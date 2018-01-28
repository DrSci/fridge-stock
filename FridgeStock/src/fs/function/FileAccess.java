package fs.function;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileAccess 
{
	public static File selectDirectory2open(Component parent)
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
	    
		if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
		{
			return fc.getSelectedFile();
		}
		
		return null;
	}
	
	public static File selectFile2open()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		
		if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
		{
			return fc.getSelectedFile();
		}
		
		return null;
	}
	
	public static File selectFile2write()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		
		if( fc.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION )
		{
			return fc.getSelectedFile();
		}
		
		return null;
	}
	
	public static byte[] open(File dir, int alibi)
	{
		byte[] input;
		
		try 
		{
			input = Files.readAllBytes(dir.toPath());
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
			input = new byte[0];
		}
		
		return input;
	}
	
	public static String open(File file)
	{
		String output = "";
		
	    try 
	    {
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	    	int buchst;
	    	
	    	while((buchst = br.read()) != -1)
	    	{
	    		output = output + (char) buchst;
	    	}
	    	
	    	br.close(); // pot. FIXME
	    }
	    catch (FileNotFoundException fnfe)
	    { 
	    	fnfe.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    
	    return output;
	}
	
	public static void write(String text, File file)
	{
		if(file.exists())
		{
			Object[] options = {"Overwrite", "Cancel"};
			
			if(JOptionPane.showOptionDialog(new JFrame(), "File exists. Overwrite?", "File already exists", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]) == 1)
			{
				return;
			}
		}
		
		try 
		{
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(text);
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String[] filter(String input, char seperator)
	{
		int z1 = 0;
		boolean other = false;
		
		for(int i = 0; i < input.length(); i++)
		{
			if(other && input.charAt(i) == seperator)
			{
				other = false;
				z1++;
			}
			else if(! other && input.charAt(i) != seperator)
			{
				other = true;
			}
		}
		
		String[] output = new String[z1];
		z1 = 0;
		other = false;
		
		for(int i = 0; i < input.length(); i++)
		{
			if(other && input.charAt(i) == seperator)
			{
				other = false;
				z1++;
			}
			else if(! other && input.charAt(i) != seperator)
			{
				other = true;
				output[z1] = "" + input.charAt(i);
			}
			else if(input.charAt(i) != seperator)
			{
				output[z1] += input.charAt(i);
			}
		}
		
		return output;
	}
	
	/*
	public Property[] getProperties(String[] lines)
	{
		Property[] output = new Property[lines.length];
		
		for(int i = 0; i < lines.length; i++)
		{
			String name = "";
			String value = "";
			boolean val = false;
			
			for(int j = 0; j < lines[i].length(); j++)
			{
				if(lines[i].charAt(j) == '=')
				{
					val = true;
				}
				else if(val)
				{
					value += lines[i].charAt(j);
				}
				else
				{
					name += lines[i].charAt(j);
				}
			}
			
			output[i] = new Property(name, value);
		}
		
		return output;
	}
	*/
	
	public static String getMD5(String password)
	{
		String hash = null;
		
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			hash = sb.toString();
		}
		catch(NoSuchAlgorithmException nsae)
		{
			nsae.printStackTrace();
		}
		
		return hash;
	}
}