import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LineThemUp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        List<String> order = IntStream.range(0, s.nextInt())
                .mapToObj(i -> s.next())
                .collect(Collectors.toList());

        boolean desc = true;
        boolean asc = true;
        for (int i = 0; i < order.size()-1; i++) {

            int compare = order.get(i).compareTo(order.get(i+1));

            if(compare > 0)
                asc = false;
            if(compare < 0)
                desc = false;

            if(!asc && !desc)
                break;
        }

        if(asc)
            System.out.println("INCREASING");
        else if(desc)
            System.out.println("DECREASING");
        else
            System.out.println("NEITHER");
    }
}
