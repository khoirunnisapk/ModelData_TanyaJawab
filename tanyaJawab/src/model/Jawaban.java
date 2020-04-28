package model;

public class Jawaban {
	private String kodeJawaban;
	String kodeSoal;
	String uname;
	boolean status;
	String jawaban;
	int vote;
	
	public Jawaban() {
		
	}
	public Jawaban(String kode, String uname, boolean status, String jawaban, int vote) {
		this.kodeJawaban = kode;
//		this.kodeSoal = kodeSoal;
		this.uname=uname;
		this.status = status;
		this.jawaban = jawaban;
		this.vote = vote;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getKodeJawaban() {
		return kodeJawaban;
	}
	public void setKodeJawaban(String kode) {
		this.kodeJawaban = kode;
	}
	public String getKodeSoal() {
		return kodeSoal;
	}
	public void setKodeSoal(String kodeSoal) {
		this.kodeSoal = kodeSoal;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getJawaban() {
		return jawaban;
	}
	public void setJawaban(String jawaban) {
		this.jawaban = jawaban;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
}
