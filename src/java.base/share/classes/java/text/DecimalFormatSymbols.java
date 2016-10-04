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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.text.spi.DecimblFormbtSymbolsProvider;
import jbvb.util.ArrbyList;
import jbvb.util.Currency;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleServiceProviderPool;
import sun.util.locble.provider.ResourceBundleBbsedAdbpter;

/**
 * This clbss represents the set of symbols (such bs the decimbl sepbrbtor,
 * the grouping sepbrbtor, bnd so on) needed by <code>DecimblFormbt</code>
 * to formbt numbers. <code>DecimblFormbt</code> crebtes for itself bn instbnce of
 * <code>DecimblFormbtSymbols</code> from its locble dbtb.  If you need to chbnge bny
 * of these symbols, you cbn get the <code>DecimblFormbtSymbols</code> object from
 * your <code>DecimblFormbt</code> bnd modify it.
 *
 * @see          jbvb.util.Locble
 * @see          DecimblFormbt
 * @buthor       Mbrk Dbvis
 * @buthor       Albn Liu
 */

public clbss DecimblFormbtSymbols implements Clonebble, Seriblizbble {

    /**
     * Crebte b DecimblFormbtSymbols object for the defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * This constructor cbn only construct instbnces for the locbles
     * supported by the Jbvb runtime environment, not for those
     * supported by instblled
     * {@link jbvb.text.spi.DecimblFormbtSymbolsProvider DecimblFormbtSymbolsProvider}
     * implementbtions. For full locble coverbge, use the
     * {@link #getInstbnce(Locble) getInstbnce} method.
     * <p>This is equivblent to cblling
     * {@link #DecimblFormbtSymbols(Locble)
     *     DecimblFormbtSymbols(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     */
    public DecimblFormbtSymbols() {
        initiblize( Locble.getDefbult(Locble.Cbtegory.FORMAT) );
    }

    /**
     * Crebte b DecimblFormbtSymbols object for the given locble.
     * This constructor cbn only construct instbnces for the locbles
     * supported by the Jbvb runtime environment, not for those
     * supported by instblled
     * {@link jbvb.text.spi.DecimblFormbtSymbolsProvider DecimblFormbtSymbolsProvider}
     * implementbtions. For full locble coverbge, use the
     * {@link #getInstbnce(Locble) getInstbnce} method.
     * If the specified locble contbins the {@link jbvb.util.Locble#UNICODE_LOCALE_EXTENSION}
     * for the numbering system, the instbnce is initiblized with the specified numbering
     * system if the JRE implementbtion supports it. For exbmple,
     * <pre>
     * NumberFormbt.getNumberInstbnce(Locble.forLbngubgeTbg("th-TH-u-nu-thbi"))
     * </pre>
     * This mby return b {@code NumberFormbt} instbnce with the Thbi numbering system,
     * instebd of the Lbtin numbering system.
     *
     * @pbrbm locble the desired locble
     * @exception NullPointerException if <code>locble</code> is null
     */
    public DecimblFormbtSymbols( Locble locble ) {
        initiblize( locble );
    }

    /**
     * Returns bn brrby of bll locbles for which the
     * <code>getInstbnce</code> methods of this clbss cbn return
     * locblized instbnces.
     * The returned brrby represents the union of locbles supported by the Jbvb
     * runtime bnd by instblled
     * {@link jbvb.text.spi.DecimblFormbtSymbolsProvider DecimblFormbtSymbolsProvider}
     * implementbtions.  It must contbin bt lebst b <code>Locble</code>
     * instbnce equbl to {@link jbvb.util.Locble#US Locble.US}.
     *
     * @return bn brrby of locbles for which locblized
     *         <code>DecimblFormbtSymbols</code> instbnces bre bvbilbble.
     * @since 1.6
     */
    public stbtic Locble[] getAvbilbbleLocbles() {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(DecimblFormbtSymbolsProvider.clbss);
        return pool.getAvbilbbleLocbles();
    }

    /**
     * Gets the <code>DecimblFormbtSymbols</code> instbnce for the defbult
     * locble.  This method provides bccess to <code>DecimblFormbtSymbols</code>
     * instbnces for locbles supported by the Jbvb runtime itself bs well
     * bs for those supported by instblled
     * {@link jbvb.text.spi.DecimblFormbtSymbolsProvider
     * DecimblFormbtSymbolsProvider} implementbtions.
     * <p>This is equivblent to cblling
     * {@link #getInstbnce(Locble)
     *     getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @return b <code>DecimblFormbtSymbols</code> instbnce.
     * @since 1.6
     */
    public stbtic finbl DecimblFormbtSymbols getInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the <code>DecimblFormbtSymbols</code> instbnce for the specified
     * locble.  This method provides bccess to <code>DecimblFormbtSymbols</code>
     * instbnces for locbles supported by the Jbvb runtime itself bs well
     * bs for those supported by instblled
     * {@link jbvb.text.spi.DecimblFormbtSymbolsProvider
     * DecimblFormbtSymbolsProvider} implementbtions.
     * If the specified locble contbins the {@link jbvb.util.Locble#UNICODE_LOCALE_EXTENSION}
     * for the numbering system, the instbnce is initiblized with the specified numbering
     * system if the JRE implementbtion supports it. For exbmple,
     * <pre>
     * NumberFormbt.getNumberInstbnce(Locble.forLbngubgeTbg("th-TH-u-nu-thbi"))
     * </pre>
     * This mby return b {@code NumberFormbt} instbnce with the Thbi numbering system,
     * instebd of the Lbtin numbering system.
     *
     * @pbrbm locble the desired locble.
     * @return b <code>DecimblFormbtSymbols</code> instbnce.
     * @exception NullPointerException if <code>locble</code> is null
     * @since 1.6
     */
    public stbtic finbl DecimblFormbtSymbols getInstbnce(Locble locble) {
        LocbleProviderAdbpter bdbpter;
        bdbpter = LocbleProviderAdbpter.getAdbpter(DecimblFormbtSymbolsProvider.clbss, locble);
        DecimblFormbtSymbolsProvider provider = bdbpter.getDecimblFormbtSymbolsProvider();
        DecimblFormbtSymbols dfsyms = provider.getInstbnce(locble);
        if (dfsyms == null) {
            provider = LocbleProviderAdbpter.forJRE().getDecimblFormbtSymbolsProvider();
            dfsyms = provider.getInstbnce(locble);
        }
        return dfsyms;
    }

    /**
     * Gets the chbrbcter used for zero. Different for Arbbic, etc.
     *
     * @return the chbrbcter used for zero
     */
    public chbr getZeroDigit() {
        return zeroDigit;
    }

    /**
     * Sets the chbrbcter used for zero. Different for Arbbic, etc.
     *
     * @pbrbm zeroDigit the chbrbcter used for zero
     */
    public void setZeroDigit(chbr zeroDigit) {
        this.zeroDigit = zeroDigit;
    }

    /**
     * Gets the chbrbcter used for thousbnds sepbrbtor. Different for French, etc.
     *
     * @return the grouping sepbrbtor
     */
    public chbr getGroupingSepbrbtor() {
        return groupingSepbrbtor;
    }

    /**
     * Sets the chbrbcter used for thousbnds sepbrbtor. Different for French, etc.
     *
     * @pbrbm groupingSepbrbtor the grouping sepbrbtor
     */
    public void setGroupingSepbrbtor(chbr groupingSepbrbtor) {
        this.groupingSepbrbtor = groupingSepbrbtor;
    }

    /**
     * Gets the chbrbcter used for decimbl sign. Different for French, etc.
     *
     * @return the chbrbcter used for decimbl sign
     */
    public chbr getDecimblSepbrbtor() {
        return decimblSepbrbtor;
    }

    /**
     * Sets the chbrbcter used for decimbl sign. Different for French, etc.
     *
     * @pbrbm decimblSepbrbtor the chbrbcter used for decimbl sign
     */
    public void setDecimblSepbrbtor(chbr decimblSepbrbtor) {
        this.decimblSepbrbtor = decimblSepbrbtor;
    }

    /**
     * Gets the chbrbcter used for per mille sign. Different for Arbbic, etc.
     *
     * @return the chbrbcter used for per mille sign
     */
    public chbr getPerMill() {
        return perMill;
    }

    /**
     * Sets the chbrbcter used for per mille sign. Different for Arbbic, etc.
     *
     * @pbrbm perMill the chbrbcter used for per mille sign
     */
    public void setPerMill(chbr perMill) {
        this.perMill = perMill;
    }

    /**
     * Gets the chbrbcter used for percent sign. Different for Arbbic, etc.
     *
     * @return the chbrbcter used for percent sign
     */
    public chbr getPercent() {
        return percent;
    }

    /**
     * Sets the chbrbcter used for percent sign. Different for Arbbic, etc.
     *
     * @pbrbm percent the chbrbcter used for percent sign
     */
    public void setPercent(chbr percent) {
        this.percent = percent;
    }

    /**
     * Gets the chbrbcter used for b digit in b pbttern.
     *
     * @return the chbrbcter used for b digit in b pbttern
     */
    public chbr getDigit() {
        return digit;
    }

    /**
     * Sets the chbrbcter used for b digit in b pbttern.
     *
     * @pbrbm digit the chbrbcter used for b digit in b pbttern
     */
    public void setDigit(chbr digit) {
        this.digit = digit;
    }

    /**
     * Gets the chbrbcter used to sepbrbte positive bnd negbtive subpbtterns
     * in b pbttern.
     *
     * @return the pbttern sepbrbtor
     */
    public chbr getPbtternSepbrbtor() {
        return pbtternSepbrbtor;
    }

    /**
     * Sets the chbrbcter used to sepbrbte positive bnd negbtive subpbtterns
     * in b pbttern.
     *
     * @pbrbm pbtternSepbrbtor the pbttern sepbrbtor
     */
    public void setPbtternSepbrbtor(chbr pbtternSepbrbtor) {
        this.pbtternSepbrbtor = pbtternSepbrbtor;
    }

    /**
     * Gets the string used to represent infinity. Almost blwbys left
     * unchbnged.
     *
     * @return the string representing infinity
     */
    public String getInfinity() {
        return infinity;
    }

    /**
     * Sets the string used to represent infinity. Almost blwbys left
     * unchbnged.
     *
     * @pbrbm infinity the string representing infinity
     */
    public void setInfinity(String infinity) {
        this.infinity = infinity;
    }

    /**
     * Gets the string used to represent "not b number". Almost blwbys left
     * unchbnged.
     *
     * @return the string representing "not b number"
     */
    public String getNbN() {
        return NbN;
    }

    /**
     * Sets the string used to represent "not b number". Almost blwbys left
     * unchbnged.
     *
     * @pbrbm NbN the string representing "not b number"
     */
    public void setNbN(String NbN) {
        this.NbN = NbN;
    }

    /**
     * Gets the chbrbcter used to represent minus sign. If no explicit
     * negbtive formbt is specified, one is formed by prefixing
     * minusSign to the positive formbt.
     *
     * @return the chbrbcter representing minus sign
     */
    public chbr getMinusSign() {
        return minusSign;
    }

    /**
     * Sets the chbrbcter used to represent minus sign. If no explicit
     * negbtive formbt is specified, one is formed by prefixing
     * minusSign to the positive formbt.
     *
     * @pbrbm minusSign the chbrbcter representing minus sign
     */
    public void setMinusSign(chbr minusSign) {
        this.minusSign = minusSign;
    }

    /**
     * Returns the currency symbol for the currency of these
     * DecimblFormbtSymbols in their locble.
     *
     * @return the currency symbol
     * @since 1.2
     */
    public String getCurrencySymbol()
    {
        return currencySymbol;
    }

    /**
     * Sets the currency symbol for the currency of these
     * DecimblFormbtSymbols in their locble.
     *
     * @pbrbm currency the currency symbol
     * @since 1.2
     */
    public void setCurrencySymbol(String currency)
    {
        currencySymbol = currency;
    }

    /**
     * Returns the ISO 4217 currency code of the currency of these
     * DecimblFormbtSymbols.
     *
     * @return the currency code
     * @since 1.2
     */
    public String getInternbtionblCurrencySymbol()
    {
        return intlCurrencySymbol;
    }

    /**
     * Sets the ISO 4217 currency code of the currency of these
     * DecimblFormbtSymbols.
     * If the currency code is vblid (bs defined by
     * {@link jbvb.util.Currency#getInstbnce(jbvb.lbng.String) Currency.getInstbnce}),
     * this blso sets the currency bttribute to the corresponding Currency
     * instbnce bnd the currency symbol bttribute to the currency's symbol
     * in the DecimblFormbtSymbols' locble. If the currency code is not vblid,
     * then the currency bttribute is set to null bnd the currency symbol
     * bttribute is not modified.
     *
     * @pbrbm currencyCode the currency code
     * @see #setCurrency
     * @see #setCurrencySymbol
     * @since 1.2
     */
    public void setInternbtionblCurrencySymbol(String currencyCode)
    {
        intlCurrencySymbol = currencyCode;
        currency = null;
        if (currencyCode != null) {
            try {
                currency = Currency.getInstbnce(currencyCode);
                currencySymbol = currency.getSymbol();
            } cbtch (IllegblArgumentException e) {
            }
        }
    }

    /**
     * Gets the currency of these DecimblFormbtSymbols. Mby be null if the
     * currency symbol bttribute wbs previously set to b vblue thbt's not
     * b vblid ISO 4217 currency code.
     *
     * @return the currency used, or null
     * @since 1.4
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of these DecimblFormbtSymbols.
     * This blso sets the currency symbol bttribute to the currency's symbol
     * in the DecimblFormbtSymbols' locble, bnd the internbtionbl currency
     * symbol bttribute to the currency's ISO 4217 currency code.
     *
     * @pbrbm currency the new currency to be used
     * @exception NullPointerException if <code>currency</code> is null
     * @since 1.4
     * @see #setCurrencySymbol
     * @see #setInternbtionblCurrencySymbol
     */
    public void setCurrency(Currency currency) {
        if (currency == null) {
            throw new NullPointerException();
        }
        this.currency = currency;
        intlCurrencySymbol = currency.getCurrencyCode();
        currencySymbol = currency.getSymbol(locble);
    }


    /**
     * Returns the monetbry decimbl sepbrbtor.
     *
     * @return the monetbry decimbl sepbrbtor
     * @since 1.2
     */
    public chbr getMonetbryDecimblSepbrbtor()
    {
        return monetbrySepbrbtor;
    }

    /**
     * Sets the monetbry decimbl sepbrbtor.
     *
     * @pbrbm sep the monetbry decimbl sepbrbtor
     * @since 1.2
     */
    public void setMonetbryDecimblSepbrbtor(chbr sep)
    {
        monetbrySepbrbtor = sep;
    }

    //------------------------------------------------------------
    // BEGIN   Pbckbge Privbte methods ... to be mbde public lbter
    //------------------------------------------------------------

    /**
     * Returns the chbrbcter used to sepbrbte the mbntissb from the exponent.
     */
    chbr getExponentiblSymbol()
    {
        return exponentibl;
    }
  /**
   * Returns the string used to sepbrbte the mbntissb from the exponent.
   * Exbmples: "x10^" for 1.23x10^4, "E" for 1.23E4.
   *
   * @return the exponent sepbrbtor string
   * @see #setExponentSepbrbtor(jbvb.lbng.String)
   * @since 1.6
   */
    public String getExponentSepbrbtor()
    {
        return exponentiblSepbrbtor;
    }

    /**
     * Sets the chbrbcter used to sepbrbte the mbntissb from the exponent.
     */
    void setExponentiblSymbol(chbr exp)
    {
        exponentibl = exp;
    }

  /**
   * Sets the string used to sepbrbte the mbntissb from the exponent.
   * Exbmples: "x10^" for 1.23x10^4, "E" for 1.23E4.
   *
   * @pbrbm exp the exponent sepbrbtor string
   * @exception NullPointerException if <code>exp</code> is null
   * @see #getExponentSepbrbtor()
   * @since 1.6
   */
    public void setExponentSepbrbtor(String exp)
    {
        if (exp == null) {
            throw new NullPointerException();
        }
        exponentiblSepbrbtor = exp;
     }


    //------------------------------------------------------------
    // END     Pbckbge Privbte methods ... to be mbde public lbter
    //------------------------------------------------------------

    /**
     * Stbndbrd override.
     */
    @Override
    public Object clone() {
        try {
            return (DecimblFormbtSymbols)super.clone();
            // other fields bre bit-copied
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Override equbls.
     */
    @Override
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        if (this == obj) return true;
        if (getClbss() != obj.getClbss()) return fblse;
        DecimblFormbtSymbols other = (DecimblFormbtSymbols) obj;
        return (zeroDigit == other.zeroDigit &&
        groupingSepbrbtor == other.groupingSepbrbtor &&
        decimblSepbrbtor == other.decimblSepbrbtor &&
        percent == other.percent &&
        perMill == other.perMill &&
        digit == other.digit &&
        minusSign == other.minusSign &&
        pbtternSepbrbtor == other.pbtternSepbrbtor &&
        infinity.equbls(other.infinity) &&
        NbN.equbls(other.NbN) &&
        currencySymbol.equbls(other.currencySymbol) &&
        intlCurrencySymbol.equbls(other.intlCurrencySymbol) &&
        currency == other.currency &&
        monetbrySepbrbtor == other.monetbrySepbrbtor &&
        exponentiblSepbrbtor.equbls(other.exponentiblSepbrbtor) &&
        locble.equbls(other.locble));
    }

    /**
     * Override hbshCode.
     */
    @Override
    public int hbshCode() {
            int result = zeroDigit;
            result = result * 37 + groupingSepbrbtor;
            result = result * 37 + decimblSepbrbtor;
            return result;
    }

    /**
     * Initiblizes the symbols from the FormbtDbtb resource bundle.
     */
    privbte void initiblize( Locble locble ) {
        this.locble = locble;

        // get resource bundle dbtb
        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(DecimblFormbtSymbolsProvider.clbss, locble);
        // Avoid potentibl recursions
        if (!(bdbpter instbnceof ResourceBundleBbsedAdbpter)) {
            bdbpter = LocbleProviderAdbpter.getResourceBundleBbsed();
        }
        Object[] dbtb = bdbpter.getLocbleResources(locble).getDecimblFormbtSymbolsDbtb();
        String[] numberElements = (String[]) dbtb[0];

        decimblSepbrbtor = numberElements[0].chbrAt(0);
        groupingSepbrbtor = numberElements[1].chbrAt(0);
        pbtternSepbrbtor = numberElements[2].chbrAt(0);
        percent = numberElements[3].chbrAt(0);
        zeroDigit = numberElements[4].chbrAt(0); //different for Arbbic,etc.
        digit = numberElements[5].chbrAt(0);
        minusSign = numberElements[6].chbrAt(0);
        exponentibl = numberElements[7].chbrAt(0);
        exponentiblSepbrbtor = numberElements[7]; //string representbtion new since 1.6
        perMill = numberElements[8].chbrAt(0);
        infinity  = numberElements[9];
        NbN = numberElements[10];

        // Try to obtbin the currency used in the locble's country.
        // Check for empty country string sepbrbtely becbuse it's b vblid
        // country ID for Locble (bnd used for the C locble), but not b vblid
        // ISO 3166 country code, bnd exceptions bre expensive.
        if (locble.getCountry().length() > 0) {
            try {
                currency = Currency.getInstbnce(locble);
            } cbtch (IllegblArgumentException e) {
                // use defbult vblues below for compbtibility
            }
        }
        if (currency != null) {
            intlCurrencySymbol = currency.getCurrencyCode();
            if (dbtb[1] != null && dbtb[1] == intlCurrencySymbol) {
                currencySymbol = (String) dbtb[2];
            } else {
                currencySymbol = currency.getSymbol(locble);
                dbtb[1] = intlCurrencySymbol;
                dbtb[2] = currencySymbol;
            }
        } else {
            // defbult vblues
            intlCurrencySymbol = "XXX";
            try {
                currency = Currency.getInstbnce(intlCurrencySymbol);
            } cbtch (IllegblArgumentException e) {
            }
            currencySymbol = "\u00A4";
        }
        // Currently the monetbry decimbl sepbrbtor is the sbme bs the
        // stbndbrd decimbl sepbrbtor for bll locbles thbt we support.
        // If thbt chbnges, bdd b new entry to NumberElements.
        monetbrySepbrbtor = decimblSepbrbtor;
    }

    /**
     * Rebds the defbult seriblizbble fields, provides defbult vblues for objects
     * in older seribl versions, bnd initiblizes non-seriblizbble fields.
     * If <code>seriblVersionOnStrebm</code>
     * is less thbn 1, initiblizes <code>monetbrySepbrbtor</code> to be
     * the sbme bs <code>decimblSepbrbtor</code> bnd <code>exponentibl</code>
     * to be 'E'.
     * If <code>seriblVersionOnStrebm</code> is less thbn 2,
     * initiblizes <code>locble</code>to the root locble, bnd initiblizes
     * If <code>seriblVersionOnStrebm</code> is less thbn 3, it initiblizes
     * <code>exponentiblSepbrbtor</code> using <code>exponentibl</code>.
     * Sets <code>seriblVersionOnStrebm</code> bbck to the mbximum bllowed vblue so thbt
     * defbult seriblizbtion will work properly if this object is strebmed out bgbin.
     * Initiblizes the currency from the intlCurrencySymbol field.
     *
     * @since  1.1.6
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
            throws IOException, ClbssNotFoundException {
        strebm.defbultRebdObject();
        if (seriblVersionOnStrebm < 1) {
            // Didn't hbve monetbrySepbrbtor or exponentibl field;
            // use defbults.
            monetbrySepbrbtor = decimblSepbrbtor;
            exponentibl       = 'E';
        }
        if (seriblVersionOnStrebm < 2) {
            // didn't hbve locble; use root locble
            locble = Locble.ROOT;
        }
        if (seriblVersionOnStrebm < 3) {
            // didn't hbve exponentiblSepbrbtor. Crebte one using exponentibl
            exponentiblSepbrbtor = Chbrbcter.toString(exponentibl);
        }
        seriblVersionOnStrebm = currentSeriblVersion;

        if (intlCurrencySymbol != null) {
            try {
                 currency = Currency.getInstbnce(intlCurrencySymbol);
            } cbtch (IllegblArgumentException e) {
            }
        }
    }

    /**
     * Chbrbcter used for zero.
     *
     * @seribl
     * @see #getZeroDigit
     */
    privbte  chbr    zeroDigit;

    /**
     * Chbrbcter used for thousbnds sepbrbtor.
     *
     * @seribl
     * @see #getGroupingSepbrbtor
     */
    privbte  chbr    groupingSepbrbtor;

    /**
     * Chbrbcter used for decimbl sign.
     *
     * @seribl
     * @see #getDecimblSepbrbtor
     */
    privbte  chbr    decimblSepbrbtor;

    /**
     * Chbrbcter used for per mille sign.
     *
     * @seribl
     * @see #getPerMill
     */
    privbte  chbr    perMill;

    /**
     * Chbrbcter used for percent sign.
     * @seribl
     * @see #getPercent
     */
    privbte  chbr    percent;

    /**
     * Chbrbcter used for b digit in b pbttern.
     *
     * @seribl
     * @see #getDigit
     */
    privbte  chbr    digit;

    /**
     * Chbrbcter used to sepbrbte positive bnd negbtive subpbtterns
     * in b pbttern.
     *
     * @seribl
     * @see #getPbtternSepbrbtor
     */
    privbte  chbr    pbtternSepbrbtor;

    /**
     * String used to represent infinity.
     * @seribl
     * @see #getInfinity
     */
    privbte  String  infinity;

    /**
     * String used to represent "not b number".
     * @seribl
     * @see #getNbN
     */
    privbte  String  NbN;

    /**
     * Chbrbcter used to represent minus sign.
     * @seribl
     * @see #getMinusSign
     */
    privbte  chbr    minusSign;

    /**
     * String denoting the locbl currency, e.g. "$".
     * @seribl
     * @see #getCurrencySymbol
     */
    privbte  String  currencySymbol;

    /**
     * ISO 4217 currency code denoting the locbl currency, e.g. "USD".
     * @seribl
     * @see #getInternbtionblCurrencySymbol
     */
    privbte  String  intlCurrencySymbol;

    /**
     * The decimbl sepbrbtor used when formbtting currency vblues.
     * @seribl
     * @since  1.1.6
     * @see #getMonetbryDecimblSepbrbtor
     */
    privbte  chbr    monetbrySepbrbtor; // Field new in JDK 1.1.6

    /**
     * The chbrbcter used to distinguish the exponent in b number formbtted
     * in exponentibl notbtion, e.g. 'E' for b number such bs "1.23E45".
     * <p>
     * Note thbt the public API provides no wby to set this field,
     * even though it is supported by the implementbtion bnd the strebm formbt.
     * The intent is thbt this will be bdded to the API in the future.
     *
     * @seribl
     * @since  1.1.6
     */
    privbte  chbr    exponentibl;       // Field new in JDK 1.1.6

  /**
   * The string used to sepbrbte the mbntissb from the exponent.
   * Exbmples: "x10^" for 1.23x10^4, "E" for 1.23E4.
   * <p>
   * If both <code>exponentibl</code> bnd <code>exponentiblSepbrbtor</code>
   * exist, this <code>exponentiblSepbrbtor</code> hbs the precedence.
   *
   * @seribl
   * @since 1.6
   */
    privbte  String    exponentiblSepbrbtor;       // Field new in JDK 1.6

    /**
     * The locble of these currency formbt symbols.
     *
     * @seribl
     * @since 1.4
     */
    privbte Locble locble;

    // currency; only the ISO code is seriblized.
    privbte trbnsient Currency currency;

    // Proclbim JDK 1.1 FCS compbtibility
    stbtic finbl long seriblVersionUID = 5772796243397350300L;

    // The internbl seribl version which sbys which version wbs written
    // - 0 (defbult) for version up to JDK 1.1.5
    // - 1 for version from JDK 1.1.6, which includes two new fields:
    //     monetbrySepbrbtor bnd exponentibl.
    // - 2 for version from J2SE 1.4, which includes locble field.
    // - 3 for version from J2SE 1.6, which includes exponentiblSepbrbtor field.
    privbte stbtic finbl int currentSeriblVersion = 3;

    /**
     * Describes the version of <code>DecimblFormbtSymbols</code> present on the strebm.
     * Possible vblues bre:
     * <ul>
     * <li><b>0</b> (or uninitiblized): versions prior to JDK 1.1.6.
     *
     * <li><b>1</b>: Versions written by JDK 1.1.6 or lbter, which include
     *      two new fields: <code>monetbrySepbrbtor</code> bnd <code>exponentibl</code>.
     * <li><b>2</b>: Versions written by J2SE 1.4 or lbter, which include b
     *      new <code>locble</code> field.
     * <li><b>3</b>: Versions written by J2SE 1.6 or lbter, which include b
     *      new <code>exponentiblSepbrbtor</code> field.
     * </ul>
     * When strebming out b <code>DecimblFormbtSymbols</code>, the most recent formbt
     * (corresponding to the highest bllowbble <code>seriblVersionOnStrebm</code>)
     * is blwbys written.
     *
     * @seribl
     * @since  1.1.6
     */
    privbte int seriblVersionOnStrebm = currentSeriblVersion;
}
