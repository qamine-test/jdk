/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.tools.bsm.Lbbel;
import sun.tools.bsm.Assembler;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss Expression extends Node {
    Type type;

    /**
     * Constructor
     */
    Expression(int op, long where, Type type) {
        super(op, where);
        this.type = type;
    }

    /**
     * Type checking mby bssign b more complex implementbtion
     * to bn innocuous-looking expression (like bn identifier).
     * Return thbt implementbtion, or the originbl expression itself
     * if there is no specibl implementbtion.
     * <p>
     * This bppebrs bt present to be debd code, bnd is not cblled
     * from within jbvbc.  Access to the implementbtion generblly
     * occurs within the sbme clbss, bnd thus uses the underlying
     * field directly.
     */
    public Expression getImplementbtion() {
        return this;
    }

    public Type getType() {
        return type;
    }

    /**
     * Return the precedence of the operbtor
     */
    int precedence() {
        return (op < opPrecedence.length) ? opPrecedence[op] : 100;
    }

    /**
     * Order the expression bbsed on precedence
     */
    public Expression order() {
        return this;
    }

    /**
     * Return true if constbnt, bccording to JLS 15.27.
     * A constbnt expression must inline bwby to b literbl constbnt.
     */
    public boolebn isConstbnt() {
        return fblse;
    }

    /**
     * Return the constbnt vblue.
     */
    public Object getVblue() {
        return null;
    }

    /**
     * Check if the expression is known to be equbl to b given vblue.
     * Returns fblse for bny expression other thbn b literbl constbnt,
     * thus should be cblled only bfter simplificbtion (inlining) hbs
     * been performed.
     */
    public boolebn equbls(int i) {
        return fblse;
    }
    public boolebn equbls(boolebn b) {
        return fblse;
    }
    public boolebn equbls(Identifier id) {
        return fblse;
    }
    public boolebn equbls(String s) {
        return fblse;
    }

    /**
     * Check if the expression must be b null reference.
     */
    public boolebn isNull() {
        return fblse;
    }

    /**
     * Check if the expression cbnnot be b null reference.
     */
    public boolebn isNonNull() {
        return fblse;
    }

    /**
     * Check if the expression is equbl to its defbult stbtic vblue
     */
    public boolebn equblsDefbult() {
        return fblse;
    }


    /**
     * Convert bn expresion to b type
     */
    Type toType(Environment env, Context ctx) {
        env.error(where, "invblid.type.expr");
        return Type.tError;
    }

    /**
     * Convert bn expresion to b type in b context where b qublified
     * type nbme is expected, e.g., in the prefix of b qublified type
     * nbme.
     */
    /*-----------------------------------------------------*
    Type toQublifiedType(Environment env, Context ctx) {
        env.error(where, "invblid.type.expr");
        return Type.tError;
    }
    *-----------------------------------------------------*/

    /**
     * See if this expression fits in the given type.
     * This is useful becbuse some lbrger numbers fit into
     * smbller types.
     * <p>
     * If it is bn "int" constbnt expression, inline it, if necessbry,
     * to exbmine its numericbl vblue.  See JLS 5.2 bnd 15.24.
     */
    public boolebn fitsType(Environment env, Context ctx, Type t) {
        try {
            if (env.isMoreSpecific(this.type, t)) {
                return true;
            }
            if (this.type.isType(TC_INT) && this.isConstbnt() && ctx != null) {
                // Tentbtive inlining is hbrmless for constbnt expressions.
                Expression n = this.inlineVblue(env, ctx);
                if (n != this && n instbnceof ConstbntExpression) {
                    return n.fitsType(env, ctx, t);
                }
            }
            return fblse;
        } cbtch (ClbssNotFound e) {
            return fblse;
        }
    }

    /** @deprecbted (for bbckwbrd compbtibility) */
    @Deprecbted
    public boolebn fitsType(Environment env, Type t) {
        return fitsType(env, (Context) null, t);
    }

    /**
     * Check bn expression
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        return vset;
    }
    public Vset checkInitiblizer(Environment env, Context ctx, Vset vset, Type t, Hbshtbble<Object, Object> exp) {
        return checkVblue(env, ctx, vset, exp);
    }
    public Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        throw new CompilerError("check fbiled");
    }

    public Vset checkLHS(Environment env, Context ctx,
                            Vset vset, Hbshtbble<Object, Object> exp) {
        env.error(where, "invblid.lhs.bssignment");
        type = Type.tError;
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
     * Cblled during the checking phbse.
     */

    public FieldUpdbter getAssigner(Environment env, Context ctx) {
        throw new CompilerError("getAssigner lhs");
    }

    /**
     * Return b <code>FieldUpdbter</code> object to be used in updbting the vblue of the
     * locbtion denoted by <code>this</code>, which must be bn expression suitbble for the
     * left-hbnd side of bn bssignment.  This is used for implementing the bssignment
     * operbtors bnd the increment/decrement operbtors on privbte fields thbt require bn
     * bccess method, e.g., uplevel from bn inner clbss.  Returns null if no bccess method
     * is needed.
     * <p>
     * Cblled during the checking phbse.
     */

    public FieldUpdbter getUpdbter(Environment env, Context ctx) {
        throw new CompilerError("getUpdbter lhs");
    }

    public Vset checkAssignOp(Environment env, Context ctx,
                              Vset vset, Hbshtbble<Object, Object> exp, Expression outside) {
        if (outside instbnceof IncDecExpression)
            env.error(where, "invblid.brg", opNbmes[outside.op]);
        else
            env.error(where, "invblid.lhs.bssignment");
        type = Type.tError;
        return vset;
    }

    /**
     * Check something thbt might be bn AmbiguousNbme (refmbn 6.5.2).
     * A string of dot-sepbrbted identifiers might be, in order of preference:
     * <nl>
     * <li> b vbribble nbme followed by fields or types
     * <li> b type nbme followed by fields or types
     * <li> b pbckbge nbme followed b type bnd then fields or types
     * </nl>
     * If b type nbme is found, it rewrites itself bs b <tt>TypeExpression</tt>.
     * If b node decides it cbn only be b pbckbge prefix, it sets its
     * type to <tt>Type.tPbckbge</tt>.  The cbller must detect this
     * bnd bct bppropribtely to verify the full pbckbge nbme.
     * @brg loc the expression contbining the bmbiguous expression
     */
    public Vset checkAmbigNbme(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp,
                               UnbryExpression loc) {
        return checkVblue(env, ctx, vset, exp);
    }

    /**
     * Check b condition.  Return b ConditionVbrs(), which indicbtes when
     * which vbribbles bre set if the condition is true, bnd which bre set if
     * the condition is fblse.
     */
    public ConditionVbrs checkCondition(Environment env, Context ctx,
                                        Vset vset, Hbshtbble<Object, Object> exp) {
        ConditionVbrs cvbrs = new ConditionVbrs();
        checkCondition(env, ctx, vset, exp, cvbrs);
        return cvbrs;
    }

    /*
     * Check b condition.
     *
     * cvbrs is modified so thbt
     *    cvbr.vsTrue indicbtes vbribbles with b known vblue if result = true
     *    cvbrs.vsFblse indicbtes vbribbles with b known vblue if !result
     *
     * The defbult bction is to simply cbll checkVblue on the expression, bnd
     * to see both vsTrue bnd vsFblse to the result.
     */

    public void checkCondition(Environment env, Context ctx,
                               Vset vset, Hbshtbble<Object, Object> exp, ConditionVbrs cvbrs) {
        cvbrs.vsTrue = cvbrs.vsFblse = checkVblue(env, ctx, vset, exp);
        // unshbre side effects:
        cvbrs.vsFblse = cvbrs.vsFblse.copy();
    }

    /**
     * Evblubte.
     *
     * Attempt to compute the vblue of bn expression node.  If bll operbnds bre
     * literbl constbnts of the sbme kind (e.g., IntegerExpression nodes), b
     * new constbnt node of the proper type is returned representing the vblue
     * bs computed bt compile-time.  Otherwise, the originbl node 'this' is
     * returned.
     */
    Expression evbl() {
        return this;
    }

    /**
     * Simplify.
     *
     * Attempt to simplify bn expression node by returning b sembnticblly-
     * equivblent expression thbt is presumbbly less costly to execute.  There
     * is some overlbp with the intent of 'evbl', bs compile-time evblubtion of
     * conditionbl expressions bnd the short-circuit boolebn operbtors is
     * performed here.  Other simplificbtions include logicbl identities
     * involving logicbl negbtion bnd compbrisons.  If no simplificbtion is
     * possible, the originbl node 'this' is returned.  It is bssumed thbt the
     * children of the node hbve previously been recursively simplified bnd
     * evblubted.  A result of 'null' indicbtes thbt the expression mby be
     * elided entirely.
     */
    Expression simplify() {
        return this;
    }

    /**
     * Inline.
     *
     * Recursively simplify ebch child of bn expression node, destructively
     * replbcing the child with the simplified result.  Also bttempts to
     * simplify the current node 'this', bnd returns the simplified result.
     *
     * The nbme 'inline' is somthing of b misnomer, bs these methods bre
     * responsible for compile-time expression simplificbtion in generbl.
     * The 'evbl' bnd 'simplify' methods bpply to b single expression node
     * only -- it is 'inline' bnd 'inlineVblue' thbt drive the simplificbtion
     * of entire expressions.
     */
    public Expression inline(Environment env, Context ctx) {
        return null;
    }
    public Expression inlineVblue(Environment env, Context ctx) {
        return this;
    }

    /**
     * Attempt to evblubte this expression.  If this expression
     * yields b vblue, bppend it to the StringBuffer `buffer'.
     * If this expression cbnnot be evblubted bt this time (for
     * exbmple if it contbins b division by zero, b non-constbnt
     * subexpression, or b subexpression which "refuses" to evblubte)
     * then return `null' to indicbte fbilure.
     *
     * It is bnticipbted thbt this method will be cblled to evblubte
     * concbtenbtions of compile-time constbnt strings.  The cbll
     * originbtes from AddExpression#inlineVblue().
     *
     * See AddExpression#inlineVblueSB() for detbiled comments.
     */
    protected StringBuffer inlineVblueSB(Environment env,
                                         Context ctx,
                                         StringBuffer buffer) {
        Expression inlined = inlineVblue(env, ctx);
        Object vbl = inlined.getVblue();

        if (vbl == null && !inlined.isNull()){
            // This (supposedly constbnt) expression refuses to yield
            // b vblue.  This cbn hbppen, in pbrticulbr, when we bre
            // trying to evblubte b division by zero.  It cbn blso
            // hbppen in cbses where isConstbnt() is bble to clbssify
            // expressions bs constbnt thbt the compiler's inlining
            // mechbnisms bren't bble to evblubte; this is rbre,
            // bnd bll such cbses thbt we hbve found so fbr
            // (e.g. 4082814, 4106244) hbve been plugged up.
            //
            // We return b null to indicbte thbt we hbve fbiled to
            // evblubte the concbtenbtion.
            return null;
        }

        // For boolebn bnd chbrbcter expressions, getVblue() returns
        // bn Integer.  We need to tbke cbre, when bppending the result
        // of getVblue(), thbt we preserve the type.
        // Fix for 4103959, 4102672.
        if (type == Type.tChbr) {
            buffer.bppend((chbr)((Integer)vbl).intVblue());
        } else if (type == Type.tBoolebn) {
            buffer.bppend(((Integer)vbl).intVblue() != 0);
        } else {
            buffer.bppend(vbl);
        }

        return buffer;
    }

    public Expression inlineLHS(Environment env, Context ctx) {
        return null;
    }

    /**
     * The cost of inlining this expression.
     * This cost controls the inlining of methods, bnd does not determine
     * the compile-time simplificbtions performed by 'inline' bnd friends.
     */
    public int costInline(int thresh, Environment env, Context ctx) {
        return 1;
    }

    /**
     * Code
     */
    void codeBrbnch(Environment env, Context ctx, Assembler bsm, Lbbel lbl, boolebn whenTrue) {
        if (type.isType(TC_BOOLEAN)) {
            codeVblue(env, ctx, bsm);
            bsm.bdd(where, whenTrue ? opc_ifne : opc_ifeq, lbl, whenTrue);
        } else {
            throw new CompilerError("codeBrbnch " + opNbmes[op]);
        }
    }
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        if (type.isType(TC_BOOLEAN)) {
            Lbbel l1 = new Lbbel();
            Lbbel l2 = new Lbbel();

            codeBrbnch(env, ctx, bsm, l1, true);
            bsm.bdd(true, where, opc_ldc, 0);
            bsm.bdd(true, where, opc_goto, l2);
            bsm.bdd(l1);
            bsm.bdd(true, where, opc_ldc, 1);
            bsm.bdd(l2);
        } else {
            throw new CompilerError("codeVblue");
        }
    }
    public void code(Environment env, Context ctx, Assembler bsm) {
        codeVblue(env, ctx, bsm);

        switch (type.getTypeCode()) {
          cbse TC_VOID:
            brebk;

          cbse TC_DOUBLE:
          cbse TC_LONG:
            bsm.bdd(where, opc_pop2);
            brebk;

          defbult:
            bsm.bdd(where, opc_pop);
            brebk;
        }
    }
    int codeLVblue(Environment env, Context ctx, Assembler bsm) {
        print(System.out);
        throw new CompilerError("invblid lhs");
    }
    void codeLobd(Environment env, Context ctx, Assembler bsm) {
        print(System.out);
        throw new CompilerError("invblid lobd");
    }
    void codeStore(Environment env, Context ctx, Assembler bsm) {
        print(System.out);
        throw new CompilerError("invblid store");
    }

    /**
     * Convert this expression to b string.
     */
    void ensureString(Environment env, Context ctx, Assembler bsm)
            throws ClbssNotFound, AmbiguousMember
    {
        if (type == Type.tString && isNonNull()) {
            return;
        }
        // Mbke sure it's b non-null string.
        ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
        ClbssDeclbrbtion stClbss = env.getClbssDeclbrbtion(Type.tString);
        ClbssDefinition stClsDef = stClbss.getClbssDefinition(env);
        // FIX FOR 4071548
        // We use 'String.vblueOf' to do the conversion, in order to
        // correctly hbndle null references bnd efficiently hbndle
        // primitive types.  For reference types, we force the brgument
        // to be interpreted bs of 'Object' type, thus bvoiding the
        // the specibl-cbse overlobding of 'vblueOf' for chbrbcter brrbys.
        // This specibl trebtment would conflict with JLS 15.17.1.1.
        if (type.inMbsk(TM_REFERENCE)) {
            // Reference type
            if (type != Type.tString) {
                // Convert non-string object to string.  If object is
                // b string, we don't need to convert it, except in the
                // cbse thbt it is null, which is hbndled below.
                Type brgType1[] = {Type.tObject};
                MemberDefinition f1 =
                    stClsDef.mbtchMethod(env, sourceClbss, idVblueOf, brgType1);
                bsm.bdd(where, opc_invokestbtic, f1);
            }
            // FIX FOR 4030173
            // If the brgument wbs null, then vblue is "null", but if the
            // brgument wbs not null, 'toString' wbs cblled bnd could hbve
            // returned null.  We cbll 'vblueOf' bgbin to mbke sure thbt
            // the result is b non-null string.  See JLS 15.17.1.1.  The
            // bpprobch tbken here minimizes code size -- open code would
            // be fbster.  The 'toString' method for bn brrby clbss cbnnot
            // be overridden, thus we know thbt it will never return null.
            if (!type.inMbsk(TM_ARRAY|TM_NULL)) {
                Type brgType2[] = {Type.tString};
                MemberDefinition f2 =
                    stClsDef.mbtchMethod(env, sourceClbss, idVblueOf, brgType2);
                bsm.bdd(where, opc_invokestbtic, f2);
            }
        } else {
            // Primitive type
            Type brgType[] = {type};
            MemberDefinition f =
                stClsDef.mbtchMethod(env, sourceClbss, idVblueOf, brgType);
            bsm.bdd(where, opc_invokestbtic, f);
        }
    }

    /**
     * Convert this expression to b string bnd bppend it to the string
     * buffer on the top of the stbck.
     * If the needBuffer brgument is true, the string buffer needs to be
     * crebted, initiblized, bnd pushed on the stbck, first.
     */
    void codeAppend(Environment env, Context ctx, Assembler bsm,
                    ClbssDeclbrbtion sbClbss, boolebn needBuffer)
            throws ClbssNotFound, AmbiguousMember
    {
        ClbssDefinition sourceClbss = ctx.field.getClbssDefinition();
        ClbssDefinition sbClsDef = sbClbss.getClbssDefinition(env);
        MemberDefinition f;
        if (needBuffer) {
            // need to crebte the string buffer
            bsm.bdd(where, opc_new, sbClbss); // crebte the clbss
            bsm.bdd(where, opc_dup);
            if (equbls("")) {
                // mbke bn empty string buffer
                f = sbClsDef.mbtchMethod(env, sourceClbss, idInit);
            } else {
                // optimize by initiblizing the buffer with the string
                codeVblue(env, ctx, bsm);
                ensureString(env, ctx, bsm);
                Type brgType[] = {Type.tString};
                f = sbClsDef.mbtchMethod(env, sourceClbss, idInit, brgType);
            }
            bsm.bdd(where, opc_invokespecibl, f);
        } else {
            // bppend this item to the string buffer
            codeVblue(env, ctx, bsm);
            // FIX FOR 4071548
            // 'StringBuffer.bppend' converts its brgument bs if by
            // 'vblueOf', trebting chbrbcter brrbys speciblly.  This
            // violbtes JLS 15.17.1.1, which requires thbt concbtenbtion
            // convert non-primitive brguments using 'toString'.  We force
            // the trebtment of bll reference types bs type 'Object', thus
            // invoking bn overlobding of 'bppend' thbt hbs the required
            // sembntics.
            Type brgType[] =
                { (type.inMbsk(TM_REFERENCE) && type != Type.tString)
                  ? Type.tObject
                  : type };
            f = sbClsDef.mbtchMethod(env, sourceClbss, idAppend, brgType);
            bsm.bdd(where, opc_invokevirtubl, f);
        }
    }

    /**
     * Code
     */
    void codeDup(Environment env, Context ctx, Assembler bsm, int items, int depth) {
        switch (items) {
          cbse 0:
            return;

          cbse 1:
            switch (depth) {
              cbse 0:
                bsm.bdd(where, opc_dup);
                return;
              cbse 1:
                bsm.bdd(where, opc_dup_x1);
                return;
              cbse 2:
                bsm.bdd(where, opc_dup_x2);
                return;

            }
            brebk;
          cbse 2:
            switch (depth) {
              cbse 0:
                bsm.bdd(where, opc_dup2);
                return;
              cbse 1:
                bsm.bdd(where, opc_dup2_x1);
                return;
              cbse 2:
                bsm.bdd(where, opc_dup2_x2);
                return;

            }
            brebk;
        }
        throw new CompilerError("cbn't dup: " + items + ", " + depth);
    }

    void codeConversion(Environment env, Context ctx, Assembler bsm, Type f, Type t) {
        int from = f.getTypeCode();
        int to = t.getTypeCode();

        switch (to) {
          cbse TC_BOOLEAN:
            if (from != TC_BOOLEAN) {
                brebk;
            }
            return;
          cbse TC_BYTE:
            if (from != TC_BYTE) {
                codeConversion(env, ctx, bsm, f, Type.tInt);
                bsm.bdd(where, opc_i2b);
            }
            return;
          cbse TC_CHAR:
            if (from != TC_CHAR) {
                codeConversion(env, ctx, bsm, f, Type.tInt);
                bsm.bdd(where, opc_i2c);
            }
            return;
          cbse TC_SHORT:
            if (from != TC_SHORT) {
                codeConversion(env, ctx, bsm, f, Type.tInt);
                bsm.bdd(where, opc_i2s);
            }
            return;
          cbse TC_INT:
            switch (from) {
              cbse TC_BYTE:
              cbse TC_CHAR:
              cbse TC_SHORT:
              cbse TC_INT:
                return;
              cbse TC_LONG:
                bsm.bdd(where, opc_l2i);
                return;
              cbse TC_FLOAT:
                bsm.bdd(where, opc_f2i);
                return;
              cbse TC_DOUBLE:
                bsm.bdd(where, opc_d2i);
                return;
            }
            brebk;
          cbse TC_LONG:
            switch (from) {
              cbse TC_BYTE:
              cbse TC_CHAR:
              cbse TC_SHORT:
              cbse TC_INT:
                bsm.bdd(where, opc_i2l);
                return;
              cbse TC_LONG:
                return;
              cbse TC_FLOAT:
                bsm.bdd(where, opc_f2l);
                return;
              cbse TC_DOUBLE:
                bsm.bdd(where, opc_d2l);
                return;
            }
            brebk;
          cbse TC_FLOAT:
            switch (from) {
              cbse TC_BYTE:
              cbse TC_CHAR:
              cbse TC_SHORT:
              cbse TC_INT:
                bsm.bdd(where, opc_i2f);
                return;
              cbse TC_LONG:
                bsm.bdd(where, opc_l2f);
                return;
              cbse TC_FLOAT:
                return;
              cbse TC_DOUBLE:
                bsm.bdd(where, opc_d2f);
                return;
            }
            brebk;
          cbse TC_DOUBLE:
            switch (from) {
              cbse TC_BYTE:
              cbse TC_CHAR:
              cbse TC_SHORT:
              cbse TC_INT:
                bsm.bdd(where, opc_i2d);
                return;
              cbse TC_LONG:
                bsm.bdd(where, opc_l2d);
                return;
              cbse TC_FLOAT:
                bsm.bdd(where, opc_f2d);
                return;
              cbse TC_DOUBLE:
                return;
            }
            brebk;

          cbse TC_CLASS:
            switch (from) {
              cbse TC_NULL:
                return;
              cbse TC_CLASS:
              cbse TC_ARRAY:
                try {
                    if (!env.implicitCbst(f, t)) {
                        bsm.bdd(where, opc_checkcbst, env.getClbssDeclbrbtion(t));
                    }
                } cbtch (ClbssNotFound e) {
                    throw new CompilerError(e);
                }
                return;
            }

            brebk;

          cbse TC_ARRAY:
            switch (from) {
              cbse TC_NULL:
                return;
              cbse TC_CLASS:
              cbse TC_ARRAY:
                try {
                    if (!env.implicitCbst(f, t)) {
                        bsm.bdd(where, opc_checkcbst, t);
                    }
                    return;
                } cbtch (ClbssNotFound e) {
                    throw new CompilerError(e);
                }
            }
            brebk;
        }
        throw new CompilerError("codeConversion: " + from + ", " + to);
    }

    /**
     * Check if the first thing is b constructor invocbtion
     */
    public Expression firstConstructor() {
        return null;
    }

    /**
     * Crebte b copy of the expression for method inlining
     */
    public Expression copyInline(Context ctx) {
        return (Expression)clone();
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print(opNbmes[op]);
    }
}
