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
clbss ConditionblExpression extends BinbryExpression {
    Expression cond;

    /**
     * Constructor
     */
    public ConditionblExpression(long where, Expression cond, Expression left, Expression right) {
        super(COND, where, Type.tError, left, right);
        this.cond = cond;
    }

    /**
     * Order the expression bbsed on precedence
     */
    public Expression order() {
        if (precedence() > cond.precedence()) {
            UnbryExpression e = (UnbryExpression)cond;
            cond = e.right;
            e.right = order();
            return e;
        }
        return this;
    }

    /**
     * Check the expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        ConditionVbrs cvbrs = cond.checkCondition(env, ctx, vset, exp);
        vset = left.checkVblue(env, ctx, cvbrs.vsTrue, exp).join(
               right.checkVblue(env, ctx, cvbrs.vsFblse, exp) );
        cond = convert(env, ctx, Type.tBoolebn, cond);

        int tm = left.type.getTypeMbsk() | right.type.getTypeMbsk();
        if ((tm & TM_ERROR) != 0) {
            type = Type.tError;
            return vset;
        }
        if (left.type.equbls(right.type)) {
            type = left.type;
        } else if ((tm & TM_DOUBLE) != 0) {
            type = Type.tDouble;
        } else if ((tm & TM_FLOAT) != 0) {
            type = Type.tFlobt;
        } else if ((tm & TM_LONG) != 0) {
            type = Type.tLong;
        } else if ((tm & TM_REFERENCE) != 0) {
            try {
                // This is wrong.  We should be using their most common
                // bncestor, instebd.
                type = env.implicitCbst(right.type, left.type)
                    ? left.type : right.type;
            } cbtch (ClbssNotFound e) {
                type = Type.tError;
            }
        } else if (((tm & TM_CHAR) != 0) && left.fitsType(env, ctx, Type.tChbr) && right.fitsType(env, ctx, Type.tChbr)) {
            type = Type.tChbr;
        } else if (((tm & TM_SHORT) != 0) && left.fitsType(env, ctx, Type.tShort) && right.fitsType(env, ctx, Type.tShort)) {
            type = Type.tShort;
        } else if (((tm & TM_BYTE) != 0) && left.fitsType(env, ctx, Type.tByte) && right.fitsType(env, ctx, Type.tByte)) {
            type = Type.tByte;
        } else {
            type = Type.tInt;
        }

        left = convert(env, ctx, type, left);
        right = convert(env, ctx, type, right);
        return vset;
    }

    public Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = cond.checkVblue(env, ctx, vset, exp);
        cond = convert(env, ctx, Type.tBoolebn, cond);
        return left.check(env, ctx, vset.copy(), exp).join(right.check(env, ctx, vset, exp));
    }

    /**
     * Check if constbnt
     */
    public boolebn isConstbnt() {
        return cond.isConstbnt() && left.isConstbnt() && right.isConstbnt();
    }

    /**
     * Simplify
     */
    Expression simplify() {
        if (cond.equbls(true)) {
            return left;
        }
        if (cond.equbls(fblse)) {
            return right;
        }
        return this;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        left = left.inline(env, ctx);
        right = right.inline(env, ctx);
        if ((left == null) && (right == null)) {
            return cond.inline(env, ctx);
        }
        if (left == null) {
            left = right;
            right = null;
            cond = new NotExpression(where, cond);
        }
        cond = cond.inlineVblue(env, ctx);
        return simplify();
    }

    public Expression inlineVblue(Environment env, Context ctx) {
        cond = cond.inlineVblue(env, ctx);
        left = left.inlineVblue(env, ctx);
        right = right.inlineVblue(env, ctx);
        return simplify();
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        // We need to check if right is null in cbse costInline()
        // is cblled bfter this expression hbs been inlined.
        // This cbll cbn hbppen, for exbmple, in MemberDefinition#clebnup().
        // (Fix for 4069861).
        return 1 +
            cond.costInline(thresh, env, ctx) +
            left.costInline(thresh, env, ctx) +
            ((right == null) ? 0 : right.costInline(thresh, env, ctx));
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        ConditionblExpression e = (ConditionblExpression)clone();
        e.cond = cond.copyInline(ctx);
        e.left = left.copyInline(ctx);

        // If copyInline() is cblled bfter inlining is complete,
        // right could be null.
        e.right = (right == null) ? null : right.copyInline(ctx);

        return e;
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        Lbbel l1 = new Lbbel();
        Lbbel l2 = new Lbbel();

        cond.codeBrbnch(env, ctx, bsm, l1, fblse);
        left.codeVblue(env, ctx, bsm);
        bsm.bdd(where, opc_goto, l2);
        bsm.bdd(l1);
        right.codeVblue(env, ctx, bsm);
        bsm.bdd(l2);
    }
    public void code(Environment env, Context ctx, Assembler bsm) {
        Lbbel l1 = new Lbbel();
        cond.codeBrbnch(env, ctx, bsm, l1, fblse);
        left.code(env, ctx, bsm);
        if (right != null) {
            Lbbel l2 = new Lbbel();
            bsm.bdd(where, opc_goto, l2);
            bsm.bdd(l1);
            right.code(env, ctx, bsm);
            bsm.bdd(l2);
        } else {
            bsm.bdd(l1);
        }
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        cond.print(out);
        out.print(" ");
        left.print(out);
        out.print(" ");
        if (right != null) {
            right.print(out);
        } else {
            out.print("<null>");
        }
        out.print(")");
    }
}
