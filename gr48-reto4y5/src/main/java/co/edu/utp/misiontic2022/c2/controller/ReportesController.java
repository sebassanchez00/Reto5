package co.edu.utp.misiontic2022.c2.controller;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.dao.ComprasDeLiderDao;
import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.model.dao.PagadoPorProyectoDao;
import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.model.vo.ProyectoBancoVo;

public class ReportesController {
    private ComprasDeLiderDao comprasDeLiderDao_;
    private PagadoPorProyectoDao pagadoPorProyectoDao_;
    private ProyectoBancoDao proyectoBancoDao_;

    public ReportesController() {
        comprasDeLiderDao_ = new ComprasDeLiderDao();
        pagadoPorProyectoDao_ = new PagadoPorProyectoDao();
        proyectoBancoDao_ = new ProyectoBancoDao();
    }

    public List<ComprasDeLiderVo> listarLideresQueMenosGastan() throws SQLException {
        return comprasDeLiderDao_.listarLideresQueMenosGastan();
    }

    public List<PagadoPorProyectoVo> listarTotalPagadoPorProyecto(Double limiteInferior) throws SQLException {
        return pagadoPorProyectoDao_.listarTotalPagadoPorProyectosSuperioresALimite(limiteInferior);
    }

    public List<ProyectoBancoVo> listarProyectosFinanciadosPorBanco(String nombreBanco) throws SQLException {
        return proyectoBancoDao_.listarProyectosFinanciadosPorBanco(nombreBanco);
    }
}