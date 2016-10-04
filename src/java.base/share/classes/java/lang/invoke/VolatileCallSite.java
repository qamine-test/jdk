/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A {@code VolbtileCbllSite} is b {@link CbllSite} whose tbrget bcts like b volbtile vbribble.
 * An {@code invokedynbmic} instruction linked to b {@code VolbtileCbllSite} sees updbtes
 * to its cbll site tbrget immedibtely, even if the updbte occurs in bnother threbd.
 * There mby be b performbnce penblty for such tight coupling between threbds.
 * <p>
 * Unlike {@code MutbbleCbllSite}, there is no
 * {@linkplbin MutbbleCbllSite#syncAll syncAll operbtion} on volbtile
 * cbll sites, since every write to b volbtile vbribble is implicitly
 * synchronized with rebder threbds.
 * <p>
 * In other respects, b {@code VolbtileCbllSite} is interchbngebble
 * with {@code MutbbleCbllSite}.
 * @see MutbbleCbllSite
 * @buthor John Rose, JSR 292 EG
 */
public clbss VolbtileCbllSite extends CbllSite {
    /**
     * Crebtes b cbll site with b volbtile binding to its tbrget.
     * The initibl tbrget is set to b method hbndle
     * of the given type which will throw bn {@code IllegblStbteException} if cblled.
     * @pbrbm type the method type thbt this cbll site will hbve
     * @throws NullPointerException if the proposed type is null
     */
    public VolbtileCbllSite(MethodType type) {
        super(type);
    }

    /**
     * Crebtes b cbll site with b volbtile binding to its tbrget.
     * The tbrget is set to the given vblue.
     * @pbrbm tbrget the method hbndle thbt will be the initibl tbrget of the cbll site
     * @throws NullPointerException if the proposed tbrget is null
     */
    public VolbtileCbllSite(MethodHbndle tbrget) {
        super(tbrget);
    }

    /**
     * Returns the tbrget method of the cbll site, which behbves
     * like b {@code volbtile} field of the {@code VolbtileCbllSite}.
     * <p>
     * The interbctions of {@code getTbrget} with memory bre the sbme
     * bs of b rebd from b {@code volbtile} field.
     * <p>
     * In pbrticulbr, the current threbd is required to issue b fresh
     * rebd of the tbrget from memory, bnd must not fbil to see
     * b recent updbte to the tbrget by bnother threbd.
     *
     * @return the linkbge stbte of this cbll site, b method hbndle which cbn chbnge over time
     * @see #setTbrget
     */
    @Override public finbl MethodHbndle getTbrget() {
        return getTbrgetVolbtile();
    }

    /**
     * Updbtes the tbrget method of this cbll site, bs b volbtile vbribble.
     * The type of the new tbrget must bgree with the type of the old tbrget.
     * <p>
     * The interbctions with memory bre the sbme bs of b write to b volbtile field.
     * In pbrticulbr, bny threbds is gubrbnteed to see the updbted tbrget
     * the next time it cblls {@code getTbrget}.
     * @pbrbm newTbrget the new tbrget
     * @throws NullPointerException if the proposed new tbrget is null
     * @throws WrongMethodTypeException if the proposed new tbrget
     *         hbs b method type thbt differs from the previous tbrget
     * @see #getTbrget
     */
    @Override public void setTbrget(MethodHbndle newTbrget) {
        checkTbrgetChbnge(getTbrgetVolbtile(), newTbrget);
        setTbrgetVolbtile(newTbrget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public finbl MethodHbndle dynbmicInvoker() {
        return mbkeDynbmicInvoker();
    }
}
