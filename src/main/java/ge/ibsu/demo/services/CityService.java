package ge.ibsu.demo.services;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.repositories.CityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CityService {
    private final CityRepository cityRepository;
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    public List<Object[]> getAllCitiesWithNameAndCountry() {
        return cityRepository.findAllCitiesWithNameAndCountry();
    }
}
