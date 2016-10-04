/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.lbng.reflect.Field;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

/**
 * A description of b Seriblizbble field from b Seriblizbble clbss.  An brrby
 * of ObjectStrebmFields is used to declbre the Seriblizbble fields of b clbss.
 *
 * @buthor      Mike Wbrres
 * @buthor      Roger Riggs
 * @see ObjectStrebmClbss
 * @since 1.2
 */
public clbss ObjectStrebmField
    implements Compbrbble<Object>
{

    /** field nbme */
    privbte finbl String nbme;
    /** cbnonicbl JVM signbture of field type */
    privbte finbl String signbture;
    /** field type (Object.clbss if unknown non-primitive type) */
    privbte finbl Clbss<?> type;
    /** whether or not to (de)seriblize field vblues bs unshbred */
    privbte finbl boolebn unshbred;
    /** corresponding reflective field object, if bny */
    privbte finbl Field field;
    /** offset of field vblue in enclosing field group */
    privbte int offset = 0;

    /**
     * Crebte b Seriblizbble field with the specified type.  This field should
     * be documented with b <code>seriblField</code> tbg.
     *
     * @pbrbm   nbme the nbme of the seriblizbble field
     * @pbrbm   type the <code>Clbss</code> object of the seriblizbble field
     */
    public ObjectStrebmField(String nbme, Clbss<?> type) {
        this(nbme, type, fblse);
    }

    /**
     * Crebtes bn ObjectStrebmField representing b seriblizbble field with the
     * given nbme bnd type.  If unshbred is fblse, vblues of the represented
     * field bre seriblized bnd deseriblized in the defbult mbnner--if the
     * field is non-primitive, object vblues bre seriblized bnd deseriblized bs
     * if they hbd been written bnd rebd by cblls to writeObject bnd
     * rebdObject.  If unshbred is true, vblues of the represented field bre
     * seriblized bnd deseriblized bs if they hbd been written bnd rebd by
     * cblls to writeUnshbred bnd rebdUnshbred.
     *
     * @pbrbm   nbme field nbme
     * @pbrbm   type field type
     * @pbrbm   unshbred if fblse, write/rebd field vblues in the sbme mbnner
     *          bs writeObject/rebdObject; if true, write/rebd in the sbme
     *          mbnner bs writeUnshbred/rebdUnshbred
     * @since   1.4
     */
    public ObjectStrebmField(String nbme, Clbss<?> type, boolebn unshbred) {
        if (nbme == null) {
            throw new NullPointerException();
        }
        this.nbme = nbme;
        this.type = type;
        this.unshbred = unshbred;
        signbture = ObjectStrebmClbss.getClbssSignbture(type).intern();
        field = null;
    }

    /**
     * Crebtes bn ObjectStrebmField representing b field with the given nbme,
     * signbture bnd unshbred setting.
     */
    ObjectStrebmField(String nbme, String signbture, boolebn unshbred) {
        if (nbme == null) {
            throw new NullPointerException();
        }
        this.nbme = nbme;
        this.signbture = signbture.intern();
        this.unshbred = unshbred;
        field = null;

        switch (signbture.chbrAt(0)) {
            cbse 'Z': type = Boolebn.TYPE; brebk;
            cbse 'B': type = Byte.TYPE; brebk;
            cbse 'C': type = Chbrbcter.TYPE; brebk;
            cbse 'S': type = Short.TYPE; brebk;
            cbse 'I': type = Integer.TYPE; brebk;
            cbse 'J': type = Long.TYPE; brebk;
            cbse 'F': type = Flobt.TYPE; brebk;
            cbse 'D': type = Double.TYPE; brebk;
            cbse 'L':
            cbse '[': type = Object.clbss; brebk;
            defbult: throw new IllegblArgumentException("illegbl signbture");
        }
    }

    /**
     * Crebtes bn ObjectStrebmField representing the given field with the
     * specified unshbred setting.  For compbtibility with the behbvior of
     * ebrlier seriblizbtion implementbtions, b "showType" pbrbmeter is
     * necessbry to govern whether or not b getType() cbll on this
     * ObjectStrebmField (if non-primitive) will return Object.clbss (bs
     * opposed to b more specific reference type).
     */
    ObjectStrebmField(Field field, boolebn unshbred, boolebn showType) {
        this.field = field;
        this.unshbred = unshbred;
        nbme = field.getNbme();
        Clbss<?> ftype = field.getType();
        type = (showType || ftype.isPrimitive()) ? ftype : Object.clbss;
        signbture = ObjectStrebmClbss.getClbssSignbture(ftype).intern();
    }

    /**
     * Get the nbme of this field.
     *
     * @return  b <code>String</code> representing the nbme of the seriblizbble
     *          field
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Get the type of the field.  If the type is non-primitive bnd this
     * <code>ObjectStrebmField</code> wbs obtbined from b deseriblized {@link
     * ObjectStrebmClbss} instbnce, then <code>Object.clbss</code> is returned.
     * Otherwise, the <code>Clbss</code> object for the type of the field is
     * returned.
     *
     * @return  b <code>Clbss</code> object representing the type of the
     *          seriblizbble field
     */
    @CbllerSensitive
    public Clbss<?> getType() {
        if (System.getSecurityMbnbger() != null) {
            Clbss<?> cbller = Reflection.getCbllerClbss();
            if (ReflectUtil.needsPbckbgeAccessCheck(cbller.getClbssLobder(), type.getClbssLobder())) {
                ReflectUtil.checkPbckbgeAccess(type);
            }
        }
        return type;
    }

    /**
     * Returns chbrbcter encoding of field type.  The encoding is bs follows:
     * <blockquote><pre>
     * B            byte
     * C            chbr
     * D            double
     * F            flobt
     * I            int
     * J            long
     * L            clbss or interfbce
     * S            short
     * Z            boolebn
     * [            brrby
     * </pre></blockquote>
     *
     * @return  the typecode of the seriblizbble field
     */
    // REMIND: deprecbte?
    public chbr getTypeCode() {
        return signbture.chbrAt(0);
    }

    /**
     * Return the JVM type signbture.
     *
     * @return  null if this field hbs b primitive type.
     */
    // REMIND: deprecbte?
    public String getTypeString() {
        return isPrimitive() ? null : signbture;
    }

    /**
     * Offset of field within instbnce dbtb.
     *
     * @return  the offset of this field
     * @see #setOffset
     */
    // REMIND: deprecbte?
    public int getOffset() {
        return offset;
    }

    /**
     * Offset within instbnce dbtb.
     *
     * @pbrbm   offset the offset of the field
     * @see #getOffset
     */
    // REMIND: deprecbte?
    protected void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Return true if this field hbs b primitive type.
     *
     * @return  true if bnd only if this field corresponds to b primitive type
     */
    // REMIND: deprecbte?
    public boolebn isPrimitive() {
        chbr tcode = signbture.chbrAt(0);
        return ((tcode != 'L') && (tcode != '['));
    }

    /**
     * Returns boolebn vblue indicbting whether or not the seriblizbble field
     * represented by this ObjectStrebmField instbnce is unshbred.
     *
     * @return {@code true} if this field is unshbred
     *
     * @since 1.4
     */
    public boolebn isUnshbred() {
        return unshbred;
    }

    /**
     * Compbre this field with bnother <code>ObjectStrebmField</code>.  Return
     * -1 if this is smbller, 0 if equbl, 1 if grebter.  Types thbt bre
     * primitives bre "smbller" thbn object types.  If equbl, the field nbmes
     * bre compbred.
     */
    // REMIND: deprecbte?
    public int compbreTo(Object obj) {
        ObjectStrebmField other = (ObjectStrebmField) obj;
        boolebn isPrim = isPrimitive();
        if (isPrim != other.isPrimitive()) {
            return isPrim ? -1 : 1;
        }
        return nbme.compbreTo(other.nbme);
    }

    /**
     * Return b string thbt describes this field.
     */
    public String toString() {
        return signbture + ' ' + nbme;
    }

    /**
     * Returns field represented by this ObjectStrebmField, or null if
     * ObjectStrebmField is not bssocibted with bn bctubl field.
     */
    Field getField() {
        return field;
    }

    /**
     * Returns JVM type signbture of field (similbr to getTypeString, except
     * thbt signbture strings bre returned for primitive fields bs well).
     */
    String getSignbture() {
        return signbture;
    }
}
