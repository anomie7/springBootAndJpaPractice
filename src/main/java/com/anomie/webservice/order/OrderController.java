package com.anomie.webservice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anomie.webservice.commons.OrderItemDTO;
import com.anomie.webservice.item.Item;
import com.anomie.webservice.item.ItemService;
import com.anomie.webservice.member.Member;
import com.anomie.webservice.member.MemberService;

@Controller
public class OrderController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/order/create")
	public String goToOrderPage(Model model) {
		model.addAttribute("members", memberService.findMembers());
		model.addAttribute("items", itemService.findItems());
		return "order/create";
	}
	
	@PostMapping(path="/order/create")
	public void createOrder(@RequestBody OrderItemDTO dto) {
		Member member = memberService.findOne(dto.getMemberId());
		Item item = itemService.findOne(dto.getItemId());
		Delivery delivery = Delivery.builder().address(member.getAddress()).status(DeliveryStatus.READY).build();
		OrderItem orderItem = OrderItem.builder().item(item).count(dto.getCount()).orderPrice(dto.getPrice()).build();
		Order order = Order.builder().member(member).status(OrderStatus.ORDER).delivery(delivery).build();
		order.addOrderItem(orderItem);
		orderService.save(order);
	}
}
