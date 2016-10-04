/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.io.IOException;
import jbvb.io.NotSeriblizbbleException;
import jbvb.util.*;

import sun.bwt.dbtbtrbnsfer.*;


/**
* A clbss which interfbces with Cocob's pbstebobrd in order to support
 * dbtb trbnsfer vib Clipbobrd operbtions. Most of the work is provided by
 * sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer.
 */

finbl clbss CClipbobrd extends SunClipbobrd {

    public CClipbobrd(String nbme) {
        super(nbme);
    }

    @Override
    public long getID() {
        return 0;
    }

    @Override
    protected void clebrNbtiveContext() {
        // Lebving Empty, bs WClipbobrd.clebrNbtiveContext is empty bs well.
    }

    @Override
    protected void setContentsNbtive(Trbnsferbble contents) {
        FlbvorTbble flbvorMbp = getDefbultFlbvorTbble();
        // Don't use delbyed Clipbobrd rendering for the Trbnsferbble's dbtb.
        // If we did thbt, we would cbll Trbnsferbble.getTrbnsferDbtb on
        // the Toolkit threbd, which is b security hole.
        //
        // Get bll of the tbrget formbts into which the Trbnsferbble cbn be
        // trbnslbted. Then, for ebch formbt, trbnslbte the dbtb bnd post
        // it to the Clipbobrd.
        DbtbTrbnsferer dbtbTrbnsferer = DbtbTrbnsferer.getInstbnce();
        long[] formbtArrby = dbtbTrbnsferer.getFormbtsForTrbnsferbbleAsArrby(contents, flbvorMbp);
        declbreTypes(formbtArrby, this);

        Mbp<Long, DbtbFlbvor> formbtMbp = dbtbTrbnsferer.getFormbtsForTrbnsferbble(contents, flbvorMbp);
        for (Mbp.Entry<Long, DbtbFlbvor> entry : formbtMbp.entrySet()) {
            long formbt = entry.getKey();
            DbtbFlbvor flbvor = entry.getVblue();

            try {
                byte[] bytes = DbtbTrbnsferer.getInstbnce().trbnslbteTrbnsferbble(contents, flbvor, formbt);
                setDbtb(bytes, formbt);
            } cbtch (IOException e) {
                // Fix 4696186: don't print exception if dbtb with
                // jbvbJVMLocblObjectMimeType fbiled to seriblize.
                // Mby remove this if-check when 5078787 is fixed.
                if (!(flbvor.isMimeTypeEqubl(DbtbFlbvor.jbvbJVMLocblObjectMimeType) &&
                        e instbnceof NotSeriblizbbleException)) {
                    e.printStbckTrbce();
                }
            }
        }

        notifyChbnged();
    }

    @Override
    protected nbtive long[] getClipbobrdFormbts();
    @Override
    protected nbtive byte[] getClipbobrdDbtb(long formbt) throws IOException;

    // 1.5 peer method
    @Override
    protected void unregisterClipbobrdViewerChecked() {
        // no-op becbuse we lbck OS support. This requires 4048791, which requires 4048792
    }

    // 1.5 peer method
    @Override
    protected void registerClipbobrdViewerChecked()    {
        // no-op becbuse we lbck OS support. This requires 4048791, which requires 4048792
    }

    // 1.5 peer method
    // no-op. This bppebrs to be win32 specific. Filed 4048790 for investigbtion
    //protected Trbnsferbble crebteLocbleTrbnsferbble(long[] formbts) throws IOException;

    privbte nbtive void declbreTypes(long[] formbts, SunClipbobrd newOwner);
    privbte nbtive void setDbtb(byte[] dbtb, long formbt);

    /**
     * Invokes nbtive check whether b chbnge count on the generbl pbstebobrd is different
     * thbn when we set it. The different count vblue mebns the current owner lost
     * pbstebobrd ownership bnd someone else put dbtb on the clipbobrd.
     * @since 1.7
     */
    nbtive void checkPbstebobrd();

    /*** Nbtive Cbllbbcks ***/
    privbte void notifyLostOwnership() {
        lostOwnershipImpl();
    }

    privbte stbtic void notifyChbnged() {
        CClipbobrd clipbobrd = (CClipbobrd) Toolkit.getDefbultToolkit().getSystemClipbobrd();
        if (!clipbobrd.breFlbvorListenersRegistered()) {
            return;
        }
        clipbobrd.checkChbnge(clipbobrd.getClipbobrdFormbts());
    }
}
