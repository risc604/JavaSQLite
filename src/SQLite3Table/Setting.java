package SQLite3Table;

public class Setting 
{
	int		set_id;
	int 	connectNotification;
	int 	overTempNotification;
	int 	tempUnit;
	int 	userSetHigh;
	int 	userSetLow;
	int 	sysHigh;
	int 	sysLow;
	String	create_date;
	
	public Setting()
	{
		set_id = 0;
		connectNotification = 0;
		overTempNotification = 0;
		tempUnit = 0;
		userSetHigh = 0;
		userSetLow = 0; 
		sysHigh = 0;    
		sysLow = 0;     
		create_date = "";
	}
	
	public Setting(	int id, int btState, int tempState, int unit, int uHigh, int uLow, 
					int sHigh, int sLow, String createDate)
	{
		set_id = id;
		connectNotification = btState;
		overTempNotification = tempState;
		tempUnit = unit;
		userSetHigh = uHigh;
		userSetLow = uLow; 
		sysHigh = sHigh;    
		sysLow = sLow; 
		create_date = createDate;
	}
	
	public int getId()
	{
		return set_id;
	}
		
	public void setId(int id)
	{
		set_id = id;
	}
		
	public int getConnectNotification()
	{
		return connectNotification;
	}
		
	public void setConnectNotification(int state)
	{
		connectNotification = state;
	}
	
	public int getTempNotification()
	{
		return overTempNotification;
	}
		
	public void setTempNotification(int state)
	{
		overTempNotification = state;
	}
	
	public int getTempUnit()
	{
		return tempUnit;
	}
		
	public void setTempUnit(int state)
	{
		tempUnit = state;
	}
	
	public int getUserSetHigh()
	{
		return userSetHigh;
	}
		
	public void setUserSetHigh(int level)
	{
		userSetHigh = level;
	}
	
	public int getUserSetLow()
	{
		return userSetLow;
	}
		
	public void setUserSetLow(int level)
	{
		userSetLow = level;
	}
	
	public int getSysHigh()
	{
		return sysHigh;
	}
		
	public void setSysHigh(int level)
	{
		sysHigh = level;
	}
	
	public int getSysLow()
	{
		return sysLow;
	}
		
	public void setSysLow(int level)
	{
		sysLow = level;
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



