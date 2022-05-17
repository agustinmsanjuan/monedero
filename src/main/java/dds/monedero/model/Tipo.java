package dds.monedero.model;

import java.time.LocalDate;

public interface Tipo {
    double calcularValor(Movimiento movimiento);
    void agregarMovimiento(LocalDate fecha, Movimiento movimiento, Cuenta cuenta);
}

