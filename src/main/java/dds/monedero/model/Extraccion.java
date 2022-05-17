package dds.monedero.model;

class Extraccion implements Tipo {

  public double calcularValor(Movimiento movimiento) {
    return -movimiento.getMonto();
  }
}
