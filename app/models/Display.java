/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import play.db.ebean.Model;
import play.mvc.Result;

/**
 * @author Kaixi
 *
 */
@Entity
public class Display extends Model{
	
	public static Date centerDate;
	
	public static List<List<Task>> tasksToDisplay = new ArrayList<List<Task>>();
	
	public static List<List<Task>> toDisplay(){
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(centerDate);
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
