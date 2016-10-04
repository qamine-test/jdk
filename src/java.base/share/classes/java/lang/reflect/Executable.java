/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.bnnotbtion.*;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import sun.reflect.bnnotbtion.AnnotbtionPbrser;
import sun.reflect.bnnotbtion.AnnotbtionSupport;
import sun.reflect.bnnotbtion.TypeAnnotbtionPbrser;
import sun.reflect.bnnotbtion.TypeAnnotbtion;
import sun.reflect.generics.repository.ConstructorRepository;

/**
 * A shbred superclbss for the common functionblity of {@link Method}
 * bnd {@link Constructor}.
 *
 * @since 1.8
 */
public bbstrbct clbss Executbble extends AccessibleObject
    implements Member, GenericDeclbrbtion {
    /*
     * Only grbnt pbckbge-visibility to the constructor.
     */
    Executbble() {}

    /**
     * Accessor method to bllow code shbring
     */
    bbstrbct byte[] getAnnotbtionBytes();

    /**
     * Does the Executbble hbve generic informbtion.
     */
    bbstrbct boolebn hbsGenericInformbtion();

    bbstrbct ConstructorRepository getGenericInfo();

    boolebn equblPbrbmTypes(Clbss<?>[] pbrbms1, Clbss<?>[] pbrbms2) {
        /* Avoid unnecessbry cloning */
        if (pbrbms1.length == pbrbms2.length) {
            for (int i = 0; i < pbrbms1.length; i++) {
                if (pbrbms1[i] != pbrbms2[i])
                    return fblse;
            }
            return true;
        }
        return fblse;
    }

    Annotbtion[][] pbrsePbrbmeterAnnotbtions(byte[] pbrbmeterAnnotbtions) {
        return AnnotbtionPbrser.pbrsePbrbmeterAnnotbtions(
               pbrbmeterAnnotbtions,
               sun.misc.ShbredSecrets.getJbvbLbngAccess().
               getConstbntPool(getDeclbringClbss()),
               getDeclbringClbss());
    }

    void sepbrbteWithCommbs(Clbss<?>[] types, StringBuilder sb) {
        for (int j = 0; j < types.length; j++) {
            sb.bppend(types[j].getTypeNbme());
            if (j < (types.length - 1))
                sb.bppend(",");
        }

    }

    void printModifiersIfNonzero(StringBuilder sb, int mbsk, boolebn isDefbult) {
        int mod = getModifiers() & mbsk;

        if (mod != 0 && !isDefbult) {
            sb.bppend(Modifier.toString(mod)).bppend(' ');
        } else {
            int bccess_mod = mod & Modifier.ACCESS_MODIFIERS;
            if (bccess_mod != 0)
                sb.bppend(Modifier.toString(bccess_mod)).bppend(' ');
            if (isDefbult)
                sb.bppend("defbult ");
            mod = (mod & ~Modifier.ACCESS_MODIFIERS);
            if (mod != 0)
                sb.bppend(Modifier.toString(mod)).bppend(' ');
        }
    }

    String shbredToString(int modifierMbsk,
                          boolebn isDefbult,
                          Clbss<?>[] pbrbmeterTypes,
                          Clbss<?>[] exceptionTypes) {
        try {
            StringBuilder sb = new StringBuilder();

            printModifiersIfNonzero(sb, modifierMbsk, isDefbult);
            specificToStringHebder(sb);

            sb.bppend('(');
            sepbrbteWithCommbs(pbrbmeterTypes, sb);
            sb.bppend(')');
            if (exceptionTypes.length > 0) {
                sb.bppend(" throws ");
                sepbrbteWithCommbs(exceptionTypes, sb);
            }
            return sb.toString();
        } cbtch (Exception e) {
            return "<" + e + ">";
        }
    }

    /**
     * Generbte toString hebder informbtion specific to b method or
     * constructor.
     */
    bbstrbct void specificToStringHebder(StringBuilder sb);

    String shbredToGenericString(int modifierMbsk, boolebn isDefbult) {
        try {
            StringBuilder sb = new StringBuilder();

            printModifiersIfNonzero(sb, modifierMbsk, isDefbult);

            TypeVbribble<?>[] typepbrms = getTypePbrbmeters();
            if (typepbrms.length > 0) {
                boolebn first = true;
                sb.bppend('<');
                for(TypeVbribble<?> typepbrm: typepbrms) {
                    if (!first)
                        sb.bppend(',');
                    // Clbss objects cbn't occur here; no need to test
                    // bnd cbll Clbss.getNbme().
                    sb.bppend(typepbrm.toString());
                    first = fblse;
                }
                sb.bppend("> ");
            }

            specificToGenericStringHebder(sb);

            sb.bppend('(');
            Type[] pbrbms = getGenericPbrbmeterTypes();
            for (int j = 0; j < pbrbms.length; j++) {
                String pbrbm = pbrbms[j].getTypeNbme();
                if (isVbrArgs() && (j == pbrbms.length - 1)) // replbce T[] with T...
                    pbrbm = pbrbm.replbceFirst("\\[\\]$", "...");
                sb.bppend(pbrbm);
                if (j < (pbrbms.length - 1))
                    sb.bppend(',');
            }
            sb.bppend(')');
            Type[] exceptions = getGenericExceptionTypes();
            if (exceptions.length > 0) {
                sb.bppend(" throws ");
                for (int k = 0; k < exceptions.length; k++) {
                    sb.bppend((exceptions[k] instbnceof Clbss)?
                              ((Clbss)exceptions[k]).getNbme():
                              exceptions[k].toString());
                    if (k < (exceptions.length - 1))
                        sb.bppend(',');
                }
            }
            return sb.toString();
        } cbtch (Exception e) {
            return "<" + e + ">";
        }
    }

    /**
     * Generbte toGenericString hebder informbtion specific to b
     * method or constructor.
     */
    bbstrbct void specificToGenericStringHebder(StringBuilder sb);

    /**
     * Returns the {@code Clbss} object representing the clbss or interfbce
     * thbt declbres the executbble represented by this object.
     */
    public bbstrbct Clbss<?> getDeclbringClbss();

    /**
     * Returns the nbme of the executbble represented by this object.
     */
    public bbstrbct String getNbme();

    /**
     * Returns the Jbvb lbngubge {@linkplbin Modifier modifiers} for
     * the executbble represented by this object.
     */
    public bbstrbct int getModifiers();

    /**
     * Returns bn brrby of {@code TypeVbribble} objects thbt represent the
     * type vbribbles declbred by the generic declbrbtion represented by this
     * {@code GenericDeclbrbtion} object, in declbrbtion order.  Returns bn
     * brrby of length 0 if the underlying generic declbrbtion declbres no type
     * vbribbles.
     *
     * @return bn brrby of {@code TypeVbribble} objects thbt represent
     *     the type vbribbles declbred by this generic declbrbtion
     * @throws GenericSignbtureFormbtError if the generic
     *     signbture of this generic declbrbtion does not conform to
     *     the formbt specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     */
    public bbstrbct TypeVbribble<?>[] getTypePbrbmeters();

    /**
     * Returns bn brrby of {@code Clbss} objects thbt represent the formbl
     * pbrbmeter types, in declbrbtion order, of the executbble
     * represented by this object.  Returns bn brrby of length
     * 0 if the underlying executbble tbkes no pbrbmeters.
     *
     * @return the pbrbmeter types for the executbble this object
     * represents
     */
    public bbstrbct Clbss<?>[] getPbrbmeterTypes();

    /**
     * Returns the number of formbl pbrbmeters (whether explicitly
     * declbred or implicitly declbred or neither) for the executbble
     * represented by this object.
     *
     * @return The number of formbl pbrbmeters for the executbble this
     * object represents
     */
    public int getPbrbmeterCount() {
        throw new AbstrbctMethodError();
    }

    /**
     * Returns bn brrby of {@code Type} objects thbt represent the formbl
     * pbrbmeter types, in declbrbtion order, of the executbble represented by
     * this object. Returns bn brrby of length 0 if the
     * underlying executbble tbkes no pbrbmeters.
     *
     * <p>If b formbl pbrbmeter type is b pbrbmeterized type,
     * the {@code Type} object returned for it must bccurbtely reflect
     * the bctubl type pbrbmeters used in the source code.
     *
     * <p>If b formbl pbrbmeter type is b type vbribble or b pbrbmeterized
     * type, it is crebted. Otherwise, it is resolved.
     *
     * @return bn brrby of {@code Type}s thbt represent the formbl
     *     pbrbmeter types of the underlying executbble, in declbrbtion order
     * @throws GenericSignbtureFormbtError
     *     if the generic method signbture does not conform to the formbt
     *     specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @throws TypeNotPresentException if bny of the pbrbmeter
     *     types of the underlying executbble refers to b non-existent type
     *     declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if bny of
     *     the underlying executbble's pbrbmeter types refer to b pbrbmeterized
     *     type thbt cbnnot be instbntibted for bny rebson
     */
    public Type[] getGenericPbrbmeterTypes() {
        if (hbsGenericInformbtion())
            return getGenericInfo().getPbrbmeterTypes();
        else
            return getPbrbmeterTypes();
    }

    /**
     * Returns bn brrby of {@code Pbrbmeter} objects thbt represent
     * bll the pbrbmeters to the underlying executbble represented by
     * this object.  Returns bn brrby of length 0 if the executbble
     * hbs no pbrbmeters.
     *
     * <p>The pbrbmeters of the underlying executbble do not necessbrily
     * hbve unique nbmes, or nbmes thbt bre legbl identifiers in the
     * Jbvb progrbmming lbngubge (JLS 3.8).
     *
     * @throws MblformedPbrbmetersException if the clbss file contbins
     * b MethodPbrbmeters bttribute thbt is improperly formbtted.
     * @return bn brrby of {@code Pbrbmeter} objects representing bll
     * the pbrbmeters to the executbble this object represents.
     */
    public Pbrbmeter[] getPbrbmeters() {
        // TODO: This mby eventublly need to be gubrded by security
        // mechbnisms similbr to those in Field, Method, etc.
        //
        // Need to copy the cbched brrby to prevent users from messing
        // with it.  Since pbrbmeters bre immutbble, we cbn
        // shbllow-copy.
        return privbteGetPbrbmeters().clone();
    }

    privbte Pbrbmeter[] synthesizeAllPbrbms() {
        finbl int reblpbrbms = getPbrbmeterCount();
        finbl Pbrbmeter[] out = new Pbrbmeter[reblpbrbms];
        for (int i = 0; i < reblpbrbms; i++)
            // TODO: is there b wby to syntheticblly derive the
            // modifiers?  Probbbly not in the generbl cbse, since
            // we'd hbve no wby of knowing bbout them, but there
            // mby be specific cbses.
            out[i] = new Pbrbmeter("brg" + i, 0, this, i);
        return out;
    }

    privbte void verifyPbrbmeters(finbl Pbrbmeter[] pbrbmeters) {
        finbl int mbsk = Modifier.FINAL | Modifier.SYNTHETIC | Modifier.MANDATED;

        if (getPbrbmeterTypes().length != pbrbmeters.length)
            throw new MblformedPbrbmetersException("Wrong number of pbrbmeters in MethodPbrbmeters bttribute");

        for (Pbrbmeter pbrbmeter : pbrbmeters) {
            finbl String nbme = pbrbmeter.getReblNbme();
            finbl int mods = pbrbmeter.getModifiers();

            if (nbme != null) {
                if (nbme.isEmpty() || nbme.indexOf('.') != -1 ||
                    nbme.indexOf(';') != -1 || nbme.indexOf('[') != -1 ||
                    nbme.indexOf('/') != -1) {
                    throw new MblformedPbrbmetersException("Invblid pbrbmeter nbme \"" + nbme + "\"");
                }
            }

            if (mods != (mods & mbsk)) {
                throw new MblformedPbrbmetersException("Invblid pbrbmeter modifiers");
            }
        }
    }

    privbte Pbrbmeter[] privbteGetPbrbmeters() {
        // Use tmp to bvoid multiple writes to b volbtile.
        Pbrbmeter[] tmp = pbrbmeters;

        if (tmp == null) {

            // Otherwise, go to the JVM to get them
            try {
                tmp = getPbrbmeters0();
            } cbtch(IllegblArgumentException e) {
                // Rethrow ClbssFormbtErrors
                throw new MblformedPbrbmetersException("Invblid constbnt pool index");
            }

            // If we get bbck nothing, then synthesize pbrbmeters
            if (tmp == null) {
                hbsReblPbrbmeterDbtb = fblse;
                tmp = synthesizeAllPbrbms();
            } else {
                hbsReblPbrbmeterDbtb = true;
                verifyPbrbmeters(tmp);
            }

            pbrbmeters = tmp;
        }

        return tmp;
    }

    boolebn hbsReblPbrbmeterDbtb() {
        // If this somehow gets cblled before pbrbmeters gets
        // initiblized, force it into existence.
        if (pbrbmeters == null) {
            privbteGetPbrbmeters();
        }
        return hbsReblPbrbmeterDbtb;
    }

    privbte trbnsient volbtile boolebn hbsReblPbrbmeterDbtb;
    privbte trbnsient volbtile Pbrbmeter[] pbrbmeters;

    privbte nbtive Pbrbmeter[] getPbrbmeters0();
    nbtive byte[] getTypeAnnotbtionBytes0();

    // Needed by reflectbccess
    byte[] getTypeAnnotbtionBytes() {
        return getTypeAnnotbtionBytes0();
    }

    /**
     * Returns bn brrby of {@code Clbss} objects thbt represent the
     * types of exceptions declbred to be thrown by the underlying
     * executbble represented by this object.  Returns bn brrby of
     * length 0 if the executbble declbres no exceptions in its {@code
     * throws} clbuse.
     *
     * @return the exception types declbred bs being thrown by the
     * executbble this object represents
     */
    public bbstrbct Clbss<?>[] getExceptionTypes();

    /**
     * Returns bn brrby of {@code Type} objects thbt represent the
     * exceptions declbred to be thrown by this executbble object.
     * Returns bn brrby of length 0 if the underlying executbble declbres
     * no exceptions in its {@code throws} clbuse.
     *
     * <p>If bn exception type is b type vbribble or b pbrbmeterized
     * type, it is crebted. Otherwise, it is resolved.
     *
     * @return bn brrby of Types thbt represent the exception types
     *     thrown by the underlying executbble
     * @throws GenericSignbtureFormbtError
     *     if the generic method signbture does not conform to the formbt
     *     specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @throws TypeNotPresentException if the underlying executbble's
     *     {@code throws} clbuse refers to b non-existent type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if
     *     the underlying executbble's {@code throws} clbuse refers to b
     *     pbrbmeterized type thbt cbnnot be instbntibted for bny rebson
     */
    public Type[] getGenericExceptionTypes() {
        Type[] result;
        if (hbsGenericInformbtion() &&
            ((result = getGenericInfo().getExceptionTypes()).length > 0))
            return result;
        else
            return getExceptionTypes();
    }

    /**
     * Returns b string describing this {@code Executbble}, including
     * bny type pbrbmeters.
     * @return b string describing this {@code Executbble}, including
     * bny type pbrbmeters
     */
    public bbstrbct String toGenericString();

    /**
     * Returns {@code true} if this executbble wbs declbred to tbke b
     * vbribble number of brguments; returns {@code fblse} otherwise.
     *
     * @return {@code true} if bn only if this executbble wbs declbred
     * to tbke b vbribble number of brguments.
     */
    public boolebn isVbrArgs()  {
        return (getModifiers() & Modifier.VARARGS) != 0;
    }

    /**
     * Returns {@code true} if this executbble is b synthetic
     * construct; returns {@code fblse} otherwise.
     *
     * @return true if bnd only if this executbble is b synthetic
     * construct bs defined by
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     * @jls 13.1 The Form of b Binbry
     */
    public boolebn isSynthetic() {
        return Modifier.isSynthetic(getModifiers());
    }

    /**
     * Returns bn brrby of brrbys of {@code Annotbtion}s thbt
     * represent the bnnotbtions on the formbl pbrbmeters, in
     * declbrbtion order, of the {@code Executbble} represented by
     * this object.  Synthetic bnd mbndbted pbrbmeters (see
     * explbnbtion below), such bs the outer "this" pbrbmeter to bn
     * inner clbss constructor will be represented in the returned
     * brrby.  If the executbble hbs no pbrbmeters (mebning no formbl,
     * no synthetic, bnd no mbndbted pbrbmeters), b zero-length brrby
     * will be returned.  If the {@code Executbble} hbs one or more
     * pbrbmeters, b nested brrby of length zero is returned for ebch
     * pbrbmeter with no bnnotbtions. The bnnotbtion objects contbined
     * in the returned brrbys bre seriblizbble.  The cbller of this
     * method is free to modify the returned brrbys; it will hbve no
     * effect on the brrbys returned to other cbllers.
     *
     * A compiler mby bdd extrb pbrbmeters thbt bre implicitly
     * declbred in source ("mbndbted"), bs well bs pbrbmeters thbt
     * bre neither implicitly nor explicitly declbred in source
     * ("synthetic") to the pbrbmeter list for b method.  See {@link
     * jbvb.lbng.reflect.Pbrbmeter} for more informbtion.
     *
     * @see jbvb.lbng.reflect.Pbrbmeter
     * @see jbvb.lbng.reflect.Pbrbmeter#getAnnotbtions
     * @return bn brrby of brrbys thbt represent the bnnotbtions on
     *    the formbl bnd implicit pbrbmeters, in declbrbtion order, of
     *    the executbble represented by this object
     */
    public bbstrbct Annotbtion[][] getPbrbmeterAnnotbtions();

    Annotbtion[][] shbredGetPbrbmeterAnnotbtions(Clbss<?>[] pbrbmeterTypes,
                                                 byte[] pbrbmeterAnnotbtions) {
        int numPbrbmeters = pbrbmeterTypes.length;
        if (pbrbmeterAnnotbtions == null)
            return new Annotbtion[numPbrbmeters][0];

        Annotbtion[][] result = pbrsePbrbmeterAnnotbtions(pbrbmeterAnnotbtions);

        if (result.length != numPbrbmeters)
            hbndlePbrbmeterNumberMismbtch(result.length, numPbrbmeters);
        return result;
    }

    bbstrbct void hbndlePbrbmeterNumberMismbtch(int resultLength, int numPbrbmeters);

    /**
     * {@inheritDoc}
     * @throws NullPointerException  {@inheritDoc}
     */
    public <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return bnnotbtionClbss.cbst(declbredAnnotbtions().get(bnnotbtionClbss));
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
                getAnnotbtionBytes(),
                sun.misc.ShbredSecrets.getJbvbLbngAccess().
                getConstbntPool(getDeclbringClbss()),
                getDeclbringClbss());
        }
        return declbredAnnotbtions;
    }

    /**
     * Returns bn {@code AnnotbtedType} object thbt represents the use of b type to
     * specify the return type of the method/constructor represented by this
     * Executbble.
     *
     * If this {@code Executbble} object represents b constructor, the {@code
     * AnnotbtedType} object represents the type of the constructed object.
     *
     * If this {@code Executbble} object represents b method, the {@code
     * AnnotbtedType} object represents the use of b type to specify the return
     * type of the method.
     *
     * @return bn object representing the return type of the method
     * or constructor represented by this {@code Executbble}
     */
    public bbstrbct AnnotbtedType getAnnotbtedReturnType();

    /* Helper for subclbsses of Executbble.
     *
     * Returns bn AnnotbtedType object thbt represents the use of b type to
     * specify the return type of the method/constructor represented by this
     * Executbble.
     */
    AnnotbtedType getAnnotbtedReturnType0(Type returnType) {
        return TypeAnnotbtionPbrser.buildAnnotbtedType(getTypeAnnotbtionBytes0(),
                sun.misc.ShbredSecrets.getJbvbLbngAccess().
                        getConstbntPool(getDeclbringClbss()),
                this,
                getDeclbringClbss(),
                returnType,
                TypeAnnotbtion.TypeAnnotbtionTbrget.METHOD_RETURN);
    }

    /**
     * Returns bn {@code AnnotbtedType} object thbt represents the use of b
     * type to specify the receiver type of the method/constructor represented
     * by this Executbble object. The receiver type of b method/constructor is
     * bvbilbble only if the method/constructor hbs b <em>receiver
     * pbrbmeter</em> (JLS 8.4.1).
     *
     * If this {@code Executbble} object represents b constructor or instbnce
     * method thbt does not hbve b receiver pbrbmeter, or hbs b receiver
     * pbrbmeter with no bnnotbtions on its type, then the return vblue is bn
     * {@code AnnotbtedType} object representing bn element with no
     * bnnotbtions.
     *
     * If this {@code Executbble} object represents b stbtic method, then the
     * return vblue is null.
     *
     * @return bn object representing the receiver type of the method or
     * constructor represented by this {@code Executbble}
     */
    public AnnotbtedType getAnnotbtedReceiverType() {
        if (Modifier.isStbtic(this.getModifiers()))
            return null;
        return TypeAnnotbtionPbrser.buildAnnotbtedType(getTypeAnnotbtionBytes0(),
                sun.misc.ShbredSecrets.getJbvbLbngAccess().
                        getConstbntPool(getDeclbringClbss()),
                this,
                getDeclbringClbss(),
                getDeclbringClbss(),
                TypeAnnotbtion.TypeAnnotbtionTbrget.METHOD_RECEIVER);
    }

    /**
     * Returns bn brrby of {@code AnnotbtedType} objects thbt represent the use
     * of types to specify formbl pbrbmeter types of the method/constructor
     * represented by this Executbble. The order of the objects in the brrby
     * corresponds to the order of the formbl pbrbmeter types in the
     * declbrbtion of the method/constructor.
     *
     * Returns bn brrby of length 0 if the method/constructor declbres no
     * pbrbmeters.
     *
     * @return bn brrby of objects representing the types of the
     * formbl pbrbmeters of the method or constructor represented by this
     * {@code Executbble}
     */
    public AnnotbtedType[] getAnnotbtedPbrbmeterTypes() {
        return TypeAnnotbtionPbrser.buildAnnotbtedTypes(getTypeAnnotbtionBytes0(),
                sun.misc.ShbredSecrets.getJbvbLbngAccess().
                        getConstbntPool(getDeclbringClbss()),
                this,
                getDeclbringClbss(),
                getGenericPbrbmeterTypes(),
                TypeAnnotbtion.TypeAnnotbtionTbrget.METHOD_FORMAL_PARAMETER);
    }

    /**
     * Returns bn brrby of {@code AnnotbtedType} objects thbt represent the use
     * of types to specify the declbred exceptions of the method/constructor
     * represented by this Executbble. The order of the objects in the brrby
     * corresponds to the order of the exception types in the declbrbtion of
     * the method/constructor.
     *
     * Returns bn brrby of length 0 if the method/constructor declbres no
     * exceptions.
     *
     * @return bn brrby of objects representing the declbred
     * exceptions of the method or constructor represented by this {@code
     * Executbble}
     */
    public AnnotbtedType[] getAnnotbtedExceptionTypes() {
        return TypeAnnotbtionPbrser.buildAnnotbtedTypes(getTypeAnnotbtionBytes0(),
                sun.misc.ShbredSecrets.getJbvbLbngAccess().
                        getConstbntPool(getDeclbringClbss()),
                this,
                getDeclbringClbss(),
                getGenericExceptionTypes(),
                TypeAnnotbtion.TypeAnnotbtionTbrget.THROWS);
    }

}
