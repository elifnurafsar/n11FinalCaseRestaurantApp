
# ResaurantsApp

RestaurantApp is a Spring Boot-powered web application that seamlessly integrates with Solr for efficient restaurant management.

> [!IMPORTANT]  
> Proje dosyalarının son commit'i 17 Martta yapılmasının ardından repository public'e alındığında README içerisindeki görsellerin benim dışımdaki diğer kullanıcılara gözükmediği tespit edilmiştir. Yapılan uyarı ardından sadece README dosyası güncellenip görseller tekrar yüklenerek son commit 18 Martta atılmıştır. Bu committe proje dosyalarında herhangi bir değişim olmamıştır. README'nin önceki sürümünde görsellerin 17 Mart tarihinde benim kullanıcım tarafından sorunsuz bir şekilde görüntülenebildiği projemin sunum videosunda gösterilmiştir.

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
    <img src="https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/50016c50-41ae-4e08-a34e-7464accabe80" alt="line to be commented">
  </li>
  <li>Delete the data folder
    <br>
     <img src="https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/795fc53d-67e8-4250-b418-1e3838e2c407" alt="location of the data folder">
  </li>
  <li>Check SolrConfig and docker-compose.yaml</li>
  <li>Run <sub> docker-compose up -d </sub></li>
  <li>Run RestaurantAppApplication.java</li>
  <li>Create a restaurant data (so it can generate managed-schema.xml)
     <br>
     <img src="https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/caecae32-0acf-45dd-8e93-5dc99187d7fa" alt="Example Request">
  </li>
  <li>From new generated data folder go to managed-schema.xml and update restaurantLocation field
    <br>
     <img src="https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/c9f9867b-ec5b-41e6-9bb5-654e9eabe798" alt="Lines to be added">
  </li>
  <li>From Solr Admin Reload the core
    <br>
    <img src="https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/b511c228-1577-4b5a-8641-833329cad1e8" >
  </li>
  <li>Stop the Application</li>
  <li>Uncomment the line that given in step-2</li>
  <li>Run application</li>
</ol>

## Screenshot Examples

### Get All Restaurants


![get_all_restaurants](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/3d33e699-b872-4c13-8abb-bff2ad30d6e6)


## Get by ID

![get_by_id](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/ae9b2279-32ce-44a9-a521-ec274333899e)


### Search By Name

![search_by_name](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/1648fe66-81ec-41ff-8129-e2581cfdfe5b)

## Delete Restaurant

![delete_restaurant](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/cd3988af-c442-4b40-8790-1ef740272bd6)

## Get All After Delete

![get_all_after_delete](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/a62989c3-5a06-4673-b9c0-d0b29223d2b5)

## Create Restaurant

![create_restaurant](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/81f26951-a6a1-4d8e-bff0-fee51f60015a)


## Update Restaurant

![update_restaurant](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/ba8d6c57-076a-4bcc-b01a-3d1ba2138e3b)


## Result

![result](https://github.com/elifnurafsar/n11FinalCaseRestaurantApp/assets/60623941/80b17b4a-9120-400e-b415-8ea194b49b8f)

