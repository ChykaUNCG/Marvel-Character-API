package com.example.marvelcharacterapi;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class MarvelCharacterController {

    private final MarvelCharacterService service;

    public MarvelCharacterController(MarvelCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MarvelCharacter>> getAllCharacters(
            @RequestParam(required = false) String universe,
            @RequestParam(required = false) String name) {

        if (universe != null) {
            return ResponseEntity.ok(service.getCharactersByUniverse(universe));
        }

        if (name != null) {
            return ResponseEntity.ok(service.searchCharactersByName(name));
        }

        return ResponseEntity.ok(service.getAllCharacters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarvelCharacter> getCharacterById(@PathVariable Long id) {
        return service.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarvelCharacter> addCharacter(
            @Valid @RequestBody MarvelCharacter character) {

        MarvelCharacter savedCharacter = service.addCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarvelCharacter> updateCharacter(
            @PathVariable Long id,
            @Valid @RequestBody MarvelCharacter character) {

        return service.updateCharacter(id, character)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {

        if (!service.deleteCharacter(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}