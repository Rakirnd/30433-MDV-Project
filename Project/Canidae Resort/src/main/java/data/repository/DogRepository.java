package data.repository;

import org.springframework.data.repository.CrudRepository;

import data.entity.Dog;

public interface DogRepository extends CrudRepository<Dog, Integer> {
	
	

}
