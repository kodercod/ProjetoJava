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
public class Filho extends login{
    
    private int idFilho;
    private String descricao;
    private String situacao;
    
    public Filho(int idFilho, String descricao, String situacao,int idLogin, String nome, String usuario, String senha, Date dataNascimento,Relatorio relatorio) {
        super(idLogin, nome, usuario, senha, dataNascimento, relatorio);
        this.idFilho = idFilho;
        this.descricao = descricao;
        this.situacao = situacao;
    }
    public static Filho filhoVazio() throws ParseException{
        Relatorio oRelatorio = new Relatorio();
        Date dataNascimento = Conversao.dataAtual();
        Filho oFilho = new Filho(0,"","A",0,"","","",dataNascimento,oRelatorio);
        return oFilho;
    }


    
    public int getIdFilho() {
        return idFilho;
    }

    public void setIdFilho(int idFilho) {
        this.idFilho = idFilho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
