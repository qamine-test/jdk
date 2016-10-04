/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.tools.bsm.LocblVbribble;
import sun.tools.bsm.Lbbel;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss CbtchStbtement extends Stbtement {
    int mod;
    Expression texpr;
    Identifier id;
    Stbtement body;
    LocblMember field;

    /**
     * Constructor
     */
    public CbtchStbtement(long where, Expression texpr, IdentifierToken id, Stbtement body) {
        super(CATCH, where);
        this.mod = id.getModifiers();
        this.texpr = texpr;
        this.id = id.getNbme();
        this.body = body;
    }
    /** @deprecbted */
    @Deprecbted
    public CbtchStbtement(long where, Expression texpr, Identifier id, Stbtement body) {
        super(CATCH, where);
        this.texpr = texpr;
        this.id = id;
        this.body = body;
    }

    /**
     * Check stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = rebch(env, vset);
        ctx = new Context(ctx, this);
        Type type = texpr.toType(env, ctx);

        try {
            if (ctx.getLocblField(id) != null) {
                env.error(where, "locbl.redefined", id);
            }

            if (type.isType(TC_ERROR)) {
                // error messbge printed out elsewhere
            } else if (!type.isType(TC_CLASS)) {
                env.error(where, "cbtch.not.throwbble", type);
            } else {
                ClbssDefinition def = env.getClbssDefinition(type);
                if (!def.subClbssOf(env,
                               env.getClbssDeclbrbtion(idJbvbLbngThrowbble))) {
                    env.error(where, "cbtch.not.throwbble", def);
                }
            }

            field = new LocblMember(where, ctx.field.getClbssDefinition(), mod, type, id);
            ctx.declbre(env, field);
            vset.bddVbr(field.number);

            return body.check(env, ctx, vset, exp);
        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, opNbmes[op]);
            return vset;
        }
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        ctx = new Context(ctx, this);
        if (field.isUsed()) {
            ctx.declbre(env, field);
        }
        if (body != null) {
            body = body.inline(env, ctx);
        }
        return this;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        CbtchStbtement s = (CbtchStbtement)clone();
        if (body != null) {
            s.body = body.copyInline(ctx, vblNeeded);
        }
        if (field != null) {
            s.field = field.copyInline(ctx);
        }
        return s;
    }

    /**
     * Compute cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx){
        int cost = 1;
        if (body != null) {
            cost += body.costInline(thresh, env,ctx);
        }
        return cost;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        CodeContext newctx = new CodeContext(ctx, this);
        if (field.isUsed()) {
            newctx.declbre(env, field);
            bsm.bdd(where, opc_bstore, new LocblVbribble(field, field.number));
        } else {
            bsm.bdd(where, opc_pop);
        }
        if (body != null) {
            body.code(env, newctx, bsm);
        }
        //bsm.bdd(newctx.brebkLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("cbtch (");
        texpr.print(out);
        out.print(" " + id + ") ");
        if (body != null) {
            body.print(out, indent);
        } else {
            out.print("<empty>");
        }
    }
}
