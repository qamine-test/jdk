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
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss CommbExpression extends BinbryExpression {
    /**
     * constructor
     */
    public CommbExpression(long where, Expression left, Expression right) {
        super(COMMA, where, (right != null) ? right.type : Type.tVoid, left, right);
    }

    /**
     * Check void expression
     */
    public Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = left.check(env, ctx, vset, exp);
        vset = right.check(env, ctx, vset, exp);
        return vset;
    }

    /**
     * Select the type
     */
    void selectType(Environment env, Context ctx, int tm) {
        type = right.type;
    }

    /**
     * Simplify
     */
    Expression simplify() {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return this;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        if (left != null) {
            left = left.inline(env, ctx);
        }
        if (right != null) {
            right = right.inline(env, ctx);
        }
        return simplify();
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        if (left != null) {
            left = left.inline(env, ctx);
        }
        if (right != null) {
            right = right.inlineVblue(env, ctx);
        }
        return simplify();
    }

    /**
     * Code
     */
    int codeLVblue(Environment env, Context ctx, Assembler bsm) {
        if (right == null) {
            // throw bn bppropribte error
            return super.codeLVblue(env, ctx, bsm);
        } else {
            // Fully code the left-hbnd side.  Do the LVblue pbrt of the
            // right-hbnd side now.  The rembinder will be done by codeLobd or
            // codeStore
            if (left != null) {
                left.code(env, ctx, bsm);
            }
            return right.codeLVblue(env, ctx, bsm);
        }
    }

    void codeLobd(Environment env, Context ctx, Assembler bsm) {
        // The left-hbnd pbrt hbs blrebdy been hbndled by codeLVblue.

        if (right == null) {
            // throw bn bppropribte error
            super.codeLobd(env, ctx, bsm);
        } else {
            right.codeLobd(env, ctx, bsm);
        }
    }

    void codeStore(Environment env, Context ctx, Assembler bsm) {
        // The left-hbnd pbrt hbs blrebdy been hbndled by codeLVblue.
        if (right == null) {
            // throw bn bppropribte error
            super.codeStore(env, ctx, bsm);
        } else {
            right.codeStore(env, ctx, bsm);
        }
    }

    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        if (left != null) {
            left.code(env, ctx, bsm);
        }
        right.codeVblue(env, ctx, bsm);
    }
    public void code(Environment env, Context ctx, Assembler bsm) {
        if (left != null) {
            left.code(env, ctx, bsm);
        }
        if (right != null) {
            right.code(env, ctx, bsm);
        }
    }
}
