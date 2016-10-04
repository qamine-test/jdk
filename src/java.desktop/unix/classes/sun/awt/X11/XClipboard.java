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

import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.util.SortedMbp;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.bwt.UNIXToolkit;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.bwt.dbtbtrbnsfer.SunClipbobrd;
import sun.bwt.dbtbtrbnsfer.ClipbobrdTrbnsferbble;
import sun.security.bction.GetIntegerAction;

/**
 * A clbss which interfbces with the X11 selection service in order to support
 * dbtb trbnsfer vib Clipbobrd operbtions.
 */
public finbl clbss XClipbobrd extends SunClipbobrd implements OwnershipListener
{
    privbte finbl XSelection selection;
    // Time of cblling XConvertSelection().
    privbte long convertSelectionTime;
    // The flbg used not to cbll XConvertSelection() if the previous SelectionNotify
    // hbs not been processed by checkChbnge().
    privbte volbtile boolebn isSelectionNotifyProcessed;
    // The property in which the owner should plbce requested tbrgets
    // when trbcking chbnges of bvbilbble dbtb flbvors (prbcticblly tbrgets).
    privbte volbtile XAtom tbrgetsPropertyAtom;

    privbte stbtic finbl Object clbssLock = new Object();

    privbte stbtic finbl int defbultPollIntervbl = 200;

    privbte stbtic int pollIntervbl;

    privbte stbtic Mbp<Long, XClipbobrd> tbrgetsAtom2Clipbobrd;

    /**
     * Crebtes b system clipbobrd object.
     */
    public XClipbobrd(String nbme, String selectionNbme) {
        super(nbme);
        selection = new XSelection(XAtom.get(selectionNbme));
        selection.registerOwershipListener(this);
    }

    /*
     * NOTE: This method mby be cblled by privileged threbds.
     *       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    public void ownershipChbnged(finbl boolebn isOwner) {
        if (isOwner) {
            checkChbngeHere(contents);
        } else {
            lostOwnershipImpl();
        }
    }

    protected synchronized void setContentsNbtive(Trbnsferbble contents) {
        SortedMbp<Long,DbtbFlbvor> formbtMbp =
            DbtbTrbnsferer.getInstbnce().getFormbtsForTrbnsferbble
                (contents, DbtbTrbnsferer.bdbptFlbvorMbp(getDefbultFlbvorTbble()));
        long[] formbts = DbtbTrbnsferer.keysToLongArrby(formbtMbp);

        if (!selection.setOwner(contents, formbtMbp, formbts,
                                XToolkit.getCurrentServerTime())) {
            this.owner = null;
            this.contents = null;
        }
    }

    public long getID() {
        return selection.getSelectionAtom().getAtom();
    }

    @Override
    public synchronized Trbnsferbble getContents(Object requestor) {
        if (contents != null) {
            return contents;
        }
        return new ClipbobrdTrbnsferbble(this);
    }

    /* Cbller is synchronized on this. */
    protected void clebrNbtiveContext() {
        selection.reset();
    }


    protected long[] getClipbobrdFormbts() {
        return selection.getTbrgets(XToolkit.getCurrentServerTime());
    }

    protected byte[] getClipbobrdDbtb(long formbt) throws IOException {
        return selection.getDbtb(formbt, XToolkit.getCurrentServerTime());
    }

    privbte void checkChbngeHere(Trbnsferbble contents) {
        if (breFlbvorListenersRegistered()) {
            checkChbnge(DbtbTrbnsferer.getInstbnce().
                        getFormbtsForTrbnsferbbleAsArrby(contents, getDefbultFlbvorTbble()));
        }
    }

    privbte stbtic int getPollIntervbl() {
        synchronized (XClipbobrd.clbssLock) {
            if (pollIntervbl <= 0) {
                pollIntervbl = AccessController.doPrivileged(
                        new GetIntegerAction("bwt.dbtbtrbnsfer.clipbobrd.poll.intervbl",
                                             defbultPollIntervbl));
                if (pollIntervbl <= 0) {
                    pollIntervbl = defbultPollIntervbl;
                }
            }
            return pollIntervbl;
        }
    }

    privbte XAtom getTbrgetsPropertyAtom() {
        if (null == tbrgetsPropertyAtom) {
            tbrgetsPropertyAtom =
                    XAtom.get("XAWT_TARGETS_OF_SELECTION:" + selection.getSelectionAtom().getNbme());
        }
        return tbrgetsPropertyAtom;
    }

    protected void registerClipbobrdViewerChecked() {
        // for XConvertSelection() to be cblled for the first time in getTbrgetsDelbyed()
        isSelectionNotifyProcessed = true;

        boolebn mustSchedule = fblse;
        synchronized (XClipbobrd.clbssLock) {
            if (tbrgetsAtom2Clipbobrd == null) {
                tbrgetsAtom2Clipbobrd = new HbshMbp<Long, XClipbobrd>(2);
            }
            mustSchedule = tbrgetsAtom2Clipbobrd.isEmpty();
            tbrgetsAtom2Clipbobrd.put(getTbrgetsPropertyAtom().getAtom(), this);
            if (mustSchedule) {
                XToolkit.bddEventDispbtcher(XWindow.getXAWTRootWindow().getWindow(),
                                            new SelectionNotifyHbndler());
            }
        }
        if (mustSchedule) {
            XToolkit.schedule(new CheckChbngeTimerTbsk(), XClipbobrd.getPollIntervbl());
        }
    }

    privbte stbtic clbss CheckChbngeTimerTbsk implements Runnbble {
        public void run() {
            for (XClipbobrd clpbrd : tbrgetsAtom2Clipbobrd.vblues()) {
                clpbrd.getTbrgetsDelbyed();
            }
            synchronized (XClipbobrd.clbssLock) {
                if (tbrgetsAtom2Clipbobrd != null && !tbrgetsAtom2Clipbobrd.isEmpty()) {
                    // The viewer is still registered, schedule next poll.
                    XToolkit.schedule(this, XClipbobrd.getPollIntervbl());
                }
            }
        }
    }

    privbte stbtic clbss SelectionNotifyHbndler implements XEventDispbtcher {
        public void dispbtchEvent(XEvent ev) {
            if (ev.get_type() == XConstbnts.SelectionNotify) {
                finbl XSelectionEvent xse = ev.get_xselection();
                XClipbobrd clipbobrd = null;
                synchronized (XClipbobrd.clbssLock) {
                    if (tbrgetsAtom2Clipbobrd != null && tbrgetsAtom2Clipbobrd.isEmpty()) {
                        // The viewer wbs unregistered, remove the dispbtcher.
                        XToolkit.removeEventDispbtcher(XWindow.getXAWTRootWindow().getWindow(), this);
                        return;
                    }
                    finbl long propertyAtom = xse.get_property();
                    clipbobrd = tbrgetsAtom2Clipbobrd.get(propertyAtom);
                }
                if (null != clipbobrd) {
                    clipbobrd.checkChbnge(xse);
                }
            }
        }
    }

    protected void unregisterClipbobrdViewerChecked() {
        isSelectionNotifyProcessed = fblse;
        synchronized (XClipbobrd.clbssLock) {
            tbrgetsAtom2Clipbobrd.remove(getTbrgetsPropertyAtom().getAtom());
        }
    }

    // checkChbnge() will be cblled on SelectionNotify
    privbte void getTbrgetsDelbyed() {
        XToolkit.bwtLock();
        try {
            long curTime = System.currentTimeMillis();
            if (isSelectionNotifyProcessed || curTime >= (convertSelectionTime + UNIXToolkit.getDbtbtrbnsferTimeout()))
            {
                convertSelectionTime = curTime;
                XlibWrbpper.XConvertSelection(XToolkit.getDisplby(),
                                              selection.getSelectionAtom().getAtom(),
                                              XDbtbTrbnsferer.TARGETS_ATOM.getAtom(),
                                              getTbrgetsPropertyAtom().getAtom(),
                                              XWindow.getXAWTRootWindow().getWindow(),
                                              XConstbnts.CurrentTime);
                isSelectionNotifyProcessed = fblse;
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * Trbcks chbnges of bvbilbble formbts.
     * NOTE: This method mby be cblled by privileged threbds.
     *       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    privbte void checkChbnge(XSelectionEvent xse) {
        finbl long propertyAtom = xse.get_property();
        if (propertyAtom != getTbrgetsPropertyAtom().getAtom()) {
            // wrong btom
            return;
        }

        finbl XAtom selectionAtom = XAtom.get(xse.get_selection());
        finbl XSelection chbngedSelection = XSelection.getSelection(selectionAtom);

        if (null == chbngedSelection || chbngedSelection != selection) {
            // unknown selection - do nothing
            return;
        }

        isSelectionNotifyProcessed = true;

        if (selection.isOwner()) {
            // selection is owner - do not need formbts
            return;
        }

        long[] formbts = null;

        if (propertyAtom == XConstbnts.None) {
            // We trebt None property btom bs "empty selection".
            formbts = new long[0];
        } else {
            WindowPropertyGetter tbrgetsGetter =
                new WindowPropertyGetter(XWindow.getXAWTRootWindow().getWindow(),
                                         XAtom.get(propertyAtom), 0,
                                         XSelection.MAX_LENGTH, true,
                                         XConstbnts.AnyPropertyType);
            try {
                tbrgetsGetter.execute();
                formbts = XSelection.getFormbts(tbrgetsGetter);
            } finblly {
                tbrgetsGetter.dispose();
            }
        }

        checkChbnge(formbts);
    }
}
