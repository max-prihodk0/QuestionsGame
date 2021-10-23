import java.util.*;
import java.io.*;

public class QuestionsGame 
{
	// Animals list that contans all the animals that
	// the computer knows
	private LinkedList<Animal> animals;
	public Scanner console;

	// Initializes animals list and console input stream 
	// and assigns all known animals and their properties
	// to the animals array
	public QuestionsGame(LinkedList<Animal> animals)
	{
		this.animals = animals;
		console = new Scanner(System.in);
	}


	// Asks the user a series of questions to try and determine the animal that they
	// have chosen. If the user has chosen an animal that the computer does not
	// know of, the animal object will be returned.
	public Animal startQuestions()
	{
		LinkedList<AnimalProperty> newProperties = new LinkedList<AnimalProperty>();
		AnimalProperty sProperty = new AnimalProperty(null, null);

		for (int i = 0; animals.size() > 1; i++)
		{
			AnimalProperty property = getProperty(sProperty.key);

			if (askQuestion(property.question))
			{
				sProperty = property;
				newProperties.add(property);
				removeAnimalsWithout(property.key);
				continue;
			}

			removeAnimalsWith(property.key);
			i--;
		}

		if (animals.size() == 1 && animals.get(0).getProperty(sProperty.key) != animals.get(0).properties.length)
		{
			for (int i = animals.get(0).getProperty(sProperty.key) + 1; i < animals.get(0).properties.length; i++)
			{
				if (askQuestion(animals.get(0).properties[i].question))
				{
					newProperties.add(animals.get(0).properties[i]);
				}
				else
				{
					System.out.print("What is the name of your animal? ");
					String name = console.nextLine();

					System.out.print("What is a unique property of your animal? (e.g. hoves, trunk, fur) ");
					String propertyName = console.nextLine();

					System.out.print("What is a question for the property you just gave? (e.g. Does it have hoves?) ");
					String question = console.nextLine();

					newProperties.add(new AnimalProperty(propertyName, question));
					return new Animal(name, newProperties.toArray(new AnimalProperty[0]));
				}
			}
		}

		if (animals.size() == 1 && askQuestion("Would your animal happen to be a " + animals.get(0).name.toLowerCase()))
		{
			System.out.println("Great, I got it right!");
			return null;
		}
		return null;
	}

	// Removes all instances of an animal in the animals array
	// with the given property `property`
	private void removeAnimalsWith(String property)
	{
		for (int i = 0; i < animals.size(); i++)
		{
			if (animals.get(i).hasProperty(property))
			{
				animals.remove(i);
				i--;
			}
		}
	}

	// Removes all instances of an animal in the animals array
	// with the given property `property`
	private void removeAnimalsWithout(String property)
	{
		for (int i = 0; i < animals.size(); i++)
		{
			if (!animals.get(i).hasProperty(property))
			{
				animals.remove(i);
				i--;
			}
		}
	}

	private AnimalProperty getProperty(String oldProperty)
	{
		if (oldProperty == null)
			return animals.get(0).properties[0];

		AnimalProperty newProperty = null;

		for (int i = 0; i < animals.get(0).properties.length; i++)
		{
			if (animals.get(0).properties[i].key == oldProperty)
				newProperty = animals.get(0).properties[i + 1];
		}

		return newProperty;
	}

	// Prompts the user with the given question and accepts 'y' or 'n'
	// if user input is not 'y' or 'n', then prompt the user again
	private boolean askQuestion(String question)
	{
		System.out.print(question.replace('?', ' ') + " (y/n)? ");
    String response = console.nextLine().trim().toLowerCase();

		while (!response.equals("y") && !response.equals("n")) 
		{
				System.out.println("Please answer y or n.");
				System.out.print(question.replace('?', ' ') + " (y/n)? ");
				response = console.nextLine().trim().toLowerCase();
		}

		return response.equals("y");
	}
}
