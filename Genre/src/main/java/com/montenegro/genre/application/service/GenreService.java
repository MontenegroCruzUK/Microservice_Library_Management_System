package com.montenegro.genre.application.service;

import com.montenegro.genre.application.dto.GenreDTO;
import com.montenegro.genre.application.exception.GenreException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {

  GenreDTO createGenre(GenreDTO genreDTO);

  List<GenreDTO> getAllGenres();

  GenreDTO getGenreById(Long genreId) throws GenreException;

  GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException;

  void deleteGenre(Long genreId) throws GenreException;       // Soft delete (si aplica)

  void hardDeleteGenre(Long genreId) throws GenreException;   // Eliminación física

  List<GenreDTO> getAllActiveGenresWithSubGenres();

  List<GenreDTO> getTopLevelGenres();

  Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);

  long getTotalActiveGenres();

  long getBookCountByGenre(Long genreId);
}
