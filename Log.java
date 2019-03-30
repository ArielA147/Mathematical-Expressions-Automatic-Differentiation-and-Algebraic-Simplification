import java.util.List;
import java.util.Map;

/**
 * * class represents the Log expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Log(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }


    /**
     * @return list of the variables that in the power.
     */
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "log(" + super.toStringLeft() + ", " + super.toStringRight() + ")";
    }

    /**
     * assigning expression in a var .
     *
     * @param var        variable object
     * @param expression new expression
     * @return new expression of the expression in log.
     */
    public Expression assign(String var, Expression expression) {
        return new Log(super.getMyLeftArgument().assign(var, expression),
                super.getMyRightArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        double right = super.getMyRightArgument().evaluate(assignment);
        double base = super.getMyLeftArgument().evaluate(assignment);

        if ((base <= 0.0) || (base == 1.0) || (right <= 0)) {
            throw new Exception("the base of the log is not possible : " + this.toString());
        } else {
            return Math.log(right) / Math.log(base);
        }
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {

        if (super.getMyLeftArgument().getVariables().contains(var)) {
            return new Div(new Log("e", super.getMyRightArgument()),
                    new Log("e", super.getMyLeftArgument().differentiate(var)));
        }
        return new Div(super.getMyRightArgument().differentiate(var),
                new Mult(super.getMyRightArgument(), new Log(new Var("e"), super.getMyLeftArgument())));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression left = super.getMyLeftArgument().simplify();
        Expression right = super.getMyRightArgument().simplify();

        if (left.toString().equals(right.toString())) {
            return new Num(1.0);
        }

        if (checkValue(right, 1.0)) {
            return new Num(0);
        }

        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Log(left.simplify(), right.simplify());
        }
    }
}
