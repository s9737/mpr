package com.vod.project;

public class Product implements IProduct {
	public ProductMarks mark;
	public String name;
	public double price;
	public float code;
	boolean box;

	public Product(ProductMarks mark, double price) {
		this.mark = mark;
		this.price = price;
	}
	public Product(String name, double price, float code) {
		this.name = name;
		this.code = code;
		this.price = price;
	}
	public Product(ProductMarks mark, double price, float code) {
		this.mark = mark;
		this.code = code;
		this.price = price;
	}
	public void setPrice(double z) throws PriceException {
		if (z < 0)
			throw new PriceException("Cena nie mo¿e byæ ni¿sza ni¿ 0");
		else
			this.price = z;
	}
	public void printProduct() {
		System.out.println("Nazwa: " + mark + " --> Cena: " + price + " zl");
	}
	public ProductMarks getName() {
		return this.mark;
	}
	public String getName2() {
		return name;
	}
	public void setBox() {
		this.box = true;
	}
	public boolean getBox() {
		return this.box = box;
	}
	public double getPrice() {
		return price;
	}
	public void setName(ProductMarks mark) {
		this.mark = mark;
	}
	public float getCode() {
		return this.code;
	}
	public void setCode(float code) throws CodeException {
		if (code < 0)
			throw new CodeException("Kod nie mo¿e byæ 0");
		else
			this.code = code;
	}
}
