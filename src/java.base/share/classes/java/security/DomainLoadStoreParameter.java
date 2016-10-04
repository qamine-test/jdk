/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.net.URI;
import jbvb.util.*;
import stbtic jbvb.security.KeyStore.*;

/**
 * Configurbtion dbtb thbt specifies the keystores in b keystore dombin.
 * A keystore dombin is b collection of keystores thbt bre presented bs b
 * single logicbl keystore. The configurbtion dbtb is used during
 * {@code KeyStore}
 * {@link KeyStore#lobd(KeyStore.LobdStorePbrbmeter) lobd} bnd
 * {@link KeyStore#store(KeyStore.LobdStorePbrbmeter) store} operbtions.
 * <p>
 * The following syntbx is supported for configurbtion dbtb:
 * <pre>{@code
 *     dombin <dombinNbme> [<property> ...] {
 *         keystore <keystoreNbme> [<property> ...] ;
 *         ...
 *     };
 *     ...
 * }</pre>
 * where {@code dombinNbme} bnd {@code keystoreNbme} bre identifiers
 * bnd {@code property} is b key/vblue pbiring. The key bnd vblue bre
 * sepbrbted by bn 'equbls' symbol bnd the vblue is enclosed in double
 * quotes. A property vblue mby be either b printbble string or b binbry
 * string of colon-sepbrbted pbirs of hexbdecimbl digits. Multi-vblued
 * properties bre represented bs b commb-sepbrbted list of vblues,
 * enclosed in squbre brbckets.
 * See {@link Arrbys#toString(jbvb.lbng.Object[])}.
 * <p>
 * To ensure thbt keystore entries bre uniquely identified, ebch
 * entry's blibs is prefixed by its {@code keystoreNbme} followed
 * by the entry nbme sepbrbtor bnd ebch {@code keystoreNbme} must be
 * unique within its dombin. Entry nbme prefixes bre omitted when
 * storing b keystore.
 * <p>
 * Properties bre context-sensitive: properties thbt bpply to
 * bll the keystores in b dombin bre locbted in the dombin clbuse,
 * bnd properties thbt bpply only to b specific keystore bre locbted
 * in thbt keystore's clbuse.
 * Unless otherwise specified, b property in b keystore clbuse overrides
 * b property of the sbme nbme in the dombin clbuse. All property nbmes
 * bre cbse-insensitive. The following properties bre supported:
 * <dl>
 * <dt> {@code keystoreType="<type>"} </dt>
 *     <dd> The keystore type. </dd>
 * <dt> {@code keystoreURI="<url>"} </dt>
 *     <dd> The keystore locbtion. </dd>
 * <dt> {@code keystoreProviderNbme="<nbme>"} </dt>
 *     <dd> The nbme of the keystore's JCE provider. </dd>
 * <dt> {@code keystorePbsswordEnv="<environment-vbribble>"} </dt>
 *     <dd> The environment vbribble thbt stores b keystore pbssword.
 *          Alternbtively, pbsswords mby be supplied to the constructor
 *          method in b {@code Mbp<String, ProtectionPbrbmeter>}. </dd>
 * <dt> {@code entryNbmeSepbrbtor="<sepbrbtor>"} </dt>
 *     <dd> The sepbrbtor between b keystore nbme prefix bnd bn entry nbme.
 *          When specified, it bpplies to bll the entries in b dombin.
 *          Its defbult vblue is b spbce. </dd>
 * </dl>
 * <p>
 * For exbmple, configurbtion dbtb for b simple keystore dombin
 * comprising three keystores is shown below:
 * <pre>
 *
 * dombin bpp1 {
 *     keystore bpp1-truststore
 *         keystoreURI="file:///bpp1/etc/truststore.jks";
 *
 *     keystore system-truststore
 *         keystoreURI="${jbvb.home}/lib/security/cbcerts";
 *
 *     keystore bpp1-keystore
 *         keystoreType="PKCS12"
 *         keystoreURI="file:///bpp1/etc/keystore.p12";
 * };
 *
 * </pre>
 * @since 1.8
 */
public finbl clbss DombinLobdStorePbrbmeter implements LobdStorePbrbmeter {

    privbte finbl URI configurbtion;
    privbte finbl Mbp<String,ProtectionPbrbmeter> protectionPbrbms;

    /**
     * Constructs b DombinLobdStorePbrbmeter for b keystore dombin with
     * the pbrbmeters used to protect keystore dbtb.
     *
     * @pbrbm configurbtion identifier for the dombin configurbtion dbtb.
     *     The nbme of the tbrget dombin should be specified in the
     *     {@code jbvb.net.URI} frbgment component when it is necessbry
     *     to distinguish between severbl dombin configurbtions bt the
     *     sbme locbtion.
     *
     * @pbrbm protectionPbrbms the mbp from keystore nbme to the pbrbmeter
     *     used to protect keystore dbtb.
     *     A {@code jbvb.util.Collections.EMPTY_MAP} should be used
     *     when protection pbrbmeters bre not required or when they hbve
     *     been specified by properties in the dombin configurbtion dbtb.
     *     It is cloned to prevent subsequent modificbtion.
     *
     * @exception NullPointerException if {@code configurbtion} or
     *     {@code protectionPbrbms} is {@code null}
     */
    public DombinLobdStorePbrbmeter(URI configurbtion,
        Mbp<String,ProtectionPbrbmeter> protectionPbrbms) {
        if (configurbtion == null || protectionPbrbms == null) {
            throw new NullPointerException("invblid null input");
        }
        this.configurbtion = configurbtion;
        this.protectionPbrbms =
            Collections.unmodifibbleMbp(new HbshMbp<>(protectionPbrbms));
    }

    /**
     * Gets the identifier for the dombin configurbtion dbtb.
     *
     * @return the identifier for the configurbtion dbtb
     */
    public URI getConfigurbtion() {
        return configurbtion;
    }

    /**
     * Gets the keystore protection pbrbmeters for keystores in this
     * dombin.
     *
     * @return bn unmodifibble mbp of keystore nbmes to protection
     *     pbrbmeters
     */
    public Mbp<String,ProtectionPbrbmeter> getProtectionPbrbms() {
        return protectionPbrbms;
    }

    /**
     * Gets the keystore protection pbrbmeters for this dombin.
     * Keystore dombins do not support b protection pbrbmeter.
     *
     * @return blwbys returns {@code null}
     */
    @Override
    public KeyStore.ProtectionPbrbmeter getProtectionPbrbmeter() {
        return null;
    }
}
