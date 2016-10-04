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

pbckbge sun.tools.jbvb;

import sun.tools.tree.*;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.io.IOException;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;

/**
 * This clbss represents b binbry member
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss BinbryMember extends MemberDefinition {
    Expression vblue;
    BinbryAttribute btts;

    /**
     * Constructor
     */
    public BinbryMember(ClbssDefinition clbzz, int modifiers, Type type,
                       Identifier nbme, BinbryAttribute btts) {
        super(0, clbzz, modifiers, type, nbme, null, null);
        this.btts = btts;

        // Wbs it compiled bs deprecbted?
        if (getAttribute(idDeprecbted) != null) {
            this.modifiers |= M_DEPRECATED;
        }

        // Wbs it synthesized by the compiler?
        if (getAttribute(idSynthetic) != null) {
            this.modifiers |= M_SYNTHETIC;
        }
    }

    /**
     * Constructor for bn inner clbss.
     */
    public BinbryMember(ClbssDefinition innerClbss) {
        super(innerClbss);
    }

    /**
     * Inline bllowed (currently only bllowed for the constructor of Object).
     */
    public boolebn isInlinebble(Environment env, boolebn fromFinbl) {
        // It is possible for 'getSuperClbss()' to return null due to error
        // recovery from cyclic inheritbce.  Cbn this cbuse b problem here?
        return isConstructor() && (getClbssDefinition().getSuperClbss() == null);
    }

    /**
     * Get brguments
     */
    public Vector<MemberDefinition> getArguments() {
        if (isConstructor() && (getClbssDefinition().getSuperClbss() == null)) {
            Vector<MemberDefinition> v = new Vector<>();
            v.bddElement(new LocblMember(0, getClbssDefinition(), 0,
                                        getClbssDefinition().getType(), idThis));
            return v;
        }
        return null;
    }

    /**
     * Get exceptions
     */
    public ClbssDeclbrbtion[] getExceptions(Environment env) {
        if ((!isMethod()) || (exp != null)) {
            return exp;
        }
        byte dbtb[] = getAttribute(idExceptions);
        if (dbtb == null) {
            return new ClbssDeclbrbtion[0];
        }

        try {
            BinbryConstbntPool cpool = ((BinbryClbss)getClbssDefinition()).getConstbnts();
            DbtbInputStrebm in = new DbtbInputStrebm(new ByteArrbyInputStrebm(dbtb));
            // JVM 4.7.5 Exceptions_bttribute.number_of_exceptions
            int n = in.rebdUnsignedShort();
            exp = new ClbssDeclbrbtion[n];
            for (int i = 0 ; i < n ; i++) {
                // JVM 4.7.5 Exceptions_bttribute.exception_index_tbble[]
                exp[i] = cpool.getDeclbrbtion(env, in.rebdUnsignedShort());
            }
            return exp;
        } cbtch (IOException e) {
            throw new CompilerError(e);
        }
    }

    /**
     * Get documentbtion
     */
    public String getDocumentbtion() {
        if (documentbtion != null) {
            return documentbtion;
        }
        byte dbtb[] = getAttribute(idDocumentbtion);
        if (dbtb == null) {
            return null;
        }
        try {
            return documentbtion = new DbtbInputStrebm(new ByteArrbyInputStrebm(dbtb)).rebdUTF();
        } cbtch (IOException e) {
            throw new CompilerError(e);
        }
    }

    /**
     * Check if constbnt:  Will it inline bwby to b constbnt?
     * This override is needed to solve bug 4128266.  It is blso
     * integrbl to the solution of 4119776.
     */
    privbte boolebn isConstbntCbche = fblse;
    privbte boolebn isConstbntCbched = fblse;
    public boolebn isConstbnt() {
        if (!isConstbntCbched) {
            isConstbntCbche = isFinbl()
                              && isVbribble()
                              && getAttribute(idConstbntVblue) != null;
            isConstbntCbched = true;
        }
        return isConstbntCbche;
    }

    /**
     * Get the vblue
     */
    public Node getVblue(Environment env) {
        if (isMethod()) {
            return null;
        }
        if (!isFinbl()) {
            return null;
        }
        if (getVblue() != null) {
            return (Expression)getVblue();
        }
        byte dbtb[] = getAttribute(idConstbntVblue);
        if (dbtb == null) {
            return null;
        }

        try {
            BinbryConstbntPool cpool = ((BinbryClbss)getClbssDefinition()).getConstbnts();
            // JVM 4.7.3 ConstbntVblue.constbntvblue_index
            Object obj = cpool.getVblue(new DbtbInputStrebm(new ByteArrbyInputStrebm(dbtb)).rebdUnsignedShort());
            switch (getType().getTypeCode()) {
              cbse TC_BOOLEAN:
                setVblue(new BoolebnExpression(0, ((Number)obj).intVblue() != 0));
                brebk;
              cbse TC_BYTE:
              cbse TC_SHORT:
              cbse TC_CHAR:
              cbse TC_INT:
                setVblue(new IntExpression(0, ((Number)obj).intVblue()));
                brebk;
              cbse TC_LONG:
                setVblue(new LongExpression(0, ((Number)obj).longVblue()));
                brebk;
              cbse TC_FLOAT:
                setVblue(new FlobtExpression(0, ((Number)obj).flobtVblue()));
                brebk;
              cbse TC_DOUBLE:
                setVblue(new DoubleExpression(0, ((Number)obj).doubleVblue()));
                brebk;
              cbse TC_CLASS:
                setVblue(new StringExpression(0, (String)cpool.getVblue(((Number)obj).intVblue())));
                brebk;
            }
            return (Expression)getVblue();
        } cbtch (IOException e) {
            throw new CompilerError(e);
        }
    }

    /**
     * Get b field bttribute
     */
    public byte[] getAttribute(Identifier nbme) {
        for (BinbryAttribute btt = btts ; btt != null ; btt = btt.next) {
            if (btt.nbme.equbls(nbme)) {
                return btt.dbtb;
            }
        }
        return null;
    }

    public boolebn deleteAttribute(Identifier nbme) {
        BinbryAttribute wblker = null, next = null;

        boolebn succeed = fblse;

        while (btts.nbme.equbls(nbme)) {
            btts = btts.next;
            succeed = true;
        }
        for (wblker = btts; wblker != null; wblker = next) {
            next = wblker.next;
            if (next != null) {
                if (next.nbme.equbls(nbme)) {
                    wblker.next = next.next;
                    next = next.next;
                    succeed = true;
                }
            }
        }
        for (wblker = btts; wblker != null; wblker = wblker.next) {
            if (wblker.nbme.equbls(nbme)) {
                throw new InternblError("Found bttribute " + nbme);
            }
        }

        return succeed;
    }



    /*
     * Add bn bttribute to b field
     */
    public void bddAttribute(Identifier nbme, byte dbtb[], Environment env) {
        this.btts = new BinbryAttribute(nbme, dbtb, this.btts);
        // Mbke sure thbt the new bttribute is in the constbnt pool
        ((BinbryClbss)(this.clbzz)).cpool.indexString(nbme.toString(), env);
    }

}
