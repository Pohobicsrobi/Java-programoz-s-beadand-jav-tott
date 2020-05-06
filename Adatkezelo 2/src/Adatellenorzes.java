import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Adatellenorzes {
	
	public boolean filled (JTextField mezo, String adat) {
		String hossz = mezoellenorzes (mezo);
		if (hossz.length()>0) return true;
		else {
			uzenet("Hiba: a(z) "+adat+" mezõ üres",0);
			return false;
		}
	}
	public boolean goodInt(JTextField mezo ,String adat) {
		String hossz =mezoellenorzes (mezo);
		boolean joszam = filled(mezo,adat);
		if (joszam) try {
			Integer.parseInt(hossz);
		} catch(NumberFormatException kivetel){
			uzenet("A(z) "+adat+" mezõben hibás számadat!",0);
			joszam = false;
		}
		return joszam;

}
	public String mezoellenorzes(JTextField szoveg) {
		return szoveg.getText();
	}
	
	public void uzenet(String szoveg1, int tipus) {
		JOptionPane.showMessageDialog(null, szoveg1, "Program üzenet", tipus);

		
	}
	
	public boolean datumformatumellenorzes(String datum) {
		SimpleDateFormat jodatum = new SimpleDateFormat("yyyy.MM.dd");
		try {
			java.util.Date pdate = jodatum.parse (datum);
			if (!jodatum.format(pdate).equals(datum)) {return false;}
			return true;
		} catch(ParseException kivetel_1) {return false;}
	}
	
	public boolean jodatum(JTextField mezo, String adat) {
		String hossz=mezoellenorzes(mezo);
		boolean joszam = filled (mezo,adat);
		if (joszam && datumformatumellenorzes(hossz))return true;
		else {
			uzenet ("A(z) "+adat+" Mezõben hibás a dátum",0);
			return false;	
		}
		
	}
	
	public boolean filled (JTextField mezo) {
		String hossz =mezoellenorzes (mezo);
		if (hossz.length()>0) return true;
		else return false;
	}
	
	public int stringToInt(String hossz) {
		int x=-1;
		try { x=Integer.valueOf(hossz);}
		catch (NumberFormatException kivetel_2) {
			uzenet("stringToInt: "+kivetel_2.getMessage(),0);
		}
		return x;
}
}