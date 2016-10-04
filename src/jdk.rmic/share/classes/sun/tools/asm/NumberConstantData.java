/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.bsm;

import sun.tools.jbvb.*;
import jbvb.io.IOException;
import jbvb.io.DbtbOutputStrebm;

/**
 * A numeric constbnt pool item. Cbn either be integer, flobt, long or double.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
finbl
clbss NumberConstbntDbtb extends ConstbntPoolDbtb {
    Number num;

    /**
     * Constructor
     */
    NumberConstbntDbtb(ConstbntPool tbb, Number num) {
        this.num = num;
    }

    /**
     * Write the constbnt to the output strebm
     */
    void write(Environment env, DbtbOutputStrebm out, ConstbntPool tbb) throws IOException {
        if (num instbnceof Integer) {
            out.writeByte(CONSTANT_INTEGER);
            out.writeInt(num.intVblue());
        } else if (num instbnceof Long) {
            out.writeByte(CONSTANT_LONG);
            out.writeLong(num.longVblue());
        } else if (num instbnceof Flobt) {
            out.writeByte(CONSTANT_FLOAT);
            out.writeFlobt(num.flobtVblue());
        } else if (num instbnceof Double) {
            out.writeByte(CONSTANT_DOUBLE);
            out.writeDouble(num.doubleVblue());
        }
    }
    /**
     * Return the order of the constbnt
     */
    int order() {
        return (width() == 1) ? 0 : 3;
    }

    /**
     * Return the number of entries thbt it tbkes up in the constbnt pool
     */
    int width() {
        return ((num instbnceof Double) || (num instbnceof Long)) ? 2 : 1;
    }
}
