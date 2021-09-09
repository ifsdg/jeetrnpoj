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

import com.situ.entry.Dept;
import com.situ.service.IdpetService;
import com.situ.service.impl.DeptserviceImpl;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/dept")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IdpetService service=new DeptserviceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action==null) {
			try {
				List<Dept> list=service.selectDepts();
				JSONArray jsonArray = new JSONArray(list);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				String aString = jsonArray.toString();
				response.getWriter().write(aString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
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

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String addr=request.getParameter("adderss");
		try {
			service.insertdept(name,tel,addr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list(request, response);
	}

	private void alt(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Dept dept=new Dept();
		dept.setName(request.getParameter("name"));
		dept.setTel(request.getParameter("tel"));
		dept.setAddress(request.getParameter("adderss"));
		dept.setId(Integer.parseInt(request.getParameter("id")));
		int i=0;
		try {
			i=service.updatedept(dept);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (i>0) {
			list(request, response);
		}
		
	}

	private void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String idString=request.getParameter("id");
		int i=service.deldept(idString);
		if (i>0) {
			list(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pnum=request.getParameter("page");
		String psize=request.getParameter("limit");
		try {
			List<Dept> list=service.selectDepts(pnum, psize);

			JSONArray jsonArray = new JSONArray(list);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String aString = "{\"code\":0,\"count\":" + service.countDepts() + ",\"data\":" + jsonArray.toString() + "}";
			response.getWriter().write(aString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
