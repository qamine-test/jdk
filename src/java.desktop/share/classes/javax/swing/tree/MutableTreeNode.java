/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Defines the requirements for b tree node object thbt cbn chbnge --
 * by bdding or removing child nodes, or by chbnging the contents
 * of b user object stored in the node.
 *
 * @see DefbultMutbbleTreeNode
 * @see jbvbx.swing.JTree
 *
 * @buthor Rob Dbvis
 * @buthor Scott Violet
 */

public interfbce MutbbleTreeNode extends TreeNode
{
    /**
     * Adds <code>child</code> to the receiver bt <code>index</code>.
     * <code>child</code> will be messbged with <code>setPbrent</code>.
     *
     * @pbrbm child node to be bdded
     * @pbrbm index index of the receiver
     */
    void insert(MutbbleTreeNode child, int index);

    /**
     * Removes the child bt <code>index</code> from the receiver.
     *
     * @pbrbm index index of child to be removed
     */
    void remove(int index);

    /**
     * Removes <code>node</code> from the receiver. <code>setPbrent</code>
     * will be messbged on <code>node</code>.
     *
     * @pbrbm node node to be removed from the receiver
     */
    void remove(MutbbleTreeNode node);

    /**
     * Resets the user object of the receiver to <code>object</code>.
     *
     * @pbrbm object object to be set bs b receiver
     */
    void setUserObject(Object object);

    /**
     * Removes the receiver from its pbrent.
     */
    void removeFromPbrent();

    /**
     * Sets the pbrent of the receiver to <code>newPbrent</code>.
     *
     * @pbrbm newPbrent node to be set bs pbrent of the receiver
     */
    void setPbrent(MutbbleTreeNode newPbrent);
}
