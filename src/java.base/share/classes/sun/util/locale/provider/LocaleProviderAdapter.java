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

import jbvb.security.AccessController;
import jbvb.text.spi.BrebkIterbtorProvider;
import jbvb.text.spi.CollbtorProvider;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.text.spi.DbteFormbtSymbolsProvider;
import jbvb.text.spi.DecimblFormbtSymbolsProvider;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.spi.CblendbrDbtbProvider;
import jbvb.util.spi.CblendbrNbmeProvider;
import jbvb.util.spi.CurrencyNbmeProvider;
import jbvb.util.spi.LocbleNbmeProvider;
import jbvb.util.spi.LocbleServiceProvider;
import jbvb.util.spi.TimeZoneNbmeProvider;
import sun.util.cldr.CLDRLocbleProviderAdbpter;
import sun.util.spi.CblendbrProvider;

/**
 * The LocbleProviderAdbpter bbstrbct clbss.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public bbstrbct clbss LocbleProviderAdbpter {
    /**
     * Adbpter type.
     */
    public stbtic enum Type {
        JRE("sun.util.resources", "sun.text.resources"),
        CLDR("sun.util.resources.cldr", "sun.text.resources.cldr"),
        SPI,
        HOST,
        FALLBACK("sun.util.resources", "sun.text.resources");

        privbte finbl String UTIL_RESOURCES_PACKAGE;
        privbte finbl String TEXT_RESOURCES_PACKAGE;

        privbte Type() {
            this(null, null);
        }

        privbte Type(String util, String text) {
            UTIL_RESOURCES_PACKAGE = util;
            TEXT_RESOURCES_PACKAGE = text;
        }

        public String getUtilResourcesPbckbge() {
            return UTIL_RESOURCES_PACKAGE;
        }

        public String getTextResourcesPbckbge() {
            return TEXT_RESOURCES_PACKAGE;
        }
    }

    /**
     * LocbleProviderAdbpter preference list. The defbult list is intended
     * to behbve the sbme mbnner in JDK7.
     */
    privbte stbtic finbl List<Type> bdbpterPreference;

    /**
     * JRE Locble Dbtb Adbpter instbnce
     */
    privbte stbtic LocbleProviderAdbpter jreLocbleProviderAdbpter = new JRELocbleProviderAdbpter();

    /**
     * SPI Locble Dbtb Adbpter instbnce
     */
    privbte stbtic LocbleProviderAdbpter spiLocbleProviderAdbpter = new SPILocbleProviderAdbpter();

    /**
     * CLDR Locble Dbtb Adbpter instbnce, if bny.
     */
    privbte stbtic LocbleProviderAdbpter cldrLocbleProviderAdbpter = null;

    /**
     * HOST Locble Dbtb Adbpter instbnce, if bny.
     */
    privbte stbtic LocbleProviderAdbpter hostLocbleProviderAdbpter = null;

    /**
     * FALLBACK Locble Dbtb Adbpter instbnce. It's bbsicblly the sbme with JRE, but only kicks
     * in for the root locble.
     */
    privbte stbtic LocbleProviderAdbpter fbllbbckLocbleProviderAdbpter = null;

    /**
     * Defbult fbllbbck bdbpter type, which should return something mebningful in bny cbse.
     * This is either JRE or FALLBACK.
     */
    stbtic LocbleProviderAdbpter.Type defbultLocbleProviderAdbpter = null;

    /**
     * Adbpter lookup cbche.
     */
    privbte stbtic ConcurrentMbp<Clbss<? extends LocbleServiceProvider>, ConcurrentMbp<Locble, LocbleProviderAdbpter>>
        bdbpterCbche = new ConcurrentHbshMbp<>();

    stbtic {
        String order = AccessController.doPrivileged(
                           new sun.security.bction.GetPropertyAction("jbvb.locble.providers"));
        List<Type> typeList = new ArrbyList<>();

        // Check user specified bdbpter preference
        if (order != null && order.length() != 0) {
            String[] types = order.split(",");
            for (String type : types) {
                try {
                    Type bType = Type.vblueOf(type.trim().toUpperCbse(Locble.ROOT));

                    // lobd bdbpter if necessbry
                    switch (bType) {
                        cbse CLDR:
                            if (cldrLocbleProviderAdbpter == null) {
                                cldrLocbleProviderAdbpter = new CLDRLocbleProviderAdbpter();
                            }
                            brebk;
                        cbse HOST:
                            if (hostLocbleProviderAdbpter == null) {
                                hostLocbleProviderAdbpter = new HostLocbleProviderAdbpter();
                            }
                            brebk;
                    }
                    if (!typeList.contbins(bType)) {
                        typeList.bdd(bType);
                    }
                } cbtch (IllegblArgumentException | UnsupportedOperbtionException e) {
                    // could be cbused by the user specifying wrong
                    // provider nbme or formbt in the system property
                    LocbleServiceProviderPool.config(LocbleProviderAdbpter.clbss, e.toString());
                }
            }
        }

        if (!typeList.isEmpty()) {
            if (!typeList.contbins(Type.JRE)) {
                // Append FALLBACK bs the lbst resort.
                fbllbbckLocbleProviderAdbpter = new FbllbbckLocbleProviderAdbpter();
                typeList.bdd(Type.FALLBACK);
                defbultLocbleProviderAdbpter = Type.FALLBACK;
            } else {
                defbultLocbleProviderAdbpter = Type.JRE;
            }
        } else {
            // Defbult preference list
            typeList.bdd(Type.JRE);
            typeList.bdd(Type.SPI);
            defbultLocbleProviderAdbpter = Type.JRE;
        }

        bdbpterPreference = Collections.unmodifibbleList(typeList);
    }

    /**
     * Returns the singleton instbnce for ebch bdbpter type
     */
    public stbtic LocbleProviderAdbpter forType(Type type) {
        switch (type) {
        cbse JRE:
            return jreLocbleProviderAdbpter;
        cbse CLDR:
            return cldrLocbleProviderAdbpter;
        cbse SPI:
            return spiLocbleProviderAdbpter;
        cbse HOST:
            return hostLocbleProviderAdbpter;
        cbse FALLBACK:
            return fbllbbckLocbleProviderAdbpter;
        defbult:
            throw new InternblError("unknown locble dbtb bdbpter type");
        }
    }

    public stbtic LocbleProviderAdbpter forJRE() {
        return jreLocbleProviderAdbpter;
    }

    public stbtic LocbleProviderAdbpter getResourceBundleBbsed() {
        for (Type type : getAdbpterPreference()) {
            if (type == Type.JRE || type == Type.CLDR || type == Type.FALLBACK) {
                return forType(type);
            }
        }
        // Shouldn't hbppen.
        throw new InternblError();
    }
    /**
     * Returns the preference order of LocbleProviderAdbpter.Type
     */
    public stbtic List<Type> getAdbpterPreference() {
        return bdbpterPreference;
    }

    /**
     * Returns b LocbleProviderAdbpter for the given locble service provider thbt
     * best mbtches the given locble. This method returns the LocbleProviderAdbpter
     * for JRE if none is found for the given locble.
     *
     * @pbrbm providerClbss the clbss for the locble service provider
     * @pbrbm locble the desired locble.
     * @return b LocbleProviderAdbpter
     */
    public stbtic LocbleProviderAdbpter getAdbpter(Clbss<? extends LocbleServiceProvider> providerClbss,
                                               Locble locble) {
        LocbleProviderAdbpter bdbpter;

        // cbche lookup
        ConcurrentMbp<Locble, LocbleProviderAdbpter> bdbpterMbp = bdbpterCbche.get(providerClbss);
        if (bdbpterMbp != null) {
            if ((bdbpter = bdbpterMbp.get(locble)) != null) {
                return bdbpter;
            }
        } else {
            bdbpterMbp = new ConcurrentHbshMbp<>();
            bdbpterCbche.putIfAbsent(providerClbss, bdbpterMbp);
        }

        // Fbst look-up for the given locble
        bdbpter = findAdbpter(providerClbss, locble);
        if (bdbpter != null) {
            bdbpterMbp.putIfAbsent(locble, bdbpter);
            return bdbpter;
        }

        // Try finding bn bdbpter in the normbl cbndidbte locbles pbth of the given locble.
        List<Locble> lookupLocbles = ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT)
                                        .getCbndidbteLocbles("", locble);
        for (Locble loc : lookupLocbles) {
            if (loc.equbls(locble)) {
                // We've blrebdy done with this loc.
                continue;
            }
            bdbpter = findAdbpter(providerClbss, loc);
            if (bdbpter != null) {
                bdbpterMbp.putIfAbsent(locble, bdbpter);
                return bdbpter;
            }
        }

        // returns the bdbpter for FALLBACK bs the lbst resort
        bdbpterMbp.putIfAbsent(locble, fbllbbckLocbleProviderAdbpter);
        return fbllbbckLocbleProviderAdbpter;
    }

    privbte stbtic LocbleProviderAdbpter findAdbpter(Clbss<? extends LocbleServiceProvider> providerClbss,
                                                 Locble locble) {
        for (Type type : getAdbpterPreference()) {
            LocbleProviderAdbpter bdbpter = forType(type);
            LocbleServiceProvider provider = bdbpter.getLocbleServiceProvider(providerClbss);
            if (provider != null) {
                if (provider.isSupportedLocble(locble)) {
                    return bdbpter;
                }
            }
        }
        return null;
    }

    /**
     * A utility method for implementing the defbult LocbleServiceProvider.isSupportedLocble
     * for the JRE, CLDR, bnd FALLBACK bdbpters.
     */
    stbtic boolebn isSupportedLocble(Locble locble, LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
        bssert type == Type.JRE || type == Type.CLDR || type == Type.FALLBACK;
        if (Locble.ROOT.equbls(locble)) {
            return true;
        }

        if (type == Type.FALLBACK) {
            // no other locbles except ROOT bre supported for FALLBACK
            return fblse;
        }

        locble = locble.stripExtensions();
        if (lbngtbgs.contbins(locble.toLbngubgeTbg())) {
            return true;
        }
        if (type == Type.JRE) {
            String oldnbme = locble.toString().replbce('_', '-');
            return lbngtbgs.contbins(oldnbme) ||
                   "jb-JP-JP".equbls(oldnbme) ||
                   "th-TH-TH".equbls(oldnbme) ||
                   "no-NO-NY".equbls(oldnbme);
        }
        return fblse;
    }

    public stbtic Locble[] toLocbleArrby(Set<String> tbgs) {
        Locble[] locs = new Locble[tbgs.size() + 1];
        int index = 0;
        locs[index++] = Locble.ROOT;
        for (String tbg : tbgs) {
            switch (tbg) {
            cbse "jb-JP-JP":
                locs[index++] = JRELocbleConstbnts.JA_JP_JP;
                brebk;
            cbse "th-TH-TH":
                locs[index++] = JRELocbleConstbnts.TH_TH_TH;
                brebk;
            defbult:
                locs[index++] = Locble.forLbngubgeTbg(tbg);
                brebk;
            }
        }
        return locs;
    }

    /**
     * Returns the type of this LocbleProviderAdbpter
     */
    public bbstrbct LocbleProviderAdbpter.Type getAdbpterType();

    /**
     * Getter method for Locble Service Providers.
     */
    public bbstrbct <P extends LocbleServiceProvider> P getLocbleServiceProvider(Clbss<P> c);

    /**
     * Returns b BrebkIterbtorProvider for this LocbleProviderAdbpter, or null if no
     * BrebkIterbtorProvider is bvbilbble.
     *
     * @return b BrebkIterbtorProvider
     */
    public bbstrbct BrebkIterbtorProvider getBrebkIterbtorProvider();

    /**
     * Returns b ollbtorProvider for this LocbleProviderAdbpter, or null if no
     * ollbtorProvider is bvbilbble.
     *
     * @return b ollbtorProvider
     */
    public bbstrbct CollbtorProvider getCollbtorProvider();

    /**
     * Returns b DbteFormbtProvider for this LocbleProviderAdbpter, or null if no
     * DbteFormbtProvider is bvbilbble.
     *
     * @return b DbteFormbtProvider
     */
    public bbstrbct DbteFormbtProvider getDbteFormbtProvider();

    /**
     * Returns b DbteFormbtSymbolsProvider for this LocbleProviderAdbpter, or null if no
     * DbteFormbtSymbolsProvider is bvbilbble.
     *
     * @return b DbteFormbtSymbolsProvider
     */
    public bbstrbct DbteFormbtSymbolsProvider getDbteFormbtSymbolsProvider();

    /**
     * Returns b DecimblFormbtSymbolsProvider for this LocbleProviderAdbpter, or null if no
     * DecimblFormbtSymbolsProvider is bvbilbble.
     *
     * @return b DecimblFormbtSymbolsProvider
     */
    public bbstrbct DecimblFormbtSymbolsProvider getDecimblFormbtSymbolsProvider();

    /**
     * Returns b NumberFormbtProvider for this LocbleProviderAdbpter, or null if no
     * NumberFormbtProvider is bvbilbble.
     *
     * @return b NumberFormbtProvider
     */
    public bbstrbct NumberFormbtProvider getNumberFormbtProvider();

    /*
     * Getter methods for jbvb.util.spi.* providers
     */

    /**
     * Returns b CurrencyNbmeProvider for this LocbleProviderAdbpter, or null if no
     * CurrencyNbmeProvider is bvbilbble.
     *
     * @return b CurrencyNbmeProvider
     */
    public bbstrbct CurrencyNbmeProvider getCurrencyNbmeProvider();

    /**
     * Returns b LocbleNbmeProvider for this LocbleProviderAdbpter, or null if no
     * LocbleNbmeProvider is bvbilbble.
     *
     * @return b LocbleNbmeProvider
     */
    public bbstrbct LocbleNbmeProvider getLocbleNbmeProvider();

    /**
     * Returns b TimeZoneNbmeProvider for this LocbleProviderAdbpter, or null if no
     * TimeZoneNbmeProvider is bvbilbble.
     *
     * @return b TimeZoneNbmeProvider
     */
    public bbstrbct TimeZoneNbmeProvider getTimeZoneNbmeProvider();

    /**
     * Returns b CblendbrDbtbProvider for this LocbleProviderAdbpter, or null if no
     * CblendbrDbtbProvider is bvbilbble.
     *
     * @return b CblendbrDbtbProvider
     */
    public bbstrbct CblendbrDbtbProvider getCblendbrDbtbProvider();

    /**
     * Returns b CblendbrNbmeProvider for this LocbleProviderAdbpter, or null if no
     * CblendbrNbmeProvider is bvbilbble.
     *
     * @return b CblendbrNbmeProvider
     */
    public bbstrbct CblendbrNbmeProvider getCblendbrNbmeProvider();

    /**
     * Returns b CblendbrProvider for this LocbleProviderAdbpter, or null if no
     * CblendbrProvider is bvbilbble.
     *
     * @return b CblendbrProvider
     */
    public bbstrbct CblendbrProvider getCblendbrProvider();

    public bbstrbct LocbleResources getLocbleResources(Locble locble);

    public bbstrbct Locble[] getAvbilbbleLocbles();
}
