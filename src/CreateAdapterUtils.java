/**
 * Created by Administrator on 2017/4/20 0020.
 */
public interface CreateAdapterUtils {
    //生成baseAdapter
    String BASE_HEADER=
    "package %s;\n"+
        "\n"+
        "/**\n "+
        "* Created by CreateRecyclerAdapter.\n "+
        "*/\n" ;
    //包名
    String BASE_ADAPTER=
            BASE_HEADER +
   "public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder>{\n"+
   "    private List<TypeInterface> models;\n"+
   "    private Context context;\n"+
   "    private int currentPosition=-1;\n"+
   "    public BaseAdapter(List<TypeInterface> models,Context context) {\n"+
   "            this.models = models;\n"+
   "            this.context=context;\n"+
   "    }\n"+
        "\n"+
   "    @Override\n"+
   "    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {\n"+
   "             context=parent.getContext();\n"+
   "             View itemView= LayoutInflater.from(context).inflate(viewType,parent,false);\n"+
   "             if (currentPosition!=-1) {\n"+
   "                 return models.get(currentPosition).createViewHolder(itemView);\n"+
   "             }else {"+
   "                return null;\n"+
   "             }"+
   "    }\n"+
        "\n"+
   "   @Override\n"+
   "   public void onBindViewHolder(BaseViewHolder holder, int position) {\n"+
   "        holder.setViewsData(models.get(position),position,this,context);\n"+
   "   }\n"+
       "\n"+
   "   @Override\n"+
   "   public int getItemCount() {\n"+
   "        if (models==null||models.size()==0) {\n"+
   "             return 0;\n"+
   "        }else {\n"+
   "        return models.size();\n"+
   "        }\n"+
   "   }\n"+
            "\n"+
   "   @Override\n"+
   "    public int getItemViewType(int position) {\n"+
   "         currentPosition=position;\n"+
   "      return models.get(position).getLayout();\n"+
   "    }\n"+
   " }\n";
    //BaseViewHolder
    //包名
    String BASE_VIEW_HOLDER =
            BASE_HEADER+
    "public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {\n" +
    "    private SparseArray<View> views;\n" +
    "    private View itemView;\n" +
    "\n" +
    "    public BaseViewHolder(View itemView) {\n" +
    "        super(itemView);\n" +
    "        this.itemView = itemView;\n" +
    "        views=new SparseArray<>();\n" +
    "    }\n" +
    "    public View getViewById(@IdRes int resID){\n" +
    "        View view=views.get(resID);\n" +
    "        if (view==null){\n" +
                    "            view=itemView.findViewById(resID);\n" +
                    "            views.put(resID,view);\n" +
                    "        }\n" +
                    "        return view;\n" +
                    "    }\n" +
                    "    public void begin(T model, Context context){\n" +
                    "        findView();\n" +
                    "        setViewData(model,context);\n" +
                    "    }\n" +
                    "    public abstract void findView();\n" +
                    "    public abstract void setViewData(T model, Context context);\n" +
                    "}";
    //LayoutInterface
    String LAYOUT_INTERFACE=
            BASE_HEADER+
    "public interface TypeInterface {\n"+
    "      int getLayout();\n"+
    "      BaseViewHolder createViewHolder(View itemView);"+
    " }";
    //Model
    //包名，参数类型,参数类型,参数类型,参数类型,参数类型,参数类型,参数类型,参数类型,参数类型,参数类型,布局id
    String MODEL=
            BASE_HEADER+
     "public class %sModel implements TypeInterface{\n"+
     "      private %s m%s;\n"+
           "\n"+
     "      public %sModel(%s m%s) {\n"+
     "      this.m%s = m%s;\n"+
     "      }\n"+
           "\n"+
     "      public %s get%s(){\n"+
     "       return m%s;\n"+
     "      }\n"+
     "      @Override\n" +
     "      public int getLayout() {\n" +
     "        return %s;\n" +
     "       }"+
            "\n"+
     "      @Override\n"+
     "      public int getLayoutId(TypeInterface mTypeInterface){\n"+
     "      return mTypeInterface.layoutId(this);\n"+
     "      }\n"+
    "}";
    //ModelHolder
    //包名,参数类型,参数类型,参数类型,布局id,,参数类型
    String MODEL_HOLDER=
            BASE_HEADER+
                    "public class %s extends BaseViewHolder<%sModel> {\n" +
                    "    private TextView one;\n" +
                    "    public %s(View itemView) {\n" +
                    "        super(itemView);\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public void findView() {\n" +
                    "        one= (TextView) getViewById(%s);\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public void setViewData(%sModel model, Context context) {\n" +
                    "        one.setText(model.getContent());\n" +
                    "    }\n" +
                    "}";

}
