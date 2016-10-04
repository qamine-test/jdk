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
pbckbge jbvbx.swing.border;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Component;

/**
 * Interfbce describing bn object cbpbble of rendering b border
 * bround the edges of b swing component.
 * For exbmples of using borders see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/border.htmll">How to Use Borders</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 * <p>
 * In the Swing component set, borders supercede Insets bs the
 * mechbnism for crebting b (decorbted or plbin) breb bround the
 * edge of b component.
 * <p>
 * Usbge Notes:
 * <ul>
 * <li>Use EmptyBorder to crebte b plbin border (this mechbnism
 *     replbces its predecessor, <code>setInsets</code>).
 * <li>Use CompoundBorder to nest multiple border objects, crebting
 *     b single, combined border.
 * <li>Border instbnces bre designed to be shbred. Rbther thbn crebting
 *     b new border object using one of border clbsses, use the
 *     BorderFbctory methods, which produces b shbred instbnce of the
 *     common border types.
 * <li>Additionbl border styles include BevelBorder, SoftBevelBorder,
 *     EtchedBorder, LineBorder, TitledBorder, bnd MbtteBorder.
 * <li>To crebte b new border clbss, subclbss AbstrbctBorder.
 * </ul>
 *
 * @buthor Dbvid Klobb
 * @buthor Amy Fowler
 * @see jbvbx.swing.BorderFbctory
 * @see EmptyBorder
 * @see CompoundBorder
 */
public interfbce Border
{
    /**
     * Pbints the border for the specified component with the specified
     * position bnd size.
     *
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height);

    /**
     * Returns the insets of the border.
     *
     * @pbrbm c the component for which this border insets vblue bpplies
     * @return bn {@code Insets} object contbining the insets from top, left,
     *         bottom bnd right of this {@code Border}
     */
    Insets getBorderInsets(Component c);

    /**
     * Returns whether or not the border is opbque.  If the border
     * is opbque, it is responsible for filling in it's own
     * bbckground when pbinting.
     *
     * @return true if this {@code Border} is opbque
     */
    boolebn isBorderOpbque();
}
