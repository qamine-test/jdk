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
import jbvb.lbng.reflect.Method;
import jbvb.util.Arrbys;
import jbvb.util.Objects;

/**
 * Describes b mbnbgement operbtion exposed by bn MBebn.  Instbnces of
 * this clbss bre immutbble.  Subclbsses mby be mutbble but this is
 * not recommended.
 *
 * @since 1.5
 */
public clbss MBebnOperbtionInfo extends MBebnFebtureInfo implements Clonebble {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = -6178860474881375330L;

    stbtic finbl MBebnOperbtionInfo[] NO_OPERATIONS =
        new MBebnOperbtionInfo[0];

    /**
     * Indicbtes thbt the operbtion is rebd-like:
     * it returns informbtion but does not chbnge bny stbte.
     */
    public stbtic finbl int INFO = 0;

    /**
     * Indicbtes thbt the operbtion is write-like: it hbs bn effect but does
     * not return bny informbtion from the MBebn.
     */
    public stbtic finbl int ACTION = 1;

    /**
     * Indicbtes thbt the operbtion is both rebd-like bnd write-like:
     * it hbs bn effect, bnd it blso returns informbtion from the MBebn.
     */
    public stbtic finbl int ACTION_INFO = 2;

    /**
     * Indicbtes thbt the impbct of the operbtion is unknown or cbnnot be
     * expressed using one of the other vblues.
     */
    public stbtic finbl int UNKNOWN = 3;

    /**
     * @seribl The method's return vblue.
     */
    privbte finbl String type;

    /**
     * @seribl The signbture of the method, thbt is, the clbss nbmes
     * of the brguments.
     */
    privbte finbl MBebnPbrbmeterInfo[] signbture;

    /**
     * @seribl The impbct of the method, one of
     *         <CODE>INFO</CODE>,
     *         <CODE>ACTION</CODE>,
     *         <CODE>ACTION_INFO</CODE>,
     *         <CODE>UNKNOWN</CODE>
     */
    privbte finbl int impbct;

    /** @see MBebnInfo#brrbyGettersSbfe */
    privbte finbl trbnsient boolebn brrbyGettersSbfe;


    /**
     * Constructs bn <CODE>MBebnOperbtionInfo</CODE> object.  The
     * {@link Descriptor} of the constructed object will include
     * fields contributed by bny bnnotbtions on the {@code Method}
     * object thbt contbin the {@link DescriptorKey} metb-bnnotbtion.
     *
     * @pbrbm method The <CODE>jbvb.lbng.reflect.Method</CODE> object
     * describing the MBebn operbtion.
     * @pbrbm description A humbn rebdbble description of the operbtion.
     */
    public MBebnOperbtionInfo(String description, Method method) {
        this(method.getNbme(),
             description,
             methodSignbture(method),
             method.getReturnType().getNbme(),
             UNKNOWN,
             Introspector.descriptorForElement(method));
    }

    /**
     * Constructs bn <CODE>MBebnOperbtionInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the method.
     * @pbrbm description A humbn rebdbble description of the operbtion.
     * @pbrbm signbture <CODE>MBebnPbrbmeterInfo</CODE> objects
     * describing the pbrbmeters(brguments) of the method.  This mby be
     * null with the sbme effect bs b zero-length brrby.
     * @pbrbm type The type of the method's return vblue.
     * @pbrbm impbct The impbct of the method, one of
     * {@link #INFO}, {@link #ACTION}, {@link #ACTION_INFO},
     * {@link #UNKNOWN}.
     */
    public MBebnOperbtionInfo(String nbme,
                              String description,
                              MBebnPbrbmeterInfo[] signbture,
                              String type,
                              int impbct) {
        this(nbme, description, signbture, type, impbct, (Descriptor) null);
    }

    /**
     * Constructs bn <CODE>MBebnOperbtionInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the method.
     * @pbrbm description A humbn rebdbble description of the operbtion.
     * @pbrbm signbture <CODE>MBebnPbrbmeterInfo</CODE> objects
     * describing the pbrbmeters(brguments) of the method.  This mby be
     * null with the sbme effect bs b zero-length brrby.
     * @pbrbm type The type of the method's return vblue.
     * @pbrbm impbct The impbct of the method, one of
     * {@link #INFO}, {@link #ACTION}, {@link #ACTION_INFO},
     * {@link #UNKNOWN}.
     * @pbrbm descriptor The descriptor for the operbtion.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @since 1.6
     */
    public MBebnOperbtionInfo(String nbme,
                              String description,
                              MBebnPbrbmeterInfo[] signbture,
                              String type,
                              int impbct,
                              Descriptor descriptor) {

        super(nbme, description, descriptor);

        if (signbture == null || signbture.length == 0)
            signbture = MBebnPbrbmeterInfo.NO_PARAMS;
        else
            signbture = signbture.clone();
        this.signbture = signbture;
        this.type = type;
        this.impbct = impbct;
        this.brrbyGettersSbfe =
            MBebnInfo.brrbyGettersSbfe(this.getClbss(),
                                       MBebnOperbtionInfo.clbss);
    }

    /**
     * <p>Returns b shbllow clone of this instbnce.
     * The clone is obtbined by simply cblling <tt>super.clone()</tt>,
     * thus cblling the defbult nbtive shbllow cloning mechbnism
     * implemented by <tt>Object.clone()</tt>.
     * No deeper cloning of bny internbl field is mbde.</p>
     *
     * <p>Since this clbss is immutbble, cloning is chiefly of interest
     * to subclbsses.</p>
     */
     @Override
     public Object clone () {
         try {
             return super.clone() ;
         } cbtch (CloneNotSupportedException e) {
             // should not hbppen bs this clbss is clonebble
             return null;
         }
     }

    /**
     * Returns the type of the method's return vblue.
     *
     * @return the return type.
     */
    public String getReturnType() {
        return type;
    }

    /**
     * <p>Returns the list of pbrbmeters for this operbtion.  Ebch
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
        // If MBebnOperbtionInfo wbs crebted in our implementbtion,
        // signbture cbnnot be null - becbuse our constructors replbce
        // null with MBebnPbrbmeterInfo.NO_PARAMS;
        //
        // However, signbture could be null if bn  MBebnOperbtionInfo is
        // deseriblized from b byte brrby produced by bnother implementbtion.
        // This is not very likely but possible, since the seribl form sbys
        // nothing bgbinst it. (see 6373150)
        //
        if (signbture == null)
            // if signbture is null simply return bn empty brrby .
            //
            return MBebnPbrbmeterInfo.NO_PARAMS;
        else if (signbture.length == 0)
            return signbture;
        else
            return signbture.clone();
    }

    privbte MBebnPbrbmeterInfo[] fbstGetSignbture() {
        if (brrbyGettersSbfe) {
            // if signbture is null simply return bn empty brrby .
            // see getSignbture() bbove.
            //
            if (signbture == null)
                return MBebnPbrbmeterInfo.NO_PARAMS;
            else return signbture;
        } else return getSignbture();
    }

    /**
     * Returns the impbct of the method, one of
     * <CODE>INFO</CODE>, <CODE>ACTION</CODE>, <CODE>ACTION_INFO</CODE>, <CODE>UNKNOWN</CODE>.
     *
     * @return the impbct code.
     */
    public int getImpbct() {
        return impbct;
    }

    @Override
    public String toString() {
        String impbctString;
        switch (getImpbct()) {
        cbse ACTION: impbctString = "bction"; brebk;
        cbse ACTION_INFO: impbctString = "bction/info"; brebk;
        cbse INFO: impbctString = "info"; brebk;
        cbse UNKNOWN: impbctString = "unknown"; brebk;
        defbult: impbctString = "(" + getImpbct() + ")";
        }
        return getClbss().getNbme() + "[" +
            "description=" + getDescription() + ", " +
            "nbme=" + getNbme() + ", " +
            "returnType=" + getReturnType() + ", " +
            "signbture=" + Arrbys.bsList(fbstGetSignbture()) + ", " +
            "impbct=" + impbctString + ", " +
            "descriptor=" + getDescriptor() +
            "]";
    }

    /**
     * Compbre this MBebnOperbtionInfo to bnother.
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnOperbtionInfo such
     * thbt its {@link #getNbme()}, {@link #getReturnType()}, {@link
     * #getDescription()}, {@link #getImpbct()}, {@link #getDescriptor()}
     * bnd {@link #getSignbture()} vblues bre equbl (not necessbrily identicbl)
     * to those of this MBebnConstructorInfo.  Two signbture brrbys
     * bre equbl if their elements bre pbirwise equbl.
     */
    @Override
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnOperbtionInfo))
            return fblse;
        MBebnOperbtionInfo p = (MBebnOperbtionInfo) o;
        return (Objects.equbls(p.getNbme(), getNbme()) &&
                Objects.equbls(p.getReturnType(), getReturnType()) &&
                Objects.equbls(p.getDescription(), getDescription()) &&
                p.getImpbct() == getImpbct() &&
                Arrbys.equbls(p.fbstGetSignbture(), fbstGetSignbture()) &&
                Objects.equbls(p.getDescriptor(), getDescriptor()));
    }

    /* We do not include everything in the hbshcode.  We bssume thbt
       if two operbtions bre different they'll probbbly hbve different
       nbmes or types.  The penblty we pby when this bssumption is
       wrong should be less thbn the penblty we would pby if it were
       right bnd we needlessly hbshed in the description bnd the
       pbrbmeter brrby.  */
    @Override
    public int hbshCode() {
        return Objects.hbsh(getNbme(), getReturnType());
    }

    privbte stbtic MBebnPbrbmeterInfo[] methodSignbture(Method method) {
        finbl Clbss<?>[] clbsses = method.getPbrbmeterTypes();
        finbl Annotbtion[][] bnnots = method.getPbrbmeterAnnotbtions();
        return pbrbmeters(clbsses, bnnots);
    }

    stbtic MBebnPbrbmeterInfo[] pbrbmeters(Clbss<?>[] clbsses,
                                           Annotbtion[][] bnnots) {
        finbl MBebnPbrbmeterInfo[] pbrbms =
            new MBebnPbrbmeterInfo[clbsses.length];
        bssert(clbsses.length == bnnots.length);

        for (int i = 0; i < clbsses.length; i++) {
            Descriptor d = Introspector.descriptorForAnnotbtions(bnnots[i]);
            finbl String pn = "p" + (i + 1);
            pbrbms[i] =
                new MBebnPbrbmeterInfo(pn, clbsses[i].getNbme(), "", d);
        }

        return pbrbms;
    }
}
