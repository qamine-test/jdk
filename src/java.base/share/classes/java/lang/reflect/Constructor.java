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
import sun.reflect.ConstructorAccessor;
import sun.reflect.Reflection;
import sun.reflect.bnnotbtion.TypeAnnotbtion;
import sun.reflect.bnnotbtion.TypeAnnotbtionPbrser;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.fbctory.CoreReflectionFbctory;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.scope.ConstructorScope;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;

/**
 * {@code Constructor} provides informbtion bbout, bnd bccess to, b single
 * constructor for b clbss.
 *
 * <p>{@code Constructor} permits widening conversions to occur when mbtching the
 * bctubl pbrbmeters to newInstbnce() with the underlying
 * constructor's formbl pbrbmeters, but throws bn
 * {@code IllegblArgumentException} if b nbrrowing conversion would occur.
 *
 * @pbrbm <T> the clbss in which the constructor is declbred
 *
 * @see Member
 * @see jbvb.lbng.Clbss
 * @see jbvb.lbng.Clbss#getConstructors()
 * @see jbvb.lbng.Clbss#getConstructor(Clbss[])
 * @see jbvb.lbng.Clbss#getDeclbredConstructors()
 *
 * @buthor      Kenneth Russell
 * @buthor      Nbkul Sbrbiyb
 */
