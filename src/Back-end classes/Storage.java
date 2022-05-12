//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//public class Storage {
//
//	/**
//	 * Arjun's part
//	 */
//
//	/**
//	 * @author: Carlos Rodriguez
//	 * First sort to use, based on date
//	 * on both daily and monthly lists.
//	 */
//	public void initialSortByDate() {
//		sortByDate(1);
//		sortByDate(2);
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 * Helper method to generate a random pivot for
//	 * the sorting algorithms in order to decrease
//	 * the chances of approaching O(n^2) due to a
//	 * bad partioning. Changes the randomized pivot
//	 * position with the last element of the lists.
//	 *
//	 * @param lowIdx: lowest index to consider for
//	 * the partition(inclusive).
//	 * @param highIdx: highest index to consider
//	 * for the partioning(inclusive).
//	 * @param: isDailyList: does the randomization
//	 * for the dailyList if 1, does it for the
//	 * monthlyList if 2. */
//	private void randomPivot(int lowIdx, int highIdx, int isDailyList) {
//		Random rnd = new Random();
//		int pivotIdx = rnd.nextInt(highIdx-lowIdx) + lowIdx;
//		switch(isDailyList) {
//			case 1:
//				//Switching whatever is in pivotIdx with last element
//				//to consider our pivot as new last element.
//				DailyReport dTemp = this.dailyList.get(pivotIdx);
//				this.dailyList.set(pivotIdx, this.dailyList.get(highIdx));
//				this.dailyList.set(highIdx, dTemp);
//				break;
//			case 2:
//				MonthlyReport mTemp = this.monthlyList.get(pivotIdx);
//				this.monthlyList.set(pivotIdx, this.monthlyList.get(highIdx));
//				this.monthlyList.set(highIdx, mTemp);
//				break;
//			default:
//				System.out.println("Error, list not identified!");
//		}
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 *
//	 * @param isDailyList: int to define if the list
//	 * referred to is the daily list if 1, the monthly
//	 * list if 2.
//	 */
//	public void sortByStations(int isDailyList) {
//		sortStation(0, this.dailyList.size()-1, isDailyList);
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 *
//	 * @param isDailyList: int to define if the list
//	 * referred to is the daily list if 1, the monthly
//	 * list if 2.
//	 */
//	public void sortByDate(int isDailyList) {
//		sortDate(0, this.dailyList.size()-1, isDailyList);
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 *
//	 * Helper method takes care of partitioning either the
//	 * monthly list or the daily list. Invokes partitioning
//	 * based on station recursively.
//	 *
//	 *
//	 * @param lowIdx: lowest index to start partition.
//	 * @param highIdx: highest index to partition at.
//	 * @param isDailyList: int to define if the list
//	 * referred to is the daily list if 1, the monthly
//	 * list if 2.
//	 */
//	private void sortStation(int lowIdx, int highIdx, int isDailyList) {
//		if(lowIdx < highIdx) {
//			int partitionIdx = partitionByStation(lowIdx, highIdx, isDailyList);
//			sortStation(lowIdx, partitionIdx-1, isDailyList);
//			sortStation(partitionIdx+1, highIdx, isDailyList);
//		}
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 *
//	 * Helper method takes care of partitioning either the
//	 * monthly list or the daily list. Invokes partitioning
//	 * based on date recursively.
//	 *
//	 *
//	 * @param lowIdx: lowest index to start partition.
//	 * @param highIdx: highest index to partition at.
//	 * @param isDailyList: int to define if the list
//	 * referred to is the daily list if 1, the monthly
//	 * list if 2.
//	 */
//	private void sortDate(int lowIdx, int highIdx, int isDailyList) {
//		if(lowIdx < highIdx) {
//			int partitionIdx = partitionByDate(lowIdx, highIdx, isDailyList);
//			sortDate(lowIdx, partitionIdx-1, isDailyList);
//			sortDate(partitionIdx+1, highIdx, isDailyList);
//		}
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 *
//	 * Evaluates the first char of a Report's station name
//	 * as pivot, and sorts all the reports in a list based
//	 * on partitions (from lowest index entered to highest)
//	 * comparing the values with the chosen pivot.
//	 *
//	 * @param lowIdx: lowest index to start the partition.
//	 * @param highIdx: highest index to start the partition.
//	 * @param isDailyList: to sort the dailyList if 1,
//	 * monthlyList if 2.
//	 * @return: Highest index reached by the partition.
//	 */
//	private int partitionByStation(int lowIdx, int highIdx, int isDailyList) {
//		randomPivot(lowIdx, highIdx, isDailyList);
//		int pivot = -1;
//		int i = -1;
//
//		//DailyList Case:
//		if(isDailyList == 1) {
//			pivot = this.dailyList.get(highIdx).getStationName().charAt(0);
//			i = lowIdx - 1;
//
//			for(int j=lowIdx; j < highIdx; j++) {
//				if(this.dailyList.get(j).getStationName().charAt(0) < pivot) {
//					i++;
//					DailyReport temp = this.dailyList.get(i);
//					this.dailyList.set(i, this.dailyList.get(j));
//					this.dailyList.set(j, temp);
//				}
//			}
//
//			DailyReport temp = this.dailyList.get(i + 1);
//			this.dailyList.set(i + 1, this.dailyList.get(highIdx));
//			this.dailyList.set(highIdx, temp);
//		}
//		//MonthlyList Case
//		if(isDailyList == 2){
//			pivot = this.monthlyList.get(highIdx).getStationName().charAt(0);
//			i = lowIdx - 1;
//
//			for(int j=lowIdx; j < highIdx; j++) {
//				if(this.monthlyList.get(j).getStationName().charAt(0) < pivot) {
//					i++;
//					MonthlyReport temp = this.monthlyList.get(i);
//					this.monthlyList.set(i, this.monthlyList.get(j));
//					this.monthlyList.set(j, temp);
//				}
//			}
//
//			MonthlyReport temp = this.monthlyList.get(i + 1);
//			this.monthlyList.set(i + 1, this.monthlyList.get(highIdx));
//			this.monthlyList.set(highIdx, temp);
//		}
//		return i + 1;
//	}
//
//	/**
//	 * @author: Carlos Rodriguez
//	 *
//	 * Evaluates the date of a Report as pivot, sorts all
//	 * the reports in a list basedon partitions (from
//	 * lowest index entered to highest) comparing the values
//	 * (year, month, day order) with the chosen pivot.
//	 *
//	 * @param lowIdx: lowest index to start the partition.
//	 * @param highIdx: highest index to start the partition.
//	 * @param isDailyList: to sort the dailyList if 1,
//	 * monthlyList if 2.
//	 * @return: Highest index reached by the partition.
//	 */
//	private int partitionByDate(int lowIdx, int highIdx, int isDailyList) {
//		randomPivot(lowIdx, highIdx, isDailyList);
//		int[] pivot = new int[3];
//		int i = -1;
//
//		//DailyList Case:
//		if(isDailyList == 1) {
//			String foundDate[] = this.dailyList.get(highIdx).getData().split("/");
//			for(int k=0; i<3; k++) {
//				pivot[k]=Integer.parseInt(foundDate[k]);
//			}
//			i = lowIdx - 1;
//
//			for(int j=lowIdx; j < highIdx; j++) {
//				foundDate = this.dailyList.get(highIdx).getData().split("/");
//				int[] currDate = new int[3];
//				for(int k=0; i<3; k++) {
//					currDate[k]=Integer.parseInt(foundDate[k]);
//				}
//				//Evaluating based on year (descending year)
//				if(currDate[2] > pivot[2]) {
//					//Evaluating based on month (ascending month)
//					if(currDate[0] > pivot[0])
//						//Evaluating based on day (ascending day)
//						if(currDate[1] > pivot[1])
//							i++;
//							DailyReport temp = this.dailyList.get(i);
//							this.dailyList.set(i, this.dailyList.get(j));
//							this.dailyList.set(j, temp);
//				}
//			}
//
//			DailyReport temp = this.dailyList.get(i + 1);
//			this.dailyList.set(i + 1, this.dailyList.get(highIdx));
//			this.dailyList.set(highIdx, temp);
//		}
//
//		//MonthlyList Case
//		if(isDailyList == 2){
//			String foundDate[] = this.monthlyList.get(highIdx).getData().split("/");
//			for(int k=0; i<3; k++) {
//				pivot[k]=Integer.parseInt(foundDate[k]);
//			}
//			i = lowIdx - 1;
//
//			for(int j=lowIdx; j < highIdx; j++) {
//				foundDate = this.monthlyList.get(highIdx).getData().split("/");
//				int[] currDate = new int[3];
//				for(int k=0; i<3; k++) {
//					currDate[k]=Integer.parseInt(foundDate[k]);
//				}
//				//Evaluating based on year
//				if(currDate[2] > pivot[2]) {
//					//Evaluating based on month
//					if(currDate[0] > pivot[0])
//						//Evaluating based on day
//						if(currDate[1] > pivot[1])
//							i++;
//							MonthlyReport temp = this.monthlyList.get(i);
//							this.monthlyList.set(i, this.monthlyList.get(j));
//							this.monthlyList.set(j, temp);
//				}
//			}
//			MonthlyReport temp = this.monthlyList.get(i + 1);
//			this.monthlyList.set(i + 1, this.monthlyList.get(highIdx));
//			this.monthlyList.set(highIdx, temp);
//		}
//		return i + 1;
//	}
//}
//