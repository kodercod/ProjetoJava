/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.model;

import br.com.fatec.projetointer2019_lib.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Luis Silva
 */
public class Pai extends login{
    private int idPai;
    private String cpf;
    private String observacao;
    private String situacao;
    
    public Pai(int idPai, String cpf, String observacao, String situacao, int idLogin, String nome, String usuario, String senha, Date dataNascimento, Relatorio relatorio) {
        super(idLogin, nome, usuario, senha, dataNascimento, relatorio);
        this.idPai = idPai;
        this.cpf = cpf;
        this.observacao = observacao;
        this.situacao = situacao;
    }
     public static Pai paiVazio() throws ParseException{
        Relatorio oRelatorio = new Relatorio();
        Date dataNascimento = Conversao.dataAtual();
        Pai oPai = new Pai(0,"","","A",0,"","","",dataNascimento,oRelatorio);
        return oPai;
    }

    public int getIdPai() {
        return idPai;
    }

    public void setIdPai(int idPai) {
        this.idPai = idPai;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
