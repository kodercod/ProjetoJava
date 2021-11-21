/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.model;

/**
 *
 * @author Luis Silva
 */
public class Internet {
    private int idInternet;
    private String nomePagina;
    private String enderecoPagina;
    private String dataAcesso;
    private String horaAcesso;

    

    public Internet() {
        this.idInternet= 0;
        this.nomePagina= "";
        this.enderecoPagina = "";
        this.dataAcesso = "";
        this.horaAcesso = "";

    }

    public int getIdInternet() {
        return idInternet;
    }

    public void setIdInternet(int idInternet) {
        this.idInternet = idInternet;
    }

    public String getNomePagina() {
        return nomePagina;
    }

    public void setNomePagina(String nomePagina) {
        this.nomePagina = nomePagina;
    }

    public String getEnderecoPagina() {
        return enderecoPagina;
    }

    public void setEnderecoPagina(String enderecoPagina) {
        this.enderecoPagina = enderecoPagina;
    }

    public String getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(String dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public String getHoraAcesso() {
        return horaAcesso;
    }

    public void setHoraAcesso(String horaAcesso) {
        this.horaAcesso = horaAcesso;
    }
}
