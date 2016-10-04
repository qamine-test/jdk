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

import jbvb.util.Objects;


/**
 * Describes bn brgument of bn operbtion exposed by bn MBebn.
 * Instbnces of this clbss bre immutbble.  Subclbsses mby be mutbble
 * but this is not recommended.
 *
 * @since 1.5
 */
public clbss MBebnPbrbmeterInfo extends MBebnFebtureInfo implements Clonebble {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 7432616882776782338L;

    /* All zero-length brrbys bre interchbngebble. */
    stbtic finbl MBebnPbrbmeterInfo[] NO_PARAMS = new MBebnPbrbmeterInfo[0];

    /**
     * @seribl The type or clbss nbme of the dbtb.
     */
    privbte finbl String type;


    /**
     * Constructs bn <CODE>MBebnPbrbmeterInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the dbtb
     * @pbrbm type The type or clbss nbme of the dbtb
     * @pbrbm description A humbn rebdbble description of the dbtb. Optionbl.
     */
    public MBebnPbrbmeterInfo(String nbme,
                              String type,
                              String description) {
        this(nbme, type, description, (Descriptor) null);
    }

    /**
     * Constructs bn <CODE>MBebnPbrbmeterInfo</CODE> object.
     *
     * @pbrbm nbme The nbme of the dbtb
     * @pbrbm type The type or clbss nbme of the dbtb
     * @pbrbm description A humbn rebdbble description of the dbtb. Optionbl.
     * @pbrbm descriptor The descriptor for the operbtion.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @since 1.6
     */
    public MBebnPbrbmeterInfo(String nbme,
                              String type,
                              String description,
                              Descriptor descriptor) {
        super(nbme, description, descriptor);

        this.type = type;
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
     * Returns the type or clbss nbme of the dbtb.
     *
     * @return the type string.
     */
    public String getType() {
        return type;
    }

    public String toString() {
        return
            getClbss().getNbme() + "[" +
            "description=" + getDescription() + ", " +
            "nbme=" + getNbme() + ", " +
            "type=" + getType() + ", " +
            "descriptor=" + getDescriptor() +
            "]";
    }

    /**
     * Compbre this MBebnPbrbmeterInfo to bnother.
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnPbrbmeterInfo such
     * thbt its {@link #getNbme()}, {@link #getType()},
     * {@link #getDescriptor()}, bnd {@link
     * #getDescription()} vblues bre equbl (not necessbrily identicbl)
     * to those of this MBebnPbrbmeterInfo.
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnPbrbmeterInfo))
            return fblse;
        MBebnPbrbmeterInfo p = (MBebnPbrbmeterInfo) o;
        return (Objects.equbls(p.getNbme(), getNbme()) &&
                Objects.equbls(p.getType(), getType()) &&
                Objects.equbls(p.getDescription(), getDescription()) &&
                Objects.equbls(p.getDescriptor(), getDescriptor()));
    }

    public int hbshCode() {
        return Objects.hbsh(getNbme(), getType());
    }
}
