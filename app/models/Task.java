/**
 * 
 */
package models;

import java.util.*;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * @author Kaixi
 *
 */
@Entity
public class Task extends Model {

	 	@Id
	    public Long id;
	    
	    @Constraints.Required
	    public String title;
	    
	    @Constraints.Required
	    public String taskType;
	    
	    
	    @Constraints.Required
//	    @Formats.DateTime(pattern="MM/dd/yyyy")
	    public Date startDay;
	    
	    @Constraints.Required
	    @Formats.DateTime(pattern="MM/dd/yy hh:mm")
	    public Date endTime;
	    
	    public String place;
	    
	    public String notes;
	    
	    public static Finder<Long,Task> find = new Finder(Long.class, Task.class);
	  
	  
	    public static List<Task> all() {
		  return find.all();
		}

		public static void create(Task task) {
		  task.save();
		}

		public static void delete(Long id) {
		  find.ref(id).delete();
		}
		
//		public static ArrayList<Task> nextThree(){ //@param: Task.all
//			
//			DateTime taskTime = new DateTime(task.startTime);
//			DateTime today = new DateTime();
//			
//			Days d = Days.daysBetween(today, taskTime);
//			int days = d.getDays();
//			
//			if (days >= 0 && days <3){
//				//add it to an arraylist
//			}
//			
//			//or we could compare how many days are in between
//			
//			// if today is monday and there are <7 days in between, we're golden.
//			// tues, 6.
//			// wed, 5. etc.
//			
//		}
		
		public static boolean isToday(Task task){
			
			DateTime taskTime = new DateTime(task.startDay);
			LocalDate taskDate = taskTime.toLocalDate();
		
			//find out the date today is
			Date todayD = new Date();
			DateTime today = new DateTime(todayD);
			LocalDate todaysDate = today.toLocalDate();
			
			if (todaysDate.equals(taskDate)){
				return true;
			}
			return false;
		}
		
	    public static List<Task> findTasksOnDay(Date date) {
	        return find.where()
	                 .eq("startDay", date)
	            .findList();
	    }
	    
	    public static List<List<Task>> toDisplay(Date day){
	    	
	    	List<List<Task>> tasksToDisplay = new ArrayList<List<Task>>();
	    	
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(day);
	    	cal.set(Calendar.HOUR_OF_DAY,0);
	    	cal.set(Calendar.MINUTE,0);
	    	cal.set(Calendar.SECOND,0);
	    	cal.set(Calendar.MILLISECOND,0);
	    	
	    	cal.add(Calendar.DATE, -1);
	    	Date before = cal.getTime();
	    	tasksToDisplay.add(Task.findTasksOnDay(before));
	    	
	    	cal.add(Calendar.DATE, 1);
	    	Date center = cal.getTime();
	    	tasksToDisplay.add(Task.findTasksOnDay(center));
	    	
	    	cal.add(Calendar.DATE, 1);
	    	Date day2 = cal.getTime();
	    	tasksToDisplay.add(Task.findTasksOnDay(day2));
	    	
	    	cal.add(Calendar.DATE, 1);
	    	Date day3 = cal.getTime();
	    	tasksToDisplay.add(Task.findTasksOnDay(day3));
	    	
	    	return tasksToDisplay;
	    }
	    
	    
	
}
