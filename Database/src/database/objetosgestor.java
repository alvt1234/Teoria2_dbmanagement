
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import java.util.*;

public class objetosgestor {

    public objetosgestor() {
    }
    
    
    public void appendMsg(JTextArea msgArea,String msg) {
        msgArea.append(msg + "\n");
        msgArea.setCaretPosition(msgArea.getDocument().getLength());
    }

    public void cargarObjetosDeBase(Connection cx, String db, DefaultMutableTreeNode tablasNode, DefaultMutableTreeNode viewsNode,DefaultMutableTreeNode seqNode,DefaultMutableTreeNode triggersNode) {

        String qFull = "SHOW FULL TABLES FROM `" + db + "`";
        try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(qFull)) {
            while (rs.next()) {
                String nombre = rs.getString(1);
                String tipo = rs.getString(2); // BASE TABLE, VIEW, SEQUENCE
                DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(nombre);
                if ("BASE TABLE".equalsIgnoreCase(tipo)) {
                     DefaultMutableTreeNode nodoTabla = new DefaultMutableTreeNode(nombre);
                    cargarColumnas(cx, db, nombre, nodoTabla);
                    tablasNode.add(nodoTabla);
                } else if ("VIEW".equalsIgnoreCase(tipo)) {
                    viewsNode.add(hijo);
                } else if ("SEQUENCE".equalsIgnoreCase(tipo)) {
                    seqNode.add(hijo);
                }
            }
        } catch (SQLException ex) {
            appendMsg(null,"Error listando tablas/views/sequences en " + db + ": " + ex.getMessage());
        }

        //Triggers
        String qTrig = "SHOW TRIGGERS FROM `" + db + "`";
        try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(qTrig)) {
            while (rs.next()) {
                String triggerName = rs.getString("Trigger"); 
                triggersNode.add(new DefaultMutableTreeNode(triggerName));
            }
        } catch (SQLException ex) {
            appendMsg(null,"Aviso TRIGGERS " + db + ": " + ex.getMessage());
        }
        
        
    }
    
    public void showprocedures(Connection cx, String db,DefaultMutableTreeNode nProc, DefaultMutableTreeNode nFunc) throws SQLException{
        try(Statement st=cx.createStatement()){
            ResultSet rs=st.executeQuery("SHOW PROCEDURE STATUS WHERE Db = '"+ db +"'");
            while(rs.next()){
                nProc.add(new DefaultMutableTreeNode(rs.getString("Name")));
            }
        }
        try(Statement st=cx.createStatement()){
            ResultSet rs=st.executeQuery("SHOW FUNCTION STATUS WHERE Db = '"+ db +"'");
            while(rs.next()){
                nFunc.add(new DefaultMutableTreeNode(rs.getString("Name")));
            }
        }
    }
    
        public void cargarIndices(Connection cx, String db,
            DefaultMutableTreeNode nIdx) throws SQLException {
        try (Statement st = cx.createStatement();
             ResultSet rsTab = st.executeQuery("SHOW FULL TABLES FROM `" + db + "`")) {
            while (rsTab.next()) {
                String t = rsTab.getString(1);
                String tipo = rsTab.getString(2);
                if (!"BASE TABLE".equalsIgnoreCase(tipo)) continue;
                DefaultMutableTreeNode nodoTabla = new DefaultMutableTreeNode(t);
                nIdx.add(nodoTabla);
                try (Statement st2 = cx.createStatement();
                     ResultSet rs = st2.executeQuery("SHOW INDEX FROM `" + db + "`.`" + t + "`")) {
                    while (rs.next()) {
                        String idxName = rs.getString("Key_name");
                        boolean nonunique = rs.getInt("Non_unique") == 1;
                        nodoTabla.add(new DefaultMutableTreeNode(
                            idxName + (nonunique ? "" : " (UNIQUE)")));
                    }
                } 
            }
        }
    }
        
        public void cargarColumnas(Connection cx, String db, String table, DefaultMutableTreeNode nodoTabla) throws SQLException{
DefaultMutableTreeNode columnsNode = new DefaultMutableTreeNode("Columns");
    nodoTabla.add(columnsNode);

    String sql = "SHOW COLUMNS FROM `" + db + "`.`" + table + "`";
    try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            String name     = rs.getString("Field");   // nombre de la columna
            String type     = rs.getString("Type");    
            String isNull   = rs.getString("Null");    
            String key      = rs.getString("Key");     
            String defVal   = rs.getString("Default"); 
            String extra    = rs.getString("Extra");   

            StringBuilder label = new StringBuilder();
            label.append(name).append(" : ").append(type);

            if ("NO".equalsIgnoreCase(isNull))  label.append(" NOT NULL");
            if (defVal != null)                 label.append(" DEFAULT ").append(defVal);
            if (key != null && !key.isEmpty())  label.append(" [").append(key).append("]");
            if (extra != null && !extra.isEmpty()) label.append(" {").append(extra).append("}");

            columnsNode.add(new DefaultMutableTreeNode(label.toString()));
        }
    }
        }
    
    public static class ERColumn {
    public final String name, type;
    public final boolean pk, notNull;
    public ERColumn(String name, String type, boolean pk, boolean notNull) {
        this.name = name; this.type = type; this.pk = pk; this.notNull = notNull;
    }
}
public static class ERForeignKey {
    public final String name, table, refTable;
    public final List<String> columns, refColumns;
    public ERForeignKey(String name, String table, List<String> columns, String refTable, List<String> refColumns) {
        this.name = name; this.table = table; this.columns = columns; this.refTable = refTable; this.refColumns = refColumns;
    }
}
public static class ERTable {
    public final String name;
    public final Map<String, ERColumn> columns = new LinkedHashMap<>();
    public final Set<String> pkCols = new LinkedHashSet<>();
    public final List<ERForeignKey> fks = new ArrayList<>();
    public ERTable(String name) { this.name = name; }
}
public static class ERModel {
    public final Map<String, ERTable> tables = new LinkedHashMap<>();
}

