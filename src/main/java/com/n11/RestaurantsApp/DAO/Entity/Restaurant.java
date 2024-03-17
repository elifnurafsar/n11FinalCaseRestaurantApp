package com.n11.RestaurantsApp.DAO.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(solrCoreName = "n11_restaurants")
public class Restaurant {

    @Id
    @Indexed(name="id", type = "string")
    @Field
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    @NotNull
    @Indexed(name="name", type = "string")
    @Field
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Indexed(name="email", type = "string")
    @Field
    private String email;

    @Indexed(name="category", type = "string")
    @Field
    private String category;

    @Indexed(name="createdAt", type = "pdate")
    @Field
    private String createdAt;

    @NotNull(message = "Latitude cannot be null")
    @Indexed(name="latitude", type = "pdouble")
    @Field
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    @Indexed(name="longitude", type = "pdouble")
    @Field
    private Double longitude;

    @Indexed(name="score", type = "pdouble")
    @Field
    private Double score;

    @Indexed(name="reviews", type = "pint")
    @Field
    private int reviews;

    @Indexed(name = "restaurantLocation", type = "location")
    @Field
    private String restaurantLocation;

}
