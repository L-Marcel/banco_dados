package entities;

import java.text.DecimalFormat;
import java.sql.*;
import util.*;

public class Departamento {

  int numero = -1;
  String nome = "";
  long cpf_gerente = 0;
  Date data_ini_gerente = Date.valueOf("0001-01-01");

  public Departamento() {
  }

  public Departamento(int numero, String nome, String cpf_gerente, String data_ini_gerente) {
    this.numero = numero;
    this.nome = nome;
    this.cpf_gerente = Converter.cpfStringToLong(cpf_gerente);
    this.data_ini_gerente = Date.valueOf(data_ini_gerente);
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

  public String getCpf_gerente() {
    return new DecimalFormat("00000000000").format(cpf_gerente);
  }

  public void setCpf_gerente(long cpf_gerente) {
    this.cpf_gerente = cpf_gerente;
  }

  public void setCpf_gerente(String cpf_gerente) {
    this.cpf_gerente = Converter.cpfStringToLong(cpf_gerente);
  }

  public Date getData_ini_gerente() {
    return data_ini_gerente;
  }

  public void setData_ini_gente(Date data_ini_gerente) {
    this.data_ini_gerente = data_ini_gerente;
  }

  @Override
  public String toString() {
    return Render.renderLine("Departamento") + "\n" + "Número: " + this.numero + "\n" + "Nome: " + this.nome + " "
        + "\n" + "CPF do gerente: " + this.getCpf_gerente() + "\n" + "Data de inicio do gerente: "
        + this.getData_ini_gerente() + "\n" + "\n" + Render.renderLine() + "\n";
  }

  public String toString(String action) {
    return Render.renderLine("Departamento " + action) + "\n" + "Número: " + this.numero + "\n" + "Nome: " + this.nome
        + " " + "\n" + "CPF do gerente: " + this.getCpf_gerente() + "\n" + "Data de inicio do gerente: "
        + this.getData_ini_gerente() + "\n" + "\n" + Render.renderLine() + "\n";
  }
}
