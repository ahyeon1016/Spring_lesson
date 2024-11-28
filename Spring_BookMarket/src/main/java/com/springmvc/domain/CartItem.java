package com.springmvc.domain;

import java.io.Serializable;

public class CartItem implements Serializable{
	
	private static final long serialVersionUID = -6160563150511606621L;
	private Book book;
	private int quantity;
	private int totalPrice;
	
	public CartItem() {}
	
	public CartItem(Book book) {
		this.book = book;
		this.quantity = 1;
		this.totalPrice = book.getUnitPrice();
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void updateTotalPrice() {
		System.out.println(this.quantity);
		System.out.println(this.book.getUnitPrice());
		totalPrice = this.book.getUnitPrice()*this.quantity;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		//»ïÇ× ¿¬»êÀÚ
		result = prime * result + ((book==null) ? 0 : book.hashCode());
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
		
		CartItem other = (CartItem) obj;
		
		if(book==null) {
			if(other.book != null) {
				return false;
			}
		} else if(!book.equals(other.book)) {
			return false;
		}

		return true;
	} 
}
