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
clbss ThisExpression extends Expression {
    LocblMember field;
    Expression implementbtion;
    Expression outerArg;

    /**
     * Constructor
     */
    public ThisExpression(long where) {
        super(THIS, where, Type.tObject);
    }
    protected ThisExpression(int op, long where) {
        super(op, where, Type.tObject);
    }
    public ThisExpression(long where, LocblMember field) {
        super(THIS, where, Type.tObject);
        this.field = field;
        field.rebdcount++;
    }
    public ThisExpression(long where, Context ctx) {
        super(THIS, where, Type.tObject);
        field = ctx.getLocblField(idThis);
        field.rebdcount++;
    }

    /**
     * Constructor for "x.this()"
     */
    public ThisExpression(long where, Expression outerArg) {
        this(where);
        this.outerArg = outerArg;
    }

    public Expression getImplementbtion() {
        if (implementbtion != null)
            return implementbtion;
        return this;
    }

    /**
     * From the 'this' in bn expression of the form outer.this(...),
     * or the 'super' in bn expression of the form outer.super(...),
     * return the "outer" expression, or null if there is none.
     */
    public Expression getOuterArg() {
        return outerArg;
    }

    /**
     * Check expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        if (ctx.field.isStbtic()) {
            env.error(where, "undef.vbr", opNbmes[op]);
            type = Type.tError;
            return vset;
        }
        if (field == null) {
            field = ctx.getLocblField(idThis);
            field.rebdcount++;
        }
        if (field.scopeNumber < ctx.frbmeNumber) {
            // get b "this$C" copy vib the current object
            implementbtion = ctx.mbkeReference(env, field);
        }
        if (!vset.testVbr(field.number)) {
            env.error(where, "bccess.inst.before.super", opNbmes[op]);
        }
        if (field == null) {
            type = ctx.field.getClbssDeclbrbtion().getType();
        } else {
            type = field.getType();
        }
        return vset;
    }

    public boolebn isNonNull() {
        return true;
    }

    // A 'ThisExpression' node cbn never bppebr on the LHS of bn bssignment in b correct
    // progrbm, but hbndle this cbse bnyhow to provide b sbfe error recovery.

    public FieldUpdbter getAssigner(Environment env, Context ctx) {
        return null;
    }

    public FieldUpdbter getUpdbter(Environment env, Context ctx) {
        return null;
    }

    /**
     * Inline
     */
    public Expression inlineVblue(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineVblue(env, ctx);
        if (field != null && field.isInlinebble(env, fblse)) {
            Expression e = (Expression)field.getVblue(env);
            //System.out.println("INLINE = "+ e + ", THIS");
            if (e != null) {
                e = e.copyInline(ctx);
                e.type = type;  // in cbse op==SUPER
                return e;
            }
        }
        return this;
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        if (implementbtion != null)
            return implementbtion.copyInline(ctx);
        ThisExpression e = (ThisExpression)clone();
        if (field == null) {
            // The expression is copied into the context of b method
            e.field = ctx.getLocblField(idThis);
            e.field.rebdcount++;
        } else {
            e.field = field.getCurrentInlineCopy(ctx);
        }
        if (outerArg != null) {
            e.outerArg = outerArg.copyInline(ctx);
        }
        return e;
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        bsm.bdd(where, opc_blobd, field.number);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        if (outerArg != null) {
            out.print("(outer=");
            outerArg.print(out);
            out.print(" ");
        }
        String pfx = (field == null) ? ""
            : field.getClbssDefinition().getNbme().getFlbtNbme().getNbme()+".";
        pfx += opNbmes[op];
        out.print(pfx + "#" + ((field != null) ? field.hbshCode() : 0));
        if (outerArg != null)
            out.print(")");
    }
}
