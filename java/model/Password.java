package model;

public class Password {
	private String oldPas;
	private String newPas;
	private String conPas;
	
	public Password() {
		
	}
	
	public Password(String oldPas, String newPas, String conPas) {
		this.oldPas = oldPas;
		this.newPas = newPas;
		this.conPas = conPas;
	}
	
	public void setOldPas(String oldPas) {
		this.oldPas = oldPas;
	}
	
	public String getOldPas() {
		return oldPas;
	}
	
	public void setNewPas(String newPas) {
		this.newPas = newPas;
	}
	
	public String getNewPas() {
		return newPas;
	}
	
	public void setConPas(String conPas) {
		this.conPas = conPas;
	}
	
	public String getConPas() {
		return conPas;
	}
}
