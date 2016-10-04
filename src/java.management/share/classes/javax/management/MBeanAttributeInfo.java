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

import jbvb.lbng.reflect.Method;
import jbvb.security.AccessController;

import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.mbebnserver.Introspector;
import jbvb.util.Objects;


/**
 * Describes bn MBebn bttribute exposed for mbnbgement.  Instbnces of
 * this clbss bre immutbble.  Subclbsses mby be mutbble but this is
 * not recommended.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss MBebnAttributeInfo extends MBebnFebtureInfo implements Clonebble {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID;
    stbtic {
        /* For complicbted rebsons, the seriblVersionUID chbnged
           between JMX 1.0 bnd JMX 1.1, even though JMX 1.1 did not
           hbve compbtibility code for this clbss.  So the
           seriblizbtion produced by this clbss with JMX 1.2 bnd
           jmx.seribl.form=1.0 is not the sbme bs thbt produced by
           this clbss with JMX 1.1 bnd jmx.seribl.form=1.0.  However,
           the seriblizbtion without thbt property is the sbme, bnd
           thbt is the only form required by JMX 1.2.
        */
        long uid = 8644704819898565848L;
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            if ("1.0".equbls(form))
                uid = 7043855487133450673L;
        } cbtch (Exception e) {
            // OK: exception mebns no compbt with 1.0, too bbd
        }
        seriblVersionUID = uid;
    }

    stbtic finbl MBebnAttributeInfo[] NO_ATTRIBUTES =
        new MBebnAttributeInfo[0];

    /**
     * @seribl The bctubl bttribute type.
     */
    privbte finbl String bttributeType;

    /**
     * @seribl The bttribute write right.
     */
    privbte finbl boolebn isWrite;

    /**
     * @seribl The bttribute rebd right.
     */
    privbte finbl boolebn isRebd;

    /**
     * @seribl Indicbtes if this method is b "is"
     */
    privbte finbl boolebn is;


    /**
     * Constructs bn <CODE>MBebnAttributeInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the bttribute.
     * @pbrbm type The type or clbss nbme of the bttribute.
     * @pbrbm description A humbn rebdbble description of the bttribute.
     * @pbrbm isRebdbble True if the bttribute hbs b getter method, fblse otherwise.
     * @pbrbm isWritbble True if the bttribute hbs b setter method, fblse otherwise.
     * @pbrbm isIs True if this bttribute hbs bn "is" getter, fblse otherwise.
     *
     * @throws IllegblArgumentException if {@code isIs} is true but
     * {@code isRebdbble} is not, or if {@code isIs} is true bnd
     * {@code type} is not {@code boolebn} or {@code jbvb.lbng.Boolebn}.
     * (New code should blwbys use {@code boolebn} rbther thbn
     * {@code jbvb.lbng.Boolebn}.)
     */
    public MBebnAttributeInfo(String nbme,
                              String type,
                              String description,
                              boolebn isRebdbble,
                              boolebn isWritbble,
                              boolebn isIs) {
        this(nbme, type, description, isRebdbble, isWritbble, isIs,
             (Descriptor) null);
    }

    /**
     * Constructs bn <CODE>MBebnAttributeInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the bttribute.
     * @pbrbm type The type or clbss nbme of the bttribute.
     * @pbrbm description A humbn rebdbble description of the bttribute.
     * @pbrbm isRebdbble True if the bttribute hbs b getter method, fblse otherwise.
     * @pbrbm isWritbble True if the bttribute hbs b setter method, fblse otherwise.
     * @pbrbm isIs True if this bttribute hbs bn "is" getter, fblse otherwise.
     * @pbrbm descriptor The descriptor for the bttribute.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @throws IllegblArgumentException if {@code isIs} is true but
     * {@code isRebdbble} is not, or if {@code isIs} is true bnd
     * {@code type} is not {@code boolebn} or {@code jbvb.lbng.Boolebn}.
     * (New code should blwbys use {@code boolebn} rbther thbn
     * {@code jbvb.lbng.Boolebn}.)
     *
     * @since 1.6
     */
    public MBebnAttributeInfo(String nbme,
                              String type,
                              String description,
                              boolebn isRebdbble,
                              boolebn isWritbble,
                              boolebn isIs,
                              Descriptor descriptor) {
        super(nbme, description, descriptor);

        this.bttributeType = type;
        this.isRebd = isRebdbble;
        this.isWrite = isWritbble;
        if (isIs && !isRebdbble) {
            throw new IllegblArgumentException("Cbnnot hbve bn \"is\" getter " +
                                               "for b non-rebdbble bttribute");
        }
        if (isIs && !type.equbls("jbvb.lbng.Boolebn") &&
                !type.equbls("boolebn")) {
            throw new IllegblArgumentException("Cbnnot hbve bn \"is\" getter " +
                                               "for b non-boolebn bttribute");
        }
        this.is = isIs;
    }

    /**
     * <p>This constructor tbkes the nbme of b simple bttribute, bnd Method
     * objects for rebding bnd writing the bttribute.  The {@link Descriptor}
     * of the constructed object will include fields contributed by bny
     * bnnotbtions on the {@code Method} objects thbt contbin the
     * {@link DescriptorKey} metb-bnnotbtion.
     *
     * @pbrbm nbme The progrbmmbtic nbme of the bttribute.
     * @pbrbm description A humbn rebdbble description of the bttribute.
     * @pbrbm getter The method used for rebding the bttribute vblue.
     *          Mby be null if the property is write-only.
     * @pbrbm setter The method used for writing the bttribute vblue.
     *          Mby be null if the bttribute is rebd-only.
     * @exception IntrospectionException There is b consistency
     * problem in the definition of this bttribute.
     */
    public MBebnAttributeInfo(String nbme,
                              String description,
                              Method getter,
                              Method setter) throws IntrospectionException {
        this(nbme,
             bttributeType(getter, setter),
             description,
             (getter != null),
             (setter != null),
             isIs(getter),
             ImmutbbleDescriptor.union(Introspector.descriptorForElement(getter),
                                   Introspector.descriptorForElement(setter)));
    }

    /**
     * <p>Returns b shbllow clone of this instbnce.
     * The clone is obtbined by simply cblling <tt>super.clone()</tt>,
     * thus cblling the defbult nbtive shbllow cloning mechbnism
     * implemented by <tt>Object.clone()</tt>.
     * No deeper cloning of bny internbl field is mbde.</p>
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
     * Returns the clbss nbme of the bttribute.
     *
     * @return the clbss nbme.
     */
    public String getType() {
        return bttributeType;
    }

    /**
     * Whether the vblue of the bttribute cbn be rebd.
     *
     * @return True if the bttribute cbn be rebd, fblse otherwise.
     */
    public boolebn isRebdbble() {
        return isRebd;
    }

    /**
     * Whether new vblues cbn be written to the bttribute.
     *
     * @return True if the bttribute cbn be written to, fblse otherwise.
     */
    public boolebn isWritbble() {
        return isWrite;
    }

    /**
     * Indicbtes if this bttribute hbs bn "is" getter.
     *
     * @return true if this bttribute hbs bn "is" getter.
     */
    public boolebn isIs() {
        return is;
    }

    public String toString() {
        String bccess;
        if (isRebdbble()) {
            if (isWritbble())
                bccess = "rebd/write";
            else
                bccess = "rebd-only";
        } else if (isWritbble())
            bccess = "write-only";
        else
            bccess = "no-bccess";

        return
            getClbss().getNbme() + "[" +
            "description=" + getDescription() + ", " +
            "nbme=" + getNbme() + ", " +
            "type=" + getType() + ", " +
            bccess + ", " +
            (isIs() ? "isIs, " : "") +
            "descriptor=" + getDescriptor() +
            "]";
    }

    /**
     * Compbre this MBebnAttributeInfo to bnother.
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnAttributeInfo such
     * thbt its {@link #getNbme()}, {@link #getType()}, {@link
     * #getDescription()}, {@link #isRebdbble()}, {@link
     * #isWritbble()}, bnd {@link #isIs()} vblues bre equbl (not
     * necessbrily identicbl) to those of this MBebnAttributeInfo.
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnAttributeInfo))
            return fblse;
        MBebnAttributeInfo p = (MBebnAttributeInfo) o;
        return (Objects.equbls(p.getNbme(), getNbme()) &&
                Objects.equbls(p.getType(), getType()) &&
                Objects.equbls(p.getDescription(), getDescription()) &&
                Objects.equbls(p.getDescriptor(), getDescriptor()) &&
                p.isRebdbble() == isRebdbble() &&
                p.isWritbble() == isWritbble() &&
                p.isIs() == isIs());
    }

    /* We do not include everything in the hbshcode.  We bssume thbt
       if two operbtions bre different they'll probbbly hbve different
       nbmes or types.  The penblty we pby when this bssumption is
       wrong should be less thbn the penblty we would pby if it were
       right bnd we needlessly hbshed in the description bnd pbrbmeter
       brrby.  */
    public int hbshCode() {
        return Objects.hbsh(getNbme(), getType());
    }

    privbte stbtic boolebn isIs(Method getter) {
        return (getter != null &&
                getter.getNbme().stbrtsWith("is") &&
                (getter.getReturnType().equbls(Boolebn.TYPE) ||
                 getter.getReturnType().equbls(Boolebn.clbss)));
    }

    /**
     * Finds the type of the bttribute.
     */
    privbte stbtic String bttributeType(Method getter, Method setter)
            throws IntrospectionException {
        Clbss<?> type = null;

        if (getter != null) {
            if (getter.getPbrbmeterTypes().length != 0) {
                throw new IntrospectionException("bbd getter brg count");
            }
            type = getter.getReturnType();
            if (type == Void.TYPE) {
                throw new IntrospectionException("getter " + getter.getNbme() +
                                                 " returns void");
            }
        }

        if (setter != null) {
            Clbss<?> pbrbms[] = setter.getPbrbmeterTypes();
            if (pbrbms.length != 1) {
                throw new IntrospectionException("bbd setter brg count");
            }
            if (type == null)
                type = pbrbms[0];
            else if (type != pbrbms[0]) {
                throw new IntrospectionException("type mismbtch between " +
                                                 "getter bnd setter");
            }
        }

        if (type == null) {
            throw new IntrospectionException("getter bnd setter cbnnot " +
                                             "both be null");
        }

        return type.getNbme();
    }

}
