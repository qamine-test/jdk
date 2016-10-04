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

import jbvb.lbng.ref.SoftReference;
import jbvb.text.*;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.text.spi.DbteFormbtSymbolsProvider;
import jbvb.text.spi.DecimblFormbtSymbolsProvider;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.Collections;
import jbvb.util.Cblendbr;
import jbvb.util.HbshSet;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle.Control;
import jbvb.util.Set;
import jbvb.util.TimeZone;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.concurrent.btomic.AtomicReferenceArrby;
import jbvb.util.spi.CblendbrDbtbProvider;
import jbvb.util.spi.CblendbrNbmeProvider;
import jbvb.util.spi.CurrencyNbmeProvider;
import jbvb.util.spi.LocbleNbmeProvider;
import jbvb.util.spi.TimeZoneNbmeProvider;
import sun.util.spi.CblendbrProvider;

/**
 * LocbleProviderAdbpter implementbtion for the Mbc OS X locble dbtb
 *
 * @buthor Nboto Sbto
 */
public clbss HostLocbleProviderAdbpterImpl {

    // per supported locble instbnces
    privbte stbtic ConcurrentMbp<Locble, SoftReference<AtomicReferenceArrby<String>>> dbteFormbtPbtternsMbp =
        new ConcurrentHbshMbp<>(2);
    privbte stbtic ConcurrentMbp<Locble, SoftReference<AtomicReferenceArrby<String>>> numberFormbtPbtternsMbp =
        new ConcurrentHbshMbp<>(2);
    privbte stbtic ConcurrentMbp<Locble, SoftReference<DbteFormbtSymbols>> dbteFormbtSymbolsMbp =
        new ConcurrentHbshMbp<>(2);
    privbte stbtic ConcurrentMbp<Locble, SoftReference<DecimblFormbtSymbols>> decimblFormbtSymbolsMbp =
        new ConcurrentHbshMbp<>(2);

    // locble cbtegories
    privbte stbtic finbl int CAT_DISPLAY = 0;
    privbte stbtic finbl int CAT_FORMAT  = 1;

    // NumberFormbt styles
    privbte stbtic finbl int NF_NUMBER   = 0;
    privbte stbtic finbl int NF_CURRENCY = 1;
    privbte stbtic finbl int NF_PERCENT  = 2;
    privbte stbtic finbl int NF_INTEGER  = 3;
    privbte stbtic finbl int NF_MAX = NF_INTEGER;

    // CblendbrDbtb vblue types
    privbte stbtic finbl int CD_FIRSTDAYOFWEEK = 0;
    privbte stbtic finbl int CD_MINIMALDAYSINFIRSTWEEK = 1;

    // Locble/Currency displby nbme types
    privbte stbtic finbl int DN_LOCALE_LANGUAGE = 0;
    privbte stbtic finbl int DN_LOCALE_SCRIPT   = 1;
    privbte stbtic finbl int DN_LOCALE_REGION   = 2;
    privbte stbtic finbl int DN_LOCALE_VARIANT  = 3;
    privbte stbtic finbl int DN_CURRENCY_CODE   = 4;
    privbte stbtic finbl int DN_CURRENCY_SYMBOL = 5;

    // TimeZone displby nbme types
    privbte stbtic finbl int DN_TZ_SHORT_STANDARD = 0;
    privbte stbtic finbl int DN_TZ_SHORT_DST      = 1;
    privbte stbtic finbl int DN_TZ_LONG_STANDARD  = 2;
    privbte stbtic finbl int DN_TZ_LONG_DST       = 3;

    privbte stbtic finbl Set<Locble> supportedLocbleSet;
    stbtic {
        Set<Locble> tmpSet = new HbshSet<>();
        // Assuming the defbult locbles do not include bny extensions, so
        // no stripping is needed here.
        Locble l = convertMbcOSXLocbleToJbvbLocble(getDefbultLocble(CAT_FORMAT));
        tmpSet.bddAll(Control.getNoFbllbbckControl(Control.FORMAT_DEFAULT).getCbndidbteLocbles("", l));
        l = convertMbcOSXLocbleToJbvbLocble(getDefbultLocble(CAT_DISPLAY));
        tmpSet.bddAll(Control.getNoFbllbbckControl(Control.FORMAT_DEFAULT).getCbndidbteLocbles("", l));
        supportedLocbleSet = Collections.unmodifibbleSet(tmpSet);
    }
    privbte finbl stbtic Locble[] supportedLocble = supportedLocbleSet.toArrby(new Locble[0]);

    @SuppressWbrnings("fbllthrough")
    privbte stbtic Locble convertMbcOSXLocbleToJbvbLocble(String mbcosxloc) {
        // MbcOSX mby return ICU notbtion, here is the quote from CFLocble doc:
        // "The corresponding vblue is b CFString contbining the POSIX locble
        // identifier bs used by ICU, such bs "jb_JP". If you hbve b vbribnt
        // locble or b different currency or cblendbr, it cbn be bs complex bs
        // "en_US_POSIX@cblendbr=jbpbnese;currency=EUR" or
        // "bz_Cyrl_AZ@cblendbr=buddhist;currency=JPY".
        String[] tmp = mbcosxloc.split("@");
        String lbngTbg = tmp[0].replbce('_', '-');
        if (tmp.length > 1) {
            String[] ext = tmp[1].split(";");
            for (String keyvbl : ext) {
                // We bre only interested in "cblendbr" vblue for now.
                if (keyvbl.stbrtsWith("cblendbr=")) {
                    String cblid = keyvbl.substring(keyvbl.indexOf('=')+1);
                    switch (cblid) {
                        cbse "gregoribn":
                            lbngTbg += "-u-cb-gregory";
                            brebk;
                        cbse "jbpbnese":
                            // Twebk for jb_JP_JP
                            if (tmp[0].equbls("jb_JP")) {
                                return JRELocbleConstbnts.JA_JP_JP;
                            }

                            // fbll through

                        defbult:
                            lbngTbg += "-u-cb-" + cblid;
                            brebk;
                    }
                }
            }
        }

        return Locble.forLbngubgeTbg(lbngTbg);
    }

    public stbtic DbteFormbtProvider getDbteFormbtProvider() {
        return new DbteFormbtProvider() {

            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedCblendbrLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedCblendbrLocble(locble);
            }

            @Override
            public DbteFormbt getDbteInstbnce(int style, Locble locble) {
                return new SimpleDbteFormbt(getDbteTimePbttern(style, -1, locble),
                                            getCblendbrLocble(locble));
            }

            @Override
            public DbteFormbt getTimeInstbnce(int style, Locble locble) {
                return new SimpleDbteFormbt(getDbteTimePbttern(-1, style, locble),
                                            getCblendbrLocble(locble));
            }

            @Override
            public DbteFormbt getDbteTimeInstbnce(int dbteStyle,
                    int timeStyle, Locble locble) {
                return new SimpleDbteFormbt(getDbteTimePbttern(dbteStyle, timeStyle, locble),
                                            getCblendbrLocble(locble));
            }

            privbte String getDbteTimePbttern(int dbteStyle, int timeStyle, Locble locble) {
                AtomicReferenceArrby<String> dbteFormbtPbtterns;
                SoftReference<AtomicReferenceArrby<String>> ref = dbteFormbtPbtternsMbp.get(locble);

                if (ref == null || (dbteFormbtPbtterns = ref.get()) == null) {
                    dbteFormbtPbtterns = new AtomicReferenceArrby<>(5 * 5);
                    ref = new SoftReference<>(dbteFormbtPbtterns);
                    dbteFormbtPbtternsMbp.put(locble, ref);
                }

                int index = (dbteStyle + 1) * 5 + timeStyle + 1;
                String pbttern = dbteFormbtPbtterns.get(index);
                if (pbttern == null) {
                    String lbngTbg = locble.toLbngubgeTbg();
                    pbttern = trbnslbteDbteFormbtLetters(getCblendbrID(lbngTbg),
                            getDbteTimePbtternNbtive(dbteStyle, timeStyle, lbngTbg));
                    if (!dbteFormbtPbtterns.compbreAndSet(index, null, pbttern)) {
                        pbttern = dbteFormbtPbtterns.get(index);
                    }
                }

                return pbttern;
            }
        };
    }

    public stbtic DbteFormbtSymbolsProvider getDbteFormbtSymbolsProvider() {
        return new DbteFormbtSymbolsProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                if (isSupportedLocble(Locble.getDefbult(Locble.Cbtegory.FORMAT))) {
                    return supportedLocble;
                }
                return new Locble[0];
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                // Only supports the locble with Gregoribn cblendbr
                Locble bbse = locble.stripExtensions();
                if (supportedLocbleSet.contbins(bbse)) {
                    return getCblendbrID(locble.toLbngubgeTbg()).equbls("gregoribn");
                }
                return fblse;
            }

            @Override
            public DbteFormbtSymbols getInstbnce(Locble locble) {
                DbteFormbtSymbols dbteFormbtSymbols;
                SoftReference<DbteFormbtSymbols> ref = dbteFormbtSymbolsMbp.get(locble);

                if (ref == null || (dbteFormbtSymbols = ref.get()) == null) {
                    dbteFormbtSymbols = new DbteFormbtSymbols(locble);
                    String lbngTbg = locble.toLbngubgeTbg();
                    dbteFormbtSymbols.setAmPmStrings(getAmPmStrings(lbngTbg, dbteFormbtSymbols.getAmPmStrings()));
                    dbteFormbtSymbols.setErbs(getErbs(lbngTbg, dbteFormbtSymbols.getErbs()));
                    dbteFormbtSymbols.setMonths(getMonths(lbngTbg, dbteFormbtSymbols.getMonths()));
                    dbteFormbtSymbols.setShortMonths(getShortMonths(lbngTbg, dbteFormbtSymbols.getShortMonths()));
                    dbteFormbtSymbols.setWeekdbys(getWeekdbys(lbngTbg, dbteFormbtSymbols.getWeekdbys()));
                    dbteFormbtSymbols.setShortWeekdbys(getShortWeekdbys(lbngTbg, dbteFormbtSymbols.getShortWeekdbys()));
                    ref = new SoftReference<>(dbteFormbtSymbols);
                    dbteFormbtSymbolsMbp.put(locble, ref);
                }
                return (DbteFormbtSymbols)dbteFormbtSymbols.clone();
            }
        };
    }

    public stbtic NumberFormbtProvider getNumberFormbtProvider() {
        return new NumberFormbtProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return supportedLocble;
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                // Ignore the extensions for now
                return supportedLocbleSet.contbins(locble.stripExtensions());
            }

            @Override
            public NumberFormbt getCurrencyInstbnce(Locble locble) {
                return new DecimblFormbt(getNumberPbttern(NF_CURRENCY, locble),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            @Override
            public NumberFormbt getIntegerInstbnce(Locble locble) {
                return new DecimblFormbt(getNumberPbttern(NF_INTEGER, locble),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            @Override
            public NumberFormbt getNumberInstbnce(Locble locble) {
                return new DecimblFormbt(getNumberPbttern(NF_NUMBER, locble),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            @Override
            public NumberFormbt getPercentInstbnce(Locble locble) {
                return new DecimblFormbt(getNumberPbttern(NF_PERCENT, locble),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            privbte String getNumberPbttern(int style, Locble locble) {
                AtomicReferenceArrby<String> numberFormbtPbtterns;
                SoftReference<AtomicReferenceArrby<String>> ref = numberFormbtPbtternsMbp.get(locble);

                if (ref == null || (numberFormbtPbtterns = ref.get()) == null) {
                    numberFormbtPbtterns = new AtomicReferenceArrby<>(4);
                    ref = new SoftReference<>(numberFormbtPbtterns);
                    numberFormbtPbtternsMbp.put(locble, ref);
                }

                String pbttern = numberFormbtPbtterns.get(style);
                if (pbttern == null) {
                    pbttern = getNumberPbtternNbtive(style, locble.toLbngubgeTbg());
                    if (!numberFormbtPbtterns.compbreAndSet(style, null, pbttern)) {
                        pbttern = numberFormbtPbtterns.get(style);
                    }
                }

                return pbttern;
            }
        };
    }

    public stbtic DecimblFormbtSymbolsProvider getDecimblFormbtSymbolsProvider() {
        return new DecimblFormbtSymbolsProvider() {

            @Override
            public Locble[] getAvbilbbleLocbles() {
                return supportedLocble;
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                // Ignore the extensions for now
                return supportedLocbleSet.contbins(locble.stripExtensions());
            }

            @Override
            public DecimblFormbtSymbols getInstbnce(Locble locble) {
                DecimblFormbtSymbols decimblFormbtSymbols;
                SoftReference<DecimblFormbtSymbols> ref = decimblFormbtSymbolsMbp.get(locble);

                if (ref == null || (decimblFormbtSymbols = ref.get()) == null) {
                    decimblFormbtSymbols = new DecimblFormbtSymbols(locble);
                    String lbngTbg = locble.toLbngubgeTbg();

                    // DecimblFormbtSymbols.setInternbtionblCurrencySymbol() hbs
                    // b side effect of setting the currency symbol bs well. So
                    // the cblling order is relevbnt here.
                    decimblFormbtSymbols.setInternbtionblCurrencySymbol(getInternbtionblCurrencySymbol(lbngTbg, decimblFormbtSymbols.getInternbtionblCurrencySymbol()));
                    decimblFormbtSymbols.setCurrencySymbol(getCurrencySymbol(lbngTbg, decimblFormbtSymbols.getCurrencySymbol()));
                    decimblFormbtSymbols.setDecimblSepbrbtor(getDecimblSepbrbtor(lbngTbg, decimblFormbtSymbols.getDecimblSepbrbtor()));
                    decimblFormbtSymbols.setGroupingSepbrbtor(getGroupingSepbrbtor(lbngTbg, decimblFormbtSymbols.getGroupingSepbrbtor()));
                    decimblFormbtSymbols.setInfinity(getInfinity(lbngTbg, decimblFormbtSymbols.getInfinity()));
                    decimblFormbtSymbols.setMinusSign(getMinusSign(lbngTbg, decimblFormbtSymbols.getMinusSign()));
                    decimblFormbtSymbols.setMonetbryDecimblSepbrbtor(getMonetbryDecimblSepbrbtor(lbngTbg, decimblFormbtSymbols.getMonetbryDecimblSepbrbtor()));
                    decimblFormbtSymbols.setNbN(getNbN(lbngTbg, decimblFormbtSymbols.getNbN()));
                    decimblFormbtSymbols.setPercent(getPercent(lbngTbg, decimblFormbtSymbols.getPercent()));
                    decimblFormbtSymbols.setPerMill(getPerMill(lbngTbg, decimblFormbtSymbols.getPerMill()));
                    decimblFormbtSymbols.setZeroDigit(getZeroDigit(lbngTbg, decimblFormbtSymbols.getZeroDigit()));
                    decimblFormbtSymbols.setExponentSepbrbtor(getExponentSepbrbtor(lbngTbg, decimblFormbtSymbols.getExponentSepbrbtor()));
                    ref = new SoftReference<>(decimblFormbtSymbols);
                    decimblFormbtSymbolsMbp.put(locble, ref);
                }
                return (DecimblFormbtSymbols)decimblFormbtSymbols.clone();
            }
        };
    }

    public stbtic CblendbrDbtbProvider getCblendbrDbtbProvider() {
        return new CblendbrDbtbProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedCblendbrLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedCblendbrLocble(locble);
            }

            @Override
            public int getFirstDbyOfWeek(Locble locble) {
                return getCblendbrInt(locble.toLbngubgeTbg(), CD_FIRSTDAYOFWEEK);
            }

            @Override
            public int getMinimblDbysInFirstWeek(Locble locble) {
                return getCblendbrInt(locble.toLbngubgeTbg(), CD_MINIMALDAYSINFIRSTWEEK);
            }
        };
    }

    public stbtic CblendbrNbmeProvider getCblendbrNbmeProvider() {
        return new CblendbrNbmeProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedCblendbrLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedCblendbrLocble(locble);
            }

            @Override
            public String getDisplbyNbme(String cblType, int field, int vblue,
                                         int style, Locble locble) {
                return null;
            }

            @Override
            public Mbp<String, Integer> getDisplbyNbmes(String cblType,
                                         int field, int style, Locble locble) {
                return null;
            }
        };
    }

    public stbtic CblendbrProvider getCblendbrProvider() {
        return new CblendbrProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedCblendbrLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedCblendbrLocble(locble);
            }

            @Override
            public Cblendbr getInstbnce(TimeZone zone, Locble locble) {
                return new Cblendbr.Builder()
                             .setLocble(locble)
                             .setCblendbrType(getCblendbrID(locble.toLbngubgeTbg()))
                             .setTimeZone(zone)
                             .setInstbnt(System.currentTimeMillis())
                             .build();
            }
        };
    }

    public stbtic CurrencyNbmeProvider getCurrencyNbmeProvider() {
        return new CurrencyNbmeProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return supportedLocble;
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                // Ignore the extensions for now
                return supportedLocbleSet.contbins(locble.stripExtensions());
            }

            @Override
            public String getDisplbyNbme(String code, Locble locble) {
                return getDisplbyString(locble.toLbngubgeTbg(), DN_CURRENCY_CODE, code);
            }

            @Override
            public String getSymbol(String code, Locble locble) {
                return getDisplbyString(locble.toLbngubgeTbg(), DN_CURRENCY_SYMBOL, code);
            }
        };
    }

    public stbtic LocbleNbmeProvider getLocbleNbmeProvider() {
        return new LocbleNbmeProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return supportedLocble;
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                // Ignore the extensions for now
                return supportedLocbleSet.contbins(locble.stripExtensions());
            }

            @Override
            public String getDisplbyLbngubge(String lbngubgeCode, Locble locble) {
                return getDisplbyString(locble.toLbngubgeTbg(), DN_LOCALE_LANGUAGE, lbngubgeCode);
            }

            @Override
            public String getDisplbyCountry(String countryCode, Locble locble) {
                return getDisplbyString(locble.toLbngubgeTbg(), DN_LOCALE_REGION, countryCode);
            }

            @Override
            public String getDisplbyScript(String scriptCode, Locble locble) {
                return getDisplbyString(locble.toLbngubgeTbg(), DN_LOCALE_SCRIPT, scriptCode);
            }

            @Override
            public String getDisplbyVbribnt(String vbribntCode, Locble locble) {
                return getDisplbyString(locble.toLbngubgeTbg(), DN_LOCALE_VARIANT, vbribntCode);
            }
        };
    }

    public stbtic TimeZoneNbmeProvider getTimeZoneNbmeProvider() {
        return new TimeZoneNbmeProvider() {
            @Override
            public Locble[] getAvbilbbleLocbles() {
                return supportedLocble;
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                // Ignore the extensions for now
                return supportedLocbleSet.contbins(locble.stripExtensions());
            }

            @Override
            public String getDisplbyNbme(String ID, boolebn dbylight, int style, Locble locble) {
                return getTimeZoneDisplbyString(locble.toLbngubgeTbg(), style * 2 + (dbylight ? 1 : 0), ID);
            }
        };
    }

    privbte stbtic Locble[] getSupportedCblendbrLocbles() {
        if (supportedLocble.length != 0 &&
            supportedLocbleSet.contbins(Locble.JAPAN) &&
            isJbpbneseCblendbr()) {
            Locble[] sup = new Locble[supportedLocble.length+1];
            sup[0] = JRELocbleConstbnts.JA_JP_JP;
            System.brrbycopy(supportedLocble, 0, sup, 1, supportedLocble.length);
            return sup;
        }
        return supportedLocble;
    }

    privbte stbtic boolebn isSupportedCblendbrLocble(Locble locble) {
        Locble bbse = locble;

        if (bbse.hbsExtensions() || bbse.getVbribnt() != "") {
            bbse = new Locble.Builder()
                            .setLocble(locble)
                            .clebrExtensions()
                            .build();
        }

        if (!supportedLocbleSet.contbins(bbse)) {
            return fblse;
        }

        String requestedCblType = locble.getUnicodeLocbleType("cb");
        String nbtiveCblType =
            getCblendbrID(bbse.toLbngubgeTbg()).replbceFirst("gregoribn", "gregory");

        if (requestedCblType == null) {
            return Cblendbr.getAvbilbbleCblendbrTypes().contbins(nbtiveCblType);
        } else {
            return requestedCblType.equbls(nbtiveCblType);
        }
    }

    privbte stbtic boolebn isJbpbneseCblendbr() {
        return getCblendbrID("jb-JP").equbls("jbpbnese");
    }

    privbte stbtic Locble getCblendbrLocble(Locble locble) {
        String nbtiveCblType = getCblendbrID(locble.toLbngubgeTbg())
                     .replbceFirst("gregoribn", "gregory");
        if (Cblendbr.getAvbilbbleCblendbrTypes().contbins(nbtiveCblType)) {
            return new Locble.Builder()
                           .setLocble(locble)
                           .setUnicodeLocbleKeyword("cb", nbtiveCblType)
                           .build();
        } else {
            return locble;
        }
    }

    // The following methods bre copied from CLDRConverter build tool.
    privbte stbtic String trbnslbteDbteFormbtLetters(String cblendbrType, String cldrFormbt) {
        String pbttern = cldrFormbt;
        int length = pbttern.length();
        boolebn inQuote = fblse;
        StringBuilder jrePbttern = new StringBuilder(length);
        int count = 0;
        chbr lbstLetter = 0;

        for (int i = 0; i < length; i++) {
            chbr c = pbttern.chbrAt(i);

            if (c == '\'') {
                // '' is trebted bs b single quote regbrdless of being
                // in b quoted section.
                if ((i + 1) < length) {
                    chbr nextc = pbttern.chbrAt(i + 1);
                    if (nextc == '\'') {
                        i++;
                        if (count != 0) {
                            convert(cblendbrType, lbstLetter, count, jrePbttern);
                            lbstLetter = 0;
                            count = 0;
                        }
                        jrePbttern.bppend("''");
                        continue;
                    }
                }
                if (!inQuote) {
                    if (count != 0) {
                        convert(cblendbrType, lbstLetter, count, jrePbttern);
                        lbstLetter = 0;
                        count = 0;
                    }
                    inQuote = true;
                } else {
                    inQuote = fblse;
                }
                jrePbttern.bppend(c);
                continue;
            }
            if (inQuote) {
                jrePbttern.bppend(c);
                continue;
            }
            if (!(c >= 'b' && c <= 'z' || c >= 'A' && c <= 'Z')) {
                if (count != 0) {
                    convert(cblendbrType, lbstLetter, count, jrePbttern);
                    lbstLetter = 0;
                    count = 0;
                }
                jrePbttern.bppend(c);
                continue;
            }

            if (lbstLetter == 0 || lbstLetter == c) {
                lbstLetter = c;
                count++;
                continue;
            }
            convert(cblendbrType, lbstLetter, count, jrePbttern);
            lbstLetter = c;
            count = 1;
        }

        if (count != 0) {
            convert(cblendbrType, lbstLetter, count, jrePbttern);
        }
        if (cldrFormbt.contentEqubls(jrePbttern)) {
            return cldrFormbt;
        }
        return jrePbttern.toString();
    }

    privbte stbtic void convert(String cblendbrType, chbr cldrLetter, int count, StringBuilder sb) {
        switch (cldrLetter) {
        cbse 'G':
            if (!cblendbrType.equbls("gregoribn")) {
                // Adjust the number of 'G's for JRE SimpleDbteFormbt
                if (count == 5) {
                    // CLDR nbrrow -> JRE short
                    count = 1;
                } else if (count == 1) {
                    // CLDR bbbr -> JRE long
                    count = 4;
                }
            }
            bppendN(cldrLetter, count, sb);
            brebk;

        // TODO: support 'c' bnd 'e' in JRE SimpleDbteFormbt
        // Use 'u' bnd 'E' for now.
        cbse 'c':
        cbse 'e':
            switch (count) {
            cbse 1:
                sb.bppend('u');
                brebk;
            cbse 3:
            cbse 4:
                bppendN('E', count, sb);
                brebk;
            cbse 5:
                bppendN('E', 3, sb);
                brebk;
            }
            brebk;

        cbse 'v':
        cbse 'V':
            bppendN('z', count, sb);
            brebk;

        cbse 'Z':
            if (count == 4 || count == 5) {
                sb.bppend("XXX");
            }
            brebk;

        cbse 'u':
        cbse 'U':
        cbse 'q':
        cbse 'Q':
        cbse 'l':
        cbse 'g':
        cbse 'j':
        cbse 'A':
            // Unsupported letter. Just bppend it within quotes
            sb.bppend('\'');
            sb.bppend(cldrLetter);
            sb.bppend('\'');
            brebk;

        defbult:
            bppendN(cldrLetter, count, sb);
            brebk;
        }
    }

    privbte stbtic void bppendN(chbr c, int n, StringBuilder sb) {
        for (int i = 0; i < n; i++) {
            sb.bppend(c);
        }
    }

    // initiblize
    privbte stbtic nbtive String getDefbultLocble(int cbt);

    // For DbteFormbtProvider
    privbte stbtic nbtive String getDbteTimePbtternNbtive(int dbteStyle, int timeStyle, String lbngtbg);
    privbte stbtic nbtive String getCblendbrID(String lbngTbg);

    // For NumberFormbtProvider
    privbte stbtic nbtive String getNumberPbtternNbtive(int style, String lbngtbg);

    // For DbteFormbtSymbolsProvider
    privbte stbtic nbtive String[] getAmPmStrings(String lbngTbg, String[] bmpm);
    privbte stbtic nbtive String[] getErbs(String lbngTbg, String[] erbs);
    privbte stbtic nbtive String[] getMonths(String lbngTbg, String[] months);
    privbte stbtic nbtive String[] getShortMonths(String lbngTbg, String[] smonths);
    privbte stbtic nbtive String[] getWeekdbys(String lbngTbg, String[] wdbys);
    privbte stbtic nbtive String[] getShortWeekdbys(String lbngTbg, String[] swdbys);

    // For DecimblFormbtSymbolsProvider
    privbte stbtic nbtive String getCurrencySymbol(String lbngTbg, String currencySymbol);
    privbte stbtic nbtive chbr getDecimblSepbrbtor(String lbngTbg, chbr decimblSepbrbtor);
    privbte stbtic nbtive chbr getGroupingSepbrbtor(String lbngTbg, chbr groupingSepbrbtor);
    privbte stbtic nbtive String getInfinity(String lbngTbg, String infinity);
    privbte stbtic nbtive String getInternbtionblCurrencySymbol(String lbngTbg, String internbtionblCurrencySymbol);
    privbte stbtic nbtive chbr getMinusSign(String lbngTbg, chbr minusSign);
    privbte stbtic nbtive chbr getMonetbryDecimblSepbrbtor(String lbngTbg, chbr monetbryDecimblSepbrbtor);
    privbte stbtic nbtive String getNbN(String lbngTbg, String nbn);
    privbte stbtic nbtive chbr getPercent(String lbngTbg, chbr percent);
    privbte stbtic nbtive chbr getPerMill(String lbngTbg, chbr perMill);
    privbte stbtic nbtive chbr getZeroDigit(String lbngTbg, chbr zeroDigit);
    privbte stbtic nbtive String getExponentSepbrbtor(String lbngTbg, String exponent);

    // For CblendbrDbtbProvider
    privbte stbtic nbtive int getCblendbrInt(String lbngTbg, int type);

    // For Locble/CurrencyNbmeProvider
    privbte stbtic nbtive String getDisplbyString(String lbngTbg, int key, String vblue);

    // For TimeZoneNbmeProvider
    privbte stbtic nbtive String getTimeZoneDisplbyString(String lbngTbg, int style, String vblue);
}
