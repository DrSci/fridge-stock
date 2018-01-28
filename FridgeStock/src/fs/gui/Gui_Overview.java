package fs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.google.gson.Gson;

import fs.function.FileAccess;
import fs.structure.Compact4Saving;
import fs.structure.Datum;
import fs.structure.Product;

public class Gui_Overview extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1231654573736127502L;
	
	private ArrayList<Product> food;
	private Gson gson;
	
	private static String saveFile = "Lagerstand.txt";
	
	private JTextField[] names;
	private JTextField[] amounts;
	private JTextField[] timeLeft;
	
	private JButton[] opened;
	
	private JButton[] change;
	private JButton[] delete;
	
	private JPanel[] product;
	private JPanel productsPanel;
	private JScrollPane jsp;
	
	private JButton add;
	
	public Gui_Overview()
	{
		this.gson = new Gson();
		Compact4Saving tmp = this.gson.fromJson(FileAccess.open(new File(saveFile)), Compact4Saving.class);
		this.food = tmp.getProducts();
		
		/*
		int n = food.size();
		
		names = new JTextField[n];
		amounts = new JTextField[n];
		timeLeft = new JTextField[n];
		
		opened = new JButton[n];
		
		change = new JButton[n];
		delete = new JButton[n];
		
		product = new JPanel[n];
		
		productsPanel = new JPanel();
		productsPanel.setLayout(new GridLayout(0, 1));
		
		for(int i = 0; i < n; i++)
		{
			names[i] = new JTextField(food.get(i).getName());
			names[i].setEnabled(false);
			amounts[i] = new JTextField(String.valueOf(food.get(i).getAmount()));
			amounts[i].setEnabled(false);
			timeLeft[i] = new JTextField(String.valueOf(food.get(i).daysLeft()));
			timeLeft[i].setEnabled(false);
			
			opened[i] = new JButton(String.valueOf(food.get(i).isOpen()));
			opened[i].addActionListener(new ActionListener_Overview(this, 1, i));
			
			change[i] = new JButton("CHANGE");
			change[i].addActionListener(new ActionListener_Overview(this, 2, i));
			delete[i] = new JButton("DELETE");
			delete[i].addActionListener(new ActionListener_Overview(this, 3, i));
			
			product[i] = new JPanel();
			product[i].setLayout(new GridLayout(0, 6));
			product[i].add(names[i]);
			product[i].add(amounts[i]);
			product[i].add(timeLeft[i]);
			product[i].add(opened[i]);
			product[i].add(change[i]);
			product[i].add(delete[i]);
			
			productsPanel.add(product[i]);
		}
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(add, BorderLayout.SOUTH);
		*/
		
		productsPanel = new JPanel();
		jsp = new JScrollPane(productsPanel);
		
		add = new JButton("New Product");
		add.addActionListener(new ActionListener_Overview(this, 0, 0));
		
		this.addWindowListener(new WindowListener_Overview(this));
		
		this.refresh();
		
		// this.setModal(true);
	}
	
	public void refresh()
	{
		this.remove(this.jsp);
		this.remove(this.productsPanel);
		
		int n = food.size();
		
		names = new JTextField[n];
		amounts = new JTextField[n];
		timeLeft = new JTextField[n];
		
		opened = new JButton[n];
		
		change = new JButton[n];
		delete = new JButton[n];
		
		product = new JPanel[n];
		
		productsPanel = new JPanel();
		productsPanel.setLayout(new GridLayout(0, 1));
		
		for(int i = 0; i < n; i++)
		{
			names[i] = new JTextField(food.get(i).getName());
			names[i].setEditable(false);
			amounts[i] = new JTextField(String.valueOf(food.get(i).getAmount()));
			amounts[i].setEditable(false);
			int dl = food.get(i).getDaysLeft();
			timeLeft[i] = new JTextField(String.valueOf(dl));
			timeLeft[i].setEditable(false);
			this.timeLeft[i].setOpaque(true);
			
			if(dl < 0)
			{
				this.timeLeft[i].setBackground(Color.RED);
			}
			else if(dl < 7)
			{
				this.timeLeft[i].setBackground(Color.YELLOW);
			}
			else
			{
				this.timeLeft[i].setBackground(Color.GREEN);
			}
			
			opened[i] = new JButton("CLOSED");
			opened[i].setOpaque(true);
			opened[i].setBackground(Color.GREEN);
			
			if(food.get(i).isOpen())
			{
				opened[i].setText("OPENED");
				opened[i].setBackground(Color.RED);
			}
			
			opened[i].addActionListener(new ActionListener_Overview(this, 1, i));
			
			change[i] = new JButton("CHANGE");
			change[i].addActionListener(new ActionListener_Overview(this, 2, i));
			delete[i] = new JButton("DELETE");
			delete[i].addActionListener(new ActionListener_Overview(this, 3, i));
			
			product[i] = new JPanel();
			product[i].setLayout(new GridLayout(0, 4));
			product[i].add(names[i]);
			product[i].add(amounts[i]);
			
			JPanel statusPanel = new JPanel();
			statusPanel.setLayout(new GridLayout(0, 2));
			statusPanel.add(timeLeft[i]);
			statusPanel.add(opened[i]);
			product[i].add(statusPanel);
			
			JPanel optionPanel = new JPanel();
			optionPanel.setLayout(new GridLayout(0, 2));
			optionPanel.add(change[i]);
			optionPanel.add(delete[i]);
			product[i].add(optionPanel);
			
			productsPanel.add(product[i]);
		}
		
		this.jsp = new JScrollPane(this.productsPanel);
		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(add, BorderLayout.SOUTH);
		
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void setProducts(ArrayList<Product> products)
	{
		this.food = products;
	}
	
	public void openFood(int index)
	{
		this.food.get(index).changeOpen(true);
	}
	
	public void closeFood(int index)
	{
		this.food.get(index).changeOpen(false);
	}
	
	public Product getProduct(int index)
	{
		return this.food.get(index);
	}
	
	public void editFood(int index)
	{
		names[index].setEditable(true);
		amounts[index].setEditable(true);
		timeLeft[index].setEditable(true);
		timeLeft[index].setText(this.food.get(index).getMhd());
		timeLeft[index].setBackground(Color.WHITE);
		
		this.change[index].setText("SAVE");
	}
	
	public void saveFood(int index)
	{
		names[index].setEditable(false);
		amounts[index].setEditable(false);
		timeLeft[index].setEditable(false);
		
		this.food.get(index).setName(names[index].getText());
		this.food.get(index).setAmount(Integer.valueOf(this.amounts[index].getText()));
		this.food.get(index).setMhd(timeLeft[index].getText());
		
		int dl = this.food.get(index).getDaysLeft();
		this.timeLeft[index].setText(String.valueOf(dl));
		this.timeLeft[index].setOpaque(true);
		
		if(dl < 0)
		{
			this.timeLeft[index].setBackground(Color.RED);
		}
		else if(dl < 7)
		{
			this.timeLeft[index].setBackground(Color.YELLOW);
		}
		else
		{
			this.timeLeft[index].setBackground(Color.GREEN);
		}
		
		this.change[index].setText("CHANGE");
	}
	
	public void removeFood(int index)
	{
		this.food.remove(index);
	}
	
	public void addFood()
	{
		this.food.add(new Product("", 1, new Datum("01.01.1970")));
	}
	
	public Product getFood(int index)
	{
		return this.food.get(index);
	}
	
	public void saveData()
	{
		FileAccess.write(gson.toJson(new Compact4Saving(this.food)), new File(saveFile));
	}
}