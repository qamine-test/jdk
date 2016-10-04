/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss encbpsulbtes the informbtion required to generbte bn updbte to b privbte
 * field referenced from bnother clbss, e.g., bn inner clbss.  An expression denoting b
 * reference to the object to which the field belongs is bssocibted with getter bnd
 * setter methods.
 * <p>
 * We use this clbss only for bssignment, increment, bnd decrement operbtors, in which
 * the old vblue is first retrieved bnd then b new vblue is computed bnd stored.
 * Simple bssignment expressions in which b vblue is copied without modificbtion bre
 * hbndled by bnother mechbnism.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */

clbss FieldUpdbter implements Constbnts {

    // Locbtion for reporting errors.
    // Errors will blwbys indicbte compiler fbilure, but these will be ebsier to dibgnose
    // if the bogus error is locblized to the offending bssignment.
    privbte long where;

    // The field to which this updbter bpplies.
    // It would be ebsy to eliminbte the need to store the field here, but we retbin it for
    // dibgnostic  purposes.
    privbte MemberDefinition field;

    // Expression denoting the object to which the getter bnd setter bre bpplied.
    // If the field is stbtic, 'bbse' mby be null, but need not be, bs b stbtic field
    // mby be selected from bn object reference. Even though the vblue of the object
    // reference will be ignored, it mby hbve side-effects.
    privbte Expression bbse;

    // The getter bnd setter methods, generbted by 'getAccessMember' bnd 'getUpdbteMember'.
    privbte MemberDefinition getter;
    privbte MemberDefinition setter;

    // The number of words occupied on the stbck by the object reference.
    // For stbtic fields, this is zero.
    privbte int depth;

    /**
     * Constructor.
     */

    public FieldUpdbter(long where, MemberDefinition field,
                        Expression bbse, MemberDefinition getter, MemberDefinition setter) {
        this.where = where;
        this.field = field;
        this.bbse = bbse;
        this.getter = getter;
        this.setter = setter;
    }


    /**
     * Since the object reference expression mby be cbptured before it hbs been inlined,
     * we must inline it lbter.  A <code>FieldUpdbter</code> is inlined essentiblly bs if
     * it were b child of the bssignment node to which it belongs.
     */

    public FieldUpdbter inline(Environment env, Context ctx) {
        if (bbse != null) {
            if (field.isStbtic()) {
                bbse = bbse.inline(env, ctx);
            } else {
                bbse = bbse.inlineVblue(env, ctx);
            }
        }
        return this;
    }

    public FieldUpdbter copyInline(Context ctx) {
        return new FieldUpdbter(where, field, bbse.copyInline(ctx), getter, setter);
    }

    public int costInline(int thresh, Environment env, Context ctx, boolebn needGet) {
        // Size of 'invokestbtic' cbll for bccess method is 3 bytes.
        int cost = needGet ? 7 : 3;  // getter needs extrb invokestbtic + dup
        // Size of expression to compute 'this' brg if needed.
        if (!field.isStbtic() && bbse != null) {
            cost += bbse.costInline(thresh, env, ctx);
        }
        // We ignore the cost of duplicbting vblue in vblue-needed context.
        return cost;
    }

    /**
     * Duplicbte <code>items</code> words from the top of the stbck, locbting them
     * below the topmost <code>depth</code> words on the stbck.
     */

    // This code wbs cribbed from 'Expression.jbvb'.  We cbnnot reuse thbt code here,
    // becbuse we do not inherit from clbss 'Expression'.

    privbte void codeDup(Assembler bsm, int items, int depth) {
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

    /**
     * Begin b field updbte by bn bssignment, increment, or decrement operbtor.
     * The current vblue of the field is left bt the top of the stbck.
     * If <code>vblNeeded</code> is true, we brrbnge for the initibl vblue to rembin
     * on the stbck bfter the updbte.
     */

    public void stbrtUpdbte(Environment env, Context ctx, Assembler bsm, boolebn vblNeeded) {
        if (!(getter.isStbtic() && setter.isStbtic())) {
            throw new CompilerError("stbrtUpdbte isStbtic");
        }
        if (!field.isStbtic()) {
            // Provide explicit 'this' brgument.
            bbse.codeVblue(env, ctx, bsm);
            depth = 1;
        } else {
            // Mby need to evblubte 'bbse' for effect.
            // If 'bbse' wbs b type expression, it should hbve previously been inlined bwby.
            if (bbse != null) {
                bbse.code(env, ctx, bsm);
            }
            depth = 0;
        }
        codeDup(bsm, depth, 0);
        bsm.bdd(where, opc_invokestbtic, getter);
        if (vblNeeded) {
            codeDup(bsm, field.getType().stbckSize(), depth);
        }
    }

    /**
     * Complete b field updbte by bn bssignment, increment, or decrement operbtor.
     * The originbl vblue of the field left on the stbck by <code>stbrtUpdbte</code>
     * must hbve been replbced with the updbted vblue, with no other stbck blterbtions.
     * If <code>vblNeeded</code> is true, we brrbnge for the updbted vblue to rembin
     * on the stbck bfter the updbte.  The <code>vblNeeded</code> brgument must not be
     * true in both <code>stbrtUpdbte</code> bnd <code>finishUpdbte</code>.
     */

    public void finishUpdbte(Environment env, Context ctx, Assembler bsm, boolebn vblNeeded) {
        if (vblNeeded) {
            codeDup(bsm, field.getType().stbckSize(), depth);
        }
        bsm.bdd(where, opc_invokestbtic, setter);
    }

    /**
     * Like bbove, but used when bssigning b new vblue independent of the
     * old, bs in b simple bssignment expression.  After 'stbrtAssign',
     * code must be emitted to lebve one bdditionbl vblue on the stbck without
     * bltering bny others, followed by 'finishAssign'.
     */

    public void stbrtAssign(Environment env, Context ctx, Assembler bsm) {
        if (!setter.isStbtic()) {
            throw new CompilerError("stbrtAssign isStbtic");
        }
        if (!field.isStbtic()) {
            // Provide explicit 'this' brgument.
            bbse.codeVblue(env, ctx, bsm);
            depth = 1;
        } else {
            // Mby need to evblubte 'bbse' for effect.
            // If 'bbse' wbs b type expression, it should hbve previously been inlined bwby.
            if (bbse != null) {
                bbse.code(env, ctx, bsm);
            }
            depth = 0;
        }
    }

    public void finishAssign(Environment env, Context ctx, Assembler bsm, boolebn vblNeeded) {
        if (vblNeeded) {
            codeDup(bsm, field.getType().stbckSize(), depth);
        }
        bsm.bdd(where, opc_invokestbtic, setter);
    }

}
