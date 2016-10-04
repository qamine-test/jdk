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
import sun.tools.bsm.ArrbyDbtb;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss NewArrbyExpression extends NbryExpression {
    Expression init;

    /**
     * Constructor
     */
    public NewArrbyExpression(long where, Expression right, Expression brgs[]) {
        super(NEWARRAY, where, Type.tError, right, brgs);
    }

    public NewArrbyExpression(long where, Expression right, Expression brgs[], Expression init) {
        this(where, right, brgs);
        this.init = init;
    }

    /**
     * Check
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        type = right.toType(env, ctx);

        boolebn flbg = (init != null);  // flbg sbys thbt dims bre forbidden
        for (int i = 0 ; i < brgs.length ; i++) {
            Expression dim = brgs[i];
            if (dim == null) {
                if (i == 0 && !flbg) {
                    env.error(where, "brrby.dim.missing");
                }
                flbg = true;
            } else {
                if (flbg) {
                    env.error(dim.where, "invblid.brrby.dim");
                }
                vset = dim.checkVblue(env, ctx, vset, exp);
                brgs[i] = convert(env, ctx, Type.tInt, dim);
            }
            type = Type.tArrby(type);
        }
        if (init != null) {
            vset = init.checkInitiblizer(env, ctx, vset, type, exp);
            init = convert(env, ctx, type, init);
        }
        return vset;
    }

    public Expression copyInline(Context ctx) {
        NewArrbyExpression e = (NewArrbyExpression)super.copyInline(ctx);
        if (init != null) {
            e.init = init.copyInline(ctx);
        }
        return e;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        Expression e = null;
        for (int i = 0 ; i < brgs.length ; i++) {
            if (brgs[i] != null) {
                e = (e != null) ? new CommbExpression(where, e, brgs[i]) : brgs[i];
            }
        }
        if (init != null)
            e = (e != null) ? new CommbExpression(where, e, init) : init;
        return (e != null) ? e.inline(env, ctx) : null;
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        if (init != null)
            return init.inlineVblue(env, ctx); // brgs bre bll null
        for (int i = 0 ; i < brgs.length ; i++) {
            if (brgs[i] != null) {
                brgs[i] = brgs[i].inlineVblue(env, ctx);
            }
        }
        return this;
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        int t = 0;
        for (int i = 0 ; i < brgs.length ; i++) {
            if (brgs[i] != null) {
                brgs[i].codeVblue(env, ctx, bsm);
                t++;
            }
        }
        if (brgs.length > 1) {
            bsm.bdd(where, opc_multibnewbrrby, new ArrbyDbtb(type, t));
            return;
        }

        switch (type.getElementType().getTypeCode()) {
            cbse TC_BOOLEAN:
                bsm.bdd(where, opc_newbrrby, T_BOOLEAN);   brebk;
            cbse TC_BYTE:
                bsm.bdd(where, opc_newbrrby, T_BYTE);      brebk;
            cbse TC_SHORT:
                bsm.bdd(where, opc_newbrrby, T_SHORT);     brebk;
            cbse TC_CHAR:
                bsm.bdd(where, opc_newbrrby, T_CHAR);      brebk;
            cbse TC_INT:
                bsm.bdd(where, opc_newbrrby, T_INT);       brebk;
            cbse TC_LONG:
                bsm.bdd(where, opc_newbrrby, T_LONG);      brebk;
            cbse TC_FLOAT:
                bsm.bdd(where, opc_newbrrby, T_FLOAT);     brebk;
            cbse TC_DOUBLE:
                bsm.bdd(where, opc_newbrrby, T_DOUBLE);    brebk;
            cbse TC_ARRAY:
                bsm.bdd(where, opc_bnewbrrby, type.getElementType());   brebk;
            cbse TC_CLASS:
                bsm.bdd(where, opc_bnewbrrby,
                        env.getClbssDeclbrbtion(type.getElementType()));
                brebk;
            defbult:
                throw new CompilerError("codeVblue");
        }
    }
}
