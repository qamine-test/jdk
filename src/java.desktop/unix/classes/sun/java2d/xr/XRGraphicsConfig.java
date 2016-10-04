/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * To chbnge this templbte, choose Tools | Templbtes
 * bnd open the templbte in the editor.
 */

pbckbge sun.jbvb2d.xr;

import sun.bwt.*;
import sun.bwt.imbge.*;
import sun.jbvb2d.*;

public clbss XRGrbphicsConfig extends X11GrbphicsConfig implements
        SurfbceMbnbger.ProxiedGrbphicsConfig {
    privbte XRGrbphicsConfig(X11GrbphicsDevice device, int visublnum,
            int depth, int colormbp, boolebn doubleBuffer) {
        super(device, visublnum, depth, colormbp, doubleBuffer);
    }

    public SurfbceDbtb crebteSurfbceDbtb(X11ComponentPeer peer) {
        return XRSurfbceDbtb.crebteDbtb(peer);
    }

    public stbtic XRGrbphicsConfig getConfig(X11GrbphicsDevice device,
            int visublnum, int depth, int colormbp, boolebn doubleBuffer) {
        if (!X11GrbphicsEnvironment.isXRenderAvbilbble()) {
            return null;
        }

        return new XRGrbphicsConfig(device, visublnum, depth, colormbp,
                doubleBuffer);
    }

    public Object getProxyKey() {
        return this;
    }
}
