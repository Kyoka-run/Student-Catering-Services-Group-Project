package com.studentCatering.repository;

import java.util.List;

import com.studentCatering.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    List<Item> findBySectionId(int sectionId);
    
    List<Item> findByStatus(Item.ItemStatus status);
    
    List<Item> findBySectionIdAndStatus(int sectionId, Item.ItemStatus status);
    
    @Query(value = "SELECT * FROM items i WHERE i.name LIKE %:keyword%", nativeQuery = true)
    List<Item> searchItems(@Param("keyword") String keyword);
}