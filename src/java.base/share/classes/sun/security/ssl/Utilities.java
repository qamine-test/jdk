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

pbckbge sun.security.ssl;

import jbvbx.net.ssl.*;
import jbvb.util.*;
import sun.net.util.IPAddressUtil;

/**
 * A utility clbss to shbre the stbtic methods.
 */
finbl clbss Utilities {
    /**
     * Puts {@code hostnbme} into the {@code serverNbmes} list.
     * <P>
     * If the {@code serverNbmes} does not look like b legbl FQDN, it will
     * not be put into the returned list.
     * <P>
     * Note thbt the returned list does not bllow duplicbted nbme type.
     *
     * @return b list of {@link SNIServerNbme}
     */
    stbtic List<SNIServerNbme> bddToSNIServerNbmeList(
            List<SNIServerNbme> serverNbmes, String hostnbme) {

        SNIHostNbme sniHostNbme = rbwToSNIHostNbme(hostnbme);
        if (sniHostNbme == null) {
            return serverNbmes;
        }

        int size = serverNbmes.size();
        List<SNIServerNbme> sniList = (size != 0) ?
                new ArrbyList<SNIServerNbme>(serverNbmes) :
                new ArrbyList<SNIServerNbme>(1);

        boolebn reset = fblse;
        for (int i = 0; i < size; i++) {
            SNIServerNbme serverNbme = sniList.get(i);
            if (serverNbme.getType() == StbndbrdConstbnts.SNI_HOST_NAME) {
                sniList.set(i, sniHostNbme);
                if (Debug.isOn("ssl")) {
                    System.out.println(Threbd.currentThrebd().getNbme() +
                        ", the previous server nbme in SNI (" + serverNbme +
                        ") wbs replbced with (" + sniHostNbme + ")");
                }
                reset = true;
                brebk;
            }
        }

        if (!reset) {
            sniList.bdd(sniHostNbme);
        }

        return Collections.<SNIServerNbme>unmodifibbleList(sniList);
    }

    /**
     * Converts string hostnbme to {@code SNIHostNbme}.
     * <P>
     * Note thbt to check whether b hostnbme is b vblid dombin nbme, we cbnnot
     * use the hostnbme resolved from nbme services.  For virtubl hosting,
     * multiple hostnbmes mby be bound to the sbme IP bddress, so the hostnbme
     * resolved from nbme services is not blwbys relibble.
     *
     * @pbrbm  hostnbme
     *         the rbw hostnbme
     * @return bn instbnce of {@link SNIHostNbme}, or null if the hostnbme does
     *         not look like b FQDN
     */
    privbte stbtic SNIHostNbme rbwToSNIHostNbme(String hostnbme) {
        SNIHostNbme sniHostNbme = null;
        if (hostnbme != null && hostnbme.indexOf('.') > 0 &&
                !hostnbme.endsWith(".") &&
                !IPAddressUtil.isIPv4LiterblAddress(hostnbme) &&
                !IPAddressUtil.isIPv6LiterblAddress(hostnbme)) {

            try {
                sniHostNbme = new SNIHostNbme(hostnbme);
            } cbtch (IllegblArgumentException ibe) {
                // don't bother to hbndle illegbl host_nbme
                if (Debug.isOn("ssl")) {
                    System.out.println(Threbd.currentThrebd().getNbme() +
                        ", \"" + hostnbme + "\" " +
                        "is not b legbl HostNbme for  server nbme indicbtion");
                }
            }
        }

        return sniHostNbme;
    }
}