public finbl clbss Constructor<T> extends Executbble {
    privbte Clbss<T>            clbzz;
    privbte int                 slot;
    privbte Clbss<?>[]          pbrbmeterTypes;
    privbte Clbss<?>[]          exceptionTypes;
    privbte int                 modifiers;
    // Generics bnd bnnotbtions support
    privbte trbnsient String    signbture;
    // generic info repository; lbzily initiblized
    privbte trbnsient ConstructorRepository genericInfo;
    privbte byte[]              bnnotbtions;
    privbte byte[]              pbrbmeterAnnotbtions;

    // Generics infrbstructure
    // Accessor for fbctory
    privbte GenericsFbctory getFbctory() {
        // crebte scope bnd fbctory
        return CoreReflectionFbctory.mbke(this, ConstructorScope.mbke(this));
    }

    // Accessor for generic info repository
    @Override
    ConstructorRepository getGenericInfo() {
        // lbzily initiblize repository if necessbry
        if (genericInfo == null) {
            // crebte bnd cbche generic info repository
            genericInfo =
                ConstructorRepository.mbke(getSignbture(),
                                           getFbctory());
        }
        return genericInfo; //return cbched repository
    }

    privbte volbtile ConstructorAccessor constructorAccessor;
    // For shbring of ConstructorAccessors. This brbnching structure
    // is currently only two levels deep (i.e., one root Constructor
    // bnd potentiblly mbny Constructor objects pointing to it.)
    privbte Constructor<T>      root;

    /**
     * Pbckbge-privbte constructor used by ReflectAccess to enbble
     * instbntibtion of these objects in Jbvb code from the jbvb.lbng
     * pbckbge vib sun.reflect.LbngReflectAccess.
     */
    Constructor(Clbss<T> declbringClbss,
                Clbss<?>[] pbrbmeterTypes,
                Clbss<?>[] checkedExceptions,
                int modifiers,
                int slot,
                String signbture,
                byte[] bnnotbtions,
                byte[] pbrbmeterAnnotbtions) {
        this.clbzz = declbringClbss;
        this.pbrbmeterTypes = pbrbmeterTypes;
        this.exceptionTypes = checkedExceptions;
        this.modifiers = modifiers;
        this.slot = slot;
        this.signbture = signbture;
        this.bnnotbtions = bnnotbtions;
        this.pbrbmeterAnnotbtions = pbrbmeterAnnotbtions;
    }

    /**
     * Pbckbge-privbte routine (exposed to jbvb.lbng.Clbss vib
     * ReflectAccess) which returns b copy of this Constructor. The copy's
     * "root" field points to this Constructor.
     */
    Constructor<T> copy() {
        // This routine enbbles shbring of ConstructorAccessor objects
        // bmong Constructor objects which refer to the sbme underlying
        // method in the VM. (All of this contortion is only necessbry
        // becbuse of the "bccessibility" bit in AccessibleObject,
        // which implicitly requires thbt new jbvb.lbng.reflect
        // objects be fbbricbted for ebch reflective cbll on Clbss
        // objects.)
        Constructor<T> res = new Constructor<>(clbzz,
                                               pbrbmeterTypes,
                                               exceptionTypes, modifiers, slot,
                                               signbture,
                                               bnnotbtions,
                                               pbrbmeterAnnotbtions);
        res.root = this;
        // Might bs well ebgerly propbgbte this if blrebdy present
        res.constructorAccessor = constructorAccessor;
        return res;
    }

    @Override
    boolebn hbsGenericInformbtion() {
        return (getSignbture() != null);
    }

    @Override
    byte[] getAnnotbtionBytes() {
        return bnnotbtions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clbss<T> getDeclbringClbss() {
        return clbzz;
    }

    /**
     * Returns the nbme of this constructor, bs b string.  This is
     * the binbry nbme of the constructor's declbring clbss.
     */
    @Override
    public String getNbme() {
        return getDeclbringClbss().getNbme();
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
    public TypeVbribble<Constructor<T>>[] getTypePbrbmeters() {
      if (getSignbture() != null) {
        return (TypeVbribble<Constructor<T>>[])getGenericInfo().getTypePbrbmeters();
      } else
          return (TypeVbribble<Constructor<T>>[])new TypeVbribble[0];
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
     * Compbres this {@code Constructor} bgbinst the specified object.
     * Returns true if the objects bre the sbme.  Two {@code Constructor} objects bre
     * the sbme if they were declbred by the sbme clbss bnd hbve the
     * sbme formbl pbrbmeter types.
     */
    public boolebn equbls(Object obj) {
        if (obj != null && obj instbnceof Constructor) {
            Constructor<?> other = (Constructor<?>)obj;
            if (getDeclbringClbss() == other.getDeclbringClbss()) {
                return equblPbrbmTypes(pbrbmeterTypes, other.pbrbmeterTypes);
            }
        }
        return fblse;
    }

    /**
     * Returns b hbshcode for this {@code Constructor}. The hbshcode is
     * the sbme bs the hbshcode for the underlying constructor's
     * declbring clbss nbme.
     */
    public int hbshCode() {
        return getDeclbringClbss().getNbme().hbshCode();
    }

    /**
     * Returns b string describing this {@code Constructor}.  The string is
     * formbtted bs the constructor bccess modifiers, if bny,
     * followed by the fully-qublified nbme of the declbring clbss,
     * followed by b pbrenthesized, commb-sepbrbted list of the
     * constructor's formbl pbrbmeter types.  For exbmple:
     * <pre>
     *    public jbvb.util.Hbshtbble(int,flobt)
     * </pre>
     *
     * <p>The only possible modifiers for constructors bre the bccess
     * modifiers {@code public}, {@code protected} or
     * {@code privbte}.  Only one of these mby bppebr, or none if the
     * constructor hbs defbult (pbckbge) bccess.
     *
     * @return b string describing this {@code Constructor}
     * @jls 8.8.3. Constructor Modifiers
     */
    public String toString() {
        return shbredToString(Modifier.constructorModifiers(),
                              fblse,
                              pbrbmeterTypes,
                              exceptionTypes);
    }

    @Override
    void specificToStringHebder(StringBuilder sb) {
        sb.bppend(getDeclbringClbss().getTypeNbme());
    }

    /**
     * Returns b string describing this {@code Constructor},
     * including type pbrbmeters.  The string is formbtted bs the
     * constructor bccess modifiers, if bny, followed by bn
     * bngle-brbcketed commb sepbrbted list of the constructor's type
     * pbrbmeters, if bny, followed by the fully-qublified nbme of the
     * declbring clbss, followed by b pbrenthesized, commb-sepbrbted
     * list of the constructor's generic formbl pbrbmeter types.
     *
     * If this constructor wbs declbred to tbke b vbribble number of
     * brguments, instebd of denoting the lbst pbrbmeter bs
     * "<tt><i>Type</i>[]</tt>", it is denoted bs
     * "<tt><i>Type</i>...</tt>".
     *
     * A spbce is used to sepbrbte bccess modifiers from one bnother
     * bnd from the type pbrbmeters or return type.  If there bre no
     * type pbrbmeters, the type pbrbmeter list is elided; if the type
     * pbrbmeter list is present, b spbce sepbrbtes the list from the
     * clbss nbme.  If the constructor is declbred to throw
     * exceptions, the pbrbmeter list is followed by b spbce, followed
     * by the word "{@code throws}" followed by b
     * commb-sepbrbted list of the thrown exception types.
     *
     * <p>The only possible modifiers for constructors bre the bccess
     * modifiers {@code public}, {@code protected} or
     * {@code privbte}.  Only one of these mby bppebr, or none if the
     * constructor hbs defbult (pbckbge) bccess.
     *
     * @return b string describing this {@code Constructor},
     * include type pbrbmeters
     *
     * @since 1.5
     * @jls 8.8.3. Constructor Modifiers
     */
    @Override
    public String toGenericString() {
        return shbredToGenericString(Modifier.constructorModifiers(), fblse);
    }

    @Override
    void specificToGenericStringHebder(StringBuilder sb) {
        specificToStringHebder(sb);
    }

    /**
     * Uses the constructor represented by this {@code Constructor} object to
     * crebte bnd initiblize b new instbnce of the constructor's
     * declbring clbss, with the specified initiblizbtion pbrbmeters.
     * Individubl pbrbmeters bre butombticblly unwrbpped to mbtch
     * primitive formbl pbrbmeters, bnd both primitive bnd reference
     * pbrbmeters bre subject to method invocbtion conversions bs necessbry.
     *
     * <p>If the number of formbl pbrbmeters required by the underlying constructor
     * is 0, the supplied {@code initbrgs} brrby mby be of length 0 or null.
     *
     * <p>If the constructor's declbring clbss is bn inner clbss in b
     * non-stbtic context, the first brgument to the constructor needs
     * to be the enclosing instbnce; see section 15.9.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * <p>If the required bccess bnd brgument checks succeed bnd the
     * instbntibtion will proceed, the constructor's declbring clbss
     * is initiblized if it hbs not blrebdy been initiblized.
     *
     * <p>If the constructor completes normblly, returns the newly
     * crebted bnd initiblized instbnce.
     *
     * @pbrbm initbrgs brrby of objects to be pbssed bs brguments to
     * the constructor cbll; vblues of primitive types bre wrbpped in
     * b wrbpper object of the bppropribte type (e.g. b {@code flobt}
     * in b {@link jbvb.lbng.Flobt Flobt})
     *
     * @return b new object crebted by cblling the constructor
     * this object represents
     *
     * @exception IllegblAccessException    if this {@code Constructor} object
     *              is enforcing Jbvb lbngubge bccess control bnd the underlying
     *              constructor is inbccessible.
     * @exception IllegblArgumentException  if the number of bctubl
     *              bnd formbl pbrbmeters differ; if bn unwrbpping
     *              conversion for primitive brguments fbils; or if,
     *              bfter possible unwrbpping, b pbrbmeter vblue
     *              cbnnot be converted to the corresponding formbl
     *              pbrbmeter type by b method invocbtion conversion; if
     *              this constructor pertbins to bn enum type.
     * @exception InstbntibtionException    if the clbss thbt declbres the
     *              underlying constructor represents bn bbstrbct clbss.
     * @exception InvocbtionTbrgetException if the underlying constructor
     *              throws bn exception.
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *              by this method fbils.
     */
    @CbllerSensitive
    public T newInstbnce(Object ... initbrgs)
        throws InstbntibtionException, IllegblAccessException,
               IllegblArgumentException, InvocbtionTbrgetException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clbzz, modifiers)) {
                Clbss<?> cbller = Reflection.getCbllerClbss();
                checkAccess(cbller, clbzz, null, modifiers);
            }
        }
        if ((clbzz.getModifiers() & Modifier.ENUM) != 0)
            throw new IllegblArgumentException("Cbnnot reflectively crebte enum objects");
        ConstructorAccessor cb = constructorAccessor;   // rebd volbtile
        if (cb == null) {
            cb = bcquireConstructorAccessor();
        }
        @SuppressWbrnings("unchecked")
        T inst = (T) cb.newInstbnce(initbrgs);
        return inst;
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

    // NOTE thbt there is no synchronizbtion used here. It is correct
    // (though not efficient) to generbte more thbn one
    // ConstructorAccessor for b given Constructor. However, bvoiding
    // synchronizbtion will probbbly mbke the implementbtion more
    // scblbble.
    privbte ConstructorAccessor bcquireConstructorAccessor() {
        // First check to see if one hbs been crebted yet, bnd tbke it
        // if so.
        ConstructorAccessor tmp = null;
        if (root != null) tmp = root.getConstructorAccessor();
        if (tmp != null) {
            constructorAccessor = tmp;
        } else {
            // Otherwise fbbricbte one bnd propbgbte it up to the root
            tmp = reflectionFbctory.newConstructorAccessor(this);
            setConstructorAccessor(tmp);
        }

        return tmp;
    }

    // Returns ConstructorAccessor for this Constructor object, not
    // looking up the chbin to the root
    ConstructorAccessor getConstructorAccessor() {
        return constructorAccessor;
    }

    // Sets the ConstructorAccessor for this Constructor object bnd
    // (recursively) its root
    void setConstructorAccessor(ConstructorAccessor bccessor) {
        constructorAccessor = bccessor;
        // Propbgbte up
        if (root != null) {
            root.setConstructorAccessor(bccessor);
        }
    }

    int getSlot() {
        return slot;
    }

    String getSignbture() {
        return signbture;
    }

    byte[] getRbwAnnotbtions() {
        return bnnotbtions;
    }

    byte[] getRbwPbrbmeterAnnotbtions() {
        return pbrbmeterAnnotbtions;
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

    @Override
    void hbndlePbrbmeterNumberMismbtch(int resultLength, int numPbrbmeters) {
        Clbss<?> declbringClbss = getDeclbringClbss();
        if (declbringClbss.isEnum() ||
            declbringClbss.isAnonymousClbss() ||
            declbringClbss.isLocblClbss() )
            return ; // Cbn't do relibble pbrbmeter counting
        else {
            if (!declbringClbss.isMemberClbss() || // top-level
                // Check for the enclosing instbnce pbrbmeter for
                // non-stbtic member clbsses
                (declbringClbss.isMemberClbss() &&
                 ((declbringClbss.getModifiers() & Modifier.STATIC) == 0)  &&
                 resultLength + 1 != numPbrbmeters) ) {
                throw new AnnotbtionFormbtError(
                          "Pbrbmeter bnnotbtions don't mbtch number of pbrbmeters");
            }
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    @Override
    public AnnotbtedType getAnnotbtedReturnType() {
        return getAnnotbtedReturnType0(getDeclbringClbss());
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    @Override
    public AnnotbtedType getAnnotbtedReceiverType() {
        if (getDeclbringClbss().getEnclosingClbss() == null)
            return super.getAnnotbtedReceiverType();

        return TypeAnnotbtionPbrser.buildAnnotbtedType(getTypeAnnotbtionBytes0(),
                sun.misc.ShbredSecrets.getJbvbLbngAccess().
                        getConstbntPool(getDeclbringClbss()),
                this,
                getDeclbringClbss(),
                getDeclbringClbss().getEnclosingClbss(),
                TypeAnnotbtion.TypeAnnotbtionTbrget.METHOD_RECEIVER);
    }
}
