package com.example.marvelcharacterapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharacterRepository extends JpaRepository<MarvelCharacter, Long> {

    List<MarvelCharacter> findByUniverseIgnoreCase(String universe);

    List<MarvelCharacter> findByNameContainingIgnoreCase(String name);
}