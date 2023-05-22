/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Marcas {

    private int idMarca;
    private String nombreMarca;
    private String descripcion;
    private String eliminado;
    private String icono;
    private int usuarioAlta;
    private Date movFechaAlta;
    private Date movHoraAlta;
    private int usuarioModifica;
    private Date movFechaModifica;
    private Date movHoraModifica;
    private String completoUsuarioAlta;
    private String completoUsuarioModifica;

    public Marcas() {

    }

    @Override
    public String toString() { //me retorna una cadena de String
        return "Marcas{" + "idMarca=" + idMarca + ", nombreMarca=" + nombreMarca + ", descripcion=" + descripcion + ", eliminado=" + eliminado + ", icono=" + icono + ", usuarioAlta=" + usuarioAlta + ", movFechaAlta=" + movFechaAlta + ", movHoraAlta=" + movHoraAlta + ", usuarioModifica=" + usuarioModifica + ", movFechaModifica=" + movFechaModifica + ", movHoraModifica=" + movHoraModifica + ", completoUsuarioAlta=" + completoUsuarioAlta + ", completoUsuarioModifica=" + completoUsuarioModifica + '}';
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(int usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Date getMovFechaAlta() {
        return movFechaAlta;
    }

    public void setMovFechaAlta(Date movFechaAlta) {
        this.movFechaAlta = movFechaAlta;
    }

    public Date getMovHoraAlta() {
        return movHoraAlta;
    }

    public void setMovHoraAlta(Date movHoraAlta) {
        this.movHoraAlta = movHoraAlta;
    }

    public int getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getMovFechaModifica() {
        return movFechaModifica;
    }

    public void setMovFechaModifica(Date movFechaModifica) {
        this.movFechaModifica = movFechaModifica;
    }

    public Date getMovHoraModifica() {
        return movHoraModifica;
    }

    public void setMovHoraModifica(Date movHoraModifica) {
        this.movHoraModifica = movHoraModifica;
    }

    public String getCompletoUsuarioAlta() {
        return completoUsuarioAlta;
    }

    public void setCompletoUsuarioAlta(String completoUsuarioAlta) {
        this.completoUsuarioAlta = completoUsuarioAlta;
    }

    public String getCompletoUsuarioModifica() {
        return completoUsuarioModifica;
    }

    public void setCompletoUsuarioModifica(String completoUsuarioModifica) {
        this.completoUsuarioModifica = completoUsuarioModifica;
    }

    public void InsertarMarca(JTextField paraNombre, JTextField paraDescripcion, JTextField paraEliminado, JTextField paraIcono, JTextField paraUsuarioAlta, JTextField paraFecha, JTextField paraHora) {
        setNombreMarca(paraNombre.getText());
        setDescripcion(paraDescripcion.getText());
        setEliminado(paraEliminado.getText());

        Conexion conexion = Conexion.getInstance();
        Connection micoConnection = conexion.getConexion();
        String sql = "INSERT INTO `marcas` (`NOMBREMARCA`, `DESCRIPCION`, `ELIMINADO`, `ICONO`, `USUARIO_ALTA`, `MOV_FECHA_ALTA`, `MOV_HORA_ALTA`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String icono = paraIcono.getText();
        if (icono.isEmpty()) {
            icono = null; // O puedes asignar un valor predeterminado en lugar de null
        }
        setIcono(icono);

        try {
            Date fechaActual = new Date();

            PreparedStatement cs = micoConnection.prepareStatement(sql);
            cs.setString(1, getNombreMarca());
            cs.setString(2, getDescripcion());
            cs.setString(3, "true");
            cs.setString(4, getIcono());
            cs.setString(5, paraUsuarioAlta.getText());
            cs.setString(6, Util.convertirFecha(fechaActual));
            cs.setString(7, Util.convertirHora(fechaActual));
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el registro");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se insertó el registro correctamente: " + e.toString());
        }
    }

    public void MostrarTabla(JTable paraTablaTotal) {
        Conexion conexion = Conexion.getInstance();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        paraTablaTotal.setRowSorter(ordenarTabla);

        String sql = "";

        modelo.addColumn("IDMARCA");
        modelo.addColumn("NOMBREMARCA");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("ELIMINADO");
        modelo.addColumn("ICONO");
        modelo.addColumn("IMAGEN");
        modelo.addColumn("USUARIO_ALTA");
        modelo.addColumn("MOV_FECHA_ALTA");
        modelo.addColumn("MOV_HORA_ALTA");
        modelo.addColumn("USUARIO_MODIFICA");
        modelo.addColumn("MOV_FECHA_MODIFICA");
        modelo.addColumn("MOV_HORA_MODIFICA");

        paraTablaTotal.setModel(modelo);

        sql = "SELECT * FROM marcas where ELIMINADO='true'";

        String[] dato = new String[12];
        Statement st;
        try {
            st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                dato[6] = rs.getString(7);
                dato[7] = rs.getString(8);
                dato[8] = rs.getString(9);
                dato[9] = rs.getString(10);
                dato[10] = rs.getString(11);
                dato[11] = rs.getString(12);

                modelo.addRow(dato);

                if (paraTablaTotal != null) {
                    paraTablaTotal.getTableHeader().setReorderingAllowed(false);
                }
            }
            paraTablaTotal.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pueden mostrar los registros: " + e.toString());
        }
    }

    public void SeleccionarMarca(JTable paraTablaTotal, JTextField paraIDMARCA, JTextField paraNombre, JTextField paraDescripcion, JTextField paraEliminado, JTextField paraIcono, JTextField paraUsuarioAlta, JTextField paraFecha, JTextField paraHora, JTextField paraUsuarioMod, JTextField paraFechaMod, JTextField paraHoraMod) {
        try {

            int fila = paraTablaTotal.getSelectedRow();

            if (fila >= 0) {
                paraIDMARCA.setText(paraTablaTotal.getValueAt(fila, 0).toString());
                paraNombre.setText(paraTablaTotal.getValueAt(fila, 1).toString());
                paraDescripcion.setText(paraTablaTotal.getValueAt(fila, 2).toString());
                paraEliminado.setText(paraTablaTotal.getValueAt(fila, 3).toString());

                paraIcono.setText("");
                if (paraTablaTotal.getValueAt(fila, 4) != null && paraTablaTotal.getValueAt(fila, 4).toString() != null && !paraTablaTotal.getValueAt(fila, 4).toString().isEmpty()) {
                    paraIcono.setText(paraTablaTotal.getValueAt(fila, 4).toString());
                }

                paraUsuarioAlta.setText("");
                if (paraTablaTotal.getValueAt(fila, 6) != null && paraTablaTotal.getValueAt(fila, 6).toString() != null && !paraTablaTotal.getValueAt(fila, 6).toString().isEmpty()) {
                    paraUsuarioAlta.setText(paraTablaTotal.getValueAt(fila, 6).toString());
                }

                paraFecha.setText("");
                if (paraTablaTotal.getValueAt(fila, 7) != null && paraTablaTotal.getValueAt(fila, 7).toString() != null && !paraTablaTotal.getValueAt(fila, 7).toString().isEmpty()) {
                    paraFecha.setText(paraTablaTotal.getValueAt(fila, 7).toString());
                }

                paraHora.setText("");
                if (paraTablaTotal.getValueAt(fila, 8) != null && paraTablaTotal.getValueAt(fila, 8).toString() != null && !paraTablaTotal.getValueAt(fila, 8).toString().isEmpty()) {
                    paraHora.setText(paraTablaTotal.getValueAt(fila, 8).toString());
                }

                paraUsuarioMod.setText("");
                if (paraTablaTotal.getValueAt(fila, 9) != null && paraTablaTotal.getValueAt(fila, 9).toString() != null && paraTablaTotal.getValueAt(fila, 9).toString().isEmpty()) {
                    paraUsuarioMod.setText(paraTablaTotal.getValueAt(fila, 9).toString());
                }
                paraFechaMod.setText("");
                if (paraTablaTotal.getValueAt(fila, 10) != null && paraTablaTotal.getValueAt(fila, 10).toString() != null && !paraTablaTotal.getValueAt(fila, 10).toString().isEmpty()) {
                    paraFechaMod.setText(paraTablaTotal.getValueAt(fila, 10).toString());
                }
                paraHoraMod.setText("");
                if (paraTablaTotal.getValueAt(fila, 11) != null && paraTablaTotal.getValueAt(fila, 11).toString() != null && !paraTablaTotal.getValueAt(fila, 11).toString().isEmpty()) {
                    paraHoraMod.setText(paraTablaTotal.getValueAt(fila, 11).toString());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Fila no selecionada");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de seleccion: " + e.toString());
        }
    }

    public void Modificar(JTextField paraIDMarca, JTextField paraNombre, JTextField paraDescripcion, JTextField paraEliminado, JTextField paraIcono, JTextField paraUsuarioAlta, JTextField paramovFechaAlta, JTextField paramovHoraAlta, JTextField paraUsarioMod, JTextField paraFechaMod, JTextField paraHoraMod) {
        try {
            int idMarca = 0;
            if (!paraIDMarca.getText().isEmpty()) {
                idMarca = Integer.parseInt(paraIDMarca.getText());
            }

            String nombreMarca = paraNombre.getText();
            String descripcion = paraDescripcion.getText();
            String eliminado = paraEliminado.getText();
            String icono = paraIcono.getText();
            String fecha = paramovFechaAlta.getText();
            String hora = paramovHoraAlta.getText();
            String fechaMod = paraFechaMod.getText();
            String horaMod = paraHoraMod.getText();

            int usuarioAlta = usuarioModifica;
            if (!paraUsuarioAlta.getText().isEmpty()) {
                usuarioAlta = Integer.parseInt(paraUsuarioAlta.getText());
            }

            int usuarioMod = usuarioAlta;
            if (!paraUsarioMod.getText().isEmpty()) {
                usuarioMod = Integer.parseInt(paraUsarioMod.getText());
            }

            Conexion conexion = Conexion.getInstance();
            Connection micoConnection = conexion.getConexion();

            String consulta = "UPDATE marcas SET NOMBREMARCA=?, DESCRIPCION=?, ELIMINADO=?, ICONO=?,  USUARIO_MODIFICA=?, MOV_FECHA_MODIFICA=?, MOV_HORA_MODIFICA=? WHERE IDMARCA=?";

            try {
                Date fechaActual = new Date();

                PreparedStatement cs = micoConnection.prepareStatement(consulta);
                cs.setString(1, nombreMarca);
                cs.setString(2, descripcion);
                cs.setString(3, eliminado);
                cs.setString(4, icono);
                cs.setInt(5, usuarioMod);
                cs.setString(6, Util.convertirFecha(fechaActual));
                cs.setString(7, Util.convertirHora(fechaActual));
                cs.setInt(8, idMarca);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Modificación exitosa");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se hizo la modificación correctamente: " + e.toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Los campos de ID de Marca y Usuario Alta deben ser números enteros.");
        }
    }

    public void Eliminar(JTextField paraidMarca) {
        if (!paraidMarca.getText().isEmpty()) {
            try {
                setIdMarca(Integer.parseInt(paraidMarca.getText()));
                Conexion conexion = Conexion.getInstance();
                Connection micoConnection = conexion.getConexion();

                //String consulta = "DELETE FROM marcas WHERE IDMARCA=? ";
                String consulta = "UPDATE marcas SET ELIMINADO='false', MOV_FECHA_MODIFICA=?, MOV_HORA_MODIFICA=? WHERE IDMARCA=?";

                try {
                    Date fechaActual = new Date();
                    PreparedStatement cs = micoConnection.prepareStatement(consulta);
                    cs.setString(1, Util.convertirFecha(fechaActual));
                    cs.setString(2, Util.convertirHora(fechaActual));
                    cs.setInt(3, getIdMarca());
                    cs.execute();
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente el registro");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro: " + e.toString());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El valor ingresado no es válido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un valor para eliminar el registro");
        }
    }
}
