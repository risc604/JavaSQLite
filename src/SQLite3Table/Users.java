package SQLite3Table;

public class Users 
{
		int 	userID;
	 String		name;
	 String		birthday;
	 String		picture;
	 String		uuid;
	 String		create_date;
	 
	 public Users()
	 {
		userID = 0;
		name = "";
		birthday = "";
		picture = "";
		uuid = "";
		create_date = "";
	 }
	 
	 public Users(int id, String uName, String uBirthday, String uPicture, String uUuid, String uCreateDate)
	 {
		 userID = id;
		 name = uName;
		 birthday = uBirthday;
		 picture = uPicture;
		 uuid = uUuid;
		 create_date = uCreateDate;
	 }
	 
	 /*
	 public Users getUser()
	 {
		 return userID;
	 }
	 
	 public void setID(Users uUser)
	 {
		 this.userID = uUser.userID;
		 this.name = uUser.name;
		 this.birthday = uUser.birthday;
		 this.picture = uUser.picture;
		 this.uuid = uUser.uuid;
		 this.create_date = uUser.create_date;
	 }
	 */
	 
	 
	 public int getID()
	 {
		 return userID;
	 }
	 
	 public void setID(int id)
	 {
		 userID = id;
	 }
	 
	 public String getName() 
	 {
		 return name;
	 }
	 
	 public void setName(String uName) 
	 {
		 name = uName;
	 }
	 
	 public String getBirthday() 
	 {
		 return birthday;
	 }
	 
	 public void setBirthday(String uBirthday) 
	 {
		 birthday = uBirthday;
	 }
	 
	 public String getPicture() 
	 {
		 return picture;
	 }
	 
	 public void setPicture(String uPicture) 
	 {
		 picture = uPicture;
	 }
	 
	 public String getUuid() 
	 {
		 return uuid;
	 }
	 
	 public void setUuid(String uUuid) 
	 {
		 uuid = uUuid;
	 }
	 
	 public String getCreateDate() 
	 {
		 return create_date;
	 }
	 
	 public void setCreateDate(String uCreateDate) 
	 {
		 create_date = uCreateDate;
	 }
}
