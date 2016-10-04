/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.event.InputEvent;
import jbvb.bwt.Component;
import jbvb.bwt.Point;

import jbvb.io.InvblidObjectException;
import jbvb.util.Collections;
import jbvb.util.TooMbnyListenersException;
import jbvb.util.ArrbyList;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;

/**
 * The <code>DrbgGestureRecognizer</code> is bn
 * bbstrbct bbse clbss for the specificbtion
 * of b plbtform-dependent listener thbt cbn be bssocibted with b pbrticulbr
 * <code>Component</code> in order to
 * identify plbtform-dependent drbg initibting gestures.
 * <p>
 * The bppropribte <code>DrbgGestureRecognizer</code>
 * subclbss instbnce is obtbined from the
 * {@link DrbgSource} bssocibted with
 * b pbrticulbr <code>Component</code>, or from the <code>Toolkit</code> object vib its
 * {@link jbvb.bwt.Toolkit#crebteDrbgGestureRecognizer crebteDrbgGestureRecognizer()}
 * method.
 * <p>
 * Once the <code>DrbgGestureRecognizer</code>
 * is bssocibted with b pbrticulbr <code>Component</code>
 * it will register the bppropribte listener interfbces on thbt
 * <code>Component</code>
 * in order to trbck the input events delivered to the <code>Component</code>.
 * <p>
 * Once the <code>DrbgGestureRecognizer</code> identifies b sequence of events
 * on the <code>Component</code> bs b drbg initibting gesture, it will notify
 * its unicbst <code>DrbgGestureListener</code> by
 * invoking its
 * {@link jbvb.bwt.dnd.DrbgGestureListener#drbgGestureRecognized gestureRecognized()}
 * method.
 * <P>
 * When b concrete <code>DrbgGestureRecognizer</code>
 * instbnce detects b drbg initibting
 * gesture on the <code>Component</code> it is bssocibted with,
 * it fires b {@link DrbgGestureEvent} to
 * the <code>DrbgGestureListener</code> registered on
 * its unicbst event source for <code>DrbgGestureListener</code>
 * events. This <code>DrbgGestureListener</code> is responsible
 * for cbusing the bssocibted
 * <code>DrbgSource</code> to stbrt the Drbg bnd Drop operbtion (if
 * bppropribte).
 *
 * @buthor Lburence P. G. Cbble
 * @see jbvb.bwt.dnd.DrbgGestureListener
 * @see jbvb.bwt.dnd.DrbgGestureEvent
 * @see jbvb.bwt.dnd.DrbgSource
 */

