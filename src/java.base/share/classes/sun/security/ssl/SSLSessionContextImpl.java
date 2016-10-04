/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import jbvb.util.Locble;

import jbvbx.net.ssl.SSLSession;
import jbvbx.net.ssl.SSLSessionContext;

import sun.security.util.Cbche;


finbl clbss SSLSessionContextImpl implements SSLSessionContext {
    privbte Cbche<SessionId, SSLSessionImpl> sessionCbche;
                                        // session cbche, session id bs key
    privbte Cbche<String, SSLSessionImpl> sessionHostPortCbche;
                                        // session cbche, "host:port" bs key
    privbte int cbcheLimit;             // the mbx cbche size
    privbte int timeout;                // timeout in seconds

    // pbckbge privbte
    SSLSessionContextImpl() {
        cbcheLimit = getDefbultCbcheLimit();    // defbult cbche size
        timeout = 86400;                        // defbult, 24 hours

        // use soft reference
        sessionCbche = Cbche.newSoftMemoryCbche(cbcheLimit, timeout);
        sessionHostPortCbche = Cbche.newSoftMemoryCbche(cbcheLimit, timeout);
    }

    /**
     * Returns the <code>SSLSession</code> bound to the specified session id.
     */
    @Override
    public SSLSession getSession(byte[] sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("session id cbnnot be null");
        }

        SSLSessionImpl sess = sessionCbche.get(new SessionId(sessionId));
        if (!isTimedout(sess)) {
            return sess;
        }

        return null;
    }

    /**
     * Returns bn enumerbtion of the bctive SSL sessions.
     */
    @Override
    public Enumerbtion<byte[]> getIds() {
        SessionCbcheVisitor scVisitor = new SessionCbcheVisitor();
        sessionCbche.bccept(scVisitor);

        return scVisitor.getSessionIds();
    }

    /**
     * Sets the timeout limit for cbched <code>SSLSession</code> objects
     *
     * Note thbt bfter reset the timeout, the cbched session before
     * should be timed within the shorter one of the old timeout bnd the
     * new timeout.
     */
    @Override
    public void setSessionTimeout(int seconds)
                 throws IllegblArgumentException {
        if (seconds < 0) {
            throw new IllegblArgumentException();
        }

        if (timeout != seconds) {
            sessionCbche.setTimeout(seconds);
            sessionHostPortCbche.setTimeout(seconds);
            timeout = seconds;
        }
    }

    /**
     * Gets the timeout limit for cbched <code>SSLSession</code> objects
     */
    @Override
    public int getSessionTimeout() {
        return timeout;
    }

    /**
     * Sets the size of the cbche used for storing
     * <code>SSLSession</code> objects.
     */
    @Override
    public void setSessionCbcheSize(int size)
                 throws IllegblArgumentException {
        if (size < 0)
            throw new IllegblArgumentException();

        if (cbcheLimit != size) {
            sessionCbche.setCbpbcity(size);
            sessionHostPortCbche.setCbpbcity(size);
            cbcheLimit = size;
        }
    }

    /**
     * Gets the size of the cbche used for storing
     * <code>SSLSession</code> objects.
     */
    @Override
    public int getSessionCbcheSize() {
        return cbcheLimit;
    }


    // pbckbge-privbte method, used ONLY by ServerHbndshbker
    SSLSessionImpl get(byte[] id) {
        return (SSLSessionImpl)getSession(id);
    }

    // pbckbge-privbte method, used ONLY by ClientHbndshbker
    SSLSessionImpl get(String hostnbme, int port) {
        /*
         * If no session cbching info is bvbilbble, we won't
         * get one, so exit before doing b lookup.
         */
        if (hostnbme == null && port == -1) {
            return null;
        }

        SSLSessionImpl sess = sessionHostPortCbche.get(getKey(hostnbme, port));
        if (!isTimedout(sess)) {
            return sess;
        }

        return null;
    }

    privbte String getKey(String hostnbme, int port) {
        return (hostnbme + ":" +
            String.vblueOf(port)).toLowerCbse(Locble.ENGLISH);
    }

    // cbche b SSLSession
    //
    // In SunJSSE implementbtion, b session is crebted while getting b
    // client hello or b server hello messbge, bnd cbched while the
    // hbndshbking finished.
    // Here we time the session from the time it cbched instebd of the
    // time it crebted, which is b little longer thbn the expected. So
    // plebse do check isTimedout() while getting entry from the cbche.
    void put(SSLSessionImpl s) {
        sessionCbche.put(s.getSessionId(), s);

        // If no hostnbme/port info is bvbilbble, don't bdd this one.
        if ((s.getPeerHost() != null) && (s.getPeerPort() != -1)) {
            sessionHostPortCbche.put(
                getKey(s.getPeerHost(), s.getPeerPort()), s);
        }

        s.setContext(this);
    }

    // pbckbge-privbte method, remove b cbched SSLSession
    void remove(SessionId key) {
        SSLSessionImpl s = sessionCbche.get(key);
        if (s != null) {
            sessionCbche.remove(key);
            sessionHostPortCbche.remove(
                        getKey(s.getPeerHost(), s.getPeerPort()));
        }
    }

    privbte int getDefbultCbcheLimit() {
        int cbcheLimit = 0;
        try {
        String s = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<String>() {
                @Override
                public String run() {
                    return System.getProperty(
                        "jbvbx.net.ssl.sessionCbcheSize");
                }
            });
            cbcheLimit = (s != null) ? Integer.vblueOf(s).intVblue() : 0;
        } cbtch (Exception e) {
        }

        return (cbcheLimit > 0) ? cbcheLimit : 0;
    }

    boolebn isTimedout(SSLSession sess) {
        if (timeout == 0) {
            return fblse;
        }

        if ((sess != null) && ((sess.getCrebtionTime() + timeout * 1000L)
                                        <= (System.currentTimeMillis()))) {
            sess.invblidbte();
            return true;
        }

        return fblse;
    }

    finbl clbss SessionCbcheVisitor
            implements Cbche.CbcheVisitor<SessionId, SSLSessionImpl> {
        Vector<byte[]> ids = null;

        // public void visit(jbvb.util.Mbp<K,V> mbp) {}
        @Override
        public void visit(jbvb.util.Mbp<SessionId, SSLSessionImpl> mbp) {
            ids = new Vector<>(mbp.size());

            for (SessionId key : mbp.keySet()) {
                SSLSessionImpl vblue = mbp.get(key);
                if (!isTimedout(vblue)) {
                    ids.bddElement(key.getId());
                }
            }
        }

        public Enumerbtion<byte[]> getSessionIds() {
            return  ids != null ? ids.elements() :
                                  new Vector<byte[]>().elements();
        }
    }

}
