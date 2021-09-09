package com.situ.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.situ.entry.Doctor;
import com.situ.entry.Patient;
import com.situ.service.IdoctorService;
import com.situ.service.IpatientService;
import com.situ.service.impl.DoctorServiceImpl;
import com.situ.service.impl.PatientServiceImpl;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/Pati")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IpatientService service=new PatientServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch (action) {
		case "add":
			add(request,response);
			break;
		case "del":
			del(request,response);
			break;
		case "list":
			list(request,response);
			break;
		case "alt":
			alt(request,response);
			break;
		case "serch":
			serch(request,response);
			break;
		default:
			break;
		}
	}

	private void serch(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String target=request.getParameter("target");
		String data=request.getParameter("data");
		if (data==""||data==null) {
			list(request, response);
			return;
		}
		try {
			List<Patient> list=service.findpatients(target, data);
			JSONArray jsonArray = new JSONArray(list);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String aString = "{\"code\":0,\"count\":" + service.countpatients() + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(aString);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pnum=request.getParameter("page");
		String psize=request.getParameter("limit");
		try {
			List<Patient> list=service.selectpatients(pnum, psize);
			JSONArray jsonArray = new JSONArray(list);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String aString = "{\"code\":0,\"count\":" + service.countpatients()  + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(aString);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name=request.getParameter("username");
		String gender=request.getParameter("gender");
		String ageString=request.getParameter("age");
		String doc=request.getParameter("doc");
		String  paitno=request.getParameter("patino");
		String  roomno=request.getParameter("room");
		try {
			int i= service.insertpatient(name ,gender ,Integer.parseInt(ageString) ,paitno,Integer.parseInt(doc) ,Integer.parseInt(roomno));
			if (i>0) {
				request.setAttribute("flag", "true");
				request.getRequestDispatcher("./patient/addpatient.jsp").forward(request, response);
				response.getWriter().write("<script> alert('tjcg');</script>");
			}
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		service.delpatient(id);
		list(request, response);
	}

	private void alt(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String patino=request.getParameter("patino");
		Integer age=Integer.parseInt(request.getParameter("age"));
		Integer wardin=Integer.parseInt(request.getParameter("wardno"));
		IdoctorService docserService=new DoctorServiceImpl();
		String docname=request.getParameter("docname");
		try {
			Doctor doc= docserService.serchDocs("name", docname).get(0);
			Patient patient=new Patient();
			patient.setId(Integer.parseInt(id));
			patient.setName(name);
			patient.setInpatientno(patino);
			patient.setDoctor_id(doc.getId());
			patient.setAge(age);
			patient.setWard_id(wardin);
			service.updatepatient(patient);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
