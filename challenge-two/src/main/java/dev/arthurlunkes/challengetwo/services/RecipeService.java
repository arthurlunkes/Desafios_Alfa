package dev.arthurlunkes.challengetwo.services;

import dev.arthurlunkes.challengetwo.dtos.DishDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeService {

    private final String endpoint = "https://forkify-api.herokuapp.com";
    private final List<String> dishes = new ArrayList<>(Arrays.asList(
            "carrot", "broccoli", "asparagus", "cauliflower", "corn", "cucumber", "green pepper",
            "lettuce", "mushrooms", "onion", "potato", "pumpkin", "red pepper", "tomato",
            "beetroot", "brussel sprouts", "peas", "zucchini", "radish", "sweet potato",
            "artichoke", "leek", "cabbage", "celery", "chili", "garlic", "basil", "coriander",
            "parsley", "dill", "rosemary", "oregano", "cinnamon", "saffron", "green bean",
            "bean", "chickpea", "lentil", "apple", "apricot", "avocado", "banana", "blackberry",
            "blackcurrant", "blueberry", "boysenberry", "cherry", "coconut", "fig", "grape",
            "grapefruit", "kiwifruit", "lemon", "lime", "lychee", "mandarin", "mango", "melon",
            "nectarine", "orange", "papaya", "passion fruit", "peach", "pear", "pineapple", "plum",
            "pomegranate", "quince", "raspberry", "strawberry", "watermelon", "salad", "pizza",
            "pasta", "popcorn", "lobster", "steak", "bbq", "pudding", "hamburger", "pie", "cake",
            "sausage", "tacos", "kebab", "poutine", "seafood", "chips", "fries", "masala", "paella",
            "som tam", "chicken", "toast", "marzipan", "tofu", "ketchup", "hummus", "chili",
            "maple syrup", "parma ham", "fajitas", "champ", "lasagna", "poke", "chocolate", "croissant",
            "arepas", "bunny chow", "pierogi", "donuts", "rendang", "sushi", "ice cream", "duck",
            "curry", "beef", "goat", "lamb", "turkey", "pork", "fish", "crab", "bacon", "ham",
            "pepperoni", "salami", "ribs"
        ));

    public ResponseEntity findByDish(HttpHeaders headers){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DishDTO> response;

        try {
            response = restTemplate.getForEntity(this.endpoint
                + "/api/search?q=" + headers.get("dish").get(0), DishDTO.class);
        } catch (HttpClientErrorException e) {
            if(e.getMessage().contains("The q parameter is missing")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Header dish está em branco!");
            } else if(e.getMessage().contains("Couldn't find recipe with that name.")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem receita com esse prato!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }

        }

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());

    }

    public ResponseEntity findAllDishes() {
        return ResponseEntity.status(HttpStatus.OK).body("Lista de pratos disponíveis: " + dishes);
    }
}