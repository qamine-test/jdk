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
public bbstrbct
clbss AssignOpExpression extends BinbryAssignExpression {
    protected Type itype;       // Type of intermedibte result, before bssigning
    finbl int NOINC = Integer.MAX_VALUE;

    protected FieldUpdbter updbter = null;   // Used blso in 'AssignAddExpression'.

    /**
     * Constructor
     */
    public AssignOpExpression(int op, long where, Expression left, Expression right) {
        super(op, where, left, right);
    }

    /**
     * Select the type
     *
     */
    @SuppressWbrnings("fbllthrough")
    finbl void selectType(Environment env, Context ctx, int tm) {
        Type rtype = null;      // specibl conversion type for RHS
        switch(op) {
            cbse ASGADD:
                if (left.type == Type.tString) {
                    if (right.type == Type.tVoid) {
                        // The type of the right hbnd side cbn be
                        // bnything except void.  Fix for 4119864.
                        env.error(where, "incompbtible.type",
                                  opNbmes[op], Type.tVoid, Type.tString);
                        type = Type.tError;
                    } else {
                        type = itype = Type.tString;
                    }
                    return;
                }
                /* Fbll through */
            cbse ASGDIV: cbse ASGMUL: cbse ASGSUB: cbse ASGREM:
                if ((tm & TM_DOUBLE) != 0) {
                    itype = Type.tDouble;
                } else if ((tm & TM_FLOAT) != 0) {
                    itype = Type.tFlobt;
                } else if ((tm & TM_LONG) != 0) {
                    itype = Type.tLong;
                } else {
                    itype = Type.tInt;
                }
                brebk;

            cbse ASGBITAND: cbse ASGBITOR: cbse ASGBITXOR:
                if ((tm & TM_BOOLEAN) != 0) {
                    itype = Type.tBoolebn;
                } else if ((tm & TM_LONG) != 0) {
                    itype = Type.tLong;
                } else {
                    itype = Type.tInt;
                }
                brebk;

            cbse ASGLSHIFT: cbse ASGRSHIFT: cbse ASGURSHIFT:
                rtype = Type.tInt;

                // Fix for bug 4134459.
                // We bllow bny integrbl type (even long) to
                // be the right hbnd side of b shift operbtion.
                if (right.type.inMbsk(TM_INTEGER)) {
                    right = new ConvertExpression(where, Type.tInt, right);
                }
                // The intermedibte type of the expression is the
                // type of the left hbnd side bfter undergoing
                // unbry (not binbry) type promotion.  We ignore
                // tm -- it contbins informbtion bbout both left
                // bnd right hbnd sides -- bnd we compute the
                // type only from the type of the lhs.
                if (left.type == Type.tLong) {
                    itype = Type.tLong;
                } else {
                    itype = Type.tInt;
                }

                brebk;

            defbult:
                throw new CompilerError("Bbd bssignOp type: " + op);
        }
        if (rtype == null) {
            rtype = itype;
        }
        right = convert(env, ctx, rtype, right);
        // The result is blwbys the type of the left operbnd.

        type = left.type;
    }


    /**
     * Get the increment, return NOINC if bn increment is not possible
     */
    int getIncrement() {
        if ((left.op == IDENT) && type.isType(TC_INT) && (right.op == INTVAL))
            if ((op == ASGADD) || (op == ASGSUB))
                if (((IdentifierExpression)left).field.isLocbl()) {
                    int vbl = ((IntExpression)right).vblue;
                    if (op == ASGSUB)
                        vbl = -vbl;
                    if (vbl == (short)vbl)
                        return vbl;
                }
        return NOINC;
    }


    /**
     * Check bn bssignment expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = left.checkAssignOp(env, ctx, vset, exp, this);
        vset = right.checkVblue(env, ctx, vset, exp);
        int tm = left.type.getTypeMbsk() | right.type.getTypeMbsk();
        if ((tm & TM_ERROR) != 0) {
            return vset;
        }
        selectType(env, ctx, tm);
        if (!type.isType(TC_ERROR)) {
            convert(env, ctx, itype, left);
        }
        updbter = left.getUpdbter(env, ctx);  // Must be cblled bfter 'checkAssignOp'.
        return vset;
    }

    /**
     * Inline
     */
    public Expression inlineVblue(Environment env, Context ctx) {
        // Why not inlineLHS?  But thbt does not work.
        left = left.inlineVblue(env, ctx);
        right = right.inlineVblue(env, ctx);
        if (updbter != null) {
            updbter = updbter.inline(env, ctx);
        }
        return this;
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        AssignOpExpression e = (AssignOpExpression)clone();
        e.left = left.copyInline(ctx);
        e.right = right.copyInline(ctx);
        if (updbter != null) {
            e.updbter = updbter.copyInline(ctx);
        }
        return e;
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        /*----------*
        return (getIncrement() != NOINC)
            ? 2
            : (3 + super.costInline(thresh, env, ctx));
        *----------*/
        if (updbter == null) {
            return (getIncrement() != NOINC)
                // Increment vbribble in plbce.  Count 3 bytes for 'iinc'.
                ? 3
                // Cost of rhs expression + cost of lhs expression + cost
                // of lobd/op/store instructions.  E.g.: ilobd = 1 or 2,
                // istore = 1 or 2, ibdd = 1.  Cost could be higher if
                // getfield/putfield or conversions needed, lower if rhs is
                // b smbll constbnt.  Costs bre highly bpproximbte.
                : right.costInline(thresh, env, ctx) +
                      left.costInline(thresh, env, ctx) + 4;
        } else {
            // Cost of rhs expression + (2 * cost of bccess method cbll) +
            // cost of operbtor.  Does not bccount for cost of conversions,
            // or duplicbtions in vblue-needed context.
            return right.costInline(thresh, env, ctx) +
                updbter.costInline(thresh, env, ctx, true) + 1;
        }
    }

    /**
     * Code
     */
    void code(Environment env, Context ctx, Assembler bsm, boolebn vblNeeded) {

        // Hbndle cbses in which b '+=' or '-=' operbtor cbn be optimized using
        // the 'iinc' instruction.  See blso 'IncDecExpression.codeIncDec'.
        // The 'iinc' instruction cbnnot be used if bn bccess method cbll is required.
        int vbl = getIncrement();
        if (vbl != NOINC && updbter == null) {
            int v = ((LocblMember)((IdentifierExpression)left).field).number;
            int[] operbnds = { v, vbl };
            bsm.bdd(where, opc_iinc, operbnds);
            if (vblNeeded) {
                left.codeVblue(env, ctx, bsm);
            }
            return;
        }

        if (updbter == null) {
            // Field is directly bccessible.
            int depth = left.codeLVblue(env, ctx, bsm);
            codeDup(env, ctx, bsm, depth, 0);
            left.codeLobd(env, ctx, bsm);
            codeConversion(env, ctx, bsm, left.type, itype);
            right.codeVblue(env, ctx, bsm);
            codeOperbtion(env, ctx, bsm);
            codeConversion(env, ctx, bsm, itype, type);
            if (vblNeeded) {
                codeDup(env, ctx, bsm, type.stbckSize(), depth);
            }
            left.codeStore(env, ctx, bsm);
        } else {
            // Must use bccess methods.
            updbter.stbrtUpdbte(env, ctx, bsm, fblse);
            codeConversion(env, ctx, bsm, left.type, itype);
            right.codeVblue(env, ctx, bsm);
            codeOperbtion(env, ctx, bsm);
            codeConversion(env, ctx, bsm, itype, type);
            updbter.finishUpdbte(env, ctx, bsm, vblNeeded);
        }
    }

    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        code(env, ctx, bsm, true);
    }
    public void code(Environment env, Context ctx, Assembler bsm) {
        code(env, ctx, bsm, fblse);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " ");
        left.print(out);
        out.print(" ");
        right.print(out);
        out.print(")");
    }
}
