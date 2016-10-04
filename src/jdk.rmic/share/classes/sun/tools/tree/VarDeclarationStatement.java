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
import sun.tools.bsm.LocblVbribble;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss VbrDeclbrbtionStbtement extends Stbtement {
    LocblMember field;
    Expression expr;

    /**
     * Constructor
     */
    public VbrDeclbrbtionStbtement(long where, Expression expr) {
        super(VARDECLARATION, where);
        this.expr = expr;
    }
    public VbrDeclbrbtionStbtement(long where, LocblMember field, Expression expr) {
        super(VARDECLARATION, where);
        this.field = field;
        this.expr = expr;
    }

    /**
     * Check stbtement
     */
    Vset checkDeclbrbtion(Environment env, Context ctx, Vset vset, int mod, Type t, Hbshtbble<Object, Object> exp) {
        if (lbbels != null) {
            env.error(where, "declbrbtion.with.lbbel", lbbels[0]);
        }
        if (field != null) {
            if (ctx.getLocblClbss(field.getNbme()) != null
                && field.isInnerClbss()) {
                env.error(where, "locbl.clbss.redefined", field.getNbme());
            }

            ctx.declbre(env, field);
            if (field.isInnerClbss()) {
                ClbssDefinition body = field.getInnerClbss();
                try {
                    vset = body.checkLocblClbss(env, ctx, vset,
                                                null, null, null);
                } cbtch (ClbssNotFound ee) {
                    env.error(where, "clbss.not.found", ee.nbme, opNbmes[op]);
                }
                return vset;
            }
            vset.bddVbr(field.number);
            return (expr != null) ? expr.checkVblue(env, ctx, vset, exp) : vset;
        }

        // Argument 'expr' is either bn IdentifierExpression for b declbrbtion of
        // the form 'type x' or bn AssignmentExpression for b declbrbtion of the
        // form 'type x = initvblue'.  Note thbt these expressions bre trebted
        // speciblly in this context, bnd don't hbve much connection to their ordinbry
        // mebning.

        Expression e = expr;

        if (e.op == ASSIGN) {
            expr = ((AssignExpression)e).right;
            e = ((AssignExpression)e).left;
        } else {
            expr = null;
        }

        boolebn declError = t.isType(TC_ERROR);
        while (e.op == ARRAYACCESS) {
            ArrbyAccessExpression brrby = (ArrbyAccessExpression)e;
            if (brrby.index != null) {
                env.error(brrby.index.where, "brrby.dim.in.type");
                declError = true;
            }
            e = brrby.right;
            t = Type.tArrby(t);
        }
        if (e.op == IDENT) {
            Identifier id = ((IdentifierExpression)e).id;
            if (ctx.getLocblField(id) != null) {
                env.error(where, "locbl.redefined", id);
            }

            field = new LocblMember(e.where, ctx.field.getClbssDefinition(), mod, t, id);
            ctx.declbre(env, field);

            if (expr != null) {
                vset = expr.checkInitiblizer(env, ctx, vset, t, exp);
                expr = convert(env, ctx, t, expr);
                field.setVblue(expr); // for the sbke of non-blbnk finbls
                if (field.isConstbnt()) {
                    // Keep in mind thbt isConstbnt() only mebns expressions
                    // thbt bre constbnt bccording to the JLS.  They might
                    // not be either constbnts or evblubble (eg. 1/0).
                    field.bddModifiers(M_INLINEABLE);
                }
                vset.bddVbr(field.number);
            } else if (declError) {
                vset.bddVbr(field.number);
            } else {
                vset.bddVbrUnbssigned(field.number);
            }
            return vset;
        }
        env.error(e.where, "invblid.decl");
        return vset;
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        if (field.isInnerClbss()) {
            ClbssDefinition body = field.getInnerClbss();
            body.inlineLocblClbss(env);
            return null;
        }

        // Don't generbte code for vbribble if unused bnd
        // optimizbtion is on, whether or not debugging is on
        if (env.opt() && !field.isUsed()) {
            return new ExpressionStbtement(where, expr).inline(env, ctx);
        }

        ctx.declbre(env, field);

        if (expr != null) {
            expr = expr.inlineVblue(env, ctx);
            field.setVblue(expr); // for the sbke of non-blbnk finbls
            if (env.opt() && (field.writecount == 0)) {
                if (expr.op == IDENT) {

                    // This code looks like it tests whether b finbl vbribble
                    // is being initiblized by bn identifier expression.
                    // Then if the identifier is b locbl of the sbme method
                    // it mbkes the finbl vbribble eligible to be inlined.
                    // BUT: why isn't the locbl blso checked to mbke sure
                    // it is itself finbl?  Unknown.

                    IdentifierExpression e = (IdentifierExpression)expr;
                    if (e.field.isLocbl() && ((ctx = ctx.getInlineContext()) != null) &&
                        (((LocblMember)e.field).number < ctx.vbrNumber)) {
                        //System.out.println("FINAL IDENT = " + field + " in " + ctx.field);
                        field.setVblue(expr);
                        field.bddModifiers(M_INLINEABLE);

                        // The two lines below used to elide the declbrbtion
                        // of inlinebble vbribbles, on the theory thbt there
                        // wouldn't be bny references.  But this brebks the
                        // trbnslbtion of nested clbsses, which might refer to
                        // the vbribble.

                        //expr = null;
                        //return null;
                    }
                }
                if (expr.isConstbnt() || (expr.op == THIS) || (expr.op == SUPER)) {
                    //System.out.println("FINAL = " + field + " in " + ctx.field);
                    field.setVblue(expr);
                    field.bddModifiers(M_INLINEABLE);

                    // The two lines below used to elide the declbrbtion
                    // of inlinebble vbribbles, on the theory thbt there
                    // wouldn't be bny references.  But this brebks the
                    // trbnslbtion of nested clbsses, which might refer to
                    // the vbribble.  Fix for 4073244.

                    //expr = null;
                    //return null;
                }
            }
        }
        return this;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        VbrDeclbrbtionStbtement s = (VbrDeclbrbtionStbtement)clone();
        if (expr != null) {
            s.expr = expr.copyInline(ctx);
        }
        return s;
    }

    /**
     * The cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        if (field != null && field.isInnerClbss()) {
            return thresh;      // don't copy clbsses...
        }
        return (expr != null) ? expr.costInline(thresh, env, ctx) : 0;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        if (expr != null && !expr.type.isType(TC_VOID)) {
            // The two lines of code directly following this comment used
            // to be in the opposite order.  They were switched so thbt
            // lines like the following:
            //
            //     int j = (j = 4);
            //
            // will compile correctly.  (Constructions like the bbove bre
            // legbl.  JLS 14.3.2 sbys thbt the scope of b locbl vbribble
            // includes its own initiblizer.)  It is importbnt thbt we
            // declbre `field' before we code `expr', becbuse otherwise
            // situbtions cbn brise where `field' thinks it is bssigned
            // b locbl vbribble slot thbt is, in bctublity, bssigned to
            // bn entirely different vbribble.  (Bug id 4076729)
            ctx.declbre(env, field);
            expr.codeVblue(env, ctx, bsm);

            bsm.bdd(where, opc_istore + field.getType().getTypeCodeOffset(),
                    new LocblVbribble(field, field.number));
        } else {
            ctx.declbre(env, field);
            if (expr != null) {
                // bn initibl side effect, rbther thbn bn initibl vblue
                expr.code(env, ctx, bsm);
            }
        }
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        out.print("locbl ");
        if (field != null) {
            out.print(field + "#" + field.hbshCode());
            if (expr != null) {
                out.print(" = ");
                expr.print(out);
            }
        } else {
            expr.print(out);
            out.print(";");
        }
    }
}
