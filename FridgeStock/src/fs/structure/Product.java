package fs.structure;

public class Product
{
	private String name;
	private Datum mhd;
	private int amount;
	private boolean opened;
	
	public Product(String name, int amount, Datum mhd)
	{
		this.name = name;
		this.mhd = mhd;
		this.amount = amount;
		this.opened = false;
	}
	
	public void changeOpen(boolean status)
	{
		this.opened = status;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public boolean isOpen()
	{
		return this.opened;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public void setMhd(String date)
	{
		this.mhd = new Datum(date);
	}
	
	public String getMhd()
	{
		return this.mhd.getDay() + "." + this.mhd.getMonth() + "." + this.mhd.getYear();
	}
	
	public int getDaysLeft()
	{
		return this.mhd.getTimeLeft();
	}
}