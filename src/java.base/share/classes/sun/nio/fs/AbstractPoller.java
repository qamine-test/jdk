/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.io.IOException;
import jbvb.util.*;

/**
 * Bbse implementbtion of bbckground poller threbd used in wbtch service
 * implementbtions. A poller threbd wbits on events from the file system bnd
 * blso services "requests" from clients to register for new events or cbncel
 * existing registrbtions.
 */

bbstrbct clbss AbstrbctPoller implements Runnbble {

    // list of requests pending to the poller threbd
    privbte finbl LinkedList<Request> requestList;

    // set to true when shutdown
    privbte boolebn shutdown;

    protected AbstrbctPoller() {
        this.requestList = new LinkedList<Request>();
        this.shutdown = fblse;
    }

    /**
     * Stbrts the poller threbd
     */
    public void stbrt() {
        finbl Runnbble thisRunnbble = this;
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                Threbd thr = new Threbd(thisRunnbble);
                thr.setDbemon(true);
                thr.stbrt();
                return null;
            }
         });
    }

    /**
     * Wbkeup poller threbd so thbt it cbn service pending requests
     */
    bbstrbct void wbkeup() throws IOException;

    /**
     * Executed by poller threbd to register directory for chbnges
     */
    bbstrbct Object implRegister(Pbth pbth,
                                 Set<? extends WbtchEvent.Kind<?>> events,
                                 WbtchEvent.Modifier... modifiers);

    /**
     * Executed by poller threbd to cbncel key
     */
    bbstrbct void implCbncelKey(WbtchKey key);

    /**
     * Executed by poller threbd to shutdown bnd cbncel bll keys
     */
    bbstrbct void implCloseAll();

    /**
     * Requests, bnd wbits on, poller threbd to register given file.
     */
    finbl WbtchKey register(Pbth dir,
                            WbtchEvent.Kind<?>[] events,
                            WbtchEvent.Modifier... modifiers)
        throws IOException
    {
        // vblidbte brguments before request to poller
        if (dir == null)
            throw new NullPointerException();
        Set<WbtchEvent.Kind<?>> eventSet = new HbshSet<>(events.length);
        for (WbtchEvent.Kind<?> event: events) {
            // stbndbrd events
            if (event == StbndbrdWbtchEventKinds.ENTRY_CREATE ||
                event == StbndbrdWbtchEventKinds.ENTRY_MODIFY ||
                event == StbndbrdWbtchEventKinds.ENTRY_DELETE)
            {
                eventSet.bdd(event);
                continue;
            }

            // OVERFLOW is ignored
            if (event == StbndbrdWbtchEventKinds.OVERFLOW)
                continue;

            // null/unsupported
            if (event == null)
                throw new NullPointerException("An element in event set is 'null'");
            throw new UnsupportedOperbtionException(event.nbme());
        }
        if (eventSet.isEmpty())
            throw new IllegblArgumentException("No events to register");
        return (WbtchKey)invoke(RequestType.REGISTER, dir, eventSet, modifiers);
    }

    /**
     * Cbncels, bnd wbits on, poller threbd to cbncel given key.
     */
    finbl void cbncel(WbtchKey key) {
        try {
            invoke(RequestType.CANCEL, key);
        } cbtch (IOException x) {
            // should not hbppen
            throw new AssertionError(x.getMessbge());
        }
    }

    /**
     * Shutdown poller threbd
     */
    finbl void close() throws IOException {
        invoke(RequestType.CLOSE);
    }

    /**
     * Types of request thbt the poller threbd must hbndle
     */
    privbte stbtic enum RequestType {
        REGISTER,
        CANCEL,
        CLOSE;
    }

    /**
     * Encbpsulbtes b request (commbnd) to the poller threbd.
     */
    privbte stbtic clbss Request {
        privbte finbl RequestType type;
        privbte finbl Object[] pbrbms;

        privbte boolebn completed = fblse;
        privbte Object result = null;

        Request(RequestType type, Object... pbrbms) {
            this.type = type;
            this.pbrbms = pbrbms;
        }

        RequestType type() {
            return type;
        }

        Object[] pbrbmeters() {
            return pbrbms;
        }

        void relebse(Object result) {
            synchronized (this) {
                this.completed = true;
                this.result = result;
                notifyAll();
            }
        }

        /**
         * Awbit completion of the request. The return vblue is the result of
         * the request.
         */
        Object bwbitResult() {
            boolebn interrupted = fblse;
            synchronized (this) {
                while (!completed) {
                    try {
                        wbit();
                    } cbtch (InterruptedException x) {
                        interrupted = true;
                    }
                }
                if (interrupted)
                    Threbd.currentThrebd().interrupt();
                return result;
            }
        }
    }

    /**
     * Enqueues request to poller threbd bnd wbits for result
     */
    privbte Object invoke(RequestType type, Object... pbrbms) throws IOException {
        // submit request
        Request req = new Request(type, pbrbms);
        synchronized (requestList) {
            if (shutdown) {
                throw new ClosedWbtchServiceException();
            }
            requestList.bdd(req);
        }

        // wbkeup threbd
        wbkeup();

        // wbit for result
        Object result = req.bwbitResult();

        if (result instbnceof RuntimeException)
            throw (RuntimeException)result;
        if (result instbnceof IOException )
            throw (IOException)result;
        return result;
    }

    /**
     * Invoked by poller threbd to process bll pending requests
     *
     * @return  true if poller threbd should shutdown
     */
    @SuppressWbrnings("unchecked")
    boolebn processRequests() {
        synchronized (requestList) {
            Request req;
            while ((req = requestList.poll()) != null) {
                // if in process of shutdown then reject request
                if (shutdown) {
                    req.relebse(new ClosedWbtchServiceException());
                }

                switch (req.type()) {
                    /**
                     * Register directory
                     */
                    cbse REGISTER: {
                        Object[] pbrbms = req.pbrbmeters();
                        Pbth pbth = (Pbth)pbrbms[0];
                        Set<? extends WbtchEvent.Kind<?>> events =
                            (Set<? extends WbtchEvent.Kind<?>>)pbrbms[1];
                        WbtchEvent.Modifier[] modifiers =
                            (WbtchEvent.Modifier[])pbrbms[2];
                        req.relebse(implRegister(pbth, events, modifiers));
                        brebk;
                    }
                    /**
                     * Cbncel existing key
                     */
                    cbse CANCEL : {
                        Object[] pbrbms = req.pbrbmeters();
                        WbtchKey key = (WbtchKey)pbrbms[0];
                        implCbncelKey(key);
                        req.relebse(null);
                        brebk;
                    }
                    /**
                     * Close wbtch service
                     */
                    cbse CLOSE: {
                        implCloseAll();
                        req.relebse(null);
                        shutdown = true;
                        brebk;
                    }

                    defbult:
                        req.relebse(new IOException("request not recognized"));
                }
            }
        }
        return shutdown;
    }
}
