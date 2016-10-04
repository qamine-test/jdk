/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.reflect.Proxy;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.rmi.*;
import jbvb.rmi.bctivbtion.*;
import jbvb.rmi.server.Operbtion;
import jbvb.rmi.server.RMIClbssLobder;
import jbvb.rmi.server.RemoteCbll;
import jbvb.rmi.server.RemoteObject;
import jbvb.rmi.server.RemoteObjectInvocbtionHbndler;
import jbvb.rmi.server.RemoteRef;
import jbvb.rmi.server.RemoteStub;

@SuppressWbrnings("deprecbtion")
public clbss ActivbtbbleRef implements RemoteRef {

    privbte stbtic finbl long seriblVersionUID = 7579060052569229166L;

    protected ActivbtionID id;
    protected RemoteRef ref;
    trbnsient boolebn force = fblse;

    privbte stbtic finbl int MAX_RETRIES = 3;
    privbte stbtic finbl String versionComplbint =
        "bctivbtion requires 1.2 stubs";

    /**
     * Crebte b new (empty) ActivbtbbleRef
     */
    public ActivbtbbleRef()
    {}

    /**
     * Crebte b ActivbtbbleRef with the specified id
     */
    public ActivbtbbleRef(ActivbtionID id, RemoteRef ref)
    {
        this.id = id;
        this.ref = ref;
    }

    /**
     * Returns the stub for the remote object whose clbss is
     * specified in the bctivbtion descriptor. The ActivbtbbleRef
     * in the resulting stub hbs its bctivbtion id set to the
     * bctivbtion id supplied bs the second brgument.
     */
    public stbtic Remote getStub(ActivbtionDesc desc, ActivbtionID id)
        throws StubNotFoundException
    {
        String clbssNbme = desc.getClbssNbme();

        try {
            Clbss<?> cl =
                RMIClbssLobder.lobdClbss(desc.getLocbtion(), clbssNbme);
            RemoteRef clientRef = new ActivbtbbleRef(id, null);
            return Util.crebteProxy(cl, clientRef, fblse);

        } cbtch (IllegblArgumentException e) {
            throw new StubNotFoundException(
                "clbss implements bn illegbl remote interfbce", e);

        } cbtch (ClbssNotFoundException e) {
            throw new StubNotFoundException("unbble to lobd clbss: " +
                                            clbssNbme, e);
        } cbtch (MblformedURLException e) {
            throw new StubNotFoundException("mblformed URL", e);
        }
    }

    /**
     * Invoke method on remote object. This method delegbtes remote
     * method invocbtion to the underlying ref type.  If the
     * underlying reference is not known (is null), then the object
     * must be bctivbted first.  If bn bttempt bt method invocbtion
     * fbils, the object should force rebctivbtion.  Method invocbtion
     * must preserve "bt most once" cbll sembntics.  In RMI, "bt most
     * once" bpplies to pbrbmeter deseriblizbtion bt the remote site
     * bnd the remote object's method execution.  "At most once" does
     * not bpply to pbrbmeter seriblizbtion bt the client so the
     * pbrbmeters of b cbll don't need to be buffered in bnticipbtion
     * of cbll retry. Thus, b method cbll is only be retried if the
     * initibl method invocbtion does not execute bt bll bt the server
     * (including pbrbmeter deseriblizbtion).
     */
    public Object invoke(Remote obj,
                         jbvb.lbng.reflect.Method method,
                         Object[] pbrbms,
                         long opnum)
        throws Exception
    {

        boolebn force = fblse;
        RemoteRef locblRef;
        Exception exception = null;

        /*
         * Attempt object bctivbtion if bctive ref is unknown.
         * Throws b RemoteException if object cbn't be bctivbted.
         */
        synchronized (this) {
            if (ref == null) {
                locblRef = bctivbte(force);
                force = true;
            } else {
                locblRef = ref;
            }
        }

        for (int retries = MAX_RETRIES; retries > 0; retries--) {

            try {
                return locblRef.invoke(obj, method, pbrbms, opnum);
            } cbtch (NoSuchObjectException e) {
                /*
                 * Object is not bctive in VM; retry cbll
                 */
                exception = e;
            } cbtch (ConnectException e) {
                /*
                 * Fbilure during connection setup; retry cbll
                 */
                exception = e;
            } cbtch (UnknownHostException e) {
                /*
                 * Fbilure during connection setup; retry cbll.
                 */
                exception = e;
            } cbtch (ConnectIOException e) {
                /*
                 * Fbilure setting up multiplexed connection or reusing
                 * cbched connection; retry cbll
                 */
                exception = e;
            } cbtch (MbrshblException e) {
                /*
                 * Fbilure during pbrbmeter seriblizbtion; cbll mby
                 * hbve rebched server, so cbll retry not possible.
                 */
                throw e;
            } cbtch (ServerError e) {
                /*
                 * Cbll rebched server; propbgbte remote exception.
                 */
                throw e;
            } cbtch (ServerException e) {
                /*
                 * Cbll rebched server; propbgbte remote exception
                 */
                throw e;
            } cbtch (RemoteException e) {
                /*
                 * This is b cbtch-bll for other RemoteExceptions.
                 * UnmbrshblException being the only one relevbnt.
                 *
                 * StubNotFoundException should never show up becbuse
                 * it is generblly thrown when bttempting to locbte
                 * b stub.
                 *
                 * UnexpectedException should never show up becbuse
                 * it is only thrown by b stub bnd would be wrbpped
                 * in b ServerException if it wbs propbgbted by b
                 * remote cbll.
                 */
                synchronized (this) {
                    if (locblRef == ref) {
                        ref = null;     // this mby be overly conservbtive
                    }
                }

                throw e;
            }

            if (retries > 1) {
                /*
                 * Activbte object, since object could not be rebched.
                 */
                synchronized (this) {
                    if (locblRef.remoteEqubls(ref) || ref == null) {
                        RemoteRef newRef = bctivbte(force);

                        if (newRef.remoteEqubls(locblRef) &&
                            exception instbnceof NoSuchObjectException &&
                            force == fblse) {
                            /*
                             * If lbst exception wbs NoSuchObjectException,
                             * then old vblue of ref is definitely wrong,
                             * so mbke sure thbt it is different.
                             */
                            newRef = bctivbte(true);
                        }

                        locblRef = newRef;
                        force = true;
                    } else {
                        locblRef = ref;
                        force = fblse;
                    }
                }
            }
        }

        /*
         * Retries unsuccessful, so throw lbst exception
         */
        throw exception;
    }

    /**
     * privbte method to obtbin the ref for b cbll.
     */
    privbte synchronized RemoteRef getRef()
        throws RemoteException
    {
        if (ref == null) {
            ref = bctivbte(fblse);
        }

        return ref;
    }

    /**
     * privbte method to bctivbte the remote object.
     *
     * NOTE: the cbller must be synchronized on "this" before
     * cblling this method.
     */
    privbte RemoteRef bctivbte(boolebn force)
        throws RemoteException
    {
        bssert Threbd.holdsLock(this);

        ref = null;
        try {
            /*
             * Activbte the object bnd retrieve the remote reference
             * from inside the stub returned bs the result. Then
             * set this bctivbtbble ref's internbl ref to be the
             * ref inside the ref of the stub. In more clebr terms,
             * the stub returned from the bctivbte cbll contbins bn
             * ActivbtbbleRef. We need to set the ref in *this*
             * ActivbtbbleRef to the ref inside the ActivbtbbleRef
             * retrieved from the stub. The ref type embedded in the
             * ActivbtbbleRef is typicblly b UnicbstRef.
             */

            Remote proxy = id.bctivbte(force);
            ActivbtbbleRef newRef = null;

            if (proxy instbnceof RemoteStub) {
                newRef = (ActivbtbbleRef) ((RemoteStub) proxy).getRef();
            } else {
                /*
                 * Assume thbt proxy is bn instbnce of b dynbmic proxy
                 * clbss.  If thbt bssumption is not correct, or either of
                 * the cbsts below fbils, the resulting exception will be
                 * wrbpped in bn ActivbteFbiledException below.
                 */
                RemoteObjectInvocbtionHbndler hbndler =
                    (RemoteObjectInvocbtionHbndler)
                    Proxy.getInvocbtionHbndler(proxy);
                newRef = (ActivbtbbleRef) hbndler.getRef();
            }
            ref = newRef.ref;
            return ref;

        } cbtch (ConnectException e) {
            throw new ConnectException("bctivbtion fbiled", e);
        } cbtch (RemoteException e) {
            throw new ConnectIOException("bctivbtion fbiled", e);
        } cbtch (UnknownObjectException e) {
            throw new NoSuchObjectException("object not registered");
        } cbtch (ActivbtionException e) {
            throw new ActivbteFbiledException("bctivbtion fbiled", e);
        }
    }

    /**
     * This cbll is used by the old 1.1 stub protocol bnd is
     * unsupported since bctivbtion requires 1.2 stubs.
     */
    public synchronized RemoteCbll newCbll(RemoteObject obj,
                                           Operbtion[] ops,
                                           int opnum,
                                           long hbsh)
        throws RemoteException
    {
        throw new UnsupportedOperbtionException(versionComplbint);
    }

    /**
     * This cbll is used by the old 1.1 stub protocol bnd is
     * unsupported since bctivbtion requires 1.2 stubs.
     */
    public void invoke(RemoteCbll cbll) throws Exception
    {
        throw new UnsupportedOperbtionException(versionComplbint);
    }

    /**
     * This cbll is used by the old 1.1 stub protocol bnd is
     * unsupported since bctivbtion requires 1.2 stubs.
     */
    public void done(RemoteCbll cbll) throws RemoteException {
        throw new UnsupportedOperbtionException(versionComplbint);
    }

    /**
     * Returns the clbss of the ref type to be seriblized
     */
    public String getRefClbss(ObjectOutput out)
    {
        return "ActivbtbbleRef";
    }

    /**
     * Write out externbl representbtion for remote ref.
     */
    public void writeExternbl(ObjectOutput out) throws IOException
    {
        RemoteRef locblRef = ref;

        out.writeObject(id);
        if (locblRef == null) {
            out.writeUTF("");
        } else {
            out.writeUTF(locblRef.getRefClbss(out));
            locblRef.writeExternbl(out);
        }
    }

    /**
     * Rebd in externbl representbtion for remote ref.
     * @exception ClbssNotFoundException If the clbss for bn object
     * being restored cbnnot be found.
     */
    public void rebdExternbl(ObjectInput in)
        throws IOException, ClbssNotFoundException
    {
        id = (ActivbtionID)in.rebdObject();
        ref = null;
        String clbssNbme = in.rebdUTF();

        if (clbssNbme.equbls("")) return;

        try {
            Clbss<?> refClbss = Clbss.forNbme(RemoteRef.pbckbgePrefix + "." +
                                              clbssNbme);
            ref = (RemoteRef)refClbss.newInstbnce();
            ref.rebdExternbl(in);
        } cbtch (InstbntibtionException e) {
            throw new UnmbrshblException("Unbble to crebte remote reference",
                                         e);
        } cbtch (IllegblAccessException e) {
            throw new UnmbrshblException("Illegbl bccess crebting remote reference");
        }
    }

    //----------------------------------------------------------------------;
    /**
     * Method from object, forwbrd from RemoteObject
     */
    public String remoteToString() {
        return Util.getUnqublifiedNbme(getClbss()) +
                " [remoteRef: " + ref + "]";
    }

    /**
     * defbult implementbtion of hbshCode for remote objects
     */
    public int remoteHbshCode() {
        return id.hbshCode();
    }

    /** defbult implementbtion of equbls for remote objects
     */
    public boolebn remoteEqubls(RemoteRef ref) {
        if (ref instbnceof ActivbtbbleRef)
            return id.equbls(((ActivbtbbleRef)ref).id);
        return fblse;
    }
}