// Tablas 
public List<String> getTablesNoInfo(Connection cx, String db) throws SQLException {
    List<String> out = new ArrayList<>();
    String sql = "SHOW FULL TABLES FROM `" + db + "` WHERE Table_type='BASE TABLE'";
    try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) out.add(rs.getString(1));
    }
    return out;
}

public String getCreateTable(Connection cx, String db, String table) throws SQLException {
    String sql = "SHOW CREATE TABLE `" + db + "`.`" + table + "`";
    try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        if (rs.next()) return rs.getString(2);
    }
    throw new SQLException("No se pudo obtener SHOW CREATE TABLE para " + table);
}

// Columnas 
public void fillColumns(Connection cx, String db, ERTable t) throws SQLException {
    String sql = "SHOW COLUMNS FROM `" + db + "`.`" + t.name + "`";
    try (Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            String name   = rs.getString("Field");
            String type   = rs.getString("Type");
            String isNull = rs.getString("Null"); 
            String key    = rs.getString("Key");  
            boolean pk = "PRI".equalsIgnoreCase(key);
            boolean notNull = "NO".equalsIgnoreCase(isNull);
            t.columns.put(name, new ERColumn(name, type, pk, notNull));
            if (pk) t.pkCols.add(name);
        }
    }
}


private void parsePrimaryKeyFromDDL_ER(String ddl, ERTable t) {
    var pkP = java.util.regex.Pattern.compile("PRIMARY\\s+KEY\\s*\\(([^\\)]+)\\)", java.util.regex.Pattern.CASE_INSENSITIVE);
    var m = pkP.matcher(ddl);
    if (m.find()) {
        for (String part : m.group(1).split(",")) {
            String col = part.replace("`","").trim();
            if (!col.isEmpty()) {
                t.pkCols.add(col);
                ERColumn c = t.columns.get(col);
                if (c != null) t.columns.put(col, new ERColumn(c.name, c.type, true, c.notNull));
            }
        }
    }
}

// FKs desde DDL
private List<ERForeignKey> parseFKsFromDDL_ER(String ddl, String table) {
    List<ERForeignKey> out = new ArrayList<>();
    var fkP = java.util.regex.Pattern.compile(
        "CONSTRAINT\\s+`?([^`\\s]+)`?\\s+FOREIGN\\s+KEY\\s*\\(([^\\)]+)\\)\\s+REFERENCES\\s+`?([^`\\s]+)`?\\s*\\(([^\\)]+)\\)",
        java.util.regex.Pattern.CASE_INSENSITIVE);
    var m = fkP.matcher(ddl);
    while (m.find()) {
        String name = m.group(1), refTable = m.group(3);
        List<String> cols = new ArrayList<>(), refCols = new ArrayList<>();
        for (String c : m.group(2).split(",")) cols.add(c.replace("`","").trim());
        for (String c : m.group(4).split(",")) refCols.add(c.replace("`","").trim());
        out.add(new ERForeignKey(name, table, cols, refTable, refCols));
    }
    return out;
}


