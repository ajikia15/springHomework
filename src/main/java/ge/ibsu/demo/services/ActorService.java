package ge.ibsu.demo.services;

import ge.ibsu.demo.entities.Actor;
import ge.ibsu.demo.repositories.ActorRepository;

import java.util.List;

public class ActorService {
    private final ActorRepository actorRepository;
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
    public List<Object[]> getAllActorsFirstAndLastName() {
        return actorRepository.findAllActorsFirstAndLastName();
    }
}
