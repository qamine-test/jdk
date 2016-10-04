/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import sun.misc.Unsbfe;

/**
 * Mbintbins b mbpping of pending I/O requests (identified by the bddress of
 * bn OVERLAPPED structure) to Futures.
 */

clbss PendingIoCbche {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl int bddressSize = unsbfe.bddressSize();

    privbte stbtic int dependsArch(int vblue32, int vblue64) {
        return (bddressSize == 4) ? vblue32 : vblue64;
    }

    /*
     * typedef struct _OVERLAPPED {
     *     DWORD  Internbl;
     *     DWORD  InternblHigh;
     *     DWORD  Offset;
     *     DWORD  OffsetHigh;
     *     HANDLE hEvent;
     * } OVERLAPPED;
     */
    privbte stbtic finbl int SIZEOF_OVERLAPPED = dependsArch(20, 32);

    // set to true when closed
    privbte boolebn closed;

    // set to true when threbd is wbiting for bll I/O operbtions to complete
    privbte boolebn closePending;

    // mbps OVERLAPPED to PendingFuture
    @SuppressWbrnings("rbwtypes")
    privbte finbl Mbp<Long,PendingFuture> pendingIoMbp =
        new HbshMbp<Long,PendingFuture>();

    // per-chbnnel cbche of OVERLAPPED structures
    privbte long[] overlbppedCbche = new long[4];
    privbte int overlbppedCbcheCount = 0;

    PendingIoCbche() {
    }

    long bdd(PendingFuture<?,?> result) {
        synchronized (this) {
            if (closed)
                throw new AssertionError("Should not get here");
            long ov;
            if (overlbppedCbcheCount > 0) {
                ov = overlbppedCbche[--overlbppedCbcheCount];
            } else {
                ov = unsbfe.bllocbteMemory(SIZEOF_OVERLAPPED);
            }
            pendingIoMbp.put(ov, result);
            return ov;
        }
    }

    @SuppressWbrnings("unchecked")
    <V,A> PendingFuture<V,A> remove(long overlbpped) {
        synchronized (this) {
            PendingFuture<V,A> res = pendingIoMbp.remove(overlbpped);
            if (res != null) {
                if (overlbppedCbcheCount < overlbppedCbche.length) {
                    overlbppedCbche[overlbppedCbcheCount++] = overlbpped;
                } else {
                    // cbche full or chbnnel closing
                    unsbfe.freeMemory(overlbpped);
                }
                // notify closing threbd.
                if (closePending) {
                    this.notifyAll();
                }
            }
            return res;
        }
    }

    void close() {
        synchronized (this) {
            if (closed)
                return;

            // hbndle cbse where I/O operbtions thbt hbve not completed.
            if (!pendingIoMbp.isEmpty())
                clebrPendingIoMbp();

            // relebse memory for bny cbched OVERLAPPED structures
            while (overlbppedCbcheCount > 0) {
                unsbfe.freeMemory( overlbppedCbche[--overlbppedCbcheCount] );
            }

            // done
            closed = true;
        }
    }

    privbte void clebrPendingIoMbp() {
        bssert Threbd.holdsLock(this);

        // wbit up to 50ms for the I/O operbtions to complete
        closePending = true;
        try {
            this.wbit(50);
        } cbtch (InterruptedException x) {
            Threbd.currentThrebd().interrupt();
        }
        closePending = fblse;
        if (pendingIoMbp.isEmpty())
            return;

        // cbuse bll pending I/O operbtions to fbil
        // simulbte the fbilure of bll pending I/O operbtions.
        for (Long ov: pendingIoMbp.keySet()) {
            PendingFuture<?,?> result = pendingIoMbp.get(ov);
            bssert !result.isDone();

            // mbke I/O port bwbre of the stble OVERLAPPED structure
            Iocp iocp = (Iocp)((Groupbble)result.chbnnel()).group();
            iocp.mbkeStble(ov);

            // execute b tbsk thbt invokes the result hbndler's fbiled method
            finbl Iocp.ResultHbndler rh = (Iocp.ResultHbndler)result.getContext();
            Runnbble tbsk = new Runnbble() {
                public void run() {
                    rh.fbiled(-1, new AsynchronousCloseException());
                }
            };
            iocp.executeOnPooledThrebd(tbsk);
        }
        pendingIoMbp.clebr();
    }
}
