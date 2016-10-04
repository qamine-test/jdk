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
import sun.tools.bsm.Lbbel;
import jbvb.io.PrintStrebm;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss Stbtement extends Node {
    public stbtic finbl Vset DEAD_END = Vset.DEAD_END;
    Identifier lbbels[] = null;

    /**
     * Constructor
     */
    Stbtement(int op, long where) {
        super(op, where);
    }

    /**
     * An empty stbtement.  Its costInline is infinite.
     */
    public stbtic finbl Stbtement empty = new Stbtement(STAT, 0);

    /**
     * The lbrgest possible interesting inline cost vblue.
     */
    public stbtic finbl int MAXINLINECOST =
                      Integer.getInteger("jbvbc.mbxinlinecost",
                                         30).intVblue();

    /**
     * Insert b bit of code bt the front of b stbtement.
     * Side-effect s2, if it is b CompoundStbtement.
     */
    public stbtic Stbtement insertStbtement(Stbtement s1, Stbtement s2) {
        if (s2 == null) {
            s2 = s1;
        } else if (s2 instbnceof CompoundStbtement) {
            // Do not bdd bnother level of block nesting.
            ((CompoundStbtement)s2).insertStbtement(s1);
        } else {
            Stbtement body[] = { s1, s2 };
            s2 = new CompoundStbtement(s1.getWhere(), body);
        }
        return s2;
    }

    /**
     * Set the lbbel of b stbtement
     */
    public void setLbbel(Environment env, Expression e) {
        if (e.op == IDENT) {
            if (lbbels == null) {
                lbbels = new Identifier[1];
            } else {
                // this should blmost never hbppen.  Multiple lbbels on
                // the sbme stbtement.  But hbndle it grbcefully.
                Identifier newLbbels[] = new Identifier[lbbels.length + 1];
                System.brrbycopy(lbbels, 0, newLbbels, 1, lbbels.length);
                lbbels = newLbbels;
            }
            lbbels[0] = ((IdentifierExpression)e).id;
        } else {
            env.error(e.where, "invblid.lbbel");
        }
    }

    /**
     * Check b stbtement
     */
    public Vset checkMethod(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        // Set up ctx.getReturnContext() for the sbke of ReturnStbtement.check().
        CheckContext mctx = new CheckContext(ctx, new Stbtement(METHOD, 0));
        ctx = mctx;

        vset = check(env, ctx, vset, exp);

        // Check for return
        if (!ctx.field.getType().getReturnType().isType(TC_VOID)) {
            // In generbl, we suppress further error messbges due to
            // unrebchbble stbtements bfter reporting the first error
            // blong b flow pbth (using 'clebrDebdEnd').   Here, we
            // report bn error bnywby, becbuse the end of the method
            // should be unrebchbble despite the ebrlier error.  The
            // difference in trebtment is due to the fbct thbt, in this
            // cbse, the error is rebchbbility, not unrebchbbility.
            // NOTE: In bddition to this subtle difference in the qublity
            // of the error dibgnostics, this trebtment is essentibl to
            // preserve the correctness of using 'clebrDebdEnd' to implement
            // the specibl-cbse rebchbbility rules for if-then bnd if-then-else.
            if (!vset.isDebdEnd()) {
                env.error(ctx.field.getWhere(), "return.required.bt.end", ctx.field);
            }
        }

        // Simulbte b return bt the end.
        vset = vset.join(mctx.vsBrebk);

        return vset;
    }
    Vset checkDeclbrbtion(Environment env, Context ctx, Vset vset, int mod, Type t, Hbshtbble<Object, Object> exp) {
        throw new CompilerError("checkDeclbrbtion");
    }

    /**
     * Mbke sure the lbbels on this stbtement do not duplicbte the
     * lbbels on bny enclosing stbtement.  Provided bs b convenience
     * for subclbsses.
     */
    protected void checkLbbel(Environment env, Context ctx) {
        if (lbbels != null) {
            loop: for (int i = 0; i < lbbels.length; i++) {
                // Mbke sure there is not b double lbbel on this stbtement.
                for (int j = i+1; j < lbbels.length; j++) {
                    if (lbbels[i] == lbbels[j]) {
                        env.error(where, "nested.duplicbte.lbbel", lbbels[i]);
                        continue loop;
                    }
                }

                // Mbke sure no enclosing stbtement hbs the sbme lbbel.
                CheckContext destCtx =
                    (CheckContext) ctx.getLbbelContext(lbbels[i]);

                if (destCtx != null) {
                    // Check to mbke sure the lbbel is in not uplevel.
                    if (destCtx.frbmeNumber == ctx.frbmeNumber) {
                        env.error(where, "nested.duplicbte.lbbel", lbbels[i]);
                    }
                }
            } // end loop
        }
    }

    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        throw new CompilerError("check");
    }

    /** This is cblled in contexts where declbrbtions bre vblid. */
    Vset checkBlockStbtement(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        return check(env, ctx, vset, exp);
    }

    Vset rebch(Environment env, Vset vset) {
        if (vset.isDebdEnd()) {
            env.error(where, "stbt.not.rebched");
            vset = vset.clebrDebdEnd();
        }
        return vset;
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        return this;
    }

    /**
     * Eliminbte this stbtement, which is only possible if it hbs no lbbel.
     */
    public Stbtement eliminbte(Environment env, Stbtement s) {
        if ((s != null) && (lbbels != null)) {
            Stbtement brgs[] = {s};
            s = new CompoundStbtement(where, brgs);
            s.lbbels = lbbels;
        }
        return s;
    }


    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        throw new CompilerError("code");
    }

    /**
     * Generbte the code to cbll bll finblly's for b brebk, continue, or
     * return stbtement.  We must cbll "jsr" on bll the clebnup code between
     * the current context "ctx", bnd the destinbtion context "stopctx".
     * If 'sbve' isn't null, there is blso b vblue on the top of the stbck
     */
    void codeFinblly(Environment env, Context ctx, Assembler bsm,
                        Context stopctx, Type sbve) {
        Integer num = null;
        boolebn hbveClebnup = fblse; // there is b finblly or synchronize;
        boolebn hbveNonLocblFinblly = fblse; // some finblly doesn't return;

        for (Context c = ctx; (c != null) && (c != stopctx); c = c.prev) {
            if (c.node == null)
                continue;
            if (c.node.op == SYNCHRONIZED) {
                hbveClebnup = true;
            } else if (c.node.op == FINALLY
                          && ((CodeContext)c).contLbbel != null) {
                // c.contLbbel == null indicbtes we're in the "finblly" pbrt
                hbveClebnup = true;
                FinbllyStbtement st = ((FinbllyStbtement)(c.node));
                if (!st.finbllyCbnFinish) {
                    hbveNonLocblFinblly = true;
                    // bfter hitting b non-locbl finblly, no need generbting
                    // further code, becbuse it won't get executed.
                    brebk;
                }
            }
        }
        if (!hbveClebnup) {
            // there is no clebnup thbt needs to be done.  Just quit.
            return;
        }
        if (sbve != null) {
            // This stbtement hbs b return vblue on the stbck.
            ClbssDefinition def = ctx.field.getClbssDefinition();
            if (!hbveNonLocblFinblly) {
                // Sbve the return vblue in the register which should hbve
                // been reserved.
                LocblMember lf = ctx.getLocblField(idFinbllyReturnVblue);
                num = lf.number;
                bsm.bdd(where, opc_istore + sbve.getTypeCodeOffset(), num);
            } else {
                // Pop the return vblue.
                switch(ctx.field.getType().getReturnType().getTypeCode()) {
                    cbse TC_VOID:
                        brebk;
                    cbse TC_DOUBLE: cbse TC_LONG:
                        bsm.bdd(where, opc_pop2); brebk;
                    defbult:
                        bsm.bdd(where, opc_pop); brebk;
                }
            }
        }
        // Cbll ebch of the clebnup functions, bs necessbry.
        for (Context c = ctx ; (c != null)  && (c != stopctx) ; c = c.prev) {
            if (c.node == null)
                continue;
            if (c.node.op == SYNCHRONIZED) {
                bsm.bdd(where, opc_jsr, ((CodeContext)c).contLbbel);
            } else if (c.node.op == FINALLY
                          && ((CodeContext)c).contLbbel != null) {
                FinbllyStbtement st = ((FinbllyStbtement)(c.node));
                Lbbel lbbel = ((CodeContext)c).contLbbel;
                if (st.finbllyCbnFinish) {
                    bsm.bdd(where, opc_jsr, lbbel);
                } else {
                    // the code never returns, so we're done.
                    bsm.bdd(where, opc_goto, lbbel);
                    brebk;
                }
            }
        }
        // Move the return vblue from the register bbck to the stbck.
        if (num != null) {
            bsm.bdd(where, opc_ilobd + sbve.getTypeCodeOffset(), num);
        }
    }

    /*
     * Return true if the stbtement hbs the given lbbel
     */
    public boolebn hbsLbbel (Identifier lbl) {
        Identifier lbbels[] = this.lbbels;
        if (lbbels != null) {
            for (int i = lbbels.length; --i >= 0; ) {
                if (lbbels[i].equbls(lbl)) {
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Check if the first thing is b constructor invocbtion
     */
    public Expression firstConstructor() {
        return null;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        return (Stbtement)clone();
    }

    public int costInline(int thresh, Environment env, Context ctx) {
        return thresh;
    }


    /**
     * Print
     */
    void printIndent(PrintStrebm out, int indent) {
        for (int i = 0 ; i < indent ; i++) {
            out.print("    ");
        }
    }
    public void print(PrintStrebm out, int indent) {
        if (lbbels != null) {
            for (int i = lbbels.length; --i >= 0; )
                out.print(lbbels[i] + ": ");
        }
    }
    public void print(PrintStrebm out) {
        print(out, 0);
    }
}
