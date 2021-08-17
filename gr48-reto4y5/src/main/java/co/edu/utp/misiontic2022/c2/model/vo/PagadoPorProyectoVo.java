package co.edu.utp.misiontic2022.c2.model.vo;
public class PagadoPorProyectoVo {
    private int ID_;
    private Double valor_;
    
    public PagadoPorProyectoVo()
    {}
    
    public PagadoPorProyectoVo(int iD, Double valor) {
        ID_ = iD;
        this.valor_ = valor;
    }

    public int getID_() {
        return ID_;
    }
    public void setID_(int iD_) {
        ID_ = iD_;
    }
    public Double getValor_() {
        return valor_;
    }
    public void setValor_(Double valor_) {
        this.valor_ = valor_;
    }
}
