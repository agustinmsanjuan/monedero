package dds.monedero.model;


class Deposito implements Tipo {

  public double calcularValor(Movimiento movimiento) {
    return  movimiento.getMonto();
  }
}

