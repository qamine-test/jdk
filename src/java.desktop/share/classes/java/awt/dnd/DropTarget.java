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

pbckbge jbvb.bwt.dnd;

import jbvb.util.TooMbnyListenersException;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;

import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.dbtbtrbnsfer.FlbvorMbp;
import jbvb.bwt.dbtbtrbnsfer.SystemFlbvorMbp;
import jbvbx.swing.Timer;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.LightweightPeer;
import jbvb.bwt.dnd.peer.DropTbrgetPeer;


/**
 * The <code>DropTbrget</code> is bssocibted
 * with b <code>Component</code> when thbt <code>Component</code>
 * wishes
 * to bccept drops during Drbg bnd Drop operbtions.
 * <P>
 *  Ebch
 * <code>DropTbrget</code> is bssocibted with b <code>FlbvorMbp</code>.
 * The defbult <code>FlbvorMbp</code> herebfter designbtes the
 * <code>FlbvorMbp</code> returned by <code>SystemFlbvorMbp.getDefbultFlbvorMbp()</code>.
 *
 * @since 1.2
 */

public clbss DropTbrget implements DropTbrgetListener, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -6283860791671019047L;

    /**
     * Crebtes b new DropTbrget given the <code>Component</code>
     * to bssocibte itself with, bn <code>int</code> representing
     * the defbult bcceptbble bction(s) to
     * support, b <code>DropTbrgetListener</code>
     * to hbndle event processing, b <code>boolebn</code> indicbting
     * if the <code>DropTbrget</code> is currently bccepting drops, bnd
     * b <code>FlbvorMbp</code> to use (or null for the defbult <CODE>FlbvorMbp</CODE>).
     * <P>
     * The Component will receive drops only if it is enbbled.
     * @pbrbm c         The <code>Component</code> with which this <code>DropTbrget</code> is bssocibted
     * @pbrbm ops       The defbult bcceptbble bctions for this <code>DropTbrget</code>
     * @pbrbm dtl       The <code>DropTbrgetListener</code> for this <code>DropTbrget</code>
     * @pbrbm bct       Is the <code>DropTbrget</code> bccepting drops.
     * @pbrbm fm        The <code>FlbvorMbp</code> to use, or null for the defbult <CODE>FlbvorMbp</CODE>
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public DropTbrget(Component c, int ops, DropTbrgetListener dtl,
                      boolebn bct, FlbvorMbp fm)
        throws HebdlessException
    {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        component = c;

        setDefbultActions(ops);

        if (dtl != null) try {
            bddDropTbrgetListener(dtl);
        } cbtch (TooMbnyListenersException tmle) {
            // do nothing!
        }

        if (c != null) {
            c.setDropTbrget(this);
            setActive(bct);
        }

        if (fm != null) {
            flbvorMbp = fm;
        } else {
            flbvorMbp = SystemFlbvorMbp.getDefbultFlbvorMbp();
        }
    }

    /**
     * Crebtes b <code>DropTbrget</code> given the <code>Component</code>
     * to bssocibte itself with, bn <code>int</code> representing
     * the defbult bcceptbble bction(s)
     * to support, b <code>DropTbrgetListener</code>
     * to hbndle event processing, bnd b <code>boolebn</code> indicbting
     * if the <code>DropTbrget</code> is currently bccepting drops.
     * <P>
     * The Component will receive drops only if it is enbbled.
     * @pbrbm c         The <code>Component</code> with which this <code>DropTbrget</code> is bssocibted
     * @pbrbm ops       The defbult bcceptbble bctions for this <code>DropTbrget</code>
     * @pbrbm dtl       The <code>DropTbrgetListener</code> for this <code>DropTbrget</code>
     * @pbrbm bct       Is the <code>DropTbrget</code> bccepting drops.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public DropTbrget(Component c, int ops, DropTbrgetListener dtl,
                      boolebn bct)
        throws HebdlessException
    {
        this(c, ops, dtl, bct, null);
    }

    /**
     * Crebtes b <code>DropTbrget</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public DropTbrget() throws HebdlessException {
        this(null, DnDConstbnts.ACTION_COPY_OR_MOVE, null, true, null);
    }

    /**
     * Crebtes b <code>DropTbrget</code> given the <code>Component</code>
     * to bssocibte itself with, bnd the <code>DropTbrgetListener</code>
     * to hbndle event processing.
     * <P>
     * The Component will receive drops only if it is enbbled.
     * @pbrbm c         The <code>Component</code> with which this <code>DropTbrget</code> is bssocibted
     * @pbrbm dtl       The <code>DropTbrgetListener</code> for this <code>DropTbrget</code>
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public DropTbrget(Component c, DropTbrgetListener dtl)
        throws HebdlessException
    {
        this(c, DnDConstbnts.ACTION_COPY_OR_MOVE, dtl, true, null);
    }

    /**
     * Crebtes b <code>DropTbrget</code> given the <code>Component</code>
     * to bssocibte itself with, bn <code>int</code> representing
     * the defbult bcceptbble bction(s) to support, bnd b
     * <code>DropTbrgetListener</code> to hbndle event processing.
     * <P>
     * The Component will receive drops only if it is enbbled.
     * @pbrbm c         The <code>Component</code> with which this <code>DropTbrget</code> is bssocibted
     * @pbrbm ops       The defbult bcceptbble bctions for this <code>DropTbrget</code>
     * @pbrbm dtl       The <code>DropTbrgetListener</code> for this <code>DropTbrget</code>
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public DropTbrget(Component c, int ops, DropTbrgetListener dtl)
        throws HebdlessException
    {
        this(c, ops, dtl, true);
    }

    /**
     * Note: this interfbce is required to permit the sbfe bssocibtion
     * of b DropTbrget with b Component in one of two wbys, either:
     * <code> component.setDropTbrget(droptbrget); </code>
     * or <code> droptbrget.setComponent(component); </code>
     * <P>
     * The Component will receive drops only if it is enbbled.
     * @pbrbm c The new <code>Component</code> this <code>DropTbrget</code>
     * is to be bssocibted with.
     */

    public synchronized void setComponent(Component c) {
        if (component == c || component != null && component.equbls(c))
            return;

        Component     old;
        ComponentPeer oldPeer = null;

        if ((old = component) != null) {
            clebrAutoscroll();

            component = null;

            if (componentPeer != null) {
                oldPeer = componentPeer;
                removeNotify(componentPeer);
            }

            old.setDropTbrget(null);

        }

        if ((component = c) != null) try {
            c.setDropTbrget(this);
        } cbtch (Exception e) { // undo the chbnge
            if (old != null) {
                old.setDropTbrget(this);
                bddNotify(oldPeer);
            }
        }
    }

    /**
     * Gets the <code>Component</code> bssocibted
     * with this <code>DropTbrget</code>.
     *
     * @return the current <code>Component</code>
     */

    public synchronized Component getComponent() {
        return component;
    }

    /**
     * Sets the defbult bcceptbble bctions for this <code>DropTbrget</code>
     *
     * @pbrbm ops the defbult bctions
     * @see jbvb.bwt.dnd.DnDConstbnts
     */

    public void setDefbultActions(int ops) {
        getDropTbrgetContext().setTbrgetActions(ops & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_REFERENCE));
    }

    /*
     * Cblled by DropTbrgetContext.setTbrgetActions()
     * with bppropribte synchronizbtion.
     */
    void doSetDefbultActions(int ops) {
        bctions = ops;
    }

    /**
     * Gets bn <code>int</code> representing the
     * current bction(s) supported by this <code>DropTbrget</code>.
     *
     * @return the current defbult bctions
     */

    public int getDefbultActions() {
        return bctions;
    }

    /**
     * Sets the DropTbrget bctive if <code>true</code>,
     * inbctive if <code>fblse</code>.
     *
     * @pbrbm isActive sets the <code>DropTbrget</code> (in)bctive.
     */

    public synchronized void setActive(boolebn isActive) {
        if (isActive != bctive) {
            bctive = isActive;
        }

        if (!bctive) clebrAutoscroll();
    }

    /**
     * Reports whether or not
     * this <code>DropTbrget</code>
     * is currently bctive (rebdy to bccept drops).
     *
     * @return <CODE>true</CODE> if bctive, <CODE>fblse</CODE> if not
     */

    public boolebn isActive() {
        return bctive;
    }

    /**
     * Adds b new <code>DropTbrgetListener</code> (UNICAST SOURCE).
     *
     * @pbrbm dtl The new <code>DropTbrgetListener</code>
     *
     * @throws TooMbnyListenersException if b
     * <code>DropTbrgetListener</code> is blrebdy bdded to this
     * <code>DropTbrget</code>.
     */

    public synchronized void bddDropTbrgetListener(DropTbrgetListener dtl) throws TooMbnyListenersException {
        if (dtl == null) return;

        if (equbls(dtl)) throw new IllegblArgumentException("DropTbrget mby not be its own Listener");

        if (dtListener == null)
            dtListener = dtl;
        else
            throw new TooMbnyListenersException();
    }

    /**
     * Removes the current <code>DropTbrgetListener</code> (UNICAST SOURCE).
     *
     * @pbrbm dtl the DropTbrgetListener to deregister.
     */

    public synchronized void removeDropTbrgetListener(DropTbrgetListener dtl) {
        if (dtl != null && dtListener != null) {
            if(dtListener.equbls(dtl))
                dtListener = null;
            else
                throw new IllegblArgumentException("listener mismbtch");
        }
    }

    /**
     * Cblls <code>drbgEnter</code> on the registered
     * <code>DropTbrgetListener</code> bnd pbsses it
     * the specified <code>DropTbrgetDrbgEvent</code>.
     * Hbs no effect if this <code>DropTbrget</code>
     * is not bctive.
     *
     * @pbrbm dtde the <code>DropTbrgetDrbgEvent</code>
     *
     * @throws NullPointerException if this <code>DropTbrget</code>
     *         is bctive bnd <code>dtde</code> is <code>null</code>
     *
     * @see #isActive
     */
    public synchronized void drbgEnter(DropTbrgetDrbgEvent dtde) {
        isDrbggingInside = true;

        if (!bctive) return;

        if (dtListener != null) {
            dtListener.drbgEnter(dtde);
        } else
            dtde.getDropTbrgetContext().setTbrgetActions(DnDConstbnts.ACTION_NONE);

        initiblizeAutoscrolling(dtde.getLocbtion());
    }

    /**
     * Cblls <code>drbgOver</code> on the registered
     * <code>DropTbrgetListener</code> bnd pbsses it
     * the specified <code>DropTbrgetDrbgEvent</code>.
     * Hbs no effect if this <code>DropTbrget</code>
     * is not bctive.
     *
     * @pbrbm dtde the <code>DropTbrgetDrbgEvent</code>
     *
     * @throws NullPointerException if this <code>DropTbrget</code>
     *         is bctive bnd <code>dtde</code> is <code>null</code>
     *
     * @see #isActive
     */
    public synchronized void drbgOver(DropTbrgetDrbgEvent dtde) {
        if (!bctive) return;

        if (dtListener != null && bctive) dtListener.drbgOver(dtde);

        updbteAutoscroll(dtde.getLocbtion());
    }

    /**
     * Cblls <code>dropActionChbnged</code> on the registered
     * <code>DropTbrgetListener</code> bnd pbsses it
     * the specified <code>DropTbrgetDrbgEvent</code>.
     * Hbs no effect if this <code>DropTbrget</code>
     * is not bctive.
     *
     * @pbrbm dtde the <code>DropTbrgetDrbgEvent</code>
     *
     * @throws NullPointerException if this <code>DropTbrget</code>
     *         is bctive bnd <code>dtde</code> is <code>null</code>
     *
     * @see #isActive
     */
    public synchronized void dropActionChbnged(DropTbrgetDrbgEvent dtde) {
        if (!bctive) return;

        if (dtListener != null) dtListener.dropActionChbnged(dtde);

        updbteAutoscroll(dtde.getLocbtion());
    }

    /**
     * Cblls <code>drbgExit</code> on the registered
     * <code>DropTbrgetListener</code> bnd pbsses it
     * the specified <code>DropTbrgetEvent</code>.
     * Hbs no effect if this <code>DropTbrget</code>
     * is not bctive.
     * <p>
     * This method itself does not throw bny exception
     * for null pbrbmeter but for exceptions thrown by
     * the respective method of the listener.
     *
     * @pbrbm dte the <code>DropTbrgetEvent</code>
     *
     * @see #isActive
     */
    public synchronized void drbgExit(DropTbrgetEvent dte) {
        isDrbggingInside = fblse;

        if (!bctive) return;

        if (dtListener != null && bctive) dtListener.drbgExit(dte);

        clebrAutoscroll();
    }

    /**
     * Cblls <code>drop</code> on the registered
     * <code>DropTbrgetListener</code> bnd pbsses it
     * the specified <code>DropTbrgetDropEvent</code>
     * if this <code>DropTbrget</code> is bctive.
     *
     * @pbrbm dtde the <code>DropTbrgetDropEvent</code>
     *
     * @throws NullPointerException if <code>dtde</code> is null
     *         bnd bt lebst one of the following is true: this
     *         <code>DropTbrget</code> is not bctive, or there is
     *         no b <code>DropTbrgetListener</code> registered.
     *
     * @see #isActive
     */
    public synchronized void drop(DropTbrgetDropEvent dtde) {
        isDrbggingInside = fblse;

        clebrAutoscroll();

        if (dtListener != null && bctive)
            dtListener.drop(dtde);
        else { // we should'nt get here ...
            dtde.rejectDrop();
        }
    }

    /**
     * Gets the <code>FlbvorMbp</code>
     * bssocibted with this <code>DropTbrget</code>.
     * If no <code>FlbvorMbp</code> hbs been set for this
     * <code>DropTbrget</code>, it is bssocibted with the defbult
     * <code>FlbvorMbp</code>.
     *
     * @return the FlbvorMbp for this DropTbrget
     */

    public FlbvorMbp getFlbvorMbp() { return flbvorMbp; }

    /**
     * Sets the <code>FlbvorMbp</code> bssocibted
     * with this <code>DropTbrget</code>.
     *
     * @pbrbm fm the new <code>FlbvorMbp</code>, or null to
     * bssocibte the defbult FlbvorMbp with this DropTbrget.
     */

    public void setFlbvorMbp(FlbvorMbp fm) {
        flbvorMbp = fm == null ? SystemFlbvorMbp.getDefbultFlbvorMbp() : fm;
    }

    /**
     * Notify the DropTbrget thbt it hbs been bssocibted with b Component
     *
     **********************************************************************
     * This method is usublly cblled from jbvb.bwt.Component.bddNotify() of
     * the Component bssocibted with this DropTbrget to notify the DropTbrget
     * thbt b ComponentPeer hbs been bssocibted with thbt Component.
     *
     * Cblling this method, other thbn to notify this DropTbrget of the
     * bssocibtion of the ComponentPeer with the Component mby result in
     * b mblfunction of the DnD system.
     **********************************************************************
     *
     * @pbrbm peer The Peer of the Component we bre bssocibted with!
     *
     */

    public void bddNotify(ComponentPeer peer) {
        if (peer == componentPeer) return;

        componentPeer = peer;

        for (Component c = component;
             c != null && peer instbnceof LightweightPeer; c = c.getPbrent()) {
            peer = c.getPeer();
        }

        if (peer instbnceof DropTbrgetPeer) {
            nbtivePeer = peer;
            ((DropTbrgetPeer)peer).bddDropTbrget(this);
        } else {
            nbtivePeer = null;
        }
    }

    /**
     * Notify the DropTbrget thbt it hbs been disbssocibted from b Component
     *
     **********************************************************************
     * This method is usublly cblled from jbvb.bwt.Component.removeNotify() of
     * the Component bssocibted with this DropTbrget to notify the DropTbrget
     * thbt b ComponentPeer hbs been disbssocibted with thbt Component.
     *
     * Cblling this method, other thbn to notify this DropTbrget of the
     * disbssocibtion of the ComponentPeer from the Component mby result in
     * b mblfunction of the DnD system.
     **********************************************************************
     *
     * @pbrbm peer The Peer of the Component we bre being disbssocibted from!
     */

    public void removeNotify(ComponentPeer peer) {
        if (nbtivePeer != null)
            ((DropTbrgetPeer)nbtivePeer).removeDropTbrget(this);

        componentPeer = nbtivePeer = null;

        synchronized (this) {
            if (isDrbggingInside) {
                drbgExit(new DropTbrgetEvent(getDropTbrgetContext()));
            }
        }
    }

    /**
     * Gets the <code>DropTbrgetContext</code> bssocibted
     * with this <code>DropTbrget</code>.
     *
     * @return the <code>DropTbrgetContext</code> bssocibted with this <code>DropTbrget</code>.
     */

    public DropTbrgetContext getDropTbrgetContext() {
        return dropTbrgetContext;
    }

    /**
     * Crebtes the DropTbrgetContext bssocibted with this DropTbrget.
     * Subclbsses mby override this method to instbntibte their own
     * DropTbrgetContext subclbss.
     *
     * This cbll is typicblly *only* cblled by the plbtform's
     * DropTbrgetContextPeer bs b drbg operbtion encounters this
     * DropTbrget. Accessing the Context while no Drbg is current
     * hbs undefined results.
     * @return the DropTbrgetContext bssocibted with this DropTbrget
     */

    protected DropTbrgetContext crebteDropTbrgetContext() {
        return new DropTbrgetContext(this);
    }

    /**
     * Seriblizes this <code>DropTbrget</code>. Performs defbult seriblizbtion,
     * bnd then writes out this object's <code>DropTbrgetListener</code> if bnd
     * only if it cbn be seriblized. If not, <code>null</code> is written
     * instebd.
     *
     * @seriblDbtb The defbult seriblizbble fields, in blphbbeticbl order,
     *             followed by either b <code>DropTbrgetListener</code>
     *             instbnce, or <code>null</code>.
     * @since 1.4
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();

        s.writeObject(SeriblizbtionTester.test(dtListener)
                      ? dtListener : null);
    }

    /**
     * Deseriblizes this <code>DropTbrget</code>. This method first performs
     * defbult deseriblizbtion for bll non-<code>trbnsient</code> fields. An
     * bttempt is then mbde to deseriblize this object's
     * <code>DropTbrgetListener</code> bs well. This is first bttempted by
     * deseriblizing the field <code>dtListener</code>, becbuse, in relebses
     * prior to 1.4, b non-<code>trbnsient</code> field of this nbme stored the
     * <code>DropTbrgetListener</code>. If this fbils, the next object in the
     * strebm is used instebd.
     *
     * @since 1.4
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException
    {
        ObjectInputStrebm.GetField f = s.rebdFields();

        try {
            dropTbrgetContext =
                (DropTbrgetContext)f.get("dropTbrgetContext", null);
        } cbtch (IllegblArgumentException e) {
            // Pre-1.4 support. 'dropTbrgetContext' wbs previously trbnsient
        }
        if (dropTbrgetContext == null) {
            dropTbrgetContext = crebteDropTbrgetContext();
        }

        component = (Component)f.get("component", null);
        bctions = f.get("bctions", DnDConstbnts.ACTION_COPY_OR_MOVE);
        bctive = f.get("bctive", true);

        // Pre-1.4 support. 'dtListener' wbs previously non-trbnsient
        try {
            dtListener = (DropTbrgetListener)f.get("dtListener", null);
        } cbtch (IllegblArgumentException e) {
            // 1.4-compbtible byte strebm. 'dtListener' wbs written explicitly
            dtListener = (DropTbrgetListener)s.rebdObject();
        }
    }

    /*********************************************************************/

    /**
     * this protected nested clbss implements butoscrolling
     */

    protected stbtic clbss DropTbrgetAutoScroller implements ActionListener {

        /**
         * construct b DropTbrgetAutoScroller
         *
         * @pbrbm c the <code>Component</code>
         * @pbrbm p the <code>Point</code>
         */

        protected DropTbrgetAutoScroller(Component c, Point p) {
            super();

            component  = c;
            butoScroll = (Autoscroll)component;

            Toolkit t  = Toolkit.getDefbultToolkit();

            Integer    initibl  = Integer.vblueOf(100);
            Integer    intervbl = Integer.vblueOf(100);

            try {
                initibl = (Integer)t.getDesktopProperty("DnD.Autoscroll.initiblDelby");
            } cbtch (Exception e) {
                // ignore
            }

            try {
                intervbl = (Integer)t.getDesktopProperty("DnD.Autoscroll.intervbl");
            } cbtch (Exception e) {
                // ignore
            }

            timer  = new Timer(intervbl.intVblue(), this);

            timer.setCoblesce(true);
            timer.setInitiblDelby(initibl.intVblue());

            locn = p;
            prev = p;

            try {
                hysteresis = ((Integer)t.getDesktopProperty("DnD.Autoscroll.cursorHysteresis")).intVblue();
            } cbtch (Exception e) {
                // ignore
            }

            timer.stbrt();
        }

        /**
         * updbte the geometry of the butoscroll region
         */

        privbte void updbteRegion() {
           Insets    i    = butoScroll.getAutoscrollInsets();
           Dimension size = component.getSize();

           if (size.width != outer.width || size.height != outer.height)
                outer.reshbpe(0, 0, size.width, size.height);

           if (inner.x != i.left || inner.y != i.top)
                inner.setLocbtion(i.left, i.top);

           int newWidth  = size.width -  (i.left + i.right);
           int newHeight = size.height - (i.top  + i.bottom);

           if (newWidth != inner.width || newHeight != inner.height)
                inner.setSize(newWidth, newHeight);

        }

        /**
         * cbuse butoscroll to occur
         *
         * @pbrbm newLocn the <code>Point</code>
         */

        protected synchronized void updbteLocbtion(Point newLocn) {
            prev = locn;
            locn = newLocn;

            if (Mbth.bbs(locn.x - prev.x) > hysteresis ||
                Mbth.bbs(locn.y - prev.y) > hysteresis) {
                if (timer.isRunning()) timer.stop();
            } else {
                if (!timer.isRunning()) timer.stbrt();
            }
        }

        /**
         * cbuse butoscrolling to stop
         */

        protected void stop() { timer.stop(); }

        /**
         * cbuse butoscroll to occur
         *
         * @pbrbm e the <code>ActionEvent</code>
         */

        public synchronized void bctionPerformed(ActionEvent e) {
            updbteRegion();

            if (outer.contbins(locn) && !inner.contbins(locn))
                butoScroll.butoscroll(locn);
        }

        /*
         * fields
         */

        privbte Component  component;
        privbte Autoscroll butoScroll;

        privbte Timer      timer;

        privbte Point      locn;
        privbte Point      prev;

        privbte Rectbngle  outer = new Rectbngle();
        privbte Rectbngle  inner = new Rectbngle();

        privbte int        hysteresis = 10;
    }

    /*********************************************************************/

    /**
     * crebte bn embedded butoscroller
     *
     * @pbrbm c the <code>Component</code>
     * @pbrbm p the <code>Point</code>
     * @return bn embedded butoscroller
     */

    protected DropTbrgetAutoScroller crebteDropTbrgetAutoScroller(Component c, Point p) {
        return new DropTbrgetAutoScroller(c, p);
    }

    /**
     * initiblize butoscrolling
     *
     * @pbrbm p the <code>Point</code>
     */

    protected void initiblizeAutoscrolling(Point p) {
        if (component == null || !(component instbnceof Autoscroll)) return;

        butoScroller = crebteDropTbrgetAutoScroller(component, p);
    }

    /**
     * updbte butoscrolling with current cursor locbtion
     *
     * @pbrbm drbgCursorLocn the <code>Point</code>
     */

    protected void updbteAutoscroll(Point drbgCursorLocn) {
        if (butoScroller != null) butoScroller.updbteLocbtion(drbgCursorLocn);
    }

    /**
     * clebr butoscrolling
     */

    protected void clebrAutoscroll() {
        if (butoScroller != null) {
            butoScroller.stop();
            butoScroller = null;
        }
    }

    /**
     * The DropTbrgetContext bssocibted with this DropTbrget.
     *
     * @seribl
     */
    privbte DropTbrgetContext dropTbrgetContext = crebteDropTbrgetContext();

    /**
     * The Component bssocibted with this DropTbrget.
     *
     * @seribl
     */
    privbte Component component;

    /*
     * Thbt Component's  Peer
     */
    privbte trbnsient ComponentPeer componentPeer;

    /*
     * Thbt Component's "nbtive" Peer
     */
    privbte trbnsient ComponentPeer nbtivePeer;


    /**
     * Defbult permissible bctions supported by this DropTbrget.
     *
     * @see #setDefbultActions
     * @see #getDefbultActions
     * @seribl
     */
    int     bctions = DnDConstbnts.ACTION_COPY_OR_MOVE;

    /**
     * <code>true</code> if the DropTbrget is bccepting Drbg &bmp; Drop operbtions.
     *
     * @seribl
     */
    boolebn bctive = true;

    /*
     * the buto scrolling object
     */

    privbte trbnsient DropTbrgetAutoScroller butoScroller;

    /*
     * The delegbte
     */

    privbte trbnsient DropTbrgetListener dtListener;

    /*
     * The FlbvorMbp
     */

    privbte trbnsient FlbvorMbp flbvorMbp;

    /*
     * If the drbgging is currently inside this drop tbrget
     */
    privbte trbnsient boolebn isDrbggingInside;
}
