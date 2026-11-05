
package com.example.marvelcharacterapi;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MarvelCharacterPageController {

    private final MarvelCharacterService service;

    public MarvelCharacterPageController(MarvelCharacterService service) {
        this.service = service;
    }

    // Display all characters and allow searching
    @GetMapping("/")
    public String showGallery(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String universe,
            Model model) {

        List<MarvelCharacter> characters;

        if (name != null && !name.isBlank()) {
            characters = service.searchCharactersByName(name);
        } else if (universe != null && !universe.isBlank()) {
            characters = service.getCharactersByUniverse(universe);
        } else {
            characters = service.getAllCharacters();
        }

        model.addAttribute("characters", characters);
        model.addAttribute("name", name);

        return "index";
    }

    // Display character details
    @GetMapping("/characters/{id}")
    public String showDetails(
            @PathVariable Long id,
            Model model,
            HttpServletResponse response) {

        return service.getCharacterById(id)
                .map(character -> {
                    model.addAttribute("character", character);
                    return "details";
                })
                .orElseGet(() -> {
                    response.setStatus(HttpStatus.NOT_FOUND.value());
                    return "error";
                });
    }

    // Display Add Character form
    @GetMapping("/characters/new")
    public String showAddForm(Model model) {

        model.addAttribute("character", new MarvelCharacter());
        model.addAttribute("editing", false);

        return "character-form";
    }

    // Save a new character
    @PostMapping("/characters")
    public String addCharacter(
            @ModelAttribute MarvelCharacter character,
            RedirectAttributes redirectAttributes) {

        service.addCharacter(character);

        redirectAttributes.addFlashAttribute(
                "message", "Character added successfully!"
        );

        return "redirect:/";
    }

    // Display Edit Character form
    @GetMapping("/characters/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model,
            HttpServletResponse response) {

        return service.getCharacterById(id)
                .map(character -> {
                    model.addAttribute("character", character);
                    model.addAttribute("editing", true);
                    return "character-form";
                })
                .orElseGet(() -> {
                    response.setStatus(HttpStatus.NOT_FOUND.value());
                    return "error";
                });
    }

    // Save changes to an existing character
    @PostMapping("/characters/{id}")
    public String updateCharacter(
            @PathVariable Long id,
            @ModelAttribute MarvelCharacter character,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response) {

        if (service.updateCharacter(id, character).isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "error";
        }

        redirectAttributes.addFlashAttribute(
                "message", "Character updated successfully!"
        );

        return "redirect:/characters/" + id;
    }

    // Delete a character
    @PostMapping("/characters/{id}/delete")
    public String deleteCharacter(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (service.deleteCharacter(id)) {
            redirectAttributes.addFlashAttribute(
                    "message", "Character deleted successfully!"
            );
        }

        return "redirect:/";
    }

    // Display the About page
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
}