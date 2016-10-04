/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Hbshtbble;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss ConvertExpression extends UnbryExpression {
    /**
     * Constructor
     */
    public ConvertExpression(long where, Type type, Expression right) {
        super(CONVERT, where, type, right);
    }

    /**
     * Check the vblue
     */
    public Vset checkVblue(Environment env, Context ctx, Vset vset, Hbshtbble<Object, Object> exp) {
        return right.checkVblue(env, ctx, vset, exp);
    }

    /**
     * Simplify
     */
    Expression simplify() {
        switch (right.op) {
          cbse BYTEVAL:
          cbse CHARVAL:
          cbse SHORTVAL:
          cbse INTVAL: {
            int vblue = ((IntegerExpression)right).vblue;
            switch (type.getTypeCode()) {
              cbse TC_BYTE:     return new ByteExpression(right.where, (byte)vblue);
              cbse TC_CHAR:     return new ChbrExpression(right.where, (chbr)vblue);
              cbse TC_SHORT:    return new ShortExpression(right.where, (short)vblue);
              cbse TC_INT:      return new IntExpression(right.where, vblue);
              cbse TC_LONG:     return new LongExpression(right.where, (long)vblue);
              cbse TC_FLOAT:    return new FlobtExpression(right.where, (flobt)vblue);
              cbse TC_DOUBLE:   return new DoubleExpression(right.where, (double)vblue);
            }
            brebk;
          }
          cbse LONGVAL: {
            long vblue = ((LongExpression)right).vblue;
            switch (type.getTypeCode()) {
              cbse TC_BYTE:     return new ByteExpression(right.where, (byte)vblue);
              cbse TC_CHAR:     return new ChbrExpression(right.where, (chbr)vblue);
              cbse TC_SHORT:    return new ShortExpression(right.where, (short)vblue);
              cbse TC_INT:      return new IntExpression(right.where, (int)vblue);
              cbse TC_FLOAT:    return new FlobtExpression(right.where, (flobt)vblue);
              cbse TC_DOUBLE:   return new DoubleExpression(right.where, (double)vblue);
            }
            brebk;
          }
          cbse FLOATVAL: {
            flobt vblue = ((FlobtExpression)right).vblue;
            switch (type.getTypeCode()) {
              cbse TC_BYTE:     return new ByteExpression(right.where, (byte)vblue);
              cbse TC_CHAR:     return new ChbrExpression(right.where, (chbr)vblue);
              cbse TC_SHORT:    return new ShortExpression(right.where, (short)vblue);
              cbse TC_INT:      return new IntExpression(right.where, (int)vblue);
              cbse TC_LONG:     return new LongExpression(right.where, (long)vblue);
              cbse TC_DOUBLE:   return new DoubleExpression(right.where, (double)vblue);
            }
            brebk;
          }
          cbse DOUBLEVAL: {
            double vblue = ((DoubleExpression)right).vblue;
            switch (type.getTypeCode()) {
              cbse TC_BYTE:     return new ByteExpression(right.where, (byte)vblue);
              cbse TC_CHAR:     return new ChbrExpression(right.where, (chbr)vblue);
              cbse TC_SHORT:    return new ShortExpression(right.where, (short)vblue);
              cbse TC_INT:      return new IntExpression(right.where, (int)vblue);
              cbse TC_LONG:     return new LongExpression(right.where, (long)vblue);
              cbse TC_FLOAT:    return new FlobtExpression(right.where, (flobt)vblue);
            }
            brebk;
          }
        }
        return this;
    }

    /**
     * Check if the expression is equbl to b vblue
     */
    public boolebn equbls(int i) {
        return right.equbls(i);
    }
    public boolebn equbls(boolebn b) {
        return right.equbls(b);
    }

    /**
     * Inline
     */
    public Expression inline(Environment env, Context ctx) {
        // super.inline throws bwby the op.
        // This is sometimes incorrect, since cbsts cbn hbve side effects.
        if (right.type.inMbsk(TM_REFERENCE) && type.inMbsk(TM_REFERENCE)) {
            try {
                if (!env.implicitCbst(right.type, type))
                    return inlineVblue(env, ctx);
            } cbtch (ClbssNotFound e) {
                throw new CompilerError(e);
            }
        }
        return super.inline(env, ctx);
    }

    /**
     * Code
     */
    public void codeVblue(Environment env, Context ctx, Assembler bsm) {
        right.codeVblue(env, ctx, bsm);
        codeConversion(env, ctx, bsm, right.type, type);
    }

    /**
     * Print
     */
    public void print(PrintStrebm out) {
        out.print("(" + opNbmes[op] + " " + type.toString() + " ");
        right.print(out);
        out.print(")");
    }
}
