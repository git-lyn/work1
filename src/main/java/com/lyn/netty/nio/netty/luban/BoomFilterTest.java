package com.lyn.netty.nio.netty.luban;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-07-25 18:12
 **/
// 测试布隆过滤器
public class BoomFilterTest {
    public static void main(String[] args) {
        int size = 10000;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.1);
        for (int i = 0; i <= size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<>(10000);

        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("出错的数据个数为: " + list.size());

    }
}
