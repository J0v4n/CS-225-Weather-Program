import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Storage {

	/**
	 * Arjun's part
	 * class variables (lists for monthly and daily reports)
	 * constructor
	 * getAllReport()
	 * readCSV()
	 * writeCSV()
	 */

	/**
	 * @author: Arjun Bott
	 * [class variable]
	 * a list for a MonthlyReport
	 */
	private ArrayList<MonthlyReport> monthlyList;

	/**
	 * @author: Arjun Bott
	 * [class variable]
	 * a list for DailyReport
	 */
	private ArrayList<DailyReport> dailyList;



	/**
	 * @author: Arjun Bott
	 * default constructor
	 */
	public Storage() {
		this.monthlyList = new ArrayList<MonthlyReport>();
		this.dailyList = new ArrayList<DailyReport>();
	}

	/**
	 * @author: Arjun Bott
	 * [getAllDaily method]
	 * @returns an array copy of the current daily report list
	 */
	public IReport[] getAllDaily() {
		IReport[] buffer = new IReport[this.dailyList.size()];

		for (int i = 0; i < this.dailyList.size(); i++) {
			buffer[i] = this.dailyList.get(i);
		}

		return buffer;
	}

	/**
	 * @author: Arjun Bott
	 * [getAllMonthly method]
	 * @returns an array copy of the current monthly report list
	 */
	public IReport[] getAllMonthly() {
		IReport[] buffer = new IReport[this.monthlyList.size()];

		for (int i = 0; i < this.monthlyList.size(); i++) {
			buffer[i] = this.monthlyList.get(i);
		}

		return buffer;
	}

	/**
	 * @author: Arjun Bott
	 * [getAllReport method]
	 * @deprecated use the getAllDaily and/or getAllMonthly instead
	 * @returns an array copy of the current monthly and daily report lists
	 */
	@Deprecated
	public IReport[] getAllReport() {
		IReport[] buffer = new IReport[this.monthlyList.size() + this.dailyList.size()];

		for (int i = 0; i < this.monthlyList.size(); i++) {
			buffer[i] = this.monthlyList.get(i);
		}

		for (int i = 0; i < this.dailyList.size(); i++) {
			buffer[this.monthlyList.size() + i] = this.dailyList.get(i);
		}

		return buffer;
	}

	/**
	 * @author: Arjun Bott
	 * @param  filename the CSV to read
	 * @return an IReport[]
	 */
	public IReport[] readCSV(String filename) throws FileNotFoundException {
		ArrayList<IReport> buffer = new ArrayList<IReport>();
		Scanner reader = new Scanner(new File(filename));
		String[] tokens;
		String lastStationName;

		// parse the first line for metadata
		tokens = reader.next().split(",");
		String[] dailyTokens = new String[]{
			"Station",
			"Date",
			"Daily Average Wind Speed",
			"Daily Max Wind Speed",
			"Daily Precipitation"
		};

		// check if this is a daily report if the lengths are the same
		// if the lengths are not the same, then this isn't a daily report
		boolean isDailyReport = tokens.length == dailyTokens.length;
		for (int i = 0; isDailyReport && i < tokens.length; i++) {
			// does tokens at i minus the whitespace "equal" dailyTokens at i
			isDailyReport = tokens[i].trim().equals(dailyTokens[i]);
		}

		String[] monthlyTokens = new String[] {
			"Station",
			"Date",
			"Monthly Total Precipitation",
			"Monthly Minimum Temperature",
			"Monthly Average Temperature",
			"Monthly Maximum Temperature"
		};

		// check if this is a monthly report if the lengths are the same
		// if the lengths are not the same, then this isn't a monthly report
		boolean isMonthlyReport = tokens.length == monthlyTokens.length;
		for (int i = 0; isMonthlyReport && i < tokens.length; i++) {
			// does tokens at i minus the whitespace "equal" monthlyTokens at i
			isMonthlyReport = tokens[i].trim().equals(monthlyTokens[i]);
		}


		while (isDailyReport && reader.hasNext()) {
			tokens = reader.next().split(",");
			buffer.add(new DailyReport(
				tokens[0], // date
				tokens[1], // station name
				Double.valueOf(tokens[2]).doubleValue(), // daily average wind speed
				Double.valueOf(tokens[3]).doubleValue(), // daily max wind speed
				Double.valueOf(tokens[4]).doubleValue()  // daily precipitation
			));
		}

		while (isMonthlyReport && reader.hasNext()) {
			tokens = reader.next().split(",");
			buffer.add(new MonthlyReport(
				tokens[0], // date
				tokens[1], // station name
				Double.valueOf(tokens[2]).doubleValue(), // monthly average temperature
				Double.valueOf(tokens[3]).doubleValue(), // monthly high temperature
				Double.valueOf(tokens[4]).doubleValue(), // monthly low temperature
				Double.valueOf(tokens[5]).doubleValue()  // monthly precipitation
			));
		}

		IReport[] arrayBuffer = new IReport[buffer.size()];
		for (int i = 0; i < arrayBuffer.length; i++) {
			arrayBuffer[i] = buffer.get(i);
		}

		return arrayBuffer;
	}


	/**
	 * @author: Carlos Rodriguez
	 * First sort to use, based on date
	 * on both daily and monthly lists.
	 */
	public void initialSortByDate() {
		sortByDate(1);
		sortByDate(2);
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
	 * for the dailyList if 1, does it for the
	 * monthlyList if 2. */
	private void randomPivot(int lowIdx, int highIdx, int isDailyList) {
		Random rnd = new Random();
		int pivotIdx = rnd.nextInt(highIdx-lowIdx) + lowIdx;
		switch(isDailyList) {
			case 1:
				//Switching whatever is in pivotIdx with last element
				//to consider our pivot as new last element.
				DailyReport dTemp = this.dailyList.get(pivotIdx);
				this.dailyList.set(pivotIdx, this.dailyList.get(highIdx));
				this.dailyList.set(highIdx, dTemp);
				break;
			case 2:
				MonthlyReport mTemp = this.monthlyList.get(pivotIdx);
				this.monthlyList.set(pivotIdx, this.monthlyList.get(highIdx));
				this.monthlyList.set(highIdx, mTemp);
				break;
			default:
				System.out.println("Error, list not identified!");
		}
	}

	/**
	 * @author: Carlos Rodriguez
	 *
	 * @param isDailyList: int to define if the list
	 * referred to is the daily list if 1, the monthly
	 * list if 2.
	 */
	public void sortByStations(int isDailyList) {
		sortStation(0, this.dailyList.size()-1, isDailyList);
	}

	/**
	 * @author: Carlos Rodriguez
	 *
	 * @param isDailyList: int to define if the list
	 * referred to is the daily list if 1, the monthly
	 * list if 2.
	 */
	public void sortByDate(int isDailyList) {
		sortDate(0, this.dailyList.size()-1, isDailyList);
	}

	/**
	 * @author: Carlos Rodriguez
	 *
	 * Helper method takes care of partitioning either the
	 * monthly list or the daily list. Invokes partitioning
	 * based on station recursively.
	 *
	 *
	 * @param lowIdx: lowest index to start partition.
	 * @param highIdx: highest index to partition at.
	 * @param isDailyList: int to define if the list
	 * referred to is the daily list if 1, the monthly
	 * list if 2.
	 */
	private void sortStation(int lowIdx, int highIdx, int isDailyList) {
		if(lowIdx < highIdx) {
			int partitionIdx = partitionByStation(lowIdx, highIdx, isDailyList);
			sortStation(lowIdx, partitionIdx-1, isDailyList);
			sortStation(partitionIdx+1, highIdx, isDailyList);
		}
	}

	/**
	 * @author: Carlos Rodriguez
	 *
	 * Helper method takes care of partitioning either the
	 * monthly list or the daily list. Invokes partitioning
	 * based on date recursively.
	 *
	 *
	 * @param lowIdx: lowest index to start partition.
	 * @param highIdx: highest index to partition at.
	 * @param isDailyList: int to define if the list
	 * referred to is the daily list if 1, the monthly
	 * list if 2.
	 */
	private void sortDate(int lowIdx, int highIdx, int isDailyList) {
		if(lowIdx < highIdx) {
			int partitionIdx = partitionByDate(lowIdx, highIdx, isDailyList);
			sortDate(lowIdx, partitionIdx-1, isDailyList);
			sortDate(partitionIdx+1, highIdx, isDailyList);
		}
	}

	/**
	 * @author: Carlos Rodriguez
	 *
	 * Evaluates the first char of a Report's station name
	 * as pivot, and sorts all the reports in a list based
	 * on partitions (from lowest index entered to highest)
	 * comparing the values with the chosen pivot.
	 *
	 * @param lowIdx: lowest index to start the partition.
	 * @param highIdx: highest index to start the partition.
	 * @param isDailyList: to sort the dailyList if 1,
	 * monthlyList if 2.
	 * @return: Highest index reached by the partition.
	 */
	private int partitionByStation(int lowIdx, int highIdx, int isDailyList) {
		randomPivot(lowIdx, highIdx, isDailyList);
		int pivot = -1;
		int i = -1;

		//DailyList Case:
		if(isDailyList == 1) {
			pivot = this.dailyList.get(highIdx).getStationName().charAt(0);
			i = lowIdx - 1;

			for(int j=lowIdx; j < highIdx; j++) {
				if(this.dailyList.get(j).getStationName().charAt(0) < pivot) {
					i++;
					DailyReport temp = this.dailyList.get(i);
					this.dailyList.set(i, this.dailyList.get(j));
					this.dailyList.set(j, temp);
				}
			}

			DailyReport temp = this.dailyList.get(i + 1);
			this.dailyList.set(i + 1, this.dailyList.get(highIdx));
			this.dailyList.set(highIdx, temp);
		}
		//MonthlyList Case
		if(isDailyList == 2){
			pivot = this.monthlyList.get(highIdx).getStationName().charAt(0);
			i = lowIdx - 1;

			for(int j=lowIdx; j < highIdx; j++) {
				if(this.monthlyList.get(j).getStationName().charAt(0) < pivot) {
					i++;
					MonthlyReport temp = this.monthlyList.get(i);
					this.monthlyList.set(i, this.monthlyList.get(j));
					this.monthlyList.set(j, temp);
				}
			}

			MonthlyReport temp = this.monthlyList.get(i + 1);
			this.monthlyList.set(i + 1, this.monthlyList.get(highIdx));
			this.monthlyList.set(highIdx, temp);
		}
		return i + 1;
	}

	/**
	 * @author: Carlos Rodriguez
	 *
	 * Evaluates the date of a Report as pivot, sorts all
	 * the reports in a list basedon partitions (from
	 * lowest index entered to highest) comparing the values
	 * (year, month, day order) with the chosen pivot.
	 *
	 * @param lowIdx: lowest index to start the partition.
	 * @param highIdx: highest index to start the partition.
	 * @param isDailyList: to sort the dailyList if 1,
	 * monthlyList if 2.
	 * @return: Highest index reached by the partition.
	 */
	private int partitionByDate(int lowIdx, int highIdx, int isDailyList) {
		randomPivot(lowIdx, highIdx, isDailyList);
		int[] pivot = new int[3];
		int i = -1;

		//DailyList Case:
		if(isDailyList == 1) {
			String foundDate[] = this.dailyList.get(highIdx).getDate().split("/");
			for(int k=0; i<3; k++) {
				pivot[k]=Integer.parseInt(foundDate[k]);
			}
			i = lowIdx - 1;

			for(int j=lowIdx; j < highIdx; j++) {
				foundDate = this.dailyList.get(highIdx).getDate().split("/");
				int[] currDate = new int[3];
				for(int k=0; i<3; k++) {
					currDate[k]=Integer.parseInt(foundDate[k]);
				}
				//Evaluating based on year (descending year)
				if(currDate[2] > pivot[2]) {
					//Evaluating based on month (ascending month)
					if(currDate[0] > pivot[0])
						//Evaluating based on day (ascending day)
						if(currDate[1] > pivot[1])
							i++;
							DailyReport temp = this.dailyList.get(i);
							this.dailyList.set(i, this.dailyList.get(j));
							this.dailyList.set(j, temp);
				}
			}

			DailyReport temp = this.dailyList.get(i + 1);
			this.dailyList.set(i + 1, this.dailyList.get(highIdx));
			this.dailyList.set(highIdx, temp);
		}

		//MonthlyList Case
		if(isDailyList == 2){
			String foundDate[] = this.monthlyList.get(highIdx).getDate().split("/");
			for(int k=0; i<3; k++) {
				pivot[k]=Integer.parseInt(foundDate[k]);
			}
			i = lowIdx - 1;

			for(int j=lowIdx; j < highIdx; j++) {
				foundDate = this.monthlyList.get(highIdx).getDate().split("/");
				int[] currDate = new int[3];
				for(int k=0; i<3; k++) {
					currDate[k]=Integer.parseInt(foundDate[k]);
				}
				//Evaluating based on year
				if(currDate[2] > pivot[2]) {
					//Evaluating based on month
					if(currDate[0] > pivot[0])
						//Evaluating based on day
						if(currDate[1] > pivot[1])
							i++;
							MonthlyReport temp = this.monthlyList.get(i);
							this.monthlyList.set(i, this.monthlyList.get(j));
							this.monthlyList.set(j, temp);
				}
			}
			MonthlyReport temp = this.monthlyList.get(i + 1);
			this.monthlyList.set(i + 1, this.monthlyList.get(highIdx));
			this.monthlyList.set(highIdx, temp);
		}
		return i + 1;
	}

	/**
	 * @author: Arjun Bott
	 * [eqauls method]
	 * @param  obj  [the other object to check equality for]
	 * @return the equality of the two objects
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		return ((Storage) obj).dailyList.equals(this.dailyList) &&
				 ((Storage) obj).monthlyList.equals(this.monthlyList);
	}

	/**
	 * @author: Arjun Bott
	 * returns a string representation of this object
	 * @return a string representation of this object
	 */
	@Override
	public String toString() {
		String buffer = "[Storage]\t";
		buffer += this.dailyList.toString() + "\t";
		buffer += this.monthlyList.toString();
		return buffer;
	}

	/**
	 * [storage tester]
	 */
	public static void main(String[] args) {
		System.out.println("hello world");
	}
}
