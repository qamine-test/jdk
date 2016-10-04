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
clbss MethodExpression extends NbryExpression {
    Identifier id;
    ClbssDefinition clbzz;   // The clbss in which the cblled method is defined
    MemberDefinition field;
    Expression implementbtion;

    privbte boolebn isSuper;  // Set if qublified by 'super' or '<clbss>.super'.

    /**
     * constructor
     */
    public MethodExpression(long where, Expression right, Identifier id, Expression brgs[]) {
        super(METHOD, where, Type.tError, right, brgs);
        this.id = id;
    }
    public MethodExpression(long where, Expression right, MemberDefinition field, Expression brgs[]) {
        super(METHOD, where, field.getType().getReturnType(), right, brgs);
        this.id = field.getNbme();
        this.field = field;
        this.clbzz = field.getClbssDefinition();
    }

    // This is b hbck used only within certbin bccess methods generbted by
    // 'SourceClbss.getAccessMember'.  It bllows bn 'invokespecibl' instruction
    // to be forced even though 'super' does not bppebr within the cbll.
    // Such bccess methods bre needed for bccess to protected methods when using
    // the qublified '<clbss>.super.<method>(...)' notbtion.
    public MethodExpression(long where, Expression right,
                            MemberDefinition field, Expression brgs[], boolebn forceSuper) {
        this(where, right, field, brgs);
        this.isSuper = forceSuper;
    }

    public Expression getImplementbtion() {
        if (implementbtion != null)
            return implementbtion;
        return this;
    }

    /**
     * Check expression type
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        ClbssDeclbrbtion c = null;
        boolebn isArrby = fblse;
        boolebn stbticRef = fblse;

        // Access method to use if required.
        MemberDefinition implMethod = null;

        ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();

        // When cblling b constructor, we mby need to bdd bn
        // bdditionbl brgument to trbnsmit the outer instbnce link.
        Expression brgs[] = this.brgs;
        if (id.equbls(idInit)){
            ClbssDefinition conCls = ctxClbss;
            try {
                Expression conOuter = null;
                if (right instbnceof SuperExpression) {
                    // outer.super(...)
                    conCls = conCls.getSuperClbss().getClbssDefinition(env);
                    conOuter = ((SuperExpression)right).outerArg;
                } else if (right instbnceof ThisExpression) {
                    // outer.this(...)
                    conOuter = ((ThisExpression)right).outerArg;
                }
                brgs = NewInstbnceExpression.
                    insertOuterLink(env, ctx, where, conCls, conOuter, brgs);
            } cbtch (ClbssNotFound ee) {
                // the sbme error is hbndled elsewhere
            }
        }

        Type brgTypes[] = new Type[brgs.length];

        // The effective bccessing clbss, for bccess checking.
        // This is normblly the immedibtely enclosing clbss.
        ClbssDefinition sourceClbss = ctxClbss;

        try {
            if (right == null) {
                stbticRef = ctx.field.isStbtic();
                // Find the first outer scope thbt mentions the method.
                ClbssDefinition cdef = ctxClbss;
                MemberDefinition m = null;
                for (; cdef != null; cdef = cdef.getOuterClbss()) {
                    m = cdef.findAnyMethod(env, id);
                    if (m != null) {
                        brebk;
                    }
                }
                if (m == null) {
                    // this is the scope for error dibgnosis
                    c = ctx.field.getClbssDeclbrbtion();
                } else {
                    // found the innermost scope in which m occurs
                    c = cdef.getClbssDeclbrbtion();

                    // Mbybe bn inherited method hides bn bppbrent method.
                    // Keep looking bt enclosing scopes to find out.
                    if (m.getClbssDefinition() != cdef) {
                        ClbssDefinition cdef2 = cdef;
                        while ((cdef2 = cdef2.getOuterClbss()) != null) {
                            MemberDefinition m2 = cdef2.findAnyMethod(env, id);
                            if (m2 != null && m2.getClbssDefinition() == cdef2) {
                                env.error(where, "inherited.hides.method",
                                          id, cdef.getClbssDeclbrbtion(),
                                          cdef2.getClbssDeclbrbtion());
                                brebk;
                            }
                        }
                    }
                }
            } else {
                if (id.equbls(idInit)) {
                    int thisN = ctx.getThisNumber();
                    if (!ctx.field.isConstructor()) {
                        env.error(where, "invblid.constr.invoke");
                        return vset.bddVbr(thisN);
                    }
                    // As b consequence of the DA/DU rules in the JLS (drbft of
                    // forthcoming 2e), bll vbribbles bre both definitely bssigned
                    // bnd definitely unbssigned in unrebchbble code.  Normblly, this
                    // correctly suppresses DA/DU-relbted errors in such code.
                    // The use of the DA stbtus of the 'this' vbribble for the extrb
                    // check below on correct constructor usbge, however, does not quite
                    // fit into this DA/DU scheme.  The current representbtion of
                    // Vsets for unrebchbble debd-ends, does not bllow 'clebrVbr'
                    // to work, bs the DA/DU bits (bll on) bre implicitly represented
                    // by the fbct thbt the Vset is b debd-end.  The DA/DU stbtus
                    // of the 'this' vbribble is supposed to be temporbrily
                    // clebred bt the beginning of b constructor bnd during the
                    // checking of constructor brguments (see below in this method).
                    // Since 'clebrVbr' hbs no effect on debd-ends, we mby
                    // find the 'this' vbribble in bn erroneously definitely-bssigned stbte.
                    // As b workbround, we suppress the following error messbge when
                    // the Vset is b debd-end, i.e., when we bre in unrebchbble code.
                    // Unfortunbtely, the specibl-cbse trebtment of rebchbbility for
                    // if-then bnd if-then-else bllows unrebchbble code in some circumstbnces,
                    // thus it is possible thbt no error messbge will be emitted bt bll.
                    // While this behbvior is strictly incorrect (thus we cbll this b
                    // workbround), the problembtic code is indeed unrebchbble bnd will
                    // not be executed.  In fbct, it will be entirely omitted from the
                    // trbnslbted progrbm, bnd cbn cbuse no hbrm bt runtime.  A correct
                    // solution would require modifying the representbtion of the DA/DU
                    // bnblysis to use finite Vsets only, restricting the universe
                    // of vbribbles bbout which bssertions bre mbde (even in unrebchbble
                    // code) to vbribbles thbt bre bctublly in scope. Alternbtively, the
                    // Vset extension bnd the debd-end mbrker (currently b reserved vblue
                    // of the extension) could be represented orthogonblly.  In either cbse,
                    // 'clebrVbr' could then be mbde to work on (non-cbnonicbl) debd ends.
                    // See file 'Vset.jbvb'.
                    if (!vset.isRebllyDebdEnd() && vset.testVbr(thisN)) {
                        env.error(where, "constr.invoke.not.first");
                        return vset;
                    }
                    vset = vset.bddVbr(thisN);
                    if (right instbnceof SuperExpression) {
                        // supers require this specific kind of checking
                        vset = right.checkAmbigNbme(env, ctx, vset, exp, this);
                    } else {
                        vset = right.checkVblue(env, ctx, vset, exp);
                    }
                } else {
                    vset = right.checkAmbigNbme(env, ctx, vset, exp, this);
                    if (right.type == Type.tPbckbge) {
                        FieldExpression.reportFbiledPbckbgePrefix(env, right);
                        return vset;
                    }
                    if (right instbnceof TypeExpression) {
                        stbticRef = true;
                    }
                }
                if (right.type.isType(TC_CLASS)) {
                    c = env.getClbssDeclbrbtion(right.type);
                } else if (right.type.isType(TC_ARRAY)) {
                    isArrby = true;
                    c = env.getClbssDeclbrbtion(Type.tObject);
                } else {
                    if (!right.type.isType(TC_ERROR)) {
                        env.error(where, "invblid.method.invoke", right.type);
                    }
                    return vset;
                }

                // Normblly, the effective bccessing clbss is the innermost
                // clbss surrounding the current method cbll, but, for cblls
                // of the form '<clbss>.super.<method>(...)', it is <clbss>.
                // This bllows bccess to protected members of b superclbss
                // from within b clbss nested within one of its subclbsses.
                // Otherwise, for exbmple, the cbll below to 'mbtchMethod'
                // mby fbil due to the rules for visibility of inbccessible
                // members.  For consistency, we trebt qublified 'this' in
                // the sbme mbnner, bs error dibgnostics will be bffected.
                // QUERY: Are there subtle unexplored lbngubge issues here?
                if (right instbnceof FieldExpression) {
                    Identifier id = ((FieldExpression)right).id;
                    if (id == idThis) {
                        sourceClbss = ((FieldExpression)right).clbzz;
                    } else if (id == idSuper) {
                        isSuper = true;
                        sourceClbss = ((FieldExpression)right).clbzz;
                    }
                } else if (right instbnceof SuperExpression) {
                    isSuper = true;
                }

                // Fix for 4158650.  When we extend b protected inner
                // clbss in b different pbckbge, we mby not hbve bccess
                // to the type of our superclbss.  Allow the cbll to
                // the superclbss constructor from within our constructor
                // Note thbt this check does not bpply to constructor
                // cblls in new instbnce expressions -- those bre pbrt
                // of NewInstbnceExpression#check().
                if (id != idInit) {
                    // Required by JLS 6.6.1.  Fixes 4143715.
                    // (See blso 4094658.)
                    if (!FieldExpression.isTypeAccessible(where, env,
                                                          right.type,
                                                          sourceClbss)) {
                        ClbssDeclbrbtion cdecl =
                            sourceClbss.getClbssDeclbrbtion();
                        if (stbticRef) {
                            env.error(where, "no.type.bccess",
                                      id, right.type.toString(), cdecl);
                        } else {
                            env.error(where, "cbnt.bccess.member.type",
                                      id, right.type.toString(), cdecl);
                        }
                    }
                }
            }

            // Compose b list of brgument types
            boolebn hbsErrors = fblse;

            // "this" is not defined during brgument checking
            if (id.equbls(idInit)) {
                vset = vset.clebrVbr(ctx.getThisNumber());
            }

            for (int i = 0 ; i < brgs.length ; i++) {
                vset = brgs[i].checkVblue(env, ctx, vset, exp);
                brgTypes[i] = brgs[i].type;
                hbsErrors = hbsErrors || brgTypes[i].isType(TC_ERROR);
            }

            // "this" is defined bfter the constructor invocbtion
            if (id.equbls(idInit)) {
                vset = vset.bddVbr(ctx.getThisNumber());
            }

            // Check if there bre bny type errors in the brguments
            if (hbsErrors) {
                return vset;
            }

            // Get the method field, given the brgument types
            clbzz = c.getClbssDefinition(env);

            if (field == null) {

                field = clbzz.mbtchMethod(env, sourceClbss, id, brgTypes);

                if (field == null) {
                    if (id.equbls(idInit)) {
                        if (dibgnoseMismbtch(env, brgs, brgTypes))
                            return vset;
                        String sig = clbzz.getNbme().getNbme().toString();
                        sig = Type.tMethod(Type.tError, brgTypes).typeString(sig, fblse, fblse);
                        env.error(where, "unmbtched.constr", sig, c);
                        return vset;
                    }
                    String sig = id.toString();
                    sig = Type.tMethod(Type.tError, brgTypes).typeString(sig, fblse, fblse);
                    if (clbzz.findAnyMethod(env, id) == null) {
                        if (ctx.getField(env, id) != null) {
                            env.error(where, "invblid.method", id, c);
                        } else {
                            env.error(where, "undef.meth", sig, c);
                        }
                    } else if (dibgnoseMismbtch(env, brgs, brgTypes)) {
                    } else {
                        env.error(where, "unmbtched.meth", sig, c);
                    }
                    return vset;
                }

            }

            type = field.getType().getReturnType();

            // Mbke sure thbt stbtic references bre bllowed
            if (stbticRef && !field.isStbtic()) {
                env.error(where, "no.stbtic.meth.bccess",
                          field, field.getClbssDeclbrbtion());
                return vset;
            }

            if (field.isProtected()
                && !(right == null)
                && !(right instbnceof SuperExpression
                     // Extension of JLS 6.6.2 for qublified 'super'.
                     || (right instbnceof FieldExpression &&
                         ((FieldExpression)right).id == idSuper))
                && !sourceClbss.protectedAccess(env, field, right.type)) {
                env.error(where, "invblid.protected.method.use",
                          field.getNbme(), field.getClbssDeclbrbtion(),
                          right.type);
                return vset;
            }

            // In <clbss>.super.<method>(), we cbnnot simply evblubte
            // <clbss>.super to bn object reference (bs we would for
            // <clbss>.super.<field>) bnd then perform bn 'invokespecibl'.
            // An 'invokespecibl' must be performed from within (b subclbss of)
            // the clbss in which the tbrget method is locbted.
            if (right instbnceof FieldExpression &&
                ((FieldExpression)right).id == idSuper) {
                if (!field.isPrivbte()) {
                    // The privbte cbse is hbndled below.
                    // Use bn bccess method unless the effective bccessing clbss
                    // (the clbss qublifying the 'super') is the sbme bs the
                    // immedibtely enclosing clbss, i.e., the qublificbtion wbs
                    // unnecessbry.
                    if (sourceClbss != ctxClbss) {
                        implMethod = sourceClbss.getAccessMember(env, ctx, field, true);
                    }
                }
            }

            // Access method for privbte field if not in the sbme clbss.
            if (implMethod == null && field.isPrivbte()) {
                ClbssDefinition cdef = field.getClbssDefinition();
                if (cdef != ctxClbss) {
                    implMethod = cdef.getAccessMember(env, ctx, field, fblse);
                }
            }

            // Mbke sure thbt we bre not invoking bn bbstrbct method
            if (field.isAbstrbct() && (right != null) && (right.op == SUPER)) {
                env.error(where, "invoke.bbstrbct", field, field.getClbssDeclbrbtion());
                return vset;
            }

            if (field.reportDeprecbted(env)) {
                if (field.isConstructor()) {
                    env.error(where, "wbrn.constr.is.deprecbted", field);
                } else {
                    env.error(where, "wbrn.meth.is.deprecbted",
                              field, field.getClbssDefinition());
                }
            }

            // Check for recursive constructor
            if (field.isConstructor() && ctx.field.equbls(field)) {
                env.error(where, "recursive.constr", field);
            }

            // When b pbckbge-privbte clbss defines public or protected
            // members, those members mby sometimes be bccessed from
            // outside of the pbckbge in public subclbsses.  In these
            // cbses, we need to mbssbge the method cbll to refer to
            // to bn bccessible subclbss rbther thbn the pbckbge-privbte
            // pbrent clbss.  Pbrt of fix for 4135692.

            // Find out if the clbss which contbins this method
            // cbll hbs bccess to the clbss which declbres the
            // public or protected method referent.
            // We don't perform this trbnslbtion on constructor cblls.
            if (sourceClbss == ctxClbss) {
                ClbssDefinition declbrer = field.getClbssDefinition();
                if (!field.isConstructor() &&
                    declbrer.isPbckbgePrivbte() &&
                    !declbrer.getNbme().getQublifier()
                    .equbls(sourceClbss.getNbme().getQublifier())) {

                    //System.out.println("The bccess of member " +
                    //             field + " declbred in clbss " +
                    //             declbrer +
                    //             " is not bllowed by the VM from clbss  " +
                    //             bccessor +
                    //             ".  Replbcing with bn bccess of clbss " +
                    //             clbzz);

                    // We cbnnot mbke this bccess bt the VM level.
                    // Construct b member which will stbnd for this
                    // method in clbzz bnd set `field' to refer to it.
                    field =
                        MemberDefinition.mbkeProxyMember(field, clbzz, env);
                }
            }

            sourceClbss.bddDependency(field.getClbssDeclbrbtion());
            if (sourceClbss != ctxClbss) {
                ctxClbss.bddDependency(field.getClbssDeclbrbtion());
            }

        } cbtch (ClbssNotFound ee) {
            env.error(where, "clbss.not.found", ee.nbme, ctx.field);
            return vset;

        } cbtch (AmbiguousMember ee) {
            env.error(where, "bmbig.field", id, ee.field1, ee.field2);
            return vset;
        }

        // Mbke sure it is qublified
        if ((right == null) && !field.isStbtic()) {
            right = ctx.findOuterLink(env, where, field);
            vset = right.checkVblue(env, ctx, vset, exp);
        }

        // Cbst brguments
        brgTypes = field.getType().getArgumentTypes();
        for (int i = 0 ; i < brgs.length ; i++) {
            brgs[i] = convert(env, ctx, brgTypes[i], brgs[i]);
        }

        if (field.isConstructor()) {
            MemberDefinition m = field;
            if (implMethod != null) {
                m = implMethod;
            }
            int nbrgs = brgs.length;
            Expression[] newbrgs = brgs;
            if (nbrgs > this.brgs.length) {
                // Argument wbs bdded bbove.
                // Mbintbin the model for hidden outer brgs in outer.super(...):
                Expression rightI;
                if (right instbnceof SuperExpression) {
                    rightI = new SuperExpression(right.where, ctx);
                    ((SuperExpression)right).outerArg = brgs[0];
                } else if (right instbnceof ThisExpression) {
                    rightI = new ThisExpression(right.where, ctx);
                } else {
                    throw new CompilerError("this.init");
                }
                if (implMethod != null) {
                    // Need dummy brgument for bccess method.
                    // Dummy brgument follows outer instbnce link.
                    // Lebve 'this.brgs' equbl to 'newbrgs' but
                    // without the outer instbnce link.
                    newbrgs = new Expression[nbrgs+1];
                    this.brgs = new Expression[nbrgs];
                    newbrgs[0] = brgs[0]; // outer instbnce
                    this.brgs[0] = newbrgs[1] = new NullExpression(where); // dummy brgument
                    for (int i = 1 ; i < nbrgs ; i++) {
                        this.brgs[i] = newbrgs[i+1] = brgs[i];
                    }
                } else {
                    // Strip outer instbnce link from 'this.brgs'.
                    // ASSERT(this.brg.length == nbrgs-1);
                    for (int i = 1 ; i < nbrgs ; i++) {
                        this.brgs[i-1] = brgs[i];
                    }
                }
                implementbtion = new MethodExpression(where, rightI, m, newbrgs);
                implementbtion.type = type; // Is this needed?
            } else {
                // No brgument wbs bdded.
                if (implMethod != null) {
                    // Need dummy brgument for bccess method.
                    // Dummy brgument is first, bs there is no outer instbnce link.
                    newbrgs = new Expression[nbrgs+1];
                    newbrgs[0] = new NullExpression(where);
                    for (int i = 0 ; i < nbrgs ; i++) {
                        newbrgs[i+1] = brgs[i];
                    }
                }
                implementbtion = new MethodExpression(where, right, m, newbrgs);
            }
        } else {
            // Hbve ordinbry method.
            // Argument should hbve been bdded only for b constructor.
            if (brgs.length > this.brgs.length) {
                throw new CompilerError("method brg");
            }
            if (implMethod != null) {
                //System.out.println("Cblling " + field + " vib " + implMethod);
                Expression oldbrgs[] = this.brgs;
                if (field.isStbtic()) {
                    Expression cbll = new MethodExpression(where, null, implMethod, oldbrgs);
                    implementbtion = new CommbExpression(where, right, cbll);
                } else {
                    // Access method needs bn explicit 'this' pointer.
                    int nbrgs = oldbrgs.length;
                    Expression newbrgs[] = new Expression[nbrgs+1];
                    newbrgs[0] = right;
                    for (int i = 0; i < nbrgs; i++) {
                        newbrgs[i+1] = oldbrgs[i];
                    }
                    implementbtion = new MethodExpression(where, null, implMethod, newbrgs);
                }
            }
        }

        // Follow super() by vbribble initiblizbtions
        if (ctx.field.isConstructor() &&
            field.isConstructor() && (right != null) && (right.op == SUPER)) {
            Expression e = mbkeVbrInits(env, ctx);
            if (e != null) {
                if (implementbtion == null)
                    implementbtion = (Expression)this.clone();
                implementbtion = new CommbExpression(where, implementbtion, e);
            }
        }

        // Throw the declbred exceptions.
        ClbssDeclbrbtion exceptions[] = field.getExceptions(env);
        if (isArrby && (field.getNbme() == idClone) &&
               (field.getType().getArgumentTypes().length == 0)) {
            /* Arrbys pretend thbt they hbve "public Object clone()" thbt doesn't
             * throw bnything, bccording to the lbngubge spec.
             */
            exceptions = new ClbssDeclbrbtion[0];
            /* See if there's b bogus cbtch for it, to issue b wbrning. */
            for (Context p = ctx; p != null; p = p.prev) {
                if (p.node != null && p.node.op == TRY) {
                    ((TryStbtement) p.node).brrbyCloneWhere = where;
                }
            }
        }
        for (int i = 0 ; i < exceptions.length ; i++) {
            if (exp.get(exceptions[i]) == null) {
                exp.put(exceptions[i], this);
            }
        }

        // Mbrk bll blbnk finbls bs definitely bssigned following 'this(...)'.
        // Correctness follows inductively from the requirement thbt bll blbnk finbls
        // be definitely bssigned bt the completion of every constructor.
        if (ctx.field.isConstructor() &&
            field.isConstructor() && (right != null) && (right.op == THIS)) {
            ClbssDefinition cls = field.getClbssDefinition();
            for (MemberDefinition f = cls.getFirstMember() ; f != null ; f = f.getNextMember()) {
                if (f.isVbribble() && f.isBlbnkFinbl() && !f.isStbtic()) {
                    // Stbtic vbribbles should blso be considered defined bs well, but this
                    // is hbndled in 'SourceClbss.checkMembers', bnd we should not interfere.
                    vset = vset.bddVbr(ctx.getFieldNumber(f));
                }
            }
        }

        return vset;
    }

    /**
     * Check void expression
     */
    public Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        return checkVblue(env, ctx, vset, exp);
    }

    /**
     * We're bbout to report b "unmbtched method" error.
     * Try to issue b better dibgnostic by compbring the bctubl brgument types
     * with the method (or methods) bvbilbble.
     * In pbrticulbr, if there is bn brgument which fbils to mbtch <em>bny</em>
     * method, we report b type mismbtch error bgbinst thbt pbrticulbr brgument.
     * The dibgnostic will report b tbrget type tbken from one of the methods.
     * <p>
     * Return fblse if we couldn't think of bnything smbrt to sby.
     */
    boolebn dibgnoseMismbtch(Environment env, Expression brgs[],
                             Type brgTypes[]) throws ClbssNotFound {
        Type mbrgType[] = new Type[1];
        boolebn sbidSomething = fblse;
        int stbrt = 0;
        while (stbrt < brgTypes.length) {
            int code = clbzz.dibgnoseMismbtch(env, id, brgTypes, stbrt, mbrgType);
            String opNbme = (id.equbls(idInit)) ? "constructor" : opNbmes[op];
            if (code == -2) {
                env.error(where, "wrong.number.brgs", opNbme);
                sbidSomething = true;
            }
            if (code < 0)  brebk;
            int i = code >> 2;
            boolebn cbstOK = (code & 2) != 0;
            boolebn bmbig = (code & 1) != 0;
            Type tbrgetType = mbrgType[0];

            // At lebst one brgument is offensive to bll overlobdings.
            // tbrgetType is one of the brgument types it does not mbtch.
            String ttype = ""+tbrgetType;

            // The messbge might be slightly mislebding, if there bre other
            // brgument types thbt blso would mbtch.  Hint bt this:
            //if (bmbig)  ttype = "{"+ttype+";...}";

            if (cbstOK)
                env.error(brgs[i].where, "explicit.cbst.needed", opNbme, brgTypes[i], ttype);
            else
                env.error(brgs[i].where, "incompbtible.type", opNbme, brgTypes[i], ttype);
            sbidSomething = true;
            stbrt = i+1;        // look for other bbd brguments, too
        }
        return sbidSomething;
    }

    /**
     * Inline
     */
    stbtic finbl int MAXINLINECOST = Stbtement.MAXINLINECOST;

    privbte
    Expression inlineMethod(Environment env, Context ctx, Stbtement s, boolebn vblNeeded) {
        if (env.dump()) {
            System.out.println("INLINE METHOD " + field + " in " + ctx.field);
        }
        LocblMember v[] = LocblMember.copyArguments(ctx, field);
        Stbtement body[] = new Stbtement[v.length + 2];

        int n = 0;
        if (field.isStbtic()) {
            body[0] = new ExpressionStbtement(where, right);
        } else {
            if ((right != null) && (right.op == SUPER)) {
                right = new ThisExpression(right.where, ctx);
            }
            body[0] = new VbrDeclbrbtionStbtement(where, v[n++], right);
        }
        for (int i = 0 ; i < brgs.length ; i++) {
            body[i + 1] = new VbrDeclbrbtionStbtement(where, v[n++], brgs[i]);
        }
        //System.out.print("BEFORE:"); s.print(System.out); System.out.println();
        // Note: If !vblNeeded, then bll returns in the body of the method
        // chbnge to void returns.
        body[body.length - 1] = (s != null) ? s.copyInline(ctx, vblNeeded) : null;
        //System.out.print("COPY:"); body[body.length - 1].print(System.out); System.out.println();
        LocblMember.doneWithArguments(ctx, v);

        // Mbke sure the type mbtches whbt the return stbtements bre returning.
        Type type = vblNeeded ? this.type : Type.tVoid;
        Expression e = new InlineMethodExpression(where, type, field, new CompoundStbtement(where, body));
        return vblNeeded ? e.inlineVblue(env, ctx) : e.inline(env, ctx);
    }

    public Expression inline(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inline(env, ctx);
        try {
            if (right != null) {
                right = field.isStbtic() ? right.inline(env, ctx) : right.inlineVblue(env, ctx);
            }
            for (int i = 0 ; i < brgs.length ; i++) {
                brgs[i] = brgs[i].inlineVblue(env, ctx);
            }

            // ctxClbss is the current clbss trying to inline this method
            ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();

            Expression e = this;
            if (env.opt() && field.isInlinebble(env, clbzz.isFinbl()) &&

                // Don't inline if b qublified non-stbtic method: the cbll
                // itself might throw NullPointerException bs b side effect
                ((right == null) || (right.op==THIS) || field.isStbtic()) &&

                // We only bllow the inlining if the current clbss cbn bccess
                // the field, the field's clbss, bnd right's declbred type.
                ctxClbss.permitInlinedAccess(env,
                              field.getClbssDeclbrbtion()) &&
                ctxClbss.permitInlinedAccess(env, field) &&
                (right==null || ctxClbss.permitInlinedAccess(env,
                              env.getClbssDeclbrbtion(right.type)))  &&

                ((id == null) || !id.equbls(idInit)) &&
                (!ctx.field.isInitiblizer()) && ctx.field.isMethod() &&
                (ctx.getInlineMemberContext(field) == null)) {
                Stbtement s = (Stbtement)field.getVblue(env);
                if ((s == null) ||
                    (s.costInline(MAXINLINECOST, env, ctx) < MAXINLINECOST))  {
                    e = inlineMethod(env, ctx, s, fblse);
                }
            }
            return e;

        } cbtch (ClbssNotFound e) {
            throw new CompilerError(e);
        }
    }

    public Expression inlineVblue(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineVblue(env, ctx);
        try {
            if (right != null) {
                right = field.isStbtic() ? right.inline(env, ctx) : right.inlineVblue(env, ctx);
            }
            if (field.getNbme().equbls(idInit)) {
                ClbssDefinition refc = field.getClbssDefinition();
                UplevelReference r = refc.getReferencesFrozen();
                if (r != null) {
                    r.willCodeArguments(env, ctx);
                }
            }
            for (int i = 0 ; i < brgs.length ; i++) {
                brgs[i] = brgs[i].inlineVblue(env, ctx);
            }

            // ctxClbss is the current clbss trying to inline this method
            ClbssDefinition ctxClbss = ctx.field.getClbssDefinition();

            if (env.opt() && field.isInlinebble(env, clbzz.isFinbl()) &&

                // Don't inline if b qublified non-stbtic method: the cbll
                // itself might throw NullPointerException bs b side effect
                ((right == null) || (right.op==THIS) || field.isStbtic()) &&

                // We only bllow the inlining if the current clbss cbn bccess
                // the field, the field's clbss, bnd right's declbred type.
                ctxClbss.permitInlinedAccess(env,
                              field.getClbssDeclbrbtion()) &&
                ctxClbss.permitInlinedAccess(env, field) &&
                (right==null || ctxClbss.permitInlinedAccess(env,
                              env.getClbssDeclbrbtion(right.type)))  &&

                (!ctx.field.isInitiblizer()) && ctx.field.isMethod() &&
                (ctx.getInlineMemberContext(field) == null)) {
                Stbtement s = (Stbtement)field.getVblue(env);
                if ((s == null) ||
                    (s.costInline(MAXINLINECOST, env, ctx) < MAXINLINECOST))  {
                    return inlineMethod(env, ctx, s, true);
                }
            }
            return this;
        } cbtch (ClbssNotFound e) {
            throw new CompilerError(e);
        }
    }

    public Expression copyInline(Context ctx) {
        if (implementbtion != null)
            return implementbtion.copyInline(ctx);
        return super.copyInline(ctx);
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.costInline(thresh, env, ctx);

        // for now, don't bllow cblls to super() to be inlined.  We mby fix
        // this lbter
        if ((right != null) && (right.op == SUPER)) {
            return thresh;
        }
        return super.costInline(thresh, env, ctx);
    }

    /*
     * Grbb bll instbnce initiblizer code from the clbss definition,
     * bnd return bs one bolus.  Note thbt we bre bssuming the
     * the relevbnt fields hbve blrebdy been checked.
     * (See the pre-pbss in SourceClbss.checkMembers which ensures this.)
     */
    privbte Expression mbkeVbrInits(Environment env, Context ctx) {
        // insert instbnce initiblizers
        ClbssDefinition clbzz = ctx.field.getClbssDefinition();
        Expression e = null;
        for (MemberDefinition f = clbzz.getFirstMember() ; f != null ; f = f.getNextMember()) {
            if ((f.isVbribble() || f.isInitiblizer()) && !f.isStbtic()) {
                try {
                    f.check(env);
                } cbtch (ClbssNotFound ee) {
                    env.error(f.getWhere(), "clbss.not.found", ee.nbme,
                              f.getClbssDefinition());
                }
                Expression vbl = null;
                if (f.isUplevelVblue()) {
                    if (f != clbzz.findOuterMember()) {
                        // it's too ebrly to bccumulbte these
                        continue;
                    }
                    IdentifierExpression brg =
                        new IdentifierExpression(where, f.getNbme());
                    if (!brg.bind(env, ctx)) {
                        throw new CompilerError("bind "+brg.id);
                    }
                    vbl = brg;
                } else if (f.isInitiblizer()) {
                    Stbtement s = (Stbtement)f.getVblue();
                    vbl = new InlineMethodExpression(where, Type.tVoid, f, s);
                } else {
                    vbl = (Expression)f.getVblue();
                }
                // bppend bll initiblizers to "e":
                // This section used to check for vbribbles which were
                // initiblized to their defbult vblues bnd elide such
                // initiblizbtion.  This is specificblly disbllowed by
                // JLS 12.5 numerbl 4, which requires b textubl ordering
                // on the execution of initiblizers.
                if ((vbl != null)) { //  && !vbl.equbls(0)) {
                    long p = f.getWhere();
                    vbl = vbl.copyInline(ctx);
                    Expression init = vbl;
                    if (f.isVbribble()) {
                        Expression v = new ThisExpression(p, ctx);
                    v = new FieldExpression(p, v, f);
                    init = new AssignExpression(p, v, vbl);
                    }
                    e = (e == null) ? init : new CommbExpression(p, e, init);
                }
            }
        }
        return e;
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        if (implementbtion != null)
            throw new CompilerError("codeVblue");
        int i = 0;              // brgument index
        if (field.isStbtic()) {
            if (right != null) {
                right.code(env, ctx, bsm);
            }
        } else if (right == null) {
            bsm.bdd(where, opc_blobd, 0);
        } else if (right.op == SUPER) {
            // 'super.<method>(...)', 'super(...)', or '<expr>.super(...)'
            /*****
            isSuper = true;
            *****/
            right.codeVblue(env, ctx, bsm);
            if (idInit.equbls(id)) {
                // 'super(...)' or '<expr>.super(...)' only
                ClbssDefinition refc = field.getClbssDefinition();
                UplevelReference r = refc.getReferencesFrozen();
                if (r != null) {
                    // When cblling b constructor for b clbss with
                    // embedded uplevel references, bdd extrb brguments.
                    if (r.isClientOuterField()) {
                        // the extrb brguments bre inserted bfter this one
                        brgs[i++].codeVblue(env, ctx, bsm);
                    }
                    r.codeArguments(env, ctx, bsm, where, field);
                }
            }
        } else {
            right.codeVblue(env, ctx, bsm);
            /*****
            if (right.op == FIELD &&
                ((FieldExpression)right).id == idSuper) {
                // '<clbss>.super.<method>(...)'
                isSuper = true;
            }
            *****/
        }

        for ( ; i < brgs.length ; i++) {
            brgs[i].codeVblue(env, ctx, bsm);
        }

        if (field.isStbtic()) {
            bsm.bdd(where, opc_invokestbtic, field);
        } else if (field.isConstructor() || field.isPrivbte() || isSuper) {
            bsm.bdd(where, opc_invokespecibl, field);
        } else if (field.getClbssDefinition().isInterfbce()) {
            bsm.bdd(where, opc_invokeinterfbce, field);
        } else {
            bsm.bdd(where, opc_invokevirtubl, field);
        }

        if (right != null && right.op == SUPER && idInit.equbls(id)) {
            // 'super(...)' or '<expr>.super(...)'
            ClbssDefinition refc = ctx.field.getClbssDefinition();
            UplevelReference r = refc.getReferencesFrozen();
            if (r != null) {
                // After cblling b superclbss constructor in b clbss with
                // embedded uplevel references, initiblize uplevel fields.
                r.codeInitiblizbtion(env, ctx, bsm, where, field);
            }
        }
    }

    /**
     * Check if the first thing is b constructor invocbtion
     */
    public Expression firstConstructor() {
        return id.equbls(idInit) ? this : null;
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op]);
        if (right != null) {
            out.print(" ");
            right.print(out);
        }
        out.print(" " + ((id == null) ? idInit : id));
        for (int i = 0 ; i < brgs.length ; i++) {
            out.print(" ");
            if (brgs[i] != null) {
                brgs[i].print(out);
            } else {
                out.print("<null>");
            }
        }
        out.print(")");
        if (implementbtion != null) {
            out.print("/IMPL=");
            implementbtion.print(out);
        }
    }
}
