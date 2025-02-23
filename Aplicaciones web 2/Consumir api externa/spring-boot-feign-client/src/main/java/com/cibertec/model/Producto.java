package com.cibertec.model;

import lombok.Data;

@Data
public class Producto {

    private Long id;
    private String descripcion;
    private Long cantidad;
    private double precio;
    private String enable;
}
