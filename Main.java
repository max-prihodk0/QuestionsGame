import java.util.*;
import java.io.*;

public class Main {

	public static LinkedList<Animal> list = new LinkedList<Animal>();

  public static void main(String[] args) {
		startProc();
  }

	public static void startProc()
	{
		try 
		{
			FileInputStream file = new FileInputStream("data.txt");
			ObjectInputStream in = new ObjectInputStream(file);
				
			
			list = (LinkedList<Animal>)in.readObject();
				
			in.close();
			file.close();
		}
		catch (IOException ex) { }
		catch (ClassNotFoundException ex) { }

		if (list.size() == 0)
		{
			list.add(new Animal("Dog", new AnimalProperty[] 
			{ 
				new AnimalProperty("mammal", "Is it a mammal"),
				new AnimalProperty("fur", "Does it have fur"),
				new AnimalProperty("four legs", "Does it have four legs"),
				new AnimalProperty("bark", "Does it bark")
			}));

			list.add(new Animal("Cat", new AnimalProperty[] 
			{ 
				new AnimalProperty("mammal", "Is it a mammal"),
				new AnimalProperty("fur", "Does it have fur"),
				new AnimalProperty("four legs", "Does it have four legs"),
				new AnimalProperty("meow", "Does it meow")
			}));

			list.add(new Animal("Monkey", new AnimalProperty[] 
			{ 
				new AnimalProperty("mammal", "Is it a mammal"),
				new AnimalProperty("fur", "Does it have fur"),
				new AnimalProperty("four legs", "Does it have four legs"),
				new AnimalProperty("bananas", "Does it like bananas")
			}));

			list.add(new Animal("Elephant", new AnimalProperty[] 
			{ 
				new AnimalProperty("mammal", "Is it a mammal"),
				new AnimalProperty("four legs", "Does it have four legs"),
				new AnimalProperty("horns", "Does it have horns"),
				new AnimalProperty("trunk", "Does it have a trunk")
			}));


			startProc();
			return;
		}

		QuestionsGame game = new QuestionsGame((LinkedList<Animal>)list.clone());

		Animal a = game.startQuestions();

		if (a != null)
			list.add(a);

		try 
		{
			FileOutputStream file = new FileOutputStream("data.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
				
			
			out.writeObject(list);
				
			out.close();
			file.close();
		}
		catch (IOException ex) { }
	}
}
