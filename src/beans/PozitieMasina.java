package beans;

import java.io.Serializable;

public class PozitieMasina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deviceId;
	private String codSofer;
	private String nrAuto;
	private String latitudine;
	private String longitudine;
	private String data;
	private String viteza;

	public PozitieMasina() {

	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCodSofer() {
		return codSofer;
	}

	public void setCodSofer(String codSofer) {
		this.codSofer = codSofer;
	}

	public String getNrAuto() {
		return nrAuto;
	}

	public void setNrAuto(String nrAuto) {
		this.nrAuto = nrAuto;
	}

	public String getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}

	public String getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getViteza() {
		return viteza;
	}

	public void setViteza(String viteza) {
		this.viteza = viteza;
	}

	public String toString() {
		return "PozitieMasina [deviceId=" + deviceId + ", codSofer=" + codSofer + ", nrAuto=" + nrAuto + ", latitudine=" + latitudine
				+ ", longitudine=" + longitudine + ", data=" + data + ", viteza=" + viteza + "]";
	}

}
