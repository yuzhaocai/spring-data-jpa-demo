package entity.OneToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *@JoinTable注解
 *	*和@Table注解类似，用于标书关联的表，name为连接表的名称，默认名称为"表名1"+"_"+"表名2" 
 *  *@joinColumns在属性中表示，所保存关联关系得外键的字段，并配合@JoinColumn注解使用
 *  *@inverseJoinColumns和@JoinColumns属性类似，它保存的是关系得另一个外键字段，也是配合@JoinColumn注解来使用
 *  
 *
 */
@Entity
@Table(name="t_person")
public class Person implements Serializable {

	private static final long serialVersionUID = -3444588011548732500L;
	
	/**
	 * 采用主键自动的方式来生成主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=32,nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int age;
	
	/**
	 * 单向一对多的问题，使用中间表的方式来维护一对多的问题，需要使用@JoinTable注解
	 * 一对多的问题，如果由多的一方来维护关系，则是通过添加第三个表的方式来维护。
	 * @OneToMany
		@JoinTable(name="t_p_c",joinColumns={@JoinColumn(name="p_id",referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="c_id",referencedColumnName="id")})
	 */
	
	@OneToMany(mappedBy="person")
	private Set<CellPhone> cellPhones = new HashSet<CellPhone>();

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

	public Set<CellPhone> getCellPhones() {
		return cellPhones;
	}

	public void setCellPhones(Set<CellPhone> cellPhones) {
		this.cellPhones = cellPhones;
	}
}
