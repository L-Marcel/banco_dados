package entities;

import java.text.DecimalFormat;
import java.sql.*;
import util.*;

public class Departamento {
  int numero = -1;
  String nome = "";
  long cpfGerente = 0;
  Date dataIniGerente = Date.valueOf("0001-01-01");

  public Departamento() {
  }

  public Departamento(int numero, String nome, String cpfGerente, String dataIniGerente) {
    this.numero = numero;
    this.nome = nome;
    this.cpfGerente = Converter.cpfStringToLong(cpfGerente);
    this.dataIniGerente = Date.valueOf(dataIniGerente);
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpfGerente() {
    return new DecimalFormat("00000000000").format(cpfGerente);
  }

  public void setCpfGerente(long cpfGerente) {
    this.cpfGerente = cpfGerente;
  }

  public void setCpfGerente(String cpfGerente) {
    this.cpfGerente = Converter.cpfStringToLong(cpfGerente);
  }

  public Date getDataIniGerente() {
    return dataIniGerente;
  }

  public void setDataIniGenrente(Date dataIniGerente) {
    this.dataIniGerente = dataIniGerente;
  }

  @Override
  public String toString() {
    return Render.renderLine("Departamento") + "\n" + "Número: " + this.numero + "\n" + "Nome: " + this.nome + " "
      + "\n" + "CPF do gerente: " + this.getCpfGerente() + "\n" + "Data de inicio do gerente: "
      + this.getDataIniGerente() + "\n" + Render.renderLine() + "\n";
  }

  public String toString(String action) {
    return Render.renderLine("Departamento " + action) + "\n" + "Número: " + this.numero + "\n" + "Nome: " + this.nome
      + " " + "\n" + "CPF do gerente: " + this.getCpfGerente() + "\n" + "Data de inicio do gerente: "
      + this.getDataIniGerente() + "\n" + Render.renderLine() + "\n";
  }
}
