package ge.ibsu.demo.services;

import ge.ibsu.demo.entities.Film;
import ge.ibsu.demo.repositories.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    public Page<Film> searchFilms(String q, Integer year, String language, int page, int size) {
        return filmRepository.search(q, year, language, PageRequest.of(page, size));
    }
}
