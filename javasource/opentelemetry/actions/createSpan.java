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
import io.opentelemetry.api.trace.Span;
import opentelemetry.implement.SpanManager;

public class createSpan extends CustomJavaAction<java.lang.String>
{
	private final java.lang.String name_;
	private final java.lang.String parentSpanId_;

	public createSpan(
		IContext context,
		java.lang.String _name_,
		java.lang.String _parentSpanId_
	)
	{
		super(context);
		this.name_ = _name_;
		this.parentSpanId_ = _parentSpanId_;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		String spanId = null;
		String mfName = "";
		if (name_ == null) {
		  mfName = getContext().getActionStack().get(1).getActionName();
		}
		else {
			mfName = name_;
		}
		
		if (parentSpanId_ != null) {
			spanId = SpanManager.createSpan(mfName, parentSpanId_);
		}
		else {
			spanId = SpanManager.createSpan(mfName, null);
		}
		if (spanId == null) {
			throw new Exception("Could not create span"); 
		}
		return spanId;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "createSpan";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
