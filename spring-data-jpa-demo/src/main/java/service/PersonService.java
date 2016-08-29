package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.PersonRepository;
import entity.Person;

@Service
@Transactional()
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository; 
	
	@Transactional(readOnly=true,rollbackFor=Exception.class)
	public List<Person> getPersonByFirstName(String firstName){
		return personRepository.findByFirstName(firstName);
	}
	
	public void savePerson(Person person){
		//测试spring data jpa中的关键字
		personRepository.save(person);
	}
	
	public void deletePerson(Long id){
		personRepository.delete(id); 	
	}
	
	public boolean exists(Long id){
		return personRepository.exists(id);
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Person getPersonByLastName(String lastName){
		return personRepository.findByLastName(lastName);
	}
	
	@Transactional(readOnly=true)
	public Person getPerson(Long id){
		return personRepository.findById(id);
	}
	
	public int updatePersonFristName(Long id,String firstName){
		return personRepository.updatePersonFirstName(id, firstName);
	}
	
	@Transactional(readOnly=true)
	public List<Long> getPersonIdsByFirstName(String firstName){
		return personRepository.findPersonIdByFisrtName(firstName);
	}
	
}
