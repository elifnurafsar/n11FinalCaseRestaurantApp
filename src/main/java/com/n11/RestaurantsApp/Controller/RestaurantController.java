package com.n11.RestaurantsApp.Controller;

import com.n11.RestaurantsApp.Common.CustomException;
import com.n11.RestaurantsApp.DAO.DTO.RestaurantDTO;
import com.n11.RestaurantsApp.Request.CreateRestaurantRequest;
import com.n11.RestaurantsApp.Request.PointRequest;
import com.n11.RestaurantsApp.Request.UpdateRestaurantRequest;
import com.n11.RestaurantsApp.Request.UpdateScoreRequest;
import com.n11.RestaurantsApp.Service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@RequestBody CreateRestaurantRequest request) {
        RestaurantDTO restaurantDTO = restaurantService.saveRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDTO);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAll() {
        List<RestaurantDTO> restaurantDTOS = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurantDTOS);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> searchByName(@RequestParam("name") String name) {
        List<RestaurantDTO> restaurantDTOS = restaurantService.findRestaurantByNameContains(name);
        return ResponseEntity.ok(restaurantDTOS);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<RestaurantDTO> findById(@RequestParam("id") String id) {
        try{
            RestaurantDTO restaurantDTO = restaurantService.findById(id);
            return ResponseEntity.ok(restaurantDTO);
        } catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

    @GetMapping("/restaurants/within-10-kilometers")
    public ResponseEntity<List<RestaurantDTO>> findRestaurantsWithin10Kilometers(@RequestBody PointRequest point) {
        List<RestaurantDTO> restaurants = restaurantService.findRestaurantsWithin10Kilometers(point);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/restaurants/within-10-kilometers/by-params")
    public ResponseEntity<List<RestaurantDTO>> findRestaurantsWithin10Kilometers(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        PointRequest point = new PointRequest(latitude, longitude);
        List<RestaurantDTO> restaurants = restaurantService.findRestaurantsWithin10Kilometers(point);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam("id") String id, @RequestBody UpdateRestaurantRequest updateRestaurantRequest) {
        restaurantService.updateRestaurant(id, updateRestaurantRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/score")
    public ResponseEntity<Void> updateScore(@RequestBody UpdateScoreRequest updateScoreRequest) {
        restaurantService.updateRestaurantScore(updateScoreRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("id") String id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }

}

