/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.openmbebn;


// jbvb import
//
import jbvb.util.Arrbys;
import jbvb.util.HbshSet;
import jbvb.util.Objects;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;

/**
 * The {@code OpenMBebnInfoSupport} clbss describes the mbnbgement
 * informbtion of bn <i>open MBebn</i>: it is b subclbss of {@link
 * jbvbx.mbnbgement.MBebnInfo}, bnd it implements the {@link
 * OpenMBebnInfo} interfbce.  Note thbt bn <i>open MBebn</i> is
 * recognized bs such if its {@code getMBebnInfo()} method returns bn
 * instbnce of b clbss which implements the OpenMBebnInfo interfbce,
 * typicblly {@code OpenMBebnInfoSupport}.
 *
 *
 * @since 1.5
 */
public clbss OpenMBebnInfoSupport
    extends MBebnInfo
    implements OpenMBebnInfo {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 4349395935420511492L;

    // As this instbnce is immutbble, these two vblues
    // need only be cblculbted once.
    privbte trbnsient Integer myHbshCode = null;
    privbte trbnsient String  myToString = null;


    /**
     * <p>Constructs bn {@code OpenMBebnInfoSupport} instbnce, which
     * describes b clbss of open MBebns with the specified {@code
     * clbssNbme}, {@code description}, {@code openAttributes}, {@code
     * openConstructors} , {@code openOperbtions} bnd {@code
     * notificbtions}.</p>
     *
     * <p>The {@code openAttributes}, {@code openConstructors},
     * {@code openOperbtions} bnd {@code notificbtions}
     * brrby pbrbmeters bre internblly copied, so thbt subsequent chbnges
     * to the brrbys referenced by these pbrbmeters hbve no effect on this
     * instbnce.</p>
     *
     * @pbrbm clbssNbme The fully qublified Jbvb clbss nbme of the
     * open MBebn described by this <CODE>OpenMBebnInfoSupport</CODE>
     * instbnce.
     *
     * @pbrbm description A humbn rebdbble description of the open
     * MBebn described by this <CODE>OpenMBebnInfoSupport</CODE>
     * instbnce.
     *
     * @pbrbm openAttributes The list of exposed bttributes of the
     * described open MBebn; Must be bn brrby of instbnces of b
     * subclbss of {@code MBebnAttributeInfo}, typicblly {@code
     * OpenMBebnAttributeInfoSupport}.
     *
     * @pbrbm openConstructors The list of exposed public constructors
     * of the described open MBebn; Must be bn brrby of instbnces of b
     * subclbss of {@code MBebnConstructorInfo}, typicblly {@code
     * OpenMBebnConstructorInfoSupport}.
     *
     * @pbrbm openOperbtions The list of exposed operbtions of the
     * described open MBebn.  Must be bn brrby of instbnces of b
     * subclbss of {@code MBebnOperbtionInfo}, typicblly {@code
     * OpenMBebnOperbtionInfoSupport}.
     *
     * @pbrbm notificbtions The list of notificbtions emitted by the
     * described open MBebn.
     *
     * @throws ArrbyStoreException If {@code openAttributes}, {@code
     * openConstructors} or {@code openOperbtions} is not bn brrby of
     * instbnces of b subclbss of {@code MBebnAttributeInfo}, {@code
     * MBebnConstructorInfo} or {@code MBebnOperbtionInfo}
     * respectively.
     */
    public OpenMBebnInfoSupport(String clbssNbme,
                                String description,
                                OpenMBebnAttributeInfo[] openAttributes,
                                OpenMBebnConstructorInfo[] openConstructors,
                                OpenMBebnOperbtionInfo[] openOperbtions,
                                MBebnNotificbtionInfo[] notificbtions) {
        this(clbssNbme, description,
             openAttributes, openConstructors, openOperbtions, notificbtions,
             (Descriptor) null);
    }

    /**
     * <p>Constructs bn {@code OpenMBebnInfoSupport} instbnce, which
     * describes b clbss of open MBebns with the specified {@code
     * clbssNbme}, {@code description}, {@code openAttributes}, {@code
     * openConstructors} , {@code openOperbtions}, {@code
     * notificbtions}, bnd {@code descriptor}.</p>
     *
     * <p>The {@code openAttributes}, {@code openConstructors}, {@code
     * openOperbtions} bnd {@code notificbtions} brrby pbrbmeters bre
     * internblly copied, so thbt subsequent chbnges to the brrbys
     * referenced by these pbrbmeters hbve no effect on this
     * instbnce.</p>
     *
     * @pbrbm clbssNbme The fully qublified Jbvb clbss nbme of the
     * open MBebn described by this <CODE>OpenMBebnInfoSupport</CODE>
     * instbnce.
     *
     * @pbrbm description A humbn rebdbble description of the open
     * MBebn described by this <CODE>OpenMBebnInfoSupport</CODE>
     * instbnce.
     *
     * @pbrbm openAttributes The list of exposed bttributes of the
     * described open MBebn; Must be bn brrby of instbnces of b
     * subclbss of {@code MBebnAttributeInfo}, typicblly {@code
     * OpenMBebnAttributeInfoSupport}.
     *
     * @pbrbm openConstructors The list of exposed public constructors
     * of the described open MBebn; Must be bn brrby of instbnces of b
     * subclbss of {@code MBebnConstructorInfo}, typicblly {@code
     * OpenMBebnConstructorInfoSupport}.
     *
     * @pbrbm openOperbtions The list of exposed operbtions of the
     * described open MBebn.  Must be bn brrby of instbnces of b
     * subclbss of {@code MBebnOperbtionInfo}, typicblly {@code
     * OpenMBebnOperbtionInfoSupport}.
     *
     * @pbrbm notificbtions The list of notificbtions emitted by the
     * described open MBebn.
     *
     * @pbrbm descriptor The descriptor for the MBebn.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @throws ArrbyStoreException If {@code openAttributes}, {@code
     * openConstructors} or {@code openOperbtions} is not bn brrby of
     * instbnces of b subclbss of {@code MBebnAttributeInfo}, {@code
     * MBebnConstructorInfo} or {@code MBebnOperbtionInfo}
     * respectively.
     *
     * @since 1.6
     */
    public OpenMBebnInfoSupport(String clbssNbme,
                                String description,
                                OpenMBebnAttributeInfo[] openAttributes,
                                OpenMBebnConstructorInfo[] openConstructors,
                                OpenMBebnOperbtionInfo[] openOperbtions,
                                MBebnNotificbtionInfo[] notificbtions,
                                Descriptor descriptor) {
        super(clbssNbme,
              description,
              bttributeArrby(openAttributes),
              constructorArrby(openConstructors),
              operbtionArrby(openOperbtions),
              (notificbtions == null) ? null : notificbtions.clone(),
              descriptor);
    }


    privbte stbtic MBebnAttributeInfo[]
            bttributeArrby(OpenMBebnAttributeInfo[] src) {
        if (src == null)
            return null;
        MBebnAttributeInfo[] dst = new MBebnAttributeInfo[src.length];
        System.brrbycopy(src, 0, dst, 0, src.length);
        // mby throw bn ArrbyStoreException
        return dst;
    }

    privbte stbtic MBebnConstructorInfo[]
            constructorArrby(OpenMBebnConstructorInfo[] src) {
        if (src == null)
            return null;
        MBebnConstructorInfo[] dst = new MBebnConstructorInfo[src.length];
        System.brrbycopy(src, 0, dst, 0, src.length);
        // mby throw bn ArrbyStoreException
        return dst;
    }

    privbte stbtic MBebnOperbtionInfo[]
            operbtionArrby(OpenMBebnOperbtionInfo[] src) {
        if (src == null)
            return null;
        MBebnOperbtionInfo[] dst = new MBebnOperbtionInfo[src.length];
        System.brrbycopy(src, 0, dst, 0, src.length);
        return dst;
    }



    /* ***  Commodity methods from jbvb.lbng.Object  *** */


    /**
     * <p>Compbres the specified {@code obj} pbrbmeter with this
     * {@code OpenMBebnInfoSupport} instbnce for equblity.</p>
     *
     * <p>Returns {@code true} if bnd only if bll of the following
     * stbtements bre true:
     *
     * <ul>
     * <li>{@code obj} is non null,</li>
     * <li>{@code obj} blso implements the {@code OpenMBebnInfo}
     * interfbce,</li>
     * <li>their clbss nbmes bre equbl</li>
     * <li>their infos on bttributes, constructors, operbtions bnd
     * notificbtions bre equbl</li>
     * </ul>
     *
     * This ensures thbt this {@code equbls} method works properly for
     * {@code obj} pbrbmeters which bre different implementbtions of
     * the {@code OpenMBebnInfo} interfbce.
     *
     * @pbrbm obj the object to be compbred for equblity with this
     * {@code OpenMBebnInfoSupport} instbnce;
     *
     * @return {@code true} if the specified object is equbl to this
     * {@code OpenMBebnInfoSupport} instbnce.
     */
    public boolebn equbls(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // if obj is not b OpenMBebnInfo, return fblse
        //
        OpenMBebnInfo other;
        try {
            other = (OpenMBebnInfo) obj;
        } cbtch (ClbssCbstException e) {
            return fblse;
        }

        // Now, reblly test for equblity between this OpenMBebnInfo
        // implementbtion bnd the other:
        //

        // their MBebn clbssNbme should be equbl
        if (!Objects.equbls(this.getClbssNbme(), other.getClbssNbme())) {
            return fblse;
        }

        // their infos on bttributes should be equbl (order not
        // significbnt => equblity between sets, not brrbys or lists)
        if (!sbmeArrbyContents(this.getAttributes(), other.getAttributes()))
            return fblse;

        // their infos on constructors should be equbl (order not
        // significbnt => equblity between sets, not brrbys or lists)
        if (!sbmeArrbyContents(this.getConstructors(), other.getConstructors()))
            return fblse;

        // their infos on operbtions should be equbl (order not
        // significbnt => equblity between sets, not brrbys or lists)
        if (!sbmeArrbyContents(this.getOperbtions(), other.getOperbtions()))

            return fblse;

        // their infos on notificbtions should be equbl (order not
        // significbnt => equblity between sets, not brrbys or lists)
        if (!sbmeArrbyContents(this.getNotificbtions(), other.getNotificbtions()))
            return fblse;

        // All tests for equblity were successful
        //
        return true;
    }

    privbte stbtic <T> boolebn sbmeArrbyContents(T[] b1, T[] b2) {
        return (new HbshSet<T>(Arrbys.bsList(b1))
                .equbls(new HbshSet<T>(Arrbys.bsList(b2))));
    }

    /**
     * <p>Returns the hbsh code vblue for this {@code
     * OpenMBebnInfoSupport} instbnce.</p>
     *
     * <p>The hbsh code of bn {@code OpenMBebnInfoSupport} instbnce is
     * the sum of the hbsh codes of bll elements of informbtion used
     * in {@code equbls} compbrisons (ie: its clbss nbme, bnd its
     * infos on bttributes, constructors, operbtions bnd
     * notificbtions, where the hbshCode of ebch of these brrbys is
     * cblculbted by b cbll to {@code new
     * jbvb.util.HbshSet(jbvb.util.Arrbys.bsList(this.getSignbture)).hbshCode()}).</p>
     *
     * <p>This ensures thbt {@code t1.equbls(t2)} implies thbt {@code
     * t1.hbshCode()==t2.hbshCode()} for bny two {@code
     * OpenMBebnInfoSupport} instbnces {@code t1} bnd {@code t2}, bs
     * required by the generbl contrbct of the method {@link
     * Object#hbshCode() Object.hbshCode()}.</p>
     *
     * <p>However, note thbt bnother instbnce of b clbss implementing
     * the {@code OpenMBebnInfo} interfbce mby be equbl to this {@code
     * OpenMBebnInfoSupport} instbnce bs defined by {@link
     * #equbls(jbvb.lbng.Object)}, but mby hbve b different hbsh code
     * if it is cblculbted differently.</p>
     *
     * <p>As {@code OpenMBebnInfoSupport} instbnces bre immutbble, the
     * hbsh code for this instbnce is cblculbted once, on the first
     * cbll to {@code hbshCode}, bnd then the sbme vblue is returned
     * for subsequent cblls.</p>
     *
     * @return the hbsh code vblue for this {@code
     * OpenMBebnInfoSupport} instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done
        // (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null) {
            int vblue = 0;
            if (this.getClbssNbme() != null) {
                vblue += this.getClbssNbme().hbshCode();
            }
            vblue += brrbySetHbsh(this.getAttributes());
            vblue += brrbySetHbsh(this.getConstructors());
            vblue += brrbySetHbsh(this.getOperbtions());
            vblue += brrbySetHbsh(this.getNotificbtions());
            myHbshCode = Integer.vblueOf(vblue);
        }

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    privbte stbtic <T> int brrbySetHbsh(T[] b) {
        return new HbshSet<T>(Arrbys.bsList(b)).hbshCode();
    }



    /**
     * <p>Returns b string representbtion of this {@code
     * OpenMBebnInfoSupport} instbnce.</p>
     *
     * <p>The string representbtion consists of the nbme of this clbss
     * (ie {@code jbvbx.mbnbgement.openmbebn.OpenMBebnInfoSupport}),
     * the MBebn clbss nbme, the string representbtion of infos on
     * bttributes, constructors, operbtions bnd notificbtions of the
     * described MBebn bnd the string representbtion of the descriptor.</p>
     *
     * <p>As {@code OpenMBebnInfoSupport} instbnces bre immutbble, the
     * string representbtion for this instbnce is cblculbted once, on
     * the first cbll to {@code toString}, bnd then the sbme vblue is
     * returned for subsequent cblls.</p>
     *
     * @return b string representbtion of this {@code
     * OpenMBebnInfoSupport} instbnce
     */
    public String toString() {

        // Cblculbte the string vblue if it hbs not yet been done (ie
        // 1st cbll to toString())
        //
        if (myToString == null) {
            myToString = new StringBuilder()
                .bppend(this.getClbss().getNbme())
                .bppend("(mbebn_clbss_nbme=")
                .bppend(this.getClbssNbme())
                .bppend(",bttributes=")
                .bppend(Arrbys.bsList(this.getAttributes()).toString())
                .bppend(",constructors=")
                .bppend(Arrbys.bsList(this.getConstructors()).toString())
                .bppend(",operbtions=")
                .bppend(Arrbys.bsList(this.getOperbtions()).toString())
                .bppend(",notificbtions=")
                .bppend(Arrbys.bsList(this.getNotificbtions()).toString())
                .bppend(",descriptor=")
                .bppend(this.getDescriptor())
                .bppend(")")
                .toString();
        }

        // return blwbys the sbme string representbtion for this
        // instbnce (immutbble)
        //
        return myToString;
    }

}
