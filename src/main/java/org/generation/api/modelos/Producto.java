package org.generation.api.modelos;

// POJO - Plain Old JAVA Object

import jakarta.persistence.*;

@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="productoId", unique=true, nullable = false)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="imagen", nullable = false)
    private String imagen;

    @Column(name="precio", nullable = false)
    private Double precio;

    public Producto(String nombre, String descripcion, String imagen, Double precio) { // INSERT INTO tabla (id, nombre) VALUES
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
    }// constructor Producto

    public Producto(){}// constructor vacío (requisito JPA)

    public Long getId(){
        return id;
    }// getId

    public String getNombre() {
        return nombre;
    }// getNombre

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }// setNombre

    public String getDescripcion() {
        return descripcion;
    }// getDescripcion

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }// setDescripcion

    public String getImagen() {
        return imagen;
    }// getImagen

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }// setImagen

    public Double getPrecio() {
        return precio;
    }// getPrecio

    public void setPrecio(Double precio) {
        this.precio = precio;
    }// setPrecio

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio=" + precio +
                '}';
    }// toString
}// class Producto
