package com.example.emt_lab.repository;

import com.example.emt_lab.model.views.AuthorsPerCountryView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsPerCountryViewRepository extends JpaRepository<AuthorsPerCountryView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.authors_per_country", nativeQuery = true)
    void refreshMaterializedView();

}
