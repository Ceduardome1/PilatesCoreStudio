package dominio;

public final class ReglasNegocio {

	private static final int minAnticipacionReserva = 60
	, limInfDiasCreacionClase = 0
	, limSupDiasCreacionClase = 60
	, margenMinutosCreacionClase = 60
	, minDuracionClase = 60
	, capacidadCamasSala = 50;
	
	public static int getMinanticipacionreserva() {
		return minAnticipacionReserva;
	}

	public static int getLimInfDiasCreacionClase() {
		return limInfDiasCreacionClase;
	}
	
	public static int getLimSupDiasCreacionClase() {
		return limSupDiasCreacionClase;
	}

	public static int getMargenminutoscreacionclase() {
		return margenMinutosCreacionClase;
	}

	public static int getMinDuracionClase() {
		return minDuracionClase;
	}

	public static int getCapacidadCamasSala() {
		return capacidadCamasSala;
	}
	
}