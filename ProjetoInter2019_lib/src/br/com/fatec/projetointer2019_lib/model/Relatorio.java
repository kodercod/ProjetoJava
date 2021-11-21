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
public class Relatorio {
    private int idRelatorio;
    private String dataRelatorio;
    private String horaRelatorio;
    private Programas programa;
    private Internet internet;

    

    public Relatorio() {
        this.idRelatorio= 0;
        this.dataRelatorio= "";
        this.horaRelatorio = "";
        Programas oProgramas = new Programas();
        Internet oInternet = new Internet();
        this.programa = oProgramas;
        this.internet = oInternet;
    }
     public Relatorio(int idRelatorio, String dataRelatorio, String horaRelatorio, Programas programa, Internet internet) {
        this.idRelatorio = idRelatorio;
        this.dataRelatorio = dataRelatorio;
        this.horaRelatorio = horaRelatorio;
        this.programa = programa;
        this.internet = internet;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }
    
    public String getDataRelatorio(){
        return dataRelatorio;
    }
    public void setDataRelatorio(String dataRelatorio){
        this.dataRelatorio = dataRelatorio;
    }
    
    public String getHoraRelatorio(){
        return horaRelatorio;
    }
    public void setHoraRelatorio(String horaRelatorio){
        this.horaRelatorio = horaRelatorio;
    }
    public Programas getProgramas(){
        return programa;
    }
    public void setProgramas(Programas programa){
        this.programa = programa;
    }
    public Internet getInternet(){
        return internet;
    }
    public void setInternet(Internet internet){
        this.internet = internet;
    }
}
