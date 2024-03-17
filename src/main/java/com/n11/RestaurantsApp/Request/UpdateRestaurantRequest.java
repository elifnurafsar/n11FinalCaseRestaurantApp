package com.n11.RestaurantsApp.Request;

import com.n11.RestaurantsApp.Common.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestaurantRequest {

    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    @NotNull
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @NotNull
    private String email;

    @NotBlank(message = "Category cannot be blank")
    @NotEmpty(message = "Category cannot be empty")
    @NotNull
    private Category category;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
