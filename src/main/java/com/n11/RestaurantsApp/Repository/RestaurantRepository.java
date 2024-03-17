package com.n11.RestaurantsApp.Repository;

import com.n11.RestaurantsApp.DAO.Entity.Restaurant;
import org.springframework.data.repository.query.Param;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.data.solr.repository.Query;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {

    @Query("name:*?0*")
    Iterable<Restaurant> findByNameContains(String name);

    // Find restaurants that located at most 10 kilometers away from user's location
    @Query(name = "Restaurant.findRestaurantsWithin10Kilometers")
    Iterable<Restaurant> findRestaurantsWithin10Kilometers(@Param("latitude") double latitude, @Param("longitude") double longitude);
}
