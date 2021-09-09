package com.situ.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 
 * @author Mrkua
 *
 */
public class DataSouseutil {
	private static DataSource dt = null;
	static DBUtil util = new DBUtil();

	static {
		try {
			Properties prop = new Properties();

			prop.load(DataSouseutil.class.getClassLoader().getResourceAsStream("db.properties"));
			//DataSouseutil.class.getClassLoader().getResourceAsStream("db.properties")
			dt = DruidDataSourceFactory.createDataSource(prop);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接池连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dt.getConnection();

	}

	/**
	 * 执行语句
	 * 语句必须是UPDATE / DELTE / INSERT之一
	 * @param sql
	 * @param parmlist
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql, Object... parmlist) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			for (int i = 1; i <= parmlist.length; i++) {
				ps.setObject(i, parmlist[i-1]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(con, ps);
		}
		return -1;

	}

	/**
	 * 查询，返回结果集 count()only
	 * 
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static Integer executeQuery(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ResultSet res=ps.executeQuery();
			res.next();
			
			return res.getInt(1);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(con);
		}
		return null;

	}

	/**
	 * 查询，返回以list保存的结果集
	 * 
	 * @param <T>
	 * 
	 * @param sql
	 * @param args
	 * @return List
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static <T> List<T> executeQuery(String sql, Class<T> cla, Object... args) throws SQLException {
		List<T> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			 con = getConnection();
			ps = con.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				ps.setObject(i, args[i-1]);
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsnd = rs.getMetaData();
			while (rs.next()) {
				T o = cla.newInstance();
				for (int i = 1; i <= rsnd.getColumnCount(); i++) {
					String columName = rsnd.getColumnLabel(i);// 获取第i列名
					Object value = rs.getObject(i);// 获取第i列值
					Field field = cla.getDeclaredField(columName);
					field.setAccessible(true);// 设置可以访问私有
					field.set(o, value);
				}
				list.add(o);
			}
			rs.close();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
			
		}
		return list;

	}

//		if (param instanceof Integer) {
//			int value = ((Integer) param).intValue();
//		} else if (param instanceof String) {
//			String s = (String) param;
//		} else if (param instanceof Double) {
//			double d = ((Double) param).doubleValue();
//		} else if (param instanceof Float) {
//			float f = ((Float) param).floatValue();
//		} else if (param instanceof Long) {
//			long l = ((Long) param).longValue();
//		} else if (param instanceof Boolean) {
//			boolean b = ((Boolean) param).booleanValue();
//		} else if (param instanceof Date) {
//			Date d = (Date) param;
//		}

}
