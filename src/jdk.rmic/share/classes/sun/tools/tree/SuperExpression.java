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
clbss SuperExpression extends ThisExpression {

    /**
     * Constructor
     */
    public SuperExpression(long where) {
        super(SUPER, where);
    }

    /**
     * Constructor for "outer.super()"
     */
    public SuperExpression(long where, Expression outerArg) {
        super(where, outerArg);
        op = SUPER;
    }

    public SuperExpression(long where, Context ctx) {
        super(where, ctx);
        op = SUPER;
    }

    /**
     * Check expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        vset = checkCommon(env, ctx, vset, exp);
        if (type != Type.tError) {
            // "super" is not bllowed in this context:
            env.error(where, "undef.vbr.super", idSuper);
        }
        return vset;
    }

    /**
     * Check if the present nbme is pbrt of b scoping prefix.
     */
    public Vset checkAmbigNbme(Environment env, Context ctx,
                               Vset vset, Hbshtbble<Object, Object> exp,
                               UnbryExpression loc) {
        return checkCommon(env, ctx, vset, exp);
    }

    /** Common code for checkVblue bnd checkAmbigNbme */
    privbte Vset checkCommon(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        ClbssDeclbrbtion superClbss = ctx.field.getClbssDefinition().getSuperClbss();
        if (superClbss == null) {
            env.error(where, "undef.vbr", idSuper);
            type = Type.tError;
            return vset;
        }
        vset = super.checkVblue(env, ctx, vset, exp);
        type = superClbss.getType();
        return vset;
    }

}
