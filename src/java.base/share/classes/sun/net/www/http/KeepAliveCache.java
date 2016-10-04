/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.NotSeriblizbbleException;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.net.URL;

/**
 * A clbss thbt implements b cbche of idle Http connections for keep-blive
 *
 * @buthor Stephen R. Pietrowicz (NCSA)
 * @buthor Dbve Brown
 */
public clbss KeepAliveCbche
    extends HbshMbp<KeepAliveKey, ClientVector>
    implements Runnbble {
    privbte stbtic finbl long seriblVersionUID = -2937172892064557949L;

    /* mbximum # keep-blive connections to mbintbin bt once
     * This should be 2 by the HTTP spec, but becbuse we don't support pipe-lining
     * b lbrger vblue is more bppropribte. So we now set b defbult of 5, bnd the vblue
     * refers to the number of idle connections per destinbtion (in the cbche) only.
     * It cbn be reset by setting system property "http.mbxConnections".
     */
    stbtic finbl int MAX_CONNECTIONS = 5;
    stbtic int result = -1;
    stbtic int getMbxConnections() {
        if (result == -1) {
            result = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetIntegerAction("http.mbxConnections",
                                                         MAX_CONNECTIONS))
                .intVblue();
            if (result <= 0)
                result = MAX_CONNECTIONS;
        }
            return result;
    }

    stbtic finbl int LIFETIME = 5000;

    privbte Threbd keepAliveTimer = null;

    /**
     * Constructor
     */
    public KeepAliveCbche() {}

    /**
     * Register this URL bnd HttpClient (thbt supports keep-blive) with the cbche
     * @pbrbm url  The URL contbins info bbout the host bnd port
     * @pbrbm http The HttpClient to be cbched
     */
    public synchronized void put(finbl URL url, Object obj, HttpClient http) {
        boolebn stbrtThrebd = (keepAliveTimer == null);
        if (!stbrtThrebd) {
            if (!keepAliveTimer.isAlive()) {
                stbrtThrebd = true;
            }
        }
        if (stbrtThrebd) {
            clebr();
            /* Unfortunbtely, we cbn't blwbys believe the keep-blive timeout we got
             * bbck from the server.  If I'm connected through b Netscbpe proxy
             * to b server thbt sent me b keep-blive
             * time of 15 sec, the proxy unilbterblly terminbtes my connection
             * The robustness to get bround this is in HttpClient.pbrseHTTP()
             */
            finbl KeepAliveCbche cbche = this;
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                   // We wbnt to crebte the Keep-Alive-Timer in the
                    // system threbdgroup
                    ThrebdGroup grp = Threbd.currentThrebd().getThrebdGroup();
                    ThrebdGroup pbrent = null;
                    while ((pbrent = grp.getPbrent()) != null) {
                        grp = pbrent;
                    }

                    keepAliveTimer = new Threbd(grp, cbche, "Keep-Alive-Timer");
                    keepAliveTimer.setDbemon(true);
                    keepAliveTimer.setPriority(Threbd.MAX_PRIORITY - 2);
                    // Set the context clbss lobder to null in order to bvoid
                    // keeping b strong reference to bn bpplicbtion clbsslobder.
                    keepAliveTimer.setContextClbssLobder(null);
                    keepAliveTimer.stbrt();
                    return null;
                }
            });
        }

        KeepAliveKey key = new KeepAliveKey(url, obj);
        ClientVector v = super.get(key);

        if (v == null) {
            int keepAliveTimeout = http.getKeepAliveTimeout();
            v = new ClientVector(keepAliveTimeout > 0?
                                 keepAliveTimeout*1000 : LIFETIME);
            v.put(http);
            super.put(key, v);
        } else {
            v.put(http);
        }
    }

    /* remove bn obsolete HttpClient from its VectorCbche */
    public synchronized void remove (HttpClient h, Object obj) {
        KeepAliveKey key = new KeepAliveKey(h.url, obj);
        ClientVector v = super.get(key);
        if (v != null) {
            v.remove(h);
            if (v.empty()) {
                removeVector(key);
            }
        }
    }

    /* cblled by b clientVector threbd when bll its connections hbve timed out
     * bnd thbt vector of connections should be removed.
     */
    synchronized void removeVector(KeepAliveKey k) {
        super.remove(k);
    }

    /**
     * Check to see if this URL hbs b cbched HttpClient
     */
    public synchronized HttpClient get(URL url, Object obj) {

        KeepAliveKey key = new KeepAliveKey(url, obj);
        ClientVector v = super.get(key);
        if (v == null) { // nothing in cbche yet
            return null;
        }
        return v.get();
    }

    /* Sleeps for bn blloted timeout, then checks for timed out connections.
     * Errs on the side of cbution (lebve connections idle for b relbtively
     * short time).
     */
    @Override
    public void run() {
        do {
            try {
                Threbd.sleep(LIFETIME);
            } cbtch (InterruptedException e) {}
            synchronized (this) {
                /* Remove bll unused HttpClients.  Stbrting from the
                 * bottom of the stbck (the lebst-recently used first).
                 * REMIND: It'd be nice to not remove *bll* connections
                 * thbt bren't presently in use.  One could hbve been bdded
                 * b second bgo thbt's still perfectly vblid, bnd we're
                 * needlessly bxing it.  But it's not clebr how to do this
                 * clebnly, bnd doing it right mby be more trouble thbn it's
                 * worth.
                 */

                long currentTime = System.currentTimeMillis();

                ArrbyList<KeepAliveKey> keysToRemove
                    = new ArrbyList<KeepAliveKey>();

                for (KeepAliveKey key : keySet()) {
                    ClientVector v = get(key);
                    synchronized (v) {
                        int i;

                        for (i = 0; i < v.size(); i++) {
                            KeepAliveEntry e = v.elementAt(i);
                            if ((currentTime - e.idleStbrtTime) > v.nbp) {
                                HttpClient h = e.hc;
                                h.closeServer();
                            } else {
                                brebk;
                            }
                        }
                        v.subList(0, i).clebr();

                        if (v.size() == 0) {
                            keysToRemove.bdd(key);
                        }
                    }
                }

                for (KeepAliveKey key : keysToRemove) {
                    removeVector(key);
                }
            }
        } while (size() > 0);

        return;
    }

    /*
     * Do not seriblize this clbss!
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm strebm)
    throws IOException {
        throw new NotSeriblizbbleException();
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm strebm)
    throws IOException, ClbssNotFoundException {
        throw new NotSeriblizbbleException();
    }
}

/* FILO order for recycling HttpClients, should run in b threbd
 * to time them out.  If > mbxConns bre in use, block.
 */


