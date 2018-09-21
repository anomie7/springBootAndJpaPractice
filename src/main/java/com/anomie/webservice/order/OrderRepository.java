package com.anomie.webservice.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.anomie.webservice.member.Member;
import com.querydsl.core.types.Predicate;

public interface OrderRepository extends JpaRepository<Order, Long>, QueryDslPredicateExecutor<Order>{
	public List<Order> findAllByOrderByIdDesc(Predicate predicate);
}
