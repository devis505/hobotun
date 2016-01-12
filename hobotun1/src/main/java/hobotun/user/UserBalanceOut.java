package hobotun.user;

import hobotun.core.UserSession;
import hobotun.db.user.table.OutUserBalanceTbl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "userBalanceOut")
@ViewScoped
public class UserBalanceOut {

    private List<OutUserBalanceTbl> outBalance;

    @PostConstruct
    public void init() {
	outBalance = UserSession.getInstance().getUser().getUserOutBalance();
    }

    public List<OutUserBalanceTbl> getOutBalance() {
	return outBalance;
    }

    public void setOutBalance(List<OutUserBalanceTbl> outBalance) {
	this.outBalance = outBalance;
    }

}
