package org.example.chromaglambackend.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {

    // method to return all outfits from the database
    // List<Outfit> findAll();
}
