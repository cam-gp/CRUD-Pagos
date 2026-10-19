package CRUDUD;

import java.sql.*; //conexion a base de datos
import java.awt.event.ActionEvent; //eventos - clics
import java.awt.event.ActionListener; // para escuchar las acciones de los botones
import javax.swing.JButton; //para botones 
import javax.swing.JCheckBox; //para las casillas de verificacion ?
import javax.swing.JComboBox; //para desplegar listas
import javax.swing.JLabel; //etiquetas de texto
import javax.swing.JTextField; //cajas de texto
import javax.swing.JFrame; //para la ventana grafica 
import javax.swing.JMenu; //barra y secciones de menu
import javax.swing.JMenuBar; //contenedor de la barra d menu
import javax.swing.JMenuItem; //menu desplegable
import javax.swing.JOptionPane; //mensajes de aviso
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable; //para tabla
import javax.swing.JScrollPane; //para las barras de desplazamiento de la tabla de los registros

public class CRUD extends JFrame implements ActionListener {

    JLabel lb1, lbId, lbMonto, lbNumPago, lbFecha, lbDescripcion;
    JTextField txtId, txtMonto, txtNumPago, txtFecha, txtDescripcion;
    JButton enviar, consultar, altaBD, actualizar, eliminar;
    JMenuItem mBD_I, mBD_C, mBD_A, mBD_E;
    pagos Pagos;    
    JTable tablaa;
    DefaultTableModel modelotabla;
    
    JComboBox cb;
    JCheckBox ckb;

