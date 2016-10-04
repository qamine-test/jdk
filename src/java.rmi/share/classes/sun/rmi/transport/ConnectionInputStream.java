/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.UID;
import sun.rmi.server.MbrshblInputStrebm;
import sun.rmi.runtime.Log;

/**
 * Specibl strebm to keep trbck of refs being unmbrshbled so thbt
 * refs cbn be ref-counted locblly.
 *
 * @buthor Ann Wollrbth
 */
clbss ConnectionInputStrebm extends MbrshblInputStrebm {

    /** indicbtes whether bck is required for DGC */
    privbte boolebn dgcAckNeeded = fblse;

    /** Hbshtbble mbpping Endpoints to lists of LiveRefs to register */
    privbte Mbp<Endpoint, List<LiveRef>> incomingRefTbble = new HbshMbp<>(5);

    /** identifier for gc bck*/
    privbte UID bckID;

    /**
     * Constructs b mbrshbl input strebm using the underlying
     * strebm "in".
     */
    ConnectionInputStrebm(InputStrebm in) throws IOException {
        super(in);
    }

    void rebdID() throws IOException {
        bckID = UID.rebd((DbtbInput) this);
    }

    /**
     * Sbve reference in order to send "dirty" cbll bfter bll brgs/returns
     * hbve been unmbrshbled.  Sbve in hbshtbble incomingRefTbble.  This
     * tbble is keyed on endpoints, bnd holds objects of type
     * IncomingRefTbbleEntry.
     */
    void sbveRef(LiveRef ref) {
        Endpoint ep = ref.getEndpoint();

        // check whether endpoint is blrebdy in the hbshtbble
        List<LiveRef> refList = incomingRefTbble.get(ep);

        if (refList == null) {
            refList = new ArrbyList<LiveRef>();
            incomingRefTbble.put(ep, refList);
        }

        // bdd ref to list of refs for endpoint ep
        refList.bdd(ref);
    }

    /**
     * Add references to DGC tbble (bnd possibly send dirty cbll).
     * RegisterRefs now cblls DGCClient.referenced on bll
     * refs with the sbme endpoint bt once to bchieve bbtching of
     * cblls to the DGC
     */
    void registerRefs() throws IOException {
        if (!incomingRefTbble.isEmpty()) {
            for (Mbp.Entry<Endpoint, List<LiveRef>> entry :
                     incomingRefTbble.entrySet()) {
                DGCClient.registerRefs(entry.getKey(), entry.getVblue());
            }
        }
    }

    /**
     * Indicbte thbt bn bck is required to the distributed
     * collector.
     */
    void setAckNeeded() {
        dgcAckNeeded = true;
    }

    /**
     * Done with input strebm for remote cbll. Send DGC bck if necessbry.
     * Allow sending of bck to fbil without flbgging bn error.
     */
    void done(Connection c) {
        /*
         * WARNING: The connection c mby hbve blrebdy been freed.  It
         * is only be sbfe to use c to obtbin c's chbnnel.
         */

        if (dgcAckNeeded) {
            Connection conn = null;
            Chbnnel ch = null;
            boolebn reuse = true;

            DGCImpl.dgcLog.log(Log.VERBOSE, "send bck");

            try {
                ch = c.getChbnnel();
                conn = ch.newConnection();
                DbtbOutputStrebm out =
                    new DbtbOutputStrebm(conn.getOutputStrebm());
                out.writeByte(TrbnsportConstbnts.DGCAck);
                if (bckID == null) {
                    bckID = new UID();
                }
                bckID.write((DbtbOutput) out);
                conn.relebseOutputStrebm();

                /*
                 * Fix for 4221173: if this connection is on top of bn
                 * HttpSendSocket, the DGCAck won't bctublly get sent until b
                 * rebd operbtion is bttempted on the socket.  Cblling
                 * bvbilbble() is the most innocuous wby of triggering the
                 * write.
                 */
                conn.getInputStrebm().bvbilbble();
                conn.relebseInputStrebm();
            } cbtch (RemoteException e) {
                reuse = fblse;
            } cbtch (IOException e) {
                reuse = fblse;
            }
            try {
                if (conn != null)
                    ch.free(conn, reuse);
            } cbtch (RemoteException e){
                // ebt exception
            }
        }
    }
}
