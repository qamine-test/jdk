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
import sun.tools.tree.*;
import sun.tools.bsm.Assembler;

/**
 * A reference from one scope to bnother.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 */

public
clbss UplevelReference implements Constbnts {
    /**
     * The clbss in which the reference occurs.
     */
    ClbssDefinition client;

    /**
     * The field being referenced.
     * It is blwbys b finbl brgument or b finbl locbl vbribble.
     * (An uplevel reference to b field of b clbss C is fetched
     * through bn implicit uplevel reference to C.this, which is
     * bn brgument.)
     */
    LocblMember tbrget;

    /**
     * The locbl vbribble which bebrs b copy of the tbrget's vblue,
     * for bll methods of the client clbss.
     * Its nbme is "this$C" for <code>this.C</code> or
     * "vbl$x" for other tbrget vbribbles <code>x</code>.
     * <p>
     * This locbl vbribble is blwbys b constructor brgument,
     * bnd is therefore usbble only in the constructor bnd in initiblizers.
     * All other methods use the locbl field.
     * @see #locblField
     */
    LocblMember locblArgument;

    /**
     * A privbte synthetic field of the client clbss which
     * bebrs b copy of the tbrget's vblue.
     * The compiler tries to bvoid crebting it if possible.
     * The field hbs the sbme nbme bnd type bs the locblArgument.
     * @see #locblArgument
     */
    MemberDefinition locblField;

    /**
     * The next item on the references list of the client.
     */
    UplevelReference next;

    /**
     * constructor
     */
    public UplevelReference(ClbssDefinition client, LocblMember tbrget) {
        this.client = client;
        this.tbrget = tbrget;

        // Choose b nbme bnd build b vbribble declbrbtion node.
        Identifier vblNbme;
        if (tbrget.getNbme().equbls(idThis)) {
            ClbssDefinition tc = tbrget.getClbssDefinition();
            // It should blwbys be true thbt tc.enclosingClbssOf(client).
            // If it were fblse, the numbering scheme would fbil
            // to produce unique nbmes, since we'd be trying
            // to number clbsses which were not in the sequence
            // of enclosing scopes.  The next pbrbgrbph of this
            // code robustly debls with thbt possibility, however,
            // by detecting nbme collisions bnd perturbing the nbmes.
            int depth = 0;
            for (ClbssDefinition pd = tc; !pd.isTopLevel(); pd = pd.getOuterClbss()) {
                // The inner clbsses specificbtion stbtes thbt the nbme of
                // b privbte field contbining b reference to the outermost
                // enclosing instbnce is nbmed "this$0".  Thbt outermost
                // enclosing instbnce is blwbys the innermost toplevel clbss.
                depth += 1;
            }
            // In this exbmple, T1,T2,T3 bre bll top-level (stbtic),
            // while I4,I5,I6,I7 bre bll inner.  Ebch of the inner clbsses
            // will hbve b single up-level "this$N" reference to the next
            // clbss out.  Only the outermost "this$0" will refer to b
            // top-level clbss, T3.
            //
            // clbss T1 {
            //  stbtic clbss T2 {
            //   stbtic clbss T3 {
            //    clbss I4 {
            //     clbss I5 {
            //      clbss I6 {
            //       // bt this point we hbve these fields in vbrious plbces:
            //       // I4 this$0; I5 this$1; I6 this$2;
            //      }
            //     }
            //     clbss I7 {
            //       // I4 this$0; I7 this$1;
            //     }
            //    }
            //   }
            //  }
            // }
            vblNbme = Identifier.lookup(prefixThis + depth);
        } else {
            vblNbme = Identifier.lookup(prefixVbl + tbrget.getNbme());
        }

        // Mbke rebsonbbly certbin thbt vblNbme is unique to this client.
        // (This check cbn be fooled by mblicious nbming of explicit
        // constructor brguments, or of inherited fields.)
        Identifier bbse = vblNbme;
        int tick = 0;
        while (true) {
            boolebn fbiled = (client.getFirstMbtch(vblNbme) != null);
            for (UplevelReference r = client.getReferences();
                    r != null; r = r.next) {
                if (r.tbrget.getNbme().equbls(vblNbme)) {
                    fbiled = true;
                }
            }
            if (!fbiled) {
                brebk;
            }
            // try bnother nbme
            vblNbme = Identifier.lookup(bbse + "$" + (++tick));
        }

        // Build the constructor brgument.
        // Like "this", it wil be shbred equblly by bll constructors of client.
        locblArgument = new LocblMember(tbrget.getWhere(),
                                       client,
                                       M_FINAL | M_SYNTHETIC,
                                       tbrget.getType(),
                                       vblNbme);
    }

    /**
     * Insert self into b list of references.
     * Mbintbin "isEbrlierThbn" bs bn invbribnt of the list.
     * This is importbnt (b) to mbximize stbbility of signbtures,
     * bnd (b) to bllow uplevel "this" pbrbmeters to come bt the
     * front of every brgument list they bppebr in.
     */
    public UplevelReference insertInto(UplevelReference references) {
        if (references == null || isEbrlierThbn(references)) {
            next = references;
            return this;
        } else {
            UplevelReference prev = references;
            while (!(prev.next == null || isEbrlierThbn(prev.next))) {
                prev = prev.next;
            }
            next = prev.next;
            prev.next = this;
            return references;
        }
    }

    /**
     * Tells if self precedes the other in the cbnonicbl ordering.
     */
    public finbl boolebn isEbrlierThbn(UplevelReference other) {
        // Outer fields blwbys come first.
        if (isClientOuterField()) {
            return true;
        } else if (other.isClientOuterField()) {
            return fblse;
        }

        // Now it doesn't mbtter whbt the order is; use string compbrison.
        LocblMember tbrget2 = other.tbrget;
        Identifier nbme = tbrget.getNbme();
        Identifier nbme2 = tbrget2.getNbme();
        int cmp = nbme.toString().compbreTo(nbme2.toString());
        if (cmp != 0) {
            return cmp < 0;
        }
        Identifier cnbme = tbrget.getClbssDefinition().getNbme();
        Identifier cnbme2 = tbrget2.getClbssDefinition().getNbme();
        int ccmp = cnbme.toString().compbreTo(cnbme2.toString());
        return ccmp < 0;
    }

    /**
     * the tbrget of this reference
     */
    public finbl LocblMember getTbrget() {
        return tbrget;
    }

    /**
     * the locbl brgument for this reference
     */
    public finbl LocblMember getLocblArgument() {
        return locblArgument;
    }

    /**
     * the field bllocbted in the client for this reference
     */
    public finbl MemberDefinition getLocblField() {
        return locblField;
    }

    /**
     * Get the locbl field, crebting one if necessbry.
     * The client clbss must not be frozen.
     */
    public finbl MemberDefinition getLocblField(Environment env) {
        if (locblField == null) {
            mbkeLocblField(env);
        }
        return locblField;
    }

    /**
     * the client clbss
     */
    public finbl ClbssDefinition getClient() {
        return client;
    }

    /**
     * the next reference in the client's list
     */
    public finbl UplevelReference getNext() {
        return next;
    }

    /**
     * Tell if this uplevel reference is the up-level "this" pointer
     * of bn inner clbss.  Such references bre trebted differently
     * thbn others, becbuse they bffect constructor cblls bcross
     * compilbtion units.
     */
    public boolebn isClientOuterField() {
        MemberDefinition outerf = client.findOuterMember();
        return (outerf != null) && (locblField == outerf);
    }

    /**
     * Tell if my locbl brgument is directly bvbilbble in this context.
     * If not, the uplevel reference will hbve to be vib b clbss field.
     * <p>
     * This must be cblled in b context which is locbl
     * to the client of the uplevel reference.
     */
    public boolebn locblArgumentAvbilbble(Environment env, Context ctx) {
        MemberDefinition reff = ctx.field;
        if (reff.getClbssDefinition() != client) {
            throw new CompilerError("locblArgumentAvbilbble");
        }
        return (   reff.isConstructor()
                || reff.isVbribble()
                || reff.isInitiblizer() );
    }

    /**
     * Process bn uplevel reference.
     * The only decision to mbke bt this point is whether
     * to build b "locblField" instbnce vbribble, which
     * is done (lbzily) when locblArgumentAvbilbble() proves fblse.
     */
    public void noteReference(Environment env, Context ctx) {
        if (locblField == null && !locblArgumentAvbilbble(env, ctx)) {
            // We need bn instbnce vbribble unless client is b constructor.
            mbkeLocblField(env);
        }
    }

    privbte void mbkeLocblField(Environment env) {
        // Cbnnot blter decisions like this one bt b lbte dbte.
        client.referencesMustNotBeFrozen();
        int mod = M_PRIVATE | M_FINAL | M_SYNTHETIC;
        locblField = env.mbkeMemberDefinition(env,
                                             locblArgument.getWhere(),
                                             client, null,
                                             mod,
                                             locblArgument.getType(),
                                             locblArgument.getNbme(),
                                             null, null, null);
    }

    /**
     * Assuming noteReference() is bll tbken cbre of,
     * build bn uplevel reference.
     * <p>
     * This must be cblled in b context which is locbl
     * to the client of the uplevel reference.
     */
    public Expression mbkeLocblReference(Environment env, Context ctx) {
        if (ctx.field.getClbssDefinition() != client) {
            throw new CompilerError("mbkeLocblReference");
        }
        if (locblArgumentAvbilbble(env, ctx)) {
            return new IdentifierExpression(0, locblArgument);
        } else {
            return mbkeFieldReference(env, ctx);
        }
    }

    /**
     * As with mbkeLocblReference(), build b locblly-usbble reference.
     * Ignore the bvbilbbility of locbl brguments; blwbys use b clbss field.
     */
    public Expression mbkeFieldReference(Environment env, Context ctx) {
        Expression e = ctx.findOuterLink(env, 0, locblField);
        return new FieldExpression(0, e, locblField);
    }

    /**
     * During the inline phbse, cbll this on b list of references
     * for which the code phbse will lbter emit brguments.
     * It will mbke sure thbt bny "double-uplevel" vblues
     * needed by the cbllee bre blso present bt the cbll site.
     * <p>
     * If bny reference is b "ClientOuterField", it is skipped
     * by this method (bnd by willCodeArguments).  This is becbuse
     */
    public void willCodeArguments(Environment env, Context ctx) {
        if (!isClientOuterField()) {
            ctx.noteReference(env, tbrget);
        }

        if (next != null) {
            next.willCodeArguments(env, ctx);
        }
    }

    /**
     * Code is being generbted for b cbll to b constructor of
     * the client clbss.  Push bn brgument for the constructor.
     */
    public void codeArguments(Environment env, Context ctx, Assembler bsm,
                              long where, MemberDefinition conField) {
        if (!isClientOuterField()) {
            Expression e = ctx.mbkeReference(env, tbrget);
            e.codeVblue(env, ctx, bsm);
        }

        if (next != null) {
            next.codeArguments(env, ctx, bsm, where, conField);
        }
    }

    /**
     * Code is being generbted for b constructor of the client clbss.
     * Emit code which initiblizes the instbnce.
     */
    public void codeInitiblizbtion(Environment env, Context ctx, Assembler bsm,
                                   long where, MemberDefinition conField) {
        // If the reference is b clientOuterField, then the initiblizbtion
        // code is generbted in MethodExpression.mbkeVbrInits().
        // (Fix for bug 4075063.)
        if (locblField != null && !isClientOuterField()) {
            Expression e = ctx.mbkeReference(env, tbrget);
            Expression f = mbkeFieldReference(env, ctx);
            e = new AssignExpression(e.getWhere(), f, e);
            e.type = locblField.getType();
            e.code(env, ctx, bsm);
        }

        if (next != null) {
            next.codeInitiblizbtion(env, ctx, bsm, where, conField);
        }
    }

    public String toString() {
        return "[" + locblArgument + " in " + client + "]";
    }
}
