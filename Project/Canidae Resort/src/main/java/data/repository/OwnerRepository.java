package data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import data.entity.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
	
	public Owner findByName(String name);

}
