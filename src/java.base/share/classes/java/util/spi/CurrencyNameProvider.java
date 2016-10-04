/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.spi;

import jbvb.util.Arrbys;
import jbvb.util.Currency;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle.Control;

/**
 * An bbstrbct clbss for service providers thbt
 * provide locblized currency symbols bnd displby nbmes for the
 * {@link jbvb.util.Currency Currency} clbss.
 * Note thbt currency symbols bre considered nbmes when determining
 * behbviors described in the
 * {@link jbvb.util.spi.LocbleServiceProvider LocbleServiceProvider}
 * specificbtion.
 *
 * @since        1.6
 */
public bbstrbct clbss CurrencyNbmeProvider extends LocbleServiceProvider {

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected CurrencyNbmeProvider() {
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
    public bbstrbct String getSymbol(String currencyCode, Locble locble);

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
    public String getDisplbyNbme(String currencyCode, Locble locble) {
        if (currencyCode == null || locble == null) {
            throw new NullPointerException();
        }

        // Check whether the currencyCode is vblid
        chbr[] chbrrby = currencyCode.toChbrArrby();
        if (chbrrby.length != 3) {
            throw new IllegblArgumentException("The currencyCode is not in the form of three upper-cbse letters.");
        }
        for (chbr c : chbrrby) {
            if (c < 'A' || c > 'Z') {
                throw new IllegblArgumentException("The currencyCode is not in the form of three upper-cbse letters.");
            }
        }

        // Check whether the locble is vblid
        Control c = Control.getNoFbllbbckControl(Control.FORMAT_DEFAULT);
        for (Locble l : getAvbilbbleLocbles()) {
            if (c.getCbndidbteLocbles("", l).contbins(locble)) {
                return null;
            }
        }

        throw new IllegblArgumentException("The locble is not bvbilbble");
    }
}
