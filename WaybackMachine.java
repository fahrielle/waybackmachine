package tripadvisor;
import java.util.*;

public class WaybackMachine {

	// hashmap to store (address, website) pairs
	// website structure contains all the stored versions of a webpage (i.e. different dates)
	HashMap<String,Website> webdata = new HashMap<String,Website>();
	
	// store website 
		// set up crawler??? takes web address, returns (checksum, content)
		// checksum checks if version is unique
	
	public void store(String address, String date, WebsiteCrawler crawler) {
		// find the (hash, content) of a given address using the crawler
		String[] siteData = new String[3];
		siteData = crawler.retrieveSite(address, date);
		
		// check if the address has been stored before
		// if no, initialize everything
		if (!webdata.containsKey(address)) {
			Website newSite = new Website();
			newSite.addSite(siteData);
			
			webdata.put(address, newSite);
			
			System.out.println("Website "+address+" has been added.");
		}
			
		// if yes, check if this particular hash is in storage already
		else {
			Website site = webdata.get(address);
			String hash = siteData[0];
			
			// if yes, don't update
			if (site.containsHash(hash)) {
				System.out.println("Latest version already exists in cache.");
			}
			
			// if no, store new date, new hash, new content
			else {
				site.addSite(siteData);
				System.out.println("Latest version of "+address+" has been added.");
			}
		}
			
	}
	
	// retrieve website (address, date)
	public String retrieve(String address, String date) {
		// try searching for the address in the database
		try {
			Website site = webdata.get(address);
			
			// find the best stored date/content that will satisfy the given date
			// e.g. if date is 4 and stored dates are 3 and 5, return the content for 3
			return site.getContent(date);
		}
		
		// if it fails then return error message
		catch (Exception e) {
			return "Error: site has not been cached yet!";
		}
	}
	
	// gui
	
	
	public static void main(String[] args) {
		
		// store sites
		// display gui with info
		
		WebsiteCrawler webCrawler = new WebsiteCrawler();
		WaybackMachine m = new WaybackMachine();
		
		webCrawler.update("a", "hello world");
		m.store("www.a.com", "Sep 01, 2015", webCrawler);
		webCrawler.update("a", "hello world");
		m.store("www.a.com", "Sep 02, 2015", webCrawler);
		webCrawler.update("b", "hello world1");
		m.store("www.a.com", "Sep 03, 2015", webCrawler);
		webCrawler.update("b", "hello world1");
		m.store("www.a.com", "Sep 04, 2015", webCrawler);
		webCrawler.update("b", "hello world1");
		m.store("www.a.com", "Sep 05, 2015", webCrawler);
		webCrawler.update("c", "hello world2");
		m.store("www.a.com", "Sep 06, 2015", webCrawler);
		webCrawler.update("d", "hello world3");
		m.store("www.a.com", "Sep 07, 2015", webCrawler);
		webCrawler.update("d", "hello world3");
		m.store("www.a.com", "Sep 08, 2015", webCrawler);
		webCrawler.update("e", "hello world4");
		m.store("www.a.com", "Sep 09, 2015", webCrawler);
		
		
		String result = m.retrieve("www.a.com", "Sep 01, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 02, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 03, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 04, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 05, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 06, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 07, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 08, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 09, 2015");
		System.out.println(result);
		
		webCrawler.update("f", "goodbye world");
		m.store("www.b.com", "Sep 01, 2015", webCrawler);
		result = m.retrieve("www.b.com", "Sep 09, 2015");
		System.out.println(result);
		result = m.retrieve("www.a.com", "Sep 08, 2015");
		System.out.println(result);

	}

}
