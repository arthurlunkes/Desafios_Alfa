package dev.arthurlunkes.challengetwo.controllers;

import dev.arthurlunkes.challengetwo.dtos.DishDTO;
import dev.arthurlunkes.challengetwo.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity findByDish(@RequestHeader HttpHeaders headers) {
        if(!headers.containsKey("dish")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Header dish está faltando!");
        };

        return this.recipeService.findByDish(headers);

    }

    @GetMapping("/all")
    public ResponseEntity findAllDishes() {
        return this.recipeService.findAllDishes();
    }

}
