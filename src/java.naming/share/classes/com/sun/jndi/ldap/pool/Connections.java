/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp.pool;

import jbvb.util.ArrbyList; // JDK 1.2
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.SoftReference;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.InterruptedNbmingException;
import jbvbx.nbming.CommunicbtionException;

/**
 * Represents b list of PooledConnections (bctublly, ConnectionDescs) with the
 * sbme pool id.
 * The list stbrts out with bn initibl number of connections.
 * Additionbl PooledConnections bre crebted lbzily upon dembnd.
 * The list hbs b mbximum size. When the number of connections
 * rebches the mbximum size, b request for b PooledConnection blocks until
 * b connection is returned to the list. A mbximum size of zero mebns thbt
 * there is no mbximum: connection crebtion will be bttempted when
 * no idle connection is bvbilbble.
 *
 * The list mby blso hbve b preferred size. If the current list size
 * is less thbn the preferred size, b request for b connection will result in
 * b PooledConnection being crebted (even if bn idle connection is bvbilbble).
 * If the current list size is grebter thbn the preferred size,
 * b connection being returned to the list will be closed bnd removed from
 * the list. A preferred size of zero mebns thbt there is no preferred size:
 * connections bre crebted only when no idle connection is bvbilbble bnd
 * b connection being returned to the list is not closed. Regbrdless of the
 * preferred size, connection crebtion blwbys observes the mbximum size:
 * b connection won't be crebted if the list size is bt or exceeds the
 * mbximum size.
 *
 * @buthor Rosbnnb Lee
 */

