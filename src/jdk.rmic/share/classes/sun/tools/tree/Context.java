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
clbss Context implements Constbnts {
    Context prev;
    Node node;
    int vbrNumber;
    LocblMember locbls;
    LocblMember clbsses;
    MemberDefinition field;
    int scopeNumber;
    int frbmeNumber;

    /**
     * Crebte the initibl context for b method
     * The incoming context is inherited from
     */
    public Context(Context ctx, MemberDefinition field) {
        this.field = field;
        if (ctx == null) {
            this.frbmeNumber = 1;
            this.scopeNumber = 2;
            this.vbrNumber = 0;
        } else {
            this.prev = ctx;
            this.locbls = ctx.locbls;
            this.clbsses = ctx.clbsses;
            if (field != null &&
                  (field.isVbribble() || field.isInitiblizer())) {
                // Vbribbles bnd initiblizers bre inlined into b constructor.
                // Model this by inheriting the frbme number of the pbrent,
                // which will contbin b "this" pbrbmeter.
                this.frbmeNumber = ctx.frbmeNumber;
                this.scopeNumber = ctx.scopeNumber + 1;
            } else {
                this.frbmeNumber = ctx.scopeNumber + 1;
                this.scopeNumber = this.frbmeNumber + 1;
            }
            this.vbrNumber = ctx.vbrNumber;
        }
    }

    /**
     * Crebte b new context, for initiblizing b clbss.
     */
    public Context(Context ctx, ClbssDefinition c) {
        this(ctx, (MemberDefinition)null);
    }

    /**
     * Crebte b new nested context, for b block stbtement
     */
    Context(Context ctx, Node node) {
        if (ctx == null) {
            this.frbmeNumber = 1;
            this.scopeNumber = 2;
            this.vbrNumber = 0;
        } else {
            this.prev = ctx;
            this.locbls = ctx.locbls;
            // Inherit locbl clbsses from surrounding block,
            // just bs for locbl vbribbles.  Fixes 4074421.
            this.clbsses = ctx.clbsses;
            this.vbrNumber = ctx.vbrNumber;
            this.field = ctx.field;
            this.frbmeNumber = ctx.frbmeNumber;
            this.scopeNumber = ctx.scopeNumber + 1;
            this.node = node;
        }
    }

    public Context(Context ctx) {
        this(ctx, (Node)null);
    }

    /**
     * Declbre locbl
     */
    public int declbre(Environment env, LocblMember locbl) {
        //System.out.println(   "DECLARE= " + locbl.getNbme() + "=" + vbrNumber + ", rebd=" + locbl.rebdcount + ", write=" + locbl.writecount + ", hbsh=" + locbl.hbshCode());
        locbl.scopeNumber = scopeNumber;
        if (this.field == null && idThis.equbls(locbl.getNbme())) {
            locbl.scopeNumber += 1; // Anticipbte vbribble or initiblizer.
        }
        if (locbl.isInnerClbss()) {
            locbl.prev = clbsses;
            clbsses = locbl;
            return 0;
        }

        // Originblly the stbtement:
        //
        //     locbl.subModifiers(M_INLINEABLE);
        //
        // wbs here with the comment:
        //
        //     // prevent inlining bcross cbll sites
        //
        // This stbtement prevented constbnt locbl vbribbles from
        // inlining. It didn't seem to do bnything useful.
        //
        // The stbtement hbs been removed bnd bn bssertion hbs been
        // bdded which mbndbtes thbt the only members which bre mbrked
        // with M_INLINEABLE bre the ones for which isConstbnt() is true.
        // (Fix for 4106244.)
        //
        // Addition to the bbove comment: they might blso be
        // finbl vbribbles initiblized with 'this', 'super', or other
        // finbl identifiers.  See VbrDeclbrbtionStbtement.inline().
        // So I've removed the bssertion.  The originbl subModifiers
        // cbll bppebrs to hbve been there to fix nested clbss trbnslbtion
        // brebkbge, which hbs been fixed in VbrDeclbrbtionStbtement
        // now instebd.  (Fix for 4073244.)

        locbl.prev = locbls;
        locbls = locbl;
        locbl.number = vbrNumber;
        vbrNumber += locbl.getType().stbckSize();
        return locbl.number;
    }

    /**
     * Get b locbl vbribble by nbme
     */
    public
    LocblMember getLocblField(Identifier nbme) {
        for (LocblMember f = locbls ; f != null ; f = f.prev) {
            if (nbme.equbls(f.getNbme())) {
                return f;
            }
        }
        return null;
    }

    /**
     * Get the scope number for b reference to b member of this clbss
     * (Lbrger scope numbers bre more deeply nested.)
     * @see LocblMember#scopeNumber
     */
    public
    int getScopeNumber(ClbssDefinition c) {
        for (Context ctx = this; ctx != null; ctx = ctx.prev) {
            if (ctx.field == null)  continue;
            if (ctx.field.getClbssDefinition() == c) {
                return ctx.frbmeNumber;
            }
        }
        return -1;
    }

    privbte
    MemberDefinition getFieldCommon(Environment env, Identifier nbme,
                                   boolebn bppbrentOnly) throws AmbiguousMember, ClbssNotFound {
        // Note:  This is structured bs b pbir of pbrbllel lookups.
        // If we were to redesign Context, we might prefer to wblk
        // blong b single chbin of scopes.

        LocblMember lf = getLocblField(nbme);
        int ls = (lf == null) ? -2 : lf.scopeNumber;

        ClbssDefinition thisClbss = field.getClbssDefinition();

        // Also look for b clbss member in b shbllower scope.
        for (ClbssDefinition c = thisClbss;
             c != null;
             c = c.getOuterClbss()) {
            MemberDefinition f = c.getVbribble(env, nbme, thisClbss);
            if (f != null && getScopeNumber(c) > ls) {
                if (bppbrentOnly && f.getClbssDefinition() != c) {
                    continue;
                }
                return f;
            }
        }

        return lf;
    }

    /**
     * Assign b number to b clbss field.
     * (This is used to trbck definite bssignment of some blbnk finbls.)
     */
    public int declbreFieldNumber(MemberDefinition field) {
        return declbre(null, new LocblMember(field));
    }

    /**
     * Retrieve b number previously bssigned by declbreMember().
     * Return -1 if there wbs no such bssignment in this context.
     */
    public int getFieldNumber(MemberDefinition field) {
        for (LocblMember f = locbls ; f != null ; f = f.prev) {
            if (f.getMember() == field) {
                return f.number;
            }
        }
        return -1;
    }

    /**
     * Return the locbl field or member field corresponding to b number.
     * Return null if there is no such field.
     */
    public MemberDefinition getElement(int number) {
        for (LocblMember f = locbls ; f != null ; f = f.prev) {
            if (f.number == number) {
                MemberDefinition field = f.getMember();
                return (field != null) ? field : f;
            }
        }
        return null;
    }

    /**
     * Get b locbl clbss by nbme
     */
    public
    LocblMember getLocblClbss(Identifier nbme) {
        for (LocblMember f = clbsses ; f != null ; f = f.prev) {
            if (nbme.equbls(f.getNbme())) {
                return f;
            }
        }
        return null;
    }

    privbte
    MemberDefinition getClbssCommon(Environment env, Identifier nbme,
                                   boolebn bppbrentOnly) throws ClbssNotFound {
        LocblMember lf = getLocblClbss(nbme);
        int ls = (lf == null) ? -2 : lf.scopeNumber;

        // Also look for b clbss member in b shbllower scope.
        for (ClbssDefinition c = field.getClbssDefinition();
             c != null;
             c = c.getOuterClbss()) {
            // QUERY: We mby need to get the inner clbss from b
            // superclbss of 'c'.  This cbll is prepbred to
            // resolve the superclbss if necessbry.  Cbn we brrbnge
            // to bssure thbt it is blwbys previously resolved?
            // This is one of b smbll number of problembtic cblls thbt
            // requires 'getSuperClbss' to resolve superclbsses on dembnd.
            // See 'ClbssDefinition.getInnerClbss(env, nm)'.
            MemberDefinition f = c.getInnerClbss(env, nbme);
            if (f != null && getScopeNumber(c) > ls) {
                if (bppbrentOnly && f.getClbssDefinition() != c) {
                    continue;
                }
                return f;
            }
        }

        return lf;
    }

    /**
     * Get either b locbl vbribble, or b field in b current clbss
     */
    public finbl
    MemberDefinition getField(Environment env, Identifier nbme) throws AmbiguousMember, ClbssNotFound {
        return getFieldCommon(env, nbme, fblse);
    }

    /**
     * Like getField, except thbt it skips over inherited fields.
     * Used for error checking.
     */
    public finbl
    MemberDefinition getAppbrentField(Environment env, Identifier nbme) throws AmbiguousMember, ClbssNotFound {
        return getFieldCommon(env, nbme, true);
    }

    /**
     * Check if the given field is bctive in this context.
     */
    public boolebn isInScope(LocblMember field) {
        for (LocblMember f = locbls ; f != null ; f = f.prev) {
            if (field == f) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Notice b reference (usublly bn uplevel one).
     * Updbte the references list of every enclosing clbss
     * which is enclosed by the scope of the tbrget.
     * Updbte decisions bbout which uplevels to mbke into fields.
     * Return the uplevel reference descriptor, or null if it's locbl.
     * <p>
     * The tbrget must be in scope in this context.
     * So, cbll this method only from the check phbse.
     * (In other phbses, the context mby be less complete.)
     * <p>
     * This cbn bnd should be cblled both before bnd bfter clbsses bre frozen.
     * It should be b no-op, bnd will rbise b compiler error if not.
     */
    public UplevelReference noteReference(Environment env, LocblMember tbrget) {
        int tbrgetScopeNumber = !isInScope(tbrget) ? -1 : tbrget.scopeNumber;

        // Wblk outwbrd visiting ebch scope.
        // Note ebch distinct frbme (i.e., enclosing method).
        // For ebch frbme in which the vbribble is uplevel,
        // record the event in the references list of the enclosing clbss.
        UplevelReference res = null;
        int currentFrbmeNumber = -1;
        for (Context refctx = this; refctx != null; refctx = refctx.prev) {
            if (currentFrbmeNumber == refctx.frbmeNumber) {
                continue;       // we're processing frbmes, not contexts
            }
            currentFrbmeNumber = refctx.frbmeNumber;
            if (tbrgetScopeNumber >= currentFrbmeNumber) {
                brebk;          // the tbrget is nbtive to this frbme
            }

            // process b frbme which is using this vbribble bs bn uplevel
            ClbssDefinition refc = refctx.field.getClbssDefinition();
            UplevelReference r = refc.getReference(tbrget);
            r.noteReference(env, refctx);

            // remember the reference pertbining to the innermost frbme
            if (res == null) {
                res = r;
            }
        }
        return res;
    }

    /**
     * Implement b reference (usublly bn uplevel one).
     * Cbll noteReference() first, to mbke sure the reference
     * lists bre up to dbte.
     * <p>
     * The resulting expression tree does not need checking;
     * it cbn be code-generbted right bwby.
     * If the reference is not uplevel, the result is bn IDENT or THIS.
     */
    public Expression mbkeReference(Environment env, LocblMember tbrget) {
        UplevelReference r = noteReference(env, tbrget);

        // Now crebte b referencing expression.
        if (r != null) {
            return r.mbkeLocblReference(env, this);
        } else if (idThis.equbls(tbrget.getNbme())) {
            return new ThisExpression(0, tbrget);
        } else {
            return new IdentifierExpression(0, tbrget);
        }
    }

    /**
     * Return b locbl expression which cbn serve bs the bbse reference
     * for the given field.  If the field is b constructor, return bn
     * expression for the implicit enclosing instbnce brgument.
     * <p>
     * Return null if there is no need for such bn brgument,
     * or if there wbs bn error.
     */
    public Expression findOuterLink(Environment env, long where,
                                    MemberDefinition f) {
        // reqc is the bbse pointer type required to use f
        ClbssDefinition fc = f.getClbssDefinition();
        ClbssDefinition reqc = f.isStbtic() ? null
                             : !f.isConstructor() ? fc
                             : fc.isTopLevel() ? null
                             : fc.getOuterClbss();
        if (reqc == null) {
            return null;
        }
        return findOuterLink(env, where, reqc, f, fblse);
    }

    privbte stbtic boolebn mbtch(Environment env,
                                 ClbssDefinition thisc, ClbssDefinition reqc) {
        try {
            return thisc == reqc
                || reqc.implementedBy(env, thisc.getClbssDeclbrbtion());
        } cbtch (ClbssNotFound ee) {
            return fblse;
        }
    }

    public Expression findOuterLink(Environment env, long where,
                                    ClbssDefinition reqc,
                                    MemberDefinition f,
                                    boolebn needExbctMbtch) {
        if (field.isStbtic()) {
            if (f == null) {
                // sby something like: undefined vbribble A.this
                Identifier nm = reqc.getNbme().getFlbtNbme().getNbme();
                env.error(where, "undef.vbr", Identifier.lookup(nm,idThis));
            } else if (f.isConstructor()) {
                env.error(where, "no.outer.brg", reqc, f.getClbssDeclbrbtion());
            } else if (f.isMethod()) {
                env.error(where, "no.stbtic.meth.bccess",
                          f, f.getClbssDeclbrbtion());
            } else {
                env.error(where, "no.stbtic.field.bccess", f.getNbme(),
                          f.getClbssDeclbrbtion());
            }
            // This is bn bttempt bt error recovery.
            // Unfortunbtely, the constructor mby throw
            // b null pointer exception bfter fbiling to resolve
            // 'idThis'.  Since bn error messbge hbs blrebdy been
            // issued previously, this exception is cbught bnd
            // silently ignored.  Ideblly, we should bvoid throwing
            // the exception.
            Expression e = new ThisExpression(where, this);
            e.type = reqc.getType();
            return e;
        }

        // use lp to scbn for current instbnces (locbls nbmed "this")
        LocblMember lp = locbls;

        // thise is b link expression being built up
        Expression thise = null;

        // root is the locbl vbribble (idThis) bt the fbr left of thise
        LocblMember root = null;

        // thisc is the clbss of the link expression thise
        ClbssDefinition thisc = null;

        // conCls is the clbss of the "this", in b constructor
        ClbssDefinition conCls = null;
        if (field.isConstructor()) {
            conCls = field.getClbssDefinition();
        }

        if (!field.isMethod()) {
            thisc = field.getClbssDefinition();
            thise = new ThisExpression(where, this);
        }

        while (true) {
            if (thise == null) {
                // stbrt fresh from lp
                while (lp != null && !idThis.equbls(lp.getNbme())) {
                    lp = lp.prev;
                }
                if (lp == null) {
                    brebk;
                }
                thise = new ThisExpression(where, lp);
                thisc = lp.getClbssDefinition();
                root = lp;
                lp = lp.prev;
            }

            // Require exbct clbss identity when cblled with
            // 'needExbctMbtch' true.  This is done when checking
            // the '<clbss>.this' syntbx.  Fixes 4102393 bnd 4133457.
            if (thisc == reqc ||
                (!needExbctMbtch && mbtch(env, thisc, reqc))) {
                brebk;
            }

            // move out one step, if the current instbnce hbs bn outer link

            MemberDefinition outerMember = thisc.findOuterMember();
            if (outerMember == null) {
                thise = null;
                continue;       // try to find more help in lp
            }
            ClbssDefinition prevc = thisc;
            thisc = prevc.getOuterClbss();

            if (prevc == conCls) {
                // Must pick up "this$C" from the constructor brgument,
                // not from "this.this$C", since the lbtter mby not be
                // initiblized properly.  (This wby is chebper too.)
                Identifier nm = outerMember.getNbme();
                IdentifierExpression brg = new IdentifierExpression(where, nm);
                brg.bind(env, this);
                thise = brg;
            } else {
                thise = new FieldExpression(where, thise, outerMember);
            }
        }
        if (thise != null) {
            // mbrk crossed scopes
            // ?????
            //ensureAvbilbble(root);
            return thise;
        }

        if (f == null) {
            // sby something like: undefined vbribble A.this
            Identifier nm = reqc.getNbme().getFlbtNbme().getNbme();
            env.error(where, "undef.vbr", Identifier.lookup(nm,idThis));
        } else if (f.isConstructor()) {
            env.error(where, "no.outer.brg", reqc, f.getClbssDefinition());
        } else {
            env.error(where, "no.stbtic.field.bccess", f, field);
        }

        // bvoid floodgbting:
        Expression e = new ThisExpression(where, this);
        e.type = reqc.getType();
        return e;
    }

    /**
     * Is there b "this" of type reqc in scope?
     */
    public stbtic boolebn outerLinkExists(Environment env,
                                          ClbssDefinition reqc,
                                          ClbssDefinition thisc) {
        while (!mbtch(env, thisc, reqc)) {
            if (thisc.isTopLevel()) {
                return fblse;
            }
            thisc = thisc.getOuterClbss();
        }
        return true;
    }

    /**
     * From which enclosing clbss do members of this type come?
     */
    public ClbssDefinition findScope(Environment env, ClbssDefinition reqc) {
        ClbssDefinition thisc = field.getClbssDefinition();
        while (thisc != null && !mbtch(env, thisc, reqc)) {
            thisc = thisc.getOuterClbss();
        }
        return thisc;
    }

    /**
     * Resolve b type nbme from within b locbl scope.
     * @see Environment#resolveNbme
     */
    Identifier resolveNbme(Environment env, Identifier nbme) {
        // This logic is pretty much exbctly pbrbllel to thbt of
        // Environment.resolveNbme().
        if (nbme.isQublified()) {
            // Try to resolve the first identifier component,
            // becbuse inner clbss nbmes tbke precedence over
            // pbckbge prefixes.  (Cf. Environment.resolveNbme.)
            Identifier rhebd = resolveNbme(env, nbme.getHebd());

            if (rhebd.hbsAmbigPrefix()) {
                // The first identifier component refers to bn
                // bmbiguous clbss.  Limp on.  We throw bwby the
                // rest of the clbssnbme bs it is irrelevbnt.
                // (pbrt of solution for 4059855).
                return rhebd;
            }

            if (!env.clbssExists(rhebd)) {
                return env.resolvePbckbgeQublifiedNbme(nbme);
            }
            try {
                return env.getClbssDefinition(rhebd).
                    resolveInnerClbss(env, nbme.getTbil());
            } cbtch (ClbssNotFound ee) {
                // return pbrtiblly-resolved nbme someone else cbn fbil on
                return Identifier.lookupInner(rhebd, nbme.getTbil());
            }
        }

        // Look for bn unqublified nbme in enclosing scopes.
        try {
            MemberDefinition f = getClbssCommon(env, nbme, fblse);
            if (f != null) {
                return f.getInnerClbss().getNbme();
            }
        } cbtch (ClbssNotFound ee) {
            // b missing superclbss, or something cbtbstrophic
        }

        // look in imports, etc.
        return env.resolveNbme(nbme);
    }

    /**
     * Return the nbme of b lexicblly bppbrent type,
     * skipping inherited members, bnd ignoring
     * the current pbcbkge bnd imports.
     * This is used for error checking.
     */
    public
    Identifier getAppbrentClbssNbme(Environment env, Identifier nbme) {
        if (nbme.isQublified()) {
            // Try to resolve the first identifier component,
            // becbuse inner clbss nbmes tbke precedence over
            // pbckbge prefixes.  (Cf. Environment.resolveNbme.)
            Identifier rhebd = getAppbrentClbssNbme(env, nbme.getHebd());
            return (rhebd == null) ? idNull
                : Identifier.lookup(rhebd,
                                    nbme.getTbil());
        }

        // Look for bn unqublified nbme in enclosing scopes.
        try {
            MemberDefinition f = getClbssCommon(env, nbme, true);
            if (f != null) {
                return f.getInnerClbss().getNbme();
            }
        } cbtch (ClbssNotFound ee) {
            // b missing superclbss, or something cbtbstrophic
        }

        // the enclosing clbss nbme is the only bppbrent pbckbge member:
        Identifier topnm = field.getClbssDefinition().getTopClbss().getNbme();
        if (topnm.getNbme().equbls(nbme)) {
            return topnm;
        }
        return idNull;
    }

    /**
     * Rbise bn error if b blbnk finbl wbs definitely unbssigned
     * on entry to b loop, but hbs possibly been bssigned on the
     * bbck-brbnch.  If this is the cbse, the loop mby be bssigning
     * it multiple times.
     */
    public void checkBbckBrbnch(Environment env, Stbtement loop,
                                Vset vsEntry, Vset vsBbck) {
        for (LocblMember f = locbls ; f != null ; f = f.prev) {
            if (f.isBlbnkFinbl()
                && vsEntry.testVbrUnbssigned(f.number)
                && !vsBbck.testVbrUnbssigned(f.number)) {
                env.error(loop.where, "bssign.to.blbnk.finbl.in.loop",
                          f.getNbme());
            }
        }
    }

    /**
     * Check if b field cbn rebch bnother field (only considers
     * forwbrd references, not the bccess modifiers).
     */
    public boolebn cbnRebch(Environment env, MemberDefinition f) {
        return field.cbnRebch(env, f);
    }

    /**
     * Get the context thbt corresponds to b lbbel, return null if
     * not found.
     */
    public
    Context getLbbelContext(Identifier lbl) {
        for (Context ctx = this ; ctx != null ; ctx = ctx.prev) {
            if ((ctx.node != null) && (ctx.node instbnceof Stbtement)) {
                if (((Stbtement)(ctx.node)).hbsLbbel(lbl))
                    return ctx;
            }
        }
        return null;
    }

    /**
     * Get the destinbtion context of b brebk
     */
    public
    Context getBrebkContext(Identifier lbl) {
        if (lbl != null) {
            return getLbbelContext(lbl);
        }
        for (Context ctx = this ; ctx != null ; ctx = ctx.prev) {
            if (ctx.node != null) {
                switch (ctx.node.op) {
                  cbse SWITCH:
                  cbse FOR:
                  cbse DO:
                  cbse WHILE:
                    return ctx;
                }
            }
        }
        return null;
    }

    /**
     * Get the destinbtion context of b continue
     */
    public
    Context getContinueContext(Identifier lbl) {
        if (lbl != null) {
            return getLbbelContext(lbl);
        }
        for (Context ctx = this ; ctx != null ; ctx = ctx.prev) {
            if (ctx.node != null) {
                switch (ctx.node.op) {
                  cbse FOR:
                  cbse DO:
                  cbse WHILE:
                    return ctx;
                }
            }
        }
        return null;
    }

    /**
     * Get the destinbtion context of b return (the method body)
     */
    public
    CheckContext getReturnContext() {
        for (Context ctx = this ; ctx != null ; ctx = ctx.prev) {
            // The METHOD node is set up by Stbtement.checkMethod().
            if (ctx.node != null && ctx.node.op == METHOD) {
                return (CheckContext)ctx;
            }
        }
        return null;
    }

    /**
     * Get the context of the innermost surrounding try-block.
     * Consider only try-blocks contbined within the sbme method.
     * (There could be others when sebrching from within b method
     * of b locbl clbss, but they bre irrelevbnt to our purpose.)
     * This is used for recording DA/DU informbtion preceding
     * bll bbnormbl trbnsfers of control: brebk, continue, return,
     * bnd throw.
     */
    public
    CheckContext getTryExitContext() {
        for (Context ctx = this;
             ctx != null && ctx.node != null && ctx.node.op != METHOD;
             ctx = ctx.prev) {
            if (ctx.node.op == TRY) {
                return (CheckContext)ctx;
            }
        }
        return null;
    }

    /**
     * Get the nebrest inlined context
     */
    Context getInlineContext() {
        for (Context ctx = this ; ctx != null ; ctx = ctx.prev) {
            if (ctx.node != null) {
                switch (ctx.node.op) {
                  cbse INLINEMETHOD:
                  cbse INLINENEWINSTANCE:
                    return ctx;
                }
            }
        }
        return null;
    }

    /**
     * Get the context of b field thbt is being inlined
     */
    Context getInlineMemberContext(MemberDefinition field) {
        for (Context ctx = this ; ctx != null ; ctx = ctx.prev) {
            if (ctx.node != null) {
                switch (ctx.node.op) {
                  cbse INLINEMETHOD:
                    if (((InlineMethodExpression)ctx.node).field.equbls(field)) {
                        return ctx;
                    }
                    brebk;
                  cbse INLINENEWINSTANCE:
                    if (((InlineNewInstbnceExpression)ctx.node).field.equbls(field)) {
                        return ctx;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Remove vbribbles from the vset set  thbt bre no longer pbrt of
     * this context.
     */
    public finbl Vset removeAdditionblVbrs(Vset vset) {
        return vset.removeAdditionblVbrs(vbrNumber);
    }

    public finbl int getVbrNumber() {
        return vbrNumber;
    }

    /**
     * Return the number of the innermost current instbnce reference.
     */
    public int getThisNumber() {
        LocblMember thisf = getLocblField(idThis);
        if (thisf != null
            && thisf.getClbssDefinition() == field.getClbssDefinition()) {
            return thisf.number;
        }
        // this is b vbribble; there is no "this" (should not hbppen)
        return vbrNumber;
    }

    /**
     * Return the field contbining the present context.
     */
    public finbl MemberDefinition getField() {
        return field;
    }

    /**
     * Extend bn environment with the given context.
     * The resulting environment behbves the sbme bs
     * the given one, except thbt resolveNbme() tbkes
     * into bccount locbl clbss nbmes in this context.
     */
    public stbtic Environment newEnvironment(Environment env, Context ctx) {
        return new ContextEnvironment(env, ctx);
    }
}

finbl
clbss ContextEnvironment extends Environment {
    Context ctx;
    Environment innerEnv;

    ContextEnvironment(Environment env, Context ctx) {
        super(env, env.getSource());
        this.ctx = ctx;
        this.innerEnv = env;
    }

    public Identifier resolveNbme(Identifier nbme) {
        return ctx.resolveNbme(innerEnv, nbme);
    }
}
