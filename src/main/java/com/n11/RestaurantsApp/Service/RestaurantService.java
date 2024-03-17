package com.n11.RestaurantsApp.Service;

import com.n11.RestaurantsApp.Common.CustomException;
import com.n11.RestaurantsApp.Common.enums.ErrorMessages;
import com.n11.RestaurantsApp.DAO.DTO.RestaurantDTO;
import com.n11.RestaurantsApp.DAO.Entity.Restaurant;
import com.n11.RestaurantsApp.DAO.Mapper.RestaurantMapper;
import com.n11.RestaurantsApp.Repository.RestaurantRepository;
import com.n11.RestaurantsApp.Request.CreateRestaurantRequest;
import com.n11.RestaurantsApp.Request.PointRequest;
import com.n11.RestaurantsApp.Request.UpdateRestaurantRequest;
import com.n11.RestaurantsApp.Request.UpdateScoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public List<RestaurantDTO> getAllRestaurants() {
        Iterable<Restaurant> restaurantsI = restaurantRepository.findAll();

        // Convert Iterable to List
        List<Restaurant> restaurantsList = new ArrayList<>();
        restaurantsI.forEach(restaurantsList::add);

        return RestaurantMapper.INSTANCE.entityListToDTOList(restaurantsList);
    }

    @Transactional
    public RestaurantDTO saveRestaurant(CreateRestaurantRequest request) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.requestToEntity(request);
        restaurant.setScore(0.0);
        UUID id = UUID.randomUUID();
        String idS = id.toString();
        restaurant.setId(idS);
        restaurant.setRestaurantLocation(request.getLatitude() + "," + request.getLongitude());
        String createDate = LocalDateTime.now().toString();
        restaurant.setCreatedAt(createDate);
        restaurant.setReviews(0);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        log.info("Restaurant with id: " + savedRestaurant.getId() + " has saved!");
        return RestaurantMapper.INSTANCE.entityToDTO(savedRestaurant);
    }

    @Transactional(readOnly = true)
    public RestaurantDTO findById(String id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if(restaurantOptional.isEmpty()){
            log.warn("Restaurant with id: " + id + " not found.");
            throw new CustomException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant = restaurantOptional.get();
        return RestaurantMapper.INSTANCE.entityToDTO(restaurant);
    }

    @Transactional(readOnly = true)
    public List<RestaurantDTO> findRestaurantByNameContains(String name) {

        Iterable<Restaurant> restaurantsI = restaurantRepository.findByNameContains(name);

        List<Restaurant> restaurantsList = new ArrayList<>();
        restaurantsI.forEach(restaurantsList::add);

        return RestaurantMapper.INSTANCE.entityListToDTOList(restaurantsList);
    }

    @Transactional
    public void updateRestaurant(String id, UpdateRestaurantRequest updateRestaurantRequest) throws CustomException{
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.RESTAURANT_NOT_FOUND));

        restaurant.setCategory(updateRestaurantRequest.getCategory().toString());
        restaurant.setEmail(updateRestaurantRequest.getEmail());
        restaurant.setLatitude(updateRestaurantRequest.getLatitude());
        restaurant.setLongitude(updateRestaurantRequest.getLongitude());
        restaurant.setRestaurantLocation(updateRestaurantRequest.getLatitude() + "," + updateRestaurantRequest.getLongitude());
        restaurant.setName(updateRestaurantRequest.getName());

        saveRestaurantToSolr(restaurant);

        log.info("Restaurant with id: " + restaurant.getId() + " has been updated!");
    }

    @Transactional
    public void updateRestaurantScore(UpdateScoreRequest request) throws CustomException{

        Restaurant receivedRestaurant = restaurantRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorMessages.RESTAURANT_NOT_FOUND));

        receivedRestaurant.setScore(request.getScore());

        saveRestaurantToSolr(receivedRestaurant);

        log.info("Score for restaurant with id: " + request.getId() + " has been updated to: " + request.getScore());
    }

    @Transactional
    public void deleteRestaurant(String id) throws CustomException{
        Restaurant receivedRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.RESTAURANT_NOT_FOUND));

        restaurantRepository.deleteById(id);
    }

    @Transactional
    public List<RestaurantDTO> findRestaurantsWithin10Kilometers(PointRequest pointRequest) {
        double latitude = pointRequest.getLatitude();
        double longitude = pointRequest.getLongitude();
        Iterable<Restaurant> restaurantsI = restaurantRepository.findRestaurantsWithin10Kilometers(latitude, longitude);

        List<Restaurant> restaurantsList = new ArrayList<>();
        restaurantsI.forEach(restaurantsList::add);

        return RestaurantMapper.INSTANCE.entityListToDTOList(restaurantsList);
    }

    private void saveRestaurantToSolr(Restaurant restaurant) throws CustomException{
        try {
            restaurantRepository.save(restaurant);
        }
        catch (Exception e){
            throw new CustomException(ErrorMessages.SOLR_ERROR);
        }
    }

}
