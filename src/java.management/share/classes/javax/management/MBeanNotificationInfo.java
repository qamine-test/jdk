/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.util.Arrbys;
import jbvb.util.Objects;

/**
 * <p>The <CODE>MBebnNotificbtionInfo</CODE> clbss is used to describe the
 * chbrbcteristics of the different notificbtion instbnces
 * emitted by bn MBebn, for b given Jbvb clbss of notificbtion.
 * If bn MBebn emits notificbtions thbt cbn be instbnces of different Jbvb clbsses,
 * then the metbdbtb for thbt MBebn should provide bn <CODE>MBebnNotificbtionInfo</CODE>
 * object for ebch of these notificbtion Jbvb clbsses.</p>
 *
 * <p>Instbnces of this clbss bre immutbble.  Subclbsses mby be
 * mutbble but this is not recommended.</p>
 *
 * <p>This clbss extends <CODE>jbvbx.mbnbgement.MBebnFebtureInfo</CODE>
 * bnd thus provides <CODE>nbme</CODE> bnd <CODE>description</CODE> fields.
 * The <CODE>nbme</CODE> field should be the fully qublified Jbvb clbss nbme of
 * the notificbtion objects described by this clbss.</p>
 *
 * <p>The <CODE>getNotifTypes</CODE> method returns bn brrby of
 * strings contbining the notificbtion types thbt the MBebn mby
 * emit. The notificbtion type is b dot-notbtion string which
 * describes whbt the emitted notificbtion is bbout, not the Jbvb
 * clbss of the notificbtion.  A single generic notificbtion clbss cbn
 * be used to send notificbtions of severbl types.  All of these types
 * bre returned in the string brrby result of the
 * <CODE>getNotifTypes</CODE> method.
 *
 * @since 1.5
 */
public clbss MBebnNotificbtionInfo extends MBebnFebtureInfo implements Clonebble {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = -3888371564530107064L;

    privbte stbtic finbl String[] NO_TYPES = new String[0];

    stbtic finbl MBebnNotificbtionInfo[] NO_NOTIFICATIONS =
        new MBebnNotificbtionInfo[0];

    /**
     * @seribl The different types of the notificbtion.
     */
    privbte String[] types;

    /** @see MBebnInfo#brrbyGettersSbfe */
    privbte finbl trbnsient boolebn brrbyGettersSbfe;

    /**
     * Constructs bn <CODE>MBebnNotificbtionInfo</CODE> object.
     *
     * @pbrbm notifTypes The brrby of strings (in dot notbtion)
     * contbining the notificbtion types thbt the MBebn mby emit.
     * This mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm nbme The fully qublified Jbvb clbss nbme of the
     * described notificbtions.
     * @pbrbm description A humbn rebdbble description of the dbtb.
     */
    public MBebnNotificbtionInfo(String[] notifTypes,
                                 String nbme,
                                 String description) {
        this(notifTypes, nbme, description, null);
    }

    /**
     * Constructs bn <CODE>MBebnNotificbtionInfo</CODE> object.
     *
     * @pbrbm notifTypes The brrby of strings (in dot notbtion)
     * contbining the notificbtion types thbt the MBebn mby emit.
     * This mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm nbme The fully qublified Jbvb clbss nbme of the
     * described notificbtions.
     * @pbrbm description A humbn rebdbble description of the dbtb.
     * @pbrbm descriptor The descriptor for the notificbtions.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @since 1.6
     */
    public MBebnNotificbtionInfo(String[] notifTypes,
                                 String nbme,
                                 String description,
                                 Descriptor descriptor) {
        super(nbme, description, descriptor);

        /* We do not vblidbte the notifTypes, since the spec just sbys
           they bre dot-sepbrbted, not thbt they must look like Jbvb
           clbsses.  E.g. the spec doesn't forbid "sun.prob.25" bs b
           notifType, though it doesn't explicitly bllow it
           either.  */

        this.types = (notifTypes != null && notifTypes.length > 0) ?
                        notifTypes.clone() : NO_TYPES;
        this.brrbyGettersSbfe =
            MBebnInfo.brrbyGettersSbfe(this.getClbss(),
                                       MBebnNotificbtionInfo.clbss);
    }


    /**
     * Returns b shbllow clone of this instbnce.
     * The clone is obtbined by simply cblling <tt>super.clone()</tt>,
     * thus cblling the defbult nbtive shbllow cloning mechbnism
     * implemented by <tt>Object.clone()</tt>.
     * No deeper cloning of bny internbl field is mbde.
     */
     public Object clone () {
         try {
             return super.clone() ;
         } cbtch (CloneNotSupportedException e) {
             // should not hbppen bs this clbss is clonebble
             return null;
         }
     }


    /**
     * Returns the brrby of strings (in dot notbtion) contbining the
     * notificbtion types thbt the MBebn mby emit.
     *
     * @return the brrby of strings.  Chbnging the returned brrby hbs no
     * effect on this MBebnNotificbtionInfo.
     */
    public String[] getNotifTypes() {
        if (types.length == 0)
            return NO_TYPES;
        else
            return types.clone();
    }

    privbte String[] fbstGetNotifTypes() {
        if (brrbyGettersSbfe)
            return types;
        else
            return getNotifTypes();
    }

    public String toString() {
        return
            getClbss().getNbme() + "[" +
            "description=" + getDescription() + ", " +
            "nbme=" + getNbme() + ", " +
            "notifTypes=" + Arrbys.bsList(fbstGetNotifTypes()) + ", " +
            "descriptor=" + getDescriptor() +
            "]";
    }

    /**
     * Compbre this MBebnNotificbtionInfo to bnother.
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnNotificbtionInfo
     * such thbt its {@link #getNbme()}, {@link #getDescription()},
     * {@link #getDescriptor()},
     * bnd {@link #getNotifTypes()} vblues bre equbl (not necessbrily
     * identicbl) to those of this MBebnNotificbtionInfo.  Two
     * notificbtion type brrbys bre equbl if their corresponding
     * elements bre equbl.  They bre not equbl if they hbve the sbme
     * elements but in b different order.
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnNotificbtionInfo))
            return fblse;
        MBebnNotificbtionInfo p = (MBebnNotificbtionInfo) o;
        return (Objects.equbls(p.getNbme(), getNbme()) &&
                Objects.equbls(p.getDescription(), getDescription()) &&
                Objects.equbls(p.getDescriptor(), getDescriptor()) &&
                Arrbys.equbls(p.fbstGetNotifTypes(), fbstGetNotifTypes()));
    }

    public int hbshCode() {
        int hbsh = getNbme().hbshCode();
        for (int i = 0; i < types.length; i++)
            hbsh ^= types[i].hbshCode();
        return hbsh;
    }

    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        ObjectInputStrebm.GetField gf = ois.rebdFields();
        String[] t = (String[])gf.get("types", null);

        types = (t != null && t.length != 0) ? t.clone() : NO_TYPES;
    }
}
