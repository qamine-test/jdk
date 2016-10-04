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

pbckbge jbvbx.bccessibility;

/**
 * This AccessibleSelection interfbce
 * provides the stbndbrd mechbnism for bn bssistive technology to determine
 * whbt the current selected children bre, bs well bs modify the selection set.
 * Any object thbt hbs children thbt cbn be selected should support
 * the AccessibleSelection interfbce.  Applicbtions cbn determine if bn object supports the
 * AccessibleSelection interfbce by first obtbining its AccessibleContext (see
 * {@link Accessible}) bnd then cblling the
 * {@link AccessibleContext#getAccessibleSelection} method.
 * If the return vblue is not null, the object supports this interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleSelection
 *
 * @buthor      Peter Korn
 * @buthor      Hbns Muller
 * @buthor      Willie Wblker
 */
public interfbce AccessibleSelection {

    /**
     * Returns the number of Accessible children currently selected.
     * If no children bre selected, the return vblue will be 0.
     *
     * @return the number of items currently selected.
     */
     public int getAccessibleSelectionCount();

    /**
     * Returns bn Accessible representing the specified selected child
     * of the object.  If there isn't b selection, or there bre
     * fewer children selected thbn the integer pbssed in, the return
     * vblue will be null.
     * <p>Note thbt the index represents the i-th selected child, which
     * is different from the i-th child.
     *
     * @pbrbm i the zero-bbsed index of selected children
     * @return the i-th selected child
     * @see #getAccessibleSelectionCount
     */
     public Accessible getAccessibleSelection(int i);

    /**
     * Determines if the current child of this object is selected.
     *
     * @return true if the current child of this object is selected; else fblse.
     * @pbrbm i the zero-bbsed index of the child in this Accessible object.
     * @see AccessibleContext#getAccessibleChild
     */
     public boolebn isAccessibleChildSelected(int i);

    /**
     * Adds the specified Accessible child of the object to the object's
     * selection.  If the object supports multiple selections,
     * the specified child is bdded to bny existing selection, otherwise
     * it replbces bny existing selection in the object.  If the
     * specified child is blrebdy selected, this method hbs no effect.
     *
     * @pbrbm i the zero-bbsed index of the child
     * @see AccessibleContext#getAccessibleChild
     */
     public void bddAccessibleSelection(int i);

    /**
     * Removes the specified child of the object from the object's
     * selection.  If the specified item isn't currently selected, this
     * method hbs no effect.
     *
     * @pbrbm i the zero-bbsed index of the child
     * @see AccessibleContext#getAccessibleChild
     */
     public void removeAccessibleSelection(int i);

    /**
     * Clebrs the selection in the object, so thbt no children in the
     * object bre selected.
     */
     public void clebrAccessibleSelection();

    /**
     * Cbuses every child of the object to be selected
     * if the object supports multiple selections.
     */
     public void selectAllAccessibleSelection();
}
