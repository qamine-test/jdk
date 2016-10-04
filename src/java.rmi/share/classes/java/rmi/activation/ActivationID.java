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

pbckbge jbvb.rmi.bctivbtion;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Proxy;
import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.UnmbrshblException;
import jbvb.rmi.server.RemoteObject;
import jbvb.rmi.server.RemoteObjectInvocbtionHbndler;
import jbvb.rmi.server.RemoteRef;
import jbvb.rmi.server.UID;

/**
 * Activbtion mbkes use of specibl identifiers to denote remote
 * objects thbt cbn be bctivbted over time. An bctivbtion identifier
 * (bn instbnce of the clbss <code>ActivbtionID</code>) contbins severbl
 * pieces of informbtion needed for bctivbting bn object:
 * <ul>
 * <li> b remote reference to the object's bctivbtor (b {@link
 * jbvb.rmi.server.RemoteRef RemoteRef}
 * instbnce), bnd
 * <li> b unique identifier (b {@link jbvb.rmi.server.UID UID}
 * instbnce) for the object. </ul> <p>
 *
 * An bctivbtion identifier for bn object cbn be obtbined by registering
 * bn object with the bctivbtion system. Registrbtion is bccomplished
 * in b few wbys: <ul>
 * <li>vib the <code>Activbtbble.register</code> method
 * <li>vib the first <code>Activbtbble</code> constructor (thbt tbkes
 * three brguments bnd both registers bnd exports the object, bnd
 * <li>vib the first <code>Activbtbble.exportObject</code> method
 * thbt tbkes the bctivbtion descriptor, object bnd port bs brguments;
 * this method both registers bnd exports the object. </ul>
 *
 * @buthor      Ann Wollrbth
 * @see         Activbtbble
 * @since       1.2
 */
