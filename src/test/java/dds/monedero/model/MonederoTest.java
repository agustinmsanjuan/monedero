package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  void init() {
    cuenta = new Cuenta();
  }

  @Test
  void Poner() {
    cuenta.poner(1500);
  }

  @Test
  void UnMontoAIngresarNoPuedeSerNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.poner(-1500));
  }

  @Test
  void RealizarTresDepositosSeAgregaAlSaldo() {
    cuenta.poner(1500);
    cuenta.poner(456);
    cuenta.poner(1900);
    Assertions.assertEquals(cuenta.getSaldo(), 3856 );
  }

  @Test
  void DepositoSuperaLimiteDiario() { //solo pueden realizarse 3 por día
    assertThrows(MaximaCantidadDepositosException.class, () -> {
          cuenta.poner(1500);
          cuenta.poner(456);
          cuenta.poner(1900);
          cuenta.poner(245);
    });
  }

  @Test
  void DepositoSeAgregaADeposito() {
    cuenta.poner(1500);
    Assertions.assertEquals(cuenta.depositos.get(0).getMonto(), 1500);
  }

  @Test
  void ExtraccionSuperaSaldo() {
    assertThrows(SaldoMenorException.class, () -> {
          cuenta.setSaldo(90);
          cuenta.sacar(1001);
    });
  }

  @Test
  public void ExtraccionSuperaLimiteDiario() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> {
      cuenta.setSaldo(5000);
      cuenta.sacar(500);
      cuenta.sacar(700);
    });
  }

  @Test
  public void ExtraccionModificaElSaldo() {
    cuenta.setSaldo(5000);
    cuenta.sacar(500);
    Assertions.assertEquals(cuenta.getSaldo(), 4500);
  }

  @Test
  public void UnMontoAExtraerNoPuedeSerNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.sacar(-500));
  }

}