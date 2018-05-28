package business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entity.Dog;
import data.repository.DogRepository;

@Service
public class DogService {
	
	@Autowired
	private DogRepository dogRepository;
	
	public Dog getDog(int dogId) {
		
		return dogRepository.findOne(dogId);
		
	}
	
	public void addDog(Dog dog) {
		
		dogRepository.save(dog);
		
	}
	
	public void updateDog(Dog dog) {
		
		dogRepository.save(dog);
		
	}
	
	public void deleteDog(int id) {
		
		dogRepository.delete(id);
		
	}

}
