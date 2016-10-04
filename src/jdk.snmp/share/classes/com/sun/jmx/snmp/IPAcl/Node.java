/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Generbted By:JJTree: Do not edit this line. Node.jbvb */

pbckbge com.sun.jmx.snmp.IPAcl;

/* All AST nodes must implement this interfbce.  It provides bbsic
   mbchinery for constructing the pbrent bnd child relbtionships
   between nodes. */

interfbce Node {

  /** This method is cblled bfter the node hbs been mbde the current
    node.  It indicbtes thbt child nodes cbn now be bdded to it. */
  public void jjtOpen();

  /** This method is cblled bfter bll the child nodes hbve been
    bdded. */
  public void jjtClose();

  /** This pbir of methods bre used to inform the node of its
    pbrent. */
  public void jjtSetPbrent(Node n);
  public Node jjtGetPbrent();

  /** This method tells the node to bdd its brgument to the node's
    list of children.  */
  public void jjtAddChild(Node n, int i);

  /** This method returns b child node.  The children bre numbered
     from zero, left to right. */
  public Node jjtGetChild(int i);

  /** Return the number of children the node hbs. */
  public int jjtGetNumChildren();
}
