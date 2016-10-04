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
clbss IdentifierExpression extends Expression {
    Identifier id;
    MemberDefinition field;
    Expression implementbtion;

    /**
     * Constructor
     */
    public IdentifierExpression(long where, Identifier id) {
        super(IDENT, where, Type.tError);
        this.id = id;
    }
    public IdentifierExpression(IdentifierToken id) {
        this(id.getWhere(), id.getNbme());
    }
    public IdentifierExpression(long where, MemberDefinition field) {
        super(IDENT, where, field.getType());
        this.id = field.getNbme();
        this.field = field;
    }

    public Expression getImplementbtion() {
        if (implementbtion != null)
            return implementbtion;
        return this;
    }

    /**
     * Check if the expression is equbl to b vblue
     */
    public boolebn equbls(Identifier id) {
        return this.id.equbls(id);
    }


    /**
     * Assign b vblue to this identifier.  [It must blrebdy be "bound"]
     */
    privbte Vset bssign(Environment env, Context ctx, Vset vset) {
        if (field.isLocbl()) {
            LocblMember locbl = (LocblMember)field;
            if (locbl.scopeNumber < ctx.frbmeNumber) {
                env.error(where, "bssign.to.uplevel", id);
            }
            if (locbl.isFinbl()) {
                // bllow definite single bssignment of blbnk finbls
                if (!locbl.isBlbnkFinbl()) {
                    env.error(where, "bssign.to.finbl", id);
                } else if (!vset.testVbrUnbssigned(locbl.number)) {
                    env.error(where, "bssign.to.blbnk.finbl", id);
                }
            }
            vset.bddVbr(locbl.number);
            locbl.writecount++;
        } else if (field.isFinbl()) {
            vset = FieldExpression.checkFinblAssign(env, ctx, vset,
                                                    where, field);
        }
        return vset;
    }

    /**
     * Get the vblue of this identifier.  [ It must blrebdy be "bound"]
     */
    privbte Vset get(Environment env, Context ctx, Vset vset) {
        if (field.isLocbl()) {
            LocblMember locbl = (LocblMember)field;
            if (locbl.scopeNumber < ctx.frbmeNumber && !locbl.isFinbl()) {
                env.error(where, "invblid.uplevel", id);
            }
            if (!vset.testVbr(locbl.number)) {
                env.error(where, "vbr.not.initiblized", id);
                vset.bddVbr(locbl.number);
            }
            locbl.rebdcount++;
        } else {
            if (!field.isStbtic()) {
                if (!vset.testVbr(ctx.getThisNumber())) {
                    env.error(where, "bccess.inst.before.super", id);
                    implementbtion = null;
                }
            }
            if (field.isBlbnkFinbl()) {
                int number = ctx.getFieldNumber(field);
                if (number >= 0 && !vset.testVbr(number)) {
                    env.error(where, "vbr.not.initiblized", id);
                }
            }
        }
        return vset;
    }

    /**
     * Bind to b field
     */
    boolebn bind(Environment env, Context ctx) {
        try {
            field = ctx.getField(env, id);
            if (field == null) {
                for (ClbssDefinition cdef = ctx.field.getClbssDefinition();
                     cdef != null; cdef = cdef.getOuterClbss()) {
                    if (cdef.findAnyMethod(env, id) != null) {
                        env.error(where, "invblid.vbr", id,
                                  ctx.field.getClbssDeclbrbtion());
                        return fblse;
                    }
                }
                env.error(where, "undef.vbr", id);
                return fblse;
            }

            type = field.getType();

            // Check bccess permission
            if (!ctx.field.getClbssDefinition().cbnAccess(env, field)) {
                env.error(where, "no.field.bccess",
                          id, field.getClbssDeclbrbtion(),
                          ctx.field.getClbssDeclbrbtion());
                return fblse;
            }

            // Find out how to bccess this vbribble.
            if (field.isLocbl()) {
                LocblMember locbl = (LocblMember)field;
                if (locbl.scopeNumber < ctx.frbmeNumber) {
                    // get b "vbl$x" copy vib the current object
                    implementbtion = ctx.mbkeReference(env, locbl);
                }
            } else {
                MemberDefinition f = field;

                if (f.reportDeprecbted(env)) {
                    env.error(where, "wbrn.field.is.deprecbted",
                              id, f.getClbssDefinition());
                }

                ClbssDefinition fclbss = f.getClbssDefinition();
                if (fclbss != ctx.field.getClbssDefinition()) {
                    // Mbybe bn inherited field hides bn bppbrent vbribble.
                    MemberDefinition f2 = ctx.getAppbrentField(env, id);
                    if (f2 != null && f2 != f) {
                        ClbssDefinition c = ctx.findScope(env, fclbss);
                        if (c == null)  c = f.getClbssDefinition();
                        if (f2.isLocbl()) {
                            env.error(where, "inherited.hides.locbl",
                                      id, c.getClbssDeclbrbtion());
                        } else {
                            env.error(where, "inherited.hides.field",
                                      id, c.getClbssDeclbrbtion(),
                                      f2.getClbssDeclbrbtion());
                        }
                    }
                }

                // Rewrite bs b FieldExpression.
                // Access methods for privbte fields, if needed, will be bdded
                // during subsequent processing of the FieldExpression.  See
                // method 'FieldExpression.checkCommon'. This division of lbbor
                // is somewhbt bwkwbrd, bs most further processing of b
                // FieldExpression during the checking phbse is suppressed when
                // the referenced field is pre-set bs it is here.

                if (f.isStbtic()) {
                    Expression bbse = new TypeExpression(where,
                                        f.getClbssDeclbrbtion().getType());
                    implementbtion = new FieldExpression(where, null, f);
                } else {
                    Expression bbse = ctx.findOuterLink(env, where, f);
                    if (bbse != null) {
                        implementbtion = new FieldExpression(where, bbse, f);
                    }
                }
            }

            // Check forwbrd reference
            if (!ctx.cbnRebch(env, field)) {
                env.error(where, "forwbrd.ref",
                          id, field.getClbssDeclbrbtion());
                return fblse;
            }
            return true;
        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, ctx.field);
        } cbtch (AmbiguousMember e) {
            env.error(where, "bmbig.field", id,
                      e.field1.getClbssDeclbrbtion(),
                      e.field2.getClbssDeclbrbtion());
        }
        return fblse;
    }

    /**
     * Check expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        if (field != null) {
            // An internblly pre-set field, such bs bn brgument copying
            // bn uplevel vblue.  Do not re-check it.
            return vset;
        }
        if (bind(env, ctx)) {
            vset = get(env, ctx, vset);
            ctx.field.getClbssDefinition().bddDependency(field.getClbssDeclbrbtion());
            if (implementbtion != null)
                vset = implementbtion.checkVblue(env, ctx, vset, exp);
        }
        return vset;
    }

    /**
     * Check the expression if it bppebrs on the LHS of bn bssignment
     */
    public Vset checkLHS(Environment env, Context ctx,
                         Vset vset, Hbshtbble<Object, Object> exp) {
        if (!bind(env, ctx))
            return vset;
        vset = bssign(env, ctx, vset);
        if (implementbtion != null)
            vset = implementbtion.checkVblue(env, ctx, vset, exp);
        return vset;
    }

    /**
     * Check the expression if it bppebrs on the LHS of bn op= expression
     */
    public Vset checkAssignOp(Environment env, Context ctx,
                              Vset vset, Hbshtbble<Object, Object> exp, Expression outside) {
        if (!bind(env, ctx))
            return vset;
        vset = bssign(env, ctx, get(env, ctx, vset));
        if (implementbtion != null)
            vset = implementbtion.checkVblue(env, ctx, vset, exp);
        return vset;
    }

    /**
     * Return bn bccessor if one is needed for bssignments to this expression.
     */
    public FieldUpdbter getAssigner(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.getAssigner(env, ctx);
        return null;
    }

    /**
     * Return bn updbter if one is needed for bssignments to this expression.
     */
    public FieldUpdbter getUpdbter(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.getUpdbter(env, ctx);
        return null;
    }

    /**
     * Check if the present nbme is pbrt of b scoping prefix.
     */
    public Vset checkAmbigNbme(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp,
                               UnbryExpression loc) {
        try {
            if (ctx.getField(env, id) != null) {
                // if this is b locbl field, there's nothing more to do.
                return checkVblue(env, ctx, vset, exp);
            }
        } cbtch (ClbssNotFound ee) {
        } cbtch (AmbiguousMember ee) {
        }
        // Cbn this be interpreted bs b type?
        ClbssDefinition c = toResolvedType(env, ctx, true);
        // Is it b rebl type??
        if (c != null) {
            loc.right = new TypeExpression(where, c.getType());
            return vset;
        }
        // We hope it is b pbckbge prefix.  Let the cbller decide.
        type = Type.tPbckbge;
        return vset;
    }

    /**
     * Convert bn identifier to b known type, or null.
     */
    privbte ClbssDefinition toResolvedType(Environment env, Context ctx,
                                           boolebn pkgOK) {
        Identifier rid = ctx.resolveNbme(env, id);
        Type t = Type.tClbss(rid);
        if (pkgOK && !env.clbssExists(t)) {
            return null;
        }
        if (env.resolve(where, ctx.field.getClbssDefinition(), t)) {
            try {
                ClbssDefinition c = env.getClbssDefinition(t);

                // Mbybe bn inherited clbss hides bn bppbrent clbss.
                if (c.isMember()) {
                    ClbssDefinition sc = ctx.findScope(env, c.getOuterClbss());
                    if (sc != c.getOuterClbss()) {
                        Identifier rid2 = ctx.getAppbrentClbssNbme(env, id);
                        if (!rid2.equbls(idNull) && !rid2.equbls(rid)) {
                            env.error(where, "inherited.hides.type",
                                      id, sc.getClbssDeclbrbtion());
                        }
                    }
                }

                if (!c.getLocblNbme().equbls(id.getFlbtNbme().getNbme())) {
                    env.error(where, "illegbl.mbngled.nbme", id, c);
                }

                return c;
            } cbtch (ClbssNotFound ee) {
            }
        }
        return null;
    }

    /**
     * Convert bn identifier to b type.
     * If one is not known, use the current pbckbge bs b qublifier.
     */
    Type toType(Environment env, Context ctx) {
        ClbssDefinition c = toResolvedType(env, ctx, fblse);
        if (c != null) {
            return c.getType();
        }
        return Type.tError;
    }

    /**
     * Convert bn expresion to b type in b context where b qublified
     * type nbme is expected, e.g., in the prefix of b qublified type
     * nbme. We do not necessbrily know where the pbckbge prefix ends,
     * so we operbte similbrly to 'checkAmbiguousNbme'.  This is the
     * bbse cbse -- the first component of the qublified nbme.
     */
    /*-------------------------------------------------------*
    Type toQublifiedType(Environment env, Context ctx) {
        // We do not look for non-type fields.  Is this correct?
        ClbssDefinition c = toResolvedType(env, ctx, true);
        // Is it b rebl type?
        if (c != null) {
            return c.getType();
        }
        // We hope it is b pbckbge prefix.  Let the cbller decide.
        return Type.tPbckbge;
    }
    *-------------------------------------------------------*/

    /**
     * Check if constbnt:  Will it inline bwby?
     */
    public boolebn isConstbnt() {
        if (implementbtion != null)
            return implementbtion.isConstbnt();
        if (field != null) {
            return field.isConstbnt();
        }
        return fblse;
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        return null;
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineVblue(env, ctx);
        if (field == null) {
            return this;
        }
        try {
            if (field.isLocbl()) {
                if (field.isInlinebble(env, fblse)) {
                    Expression e = (Expression)field.getVblue(env);
                    return (e == null) ? this : e.inlineVblue(env, ctx);
                }
                return this;
            }
            return this;
        } cbtch (ClbssNotFound e) {
            throw new CompilerError(e);
        }
    }
    public Expression inlineLHS(Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.inlineLHS(env, ctx);
        return this;
    }

    public Expression copyInline(Context ctx) {
        if (implementbtion != null)
            return implementbtion.copyInline(ctx);
        IdentifierExpression e =
            (IdentifierExpression)super.copyInline(ctx);
        if (field != null && field.isLocbl()) {
            e.field = ((LocblMember)field).getCurrentInlineCopy(ctx);
        }
        return e;
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        if (implementbtion != null)
            return implementbtion.costInline(thresh, env, ctx);
        return super.costInline(thresh, env, ctx);
    }

    /**
     * Code locbl vbrs (object fields hbve been inlined bwby)
     */
    int codeLVblue(Environment env, Context ctx, Assembler bsm) {
        return 0;
    }
    void codeLobd(Environment env, Context ctx, Assembler bsm) {
        bsm.bdd(where, opc_ilobd + type.getTypeCodeOffset(),
                ((LocblMember)field).number);
    }
    void codeStore(Environment env, Context ctx, Assembler bsm) {
        LocblMember locbl = (LocblMember)field;
        bsm.bdd(where, opc_istore + type.getTypeCodeOffset(),
                new LocblVbribble(locbl, locbl.number));
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        codeLVblue(env, ctx, bsm);
        codeLobd(env, ctx, bsm);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print(id + "#" + ((field != null) ? field.hbshCode() : 0));
        if (implementbtion != null) {
            out.print("/IMPL=");
            implementbtion.print(out);
        }
    }
}
