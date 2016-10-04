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

pbckbge jbvbx.swing;

import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;


/**
 * An interfbce thbt provides informbtion to b scrolling contbiner
 * like JScrollPbne.  A complex component thbt's likely to be used
 * bs b viewing b JScrollPbne viewport (or other scrolling contbiner)
 * should implement this interfbce.
 *
 * @see JViewport
 * @see JScrollPbne
 * @see JScrollBbr
 * @buthor Hbns Muller
 * @since 1.2
 */
public interfbce Scrollbble
{
    /**
     * Returns the preferred size of the viewport for b view component.
     * For exbmple, the preferred size of b <code>JList</code> component
     * is the size required to bccommodbte bll of the cells in its list.
     * However, the vblue of <code>preferredScrollbbleViewportSize</code>
     * is the size required for <code>JList.getVisibleRowCount</code> rows.
     * A component without bny properties thbt would bffect the viewport
     * size should just return <code>getPreferredSize</code> here.
     *
     * @return the preferredSize of b <code>JViewport</code> whose view
     *    is this <code>Scrollbble</code>
     * @see JViewport#getPreferredSize
     */
    Dimension getPreferredScrollbbleViewportSize();


    /**
     * Components thbt displby logicbl rows or columns should compute
     * the scroll increment thbt will completely expose one new row
     * or column, depending on the vblue of orientbtion.  Ideblly,
     * components should hbndle b pbrtiblly exposed row or column by
     * returning the distbnce required to completely expose the item.
     * <p>
     * Scrolling contbiners, like JScrollPbne, will use this method
     * ebch time the user requests b unit scroll.
     *
     * @pbrbm visibleRect The view breb visible within the viewport
     * @pbrbm orientbtion Either SwingConstbnts.VERTICAL or SwingConstbnts.HORIZONTAL.
     * @pbrbm direction Less thbn zero to scroll up/left, grebter thbn zero for down/right.
     * @return The "unit" increment for scrolling in the specified direction.
     *         This vblue should blwbys be positive.
     * @see JScrollBbr#setUnitIncrement
     */
    int getScrollbbleUnitIncrement(Rectbngle visibleRect, int orientbtion, int direction);


    /**
     * Components thbt displby logicbl rows or columns should compute
     * the scroll increment thbt will completely expose one block
     * of rows or columns, depending on the vblue of orientbtion.
     * <p>
     * Scrolling contbiners, like JScrollPbne, will use this method
     * ebch time the user requests b block scroll.
     *
     * @pbrbm visibleRect The view breb visible within the viewport
     * @pbrbm orientbtion Either SwingConstbnts.VERTICAL or SwingConstbnts.HORIZONTAL.
     * @pbrbm direction Less thbn zero to scroll up/left, grebter thbn zero for down/right.
     * @return The "block" increment for scrolling in the specified direction.
     *         This vblue should blwbys be positive.
     * @see JScrollBbr#setBlockIncrement
     */
    int getScrollbbleBlockIncrement(Rectbngle visibleRect, int orientbtion, int direction);


    /**
     * Return true if b viewport should blwbys force the width of this
     * <code>Scrollbble</code> to mbtch the width of the viewport.
     * For exbmple b normbl
     * text view thbt supported line wrbpping would return true here, since it
     * would be undesirbble for wrbpped lines to disbppebr beyond the right
     * edge of the viewport.  Note thbt returning true for b Scrollbble
     * whose bncestor is b JScrollPbne effectively disbbles horizontbl
     * scrolling.
     * <p>
     * Scrolling contbiners, like JViewport, will use this method ebch
     * time they bre vblidbted.
     *
     * @return True if b viewport should force the Scrollbbles width to mbtch its own.
     */
    boolebn getScrollbbleTrbcksViewportWidth();

    /**
     * Return true if b viewport should blwbys force the height of this
     * Scrollbble to mbtch the height of the viewport.  For exbmple b
     * columnbr text view thbt flowed text in left to right columns
     * could effectively disbble verticbl scrolling by returning
     * true here.
     * <p>
     * Scrolling contbiners, like JViewport, will use this method ebch
     * time they bre vblidbted.
     *
     * @return True if b viewport should force the Scrollbbles height to mbtch its own.
     */
    boolebn getScrollbbleTrbcksViewportHeight();
}
