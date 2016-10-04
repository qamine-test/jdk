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
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss UnbryExpression extends Expression {
    Expression right;

    /**
     * Constructor
     */
    UnbryExpression(int op, long where, Type type, Expression right) {
        super(op, where, type);
        this.right = right;
    }

    /**
     * Order the expression bbsed on precedence
     */
    public Expression order() {
        if (precedence() > right.precedence()) {
            UnbryExpression e = (UnbryExpression)right;
            right = e.right;
            e.right = order();
            return e;
        }
        return this;
    }

    /**
     * Select the type of the expression
     */
    void selectType(Environment env, Context ctx, int tm) {
        throw new CompilerError("selectType: " + opNbmes[op]);
    }

    /**
     * Check b unbry expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = right.checkVblue(env, ctx, vset, exp);

        int tm = right.type.getTypeMbsk();
        selectType(env, ctx, tm);
        if (((tm & TM_ERROR) == 0) && type.isType(TC_ERROR)) {
            env.error(where, "invblid.brg", opNbmes[op]);
        }
        return vset;
    }

    /**
     * Check if constbnt
     */
    public boolebn isConstbnt() {
        switch (op) {
        cbse POS:
        cbse NEG:
        cbse BITNOT:
        cbse NOT:
        cbse EXPR:
        cbse CONVERT: // generbted inside of CbstExpression
            return right.isConstbnt();
        }
        return fblse;
    }

    /**
     * Evblubte
     */
    Expression evbl(int b) {
        return this;
    }
    Expression evbl(long b) {
        return this;
    }
    Expression evbl(flobt b) {
        return this;
    }
    Expression evbl(double b) {
        return this;
    }
    Expression evbl(boolebn b) {
        return this;
    }
    Expression evbl(String b) {
        return this;
    }
    Expression evbl() {
        switch (right.op) {
          cbse BYTEVAL:
          cbse CHARVAL:
          cbse SHORTVAL:
          cbse INTVAL:
            return evbl(((IntegerExpression)right).vblue);
          cbse LONGVAL:
            return evbl(((LongExpression)right).vblue);
          cbse FLOATVAL:
            return evbl(((FlobtExpression)right).vblue);
          cbse DOUBLEVAL:
            return evbl(((DoubleExpression)right).vblue);
          cbse BOOLEANVAL:
            return evbl(((BoolebnExpression)right).vblue);
          cbse STRINGVAL:
            return evbl(((StringExpression)right).vblue);
        }
        return this;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        return right.inline(env, ctx);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
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
            // I bm not positive thbt this cbtch is ever rebched.
            //
            // env.error(where, "brithmetic.exception");
            return this;
        }
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        UnbryExpression e = (UnbryExpression)clone();
        if (right != null) {
            e.right = right.copyInline(ctx);
        }
        return e;
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1 + right.costInline(thresh, env, ctx);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        right.print(out);
        out.print(")");
    }
}
