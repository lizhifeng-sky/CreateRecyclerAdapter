import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class CreateAdapter extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        new AdapterSettingFrame(e);
    }
}
