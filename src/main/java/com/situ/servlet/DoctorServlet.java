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
import com.situ.service.IdoctorService;
import com.situ.service.impl.DoctorServiceImpl;

/**
 * Servlet implementation class DoctorServlet
 */
@WebServlet("/Doc")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IdoctorService service=new DoctorServiceImpl();
       String iString="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorServlet() {
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

	private void serch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String tar=request.getParameter("target");
		String data=request.getParameter("data");
		List<Doctor> list=null;
		if (data==null||data.equals("")) {
			list(request, response);
			return;
		}
		try {
			if (tar.equals("id")) {
				list=service.serchDocs("id", data);
			}else if(tar.equals("name")){
				list=service.serchDocs("name", data);
			}else {
				list=service.selectDocs("1","5");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray(list);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String aString = "{\"code\":0,\"count\":" + list.size() + ",\"data\":" + jsonArray.toString() + "}";
		response.getWriter().write(aString);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pnum=request.getParameter("page");
		String psize=request.getParameter("limit");
		try {
			List<Doctor> list=service.selectDocs(pnum, psize);
			JSONArray jsonArray = new JSONArray(list);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String aString = "{\"code\":0,\"count\":" + service.countDocs() + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(aString);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Doctor doctor=new Doctor();
		doctor.setName(request.getParameter("name"));
		doctor.setTitle(request.getParameter("title"));
		doctor.setDept_id(Integer.parseInt(request.getParameter("dept")));
		doctor.setCardno(request.getParameter("cardno"));
		try {
			service.insertdoc(doctor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list(request, response);
	}

	private void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		service.deldoc(id);
		list(request, response);
	}

	private void alt(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		try {
			Doctor doctor=service.serchDocs("id",request.getParameter("id")).get(0);
			doctor.setName(request.getParameter("name"));
			doctor.setTitle(request.getParameter("title"));
			doctor.setDept_id(Integer.parseInt(request.getParameter("dept")));
			service.updatedoc(doctor);
			list(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
