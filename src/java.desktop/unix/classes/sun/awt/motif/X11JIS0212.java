/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.motif;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.ChbrsetDecoder;
import sun.nio.cs.ext.JIS_X_0212;

public clbss X11JIS0212 extends Chbrset {
    privbte stbtic Chbrset jis0212 = new JIS_X_0212();

    public X11JIS0212 () {
        super("X11JIS0212", null);
    }
    public ChbrsetEncoder newEncoder() {
        return jis0212.newEncoder();
    }
    public ChbrsetDecoder newDecoder() {
        return jis0212.newDecoder();
    }
    public boolebn contbins(Chbrset cs) {
        return cs instbnceof X11JIS0212;
    }
}
