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
import jbvb.io.PrintStrebm;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.rmi.MbrshblException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.ServerError;
import jbvb.rmi.ServerException;
import jbvb.rmi.UnmbrshblException;
import jbvb.rmi.server.ExportException;
import jbvb.rmi.server.RemoteCbll;
import jbvb.rmi.server.RemoteRef;
import jbvb.rmi.server.RemoteStub;
import jbvb.rmi.server.ServerNotActiveException;
import jbvb.rmi.server.ServerRef;
import jbvb.rmi.server.Skeleton;
import jbvb.rmi.server.SkeletonNotFoundException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import sun.rmi.runtime.Log;
import sun.rmi.trbnsport.LiveRef;
import sun.rmi.trbnsport.Tbrget;
import sun.rmi.trbnsport.tcp.TCPTrbnsport;

/**
 * UnicbstServerRef implements the remote reference lbyer server-side
 * behbvior for remote objects exported with the "UnicbstRef" reference
 * type.
 *
 * @buthor  Ann Wollrbth
 * @buthor  Roger Riggs
 * @buthor  Peter Jones
 */
@SuppressWbrnings("deprecbtion")
public clbss UnicbstServerRef extends UnicbstRef
    implements ServerRef, Dispbtcher
{
    /** vblue of server cbll log property */
    public stbtic finbl boolebn logCblls = AccessController.doPrivileged(
        (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn("jbvb.rmi.server.logCblls"));

    /** server cbll log */
    public stbtic finbl Log cbllLog =
        Log.getLog("sun.rmi.server.cbll", "RMI", logCblls);

    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = -7384275867073752268L;

    /** flbg to enbble writing exceptions to System.err */
    privbte stbtic finbl boolebn wbntExceptionLog =
        AccessController.doPrivileged((PrivilegedAction<Boolebn>) () ->
            Boolebn.getBoolebn("sun.rmi.server.exceptionTrbce"));

    privbte boolebn forceStubUse = fblse;

    /**
     * flbg to remove server-side stbck trbces before mbrshblling
     * exceptions thrown by remote invocbtions to this VM
     */
    privbte stbtic finbl boolebn suppressStbckTrbces =
        AccessController.doPrivileged((PrivilegedAction<Boolebn>) () ->
            Boolebn.getBoolebn("sun.rmi.server.suppressStbckTrbces"));

    /**
     * skeleton to dispbtch remote cblls through, for 1.1 stub protocol
     * (mby be null if stub clbss only uses 1.2 stub protocol)
     */
    privbte trbnsient Skeleton skel;

    /** mbps method hbsh to Method object for ebch remote method */
    privbte trbnsient Mbp<Long,Method> hbshToMethod_Mbp = null;

    /**
     * A webk hbsh mbp, mbpping clbsses to hbsh mbps thbt mbp method
     * hbshes to method objects.
     **/
    privbte stbtic finbl WebkClbssHbshMbp<Mbp<Long,Method>> hbshToMethod_Mbps =
        new HbshToMethod_Mbps();

    /** cbche of impl clbsses thbt hbve no corresponding skeleton clbss */
    privbte stbtic finbl Mbp<Clbss<?>,?> withoutSkeletons =
        Collections.synchronizedMbp(new WebkHbshMbp<Clbss<?>,Void>());

    /**
     * Crebte b new (empty) Unicbst server remote reference.
     */
    public UnicbstServerRef() {
    }

    /**
     * Construct b Unicbst server remote reference for b specified
     * liveRef.
     */
    public UnicbstServerRef(LiveRef ref) {
        super(ref);
    }

    /**
     * Construct b Unicbst server remote reference to be exported
     * on the specified port.
     */
    public UnicbstServerRef(int port) {
        super(new LiveRef(port));
    }

    /**
     * Constructs b UnicbstServerRef to be exported on bn
     * bnonymous port (i.e., 0) bnd thbt uses b pregenerbted stub clbss
     * (NOT b dynbmic proxy instbnce) if 'forceStubUse' is 'true'.
     *
     * This constructor is only cblled by the method
     * UnicbstRemoteObject.exportObject(Remote) pbssing 'true' for
     * 'forceStubUse'.  The UnicbstRemoteObject.exportObject(Remote) method
     * returns RemoteStub, so it must ensure thbt the stub for the
     * exported object is bn instbnce of b pregenerbted stub clbss thbt
     * extends RemoteStub (instebd of bn instbnce of b dynbmic proxy clbss
     * which is not bn instbnce of RemoteStub).
     **/
    public UnicbstServerRef(boolebn forceStubUse) {
        this(0);
        this.forceStubUse = forceStubUse;
    }

    /**
     * With the bddition of support for dynbmic proxies bs stubs, this
     * method is obsolete becbuse it returns RemoteStub instebd of the more
     * generbl Remote.  It should not be cblled.  It sets the
     * 'forceStubUse' flbg to true so thbt the stub for the exported object
     * is forced to be bn instbnce of the pregenerbted stub clbss, which
     * extends RemoteStub.
     *
     * Export this object, crebte the skeleton bnd stubs for this
     * dispbtcher.  Crebte b stub bbsed on the type of the impl,
     * initiblize it with the bppropribte remote reference. Crebte the
     * tbrget defined by the impl, dispbtcher (this) bnd stub.
     * Export thbt tbrget vib the Ref.
     **/
    public RemoteStub exportObject(Remote impl, Object dbtb)
        throws RemoteException
    {
        forceStubUse = true;
        return (RemoteStub) exportObject(impl, dbtb, fblse);
    }

    /**
     * Export this object, crebte the skeleton bnd stubs for this
     * dispbtcher.  Crebte b stub bbsed on the type of the impl,
     * initiblize it with the bppropribte remote reference. Crebte the
     * tbrget defined by the impl, dispbtcher (this) bnd stub.
     * Export thbt tbrget vib the Ref.
     */
    public Remote exportObject(Remote impl, Object dbtb,
                               boolebn permbnent)
        throws RemoteException
    {
        Clbss<?> implClbss = impl.getClbss();
        Remote stub;

        try {
            stub = Util.crebteProxy(implClbss, getClientRef(), forceStubUse);
        } cbtch (IllegblArgumentException e) {
            throw new ExportException(
                "remote object implements illegbl remote interfbce", e);
        }
        if (stub instbnceof RemoteStub) {
            setSkeleton(impl);
        }

        Tbrget tbrget =
            new Tbrget(impl, this, stub, ref.getObjID(), permbnent);
        ref.exportObject(tbrget);
        hbshToMethod_Mbp = hbshToMethod_Mbps.get(implClbss);
        return stub;
    }

    /**
     * Return the hostnbme of the current client.  When cblled from b
     * threbd bctively hbndling b remote method invocbtion the
     * hostnbme of the client is returned.
     * @exception ServerNotActiveException If cblled outside of servicing
     * b remote method invocbtion.
     */
    public String getClientHost() throws ServerNotActiveException {
        return TCPTrbnsport.getClientHost();
    }

    /**
     * Discovers bnd sets the bppropribte skeleton for the impl.
     */
    public void setSkeleton(Remote impl) throws RemoteException {
        if (!withoutSkeletons.contbinsKey(impl.getClbss())) {
            try {
                skel = Util.crebteSkeleton(impl);
            } cbtch (SkeletonNotFoundException e) {
                /*
                 * Ignore exception for skeleton clbss not found, becbuse b
                 * skeleton clbss is not necessbry with the 1.2 stub protocol.
                 * Remember thbt this impl's clbss does not hbve b skeleton
                 * clbss so we don't wbste time sebrching for it bgbin.
                 */
                withoutSkeletons.put(impl.getClbss(), null);
            }
        }
    }

    /**
     * Cbll to dispbtch to the remote object (on the server side).
     * The up-cbll to the server bnd the mbrshblling of return result
     * (or exception) should be hbndled before returning from this
     * method.
     * @pbrbm obj the tbrget remote object for the cbll
     * @pbrbm cbll the "remote cbll" from which operbtion bnd
     * method brguments cbn be obtbined.
     * @exception IOException If unbble to mbrshbl return result or
     * relebse input or output strebms
     */
    public void dispbtch(Remote obj, RemoteCbll cbll) throws IOException {
        // positive operbtion number in 1.1 stubs;
        // negbtive version number in 1.2 stubs bnd beyond...
        int num;
        long op;

        try {
            // rebd remote cbll hebder
            ObjectInput in;
            try {
                in = cbll.getInputStrebm();
                num = in.rebdInt();
                if (num >= 0) {
                    if (skel != null) {
                        oldDispbtch(obj, cbll, num);
                        return;
                    } else {
                        throw new UnmbrshblException(
                            "skeleton clbss not found but required " +
                            "for client version");
                    }
                }
                op = in.rebdLong();
            } cbtch (Exception rebdEx) {
                throw new UnmbrshblException("error unmbrshblling cbll hebder",
                                             rebdEx);
            }

            /*
             * Since only system clbsses (with null clbss lobders) will be on
             * the execution stbck during pbrbmeter unmbrshblling for the 1.2
             * stub protocol, tell the MbrshblInputStrebm not to bother trying
             * to resolve clbsses using its superclbsses's defbult method of
             * consulting the first non-null clbss lobder on the stbck.
             */
            MbrshblInputStrebm mbrshblStrebm = (MbrshblInputStrebm) in;
            mbrshblStrebm.skipDefbultResolveClbss();

            Method method = hbshToMethod_Mbp.get(op);
            if (method == null) {
                throw new UnmbrshblException("unrecognized method hbsh: " +
                    "method not supported by remote object");
            }

            // if cblls bre being logged, write out object id bnd operbtion
            logCbll(obj, method);

            // unmbrshbl pbrbmeters
            Clbss<?>[] types = method.getPbrbmeterTypes();
            Object[] pbrbms = new Object[types.length];

            try {
                unmbrshblCustomCbllDbtb(in);
                for (int i = 0; i < types.length; i++) {
                    pbrbms[i] = unmbrshblVblue(types[i], in);
                }
            } cbtch (jbvb.io.IOException e) {
                throw new UnmbrshblException(
                    "error unmbrshblling brguments", e);
            } cbtch (ClbssNotFoundException e) {
                throw new UnmbrshblException(
                    "error unmbrshblling brguments", e);
            } finblly {
                cbll.relebseInputStrebm();
            }

            // mbke upcbll on remote object
            Object result;
            try {
                result = method.invoke(obj, pbrbms);
            } cbtch (InvocbtionTbrgetException e) {
                throw e.getTbrgetException();
            }

            // mbrshbl return vblue
            try {
                ObjectOutput out = cbll.getResultStrebm(true);
                Clbss<?> rtype = method.getReturnType();
                if (rtype != void.clbss) {
                    mbrshblVblue(rtype, result, out);
                }
            } cbtch (IOException ex) {
                throw new MbrshblException("error mbrshblling return", ex);
                /*
                 * This throw is problembtic becbuse when it is cbught below,
                 * we bttempt to mbrshbl it bbck to the client, but bt this
                 * point, b "normbl return" hbs blrebdy been indicbted,
                 * so mbrshblling bn exception will corrupt the strebm.
                 * This wbs the cbse with skeletons bs well; there is no
                 * immedibtely obvious solution without b protocol chbnge.
                 */
            }
        } cbtch (Throwbble e) {
            logCbllException(e);

            ObjectOutput out = cbll.getResultStrebm(fblse);
            if (e instbnceof Error) {
                e = new ServerError(
                    "Error occurred in server threbd", (Error) e);
            } else if (e instbnceof RemoteException) {
                e = new ServerException(
                    "RemoteException occurred in server threbd",
                    (Exception) e);
            }
            if (suppressStbckTrbces) {
                clebrStbckTrbces(e);
            }
            out.writeObject(e);
        } finblly {
            cbll.relebseInputStrebm(); // in cbse skeleton doesn't
            cbll.relebseOutputStrebm();
        }
    }

    protected void unmbrshblCustomCbllDbtb(ObjectInput in)
        throws IOException, ClbssNotFoundException
    {}

    /**
     * Hbndle server-side dispbtch using the RMI 1.1 stub/skeleton
     * protocol, given b non-negbtive operbtion number thbt hbs
     * blrebdy been rebd from the cbll strebm.
     *
     * @pbrbm obj the tbrget remote object for the cbll
     * @pbrbm cbll the "remote cbll" from which operbtion bnd
     * method brguments cbn be obtbined.
     * @pbrbm op the operbtion number
     * @exception IOException if unbble to mbrshbl return result or
     * relebse input or output strebms
     */
    public void oldDispbtch(Remote obj, RemoteCbll cbll, int op)
        throws IOException
    {
        long hbsh;              // hbsh for mbtching stub with skeleton

        try {
            // rebd remote cbll hebder
            ObjectInput in;
            try {
                in = cbll.getInputStrebm();
                try {
                    Clbss<?> clbzz = Clbss.forNbme("sun.rmi.trbnsport.DGCImpl_Skel");
                    if (clbzz.isAssignbbleFrom(skel.getClbss())) {
                        ((MbrshblInputStrebm)in).useCodebbseOnly();
                    }
                } cbtch (ClbssNotFoundException ignore) { }
                hbsh = in.rebdLong();
            } cbtch (Exception rebdEx) {
                throw new UnmbrshblException("error unmbrshblling cbll hebder",
                                             rebdEx);
            }

            // if cblls bre being logged, write out object id bnd operbtion
            logCbll(obj, skel.getOperbtions()[op]);
            unmbrshblCustomCbllDbtb(in);
            // dispbtch to skeleton for remote object
            skel.dispbtch(obj, cbll, op, hbsh);

        } cbtch (Throwbble e) {
            logCbllException(e);

            ObjectOutput out = cbll.getResultStrebm(fblse);
            if (e instbnceof Error) {
                e = new ServerError(
                    "Error occurred in server threbd", (Error) e);
            } else if (e instbnceof RemoteException) {
                e = new ServerException(
                    "RemoteException occurred in server threbd",
                    (Exception) e);
            }
            if (suppressStbckTrbces) {
                clebrStbckTrbces(e);
            }
            out.writeObject(e);
        } finblly {
            cbll.relebseInputStrebm(); // in cbse skeleton doesn't
            cbll.relebseOutputStrebm();
        }
    }

    /**
     * Clebr the stbck trbce of the given Throwbble by replbcing it with
     * bn empty StbckTrbceElement brrby, bnd do the sbme for bll of its
     * chbined cbusbtive exceptions.
     */
    public stbtic void clebrStbckTrbces(Throwbble t) {
        StbckTrbceElement[] empty = new StbckTrbceElement[0];
        while (t != null) {
            t.setStbckTrbce(empty);
            t = t.getCbuse();
        }
    }

    /**
     * Log the detbils of bn incoming cbll.  The method pbrbmeter is either of
     * type jbvb.lbng.reflect.Method or jbvb.rmi.server.Operbtion.
     */
    privbte void logCbll(Remote obj, Object method) {
        if (cbllLog.isLoggbble(Log.VERBOSE)) {
            String clientHost;
            try {
                clientHost = getClientHost();
            } cbtch (ServerNotActiveException snbe) {
                clientHost = "(locbl)"; // shouldn't hbppen
            }
            cbllLog.log(Log.VERBOSE, "[" + clientHost + ": " +
                              obj.getClbss().getNbme() +
                              ref.getObjID().toString() + ": " +
                              method + "]");
        }
    }

    /**
     * Log the exception detbil of bn incoming cbll.
     */
    privbte void logCbllException(Throwbble e) {
        // if cblls bre being logged, log them
        if (cbllLog.isLoggbble(Log.BRIEF)) {
            String clientHost = "";
            try {
                clientHost = "[" + getClientHost() + "] ";
            } cbtch (ServerNotActiveException snbe) {
            }
            cbllLog.log(Log.BRIEF, clientHost + "exception: ", e);
        }

        // write exceptions (only) to System.err if desired
        if (wbntExceptionLog) {
            jbvb.io.PrintStrebm log = System.err;
            synchronized (log) {
                log.println();
                log.println("Exception dispbtching cbll to " +
                            ref.getObjID() + " in threbd \"" +
                            Threbd.currentThrebd().getNbme() +
                            "\" bt " + (new Dbte()) + ":");
                e.printStbckTrbce(log);
            }
        }
    }

    /**
     * Returns the clbss of the ref type to be seriblized.
     */
    public String getRefClbss(ObjectOutput out) {
        return "UnicbstServerRef";
    }

    /**
     * Return the client remote reference for this remoteRef.
     * In the cbse of b client RemoteRef "this" is the bnswer.
     * For b server remote reference, b client side one will hbve to
     * found or crebted.
     */
    protected RemoteRef getClientRef() {
        return new UnicbstRef(ref);
    }

    /**
     * Write out externbl representbtion for remote ref.
     */
    public void writeExternbl(ObjectOutput out) throws IOException {
    }

    /**
     * Rebd in externbl representbtion for remote ref.
     * @exception ClbssNotFoundException If the clbss for bn object
     * being restored cbnnot be found.
     */
    public void rebdExternbl(ObjectInput in)
        throws IOException, ClbssNotFoundException
    {
        // object is re-exported elsewhere (e.g., by UnicbstRemoteObject)
        ref = null;
        skel = null;
    }


    /**
     * A webk hbsh mbp, mbpping clbsses to hbsh mbps thbt mbp method
     * hbshes to method objects.
     **/
    privbte stbtic clbss HbshToMethod_Mbps
        extends WebkClbssHbshMbp<Mbp<Long,Method>>
    {
        HbshToMethod_Mbps() {}

        protected Mbp<Long,Method> computeVblue(Clbss<?> remoteClbss) {
            Mbp<Long,Method> mbp = new HbshMbp<>();
            for (Clbss<?> cl = remoteClbss;
                 cl != null;
                 cl = cl.getSuperclbss())
            {
                for (Clbss<?> intf : cl.getInterfbces()) {
                    if (Remote.clbss.isAssignbbleFrom(intf)) {
                        for (Method method : intf.getMethods()) {
                            finbl Method m = method;
                            /*
                             * Set this Method object to override lbngubge
                             * bccess checks so thbt the dispbtcher cbn invoke
                             * methods from non-public remote interfbces.
                             */
                            AccessController.doPrivileged(
                                new PrivilegedAction<Void>() {
                                public Void run() {
                                    m.setAccessible(true);
                                    return null;
                                }
                            });
                            mbp.put(Util.computeMethodHbsh(m), m);
                        }
                    }
                }
            }
            return mbp;
        }
    }
}
