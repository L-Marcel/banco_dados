package src;

import java.text.DecimalFormat;
import java.sql.*;
import src.util.*;

public class Empregado {
  long cpf = 0;
  String pnome = "";
  String unome = "";
  Date dataNasc = Date.valueOf("0001-01-01");
  String endereco = "";
  Double salario = 0.0;
  String sexo = "";
  int numeroDep = -1;
  String cpfSupervisor = null;

  public Empregado(String cpf, String pnome, String unome, String dataNasc, String endereco, Double salario,
      String sexo, int numeroDep, String cpfSupervisor) {
    this.cpf = Converter.cpfStringToLong(cpf);
    this.pnome = pnome;
    this.unome = unome;
    this.dataNasc = Date.valueOf(dataNasc);
    this.endereco = endereco;
    this.salario = salario;
    this.sexo = sexo;
    this.numeroDep = numeroDep;
    this.cpfSupervisor = Converter.cpfString(cpfSupervisor);
  }

  public Empregado(String cpf, String pnome, String unome, String dataNasc, String endereco, Double salario,
      String sexo, int numeroDep) {
    this.cpf = Converter.cpfStringToLong(cpf);
    this.pnome = pnome;
    this.unome = unome;
    this.dataNasc = Date.valueOf(dataNasc);
    this.endereco = endereco;
    this.salario = salario;
    this.sexo = sexo;
    this.numeroDep = numeroDep;
  }

  public String getCpf() {
    return new DecimalFormat("00000000000").format(cpf);
  }

  public void setCpf(long cpf) {
    this.cpf = cpf;
  }

  public String getPnome() {
    return pnome;
  }

  public void setPnome(String pnome) {
    this.pnome = pnome;
  }

  public String getUnome() {
    return unome;
  }

  public void setUnome(String unome) {
    this.unome = unome;
  }

  public Date getDataNasc() {
    return dataNasc;
  }

  public void setDataNasc(Date dataNasc) {
    this.dataNasc = dataNasc;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public Double getSalario() {
    return salario;
  }

  public void setSalario(Double salario) {
    this.salario = salario;
  }

  public String getSexo() {
    return sexo;
  }

  public String getSexo(boolean altFormat) {
    if (altFormat) {
      switch (this.sexo.toLowerCase()) {
      case ("f"):
        return "Feminino";
      case ("m"):
        return "Masculino";
      case ("o"):
        return "Outro";
      default:
        return "Outro";
      }
    } else {
      return getSexo();
    }
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public int getNumeroDep() {
    return numeroDep;
  }

  public void setNumeroDep(int numeroDep) {
    this.numeroDep = numeroDep;
  }

  public String getCpfSupervisor() {
    return cpfSupervisor;
  }

  public void setCpfSupervisor(String cpfSupervisor) {
    this.cpfSupervisor = cpfSupervisor;
  }

  @Override
  public String toString() {
    return Render.renderLine("Empregado") + "\n" + "CPF: " + this.getCpf() + "\n" + "Nome completo: " + this.pnome + " "
        + this.unome + "\n" + "Salário: R$ " + this.salario + "\n" + "Data de nascimento: " + this.dataNasc + "\n"
        + "Endereço: " + this.endereco + "\n" + "Sexo: " + this.getSexo(true) + "\n" + Render.renderLine() + "\n"
        + "Supervisor: " + this.cpfSupervisor + "\n" + "Departamento: " + this.numeroDep + "\n" + Render.renderLine()
        + "\n";
  }

  public String toString(String action) {
    return Render.renderLine("Empregado " + action) + "\n" + "CPF: " + this.getCpf() + "\n" + "Nome completo: "
        + this.pnome + " " + this.unome + "\n" + "Salário: R$ " + this.salario + "\n" + "Data de nascimento: "
        + this.dataNasc + "\n" + "Endereço: " + this.endereco + "\n" + "Sexo: " + this.getSexo(true) + "\n"
        + Render.renderLine() + "\n" + "Supervisor: " + this.cpfSupervisor + "\n" + "Departamento: " + this.numeroDep
        + "\n" + Render.renderLine() + "\n";
  }
}
