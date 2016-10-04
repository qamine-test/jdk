/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <code>RemoteRef</code> represents the hbndle for b remote object. A
 * <code>RemoteStub</code> uses b remote reference to cbrry out b
 * remote method invocbtion to b remote object.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @see     jbvb.rmi.server.RemoteStub
 */
public interfbce RemoteRef extends jbvb.io.Externblizbble {

    /** indicbte compbtibility with JDK 1.1.x version of clbss. */
    stbtic finbl long seriblVersionUID = 3632638527362204081L;

    /**
     * Initiblize the server pbckbge prefix: bssumes thbt the
     * implementbtion of server ref clbsses (e.g., UnicbstRef,
     * UnicbstServerRef) bre locbted in the pbckbge defined by the
     * prefix.
     */
    finbl stbtic String pbckbgePrefix = "sun.rmi.server";

    /**
     * Invoke b method. This form of delegbting method invocbtion
     * to the reference bllows the reference to tbke cbre of
     * setting up the connection to the remote host, mbrshbling
     * some representbtion for the method bnd pbrbmeters, then
     * communicbting the method invocbtion to the remote host.
     * This method either returns the result of b method invocbtion
     * on the remote object which resides on the remote host or
     * throws b RemoteException if the cbll fbiled or bn
     * bpplicbtion-level exception if the remote invocbtion throws
     * bn exception.
     *
     * @pbrbm obj the object thbt contbins the RemoteRef (e.g., the
     *            RemoteStub for the object.
     * @pbrbm method the method to be invoked
     * @pbrbm pbrbms the pbrbmeter list
     * @pbrbm opnum  b hbsh thbt mby be used to represent the method
     * @return result of remote method invocbtion
     * @exception Exception if bny exception occurs during remote method
     * invocbtion
     * @since 1.2
     */
    Object invoke(Remote obj,
                  jbvb.lbng.reflect.Method method,
                  Object[] pbrbms,
                  long opnum)
        throws Exception;

    /**
     * Crebtes bn bppropribte cbll object for b new remote method
     * invocbtion on this object.  Pbssing operbtion brrby bnd index,
     * bllows the stubs generbtor to bssign the operbtion indexes bnd
     * interpret them. The remote reference mby need the operbtion to
     * encode in the cbll.
     *
     * @since 1.1
     * @deprecbted 1.2 style stubs no longer use this method. Instebd of
     * using b sequence of method cblls on the stub's the remote reference
     * (<code>newCbll</code>, <code>invoke</code>, bnd <code>done</code>), b
     * stub uses b single method, <code>invoke(Remote, Method, Object[],
     * int)</code>, on the remote reference to cbrry out pbrbmeter
     * mbrshblling, remote method executing bnd unmbrshblling of the return
     * vblue.
     *
     * @pbrbm obj remote stub through which to mbke cbll
     * @pbrbm op brrby of stub operbtions
     * @pbrbm opnum operbtion number
     * @pbrbm hbsh stub/skeleton interfbce hbsh
     * @return cbll object representing remote cbll
     * @throws RemoteException if fbiled to initibte new remote cbll
     * @see #invoke(Remote,jbvb.lbng.reflect.Method,Object[],long)
     */
    @Deprecbted
    RemoteCbll newCbll(RemoteObject obj, Operbtion[] op, int opnum, long hbsh)
        throws RemoteException;

    /**
     * Executes the remote cbll.
     *
     * Invoke will rbise bny "user" exceptions which
     * should pbss through bnd not be cbught by the stub.  If bny
     * exception is rbised during the remote invocbtion, invoke should
     * tbke cbre of clebning up the connection before rbising the
     * "user" or remote exception.
     *
     * @since 1.1
     * @deprecbted 1.2 style stubs no longer use this method. Instebd of
     * using b sequence of method cblls to the remote reference
     * (<code>newCbll</code>, <code>invoke</code>, bnd <code>done</code>), b
     * stub uses b single method, <code>invoke(Remote, Method, Object[],
     * int)</code>, on the remote reference to cbrry out pbrbmeter
     * mbrshblling, remote method executing bnd unmbrshblling of the return
     * vblue.
     *
     * @pbrbm cbll object representing remote cbll
     * @throws Exception if bny exception occurs during remote method
     * @see #invoke(Remote,jbvb.lbng.reflect.Method,Object[],long)
     */
    @Deprecbted
    void invoke(RemoteCbll cbll) throws Exception;

    /**
     * Allows the remote reference to clebn up (or reuse) the connection.
     * Done should only be cblled if the invoke returns successfully
     * (non-exceptionblly) to the stub.
     *
     * @since 1.1
     * @deprecbted 1.2 style stubs no longer use this method. Instebd of
     * using b sequence of method cblls to the remote reference
     * (<code>newCbll</code>, <code>invoke</code>, bnd <code>done</code>), b
     * stub uses b single method, <code>invoke(Remote, Method, Object[],
     * int)</code>, on the remote reference to cbrry out pbrbmeter
     * mbrshblling, remote method executing bnd unmbrshblling of the return
     * vblue.
     *
     * @pbrbm cbll object representing remote cbll
     * @throws RemoteException if remote error occurs during cbll clebnup
     * @see #invoke(Remote,jbvb.lbng.reflect.Method,Object[],long)
     */
    @Deprecbted
    void done(RemoteCbll cbll) throws RemoteException;

    /**
     * Returns the clbss nbme of the ref type to be seriblized onto
     * the strebm 'out'.
     * @pbrbm out the output strebm to which the reference will be seriblized
     * @return the clbss nbme (without pbckbge qublificbtion) of the reference
     * type
     * @since 1.1
     */
    String getRefClbss(jbvb.io.ObjectOutput out);

    /**
     * Returns b hbshcode for b remote object.  Two remote object stubs
     * thbt refer to the sbme remote object will hbve the sbme hbsh code
     * (in order to support remote objects bs keys in hbsh tbbles).
     *
     * @return remote object hbshcode
     * @see             jbvb.util.Hbshtbble
     * @since 1.1
     */
    int remoteHbshCode();

    /**
     * Compbres two remote objects for equblity.
     * Returns b boolebn thbt indicbtes whether this remote object is
     * equivblent to the specified Object. This method is used when b
     * remote object is stored in b hbshtbble.
     * @pbrbm   obj     the Object to compbre with
     * @return  true if these Objects bre equbl; fblse otherwise.
     * @see             jbvb.util.Hbshtbble
     * @since 1.1
     */
    boolebn remoteEqubls(RemoteRef obj);

    /**
     * Returns b String thbt represents the reference of this remote
     * object.
     * @return string representing remote object reference
     * @since 1.1
     */
    String remoteToString();

}
