package com.example.redisstuey;

import com.example.redisstuey.redisRepositoryExample.Person;
import com.example.redisstuey.redisRepositoryExample.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisRepositoryTest {

	@Autowired
	private PersonRepository repo;

	@Test
	void redisRepositoryTest() {

		Person person = new Person("myId", "sun", 32);

		// 저장 (실제 redis에 저장되는 hash key = people:myId)
		repo.save(person);

		// 검색 (hgetAll people:myId)
		Optional<Person> byId = repo.findById(person.getId());
		Person savedPerson = byId.get();
		assertEquals(person.getId(), savedPerson.getId());

		// Person Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
		repo.count();

		// 삭제
		//repo.delete(person);
	}

}
