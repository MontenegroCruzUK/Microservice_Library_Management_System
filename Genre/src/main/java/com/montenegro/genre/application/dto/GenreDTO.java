package com.montenegro.genre.application.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {

  private Long id;

  @NotBlank(message = "Genre Code is Mandatory")
  private String code;

  @NotBlank(message = "Genre name is mandatory")
  private String name;

  @Size(max = 500, message = "Description must not exceed 500 characters")
  private String description;

  @Min(value = 0, message = "Display order cannot be negative")
  private Integer displayOrder = 0;

  private Boolean active;

  private Long parentGenreId;

  private String parentGenreName;

  private List<GenreDTO> subGenres;

  private Long bookCount;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}

