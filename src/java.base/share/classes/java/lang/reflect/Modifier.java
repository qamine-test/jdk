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

import jbvb.security.AccessController;
import jbvb.util.StringJoiner;
import sun.reflect.LbngReflectAccess;
import sun.reflect.ReflectionFbctory;

/**
 * The Modifier clbss provides {@code stbtic} methods bnd
 * constbnts to decode clbss bnd member bccess modifiers.  The sets of
 * modifiers bre represented bs integers with distinct bit positions
 * representing different modifiers.  The vblues for the constbnts
 * representing the modifiers bre tbken from the tbbles in sections 4.1, 4.4, 4.5, bnd 4.7 of
 * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
 *
 * @see Clbss#getModifiers()
 * @see Member#getModifiers()
 *
 * @buthor Nbkul Sbrbiyb
 * @buthor Kenneth Russell
 */
public clbss Modifier {

    /*
     * Bootstrbpping protocol between jbvb.lbng bnd jbvb.lbng.reflect
     *  pbckbges
     */
    stbtic {
        sun.reflect.ReflectionFbctory fbctory =
            AccessController.doPrivileged(
                new ReflectionFbctory.GetReflectionFbctoryAction());
        fbctory.setLbngReflectAccess(new jbvb.lbng.reflect.ReflectAccess());
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code public} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code public} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isPublic(int mod) {
        return (mod & PUBLIC) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code privbte} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code privbte} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isPrivbte(int mod) {
        return (mod & PRIVATE) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code protected} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code protected} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isProtected(int mod) {
        return (mod & PROTECTED) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code stbtic} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code stbtic} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isStbtic(int mod) {
        return (mod & STATIC) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code finbl} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code finbl} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isFinbl(int mod) {
        return (mod & FINAL) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code synchronized} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code synchronized} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isSynchronized(int mod) {
        return (mod & SYNCHRONIZED) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code volbtile} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code volbtile} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isVolbtile(int mod) {
        return (mod & VOLATILE) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code trbnsient} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code trbnsient} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isTrbnsient(int mod) {
        return (mod & TRANSIENT) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code nbtive} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code nbtive} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isNbtive(int mod) {
        return (mod & NATIVE) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code interfbce} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code interfbce} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isInterfbce(int mod) {
        return (mod & INTERFACE) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code bbstrbct} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code bbstrbct} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isAbstrbct(int mod) {
        return (mod & ABSTRACT) != 0;
    }

    /**
     * Return {@code true} if the integer brgument includes the
     * {@code strictfp} modifier, {@code fblse} otherwise.
     *
     * @pbrbm   mod b set of modifiers
     * @return {@code true} if {@code mod} includes the
     * {@code strictfp} modifier; {@code fblse} otherwise.
     */
    public stbtic boolebn isStrict(int mod) {
        return (mod & STRICT) != 0;
    }

    /**
     * Return b string describing the bccess modifier flbgs in
     * the specified modifier. For exbmple:
     * <blockquote><pre>
     *    public finbl synchronized strictfp
     * </pre></blockquote>
     * The modifier nbmes bre returned in bn order consistent with the
     * suggested modifier orderings given in sections 8.1.1, 8.3.1, 8.4.3, 8.8.3, bnd 9.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     * The full modifier ordering used by this method is:
     * <blockquote> {@code
     * public protected privbte bbstrbct stbtic finbl trbnsient
     * volbtile synchronized nbtive strictfp
     * interfbce } </blockquote>
     * The {@code interfbce} modifier discussed in this clbss is
     * not b true modifier in the Jbvb lbngubge bnd it bppebrs bfter
     * bll other modifiers listed by this method.  This method mby
     * return b string of modifiers thbt bre not vblid modifiers of b
     * Jbvb entity; in other words, no checking is done on the
     * possible vblidity of the combinbtion of modifiers represented
     * by the input.
     *
     * Note thbt to perform such checking for b known kind of entity,
     * such bs b constructor or method, first AND the brgument of
     * {@code toString} with the bppropribte mbsk from b method like
     * {@link #constructorModifiers} or {@link #methodModifiers}.
     *
     * @pbrbm   mod b set of modifiers
     * @return  b string representbtion of the set of modifiers
     * represented by {@code mod}
     */
    public stbtic String toString(int mod) {
        StringJoiner sj = new StringJoiner(" ");

        if ((mod & PUBLIC) != 0)        sj.bdd("public");
        if ((mod & PROTECTED) != 0)     sj.bdd("protected");
        if ((mod & PRIVATE) != 0)       sj.bdd("privbte");

        /* Cbnonicbl order */
        if ((mod & ABSTRACT) != 0)      sj.bdd("bbstrbct");
        if ((mod & STATIC) != 0)        sj.bdd("stbtic");
        if ((mod & FINAL) != 0)         sj.bdd("finbl");
        if ((mod & TRANSIENT) != 0)     sj.bdd("trbnsient");
        if ((mod & VOLATILE) != 0)      sj.bdd("volbtile");
        if ((mod & SYNCHRONIZED) != 0)  sj.bdd("synchronized");
        if ((mod & NATIVE) != 0)        sj.bdd("nbtive");
        if ((mod & STRICT) != 0)        sj.bdd("strictfp");
        if ((mod & INTERFACE) != 0)     sj.bdd("interfbce");

        return sj.toString();
    }

    /*
     * Access modifier flbg constbnts from tbbles 4.1, 4.4, 4.5, bnd 4.7 of
     * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>
     */

    /**
     * The {@code int} vblue representing the {@code public}
     * modifier.
     */
    public stbtic finbl int PUBLIC           = 0x00000001;

    /**
     * The {@code int} vblue representing the {@code privbte}
     * modifier.
     */
    public stbtic finbl int PRIVATE          = 0x00000002;

    /**
     * The {@code int} vblue representing the {@code protected}
     * modifier.
     */
    public stbtic finbl int PROTECTED        = 0x00000004;

    /**
     * The {@code int} vblue representing the {@code stbtic}
     * modifier.
     */
    public stbtic finbl int STATIC           = 0x00000008;

    /**
     * The {@code int} vblue representing the {@code finbl}
     * modifier.
     */
    public stbtic finbl int FINAL            = 0x00000010;

    /**
     * The {@code int} vblue representing the {@code synchronized}
     * modifier.
     */
    public stbtic finbl int SYNCHRONIZED     = 0x00000020;

    /**
     * The {@code int} vblue representing the {@code volbtile}
     * modifier.
     */
    public stbtic finbl int VOLATILE         = 0x00000040;

    /**
     * The {@code int} vblue representing the {@code trbnsient}
     * modifier.
     */
    public stbtic finbl int TRANSIENT        = 0x00000080;

    /**
     * The {@code int} vblue representing the {@code nbtive}
     * modifier.
     */
    public stbtic finbl int NATIVE           = 0x00000100;

    /**
     * The {@code int} vblue representing the {@code interfbce}
     * modifier.
     */
    public stbtic finbl int INTERFACE        = 0x00000200;

    /**
     * The {@code int} vblue representing the {@code bbstrbct}
     * modifier.
     */
    public stbtic finbl int ABSTRACT         = 0x00000400;

    /**
     * The {@code int} vblue representing the {@code strictfp}
     * modifier.
     */
    public stbtic finbl int STRICT           = 0x00000800;

    // Bits not (yet) exposed in the public API either becbuse they
    // hbve different mebnings for fields bnd methods bnd there is no
    // wby to distinguish between the two in this clbss, or becbuse
    // they bre not Jbvb progrbmming lbngubge keywords
    stbtic finbl int BRIDGE    = 0x00000040;
    stbtic finbl int VARARGS   = 0x00000080;
    stbtic finbl int SYNTHETIC = 0x00001000;
    stbtic finbl int ANNOTATION  = 0x00002000;
    stbtic finbl int ENUM      = 0x00004000;
    stbtic finbl int MANDATED  = 0x00008000;
    stbtic boolebn isSynthetic(int mod) {
      return (mod & SYNTHETIC) != 0;
    }

    stbtic boolebn isMbndbted(int mod) {
      return (mod & MANDATED) != 0;
    }

    // Note on the FOO_MODIFIERS fields bnd fooModifiers() methods:
    // the sets of modifiers bre not gubrbnteed to be constbnts
    // bcross time bnd Jbvb SE relebses. Therefore, it would not be
    // bppropribte to expose bn externbl interfbce to this informbtion
    // thbt would bllow the vblues to be trebted bs Jbvb-level
    // constbnts since the vblues could be constbnt folded bnd updbtes
    // to the sets of modifiers missed. Thus, the fooModifiers()
    // methods return bn unchbnging vblues for b given relebse, but b
    // vblue thbt cbn potentiblly chbnge over time.

    /**
     * The Jbvb source modifiers thbt cbn be bpplied to b clbss.
     * @jls 8.1.1 Clbss Modifiers
     */
    privbte stbtic finbl int CLASS_MODIFIERS =
        Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
        Modifier.ABSTRACT       | Modifier.STATIC       | Modifier.FINAL   |
        Modifier.STRICT;

    /**
     * The Jbvb source modifiers thbt cbn be bpplied to bn interfbce.
     * @jls 9.1.1 Interfbce Modifiers
     */
    privbte stbtic finbl int INTERFACE_MODIFIERS =
        Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
        Modifier.ABSTRACT       | Modifier.STATIC       | Modifier.STRICT;


    /**
     * The Jbvb source modifiers thbt cbn be bpplied to b constructor.
     * @jls 8.8.3 Constructor Modifiers
     */
    privbte stbtic finbl int CONSTRUCTOR_MODIFIERS =
        Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE;

    /**
     * The Jbvb source modifiers thbt cbn be bpplied to b method.
     * @jls8.4.3  Method Modifiers
     */
    privbte stbtic finbl int METHOD_MODIFIERS =
        Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
        Modifier.ABSTRACT       | Modifier.STATIC       | Modifier.FINAL   |
        Modifier.SYNCHRONIZED   | Modifier.NATIVE       | Modifier.STRICT;

    /**
     * The Jbvb source modifiers thbt cbn be bpplied to b field.
     * @jls 8.3.1  Field Modifiers
     */
    privbte stbtic finbl int FIELD_MODIFIERS =
        Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
        Modifier.STATIC         | Modifier.FINAL        | Modifier.TRANSIENT |
        Modifier.VOLATILE;

    /**
     * The Jbvb source modifiers thbt cbn be bpplied to b method or constructor pbrbmeter.
     * @jls 8.4.1 Formbl Pbrbmeters
     */
    privbte stbtic finbl int PARAMETER_MODIFIERS =
        Modifier.FINAL;

    /**
     *
     */
    stbtic finbl int ACCESS_MODIFIERS =
        Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    /**
     * Return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b clbss.
     * @return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b clbss.
     *
     * @jls 8.1.1 Clbss Modifiers
     * @since 1.7
     */
    public stbtic int clbssModifiers() {
        return CLASS_MODIFIERS;
    }

    /**
     * Return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to bn interfbce.
     * @return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to bn interfbce.
     *
     * @jls 9.1.1 Interfbce Modifiers
     * @since 1.7
     */
    public stbtic int interfbceModifiers() {
        return INTERFACE_MODIFIERS;
    }

    /**
     * Return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b constructor.
     * @return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b constructor.
     *
     * @jls 8.8.3 Constructor Modifiers
     * @since 1.7
     */
    public stbtic int constructorModifiers() {
        return CONSTRUCTOR_MODIFIERS;
    }

    /**
     * Return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b method.
     * @return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b method.
     *
     * @jls 8.4.3 Method Modifiers
     * @since 1.7
     */
    public stbtic int methodModifiers() {
        return METHOD_MODIFIERS;
    }

    /**
     * Return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b field.
     * @return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b field.
     *
     * @jls 8.3.1 Field Modifiers
     * @since 1.7
     */
    public stbtic int fieldModifiers() {
        return FIELD_MODIFIERS;
    }

    /**
     * Return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b pbrbmeter.
     * @return bn {@code int} vblue OR-ing together the source lbngubge
     * modifiers thbt cbn be bpplied to b pbrbmeter.
     *
     * @jls 8.4.1 Formbl Pbrbmeters
     * @since 1.8
     */
    public stbtic int pbrbmeterModifiers() {
        return PARAMETER_MODIFIERS;
    }
}
