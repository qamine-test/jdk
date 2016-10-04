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

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss InlineReturnStbtement extends Stbtement {
    Expression expr;

    /**
     * Constructor
     */
    public InlineReturnStbtement(long where, Expression expr) {
        super(INLINERETURN, where);
        this.expr = expr;
    }

    /**
     * Get the destinbtion context of b brebk
     */
    Context getDestinbtion(Context ctx) {
        for (; ctx != null ; ctx = ctx.prev) {
            if ((ctx.node != null) && ((ctx.node.op == INLINEMETHOD) || (ctx.node.op == INLINENEWINSTANCE))) {
                return ctx;
            }
        }
        return null;
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        if (expr != null) {
            expr = expr.inlineVblue(env, ctx);
        }
        return this;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        InlineReturnStbtement s = (InlineReturnStbtement)clone();
        if (expr != null) {
            s.expr = expr.copyInline(ctx);
        }
        return s;
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1 + ((expr != null) ? expr.costInline(thresh, env, ctx) : 0);
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        if (expr != null) {
            expr.codeVblue(env, ctx, bsm);
        }
        CodeContext destctx = (CodeContext)getDestinbtion(ctx);
        bsm.bdd(where, opc_goto, destctx.brebkLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("inline-return");
        if (expr != null) {
            out.print(" ");
            expr.print(out);
        }
        out.print(";");
    }
}
