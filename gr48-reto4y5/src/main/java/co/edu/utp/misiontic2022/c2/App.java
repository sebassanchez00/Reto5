package co.edu.utp.misiontic2022.c2;

import co.edu.utp.misiontic2022.c2.view.FormConsulta;
import co.edu.utp.misiontic2022.c2.view.ReportesView;

public class App {
    public static void main(String[] args) {
        //ReportesView rv = new ReportesView();
        // rv.lideresQueMenosGastan();
        // rv.proyectosFinanciadosPorBanco("Banco de Occidente");
        // rv.totalPagadoPorProyectosSuperioresALimite(50000d);
        var view = new FormConsulta();
        view.setVisible(true);

        
    }
}