import java.util.*;
public class CalcVisitor extends Adapter {
  public Stack<Integer> stack = new Stack<Integer>();
  public Object visit(ASTFactor f, Object d) {
    stack.push(f.value);
    System.out.println("Pushed on " + f.value);
    return super.visit(f, d);
  }
  public Object visit(ASTOperator o, Object d) {
    System.out.println("Got an operator: " + (o.plus ? "+" : "-"));
    int op2 = stack.pop();
    int op1 = stack.pop();
    if (o.plus) {
      stack.push(op1+op2);
    } else {
      stack.push(op1-op2);
    }
    System.out.println("Stack top is now " + stack.peek());
    return super.visit(o, d);
  }
}
