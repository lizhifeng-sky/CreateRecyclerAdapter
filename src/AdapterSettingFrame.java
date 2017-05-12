import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/19 0019.
 */
public class AdapterSettingFrame {
    private Frame frame;
    private TextField textField_holder_title;//
    private TextField textField_holder_content;
    private TextField textField_parameter_title;//参数类型
    private TextField textField_parameter_content;
    private TextField textField_layout_id_title;
    private TextField textField_layout_id_content;
    private Button button_confirm_create;
    private Dialog dialog_check_input;
    private Button okButton;
    private Label label;
    private AnActionEvent anActionEvent;

    public AdapterSettingFrame(AnActionEvent e) {
        this.anActionEvent=e;
        initFrame();
    }

    private void initFrame() {
        frame = new Frame("生成adapter、viewHolder");
        frame.setBounds(300, 100, 600, 300);
        frame.setLayout(new FlowLayout());
        //输入holder
        textField_holder_title = new TextField(30);
        textField_holder_title.setText("输入holderName");
        textField_holder_content = new TextField(30);
        //输入model参数类型
        textField_parameter_title = new TextField(30);
        textField_parameter_title.setText("请输入Model参数类型");
        textField_parameter_content = new TextField(30);
        //输入布局id
        textField_layout_id_title = new TextField(30);
        textField_layout_id_title.setText("请输入布局id");
        textField_layout_id_content = new TextField(30);
        //确认生成
        button_confirm_create = new Button("开始生成");
        //输入检测 dialog
        dialog_check_input = new Dialog(frame, "提示", true);
        dialog_check_input.setBounds(400, 200, 250, 150);
        dialog_check_input.setLayout(new FlowLayout());
        CenterUtils.setDialogContentInParent(dialog_check_input);
        label = new Label();
        okButton = new Button("确定");

        dialog_check_input.add(label);
        dialog_check_input.add(okButton);
        //添加
        frame.add(textField_holder_title);
        textField_holder_title.setEditable(false);
        frame.add(textField_holder_content);

        frame.add(textField_parameter_title);
        textField_parameter_title.setEditable(false);
        frame.add(textField_parameter_content);

        frame.add(textField_layout_id_title);
        textField_layout_id_title.setEditable(false);
        frame.add(textField_layout_id_content);

        frame.add(button_confirm_create);

        frame.setVisible(true);
        CenterUtils.setFrameContentInParent(frame);
        setListener();

    }

    private void setListener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
            }
        });
        button_confirm_create.addActionListener(e -> {
            String holderName=null;
            if (textField_holder_content != null
                    && textField_holder_content.getText() != null
                    && checkParameter(textField_holder_content.getText().trim())) {
                holderName=textField_holder_content.getText();
            } else {
                label.setText("请输入合法的HolderName");
                dialog_check_input.setVisible(true);
            }
            String modelName=null;
            if (textField_parameter_content != null
                    && textField_parameter_content.getText() != null
                    && checkParameter(textField_parameter_content.getText().trim())){
                modelName=textField_parameter_content.getText();
            }else {
                label.setText("请输入合法的ModelName");
                dialog_check_input.setVisible(true);
            }
            String layout_id=null;
            if (textField_layout_id_content != null
                    && textField_layout_id_content.getText() != null
                    && checkParameter(textField_layout_id_content.getText().trim())){
                layout_id=textField_layout_id_content.getText();
            }else {
                label.setText("请输入合法的Id");
                dialog_check_input.setVisible(true);
            }
            startCreate(holderName,modelName,layout_id);
        });
        okButton.addActionListener(e -> dialog_check_input.setVisible(false));
    }

    private void startCreate(String holderName, String modelName, String layout_id) {
        Editor editor=anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (editor==null){
            return;
        }
        String content = editor.getDocument().getText();
        String currentPath = getCurrentPath(anActionEvent);
        String truePath=getCurrentPath(currentPath);
        if (!truePath.contains("adapter")) {
            Messages.showMessageDialog("Your Adapter should in package 'adapter'.", "Error", Messages.getErrorIcon());
            return;
        }
        try {
            String packageName=getPackageName(truePath);
            CreateJavaUtils.createBaseAdapter(packageName,truePath);
            CreateJavaUtils.createBaseViewHolder(packageName,truePath);
            CreateJavaUtils.createModelInterface(packageName,truePath);
            CreateJavaUtils.createViewHolder(packageName,truePath,
                    textField_holder_content.getText(),
                    textField_parameter_content.getText(),
                    textField_layout_id_content.getText());

            CreateJavaUtils.createModel(packageName,truePath,
                    textField_parameter_content.getText(),
                    textField_layout_id_content.getText());
            refreshProject(anActionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getPackageName(String path) {
        String[] strings = path.split("/");
        StringBuilder packageName = new StringBuilder();
        boolean packageBegin = false;
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            if ((string.equals("com")) || (string.equals("org")) || (string.equals("cn")) || (string.equals("pw"))) {
                packageBegin = true;
            }
            if (packageBegin) {
                packageName.append(string);
                if (i != strings.length - 1) {
                    packageName.append(".");
                }
            }
        }
        return packageName.toString();
    }
    private String getCurrentPath(String path){
        String[] strings=path.split("/");
        StringBuilder newPath=new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i!=strings.length-1){
                newPath.append(strings[i]).append("/");
            }
        }
        return newPath.toString();
    }

    private boolean checkParameter(String content) {
        char[] chars = content.toCharArray();
        return 64 < chars[0] && chars[0] < 91;
    }
    private String getCurrentPath(AnActionEvent e) {
        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (currentFile != null) {
            return currentFile.getPath();
        }
        return null;
    }
    private void refreshProject(AnActionEvent e) {
        e.getProject().getBaseDir().refresh(false, true);
        frame.setVisible(false);
    }
}
