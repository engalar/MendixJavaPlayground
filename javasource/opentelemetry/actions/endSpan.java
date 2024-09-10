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

public class endSpan extends CustomJavaAction<java.lang.Void>
{
	private java.lang.String spanId_;
	private opentelemetry.proxies.SpanStatus status_;

	public endSpan(IContext context, java.lang.String spanId_, java.lang.String status_)
	{
		super(context);
		this.spanId_ = spanId_;
		this.status_ = status_ == null ? null : opentelemetry.proxies.SpanStatus.valueOf(status_);
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		// BEGIN USER CODE
		int success = 0;
		if (this.status_ != null) {
			switch (status_) {
			case Success :
				success = 1;
				break;
			case Error:
				success = 2;
				break;
			default: 
				success = 0;
				break;
			}
			otel.endSpan(spanId_, success);
		}
		else {
			otel.endSpan(spanId_);
		}
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
		return "endSpan";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
