import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		selectAllTemperature(con);
		/*
		//�إ� Table
		createTable(con);
		
		//�s�W���
		insert(con, 1, "�Ĥ@��");
		insert(con, 2, "�ĤG��");
		//�d����ܸ��
		System.out.println("�s�W�ĤG����ƫ᪬�p:");	
		selectAll(con);
		
		//�ק���
		System.out.println("�ק�Ĥ@����ƫ᪬�p:");
		update(con, 1, "�o�ӴӳQ���ܤF!");
		//�d����ܸ��
		selectAll(con);
		
		//�R�����
		System.out.println("�R���Ĥ@����ƫ᪬�p:");
		delete(con, 1);
		//�d����ܸ��
		selectAll(con);
		
		//�R�� Table
		dropTable(con);
		*/
		
		
		
		//������Ʈw, �_�u
		con.close();
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
	
	//drop table
	public void dropTable(Connection con) throws SQLException
	{
		String sql = "drop table test";
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
	
	public void selectAll(Connection con, String table) throws SQLException
	{
		String sql = "select * from " + table;
		Statement stat = null;
		ResultSet rs = null;
		
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		
		
		if(table.equalsIgnoreCase("temperature"))
		{
			field[0] = 
		}
			while(rs.next())
			{
				System.out.printf("%02d, %s, %s, %s, %s !! %n", rs.getInt("temp_id"), 
						rs.getString("temp_start_time"), rs.getString("temp_data"), 
						rs.getString("temp_end_time"), rs.getString("temp_uuid"));
			}
		}
		else if(table.equalsIgnoreCase(users))
		{
			while(rs.next())
			{
				System.out.printf("%02d, %s, %s, %s, %s !! %n", rs.getInt("_id"), 
						rs.getString("name"), rs.getString(""), 
						rs.getString("temp_end_time"), rs.getString("temp_uuid"));
			}
		}
	}
	
	public 
	
}