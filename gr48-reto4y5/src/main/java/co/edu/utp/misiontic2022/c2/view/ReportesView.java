package co.edu.utp.misiontic2022.c2.view;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.model.vo.ProyectoBancoVo;

public class ReportesView {

    private ReportesController controller;

    public ReportesView() {
        controller = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        var respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void proyectosFinanciadosPorBanco(String banco) {
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO " + repitaCaracter('=', 37));
        if (banco != null && !banco.isBlank()) {
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", "ID", "CONSTRUCTORA", "CIUDAD",
                    "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));            
        }

            List<ProyectoBancoVo> lista = null;
            try {
                lista = controller.listarProyectosFinanciadosPorBanco(banco);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for(ProyectoBancoVo vo: lista)
            {
                System.out.println(
                String.format("%3d %-25s %-20s %-15s %7d %-30s", 
                vo.getID_(), 
                vo.getConstructora_(), 
                vo.getCiudad_(), 
                vo.getClasificacion_(), 
                vo.getEstrato_(), 
                vo.getLider_())
                );
            }
    }

    public void totalPagadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL PAGADO POR PROYECTO " + repitaCaracter('=', 1));
        if (limiteInferior != null) {
            System.out.println(String.format("%2s %15s", "ID", "VALOR  "));
            System.out.println(repitaCaracter('-', 29));
            
            List<PagadoPorProyectoVo> lista2 = null;
            try {
                lista2 = controller.listarTotalPagadoPorProyecto(limiteInferior);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for(PagadoPorProyectoVo vo: lista2)
            {
                System.out.println(
                String.format("%3d %,15.1f", 
                vo.getID_(), 
                vo.getValor_())
                );  
            }
        }
    }

    public void lideresQueMenosGastan() {
        System.out.println(repitaCaracter('=', 5) + " 10 LIDERES MENOS COMPRADORES " + repitaCaracter('=', 6));
        System.out.println(String.format("%-24s %15s", "LIDER", "VALOR  "));
        System.out.println(repitaCaracter('-', 41));
        
        List<ComprasDeLiderVo> lista3 = null;
        try {
            lista3 = controller.listarLideresQueMenosGastan();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(ComprasDeLiderVo aux:lista3)
        {
            System.out.println(
                String.format("%-25s %,15.1f", 
                aux.getNombreApellidoLider_(), 
                aux.getValor_())
            );
        }
    }
}