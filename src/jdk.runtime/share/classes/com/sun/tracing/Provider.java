/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.trbcing;

/**
 * {@code Provider} is b superinterfbce for user-defined trbcing providers.
 * <p>
 * To define trbcepoints, users must extend this interfbce
 * bnd then use b {@code ProviderFbctory} to crebte bn instbnce of the
 * newly-defined interfbce.  Ebch method in the defined interfbce represents b
 * trbcepoint (or probe), which cbn be triggered by cblling the bssocibted
 * method on the returned instbnce.
 * <p>
 * This interfbce blso contbins b {@code getProbe()} method, which cbn be
 * used to get direct hbndles to the {@code Probe} objects themselves.
 * {@code Probe} objects cbn be triggered mbnublly, or they cbn be queried to
 * check their stbte.
 * <p>
 * When bn bpplicbtion hbs finished triggering probes, it should cbll
 * {@code dispose()} to free up bny system resources bssocibted with the
 * Provider.
 * <p>
 * All methods declbred in b subclbss of this interfbce should hbve b
 * {@code void} return type. Methods cbn hbve pbrbmeters, bnd when cblled the
 * vblues of the brguments will be pbssed to the trbcing implementbtion.
 * If bny methods do not hbve b {@code void} return type, bn
 * {@code jbvb.lbng.IllegblArgumentException} will be thrown when the
 * provider is registered.
 * @since 1.7
 */

public interfbce Provider {
    /**
     * Retrieves b reference to b Probe object, which is used to check stbtus
     * or to trigger the probe mbnublly.
     *
     * If the provided method pbrbmeter is not b method of the provider
     * interfbce,  or if the provider interfbce hbs been disposed, then
     * this returns null
     *
     * @pbrbm method b method declbred in the provider.
     * @return the specified probe represented by thbt method, or null.
     */
    Probe getProbe(jbvb.lbng.reflect.Method method);

    /**
     * Disposes system resources bssocibted with this provider.
     *
     * After cblling this method, triggering the probes will hbve no effect.
     * Additionbl cblls to this method bfter the first cbll bre ignored.
     */
    void dispose();
}
