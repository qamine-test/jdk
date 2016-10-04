/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.MBebnPbrbmeterInfo;


/**
 * Describes bn operbtion of bn Open MBebn.
 *
 *
 * @since 1.5
 */
public clbss OpenMBebnOperbtionInfoSupport
    extends MBebnOperbtionInfo
    implements OpenMBebnOperbtionInfo {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 4996859732565369366L;

    /**
     * @seribl The <i>open type</i> of the vblues returned by the operbtion
     *         described by this {@link OpenMBebnOperbtionInfo} instbnce
     *
     */
    privbte OpenType<?> returnOpenType;


    // As this instbnce is immutbble,
    // these two vblues need only be cblculbted once.
    privbte trbnsient Integer myHbshCode = null;
    privbte trbnsient String  myToString = null;


    /**
     * <p>Constructs bn {@code OpenMBebnOperbtionInfoSupport}
     * instbnce, which describes the operbtion of b clbss of open
     * MBebns, with the specified {@code nbme}, {@code description},
     * {@code signbture}, {@code returnOpenType} bnd {@code
     * impbct}.</p>
     *
     * <p>The {@code signbture} brrby pbrbmeter is internblly copied,
     * so thbt subsequent chbnges to the brrby referenced by {@code
     * signbture} hbve no effect on this instbnce.</p>
     *
     * @pbrbm nbme cbnnot be b null or empty string.
     *
     * @pbrbm description cbnnot be b null or empty string.
     *
     * @pbrbm signbture cbn be null or empty if there bre no
     * pbrbmeters to describe.
     *
     * @pbrbm returnOpenType cbnnot be null: use {@code
     * SimpleType.VOID} for operbtions thbt return nothing.
     *
     * @pbrbm impbct must be one of {@code ACTION}, {@code
     * ACTION_INFO}, {@code INFO}, or {@code UNKNOWN}.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code
     * returnOpenType} is null, or {@code impbct} is not one of {@code
     * ACTION}, {@code ACTION_INFO}, {@code INFO}, or {@code UNKNOWN}.
     *
     * @throws ArrbyStoreException If {@code signbture} is not bn
     * brrby of instbnces of b subclbss of {@code MBebnPbrbmeterInfo}.
     */
    public OpenMBebnOperbtionInfoSupport(String nbme,
                                         String description,
                                         OpenMBebnPbrbmeterInfo[] signbture,
                                         OpenType<?> returnOpenType,
                                         int impbct) {
        this(nbme, description, signbture, returnOpenType, impbct,
             (Descriptor) null);
    }

    /**
     * <p>Constructs bn {@code OpenMBebnOperbtionInfoSupport}
     * instbnce, which describes the operbtion of b clbss of open
     * MBebns, with the specified {@code nbme}, {@code description},
     * {@code signbture}, {@code returnOpenType}, {@code
     * impbct}, bnd {@code descriptor}.</p>
     *
     * <p>The {@code signbture} brrby pbrbmeter is internblly copied,
     * so thbt subsequent chbnges to the brrby referenced by {@code
     * signbture} hbve no effect on this instbnce.</p>
     *
     * @pbrbm nbme cbnnot be b null or empty string.
     *
     * @pbrbm description cbnnot be b null or empty string.
     *
     * @pbrbm signbture cbn be null or empty if there bre no
     * pbrbmeters to describe.
     *
     * @pbrbm returnOpenType cbnnot be null: use {@code
     * SimpleType.VOID} for operbtions thbt return nothing.
     *
     * @pbrbm impbct must be one of {@code ACTION}, {@code
     * ACTION_INFO}, {@code INFO}, or {@code UNKNOWN}.
     *
     * @pbrbm descriptor The descriptor for the operbtion.  This mby
     * be null, which is equivblent to bn empty descriptor.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code
     * returnOpenType} is null, or {@code impbct} is not one of {@code
     * ACTION}, {@code ACTION_INFO}, {@code INFO}, or {@code UNKNOWN}.
     *
     * @throws ArrbyStoreException If {@code signbture} is not bn
     * brrby of instbnces of b subclbss of {@code MBebnPbrbmeterInfo}.
     *
     * @since 1.6
     */
    public OpenMBebnOperbtionInfoSupport(String nbme,
                                         String description,
                                         OpenMBebnPbrbmeterInfo[] signbture,
                                         OpenType<?> returnOpenType,
                                         int impbct,
                                         Descriptor descriptor) {
        super(nbme,
              description,
              brrbyCopyCbst(signbture),
              // must prevent NPE here - we will throw IAE lbter on if
              // returnOpenType is null
              (returnOpenType == null) ? null : returnOpenType.getClbssNbme(),
              impbct,
              ImmutbbleDescriptor.union(descriptor,
                // must prevent NPE here - we will throw IAE lbter on if
                // returnOpenType is null
                (returnOpenType==null) ? null :returnOpenType.getDescriptor()));

        // check pbrbmeters thbt should not be null or empty
        // (unfortunbtely it is not done in superclbss :-( ! )
        //
        if (nbme == null || nbme.trim().equbls("")) {
            throw new IllegblArgumentException("Argument nbme cbnnot " +
                                               "be null or empty");
        }
        if (description == null || description.trim().equbls("")) {
            throw new IllegblArgumentException("Argument description cbnnot " +
                                               "be null or empty");
        }
        if (returnOpenType == null) {
            throw new IllegblArgumentException("Argument returnOpenType " +
                                               "cbnnot be null");
        }

        if (impbct != ACTION && impbct != ACTION_INFO && impbct != INFO &&
                impbct != UNKNOWN) {
            throw new IllegblArgumentException("Argument impbct cbn only be " +
                                               "one of ACTION, ACTION_INFO, " +
                                               "INFO, or UNKNOWN: " + impbct);
        }

        this.returnOpenType = returnOpenType;
    }


    // Converts bn brrby of OpenMBebnPbrbmeterInfo objects extending
    // MBebnPbrbmeterInfo into bn brrby of MBebnPbrbmeterInfo.
    //
    privbte stbtic MBebnPbrbmeterInfo[]
            brrbyCopyCbst(OpenMBebnPbrbmeterInfo[] src) {
        if (src == null)
            return null;

        MBebnPbrbmeterInfo[] dst = new MBebnPbrbmeterInfo[src.length];
        System.brrbycopy(src, 0, dst, 0, src.length);
        // mby throw bn ArrbyStoreException
        return dst;
    }

    // Converts bn brrby of MBebnPbrbmeterInfo objects implementing
    // OpenMBebnPbrbmeterInfo into bn brrby of OpenMBebnPbrbmeterInfo.
    //
    privbte stbtic OpenMBebnPbrbmeterInfo[]
            brrbyCopyCbst(MBebnPbrbmeterInfo[] src) {
        if (src == null)
            return null;

        OpenMBebnPbrbmeterInfo[] dst = new OpenMBebnPbrbmeterInfo[src.length];
        System.brrbycopy(src, 0, dst, 0, src.length);
        // mby throw bn ArrbyStoreException
        return dst;
    }


    // [JF]: should we bdd constructor with jbvb.lbng.reflect.Method
    // method pbrbmeter ?  would need to bdd consistency check between
    // OpenType<?> returnOpenType bnd method.getReturnType().


    /**
     * Returns the <i>open type</i> of the vblues returned by the
     * operbtion described by this {@code OpenMBebnOperbtionInfo}
     * instbnce.
     */
    public OpenType<?> getReturnOpenType() {

        return returnOpenType;
    }



    /* ***  Commodity methods from jbvb.lbng.Object  *** */


    /**
     * <p>Compbres the specified {@code obj} pbrbmeter with this
     * {@code OpenMBebnOperbtionInfoSupport} instbnce for
     * equblity.</p>
     *
     * <p>Returns {@code true} if bnd only if bll of the following
     * stbtements bre true:
     *
     * <ul>
     * <li>{@code obj} is non null,</li>
     * <li>{@code obj} blso implements the {@code
     * OpenMBebnOperbtionInfo} interfbce,</li>
     * <li>their nbmes bre equbl</li>
     * <li>their signbtures bre equbl</li>
     * <li>their return open types bre equbl</li>
     * <li>their impbcts bre equbl</li>
     * </ul>
     *
     * This ensures thbt this {@code equbls} method works properly for
     * {@code obj} pbrbmeters which bre different implementbtions of
     * the {@code OpenMBebnOperbtionInfo} interfbce.
     *
     * @pbrbm obj the object to be compbred for equblity with this
     * {@code OpenMBebnOperbtionInfoSupport} instbnce;
     *
     * @return {@code true} if the specified object is equbl to this
     * {@code OpenMBebnOperbtionInfoSupport} instbnce.
     */
    public boolebn equbls(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // if obj is not b OpenMBebnOperbtionInfo, return fblse
        //
        OpenMBebnOperbtionInfo other;
        try {
            other = (OpenMBebnOperbtionInfo) obj;
        } cbtch (ClbssCbstException e) {
            return fblse;
        }

        // Now, reblly test for equblity between this
        // OpenMBebnOperbtionInfo implementbtion bnd the other:
        //

        // their Nbme should be equbl
        if ( ! this.getNbme().equbls(other.getNbme()) ) {
            return fblse;
        }

        // their Signbtures should be equbl
        if ( ! Arrbys.equbls(this.getSignbture(), other.getSignbture()) ) {
            return fblse;
        }

        // their return open types should be equbl
        if ( ! this.getReturnOpenType().equbls(other.getReturnOpenType()) ) {
            return fblse;
        }

        // their impbcts should be equbl
        if ( this.getImpbct() != other.getImpbct() ) {
            return fblse;
        }

        // All tests for equblity were successfull
        //
        return true;
    }

    /**
     * <p>Returns the hbsh code vblue for this {@code
     * OpenMBebnOperbtionInfoSupport} instbnce.</p>
     *
     * <p>The hbsh code of bn {@code OpenMBebnOperbtionInfoSupport}
     * instbnce is the sum of the hbsh codes of bll elements of
     * informbtion used in {@code equbls} compbrisons (ie: its nbme,
     * return open type, impbct bnd signbture, where the signbture
     * hbshCode is cblculbted by b cbll to {@code
     * jbvb.util.Arrbys.bsList(this.getSignbture).hbshCode()}).</p>
     *
     * <p>This ensures thbt {@code t1.equbls(t2) } implies thbt {@code
     * t1.hbshCode()==t2.hbshCode() } for bny two {@code
     * OpenMBebnOperbtionInfoSupport} instbnces {@code t1} bnd {@code
     * t2}, bs required by the generbl contrbct of the method {@link
     * Object#hbshCode() Object.hbshCode()}.</p>
     *
     * <p>However, note thbt bnother instbnce of b clbss implementing
     * the {@code OpenMBebnOperbtionInfo} interfbce mby be equbl to
     * this {@code OpenMBebnOperbtionInfoSupport} instbnce bs defined
     * by {@link #equbls(jbvb.lbng.Object)}, but mby hbve b different
     * hbsh code if it is cblculbted differently.</p>
     *
     * <p>As {@code OpenMBebnOperbtionInfoSupport} instbnces bre
     * immutbble, the hbsh code for this instbnce is cblculbted once,
     * on the first cbll to {@code hbshCode}, bnd then the sbme vblue
     * is returned for subsequent cblls.</p>
     *
     * @return the hbsh code vblue for this {@code
     * OpenMBebnOperbtionInfoSupport} instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done
        // (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null) {
            int vblue = 0;
            vblue += this.getNbme().hbshCode();
            vblue += Arrbys.bsList(this.getSignbture()).hbshCode();
            vblue += this.getReturnOpenType().hbshCode();
            vblue += this.getImpbct();
            myHbshCode = Integer.vblueOf(vblue);
        }

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    /**
     * <p>Returns b string representbtion of this {@code
     * OpenMBebnOperbtionInfoSupport} instbnce.</p>
     *
     * <p>The string representbtion consists of the nbme of this clbss
     * (ie {@code
     * jbvbx.mbnbgement.openmbebn.OpenMBebnOperbtionInfoSupport}), bnd
     * the nbme, signbture, return open type bnd impbct of the
     * described operbtion bnd the string representbtion of its descriptor.</p>
     *
     * <p>As {@code OpenMBebnOperbtionInfoSupport} instbnces bre
     * immutbble, the string representbtion for this instbnce is
     * cblculbted once, on the first cbll to {@code toString}, bnd
     * then the sbme vblue is returned for subsequent cblls.</p>
     *
     * @return b string representbtion of this {@code
     * OpenMBebnOperbtionInfoSupport} instbnce
     */
    public String toString() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done
        // (ie 1st cbll to toString())
        //
        if (myToString == null) {
            myToString = new StringBuilder()
                .bppend(this.getClbss().getNbme())
                .bppend("(nbme=")
                .bppend(this.getNbme())
                .bppend(",signbture=")
                .bppend(Arrbys.bsList(this.getSignbture()).toString())
                .bppend(",return=")
                .bppend(this.getReturnOpenType().toString())
                .bppend(",impbct=")
                .bppend(this.getImpbct())
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

    /**
     * An object seriblized in b version of the API before Descriptors were
     * bdded to this clbss will hbve bn empty or null Descriptor.
     * For consistency with our
     * behbvior in this version, we must replbce the object with one
     * where the Descriptors reflect the sbme vblue of returned openType.
     **/
    privbte Object rebdResolve() {
        if (getDescriptor().getFieldNbmes().length == 0) {
            // This constructor will construct the expected defbult Descriptor.
            //
            return new OpenMBebnOperbtionInfoSupport(
                    nbme, description, brrbyCopyCbst(getSignbture()),
                    returnOpenType, getImpbct());
        } else
            return this;
    }

}
