
import java.util.List;
import java.util.Map;

/**
 * class represents the power expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Pow(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return list of the variables that in the power.
     */
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "(" + super.toStringLeft() + "^" + super.toStringRight() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(super.getMyLeftArgument().assign(var, expression),
                super.getMyRightArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Expression left = super.getMyLeftArgument();
        Expression right = super.getMyRightArgument();
        if ((left.evaluate(assignment) < 0.0
                && (right.evaluate(assignment) > 0.0 && right.evaluate(assignment) < 1.0))) {
            throw new Exception("there is no that power for the number");
        } else if (left.evaluate(assignment) == 0.0 && right.evaluate(assignment) == 0.0) {
            throw new Exception("there is no power of 0^0");
        } else {
            return Math.pow(super.getMyLeftArgument().evaluate(assignment),
                    super.getMyRightArgument().evaluate(assignment));
        }
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (super.getVariables().contains(var)) {

            Expression left = this;
            Expression right = new Plus(
                    new Mult(super.getMyLeftArgument().differentiate(var),
                            new Div(super.getMyRightArgument(), super.getMyLeftArgument())),
                    new Mult(super.getMyRightArgument().differentiate(var),
                            new Log(new Var("e"), super.getMyLeftArgument())));
            return new Mult(left, right);
        }
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression right = super.getMyRightArgument().simplify();
        Expression left = super.getMyLeftArgument().simplify();

        try {
            if (right.evaluate() == 0.0) {
                return new Num(1);
            }
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }
        try {
            if (left.simplify().evaluate() == 1.0) {
                return new Num(1.0);
            }
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }

        try {
            if (left.evaluate() == 0.0) {
                return new Num(0);
            }
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }

        try {
            if (right.evaluate() == 1.0) {
                return left;
            }
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }

        // if left and right are only numbers
        try {
            return new Num(this.evaluate());
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }
        return new Pow(left, right);

    }
}
