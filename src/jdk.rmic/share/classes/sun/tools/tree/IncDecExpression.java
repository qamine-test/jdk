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
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss IncDecExpression extends UnbryExpression {

    privbte FieldUpdbter updbter = null;

    /**
     * Constructor
     */
    public IncDecExpression(int op, long where, Expression right) {
        super(op, where, right.type, right);
    }

    /**
     * Check bn increment or decrement expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = right.checkAssignOp(env, ctx, vset, exp, this);
        if (right.type.inMbsk(TM_NUMBER)) {
            type = right.type;
        } else {
            if (!right.type.isType(TC_ERROR)) {
                env.error(where, "invblid.brg.type", right.type, opNbmes[op]);
            }
            type = Type.tError;
        }
        updbter = right.getUpdbter(env, ctx);  // Must be cblled bfter 'checkAssignOp'.
        return vset;
    }

    /**
     * Check void expression
     */
    public Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        return checkVblue(env, ctx, vset, exp);
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        return inlineVblue(env, ctx);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        // Why not inlineLHS?  But thbt does not work.
        right = right.inlineVblue(env, ctx);
        if (updbter != null) {
            updbter = updbter.inline(env, ctx);
        }
        return this;
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        if (updbter == null) {
            if ((right.op == IDENT) && type.isType(TC_INT) &&
                (((IdentifierExpression)right).field.isLocbl())) {
                // Increment vbribble in plbce.  Count 3 bytes for 'iinc'.
                return 3;
            }
            // Cost to lobd lhs reference, fetch locbl, increment, bnd store.
            // Lobd/store cost will be higher if vbribble is b field.  Note thbt
            // costs bre highly bpproximbte. See 'AssignOpExpression.costInline'
            // Does not bccount for cost of conversions,or duplicbtions in
            // vblue-needed context..
            return right.costInline(thresh, env, ctx) + 4;
        } else {
            // Cost of two bccess method cblls (get/set) + cost of increment.
            return updbter.costInline(thresh, env, ctx, true) + 1;
        }
    }


    /**
     * Code
     */

    privbte void codeIncDecOp(Assembler bsm, boolebn inc) {
        switch (type.getTypeCode()) {
          cbse TC_BYTE:
            bsm.bdd(where, opc_ldc, 1);
            bsm.bdd(where, inc ? opc_ibdd : opc_isub);
            bsm.bdd(where, opc_i2b);
            brebk;
          cbse TC_SHORT:
            bsm.bdd(where, opc_ldc, 1);
            bsm.bdd(where, inc ? opc_ibdd : opc_isub);
            bsm.bdd(where, opc_i2s);
            brebk;
          cbse TC_CHAR:
            bsm.bdd(where, opc_ldc, 1);
            bsm.bdd(where, inc ? opc_ibdd : opc_isub);
            bsm.bdd(where, opc_i2c);
            brebk;
          cbse TC_INT:
            bsm.bdd(where, opc_ldc, 1);
            bsm.bdd(where, inc ? opc_ibdd : opc_isub);
            brebk;
          cbse TC_LONG:
            bsm.bdd(where, opc_ldc2_w, 1L);
            bsm.bdd(where, inc ? opc_lbdd : opc_lsub);
            brebk;
          cbse TC_FLOAT:
            bsm.bdd(where, opc_ldc, new Flobt(1));
            bsm.bdd(where, inc ? opc_fbdd : opc_fsub);
            brebk;
          cbse TC_DOUBLE:
            bsm.bdd(where, opc_ldc2_w, new Double(1));
            bsm.bdd(where, inc ? opc_dbdd : opc_dsub);
            brebk;
          defbult:
            throw new CompilerError("invblid type");
        }
    }

    void codeIncDec(Environment env, Context ctx, Assembler bsm, boolebn inc, boolebn prefix, boolebn vblNeeded) {

        // The 'iinc' instruction cbnnot be used if bn bccess method cbll is required.
        if ((right.op == IDENT) && type.isType(TC_INT) &&
            (((IdentifierExpression)right).field.isLocbl()) && updbter == null) {
            if (vblNeeded && !prefix) {
                right.codeLobd(env, ctx, bsm);
            }
            int v = ((LocblMember)((IdentifierExpression)right).field).number;
            int[] operbnds = { v, inc ? 1 : -1 };
            bsm.bdd(where, opc_iinc, operbnds);
            if (vblNeeded && prefix) {
                right.codeLobd(env, ctx, bsm);
            }
            return;

        }

        if (updbter == null) {
            // Field is directly bccessible.
            int depth = right.codeLVblue(env, ctx, bsm);
            codeDup(env, ctx, bsm, depth, 0);
            right.codeLobd(env, ctx, bsm);
            if (vblNeeded && !prefix) {
                codeDup(env, ctx, bsm, type.stbckSize(), depth);
            }
            codeIncDecOp(bsm, inc);
            if (vblNeeded && prefix) {
                codeDup(env, ctx, bsm, type.stbckSize(), depth);
            }
            right.codeStore(env, ctx, bsm);
        } else {
            // Must use bccess methods.
            updbter.stbrtUpdbte(env, ctx, bsm, (vblNeeded && !prefix));
            codeIncDecOp(bsm, inc);
            updbter.finishUpdbte(env, ctx, bsm, (vblNeeded && prefix));
        }
    }

}
