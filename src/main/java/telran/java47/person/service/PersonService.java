package telran.java47.person.service;

import java.util.List;

import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.model.Address;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	List<PersonDto> findPersonsByCity(String city);

	List<PersonDto> findPersonsByAge(Integer min, Integer max);

	PersonDto updateName(Integer id, String newName);

	List<PersonDto> findPersonsByName(String name);

	List<CityPopulationDto> getCityPopulation();

	PersonDto updateAdress(Integer id, Address adress);

	PersonDto deletePersonById(int id);

}
