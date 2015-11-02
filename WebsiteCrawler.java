package tripadvisor;

public class WebsiteCrawler {
	// used in tester to constantly create "new" sites crawled
	private String hash;
	private String content;

	public WebsiteCrawler() {
	}
	
	// store hash and content into a string array and return to the wayback machine
	// actual implementation of a crawler would probably go here but it's assumed it works
	public String[] retrieveSite(String address, String date) {
		String[] website = new String[3];
		website[0] = this.hash;
		website[1] = date;
		website[2] = this.content;
		
		return website;
	}
	
	// allows hash and content to be changed
	public void update(String hash, String content) {
		this.hash = hash;
		this.content = content;
	}

}
