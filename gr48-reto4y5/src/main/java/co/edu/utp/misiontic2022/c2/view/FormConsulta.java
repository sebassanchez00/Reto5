package co.edu.utp.misiontic2022.c2.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import co.edu.utp.misiontic2022.c2.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.model.vo.ProyectoBancoVo;

public class FormConsulta extends JFrame{

    private ReportesController controller;
    private JTable  tablaComprasLider;
    private JTable  tablaPagadoProyecto;
    private JTable  tablaProyectoBanco;

    public FormConsulta() {
        initUI();
        setLocationRelativeTo(null);
        controller = new ReportesController();
    }

    private void initUI()
    {
        setTitle("Proyectos Construcción - MisionTIC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);

        //Tabbed Panels
        var tbd = new JTabbedPane();
        tbd.setForeground(new Color(30,0,255));
        getContentPane().add(tbd, BorderLayout.CENTER);

        //JTables
        tablaComprasLider = new JTable();
        tablaPagadoProyecto =  new JTable();
        tablaProyectoBanco = new JTable();

        //Panel Consulta Compras Lider
        var panelExterior1 = new JPanel();
        panelExterior1.setLayout(new BorderLayout());
        tbd.addTab("Consulta Compras Lider",panelExterior1);

        var btnConsulta = new JButton("Consultar");
        btnConsulta.addActionListener( e -> consultarComprasLider());

        var panelEntrada1 = new JPanel();
        panelEntrada1.add(btnConsulta);   
        panelExterior1.add(panelEntrada1, BorderLayout.PAGE_START);

        tablaComprasLider = new JTable();
        panelExterior1.add(new JScrollPane(tablaComprasLider), BorderLayout.CENTER);

        //Panel Consulta Financiación Banco
        var panelExterior2 = new JPanel();
        panelExterior2.setLayout(new BorderLayout());
        tbd.addTab("Consulta Financiación Banco",panelExterior2);
     
        var panelEntrada2 = new JPanel();

        panelEntrada2.add(new JLabel("Banco"));
        JTextField jtf1 = new JTextField(15);
        panelEntrada2.add(jtf1);

        var btnConsulta2 = new JButton("Consultar");
        btnConsulta2.addActionListener( e -> consultarFinanciacionBanco(jtf1.getText()));
        panelEntrada2.add(btnConsulta2);

        panelExterior2.add(panelEntrada2, BorderLayout.PAGE_START);

        tablaProyectoBanco = new JTable();
        panelExterior2.add(new JScrollPane(tablaProyectoBanco), BorderLayout.CENTER);

        //Panel Consulta Presupuesto Proyecto
        var panelExterior3 = new JPanel();
        panelExterior3.setLayout(new BorderLayout());
        tbd.addTab("Consulta Presupuesto Proyecto",panelExterior3);

        var panelEntrada3 = new JPanel();

        panelEntrada3.add(new JLabel("Límite inferior"));
        JTextField jtf2 = new JTextField(15);
        panelEntrada3.add(jtf2);

        var btnConsulta3 = new JButton("Consultar");
        btnConsulta3.addActionListener( e -> consultarPresupuestoProyecto(Double.valueOf(jtf2.getText())));
        panelEntrada3.add(btnConsulta3);

        panelExterior3.add(panelEntrada3, BorderLayout.PAGE_START);

        tablaPagadoProyecto = new JTable();
        panelExterior3.add(new JScrollPane(tablaPagadoProyecto), BorderLayout.CENTER);
    }

    private void consultarComprasLider()
    {
        try{
            var listaSQL = controller.listarLideresQueMenosGastan();
            var tableModel = new ComprasLiderTableModel();
            tableModel.setData(listaSQL);
            tablaComprasLider.setModel(tableModel);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarFinanciacionBanco(String banco)
    {
        try{
            var listaSQL = controller.listarProyectosFinanciadosPorBanco(banco);
            var tableModel = new FinanciacionBancoTableModel();
            tableModel.setData(listaSQL);
            tablaProyectoBanco.setModel(tableModel);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarPresupuestoProyecto(Double presupuesto)
    {
        try{
            var listaSQL = controller.listarTotalPagadoPorProyecto(presupuesto);
            var tableModel = new PresupuestoProyectoTableModel();
            tableModel.setData(listaSQL);
            tablaPagadoProyecto.setModel(tableModel);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComprasLiderTableModel extends AbstractTableModel{
        private List<ComprasDeLiderVo> data;

        public void setData(List<ComprasDeLiderVo> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column)
        {
            switch(column)
            {
                case 0:
                return "LIDER";
                case 1:
                return "VALOR";
            }
            return super.getColumnName(column);
        }

        @Override
        public  Class<?> getColumnClass(int columnIndex)
        {
            switch(columnIndex)
            {
                case 0:
                return String.class;
                case 1:
                return Double.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public int getRowCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch(columnIndex)
            {
                case 0 :
                return project.getNombreApellidoLider_();
                case 1 :
                return project.getValor_();
            } 
            return null;
        }       
    }

    private class FinanciacionBancoTableModel extends AbstractTableModel{
        private List<ProyectoBancoVo> data;

        public void setData(List<ProyectoBancoVo> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column)
        {
            switch(column)
            {
                case 0:
                return "ID";
                case 1:
                return "Constructora";
                case 2:
                return "Ciudad";
                case 3:
                return "Clasificacion";
                case 4:
                return "Estrato";
                case 5:
                return "LIDER";
            }
            return super.getColumnName(column);
        }

        @Override
        public  Class<?> getColumnClass(int columnIndex)
        {
            switch(columnIndex)
            {
                case 0:
                return Integer.class;
                case 1:
                return String.class;
                case 2:
                return String.class;
                case 3:
                return String.class;
                case 4:
                return Integer.class;
                case 5:
                return String.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public int getRowCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch(columnIndex)
            {
                case 0 :
                return project.getID_();
                case 1 :
                return project.getConstructora_();
                case 2 :
                return project.getCiudad_();
                case 3 :
                return project.getClasificacion_();
                case 4 :
                return project.getEstrato_();
                case 5 :
                return project.getLider_();

            } 
            return null;
        }       
    }

    private class PresupuestoProyectoTableModel extends AbstractTableModel{
        
        private List<PagadoPorProyectoVo> data;

        public void setData(List<PagadoPorProyectoVo> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column)
        {
            switch(column)
            {
                case 0:
                return "ID PROYECTO";
                case 1:
                return "VALOR";
            }
            return super.getColumnName(column);
        }

        @Override
        public  Class<?> getColumnClass(int columnIndex)
        {
            switch(columnIndex)
            {
                case 0:
                return Integer.class;
                case 1:
                return Double.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public int getRowCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch(columnIndex)
            {
                case 0 :
                return project.getID_();
                case 1 :
                return project.getValor_();
            } 
            return null;
        }
    }           
}
