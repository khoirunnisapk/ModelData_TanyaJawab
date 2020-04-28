package model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
public class Person {
	private String password;
	String uname;
	String tingkatPend;
	String level;
	int poin;
	List<String> idPertanyaan=new ArrayList<>();
	List<String> idJawaban=new ArrayList<>();
	List<Pertanyaan> questionsList = new ArrayList<>();
	List<Jawaban> answerList = new ArrayList<>();
	

	public Person(String password, String uname, String tingkatPend, String level, int poin, List<String> idPertanyaan, List<String>idJawaban) {
		this.password=password;
		this.uname = uname;
		this.tingkatPend = tingkatPend;
		this.level = level;
		this.poin = poin;
		this.idPertanyaan = idPertanyaan;
		this.idJawaban = idJawaban;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getTingkatPend() {
		return tingkatPend;
	}
	public void setTingkatPend(String tingkatPend) {
		this.tingkatPend = tingkatPend;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getPoin() {
		return poin;
	}
	public void setPoin(int poin) {
		this.poin = poin;
	}
	public List<String> getIdPertanyaan() {
		return idPertanyaan;
	}
	public void setIdPertanyaan(List<String> idPertanyaan) {
		this.idPertanyaan = idPertanyaan;
	}
	public final void addIdPertanyaan(String pertanyaan) {
		this.idPertanyaan.add(pertanyaan);
	}
	public List<String> getIdJawaban() {
		return idJawaban;
	}
	public void setIdJawaban(List<String> idJawaban) {
		this.idJawaban = idJawaban;
	}
	public List<Pertanyaan> dapatkanQuestions() {
		return questionsList;
	}
	public void ubahQuestions(List<Pertanyaan> questions) {
		this.questionsList = questions;
	}
	public List<Jawaban> dapatkanAnswer() {
		return answerList;
	}
	public void ubahAnswer(List<Jawaban> answer) {
		this.answerList = answer;
	}
	public final void addQuestions(Pertanyaan pertanyaan) {
		this.questionsList.add(pertanyaan);
		System.out.println("added!!!!");
	}
	public final void addAnswer(Jawaban jawab) {
		this.answerList.add(jawab);
		System.out.println("added!!!!");
	}
	public Person() {
		
	}
}
