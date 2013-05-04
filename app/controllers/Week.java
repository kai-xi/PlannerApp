/**
 * 
 */
package controllers;

import models.Task;
import play.data.Form;
import play.mvc.Controller;

/**
 * @author Kaixi
 *
 */
public class Week extends Controller{
	
	static Form<Task> taskForm = Form.form(Task.class);
	
	
	
	// maybe an array list of tasks for each day?

}
