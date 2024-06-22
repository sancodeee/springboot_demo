package com.ws.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * sql拦截器
 *
 * @author wangsen
 * @date 2024/06/22
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}), @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}), @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Slf4j
public class SqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 记录SQL执行开始时间
        long startTime = System.currentTimeMillis();
        // 执行原始SQL操作
        Object result = invocation.proceed();
        // 记录SQL执行结束时间
        long endTime = System.currentTimeMillis();

        String sql = null;
        try {
            // 获取执行的SQL语句
            sql = generateSql(invocation);
        } catch (Exception e) {
            log.error("获取SQL异常", e);
        } finally {
            // 计算SQL执行耗时
            long costTime = endTime - startTime;
            // 打印SQL执行耗时和SQL语句
            log.info("\n 执行SQL：{} \n 执行SQL耗时：{}(ms) \n=========================================================================",
                    sql, costTime);
        }
        // 返回原始方法执行结果
        return result;
    }

    /**
     * 生成完整的SQL语句，包括参数替换
     *
     * @param invocation 调用
     * @return {@link String }
     */
    private static String generateSql(Invocation invocation) {
        // 获取到BoundSql以及Configuration对象
        // BoundSql 对象存储了一条具体的 SQL 语句及其相关参数信息。
        // Configuration 对象保存了 MyBatis 框架运行时所有的配置信息
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs().length > 1 ? invocation.getArgs()[1] : null;
        BoundSql boundSql = statement.getBoundSql(parameter);
        Configuration configuration = statement.getConfiguration();

        // SQL中多个空格使用一个空格代替
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        // 获取参数映射
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // 获取参数对象
        Object parameterObject = boundSql.getParameterObject();

        // 如果SQL有参数映射，并且参数对象不为空，则进行参数替换
        if (!ObjectUtils.isEmpty(parameterMappings) && !ObjectUtils.isEmpty(parameterObject)) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // 如果参数对象的类型有对应的TypeHandler，则使用TypeHandler进行参数处理
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                // 否则，逐个处理参数映射
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    // 检查对象中是否存在属性的getter方法，如果存在则取出来进行替换
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 检查BoundSql对象中是否存在附加参数，有的话进行替换
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        // 如果都没有匹配到，用"缺失"替换
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }
        return sql;
    }

    /**
     * 获取参数值的字符串表示形式
     *
     * @param object 对象
     * @return {@link String }
     */
    private static String getParameterValue(Object object) {
        String value = "";
        if (object instanceof String) {
            value = "'" + object.toString() + "'";
        } else if (object instanceof Date) {
            DateFormat format = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + format.format((Date) object) + "'";
        } else if (!ObjectUtils.isEmpty(object)) {
            value = object.toString();
        }
        return value;
    }

    @Override
    public Object plugin(Object target) {
        // 包装目标对象，返回代理对象
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以通过properties配置插件参数
    }
}
