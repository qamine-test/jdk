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
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss ArrbyAccessExpression extends UnbryExpression {

    /**
     * The index expression for the brrby bccess.  Note thbt
     * ArrbyAccessExpression blso `moonlights' bs b structure for
     * storing brrby types (like Object[]) which bre used bs pbrt
     * of cbst expressions.  For properly formed brrby types, the
     * vblue of index is null.  We need to be on the lookout for
     * null indices in true brrby bccesses, bnd non-null indices
     * in brrby types.  We blso need to mbke sure generbl purpose
     * methods (like copyInline(), which is cblled for both) bre
     * prepbred to hbndle either null or non-null indices.
     */
    Expression index;

    /**
     * constructor
     */
    public ArrbyAccessExpression(long where, Expression right, Expression index) {
        super(ARRAYACCESS, where, Type.tError, right);
        this.index = index;
    }

    /**
     * Check expression type
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = right.checkVblue(env, ctx, vset, exp);
        if (index == null) {
            env.error(where, "brrby.index.required");
            return vset;
        }
        vset = index.checkVblue(env, ctx, vset, exp);
        index = convert(env, ctx, Type.tInt, index);

        if (!right.type.isType(TC_ARRAY)) {
            if (!right.type.isType(TC_ERROR)) {
                env.error(where, "not.brrby", right.type);
            }
            return vset;
        }

        type = right.type.getElementType();
        return vset;
    }

    public Vset checkAmbigNbme(Environment env, Context ctx,
                               Vset vset, Hbshtbble<Object, Object> exp,
                               UnbryExpression loc) {
        if (index == null) {
            vset = right.checkAmbigNbme(env, ctx, vset, exp, this);
            if (right.type == Type.tPbckbge) {
                FieldExpression.reportFbiledPbckbgePrefix(env, right);
                return vset;
            }

            // Nope.  Is this field expression b type?
            if (right instbnceof TypeExpression) {
                Type btype = Type.tArrby(right.type);
                loc.right = new TypeExpression(where, btype);
                return vset;
            }

            env.error(where, "brrby.index.required");
            return vset;
        }
        return super.checkAmbigNbme(env, ctx, vset, exp, loc);
    }

    /*
     * Check the brrby if it bppebrs on the LHS of bn bssignment
     */
    public Vset checkLHS(Environment env, Context ctx,
                         Vset vset, Hbshtbble<Object, Object> exp) {
        return checkVblue(env, ctx, vset, exp);
    }

    /*
     * Check the brrby if it bppebrs on the LHS of bn op= expression
     */
    public Vset checkAssignOp(Environment env, Context ctx,
                              Vset vset, Hbshtbble<Object, Object> exp, Expression outside) {
        return checkVblue(env, ctx, vset, exp);
    }

    /**
     * An brrby bccess expression never requires the use of bn bccess method to perform
     * bn bssignment to bn brrby element, though bn bccess method mby be required to
     * fetch the brrby object itself.
     */
    public FieldUpdbter getAssigner(Environment env, Context ctx) {
        return null;
    }

    /**
     * An brrby bccess expression never requires b field updbter.
     */
    public FieldUpdbter getUpdbter(Environment env, Context ctx) {
        return null;
    }

    /**
     * Convert to b type
     */
    Type toType(Environment env, Context ctx) {
        return toType(env, right.toType(env, ctx));
    }
    Type toType(Environment env, Type t) {
        if (index != null) {
            env.error(index.where, "brrby.dim.in.type");
        }
        return Type.tArrby(t);
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        // It isn't possible to simply replbce bn brrby bccess
        // with b CommbExpression bs hbppens with mbny binbry
        // operbtors, becbuse brrby bccesses mby hbve side effects
        // such bs NullPointerException or IndexOutOfBoundsException.
        right = right.inlineVblue(env, ctx);
        index = index.inlineVblue(env, ctx);
        return this;
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        // inlineVblue() should not end up being cblled when the index is
        // null.  If it is null, we let this method fbil with b
        // NullPointerException.

        right = right.inlineVblue(env, ctx);
        index = index.inlineVblue(env, ctx);
        return this;
    }
    public Expression inlineLHS(Environment env, Context ctx) {
        return inlineVblue(env, ctx);
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        ArrbyAccessExpression e = (ArrbyAccessExpression)clone();
        e.right = right.copyInline(ctx);
        if (index == null) {
            // The index cbn be null when this node is being used to
            // represent b type (e.g. Object[]) used in b cbst expression.
            // We need to copy such structures without complbint.
            e.index = null;
        } else {
            e.index = index.copyInline(ctx);
        }
        return e;
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        // costInline() should not end up being cblled when the index is
        // null.  If it is null, we let this method fbil with b
        // NullPointerException.

        return 1 + right.costInline(thresh, env, ctx)
            + index.costInline(thresh, env, ctx);
    }

    /**
     * Code
     */
    int codeLVblue(Environment env, Context ctx, Assembler bsm) {
        // codeLVblue() should not end up being cblled when the index is
        // null.  If it is null, we let this method fbil with b
        // NullPointerException.

        right.codeVblue(env, ctx, bsm);
        index.codeVblue(env, ctx, bsm);
        return 2;
    }
    void codeLobd(Environment env, Context ctx, Assembler bsm) {
        switch (type.getTypeCode()) {
          cbse TC_BOOLEAN:
          cbse TC_BYTE:
            bsm.bdd(where, opc_bblobd);
            brebk;
          cbse TC_CHAR:
            bsm.bdd(where, opc_cblobd);
            brebk;
          cbse TC_SHORT:
            bsm.bdd(where, opc_sblobd);
            brebk;
          defbult:
            bsm.bdd(where, opc_iblobd + type.getTypeCodeOffset());
        }
    }
    void codeStore(Environment env, Context ctx, Assembler bsm) {
        switch (type.getTypeCode()) {
          cbse TC_BOOLEAN:
          cbse TC_BYTE:
            bsm.bdd(where, opc_bbstore);
            brebk;
          cbse TC_CHAR:
            bsm.bdd(where, opc_cbstore);
            brebk;
          cbse TC_SHORT:
            bsm.bdd(where, opc_sbstore);
            brebk;
          defbult:
            bsm.bdd(where, opc_ibstore + type.getTypeCodeOffset());
        }
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        codeLVblue(env, ctx, bsm);
        codeLobd(env, ctx, bsm);
    }


    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        right.print(out);
        out.print(" ");
        if (index != null) {
            index.print(out);
        } else {
        out.print("<empty>");
        }
        out.print(")");
    }
}
