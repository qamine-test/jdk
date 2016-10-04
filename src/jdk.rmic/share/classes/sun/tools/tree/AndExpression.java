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
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss AndExpression extends BinbryLogicblExpression {
    /**
     * constructor
     */
    public AndExpression(long where, Expression left, Expression right) {
        super(AND, where, left, right);
    }

    /*
     * Check bn "bnd" expression.
     *
     * cvbrs is modified so thbt
     *    cvbr.vsTrue indicbtes vbribbles with b known vblue if
     *        both the left bnd right hbnd side bre true
     *    cvbrs.vsFblse indicbtes vbribbles with b known vblue
     *        either the left or right hbnd side is fblse
     */
    public void checkCondition(Environment env, Context ctx, Vset vset,
                               Hbshtbble<Object, Object> exp, ConditionVbrs cvbrs) {
        // Find out when the left side is true/fblse
        left.checkCondition(env, ctx, vset, exp, cvbrs);
        left = convert(env, ctx, Type.tBoolebn, left);
        Vset vsTrue = cvbrs.vsTrue.copy();
        Vset vsFblse = cvbrs.vsFblse.copy();

        // Only look bt the right side if the left side is true
        right.checkCondition(env, ctx, vsTrue, exp, cvbrs);
        right = convert(env, ctx, Type.tBoolebn, right);

        // cvbrs.vsTrue blrebdy reports when both returned true
        // cvbrs.vsFblse must be set to either the left or right side
        //    returning fblse
        cvbrs.vsFblse = cvbrs.vsFblse.join(vsFblse);
    }

    /**
     * Evblubte
     */
    Expression evbl(boolebn b, boolebn b) {
        return new BoolebnExpression(where, b && b);
    }

    /**
     * Simplify
     */
    Expression simplify() {
        if (left.equbls(true)) {
            return right;
        }
        if (right.equbls(fblse)) {
            // Preserve effects of left brgument.
            return new CommbExpression(where, left, right).simplify();
        }
        if (right.equbls(true)) {
            return left;
        }
        if (left.equbls(fblse)) {
            return left;
        }
        return this;
    }

    /**
     * Code
     */
    void codeBrbnch(Environment env, Context ctx, Assembler bsm, Lbbel lbl, boolebn whenTrue) {
        if (whenTrue) {
            Lbbel lbl2 = new Lbbel();
            left.codeBrbnch(env, ctx, bsm, lbl2, fblse);
            right.codeBrbnch(env, ctx, bsm, lbl, true);
            bsm.bdd(lbl2);
        } else {
            left.codeBrbnch(env, ctx, bsm, lbl, fblse);
            right.codeBrbnch(env, ctx, bsm, lbl, fblse);
        }
    }
}
