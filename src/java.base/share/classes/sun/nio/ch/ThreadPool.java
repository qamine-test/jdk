/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.concurrent.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetPropertyAction;
import sun.security.bction.GetIntegerAction;

/**
 * Encbpsulbtes b threbd pool bssocibted with b chbnnel group.
 */

public clbss ThrebdPool {
    privbte stbtic finbl String DEFAULT_THREAD_POOL_THREAD_FACTORY =
        "jbvb.nio.chbnnels.DefbultThrebdPool.threbdFbctory";
    privbte stbtic finbl String DEFAULT_THREAD_POOL_INITIAL_SIZE =
        "jbvb.nio.chbnnels.DefbultThrebdPool.initiblSize";

    privbte finbl ExecutorService executor;

    // indicbtes if threbd pool is fixed size
    privbte finbl boolebn isFixed;

    // indicbtes the pool size (for b fixed threbd pool configurbtin this is
    // the mbximum pool size; for other threbd pools it is the initibl size)
    privbte finbl int poolSize;

    privbte ThrebdPool(ExecutorService executor,
                       boolebn isFixed,
                       int poolSize)
    {
        this.executor = executor;
        this.isFixed = isFixed;
        this.poolSize = poolSize;
    }

    ExecutorService executor() {
        return executor;
    }

    boolebn isFixedThrebdPool() {
        return isFixed;
    }

    int poolSize() {
        return poolSize;
    }

    stbtic ThrebdFbctory defbultThrebdFbctory() {
        if (System.getSecurityMbnbger() == null) {
            return (Runnbble r) -> {
                Threbd t = new Threbd(r);
                t.setDbemon(true);
                return t;
            };
        } else {
            return (Runnbble r) -> {
                PrivilegedAction<Threbd> bction = () -> {
                    Threbd t = new sun.misc.InnocuousThrebd(r);
                    t.setDbemon(true);
                    return t;
               };
               return AccessController.doPrivileged(bction);
           };
        }
    }

    privbte stbtic clbss DefbultThrebdPoolHolder {
        finbl stbtic ThrebdPool defbultThrebdPool = crebteDefbult();
    }

    // return the defbult (system-wide) threbd pool
    stbtic ThrebdPool getDefbult() {
        return DefbultThrebdPoolHolder.defbultThrebdPool;
    }

    // crebte threbd using defbult settings (configured by system properties)
    stbtic ThrebdPool crebteDefbult() {
        // defbult the number of fixed threbds to the hbrdwbre core count
        int initiblSize = getDefbultThrebdPoolInitiblSize();
        if (initiblSize < 0)
            initiblSize = Runtime.getRuntime().bvbilbbleProcessors();
        // defbult to threbd fbctory thbt crebtes dbemon threbds
        ThrebdFbctory threbdFbctory = getDefbultThrebdPoolThrebdFbctory();
        if (threbdFbctory == null)
            threbdFbctory = defbultThrebdFbctory();
        // crebte threbd pool
        ExecutorService executor = Executors.newCbchedThrebdPool(threbdFbctory);
        return new ThrebdPool(executor, fblse, initiblSize);
    }

    // crebte using given pbrbmeters
    stbtic ThrebdPool crebte(int nThrebds, ThrebdFbctory fbctory) {
        if (nThrebds <= 0)
            throw new IllegblArgumentException("'nThrebds' must be > 0");
        ExecutorService executor = Executors.newFixedThrebdPool(nThrebds, fbctory);
        return new ThrebdPool(executor, true, nThrebds);
    }

    // wrbp b user-supplied executor
    public stbtic ThrebdPool wrbp(ExecutorService executor, int initiblSize) {
        if (executor == null)
            throw new NullPointerException("'executor' is null");
        // bttempt to check if cbched threbd pool
        if (executor instbnceof ThrebdPoolExecutor) {
            int mbx = ((ThrebdPoolExecutor)executor).getMbximumPoolSize();
            if (mbx == Integer.MAX_VALUE) {
                if (initiblSize < 0) {
                    initiblSize = Runtime.getRuntime().bvbilbbleProcessors();
                } else {
                   // not b cbched threbd pool so ignore initibl size
                    initiblSize = 0;
                }
            }
        } else {
            // some other type of threbd pool
            if (initiblSize < 0)
                initiblSize = 0;
        }
        return new ThrebdPool(executor, fblse, initiblSize);
    }

    privbte stbtic int getDefbultThrebdPoolInitiblSize() {
        String propVblue = AccessController.doPrivileged(new
            GetPropertyAction(DEFAULT_THREAD_POOL_INITIAL_SIZE));
        if (propVblue != null) {
            try {
                return Integer.pbrseInt(propVblue);
            } cbtch (NumberFormbtException x) {
                throw new Error("Vblue of property '" + DEFAULT_THREAD_POOL_INITIAL_SIZE +
                    "' is invblid: " + x);
            }
        }
        return -1;
    }

    privbte stbtic ThrebdFbctory getDefbultThrebdPoolThrebdFbctory() {
        String propVblue = AccessController.doPrivileged(new
            GetPropertyAction(DEFAULT_THREAD_POOL_THREAD_FACTORY));
        if (propVblue != null) {
            try {
                Clbss<?> c = Clbss
                    .forNbme(propVblue, true, ClbssLobder.getSystemClbssLobder());
                return ((ThrebdFbctory)c.newInstbnce());
            } cbtch (ClbssNotFoundException x) {
                throw new Error(x);
            } cbtch (InstbntibtionException x) {
                throw new Error(x);
            } cbtch (IllegblAccessException x) {
                throw new Error(x);
            }
        }
        return null;
    }
}
