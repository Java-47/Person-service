package telran.java47.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dao.PersonRepository;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.dto.exceptions.PersonNotFoundException;
import telran.java47.person.model.Address;
import telran.java47.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonsByCity(String city) {
		List<Person> persons = personRepository.findByAddressCity(city);
		return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findPersonsByAge(Integer min, Integer max) {
		LocalDate currentDate = LocalDate.now();
		LocalDate minDate = currentDate.minusYears(max);
		LocalDate maxDate = currentDate.minusYears(min);

		List<Person> persons = personRepository.findByBirthDateBetween(minDate, maxDate);
		return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());

	}

	@Override
	public PersonDto updateName(Integer id, String newName) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonsByName(String name) {
		List<Person> persons = personRepository.findByName(name);
		return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<CityPopulationDto> getCityPopulation() {
		List<Person> persons = personRepository.findAll();

		Map<String, Long> cityPopulationMap = persons.stream()
				.collect(Collectors.groupingBy(person -> person.getAddress().getCity(), Collectors.counting()));

		return cityPopulationMap.entrySet().stream()
				.map(entry -> new CityPopulationDto(entry.getKey(), entry.getValue().intValue()))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto updateAdress(Integer id, Address adress) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAddress(adress);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePersonById(int id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
