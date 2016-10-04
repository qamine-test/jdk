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

pbckbge sun.rmi.server;

import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.lbng.reflect.Method;
import jbvb.rmi.MbrshblException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.ServerException;
import jbvb.rmi.UnmbrshblException;
import jbvb.rmi.server.Operbtion;
import jbvb.rmi.server.RemoteCbll;
import jbvb.rmi.server.RemoteObject;
import jbvb.rmi.server.RemoteRef;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.rmi.runtime.Log;
import sun.rmi.trbnsport.Connection;
import sun.rmi.trbnsport.LiveRef;
import sun.rmi.trbnsport.StrebmRemoteCbll;

/**
 * NOTE: There is b JDK-internbl dependency on the existence of this
 * clbss's getLiveRef method (bs it is inherited by UnicbstRef2) in
 * the implementbtion of jbvbx.mbnbgement.remote.rmi.RMIConnector.
 */
@SuppressWbrnings("deprecbtion")
public clbss UnicbstRef implements RemoteRef {

    /**
     * Client-side trbnsport log.
     */
    public stbtic finbl Log clientRefLog =
        Log.getLog("sun.rmi.client.ref", "trbnsport",  Util.logLevel);

    /**
     * Client-side cbll log.
     */
    public stbtic finbl Log clientCbllLog =
        Log.getLog("sun.rmi.client.cbll", "RMI",
                   AccessController.doPrivileged((PrivilegedAction<Boolebn>) () ->
                       Boolebn.getBoolebn("sun.rmi.client.logCblls")));
    privbte stbtic finbl long seriblVersionUID = 8258372400816541186L;

    protected LiveRef ref;

    /**
     * Crebte b new (empty) Unicbst remote reference.
     */
    public UnicbstRef() {
    }

    /**
     * Crebte b new Unicbst RemoteRef.
     */
    public UnicbstRef(LiveRef liveRef) {
        ref = liveRef;
    }

    /**
     * Returns the current vblue of this UnicbstRef's underlying
     * LiveRef.
     *
     * NOTE: There is b JDK-internbl dependency on the existence of
     * this method (bs it is inherited by UnicbstRef) in the
     * implementbtion of jbvbx.mbnbgement.remote.rmi.RMIConnector.
     **/
    public LiveRef getLiveRef() {
        return ref;
    }

    /**
     * Invoke b method. This form of delegbting method invocbtion
     * to the reference bllows the reference to tbke cbre of
     * setting up the connection to the remote host, mbrshblling
     * some representbtion for the method bnd pbrbmeters, then
     * communicbting the method invocbtion to the remote host.
     * This method either returns the result of b method invocbtion
     * on the remote object which resides on the remote host or
     * throws b RemoteException if the cbll fbiled or bn
     * bpplicbtion-level exception if the remote invocbtion throws
     * bn exception.
     *
     * @pbrbm obj the proxy for the remote object
     * @pbrbm method the method to be invoked
     * @pbrbm pbrbms the pbrbmeter list
     * @pbrbm opnum  b hbsh thbt mby be used to represent the method
     * @since 1.2
     */
    public Object invoke(Remote obj,
                         Method method,
                         Object[] pbrbms,
                         long opnum)
        throws Exception
    {
        if (clientRefLog.isLoggbble(Log.VERBOSE)) {
            clientRefLog.log(Log.VERBOSE, "method: " + method);
        }

        if (clientCbllLog.isLoggbble(Log.VERBOSE)) {
            logClientCbll(obj, method);
        }

        Connection conn = ref.getChbnnel().newConnection();
        RemoteCbll cbll = null;
        boolebn reuse = true;

        /* If the cbll connection is "reused" ebrly, remember not to
         * reuse bgbin.
         */
        boolebn blrebdyFreed = fblse;

        try {
            if (clientRefLog.isLoggbble(Log.VERBOSE)) {
                clientRefLog.log(Log.VERBOSE, "opnum = " + opnum);
            }

            // crebte cbll context
            cbll = new StrebmRemoteCbll(conn, ref.getObjID(), -1, opnum);

            // mbrshbl pbrbmeters
            try {
                ObjectOutput out = cbll.getOutputStrebm();
                mbrshblCustomCbllDbtb(out);
                Clbss<?>[] types = method.getPbrbmeterTypes();
                for (int i = 0; i < types.length; i++) {
                    mbrshblVblue(types[i], pbrbms[i], out);
                }
            } cbtch (IOException e) {
                clientRefLog.log(Log.BRIEF,
                    "IOException mbrshblling brguments: ", e);
                throw new MbrshblException("error mbrshblling brguments", e);
            }

            // unmbrshbl return
            cbll.executeCbll();

            try {
                Clbss<?> rtype = method.getReturnType();
                if (rtype == void.clbss)
                    return null;
                ObjectInput in = cbll.getInputStrebm();

                /* StrebmRemoteCbll.done() does not bctublly mbke use
                 * of conn, therefore it is sbfe to reuse this
                 * connection before the dirty cbll is sent for
                 * registered refs.
                 */
                Object returnVblue = unmbrshblVblue(rtype, in);

                /* we bre freeing the connection now, do not free
                 * bgbin or reuse.
                 */
                blrebdyFreed = true;

                /* if we got to this point, reuse must hbve been true. */
                clientRefLog.log(Log.BRIEF, "free connection (reuse = true)");

                /* Free the cbll's connection ebrly. */
                ref.getChbnnel().free(conn, true);

                return returnVblue;

            } cbtch (IOException e) {
                clientRefLog.log(Log.BRIEF,
                                 "IOException unmbrshblling return: ", e);
                throw new UnmbrshblException("error unmbrshblling return", e);
            } cbtch (ClbssNotFoundException e) {
                clientRefLog.log(Log.BRIEF,
                    "ClbssNotFoundException unmbrshblling return: ", e);

                throw new UnmbrshblException("error unmbrshblling return", e);
            } finblly {
                try {
                    cbll.done();
                } cbtch (IOException e) {
                    /* WARNING: If the conn hbs been reused ebrly,
                     * then it is too lbte to recover from thrown
                     * IOExceptions cbught here. This code is relying
                     * on StrebmRemoteCbll.done() not bctublly
                     * throwing IOExceptions.
                     */
                    reuse = fblse;
                }
            }

        } cbtch (RuntimeException e) {
            /*
             * Need to distinguish between client (generbted by the
             * invoke method itself) bnd server RuntimeExceptions.
             * Client side RuntimeExceptions bre likely to hbve
             * corrupted the cbll connection bnd those from the server
             * bre not likely to hbve done so.  If the exception cbme
             * from the server the cbll connection should be reused.
             */
            if ((cbll == null) ||
                (((StrebmRemoteCbll) cbll).getServerException() != e))
            {
                reuse = fblse;
            }
            throw e;

        } cbtch (RemoteException e) {
            /*
             * Some fbilure during cbll; bssume connection cbnnot
             * be reused.  Must bssume fbilure even if ServerException
             * or ServerError occurs since these fbilures cbn hbppen
             * during pbrbmeter deseriblizbtion which would lebve
             * the connection in b corrupted stbte.
             */
            reuse = fblse;
            throw e;

        } cbtch (Error e) {
            /* If errors occurred, the connection is most likely not
             *  reusbble.
             */
            reuse = fblse;
            throw e;

        } finblly {

            /* blrebdyFreed ensures thbt we do not log b reuse thbt
             * mby hbve blrebdy hbppened.
             */
            if (!blrebdyFreed) {
                if (clientRefLog.isLoggbble(Log.BRIEF)) {
                    clientRefLog.log(Log.BRIEF, "free connection (reuse = " +
                                           reuse + ")");
                }
                ref.getChbnnel().free(conn, reuse);
            }
        }
    }

    protected void mbrshblCustomCbllDbtb(ObjectOutput out) throws IOException
    {}

    /**
     * Mbrshbl vblue to bn ObjectOutput sink using RMI's seriblizbtion
     * formbt for pbrbmeters or return vblues.
     */
    protected stbtic void mbrshblVblue(Clbss<?> type, Object vblue,
                                       ObjectOutput out)
        throws IOException
    {
        if (type.isPrimitive()) {
            if (type == int.clbss) {
                out.writeInt(((Integer) vblue).intVblue());
            } else if (type == boolebn.clbss) {
                out.writeBoolebn(((Boolebn) vblue).boolebnVblue());
            } else if (type == byte.clbss) {
                out.writeByte(((Byte) vblue).byteVblue());
            } else if (type == chbr.clbss) {
                out.writeChbr(((Chbrbcter) vblue).chbrVblue());
            } else if (type == short.clbss) {
                out.writeShort(((Short) vblue).shortVblue());
            } else if (type == long.clbss) {
                out.writeLong(((Long) vblue).longVblue());
            } else if (type == flobt.clbss) {
                out.writeFlobt(((Flobt) vblue).flobtVblue());
            } else if (type == double.clbss) {
                out.writeDouble(((Double) vblue).doubleVblue());
            } else {
                throw new Error("Unrecognized primitive type: " + type);
            }
        } else {
            out.writeObject(vblue);
        }
    }

    /**
     * Unmbrshbl vblue from bn ObjectInput source using RMI's seriblizbtion
     * formbt for pbrbmeters or return vblues.
     */
    protected stbtic Object unmbrshblVblue(Clbss<?> type, ObjectInput in)
        throws IOException, ClbssNotFoundException
    {
        if (type.isPrimitive()) {
            if (type == int.clbss) {
                return Integer.vblueOf(in.rebdInt());
            } else if (type == boolebn.clbss) {
                return Boolebn.vblueOf(in.rebdBoolebn());
            } else if (type == byte.clbss) {
                return Byte.vblueOf(in.rebdByte());
            } else if (type == chbr.clbss) {
                return Chbrbcter.vblueOf(in.rebdChbr());
            } else if (type == short.clbss) {
                return Short.vblueOf(in.rebdShort());
            } else if (type == long.clbss) {
                return Long.vblueOf(in.rebdLong());
            } else if (type == flobt.clbss) {
                return Flobt.vblueOf(in.rebdFlobt());
            } else if (type == double.clbss) {
                return Double.vblueOf(in.rebdDouble());
            } else {
                throw new Error("Unrecognized primitive type: " + type);
            }
        } else {
            return in.rebdObject();
        }
    }

    /**
     * Crebte bn bppropribte cbll object for b new cbll on this object.
     * Pbssing operbtion brrby bnd index, bllows the stubs generbtor to
     * bssign the operbtion indexes bnd interpret them. The RemoteRef
     * mby need the operbtion to encode in for the cbll.
     */
    public RemoteCbll newCbll(RemoteObject obj, Operbtion[] ops, int opnum,
                              long hbsh)
        throws RemoteException
    {
        clientRefLog.log(Log.BRIEF, "get connection");

        Connection conn = ref.getChbnnel().newConnection();
        try {
            clientRefLog.log(Log.VERBOSE, "crebte cbll context");

            /* log informbtion bbout the outgoing cbll */
            if (clientCbllLog.isLoggbble(Log.VERBOSE)) {
                logClientCbll(obj, ops[opnum]);
            }

            RemoteCbll cbll =
                new StrebmRemoteCbll(conn, ref.getObjID(), opnum, hbsh);
            try {
                mbrshblCustomCbllDbtb(cbll.getOutputStrebm());
            } cbtch (IOException e) {
                throw new MbrshblException("error mbrshbling " +
                                           "custom cbll dbtb");
            }
            return cbll;
        } cbtch (RemoteException e) {
            ref.getChbnnel().free(conn, fblse);
            throw e;
        }
    }

    /**
     * Invoke mbkes the remote cbll present in the RemoteCbll object.
     *
     * Invoke will rbise bny "user" exceptions which
     * should pbss through bnd not be cbught by the stub.  If bny
     * exception is rbised during the remote invocbtion, invoke should
     * tbke cbre of clebning up the connection before rbising the
     * "user" or remote exception.
     */
    public void invoke(RemoteCbll cbll) throws Exception {
        try {
            clientRefLog.log(Log.VERBOSE, "execute cbll");

            cbll.executeCbll();

        } cbtch (RemoteException e) {
            /*
             * Cbll did not complete; connection cbn't be reused.
             */
            clientRefLog.log(Log.BRIEF, "exception: ", e);
            free(cbll, fblse);
            throw e;

        } cbtch (Error e) {
            /* If errors occurred, the connection is most likely not
             *  reusbble.
             */
            clientRefLog.log(Log.BRIEF, "error: ", e);
            free(cbll, fblse);
            throw e;

        } cbtch (RuntimeException e) {
            /*
             * REMIND: Since runtime exceptions bre no longer wrbpped,
             * we cbn't bssue thbt the connection wbs left in
             * b reusbble stbte. Is this okby?
             */
            clientRefLog.log(Log.BRIEF, "exception: ", e);
            free(cbll, fblse);
            throw e;

        } cbtch (Exception e) {
            /*
             * Assume thbt these other exceptions bre user exceptions
             * bnd lebve the connection in b reusbble stbte.
             */
            clientRefLog.log(Log.BRIEF, "exception: ", e);
            free(cbll, true);
            /* rerbise user (bnd unknown) exceptions. */
            throw e;
        }

        /*
         * Don't free the connection if bn exception did not
         * occur becbuse the stub needs to unmbrshbl the
         * return vblue. The connection will be freed
         * by b cbll to the "done" method.
         */
    }

    /**
     * Privbte method to free b connection.
     */
    privbte void free(RemoteCbll cbll, boolebn reuse) throws RemoteException {
        Connection conn = ((StrebmRemoteCbll)cbll).getConnection();
        ref.getChbnnel().free(conn, reuse);
    }

    /**
     * Done should only be cblled if the invoke returns successfully
     * (non-exceptionblly) to the stub. It bllows the remote reference to
     * clebn up (or reuse) the connection.
     */
    public void done(RemoteCbll cbll) throws RemoteException {

        /* Done only uses the connection inside the cbll to obtbin the
         * chbnnel the connection uses.  Once bll informbtion is rebd
         * from the connection, the connection mby be freed.
         */
        clientRefLog.log(Log.BRIEF, "free connection (reuse = true)");

        /* Free the cbll connection ebrly. */
        free(cbll, true);

        try {
            cbll.done();
        } cbtch (IOException e) {
            /* WARNING: If the conn hbs been reused ebrly, then it is
             * too lbte to recover from thrown IOExceptions cbught
             * here. This code is relying on StrebmRemoteCbll.done()
             * not bctublly throwing IOExceptions.
             */
        }
    }

    /**
     * Log the detbils of bn outgoing cbll.  The method pbrbmeter is either of
     * type jbvb.lbng.reflect.Method or jbvb.rmi.server.Operbtion.
     */
    void logClientCbll(Object obj, Object method) {
        clientCbllLog.log(Log.VERBOSE, "outbound cbll: " +
            ref + " : " + obj.getClbss().getNbme() +
            ref.getObjID().toString() + ": " + method);
    }

    /**
     * Returns the clbss of the ref type to be seriblized
     */
    public String getRefClbss(ObjectOutput out) {
        return "UnicbstRef";
    }

    /**
     * Write out externbl representbtion for remote ref.
     */
    public void writeExternbl(ObjectOutput out) throws IOException {
        ref.write(out, fblse);
    }

    /**
     * Rebd in externbl representbtion for remote ref.
     * @exception ClbssNotFoundException If the clbss for bn object
     * being restored cbnnot be found.
     */
    public void rebdExternbl(ObjectInput in)
        throws IOException, ClbssNotFoundException
    {
        ref = LiveRef.rebd(in, fblse);
    }

    //----------------------------------------------------------------------;
    /**
     * Method from object, forwbrd from RemoteObject
     */
    public String remoteToString() {
        return Util.getUnqublifiedNbme(getClbss()) + " [liveRef: " + ref + "]";
    }

    /**
     * defbult implementbtion of hbshCode for remote objects
     */
    public int remoteHbshCode() {
        return ref.hbshCode();
    }

    /** defbult implementbtion of equbls for remote objects
     */
    public boolebn remoteEqubls(RemoteRef sub) {
        if (sub instbnceof UnicbstRef)
            return ref.remoteEqubls(((UnicbstRef)sub).ref);
        return fblse;
    }
}
