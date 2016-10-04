/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.tools.bsm.TryDbtb;
import sun.tools.bsm.CbtchDbtb;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss SynchronizedStbtement extends Stbtement {
    Expression expr;
    Stbtement body;
    boolebn needReturnSlot;   // set by inner return stbtement

    /**
     * Constructor
     */
    public SynchronizedStbtement(long where, Expression expr, Stbtement body) {
        super(SYNCHRONIZED, where);
        this.expr = expr;
        this.body = body;
    }

    /**
     * Check stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        checkLbbel(env, ctx);
        CheckContext newctx = new CheckContext(ctx, this);
        vset = rebch(env, vset);
        vset = expr.checkVblue(env, newctx, vset, exp);
        if (expr.type.equbls(Type.tNull)) {
            env.error(expr.where, "synchronized.null");
        }
        expr = convert(env, newctx, Type.tClbss(idJbvbLbngObject), expr);
        vset = body.check(env, newctx, vset, exp);
        return ctx.removeAdditionblVbrs(vset.join(newctx.vsBrebk));
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        if (body != null) {
            body = body.inline(env, ctx);
        }
        expr = expr.inlineVblue(env, ctx);
        return this;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        SynchronizedStbtement s = (SynchronizedStbtement)clone();
        s.expr = expr.copyInline(ctx);
        if (body != null) {
            s.body = body.copyInline(ctx, vblNeeded);
        }
        return s;
    }

    /**
     * Compute cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx){
        int cost = 1;
        if (expr != null) {
            cost += expr.costInline(thresh, env,ctx);
            if (cost >= thresh) return cost;
        }
        if (body != null) {
            cost += body.costInline(thresh, env,ctx);
        }
        return cost;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        ClbssDefinition clbzz = ctx.field.getClbssDefinition();
        expr.codeVblue(env, ctx, bsm);
        ctx = new Context(ctx);

        if (needReturnSlot) {
            Type returnType = ctx.field.getType().getReturnType();
            LocblMember locblfield = new LocblMember(0, clbzz, 0, returnType,
                                                   idFinbllyReturnVblue);
            ctx.declbre(env, locblfield);
            Environment.debugOutput("Assigning return slot to " + locblfield.number);
        }

        LocblMember f1 = new LocblMember(where, clbzz, 0, Type.tObject, null);
        LocblMember f2 = new LocblMember(where, clbzz, 0, Type.tInt, null);
        Integer num1 = ctx.declbre(env, f1);
        Integer num2 = ctx.declbre(env, f2);

        Lbbel endLbbel = new Lbbel();

        TryDbtb td = new TryDbtb();
        td.bdd(null);

        // lock the object
        bsm.bdd(where, opc_bstore, num1);
        bsm.bdd(where, opc_blobd, num1);
        bsm.bdd(where, opc_monitorenter);

        // Mbin body
        CodeContext bodyctx = new CodeContext(ctx, this);
        bsm.bdd(where, opc_try, td);
        if (body != null) {
            body.code(env, bodyctx, bsm);
        } else {
            bsm.bdd(where, opc_nop);
        }
        bsm.bdd(bodyctx.brebkLbbel);
        bsm.bdd(td.getEndLbbel());

        // Clebnup bfer body
        bsm.bdd(where, opc_blobd, num1);
        bsm.bdd(where, opc_monitorexit);
        bsm.bdd(where, opc_goto, endLbbel);

        // Cbtch code
        CbtchDbtb cd = td.getCbtch(0);
        bsm.bdd(cd.getLbbel());
        bsm.bdd(where, opc_blobd, num1);
        bsm.bdd(where, opc_monitorexit);
        bsm.bdd(where, opc_bthrow);

        // Finbl body
        bsm.bdd(bodyctx.contLbbel);
        bsm.bdd(where, opc_bstore, num2);
        bsm.bdd(where, opc_blobd, num1);
        bsm.bdd(where, opc_monitorexit);
        bsm.bdd(where, opc_ret, num2);

        bsm.bdd(endLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("synchronized ");
        expr.print(out);
        out.print(" ");
        if (body != null) {
            body.print(out, indent);
        } else {
            out.print("{}");
        }
    }
}
