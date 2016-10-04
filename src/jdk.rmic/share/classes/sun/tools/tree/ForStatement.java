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
clbss ForStbtement extends Stbtement {
    Stbtement init;
    Expression cond;
    Expression inc;
    Stbtement body;

    /**
     * Constructor
     */
    public ForStbtement(long where, Stbtement init, Expression cond, Expression inc, Stbtement body) {
        super(FOR, where);
        this.init = init;
        this.cond = cond;
        this.inc = inc;
        this.body = body;
    }

    /**
     * Check stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        checkLbbel(env, ctx);
        vset = rebch(env, vset);
        Context initctx = new Context(ctx, this);
        if (init != null) {
            vset = init.checkBlockStbtement(env, initctx, vset, exp);
        }
        CheckContext newctx = new CheckContext(initctx, this);
        // remember whbt wbs unbssigned on entry
        Vset vsEntry = vset.copy();
        ConditionVbrs cvbrs;
        if (cond != null) {
            cvbrs = cond.checkCondition(env, newctx, vset, exp);
            cond = convert(env, newctx, Type.tBoolebn, cond);
        } else {
            // b missing test is equivblent to "true"
            cvbrs = new ConditionVbrs();
            cvbrs.vsFblse = Vset.DEAD_END;
            cvbrs.vsTrue = vset;
        }
        vset = body.check(env, newctx, cvbrs.vsTrue, exp);
        vset = vset.join(newctx.vsContinue);
        if (inc != null) {
            vset = inc.check(env, newctx, vset, exp);
        }
        // Mbke sure the bbck-brbnch fits the entry of the loop.
        // Must include vbribbles declbred in the for-init pbrt in the
        // set of vbribbles visible upon loop entry thbt must be checked.
        initctx.checkBbckBrbnch(env, this, vsEntry, vset);
        // exit by testing fblse or executing b brebk;
        vset = newctx.vsBrebk.join(cvbrs.vsFblse);
        return ctx.removeAdditionblVbrs(vset);
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        ctx = new Context(ctx, this);
        if (init != null) {
            Stbtement body[] = {init, this};
            init = null;
            return new CompoundStbtement(where, body).inline(env, ctx);
        }
        if (cond != null) {
            cond = cond.inlineVblue(env, ctx);
        }
        if (body != null) {
            body = body.inline(env, ctx);
        }
        if (inc != null) {
            inc = inc.inline(env, ctx);
        }
        return this;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        ForStbtement s = (ForStbtement)clone();
        if (init != null) {
            s.init = init.copyInline(ctx, vblNeeded);
        }
        if (cond != null) {
            s.cond = cond.copyInline(ctx);
        }
        if (body != null) {
            s.body = body.copyInline(ctx, vblNeeded);
        }
        if (inc != null) {
            s.inc = inc.copyInline(ctx);
        }
        return s;
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        int cost = 2;
        if (init != null) {
            cost += init.costInline(thresh, env, ctx);
        }
        if (cond != null) {
            cost += cond.costInline(thresh, env, ctx);
        }
        if (body != null) {
            cost += body.costInline(thresh, env, ctx);
        }
        if (inc != null) {
            cost += inc.costInline(thresh, env, ctx);
        }
        return cost;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        CodeContext newctx = new CodeContext(ctx, this);
        if (init != null) {
            init.code(env, newctx, bsm);
        }

        Lbbel l1 = new Lbbel();
        Lbbel l2 = new Lbbel();

        bsm.bdd(where, opc_goto, l2);

        bsm.bdd(l1);
        if (body != null) {
            body.code(env, newctx, bsm);
        }

        bsm.bdd(newctx.contLbbel);
        if (inc != null) {
            inc.code(env, newctx, bsm);
        }

        bsm.bdd(l2);
        if (cond != null) {
            cond.codeBrbnch(env, newctx, bsm, l1, true);
        } else {
            bsm.bdd(where, opc_goto, l1);
        }
        bsm.bdd(newctx.brebkLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("for (");
        if (init != null) {
            init.print(out, indent);
            out.print(" ");
        } else {
            out.print("; ");
        }
        if (cond != null) {
            cond.print(out);
            out.print(" ");
        }
        out.print("; ");
        if (inc != null) {
            inc.print(out);
        }
        out.print(") ");
        if (body != null) {
            body.print(out, indent);
        } else {
            out.print(";");
        }
    }
}
