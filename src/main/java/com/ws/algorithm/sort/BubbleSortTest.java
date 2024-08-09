package com.ws.algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 冒泡排序
 *
 * @author wangsen
 * @date 2024/08/04
 */
@Slf4j
public class BubbleSortTest {

    @Test
    public void test() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(arr);
        log.info("排序结果:{}", arr);     

    }

    public void bubbleSort(int[] arr) {
        int high = arr.length - 1;
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < high - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
