package myfirstmodule.implement;

import java.util.Map;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.UserAction;

public class CustomLoginAction extends UserAction<ISession> {
  private final Map<String, ?> params;

  public CustomLoginAction(IContext context, java.util.Map<String, ? extends Object> params) {
    super(context);
    this.params = params;
  }

  @Override
  public ISession executeAction() throws Exception {
    // perform custom login steps using info received in param
    return null;
  }
}

