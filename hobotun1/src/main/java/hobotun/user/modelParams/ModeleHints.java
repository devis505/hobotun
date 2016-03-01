package hobotun.user.modelParams;

import java.util.ArrayList;
import java.util.List;

import hobotun.util.SystemParams;

public class ModeleHints {
	
	private List<String> hint = new ArrayList<>();
	
	public ModeleHints () {
		for (int i = 2; i < 26; i++) {
			hint.add(SystemParams.getInstance().getParam(i));
		}
	}

	public List<String> getHint() {
		return hint;
	}
	
}
