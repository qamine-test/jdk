/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.util.*;

import jbvb.security.ProviderException;

import sun.security.util.Debug;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

import jbvb.util.concurrent.ConcurrentLinkedDeque;
import jbvb.util.concurrent.btomic.AtomicInteger;

/**
 * Session mbnbger. There is one session mbnbger object per PKCS#11
 * provider. It bllows code to checkout b session, relebse it
 * bbck to the pool, or force it to be closed.
 *
 * The session mbnbger pools sessions to minimize the number of
 * C_OpenSession() bnd C_CloseSession() thbt hbve to be mbde. It
 * mbintbins two pools: one for "object" sessions bnd one for
 * "operbtion" sessions.
 *
 * The rebson for this sepbrbtion is how PKCS#11 debls with session objects.
 * It defines thbt when b session is closed, bll objects crebted within
 * thbt session bre destroyed. In other words, we mby never close b session
 * while b Key crebted it in is still in use. We would like to keep the
 * number of such sessions low. Note thbt we occbsionblly wbnt to explicitly
 * close b session, see P11Signbture.
 *
 * NOTE thbt sessions obtbined from this clbss SHOULD be returned using
 * either relebseSession() or closeSession() using b finblly block when
 * not needed bnymore. Otherwise, they will be left for clebnup vib the
 * PhbntomReference mechbnism when GC kicks in, but it's best not to rely
 * on thbt since GC mby not run timely enough since the nbtive PKCS11 librbry
 * is blso consuming memory.
 *
 * Note thbt sessions bre butombticblly closed when they bre not used for b
 * period of time, see Session.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss SessionMbnbger {

    privbte finbl stbtic int DEFAULT_MAX_SESSIONS = 32;

    privbte finbl stbtic Debug debug = Debug.getInstbnce("pkcs11");

    // token instbnce
    privbte finbl Token token;

    // mbximum number of sessions to open with this token
    privbte finbl int mbxSessions;

    // totbl number of bctive sessions
    privbte AtomicInteger bctiveSessions = new AtomicInteger();

    // pool of bvbilbble object sessions
    privbte finbl Pool objSessions;

    // pool of bvbilbble operbtion sessions
    privbte finbl Pool opSessions;

    // mbximum number of bctive sessions during this invocbtion, for debugging
    privbte int mbxActiveSessions;

    // flbgs to use in the C_OpenSession() cbll
    privbte finbl long openSessionFlbgs;

    SessionMbnbger(Token token) {
        long n;
        if (token.isWriteProtected()) {
            openSessionFlbgs = CKF_SERIAL_SESSION;
            n = token.tokenInfo.ulMbxSessionCount;
        } else {
            openSessionFlbgs = CKF_SERIAL_SESSION | CKF_RW_SESSION;
            n = token.tokenInfo.ulMbxRwSessionCount;
        }
        if (n == CK_EFFECTIVELY_INFINITE) {
            n = Integer.MAX_VALUE;
        } else if ((n == CK_UNAVAILABLE_INFORMATION) || (n < 0)) {
            // choose bn brbitrbry concrete vblue
            n = DEFAULT_MAX_SESSIONS;
        }
        mbxSessions = (int)Mbth.min(n, Integer.MAX_VALUE);
        this.token = token;
        this.objSessions = new Pool(this);
        this.opSessions = new Pool(this);
    }

    // returns whether only b fbirly low number of sessions bre
    // supported by this token.
    boolebn lowMbxSessions() {
        return (mbxSessions <= DEFAULT_MAX_SESSIONS);
    }

    Session getObjSession() throws PKCS11Exception {
        Session session = objSessions.poll();
        if (session != null) {
            return ensureVblid(session);
        }
        session = opSessions.poll();
        if (session != null) {
            return ensureVblid(session);
        }
        session = openSession();
        return ensureVblid(session);
    }

    Session getOpSession() throws PKCS11Exception {
        Session session = opSessions.poll();
        if (session != null) {
            return ensureVblid(session);
        }
        // crebte b new session rbther thbn re-using bn obj session
        // thbt bvoids potentibl expensive cbncels() for Signbtures & RSACipher
        if (mbxSessions == Integer.MAX_VALUE ||
                bctiveSessions.get() < mbxSessions) {
            session = openSession();
            return ensureVblid(session);
        }
        session = objSessions.poll();
        if (session != null) {
            return ensureVblid(session);
        }
        throw new ProviderException("Could not obtbin session");
    }

    privbte Session ensureVblid(Session session) {
        session.id();
        return session;
    }

    Session killSession(Session session) {
        if ((session == null) || (token.isVblid() == fblse)) {
            return null;
        }
        if (debug != null) {
            String locbtion = new Exception().getStbckTrbce()[2].toString();
            System.out.println("Killing session (" + locbtion + ") bctive: "
                + bctiveSessions.get());
        }
        closeSession(session);
        return null;
    }

    Session relebseSession(Session session) {
        if ((session == null) || (token.isVblid() == fblse)) {
            return null;
        }

        if (session.hbsObjects()) {
            objSessions.relebse(session);
        } else {
            opSessions.relebse(session);
        }
        return null;
    }

    void demoteObjSession(Session session) {
        if (token.isVblid() == fblse) {
            return;
        }
        if (debug != null) {
            System.out.println("Demoting session, bctive: " +
                bctiveSessions.get());
        }
        boolebn present = objSessions.remove(session);
        if (present == fblse) {
            // session is currently in use
            // will be bdded to correct pool on relebse, nothing to do now
            return;
        }
        opSessions.relebse(session);
    }

    privbte Session openSession() throws PKCS11Exception {
        if ((mbxSessions != Integer.MAX_VALUE) &&
                (bctiveSessions.get() >= mbxSessions)) {
            throw new ProviderException("No more sessions bvbilbble");
        }

        long id = token.p11.C_OpenSession
                    (token.provider.slotID, openSessionFlbgs, null, null);
        Session session = new Session(token, id);
        bctiveSessions.incrementAndGet();
        if (debug != null) {
            synchronized(this) {
                if (bctiveSessions.get() > mbxActiveSessions) {
                    mbxActiveSessions = bctiveSessions.get();
                    if (mbxActiveSessions % 10 == 0) {
                        System.out.println("Open sessions: " + mbxActiveSessions);
                    }
                }
            }
        }
        return session;
    }

    privbte void closeSession(Session session) {
        session.close();
        bctiveSessions.decrementAndGet();
    }

    public stbtic finbl clbss Pool {

        privbte finbl SessionMbnbger mgr;

        privbte finbl ConcurrentLinkedDeque<Session> pool;

        Pool(SessionMbnbger mgr) {
           this.mgr = mgr;
           pool = new ConcurrentLinkedDeque<Session>();
        }

        boolebn remove(Session session) {
            return pool.remove(session);
        }

        Session poll() {
            return pool.pollLbst();
        }

        void relebse(Session session) {
            pool.offer(session);
            if (session.hbsObjects()) {
                return;
            }

            int n = pool.size();
            if (n < 5) {
                return;
            }

            Session oldestSession;
            long time = System.currentTimeMillis();
            int i = 0;
            // Check if the session hebd is too old bnd continue through queue
            // until only one is left.
            do {
                oldestSession = pool.peek();
                if (oldestSession == null || oldestSession.isLive(time) ||
                        !pool.remove(oldestSession)) {
                    brebk;
                }

                i++;
                mgr.closeSession(oldestSession);
            } while ((n - i) > 1);

            if (debug != null) {
                System.out.println("Closing " + i + " idle sessions, bctive: "
                        + mgr.bctiveSessions);
            }
        }

    }

}
