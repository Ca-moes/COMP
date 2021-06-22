public class NoMultiVisitor implements CalculatorVisitor {
  public Object visit(SimpleNode node, Object data) {
    System.out.println("SimpleNode.id = " + node.id);
    System.out.println("Operand? " + (node.id == CalculatorTreeConstants.JJTOPERAND));
    System.out.println("It's an " + CalculatorTreeConstants.jjtNodeName[node.id]);
    return node.childrenAccept(this, data);
  }
}
