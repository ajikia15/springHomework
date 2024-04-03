package ge.ibsu.demo.controllers;

import ge.ibsu.demo.services.ActorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController

public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @GetMapping("/api/actors")
    public List<Object[]> getAllActorsFirstAndLastName() {
        return actorService.getAllActorsFirstAndLastName();
    }
}
