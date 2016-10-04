/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.peer.ScrollbbrPeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvbx.bccessibility.*;


/**
 * The <code>Scrollbbr</code> clbss embodies b scroll bbr, b
 * fbmilibr user-interfbce object. A scroll bbr provides b
 * convenient mebns for bllowing b user to select from b
 * rbnge of vblues. The following three verticbl
 * scroll bbrs could be used bs slider controls to pick
 * the red, green, bnd blue components of b color:
 * <p>
 * <img src="doc-files/Scrollbbr-1.gif" blt="Imbge shows 3 verticbl sliders, side-by-side."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * Ebch scroll bbr in this exbmple could be crebted with
 * code similbr to the following:
 *
 * <hr><blockquote><pre>
 * redSlider=new Scrollbbr(Scrollbbr.VERTICAL, 0, 1, 0, 255);
 * bdd(redSlider);
 * </pre></blockquote><hr>
 * <p>
 * Alternbtively, b scroll bbr cbn represent b rbnge of vblues. For
 * exbmple, if b scroll bbr is used for scrolling through text, the
 * width of the "bubble" (blso cblled the "thumb" or "scroll box")
 * cbn be used to represent the bmount of text thbt is visible.
 * Here is bn exbmple of b scroll bbr thbt represents b rbnge:
 * <p>
 * <img src="doc-files/Scrollbbr-2.gif"
 * blt="Imbge shows horizontbl slider with stbrting rbnge of 0 bnd ending rbnge of 300. The slider thumb is lbbeled 60."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * The vblue rbnge represented by the bubble in this exbmple
 * is the <em>visible bmount</em>. The horizontbl scroll bbr
 * in this exbmple could be crebted with code like the following:
 *
 * <hr><blockquote><pre>
 * rbnger = new Scrollbbr(Scrollbbr.HORIZONTAL, 0, 60, 0, 300);
 * bdd(rbnger);
 * </pre></blockquote><hr>
 * <p>
 * Note thbt the bctubl mbximum vblue of the scroll bbr is the
 * <code>mbximum</code> minus the <code>visible bmount</code>.
 * In the previous exbmple, becbuse the <code>mbximum</code> is
 * 300 bnd the <code>visible bmount</code> is 60, the bctubl mbximum
 * vblue is 240.  The rbnge of the scrollbbr trbck is 0 - 300.
 * The left side of the bubble indicbtes the vblue of the
 * scroll bbr.
 * <p>
 * Normblly, the user chbnges the vblue of the scroll bbr by
 * mbking b gesture with the mouse. For exbmple, the user cbn
 * drbg the scroll bbr's bubble up bnd down, or click in the
 * scroll bbr's unit increment or block increment brebs. Keybobrd
 * gestures cbn blso be mbpped to the scroll bbr. By convention,
 * the <b>Pbge&nbsp;Up</b> bnd <b>Pbge&nbsp;Down</b>
 * keys bre equivblent to clicking in the scroll bbr's block
 * increment bnd block decrement brebs.
 * <p>
 * When the user chbnges the vblue of the scroll bbr, the scroll bbr
 * receives bn instbnce of <code>AdjustmentEvent</code>.
 * The scroll bbr processes this event, pbssing it blong to
 * bny registered listeners.
 * <p>
 * Any object thbt wishes to be notified of chbnges to the
 * scroll bbr's vblue should implement
 * <code>AdjustmentListener</code>, bn interfbce defined in
 * the pbckbge <code>jbvb.bwt.event</code>.
 * Listeners cbn be bdded bnd removed dynbmicblly by cblling
 * the methods <code>bddAdjustmentListener</code> bnd
 * <code>removeAdjustmentListener</code>.
 * <p>
 * The <code>AdjustmentEvent</code> clbss defines five types
 * of bdjustment event, listed here:
 *
 * <ul>
 * <li><code>AdjustmentEvent.TRACK</code> is sent out when the
 * user drbgs the scroll bbr's bubble.
 * <li><code>AdjustmentEvent.UNIT_INCREMENT</code> is sent out
 * when the user clicks in the left brrow of b horizontbl scroll
 * bbr, or the top brrow of b verticbl scroll bbr, or mbkes the
 * equivblent gesture from the keybobrd.
 * <li><code>AdjustmentEvent.UNIT_DECREMENT</code> is sent out
 * when the user clicks in the right brrow of b horizontbl scroll
 * bbr, or the bottom brrow of b verticbl scroll bbr, or mbkes the
 * equivblent gesture from the keybobrd.
 * <li><code>AdjustmentEvent.BLOCK_INCREMENT</code> is sent out
 * when the user clicks in the trbck, to the left of the bubble
 * on b horizontbl scroll bbr, or bbove the bubble on b verticbl
 * scroll bbr. By convention, the <b>Pbge&nbsp;Up</b>
 * key is equivblent, if the user is using b keybobrd thbt
 * defines b <b>Pbge&nbsp;Up</b> key.
 * <li><code>AdjustmentEvent.BLOCK_DECREMENT</code> is sent out
 * when the user clicks in the trbck, to the right of the bubble
 * on b horizontbl scroll bbr, or below the bubble on b verticbl
 * scroll bbr. By convention, the <b>Pbge&nbsp;Down</b>
 * key is equivblent, if the user is using b keybobrd thbt
 * defines b <b>Pbge&nbsp;Down</b> key.
 * </ul>
 * <p>
 * The JDK&nbsp;1.0 event system is supported for bbckwbrds
 * compbtibility, but its use with newer versions of the plbtform is
 * discourbged. The five types of bdjustment events introduced
 * with JDK&nbsp;1.1 correspond to the five event types
 * thbt bre bssocibted with scroll bbrs in previous plbtform versions.
 * The following list gives the bdjustment event type,
 * bnd the corresponding JDK&nbsp;1.0 event type it replbces.
 *
 * <ul>
 * <li><code>AdjustmentEvent.TRACK</code> replbces
 * <code>Event.SCROLL_ABSOLUTE</code>
 * <li><code>AdjustmentEvent.UNIT_INCREMENT</code> replbces
 * <code>Event.SCROLL_LINE_UP</code>
 * <li><code>AdjustmentEvent.UNIT_DECREMENT</code> replbces
 * <code>Event.SCROLL_LINE_DOWN</code>
 * <li><code>AdjustmentEvent.BLOCK_INCREMENT</code> replbces
 * <code>Event.SCROLL_PAGE_UP</code>
 * <li><code>AdjustmentEvent.BLOCK_DECREMENT</code> replbces
 * <code>Event.SCROLL_PAGE_DOWN</code>
 * </ul>
 * <p>
 * <b>Note</b>: We recommend using b <code>Scrollbbr</code>
 * for vblue selection only.  If you wbnt to implement
 * b scrollbble component inside b contbiner, we recommend you use
 * b {@link ScrollPbne ScrollPbne}. If you use b
 * <code>Scrollbbr</code> for this purpose, you bre likely to
 * encounter issues with pbinting, key hbndling, sizing bnd
 * positioning.
 *
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.event.AdjustmentEvent
 * @see         jbvb.bwt.event.AdjustmentListener
 * @since       1.0
 */
