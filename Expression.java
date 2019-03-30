import java.util.Map;
import java.util.List;

/**
 * Expression class.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided.
     *
     * @param assignment - map of variable values
     * @return the return the result of the evaluate
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method.
     *
     * @return `evaluate(assignment)` method above, but uses an empty assignment.
     * @throws Exception exception.
     */
    double evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * @param var        variable object
     * @param expression new expression
     * @return a new expression in which all occurrences of the variable var are replaced with the provided
     * expression (Does not modify the current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();


}
