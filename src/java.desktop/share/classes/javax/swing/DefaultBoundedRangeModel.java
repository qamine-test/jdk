/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.event.*;
import jbvb.io.Seriblizbble;
import jbvb.util.EventListener;

/**
 * A generic implementbtion of BoundedRbngeModel.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Dbvid Klobb
 * @buthor Hbns Muller
 * @see BoundedRbngeModel
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultBoundedRbngeModel implements BoundedRbngeModel, Seriblizbble
{
    /**
     * Only one <code>ChbngeEvent</code> is needed per model instbnce since the
     * event's only (rebd-only) stbte is the source property.  The source
     * of events generbted here is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;

    /** The listeners wbiting for model chbnges. */
    protected EventListenerList listenerList = new EventListenerList();

    privbte int vblue = 0;
    privbte int extent = 0;
    privbte int min = 0;
    privbte int mbx = 100;
    privbte boolebn isAdjusting = fblse;


    /**
     * Initiblizes bll of the properties with defbult vblues.
     * Those vblues bre:
     * <ul>
     * <li><code>vblue</code> = 0
     * <li><code>extent</code> = 0
     * <li><code>minimum</code> = 0
     * <li><code>mbximum</code> = 100
     * <li><code>bdjusting</code> = fblse
     * </ul>
     */
    public DefbultBoundedRbngeModel() {
    }


    /**
     * Initiblizes vblue, extent, minimum bnd mbximum. Adjusting is fblse.
     * Throws bn <code>IllegblArgumentException</code> if the following
     * constrbints bren't sbtisfied:
     * <pre>
     * min &lt;= vblue &lt;= vblue+extent &lt;= mbx
     * </pre>
     *
     * @pbrbm vblue  bn int giving the current vblue
     * @pbrbm extent the length of the inner rbnge thbt begins bt the model's vblue
     * @pbrbm min    bn int giving the minimum vblue
     * @pbrbm mbx    bn int giving the mbximum vblue
     */
    public DefbultBoundedRbngeModel(int vblue, int extent, int min, int mbx)
    {
        if ((mbx >= min) &&
            (vblue >= min) &&
            ((vblue + extent) >= vblue) &&
            ((vblue + extent) <= mbx)) {
            this.vblue = vblue;
            this.extent = extent;
            this.min = min;
            this.mbx = mbx;
        }
        else {
            throw new IllegblArgumentException("invblid rbnge properties");
        }
    }


    /**
     * Returns the model's current vblue.
     * @return the model's current vblue
     * @see #setVblue
     * @see BoundedRbngeModel#getVblue
     */
    public int getVblue() {
      return vblue;
    }


    /**
     * Returns the model's extent.
     * @return the model's extent
     * @see #setExtent
     * @see BoundedRbngeModel#getExtent
     */
    public int getExtent() {
      return extent;
    }


    /**
     * Returns the model's minimum.
     * @return the model's minimum
     * @see #setMinimum
     * @see BoundedRbngeModel#getMinimum
     */
    public int getMinimum() {
      return min;
    }


    /**
     * Returns the model's mbximum.
     * @return  the model's mbximum
     * @see #setMbximum
     * @see BoundedRbngeModel#getMbximum
     */
    public int getMbximum() {
        return mbx;
    }


    /**
     * Sets the current vblue of the model. For b slider, thbt
     * determines where the knob bppebrs. Ensures thbt the new
     * vblue, <I>n</I> fblls within the model's constrbints:
     * <pre>
     *     minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     *
     * @see BoundedRbngeModel#setVblue
     */
    public void setVblue(int n) {
        n = Mbth.min(n, Integer.MAX_VALUE - extent);

        int newVblue = Mbth.mbx(n, min);
        if (newVblue + extent > mbx) {
            newVblue = mbx - extent;
        }
        setRbngeProperties(newVblue, extent, min, mbx, isAdjusting);
    }


    /**
     * Sets the extent to <I>n</I> bfter ensuring thbt <I>n</I>
     * is grebter thbn or equbl to zero bnd fblls within the model's
     * constrbints:
     * <pre>
     *     minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * @see BoundedRbngeModel#setExtent
     */
    public void setExtent(int n) {
        int newExtent = Mbth.mbx(0, n);
        if(vblue + newExtent > mbx) {
            newExtent = mbx - vblue;
        }
        setRbngeProperties(vblue, newExtent, min, mbx, isAdjusting);
    }


    /**
     * Sets the minimum to <I>n</I> bfter ensuring thbt <I>n</I>
     * thbt the other three properties obey the model's constrbints:
     * <pre>
     *     minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * @see #getMinimum
     * @see BoundedRbngeModel#setMinimum
     */
    public void setMinimum(int n) {
        int newMbx = Mbth.mbx(n, mbx);
        int newVblue = Mbth.mbx(n, vblue);
        int newExtent = Mbth.min(newMbx - newVblue, extent);
        setRbngeProperties(newVblue, newExtent, n, newMbx, isAdjusting);
    }


    /**
     * Sets the mbximum to <I>n</I> bfter ensuring thbt <I>n</I>
     * thbt the other three properties obey the model's constrbints:
     * <pre>
     *     minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * @see BoundedRbngeModel#setMbximum
     */
    public void setMbximum(int n) {
        int newMin = Mbth.min(n, min);
        int newExtent = Mbth.min(n - newMin, extent);
        int newVblue = Mbth.min(n - newExtent, vblue);
        setRbngeProperties(newVblue, newExtent, newMin, n, isAdjusting);
    }


    /**
     * Sets the <code>vblueIsAdjusting</code> property.
     *
     * @see #getVblueIsAdjusting
     * @see #setVblue
     * @see BoundedRbngeModel#setVblueIsAdjusting
     */
    public void setVblueIsAdjusting(boolebn b) {
        setRbngeProperties(vblue, extent, min, mbx, b);
    }


    /**
     * Returns true if the vblue is in the process of chbnging
     * bs b result of bctions being tbken by the user.
     *
     * @return the vblue of the <code>vblueIsAdjusting</code> property
     * @see #setVblue
     * @see BoundedRbngeModel#getVblueIsAdjusting
     */
    public boolebn getVblueIsAdjusting() {
        return isAdjusting;
    }


    /**
     * Sets bll of the <code>BoundedRbngeModel</code> properties bfter forcing
     * the brguments to obey the usubl constrbints:
     * <pre>
     *     minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * <p>
     * At most, one <code>ChbngeEvent</code> is generbted.
     *
     * @see BoundedRbngeModel#setRbngeProperties
     * @see #setVblue
     * @see #setExtent
     * @see #setMinimum
     * @see #setMbximum
     * @see #setVblueIsAdjusting
     */
    public void setRbngeProperties(int newVblue, int newExtent, int newMin, int newMbx, boolebn bdjusting)
    {
        if (newMin > newMbx) {
            newMin = newMbx;
        }
        if (newVblue > newMbx) {
            newMbx = newVblue;
        }
        if (newVblue < newMin) {
            newMin = newVblue;
        }

        /* Convert the bddends to long so thbt extent cbn be
         * Integer.MAX_VALUE without rolling over the sum.
         * A JCK test covers this, see bug 4097718.
         */
        if (((long)newExtent + (long)newVblue) > newMbx) {
            newExtent = newMbx - newVblue;
        }

        if (newExtent < 0) {
            newExtent = 0;
        }

        boolebn isChbnge =
            (newVblue != vblue) ||
            (newExtent != extent) ||
            (newMin != min) ||
            (newMbx != mbx) ||
            (bdjusting != isAdjusting);

        if (isChbnge) {
            vblue = newVblue;
            extent = newExtent;
            min = newMin;
            mbx = newMbx;
            isAdjusting = bdjusting;

            fireStbteChbnged();
        }
    }


    /**
     * Adds b <code>ChbngeListener</code>.  The chbnge listeners bre run ebch
     * time bny one of the Bounded Rbnge model properties chbnges.
     *
     * @pbrbm l the ChbngeListener to bdd
     * @see #removeChbngeListener
     * @see BoundedRbngeModel#bddChbngeListener
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }


    /**
     * Removes b <code>ChbngeListener</code>.
     *
     * @pbrbm l the <code>ChbngeListener</code> to remove
     * @see #bddChbngeListener
     * @see BoundedRbngeModel#removeChbngeListener
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }


    /**
     * Returns bn brrby of bll the chbnge listeners
     * registered on this <code>DefbultBoundedRbngeModel</code>.
     *
     * @return bll of this model's <code>ChbngeListener</code>s
     *         or bn empty
     *         brrby if no chbnge listeners bre currently registered
     *
     * @see #bddChbngeListener
     * @see #removeChbngeListener
     *
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }


    /**
     * Runs ebch <code>ChbngeListener</code>'s <code>stbteChbnged</code> method.
     *
     * @see #setRbngeProperties
     * @see EventListenerList
     */
    protected void fireStbteChbnged()
    {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -=2 ) {
            if (listeners[i] == ChbngeListener.clbss) {
                if (chbngeEvent == null) {
                    chbngeEvent = new ChbngeEvent(this);
                }
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }


    /**
     * Returns b string thbt displbys bll of the
     * <code>BoundedRbngeModel</code> properties.
     */
    public String toString()  {
        String modelString =
            "vblue=" + getVblue() + ", " +
            "extent=" + getExtent() + ", " +
            "min=" + getMinimum() + ", " +
            "mbx=" + getMbximum() + ", " +
            "bdj=" + getVblueIsAdjusting();

        return getClbss().getNbme() + "[" + modelString + "]";
    }

    /**
     * Returns bn brrby of bll the objects currently registered bs
     * <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s
     * bre registered using the <code>bdd<em>Foo</em>Listener</code> method.
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b <code>DefbultBoundedRbngeModel</code>
     * instbnce <code>m</code>
     * for its chbnge listeners
     * with the following code:
     *
     * <pre>ChbngeListener[] cls = (ChbngeListener[])(m.getListeners(ChbngeListener.clbss));</pre>
     *
     * If no such listeners exist,
     * this method returns bn empty brrby.
     *
     * @pbrbm <T> the type of {@code EventListener} clbss being requested
     * @pbrbm listenerType  the type of listeners requested;
     *          this pbrbmeter should specify bn interfbce
     *          thbt descends from <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s
     *          on this model,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code> doesn't
     *          specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getChbngeListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }
}
