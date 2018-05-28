package business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entity.Owner;
import data.repository.OwnerRepository;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public Owner getUser(int userId) {
		
		return ownerRepository.findOne(userId);
		
	}
	
	public List<Owner> getAllUsers(){
		
		List<Owner> owners = new ArrayList<Owner>();
		ownerRepository.findAll().forEach(owners::add);
		
		return owners;
		
	}
	
	public Owner getUserByUsername(String name){
		
		return ownerRepository.findByName(name);

	}
	
	public void addUser(Owner user) {
		
		ownerRepository.save(user);
		
	}
	
	public void updateUser(Owner user) {
		
		ownerRepository.save(user);
		
	}
	
	public void deleteUser(int id) {
		
		ownerRepository.delete(id);
		
	}

}
