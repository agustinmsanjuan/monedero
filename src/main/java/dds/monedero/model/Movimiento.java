package dds.monedero.model;

import java.time.LocalDate;

// Si hacía una clase abstracta Movimiento, iba a tener una lista para depositos y otra para
// extracciones.
public class Movimiento {
  private final LocalDate fecha;
  // Nota: En ningún lenguaje de programación usen jamás doubles
  // (es decir, números con punto flotante) para modelar dinero en el mundo real.
  // En su lugar siempre usen numeros de precision arbitraria o punto fijo,
  // como BigDecimal en Java y similares.
  // De todas formas, NO es necesario modificar ésto como parte de este ejercicio. 
  private final double monto;
  Tipo tipo;

  public Movimiento(LocalDate fecha, double monto, Tipo tipo) {
    this.fecha = fecha;
    this.monto = monto;
    this.tipo = tipo;
  }

  public double getMonto() {
    return monto;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public boolean esDeLaFecha(LocalDate fecha) {
    return this.fecha.equals(fecha);
  }

  double calcularValor(Cuenta cuenta, Tipo tipo) {
    return cuenta.getSaldo() + tipo.calcularValor(this);
  }

  void agregateA(Cuenta cuenta) {
    cuenta.setSaldo(calcularValor(cuenta, tipo));
    cuenta.agregarMovimiento(fecha, monto);
  }

}
