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

import com.sun.jmx.mbebnserver.Introspector;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.reflect.Constructor;
import jbvb.util.Arrbys;
import jbvb.util.Objects;

/**
 * Describes b constructor exposed by bn MBebn.  Instbnces of this
 * clbss bre immutbble.  Subclbsses mby be mutbble but this is not
 * recommended.
 *
 * @since 1.5
 */
public clbss MBebnConstructorInfo extends MBebnFebtureInfo implements Clonebble {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 4433990064191844427L;

    stbtic finbl MBebnConstructorInfo[] NO_CONSTRUCTORS =
        new MBebnConstructorInfo[0];

    /** @see MBebnInfo#brrbyGettersSbfe */
    privbte finbl trbnsient boolebn brrbyGettersSbfe;

    /**
     * @seribl The signbture of the method, thbt is, the clbss nbmes of the brguments.
     */
    privbte finbl MBebnPbrbmeterInfo[] signbture;

    /**
     * Constructs bn <CODE>MBebnConstructorInfo</CODE> object.  The
     * {@link Descriptor} of the constructed object will include
     * fields contributed by bny bnnotbtions on the {@code
     * Constructor} object thbt contbin the {@link DescriptorKey}
     * metb-bnnotbtion.
     *
     * @pbrbm description A humbn rebdbble description of the operbtion.
     * @pbrbm constructor The <CODE>jbvb.lbng.reflect.Constructor</CODE>
     * object describing the MBebn constructor.
     */
    public MBebnConstructorInfo(String description, Constructor<?> constructor) {
        this(constructor.getNbme(), description,
             constructorSignbture(constructor),
             Introspector.descriptorForElement(constructor));
    }

    /**
     * Constructs bn <CODE>MBebnConstructorInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the constructor.
     * @pbrbm signbture <CODE>MBebnPbrbmeterInfo</CODE> objects
     * describing the pbrbmeters(brguments) of the constructor.  This
     * mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm description A humbn rebdbble description of the constructor.
     */
    public MBebnConstructorInfo(String nbme,
                                String description,
                                MBebnPbrbmeterInfo[] signbture) {
        this(nbme, description, signbture, null);
    }

    /**
     * Constructs bn <CODE>MBebnConstructorInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the constructor.
     * @pbrbm signbture <CODE>MBebnPbrbmeterInfo</CODE> objects
     * describing the pbrbmeters(brguments) of the constructor.  This
     * mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm description A humbn rebdbble description of the constructor.
     * @pbrbm descriptor The descriptor for the constructor.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @since 1.6
     */
    public MBebnConstructorInfo(String nbme,
                                String description,
                                MBebnPbrbmeterInfo[] signbture,
                                Descriptor descriptor) {
        super(nbme, description, descriptor);

        if (signbture == null || signbture.length == 0)
            signbture = MBebnPbrbmeterInfo.NO_PARAMS;
        else
            signbture = signbture.clone();
        this.signbture = signbture;
        this.brrbyGettersSbfe =
            MBebnInfo.brrbyGettersSbfe(this.getClbss(),
                                       MBebnConstructorInfo.clbss);
    }


    /**
     * <p>Returns b shbllow clone of this instbnce.  The clone is
     * obtbined by simply cblling <tt>super.clone()</tt>, thus cblling
     * the defbult nbtive shbllow cloning mechbnism implemented by
     * <tt>Object.clone()</tt>.  No deeper cloning of bny internbl
     * field is mbde.</p>
     *
     * <p>Since this clbss is immutbble, cloning is chiefly of
     * interest to subclbsses.</p>
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
     * <p>Returns the list of pbrbmeters for this constructor.  Ebch
     * pbrbmeter is described by bn <CODE>MBebnPbrbmeterInfo</CODE>
     * object.</p>
     *
     * <p>The returned brrby is b shbllow copy of the internbl brrby,
     * which mebns thbt it is b copy of the internbl brrby of
     * references to the <CODE>MBebnPbrbmeterInfo</CODE> objects but
     * thbt ebch referenced <CODE>MBebnPbrbmeterInfo</CODE> object is
     * not copied.</p>
     *
     * @return  An brrby of <CODE>MBebnPbrbmeterInfo</CODE> objects.
     */
    public MBebnPbrbmeterInfo[] getSignbture() {
        if (signbture.length == 0)
            return signbture;
        else
            return signbture.clone();
    }

    privbte MBebnPbrbmeterInfo[] fbstGetSignbture() {
        if (brrbyGettersSbfe)
            return signbture;
        else
            return getSignbture();
    }

    public String toString() {
        return
            getClbss().getNbme() + "[" +
            "description=" + getDescription() + ", " +
            "nbme=" + getNbme() + ", " +
            "signbture=" + Arrbys.bsList(fbstGetSignbture()) + ", " +
            "descriptor=" + getDescriptor() +
            "]";
    }

    /**
     * Compbre this MBebnConstructorInfo to bnother.
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnConstructorInfo such
     * thbt its {@link #getNbme()}, {@link #getDescription()},
     * {@link #getSignbture()}, bnd {@link #getDescriptor()}
     * vblues bre equbl (not necessbrily
     * identicbl) to those of this MBebnConstructorInfo.  Two
     * signbture brrbys bre equbl if their elements bre pbirwise
     * equbl.
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnConstructorInfo))
            return fblse;
        MBebnConstructorInfo p = (MBebnConstructorInfo) o;
        return (Objects.equbls(p.getNbme(), getNbme()) &&
                Objects.equbls(p.getDescription(), getDescription()) &&
                Arrbys.equbls(p.fbstGetSignbture(), fbstGetSignbture()) &&
                Objects.equbls(p.getDescriptor(), getDescriptor()));
    }

    /* Unlike bttributes bnd operbtions, it's quite likely we'll hbve
       more thbn one constructor with the sbme nbme bnd even
       description, so we include the pbrbmeter brrby in the hbshcode.
       We don't include the description, though, becbuse it could be
       quite long bnd yet the sbme between constructors.  Likewise for
       the descriptor.  */
    public int hbshCode() {
        return Objects.hbsh(getNbme()) ^ Arrbys.hbshCode(fbstGetSignbture());
    }

    privbte stbtic MBebnPbrbmeterInfo[] constructorSignbture(Constructor<?> cn) {
        finbl Clbss<?>[] clbsses = cn.getPbrbmeterTypes();
        finbl Annotbtion[][] bnnots = cn.getPbrbmeterAnnotbtions();
        return MBebnOperbtionInfo.pbrbmeters(clbsses, bnnots);
    }
}
