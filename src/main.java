

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import SQLite3Table.SQLite3Table;
import SQLite3Table.Temperature;



public class main 
{
	//public main() {}
	
	@SuppressWarnings("unused")
	public static void testJDBC()
	{
		try 
		{
			SQLiteJDBC test = new SQLiteJDBC();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("testJDBC(), in main.");
	}
	
	public static void testParser() 
	{
		try 
		{
			new ParserData();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testSQLite3() 
	{
		System.out.println("Test SQLite 3 ...");
		List<Temperature> tempList;
		
		try 
		{
			SQLite3Table sql3Table = new SQLite3Table();
			
			tempList = sql3Table.getAllTemprature(sql3Table.getConnection());
			sql3Table.tableInfo(tempList);
			
			//List<Integer> deleTempIdList = sql3Table.getTempIdList(tempList, "36caf2d1-c29c-4f41-88f1-2a6855974bf7");
			//for (int i=0; i <deleTempIdList.size(); i++) 
			//{
			//	sql3Table.delete(sql3Table.getConnection(), deleTempIdList.get(i), "temperature");
			//}
			
			//debug
			//tempList = sql3Table.getAllTemprature(sql3Table.getConnection());
			//sql3Table.tableInfo(tempList);
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	public static void testSQLite4() 
	{
		System.out.println("Test SQLite 3 ...");
		List<Temperature> tempList;
		LinkedHashMap<LocalDate, Double>	graphicsList;
		
		try 
		{
			SQLite3Table sql3Table = new SQLite3Table();
			tempList = sql3Table.getAllTemprature(sql3Table.getConnection());
			List<Double> dataList = null;
			//for (int i=0; i<tempList.size(); i++) {
			//for (int i=0; i<1; i++) {				
				dataList = Utils.StringDataToList(tempList.get(5).getData());
							
				//Double tempValue = 1.0;
				//LocalDate ldDT = Utils.getDataStartTime(tempList.get(i).getStartTime()); 				
			//}
			
			//debug
			System.out.println("size: " + dataList.size() + " "+ Arrays.toString(dataList.toArray()));
			
			//sql3Table.tableInfo(tempList);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(final String[] args)
	{
		//testJDBC();
		//testParser();
		
		//testSQLite3();
		testSQLite4();
		
		System.out.println("Hello Java for JDBC of SQLite.");
	}

}
