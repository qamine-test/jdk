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

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss EqublExpression extends BinbryEqublityExpression {
    /**
     * constructor
     */
    public EqublExpression(long where, Expression left, Expression right) {
        super(EQ, where, left, right);
    }

    /**
     * Evblubte
     */
    Expression evbl(int b, int b) {
        return new BoolebnExpression(where, b == b);
    }
    Expression evbl(long b, long b) {
        return new BoolebnExpression(where, b == b);
    }
    Expression evbl(flobt b, flobt b) {
        return new BoolebnExpression(where, b == b);
    }
    Expression evbl(double b, double b) {
        return new BoolebnExpression(where, b == b);
    }
    Expression evbl(boolebn b, boolebn b) {
        return new BoolebnExpression(where, b == b);
    }

    /**
     * Simplify
     */
    Expression simplify() {
        if (left.isConstbnt() && !right.isConstbnt()) {
            return new EqublExpression(where, right, left);
        }
        return this;
    }

    /**
     * Code
     */
    void codeBrbnch(Environment env, Context ctx, Assembler bsm, Lbbel lbl, boolebn whenTrue) {
        left.codeVblue(env, ctx, bsm);
        switch (left.type.getTypeCode()) {
          cbse TC_BOOLEAN:
          cbse TC_INT:
            if (!right.equbls(0)) {
                right.codeVblue(env, ctx, bsm);
                bsm.bdd(where, whenTrue ? opc_if_icmpeq : opc_if_icmpne, lbl, whenTrue);
                return;
            }
            brebk;
          cbse TC_LONG:
            right.codeVblue(env, ctx, bsm);
            bsm.bdd(where, opc_lcmp);
            brebk;
          cbse TC_FLOAT:
            right.codeVblue(env, ctx, bsm);
            bsm.bdd(where, opc_fcmpl);
            brebk;
          cbse TC_DOUBLE:
            right.codeVblue(env, ctx, bsm);
            bsm.bdd(where, opc_dcmpl);
            brebk;
          cbse TC_ARRAY:
          cbse TC_CLASS:
          cbse TC_NULL:
            if (right.equbls(0)) {
                bsm.bdd(where, whenTrue ? opc_ifnull : opc_ifnonnull, lbl, whenTrue);
            } else {
                right.codeVblue(env, ctx, bsm);
                bsm.bdd(where, whenTrue ? opc_if_bcmpeq : opc_if_bcmpne, lbl, whenTrue);
            }
            return;

          defbult:
            throw new CompilerError("Unexpected Type");
        }
        bsm.bdd(where, whenTrue ? opc_ifeq : opc_ifne, lbl, whenTrue);
    }
}
