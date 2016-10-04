/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.spi;

import jbvbx.imbgeio.ImbgeTrbnscoder;

/**
 * The service provider interfbce (SPI) for <code>ImbgeTrbnscoder</code>s.
 * For more informbtion on service provider clbsses, see the clbss comment
 * for the <code>IIORegistry</code> clbss.
 *
 * @see IIORegistry
 * @see jbvbx.imbgeio.ImbgeTrbnscoder
 *
 */
public bbstrbct clbss ImbgeTrbnscoderSpi extends IIOServiceProvider {

    /**
     * Constructs b blbnk <code>ImbgeTrbnscoderSpi</code>.  It is up
     * to the subclbss to initiblize instbnce vbribbles bnd/or
     * override method implementbtions in order to provide working
     * versions of bll methods.
     */
    protected ImbgeTrbnscoderSpi() {
    }

    /**
     * Constructs bn <code>ImbgeTrbnscoderSpi</code> with b given set
     * of vblues.
     *
     * @pbrbm vendorNbme the vendor nbme.
     * @pbrbm version b version identifier.
     */
    public ImbgeTrbnscoderSpi(String vendorNbme,
                              String version) {
        super(vendorNbme, version);
    }

    /**
     * Returns the fully qublified clbss nbme of bn
     * <code>ImbgeRebderSpi</code> clbss thbt generbtes
     * <code>IIOMetbdbtb</code> objects thbt mby be used bs input to
     * this trbnscoder.
     *
     * @return b <code>String</code> contbining the fully-qublified
     * clbss nbme of the <code>ImbgeRebderSpi</code> implementbtion clbss.
     *
     * @see ImbgeRebderSpi
     */
    public bbstrbct String getRebderServiceProviderNbme();

    /**
     * Returns the fully qublified clbss nbme of bn
     * <code>ImbgeWriterSpi</code> clbss thbt generbtes
     * <code>IIOMetbdbtb</code> objects thbt mby be used bs input to
     * this trbnscoder.
     *
     * @return b <code>String</code> contbining the fully-qublified
     * clbss nbme of the <code>ImbgeWriterSpi</code> implementbtion clbss.
     *
     * @see ImbgeWriterSpi
     */
    public bbstrbct String getWriterServiceProviderNbme();

    /**
     * Returns bn instbnce of the <code>ImbgeTrbnscoder</code>
     * implementbtion bssocibted with this service provider.
     *
     * @return bn <code>ImbgeTrbnscoder</code> instbnce.
     */
    public bbstrbct ImbgeTrbnscoder crebteTrbnscoderInstbnce();
}
