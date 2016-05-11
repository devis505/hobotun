package hobotun.user.modelParams;

import java.util.ArrayList;
import java.util.List;

import hobotun.util.SystemParams;

public class ModeleHints {
	
	private List<String> hint = new ArrayList<>();
	
	public ModeleHints () {
		hint = SystemParams.getInstance().getAllHintsForModelUpload();
	}

	public List<String> getHint() {
		return hint;
	}
	
}
