/* Generated By:JJTree: Do not edit this line. ASTOperator.java */

public class ASTOperator extends SimpleNode {

  public boolean plus;

  public ASTOperator(int id) {
    super(id);
  }

  public ASTOperator(Postfix p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(PostfixVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}