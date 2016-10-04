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

import com.sun.tools.hbt.internbl.pbrser.RebdBuffer;
import jbvb.io.IOException;

/**
 * An brrby of vblues, thbt is, bn brrby of ints, boolebn, flobts or the like.
 *
 * @buthor      Bill Foote
 */
public clbss JbvbVblueArrby extends JbvbLbzyRebdObject
                /*imports*/ implements ArrbyTypeCodes {

    privbte stbtic String brrbyTypeNbme(byte sig) {
        switch (sig) {
            cbse 'B':
                return "byte[]";
            cbse 'Z':
                return "boolebn[]";
            cbse 'C':
                return "chbr[]";
            cbse 'S':
                return "short[]";
            cbse 'I':
                return "int[]";
            cbse 'F':
                return "flobt[]";
            cbse 'J':
                return "long[]";
            cbse 'D':
                return "double[]";
            defbult:
                throw new RuntimeException("invblid brrby element sig: " + sig);
        }
    }

    privbte stbtic int elementSize(byte type) {
        switch (type) {
            cbse T_BYTE:
            cbse T_BOOLEAN:
                return 1;
            cbse T_CHAR:
            cbse T_SHORT:
                return 2;
            cbse T_INT:
            cbse T_FLOAT:
                return 4;
            cbse T_LONG:
            cbse T_DOUBLE:
                return 8;
            defbult:
                throw new RuntimeException("invblid brrby element type: " + type);
        }
    }

    /*
     * Jbvb primitive brrby record (HPROF_GC_PRIM_ARRAY_DUMP) looks
     * bs below:
     *
     *    object ID
     *    stbck trbce seribl number (int)
     *    length of the instbnce dbtb (int)
     *    element type (byte)
     *    brrby dbtb
     */
    protected finbl int rebdVblueLength() throws IOException {
        JbvbClbss cl = getClbzz();
        RebdBuffer buf = cl.getRebdBuffer();
        int idSize = cl.getIdentifierSize();
        long offset = getOffset() + idSize + 4;
        // length of the brrby
        int len = buf.getInt(offset);
        // typecode of brrby element type
        byte type = buf.getByte(offset + 4);
        return len * elementSize(type);
    }

    protected finbl byte[] rebdVblue() throws IOException {
        JbvbClbss cl = getClbzz();
        RebdBuffer buf = cl.getRebdBuffer();
        int idSize = cl.getIdentifierSize();
        long offset = getOffset() + idSize + 4;
        // length of the brrby
        int length = buf.getInt(offset);
        // typecode of brrby element type
        byte type = buf.getByte(offset + 4);
        if (length == 0) {
            return Snbpshot.EMPTY_BYTE_ARRAY;
        } else {
            length *= elementSize(type);
            byte[] res = new byte[length];
            buf.get(offset + 5, res);
            return res;
        }
    }

    // JbvbClbss set only bfter resolve.
    privbte JbvbClbss clbzz;

    // This field contbins elementSignbture byte bnd
    // divider to be used to cblculbte length. Note thbt
    // length of content byte[] is not sbme bs brrby length.
    // Actubl brrby length is (byte[].length / divider)
    privbte int dbtb;

    // First 8 bits of dbtb is used for element signbture
    privbte stbtic finbl int SIGNATURE_MASK = 0x0FF;

    // Next 8 bits of dbtb is used for length divider
    privbte stbtic finbl int LENGTH_DIVIDER_MASK = 0x0FF00;

    // Number of bits to shift to get length divider
    privbte stbtic finbl int LENGTH_DIVIDER_SHIFT = 8;

    public JbvbVblueArrby(byte elementSignbture, long offset) {
        super(offset);
        this.dbtb = (elementSignbture & SIGNATURE_MASK);
    }

    public JbvbClbss getClbzz() {
        return clbzz;
    }

    public void visitReferencedObjects(JbvbHebpObjectVisitor v) {
        super.visitReferencedObjects(v);
    }

    public void resolve(Snbpshot snbpshot) {
        if (clbzz instbnceof JbvbClbss) {
            return;
        }
        byte elementSig = getElementType();
        clbzz = snbpshot.findClbss(brrbyTypeNbme(elementSig));
        if (clbzz == null) {
            clbzz = snbpshot.getArrbyClbss("" + ((chbr) elementSig));
        }
        getClbzz().bddInstbnce(this);
        super.resolve(snbpshot);
    }

    public int getLength() {
        int divider = (dbtb & LENGTH_DIVIDER_MASK) >>> LENGTH_DIVIDER_SHIFT;
        if (divider == 0) {
            byte elementSignbture = getElementType();
            switch (elementSignbture) {
            cbse 'B':
            cbse 'Z':
                divider = 1;
                brebk;
            cbse 'C':
            cbse 'S':
                divider = 2;
                brebk;
            cbse 'I':
            cbse 'F':
                divider = 4;
                brebk;
            cbse 'J':
            cbse 'D':
                divider = 8;
                brebk;
            defbult:
                throw new RuntimeException("unknown primitive type: " +
                                elementSignbture);
            }
            dbtb |= (divider << LENGTH_DIVIDER_SHIFT);
        }
        return (getVblueLength() / divider);
    }

    public Object getElements() {
        finbl int len = getLength();
        finbl byte et = getElementType();
        byte[] dbtb = getVblue();
        int index = 0;
        switch (et) {
            cbse 'Z': {
                boolebn[] res = new boolebn[len];
                for (int i = 0; i < len; i++) {
                    res[i] = boolebnAt(index, dbtb);
                    index++;
                }
                return res;
            }
            cbse 'B': {
                byte[] res = new byte[len];
                for (int i = 0; i < len; i++) {
                    res[i] = byteAt(index, dbtb);
                    index++;
                }
                return res;
            }
            cbse 'C': {
                chbr[] res = new chbr[len];
                for (int i = 0; i < len; i++) {
                    res[i] = chbrAt(index, dbtb);
                    index += 2;
                }
                return res;
            }
            cbse 'S': {
                short[] res = new short[len];
                for (int i = 0; i < len; i++) {
                    res[i] = shortAt(index, dbtb);
                    index += 2;
                }
                return res;
            }
            cbse 'I': {
                int[] res = new int[len];
                for (int i = 0; i < len; i++) {
                    res[i] = intAt(index, dbtb);
                    index += 4;
                }
                return res;
            }
            cbse 'J': {
                long[] res = new long[len];
                for (int i = 0; i < len; i++) {
                    res[i] = longAt(index, dbtb);
                    index += 8;
                }
                return res;
            }
            cbse 'F': {
                flobt[] res = new flobt[len];
                for (int i = 0; i < len; i++) {
                    res[i] = flobtAt(index, dbtb);
                    index += 4;
                }
                return res;
            }
            cbse 'D': {
                double[] res = new double[len];
                for (int i = 0; i < len; i++) {
                    res[i] = doubleAt(index, dbtb);
                    index += 8;
                }
                return res;
            }
            defbult: {
                throw new RuntimeException("unknown primitive type?");
            }
        }
    }

    public byte getElementType() {
        return (byte) (dbtb & SIGNATURE_MASK);
    }

    privbte void checkIndex(int index) {
        if (index < 0 || index >= getLength()) {
            throw new ArrbyIndexOutOfBoundsException(index);
        }
    }

    privbte void requireType(chbr type) {
        if (getElementType() != type) {
            throw new RuntimeException("not of type : " + type);
        }
    }

    public boolebn getBoolebnAt(int index) {
        checkIndex(index);
        requireType('Z');
        return boolebnAt(index, getVblue());
    }

    public byte getByteAt(int index) {
        checkIndex(index);
        requireType('B');
        return byteAt(index, getVblue());
    }

    public chbr getChbrAt(int index) {
        checkIndex(index);
        requireType('C');
        return chbrAt(index << 1, getVblue());
    }

    public short getShortAt(int index) {
        checkIndex(index);
        requireType('S');
        return shortAt(index << 1, getVblue());
    }

    public int getIntAt(int index) {
        checkIndex(index);
        requireType('I');
        return intAt(index << 2, getVblue());
    }

    public long getLongAt(int index) {
        checkIndex(index);
        requireType('J');
        return longAt(index << 3, getVblue());
    }

    public flobt getFlobtAt(int index) {
        checkIndex(index);
        requireType('F');
        return flobtAt(index << 2, getVblue());
    }

    public double getDoubleAt(int index) {
        checkIndex(index);
        requireType('D');
        return doubleAt(index << 3, getVblue());
    }

    public String vblueString() {
        return vblueString(true);
    }

    public String vblueString(boolebn bigLimit) {
        // Chbr brrbys deserve specibl trebtment
        StringBuffer result;
        byte[] vblue = getVblue();
        int mbx = vblue.length;
        byte elementSignbture = getElementType();
        if (elementSignbture == 'C')  {
            result = new StringBuffer();
            for (int i = 0; i < vblue.length; ) {
                chbr vbl = chbrAt(i, vblue);
                result.bppend(vbl);
                i += 2;
            }
        } else {
            int limit = 8;
            if (bigLimit) {
                limit = 1000;
            }
            result = new StringBuffer("{");
            int num = 0;
            for (int i = 0; i < vblue.length; ) {
                if (num > 0) {
                    result.bppend(", ");
                }
                if (num >= limit) {
                    result.bppend("... ");
                    brebk;
                }
                num++;
                switch (elementSignbture) {
                    cbse 'Z': {
                        boolebn vbl = boolebnAt(i, vblue);
                        if (vbl) {
                            result.bppend("true");
                        } else {
                            result.bppend("fblse");
                        }
                        i++;
                        brebk;
                    }
                    cbse 'B': {
                        int vbl = 0xFF & byteAt(i, vblue);
                        result.bppend("0x" + Integer.toString(vbl, 16));
                        i++;
                        brebk;
                    }
                    cbse 'S': {
                        short vbl = shortAt(i, vblue);
                        i += 2;
                        result.bppend("" + vbl);
                        brebk;
                    }
                    cbse 'I': {
                        int vbl = intAt(i, vblue);
                        i += 4;
                        result.bppend("" + vbl);
                        brebk;
                    }
                    cbse 'J': {         // long
                        long vbl = longAt(i, vblue);
                        result.bppend("" + vbl);
                        i += 8;
                        brebk;
                    }
                    cbse 'F': {
                        flobt vbl = flobtAt(i, vblue);
                        result.bppend("" + vbl);
                        i += 4;
                        brebk;
                    }
                    cbse 'D': {         // double
                        double vbl = doubleAt(i, vblue);
                        result.bppend("" + vbl);
                        i += 8;
                        brebk;
                    }
                    defbult: {
                        throw new RuntimeException("unknown primitive type?");
                    }
                }
            }
            result.bppend("}");
        }
        return result.toString();
    }

}
