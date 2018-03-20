package model;

public class UserBO 
{
	private int id;
	private String usuarioJava;
	private String contrasenaJava;
	
	public UserBO(){}
	public UserBO(String pUsername, String pPassword)
	{
		this.usuarioJava = pUsername;
		this.contrasenaJava = pPassword;
	}
	
	public int getId() 
	{
		return id;
	}
	   
	public void setId( int id ) 
	{
		this.id = id;
	}
	
	public String getUsername() 
	{
		return usuarioJava;
	}
	
	public void setUsername(String usuario) 
	{
		this.usuarioJava = usuario;
	}
	
	public String getPassword()
	{
		return contrasenaJava;
	}
	
	public void setPassword(String contrasena) 
	{
		this.contrasenaJava = contrasena;
	}
}
