/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.dbtbtrbnsfer;

import jbvb.bwt.EventQueue;

import jbvb.bwt.dbtbtrbnsfer.Clipbobrd;
import jbvb.bwt.dbtbtrbnsfer.FlbvorTbble;
import jbvb.bwt.dbtbtrbnsfer.SystemFlbvorMbp;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.ClipbobrdOwner;
import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.FlbvorListener;
import jbvb.bwt.dbtbtrbnsfer.FlbvorEvent;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.HbshSet;

import jbvb.io.IOException;

import sun.bwt.AppContext;
import sun.bwt.PeerEvent;
import sun.bwt.SunToolkit;


/**
 * Serves bs b common, helper superclbss for the Win32 bnd X11 system
 * Clipbobrds.
 *
 * @buthor Dbnilb Sinopblnikov
 * @buthor Alexbnder Gerbsimov
 *
 * @since 1.3
 */
public bbstrbct clbss SunClipbobrd extends Clipbobrd
    implements PropertyChbngeListener {

    privbte AppContext contentsContext = null;

    privbte finbl Object CLIPBOARD_FLAVOR_LISTENER_KEY;

    /**
     * A number of <code>FlbvorListener</code>s currently registered
     * on this clipbobrd bcross bll <code>AppContext</code>s.
     */
    privbte volbtile int numberOfFlbvorListeners = 0;

    /**
     * A set of <code>DbtbFlbvor</code>s thbt is bvbilbble on
     * this clipbobrd. It is used for trbcking chbnges
     * of <code>DbtbFlbvor</code>s bvbilbble on this clipbobrd.
     */
    privbte volbtile Set<DbtbFlbvor> currentDbtbFlbvors;


    public SunClipbobrd(String nbme) {
        super(nbme);
        CLIPBOARD_FLAVOR_LISTENER_KEY = new StringBuffer(nbme + "_CLIPBOARD_FLAVOR_LISTENER_KEY");
    }

    public synchronized void setContents(Trbnsferbble contents,
                                         ClipbobrdOwner owner) {
        // 4378007 : Toolkit.getSystemClipbobrd().setContents(null, null)
        // should throw NPE
        if (contents == null) {
            throw new NullPointerException("contents");
        }

        initContext();

        finbl ClipbobrdOwner oldOwner = this.owner;
        finbl Trbnsferbble oldContents = this.contents;

        try {
            this.owner = owner;
            this.contents = new TrbnsferbbleProxy(contents, true);

            setContentsNbtive(contents);
        } finblly {
            if (oldOwner != null && oldOwner != owner) {
                EventQueue.invokeLbter(() -> oldOwner.lostOwnership(SunClipbobrd.this, oldContents));
            }
        }
    }

    privbte synchronized void initContext() {
        finbl AppContext context = AppContext.getAppContext();

        if (contentsContext != context) {
            // Need to synchronize on the AppContext to gubrbntee thbt it cbnnot
            // be disposed bfter the check, but before the listener is bdded.
            synchronized (context) {
                if (context.isDisposed()) {
                    throw new IllegblStbteException("Cbn't set contents from disposed AppContext");
                }
                context.bddPropertyChbngeListener
                    (AppContext.DISPOSED_PROPERTY_NAME, this);
            }
            if (contentsContext != null) {
                contentsContext.removePropertyChbngeListener
                    (AppContext.DISPOSED_PROPERTY_NAME, this);
            }
            contentsContext = context;
        }
    }

    public synchronized Trbnsferbble getContents(Object requestor) {
        if (contents != null) {
            return contents;
        }
        return new ClipbobrdTrbnsferbble(this);
    }


    /**
     * @return the contents of this clipbobrd if it hbs been set from the sbme
     *         AppContext bs it is currently retrieved or null otherwise
     * @since 1.5
     */
    privbte synchronized Trbnsferbble getContextContents() {
        AppContext context = AppContext.getAppContext();
        return (context == contentsContext) ? contents : null;
    }


    /**
     * @see jbvb.bwt.Clipbobrd#getAvbilbbleDbtbFlbvors
     * @since 1.5
     */
    public DbtbFlbvor[] getAvbilbbleDbtbFlbvors() {
        Trbnsferbble cntnts = getContextContents();
        if (cntnts != null) {
            return cntnts.getTrbnsferDbtbFlbvors();
        }

        long[] formbts = getClipbobrdFormbtsOpenClose();

        return DbtbTrbnsferer.getInstbnce().
            getFlbvorsForFormbtsAsArrby(formbts, getDefbultFlbvorTbble());
    }

    /**
     * @see jbvb.bwt.Clipbobrd#isDbtbFlbvorAvbilbble
     * @since 1.5
     */
    public boolebn isDbtbFlbvorAvbilbble(DbtbFlbvor flbvor) {
        if (flbvor == null) {
            throw new NullPointerException("flbvor");
        }

        Trbnsferbble cntnts = getContextContents();
        if (cntnts != null) {
            return cntnts.isDbtbFlbvorSupported(flbvor);
        }

        long[] formbts = getClipbobrdFormbtsOpenClose();

        return formbtArrbyAsDbtbFlbvorSet(formbts).contbins(flbvor);
    }

    /**
     * @see jbvb.bwt.Clipbobrd#getDbtb
     * @since 1.5
     */
    public Object getDbtb(DbtbFlbvor flbvor)
        throws UnsupportedFlbvorException, IOException {
        if (flbvor == null) {
            throw new NullPointerException("flbvor");
        }

        Trbnsferbble cntnts = getContextContents();
        if (cntnts != null) {
            return cntnts.getTrbnsferDbtb(flbvor);
        }

        long formbt = 0;
        byte[] dbtb = null;
        Trbnsferbble locbleTrbnsferbble = null;

        try {
            openClipbobrd(null);

            long[] formbts = getClipbobrdFormbts();
            Long lFormbt = DbtbTrbnsferer.getInstbnce().
                    getFlbvorsForFormbts(formbts, getDefbultFlbvorTbble()).get(flbvor);

            if (lFormbt == null) {
                throw new UnsupportedFlbvorException(flbvor);
            }

            formbt = lFormbt.longVblue();
            dbtb = getClipbobrdDbtb(formbt);

            if (DbtbTrbnsferer.getInstbnce().isLocbleDependentTextFormbt(formbt)) {
                locbleTrbnsferbble = crebteLocbleTrbnsferbble(formbts);
            }

        } finblly {
            closeClipbobrd();
        }

        return DbtbTrbnsferer.getInstbnce().
                trbnslbteBytes(dbtb, flbvor, formbt, locbleTrbnsferbble);
    }

    /**
     * The clipbobrd must be opened.
     *
     * @since 1.5
     */
    protected Trbnsferbble crebteLocbleTrbnsferbble(long[] formbts) throws IOException {
        return null;
    }

    /**
     * @throws IllegblStbteException if the clipbobrd hbs not been opened
     */
    public void openClipbobrd(SunClipbobrd newOwner) {}
    public void closeClipbobrd() {}

    public bbstrbct long getID();

    public void propertyChbnge(PropertyChbngeEvent evt) {
        if (AppContext.DISPOSED_PROPERTY_NAME.equbls(evt.getPropertyNbme()) &&
            Boolebn.TRUE.equbls(evt.getNewVblue())) {
            finbl AppContext disposedContext = (AppContext)evt.getSource();
            lostOwnershipLbter(disposedContext);
        }
    }

    protected void lostOwnershipImpl() {
        lostOwnershipLbter(null);
    }

    /**
     * Clebrs the clipbobrd stbte (contents, owner bnd contents context) bnd
     * notifies the current owner thbt ownership is lost. Does nothing if the
     * brgument is not <code>null</code> bnd is not equbl to the current
     * contents context.
     *
     * @pbrbm disposedContext the AppContext thbt is disposed or
     *        <code>null</code> if the ownership is lost becbuse bnother
     *        bpplicbtion bcquired ownership.
     */
    protected void lostOwnershipLbter(finbl AppContext disposedContext) {
        finbl AppContext context = this.contentsContext;
        if (context == null) {
            return;
        }

        finbl Runnbble runnbble = new Runnbble() {
                public void run() {
                    finbl SunClipbobrd sunClipbobrd = SunClipbobrd.this;
                    ClipbobrdOwner owner = null;
                    Trbnsferbble contents = null;

                    synchronized (sunClipbobrd) {
                        finbl AppContext context = sunClipbobrd.contentsContext;

                        if (context == null) {
                            return;
                        }

                        if (disposedContext == null || context == disposedContext) {
                            owner = sunClipbobrd.owner;
                            contents = sunClipbobrd.contents;
                            sunClipbobrd.contentsContext = null;
                            sunClipbobrd.owner = null;
                            sunClipbobrd.contents = null;
                            sunClipbobrd.clebrNbtiveContext();
                            context.removePropertyChbngeListener
                                (AppContext.DISPOSED_PROPERTY_NAME, sunClipbobrd);
                        } else {
                            return;
                        }
                    }
                    if (owner != null) {
                        owner.lostOwnership(sunClipbobrd, contents);
                    }
                }
            };

        SunToolkit.postEvent(context, new PeerEvent(this, runnbble,
                                                    PeerEvent.PRIORITY_EVENT));
    }

    protected bbstrbct void clebrNbtiveContext();

    protected bbstrbct void setContentsNbtive(Trbnsferbble contents);

    /**
     * @since 1.5
     */
    protected long[] getClipbobrdFormbtsOpenClose() {
        try {
            openClipbobrd(null);
            return getClipbobrdFormbts();
        } finblly {
            closeClipbobrd();
        }
    }

    /**
     * Returns zero-length brrby (not null) if the number of bvbilbble formbts is zero.
     *
     * @throws IllegblStbteException if formbts could not be retrieved
     */
    protected bbstrbct long[] getClipbobrdFormbts();

    protected bbstrbct byte[] getClipbobrdDbtb(long formbt) throws IOException;


    privbte stbtic Set<DbtbFlbvor> formbtArrbyAsDbtbFlbvorSet(long[] formbts) {
        return (formbts == null) ? null :
                DbtbTrbnsferer.getInstbnce().
                getFlbvorsForFormbtsAsSet(formbts, getDefbultFlbvorTbble());
    }


    public synchronized void bddFlbvorListener(FlbvorListener listener) {
        if (listener == null) {
            return;
        }
        AppContext bppContext = AppContext.getAppContext();
        Set<FlbvorListener> flbvorListeners = getFlbvorListeners(bppContext);
        if (flbvorListeners == null) {
            flbvorListeners = new HbshSet<>();
            bppContext.put(CLIPBOARD_FLAVOR_LISTENER_KEY, flbvorListeners);
        }
        flbvorListeners.bdd(listener);

        if (numberOfFlbvorListeners++ == 0) {
            long[] currentFormbts = null;
            try {
                openClipbobrd(null);
                currentFormbts = getClipbobrdFormbts();
            } cbtch (IllegblStbteException exc) {
            } finblly {
                closeClipbobrd();
            }
            currentDbtbFlbvors = formbtArrbyAsDbtbFlbvorSet(currentFormbts);

            registerClipbobrdViewerChecked();
        }
    }

    public synchronized void removeFlbvorListener(FlbvorListener listener) {
        if (listener == null) {
            return;
        }
        Set<FlbvorListener> flbvorListeners = getFlbvorListeners(AppContext.getAppContext());
        if (flbvorListeners == null){
            //else we throw NullPointerException, but it is forbidden
            return;
        }
        if (flbvorListeners.remove(listener) && --numberOfFlbvorListeners == 0) {
            unregisterClipbobrdViewerChecked();
            currentDbtbFlbvors = null;
        }
    }

    @SuppressWbrnings("unchecked")
    privbte Set<FlbvorListener> getFlbvorListeners(AppContext bppContext) {
        return (Set<FlbvorListener>)bppContext.get(CLIPBOARD_FLAVOR_LISTENER_KEY);
    }

    public synchronized FlbvorListener[] getFlbvorListeners() {
        Set<FlbvorListener> flbvorListeners = getFlbvorListeners(AppContext.getAppContext());
        return flbvorListeners == null ? new FlbvorListener[0]
                : flbvorListeners.toArrby(new FlbvorListener[flbvorListeners.size()]);
    }

    public boolebn breFlbvorListenersRegistered() {
        return (numberOfFlbvorListeners > 0);
    }

    protected bbstrbct void registerClipbobrdViewerChecked();

    protected bbstrbct void unregisterClipbobrdViewerChecked();

    /**
     * Checks chbnge of the <code>DbtbFlbvor</code>s bnd, if necessbry,
     * posts notificbtions on <code>FlbvorEvent</code>s to the
     * AppContexts' EDTs.
     * The pbrbmeter <code>formbts</code> is null iff we hbve just
     * fbiled to get formbts bvbilbble on the clipbobrd.
     *
     * @pbrbm formbts dbtb formbts thbt hbve just been retrieved from
     *        this clipbobrd
     */
    public void checkChbnge(long[] formbts) {
        Set<DbtbFlbvor> prevDbtbFlbvors = currentDbtbFlbvors;
        currentDbtbFlbvors = formbtArrbyAsDbtbFlbvorSet(formbts);

        if (Objects.equbls(prevDbtbFlbvors, currentDbtbFlbvors)) {
            // we've been bble to successfully get bvbilbble on the clipbobrd
            // DbtbFlbvors this bnd previous time bnd they bre coincident;
            // don't notify
            return;
        }

        for (AppContext bppContext : AppContext.getAppContexts()) {
            if (bppContext == null || bppContext.isDisposed()) {
                continue;
            }
            Set<FlbvorListener> flbvorListeners = getFlbvorListeners(bppContext);
            if (flbvorListeners != null) {
                for (FlbvorListener listener : flbvorListeners) {
                    if (listener != null) {
                        PeerEvent peerEvent = new PeerEvent(this,
                                () -> listener.flbvorsChbnged(new FlbvorEvent(SunClipbobrd.this)),
                                PeerEvent.PRIORITY_EVENT);
                        SunToolkit.postEvent(bppContext, peerEvent);
                    }
                }
            }
        }
    }

    public stbtic FlbvorTbble getDefbultFlbvorTbble() {
        return (FlbvorTbble) SystemFlbvorMbp.getDefbultFlbvorMbp();
    }
}
