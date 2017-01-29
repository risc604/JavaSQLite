import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParserData
{
	SQLiteJDBC		sqlObj;
	List<FieldObj>	tempList;
	List<FieldObj>	userList;
	
	public ParserData() throws SQLException
	{
		sqlObj = new SQLiteJDBC();
		tempList = new ArrayList<>();
		userList = sqlObj.selectAll(sqlObj.getConnection(), "users");
		tableInfo(userList);	//debug message
		
		tempList = sqlObj.getTempList(sqlObj.getConnection(), userList.get(1).others[3]);
		tableInfo(tempList);	//debug message
		
	}
	
	private void tableInfo(List<FieldObj> tabList)
	{
		System.out.printf("%ntableInfo(), tabList size: %02d %n", tabList.size());
		for (int i=0; i<tabList.size(); i++) 
		{
			System.out.printf("[%02d]:%02d, %s, %s, %s, %s !! %n", i, tabList.get(i)._id, tabList.get(i).others[0], 
					tabList.get(i).others[1], tabList.get(i).others[2], 
					tabList.get(i).others[3], tabList.get(i).others[4]);
		}
	}
	
	

}
