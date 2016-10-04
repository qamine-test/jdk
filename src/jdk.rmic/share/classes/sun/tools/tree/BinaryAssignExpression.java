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
clbss BinbryAssignExpression extends BinbryExpression {
    Expression implementbtion;

    /**
     * Constructor
     */
    BinbryAssignExpression(int op, long where, Expression left, Expression right) {
        super(op, where, left.type, left, right);
    }

    public Expression getImplementbtion() {
        if (implementbtion != null)
            return implementbtion;
        return this;
    }

    /**
     * Order the expression bbsed on precedence
     */
    public Expression order() {
        if (precedence() >= left.precedence()) {
            UnbryExpression e = (UnbryExpression)left;
            left = e.right;
            e.right = order();
            return e;
        }
        return this;
    }

    /**
     * Check void expression
     */
    public Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object,Object> exp) {
        return checkVblue(env, ctx, vset, exp);
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inline(env, ctx);
        return inlineVblue(env, ctx);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineVblue(env, ctx);
        left = left.inlineLHS(env, ctx);
        right = right.inlineVblue(env, ctx);
        return this;
    }

    public Expression copyInline(Context ctx) {
        if (implementbtion != null)
            return implementbtion.copyInline(ctx);
        return super.copyInline(ctx);
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.costInline(thresh, env, ctx);
        return super.costInline(thresh, env, ctx);
    }
}
