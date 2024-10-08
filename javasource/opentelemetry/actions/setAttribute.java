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
import opentelemetry.implement.SpanManager;

public class setAttribute extends CustomJavaAction<java.lang.Void>
{
	private final java.lang.String spanId_;
	private final java.lang.String attributeName_;
	private final java.lang.String attributeValue_;

	public setAttribute(
		IContext context,
		java.lang.String _spanId_,
		java.lang.String _attributeName_,
		java.lang.String _attributeValue_
	)
	{
		super(context);
		this.spanId_ = _spanId_;
		this.attributeName_ = _attributeName_;
		this.attributeValue_ = _attributeValue_;
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		// BEGIN USER CODE
		try {
			SpanManager.setAttribute(spanId_, attributeName_, attributeValue_);
		}
		catch (Exception e) {
			throw e;
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
		return "setAttribute";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
