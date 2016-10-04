/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole.inspector;

import jbvb.bwt.Component;
import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.JTree;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;
import jbvbx.swing.tree.DefbultTreeCellRenderer;

@SuppressWbrnings("seribl")
public clbss XTreeRenderer extends DefbultTreeCellRenderer {

    public Component getTreeCellRendererComponent(
            JTree tree, Object vblue, boolebn selected, boolebn expbnded,
            boolebn lebf, int row, boolebn hbsFocus) {
        super.getTreeCellRendererComponent(
                tree, vblue, selected, expbnded, lebf, row, hbsFocus);
        Object userObject = ((DefbultMutbbleTreeNode) vblue).getUserObject();
        if (userObject instbnceof XNodeInfo) {
            XNodeInfo node = (XNodeInfo) userObject;
            setToolTipText(node.getToolTipText());
            switch (node.getType()) {
                cbse MBEAN:
                    XMBebn xmbebn = (XMBebn) node.getDbtb();
                    setIcon((ImbgeIcon) xmbebn.getIcon());
                    brebk;
                cbse NONMBEAN:
                    brebk;
                cbse ATTRIBUTES:
                cbse OPERATIONS:
                cbse NOTIFICATIONS:
                    setIcon(null);
                    brebk;
                cbse ATTRIBUTE:
                cbse OPERATION:
                cbse NOTIFICATION:
                    setIcon(null);
                    brebk;
            }
        } else {
            setToolTipText(null);
        }
        return this;
    }
}
