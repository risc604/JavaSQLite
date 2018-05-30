

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;


import SQLite3Table.SQLite3Table;
import SQLite3Table.Temperature;



@SuppressWarnings("unused")
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
	
	//@SuppressWarnings({ "unused", "null" })
	public static void testSQLite4() 
	{
		System.out.println("Test SQLite 3 ...");
		List<Temperature> tempList;
		LinkedHashMap<LocalDateTime, Double>	graphicsList = new LinkedHashMap<LocalDateTime, Double>();
		
		try 
		{
			SQLite3Table sql3Table = new SQLite3Table();
			tempList = sql3Table.getAllTemprature(sql3Table.getConnection());
			List<Double> dataList = null;
			List<LocalDateTime> ldtList = null;
			//for (int i=0; i<tempList.size(); i++) {
			//for (int i=0; i<1; i++) {				
			String startTime = tempList.get(0).getStartTime();
			LocalDateTime ldTime = Utils.getStringToLocalTime(startTime);
			
			System.out.println("StartTime: " + startTime + ", ldTime: " + ldTime);
			dataList = Utils.StringDataToList(tempList.get(0).getData());
			ldtList = Utils.getLocalDateTimeList(ldTime, dataList.size());
				
			for (int i=0; i<dataList.size(); i++) {
				
				try {
					if ((dataList.get(i).compareTo(37.9) <= 0)) {
						graphicsList.put(ldtList.get(i), dataList.get(i));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
				//Double tempValue = 1.0;
				//LocalDate ldDT = Utils.getDataStartTime(tempList.get(i).getStartTime()); 				
			//}
			
			//debug
			System.out.println("size: " + dataList.size() + " "+ Arrays.toString(dataList.toArray()));
			System.out.println("size: " + graphicsList.size() + ", " + graphicsList.toString());
			//sql3Table.tableInfo(tempList);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@SuppressWarnings("unused")
	public static void testLocalDateArithmeticOperators() 
	{		
		List<Temperature> tempList = null;
	
		try 
		{
			SQLite3Table sql3Table = new SQLite3Table();
			tempList = sql3Table.getAllTemprature(sql3Table.getConnection());
			
			LocalDateTime[] ldtArray = new LocalDateTime[3];
			ldtArray[0] = Utils.getStringToLocalTime(tempList.get(0).getStartTime());
			ldtArray[1] = Utils.getStringToLocalTime(tempList.get(0).getEndTime());
			ldtArray[2] = ldtArray[1].minusMinutes(ldtArray[0].getMinute()-1);
			//ldtArray[2] = Utils.getDiffrentDT(ldtArray);
			int minus = ldtArray[2].getMinute();
			
			List<Double> dataList = Utils.StringDataToList(tempList.get(0).getData());
			
			System.out.println("ldtArray: " + Arrays.toString(ldtArray)); 
			System.out.println("different minuse: " + minus);
			System.out.println("data records: " + dataList.size());
			
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
		//testSQLite4();

		testLocalDateArithmeticOperators();
		
		System.out.println("Hello Java for JDBC of SQLite.");
	}

}

