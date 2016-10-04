/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.tree;

import jbvb.bwt.Component;
import jbvbx.swing.JTree;

/**
 * Defines the requirements for bn object thbt displbys b tree node.
 * See <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tree.html">How to Use Trees</b>
 * in <em>The Jbvb Tutoribl</em>
 * for bn exbmple of implementing b tree cell renderer
 * thbt displbys custom icons.
 *
 * @buthor Rob Dbvis
 * @buthor Rby Rybn
 * @buthor Scott Violet
 */
public interfbce TreeCellRenderer {

    /**
     * Sets the vblue of the current tree cell to <code>vblue</code>.
     * If <code>selected</code> is true, the cell will be drbwn bs if
     * selected. If <code>expbnded</code> is true the node is currently
     * expbnded bnd if <code>lebf</code> is true the node represents b
     * lebf bnd if <code>hbsFocus</code> is true the node currently hbs
     * focus. <code>tree</code> is the <code>JTree</code> the receiver is being
     * configured for.  Returns the <code>Component</code> thbt the renderer
     * uses to drbw the vblue.
     * <p>
     * The <code>TreeCellRenderer</code> is blso responsible for rendering the
     * the cell representing the tree's current DnD drop locbtion if
     * it hbs one. If this renderer cbres bbout rendering
     * the DnD drop locbtion, it should query the tree directly to
     * see if the given row represents the drop locbtion:
     * <pre>
     *     JTree.DropLocbtion dropLocbtion = tree.getDropLocbtion();
     *     if (dropLocbtion != null
     *             &bmp;&bmp; dropLocbtion.getChildIndex() == -1
     *             &bmp;&bmp; tree.getRowForPbth(dropLocbtion.getPbth()) == row) {
     *
     *         // this row represents the current drop locbtion
     *         // so render it speciblly, perhbps with b different color
     *     }
     * </pre>
     *
     * @pbrbm tree      the receiver is being configured for
     * @pbrbm vblue     the vblue to render
     * @pbrbm selected  whether node is selected
     * @pbrbm expbnded  whether node is expbnded
     * @pbrbm lebf      whether node is b lebd node
     * @pbrbm row       row index
     * @pbrbm hbsFocus  whether node hbs focus
     * @return          the {@code Component} thbt the renderer uses to drbw the vblue
     */
    Component getTreeCellRendererComponent(JTree tree, Object vblue,
                                   boolebn selected, boolebn expbnded,
                                   boolebn lebf, int row, boolebn hbsFocus);

}
