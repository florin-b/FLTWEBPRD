package beans;

import utils.Formatting;

public class BeanSmsEmis {

	private String codSofer;
	private String document;
	private String client;
	private String dataSosireClient;
	private String dataEmitereSms;
	private String interval;

	public String getCodSofer() {
		return codSofer;
	}

	public void setCodSofer(String codSofer) {
		this.codSofer = codSofer;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDataSosireClient() {
		return dataSosireClient;
	}

	public void setDataSosireClient(String dataSosireClient) {
		this.dataSosireClient = dataSosireClient;
	}

	public String getDataEmitereSms() {
		return dataEmitereSms;
	}

	public void setDataEmitereSms(String dataEmitereSms) {
		this.dataEmitereSms = dataEmitereSms;
	}

	public long getInterval() {
		return Formatting.dateDiffMin(this.dataEmitereSms, this.dataSosireClient);
	}

}
