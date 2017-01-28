
public class fieldObj 
{
	private final int SIZE = 5;
	int		_id;
	String[] others;
	
	public fieldObj()
	{
		_id = 0;
		others = new String[SIZE];    
	}
	
	public int getId()
	{
		return(_id);
	}
	
	public void setId(int id)
	{
		_id = id;
	}
	
	public String[] getOthersField()
	{
		return(others);
	}
	
	public void setOthersFeild(String[] other)
	{
		others = other;
	}

}
