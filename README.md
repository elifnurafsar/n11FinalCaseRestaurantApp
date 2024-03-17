
# ResaurantsApp

RestaurantApp is a Spring Boot-powered web application that seamlessly integrates with Solr for efficient restaurant management.


## Restaurant API Usage

#### Create a Restaurant

```http
POST /api/restaurants
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `request` | `RestaurantDTO` | **Required**. Restaurant data to be saved. |


#### Update Restaurant

```http
PUT /api/restaurants
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `request` | `UpdateRestaurantRequest` | **Required**. Restaurant data to be updated. |

#### Udtate Score

```http
PUT /api/restaurants/score
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `request` | `UpdateScoreRequest` | **Required**. Restaurant data and new score information. |

#### Find Restaurants within 10 Kilometers

```http
GET /api/restaurants/restaurants/within-10-kilometers
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `request` | `PointRequest` | **Required.** Location information of the reference point.|

#### Find Restaurants within 10 Kilometers (Parametrized)

```http
GET /api/restaurants/restaurants/within-10-kilometers/by-params?latitude={latitude}&longitude={longitude}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `latitude` | `double` | **Required.** The latitude coordinate of the point.|
| `longitude` | `double` | **Required.** The longitude coordinate of the point.|


#### Get All Restaurants

```http
GET /api/restaurants
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `none` |  | |

#### Search Restaurant by Name

```http
GET /api/restaurants/search?name={name}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required** The name of the restaurant to search for.|

#### Search Restaurant by ID

```http
GET /api/restaurants/find-by-id?id={id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required** The id of the restaurant to search for.|


#### Delete a Restaurant

```http
DELETE /api/restaurants?id={id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required** The id of the restaurant to be deleted.|

## Installation Guide

Because the String or Point datatype that we used when creating the Restaurant instance are not compatible Solr location datatype we need extra steps to run this project.

<ol>
  <li>Clone the project to your locale.</li>
  <li>Comment the below line in RestaurantService.java
    <br>
    <img src="https://github.com/elifnurafsar/xxx/assets/60623941/1c70fe3b-1fc1-44e8-9e7f-defb81aab00b" alt="line to be commented">
  </li>
  <li>Delete the data folder
    <br>
     <img src="https://github.com/elifnurafsar/xxx/assets/60623941/6655fe95-9f84-4d3a-9147-2e691dc9701d" alt="location of the data folder">
  </li>
  <li>Check SolrConfig and docker-compose.yaml</li>
  <li>Run <sub> docker-compose up -d </sub></li>
  <li>Run RestaurantAppApplication.java</li>
  <li>Create a restaurant data (so it can generate managed-schema.xml)
     <br>
     <img src="https://github.com/elifnurafsar/xxx/assets/60623941/c01742ca-4fca-4fb4-99b7-ec3ac3daf8d0" alt="Example Request">
  </li>
  <li>From new generated data folder go to managed-schema.xml and update restaurantLocation field
    <br>
     <img src="https://github.com/elifnurafsar/xxx/assets/60623941/43d09983-7b35-42a6-bb59-943b416a8b48" alt="Lines to be added">
  </li>
  <li>From Solr Admin Reload the core
    <br>
    <img src="https://github.com/elifnurafsar/xxx/assets/60623941/778608fb-090b-4bb1-8a13-ffe62aa60882" >
  </li>
  <li>Stop the Application</li>
  <li>Uncomment the line that given in step-2</li>
  <li>Run application</li>
</ol>

## Screenshot Examples

### Get All Restaurants

![getAllRestaaurants](https://github.com/elifnurafsar/xxx/assets/60623941/8e7bdc0b-06d1-4f07-b16e-fd08d76fa6e9)


## Get by ID

![search_by_id](https://github.com/elifnurafsar/xxx/assets/60623941/57e9ebf9-8725-41ad-b5fd-30f562175f97)

### Search By Name

![search_by_name](https://github.com/elifnurafsar/xxx/assets/60623941/a8c8e41c-fe7c-4088-b8c3-db25a2fa5d96)

## Delete Restaurant
![delete_restaurant](https://github.com/elifnurafsar/xxx/assets/60623941/0a5d67a0-3732-4d54-9a23-112a9748b92b)

## Get All After Delete
![result_delete](https://github.com/elifnurafsar/xxx/assets/60623941/1ba1eb47-d765-4bb4-a9ab-b04d8caa8760)

## Create Restaurant

 <img src="https://github.com/elifnurafsar/xxx/assets/60623941/c01742ca-4fca-4fb4-99b7-ec3ac3daf8d0" alt="Example Request">

## Update Restaurant

![updateRestaurant](https://github.com/elifnurafsar/xxx/assets/60623941/584fdfd8-00c5-4097-b775-7e9a7f73fc18)

## Result
![get_updated](https://github.com/elifnurafsar/xxx/assets/60623941/912ae8b5-412e-4ed4-922d-c1b8b1dd02a1)
