package co.edu.utp.misiontic2022.c2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class PagadoPorProyectoDao {
    public List<PagadoPorProyectoVo> listarTotalPagadoPorProyectosSuperioresALimite(Double limiteInferior) throws SQLException
    {
        List<PagadoPorProyectoVo> Lrespuesta = new ArrayList<PagadoPorProyectoVo>();

        // Alistemiento objetos para el query
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        // Alistamiento query
        String query = "SELECT ID_Proyecto, VALOR "
            + "FROM ("
            + "SELECT C.ID_Proyecto, SUM(C.Cantidad * MC.Precio_Unidad) AS VALOR "
            + "FROM Compra C "
            + "INNER JOIN MaterialConstruccion MC ON C.ID_MaterialConstruccion  = MC.ID_MaterialConstruccion "
            + "WHERE C.Pagado = 'Si' "
            + "GROUP BY C.ID_Proyecto "
            + "ORDER BY VALOR DESC) "
            + "WHERE VALOR > %s";
            query = String.format(query, limiteInferior.toString());

        try {
            // Asignación objetos del query
            stmt = conn.prepareStatement(query);
            rset = stmt.executeQuery();

            // Llenado del value object
            while (rset.next()) {
                var vo = new PagadoPorProyectoVo();
                vo.setID_(rset.getInt("ID_Proyecto"));
                vo.setValor_(rset.getDouble("VALOR"));
                Lrespuesta.add(vo);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }

        return Lrespuesta;
    }
}
