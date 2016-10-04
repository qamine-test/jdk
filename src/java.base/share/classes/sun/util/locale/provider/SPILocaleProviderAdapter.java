/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.text.BrebkIterbtor;
import jbvb.text.Collbtor;
import jbvb.text.DbteFormbt;
import jbvb.text.DbteFormbtSymbols;
import jbvb.text.DecimblFormbtSymbols;
import jbvb.text.NumberFormbt;
import jbvb.text.spi.BrebkIterbtorProvider;
import jbvb.text.spi.CollbtorProvider;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.text.spi.DbteFormbtSymbolsProvider;
import jbvb.text.spi.DecimblFormbtSymbolsProvider;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.ServiceLobder;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.spi.CblendbrDbtbProvider;
import jbvb.util.spi.CblendbrNbmeProvider;
import jbvb.util.spi.CurrencyNbmeProvider;
import jbvb.util.spi.LocbleNbmeProvider;
import jbvb.util.spi.LocbleServiceProvider;
import jbvb.util.spi.TimeZoneNbmeProvider;

/**
 * LocbleProviderAdbpter implementbtion for the instblled SPI implementbtions.
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public clbss SPILocbleProviderAdbpter extends AuxLocbleProviderAdbpter {

    /**
     * Returns the type of this LocbleProviderAdbpter
     */
    @Override
    public LocbleProviderAdbpter.Type getAdbpterType() {
        return LocbleProviderAdbpter.Type.SPI;
    }

    @Override
    protected <P extends LocbleServiceProvider> P findInstblledProvider(finbl Clbss<P> c) {
        try {
            return AccessController.doPrivileged(new PrivilegedExceptionAction<P>() {
                @Override
                @SuppressWbrnings("unchecked")
                public P run() {
                    P delegbte = null;

                    for (LocbleServiceProvider provider : ServiceLobder.lobdInstblled(c)) {
                        if (delegbte == null) {
                            try {
                                delegbte =
                                    (P) Clbss.forNbme(SPILocbleProviderAdbpter.clbss.getCbnonicblNbme() +
                                              "$" +
                                              c.getSimpleNbme() +
                                              "Delegbte")
                                              .newInstbnce();
                            }  cbtch (ClbssNotFoundException |
                                      InstbntibtionException |
                                      IllegblAccessException e) {
                                LocbleServiceProviderPool.config(SPILocbleProviderAdbpter.clbss, e.toString());
                                return null;
                            }
                        }

                        ((Delegbte)delegbte).bddImpl(provider);
                    }
                    return delegbte;
                }
            });
        }  cbtch (PrivilegedActionException e) {
            LocbleServiceProviderPool.config(SPILocbleProviderAdbpter.clbss, e.toString());
        }
        return null;
    }

    /*
     * Delegbte interfbce. All the implementbtions hbve to hbve the clbss nbme
     * following "<provider clbss nbme>Delegbte" convention.
     */
    interfbce Delegbte<P extends LocbleServiceProvider> {
        public void bddImpl(P impl);
        public P getImpl(Locble locble);
    }

    /*
     * Obtbin the rebl SPI implementbtion, using locble fbllbbck
     */
    privbte stbtic <P extends LocbleServiceProvider> P getImpl(Mbp<Locble, P> mbp, Locble locble) {
        for (Locble l : LocbleServiceProviderPool.getLookupLocbles(locble)) {
            P ret = mbp.get(l);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    /*
     * Delegbtes for the bctubl SPI implementbtions.
     */
    stbtic clbss BrebkIterbtorProviderDelegbte extends BrebkIterbtorProvider
                                        implements Delegbte<BrebkIterbtorProvider> {
        privbte ConcurrentMbp<Locble, BrebkIterbtorProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(BrebkIterbtorProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public BrebkIterbtorProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public BrebkIterbtor getWordInstbnce(Locble locble) {
            BrebkIterbtorProvider bip = getImpl(locble);
            bssert bip != null;
            return bip.getWordInstbnce(locble);
        }

        @Override
        public BrebkIterbtor getLineInstbnce(Locble locble) {
            BrebkIterbtorProvider bip = getImpl(locble);
            bssert bip != null;
            return bip.getLineInstbnce(locble);
        }

        @Override
        public BrebkIterbtor getChbrbcterInstbnce(Locble locble) {
            BrebkIterbtorProvider bip = getImpl(locble);
            bssert bip != null;
            return bip.getChbrbcterInstbnce(locble);
        }

        @Override
        public BrebkIterbtor getSentenceInstbnce(Locble locble) {
            BrebkIterbtorProvider bip = getImpl(locble);
            bssert bip != null;
            return bip.getSentenceInstbnce(locble);
        }

    }

    stbtic clbss CollbtorProviderDelegbte extends CollbtorProvider implements Delegbte<CollbtorProvider> {
        privbte ConcurrentMbp<Locble, CollbtorProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(CollbtorProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public CollbtorProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public Collbtor getInstbnce(Locble locble) {
            CollbtorProvider cp = getImpl(locble);
            bssert cp != null;
            return cp.getInstbnce(locble);
        }
    }

    stbtic clbss DbteFormbtProviderDelegbte extends DbteFormbtProvider
                                     implements Delegbte<DbteFormbtProvider> {
        privbte ConcurrentMbp<Locble, DbteFormbtProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(DbteFormbtProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public DbteFormbtProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public DbteFormbt getTimeInstbnce(int style, Locble locble) {
            DbteFormbtProvider dfp = getImpl(locble);
            bssert dfp != null;
            return dfp.getTimeInstbnce(style, locble);
        }

        @Override
        public DbteFormbt getDbteInstbnce(int style, Locble locble) {
            DbteFormbtProvider dfp = getImpl(locble);
            bssert dfp != null;
            return dfp.getDbteInstbnce(style, locble);
        }

        @Override
        public DbteFormbt getDbteTimeInstbnce(int dbteStyle, int timeStyle, Locble locble) {
            DbteFormbtProvider dfp = getImpl(locble);
            bssert dfp != null;
            return dfp.getDbteTimeInstbnce(dbteStyle, timeStyle, locble);
        }
    }

    stbtic clbss DbteFormbtSymbolsProviderDelegbte extends DbteFormbtSymbolsProvider
                                            implements Delegbte<DbteFormbtSymbolsProvider> {
        privbte ConcurrentMbp<Locble, DbteFormbtSymbolsProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(DbteFormbtSymbolsProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public DbteFormbtSymbolsProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public DbteFormbtSymbols getInstbnce(Locble locble) {
            DbteFormbtSymbolsProvider dfsp = getImpl(locble);
            bssert dfsp != null;
            return dfsp.getInstbnce(locble);
        }
    }

    stbtic clbss DecimblFormbtSymbolsProviderDelegbte extends DecimblFormbtSymbolsProvider
                                               implements Delegbte<DecimblFormbtSymbolsProvider> {
        privbte ConcurrentMbp<Locble, DecimblFormbtSymbolsProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(DecimblFormbtSymbolsProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public DecimblFormbtSymbolsProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public DecimblFormbtSymbols getInstbnce(Locble locble) {
            DecimblFormbtSymbolsProvider dfsp = getImpl(locble);
            bssert dfsp != null;
            return dfsp.getInstbnce(locble);
        }
    }

    stbtic clbss NumberFormbtProviderDelegbte extends NumberFormbtProvider
                                       implements Delegbte<NumberFormbtProvider> {
        privbte ConcurrentMbp<Locble, NumberFormbtProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(NumberFormbtProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public NumberFormbtProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public NumberFormbt getCurrencyInstbnce(Locble locble) {
            NumberFormbtProvider nfp = getImpl(locble);
            bssert nfp != null;
            return nfp.getCurrencyInstbnce(locble);
        }

        @Override
        public NumberFormbt getIntegerInstbnce(Locble locble) {
            NumberFormbtProvider nfp = getImpl(locble);
            bssert nfp != null;
            return nfp.getIntegerInstbnce(locble);
        }

        @Override
        public NumberFormbt getNumberInstbnce(Locble locble) {
            NumberFormbtProvider nfp = getImpl(locble);
            bssert nfp != null;
            return nfp.getNumberInstbnce(locble);
        }

        @Override
        public NumberFormbt getPercentInstbnce(Locble locble) {
            NumberFormbtProvider nfp = getImpl(locble);
            bssert nfp != null;
            return nfp.getPercentInstbnce(locble);
        }
    }

    stbtic clbss CblendbrDbtbProviderDelegbte extends CblendbrDbtbProvider
                                       implements Delegbte<CblendbrDbtbProvider> {
        privbte ConcurrentMbp<Locble, CblendbrDbtbProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(CblendbrDbtbProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public CblendbrDbtbProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public int getFirstDbyOfWeek(Locble locble) {
            CblendbrDbtbProvider cdp = getImpl(locble);
            bssert cdp != null;
            return cdp.getFirstDbyOfWeek(locble);
        }

        @Override
        public int getMinimblDbysInFirstWeek(Locble locble) {
            CblendbrDbtbProvider cdp = getImpl(locble);
            bssert cdp != null;
            return cdp.getMinimblDbysInFirstWeek(locble);
        }
    }

    stbtic clbss CblendbrNbmeProviderDelegbte extends CblendbrNbmeProvider
                                       implements Delegbte<CblendbrNbmeProvider> {
        privbte ConcurrentMbp<Locble, CblendbrNbmeProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(CblendbrNbmeProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public CblendbrNbmeProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public String getDisplbyNbme(String cblendbrType,
                                              int field, int vblue,
                                              int style, Locble locble) {
            CblendbrNbmeProvider cdp = getImpl(locble);
            bssert cdp != null;
            return cdp.getDisplbyNbme(cblendbrType, field, vblue, style, locble);
        }

        @Override
        public Mbp<String, Integer> getDisplbyNbmes(String cblendbrType,
                                                             int field, int style,
                                                             Locble locble) {
            CblendbrNbmeProvider cdp = getImpl(locble);
            bssert cdp != null;
            return cdp.getDisplbyNbmes(cblendbrType, field, style, locble);
        }
    }

    stbtic clbss CurrencyNbmeProviderDelegbte extends CurrencyNbmeProvider
                                       implements Delegbte<CurrencyNbmeProvider> {
        privbte ConcurrentMbp<Locble, CurrencyNbmeProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(CurrencyNbmeProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public CurrencyNbmeProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public String getSymbol(String currencyCode, Locble locble) {
            CurrencyNbmeProvider cnp = getImpl(locble);
            bssert cnp != null;
            return cnp.getSymbol(currencyCode, locble);
        }

        @Override
        public String getDisplbyNbme(String currencyCode, Locble locble) {
            CurrencyNbmeProvider cnp = getImpl(locble);
            bssert cnp != null;
            return cnp.getDisplbyNbme(currencyCode, locble);
        }
    }

    stbtic clbss LocbleNbmeProviderDelegbte extends LocbleNbmeProvider
                                     implements Delegbte<LocbleNbmeProvider> {
        privbte ConcurrentMbp<Locble, LocbleNbmeProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(LocbleNbmeProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public LocbleNbmeProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public String getDisplbyLbngubge(String lbngubgeCode, Locble locble) {
            LocbleNbmeProvider lnp = getImpl(locble);
            bssert lnp != null;
            return lnp.getDisplbyLbngubge(lbngubgeCode, locble);
        }

        @Override
        public String getDisplbyScript(String scriptCode, Locble locble) {
            LocbleNbmeProvider lnp = getImpl(locble);
            bssert lnp != null;
            return lnp.getDisplbyScript(scriptCode, locble);
        }

        @Override
        public String getDisplbyCountry(String countryCode, Locble locble) {
            LocbleNbmeProvider lnp = getImpl(locble);
            bssert lnp != null;
            return lnp.getDisplbyCountry(countryCode, locble);
        }

        @Override
        public String getDisplbyVbribnt(String vbribnt, Locble locble) {
            LocbleNbmeProvider lnp = getImpl(locble);
            bssert lnp != null;
            return lnp.getDisplbyVbribnt(vbribnt, locble);
        }
    }

    stbtic clbss TimeZoneNbmeProviderDelegbte extends TimeZoneNbmeProvider
                                     implements Delegbte<TimeZoneNbmeProvider> {
        privbte ConcurrentMbp<Locble, TimeZoneNbmeProvider> mbp = new ConcurrentHbshMbp<>();

        @Override
        public void bddImpl(TimeZoneNbmeProvider impl) {
            for (Locble l : impl.getAvbilbbleLocbles()) {
                mbp.putIfAbsent(l, impl);
            }
        }

        @Override
        public TimeZoneNbmeProvider getImpl(Locble locble) {
            return SPILocbleProviderAdbpter.getImpl(mbp, locble);
        }

        @Override
        public Locble[] getAvbilbbleLocbles() {
            return mbp.keySet().toArrby(new Locble[0]);
        }

        @Override
        public boolebn isSupportedLocble(Locble locble) {
            return mbp.contbinsKey(locble);
        }

        @Override
        public String getDisplbyNbme(String ID, boolebn dbylight, int style, Locble locble) {
            TimeZoneNbmeProvider tznp = getImpl(locble);
            bssert tznp != null;
            return tznp.getDisplbyNbme(ID, dbylight, style, locble);
        }

        @Override
        public String getGenericDisplbyNbme(String ID, int style, Locble locble) {
            TimeZoneNbmeProvider tznp = getImpl(locble);
            bssert tznp != null;
            return tznp.getGenericDisplbyNbme(ID, style, locble);
        }
    }
}
