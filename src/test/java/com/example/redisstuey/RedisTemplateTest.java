package com.example.redisstuey;

import com.example.redisstuey.redisRepositoryExample.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void stringTest() {

        // given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "helloString";

        // when (set helloString data1)
        valueOperations.set(key, "data1");

        // then (get helloString)
        String value = valueOperations.get(key);
        assertThat(value).isEqualTo("data1");

        valueOperations.getAndDelete(key);
    }

    @Test
    void listTest() {

        // given
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        String key = "listKey";

        // when (rpush listKey h)...
        listOperations.rightPush(key, "h");
        listOperations.rightPush(key, "e");
        listOperations.rightPush(key, "l");
        listOperations.rightPush(key, "l");
        listOperations.rightPush(key, "o");

        // then (lindex listKey 0)
        String index1Data = listOperations.index(key, 0);
        assertThat(index1Data).isEqualTo("h");

        // (lrange listKey 0, 4)
        List<String> redisList = listOperations.range(key, 0, 4);
        redisList.forEach(data -> System.out.print(data)); // hello

        Long size = listOperations.size(key);
        assertThat(size).isEqualTo(5L);
    }

    @Test
    void setTest() {

        // given
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String key = "setKey";

        // when (sadd setKey h)...
        setOperations.add(key, "h", "e", "l", "l", "o");

        // then (smembers setKey)
        Set <String> redisSet = setOperations.members(key);
        Long size = setOperations.size(key);
        redisSet.forEach(data -> System.out.println(data));

        assertThat(size).isEqualTo(4L);
    }


    @Test
    void hashTest() {

        // given <key, fieldKeyType, fieldValueType >
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        String keyspace = "people";
        String key = keyspace + ":sun";

        Map<String, Object> map = new HashMap<>();
        map.put("id", "sun");
        map.put("name", "sun");
        map.put("age", 32);

        // when
        hashOperations.putAll(key, map);

        // then (hgetAll people:sun)
        Map<String, Object> redisMap = hashOperations.entries(key);
        Person sun = new Person(redisMap.get("id").toString(), redisMap.get("name").toString(), (Integer)redisMap.get("age"));
        System.out.println(sun);

        assertThat(sun.getId()).isEqualTo("sun");
        assertThat(sun.getName()).isEqualTo("sun");
        assertThat(sun.getAge()).isEqualTo(32);
    }


}
