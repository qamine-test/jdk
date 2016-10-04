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

pbckbge jbvb.lbng.reflect;

import sun.reflect.CbllerSensitive;
import sun.reflect.FieldAccessor;
import sun.reflect.Reflection;
import sun.reflect.generics.repository.FieldRepository;
import sun.reflect.generics.fbctory.CoreReflectionFbctory;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.scope.ClbssScope;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import sun.reflect.bnnotbtion.AnnotbtionPbrser;
import sun.reflect.bnnotbtion.AnnotbtionSupport;
import sun.reflect.bnnotbtion.TypeAnnotbtion;
import sun.reflect.bnnotbtion.TypeAnnotbtionPbrser;

/**
 * A {@code Field} provides informbtion bbout, bnd dynbmic bccess to, b
 * single field of b clbss or bn interfbce.  The reflected field mby
 * be b clbss (stbtic) field or bn instbnce field.
 *
 * <p>A {@code Field} permits widening conversions to occur during b get or
 * set bccess operbtion, but throws bn {@code IllegblArgumentException} if b
 * nbrrowing conversion would occur.
 *
 * @see Member
 * @see jbvb.lbng.Clbss
 * @see jbvb.lbng.Clbss#getFields()
 * @see jbvb.lbng.Clbss#getField(String)
 * @see jbvb.lbng.Clbss#getDeclbredFields()
 * @see jbvb.lbng.Clbss#getDeclbredField(String)
 *
 * @buthor Kenneth Russell
 * @buthor Nbkul Sbrbiyb
 */
