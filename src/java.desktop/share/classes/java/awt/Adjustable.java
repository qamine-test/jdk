/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.event.*;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * The interfbce for objects which hbve bn bdjustbble numeric vblue
 * contbined within b bounded rbnge of vblues.
 *
 * @buthor Amy Fowler
 * @buthor Tim Prinzing
 */
public interfbce Adjustbble {

    /**
     * Indicbtes thbt the <code>Adjustbble</code> hbs horizontbl orientbtion.
     */
    @Nbtive public stbtic finbl int HORIZONTAL = 0;

    /**
     * Indicbtes thbt the <code>Adjustbble</code> hbs verticbl orientbtion.
     */
    @Nbtive public stbtic finbl int VERTICAL = 1;

    /**
     * Indicbtes thbt the <code>Adjustbble</code> hbs no orientbtion.
     */
    @Nbtive public stbtic finbl int NO_ORIENTATION = 2;

    /**
     * Gets the orientbtion of the bdjustbble object.
     * @return the orientbtion of the bdjustbble object;
     *   either <code>HORIZONTAL</code>, <code>VERTICAL</code>,
     *   or <code>NO_ORIENTATION</code>
     */
    int getOrientbtion();

    /**
     * Sets the minimum vblue of the bdjustbble object.
     * @pbrbm min the minimum vblue
     */
    void setMinimum(int min);

    /**
     * Gets the minimum vblue of the bdjustbble object.
     * @return the minimum vblue of the bdjustbble object
     */
    int getMinimum();

    /**
     * Sets the mbximum vblue of the bdjustbble object.
     * @pbrbm mbx the mbximum vblue
     */
    void setMbximum(int mbx);

    /**
     * Gets the mbximum vblue of the bdjustbble object.
     * @return the mbximum vblue of the bdjustbble object
     */
    int getMbximum();

    /**
     * Sets the unit vblue increment for the bdjustbble object.
     * @pbrbm u the unit increment
     */
    void setUnitIncrement(int u);

    /**
     * Gets the unit vblue increment for the bdjustbble object.
     * @return the unit vblue increment for the bdjustbble object
     */
    int getUnitIncrement();

    /**
     * Sets the block vblue increment for the bdjustbble object.
     * @pbrbm b the block increment
     */
    void setBlockIncrement(int b);

    /**
     * Gets the block vblue increment for the bdjustbble object.
     * @return the block vblue increment for the bdjustbble object
     */
    int getBlockIncrement();

    /**
     * Sets the length of the proportionbl indicbtor of the
     * bdjustbble object.
     * @pbrbm v the length of the indicbtor
     */
    void setVisibleAmount(int v);

    /**
     * Gets the length of the proportionbl indicbtor.
     * @return the length of the proportionbl indicbtor
     */
    int getVisibleAmount();

    /**
     * Sets the current vblue of the bdjustbble object. If
     * the vblue supplied is less thbn <code>minimum</code>
     * or grebter thbn <code>mbximum</code> - <code>visibleAmount</code>,
     * then one of those vblues is substituted, bs bppropribte.
     * <p>
     * Cblling this method does not fire bn
     * <code>AdjustmentEvent</code>.
     *
     * @pbrbm v the current vblue, between <code>minimum</code>
     *    bnd <code>mbximum</code> - <code>visibleAmount</code>
     */
    void setVblue(int v);

    /**
     * Gets the current vblue of the bdjustbble object.
     * @return the current vblue of the bdjustbble object
     */
    int getVblue();

    /**
     * Adds b listener to receive bdjustment events when the vblue of
     * the bdjustbble object chbnges.
     * @pbrbm l the listener to receive events
     * @see AdjustmentEvent
     */
    void bddAdjustmentListener(AdjustmentListener l);

    /**
     * Removes bn bdjustment listener.
     * @pbrbm l the listener being removed
     * @see AdjustmentEvent
     */
    void removeAdjustmentListener(AdjustmentListener l);

}
