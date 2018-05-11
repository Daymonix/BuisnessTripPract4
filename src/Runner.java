import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Runner {
	
	public static void main(String[] args) {
		String data = "";
		data = readFile("in.txt");
		
		String[] lns = data.split("\n");
		
		double dRate = Double.parseDouble(getVal(lns[0]));
		int[] ids = parseIds(getVal(lns[1]));
		
		List<Item> its = new ArrayList<Item>();
		
		for(int i : ids) {
			its.add(new Item(i));
		}
		
		for(int i = 2; i < lns.length; i++)
		{	
			for(Item it : its) {
				if(!it.isFull()) {
					if(it.getId() == Integer.parseInt(getId(lns[i]))) {
						String n = getName(lns[i]).toLowerCase();
						String val = getVal(lns[i]);
						
						if(n.compareTo("account") == 0) {
							it.setAccount(val);
						}
						
						if(n.compareTo("transport") == 0) {
							it.setTransport(Double.parseDouble(val));
						}
						
						if(n.compareTo("days") == 0) {
							it.setDays(Integer.parseInt(val));
						}
					}
				}
			}
		}
		
		
		System.out.println("Original list");
		for(Item i : its) {
			System.out.println(i);
		}
		System.out.println("");
		
		Item[] is = its.toArray(new Item[its.size()]);
		Arrays.sort(is, Collections.reverseOrder());
		
		System.out.println("Sorted list");
		for(int i = 0; i < is.length; i++) {
			System.out.println(is[i]);
		}
		
	}
	
	static String getName(String data) {
		data = data.split("=")[0].trim();
		return data.substring(0, data.length() - 2);
	}
	
	static String getId(String data) {
		data = data.split("=")[0].trim();
		return data.substring(data.length() - 2, data.length());
	}
	
	static String getVal(String data) {
		return data.split("=")[1].trim();
	}
	
	static int[] parseIds(String data) {
		List<Integer> r = new ArrayList<Integer>();
		String[] ids = data.split(";");
		for(String s : ids) {
			r.add(Integer.parseInt(s.trim()));
		}
		return Arrays.stream(r.toArray(new Integer[r.size()])).mapToInt(Integer::intValue).toArray();
	}
	
	
	static String readFile(String path) {
		String r = "";
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			r = new String(encoded);
		} catch (Exception ex) {
			System.out.println("Invalid file or filename");
		}
		return r;
	}
	
	static void writeFile(String path, String data) {
		try {
			String str = data;
		    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		    writer.write(str);
		     
		    writer.close();
		} catch (Exception ex) {
			System.out.println("Invalid file or filename");
		}
	}
}