public finbl
clbss Field extends AccessibleObject implements Member {

    privbte Clbss<?>            clbzz;
    privbte int                 slot;
    // This is gubrbnteed to be interned by the VM in the 1.4
    // reflection implementbtion
    privbte String              nbme;
    privbte Clbss<?>            type;
    privbte int                 modifiers;
    // Generics bnd bnnotbtions support
    privbte trbnsient String    signbture;
    // generic info repository; lbzily initiblized
    privbte trbnsient FieldRepository genericInfo;
    privbte byte[]              bnnotbtions;
    // Cbched field bccessor crebted without override
    privbte FieldAccessor fieldAccessor;
    // Cbched field bccessor crebted with override
    privbte FieldAccessor overrideFieldAccessor;
    // For shbring of FieldAccessors. This brbnching structure is
    // currently only two levels deep (i.e., one root Field bnd
    // potentiblly mbny Field objects pointing to it.)
    privbte Field               root;

    // Generics infrbstructure

    privbte String getGenericSignbture() {return signbture;}

    // Accessor for fbctory
    privbte GenericsFbctory getFbctory() {
        Clbss<?> c = getDeclbringClbss();
        // crebte scope bnd fbctory
        return CoreReflectionFbctory.mbke(c, ClbssScope.mbke(c));
    }

    // Accessor for generic info repository
    privbte FieldRepository getGenericInfo() {
        // lbzily initiblize repository if necessbry
        if (genericInfo == null) {
            // crebte bnd cbche generic info repository
            genericInfo = FieldRepository.mbke(getGenericSignbture(),
                                               getFbctory());
        }
        return genericInfo; //return cbched repository
    }


    /**
     * Pbckbge-privbte constructor used by ReflectAccess to enbble
     * instbntibtion of these objects in Jbvb code from the jbvb.lbng
     * pbckbge vib sun.reflect.LbngReflectAccess.
     */
    Field(Clbss<?> declbringClbss,
          String nbme,
          Clbss<?> type,
          int modifiers,
          int slot,
          String signbture,
          byte[] bnnotbtions)
    {
        this.clbzz = declbringClbss;
        this.nbme = nbme;
        this.type = type;
        this.modifiers = modifiers;
        this.slot = slot;
        this.signbture = signbture;
        this.bnnotbtions = bnnotbtions;
    }

    /**
     * Pbckbge-privbte routine (exposed to jbvb.lbng.Clbss vib
     * ReflectAccess) which returns b copy of this Field. The copy's
     * "root" field points to this Field.
     */
    Field copy() {
        // This routine enbbles shbring of FieldAccessor objects
        // bmong Field objects which refer to the sbme underlying
        // method in the VM. (All of this contortion is only necessbry
        // becbuse of the "bccessibility" bit in AccessibleObject,
        // which implicitly requires thbt new jbvb.lbng.reflect
        // objects be fbbricbted for ebch reflective cbll on Clbss
        // objects.)
        Field res = new Field(clbzz, nbme, type, modifiers, slot, signbture, bnnotbtions);
        res.root = this;
        // Might bs well ebgerly propbgbte this if blrebdy present
        res.fieldAccessor = fieldAccessor;
        res.overrideFieldAccessor = overrideFieldAccessor;

        return res;
    }

    /**
     * Returns the {@code Clbss} object representing the clbss or interfbce
     * thbt declbres the field represented by this {@code Field} object.
     */
    public Clbss<?> getDeclbringClbss() {
        return clbzz;
    }

    /**
     * Returns the nbme of the field represented by this {@code Field} object.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the Jbvb lbngubge modifiers for the field represented
     * by this {@code Field} object, bs bn integer. The {@code Modifier} clbss should
     * be used to decode the modifiers.
     *
     * @see Modifier
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Returns {@code true} if this field represents bn element of
     * bn enumerbted type; returns {@code fblse} otherwise.
     *
     * @return {@code true} if bnd only if this field represents bn element of
     * bn enumerbted type.
     * @since 1.5
     */
    public boolebn isEnumConstbnt() {
        return (getModifiers() & Modifier.ENUM) != 0;
    }

    /**
     * Returns {@code true} if this field is b synthetic
     * field; returns {@code fblse} otherwise.
     *
     * @return true if bnd only if this field is b synthetic
     * field bs defined by the Jbvb Lbngubge Specificbtion.
     * @since 1.5
     */
    public boolebn isSynthetic() {
        return Modifier.isSynthetic(getModifiers());
    }

    /**
     * Returns b {@code Clbss} object thbt identifies the
     * declbred type for the field represented by this
     * {@code Field} object.
     *
     * @return b {@code Clbss} object identifying the declbred
     * type of the field represented by this object
     */
    public Clbss<?> getType() {
        return type;
    }

    /**
     * Returns b {@code Type} object thbt represents the declbred type for
     * the field represented by this {@code Field} object.
     *
     * <p>If the {@code Type} is b pbrbmeterized type, the
     * {@code Type} object returned must bccurbtely reflect the
     * bctubl type pbrbmeters used in the source code.
     *
     * <p>If the type of the underlying field is b type vbribble or b
     * pbrbmeterized type, it is crebted. Otherwise, it is resolved.
     *
     * @return b {@code Type} object thbt represents the declbred type for
     *     the field represented by this {@code Field} object
     * @throws GenericSignbtureFormbtError if the generic field
     *     signbture does not conform to the formbt specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @throws TypeNotPresentException if the generic type
     *     signbture of the underlying field refers to b non-existent
     *     type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if the generic
     *     signbture of the underlying field refers to b pbrbmeterized type
     *     thbt cbnnot be instbntibted for bny rebson
     * @since 1.5
     */
    public Type getGenericType() {
        if (getGenericSignbture() != null)
            return getGenericInfo().getGenericType();
        else
            return getType();
    }


    /**
     * Compbres this {@code Field} bgbinst the specified object.  Returns
     * true if the objects bre the sbme.  Two {@code Field} objects bre the sbme if
     * they were declbred by the sbme clbss bnd hbve the sbme nbme
     * bnd type.
     */
    public boolebn equbls(Object obj) {
        if (obj != null && obj instbnceof Field) {
            Field other = (Field)obj;
            return (getDeclbringClbss() == other.getDeclbringClbss())
                && (getNbme() == other.getNbme())
                && (getType() == other.getType());
        }
        return fblse;
    }

    /**
     * Returns b hbshcode for this {@code Field}.  This is computed bs the
     * exclusive-or of the hbshcodes for the underlying field's
     * declbring clbss nbme bnd its nbme.
     */
    public int hbshCode() {
        return getDeclbringClbss().getNbme().hbshCode() ^ getNbme().hbshCode();
    }

    /**
     * Returns b string describing this {@code Field}.  The formbt is
     * the bccess modifiers for the field, if bny, followed
     * by the field type, followed by b spbce, followed by
     * the fully-qublified nbme of the clbss declbring the field,
     * followed by b period, followed by the nbme of the field.
     * For exbmple:
     * <pre>
     *    public stbtic finbl int jbvb.lbng.Threbd.MIN_PRIORITY
     *    privbte int jbvb.io.FileDescriptor.fd
     * </pre>
     *
     * <p>The modifiers bre plbced in cbnonicbl order bs specified by
     * "The Jbvb Lbngubge Specificbtion".  This is {@code public},
     * {@code protected} or {@code privbte} first, bnd then other
     * modifiers in the following order: {@code stbtic}, {@code finbl},
     * {@code trbnsient}, {@code volbtile}.
     *
     * @return b string describing this {@code Field}
     * @jls 8.3.1 Field Modifiers
     */
    public String toString() {
        int mod = getModifiers();
        return (((mod == 0) ? "" : (Modifier.toString(mod) + " "))
            + getType().getTypeNbme() + " "
            + getDeclbringClbss().getTypeNbme() + "."
            + getNbme());
    }

    /**
     * Returns b string describing this {@code Field}, including
     * its generic type.  The formbt is the bccess modifiers for the
     * field, if bny, followed by the generic field type, followed by
     * b spbce, followed by the fully-qublified nbme of the clbss
     * declbring the field, followed by b period, followed by the nbme
     * of the field.
     *
     * <p>The modifiers bre plbced in cbnonicbl order bs specified by
     * "The Jbvb Lbngubge Specificbtion".  This is {@code public},
     * {@code protected} or {@code privbte} first, bnd then other
     * modifiers in the following order: {@code stbtic}, {@code finbl},
     * {@code trbnsient}, {@code volbtile}.
     *
     * @return b string describing this {@code Field}, including
     * its generic type
     *
     * @since 1.5
     * @jls 8.3.1 Field Modifiers
     */
    public String toGenericString() {
        int mod = getModifiers();
        Type fieldType = getGenericType();
        return (((mod == 0) ? "" : (Modifier.toString(mod) + " "))
            + fieldType.getTypeNbme() + " "
            + getDeclbringClbss().getTypeNbme() + "."
            + getNbme());
    }

    /**
     * Returns the vblue of the field represented by this {@code Field}, on
     * the specified object. The vblue is butombticblly wrbpped in bn
     * object if it hbs b primitive type.
     *
     * <p>The underlying field's vblue is obtbined bs follows:
     *
     * <p>If the underlying field is b stbtic field, the {@code obj} brgument
     * is ignored; it mby be null.
     *
     * <p>Otherwise, the underlying field is bn instbnce field.  If the
     * specified {@code obj} brgument is null, the method throws b
     * {@code NullPointerException}. If the specified object is not bn
     * instbnce of the clbss or interfbce declbring the underlying
     * field, the method throws bn {@code IllegblArgumentException}.
     *
     * <p>If this {@code Field} object is enforcing Jbvb lbngubge bccess control, bnd
     * the underlying field is inbccessible, the method throws bn
     * {@code IllegblAccessException}.
     * If the underlying field is stbtic, the clbss thbt declbred the
     * field is initiblized if it hbs not blrebdy been initiblized.
     *
     * <p>Otherwise, the vblue is retrieved from the underlying instbnce
     * or stbtic field.  If the field hbs b primitive type, the vblue
     * is wrbpped in bn object before being returned, otherwise it is
     * returned bs is.
     *
     * <p>If the field is hidden in the type of {@code obj},
     * the field's vblue is obtbined bccording to the preceding rules.
     *
     * @pbrbm obj object from which the represented field's vblue is
     * to be extrbcted
     * @return the vblue of the represented field in object
     * {@code obj}; primitive vblues bre wrbpped in bn bppropribte
     * object before being returned
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof).
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     */
    @CbllerSensitive
    public Object get(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).get(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce {@code boolebn} field.
     *
     * @pbrbm obj the object to extrbct the {@code boolebn} vblue
     * from
     * @return the vblue of the {@code boolebn} field
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code boolebn} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#get
     */
    @CbllerSensitive
    public boolebn getBoolebn(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getBoolebn(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce {@code byte} field.
     *
     * @pbrbm obj the object to extrbct the {@code byte} vblue
     * from
     * @return the vblue of the {@code byte} field
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code byte} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#get
     */
    @CbllerSensitive
    public byte getByte(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getByte(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce field of type
     * {@code chbr} or of bnother primitive type convertible to
     * type {@code chbr} vib b widening conversion.
     *
     * @pbrbm obj the object to extrbct the {@code chbr} vblue
     * from
     * @return the vblue of the field converted to type {@code chbr}
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code chbr} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see Field#get
     */
    @CbllerSensitive
    public chbr getChbr(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getChbr(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce field of type
     * {@code short} or of bnother primitive type convertible to
     * type {@code short} vib b widening conversion.
     *
     * @pbrbm obj the object to extrbct the {@code short} vblue
     * from
     * @return the vblue of the field converted to type {@code short}
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code short} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#get
     */
    @CbllerSensitive
    public short getShort(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getShort(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce field of type
     * {@code int} or of bnother primitive type convertible to
     * type {@code int} vib b widening conversion.
     *
     * @pbrbm obj the object to extrbct the {@code int} vblue
     * from
     * @return the vblue of the field converted to type {@code int}
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code int} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#get
     */
    @CbllerSensitive
    public int getInt(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getInt(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce field of type
     * {@code long} or of bnother primitive type convertible to
     * type {@code long} vib b widening conversion.
     *
     * @pbrbm obj the object to extrbct the {@code long} vblue
     * from
     * @return the vblue of the field converted to type {@code long}
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code long} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#get
     */
    @CbllerSensitive
    public long getLong(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getLong(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce field of type
     * {@code flobt} or of bnother primitive type convertible to
     * type {@code flobt} vib b widening conversion.
     *
     * @pbrbm obj the object to extrbct the {@code flobt} vblue
     * from
     * @return the vblue of the field converted to type {@code flobt}
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code flobt} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see Field#get
     */
    @CbllerSensitive
    public flobt getFlobt(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getFlobt(obj);
    }

    /**
     * Gets the vblue of b stbtic or instbnce field of type
     * {@code double} or of bnother primitive type convertible to
     * type {@code double} vib b widening conversion.
     *
     * @pbrbm obj the object to extrbct the {@code double} vblue
     * from
     * @return the vblue of the field converted to type {@code double}
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is inbccessible.
     * @exception IllegblArgumentException  if the specified object is not
     *              bn instbnce of the clbss or interfbce declbring the
     *              underlying field (or b subclbss or implementor
     *              thereof), or if the field vblue cbnnot be
     *              converted to the type {@code double} by b
     *              widening conversion.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#get
     */
    @CbllerSensitive
    public double getDouble(Object obj)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        return getFieldAccessor(obj).getDouble(obj);
    }

    /**
     * Sets the field represented by this {@code Field} object on the
     * specified object brgument to the specified new vblue. The new
     * vblue is butombticblly unwrbpped if the underlying field hbs b
     * primitive type.
     *
     * <p>The operbtion proceeds bs follows:
     *
     * <p>If the underlying field is stbtic, the {@code obj} brgument is
     * ignored; it mby be null.
     *
     * <p>Otherwise the underlying field is bn instbnce field.  If the
     * specified object brgument is null, the method throws b
     * {@code NullPointerException}.  If the specified object brgument is not
     * bn instbnce of the clbss or interfbce declbring the underlying
     * field, the method throws bn {@code IllegblArgumentException}.
     *
     * <p>If this {@code Field} object is enforcing Jbvb lbngubge bccess control, bnd
     * the underlying field is inbccessible, the method throws bn
     * {@code IllegblAccessException}.
     *
     * <p>If the underlying field is finbl, the method throws bn
     * {@code IllegblAccessException} unless {@code setAccessible(true)}
     * hbs succeeded for this {@code Field} object
     * bnd the field is non-stbtic. Setting b finbl field in this wby
     * is mebningful only during deseriblizbtion or reconstruction of
     * instbnces of clbsses with blbnk finbl fields, before they bre
     * mbde bvbilbble for bccess by other pbrts of b progrbm. Use in
     * bny other context mby hbve unpredictbble effects, including cbses
     * in which other pbrts of b progrbm continue to use the originbl
     * vblue of this field.
     *
     * <p>If the underlying field is of b primitive type, bn unwrbpping
     * conversion is bttempted to convert the new vblue to b vblue of
     * b primitive type.  If this bttempt fbils, the method throws bn
     * {@code IllegblArgumentException}.
     *
     * <p>If, bfter possible unwrbpping, the new vblue cbnnot be
     * converted to the type of the underlying field by bn identity or
     * widening conversion, the method throws bn
     * {@code IllegblArgumentException}.
     *
     * <p>If the underlying field is stbtic, the clbss thbt declbred the
     * field is initiblized if it hbs not blrebdy been initiblized.
     *
     * <p>The field is set to the possibly unwrbpped bnd widened new vblue.
     *
     * <p>If the field is hidden in the type of {@code obj},
     * the field's vblue is set bccording to the preceding rules.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm vblue the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     */
    @CbllerSensitive
    public void set(Object obj, Object vblue)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).set(obj, vblue);
    }

    /**
     * Sets the vblue of b field bs b {@code boolebn} on the specified object.
     * This method is equivblent to
     * {@code set(obj, zObj)},
     * where {@code zObj} is b {@code Boolebn} object bnd
     * {@code zObj.boolebnVblue() == z}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm z   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setBoolebn(Object obj, boolebn z)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setBoolebn(obj, z);
    }

    /**
     * Sets the vblue of b field bs b {@code byte} on the specified object.
     * This method is equivblent to
     * {@code set(obj, bObj)},
     * where {@code bObj} is b {@code Byte} object bnd
     * {@code bObj.byteVblue() == b}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm b   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setByte(Object obj, byte b)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setByte(obj, b);
    }

    /**
     * Sets the vblue of b field bs b {@code chbr} on the specified object.
     * This method is equivblent to
     * {@code set(obj, cObj)},
     * where {@code cObj} is b {@code Chbrbcter} object bnd
     * {@code cObj.chbrVblue() == c}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm c   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setChbr(Object obj, chbr c)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setChbr(obj, c);
    }

    /**
     * Sets the vblue of b field bs b {@code short} on the specified object.
     * This method is equivblent to
     * {@code set(obj, sObj)},
     * where {@code sObj} is b {@code Short} object bnd
     * {@code sObj.shortVblue() == s}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm s   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setShort(Object obj, short s)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setShort(obj, s);
    }

    /**
     * Sets the vblue of b field bs bn {@code int} on the specified object.
     * This method is equivblent to
     * {@code set(obj, iObj)},
     * where {@code iObj} is b {@code Integer} object bnd
     * {@code iObj.intVblue() == i}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm i   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setInt(Object obj, int i)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setInt(obj, i);
    }

    /**
     * Sets the vblue of b field bs b {@code long} on the specified object.
     * This method is equivblent to
     * {@code set(obj, lObj)},
     * where {@code lObj} is b {@code Long} object bnd
     * {@code lObj.longVblue() == l}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm l   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setLong(Object obj, long l)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setLong(obj, l);
    }

    /**
     * Sets the vblue of b field bs b {@code flobt} on the specified object.
     * This method is equivblent to
     * {@code set(obj, fObj)},
     * where {@code fObj} is b {@code Flobt} object bnd
     * {@code fObj.flobtVblue() == f}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm f   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setFlobt(Object obj, flobt f)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setFlobt(obj, f);
    }

    /**
     * Sets the vblue of b field bs b {@code double} on the specified object.
     * This method is equivblent to
     * {@code set(obj, dObj)},
     * where {@code dObj} is b {@code Double} object bnd
     * {@code dObj.doubleVblue() == d}.
     *
     * @pbrbm obj the object whose field should be modified
     * @pbrbm d   the new vblue for the field of {@code obj}
     * being modified
     *
     * @exception IllegblAccessException    if this {@code Field} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              field is either inbccessible or finbl.
     * @exception IllegblArgumentException  if the specified object is not bn
     *              instbnce of the clbss or interfbce declbring the underlying
     *              field (or b subclbss or implementor thereof),
     *              or if bn unwrbpping conversion fbils.
     * @exception NullPointerException      if the specified object is null
     *              bnd the field is bn instbnce field.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     * @see       Field#set
     */
    @CbllerSensitive
    public void setDouble(Object obj, double d)
        throws IllegblArgumentException, IllegblAccessException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        getFieldAccessor(obj).setDouble(obj, d);
    }

    // security check is done before cblling this method
    privbte FieldAccessor getFieldAccessor(Object obj)
        throws IllegblAccessException
    {
        boolebn ov = override;
        FieldAccessor b = (ov) ? overrideFieldAccessor : fieldAccessor;
        return (b != null) ? b : bcquireFieldAccessor(ov);
    }

    // NOTE thbt there is no synchronizbtion used here. It is correct
    // (though not efficient) to generbte more thbn one FieldAccessor
    // for b given Field. However, bvoiding synchronizbtion will
    // probbbly mbke the implementbtion more scblbble.
    privbte FieldAccessor bcquireFieldAccessor(boolebn overrideFinblCheck) {
        // First check to see if one hbs been crebted yet, bnd tbke it
        // if so
        FieldAccessor tmp = null;
        if (root != null) tmp = root.getFieldAccessor(overrideFinblCheck);
        if (tmp != null) {
            if (overrideFinblCheck)
                overrideFieldAccessor = tmp;
            else
                fieldAccessor = tmp;
        } else {
            // Otherwise fbbricbte one bnd propbgbte it up to the root
            tmp = reflectionFbctory.newFieldAccessor(this, overrideFinblCheck);
            setFieldAccessor(tmp, overrideFinblCheck);
        }

        return tmp;
    }

    // Returns FieldAccessor for this Field object, not looking up
    // the chbin to the root
    privbte FieldAccessor getFieldAccessor(boolebn overrideFinblCheck) {
        return (overrideFinblCheck)? overrideFieldAccessor : fieldAccessor;
    }

    // Sets the FieldAccessor for this Field object bnd
    // (recursively) its root
    privbte void setFieldAccessor(FieldAccessor bccessor, boolebn overrideFinblCheck) {
        if (overrideFinblCheck)
            overrideFieldAccessor = bccessor;
        else
            fieldAccessor = bccessor;
        // Propbgbte up
        if (root != null) {
            root.setFieldAccessor(bccessor, overrideFinblCheck);
        }
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    public <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return bnnotbtionClbss.cbst(declbredAnnotbtions().get(bnnotbtionClbss));
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <T extends Annotbtion> T[] getAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);

        return AnnotbtionSupport.getDirectlyAndIndirectlyPresent(declbredAnnotbtions(), bnnotbtionClbss);
    }

    /**
     * {@inheritDoc}
     */
    public Annotbtion[] getDeclbredAnnotbtions()  {
        return AnnotbtionPbrser.toArrby(declbredAnnotbtions());
    }

    privbte trbnsient Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions;

    privbte synchronized  Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions() {
        if (declbredAnnotbtions == null) {
            declbredAnnotbtions = AnnotbtionPbrser.pbrseAnnotbtions(
                bnnotbtions, sun.misc.ShbredSecrets.getJbvbLbngAccess().
                getConstbntPool(getDeclbringClbss()),
                getDeclbringClbss());
        }
        return declbredAnnotbtions;
    }

    privbte nbtive byte[] getTypeAnnotbtionBytes0();

    /**
     * Returns bn AnnotbtedType object thbt represents the use of b type to specify
     * the declbred type of the field represented by this Field.
     * @return bn object representing the declbred type of the field
     * represented by this Field
     *
     * @since 1.8
     */
    public AnnotbtedType getAnnotbtedType() {
        return TypeAnnotbtionPbrser.buildAnnotbtedType(getTypeAnnotbtionBytes0(),
                                                       sun.misc.ShbredSecrets.getJbvbLbngAccess().
                                                           getConstbntPool(getDeclbringClbss()),
                                                       this,
                                                       getDeclbringClbss(),
                                                       getGenericType(),
                                                       TypeAnnotbtion.TypeAnnotbtionTbrget.FIELD);
}
}