    public CRUD() {
        //Ventana principal
        this.setBounds(150, 50, 750, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        //Menus de arriba
        JMenuBar bm = new JMenuBar();
        this.setJMenuBar(bm);
        
        JMenu mArchivo = new JMenu("Archivo");
        bm.add(mArchivo);
        
        JMenu mBD = new JMenu("Base de datos");
        bm.add(mBD);
        
        //Menu desplegable
        mBD_I = new JMenuItem("Insertar");
        mBD.add(mBD_I);

        mBD_C = new JMenuItem("Consultar");
        mBD.add(mBD_C);
        
        mBD_A = new JMenuItem("Alta en BD");
        mBD.add(mBD_A);
        
        mBD_E = new JMenuItem("Eliminar");
        mBD.add(mBD_E);

        // Título
        this.lb1 = new JLabel("***CRUD de Fecha de Pagos***");
        this.lb1.setBounds(260, 20, 250, 30);
        this.add(lb1);

        //ID
        this.lbId = new JLabel("Id:");
        this.lbId.setBounds(150, 70, 100, 30);
        this.add(lbId);
        
        this.txtId = new JTextField();
        this.txtId.setBounds(260, 70, 250, 30);
        this.add(txtId);
        
        //Monto
        this.lbMonto = new JLabel("Monto:");
        this.lbMonto.setBounds(150, 110, 100, 30);
        this.add(lbMonto);

        this.txtMonto = new JTextField();
        this.txtMonto.setBounds(260, 110, 250, 30);
        this.add(txtMonto);
        
        //Número de Pago
        this.lbNumPago = new JLabel("Número de Pago:");
        this.lbNumPago.setBounds(150, 150, 110, 30);
        this.add(lbNumPago);

        this.txtNumPago = new JTextField();
        this.txtNumPago.setBounds(260, 150, 250, 30);
        this.add(txtNumPago);
        
        //Fecha Año-Mes-Dia
        this.lbFecha = new JLabel("Fecha:");
        this.lbFecha.setBounds(150, 190, 100, 30);
        this.add(lbFecha);
        
        this.txtFecha = new JTextField();
        this.txtFecha.setBounds(260, 190, 250, 30);
        this.add(txtFecha);
        
        //Descripción
        this.lbDescripcion = new JLabel("Descripción:");
        this.lbDescripcion.setBounds(150, 230, 100, 30);
        this.add(lbDescripcion);
        
        this.txtDescripcion = new JTextField();
        this.txtDescripcion.setBounds(260, 230, 250, 30);
        this.add(txtDescripcion);
        
        //Botones
            //this.enviar = new JButton("Enviar");
            //this.enviar.setBounds(50, 290, 110, 35);
            //this.add(enviar);

        this.consultar = new JButton("Consultar");
        this.consultar.setBounds(170, 290, 110, 35);
        this.add(consultar);

        this.altaBD = new JButton("Alta BD");
        this.altaBD.setBounds(290, 290, 110, 35);
        this.add(altaBD);

        this.actualizar = new JButton("Actualizar");
        this.actualizar.setBounds(410, 290, 110, 35);
        this.add(actualizar);

        this.eliminar = new JButton("Eliminar");
        this.eliminar.setBounds(530, 290, 110, 35);
        this.add(eliminar);

        // Eventos
        //this.enviar.addActionListener(this);
        this.consultar.addActionListener(this);
        this.altaBD.addActionListener(this);
        this.actualizar.addActionListener(this);
        this.eliminar.addActionListener(this);

        this.setVisible(true);
        
        //Tabla
        this.setBounds(150,50,750,650);
        modelotabla = new DefaultTableModel(new String[]{"ID","Monto","Numero de pago","Fecha", "Descripcion"}, 0 );
        tablaa = new JTable(modelotabla);
        
        JScrollPane scroll = new JScrollPane(tablaa);
        scroll.setBounds(50, 350, 630, 200);
        this.add(scroll);
        
    }

    public static void main(String[] args) {
        new CRUD();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

         //if (e.getSource() == enviar) {
        //     System.out.println("Enviar ob");
       //        Pagos = new pagos();
      //          Pagos.setMonto(Double.parseDouble(txtMonto.getText()));
     //         }

        if (e.getSource() == consultar) {
            JOptionPane.showMessageDialog(this, "Consultando en la base de datos");
            consultar();
        }

        if (e.getSource() == altaBD) {
            JOptionPane.showMessageDialog(this, "Agregado a la base");
            altaBD();
        }

        if (e.getSource() == actualizar) {
            JOptionPane.showMessageDialog(this, "Base actualizada");
            actualizar();
        }

        if (e.getSource() == eliminar) {
            JOptionPane.showMessageDialog(this, "Registro eliminado correctamente");
            eliminar();
        }
    }

    private Connection getConexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/crud?connectTimeout=3000&useSSL=false",
                "root",
                ""
        );
    }

    public void altaBD() {
        new Thread(() -> {
            try (Connection cnx = getConexion()) {
                String sql = "INSERT INTO pagos(id, monto, numPago, fecha, descripcion) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = cnx.prepareStatement(sql);
                
                ps.setInt(1,Integer.parseInt(txtId.getText()));
                ps.setDouble(2,Double.parseDouble(txtMonto.getText()));
                ps.setInt(3,Integer.parseInt(txtNumPago.getText()));
                ps.setString(4, txtFecha.getText());
                ps.setString(5, txtDescripcion.getText());
                
                ps.execute();
                JOptionPane.showMessageDialog(this,"Registro agregado correctamente.");

            } catch (SQLException ex) {
                System.out.println("Error en SQL " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Revisa que los números de Monto y Número de Pago sean válidos.");
            }
        }).start();
    }

    public void consultar() {
    new Thread(() -> {
        try (Connection cnx = getConexion()) {
            // Consulta para traer todos los registros de la tabla
            String sql = "SELECT * FROM pagos";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            modelotabla.setRowCount(0); // Limpia las filas previas de la tabla

            // Recorremos todos los registros devueltos por la BD
            while (rs.next()) {
                modelotabla.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDouble("monto"),
                    String.format("%03d", rs.getInt("numPago")), // Formato a 3 dígitos (ej: 004)
                    rs.getString("fecha"),
                    rs.getString("descripcion")
                });
            }

            JOptionPane.showMessageDialog(this, "Registros cargados correctamente.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error SQL: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }).start();
}
    public void actualizar() {
        new Thread(() -> {
            try (Connection cnx = getConexion()) {
                String sql = "UPDATE pagos SET monto = ?, numPago = ?, fecha = ?, descripcion = ? WHERE id = ?";
                PreparedStatement ps = cnx.prepareStatement(sql);

                ps.setDouble(1, Double.parseDouble(txtMonto.getText()));
                ps.setInt(2, Integer.parseInt(txtNumPago.getText()));
                ps.setString(3, txtFecha.getText());
                ps.setString(4, txtDescripcion.getText());
                ps.setInt(5, Integer.parseInt(txtId.getText()));

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(this,"Registro actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this,"No se encontró el ID a actualizar.");
                }

            } catch (SQLException ex) {
                System.out.println("Error SQL: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Error: Ingresa un ID válido.");
            }
        }).start();
    }

    public void eliminar() {
        new Thread(() -> {
            try (Connection cnx = getConexion()) {
                String sql = "DELETE FROM pagos WHERE id = ?";
                PreparedStatement ps = cnx.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(txtId.getText()));

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
                    txtId.setText("");
                    txtMonto.setText("");
                    txtNumPago.setText("");
                    txtFecha.setText("");
                    txtDescripcion.setText("");
                } else {
                    System.out.println("No se encontró el ID.");
                }

            } catch (SQLException ex) {
                System.out.println("Error SQL: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Error: Ingresa un ID válido.");
            }
        }).start();
    }
}