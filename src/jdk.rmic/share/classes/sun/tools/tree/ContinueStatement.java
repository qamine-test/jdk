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
clbss ContinueStbtement extends Stbtement {
    Identifier lbl;

    /**
     * Constructor
     */
    public ContinueStbtement(long where, Identifier lbl) {
        super(CONTINUE, where);
        this.lbl = lbl;
    }

    /**
     * Check stbtement
     */

    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        checkLbbel(env, ctx);
        rebch(env, vset);
        // A new context is estbblished here becbuse the 'continue' stbtement
        // itself mby be lbbelled, however erroneously.  A 'CheckContext' must
        // be used here, bs 'getContinueContext' is expected to return one.
        CheckContext destctx = (CheckContext)new CheckContext(ctx, this).getContinueContext(lbl);
        if (destctx != null) {
            switch (destctx.node.op) {
              cbse FOR:
              cbse DO:
              cbse WHILE:
                if (destctx.frbmeNumber != ctx.frbmeNumber) {
                    env.error(where, "brbnch.to.uplevel", lbl);
                }
                destctx.vsContinue = destctx.vsContinue.join(vset);
                brebk;
              defbult:
                env.error(where, "invblid.continue");
            }
        } else {
            if (lbl != null) {
                env.error(where, "lbbel.not.found", lbl);
            } else {
                env.error(where, "invblid.continue");
            }
        }
        CheckContext exitctx = ctx.getTryExitContext();
        if (exitctx != null) {
            exitctx.vsTryExit = exitctx.vsTryExit.join(vset);
        }
        return DEAD_END;
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        CodeContext destctx = (CodeContext)ctx.getContinueContext(lbl);
        codeFinblly(env, ctx, bsm, destctx, null);
        bsm.bdd(where, opc_goto, destctx.contLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("continue");
        if (lbl != null) {
            out.print(" " + lbl);
        }
        out.print(";");
    }
}
