import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

/**
 * class represents the variables.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Var implements Expression {

    private String myVar;

    /**
     * constructor.
     *
     * @param var the name of the variable
     */
    public Var(String var) {
        this.myVar = var;
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
        return assignment.get(this.myVar);
    }

    /**
     * @return Returns a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List varibles = new ArrayList();
        varibles.add(myVar);

        return varibles;
    }

    /**
     * @return Returns a nice string representation of the expression.
     */
    public String toString() {
        return this.myVar;
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
        if (this.myVar.equals(var)) {
            return expression;
        }
        return this;
    }

    /**
     * A convenience method.
     *
     * @return `evaluate(assignment)` method above, but uses an empty assignment.
     * @throws Exception - exception didnt secsseded to do evalute
     */
    public double evaluate() throws Exception {
        if (myVar.equals("e")) {
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("e", Math.E);
            return this.evaluate(assignment);
        }
        return this.evaluate(Collections.emptyMap());
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (myVar.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }
}



