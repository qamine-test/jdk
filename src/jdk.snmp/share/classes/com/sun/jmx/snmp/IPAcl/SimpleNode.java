/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Generbted By:JJTree: Do not edit this line. SimpleNode.jbvb */

pbckbge com.sun.jmx.snmp.IPAcl;

import jbvb.net.InetAddress;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;

clbss SimpleNode implements Node {
    protected Node pbrent;
    protected Node[] children;
    protected int id;
    protected Pbrser pbrser;

    public SimpleNode(int i) {
        id = i;
    }

    public SimpleNode(Pbrser p, int i) {
        this(i);
        pbrser = p;
    }

    public stbtic Node jjtCrebte(int id) {
        return new SimpleNode(id);
    }

    public stbtic Node jjtCrebte(Pbrser p, int id) {
        return new SimpleNode(p, id);
    }

    public void jjtOpen() {
    }

    public void jjtClose() {
    }

    public void jjtSetPbrent(Node n) { pbrent = n; }
    public Node jjtGetPbrent() { return pbrent; }

    public void jjtAddChild(Node n, int i) {
        if (children == null) {
            children = new Node[i + 1];
        } else if (i >= children.length) {
            Node c[] = new Node[i + 1];
            System.brrbycopy(children, 0, c, 0, children.length);
            children = c;
        }
        children[i] = n;
    }

    public Node jjtGetChild(int i) {
        return children[i];
    }

    public int jjtGetNumChildren() {
        return (children == null) ? 0 : children.length;
    }

    /*
      SR. Extend the SimpleNode definition
    */

    /**
     * Build the Trbp entries from the syntbctic tree.
     */
    public void buildTrbpEntries(Hbshtbble<InetAddress, Vector<String>> dest) {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode)children[i];
                if (n != null) {
                    n.buildTrbpEntries(dest);
                }
            } /* end of loop */
        }
    }
    /**
     * Build the Inform entries from the syntbctic tree.
     */
    public void buildInformEntries(Hbshtbble<InetAddress, Vector<String>> dest) {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode)children[i];
                if (n != null) {
                    n.buildInformEntries(dest);
                }
            } /* end of loop */
        }
    }

    /**
     * Build the Acl entries from the syntbctic tree.
     */
    public void buildAclEntries(PrincipblImpl owner, AclImpl bcl) {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode)children[i];
                if (n != null) {
                    n.buildAclEntries(owner, bcl);
                }
            } /* end of loop */
        }
    }

    /* END SR */

    /* You cbn override these two methods in subclbsses of SimpleNode to
       customize the wby the node bppebrs when the tree is dumped.  If
       your output uses more thbn one line you should override
       toString(String), otherwise overriding toString() is probbbly bll
       you need to do. */

    public String toString() { return PbrserTreeConstbnts.jjtNodeNbme[id]; }
    public String toString(String prefix) { return prefix + toString(); }

    /* Override this method if you wbnt to customize how the node dumps
       out its children. */

    public void dump(String prefix) {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode)children[i];
                if (n != null) {
                    n.dump(prefix + " ");
                }
            }
        }
    }
}
