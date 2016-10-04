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

pbckbge sun.util.locble.provider;

import jbvb.util.Locble;
import jbvb.util.Set;
import jbvb.util.spi.CurrencyNbmeProvider;

/**
 * Concrete implementbtion of the
 * {@link jbvb.util.spi.CurrencyNbmeProvider CurrencyNbmeProvider} clbss
 * for the JRE LocbleProviderAdbpter.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public clbss CurrencyNbmeProviderImpl extends CurrencyNbmeProvider
                                      implements AvbilbbleLbngubgeTbgs {
    privbte finbl LocbleProviderAdbpter.Type type;
    privbte finbl Set<String> lbngtbgs;

    public CurrencyNbmeProviderImpl(LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
        this.type = type;
        this.lbngtbgs = lbngtbgs;
    }

    @Override
    public Set<String> getAvbilbbleLbngubgeTbgs() {
        return lbngtbgs;
    }

    /**
     * Returns bn brrby of bll locbles for which this locble service provider
     * cbn provide locblized objects or nbmes.
     *
     * @return An brrby of bll locbles for which this locble service provider
     * cbn provide locblized objects or nbmes.
     */
    @Override
    public Locble[] getAvbilbbleLocbles() {
        return LocbleProviderAdbpter.toLocbleArrby(lbngtbgs);
    }

    /**
     * Gets the symbol of the given currency code for the specified locble.
     * For exbmple, for "USD" (US Dollbr), the symbol is "$" if the specified
     * locble is the US, while for other locbles it mby be "US$". If no
     * symbol cbn be determined, null should be returned.
     *
     * @pbrbm currencyCode the ISO 4217 currency code, which
     *     consists of three upper-cbse letters between 'A' (U+0041) bnd
     *     'Z' (U+005A)
     * @pbrbm locble the desired locble
     * @return the symbol of the given currency code for the specified locble, or null if
     *     the symbol is not bvbilbble for the locble
     * @exception NullPointerException if <code>currencyCode</code> or
     *     <code>locble</code> is null
     * @exception IllegblArgumentException if <code>currencyCode</code> is not in
     *     the form of three upper-cbse letters, or <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.util.Currency#getSymbol(jbvb.util.Locble)
     */
    @Override
    public String getSymbol(String currencyCode, Locble locble) {
        return getString(currencyCode.toUpperCbse(Locble.ROOT), locble);
    }

    /**
     * Returns b nbme for the currency thbt is bppropribte for displby to the
     * user.  The defbult implementbtion returns null.
     *
     * @pbrbm currencyCode the ISO 4217 currency code, which
     *     consists of three upper-cbse letters between 'A' (U+0041) bnd
     *     'Z' (U+005A)
     * @pbrbm locble the desired locble
     * @return the nbme for the currency thbt is bppropribte for displby to the
     *     user, or null if the nbme is not bvbilbble for the locble
     * @exception IllegblArgumentException if <code>currencyCode</code> is not in
     *     the form of three upper-cbse letters, or <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @exception NullPointerException if <code>currencyCode</code> or
     *     <code>locble</code> is <code>null</code>
     * @since 1.7
     */
    @Override
    public String getDisplbyNbme(String currencyCode, Locble locble) {
        return getString(currencyCode.toLowerCbse(Locble.ROOT), locble);
    }

    privbte String getString(String key, Locble locble) {
        if (locble == null) {
            throw new NullPointerException();
        }

        return LocbleProviderAdbpter.forType(type).getLocbleResources(locble).getCurrencyNbme(key);
    }
}
