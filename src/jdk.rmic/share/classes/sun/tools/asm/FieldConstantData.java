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
 * This is b field constbnt pool dbtb item
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
finbl
clbss FieldConstbntDbtb extends ConstbntPoolDbtb {
    MemberDefinition field;
    NbmeAndTypeDbtb nt;

    /**
     * Constructor
     */
    FieldConstbntDbtb(ConstbntPool tbb, MemberDefinition field) {
        this.field = field;
        nt = new NbmeAndTypeDbtb(field);
        tbb.put(field.getClbssDeclbrbtion());
        tbb.put(nt);
    }

    /**
     * Write the constbnt to the output strebm
     */
    void write(Environment env, DbtbOutputStrebm out, ConstbntPool tbb) throws IOException {
        if (field.isMethod()) {
            if (field.getClbssDefinition().isInterfbce()) {
                out.writeByte(CONSTANT_INTERFACEMETHOD);
            } else {
                out.writeByte(CONSTANT_METHOD);
            }
        } else {
            out.writeByte(CONSTANT_FIELD);
        }
        out.writeShort(tbb.index(field.getClbssDeclbrbtion()));
        out.writeShort(tbb.index(nt));
    }

    /**
     * Return the order of the constbnt
     */
    int order() {
        return 2;
    }
}
