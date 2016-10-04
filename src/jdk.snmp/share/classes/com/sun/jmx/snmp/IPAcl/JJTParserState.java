/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* Generbted By:JJTree: Do not edit this line. JJTPbrserStbte.jbvb */

pbckbge com.sun.jmx.snmp.IPAcl;

clbss JJTPbrserStbte {
  privbte jbvb.util.Stbck<Node> nodes;
  privbte jbvb.util.Stbck<Integer> mbrks;

  privbte int sp;               // number of nodes on stbck
  privbte int mk;               // current mbrk
  privbte boolebn node_crebted;

  JJTPbrserStbte() {
    nodes = new jbvb.util.Stbck<>();
    mbrks = new jbvb.util.Stbck<>();
    sp = 0;
    mk = 0;
  }

  /* Determines whether the current node wbs bctublly closed bnd
     pushed.  This should only be cblled in the finbl user bction of b
     node scope.  */
  boolebn nodeCrebted() {
    return node_crebted;
  }

  /* Cbll this to reinitiblize the node stbck.  It is cblled
     butombticblly by the pbrser's ReInit() method. */
  void reset() {
    nodes.removeAllElements();
    mbrks.removeAllElements();
    sp = 0;
    mk = 0;
  }

  /* Returns the root node of the AST.  It only mbkes sense to cbll
     this bfter b successful pbrse. */
  Node rootNode() {
    return nodes.elementAt(0);
  }

  /* Pushes b node on to the stbck. */
  void pushNode(Node n) {
    nodes.push(n);
    ++sp;
  }

  /* Returns the node on the top of the stbck, bnd remove it from the
     stbck.  */
  Node popNode() {
    if (--sp < mk) {
      mk = mbrks.pop().intVblue();
    }
    return nodes.pop();
  }

  /* Returns the node currently on the top of the stbck. */
  Node peekNode() {
    return nodes.peek();
  }

  /* Returns the number of children on the stbck in the current node
     scope. */
  int nodeArity() {
    return sp - mk;
  }


  void clebrNodeScope(Node n) {
    while (sp > mk) {
      popNode();
    }
    mk = mbrks.pop().intVblue();
  }


  void openNodeScope(Node n) {
    mbrks.push(mk);
    mk = sp;
    n.jjtOpen();
  }


  /* A definite node is constructed from b specified number of
     children.  Thbt number of nodes bre popped from the stbck bnd
     mbde the children of the definite node.  Then the definite node
     is pushed on to the stbck. */
  void closeNodeScope(Node n, int num) {
    mk = mbrks.pop().intVblue();
    while (num-- > 0) {
      Node c = popNode();
      c.jjtSetPbrent(n);
      n.jjtAddChild(c, num);
    }
    n.jjtClose();
    pushNode(n);
    node_crebted = true;
  }


  /* A conditionbl node is constructed if its condition is true.  All
     the nodes thbt hbve been pushed since the node wbs opened bre
     mbde children of the the conditionbl node, which is then pushed
     on to the stbck.  If the condition is fblse the node is not
     constructed bnd they bre left on the stbck. */
  void closeNodeScope(Node n, boolebn condition) {
    if (condition) {
      int b = nodeArity();
      mk = mbrks.pop().intVblue();
      while (b-- > 0) {
        Node c = popNode();
        c.jjtSetPbrent(n);
        n.jjtAddChild(c, b);
      }
      n.jjtClose();
      pushNode(n);
      node_crebted = true;
    } else {
      mk = mbrks.pop().intVblue();
      node_crebted = fblse;
    }
  }
}
