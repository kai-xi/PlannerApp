/**
 * 
 */
package controllers;

import java.util.*;

import models.Center;
import models.Task;

import play.data.DynamicForm;
import play.mvc.Controller;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.helper.form;

/**
 * @author Kaixi
 *
 */
public class Planner extends Controller {
	
	static Form<Center> centerForm = Form.form(Center.class);
	
	public static Result toDisplay(){
		
		Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY,0);
    	cal.set(Calendar.MINUTE,0);
    	cal.set(Calendar.SECOND,0);
    	cal.set(Calendar.MILLISECOND,0);
    	Date center = cal.getTime();
		
		if(centerForm.hasErrors()) {
		    return badRequest(views.html.planner.render((Task.toDisplay(center)), centerForm));
		}
		else if(centerForm.bindFromRequest().get().centerDate == null) {
			return ok(views.html.planner.render((Task.toDisplay(center)), centerForm));
		}
		else {
			center = centerForm.bindFromRequest().get().centerDate;

		    return ok(views.html.planner.render((Task.toDisplay(center)), centerForm)); 
		}
		
		
	}
	
	// returns a form and what to make display
	
	// just creates some day thing
	
	

}
