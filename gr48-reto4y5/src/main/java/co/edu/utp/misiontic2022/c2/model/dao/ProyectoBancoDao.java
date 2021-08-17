package co.edu.utp.misiontic2022.c2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class ProyectoBancoDao {
    public List<ProyectoBancoVo> listarProyectosFinanciadosPorBanco(String nombreBanco) throws SQLException {
        List<ProyectoBancoVo> Lrespuesta = new ArrayList<ProyectoBancoVo>();

        // Alistemiento objetos para el query
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        // Alistamiento query
        String query = "SELECT p.ID_Proyecto AS ID, p.Constructora , p.Ciudad, p.Clasificacion , t.Estrato, l.Nombre || ' ' || l.Primer_Apellido  || ' ' || l.Segundo_Apellido as LIDER"
                + " from Proyecto p" 
                + " inner join Tipo t on p.ID_Tipo = t.ID_Tipo"
                + " inner join Lider l on p.ID_Lider = l.ID_Lider" 
                + " where Banco_Vinculado = '%s'"
                + " Order by p.Fecha_Inicio DESC ,  p.Ciudad ASC , p.Constructora";
        query = String.format(query, nombreBanco);

        try {
            // Asignación objetos del query
            stmt = conn.prepareStatement(query);
            rset = stmt.executeQuery();

            // Llenado del value object
            while (rset.next()) {
                var vo = new ProyectoBancoVo();
                vo.setCiudad_(rset.getString("Ciudad"));
                vo.setClasificacion_(rset.getString("Clasificacion"));
                vo.setConstructora_(rset.getString("Constructora"));
                vo.setEstrato_(rset.getInt("Estrato"));
                vo.setID_(rset.getInt("ID"));
                vo.setLider_(rset.getString("LIDER"));
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
