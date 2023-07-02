package telran.java47.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.model.Address;
import telran.java47.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

	final PersonService personService;

	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@GetMapping("/city/{city}")
	public List<PersonDto> findPersonByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@GetMapping("/ages/{min}/{max}")
	public List<PersonDto> findPersonsByAge(@PathVariable Integer min, @PathVariable Integer max) {
		return personService.findPersonsByAge(min, max);
	}

	@PutMapping("/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}

	@GetMapping("/name/{name}")
	public List<PersonDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}

	@GetMapping("/population/city")
	public List<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}

	@PutMapping("{id}/address")
	public PersonDto updateAdress(@PathVariable Integer id, @RequestBody Address adress) {
		return personService.updateAdress(id, adress);
	}

	@DeleteMapping("/{id}")
	public PersonDto deletePersonById(@PathVariable int id) {
		return personService.deletePersonById(id);
	}
}
