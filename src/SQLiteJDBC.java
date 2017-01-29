import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteException;

public class SQLiteJDBC 
{
	private final String DB_NAME = "data/mt24hr.db";
	//private final String DBFILE = "jdbc:sqlite:data/mt24hr.db";
	//private final String DBFILE = "jdbc:sqlite:data/test.db";
	private final String DBFILE = "jdbc:sqlite:" + DB_NAME;
	
	public SQLiteJDBC() throws SQLException
	{
		Connection con = getConnection();
		
		//System.out.println("users table data :");
		//selectAll(con, "users");
		
		//System.out.println("temperature table data :");
		//selectAll(con, "temperature");
		
		/*
		//建立 Table
		createTable(con);
		
		//新增資料
		insert(con, 1, "第一個");
		insert(con, 2, "第二個");
		//查詢顯示資料
		System.out.println("新增第二筆資料後狀況:");	
		selectAll(con);
		
		//修改資料
		System.out.println("修改第一筆資料後狀況:");
		update(con, 1, "這個植被給變了!");
		//查詢顯示資料
		selectAll(con);
		
		//刪除資料
		System.out.println("刪除第一筆資料後狀況:");
		delete(con, 1);
		//查詢顯示資料
		selectAll(con);
		
		//刪除 Table
		dropTable(con);
		*/
		
		
		
		//關閉資料庫, 斷線
		//con.close();
	}
	
	public Connection getConnection() throws SQLException
	{
		SQLiteConfig config = new SQLiteConfig();
		
		//config.setReadOnly(true);
		config.setSharedCache(true);
		config.enableRecursiveTriggers(true);
		
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl(DBFILE);
		return ds.getConnection();
		//ds.setServerNAme("");
	}
	
	//Create Table
	public void createTable(Connection con) throws SQLException
	{
		String sql = "DROP TABLE IF EXISTS test ; create table test (id integer, name string); ";
		Statement stat = null;
		stat = con.createStatement();
		stat.executeUpdate(sql);
	}
	
	public void createTable(Connection con, String table) throws SQLException
	{
		String sql = "DROP TABLE IF EXISTS " + table + " ; create table " + table + " (id integer, name string); ";
		Statement stat = null;
		stat = con.createStatement();
		stat.executeUpdate(sql);
	}
	
	//drop table
	public void dropTable(Connection con) throws SQLException
	{
		String sql = "drop table test";
		Statement stat = null;
		stat = con.createStatement();
		stat.executeUpdate(sql);
	}
	
	public void dropTable(Connection con, String table) throws SQLException
	{
		String sql = "drop table " + table;
		Statement stat = null;
		stat = con.createStatement();
		stat.executeUpdate(sql);
	}
	
	//insert
	public void insert(Connection con, int id, String name) throws SQLException
	{
		String sql = "insert into test (id,name) values(?,?)";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setInt(idx++, id);
		pst.setString(idx++, name);
		pst.executeUpdate();
	}
	
	public void insert(Connection con, int id, String name, String table) throws SQLException
	{
		String sql = "insert into" + table + " (id,name) values(?,?)";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setInt(idx++, id);
		pst.setString(idx++, name);
		pst.executeUpdate();
	}
	
	//update
	public void update(Connection con, int id, String name) throws SQLException
	{
		String sql = "update test set name = ? where id = ?";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setString(idx++, name);
		pst.setInt(idx++, id);
		pst.executeUpdate();
	}
	
	public void update(Connection con, int id, String name, String table) throws SQLException
	{
		String sql = "update " + table + " set name = ? where id = ?";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setString(idx++, name);
		pst.setInt(idx++, id);
		pst.executeUpdate();
	}
	
	//delete
	public void delete(Connection con, int id) throws SQLException
	{
		String sql = "delete from test where id = ?";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setInt(idx++, id);
		pst.executeUpdate();
	}
	
	public void delete(Connection con, int id, String table) throws SQLException
	{
		String sql = "delete from " + table + " where id = ?";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setInt(idx++, id);
		pst.executeUpdate();
	}
	
	
	//select All
	public void selectAll(Connection con) throws SQLException
	{
		String sql = "select * from test";
		Statement stat = null;
		ResultSet rs = null;
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next())
		{
			System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
		}
	}
	
	public List<FieldObj> selectAll(Connection con, String table) throws SQLException
	{
		String sql = "select * from " + table;
		Statement stat = null;
		ResultSet rs = null; 
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		//FieldObj	fieldObj = new FieldObj();
		List<FieldObj>	objList = new ArrayList<>();
		
		while(rs.next())
		{
			FieldObj	fieldObj = new FieldObj();
			if (table.equalsIgnoreCase("users"))
			{
				fieldObj._id = rs.getInt("_id");
				fieldObj.others[0] = rs.getString("name");
				fieldObj.others[1] = rs.getString("birthday");
				fieldObj.others[2] = rs.getString("picture");
				fieldObj.others[3] = rs.getString("uuid");
				fieldObj.others[4] = rs.getString("create_date");
				
				//fieldObj.others[5] = rs.getString("picture");
			}
			else if (table.equalsIgnoreCase("temperature"))
			{
				fieldObj._id = rs.getInt("temp_id");
				fieldObj.others[0] = rs.getString("temp_start_time");
				fieldObj.others[1] = rs.getString("temp_data");
				fieldObj.others[2] = rs.getString("temp_end_time");
				fieldObj.others[3] = rs.getString("temp_uuid");
				fieldObj.others[4] = rs.getString("temp_create_date");
			}
			
			objList.add(fieldObj);
			System.out.printf("%02d, %s, %s, %s, %s !! %n", fieldObj._id, fieldObj.others[0], 
					fieldObj.others[1], fieldObj.others[2], fieldObj.others[3], fieldObj.others[4]);
			
			//fieldObj = null;			
		}
		
		return objList;
	}
	
	public List<FieldObj> getTempList(Connection con, String userUuid) throws SQLException
	{
		System.out.println("userUuid: " + userUuid);
		
		String	sql = "select * from temperature where temp_uuid = \"" + userUuid + "\"";
		Statement stat = null;
		ResultSet rs = null; 
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		List<FieldObj>	objList = new ArrayList<>();
		
		if(!rs.next())
		{
			System.out.println("userUuid: " + userUuid + ", NO Data !!");
			return null;
		}
		
		while(rs.next())
		{
			FieldObj	fieldObj = new FieldObj();
			
			fieldObj._id = rs.getInt("temp_id");
			fieldObj.others[0] = rs.getString("temp_start_time");
			fieldObj.others[1] = rs.getString("temp_data");
			fieldObj.others[2] = rs.getString("temp_end_time");
			fieldObj.others[3] = rs.getString("temp_uuid");
			fieldObj.others[4] = rs.getString("temp_create_date");
			
			objList.add(fieldObj);
			//System.out.printf("%02d, %s, %s, %s, %s !! %n", fieldObj._id, fieldObj.others[0], 
			//		fieldObj.others[1], fieldObj.others[2], fieldObj.others[3], fieldObj.others[4]);
		}
				
		return objList;
	}
	
	
	
}