
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