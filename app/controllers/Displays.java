/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.PlannerDisplay;
import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * @author Kaixi
 *
 */
public class Displays extends Controller{
	
	static Form<PlannerDisplay> displayForm = Form.form(PlannerDisplay.class);
	
	public static Result changeDisplay() {
		Form<PlannerDisplay> filledForm = displayForm.bindFromRequest();
    	if (filledForm.hasErrors())	{
    		return badRequest(views.html.changedisplay.render(filledForm));
    	}
    	else	{
    		/*
    		 * filledForm.get() returns a Task
    		 * and Task.create() accepts a Task parameter
    		 */
    		PlannerDisplay.create(filledForm.get());
    	}
		return ok(views.html.changedisplay.render(displayForm));
    }
	
	public static Result planner(Long id) {
		return ok(views.html.planner.render(PlannerDisplay.toDisplay(id)));
	}

}
