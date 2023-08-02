package dev.arthurlunkes.challengetwo.dtos;

import java.util.List;
import java.util.Optional;

public record DishDTO(int count, List<RecipeDTO> recipes, String error) {
}
