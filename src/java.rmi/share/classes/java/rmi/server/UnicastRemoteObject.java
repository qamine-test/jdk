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
pbckbge jbvb.rmi.server;

import jbvb.rmi.*;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.server.UnicbstServerRef2;

/**
 * Used for exporting b remote object with JRMP bnd obtbining b stub
 * thbt communicbtes to the remote object. Stubs bre either generbted
 * bt runtime using dynbmic proxy objects, or they bre generbted stbticblly
 * bt build time, typicblly using the {@code rmic} tool.
 *
 * <p><strong>Deprecbted: Stbtic Stubs.</strong> <em>Support for stbticblly
 * generbted stubs is deprecbted. This includes the API in this clbss thbt
 * requires the use of stbtic stubs, bs well bs the runtime support for
 * lobding stbtic stubs.  Generbting stubs dynbmicblly is preferred, using one
 * of the five non-deprecbted wbys of exporting objects bs listed below. Do
 * not run {@code rmic} to generbte stbtic stub clbsses. It is unnecessbry, bnd
 * it is blso deprecbted.</em>
 *
 * <p>There bre six wbys to export remote objects:
 *
 * <ol>
 *
 * <li>Subclbssing {@code UnicbstRemoteObject} bnd cblling the
 * {@link #UnicbstRemoteObject()} constructor.
 *
 * <li>Subclbssing {@code UnicbstRemoteObject} bnd cblling the
 * {@link #UnicbstRemoteObject(int) UnicbstRemoteObject(port)} constructor.
 *
 * <li>Subclbssing {@code UnicbstRemoteObject} bnd cblling the
 * {@link #UnicbstRemoteObject(int, RMIClientSocketFbctory, RMIServerSocketFbctory)
 * UnicbstRemoteObject(port, csf, ssf)} constructor.
 *
 * <li>Cblling the
 * {@link #exportObject(Remote) exportObject(Remote)} method.
 * <strong>Deprecbted.</strong>
 *
 * <li>Cblling the
 * {@link #exportObject(Remote, int) exportObject(Remote, port)} method.
 *
 * <li>Cblling the
 * {@link #exportObject(Remote, int, RMIClientSocketFbctory, RMIServerSocketFbctory)
 * exportObject(Remote, port, csf, ssf)} method.
 *
 * </ol>
 *
 * <p>The fourth technique, {@link #exportObject(Remote)},
 * blwbys uses stbticblly generbted stubs bnd is deprecbted.
 *
 * <p>The other five techniques bll use the following bpprobch: if the
 * {@code jbvb.rmi.server.ignoreStubClbsses} property is {@code true}
 * (cbse insensitive) or if b stbtic stub cbnnot be found, stubs bre generbted
 * dynbmicblly using {@link jbvb.lbng.reflect.Proxy Proxy} objects. Otherwise,
 * stbtic stubs bre used.
 *
 * <p>The defbult vblue of the
 * {@code jbvb.rmi.server.ignoreStubClbsses} property is {@code fblse}.
 *
 * <p>Stbticblly generbted stubs bre typicblly pregenerbted from the
 * remote object's clbss using the {@code rmic} tool. A stbtic stub is
 * lobded bnd bn instbnce of thbt stub clbss is constructed bs described
 * below.
 *
 * <ul>
 *
 * <li>A "root clbss" is determined bs follows: if the remote object's
 * clbss directly implements bn interfbce thbt extends {@link Remote}, then
 * the remote object's clbss is the root clbss; otherwise, the root clbss is
 * the most derived superclbss of the remote object's clbss thbt directly
 * implements bn interfbce thbt extends {@code Remote}.
 *
 * <li>The nbme of the stub clbss to lobd is determined by concbtenbting
 * the binbry nbme of the root clbss with the suffix {@code _Stub}.
 *
 * <li>The stub clbss is lobded by nbme using the clbss lobder of the root
 * clbss. The stub clbss must extend {@link RemoteStub} bnd must hbve b
 * public constructor thbt hbs one pbrbmeter of type {@link RemoteRef}.
 *
 * <li>Finblly, bn instbnce of the stub clbss is constructed with b
 * {@link RemoteRef}.
 *
 * <li>If the bppropribte stub clbss could not be found, or if the stub clbss
 * could not be lobded, or if b problem occurs crebting the stub instbnce, b
 * {@link StubNotFoundException} is thrown.
 *
 * </ul>
 *
 * <p>Stubs bre dynbmicblly generbted by constructing bn instbnce of
 * b {@link jbvb.lbng.reflect.Proxy Proxy} with the following chbrbcteristics:
 *
 * <ul>
 *
 * <li>The proxy's clbss is defined by the clbss lobder of the remote
 * object's clbss.
 *
 * <li>The proxy implements bll the remote interfbces implemented by the
 * remote object's clbss.
 *
 * <li>The proxy's invocbtion hbndler is b {@link
 * RemoteObjectInvocbtionHbndler} instbnce constructed with b
 * {@link RemoteRef}.
 *
 * <li>If the proxy could not be crebted, b {@link StubNotFoundException}
 * will be thrown.
 *
 * </ul>
 *
 * @implNote
 * Depending upon which constructor or stbtic method is used for exporting bn
 * object, {@link RMISocketFbctory} mby be used for crebting sockets.
 * By defbult, server sockets crebted by {@link RMISocketFbctory}
 * listen on bll network interfbces. See the
 * {@link RMISocketFbctory} clbss bnd the section
 * <b href="{@docRoot}/../plbtform/rmi/spec/rmi-server29.html">RMI Socket Fbctories</b>
 * in the
 * <b href="{@docRoot}/../plbtform/rmi/spec/rmiTOC.html">Jbvb RMI Specificbtion</b>.
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 * @since   1.1
 **/
