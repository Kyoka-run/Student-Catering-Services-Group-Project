package com.studentCatering.repository;

import java.util.List;

import com.studentCatering.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    @Query(value = "SELECT * FROM section i WHERE i.name LIKE %:keyword% OR i.description LIKE %:keyword%", nativeQuery = true)
    List<Section> searchSections(@Param("keyword") String keyword);
}