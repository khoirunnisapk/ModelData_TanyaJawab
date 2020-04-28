package model;

import java.util.ArrayList;
import java.util.List;

public class Pertanyaan {
	private String kode;
	boolean status;
	String uname;
	String kategori;
	String tingkat;
	int poinDiberikan;
	String pertanyaan;
	List<String> idJawabanList=new ArrayList<>();
	List<Jawaban> answerList = new ArrayList<>();	
	public Pertanyaan() {
		
	}
	public Pertanyaan(String kode, boolean status, String uname, String kategori, String tingkat, int poinDiberikan, String pertanyaan, List<String> idJawaban) {
		this.kode = kode;
		this.status = status;
		this.uname = uname;
		this.kategori = kategori;
		this.tingkat = tingkat;
		this.poinDiberikan = poinDiberikan;
		this.pertanyaan = pertanyaan;
		this.idJawabanList = idJawaban;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getTingkat() {
		return tingkat;
	}
	public void setTingkat(String tingkat) {
		this.tingkat = tingkat;
	}
	public int getPoinDiberikan() {
		return poinDiberikan;
	}
	public void setPoinDiberikan(int poinDiberikan) {
		this.poinDiberikan = poinDiberikan;
	}
	public String getPertanyaan() {
		return pertanyaan;
	}
	public void setPertanyaan(String pertanyaan) {
		this.pertanyaan = pertanyaan;
	}
	public List<String> getIdJawabanList() {
		return idJawabanList;
	}
	public void setIdJawabanList(List<String> idJawaban) {
		this.idJawabanList = idJawaban;
	}
	public List<Jawaban> dapatkanAnswer() {
		return answerList;
	}
	public final void addJawaban(Jawaban jawaban) {
		this.answerList.add(jawaban);
		System.out.println("added!!!!");
	}
	public void ubahAnswer(List<Jawaban> answer) {
		this.answerList = answer;
	}
}
