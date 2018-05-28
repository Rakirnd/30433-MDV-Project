package data.repository;

import org.springframework.data.repository.CrudRepository;

import data.entity.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Integer> {

}
