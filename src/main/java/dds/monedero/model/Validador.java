package dds.monedero.model;

import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;

public class Validador {
  public static boolean esNegativo(double monto) {
    return monto < 0;
  }

  public static void validarMontoNegativo(double monto) {
    if (esNegativo(monto)) {
      throw new MontoNegativoException(monto + ": el monto a ingresar debe ser un valor positivo");
    }
  }

  public static void validarMontoDeExtraccion(double saldo, double monto) {
    if (saldo < monto) {
      throw new SaldoMenorException("No puede sacar mas de " + saldo + " $");
    }
  }

  public static void validarLimiteDeExtraccionDiaria(double montoExtraidoHoy, double monto) {
    double limite = 1000 - montoExtraidoHoy;
    if (monto > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
          + " diarios, límite: " + limite);
    }
  }
}
