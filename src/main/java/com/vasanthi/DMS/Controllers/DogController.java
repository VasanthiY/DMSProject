package com.vasanthi.DMS.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vasanthi.DMS.Models.Dog;
import com.vasanthi.DMS.Models.Trainer;
import com.vasanthi.DMS.Repository.DogRepository;
import com.vasanthi.DMS.Repository.TrainerRepository;

/**
 * S566493
 */
@Controller
public class DogController {
	

	ModelAndView mv = new ModelAndView();

	@Autowired
	public DogRepository dogRepository;

	@Autowired
	public TrainerRepository trainerRepository;

	@RequestMapping("dogHome")
	public ModelAndView home() {
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("add")
	public ModelAndView add() {
		mv.setViewName("addNewDog");
		mv.addObject("trainers",trainerRepository.findAll());
		return mv;
	}

	@RequestMapping("addNewDog")
	public ModelAndView addnewDog(Dog dog, @RequestParam("trainerId") int id) {
		dog.setTrainer(trainerRepository.findById(id).orElse(new Trainer()));
		dogRepository.save(dog);
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs() {
		mv.setViewName("viewDogs");
		Iterable<Dog> dogs = dogRepository.findAll();
		mv.addObject("dogs", dogs);
		return mv;
	}

	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog) {
		dogRepository.save(dog);
		mv.setViewName("editDog");
		return mv;
	}

	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog) {
		// find by id
//		Optional<Dog> found = dogRepository.findById(dog.getId());
//		if (found.isPresent()) {
//			dogRepository.delete(dog);
//		}
		// return home();

		// based on name
//		List<Dog> dogsFound = dogRepository.findByName(dog.getName());
//		for(Dog d : dogsFound) {
//			dogRepository.delete(d);
//		}
//		return home();

		Dog d = dogRepository.findById(dog.getId()).orElse(new Dog());
		dogRepository.delete(d);
		return home();
	}

	@RequestMapping("search")
	public ModelAndView searchDogById(int id) {
		Dog dog = dogRepository.findById(id).orElse(new Dog());
		mv.addObject(dog);
		mv.setViewName("searchResults");
		return mv;
	}

	@RequestMapping("addTrainer")
	public ModelAndView addTrainer() {
		mv.setViewName("addNewTrainer");
		return mv;
	}

	@RequestMapping("trainerAdded")
	public ModelAndView addnewTrainer(Trainer trainer) {
		trainerRepository.save(trainer);
		mv.setViewName("home");
		return mv;
	}

}
