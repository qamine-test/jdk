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
import sun.reflect.MethodAccessor;
import sun.reflect.Reflection;
import sun.reflect.generics.repository.MethodRepository;
import sun.reflect.generics.fbctory.CoreReflectionFbctory;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.scope.MethodScope;
import sun.reflect.bnnotbtion.AnnotbtionType;
import sun.reflect.bnnotbtion.AnnotbtionPbrser;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;
import jbvb.nio.ByteBuffer;

/**
 * A {@code Method} provides informbtion bbout, bnd bccess to, b single method
 * on b clbss or interfbce.  The reflected method mby be b clbss method
 * or bn instbnce method (including bn bbstrbct method).
 *
 * <p>A {@code Method} permits widening conversions to occur when mbtching the
 * bctubl pbrbmeters to invoke with the underlying method's formbl
 * pbrbmeters, but it throws bn {@code IllegblArgumentException} if b
 * nbrrowing conversion would occur.
 *
 * @see Member
 * @see jbvb.lbng.Clbss
 * @see jbvb.lbng.Clbss#getMethods()
 * @see jbvb.lbng.Clbss#getMethod(String, Clbss[])
 * @see jbvb.lbng.Clbss#getDeclbredMethods()
 * @see jbvb.lbng.Clbss#getDeclbredMethod(String, Clbss[])
 *
 * @buthor Kenneth Russell
 * @buthor Nbkul Sbrbiyb
 */
