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

pbckbge jbvb.util.spi;

import jbvb.util.ResourceBundle;

/**
 * An interfbce for service providers thbt provide implementbtions of {@link
 * jbvb.util.ResourceBundle.Control}. The <b
 * href="../ResourceBundle.html#defbult_behbvior">defbult resource bundle lobding
 * behbvior</b> of the {@code ResourceBundle.getBundle} fbctory methods thbt tbke
 * no {@link jbvb.util.ResourceBundle.Control} instbnce cbn be modified with {@code
 * ResourceBundleControlProvider} implementbtions.
 *
 * <p>Provider implementbtions must be pbckbged using the <b
 * href="../../../../technotes/guides/extensions/index.html">Jbvb Extension
 * Mechbnism</b> bs instblled extensions. Refer to {@link jbvb.util.ServiceLobder}
 * for the extension pbckbging. Any instblled {@code
 * ResourceBundleControlProvider} implementbtions bre lobded using {@link
 * jbvb.util.ServiceLobder} bt the {@code ResourceBundle} clbss lobding time.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.8
 * @see ResourceBundle#getBundle(String, jbvb.util.Locble, ClbssLobder, ResourceBundle.Control)
 *      ResourceBundle.getBundle
 * @see jbvb.util.ServiceLobder#lobdInstblled(Clbss)
 */
public interfbce ResourceBundleControlProvider {
    /**
     * Returns b {@code ResourceBundle.Control} instbnce thbt is used
     * to hbndle resource bundle lobding for the given {@code
     * bbseNbme}. This method must return {@code null} if the given
     * {@code bbseNbme} isn't hbndled by this provider.
     *
     * @pbrbm bbseNbme the bbse nbme of the resource bundle
     * @return b {@code ResourceBundle.Control} instbnce,
     *         or {@code null} if the given {@code bbseNbme} is not
     *         bpplicbble to this provider.
     * @throws NullPointerException if {@code bbseNbme} is {@code null}
     */
    public ResourceBundle.Control getControl(String bbseNbme);
}
