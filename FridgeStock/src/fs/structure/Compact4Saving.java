package fs.structure;

import java.util.ArrayList;

public class Compact4Saving 
{
	private Product[] save;
	
	public Compact4Saving(ArrayList<Product> lagerstand)
	{
		this.save = new Product[lagerstand.size()];
		
		for(int i = 0; i < lagerstand.size(); i++)
		{
			save[i] = lagerstand.get(i);
		}
	}
	
	public ArrayList<Product> getProducts()
	{
		ArrayList<Product> lagerstand = new ArrayList<Product>();
		
		for(int i = 0; i < this.save.length; i++)
		{
			lagerstand.add(this.save[i]);
		}
		
		return lagerstand;
	}
}