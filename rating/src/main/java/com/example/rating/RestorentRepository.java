package com.example.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface RestorentRepository extends JpaRepository<Restaurant,Integer> {
    List<Restaurant> findByName(String name);
}
