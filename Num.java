import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * class represents the numbers.
 *
 * @version 10.05.2018
 * @author ariel alexi
 */
public class Num implements Expression {

    private double num;

    /**
     * constructor.
     *
     * @param number the name of the variable
     */
    public Num(double number) {
        this.num = number;
    }

    /**
     * constructor.
     *
     * @param number the name of the variable
     */
    public Num(int number) {
        this.num = (double) number;
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment - map object
     * @return the double type of the current string
     * @throws Exception - there was an ERROR in evaluate of Var
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }

    /**
     * @return Returns a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return new ArrayList();
    }

    /**
     * @return Returns a nice string representation of the expression.
     */
    public String toString() {
        return Double.toString(this.num);
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with the provided
     * expression (Does not modify the current expression).
     *
     * @param var        var object
     * @param expression expression object
     * @return the expression object
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * A convenience method.
     *
     * @return `evaluate(assignment)` method above, but uses an empty assignment.
     * @throws Exception - exception didnt secsseded to do evalute
     */
    public double evaluate() throws Exception {
        return this.evaluate(Collections.emptyMap());
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }
}
