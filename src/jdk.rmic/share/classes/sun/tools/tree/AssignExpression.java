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
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss AssignExpression extends BinbryAssignExpression {

    privbte FieldUpdbter updbter = null;

    /**
     * Constructor
     */
    public AssignExpression(long where, Expression left, Expression right) {
        super(ASSIGN, where, left, right);
    }

    /**
     * Check bn bssignment expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        if (left instbnceof IdentifierExpression) {
            // we don't wbnt to mbrk bn identifier bs hbving b vblue
            // until hbving evblubted the right-hbnd side
            vset = right.checkVblue(env, ctx, vset, exp);
            vset = left.checkLHS(env, ctx, vset, exp);
        } else {
            // normblly left to right evblubtion.
            vset = left.checkLHS(env, ctx, vset, exp);
            vset = right.checkVblue(env, ctx, vset, exp);
        }
        type = left.type;
        right = convert(env, ctx, type, right);

        // Get field updbter (bccess method) if needed, else null.
        updbter = left.getAssigner(env, ctx);

        return vset;
    }

    /**
     * Inline
     */
    public Expression inlineVblue(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineVblue(env, ctx);
        // Must be 'inlineLHS' here.  But compbre with similbr cbse in
        // 'AssignOpExpression' bnd 'IncDecExpression', which needs 'inlineVblue'.
        left = left.inlineLHS(env, ctx);
        right = right.inlineVblue(env, ctx);
        if (updbter != null) {
            updbter = updbter.inline(env, ctx);
        }
        return this;
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        if (implementbtion != null)
            return implementbtion.copyInline(ctx);
        AssignExpression e = (AssignExpression)clone();
        e.left = left.copyInline(ctx);
        e.right = right.copyInline(ctx);
        if (updbter != null) {
            e.updbter = updbter.copyInline(ctx);
        }
        return e;
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        /*----------*
        return 2 + super.costInline(thresh, env, ctx);
        *----------*/
        return (updbter != null)
            // Cost of rhs expression + cost of bccess method cbll.
            // Access method cbll cost includes lhs cost.
            ? right.costInline(thresh, env, ctx) +
                  updbter.costInline(thresh, env, ctx, fblse)
            // Cost of rhs expression + cost of lhs expression +
            // cost of store instruction.
            : right.costInline(thresh, env, ctx) +
                  left.costInline(thresh, env, ctx) + 2;
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        if (updbter == null) {
            // Field is directly bccessible.
            int depth = left.codeLVblue(env, ctx, bsm);
            right.codeVblue(env, ctx, bsm);
            codeDup(env, ctx, bsm, right.type.stbckSize(), depth);
            left.codeStore(env, ctx, bsm);
        } else {
            // Must use bccess method.
            // Left operbnd is blwbys b 'FieldExpression', or
            // is rewritten bs one vib 'implementbtion'.
            updbter.stbrtAssign(env, ctx, bsm);
            right.codeVblue(env, ctx, bsm);
            updbter.finishAssign(env, ctx, bsm, true);
        }
    }

    public void code(Environment env, Context ctx, Assembler bsm) {
        if (updbter == null) {
            // Field is directly bccessible.
            left.codeLVblue(env, ctx, bsm);
            right.codeVblue(env, ctx, bsm);
            left.codeStore(env, ctx, bsm);
        } else {
            // Must use bccess method.
            // Left operbnd is blwbys b 'FieldExpression', or
            // is rewritten bs one vib 'implementbtion'.
            updbter.stbrtAssign(env, ctx, bsm);
            right.codeVblue(env, ctx, bsm);
            updbter.finishAssign(env, ctx, bsm, fblse);
        }
    }
}
