/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Actividad;
import Modelo.Monitor;
import Modelo.Socio;
import Vista.VistaActividad;
import Vista.VistaMonitores;
import Vista.VistaInscripciones;
import Vista.VistaSocios;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UtilTablas 
{
    public DefaultTableModel modeloTablaMonitores = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false;
        }
    };
    
    public DefaultTableModel modeloTablaSocios = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false;
        }
    };
    
    public DefaultTableModel modeloTablaActividad = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false;
        }
    };
    
    public DefaultTableModel modeloTablaRealiza = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false;
        }
    };
    
    public void dibujarTablaMonitores(VistaMonitores vMonitor)
    {
        vMonitor.jTablaMonitores.setModel(modeloTablaMonitores);
        
        String[] columnasTabla = {"Código", "Nombre", "DNI",
            "Teléfono", "Correo", "Fecha de Incorporación", "Nick"};
        modeloTablaMonitores.setColumnIdentifiers(columnasTabla);
        
        vMonitor.jTablaMonitores.getTableHeader().setResizingAllowed(false);
        vMonitor.jTablaMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vMonitor.jTablaMonitores.getColumnModel().getColumn(0).setPreferredWidth(40);
        vMonitor.jTablaMonitores.getColumnModel().getColumn(1).setPreferredWidth(240);
        vMonitor.jTablaMonitores.getColumnModel().getColumn(2).setPreferredWidth(70);
        vMonitor.jTablaMonitores.getColumnModel().getColumn(3).setPreferredWidth(70);
        vMonitor.jTablaMonitores.getColumnModel().getColumn(4).setPreferredWidth(200);
        vMonitor.jTablaMonitores.getColumnModel().getColumn(5).setPreferredWidth(150);
        vMonitor.jTablaMonitores.getColumnModel().getColumn(6).setPreferredWidth(60);
    }
    
    public void rellenarTablaMonitores(ArrayList<Monitor> monitores)
    {
        Object[] fila = new Object[7];
        int numRegistros = monitores.size();
        for(int i = 0; i < numRegistros; i++)
        {
            fila[0] = monitores.get(i).getCodmonitor();
            fila[1] = monitores.get(i).getNombre();
            fila[2] = monitores.get(i).getDni();
            fila[3] = monitores.get(i).getTelefono();
            fila[4] = monitores.get(i).getCorreo();
            fila[5] = monitores.get(i).getFechaentrada();
            fila[6] = monitores.get(i).getNick();
            modeloTablaMonitores.addRow(fila);
        }
    }
    
    public void vaciarTablaMonitores()
    {
        while(modeloTablaMonitores.getRowCount() > 0)
            modeloTablaMonitores.removeRow(0);
    }
    
    public void EliminarMonitor(int posTabla)
    {
        modeloTablaMonitores.removeRow(posTabla);
    }
    
    
    //------------ SOCIOS ------------
    public void dibujarTablaSocios(VistaSocios vSocio)
    {
        vSocio.jTablaSocios.setModel(modeloTablaSocios);
        
        String[] columnasTabla = {"Numero Socio", "Nombre", "DNI",
            "Fecha de Nacimiento","Teléfono", "Correo", "Fecha de Incorporación", "Categoria"};
        modeloTablaSocios.setColumnIdentifiers(columnasTabla);
        
        vSocio.jTablaSocios.getTableHeader().setResizingAllowed(false);
        vSocio.jTablaSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vSocio.jTablaSocios.getColumnModel().getColumn(0).setPreferredWidth(120);
        vSocio.jTablaSocios.getColumnModel().getColumn(1).setPreferredWidth(200);
        vSocio.jTablaSocios.getColumnModel().getColumn(2).setPreferredWidth(120);
        vSocio.jTablaSocios.getColumnModel().getColumn(3).setPreferredWidth(160);
        vSocio.jTablaSocios.getColumnModel().getColumn(4).setPreferredWidth(100);
        vSocio.jTablaSocios.getColumnModel().getColumn(5).setPreferredWidth(300);
        vSocio.jTablaSocios.getColumnModel().getColumn(6).setPreferredWidth(180);
        vSocio.jTablaSocios.getColumnModel().getColumn(7).setPreferredWidth(90);
    }
    
    public void rellenarTablaSocios(ArrayList<Socio> socios)
    {
        Object[] fila = new Object[8];
        int numRegistros = socios.size();
        for(int i = 0; i < numRegistros; i++)
        {
            fila[0] = socios.get(i).getNumerosocio();
            fila[1] = socios.get(i).getNombre();
            fila[2] = socios.get(i).getDni();
            fila[3] = socios.get(i).getFechanacimiento();
            fila[4] = socios.get(i).getTelefono();
            fila[5] = socios.get(i).getCorreo();
            fila[6] = socios.get(i).getFechaentrada();
            fila[7] = socios.get(i).getCategoria();
            modeloTablaSocios.addRow(fila);
        }
    }
    
    public void vaciarTablaSocios()
    {
        while(modeloTablaSocios.getRowCount() > 0)
            modeloTablaSocios.removeRow(0);
    }
    
    public void EliminarSocio(int posTabla)
    {
        modeloTablaSocios.removeRow(posTabla);
    }
    
    //------------------ ACTIVIDAD ------------------------
    public void dibujarTablaActividad(VistaActividad vActividad)
    {
        vActividad.jTablaActividad.setModel(modeloTablaActividad);
        
        String[] columnasTabla = {"Nombre", "Correo"};
        modeloTablaActividad.setColumnIdentifiers(columnasTabla);
        
        vActividad.jTablaActividad.getTableHeader().setResizingAllowed(false);
        vActividad.jTablaActividad.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vActividad.jTablaActividad.getColumnModel().getColumn(0).setPreferredWidth(200);
        vActividad.jTablaActividad.getColumnModel().getColumn(1).setPreferredWidth(200);
    }
    
    public void rellenarTablaActividad(ArrayList<Object[]> socios) throws Exception
    {
        Object[] fila = new Object[2];
        
        for(int i = 0; i<socios.size(); i++)
        {
            Object[] s = socios.get(i);
            fila[0] = s[0];
            fila[1] = s[1];
            modeloTablaActividad.addRow(fila);
        }         
    }
    
    public void vaciarTablaActividad()
    {
        while(modeloTablaActividad.getRowCount() > 0)
            modeloTablaActividad.removeRow(0);
    }
    
    // ----------------------------- INSCRIPCIONES -----------------------------
    public void dibujarTablaRealiza(VistaInscripciones vInscripcion)
    {
        vInscripcion.jTableInscripciones.setModel(modeloTablaRealiza);
        
        String[] columnasTabla = {"Código Socio", "Código Actividad"};
        modeloTablaRealiza.setColumnIdentifiers(columnasTabla);
        
        vInscripcion.jTableInscripciones.getTableHeader().setResizingAllowed(false);
        vInscripcion.jTableInscripciones.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vInscripcion.jTableInscripciones.getColumnModel().getColumn(0).setPreferredWidth(200);
        vInscripcion.jTableInscripciones.getColumnModel().getColumn(1).setPreferredWidth(200);
    }
    
    public void rellenarTablaInscripciones(List<Socio> socios) throws Exception
    {
        Object[] fila = new Object[2];
        
        for (Socio socio : socios) 
        {
            Set<Actividad> actividades = socio.getActividades();
            for(Actividad actividad : actividades)
            {
                fila[0] = socio.getNumerosocio();
                fila[1] = actividad.getIdactividad();
                modeloTablaRealiza.addRow(fila);
            }
        } 
    }
    
    public void vaciarTablaInscripciones()
    {
        while(modeloTablaRealiza.getRowCount() > 0)
            modeloTablaRealiza.removeRow(0);
    }
}
