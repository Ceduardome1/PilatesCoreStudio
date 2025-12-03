package dominio;

public class Direccion {

	private final int cp;
	private final short numExt;
	private final String calle, colonia;
	
	public Direccion(int cp, short numExt, String calle, String colonia) {
		this.cp = cp;
		this.numExt = numExt;
		this.calle = calle;
		this.colonia = colonia;
	}

	public int getCp() {
		return cp;
	}

	public short getNumExt() {
		return numExt;
	}

	public String getCalle() {
		return calle;
	}

	public String getColonia() {
		return colonia;
	}
	
}