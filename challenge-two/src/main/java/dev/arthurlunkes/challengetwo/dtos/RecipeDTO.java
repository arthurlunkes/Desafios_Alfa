package dev.arthurlunkes.challengetwo.dtos;

public record RecipeDTO(String publisher, String title, String source_url, String recipe_id,
                        String image_url, double social_rank, String publisher_url) {
}
