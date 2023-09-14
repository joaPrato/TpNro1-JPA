package com.example.tp1PersistenciaJPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Rubro")
public class Rubro extends BaseEntidad{
    private String denominacion;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="rubro_id")
    @Builder.Default
    private List<Producto> productos =new ArrayList<>();

    public void agregarProductoAlRubro(Producto prod) {

        productos.add(prod);
    }
    public void mostrarProductosRubro(){
        System.out.println("-----------------------------");
        System.out.println("Lista de productos del rubro "+denominacion);
        for (Producto producto:productos){
            producto.mostrarProducto();
        }
    }

}

