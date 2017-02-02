import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		
		//--- all temperature raw data
		tempList = sqlObj.getTempList(sqlObj.getConnection(), userList.get(2).others[3]);
		tableInfo(tempList);	//debug message
		List<byte[]> tempData = getDataList(tempList, tempList.size());
		
		//-- time in 3 hours.
		//int idx = compareDateTime(tempList);
		//List<byte[]> tempData = getDataList(tempList, idx);
	}
	
	public List<byte[]> getDataList(List<FieldObj> srcData, int indx)
	{
		List<byte[]> byteList = new ArrayList<>();
		
		for(int i=0; i<indx; i++)
		{
			//String tmpData = srcData.get(i).others[1];
			byte[] dataByte = Utils.hexStringToByteArray(srcData.get(i).others[1]);
			System.out.printf("%ndataByet length: %02d ", dataByte.length/3);
			
			for(int j=0; j<dataByte.length; j+=3)
			{
				byte[] rawData = new byte[3];
				rawData[0] = dataByte[j];
				rawData[1] = dataByte[j+1];
				rawData[2] = dataByte[j+2];
				byteList.add(rawData);
				System.out.printf(", [%02d]:%02d, [%02d]:%02d, [%02d]:%02d", 
						0, rawData[0], 1, rawData[1], 2, rawData[2] );
			}
		}
		
		//debug message.
		for(int i=0; i<byteList.size(); i++)
		{
			//for(int j=0; j<3; j++)
			{
				System.out.printf("%n[%02d]: %s ", i, Utils.getHexToString(byteList.get(i)) );
			}
		}
		
		
		System.out.printf("%n byteList size: %02d %n", byteList.size());
		return byteList;
	}
	
	public int compareDateTime(List<FieldObj> fieldObj)
	{
		int tempIdx = 0;
		Date firstDT = StringToDate(fieldObj.get(0).others[0]);
		System.out.println(", compareDateTime(), firstDT: " + firstDT );
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDT);
		cal.add(cal.MINUTE, 180);
		System.out.println("+180min: " + cal.getTime() );
		
		List<Date> endTimeList = new ArrayList<>();
		for (int i=0; i<fieldObj.size(); i++) 
		{
			Date tmpET = StringToDate(fieldObj.get(i).others[2]);
			System.out.printf(", [%02d]tmpET:%s %n", i, tmpET);
			
			if(tmpET.compareTo(cal.getTime()) <= 0)
			{	
				System.out.printf(", [%02d]tmpET:%s %n", i, tmpET);
				endTimeList.add(tmpET);
			}
			else
			{
				tempIdx = i;
				System.out.println("i = " + i);
				break;
			}
		}
		//System.out.printf(", args)
		return tempIdx;
	}
	
	public Date StringToDate(String srcTime)
	{
		//Date tmpDate = new Date();
		byte[]	startTime = Utils.hexStringToByteArray(srcTime);
		int[]	intTmp = new int[5]; 
				intTmp[0] = Utils.byteToUnsignedInt(startTime[0]) + 2000;
				intTmp[1] = Utils.byteToUnsignedInt(startTime[1]);
				intTmp[2] = Utils.byteToUnsignedInt(startTime[2]);
				intTmp[3] = Utils.byteToUnsignedInt(startTime[3]);
				intTmp[4] = Utils.byteToUnsignedInt(startTime[4]);
				//intTmp[5] = Utils.byteToUnsignedInt(startTime[5]);
				
		String tmpDT = String.format("%04d/%02d/%02d %02d:%02d:%02d", 
				intTmp[0], intTmp[1]+1, intTmp[2], intTmp[3], intTmp[4], 0);
		
		System.out.printf("StringToDate(), tmpDT: %s", tmpDT);
		return(new Date(tmpDT));
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
