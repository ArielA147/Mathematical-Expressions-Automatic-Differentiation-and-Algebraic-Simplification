import java.util.List;

/**
 * class represents the unary expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public abstract class UnaryExpression extends BaseExpression implements Expression {

    private Expression argument;

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public UnaryExpression(Expression number) {
        this.argument = number;
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public UnaryExpression(int number) {
        this.argument = new Num(number);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public UnaryExpression(Double number) {
        this.argument = new Num(number);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public UnaryExpression(String number) {
        this.argument = new Var(number);
    }

    /**
     * @return list of all the variables that are in the unary expression.
     */
    public List<String> getVariables() {
        return getArgument().getVariables();
    }

    /**
     * @return return the argument.
     */
    public Expression getArgument() {
        return argument;
    }

    /**
     * @return string of the argument
     */
    public String toString() {
        return this.argument.toString();
    }

}
