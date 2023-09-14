package com.example.tp1PersistenciaJPA.entidades;

import com.example.tp1PersistenciaJPA.enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Factura")
public class Factura extends BaseEntidad{
    private int numero;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double descuento;
    private FormaPago formaPago;
    private int total;

    public void mostrarFacutra(){
        System.out.println("-----------------------------");
        System.out.println("Datos de Factura nro "+numero);
        System.out.println("Fecha: "+fecha);
        if(descuento!=0.0d){
            System.out.println("Descuento: "+descuento);
        }
        System.out.println("Forma de pago: "+formaPago);
        System.out.println("Total: "+total);
    }


}
