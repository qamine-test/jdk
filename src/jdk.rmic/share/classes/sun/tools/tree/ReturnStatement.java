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
clbss ReturnStbtement extends Stbtement {
    Expression expr;

    /**
     * Constructor
     */
    public ReturnStbtement(long where, Expression expr) {
        super(RETURN, where);
        this.expr = expr;
    }

    /**
     * Check stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        checkLbbel(env, ctx);
        vset = rebch(env, vset);
        if (expr != null) {
            vset = expr.checkVblue(env, ctx, vset, exp);
        }

        // Mbke sure the return isn't inside b stbtic initiblizer
        if (ctx.field.isInitiblizer()) {
            env.error(where, "return.inside.stbtic.initiblizer");
            return DEAD_END;
        }
        // Check return type
        if (ctx.field.getType().getReturnType().isType(TC_VOID)) {
            if (expr != null) {
                if (ctx.field.isConstructor()) {
                    env.error(where, "return.with.vblue.constr", ctx.field);
                } else {
                    env.error(where, "return.with.vblue", ctx.field);
                }
                expr = null;
            }
        } else {
            if (expr == null) {
                env.error(where, "return.without.vblue", ctx.field);
            } else {
                expr = convert(env, ctx, ctx.field.getType().getReturnType(), expr);
            }
        }
        CheckContext mctx = ctx.getReturnContext();
        if (mctx != null) {
            mctx.vsBrebk = mctx.vsBrebk.join(vset);
        }
        CheckContext exitctx = ctx.getTryExitContext();
        if (exitctx != null) {
            exitctx.vsTryExit = exitctx.vsTryExit.join(vset);
        }
        if (expr != null) {
            // see if we bre returning b vblue out of b try or synchronized
            // stbtement.  If so, find the outermost one. . . .
            Node outerFinbllyNode = null;
            for (Context c = ctx; c != null; c = c.prev) {
                if (c.node == null) {
                    continue;
                }
                if (c.node.op == METHOD) {
                    // Don't sebrch outside current method. Fixes 4084230.
                    brebk;
                }
                if (c.node.op == SYNCHRONIZED) {
                    outerFinbllyNode = c.node;
                    brebk;
                } else if (c.node.op == FINALLY
                           && ((CheckContext)c).vsContinue != null) {
                    outerFinbllyNode = c.node;
                }
            }
            if (outerFinbllyNode != null) {
                if (outerFinbllyNode.op == FINALLY) {
                    ((FinbllyStbtement)outerFinbllyNode).needReturnSlot = true;
                } else {
                    ((SynchronizedStbtement)outerFinbllyNode).needReturnSlot = true;
                }
            }
        }
        return DEAD_END;
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
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1 + ((expr != null) ? expr.costInline(thresh, env, ctx) : 0);
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        Expression e = (expr != null) ? expr.copyInline(ctx) : null;
        if ((!vblNeeded) && (e != null)) {
            Stbtement body[] = {
                new ExpressionStbtement(where, e),
                new InlineReturnStbtement(where, null)
            };
            return new CompoundStbtement(where, body);
        }
        return new InlineReturnStbtement(where, e);
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        if (expr == null) {
            codeFinblly(env, ctx, bsm, null, null);
            bsm.bdd(where, opc_return);
        } else {
            expr.codeVblue(env, ctx, bsm);
            codeFinblly(env, ctx, bsm, null, expr.type);
            bsm.bdd(where, opc_ireturn + expr.type.getTypeCodeOffset());
        }
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("return");
        if (expr != null) {
            out.print(" ");
            expr.print(out);
        }
        out.print(";");
    }
}
