package com.n11.RestaurantsApp.DAO.Mapper;


import com.n11.RestaurantsApp.Common.enums.Category;
import com.n11.RestaurantsApp.DAO.DTO.RestaurantDTO;
import com.n11.RestaurantsApp.DAO.Entity.Restaurant;
import com.n11.RestaurantsApp.Request.CreateRestaurantRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant requestToEntity(CreateRestaurantRequest createRestaurantRequest);

    RestaurantDTO entityToDTO(Restaurant restaurant);

    List<RestaurantDTO> entityListToDTOList(List<Restaurant> restaurants);

    default String mapCategoryToString(Category category) {
        return category != null ? category.toString() : Category.GENERAL.toString();
    }

    default Category mapCategory(String category) {
        return Category.valueOf(category);
    }

}