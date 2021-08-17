package co.edu.utp.misiontic2022.c2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class ComprasDeLiderDao {
    public List<ComprasDeLiderVo> listarLideresQueMenosGastan() throws SQLException
    {
        List<ComprasDeLiderVo> Lrespuesta = new ArrayList<ComprasDeLiderVo>();

        // Alistemiento objetos para el query
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        // Alistamiento query
        String query = "SELECT LID.Nombre || ' ' || LID.Primer_Apellido || ' ' || LID.Segundo_Apellido AS LIDER, AUX.VALOR "
        + "FROM("
        + "SELECT ID_Lider, SUM(VALOR) AS VALOR "
        + "FROM("
        + "SELECT P.ID_Lider , C.ID_Proyecto,  SUM(C.Cantidad * MC.Precio_Unidad) AS VALOR "
        + "FROM Compra C "
        + "INNER JOIN MaterialConstruccion MC ON C.ID_MaterialConstruccion = MC.ID_MaterialConstruccion "
        + "INNER JOIN Proyecto P ON C.ID_Proyecto = P.ID_Proyecto "
        + "GROUP BY C.ID_Proyecto "
        + "ORDER BY VALOR DESC) "
        + "GROUP BY ID_Lider "
        + "ORDER BY VALOR ASC"
        + ") AUX "
        + "INNER JOIN Lider LID ON LID.ID_Lider = AUX.ID_Lider "
        + "LIMIT 10;";

        try {
            // Asignación objetos del query
            stmt = conn.prepareStatement(query);
            rset = stmt.executeQuery();

            // Llenado del value object
            while (rset.next()) {
                var vo = new ComprasDeLiderVo();
                vo.setNombreApellidoLider_(rset.getString("LIDER"));
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