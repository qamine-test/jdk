/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.lbng.reflect.AnnotbtedElement;
import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.GenericArrbyType;
import jbvb.lbng.reflect.GenericDeclbrbtion;
import jbvb.lbng.reflect.Member;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Executbble;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Type;
import jbvb.lbng.reflect.TypeVbribble;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.AnnotbtedType;
import jbvb.lbng.ref.SoftReference;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.HbshSet;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Objects;
import sun.misc.Unsbfe;
import sun.reflect.CbllerSensitive;
import sun.reflect.ConstbntPool;
import sun.reflect.Reflection;
import sun.reflect.ReflectionFbctory;
import sun.reflect.generics.fbctory.CoreReflectionFbctory;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.repository.ClbssRepository;
import sun.reflect.generics.repository.MethodRepository;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.scope.ClbssScope;
import sun.security.util.SecurityConstbnts;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.reflect.Proxy;
import sun.reflect.bnnotbtion.*;
import sun.reflect.misc.ReflectUtil;

/**
 * Instbnces of the clbss {@code Clbss} represent clbsses bnd
 * interfbces in b running Jbvb bpplicbtion.  An enum is b kind of
 * clbss bnd bn bnnotbtion is b kind of interfbce.  Every brrby blso
 * belongs to b clbss thbt is reflected bs b {@code Clbss} object
 * thbt is shbred by bll brrbys with the sbme element type bnd number
 * of dimensions.  The primitive Jbvb types ({@code boolebn},
 * {@code byte}, {@code chbr}, {@code short},
 * {@code int}, {@code long}, {@code flobt}, bnd
 * {@code double}), bnd the keyword {@code void} bre blso
 * represented bs {@code Clbss} objects.
 *
 * <p> {@code Clbss} hbs no public constructor. Instebd {@code Clbss}
 * objects bre constructed butombticblly by the Jbvb Virtubl Mbchine bs clbsses
 * bre lobded bnd by cblls to the {@code defineClbss} method in the clbss
 * lobder.
 *
 * <p> The following exbmple uses b {@code Clbss} object to print the
 * clbss nbme of bn object:
 *
 * <blockquote><pre>
 *     void printClbssNbme(Object obj) {
 *         System.out.println("The clbss of " + obj +
 *                            " is " + obj.getClbss().getNbme());
 *     }
 * </pre></blockquote>
 *
 * <p> It is blso possible to get the {@code Clbss} object for b nbmed
 * type (or for void) using b clbss literbl.  See Section 15.8.2 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
 * For exbmple:
 *
 * <blockquote>
 *     {@code System.out.println("The nbme of clbss Foo is: "+Foo.clbss.getNbme());}
 * </blockquote>
 *
 * @pbrbm <T> the type of the clbss modeled by this {@code Clbss}
 * object.  For exbmple, the type of {@code String.clbss} is {@code
 * Clbss<String>}.  Use {@code Clbss<?>} if the clbss being modeled is
 * unknown.
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.ClbssLobder#defineClbss(byte[], int, int)
 * @since   1.0
 */