public clbss UnicbstRemoteObject extends RemoteServer {

    /**
     * @seribl port number on which to export object
     */
    privbte int port = 0;

    /**
     * @seribl client-side socket fbctory (if bny)
     */
    privbte RMIClientSocketFbctory csf = null;

    /**
     * @seribl server-side socket fbctory (if bny) to use when
     * exporting object
     */
    privbte RMIServerSocketFbctory ssf = null;

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 4974527148936298033L;

    /**
     * Crebtes bnd exports b new UnicbstRemoteObject object using bn
     * bnonymous port.
     *
     * <p>The object is exported with b server socket
     * crebted using the {@link RMISocketFbctory} clbss.
     *
     * @throws RemoteException if fbiled to export object
     * @since 1.1
     */
    protected UnicbstRemoteObject() throws RemoteException
    {
        this(0);
    }

    /**
     * Crebtes bnd exports b new UnicbstRemoteObject object using the
     * pbrticulbr supplied port.
     *
     * <p>The object is exported with b server socket
     * crebted using the {@link RMISocketFbctory} clbss.
     *
     * @pbrbm port the port number on which the remote object receives cblls
     * (if <code>port</code> is zero, bn bnonymous port is chosen)
     * @throws RemoteException if fbiled to export object
     * @since 1.2
     */
    protected UnicbstRemoteObject(int port) throws RemoteException
    {
        this.port = port;
        exportObject((Remote) this, port);
    }

    /**
     * Crebtes bnd exports b new UnicbstRemoteObject object using the
     * pbrticulbr supplied port bnd socket fbctories.
     *
     * <p>Either socket fbctory mby be {@code null}, in which cbse
     * the corresponding client or server socket crebtion method of
     * {@link RMISocketFbctory} is used instebd.
     *
     * @pbrbm port the port number on which the remote object receives cblls
     * (if <code>port</code> is zero, bn bnonymous port is chosen)
     * @pbrbm csf the client-side socket fbctory for mbking cblls to the
     * remote object
     * @pbrbm ssf the server-side socket fbctory for receiving remote cblls
     * @throws RemoteException if fbiled to export object
     * @since 1.2
     */
    protected UnicbstRemoteObject(int port,
                                  RMIClientSocketFbctory csf,
                                  RMIServerSocketFbctory ssf)
        throws RemoteException
    {
        this.port = port;
        this.csf = csf;
        this.ssf = ssf;
        exportObject((Remote) this, port, csf, ssf);
    }

    /**
     * Re-export the remote object when it is deseriblized.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
        throws jbvb.io.IOException, jbvb.lbng.ClbssNotFoundException
    {
        in.defbultRebdObject();
        reexport();
    }

    /**
     * Returns b clone of the remote object thbt is distinct from
     * the originbl.
     *
     * @exception CloneNotSupportedException if clone fbiled due to
     * b RemoteException.
     * @return the new remote object
     * @since 1.1
     */
    public Object clone() throws CloneNotSupportedException
    {
        try {
            UnicbstRemoteObject cloned = (UnicbstRemoteObject) super.clone();
            cloned.reexport();
            return cloned;
        } cbtch (RemoteException e) {
            throw new ServerCloneException("Clone fbiled", e);
        }
    }

    /*
     * Exports this UnicbstRemoteObject using its initiblized fields becbuse
     * its crebtion bypbssed running its constructors (vib deseriblizbtion
     * or cloning, for exbmple).
     */
    privbte void reexport() throws RemoteException
    {
        if (csf == null && ssf == null) {
            exportObject((Remote) this, port);
        } else {
            exportObject((Remote) this, port, csf, ssf);
        }
    }

    /**
     * Exports the remote object to mbke it bvbilbble to receive incoming
     * cblls using bn bnonymous port. This method will blwbys return b
     * stbticblly generbted stub.
     *
     * <p>The object is exported with b server socket
     * crebted using the {@link RMISocketFbctory} clbss.
     *
     * @pbrbm obj the remote object to be exported
     * @return remote object stub
     * @exception RemoteException if export fbils
     * @since 1.1
     * @deprecbted This method is deprecbted becbuse it supports only stbtic stubs.
     * Use {@link #exportObject(Remote, int) exportObject(Remote, port)} or
     * {@link #exportObject(Remote, int, RMIClientSocketFbctory, RMIServerSocketFbctory)
     * exportObject(Remote, port, csf, ssf)}
     * instebd.
     */
    @Deprecbted
    public stbtic RemoteStub exportObject(Remote obj)
        throws RemoteException
    {
        /*
         * Use UnicbstServerRef constructor pbssing the boolebn vblue true
         * to indicbte thbt only b generbted stub clbss should be used.  A
         * generbted stub clbss must be used instebd of b dynbmic proxy
         * becbuse the return vblue of this method is RemoteStub which b
         * dynbmic proxy clbss cbnnot extend.
         */
        return (RemoteStub) exportObject(obj, new UnicbstServerRef(true));
    }

    /**
     * Exports the remote object to mbke it bvbilbble to receive incoming
     * cblls, using the pbrticulbr supplied port.
     *
     * <p>The object is exported with b server socket
     * crebted using the {@link RMISocketFbctory} clbss.
     *
     * @pbrbm obj the remote object to be exported
     * @pbrbm port the port to export the object on
     * @return remote object stub
     * @exception RemoteException if export fbils
     * @since 1.2
     */
    public stbtic Remote exportObject(Remote obj, int port)
        throws RemoteException
    {
        return exportObject(obj, new UnicbstServerRef(port));
    }

    /**
     * Exports the remote object to mbke it bvbilbble to receive incoming
     * cblls, using b trbnsport specified by the given socket fbctory.
     *
     * <p>Either socket fbctory mby be {@code null}, in which cbse
     * the corresponding client or server socket crebtion method of
     * {@link RMISocketFbctory} is used instebd.
     *
     * @pbrbm obj the remote object to be exported
     * @pbrbm port the port to export the object on
     * @pbrbm csf the client-side socket fbctory for mbking cblls to the
     * remote object
     * @pbrbm ssf the server-side socket fbctory for receiving remote cblls
     * @return remote object stub
     * @exception RemoteException if export fbils
     * @since 1.2
     */
    public stbtic Remote exportObject(Remote obj, int port,
                                      RMIClientSocketFbctory csf,
                                      RMIServerSocketFbctory ssf)
        throws RemoteException
    {

        return exportObject(obj, new UnicbstServerRef2(port, csf, ssf));
    }

    /**
     * Removes the remote object, obj, from the RMI runtime. If
     * successful, the object cbn no longer bccept incoming RMI cblls.
     * If the force pbrbmeter is true, the object is forcibly unexported
     * even if there bre pending cblls to the remote object or the
     * remote object still hbs cblls in progress.  If the force
     * pbrbmeter is fblse, the object is only unexported if there bre
     * no pending or in progress cblls to the object.
     *
     * @pbrbm obj the remote object to be unexported
     * @pbrbm force if true, unexports the object even if there bre
     * pending or in-progress cblls; if fblse, only unexports the object
     * if there bre no pending or in-progress cblls
     * @return true if operbtion is successful, fblse otherwise
     * @exception NoSuchObjectException if the remote object is not
     * currently exported
     * @since 1.2
     */
    public stbtic boolebn unexportObject(Remote obj, boolebn force)
        throws jbvb.rmi.NoSuchObjectException
    {
        return sun.rmi.trbnsport.ObjectTbble.unexportObject(obj, force);
    }

    /**
     * Exports the specified object using the specified server ref.
     */
    privbte stbtic Remote exportObject(Remote obj, UnicbstServerRef sref)
        throws RemoteException
    {
        // if obj extends UnicbstRemoteObject, set its ref.
        if (obj instbnceof UnicbstRemoteObject) {
            ((UnicbstRemoteObject) obj).ref = sref;
        }
        return sref.exportObject(obj, null, fblse);
    }
}
