package myfirstmodule.implement;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IUser;
import com.mendix.systemwideinterfaces.core.UserAction;

import administration.proxies.Account;
import system.proxies.UserRole;

public class CustomLoginAction extends UserAction<ISession> {
  private final Map<String, ?> params;

  public CustomLoginAction(IContext context, java.util.Map<String, ? extends Object> params) {
    super(context);
    this.params = params;
  }

  @Override
  public ISession executeAction() throws Exception {
    // perform custom login steps using info received in param
    String userName = (String) params.get("userName");
    if (userName.equals("wengao")) {
      return Core.initializeSession(getOrCreateUser(userName), (String) params.get("currentSessionId"));
    }
    return null;
  }

  private IUser getOrCreateUser(String userName) throws CoreException, InterruptedException, ExecutionException {
    var context = getContext();
    var userList = Core
        .createXPathQuery(String.format("//%s[%s = $value]", Account.entityName, Account.MemberNames.Name))
        .setAmount(1)
        .setVariable("value", userName)
        .execute(context);
    Account account = null;
    if (userList.size() == 0) {
      account = new Account(context);
      account.setName(userName);
      account.setPassword("Mendix!12345");

      var result = Core
          .createXPathQuery(String.format("//%s", UserRole.entityName))
          .execute(context);
      List<UserRole> userList2 = result.stream()
          .map(m -> {
            try {
              return UserRole.load(context, m.getId());
            } catch (CoreException e) {
              e.printStackTrace();
            }
            return null;
          }).collect(java.util.stream.Collectors.toList());
      account.setUserRoles(userList2);
      account.commit();
    } else {
      account = Account.load(context, userList.get(0).getId());
    }
    return Core.getUser(getContext(), userName);
  }
}