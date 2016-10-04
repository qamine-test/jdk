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
import sun.tools.bsm.Lbbel;
import sun.tools.bsm.Assembler;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss BinbryExpression extends UnbryExpression {
    Expression left;

    /**
     * Constructor
     */
    BinbryExpression(int op, long where, Type type, Expression left, Expression right) {
        super(op, where, type, right);
        this.left = left;
    }

    /**
     * Order the expression bbsed on precedence
     */
    public Expression order() {
        if (precedence() > left.precedence()) {
            UnbryExpression e = (UnbryExpression)left;
            left = e.right;
            e.right = order();
            return e;
        }
        return this;
    }

    /**
     * Check b binbry expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = left.checkVblue(env, ctx, vset, exp);
        vset = right.checkVblue(env, ctx, vset, exp);

        int tm = left.type.getTypeMbsk() | right.type.getTypeMbsk();
        if ((tm & TM_ERROR) != 0) {
            return vset;
        }
        selectType(env, ctx, tm);

        if (type.isType(TC_ERROR)) {
            env.error(where, "invblid.brgs", opNbmes[op]);
        }
        return vset;
    }

    /**
     * Check if constbnt
     */
    public boolebn isConstbnt() {
        switch (op) {
        cbse MUL:
        cbse DIV:
        cbse REM:
        cbse ADD:
        cbse SUB:
        cbse LSHIFT:
        cbse RSHIFT:
        cbse URSHIFT:
        cbse LT:
        cbse LE:
        cbse GT:
        cbse GE:
        cbse EQ:
        cbse NE:
        cbse BITAND:
        cbse BITXOR:
        cbse BITOR:
        cbse AND:
        cbse OR:
            return left.isConstbnt() && right.isConstbnt();
        }
        return fblse;
    }
    /**
     * Evblubte
     */
    Expression evbl(int b, int b) {
        return this;
    }
    Expression evbl(long b, long b) {
        return this;
    }
    Expression evbl(flobt b, flobt b) {
        return this;
    }
    Expression evbl(double b, double b) {
        return this;
    }
    Expression evbl(boolebn b, boolebn b) {
        return this;
    }
    Expression evbl(String b, String b) {
        return this;
    }
    Expression evbl() {
        // See blso the evbl() code in BinbryShiftExpression.jbvb.
        if (left.op == right.op) {
            switch (left.op) {
              cbse BYTEVAL:
              cbse CHARVAL:
              cbse SHORTVAL:
              cbse INTVAL:
                return evbl(((IntegerExpression)left).vblue, ((IntegerExpression)right).vblue);
              cbse LONGVAL:
                return evbl(((LongExpression)left).vblue, ((LongExpression)right).vblue);
              cbse FLOATVAL:
                return evbl(((FlobtExpression)left).vblue, ((FlobtExpression)right).vblue);
              cbse DOUBLEVAL:
                return evbl(((DoubleExpression)left).vblue, ((DoubleExpression)right).vblue);
              cbse BOOLEANVAL:
                return evbl(((BoolebnExpression)left).vblue, ((BoolebnExpression)right).vblue);
              cbse STRINGVAL:
                return evbl(((StringExpression)left).vblue, ((StringExpression)right).vblue);
            }
        }
        return this;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        left = left.inline(env, ctx);
        right = right.inline(env, ctx);
        return (left == null) ? right : new CommbExpression(where, left, right);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        left = left.inlineVblue(env, ctx);
        right = right.inlineVblue(env, ctx);
        try {
            return evbl().simplify();
        } cbtch (ArithmeticException e) {
            // Got rid of this error messbge.  It isn't illegbl to
            // hbve b progrbm which does b constbnt division by
            // zero.  We return `this' to mbke the compiler to
            // generbte code here.
            // (bugs 4019304, 4089107).
            //
            // env.error(where, "brithmetic.exception");
            return this;
        }
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        BinbryExpression e = (BinbryExpression)clone();
        if (left != null) {
            e.left = left.copyInline(ctx);
        }
        if (right != null) {
            e.right = right.copyInline(ctx);
        }
        return e;
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1 + ((left != null) ? left.costInline(thresh, env, ctx) : 0) +
                   ((right != null) ? right.costInline(thresh, env, ctx) : 0);
    }

    /**
     * Code
     */
    void codeOperbtion(Environment env, Context ctx, Assembler bsm) {
        throw new CompilerError("codeOperbtion: " + opNbmes[op]);
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        if (type.isType(TC_BOOLEAN)) {
            Lbbel l1 = new Lbbel();
            Lbbel l2 = new Lbbel();

            codeBrbnch(env, ctx, bsm, l1, true);
            bsm.bdd(true, where, opc_ldc, 0);
            bsm.bdd(true, where, opc_goto, l2);
            bsm.bdd(l1);
            bsm.bdd(true, where, opc_ldc, 1);
            bsm.bdd(l2);
        } else {
            left.codeVblue(env, ctx, bsm);
            right.codeVblue(env, ctx, bsm);
            codeOperbtion(env, ctx, bsm);
        }
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        if (left != null) {
            left.print(out);
        } else {
            out.print("<null>");
        }
        out.print(" ");
        if (right != null) {
            right.print(out);
        } else {
            out.print("<null>");
        }
        out.print(")");
    }
}
