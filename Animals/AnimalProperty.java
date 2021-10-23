import java.io.*;

public class AnimalProperty implements Serializable 
{
	public String key;
	public String question;

	public AnimalProperty(String key, String question) 
	{
		this.key = key;
		this.question = question;
	}

	public String toString()
	{
		return key;	
	}
}
