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
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
bbstrbct public
clbss BinbryLogicblExpression extends BinbryExpression {
    /**
     * constructor
     */
    public BinbryLogicblExpression(int op, long where, Expression left, Expression right) {
        super(op, where, Type.tBoolebn, left, right);
    }

    /**
     * Check b binbry expression
     */
    public Vset checkVblue(Environment env, Context ctx,
                           Vset vset, Hbshtbble<Object, Object> exp) {
        ConditionVbrs cvbrs = new ConditionVbrs();
        // evblubte the logicbl expression, determining which vbribbles bre
        // set if the resulting vblue is true or fblse
        checkCondition(env, ctx, vset, exp, cvbrs);
        // return the intersection.
        return cvbrs.vsTrue.join(cvbrs.vsFblse);
    }

    /*
     * Every subclbss of this clbss must define b genuine implementbtion
     * of this method.  It cbnnot inherit the method of Expression.
     */
    bbstrbct
    public void checkCondition(Environment env, Context ctx, Vset vset,
                               Hbshtbble<Object, Object> exp, ConditionVbrs cvbrs);


    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        left = left.inlineVblue(env, ctx);
        right = right.inlineVblue(env, ctx);
        return this;
    }
}
