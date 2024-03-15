/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *Clase que se encarga de dibujar y configurar las tablas de las diferentes interfaces
 * @author Ismael Da Palma Fernández
 */
public class UtilTablas 
{
    public DefaultTableModel modeloTablaPorTeclado = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false;
        }
    };
    
    public DefaultTableModel modeloTablaPorTecladoAFND = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column) 
        {
            return false;
        }
    };
    
    /**
     * Metodo que se encarga de dibujar la tabla de la interfaz Teclado del AFD
     * @param vTeclado 
     */
    public void dibujarTablaPorTeclado(VistaTeclado vTeclado)
    {
        vTeclado.jTableAutomatasTeclado.setModel(modeloTablaPorTeclado);
        
        String[] columnasTabla = {"Estado Origen", "Símbolo", "Estado Destino"};
        modeloTablaPorTeclado.setColumnIdentifiers(columnasTabla);
        
        vTeclado.jTableAutomatasTeclado.getTableHeader().setResizingAllowed(false);
        vTeclado.jTableAutomatasTeclado.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vTeclado.jTableAutomatasTeclado.getColumnModel().getColumn(0).setPreferredWidth(100);
        vTeclado.jTableAutomatasTeclado.getColumnModel().getColumn(1).setPreferredWidth(100);
        vTeclado.jTableAutomatasTeclado.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    
    /**
     * Metodo que rellena la tabla de la interfaz vistaTeclado con las diferentes transiciones del AFD
     * @param transiciones 
     */
    public void rellenarTablaPorTeclado(List<TransicionAFD> transiciones)
    {
        Object[] fila = new Object[3];
        for(int i = 0; i < transiciones.size(); i++)
        {
            fila[0] = transiciones.get(i).getEs1();
            fila[1] = transiciones.get(i).getSimbolo();
            fila[2] = transiciones.get(i).getEs2();
            modeloTablaPorTeclado.addRow(fila);
        }
    }
    
    /**
     * Metodo que vacia la tabla de la interfaz AFD
     */
    public void vaciarTablaPorTeclado()
    {
        while(modeloTablaPorTeclado.getRowCount() > 0)
            modeloTablaPorTeclado.removeRow(0);
    }
    
    // -------------------------------------- AFND ---------------------------------------------------
    
    /**
     * Metodo que se encarga de dibujar la tabla de la interfaz vistaTecladoAFND del AFND
     * @param vTecladoAFND 
     */
    public void dibujarTablaPorTecladoAFND(VistaTecladoAFND vTecladoAFND)
    {
        vTecladoAFND.jTableAFNDPorTeclado.setModel(modeloTablaPorTecladoAFND);
        
        String[] columnasTabla = {"Estado Origen", "Símbolo", "Estado Destino", "Transiciónλ"};
        modeloTablaPorTecladoAFND.setColumnIdentifiers(columnasTabla);
        
        vTecladoAFND.jTableAFNDPorTeclado.getTableHeader().setResizingAllowed(false);
        vTecladoAFND.jTableAFNDPorTeclado.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        vTecladoAFND.jTableAFNDPorTeclado.getColumnModel().getColumn(0).setPreferredWidth(50);
        vTecladoAFND.jTableAFNDPorTeclado.getColumnModel().getColumn(1).setPreferredWidth(30);
        vTecladoAFND.jTableAFNDPorTeclado.getColumnModel().getColumn(2).setPreferredWidth(100);
        vTecladoAFND.jTableAFNDPorTeclado.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    
    /**
     * Metodo que rellena la tabla de la interfaz vTecladoAFND con las diferentes transiciones del AFND
     * @param transiciones
     * @param transicionesλ 
     */
    public void rellenarTablaPorTecladoAFND(List<TransicionAFND> transiciones, List<Transicionλ> transicionesλ)
    {
        Object[] fila = new Object[4];
        fila[2] = "";
        fila[3] = "";
        int j = 0; //Para recorrer los estados destinos de la transicion
        int z = 0; //Para recorrer los estados destinos de la transicion lambda
        for(int i = 0; i < transiciones.size(); i++)
        {
            fila[0] = transiciones.get(i).getEs1();
            fila[1] = transiciones.get(i).getSimbolo();
            
            for(j = 0; j < transiciones.get(i).getEs2().length-1; j++)
                fila[2] += transiciones.get(i).getEs2()[j] + ",";
            fila[2] += transiciones.get(i).getEs2()[j];
            
            for(z = 0; z < transicionesλ.get(i).getEst2().length-1; z++)
                fila[3] += transicionesλ.get(i).getEst2()[z] + ",";
            fila[3] += transicionesλ.get(i).getEst2()[z];
            modeloTablaPorTecladoAFND.addRow(fila);
            fila[2] = "";
            fila[3] = "";
        }
    }
    
    /**
     * Metodo que vacia la tabla de la interfaz AFND
     */
    public void vaciarTablaPorTecladoAFND()
    {
        while(modeloTablaPorTecladoAFND.getRowCount() > 0)
            modeloTablaPorTecladoAFND.removeRow(0);
    }
}
