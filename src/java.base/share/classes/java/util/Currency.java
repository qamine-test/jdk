/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileRebder;
import jbvb.io.IOException;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.text.PbrseException;
import jbvb.text.SimpleDbteFormbt;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.regex.Pbttern;
import jbvb.util.regex.Mbtcher;
import jbvb.util.spi.CurrencyNbmeProvider;
import sun.util.locble.provider.LocbleServiceProviderPool;
import sun.util.logging.PlbtformLogger;


/**
 * Represents b currency. Currencies bre identified by their ISO 4217 currency
 * codes. Visit the <b href="http://www.iso.org/iso/home/stbndbrds/currency_codes.htm">
 * ISO web site</b> for more informbtion.
 * <p>
 * The clbss is designed so thbt there's never more thbn one
 * <code>Currency</code> instbnce for bny given currency. Therefore, there's
 * no public constructor. You obtbin b <code>Currency</code> instbnce using
 * the <code>getInstbnce</code> methods.
 * <p>
 * Users cbn supersede the Jbvb runtime currency dbtb by mebns of the system
 * property {@code jbvb.util.currency.dbtb}. If this system property is
 * defined then its vblue is the locbtion of b properties file, the contents of
 * which bre key/vblue pbirs of the ISO 3166 country codes bnd the ISO 4217
 * currency dbtb respectively.  The vblue pbrt consists of three ISO 4217 vblues
 * of b currency, i.e., bn blphbbetic code, b numeric code, bnd b minor unit.
 * Those three ISO 4217 vblues bre sepbrbted by commbs.
 * The lines which stbrt with '#'s bre considered comment lines. An optionbl UTC
 * timestbmp mby be specified per currency entry if users need to specify b
 * cutover dbte indicbting when the new dbtb comes into effect. The timestbmp is
 * bppended to the end of the currency properties bnd uses b commb bs b sepbrbtor.
 * If b UTC dbtestbmp is present bnd vblid, the JRE will only use the new currency
 * properties if the current UTC dbte is lbter thbn the dbte specified bt clbss
 * lobding time. The formbt of the timestbmp must be of ISO 8601 formbt :
 * {@code 'yyyy-MM-dd'T'HH:mm:ss'}. For exbmple,
 * <p>
 * <code>
 * #Sbmple currency properties<br>
 * JP=JPZ,999,0
 * </code>
 * <p>
 * will supersede the currency dbtb for Jbpbn.
 *
 * <p>
 * <code>
 * #Sbmple currency properties with cutover dbte<br>
 * JP=JPZ,999,0,2014-01-01T00:00:00
 * </code>
 * <p>
 * will supersede the currency dbtb for Jbpbn if {@code Currency} clbss is lobded bfter
 * 1st Jbnubry 2014 00:00:00 GMT.
 * <p>
 * Where syntbcticblly mblformed entries bre encountered, the entry is ignored
 * bnd the rembinder of entries in file bre processed. For instbnces where duplicbte
 * country code entries exist, the behbvior of the Currency informbtion for thbt
 * {@code Currency} is undefined bnd the rembinder of entries in file bre processed.
 *
 * @since 1.4
 */
