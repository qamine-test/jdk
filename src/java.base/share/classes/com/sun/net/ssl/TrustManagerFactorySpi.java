/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * NOTE:  this file wbs copied from jbvbx.net.ssl.TrustMbnbgerFbctorySpi
 */

pbckbge com.sun.net.ssl;

import jbvb.security.*;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>TrustMbnbgerFbctory</code> clbss.
 *
 * <p> All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrticulbr trust mbnbger fbctory.
 *
 * @deprecbted As of JDK 1.4, this implementbtion-specific clbss wbs
 *      replbced by {@link jbvbx.net.ssl.TrustMbnbgerFbctorySpi}.
 */
@Deprecbted
public bbstrbct clbss TrustMbnbgerFbctorySpi {
    /**
     * Initiblizes this fbctory with b source of certificbte
     * buthorities bnd relbted trust mbteribl. The
     * provider mby blso include b provider-specific source
     * of key mbteribl.
     *
     * @pbrbm ks the key store or null
     */
    protected bbstrbct void engineInit(KeyStore ks) throws KeyStoreException;

    /**
     * Returns one trust mbnbger for ebch type of trust mbteribl.
     * @return the trust mbnbgers
     */
    protected bbstrbct TrustMbnbger[] engineGetTrustMbnbgers();
}
