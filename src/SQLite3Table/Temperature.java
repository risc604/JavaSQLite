package SQLite3Table;

public class Temperature 
{
		int 	tempID;
	 String		start_time;
	 String		data;
	 String		end_time;
	 String		uuid;
	 String		create_date;
	 
	 // TODO Auto-generated constructor stub
	 public Temperature()
	 {
		tempID = 0;      
		start_time = "";   
		data = "";
		end_time = "";    
		uuid = "";        
		create_date = ""; 
	 }
		
	 public Temperature(int id, String startTime, String data, String endTime, String uuid, String createDate)
	 {
		tempID = id;      
		start_time = startTime;   
		this.data = data;        
		end_time = endTime;    
		this.uuid = uuid;        
		create_date = createDate; 
	 }
	 
	 public int getId()
	 {
		 return tempID;
	 }
		
	 public void setId(int id)
	 {
		 tempID = id;
	 }
	
	 public String getStartTime()
	 {
		 return start_time;
	 }
		
	 public void setStartTime(String startTime)
	 {
		 start_time = startTime;
	 }
	 
	 public String getData()
	 {
		 return data;
	 }
		
	 public void setData(String data)
	 {
		 this.data = data;
	 }
	 
	 public String getEndTime()
	 {
		 return end_time;
	 }
		
	 public void setEndTime(String endTime)
	 {
		 end_time = endTime;
	 }
	 
	 public String getUuid()
	 {
		 return uuid;
	 }
		
	 public void setUuid(String uuid)
	 {
		 this.uuid = uuid;
	 }
	 
	 public String getCreateDate() 
	 {
		 return create_date;
	 }
	 
	 public void setCreateDate(String createDate) 
	 {
		 create_date = createDate;
	 }
	
	 
}
