package com.example.redisstuey.redisRepositoryExample;

import org.springframework.data.repository.CrudRepository;

// <도메인, id type>
public interface PersonRepository  extends CrudRepository<Person, String> {
}
