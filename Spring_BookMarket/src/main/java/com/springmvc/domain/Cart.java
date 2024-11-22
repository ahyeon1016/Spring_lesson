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
	
	public void addCartItem(CartItem item) {
		String bookId = item.getBook().getBookId(); //���� ����ϱ� ���� ���� ID ��������
		
		//���� ID�� cartItems ��ü�� ��� �Ǿ� �ִ��� Ȯ��
		if(cartItems.containsKey(bookId)) {
			CartItem cartItem = cartItems.get(bookId); //��ϵ� ����ID ��������
			//��ϵ� ���� ID ���� ����
			cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());
			cartItems.put(bookId, cartItem);
		} else {
			cartItems.put(bookId, item); //���� ID�� ���� ���� ���� (item) ����
		}
		//updateGrandTotal();
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

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		//���� ������
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
	
}