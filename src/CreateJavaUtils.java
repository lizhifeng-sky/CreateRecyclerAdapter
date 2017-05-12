
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class CreateJavaUtils {
    public static void createBaseAdapter(String packageName, String path) throws IOException {
        String filePath = path + "BaseAdapter.java";
        File file = new File(filePath);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(CreateAdapterUtils.BASE_ADAPTER, packageName+".BaseAdapter");
        writer.write(content);
        writer.flush();
        writer.close();
    }
    public static void createBaseViewHolder(String packageName, String path) throws IOException {
        String dir = path + "holder/";
        String filePath = dir + "BaseViewHolder.java";
        File dirs = new File(dir);
        File file = new File(filePath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(CreateAdapterUtils.BASE_VIEW_HOLDER, packageName+".holder");
        writer.write(content);
        writer.flush();
        writer.close();
    }
    public static void createViewHolder(String packageName, String path,String viewHolderName,String typeName,String idName) throws IOException {
        String dir = path + "holder/";
        String filePath = dir + viewHolderName+".java";
        File dirs = new File(dir);
        File file = new File(filePath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(
                CreateAdapterUtils.MODEL_HOLDER,
                packageName+".holder",
                viewHolderName,
                typeName,
                viewHolderName,
                idName,
                typeName);
        writer.write(content);
        writer.flush();
        writer.close();
    }
    public static void createModelInterface(String packageName, String path) throws IOException {
        String dir = path + "type/";
        String filePath = dir +"TypeInterface.java";
        File dirs = new File(dir);
        File file = new File(filePath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(
                CreateAdapterUtils.LAYOUT_INTERFACE,
                packageName+".type");
        writer.write(content);
        writer.flush();
        writer.close();
    }
    public static void createModel(String packageName, String path,String typeName,String idName) throws IOException {
        String dir = path + "type/";
        String filePath = dir +typeName+"Model.java";
        File dirs = new File(dir);
        File file = new File(filePath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(CreateAdapterUtils.MODEL,
                packageName+".type",
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                typeName,
                idName);
        writer.write(content);
        writer.flush();
        writer.close();
    }
}
