import java.util.Map;
import java.util.TreeMap;

/**
 * test class which checks different expression.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */

public class ExpressionsTest {


    /**
     * main - main function for checking the assignment.
     *
     * @param args - arguments from user, not gonna get any.
     * @throws Exception - in case there is no way to calculate the expression.
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        System.out.println(e.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", (double) 2);
        assignment.put("y", 0.25);
        assignment.put("e", Math.E);
        try {
            System.out.println(e.evaluate(assignment));
        } catch (Exception exc) {
            System.out.println(exc.toString());
        }
        System.out.println(e.differentiate("x"));
        try {
            System.out.println(e.differentiate("x").evaluate(assignment));
        } catch (Exception exc) {
            System.out.println(exc.toString());
        }
        System.out.println(e.differentiate("x").simplify());
    }
}
