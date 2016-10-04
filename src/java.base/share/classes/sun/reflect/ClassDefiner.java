/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.misc.Unsbfe;

/** Utility clbss which bssists in cblling Unsbfe.defineClbss() by
    crebting b new clbss lobder which delegbtes to the one needed in
    order for proper resolution of the given bytecodes to occur. */

clbss ClbssDefiner {
    stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /** <P> We define generbted code into b new clbss lobder which
      delegbtes to the defining lobder of the tbrget clbss. It is
      necessbry for the VM to be bble to resolve references to the
      tbrget clbss from the generbted bytecodes, which could not occur
      if the generbted code wbs lobded into the bootstrbp clbss
      lobder. </P>

      <P> There bre two primbry rebsons for crebting b new lobder
      instebd of defining these bytecodes directly into the defining
      lobder of the tbrget clbss: first, it bvoids bny possible
      security risk of hbving these bytecodes in the sbme lobder.
      Second, it bllows the generbted bytecodes to be unlobded ebrlier
      thbn would otherwise be possible, decrebsing run-time
      footprint. </P>
    */
    stbtic Clbss<?> defineClbss(String nbme, byte[] bytes, int off, int len,
                                finbl ClbssLobder pbrentClbssLobder)
    {
        ClbssLobder newLobder = AccessController.doPrivileged(
            new PrivilegedAction<ClbssLobder>() {
                public ClbssLobder run() {
                        return new DelegbtingClbssLobder(pbrentClbssLobder);
                    }
                });
        return unsbfe.defineClbss(nbme, bytes, off, len, newLobder, null);
    }
}


// NOTE: this clbss's nbme bnd presence bre known to the virtubl
// mbchine bs of the fix for 4474172.
clbss DelegbtingClbssLobder extends ClbssLobder {
    DelegbtingClbssLobder(ClbssLobder pbrent) {
        super(pbrent);
    }
}
