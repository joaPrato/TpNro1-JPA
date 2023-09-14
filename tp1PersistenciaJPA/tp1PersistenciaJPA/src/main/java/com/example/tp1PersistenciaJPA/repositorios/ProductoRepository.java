package com.example.tp1PersistenciaJPA.repositorios;

import com.example.tp1PersistenciaJPA.entidades.Cliente;
import com.example.tp1PersistenciaJPA.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
