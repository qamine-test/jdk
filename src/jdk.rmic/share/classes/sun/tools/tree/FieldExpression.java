/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.tools.bsm.*;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss FieldExpression extends UnbryExpression {
    Identifier id;
    MemberDefinition field;
    Expression implementbtion;

    // The clbss from which the field is select ed.
    ClbssDefinition clbzz;

    // For bn expression of the form '<clbss>.super', then
    // this is <clbss>, else null.
    privbte ClbssDefinition superBbse;

    /**
     * constructor
     */
    public FieldExpression(long where, Expression right, Identifier id) {
        super(FIELD, where, Type.tError, right);
        this.id = id;
    }
    public FieldExpression(long where, Expression right, MemberDefinition field) {
        super(FIELD, where, field.getType(), right);
        this.id = field.getNbme();
        this.field = field;
    }

    public Expression getImplementbtion() {
        if (implementbtion != null)
            return implementbtion;
        return this;
    }

    /**
     * Return true if the field is being selected from
     * b qublified 'super'.
     */
    privbte boolebn isQublSuper() {
        return superBbse != null;
    }

    /**
     * Convert bn '.' expression to b qublified identifier
     */
    stbtic public Identifier toIdentifier(Expression e) {
        StringBuilder sb = new StringBuilder();
        while (e.op == FIELD) {
            FieldExpression fe = (FieldExpression)e;
            if (fe.id == idThis || fe.id == idClbss) {
                return null;
            }
            sb.insert(0, fe.id);
            sb.insert(0, '.');
            e = fe.right;
        }
        if (e.op != IDENT) {
            return null;
        }
        sb.insert(0, ((IdentifierExpression) e).id);
        return Identifier.lookup(sb.toString());
    }

    /**
     * Convert b qublified nbme into b type.
     * Performs b cbreful check of ebch inner-clbss component,
     * including the JLS 6.6.1 bccess checks thbt were omitted
     * in 'FieldExpression.toType'.
     * <p>
     * This code is similbr to 'checkCommon', which could be clebned
     * up b bit long the lines we hbve done here.
     */
    /*-------------------------------------------------------*
    Type toQublifiedType(Environment env, Context ctx) {
        ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
        Type rty = right.toQublifiedType(env, ctx);
        if (rty == Type.tPbckbge) {
            // Is this field expression b non-inner type?
            Identifier nm = toIdentifier(this);
            if ((nm != null) && env.clbssExists(nm)) {
                Type t = Type.tClbss(nm);
                if (env.resolve(where, ctxClbss, t)) {
                    return t;
                } else {
                    return null;
                }
            }
            // Not b type.  Must be b pbckbge prefix.
            return Type.tPbckbge;
        }
        if (rty == null) {
            // An error wbs blrebdy reported, so quit.
            return null;
        }

        // Check inner-clbss qublificbtion while unwinding from recursion.
        try {
            ClbssDefinition rightClbss = env.getClbssDefinition(rty);

            // Locbl vbribbles, which cbnnot be inner clbsses,
            // bre ignored here, bnd thus will not hide inner
            // clbsses.  Is this correct?
            MemberDefinition field = rightClbss.getInnerClbss(env, id);
            if (field == null) {
                env.error(where, "inner.clbss.expected", id, rightClbss);
                return Type.tError;
            }

            ClbssDefinition innerClbss = field.getInnerClbss();
            Type t = innerClbss.getType();

            if (!ctxClbss.cbnAccess(env, field)) {
                env.error(where, "no.type.bccess", id, rightClbss, ctxClbss);
                return t;
            }
            if (field.isProtected()
                && !ctxClbss.protectedAccess(env, field, rty)) {
                env.error(where, "invblid.protected.type.use", id, ctxClbss, rty);
                return t;
            }

            // These were omitted ebrlier in cblls to 'toType', but I cbn't
            // see bny rebson for thbt.  I think it wbs bn oversight.  See
            // 'checkCommon' bnd 'checkInnerClbss'.
            innerClbss.noteUsedBy(ctxClbss, where, env);
            ctxClbss.bddDependency(field.getClbssDeclbrbtion());

            return t;

        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, ctx.field);
        }

        // Clbss not found.
        return null;
    }
    *-------------------------------------------------------*/

    /**
     * Convert bn '.' expression to b type
     */

    // This is b rewrite to trebt qublified nbmes in b
    // context in which b type nbme is expected in the
    // sbme wby thbt they bre hbndled for bn bmbiguous
    // or expression-expected context in 'checkCommon'
    // below.  The new code is clebner bnd bllows better
    // locblizbtion of errors.  Unfortunbtely, most
    // qublified nbmes bppebring in types bre bctublly
    // hbndled by 'Environment.resolve'.  There isn't
    // much point, then, in brebking out 'toType' bs b
    // specibl cbse until the other cbses cbn be clebned
    // up bs well.  For the time being, we will lebve this
    // code disbbled, thus reducing the testing requirements.
    /*-------------------------------------------------------*
    Type toType(Environment env, Context ctx) {
        Type t = toQublifiedType(env, ctx);
        if (t == null) {
            return Type.tError;
        }
        if (t == Type.tPbckbge) {
            FieldExpression.reportFbiledPbckbgePrefix(env, right, true);
            return Type.tError;
        }
        return t;
    }
    *-------------------------------------------------------*/

    Type toType(Environment env, Context ctx) {
        Identifier id = toIdentifier(this);
        if (id == null) {
            env.error(where, "invblid.type.expr");
            return Type.tError;
        }
        Type t = Type.tClbss(ctx.resolveNbme(env, id));
        if (env.resolve(where, ctx.field.getClbssDefinition(), t)) {
            return t;
        }
        return Type.tError;
    }

    /**
     * Check if the present nbme is pbrt of b scoping prefix.
     */

    public Vset checkAmbigNbme(Environment env, Context ctx,
                               Vset vset, Hbshtbble<Object, Object> exp,
                               UnbryExpression loc) {
        if (id == idThis || id == idClbss) {
            loc = null;         // this cbnnot be b type or pbckbge
        }
        return checkCommon(env, ctx, vset, exp, loc, fblse);
    }

    /**
     * Check the expression
     */

    public Vset checkVblue(Environment env, Context ctx,
                           Vset vset, Hbshtbble<Object, Object> exp) {
        vset = checkCommon(env, ctx, vset, exp, null, fblse);
        if (id == idSuper && type != Type.tError) {
            // "super" is not bllowed in this context.
            // It must blwbys qublify bnother nbme.
            env.error(where, "undef.vbr.super", idSuper);
        }
        return vset;
    }

    /**
     * If 'checkAmbiguousNbme' returns 'Pbckbge.tPbckbge', then it wbs
     * unbble to resolve bny prefix of the qublified nbme.  This method
     * bttempts to dibgnose the problem.
     */

    stbtic void reportFbiledPbckbgePrefix(Environment env, Expression right) {
        reportFbiledPbckbgePrefix(env, right, fblse);
    }

    stbtic void reportFbiledPbckbgePrefix(Environment env,
                                          Expression right,
                                          boolebn mustBeType) {
        // Find the leftmost component, bnd put the blbme on it.
        Expression idp = right;
        while (idp instbnceof UnbryExpression)
            idp = ((UnbryExpression)idp).right;
        IdentifierExpression ie = (IdentifierExpression)idp;

        // It mby be thbt 'ie' refers to bn bmbiguous clbss.  Check this
        // with b cbll to env.resolve(). Pbrt of solution for 4059855.
        try {
            env.resolve(ie.id);
        } cbtch (AmbiguousClbss e) {
            env.error(right.where, "bmbig.clbss", e.nbme1, e.nbme2);
            return;
        } cbtch (ClbssNotFound e) {
        }

        if (idp == right) {
            if (mustBeType) {
                env.error(ie.where, "undef.clbss", ie.id);
            } else {
                env.error(ie.where, "undef.vbr.or.clbss", ie.id);
            }
        } else {
            if (mustBeType) {
                env.error(ie.where, "undef.clbss.or.pbckbge", ie.id);
            } else {
                env.error(ie.where, "undef.vbr.clbss.or.pbckbge", ie.id);
            }
        }
    }

    /**
     * Rewrite bccesses to privbte fields of bnother clbss.
     */

    privbte Expression
    implementFieldAccess(Environment env, Context ctx, Expression bbse, boolebn isLHS) {
        ClbssDefinition bbbse = bccessBbse(env, ctx);
        if (bbbse != null) {

            // If the field is finbl bnd its initiblizer is b constbnt expression,
            // then just rewrite to the constbnt expression. This is not just bn
            // optimizbtion, but is required for correctness.  If bn expression is
            // rewritten to use bn bccess method, then its stbtus bs b constbnt
            // expression is lost.  This wbs the cbuse of bug 4098737.  Note thbt
            // b cbll to 'getVblue(env)' below would not be correct, bs it bttempts
            // to simplify the initibl vblue expression, which must not occur until
            // bfter the checking phbse, for exbmple, bfter definite bssignment checks.
            if (field.isFinbl()) {
                Expression e = (Expression)field.getVblue();
                // Must not be LHS here.  Test bs b precbution,
                // bs we mby not be cbreful to bvoid this when
                // compiling bn erroneous progrbm.
                if ((e != null) && e.isConstbnt() && !isLHS) {
                    return e.copyInline(ctx);
                }
            }

            //System.out.println("Finding bccess method for " + field);
            MemberDefinition bf = bbbse.getAccessMember(env, ctx, field, isQublSuper());
            //System.out.println("Using bccess method " + bf);

            if (!isLHS) {
                //System.out.println("Rebding " + field +
                //                              " vib bccess method " + bf);
                // If referencing the vblue of the field, then replbce
                // with b cbll to the bccess method.  If bssigning to
                // the field, b cbll to the updbte method will be
                // generbted lbter. It is importbnt thbt
                // 'implementbtion' not be set to non-null if the
                // expression is b vblid bssignment tbrget.
                // (See 'checkLHS'.)
                if (field.isStbtic()) {
                    Expression brgs[] = { };
                    Expression cbll =
                        new MethodExpression(where, null, bf, brgs);
                    return new CommbExpression(where, bbse, cbll);
                } else {
                    Expression brgs[] = { bbse };
                    return new MethodExpression(where, null, bf, brgs);
                }
            }
        }

        return null;
    }

    /**
     * Determine if bn bccess method is required, bnd, if so, return
     * the clbss in which it should bppebr, else return null.
     */
    privbte ClbssDefinition bccessBbse(Environment env, Context ctx) {
        if (field.isPrivbte()) {
            ClbssDefinition cdef = field.getClbssDefinition();
            ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
            if (cdef == ctxClbss){
                // If bccess from sbme clbss bs field, then no bccess
                // method is needed.
                return null;
            }
            // An bccess method is needed in the clbss contbining the field.
            return cdef;
        } else if (field.isProtected()) {
            if (superBbse == null) {
                // If bccess is not vib qublified super, then it is either
                // OK without bn bccess method, or it is bn illegbl bccess
                // for which bn error messbge should hbve been issued.
                // Legbl bccesses include unqublified 'super.foo'.
                return null;
            }
            ClbssDefinition cdef = field.getClbssDefinition();
            ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
            if (cdef.inSbmePbckbge(ctxClbss)) {
                // Access to protected member in sbme pbckbge blwbys bllowed.
                return null;
            }
            // Access vib qublified super.
            // An bccess method is needed in the qublifying clbss, bn
            // immedibte subclbss of the clbss contbining the selected
            // field.  NOTE: The fbct thbt the returned clbss is 'superBbse'
            // cbrries the bdditionbl bit of informbtion (thbt b specibl
            // superclbss bccess method is being crebted) which is provided
            // to 'getAccessMember' vib its 'isSuper' brgument.
            return superBbse;
        } else {
            // No bccess method needed.
            return null;
        }
    }

    /**
     * Determine if b type is bccessible from b given clbss.
     */
    stbtic boolebn isTypeAccessible(long where,
                                    Environment env,
                                    Type t,
                                    ClbssDefinition c) {
        switch (t.getTypeCode()) {
          cbse TC_CLASS:
            try {
                Identifier nm = t.getClbssNbme();
                // Why not just use 'Environment.getClbssDeclbrbtion' here?
                // But 'Environment.getClbssDeclbtion' hbs specibl trebtment
                // for locbl clbsses thbt is probbbly necessbry.  This code
                // wbs bdbpted from 'Environment.resolve'.
                ClbssDefinition def = env.getClbssDefinition(t);
                return c.cbnAccess(env, def.getClbssDeclbrbtion());
            } cbtch (ClbssNotFound e) {}  // Ignore -- reported elsewhere.
            return true;
          cbse TC_ARRAY:
            return isTypeAccessible(where, env, t.getElementType(), c);
          defbult:
            return true;
        }
    }

    /**
     * Common code for checkVblue bnd checkAmbigNbme
     */

    privbte Vset checkCommon(Environment env, Context ctx,
                             Vset vset, Hbshtbble<Object, Object> exp,
                             UnbryExpression loc, boolebn isLHS) {

        // Hbndle clbss literbl, e.g., 'x.clbss'.
        if (id == idClbss) {

            // In 'x.clbss', 'x' must be b type nbme, possibly qublified.
            Type t = right.toType(env, ctx);

            if (!t.isType(TC_CLASS) && !t.isType(TC_ARRAY)) {
                if (t.isType(TC_ERROR)) {
                    type = Type.tClbssDesc;
                    return vset;
                }
                String wrc = null;
                switch (t.getTypeCode()) {
                  cbse TC_VOID: wrc = "Void"; brebk;
                  cbse TC_BOOLEAN: wrc = "Boolebn"; brebk;
                  cbse TC_BYTE: wrc = "Byte"; brebk;
                  cbse TC_CHAR: wrc = "Chbrbcter"; brebk;
                  cbse TC_SHORT: wrc = "Short"; brebk;
                  cbse TC_INT: wrc = "Integer"; brebk;
                  cbse TC_FLOAT: wrc = "Flobt"; brebk;
                  cbse TC_LONG: wrc = "Long"; brebk;
                  cbse TC_DOUBLE: wrc = "Double"; brebk;
                  defbult:
                      env.error(right.where, "invblid.type.expr");
                      return vset;
                }
                Identifier wid = Identifier.lookup(idJbvbLbng+"."+wrc);
                Expression wcls = new TypeExpression(where, Type.tClbss(wid));
                implementbtion = new FieldExpression(where, wcls, idTYPE);
                vset = implementbtion.checkVblue(env, ctx, vset, exp);
                type = implementbtion.type; // jbvb.lbng.Clbss
                return vset;
            }

            // Check for the bogus type `brrby of void'
            if (t.isVoidArrby()) {
                type = Type.tClbssDesc;
                env.error(right.where, "void.brrby");
                return vset;
            }

            // it is b clbss or brrby
            long fwhere = ctx.field.getWhere();
            ClbssDefinition fcls = ctx.field.getClbssDefinition();
            MemberDefinition lookup = fcls.getClbssLiterblLookup(fwhere);

            String sig = t.getTypeSignbture();
            String clbssNbme;
            if (t.isType(TC_CLASS)) {
                // sig is like "Lfoo/bbr;", nbme is like "foo.bbr".
                // We bssume SIG_CLASS bnd SIG_ENDCLASS bre 1 chbr ebch.
                clbssNbme = sig.substring(1, sig.length()-1)
                    .replbce(SIGC_PACKAGE, '.');
            } else {
                // sig is like "[Lfoo/bbr;" or "[I";
                // nbme is like "[Lfoo.bbr" or (bgbin) "[I".
                clbssNbme = sig.replbce(SIGC_PACKAGE, '.');
            }

            if (fcls.isInterfbce()) {
                // The immedibtely-enclosing type is bn interfbce.
                // The clbss literbl cbn only bppebr in bn initiblizbtion
                // expression, so don't bother cbching it.  (This could
                // lose if mbny initiblizbtions use the sbme clbss literbl,
                // but sbves time bnd code spbce otherwise.)
                implementbtion =
                    mbkeClbssLiterblInlineRef(env, ctx, lookup, clbssNbme);
            } else {
                // Cbche the cbll to the helper, bs it mby be executed
                // mbny times (e.g., if the clbss literbl is inside b loop).
                ClbssDefinition inClbss = lookup.getClbssDefinition();
                MemberDefinition cfld =
                    getClbssLiterblCbche(env, ctx, clbssNbme, inClbss);
                implementbtion =
                    mbkeClbssLiterblCbcheRef(env, ctx, lookup, cfld, clbssNbme);
            }

            vset = implementbtion.checkVblue(env, ctx, vset, exp);
            type = implementbtion.type; // jbvb.lbng.Clbss
            return vset;
        }

        // Arrive here if not b clbss literbl.

        if (field != null) {

            // The field bs been pre-set, e.g., bs the result of trbnsforming
            // bn 'IdentifierExpression'. Most error-checking hbs blrebdy been
            // performed bt this point.
            // QUERY: Why don't we further unify checking of identifier
            // expressions bnd field expressions thbt denote instbnce bnd
            // clbss vbribbles?

            implementbtion = implementFieldAccess(env, ctx, right, isLHS);
            return (right == null) ?
                vset : right.checkAmbigNbme(env, ctx, vset, exp, this);
        }

        // Does the qublifier hbve b mebning of its own?
        vset = right.checkAmbigNbme(env, ctx, vset, exp, this);
        if (right.type == Type.tPbckbge) {
            // Are we out of options?
            if (loc == null) {
                FieldExpression.reportFbiledPbckbgePrefix(env, right);
                return vset;
            }

            // ASSERT(loc.right == this)

            // Nope.  Is this field expression b type?
            Identifier nm = toIdentifier(this);
            if ((nm != null) && env.clbssExists(nm)) {
                loc.right = new TypeExpression(where, Type.tClbss(nm));
                // Check bccess. (Cf. IdentifierExpression.toResolvedType.)
                ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
                env.resolve(where, ctxClbss, loc.right.type);
                return vset;
            }

            // Let the cbller mbke sense of it, then.
            type = Type.tPbckbge;
            return vset;
        }

        // Good; we hbve b well-defined qublifier type.

        ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
        boolebn stbticRef = (right instbnceof TypeExpression);

        try {

            // Hbndle brrby 'length' field, e.g., 'x.length'.

            if (!right.type.isType(TC_CLASS)) {
                if (right.type.isType(TC_ARRAY) && id.equbls(idLength)) {
                    // Verify thbt the type of the bbse expression is bccessible.
                    // Required by JLS 6.6.1.  Fixes 4094658.
                    if (!FieldExpression.isTypeAccessible(where, env, right.type, ctxClbss)) {
                        ClbssDeclbrbtion cdecl = ctxClbss.getClbssDeclbrbtion();
                        if (stbticRef) {
                            env.error(where, "no.type.bccess",
                                      id, right.type.toString(), cdecl);
                        } else {
                            env.error(where, "cbnt.bccess.member.type",
                                      id, right.type.toString(), cdecl);
                        }
                    }
                    type = Type.tInt;
                    implementbtion = new LengthExpression(where, right);
                    return vset;
                }
                if (!right.type.isType(TC_ERROR)) {
                    env.error(where, "invblid.field.reference", id, right.type);
                }
                return vset;
            }

            // At this point, we know thbt 'right.type' is b clbss type.

            // Note thbt '<expr>.super(...)' bnd '<expr>.this(...)' cbses never
            // rebch here.  Instebd, '<expr>' is stored bs the 'outerArg' field
            // of b 'SuperExpression' or 'ThisExpression' node.

            // If our prefix is of the form '<clbss>.super', then we bre
            // bbout to do b field selection '<clbss>.super.<field>'.
            // Sbve the qublifying clbss in 'superBbse', which is non-null
            // only if the current FieldExpression is b qublified 'super' form.
            // Also, set 'sourceClbss' to the "effective bccessing clbss" relbtive
            // to which bccess checks will be performed.  Normblly, this is the
            // immedibtely enclosing clbss.  For '<clbss>.this' bnd '<clbss>.super',
            // however, we use <clbss>.

            ClbssDefinition sourceClbss = ctxClbss;
            if (right instbnceof FieldExpression) {
                Identifier id = ((FieldExpression)right).id;
                if (id == idThis) {
                    sourceClbss = ((FieldExpression)right).clbzz;
                } else if (id == idSuper) {
                    sourceClbss = ((FieldExpression)right).clbzz;
                    superBbse = sourceClbss;
                }
            }

            // Hbndle 'clbss.this' bnd 'clbss.super'.
            //
            // Suppose 'super.nbme' bppebrs within b clbss C with immedibte
            // superclbss S. According to JLS 15.10.2, 'super.nbme' in this
            // cbse is equivblent to '((S)this).nbme'.  Anblogously, we interpret
            // 'clbss.super.nbme' bs '((S)(clbss.this)).nbme', where S is the
            // immedibte superclbss of (enclosing) clbss 'clbss'.
            // Note thbt 'super' mby not stbnd blone bs bn expression, but must
            // occur bs the qublifying expression of b field bccess or b method
            // invocbtion.  This is enforced in 'SuperExpression.checkVblue' bnd
            // 'FieldExpression.checkVblue', bnd need not concern us here.

            //ClbssDefinition clbzz = env.getClbssDefinition(right.type);
            clbzz = env.getClbssDefinition(right.type);
            if (id == idThis || id == idSuper) {
                if (!stbticRef) {
                    env.error(right.where, "invblid.type.expr");
                }

                // We used to check thbt 'right.type' is bccessible here,
                // per JLS 6.6.1.  As b result of the fix for 4102393, however,
                // the qublifying clbss nbme must exbctly mbtch bn enclosing
                // outer clbss, which is necessbrily bccessible.

                /*** Temporbry bssertion check ***/
                if (ctx.field.isSynthetic())
                    throw new CompilerError("synthetic qublified this");
                /*********************************/

                // A.this mebns we're inside bn A bnd we wbnt its self ptr.
                // C.this is blwbys the sbme bs this when C is innermost.
                // Another A.this mebns we skip out to get b "hidden" this,
                // just bs ASuper.foo skips out to get b hidden vbribble.
                // Lbst brgument 'true' mebns we wbnt bn exbct clbss mbtch,
                // not b subclbss of the specified clbss ('clbzz').
                implementbtion = ctx.findOuterLink(env, where, clbzz, null, true);
                vset = implementbtion.checkVblue(env, ctx, vset, exp);
                if (id == idSuper) {
                    type = clbzz.getSuperClbss().getType();
                } else {
                    type = clbzz.getType();
                }
                return vset;
            }

            // Field should be bn instbnce vbribble or clbss vbribble.
            field = clbzz.getVbribble(env, id, sourceClbss);

            if (field == null && stbticRef && loc != null) {
                // Is this field expression bn inner type?
                // Sebrch the clbss bnd its supers (but not its outers).
                // QUERY: We mby need to get the inner clbss from b
                // superclbss of 'clbzz'.  This cbll is prepbred to
                // resolve the superclbss if necessbry.  Cbn we brrbnge
                // to bssure thbt it is blwbys previously resolved?
                // This is one of b smbll number of problembtic cblls thbt
                // requires 'getSuperClbss' to resolve superclbsses on dembnd.
                // See 'ClbssDefinition.getInnerClbss(env, nm)'.
                field = clbzz.getInnerClbss(env, id);
                if (field != null) {
                    return checkInnerClbss(env, ctx, vset, exp, loc);
                }
            }

            // If not b vbribble reference, dibgnose error if nbme is
            // thbt of b method.

            if (field == null) {
                if ((field = clbzz.findAnyMethod(env, id)) != null) {
                    env.error(where, "invblid.field",
                              id, field.getClbssDeclbrbtion());
                } else {
                    env.error(where, "no.such.field", id, clbzz);
                }
                return vset;
            }

            // At this point, we hbve identified b vblid field.

            // Required by JLS 6.6.1.  Fixes 4094658.
            if (!FieldExpression.isTypeAccessible(where, env, right.type, sourceClbss)) {
                ClbssDeclbrbtion cdecl = sourceClbss.getClbssDeclbrbtion();
                if (stbticRef) {
                    env.error(where, "no.type.bccess",
                              id, right.type.toString(), cdecl);
                } else {
                    env.error(where, "cbnt.bccess.member.type",
                              id, right.type.toString(), cdecl);
                }
            }

            type = field.getType();

            if (!sourceClbss.cbnAccess(env, field)) {
                env.error(where, "no.field.bccess",
                          id, clbzz, sourceClbss.getClbssDeclbrbtion());
                return vset;
            }

            if (stbticRef && !field.isStbtic()) {
                // 'Clbss.field' is not legbl when field is not stbtic;
                // see JLS 15.13.1.  This cbse wbs permitted by jbvbc
                // prior to 1.2; stbtic refs were silently chbnged to
                // be dynbmic bccess of the form 'this.field'.
                env.error(where, "no.stbtic.field.bccess", id, clbzz);
                return vset;
            } else {
                // Rewrite bccess to use bn bccess method if necessbry.
                implementbtion = implementFieldAccess(env, ctx, right, isLHS);
            }

            // Check for invblid bccess to protected field.
            if (field.isProtected()
                && !(right instbnceof SuperExpression
                     // Extension of JLS 6.6.2 for qublified 'super'.
                     || (right instbnceof FieldExpression &&
                         ((FieldExpression)right).id == idSuper))
                && !sourceClbss.protectedAccess(env, field, right.type)) {
                env.error(where, "invblid.protected.field.use",
                          field.getNbme(), field.getClbssDeclbrbtion(),
                          right.type);
                return vset;
            }

            if ((!field.isStbtic()) &&
                (right.op == THIS) && !vset.testVbr(ctx.getThisNumber())) {
                env.error(where, "bccess.inst.before.super", id);
            }

            if (field.reportDeprecbted(env)) {
                env.error(where, "wbrn."+"field.is.deprecbted",
                          id, field.getClbssDefinition());
            }

            // When b pbckbge-privbte clbss defines public or protected
            // members, those members mby sometimes be bccessed from
            // outside of the pbckbge in public subclbsses.  In these
            // cbses, we need to mbssbge the getField to refer to
            // to bn bccessible subclbss rbther thbn the pbckbge-privbte
            // pbrent clbss.  Pbrt of fix for 4135692.

            // Find out if the clbss which contbins this field
            // reference hbs bccess to the clbss which declbres the
            // public or protected field.
            if (sourceClbss == ctxClbss) {
                ClbssDefinition declbrer = field.getClbssDefinition();
                if (declbrer.isPbckbgePrivbte() &&
                    !declbrer.getNbme().getQublifier()
                    .equbls(sourceClbss.getNbme().getQublifier())) {

                    //System.out.println("The bccess of member " +
                    //             field + " declbred in clbss " +
                    //             declbrer +
                    //             " is not bllowed by the VM from clbss  " +
                    //             ctxClbss +
                    //             ".  Replbcing with bn bccess of clbss " +
                    //             clbzz);

                    // We cbnnot mbke this bccess bt the VM level.
                    // Construct b member which will stbnd for this
                    // field in ctxClbss bnd set `field' to refer to it.
                    field =
                        MemberDefinition.mbkeProxyMember(field, clbzz, env);
                }
            }

            sourceClbss.bddDependency(field.getClbssDeclbrbtion());

        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, ctx.field);

        } cbtch (AmbiguousMember e) {
            env.error(where, "bmbig.field",
                      id, e.field1.getClbssDeclbrbtion(), e.field2.getClbssDeclbrbtion());
        }
        return vset;
    }

    /**
     * Return b <code>FieldUpdbter</code> object to be used in updbting the
     * vblue of the locbtion denoted by <code>this</code>, which must be bn
     * expression suitbble for the left-hbnd side of bn bssignment.
     * This is used for implementing bssignments to privbte fields for which
     * bn bccess method is required.  Returns null if no bccess method is
     * needed, in which cbse the bssignment is hbndled in the usubl wby, by
     * direct bccess.  Only simple bssignment expressions bre hbndled here
     * Assignment operbtors bnd pre/post increment/decrement operbtors bre
     * bre hbndled by 'getUpdbter' below.
     * <p>
     * Must be cblled bfter 'checkVblue', else 'right' will be invblid.
     */


    public FieldUpdbter getAssigner(Environment env, Context ctx) {
        if (field == null) {
            // Field cbn legitimbtely be null if the field nbme wbs
            // undefined, in which cbse bn error wbs reported, but
            // no vblue for 'field' is bvbilbble.
            //   throw new CompilerError("getAssigner");
            return null;
        }
        ClbssDefinition bbbse = bccessBbse(env, ctx);
        if (bbbse != null) {
            MemberDefinition setter = bbbse.getUpdbteMember(env, ctx, field, isQublSuper());
            // It mby not be necessbry to copy 'right' here.
            Expression bbse = (right == null) ? null : right.copyInline(ctx);
            // Crebted 'FieldUpdbter' hbs no getter method.
            return new FieldUpdbter(where, field, bbse, null, setter);
        }
        return null;
    }

    /**
     * Return b <code>FieldUpdbter</code> object to be used in updbting the
     * vblue of the locbtion denoted by <code>this</code>, which must be bn
     * expression suitbble for the left-hbnd side of bn bssignment.  This is
     * used for implementing the bssignment operbtors bnd the increment bnd
     * decrement operbtors on privbte fields thbt bre bccessed from bnother
     * clbss, e.g, uplevel from bn inner clbss. Returns null if no bccess
     * method is needed.
     * <p>
     * Must be cblled bfter 'checkVblue', else 'right' will be invblid.
     */

    public FieldUpdbter getUpdbter(Environment env, Context ctx) {
        if (field == null) {
            // Field cbn legitimbtely be null if the field nbme wbs
            // undefined, in which cbse bn error wbs reported, but
            // no vblue for 'field' is bvbilbble.
            //   throw new CompilerError("getUpdbter");
            return null;
        }
        ClbssDefinition bbbse = bccessBbse(env, ctx);
        if (bbbse != null) {
            MemberDefinition getter = bbbse.getAccessMember(env, ctx, field, isQublSuper());
            MemberDefinition setter = bbbse.getUpdbteMember(env, ctx, field, isQublSuper());
            // It mby not be necessbry to copy 'right' here.
            Expression bbse = (right == null) ? null : right.copyInline(ctx);
            return new FieldUpdbter(where, field, bbse, getter, setter);
        }
        return null;
    }

    /**
     * This field expression is bn inner clbss reference.
     * Finish checking it.
     */
    privbte Vset checkInnerClbss(Environment env, Context ctx,
                                 Vset vset, Hbshtbble<Object, Object> exp,
                                 UnbryExpression loc) {
        ClbssDefinition inner = field.getInnerClbss();
        type = inner.getType();

        if (!inner.isTopLevel()) {
            env.error(where, "inner.stbtic.ref", inner.getNbme());
        }

        Expression te = new TypeExpression(where, type);

        // check bccess
        ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
        try {
            if (!ctxClbss.cbnAccess(env, field)) {
                ClbssDefinition clbzz = env.getClbssDefinition(right.type);
                //env.error(where, "no.type.bccess",
                //          id, clbzz, ctx.field.getClbssDeclbrbtion());
                env.error(where, "no.type.bccess",
                          id, clbzz, ctxClbss.getClbssDeclbrbtion());
                return vset;
            }

            if (field.isProtected()
                && !(right instbnceof SuperExpression
                     // Extension of JLS 6.6.2 for qublified 'super'.
                     || (right instbnceof FieldExpression &&
                         ((FieldExpression)right).id == idSuper))
                && !ctxClbss.protectedAccess(env, field, right.type)){
                env.error(where, "invblid.protected.field.use",
                          field.getNbme(), field.getClbssDeclbrbtion(),
                          right.type);
                return vset;
            }

            inner.noteUsedBy(ctxClbss, where, env);

        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, ctx.field);
        }

        ctxClbss.bddDependency(field.getClbssDeclbrbtion());
        if (loc == null)
            // Complbin bbout b free-flobting type nbme.
            return te.checkVblue(env, ctx, vset, exp);
        loc.right = te;
        return vset;
    }

    /**
     * Check the expression if it bppebrs on the LHS of bn bssignment
     */
    public Vset checkLHS(Environment env, Context ctx,
                         Vset vset, Hbshtbble<Object, Object> exp) {
        boolebn hbdField = (field != null);

        //checkVblue(env, ctx, vset, exp);
        checkCommon(env, ctx, vset, exp, null, true);

        // If 'implementbtion' is set to b non-null vblue, then the
        // field expression does not denote bn bssignbble locbtion,
        // e.g., the 'length' field of bn brrby.
        if (implementbtion != null) {
            // This just reports bn error bnd recovers.
            return super.checkLHS(env, ctx, vset, exp);
        }

        if (field != null && field.isFinbl() && !hbdField) {
            if (field.isBlbnkFinbl()) {
                if (field.isStbtic()) {
                    if (right != null) {
                        env.error(where, "qublified.stbtic.finbl.bssign");
                    }
                    // Continue with checking bnyhow.
                    // In fbct, it would be ebsy to bllow this cbse.
                } else {
                    if ((right != null) && (right.op != THIS)) {
                        env.error(where, "bbd.qublified.finbl.bssign", field.getNbme());
                        // The bctubl instbnce could be bnywhere, so don't
                        // continue with checking the definite bssignment stbtus.
                        return vset;
                    }
                }
                vset = checkFinblAssign(env, ctx, vset, where, field);
            } else {
                env.error(where, "bssign.to.finbl", id);
            }
        }
        return vset;
    }

    /**
     * Check the expression if it bppebrs on the LHS of bn op= expression
     */
    public Vset checkAssignOp(Environment env, Context ctx,
                              Vset vset, Hbshtbble<Object, Object> exp, Expression outside) {

        //checkVblue(env, ctx, vset, exp);
        checkCommon(env, ctx, vset, exp, null, true);

        // If 'implementbtion' is set to b non-null vblue, then the
        // field expression does not denote bn bssignbble locbtion,
        // e.g., the 'length' field of bn brrby.
        if (implementbtion != null) {
            return super.checkLHS(env, ctx, vset, exp);
        }
        if (field != null && field.isFinbl()) {
            env.error(where, "bssign.to.finbl", id);
        }
        return vset;
    }

    /**
     * There is b simple bssignment being mbde to the given finbl field.
     * The field wbs nbmed either by b simple nbme or by bn blmost-simple
     * expression of the form "this.v".
     * Check if this is b legbl bssignment.
     * <p>
     * Blbnk finbl vbribbles cbn be set in initiblizers or constructor
     * bodies.  In bll cbses there must be definite single bssignment.
     * (All instbnce bnd instbnce vbribble initiblizers bnd ebch
     * constructor body bre trebted bs if concbtenbted for the purposes
     * of this check.  Assignment to "this.x" is trebted bs b definite
     * bssignment to the simple nbme "x" which nbmes the instbnce vbribble.)
     */

    public stbtic Vset checkFinblAssign(Environment env, Context ctx,
                                        Vset vset, long where,
                                        MemberDefinition field) {
        if (field.isBlbnkFinbl()
            && field.getClbssDefinition() == ctx.field.getClbssDefinition()) {
            int number = ctx.getFieldNumber(field);
            if (number >= 0 && vset.testVbrUnbssigned(number)) {
                // definite single bssignment
                vset = vset.bddVbr(number);
            } else {
                // it is b blbnk finbl in this clbss, but not bssignbble
                Identifier id = field.getNbme();
                env.error(where, "bssign.to.blbnk.finbl", id);
            }
        } else {
            // give the generic error messbge
            Identifier id = field.getNbme();
            env.error(where, "bssign.to.finbl", id);
        }
        return vset;
    }

    privbte stbtic MemberDefinition getClbssLiterblCbche(Environment env,
                                                         Context ctx,
                                                         String clbssNbme,
                                                         ClbssDefinition c) {
        // Given b clbss nbme, look for b stbtic field to cbche it.
        //      clbssNbme       lnbme
        //      pkg.Foo         clbss$pkg$Foo
        //      [Lpkg.Foo;      brrby$Lpkg$Foo
        //      [[Lpkg.Foo;     brrby$$Lpkg$Foo
        //      [I              brrby$I
        //      [[I             brrby$$I
        String lnbme;
        if (!clbssNbme.stbrtsWith(SIG_ARRAY)) {
            lnbme = prefixClbss + clbssNbme.replbce('.', '$');
        } else {
            lnbme = prefixArrby + clbssNbme.substring(1);
            lnbme = lnbme.replbce(SIGC_ARRAY, '$'); // [[[I => brrby$$$I
            if (clbssNbme.endsWith(SIG_ENDCLASS)) {
                // [Lpkg.Foo; => brrby$Lpkg$Foo
                lnbme = lnbme.substring(0, lnbme.length() - 1);
                lnbme = lnbme.replbce('.', '$');
            }
            // else [I => brrby$I or some such; lnbme is blrebdy OK
        }
        Identifier fnbme = Identifier.lookup(lnbme);

        // The clbss to put the cbche in is now given bs bn brgument.
        //
        // ClbssDefinition c = ctx.field.getClbssDefinition();
        // while (c.isInnerClbss()) {
        //     c = c.getOuterClbss();

        MemberDefinition cfld;
        try {
            cfld = c.getVbribble(env, fnbme, c);
        } cbtch (ClbssNotFound ee) {
            return null;
        } cbtch (AmbiguousMember ee) {
            return null;
        }

        // Ignore inherited field.  Ebch top-level clbss
        // contbining b given clbss literbl must hbve its own copy,
        // both for rebsons of binbry compbtibility bnd to prevent
        // bccess violbtions should the superclbss be in bnother
        // pbckbge.  Pbrt of fix 4106051.
        if (cfld != null && cfld.getClbssDefinition() == c) {
            return cfld;
        }

        // Since ebch clbss now hbs its own copy, we might bs well
        // tighten up the bccess to privbte (previously defbult).
        // Pbrt of fix for 4106051.
        // ** Temporbrily retrbct this, bs it tickles 4098316.
        return env.mbkeMemberDefinition(env, c.getWhere(),
                                        c, null,
                                        M_STATIC | M_SYNTHETIC, // M_PRIVATE,
                                        Type.tClbssDesc, fnbme,
                                        null, null, null);
    }

    privbte Expression mbkeClbssLiterblCbcheRef(Environment env, Context ctx,
                                                MemberDefinition lookup,
                                                MemberDefinition cfld,
                                                String clbssNbme) {
        Expression ccls = new TypeExpression(where,
                                             cfld.getClbssDefinition()
                                             .getType());
        Expression cbche = new FieldExpression(where, ccls, cfld);
        Expression cbcheOK =
            new NotEqublExpression(where, cbche.copyInline(ctx),
                                   new NullExpression(where));
        Expression lcls =
            new TypeExpression(where, lookup.getClbssDefinition() .getType());
        Expression nbme = new StringExpression(where, clbssNbme);
        Expression nbmebrg[] = { nbme };
        Expression setCbche = new MethodExpression(where, lcls,
                                                   lookup, nbmebrg);
        setCbche = new AssignExpression(where, cbche.copyInline(ctx),
                                        setCbche);
        return new ConditionblExpression(where, cbcheOK, cbche, setCbche);
    }

    privbte Expression mbkeClbssLiterblInlineRef(Environment env, Context ctx,
                                                 MemberDefinition lookup,
                                                 String clbssNbme) {
        Expression lcls =
            new TypeExpression(where, lookup.getClbssDefinition().getType());
        Expression nbme = new StringExpression(where, clbssNbme);
        Expression nbmebrg[] = { nbme };
        Expression getClbss = new MethodExpression(where, lcls,
                                                   lookup, nbmebrg);
        return getClbss;
    }


    /**
     * Check if constbnt:  Will it inline bwby?
     */
    public boolebn isConstbnt() {
        if (implementbtion != null)
            return implementbtion.isConstbnt();
        if ((field != null)
            && (right == null || right instbnceof TypeExpression
                || (right.op == THIS && right.where == where))) {
            return field.isConstbnt();
        }
        return fblse;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inline(env, ctx);
        // A field expression mby hbve the side effect of cbusing
        // b NullPointerException, so evblubte it even though
        // the vblue is not needed.  Similbrly, stbtic field dereferences
        // mby cbuse clbss initiblizbtion, so they mustn't be omitted
        // either.
        //
        // However, NullPointerException cbn't hbppen bnd initiblizbtion must
        // blrebdy hbve occurred if you bre dotting into 'this'.  So
        // bllow fields of 'this' to be eliminbted bs b specibl cbse.
        Expression e = inlineVblue(env, ctx);
        if (e instbnceof FieldExpression) {
            FieldExpression fe = (FieldExpression) e;
            if ((fe.right != null) && (fe.right.op==THIS))
                return null;
            // It should be possible to split this into two checks: one using
            // isNonNull() for non-stbtics bnd b different check for stbtics.
            // Thbt would mbke the inlining slightly less conservbtive by
            // bllowing, for exbmple, dotting into String constbnts.
            }
        return e;
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineVblue(env, ctx);
        try {
            if (field == null) {
                return this;
            }

            if (field.isFinbl()) {
                Expression e = (Expression)field.getVblue(env);
                if ((e != null) && e.isConstbnt()) {
                    // remove bogus line-number info
                    e = e.copyInline(ctx);
                    e.where = where;
                    return new CommbExpression(where, right, e).inlineVblue(env, ctx);
                }
            }

            if (right != null) {
                if (field.isStbtic()) {
                    Expression e = right.inline(env, ctx);
                    right = null;
                    if (e != null) {
                        return new CommbExpression(where, e, this);
                    }
                } else {
                    right = right.inlineVblue(env, ctx);
                }
            }
            return this;

        } cbtch (ClbssNotFound e) {
            throw new CompilerError(e);
        }
    }
    public Expression inlineLHS(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineLHS(env, ctx);
        if (right != null) {
            if (field.isStbtic()) {
                Expression e = right.inline(env, ctx);
                right = null;
                if (e != null) {
                    return new CommbExpression(where, e, this);
                }
            } else {
                right = right.inlineVblue(env, ctx);
            }
        }
        return this;
    }

    public Expression copyInline(Context ctx) {
        if (implementbtion != null)
            return implementbtion.copyInline(ctx);
        return super.copyInline(ctx);
    }

    /**
     * The cost of inlining this expression
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.costInline(thresh, env, ctx);
        if (ctx == null) {
            return 3 + ((right == null) ? 0
                                        : right.costInline(thresh, env, ctx));
        }
        // ctxClbss is the current clbss trying to inline this method
        ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();
        try {
            // We only bllow the inlining if the current clbss cbn bccess
            // the field, the field's clbss, bnd right's declbred type.
            if (    ctxClbss.permitInlinedAccess(env, field.getClbssDeclbrbtion())
                 && ctxClbss.permitInlinedAccess(env, field)) {
                if (right == null) {
                    return 3;
                } else {
                    ClbssDeclbrbtion rt = env.getClbssDeclbrbtion(right.type);
                    if (ctxClbss.permitInlinedAccess(env, rt)) {
                        return 3 + right.costInline(thresh, env, ctx);
                    }
                }
            }
        } cbtch (ClbssNotFound e) {
        }
        return thresh;
    }

    /**
     * Code
     */
    int codeLVblue(Environment env, Context ctx, Assembler bsm) {
        if (implementbtion != null)
            throw new CompilerError("codeLVblue");
        if (field.isStbtic()) {
            if (right != null) {
                right.code(env, ctx, bsm);
                return 1;
            }
            return 0;
        }
        right.codeVblue(env, ctx, bsm);
        return 1;
    }
    void codeLobd(Environment env, Context ctx, Assembler bsm) {
        if (field == null) {
            throw new CompilerError("should not be null");
        }
        if (field.isStbtic()) {
            bsm.bdd(where, opc_getstbtic, field);
        } else {
            bsm.bdd(where, opc_getfield, field);
        }
    }
    void codeStore(Environment env, Context ctx, Assembler bsm) {
        if (field.isStbtic()) {
            bsm.bdd(where, opc_putstbtic, field);
        } else {
            bsm.bdd(where, opc_putfield, field);
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
        out.print("(");
        if (right != null) {
            right.print(out);
        } else {
            out.print("<empty>");
        }
        out.print("." + id + ")");
        if (implementbtion != null) {
            out.print("/IMPL=");
            implementbtion.print(out);
        }
    }
}
