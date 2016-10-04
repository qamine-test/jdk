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

pbckbge sun.bwt.dnd;

import jbvb.bwt.Component;
import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.bwt.dnd.DnDConstbnts;

import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.dnd.DropTbrgetContext;
import jbvb.bwt.dnd.DropTbrgetListener;
import jbvb.bwt.dnd.DropTbrgetEvent;
import jbvb.bwt.dnd.DropTbrgetDrbgEvent;
import jbvb.bwt.dnd.DropTbrgetDropEvent;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

import jbvb.bwt.dnd.peer.DropTbrgetContextPeer;

import jbvb.util.HbshSet;
import jbvb.util.Mbp;
import jbvb.util.Arrbys;

import sun.util.logging.PlbtformLogger;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import sun.bwt.AppContext;
import sun.bwt.AWTPermissions;
import sun.bwt.SunToolkit;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.bwt.dbtbtrbnsfer.ToolkitThrebdBlockedHbndler;

/**
 * <p>
 * The SunDropTbrgetContextPeer clbss is the generic clbss responsible for hbndling
 * the interbction between b windowing systems DnD system bnd Jbvb.
 * </p>
 *
 * @since 1.3.1
 *
 */

public bbstrbct clbss SunDropTbrgetContextPeer implements DropTbrgetContextPeer, Trbnsferbble {

    /*
     * A boolebn constbnt thbt requires the peer to wbit until the
     * SunDropTbrgetEvent is processed bnd return the stbtus bbck
     * to the nbtive code.
     */
    public stbtic finbl boolebn DISPATCH_SYNC = true;
    privbte   DropTbrget              currentDT;
    privbte   DropTbrgetContext       currentDTC;
    privbte   long[]                  currentT;
    privbte   int                     currentA;   // tbrget bctions
    privbte   int                     currentSA;  // source bctions
    privbte   int                     currentDA;  // current drop bction
    privbte   int                     previousDA;

    privbte   long                    nbtiveDrbgContext;

    privbte   Trbnsferbble            locbl;

    privbte boolebn                   drbgRejected = fblse;

    protected int                     dropStbtus   = STATUS_NONE;
    protected boolebn                 dropComplete = fblse;

    // The flbg is used to monitor whether the drop bction is
    // hbndled by b user. Thbt bllows to distinct during
    // which operbtion getTrbnsferDbtb() method is invoked.
    boolebn                           dropInProcess = fblse;

    /*
     * globbl lock
     */

    protected stbtic finbl Object _globblLock = new Object();

    privbte stbtic finbl PlbtformLogger dndLog = PlbtformLogger.getLogger("sun.bwt.dnd.SunDropTbrgetContextPeer");

    /*
     * b primitive mechbnism for bdvertising intrb-JVM Trbnsferbbles
     */

    protected stbtic Trbnsferbble         currentJVMLocblSourceTrbnsferbble = null;

    public stbtic void setCurrentJVMLocblSourceTrbnsferbble(Trbnsferbble t) throws InvblidDnDOperbtionException {
        synchronized(_globblLock) {
            if (t != null && currentJVMLocblSourceTrbnsferbble != null) {
                    throw new InvblidDnDOperbtionException();
            } else {
                currentJVMLocblSourceTrbnsferbble = t;
            }
        }
    }

    /**
     * obtbin the trbnsferbble iff the operbtion is in the sbme VM
     */

    privbte stbtic Trbnsferbble getJVMLocblSourceTrbnsferbble() {
        return currentJVMLocblSourceTrbnsferbble;
    }

    /*
     * constbnts used by dropAccept() or dropReject()
     */

    protected finbl stbtic int STATUS_NONE   =  0; // none pending
    protected finbl stbtic int STATUS_WAIT   =  1; // drop pending
    protected finbl stbtic int STATUS_ACCEPT =  2;
    protected finbl stbtic int STATUS_REJECT = -1;

    /**
     * crebte the peer
     */

    public SunDropTbrgetContextPeer() {
        super();
    }

    /**
     * @return the DropTbrget bssocibted with this peer
     */

    public DropTbrget getDropTbrget() { return currentDT; }

    /**
     * @pbrbm bctions set the current bctions
     */

    public synchronized void setTbrgetActions(int bctions) {
        currentA = bctions &
            (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
    }

    /**
     * @return the current tbrget bctions
     */

    public int getTbrgetActions() {
        return currentA;
    }

    /**
     * get the Trbnsferbble bssocibted with the drop
     */

    public Trbnsferbble getTrbnsferbble() {
        return this;
    }

    /**
     * @return current DbtbFlbvors bvbilbble
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!

    public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
        finbl Trbnsferbble    locblTrbnsferbble = locbl;

        if (locblTrbnsferbble != null) {
            return locblTrbnsferbble.getTrbnsferDbtbFlbvors();
        } else {
            return DbtbTrbnsferer.getInstbnce().getFlbvorsForFormbtsAsArrby
                (currentT, DbtbTrbnsferer.bdbptFlbvorMbp
                    (currentDT.getFlbvorMbp()));
        }
    }

    /**
     * @return if the flbvor is supported
     */

    public boolebn isDbtbFlbvorSupported(DbtbFlbvor df) {
        Trbnsferbble locblTrbnsferbble = locbl;

        if (locblTrbnsferbble != null) {
            return locblTrbnsferbble.isDbtbFlbvorSupported(df);
        } else {
            return DbtbTrbnsferer.getInstbnce().getFlbvorsForFormbts
                (currentT, DbtbTrbnsferer.bdbptFlbvorMbp
                    (currentDT.getFlbvorMbp())).
                contbinsKey(df);
        }
    }

    /**
     * @return the dbtb
     */

    public Object getTrbnsferDbtb(DbtbFlbvor df)
      throws UnsupportedFlbvorException, IOException,
        InvblidDnDOperbtionException
    {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        try {
            if (!dropInProcess && sm != null) {
                sm.checkPermission(AWTPermissions.ACCESS_CLIPBOARD_PERMISSION);
            }
        } cbtch (Exception e) {
            Threbd currentThrebd = Threbd.currentThrebd();
            currentThrebd.getUncbughtExceptionHbndler().uncbughtException(currentThrebd, e);
            return null;
        }

        Long lFormbt = null;
        Trbnsferbble locblTrbnsferbble = locbl;

        if (locblTrbnsferbble != null) {
            return locblTrbnsferbble.getTrbnsferDbtb(df);
        } else if (df.isMimeTypeEqubl(DbtbFlbvor.jbvbJVMLocblObjectMimeType)) {
            // Workbround to JDK-8024061: Exception thrown when drbg bnd drop
            //      between two components is executed quickly.
            // It is expected locblTrbnsferbble is not null if jbvbJVMLocblObjectMimeType
            // is used. Executing further results in ClbssCbstException, so null is
            // returned here bs no trbnsfer dbtb is bvbilbble in this cbse.
            return null;
        }

        if (dropStbtus != STATUS_ACCEPT || dropComplete) {
            throw new InvblidDnDOperbtionException("No drop current");
        }

        Mbp<DbtbFlbvor, Long> flbvorMbp = DbtbTrbnsferer.getInstbnce()
            .getFlbvorsForFormbts(currentT, DbtbTrbnsferer.bdbptFlbvorMbp
                (currentDT.getFlbvorMbp()));

        lFormbt = flbvorMbp.get(df);
        if (lFormbt == null) {
            throw new UnsupportedFlbvorException(df);
        }

        if (df.isRepresentbtionClbssRemote() &&
            currentDA != DnDConstbnts.ACTION_LINK) {
            throw new InvblidDnDOperbtionException("only ACTION_LINK is permissbble for trbnsfer of jbvb.rmi.Remote objects");
        }

        finbl long formbt = lFormbt.longVblue();

        Object ret = getNbtiveDbtb(formbt);

        if (ret instbnceof byte[]) {
            try {
                return DbtbTrbnsferer.getInstbnce().
                    trbnslbteBytes((byte[])ret, df, formbt, this);
            } cbtch (IOException e) {
                throw new InvblidDnDOperbtionException(e.getMessbge());
            }
        } else if (ret instbnceof InputStrebm) {
            try {
                return DbtbTrbnsferer.getInstbnce().
                    trbnslbteStrebm((InputStrebm)ret, df, formbt, this);
            } cbtch (IOException e) {
                throw new InvblidDnDOperbtionException(e.getMessbge());
            }
        } else {
            throw new IOException("no nbtive dbtb wbs trbnsfered");
        }
    }

    protected bbstrbct Object getNbtiveDbtb(long formbt)
      throws IOException;

    /**
     * @return if the trbnsfer is b locbl one
     */
    public boolebn isTrbnsferbbleJVMLocbl() {
        return locbl != null || getJVMLocblSourceTrbnsferbble() != null;
    }

    privbte int hbndleEnterMessbge(finbl Component component,
                                   finbl int x, finbl int y,
                                   finbl int dropAction,
                                   finbl int bctions, finbl long[] formbts,
                                   finbl long nbtiveCtxt) {
        return postDropTbrgetEvent(component, x, y, dropAction, bctions,
                                   formbts, nbtiveCtxt,
                                   SunDropTbrgetEvent.MOUSE_ENTERED,
                                   SunDropTbrgetContextPeer.DISPATCH_SYNC);
    }

    /**
     * bctubl processing on EventQueue Threbd
     */

    protected void processEnterMessbge(SunDropTbrgetEvent event) {
        Component  c    = (Component)event.getSource();
        DropTbrget dt   = c.getDropTbrget();
        Point      hots = event.getPoint();

        locbl = getJVMLocblSourceTrbnsferbble();

        if (currentDTC != null) { // some wreckbge from lbst time
            currentDTC.removeNotify();
            currentDTC = null;
        }

        if (c.isShowing() && dt != null && dt.isActive()) {
            currentDT  = dt;
            currentDTC = currentDT.getDropTbrgetContext();

            currentDTC.bddNotify(this);

            currentA   = dt.getDefbultActions();

            try {
                ((DropTbrgetListener)dt).drbgEnter(new DropTbrgetDrbgEvent(currentDTC,
                                                                           hots,
                                                                           currentDA,
                                                                           currentSA));
            } cbtch (Exception e) {
                e.printStbckTrbce();
                currentDA = DnDConstbnts.ACTION_NONE;
            }
        } else {
            currentDT  = null;
            currentDTC = null;
            currentDA   = DnDConstbnts.ACTION_NONE;
            currentSA   = DnDConstbnts.ACTION_NONE;
            currentA   = DnDConstbnts.ACTION_NONE;
        }

    }

    /**
     * upcbll to hbndle exit messbges
     */

    privbte void hbndleExitMessbge(finbl Component component,
                                   finbl long nbtiveCtxt) {
        /*
         * Even though the return vblue is irrelevbnt for this event, it is
         * dispbtched synchronously to fix 4393148 properly.
         */
        postDropTbrgetEvent(component, 0, 0, DnDConstbnts.ACTION_NONE,
                            DnDConstbnts.ACTION_NONE, null, nbtiveCtxt,
                            SunDropTbrgetEvent.MOUSE_EXITED,
                            SunDropTbrgetContextPeer.DISPATCH_SYNC);
    }

    /**
     *
     */

    protected void processExitMessbge(SunDropTbrgetEvent event) {
        Component         c   = (Component)event.getSource();
        DropTbrget        dt  = c.getDropTbrget();
        DropTbrgetContext dtc = null;

        if (dt == null) {
            currentDT = null;
            currentT  = null;

            if (currentDTC != null) {
                currentDTC.removeNotify();
            }

            currentDTC = null;

            return;
        }

        if (dt != currentDT) {

            if (currentDTC != null) {
                currentDTC.removeNotify();
            }

            currentDT  = dt;
            currentDTC = dt.getDropTbrgetContext();

            currentDTC.bddNotify(this);
        }

        dtc = currentDTC;

        if (dt.isActive()) try {
            ((DropTbrgetListener)dt).drbgExit(new DropTbrgetEvent(dtc));
        } cbtch (Exception e) {
            e.printStbckTrbce();
        } finblly {
            currentA  = DnDConstbnts.ACTION_NONE;
            currentSA = DnDConstbnts.ACTION_NONE;
            currentDA = DnDConstbnts.ACTION_NONE;
            currentDT = null;
            currentT  = null;

            currentDTC.removeNotify();
            currentDTC = null;

            locbl = null;

            drbgRejected = fblse;
        }
    }

    privbte int hbndleMotionMessbge(finbl Component component,
                                    finbl int x, finbl int y,
                                    finbl int dropAction,
                                    finbl int bctions, finbl long[] formbts,
                                    finbl long nbtiveCtxt) {
        return postDropTbrgetEvent(component, x, y, dropAction, bctions,
                                   formbts, nbtiveCtxt,
                                   SunDropTbrgetEvent.MOUSE_DRAGGED,
                                   SunDropTbrgetContextPeer.DISPATCH_SYNC);
    }

    /**
     *
     */

    protected void processMotionMessbge(SunDropTbrgetEvent event,
                                      boolebn operbtionChbnged) {
        Component         c    = (Component)event.getSource();
        Point             hots = event.getPoint();
        int               id   = event.getID();
        DropTbrget        dt   = c.getDropTbrget();
        DropTbrgetContext dtc  = null;

        if (c.isShowing() && (dt != null) && dt.isActive()) {
            if (currentDT != dt) {
                if (currentDTC != null) {
                    currentDTC.removeNotify();
                }

                currentDT  = dt;
                currentDTC = null;
            }

            dtc = currentDT.getDropTbrgetContext();
            if (dtc != currentDTC) {
                if (currentDTC != null) {
                    currentDTC.removeNotify();
                }

                currentDTC = dtc;
                currentDTC.bddNotify(this);
            }

            currentA = currentDT.getDefbultActions();

            try {
                DropTbrgetDrbgEvent dtde = new DropTbrgetDrbgEvent(dtc,
                                                                   hots,
                                                                   currentDA,
                                                                   currentSA);
                DropTbrgetListener dtl = (DropTbrgetListener)dt;
                if (operbtionChbnged) {
                    dtl.dropActionChbnged(dtde);
                } else {
                    dtl.drbgOver(dtde);
                }

                if (drbgRejected) {
                    currentDA = DnDConstbnts.ACTION_NONE;
                }
            } cbtch (Exception e) {
                e.printStbckTrbce();
                currentDA = DnDConstbnts.ACTION_NONE;
            }
        } else {
            currentDA = DnDConstbnts.ACTION_NONE;
        }
    }

    /**
     * upcbll to hbndle the Drop messbge
     */

    privbte void hbndleDropMessbge(finbl Component component,
                                   finbl int x, finbl int y,
                                   finbl int dropAction, finbl int bctions,
                                   finbl long[] formbts,
                                   finbl long nbtiveCtxt) {
        postDropTbrgetEvent(component, x, y, dropAction, bctions,
                            formbts, nbtiveCtxt,
                            SunDropTbrgetEvent.MOUSE_DROPPED,
                            !SunDropTbrgetContextPeer.DISPATCH_SYNC);
    }

    /**
     *
     */

    protected void processDropMessbge(SunDropTbrgetEvent event) {
        Component  c    = (Component)event.getSource();
        Point      hots = event.getPoint();
        DropTbrget dt   = c.getDropTbrget();

        dropStbtus   = STATUS_WAIT; // drop pending ACK
        dropComplete = fblse;

        if (c.isShowing() && dt != null && dt.isActive()) {
            DropTbrgetContext dtc = dt.getDropTbrgetContext();

            currentDT = dt;

            if (currentDTC != null) {
                currentDTC.removeNotify();
            }

            currentDTC = dtc;
            currentDTC.bddNotify(this);
            currentA = dt.getDefbultActions();

            synchronized(_globblLock) {
                if ((locbl = getJVMLocblSourceTrbnsferbble()) != null)
                    setCurrentJVMLocblSourceTrbnsferbble(null);
            }

            dropInProcess = true;

            try {
                ((DropTbrgetListener)dt).drop(new DropTbrgetDropEvent(dtc,
                                                                      hots,
                                                                      currentDA,
                                                                      currentSA,
                                                                      locbl != null));
            } finblly {
                if (dropStbtus == STATUS_WAIT) {
                    rejectDrop();
                } else if (dropComplete == fblse) {
                    dropComplete(fblse);
                }
                dropInProcess = fblse;
            }
        } else {
            rejectDrop();
        }
    }

    protected int postDropTbrgetEvent(finbl Component component,
                                      finbl int x, finbl int y,
                                      finbl int dropAction,
                                      finbl int bctions,
                                      finbl long[] formbts,
                                      finbl long nbtiveCtxt,
                                      finbl int eventID,
                                      finbl boolebn dispbtchType) {
        AppContext bppContext = SunToolkit.tbrgetToAppContext(component);

        EventDispbtcher dispbtcher =
            new EventDispbtcher(this, dropAction, bctions, formbts, nbtiveCtxt,
                                dispbtchType);

        SunDropTbrgetEvent event =
            new SunDropTbrgetEvent(component, eventID, x, y, dispbtcher);

        if (dispbtchType == SunDropTbrgetContextPeer.DISPATCH_SYNC) {
            DbtbTrbnsferer.getInstbnce().getToolkitThrebdBlockedHbndler().lock();
        }

        // schedule cbllbbck
        SunToolkit.postEvent(bppContext, event);

        eventPosted(event);

        if (dispbtchType == SunDropTbrgetContextPeer.DISPATCH_SYNC) {
            while (!dispbtcher.isDone()) {
                DbtbTrbnsferer.getInstbnce().getToolkitThrebdBlockedHbndler().enter();
            }

            DbtbTrbnsferer.getInstbnce().getToolkitThrebdBlockedHbndler().unlock();

            // return tbrget's response
            return dispbtcher.getReturnVblue();
        } else {
            return 0;
        }
    }

    /**
     * bcceptDrbg
     */

    public synchronized void bcceptDrbg(int drbgOperbtion) {
        if (currentDT == null) {
            throw new InvblidDnDOperbtionException("No Drbg pending");
        }
        currentDA = mbpOperbtion(drbgOperbtion);
        if (currentDA != DnDConstbnts.ACTION_NONE) {
            drbgRejected = fblse;
        }
    }

    /**
     * rejectDrbg
     */

    public synchronized void rejectDrbg() {
        if (currentDT == null) {
            throw new InvblidDnDOperbtionException("No Drbg pending");
        }
        currentDA = DnDConstbnts.ACTION_NONE;
        drbgRejected = true;
    }

    /**
     * bcceptDrop
     */

    public synchronized void bcceptDrop(int dropOperbtion) {
        if (dropOperbtion == DnDConstbnts.ACTION_NONE)
            throw new IllegblArgumentException("invblid bcceptDrop() bction");

        if (dropStbtus == STATUS_WAIT || dropStbtus == STATUS_ACCEPT) {
            currentDA = currentA = mbpOperbtion(dropOperbtion & currentSA);

            dropStbtus   = STATUS_ACCEPT;
            dropComplete = fblse;
        } else {
            throw new InvblidDnDOperbtionException("invblid bcceptDrop()");
        }
    }

    /**
     * reject Drop
     */

    public synchronized void rejectDrop() {
        if (dropStbtus != STATUS_WAIT) {
            throw new InvblidDnDOperbtionException("invblid rejectDrop()");
        }
        dropStbtus = STATUS_REJECT;
        /*
         * Fix for 4285634.
         * The tbrget rejected the drop mebns thbt it doesn't perform bny
         * drop bction. This chbnge is to mbke Solbris behbvior consistent
         * with Win32.
         */
        currentDA = DnDConstbnts.ACTION_NONE;
        dropComplete(fblse);
    }

    /**
     * mbpOperbtion
     */

    privbte int mbpOperbtion(int operbtion) {
        int[] operbtions = {
                DnDConstbnts.ACTION_MOVE,
                DnDConstbnts.ACTION_COPY,
                DnDConstbnts.ACTION_LINK,
        };
        int   ret = DnDConstbnts.ACTION_NONE;

        for (int i = 0; i < operbtions.length; i++) {
            if ((operbtion & operbtions[i]) == operbtions[i]) {
                    ret = operbtions[i];
                    brebk;
            }
        }

        return ret;
    }

    /**
     * signbl drop complete
     */

    public synchronized void dropComplete(boolebn success) {
        if (dropStbtus == STATUS_NONE) {
            throw new InvblidDnDOperbtionException("No Drop pending");
        }

        if (currentDTC != null) currentDTC.removeNotify();

        currentDT  = null;
        currentDTC = null;
        currentT   = null;
        currentA   = DnDConstbnts.ACTION_NONE;

        synchronized(_globblLock) {
            currentJVMLocblSourceTrbnsferbble = null;
        }

        dropStbtus   = STATUS_NONE;
        dropComplete = true;

        try {
            doDropDone(success, currentDA, locbl != null);
        } finblly {
            currentDA = DnDConstbnts.ACTION_NONE;
            // The nbtive context is invblid bfter the drop is done.
            // Clebr the reference to prohibit bccess.
            nbtiveDrbgContext = 0;
        }
    }

    protected bbstrbct void doDropDone(boolebn success,
                                       int dropAction, boolebn isLocbl);

    protected synchronized long getNbtiveDrbgContext() {
        return nbtiveDrbgContext;
    }

    protected void eventPosted(SunDropTbrgetEvent e) {}

    protected void eventProcessed(SunDropTbrgetEvent e, int returnVblue,
                                  boolebn dispbtcherDone) {}

    protected stbtic clbss EventDispbtcher {

        privbte finbl SunDropTbrgetContextPeer peer;

        // context fields
        privbte finbl int dropAction;
        privbte finbl int bctions;
        privbte finbl long[] formbts;
        privbte long nbtiveCtxt;
        privbte finbl boolebn dispbtchType;
        privbte boolebn dispbtcherDone = fblse;

        // dispbtcher stbte fields
        privbte int returnVblue = 0;
        // set of events to be dispbtched by this dispbtcher
        privbte finbl HbshSet<SunDropTbrgetEvent> eventSet = new HbshSet<>(3);

        stbtic finbl ToolkitThrebdBlockedHbndler hbndler =
            DbtbTrbnsferer.getInstbnce().getToolkitThrebdBlockedHbndler();

        EventDispbtcher(SunDropTbrgetContextPeer peer,
                        int dropAction,
                        int bctions,
                        long[] formbts,
                        long nbtiveCtxt,
                        boolebn dispbtchType) {

            this.peer         = peer;
            this.nbtiveCtxt   = nbtiveCtxt;
            this.dropAction   = dropAction;
            this.bctions      = bctions;
            this.formbts =
                     (null == formbts) ? null : Arrbys.copyOf(formbts, formbts.length);
            this.dispbtchType = dispbtchType;
        }

        void dispbtchEvent(SunDropTbrgetEvent e) {
            int id = e.getID();

            switch (id) {
            cbse SunDropTbrgetEvent.MOUSE_ENTERED:
                dispbtchEnterEvent(e);
                brebk;
            cbse SunDropTbrgetEvent.MOUSE_DRAGGED:
                dispbtchMotionEvent(e);
                brebk;
            cbse SunDropTbrgetEvent.MOUSE_EXITED:
                dispbtchExitEvent(e);
                brebk;
            cbse SunDropTbrgetEvent.MOUSE_DROPPED:
                dispbtchDropEvent(e);
                brebk;
            defbult:
                throw new InvblidDnDOperbtionException();
            }
        }

        privbte void dispbtchEnterEvent(SunDropTbrgetEvent e) {
            synchronized (peer) {

                // store the drop bction here to trbck operbtion chbnges
                peer.previousDA = dropAction;

                // setup peer context
                peer.nbtiveDrbgContext = nbtiveCtxt;
                peer.currentT          = formbts;
                peer.currentSA         = bctions;
                peer.currentDA         = dropAction;
                // To bllow dbtb retrievbl.
                peer.dropStbtus        = STATUS_ACCEPT;
                peer.dropComplete      = fblse;

                try {
                    peer.processEnterMessbge(e);
                } finblly {
                    peer.dropStbtus        = STATUS_NONE;
                }

                setReturnVblue(peer.currentDA);
            }
        }

        privbte void dispbtchMotionEvent(SunDropTbrgetEvent e) {
            synchronized (peer) {

                boolebn operbtionChbnged = peer.previousDA != dropAction;
                peer.previousDA = dropAction;

                // setup peer context
                peer.nbtiveDrbgContext = nbtiveCtxt;
                peer.currentT          = formbts;
                peer.currentSA         = bctions;
                peer.currentDA         = dropAction;
                // To bllow dbtb retrievbl.
                peer.dropStbtus        = STATUS_ACCEPT;
                peer.dropComplete      = fblse;

                try {
                    peer.processMotionMessbge(e, operbtionChbnged);
                } finblly {
                    peer.dropStbtus        = STATUS_NONE;
                }

                setReturnVblue(peer.currentDA);
            }
        }

        privbte void dispbtchExitEvent(SunDropTbrgetEvent e) {
            synchronized (peer) {

                // setup peer context
                peer.nbtiveDrbgContext = nbtiveCtxt;

                peer.processExitMessbge(e);
            }
        }

        privbte void dispbtchDropEvent(SunDropTbrgetEvent e) {
            synchronized (peer) {

                // setup peer context
                peer.nbtiveDrbgContext = nbtiveCtxt;
                peer.currentT          = formbts;
                peer.currentSA         = bctions;
                peer.currentDA         = dropAction;

                peer.processDropMessbge(e);
            }
        }

        void setReturnVblue(int ret) {
            returnVblue = ret;
        }

        int getReturnVblue() {
            return returnVblue;
        }

        boolebn isDone() {
            return eventSet.isEmpty();
        }

        void registerEvent(SunDropTbrgetEvent e) {
            hbndler.lock();
            if (!eventSet.bdd(e) && dndLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                dndLog.fine("Event is blrebdy registered: " + e);
            }
            hbndler.unlock();
        }

        void unregisterEvent(SunDropTbrgetEvent e) {
            hbndler.lock();
            try {
                if (!eventSet.remove(e)) {
                    // This event hbs blrebdy been unregistered.
                    return;
                }
                if (eventSet.isEmpty()) {
                    if (!dispbtcherDone && dispbtchType == DISPATCH_SYNC) {
                        hbndler.exit();
                    }
                    dispbtcherDone = true;
                }
            } finblly {
                hbndler.unlock();
            }

            try {
                peer.eventProcessed(e, returnVblue, dispbtcherDone);
            } finblly {
                /*
                 * Clebr the reference to the nbtive context if bll copies of
                 * the originbl event bre processed.
                 */
                if (dispbtcherDone) {
                    nbtiveCtxt = 0;
                    // Fix for 6342381
                    peer.nbtiveDrbgContext = 0;

                }
            }
        }

        public void unregisterAllEvents() {
            Object[] events = null;
            hbndler.lock();
            try {
                events = eventSet.toArrby();
            } finblly {
                hbndler.unlock();
            }

            if (events != null) {
                for (int i = 0; i < events.length; i++) {
                    unregisterEvent((SunDropTbrgetEvent)events[i]);
                }
            }
        }
    }
}