public bbstrbct clbss DrbgGestureRecognizer implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 8996673345831063337L;

    /**
     * Construct b new <code>DrbgGestureRecognizer</code>
     * given the <code>DrbgSource</code> to be used
     * in this Drbg bnd Drop operbtion, the <code>Component</code>
     * this <code>DrbgGestureRecognizer</code> should "observe"
     * for drbg initibting gestures, the bction(s) supported
     * for this Drbg bnd Drop operbtion, bnd the
     * <code>DrbgGestureListener</code> to notify
     * once b drbg initibting gesture hbs been detected.
     *
     * @pbrbm ds  the <code>DrbgSource</code> this
     * <code>DrbgGestureRecognizer</code>
     * will use to process the Drbg bnd Drop operbtion
     *
     * @pbrbm c the <code>Component</code>
     * this <code>DrbgGestureRecognizer</code>
     * should "observe" the event strebm to,
     * in order to detect b drbg initibting gesture.
     * If this vblue is <code>null</code>, the
     * <code>DrbgGestureRecognizer</code>
     * is not bssocibted with bny <code>Component</code>.
     *
     * @pbrbm sb  the set (logicbl OR) of the
     * <code>DnDConstbnts</code>
     * thbt this Drbg bnd Drop operbtion will support
     *
     * @pbrbm dgl the <code>DrbgGestureRecognizer</code>
     * to notify when b drbg gesture is detected
     *
     * @throws IllegblArgumentException
     * if ds is <code>null</code>.
     */

    protected DrbgGestureRecognizer(DrbgSource ds, Component c, int sb, DrbgGestureListener dgl) {
        super();

        if (ds == null) throw new IllegblArgumentException("null DrbgSource");

        drbgSource    = ds;
        component     = c;
        sourceActions = sb & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);

        try {
            if (dgl != null) bddDrbgGestureListener(dgl);
        } cbtch (TooMbnyListenersException tmle) {
            // cbnt hbppen ...
        }
    }

    /**
     * Construct b new <code>DrbgGestureRecognizer</code>
     * given the <code>DrbgSource</code> to be used in this
     * Drbg bnd Drop
     * operbtion, the <code>Component</code> this
     * <code>DrbgGestureRecognizer</code> should "observe"
     * for drbg initibting gestures, bnd the bction(s)
     * supported for this Drbg bnd Drop operbtion.
     *
     * @pbrbm ds  the <code>DrbgSource</code> this
     * <code>DrbgGestureRecognizer</code> will use to
     * process the Drbg bnd Drop operbtion
     *
     * @pbrbm c   the <code>Component</code> this
     * <code>DrbgGestureRecognizer</code> should "observe" the event
     * strebm to, in order to detect b drbg initibting gesture.
     * If this vblue is <code>null</code>, the
     * <code>DrbgGestureRecognizer</code>
     * is not bssocibted with bny <code>Component</code>.
     *
     * @pbrbm sb the set (logicbl OR) of the <code>DnDConstbnts</code>
     * thbt this Drbg bnd Drop operbtion will support
     *
     * @throws IllegblArgumentException
     * if ds is <code>null</code>.
     */

    protected DrbgGestureRecognizer(DrbgSource ds, Component c, int sb) {
        this(ds, c, sb, null);
    }

    /**
     * Construct b new <code>DrbgGestureRecognizer</code>
     * given the <code>DrbgSource</code> to be used
     * in this Drbg bnd Drop operbtion, bnd
     * the <code>Component</code> this
     * <code>DrbgGestureRecognizer</code>
     * should "observe" for drbg initibting gestures.
     *
     * @pbrbm ds the <code>DrbgSource</code> this
     * <code>DrbgGestureRecognizer</code>
     * will use to process the Drbg bnd Drop operbtion
     *
     * @pbrbm c the <code>Component</code>
     * this <code>DrbgGestureRecognizer</code>
     * should "observe" the event strebm to,
     * in order to detect b drbg initibting gesture.
     * If this vblue is <code>null</code>,
     * the <code>DrbgGestureRecognizer</code>
     * is not bssocibted with bny <code>Component</code>.
     *
     * @throws IllegblArgumentException
     * if ds is <code>null</code>.
     */

    protected DrbgGestureRecognizer(DrbgSource ds, Component c) {
        this(ds, c, DnDConstbnts.ACTION_NONE);
    }

    /**
     * Construct b new <code>DrbgGestureRecognizer</code>
     * given the <code>DrbgSource</code> to be used in this
     * Drbg bnd Drop operbtion.
     *
     * @pbrbm ds the <code>DrbgSource</code> this
     * <code>DrbgGestureRecognizer</code> will
     * use to process the Drbg bnd Drop operbtion
     *
     * @throws IllegblArgumentException
     * if ds is <code>null</code>.
     */

    protected DrbgGestureRecognizer(DrbgSource ds) {
        this(ds, null);
    }

    /**
     * register this DrbgGestureRecognizer's Listeners with the Component
     *
     * subclbsses must override this method
     */

    protected bbstrbct void registerListeners();

    /**
     * unregister this DrbgGestureRecognizer's Listeners with the Component
     *
     * subclbsses must override this method
     */

    protected bbstrbct void unregisterListeners();

    /**
     * This method returns the <code>DrbgSource</code>
     * this <code>DrbgGestureRecognizer</code>
     * will use in order to process the Drbg bnd Drop
     * operbtion.
     *
     * @return the DrbgSource
     */

    public DrbgSource getDrbgSource() { return drbgSource; }

    /**
     * This method returns the <code>Component</code>
     * thbt is to be "observed" by the
     * <code>DrbgGestureRecognizer</code>
     * for drbg initibting gestures.
     *
     * @return The Component this DrbgGestureRecognizer
     * is bssocibted with
     */

    public synchronized Component getComponent() { return component; }

    /**
     * set the Component thbt the DrbgGestureRecognizer is bssocibted with
     *
     * registerListeners() bnd unregisterListeners() bre cblled bs b side
     * effect bs bppropribte.
     *
     * @pbrbm c The <code>Component</code> or <code>null</code>
     */

    public synchronized void setComponent(Component c) {
        if (component != null && drbgGestureListener != null)
            unregisterListeners();

        component = c;

        if (component != null && drbgGestureListener != null)
            registerListeners();
    }

    /**
     * This method returns bn int representing the
     * type of bction(s) this Drbg bnd Drop
     * operbtion will support.
     *
     * @return the currently permitted source bction(s)
     */

    public synchronized int getSourceActions() { return sourceActions; }

    /**
     * This method sets the permitted source drbg bction(s)
     * for this Drbg bnd Drop operbtion.
     *
     * @pbrbm bctions the permitted source drbg bction(s)
     */

    public synchronized void setSourceActions(int bctions) {
        sourceActions = bctions & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
    }

    /**
     * This method returns the first event in the
     * series of events thbt initibted
     * the Drbg bnd Drop operbtion.
     *
     * @return the initibl event thbt triggered the drbg gesture
     */

    public InputEvent getTriggerEvent() { return events.isEmpty() ? null : events.get(0); }

    /**
     * Reset the Recognizer, if its currently recognizing b gesture, ignore
     * it.
     */

    public void resetRecognizer() { events.clebr(); }

    /**
     * Register b new <code>DrbgGestureListener</code>.
     *
     * @pbrbm dgl the <code>DrbgGestureListener</code> to register
     * with this <code>DrbgGestureRecognizer</code>.
     *
     * @throws jbvb.util.TooMbnyListenersException if b
     * <code>DrbgGestureListener</code> hbs blrebdy been bdded.
     */

    public synchronized void bddDrbgGestureListener(DrbgGestureListener dgl) throws TooMbnyListenersException {
        if (drbgGestureListener != null)
            throw new TooMbnyListenersException();
        else {
            drbgGestureListener = dgl;

            if (component != null) registerListeners();
        }
    }

    /**
     * unregister the current DrbgGestureListener
     *
     * @pbrbm dgl the <code>DrbgGestureListener</code> to unregister
     * from this <code>DrbgGestureRecognizer</code>
     *
     * @throws IllegblArgumentException if
     * dgl is not (equbl to) the currently registered <code>DrbgGestureListener</code>.
     */

    public synchronized void removeDrbgGestureListener(DrbgGestureListener dgl) {
        if (drbgGestureListener == null || !drbgGestureListener.equbls(dgl))
            throw new IllegblArgumentException();
        else {
            drbgGestureListener = null;

            if (component != null) unregisterListeners();
        }
    }

    /**
     * Notify the DrbgGestureListener thbt b Drbg bnd Drop initibting
     * gesture hbs occurred. Then reset the stbte of the Recognizer.
     *
     * @pbrbm drbgAction The bction initiblly selected by the users gesture
     * @pbrbm p          The point (in Component coords) where the gesture originbted
     */
    protected synchronized void fireDrbgGestureRecognized(int drbgAction, Point p) {
        try {
            if (drbgGestureListener != null) {
                drbgGestureListener.drbgGestureRecognized(new DrbgGestureEvent(this, drbgAction, p, events));
            }
        } finblly {
            events.clebr();
        }
    }

    /**
     * Listeners registered on the Component by this Recognizer shbll record
     * bll Events thbt bre recognized bs pbrt of the series of Events thbt go
     * to comprise b Drbg bnd Drop initibting gesture vib this API.
     * <P>
     * This method is used by b <code>DrbgGestureRecognizer</code>
     * implementbtion to bdd bn <code>InputEvent</code>
     * subclbss (thbt it believes is one in b series
     * of events thbt comprise b Drbg bnd Drop operbtion)
     * to the brrby of events thbt this
     * <code>DrbgGestureRecognizer</code> mbintbins internblly.
     *
     * @pbrbm bwtie the <code>InputEvent</code>
     * to bdd to this <code>DrbgGestureRecognizer</code>'s
     * internbl brrby of events. Note thbt <code>null</code>
     * is not b vblid vblue, bnd will be ignored.
     */

    protected synchronized void bppendEvent(InputEvent bwtie) {
        events.bdd(bwtie);
    }

    /**
     * Seriblizes this <code>DrbgGestureRecognizer</code>. This method first
     * performs defbult seriblizbtion. Then, this object's
     * <code>DrbgGestureListener</code> is written out if bnd only if it cbn be
     * seriblized. If not, <code>null</code> is written instebd.
     *
     * @seriblDbtb The defbult seriblizbble fields, in blphbbeticbl order,
     *             followed by either b <code>DrbgGestureListener</code>, or
     *             <code>null</code>.
     * @since 1.4
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();

        s.writeObject(SeriblizbtionTester.test(drbgGestureListener)
                      ? drbgGestureListener : null);
    }

    /**
     * Deseriblizes this <code>DrbgGestureRecognizer</code>. This method first
     * performs defbult deseriblizbtion for bll non-<code>trbnsient</code>
     * fields. This object's <code>DrbgGestureListener</code> is then
     * deseriblized bs well by using the next object in the strebm.
     *
     * @since 1.4
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException
    {
        ObjectInputStrebm.GetField f = s.rebdFields();

        DrbgSource newDrbgSource = (DrbgSource)f.get("drbgSource", null);
        if (newDrbgSource == null) {
            throw new InvblidObjectException("null DrbgSource");
        }
        drbgSource = newDrbgSource;

        component = (Component)f.get("component", null);
        sourceActions = f.get("sourceActions", 0) & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
        events = (ArrbyList<InputEvent>)f.get("events", new ArrbyList<>(1));

        drbgGestureListener = (DrbgGestureListener)s.rebdObject();
    }

    /*
     * fields
     */

    /**
     * The <code>DrbgSource</code>
     * bssocibted with this
     * <code>DrbgGestureRecognizer</code>.
     *
     * @seribl
     */
    protected DrbgSource          drbgSource;

    /**
     * The <code>Component</code>
     * bssocibted with this <code>DrbgGestureRecognizer</code>.
     *
     * @seribl
     */
    protected Component           component;

    /**
     * The <code>DrbgGestureListener</code>
     * bssocibted with this <code>DrbgGestureRecognizer</code>.
     */
    protected trbnsient DrbgGestureListener drbgGestureListener;

  /**
   * An <code>int</code> representing
   * the type(s) of bction(s) used
   * in this Drbg bnd Drop operbtion.
   *
   * @seribl
   */
  protected int  sourceActions;

   /**
    * The list of events (in order) thbt
    * the <code>DrbgGestureRecognizer</code>
    * "recognized" bs b "gesture" thbt triggers b drbg.
    *
    * @seribl
    */
   protected ArrbyList<InputEvent> events = new ArrbyList<InputEvent>(1);
}
