import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Storage {
    
	/**
	 * Arjun's part
	 */
	
	//ALL STRINGS NEED TO BE CHANGED TO BE OF REPORT TYPE
	
	private List<String> dailyList = new ArrayList<String>();
	private List<String> monthlyList = new ArrayList<String>();
	
	/***
	 * Testing Constructor
	 */
	public Storage() {
		//Dummy list to test
		char aC= 't';
		for(int i = 0; i < 12; i++) {
			this.dailyList.add(Character.toString(aC));
			aC--;
		}
		this.dailyList.add("F");
		this.dailyList.add("A");
	}
	
	//Getters
	public List<String> getDailyList(){
		return Collections.unmodifiableList(this.dailyList);
	}
	
	public List<String> getMonthlyList(){
		return Collections.unmodifiableList(this.monthlyList);
	}
			
	/**
	 * @author: Carlos Rodriguez
	 * Helper method to generate a random pivot for
	 * the sorting algorithms in order to decrease
	 * the chances of approaching O(n^2) due to a 
	 * bad partioning. Changes the randomized pivot
	 * position with the last element of the lists.
	 * 
	 * @param lowIdx: lowest index to consider for
	 * the partition(inclusive).
	 * @param highIdx: highest index to consider
	 * for the partioning(inclusive). 
	 * @param: isDailyList: does the randomization
	 * for the dailyList if true, does it for the
	 * monthlyList if false. */
	private void randomPivot(int lowIdx, int highIdx, boolean isDailyList) {
		Random rnd = new Random();
		int pivotIdx = rnd.nextInt(highIdx-lowIdx) + lowIdx;
		if(isDailyList) {
			//Switching whatever is in pivotIdx with last element
			//to consider our pivot as new last element.
			String temp = this.dailyList.get(pivotIdx);
			this.dailyList.set(pivotIdx, this.dailyList.get(highIdx));
			this.dailyList.set(highIdx, temp);
		}
		else {
			String temp = this.monthlyList.get(pivotIdx);
			this.monthlyList.set(pivotIdx, this.monthlyList.get(highIdx));
			this.monthlyList.set(highIdx, temp); 
		}
	}
	
	/**
	 * @author: Carlos Rodriguez
	 * 
	 * @param isDailyList: boolean to define if the list
	 * referred to is the daily list if true, the monthly
	 * list if false.
	 */
	public void sortByStations(boolean isDailyList) {
		sortStation(0, this.dailyList.size()-1, isDailyList);
	}
	
	/**
	 * @author: Carlos Rodriguez
	 * 
	 * Helper method takes care of partitioning either the
	 * monthly list or the daily list. Invokes partitioning
	 * based on station.
	 * 
	 * @param lowIdx
	 * @param highIdx
	 * @param isDailyList
	 */
	private void sortStation(int lowIdx, int highIdx, boolean isDailyList) {
		if(isDailyList) {
			if(lowIdx < highIdx) {
				int partitionIdx = partitionByStation(lowIdx, highIdx, isDailyList);
				sortStation(lowIdx, partitionIdx-1, isDailyList);
				sortStation(partitionIdx+1, highIdx, isDailyList);
			}
		}
	}
	
	/**
	 * @author: Carlos Rodriguez
	 * 
	 * Evaluates the first char of a Report's station name,
	 * and sorts all the reports in a list.
	 * 
	 * @param lowIdx: lowest index to start the partition.
	 * @param highIdx: highest index to start the partition.
	 * @param isDailyList: to sort the dailyList if true,
	 * monthlyList if false.
	 * @return: Highest index reached by the partition.
	 */
	private int partitionByStation(int lowIdx, int highIdx, boolean isDailyList) {
		randomPivot(lowIdx, highIdx, isDailyList);
		int pivot = -1;
		int i = -1;
		if(isDailyList) {
			pivot = this.dailyList.get(highIdx).charAt(0);
			i = lowIdx - 1;
			
			for(int j=lowIdx; j < highIdx; j++) {
				if(this.dailyList.get(j).charAt(0) < pivot) {
					i++;
					String temp = this.dailyList.get(i);
					this.dailyList.set(i, this.dailyList.get(j));
					this.dailyList.set(j, temp);
				}
			}
			
			String temp = this.dailyList.get(i + 1);
			this.dailyList.set(i + 1, this.dailyList.get(highIdx));
			this.dailyList.set(highIdx, temp);
		}
		return i + 1;
	}
	
}

