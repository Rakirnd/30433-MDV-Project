package business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entity.Contract;
import data.repository.ContractRepository;

@Service
public class ContractService {
	
	@Autowired
	private ContractRepository contractRepository;
	
	public Contract getContract(int contractId) {
		
		return contractRepository.findOne(contractId);
		
	}
	
	public void addContract(Contract contract) {
		
		contractRepository.save(contract);
		
	}
	
	public void updateContract(Contract contract) {
		
		contractRepository.save(contract);
		
	}
	
	public void deleteContract(int id) {
		
		contractRepository.delete(id);
		
	}

}
