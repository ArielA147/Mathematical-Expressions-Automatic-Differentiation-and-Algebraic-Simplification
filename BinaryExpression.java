import java.util.List;

/**
 * class represents the binary expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public abstract class BinaryExpression extends BaseExpression implements Expression {

    private Expression myLeftArgument;
    private Expression myRightArgument;

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(Expression leftArgument, Expression rightArgument) {
        this.myLeftArgument = leftArgument;
        this.myRightArgument = rightArgument;
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(double leftArgument, Expression rightArgument) {
        this.myLeftArgument = new Num(leftArgument);
        this.myRightArgument = rightArgument;
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(Expression leftArgument, double rightArgument) {
        this.myLeftArgument = leftArgument;
        this.myRightArgument = new Num(rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(double leftArgument, double rightArgument) {
        this.myLeftArgument = new Num(leftArgument);
        this.myRightArgument = new Num(rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(String leftArgument, Expression rightArgument) {
        this.myLeftArgument = new Var(leftArgument);
        this.myRightArgument = rightArgument;
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(Expression leftArgument, String rightArgument) {
        this.myLeftArgument = leftArgument;
        this.myRightArgument = new Var(rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(String leftArgument, String rightArgument) {
        this.myLeftArgument = new Var(leftArgument);
        this.myRightArgument = new Var(rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(double leftArgument, String rightArgument) {
        this.myLeftArgument = new Num(leftArgument);
        this.myRightArgument = new Var(rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public BinaryExpression(String leftArgument, double rightArgument) {
        this.myLeftArgument = new Var(leftArgument);
        this.myRightArgument = new Num(rightArgument);
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * @return the right argument of the expression.
     */
    public Expression getMyRightArgument() {
        return this.myRightArgument;
    }

    /**
     * @return the left argument of the expression.
     */
    public Expression getMyLeftArgument() {
        return this.myLeftArgument;
    }

    /**
     * @return the left argument as a string
     */
    public String toStringLeft() {
        return this.myLeftArgument.toString();
    }

    /**
     * @return the right argument as a string
     */
    public String toStringRight() {
        return this.myRightArgument.toString();
    }

    /**
     * @return list of the variables in the left and the right arguments.
     */
    public List<String> getVariables() {

        List<String> list1 = getMyLeftArgument().getVariables();
        List<String> list2 = getMyRightArgument().getVariables();

        for (int i = 0; i < list2.size(); i++) {
            if (!(list1.contains(list2.get(i)))) {
                list1.add(list2.get(i));
            }
        }
        return list1;
    }

    /**
     * the function checks if the expression is equal to the value.
     *
     * @param value double number
     * @param exp   expression object
     * @return true if equals else false
     */
    public boolean checkValue(Expression exp, double value) {
        return exp.toString().equals(Double.toString(value));
    }
}
