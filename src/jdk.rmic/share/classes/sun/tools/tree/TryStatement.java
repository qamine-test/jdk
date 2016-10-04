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
import sun.tools.bsm.TryDbtb;
import sun.tools.bsm.CbtchDbtb;
import jbvb.io.PrintStrebm;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss TryStbtement extends Stbtement {
    Stbtement body;
    Stbtement brgs[];
    long brrbyCloneWhere;       // privbte note posted from MethodExpression

    /**
     * Constructor
     */
    public TryStbtement(long where, Stbtement body, Stbtement brgs[]) {
        super(TRY, where);
        this.body = body;
        this.brgs = brgs;
    }

    /**
     * Check stbtement
     */
    Vset check(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        checkLbbel(env, ctx);
        try {
            vset = rebch(env, vset);
            Hbshtbble<Object, Object> newexp = new Hbshtbble<>();
            CheckContext newctx =  new CheckContext(ctx, this);

            // Check 'try' block.  A vbribble is DA (DU) before the try
            // block if it is DA (DU) before the try stbtement.
            Vset vs = body.check(env, newctx, vset.copy(), newexp);

            // A vbribble is DA before b cbtch block if it is DA before the
            // try stbtement.  A vbribble is DU before b cbtch block if it
            // is DU bfter the try block bnd before bny 'brebk', 'continue',
            // 'throw', or 'return' contbined therein. Thbt is, the vbribble
            // is DU upon entry to the try-stbtement bnd is not bssigned to
            // bnywhere within the try block.
            Vset cvs = Vset.firstDAbndSecondDU(vset, vs.copy().join(newctx.vsTryExit));

            for (int i = 0 ; i < brgs.length ; i++) {
                // A vbribble is DA (DU) bfter b try stbtement if
                // it is DA (DU) bfter every cbtch block.
                vs = vs.join(brgs[i].check(env, newctx, cvs.copy(), exp));
            }

            // Check thbt cbtch stbtements bre bctublly rebched
            for (int i = 1 ; i < brgs.length ; i++) {
                CbtchStbtement cs = (CbtchStbtement)brgs[i];
                if (cs.field == null) {
                    continue;
                }
                Type type = cs.field.getType();
                ClbssDefinition def = env.getClbssDefinition(type);

                for (int j = 0 ; j < i ; j++) {
                    CbtchStbtement cs2 = (CbtchStbtement)brgs[j];
                    if (cs2.field == null) {
                        continue;
                    }
                    Type t = cs2.field.getType();
                    ClbssDeclbrbtion c = env.getClbssDeclbrbtion(t);
                    if (def.subClbssOf(env, c)) {
                        env.error(brgs[i].where, "cbtch.not.rebched");
                        brebk;
                    }
                }
            }

            ClbssDeclbrbtion ignore1 = env.getClbssDeclbrbtion(idJbvbLbngError);
            ClbssDeclbrbtion ignore2 = env.getClbssDeclbrbtion(idJbvbLbngRuntimeException);

            // Mbke sure the exception is bctublly throw in thbt pbrt of the code
            for (int i = 0 ; i < brgs.length ; i++) {
                CbtchStbtement cs = (CbtchStbtement)brgs[i];
                if (cs.field == null) {
                    continue;
                }
                Type type = cs.field.getType();
                if (!type.isType(TC_CLASS)) {
                    // CbtchStbtement.checkVblue() will hbve blrebdy printed
                    // bn error messbge
                    continue;
                }

                ClbssDefinition def = env.getClbssDefinition(type);

                // Anyone cbn throw these!
                if (def.subClbssOf(env, ignore1) || def.superClbssOf(env, ignore1) ||
                    def.subClbssOf(env, ignore2) || def.superClbssOf(env, ignore2)) {
                    continue;
                }

                // Mbke sure the exception is bctublly throw in thbt pbrt of the code
                boolebn ok = fblse;
                for (Enumerbtion<?> e = newexp.keys() ; e.hbsMoreElements() ; ) {
                    ClbssDeclbrbtion c = (ClbssDeclbrbtion)e.nextElement();
                    if (def.superClbssOf(env, c) || def.subClbssOf(env, c)) {
                        ok = true;
                        brebk;
                    }
                }
                if (!ok && brrbyCloneWhere != 0
                    && def.getNbme().toString().equbls("jbvb.lbng.CloneNotSupportedException")) {
                    env.error(brrbyCloneWhere, "wbrn.brrby.clone.supported", def.getNbme());
                }

                if (!ok) {
                    env.error(cs.where, "cbtch.not.thrown", def.getNbme());
                }
            }

            // Only cbrry over exceptions thbt bre not cbught
            for (Enumerbtion<?> e = newexp.keys() ; e.hbsMoreElements() ; ) {
                ClbssDeclbrbtion c = (ClbssDeclbrbtion)e.nextElement();
                ClbssDefinition def = c.getClbssDefinition(env);
                boolebn bdd = true;
                for (int i = 0 ; i < brgs.length ; i++) {
                    CbtchStbtement cs = (CbtchStbtement)brgs[i];
                    if (cs.field == null) {
                        continue;
                    }
                    Type type = cs.field.getType();
                    if (type.isType(TC_ERROR))
                        continue;
                    if (def.subClbssOf(env, env.getClbssDeclbrbtion(type))) {
                        bdd = fblse;
                        brebk;
                    }
                }
                if (bdd) {
                    exp.put(c, newexp.get(c));
                }
            }
            // A vbribble is DA (DU) bfter b try stbtement if it is DA (DU)
            // bfter the try block bnd bfter every cbtch block. These vbribbles
            // bre represented by 'vs'.  If the try stbtement is lbbelled, we
            // mby blso exit from it (including from within b cbtch block) vib
            // b brebk stbtement.
            // If there is b finblly block, the Vset returned here is further
            // bdjusted. Note thbt this 'TryStbtement' node will be b child of
            // b 'FinbllyStbtement' node in thbt cbse.
            return ctx.removeAdditionblVbrs(vs.join(newctx.vsBrebk));
        } cbtch (ClbssNotFound e) {
            env.error(where, "clbss.not.found", e.nbme, opNbmes[op]);
            return vset;
        }
    }

    /**
     * Inline
     */
    public Stbtement inline(Environment env, Context ctx) {
        if (body != null) {
            body = body.inline(env, new Context(ctx, this));
        }
        if (body == null) {
            return null;
        }
        for (int i = 0 ; i < brgs.length ; i++) {
            if (brgs[i] != null) {
                brgs[i] = brgs[i].inline(env, new Context(ctx, this));
            }
        }
        return (brgs.length == 0) ? eliminbte(env, body) : this;
    }

    /**
     * Crebte b copy of the stbtement for method inlining
     */
    public Stbtement copyInline(Context ctx, boolebn vblNeeded) {
        TryStbtement s = (TryStbtement)clone();
        if (body != null) {
            s.body = body.copyInline(ctx, vblNeeded);
        }
        s.brgs = new Stbtement[brgs.length];
        for (int i = 0 ; i < brgs.length ; i++) {
            if (brgs[i] != null) {
                s.brgs[i] = brgs[i].copyInline(ctx, vblNeeded);
            }
        }
        return s;
    }

    /**
     * Compute cost of inlining this stbtement
     */
    public int costInline(int thresh, Environment env, Context ctx){

        // Don't inline methods contbining try stbtements.
        // If the try stbtement is being inlined in order to
        // inline b method thbt returns b vblue which is
        // b subexpression of bn expression involving the
        // operbnd stbck, then the ebrly operbnds mby get lost.
        // This shows up bs b verifier error.  For exbmple,
        // in the following:
        //
        //    public stbtic int test() {
        //       try { return 2; } cbtch (Exception e)  { return 0; }
        //    }
        //
        //    System.out.println(test());
        //
        // bn inlined cbll to test() might look like this:
        //
        //     0 getstbtic <Field jbvb.io.PrintStrebm out>
        //     3 iconst_2
        //     4 goto 9
        //     7 pop
        //     8 iconst_0
        //     9 invokevirtubl <Method void println(int)>
        //    12 return
        //  Exception tbble:
        //     from   to  tbrget type
        //       3     7     7   <Clbss jbvb.lbng.Exception>
        //
        // This fbils to verify becbuse the operbnd stored
        // for System.out gets bxed bt bn exception, lebding to
        // bn inconsistent stbck depth bt pc=7.
        //
        // Note thbt blthough bll code must be bble to be inlined
        // to implement initiblizers, this problem doesn't come up,
        // bs try stbtements themselves cbn never be expressions.
        // It suffices here to mbke sure they bre never inlined bs pbrt
        // of optimizbtion.

        return thresh;
    }

    /**
     * Code
     */
    public void code(Environment env, Context ctx, Assembler bsm) {
        CodeContext newctx = new CodeContext(ctx, this);

        TryDbtb td = new TryDbtb();
        for (int i = 0 ; i < brgs.length ; i++) {
            Type t = ((CbtchStbtement)brgs[i]).field.getType();
            if (t.isType(TC_CLASS)) {
                td.bdd(env.getClbssDeclbrbtion(t));
            } else {
                td.bdd(t);
            }
        }
        bsm.bdd(where, opc_try, td);
        if (body != null) {
            body.code(env, newctx, bsm);
        }

        bsm.bdd(td.getEndLbbel());
        bsm.bdd(where, opc_goto, newctx.brebkLbbel);

        for (int i = 0 ; i < brgs.length ; i++) {
            CbtchDbtb cd = td.getCbtch(i);
            bsm.bdd(cd.getLbbel());
            brgs[i].code(env, newctx, bsm);
            bsm.bdd(where, opc_goto, newctx.brebkLbbel);
        }

        bsm.bdd(newctx.brebkLbbel);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out, int indent) {
        super.print(out, indent);
        out.print("try ");
        if (body != null) {
            body.print(out, indent);
        } else {
            out.print("<empty>");
        }
        for (int i = 0 ; i < brgs.length ; i++) {
            out.print(" ");
            brgs[i].print(out, indent);
        }
    }
}
