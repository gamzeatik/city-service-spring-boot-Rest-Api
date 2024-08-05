package gamzeatik.cityservice.controller;

import gamzeatik.cityservice.exception.CityAlreadyExistsException;
import gamzeatik.cityservice.exception.CityNotFoundException;
import gamzeatik.cityservice.model.City;
import gamzeatik.cityservice.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

    private final CityService service;

    @GetMapping
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(service.getCities(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id) {
        return new ResponseEntity<>(getCityById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        return new ResponseEntity<>(service.createCity(newCity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable String id, @RequestBody City newCity) {
        service.updateCity(id, newCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id) {
        service.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private City getCityById(String id) {
        return service.getCityById(id);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFoundException(CityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<String> handleCityAlreadyExistsException(CityAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
