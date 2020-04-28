package controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import model.Jawaban;

import model.Person;
import model.Pertanyaan;



public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String currUname;
	String idPertanyaan;
	String status;
	String pertanyaan;
	public ActionController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("ACTION = "+action);
		MongoDBUtils mongodbUtils = new MongoDBUtils();
		if("login".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} else if("signup".contentEquals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
			rd.forward(request, response);
		} else if("to_login".equals(action)) {
			this.currUname = request.getParameter("username");
			String password = request.getParameter("password");
			boolean result = mongodbUtils.authenticator(this.currUname, password);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			
		} else if("to_signup".equals(action)) {
			this.currUname = request.getParameter("username");
			String education = request.getParameter("education");
			String password = request.getParameter("password");
			String level = "pemula";
			int poin = 50;
			boolean exist = mongodbUtils.userExist(this.currUname);
			if(!exist) {
				boolean result = mongodbUtils.insertObjectPerson(password, this.currUname, education, level, poin);
				if(result) {
					RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
					rd.forward(request, response);
				}		
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			
		} else if("Retrieve".equals(action)){
			showQData(request, response, mongodbUtils);
		}else if("Tanya".equals(action)){
			RequestDispatcher rd = request.getRequestDispatcher("/tanya.jsp");
			rd.forward(request, response);
		}else if("submitQuestion".equals(action)) {
			String uname = currUname;
			String kategori = request.getParameter("kategori");
			String tingkat = request.getParameter("tingkat");
			int poinDiberikan = Integer.parseInt(request.getParameter("poinDiberikan"));	
			String pertanyaan = request.getParameter("pertanyaan");
			System.out.println(uname+" "+kategori+" "+tingkat+" "+poinDiberikan+" "+pertanyaan);
			boolean result = mongodbUtils.insertObjectQuestion(uname, kategori, tingkat, poinDiberikan, pertanyaan);
			if(result) {
				showQData(request, response, mongodbUtils);
				System.out.println("success boiii");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}else if("jawab".equals(action)) {
			 idPertanyaan = request.getParameter("kode");
			 status = request.getParameter("status");
			 pertanyaan = request.getParameter("pertanyaan");
			System.out.println("haaaaa "+ idPertanyaan+" "+status);
			if(status.equals("false")) {
				RequestDispatcher rd = request.getRequestDispatcher("/inputJawab.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/listJawab.jsp");
				rd.forward(request, response);
			}
		}else if("inputJawaban".equals(action)) {
			String uname = this.currUname;
			String jawaban = request.getParameter("answer");
		
			boolean resultUpdate = mongodbUtils.insertObjectAnswer(idPertanyaan, uname, jawaban);
			if(resultUpdate) {
				showAnswerData(request, response, mongodbUtils);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
						
		}
	}

	public void showAnswerData(HttpServletRequest request, HttpServletResponse response,
			MongoDBUtils mongodbUtils) {
		try {
			ArrayList<Pertanyaan> listPertanyaan = mongodbUtils.getPertanyaan();
			ArrayList<Jawaban> listJawaban = mongodbUtils.getJawaban();
			for(int i=0; i<listPertanyaan.size();i++) {
				List<String> id_Jawaban = listPertanyaan.get(i).getIdJawabanList();
				for(int j=0; j<listJawaban.size(); j++) {
					if(id_Jawaban.contains(listJawaban.get(j).getKodeJawaban())){
						listPertanyaan.get(i).addJawaban(listJawaban.get(j));
					}
				}
			}
			request.setAttribute("dataList", listJawaban);
			request.getRequestDispatcher("/listJawab.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showQData(HttpServletRequest request, HttpServletResponse response,
			MongoDBUtils mongodbUtils) {
		try {
			ArrayList<Person> listPerson = mongodbUtils.getPerson();
			ArrayList<Pertanyaan> listPertanyaan = mongodbUtils.getPertanyaan();
			for(int i=0; i<listPerson.size();i++) {
				System.out.println(i);
				List<String> id_pertanyaan = listPerson.get(i).getIdPertanyaan();
				for(int j=0; j<listPertanyaan.size(); j++) {
					System.out.println(j);
					if(id_pertanyaan.contains(listPertanyaan.get(j).getKode())){
						listPerson.get(i).addQuestions(listPertanyaan.get(j));
						
					}
				}
			}
			request.setAttribute("dataList", listPertanyaan);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}