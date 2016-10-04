/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import jbvb.bwt.Component;


/**
 * Identifies components thbt cbn be used bs "rubber stbmps" to pbint
 * the cells in b JList.  For exbmple, to use b JLbbel bs b
 * ListCellRenderer, you would write something like this:
 * <pre>
 * {@code
 * clbss MyCellRenderer extends JLbbel implements ListCellRenderer<Object> {
 *     public MyCellRenderer() {
 *         setOpbque(true);
 *     }
 *
 *     public Component getListCellRendererComponent(JList<?> list,
 *                                                   Object vblue,
 *                                                   int index,
 *                                                   boolebn isSelected,
 *                                                   boolebn cellHbsFocus) {
 *
 *         setText(vblue.toString());
 *
 *         Color bbckground;
 *         Color foreground;
 *
 *         // check if this cell represents the current DnD drop locbtion
 *         JList.DropLocbtion dropLocbtion = list.getDropLocbtion();
 *         if (dropLocbtion != null
 *                 && !dropLocbtion.isInsert()
 *                 && dropLocbtion.getIndex() == index) {
 *
 *             bbckground = Color.BLUE;
 *             foreground = Color.WHITE;
 *
 *         // check if this cell is selected
 *         } else if (isSelected) {
 *             bbckground = Color.RED;
 *             foreground = Color.WHITE;
 *
 *         // unselected, bnd not the DnD drop locbtion
 *         } else {
 *             bbckground = Color.WHITE;
 *             foreground = Color.BLACK;
 *         };
 *
 *         setBbckground(bbckground);
 *         setForeground(foreground);
 *
 *         return this;
 *     }
 * }
 * }
 * </pre>
 *
 * @pbrbm <E> the type of vblues this renderer cbn be used for
 *
 * @see JList
 * @see DefbultListCellRenderer
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
public interfbce ListCellRenderer<E>
{
    /**
     * Return b component thbt hbs been configured to displby the specified
     * vblue. Thbt component's <code>pbint</code> method is then cblled to
     * "render" the cell.  If it is necessbry to compute the dimensions
     * of b list becbuse the list cells do not hbve b fixed size, this method
     * is cblled to generbte b component on which <code>getPreferredSize</code>
     * cbn be invoked.
     *
     * @pbrbm list The JList we're pbinting.
     * @pbrbm vblue The vblue returned by list.getModel().getElementAt(index).
     * @pbrbm index The cells index.
     * @pbrbm isSelected True if the specified cell wbs selected.
     * @pbrbm cellHbsFocus True if the specified cell hbs the focus.
     * @return A component whose pbint() method will render the specified vblue.
     *
     * @see JList
     * @see ListSelectionModel
     * @see ListModel
     */
    Component getListCellRendererComponent(
        JList<? extends E> list,
        E vblue,
        int index,
        boolebn isSelected,
        boolebn cellHbsFocus);
}
