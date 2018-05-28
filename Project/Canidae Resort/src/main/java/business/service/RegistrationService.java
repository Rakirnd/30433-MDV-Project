package business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entity.Registration;
import data.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	public Registration getRegistraion(int regId) {
		
		return registrationRepository.findOne(regId);
		
	}
	
	public void addRegistration(Registration reg) {
		
		registrationRepository.save(reg);
		
	}
	
	public void updateRegistration(Registration reg) {
		
		registrationRepository.save(reg);
		
	}
	
	public void deleteRegistration(int id) {
		
		registrationRepository.delete(id);
		
	}

}
