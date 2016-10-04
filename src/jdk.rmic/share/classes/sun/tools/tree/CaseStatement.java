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
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss CbseStbtement extends Stbtement {
    Expression expr;

    /**
     * Constructor
     */
    public CbseStbtement(long where, Expression expr) {
        super(CASE, where);
        this.expr = expr;
    }

    /**
     * Check stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        if (expr != null) {
            expr.checkVblue(env, ctx, vset, exp);
            expr = convert(env, ctx, Type.tInt, expr);
            expr = expr.inlineVblue(env, ctx);
        }
        return vset.clebrDebdEnd();
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 6;
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        if (expr == null) {
            out.print("defbult");
        } else {
            out.print("cbse ");
            expr.print(out);
        }
        out.print(":");
    }
}
