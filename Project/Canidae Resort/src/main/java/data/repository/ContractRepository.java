package data.repository;

import org.springframework.data.repository.CrudRepository;

import data.entity.Contract;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
	
	

}
