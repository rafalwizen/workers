package wizen.rafal.workers.rest;

public class HelperTransferObject {

	private int personalIdentityNumber;
	private boolean start;
	
	public HelperTransferObject() {
		
	}
	
	public HelperTransferObject(int personalIdentityNumber, boolean start) {
		this.personalIdentityNumber = personalIdentityNumber;
		this.start = start;
	}

	public int getPersonalIdentityNumber() {
		return personalIdentityNumber;
	}

	public void setPersonalIdentityNumber(int personalIdentityNumber) {
		this.personalIdentityNumber = personalIdentityNumber;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
}
