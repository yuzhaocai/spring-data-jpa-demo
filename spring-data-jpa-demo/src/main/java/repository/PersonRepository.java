package repository;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entity.Person;
/**
 * Repository是一个标记型接口，不提供任何方法，开发者需要自己在接口中声明方法
 * 用户接口实现标记为Spring Data JPA的方式有两种：
 * 	1.继承Repository接口或其子接口
 *  2.在持久层接口上使用@RepositoryDefinition注解，然后为其制定domainClass和idClass属性
 * 上边两种方式是等价的.
 * 
 * spring data jpa为我们解析方法名称并创建实现类,通过动态代理的方式，具体的方法实现会采用标准的JPA 的api来实现
 * spring data jpa既支持JPQL(面向对象的查询方式)，也支持本地sql的方式
 * 
 * spring data jpa接口选择：（优选选择Repository接口）
 * 	1.Repository:通常情况下的选择，如果需要的话可以自己拷贝其他接口中方法来用，所以不存在功能的强弱。
 *  2.CrudRepository：当每个接口中都有相似的增删改查的方法，可以选择继承该接口。
 *  3.PagingAndSortingRepository：改接口继承自CrudRepository接口，通常很少用，而是继承Repository或CrudRespository接口的基础上来，在自己声明的方法列表中Pageable 或 Sort 类型的参数，用于指定分页或排序信息即可
 *	4.JpaRepository：是针对 JPA 技术提供的接口，它在父接口的基础上，提供了其他一些方法，比如 flush()，saveAndFlush()，deleteInBatch() 等。
 * 
 * spring data jpa中执行具体的方法或查询的方式有三种：
 *  1.spring data jpa在后台为持久层接口创建代理对象,解析方法名，来生成相应的sql语句，实现相应的功能。
 *  2.通过命名查询语句来实现查询。@NameQuery是类级别的注解，名称格式为"类的名称.方法名称"的格式
 *  3.开发者在生命的方法上使用@Query直接，指定查询的语句（该查询语句不是sql语句，而是面向对象的JPQL查询语句，这只nativeQuery为true，表示使用本地sql的查询方式）
 *  	*JPQL语句的查询参数通过位置编号或者变量名称来指定。
 *  
 * spring data jpa查询的顺序问题：query-lookup-strategy默认值create-if-not-found
 * 	当指定的一个方法同时满足上班的三个条件时，优先使用哪种了，jpa使用query-lookup-strategy来指定查询的策略：
 * 	1.create：通过解析方法名来创建查询
 *  2.create-if-not-found：优先使用@Query注解指定的查询，然后使用@NameQuery指定的查询，最后使用解析方法名称的方式来创建查询。
 *  3.use-declared-query：优先使用@Query注解指定的查询语句，然后使用命名查询，如果都没有，则抛出异常。
 *  
 * spring data jpa中事务问题：
 *  1.spring data jpa默认实现的方法都是有事务的，相当于在XxxRepository接口中添加了@Transactional注解，也可以在接口声明的方法上自己添加注解，也可以在业务层中使用注解。
 *  2.当在Service层也提供事务是，是为了将service层调用多个Repository中的方法也放在同一个事务中，需要在Service层也添加注解，此时事务会根据事务的传播途径来进行合并或挂起。
 *  
 *  spring data jpa为Repository中的方法提供自定义实现：
 *  	通过repository-impl-postfix="Impl"属性配置实现类的后缀，将需要自己实现的方法写在对应的方法中。
 *  
 *  
 *  spring data jpa命名查询语句
 *  	通过在类上定义定义@NameQuery定义命名查询语句，使用唯一要注意的是名称查询语句的名称要满足"DomainClass.methodName"的命名规则
 *  	命名语句中的参数可以通过位置编号或者是变量的方式来指定参数
 */ 
@RepositoryDefinition(domainClass=Person.class,idClass=Long.class)
@NamedQueries(@NamedQuery(name="Person.findByLastName",query="select * from Person where lastName = ?1"))
@Repository
public interface PersonRepository{
	
	public List<Person> findByFirstName(String firstName);
	
	public void save(Person person);
	
	public void delete(Long id);
	
	public boolean exists(Long id);
	
	public List<Person> findAll();
	
	/**
	 * 命名查询语句的使用
	 */
	public Person findByLastName(String lastName);
	
	/**
	 * 使用@Query的方式进行查询(nativeQuery为true，表示使用本地sql)
	 * @param id
	 * @return
	 * 参数指定的方式：
	 * 	*位置编号：?1
	 *  *参数名称方式：:name,那么在参数列表中必须指定参数
	 */
	@Query(value="select * from t_person where id = :id",nativeQuery=true)
	public Person findById(@Param("id")Long id);
	
	/**
	 * 支持更新类的查询语句，使用@Modifying注解标注在@Query注解之上
	 * @param id
	 * @param firstName
	 * @return
	 */
	@Modifying
	@Query("update Person set firstName = :firstName where id = :id")
	public int updatePersonFirstName(@Param("id") Long id,@Param("firstName") String firstName);
	
	/**
	 * 自己扩展自己的实现类，在子类中实现具体的数据访问层的逻辑,那么在调用的时候将会调用子类中的实现方法
	 * @param firstName
	 * @return
	 * 使用方式：
	 * 	1.在接口中声明需要的方法
	 *  2.在子类中实现具体的方法（注意：此处的子类不是严格意义上的子类，没有继承关系）
	 *  3.在配置文件中配置扫描子类的方式，如后缀为Impl
	 */
	public List<Long> findPersonIdByFisrtName(String firstName);
	
}
