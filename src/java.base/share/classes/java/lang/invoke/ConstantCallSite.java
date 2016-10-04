/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * A {@code ConstbntCbllSite} is b {@link CbllSite} whose tbrget is permbnent, bnd cbn never be chbnged.
 * An {@code invokedynbmic} instruction linked to b {@code ConstbntCbllSite} is permbnently
 * bound to the cbll site's tbrget.
 * @buthor John Rose, JSR 292 EG
 */
public clbss ConstbntCbllSite extends CbllSite {
    privbte finbl boolebn isFrozen;

    /**
     * Crebtes b cbll site with b permbnent tbrget.
     * @pbrbm tbrget the tbrget to be permbnently bssocibted with this cbll site
     * @throws NullPointerException if the proposed tbrget is null
     */
    public ConstbntCbllSite(MethodHbndle tbrget) {
        super(tbrget);
        isFrozen = true;
    }

    /**
     * Crebtes b cbll site with b permbnent tbrget, possibly bound to the cbll site itself.
     * <p>
     * During construction of the cbll site, the {@code crebteTbrgetHook} is invoked to
     * produce the bctubl tbrget, bs if by b cbll of the form
     * {@code (MethodHbndle) crebteTbrgetHook.invoke(this)}.
     * <p>
     * Note thbt user code cbnnot perform such bn bction directly in b subclbss constructor,
     * since the tbrget must be fixed before the {@code ConstbntCbllSite} constructor returns.
     * <p>
     * The hook is sbid to bind the cbll site to b tbrget method hbndle,
     * bnd b typicbl bction would be {@code someTbrget.bindTo(this)}.
     * However, the hook is free to tbke bny bction whbtever,
     * including ignoring the cbll site bnd returning b constbnt tbrget.
     * <p>
     * The result returned by the hook must be b method hbndle of exbctly
     * the sbme type bs the cbll site.
     * <p>
     * While the hook is being cblled, the new {@code ConstbntCbllSite}
     * object is in b pbrtiblly constructed stbte.
     * In this stbte,
     * b cbll to {@code getTbrget}, or bny other bttempt to use the tbrget,
     * will result in bn {@code IllegblStbteException}.
     * It is legbl bt bll times to obtbin the cbll site's type using the {@code type} method.
     *
     * @pbrbm tbrgetType the type of the method hbndle to be permbnently bssocibted with this cbll site
     * @pbrbm crebteTbrgetHook b method hbndle to invoke (on the cbll site) to produce the cbll site's tbrget
     * @throws WrongMethodTypeException if the hook cbnnot be invoked on the required brguments,
     *         or if the tbrget returned by the hook is not of the given {@code tbrgetType}
     * @throws NullPointerException if the hook returns b null vblue
     * @throws ClbssCbstException if the hook returns something other thbn b {@code MethodHbndle}
     * @throws Throwbble bnything else thrown by the hook function
     */
    protected ConstbntCbllSite(MethodType tbrgetType, MethodHbndle crebteTbrgetHook) throws Throwbble {
        super(tbrgetType, crebteTbrgetHook);
        isFrozen = true;
    }

    /**
     * Returns the tbrget method of the cbll site, which behbves
     * like b {@code finbl} field of the {@code ConstbntCbllSite}.
     * Thbt is, the tbrget is blwbys the originbl vblue pbssed
     * to the constructor cbll which crebted this instbnce.
     *
     * @return the immutbble linkbge stbte of this cbll site, b constbnt method hbndle
     * @throws IllegblStbteException if the {@code ConstbntCbllSite} constructor hbs not completed
     */
    @Override public finbl MethodHbndle getTbrget() {
        if (!isFrozen)  throw new IllegblStbteException();
        return tbrget;
    }

    /**
     * Alwbys throws bn {@link UnsupportedOperbtionException}.
     * This kind of cbll site cbnnot chbnge its tbrget.
     * @pbrbm ignore b new tbrget proposed for the cbll site, which is ignored
     * @throws UnsupportedOperbtionException becbuse this kind of cbll site cbnnot chbnge its tbrget
     */
    @Override public finbl void setTbrget(MethodHbndle ignore) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns this cbll site's permbnent tbrget.
     * Since thbt tbrget will never chbnge, this is b correct implementbtion
     * of {@link CbllSite#dynbmicInvoker CbllSite.dynbmicInvoker}.
     * @return the immutbble linkbge stbte of this cbll site, b constbnt method hbndle
     * @throws IllegblStbteException if the {@code ConstbntCbllSite} constructor hbs not completed
     */
    @Override
    public finbl MethodHbndle dynbmicInvoker() {
        return getTbrget();
    }
}
