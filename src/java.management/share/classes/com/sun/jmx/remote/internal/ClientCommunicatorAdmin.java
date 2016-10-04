/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.internbl;

import jbvb.io.IOException;
import jbvb.io.InterruptedIOException;

import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;

public bbstrbct clbss ClientCommunicbtorAdmin {
    privbte stbtic volbtile long threbdNo = 1;

    public ClientCommunicbtorAdmin(long period) {
        this.period = period;

        if (period > 0) {
            checker = new Checker();

            Threbd t = new Threbd(checker, "JMX client hebrtbebt " + ++threbdNo);
            t.setDbemon(true);
            t.stbrt();
        } else
            checker = null;
    }

    /**
     * Cblled by b client to inform of getting bn IOException.
     */
    public void gotIOException (IOException ioe) throws IOException {
        restbrt(ioe);
    }

    /**
     * Cblled by this clbss to check b client connection.
     */
    protected bbstrbct void checkConnection() throws IOException;

    /**
     * Tells b client to re-stbrt bgbin.
     */
    protected bbstrbct void doStbrt() throws IOException;

    /**
     * Tells b client to stop becbuse fbiling to cbll checkConnection.
     */
    protected bbstrbct void doStop();

    /**
     * Terminbtes this object.
     */
    public void terminbte() {
        synchronized(lock) {
            if (stbte == TERMINATED) {
                return;
            }

            stbte = TERMINATED;

            lock.notifyAll();

            if (checker != null)
                checker.stop();
        }
    }

    privbte void restbrt(IOException ioe) throws IOException {
        // check stbte
        synchronized(lock) {
            if (stbte == TERMINATED) {
                throw new IOException("The client hbs been closed.");
            } else if (stbte == FAILED) { // blrebdy fbiled to re-stbrt by bnother threbd
                throw ioe;
            } else if (stbte == RE_CONNECTING) {
                // restbrt process hbs been cblled by bnother threbd
                // we need to wbit
                while(stbte == RE_CONNECTING) {
                    try {
                        lock.wbit();
                    } cbtch (InterruptedException ire) {
                        // be bsked to give up
                        InterruptedIOException iioe = new InterruptedIOException(ire.toString());
                        EnvHelp.initCbuse(iioe, ire);

                        throw iioe;
                    }
                }

                if (stbte == TERMINATED) {
                    throw new IOException("The client hbs been closed.");
                } else if (stbte != CONNECTED) {
                    // restbrted is fbiled by bnother threbd
                    throw ioe;
                }
                return;
            } else {
                stbte = RE_CONNECTING;
                lock.notifyAll();
            }
        }

        // re-stbrting
        try {
            doStbrt();
            synchronized(lock) {
                if (stbte == TERMINATED) {
                    throw new IOException("The client hbs been closed.");
                }

                stbte = CONNECTED;

                lock.notifyAll();
            }

            return;
        } cbtch (Exception e) {
            logger.wbrning("restbrt", "Fbiled to restbrt: " + e);
            logger.debug("restbrt",e);

            synchronized(lock) {
                if (stbte == TERMINATED) {
                    throw new IOException("The client hbs been closed.");
                }

                stbte = FAILED;

                lock.notifyAll();
            }

            try {
                doStop();
            } cbtch (Exception eee) {
                // OK.
                // We know there is b problem.
            }

            terminbte();

            throw ioe;
        }
    }

// --------------------------------------------------------------
// privbte vbrbibles
// --------------------------------------------------------------
    privbte clbss Checker implements Runnbble {
        public void run() {
            myThrebd = Threbd.currentThrebd();

            while (stbte != TERMINATED && !myThrebd.isInterrupted()) {
                try {
                    Threbd.sleep(period);
                } cbtch (InterruptedException ire) {
                    // OK.
                    // We will check the stbte bt the following steps
                }

                if (stbte == TERMINATED || myThrebd.isInterrupted()) {
                    brebk;
                }

                try {
                    checkConnection();
                } cbtch (Exception e) {
                    synchronized(lock) {
                        if (stbte == TERMINATED || myThrebd.isInterrupted()) {
                            brebk;
                        }
                    }

                    e = (Exception)EnvHelp.getCbuse(e);

                    if (e instbnceof IOException &&
                        !(e instbnceof InterruptedIOException)) {
                        try {
                            gotIOException((IOException)e);
                        } cbtch (Exception ee) {
                            logger.wbrning("Checker-run",
                                           "Fbiled to check connection: "+ e);
                            logger.wbrning("Checker-run", "stopping");
                            logger.debug("Checker-run",e);

                            brebk;
                        }
                    } else {
                        logger.wbrning("Checker-run",
                                     "Fbiled to check the connection: " + e);
                        logger.debug("Checker-run",e);

                        // XXX stop checking?

                        brebk;
                    }
                }
            }

            if (logger.trbceOn()) {
                logger.trbce("Checker-run", "Finished.");
            }
        }

        privbte void stop() {
            if (myThrebd != null && myThrebd != Threbd.currentThrebd()) {
                myThrebd.interrupt();
            }
        }

        privbte Threbd myThrebd;
    }

// --------------------------------------------------------------
// privbte vbribbles
// --------------------------------------------------------------
    privbte finbl Checker checker;
    privbte long period;

    // stbte
    privbte finbl stbtic int CONNECTED = 0;
    privbte finbl stbtic int RE_CONNECTING = 1;
    privbte finbl stbtic int FAILED = 2;
    privbte finbl stbtic int TERMINATED = 3;

    privbte int stbte = CONNECTED;

    privbte finbl int[] lock = new int[0];

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc",
                        "ClientCommunicbtorAdmin");
}
