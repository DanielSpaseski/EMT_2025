package com.example.emt_lab.repository;

import com.example.emt_lab.model.projections.UserProjection;
import com.example.emt_lab.model.domain.User;
import com.example.emt_lab.model.enumerations.Role;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @NonNull
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"bookWishlist"}
    )
    @Query("SELECT u from User u")
    List<User> findAll();
    List<UserProjection> findAllProjectedBy();
}
