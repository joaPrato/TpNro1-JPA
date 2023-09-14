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
@Table(name = "Cliente")
public class Cliente extends BaseEntidad {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi) {

        domicilios.add(domi);
    }

    public void mostrarDomicilios() {
        System.out.println("-----------------------------");
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println(" ");
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero());
        }
    }
    public void agregarPedidos (Pedido pedi){
        pedidos.add(pedi);
    }
    public void mostrarPedidos (){
        System.out.println("-----------------------------");
        System.out.println("Pedidos de  "+nombre+" "+apellido+":");
        for (Pedido pedido : pedidos){
            pedido.mostrarPedido();
            pedido.mostrarDetallePedido();
            if (pedido.getFactura()!=null){
                pedido.getFactura().mostrarFacutra();
            }

        }
    }
}
