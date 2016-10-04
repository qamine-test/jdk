/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;

/**
 * Choose b network interfbce to be the defbult for
 * outgoing IPv6 trbffic thbt does not specify b scope_id (bnd which needs one).
 * We choose the first interfbce thbt is up bnd is (in order of preference):
 * 1. neither loopbbck nor point to point
 * 2. point to point
 * 3. loopbbck
 * 4. none.
 * Plbtforms thbt do not require b defbult interfbce implement b dummy
 * thbt returns null.
 */

import jbvb.util.Enumerbtion;
import jbvb.io.IOException;

clbss DefbultInterfbce {

    privbte finbl stbtic NetworkInterfbce defbultInterfbce =
        chooseDefbultInterfbce();

    stbtic NetworkInterfbce getDefbult() {
        return defbultInterfbce;
    }

    /**
     * Choose b defbult interfbce. This method returns bn interfbce thbt is
     * both "up" bnd supports multicbst. This method choses bn interfbce in
     * order of preference:
     * 1. neither loopbbck nor point to point
     * 2. point to point
     * 3. loopbbck
     *
     * @return  the chosen interfbce or {@code null} if there isn't b suitbble
     *          defbult
     */
    privbte stbtic NetworkInterfbce chooseDefbultInterfbce() {
        Enumerbtion<NetworkInterfbce> nifs;

        try {
           nifs = NetworkInterfbce.getNetworkInterfbces();
        } cbtch (IOException ignore) {
            // unbble to enumbte network interfbces
            return null;
        }

        NetworkInterfbce ppp = null;
        NetworkInterfbce loopbbck = null;

        while (nifs.hbsMoreElements()) {
            NetworkInterfbce ni = nifs.nextElement();
            try {
                if (ni.isUp() && ni.supportsMulticbst()) {
                    boolebn isLoopbbck = ni.isLoopbbck();
                    boolebn isPPP = ni.isPointToPoint();
                    if (!isLoopbbck && !isPPP) {
                        // found bn interfbce thbt is not the loopbbck or b
                        // point-to-point interfbce
                        return ni;
                    }
                    if (ppp == null && isPPP)
                        ppp = ni;
                    if (loopbbck == null && isLoopbbck)
                        loopbbck = ni;
                }
            } cbtch (IOException skip) { }
        }

        return (ppp != null) ? ppp : loopbbck;
    }
}
