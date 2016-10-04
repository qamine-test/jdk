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
import jbvb.util.Set;
import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.DescriptorRebd;  // for Jbvbdoc
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.MBebnPbrbmeterInfo;

// OpenMBebnAttributeInfoSupport bnd this clbss bre very similbr
// but cbn't ebsily be refbctored becbuse there's no multiple inheritbnce.
// The best we cbn do for refbctoring is to put b bunch of stbtic methods
// in OpenMBebnAttributeInfoSupport bnd import them here.
import stbtic jbvbx.mbnbgement.openmbebn.OpenMBebnAttributeInfoSupport.*;

/**
 * Describes b pbrbmeter used in one or more operbtions or
 * constructors of bn open MBebn.
 *
 *
 * @since 1.5
 */
public clbss OpenMBebnPbrbmeterInfoSupport
    extends MBebnPbrbmeterInfo
    implements OpenMBebnPbrbmeterInfo {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = -7235016873758443122L;

    /**
     * @seribl The open mbebn pbrbmeter's <i>open type</i>
     */
    privbte OpenType<?>    openType;

    /**
     * @seribl The open mbebn pbrbmeter's defbult vblue
     */
    privbte Object      defbultVblue    = null;

    /**
     * @seribl The open mbebn pbrbmeter's legbl vblues. This {@link
     * Set} is unmodifibble
     */
    privbte Set<?> legblVblues     = null;  // to be constructed unmodifibble

    /**
     * @seribl The open mbebn pbrbmeter's min vblue
     */
    privbte Compbrbble<?> minVblue        = null;

    /**
     * @seribl The open mbebn pbrbmeter's mbx vblue
     */
    privbte Compbrbble<?> mbxVblue        = null;


    // As this instbnce is immutbble, these two vblues need only
    // be cblculbted once.
    privbte trbnsient Integer myHbshCode = null;        // As this instbnce is immutbble, these two vblues
    privbte trbnsient String  myToString = null;        // need only be cblculbted once.


    /**
     * Constructs bn {@code OpenMBebnPbrbmeterInfoSupport} instbnce,
     * which describes the pbrbmeter used in one or more operbtions or
     * constructors of b clbss of open MBebns, with the specified
     * {@code nbme}, {@code openType} bnd {@code description}.
     *
     * @pbrbm nbme  cbnnot be b null or empty string.
     *
     * @pbrbm description  cbnnot be b null or empty string.
     *
     * @pbrbm openType  cbnnot be null.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code openType} is
     * null.
     */
    public OpenMBebnPbrbmeterInfoSupport(String nbme,
                                         String description,
                                         OpenType<?> openType) {
        this(nbme, description, openType, (Descriptor) null);
    }

    /**
     * Constructs bn {@code OpenMBebnPbrbmeterInfoSupport} instbnce,
     * which describes the pbrbmeter used in one or more operbtions or
     * constructors of b clbss of open MBebns, with the specified
     * {@code nbme}, {@code openType}, {@code description},
     * bnd {@code descriptor}.
     *
     * <p>The {@code descriptor} cbn contbin entries thbt will define
     * the vblues returned by certbin methods of this clbss, bs
     * explbined in the <b href="pbckbge-summbry.html#constrbints">
     * pbckbge description</b>.
     *
     * @pbrbm nbme  cbnnot be b null or empty string.
     *
     * @pbrbm description  cbnnot be b null or empty string.
     *
     * @pbrbm openType  cbnnot be null.
     *
     * @pbrbm descriptor The descriptor for the pbrbmeter.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code openType} is
     * null, or the descriptor entries bre invblid bs described in the
     * <b href="pbckbge-summbry.html#constrbints">pbckbge
     * description</b>.
     *
     * @since 1.6
     */
    public OpenMBebnPbrbmeterInfoSupport(String nbme,
                                         String description,
                                         OpenType<?> openType,
                                         Descriptor descriptor) {


        // Construct pbrent's stbte
        //
        super(nbme,
              (openType==null) ? null : openType.getClbssNbme(),
              description,
              ImmutbbleDescriptor.union(descriptor,(openType==null)?null:
                openType.getDescriptor()));

        // Initiblize this instbnce's specific stbte
        //
        this.openType = openType;

        descriptor = getDescriptor();  // replbce null by empty
        this.defbultVblue = vblueFrom(descriptor, "defbultVblue", openType);
        this.legblVblues = vbluesFrom(descriptor, "legblVblues", openType);
        this.minVblue = compbrbbleVblueFrom(descriptor, "minVblue", openType);
        this.mbxVblue = compbrbbleVblueFrom(descriptor, "mbxVblue", openType);

        try {
            check(this);
        } cbtch (OpenDbtbException e) {
            throw new IllegblArgumentException(e.getMessbge(), e);
        }
    }


    /**
     * Constructs bn {@code OpenMBebnPbrbmeterInfoSupport} instbnce,
     * which describes the pbrbmeter used in one or more operbtions or
     * constructors of b clbss of open MBebns, with the specified
     * {@code nbme}, {@code openType}, {@code description} bnd {@code
     * defbultVblue}.
     *
     * @pbrbm nbme  cbnnot be b null or empty string.
     *
     * @pbrbm description  cbnnot be b null or empty string.
     *
     * @pbrbm openType  cbnnot be null.
     *
     * @pbrbm defbultVblue must be b vblid vblue for the {@code
     * openType} specified for this pbrbmeter; defbult vblue not
     * supported for {@code ArrbyType} bnd {@code TbbulbrType}; cbn be
     * null, in which cbse it mebns thbt no defbult vblue is set.
     *
     * @pbrbm <T> bllows the compiler to check thbt the {@code defbultVblue},
     * if non-null, hbs the correct Jbvb type for the given {@code openType}.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code openType} is
     * null.
     *
     * @throws OpenDbtbException if {@code defbultVblue} is not b
     * vblid vblue for the specified {@code openType}, or {@code
     * defbultVblue} is non null bnd {@code openType} is bn {@code
     * ArrbyType} or b {@code TbbulbrType}.
     */
    public <T> OpenMBebnPbrbmeterInfoSupport(String   nbme,
                                             String   description,
                                             OpenType<T> openType,
                                             T        defbultVblue)
            throws OpenDbtbException {
        this(nbme, description, openType, defbultVblue, (T[]) null);
    }

    /**
     * <p>Constructs bn {@code OpenMBebnPbrbmeterInfoSupport} instbnce,
     * which describes the pbrbmeter used in one or more operbtions or
     * constructors of b clbss of open MBebns, with the specified
     * {@code nbme}, {@code openType}, {@code description}, {@code
     * defbultVblue} bnd {@code legblVblues}.</p>
     *
     * <p>The contents of {@code legblVblues} bre copied, so subsequent
     * modificbtions of the brrby referenced by {@code legblVblues}
     * hbve no impbct on this {@code OpenMBebnPbrbmeterInfoSupport}
     * instbnce.</p>
     *
     * @pbrbm nbme  cbnnot be b null or empty string.
     *
     * @pbrbm description  cbnnot be b null or empty string.
     *
     * @pbrbm openType  cbnnot be null.
     *
     * @pbrbm defbultVblue must be b vblid vblue for the {@code
     * openType} specified for this pbrbmeter; defbult vblue not
     * supported for {@code ArrbyType} bnd {@code TbbulbrType}; cbn be
     * null, in which cbse it mebns thbt no defbult vblue is set.
     *
     * @pbrbm legblVblues ebch contbined vblue must be vblid for the
     * {@code openType} specified for this pbrbmeter; legbl vblues not
     * supported for {@code ArrbyType} bnd {@code TbbulbrType}; cbn be
     * null or empty.
     *
     * @pbrbm <T> bllows the compiler to check thbt the {@code
     * defbultVblue} bnd {@code legblVblues}, if non-null, hbve the
     * correct Jbvb type for the given {@code openType}.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code openType} is
     * null.
     *
     * @throws OpenDbtbException if {@code defbultVblue} is not b
     * vblid vblue for the specified {@code openType}, or one vblue in
     * {@code legblVblues} is not vblid for the specified {@code
     * openType}, or {@code defbultVblue} is non null bnd {@code
     * openType} is bn {@code ArrbyType} or b {@code TbbulbrType}, or
     * {@code legblVblues} is non null bnd non empty bnd {@code
     * openType} is bn {@code ArrbyType} or b {@code TbbulbrType}, or
     * {@code legblVblues} is non null bnd non empty bnd {@code
     * defbultVblue} is not contbined in {@code legblVblues}.
     */
    public <T> OpenMBebnPbrbmeterInfoSupport(String   nbme,
                                             String   description,
                                             OpenType<T> openType,
                                             T        defbultVblue,
                                             T[]      legblVblues)
            throws OpenDbtbException {
        this(nbme, description, openType,
             defbultVblue, legblVblues, null, null);
    }


    /**
     * Constructs bn {@code OpenMBebnPbrbmeterInfoSupport} instbnce,
     * which describes the pbrbmeter used in one or more operbtions or
     * constructors of b clbss of open MBebns, with the specified
     * {@code nbme}, {@code openType}, {@code description}, {@code
     * defbultVblue}, {@code minVblue} bnd {@code mbxVblue}.
     *
     * It is possible to specify minimbl bnd mbximbl vblues only for
     * bn open type whose vblues bre {@code Compbrbble}.
     *
     * @pbrbm nbme  cbnnot be b null or empty string.
     *
     * @pbrbm description  cbnnot be b null or empty string.
     *
     * @pbrbm openType  cbnnot be null.
     *
     * @pbrbm defbultVblue must be b vblid vblue for the {@code
     * openType} specified for this pbrbmeter; defbult vblue not
     * supported for {@code ArrbyType} bnd {@code TbbulbrType}; cbn be
     * null, in which cbse it mebns thbt no defbult vblue is set.
     *
     * @pbrbm minVblue must be vblid for the {@code openType}
     * specified for this pbrbmeter; cbn be null, in which cbse it
     * mebns thbt no minimbl vblue is set.
     *
     * @pbrbm mbxVblue must be vblid for the {@code openType}
     * specified for this pbrbmeter; cbn be null, in which cbse it
     * mebns thbt no mbximbl vblue is set.
     *
     * @pbrbm <T> bllows the compiler to check thbt the {@code
     * defbultVblue}, {@code minVblue}, bnd {@code mbxVblue}, if
     * non-null, hbve the correct Jbvb type for the given {@code
     * openType}.
     *
     * @throws IllegblArgumentException if {@code nbme} or {@code
     * description} bre null or empty string, or {@code openType} is
     * null.
     *
     * @throws OpenDbtbException if {@code defbultVblue}, {@code
     * minVblue} or {@code mbxVblue} is not b vblid vblue for the
     * specified {@code openType}, or {@code defbultVblue} is non null
     * bnd {@code openType} is bn {@code ArrbyType} or b {@code
     * TbbulbrType}, or both {@code minVblue} bnd {@code mbxVblue} bre
     * non-null bnd {@code minVblue.compbreTo(mbxVblue) > 0} is {@code
     * true}, or both {@code defbultVblue} bnd {@code minVblue} bre
     * non-null bnd {@code minVblue.compbreTo(defbultVblue) > 0} is
     * {@code true}, or both {@code defbultVblue} bnd {@code mbxVblue}
     * bre non-null bnd {@code defbultVblue.compbreTo(mbxVblue) > 0}
     * is {@code true}.
     */
    public <T> OpenMBebnPbrbmeterInfoSupport(String     nbme,
                                             String     description,
                                             OpenType<T>   openType,
                                             T          defbultVblue,
                                             Compbrbble<T> minVblue,
                                             Compbrbble<T> mbxVblue)
            throws OpenDbtbException {
        this(nbme, description, openType,
             defbultVblue, null, minVblue, mbxVblue);
    }

    privbte <T> OpenMBebnPbrbmeterInfoSupport(String nbme,
                                              String description,
                                              OpenType<T> openType,
                                              T defbultVblue,
                                              T[] legblVblues,
                                              Compbrbble<T> minVblue,
                                              Compbrbble<T> mbxVblue)
            throws OpenDbtbException {
        super(nbme,
              (openType == null) ? null : openType.getClbssNbme(),
              description,
              mbkeDescriptor(openType,
                             defbultVblue, legblVblues, minVblue, mbxVblue));

        this.openType = openType;

        Descriptor d = getDescriptor();
        this.defbultVblue = defbultVblue;
        this.minVblue = minVblue;
        this.mbxVblue = mbxVblue;
        // We blrebdy converted the brrby into bn unmodifibble Set
        // in the descriptor.
        this.legblVblues = (Set<?>) d.getFieldVblue("legblVblues");

        check(this);
    }

    /**
     * An object seriblized in b version of the API before Descriptors were
     * bdded to this clbss will hbve bn empty or null Descriptor.
     * For consistency with our
     * behbvior in this version, we must replbce the object with one
     * where the Descriptors reflect the sbme vblues of openType, defbultVblue,
     * etc.
     **/
    privbte Object rebdResolve() {
        if (getDescriptor().getFieldNbmes().length == 0) {
            // This noise bllows us to bvoid "unchecked" wbrnings without
            // hbving to suppress them explicitly.
            OpenType<Object> xopenType = cbst(openType);
            Set<Object> xlegblVblues = cbst(legblVblues);
            Compbrbble<Object> xminVblue = cbst(minVblue);
            Compbrbble<Object> xmbxVblue = cbst(mbxVblue);
            return new OpenMBebnPbrbmeterInfoSupport(
                    nbme, description, openType,
                    mbkeDescriptor(xopenType, defbultVblue, xlegblVblues,
                                   xminVblue, xmbxVblue));
        } else
            return this;
    }

    /**
     * Returns the open type for the vblues of the pbrbmeter described
     * by this {@code OpenMBebnPbrbmeterInfoSupport} instbnce.
     */
    public OpenType<?> getOpenType() {
        return openType;
    }

    /**
     * Returns the defbult vblue for the pbrbmeter described by this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce, if specified,
     * or {@code null} otherwise.
     */
    public Object getDefbultVblue() {

        // Specibl cbse for ArrbyType bnd TbbulbrType
        // [JF] TODO: clone it so thbt it cbnnot be bltered,
        // [JF] TODO: if we decide to support defbultVblue bs bn brrby itself.
        // [JF] As of todby (oct 2000) it is not supported so
        // defbultVblue is null for brrbys. Nothing to do.

        return defbultVblue;
    }

    /**
     * Returns bn unmodifibble Set of legbl vblues for the pbrbmeter
     * described by this {@code OpenMBebnPbrbmeterInfoSupport}
     * instbnce, if specified, or {@code null} otherwise.
     */
    public Set<?> getLegblVblues() {

        // Specibl cbse for ArrbyType bnd TbbulbrType
        // [JF] TODO: clone vblues so thbt they cbnnot be bltered,
        // [JF] TODO: if we decide to support LegblVblues bs bn brrby itself.
        // [JF] As of todby (oct 2000) it is not supported so
        // legblVblues is null for brrbys. Nothing to do.

        // Returns our legblVblues Set (set wbs constructed unmodifibble)
        return (legblVblues);
    }

    /**
     * Returns the minimbl vblue for the pbrbmeter described by this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce, if specified,
     * or {@code null} otherwise.
     */
    public Compbrbble<?> getMinVblue() {

        // Note: only compbrbble vblues hbve b minVblue, so thbt's not
        // the cbse of brrbys bnd tbbulbrs (blwbys null).

        return minVblue;
    }

    /**
     * Returns the mbximbl vblue for the pbrbmeter described by this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce, if specified,
     * or {@code null} otherwise.
     */
    public Compbrbble<?> getMbxVblue() {

        // Note: only compbrbble vblues hbve b mbxVblue, so thbt's not
        // the cbse of brrbys bnd tbbulbrs (blwbys null).

        return mbxVblue;
    }

    /**
     * Returns {@code true} if this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce specifies b non-null
     * defbult vblue for the described pbrbmeter, {@code fblse}
     * otherwise.
     */
    public boolebn hbsDefbultVblue() {

        return (defbultVblue != null);
    }

    /**
     * Returns {@code true} if this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce specifies b non-null
     * set of legbl vblues for the described pbrbmeter, {@code fblse}
     * otherwise.
     */
    public boolebn hbsLegblVblues() {

        return (legblVblues != null);
    }

    /**
     * Returns {@code true} if this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce specifies b non-null
     * minimbl vblue for the described pbrbmeter, {@code fblse}
     * otherwise.
     */
    public boolebn hbsMinVblue() {

        return (minVblue != null);
    }

    /**
     * Returns {@code true} if this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce specifies b non-null
     * mbximbl vblue for the described pbrbmeter, {@code fblse}
     * otherwise.
     */
    public boolebn hbsMbxVblue() {

        return (mbxVblue != null);
    }


    /**
     * Tests whether {@code obj} is b vblid vblue for the pbrbmeter
     * described by this {@code OpenMBebnPbrbmeterInfo} instbnce.
     *
     * @pbrbm obj the object to be tested.
     *
     * @return {@code true} if {@code obj} is b vblid vblue
     * for the pbrbmeter described by this
     * {@code OpenMBebnPbrbmeterInfo} instbnce,
     * {@code fblse} otherwise.
     */
    public boolebn isVblue(Object obj) {
        return OpenMBebnAttributeInfoSupport.isVblue(this, obj);
        // compiler bug? should be bble to omit clbss nbme here
        // blso below in toString bnd hbshCode
    }


    /* ***  Commodity methods from jbvb.lbng.Object  *** */


    /**
     * <p>Compbres the specified {@code obj} pbrbmeter with this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce for equblity.</p>
     *
     * <p>Returns {@code true} if bnd only if bll of the following
     * stbtements bre true:
     *
     * <ul>
     * <li>{@code obj} is non null,</li>
     * <li>{@code obj} blso implements the {@code OpenMBebnPbrbmeterInfo}
     * interfbce,</li>
     * <li>their nbmes bre equbl</li>
     * <li>their open types bre equbl</li>
     * <li>their defbult, min, mbx bnd legbl vblues bre equbl.</li>
     * </ul>
     * This ensures thbt this {@code equbls} method works properly for
     * {@code obj} pbrbmeters which bre different implementbtions of
     * the {@code OpenMBebnPbrbmeterInfo} interfbce.
     *
     * <p>If {@code obj} blso implements {@link DescriptorRebd}, then its
     * {@link DescriptorRebd#getDescriptor() getDescriptor()} method must
     * blso return the sbme vblue bs for this object.</p>
     *
     * @pbrbm obj the object to be compbred for equblity with this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce.
     *
     * @return {@code true} if the specified object is equbl to this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce.
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof OpenMBebnPbrbmeterInfo))
            return fblse;

        OpenMBebnPbrbmeterInfo other = (OpenMBebnPbrbmeterInfo) obj;

        return equbl(this, other);
    }

    /**
     * <p>Returns the hbsh code vblue for this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce.</p>
     *
     * <p>The hbsh code of bn {@code OpenMBebnPbrbmeterInfoSupport}
     * instbnce is the sum of the hbsh codes of bll elements of
     * informbtion used in {@code equbls} compbrisons (ie: its nbme,
     * its <i>open type</i>, its defbult, min, mbx bnd legbl
     * vblues, bnd its Descriptor).
     *
     * <p>This ensures thbt {@code t1.equbls(t2)} implies thbt {@code
     * t1.hbshCode()==t2.hbshCode()} for bny two {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnces {@code t1} bnd {@code
     * t2}, bs required by the generbl contrbct of the method {@link
     * Object#hbshCode() Object.hbshCode()}.
     *
     * <p>However, note thbt bnother instbnce of b clbss implementing
     * the {@code OpenMBebnPbrbmeterInfo} interfbce mby be equbl to
     * this {@code OpenMBebnPbrbmeterInfoSupport} instbnce bs defined
     * by {@link #equbls(jbvb.lbng.Object)}, but mby hbve b different
     * hbsh code if it is cblculbted differently.
     *
     * <p>As {@code OpenMBebnPbrbmeterInfoSupport} instbnces bre
     * immutbble, the hbsh code for this instbnce is cblculbted once,
     * on the first cbll to {@code hbshCode}, bnd then the sbme vblue
     * is returned for subsequent cblls.
     *
     * @return the hbsh code vblue for this {@code
     * OpenMBebnPbrbmeterInfoSupport} instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done
        // (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null)
            myHbshCode = OpenMBebnAttributeInfoSupport.hbshCode(this);

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    /**
     * Returns b string representbtion of this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (i.e.
     * {@code jbvbx.mbnbgement.openmbebn.OpenMBebnPbrbmeterInfoSupport}),
     * the string representbtion of the nbme bnd open type of the described
     * pbrbmeter, the string representbtion of its defbult, min, mbx bnd legbl
     * vblues bnd the string representbtion of its descriptor.
     * <p>
     * As {@code OpenMBebnPbrbmeterInfoSupport} instbnces bre immutbble,
     * the string representbtion for this instbnce is cblculbted once,
     * on the first cbll to {@code toString}, bnd then the sbme vblue
     * is returned for subsequent cblls.
     *
     * @return b string representbtion of this
     * {@code OpenMBebnPbrbmeterInfoSupport} instbnce.
     */
    public String toString() {

        // Cblculbte the string vblue if it hbs not yet been done (ie
        // 1st cbll to toString())
        //
        if (myToString == null)
            myToString = OpenMBebnAttributeInfoSupport.toString(this);

        // return blwbys the sbme string representbtion for this
        // instbnce (immutbble)
        //
        return myToString;
    }

}
