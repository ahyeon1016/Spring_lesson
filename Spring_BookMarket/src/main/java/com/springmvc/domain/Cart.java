package com.springmvc.domain;

import java.util.HashMap;
import java.util.Map;

import com.springmvc.domain.CartItem;

public class Cart {
	private String cartId;
	private Map<String, CartItem> cartItems;
	private int grandTotal;
	
	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;
	}
	
	public Cart(String cartId){
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItem() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItem) {
		this.cartItems = cartItem;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void updateGrandTotal() {
		grandTotal = 0;
		for(CartItem  item : cartItems.values()) {
			grandTotal = grandTotal + item.getTotalPrice();
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		//삼항 연산자
		result = prime * result + ((cartId==null) ? 0 : cartId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Cart other = (Cart) obj;
		
		if(cartId==null) {
			if(other.cartId != null) {
				return false;
			}
		} else if(!cartId.equals(other.cartId)) {
			return false;
		}

		return true;
	} 
	
	public void addCartItem(CartItem item) {
		String bookId = item.getBook().getBookId(); //현재 등록하기 위한 도서 ID 가져오기
		
		//도서 ID가 cartItems 객체에 등록 되어 있는지 확인
		if(cartItems.containsKey(bookId)) {
			CartItem cartItem = cartItems.get(bookId); //등록된 도서ID 가져오기
			//등록된 도서 ID 개수 저장
			cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());
			cartItems.put(bookId, cartItem);
		} else {
			cartItems.put(bookId, item); //도서 ID에 대한 도서 정보 (item) 저장
		}
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem item) {
		String bookId = item.getBook().getBookId();
		cartItems.remove(bookId); //bookId 도서 삭제
		updateGrandTotal(); //총액 갱신
	}
}
