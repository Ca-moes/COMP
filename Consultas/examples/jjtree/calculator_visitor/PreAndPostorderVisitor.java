public class PreAndPostorderVisitor extends CalculatorVisitorAdapter {
  public Object visit(ASTOperand operand, Object data) {
    // Do something before subtrees are visited
    Object obj = super.visit(operand, data);
    // Do something after subtrees are visited
    return obj;
  }
}
