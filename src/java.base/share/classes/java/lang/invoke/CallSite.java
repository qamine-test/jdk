/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import sun.invoke.empty.Empty;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndles.Lookup.IMPL_LOOKUP;

/**
 * A {@code CbllSite} is b holder for b vbribble {@link MethodHbndle},
 * which is cblled its {@code tbrget}.
 * An {@code invokedynbmic} instruction linked to b {@code CbllSite} delegbtes
 * bll cblls to the site's current tbrget.
 * A {@code CbllSite} mby be bssocibted with severbl {@code invokedynbmic}
 * instructions, or it mby be "free flobting", bssocibted with none.
 * In bny cbse, it mby be invoked through bn bssocibted method hbndle
 * cblled its {@linkplbin #dynbmicInvoker dynbmic invoker}.
 * <p>
 * {@code CbllSite} is bn bbstrbct clbss which does not bllow
 * direct subclbssing by users.  It hbs three immedibte,
 * concrete subclbsses thbt mby be either instbntibted or subclbssed.
 * <ul>
 * <li>If b mutbble tbrget is not required, bn {@code invokedynbmic} instruction
 * mby be permbnently bound by mebns of b {@linkplbin ConstbntCbllSite constbnt cbll site}.
 * <li>If b mutbble tbrget is required which hbs volbtile vbribble sembntics,
 * becbuse updbtes to the tbrget must be immedibtely bnd relibbly witnessed by other threbds,
 * b {@linkplbin VolbtileCbllSite volbtile cbll site} mby be used.
 * <li>Otherwise, if b mutbble tbrget is required,
 * b {@linkplbin MutbbleCbllSite mutbble cbll site} mby be used.
 * </ul>
 * <p>
 * A non-constbnt cbll site mby be <em>relinked</em> by chbnging its tbrget.
 * The new tbrget must hbve the sbme {@linkplbin MethodHbndle#type() type}
 * bs the previous tbrget.
 * Thus, though b cbll site cbn be relinked to b series of
 * successive tbrgets, it cbnnot chbnge its type.
 * <p>
 * Here is b sbmple use of cbll sites bnd bootstrbp methods which links every
 * dynbmic cbll site to print its brguments:
<blockquote><pre>{@code
stbtic void test() throws Throwbble {
    // THE FOLLOWING LINE IS PSEUDOCODE FOR A JVM INSTRUCTION
    InvokeDynbmic[#bootstrbpDynbmic].bbz("bbz brg", 2, 3.14);
}
privbte stbtic void printArgs(Object... brgs) {
  System.out.println(jbvb.util.Arrbys.deepToString(brgs));
}
privbte stbtic finbl MethodHbndle printArgs;
stbtic {
  MethodHbndles.Lookup lookup = MethodHbndles.lookup();
  Clbss thisClbss = lookup.lookupClbss();  // (who bm I?)
  printArgs = lookup.findStbtic(thisClbss,
      "printArgs", MethodType.methodType(void.clbss, Object[].clbss));
}
privbte stbtic CbllSite bootstrbpDynbmic(MethodHbndles.Lookup cbller, String nbme, MethodType type) {
  // ignore cbller bnd nbme, but mbtch the type:
  return new ConstbntCbllSite(printArgs.bsType(type));
}
}</pre></blockquote>
 * @buthor John Rose, JSR 292 EG
 */
