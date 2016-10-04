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
import sun.tools.bsm.Lbbel;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss BoolebnExpression extends ConstbntExpression {
    boolebn vblue;

    /**
     * Constructor
     */
    public BoolebnExpression(long where, boolebn vblue) {
        super(BOOLEANVAL, where, Type.tBoolebn);
        this.vblue = vblue;
    }

    /**
     * Get the vblue
     */
    public Object getVblue() {
        return vblue ? 1 : 0;
    }

    /**
     * Check if the expression is equbl to b vblue
     */
    public boolebn equbls(boolebn b) {
        return vblue == b;
    }


    /**
     * Check if the expression is equbl to its defbult stbtic vblue
     */
    public boolebn equblsDefbult() {
        return !vblue;
    }


    /*
     * Check b "not" expression.
     *
     * cvbrs is modified so thbt
     *    cvbr.vsTrue indicbtes vbribbles with b known vblue if
     *         the expression is true.
     *    cvbrs.vsFblse indicbtes vbribbles with b known vblue if
     *         the expression is fblse
     *
     * For constbnt expressions, set the side thbt corresponds to our
     * blrebdy known vblue to vset.  Set the side thbt corresponds to the
     * other wby to "impossible"
     */

    public void checkCondition(Environment env, Context ctx,
                               Vset vset, Hbshtbble<Object, Object> exp, ConditionVbrs cvbrs) {
        if (vblue) {
            cvbrs.vsFblse = Vset.DEAD_END;
            cvbrs.vsTrue = vset;
        } else {
            cvbrs.vsFblse = vset;
            cvbrs.vsTrue = Vset.DEAD_END;
        }
    }


    /**
     * Code
     */
    void codeBrbnch(Environment env, Context ctx, Assembler bsm, Lbbel lbl, boolebn whenTrue) {
        if (vblue == whenTrue) {
            bsm.bdd(where, opc_goto, lbl);
        }
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        bsm.bdd(where, opc_ldc, vblue ? 1 : 0);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print(vblue ? "true" : "fblse");
    }
}
