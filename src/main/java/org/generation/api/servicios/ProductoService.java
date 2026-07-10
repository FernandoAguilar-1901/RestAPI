package org.generation.api.servicios;

import org.generation.api.modelos.Producto;
import org.generation.api.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService( ProductoRepository repository ){
        this.productoRepository = repository;
    }// constructor ProductoService


    public List<Producto> getProductos() {
        return productoRepository.findAll(); // SELECT * FROM producto;
    }// getProductos

    public Producto getProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("El producto con el id [" + id + "] no existe") );
    }// getProducto

    public Producto deleteProducto(Long id) {
        Producto producto = null;

        if( productoRepository.existsById(id) ){
            producto = productoRepository.findById(id).get();
            productoRepository.deleteById(id);
        }// if

        return producto;
    }// deleteProducto

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }// crearProducto

    public Producto actualizarProducto(Long id, String nombre, String descripcion, String imagen, Double precio) {
        Producto producto = null;

        if( productoRepository.existsById(id) ){
            Producto p = productoRepository.findById(id).get();

            if(nombre!=null) p.setNombre(nombre);
            if(descripcion!=null) p.setDescripcion(descripcion);
            if(imagen!=null) p.setImagen(imagen);
            if(precio!=null) p.setPrecio(precio);

            producto = productoRepository.save(p);
        }// if

        return producto;
    }// actualizarProducto

}// class ProductoService
