import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class Adatbaziscsatlakozas {
	private Statement allitas = null;
	private Connection kapcsolat = null;
	private ResultSet eredmeny = null;
	
	public void Regisztracio(){
		try {
			Class.forName("org.sqlite.JDBC");
			uzenet("Sikeres driver regisztráció!", 1);
		}catch (ClassNotFoundException kivetel){
			uzenet("Hibás driver regisztráció!"+kivetel.getMessage(), 0);
		}
	}

	public void uzenet(String msg, int tipus){
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);;
	}
	
	public void Csatlakozas(){
		try{
			String adatbazieleres = "jdbc:sqlite:C:/Users/Robi/Downloads/JDBC/sqlite-tools-win32-x86-3310100/teniszezok";
			kapcsolat = DriverManager.getConnection(adatbazieleres);
			uzenet("Kapcsolódás OK!", 1);
		}catch (SQLException kivetel){
			uzenet("JDBC Connect: "+kivetel.getMessage(), 0);
		}
	}
	
	public void Szetcsatlakozas(){
		try{
			kapcsolat.close();
			uzenet("Szétkapcsolás OK!", 1);
		}catch (SQLException kivetel) {uzenet(kivetel.getMessage(), 0);}
	}
	
	public EmpTM Adatolvasas(){
		Object teniszezok[] = {"jel","azonosito", "nev", "szulido", "nemzetiseg", "ranglistapont"};
		EmpTM adatok = new EmpTM(teniszezok, 0);
		String nev="", szulido="", nemzetiseg="", x="\t";
		int azonosito=0, ranglistapont=0;
		String sqlp="select azonosito, nev, szulido, nemzetiseg, ranglistapont from teniszezok";
		try {
			allitas = kapcsolat.createStatement();
			eredmeny = allitas.executeQuery(sqlp);
			while (eredmeny.next()){
				azonosito = eredmeny.getInt("azonosito");
				nev = eredmeny.getString("nev");
				szulido = eredmeny.getString("szulido");
				nemzetiseg = eredmeny.getString("nemzetiseg");
				ranglistapont = eredmeny.getInt("ranglistapont");
				adatok.addRow(new Object[]{false, azonosito, nev, szulido, nemzetiseg, ranglistapont});
				}
		
			eredmeny.close();		
	}catch (SQLException kivetel) {uzenet(kivetel.getMessage(), 0);}
	return adatok;
	}
	
public void ujteniszezo(String azonosito, String nev, String szulido, String nemzetiseg, String ranglistapont ) {
		String sqlpadatbazis ="insert into teniszezok values("+azonosito+",\""+nev+"\", \""+szulido+"\", \""+nemzetiseg+"\","+ranglistapont+")";
		try {
			allitas = kapcsolat.createStatement();
			allitas.execute(sqlpadatbazis);
			uzenet("Új teniszezõ sikeresen bekerült a listába!",1);
		}	catch(SQLException kivetel) {
			uzenet("JDBC insert: "+kivetel.getMessage(),0 );
		
		}
	}
	
public void teniszezotorles(String azonosito) {
	String sqlpadatbazis = "delete from teniszezok where azonosito ="+azonosito;
	try {
		allitas = kapcsolat.createStatement();
		allitas.execute(sqlpadatbazis);}
	catch (SQLException kivetel) {
		uzenet("JDBC Delete: "+kivetel.getMessage(),0);
		
	}
}


public void teniszezomodositas(String azonosito, String mnev, String madat) {
	String sqlpadatbazis = "update teniszezok set "+mnev+"='"+madat+"' where azonosito="+azonosito;
	try {
		allitas = kapcsolat.createStatement();
		allitas.execute(sqlpadatbazis);}
	catch (SQLException kivetel) {
		uzenet("JDBC Update: "+kivetel.getMessage(),0);
		
	}

}
}



