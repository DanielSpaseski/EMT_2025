package com.example.emt_lab.repository;

import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a")
    List<AuthorProjection> findAllByNameAndSurname();
}
