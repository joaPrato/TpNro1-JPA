package com.example.tp1PersistenciaJPA;

import com.example.tp1PersistenciaJPA.entidades.*;
import com.example.tp1PersistenciaJPA.enumeraciones.Estado;
import com.example.tp1PersistenciaJPA.enumeraciones.FormaPago;
import com.example.tp1PersistenciaJPA.enumeraciones.Tipo;
import com.example.tp1PersistenciaJPA.enumeraciones.TipoEnvio;
import com.example.tp1PersistenciaJPA.repositorios.ClienteRepository;
import com.example.tp1PersistenciaJPA.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Tp1PersistenciaJpaApplication {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1PersistenciaJpaApplication.class, args);
		System.out.println("Estoy Funcionando");

	}

	@Bean
	CommandLineRunner init (ClienteRepository clienteRepo,RubroRepository rubroRepo){
		return args -> {
			System.out.println("--------Funcionando--------");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fecha1 = "2023-02-10";
			String fecha2 = "2023-09-13";
			//Parseo String en un objeto Date
			Date fechaUno = formatoFecha.parse(fecha1);
			Date fechaDos = formatoFecha.parse(fecha2);

			//Creacion de objetos Rubro
			Rubro rubro1=Rubro.builder()
					.denominacion("Hamburgesas")
					.build();
			Rubro rubro2=Rubro.builder()
					.denominacion("Ingredientes")
					.build();
			//Creacion de objetos Producto
			Producto producto1=Producto.builder()
					.tipo(Tipo.manufacturado)
					.tiempoEstimadoCocina(20)
					.denominacion("HambuergesaXXL")
					.precioVenta(3500)
					.precioCompra(1450)
					.receta("Paso1: poner carne en la plancha.....")
					.build();
			Producto producto2=Producto.builder()
					.tipo(Tipo.manufacturado)
					.tiempoEstimadoCocina(25)
					.denominacion("Magnifica")
					.precioVenta(4000)
					.receta("Paso1: poner carne en la plancha.....")
					.build();
			Producto producto3=Producto.builder()
					.tipo(Tipo.insumo)
					.denominacion("Tomate")
					.precioCompra(20)
					.stockActual(25)
					.stockMinimo(15)
					.unidadMedida("unidad")
					.build();
			Producto producto4=Producto.builder()
					.tipo(Tipo.insumo)
					.denominacion("Mayonesa")
					.precioCompra(300)
					.stockActual(800)
					.stockMinimo(600)
					.unidadMedida("gramos")
					.build();
			//Asociamos al Rubro el Producto
			rubro1.agregarProductoAlRubro(producto1);
			rubro1.agregarProductoAlRubro(producto2);
			rubro2.agregarProductoAlRubro(producto3);
			rubro2.agregarProductoAlRubro(producto4);



			//Creacion de objetos Cliente y Domicilio
			Cliente cliente1 = Cliente.builder()
					.nombre("Carlos")
					.apellido("Sanchez")
					.telefono("2614856248")
					.email("carlorSanchez@gmail.com")
					.build();
			Domicilio domicilio1 =Domicilio.builder()
					.calle("Florida")
					.numero("6598")
					.localidad("Maipu")
					.build();
			Domicilio domicilio2 =Domicilio.builder()
					.calle("Rio Juramento")
					.numero("1242")
					.localidad("Guaymallén")
					.build();
			//Asignamos al cliente1 los domicilios
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			//Creacion de objetos Pedido
			Pedido pedido1 =Pedido.builder()
					.estado(Estado.entregado)
					.fecha(fechaUno)
					.tipoEnvio(TipoEnvio.delivery)
					.total(11000)
					.build();
			Pedido pedido2 =Pedido.builder()
					.estado(Estado.preparacion)
					.fecha(fechaDos)
					.tipoEnvio(TipoEnvio.delivery)
					.total(8000)
					.build();
			//Agregamos al cliente1 los pedidos
			cliente1.agregarPedidos(pedido1);
			cliente1.agregarPedidos(pedido2);
			//Creacion de obetos Factura
			Factura factura1=Factura.builder()
					.numero(7524)
					.fecha(fechaUno)
					.formaPago(FormaPago.mercadoPago)
					.total(11000)
					.build();
			Factura factura2=Factura.builder()
					.numero(8541)
					.fecha(fechaDos)
					.formaPago(FormaPago.mercadoPago)
					.descuento(1000)
					.total(7000)
					.build();
			//Asociamos el pedido su factura
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			//Creacion de objeto DetallePedido
			DetallePedido detallePedido1=DetallePedido.builder()
					.cantidad(2)
					.subtotal(7000)
					.build();
			DetallePedido detallePedido2=DetallePedido.builder()
					.cantidad(1)
					.subtotal(4000)
					.build();
			DetallePedido detallePedido3=DetallePedido.builder()
					.cantidad(2)
					.subtotal(8000)
					.build();
			//Asociacion de Pedido Con Detalle
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);

			//Asociar el los productos al detalle
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto2);

			//Guardamos los rubros con sus productos
			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);
			//Guardamos a cliente y todos los objetos aosciados por el cascadeo
			clienteRepository.save(cliente1);


			Cliente clienteRecuperado= clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado!=null){
				System.out.println("Cliente");
				System.out.println("Nomrbe: "+clienteRecuperado.getNombre());
				System.out.println("Apellido: "+clienteRecuperado.getApellido());
				System.out.println("Telefono: "+clienteRecuperado.getTelefono());
				System.out.println("Email: "+clienteRecuperado.getEmail());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();
			}
			Rubro rubroRecuperado1 = rubroRepository.findById(rubro1.getId()).orElse(null);
			if(rubroRecuperado1!=null){
				rubroRecuperado1.mostrarProductosRubro();
			}
			Rubro rubroRecuperado2 = rubroRepository.findById(rubro2.getId()).orElse(null);
			if(rubroRecuperado2!=null){
				rubroRecuperado2.mostrarProductosRubro();
			}















		};
	}

}
