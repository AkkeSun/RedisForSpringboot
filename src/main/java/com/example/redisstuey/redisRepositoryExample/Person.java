package com.example.redisstuey.redisRepositoryExample;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

// value = Redis의 keyspace 값
// timeToLive = Redis hash 의 만료시간 (초단위)
@RedisHash(value = "people", timeToLive = 100)
@Data
public class Person {

    public Person(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    @Id // id 값을 설정하지 않으면 랜덤으로 값이 들어간다
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;
}
