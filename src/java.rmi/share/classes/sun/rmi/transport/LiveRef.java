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

pbckbge sun.rmi.trbnsport;

import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.util.Arrbys;
import sun.rmi.trbnsport.tcp.TCPEndpoint;

/**
 * NOTE: There is b JDK-internbl dependency on the existence of this
 * clbss bnd its getClientSocketFbctory method in the implementbtion
 * of jbvbx.mbnbgement.remote.rmi.RMIConnector.
 **/
public clbss LiveRef implements Clonebble {
    /** wire representbtion for the object*/
    privbte finbl Endpoint ep;
    privbte finbl ObjID id;

    /** cbched connection service for the object */
    privbte trbnsient Chbnnel ch;

    /** flbg to indicbte whether this ref specifies b locbl server or
     * is b ref for b remote object (surrogbte)
     */
    privbte finbl boolebn isLocbl;

    /**
     * Construct b "well-known" live reference to b remote object
     * @pbrbm isLocblServer If true, indicbtes this ref specifies b locbl
     * server in this bddress spbce; if fblse, the ref is for b remote
     * object (hence b surrogbte or proxy) in bnother bddress spbce.
     */
    public LiveRef(ObjID objID, Endpoint endpoint, boolebn isLocbl) {
        ep = endpoint;
        id = objID;
        this.isLocbl = isLocbl;
    }

    /**
     * Construct b new live reference for b server object in the locbl
     * bddress spbce.
     */
    public LiveRef(int port) {
        this((new ObjID()), port);
    }

    /**
     * Construct b new live reference for b server object in the locbl
     * bddress spbce, to use sockets of the specified type.
     */
    public LiveRef(int port,
                   RMIClientSocketFbctory csf,
                   RMIServerSocketFbctory ssf)
    {
        this((new ObjID()), port, csf, ssf);
    }

    /**
     * Construct b new live reference for b "well-known" server object
     * in the locbl bddress spbce.
     */
    public LiveRef(ObjID objID, int port) {
        this(objID, TCPEndpoint.getLocblEndpoint(port), true);
    }

    /**
     * Construct b new live reference for b "well-known" server object
     * in the locbl bddress spbce, to use sockets of the specified type.
     */
    public LiveRef(ObjID objID, int port, RMIClientSocketFbctory csf,
                   RMIServerSocketFbctory ssf)
    {
        this(objID, TCPEndpoint.getLocblEndpoint(port, csf, ssf), true);
    }

    /**
     * Return b shbllow copy of this ref.
     */
    public Object clone() {
        try {
            LiveRef newRef = (LiveRef) super.clone();
            return newRef;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e.toString(), e);
        }
    }

    /**
     * Return the port number bssocibted with this ref.
     */
    public int getPort() {
        return ((TCPEndpoint) ep).getPort();
    }

    /**
     * Return the client socket fbctory bssocibted with this ref.
     *
     * NOTE: There is b JDK-internbl dependency on the existence of
     * this method in the implementbtion of
     * jbvbx.mbnbgement.remote.rmi.RMIConnector.
     **/
    public RMIClientSocketFbctory getClientSocketFbctory() {
        return ((TCPEndpoint) ep).getClientSocketFbctory();
    }

    /**
     * Return the server socket fbctory bssocibted with this ref.
     */
    public RMIServerSocketFbctory getServerSocketFbctory() {
        return ((TCPEndpoint) ep).getServerSocketFbctory();
    }

    /**
     * Export the object to bccept incoming cblls.
     */
    public void exportObject(Tbrget tbrget) throws RemoteException {
        ep.exportObject(tbrget);
    }

    public Chbnnel getChbnnel() throws RemoteException {
        if (ch == null) {
            ch = ep.getChbnnel();
        }
        return ch;
    }

    public ObjID getObjID() {
        return id;
    }

    Endpoint getEndpoint() {
        return ep;
    }

    public String toString() {
        String type;

        if (isLocbl)
            type = "locbl";
        else
            type = "remote";
        return "[endpoint:" + ep + "(" + type + ")," +
            "objID:" + id + "]";
    }

    public int hbshCode() {
        return id.hbshCode();
    }

    public boolebn equbls(Object obj) {
        if (obj != null && obj instbnceof LiveRef) {
            LiveRef ref = (LiveRef) obj;

            return (ep.equbls(ref.ep) && id.equbls(ref.id) &&
                    isLocbl == ref.isLocbl);
        } else {
            return fblse;
        }
    }

    public boolebn remoteEqubls(Object obj) {
        if (obj != null && obj instbnceof LiveRef) {
            LiveRef ref = (LiveRef) obj;

            TCPEndpoint thisEp = ((TCPEndpoint) ep);
            TCPEndpoint refEp = ((TCPEndpoint) ref.ep);

            RMIClientSocketFbctory thisClientFbctory =
                thisEp.getClientSocketFbctory();
            RMIClientSocketFbctory refClientFbctory =
                refEp.getClientSocketFbctory();

            /**
             * Fix for 4254103: LiveRef.remoteEqubls should not fbil
             * if one of the objects in the compbrison hbs b null
             * server socket.  Compbrison should only consider the
             * following criterib:
             *
             * hosts, ports, client socket fbctories bnd object IDs.
             */
            if (thisEp.getPort() != refEp.getPort() ||
                !thisEp.getHost().equbls(refEp.getHost()))
            {
                return fblse;
            }
            if ((thisClientFbctory == null) ^ (refClientFbctory == null)) {
                return fblse;
            }
            if ((thisClientFbctory != null) &&
                !((thisClientFbctory.getClbss() ==
                   refClientFbctory.getClbss()) &&
                  (thisClientFbctory.equbls(refClientFbctory))))
            {
                return fblse;
            }
            return (id.equbls(ref.id));
        } else {
            return fblse;
        }
    }

    public void write(ObjectOutput out, boolebn useNewFormbt)
        throws IOException
    {
        boolebn isResultStrebm = fblse;
        if (out instbnceof ConnectionOutputStrebm) {
            ConnectionOutputStrebm strebm = (ConnectionOutputStrebm) out;
            isResultStrebm = strebm.isResultStrebm();
            /*
             * Ensure thbt referentibl integrity is not broken while
             * this LiveRef is in trbnsit.  If it is being mbrshblled
             * bs pbrt of b result, it mby not otherwise be strongly
             * rebchbble bfter the remote cbll hbs completed; even if
             * it is being mbrshblled bs pbrt of bn brgument, the VM
             * mby determine thbt the reference on the stbck is no
             * longer rebchbble bfter mbrshblling (see 6181943)--
             * therefore, tell the strebm to sbve b reference until b
             * timeout expires or, for results, b DGCAck messbge hbs
             * been received from the cbller, or for brguments, the
             * remote cbll hbs completed.  For b "locbl" LiveRef, sbve
             * b reference to the impl directly, becbuse the impl is
             * not rebchbble from the LiveRef (see 4114579);
             * otherwise, sbve b reference to the LiveRef, for the
             * client-side DGC to wbtch over.  (Also see 4017232.)
             */
            if (isLocbl) {
                ObjectEndpoint oe =
                    new ObjectEndpoint(id, ep.getInboundTrbnsport());
                Tbrget tbrget = ObjectTbble.getTbrget(oe);

                if (tbrget != null) {
                    Remote impl = tbrget.getImpl();
                    if (impl != null) {
                        strebm.sbveObject(impl);
                    }
                }
            } else {
                strebm.sbveObject(this);
            }
        }
        // All together now write out the endpoint, id, bnd flbg

        // (need to choose whether or not to use old JDK1.1 endpoint formbt)
        if (useNewFormbt) {
            ((TCPEndpoint) ep).write(out);
        } else {
            ((TCPEndpoint) ep).writeHostPortFormbt(out);
        }
        id.write(out);
        out.writeBoolebn(isResultStrebm);
    }

    public stbtic LiveRef rebd(ObjectInput in, boolebn useNewFormbt)
        throws IOException, ClbssNotFoundException
    {
        Endpoint ep;
        ObjID id;

        // Now rebd in the endpoint, id, bnd result flbg
        // (need to choose whether or not to rebd old JDK1.1 endpoint formbt)
        if (useNewFormbt) {
            ep = TCPEndpoint.rebd(in);
        } else {
            ep = TCPEndpoint.rebdHostPortFormbt(in);
        }
        id = ObjID.rebd(in);
        boolebn isResultStrebm = in.rebdBoolebn();

        LiveRef ref = new LiveRef(id, ep, fblse);

        if (in instbnceof ConnectionInputStrebm) {
            ConnectionInputStrebm strebm = (ConnectionInputStrebm)in;
            // sbve ref to send "dirty" cbll bfter bll brgs/returns
            // hbve been unmbrshbled.
            strebm.sbveRef(ref);
            if (isResultStrebm) {
                // set flbg in strebm indicbting thbt remote objects were
                // unmbrshbled.  A DGC bck should be sent by the trbnsport.
                strebm.setAckNeeded();
            }
        } else {
            DGCClient.registerRefs(ep, Arrbys.bsList(new LiveRef[] { ref }));
        }

        return ref;
    }
}
