/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.model;

import java.util.Date;

/**
 *
 * @author Luis Silva
 */
public class login {
    private int idLogin;
    private String nome;
    private String usuario;
    private String senha;
    private Date dataNascimento;
    private Relatorio relatorio;
    
    public login(int idLogin,  String nome, String usuario, String senha, Date dataNascimento, Relatorio relatorio){
        this.idLogin = idLogin;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.relatorio = relatorio;
    }
    
    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    
    
}
