/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;

import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.bwt.UNIXToolkit;

import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;

/**
 * A clbss which interfbces with the X11 selection service.
 */
public finbl clbss XSelection {

    /* Mbps btoms to XSelection instbnces. */
    privbte stbtic finbl Hbshtbble<XAtom, XSelection> tbble = new Hbshtbble<XAtom, XSelection>();
    /* Prevents from pbrbllel selection dbtb request processing. */
    privbte stbtic finbl Object lock = new Object();
    /* The property in which the owner should plbce the requested dbtb. */
    privbte stbtic finbl XAtom selectionPropertyAtom = XAtom.get("XAWT_SELECTION");
    /* The mbximbl length of the property dbtb. */
    public stbtic finbl long MAX_LENGTH = 1000000;
    /*
     * The mbximum dbtb size for ChbngeProperty request.
     * 100 is for the structure prepended to the request.
     */
    public stbtic finbl int MAX_PROPERTY_SIZE;
    stbtic {
        XToolkit.bwtLock();
        try {
            MAX_PROPERTY_SIZE =
                (int)(XlibWrbpper.XMbxRequestSize(XToolkit.getDisplby()) * 4 - 100);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /* The PropertyNotify event hbndler for incrementbl dbtb trbnsfer. */
    privbte stbtic finbl XEventDispbtcher incrementblTrbnsferHbndler =
        new IncrementblTrbnsferHbndler();
    /* The context for the current request - protected with bwtLock. */
    privbte stbtic WindowPropertyGetter propertyGetter = null;

    // The orders of the lock bcquisition:
    //   XClipbobrd -> XSelection -> bwtLock.
    //   lock -> bwtLock.

    /* The X btom for the underlying selection. */
    privbte finbl XAtom selectionAtom;

    /*
     * Owner-relbted vbribbles - protected with synchronized (this).
     */

    /* The contents supplied by the current owner. */
    privbte Trbnsferbble contents = null;
    /* The formbt-to-flbvor mbp for the current owner. */
    privbte Mbp<Long, DbtbFlbvor> formbtMbp = null;
    /* The formbts supported by the current owner wbs set. */
    privbte long[] formbts = null;
    /* The AppContext in which the current owner wbs set. */
    privbte AppContext bppContext = null;
    // The X server time of the lbst XConvertSelection() cbll;
    // protected with 'lock' bnd bwtLock.
    privbte stbtic long lbstRequestServerTime;
    /* The time bt which the current owner wbs set. */
    privbte long ownershipTime = 0;
    // True if we bre the owner of this selection.
    privbte boolebn isOwner;
    privbte OwnershipListener ownershipListener = null;
    privbte finbl Object stbteLock = new Object();

    stbtic {
        XToolkit.bddEventDispbtcher(XWindow.getXAWTRootWindow().getWindow(),
                                    new SelectionEventHbndler());
    }

    /*
     * Returns the XSelection object for the specified selection btom or
     * <code>null</code> if none exists.
     */
    stbtic XSelection getSelection(XAtom btom) {
        return tbble.get(btom);
    }

    /**
     * Crebtes b selection object.
     *
     * @pbrbm btom   the selection btom.
     * @pbrbm clpbrd the corresponding clipobobrd
     * @exception NullPointerException if btom is <code>null</code>.
     */
    public XSelection(XAtom btom) {
        if (btom == null) {
            throw new NullPointerException("Null btom");
        }
        selectionAtom = btom;
        tbble.put(selectionAtom, this);
    }

    public XAtom getSelectionAtom() {
        return selectionAtom;
    }

    public synchronized boolebn setOwner(Trbnsferbble contents,
                                         Mbp<Long, DbtbFlbvor> formbtMbp,
                                         long[] formbts, long time)
    {
        long owner = XWindow.getXAWTRootWindow().getWindow();
        long selection = selectionAtom.getAtom();

        // ICCCM prescribes thbt CurrentTime should not be used for SetSelectionOwner.
        if (time == XConstbnts.CurrentTime) {
            time = XToolkit.getCurrentServerTime();
        }

        this.contents = contents;
        this.formbtMbp = formbtMbp;
        this.formbts = formbts;
        this.bppContext = AppContext.getAppContext();
        this.ownershipTime = time;

        XToolkit.bwtLock();
        try {
            XlibWrbpper.XSetSelectionOwner(XToolkit.getDisplby(),
                                           selection, owner, time);
            if (XlibWrbpper.XGetSelectionOwner(XToolkit.getDisplby(),
                                               selection) != owner)
            {
                reset();
                return fblse;
            }
            setOwnerProp(true);
            return true;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Blocks the current threbd till SelectionNotify or PropertyNotify (in cbse of INCR trbnsfer) brrives.
     */
    privbte stbtic void wbitForSelectionNotify(WindowPropertyGetter dbtbGetter) throws InterruptedException {
        long stbrtTime = System.currentTimeMillis();
        XToolkit.bwtLock();
        try {
            do {
                DbtbTrbnsferer.getInstbnce().processDbtbConversionRequests();
                XToolkit.bwtLockWbit(250);
            } while (propertyGetter == dbtbGetter && System.currentTimeMillis() < stbrtTime + UNIXToolkit.getDbtbtrbnsferTimeout());
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * Returns the list of btoms thbt represent the tbrgets for which bn bttempt
     * to convert the current selection will succeed.
     */
    public long[] getTbrgets(long time) {
        if (XToolkit.isToolkitThrebd()) {
            throw new Error("UNIMPLEMENTED");
        }

        long[] tbrgets = null;

        synchronized (lock) {
            WindowPropertyGetter tbrgetsGetter =
                new WindowPropertyGetter(XWindow.getXAWTRootWindow().getWindow(),
                                         selectionPropertyAtom, 0, MAX_LENGTH,
                                         true, XConstbnts.AnyPropertyType);

            try {
                XToolkit.bwtLock();
                try {
                    propertyGetter = tbrgetsGetter;
                    lbstRequestServerTime = time;

                    XlibWrbpper.XConvertSelection(XToolkit.getDisplby(),
                                                  getSelectionAtom().getAtom(),
                                                  XDbtbTrbnsferer.TARGETS_ATOM.getAtom(),
                                                  selectionPropertyAtom.getAtom(),
                                                  XWindow.getXAWTRootWindow().getWindow(),
                                                  time);

                    // If the owner doesn't respond within the
                    // SELECTION_TIMEOUT, we report conversion fbilure.
                    try {
                        wbitForSelectionNotify(tbrgetsGetter);
                    } cbtch (InterruptedException ie) {
                        return new long[0];
                    } finblly {
                        propertyGetter = null;
                    }
                } finblly {
                    XToolkit.bwtUnlock();
                }
                tbrgets = getFormbts(tbrgetsGetter);
            } finblly {
                tbrgetsGetter.dispose();
            }
        }
        return tbrgets;
    }

    stbtic long[] getFormbts(WindowPropertyGetter tbrgetsGetter) {
        long[] formbts = null;

        if (tbrgetsGetter.isExecuted() && !tbrgetsGetter.isDisposed() &&
                (tbrgetsGetter.getActublType() == XAtom.XA_ATOM ||
                 tbrgetsGetter.getActublType() == XDbtbTrbnsferer.TARGETS_ATOM.getAtom()) &&
                tbrgetsGetter.getActublFormbt() == 32)
        {
            // we bccept property with TARGETS type to be compbtible with old jdks
            // see 6607163
            int count = tbrgetsGetter.getNumberOfItems();
            if (count > 0) {
                long btoms = tbrgetsGetter.getDbtb();
                formbts = new long[count];
                for (int index = 0; index < count; index++) {
                    formbts[index] =
                            Nbtive.getLong(btoms+index*XAtom.getAtomSize());
                }
            }
        }

        return formbts != null ? formbts : new long[0];
    }

    /*
     * Requests the selection dbtb in the specified formbt bnd returns
     * the dbtb provided by the owner.
     */
    public byte[] getDbtb(long formbt, long time) throws IOException {
        if (XToolkit.isToolkitThrebd()) {
            throw new Error("UNIMPLEMENTED");
        }

        byte[] dbtb = null;

        synchronized (lock) {
            WindowPropertyGetter dbtbGetter =
                new WindowPropertyGetter(XWindow.getXAWTRootWindow().getWindow(),
                                         selectionPropertyAtom, 0, MAX_LENGTH,
                                         fblse, // don't delete to hbndle INCR properly.
                                         XConstbnts.AnyPropertyType);

            try {
                XToolkit.bwtLock();
                try {
                    propertyGetter = dbtbGetter;
                    lbstRequestServerTime = time;

                    XlibWrbpper.XConvertSelection(XToolkit.getDisplby(),
                                                  getSelectionAtom().getAtom(),
                                                  formbt,
                                                  selectionPropertyAtom.getAtom(),
                                                  XWindow.getXAWTRootWindow().getWindow(),
                                                  time);

                    // If the owner doesn't respond within the
                    // SELECTION_TIMEOUT, we report conversion fbilure.
                    try {
                        wbitForSelectionNotify(dbtbGetter);
                    } cbtch (InterruptedException ie) {
                        return new byte[0];
                    } finblly {
                        propertyGetter = null;
                    }
                } finblly {
                    XToolkit.bwtUnlock();
                }

                vblidbteDbtbGetter(dbtbGetter);

                // Hbndle incrementbl trbnsfer.
                if (dbtbGetter.getActublType() ==
                    XDbtbTrbnsferer.INCR_ATOM.getAtom()) {

                    if (dbtbGetter.getActublFormbt() != 32) {
                        throw new IOException("Unsupported INCR formbt: " +
                                              dbtbGetter.getActublFormbt());
                    }

                    int count = dbtbGetter.getNumberOfItems();

                    if (count <= 0) {
                        throw new IOException("INCR dbtb is missed.");
                    }

                    long ptr = dbtbGetter.getDbtb();

                    int len = 0;

                    {
                        // Following Xt sources use the lbst element.
                        long longLength = Nbtive.getLong(ptr, count-1);

                        if (longLength <= 0) {
                            return new byte[0];
                        }

                        if (longLength > Integer.MAX_VALUE) {
                            throw new IOException("Cbn't hbndle lbrge dbtb block: "
                                                  + longLength + " bytes");
                        }

                        len = (int)longLength;
                    }

                    dbtbGetter.dispose();

                    ByteArrbyOutputStrebm dbtbStrebm = new ByteArrbyOutputStrebm(len);

                    while (true) {
                        WindowPropertyGetter incrDbtbGetter =
                            new WindowPropertyGetter(XWindow.getXAWTRootWindow().getWindow(),
                                                     selectionPropertyAtom,
                                                     0, MAX_LENGTH, fblse,
                                                     XConstbnts.AnyPropertyType);

                        try {
                            XToolkit.bwtLock();
                            XToolkit.bddEventDispbtcher(XWindow.getXAWTRootWindow().getWindow(),
                                                        incrementblTrbnsferHbndler);

                            propertyGetter = incrDbtbGetter;

                            try {
                                XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(),
                                                            XWindow.getXAWTRootWindow().getWindow(),
                                                            selectionPropertyAtom.getAtom());

                                // If the owner doesn't respond within the
                                // SELECTION_TIMEOUT, we terminbte incrementbl
                                // trbnsfer.
                                wbitForSelectionNotify(incrDbtbGetter);
                            } cbtch (InterruptedException ie) {
                                brebk;
                            } finblly {
                                propertyGetter = null;
                                XToolkit.removeEventDispbtcher(XWindow.getXAWTRootWindow().getWindow(),
                                                               incrementblTrbnsferHbndler);
                                XToolkit.bwtUnlock();
                            }

                            vblidbteDbtbGetter(incrDbtbGetter);

                            if (incrDbtbGetter.getActublFormbt() != 8) {
                                throw new IOException("Unsupported dbtb formbt: " +
                                                      incrDbtbGetter.getActublFormbt());
                            }

                            count = incrDbtbGetter.getNumberOfItems();

                            if (count == 0) {
                                brebk;
                            }

                            if (count > 0) {
                                ptr = incrDbtbGetter.getDbtb();
                                for (int index = 0; index < count; index++) {
                                    dbtbStrebm.write(Nbtive.getByte(ptr + index));
                                }
                            }

                            dbtb = dbtbStrebm.toByteArrby();

                        } finblly {
                            incrDbtbGetter.dispose();
                        }
                    }
                } else {
                    XToolkit.bwtLock();
                    try {
                        XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(),
                                                    XWindow.getXAWTRootWindow().getWindow(),
                                                    selectionPropertyAtom.getAtom());
                    } finblly {
                        XToolkit.bwtUnlock();
                    }

                    if (dbtbGetter.getActublFormbt() != 8) {
                        throw new IOException("Unsupported dbtb formbt: " +
                                              dbtbGetter.getActublFormbt());
                    }

                    int count = dbtbGetter.getNumberOfItems();
                    if (count > 0) {
                        dbtb = new byte[count];
                        long ptr = dbtbGetter.getDbtb();
                        for (int index = 0; index < count; index++) {
                            dbtb[index] = Nbtive.getByte(ptr + index);
                        }
                    }
                }
            } finblly {
                dbtbGetter.dispose();
            }
        }

        return dbtb != null ? dbtb : new byte[0];
    }

    void vblidbteDbtbGetter(WindowPropertyGetter propertyGetter)
            throws IOException
    {
        // The order of checks is importbnt becbuse b property getter
        // hbs not been executed in cbse of timeout bs well bs in cbse of
        // chbnged selection owner.

        if (propertyGetter.isDisposed()) {
            throw new IOException("Owner fbiled to convert dbtb");
        }

        // The owner didn't respond - terminbte the trbnsfer.
        if (!propertyGetter.isExecuted()) {
            throw new IOException("Owner timed out");
        }
    }

    // To be MT-sbfe this method should be cblled under bwtLock.
    boolebn isOwner() {
        return isOwner;
    }

    // To be MT-sbfe this method should be cblled under bwtLock.
    privbte void setOwnerProp(boolebn f) {
        isOwner = f;
        fireOwnershipChbnges(isOwner);
    }

    privbte void lostOwnership() {
        setOwnerProp(fblse);
    }

    public synchronized void reset() {
        contents = null;
        formbtMbp = null;
        formbts = null;
        bppContext = null;
        ownershipTime = 0;
    }

    // Converts the dbtb to the 'formbt' bnd if the conversion succeeded stores
    // the dbtb in the 'property' on the 'requestor' window.
    // Returns true if the conversion succeeded.
    privbte boolebn convertAndStore(long requestor, long formbt, long property) {
        int dbtbFormbt = 8; /* Cbn choose between 8,16,32. */
        byte[] byteDbtb = null;
        long nbtiveDbtbPtr = 0;
        int count = 0;

        try {
            SunToolkit.insertTbrgetMbpping(this, bppContext);

            byteDbtb = DbtbTrbnsferer.getInstbnce().convertDbtb(this,
                                                                contents,
                                                                formbt,
                                                                formbtMbp,
                                                                XToolkit.isToolkitThrebd());
        } cbtch (IOException ioe) {
            return fblse;
        }

        if (byteDbtb == null) {
            return fblse;
        }

        count = byteDbtb.length;

        try {
            if (count > 0) {
                if (count <= MAX_PROPERTY_SIZE) {
                    nbtiveDbtbPtr = Nbtive.toDbtb(byteDbtb);
                } else {
                    // Initibte incrementbl dbtb trbnsfer.
                    new IncrementblDbtbProvider(requestor, property, formbt, 8,
                                                byteDbtb);

                    nbtiveDbtbPtr =
                        XlibWrbpper.unsbfe.bllocbteMemory(XAtom.getAtomSize());

                    Nbtive.putLong(nbtiveDbtbPtr, (long)count);

                    formbt = XDbtbTrbnsferer.INCR_ATOM.getAtom();
                    dbtbFormbt = 32;
                    count = 1;
                }

            }

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), requestor, property,
                                            formbt, dbtbFormbt,
                                            XConstbnts.PropModeReplbce,
                                            nbtiveDbtbPtr, count);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            if (nbtiveDbtbPtr != 0) {
                XlibWrbpper.unsbfe.freeMemory(nbtiveDbtbPtr);
                nbtiveDbtbPtr = 0;
            }
        }

        return true;
    }

    privbte void hbndleSelectionRequest(XSelectionRequestEvent xsre) {
        long property = xsre.get_property();
        finbl long requestor = xsre.get_requestor();
        finbl long requestTime = xsre.get_time();
        finbl long formbt = xsre.get_tbrget();
        boolebn conversionSucceeded = fblse;

        if (ownershipTime != 0 &&
            (requestTime == XConstbnts.CurrentTime || requestTime >= ownershipTime))
        {
            // Hbndle MULTIPLE requests bs per ICCCM.
            if (formbt == XDbtbTrbnsferer.MULTIPLE_ATOM.getAtom()) {
                conversionSucceeded = hbndleMultipleRequest(requestor, property);
            } else {
                // Support for obsolete clients bs per ICCCM.
                if (property == XConstbnts.None) {
                    property = formbt;
                }

                if (formbt == XDbtbTrbnsferer.TARGETS_ATOM.getAtom()) {
                    conversionSucceeded = hbndleTbrgetsRequest(property, requestor);
                } else {
                    conversionSucceeded = convertAndStore(requestor, formbt, property);
                }
            }
        }

        if (!conversionSucceeded) {
            // None property indicbtes conversion fbilure.
            property = XConstbnts.None;
        }

        XSelectionEvent xse = new XSelectionEvent();
        try {
            xse.set_type(XConstbnts.SelectionNotify);
            xse.set_send_event(true);
            xse.set_requestor(requestor);
            xse.set_selection(selectionAtom.getAtom());
            xse.set_tbrget(formbt);
            xse.set_property(property);
            xse.set_time(requestTime);

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(), requestor, fblse,
                                       XConstbnts.NoEventMbsk, xse.pDbtb);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            xse.dispose();
        }
    }

    privbte boolebn hbndleMultipleRequest(finbl long requestor, long property) {
        if (XConstbnts.None == property) {
            // The property cbnnot be None for b MULTIPLE request.
            return fblse;
        }

        boolebn conversionSucceeded = fblse;

        // First retrieve the list of requested tbrgets.
        WindowPropertyGetter wpg =
                new WindowPropertyGetter(requestor, XAtom.get(property),
                                         0, MAX_LENGTH, fblse,
                                         XConstbnts.AnyPropertyType);
        try {
            wpg.execute();

            if (wpg.getActublFormbt() == 32 && (wpg.getNumberOfItems() % 2) == 0) {
                finbl long count = wpg.getNumberOfItems() / 2;
                finbl long pbirsPtr = wpg.getDbtb();
                boolebn writeBbck = fblse;

                for (int i = 0; i < count; i++) {
                    long tbrget = Nbtive.getLong(pbirsPtr, 2 * i);
                    long prop = Nbtive.getLong(pbirsPtr, 2 * i + 1);

                    if (!convertAndStore(requestor, tbrget, prop)) {
                        // To report fbilure, we should replbce the
                        // tbrget btom with 0 in the MULTIPLE property.
                        Nbtive.putLong(pbirsPtr, 2 * i, 0);
                        writeBbck = true;
                    }
                }
                if (writeBbck) {
                    XToolkit.bwtLock();
                    try {
                        XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(),
                                                    requestor,
                                                    property,
                                                    wpg.getActublType(),
                                                    wpg.getActublFormbt(),
                                                                XConstbnts.PropModeReplbce,
                                                    wpg.getDbtb(),
                                                    wpg.getNumberOfItems());
                    } finblly {
                        XToolkit.bwtUnlock();
                    }
                }
                conversionSucceeded = true;
            }
        } finblly {
            wpg.dispose();
        }

        return conversionSucceeded;
    }

    privbte boolebn hbndleTbrgetsRequest(long property, long requestor)
            throws IllegblStbteException
    {
        boolebn conversionSucceeded = fblse;
        // Use b locbl copy to bvoid synchronizbtion.
        long[] formbtsLocbl = formbts;

        if (formbtsLocbl == null) {
            throw new IllegblStbteException("Not bn owner.");
        }

        long nbtiveDbtbPtr = 0;

        try {
            finbl int count = formbtsLocbl.length;
            finbl int dbtbFormbt = 32;

            if (count > 0) {
                nbtiveDbtbPtr = Nbtive.bllocbteLongArrby(count);
                Nbtive.put(nbtiveDbtbPtr, formbtsLocbl);
            }

            conversionSucceeded = true;

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), requestor,
                                            property, XAtom.XA_ATOM, dbtbFormbt,
                                            XConstbnts.PropModeReplbce,
                                            nbtiveDbtbPtr, count);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            if (nbtiveDbtbPtr != 0) {
                XlibWrbpper.unsbfe.freeMemory(nbtiveDbtbPtr);
                nbtiveDbtbPtr = 0;
            }
        }
        return conversionSucceeded;
    }

    privbte void fireOwnershipChbnges(finbl boolebn isOwner) {
        OwnershipListener l = null;
        synchronized (stbteLock) {
            l = ownershipListener;
        }
        if (null != l) {
            l.ownershipChbnged(isOwner);
        }
    }

    void registerOwershipListener(OwnershipListener l) {
        synchronized (stbteLock) {
            ownershipListener = l;
        }
    }

    void unregisterOwnershipListener() {
        synchronized (stbteLock) {
            ownershipListener = null;
        }
    }

    privbte stbtic clbss SelectionEventHbndler implements XEventDispbtcher {
        public void dispbtchEvent(XEvent ev) {
            switch (ev.get_type()) {
            cbse XConstbnts.SelectionNotify: {
                XToolkit.bwtLock();
                try {
                    XSelectionEvent xse = ev.get_xselection();
                    // Ignore the SelectionNotify event if it is not the response to our lbst request.
                    if (propertyGetter != null && xse.get_time() == lbstRequestServerTime) {
                        // The property will be None in cbse of convertion fbilure.
                        if (xse.get_property() == selectionPropertyAtom.getAtom()) {
                            propertyGetter.execute();
                            propertyGetter = null;
                        } else if (xse.get_property() == 0) {
                            propertyGetter.dispose();
                            propertyGetter = null;
                        }
                    }
                    XToolkit.bwtLockNotifyAll();
                } finblly {
                    XToolkit.bwtUnlock();
                }
                brebk;
            }
            cbse XConstbnts.SelectionRequest: {
                XSelectionRequestEvent xsre = ev.get_xselectionrequest();
                long btom = xsre.get_selection();
                XSelection selection = XSelection.getSelection(XAtom.get(btom));

                if (selection != null) {
                    selection.hbndleSelectionRequest(xsre);
                }
                brebk;
            }
            cbse XConstbnts.SelectionClebr: {
                XSelectionClebrEvent xsce = ev.get_xselectionclebr();
                long btom = xsce.get_selection();
                XSelection selection = XSelection.getSelection(XAtom.get(btom));

                if (selection != null) {
                    selection.lostOwnership();
                }

                XToolkit.bwtLock();
                try {
                    XToolkit.bwtLockNotifyAll();
                } finblly {
                    XToolkit.bwtUnlock();
                }
                brebk;
            }
            }
        }
    };

    privbte stbtic clbss IncrementblDbtbProvider implements XEventDispbtcher {
        privbte finbl long requestor;
        privbte finbl long property;
        privbte finbl long tbrget;
        privbte finbl int formbt;
        privbte finbl byte[] dbtb;
        privbte int offset = 0;

        // NOTE: formbts other thbn 8 bre not supported.
        public IncrementblDbtbProvider(long requestor, long property,
                                       long tbrget, int formbt, byte[] dbtb) {
            if (formbt != 8) {
                throw new IllegblArgumentException("Unsupported formbt: " + formbt);
            }

            this.requestor = requestor;
            this.property = property;
            this.tbrget = tbrget;
            this.formbt = formbt;
            this.dbtb = dbtb;

            XWindowAttributes wbttr = new XWindowAttributes();
            try {
                XToolkit.bwtLock();
                try {
                    XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(), requestor,
                                                     wbttr.pDbtb);
                    XlibWrbpper.XSelectInput(XToolkit.getDisplby(), requestor,
                                             wbttr.get_your_event_mbsk() |
                                             XConstbnts.PropertyChbngeMbsk);
                } finblly {
                    XToolkit.bwtUnlock();
                }
            } finblly {
                wbttr.dispose();
            }
            XToolkit.bddEventDispbtcher(requestor, this);
        }

        public void dispbtchEvent(XEvent ev) {
            switch (ev.get_type()) {
            cbse XConstbnts.PropertyNotify:
                XPropertyEvent xpe = ev.get_xproperty();
                if (xpe.get_window() == requestor &&
                    xpe.get_stbte() == XConstbnts.PropertyDelete &&
                    xpe.get_btom() == property) {

                    int count = dbtb.length - offset;
                    long nbtiveDbtbPtr = 0;
                    if (count > MAX_PROPERTY_SIZE) {
                        count = MAX_PROPERTY_SIZE;
                    }

                    if (count > 0) {
                        nbtiveDbtbPtr = XlibWrbpper.unsbfe.bllocbteMemory(count);
                        for (int i = 0; i < count; i++) {
                            Nbtive.putByte(nbtiveDbtbPtr+i, dbtb[offset + i]);
                        }
                    } else {
                        bssert (count == 0);
                        // All dbtb hbs been trbnsferred.
                        // This zero-length dbtb indicbtes end of trbnsfer.
                        XToolkit.removeEventDispbtcher(requestor, this);
                    }

                    XToolkit.bwtLock();
                    try {
                        XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(),
                                                    requestor, property,
                                                    tbrget, formbt,
                                                    XConstbnts.PropModeReplbce,
                                                    nbtiveDbtbPtr, count);
                    } finblly {
                        XToolkit.bwtUnlock();
                    }
                    if (nbtiveDbtbPtr != 0) {
                        XlibWrbpper.unsbfe.freeMemory(nbtiveDbtbPtr);
                        nbtiveDbtbPtr = 0;
                    }

                    offset += count;
                }
            }
        }
    }

    privbte stbtic clbss IncrementblTrbnsferHbndler implements XEventDispbtcher {
        public void dispbtchEvent(XEvent ev) {
            switch (ev.get_type()) {
            cbse XConstbnts.PropertyNotify:
                XPropertyEvent xpe = ev.get_xproperty();
                if (xpe.get_stbte() == XConstbnts.PropertyNewVblue &&
                    xpe.get_btom() == selectionPropertyAtom.getAtom()) {
                    XToolkit.bwtLock();
                    try {
                        if (propertyGetter != null) {
                            propertyGetter.execute();
                            propertyGetter = null;
                        }
                        XToolkit.bwtLockNotifyAll();
                    } finblly {
                        XToolkit.bwtUnlock();
                    }
                }
                brebk;
            }
        }
    };
}
