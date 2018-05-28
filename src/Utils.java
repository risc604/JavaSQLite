//package ;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.DateFormatter;

public class Utils 
{
	static final String HEXES = "0123456789ABCDEF";
	
	public Utils()
	{}
	
	public static int byteToUnsignedInt(byte b)
    {
        return 0x00 << 24 | b & 0xff;
    }
	
	public static String convertArrayToString(byte[] data, int start, int length)
    {
        byte[] tmpbyte = new byte[length];

        for (int i=0; i<length; i++)
            tmpbyte[i] = data[start+i];
        String tmpStr = getHexToString(tmpbyte);

        return(tmpStr);
    }
	
	public static String getHexToString(byte[] raw)
    {
        //StringBuilder sb= new StringBuilder(responInfo.length);
        //for (byte indx: responInfo)
        //{
        //    sb.append(format("%02X", indx));
        //}

        if (raw == null)
        {
            return null;
        }

        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw)
        {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static byte[] hexStringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len/2];

        for(int i = 0; i < len; i+=2)
        {
            data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) +
                                 Character.digit(s.charAt(i+1), 16));
        }

        return data;
    }

    public static byte[] StringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len/2];

        for(int i = 0; i < len; i+=2)
        {
            data[i/2] = (byte) ((Character.digit(s.charAt(i), 10) << 4) +
                    Character.digit(s.charAt(i+1), 10));
        }

        return data;
    }
    
    public static List<Double> StringDataToList(String srcString)
    {
    	if((srcString == null) || (srcString.equalsIgnoreCase("")))
    		return null;
    	
    	System.out.println("srcString: " + srcString);
 
    	int counts = srcString.length() / 6;
    	
    	System.out.println("counts: " + counts);
    	List<Double> dataList = new ArrayList<Double>();
    	for (int i=0; i<counts; i++) 
    	{
    		String s1 = String.valueOf(srcString.toCharArray(), i*6, 4);
    		byte[] byteTmp = hexStringToByteArray(s1);
    		Double tmpData = (byteToUnsignedInt(byteTmp[0]) * 100 + byteToUnsignedInt(byteTmp[1])) / 100.0;
    		//System.out.printf("data1: %02d, data2: %02d: %4.2f %n", byteTmp[0], byteTmp[1], tmpData);
    		
    		dataList.add(tmpData);	
		}
    	
    	//debug
    	
    	
    	return dataList;
    }
    
    
    public static LocalDateTime getDataStartTime(String hexDT) 
    {
    	if ((hexDT == null) || (hexDT.equalsIgnoreCase(""))) 
    	{
			return null;
		}
    	
    	byte[] byteDT = hexStringToByteArray(hexDT);
    	int[] intDT = new int[byteDT.length];
    	for (int i=0; i<intDT.length; i++) 
    	{
    		intDT[i] = byteToUnsignedInt(byteDT[i]);
		}    	
    	System.out.println("intDT: " + Arrays.toString(intDT));
    	
    	//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[yy, MM, dd, HH, mm]");
    	//LocalDateTime ldStartTime = LocalDateTime.parse( Arrays.toString(intDT), dtf);
    	LocalDateTime ldStartTime = LocalDateTime.of(intDT[0]+2000, intDT[1], intDT[2], intDT[3], intDT[4]);
    	System.out.println("ldStartTime: " + ldStartTime);
    	
    	
		return ldStartTime;
	}
    
    
	public static List <LocalDateTime> getLocalDateTimeList(LocalDateTime ldtSrc, int records) 
    {
    	if ((ldtSrc == null) || (records < 1)) 
    	{
			return null;
		}
    	LocalDateTime ldtTmp = ldtSrc;
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    	List<LocalDateTime> tmpLDTList = new ArrayList<LocalDateTime>();
    	for (int i=0; i<records; i++) {
			tmpLDTList.add(ldtTmp);
			ldtTmp = ldtTmp.plusMinutes(1);
		}
    	
    	
    	
    	System.out.println("tmpLDTList: " + Arrays.toString(tmpLDTList.toArray()));
	
    	return tmpLDTList;
	}
    
     
    @SuppressWarnings("deprecation")
	public static String calculateEndTime(int records, String startTime)
	{
		String lastTime = new String();
		byte[] dateTime = new byte[5];
		
		dateTime = hexStringToByteArray(startTime);
		String tmpString = String.format("%04d%02d%02d%02d%02d", 
				(dateTime[0]+2000), dateTime[1], dateTime[2], dateTime[3], dateTime[4]);
		System.out.println("tmpString: " + tmpString);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmm"); 
		Date startDateTime = null;
		
		try 
		{
			startDateTime = sdf.parse(tmpString);
			//startDateTime = sdf.parse(startTime);
			System.out.println("startDateTime: " + startDateTime.toString() + ", records: " + records);
			
			int tmpLong = startDateTime.getMinutes() + records;
			System.out.printf("tmpLong: %d %n", tmpLong);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDateTime);
			calendar.add(Calendar.MINUTE, records);//minute + 156
			int[] intDTime = new int[5];
			
			intDTime[0] = calendar.getTime().getYear() + 1900 - 2000;
			intDTime[1] = calendar.getTime().getMonth()+1;
			intDTime[2] = calendar.getTime().getDate();
			intDTime[3] = calendar.getTime().getHours();
			intDTime[4] = calendar.getTime().getMinutes();
			System.out.printf("Year:%02d %nMonth: %02d %nDate: %02d %nHour:%02d %nMinute: %02d %n", 
					intDTime[0], intDTime[1], intDTime[2], intDTime[3], intDTime[4]);
			
			//lastTime = String.format("%02X%02X%02X%02X%02X. %n", 
			//		intDTime[0], intDTime[1], intDTime[2],
			//		intDTime[3], intDTime[4]);	
			//System.out.printf("intDTime year: %4d %n", intDTime[0]);
			
			byte[] byteDTime = new byte[5];
			for(int i=0; i<byteDTime.length; i++)
			{
				byteDTime[i] = (byte) (intDTime[i] & 0xFF);
			}
			
			//for(int i=0; i<byteDTime.length; i++)
			//{
			//	System.out.printf("%02X", byteDTime[i]);
			//}
			//System.out.printf("%n");
			
			lastTime = getHexToString(byteDTime);
			
			//startDateTime = Timestamp.Timestamp(tmpLong);
			//System.out.printf("int year: %4d %n", 
			//		calendar.getTime().getYear()+1900);
			//System.out.printf("int date: %02d%02d%02d%02d%02d %n", 
			//		calendar.getTime().getYear()+1900, calendar.getTime().getMonth(), calendar.getTime().getDate(), 
			//		calendar.getTime().getHours(), calendar.getTime().getMinutes());
			System.out.println("calendar: " + calendar.getTime().toString());
			System.out.println("lastTime : " + lastTime);
			
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lastTime;
	}


}
