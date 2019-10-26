package Tools;

public class RandomGenerator 
{
	private String RandomKey  = "";
	private int RandomKeySize = 20;
	
	public RandomGenerator() { }

	public String Get(int _RandomKeySize)
	{
		this.RandomKeySize = _RandomKeySize;
		
		while(this.RandomKey.length() < this.RandomKeySize)
		{
			int KeyOption = (int) (Math.random() * 3);
			
			switch(KeyOption)
			{
				case(0): { this.RandomKey += (int)  (Math.random() * 10); 	   break; }
				case(1): { this.RandomKey += (char) (Math.random() * 26 + 65); break; }
				case(2): { this.RandomKey += (char) (Math.random() * 26 + 97); break; }
				default: { break; }
			}
		}
		
		return this.RandomKey;
	}
}