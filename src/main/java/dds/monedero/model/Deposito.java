package dds.monedero.model;


import java.time.LocalDate;

class Deposito implements Tipo {

  public double calcularValor(Movimiento movimiento) {
    return  movimiento.getMonto();
  }

  public void agregarMovimiento(LocalDate fecha, Movimiento movimiento, Cuenta cuenta) {
    Movimiento deposito = new Movimiento(fecha, movimiento.getMonto(), new Deposito());
    cuenta.depositos.add(deposito);
  }
}

