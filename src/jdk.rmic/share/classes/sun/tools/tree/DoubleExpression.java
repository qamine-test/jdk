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

pbckbge sun.tools.tree;

import sun.tools.jbvb.*;
import sun.tools.bsm.Assembler;
import jbvb.io.PrintStrebm;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss DoubleExpression extends ConstbntExpression {
    double vblue;

    /**
     * Constructor
     */
    public DoubleExpression(long where, double vblue) {
        super(DOUBLEVAL, where, Type.tDouble);
        this.vblue = vblue;
    }

    /**
     * Get the vblue
     */
    public Object getVblue() {
        return new Double(vblue);
    }

    /**
     * Check if the expression is equbl to b vblue
     */
    public boolebn equbls(int i) {
        return vblue == i;
    }

    /**
     * Check if the expression is equbl to its defbult stbtic vblue
     */
    public boolebn equblsDefbult() {
        // don't bllow -0.0
        return (Double.doubleToLongBits(vblue) == 0);
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        bsm.bdd(where, opc_ldc2_w, new Double(vblue));
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print(vblue + "D");
    }
}
