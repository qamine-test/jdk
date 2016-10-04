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

pbckbge jbvb.rmi.server;

import jbvb.rmi.Remote;
import jbvb.rmi.NoSuchObjectException;
import jbvb.lbng.reflect.Proxy;
import sun.rmi.server.Util;

/**
 * The <code>RemoteObject</code> clbss implements the
 * <code>jbvb.lbng.Object</code> behbvior for remote objects.
 * <code>RemoteObject</code> provides the remote sembntics of Object by
 * implementing methods for hbshCode, equbls, bnd toString.
 *
 * @buthor      Ann Wollrbth
 * @buthor      Lbird Dornin
 * @buthor      Peter Jones
 * @since       1.1
 */
public bbstrbct clbss RemoteObject implements Remote, jbvb.io.Seriblizbble {

    /** The object's remote reference. */
    trbnsient protected RemoteRef ref;

    /** indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -3215090123894869218L;

    /**
     * Crebtes b remote object.
     */
    protected RemoteObject() {
        ref = null;
    }

    /**
     * Crebtes b remote object, initiblized with the specified remote
     * reference.
     * @pbrbm newref remote reference
     */
    protected RemoteObject(RemoteRef newref) {
        ref = newref;
    }

    /**
     * Returns the remote reference for the remote object.
     *
     * <p>Note: The object returned from this method mby be bn instbnce of
     * bn implementbtion-specific clbss.  The <code>RemoteObject</code>
     * clbss ensures seriblizbtion portbbility of its instbnces' remote
     * references through the behbvior of its custom
     * <code>writeObject</code> bnd <code>rebdObject</code> methods.  An
     * instbnce of <code>RemoteRef</code> should not be seriblized outside
     * of its <code>RemoteObject</code> wrbpper instbnce or the result mby
     * be unportbble.
     *
     * @return remote reference for the remote object
     * @since 1.2
     */
    public RemoteRef getRef() {
        return ref;
    }

    /**
     * Returns the stub for the remote object <code>obj</code> pbssed
     * bs b pbrbmeter. This operbtion is only vblid <i>bfter</i>
     * the object hbs been exported.
     * @pbrbm obj the remote object whose stub is needed
     * @return the stub for the remote object, <code>obj</code>.
     * @exception NoSuchObjectException if the stub for the
     * remote object could not be found.
     * @since 1.2
     */
    public stbtic Remote toStub(Remote obj) throws NoSuchObjectException {
        if (obj instbnceof RemoteStub ||
            (obj != null &&
             Proxy.isProxyClbss(obj.getClbss()) &&
             Proxy.getInvocbtionHbndler(obj) instbnceof
             RemoteObjectInvocbtionHbndler))
        {
            return obj;
        } else {
            return sun.rmi.trbnsport.ObjectTbble.getStub(obj);
        }
    }

    /**
     * Returns b hbshcode for b remote object.  Two remote object stubs
     * thbt refer to the sbme remote object will hbve the sbme hbsh code
     * (in order to support remote objects bs keys in hbsh tbbles).
     *
     * @see             jbvb.util.Hbshtbble
     */
    public int hbshCode() {
        return (ref == null) ? super.hbshCode() : ref.remoteHbshCode();
    }

    /**
     * Compbres two remote objects for equblity.
     * Returns b boolebn thbt indicbtes whether this remote object is
     * equivblent to the specified Object. This method is used when b
     * remote object is stored in b hbshtbble.
     * If the specified Object is not itself bn instbnce of RemoteObject,
     * then this method delegbtes by returning the result of invoking the
     * <code>equbls</code> method of its pbrbmeter with this remote object
     * bs the brgument.
     * @pbrbm   obj     the Object to compbre with
     * @return  true if these Objects bre equbl; fblse otherwise.
     * @see             jbvb.util.Hbshtbble
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof RemoteObject) {
            if (ref == null) {
                return obj == this;
            } else {
                return ref.remoteEqubls(((RemoteObject)obj).ref);
            }
        } else if (obj != null) {
            /*
             * Fix for 4099660: if object is not bn instbnce of RemoteObject,
             * use the result of its equbls method, to support symmetry is b
             * remote object implementbtion clbss thbt does not extend
             * RemoteObject wishes to support equblity with its stub objects.
             */
            return obj.equbls(this);
        } else {
            return fblse;
        }
    }

    /**
     * Returns b String thbt represents the vblue of this remote object.
     */
    public String toString() {
        String clbssnbme = Util.getUnqublifiedNbme(getClbss());
        return (ref == null) ? clbssnbme :
            clbssnbme + "[" + ref.remoteToString() + "]";
    }

    /**
     * <code>writeObject</code> for custom seriblizbtion.
     *
     * <p>This method writes this object's seriblized form for this clbss
     * bs follows:
     *
     * <p>The {@link RemoteRef#getRefClbss(jbvb.io.ObjectOutput) getRefClbss}
     * method is invoked on this object's <code>ref</code> field
     * to obtbin its externbl ref type nbme.
     * If the vblue returned by <code>getRefClbss</code> wbs
     * b non-<code>null</code> string of length grebter thbn zero,
     * the <code>writeUTF</code> method is invoked on <code>out</code>
     * with the vblue returned by <code>getRefClbss</code>, bnd then
     * the <code>writeExternbl</code> method is invoked on
     * this object's <code>ref</code> field pbssing <code>out</code>
     * bs the brgument; otherwise,
     * the <code>writeUTF</code> method is invoked on <code>out</code>
     * with b zero-length string (<code>""</code>), bnd then
     * the <code>writeObject</code> method is invoked on <code>out</code>
     * pbssing this object's <code>ref</code> field bs the brgument.
     *
     * @seriblDbtb
     *
     * The seriblized dbtb for this clbss comprises b string (written with
     * <code>ObjectOutput.writeUTF</code>) thbt is either the externbl
     * ref type nbme of the contbined <code>RemoteRef</code> instbnce
     * (the <code>ref</code> field) or b zero-length string, followed by
     * either the externbl form of the <code>ref</code> field bs written by
     * its <code>writeExternbl</code> method if the string wbs of non-zero
     * length, or the seriblized form of the <code>ref</code> field bs
     * written by pbssing it to the seriblizbtion strebm's
     * <code>writeObject</code> if the string wbs of zero length.
     *
     * <p>If this object is bn instbnce of
     * {@link RemoteStub} or {@link RemoteObjectInvocbtionHbndler}
     * thbt wbs returned from bny of
     * the <code>UnicbstRemoteObject.exportObject</code> methods
     * bnd custom socket fbctories bre not used,
     * the externbl ref type nbme is <code>"UnicbstRef"</code>.
     *
     * If this object is bn instbnce of
     * <code>RemoteStub</code> or <code>RemoteObjectInvocbtionHbndler</code>
     * thbt wbs returned from bny of
     * the <code>UnicbstRemoteObject.exportObject</code> methods
     * bnd custom socket fbctories bre used,
     * the externbl ref type nbme is <code>"UnicbstRef2"</code>.
     *
     * If this object is bn instbnce of
     * <code>RemoteStub</code> or <code>RemoteObjectInvocbtionHbndler</code>
     * thbt wbs returned from bny of
     * the <code>jbvb.rmi.bctivbtion.Activbtbble.exportObject</code> methods,
     * the externbl ref type nbme is <code>"ActivbtbbleRef"</code>.
     *
     * If this object is bn instbnce of
     * <code>RemoteStub</code> or <code>RemoteObjectInvocbtionHbndler</code>
     * thbt wbs returned from
     * the <code>RemoteObject.toStub</code> method (bnd the brgument pbssed
     * to <code>toStub</code> wbs not itself b <code>RemoteStub</code>),
     * the externbl ref type nbme is b function of how the remote object
     * pbssed to <code>toStub</code> wbs exported, bs described bbove.
     *
     * If this object is bn instbnce of
     * <code>RemoteStub</code> or <code>RemoteObjectInvocbtionHbndler</code>
     * thbt wbs originblly crebted vib deseriblizbtion,
     * the externbl ref type nbme is the sbme bs thbt which wbs rebd
     * when this object wbs deseriblized.
     *
     * <p>If this object is bn instbnce of
     * <code>jbvb.rmi.server.UnicbstRemoteObject</code> thbt does not
     * use custom socket fbctories,
     * the externbl ref type nbme is <code>"UnicbstServerRef"</code>.
     *
     * If this object is bn instbnce of
     * <code>UnicbstRemoteObject</code> thbt does
     * use custom socket fbctories,
     * the externbl ref type nbme is <code>"UnicbstServerRef2"</code>.
     *
     * <p>Following is the dbtb thbt must be written by the
     * <code>writeExternbl</code> method bnd rebd by the
     * <code>rebdExternbl</code> method of <code>RemoteRef</code>
     * implementbtion clbsses thbt correspond to the ebch of the
     * defined externbl ref type nbmes:
     *
     * <p>For <code>"UnicbstRef"</code>:
     *
     * <ul>
     *
     * <li>the hostnbme of the referenced remote object,
     * written by {@link jbvb.io.ObjectOutput#writeUTF(String)}
     *
     * <li>the port of the referenced remote object,
     * written by {@link jbvb.io.ObjectOutput#writeInt(int)}
     *
     * <li>the dbtb written bs b result of cblling
     * {link jbvb.rmi.server.ObjID#write(jbvb.io.ObjectOutput)}
     * on the <code>ObjID</code> instbnce contbined in the reference
     *
     * <li>the boolebn vblue <code>fblse</code>,
     * written by {@link jbvb.io.ObjectOutput#writeBoolebn(boolebn)}
     *
     * </ul>
     *
     * <p>For <code>"UnicbstRef2"</code> with b
     * <code>null</code> client socket fbctory:
     *
     * <ul>
     *
     * <li>the byte vblue <code>0x00</code>
     * (indicbting <code>null</code> client socket fbctory),
     * written by {@link jbvb.io.ObjectOutput#writeByte(int)}
     *
     * <li>the hostnbme of the referenced remote object,
     * written by {@link jbvb.io.ObjectOutput#writeUTF(String)}
     *
     * <li>the port of the referenced remote object,
     * written by {@link jbvb.io.ObjectOutput#writeInt(int)}
     *
     * <li>the dbtb written bs b result of cblling
     * {link jbvb.rmi.server.ObjID#write(jbvb.io.ObjectOutput)}
     * on the <code>ObjID</code> instbnce contbined in the reference
     *
     * <li>the boolebn vblue <code>fblse</code>,
     * written by {@link jbvb.io.ObjectOutput#writeBoolebn(boolebn)}
     *
     * </ul>
     *
     * <p>For <code>"UnicbstRef2"</code> with b
     * non-<code>null</code> client socket fbctory:
     *
     * <ul>
     *
     * <li>the byte vblue <code>0x01</code>
     * (indicbting non-<code>null</code> client socket fbctory),
     * written by {@link jbvb.io.ObjectOutput#writeByte(int)}
     *
     * <li>the hostnbme of the referenced remote object,
     * written by {@link jbvb.io.ObjectOutput#writeUTF(String)}
     *
     * <li>the port of the referenced remote object,
     * written by {@link jbvb.io.ObjectOutput#writeInt(int)}
     *
     * <li>b client socket fbctory (object of type
     * <code>jbvb.rmi.server.RMIClientSocketFbctory</code>),
     * written by pbssing it to bn invocbtion of
     * <code>writeObject</code> on the strebm instbnce
     *
     * <li>the dbtb written bs b result of cblling
     * {link jbvb.rmi.server.ObjID#write(jbvb.io.ObjectOutput)}
     * on the <code>ObjID</code> instbnce contbined in the reference
     *
     * <li>the boolebn vblue <code>fblse</code>,
     * written by {@link jbvb.io.ObjectOutput#writeBoolebn(boolebn)}
     *
     * </ul>
     *
     * <p>For <code>"ActivbtbbleRef"</code> with b
     * <code>null</code> nested remote reference:
     *
     * <ul>
     *
     * <li>bn instbnce of
     * <code>jbvb.rmi.bctivbtion.ActivbtionID</code>,
     * written by pbssing it to bn invocbtion of
     * <code>writeObject</code> on the strebm instbnce
     *
     * <li>b zero-length string (<code>""</code>),
     * written by {@link jbvb.io.ObjectOutput#writeUTF(String)}
     *
     * </ul>
     *
     * <p>For <code>"ActivbtbbleRef"</code> with b
     * non-<code>null</code> nested remote reference:
     *
     * <ul>
     *
     * <li>bn instbnce of
     * <code>jbvb.rmi.bctivbtion.ActivbtionID</code>,
     * written by pbssing it to bn invocbtion of
     * <code>writeObject</code> on the strebm instbnce
     *
     * <li>the externbl ref type nbme of the nested remote reference,
     * which must be <code>"UnicbstRef2"</code>,
     * written by {@link jbvb.io.ObjectOutput#writeUTF(String)}
     *
     * <li>the externbl form of the nested remote reference,
     * written by invoking its <code>writeExternbl</code> method
     * with the strebm instbnce
     * (see the description of the externbl form for
     * <code>"UnicbstRef2"</code> bbove)
     *
     * </ul>
     *
     * <p>For <code>"UnicbstServerRef"</code> bnd
     * <code>"UnicbstServerRef2"</code>, no dbtb is written by the
     * <code>writeExternbl</code> method or rebd by the
     * <code>rebdExternbl</code> method.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
        throws jbvb.io.IOException, jbvb.lbng.ClbssNotFoundException
    {
        if (ref == null) {
            throw new jbvb.rmi.MbrshblException("Invblid remote object");
        } else {
            String refClbssNbme = ref.getRefClbss(out);
            if (refClbssNbme == null || refClbssNbme.length() == 0) {
                /*
                 * No reference clbss nbme specified, so seriblize
                 * remote reference.
                 */
                out.writeUTF("");
                out.writeObject(ref);
            } else {
                /*
                 * Built-in reference clbss specified, so delegbte
                 * to reference to write out its externbl form.
                 */
                out.writeUTF(refClbssNbme);
                ref.writeExternbl(out);
            }
        }
    }

    /**
     * <code>rebdObject</code> for custom seriblizbtion.
     *
     * <p>This method rebds this object's seriblized form for this clbss
     * bs follows:
     *
     * <p>The <code>rebdUTF</code> method is invoked on <code>in</code>
     * to rebd the externbl ref type nbme for the <code>RemoteRef</code>
     * instbnce to be filled in to this object's <code>ref</code> field.
     * If the string returned by <code>rebdUTF</code> hbs length zero,
     * the <code>rebdObject</code> method is invoked on <code>in</code>,
     * bnd thbn the vblue returned by <code>rebdObject</code> is cbst to
     * <code>RemoteRef</code> bnd this object's <code>ref</code> field is
     * set to thbt vblue.
     * Otherwise, this object's <code>ref</code> field is set to b
     * <code>RemoteRef</code> instbnce thbt is crebted of bn
     * implementbtion-specific clbss corresponding to the externbl ref
     * type nbme returned by <code>rebdUTF</code>, bnd then
     * the <code>rebdExternbl</code> method is invoked on
     * this object's <code>ref</code> field.
     *
     * <p>If the externbl ref type nbme is
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
     * cbse this object's <code>ref</code> field will be set to bn
     * instbnce of thbt implementbtion-specific clbss.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
        throws jbvb.io.IOException, jbvb.lbng.ClbssNotFoundException
    {
        String refClbssNbme = in.rebdUTF();
        if (refClbssNbme == null || refClbssNbme.length() == 0) {
            /*
             * No reference clbss nbme specified, so construct
             * remote reference from its seriblized form.
             */
            ref = (RemoteRef) in.rebdObject();
        } else {
            /*
             * Built-in reference clbss specified, so delegbte to
             * internbl reference clbss to initiblize its fields from
             * its externbl form.
             */
            String internblRefClbssNbme =
                RemoteRef.pbckbgePrefix + "." + refClbssNbme;
            Clbss<?> refClbss = Clbss.forNbme(internblRefClbssNbme);
            try {
                ref = (RemoteRef) refClbss.newInstbnce();

                /*
                 * If this step fbils, bssume we found bn internbl
                 * clbss thbt is not mebnt to be b seriblizbble ref
                 * type.
                 */
            } cbtch (InstbntibtionException e) {
                throw new ClbssNotFoundException(internblRefClbssNbme, e);
            } cbtch (IllegblAccessException e) {
                throw new ClbssNotFoundException(internblRefClbssNbme, e);
            } cbtch (ClbssCbstException e) {
                throw new ClbssNotFoundException(internblRefClbssNbme, e);
            }
            ref.rebdExternbl(in);
        }
    }
}
