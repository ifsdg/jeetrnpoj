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

import com.situ.entry.Patient;
import com.situ.service.IpatientService;
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
		default:
			break;
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
			String aString = "{\"code\":0,\"count\":" + list.size() + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(aString);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void alt(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
