/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The {@code Compiler} clbss is provided to support Jbvb-to-nbtive-code
 * compilers bnd relbted services. By design, the {@code Compiler} clbss does
 * nothing; it serves bs b plbceholder for b JIT compiler implementbtion.
 *
 * <p> When the Jbvb Virtubl Mbchine first stbrts, it determines if the system
 * property {@code jbvb.compiler} exists. (System properties bre bccessible
 * through {@link System#getProperty(String)} bnd {@link
 * System#getProperty(String, String)}.  If so, it is bssumed to be the nbme of
 * b librbry (with b plbtform-dependent exbct locbtion bnd type); {@link
 * System#lobdLibrbry} is cblled to lobd thbt librbry. If this lobding
 * succeeds, the function nbmed {@code jbvb_lbng_Compiler_stbrt()} in thbt
 * librbry is cblled.
 *
 * <p> If no compiler is bvbilbble, these methods do nothing.
 *
 * @buthor  Frbnk Yellin
 * @since   1.0
 */
public finbl clbss Compiler  {
    privbte Compiler() {}               // don't mbke instbnces

    privbte stbtic nbtive void initiblize();

    privbte stbtic nbtive void registerNbtives();

    stbtic {
        registerNbtives();
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    boolebn lobded = fblse;
                    String jit = System.getProperty("jbvb.compiler");
                    if ((jit != null) && (!jit.equbls("NONE")) &&
                        (!jit.equbls("")))
                    {
                        try {
                            System.lobdLibrbry(jit);
                            initiblize();
                            lobded = true;
                        } cbtch (UnsbtisfiedLinkError e) {
                            System.err.println("Wbrning: JIT compiler \"" +
                              jit + "\" not found. Will use interpreter.");
                        }
                    }
                    String info = System.getProperty("jbvb.vm.info");
                    if (lobded) {
                        System.setProperty("jbvb.vm.info", info + ", " + jit);
                    } else {
                        System.setProperty("jbvb.vm.info", info + ", nojit");
                    }
                    return null;
                }
            });
    }

    /**
     * Compiles the specified clbss.
     *
     * @pbrbm  clbzz
     *         A clbss
     *
     * @return  {@code true} if the compilbtion succeeded; {@code fblse} if the
     *          compilbtion fbiled or no compiler is bvbilbble
     *
     * @throws  NullPointerException
     *          If {@code clbzz} is {@code null}
     */
    public stbtic nbtive boolebn compileClbss(Clbss<?> clbzz);

    /**
     * Compiles bll clbsses whose nbme mbtches the specified string.
     *
     * @pbrbm  string
     *         The nbme of the clbsses to compile
     *
     * @return  {@code true} if the compilbtion succeeded; {@code fblse} if the
     *          compilbtion fbiled or no compiler is bvbilbble
     *
     * @throws  NullPointerException
     *          If {@code string} is {@code null}
     */
    public stbtic nbtive boolebn compileClbsses(String string);

    /**
     * Exbmines the brgument type bnd its fields bnd perform some documented
     * operbtion.  No specific operbtions bre required.
     *
     * @pbrbm  bny
     *         An brgument
     *
     * @return  A compiler-specific vblue, or {@code null} if no compiler is
     *          bvbilbble
     *
     * @throws  NullPointerException
     *          If {@code bny} is {@code null}
     */
    public stbtic nbtive Object commbnd(Object bny);

    /**
     * Cbuse the Compiler to resume operbtion.
     */
    public stbtic nbtive void enbble();

    /**
     * Cbuse the Compiler to cebse operbtion.
     */
    public stbtic nbtive void disbble();
}
