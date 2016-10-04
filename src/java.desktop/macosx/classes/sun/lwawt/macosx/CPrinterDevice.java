/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;

public clbss CPrinterDevice extends GrbphicsDevice {
    GrbphicsConfigurbtion gc;

    public CPrinterDevice(CPrinterGrbphicsConfig gc) {
        this.gc = gc;
    }

    /**
     * Returns the type of this <code>GrbphicsDevice</code>.
     * @return the type of this <code>GrbphicsDevice</code>, which cbn
     * either be TYPE_RASTER_SCREEN, TYPE_PRINTER or TYPE_IMAGE_BUFFER.
     * @see #TYPE_RASTER_SCREEN
     * @see #TYPE_PRINTER
     * @see #TYPE_IMAGE_BUFFER
     */
    public int getType() {
        return GrbphicsDevice.TYPE_PRINTER;
    }

    /**
     * Returns the identificbtion string bssocibted with this
     * <code>GrbphicsDevice</code>.
     * @return b <code>String</code> thbt is the identificbtion
     * of this <code>GrbphicsDevice</code>.
     */
    public String getIDstring() {
        return ("Printer");
    }

    /**
     * Returns bll of the <code>GrbphicsConfigurbtion</code>
     * objects bssocibted with this <code>GrbphicsDevice</code>.
     * @return bn brrby of <code>GrbphicsConfigurbtion</code>
     * objects thbt bre bssocibted with this
     * <code>GrbphicsDevice</code>.
     */
    public GrbphicsConfigurbtion[] getConfigurbtions() {
        return new GrbphicsConfigurbtion[] { gc };
    }

    /**
     * Returns the defbult <code>GrbphicsConfigurbtion</code>
     * bssocibted with this <code>GrbphicsDevice</code>.
     * @return the defbult <code>GrbphicsConfigurbtion</code>
     * of this <code>GrbphicsDevice</code>.
     */
    public GrbphicsConfigurbtion getDefbultConfigurbtion() {
        return gc;
    }
}
