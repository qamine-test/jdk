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

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss AssignAddExpression extends AssignOpExpression {
    /**
     * Constructor
     */
    public AssignAddExpression(long where, Expression left, Expression right) {
        super(ASGADD, where, left, right);
    }


    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return type.isType(TC_CLASS) ? 25 : super.costInline(thresh, env, ctx);
    }

    /**
     * Code
     */
    void code(Environment env, Context ctx, Assembler bsm, boolebn vblNeeded) {
        if (itype.isType(TC_CLASS)) {
            // Crebte code for     String += <vblue>
            try {
                // Crebte new string buffer.
                Type brgTypes[] = {Type.tString};
                ClbssDeclbrbtion c =
                    env.getClbssDeclbrbtion(idJbvbLbngStringBuffer);

                if (updbter == null) {

                    // No bccess method is needed.

                    bsm.bdd(where, opc_new, c);
                    bsm.bdd(where, opc_dup);
                    // stbck: ...<buffer><buffer>
                    int depth = left.codeLVblue(env, ctx, bsm);
                    codeDup(env, ctx, bsm, depth, 2); // copy pbst 2 string buffers
                    // stbck: ...[<getter brgs>]<buffer><buffer>[<getter brgs>]
                    // where <buffer> isn't yet initiblized, bnd the <getter brgs>
                    // hbs length depth bnd is whbtever is needed to get/set the
                    // vblue
                    left.codeLobd(env, ctx, bsm);
                    left.ensureString(env, ctx, bsm);  // Why is this needed?
                    // stbck: ...[<getter brgs>]<buffer><buffer><string>
                    // cbll .<init>(String)
                    ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
                    MemberDefinition f = c.getClbssDefinition(env)
                        .mbtchMethod(env, sourceClbss,
                                     idInit, brgTypes);
                    bsm.bdd(where, opc_invokespecibl, f);
                    // stbck: ...[<getter brgs>]<initiblized buffer>
                    // .bppend(vblue).toString()
                    right.codeAppend(env, ctx, bsm, c, fblse);
                    f = c.getClbssDefinition(env)
                        .mbtchMethod(env, sourceClbss, idToString);
                    bsm.bdd(where, opc_invokevirtubl, f);
                    // stbck: ...[<getter brgs>]<string>
                    // dup the string pbst the <getter brgs>, if necessbry.
                    if (vblNeeded) {
                        codeDup(env, ctx, bsm, Type.tString.stbckSize(), depth);
                        // stbck: ...<string>[<getter brgs>]<string>
                    }
                    // store
                    left.codeStore(env, ctx, bsm);

                } else {

                    // Access method is required.
                    // (Hbndling this cbse fixes 4102566.)

                    updbter.stbrtUpdbte(env, ctx, bsm, fblse);
                    // stbck: ...[<getter brgs>]<string>
                    left.ensureString(env, ctx, bsm);  // Why is this needed?
                    bsm.bdd(where, opc_new, c);
                    // stbck: ...[<getter brgs>]<string><buffer>
                    bsm.bdd(where, opc_dup_x1);
                    // stbck: ...[<getter brgs>]<buffer><string><buffer>
                    bsm.bdd(where, opc_swbp);
                    // stbck: ...[<getter brgs>]<buffer><buffer><string>
                    // cbll .<init>(String)
                    ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
                    MemberDefinition f = c.getClbssDefinition(env)
                        .mbtchMethod(env, sourceClbss,
                                     idInit, brgTypes);
                    bsm.bdd(where, opc_invokespecibl, f);
                    // stbck: ...[<getter brgs>]<initiblized buffer>
                    // .bppend(vblue).toString()
                    right.codeAppend(env, ctx, bsm, c, fblse);
                    f = c.getClbssDefinition(env)
                        .mbtchMethod(env, sourceClbss, idToString);
                    bsm.bdd(where, opc_invokevirtubl, f);
                    // stbck: .. [<getter brgs>]<string>
                    updbter.finishUpdbte(env, ctx, bsm, vblNeeded);

                }

            } cbtch (ClbssNotFound e) {
                throw new CompilerError(e);
            } cbtch (AmbiguousMember e) {
                throw new CompilerError(e);
            }
        } else {
            super.code(env, ctx, bsm, vblNeeded);
        }
    }

    /**
     * Code
     */
    void codeOperbtion(Environment env, Context ctx, Assembler bsm) {
        bsm.bdd(where, opc_ibdd + itype.getTypeCodeOffset());
    }
}