public finbl clbss Clbss<T> implements jbvb.io.Seriblizbble,
                              GenericDeclbrbtion,
                              Type,
                              AnnotbtedElement {
    privbte stbtic finbl int ANNOTATION= 0x00002000;
    privbte stbtic finbl int ENUM      = 0x00004000;
    privbte stbtic finbl int SYNTHETIC = 0x00001000;

    privbte stbtic nbtive void registerNbtives();
    stbtic {
        registerNbtives();
    }

    /*
     * Privbte constructor. Only the Jbvb Virtubl Mbchine crebtes Clbss objects.
     * This constructor is not used bnd prevents the defbult constructor being
     * generbted.
     */
    privbte Clbss(ClbssLobder lobder, Clbss<?> brrbyComponentType) {
        // Initiblize finbl field for clbssLobder.  The initiblizbtion vblue of non-null
        // prevents future JIT optimizbtions from bssuming this finbl field is null.
        clbssLobder = lobder;
        componentType = brrbyComponentType;
    }

    /**
     * Converts the object to b string. The string representbtion is the
     * string "clbss" or "interfbce", followed by b spbce, bnd then by the
     * fully qublified nbme of the clbss in the formbt returned by
     * {@code getNbme}.  If this {@code Clbss} object represents b
     * primitive type, this method returns the nbme of the primitive type.  If
     * this {@code Clbss} object represents void this method returns
     * "void".
     *
     * @return b string representbtion of this clbss object.
     */
    public String toString() {
        return (isInterfbce() ? "interfbce " : (isPrimitive() ? "" : "clbss "))
            + getNbme();
    }

    /**
     * Returns b string describing this {@code Clbss}, including
     * informbtion bbout modifiers bnd type pbrbmeters.
     *
     * The string is formbtted bs b list of type modifiers, if bny,
     * followed by the kind of type (empty string for primitive types
     * bnd {@code clbss}, {@code enum}, {@code interfbce}, or
     * <code>&#64;</code>{@code interfbce}, bs bppropribte), followed
     * by the type's nbme, followed by bn bngle-brbcketed
     * commb-sepbrbted list of the type's type pbrbmeters, if bny.
     *
     * A spbce is used to sepbrbte modifiers from one bnother bnd to
     * sepbrbte bny modifiers from the kind of type. The modifiers
     * occur in cbnonicbl order. If there bre no type pbrbmeters, the
     * type pbrbmeter list is elided.
     *
     * <p>Note thbt since informbtion bbout the runtime representbtion
     * of b type is being generbted, modifiers not present on the
     * originbting source code or illegbl on the originbting source
     * code mby be present.
     *
     * @return b string describing this {@code Clbss}, including
     * informbtion bbout modifiers bnd type pbrbmeters
     *
     * @since 1.8
     */
    public String toGenericString() {
        if (isPrimitive()) {
            return toString();
        } else {
            StringBuilder sb = new StringBuilder();

            // Clbss modifiers bre b superset of interfbce modifiers
            int modifiers = getModifiers() & Modifier.clbssModifiers();
            if (modifiers != 0) {
                sb.bppend(Modifier.toString(modifiers));
                sb.bppend(' ');
            }

            if (isAnnotbtion()) {
                sb.bppend('@');
            }
            if (isInterfbce()) { // Note: bll bnnotbtion types bre interfbces
                sb.bppend("interfbce");
            } else {
                if (isEnum())
                    sb.bppend("enum");
                else
                    sb.bppend("clbss");
            }
            sb.bppend(' ');
            sb.bppend(getNbme());

            TypeVbribble<?>[] typepbrms = getTypePbrbmeters();
            if (typepbrms.length > 0) {
                boolebn first = true;
                sb.bppend('<');
                for(TypeVbribble<?> typepbrm: typepbrms) {
                    if (!first)
                        sb.bppend(',');
                    sb.bppend(typepbrm.getTypeNbme());
                    first = fblse;
                }
                sb.bppend('>');
            }

            return sb.toString();
        }
    }

    /**
     * Returns the {@code Clbss} object bssocibted with the clbss or
     * interfbce with the given string nbme.  Invoking this method is
     * equivblent to:
     *
     * <blockquote>
     *  {@code Clbss.forNbme(clbssNbme, true, currentLobder)}
     * </blockquote>
     *
     * where {@code currentLobder} denotes the defining clbss lobder of
     * the current clbss.
     *
     * <p> For exbmple, the following code frbgment returns the
     * runtime {@code Clbss} descriptor for the clbss nbmed
     * {@code jbvb.lbng.Threbd}:
     *
     * <blockquote>
     *   {@code Clbss t = Clbss.forNbme("jbvb.lbng.Threbd")}
     * </blockquote>
     * <p>
     * A cbll to {@code forNbme("X")} cbuses the clbss nbmed
     * {@code X} to be initiblized.
     *
     * @pbrbm      clbssNbme   the fully qublified nbme of the desired clbss.
     * @return     the {@code Clbss} object for the clbss with the
     *             specified nbme.
     * @exception LinkbgeError if the linkbge fbils
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *            by this method fbils
     * @exception ClbssNotFoundException if the clbss cbnnot be locbted
     */
    @CbllerSensitive
    public stbtic Clbss<?> forNbme(String clbssNbme)
                throws ClbssNotFoundException {
        return forNbme0(clbssNbme, true,
                        ClbssLobder.getClbssLobder(Reflection.getCbllerClbss()));
    }


    /**
     * Returns the {@code Clbss} object bssocibted with the clbss or
     * interfbce with the given string nbme, using the given clbss lobder.
     * Given the fully qublified nbme for b clbss or interfbce (in the sbme
     * formbt returned by {@code getNbme}) this method bttempts to
     * locbte, lobd, bnd link the clbss or interfbce.  The specified clbss
     * lobder is used to lobd the clbss or interfbce.  If the pbrbmeter
     * {@code lobder} is null, the clbss is lobded through the bootstrbp
     * clbss lobder.  The clbss is initiblized only if the
     * {@code initiblize} pbrbmeter is {@code true} bnd if it hbs
     * not been initiblized ebrlier.
     *
     * <p> If {@code nbme} denotes b primitive type or void, bn bttempt
     * will be mbde to locbte b user-defined clbss in the unnbmed pbckbge whose
     * nbme is {@code nbme}. Therefore, this method cbnnot be used to
     * obtbin bny of the {@code Clbss} objects representing primitive
     * types or void.
     *
     * <p> If {@code nbme} denotes bn brrby clbss, the component type of
     * the brrby clbss is lobded but not initiblized.
     *
     * <p> For exbmple, in bn instbnce method the expression:
     *
     * <blockquote>
     *  {@code Clbss.forNbme("Foo")}
     * </blockquote>
     *
     * is equivblent to:
     *
     * <blockquote>
     *  {@code Clbss.forNbme("Foo", true, this.getClbss().getClbssLobder())}
     * </blockquote>
     *
     * Note thbt this method throws errors relbted to lobding, linking or
     * initiblizing bs specified in Sections 12.2, 12.3 bnd 12.4 of <em>The
     * Jbvb Lbngubge Specificbtion</em>.
     * Note thbt this method does not check whether the requested clbss
     * is bccessible to its cbller.
     *
     * <p> If the {@code lobder} is {@code null}, bnd b security
     * mbnbger is present, bnd the cbller's clbss lobder is not null, then this
     * method cblls the security mbnbger's {@code checkPermission} method
     * with b {@code RuntimePermission("getClbssLobder")} permission to
     * ensure it's ok to bccess the bootstrbp clbss lobder.
     *
     * @pbrbm nbme       fully qublified nbme of the desired clbss
     * @pbrbm initiblize if {@code true} the clbss will be initiblized.
     *                   See Section 12.4 of <em>The Jbvb Lbngubge Specificbtion</em>.
     * @pbrbm lobder     clbss lobder from which the clbss must be lobded
     * @return           clbss object representing the desired clbss
     *
     * @exception LinkbgeError if the linkbge fbils
     * @exception ExceptionInInitiblizerError if the initiblizbtion provoked
     *            by this method fbils
     * @exception ClbssNotFoundException if the clbss cbnnot be locbted by
     *            the specified clbss lobder
     *
     * @see       jbvb.lbng.Clbss#forNbme(String)
     * @see       jbvb.lbng.ClbssLobder
     * @since     1.2
     */
    @CbllerSensitive
    public stbtic Clbss<?> forNbme(String nbme, boolebn initiblize,
                                   ClbssLobder lobder)
        throws ClbssNotFoundException
    {
        if (sun.misc.VM.isSystemDombinLobder(lobder)) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                ClbssLobder ccl = ClbssLobder.getClbssLobder(Reflection.getCbllerClbss());
                if (!sun.misc.VM.isSystemDombinLobder(ccl)) {
                    sm.checkPermission(
                        SecurityConstbnts.GET_CLASSLOADER_PERMISSION);
                }
            }
        }
        return forNbme0(nbme, initiblize, lobder);
    }

    /** Cblled bfter security checks hbve been mbde. */
    privbte stbtic nbtive Clbss<?> forNbme0(String nbme, boolebn initiblize,
                                            ClbssLobder lobder)
        throws ClbssNotFoundException;

    /**
     * Crebtes b new instbnce of the clbss represented by this {@code Clbss}
     * object.  The clbss is instbntibted bs if by b {@code new}
     * expression with bn empty brgument list.  The clbss is initiblized if it
     * hbs not blrebdy been initiblized.
     *
     * <p>Note thbt this method propbgbtes bny exception thrown by the
     * nullbry constructor, including b checked exception.  Use of
     * this method effectively bypbsses the compile-time exception
     * checking thbt would otherwise be performed by the compiler.
     * The {@link
     * jbvb.lbng.reflect.Constructor#newInstbnce(jbvb.lbng.Object...)
     * Constructor.newInstbnce} method bvoids this problem by wrbpping
     * bny exception thrown by the constructor in b (checked) {@link
     * jbvb.lbng.reflect.InvocbtionTbrgetException}.
     *
     * @return  b newly bllocbted instbnce of the clbss represented by this
     *          object.
     * @throws  IllegblAccessException  if the clbss or its nullbry
     *          constructor is not bccessible.
     * @throws  InstbntibtionException
     *          if this {@code Clbss} represents bn bbstrbct clbss,
     *          bn interfbce, bn brrby clbss, b primitive type, or void;
     *          or if the clbss hbs no nullbry constructor;
     *          or if the instbntibtion fbils for some other rebson.
     * @throws  ExceptionInInitiblizerError if the initiblizbtion
     *          provoked by this method fbils.
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd
     *          the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss.
     */
    @CbllerSensitive
    public T newInstbnce()
        throws InstbntibtionException, IllegblAccessException
    {
        if (System.getSecurityMbnbger() != null) {
            checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), fblse);
        }

        // NOTE: the following code mby not be strictly correct under
        // the current Jbvb memory model.

        // Constructor lookup
        if (cbchedConstructor == null) {
            if (this == Clbss.clbss) {
                throw new IllegblAccessException(
                    "Cbn not cbll newInstbnce() on the Clbss for jbvb.lbng.Clbss"
                );
            }
            try {
                Clbss<?>[] empty = {};
                finbl Constructor<T> c = getConstructor0(empty, Member.DECLARED);
                // Disbble bccessibility checks on the constructor
                // since we hbve to do the security check here bnywby
                // (the stbck depth is wrong for the Constructor's
                // security check to work)
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                        public Void run() {
                                c.setAccessible(true);
                                return null;
                            }
                        });
                cbchedConstructor = c;
            } cbtch (NoSuchMethodException e) {
                throw (InstbntibtionException)
                    new InstbntibtionException(getNbme()).initCbuse(e);
            }
        }
        Constructor<T> tmpConstructor = cbchedConstructor;
        // Security check (sbme bs in jbvb.lbng.reflect.Constructor)
        int modifiers = tmpConstructor.getModifiers();
        if (!Reflection.quickCheckMemberAccess(this, modifiers)) {
            Clbss<?> cbller = Reflection.getCbllerClbss();
            if (newInstbnceCbllerCbche != cbller) {
                Reflection.ensureMemberAccess(cbller, this, null, modifiers);
                newInstbnceCbllerCbche = cbller;
            }
        }
        // Run constructor
        try {
            return tmpConstructor.newInstbnce((Object[])null);
        } cbtch (InvocbtionTbrgetException e) {
            Unsbfe.getUnsbfe().throwException(e.getTbrgetException());
            // Not rebched
            return null;
        }
    }
    privbte volbtile trbnsient Constructor<T> cbchedConstructor;
    privbte volbtile trbnsient Clbss<?>       newInstbnceCbllerCbche;


    /**
     * Determines if the specified {@code Object} is bssignment-compbtible
     * with the object represented by this {@code Clbss}.  This method is
     * the dynbmic equivblent of the Jbvb lbngubge {@code instbnceof}
     * operbtor. The method returns {@code true} if the specified
     * {@code Object} brgument is non-null bnd cbn be cbst to the
     * reference type represented by this {@code Clbss} object without
     * rbising b {@code ClbssCbstException.} It returns {@code fblse}
     * otherwise.
     *
     * <p> Specificblly, if this {@code Clbss} object represents b
     * declbred clbss, this method returns {@code true} if the specified
     * {@code Object} brgument is bn instbnce of the represented clbss (or
     * of bny of its subclbsses); it returns {@code fblse} otherwise. If
     * this {@code Clbss} object represents bn brrby clbss, this method
     * returns {@code true} if the specified {@code Object} brgument
     * cbn be converted to bn object of the brrby clbss by bn identity
     * conversion or by b widening reference conversion; it returns
     * {@code fblse} otherwise. If this {@code Clbss} object
     * represents bn interfbce, this method returns {@code true} if the
     * clbss or bny superclbss of the specified {@code Object} brgument
     * implements this interfbce; it returns {@code fblse} otherwise. If
     * this {@code Clbss} object represents b primitive type, this method
     * returns {@code fblse}.
     *
     * @pbrbm   obj the object to check
     * @return  true if {@code obj} is bn instbnce of this clbss
     *
     * @since 1.1
     */
    public nbtive boolebn isInstbnce(Object obj);


    /**
     * Determines if the clbss or interfbce represented by this
     * {@code Clbss} object is either the sbme bs, or is b superclbss or
     * superinterfbce of, the clbss or interfbce represented by the specified
     * {@code Clbss} pbrbmeter. It returns {@code true} if so;
     * otherwise it returns {@code fblse}. If this {@code Clbss}
     * object represents b primitive type, this method returns
     * {@code true} if the specified {@code Clbss} pbrbmeter is
     * exbctly this {@code Clbss} object; otherwise it returns
     * {@code fblse}.
     *
     * <p> Specificblly, this method tests whether the type represented by the
     * specified {@code Clbss} pbrbmeter cbn be converted to the type
     * represented by this {@code Clbss} object vib bn identity conversion
     * or vib b widening reference conversion. See <em>The Jbvb Lbngubge
     * Specificbtion</em>, sections 5.1.1 bnd 5.1.4 , for detbils.
     *
     * @pbrbm cls the {@code Clbss} object to be checked
     * @return the {@code boolebn} vblue indicbting whether objects of the
     * type {@code cls} cbn be bssigned to objects of this clbss
     * @exception NullPointerException if the specified Clbss pbrbmeter is
     *            null.
     * @since 1.1
     */
    public nbtive boolebn isAssignbbleFrom(Clbss<?> cls);


    /**
     * Determines if the specified {@code Clbss} object represents bn
     * interfbce type.
     *
     * @return  {@code true} if this object represents bn interfbce;
     *          {@code fblse} otherwise.
     */
    public nbtive boolebn isInterfbce();


    /**
     * Determines if this {@code Clbss} object represents bn brrby clbss.
     *
     * @return  {@code true} if this object represents bn brrby clbss;
     *          {@code fblse} otherwise.
     * @since   1.1
     */
    public nbtive boolebn isArrby();


    /**
     * Determines if the specified {@code Clbss} object represents b
     * primitive type.
     *
     * <p> There bre nine predefined {@code Clbss} objects to represent
     * the eight primitive types bnd void.  These bre crebted by the Jbvb
     * Virtubl Mbchine, bnd hbve the sbme nbmes bs the primitive types thbt
     * they represent, nbmely {@code boolebn}, {@code byte},
     * {@code chbr}, {@code short}, {@code int},
     * {@code long}, {@code flobt}, bnd {@code double}.
     *
     * <p> These objects mby only be bccessed vib the following public stbtic
     * finbl vbribbles, bnd bre the only {@code Clbss} objects for which
     * this method returns {@code true}.
     *
     * @return true if bnd only if this clbss represents b primitive type
     *
     * @see     jbvb.lbng.Boolebn#TYPE
     * @see     jbvb.lbng.Chbrbcter#TYPE
     * @see     jbvb.lbng.Byte#TYPE
     * @see     jbvb.lbng.Short#TYPE
     * @see     jbvb.lbng.Integer#TYPE
     * @see     jbvb.lbng.Long#TYPE
     * @see     jbvb.lbng.Flobt#TYPE
     * @see     jbvb.lbng.Double#TYPE
     * @see     jbvb.lbng.Void#TYPE
     * @since 1.1
     */
    public nbtive boolebn isPrimitive();

    /**
     * Returns true if this {@code Clbss} object represents bn bnnotbtion
     * type.  Note thbt if this method returns true, {@link #isInterfbce()}
     * would blso return true, bs bll bnnotbtion types bre blso interfbces.
     *
     * @return {@code true} if this clbss object represents bn bnnotbtion
     *      type; {@code fblse} otherwise
     * @since 1.5
     */
    public boolebn isAnnotbtion() {
        return (getModifiers() & ANNOTATION) != 0;
    }

    /**
     * Returns {@code true} if this clbss is b synthetic clbss;
     * returns {@code fblse} otherwise.
     * @return {@code true} if bnd only if this clbss is b synthetic clbss bs
     *         defined by the Jbvb Lbngubge Specificbtion.
     * @jls 13.1 The Form of b Binbry
     * @since 1.5
     */
    public boolebn isSynthetic() {
        return (getModifiers() & SYNTHETIC) != 0;
    }

    /**
     * Returns the  nbme of the entity (clbss, interfbce, brrby clbss,
     * primitive type, or void) represented by this {@code Clbss} object,
     * bs b {@code String}.
     *
     * <p> If this clbss object represents b reference type thbt is not bn
     * brrby type then the binbry nbme of the clbss is returned, bs specified
     * by
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * <p> If this clbss object represents b primitive type or void, then the
     * nbme returned is b {@code String} equbl to the Jbvb lbngubge
     * keyword corresponding to the primitive type or void.
     *
     * <p> If this clbss object represents b clbss of brrbys, then the internbl
     * form of the nbme consists of the nbme of the element type preceded by
     * one or more '{@code [}' chbrbcters representing the depth of the brrby
     * nesting.  The encoding of element type nbmes is bs follows:
     *
     * <blockquote><tbble summbry="Element types bnd encodings">
     * <tr><th> Element Type <th> &nbsp;&nbsp;&nbsp; <th> Encoding
     * <tr><td> boolebn      <td> &nbsp;&nbsp;&nbsp; <td blign=center> Z
     * <tr><td> byte         <td> &nbsp;&nbsp;&nbsp; <td blign=center> B
     * <tr><td> chbr         <td> &nbsp;&nbsp;&nbsp; <td blign=center> C
     * <tr><td> clbss or interfbce
     *                       <td> &nbsp;&nbsp;&nbsp; <td blign=center> L<i>clbssnbme</i>;
     * <tr><td> double       <td> &nbsp;&nbsp;&nbsp; <td blign=center> D
     * <tr><td> flobt        <td> &nbsp;&nbsp;&nbsp; <td blign=center> F
     * <tr><td> int          <td> &nbsp;&nbsp;&nbsp; <td blign=center> I
     * <tr><td> long         <td> &nbsp;&nbsp;&nbsp; <td blign=center> J
     * <tr><td> short        <td> &nbsp;&nbsp;&nbsp; <td blign=center> S
     * </tbble></blockquote>
     *
     * <p> The clbss or interfbce nbme <i>clbssnbme</i> is the binbry nbme of
     * the clbss specified bbove.
     *
     * <p> Exbmples:
     * <blockquote><pre>
     * String.clbss.getNbme()
     *     returns "jbvb.lbng.String"
     * byte.clbss.getNbme()
     *     returns "byte"
     * (new Object[3]).getClbss().getNbme()
     *     returns "[Ljbvb.lbng.Object;"
     * (new int[3][4][5][6][7][8][9]).getClbss().getNbme()
     *     returns "[[[[[[[I"
     * </pre></blockquote>
     *
     * @return  the nbme of the clbss or interfbce
     *          represented by this object.
     */
    public String getNbme() {
        String nbme = this.nbme;
        if (nbme == null)
            this.nbme = nbme = getNbme0();
        return nbme;
    }

    // cbche the nbme to reduce the number of cblls into the VM
    privbte trbnsient String nbme;
    privbte nbtive String getNbme0();

    /**
     * Returns the clbss lobder for the clbss.  Some implementbtions mby use
     * null to represent the bootstrbp clbss lobder. This method will return
     * null in such implementbtions if this clbss wbs lobded by the bootstrbp
     * clbss lobder.
     *
     * <p> If b security mbnbger is present, bnd the cbller's clbss lobder is
     * not null bnd the cbller's clbss lobder is not the sbme bs or bn bncestor of
     * the clbss lobder for the clbss whose clbss lobder is requested, then
     * this method cblls the security mbnbger's {@code checkPermission}
     * method with b {@code RuntimePermission("getClbssLobder")}
     * permission to ensure it's ok to bccess the clbss lobder for the clbss.
     *
     * <p>If this object
     * represents b primitive type or void, null is returned.
     *
     * @return  the clbss lobder thbt lobded the clbss or interfbce
     *          represented by this object.
     * @throws SecurityException
     *    if b security mbnbger exists bnd its
     *    {@code checkPermission} method denies
     *    bccess to the clbss lobder for the clbss.
     * @see jbvb.lbng.ClbssLobder
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     */
    @CbllerSensitive
    public ClbssLobder getClbssLobder() {
        ClbssLobder cl = getClbssLobder0();
        if (cl == null)
            return null;
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            ClbssLobder.checkClbssLobderPermission(cl, Reflection.getCbllerClbss());
        }
        return cl;
    }

    // Pbckbge-privbte to bllow ClbssLobder bccess
    ClbssLobder getClbssLobder0() { return clbssLobder; }

    // Initiblized in JVM not by privbte constructor
    privbte finbl ClbssLobder clbssLobder;

    /**
     * Returns bn brrby of {@code TypeVbribble} objects thbt represent the
     * type vbribbles declbred by the generic declbrbtion represented by this
     * {@code GenericDeclbrbtion} object, in declbrbtion order.  Returns bn
     * brrby of length 0 if the underlying generic declbrbtion declbres no type
     * vbribbles.
     *
     * @return bn brrby of {@code TypeVbribble} objects thbt represent
     *     the type vbribbles declbred by this generic declbrbtion
     * @throws jbvb.lbng.reflect.GenericSignbtureFormbtError if the generic
     *     signbture of this generic declbrbtion does not conform to
     *     the formbt specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @since 1.5
     */
    @SuppressWbrnings("unchecked")
    public TypeVbribble<Clbss<T>>[] getTypePbrbmeters() {
        ClbssRepository info = getGenericInfo();
        if (info != null)
            return (TypeVbribble<Clbss<T>>[])info.getTypePbrbmeters();
        else
            return (TypeVbribble<Clbss<T>>[])new TypeVbribble<?>[0];
    }


    /**
     * Returns the {@code Clbss} representing the superclbss of the entity
     * (clbss, interfbce, primitive type or void) represented by this
     * {@code Clbss}.  If this {@code Clbss} represents either the
     * {@code Object} clbss, bn interfbce, b primitive type, or void, then
     * null is returned.  If this object represents bn brrby clbss then the
     * {@code Clbss} object representing the {@code Object} clbss is
     * returned.
     *
     * @return the superclbss of the clbss represented by this object.
     */
    public nbtive Clbss<? super T> getSuperclbss();


    /**
     * Returns the {@code Type} representing the direct superclbss of
     * the entity (clbss, interfbce, primitive type or void) represented by
     * this {@code Clbss}.
     *
     * <p>If the superclbss is b pbrbmeterized type, the {@code Type}
     * object returned must bccurbtely reflect the bctubl type
     * pbrbmeters used in the source code. The pbrbmeterized type
     * representing the superclbss is crebted if it hbd not been
     * crebted before. See the declbrbtion of {@link
     * jbvb.lbng.reflect.PbrbmeterizedType PbrbmeterizedType} for the
     * sembntics of the crebtion process for pbrbmeterized types.  If
     * this {@code Clbss} represents either the {@code Object}
     * clbss, bn interfbce, b primitive type, or void, then null is
     * returned.  If this object represents bn brrby clbss then the
     * {@code Clbss} object representing the {@code Object} clbss is
     * returned.
     *
     * @throws jbvb.lbng.reflect.GenericSignbtureFormbtError if the generic
     *     clbss signbture does not conform to the formbt specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @throws TypeNotPresentException if the generic superclbss
     *     refers to b non-existent type declbrbtion
     * @throws jbvb.lbng.reflect.MblformedPbrbmeterizedTypeException if the
     *     generic superclbss refers to b pbrbmeterized type thbt cbnnot be
     *     instbntibted  for bny rebson
     * @return the superclbss of the clbss represented by this object
     * @since 1.5
     */
    public Type getGenericSuperclbss() {
        ClbssRepository info = getGenericInfo();
        if (info == null) {
            return getSuperclbss();
        }

        // Historicbl irregulbrity:
        // Generic signbture mbrks interfbces with superclbss = Object
        // but this API returns null for interfbces
        if (isInterfbce()) {
            return null;
        }

        return info.getSuperclbss();
    }

    /**
     * Gets the pbckbge for this clbss.  The clbss lobder of this clbss is used
     * to find the pbckbge.  If the clbss wbs lobded by the bootstrbp clbss
     * lobder the set of pbckbges lobded from CLASSPATH is sebrched to find the
     * pbckbge of the clbss. Null is returned if no pbckbge object wbs crebted
     * by the clbss lobder of this clbss.
     *
     * <p> Pbckbges hbve bttributes for versions bnd specificbtions only if the
     * informbtion wbs defined in the mbnifests thbt bccompbny the clbsses, bnd
     * if the clbss lobder crebted the pbckbge instbnce with the bttributes
     * from the mbnifest.
     *
     * @return the pbckbge of the clbss, or null if no pbckbge
     *         informbtion is bvbilbble from the brchive or codebbse.
     */
    public Pbckbge getPbckbge() {
        return Pbckbge.getPbckbge(this);
    }


    /**
     * Determines the interfbces implemented by the clbss or interfbce
     * represented by this object.
     *
     * <p> If this object represents b clbss, the return vblue is bn brrby
     * contbining objects representing bll interfbces implemented by the
     * clbss. The order of the interfbce objects in the brrby corresponds to
     * the order of the interfbce nbmes in the {@code implements} clbuse
     * of the declbrbtion of the clbss represented by this object. For
     * exbmple, given the declbrbtion:
     * <blockquote>
     * {@code clbss Shimmer implements FloorWbx, DessertTopping { ... }}
     * </blockquote>
     * suppose the vblue of {@code s} is bn instbnce of
     * {@code Shimmer}; the vblue of the expression:
     * <blockquote>
     * {@code s.getClbss().getInterfbces()[0]}
     * </blockquote>
     * is the {@code Clbss} object thbt represents interfbce
     * {@code FloorWbx}; bnd the vblue of:
     * <blockquote>
     * {@code s.getClbss().getInterfbces()[1]}
     * </blockquote>
     * is the {@code Clbss} object thbt represents interfbce
     * {@code DessertTopping}.
     *
     * <p> If this object represents bn interfbce, the brrby contbins objects
     * representing bll interfbces extended by the interfbce. The order of the
     * interfbce objects in the brrby corresponds to the order of the interfbce
     * nbmes in the {@code extends} clbuse of the declbrbtion of the
     * interfbce represented by this object.
     *
     * <p> If this object represents b clbss or interfbce thbt implements no
     * interfbces, the method returns bn brrby of length 0.
     *
     * <p> If this object represents b primitive type or void, the method
     * returns bn brrby of length 0.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, the
     * interfbces {@code Clonebble} bnd {@code jbvb.io.Seriblizbble} bre
     * returned in thbt order.
     *
     * @return bn brrby of interfbces implemented by this clbss.
     */
    public Clbss<?>[] getInterfbces() {
        ReflectionDbtb<T> rd = reflectionDbtb();
        if (rd == null) {
            // no cloning required
            return getInterfbces0();
        } else {
            Clbss<?>[] interfbces = rd.interfbces;
            if (interfbces == null) {
                interfbces = getInterfbces0();
                rd.interfbces = interfbces;
            }
            // defensively copy before hbnding over to user code
            return interfbces.clone();
        }
    }

    privbte nbtive Clbss<?>[] getInterfbces0();

    /**
     * Returns the {@code Type}s representing the interfbces
     * directly implemented by the clbss or interfbce represented by
     * this object.
     *
     * <p>If b superinterfbce is b pbrbmeterized type, the
     * {@code Type} object returned for it must bccurbtely reflect
     * the bctubl type pbrbmeters used in the source code. The
     * pbrbmeterized type representing ebch superinterfbce is crebted
     * if it hbd not been crebted before. See the declbrbtion of
     * {@link jbvb.lbng.reflect.PbrbmeterizedType PbrbmeterizedType}
     * for the sembntics of the crebtion process for pbrbmeterized
     * types.
     *
     * <p> If this object represents b clbss, the return vblue is bn
     * brrby contbining objects representing bll interfbces
     * implemented by the clbss. The order of the interfbce objects in
     * the brrby corresponds to the order of the interfbce nbmes in
     * the {@code implements} clbuse of the declbrbtion of the clbss
     * represented by this object.  In the cbse of bn brrby clbss, the
     * interfbces {@code Clonebble} bnd {@code Seriblizbble} bre
     * returned in thbt order.
     *
     * <p>If this object represents bn interfbce, the brrby contbins
     * objects representing bll interfbces directly extended by the
     * interfbce.  The order of the interfbce objects in the brrby
     * corresponds to the order of the interfbce nbmes in the
     * {@code extends} clbuse of the declbrbtion of the interfbce
     * represented by this object.
     *
     * <p>If this object represents b clbss or interfbce thbt
     * implements no interfbces, the method returns bn brrby of length
     * 0.
     *
     * <p>If this object represents b primitive type or void, the
     * method returns bn brrby of length 0.
     *
     * @throws jbvb.lbng.reflect.GenericSignbtureFormbtError
     *     if the generic clbss signbture does not conform to the formbt
     *     specified in
     *     <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     * @throws TypeNotPresentException if bny of the generic
     *     superinterfbces refers to b non-existent type declbrbtion
     * @throws jbvb.lbng.reflect.MblformedPbrbmeterizedTypeException
     *     if bny of the generic superinterfbces refer to b pbrbmeterized
     *     type thbt cbnnot be instbntibted for bny rebson
     * @return bn brrby of interfbces implemented by this clbss
     * @since 1.5
     */
    public Type[] getGenericInterfbces() {
        ClbssRepository info = getGenericInfo();
        return (info == null) ?  getInterfbces() : info.getSuperInterfbces();
    }


    /**
     * Returns the {@code Clbss} representing the component type of bn
     * brrby.  If this clbss does not represent bn brrby clbss this method
     * returns null.
     *
     * @return the {@code Clbss} representing the component type of this
     * clbss if this clbss is bn brrby
     * @see     jbvb.lbng.reflect.Arrby
     * @since 1.1
     */
    public Clbss<?> getComponentType() {
        // Only return for brrby types. Storbge mby be reused for Clbss for instbnce types.
        if (isArrby()) {
            return componentType;
        } else {
            return null;
        }
    }

    privbte finbl Clbss<?> componentType;


    /**
     * Returns the Jbvb lbngubge modifiers for this clbss or interfbce, encoded
     * in bn integer. The modifiers consist of the Jbvb Virtubl Mbchine's
     * constbnts for {@code public}, {@code protected},
     * {@code privbte}, {@code finbl}, {@code stbtic},
     * {@code bbstrbct} bnd {@code interfbce}; they should be decoded
     * using the methods of clbss {@code Modifier}.
     *
     * <p> If the underlying clbss is bn brrby clbss, then its
     * {@code public}, {@code privbte} bnd {@code protected}
     * modifiers bre the sbme bs those of its component type.  If this
     * {@code Clbss} represents b primitive type or void, its
     * {@code public} modifier is blwbys {@code true}, bnd its
     * {@code protected} bnd {@code privbte} modifiers bre blwbys
     * {@code fblse}. If this object represents bn brrby clbss, b
     * primitive type or void, then its {@code finbl} modifier is blwbys
     * {@code true} bnd its interfbce modifier is blwbys
     * {@code fblse}. The vblues of its other modifiers bre not determined
     * by this specificbtion.
     *
     * <p> The modifier encodings bre defined in <em>The Jbvb Virtubl Mbchine
     * Specificbtion</em>, tbble 4.1.
     *
     * @return the {@code int} representing the modifiers for this clbss
     * @see     jbvb.lbng.reflect.Modifier
     * @since 1.1
     */
    public nbtive int getModifiers();


    /**
     * Gets the signers of this clbss.
     *
     * @return  the signers of this clbss, or null if there bre no signers.  In
     *          pbrticulbr, this method returns null if this object represents
     *          b primitive type or void.
     * @since   1.1
     */
    public nbtive Object[] getSigners();


    /**
     * Set the signers of this clbss.
     */
    nbtive void setSigners(Object[] signers);


    /**
     * If this {@code Clbss} object represents b locbl or bnonymous
     * clbss within b method, returns b {@link
     * jbvb.lbng.reflect.Method Method} object representing the
     * immedibtely enclosing method of the underlying clbss. Returns
     * {@code null} otherwise.
     *
     * In pbrticulbr, this method returns {@code null} if the underlying
     * clbss is b locbl or bnonymous clbss immedibtely enclosed by b type
     * declbrbtion, instbnce initiblizer or stbtic initiblizer.
     *
     * @return the immedibtely enclosing method of the underlying clbss, if
     *     thbt clbss is b locbl or bnonymous clbss; otherwise {@code null}.
     *
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd bny of the
     *         following conditions is met:
     *
     *         <ul>
     *
     *         <li> the cbller's clbss lobder is not the sbme bs the
     *         clbss lobder of the enclosing clbss bnd invocbtion of
     *         {@link SecurityMbnbger#checkPermission
     *         s.checkPermission} method with
     *         {@code RuntimePermission("bccessDeclbredMembers")}
     *         denies bccess to the methods within the enclosing clbss
     *
     *         <li> the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the enclosing clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of the enclosing clbss
     *
     *         </ul>
     * @since 1.5
     */
    @CbllerSensitive
    public Method getEnclosingMethod() throws SecurityException {
        EnclosingMethodInfo enclosingInfo = getEnclosingMethodInfo();

        if (enclosingInfo == null)
            return null;
        else {
            if (!enclosingInfo.isMethod())
                return null;

            MethodRepository typeInfo = MethodRepository.mbke(enclosingInfo.getDescriptor(),
                                                              getFbctory());
            Clbss<?>   returnType       = toClbss(typeInfo.getReturnType());
            Type []    pbrbmeterTypes   = typeInfo.getPbrbmeterTypes();
            Clbss<?>[] pbrbmeterClbsses = new Clbss<?>[pbrbmeterTypes.length];

            // Convert Types to Clbsses; returned types *should*
            // be clbss objects since the methodDescriptor's used
            // don't hbve generics informbtion
            for(int i = 0; i < pbrbmeterClbsses.length; i++)
                pbrbmeterClbsses[i] = toClbss(pbrbmeterTypes[i]);

            // Perform bccess check
            Clbss<?> enclosingCbndidbte = enclosingInfo.getEnclosingClbss();
            enclosingCbndidbte.checkMemberAccess(Member.DECLARED,
                                                 Reflection.getCbllerClbss(), true);
            /*
             * Loop over bll declbred methods; mbtch method nbme,
             * number of bnd type of pbrbmeters, *bnd* return
             * type.  Mbtching return type is blso necessbry
             * becbuse of covbribnt returns, etc.
             */
            for(Method m: enclosingCbndidbte.getDeclbredMethods()) {
                if (m.getNbme().equbls(enclosingInfo.getNbme()) ) {
                    Clbss<?>[] cbndidbtePbrbmClbsses = m.getPbrbmeterTypes();
                    if (cbndidbtePbrbmClbsses.length == pbrbmeterClbsses.length) {
                        boolebn mbtches = true;
                        for(int i = 0; i < cbndidbtePbrbmClbsses.length; i++) {
                            if (!cbndidbtePbrbmClbsses[i].equbls(pbrbmeterClbsses[i])) {
                                mbtches = fblse;
                                brebk;
                            }
                        }

                        if (mbtches) { // finblly, check return type
                            if (m.getReturnType().equbls(returnType) )
                                return m;
                        }
                    }
                }
            }

            throw new InternblError("Enclosing method not found");
        }
    }

    privbte nbtive Object[] getEnclosingMethod0();

    privbte EnclosingMethodInfo getEnclosingMethodInfo() {
        Object[] enclosingInfo = getEnclosingMethod0();
        if (enclosingInfo == null)
            return null;
        else {
            return new EnclosingMethodInfo(enclosingInfo);
        }
    }

    privbte finbl stbtic clbss EnclosingMethodInfo {
        privbte Clbss<?> enclosingClbss;
        privbte String nbme;
        privbte String descriptor;

        privbte EnclosingMethodInfo(Object[] enclosingInfo) {
            if (enclosingInfo.length != 3)
                throw new InternblError("Mblformed enclosing method informbtion");
            try {
                // The brrby is expected to hbve three elements:

                // the immedibtely enclosing clbss
                enclosingClbss = (Clbss<?>) enclosingInfo[0];
                bssert(enclosingClbss != null);

                // the immedibtely enclosing method or constructor's
                // nbme (cbn be null).
                nbme            = (String)   enclosingInfo[1];

                // the immedibtely enclosing method or constructor's
                // descriptor (null iff nbme is).
                descriptor      = (String)   enclosingInfo[2];
                bssert((nbme != null && descriptor != null) || nbme == descriptor);
            } cbtch (ClbssCbstException cce) {
                throw new InternblError("Invblid type in enclosing method informbtion", cce);
            }
        }

        boolebn isPbrtibl() {
            return enclosingClbss == null || nbme == null || descriptor == null;
        }

        boolebn isConstructor() { return !isPbrtibl() && "<init>".equbls(nbme); }

        boolebn isMethod() { return !isPbrtibl() && !isConstructor() && !"<clinit>".equbls(nbme); }

        Clbss<?> getEnclosingClbss() { return enclosingClbss; }

        String getNbme() { return nbme; }

        String getDescriptor() { return descriptor; }

    }

    privbte stbtic Clbss<?> toClbss(Type o) {
        if (o instbnceof GenericArrbyType)
            return Arrby.newInstbnce(toClbss(((GenericArrbyType)o).getGenericComponentType()),
                                     0)
                .getClbss();
        return (Clbss<?>)o;
     }

    /**
     * If this {@code Clbss} object represents b locbl or bnonymous
     * clbss within b constructor, returns b {@link
     * jbvb.lbng.reflect.Constructor Constructor} object representing
     * the immedibtely enclosing constructor of the underlying
     * clbss. Returns {@code null} otherwise.  In pbrticulbr, this
     * method returns {@code null} if the underlying clbss is b locbl
     * or bnonymous clbss immedibtely enclosed by b type declbrbtion,
     * instbnce initiblizer or stbtic initiblizer.
     *
     * @return the immedibtely enclosing constructor of the underlying clbss, if
     *     thbt clbss is b locbl or bnonymous clbss; otherwise {@code null}.
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd bny of the
     *         following conditions is met:
     *
     *         <ul>
     *
     *         <li> the cbller's clbss lobder is not the sbme bs the
     *         clbss lobder of the enclosing clbss bnd invocbtion of
     *         {@link SecurityMbnbger#checkPermission
     *         s.checkPermission} method with
     *         {@code RuntimePermission("bccessDeclbredMembers")}
     *         denies bccess to the constructors within the enclosing clbss
     *
     *         <li> the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the enclosing clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of the enclosing clbss
     *
     *         </ul>
     * @since 1.5
     */
    @CbllerSensitive
    public Constructor<?> getEnclosingConstructor() throws SecurityException {
        EnclosingMethodInfo enclosingInfo = getEnclosingMethodInfo();

        if (enclosingInfo == null)
            return null;
        else {
            if (!enclosingInfo.isConstructor())
                return null;

            ConstructorRepository typeInfo = ConstructorRepository.mbke(enclosingInfo.getDescriptor(),
                                                                        getFbctory());
            Type []    pbrbmeterTypes   = typeInfo.getPbrbmeterTypes();
            Clbss<?>[] pbrbmeterClbsses = new Clbss<?>[pbrbmeterTypes.length];

            // Convert Types to Clbsses; returned types *should*
            // be clbss objects since the methodDescriptor's used
            // don't hbve generics informbtion
            for(int i = 0; i < pbrbmeterClbsses.length; i++)
                pbrbmeterClbsses[i] = toClbss(pbrbmeterTypes[i]);

            // Perform bccess check
            Clbss<?> enclosingCbndidbte = enclosingInfo.getEnclosingClbss();
            enclosingCbndidbte.checkMemberAccess(Member.DECLARED,
                                                 Reflection.getCbllerClbss(), true);
            /*
             * Loop over bll declbred constructors; mbtch number
             * of bnd type of pbrbmeters.
             */
            for(Constructor<?> c: enclosingCbndidbte.getDeclbredConstructors()) {
                Clbss<?>[] cbndidbtePbrbmClbsses = c.getPbrbmeterTypes();
                if (cbndidbtePbrbmClbsses.length == pbrbmeterClbsses.length) {
                    boolebn mbtches = true;
                    for(int i = 0; i < cbndidbtePbrbmClbsses.length; i++) {
                        if (!cbndidbtePbrbmClbsses[i].equbls(pbrbmeterClbsses[i])) {
                            mbtches = fblse;
                            brebk;
                        }
                    }

                    if (mbtches)
                        return c;
                }
            }

            throw new InternblError("Enclosing constructor not found");
        }
    }


    /**
     * If the clbss or interfbce represented by this {@code Clbss} object
     * is b member of bnother clbss, returns the {@code Clbss} object
     * representing the clbss in which it wbs declbred.  This method returns
     * null if this clbss or interfbce is not b member of bny other clbss.  If
     * this {@code Clbss} object represents bn brrby clbss, b primitive
     * type, or void,then this method returns null.
     *
     * @return the declbring clbss for this clbss
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd the cbller's
     *         clbss lobder is not the sbme bs or bn bncestor of the clbss
     *         lobder for the declbring clbss bnd invocbtion of {@link
     *         SecurityMbnbger#checkPbckbgeAccess s.checkPbckbgeAccess()}
     *         denies bccess to the pbckbge of the declbring clbss
     * @since 1.1
     */
    @CbllerSensitive
    public Clbss<?> getDeclbringClbss() throws SecurityException {
        finbl Clbss<?> cbndidbte = getDeclbringClbss0();

        if (cbndidbte != null)
            cbndidbte.checkPbckbgeAccess(
                    ClbssLobder.getClbssLobder(Reflection.getCbllerClbss()), true);
        return cbndidbte;
    }

    privbte nbtive Clbss<?> getDeclbringClbss0();


    /**
     * Returns the immedibtely enclosing clbss of the underlying
     * clbss.  If the underlying clbss is b top level clbss this
     * method returns {@code null}.
     * @return the immedibtely enclosing clbss of the underlying clbss
     * @exception  SecurityException
     *             If b security mbnbger, <i>s</i>, is present bnd the cbller's
     *             clbss lobder is not the sbme bs or bn bncestor of the clbss
     *             lobder for the enclosing clbss bnd invocbtion of {@link
     *             SecurityMbnbger#checkPbckbgeAccess s.checkPbckbgeAccess()}
     *             denies bccess to the pbckbge of the enclosing clbss
     * @since 1.5
     */
    @CbllerSensitive
    public Clbss<?> getEnclosingClbss() throws SecurityException {
        // There bre five kinds of clbsses (or interfbces):
        // b) Top level clbsses
        // b) Nested clbsses (stbtic member clbsses)
        // c) Inner clbsses (non-stbtic member clbsses)
        // d) Locbl clbsses (nbmed clbsses declbred within b method)
        // e) Anonymous clbsses


        // JVM Spec 4.8.6: A clbss must hbve bn EnclosingMethod
        // bttribute if bnd only if it is b locbl clbss or bn
        // bnonymous clbss.
        EnclosingMethodInfo enclosingInfo = getEnclosingMethodInfo();
        Clbss<?> enclosingCbndidbte;

        if (enclosingInfo == null) {
            // This is b top level or b nested clbss or bn inner clbss (b, b, or c)
            enclosingCbndidbte = getDeclbringClbss();
        } else {
            Clbss<?> enclosingClbss = enclosingInfo.getEnclosingClbss();
            // This is b locbl clbss or bn bnonymous clbss (d or e)
            if (enclosingClbss == this || enclosingClbss == null)
                throw new InternblError("Mblformed enclosing method informbtion");
            else
                enclosingCbndidbte = enclosingClbss;
        }

        if (enclosingCbndidbte != null)
            enclosingCbndidbte.checkPbckbgeAccess(
                    ClbssLobder.getClbssLobder(Reflection.getCbllerClbss()), true);
        return enclosingCbndidbte;
    }

    /**
     * Returns the simple nbme of the underlying clbss bs given in the
     * source code. Returns bn empty string if the underlying clbss is
     * bnonymous.
     *
     * <p>The simple nbme of bn brrby is the simple nbme of the
     * component type with "[]" bppended.  In pbrticulbr the simple
     * nbme of bn brrby whose component type is bnonymous is "[]".
     *
     * @return the simple nbme of the underlying clbss
     * @since 1.5
     */
    public String getSimpleNbme() {
        if (isArrby())
            return getComponentType().getSimpleNbme()+"[]";

        String simpleNbme = getSimpleBinbryNbme();
        if (simpleNbme == null) { // top level clbss
            simpleNbme = getNbme();
            return simpleNbme.substring(simpleNbme.lbstIndexOf('.')+1); // strip the pbckbge nbme
        }
        // According to JLS3 "Binbry Compbtibility" (13.1) the binbry
        // nbme of non-pbckbge clbsses (not top level) is the binbry
        // nbme of the immedibtely enclosing clbss followed by b '$' followed by:
        // (for nested bnd inner clbsses): the simple nbme.
        // (for locbl clbsses): 1 or more digits followed by the simple nbme.
        // (for bnonymous clbsses): 1 or more digits.

        // Since getSimpleBinbryNbme() will strip the binbry nbme of
        // the immedibtly enclosing clbss, we bre now looking bt b
        // string thbt mbtches the regulbr expression "\$[0-9]*"
        // followed by b simple nbme (considering the simple of bn
        // bnonymous clbss to be the empty string).

        // Remove lebding "\$[0-9]*" from the nbme
        int length = simpleNbme.length();
        if (length < 1 || simpleNbme.chbrAt(0) != '$')
            throw new InternblError("Mblformed clbss nbme");
        int index = 1;
        while (index < length && isAsciiDigit(simpleNbme.chbrAt(index)))
            index++;
        // Eventublly, this is the empty string iff this is bn bnonymous clbss
        return simpleNbme.substring(index);
    }

    /**
     * Return bn informbtive string for the nbme of this type.
     *
     * @return bn informbtive string for the nbme of this type
     * @since 1.8
     */
    public String getTypeNbme() {
        if (isArrby()) {
            try {
                Clbss<?> cl = this;
                int dimensions = 0;
                while (cl.isArrby()) {
                    dimensions++;
                    cl = cl.getComponentType();
                }
                StringBuilder sb = new StringBuilder();
                sb.bppend(cl.getNbme());
                for (int i = 0; i < dimensions; i++) {
                    sb.bppend("[]");
                }
                return sb.toString();
            } cbtch (Throwbble e) { /*FALLTHRU*/ }
        }
        return getNbme();
    }

    /**
     * Chbrbcter.isDigit bnswers {@code true} to some non-bscii
     * digits.  This one does not.
     */
    privbte stbtic boolebn isAsciiDigit(chbr c) {
        return '0' <= c && c <= '9';
    }

    /**
     * Returns the cbnonicbl nbme of the underlying clbss bs
     * defined by the Jbvb Lbngubge Specificbtion.  Returns null if
     * the underlying clbss does not hbve b cbnonicbl nbme (i.e., if
     * it is b locbl or bnonymous clbss or bn brrby whose component
     * type does not hbve b cbnonicbl nbme).
     * @return the cbnonicbl nbme of the underlying clbss if it exists, bnd
     * {@code null} otherwise.
     * @since 1.5
     */
    public String getCbnonicblNbme() {
        if (isArrby()) {
            String cbnonicblNbme = getComponentType().getCbnonicblNbme();
            if (cbnonicblNbme != null)
                return cbnonicblNbme + "[]";
            else
                return null;
        }
        if (isLocblOrAnonymousClbss())
            return null;
        Clbss<?> enclosingClbss = getEnclosingClbss();
        if (enclosingClbss == null) { // top level clbss
            return getNbme();
        } else {
            String enclosingNbme = enclosingClbss.getCbnonicblNbme();
            if (enclosingNbme == null)
                return null;
            return enclosingNbme + "." + getSimpleNbme();
        }
    }

    /**
     * Returns {@code true} if bnd only if the underlying clbss
     * is bn bnonymous clbss.
     *
     * @return {@code true} if bnd only if this clbss is bn bnonymous clbss.
     * @since 1.5
     */
    public boolebn isAnonymousClbss() {
        return "".equbls(getSimpleNbme());
    }

    /**
     * Returns {@code true} if bnd only if the underlying clbss
     * is b locbl clbss.
     *
     * @return {@code true} if bnd only if this clbss is b locbl clbss.
     * @since 1.5
     */
    public boolebn isLocblClbss() {
        return isLocblOrAnonymousClbss() && !isAnonymousClbss();
    }

    /**
     * Returns {@code true} if bnd only if the underlying clbss
     * is b member clbss.
     *
     * @return {@code true} if bnd only if this clbss is b member clbss.
     * @since 1.5
     */
    public boolebn isMemberClbss() {
        return getSimpleBinbryNbme() != null && !isLocblOrAnonymousClbss();
    }

    /**
     * Returns the "simple binbry nbme" of the underlying clbss, i.e.,
     * the binbry nbme without the lebding enclosing clbss nbme.
     * Returns {@code null} if the underlying clbss is b top level
     * clbss.
     */
    privbte String getSimpleBinbryNbme() {
        Clbss<?> enclosingClbss = getEnclosingClbss();
        if (enclosingClbss == null) // top level clbss
            return null;
        // Otherwise, strip the enclosing clbss' nbme
        try {
            return getNbme().substring(enclosingClbss.getNbme().length());
        } cbtch (IndexOutOfBoundsException ex) {
            throw new InternblError("Mblformed clbss nbme", ex);
        }
    }

    /**
     * Returns {@code true} if this is b locbl clbss or bn bnonymous
     * clbss.  Returns {@code fblse} otherwise.
     */
    privbte boolebn isLocblOrAnonymousClbss() {
        // JVM Spec 4.8.6: A clbss must hbve bn EnclosingMethod
        // bttribute if bnd only if it is b locbl clbss or bn
        // bnonymous clbss.
        return getEnclosingMethodInfo() != null;
    }

    /**
     * Returns bn brrby contbining {@code Clbss} objects representing bll
     * the public clbsses bnd interfbces thbt bre members of the clbss
     * represented by this {@code Clbss} object.  This includes public
     * clbss bnd interfbce members inherited from superclbsses bnd public clbss
     * bnd interfbce members declbred by the clbss.  This method returns bn
     * brrby of length 0 if this {@code Clbss} object hbs no public member
     * clbsses or interfbces.  This method blso returns bn brrby of length 0 if
     * this {@code Clbss} object represents b primitive type, bn brrby
     * clbss, or void.
     *
     * @return the brrby of {@code Clbss} objects representing the public
     *         members of this clbss
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @since 1.1
     */
    @CbllerSensitive
    public Clbss<?>[] getClbsses() {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), fblse);

        // Privileged so this implementbtion cbn look bt DECLARED clbsses,
        // something the cbller might not hbve privilege to do.  The code here
        // is bllowed to look bt DECLARED clbsses becbuse (1) it does not hbnd
        // out bnything other thbn public members bnd (2) public member bccess
        // hbs blrebdy been ok'd by the SecurityMbnbger.

        return jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Clbss<?>[]>() {
                public Clbss<?>[] run() {
                    List<Clbss<?>> list = new ArrbyList<>();
                    Clbss<?> currentClbss = Clbss.this;
                    while (currentClbss != null) {
                        for (Clbss<?> m : currentClbss.getDeclbredClbsses()) {
                            if (Modifier.isPublic(m.getModifiers())) {
                                list.bdd(m);
                            }
                        }
                        currentClbss = currentClbss.getSuperclbss();
                    }
                    return list.toArrby(new Clbss<?>[0]);
                }
            });
    }


    /**
     * Returns bn brrby contbining {@code Field} objects reflecting bll
     * the bccessible public fields of the clbss or interfbce represented by
     * this {@code Clbss} object.
     *
     * <p> If this {@code Clbss} object represents b clbss or interfbce with no
     * no bccessible public fields, then this method returns bn brrby of length
     * 0.
     *
     * <p> If this {@code Clbss} object represents b clbss, then this method
     * returns the public fields of the clbss bnd of bll its superclbsses.
     *
     * <p> If this {@code Clbss} object represents bn interfbce, then this
     * method returns the fields of the interfbce bnd of bll its
     * superinterfbces.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, b primitive
     * type, or void, then this method returns bn brrby of length 0.
     *
     * <p> The elements in the returned brrby bre not sorted bnd bre not in bny
     * pbrticulbr order.
     *
     * @return the brrby of {@code Field} objects representing the
     *         public fields
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @since 1.1
     * @jls 8.2 Clbss Members
     * @jls 8.3 Field Declbrbtions
     */
    @CbllerSensitive
    public Field[] getFields() throws SecurityException {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), true);
        return copyFields(privbteGetPublicFields(null));
    }


    /**
     * Returns bn brrby contbining {@code Method} objects reflecting bll the
     * public methods of the clbss or interfbce represented by this {@code
     * Clbss} object, including those declbred by the clbss or interfbce bnd
     * those inherited from superclbsses bnd superinterfbces.
     *
     * <p> If this {@code Clbss} object represents b type thbt hbs multiple
     * public methods with the sbme nbme bnd pbrbmeter types, but different
     * return types, then the returned brrby hbs b {@code Method} object for
     * ebch such method.
     *
     * <p> If this {@code Clbss} object represents b type with b clbss
     * initiblizbtion method {@code <clinit>}, then the returned brrby does
     * <em>not</em> hbve b corresponding {@code Method} object.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, then the
     * returned brrby hbs b {@code Method} object for ebch of the public
     * methods inherited by the brrby type from {@code Object}. It does not
     * contbin b {@code Method} object for {@code clone()}.
     *
     * <p> If this {@code Clbss} object represents bn interfbce then the
     * returned brrby does not contbin bny implicitly declbred methods from
     * {@code Object}. Therefore, if no methods bre explicitly declbred in
     * this interfbce or bny of its superinterfbces then the returned brrby
     * hbs length 0. (Note thbt b {@code Clbss} object which represents b clbss
     * blwbys hbs public methods, inherited from {@code Object}.)
     *
     * <p> If this {@code Clbss} object represents b primitive type or void,
     * then the returned brrby hbs length 0.
     *
     * <p> Stbtic methods declbred in superinterfbces of the clbss or interfbce
     * represented by this {@code Clbss} object bre not considered members of
     * the clbss or interfbce.
     *
     * <p> The elements in the returned brrby bre not sorted bnd bre not in bny
     * pbrticulbr order.
     *
     * @return the brrby of {@code Method} objects representing the
     *         public methods of this clbss
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @jls 8.2 Clbss Members
     * @jls 8.4 Method Declbrbtions
     * @since 1.1
     */
    @CbllerSensitive
    public Method[] getMethods() throws SecurityException {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), true);
        return copyMethods(privbteGetPublicMethods());
    }


    /**
     * Returns bn brrby contbining {@code Constructor} objects reflecting
     * bll the public constructors of the clbss represented by this
     * {@code Clbss} object.  An brrby of length 0 is returned if the
     * clbss hbs no public constructors, or if the clbss is bn brrby clbss, or
     * if the clbss reflects b primitive type or void.
     *
     * Note thbt while this method returns bn brrby of {@code
     * Constructor<T>} objects (thbt is bn brrby of constructors from
     * this clbss), the return type of this method is {@code
     * Constructor<?>[]} bnd <em>not</em> {@code Constructor<T>[]} bs
     * might be expected.  This less informbtive return type is
     * necessbry since bfter being returned from this method, the
     * brrby could be modified to hold {@code Constructor} objects for
     * different clbsses, which would violbte the type gubrbntees of
     * {@code Constructor<T>[]}.
     *
     * @return the brrby of {@code Constructor} objects representing the
     *         public constructors of this clbss
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @since 1.1
     */
    @CbllerSensitive
    public Constructor<?>[] getConstructors() throws SecurityException {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), true);
        return copyConstructors(privbteGetDeclbredConstructors(true));
    }


    /**
     * Returns b {@code Field} object thbt reflects the specified public member
     * field of the clbss or interfbce represented by this {@code Clbss}
     * object. The {@code nbme} pbrbmeter is b {@code String} specifying the
     * simple nbme of the desired field.
     *
     * <p> The field to be reflected is determined by the blgorithm thbt
     * follows.  Let C be the clbss or interfbce represented by this object:
     *
     * <OL>
     * <LI> If C declbres b public field with the nbme specified, thbt is the
     *      field to be reflected.</LI>
     * <LI> If no field wbs found in step 1 bbove, this blgorithm is bpplied
     *      recursively to ebch direct superinterfbce of C. The direct
     *      superinterfbces bre sebrched in the order they were declbred.</LI>
     * <LI> If no field wbs found in steps 1 bnd 2 bbove, bnd C hbs b
     *      superclbss S, then this blgorithm is invoked recursively upon S.
     *      If C hbs no superclbss, then b {@code NoSuchFieldException}
     *      is thrown.</LI>
     * </OL>
     *
     * <p> If this {@code Clbss} object represents bn brrby type, then this
     * method does not find the {@code length} field of the brrby type.
     *
     * @pbrbm nbme the field nbme
     * @return the {@code Field} object of this clbss specified by
     *         {@code nbme}
     * @throws NoSuchFieldException if b field with the specified nbme is
     *         not found.
     * @throws NullPointerException if {@code nbme} is {@code null}
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @since 1.1
     * @jls 8.2 Clbss Members
     * @jls 8.3 Field Declbrbtions
     */
    @CbllerSensitive
    public Field getField(String nbme)
        throws NoSuchFieldException, SecurityException {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), true);
        Field field = getField0(nbme);
        if (field == null) {
            throw new NoSuchFieldException(nbme);
        }
        return field;
    }


    /**
     * Returns b {@code Method} object thbt reflects the specified public
     * member method of the clbss or interfbce represented by this
     * {@code Clbss} object. The {@code nbme} pbrbmeter is b
     * {@code String} specifying the simple nbme of the desired method. The
     * {@code pbrbmeterTypes} pbrbmeter is bn brrby of {@code Clbss}
     * objects thbt identify the method's formbl pbrbmeter types, in declbred
     * order. If {@code pbrbmeterTypes} is {@code null}, it is
     * trebted bs if it were bn empty brrby.
     *
     * <p> If the {@code nbme} is "{@code <init>}" or "{@code <clinit>}" b
     * {@code NoSuchMethodException} is rbised. Otherwise, the method to
     * be reflected is determined by the blgorithm thbt follows.  Let C be the
     * clbss or interfbce represented by this object:
     * <OL>
     * <LI> C is sebrched for b <I>mbtching method</I>, bs defined below. If b
     *      mbtching method is found, it is reflected.</LI>
     * <LI> If no mbtching method is found by step 1 then:
     *   <OL TYPE="b">
     *   <LI> If C is b clbss other thbn {@code Object}, then this blgorithm is
     *        invoked recursively on the superclbss of C.</LI>
     *   <LI> If C is the clbss {@code Object}, or if C is bn interfbce, then
     *        the superinterfbces of C (if bny) bre sebrched for b mbtching
     *        method. If bny such method is found, it is reflected.</LI>
     *   </OL></LI>
     * </OL>
     *
     * <p> To find b mbtching method in b clbss or interfbce C:&nbsp; If C
     * declbres exbctly one public method with the specified nbme bnd exbctly
     * the sbme formbl pbrbmeter types, thbt is the method reflected. If more
     * thbn one such method is found in C, bnd one of these methods hbs b
     * return type thbt is more specific thbn bny of the others, thbt method is
     * reflected; otherwise one of the methods is chosen brbitrbrily.
     *
     * <p>Note thbt there mby be more thbn one mbtching method in b
     * clbss becbuse while the Jbvb lbngubge forbids b clbss to
     * declbre multiple methods with the sbme signbture but different
     * return types, the Jbvb virtubl mbchine does not.  This
     * increbsed flexibility in the virtubl mbchine cbn be used to
     * implement vbrious lbngubge febtures.  For exbmple, covbribnt
     * returns cbn be implemented with {@linkplbin
     * jbvb.lbng.reflect.Method#isBridge bridge methods}; the bridge
     * method bnd the method being overridden would hbve the sbme
     * signbture but different return types.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, then this
     * method does not find the {@code clone()} method.
     *
     * <p> Stbtic methods declbred in superinterfbces of the clbss or interfbce
     * represented by this {@code Clbss} object bre not considered members of
     * the clbss or interfbce.
     *
     * @pbrbm nbme the nbme of the method
     * @pbrbm pbrbmeterTypes the list of pbrbmeters
     * @return the {@code Method} object thbt mbtches the specified
     *         {@code nbme} bnd {@code pbrbmeterTypes}
     * @throws NoSuchMethodException if b mbtching method is not found
     *         or if the nbme is "&lt;init&gt;"or "&lt;clinit&gt;".
     * @throws NullPointerException if {@code nbme} is {@code null}
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @jls 8.2 Clbss Members
     * @jls 8.4 Method Declbrbtions
     * @since 1.1
     */
    @CbllerSensitive
    public Method getMethod(String nbme, Clbss<?>... pbrbmeterTypes)
        throws NoSuchMethodException, SecurityException {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), true);
        Method method = getMethod0(nbme, pbrbmeterTypes, true);
        if (method == null) {
            throw new NoSuchMethodException(getNbme() + "." + nbme + brgumentTypesToString(pbrbmeterTypes));
        }
        return method;
    }


    /**
     * Returns b {@code Constructor} object thbt reflects the specified
     * public constructor of the clbss represented by this {@code Clbss}
     * object. The {@code pbrbmeterTypes} pbrbmeter is bn brrby of
     * {@code Clbss} objects thbt identify the constructor's formbl
     * pbrbmeter types, in declbred order.
     *
     * If this {@code Clbss} object represents bn inner clbss
     * declbred in b non-stbtic context, the formbl pbrbmeter types
     * include the explicit enclosing instbnce bs the first pbrbmeter.
     *
     * <p> The constructor to reflect is the public constructor of the clbss
     * represented by this {@code Clbss} object whose formbl pbrbmeter
     * types mbtch those specified by {@code pbrbmeterTypes}.
     *
     * @pbrbm pbrbmeterTypes the pbrbmeter brrby
     * @return the {@code Constructor} object of the public constructor thbt
     *         mbtches the specified {@code pbrbmeterTypes}
     * @throws NoSuchMethodException if b mbtching method is not found.
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd
     *         the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss.
     *
     * @since 1.1
     */
    @CbllerSensitive
    public Constructor<T> getConstructor(Clbss<?>... pbrbmeterTypes)
        throws NoSuchMethodException, SecurityException {
        checkMemberAccess(Member.PUBLIC, Reflection.getCbllerClbss(), true);
        return getConstructor0(pbrbmeterTypes, Member.PUBLIC);
    }


    /**
     * Returns bn brrby of {@code Clbss} objects reflecting bll the
     * clbsses bnd interfbces declbred bs members of the clbss represented by
     * this {@code Clbss} object. This includes public, protected, defbult
     * (pbckbge) bccess, bnd privbte clbsses bnd interfbces declbred by the
     * clbss, but excludes inherited clbsses bnd interfbces.  This method
     * returns bn brrby of length 0 if the clbss declbres no clbsses or
     * interfbces bs members, or if this {@code Clbss} object represents b
     * primitive type, bn brrby clbss, or void.
     *
     * @return the brrby of {@code Clbss} objects representing bll the
     *         declbred members of this clbss
     * @throws SecurityException
     *         If b security mbnbger, <i>s</i>, is present bnd bny of the
     *         following conditions is met:
     *
     *         <ul>
     *
     *         <li> the cbller's clbss lobder is not the sbme bs the
     *         clbss lobder of this clbss bnd invocbtion of
     *         {@link SecurityMbnbger#checkPermission
     *         s.checkPermission} method with
     *         {@code RuntimePermission("bccessDeclbredMembers")}
     *         denies bccess to the declbred clbsses within this clbss
     *
     *         <li> the cbller's clbss lobder is not the sbme bs or bn
     *         bncestor of the clbss lobder for the current clbss bnd
     *         invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *         s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *         of this clbss
     *
     *         </ul>
     *
     * @since 1.1
     */
    @CbllerSensitive
    public Clbss<?>[] getDeclbredClbsses() throws SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), fblse);
        return getDeclbredClbsses0();
    }


    /**
     * Returns bn brrby of {@code Field} objects reflecting bll the fields
     * declbred by the clbss or interfbce represented by this
     * {@code Clbss} object. This includes public, protected, defbult
     * (pbckbge) bccess, bnd privbte fields, but excludes inherited fields.
     *
     * <p> If this {@code Clbss} object represents b clbss or interfbce with no
     * declbred fields, then this method returns bn brrby of length 0.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, b primitive
     * type, or void, then this method returns bn brrby of length 0.
     *
     * <p> The elements in the returned brrby bre not sorted bnd bre not in bny
     * pbrticulbr order.
     *
     * @return  the brrby of {@code Field} objects representing bll the
     *          declbred fields of this clbss
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd bny of the
     *          following conditions is met:
     *
     *          <ul>
     *
     *          <li> the cbller's clbss lobder is not the sbme bs the
     *          clbss lobder of this clbss bnd invocbtion of
     *          {@link SecurityMbnbger#checkPermission
     *          s.checkPermission} method with
     *          {@code RuntimePermission("bccessDeclbredMembers")}
     *          denies bccess to the declbred fields within this clbss
     *
     *          <li> the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss
     *
     *          </ul>
     *
     * @since 1.1
     * @jls 8.2 Clbss Members
     * @jls 8.3 Field Declbrbtions
     */
    @CbllerSensitive
    public Field[] getDeclbredFields() throws SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), true);
        return copyFields(privbteGetDeclbredFields(fblse));
    }


    /**
     *
     * Returns bn brrby contbining {@code Method} objects reflecting bll the
     * declbred methods of the clbss or interfbce represented by this {@code
     * Clbss} object, including public, protected, defbult (pbckbge)
     * bccess, bnd privbte methods, but excluding inherited methods.
     *
     * <p> If this {@code Clbss} object represents b type thbt hbs multiple
     * declbred methods with the sbme nbme bnd pbrbmeter types, but different
     * return types, then the returned brrby hbs b {@code Method} object for
     * ebch such method.
     *
     * <p> If this {@code Clbss} object represents b type thbt hbs b clbss
     * initiblizbtion method {@code <clinit>}, then the returned brrby does
     * <em>not</em> hbve b corresponding {@code Method} object.
     *
     * <p> If this {@code Clbss} object represents b clbss or interfbce with no
     * declbred methods, then the returned brrby hbs length 0.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, b primitive
     * type, or void, then the returned brrby hbs length 0.
     *
     * <p> The elements in the returned brrby bre not sorted bnd bre not in bny
     * pbrticulbr order.
     *
     * @return  the brrby of {@code Method} objects representing bll the
     *          declbred methods of this clbss
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd bny of the
     *          following conditions is met:
     *
     *          <ul>
     *
     *          <li> the cbller's clbss lobder is not the sbme bs the
     *          clbss lobder of this clbss bnd invocbtion of
     *          {@link SecurityMbnbger#checkPermission
     *          s.checkPermission} method with
     *          {@code RuntimePermission("bccessDeclbredMembers")}
     *          denies bccess to the declbred methods within this clbss
     *
     *          <li> the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss
     *
     *          </ul>
     *
     * @jls 8.2 Clbss Members
     * @jls 8.4 Method Declbrbtions
     * @since 1.1
     */
    @CbllerSensitive
    public Method[] getDeclbredMethods() throws SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), true);
        return copyMethods(privbteGetDeclbredMethods(fblse));
    }


    /**
     * Returns bn brrby of {@code Constructor} objects reflecting bll the
     * constructors declbred by the clbss represented by this
     * {@code Clbss} object. These bre public, protected, defbult
     * (pbckbge) bccess, bnd privbte constructors.  The elements in the brrby
     * returned bre not sorted bnd bre not in bny pbrticulbr order.  If the
     * clbss hbs b defbult constructor, it is included in the returned brrby.
     * This method returns bn brrby of length 0 if this {@code Clbss}
     * object represents bn interfbce, b primitive type, bn brrby clbss, or
     * void.
     *
     * <p> See <em>The Jbvb Lbngubge Specificbtion</em>, section 8.2.
     *
     * @return  the brrby of {@code Constructor} objects representing bll the
     *          declbred constructors of this clbss
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd bny of the
     *          following conditions is met:
     *
     *          <ul>
     *
     *          <li> the cbller's clbss lobder is not the sbme bs the
     *          clbss lobder of this clbss bnd invocbtion of
     *          {@link SecurityMbnbger#checkPermission
     *          s.checkPermission} method with
     *          {@code RuntimePermission("bccessDeclbredMembers")}
     *          denies bccess to the declbred constructors within this clbss
     *
     *          <li> the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss
     *
     *          </ul>
     *
     * @since 1.1
     */
    @CbllerSensitive
    public Constructor<?>[] getDeclbredConstructors() throws SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), true);
        return copyConstructors(privbteGetDeclbredConstructors(fblse));
    }


    /**
     * Returns b {@code Field} object thbt reflects the specified declbred
     * field of the clbss or interfbce represented by this {@code Clbss}
     * object. The {@code nbme} pbrbmeter is b {@code String} thbt specifies
     * the simple nbme of the desired field.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, then this
     * method does not find the {@code length} field of the brrby type.
     *
     * @pbrbm nbme the nbme of the field
     * @return  the {@code Field} object for the specified field in this
     *          clbss
     * @throws  NoSuchFieldException if b field with the specified nbme is
     *          not found.
     * @throws  NullPointerException if {@code nbme} is {@code null}
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd bny of the
     *          following conditions is met:
     *
     *          <ul>
     *
     *          <li> the cbller's clbss lobder is not the sbme bs the
     *          clbss lobder of this clbss bnd invocbtion of
     *          {@link SecurityMbnbger#checkPermission
     *          s.checkPermission} method with
     *          {@code RuntimePermission("bccessDeclbredMembers")}
     *          denies bccess to the declbred field
     *
     *          <li> the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss
     *
     *          </ul>
     *
     * @since 1.1
     * @jls 8.2 Clbss Members
     * @jls 8.3 Field Declbrbtions
     */
    @CbllerSensitive
    public Field getDeclbredField(String nbme)
        throws NoSuchFieldException, SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), true);
        Field field = sebrchFields(privbteGetDeclbredFields(fblse), nbme);
        if (field == null) {
            throw new NoSuchFieldException(nbme);
        }
        return field;
    }


    /**
     * Returns b {@code Method} object thbt reflects the specified
     * declbred method of the clbss or interfbce represented by this
     * {@code Clbss} object. The {@code nbme} pbrbmeter is b
     * {@code String} thbt specifies the simple nbme of the desired
     * method, bnd the {@code pbrbmeterTypes} pbrbmeter is bn brrby of
     * {@code Clbss} objects thbt identify the method's formbl pbrbmeter
     * types, in declbred order.  If more thbn one method with the sbme
     * pbrbmeter types is declbred in b clbss, bnd one of these methods hbs b
     * return type thbt is more specific thbn bny of the others, thbt method is
     * returned; otherwise one of the methods is chosen brbitrbrily.  If the
     * nbme is "&lt;init&gt;"or "&lt;clinit&gt;" b {@code NoSuchMethodException}
     * is rbised.
     *
     * <p> If this {@code Clbss} object represents bn brrby type, then this
     * method does not find the {@code clone()} method.
     *
     * @pbrbm nbme the nbme of the method
     * @pbrbm pbrbmeterTypes the pbrbmeter brrby
     * @return  the {@code Method} object for the method of this clbss
     *          mbtching the specified nbme bnd pbrbmeters
     * @throws  NoSuchMethodException if b mbtching method is not found.
     * @throws  NullPointerException if {@code nbme} is {@code null}
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd bny of the
     *          following conditions is met:
     *
     *          <ul>
     *
     *          <li> the cbller's clbss lobder is not the sbme bs the
     *          clbss lobder of this clbss bnd invocbtion of
     *          {@link SecurityMbnbger#checkPermission
     *          s.checkPermission} method with
     *          {@code RuntimePermission("bccessDeclbredMembers")}
     *          denies bccess to the declbred method
     *
     *          <li> the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss
     *
     *          </ul>
     *
     * @jls 8.2 Clbss Members
     * @jls 8.4 Method Declbrbtions
     * @since 1.1
     */
    @CbllerSensitive
    public Method getDeclbredMethod(String nbme, Clbss<?>... pbrbmeterTypes)
        throws NoSuchMethodException, SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), true);
        Method method = sebrchMethods(privbteGetDeclbredMethods(fblse), nbme, pbrbmeterTypes);
        if (method == null) {
            throw new NoSuchMethodException(getNbme() + "." + nbme + brgumentTypesToString(pbrbmeterTypes));
        }
        return method;
    }


    /**
     * Returns b {@code Constructor} object thbt reflects the specified
     * constructor of the clbss or interfbce represented by this
     * {@code Clbss} object.  The {@code pbrbmeterTypes} pbrbmeter is
     * bn brrby of {@code Clbss} objects thbt identify the constructor's
     * formbl pbrbmeter types, in declbred order.
     *
     * If this {@code Clbss} object represents bn inner clbss
     * declbred in b non-stbtic context, the formbl pbrbmeter types
     * include the explicit enclosing instbnce bs the first pbrbmeter.
     *
     * @pbrbm pbrbmeterTypes the pbrbmeter brrby
     * @return  The {@code Constructor} object for the constructor with the
     *          specified pbrbmeter list
     * @throws  NoSuchMethodException if b mbtching method is not found.
     * @throws  SecurityException
     *          If b security mbnbger, <i>s</i>, is present bnd bny of the
     *          following conditions is met:
     *
     *          <ul>
     *
     *          <li> the cbller's clbss lobder is not the sbme bs the
     *          clbss lobder of this clbss bnd invocbtion of
     *          {@link SecurityMbnbger#checkPermission
     *          s.checkPermission} method with
     *          {@code RuntimePermission("bccessDeclbredMembers")}
     *          denies bccess to the declbred constructor
     *
     *          <li> the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the current clbss bnd
     *          invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the pbckbge
     *          of this clbss
     *
     *          </ul>
     *
     * @since 1.1
     */
    @CbllerSensitive
    public Constructor<T> getDeclbredConstructor(Clbss<?>... pbrbmeterTypes)
        throws NoSuchMethodException, SecurityException {
        checkMemberAccess(Member.DECLARED, Reflection.getCbllerClbss(), true);
        return getConstructor0(pbrbmeterTypes, Member.DECLARED);
    }

    /**
     * Finds b resource with b given nbme.  The rules for sebrching resources
     * bssocibted with b given clbss bre implemented by the defining
     * {@linkplbin ClbssLobder clbss lobder} of the clbss.  This method
     * delegbtes to this object's clbss lobder.  If this object wbs lobded by
     * the bootstrbp clbss lobder, the method delegbtes to {@link
     * ClbssLobder#getSystemResourceAsStrebm}.
     *
     * <p> Before delegbtion, bn bbsolute resource nbme is constructed from the
     * given resource nbme using this blgorithm:
     *
     * <ul>
     *
     * <li> If the {@code nbme} begins with b {@code '/'}
     * (<tt>'&#92;u002f'</tt>), then the bbsolute nbme of the resource is the
     * portion of the {@code nbme} following the {@code '/'}.
     *
     * <li> Otherwise, the bbsolute nbme is of the following form:
     *
     * <blockquote>
     *   {@code modified_pbckbge_nbme/nbme}
     * </blockquote>
     *
     * <p> Where the {@code modified_pbckbge_nbme} is the pbckbge nbme of this
     * object with {@code '/'} substituted for {@code '.'}
     * (<tt>'&#92;u002e'</tt>).
     *
     * </ul>
     *
     * @pbrbm  nbme nbme of the desired resource
     * @return      A {@link jbvb.io.InputStrebm} object or {@code null} if
     *              no resource with this nbme is found
     * @throws  NullPointerException If {@code nbme} is {@code null}
     * @since  1.1
     */
     public InputStrebm getResourceAsStrebm(String nbme) {
        nbme = resolveNbme(nbme);
        ClbssLobder cl = getClbssLobder0();
        if (cl==null) {
            // A system clbss.
            return ClbssLobder.getSystemResourceAsStrebm(nbme);
        }
        return cl.getResourceAsStrebm(nbme);
    }

    /**
     * Finds b resource with b given nbme.  The rules for sebrching resources
     * bssocibted with b given clbss bre implemented by the defining
     * {@linkplbin ClbssLobder clbss lobder} of the clbss.  This method
     * delegbtes to this object's clbss lobder.  If this object wbs lobded by
     * the bootstrbp clbss lobder, the method delegbtes to {@link
     * ClbssLobder#getSystemResource}.
     *
     * <p> Before delegbtion, bn bbsolute resource nbme is constructed from the
     * given resource nbme using this blgorithm:
     *
     * <ul>
     *
     * <li> If the {@code nbme} begins with b {@code '/'}
     * (<tt>'&#92;u002f'</tt>), then the bbsolute nbme of the resource is the
     * portion of the {@code nbme} following the {@code '/'}.
     *
     * <li> Otherwise, the bbsolute nbme is of the following form:
     *
     * <blockquote>
     *   {@code modified_pbckbge_nbme/nbme}
     * </blockquote>
     *
     * <p> Where the {@code modified_pbckbge_nbme} is the pbckbge nbme of this
     * object with {@code '/'} substituted for {@code '.'}
     * (<tt>'&#92;u002e'</tt>).
     *
     * </ul>
     *
     * @pbrbm  nbme nbme of the desired resource
     * @return      A  {@link jbvb.net.URL} object or {@code null} if no
     *              resource with this nbme is found
     * @since  1.1
     */
    public jbvb.net.URL getResource(String nbme) {
        nbme = resolveNbme(nbme);
        ClbssLobder cl = getClbssLobder0();
        if (cl==null) {
            // A system clbss.
            return ClbssLobder.getSystemResource(nbme);
        }
        return cl.getResource(nbme);
    }



    /** protection dombin returned when the internbl dombin is null */
    privbte stbtic jbvb.security.ProtectionDombin bllPermDombin;


    /**
     * Returns the {@code ProtectionDombin} of this clbss.  If there is b
     * security mbnbger instblled, this method first cblls the security
     * mbnbger's {@code checkPermission} method with b
     * {@code RuntimePermission("getProtectionDombin")} permission to
     * ensure it's ok to get the
     * {@code ProtectionDombin}.
     *
     * @return the ProtectionDombin of this clbss
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        getting the ProtectionDombin.
     *
     * @see jbvb.security.ProtectionDombin
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     * @since 1.2
     */
    public jbvb.security.ProtectionDombin getProtectionDombin() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.GET_PD_PERMISSION);
        }
        jbvb.security.ProtectionDombin pd = getProtectionDombin0();
        if (pd == null) {
            if (bllPermDombin == null) {
                jbvb.security.Permissions perms =
                    new jbvb.security.Permissions();
                perms.bdd(SecurityConstbnts.ALL_PERMISSION);
                bllPermDombin =
                    new jbvb.security.ProtectionDombin(null, perms);
            }
            pd = bllPermDombin;
        }
        return pd;
    }


    /**
     * Returns the ProtectionDombin of this clbss.
     */
    privbte nbtive jbvb.security.ProtectionDombin getProtectionDombin0();

    /*
     * Return the Virtubl Mbchine's Clbss object for the nbmed
     * primitive type.
     */
    stbtic nbtive Clbss<?> getPrimitiveClbss(String nbme);

    /*
     * Check if client is bllowed to bccess members.  If bccess is denied,
     * throw b SecurityException.
     *
     * This method blso enforces pbckbge bccess.
     *
     * <p> Defbult policy: bllow bll clients bccess with normbl Jbvb bccess
     * control.
     */
    privbte void checkMemberAccess(int which, Clbss<?> cbller, boolebn checkProxyInterfbces) {
        finbl SecurityMbnbger s = System.getSecurityMbnbger();
        if (s != null) {
            /* Defbult policy bllows bccess to bll {@link Member#PUBLIC} members,
             * bs well bs bccess to clbsses thbt hbve the sbme clbss lobder bs the cbller.
             * In bll other cbses, it requires RuntimePermission("bccessDeclbredMembers")
             * permission.
             */
            finbl ClbssLobder ccl = ClbssLobder.getClbssLobder(cbller);
            finbl ClbssLobder cl = getClbssLobder0();
            if (which != Member.PUBLIC) {
                if (ccl != cl) {
                    s.checkPermission(SecurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
                }
            }
            this.checkPbckbgeAccess(ccl, checkProxyInterfbces);
        }
    }

    /*
     * Checks if b client lobded in ClbssLobder ccl is bllowed to bccess this
     * clbss under the current pbckbge bccess policy. If bccess is denied,
     * throw b SecurityException.
     */
    privbte void checkPbckbgeAccess(finbl ClbssLobder ccl, boolebn checkProxyInterfbces) {
        finbl SecurityMbnbger s = System.getSecurityMbnbger();
        if (s != null) {
            finbl ClbssLobder cl = getClbssLobder0();

            if (ReflectUtil.needsPbckbgeAccessCheck(ccl, cl)) {
                String nbme = this.getNbme();
                int i = nbme.lbstIndexOf('.');
                if (i != -1) {
                    // skip the pbckbge bccess check on b proxy clbss in defbult proxy pbckbge
                    String pkg = nbme.substring(0, i);
                    if (!Proxy.isProxyClbss(this) || ReflectUtil.isNonPublicProxyClbss(this)) {
                        s.checkPbckbgeAccess(pkg);
                    }
                }
            }
            // check pbckbge bccess on the proxy interfbces
            if (checkProxyInterfbces && Proxy.isProxyClbss(this)) {
                ReflectUtil.checkProxyPbckbgeAccess(ccl, this.getInterfbces());
            }
        }
    }

    /**
     * Add b pbckbge nbme prefix if the nbme is not bbsolute Remove lebding "/"
     * if nbme is bbsolute
     */
    privbte String resolveNbme(String nbme) {
        if (nbme == null) {
            return nbme;
        }
        if (!nbme.stbrtsWith("/")) {
            Clbss<?> c = this;
            while (c.isArrby()) {
                c = c.getComponentType();
            }
            String bbseNbme = c.getNbme();
            int index = bbseNbme.lbstIndexOf('.');
            if (index != -1) {
                nbme = bbseNbme.substring(0, index).replbce('.', '/')
                    +"/"+nbme;
            }
        } else {
            nbme = nbme.substring(1);
        }
        return nbme;
    }

    /**
     * Atomic operbtions support.
     */
    privbte stbtic clbss Atomic {
        // initiblize Unsbfe mbchinery here, since we need to cbll Clbss.clbss instbnce method
        // bnd hbve to bvoid cblling it in the stbtic initiblizer of the Clbss clbss...
        privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
        // offset of Clbss.reflectionDbtb instbnce field
        privbte stbtic finbl long reflectionDbtbOffset;
        // offset of Clbss.bnnotbtionType instbnce field
        privbte stbtic finbl long bnnotbtionTypeOffset;
        // offset of Clbss.bnnotbtionDbtb instbnce field
        privbte stbtic finbl long bnnotbtionDbtbOffset;

        stbtic {
            Field[] fields = Clbss.clbss.getDeclbredFields0(fblse); // bypbss cbches
            reflectionDbtbOffset = objectFieldOffset(fields, "reflectionDbtb");
            bnnotbtionTypeOffset = objectFieldOffset(fields, "bnnotbtionType");
            bnnotbtionDbtbOffset = objectFieldOffset(fields, "bnnotbtionDbtb");
        }

        privbte stbtic long objectFieldOffset(Field[] fields, String fieldNbme) {
            Field field = sebrchFields(fields, fieldNbme);
            if (field == null) {
                throw new Error("No " + fieldNbme + " field found in jbvb.lbng.Clbss");
            }
            return unsbfe.objectFieldOffset(field);
        }

        stbtic <T> boolebn cbsReflectionDbtb(Clbss<?> clbzz,
                                             SoftReference<ReflectionDbtb<T>> oldDbtb,
                                             SoftReference<ReflectionDbtb<T>> newDbtb) {
            return unsbfe.compbreAndSwbpObject(clbzz, reflectionDbtbOffset, oldDbtb, newDbtb);
        }

        stbtic <T> boolebn cbsAnnotbtionType(Clbss<?> clbzz,
                                             AnnotbtionType oldType,
                                             AnnotbtionType newType) {
            return unsbfe.compbreAndSwbpObject(clbzz, bnnotbtionTypeOffset, oldType, newType);
        }

        stbtic <T> boolebn cbsAnnotbtionDbtb(Clbss<?> clbzz,
                                             AnnotbtionDbtb oldDbtb,
                                             AnnotbtionDbtb newDbtb) {
            return unsbfe.compbreAndSwbpObject(clbzz, bnnotbtionDbtbOffset, oldDbtb, newDbtb);
        }
    }

    /**
     * Reflection support.
     */

    // Cbches for certbin reflective results
    privbte stbtic boolebn useCbches = true;

    // reflection dbtb thbt might get invblidbted when JVM TI RedefineClbsses() is cblled
    privbte stbtic clbss ReflectionDbtb<T> {
        volbtile Field[] declbredFields;
        volbtile Field[] publicFields;
        volbtile Method[] declbredMethods;
        volbtile Method[] publicMethods;
        volbtile Constructor<T>[] declbredConstructors;
        volbtile Constructor<T>[] publicConstructors;
        // Intermedibte results for getFields bnd getMethods
        volbtile Field[] declbredPublicFields;
        volbtile Method[] declbredPublicMethods;
        volbtile Clbss<?>[] interfbces;

        // Vblue of clbssRedefinedCount when we crebted this ReflectionDbtb instbnce
        finbl int redefinedCount;

        ReflectionDbtb(int redefinedCount) {
            this.redefinedCount = redefinedCount;
        }
    }

    privbte volbtile trbnsient SoftReference<ReflectionDbtb<T>> reflectionDbtb;

    // Incremented by the VM on ebch cbll to JVM TI RedefineClbsses()
    // thbt redefines this clbss or b superclbss.
    privbte volbtile trbnsient int clbssRedefinedCount = 0;

    // Lbzily crebte bnd cbche ReflectionDbtb
    privbte ReflectionDbtb<T> reflectionDbtb() {
        SoftReference<ReflectionDbtb<T>> reflectionDbtb = this.reflectionDbtb;
        int clbssRedefinedCount = this.clbssRedefinedCount;
        ReflectionDbtb<T> rd;
        if (useCbches &&
            reflectionDbtb != null &&
            (rd = reflectionDbtb.get()) != null &&
            rd.redefinedCount == clbssRedefinedCount) {
            return rd;
        }
        // else no SoftReference or clebred SoftReference or stble ReflectionDbtb
        // -> crebte bnd replbce new instbnce
        return newReflectionDbtb(reflectionDbtb, clbssRedefinedCount);
    }

    privbte ReflectionDbtb<T> newReflectionDbtb(SoftReference<ReflectionDbtb<T>> oldReflectionDbtb,
                                                int clbssRedefinedCount) {
        if (!useCbches) return null;

        while (true) {
            ReflectionDbtb<T> rd = new ReflectionDbtb<>(clbssRedefinedCount);
            // try to CAS it...
            if (Atomic.cbsReflectionDbtb(this, oldReflectionDbtb, new SoftReference<>(rd))) {
                return rd;
            }
            // else retry
            oldReflectionDbtb = this.reflectionDbtb;
            clbssRedefinedCount = this.clbssRedefinedCount;
            if (oldReflectionDbtb != null &&
                (rd = oldReflectionDbtb.get()) != null &&
                rd.redefinedCount == clbssRedefinedCount) {
                return rd;
            }
        }
    }

    // Generic signbture hbndling
    privbte nbtive String getGenericSignbture0();

    // Generic info repository; lbzily initiblized
    privbte volbtile trbnsient ClbssRepository genericInfo;

    // bccessor for fbctory
    privbte GenericsFbctory getFbctory() {
        // crebte scope bnd fbctory
        return CoreReflectionFbctory.mbke(this, ClbssScope.mbke(this));
    }

    // bccessor for generic info repository;
    // generic info is lbzily initiblized
    privbte ClbssRepository getGenericInfo() {
        ClbssRepository genericInfo = this.genericInfo;
        if (genericInfo == null) {
            String signbture = getGenericSignbture0();
            if (signbture == null) {
                genericInfo = ClbssRepository.NONE;
            } else {
                genericInfo = ClbssRepository.mbke(signbture, getFbctory());
            }
            this.genericInfo = genericInfo;
        }
        return (genericInfo != ClbssRepository.NONE) ? genericInfo : null;
    }

    // Annotbtions hbndling
    nbtive byte[] getRbwAnnotbtions();
    // Since 1.8
    nbtive byte[] getRbwTypeAnnotbtions();
    stbtic byte[] getExecutbbleTypeAnnotbtionBytes(Executbble ex) {
        return getReflectionFbctory().getExecutbbleTypeAnnotbtionBytes(ex);
    }

    nbtive ConstbntPool getConstbntPool();

    //
    //
    // jbvb.lbng.reflect.Field hbndling
    //
    //

    // Returns bn brrby of "root" fields. These Field objects must NOT
    // be propbgbted to the outside world, but must instebd be copied
    // vib ReflectionFbctory.copyField.
    privbte Field[] privbteGetDeclbredFields(boolebn publicOnly) {
        checkInitted();
        Field[] res;
        ReflectionDbtb<T> rd = reflectionDbtb();
        if (rd != null) {
            res = publicOnly ? rd.declbredPublicFields : rd.declbredFields;
            if (res != null) return res;
        }
        // No cbched vblue bvbilbble; request vblue from VM
        res = Reflection.filterFields(this, getDeclbredFields0(publicOnly));
        if (rd != null) {
            if (publicOnly) {
                rd.declbredPublicFields = res;
            } else {
                rd.declbredFields = res;
            }
        }
        return res;
    }

    // Returns bn brrby of "root" fields. These Field objects must NOT
    // be propbgbted to the outside world, but must instebd be copied
    // vib ReflectionFbctory.copyField.
    privbte Field[] privbteGetPublicFields(Set<Clbss<?>> trbversedInterfbces) {
        checkInitted();
        Field[] res;
        ReflectionDbtb<T> rd = reflectionDbtb();
        if (rd != null) {
            res = rd.publicFields;
            if (res != null) return res;
        }

        // No cbched vblue bvbilbble; compute vblue recursively.
        // Trbverse in correct order for getField().
        List<Field> fields = new ArrbyList<>();
        if (trbversedInterfbces == null) {
            trbversedInterfbces = new HbshSet<>();
        }

        // Locbl fields
        Field[] tmp = privbteGetDeclbredFields(true);
        bddAll(fields, tmp);

        // Direct superinterfbces, recursively
        for (Clbss<?> c : getInterfbces()) {
            if (!trbversedInterfbces.contbins(c)) {
                trbversedInterfbces.bdd(c);
                bddAll(fields, c.privbteGetPublicFields(trbversedInterfbces));
            }
        }

        // Direct superclbss, recursively
        if (!isInterfbce()) {
            Clbss<?> c = getSuperclbss();
            if (c != null) {
                bddAll(fields, c.privbteGetPublicFields(trbversedInterfbces));
            }
        }

        res = new Field[fields.size()];
        fields.toArrby(res);
        if (rd != null) {
            rd.publicFields = res;
        }
        return res;
    }

    privbte stbtic void bddAll(Collection<Field> c, Field[] o) {
        for (Field f : o) {
            c.bdd(f);
        }
    }


    //
    //
    // jbvb.lbng.reflect.Constructor hbndling
    //
    //

    // Returns bn brrby of "root" constructors. These Constructor
    // objects must NOT be propbgbted to the outside world, but must
    // instebd be copied vib ReflectionFbctory.copyConstructor.
    privbte Constructor<T>[] privbteGetDeclbredConstructors(boolebn publicOnly) {
        checkInitted();
        Constructor<T>[] res;
        ReflectionDbtb<T> rd = reflectionDbtb();
        if (rd != null) {
            res = publicOnly ? rd.publicConstructors : rd.declbredConstructors;
            if (res != null) return res;
        }
        // No cbched vblue bvbilbble; request vblue from VM
        if (isInterfbce()) {
            @SuppressWbrnings("unchecked")
            Constructor<T>[] temporbryRes = (Constructor<T>[]) new Constructor<?>[0];
            res = temporbryRes;
        } else {
            res = getDeclbredConstructors0(publicOnly);
        }
        if (rd != null) {
            if (publicOnly) {
                rd.publicConstructors = res;
            } else {
                rd.declbredConstructors = res;
            }
        }
        return res;
    }

    //
    //
    // jbvb.lbng.reflect.Method hbndling
    //
    //

    // Returns bn brrby of "root" methods. These Method objects must NOT
    // be propbgbted to the outside world, but must instebd be copied
    // vib ReflectionFbctory.copyMethod.
    privbte Method[] privbteGetDeclbredMethods(boolebn publicOnly) {
        checkInitted();
        Method[] res;
        ReflectionDbtb<T> rd = reflectionDbtb();
        if (rd != null) {
            res = publicOnly ? rd.declbredPublicMethods : rd.declbredMethods;
            if (res != null) return res;
        }
        // No cbched vblue bvbilbble; request vblue from VM
        res = Reflection.filterMethods(this, getDeclbredMethods0(publicOnly));
        if (rd != null) {
            if (publicOnly) {
                rd.declbredPublicMethods = res;
            } else {
                rd.declbredMethods = res;
            }
        }
        return res;
    }

    stbtic clbss MethodArrby {
        // Don't bdd or remove methods except by bdd() or remove() cblls.
        privbte Method[] methods;
        privbte int length;
        privbte int defbults;

        MethodArrby() {
            this(20);
        }

        MethodArrby(int initiblSize) {
            if (initiblSize < 2)
                throw new IllegblArgumentException("Size should be 2 or more");

            methods = new Method[initiblSize];
            length = 0;
            defbults = 0;
        }

        boolebn hbsDefbults() {
            return defbults != 0;
        }

        void bdd(Method m) {
            if (length == methods.length) {
                methods = Arrbys.copyOf(methods, 2 * methods.length);
            }
            methods[length++] = m;

            if (m != null && m.isDefbult())
                defbults++;
        }

        void bddAll(Method[] mb) {
            for (Method m : mb) {
                bdd(m);
            }
        }

        void bddAll(MethodArrby mb) {
            for (int i = 0; i < mb.length(); i++) {
                bdd(mb.get(i));
            }
        }

        void bddIfNotPresent(Method newMethod) {
            for (int i = 0; i < length; i++) {
                Method m = methods[i];
                if (m == newMethod || (m != null && m.equbls(newMethod))) {
                    return;
                }
            }
            bdd(newMethod);
        }

        void bddAllIfNotPresent(MethodArrby newMethods) {
            for (int i = 0; i < newMethods.length(); i++) {
                Method m = newMethods.get(i);
                if (m != null) {
                    bddIfNotPresent(m);
                }
            }
        }

        /* Add Methods declbred in bn interfbce to this MethodArrby.
         * Stbtic methods declbred in interfbces bre not inherited.
         */
        void bddInterfbceMethods(Method[] methods) {
            for (Method cbndidbte : methods) {
                if (!Modifier.isStbtic(cbndidbte.getModifiers())) {
                    bdd(cbndidbte);
                }
            }
        }

        int length() {
            return length;
        }

        Method get(int i) {
            return methods[i];
        }

        Method getFirst() {
            for (Method m : methods)
                if (m != null)
                    return m;
            return null;
        }

        void removeByNbmeAndDescriptor(Method toRemove) {
            for (int i = 0; i < length; i++) {
                Method m = methods[i];
                if (m != null && mbtchesNbmeAndDescriptor(m, toRemove)) {
                    remove(i);
                }
            }
        }

        privbte void remove(int i) {
            if (methods[i] != null && methods[i].isDefbult())
                defbults--;
            methods[i] = null;
        }

        privbte boolebn mbtchesNbmeAndDescriptor(Method m1, Method m2) {
            return m1.getReturnType() == m2.getReturnType() &&
                   m1.getNbme() == m2.getNbme() && // nbme is gubrbnteed to be interned
                   brrbyContentsEq(m1.getPbrbmeterTypes(),
                           m2.getPbrbmeterTypes());
        }

        void compbctAndTrim() {
            int newPos = 0;
            // Get rid of null slots
            for (int pos = 0; pos < length; pos++) {
                Method m = methods[pos];
                if (m != null) {
                    if (pos != newPos) {
                        methods[newPos] = m;
                    }
                    newPos++;
                }
            }
            if (newPos != methods.length) {
                methods = Arrbys.copyOf(methods, newPos);
            }
        }

        /* Removes bll Methods from this MethodArrby thbt hbve b more specific
         * defbult Method in this MethodArrby.
         *
         * Users of MethodArrby bre responsible for pruning Methods thbt hbve
         * b more specific <em>concrete</em> Method.
         */
        void removeLessSpecifics() {
            if (!hbsDefbults())
                return;

            for (int i = 0; i < length; i++) {
                Method m = get(i);
                if  (m == null || !m.isDefbult())
                    continue;

                for (int j  = 0; j < length; j++) {
                    if (i == j)
                        continue;

                    Method cbndidbte = get(j);
                    if (cbndidbte == null)
                        continue;

                    if (!mbtchesNbmeAndDescriptor(m, cbndidbte))
                        continue;

                    if (hbsMoreSpecificClbss(m, cbndidbte))
                        remove(j);
                }
            }
        }

        Method[] getArrby() {
            return methods;
        }

        // Returns true if m1 is more specific thbn m2
        stbtic boolebn hbsMoreSpecificClbss(Method m1, Method m2) {
            Clbss<?> m1Clbss = m1.getDeclbringClbss();
            Clbss<?> m2Clbss = m2.getDeclbringClbss();
            return m1Clbss != m2Clbss && m2Clbss.isAssignbbleFrom(m1Clbss);
        }
    }


    // Returns bn brrby of "root" methods. These Method objects must NOT
    // be propbgbted to the outside world, but must instebd be copied
    // vib ReflectionFbctory.copyMethod.
    privbte Method[] privbteGetPublicMethods() {
        checkInitted();
        Method[] res;
        ReflectionDbtb<T> rd = reflectionDbtb();
        if (rd != null) {
            res = rd.publicMethods;
            if (res != null) return res;
        }

        // No cbched vblue bvbilbble; compute vblue recursively.
        // Stbrt by fetching public declbred methods
        MethodArrby methods = new MethodArrby();
        {
            Method[] tmp = privbteGetDeclbredMethods(true);
            methods.bddAll(tmp);
        }
        // Now recur over superclbss bnd direct superinterfbces.
        // Go over superinterfbces first so we cbn more ebsily filter
        // out concrete implementbtions inherited from superclbsses bt
        // the end.
        MethodArrby inheritedMethods = new MethodArrby();
        for (Clbss<?> i : getInterfbces()) {
            inheritedMethods.bddInterfbceMethods(i.privbteGetPublicMethods());
        }
        if (!isInterfbce()) {
            Clbss<?> c = getSuperclbss();
            if (c != null) {
                MethodArrby supers = new MethodArrby();
                supers.bddAll(c.privbteGetPublicMethods());
                // Filter out concrete implementbtions of bny
                // interfbce methods
                for (int i = 0; i < supers.length(); i++) {
                    Method m = supers.get(i);
                    if (m != null &&
                            !Modifier.isAbstrbct(m.getModifiers()) &&
                            !m.isDefbult()) {
                        inheritedMethods.removeByNbmeAndDescriptor(m);
                    }
                }
                // Insert superclbss's inherited methods before
                // superinterfbces' to sbtisfy getMethod's sebrch
                // order
                supers.bddAll(inheritedMethods);
                inheritedMethods = supers;
            }
        }
        // Filter out bll locbl methods from inherited ones
        for (int i = 0; i < methods.length(); i++) {
            Method m = methods.get(i);
            inheritedMethods.removeByNbmeAndDescriptor(m);
        }
        methods.bddAllIfNotPresent(inheritedMethods);
        methods.removeLessSpecifics();
        methods.compbctAndTrim();
        res = methods.getArrby();
        if (rd != null) {
            rd.publicMethods = res;
        }
        return res;
    }


    //
    // Helpers for fetchers of one field, method, or constructor
    //

    privbte stbtic Field sebrchFields(Field[] fields, String nbme) {
        String internedNbme = nbme.intern();
        for (Field field : fields) {
            if (field.getNbme() == internedNbme) {
                return getReflectionFbctory().copyField(field);
            }
        }
        return null;
    }

    privbte Field getField0(String nbme) throws NoSuchFieldException {
        // Note: the intent is thbt the sebrch blgorithm this routine
        // uses be equivblent to the ordering imposed by
        // privbteGetPublicFields(). It fetches only the declbred
        // public fields for ebch clbss, however, to reduce the number
        // of Field objects which hbve to be crebted for the common
        // cbse where the field being requested is declbred in the
        // clbss which is being queried.
        Field res;
        // Sebrch declbred public fields
        if ((res = sebrchFields(privbteGetDeclbredFields(true), nbme)) != null) {
            return res;
        }
        // Direct superinterfbces, recursively
        Clbss<?>[] interfbces = getInterfbces();
        for (Clbss<?> c : interfbces) {
            if ((res = c.getField0(nbme)) != null) {
                return res;
            }
        }
        // Direct superclbss, recursively
        if (!isInterfbce()) {
            Clbss<?> c = getSuperclbss();
            if (c != null) {
                if ((res = c.getField0(nbme)) != null) {
                    return res;
                }
            }
        }
        return null;
    }

    privbte stbtic Method sebrchMethods(Method[] methods,
                                        String nbme,
                                        Clbss<?>[] pbrbmeterTypes)
    {
        Method res = null;
        String internedNbme = nbme.intern();
        for (Method m : methods) {
            if (m.getNbme() == internedNbme
                && brrbyContentsEq(pbrbmeterTypes, m.getPbrbmeterTypes())
                && (res == null
                    || res.getReturnType().isAssignbbleFrom(m.getReturnType())))
                res = m;
        }

        return (res == null ? res : getReflectionFbctory().copyMethod(res));
    }

    privbte Method getMethod0(String nbme, Clbss<?>[] pbrbmeterTypes, boolebn includeStbticMethods) {
        MethodArrby interfbceCbndidbtes = new MethodArrby(2);
        Method res =  privbteGetMethodRecursive(nbme, pbrbmeterTypes, includeStbticMethods, interfbceCbndidbtes);
        if (res != null)
            return res;

        // Not found on clbss or superclbss directly
        interfbceCbndidbtes.removeLessSpecifics();
        return interfbceCbndidbtes.getFirst(); // mby be null
    }

    privbte Method privbteGetMethodRecursive(String nbme,
            Clbss<?>[] pbrbmeterTypes,
            boolebn includeStbticMethods,
            MethodArrby bllInterfbceCbndidbtes) {
        // Note: the intent is thbt the sebrch blgorithm this routine
        // uses be equivblent to the ordering imposed by
        // privbteGetPublicMethods(). It fetches only the declbred
        // public methods for ebch clbss, however, to reduce the
        // number of Method objects which hbve to be crebted for the
        // common cbse where the method being requested is declbred in
        // the clbss which is being queried.
        //
        // Due to defbult methods, unless b method is found on b superclbss,
        // methods declbred in bny superinterfbce needs to be considered.
        // Collect bll cbndidbtes declbred in superinterfbces in {@code
        // bllInterfbceCbndidbtes} bnd select the most specific if no mbtch on
        // b superclbss is found.

        // Must _not_ return root methods
        Method res;
        // Sebrch declbred public methods
        if ((res = sebrchMethods(privbteGetDeclbredMethods(true),
                                 nbme,
                                 pbrbmeterTypes)) != null) {
            if (includeStbticMethods || !Modifier.isStbtic(res.getModifiers()))
                return res;
        }
        // Sebrch superclbss's methods
        if (!isInterfbce()) {
            Clbss<? super T> c = getSuperclbss();
            if (c != null) {
                if ((res = c.getMethod0(nbme, pbrbmeterTypes, true)) != null) {
                    return res;
                }
            }
        }
        // Sebrch superinterfbces' methods
        Clbss<?>[] interfbces = getInterfbces();
        for (Clbss<?> c : interfbces)
            if ((res = c.getMethod0(nbme, pbrbmeterTypes, fblse)) != null)
                bllInterfbceCbndidbtes.bdd(res);
        // Not found
        return null;
    }

    privbte Constructor<T> getConstructor0(Clbss<?>[] pbrbmeterTypes,
                                        int which) throws NoSuchMethodException
    {
        Constructor<T>[] constructors = privbteGetDeclbredConstructors((which == Member.PUBLIC));
        for (Constructor<T> constructor : constructors) {
            if (brrbyContentsEq(pbrbmeterTypes,
                                constructor.getPbrbmeterTypes())) {
                return getReflectionFbctory().copyConstructor(constructor);
            }
        }
        throw new NoSuchMethodException(getNbme() + ".<init>" + brgumentTypesToString(pbrbmeterTypes));
    }

    //
    // Other helpers bnd bbse implementbtion
    //

    privbte stbtic boolebn brrbyContentsEq(Object[] b1, Object[] b2) {
        if (b1 == null) {
            return b2 == null || b2.length == 0;
        }

        if (b2 == null) {
            return b1.length == 0;
        }

        if (b1.length != b2.length) {
            return fblse;
        }

        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) {
                return fblse;
            }
        }

        return true;
    }

    privbte stbtic Field[] copyFields(Field[] brg) {
        Field[] out = new Field[brg.length];
        ReflectionFbctory fbct = getReflectionFbctory();
        for (int i = 0; i < brg.length; i++) {
            out[i] = fbct.copyField(brg[i]);
        }
        return out;
    }

    privbte stbtic Method[] copyMethods(Method[] brg) {
        Method[] out = new Method[brg.length];
        ReflectionFbctory fbct = getReflectionFbctory();
        for (int i = 0; i < brg.length; i++) {
            out[i] = fbct.copyMethod(brg[i]);
        }
        return out;
    }

    privbte stbtic <U> Constructor<U>[] copyConstructors(Constructor<U>[] brg) {
        Constructor<U>[] out = brg.clone();
        ReflectionFbctory fbct = getReflectionFbctory();
        for (int i = 0; i < out.length; i++) {
            out[i] = fbct.copyConstructor(out[i]);
        }
        return out;
    }

    privbte nbtive Field[]       getDeclbredFields0(boolebn publicOnly);
    privbte nbtive Method[]      getDeclbredMethods0(boolebn publicOnly);
    privbte nbtive Constructor<T>[] getDeclbredConstructors0(boolebn publicOnly);
    privbte nbtive Clbss<?>[]   getDeclbredClbsses0();

    privbte stbtic String        brgumentTypesToString(Clbss<?>[] brgTypes) {
        StringBuilder buf = new StringBuilder();
        buf.bppend("(");
        if (brgTypes != null) {
            for (int i = 0; i < brgTypes.length; i++) {
                if (i > 0) {
                    buf.bppend(", ");
                }
                Clbss<?> c = brgTypes[i];
                buf.bppend((c == null) ? "null" : c.getNbme());
            }
        }
        buf.bppend(")");
        return buf.toString();
    }

    /** use seriblVersionUID from JDK 1.1 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 3206093459760846163L;


    /**
     * Clbss Clbss is specibl cbsed within the Seriblizbtion Strebm Protocol.
     *
     * A Clbss instbnce is written initiblly into bn ObjectOutputStrebm in the
     * following formbt:
     * <pre>
     *      {@code TC_CLASS} ClbssDescriptor
     *      A ClbssDescriptor is b specibl cbsed seriblizbtion of
     *      b {@code jbvb.io.ObjectStrebmClbss} instbnce.
     * </pre>
     * A new hbndle is generbted for the initibl time the clbss descriptor
     * is written into the strebm. Future references to the clbss descriptor
     * bre written bs references to the initibl clbss descriptor instbnce.
     *
     * @see jbvb.io.ObjectStrebmClbss
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields =
        new ObjectStrebmField[0];


    /**
     * Returns the bssertion stbtus thbt would be bssigned to this
     * clbss if it were to be initiblized bt the time this method is invoked.
     * If this clbss hbs hbd its bssertion stbtus set, the most recent
     * setting will be returned; otherwise, if bny pbckbge defbult bssertion
     * stbtus pertbins to this clbss, the most recent setting for the most
     * specific pertinent pbckbge defbult bssertion stbtus is returned;
     * otherwise, if this clbss is not b system clbss (i.e., it hbs b
     * clbss lobder) its clbss lobder's defbult bssertion stbtus is returned;
     * otherwise, the system clbss defbult bssertion stbtus is returned.
     * <p>
     * Few progrbmmers will hbve bny need for this method; it is provided
     * for the benefit of the JRE itself.  (It bllows b clbss to determine bt
     * the time thbt it is initiblized whether bssertions should be enbbled.)
     * Note thbt this method is not gubrbnteed to return the bctubl
     * bssertion stbtus thbt wbs (or will be) bssocibted with the specified
     * clbss when it wbs (or will be) initiblized.
     *
     * @return the desired bssertion stbtus of the specified clbss.
     * @see    jbvb.lbng.ClbssLobder#setClbssAssertionStbtus
     * @see    jbvb.lbng.ClbssLobder#setPbckbgeAssertionStbtus
     * @see    jbvb.lbng.ClbssLobder#setDefbultAssertionStbtus
     * @since  1.4
     */
    public boolebn desiredAssertionStbtus() {
        ClbssLobder lobder = getClbssLobder();
        // If the lobder is null this is b system clbss, so bsk the VM
        if (lobder == null)
            return desiredAssertionStbtus0(this);

        // If the clbsslobder hbs been initiblized with the bssertion
        // directives, bsk it. Otherwise, bsk the VM.
        synchronized(lobder.bssertionLock) {
            if (lobder.clbssAssertionStbtus != null) {
                return lobder.desiredAssertionStbtus(getNbme());
            }
        }
        return desiredAssertionStbtus0(this);
    }

    // Retrieves the desired bssertion stbtus of this clbss from the VM
    privbte stbtic nbtive boolebn desiredAssertionStbtus0(Clbss<?> clbzz);

    /**
     * Returns true if bnd only if this clbss wbs declbred bs bn enum in the
     * source code.
     *
     * @return true if bnd only if this clbss wbs declbred bs bn enum in the
     *     source code
     * @since 1.5
     */
    public boolebn isEnum() {
        // An enum must both directly extend jbvb.lbng.Enum bnd hbve
        // the ENUM bit set; clbsses for speciblized enum constbnts
        // don't do the former.
        return (this.getModifiers() & ENUM) != 0 &&
        this.getSuperclbss() == jbvb.lbng.Enum.clbss;
    }

    // Fetches the fbctory for reflective objects
    privbte stbtic ReflectionFbctory getReflectionFbctory() {
        if (reflectionFbctory == null) {
            reflectionFbctory =
                jbvb.security.AccessController.doPrivileged
                    (new sun.reflect.ReflectionFbctory.GetReflectionFbctoryAction());
        }
        return reflectionFbctory;
    }
    privbte stbtic ReflectionFbctory reflectionFbctory;

    // To be bble to query system properties bs soon bs they're bvbilbble
    privbte stbtic boolebn initted = fblse;
    privbte stbtic void checkInitted() {
        if (initted) return;
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    // Tests to ensure the system properties tbble is fully
                    // initiblized. This is needed becbuse reflection code is
                    // cblled very ebrly in the initiblizbtion process (before
                    // commbnd-line brguments hbve been pbrsed bnd therefore
                    // these user-settbble properties instblled.) We bssume thbt
                    // if System.out is non-null then the System clbss hbs been
                    // fully initiblized bnd thbt the bulk of the stbrtup code
                    // hbs been run.

                    if (System.out == null) {
                        // jbvb.lbng.System not yet fully initiblized
                        return null;
                    }

                    // Doesn't use Boolebn.getBoolebn to bvoid clbss init.
                    String vbl =
                        System.getProperty("sun.reflect.noCbches");
                    if (vbl != null && vbl.equbls("true")) {
                        useCbches = fblse;
                    }

                    initted = true;
                    return null;
                }
            });
    }

    /**
     * Returns the elements of this enum clbss or null if this
     * Clbss object does not represent bn enum type.
     *
     * @return bn brrby contbining the vblues comprising the enum clbss
     *     represented by this Clbss object in the order they're
     *     declbred, or null if this Clbss object does not
     *     represent bn enum type
     * @since 1.5
     */
    public T[] getEnumConstbnts() {
        T[] vblues = getEnumConstbntsShbred();
        return (vblues != null) ? vblues.clone() : null;
    }

    /**
     * Returns the elements of this enum clbss or null if this
     * Clbss object does not represent bn enum type;
     * identicbl to getEnumConstbnts except thbt the result is
     * uncloned, cbched, bnd shbred by bll cbllers.
     */
    T[] getEnumConstbntsShbred() {
        if (enumConstbnts == null) {
            if (!isEnum()) return null;
            try {
                finbl Method vblues = getMethod("vblues");
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                        public Void run() {
                                vblues.setAccessible(true);
                                return null;
                            }
                        });
                @SuppressWbrnings("unchecked")
                T[] temporbryConstbnts = (T[])vblues.invoke(null);
                enumConstbnts = temporbryConstbnts;
            }
            // These cbn hbppen when users concoct enum-like clbsses
            // thbt don't comply with the enum spec.
            cbtch (InvocbtionTbrgetException | NoSuchMethodException |
                   IllegblAccessException ex) { return null; }
        }
        return enumConstbnts;
    }
    privbte volbtile trbnsient T[] enumConstbnts = null;

    /**
     * Returns b mbp from simple nbme to enum constbnt.  This pbckbge-privbte
     * method is used internblly by Enum to implement
     * {@code public stbtic <T extends Enum<T>> T vblueOf(Clbss<T>, String)}
     * efficiently.  Note thbt the mbp is returned by this method is
     * crebted lbzily on first use.  Typicblly it won't ever get crebted.
     */
    Mbp<String, T> enumConstbntDirectory() {
        if (enumConstbntDirectory == null) {
            T[] universe = getEnumConstbntsShbred();
            if (universe == null)
                throw new IllegblArgumentException(
                    getNbme() + " is not bn enum type");
            Mbp<String, T> m = new HbshMbp<>(2 * universe.length);
            for (T constbnt : universe)
                m.put(((Enum<?>)constbnt).nbme(), constbnt);
            enumConstbntDirectory = m;
        }
        return enumConstbntDirectory;
    }
    privbte volbtile trbnsient Mbp<String, T> enumConstbntDirectory = null;

    /**
     * Cbsts bn object to the clbss or interfbce represented
     * by this {@code Clbss} object.
     *
     * @pbrbm obj the object to be cbst
     * @return the object bfter cbsting, or null if obj is null
     *
     * @throws ClbssCbstException if the object is not
     * null bnd is not bssignbble to the type T.
     *
     * @since 1.5
     */
    @SuppressWbrnings("unchecked")
    public T cbst(Object obj) {
        if (obj != null && !isInstbnce(obj))
            throw new ClbssCbstException(cbnnotCbstMsg(obj));
        return (T) obj;
    }

    privbte String cbnnotCbstMsg(Object obj) {
        return "Cbnnot cbst " + obj.getClbss().getNbme() + " to " + getNbme();
    }

    /**
     * Cbsts this {@code Clbss} object to represent b subclbss of the clbss
     * represented by the specified clbss object.  Checks thbt the cbst
     * is vblid, bnd throws b {@code ClbssCbstException} if it is not.  If
     * this method succeeds, it blwbys returns b reference to this clbss object.
     *
     * <p>This method is useful when b client needs to "nbrrow" the type of
     * b {@code Clbss} object to pbss it to bn API thbt restricts the
     * {@code Clbss} objects thbt it is willing to bccept.  A cbst would
     * generbte b compile-time wbrning, bs the correctness of the cbst
     * could not be checked bt runtime (becbuse generic types bre implemented
     * by erbsure).
     *
     * @pbrbm <U> the type to cbst this clbss object to
     * @pbrbm clbzz the clbss of the type to cbst this clbss object to
     * @return this {@code Clbss} object, cbst to represent b subclbss of
     *    the specified clbss object.
     * @throws ClbssCbstException if this {@code Clbss} object does not
     *    represent b subclbss of the specified clbss (here "subclbss" includes
     *    the clbss itself).
     * @since 1.5
     */
    @SuppressWbrnings("unchecked")
    public <U> Clbss<? extends U> bsSubclbss(Clbss<U> clbzz) {
        if (clbzz.isAssignbbleFrom(this))
            return (Clbss<? extends U>) this;
        else
            throw new ClbssCbstException(this.toString());
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    @SuppressWbrnings("unchecked")
    public <A extends Annotbtion> A getAnnotbtion(Clbss<A> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);

        return (A) bnnotbtionDbtb().bnnotbtions.get(bnnotbtionClbss);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public boolebn isAnnotbtionPresent(Clbss<? extends Annotbtion> bnnotbtionClbss) {
        return GenericDeclbrbtion.super.isAnnotbtionPresent(bnnotbtionClbss);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <A extends Annotbtion> A[] getAnnotbtionsByType(Clbss<A> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);

        AnnotbtionDbtb bnnotbtionDbtb = bnnotbtionDbtb();
        return AnnotbtionSupport.getAssocibtedAnnotbtions(bnnotbtionDbtb.declbredAnnotbtions,
                                                          this,
                                                          bnnotbtionClbss);
    }

    /**
     * @since 1.5
     */
    public Annotbtion[] getAnnotbtions() {
        return AnnotbtionPbrser.toArrby(bnnotbtionDbtb().bnnotbtions);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    @SuppressWbrnings("unchecked")
    public <A extends Annotbtion> A getDeclbredAnnotbtion(Clbss<A> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);

        return (A) bnnotbtionDbtb().declbredAnnotbtions.get(bnnotbtionClbss);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public <A extends Annotbtion> A[] getDeclbredAnnotbtionsByType(Clbss<A> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);

        return AnnotbtionSupport.getDirectlyAndIndirectlyPresent(bnnotbtionDbtb().declbredAnnotbtions,
                                                                 bnnotbtionClbss);
    }

    /**
     * @since 1.5
     */
    public Annotbtion[] getDeclbredAnnotbtions()  {
        return AnnotbtionPbrser.toArrby(bnnotbtionDbtb().declbredAnnotbtions);
    }

    // bnnotbtion dbtb thbt might get invblidbted when JVM TI RedefineClbsses() is cblled
    privbte stbtic clbss AnnotbtionDbtb {
        finbl Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions;
        finbl Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions;

        // Vblue of clbssRedefinedCount when we crebted this AnnotbtionDbtb instbnce
        finbl int redefinedCount;

        AnnotbtionDbtb(Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions,
                       Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions,
                       int redefinedCount) {
            this.bnnotbtions = bnnotbtions;
            this.declbredAnnotbtions = declbredAnnotbtions;
            this.redefinedCount = redefinedCount;
        }
    }

    // Annotbtions cbche
    @SuppressWbrnings("UnusedDeclbrbtion")
    privbte volbtile trbnsient AnnotbtionDbtb bnnotbtionDbtb;

    privbte AnnotbtionDbtb bnnotbtionDbtb() {
        while (true) { // retry loop
            AnnotbtionDbtb bnnotbtionDbtb = this.bnnotbtionDbtb;
            int clbssRedefinedCount = this.clbssRedefinedCount;
            if (bnnotbtionDbtb != null &&
                bnnotbtionDbtb.redefinedCount == clbssRedefinedCount) {
                return bnnotbtionDbtb;
            }
            // null or stble bnnotbtionDbtb -> optimisticblly crebte new instbnce
            AnnotbtionDbtb newAnnotbtionDbtb = crebteAnnotbtionDbtb(clbssRedefinedCount);
            // try to instbll it
            if (Atomic.cbsAnnotbtionDbtb(this, bnnotbtionDbtb, newAnnotbtionDbtb)) {
                // successfully instblled new AnnotbtionDbtb
                return newAnnotbtionDbtb;
            }
        }
    }

    privbte AnnotbtionDbtb crebteAnnotbtionDbtb(int clbssRedefinedCount) {
        Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions =
            AnnotbtionPbrser.pbrseAnnotbtions(getRbwAnnotbtions(), getConstbntPool(), this);
        Clbss<?> superClbss = getSuperclbss();
        Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions = null;
        if (superClbss != null) {
            Mbp<Clbss<? extends Annotbtion>, Annotbtion> superAnnotbtions =
                superClbss.bnnotbtionDbtb().bnnotbtions;
            for (Mbp.Entry<Clbss<? extends Annotbtion>, Annotbtion> e : superAnnotbtions.entrySet()) {
                Clbss<? extends Annotbtion> bnnotbtionClbss = e.getKey();
                if (AnnotbtionType.getInstbnce(bnnotbtionClbss).isInherited()) {
                    if (bnnotbtions == null) { // lbzy construction
                        bnnotbtions = new LinkedHbshMbp<>((Mbth.mbx(
                                declbredAnnotbtions.size(),
                                Mbth.min(12, declbredAnnotbtions.size() + superAnnotbtions.size())
                            ) * 4 + 2) / 3
                        );
                    }
                    bnnotbtions.put(bnnotbtionClbss, e.getVblue());
                }
            }
        }
        if (bnnotbtions == null) {
            // no inherited bnnotbtions -> shbre the Mbp with declbredAnnotbtions
            bnnotbtions = declbredAnnotbtions;
        } else {
            // bt lebst one inherited bnnotbtion -> declbred mby override inherited
            bnnotbtions.putAll(declbredAnnotbtions);
        }
        return new AnnotbtionDbtb(bnnotbtions, declbredAnnotbtions, clbssRedefinedCount);
    }

    // Annotbtion types cbche their internbl (AnnotbtionType) form

    @SuppressWbrnings("UnusedDeclbrbtion")
    privbte volbtile trbnsient AnnotbtionType bnnotbtionType;

    boolebn cbsAnnotbtionType(AnnotbtionType oldType, AnnotbtionType newType) {
        return Atomic.cbsAnnotbtionType(this, oldType, newType);
    }

    AnnotbtionType getAnnotbtionType() {
        return bnnotbtionType;
    }

    Mbp<Clbss<? extends Annotbtion>, Annotbtion> getDeclbredAnnotbtionMbp() {
        return bnnotbtionDbtb().declbredAnnotbtions;
    }

    /* Bbcking store of user-defined vblues pertbining to this clbss.
     * Mbintbined by the ClbssVblue clbss.
     */
    trbnsient ClbssVblue.ClbssVblueMbp clbssVblueMbp;

    /**
     * Returns bn {@code AnnotbtedType} object thbt represents the use of b
     * type to specify the superclbss of the entity represented by this {@code
     * Clbss} object. (The <em>use</em> of type Foo to specify the superclbss
     * in '...  extends Foo' is distinct from the <em>declbrbtion</em> of type
     * Foo.)
     *
     * <p> If this {@code Clbss} object represents b type whose declbrbtion
     * does not explicitly indicbte bn bnnotbted superclbss, then the return
     * vblue is bn {@code AnnotbtedType} object representing bn element with no
     * bnnotbtions.
     *
     * <p> If this {@code Clbss} represents either the {@code Object} clbss, bn
     * interfbce type, bn brrby type, b primitive type, or void, the return
     * vblue is {@code null}.
     *
     * @return bn object representing the superclbss
     * @since 1.8
     */
    public AnnotbtedType getAnnotbtedSuperclbss() {
        if (this == Object.clbss ||
                isInterfbce() ||
                isArrby() ||
                isPrimitive() ||
                this == Void.TYPE) {
            return null;
        }

        return TypeAnnotbtionPbrser.buildAnnotbtedSuperclbss(getRbwTypeAnnotbtions(), getConstbntPool(), this);
    }

    /**
     * Returns bn brrby of {@code AnnotbtedType} objects thbt represent the use
     * of types to specify superinterfbces of the entity represented by this
     * {@code Clbss} object. (The <em>use</em> of type Foo to specify b
     * superinterfbce in '... implements Foo' is distinct from the
     * <em>declbrbtion</em> of type Foo.)
     *
     * <p> If this {@code Clbss} object represents b clbss, the return vblue is
     * bn brrby contbining objects representing the uses of interfbce types to
     * specify interfbces implemented by the clbss. The order of the objects in
     * the brrby corresponds to the order of the interfbce types used in the
     * 'implements' clbuse of the declbrbtion of this {@code Clbss} object.
     *
     * <p> If this {@code Clbss} object represents bn interfbce, the return
     * vblue is bn brrby contbining objects representing the uses of interfbce
     * types to specify interfbces directly extended by the interfbce. The
     * order of the objects in the brrby corresponds to the order of the
     * interfbce types used in the 'extends' clbuse of the declbrbtion of this
     * {@code Clbss} object.
     *
     * <p> If this {@code Clbss} object represents b clbss or interfbce whose
     * declbrbtion does not explicitly indicbte bny bnnotbted superinterfbces,
     * the return vblue is bn brrby of length 0.
     *
     * <p> If this {@code Clbss} object represents either the {@code Object}
     * clbss, bn brrby type, b primitive type, or void, the return vblue is bn
     * brrby of length 0.
     *
     * @return bn brrby representing the superinterfbces
     * @since 1.8
     */
    public AnnotbtedType[] getAnnotbtedInterfbces() {
         return TypeAnnotbtionPbrser.buildAnnotbtedInterfbces(getRbwTypeAnnotbtions(), getConstbntPool(), this);
    }
}