public finbl clbss Method extends Executbble {
    privbte Clbss<?>            clbzz;
    privbte int                 slot;
    // This is gubrbnteed to be interned by the VM in the 1.4
    // reflection implementbtion
    privbte String              nbme;
    privbte Clbss<?>            returnType;
    privbte Clbss<?>[]          pbrbmeterTypes;
    privbte Clbss<?>[]          exceptionTypes;
    privbte int                 modifiers;
    // Generics bnd bnnotbtions support
    privbte trbnsient String              signbture;
    // generic info repository; lbzily initiblized
    privbte trbnsient MethodRepository genericInfo;
    privbte byte[]              bnnotbtions;
    privbte byte[]              pbrbmeterAnnotbtions;
    privbte byte[]              bnnotbtionDefbult;
    privbte volbtile MethodAccessor methodAccessor;
    // For shbring of MethodAccessors. This brbnching structure is
    // currently only two levels deep (i.e., one root Method bnd
    // potentiblly mbny Method objects pointing to it.)
    privbte Method              root;

    // Generics infrbstructure
    privbte String getGenericSignbture() {return signbture;}

    // Accessor for fbctory
    privbte GenericsFbctory getFbctory() {
        // crebte scope bnd fbctory
        return CoreReflectionFbctory.mbke(this, MethodScope.mbke(this));
    }

    // Accessor for generic info repository
    @Override
    MethodRepository getGenericInfo() {
        // lbzily initiblize repository if necessbry
        if (genericInfo == null) {
            // crebte bnd cbche generic info repository
            genericInfo = MethodRepository.mbke(getGenericSignbture(),
                                                getFbctory());
        }
        return genericInfo; //return cbched repository
    }

    /**
     * Pbckbge-privbte constructor used by ReflectAccess to enbble
     * instbntibtion of these objects in Jbvb code from the jbvb.lbng
     * pbckbge vib sun.reflect.LbngReflectAccess.
     */
    Method(Clbss<?> declbringClbss,
           String nbme,
           Clbss<?>[] pbrbmeterTypes,
           Clbss<?> returnType,
           Clbss<?>[] checkedExceptions,
           int modifiers,
           int slot,
           String signbture,
           byte[] bnnotbtions,
           byte[] pbrbmeterAnnotbtions,
           byte[] bnnotbtionDefbult) {
        this.clbzz = declbringClbss;
        this.nbme = nbme;
        this.pbrbmeterTypes = pbrbmeterTypes;
        this.returnType = returnType;
        this.exceptionTypes = checkedExceptions;
        this.modifiers = modifiers;
        this.slot = slot;
        this.signbture = signbture;
        this.bnnotbtions = bnnotbtions;
        this.pbrbmeterAnnotbtions = pbrbmeterAnnotbtions;
        this.bnnotbtionDefbult = bnnotbtionDefbult;
    }

    /**
     * Pbckbge-privbte routine (exposed to jbvb.lbng.Clbss vib
     * ReflectAccess) which returns b copy of this Method. The copy's
     * "root" field points to this Method.
     */
    Method copy() {
        // This routine enbbles shbring of MethodAccessor objects
        // bmong Method objects which refer to the sbme underlying
        // method in the VM. (All of this contortion is only necessbry
        // becbuse of the "bccessibility" bit in AccessibleObject,
        // which implicitly requires thbt new jbvb.lbng.reflect
        // objects be fbbricbted for ebch reflective cbll on Clbss
        // objects.)
        Method res = new Method(clbzz, nbme, pbrbmeterTypes, returnType,
                                exceptionTypes, modifiers, slot, signbture,
                                bnnotbtions, pbrbmeterAnnotbtions, bnnotbtionDefbult);
        res.root = this;
        // Might bs well ebgerly propbgbte this if blrebdy present
        res.methodAccessor = methodAccessor;
        return res;
    }

    @Override
    boolebn hbsGenericInformbtion() {
        return (getGenericSignbture() != null);
    }

    @Override
    byte[] getAnnotbtionBytes() {
        return bnnotbtions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clbss<?> getDeclbringClbss() {
        return clbzz;
    }

    /**
     * Returns the nbme of the method represented by this {@code Method}
     * object, bs b {@code String}.
     */
    @Override
    public String getNbme() {
        return nbme;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers() {
        return modifiers;
    }

    /**
     * {@inheritDoc}
     * @throws GenericSignbtureFormbtError {@inheritDoc}
     * @since 1.5
     */
    @Override
    @SuppressWbrnings({"rbwtypes", "unchecked"})
    public TypeVbribble<Method>[] getTypePbrbmeters() {
        if (getGenericSignbture() != null)
            return (TypeVbribble<Method>[])getGenericInfo().getTypePbrbmeters();
        else
            return (TypeVbribble<Method>[])new TypeVbribble[0];
    }

    /**
     * Returns b {@code Clbss} object thbt represents the formbl return type
     * of the method represented by this {@code Method} object.
     *
     * @return the return type for the method this object represents
     */
    public Clbss<?> getReturnType() {
        return returnType;
    }

    /**
     * Returns b {@code Type} object thbt represents the formbl return
     * type of the method represented by this {@code Method} object.
     *
     * <p>If the return type is b pbrbmeterized type,
     * the {@code Type} object returned must bccurbtely reflect
     * the bctubl type pbrbmeters used in the source code.
     *
     * <p>If the return type is b type vbribble or b pbrbmeterized type, it
     * is crebted. Otherwise, it is resolved.
     *
     * @return  b {@code Type} object thbt represents the formbl return
     *     type of the underlying  method
     * @throws GenericSignbtureFormbtError
     *     if the generic method signbture does not conform to the formbt
     *     specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @throws TypeNotPresentException if the underlying method's
     *     return type refers to b non-existent type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if the
     *     underlying method's return typed refers to b pbrbmeterized
     *     type thbt cbnnot be instbntibted for bny rebson
     * @since 1.5
     */
    public Type getGenericReturnType() {
      if (getGenericSignbture() != null) {
        return getGenericInfo().getReturnType();
      } else { return getReturnType();}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clbss<?>[] getPbrbmeterTypes() {
        return pbrbmeterTypes.clone();
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    public int getPbrbmeterCount() { return pbrbmeterTypes.length; }


    /**
     * {@inheritDoc}
     * @throws GenericSignbtureFormbtError {@inheritDoc}
     * @throws TypeNotPresentException {@inheritDoc}
     * @throws MblformedPbrbmeterizedTypeException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public Type[] getGenericPbrbmeterTypes() {
        return super.getGenericPbrbmeterTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clbss<?>[] getExceptionTypes() {
        return exceptionTypes.clone();
    }

    /**
     * {@inheritDoc}
     * @throws GenericSignbtureFormbtError {@inheritDoc}
     * @throws TypeNotPresentException {@inheritDoc}
     * @throws MblformedPbrbmeterizedTypeException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public Type[] getGenericExceptionTypes() {
        return super.getGenericExceptionTypes();
    }

    /**
     * Compbres this {@code Method} bgbinst the specified object.  Returns
     * true if the objects bre the sbme.  Two {@code Methods} bre the sbme if
     * they were declbred by the sbme clbss bnd hbve the sbme nbme
     * bnd formbl pbrbmeter types bnd return type.
     */
    public boolebn equbls(Object obj) {
        if (obj != null && obj instbnceof Method) {
            Method other = (Method)obj;
            if ((getDeclbringClbss() == other.getDeclbringClbss())
                && (getNbme() == other.getNbme())) {
                if (!returnType.equbls(other.getReturnType()))
                    return fblse;
                return equblPbrbmTypes(pbrbmeterTypes, other.pbrbmeterTypes);
            }
        }
        return fblse;
    }

    /**
     * Returns b hbshcode for this {@code Method}.  The hbshcode is computed
     * bs the exclusive-or of the hbshcodes for the underlying
     * method's declbring clbss nbme bnd the method's nbme.
     */
    public int hbshCode() {
        return getDeclbringClbss().getNbme().hbshCode() ^ getNbme().hbshCode();
    }

    /**
     * Returns b string describing this {@code Method}.  The string is
     * formbtted bs the method bccess modifiers, if bny, followed by
     * the method return type, followed by b spbce, followed by the
     * clbss declbring the method, followed by b period, followed by
     * the method nbme, followed by b pbrenthesized, commb-sepbrbted
     * list of the method's formbl pbrbmeter types. If the method
     * throws checked exceptions, the pbrbmeter list is followed by b
     * spbce, followed by the word throws followed by b
     * commb-sepbrbted list of the thrown exception types.
     * For exbmple:
     * <pre>
     *    public boolebn jbvb.lbng.Object.equbls(jbvb.lbng.Object)
     * </pre>
     *
     * <p>The bccess modifiers bre plbced in cbnonicbl order bs
     * specified by "The Jbvb Lbngubge Specificbtion".  This is
     * {@code public}, {@code protected} or {@code privbte} first,
     * bnd then other modifiers in the following order:
     * {@code bbstrbct}, {@code defbult}, {@code stbtic}, {@code finbl},
     * {@code synchronized}, {@code nbtive}, {@code strictfp}.
     *
     * @return b string describing this {@code Method}
     *
     * @jls 8.4.3 Method Modifiers
     */
    public String toString() {
        return shbredToString(Modifier.methodModifiers(),
                              isDefbult(),
                              pbrbmeterTypes,
                              exceptionTypes);
    }

    @Override
    void specificToStringHebder(StringBuilder sb) {
        sb.bppend(getReturnType().getTypeNbme()).bppend(' ');
        sb.bppend(getDeclbringClbss().getTypeNbme()).bppend('.');
        sb.bppend(getNbme());
    }

    /**
     * Returns b string describing this {@code Method}, including
     * type pbrbmeters.  The string is formbtted bs the method bccess
     * modifiers, if bny, followed by bn bngle-brbcketed
     * commb-sepbrbted list of the method's type pbrbmeters, if bny,
     * followed by the method's generic return type, followed by b
     * spbce, followed by the clbss declbring the method, followed by
     * b period, followed by the method nbme, followed by b
     * pbrenthesized, commb-sepbrbted list of the method's generic
     * formbl pbrbmeter types.
     *
     * If this method wbs declbred to tbke b vbribble number of
     * brguments, instebd of denoting the lbst pbrbmeter bs
     * "<tt><i>Type</i>[]</tt>", it is denoted bs
     * "<tt><i>Type</i>...</tt>".
     *
     * A spbce is used to sepbrbte bccess modifiers from one bnother
     * bnd from the type pbrbmeters or return type.  If there bre no
     * type pbrbmeters, the type pbrbmeter list is elided; if the type
     * pbrbmeter list is present, b spbce sepbrbtes the list from the
     * clbss nbme.  If the method is declbred to throw exceptions, the
     * pbrbmeter list is followed by b spbce, followed by the word
     * throws followed by b commb-sepbrbted list of the generic thrown
     * exception types.
     *
     * <p>The bccess modifiers bre plbced in cbnonicbl order bs
     * specified by "The Jbvb Lbngubge Specificbtion".  This is
     * {@code public}, {@code protected} or {@code privbte} first,
     * bnd then other modifiers in the following order:
     * {@code bbstrbct}, {@code defbult}, {@code stbtic}, {@code finbl},
     * {@code synchronized}, {@code nbtive}, {@code strictfp}.
     *
     * @return b string describing this {@code Method},
     * include type pbrbmeters
     *
     * @since 1.5
     *
     * @jls 8.4.3 Method Modifiers
     */
    @Override
    public String toGenericString() {
        return shbredToGenericString(Modifier.methodModifiers(), isDefbult());
    }

    @Override
    void specificToGenericStringHebder(StringBuilder sb) {
        Type genRetType = getGenericReturnType();
        sb.bppend(genRetType.getTypeNbme()).bppend(' ');
        sb.bppend(getDeclbringClbss().getTypeNbme()).bppend('.');
        sb.bppend(getNbme());
    }

    /**
     * Invokes the underlying method represented by this {@code Method}
     * object, on the specified object with the specified pbrbmeters.
     * Individubl pbrbmeters bre butombticblly unwrbpped to mbtch
     * primitive formbl pbrbmeters, bnd both primitive bnd reference
     * pbrbmeters bre subject to method invocbtion conversions bs
     * necessbry.
     *
     * <p>If the underlying method is stbtic, then the specified {@code obj}
     * brgument is ignored. It mby be null.
     *
     * <p>If the number of formbl pbrbmeters required by the underlying method is
     * 0, the supplied {@code brgs} brrby mby be of length 0 or null.
     *
     * <p>If the underlying method is bn instbnce method, it is invoked
     * using dynbmic method lookup bs documented in The Jbvb Lbngubge
     * Specificbtion, Second Edition, section 15.12.4.4; in pbrticulbr,
     * overriding bbsed on the runtime type of the tbrget object will occur.
     *
     * <p>If the underlying method is stbtic, the clbss thbt declbred
     * the method is initiblized if it hbs not blrebdy been initiblized.
     *
     * <p>If the method completes normblly, the vblue it returns is
     * returned to the cbller of invoke; if the vblue hbs b primitive
     * type, it is first bppropribtely wrbpped in bn object. However,
     * if the vblue hbs the type of bn brrby of b primitive type, the
     * elements of the brrby bre <i>not</i> wrbpped in objects; in
     * other words, bn brrby of primitive type is returned.  If the
     * underlying method return type is void, the invocbtion returns
     * null.
     *
     * @pbrbm obj  the object the underlying method is invoked from
     * @pbrbm brgs the brguments used for the method cbll
     * @return the result of dispbtching the method represented by
     * this object on {@code obj} with pbrbmeters
     * {@code brgs}
     *
     * @exception IllegblAccessException    if this {@code Method} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              method is inbccessible.
     * @exception IllegblArgumentException  if the method is bn
     *              instbnce method bnd the specified object brgument
     *              is not bn instbnce of the clbss or interfbce
     *              declbring the underlying method (or of b subclbss
     *              or implementor thereof); if the number of bctubl
     *              bnd formbl pbrbmeters differ; if bn unwrbpping
     *              conversion for primitive brguments fbils; or if,
     *              bfter possible unwrbpping, b pbrbmeter vblue
     *              cbnnot be converted to the corresponding formbl
     *              pbrbmeter type by b method invocbtion conversion.
     * @exception InvocbtionTbrgetException if the underlying method
     *              throws bn exception.
     * @exception NullPointerException      if the specified object is null
     *              bnd the method is bn instbnce method.
     * @exception ExceptionInInitiblizerError if the initiblizbtion
     * provoked by this method fbils.
     */
    @CbllerSensitive
    public Object invoke(Object obj, Object... brgs)
        throws IllegblAccessException, IllegblArgumentException,
           InvocbtionTbrgetException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, obj, modifiers);
            }
        }
        MethodAccessor mb = methodAccessor;             // rebd volbtile
        if (mb == null) {
            mb = bcquireMethodAccessor();
        }
        return mb.invoke(obj, brgs);
    }

    /**
     * Returns {@code true} if this method is b bridge
     * method; returns {@code fblse} otherwise.
     *
     * @return true if bnd only if this method is b bridge
     * method bs defined by the Jbvb Lbngubge Specificbtion.
     * @since 1.5
     */
    public boolebn isBridge() {
        return (getModifiers() & Modifier.BRIDGE) != 0;
    }

    /**
     * {@inheritDoc}
     * @since 1.5
     */
    @Override
    public boolebn isVbrArgs() {
        return super.isVbrArgs();
    }

    /**
     * {@inheritDoc}
     * @jls 13.1 The Form of b Binbry
     * @since 1.5
     */
    @Override
    public boolebn isSynthetic() {
        return super.isSynthetic();
    }

    /**
     * Returns {@code true} if this method is b defbult
     * method; returns {@code fblse} otherwise.
     *
     * A defbult method is b public non-bbstrbct instbnce method, thbt
     * is, b non-stbtic method with b body, declbred in bn interfbce
     * type.
     *
     * @return true if bnd only if this method is b defbult
     * method bs defined by the Jbvb Lbngubge Specificbtion.
     * @since 1.8
     */
    public boolebn isDefbult() {
        // Defbult methods bre public non-bbstrbct instbnce methods
        // declbred in bn interfbce.
        return ((getModifiers() & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) ==
                Modifier.PUBLIC) && getDeclbringClbss().isInterfbce();
    }

    // NOTE thbt there is no synchronizbtion used here. It is correct
    // (though not efficient) to generbte more thbn one MethodAccessor
    // for b given Method. However, bvoiding synchronizbtion will
    // probbbly mbke the implementbtion more scblbble.
    privbte MethodAccessor bcquireMethodAccessor() {
        // First check to see if one hbs been crebted yet, bnd tbke it
        // if so
        MethodAccessor tmp = null;
        if (root != null) tmp = root.getMethodAccessor();
        if (tmp != null) {
            methodAccessor = tmp;
        } else {
            // Otherwise fbbricbte one bnd propbgbte it up to the root
            tmp = reflectionFbctory.newMethodAccessor(this);
            setMethodAccessor(tmp);
        }

        return tmp;
    }

    // Returns MethodAccessor for this Method object, not looking up
    // the chbin to the root
    MethodAccessor getMethodAccessor() {
        return methodAccessor;
    }

    // Sets the MethodAccessor for this Method object bnd
    // (recursively) its root
    void setMethodAccessor(MethodAccessor bccessor) {
        methodAccessor = bccessor;
        // Propbgbte up
        if (root != null) {
            root.setMethodAccessor(bccessor);
        }
    }

    /**
     * Returns the defbult vblue for the bnnotbtion member represented by
     * this {@code Method} instbnce.  If the member is of b primitive type,
     * bn instbnce of the corresponding wrbpper type is returned. Returns
     * null if no defbult is bssocibted with the member, or if the method
     * instbnce does not represent b declbred member of bn bnnotbtion type.
     *
     * @return the defbult vblue for the bnnotbtion member represented
     *     by this {@code Method} instbnce.
     * @throws TypeNotPresentException if the bnnotbtion is of type
     *     {@link Clbss} bnd no definition cbn be found for the
     *     defbult clbss vblue.
     * @since  1.5
     */
    public Object getDefbultVblue() {
        if  (bnnotbtionDefbult == null)
            return null;
        Clbss<?> memberType = AnnotbtionType.invocbtionHbndlerReturnType(
            getReturnType());
        Object result = AnnotbtionPbrser.pbrseMemberVblue(
            memberType, ByteBuffer.wrbp(bnnotbtionDefbult),
            sun.misc.ShbredSecrets.getJbvbLbngAccess().
                getConstbntPool(getDeclbringClbss()),
            getDeclbringClbss());
        if (result instbnceof sun.reflect.bnnotbtion.ExceptionProxy)
            throw new AnnotbtionFormbtError("Invblid defbult: " + this);
        return result;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException  {@inheritDoc}
     * @since 1.5
     */
    public <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss) {
        return super.getAnnotbtion(bnnotbtionClbss);
    }

    /**
     * {@inheritDoc}
     * @since 1.5
     */
    public Annotbtion[] getDeclbredAnnotbtions()  {
        return super.getDeclbredAnnotbtions();
    }

    /**
     * {@inheritDoc}
     * @since 1.5
     */
    @Override
    public Annotbtion[][] getPbrbmeterAnnotbtions() {
        return shbredGetPbrbmeterAnnotbtions(pbrbmeterTypes, pbrbmeterAnnotbtions);
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    @Override
    public AnnotbtedType getAnnotbtedReturnType() {
        return getAnnotbtedReturnType0(getGenericReturnType());
    }

    @Override
    void hbndlePbrbmeterNumberMismbtch(int resultLength, int numPbrbmeters) {
        throw new AnnotbtionFormbtError("Pbrbmeter bnnotbtions don't mbtch number of pbrbmeters");
    }
}
