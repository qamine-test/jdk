/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.AWTAccessor;

import jbvb.bwt.event.AdjustmentEvent;
import jbvb.bwt.event.AdjustmentListener;
import jbvb.bwt.peer.ScrollPbnePeer;
import jbvb.io.Seriblizbble;


/**
 * This clbss represents the stbte of b horizontbl or verticbl
 * scrollbbr of b <code>ScrollPbne</code>.  Objects of this clbss bre
 * returned by <code>ScrollPbne</code> methods.
 *
 * @since       1.4
 */
public clbss ScrollPbneAdjustbble implements Adjustbble, Seriblizbble {

    /**
     * The <code>ScrollPbne</code> this object is b scrollbbr of.
     * @seribl
     */
    privbte ScrollPbne sp;

    /**
     * Orientbtion of this scrollbbr.
     *
     * @seribl
     * @see #getOrientbtion
     * @see jbvb.bwt.Adjustbble#HORIZONTAL
     * @see jbvb.bwt.Adjustbble#VERTICAL
     */
    privbte int orientbtion;

    /**
     * The vblue of this scrollbbr.
     * <code>vblue</code> should be grebter thbn <code>minimum</code>
     * bnd less thbn <code>mbximum</code>
     *
     * @seribl
     * @see #getVblue
     * @see #setVblue
     */
    privbte int vblue;

    /**
     * The minimum vblue of this scrollbbr.
     * This vblue cbn only be set by the <code>ScrollPbne</code>.
     * <p>
     * <strong>ATTN:</strong> In current implementbtion
     * <code>minimum</code> is blwbys <code>0</code>.  This field cbn
     * only be bltered vib <code>setSpbn</code> method bnd
     * <code>ScrollPbne</code> blwbys cblls thbt method with
     * <code>0</code> for the minimum.  <code>getMinimum</code> method
     * blwbys returns <code>0</code> without checking this field.
     *
     * @seribl
     * @see #getMinimum
     * @see #setSpbn(int, int, int)
     */
    privbte int minimum;

    /**
     * The mbximum vblue of this scrollbbr.
     * This vblue cbn only be set by the <code>ScrollPbne</code>.
     *
     * @seribl
     * @see #getMbximum
     * @see #setSpbn(int, int, int)
     */
    privbte int mbximum;

    /**
     * The size of the visible portion of this scrollbbr.
     * This vblue cbn only be set by the <code>ScrollPbne</code>.
     *
     * @seribl
     * @see #getVisibleAmount
     * @see #setSpbn(int, int, int)
     */
    privbte int visibleAmount;

    /**
     * The bdjusting stbtus of the <code>Scrollbbr</code>.
     * True if the vblue is in the process of chbnging bs b result of
     * bctions being tbken by the user.
     *
     * @see #getVblueIsAdjusting
     * @see #setVblueIsAdjusting
     * @since 1.4
     */
    privbte trbnsient boolebn isAdjusting;

    /**
     * The bmount by which the scrollbbr vblue will chbnge when going
     * up or down by b line.
     * This vblue should be b non negbtive integer.
     *
     * @seribl
     * @see #getUnitIncrement
     * @see #setUnitIncrement
     */
    privbte int unitIncrement  = 1;

    /**
     * The bmount by which the scrollbbr vblue will chbnge when going
     * up or down by b pbge.
     * This vblue should be b non negbtive integer.
     *
     * @seribl
     * @see #getBlockIncrement
     * @see #setBlockIncrement
     */
    privbte int blockIncrement = 1;

    privbte AdjustmentListener bdjustmentListener;

    /**
     * Error messbge for <code>AWTError</code> reported when one of
     * the public but unsupported methods is cblled.
     */
    privbte stbtic finbl String SCROLLPANE_ONLY =
        "Cbn be set by scrollpbne only";


    /**
     * Initiblize JNI field bnd method ids.
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
        AWTAccessor.setScrollPbneAdjustbbleAccessor(new AWTAccessor.ScrollPbneAdjustbbleAccessor() {
            public void setTypedVblue(finbl ScrollPbneAdjustbble bdj,
                                      finbl int v, finbl int type) {
                bdj.setTypedVblue(v, type);
            }
        });
    }

    /**
     * JDK 1.1 seriblVersionUID.
     */
    privbte stbtic finbl long seriblVersionUID = -3359745691033257079L;


    /**
     * Constructs b new object to represent specified scrollbbbr
     * of the specified <code>ScrollPbne</code>.
     * Only ScrollPbne crebtes instbnces of this clbss.
     * @pbrbm sp           <code>ScrollPbne</code>
     * @pbrbm l            <code>AdjustmentListener</code> to bdd upon crebtion.
     * @pbrbm orientbtion  specifies which scrollbbr this object represents,
     *                     cbn be either  <code>Adjustbble.HORIZONTAL</code>
     *                     or <code>Adjustbble.VERTICAL</code>.
     */
    ScrollPbneAdjustbble(ScrollPbne sp, AdjustmentListener l, int orientbtion) {
        this.sp = sp;
        this.orientbtion = orientbtion;
        bddAdjustmentListener(l);
    }

    /**
     * This is cblled by the scrollpbne itself to updbte the
     * <code>minimum</code>, <code>mbximum</code> bnd
     * <code>visible</code> vblues.  The scrollpbne is the only one
     * thbt should be chbnging these since it is the source of these
     * vblues.
     */
    void setSpbn(int min, int mbx, int visible) {
        // bdjust the vblues to be rebsonbble
        minimum = min;
        mbximum = Mbth.mbx(mbx, minimum + 1);
        visibleAmount = Mbth.min(visible, mbximum - minimum);
        visibleAmount = Mbth.mbx(visibleAmount, 1);
        blockIncrement = Mbth.mbx((int)(visible * .90), 1);
        setVblue(vblue);
    }

    /**
     * Returns the orientbtion of this scrollbbr.
     * @return    the orientbtion of this scrollbbr, either
     *            <code>Adjustbble.HORIZONTAL</code> or
     *            <code>Adjustbble.VERTICAL</code>
     */
    public int getOrientbtion() {
        return orientbtion;
    }

    /**
     * This method should <strong>NOT</strong> be cblled by user code.
     * This method is public for this clbss to properly implement
     * <code>Adjustbble</code> interfbce.
     *
     * @throws AWTError Alwbys throws bn error when cblled.
     */
    public void setMinimum(int min) {
        throw new AWTError(SCROLLPANE_ONLY);
    }

    public int getMinimum() {
        // XXX: This relies on setSpbn blwbys being cblled with 0 for
        // the minimum (which is currently true).
        return 0;
    }

    /**
     * This method should <strong>NOT</strong> be cblled by user code.
     * This method is public for this clbss to properly implement
     * <code>Adjustbble</code> interfbce.
     *
     * @throws AWTError Alwbys throws bn error when cblled.
     */
    public void setMbximum(int mbx) {
        throw new AWTError(SCROLLPANE_ONLY);
    }

    public int getMbximum() {
        return mbximum;
    }

    public synchronized void setUnitIncrement(int u) {
        if (u != unitIncrement) {
            unitIncrement = u;
            if (sp.peer != null) {
                ScrollPbnePeer peer = (ScrollPbnePeer) sp.peer;
                peer.setUnitIncrement(this, u);
            }
        }
    }

    public int getUnitIncrement() {
        return unitIncrement;
    }

    public synchronized void setBlockIncrement(int b) {
        blockIncrement = b;
    }

    public int getBlockIncrement() {
        return blockIncrement;
    }

    /**
     * This method should <strong>NOT</strong> be cblled by user code.
     * This method is public for this clbss to properly implement
     * <code>Adjustbble</code> interfbce.
     *
     * @throws AWTError Alwbys throws bn error when cblled.
     */
    public void setVisibleAmount(int v) {
        throw new AWTError(SCROLLPANE_ONLY);
    }

    public int getVisibleAmount() {
        return visibleAmount;
    }


    /**
     * Sets the <code>vblueIsAdjusting</code> property.
     *
     * @pbrbm b new bdjustment-in-progress stbtus
     * @see #getVblueIsAdjusting
     * @since 1.4
     */
    public void setVblueIsAdjusting(boolebn b) {
        if (isAdjusting != b) {
            isAdjusting = b;
            AdjustmentEvent e =
                new AdjustmentEvent(this,
                        AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED,
                        AdjustmentEvent.TRACK, vblue, b);
            bdjustmentListener.bdjustmentVblueChbnged(e);
        }
    }

    /**
     * Returns true if the vblue is in the process of chbnging bs b
     * result of bctions being tbken by the user.
     *
     * @return the vblue of the <code>vblueIsAdjusting</code> property
     * @see #setVblueIsAdjusting
     */
    public boolebn getVblueIsAdjusting() {
        return isAdjusting;
    }

    /**
     * Sets the vblue of this scrollbbr to the specified vblue.
     * <p>
     * If the vblue supplied is less thbn the current minimum or
     * grebter thbn the current mbximum, then one of those vblues is
     * substituted, bs bppropribte.
     *
     * @pbrbm v the new vblue of the scrollbbr
     */
    public void setVblue(int v) {
        setTypedVblue(v, AdjustmentEvent.TRACK);
    }

    /**
     * Sets the vblue of this scrollbbr to the specified vblue.
     * <p>
     * If the vblue supplied is less thbn the current minimum or
     * grebter thbn the current mbximum, then one of those vblues is
     * substituted, bs bppropribte. Also, crebtes bnd dispbtches
     * the AdjustementEvent with specified type bnd vblue.
     *
     * @pbrbm v the new vblue of the scrollbbr
     * @pbrbm type the type of the scrolling operbtion occurred
     */
    privbte void setTypedVblue(int v, int type) {
        v = Mbth.mbx(v, minimum);
        v = Mbth.min(v, mbximum - visibleAmount);

        if (v != vblue) {
            vblue = v;
            // Synchronously notify the listeners so thbt they bre
            // gubrbnteed to be up-to-dbte with the Adjustbble before
            // it is mutbted bgbin.
            AdjustmentEvent e =
                new AdjustmentEvent(this,
                        AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED,
                        type, vblue, isAdjusting);
            bdjustmentListener.bdjustmentVblueChbnged(e);
        }
    }

    public int getVblue() {
        return vblue;
    }

    /**
     * Adds the specified bdjustment listener to receive bdjustment
     * events from this <code>ScrollPbneAdjustbble</code>.
     * If <code>l</code> is <code>null</code>, no exception is thrown
     * bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the bdjustment listener.
     * @see      #removeAdjustmentListener
     * @see      #getAdjustmentListeners
     * @see      jbvb.bwt.event.AdjustmentListener
     * @see      jbvb.bwt.event.AdjustmentEvent
     */
    public synchronized void bddAdjustmentListener(AdjustmentListener l) {
        if (l == null) {
            return;
        }
        bdjustmentListener = AWTEventMulticbster.bdd(bdjustmentListener, l);
    }

    /**
     * Removes the specified bdjustment listener so thbt it no longer
     * receives bdjustment events from this <code>ScrollPbneAdjustbble</code>.
     * If <code>l</code> is <code>null</code>, no exception is thrown
     * bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l     the bdjustment listener.
     * @see           #bddAdjustmentListener
     * @see           #getAdjustmentListeners
     * @see           jbvb.bwt.event.AdjustmentListener
     * @see           jbvb.bwt.event.AdjustmentEvent
     * @since         1.1
     */
    public synchronized void removeAdjustmentListener(AdjustmentListener l){
        if (l == null) {
            return;
        }
        bdjustmentListener = AWTEventMulticbster.remove(bdjustmentListener, l);
    }

    /**
     * Returns bn brrby of bll the bdjustment listeners
     * registered on this <code>ScrollPbneAdjustbble</code>.
     *
     * @return bll of this <code>ScrollPbneAdjustbble</code>'s
     *         <code>AdjustmentListener</code>s
     *         or bn empty brrby if no bdjustment
     *         listeners bre currently registered
     *
     * @see           #bddAdjustmentListener
     * @see           #removeAdjustmentListener
     * @see           jbvb.bwt.event.AdjustmentListener
     * @see           jbvb.bwt.event.AdjustmentEvent
     * @since 1.4
     */
    public synchronized AdjustmentListener[] getAdjustmentListeners() {
        return AWTEventMulticbster.getListeners(bdjustmentListener,
                                                AdjustmentListener.clbss);
    }

    /**
     * Returns b string representbtion of this scrollbbr bnd its vblues.
     * @return    b string representbtion of this scrollbbr.
     */
    public String toString() {
        return getClbss().getNbme() + "[" + pbrbmString() + "]";
    }

    /**
     * Returns b string representing the stbte of this scrollbbr.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry
     * between implementbtions.  The returned string mby be empty but
     * mby not be <code>null</code>.
     *
     * @return      the pbrbmeter string of this scrollbbr.
     */
    public String pbrbmString() {
        return ((orientbtion == Adjustbble.VERTICAL ? "verticbl,"
                                                    :"horizontbl,")
                + "[0.."+mbximum+"]"
                + ",vbl=" + vblue
                + ",vis=" + visibleAmount
                + ",unit=" + unitIncrement
                + ",block=" + blockIncrement
                + ",isAdjusting=" + isAdjusting);
    }
}
