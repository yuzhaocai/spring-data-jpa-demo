package entity;

import java.io.Serializable;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 5161901213565944240L;
	
	private Long id;
	
	private Double price;
	
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
