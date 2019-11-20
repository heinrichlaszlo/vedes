import java.util.ArrayList;

public class Binfalista 
{

	public static void main(String[] args) 
	{
		LZWBinFa bf1 = new LZWBinFa();
		LZWBinFa bf2 = new LZWBinFa();
		LZWBinFa bf3 = new LZWBinFa();
		LZWBinFa bf4 = new LZWBinFa();
		LZWBinFa bf5 = new LZWBinFa();
		
		for (char c : "0111001".toCharArray()) 
		{
			bf1.egyBitFeldolg(c);
		}
		for (char c : "0111001".toCharArray()) 
		{
			bf2.egyBitFeldolg(c);
		}
		for (char c : "0111011".toCharArray()) 
		{
			bf3.egyBitFeldolg(c);
		}
		for (char c : "0101010".toCharArray()) 
		{
			bf4.egyBitFeldolg(c);
		}
		for (char c : "0111110".toCharArray()) 
		{
			bf5.egyBitFeldolg(c);
		}

		ArrayList<LZWBinFa> list = new ArrayList<LZWBinFa>();
		list.add(bf1);
		list.add(bf2);
		list.add(bf3);
		list.add(bf4);
		list.add(bf5);
		
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i).getMelyseg() + " " + list.get(i).getAtlag());
		}
		java.util.Collections.sort(list);
		System.out.println("------");
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i).getMelyseg() + " " + list.get(i).getAtlag());
		}
	}

}