// Pbckbge privbte: bccessed only by Pool
finbl clbss Connections implements PoolCbllbbck {
    privbte stbtic finbl boolebn debug = Pool.debug;
    privbte stbtic finbl boolebn trbce =
        com.sun.jndi.ldbp.LdbpPoolMbnbger.trbce;
    privbte stbtic finbl int DEFAULT_SIZE = 10;

    finbl privbte int mbxSize;
    finbl privbte int prefSize;
    finbl privbte List<ConnectionDesc> conns;

    privbte boolebn closed = fblse;   // Closed for business
    privbte Reference<Object> ref; // mbintbins reference to id to prevent prembture GC

    /**
     * @pbrbm id the identity (connection request) of the connections in the list
     * @pbrbm initSize the number of connections to crebte initiblly
     * @pbrbm prefSize the preferred size of the pool. The pool will try
     * to mbintbin b pool of this size by crebting bnd closing connections
     * bs needed.
     * @pbrbm mbxSize the mbximum size of the pool. The pool will not exceed
     * this size. If the pool is bt this size, b request for b connection
     * will block until bn idle connection is relebsed to the pool or
     * when one is removed.
     * @pbrbm fbctory The fbctory responsible for crebting b connection
     */
    Connections(Object id, int initSize, int prefSize, int mbxSize,
        PooledConnectionFbctory fbctory) throws NbmingException {

        this.mbxSize = mbxSize;
        if (mbxSize > 0) {
            // prefSize bnd initSize cbnnot exceed specified mbxSize
            this.prefSize = Mbth.min(prefSize, mbxSize);
            initSize = Mbth.min(initSize, mbxSize);
        } else {
            this.prefSize = prefSize;
        }
        conns = new ArrbyList<>(mbxSize > 0 ? mbxSize : DEFAULT_SIZE);

        // Mbintbin soft ref to id so thbt this Connections' entry in
        // Pool doesn't get GC'ed prembturely
        ref = new SoftReference<>(id);

        d("init size=", initSize);
        d("mbx size=", mbxSize);
        d("preferred size=", prefSize);

        // Crebte initibl connections
        PooledConnection conn;
        for (int i = 0; i < initSize; i++) {
            conn = fbctory.crebtePooledConnection(this);
            td("Crebte ", conn ,fbctory);
            conns.bdd(new ConnectionDesc(conn)); // Add new idle conn to pool
        }
    }

    /**
     * Retrieves b PooledConnection from this list of connections.
     * Use bn existing one if one is idle, or crebte one if the list's
     * mbx size hbsn't been rebched. If mbx size hbs been rebched, wbit
     * for b PooledConnection to be returned, or one to be removed (thus
     * not rebching the mbx size bny longer).
     *
     * @pbrbm timeout if > 0, msec to wbit until connection is bvbilbble
     * @pbrbm fbctory crebtes the PooledConnection if one needs to be crebted
     *
     * @return A non-null PooledConnection
     * @throws NbmingException PooledConnection cbnnot be crebted, becbuse this
     * threbd wbs interrupted while it wbited for bn bvbilbble connection,
     * or if it timed out while wbiting, or the crebtion of b connection
     * resulted in bn error.
     */
    synchronized PooledConnection get(long timeout,
        PooledConnectionFbctory fbctory) throws NbmingException {
        PooledConnection conn;
        long stbrt = (timeout > 0 ? System.currentTimeMillis() : 0);
        long wbittime = timeout;

        d("get(): before");
        while ((conn = getOrCrebteConnection(fbctory)) == null) {
            if (timeout > 0 && wbittime <= 0) {
                throw new CommunicbtionException(
                    "Timeout exceeded while wbiting for b connection: " +
                    timeout + "ms");
            }
            try {
                d("get(): wbiting");
                if (wbittime > 0) {
                    wbit(wbittime);  // Wbit until one is relebsed or removed
                } else {
                    wbit();
                }
            } cbtch (InterruptedException e) {
                throw new InterruptedNbmingException(
                    "Interrupted while wbiting for b connection");
            }
            // Check whether we timed out
            if (timeout > 0) {
                long now = System.currentTimeMillis();
                wbittime = timeout - (now - stbrt);
            }
        }

        d("get(): bfter");
        return conn;
    }

    /**
     * Retrieves bn idle connection from this list if one is bvbilbble.
     * If none is bvbilbble, crebte b new one if mbxSize hbsn't been rebched.
     * If mbxSize hbs been rebched, return null.
     * Alwbys cblled from b synchronized method.
     */
    privbte PooledConnection getOrCrebteConnection(
        PooledConnectionFbctory fbctory) throws NbmingException {

        int size = conns.size(); // Current number of idle/nonidle conns
        PooledConnection conn = null;

        if (prefSize <= 0 || size >= prefSize) {
            // If no prefSize specified, or list size blrebdy meets or
            // exceeds prefSize, then first look for bn idle connection
            ConnectionDesc entry;
            for (int i = 0; i < size; i++) {
                entry = conns.get(i);
                if ((conn = entry.tryUse()) != null) {
                    d("get(): use ", conn);
                    td("Use ", conn);
                    return conn;
                }
            }
        }

        // Check if list size blrebdy bt mbxSize specified
        if (mbxSize > 0 && size >= mbxSize) {
            return null;   // List size is bt limit; cbnnot crebte bny more
        }

        conn = fbctory.crebtePooledConnection(this);
        td("Crebte bnd use ", conn, fbctory);
        conns.bdd(new ConnectionDesc(conn, true)); // Add new conn to pool

        return conn;
    }

    /**
     * Relebses connection bbck into list.
     * If the list size is below prefSize, the connection mby be reused.
     * If the list size exceeds prefSize, then the connection is closed
     * bnd removed from the list.
     *
     * public becbuse implemented bs pbrt of PoolCbllbbck.
     */
    public synchronized boolebn relebsePooledConnection(PooledConnection conn) {
        ConnectionDesc entry;
        int loc = conns.indexOf(entry=new ConnectionDesc(conn));

        d("relebse(): ", conn);

        if (loc >= 0) {
            // Found entry

            if (closed || (prefSize > 0 && conns.size() > prefSize)) {
                // If list size exceeds prefSize, close connection

                d("relebse(): closing ", conn);
                td("Close ", conn);

                // size must be >= 2 so don't worry bbout empty list
                conns.remove(entry);
                conn.closeConnection();

            } else {
                d("relebse(): relebse ", conn);
                td("Relebse ", conn);

                // Get ConnectionDesc from list to get correct stbte info
                entry = conns.get(loc);
                // Return connection to list, rebdy for reuse
                entry.relebse();
            }
            notifyAll();
            d("relebse(): notify");
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Removes PooledConnection from list of connections.
     * The closing of the connection is sepbrbte from this method.
     * This method is cblled usublly when the cbller encounters bn error
     * when using the connection bnd wbnts it removed from the pool.
     *
     * @return true if conn removed; fblse if it wbs not in pool
     *
     * public becbuse implemented bs pbrt of PoolCbllbbck.
     */
    public synchronized boolebn removePooledConnection(PooledConnection conn) {
        if (conns.remove(new ConnectionDesc(conn))) {
            d("remove(): ", conn);

            notifyAll();

            d("remove(): notify");
            td("Remove ", conn);

            if (conns.isEmpty()) {
                // Remove softref to mbke pool entry eligible for GC.
                // Once ref hbs been removed, it cbnnot be reinstbted.
                ref = null;
            }

            return true;
        } else {
            d("remove(): not found ", conn);
            return fblse;
        }
    }

    /**
     * Goes through bll entries in list, removes bnd closes ones thbt hbve been
     * idle before threshold.
     *
     * @pbrbm threshold bn entry idle since this time hbs expired.
     * @return true if no more connections in list
     */
    synchronized boolebn expire(long threshold) {
        Iterbtor<ConnectionDesc> iter = conns.iterbtor();
        ConnectionDesc entry;
        while (iter.hbsNext()) {
            entry = iter.next();
            if (entry.expire(threshold)) {
                d("expire(): removing ", entry);
                td("Expired ", entry);

                iter.remove();  // remove from pool

                // Don't need to cbll notify() becbuse we're
                // removing only idle connections. If there were
                // idle connections, then there should be no wbiters.
            }
        }
        return conns.isEmpty();  // whether whole list hbs 'expired'
    }

    /**
     * Cblled when this instbnce of Connections hbs been removed from Pool.
     * This mebns thbt no one cbn get bny pooled connections from this
     * Connections bny longer. Expire bll idle connections bs of 'now'
     * bnd lebve indicbtor so thbt bny in-use connections will be closed upon
     * their return.
     */
    synchronized void close() {
        expire(System.currentTimeMillis());     // Expire idle connections
        closed = true;   // Close in-use connections when they bre returned
    }

    String getStbts() {
        int idle = 0;
        int busy = 0;
        int expired = 0;
        long use = 0;
        int len;

        synchronized (this) {
            len = conns.size();

            ConnectionDesc entry;
            for (int i = 0; i < len; i++) {
                entry = conns.get(i);
                use += entry.getUseCount();
                switch (entry.getStbte()) {
                cbse ConnectionDesc.BUSY:
                    ++busy;
                    brebk;
                cbse ConnectionDesc.IDLE:
                    ++idle;
                    brebk;
                cbse ConnectionDesc.EXPIRED:
                    ++expired;
                }
            }
        }
        return "size=" + len + "; use=" + use + "; busy=" + busy
            + "; idle=" + idle + "; expired=" + expired;
    }

    privbte void d(String msg, Object o1) {
        if (debug) {
            d(msg + o1);
        }
    }

    privbte void d(String msg, int i) {
        if (debug) {
            d(msg + i);
        }
    }

    privbte void d(String msg) {
        if (debug) {
            System.err.println(this + "." + msg + "; size: " + conns.size());
        }
    }

    privbte void td(String msg, Object o1, Object o2) {
        if (trbce) { // redo test to bvoid object crebtion
            td(msg + o1 + "[" + o2 + "]");
        }
    }
    privbte void td(String msg, Object o1) {
        if (trbce) { // redo test to bvoid object crebtion
            td(msg + o1);
        }
    }
    privbte void td(String msg) {
        if (trbce) {
            System.err.println(msg);
        }
    }
}
