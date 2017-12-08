package nz.co.udenbrothers.yoobie.sql_stuff;



import java.util.ArrayList;
import java.util.List;



public class SQL {

    public static final int VERSION = 4;
    public static final String DBNAME = "clockDB";
/*
    public static <T extends Model> List<T> get(Class<T> classes, Filter<T> filter){
        Cur cur = new Cur(classes.getSimpleName());
        Gson gson = new Gson();

        List<T> newList = new ArrayList<>();
        List<T> listt = gson.fromJson(cur.cursorToString(), new ListOfJson<>(classes));

        for(T t: listt){
            if(filter.filt(t)){
                newList.add(t);
            }
        }
        return newList;
    }

    public static <T extends Model> List<T> get(Class<T> classes){
        Cur cur = new Cur(classes.getSimpleName());
        Gson gson = new Gson();

       return gson.fromJson(cur.cursorToString(), new ListOfJson<>(classes));
    }
*/
}
