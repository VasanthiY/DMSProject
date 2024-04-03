package com.vasanthi.DMS.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vasanthi.DMS.Models.Dog;

/**
 * S566493
 */

public interface DogRepository extends CrudRepository<Dog,Integer>{

	List<Dog> findByName(String name);

}