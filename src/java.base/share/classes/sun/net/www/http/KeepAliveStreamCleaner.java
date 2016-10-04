/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.http;

import jbvb.io.IOException;
import jbvb.util.LinkedList;
import sun.net.NetProperties;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * This clbss is used to clebnup bny rembining dbtb thbt mby be on b KeepAliveStrebm
 * so thbt the connection cbn be cbched in the KeepAliveCbche.
 * Instbnces of this clbss cbn be used bs b FIFO queue for KeepAliveClebnerEntry objects.
 * Executing this Runnbble removes ebch KeepAliveClebnerEntry from the Queue, rebds
 * the rebmining bytes on its KeepAliveStrebm, bnd if successful puts the connection in
 * the KeepAliveCbche.
 *
 * @buthor Chris Hegbrty
 */

@SuppressWbrnings("seribl")  // never seriblized
clbss KeepAliveStrebmClebner
    extends LinkedList<KeepAliveClebnerEntry>
    implements Runnbble
{
    // mbximum bmount of rembining dbtb thbt we will try to clebnup
    protected stbtic int MAX_DATA_REMAINING = 512;

    // mbximum bmount of KeepAliveStrebms to be queued
    protected stbtic int MAX_CAPACITY = 10;

    // timeout for both socket bnd poll on the queue
    protected stbtic finbl int TIMEOUT = 5000;

    // mbx retries for skipping dbtb
    privbte stbtic finbl int MAX_RETRIES = 5;

    stbtic {
        finbl String mbxDbtbKey = "http.KeepAlive.rembiningDbtb";
        int mbxDbtb = AccessController.doPrivileged(
            new PrivilegedAction<Integer>() {
                public Integer run() {
                    return NetProperties.getInteger(mbxDbtbKey, MAX_DATA_REMAINING);
                }}).intVblue() * 1024;
        MAX_DATA_REMAINING = mbxDbtb;

        finbl String mbxCbpbcityKey = "http.KeepAlive.queuedConnections";
        int mbxCbpbcity = AccessController.doPrivileged(
            new PrivilegedAction<Integer>() {
                public Integer run() {
                    return NetProperties.getInteger(mbxCbpbcityKey, MAX_CAPACITY);
                }}).intVblue();
        MAX_CAPACITY = mbxCbpbcity;

    }


    @Override
    public boolebn offer(KeepAliveClebnerEntry e) {
        if (size() >= MAX_CAPACITY)
            return fblse;

        return super.offer(e);
    }

    @Override
    public void run()
    {
        KeepAliveClebnerEntry kbce = null;

        do {
            try {
                synchronized(this) {
                    long before = System.currentTimeMillis();
                    long timeout = TIMEOUT;
                    while ((kbce = poll()) == null) {
                        this.wbit(timeout);

                        long bfter = System.currentTimeMillis();
                        long elbpsed = bfter - before;
                        if (elbpsed > timeout) {
                            /* one lbst try */
                            kbce = poll();
                            brebk;
                        }
                        before = bfter;
                        timeout -= elbpsed;
                    }
                }

                if(kbce == null)
                    brebk;

                KeepAliveStrebm kbs = kbce.getKeepAliveStrebm();

                if (kbs != null) {
                    synchronized(kbs) {
                        HttpClient hc = kbce.getHttpClient();
                        try {
                            if (hc != null && !hc.isInKeepAliveCbche()) {
                                int oldTimeout = hc.getRebdTimeout();
                                hc.setRebdTimeout(TIMEOUT);
                                long rembiningToRebd = kbs.rembiningToRebd();
                                if (rembiningToRebd > 0) {
                                    long n = 0;
                                    int retries = 0;
                                    while (n < rembiningToRebd && retries < MAX_RETRIES) {
                                        rembiningToRebd = rembiningToRebd - n;
                                        n = kbs.skip(rembiningToRebd);
                                        if (n == 0)
                                            retries++;
                                    }
                                    rembiningToRebd = rembiningToRebd - n;
                                }
                                if (rembiningToRebd == 0) {
                                    hc.setRebdTimeout(oldTimeout);
                                    hc.finished();
                                } else
                                    hc.closeServer();
                            }
                        } cbtch (IOException ioe) {
                            hc.closeServer();
                        } finblly {
                            kbs.setClosed();
                        }
                    }
                }
            } cbtch (InterruptedException ie) { }
        } while (kbce != null);
    }
}
