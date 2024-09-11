// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package opentelemetry.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import ap.otel.otel;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class recordException extends CustomJavaAction<java.lang.Void>
{
	private java.lang.String spanId_;
	private IMendixObject Exception_;

	public recordException(IContext context, java.lang.String spanId_, IMendixObject Exception_)
	{
		super(context);
		this.spanId_ = spanId_;
		this.Exception_ = Exception_;
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		// BEGIN USER CODE
		otel.recordException(spanId_, Exception_.toString());
		return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "recordException";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}