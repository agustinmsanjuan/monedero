package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cuenta {

  private double saldo = 0;
  List<Movimiento> depositos = new ArrayList<>();
  List<Movimiento> extracciones = new ArrayList<>();
  public Cuenta() {
    saldo = 0;
  }

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }

  public void poner(double monto) {
    Validador.validarMontoNegativo(monto);
    if (depositos.size() >= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }
    new Movimiento(LocalDate.now(), monto, new Deposito()).agregateA(this);
  }

  public void sacar(double monto) {
    Validador.validarMontoNegativo(monto);
    Validador.validarMontoDeExtraccion(saldo, monto);
    double montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    Validador.validarLimiteDeExtraccionDiaria(montoExtraidoHoy, monto);

    new Movimiento(LocalDate.now(), monto, new Extraccion()).agregateA(this); // aca también repite
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return extracciones.stream()
        .filter(movimiento -> movimiento.getFecha().equals(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}