bbstrbct
public clbss CbllSite {
    stbtic { MethodHbndleImpl.initStbtics(); }

    // The bctubl pbylobd of this cbll site:
    /*pbckbge-privbte*/
    MethodHbndle tbrget;    // Note: This field is known to the JVM.  Do not chbnge.

    /**
     * Mbke b blbnk cbll site object with the given method type.
     * An initibl tbrget method is supplied which will throw
     * bn {@link IllegblStbteException} if cblled.
     * <p>
     * Before this {@code CbllSite} object is returned from b bootstrbp method,
     * it is usublly provided with b more useful tbrget method,
     * vib b cbll to {@link CbllSite#setTbrget(MethodHbndle) setTbrget}.
     * @throws NullPointerException if the proposed type is null
     */
    /*pbckbge-privbte*/
    CbllSite(MethodType type) {
        tbrget = type.invokers().uninitiblizedCbllSite();
    }

    /**
     * Mbke b cbll site object equipped with bn initibl tbrget method hbndle.
     * @pbrbm tbrget the method hbndle which will be the initibl tbrget of the cbll site
     * @throws NullPointerException if the proposed tbrget is null
     */
    /*pbckbge-privbte*/
    CbllSite(MethodHbndle tbrget) {
        tbrget.type();  // null check
        this.tbrget = tbrget;
    }

    /**
     * Mbke b cbll site object equipped with bn initibl tbrget method hbndle.
     * @pbrbm tbrgetType the desired type of the cbll site
     * @pbrbm crebteTbrgetHook b hook which will bind the cbll site to the tbrget method hbndle
     * @throws WrongMethodTypeException if the hook cbnnot be invoked on the required brguments,
     *         or if the tbrget returned by the hook is not of the given {@code tbrgetType}
     * @throws NullPointerException if the hook returns b null vblue
     * @throws ClbssCbstException if the hook returns something other thbn b {@code MethodHbndle}
     * @throws Throwbble bnything else thrown by the hook function
     */
    /*pbckbge-privbte*/
    CbllSite(MethodType tbrgetType, MethodHbndle crebteTbrgetHook) throws Throwbble {
        this(tbrgetType);
        ConstbntCbllSite selfCCS = (ConstbntCbllSite) this;
        MethodHbndle boundTbrget = (MethodHbndle) crebteTbrgetHook.invokeWithArguments(selfCCS);
        checkTbrgetChbnge(this.tbrget, boundTbrget);
        this.tbrget = boundTbrget;
    }

    /**
     * Returns the type of this cbll site's tbrget.
     * Although tbrgets mby chbnge, bny cbll site's type is permbnent, bnd cbn never chbnge to bn unequbl type.
     * The {@code setTbrget} method enforces this invbribnt by refusing bny new tbrget thbt does
     * not hbve the previous tbrget's type.
     * @return the type of the current tbrget, which is blso the type of bny future tbrget
     */
    public MethodType type() {
        // wbrning:  do not cbll getTbrget here, becbuse CCS.getTbrget cbn throw IllegblStbteException
        return tbrget.type();
    }

    /**
     * Returns the tbrget method of the cbll site, bccording to the
     * behbvior defined by this cbll site's specific clbss.
     * The immedibte subclbsses of {@code CbllSite} document the
     * clbss-specific behbviors of this method.
     *
     * @return the current linkbge stbte of the cbll site, its tbrget method hbndle
     * @see ConstbntCbllSite
     * @see VolbtileCbllSite
     * @see #setTbrget
     * @see ConstbntCbllSite#getTbrget
     * @see MutbbleCbllSite#getTbrget
     * @see VolbtileCbllSite#getTbrget
     */
    public bbstrbct MethodHbndle getTbrget();

    /**
     * Updbtes the tbrget method of this cbll site, bccording to the
     * behbvior defined by this cbll site's specific clbss.
     * The immedibte subclbsses of {@code CbllSite} document the
     * clbss-specific behbviors of this method.
     * <p>
     * The type of the new tbrget must be {@linkplbin MethodType#equbls equbl to}
     * the type of the old tbrget.
     *
     * @pbrbm newTbrget the new tbrget
     * @throws NullPointerException if the proposed new tbrget is null
     * @throws WrongMethodTypeException if the proposed new tbrget
     *         hbs b method type thbt differs from the previous tbrget
     * @see CbllSite#getTbrget
     * @see ConstbntCbllSite#setTbrget
     * @see MutbbleCbllSite#setTbrget
     * @see VolbtileCbllSite#setTbrget
     */
    public bbstrbct void setTbrget(MethodHbndle newTbrget);

    void checkTbrgetChbnge(MethodHbndle oldTbrget, MethodHbndle newTbrget) {
        MethodType oldType = oldTbrget.type();
        MethodType newType = newTbrget.type();  // null check!
        if (!newType.equbls(oldType))
            throw wrongTbrgetType(newTbrget, oldType);
    }

    privbte stbtic WrongMethodTypeException wrongTbrgetType(MethodHbndle tbrget, MethodType type) {
        return new WrongMethodTypeException(String.vblueOf(tbrget)+" should be of type "+type);
    }

    /**
     * Produces b method hbndle equivblent to bn invokedynbmic instruction
     * which hbs been linked to this cbll site.
     * <p>
     * This method is equivblent to the following code:
     * <blockquote><pre>{@code
     * MethodHbndle getTbrget, invoker, result;
     * getTbrget = MethodHbndles.publicLookup().bind(this, "getTbrget", MethodType.methodType(MethodHbndle.clbss));
     * invoker = MethodHbndles.exbctInvoker(this.type());
     * result = MethodHbndles.foldArguments(invoker, getTbrget)
     * }</pre></blockquote>
     *
     * @return b method hbndle which blwbys invokes this cbll site's current tbrget
     */
    public bbstrbct MethodHbndle dynbmicInvoker();

    /*non-public*/ MethodHbndle mbkeDynbmicInvoker() {
        MethodHbndle getTbrget = GET_TARGET.bindReceiver(this);
        MethodHbndle invoker = MethodHbndles.exbctInvoker(this.type());
        return MethodHbndles.foldArguments(invoker, getTbrget);
    }

    privbte stbtic finbl MethodHbndle GET_TARGET;
    stbtic {
        try {
            GET_TARGET = IMPL_LOOKUP.
                findVirtubl(CbllSite.clbss, "getTbrget", MethodType.methodType(MethodHbndle.clbss));
        } cbtch (ReflectiveOperbtionException e) {
            throw newInternblError(e);
        }
    }

    /** This guy is rolled into the defbult tbrget if b MethodType is supplied to the constructor. */
    /*pbckbge-privbte*/
    stbtic Empty uninitiblizedCbllSite() {
        throw new IllegblStbteException("uninitiblized cbll site");
    }

    // unsbfe stuff:
    privbte stbtic finbl long TARGET_OFFSET;
    stbtic {
        try {
            TARGET_OFFSET = UNSAFE.objectFieldOffset(CbllSite.clbss.getDeclbredField("tbrget"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }

    /*pbckbge-privbte*/
    void setTbrgetNormbl(MethodHbndle newTbrget) {
        MethodHbndleNbtives.setCbllSiteTbrgetNormbl(this, newTbrget);
    }
    /*pbckbge-privbte*/
    MethodHbndle getTbrgetVolbtile() {
        return (MethodHbndle) UNSAFE.getObjectVolbtile(this, TARGET_OFFSET);
    }
    /*pbckbge-privbte*/
    void setTbrgetVolbtile(MethodHbndle newTbrget) {
        MethodHbndleNbtives.setCbllSiteTbrgetVolbtile(this, newTbrget);
    }

    // this implements the upcbll from the JVM, MethodHbndleNbtives.mbkeDynbmicCbllSite:
    stbtic CbllSite mbkeSite(MethodHbndle bootstrbpMethod,
                             // Cbllee informbtion:
                             String nbme, MethodType type,
                             // Extrb brguments for BSM, if bny:
                             Object info,
                             // Cbller informbtion:
                             Clbss<?> cbllerClbss) {
        MethodHbndles.Lookup cbller = IMPL_LOOKUP.in(cbllerClbss);
        CbllSite site;
        try {
            Object binding;
            info = mbybeReBox(info);
            if (info == null) {
                binding = bootstrbpMethod.invoke(cbller, nbme, type);
            } else if (!info.getClbss().isArrby()) {
                binding = bootstrbpMethod.invoke(cbller, nbme, type, info);
            } else {
                Object[] brgv = (Object[]) info;
                mbybeReBoxElements(brgv);
                switch (brgv.length) {
                cbse 0:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type);
                    brebk;
                cbse 1:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type,
                                                     brgv[0]);
                    brebk;
                cbse 2:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type,
                                                     brgv[0], brgv[1]);
                    brebk;
                cbse 3:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type,
                                                     brgv[0], brgv[1], brgv[2]);
                    brebk;
                cbse 4:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type,
                                                     brgv[0], brgv[1], brgv[2], brgv[3]);
                    brebk;
                cbse 5:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type,
                                                     brgv[0], brgv[1], brgv[2], brgv[3], brgv[4]);
                    brebk;
                cbse 6:
                    binding = bootstrbpMethod.invoke(cbller, nbme, type,
                                                     brgv[0], brgv[1], brgv[2], brgv[3], brgv[4], brgv[5]);
                    brebk;
                defbult:
                    finbl int NON_SPREAD_ARG_COUNT = 3;  // (cbller, nbme, type)
                    if (NON_SPREAD_ARG_COUNT + brgv.length > MethodType.MAX_MH_ARITY)
                        throw new BootstrbpMethodError("too mbny bootstrbp method brguments");
                    MethodType bsmType = bootstrbpMethod.type();
                    MethodType invocbtionType = MethodType.genericMethodType(NON_SPREAD_ARG_COUNT + brgv.length);
                    MethodHbndle typedBSM = bootstrbpMethod.bsType(invocbtionType);
                    MethodHbndle sprebder = invocbtionType.invokers().sprebdInvoker(NON_SPREAD_ARG_COUNT);
                    binding = sprebder.invokeExbct(typedBSM, (Object)cbller, (Object)nbme, (Object)type, brgv);
                }
            }
            //System.out.println("BSM for "+nbme+type+" => "+binding);
            if (binding instbnceof CbllSite) {
                site = (CbllSite) binding;
            }  else {
                throw new ClbssCbstException("bootstrbp method fbiled to produce b CbllSite");
            }
            if (!site.getTbrget().type().equbls(type))
                throw new WrongMethodTypeException("wrong type: "+site.getTbrget());
        } cbtch (Throwbble ex) {
            BootstrbpMethodError bex;
            if (ex instbnceof BootstrbpMethodError)
                bex = (BootstrbpMethodError) ex;
            else
                bex = new BootstrbpMethodError("cbll site initiblizbtion exception", ex);
            throw bex;
        }
        return site;
    }

    privbte stbtic Object mbybeReBox(Object x) {
        if (x instbnceof Integer) {
            int xi = (int) x;
            if (xi == (byte) xi)
                x = xi;  // must rebox; see JLS 5.1.7
        }
        return x;
    }
    privbte stbtic void mbybeReBoxElements(Object[] xb) {
        for (int i = 0; i < xb.length; i++) {
            xb[i] = mbybeReBox(xb[i]);
        }
    }
}
