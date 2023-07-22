
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.View;
import model.ModelUser;
import model.AdministratorSQL;
public class Control  {
    
    //Definimos los objetos
    private ModelUser objUser;
    private View objView;
    private AdministratorSQL objSQL;
    DefaultTableModel modelo = new DefaultTableModel();
    //AdministratorSQL dao = new AdministratorSQL();
    
    //Se crea el constructor
    public Control (View objView, ModelUser objUser){
        
        //relacionamos los objetos
        this.objView = objView;
        this.objUser= objUser;
        this.objSQL=new AdministratorSQL();
        //se llama la funcion que inicia la interfaz
        startView();
        objView.btnregister.addActionListener(ObjbtnRegister);
        objView.btnconsult.addActionListener(ObjbtnConsult);
        
    }

    public Control(View v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //se muestra interfaz y se centra
    private void startView(){
        objView.setVisible(true);
        objView.setLocationRelativeTo(null);
        
        for (int i=1;i<=31;i++){
            objView.optday.addItem(String.valueOf(i));
        }
        for (int j=1900; j<=2100;j++){
            objView.optyear.addItem(String.valueOf(j));
        }  
        for (int k=1;k<=12;k++){
            objView.optmonth.addItem(String.valueOf(k));
        }
    }
    //en esta linea se envian datos reales a la base de datos
    
    private void establish_user(){
        objUser.setName(objView.txtname.getText());
        objUser.setId(Integer.parseInt(objView.txtid.getText()));
        String date =objView.optday.getSelectedItem()+"-"+objView.optmonth.getSelectedItem()+"-"+objView.optyear.getSelectedItem();
        objUser.setBirthday(date);
    }
    
    //Accion para el botonregistrar
    ActionListener ObjbtnRegister = new ActionListener (){
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean confirm = objSQL.connectSQL();
            if(confirm){
                establish_user();
                objSQL.register(objUser.getName(),objUser.getBirthday(),objUser.getId());
            }
        }   
    };  
    //Accion para el consultar
    ActionListener ObjbtnConsult = new ActionListener (){
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean confirm = objSQL.connectSQL();
            if(confirm){
                listar(objView.tbusers);
            }
               
        }   
    };   
    //
    public void listar(JTable tbusers) {
        centrarCeldas(tbusers);
        modelo = (DefaultTableModel) tbusers.getModel();
        tbusers.setModel(modelo);
        List<ModelUser> lista = objSQL.consult();
        Object[] objeto = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getName();
            objeto[1] = lista.get(i).getBirthday();
            objeto[2] = lista.get(i).getId();
            modelo.addRow(objeto);
        }
        //medidas de la tabla
        tbusers.setRowHeight(35);
        tbusers.setRowMargin(10);
    }
    
    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < objView.tbusers.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

}
