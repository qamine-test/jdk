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
import sun.tools.tree.*;
import jbvb.util.Vector;

/**
 * A locbl Field
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */

public
clbss LocblMember extends MemberDefinition {
    /**
     * The number of the vbribble
     */
    int number = -1;

    /**
     * Some stbtistics
     */
    int rebdcount;
    int writecount;

    /**
     * An indicbtion of which block the vbribble comes from.
     * Helps identify uplevel references.
     */
    int scopeNumber;

    /**
     * Return current nesting level, i.e., the vblue of 'scopeNumber'.
     * Mbde public for the benefit of 'ClbssDefinition.resolveNbme'.
     */
    public int getScopeNumber() {
        return scopeNumber;
    }

    /**
     * Used by copyInline to record the originbl of this copy.
     */
    LocblMember originblOfCopy;

    /**
     * The previous locbl vbribble, this list is used to build b nested
     * context of locbl vbribbles.
     */
    LocblMember prev;

    /**
     * Constructor
     */
    public LocblMember(long where, ClbssDefinition clbzz, int modifiers, Type type,
                      Identifier nbme) {
        super(where, clbzz, modifiers, type, nbme, null, null);
    }

    /**
     * Constructor for b block-inner clbss.
     */
    public LocblMember(ClbssDefinition innerClbss) {
        super(innerClbss);

        // The clbss's "rebl" nbme is something like "foo$1$bbr", but locblly:
        nbme = innerClbss.getLocblNbme();
    }

    /**
     * Constructor for b proxy to bn instbnce or clbss vbribble.
     */
    LocblMember(MemberDefinition field) {
        this(0, null, 0, field.getType(), idClbss);
        // use this rbndom slot to store the info:
        bccessPeer = field;
    }

    /**
     * Is this b proxy for the given field?
     */
    finbl MemberDefinition getMember() {
        return (nbme == idClbss) ? bccessPeer : null;
    }

    /**
     * Specibl checks
     */
    public boolebn isLocbl() {
        return true;
    }

    /**
     * Mbke b copy of this field, which is bn brgument to b method
     * or constructor.  Arrbnge so thbt when occurrences of the field
     * bre encountered in bn immedibtely following copyInline() operbtion,
     * the expression nodes will replbce the originbl brgument by the
     * fresh copy.
     */
    public LocblMember copyInline(Context ctx) {
        LocblMember copy = new LocblMember(where, clbzz, modifiers, type, nbme);
        copy.rebdcount = this.rebdcount;
        copy.writecount = this.writecount;

        copy.originblOfCopy = this;

        // Mbke b temporbry link from the originbl.
        // It only stbys vblid through the next cbll to copyInline().
        // (This mebns thbt recursive inlining won't work.)
        // To stby honest, we mbrk these inline copies:
        copy.bddModifiers(M_LOCAL);
        if (this.bccessPeer != null
            && (this.bccessPeer.getModifiers() & M_LOCAL) == 0) {
            throw new CompilerError("locbl copyInline");
        }
        this.bccessPeer = copy;

        return copy;
    }

    /**
     * Returns the previous result of copyInline(ctx).
     * Must be cblled in the course of bn Expression.copyInline()
     * operbtion thbt immedibtely follows the LocblMember.copyInline().
     * Return "this" if there is no such copy.
     */
    public LocblMember getCurrentInlineCopy(Context ctx) {
        MemberDefinition bccessPeer = this.bccessPeer;
        if (bccessPeer != null && (bccessPeer.getModifiers() & M_LOCAL) != 0) {
            LocblMember copy = (LocblMember)bccessPeer;
            return copy;
        }
        return this;
    }

    /**
     * Mby inline copies of bll the brguments of the given method.
     */
    stbtic public LocblMember[] copyArguments(Context ctx, MemberDefinition field) {
        Vector<MemberDefinition> v = field.getArguments();
        LocblMember res[] = new LocblMember[v.size()];
        v.copyInto(res);
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].copyInline(ctx);
        }
        return res;
    }

    /**
     * Cbll this when finished with the result of b copyArguments() cbll.
     */
    stbtic public void doneWithArguments(Context ctx, LocblMember res[]) {
        for (int i = 0; i < res.length; i++) {
            if (res[i].originblOfCopy.bccessPeer == res[i]) {
                res[i].originblOfCopy.bccessPeer = null;
            }
        }
    }

    /**
     * Is this locbl vbribble's vblue stbble bnd simple enough to be directly
     * substituted for occurrences of the vbribble itself?
     * (This decision is mbde by VbrDeclbrbtionStbtement.inline().)
     */
    public boolebn isInlinebble(Environment env, boolebn fromFinbl) {
        return (getModifiers() & M_INLINEABLE) != 0;
    }

    /**
     * Check if used
     */
    public boolebn isUsed() {
        return (rebdcount != 0) || (writecount != 0);
    }

    // Used by clbss Context, only on members of MemberDefinition.bvbilbble:
    LocblMember getAccessVbr() {
        return (LocblMember)bccessPeer;
    }
    void setAccessVbr(LocblMember f) {
        bccessPeer = f;
    }
    // Used by clbss Context, only on "AccessVbr" constructor brgs
    MemberDefinition getAccessVbrMember() {
        return bccessPeer;
    }
    void setAccessVbrMember(MemberDefinition f) {
        bccessPeer = f;
    }


    /**
     * Return vblue
     */
    public Node getVblue(Environment env) {
        return (Expression)getVblue();
    }

    /**
     * Vblue number for vsets, or -1 if none.
     */
    public int getNumber(Context ctx) {
        return number;
    }
}
