package entity.OneToOne;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * OneToOne
 * 	单向
 * 		*在Student中添加Address对象
 * 			*只需要在关系的发出端添加@OneToOne注解
 *  双向
 *		*在Student中添加Address对象
 *		*在Address中添加Student对象
 *			*在关系得发出端添加@OneToOne注解
 *			*在关系得反端添加@OneToOne注解并且制定mappendBy属性,指向关系得发出端
 *
 */
@Entity
@Table(name="t_student")
public class Student implements Serializable {
	
	/**
	 * 主键生成策略为自动
	 * 	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_gene")
	 *	@SequenceGenerator(name="seq_gene",sequenceName="seq_user")
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	/**
	 * @Transient标示该字段为瞬时字段，不会再数据库中生成其对应的记录
	 */
	@Transient
	private String tempValue;
	
	@OneToOne
	@JoinColumn(name="addr_id")
	private Address address;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTempValue() {
		return tempValue;
	}

	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}
	
	
}
