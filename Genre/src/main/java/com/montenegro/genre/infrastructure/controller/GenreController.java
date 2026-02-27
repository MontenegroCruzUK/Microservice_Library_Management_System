package com.montenegro.genre.infrastructure.controller;



import com.montenegro.genre.application.dto.ApiResponse;
import com.montenegro.genre.application.dto.GenreDTO;
import com.montenegro.genre.application.exception.GenreException;
import com.montenegro.genre.application.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

  private final GenreService genreService;

  @PostMapping
  public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) {
    GenreDTO createdGenre = genreService.createGenre(genreDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
       .body(createdGenre);
  }

  @GetMapping
  public ResponseEntity<List<GenreDTO>> getAllGenres() {
    return ResponseEntity.ok(genreService.getAllGenres());
  }

  @GetMapping("/{genreId}")
  public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long genreId) throws GenreException {
    return ResponseEntity.ok(genreService.getGenreById(genreId));
  }

  @PutMapping("/{genreId}")
  public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long genreId,
     @RequestBody GenreDTO genreDTO) throws GenreException {
    return ResponseEntity.ok(genreService.updateGenre(genreId, genreDTO));
  }

  @DeleteMapping("/{genreId}")
  public ResponseEntity<ApiResponse> deleteGenre(@PathVariable Long genreId) throws GenreException {
    genreService.deleteGenre(genreId);
    return ResponseEntity.ok(new ApiResponse(true, "Genre deleted - soft delete", LocalDateTime.now()));
  }

  @DeleteMapping("/{genreId}/hard")
  public ResponseEntity<ApiResponse> hardDeleteGenre(@PathVariable Long genreId) throws GenreException {
    genreService.hardDeleteGenre(genreId);
    return ResponseEntity.ok(new ApiResponse(true, "Genre deleted - hard delete", LocalDateTime.now()));
  }

  @GetMapping("/top-level")
  public ResponseEntity<List<GenreDTO>> getTopLevelGenre() {
    return ResponseEntity.ok(genreService.getTopLevelGenres());
  }

  @GetMapping("/count")
  public ResponseEntity<Long> getTotalActiveGenres() {
    return ResponseEntity.ok(genreService.getTotalActiveGenres());
  }

  @GetMapping("/{id}/book-count")
  public ResponseEntity<Long> getBookCountByGenders(@PathVariable Long id) {
    return ResponseEntity.ok(genreService.getBookCountByGenre(id));
  }
}
