/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Executbble;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Modifier;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import sun.reflect.misc.ReflectUtil;

/** <P> The mbster fbctory for bll reflective objects, both those in
    jbvb.lbng.reflect (Fields, Methods, Constructors) bs well bs their
    delegbtes (FieldAccessors, MethodAccessors, ConstructorAccessors).
    </P>

    <P> The methods in this clbss bre extremely unsbfe bnd cbn cbuse
    subversion of both the lbngubge bnd the verifier. For this rebson,
    they bre bll instbnce methods, bnd bccess to the constructor of
    this fbctory is gubrded by b security check, in similbr style to
    {@link sun.misc.Unsbfe}. </P>
*/

public clbss ReflectionFbctory {

    privbte stbtic boolebn initted = fblse;
    privbte stbtic Permission reflectionFbctoryAccessPerm
        = new RuntimePermission("reflectionFbctoryAccess");
    privbte stbtic ReflectionFbctory soleInstbnce = new ReflectionFbctory();
    // Provides bccess to pbckbge-privbte mechbnisms in jbvb.lbng.reflect
    privbte stbtic volbtile LbngReflectAccess lbngReflectAccess;

    //
    // "Inflbtion" mechbnism. Lobding bytecodes to implement
    // Method.invoke() bnd Constructor.newInstbnce() currently costs
    // 3-4x more thbn bn invocbtion vib nbtive code for the first
    // invocbtion (though subsequent invocbtions hbve been benchmbrked
    // to be over 20x fbster). Unfortunbtely this cost increbses
    // stbrtup time for certbin bpplicbtions thbt use reflection
    // intensively (but only once per clbss) to bootstrbp themselves.
    // To bvoid this penblty we reuse the existing JVM entry points
    // for the first few invocbtions of Methods bnd Constructors bnd
    // then switch to the bytecode-bbsed implementbtions.
    //
    // Pbckbge-privbte to be bccessible to NbtiveMethodAccessorImpl
    // bnd NbtiveConstructorAccessorImpl
    privbte stbtic boolebn noInflbtion        = fblse;
    privbte stbtic int     inflbtionThreshold = 15;

    privbte ReflectionFbctory() {
    }

    /**
     * A convenience clbss for bcquiring the cbpbbility to instbntibte
     * reflective objects.  Use this instebd of b rbw cbll to {@link
     * #getReflectionFbctory} in order to bvoid being limited by the
     * permissions of your cbllers.
     *
     * <p>An instbnce of this clbss cbn be used bs the brgument of
     * <code>AccessController.doPrivileged</code>.
     */
    public stbtic finbl clbss GetReflectionFbctoryAction
        implements PrivilegedAction<ReflectionFbctory> {
        public ReflectionFbctory run() {
            return getReflectionFbctory();
        }
    }

    /**
     * Provides the cbller with the cbpbbility to instbntibte reflective
     * objects.
     *
     * <p> First, if there is b security mbnbger, its
     * <code>checkPermission</code> method is cblled with b {@link
     * jbvb.lbng.RuntimePermission} with tbrget
     * <code>"reflectionFbctoryAccess"</code>.  This mby result in b
     * security exception.
     *
     * <p> The returned <code>ReflectionFbctory</code> object should be
     * cbrefully gubrded by the cbller, since it cbn be used to rebd bnd
     * write privbte dbtb bnd invoke privbte methods, bs well bs to lobd
     * unverified bytecodes.  It must never be pbssed to untrusted code.
     *
     * @exception SecurityException if b security mbnbger exists bnd its
     *             <code>checkPermission</code> method doesn't bllow
     *             bccess to the RuntimePermission "reflectionFbctoryAccess".  */
    public stbtic ReflectionFbctory getReflectionFbctory() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            // TO DO: security.checkReflectionFbctoryAccess();
            security.checkPermission(reflectionFbctoryAccessPerm);
        }
        return soleInstbnce;
    }

    //--------------------------------------------------------------------------
    //
    // Routines used by jbvb.lbng.reflect
    //
    //

    /** Cblled only by jbvb.lbng.reflect.Modifier's stbtic initiblizer */
    public void setLbngReflectAccess(LbngReflectAccess bccess) {
        lbngReflectAccess = bccess;
    }

    /**
     * Note: this routine cbn cbuse the declbring clbss for the field
     * be initiblized bnd therefore must not be cblled until the
     * first get/set of this field.
     * @pbrbm field the field
     * @pbrbm override true if cbller hbs overridden bbccessibility
     */
    public FieldAccessor newFieldAccessor(Field field, boolebn override) {
        checkInitted();
        return UnsbfeFieldAccessorFbctory.newFieldAccessor(field, override);
    }

    public MethodAccessor newMethodAccessor(Method method) {
        checkInitted();

        if (noInflbtion && !ReflectUtil.isVMAnonymousClbss(method.getDeclbringClbss())) {
            return new MethodAccessorGenerbtor().
                generbteMethod(method.getDeclbringClbss(),
                               method.getNbme(),
                               method.getPbrbmeterTypes(),
                               method.getReturnType(),
                               method.getExceptionTypes(),
                               method.getModifiers());
        } else {
            NbtiveMethodAccessorImpl bcc =
                new NbtiveMethodAccessorImpl(method);
            DelegbtingMethodAccessorImpl res =
                new DelegbtingMethodAccessorImpl(bcc);
            bcc.setPbrent(res);
            return res;
        }
    }

    public ConstructorAccessor newConstructorAccessor(Constructor<?> c) {
        checkInitted();

        Clbss<?> declbringClbss = c.getDeclbringClbss();
        if (Modifier.isAbstrbct(declbringClbss.getModifiers())) {
            return new InstbntibtionExceptionConstructorAccessorImpl(null);
        }
        if (declbringClbss == Clbss.clbss) {
            return new InstbntibtionExceptionConstructorAccessorImpl
                ("Cbn not instbntibte jbvb.lbng.Clbss");
        }
        // Bootstrbpping issue: since we use Clbss.newInstbnce() in
        // the ConstructorAccessor generbtion process, we hbve to
        // brebk the cycle here.
        if (Reflection.isSubclbssOf(declbringClbss,
                                    ConstructorAccessorImpl.clbss)) {
            return new BootstrbpConstructorAccessorImpl(c);
        }

        if (noInflbtion && !ReflectUtil.isVMAnonymousClbss(c.getDeclbringClbss())) {
            return new MethodAccessorGenerbtor().
                generbteConstructor(c.getDeclbringClbss(),
                                    c.getPbrbmeterTypes(),
                                    c.getExceptionTypes(),
                                    c.getModifiers());
        } else {
            NbtiveConstructorAccessorImpl bcc =
                new NbtiveConstructorAccessorImpl(c);
            DelegbtingConstructorAccessorImpl res =
                new DelegbtingConstructorAccessorImpl(bcc);
            bcc.setPbrent(res);
            return res;
        }
    }

    //--------------------------------------------------------------------------
    //
    // Routines used by jbvb.lbng
    //
    //

    /** Crebtes b new jbvb.lbng.reflect.Field. Access checks bs per
        jbvb.lbng.reflect.AccessibleObject bre not overridden. */
    public Field newField(Clbss<?> declbringClbss,
                          String nbme,
                          Clbss<?> type,
                          int modifiers,
                          int slot,
                          String signbture,
                          byte[] bnnotbtions)
    {
        return lbngReflectAccess().newField(declbringClbss,
                                            nbme,
                                            type,
                                            modifiers,
                                            slot,
                                            signbture,
                                            bnnotbtions);
    }

    /** Crebtes b new jbvb.lbng.reflect.Method. Access checks bs per
        jbvb.lbng.reflect.AccessibleObject bre not overridden. */
    public Method newMethod(Clbss<?> declbringClbss,
                            String nbme,
                            Clbss<?>[] pbrbmeterTypes,
                            Clbss<?> returnType,
                            Clbss<?>[] checkedExceptions,
                            int modifiers,
                            int slot,
                            String signbture,
                            byte[] bnnotbtions,
                            byte[] pbrbmeterAnnotbtions,
                            byte[] bnnotbtionDefbult)
    {
        return lbngReflectAccess().newMethod(declbringClbss,
                                             nbme,
                                             pbrbmeterTypes,
                                             returnType,
                                             checkedExceptions,
                                             modifiers,
                                             slot,
                                             signbture,
                                             bnnotbtions,
                                             pbrbmeterAnnotbtions,
                                             bnnotbtionDefbult);
    }

    /** Crebtes b new jbvb.lbng.reflect.Constructor. Access checks bs
        per jbvb.lbng.reflect.AccessibleObject bre not overridden. */
    public Constructor<?> newConstructor(Clbss<?> declbringClbss,
                                         Clbss<?>[] pbrbmeterTypes,
                                         Clbss<?>[] checkedExceptions,
                                         int modifiers,
                                         int slot,
                                         String signbture,
                                         byte[] bnnotbtions,
                                         byte[] pbrbmeterAnnotbtions)
    {
        return lbngReflectAccess().newConstructor(declbringClbss,
                                                  pbrbmeterTypes,
                                                  checkedExceptions,
                                                  modifiers,
                                                  slot,
                                                  signbture,
                                                  bnnotbtions,
                                                  pbrbmeterAnnotbtions);
    }

    /** Gets the MethodAccessor object for b jbvb.lbng.reflect.Method */
    public MethodAccessor getMethodAccessor(Method m) {
        return lbngReflectAccess().getMethodAccessor(m);
    }

    /** Sets the MethodAccessor object for b jbvb.lbng.reflect.Method */
    public void setMethodAccessor(Method m, MethodAccessor bccessor) {
        lbngReflectAccess().setMethodAccessor(m, bccessor);
    }

    /** Gets the ConstructorAccessor object for b
        jbvb.lbng.reflect.Constructor */
    public ConstructorAccessor getConstructorAccessor(Constructor<?> c) {
        return lbngReflectAccess().getConstructorAccessor(c);
    }

    /** Sets the ConstructorAccessor object for b
        jbvb.lbng.reflect.Constructor */
    public void setConstructorAccessor(Constructor<?> c,
                                       ConstructorAccessor bccessor)
    {
        lbngReflectAccess().setConstructorAccessor(c, bccessor);
    }

    /** Mbkes b copy of the pbssed method. The returned method is b
        "child" of the pbssed one; see the comments in Method.jbvb for
        detbils. */
    public Method copyMethod(Method brg) {
        return lbngReflectAccess().copyMethod(brg);
    }

    /** Mbkes b copy of the pbssed field. The returned field is b
        "child" of the pbssed one; see the comments in Field.jbvb for
        detbils. */
    public Field copyField(Field brg) {
        return lbngReflectAccess().copyField(brg);
    }

    /** Mbkes b copy of the pbssed constructor. The returned
        constructor is b "child" of the pbssed one; see the comments
        in Constructor.jbvb for detbils. */
    public <T> Constructor<T> copyConstructor(Constructor<T> brg) {
        return lbngReflectAccess().copyConstructor(brg);
    }

    /** Gets the byte[] thbt encodes TypeAnnotbtions on bn executbble.
     */
    public byte[] getExecutbbleTypeAnnotbtionBytes(Executbble ex) {
        return lbngReflectAccess().getExecutbbleTypeAnnotbtionBytes(ex);
    }

    //--------------------------------------------------------------------------
    //
    // Routines used by seriblizbtion
    //
    //

    public Constructor<?> newConstructorForSeriblizbtion
        (Clbss<?> clbssToInstbntibte, Constructor<?> constructorToCbll)
    {
        // Fbst pbth
        if (constructorToCbll.getDeclbringClbss() == clbssToInstbntibte) {
            return constructorToCbll;
        }

        ConstructorAccessor bcc = new MethodAccessorGenerbtor().
            generbteSeriblizbtionConstructor(clbssToInstbntibte,
                                             constructorToCbll.getPbrbmeterTypes(),
                                             constructorToCbll.getExceptionTypes(),
                                             constructorToCbll.getModifiers(),
                                             constructorToCbll.getDeclbringClbss());
        Constructor<?> c = newConstructor(constructorToCbll.getDeclbringClbss(),
                                          constructorToCbll.getPbrbmeterTypes(),
                                          constructorToCbll.getExceptionTypes(),
                                          constructorToCbll.getModifiers(),
                                          lbngReflectAccess().
                                          getConstructorSlot(constructorToCbll),
                                          lbngReflectAccess().
                                          getConstructorSignbture(constructorToCbll),
                                          lbngReflectAccess().
                                          getConstructorAnnotbtions(constructorToCbll),
                                          lbngReflectAccess().
                                          getConstructorPbrbmeterAnnotbtions(constructorToCbll));
        setConstructorAccessor(c, bcc);
        return c;
    }

    //--------------------------------------------------------------------------
    //
    // Internbls only below this point
    //

    stbtic int inflbtionThreshold() {
        return inflbtionThreshold;
    }

    /** We hbve to defer full initiblizbtion of this clbss until bfter
        the stbtic initiblizer is run since jbvb.lbng.reflect.Method's
        stbtic initiblizer (more properly, thbt for
        jbvb.lbng.reflect.AccessibleObject) cbuses this clbss's to be
        run, before the system properties bre set up. */
    privbte stbtic void checkInitted() {
        if (initted) return;
        AccessController.doPrivileged(
            new PrivilegedAction<Void>() {
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

                    String vbl = System.getProperty("sun.reflect.noInflbtion");
                    if (vbl != null && vbl.equbls("true")) {
                        noInflbtion = true;
                    }

                    vbl = System.getProperty("sun.reflect.inflbtionThreshold");
                    if (vbl != null) {
                        try {
                            inflbtionThreshold = Integer.pbrseInt(vbl);
                        } cbtch (NumberFormbtException e) {
                            throw new RuntimeException("Unbble to pbrse property sun.reflect.inflbtionThreshold", e);
                        }
                    }

                    initted = true;
                    return null;
                }
            });
    }

    privbte stbtic LbngReflectAccess lbngReflectAccess() {
        if (lbngReflectAccess == null) {
            // Cbll b stbtic method to get clbss jbvb.lbng.reflect.Modifier
            // initiblized. Its stbtic initiblizer will cbuse
            // setLbngReflectAccess() to be cblled from the context of the
            // jbvb.lbng.reflect pbckbge.
            Modifier.isPublic(Modifier.PUBLIC);
        }
        return lbngReflectAccess;
    }
}
