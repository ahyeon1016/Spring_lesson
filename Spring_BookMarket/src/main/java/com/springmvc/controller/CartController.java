package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springmvc.domain.Book;
import com.springmvc.domain.Cart;
import com.springmvc.domain.CartItem;
import com.springmvc.exception.BookIdException;
import com.springmvc.service.BookService;
import com.springmvc.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		return "redirect:/cart/"+sessionId;
	}
	
	@PostMapping
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		return cartService.create(cart);
	}
	
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
		Cart cart = cartService.read(cartId);
		model.addAttribute("cart", cart);
		return "cart";
	}
	
	@PutMapping("/{cartId}")
	public @ResponseBody Cart read(@PathVariable(value="cartId") String cartId) {
		return cartService.read(cartId);
	}
	
	@PutMapping("/add/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest request) {
		//��ٱ��� ID�� ����ID ��������
		String sessionId= request.getSession(true).getId();
		Cart cart = cartService.read(sessionId); //��ٱ��Ͽ� ��ϵ� ��� ���� ������
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		//��� ���� bookId�� ���� ���� ������
		Book book = bookService.getBookById(bookId);
		if(book==null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}
		//bookId�� ���� ���� ������ ��ٱ��Ͽ� ����ϱ�
		cart.addCartItem(new CartItem(book));
		cartService.update(sessionId, cart); //���� ID�� ���� �庸���� �����ϱ�
	}
}