package controllers;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import models.Task;
import play.*;
import play.data.Form;
import play.mvc.*;

/*
 * views.html.index.render(String message) is a standard Java method that renders the
 * template defined in app/views/index.scala.html
 */
import views.html.*;

public class Application extends Controller {
	/*
	 * The type is Form<Task> since it is a form generating a Task.
	 */
	static Form<Task> taskForm = Form.form(Task.class);
	
	
	// controllers.Application.index() is an action method
    public static Result index() {
    	// returns a Result that represents the web server's HTTP response
    	// The action returns a 200 OK response with the HTML response body as a parameter.
        //return ok(index.render("Hello World."));
    	
    	/*
    	 * Here, we implement an index() action method that returns a 303 See Other response
    	 * instead of a 200 OK response.
    	 * the reverse router (controllers.routes.Application.tasks()) returns another URL to request.
    	 */
    	return redirect(routes.Application.tasks());
    }
    
    /*
     * Built-in TODO result returns a 501 Not Implemented response.
     * Can use as placeholder when you're not ready to write the action implementation.
     */
    public static Result tasks()	{
    	/*
    	 * index.scala.html accepts two params: a list of Tasks and a Form<Task> that
    	 * generates a Task.
    	 * Task.all() has return type List<Task> and taskForm is a form that generates
    	 * a Task.
    	 */
    	return ok(index.render(Task.all(),taskForm));
    }
    
    public static Result newTask()	{
    	/*
    	 * taskForm.bindFromRequest() creates a new form filled with the request data
    	 */
    	Form<Task> filledForm = taskForm.bindFromRequest();
    	if (filledForm.hasErrors())	{
    		return badRequest(index.render(Task.all(),filledForm));
    	}
    	else	{
    		/*
    		 * filledForm.get() returns a Task
    		 * and Task.create() accepts a Task parameter
    		 */
    		Task.create(filledForm.get());
    	}
    	// After you create a task, redirect back to the tasks list view
    	return redirect(routes.Application.tasks());
    }
    
    public static Result deleteTask(Long id)	{
    	Task.delete(id);
    	// After you delete the task specified by the id, redirect back to the tasks list view
    	return redirect(routes.Application.tasks());
    }
    
    public static Result week(){
    	
    	List<List<Task>> tasksThisWeek = new ArrayList<List<Task>>();
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY,0);
    	cal.set(Calendar.MINUTE,0);
    	cal.set(Calendar.SECOND,0);
    	cal.set(Calendar.MILLISECOND,0);
    	
    	cal.add(Calendar.DATE, -1);
    	Date yesterday = cal.getTime();
    	tasksThisWeek.add(Task.findTasksOnDay(yesterday));
    	
    	cal.add(Calendar.DATE, 1);
    	Date today = cal.getTime();
    	tasksThisWeek.add(Task.findTasksOnDay(today));
    	
    	cal.add(Calendar.DATE, 1);
    	Date tomorrow = cal.getTime();
    	tasksThisWeek.add(Task.findTasksOnDay(tomorrow));
    	
    	cal.add(Calendar.DATE, 1);
    	Date day3 = cal.getTime();
    	tasksThisWeek.add(Task.findTasksOnDay(day3));
    	
    	return ok(views.html.planner.render(tasksThisWeek));
    }
  
}
