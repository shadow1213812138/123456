import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class WebCrawler {

	public static void main(String[] args) 
	throws IOException{
	 System.out.println("Enter a Url:");
	 Scanner input = new Scanner(System.in);
	 String URL = input.nextLine();
	 Crawer(URL);
     input.close();
	}
	public static void Crawer(String URL)
	throws IOException{
	   ArrayList<String> ListPending  = new ArrayList<>();
	   ArrayList<String> ListCreated  = new ArrayList<>();
	   ArrayList<String> List;
	   ListPending.add(URL);
	 try {
		 while(!ListPending.isEmpty() && ListCreated.size() <= 100) {
			URL = ListPending.remove(0);
            List = getSubURLs(URL);
            ListCreated.add(URL);
            System.out.println("Crawl" + URL);
            for(String s: List)
            	if(!ListCreated.contains(s))
            		ListPending.add(s);
		 }
	  }
	 catch(java.net.MalformedURLException ex){
		 
	 }
	}
	public static ArrayList<String> getSubURLs(String URL)
	throws IOException{
		String URLString;
		ArrayList<String> List = new ArrayList<String>();
		int current = 0,index = 0;
		java.net.URL url1 = new java.net.URL(URL);
		Scanner input = new Scanner(url1.openStream());
		while(input.hasNext()) {
		URLString = input.nextLine();
		current = URLString.indexOf("http:");
		while(current > 0){
			index = URLString.indexOf("\"", current);
			if(index > 0) {
				List.add(URLString.substring(current, index));
				current = URLString.indexOf("http:", index);
			}
			else
			  current  = -1;
		  }
		}
		input.close();
		return List;
	}
}
