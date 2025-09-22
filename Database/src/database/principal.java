package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.mxgraph.view.mxGraph;
import com.mxgraph.swing.mxGraphComponent;

/**
 *
 * @author villa
 */
public class principal extends javax.swing.JFrame {

    DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Servers");
    Color fondo = new Color(51, 51, 51);
    Color texto = Color.WHITE;
    private DefaultTableModel modelocolumnas;
    private String tabla2;
    private JSplitPane split;
    private JPanel panelIzq, panelDer;
    private JTree tree;
    private JTextArea sqlEditor, msgArea;
    private JTable resultTable;
    private JButton btnRun;
    private String nombreselect;
    private Connection connActual, connpostgres;
    private Map<String, usuario> perfiles = new LinkedHashMap<>();
    private final Map<String, java.sql.Connection> conexiones = new LinkedHashMap<>();
    private JTabbedPane tabsPerfiles;
    private JTextArea ddlArea;
    private JTabbedPane workTabs;
    private Map<String, Component> openTableTabs = new HashMap<>();
    private int pageSizeDefault = 100;
    private boolean ismariaDB = true;
    String bd;
    barratareas bt = new barratareas();
    objetosgestor og = new objetosgestor();
    JTabbedPane tabs = new JTabbedPane();

    public principal() {
        initComponents();
        jtablecolumnas();
        this.setExtendedState(MAXIMIZED_BOTH);
        bt.inicializarbarratareas(panelprincipal);
        paneles();
        cargarPerfiles();
        UIManager.put("TabbedPane.selected", Color.darkGray);
        UIManager.put("TabbedPane.contentAreaColor", Color.darkGray);
        UIManager.put("TabbedPane.focus", Color.darkGray);
        UIManager.put("TabbedPane.background", Color.darkGray);
        UIManager.put("TabbedPane.foreground", Color.WHITE);

    }

    private void cargarPerfiles() {
        perfiles = Conexiones.cargar();

        for (usuario u : perfiles.values()) {
            DefaultMutableTreeNode nombrebd = new DefaultMutableTreeNode(u.nombre);
            raiz.add(nombrebd);
        }
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();
        tree.expandRow(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameuser = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        lbajusteconexion = new javax.swing.JLabel();
        txtdatabase = new javax.swing.JTextField();
        lbport = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbconnect = new javax.swing.JLabel();
        lburl = new javax.swing.JLabel();
        lbpassword = new javax.swing.JLabel();
        txturl = new javax.swing.JTextField();
        txtport = new javax.swing.JTextField();
        lbdatabase = new javax.swing.JLabel();
        rbhost = new javax.swing.JRadioButton();
        lbauthentication = new javax.swing.JLabel();
        lbserver1 = new javax.swing.JLabel();
        txtserverhost = new javax.swing.JTextField();
        lbserver2 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        btcancelar = new javax.swing.JButton();
        btprobarconexion = new javax.swing.JButton();
        btfinalizar = new javax.swing.JButton();
        chekbguardarcontra = new javax.swing.JCheckBox();
        txtpassword = new javax.swing.JPasswordField();
        lbcuadroserver = new javax.swing.JLabel();
        frametables = new javax.swing.JFrame();
        paneltablas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombretabla = new javax.swing.JTextField();
        btaggcolumna = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_columnas = new javax.swing.JTable();
        btquitarcolumna = new javax.swing.JButton();
        btaggcolumna2 = new javax.swing.JButton();
        framecolumns = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        txtnamecolumn = new javax.swing.JTextField();
        cbdatatype = new javax.swing.JComboBox<>();
        cbnotnull = new javax.swing.JCheckBox();
        cbautoincrement = new javax.swing.JCheckBox();
        cbuniquetype = new javax.swing.JCheckBox();
        cbkeys = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbauthentication1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        lbauthentication2 = new javax.swing.JLabel();
        btaccept = new javax.swing.JButton();
        framevista = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtnombrevista = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        areavista = new javax.swing.JTextArea();
        btguardarvista = new javax.swing.JButton();
        panelprincipal = new javax.swing.JPanel();

        frameuser.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        lbajusteconexion.setBackground(new java.awt.Color(255, 255, 255));
        lbajusteconexion.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        lbajusteconexion.setForeground(new java.awt.Color(255, 255, 255));
        lbajusteconexion.setText("Ajustes de conexion");
        jPanel1.add(lbajusteconexion);
        lbajusteconexion.setBounds(20, 20, 170, 20);

        txtdatabase.setBackground(new java.awt.Color(51, 51, 51));
        txtdatabase.setForeground(new java.awt.Color(255, 255, 255));
        txtdatabase.setText("midb");
        txtdatabase.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdatabase.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtdatabase);
        txtdatabase.setBounds(110, 170, 460, 22);

        lbport.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lbport.setForeground(new java.awt.Color(255, 255, 255));
        lbport.setText("Port:");
        jPanel1.add(lbport);
        lbport.setBounds(460, 145, 40, 20);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 60, 600, 10);

        lbconnect.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lbconnect.setForeground(new java.awt.Color(255, 255, 255));
        lbconnect.setText("Connect by:");
        jPanel1.add(lbconnect);
        lbconnect.setBounds(30, 95, 70, 20);

        lburl.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lburl.setForeground(new java.awt.Color(255, 255, 255));
        lburl.setText("URL:");
        jPanel1.add(lburl);
        lburl.setBounds(30, 120, 37, 14);

        lbpassword.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lbpassword.setForeground(new java.awt.Color(255, 255, 255));
        lbpassword.setText("Password:");
        jPanel1.add(lbpassword);
        lbpassword.setBounds(30, 255, 120, 20);

        txturl.setBackground(new java.awt.Color(51, 51, 51));
        txturl.setForeground(new java.awt.Color(255, 255, 255));
        txturl.setText("jdbc:mariadb://localhost:3306/");
        txturl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txturl.setCaretColor(new java.awt.Color(255, 255, 255));
        txturl.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txturl.setEnabled(false);
        jPanel1.add(txturl);
        txturl.setBounds(110, 120, 460, 22);

