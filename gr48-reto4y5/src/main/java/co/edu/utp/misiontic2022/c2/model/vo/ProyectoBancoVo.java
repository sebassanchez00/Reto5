package co.edu.utp.misiontic2022.c2.model.vo;
public class ProyectoBancoVo {
    private int ID_;
    private String constructora_;
    private String ciudad_;
    private String clasificacion_;
    private int estrato_;
    private String lider_;

    public ProyectoBancoVo()
    {
        
    }
    
    public ProyectoBancoVo(int iD, String constructora, String ciudad, String clasificacion, int estrato,
            String lider) {
        ID_ = iD;
        this.constructora_ = constructora;
        this.ciudad_ = ciudad;
        this.clasificacion_ = clasificacion;
        this.estrato_ = estrato;
        this.lider_ = lider;
    }
    
    public int getID_() {
        return ID_;
    }
    public void setID_(int iD_) {
        ID_ = iD_;
    }
    public String getConstructora_() {
        return constructora_;
    }
    public void setConstructora_(String constructora_) {
        this.constructora_ = constructora_;
    }
    public String getCiudad_() {
        return ciudad_;
    }
    public void setCiudad_(String ciudad_) {
        this.ciudad_ = ciudad_;
    }
    public String getClasificacion_() {
        return clasificacion_;
    }
    public void setClasificacion_(String clasificacion_) {
        this.clasificacion_ = clasificacion_;
    }
    public int getEstrato_() {
        return estrato_;
    }
    public void setEstrato_(int estrato_) {
        this.estrato_ = estrato_;
    }
    public String getLider_() {
        return lider_;
    }
    public void setLider_(String lider_) {
        this.lider_ = lider_;
    }
}
