package com.example.tp1PersistenciaJPA.entidades;

import com.example.tp1PersistenciaJPA.enumeraciones.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Producto")
public class Producto extends BaseEntidad{
    private Tipo tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;

    public void mostrarProducto (){
        System.out.println("-----");
        System.out.println("Denominación: "+denominacion);
        System.out.println("Producto "+getId());
        System.out.println("Tipo: "+tipo);
        System.out.println("Tiempo estimado en cocina: "+tiempoEstimadoCocina);
        System.out.println("Precio Venta: $"+precioVenta);
        System.out.println("Precio Compra: $"+precioCompra);
        System.out.println("Stock actual "+stockActual);
        System.out.println("Stock minimo: "+stockMinimo);
        System.out.println("Unidad de Medida: "+unidadMedida);
        System.out.println("Receta: "+receta);
    }
}
