package SQLite3Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

public class SQLite3Table 
{
	private final String DB_NAME = "data/mt24hr.db";
	private final String DBFILE = "jdbc:sqlite:" + DB_NAME;
	
	public SQLite3Table() throws SQLException
	{
		Connection con = getConnection();
	}
	
	public Connection getConnection() throws SQLException
	{
		SQLiteConfig config = new SQLiteConfig();
		
		config.setSharedCache(true);
		config.enableRecursiveTriggers(true);
		
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl(DBFILE);
		return ds.getConnection();
	}
	
	//Create Table
	public void createTable(Connection con, String table) throws SQLException
	{
		String sql = "DROP TABLE IF EXISTS " + table + " ; create table " + table + " (id integer, name string); ";
		Statement stat = null;
		stat = con.createStatement();
		stat.executeUpdate(sql);
	}
	
	//drop table
	public void dropTable(Connection con, String table) throws SQLException
	{
		String sql = "drop table " + table;
		Statement stat = null;
		stat = con.createStatement();
		stat.executeUpdate(sql);
	}
	
	//insert
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
	public void delete(Connection con, int id, String table) throws SQLException
	{
		String sql = "delete from " + table + " where temp_id = " + id;
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		//int idx = 1;
		//pst.setInt(idx++, id);
		pst.executeUpdate();
	}
	
	//delete
	public void delete(Connection con, String uuid, String table) throws SQLException
	{
		String sql = "delete from " + table + " where " + table + "_uuid " + " = " + "\"" + uuid + "\"";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setNString(idx, uuid);
		pst.executeUpdate();
	}
		
	//select all user 
	public List<Users> getAllUser(Connection con) throws SQLException
	{
		String	sql = "select * from users";
		Statement stat = null;
		ResultSet rs = null; 
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		List<Users>	userList = new ArrayList<>();
		
		if(!rs.next())
		{
			System.out.println("user table is NO Data !!");
			return null;
		}
		
		while(rs.next())
		{
			Users	userObj = new Users();
			
			userObj.userID 		= rs.getInt("_id");
			userObj.name		= rs.getString("name");
			userObj.birthday	= rs.getString("birthday");
			userObj.picture 	= rs.getString("picture");
			userObj.uuid		= rs.getString("uuid");
			userObj.create_date = rs.getString("create_date");
			
			userList.add(userObj);
			//System.out.printf("%02d, %s, %s, %s, %s !! %n", fieldObj._id, fieldObj.others[0], 
			//		fieldObj.others[1], fieldObj.others[2], fieldObj.others[3], fieldObj.others[4]);
		}
				
		return userList;
	}
	
	// select all Temperature
	public List<Temperature> getAllTemprature(Connection con) throws SQLException
	{
		String	sql = "select * from temperature";
		Statement stat = null;
		ResultSet rs = null; 
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		List<Temperature>	tempList = new ArrayList<>();
		
		if(!rs.next())
		{
			System.out.println("Temperature table is NO Data !!");
			return null;
		}
		
		while(rs.next())
		{
			Temperature	tempObj = new Temperature();
			
			tempObj.tempID 		= rs.getInt("temp_id");
			tempObj.start_time	= rs.getString("temp_start_time");
			tempObj.data		= rs.getString("temp_data");
			tempObj.end_time 	= rs.getString("temp_end_time");
			tempObj.uuid		= rs.getString("temp_uuid");
			tempObj.create_date = rs.getString("temp_create_date");
			
			tempList.add(tempObj);
			//System.out.printf("%02d, %s, %s, %s, %s !! %n", fieldObj._id, fieldObj.others[0], 
			//		fieldObj.others[1], fieldObj.others[2], fieldObj.others[3], fieldObj.others[4]);
		}
				
		return tempList;
	}
		
	// select all Setting
	public List<Setting> getAllSetting(Connection con) throws SQLException
	{
		String	sql = "select * from Setting";
		Statement stat = null;
		ResultSet rs = null; 
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		List<Setting>	settingList = new ArrayList<>();
		
		if(!rs.next())
		{
			System.out.println("Setting table is NO Data !!");
			return null;
		}
		
		while(rs.next())
		{
			Setting	settingObj = new Setting();
			
			settingObj.set_id				= rs.getInt("set_id");
			settingObj.connectNotification 	= rs.getInt("bt_notification");
			settingObj.overTempNotification	= rs.getInt("temp_notification");
			settingObj.tempUnit				= rs.getInt("temp_unit");
			settingObj.userSetHigh			= rs.getInt("user_high");
			settingObj.userSetLow			= rs.getInt("user_low");
			settingObj.sysHigh				= rs.getInt("sys_high");
			settingObj.sysLow				= rs.getInt("sys_low");
			settingObj.create_date 			= rs.getString("set_create_date");
			
			settingList.add(settingObj);
			System.out.printf("setting table, id:%02d, connect:%02d, overTemp:%02d, tempUnit:%02d," +
					"userSetHigh:%02d, userSetLow:%02d, sysHigh:%02d, sysLow:%02d, %s !! %n", 
					settingObj.set_id, settingObj.connectNotification, settingObj.overTempNotification, 
					settingObj.tempUnit, settingObj.userSetHigh, settingObj.userSetLow, 
					settingObj.sysHigh, settingObj.userSetLow, settingObj.create_date);
		}
				
		return settingList;
	}
	
	public void tableInfo(List<Temperature> tabList)
	{
		System.out.printf("%ntableInfo(), tabList size: %02d %n", tabList.size());
		for (int i=0; i<tabList.size(); i++) 
		{
			System.out.printf("[%02d]:%02d, %s, %s, %s, %s, %s !! %n", i, 
					tabList.get(i).tempID, tabList.get(i).start_time, 
					tabList.get(i).data, tabList.get(i).end_time , tabList.get(i).uuid, 
					tabList.get(i).create_date);
		}
	}
	
	public List<Integer> getTempIdList(List<Temperature> tabList, String userUuid)
	{
		List<Integer> tmpIdList = new ArrayList<>();
		
		for (int i=0; i<tabList.size(); i++) 
		{
			if (tabList.get(i).uuid.equalsIgnoreCase(userUuid)) 
			{
				tmpIdList.add(tabList.get(i).tempID);
			}
		}
		
		//debug
		for (int i=0; i<tmpIdList.size(); i++) 
		{
			System.out.println("[" + i + "]:" + tmpIdList.get(i));
		}
		return tmpIdList;
		
	}
	
		

}