        txtport.setBackground(new java.awt.Color(51, 51, 51));
        txtport.setForeground(new java.awt.Color(255, 255, 255));
        txtport.setText("3306");
        txtport.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtport);
        txtport.setBounds(500, 145, 70, 22);

        lbdatabase.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lbdatabase.setForeground(new java.awt.Color(255, 255, 255));
        lbdatabase.setText("Database:");
        jPanel1.add(lbdatabase);
        lbdatabase.setBounds(30, 170, 70, 20);

        rbhost.setBackground(new java.awt.Color(51, 51, 51));
        rbhost.setForeground(new java.awt.Color(255, 255, 255));
        rbhost.setSelected(true);
        rbhost.setText("Host");
        rbhost.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rbhost.setOpaque(true);
        jPanel1.add(rbhost);
        rbhost.setBounds(110, 95, 80, 20);

        lbauthentication.setForeground(new java.awt.Color(255, 255, 255));
        lbauthentication.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.add(lbauthentication);
        lbauthentication.setBounds(16, 75, 570, 130);

        lbserver1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lbserver1.setForeground(new java.awt.Color(255, 255, 255));
        lbserver1.setText("Server Host:");
        jPanel1.add(lbserver1);
        lbserver1.setBounds(30, 145, 70, 20);

        txtserverhost.setBackground(new java.awt.Color(51, 51, 51));
        txtserverhost.setForeground(new java.awt.Color(255, 255, 255));
        txtserverhost.setText("localhost");
        txtserverhost.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtserverhost.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtserverhost);
        txtserverhost.setBounds(110, 145, 340, 22);

        lbserver2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lbserver2.setForeground(new java.awt.Color(255, 255, 255));
        lbserver2.setText("Username:");
        jPanel1.add(lbserver2);
        lbserver2.setBounds(30, 230, 120, 20);

        txtuser.setBackground(new java.awt.Color(51, 51, 51));
        txtuser.setForeground(new java.awt.Color(255, 255, 255));
        txtuser.setText("usuario");
        txtuser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtuser.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtuser);
        txtuser.setBounds(150, 230, 180, 22);

        btcancelar.setBackground(new java.awt.Color(51, 51, 51));
        btcancelar.setForeground(new java.awt.Color(255, 255, 255));
        btcancelar.setText("Cancelar");
        btcancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btcancelar);
        btcancelar.setBounds(420, 330, 110, 20);

        btprobarconexion.setBackground(new java.awt.Color(51, 51, 51));
        btprobarconexion.setForeground(new java.awt.Color(255, 255, 255));
        btprobarconexion.setText("Probar conexion");
        btprobarconexion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btprobarconexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprobarconexionActionPerformed(evt);
            }
        });
        jPanel1.add(btprobarconexion);
        btprobarconexion.setBounds(60, 330, 110, 20);

        btfinalizar.setBackground(new java.awt.Color(51, 51, 51));
        btfinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btfinalizar.setText("Finalizar");
        btfinalizar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btfinalizarActionPerformed(evt);
            }
        });
        jPanel1.add(btfinalizar);
        btfinalizar.setBounds(240, 330, 110, 20);

        chekbguardarcontra.setForeground(new java.awt.Color(255, 255, 255));
        chekbguardarcontra.setSelected(true);
        chekbguardarcontra.setText("Save password");
        chekbguardarcontra.setContentAreaFilled(false);
        jPanel1.add(chekbguardarcontra);
        chekbguardarcontra.setBounds(350, 260, 150, 20);

        txtpassword.setBackground(new java.awt.Color(51, 51, 51));
        txtpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtpassword.setText("userpass123");
        txtpassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtpassword.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtpassword);
        txtpassword.setBounds(150, 255, 180, 20);

        lbcuadroserver.setForeground(new java.awt.Color(255, 255, 255));
        lbcuadroserver.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Authentication (Database Native)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.add(lbcuadroserver);
        lbcuadroserver.setBounds(15, 210, 570, 80);

        frameuser.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 470));

        paneltablas.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Table name:");

        txtnombretabla.setBackground(new java.awt.Color(51, 51, 51));
        txtnombretabla.setForeground(new java.awt.Color(255, 255, 255));
        txtnombretabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombretabla.setCaretColor(new java.awt.Color(255, 255, 255));

        btaggcolumna.setBackground(new java.awt.Color(51, 51, 51));
        btaggcolumna.setForeground(new java.awt.Color(255, 255, 255));
        btaggcolumna.setText("Agregar columna");
        btaggcolumna.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btaggcolumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaggcolumnaActionPerformed(evt);
            }
        });

        tabla_columnas.setBackground(new java.awt.Color(51, 51, 51));
        tabla_columnas.setForeground(new java.awt.Color(255, 255, 255));
        tabla_columnas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column Name", "Data Type", "Not Null", "Auto Increment", "Key"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_columnas.setToolTipText("");
        tabla_columnas.setGridColor(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(tabla_columnas);

        btquitarcolumna.setBackground(new java.awt.Color(51, 51, 51));
        btquitarcolumna.setForeground(new java.awt.Color(255, 255, 255));
        btquitarcolumna.setText("Quitar columna");
        btquitarcolumna.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btquitarcolumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btquitarcolumnaActionPerformed(evt);
            }
        });

        btaggcolumna2.setBackground(new java.awt.Color(51, 51, 51));
        btaggcolumna2.setForeground(new java.awt.Color(255, 255, 255));
        btaggcolumna2.setText("Guardar");
        btaggcolumna2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btaggcolumna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaggcolumna2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneltablasLayout = new javax.swing.GroupLayout(paneltablas);
        paneltablas.setLayout(paneltablasLayout);
        paneltablasLayout.setHorizontalGroup(
            paneltablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneltablasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtnombretabla, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(363, Short.MAX_VALUE))
            .addGroup(paneltablasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(paneltablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btaggcolumna, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btquitarcolumna, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btaggcolumna2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        paneltablasLayout.setVerticalGroup(
            paneltablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneltablasLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(paneltablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnombretabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(paneltablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneltablasLayout.createSequentialGroup()
                        .addComponent(btaggcolumna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btquitarcolumna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btaggcolumna2)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frametablesLayout = new javax.swing.GroupLayout(frametables.getContentPane());
        frametables.getContentPane().setLayout(frametablesLayout);
        frametablesLayout.setHorizontalGroup(
            frametablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneltablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frametablesLayout.setVerticalGroup(
            frametablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneltablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtnamecolumn.setBackground(new java.awt.Color(51, 51, 51));
        txtnamecolumn.setForeground(new java.awt.Color(255, 255, 255));
        txtnamecolumn.setText("Column 1");
        txtnamecolumn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnamecolumn.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtnamecolumn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 150, 20));

        cbdatatype.setBackground(new java.awt.Color(51, 51, 51));
        cbdatatype.setForeground(new java.awt.Color(255, 255, 255));
        cbdatatype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "varchar(100)", "VARCHAR", "INT", "BIGINT", "TEXT", "DATE", "DATETIME", "DECIMAL", "BOOLEAN", " " }));
        jPanel2.add(cbdatatype, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 140, 20));

        cbnotnull.setForeground(new java.awt.Color(255, 255, 255));
        cbnotnull.setText("Not Null");
        cbnotnull.setContentAreaFilled(false);
        jPanel2.add(cbnotnull, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        cbautoincrement.setForeground(new java.awt.Color(255, 255, 255));
        cbautoincrement.setText("Auto Increment");
        cbautoincrement.setContentAreaFilled(false);
        jPanel2.add(cbautoincrement, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        cbuniquetype.setForeground(new java.awt.Color(255, 255, 255));
        cbuniquetype.setText("Unique Type:");
        cbuniquetype.setContentAreaFilled(false);
        jPanel2.add(cbuniquetype, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        cbkeys.setBackground(new java.awt.Color(51, 51, 51));
        cbkeys.setForeground(new java.awt.Color(255, 255, 255));
        cbkeys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primary Key", "Unique Key" }));
        jPanel2.add(cbkeys, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Edit attribute Column");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Data Type:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        lbauthentication1.setBackground(new java.awt.Color(51, 51, 51));
        lbauthentication1.setForeground(new java.awt.Color(255, 255, 255));
        lbauthentication1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Keys", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.add(lbauthentication1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 360, 60));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 44, 380, 10));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Name:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        lbauthentication2.setBackground(new java.awt.Color(51, 51, 51));
        lbauthentication2.setForeground(new java.awt.Color(255, 255, 255));
        lbauthentication2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.add(lbauthentication2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 360, 130));

        btaccept.setBackground(new java.awt.Color(51, 51, 51));
        btaccept.setForeground(new java.awt.Color(255, 255, 255));
        btaccept.setText("Aceptar");
        btaccept.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btaccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btacceptActionPerformed(evt);
            }
        });
        jPanel2.add(btaccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 80, 20));

        javax.swing.GroupLayout framecolumnsLayout = new javax.swing.GroupLayout(framecolumns.getContentPane());
        framecolumns.getContentPane().setLayout(framecolumnsLayout);
        framecolumnsLayout.setHorizontalGroup(
            framecolumnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        framecolumnsLayout.setVerticalGroup(
            framecolumnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setForeground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("View name:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, -1, -1));

        txtnombrevista.setBackground(new java.awt.Color(51, 51, 51));
        txtnombrevista.setForeground(new java.awt.Color(255, 255, 255));
        txtnombrevista.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtnombrevista, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 23, 143, -1));

        areavista.setBackground(new java.awt.Color(51, 51, 51));
        areavista.setColumns(20);
        areavista.setForeground(new java.awt.Color(255, 255, 255));
        areavista.setRows(5);
        areavista.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(areavista);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 81, 414, 185));

        btguardarvista.setBackground(new java.awt.Color(51, 51, 51));
        btguardarvista.setForeground(new java.awt.Color(255, 255, 255));
        btguardarvista.setText("Guardar");
        btguardarvista.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btguardarvista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btguardarvistaActionPerformed(evt);
            }
        });
        jPanel4.add(btguardarvista, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 23, 81, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout framevistaLayout = new javax.swing.GroupLayout(framevista.getContentPane());
        framevista.getContentPane().setLayout(framevistaLayout);
        framevistaLayout.setHorizontalGroup(
            framevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        framevistaLayout.setVerticalGroup(
            framevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        panelprincipal.setBackground(java.awt.Color.darkGray);
        panelprincipal.setForeground(new java.awt.Color(255, 255, 255));
        panelprincipal.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelarActionPerformed
        frameuser.setVisible(false);
    }//GEN-LAST:event_btcancelarActionPerformed

    private void btfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btfinalizarActionPerformed

        String host = txtserverhost.getText();
        int puerto = Integer.parseInt(txtport.getText());
        String database = txtdatabase.getText();
        String user = txtuser.getText();
        String password = new String(txtpassword.getPassword());
        ConnectMariadb cm = new ConnectMariadb();
        ConnectPostgres cp = new ConnectPostgres();

        try {
            usuario pc = new usuario();
            System.out.println("ismariadb: ");
            if (ismariaDB) {
                connActual = cm.conectar(host, puerto, database, user, password);
                pc.nombre = host + ":" + puerto + " (" + user + ")";
                pc.host = host;
                pc.port = puerto;
                pc.database = database;
                pc.username = user;
                pc.guardarPassword = chekbguardarcontra.isSelected();
                pc.password = pc.guardarPassword ? password : "";

                System.out.println("connactual: " + connActual);
                perfiles.put(pc.nombre, pc);
                System.out.println("antes de guardar");
                Conexiones.guardar(perfiles.values());
                System.out.println("despues de guardar");
                conexiones.put(pc.nombre, connActual);
                agregarInstanciaAlArbol(connActual, pc.nombre);
                tree.setRootVisible(true);
                tree.setShowsRootHandles(true);

                frameuser.setVisible(false);
                javax.swing.JComponent ed = crearEditorSQL();
                String tabTitle = "Editor SQL (" + (nombreselect != null ? nombreselect : "sin conexión") + ")";
                addClosableTab(tabsPerfiles, "tabla: " + tabTitle, ed);

                tabsPerfiles.setSelectedComponent(ed);
            } else {
                connpostgres = cp.conectar(host, puerto, database, user, password);
                System.out.println("si se guardo");
                pc.nombre = host + ":" + puerto + " (" + user + ")";
                pc.host = host;
                pc.port = puerto;
                pc.database = database;
                pc.username = user;
                pc.guardarPassword = chekbguardarcontra.isSelected();
                pc.password = pc.guardarPassword ? password : "";
                
                metadatamariadb mtmd=new metadatamariadb(connActual, connpostgres);
                JOptionPane.showMessageDialog(null, "Completado exitosamente");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Fallo en la conexión: " + e.getMessage());
        }


    }//GEN-LAST:event_btfinalizarActionPerformed

    private void btprobarconexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprobarconexionActionPerformed
        String host = txtserverhost.getText();
        int puerto = Integer.parseInt(txtport.getText());
        String database = txtdatabase.getText();
        String user = txtuser.getText();
        String password = new String(txtpassword.getPassword());

        ConnectMariadb cm = new ConnectMariadb();
        ConnectPostgres cp = new ConnectPostgres();
        boolean si;
        if(ismariaDB){
            si = cm.probarConexion(host, puerto, database, user, password); 
        }else{
            si = cp.probarConexion(host, puerto, database, user, password); 
        }
        
        if (si) {
            JOptionPane.showInternalMessageDialog(null, "Conexión exitosa");
        } else {
            JOptionPane.showInternalMessageDialog(null, "Fallo en conexión.");
        }

    }//GEN-LAST:event_btprobarconexionActionPerformed

    private void btaggcolumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaggcolumnaActionPerformed
        framecolumns.setVisible(true);
        framecolumns.setLocation(400, 100);
        framecolumns.setSize(410, 350);
    }//GEN-LAST:event_btaggcolumnaActionPerformed

    private void btacceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btacceptActionPerformed
        String colName = txtnamecolumn.getText().trim();
        String typeSel = (cbdatatype.getSelectedItem() == null) ? "" : cbdatatype.getSelectedItem().toString().trim();
        boolean notNull = cbnotnull.isSelected();
        boolean autoInc = cbautoincrement.isSelected();
        String key = cbuniquetype.isSelected() ? cbkeys.getSelectedItem().toString() : "";

        if (colName.isEmpty()) {
            JOptionPane.showMessageDialog(framecolumns, "Ingresa el nombre de la columna.");
            return;
        }
        if (typeSel.isEmpty()) {
            JOptionPane.showMessageDialog(framecolumns, "Selecciona el tipo de dato.");
            return;
        }
        if (autoInc && !(typeSel.equalsIgnoreCase("INT") || typeSel.equalsIgnoreCase("BIGINT"))) {
            JOptionPane.showMessageDialog(framecolumns, "AUTO_INCREMENT solo para INT/BIGINT.");
            return;
        }
        modelocolumnas.addRow(new Object[]{colName, typeSel, notNull, autoInc, key});

        framecolumns.setVisible(false);
    }//GEN-LAST:event_btacceptActionPerformed

    private void btquitarcolumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btquitarcolumnaActionPerformed
        int row = tabla_columnas.getSelectedRow();
        if (row >= 0) {
            modelocolumnas.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(frametables, "Selecciona una fila.");
        }

    }//GEN-LAST:event_btquitarcolumnaActionPerformed

    private void btaggcolumna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaggcolumna2ActionPerformed
        String table = txtnombretabla.getText().trim();
        if (table.isEmpty()) {
            JOptionPane.showMessageDialog(frametables, "Ingresa el nombre de la tabla.");
            return;
        }
        int rows = modelocolumnas.getRowCount();
        if (rows == 0) {
            JOptionPane.showMessageDialog(frametables, "Agrega al menos una columna.");
            return;
        }

        List<String> pks = new ArrayList<>();
        List<String> uniques = new ArrayList<>();
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE `").append(tabla2).append("`.`").append(table).append("` (\n");
        for (int i = 0; i < rows; i++) {
            String colName = String.valueOf(modelocolumnas.getValueAt(i, 0)).trim();
            String typeSel = String.valueOf(modelocolumnas.getValueAt(i, 1)).trim();
            boolean notNull = Boolean.TRUE.equals(modelocolumnas.getValueAt(i, 2));
            boolean autoInc = Boolean.TRUE.equals(modelocolumnas.getValueAt(i, 3));
            String key = String.valueOf(modelocolumnas.getValueAt(i, 4));

            if (colName.isEmpty()) {
                JOptionPane.showMessageDialog(frametables, "Hay columnas sin nombre.");
                return;
            }
            if (typeSel.isEmpty()) {
                JOptionPane.showMessageDialog(frametables, "Hay columnas sin tipo.");
                return;
            }
            String tipo;
            String upper = typeSel.toUpperCase();
            if (upper.equals("VARCHAR")) {
                tipo = "VARCHAR(255)";
            } else if (upper.equals("DECIMAL")) {
                tipo = "DECIMAL(10,2)";
            } else {
                tipo = typeSel;
            }
            ddl.append("  `").append(colName).append("` ").append(tipo);
            if (notNull) {
                ddl.append(" NOT NULL");
            }
            if (autoInc) {
                if (!(upper.equals("INT") || upper.equals("BIGINT"))) {
                    JOptionPane.showMessageDialog(frametables, "AUTO_INCREMENT solo en INT/BIGINT: " + colName);
                    return;
                }
                ddl.append(" AUTO_INCREMENT");
            }
            ddl.append(",\n");

            if ("Primary Key".equalsIgnoreCase(key)) {
                pks.add(colName);
            } else if ("Unique Key".equalsIgnoreCase(key)) {
                uniques.add(colName);
            }
        }
        if (!pks.isEmpty()) {
            ddl.append(" PRIMARY KEY (");
            for (int i = 0; i < pks.size(); i++) {
                if (i > 0) {
                    ddl.append(", ");
                }
                ddl.append("`").append(pks.get(i)).append("`");
            }
            ddl.append("),\n");
        }

        for (String u : uniques) {
            String idx = "uq_" + u.replaceAll("[^a-zA-Z0-9_]", "");
            ddl.append("  UNIQUE KEY `").append(idx).append("` (`").append(u).append("`),\n");
        }
        int last = ddl.lastIndexOf(",\n");
        if (last >= 0) {
            ddl.delete(last, last + 2).append("\n");
        }

        ddl.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

        try (Statement st = connActual.createStatement()) {
            st.execute(ddl.toString());
            og.appendMsg(msgArea, "Tabla creada: " + tabla2 + "." + table);
            if (nombreselect != null) {
                agregarInstanciaAlArbol(connActual, nombreselect);
            }
            System.out.println(ddl);
            ddlArea.setText(ddl.toString());
            ddlArea.setCaretPosition(0);
            tabs.setSelectedIndex(2);

            frametables.setVisible(false);
            modelocolumnas.setRowCount(0);
            txtnombretabla.setText("");
        } catch (SQLException ex) {
            og.appendMsg(msgArea, "Error al crear tabla: " + ex.getMessage());
            JOptionPane.showMessageDialog(frametables, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btaggcolumna2ActionPerformed

    private void btguardarvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguardarvistaActionPerformed

        guardarvista(bd);
    }//GEN-LAST:event_btguardarvistaActionPerformed

    private void paneles() {
        // ===== Panel izquierdo =====
        panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBackground(new Color(51, 51, 51));
        panelIzq.setPreferredSize(new Dimension(350, 625));

        // Árbol
        tree = new JTree(raiz);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        tree.expandRow(0);
        tree.setBackground(fondo);
        tree.setForeground(texto);
        tree.setOpaque(true);
        tree.setSelectionRow(0);
        tree.setCellRenderer(new javax.swing.tree.DefaultTreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                java.awt.Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                if (sel) {
                    setBackgroundNonSelectionColor(new Color(75, 110, 175));
                    setTextNonSelectionColor(texto);
                    setTextSelectionColor(texto);
                }
                setBackground(fondo);
                setForeground(texto);
                setOpaque(true);
                return c;
            }
        });

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (n == null) {
                    return;
                }
                String key = String.valueOf(n.getUserObject());

                if (perfiles.containsKey(key) && conexiones.containsKey(key)) {
                    connActual = conexiones.get(key);
                    og.appendMsg(msgArea, "Conexión activa: " + key);
                }
            }
        });
        //menu para crear tablas y vistas
        JPopupMenu menutablas = new JPopupMenu();
        JMenuItem nuevatabla = new JMenuItem("Crear nueva tabla");
        JMenuItem refrescar = new JMenuItem("Refrescar");
        menutablas.add(nuevatabla);
        menutablas.add(refrescar);

        JPopupMenu menuvista = new JPopupMenu();
        JMenuItem nuevavista = new JMenuItem("Crear nueva vista");
        menuvista.add(nuevavista);
        menuvista.add(refrescar);

        //menu de la metadata
        JPopupMenu menuObjeto = new JPopupMenu();
        JMenuItem ShowDDL = new JMenuItem("Show DDL");
        JMenuItem AbrirDDL = new JMenuItem("Abrir DDL en editor");
        menuObjeto.add(ShowDDL);
        menuObjeto.add(AbrirDDL);
        menuObjeto.addSeparator();

        // Menu de la raiz
        JPopupMenu menuRaiz = new JPopupMenu();
        JMenuItem nuevoArchivo = new JMenuItem("Nueva Conexion");
        menuRaiz.add(nuevoArchivo);

        //Menu de las conexiones
        JPopupMenu menuPerfil = new JPopupMenu();
        JMenuItem miConnect = new JMenuItem("Connect");
        JMenuItem postgres = new JMenuItem("Migrar a postgres");
        JMenuItem miEdit = new JMenuItem("Edit…");
        JMenuItem miDelete = new JMenuItem("Delete");
        menuPerfil.add(miConnect);
        menuPerfil.add(postgres);
        menuPerfil.add(miEdit);
        menuPerfil.addSeparator();
        menuPerfil.add(miDelete);
        menuRaiz.addSeparator();

        nuevavista.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if (path == null) {
                return;
            }
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
            DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();

            if (padre == null) {
                return;
            }
            bd = padre.getUserObject().toString();
            framevista.setVisible(true);
            framevista.setLocation(400, 100);
            framevista.setSize(476, 338);

        });

        nuevatabla.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if (path == null) {
                return;
            }
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
            DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();

            if (padre == null) {
                return;
            }
            tabla2 = padre.getUserObject().toString();
            txtnombretabla.setText("");
            modelocolumnas.setRowCount(0);
            frametables.setVisible(true);
            frametables.setLocation(400, 100);
            frametables.setSize(655, 470);

        });

        refrescar.addActionListener(e -> {
            if (connActual != null && nombreselect != null) {
                try {
                    agregarInstanciaAlArbol(connActual, nombreselect);
                } catch (SQLException ex) {
                    og.appendMsg(msgArea, "Error al refrescar");
                }
            }
        });

        nuevoArchivo.addActionListener(e -> {
            frameuser.setVisible(true);
            frameuser.setLocation(400, 100);
            frameuser.setSize(610, 470);
        });

        miConnect.addActionListener(e -> {
            nombreselect = nodoSeleccionado(tree);
            usuario u = perfiles.get(nombreselect);
            if (u == null) {
                return;
            }
            conectarperfil(nombreselect);
        });
        postgres.addActionListener(e -> {
            frameuser.setVisible(true);
            frameuser.setLocation(400, 100);
            frameuser.setSize(610, 470);
            txtport.setText("5432");
            txturl.setText("jdbc:postgresql://localhost:5432/postgres");
            txtdatabase.setText("pancitopg");
            txtuser.setText("postgres");
            txtpassword.setText("clave123");
            ismariaDB = false;
        });

        ShowDDL.addActionListener(ev -> {
            TreePath path = tree.getSelectionPath();
            String[] ctx = extraerContextoObjeto(path);
            if (ctx != null) {
                mostrarDDL(ctx[0], ctx[1], ctx[2], false);
            }
        });
        AbrirDDL.addActionListener(ev -> {
            TreePath path = tree.getSelectionPath();
            String[] ctx = extraerContextoObjeto(path);
            if (ctx != null) {
                mostrarDDL(ctx[0], ctx[1], ctx[2], true);
            }
        });

        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            private void mostrarMenuSiCorresponde(java.awt.event.MouseEvent e) {

                if (e.isPopupTrigger()) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
                        DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();
                        tree.setSelectionPath(path);
                        if (nodo == raiz) {
                            menuRaiz.show(tree, e.getX(), e.getY());
                        } else if (nodo.getParent() == raiz) {
                            menuPerfil.show(tree, e.getX(), e.getY());
                        } else if (padre != null) {
                            String padreName = padre.getUserObject().toString();
                            if ("Tables".equals(nodo.getUserObject().toString())) {
                                menutablas.show(tree, e.getX(), e.getY());
                            } else if ("Views".equals(nodo.getUserObject().toString())) {
                                menuvista.show(tree, e.getX(), e.getY());
                            } else if (padreName.equals("Tables") || padreName.equals("Views")
                                    || padreName.equals("Triggers") || padreName.equals("Procedures")
                                    || padreName.equals("Functions") || padreName.equals("Sequences")) {
                                menuObjeto.show(tree, e.getX(), e.getY());
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                mostrarMenuSiCorresponde(e);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                mostrarMenuSiCorresponde(e);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path == null) {
                        return;
                    }

                    DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if (nodo == null) {
                        return;
                    }

                    DefaultMutableTreeNode n = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (n != null) {
                        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) n.getParent();
                        if (parent != null && "Databases".equals(String.valueOf(parent.getUserObject()))) {
                            String dbSel = String.valueOf(n.getUserObject());
                            // Abre el diagrama
                            mostrarDiagramaER(dbSel);
                        }
                    }

                    // 2) Si NO es carpeta BD, usamos tu lógica existente
                    String[] ctx = extraerContextoObjeto(path);
                    if (ctx == null) {
                        return;
                    }

                    String db = ctx[0];
                    String tipo = ctx[1];
                    String name = ctx[2]; // nombre de la tabla

                    if ("Tables".equalsIgnoreCase(tipo) || "Views".equalsIgnoreCase(tipo)) {
                        name = name.replace(" (VIEW)", "");
                        openTableTab(db, name);
                    }
                }
            }

        });

        JScrollPane scrollTree = new JScrollPane(tree);
        scrollTree.getViewport().setBackground(fondo);
        scrollTree.setPreferredSize(new Dimension(350, 625));
        panelIzq.add(scrollTree, BorderLayout.CENTER);

        // ===== Panel derecho =====
        panelDer = new JPanel(new BorderLayout());
        panelDer.setBackground(Color.darkGray);
        panelDer.setPreferredSize(new Dimension(300, 625));

        tabsPerfiles = new JTabbedPane();
        tabsPerfiles.setBackground(Color.darkGray);
        tabsPerfiles.setForeground(Color.WHITE);
        panelDer.add(tabsPerfiles, BorderLayout.CENTER);
        // ===== Split principal (izq/der) =====
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzq, panelDer);
        split.setDividerSize(5);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);
        split.setResizeWeight(0);
        panelprincipal.add(split, BorderLayout.CENTER);
    }

    private void openTableTab(String db, String table) {
        String tabKey = db + "." + table;
        if (openTableTabs.containsKey(tabKey)) {
            tabsPerfiles.setSelectedComponent(openTableTabs.get(tabKey));
            return;
        }

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.darkGray);

        // Barra superior
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        toolbar.setBackground(Color.darkGray);
        JLabel title = new JLabel(db + "." + table);
        title.setForeground(Color.WHITE);
        toolbar.add(title);

        JButton btnRefresh = new JButton("Refrescar");
        toolbar.add(btnRefresh);

        JLabel lblPage = new JLabel("Filas/página:");
        lblPage.setForeground(Color.WHITE);
        toolbar.add(lblPage);

        JSpinner spPage = new JSpinner(new SpinnerNumberModel(pageSizeDefault, 10, 10000, 10));
        toolbar.add(spPage);

        JButton btnPrev = new JButton("<<");
        JButton btnNext = new JButton(">>");
        toolbar.add(btnPrev);
        toolbar.add(btnNext);

        root.add(toolbar, BorderLayout.NORTH);

        // Sub-pestañas
        JTabbedPane inner = new JTabbedPane();
        inner.setBackground(Color.darkGray);
        inner.setForeground(Color.WHITE);
        root.add(inner, BorderLayout.CENTER);

        // Datos
        JTable tableData = new JTable();
        tableData.setBackground(Color.darkGray);
        tableData.setForeground(Color.WHITE);
        tableData.setGridColor(Color.GRAY);
        tableData.setSelectionBackground(new Color(75, 110, 175));
        tableData.setSelectionForeground(Color.WHITE);
        JScrollPane spData = new JScrollPane(tableData);
        spData.getViewport().setBackground(Color.darkGray);
        inner.addTab("Datos", spData);

        // Estructura (columns + DDL)
        JTextArea taStruct = new JTextArea();
        taStruct.setEditable(false);
        taStruct.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 13));
        taStruct.setBackground(Color.darkGray);
        taStruct.setForeground(Color.WHITE);
        JScrollPane spStruct = new JScrollPane(taStruct);
        spStruct.getViewport().setBackground(Color.darkGray);
        inner.addTab("Estructura", spStruct);

        JTextArea taDiagram = new JTextArea();
        taDiagram.setEditable(false);
        taDiagram.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 13));
        taDiagram.setBackground(Color.darkGray);
        taDiagram.setForeground(Color.WHITE);
        JScrollPane spDiag = new JScrollPane(taDiagram);
        spDiag.getViewport().setBackground(Color.darkGray);
        inner.addTab("Diagrama", spDiag);

        // Añadir pestaña y seleccionar
        String titulo = "tabla: " + table;
        addClosableTab(tabsPerfiles, "tabla: " + table, root);

        tabsPerfiles.setSelectedComponent(root);
        openTableTabs.put(tabKey, root);

        // Estado de paginación
        final int[] offset = {0};

        Runnable loadAll = () -> {
            int pageSize = (Integer) spPage.getValue();
            cargarDatosTabla(db, table, pageSize, offset[0], tableData);
         
        };

        btnRefresh.addActionListener(e -> loadAll.run());
        btnNext.addActionListener(e -> {
            offset[0] += (Integer) spPage.getValue();
            loadAll.run();
        });
        btnPrev.addActionListener(e -> {
            offset[0] = Math.max(0, offset[0] - (Integer) spPage.getValue());
            loadAll.run();
        });

        // Carga inicial
        loadAll.run();
    }

    private void cargarDatosTabla(String db, String table, int limit, int offset, JTable target) {
        if (connActual == null) {
            JOptionPane.showMessageDialog(this, "No hay conexión activa.");
            return;
        }
        String sql = "SELECT * FROM `" + db + "`.`" + table + "` LIMIT " + limit + " OFFSET " + offset;
        try (Statement st = connActual.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            target.setModel(buildTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error cargando datos: " + ex.getMessage());
        }
    }

    private static javax.swing.table.TableModel buildTableModel(ResultSet rs) throws SQLException {
        java.sql.ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        for (int i = 1; i <= colCount; i++) {
            model.addColumn(meta.getColumnLabel(i));
        }
        Object[] row = new Object[colCount];
        while (rs.next()) {
            for (int i = 1; i <= colCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row.clone());
        }
        return model;
    }

    private String nodoSeleccionado(JTree tree) {
        DefaultMutableTreeNode n = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        return (n == null) ? null : n.getUserObject().toString();
    }

    private javax.swing.JComponent crearEditorSQL() {
        JPanel root = new JPanel(new BorderLayout());

        // Top: toolbar + editor 
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.darkGray);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        toolbar.setBackground(Color.darkGray);
        btnRun = new JButton("Ejecutar (Ctrl+Enter)");
        btnRun.setBackground(new Color(51, 51, 51));
        btnRun.setForeground(Color.WHITE);
        btnRun.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        toolbar.add(btnRun);

        JLabel baseconectada = new JLabel(nombreselect);
        baseconectada.setForeground(Color.WHITE);
        toolbar.add(baseconectada);
        topPanel.add(toolbar, BorderLayout.NORTH);

        sqlEditor = new JTextArea();
        sqlEditor.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 14));
        sqlEditor.setText("-- Escribe tu SQL aquí\nSELECT 1;");
        sqlEditor.setBackground(Color.darkGray);
        sqlEditor.setForeground(Color.WHITE);
        sqlEditor.setCaretColor(Color.WHITE);
        sqlEditor.setBorder(null);

        JScrollPane spEditor = new JScrollPane(sqlEditor);
        spEditor.getViewport().setBackground(Color.darkGray);
        topPanel.add(spEditor, BorderLayout.CENTER);

        // Abajo: pestañas de resultados (locales al editor)
        JTabbedPane resultTabs = new JTabbedPane();
        resultTabs.setBackground(Color.darkGray);
        resultTabs.setForeground(Color.WHITE);

        resultTable = new JTable();
        resultTable.setBackground(Color.darkGray);
        resultTable.setForeground(Color.WHITE);
        resultTable.setGridColor(Color.GRAY);
        resultTable.setSelectionBackground(new Color(75, 110, 175));
        resultTable.setSelectionForeground(Color.WHITE);
        JScrollPane spTable = new JScrollPane(resultTable);
        spTable.getViewport().setBackground(Color.darkGray);
        resultTabs.addTab("Resultados", spTable);

        msgArea = new JTextArea(5, 60);
        msgArea.setEditable(false);
        msgArea.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 13));
        msgArea.setBackground(Color.darkGray);
        msgArea.setForeground(Color.WHITE);
        msgArea.setCaretColor(Color.WHITE);
        msgArea.setBorder(null);
        JScrollPane spMsg = new JScrollPane(msgArea);
        spMsg.getViewport().setBackground(Color.darkGray);
        resultTabs.addTab("Mensajes", spMsg);

        ddlArea = new JTextArea(12, 60);
        ddlArea.setEditable(false);
        ddlArea.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 13));
        ddlArea.setBackground(Color.darkGray);
        ddlArea.setForeground(Color.WHITE);
        ddlArea.setCaretColor(Color.WHITE);
        ddlArea.setBorder(null);
        JScrollPane spDDL = new JScrollPane(ddlArea);
        spDDL.getViewport().setBackground(Color.darkGray);
        resultTabs.addTab("DDL", spDDL);

        // Split vertical 50/50
        JSplitPane vertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, resultTabs);
        vertical.setResizeWeight(0.5);
        vertical.setContinuousLayout(true);
        root.add(vertical, BorderLayout.CENTER);

        // Atajos
        btnRun.addActionListener(e -> ejecutarSQLDesdeEditor());
        sqlEditor.getActionMap().put("run-sql", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ejecutarSQLDesdeEditor();
            }
        });

        return root;
    }

    private void ejecutarSQLDesdeEditor() {
        String sql = sqlEditor.getText().trim();
        if (sql.isEmpty()) {
            msgArea.append("No hay SQL.\n");
            return;
        }
        if (connActual == null) {
            msgArea.append("No hay conexión activa.\n");
            return;
        }

        try (Statement st = connActual.createStatement()) {
            boolean hasResultSet = st.execute(sql);

            if (hasResultSet) {
                // algo que devuelve datos
                try (ResultSet rs = st.getResultSet()) {
                    resultTable.setModel(bt.buildTableModel(rs));
                }
                og.appendMsg(msgArea, "Consulta ejecutada con éxito.");
            } else {
                // Es un DDL/DML
                int count = st.getUpdateCount();
                og.appendMsg(msgArea, "Comando ejecutado. Filas afectadas: " + count);
            }

        } catch (SQLException ex) {
            og.appendMsg(msgArea, "Error: " + ex.getMessage());
        }
    }

    private DefaultMutableTreeNode buscarNodoEnRaiz(String nombre) {
        for (int i = 0; i < raiz.getChildCount(); i++) {
            DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) raiz.getChildAt(i);
            if (nombre.equals(hijo.getUserObject().toString())) {
                return hijo;
            }
        }
        return null;
    }

    private void conectarperfil(String nombre) {
        usuario u = perfiles.get(nombre);

        if (u == null) {
            return;
        }

        ConnectMariadb cm = new ConnectMariadb();
        try {
            connActual = cm.conectar(u.getHost(), u.port, u.database, u.username, u.getPassword());
            System.out.println("host: " + u.getHost() + "username: " + u.username + "pass: " + u.getPassword());

            conexiones.put(nombre, connActual);
            agregarInstanciaAlArbol(connActual, nombre);

            /*panelDer.removeAll();
            editor();
            panelDer.revalidate();
            panelDer.repaint();*/
            javax.swing.JComponent ed = crearEditorSQL();
            String tabTitle = "Editor SQL (" + (nombreselect != null ? nombreselect : "sin conexión") + ")";
            tabsPerfiles.addTab(tabTitle, ed);
            tabsPerfiles.setSelectedComponent(ed);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void agregarInstanciaAlArbol(Connection cx, String nombreInstancia) throws SQLException {
        DefaultMutableTreeNode instanciaNode = buscarNodoEnRaiz(nombreInstancia);
        if (instanciaNode == null) {
            instanciaNode = new DefaultMutableTreeNode(nombreInstancia);
            raiz.add(instanciaNode);
        }

        instanciaNode.removeAllChildren();
        DefaultMutableTreeNode databasesNode = new DefaultMutableTreeNode("Databases");
        instanciaNode.add(databasesNode);

        try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery("SHOW DATABASES")) {
            while (rs.next()) {
                String db = rs.getString(1);
                System.out.println("db " + db);

                String d = db.toLowerCase(java.util.Locale.ROOT);
                if (d.equals("information_schema") || d.equals("mysql")
                        || d.equals("performance_schema") || d.equals("sys")) {
                    continue;
                }
                //subcarpetas
                DefaultMutableTreeNode dbNode = new DefaultMutableTreeNode(db);
                DefaultMutableTreeNode tablasNode = new DefaultMutableTreeNode("Tables");
                DefaultMutableTreeNode viewsNode = new DefaultMutableTreeNode("Views");
                DefaultMutableTreeNode seqNode = new DefaultMutableTreeNode("Sequences");
                DefaultMutableTreeNode indicesNode = new DefaultMutableTreeNode("Indexes");
                DefaultMutableTreeNode triggersNode = new DefaultMutableTreeNode("Triggers");
                DefaultMutableTreeNode proceNode = new DefaultMutableTreeNode("Procedures");
                DefaultMutableTreeNode funcNode = new DefaultMutableTreeNode("Functions");

                dbNode.add(tablasNode);
                dbNode.add(viewsNode);
                dbNode.add(seqNode);
                dbNode.add(indicesNode);
                dbNode.add(triggersNode);
                dbNode.add(proceNode);
                dbNode.add(funcNode);
                og.cargarObjetosDeBase(cx, db, tablasNode, viewsNode, seqNode, triggersNode);
                og.showprocedures(cx, db, proceNode, funcNode);
                og.cargarIndices(cx, db, indicesNode);
                databasesNode.add(dbNode);
            }
        }
        raiz.add(instanciaNode);

        ((DefaultTreeModel) tree.getModel()).reload(raiz);
        tree.expandRow(0);
    }

    private String[] extraerContextoObjeto(TreePath path) {
        if (path == null) {
            return null;
        }
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();
        if (padre == null) {
            return null;
        }

        String tipo = padre.getUserObject().toString(); // "Tables", "Views", "Triggers", etc.
        DefaultMutableTreeNode dbNode = (DefaultMutableTreeNode) padre.getParent();
        if (dbNode == null) {
            return null;
        }
        String db = dbNode.getUserObject().toString();
        // Verifica estructura esperada: dbNode.parent -> "Databases"
        DefaultMutableTreeNode databases = (DefaultMutableTreeNode) dbNode.getParent();
        if (databases == null || !"Databases".equals(databases.getUserObject().toString())) {
            return null;
        }

        String nombre = nodo.getUserObject().toString();
        return new String[]{db, tipo, nombre};
    }

    private String obtenerDDL(String db, String tipo, String nombre) throws SQLException {
        if (connActual == null) {
            throw new SQLException("No hay conexión activa.");
        }
        String sql;
        String ddlColPreferida = null; // nombre de columna que contiene el DDL

        switch (tipo) {
            case "Tables" -> {
                sql = "SHOW CREATE TABLE `" + db + "`.`" + nombre + "`";
                ddlColPreferida = "Create Table";
            }
            case "Views" -> {
                sql = "SHOW CREATE VIEW `" + db + "`.`" + nombre + "`";
                ddlColPreferida = "Create View";
            }
            case "Triggers" ->
                sql = "SHOW CREATE TRIGGER `" + db + "`.`" + nombre + "`";

            case "Procedures" -> {
                sql = "SHOW CREATE PROCEDURE `" + db + "`.`" + nombre + "`";
                ddlColPreferida = "Create Procedure";
            }
            case "Functions" -> {
                sql = "SHOW CREATE FUNCTION `" + db + "`.`" + nombre + "`";
                ddlColPreferida = "Create Function";
            }
            case "Sequences" -> {
                sql = "SHOW CREATE SEQUENCE `" + db + "`.`" + nombre + "`";
                ddlColPreferida = "Create Sequence";
            }
            default ->
                throw new SQLException("Tipo no soportado para DDL: " + tipo);
        }

        try (Statement st = connActual.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (!rs.next()) {
                throw new SQLException("No se obtuvo DDL para " + nombre);
            }

            if (ddlColPreferida != null) {
                try {
                    String ddl = rs.getString(ddlColPreferida);
                    if (ddl != null && !ddl.isBlank()) {
                        return ddl;
                    }
                } catch (SQLException ignore) {
                }
            }

            // busca la primera columna q tenga create
            java.sql.ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                String val = rs.getString(i);
                if (val != null && val.toUpperCase().contains("CREATE ")) {
                    return val;
                }
            }
            // caso ppara el trigger
            try {
                String val = rs.getString("SQL Original Statement");
                if (val != null && !val.isBlank()) {
                    return val;
                }
            } catch (SQLException ignore) {
            }

            throw new SQLException("No se encontró columna de DDL en la respuesta.");
        }
    }

    //muestra en ddl en la pesta;a del editor ddl
    private void mostrarDDL(String db, String tipo, String nombre, boolean abrirEnEditor) {
        try {
            String ddl = obtenerDDL(db, tipo, nombre);
            ddlArea.setText(ddl);
            ddlArea.setCaretPosition(0);
            tabs.setSelectedIndex(2);
            if (abrirEnEditor) {
                sqlEditor.setText(ddl);
                sqlEditor.requestFocusInWindow();
            }
            og.appendMsg(msgArea, "DDL cargado: " + tipo + " " + db + "." + nombre);
        } catch (SQLException ex) {
            og.appendMsg(msgArea, "Error DDL: " + ex.getMessage());
        }
    }

    private void jtablecolumnas() {
        modelocolumnas = new DefaultTableModel(new Object[][]{}, new String[]{"Column Name", "Data Type", "Not Null", "Auto Increment", "Key"}) {
            @Override
            public Class<?> getColumnClass(int c) {
                return switch (c) {
                    case 2, 3 ->
                        Boolean.class;
                    default ->
                        String.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 4;
            }

        };
        tabla_columnas.setModel(modelocolumnas);
    }

    private void guardarvista(String bd) {
        String nombrevista = txtnombrevista.getText();
        String sql = areavista.getText().trim();

        if (nombrevista.isEmpty()) {
            JOptionPane.showMessageDialog(framevista, "Ingresa el nombre de la vista.");
        }
        if (sql.isEmpty()) {
            JOptionPane.showMessageDialog(framevista, "Ingresa la sentencia SELECT.");
        }
        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        if (!sql.toUpperCase().startsWith("SELECT")) {
            JOptionPane.showMessageDialog(framevista, "El cuerpo de la vista debe iniciar con SELECT");
            return;
        }
        String sentencia = "CREATE VIEW `" + bd + "`.`" + nombrevista + "` AS " + sql;
        try (Statement st = connActual.createStatement()) {
            st.execute(sentencia);
            og.appendMsg(msgArea, "Vista creada" + bd + "." + nombrevista);
            if (nombreselect != null) {
                agregarInstanciaAlArbol(connActual, nombreselect);
            }
            ddlArea.setText(sentencia);
            ddlArea.setCaretPosition(0);
            tabs.setSelectedIndex(2);
            framevista.setVisible(false);
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(framevista, "Error al crear vista: " + ex.getMessage());
        }
    }

    private void addClosableTab(JTabbedPane tabbedPane, String title, Component content) {
        tabbedPane.addTab(title, content);
        int index = tabbedPane.indexOfComponent(content);

        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);

        JLabel lbl = new JLabel(title + " ");
        lbl.setForeground(Color.WHITE);

        JButton btnClose = new JButton("x");
        btnClose.setMargin(new Insets(0, 2, 0, 2));
        btnClose.setBorder(null);
        btnClose.setFocusPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setForeground(Color.LIGHT_GRAY);

        btnClose.addActionListener(e -> {
            int i = tabbedPane.indexOfComponent(content);
            if (i != -1) {
                tabbedPane.remove(i);
                openTableTabs.values().remove(content);
            }
        });

        tabHeader.add(lbl);
        tabHeader.add(btnClose);
        tabbedPane.setTabComponentAt(index, tabHeader);
    }

    private void mostrarDiagramaER(String db) {
        if (connActual == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay conexión activa.");
            return;
        }
        try {
            objetosgestor.ERModel model = og.buildERModel(connActual, db);
            mxGraph graph = og.buildERGraphJGraphX(model);
            mxGraphComponent comp = new mxGraphComponent(graph);
            comp.setConnectable(false);
            comp.setDragEnabled(true);
            comp.setPanning(true);
            comp.setToolTips(true);
            comp.getViewport().setOpaque(true);
            comp.getViewport().setBackground(java.awt.Color.WHITE);

            comp.zoomTo(0.9, true);
            comp.zoomAndCenter();

            javax.swing.JFrame f = new javax.swing.JFrame("ERD - " + db);
            f.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().add(comp, java.awt.BorderLayout.CENTER);
            f.setSize(980, 720);
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error generando ERD: " + ex.getMessage());
        }
    }

    //MAIN
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areavista;
    private javax.swing.JButton btaccept;
    private javax.swing.JButton btaggcolumna;
    private javax.swing.JButton btaggcolumna2;
    private javax.swing.JButton btcancelar;
    private javax.swing.JButton btfinalizar;
    private javax.swing.JButton btguardarvista;
    private javax.swing.JButton btprobarconexion;
    private javax.swing.JButton btquitarcolumna;
    private javax.swing.JCheckBox cbautoincrement;
    private javax.swing.JComboBox<String> cbdatatype;
    private javax.swing.JComboBox<String> cbkeys;
    private javax.swing.JCheckBox cbnotnull;
    private javax.swing.JCheckBox cbuniquetype;
    private javax.swing.JCheckBox chekbguardarcontra;
    private javax.swing.JFrame framecolumns;
    private javax.swing.JFrame frametables;
    private javax.swing.JFrame frameuser;
    private javax.swing.JFrame framevista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbajusteconexion;
    private javax.swing.JLabel lbauthentication;
    private javax.swing.JLabel lbauthentication1;
    private javax.swing.JLabel lbauthentication2;
    private javax.swing.JLabel lbconnect;
    private javax.swing.JLabel lbcuadroserver;
    private javax.swing.JLabel lbdatabase;
    private javax.swing.JLabel lbpassword;
    private javax.swing.JLabel lbport;
    private javax.swing.JLabel lbserver1;
    private javax.swing.JLabel lbserver2;
    private javax.swing.JLabel lburl;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JPanel paneltablas;
    private javax.swing.JRadioButton rbhost;
    private javax.swing.JTable tabla_columnas;
    private javax.swing.JTextField txtdatabase;
    private javax.swing.JTextField txtnamecolumn;
    private javax.swing.JTextField txtnombretabla;
    private javax.swing.JTextField txtnombrevista;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtport;
    private javax.swing.JTextField txtserverhost;
    private javax.swing.JTextField txturl;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
