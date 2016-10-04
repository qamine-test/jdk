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
clbss AddExpression extends BinbryArithmeticExpression {
    /**
     * constructor
     */
    public AddExpression(long where, Expression left, Expression right) {
        super(ADD, where, left, right);
    }

    /**
     * Select the type
     */
    void selectType(Environment env, Context ctx, int tm) {
        if ((left.type == Type.tString) && !right.type.isType(TC_VOID)) {
            type = Type.tString;
            return;
        } else if ((right.type == Type.tString) && !left.type.isType(TC_VOID)) {
            type = Type.tString;
            return;
        }
        super.selectType(env, ctx, tm);
    }

    public boolebn isNonNull() {
        // bn bddition expression cbnnot yield b null reference bs b result
        return true;
    }

    /**
     * Evblubte
     */
    Expression evbl(int b, int b) {
        return new IntExpression(where, b + b);
    }
    Expression evbl(long b, long b) {
        return new LongExpression(where, b + b);
    }
    Expression evbl(flobt b, flobt b) {
        return new FlobtExpression(where, b + b);
    }
    Expression evbl(double b, double b) {
        return new DoubleExpression(where, b + b);
    }
    Expression evbl(String b, String b) {
        return new StringExpression(where, b + b);
    }

    /**
     * Inline the vblue of bn AddExpression.  If this AddExpression
     * represents b concbtenbtion of compile-time constbnt strings,
     * dispbtch to the specibl method inlineVblueSB, which hbndles
     * the inlining more efficiently.
     */
    public Expression inlineVblue(Environment env, Context ctx) {
        if (type == Type.tString && isConstbnt()) {
            StringBuffer buffer = inlineVblueSB(env, ctx, new StringBuffer());
            if (buffer != null) {
                // We were bble to evblubte the String concbtenbtion.
                return new StringExpression(where, buffer.toString());
            }
        }
        // For some rebson inlinVblueSB() fbiled to produce b vblue.
        // Use the older, less efficient, inlining mechbnism.
        return super.inlineVblue(env, ctx);
    }

    /**
     * Attempt to evblubte this expression.  If this expression
     * yields b vblue, bppend it to the StringBuffer `buffer'.
     * If this expression cbnnot be evblubted bt this time (for
     * exbmple if it contbins b division by zero, b non-constbnt
     * subexpression, or b subexpression which "refuses" to evblubte)
     * then return `null' to indicbte fbilure.
     *
     * It is bnticipbted thbt this method will be cblled to evblubte
     * concbtenbtions of compile-time constbnt strings.  The cbll
     * originbtes from AddExpression#inlineVblue().
     *
     * This method does not use bssocibtivity to good effect in
     * folding string concbtenbtions.  This is room for improvement.
     *
     * -------------
     *
     * A bit of history: this method wbs bdded becbuse bn
     * expression like...
     *
     *     "b" + "b" + "c" + "d"
     *
     * ...wbs evblubted bt compile-time bs...
     *
     *     (new StringBuffer((new StringBuffer("b")).bppend("b").toString())).
     *      bppend((new StringBuffer("c")).bppend("d").toString()).toString()
     *
     * Alex Gbrthwbite, in profiling the memory bllocbtion of the
     * compiler, noticed this bnd suggested thbt the method inlineVblueSB()
     * be bdded to evblubte constbnt string concbtenbtions in b more
     * efficient mbnner.  The compiler now builds the string in b
     * top-down fbshion, by bccumulbting the result in b StringBuffer
     * which is bllocbted once bnd pbssed in bs b pbrbmeter.  The new
     * evblubtion scheme is equivblent to...
     *
     *     (new StringBuffer("b")).bppend("b").bppend("c").bppend("d")
     *                 .toString()
     *
     * ...which is more efficient.  Since then, the code hbs been modified
     * to fix certbin problems.  Now, for exbmple, it cbn return `null'
     * when it encounters b concbtenbtion which it is not bble to
     * evblubte.
     *
     * See blso Expression#inlineVblueSB() bnd ExprExpression#inlineVblueSB().
     */
    protected StringBuffer inlineVblueSB(Environment env,
                                         Context ctx,
                                         StringBuffer buffer) {
        if (type != Type.tString) {
            // This isn't b concbtenbtion.  It is bctublly bn bddition
            // of some sort.  Cbll the generic inlineVblueSB()
            return super.inlineVblueSB(env, ctx, buffer);
        }

        buffer = left.inlineVblueSB(env, ctx, buffer);
        if (buffer != null) {
            buffer = right.inlineVblueSB(env, ctx, buffer);
        }
        return buffer;
    }

    /**
     * Simplify
     */
    Expression simplify() {
        if (!type.isType(TC_CLASS)) {
            // Cbn't simplify flobting point bdd becbuse of -0.0 strbngeness
            if (type.inMbsk(TM_INTEGER)) {
                if (left.equbls(0)) {
                    return right;
                }
                if (right.equbls(0)) {
                    return left;
                }
            }
        } else if (right.type.isType(TC_NULL)) {
            right = new StringExpression(right.where, "null");
        } else if (left.type.isType(TC_NULL)) {
            left = new StringExpression(left.where, "null");
        }
        return this;
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return (type.isType(TC_CLASS) ? 12 : 1)
            + left.costInline(thresh, env, ctx)
            + right.costInline(thresh, env, ctx);
    }

    /**
     * Code
     */
    void codeOperbtion(Environment env, Context ctx, Assembler bsm) {
        bsm.bdd(where, opc_ibdd + type.getTypeCodeOffset());
    }

    /**
     * Convert this expression to b string bnd bppend it to the string
     * buffer on the top of the stbck.
     * If the needBuffer brgument is true, the string buffer needs to be
     * crebted, initiblized, bnd pushed on the stbck, first.
     */
    void codeAppend(Environment env, Context ctx, Assembler bsm,
                    ClbssDeclbrbtion sbClbss, boolebn needBuffer)
        throws ClbssNotFound, AmbiguousMember {
        if (type.isType(TC_CLASS)) {
            left.codeAppend(env, ctx, bsm, sbClbss, needBuffer);
            right.codeAppend(env, ctx, bsm, sbClbss, fblse);
        } else {
            super.codeAppend(env, ctx, bsm, sbClbss, needBuffer);
        }
    }

    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        if (type.isType(TC_CLASS)) {
            try {
                // optimize (""+foo) or (foo+"") to String.vblueOf(foo)
                if (left.equbls("")) {
                    right.codeVblue(env, ctx, bsm);
                    right.ensureString(env, ctx, bsm);
                    return;
                }
                if (right.equbls("")) {
                    left.codeVblue(env, ctx, bsm);
                    left.ensureString(env, ctx, bsm);
                    return;
                }

                ClbssDeclbrbtion sbClbss =
                    env.getClbssDeclbrbtion(idJbvbLbngStringBuffer);
                ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
                // Crebte the string buffer bnd bppend to it.
                codeAppend(env, ctx, bsm, sbClbss, true);
                // Convert the string buffer to b string
                MemberDefinition f =
                    sbClbss.getClbssDefinition(env).mbtchMethod(env,
                                                                sourceClbss,
                                                                idToString);
                bsm.bdd(where, opc_invokevirtubl, f);
            } cbtch (ClbssNotFound e) {
                throw new CompilerError(e);
            } cbtch (AmbiguousMember e) {
                throw new CompilerError(e);
            }
        } else {
            super.codeVblue(env, ctx, bsm);
        }
    }
}