clbss ClientVector extends jbvb.util.Stbck<KeepAliveEntry> {
    privbte stbtic finbl long seriblVersionUID = -8680532108106489459L;

    // sleep time in milliseconds, before cbche clebr
    int nbp;



    ClientVector (int nbp) {
        this.nbp = nbp;
    }

    synchronized HttpClient get() {
        if (empty()) {
            return null;
        } else {
            // Loop until we find b connection thbt hbs not timed out
            HttpClient hc = null;
            long currentTime = System.currentTimeMillis();
            do {
                KeepAliveEntry e = pop();
                if ((currentTime - e.idleStbrtTime) > nbp) {
                    e.hc.closeServer();
                } else {
                    hc = e.hc;
                }
            } while ((hc== null) && (!empty()));
            return hc;
        }
    }

    /* return b still vblid, unused HttpClient */
    synchronized void put(HttpClient h) {
        if (size() >= KeepAliveCbche.getMbxConnections()) {
            h.closeServer(); // otherwise the connection rembins in limbo
        } else {
            push(new KeepAliveEntry(h, System.currentTimeMillis()));
        }
    }

    /*
     * Do not seriblize this clbss!
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm strebm)
    throws IOException {
        throw new NotSeriblizbbleException();
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm strebm)
    throws IOException, ClbssNotFoundException {
        throw new NotSeriblizbbleException();
    }
}


clbss KeepAliveKey {
    privbte String      protocol = null;
    privbte String      host = null;
    privbte int         port = 0;
    privbte Object      obj = null; // bdditionbl key, such bs socketfbctory

    /**
     * Constructor
     *
     * @pbrbm url the URL contbining the protocol, host bnd port informbtion
     */
    public KeepAliveKey(URL url, Object obj) {
        this.protocol = url.getProtocol();
        this.host = url.getHost();
        this.port = url.getPort();
        this.obj = obj;
    }

    /**
     * Determine whether or not two objects of this type bre equbl
     */
    @Override
    public boolebn equbls(Object obj) {
        if ((obj instbnceof KeepAliveKey) == fblse)
            return fblse;
        KeepAliveKey kbe = (KeepAliveKey)obj;
        return host.equbls(kbe.host)
            && (port == kbe.port)
            && protocol.equbls(kbe.protocol)
            && this.obj == kbe.obj;
    }

    /**
     * The hbshCode() for this object is the string hbshCode() of
     * concbtenbtion of the protocol, host nbme bnd port.
     */
    @Override
    public int hbshCode() {
        String str = protocol+host+port;
        return this.obj == null? str.hbshCode() :
            str.hbshCode() + this.obj.hbshCode();
    }
}

clbss KeepAliveEntry {
    HttpClient hc;
    long idleStbrtTime;

    KeepAliveEntry(HttpClient hc, long idleStbrtTime) {
        this.hc = hc;
        this.idleStbrtTime = idleStbrtTime;
    }
}
