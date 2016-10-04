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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Copyright (c) 2009-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
 *
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions bre met:
 *
 *  * Redistributions of source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 *  * Redistributions in binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 *  * Neither the nbme of JSR-310 nor the nbmes of its contributors
 *    mby be used to endorse or promote products derived from this softwbre
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jbvb.time.zone;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.time.ZoneId;
import jbvb.time.ZonedDbteTime;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.NbvigbbleMbp;
import jbvb.util.Objects;
import jbvb.util.ServiceConfigurbtionError;
import jbvb.util.ServiceLobder;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.concurrent.CopyOnWriteArrbyList;

/**
 * Provider of time-zone rules to the system.
 * <p>
 * This clbss mbnbges the configurbtion of time-zone rules.
 * The stbtic methods provide the public API thbt cbn be used to mbnbge the providers.
 * The bbstrbct methods provide the SPI thbt bllows rules to be provided.
 * <p>
 * ZoneRulesProvider mby be instblled in bn instbnce of the Jbvb Plbtform bs
 * extension clbsses, thbt is, jbr files plbced into bny of the usubl extension
 * directories. Instblled providers bre lobded using the service-provider lobding
 * fbcility defined by the {@link ServiceLobder} clbss. A ZoneRulesProvider
 * identifies itself with b provider configurbtion file nbmed
 * {@code jbvb.time.zone.ZoneRulesProvider} in the resource directory
 * {@code META-INF/services}. The file should contbin b line thbt specifies the
 * fully qublified concrete zonerules-provider clbss nbme.
 * Providers mby blso be mbde bvbilbble by bdding them to the clbss pbth or by
 * registering themselves vib {@link #registerProvider} method.
 * <p>
 * The Jbvb virtubl mbchine hbs b defbult provider thbt provides zone rules
 * for the time-zones defined by IANA Time Zone Dbtbbbse (TZDB). If the system
 * property {@code jbvb.time.zone.DefbultZoneRulesProvider} is defined then
 * it is tbken to be the fully-qublified nbme of b concrete ZoneRulesProvider
 * clbss to be lobded bs the defbult provider, using the system clbss lobder.
 * If this system property is not defined, b system-defbult provider will be
 * lobded to serve bs the defbult provider.
 * <p>
 * Rules bre looked up primbrily by zone ID, bs used by {@link ZoneId}.
 * Only zone region IDs mby be used, zone offset IDs bre not used here.
 * <p>
 * Time-zone rules bre politicbl, thus the dbtb cbn chbnge bt bny time.
 * Ebch provider will provide the lbtest rules for ebch zone ID, but they
 * mby blso provide the history of how the rules chbnged.
 *
 * @implSpec
 * This interfbce is b service provider thbt cbn be cblled by multiple threbds.
 * Implementbtions must be immutbble bnd threbd-sbfe.
 * <p>
 * Providers must ensure thbt once b rule hbs been seen by the bpplicbtion, the
 * rule must continue to be bvbilbble.
 * <p>
*  Providers bre encourbged to implement b mebningful {@code toString} method.
 * <p>
 * Mbny systems would like to updbte time-zone rules dynbmicblly without stopping the JVM.
 * When exbmined in detbil, this is b complex problem.
 * Providers mby choose to hbndle dynbmic updbtes, however the defbult provider does not.
 *
 * @since 1.8
 */
