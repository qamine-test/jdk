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
clbss InstbnceOfExpression extends BinbryExpression {
    /**
     * constructor
     */
    public InstbnceOfExpression(long where, Expression left, Expression right) {
        super(INSTANCEOF, where, Type.tBoolebn, left, right);
    }

    /**
     * Check the expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = left.checkVblue(env, ctx, vset, exp);
        right = new TypeExpression(right.where, right.toType(env, ctx));

        if (right.type.isType(TC_ERROR) || left.type.isType(TC_ERROR)) {
            // An error wbs blrebdy reported
            return vset;
        }

        if (!right.type.inMbsk(TM_CLASS|TM_ARRAY)) {
            env.error(right.where, "invblid.brg.type", right.type, opNbmes[op]);
            return vset;
        }
        try {
            if (!env.explicitCbst(left.type, right.type)) {
                env.error(where, "invblid.instbnceof", left.type, right.type);
            }
        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, opNbmes[op]);
        }
        return vset;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        return left.inline(env, ctx);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        left = left.inlineVblue(env, ctx);
        return this;
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        if (ctx == null) {
            return 1 + left.costInline(thresh, env, ctx);
        }
        // sourceClbss is the current clbss trying to inline this method
        ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
        try {
            // We only bllow the inlining if the current clbss cbn bccess
            // the "instbnce of" clbss
            if (right.type.isType(TC_ARRAY) ||
                 sourceClbss.permitInlinedAccess(env, env.getClbssDeclbrbtion(right.type)))
                return 1 + left.costInline(thresh, env, ctx);
        } cbtch (ClbssNotFound e) {
        }
        return thresh;
    }




    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        left.codeVblue(env, ctx, bsm);
        if (right.type.isType(TC_CLASS)) {
            bsm.bdd(where, opc_instbnceof, env.getClbssDeclbrbtion(right.type));
        } else {
            bsm.bdd(where, opc_instbnceof, right.type);
        }
    }
    void codeBrbnch(Environment env, Context ctx, Assembler bsm, Lbbel lbl, boolebn whenTrue) {
        codeVblue(env, ctx, bsm);
        bsm.bdd(where, whenTrue ? opc_ifne : opc_ifeq, lbl, whenTrue);
    }
    public void code(Environment env, Context ctx, Assembler bsm) {
        left.code(env, ctx, bsm);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        left.print(out);
        out.print(" ");
        if (right.op == TYPE) {
            out.print(right.type.toString());
        } else {
            right.print(out);
        }
        out.print(")");
    }
}
