package com.vod.events;

import java.util.EventObject;

import com.vod.project.Product;


public class EventProduct extends EventObject {
private Product _product;

	public Product getProduct() {
		return _product;
		}	
	public EventProduct(Object source, Product product) {
		super(source);
		_product=product;
	}
	
}
