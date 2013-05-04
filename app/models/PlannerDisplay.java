/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.mvc.Result;

/**
 * @author Kaixi
 *
 */
@Entity
public class PlannerDisplay extends Model{

	
	@Id
    public Long id;
	
	public static Date centerDate;
	
	public static List<List<Task>> tasksToDisplay = new ArrayList<List<Task>>();
	
	public static Finder<Long,PlannerDisplay> find = new Finder(Long.class, PlannerDisplay.class);
	
	public static void create(PlannerDisplay dis) {
		  dis.save();
	}
	
	public static List<List<Task>> toDisplay(Long id){
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(find.ref(id).centerDate);
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
