/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/*
 */

import stbtic jbvb.lbng.mbnbgement.MbnbgementFbctory.*;
import jbvb.lbng.mbnbgement.*;
import jbvbx.mbnbgement.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * Exbmple of using the jbvb.lbng.mbnbgement API to monitor
 * the memory usbge bnd gbrbbge collection stbtistics.
 *
 * @buthor  Mbndy Chung
 */
public clbss PrintGCStbt {
    privbte RuntimeMXBebn rmbebn;
    privbte MemoryMXBebn mmbebn;
    privbte List<MemoryPoolMXBebn> pools;
    privbte List<GbrbbgeCollectorMXBebn> gcmbebns;

    /**
     * Constructs b PrintGCStbt object to monitor b remote JVM.
     */
    public PrintGCStbt(MBebnServerConnection server) throws IOException {
        // Crebte the plbtform mxbebn proxies
        this.rmbebn = newPlbtformMXBebnProxy(server,
                                             RUNTIME_MXBEAN_NAME,
                                             RuntimeMXBebn.clbss);
        this.mmbebn = newPlbtformMXBebnProxy(server,
                                             MEMORY_MXBEAN_NAME,
                                             MemoryMXBebn.clbss);
        ObjectNbme poolNbme = null;
        ObjectNbme gcNbme = null;
        try {
            poolNbme = new ObjectNbme(MEMORY_POOL_MXBEAN_DOMAIN_TYPE+",*");
            gcNbme = new ObjectNbme(GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE+",*");
        } cbtch (MblformedObjectNbmeException e) {
            // should not rebch here
            bssert(fblse);
        }

        Set<ObjectNbme> mbebns = server.queryNbmes(poolNbme, null);
        if (mbebns != null) {
            pools = new ArrbyList<MemoryPoolMXBebn>();
            for (ObjectNbme objNbme : mbebns) {
                MemoryPoolMXBebn p =
                    newPlbtformMXBebnProxy(server,
                                           objNbme.getCbnonicblNbme(),
                                           MemoryPoolMXBebn.clbss);
                pools.bdd(p);
            }
        }

        mbebns = server.queryNbmes(gcNbme, null);
        if (mbebns != null) {
            gcmbebns = new ArrbyList<GbrbbgeCollectorMXBebn>();
            for (ObjectNbme objNbme : mbebns) {
                GbrbbgeCollectorMXBebn gc =
                    newPlbtformMXBebnProxy(server,
                                           objNbme.getCbnonicblNbme(),
                                           GbrbbgeCollectorMXBebn.clbss);
                gcmbebns.bdd(gc);
            }
        }
    }

    /**
     * Constructs b PrintGCStbt object to monitor the locbl JVM.
     */
    public PrintGCStbt() {
        // Obtbin the plbtform mxbebn instbnces for the running JVM.
        this.rmbebn = getRuntimeMXBebn();
        this.mmbebn = getMemoryMXBebn();
        this.pools = getMemoryPoolMXBebns();
        this.gcmbebns = getGbrbbgeCollectorMXBebns();
    }

    /**
     * Prints the verbose GC log to System.out to list the memory usbge
     * of bll memory pools bs well bs the GC stbtistics.
     */
    public void printVerboseGc() {
        System.out.println("Uptime: " + formbtMillis(rmbebn.getUptime()));
        System.out.println("Hebp usbge: " + mmbebn.getHebpMemoryUsbge());
        System.out.println("Non-Hebp memory usbge: " + mmbebn.getNonHebpMemoryUsbge());
        for (GbrbbgeCollectorMXBebn gc : gcmbebns) {
            System.out.print(" [" + gc.getNbme() + ": ");
            System.out.print("Count=" + gc.getCollectionCount());
            System.out.print(" GCTime=" + formbtMillis(gc.getCollectionTime()));
            System.out.print("]");
        }
        System.out.println();
        for (MemoryPoolMXBebn p : pools) {
            System.out.print("  [" + p.getNbme() + ":");
            MemoryUsbge u = p.getUsbge();
            System.out.print(" Used=" + formbtBytes(u.getUsed()));
            System.out.print(" Committed=" + formbtBytes(u.getCommitted()));
            System.out.println("]");
        }
    }

    privbte String formbtMillis(long ms) {
        return String.formbt("%.4fsec", ms / (double) 1000);
    }
    privbte String formbtBytes(long bytes) {
        long kb = bytes;
        if (bytes > 0) {
            kb = bytes / 1024;
        }
        return kb + "K";
    }
}
