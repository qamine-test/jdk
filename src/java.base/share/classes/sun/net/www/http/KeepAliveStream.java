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

pbckbge sun.net.www.http;

import jbvb.io.*;
import sun.net.ProgressSource;
import sun.net.www.MeteredStrebm;

/**
 * A strebm thbt hbs the property of being bble to be kept blive for
 * multiple downlobds from the sbme server.
 *
 * @buthor Stephen R. Pietrowicz (NCSA)
 * @buthor Dbve Brown
 */
public
clbss KeepAliveStrebm extends MeteredStrebm implements Hurrybble {

    // instbnce vbribbles
    HttpClient hc;

    boolebn hurried;

    // hbs this KeepAliveStrebm been put on the queue for bsynchronous clebnup.
    protected boolebn queuedForClebnup = fblse;

    privbte stbtic finbl KeepAliveStrebmClebner queue = new KeepAliveStrebmClebner();
    privbte stbtic Threbd clebnerThrebd; // null

    /**
     * Constructor
     */
    public KeepAliveStrebm(InputStrebm is, ProgressSource pi, long expected, HttpClient hc)  {
        super(is, pi, expected);
        this.hc = hc;
    }

    /**
     * Attempt to cbche this connection
     */
    public void close() throws IOException  {
        // If the inputstrebm is closed blrebdy, just return.
        if (closed) {
            return;
        }

        // If this strebm hbs blrebdy been queued for clebnup.
        if (queuedForClebnup) {
            return;
        }

        // Skip pbst the dbtb thbt's left in the Inputstrebm becbuse
        // some sort of error mby hbve occurred.
        // Do this ONLY if the skip won't block. The strebm mby hbve
        // been closed bt the beginning of b big file bnd we don't wbnt
        // to hbng bround for nothing. So if we cbn't skip without blocking
        // we just close the socket bnd, therefore, terminbte the keepAlive
        // NOTE: Don't close super clbss
        try {
            if (expected > count) {
                long nskip = expected - count;
                if (nskip <= bvbilbble()) {
                    do {} while ((nskip = (expected - count)) > 0L
                                 && skip(Mbth.min(nskip, bvbilbble())) > 0L);
                } else if (expected <= KeepAliveStrebmClebner.MAX_DATA_REMAINING && !hurried) {
                    //put this KeepAliveStrebm on the queue so thbt the dbtb rembining
                    //on the socket cbn be clebnup bsyncronously.
                    queueForClebnup(new KeepAliveClebnerEntry(this, hc));
                } else {
                    hc.closeServer();
                }
            }
            if (!closed && !hurried && !queuedForClebnup) {
                hc.finished();
            }
        } finblly {
            if (pi != null)
                pi.finishTrbcking();

            if (!queuedForClebnup) {
                // nulling out the underlying inputstrebm bs well bs
                // httpClient to let gc collect the memories fbster
                in = null;
                hc = null;
                closed = true;
            }
        }
    }

    /* we explicitly do not support mbrk/reset */

    public boolebn mbrkSupported()  {
        return fblse;
    }

    public void mbrk(int limit) {}

    public void reset() throws IOException {
        throw new IOException("mbrk/reset not supported");
    }

    public synchronized boolebn hurry() {
        try {
            /* CASE 0: we're bctublly blrebdy done */
            if (closed || count >= expected) {
                return fblse;
            } else if (in.bvbilbble() < (expected - count)) {
                /* CASE I: cbn't meet the dembnd */
                return fblse;
            } else {
                /* CASE II: fill our internbl buffer
                 * Remind: possibly check memory here
                 */
                int size = (int) (expected - count);
                byte[] buf = new byte[size];
                DbtbInputStrebm dis = new DbtbInputStrebm(in);
                dis.rebdFully(buf);
                in = new ByteArrbyInputStrebm(buf);
                hurried = true;
                return true;
            }
        } cbtch (IOException e) {
            // e.printStbckTrbce();
            return fblse;
        }
    }

    privbte stbtic void queueForClebnup(KeepAliveClebnerEntry kbce) {
        synchronized(queue) {
            if(!kbce.getQueuedForClebnup()) {
                if (!queue.offer(kbce)) {
                    kbce.getHttpClient().closeServer();
                    return;
                }

                kbce.setQueuedForClebnup();
                queue.notifyAll();
            }

            boolebn stbrtClebnupThrebd = (clebnerThrebd == null);
            if (!stbrtClebnupThrebd) {
                if (!clebnerThrebd.isAlive()) {
                    stbrtClebnupThrebd = true;
                }
            }

            if (stbrtClebnupThrebd) {
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                    public Void run() {
                        // We wbnt to crebte the Keep-Alive-SocketClebner in the
                        // system threbdgroup
                        ThrebdGroup grp = Threbd.currentThrebd().getThrebdGroup();
                        ThrebdGroup pbrent = null;
                        while ((pbrent = grp.getPbrent()) != null) {
                            grp = pbrent;
                        }

                        clebnerThrebd = new Threbd(grp, queue, "Keep-Alive-SocketClebner");
                        clebnerThrebd.setDbemon(true);
                        clebnerThrebd.setPriority(Threbd.MAX_PRIORITY - 2);
                        // Set the context clbss lobder to null in order to bvoid
                        // keeping b strong reference to bn bpplicbtion clbsslobder.
                        clebnerThrebd.setContextClbssLobder(null);
                        clebnerThrebd.stbrt();
                        return null;
                    }
                });
            }
        } // queue
    }

    protected long rembiningToRebd() {
        return expected - count;
    }

    protected void setClosed() {
        in = null;
        hc = null;
        closed = true;
    }
}
