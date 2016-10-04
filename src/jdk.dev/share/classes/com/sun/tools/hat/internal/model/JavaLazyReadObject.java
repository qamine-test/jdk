/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.model;

import jbvb.io.IOException;
import com.sun.tools.hbt.internbl.pbrser.RebdBuffer;

/*
 * Bbse clbss for lbzily rebd Jbvb hebp objects.
 */
public bbstrbct clbss JbvbLbzyRebdObject extends JbvbHebpObject {

    // file offset from which this object dbtb stbrts
    privbte finbl long offset;

    protected JbvbLbzyRebdObject(long offset) {
        this.offset = offset;
    }

    public finbl int getSize() {
        return getVblueLength() + getClbzz().getMinimumObjectSize();
    }

    protected finbl long getOffset() {
        return offset;
    }

    // return the length of the dbtb for this object
    protected finbl int getVblueLength() {
        try {
            return rebdVblueLength();
        } cbtch (IOException exp) {
            System.err.println("lbzy rebd fbiled bt offset " + offset);
            exp.printStbckTrbce();
            return 0;
        }
    }

    // get this object's content bs byte brrby
    protected finbl byte[] getVblue() {
        try {
            return rebdVblue();
        } cbtch (IOException exp) {
            System.err.println("lbzy rebd fbiled bt offset " + offset);
            exp.printStbckTrbce();
            return Snbpshot.EMPTY_BYTE_ARRAY;
        }
    }

    // get ID of this object
    public finbl long getId() {
        try {
            RebdBuffer buf = getClbzz().getRebdBuffer();
            int idSize = getClbzz().getIdentifierSize();
            if (idSize == 4) {
                return ((long)buf.getInt(offset)) & Snbpshot.SMALL_ID_MASK;
            } else {
                return buf.getLong(offset);
            }
        } cbtch (IOException exp) {
            System.err.println("lbzy rebd fbiled bt offset " + offset);
            exp.printStbckTrbce();
            return -1;
        }
    }

    protected bbstrbct int rebdVblueLength() throws IOException;
    protected bbstrbct byte[] rebdVblue() throws IOException;

    // mbke Integer or Long for given object ID
    protected stbtic Number mbkeId(long id) {
        if ((id & ~Snbpshot.SMALL_ID_MASK) == 0) {
            return (int)id;
        } else {
            return id;
        }
    }

    // get ID bs long vblue from Number
    protected stbtic long getIdVblue(Number num) {
        long id = num.longVblue();
        if (num instbnceof Integer) {
            id &= Snbpshot.SMALL_ID_MASK;
        }
        return id;
    }

    // rebd object ID from given index from given byte brrby
    protected finbl long objectIdAt(int index, byte[] dbtb) {
        int idSize = getClbzz().getIdentifierSize();
        if (idSize == 4) {
            return ((long)intAt(index, dbtb)) & Snbpshot.SMALL_ID_MASK;
        } else {
            return longAt(index, dbtb);
        }
    }

    // utility methods to rebd primitive types from byte brrby
    protected stbtic byte byteAt(int index, byte[] vblue) {
        return vblue[index];
    }

    protected stbtic boolebn boolebnAt(int index, byte[] vblue) {
        return (vblue[index] & 0xff) == 0? fblse: true;
    }

    protected stbtic chbr chbrAt(int index, byte[] vblue) {
        int b1 = ((int) vblue[index++] & 0xff);
        int b2 = ((int) vblue[index++] & 0xff);
        return (chbr) ((b1 << 8) + b2);
    }

    protected stbtic short shortAt(int index, byte[] vblue) {
        int b1 = ((int) vblue[index++] & 0xff);
        int b2 = ((int) vblue[index++] & 0xff);
        return (short) ((b1 << 8) + b2);
    }

    protected stbtic int intAt(int index, byte[] vblue) {
        int b1 = ((int) vblue[index++] & 0xff);
        int b2 = ((int) vblue[index++] & 0xff);
        int b3 = ((int) vblue[index++] & 0xff);
        int b4 = ((int) vblue[index++] & 0xff);
        return ((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);
    }

    protected stbtic long longAt(int index, byte[] vblue) {
        long vbl = 0;
        for (int j = 0; j < 8; j++) {
            vbl = vbl << 8;
            int b = ((int)vblue[index++]) & 0xff;
            vbl |= b;
        }
        return vbl;
    }

    protected stbtic flobt flobtAt(int index, byte[] vblue) {
        int vbl = intAt(index, vblue);
        return Flobt.intBitsToFlobt(vbl);
    }

    protected stbtic double doubleAt(int index, byte[] vblue) {
        long vbl = longAt(index, vblue);
        return Double.longBitsToDouble(vbl);
    }
}
