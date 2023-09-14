package com.example.tp1PersistenciaJPA.repositorios;

import com.example.tp1PersistenciaJPA.entidades.Cliente;
import com.example.tp1PersistenciaJPA.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends JpaRepository<Rubro,Long> {
}