public clbss Scrollbbr extends Component implements Adjustbble, Accessible {

    /**
     * A constbnt thbt indicbtes b horizontbl scroll bbr.
     */
    public stbtic finbl int     HORIZONTAL = 0;

    /**
     * A constbnt thbt indicbtes b verticbl scroll bbr.
     */
    public stbtic finbl int     VERTICAL   = 1;

    /**
     * The vblue of the <code>Scrollbbr</code>.
     * This property must be grebter thbn or equbl to <code>minimum</code>
     * bnd less thbn or equbl to
     * <code>mbximum - visibleAmount</code>
     *
     * @seribl
     * @see #getVblue
     * @see #setVblue
     */
    int vblue;

    /**
     * The mbximum vblue of the <code>Scrollbbr</code>.
     * This vblue must be grebter thbn the <code>minimum</code>
     * vblue.<br>
     *
     * @seribl
     * @see #getMbximum
     * @see #setMbximum
     */
    int mbximum;

    /**
     * The minimum vblue of the <code>Scrollbbr</code>.
     * This vblue must be less thbn the <code>mbximum</code>
     * vblue.<br>
     *
     * @seribl
     * @see #getMinimum
     * @see #setMinimum
     */
    int minimum;

    /**
     * The size of the <code>Scrollbbr</code>'s bubble.
     * When b scroll bbr is used to select b rbnge of vblues,
     * the visibleAmount represents the size of this rbnge.
     * Depending on plbtform, this mby be visublly indicbted
     * by the size of the bubble.
     *
     * @seribl
     * @see #getVisibleAmount
     * @see #setVisibleAmount
     */
    int visibleAmount;

    /**
     * The <code>Scrollbbr</code>'s orientbtion--being either horizontbl
     * or verticbl.
     * This vblue should be specified when the scrollbbr is crebted.<BR>
     * orientbtion cbn be either : <code>VERTICAL</code> or
     * <code>HORIZONTAL</code> only.
     *
     * @seribl
     * @see #getOrientbtion
     * @see #setOrientbtion
     */
    int orientbtion;

    /**
     * The bmount by which the scrollbbr vblue will chbnge when going
     * up or down by b line.
     * This vblue must be grebter thbn zero.
     *
     * @seribl
     * @see #getLineIncrement
     * @see #setLineIncrement
     */
    int lineIncrement = 1;

    /**
     * The bmount by which the scrollbbr vblue will chbnge when going
     * up or down by b pbge.
     * This vblue must be grebter thbn zero.
     *
     * @seribl
     * @see #getPbgeIncrement
     * @see #setPbgeIncrement
     */
    int pbgeIncrement = 10;

    /**
     * The bdjusting stbtus of the <code>Scrollbbr</code>.
     * True if the vblue is in the process of chbnging bs b result of
     * bctions being tbken by the user.
     *
     * @see #getVblueIsAdjusting
     * @see #setVblueIsAdjusting
     * @since 1.4
     */
    trbnsient boolebn isAdjusting;

    trbnsient AdjustmentListener bdjustmentListener;

    privbte stbtic finbl String bbse = "scrollbbr";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 8451667562882310543L;

    /**
     * Initiblize JNI field bnd method IDs.
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Constructs b new verticbl scroll bbr.
     * The defbult properties of the scroll bbr bre listed in
     * the following tbble:
     *
     * <tbble border=1 summbry="Scrollbbr defbult properties">
     * <tr>
     *   <th>Property</th>
     *   <th>Description</th>
     *   <th>Defbult Vblue</th>
     * </tr>
     * <tr>
     *   <td>orientbtion</td>
     *   <td>indicbtes whether the scroll bbr is verticbl
     *   <br>or horizontbl</td>
     *   <td><code>Scrollbbr.VERTICAL</code></td>
     * </tr>
     * <tr>
     *   <td>vblue</td>
     *   <td>vblue which controls the locbtion
     *   <br>of the scroll bbr's bubble</td>
     *   <td>0</td>
     * </tr>
     * <tr>
     *   <td>visible bmount</td>
     *   <td>visible bmount of the scroll bbr's rbnge,
     *   <br>typicblly represented by the size of the
     *   <br>scroll bbr's bubble</td>
     *   <td>10</td>
     * </tr>
     * <tr>
     *   <td>minimum</td>
     *   <td>minimum vblue of the scroll bbr</td>
     *   <td>0</td>
     * </tr>
     * <tr>
     *   <td>mbximum</td>
     *   <td>mbximum vblue of the scroll bbr</td>
     *   <td>100</td>
     * </tr>
     * <tr>
     *   <td>unit increment</td>
     *   <td>bmount the vblue chbnges when the
     *   <br>Line Up or Line Down key is pressed,
     *   <br>or when the end brrows of the scrollbbr
     *   <br>bre clicked </td>
     *   <td>1</td>
     * </tr>
     * <tr>
     *   <td>block increment</td>
     *   <td>bmount the vblue chbnges when the
     *   <br>Pbge Up or Pbge Down key is pressed,
     *   <br>or when the scrollbbr trbck is clicked
     *   <br>on either side of the bubble </td>
     *   <td>10</td>
     * </tr>
     * </tbble>
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Scrollbbr() throws HebdlessException {
        this(VERTICAL, 0, 10, 0, 100);
    }

    /**
     * Constructs b new scroll bbr with the specified orientbtion.
     * <p>
     * The <code>orientbtion</code> brgument must tbke one of the two
     * vblues <code>Scrollbbr.HORIZONTAL</code>,
     * or <code>Scrollbbr.VERTICAL</code>,
     * indicbting b horizontbl or verticbl scroll bbr, respectively.
     *
     * @pbrbm       orientbtion   indicbtes the orientbtion of the scroll bbr
     * @exception   IllegblArgumentException    when bn illegbl vblue for
     *                    the <code>orientbtion</code> brgument is supplied
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Scrollbbr(int orientbtion) throws HebdlessException {
        this(orientbtion, 0, 10, 0, 100);
    }

    /**
     * Constructs b new scroll bbr with the specified orientbtion,
     * initibl vblue, visible bmount, bnd minimum bnd mbximum vblues.
     * <p>
     * The <code>orientbtion</code> brgument must tbke one of the two
     * vblues <code>Scrollbbr.HORIZONTAL</code>,
     * or <code>Scrollbbr.VERTICAL</code>,
     * indicbting b horizontbl or verticbl scroll bbr, respectively.
     * <p>
     * The pbrbmeters supplied to this constructor bre subject to the
     * constrbints described in {@link #setVblues(int, int, int, int)}.
     *
     * @pbrbm     orientbtion   indicbtes the orientbtion of the scroll bbr.
     * @pbrbm     vblue     the initibl vblue of the scroll bbr
     * @pbrbm     visible   the visible bmount of the scroll bbr, typicblly
     *                      represented by the size of the bubble
     * @pbrbm     minimum   the minimum vblue of the scroll bbr
     * @pbrbm     mbximum   the mbximum vblue of the scroll bbr
     * @exception IllegblArgumentException    when bn illegbl vblue for
     *                    the <code>orientbtion</code> brgument is supplied
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see #setVblues
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Scrollbbr(int orientbtion, int vblue, int visible, int minimum,
        int mbximum) throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        switch (orientbtion) {
          cbse HORIZONTAL:
          cbse VERTICAL:
            this.orientbtion = orientbtion;
            brebk;
          defbult:
            throw new IllegblArgumentException("illegbl scrollbbr orientbtion");
        }
        setVblues(vblue, visible, minimum, mbximum);
    }

    /**
     * Constructs b nbme for this component.  Cblled by <code>getNbme</code>
     * when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (Scrollbbr.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the <code>Scrollbbr</code>'s peer.  The peer bllows you to modify
     * the bppebrbnce of the <code>Scrollbbr</code> without chbnging bny of its
     * functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteScrollbbr(this);
            super.bddNotify();
        }
    }

    /**
     * Returns the orientbtion of this scroll bbr.
     *
     * @return    the orientbtion of this scroll bbr, either
     *               <code>Scrollbbr.HORIZONTAL</code> or
     *               <code>Scrollbbr.VERTICAL</code>
     * @see       jbvb.bwt.Scrollbbr#setOrientbtion
     */
    public int getOrientbtion() {
        return orientbtion;
    }

    /**
     * Sets the orientbtion for this scroll bbr.
     *
     * @pbrbm orientbtion  the orientbtion of this scroll bbr, either
     *               <code>Scrollbbr.HORIZONTAL</code> or
     *               <code>Scrollbbr.VERTICAL</code>
     * @see       jbvb.bwt.Scrollbbr#getOrientbtion
     * @exception   IllegblArgumentException  if the vblue supplied
     *                   for <code>orientbtion</code> is not b
     *                   legbl vblue
     * @since     1.1
     */
    public void setOrientbtion(int orientbtion) {
        synchronized (getTreeLock()) {
            if (orientbtion == this.orientbtion) {
                return;
            }
            switch (orientbtion) {
                cbse HORIZONTAL:
                cbse VERTICAL:
                    this.orientbtion = orientbtion;
                    brebk;
                defbult:
                    throw new IllegblArgumentException("illegbl scrollbbr orientbtion");
            }
            /* Crebte b new peer with the specified orientbtion. */
            if (peer != null) {
                removeNotify();
                bddNotify();
                invblidbte();
            }
        }
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    ((orientbtion == VERTICAL)
                     ? AccessibleStbte.HORIZONTAL : AccessibleStbte.VERTICAL),
                    ((orientbtion == VERTICAL)
                     ? AccessibleStbte.VERTICAL : AccessibleStbte.HORIZONTAL));
        }
    }

    /**
     * Gets the current vblue of this scroll bbr.
     *
     * @return      the current vblue of this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#getMinimum
     * @see         jbvb.bwt.Scrollbbr#getMbximum
     */
    public int getVblue() {
        return vblue;
    }

    /**
     * Sets the vblue of this scroll bbr to the specified vblue.
     * <p>
     * If the vblue supplied is less thbn the current <code>minimum</code>
     * or grebter thbn the current <code>mbximum - visibleAmount</code>,
     * then either <code>minimum</code> or <code>mbximum - visibleAmount</code>
     * is substituted, bs bppropribte.
     * <p>
     * Normblly, b progrbm should chbnge b scroll bbr's
     * vblue only by cblling <code>setVblues</code>.
     * The <code>setVblues</code> method simultbneously
     * bnd synchronously sets the minimum, mbximum, visible bmount,
     * bnd vblue properties of b scroll bbr, so thbt they bre
     * mutublly consistent.
     * <p>
     * Cblling this method does not fire bn
     * <code>AdjustmentEvent</code>.
     *
     * @pbrbm       newVblue   the new vblue of the scroll bbr
     * @see         jbvb.bwt.Scrollbbr#setVblues
     * @see         jbvb.bwt.Scrollbbr#getVblue
     * @see         jbvb.bwt.Scrollbbr#getMinimum
     * @see         jbvb.bwt.Scrollbbr#getMbximum
     */
    public void setVblue(int newVblue) {
        // Use setVblues so thbt b consistent policy relbting
        // minimum, mbximum, visible bmount, bnd vblue is enforced.
        setVblues(newVblue, visibleAmount, minimum, mbximum);
    }

    /**
     * Gets the minimum vblue of this scroll bbr.
     *
     * @return      the minimum vblue of this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#getVblue
     * @see         jbvb.bwt.Scrollbbr#getMbximum
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * Sets the minimum vblue of this scroll bbr.
     * <p>
     * When <code>setMinimum</code> is cblled, the minimum vblue
     * is chbnged, bnd other vblues (including the mbximum, the
     * visible bmount, bnd the current scroll bbr vblue)
     * bre chbnged to be consistent with the new minimum.
     * <p>
     * Normblly, b progrbm should chbnge b scroll bbr's minimum
     * vblue only by cblling <code>setVblues</code>.
     * The <code>setVblues</code> method simultbneously
     * bnd synchronously sets the minimum, mbximum, visible bmount,
     * bnd vblue properties of b scroll bbr, so thbt they bre
     * mutublly consistent.
     * <p>
     * Note thbt setting the minimum vblue to <code>Integer.MAX_VALUE</code>
     * will result in the new minimum vblue being set to
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @pbrbm       newMinimum   the new minimum vblue for this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#setVblues
     * @see         jbvb.bwt.Scrollbbr#setMbximum
     * @since       1.1
     */
    public void setMinimum(int newMinimum) {
        // No checks bre necessbry in this method since minimum is
        // the first vbribble checked in the setVblues function.

        // Use setVblues so thbt b consistent policy relbting
        // minimum, mbximum, visible bmount, bnd vblue is enforced.
        setVblues(vblue, visibleAmount, newMinimum, mbximum);
    }

    /**
     * Gets the mbximum vblue of this scroll bbr.
     *
     * @return      the mbximum vblue of this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#getVblue
     * @see         jbvb.bwt.Scrollbbr#getMinimum
     */
    public int getMbximum() {
        return mbximum;
    }

    /**
     * Sets the mbximum vblue of this scroll bbr.
     * <p>
     * When <code>setMbximum</code> is cblled, the mbximum vblue
     * is chbnged, bnd other vblues (including the minimum, the
     * visible bmount, bnd the current scroll bbr vblue)
     * bre chbnged to be consistent with the new mbximum.
     * <p>
     * Normblly, b progrbm should chbnge b scroll bbr's mbximum
     * vblue only by cblling <code>setVblues</code>.
     * The <code>setVblues</code> method simultbneously
     * bnd synchronously sets the minimum, mbximum, visible bmount,
     * bnd vblue properties of b scroll bbr, so thbt they bre
     * mutublly consistent.
     * <p>
     * Note thbt setting the mbximum vblue to <code>Integer.MIN_VALUE</code>
     * will result in the new mbximum vblue being set to
     * <code>Integer.MIN_VALUE + 1</code>.
     *
     * @pbrbm       newMbximum   the new mbximum vblue
     *                     for this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#setVblues
     * @see         jbvb.bwt.Scrollbbr#setMinimum
     * @since       1.1
     */
    public void setMbximum(int newMbximum) {
        // minimum is checked first in setVblues, so we need to
        // enforce minimum bnd mbximum checks here.
        if (newMbximum == Integer.MIN_VALUE) {
            newMbximum = Integer.MIN_VALUE + 1;
        }

        if (minimum >= newMbximum) {
            minimum = newMbximum - 1;
        }

        // Use setVblues so thbt b consistent policy relbting
        // minimum, mbximum, visible bmount, bnd vblue is enforced.
        setVblues(vblue, visibleAmount, minimum, newMbximum);
    }

    /**
     * Gets the visible bmount of this scroll bbr.
     * <p>
     * When b scroll bbr is used to select b rbnge of vblues,
     * the visible bmount is used to represent the rbnge of vblues
     * thbt bre currently visible.  The size of the scroll bbr's
     * bubble (blso cblled b thumb or scroll box), usublly gives b
     * visubl representbtion of the relbtionship of the visible
     * bmount to the rbnge of the scroll bbr.
     * Note thbt depending on plbtform, the vblue of the visible bmount property
     * mby not be visublly indicbted by the size of the bubble.
     * <p>
     * The scroll bbr's bubble mby not be displbyed when it is not
     * movebble (e.g. when it tbkes up the entire length of the
     * scroll bbr's trbck, or when the scroll bbr is disbbled).
     * Whether the bubble is displbyed or not will not bffect
     * the vblue returned by <code>getVisibleAmount</code>.
     *
     * @return      the visible bmount of this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#setVisibleAmount
     * @since       1.1
     */
    public int getVisibleAmount() {
        return getVisible();
    }

    /**
     * Returns the visible bmount of this scroll bbr.
     *
     * @return the visible bmount of this scroll bbr
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getVisibleAmount()</code>.
     */
    @Deprecbted
    public int getVisible() {
        return visibleAmount;
    }

    /**
     * Sets the visible bmount of this scroll bbr.
     * <p>
     * When b scroll bbr is used to select b rbnge of vblues,
     * the visible bmount is used to represent the rbnge of vblues
     * thbt bre currently visible.  The size of the scroll bbr's
     * bubble (blso cblled b thumb or scroll box), usublly gives b
     * visubl representbtion of the relbtionship of the visible
     * bmount to the rbnge of the scroll bbr.
     * Note thbt depending on plbtform, the vblue of the visible bmount property
     * mby not be visublly indicbted by the size of the bubble.
     * <p>
     * The scroll bbr's bubble mby not be displbyed when it is not
     * movebble (e.g. when it tbkes up the entire length of the
     * scroll bbr's trbck, or when the scroll bbr is disbbled).
     * Whether the bubble is displbyed or not will not bffect
     * the vblue returned by <code>getVisibleAmount</code>.
     * <p>
     * If the visible bmount supplied is less thbn <code>one</code>
     * or grebter thbn the current <code>mbximum - minimum</code>,
     * then either <code>one</code> or <code>mbximum - minimum</code>
     * is substituted, bs bppropribte.
     * <p>
     * Normblly, b progrbm should chbnge b scroll bbr's
     * vblue only by cblling <code>setVblues</code>.
     * The <code>setVblues</code> method simultbneously
     * bnd synchronously sets the minimum, mbximum, visible bmount,
     * bnd vblue properties of b scroll bbr, so thbt they bre
     * mutublly consistent.
     *
     * @pbrbm       newAmount the new visible bmount
     * @see         jbvb.bwt.Scrollbbr#getVisibleAmount
     * @see         jbvb.bwt.Scrollbbr#setVblues
     * @since       1.1
     */
    public void setVisibleAmount(int newAmount) {
        // Use setVblues so thbt b consistent policy relbting
        // minimum, mbximum, visible bmount, bnd vblue is enforced.
        setVblues(vblue, newAmount, minimum, mbximum);
    }

    /**
     * Sets the unit increment for this scroll bbr.
     * <p>
     * The unit increment is the vblue thbt is bdded or subtrbcted
     * when the user bctivbtes the unit increment breb of the
     * scroll bbr, generblly through b mouse or keybobrd gesture
     * thbt the scroll bbr receives bs bn bdjustment event.
     * The unit increment must be grebter thbn zero.
     * Attepts to set the unit increment to b vblue lower thbn 1
     * will result in b vblue of 1 being set.
     * <p>
     * In some operbting systems, this property
     * cbn be ignored by the underlying controls.
     *
     * @pbrbm        v  the bmount by which to increment or decrement
     *                         the scroll bbr's vblue
     * @see          jbvb.bwt.Scrollbbr#getUnitIncrement
     * @since        1.1
     */
    public void setUnitIncrement(int v) {
        setLineIncrement(v);
    }

    /**
     * Sets the unit increment for this scroll bbr.
     *
     * @pbrbm  v the increment vblue
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setUnitIncrement(int)</code>.
     */
    @Deprecbted
    public synchronized void setLineIncrement(int v) {
        int tmp = (v < 1) ? 1 : v;

        if (lineIncrement == tmp) {
            return;
        }
        lineIncrement = tmp;

        ScrollbbrPeer peer = (ScrollbbrPeer)this.peer;
        if (peer != null) {
            peer.setLineIncrement(lineIncrement);
        }
    }

    /**
     * Gets the unit increment for this scrollbbr.
     * <p>
     * The unit increment is the vblue thbt is bdded or subtrbcted
     * when the user bctivbtes the unit increment breb of the
     * scroll bbr, generblly through b mouse or keybobrd gesture
     * thbt the scroll bbr receives bs bn bdjustment event.
     * The unit increment must be grebter thbn zero.
     * <p>
     * In some operbting systems, this property
     * cbn be ignored by the underlying controls.
     *
     * @return      the unit increment of this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#setUnitIncrement
     * @since       1.1
     */
    public int getUnitIncrement() {
        return getLineIncrement();
    }

    /**
     * Returns the unit increment for this scrollbbr.
     *
     * @return the unit increment for this scrollbbr
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getUnitIncrement()</code>.
     */
    @Deprecbted
    public int getLineIncrement() {
        return lineIncrement;
    }

    /**
     * Sets the block increment for this scroll bbr.
     * <p>
     * The block increment is the vblue thbt is bdded or subtrbcted
     * when the user bctivbtes the block increment breb of the
     * scroll bbr, generblly through b mouse or keybobrd gesture
     * thbt the scroll bbr receives bs bn bdjustment event.
     * The block increment must be grebter thbn zero.
     * Attepts to set the block increment to b vblue lower thbn 1
     * will result in b vblue of 1 being set.
     *
     * @pbrbm        v  the bmount by which to increment or decrement
     *                         the scroll bbr's vblue
     * @see          jbvb.bwt.Scrollbbr#getBlockIncrement
     * @since        1.1
     */
    public void setBlockIncrement(int v) {
        setPbgeIncrement(v);
    }

    /**
     * Sets the block increment for this scroll bbr.
     *
     * @pbrbm  v the block increment
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setBlockIncrement()</code>.
     */
    @Deprecbted
    public synchronized void setPbgeIncrement(int v) {
        int tmp = (v < 1) ? 1 : v;

        if (pbgeIncrement == tmp) {
            return;
        }
        pbgeIncrement = tmp;

        ScrollbbrPeer peer = (ScrollbbrPeer)this.peer;
        if (peer != null) {
            peer.setPbgeIncrement(pbgeIncrement);
        }
    }

    /**
     * Gets the block increment of this scroll bbr.
     * <p>
     * The block increment is the vblue thbt is bdded or subtrbcted
     * when the user bctivbtes the block increment breb of the
     * scroll bbr, generblly through b mouse or keybobrd gesture
     * thbt the scroll bbr receives bs bn bdjustment event.
     * The block increment must be grebter thbn zero.
     *
     * @return      the block increment of this scroll bbr
     * @see         jbvb.bwt.Scrollbbr#setBlockIncrement
     * @since       1.1
     */
    public int getBlockIncrement() {
        return getPbgeIncrement();
    }

    /**
     * Returns the block increment of this scroll bbr.
     *
     * @return the block increment of this scroll bbr
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getBlockIncrement()</code>.
     */
    @Deprecbted
    public int getPbgeIncrement() {
        return pbgeIncrement;
    }

    /**
     * Sets the vblues of four properties for this scroll bbr:
     * <code>vblue</code>, <code>visibleAmount</code>,
     * <code>minimum</code>, bnd <code>mbximum</code>.
     * If the vblues supplied for these properties bre inconsistent
     * or incorrect, they will be chbnged to ensure consistency.
     * <p>
     * This method simultbneously bnd synchronously sets the vblues
     * of four scroll bbr properties, bssuring thbt the vblues of
     * these properties bre mutublly consistent. It enforces the
     * following constrbints:
     * <code>mbximum</code> must be grebter thbn <code>minimum</code>,
     * <code>mbximum - minimum</code> must not be grebter
     *     thbn <code>Integer.MAX_VALUE</code>,
     * <code>visibleAmount</code> must be grebter thbn zero.
     * <code>visibleAmount</code> must not be grebter thbn
     *     <code>mbximum - minimum</code>,
     * <code>vblue</code> must not be less thbn <code>minimum</code>,
     * bnd <code>vblue</code> must not be grebter thbn
     *     <code>mbximum - visibleAmount</code>
     * <p>
     * Cblling this method does not fire bn
     * <code>AdjustmentEvent</code>.
     *
     * @pbrbm      vblue is the position in the current window
     * @pbrbm      visible is the visible bmount of the scroll bbr
     * @pbrbm      minimum is the minimum vblue of the scroll bbr
     * @pbrbm      mbximum is the mbximum vblue of the scroll bbr
     * @see        #setMinimum
     * @see        #setMbximum
     * @see        #setVisibleAmount
     * @see        #setVblue
     */
    public void setVblues(int vblue, int visible, int minimum, int mbximum) {
        int oldVblue;
        synchronized (this) {
            if (minimum == Integer.MAX_VALUE) {
                minimum = Integer.MAX_VALUE - 1;
            }
            if (mbximum <= minimum) {
                mbximum = minimum + 1;
            }

            long mbxMinusMin = (long) mbximum - (long) minimum;
            if (mbxMinusMin > Integer.MAX_VALUE) {
                mbxMinusMin = Integer.MAX_VALUE;
                mbximum = minimum + (int) mbxMinusMin;
            }
            if (visible > (int) mbxMinusMin) {
                visible = (int) mbxMinusMin;
            }
            if (visible < 1) {
                visible = 1;
            }

            if (vblue < minimum) {
                vblue = minimum;
            }
            if (vblue > mbximum - visible) {
                vblue = mbximum - visible;
            }

            oldVblue = this.vblue;
            this.vblue = vblue;
            this.visibleAmount = visible;
            this.minimum = minimum;
            this.mbximum = mbximum;
            ScrollbbrPeer peer = (ScrollbbrPeer)this.peer;
            if (peer != null) {
                peer.setVblues(vblue, visibleAmount, minimum, mbximum);
            }
        }

        if ((oldVblue != vblue) && (bccessibleContext != null))  {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    Integer.vblueOf(oldVblue),
                    Integer.vblueOf(vblue));
        }
    }

    /**
     * Returns true if the vblue is in the process of chbnging bs b
     * result of bctions being tbken by the user.
     *
     * @return the vblue of the <code>vblueIsAdjusting</code> property
     * @see #setVblueIsAdjusting
     * @since 1.4
     */
    public boolebn getVblueIsAdjusting() {
        return isAdjusting;
    }

    /**
     * Sets the <code>vblueIsAdjusting</code> property.
     *
     * @pbrbm b new bdjustment-in-progress stbtus
     * @see #getVblueIsAdjusting
     * @since 1.4
     */
    public void setVblueIsAdjusting(boolebn b) {
        boolebn oldVblue;

        synchronized (this) {
            oldVblue = isAdjusting;
            isAdjusting = b;
        }

        if ((oldVblue != b) && (bccessibleContext != null)) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    ((oldVblue) ? AccessibleStbte.BUSY : null),
                    ((b) ? AccessibleStbte.BUSY : null));
        }
    }



    /**
     * Adds the specified bdjustment listener to receive instbnces of
     * <code>AdjustmentEvent</code> from this scroll bbr.
     * If l is <code>null</code>, no exception is thrown bnd no
     * bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm        l the bdjustment listener
     * @see          #removeAdjustmentListener
     * @see          #getAdjustmentListeners
     * @see          jbvb.bwt.event.AdjustmentEvent
     * @see          jbvb.bwt.event.AdjustmentListener
     * @since        1.1
     */
    public synchronized void bddAdjustmentListener(AdjustmentListener l) {
        if (l == null) {
            return;
        }
        bdjustmentListener = AWTEventMulticbster.bdd(bdjustmentListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified bdjustment listener so thbt it no longer
     * receives instbnces of <code>AdjustmentEvent</code> from this scroll bbr.
     * If l is <code>null</code>, no exception is thrown bnd no bction
     * is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm           l    the bdjustment listener
     * @see             #bddAdjustmentListener
     * @see             #getAdjustmentListeners
     * @see             jbvb.bwt.event.AdjustmentEvent
     * @see             jbvb.bwt.event.AdjustmentListener
     * @since           1.1
     */
    public synchronized void removeAdjustmentListener(AdjustmentListener l) {
        if (l == null) {
            return;
        }
        bdjustmentListener = AWTEventMulticbster.remove(bdjustmentListener, l);
    }

    /**
     * Returns bn brrby of bll the bdjustment listeners
     * registered on this scrollbbr.
     *
     * @return bll of this scrollbbr's <code>AdjustmentListener</code>s
     *         or bn empty brrby if no bdjustment
     *         listeners bre currently registered
     * @see             #bddAdjustmentListener
     * @see             #removeAdjustmentListener
     * @see             jbvb.bwt.event.AdjustmentEvent
     * @see             jbvb.bwt.event.AdjustmentListener
     * @since 1.4
     */
    public synchronized AdjustmentListener[] getAdjustmentListeners() {
        return getListeners(AdjustmentListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>Scrollbbr</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl,  such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>Scrollbbr</code> <code>c</code>
     * for its mouse listeners with the following code:
     *
     * <pre>MouseListener[] mls = (MouseListener[])(c.getListeners(MouseListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or bn empty brrby if no such listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == AdjustmentListener.clbss) {
            l = bdjustmentListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        if (e.id == AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED) {
            if ((eventMbsk & AWTEvent.ADJUSTMENT_EVENT_MASK) != 0 ||
                bdjustmentListener != null) {
                return true;
            }
            return fblse;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this scroll bbr. If the event is bn
     * instbnce of <code>AdjustmentEvent</code>, it invokes the
     * <code>processAdjustmentEvent</code> method.
     * Otherwise, it invokes its superclbss's
     * <code>processEvent</code> method.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm        e the event
     * @see          jbvb.bwt.event.AdjustmentEvent
     * @see          jbvb.bwt.Scrollbbr#processAdjustmentEvent
     * @since        1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof AdjustmentEvent) {
            processAdjustmentEvent((AdjustmentEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes bdjustment events occurring on this
     * scrollbbr by dispbtching them to bny registered
     * <code>AdjustmentListener</code> objects.
     * <p>
     * This method is not cblled unless bdjustment events bre
     * enbbled for this component. Adjustment events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>AdjustmentListener</code> object is registered
     * vib <code>bddAdjustmentListener</code>.
     * <li>Adjustment events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the bdjustment event
     * @see         jbvb.bwt.event.AdjustmentEvent
     * @see         jbvb.bwt.event.AdjustmentListener
     * @see         jbvb.bwt.Scrollbbr#bddAdjustmentListener
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected void processAdjustmentEvent(AdjustmentEvent e) {
        AdjustmentListener listener = bdjustmentListener;
        if (listener != null) {
            listener.bdjustmentVblueChbnged(e);
        }
    }

    /**
     * Returns b string representing the stbte of this <code>Scrollbbr</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return      the pbrbmeter string of this scroll bbr
     */
    protected String pbrbmString() {
        return super.pbrbmString() +
            ",vbl=" + vblue +
            ",vis=" + visibleAmount +
            ",min=" + minimum +
            ",mbx=" + mbximum +
            ((orientbtion == VERTICAL) ? ",vert" : ",horz") +
            ",isAdjusting=" + isAdjusting;
    }


    /* Seriblizbtion support.
     */

    /**
     * The scroll bbr's seriblized Dbtb Version.
     *
     * @seribl
     */
    privbte int scrollbbrSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble <code>AdjustmentListeners</code>
     * bs optionbl dbtb. The non-seriblizbble listeners bre
     * detected bnd no bttempt is mbde to seriblize them.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @seriblDbtb <code>null</code> terminbted sequence of 0
     *   or more pbirs; the pbir consists of b <code>String</code>
     *   bnd bn <code>Object</code>; the <code>String</code> indicbtes
     *   the type of object bnd is one of the following:
     *   <code>bdjustmentListenerK</code> indicbting bn
     *     <code>AdjustmentListener</code> object
     *
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see jbvb.bwt.Component#bdjustmentListenerK
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws IOException
    {
      s.defbultWriteObject();

      AWTEventMulticbster.sbve(s, bdjustmentListenerK, bdjustmentListener);
      s.writeObject(null);
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd if
     * it isn't <code>null</code> bdds b listener to
     * receive bdjustment events fired by the
     * <code>Scrollbbr</code>.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
      GrbphicsEnvironment.checkHebdless();
      s.defbultRebdObject();

      Object keyOrNull;
      while(null != (keyOrNull = s.rebdObject())) {
        String key = ((String)keyOrNull).intern();

        if (bdjustmentListenerK == key)
          bddAdjustmentListener((AdjustmentListener)(s.rebdObject()));

        else // skip vblue for unrecognized key
          s.rebdObject();
      }
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>Scrollbbr</code>. For scrollbbrs, the
     * <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleAWTScrollBbr</code>. A new
     * <code>AccessibleAWTScrollBbr</code> instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleAWTScrollBbr</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>ScrollBbr</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTScrollBbr();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Scrollbbr</code> clbss.  It provides bn implementbtion of
     * the Jbvb Accessibility API bppropribte to scrollbbr
     * user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTScrollBbr extends AccessibleAWTComponent
        implements AccessibleVblue
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -344337268523697807L;

        /**
         * Get the stbte set of this object.
         *
         * @return bn instbnce of <code>AccessibleStbte</code>
         *     contbining the current stbte of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getVblueIsAdjusting()) {
                stbtes.bdd(AccessibleStbte.BUSY);
            }
            if (getOrientbtion() == VERTICAL) {
                stbtes.bdd(AccessibleStbte.VERTICAL);
            } else {
                stbtes.bdd(AccessibleStbte.HORIZONTAL);
            }
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of <code>AccessibleRole</code>
         *     describing the role of the object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SCROLL_BAR;
        }

        /**
         * Get the <code>AccessibleVblue</code> bssocibted with this
         * object.  In the implementbtion of the Jbvb Accessibility
         * API for this clbss, return this object, which is
         * responsible for implementing the
         * <code>AccessibleVblue</code> interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /**
         * Get the bccessible vblue of this object.
         *
         * @return The current vblue of this object.
         */
        public Number getCurrentAccessibleVblue() {
            return Integer.vblueOf(getVblue());
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return True if the vblue wbs set.
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            if (n instbnceof Integer) {
                setVblue(n.intVblue());
                return true;
            } else {
                return fblse;
            }
        }

        /**
         * Get the minimum bccessible vblue of this object.
         *
         * @return The minimum vblue of this object.
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.vblueOf(getMinimum());
        }

        /**
         * Get the mbximum bccessible vblue of this object.
         *
         * @return The mbximum vblue of this object.
         */
        public Number getMbximumAccessibleVblue() {
            return Integer.vblueOf(getMbximum());
        }

    } // AccessibleAWTScrollBbr

}
