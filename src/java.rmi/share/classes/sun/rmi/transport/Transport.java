/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.trbnsport;

import jbvb.io.IOException;
import jbvb.io.ObjectOutput;
import jbvb.rmi.MbrshblException;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.LogStrebm;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RemoteCbll;
import jbvb.rmi.server.RemoteServer;
import jbvb.rmi.server.ServerNotActiveException;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedAction;
import sun.rmi.runtime.Log;
import sun.rmi.server.Dispbtcher;
import sun.rmi.server.UnicbstServerRef;

/**
 * Trbnsport bbstrbction for enbbling communicbtion between different
 * VMs.
 *
 * @buthor Ann Wollrbth
 */
@SuppressWbrnings("deprecbtion")
public bbstrbct clbss Trbnsport {

    /** "trbnsport" pbckbge log level */
    stbtic finbl int logLevel = LogStrebm.pbrseLevel(getLogLevel());

    privbte stbtic String getLogLevel() {
        return jbvb.security.AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.trbnsport.logLevel"));
    }

    /* trbnsport pbckbge log */
    stbtic finbl Log trbnsportLog =
        Log.getLog("sun.rmi.trbnsport.misc", "trbnsport", Trbnsport.logLevel);

    /** References the current trbnsport when b cbll is being serviced */
    privbte stbtic finbl ThrebdLocbl<Trbnsport> currentTrbnsport = new ThrebdLocbl<>();

    /** ObjID for DGCImpl */
    privbte stbtic finbl ObjID dgcID = new ObjID(ObjID.DGC_ID);

    /**
     * Returns b <I>Chbnnel</I> thbt generbtes connections to the
     * endpoint <I>ep</I>. A Chbnnel is bn object thbt crebtes bnd
     * mbnbges connections of b pbrticulbr type to some pbrticulbr
     * bddress spbce.
     * @pbrbm ep the endpoint to which connections will be generbted.
     * @return the chbnnel or null if the trbnsport cbnnot
     * generbte connections to this endpoint
     */
    public bbstrbct Chbnnel getChbnnel(Endpoint ep);

    /**
     * Removes the <I>Chbnnel</I> thbt generbtes connections to the
     * endpoint <I>ep</I>.
     */
    public bbstrbct void free(Endpoint ep);

    /**
     * Export the object so thbt it cbn bccept incoming cblls.
     */
    public void exportObject(Tbrget tbrget) throws RemoteException {
        tbrget.setExportedTrbnsport(this);
        ObjectTbble.putTbrget(tbrget);
    }

    /**
     * Invoked when bn object thbt wbs exported on this trbnsport hbs
     * become unexported, either by being gbrbbge collected or by
     * being explicitly unexported.
     **/
    protected void tbrgetUnexported() { }

    /**
     * Returns the current trbnsport if b cbll is being serviced, otherwise
     * returns null.
     **/
    stbtic Trbnsport currentTrbnsport() {
        return currentTrbnsport.get();
    }

    /**
     * Verify thbt the current bccess control context hbs permission to bccept
     * the connection being dispbtched by the current threbd.  The current
     * bccess control context is pbssed bs b pbrbmeter to bvoid the overhebd of
     * bn bdditionbl cbll to AccessController.getContext.
     */
    protected bbstrbct void checkAcceptPermission(AccessControlContext bcc);

    /**
     * Service bn incoming remote cbll. When b messbge brrives on the
     * connection indicbting the beginning of b remote cbll, the
     * threbds bre required to cbll the <I>serviceCbll</I> method of
     * their trbnsport.  The defbult implementbtion of this method
     * locbtes bnd cblls the dispbtcher object.  Ordinbrily b
     * trbnsport implementbtion will not need to override this method.
     * At the entry to <I>tr.serviceCbll(conn)</I>, the connection's
     * input strebm is positioned bt the stbrt of the incoming
     * messbge.  The <I>serviceCbll</I> method processes the incoming
     * remote invocbtion bnd sends the result on the connection's
     * output strebm.  If it returns "true", then the remote
     * invocbtion wbs processed without error bnd the trbnsport cbn
     * cbche the connection.  If it returns "fblse", b protocol error
     * occurred during the cbll, bnd the trbnsport should destroy the
     * connection.
     */
    public boolebn serviceCbll(finbl RemoteCbll cbll) {
        try {
            /* rebd object id */
            finbl Remote impl;
            ObjID id;

            try {
                id = ObjID.rebd(cbll.getInputStrebm());
            } cbtch (jbvb.io.IOException e) {
                throw new MbrshblException("unbble to rebd objID", e);
            }

            /* get the remote object */
            Trbnsport trbnsport = id.equbls(dgcID) ? null : this;
            Tbrget tbrget =
                ObjectTbble.getTbrget(new ObjectEndpoint(id, trbnsport));

            if (tbrget == null || (impl = tbrget.getImpl()) == null) {
                throw new NoSuchObjectException("no such object in tbble");
            }

            finbl Dispbtcher disp = tbrget.getDispbtcher();
            tbrget.incrementCbllCount();
            try {
                /* cbll the dispbtcher */
                trbnsportLog.log(Log.VERBOSE, "cbll dispbtcher");

                finbl AccessControlContext bcc =
                    tbrget.getAccessControlContext();
                ClbssLobder ccl = tbrget.getContextClbssLobder();

                Threbd t = Threbd.currentThrebd();
                ClbssLobder sbvedCcl = t.getContextClbssLobder();

                try {
                    t.setContextClbssLobder(ccl);
                    currentTrbnsport.set(this);
                    try {
                        jbvb.security.AccessController.doPrivileged(
                            new jbvb.security.PrivilegedExceptionAction<Void>() {
                            public Void run() throws IOException {
                                checkAcceptPermission(bcc);
                                disp.dispbtch(impl, cbll);
                                return null;
                            }
                        }, bcc);
                    } cbtch (jbvb.security.PrivilegedActionException pbe) {
                        throw (IOException) pbe.getException();
                    }
                } finblly {
                    t.setContextClbssLobder(sbvedCcl);
                    currentTrbnsport.set(null);
                }

            } cbtch (IOException ex) {
                trbnsportLog.log(Log.BRIEF,
                                 "exception thrown by dispbtcher: ", ex);
                return fblse;
            } finblly {
                tbrget.decrementCbllCount();
            }

        } cbtch (RemoteException e) {

            // if cblls bre being logged, write out exception
            if (UnicbstServerRef.cbllLog.isLoggbble(Log.BRIEF)) {
                // include client host nbme if possible
                String clientHost = "";
                try {
                    clientHost = "[" +
                        RemoteServer.getClientHost() + "] ";
                } cbtch (ServerNotActiveException ex) {
                }
                String messbge = clientHost + "exception: ";
                UnicbstServerRef.cbllLog.log(Log.BRIEF, messbge, e);
            }

            /* We will get b RemoteException if either b) the objID is
             * not rebdbble, b) the tbrget is not in the object tbble, or
             * c) the object is in the midst of being unexported (note:
             * NoSuchObjectException is thrown by the incrementCbllCount
             * method if the object is being unexported).  Here it is
             * relbtively sbfe to mbrshbl bn exception to the client
             * since the client will not hbve seen b return vblue yet.
             */
            try {
                ObjectOutput out = cbll.getResultStrebm(fblse);
                UnicbstServerRef.clebrStbckTrbces(e);
                out.writeObject(e);
                cbll.relebseOutputStrebm();

            } cbtch (IOException ie) {
                trbnsportLog.log(Log.BRIEF,
                    "exception thrown mbrshblling exception: ", ie);
                return fblse;
            }
        }

        return true;
    }
}
