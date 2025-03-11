package main.services;
import java.util.List;

import main.domain.Animals;
import main.repository.Repo_Animals;

public class AnimalsServices {
	private Repo_Animals repo_animals;
	
	
	AnimalsServices(Repo_Animals repo_animals){
		this.repo_animals = repo_animals;
	}
	  
	public void addAnimal(Animals animal) {
		this.repo_animals.addAnimal(animal);
	}

    
    public List<Animals> getAllAnimals() {
        return this.repo_animals.getAllAnimals();
    }

    
    public Animals getAnimalById(int id) {
        return this.repo_animals.readById(id);
    }

    
    public boolean updateAnimal(int id, Animals updatedAnimal) {
        return this.repo_animals.updateAnimal(id, updatedAnimal);
    }

    
    public boolean deleteAnimal(int id) {
        return this.repo_animals.delete(id);
}
}