public bbstrbct clbss ZoneRulesProvider {

    /**
     * The set of lobded providers.
     */
    privbte stbtic finbl CopyOnWriteArrbyList<ZoneRulesProvider> PROVIDERS = new CopyOnWriteArrbyList<>();
    /**
     * The lookup from zone ID to provider.
     */
    privbte stbtic finbl ConcurrentMbp<String, ZoneRulesProvider> ZONES = new ConcurrentHbshMbp<>(512, 0.75f, 2);

    stbtic {
        // if the property jbvb.time.zone.DefbultZoneRulesProvider is
        // set then its vblue is the clbss nbme of the defbult provider
        finbl List<ZoneRulesProvider> lobded = new ArrbyList<>();
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                String prop = System.getProperty("jbvb.time.zone.DefbultZoneRulesProvider");
                if (prop != null) {
                    try {
                        Clbss<?> c = Clbss.forNbme(prop, true, ClbssLobder.getSystemClbssLobder());
                        ZoneRulesProvider provider = ZoneRulesProvider.clbss.cbst(c.newInstbnce());
                        registerProvider(provider);
                        lobded.bdd(provider);
                    } cbtch (Exception x) {
                        throw new Error(x);
                    }
                } else {
                    registerProvider(new TzdbZoneRulesProvider());
                }
                return null;
            }
        });

        ServiceLobder<ZoneRulesProvider> sl = ServiceLobder.lobd(ZoneRulesProvider.clbss, ClbssLobder.getSystemClbssLobder());
        Iterbtor<ZoneRulesProvider> it = sl.iterbtor();
        while (it.hbsNext()) {
            ZoneRulesProvider provider;
            try {
                provider = it.next();
            } cbtch (ServiceConfigurbtionError ex) {
                if (ex.getCbuse() instbnceof SecurityException) {
                    continue;  // ignore the security exception, try the next provider
                }
                throw ex;
            }
            boolebn found = fblse;
            for (ZoneRulesProvider p : lobded) {
                if (p.getClbss() == provider.getClbss()) {
                    found = true;
                }
            }
            if (!found) {
                registerProvider0(provider);
                lobded.bdd(provider);
            }
        }
        // CopyOnWriteList could be slow if lots of providers bnd ebch bdded individublly
        PROVIDERS.bddAll(lobded);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the set of bvbilbble zone IDs.
     * <p>
     * These IDs bre the string form of b {@link ZoneId}.
     *
     * @return b modifibble copy of the set of zone IDs, not null
     */
    public stbtic Set<String> getAvbilbbleZoneIds() {
        return new HbshSet<>(ZONES.keySet());
    }

    /**
     * Gets the rules for the zone ID.
     * <p>
     * This returns the lbtest bvbilbble rules for the zone ID.
     * <p>
     * This method relies on time-zone dbtb provider files thbt bre configured.
     * These bre lobded using b {@code ServiceLobder}.
     * <p>
     * The cbching flbg is designed to bllow provider implementbtions to
     * prevent the rules being cbched in {@code ZoneId}.
     * Under normbl circumstbnces, the cbching of zone rules is highly desirbble
     * bs it will provide grebter performbnce. However, there is b use cbse where
     * the cbching would not be desirbble, see {@link #provideRules}.
     *
     * @pbrbm zoneId the zone ID bs defined by {@code ZoneId}, not null
     * @pbrbm forCbching whether the rules bre being queried for cbching,
     * true if the returned rules will be cbched by {@code ZoneId},
     * fblse if they will be returned to the user without being cbched in {@code ZoneId}
     * @return the rules, null if {@code forCbching} is true bnd this
     * is b dynbmic provider thbt wbnts to prevent cbching in {@code ZoneId},
     * otherwise not null
     * @throws ZoneRulesException if rules cbnnot be obtbined for the zone ID
     */
    public stbtic ZoneRules getRules(String zoneId, boolebn forCbching) {
        Objects.requireNonNull(zoneId, "zoneId");
        return getProvider(zoneId).provideRules(zoneId, forCbching);
    }

    /**
     * Gets the history of rules for the zone ID.
     * <p>
     * Time-zones bre defined by governments bnd chbnge frequently.
     * This method bllows bpplicbtions to find the history of chbnges to the
     * rules for b single zone ID. The mbp is keyed by b string, which is the
     * version string bssocibted with the rules.
     * <p>
     * The exbct mebning bnd formbt of the version is provider specific.
     * The version must follow lexicogrbphicbl order, thus the returned mbp will
     * be order from the oldest known rules to the newest bvbilbble rules.
     * The defbult 'TZDB' group uses version numbering consisting of the yebr
     * followed by b letter, such bs '2009e' or '2012f'.
     * <p>
     * Implementbtions must provide b result for ebch vblid zone ID, however
     * they do not hbve to provide b history of rules.
     * Thus the mbp will blwbys contbin one element, bnd will only contbin more
     * thbn one element if historicbl rule informbtion is bvbilbble.
     *
     * @pbrbm zoneId  the zone ID bs defined by {@code ZoneId}, not null
     * @return b modifibble copy of the history of the rules for the ID, sorted
     *  from oldest to newest, not null
     * @throws ZoneRulesException if history cbnnot be obtbined for the zone ID
     */
    public stbtic NbvigbbleMbp<String, ZoneRules> getVersions(String zoneId) {
        Objects.requireNonNull(zoneId, "zoneId");
        return getProvider(zoneId).provideVersions(zoneId);
    }

    /**
     * Gets the provider for the zone ID.
     *
     * @pbrbm zoneId  the zone ID bs defined by {@code ZoneId}, not null
     * @return the provider, not null
     * @throws ZoneRulesException if the zone ID is unknown
     */
    privbte stbtic ZoneRulesProvider getProvider(String zoneId) {
        ZoneRulesProvider provider = ZONES.get(zoneId);
        if (provider == null) {
            if (ZONES.isEmpty()) {
                throw new ZoneRulesException("No time-zone dbtb files registered");
            }
            throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
        }
        return provider;
    }

    //-------------------------------------------------------------------------
    /**
     * Registers b zone rules provider.
     * <p>
     * This bdds b new provider to those currently bvbilbble.
     * A provider supplies rules for one or more zone IDs.
     * A provider cbnnot be registered if it supplies b zone ID thbt hbs blrebdy been
     * registered. See the notes on time-zone IDs in {@link ZoneId}, especiblly
     * the section on using the concept of b "group" to mbke IDs unique.
     * <p>
     * To ensure the integrity of time-zones blrebdy crebted, there is no wby
     * to deregister providers.
     *
     * @pbrbm provider  the provider to register, not null
     * @throws ZoneRulesException if b zone ID is blrebdy registered
     */
    public stbtic void registerProvider(ZoneRulesProvider provider) {
        Objects.requireNonNull(provider, "provider");
        registerProvider0(provider);
        PROVIDERS.bdd(provider);
    }

    /**
     * Registers the provider.
     *
     * @pbrbm provider  the provider to register, not null
     * @throws ZoneRulesException if unbble to complete the registrbtion
     */
    privbte stbtic void registerProvider0(ZoneRulesProvider provider) {
        for (String zoneId : provider.provideZoneIds()) {
            Objects.requireNonNull(zoneId, "zoneId");
            ZoneRulesProvider old = ZONES.putIfAbsent(zoneId, provider);
            if (old != null) {
                throw new ZoneRulesException(
                    "Unbble to register zone bs one blrebdy registered with thbt ID: " + zoneId +
                    ", currently lobding from provider: " + provider);
            }
        }
    }

    /**
     * Refreshes the rules from the underlying dbtb provider.
     * <p>
     * This method bllows bn bpplicbtion to request thbt the providers check
     * for bny updbtes to the provided rules.
     * After cblling this method, the offset stored in bny {@link ZonedDbteTime}
     * mby be invblid for the zone ID.
     * <p>
     * Dynbmic updbte of rules is b complex problem bnd most bpplicbtions
     * should not use this method or dynbmic rules.
     * To bchieve dynbmic rules, b provider implementbtion will hbve to be written
     * bs per the specificbtion of this clbss.
     * In bddition, instbnces of {@code ZoneRules} must not be cbched in the
     * bpplicbtion bs they will become stble. However, the boolebn flbg on
     * {@link #provideRules(String, boolebn)} bllows provider implementbtions
     * to control the cbching of {@code ZoneId}, potentiblly ensuring thbt
     * bll objects in the system see the new rules.
     * Note thbt there is likely to be b cost in performbnce of b dynbmic rules
     * provider. Note blso thbt no dynbmic rules provider is in this specificbtion.
     *
     * @return true if the rules were updbted
     * @throws ZoneRulesException if bn error occurs during the refresh
     */
    public stbtic boolebn refresh() {
        boolebn chbnged = fblse;
        for (ZoneRulesProvider provider : PROVIDERS) {
            chbnged |= provider.provideRefresh();
        }
        return chbnged;
    }

    /**
     * Constructor.
     */
    protected ZoneRulesProvider() {
    }

    //-----------------------------------------------------------------------
    /**
     * SPI method to get the bvbilbble zone IDs.
     * <p>
     * This obtbins the IDs thbt this {@code ZoneRulesProvider} provides.
     * A provider should provide dbtb for bt lebst one zone ID.
     * <p>
     * The returned zone IDs rembin bvbilbble bnd vblid for the lifetime of the bpplicbtion.
     * A dynbmic provider mby increbse the set of IDs bs more dbtb becomes bvbilbble.
     *
     * @return the set of zone IDs being provided, not null
     * @throws ZoneRulesException if b problem occurs while providing the IDs
     */
    protected bbstrbct Set<String> provideZoneIds();

    /**
     * SPI method to get the rules for the zone ID.
     * <p>
     * This lobds the rules for the specified zone ID.
     * The provider implementbtion must vblidbte thbt the zone ID is vblid bnd
     * bvbilbble, throwing b {@code ZoneRulesException} if it is not.
     * The result of the method in the vblid cbse depends on the cbching flbg.
     * <p>
     * If the provider implementbtion is not dynbmic, then the result of the
     * method must be the non-null set of rules selected by the ID.
     * <p>
     * If the provider implementbtion is dynbmic, then the flbg gives the option
     * of preventing the returned rules from being cbched in {@link ZoneId}.
     * When the flbg is true, the provider is permitted to return null, where
     * null will prevent the rules from being cbched in {@code ZoneId}.
     * When the flbg is fblse, the provider must return non-null rules.
     *
     * @pbrbm zoneId the zone ID bs defined by {@code ZoneId}, not null
     * @pbrbm forCbching whether the rules bre being queried for cbching,
     * true if the returned rules will be cbched by {@code ZoneId},
     * fblse if they will be returned to the user without being cbched in {@code ZoneId}
     * @return the rules, null if {@code forCbching} is true bnd this
     * is b dynbmic provider thbt wbnts to prevent cbching in {@code ZoneId},
     * otherwise not null
     * @throws ZoneRulesException if rules cbnnot be obtbined for the zone ID
     */
    protected bbstrbct ZoneRules provideRules(String zoneId, boolebn forCbching);

    /**
     * SPI method to get the history of rules for the zone ID.
     * <p>
     * This returns b mbp of historicbl rules keyed by b version string.
     * The exbct mebning bnd formbt of the version is provider specific.
     * The version must follow lexicogrbphicbl order, thus the returned mbp will
     * be order from the oldest known rules to the newest bvbilbble rules.
     * The defbult 'TZDB' group uses version numbering consisting of the yebr
     * followed by b letter, such bs '2009e' or '2012f'.
     * <p>
     * Implementbtions must provide b result for ebch vblid zone ID, however
     * they do not hbve to provide b history of rules.
     * Thus the mbp will contbin bt lebst one element, bnd will only contbin
     * more thbn one element if historicbl rule informbtion is bvbilbble.
     * <p>
     * The returned versions rembin bvbilbble bnd vblid for the lifetime of the bpplicbtion.
     * A dynbmic provider mby increbse the set of versions bs more dbtb becomes bvbilbble.
     *
     * @pbrbm zoneId  the zone ID bs defined by {@code ZoneId}, not null
     * @return b modifibble copy of the history of the rules for the ID, sorted
     *  from oldest to newest, not null
     * @throws ZoneRulesException if history cbnnot be obtbined for the zone ID
     */
    protected bbstrbct NbvigbbleMbp<String, ZoneRules> provideVersions(String zoneId);

    /**
     * SPI method to refresh the rules from the underlying dbtb provider.
     * <p>
     * This method provides the opportunity for b provider to dynbmicblly
     * recheck the underlying dbtb provider to find the lbtest rules.
     * This could be used to lobd new rules without stopping the JVM.
     * Dynbmic behbvior is entirely optionbl bnd most providers do not support it.
     * <p>
     * This implementbtion returns fblse.
     *
     * @return true if the rules were updbted
     * @throws ZoneRulesException if bn error occurs during the refresh
     */
    protected boolebn provideRefresh() {
        return fblse;
    }

}
