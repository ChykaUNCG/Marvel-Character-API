package com.example.marvelcharacterapi;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MarvelCharacterService {

    private final MarvelCharacterRepository repository;

    public MarvelCharacterService(MarvelCharacterRepository repository) {
        this.repository = repository;
    }

    public List<MarvelCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public Optional<MarvelCharacter> getCharacterById(Long id) {
        return repository.findById(id);
    }

    public MarvelCharacter addCharacter(MarvelCharacter character) {
        return repository.save(character);
    }

    public Optional<MarvelCharacter> updateCharacter(Long id, MarvelCharacter updatedCharacter) {
        return repository.findById(id).map(character -> {
            character.setName(updatedCharacter.getName());
            character.setDescription(updatedCharacter.getDescription());
            character.setUniverse(updatedCharacter.getUniverse());
            character.setRole(updatedCharacter.getRole());
            return repository.save(character);
        });
    }

    public boolean deleteCharacter(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public List<MarvelCharacter> getCharactersByUniverse(String universe) {
        return repository.findByUniverseIgnoreCase(universe);
    }

    public List<MarvelCharacter> searchCharactersByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}