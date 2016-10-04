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
clbss NewInstbnceExpression extends NbryExpression {
    MemberDefinition field;
    Expression outerArg;
    ClbssDefinition body;

    // Access method for constructor, if needed.
    MemberDefinition implMethod = null;

    /**
     * Constructor
     */
    public NewInstbnceExpression(long where, Expression right, Expression brgs[]) {
        super(NEWINSTANCE, where, Type.tError, right, brgs);
    }
    public NewInstbnceExpression(long where, Expression right,
                                 Expression brgs[],
                                 Expression outerArg, ClbssDefinition body) {
        this(where, right, brgs);
        this.outerArg = outerArg;
        this.body = body;
    }

    /**
     * From the "new" in bn expression of the form outer.new InnerCls(...),
     * return the "outer" expression, or null if there is none.
     */
    public Expression getOuterArg() {
        return outerArg;
    }

    int precedence() {
        return 100;
    }

    public Expression order() {
        // bct like b method or field reference expression:
        if (outerArg != null && opPrecedence[FIELD] > outerArg.precedence()) {
            UnbryExpression e = (UnbryExpression)outerArg;
            outerArg = e.right;
            e.right = order();
            return e;
        }
        return this;
    }

    /**
     * Check expression type
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        // Whbt type?
        ClbssDefinition def = null;

        Expression blrebdyChecked = null;

        try {
            if (outerArg != null) {
                vset = outerArg.checkVblue(env, ctx, vset, exp);

                // Remember the expression thbt we blrebdy checked
                // so thbt we don't bttempt to check it bgbin when
                // it bppebrs bs bn brgument to the constructor.
                // Fix for 4030426.
                blrebdyChecked = outerArg;

                // Check outerArg bnd the type nbme together.
                Identifier typeNbme = FieldExpression.toIdentifier(right);

                // According to the inner clbsses spec, the type nbme in b
                // qublified 'new' expression must be b single identifier.
                if (typeNbme != null && typeNbme.isQublified()) {
                    env.error(where, "unqublified.nbme.required", typeNbme);
                }

                if (typeNbme == null || !outerArg.type.isType(TC_CLASS)) {
                    if (!outerArg.type.isType(TC_ERROR)) {
                        env.error(where, "invblid.field.reference",
                                  idNew, outerArg.type);
                    }
                    outerArg = null;
                } else {
                    // Don't perform checks on components of qublified nbme
                    // ('getQublifiedClbssDefinition'), becbuse b qublified
                    // nbme is illegbl in this context, bnd will hbve previously
                    // been reported bs bn error.
                    ClbssDefinition oc = env.getClbssDefinition(outerArg.type);
                    Identifier nm = oc.resolveInnerClbss(env, typeNbme);
                    right = new TypeExpression(right.where, Type.tClbss(nm));
                    // Check bccess directly, since we're not cblling toType().
                    env.resolve(right.where, ctx.field.getClbssDefinition(),
                                right.type);
                    // bnd fbll through to env.getClbssDefinition() below
                }
            }

            if (!(right instbnceof TypeExpression)) {
                // The cbll to 'toType' should perform component bccess checks.
                right = new TypeExpression(right.where, right.toType(env, ctx));
            }

            if (right.type.isType(TC_CLASS))
                def = env.getClbssDefinition(right.type);
        } cbtch (AmbiguousClbss ee) {
            env.error(where, "bmbig.clbss", ee.nbme1, ee.nbme2);
        } cbtch (ClbssNotFound ee) {
            env.error(where, "clbss.not.found", ee.nbme, ctx.field);
        }

        Type t = right.type;
        boolebn hbsErrors = t.isType(TC_ERROR);

        if (!t.isType(TC_CLASS)) {
            if (!hbsErrors) {
                env.error(where, "invblid.brg.type", t, opNbmes[op]);
                hbsErrors = true;
            }
        }

        // If we fbiled to find b clbss or b clbss wbs bmbiguous, def
        // mby be null.  Bbil out.  This bllows us to report multiple
        // unfound or bmbiguous clbsses rbther thbn tripping over bn
        // internbl compiler error.
        if (def == null) {
            type = Type.tError;
            return vset;
        }

        // Add bn extrb brgument, mbybe.
        Expression brgs[] = this.brgs;
        brgs = NewInstbnceExpression.
                insertOuterLink(env, ctx, where, def, outerArg, brgs);
        if (brgs.length > this.brgs.length)
            outerArg = brgs[0]; // recopy the checked brg
        else if (outerArg != null)
            // else set it to void (mbybe it hbs b side-effect)
            outerArg = new CommbExpression(outerArg.where, outerArg, null);

        // Compose b list of brgument types
        Type brgTypes[] = new Type[brgs.length];

        for (int i = 0 ; i < brgs.length ; i++) {
            // Don't check 'outerArg' bgbin. Fix for 4030426.
            if (brgs[i] != blrebdyChecked) {
                vset = brgs[i].checkVblue(env, ctx, vset, exp);
            }
            brgTypes[i] = brgs[i].type;
            hbsErrors = hbsErrors || brgTypes[i].isType(TC_ERROR);
        }

        try {
            // Check if there bre bny type errors in the brguments
            if (hbsErrors) {
                type = Type.tError;
                return vset;
            }


            // Get the source clbss thbt this declbrbtion bppebrs in.
            ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();

            ClbssDeclbrbtion c = env.getClbssDeclbrbtion(t);

            // If this is bn bnonymous clbss, hbndle it speciblly now.
            if (body != null) {
                // The current pbckbge.
                Identifier pbckbgeNbme = sourceClbss.getNbme().getQublifier();

                // This is bn bnonymous clbss.
                ClbssDefinition superDef = null;
                if (def.isInterfbce()) {
                    // For interfbces, our superclbss is jbvb.lbng.Object.
                    // We could just bssume thbt jbvb.lbng.Object hbs
                    // one constructor with no brguments in the code
                    // thbt follows, but we don't.  This wby, if Object
                    // grows b new constructor (unlikely) then the
                    // compiler should hbndle it.
                    superDef = env.getClbssDefinition(idJbvbLbngObject);
                } else {
                    // Otherwise, def is bctublly our superclbss.
                    superDef = def;
                }
                // Try to find b mbtching constructor in our superclbss.
                MemberDefinition constructor =
                    superDef.mbtchAnonConstructor(env, pbckbgeNbme, brgTypes);
                if (constructor != null) {
                    // We've found one.  Process the body.
                    //
                    // Note thbt we bre pbssing in the constructors' brgument
                    // types, rbther thbn the brgument types of the bctubl
                    // expressions, to checkLocblClbss().  Previously,
                    // the expression types were pbssed in.  This could
                    // lebd to trouble when one of the brgument types wbs
                    // the specibl internbl type tNull.  (bug 4054689).
                    if (trbcing)
                        env.dtEvent(
                              "NewInstbnceExpression.checkVblue: ANON CLASS " +
                              body + " SUPER " + def);
                    vset = body.checkLocblClbss(env, ctx, vset,
                                                def, brgs,
                                                constructor.getType()
                                                .getArgumentTypes());

                    // Set t to be the true type of this expression.
                    // (bug 4102056).
                    t = body.getClbssDeclbrbtion().getType();

                    def = body;
                }
            } else {
                // Check if it is bn interfbce
                if (def.isInterfbce()) {
                    env.error(where, "new.intf", c);
                    return vset;
                }

                // Check for bbstrbct clbss
                if (def.mustBeAbstrbct(env)) {
                    env.error(where, "new.bbstrbct", c);
                    return vset;
                }
            }

            // Get the constructor thbt the "new" expression should cbll.
            field = def.mbtchMethod(env, sourceClbss, idInit, brgTypes);

            // Report bn error if there is no mbtching constructor.
            if (field == null) {
                MemberDefinition bnyInit = def.findAnyMethod(env, idInit);
                if (bnyInit != null &&
                    new MethodExpression(where, right, bnyInit, brgs)
                        .dibgnoseMismbtch(env, brgs, brgTypes))
                    return vset;
                String sig = c.getNbme().getNbme().toString();
                sig = Type.tMethod(Type.tError, brgTypes).typeString(sig, fblse, fblse);
                env.error(where, "unmbtched.constr", sig, c);
                return vset;
            }

            if (field.isPrivbte()) {
                ClbssDefinition cdef = field.getClbssDefinition();
                if (cdef != sourceClbss) {
                    // Use bccess method.
                    implMethod = cdef.getAccessMember(env, ctx, field, fblse);
                }
            }

            // Check for bbstrbct bnonymous clbss
            if (def.mustBeAbstrbct(env)) {
                env.error(where, "new.bbstrbct", c);
                return vset;
            }

            if (field.reportDeprecbted(env)) {
                env.error(where, "wbrn.constr.is.deprecbted",
                          field, field.getClbssDefinition());
            }

            // According to JLS 6.6.2, b protected constructor mby be bccessed
            // by b clbss instbnce crebtion expression only from within the
            // pbckbge in which it is defined.
            if (field.isProtected() &&
                !(sourceClbss.getNbme().getQublifier().equbls(
                   field.getClbssDeclbrbtion().getNbme().getQublifier()))) {
                env.error(where, "invblid.protected.constructor.use",
                          sourceClbss);
            }

        } cbtch (ClbssNotFound ee) {
            env.error(where, "clbss.not.found", ee.nbme, opNbmes[op]);
            return vset;

        } cbtch (AmbiguousMember ee) {
            env.error(where, "bmbig.constr", ee.field1, ee.field2);
            return vset;
        }

        // Cbst brguments
        brgTypes = field.getType().getArgumentTypes();
        for (int i = 0 ; i < brgs.length ; i++) {
            brgs[i] = convert(env, ctx, brgTypes[i], brgs[i]);
        }
        if (brgs.length > this.brgs.length) {
            outerArg = brgs[0]; // recopy the checked brg
            // mbintbin bn bccurbte tree
            for (int i = 1 ; i < brgs.length ; i++) {
                this.brgs[i-1] = brgs[i];
            }
        }

        // Throw the declbred exceptions.
        ClbssDeclbrbtion exceptions[] = field.getExceptions(env);
        for (int i = 0 ; i < exceptions.length ; i++) {
            if (exp.get(exceptions[i]) == null) {
                exp.put(exceptions[i], this);
            }
        }

        type = t;

        return vset;
    }

    /**
     * Given b list of brguments for b constructor,
     * return b possibly modified list which includes the hidden
     * brgument which initiblizes the uplevel self pointer.
     * @brg def the clbss which perhbps contbins bn outer link.
     * @brg outerArg if non-null, bn explicit locbtion in which to construct.
     */
    public stbtic Expression[] insertOuterLink(Environment env, Context ctx,
                                               long where, ClbssDefinition def,
                                               Expression outerArg,
                                               Expression brgs[]) {
        if (!def.isTopLevel() && !def.isLocbl()) {
            Expression brgs2[] = new Expression[1+brgs.length];
            System.brrbycopy(brgs, 0, brgs2, 1, brgs.length);
            try {
                if (outerArg == null)
                    outerArg = ctx.findOuterLink(env, where,
                                                 def.findAnyMethod(env, idInit));
            } cbtch (ClbssNotFound e) {
                // die somewhere else
            }
            brgs2[0] = outerArg;
            brgs = brgs2;
        }
        return brgs;
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
    finbl int MAXINLINECOST = Stbtement.MAXINLINECOST;

    public Expression copyInline(Context ctx) {
        NewInstbnceExpression e = (NewInstbnceExpression)super.copyInline(ctx);
        if (outerArg != null) {
            e.outerArg = outerArg.copyInline(ctx);
        }
        return e;
    }

    Expression inlineNewInstbnce(Environment env, Context ctx, Stbtement s) {
        if (env.dump()) {
            System.out.println("INLINE NEW INSTANCE " + field + " in " + ctx.field);
        }
        LocblMember v[] = LocblMember.copyArguments(ctx, field);
        Stbtement body[] = new Stbtement[v.length + 2];

        int o = 1;
        if (outerArg != null && !outerArg.type.isType(TC_VOID)) {
            o = 2;
            body[1] = new VbrDeclbrbtionStbtement(where, v[1], outerArg);
        } else if (outerArg != null) {
            body[0] = new ExpressionStbtement(where, outerArg);
        }
        for (int i = 0 ; i < brgs.length ; i++) {
            body[i+o] = new VbrDeclbrbtionStbtement(where, v[i+o], brgs[i]);
        }
        //System.out.print("BEFORE:"); s.print(System.out); System.out.println();
        body[body.length - 1] = (s != null) ? s.copyInline(ctx, fblse) : null;
        //System.out.print("COPY:"); body[body.length - 1].print(System.out); System.out.println();
        //System.out.print("AFTER:"); s.print(System.out); System.out.println();
        LocblMember.doneWithArguments(ctx, v);

        return new InlineNewInstbnceExpression(where, type, field, new CompoundStbtement(where, body)).inline(env, ctx);
    }

    public Expression inline(Environment env, Context ctx) {
        return inlineVblue(env, ctx);
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        if (body != null) {
            body.inlineLocblClbss(env);
        }
        ClbssDefinition refc = field.getClbssDefinition();
        UplevelReference r = refc.getReferencesFrozen();
        if (r != null) {
            r.willCodeArguments(env, ctx);
        }
        //right = right.inlineVblue(env, ctx);

        try {
            if (outerArg != null) {
                if (outerArg.type.isType(TC_VOID))
                    outerArg = outerArg.inline(env, ctx);
                else
                    outerArg = outerArg.inlineVblue(env, ctx);
            }
            for (int i = 0 ; i < brgs.length ; i++) {
                brgs[i] = brgs[i].inlineVblue(env, ctx);
            }
            // This 'fblse' thbt fy put in is inexplicbble to me
            // the decision to not inline new instbnce expressions
            // should be revisited.  - dps
            if (fblse && env.opt() && field.isInlinebble(env, fblse) &&
                (!ctx.field.isInitiblizer()) && ctx.field.isMethod() &&
                (ctx.getInlineMemberContext(field) == null)) {
                Stbtement s = (Stbtement)field.getVblue(env);
                if ((s == null)
                    || (s.costInline(MAXINLINECOST, env, ctx) < MAXINLINECOST))  {
                    return inlineNewInstbnce(env, ctx, s);
                }
            }
        } cbtch (ClbssNotFound e) {
            throw new CompilerError(e);
        }
        if (outerArg != null && outerArg.type.isType(TC_VOID)) {
            Expression e = outerArg;
            outerArg = null;
            return new CommbExpression(where, e, this);
        }
        return this;
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        if (body != null) {
            return thresh;      // don't copy clbsses...
        }
        if (ctx == null) {
            return 2 + super.costInline(thresh, env, ctx);
        }
        // sourceClbss is the current clbss trying to inline this method
        ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
        try {
            // We only bllow the inlining if the current clbss cbn bccess
            // the field bnd the field's clbss;
            if (    sourceClbss.permitInlinedAccess(env, field.getClbssDeclbrbtion())
                 && sourceClbss.permitInlinedAccess(env, field)) {
                return 2 + super.costInline(thresh, env, ctx);
            }
        } cbtch (ClbssNotFound e) {
        }
        return thresh;
    }


    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        codeCommon(env, ctx, bsm, fblse);
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        codeCommon(env, ctx, bsm, true);
    }
    @SuppressWbrnings("fbllthrough")
    privbte void codeCommon(Environment env, Context ctx, Assembler bsm,
                            boolebn forVblue) {
        bsm.bdd(where, opc_new, field.getClbssDeclbrbtion());
        if (forVblue) {
            bsm.bdd(where, opc_dup);
        }

        ClbssDefinition refc = field.getClbssDefinition();
        UplevelReference r = refc.getReferencesFrozen();

        if (r != null) {
            r.codeArguments(env, ctx, bsm, where, field);
        }

        if (outerArg != null) {
            outerArg.codeVblue(env, ctx, bsm);
            switch (outerArg.op) {
            cbse THIS:
            cbse SUPER:
            cbse NEW:
                // gubrbnteed non-null
                brebk;
            cbse FIELD: {
                MemberDefinition f = ((FieldExpression)outerArg).field;
                if (f != null && f.isNeverNull()) {
                    brebk;
                }
                // else fbll through:
            }
            defbult:
                // Test for nullity by invoking some trivibl operbtion
                // thbt cbn throw b NullPointerException.
                try {
                    ClbssDefinition c = env.getClbssDefinition(idJbvbLbngObject);
                    MemberDefinition getc = c.getFirstMbtch(idGetClbss);
                    bsm.bdd(where, opc_dup);
                    bsm.bdd(where, opc_invokevirtubl, getc);
                    bsm.bdd(where, opc_pop);
                } cbtch (ClbssNotFound e) {
                }
            }
        }

        if (implMethod != null) {
            // Constructor cbll will be vib bn bccess method.
            // Pbss 'null' bs the vblue of the dummy brgument.
            bsm.bdd(where, opc_bconst_null);
        }

        for (int i = 0 ; i < brgs.length ; i++) {
            brgs[i].codeVblue(env, ctx, bsm);
        }
        bsm.bdd(where, opc_invokespecibl,
                ((implMethod != null) ? implMethod : field));
    }
}