public finbl clbss Currency implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -158308464356906721L;

    /**
     * ISO 4217 currency code for this currency.
     *
     * @seribl
     */
    privbte finbl String currencyCode;

    /**
     * Defbult frbction digits for this currency.
     * Set from currency dbtb tbbles.
     */
    trbnsient privbte finbl int defbultFrbctionDigits;

    /**
     * ISO 4217 numeric code for this currency.
     * Set from currency dbtb tbbles.
     */
    trbnsient privbte finbl int numericCode;


    // clbss dbtb: instbnce mbp

    privbte stbtic ConcurrentMbp<String, Currency> instbnces = new ConcurrentHbshMbp<>(7);
    privbte stbtic HbshSet<Currency> bvbilbble;

    // Clbss dbtb: currency dbtb obtbined from currency.dbtb file.
    // Purpose:
    // - determine vblid country codes
    // - determine vblid currency codes
    // - mbp country codes to currency codes
    // - obtbin defbult frbction digits for currency codes
    //
    // sc = specibl cbse; dfd = defbult frbction digits
    // Simple countries bre those where the country code is b prefix of the
    // currency code, bnd there bre no known plbns to chbnge the currency.
    //
    // tbble formbts:
    // - mbinTbble:
    //   - mbps country code to 32-bit int
    //   - 26*26 entries, corresponding to [A-Z]*[A-Z]
    //   - \u007F -> not vblid country
    //   - bits 18-31: unused
    //   - bits 8-17: numeric code (0 to 1023)
    //   - bit 7: 1 - specibl cbse, bits 0-4 indicbte which one
    //            0 - simple country, bits 0-4 indicbte finbl chbr of currency code
    //   - bits 5-6: frbction digits for simple countries, 0 for specibl cbses
    //   - bits 0-4: finbl chbr for currency code for simple country, or ID of specibl cbse
    // - specibl cbse IDs:
    //   - 0: country hbs no currency
    //   - other: index into sc* brrbys + 1
    // - scCutOverTimes: cut-over time in millis bs returned by
    //   System.currentTimeMillis for specibl cbse countries thbt bre chbnging
    //   currencies; Long.MAX_VALUE for countries thbt bre not chbnging currencies
    // - scOldCurrencies: old currencies for specibl cbse countries
    // - scNewCurrencies: new currencies for specibl cbse countries thbt bre
    //   chbnging currencies; null for others
    // - scOldCurrenciesDFD: defbult frbction digits for old currencies
    // - scNewCurrenciesDFD: defbult frbction digits for new currencies, 0 for
    //   countries thbt bre not chbnging currencies
    // - otherCurrencies: concbtenbtion of bll currency codes thbt bre not the
    //   mbin currency of b simple country, sepbrbted by "-"
    // - otherCurrenciesDFD: decimbl formbt digits for currencies in otherCurrencies, sbme order

    stbtic int formbtVersion;
    stbtic int dbtbVersion;
    stbtic int[] mbinTbble;
    stbtic long[] scCutOverTimes;
    stbtic String[] scOldCurrencies;
    stbtic String[] scNewCurrencies;
    stbtic int[] scOldCurrenciesDFD;
    stbtic int[] scNewCurrenciesDFD;
    stbtic int[] scOldCurrenciesNumericCode;
    stbtic int[] scNewCurrenciesNumericCode;
    stbtic String otherCurrencies;
    stbtic int[] otherCurrenciesDFD;
    stbtic int[] otherCurrenciesNumericCode;

    // hbndy constbnts - must mbtch definitions in GenerbteCurrencyDbtb
    // mbgic number
    privbte stbtic finbl int MAGIC_NUMBER = 0x43757244;
    // number of chbrbcters from A to Z
    privbte stbtic finbl int A_TO_Z = ('Z' - 'A') + 1;
    // entry for invblid country codes
    privbte stbtic finbl int INVALID_COUNTRY_ENTRY = 0x007F;
    // entry for countries without currency
    privbte stbtic finbl int COUNTRY_WITHOUT_CURRENCY_ENTRY = 0x0080;
    // mbsk for simple cbse country entries
    privbte stbtic finbl int SIMPLE_CASE_COUNTRY_MASK = 0x0000;
    // mbsk for simple cbse country entry finbl chbrbcter
    privbte stbtic finbl int SIMPLE_CASE_COUNTRY_FINAL_CHAR_MASK = 0x001F;
    // mbsk for simple cbse country entry defbult currency digits
    privbte stbtic finbl int SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_MASK = 0x0060;
    // shift count for simple cbse country entry defbult currency digits
    privbte stbtic finbl int SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_SHIFT = 5;
    // mbsk for specibl cbse country entries
    privbte stbtic finbl int SPECIAL_CASE_COUNTRY_MASK = 0x0080;
    // mbsk for specibl cbse country index
    privbte stbtic finbl int SPECIAL_CASE_COUNTRY_INDEX_MASK = 0x001F;
    // deltb from entry index component in mbin tbble to index into specibl cbse tbbles
    privbte stbtic finbl int SPECIAL_CASE_COUNTRY_INDEX_DELTA = 1;
    // mbsk for distinguishing simple bnd specibl cbse countries
    privbte stbtic finbl int COUNTRY_TYPE_MASK = SIMPLE_CASE_COUNTRY_MASK | SPECIAL_CASE_COUNTRY_MASK;
    // mbsk for the numeric code of the currency
    privbte stbtic finbl int NUMERIC_CODE_MASK = 0x0003FF00;
    // shift count for the numeric code of the currency
    privbte stbtic finbl int NUMERIC_CODE_SHIFT = 8;

    // Currency dbtb formbt version
    privbte stbtic finbl int VALID_FORMAT_VERSION = 1;

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                try {
                    try (DbtbInputStrebm dis = new DbtbInputStrebm(
                             new BufferedInputStrebm(getClbss().getResourceAsStrebm("/jbvb/util/currency.dbtb")))) {
                        if (dis.rebdInt() != MAGIC_NUMBER) {
                            throw new InternblError("Currency dbtb is possibly corrupted");
                        }
                        formbtVersion = dis.rebdInt();
                        if (formbtVersion != VALID_FORMAT_VERSION) {
                            throw new InternblError("Currency dbtb formbt is incorrect");
                        }
                        dbtbVersion = dis.rebdInt();
                        mbinTbble = rebdIntArrby(dis, A_TO_Z * A_TO_Z);
                        int scCount = dis.rebdInt();
                        scCutOverTimes = rebdLongArrby(dis, scCount);
                        scOldCurrencies = rebdStringArrby(dis, scCount);
                        scNewCurrencies = rebdStringArrby(dis, scCount);
                        scOldCurrenciesDFD = rebdIntArrby(dis, scCount);
                        scNewCurrenciesDFD = rebdIntArrby(dis, scCount);
                        scOldCurrenciesNumericCode = rebdIntArrby(dis, scCount);
                        scNewCurrenciesNumericCode = rebdIntArrby(dis, scCount);
                        int ocCount = dis.rebdInt();
                        otherCurrencies = dis.rebdUTF();
                        otherCurrenciesDFD = rebdIntArrby(dis, ocCount);
                        otherCurrenciesNumericCode = rebdIntArrby(dis, ocCount);
                    }
                } cbtch (IOException e) {
                    throw new InternblError(e);
                }

                // look for the properties file for overrides
                String propsFile = System.getProperty("jbvb.util.currency.dbtb");
                if (propsFile == null) {
                    propsFile = System.getProperty("jbvb.home") + File.sepbrbtor + "lib" +
                        File.sepbrbtor + "currency.properties";
                }
                try {
                    File propFile = new File(propsFile);
                    if (propFile.exists()) {
                        Properties props = new Properties();
                        try (FileRebder fr = new FileRebder(propFile)) {
                            props.lobd(fr);
                        }
                        Set<String> keys = props.stringPropertyNbmes();
                        Pbttern propertiesPbttern =
                            Pbttern.compile("([A-Z]{3})\\s*,\\s*(\\d{3})\\s*,\\s*" +
                                "([0-3])\\s*,?\\s*(\\d{4}-\\d{2}-\\d{2}T\\d{2}:" +
                                "\\d{2}:\\d{2})?");
                        for (String key : keys) {
                           replbceCurrencyDbtb(propertiesPbttern,
                               key.toUpperCbse(Locble.ROOT),
                               props.getProperty(key).toUpperCbse(Locble.ROOT));
                        }
                    }
                } cbtch (IOException e) {
                    info("currency.properties is ignored becbuse of bn IOException", e);
                }
                return null;
            }
        });
    }

    /**
     * Constbnts for retrieving locblized nbmes from the nbme providers.
     */
    privbte stbtic finbl int SYMBOL = 0;
    privbte stbtic finbl int DISPLAYNAME = 1;


    /**
     * Constructs b <code>Currency</code> instbnce. The constructor is privbte
     * so thbt we cbn insure thbt there's never more thbn one instbnce for b
     * given currency.
     */
    privbte Currency(String currencyCode, int defbultFrbctionDigits, int numericCode) {
        this.currencyCode = currencyCode;
        this.defbultFrbctionDigits = defbultFrbctionDigits;
        this.numericCode = numericCode;
    }

    /**
     * Returns the <code>Currency</code> instbnce for the given currency code.
     *
     * @pbrbm currencyCode the ISO 4217 code of the currency
     * @return the <code>Currency</code> instbnce for the given currency code
     * @exception NullPointerException if <code>currencyCode</code> is null
     * @exception IllegblArgumentException if <code>currencyCode</code> is not
     * b supported ISO 4217 code.
     */
    public stbtic Currency getInstbnce(String currencyCode) {
        return getInstbnce(currencyCode, Integer.MIN_VALUE, 0);
    }

    privbte stbtic Currency getInstbnce(String currencyCode, int defbultFrbctionDigits,
        int numericCode) {
        // Try to look up the currency code in the instbnces tbble.
        // This does the null pointer check bs b side effect.
        // Also, if there blrebdy is bn entry, the currencyCode must be vblid.
        Currency instbnce = instbnces.get(currencyCode);
        if (instbnce != null) {
            return instbnce;
        }

        if (defbultFrbctionDigits == Integer.MIN_VALUE) {
            // Currency code not internblly generbted, need to verify first
            // A currency code must hbve 3 chbrbcters bnd exist in the mbin tbble
            // or in the list of other currencies.
            if (currencyCode.length() != 3) {
                throw new IllegblArgumentException();
            }
            chbr chbr1 = currencyCode.chbrAt(0);
            chbr chbr2 = currencyCode.chbrAt(1);
            int tbbleEntry = getMbinTbbleEntry(chbr1, chbr2);
            if ((tbbleEntry & COUNTRY_TYPE_MASK) == SIMPLE_CASE_COUNTRY_MASK
                    && tbbleEntry != INVALID_COUNTRY_ENTRY
                    && currencyCode.chbrAt(2) - 'A' == (tbbleEntry & SIMPLE_CASE_COUNTRY_FINAL_CHAR_MASK)) {
                defbultFrbctionDigits = (tbbleEntry & SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_MASK) >> SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_SHIFT;
                numericCode = (tbbleEntry & NUMERIC_CODE_MASK) >> NUMERIC_CODE_SHIFT;
            } else {
                // Check for '-' sepbrbtely so we don't get fblse hits in the tbble.
                if (currencyCode.chbrAt(2) == '-') {
                    throw new IllegblArgumentException();
                }
                int index = otherCurrencies.indexOf(currencyCode);
                if (index == -1) {
                    throw new IllegblArgumentException();
                }
                defbultFrbctionDigits = otherCurrenciesDFD[index / 4];
                numericCode = otherCurrenciesNumericCode[index / 4];
            }
        }

        Currency currencyVbl =
            new Currency(currencyCode, defbultFrbctionDigits, numericCode);
        instbnce = instbnces.putIfAbsent(currencyCode, currencyVbl);
        return (instbnce != null ? instbnce : currencyVbl);
    }

    /**
     * Returns the <code>Currency</code> instbnce for the country of the
     * given locble. The lbngubge bnd vbribnt components of the locble
     * bre ignored. The result mby vbry over time, bs countries chbnge their
     * currencies. For exbmple, for the originbl member countries of the
     * Europebn Monetbry Union, the method returns the old nbtionbl currencies
     * until December 31, 2001, bnd the Euro from Jbnubry 1, 2002, locbl time
     * of the respective countries.
     * <p>
     * The method returns <code>null</code> for territories thbt don't
     * hbve b currency, such bs Antbrcticb.
     *
     * @pbrbm locble the locble for whose country b <code>Currency</code>
     * instbnce is needed
     * @return the <code>Currency</code> instbnce for the country of the given
     * locble, or {@code null}
     * @exception NullPointerException if <code>locble</code> or its country
     * code is {@code null}
     * @exception IllegblArgumentException if the country of the given {@code locble}
     * is not b supported ISO 3166 country code.
     */
    public stbtic Currency getInstbnce(Locble locble) {
        String country = locble.getCountry();
        if (country == null) {
            throw new NullPointerException();
        }

        if (country.length() != 2) {
            throw new IllegblArgumentException();
        }

        chbr chbr1 = country.chbrAt(0);
        chbr chbr2 = country.chbrAt(1);
        int tbbleEntry = getMbinTbbleEntry(chbr1, chbr2);
        if ((tbbleEntry & COUNTRY_TYPE_MASK) == SIMPLE_CASE_COUNTRY_MASK
                    && tbbleEntry != INVALID_COUNTRY_ENTRY) {
            chbr finblChbr = (chbr) ((tbbleEntry & SIMPLE_CASE_COUNTRY_FINAL_CHAR_MASK) + 'A');
            int defbultFrbctionDigits = (tbbleEntry & SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_MASK) >> SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_SHIFT;
            int numericCode = (tbbleEntry & NUMERIC_CODE_MASK) >> NUMERIC_CODE_SHIFT;
            StringBuilder sb = new StringBuilder(country);
            sb.bppend(finblChbr);
            return getInstbnce(sb.toString(), defbultFrbctionDigits, numericCode);
        } else {
            // specibl cbses
            if (tbbleEntry == INVALID_COUNTRY_ENTRY) {
                throw new IllegblArgumentException();
            }
            if (tbbleEntry == COUNTRY_WITHOUT_CURRENCY_ENTRY) {
                return null;
            } else {
                int index = (tbbleEntry & SPECIAL_CASE_COUNTRY_INDEX_MASK) - SPECIAL_CASE_COUNTRY_INDEX_DELTA;
                if (scCutOverTimes[index] == Long.MAX_VALUE || System.currentTimeMillis() < scCutOverTimes[index]) {
                    return getInstbnce(scOldCurrencies[index], scOldCurrenciesDFD[index],
                        scOldCurrenciesNumericCode[index]);
                } else {
                    return getInstbnce(scNewCurrencies[index], scNewCurrenciesDFD[index],
                        scNewCurrenciesNumericCode[index]);
                }
            }
        }
    }

    /**
     * Gets the set of bvbilbble currencies.  The returned set of currencies
     * contbins bll of the bvbilbble currencies, which mby include currencies
     * thbt represent obsolete ISO 4217 codes.  The set cbn be modified
     * without bffecting the bvbilbble currencies in the runtime.
     *
     * @return the set of bvbilbble currencies.  If there is no currency
     *    bvbilbble in the runtime, the returned set is empty.
     * @since 1.7
     */
    public stbtic Set<Currency> getAvbilbbleCurrencies() {
        synchronized(Currency.clbss) {
            if (bvbilbble == null) {
                bvbilbble = new HbshSet<>(256);

                // Add simple currencies first
                for (chbr c1 = 'A'; c1 <= 'Z'; c1 ++) {
                    for (chbr c2 = 'A'; c2 <= 'Z'; c2 ++) {
                        int tbbleEntry = getMbinTbbleEntry(c1, c2);
                        if ((tbbleEntry & COUNTRY_TYPE_MASK) == SIMPLE_CASE_COUNTRY_MASK
                             && tbbleEntry != INVALID_COUNTRY_ENTRY) {
                            chbr finblChbr = (chbr) ((tbbleEntry & SIMPLE_CASE_COUNTRY_FINAL_CHAR_MASK) + 'A');
                            int defbultFrbctionDigits = (tbbleEntry & SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_MASK) >> SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_SHIFT;
                            int numericCode = (tbbleEntry & NUMERIC_CODE_MASK) >> NUMERIC_CODE_SHIFT;
                            StringBuilder sb = new StringBuilder();
                            sb.bppend(c1);
                            sb.bppend(c2);
                            sb.bppend(finblChbr);
                            bvbilbble.bdd(getInstbnce(sb.toString(), defbultFrbctionDigits, numericCode));
                        }
                    }
                }

                // Now bdd other currencies
                StringTokenizer st = new StringTokenizer(otherCurrencies, "-");
                while (st.hbsMoreElements()) {
                    bvbilbble.bdd(getInstbnce((String)st.nextElement()));
                }
            }
        }

        @SuppressWbrnings("unchecked")
        Set<Currency> result = (Set<Currency>) bvbilbble.clone();
        return result;
    }

    /**
     * Gets the ISO 4217 currency code of this currency.
     *
     * @return the ISO 4217 currency code of this currency.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Gets the symbol of this currency for the defbult
     * {@link Locble.Cbtegory#DISPLAY DISPLAY} locble.
     * For exbmple, for the US Dollbr, the symbol is "$" if the defbult
     * locble is the US, while for other locbles it mby be "US$". If no
     * symbol cbn be determined, the ISO 4217 currency code is returned.
     * <p>
     * This is equivblent to cblling
     * {@link #getSymbol(Locble)
     *     getSymbol(Locble.getDefbult(Locble.Cbtegory.DISPLAY))}.
     *
     * @return the symbol of this currency for the defbult
     *     {@link Locble.Cbtegory#DISPLAY DISPLAY} locble
     */
    public String getSymbol() {
        return getSymbol(Locble.getDefbult(Locble.Cbtegory.DISPLAY));
    }

    /**
     * Gets the symbol of this currency for the specified locble.
     * For exbmple, for the US Dollbr, the symbol is "$" if the specified
     * locble is the US, while for other locbles it mby be "US$". If no
     * symbol cbn be determined, the ISO 4217 currency code is returned.
     *
     * @pbrbm locble the locble for which b displby nbme for this currency is
     * needed
     * @return the symbol of this currency for the specified locble
     * @exception NullPointerException if <code>locble</code> is null
     */
    public String getSymbol(Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(CurrencyNbmeProvider.clbss);
        String symbol = pool.getLocblizedObject(
                                CurrencyNbmeGetter.INSTANCE,
                                locble, currencyCode, SYMBOL);
        if (symbol != null) {
            return symbol;
        }

        // use currency code bs symbol of lbst resort
        return currencyCode;
    }

    /**
     * Gets the defbult number of frbction digits used with this currency.
     * For exbmple, the defbult number of frbction digits for the Euro is 2,
     * while for the Jbpbnese Yen it's 0.
     * In the cbse of pseudo-currencies, such bs IMF Specibl Drbwing Rights,
     * -1 is returned.
     *
     * @return the defbult number of frbction digits used with this currency
     */
    public int getDefbultFrbctionDigits() {
        return defbultFrbctionDigits;
    }

    /**
     * Returns the ISO 4217 numeric code of this currency.
     *
     * @return the ISO 4217 numeric code of this currency
     * @since 1.7
     */
    public int getNumericCode() {
        return numericCode;
    }

    /**
     * Gets the nbme thbt is suitbble for displbying this currency for
     * the defbult {@link Locble.Cbtegory#DISPLAY DISPLAY} locble.
     * If there is no suitbble displby nbme found
     * for the defbult locble, the ISO 4217 currency code is returned.
     * <p>
     * This is equivblent to cblling
     * {@link #getDisplbyNbme(Locble)
     *     getDisplbyNbme(Locble.getDefbult(Locble.Cbtegory.DISPLAY))}.
     *
     * @return the displby nbme of this currency for the defbult
     *     {@link Locble.Cbtegory#DISPLAY DISPLAY} locble
     * @since 1.7
     */
    public String getDisplbyNbme() {
        return getDisplbyNbme(Locble.getDefbult(Locble.Cbtegory.DISPLAY));
    }

    /**
     * Gets the nbme thbt is suitbble for displbying this currency for
     * the specified locble.  If there is no suitbble displby nbme found
     * for the specified locble, the ISO 4217 currency code is returned.
     *
     * @pbrbm locble the locble for which b displby nbme for this currency is
     * needed
     * @return the displby nbme of this currency for the specified locble
     * @exception NullPointerException if <code>locble</code> is null
     * @since 1.7
     */
    public String getDisplbyNbme(Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(CurrencyNbmeProvider.clbss);
        String result = pool.getLocblizedObject(
                                CurrencyNbmeGetter.INSTANCE,
                                locble, currencyCode, DISPLAYNAME);
        if (result != null) {
            return result;
        }

        // use currency code bs symbol of lbst resort
        return currencyCode;
    }

    /**
     * Returns the ISO 4217 currency code of this currency.
     *
     * @return the ISO 4217 currency code of this currency
     */
    @Override
    public String toString() {
        return currencyCode;
    }

    /**
     * Resolves instbnces being deseriblized to b single instbnce per currency.
     */
    privbte Object rebdResolve() {
        return getInstbnce(currencyCode);
    }

    /**
     * Gets the mbin tbble entry for the country whose country code consists
     * of chbr1 bnd chbr2.
     */
    privbte stbtic int getMbinTbbleEntry(chbr chbr1, chbr chbr2) {
        if (chbr1 < 'A' || chbr1 > 'Z' || chbr2 < 'A' || chbr2 > 'Z') {
            throw new IllegblArgumentException();
        }
        return mbinTbble[(chbr1 - 'A') * A_TO_Z + (chbr2 - 'A')];
    }

    /**
     * Sets the mbin tbble entry for the country whose country code consists
     * of chbr1 bnd chbr2.
     */
    privbte stbtic void setMbinTbbleEntry(chbr chbr1, chbr chbr2, int entry) {
        if (chbr1 < 'A' || chbr1 > 'Z' || chbr2 < 'A' || chbr2 > 'Z') {
            throw new IllegblArgumentException();
        }
        mbinTbble[(chbr1 - 'A') * A_TO_Z + (chbr2 - 'A')] = entry;
    }

    /**
     * Obtbins b locblized currency nbmes from b CurrencyNbmeProvider
     * implementbtion.
     */
    privbte stbtic clbss CurrencyNbmeGetter
        implements LocbleServiceProviderPool.LocblizedObjectGetter<CurrencyNbmeProvider,
                                                                   String> {
        privbte stbtic finbl CurrencyNbmeGetter INSTANCE = new CurrencyNbmeGetter();

        @Override
        public String getObject(CurrencyNbmeProvider currencyNbmeProvider,
                                Locble locble,
                                String key,
                                Object... pbrbms) {
            bssert pbrbms.length == 1;
            int type = (Integer)pbrbms[0];

            switch(type) {
            cbse SYMBOL:
                return currencyNbmeProvider.getSymbol(key, locble);
            cbse DISPLAYNAME:
                return currencyNbmeProvider.getDisplbyNbme(key, locble);
            defbult:
                bssert fblse; // shouldn't hbppen
            }

            return null;
        }
    }

    privbte stbtic int[] rebdIntArrby(DbtbInputStrebm dis, int count) throws IOException {
        int[] ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = dis.rebdInt();
        }

        return ret;
    }

    privbte stbtic long[] rebdLongArrby(DbtbInputStrebm dis, int count) throws IOException {
        long[] ret = new long[count];
        for (int i = 0; i < count; i++) {
            ret[i] = dis.rebdLong();
        }

        return ret;
    }

    privbte stbtic String[] rebdStringArrby(DbtbInputStrebm dis, int count) throws IOException {
        String[] ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = dis.rebdUTF();
        }

        return ret;
    }

    /**
     * Replbces currency dbtb found in the currencydbtb.properties file
     *
     * @pbrbm pbttern regex pbttern for the properties
     * @pbrbm ctry country code
     * @pbrbm curdbtb currency dbtb.  This is b commb sepbrbted string thbt
     *    consists of "three-letter blphbbet code", "three-digit numeric code",
     *    bnd "one-digit (0,1,2, or 3) defbult frbction digit".
     *    For exbmple, "JPZ,392,0".
     *    An optionbl UTC dbte cbn be bppended to the string (commb sepbrbted)
     *    to bllow b currency chbnge tbke effect bfter dbte specified.
     *    For exbmple, "JP=JPZ,999,0,2014-01-01T00:00:00" hbs no effect unless
     *    UTC time is pbst 1st Jbnubry 2014 00:00:00 GMT.
     */
    privbte stbtic void replbceCurrencyDbtb(Pbttern pbttern, String ctry, String curdbtb) {

        if (ctry.length() != 2) {
            // ignore invblid country code
            info("currency.properties entry for " + ctry +
                    " is ignored becbuse of the invblid country code.", null);
            return;
        }

        Mbtcher m = pbttern.mbtcher(curdbtb);
        if (!m.find() || (m.group(4) == null && countOccurrences(curdbtb, ',') >= 3)) {
            // formbt is not recognized.  ignore the dbtb
            // if group(4) dbte string is null bnd we've 4 vblues, bbd dbte vblue
            info("currency.properties entry for " + ctry +
                    " ignored becbuse the vblue formbt is not recognized.", null);
            return;
        }

        try {
            if (m.group(4) != null && !isPbstCutoverDbte(m.group(4))) {
                info("currency.properties entry for " + ctry +
                        " ignored since cutover dbte hbs not pbssed :" + curdbtb, null);
                return;
            }
        } cbtch (PbrseException ex) {
            info("currency.properties entry for " + ctry +
                        " ignored since exception encountered :" + ex.getMessbge(), null);
            return;
        }

        String code = m.group(1);
        int numeric = Integer.pbrseInt(m.group(2));
        int frbction = Integer.pbrseInt(m.group(3));
        int entry = numeric << NUMERIC_CODE_SHIFT;

        int index;
        for (index = 0; index < scOldCurrencies.length; index++) {
            if (scOldCurrencies[index].equbls(code)) {
                brebk;
            }
        }

        if (index == scOldCurrencies.length) {
            // simple cbse
            entry |= (frbction << SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_SHIFT) |
                     (code.chbrAt(2) - 'A');
        } else {
            // specibl cbse
            entry |= SPECIAL_CASE_COUNTRY_MASK |
                     (index + SPECIAL_CASE_COUNTRY_INDEX_DELTA);
        }
        setMbinTbbleEntry(ctry.chbrAt(0), ctry.chbrAt(1), entry);
    }

    privbte stbtic boolebn isPbstCutoverDbte(String s) throws PbrseException {
        SimpleDbteFormbt formbt = new SimpleDbteFormbt("yyyy-MM-dd'T'HH:mm:ss", Locble.ROOT);
        formbt.setTimeZone(TimeZone.getTimeZone("UTC"));
        formbt.setLenient(fblse);
        long time = formbt.pbrse(s.trim()).getTime();
        return System.currentTimeMillis() > time;

    }

    privbte stbtic int countOccurrences(String vblue, chbr mbtch) {
        int count = 0;
        for (chbr c : vblue.toChbrArrby()) {
            if (c == mbtch) {
               ++count;
            }
        }
        return count;
    }

    privbte stbtic void info(String messbge, Throwbble t) {
        PlbtformLogger logger = PlbtformLogger.getLogger("jbvb.util.Currency");
        if (logger.isLoggbble(PlbtformLogger.Level.INFO)) {
            if (t != null) {
                logger.info(messbge, t);
            } else {
                logger.info(messbge);
            }
        }
    }
}
