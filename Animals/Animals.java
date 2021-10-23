import java.io.*;

public class Animal implements Serializable 
{
	public AnimalProperty[] properties;
	public String name;

	public Animal(String name, AnimalProperty[] properties)
	{
		this.name = name;
		this.properties = properties;
	}

	public boolean hasProperty(String property)
	{
		for (AnimalProperty p : properties)
		{
			if (p.key == property)
				return true;
		}

		return false;
	}

	public int getProperty(String property) 
	{
		int i;
		for (i = 0; i < properties.length; i++)
		{
			if (properties[i].key == property)
				return i;
		}

		return i;
	}

	public String toString()
	{
		return name;
	}
}
