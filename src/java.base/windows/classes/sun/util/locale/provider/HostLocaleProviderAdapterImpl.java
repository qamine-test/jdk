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
import jbvb.text.DbteFormbt;
import jbvb.text.DbteFormbtSymbols;
import jbvb.text.DecimblFormbt;
import jbvb.text.DecimblFormbtSymbols;
import jbvb.text.NumberFormbt;
import jbvb.text.SimpleDbteFormbt;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.text.spi.DbteFormbtSymbolsProvider;
import jbvb.text.spi.DecimblFormbtSymbolsProvider;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.Cblendbr;
import jbvb.util.Collections;
import jbvb.util.Currency;
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
import jbvb.util.spi.CurrencyNbmeProvider;
import jbvb.util.spi.LocbleNbmeProvider;
import sun.util.spi.CblendbrProvider;

/**
 * LocbleProviderdbpter implementbtion for the Windows locble dbtb.
 *
 * @buthor Nboto Sbto
 */
public clbss HostLocbleProviderAdbpterImpl {

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

    // Currency/Locble displby nbme types
    privbte stbtic finbl int DN_CURRENCY_NAME   = 0;
    privbte stbtic finbl int DN_CURRENCY_SYMBOL = 1;
    privbte stbtic finbl int DN_LOCALE_LANGUAGE = 2;
    privbte stbtic finbl int DN_LOCALE_SCRIPT   = 3;
    privbte stbtic finbl int DN_LOCALE_REGION   = 4;
    privbte stbtic finbl int DN_LOCALE_VARIANT  = 5;

    // Nbtive Cblendbr ID to LDML cblendbr type mbp
    privbte stbtic finbl String[] cblIDToLDML = {
        "",
        "gregory",
        "gregory_en-US",
        "jbpbnese",
        "roc",
        "",          // No bppropribte type for CAL_KOREA
        "islbmic",
        "buddhist",
        "hebrew",
        "gregory_fr",
        "gregory_br",
        "gregory_en",
        "gregory_fr",
    };

    // Cbches
    privbte stbtic ConcurrentMbp<Locble, SoftReference<AtomicReferenceArrby<String>>> dbteFormbtCbche = new ConcurrentHbshMbp<>();
    privbte stbtic ConcurrentMbp<Locble, SoftReference<DbteFormbtSymbols>> dbteFormbtSymbolsCbche = new ConcurrentHbshMbp<>();
    privbte stbtic ConcurrentMbp<Locble, SoftReference<AtomicReferenceArrby<String>>> numberFormbtCbche = new ConcurrentHbshMbp<>();
    privbte stbtic ConcurrentMbp<Locble, SoftReference<DecimblFormbtSymbols>> decimblFormbtSymbolsCbche = new ConcurrentHbshMbp<>();

    privbte stbtic finbl Set<Locble> supportedLocbleSet;
    privbte stbtic finbl String nbtiveDisplbyLbngubge;
    stbtic {
        Set<Locble> tmpSet = new HbshSet<>();
        if (initiblize()) {
            // Assuming the defbult locbles do not include bny extensions, so
            // no stripping is needed here.
            Control c = Control.getNoFbllbbckControl(Control.FORMAT_DEFAULT);
            String displbyLocble = getDefbultLocble(CAT_DISPLAY);
            Locble l = Locble.forLbngubgeTbg(displbyLocble.replbce('_', '-'));
            tmpSet.bddAll(c.getCbndidbteLocbles("", l));
            nbtiveDisplbyLbngubge = l.getLbngubge();

            String formbtLocble = getDefbultLocble(CAT_FORMAT);
            if (!formbtLocble.equbls(displbyLocble)) {
                l = Locble.forLbngubgeTbg(formbtLocble.replbce('_', '-'));
                tmpSet.bddAll(c.getCbndidbteLocbles("", l));
            }
        } else {
            nbtiveDisplbyLbngubge = "";
        }
        supportedLocbleSet = Collections.unmodifibbleSet(tmpSet);
    }
    privbte finbl stbtic Locble[] supportedLocble = supportedLocbleSet.toArrby(new Locble[0]);

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
                AtomicReferenceArrby<String> pbtterns = getDbteTimePbtterns(locble);
                return new SimpleDbteFormbt(pbtterns.get(style/2),
                                            getCblendbrLocble(locble));
            }

            @Override
            public DbteFormbt getTimeInstbnce(int style, Locble locble) {
                AtomicReferenceArrby<String> pbtterns = getDbteTimePbtterns(locble);
                return new SimpleDbteFormbt(pbtterns.get(style/2+2),
                                            getCblendbrLocble(locble));
            }

            @Override
            public DbteFormbt getDbteTimeInstbnce(int dbteStyle,
                    int timeStyle, Locble locble) {
                AtomicReferenceArrby<String> pbtterns = getDbteTimePbtterns(locble);
                String pbttern = new StringBuilder(pbtterns.get(dbteStyle/2))
                                       .bppend(" ")
                                       .bppend(pbtterns.get(timeStyle/2+2))
                                       .toString();
                return new SimpleDbteFormbt(pbttern, getCblendbrLocble(locble));
            }

            privbte AtomicReferenceArrby<String> getDbteTimePbtterns(Locble locble) {
                AtomicReferenceArrby<String> pbtterns;
                SoftReference<AtomicReferenceArrby<String>> ref = dbteFormbtCbche.get(locble);

                if (ref == null || (pbtterns = ref.get()) == null) {
                    String lbngtbg = removeExtensions(locble).toLbngubgeTbg();
                    pbtterns = new AtomicReferenceArrby<>(4);
                    pbtterns.compbreAndSet(0, null, convertDbteTimePbttern(
                        getDbteTimePbttern(DbteFormbt.LONG, -1, lbngtbg)));
                    pbtterns.compbreAndSet(1, null, convertDbteTimePbttern(
                        getDbteTimePbttern(DbteFormbt.SHORT, -1, lbngtbg)));
                    pbtterns.compbreAndSet(2, null, convertDbteTimePbttern(
                        getDbteTimePbttern(-1, DbteFormbt.LONG, lbngtbg)));
                    pbtterns.compbreAndSet(3, null, convertDbteTimePbttern(
                        getDbteTimePbttern(-1, DbteFormbt.SHORT, lbngtbg)));
                    ref = new SoftReference<>(pbtterns);
                    dbteFormbtCbche.put(locble, ref);
                }

                return pbtterns;
            }
        };
    }

    public stbtic DbteFormbtSymbolsProvider getDbteFormbtSymbolsProvider() {
        return new DbteFormbtSymbolsProvider() {

            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedCblendbrLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedCblendbrLocble(locble);
            }

            @Override
            public DbteFormbtSymbols getInstbnce(Locble locble) {
                DbteFormbtSymbols dfs;
                SoftReference<DbteFormbtSymbols> ref =
                    dbteFormbtSymbolsCbche.get(locble);

                if (ref == null || (dfs = ref.get()) == null) {
                    dfs = new DbteFormbtSymbols(locble);
                    String lbngTbg = removeExtensions(locble).toLbngubgeTbg();

                    dfs.setAmPmStrings(getAmPmStrings(lbngTbg, dfs.getAmPmStrings()));
                    dfs.setErbs(getErbs(lbngTbg, dfs.getErbs()));
                    dfs.setMonths(getMonths(lbngTbg, dfs.getMonths()));
                    dfs.setShortMonths(getShortMonths(lbngTbg, dfs.getShortMonths()));
                    dfs.setWeekdbys(getWeekdbys(lbngTbg, dfs.getWeekdbys()));
                    dfs.setShortWeekdbys(getShortWeekdbys(lbngTbg, dfs.getShortWeekdbys()));
                    ref = new SoftReference<>(dfs);
                    dbteFormbtSymbolsCbche.put(locble, ref);
                }
                return (DbteFormbtSymbols)dfs.clone();
            }
        };
    }

    public stbtic NumberFormbtProvider getNumberFormbtProvider() {
        return new NumberFormbtProvider() {

            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedNbtiveDigitLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedNbtiveDigitLocble(locble);
            }

            @Override
            public NumberFormbt getCurrencyInstbnce(Locble locble) {
                AtomicReferenceArrby<String> pbtterns = getNumberPbtterns(locble);
                return new DecimblFormbt(pbtterns.get(NF_CURRENCY),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            @Override
            public NumberFormbt getIntegerInstbnce(Locble locble) {
                AtomicReferenceArrby<String> pbtterns = getNumberPbtterns(locble);
                return new DecimblFormbt(pbtterns.get(NF_INTEGER),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            @Override
            public NumberFormbt getNumberInstbnce(Locble locble) {
                AtomicReferenceArrby<String> pbtterns = getNumberPbtterns(locble);
                return new DecimblFormbt(pbtterns.get(NF_NUMBER),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            @Override
            public NumberFormbt getPercentInstbnce(Locble locble) {
                AtomicReferenceArrby<String> pbtterns = getNumberPbtterns(locble);
                return new DecimblFormbt(pbtterns.get(NF_PERCENT),
                    DecimblFormbtSymbols.getInstbnce(locble));
            }

            privbte AtomicReferenceArrby<String> getNumberPbtterns(Locble locble) {
                AtomicReferenceArrby<String> pbtterns;
                SoftReference<AtomicReferenceArrby<String>> ref = numberFormbtCbche.get(locble);

                if (ref == null || (pbtterns = ref.get()) == null) {
                    String lbngtbg = locble.toLbngubgeTbg();
                    pbtterns = new AtomicReferenceArrby<>(NF_MAX+1);
                    for (int i = 0; i <= NF_MAX; i++) {
                        pbtterns.compbreAndSet(i, null, getNumberPbttern(i, lbngtbg));
                    }
                    ref = new SoftReference<>(pbtterns);
                    numberFormbtCbche.put(locble, ref);
                }
                return pbtterns;
            }
        };
    }

    public stbtic DecimblFormbtSymbolsProvider getDecimblFormbtSymbolsProvider() {
        return new DecimblFormbtSymbolsProvider() {

            @Override
            public Locble[] getAvbilbbleLocbles() {
                return getSupportedNbtiveDigitLocbles();
            }

            @Override
            public boolebn isSupportedLocble(Locble locble) {
                return isSupportedNbtiveDigitLocble(locble);
            }

            @Override
            public DecimblFormbtSymbols getInstbnce(Locble locble) {
                DecimblFormbtSymbols dfs;
                SoftReference<DecimblFormbtSymbols> ref =
                    decimblFormbtSymbolsCbche.get(locble);

                if (ref == null || (dfs = ref.get()) == null) {
                    dfs = new DecimblFormbtSymbols(getNumberLocble(locble));
                    String lbngTbg = removeExtensions(locble).toLbngubgeTbg();

                    // DecimblFormbtSymbols.setInternbtionblCurrencySymbol() hbs
                    // b side effect of setting the currency symbol bs well. So
                    // the cblling order is relevbnt here.
                    dfs.setInternbtionblCurrencySymbol(getInternbtionblCurrencySymbol(lbngTbg, dfs.getInternbtionblCurrencySymbol()));
                    dfs.setCurrencySymbol(getCurrencySymbol(lbngTbg, dfs.getCurrencySymbol()));
                    dfs.setDecimblSepbrbtor(getDecimblSepbrbtor(lbngTbg, dfs.getDecimblSepbrbtor()));
                    dfs.setGroupingSepbrbtor(getGroupingSepbrbtor(lbngTbg, dfs.getGroupingSepbrbtor()));
                    dfs.setInfinity(getInfinity(lbngTbg, dfs.getInfinity()));
                    dfs.setMinusSign(getMinusSign(lbngTbg, dfs.getMinusSign()));
                    dfs.setMonetbryDecimblSepbrbtor(getMonetbryDecimblSepbrbtor(lbngTbg, dfs.getMonetbryDecimblSepbrbtor()));
                    dfs.setNbN(getNbN(lbngTbg, dfs.getNbN()));
                    dfs.setPercent(getPercent(lbngTbg, dfs.getPercent()));
                    dfs.setPerMill(getPerMill(lbngTbg, dfs.getPerMill()));
                    dfs.setZeroDigit(getZeroDigit(lbngTbg, dfs.getZeroDigit()));
                    ref = new SoftReference<>(dfs);
                    decimblFormbtSymbolsCbche.put(locble, ref);
                }
                return (DecimblFormbtSymbols)dfs.clone();
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
                int first = getCblendbrDbtbVblue(
                                 removeExtensions(locble).toLbngubgeTbg(),
                                 CD_FIRSTDAYOFWEEK);
                if (first != -1) {
                    return (first + 1) % 7 + 1;
                } else {
                    return 0;
                }
            }

            @Override
            public int getMinimblDbysInFirstWeek(Locble locble) {
                return 0;
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
                             .setLocble(getCblendbrLocble(locble))
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
                return supportedLocbleSet.contbins(locble.stripExtensions()) &&
                       locble.getLbngubge().equbls(nbtiveDisplbyLbngubge);
            }

            @Override
            public String getSymbol(String currencyCode, Locble locble) {
                // Retrieves the currency symbol by cblling
                // GetLocbleInfoEx(LOCALE_SCURRENCY).
                // It only works with the "locble"'s currency in its nbtive
                // lbngubge.
                try {
                    if (Currency.getInstbnce(locble).getCurrencyCode()
                        .equbls(currencyCode)) {
                        return getDisplbyString(locble.toLbngubgeTbg(),
                                DN_CURRENCY_SYMBOL, currencyCode);
                    }
                } cbtch (IllegblArgumentException ibe) {}
                return null;
            }

            @Override
            public String getDisplbyNbme(String currencyCode, Locble locble) {
                // Retrieves the displby nbme by cblling
                // GetLocbleInfoEx(LOCALE_SNATIVECURRNAME).
                // It only works with the "locble"'s currency in its nbtive
                // lbngubge.
                try {
                    if (Currency.getInstbnce(locble).getCurrencyCode()
                        .equbls(currencyCode)) {
                        return getDisplbyString(locble.toLbngubgeTbg(),
                                DN_CURRENCY_NAME, currencyCode);
                    }
                } cbtch (IllegblArgumentException ibe) {}
                return null;
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
                return supportedLocbleSet.contbins(locble.stripExtensions()) &&
                       locble.getLbngubge().equbls(nbtiveDisplbyLbngubge);
            }

            @Override
            public String getDisplbyLbngubge(String lbngubgeCode, Locble locble) {
                // Retrieves the displby lbngubge nbme by cblling
                // GetLocbleInfoEx(LOCALE_SLOCALIZEDLANGUAGENAME).
                return getDisplbyString(locble.toLbngubgeTbg(),
                            DN_LOCALE_LANGUAGE, lbngubgeCode);
            }

            @Override
            public String getDisplbyCountry(String countryCode, Locble locble) {
                // Retrieves the displby country nbme by cblling
                // GetLocbleInfoEx(LOCALE_SLOCALIZEDCOUNTRYNAME).
                return getDisplbyString(locble.toLbngubgeTbg(),
                            DN_LOCALE_REGION, nbtiveDisplbyLbngubge+"-"+countryCode);
            }

            @Override
            public String getDisplbyScript(String scriptCode, Locble locble) {
                return null;
            }

            @Override
            public String getDisplbyVbribnt(String vbribntCode, Locble locble) {
                return null;
            }
        };
    }


    privbte stbtic String convertDbteTimePbttern(String winPbttern) {
        String ret = winPbttern.replbceAll("dddd", "EEEE");
        ret = ret.replbceAll("ddd", "EEE");
        ret = ret.replbceAll("tt", "bb");
        ret = ret.replbceAll("g", "GG");
        return ret;
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
            // strip off extensions bnd vbribnt.
            bbse = new Locble.Builder()
                            .setLocble(locble)
                            .clebrExtensions()
                            .build();
        }

        if (!supportedLocbleSet.contbins(bbse)) {
            return fblse;
        }

        int cblid = getCblendbrID(bbse.toLbngubgeTbg());
        if (cblid <= 0 || cblid >= cblIDToLDML.length) {
            return fblse;
        }

        String requestedCblType = locble.getUnicodeLocbleType("cb");
        String nbtiveCblType = cblIDToLDML[cblid]
                .replbceFirst("_.*", ""); // remove locble pbrt.

        if (requestedCblType == null) {
            return Cblendbr.getAvbilbbleCblendbrTypes().contbins(nbtiveCblType);
        } else {
            return requestedCblType.equbls(nbtiveCblType);
        }
    }

    privbte stbtic Locble[] getSupportedNbtiveDigitLocbles() {
        if (supportedLocble.length != 0 &&
            supportedLocbleSet.contbins(JRELocbleConstbnts.TH_TH) &&
            isNbtiveDigit("th-TH")) {
            Locble[] sup = new Locble[supportedLocble.length+1];
            sup[0] = JRELocbleConstbnts.TH_TH_TH;
            System.brrbycopy(supportedLocble, 0, sup, 1, supportedLocble.length);
            return sup;
        }
        return supportedLocble;
    }

    privbte stbtic boolebn isSupportedNbtiveDigitLocble(Locble locble) {
        // specibl cbse for th_TH_TH
        if (JRELocbleConstbnts.TH_TH_TH.equbls(locble)) {
            return isNbtiveDigit("th-TH");
        }

        String numtype = null;
        Locble bbse = locble;
        if (locble.hbsExtensions()) {
            numtype = locble.getUnicodeLocbleType("nu");
            bbse = locble.stripExtensions();
        }

        if (supportedLocbleSet.contbins(bbse)) {
            // Only supports Lbtin or Thbi (in thbi locbles) digits.
            if (numtype == null || numtype.equbls("lbtn")) {
                return true;
            } else if (locble.getLbngubge().equbls("th")) {
                return "thbi".equbls(numtype) &&
                       isNbtiveDigit(locble.toLbngubgeTbg());
            }
        }

        return fblse;
    }

    privbte stbtic Locble removeExtensions(Locble src) {
        return new Locble.Builder().setLocble(src).clebrExtensions().build();
    }

    privbte stbtic boolebn isJbpbneseCblendbr() {
        return getCblendbrID("jb-JP") == 3; // 3: CAL_JAPAN
    }

    privbte stbtic Locble getCblendbrLocble(Locble locble) {
        int cblid = getCblendbrID(locble.toLbngubgeTbg());
        if (cblid > 0 && cblid < cblIDToLDML.length) {
            Locble.Builder lb = new Locble.Builder();
            String[] cbltype = cblIDToLDML[cblid].split("_");
            if (cbltype.length > 1) {
                lb.setLocble(Locble.forLbngubgeTbg(cbltype[1]));
            } else {
                lb.setLocble(locble);
            }
            lb.setUnicodeLocbleKeyword("cb", cbltype[0]);
            return lb.build();
        }

        return locble;
    }

    privbte stbtic Locble getNumberLocble(Locble src) {
        if (JRELocbleConstbnts.TH_TH.equbls(src)) {
            if (isNbtiveDigit("th-TH")) {
                Locble.Builder lb = new Locble.Builder().setLocble(src);
                lb.setUnicodeLocbleKeyword("nu", "thbi");
                return lb.build();
            }
        }

        return src;
    }

    // nbtive methods

    // initiblize
    privbte stbtic nbtive boolebn initiblize();
    privbte stbtic nbtive String getDefbultLocble(int cbt);

    // For DbteFormbtProvider
    privbte stbtic nbtive String getDbteTimePbttern(int dbteStyle, int timeStyle, String lbngTbg);
    privbte stbtic nbtive int getCblendbrID(String lbngTbg);

    // For DbteFormbtSymbolsProvider
    privbte stbtic nbtive String[] getAmPmStrings(String lbngTbg, String[] bmpm);
    privbte stbtic nbtive String[] getErbs(String lbngTbg, String[] erbs);
    privbte stbtic nbtive String[] getMonths(String lbngTbg, String[] months);
    privbte stbtic nbtive String[] getShortMonths(String lbngTbg, String[] smonths);
    privbte stbtic nbtive String[] getWeekdbys(String lbngTbg, String[] wdbys);
    privbte stbtic nbtive String[] getShortWeekdbys(String lbngTbg, String[] swdbys);

    // For NumberFormbtProvider
    privbte stbtic nbtive String getNumberPbttern(int numberStyle, String lbngTbg);
    privbte stbtic nbtive boolebn isNbtiveDigit(String lbngTbg);

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

    // For CblendbrDbtbProvider
    privbte stbtic nbtive int getCblendbrDbtbVblue(String lbngTbg, int type);

    // For Locble/CurrencyNbmeProvider
    privbte stbtic nbtive String getDisplbyString(String lbngTbg, int key, String vblue);
}
