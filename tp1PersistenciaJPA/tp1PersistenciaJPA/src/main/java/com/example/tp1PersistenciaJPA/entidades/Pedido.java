package com.example.tp1PersistenciaJPA.entidades;

import com.example.tp1PersistenciaJPA.enumeraciones.Estado;
import com.example.tp1PersistenciaJPA.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Pedido")
public class Pedido extends BaseEntidad{
    private Estado estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private TipoEnvio tipoEnvio;
    private double total;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos =new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detallePed) {

        detallePedidos.add(detallePed);
    }


    public void mostrarPedido (){
        System.out.println("---");
        System.out.println("PEDIDO "+getId());
        System.out.println("Estado: "+estado);
        System.out.println("Fecha: "+fecha);
        System.out.println("Tipo envio: "+tipoEnvio);
        System.out.println("Total: "+total);
    }
    public void mostrarDetallePedido(){
        System.out.println(" ");
        System.out.println("DETALLE");
        for (DetallePedido detallePedido: detallePedidos){
            System.out.println("----");
            System.out.println("Producto: "+detallePedido.getProducto().getDenominacion());
            System.out.println("Cantidad: "+detallePedido.getCantidad());
            System.out.println("SubTotal: "+detallePedido.getSubtotal());
        }
    }



}
