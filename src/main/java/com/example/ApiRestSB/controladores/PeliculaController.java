package com.example.ApiRestSB.controladores;

import com.example.ApiRestSB.modelos.Pelicula;
import com.example.ApiRestSB.repositorios.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PeliculaController {

    PeliculaRepository repositorio;

    public PeliculaController(PeliculaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/api/peliculas")
    public List<Pelicula> obtenerPeliculas() {
        return repositorio.findAll();
    }

    @GetMapping("/api/pelicula/{id}")
    public ResponseEntity<Pelicula> obtenerPelicula(@PathVariable Long id) {
        Optional<Pelicula> opt = repositorio.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(opt.get());
        }
    }


    @PostMapping("/api/peliculas")
    public ResponseEntity<Pelicula> guardarPelicula(@RequestBody Pelicula pelicula) {
        if (pelicula.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(pelicula);
        return ResponseEntity.ok(pelicula);
    }

    @PutMapping("/api/peliculas")
    public ResponseEntity<Pelicula> actualizarPelicula(@RequestBody Pelicula pelicula) {
        if (pelicula.getId() == null || !repositorio.existsById(pelicula.getId())) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(pelicula);
        return ResponseEntity.ok(pelicula);
    }

    @DeleteMapping("/api/pelicula/{id}")
    public ResponseEntity<Pelicula> borrarPelicula(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
