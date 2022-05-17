package dds.monedero.model;

import java.time.LocalDate;

class Extraccion implements Tipo {

  public double calcularValor(Movimiento movimiento) {
    return -movimiento.getMonto();
  }

  public void agregarMovimiento(LocalDate fecha, Movimiento movimiento, Cuenta cuenta) {
    Movimiento deposito = new Movimiento(fecha, movimiento.getMonto(), new Extraccion());
    cuenta.extracciones.add(deposito);
  }
}
