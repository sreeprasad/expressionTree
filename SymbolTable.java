import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
public class SymbolTable{
	   private HashMap<String, Integer> map = new HashMap<String, Integer>();
	   public SymbolTable(){

	   }
	   public int get(String variable){
	   	if(map.containsKey(variable)){
	   		return map.get(variable);
	   	}else{
	   		map.put(variable,0);
	   	}
	   }

	   public void set(int value, String variable){
	   		map.put(variable,value);
	   }

	   public void print() {
            for (Iterator<Entry<String, Integer>> it =
                     map.entrySet().iterator();
                 it.hasNext();
                 ) {
                Entry<String,Integer> x = it.next();
                Platform.instance().outputLine((x.getKey()
                                                + " = "
                                                + x.getValue()));
            }
        }

         public void reset() {
            map.clear();
        }

}