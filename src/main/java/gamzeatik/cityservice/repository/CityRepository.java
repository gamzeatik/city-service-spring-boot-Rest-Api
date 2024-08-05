package gamzeatik.cityservice.repository;

import gamzeatik.cityservice.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends MongoRepository<City, String> {
    List<City> findAllByName(String name);

    Optional<City> findByName(String name);
}
