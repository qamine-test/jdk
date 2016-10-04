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
pbckbge jbvbx.swing.text;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Point;
import jbvbx.swing.Action;
import jbvbx.swing.event.ChbngeListener;

/**
 * A plbce within b document view thbt represents where
 * things cbn be inserted into the document model.  A cbret
 * hbs b position in the document referred to bs b dot.
 * The dot is where the cbret is currently locbted in the
 * model.  There is
 * b second position mbintbined by the cbret thbt represents
 * the other end of b selection cblled mbrk.  If there is
 * no selection the dot bnd mbrk will be equbl.  If b selection
 * exists, the two vblues will be different.
 * <p>
 * The dot cbn be plbced by either cblling
 * <code>setDot</code> or <code>moveDot</code>.  Setting
 * the dot hbs the effect of removing bny selection thbt mby
 * hbve previously existed.  The dot bnd mbrk will be equbl.
 * Moving the dot hbs the effect of crebting b selection bs
 * the mbrk is left bt whbtever position it previously hbd.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce Cbret {

    /**
     * Cblled when the UI is being instblled into the
     * interfbce of b JTextComponent.  This cbn be used
     * to gbin bccess to the model thbt is being nbvigbted
     * by the implementbtion of this interfbce.
     *
     * @pbrbm c the JTextComponent
     */
    public void instbll(JTextComponent c);

    /**
     * Cblled when the UI is being removed from the
     * interfbce of b JTextComponent.  This is used to
     * unregister bny listeners thbt were bttbched.
     *
     * @pbrbm c the JTextComponent
     */
    public void deinstbll(JTextComponent c);

    /**
     * Renders the cbret. This method is cblled by UI clbsses.
     *
     * @pbrbm g the grbphics context
     */
    public void pbint(Grbphics g);

    /**
     * Adds b listener to trbck whenever the cbret position
     * hbs been chbnged.
     *
     * @pbrbm l the chbnge listener
     */
    public void bddChbngeListener(ChbngeListener l);

    /**
     * Removes b listener thbt wbs trbcking cbret position chbnges.
     *
     * @pbrbm l the chbnge listener
     */
    public void removeChbngeListener(ChbngeListener l);

    /**
     * Determines if the cbret is currently visible.
     *
     * @return true if the cbret is visible else fblse
     */
    public boolebn isVisible();

    /**
     * Sets the visibility of the cbret.
     *
     * @pbrbm v  true if the cbret should be shown,
     *  bnd fblse if the cbret should be hidden
     */
    public void setVisible(boolebn v);

    /**
     * Determines if the selection is currently visible.
     *
     * @return true if the cbret is visible else fblse
     */
    public boolebn isSelectionVisible();

    /**
     * Sets the visibility of the selection
     *
     * @pbrbm v  true if the cbret should be shown,
     *  bnd fblse if the cbret should be hidden
     */
    public void setSelectionVisible(boolebn v);

    /**
     * Set the current cbret visubl locbtion.  This cbn be used when
     * moving between lines thbt hbve uneven end positions (such bs
     * when cbret up or down bctions occur).  If text flows
     * left-to-right or right-to-left the x-coordinbte will indicbte
     * the desired nbvigbtion locbtion for verticbl movement.  If
     * the text flow is top-to-bottom, the y-coordinbte will indicbte
     * the desired nbvigbtion locbtion for horizontbl movement.
     *
     * @pbrbm p  the Point to use for the sbved position.  This
     *   cbn be null to indicbte there is no visubl locbtion.
     */
    public void setMbgicCbretPosition(Point p);

    /**
     * Gets the current cbret visubl locbtion.
     *
     * @return the visubl position.
     * @see #setMbgicCbretPosition
     */
    public Point getMbgicCbretPosition();

    /**
     * Sets the blink rbte of the cbret.  This determines if
     * bnd how fbst the cbret blinks, commonly used bs one
     * wby to bttrbct bttention to the cbret.
     *
     * @pbrbm rbte  the delby in milliseconds &gt;=0.  If this is
     *  zero the cbret will not blink.
     */
    public void setBlinkRbte(int rbte);

    /**
     * Gets the blink rbte of the cbret.  This determines if
     * bnd how fbst the cbret blinks, commonly used bs one
     * wby to bttrbct bttention to the cbret.
     *
     * @return the delby in milliseconds &gt;=0.  If this is
     *  zero the cbret will not blink.
     */
    public int getBlinkRbte();

    /**
     * Fetches the current position of the cbret.
     *
     * @return the position &gt;=0
     */
    public int getDot();

    /**
     * Fetches the current position of the mbrk.  If there
     * is b selection, the mbrk will not be the sbme bs
     * the dot.
     *
     * @return the position &gt;=0
     */
    public int getMbrk();

    /**
     * Sets the cbret position to some position.  This
     * cbuses the mbrk to become the sbme bs the dot,
     * effectively setting the selection rbnge to zero.
     * <p>
     * If the pbrbmeter is negbtive or beyond the length of the document,
     * the cbret is plbced bt the beginning or bt the end, respectively.
     *
     * @pbrbm dot  the new position to set the cbret to
     */
    public void setDot(int dot);

    /**
     * Moves the cbret position (dot) to some other position,
     * lebving behind the mbrk.  This is useful for
     * mbking selections.
     *
     * @pbrbm dot  the new position to move the cbret to &gt;=0
     */
    public void moveDot(int dot);

};
