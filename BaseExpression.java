import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class represents the bases expressions.
 *
 * @version 10.05.2018
 * @author ariel alexi
 */
abstract class BaseExpression implements Expression {

    private Expression myArgument;
    private List<String> variblesList = new ArrayList<>();

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
     * @return getter of the list
     */
    public List getVariblesList() {
        return variblesList;
    }

}
