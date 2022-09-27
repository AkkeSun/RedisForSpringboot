package com.example.redisstuey;

import com.example.redisstuey.cacheExample.CacheService;
import com.example.redisstuey.cacheExample.MyData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RedisCacheTest {

    @Autowired
    CacheService cacheService;

    @Test
    void defaultCacheTest() throws InterruptedException {

        System.out.println("1차 시도");
        MyData data1 = cacheService.test1("hello");

        Thread.sleep(5000);
        System.out.println("2차 시도");
        MyData data2 = cacheService.test1("hello");

        assertThat(data1.getId()).isEqualTo(data2.getId());
    }

    @Test
    void customCacheTest() throws InterruptedException {

        System.out.println("1차 시도");
        MyData data1 = cacheService.test2("hello");

        Thread.sleep(5000);
        System.out.println("2차 시도");
        MyData data2 = cacheService.test2("hello");

        assertThat(data1.getId()).isEqualTo(data2.getId());
    }

}
