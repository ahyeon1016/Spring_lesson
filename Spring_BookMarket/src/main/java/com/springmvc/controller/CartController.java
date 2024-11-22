package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		System.out.println("1ししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししし");

		return cartService.create(cart);
	}
	
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
		System.out.println("2ししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししし");
		Cart cart = cartService.read(cartId);
		model.addAttribute("cart", cart);
		return "cart";
	}
	
	@PutMapping("/{cartId}")
	public @ResponseBody Cart read(@PathVariable(value="cartId") String cartId) {
		System.out.println("3ししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししし");

		return cartService.read(cartId);
	}
	
	@PutMapping("/add/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest request) {
		System.out.println("4ししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししし");
		//舌郊姥艦 ID昔 室芝ID 亜閃神奄
		String sessionId= request.getSession(true).getId();
		Cart cart = cartService.read(sessionId); //舌郊姥艦拭 去系吉 乞窮 舛左 条嬢神奄
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		//井稽 痕呪 bookId拭 企廃 舛左 条嬢神奄
		Book book = bookService.getBookById(bookId);
		if(book==null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}
		//bookId拭 企廃 亀辞 舛左研 舌郊姥艦拭 去系馬奄
		cart.addCartItem(new CartItem(book));
		cartService.update(sessionId, cart); //室芝 ID拭 企廃 舌左姥艦 飴重馬奄
	}
	
	@PutMapping("/remove/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeCartByItem(@PathVariable String bookId, HttpServletRequest request) {
		//舌郊姥艦 ID昔 室芝ID 亜閃神奄
		System.out.println("5ししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししししし");
		System.out.println("bookId" + bookId);
		String sessionId= request.getSession(true).getId();
		Cart cart = cartService.read(sessionId); //舌郊姥艦拭 去系吉 乞窮 舛左 条嬢神奄
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		//井稽 痕呪 bookId拭 企廃 舛左 条嬢神奄
		Book book = bookService.getBookById(bookId);
		if(book==null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}
		//bookId拭 企廃 亀辞 舛左研 舌郊姥艦拭辞 肢薦馬奄
		cart.removeCartItem(new CartItem(book));
		cartService.update(sessionId, cart); //室芝 ID拭 企廃 舌左姥艦 飴重馬奄
	}
	
	@DeleteMapping("/{cartId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteCartList(@PathVariable(value="cartId") String cartId) {
		cartService.delete(cartId);
	}
}
