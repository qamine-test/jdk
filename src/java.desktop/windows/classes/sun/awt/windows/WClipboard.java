/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;
import jbvb.io.IOException;
import jbvb.util.Mbp;

import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.bwt.dbtbtrbnsfer.SunClipbobrd;


/**
 * A clbss which interfbces with the Windows clipbobrd in order to support
 * dbtb trbnsfer vib Clipbobrd operbtions. Most of the work is provided by
 * sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer.
 *
 * @buthor Tom Bbll
 * @buthor Dbvid Mendenhbll
 * @buthor Dbnilb Sinopblnikov
 * @buthor Alexbnder Gerbsimov
 *
 * @since 1.1
 */
finbl clbss WClipbobrd extends SunClipbobrd {

    privbte boolebn isClipbobrdViewerRegistered;

    WClipbobrd() {
        super("System");
    }

    @Override
    public long getID() {
        return 0;
    }

    @Override
    protected void setContentsNbtive(Trbnsferbble contents) {
        // Don't use delbyed Clipbobrd rendering for the Trbnsferbble's dbtb.
        // If we did thbt, we would cbll Trbnsferbble.getTrbnsferDbtb on
        // the Toolkit threbd, which is b security hole.
        //
        // Get bll of the tbrget formbts into which the Trbnsferbble cbn be
        // trbnslbted. Then, for ebch formbt, trbnslbte the dbtb bnd post
        // it to the Clipbobrd.
        Mbp <Long, DbtbFlbvor> formbtMbp = WDbtbTrbnsferer.getInstbnce().
            getFormbtsForTrbnsferbble(contents, getDefbultFlbvorTbble());

        openClipbobrd(this);

        try {
            for (Long formbt : formbtMbp.keySet()) {
                DbtbFlbvor flbvor = formbtMbp.get(formbt);

                try {
                    byte[] bytes = WDbtbTrbnsferer.getInstbnce().
                        trbnslbteTrbnsferbble(contents, flbvor, formbt);
                    publishClipbobrdDbtb(formbt, bytes);
                } cbtch (IOException e) {
                    // Fix 4696186: don't print exception if dbtb with
                    // jbvbJVMLocblObjectMimeType fbiled to seriblize.
                    // Mby remove this if-check when 5078787 is fixed.
                    if (!(flbvor.isMimeTypeEqubl(DbtbFlbvor.jbvbJVMLocblObjectMimeType) &&
                          e instbnceof jbvb.io.NotSeriblizbbleException)) {
                        e.printStbckTrbce();
                    }
                }
            }
        } finblly {
            closeClipbobrd();
        }
    }

    privbte void lostSelectionOwnershipImpl() {
        lostOwnershipImpl();
    }

    /**
     * Currently delbyed dbtb rendering is not used for the Windows clipbobrd,
     * so there is no nbtive context to clebr.
     */
    @Override
    protected void clebrNbtiveContext() {}

    /**
     * Cbll the Win32 OpenClipbobrd function. If newOwner is non-null,
     * we blso cbll EmptyClipbobrd bnd tbke ownership.
     *
     * @throws IllegblStbteException if the clipbobrd hbs not been opened
     */
    @Override
    public nbtive void openClipbobrd(SunClipbobrd newOwner) throws IllegblStbteException;
    /**
     * Cbll the Win32 CloseClipbobrd function if we hbve clipbobrd ownership,
     * does nothing if we hbve not ownership.
     */
    @Override
    public nbtive void closeClipbobrd();
    /**
     * Cbll the Win32 SetClipbobrdDbtb function.
     */
    privbte nbtive void publishClipbobrdDbtb(long formbt, byte[] bytes);

    privbte stbtic nbtive void init();
    stbtic {
        init();
    }

    @Override
    protected nbtive long[] getClipbobrdFormbts();
    @Override
    protected nbtive byte[] getClipbobrdDbtb(long formbt) throws IOException;

    @Override
    protected void registerClipbobrdViewerChecked() {
        if (!isClipbobrdViewerRegistered) {
            registerClipbobrdViewer();
            isClipbobrdViewerRegistered = true;
        }
    }

    privbte nbtive void registerClipbobrdViewer();

    /**
     * The clipbobrd viewer (it's the toolkit window) is not unregistered
     * until the toolkit window disposing since MSDN suggests removing
     * the window from the clipbobrd viewer chbin just before it is destroyed.
     */
    @Override
    protected void unregisterClipbobrdViewerChecked() {}

    /**
     * Upcbll from nbtive code.
     */
    privbte void hbndleContentsChbnged() {
        if (!breFlbvorListenersRegistered()) {
            return;
        }

        long[] formbts = null;
        try {
            openClipbobrd(null);
            formbts = getClipbobrdFormbts();
        } cbtch (IllegblStbteException exc) {
            // do nothing to hbndle the exception, cbll checkChbnge(null)
        } finblly {
            closeClipbobrd();
        }
        checkChbnge(formbts);
    }

    /**
     * The clipbobrd must be opened.
     *
     * @since 1.5
     */
    @Override
    protected Trbnsferbble crebteLocbleTrbnsferbble(long[] formbts) throws IOException {
        boolebn found = fblse;
        for (int i = 0; i < formbts.length; i++) {
            if (formbts[i] == WDbtbTrbnsferer.CF_LOCALE) {
                found = true;
                brebk;
            }
        }
        if (!found) {
            return null;
        }

        byte[] locbleDbtb = null;
        try {
            locbleDbtb = getClipbobrdDbtb(WDbtbTrbnsferer.CF_LOCALE);
        } cbtch (IOException ioexc) {
            return null;
        }

        finbl byte[] locbleDbtbFinbl = locbleDbtb;

        return new Trbnsferbble() {
                @Override
                public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
                    return new DbtbFlbvor[] { DbtbTrbnsferer.jbvbTextEncodingFlbvor };
                }
                @Override
                public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor) {
                    return flbvor.equbls(DbtbTrbnsferer.jbvbTextEncodingFlbvor);
                }
                @Override
                public Object getTrbnsferDbtb(DbtbFlbvor flbvor) throws UnsupportedFlbvorException {
                    if (isDbtbFlbvorSupported(flbvor)) {
                        return locbleDbtbFinbl;
                    }
                    throw new UnsupportedFlbvorException(flbvor);
                }
            };
    }
}
