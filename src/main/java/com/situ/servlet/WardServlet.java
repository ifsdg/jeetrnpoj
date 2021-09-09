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


import com.situ.entry.Ward;
import com.situ.service.IwardService;
import com.situ.service.impl.WardServiceImpl;
import com.situ.util.JsonUtil;

/**
 * Servlet implementation class WardServlet
 */
@WebServlet("/Ward")
public class WardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IwardService service=new WardServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WardServlet() {
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
			try {
				String aString=JsonUtil.parseJson(service.selectward(request.getParameter("id")));
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(aString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void serch(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String data=request.getParameter("data");
		List<Ward> list=null;
		if (data==null||data.equals("")) {
			list(request, response);
			return;
		}
		try {
			list= service.selectwards(data);
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

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pnum=request.getParameter("page");
		String psize=request.getParameter("limit");
		try {
			List<Ward> list=service.selectwards(pnum, psize);
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
		String waardno=request.getParameter("wardno");
		String addString=request.getParameter("address");
		String deptno=request.getParameter("deptno");
		try {
			service.insertward(waardno,addString,deptno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list(request, response);
	}

	private void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		service.delward(id);
		list(request, response);
	}

	private void alt(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String waardno=request.getParameter("wardno");
		String addString=request.getParameter("address");
		String deptno=request.getParameter("deptno");
		
		try {
			Ward ward=service.selectwards(id).get(0);
			ward.setAddress(addString);
			ward.setWardno(waardno);
			if (Integer.parseInt(deptno)!=0) {
				ward.setDept_id(Integer.parseInt(deptno));
			}
			service.updateward(ward);
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
