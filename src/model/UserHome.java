package model;

public interface UserHome 
{
	public UserBO validate(String pUsername, String pPassword);
        public UserBO changePassword(int userID, String pNewPassword);
}
