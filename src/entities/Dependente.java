package entities;

import java.text.DecimalFormat;
import java.sql.*;
import util.*;

public class Dependente {

  String nome = "";
  long cpfEmpregado = 0;
  Date dataNasc = Date.valueOf("0001-01-01");
  String sexo = "";
  String parentesco = "";

  public Dependente() {
  }

  public Dependente(String nome, String cpfEmpregado, String dataNasc, String sexo, String parentesco) {
    this.nome = nome;
    this.cpfEmpregado = Converter.cpfStringToLong(cpfEmpregado);
    this.dataNasc = Date.valueOf(dataNasc);
    this.sexo = sexo;
    this.parentesco = parentesco;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpfEmpregado() {
    return new DecimalFormat("00000000000").format(cpfEmpregado);
  }

  public void setCpfEmpregado(long cpfEmpregado) {
    this.cpfEmpregado = cpfEmpregado;
  }

  public Date getDataNasc() {
    return dataNasc;
  }

  public void setDataNasc(Date dataNasc) {
    this.dataNasc = dataNasc;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getParentesco() {
    return parentesco;
  }

  public void setParentesco(String parentesco) {
    this.parentesco = parentesco;
  }

  @Override
  public String toString() {
    return Render.renderLine("Dependente") + "\n" + "Nome: " + this.nome
      + " " + "\n" + "CPF do empregado: " + this.getCpfEmpregado() + "\n" + "Data de nascimento do dependente: "
      + this.getDataNasc() + "\n" + Render.renderLine() + "\n" + "Sexo: " + this.sexo
      + " " + "\n" + "Prentesco: " + this.parentesco + "\n"  + Render.renderLine() + "\n";
  }

  public String toString(String action) {
    return Render.renderLine("Dependente " + action) + "\n" + "Nome: " + this.nome
      + " " + "\n" + "CPF do empregado: " + this.getCpfEmpregado() + "\n" + "Data de nascimento do dependente: "
      + this.getDataNasc() + "\n" + Render.renderLine() + "\n" + "Sexo: " + this.sexo
      + " " + "\n" + "Prentesco: " + this.parentesco + "\n"  + Render.renderLine() + "\n";
  }
}
