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
clbss WhileStbtement extends Stbtement {
    Expression cond;
    Stbtement body;

    /**
     * Constructor
     */
    public WhileStbtement(long where, Expression cond, Stbtement body) {
        super(WHILE, where);
        this.cond = cond;
        this.body = body;
    }

    /**
     * Check b while stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        checkLbbel(env, ctx);
        CheckContext newctx = new CheckContext(ctx, this);
        // remember whbt wbs unbssigned on entry
        Vset vsEntry = vset.copy();
        // check the condition.  Determine which vbribbles hbve vblues if
        // it returns true or fblse.
        ConditionVbrs cvbrs =
              cond.checkCondition(env, newctx, rebch(env, vset), exp);
        cond = convert(env, newctx, Type.tBoolebn, cond);
        // check the body, given thbt the condition returned true.
        vset = body.check(env, newctx, cvbrs.vsTrue, exp);
        vset = vset.join(newctx.vsContinue);
        // mbke sure the bbck-brbnch fits the entry of the loop
        ctx.checkBbckBrbnch(env, this, vsEntry, vset);
        // Exit the while loop by testing fblse or getting b brebk stbtement
        vset = newctx.vsBrebk.join(cvbrs.vsFblse);
        return ctx.removeAdditionblVbrs(vset);
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        ctx = new Context(ctx, this);
        cond = cond.inlineVblue(env, ctx);
        if (body != null) {
            body = body.inline(env, ctx);
        }
        return this;
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1 + cond.costInline(thresh, env, ctx)
                 + ((body != null) ? body.costInline(thresh, env, ctx) : 0);
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        WhileStbtement s = (WhileStbtement)clone();
        s.cond = cond.copyInline(ctx);
        if (body != null) {
            s.body = body.copyInline(ctx, vblNeeded);
        }
        return s;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        CodeContext newctx = new CodeContext(ctx, this);

        bsm.bdd(where, opc_goto, newctx.contLbbel);

        Lbbel l1 = new Lbbel();
        bsm.bdd(l1);

        if (body != null) {
            body.code(env, newctx, bsm);
        }

        bsm.bdd(newctx.contLbbel);
        cond.codeBrbnch(env, newctx, bsm, l1, true);
        bsm.bdd(newctx.brebkLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("while ");
        cond.print(out);
        if (body != null) {
            out.print(" ");
            body.print(out, indent);
        } else {
            out.print(";");
        }
    }
}
