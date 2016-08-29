package service;

/*使用静态导入，将类中的所有静态成员变量全部导入进来*/
import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext-core.xml"})
public class TestPersonService {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * 只是为了更新数据库
	 */
	@Test
	public void updateDDL(){
		
	}
	
	@Test
	public void testGetPersonByFirstName(){
		String firstName = "wu";	
		List<Person> person = personService.getPersonByFirstName(firstName);
		for (Person p : person) {
			System.out.println(p);
		}
	}
	
	@Test
	public void testSavePerson(){
		Person person = new Person();
		person.setFirstName("yang");
		person.setLastName("mi");
		personService.savePerson(person);
	}
	
	@Test
	public void testDeletePerson(){
		Long id = 2L;
		personService.deletePerson(id);
	}
	
	@Test
	public void testExistsPerson(){
		Long id = 3L;
		System.out.println(personService.exists(id));
	}
	
	@Test
	public void testFindAllPerson(){
		List<Person> persons = personService.findAll();
		
		for (Person person : persons) {
			System.out.println(person);
		}
	}
	
	/**
	 * 测试条件：
	 * 	去掉PersonService.savePerson()方法上的@Transactional注解
	 */
	@Test
	public void testJPATransactional(){
		try {
			Person person = new Person();
			person.setFirstName("liu");
			person.setLastName("yifei");
			personService.savePerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPersonByLastName(){
		String lastName = "yan";
		Person person = personService.getPersonByLastName(lastName);
		System.out.println(person);
	}
	
	@Test
	public void testGetPerson(){
		Long id = 1L;
		Person person = personService.getPerson(id);
		System.out.println(person);
	}
	
	@Test
	public void testUpdatePersonFristName(){
		Long id = 1L;
		String firstName = "fan";
		int i = personService.updatePersonFristName(id, firstName);
		System.out.println(i);
	}
	
	@Test
	public void testGetPersonIdsByFirstName(){
		String firstName = "liu";
		List<Long> personIds = personService.getPersonIdsByFirstName(firstName);
		System.out.println(personIds.toString());
	}
	
	
}
