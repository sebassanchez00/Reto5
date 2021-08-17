package co.edu.utp.misiontic2022.c2.model.vo;

public class ComprasDeLiderVo {
    private String nombreApellidoLider_;
    private Double valor_;
    
    public ComprasDeLiderVo()
    { }

    public ComprasDeLiderVo(String nombreApellidoLider, Double valor) {
        this.nombreApellidoLider_ = nombreApellidoLider;
        this.valor_ = valor;
    }

    public String getNombreApellidoLider_() {
        return nombreApellidoLider_;
    }
    public void setNombreApellidoLider_(String nombreApellidoLider_) {
        this.nombreApellidoLider_ = nombreApellidoLider_;
    }
    public Double getValor_() {
        return valor_;
    }
    public void setValor_(Double valor_) {
        this.valor_ = valor_;
    }
}
