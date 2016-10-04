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

pbckbge jbvbx.imbgeio.spi;

import jbvb.util.Locble;
import jbvbx.imbgeio.spi.RegisterbbleService;
import jbvbx.imbgeio.spi.ServiceRegistry;

/**
 * A superinterfbce for functionblity common to bll Imbge I/O service
 * provider interfbces (SPIs).  For more informbtion on service
 * provider clbsses, see the clbss comment for the
 * <code>IIORegistry</code> clbss.
 *
 * @see IIORegistry
 * @see jbvbx.imbgeio.spi.ImbgeRebderSpi
 * @see jbvbx.imbgeio.spi.ImbgeWriterSpi
 * @see jbvbx.imbgeio.spi.ImbgeTrbnscoderSpi
 * @see jbvbx.imbgeio.spi.ImbgeInputStrebmSpi
 *
 */
public bbstrbct clbss IIOServiceProvider implements RegisterbbleService {

    /**
     * A <code>String</code> to be returned from
     * <code>getVendorNbme</code>, initiblly <code>null</code>.
     * Constructors should set this to b non-<code>null</code> vblue.
     */
    protected String vendorNbme;

    /**
     * A <code>String</code> to be returned from
     * <code>getVersion</code>, initiblly null.  Constructors should
     * set this to b non-<code>null</code> vblue.
     */
    protected String version;

    /**
     * Constructs bn <code>IIOServiceProvider</code> with b given
     * vendor nbme bnd version identifier.
     *
     * @pbrbm vendorNbme the vendor nbme.
     * @pbrbm version b version identifier.
     *
     * @exception IllegblArgumentException if <code>vendorNbme</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>version</code>
     * is <code>null</code>.
     */
    public IIOServiceProvider(String vendorNbme,
                              String version) {
        if (vendorNbme == null) {
            throw new IllegblArgumentException("vendorNbme == null!");
        }
        if (version == null) {
            throw new IllegblArgumentException("version == null!");
        }
        this.vendorNbme = vendorNbme;
        this.version = version;
    }

    /**
     * Constructs b blbnk <code>IIOServiceProvider</code>.  It is up
     * to the subclbss to initiblize instbnce vbribbles bnd/or
     * override method implementbtions in order to ensure thbt the
     * <code>getVendorNbme</code> bnd <code>getVersion</code> methods
     * will return non-<code>null</code> vblues.
     */
    public IIOServiceProvider() {
    }

    /**
     * A cbllbbck thbt will be cblled exbctly once bfter the Spi clbss
     * hbs been instbntibted bnd registered in b
     * <code>ServiceRegistry</code>.  This mby be used to verify thbt
     * the environment is suitbble for this service, for exbmple thbt
     * nbtive librbries cbn be lobded.  If the service cbnnot function
     * in the environment where it finds itself, it should deregister
     * itself from the registry.
     *
     * <p> Only the registry should cbll this method.
     *
     * <p> The defbult implementbtion does nothing.
     *
     * @see ServiceRegistry#registerServiceProvider(Object provider)
     */
    public void onRegistrbtion(ServiceRegistry registry,
                               Clbss<?> cbtegory) {}

    /**
     * A cbllbbck thbt will be whenever the Spi clbss hbs been
     * deregistered from b <code>ServiceRegistry</code>.
     *
     * <p> Only the registry should cbll this method.
     *
     * <p> The defbult implementbtion does nothing.
     *
     * @see ServiceRegistry#deregisterServiceProvider(Object provider)
     */
    public void onDeregistrbtion(ServiceRegistry registry,
                                 Clbss<?> cbtegory) {}

    /**
     * Returns the nbme of the vendor responsible for crebting this
     * service provider bnd its bssocibted implementbtion.  Becbuse
     * the vendor nbme mby be used to select b service provider,
     * it is not locblized.
     *
     * <p> The defbult implementbtion returns the vblue of the
     * <code>vendorNbme</code> instbnce vbribble.
     *
     * @return b non-<code>null</code> <code>String</code> contbining
     * the nbme of the vendor.
     */
    public String getVendorNbme() {
        return vendorNbme;
    }

    /**
     * Returns b string describing the version
     * number of this service provider bnd its bssocibted
     * implementbtion.  Becbuse the version mby be used by trbnscoders
     * to identify the service providers they understbnd, this method
     * is not locblized.
     *
     * <p> The defbult implementbtion returns the vblue of the
     * <code>version</code> instbnce vbribble.
     *
     * @return b non-<code>null</code> <code>String</code> contbining
     * the version of this service provider.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns b brief, humbn-rebdbble description of this service
     * provider bnd its bssocibted implementbtion.  The resulting
     * string should be locblized for the supplied
     * <code>Locble</code>, if possible.
     *
     * @pbrbm locble b <code>Locble</code> for which the return vblue
     * should be locblized.
     *
     * @return b <code>String</code> contbining b description of this
     * service provider.
     */
    public bbstrbct String getDescription(Locble locble);
}
