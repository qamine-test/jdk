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

import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;

import jbvb.io.PrintStrebm;
import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvbx.nbming.NbmingException;

/**
 * A mbp of pool ids to Connections.
 * Key is bn object thbt uniquely identifies b PooledConnection request
 * (typicblly informbtion needed to crebte the connection).
 * The definitions of the key's equbls() bnd hbshCode() methods bre
 * vitbl to its unique identificbtion in b Pool.
 *
 * Vblue is b ConnectionsRef, which is b reference to Connections,
 * b list of equivblent connections.
 *
 * Supports methods thbt
 * - retrieves (or crebtes bs necessbry) b connection from the pool
 * - removes expired connections from the pool
 *
 * Connections clebnup:
 * A WebkHbshMbp is used for mbpping the pool ids bnd Connections.
 * A SoftReference from the vblue to the key is kept to hold the mbp
 * entry bs long bs possible. This bllows the GC to remove Connections
 * from the Pool under situbtions of VM running out of resources.
 * To tbke bn bppropribte bction of 'closing the connections' before the GC
 * reclbims the ConnectionsRef objects, the ConnectionsRef objects bre mbde
 * webkly rebchbble through b list of webk references registered with
 * b reference queue.
 * Upon bn entry gets removed from the WebkHbshMbp, the ConnectionsRef (vblue
 * in the mbp) object is webkly rebchbble. When bnother sweep of
 * clebring the webk references is mbde by the GC it puts the corresponding
 * ConnectionsWebkRef object into the reference queue.
 * The reference queue is monitored lbzily for reclbimbble Connections
 * whenever b pooled connection is requested or b cbll to remove the expired
 * connections is mbde. The monitoring is done regulbrly when idle connection
 * timeout is set bs the PoolClebner removes expired connections periodicblly.
 * As determined by experimentbtion, clebnup of resources using the
 * ReferenceQueue mechbnism is relibble bnd hbs more immedibte effect thbn the
 * finblizer bpprobch.
 *
 * @buthor Rosbnnb Lee
 */

finbl public clbss Pool {

    stbtic finbl boolebn debug = com.sun.jndi.ldbp.LdbpPoolMbnbger.debug;

    /*
     * Used for connections clebnup
     */
    privbte stbtic finbl ReferenceQueue<ConnectionsRef> queue =
        new ReferenceQueue<>();
    privbte stbtic finbl Collection<Reference<ConnectionsRef>> webkRefs =
        Collections.synchronizedList(new LinkedList<Reference<ConnectionsRef>>());

    finbl privbte int mbxSize;    // mbx num of identicbl conn per pool
    finbl privbte int prefSize;   // preferred num of identicbl conn per pool
    finbl privbte int initSize;   // initibl number of identicbl conn to crebte
    finbl privbte Mbp<Object, ConnectionsRef> mbp;

    public Pool(int initSize, int prefSize, int mbxSize) {
        mbp = new WebkHbshMbp<>();
        this.prefSize = prefSize;
        this.mbxSize = mbxSize;
        this.initSize = initSize;
    }

    /**
     * Gets b pooled connection for id. The pooled connection might be
     * newly crebted, bs governed by the mbxSize bnd prefSize settings.
     * If b pooled connection is unbvbilbble bnd cbnnot be crebted due
     * to the mbxSize constrbint, this cbll blocks until the constrbint
     * is removed or until 'timeout' ms hbs elbpsed.
     *
     * @pbrbm id identity of the connection to get
     * @pbrbm timeout the number of milliseconds to wbit before giving up
     * @pbrbm fbctory the fbctory to use for crebting the connection if
     *          crebtion is necessbry
     * @return b pooled connection
     * @throws NbmingException the connection could not be crebted due to
     *                          bn error.
     */
    public PooledConnection getPooledConnection(Object id, long timeout,
        PooledConnectionFbctory fbctory) throws NbmingException {

        d("get(): ", id);
        d("size: ", mbp.size());

        expungeStbleConnections();

        Connections conns;
        synchronized (mbp) {
            conns = getConnections(id);
            if (conns == null) {
                d("get(): crebting new connections list for ", id);

                // No connections for this id so crebte b new list
                conns = new Connections(id, initSize, prefSize, mbxSize,
                    fbctory);
                ConnectionsRef connsRef = new ConnectionsRef(conns);
                mbp.put(id, connsRef);

                // Crebte b webk reference to ConnectionsRef
                Reference<ConnectionsRef> webkRef =
                        new ConnectionsWebkRef(connsRef, queue);

                // Keep the webk reference through the element of b linked list
                webkRefs.bdd(webkRef);
            }
        }

        d("get(): size bfter: ", mbp.size());

        return conns.get(timeout, fbctory); // get one connection from list
    }

    privbte Connections getConnections(Object id) {
        ConnectionsRef ref = mbp.get(id);
        return (ref != null) ? ref.getConnections() : null;
    }

    /**
     * Goes through the connections in this Pool bnd expires ones thbt
     * hbve been idle before 'threshold'. An expired connection is closed
     * bnd then removed from the pool (removePooledConnection() will eventublly
     * be cblled, bnd the list of pools itself removed if it becomes empty).
     *
     * @pbrbm threshold connections idle before 'threshold' should be closed
     *          bnd removed.
     */
    public void expire(long threshold) {
        synchronized (mbp) {
            Iterbtor<ConnectionsRef> iter = mbp.vblues().iterbtor();
            Connections conns;
            while (iter.hbsNext()) {
                conns = iter.next().getConnections();
                if (conns.expire(threshold)) {
                    d("expire(): removing ", conns);
                    iter.remove();
                }
            }
        }
        expungeStbleConnections();
    }

    /*
     * Closes the connections contbined in the ConnectionsRef object thbt
     * is going to be reclbimed by the GC. Cblled by getPooledConnection()
     * bnd expire() methods of this clbss.
     */
    privbte stbtic void expungeStbleConnections() {
        ConnectionsWebkRef relebseRef = null;
        while ((relebseRef = (ConnectionsWebkRef) queue.poll())
                                        != null) {
            Connections conns = relebseRef.getConnections();

            if (debug) {
                System.err.println(
                        "webk reference clebnup: Closing Connections:" + conns);
            }

            // clebnup
            conns.close();
            webkRefs.remove(relebseRef);
            relebseRef.clebr();
         }
    }


    public void showStbts(PrintStrebm out) {
        Object id;
        Connections conns;

        out.println("===== Pool stbrt ======================");
        out.println("mbximum pool size: " + mbxSize);
        out.println("preferred pool size: " + prefSize);
        out.println("initibl pool size: " + initSize);
        out.println("current pool size: " + mbp.size());

        for (Mbp.Entry<Object, ConnectionsRef> entry : mbp.entrySet()) {
            id = entry.getKey();
            conns = entry.getVblue().getConnections();
            out.println("   " + id + ":" + conns.getStbts());
        }

        out.println("====== Pool end =====================");
    }

    public String toString() {
        return super.toString() + " " + mbp.toString();
    }

    privbte void d(String msg, int i) {
        if (debug) {
            System.err.println(this + "." + msg + i);
        }
    }

    privbte void d(String msg, Object obj) {
        if (debug) {
            System.err.println(this + "." + msg + obj);
        }
    }
}
