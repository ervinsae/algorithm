package ervin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<String> data = new ArrayList<>();

        data.add("apple");
        data.add("message");
        data.add("per");

        data.stream().filter(new Predicate<String>() {

            @Override
            public boolean test(String t) {
                return t.contains("a");
            }
            
        }).forEach(new Consumer<String>() {

            @Override
            public void accept(String t) {
                System.out.println("filter--->"  + t);
            }
        });

        for (String str : data) {
            System.out.println(str);
        }

        Test testKt = new Test();
        testKt.returnTest();
        
    }
}
