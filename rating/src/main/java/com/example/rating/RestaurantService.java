package com.example.rating;

import java.util.HashMap;
import java.util.List;

public interface RestaurantService {
    Restaurant saveRestorent(Restaurant restorent);

    List<Restaurant> getAllRestaurant();

    Restaurant updateRestaurant(Restaurant restaurant, int id);

    void deleteRestaurant(int id);
    Restaurant getAvg(Restaurant restaurant, int id);

    HashMap<String,Double> getTop10();
}
