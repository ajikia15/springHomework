package ge.ibsu.demo.controllers;
import ge.ibsu.demo.services.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/api/cities")
    public List<Object[]> getAllCitiesWithNameAndCountry() {
        return cityService.getAllCitiesWithNameAndCountry();
    }
}