public ERModel buildERModel(Connection cx, String db) throws SQLException {
    ERModel model = new ERModel();
    for (String tname : getTablesNoInfo(cx, db)) {
        ERTable t = new ERTable(tname);
        fillColumns(cx, db, t);
        String ddl = getCreateTable(cx, db, tname);
        parsePrimaryKeyFromDDL_ER(ddl, t);
        t.fks.addAll(parseFKsFromDDL_ER(ddl, tname));
        model.tables.put(tname, t);
    }
    return model;
}

public mxGraph buildERGraphJGraphX(ERModel model) {
    mxGraph graph = new mxGraph();
    graph.setHtmlLabels(true);
    Object parent = graph.getDefaultParent();

    Map<String, Object> vStyle = graph.getStylesheet().getDefaultVertexStyle();
    vStyle.put("rounded", "1");
    vStyle.put("whiteSpace", "wrap");
    vStyle.put("html", "1");
    vStyle.put("align", "left");
    vStyle.put("verticalAlign", "top");
    vStyle.put("spacingLeft", 8);
    vStyle.put("spacingTop", 6);
    vStyle.put("fontSize", 11);
    vStyle.put("fontColor", "#000000");
    vStyle.put("strokeColor", "#90A4AE");
    vStyle.put("fillColor", "#E8F0FE");
    vStyle.put("resizable", "0");

    
    Map<String, Object> eStyle = graph.getStylesheet().getDefaultEdgeStyle();
    eStyle.put("rounded", "1");
    eStyle.put("strokeColor", "#607D8B");
    eStyle.put("fontSize", 10);
    eStyle.put("fontColor", "#37474F");

    
    final int COL_LIMIT = 14;  

    graph.getModel().beginUpdate();
    try {
        Map<String, Object> vmap = new HashMap<>();

        for (ERTable t : model.tables.values()) {
            // Columnas FK para marcar FK
            Set<String> fkCols = new HashSet<>();
            for (ERForeignKey fk : t.fks) fkCols.addAll(fk.columns);

            StringBuilder sb = new StringBuilder();
            sb.append("<html>")
              .append("<div style='font-size:12px;'><b>").append(t.name).append("</b></div>")
              .append("<div style='font-size:11px; line-height:1.2;'>");

            int count = 0;
            for (ERColumn c : t.columns.values()) {
                if (count == COL_LIMIT) {
                    int restantes = t.columns.size() - COL_LIMIT;
                    if (restantes > 0) {
                        sb.append("<span style='color:#555;'>+").append(restantes).append(" más…</span><br/>");
                    }
                    break;
                }
                sb.append("&nbsp;&nbsp;– ").append(c.name).append(" : ").append(c.type);
                if (t.pkCols.contains(c.name)) sb.append(" <b>[PK]</b>");
                if (fkCols.contains(c.name))   sb.append(" [FK]");
                if (c.notNull)                 sb.append(" <i>(NN)</i>");
                sb.append("<br/>");
                count++;
            }
            sb.append("</div></html>");

            
            int rowsMostradas = Math.min(COL_LIMIT, t.columns.size());
            int h = 28 + rowsMostradas * 16;  
            int w = 320;                      
            Object v = graph.insertVertex(parent, null, sb.toString(), 20, 20, w, h, "");
            vmap.put(t.name, v);
        }

        
        for (ERTable t : model.tables.values()) {
            for (ERForeignKey fk : t.fks) {
                Object from = vmap.get(fk.table);
                Object to   = vmap.get(fk.refTable);
                if (from != null && to != null) {
                    String elabel = String.join(",", fk.columns) + " → " + String.join(",", fk.refColumns);
                    graph.insertEdge(parent, null, elabel, from, to);
                }
            }
        }
    } finally {
        graph.getModel().endUpdate();
    }

    
    com.mxgraph.layout.mxFastOrganicLayout layout = new com.mxgraph.layout.mxFastOrganicLayout(graph);
    layout.setForceConstant(160);    
    layout.setMinDistanceLimit(60);  
    layout.setInitialTemp(3.0);      
    layout.setMaxIterations(2000);   
    layout.setDisableEdgeStyle(false);
    layout.execute(graph.getDefaultParent());

    return graph;
}

}
