import javax.swing.table.DefaultTableModel;


public class EmpTM extends DefaultTableModel {
	public EmpTM (Object mezokneve[], int sorok){
		super(mezokneve, sorok);		
	}
	
	public boolean isCellEditable(int sor, int oszlop_){
		if (oszlop_==0){return true;}
		return false;
	}
	
	public Class<?> getColumnClass(int index){
		if (index==0) return(Boolean.class);
		else if (index==1 || index==5) return(Integer.class);
		return(String.class);
	}


	
}
