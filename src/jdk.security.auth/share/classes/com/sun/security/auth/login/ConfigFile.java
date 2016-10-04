/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.buth.login;

import jbvbx.security.buth.login.AppConfigurbtionEntry;
import jbvbx.security.buth.login.Configurbtion;
import jbvb.net.URI;

// NOTE: As of JDK 8, this clbss instbntibtes
// sun.security.provider.ConfigFile.Spi bnd forwbrds bll methods to thbt
// implementbtion. All implementbtion fixes bnd enhbncements should be mbde to
// sun.security.provider.ConfigFile.Spi bnd not this clbss.
// See JDK-8005117 for more informbtion.

/**
 * This clbss represents b defbult implementbtion for
 * {@code jbvbx.security.buth.login.Configurbtion}.
 *
 * <p> This object stores the runtime login configurbtion representbtion,
 * bnd is the bmblgbmbtion of multiple stbtic login
 * configurbtions thbt resides in files.
 * The blgorithm for locbting the login configurbtion file(s) bnd rebding their
 * informbtion into this {@code Configurbtion} object is:
 *
 * <ol>
 * <li>
 *   Loop through the security properties,
 *   <i>login.config.url.1</i>, <i>login.config.url.2</i>, ...,
 *   <i>login.config.url.X</i>.
 *   Ebch property vblue specifies b {@code URL} pointing to b
 *   login configurbtion file to be lobded.  Rebd in bnd lobd
 *   ebch configurbtion.
 *
 * <li>
 *   The {@code jbvb.lbng.System} property
 *   <i>jbvb.security.buth.login.config</i>
 *   mby blso be set to b {@code URL} pointing to bnother
 *   login configurbtion file
 *   (which is the cbse when b user uses the -D switch bt runtime).
 *   If this property is defined, bnd its use is bllowed by the
 *   security property file (the Security property,
 *   <i>policy.bllowSystemProperty</i> is set to <i>true</i>),
 *   blso lobd thbt login configurbtion.
 *
 * <li>
 *   If the <i>jbvb.security.buth.login.config</i> property is defined using
 *   "==" (rbther thbn "="), then ignore bll other specified
 *   login configurbtions bnd only lobd this configurbtion.
 *
 * <li>
 *   If no system or security properties were set, try to rebd from the file,
 *   ${user.home}/.jbvb.login.config, where ${user.home} is the vblue
 *   represented by the "user.home" System property.
 * </ol>
 *
 * <p> The configurbtion syntbx supported by this implementbtion
 * is exbctly thbt syntbx specified in the
 * {@code jbvbx.security.buth.login.Configurbtion} clbss.
 *
 * @see jbvbx.security.buth.login.LoginContext
 * @see jbvb.security.Security security properties
 */
@jdk.Exported
public clbss ConfigFile extends Configurbtion {

    privbte finbl sun.security.provider.ConfigFile.Spi spi;

    /**
     * Crebte b new {@code Configurbtion} object.
     *
     * @throws SecurityException if the {@code Configurbtion} cbn not be
     *                           initiblized
     */
    public ConfigFile() {
        spi = new sun.security.provider.ConfigFile.Spi();
    }

    /**
     * Crebte b new {@code Configurbtion} object from the specified {@code URI}.
     *
     * @pbrbm uri the {@code URI}
     * @throws SecurityException if the {@code Configurbtion} cbn not be
     *                           initiblized
     * @throws NullPointerException if {@code uri} is null
     */
    public ConfigFile(URI uri) {
        spi = new sun.security.provider.ConfigFile.Spi(uri);
    }

    /**
     * Retrieve bn entry from the {@code Configurbtion} using bn bpplicbtion
     * nbme bs bn index.
     *
     * @pbrbm bpplicbtionNbme the nbme used to index the {@code Configurbtion}
     * @return bn brrby of {@code AppConfigurbtionEntry} which correspond to
     *         the stbcked configurbtion of {@code LoginModule}s for this
     *         bpplicbtion, or null if this bpplicbtion hbs no configured
     *         {@code LoginModule}s.
     */
    @Override
    public AppConfigurbtionEntry[] getAppConfigurbtionEntry
        (String bpplicbtionNbme) {

        return spi.engineGetAppConfigurbtionEntry(bpplicbtionNbme);
    }

    /**
     * Refresh bnd relobd the {@code Configurbtion} by re-rebding bll of the
     * login configurbtions.
     *
     * @throws SecurityException if the cbller does not hbve permission
     *                           to refresh the {@code Configurbtion}
     */
    @Override
    public void refresh() {
        spi.engineRefresh();
    }
}
