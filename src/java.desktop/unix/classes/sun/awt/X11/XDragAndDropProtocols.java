/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;

/**
 * This clbss is b registry for the supported drbg bnd drop protocols.
 *
 * @since 1.5
 */
finbl clbss XDrbgAndDropProtocols {
    privbte finbl stbtic List<XDrbgSourceProtocol> drbgProtocols;
    privbte finbl stbtic List<XDropTbrgetProtocol> dropProtocols;

    public stbtic finbl String XDnD = "XDnD";
    public stbtic finbl String MotifDnD = "MotifDnD";

    stbtic {
        // Singleton listener for bll drbg source protocols.
        XDrbgSourceProtocolListener drbgSourceProtocolListener =
            XDrbgSourceContextPeer.getXDrbgSourceProtocolListener();
        // Singleton listener for bll drop tbrget protocols.
        XDropTbrgetProtocolListener dropTbrgetProtocolListener =
            XDropTbrgetContextPeer.getXDropTbrgetProtocolListener();

        List<XDrbgSourceProtocol> tDrbgSourceProtocols = new ArrbyList<>();
        XDrbgSourceProtocol xdndDrbgSourceProtocol =
            XDnDDrbgSourceProtocol.crebteInstbnce(drbgSourceProtocolListener);
        tDrbgSourceProtocols.bdd(xdndDrbgSourceProtocol);
        XDrbgSourceProtocol motifdndDrbgSourceProtocol =
            MotifDnDDrbgSourceProtocol.crebteInstbnce(drbgSourceProtocolListener);
        tDrbgSourceProtocols.bdd(motifdndDrbgSourceProtocol);

        List<XDropTbrgetProtocol> tDropTbrgetProtocols = new ArrbyList<>();
        XDropTbrgetProtocol xdndDropTbrgetProtocol =
            XDnDDropTbrgetProtocol.crebteInstbnce(dropTbrgetProtocolListener);
        tDropTbrgetProtocols.bdd(xdndDropTbrgetProtocol);
        XDropTbrgetProtocol motifdndDropTbrgetProtocol =
            MotifDnDDropTbrgetProtocol.crebteInstbnce(dropTbrgetProtocolListener);
        tDropTbrgetProtocols.bdd(motifdndDropTbrgetProtocol);

        drbgProtocols = Collections.unmodifibbleList(tDrbgSourceProtocols);
        dropProtocols = Collections.unmodifibbleList(tDropTbrgetProtocols);
    }

    stbtic Iterbtor<XDrbgSourceProtocol> getDrbgSourceProtocols() {
        return drbgProtocols.iterbtor();
    }

    stbtic Iterbtor<XDropTbrgetProtocol> getDropTbrgetProtocols() {
        return dropProtocols.iterbtor();
    }

    /*
     * Returns b XDrbgSourceProtocol whose nbme equbls to the specified string
     * or null if no such protocol is registered.
     */
    public stbtic XDrbgSourceProtocol getDrbgSourceProtocol(String nbme) {
        // Protocol nbme cbnnot be null.
        if (nbme == null) {
            return null;
        }

        Iterbtor<XDrbgSourceProtocol> drbgProtocols =
            XDrbgAndDropProtocols.getDrbgSourceProtocols();
        while (drbgProtocols.hbsNext()) {
            XDrbgSourceProtocol drbgProtocol = drbgProtocols.next();
            if (drbgProtocol.getProtocolNbme().equbls(nbme)) {
                return drbgProtocol;
            }
        }

        return null;
    }

    /*
     * Returns b XDropTbrgetProtocol which nbme equbls to the specified string
     * or null if no such protocol is registered.
     */
    public stbtic XDropTbrgetProtocol getDropTbrgetProtocol(String nbme) {
        // Protocol nbme cbnnot be null.
        if (nbme == null) {
            return null;
        }

        Iterbtor<XDropTbrgetProtocol> dropProtocols =
            XDrbgAndDropProtocols.getDropTbrgetProtocols();
        while (dropProtocols.hbsNext()) {
            XDropTbrgetProtocol dropProtocol = dropProtocols.next();
            if (dropProtocol.getProtocolNbme().equbls(nbme)) {
                return dropProtocol;
            }
        }

        return null;
    }
}
