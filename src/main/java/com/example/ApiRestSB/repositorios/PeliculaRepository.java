package com.example.ApiRestSB.repositorios;

import com.example.ApiRestSB.modelos.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository <Pelicula, Long>{

}
