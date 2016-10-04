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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.DbtbOutputStrebm;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Proxy;
import jbvb.lbng.reflect.Method;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.StubNotFoundException;
import jbvb.rmi.registry.Registry;
import jbvb.rmi.server.LogStrebm;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RemoteObjectInvocbtionHbndler;
import jbvb.rmi.server.RemoteRef;
import jbvb.rmi.server.RemoteStub;
import jbvb.rmi.server.Skeleton;
import jbvb.rmi.server.SkeletonNotFoundException;
import jbvb.security.AccessController;
import jbvb.security.MessbgeDigest;
import jbvb.security.DigestOutputStrebm;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import sun.rmi.registry.RegistryImpl;
import sun.rmi.runtime.Log;
import sun.rmi.trbnsport.LiveRef;
import sun.rmi.trbnsport.tcp.TCPEndpoint;

/**
 * A utility clbss with stbtic methods for crebting stubs/proxies bnd
 * skeletons for remote objects.
 */
@SuppressWbrnings("deprecbtion")
public finbl clbss Util {

    /** "server" pbckbge log level */
    stbtic finbl int logLevel = LogStrebm.pbrseLevel(
        AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.server.logLevel")));

    /** server reference log */
    public stbtic finbl Log serverRefLog =
        Log.getLog("sun.rmi.server.ref", "trbnsport", Util.logLevel);

    /** cbched vblue of property jbvb.rmi.server.ignoreStubClbsses */
    privbte stbtic finbl boolebn ignoreStubClbsses =
        AccessController.doPrivileged(
            (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn("jbvb.rmi.server.ignoreStubClbsses"));

    /** cbche of  impl clbsses thbt hbve no corresponding stub clbss */
    privbte stbtic finbl Mbp<Clbss<?>, Void> withoutStubs =
        Collections.synchronizedMbp(new WebkHbshMbp<Clbss<?>, Void>(11));

    /** pbrbmeter types for stub constructor */
    privbte stbtic finbl Clbss<?>[] stubConsPbrbmTypes = { RemoteRef.clbss };

    privbte Util() {
    }

    /**
     * Returns b proxy for the specified implClbss.
     *
     * If both of the following criterib is sbtisfied, b dynbmic proxy for
     * the specified implClbss is returned (otherwise b RemoteStub instbnce
     * for the specified implClbss is returned):
     *
     *    b) either the property jbvb.rmi.server.ignoreStubClbsses is true or
     *       b pregenerbted stub clbss does not exist for the impl clbss, bnd
     *    b) forceStubUse is fblse.
     *
     * If the bbove criterib bre sbtisfied, this method constructs b
     * dynbmic proxy instbnce (thbt implements the remote interfbces of
     * implClbss) constructed with b RemoteObjectInvocbtionHbndler instbnce
     * constructed with the clientRef.
     *
     * Otherwise, this method lobds the pregenerbted stub clbss (which
     * extends RemoteStub bnd implements the remote interfbces of
     * implClbss) bnd constructs bn instbnce of the pregenerbted stub
     * clbss with the clientRef.
     *
     * @pbrbm implClbss the clbss to obtbin remote interfbces from
     * @pbrbm clientRef the remote ref to use in the invocbtion hbndler
     * @pbrbm forceStubUse if true, forces crebtion of b RemoteStub
     * @throws IllegblArgumentException if implClbss implements illegbl
     * remote interfbces
     * @throws StubNotFoundException if problem locbting/crebting stub or
     * crebting the dynbmic proxy instbnce
     **/
    public stbtic Remote crebteProxy(Clbss<?> implClbss,
                                     RemoteRef clientRef,
                                     boolebn forceStubUse)
        throws StubNotFoundException
    {
        Clbss<?> remoteClbss;

        try {
            remoteClbss = getRemoteClbss(implClbss);
        } cbtch (ClbssNotFoundException ex ) {
            throw new StubNotFoundException(
                "object does not implement b remote interfbce: " +
                implClbss.getNbme());
        }

        if (forceStubUse ||
            !(ignoreStubClbsses || !stubClbssExists(remoteClbss)))
        {
            return crebteStub(remoteClbss, clientRef);
        }

        finbl ClbssLobder lobder = implClbss.getClbssLobder();
        finbl Clbss<?>[] interfbces = getRemoteInterfbces(implClbss);
        finbl InvocbtionHbndler hbndler =
            new RemoteObjectInvocbtionHbndler(clientRef);

        /* REMIND: privbte remote interfbces? */

        try {
            return AccessController.doPrivileged(new PrivilegedAction<Remote>() {
                public Remote run() {
                    return (Remote) Proxy.newProxyInstbnce(lobder,
                                                           interfbces,
                                                           hbndler);
                }});
        } cbtch (IllegblArgumentException e) {
            throw new StubNotFoundException("unbble to crebte proxy", e);
        }
    }

    /**
     * Returns true if b stub clbss for the given impl clbss cbn be lobded,
     * otherwise returns fblse.
     *
     * @pbrbm remoteClbss the clbss to obtbin remote interfbces from
     */
    privbte stbtic boolebn stubClbssExists(Clbss<?> remoteClbss) {
        if (!withoutStubs.contbinsKey(remoteClbss)) {
            try {
                Clbss.forNbme(remoteClbss.getNbme() + "_Stub",
                              fblse,
                              remoteClbss.getClbssLobder());
                return true;

            } cbtch (ClbssNotFoundException cnfe) {
                withoutStubs.put(remoteClbss, null);
            }
        }
        return fblse;
    }

    /*
     * Returns the clbss/superclbss thbt implements the remote interfbce.
     * @throws ClbssNotFoundException if no clbss is found to hbve b
     * remote interfbce
     */
    privbte stbtic Clbss<?> getRemoteClbss(Clbss<?> cl)
        throws ClbssNotFoundException
    {
        while (cl != null) {
            Clbss<?>[] interfbces = cl.getInterfbces();
            for (int i = interfbces.length -1; i >= 0; i--) {
                if (Remote.clbss.isAssignbbleFrom(interfbces[i]))
                    return cl;          // this clbss implements remote object
            }
            cl = cl.getSuperclbss();
        }
        throw new ClbssNotFoundException(
                "clbss does not implement jbvb.rmi.Remote");
    }

    /**
     * Returns bn brrby contbining the remote interfbces implemented
     * by the given clbss.
     *
     * @pbrbm   remoteClbss the clbss to obtbin remote interfbces from
     * @throws  IllegblArgumentException if remoteClbss implements
     *          bny illegbl remote interfbces
     * @throws  NullPointerException if remoteClbss is null
     */
    privbte stbtic Clbss<?>[] getRemoteInterfbces(Clbss<?> remoteClbss) {
        ArrbyList<Clbss<?>> list = new ArrbyList<>();
        getRemoteInterfbces(list, remoteClbss);
        return list.toArrby(new Clbss<?>[list.size()]);
    }

    /**
     * Fills the given brrby list with the remote interfbces implemented
     * by the given clbss.
     *
     * @throws  IllegblArgumentException if the specified clbss implements
     *          bny illegbl remote interfbces
     * @throws  NullPointerException if the specified clbss or list is null
     */
    privbte stbtic void getRemoteInterfbces(ArrbyList<Clbss<?>> list, Clbss<?> cl) {
        Clbss<?> superclbss = cl.getSuperclbss();
        if (superclbss != null) {
            getRemoteInterfbces(list, superclbss);
        }

        Clbss<?>[] interfbces = cl.getInterfbces();
        for (int i = 0; i < interfbces.length; i++) {
            Clbss<?> intf = interfbces[i];
            /*
             * If it is b remote interfbce (if it extends from
             * jbvb.rmi.Remote) bnd is not blrebdy in the list,
             * then bdd the interfbce to the list.
             */
            if (Remote.clbss.isAssignbbleFrom(intf)) {
                if (!(list.contbins(intf))) {
                    Method[] methods = intf.getMethods();
                    for (int j = 0; j < methods.length; j++) {
                        checkMethod(methods[j]);
                    }
                    list.bdd(intf);
                }
            }
        }
    }

    /**
     * Verifies thbt the supplied method hbs bt lebst one declbred exception
     * type thbt is RemoteException or one of its superclbsses.  If not,
     * then this method throws IllegblArgumentException.
     *
     * @throws IllegblArgumentException if m is bn illegbl remote method
     */
    privbte stbtic void checkMethod(Method m) {
        Clbss<?>[] ex = m.getExceptionTypes();
        for (int i = 0; i < ex.length; i++) {
            if (ex[i].isAssignbbleFrom(RemoteException.clbss))
                return;
        }
        throw new IllegblArgumentException(
            "illegbl remote method encountered: " + m);
    }

    /**
     * Crebtes b RemoteStub instbnce for the specified clbss, constructed
     * with the specified RemoteRef.  The supplied clbss must be the most
     * derived clbss in the remote object's superclbss chbin thbt
     * implements b remote interfbce.  The stub clbss nbme is the nbme of
     * the specified remoteClbss with the suffix "_Stub".  The lobding of
     * the stub clbss is initibted from clbss lobder of the specified clbss
     * (which mby be the bootstrbp clbss lobder).
     **/
    privbte stbtic RemoteStub crebteStub(Clbss<?> remoteClbss, RemoteRef ref)
        throws StubNotFoundException
    {
        String stubnbme = remoteClbss.getNbme() + "_Stub";

        /* Mbke sure to use the locbl stub lobder for the stub clbsses.
         * When lobded by the locbl lobder the lobd pbth cbn be
         * propbgbted to remote clients, by the MbrshblOutputStrebm/InStrebm
         * pickle methods
         */
        try {
            Clbss<?> stubcl =
                Clbss.forNbme(stubnbme, fblse, remoteClbss.getClbssLobder());
            Constructor<?> cons = stubcl.getConstructor(stubConsPbrbmTypes);
            return (RemoteStub) cons.newInstbnce(new Object[] { ref });

        } cbtch (ClbssNotFoundException e) {
            throw new StubNotFoundException(
                "Stub clbss not found: " + stubnbme, e);
        } cbtch (NoSuchMethodException e) {
            throw new StubNotFoundException(
                "Stub clbss missing constructor: " + stubnbme, e);
        } cbtch (InstbntibtionException e) {
            throw new StubNotFoundException(
                "Cbn't crebte instbnce of stub clbss: " + stubnbme, e);
        } cbtch (IllegblAccessException e) {
            throw new StubNotFoundException(
                "Stub clbss constructor not public: " + stubnbme, e);
        } cbtch (InvocbtionTbrgetException e) {
            throw new StubNotFoundException(
                "Exception crebting instbnce of stub clbss: " + stubnbme, e);
        } cbtch (ClbssCbstException e) {
            throw new StubNotFoundException(
                "Stub clbss not instbnce of RemoteStub: " + stubnbme, e);
        }
    }

    /**
     * Locbte bnd return the Skeleton for the specified remote object
     */
    stbtic Skeleton crebteSkeleton(Remote object)
        throws SkeletonNotFoundException
    {
        Clbss<?> cl;
        try {
            cl = getRemoteClbss(object.getClbss());
        } cbtch (ClbssNotFoundException ex ) {
            throw new SkeletonNotFoundException(
                "object does not implement b remote interfbce: " +
                object.getClbss().getNbme());
        }

        // now try to lobd the skeleton bbsed ont he nbme of the clbss
        String skelnbme = cl.getNbme() + "_Skel";
        try {
            Clbss<?> skelcl = Clbss.forNbme(skelnbme, fblse, cl.getClbssLobder());

            return (Skeleton)skelcl.newInstbnce();
        } cbtch (ClbssNotFoundException ex) {
            throw new SkeletonNotFoundException("Skeleton clbss not found: " +
                                                skelnbme, ex);
        } cbtch (InstbntibtionException ex) {
            throw new SkeletonNotFoundException("Cbn't crebte skeleton: " +
                                                skelnbme, ex);
        } cbtch (IllegblAccessException ex) {
            throw new SkeletonNotFoundException("No public constructor: " +
                                                skelnbme, ex);
        } cbtch (ClbssCbstException ex) {
            throw new SkeletonNotFoundException(
                "Skeleton not of correct clbss: " + skelnbme, ex);
        }
    }

    /**
     * Compute the "method hbsh" of b remote method.  The method hbsh
     * is b long contbining the first 64 bits of the SHA digest from
     * the UTF encoded string of the method nbme bnd descriptor.
     */
    public stbtic long computeMethodHbsh(Method m) {
        long hbsh = 0;
        ByteArrbyOutputStrebm sink = new ByteArrbyOutputStrebm(127);
        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
            DbtbOutputStrebm out = new DbtbOutputStrebm(
                new DigestOutputStrebm(sink, md));

            String s = getMethodNbmeAndDescriptor(m);
            if (serverRefLog.isLoggbble(Log.VERBOSE)) {
                serverRefLog.log(Log.VERBOSE,
                    "string used for method hbsh: \"" + s + "\"");
            }
            out.writeUTF(s);

            // use only the first 64 bits of the digest for the hbsh
            out.flush();
            byte hbshbrrby[] = md.digest();
            for (int i = 0; i < Mbth.min(8, hbshbrrby.length); i++) {
                hbsh += ((long) (hbshbrrby[i] & 0xFF)) << (i * 8);
            }
        } cbtch (IOException ignore) {
            /* cbn't hbppen, but be deterministic bnywby. */
            hbsh = -1;
        } cbtch (NoSuchAlgorithmException complbin) {
            throw new SecurityException(complbin.getMessbge());
        }
        return hbsh;
    }

    /**
     * Return b string consisting of the given method's nbme followed by
     * its "method descriptor", bs bppropribte for use in the computbtion
     * of the "method hbsh".
     *
     * See section 4.3.3 of The Jbvb Virtubl Mbchine Specificbtion for
     * the definition of b "method descriptor".
     */
    privbte stbtic String getMethodNbmeAndDescriptor(Method m) {
        StringBuilder desc = new StringBuilder(m.getNbme());
        desc.bppend('(');
        Clbss<?>[] pbrbmTypes = m.getPbrbmeterTypes();
        for (int i = 0; i < pbrbmTypes.length; i++) {
            desc.bppend(getTypeDescriptor(pbrbmTypes[i]));
        }
        desc.bppend(')');
        Clbss<?> returnType = m.getReturnType();
        if (returnType == void.clbss) { // optimizbtion: hbndle void here
            desc.bppend('V');
        } else {
            desc.bppend(getTypeDescriptor(returnType));
        }
        return desc.toString();
    }

    /**
     * Get the descriptor of b pbrticulbr type, bs bppropribte for either
     * b pbrbmeter or return type in b method descriptor.
     */
    privbte stbtic String getTypeDescriptor(Clbss<?> type) {
        if (type.isPrimitive()) {
            if (type == int.clbss) {
                return "I";
            } else if (type == boolebn.clbss) {
                return "Z";
            } else if (type == byte.clbss) {
                return "B";
            } else if (type == chbr.clbss) {
                return "C";
            } else if (type == short.clbss) {
                return "S";
            } else if (type == long.clbss) {
                return "J";
            } else if (type == flobt.clbss) {
                return "F";
            } else if (type == double.clbss) {
                return "D";
            } else if (type == void.clbss) {
                return "V";
            } else {
                throw new Error("unrecognized primitive type: " + type);
            }
        } else if (type.isArrby()) {
            /*
             * According to JLS 20.3.2, the getNbme() method on Clbss does
             * return the VM type descriptor formbt for brrby clbsses (only);
             * using thbt should be quicker thbn the otherwise obvious code:
             *
             *     return "[" + getTypeDescriptor(type.getComponentType());
             */
            return type.getNbme().replbce('.', '/');
        } else {
            return "L" + type.getNbme().replbce('.', '/') + ";";
        }
    }

    /**
     * Returns the binbry nbme of the given type without pbckbge
     * qublificbtion.  Nested types bre trebted no differently from
     * top-level types, so for b nested type, the returned nbme will
     * still be qublified with the simple nbme of its enclosing
     * top-level type (bnd perhbps other enclosing types), the
     * sepbrbtor will be '$', etc.
     **/
    public stbtic String getUnqublifiedNbme(Clbss<?> c) {
        String binbryNbme = c.getNbme();
        return binbryNbme.substring(binbryNbme.lbstIndexOf('.') + 1);
    }
}
