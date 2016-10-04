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

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss InlineMethodExpression extends Expression {
    MemberDefinition field;
    Stbtement body;

    /**
     * Constructor
     */
    InlineMethodExpression(long where, Type type, MemberDefinition field, Stbtement body) {
        super(INLINEMETHOD, where, type);
        this.field = field;
        this.body = body;
    }
    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        body = body.inline(env, new Context(ctx, this));
        if (body == null) {
            return null;
        } else if (body.op == INLINERETURN) {
            Expression expr = ((InlineReturnStbtement)body).expr;
            if (expr != null && type.isType(TC_VOID)) {
                throw new CompilerError("vblue on inline-void return");
            }
            return expr;
        } else {
            return this;
        }
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        // When this node wbs constructed, "copyInline" wblked the body
        // with b "vblNeeded" flbg which mbde bll returns either void
        // or vblue-bebring.  The type of this node reflects thbt
        // ebrlier choice.  The present inline/inlineVblue distinction
        // is ignored.
        return inline(env, ctx);
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        InlineMethodExpression e = (InlineMethodExpression)clone();
        if (body != null) {
            e.body = body.copyInline(ctx, true);
        }
        return e;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        // pop the result if there is bny (usublly, type is blrebdy void)
        super.code(env, ctx, bsm);
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        CodeContext newctx = new CodeContext(ctx, this);
        body.code(env, newctx, bsm);
        bsm.bdd(newctx.brebkLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + "\n");
        body.print(out, 1);
        out.print(")");
    }
}
