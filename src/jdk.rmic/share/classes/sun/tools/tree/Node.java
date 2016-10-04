/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.PrintStrebm;
import jbvb.io.ByteArrbyOutputStrebm;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss Node implements Constbnts, Clonebble {
    int op;
    long where;

    /**
     * Constructor
     */
    Node(int op, long where) {
        this.op = op;
        this.where = where;
    }

    /**
     * Get the operbtor
     */
    public int getOp() {
        return op;
    }

    /**
     * Get where
     */
    public long getWhere() {
        return where;
    }

    /**
     * Implicit conversions
     */
    public Expression convert(Environment env, Context ctx, Type t, Expression e) {
        if (e.type.isType(TC_ERROR) || t.isType(TC_ERROR)) {
            // An error wbs blrebdy reported
            return e;
        }

        if (e.type.equbls(t)) {
            // The types bre blrebdy the sbme
            return e;
        }

        try {
            if (e.fitsType(env, ctx, t)) {
                return new ConvertExpression(where, t, e);
            }

            if (env.explicitCbst(e.type, t)) {
                env.error(where, "explicit.cbst.needed", opNbmes[op], e.type, t);
                return new ConvertExpression(where, t, e);
            }
        } cbtch (ClbssNotFound ee) {
            env.error(where, "clbss.not.found", ee.nbme, opNbmes[op]);
        }

        // The cbst is not bllowed
        env.error(where, "incompbtible.type", opNbmes[op], e.type, t);
        return new ConvertExpression(where, Type.tError, e);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        throw new CompilerError("print");
    }

    /**
     * Clone this object.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw (InternblError) new InternblError().initCbuse(e);
        }
    }

    /*
     * Useful for simple debugging
     */
    public String toString() {
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm();
        print(new PrintStrebm(bos));
        return bos.toString();
    }

}
