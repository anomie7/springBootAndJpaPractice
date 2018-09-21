package com.anomie.webservice.order;

import com.anomie.webservice.commons.OrderSearchVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class OrderPredicate {

	public static Predicate orderSearch(OrderSearchVO searchVO) {
		QOrder order = QOrder.order;
		BooleanBuilder builder = new BooleanBuilder();
		String memberName = searchVO.getMemberName();
		OrderStatus status = searchVO.getOrderStatus();

		if (memberName != null && memberName != "") {
			builder.and(order.member.name.eq(memberName));
		}

		if (status != null) {
			builder.and(order.status.eq(status));
		}

		return builder;
	}

}