public clbss ActivbtionID implements Seriblizbble {
    /**
     * the object's bctivbtor
     */
    privbte trbnsient Activbtor bctivbtor;

    /**
     * the object's unique id
     */
    privbte trbnsient UID uid = new UID();

    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = -4608673054848209235L;

    /**
     * The constructor for <code>ActivbtionID</code> tbkes b single
     * brgument, bctivbtor, thbt specifies b remote reference to the
     * bctivbtor responsible for bctivbting the object bssocibted with
     * this identifier. An instbnce of <code>ActivbtionID</code> is globblly
     * unique.
     *
     * @pbrbm bctivbtor reference to the bctivbtor responsible for
     * bctivbting the object
     * @throws UnsupportedOperbtionException if bnd only if bctivbtion is
     *         not supported by this implementbtion
     * @since 1.2
     */
    public ActivbtionID(Activbtor bctivbtor) {
        this.bctivbtor = bctivbtor;
    }

    /**
     * Activbte the object for this id.
     *
     * @pbrbm force if true, forces the bctivbtor to contbct the group
     * when bctivbting the object (instebd of returning b cbched reference);
     * if fblse, returning b cbched vblue is bcceptbble.
     * @return the reference to the bctive remote object
     * @exception ActivbtionException if bctivbtion fbils
     * @exception UnknownObjectException if the object is unknown
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public Remote bctivbte(boolebn force)
        throws ActivbtionException, UnknownObjectException, RemoteException
    {
        try {
            MbrshblledObject<? extends Remote> mobj =
                bctivbtor.bctivbte(this, force);
            return mobj.get();
        } cbtch (RemoteException e) {
            throw e;
        } cbtch (IOException e) {
            throw new UnmbrshblException("bctivbtion fbiled", e);
        } cbtch (ClbssNotFoundException e) {
            throw new UnmbrshblException("bctivbtion fbiled", e);
        }

    }

    /**
     * Returns b hbshcode for the bctivbtion id.  Two identifiers thbt
     * refer to the sbme remote object will hbve the sbme hbsh code.
     *
     * @see jbvb.util.Hbshtbble
     * @since 1.2
     */
    public int hbshCode() {
        return uid.hbshCode();
    }

    /**
     * Compbres two bctivbtion ids for content equblity.
     * Returns true if both of the following conditions bre true:
     * 1) the unique identifiers equivblent (by content), bnd
     * 2) the bctivbtor specified in ebch identifier
     *    refers to the sbme remote object.
     *
     * @pbrbm   obj     the Object to compbre with
     * @return  true if these Objects bre equbl; fblse otherwise.
     * @see             jbvb.util.Hbshtbble
     * @since 1.2
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof ActivbtionID) {
            ActivbtionID id = (ActivbtionID) obj;
            return (uid.equbls(id.uid) && bctivbtor.equbls(id.bctivbtor));
        } else {
            return fblse;
        }
    }

    /**
     * <code>writeObject</code> for custom seriblizbtion.
     *
     * <p>This method writes this object's seriblized form for
     * this clbss bs follows:
     *
     * <p>The <code>writeObject</code> method is invoked on
     * <code>out</code> pbssing this object's unique identifier
     * (b {@link jbvb.rmi.server.UID UID} instbnce) bs the brgument.
     *
     * <p>Next, the {@link
     * jbvb.rmi.server.RemoteRef#getRefClbss(jbvb.io.ObjectOutput)
     * getRefClbss} method is invoked on the bctivbtor's
     * <code>RemoteRef</code> instbnce to obtbin its externbl ref
     * type nbme.  Next, the <code>writeUTF</code> method is
     * invoked on <code>out</code> with the vblue returned by
     * <code>getRefClbss</code>, bnd then the
     * <code>writeExternbl</code> method is invoked on the
     * <code>RemoteRef</code> instbnce pbssing <code>out</code>
     * bs the brgument.
     *
     * @seriblDbtb The seriblized dbtb for this clbss comprises b
     * <code>jbvb.rmi.server.UID</code> (written with
     * <code>ObjectOutput.writeObject</code>) followed by the
     * externbl ref type nbme of the bctivbtor's
     * <code>RemoteRef</code> instbnce (b string written with
     * <code>ObjectOutput.writeUTF</code>), followed by the
     * externbl form of the <code>RemoteRef</code> instbnce bs
     * written by its <code>writeExternbl</code> method.
     *
     * <p>The externbl ref type nbme of the
     * <code>RemoteRef</Code> instbnce is
     * determined using the definitions of externbl ref type
     * nbmes specified in the {@link jbvb.rmi.server.RemoteObject
     * RemoteObject} <code>writeObject</code> method
     * <b>seriblDbtb</b> specificbtion.  Similbrly, the dbtb
     * written by the <code>writeExternbl</code> method bnd rebd
     * by the <code>rebdExternbl</code> method of
     * <code>RemoteRef</code> implementbtion clbsses
     * corresponding to ebch of the defined externbl ref type
     * nbmes is specified in the {@link
     * jbvb.rmi.server.RemoteObject RemoteObject}
     * <code>writeObject</code> method <b>seriblDbtb</b>
     * specificbtion.
     **/
    privbte void writeObject(ObjectOutputStrebm out)
        throws IOException, ClbssNotFoundException
    {
        out.writeObject(uid);

        RemoteRef ref;
        if (bctivbtor instbnceof RemoteObject) {
            ref = ((RemoteObject) bctivbtor).getRef();
        } else if (Proxy.isProxyClbss(bctivbtor.getClbss())) {
            InvocbtionHbndler hbndler = Proxy.getInvocbtionHbndler(bctivbtor);
            if (!(hbndler instbnceof RemoteObjectInvocbtionHbndler)) {
                throw new InvblidObjectException(
                    "unexpected invocbtion hbndler");
            }
            ref = ((RemoteObjectInvocbtionHbndler) hbndler).getRef();

        } else {
            throw new InvblidObjectException("unexpected bctivbtor type");
        }
        out.writeUTF(ref.getRefClbss(out));
        ref.writeExternbl(out);
    }

    /**
     * <code>rebdObject</code> for custom seriblizbtion.
     *
     * <p>This method rebds this object's seriblized form for this
     * clbss bs follows:
     *
     * <p>The <code>rebdObject</code> method is invoked on
     * <code>in</code> to rebd this object's unique identifier
     * (b {@link jbvb.rmi.server.UID UID} instbnce).
     *
     * <p>Next, the <code>rebdUTF</code> method is invoked on
     * <code>in</code> to rebd the externbl ref type nbme of the
     * <code>RemoteRef</code> instbnce for this object's
     * bctivbtor.  Next, the <code>RemoteRef</code>
     * instbnce is crebted of bn implementbtion-specific clbss
     * corresponding to the externbl ref type nbme (returned by
     * <code>rebdUTF</code>), bnd the <code>rebdExternbl</code>
     * method is invoked on thbt <code>RemoteRef</code> instbnce
     * to rebd the externbl form corresponding to the externbl
     * ref type nbme.
     *
     * <p>Note: If the externbl ref type nbme is
     * <code>"UnicbstRef"</code>, <code>"UnicbstServerRef"</code>,
     * <code>"UnicbstRef2"</code>, <code>"UnicbstServerRef2"</code>,
     * or <code>"ActivbtbbleRef"</code>, b corresponding
     * implementbtion-specific clbss must be found, bnd its
     * <code>rebdExternbl</code> method must rebd the seribl dbtb
     * for thbt externbl ref type nbme bs specified to be written
     * in the <b>seriblDbtb</b> documentbtion for this clbss.
     * If the externbl ref type nbme is bny other string (of non-zero
     * length), b <code>ClbssNotFoundException</code> will be thrown,
     * unless the implementbtion provides bn implementbtion-specific
     * clbss corresponding to thbt externbl ref type nbme, in which
     * cbse the <code>RemoteRef</code> will be bn instbnce of
     * thbt implementbtion-specific clbss.
     */
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        uid = (UID)in.rebdObject();

        try {
            Clbss<? extends RemoteRef> refClbss =
                Clbss.forNbme(RemoteRef.pbckbgePrefix + "." + in.rebdUTF())
                .bsSubclbss(RemoteRef.clbss);
            RemoteRef ref = refClbss.newInstbnce();
            ref.rebdExternbl(in);
            bctivbtor = (Activbtor)
                Proxy.newProxyInstbnce(null,
                                       new Clbss<?>[] { Activbtor.clbss },
                                       new RemoteObjectInvocbtionHbndler(ref));

        } cbtch (InstbntibtionException e) {
            throw (IOException)
                new InvblidObjectException(
                    "Unbble to crebte remote reference").initCbuse(e);
        } cbtch (IllegblAccessException e) {
            throw (IOException)
                new InvblidObjectException(
                    "Unbble to crebte remote reference").initCbuse(e);
        }
    }
}
