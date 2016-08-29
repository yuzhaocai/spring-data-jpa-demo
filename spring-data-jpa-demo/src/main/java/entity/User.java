package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 配置JPA关联关系
 * @author Administrator
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -3363120264501521428L;
	
	private Long id;
	
	private String username;
	
	private String email;
	
	private Integer age;
	
	private Set<Order> orders = new HashSet<Order>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
