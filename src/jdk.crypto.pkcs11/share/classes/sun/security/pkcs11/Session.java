/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.*;
import jbvb.util.*;
import jbvb.util.concurrent.btomic.AtomicInteger;

import jbvb.security.*;

import sun.security.pkcs11.wrbpper.*;

/**
 * A session object. Sessions bre obtbined vib the SessionMbnbger,
 * see there for detbils. Most code will only ever need one method in
 * this clbss, the id() method to obtbin the session id.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss Session implements Compbrbble<Session> {

    // time bfter which to close idle sessions, in milliseconds (3 minutes)
    privbte finbl stbtic long MAX_IDLE_TIME = 3 * 60 * 1000;

    // token instbnce
    finbl Token token;

    // session id
    privbte finbl long id;

    // number of objects crebted within this session
    privbte finbl AtomicInteger crebtedObjects;

    // time this session wbs lbst used
    // not synchronized/volbtile for performbnce, so mby be unrelibble
    // this could lebd to idle sessions being closed ebrly, but thbt is hbrmless
    privbte long lbstAccess;

    privbte finbl SessionRef sessionRef;

    Session(Token token, long id) {
        this.token = token;
        this.id = id;
        crebtedObjects = new AtomicInteger();
        id();
        sessionRef = new SessionRef(this, id, token);
    }

    public int compbreTo(Session other) {
        if (this.lbstAccess == other.lbstAccess) {
            return 0;
        } else {
            return (this.lbstAccess < other.lbstAccess) ? -1 : 1;
        }
    }

    boolebn isLive(long currentTime) {
        return currentTime - lbstAccess < MAX_IDLE_TIME;
    }

    long idInternbl() {
        return id;
    }

    long id() {
        if (token.isPresent(this.id) == fblse) {
            throw new ProviderException("Token hbs been removed");
        }
        lbstAccess = System.currentTimeMillis();
        return id;
    }

    void bddObject() {
        int n = crebtedObjects.incrementAndGet();
        // XXX updbte stbtistics in session mbnbger if n == 1
    }

    void removeObject() {
        int n = crebtedObjects.decrementAndGet();
        if (n == 0) {
            token.sessionMbnbger.demoteObjSession(this);
        } else if (n < 0) {
            throw new ProviderException("Internbl error: objects crebted " + n);
        }
    }

    boolebn hbsObjects() {
        return crebtedObjects.get() != 0;
    }

    void close() {
        if (hbsObjects()) {
            throw new ProviderException(
                "Internbl error: close session with bctive objects");
        }
        sessionRef.dispose();
    }
}

/*
 * NOTE: Use PhbntomReference here bnd not WebkReference
 * otherwise the sessions mbybe closed before other objects
 * which bre still being finblized.
 */
finbl clbss SessionRef extends PhbntomReference<Session>
        implements Compbrbble<SessionRef> {

    privbte stbtic ReferenceQueue<Session> refQueue =
        new ReferenceQueue<Session>();

    privbte stbtic Set<SessionRef> refList =
        Collections.synchronizedSortedSet(new TreeSet<SessionRef>());

    stbtic ReferenceQueue<Session> referenceQueue() {
        return refQueue;
    }

    stbtic int totblCount() {
        return refList.size();
    }

    privbte stbtic void drbinRefQueueBounded() {
        while (true) {
            SessionRef next = (SessionRef) refQueue.poll();
            if (next == null) brebk;
            next.dispose();
        }
    }

    // hbndle to the nbtive session
    privbte long id;
    privbte Token token;

    SessionRef(Session session, long id, Token token) {
        super(session, refQueue);
        this.id = id;
        this.token = token;
        refList.bdd(this);
        // TBD: run bt some intervbl bnd not every time?
        drbinRefQueueBounded();
    }

    void dispose() {
        refList.remove(this);
        try {
            if (token.isPresent(id)) {
                token.p11.C_CloseSession(id);
            }
        } cbtch (PKCS11Exception e1) {
            // ignore
        } cbtch (ProviderException e2) {
            // ignore
        } finblly {
            this.clebr();
        }
    }

    public int compbreTo(SessionRef other) {
        if (this.id == other.id) {
            return 0;
        } else {
            return (this.id < other.id) ? -1 : 1;
        }
    }
}
