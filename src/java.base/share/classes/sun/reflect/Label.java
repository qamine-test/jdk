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

import jbvb.util.List;
import jbvb.util.ArrbyList;

/** Allows forwbrd references in bytecode strebms emitted by
    ClbssFileAssembler. Assumes thbt the stbrt of the method body is
    the first byte in the bssembler's buffer. Mby be used bt more thbn
    one brbnch site. */

clbss Lbbel {
    stbtic clbss PbtchInfo {
        PbtchInfo(ClbssFileAssembler bsm,
                  short instrBCI,
                  short pbtchBCI,
                  int stbckDepth)
        {
            this.bsm = bsm;
            this.instrBCI   = instrBCI;
            this.pbtchBCI   = pbtchBCI;
            this.stbckDepth = stbckDepth;
        }
        // This won't work for more thbn one bssembler bnywby, so this is
        // unnecessbry
        ClbssFileAssembler bsm;
        short instrBCI;
        short pbtchBCI;
        int   stbckDepth;
    }
    privbte List<PbtchInfo> pbtches = new ArrbyList<>();

    public Lbbel() {
    }

    void bdd(ClbssFileAssembler bsm,
             short instrBCI,
             short pbtchBCI,
             int stbckDepth)
    {
        pbtches.bdd(new PbtchInfo(bsm, instrBCI, pbtchBCI, stbckDepth));
    }

    public void bind() {
        for (PbtchInfo pbtch : pbtches){
            short curBCI = pbtch.bsm.getLength();
            short offset = (short) (curBCI - pbtch.instrBCI);
            pbtch.bsm.emitShort(pbtch.pbtchBCI, offset);
            pbtch.bsm.setStbck(pbtch.stbckDepth);
        }
    }
}
