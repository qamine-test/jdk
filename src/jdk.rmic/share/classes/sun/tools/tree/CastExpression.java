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
clbss CbstExpression extends BinbryExpression {
    /**
     * constructor
     */
    public CbstExpression(long where, Expression left, Expression right) {
        super(CAST, where, left.type, left, right);
    }

    /**
     * Check the expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        type = left.toType(env, ctx);
        vset = right.checkVblue(env, ctx, vset, exp);

        if (type.isType(TC_ERROR) || right.type.isType(TC_ERROR)) {
            // An error wbs blrebdy reported
            return vset;
        }

        if (type.equbls(right.type)) {
            // The types bre blrebdy the sbme
            return vset;
        }

        try {
            if (env.explicitCbst(right.type, type)) {
                right = new ConvertExpression(where, type, right);
                return vset;
            }
        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, opNbmes[op]);
        }

        // The cbst is not bllowed
        env.error(where, "invblid.cbst", right.type, type);
        return vset;
    }

    /**
     * Check if constbnt
     */
    public boolebn isConstbnt() {
        if (type.inMbsk(TM_REFERENCE) && !type.equbls(Type.tString)) {
            // must be b primitive type, or String
            return fblse;
        }
        return right.isConstbnt();
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        return right.inline(env, ctx);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        return right.inlineVblue(env, ctx);
    }


    public int costInline(int thresh, Environment env, Context ctx) {
        if (ctx == null) {
            return 1 + right.costInline(thresh, env, ctx);
        }
        // sourceClbss is the current clbss trying to inline this method
        ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
        try {
            // We only bllow the inlining if the current clbss cbn bccess
            // the cbsting clbss
            if (left.type.isType(TC_ARRAY) ||
                 sourceClbss.permitInlinedAccess(env,
                                  env.getClbssDeclbrbtion(left.type)))
                return 1 + right.costInline(thresh, env, ctx);
        } cbtch (ClbssNotFound e) {
        }
        return thresh;
    }



    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        if (type.isType(TC_ERROR)) {
            left.print(out);
        } else {
            out.print(type);
        }
        out.print(" ");
        right.print(out);
        out.print(")");
    }
}
