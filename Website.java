package tripadvisor;
import java.util.*;
import java.text.*;

public class Website {
	// list of hashes
	HashSet<String> hashset = new HashSet<String>();
	
	// list of date, content pairs
	ArrayList<String> dateArray = new ArrayList<String>();
	ArrayList<String> contentArray = new ArrayList<String>();
	
	// converts strings to dates
	DateFormat strdate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
	
	public Website() {
	}
	
	public void addSite(String[] data) {
		// add the hash to the hashset
		this.hashset.add(data[0]);
		
		// add the date and content pair to the list
		this.dateArray.add(data[1]);
		this.contentArray.add(data[2]);
	}
	
	// check if a certain hash is already in the hashset
	public boolean containsHash(String hash) {
		
		return this.hashset.contains(hash);
	}
	
	private int findDate(Date searchDate, ArrayList<String> dates) {
		try {
			// go to the middle of the array
			int index = dates.size() / 2;
			Date indexDate = strdate.parse(dates.get(index));
			
			// if it's the same date, return that index
			if (indexDate.equals(searchDate)) {
				return index;
			}
			
			// if it's less than the search date, check if the next date is greater
			if (indexDate.before(searchDate)) {
				
				// if there is no next date, return index for this date
				if (index == (dates.size() - 1)) {
					return index;
				}
				
				// if yes, then return contents for that date\
				Date nextDate = strdate.parse(dates.get(index+1));
				
				if (searchDate.before(nextDate)) {
					return index;
				}
				
				// if no, then go to middle of the right portion and try again
				else {
					ArrayList<String> newDatesArray = new ArrayList<String>(dates.subList(index, dates.size()));
					return index+findDate(searchDate,newDatesArray);
				}
			}
			
			// if it's more than date, go to middle of left portion and try again
			else {
				
				// if index is the beginning of the array then there wasn't an older version stored, return this version
				if (index == 0) {
					return index;
				}
				else {
					ArrayList<String> newDatesArray = new ArrayList<String>(dates.subList(0, index));
					return findDate(searchDate,newDatesArray);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error in finding date index");
			return -1;
		}
	}
	
	public String getContent(String date) {
		try {
			Date searchDate = strdate.parse(date);
			
			int index = findDate(searchDate, this.dateArray);
			return this.contentArray.get(index);
		}
		catch (Exception e) {
			return "Could not find content";
		}
	}
	// each version contains: hash, date, content
}
