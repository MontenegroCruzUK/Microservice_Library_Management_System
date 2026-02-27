package com.montenegro.genre.application.mapper;


import com.montenegro.genre.application.dto.GenreDTO;
import com.montenegro.genre.domain.model.Genre;
import com.montenegro.genre.infrastructure.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreMapper {

  private final GenreRepository genreRepository;

  public GenreDTO toDTO(Genre genre) {
    return toDTO(genre, false);
  }

  // Evita recursión infinita
  public GenreDTO toDTO(Genre genre, boolean skipChildren) {
    if (genre == null) {
      return null;
    }

    GenreDTO dto = GenreDTO.builder()
       .id(genre.getId())
       .code(genre.getCode())
       .name(genre.getName())
       .description(genre.getDescription())
       .displayOrder(genre.getDisplayOrder())
       .active(genre.getActive())
       .createdAt(genre.getCreatedAt())
       .updatedAt(genre.getUpdatedAt()) // corregido
       .build();

    if (genre.getParentGenre() != null) {
      dto.setParentGenreId(genre.getParentGenre()
         .getId());
      dto.setParentGenreName(genre.getParentGenre()
         .getName());
    }

    List<Genre> subGenres = genre.getSubGenres();

    if (!skipChildren && subGenres != null) {
      dto.setSubGenres(
         subGenres.stream()
            .filter(Genre::getActive)
            .map(sub -> toDTO(sub, true)) // evita recursión profunda
            .collect(Collectors.toList())
      );
    } else {
      dto.setSubGenres(Collections.emptyList());
    }

    return dto;
  }

  public Genre toEntity(GenreDTO genreDTO) {
    if (genreDTO == null) {
      return null;
    }

    Genre genre = Genre.builder()
       .code(genreDTO.getCode())
       .name(genreDTO.getName())
       .description(genreDTO.getDescription())
       .displayOrder(genreDTO.getDisplayOrder())
       .active(genreDTO.getActive() != null ? genreDTO.getActive() : true)
       .build();

    if (genreDTO.getParentGenreId() != null) {
      genreRepository.findById(genreDTO.getParentGenreId())
         .ifPresent(genre::setParentGenre);
    }
    return null;
  }

  public void updateEntryFromDTO(GenreDTO dto, Genre existingGenre) {
    if (dto == null || existingGenre == null) {
      return;
    }
    existingGenre.setCode(dto.getCode());
    existingGenre.setName(dto.getName());
    existingGenre.setDisplayOrder(dto.getDisplayOrder());
    existingGenre.setDisplayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0);
    if (dto.getActive() != null) {
      existingGenre.setActive(dto.getActive());
    }
    if (dto.getParentGenreId() != null) {
      genreRepository.findById(dto.getParentGenreId())
         .ifPresent(existingGenre::setParentGenre);
    }
  }

  public List<GenreDTO> toDTOList(List<Genre> genreList) {
    return genreList.stream()
       .map(genre -> toDTO(genre))
       .collect(Collectors.toList());
  }
}