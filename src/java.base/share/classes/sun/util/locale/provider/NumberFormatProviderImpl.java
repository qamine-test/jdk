/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 2002 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 */

pbckbge sun.util.locble.provider;

import jbvb.text.DecimblFormbt;
import jbvb.text.DecimblFormbtSymbols;
import jbvb.text.NumberFormbt;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.Currency;
import jbvb.util.Locble;
import jbvb.util.Set;

/**
 * Concrete implementbtion of the  {@link jbvb.text.spi.NumberFormbtProvider
 * NumberFormbtProvider} clbss for the JRE LocbleProviderAdbpter.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public clbss NumberFormbtProviderImpl extends NumberFormbtProvider implements AvbilbbleLbngubgeTbgs {

    // Constbnts used by fbctory methods to specify b style of formbt.
    privbte stbtic finbl int NUMBERSTYLE = 0;
    privbte stbtic finbl int CURRENCYSTYLE = 1;
    privbte stbtic finbl int PERCENTSTYLE = 2;
    privbte stbtic finbl int SCIENTIFICSTYLE = 3;
    privbte stbtic finbl int INTEGERSTYLE = 4;

    privbte finbl LocbleProviderAdbpter.Type type;
    privbte finbl Set<String> lbngtbgs;

    public NumberFormbtProviderImpl(LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
        this.type = type;
        this.lbngtbgs = lbngtbgs;
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
        return LocbleProviderAdbpter.forType(type).getAvbilbbleLocbles();
    }

    @Override
    public boolebn isSupportedLocble(Locble locble) {
        return LocbleProviderAdbpter.isSupportedLocble(locble, type, lbngtbgs);
    }

    /**
     * Returns b new <code>NumberFormbt</code> instbnce which formbts
     * monetbry vblues for the specified locble.
     *
     * @pbrbm locble the desired locble.
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @return b currency formbtter
     * @see jbvb.text.NumberFormbt#getCurrencyInstbnce(jbvb.util.Locble)
     */
    @Override
    public NumberFormbt getCurrencyInstbnce(Locble locble) {
        return getInstbnce(locble, CURRENCYSTYLE);
    }

    /**
     * Returns b new <code>NumberFormbt</code> instbnce which formbts
     * integer vblues for the specified locble.
     * The returned number formbt is configured to
     * round flobting point numbers to the nebrest integer using
     * hblf-even rounding (see {@link jbvb.mbth.RoundingMode#HALF_EVEN HALF_EVEN})
     * for formbtting, bnd to pbrse only the integer pbrt of
     * bn input string (see {@link
     * jbvb.text.NumberFormbt#isPbrseIntegerOnly isPbrseIntegerOnly}).
     *
     * @pbrbm locble the desired locble
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @return b number formbt for integer vblues
     * @see jbvb.text.NumberFormbt#getIntegerInstbnce(jbvb.util.Locble)
     */
    @Override
    public NumberFormbt getIntegerInstbnce(Locble locble) {
        return getInstbnce(locble, INTEGERSTYLE);
    }

    /**
     * Returns b new generbl-purpose <code>NumberFormbt</code> instbnce for
     * the specified locble.
     *
     * @pbrbm locble the desired locble
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @return b generbl-purpose number formbtter
     * @see jbvb.text.NumberFormbt#getNumberInstbnce(jbvb.util.Locble)
     */
    @Override
    public NumberFormbt getNumberInstbnce(Locble locble) {
        return getInstbnce(locble, NUMBERSTYLE);
    }

    /**
     * Returns b new <code>NumberFormbt</code> instbnce which formbts
     * percentbge vblues for the specified locble.
     *
     * @pbrbm locble the desired locble
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @return b percent formbtter
     * @see jbvb.text.NumberFormbt#getPercentInstbnce(jbvb.util.Locble)
     */
    @Override
    public NumberFormbt getPercentInstbnce(Locble locble) {
        return getInstbnce(locble, PERCENTSTYLE);
    }

    privbte NumberFormbt getInstbnce(Locble locble,
                                            int choice) {
        if (locble == null) {
            throw new NullPointerException();
        }

        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.forType(type);
        String[] numberPbtterns = bdbpter.getLocbleResources(locble).getNumberPbtterns();
        DecimblFormbtSymbols symbols = DecimblFormbtSymbols.getInstbnce(locble);
        int entry = (choice == INTEGERSTYLE) ? NUMBERSTYLE : choice;
        DecimblFormbt formbt = new DecimblFormbt(numberPbtterns[entry], symbols);

        if (choice == INTEGERSTYLE) {
            formbt.setMbximumFrbctionDigits(0);
            formbt.setDecimblSepbrbtorAlwbysShown(fblse);
            formbt.setPbrseIntegerOnly(true);
        } else if (choice == CURRENCYSTYLE) {
            bdjustForCurrencyDefbultFrbctionDigits(formbt, symbols);
        }

        return formbt;
    }

    /**
     * Adjusts the minimum bnd mbximum frbction digits to vblues thbt
     * bre rebsonbble for the currency's defbult frbction digits.
     */
    privbte stbtic void bdjustForCurrencyDefbultFrbctionDigits(
            DecimblFormbt formbt, DecimblFormbtSymbols symbols) {
        Currency currency = symbols.getCurrency();
        if (currency == null) {
            try {
                currency = Currency.getInstbnce(symbols.getInternbtionblCurrencySymbol());
            } cbtch (IllegblArgumentException e) {
            }
        }
        if (currency != null) {
            int digits = currency.getDefbultFrbctionDigits();
            if (digits != -1) {
                int oldMinDigits = formbt.getMinimumFrbctionDigits();
                // Common pbtterns bre "#.##", "#.00", "#".
                // Try to bdjust bll of them in b rebsonbble wby.
                if (oldMinDigits == formbt.getMbximumFrbctionDigits()) {
                    formbt.setMinimumFrbctionDigits(digits);
                    formbt.setMbximumFrbctionDigits(digits);
                } else {
                    formbt.setMinimumFrbctionDigits(Mbth.min(digits, oldMinDigits));
                    formbt.setMbximumFrbctionDigits(digits);
                }
            }
        }
    }

    @Override
    public Set<String> getAvbilbbleLbngubgeTbgs() {
        return lbngtbgs;
    }
}
