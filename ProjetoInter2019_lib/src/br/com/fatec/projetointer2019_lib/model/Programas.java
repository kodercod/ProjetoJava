/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.model;





/**
 *
 * @author jeffersonpasserini
 */
public class Programas {
    
    private int idPrograma;
    private String nomePrograma;
    private String dataAcesso;
    private String horaAcesso;

    

    public Programas() {
        this.idPrograma= 0;
        this.nomePrograma= "";
        this.dataAcesso = "";
        this.horaAcesso = "";

    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }
    
    public String getDataAcesso(){
        return dataAcesso;
    }
    public void setDataAcesso(String dataAcesso){
        this.dataAcesso = dataAcesso;
    }
    
    public String getHoraAcesso(){
        return horaAcesso;
    }
    public void setHoraAcesso(String horaAcesso){
        this.horaAcesso = horaAcesso;
    }
    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

}
