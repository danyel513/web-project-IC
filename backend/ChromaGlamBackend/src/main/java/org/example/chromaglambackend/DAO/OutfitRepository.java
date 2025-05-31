package org.example.chromaglambackend.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {
    // we will use the default findAll() method already implemented in JpaRepository
    Outfit findById(long item_id);
    Outfit save(Outfit outfit);
}
