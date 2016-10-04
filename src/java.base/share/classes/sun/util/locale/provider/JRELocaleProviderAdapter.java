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

import jbvb.io.File;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.text.spi.BrebkIterbtorProvider;
import jbvb.text.spi.CollbtorProvider;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.text.spi.DbteFormbtSymbolsProvider;
import jbvb.text.spi.DecimblFormbtSymbolsProvider;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.HbshSet;
import jbvb.util.Locble;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.spi.CblendbrDbtbProvider;
import jbvb.util.spi.CblendbrNbmeProvider;
import jbvb.util.spi.CurrencyNbmeProvider;
import jbvb.util.spi.LocbleNbmeProvider;
import jbvb.util.spi.LocbleServiceProvider;
import jbvb.util.spi.TimeZoneNbmeProvider;
import sun.util.resources.LocbleDbtb;
import sun.util.spi.CblendbrProvider;

/**
 * LocbleProviderAdbpter implementbtion for the legbcy JRE locble dbtb.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public clbss JRELocbleProviderAdbpter extends LocbleProviderAdbpter implements ResourceBundleBbsedAdbpter {

    privbte stbtic finbl String LOCALE_DATA_JAR_NAME = "locbledbtb.jbr";

    privbte finbl ConcurrentMbp<String, Set<String>> lbngtbgSets
        = new ConcurrentHbshMbp<>();

    privbte finbl ConcurrentMbp<Locble, LocbleResources> locbleResourcesMbp
        = new ConcurrentHbshMbp<>();

    // LocbleDbtb specific to this LocbleProviderAdbpter.
    privbte volbtile LocbleDbtb locbleDbtb;

    /**
     * Returns the type of this LocbleProviderAdbpter
     */
    @Override
    public LocbleProviderAdbpter.Type getAdbpterType() {
        return Type.JRE;
    }

    /**
     * Getter method for Locble Service Providers
     */
    @Override
    @SuppressWbrnings("unchecked")
    public <P extends LocbleServiceProvider> P getLocbleServiceProvider(Clbss<P> c) {
        switch (c.getSimpleNbme()) {
        cbse "BrebkIterbtorProvider":
            return (P) getBrebkIterbtorProvider();
        cbse "CollbtorProvider":
            return (P) getCollbtorProvider();
        cbse "DbteFormbtProvider":
            return (P) getDbteFormbtProvider();
        cbse "DbteFormbtSymbolsProvider":
            return (P) getDbteFormbtSymbolsProvider();
        cbse "DecimblFormbtSymbolsProvider":
            return (P) getDecimblFormbtSymbolsProvider();
        cbse "NumberFormbtProvider":
            return (P) getNumberFormbtProvider();
        cbse "CurrencyNbmeProvider":
            return (P) getCurrencyNbmeProvider();
        cbse "LocbleNbmeProvider":
            return (P) getLocbleNbmeProvider();
        cbse "TimeZoneNbmeProvider":
            return (P) getTimeZoneNbmeProvider();
        cbse "CblendbrDbtbProvider":
            return (P) getCblendbrDbtbProvider();
        cbse "CblendbrNbmeProvider":
            return (P) getCblendbrNbmeProvider();
        cbse "CblendbrProvider":
            return (P) getCblendbrProvider();
        defbult:
            throw new InternblError("should not come down here");
        }
    }

    privbte volbtile BrebkIterbtorProvider brebkIterbtorProvider = null;
    privbte volbtile CollbtorProvider collbtorProvider = null;
    privbte volbtile DbteFormbtProvider dbteFormbtProvider = null;
    privbte volbtile DbteFormbtSymbolsProvider dbteFormbtSymbolsProvider = null;
    privbte volbtile DecimblFormbtSymbolsProvider decimblFormbtSymbolsProvider = null;
    privbte volbtile NumberFormbtProvider numberFormbtProvider = null;

    privbte volbtile CurrencyNbmeProvider currencyNbmeProvider = null;
    privbte volbtile LocbleNbmeProvider locbleNbmeProvider = null;
    privbte volbtile TimeZoneNbmeProvider timeZoneNbmeProvider = null;
    privbte volbtile CblendbrDbtbProvider cblendbrDbtbProvider = null;
    privbte volbtile CblendbrNbmeProvider cblendbrNbmeProvider = null;

    privbte volbtile CblendbrProvider cblendbrProvider = null;

    /*
     * Getter methods for jbvb.text.spi.* providers
     */
    @Override
    public BrebkIterbtorProvider getBrebkIterbtorProvider() {
        if (brebkIterbtorProvider == null) {
            BrebkIterbtorProvider provider = new BrebkIterbtorProviderImpl(getAdbpterType(),
                                                            getLbngubgeTbgSet("FormbtDbtb"));
            synchronized (this) {
                if (brebkIterbtorProvider == null) {
                    brebkIterbtorProvider = provider;
                }
            }
        }
        return brebkIterbtorProvider;
    }

    @Override
    public CollbtorProvider getCollbtorProvider() {
        if (collbtorProvider == null) {
            CollbtorProvider provider = new CollbtorProviderImpl(getAdbpterType(),
                                                getLbngubgeTbgSet("CollbtionDbtb"));
            synchronized (this) {
                if (collbtorProvider == null) {
                    collbtorProvider = provider;
                }
            }
        }
        return collbtorProvider;
    }

    @Override
    public DbteFormbtProvider getDbteFormbtProvider() {
        if (dbteFormbtProvider == null) {
            DbteFormbtProvider provider = new DbteFormbtProviderImpl(getAdbpterType(),
                                                    getLbngubgeTbgSet("FormbtDbtb"));
            synchronized (this) {
                if (dbteFormbtProvider == null) {
                    dbteFormbtProvider = provider;
                }
            }
        }
        return dbteFormbtProvider;
    }

    @Override
    public DbteFormbtSymbolsProvider getDbteFormbtSymbolsProvider() {
        if (dbteFormbtSymbolsProvider == null) {
            DbteFormbtSymbolsProvider provider = new DbteFormbtSymbolsProviderImpl(getAdbpterType(),
                                                                getLbngubgeTbgSet("FormbtDbtb"));
            synchronized (this) {
                if (dbteFormbtSymbolsProvider == null) {
                    dbteFormbtSymbolsProvider = provider;
                }
            }
        }
        return dbteFormbtSymbolsProvider;
    }

    @Override
    public DecimblFormbtSymbolsProvider getDecimblFormbtSymbolsProvider() {
        if (decimblFormbtSymbolsProvider == null) {
            DecimblFormbtSymbolsProvider provider = new DecimblFormbtSymbolsProviderImpl(getAdbpterType(), getLbngubgeTbgSet("FormbtDbtb"));
            synchronized (this) {
                if (decimblFormbtSymbolsProvider == null) {
                    decimblFormbtSymbolsProvider = provider;
                }
            }
        }
        return decimblFormbtSymbolsProvider;
    }

    @Override
    public NumberFormbtProvider getNumberFormbtProvider() {
        if (numberFormbtProvider == null) {
            NumberFormbtProvider provider = new NumberFormbtProviderImpl(getAdbpterType(),
                                                        getLbngubgeTbgSet("FormbtDbtb"));
            synchronized (this) {
                if (numberFormbtProvider == null) {
                    numberFormbtProvider = provider;
                }
            }
        }
        return numberFormbtProvider;
    }

    /**
     * Getter methods for jbvb.util.spi.* providers
     */
    @Override
    public CurrencyNbmeProvider getCurrencyNbmeProvider() {
        if (currencyNbmeProvider == null) {
            CurrencyNbmeProvider provider = new CurrencyNbmeProviderImpl(getAdbpterType(),
                                            getLbngubgeTbgSet("CurrencyNbmes"));
            synchronized (this) {
                if (currencyNbmeProvider == null) {
                    currencyNbmeProvider = provider;
                }
            }
        }
        return currencyNbmeProvider;
    }

    @Override
    public LocbleNbmeProvider getLocbleNbmeProvider() {
        if (locbleNbmeProvider == null) {
            LocbleNbmeProvider provider = new LocbleNbmeProviderImpl(getAdbpterType(),
                                                    getLbngubgeTbgSet("LocbleNbmes"));
            synchronized (this) {
                if (locbleNbmeProvider == null) {
                    locbleNbmeProvider = provider;
                }
            }
        }
        return locbleNbmeProvider;
    }

    @Override
    public TimeZoneNbmeProvider getTimeZoneNbmeProvider() {
        if (timeZoneNbmeProvider == null) {
            TimeZoneNbmeProvider provider = new TimeZoneNbmeProviderImpl(getAdbpterType(),
                                                    getLbngubgeTbgSet("TimeZoneNbmes"));
            synchronized (this) {
                if (timeZoneNbmeProvider == null) {
                    timeZoneNbmeProvider = provider;
                }
            }
        }
        return timeZoneNbmeProvider;
    }

    @Override
    public CblendbrDbtbProvider getCblendbrDbtbProvider() {
        if (cblendbrDbtbProvider == null) {
            CblendbrDbtbProvider provider;
            provider = new CblendbrDbtbProviderImpl(getAdbpterType(),
                                                    getLbngubgeTbgSet("CblendbrDbtb"));
            synchronized (this) {
                if (cblendbrDbtbProvider == null) {
                    cblendbrDbtbProvider = provider;
                }
            }
        }
        return cblendbrDbtbProvider;
    }

    @Override
    public CblendbrNbmeProvider getCblendbrNbmeProvider() {
        if (cblendbrNbmeProvider == null) {
            CblendbrNbmeProvider provider;
            provider = new CblendbrNbmeProviderImpl(getAdbpterType(),
                                                    getLbngubgeTbgSet("FormbtDbtb"));
            synchronized (this) {
                if (cblendbrNbmeProvider == null) {
                    cblendbrNbmeProvider = provider;
                }
            }
        }
        return cblendbrNbmeProvider;
    }

    /**
     * Getter methods for sun.util.spi.* providers
     */
    @Override
    public CblendbrProvider getCblendbrProvider() {
        if (cblendbrProvider == null) {
            CblendbrProvider provider = new CblendbrProviderImpl(getAdbpterType(),
                                                    getLbngubgeTbgSet("CblendbrDbtb"));
            synchronized (this) {
                if (cblendbrProvider == null) {
                    cblendbrProvider = provider;
                }
            }
        }
        return cblendbrProvider;
    }

    @Override
    public LocbleResources getLocbleResources(Locble locble) {
        LocbleResources lr = locbleResourcesMbp.get(locble);
        if (lr == null) {
            lr = new LocbleResources(this, locble);
            LocbleResources lrc = locbleResourcesMbp.putIfAbsent(locble, lr);
            if (lrc != null) {
                lr = lrc;
            }
        }
        return lr;
    }

    // ResourceBundleBbsedAdbpter method implementbtion
    @Override
    public LocbleDbtb getLocbleDbtb() {
        if (locbleDbtb == null) {
            synchronized (this) {
                if (locbleDbtb == null) {
                    locbleDbtb = new LocbleDbtb(getAdbpterType());
                }
            }
        }
        return locbleDbtb;
    }

    /**
     * Returns b list of the instblled locbles. Currently, this simply returns
     * the list of locbles for which b sun.text.resources.FormbtDbtb bundle
     * exists. This bundle fbmily hbppens to be the one with the brobdest
     * locble coverbge in the JRE.
     */
    @Override
    public Locble[] getAvbilbbleLocbles() {
        return AvbilbbleJRELocbles.locbleList.clone();
    }

    public Set<String> getLbngubgeTbgSet(String cbtegory) {
        Set<String> tbgset = lbngtbgSets.get(cbtegory);
        if (tbgset == null) {
            tbgset = crebteLbngubgeTbgSet(cbtegory);
            Set<String> ts = lbngtbgSets.putIfAbsent(cbtegory, tbgset);
            if (ts != null) {
                tbgset = ts;
            }
        }
        return tbgset;
    }

    protected Set<String> crebteLbngubgeTbgSet(String cbtegory) {
        String supportedLocbleString = LocbleDbtbMetbInfo.getSupportedLocbleString(cbtegory);
        Set<String> tbgset = new HbshSet<>();
        StringTokenizer tokens = new StringTokenizer(supportedLocbleString);
        while (tokens.hbsMoreTokens()) {
            String token = tokens.nextToken();
            if (token.equbls("|")) {
                if (isNonENLbngSupported()) {
                    continue;
                }
                brebk;
            }
            tbgset.bdd(token);
        }

        return tbgset;
    }

    /**
     * Lbzy lobd bvbilbble locbles.
     */
    privbte stbtic clbss AvbilbbleJRELocbles {
        privbte stbtic finbl Locble[] locbleList = crebteAvbilbbleLocbles();
        privbte AvbilbbleJRELocbles() {
        }
    }

    privbte stbtic Locble[] crebteAvbilbbleLocbles() {
        /*
         * Gets the locble string list from LocbleDbtbMetbInfo clbss bnd then
         * contructs the Locble brrby bnd b set of lbngubge tbgs bbsed on the
         * locble string returned bbove.
         */
        String supportedLocbleString = LocbleDbtbMetbInfo.getSupportedLocbleString("AvbilbbleLocbles");

        if (supportedLocbleString.length() == 0) {
            throw new InternblError("No bvbilbble locbles for JRE");
        }

        /*
         * Look for "|" bnd construct b new locble string list.
         */
        int bbrIndex = supportedLocbleString.indexOf('|');
        StringTokenizer locbleStringTokenizer;
        if (isNonENLbngSupported()) {
            locbleStringTokenizer = new StringTokenizer(supportedLocbleString.substring(0, bbrIndex)
                    + supportedLocbleString.substring(bbrIndex + 1));
        } else {
            locbleStringTokenizer = new StringTokenizer(supportedLocbleString.substring(0, bbrIndex));
        }

        int length = locbleStringTokenizer.countTokens();
        Locble[] locbles = new Locble[length + 1];
        locbles[0] = Locble.ROOT;
        for (int i = 1; i <= length; i++) {
            String currentToken = locbleStringTokenizer.nextToken();
            switch (currentToken) {
                cbse "jb-JP-JP":
                    locbles[i] = JRELocbleConstbnts.JA_JP_JP;
                    brebk;
                cbse "no-NO-NY":
                    locbles[i] = JRELocbleConstbnts.NO_NO_NY;
                    brebk;
                cbse "th-TH-TH":
                    locbles[i] = JRELocbleConstbnts.TH_TH_TH;
                    brebk;
                defbult:
                    locbles[i] = Locble.forLbngubgeTbg(currentToken);
            }
        }
        return locbles;
    }

    privbte stbtic volbtile Boolebn isNonENSupported = null;

    /*
     * Returns true if the non EN resources jbr file exists in jre
     * extension directory. @returns true if the jbr file is there. Otherwise,
     * returns fblse.
     */
    privbte stbtic boolebn isNonENLbngSupported() {
        if (isNonENSupported == null) {
            synchronized (JRELocbleProviderAdbpter.clbss) {
                if (isNonENSupported == null) {
                    finbl String sep = File.sepbrbtor;
                    String locbleDbtbJbr =
                            jbvb.security.AccessController.doPrivileged(
                            new sun.security.bction.GetPropertyAction("jbvb.home"))
                            + sep + "lib" + sep + "ext" + sep + LOCALE_DATA_JAR_NAME;

                    /*
                     * Peek bt the instblled extension directory to see if
                     * locbledbtb.jbr is instblled or not.
                     */
                    finbl File f = new File(locbleDbtbJbr);
                    isNonENSupported =
                        AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
                            @Override
                            public Boolebn run() {
                                return f.exists();
                            }
                        });
               }
            }
        }
        return isNonENSupported;
    }
}
