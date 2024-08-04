package com.ws.algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 快速排序
 *
 * @author wangsen
 * @date 2024/08/04
 */
@Slf4j
public class QuickSortTest {

    @Test
    public void test() {
        // 测试数据
        int[] arr = {3, 4, 5, 2, 1, 9, 8};
        quickSort(arr, 0, arr.length - 1);
        log.info("排序结果:{}", arr);
    }

    public void quickSort(int[] arr, int low, int high) {
        // 递归结束快排
        if (low < high) {
            int partition = partition(arr, low, high);
            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
        }
    }

    /**
     * 分区
     *
     * @param arr  数组
     * @param low  低
     * @param high 高
     * @return int 分区点值
     */
    public int partition(int[] arr, int low, int high) {
        // 分区点
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // 将分区点放到正确的位置
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
