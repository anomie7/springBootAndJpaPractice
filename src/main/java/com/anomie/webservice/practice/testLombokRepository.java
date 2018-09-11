package com.anomie.webservice.practice;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface testLombokRepository extends CrudRepository<testLombok, Long>, QueryDslPredicateExecutor<testLombok> {

}
