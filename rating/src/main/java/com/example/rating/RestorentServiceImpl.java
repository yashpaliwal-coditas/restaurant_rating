package com.example.rating;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestorentServiceImpl implements RestaurantService {
    RestorentRepository restorentRepository;
    public RestorentServiceImpl(RestorentRepository restorentRepository) {
        this.restorentRepository = restorentRepository;
    }
    public Restaurant saveRestorent(Restaurant restaurant)  {

        return restorentRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restorentRepository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, int id) {
        Restaurant existingRes = restorentRepository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Restaurant", "id", id)
        );
        existingRes.setCleanliness(restaurant.getCleanliness());
        existingRes.setAmbience(restaurant.getAmbience());
        existingRes.setService(restaurant.getService());
        existingRes.setFood(restaurant.getFood());

        restorentRepository.save(existingRes);

        return existingRes;
    }

    @Override
    public void deleteRestaurant(int id) {
        Restaurant restaurant = restorentRepository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Restaurant","id",id)
        );

        restorentRepository.deleteById(id);
    }

    @Override
    public Restaurant getAvg(Restaurant restaurant, int id) {
        Restaurant existingRes = restorentRepository.findById(id).orElseThrow(
                ()->new ResourseNotFoundException("Restaurant","id",id)
        );
        return existingRes;
    }

    @Override
    public HashMap<String, Double> getTop10() {
        String hotelNameList[]  = {"Hayat","HHI", "ITC","redison", "novo"};
        HashMap<String, Double> rating= new LinkedHashMap<String, Double>();
        for(int index=0; index<hotelNameList.length;index++){
            List<Restaurant> ratingPerRestorent = restorentRepository.findByName(hotelNameList[index]);
            double ratingPerHotel=0.0;
            for(Restaurant restaurant:ratingPerRestorent){
                ratingPerHotel+=restaurant.getAvg();
            }
            ratingPerHotel/=ratingPerRestorent.size();
            rating.put(hotelNameList[index],ratingPerHotel);
        }
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(rating.entrySet());

        // sort the list by value using a comparator
        Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> e1, Map.Entry<String, Double> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        HashMap<String, Double> sortedHotelList = new LinkedHashMap<>();

        // iterate over the sorted list of entries and put them into the LinkedHashMap
        for (Map.Entry<String, Double> entry : entryList) {
            sortedHotelList.put(entry.getKey(), entry.getValue());
        }
       return sortedHotelList;
    }
}
