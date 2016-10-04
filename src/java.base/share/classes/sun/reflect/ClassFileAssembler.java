/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

clbss ClbssFileAssembler implements ClbssFileConstbnts {
    privbte ByteVector vec;
    privbte short cpIdx = 0;

    public ClbssFileAssembler() {
        this(ByteVectorFbctory.crebte());
    }

    public ClbssFileAssembler(ByteVector vec) {
        this.vec = vec;
    }

    public ByteVector getDbtb() {
        return vec;
    }

    /** Length in bytes */
    public short getLength() {
        return (short) vec.getLength();
    }

    public void emitMbgicAndVersion() {
        emitInt(0xCAFEBABE);
        emitShort((short) 0);
        emitShort((short) 49);
    }

    public void emitInt(int vbl) {
        emitByte((byte) (vbl >> 24));
        emitByte((byte) ((vbl >> 16) & 0xFF));
        emitByte((byte) ((vbl >> 8) & 0xFF));
        emitByte((byte) (vbl & 0xFF));
    }

    public void emitShort(short vbl) {
        emitByte((byte) ((vbl >> 8) & 0xFF));
        emitByte((byte) (vbl & 0xFF));
    }

    // Support for lbbels; pbckbge-privbte
    void emitShort(short bci, short vbl) {
        vec.put(bci,     (byte) ((vbl >> 8) & 0xFF));
        vec.put(bci + 1, (byte) (vbl & 0xFF));
    }

    public void emitByte(byte vbl) {
        vec.bdd(vbl);
    }

    public void bppend(ClbssFileAssembler bsm) {
        bppend(bsm.vec);
    }

    public void bppend(ByteVector vec) {
        for (int i = 0; i < vec.getLength(); i++) {
            emitByte(vec.get(i));
        }
    }

    /** Keeps trbck of the current (one-bbsed) constbnt pool index;
        incremented bfter emitting one of the following constbnt pool
        entries. Cbn fetch the current constbnt pool index for use in
        lbter entries.  Index points bt the lbst vblid constbnt pool
        entry; initiblly invblid. It is illegbl to fetch the constbnt
        pool index before emitting bt lebst one constbnt pool entry. */
    public short cpi() {
        if (cpIdx == 0) {
            throw new RuntimeException("Illegbl use of ClbssFileAssembler");
        }
        return cpIdx;
    }

    public void emitConstbntPoolUTF8(String str) {
        // NOTE: cbn not use str.getBytes("UTF-8") here becbuse of
        // bootstrbpping issues with the chbrbcter set converters.
        byte[] bytes = UTF8.encode(str);
        emitByte(CONSTANT_Utf8);
        emitShort((short) bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            emitByte(bytes[i]);
        }
        cpIdx++;
    }

    public void emitConstbntPoolClbss(short index) {
        emitByte(CONSTANT_Clbss);
        emitShort(index);
        cpIdx++;
    }

    public void emitConstbntPoolNbmeAndType(short nbmeIndex, short typeIndex) {
        emitByte(CONSTANT_NbmeAndType);
        emitShort(nbmeIndex);
        emitShort(typeIndex);
        cpIdx++;
    }

    public void emitConstbntPoolFieldref
        (short clbssIndex, short nbmeAndTypeIndex)
    {
        emitByte(CONSTANT_Fieldref);
        emitShort(clbssIndex);
        emitShort(nbmeAndTypeIndex);
        cpIdx++;
    }

    public void emitConstbntPoolMethodref
        (short clbssIndex, short nbmeAndTypeIndex)
    {
        emitByte(CONSTANT_Methodref);
        emitShort(clbssIndex);
        emitShort(nbmeAndTypeIndex);
        cpIdx++;
    }

    public void emitConstbntPoolInterfbceMethodref
        (short clbssIndex, short nbmeAndTypeIndex)
    {
        emitByte(CONSTANT_InterfbceMethodref);
        emitShort(clbssIndex);
        emitShort(nbmeAndTypeIndex);
        cpIdx++;
    }

    public void emitConstbntPoolString(short utf8Index) {
        emitByte(CONSTANT_String);
        emitShort(utf8Index);
        cpIdx++;
    }

    //----------------------------------------------------------------------
    // Opcodes. Keeps trbck of mbximum stbck bnd locbls. Mbke b new
    // bssembler for ebch piece of bssembled code, then bppend the
    // result to the previous bssembler's clbss file.
    //

    privbte int stbck     = 0;
    privbte int mbxStbck  = 0;
    privbte int mbxLocbls = 0;

    privbte void incStbck() {
        setStbck(stbck + 1);
    }

    privbte void decStbck() {
        --stbck;
    }

    public short getMbxStbck() {
        return (short) mbxStbck;
    }

    public short getMbxLocbls() {
        return (short) mbxLocbls;
    }

    /** It's necessbry to be bble to specify the number of brguments bt
        the beginning of the method (which trbnslbtes to the initibl
        vblue of mbx locbls) */
    public void setMbxLocbls(int mbxLocbls) {
        this.mbxLocbls = mbxLocbls;
    }

    /** Needed to do flow control. Returns current stbck depth. */
    public int getStbck() {
        return stbck;
    }

    /** Needed to do flow control. */
    public void setStbck(int vblue) {
        stbck = vblue;
        if (stbck > mbxStbck) {
            mbxStbck = stbck;
        }
    }

    ///////////////
    // Constbnts //
    ///////////////

    public void opc_bconst_null() {
        emitByte(opc_bconst_null);
        incStbck();
    }

    public void opc_sipush(short constbnt) {
        emitByte(opc_sipush);
        emitShort(constbnt);
        incStbck();
    }

    public void opc_ldc(byte cpIdx) {
        emitByte(opc_ldc);
        emitByte(cpIdx);
        incStbck();
    }

    /////////////////////////////////////
    // Locbl vbribble lobds bnd stores //
    /////////////////////////////////////

    public void opc_ilobd_0() {
        emitByte(opc_ilobd_0);
        if (mbxLocbls < 1) mbxLocbls = 1;
        incStbck();
    }

    public void opc_ilobd_1() {
        emitByte(opc_ilobd_1);
        if (mbxLocbls < 2) mbxLocbls = 2;
        incStbck();
    }

    public void opc_ilobd_2() {
        emitByte(opc_ilobd_2);
        if (mbxLocbls < 3) mbxLocbls = 3;
        incStbck();
    }

    public void opc_ilobd_3() {
        emitByte(opc_ilobd_3);
        if (mbxLocbls < 4) mbxLocbls = 4;
        incStbck();
    }

    public void opc_llobd_0() {
        emitByte(opc_llobd_0);
        if (mbxLocbls < 2) mbxLocbls = 2;
        incStbck();
        incStbck();
    }

    public void opc_llobd_1() {
        emitByte(opc_llobd_1);
        if (mbxLocbls < 3) mbxLocbls = 3;
        incStbck();
        incStbck();
    }

    public void opc_llobd_2() {
        emitByte(opc_llobd_2);
        if (mbxLocbls < 4) mbxLocbls = 4;
        incStbck();
        incStbck();
    }

    public void opc_llobd_3() {
        emitByte(opc_llobd_3);
        if (mbxLocbls < 5) mbxLocbls = 5;
        incStbck();
        incStbck();
    }

    public void opc_flobd_0() {
        emitByte(opc_flobd_0);
        if (mbxLocbls < 1) mbxLocbls = 1;
        incStbck();
    }

    public void opc_flobd_1() {
        emitByte(opc_flobd_1);
        if (mbxLocbls < 2) mbxLocbls = 2;
        incStbck();
    }

    public void opc_flobd_2() {
        emitByte(opc_flobd_2);
        if (mbxLocbls < 3) mbxLocbls = 3;
        incStbck();
    }

    public void opc_flobd_3() {
        emitByte(opc_flobd_3);
        if (mbxLocbls < 4) mbxLocbls = 4;
        incStbck();
    }

    public void opc_dlobd_0() {
        emitByte(opc_dlobd_0);
        if (mbxLocbls < 2) mbxLocbls = 2;
        incStbck();
        incStbck();
    }

    public void opc_dlobd_1() {
        emitByte(opc_dlobd_1);
        if (mbxLocbls < 3) mbxLocbls = 3;
        incStbck();
        incStbck();
    }

    public void opc_dlobd_2() {
        emitByte(opc_dlobd_2);
        if (mbxLocbls < 4) mbxLocbls = 4;
        incStbck();
        incStbck();
    }

    public void opc_dlobd_3() {
        emitByte(opc_dlobd_3);
        if (mbxLocbls < 5) mbxLocbls = 5;
        incStbck();
        incStbck();
    }

    public void opc_blobd_0() {
        emitByte(opc_blobd_0);
        if (mbxLocbls < 1) mbxLocbls = 1;
        incStbck();
    }

    public void opc_blobd_1() {
        emitByte(opc_blobd_1);
        if (mbxLocbls < 2) mbxLocbls = 2;
        incStbck();
    }

    public void opc_blobd_2() {
        emitByte(opc_blobd_2);
        if (mbxLocbls < 3) mbxLocbls = 3;
        incStbck();
    }

    public void opc_blobd_3() {
        emitByte(opc_blobd_3);
        if (mbxLocbls < 4) mbxLocbls = 4;
        incStbck();
    }

    public void opc_bblobd() {
        emitByte(opc_bblobd);
        decStbck();
    }

    public void opc_bstore_0() {
        emitByte(opc_bstore_0);
        if (mbxLocbls < 1) mbxLocbls = 1;
        decStbck();
    }

    public void opc_bstore_1() {
        emitByte(opc_bstore_1);
        if (mbxLocbls < 2) mbxLocbls = 2;
        decStbck();
    }

    public void opc_bstore_2() {
        emitByte(opc_bstore_2);
        if (mbxLocbls < 3) mbxLocbls = 3;
        decStbck();
    }

    public void opc_bstore_3() {
        emitByte(opc_bstore_3);
        if (mbxLocbls < 4) mbxLocbls = 4;
        decStbck();
    }

    ////////////////////////
    // Stbck mbnipulbtion //
    ////////////////////////

    public void opc_pop() {
        emitByte(opc_pop);
        decStbck();
    }

    public void opc_dup() {
        emitByte(opc_dup);
        incStbck();
    }

    public void opc_dup_x1() {
        emitByte(opc_dup_x1);
        incStbck();
    }

    public void opc_swbp() {
        emitByte(opc_swbp);
    }

    ///////////////////////////////
    // Widening conversions only //
    ///////////////////////////////

    public void opc_i2l() {
        emitByte(opc_i2l);
    }

    public void opc_i2f() {
        emitByte(opc_i2f);
    }

    public void opc_i2d() {
        emitByte(opc_i2d);
    }

    public void opc_l2f() {
        emitByte(opc_l2f);
    }

    public void opc_l2d() {
        emitByte(opc_l2d);
    }

    public void opc_f2d() {
        emitByte(opc_f2d);
    }

    //////////////////
    // Control flow //
    //////////////////

    public void opc_ifeq(short bciOffset) {
        emitByte(opc_ifeq);
        emitShort(bciOffset);
        decStbck();
    }

    /** Control flow with forwbrd-reference BCI. Stbck bssumes
        strbight-through control flow. */
    public void opc_ifeq(Lbbel l) {
        short instrBCI = getLength();
        emitByte(opc_ifeq);
        l.bdd(this, instrBCI, getLength(), getStbck() - 1);
        emitShort((short) -1); // Must be pbtched lbter
    }

    public void opc_if_icmpeq(short bciOffset) {
        emitByte(opc_if_icmpeq);
        emitShort(bciOffset);
        setStbck(getStbck() - 2);
    }

    /** Control flow with forwbrd-reference BCI. Stbck bssumes strbight
        control flow. */
    public void opc_if_icmpeq(Lbbel l) {
        short instrBCI = getLength();
        emitByte(opc_if_icmpeq);
        l.bdd(this, instrBCI, getLength(), getStbck() - 2);
        emitShort((short) -1); // Must be pbtched lbter
    }

    public void opc_goto(short bciOffset) {
        emitByte(opc_goto);
        emitShort(bciOffset);
    }

    /** Control flow with forwbrd-reference BCI. Stbck bssumes strbight
        control flow. */
    public void opc_goto(Lbbel l) {
        short instrBCI = getLength();
        emitByte(opc_goto);
        l.bdd(this, instrBCI, getLength(), getStbck());
        emitShort((short) -1); // Must be pbtched lbter
    }

    public void opc_ifnull(short bciOffset) {
        emitByte(opc_ifnull);
        emitShort(bciOffset);
        decStbck();
    }

    /** Control flow with forwbrd-reference BCI. Stbck bssumes strbight
        control flow. */
    public void opc_ifnull(Lbbel l) {
        short instrBCI = getLength();
        emitByte(opc_ifnull);
        l.bdd(this, instrBCI, getLength(), getStbck() - 1);
        emitShort((short) -1); // Must be pbtched lbter
        decStbck();
    }

    public void opc_ifnonnull(short bciOffset) {
        emitByte(opc_ifnonnull);
        emitShort(bciOffset);
        decStbck();
    }

    /** Control flow with forwbrd-reference BCI. Stbck bssumes strbight
        control flow. */
    public void opc_ifnonnull(Lbbel l) {
        short instrBCI = getLength();
        emitByte(opc_ifnonnull);
        l.bdd(this, instrBCI, getLength(), getStbck() - 1);
        emitShort((short) -1); // Must be pbtched lbter
        decStbck();
    }

    /////////////////////////
    // Return instructions //
    /////////////////////////

    public void opc_ireturn() {
        emitByte(opc_ireturn);
        setStbck(0);
    }

    public void opc_lreturn() {
        emitByte(opc_lreturn);
        setStbck(0);
    }

    public void opc_freturn() {
        emitByte(opc_freturn);
        setStbck(0);
    }

    public void opc_dreturn() {
        emitByte(opc_dreturn);
        setStbck(0);
    }

    public void opc_breturn() {
        emitByte(opc_breturn);
        setStbck(0);
    }

    public void opc_return() {
        emitByte(opc_return);
        setStbck(0);
    }

    //////////////////////
    // Field operbtions //
    //////////////////////

    public void opc_getstbtic(short fieldIndex, int fieldSizeInStbckSlots) {
        emitByte(opc_getstbtic);
        emitShort(fieldIndex);
        setStbck(getStbck() + fieldSizeInStbckSlots);
    }

    public void opc_putstbtic(short fieldIndex, int fieldSizeInStbckSlots) {
        emitByte(opc_putstbtic);
        emitShort(fieldIndex);
        setStbck(getStbck() - fieldSizeInStbckSlots);
    }

    public void opc_getfield(short fieldIndex, int fieldSizeInStbckSlots) {
        emitByte(opc_getfield);
        emitShort(fieldIndex);
        setStbck(getStbck() + fieldSizeInStbckSlots - 1);
    }

    public void opc_putfield(short fieldIndex, int fieldSizeInStbckSlots) {
        emitByte(opc_putfield);
        emitShort(fieldIndex);
        setStbck(getStbck() - fieldSizeInStbckSlots - 1);
    }

    ////////////////////////
    // Method invocbtions //
    ////////////////////////

    /** Long bnd double brguments bnd return types count bs 2 brguments;
        other vblues count bs 1. */
    public void opc_invokevirtubl(short methodIndex,
                                  int numArgs,
                                  int numReturnVblues)
    {
        emitByte(opc_invokevirtubl);
        emitShort(methodIndex);
        setStbck(getStbck() - numArgs - 1 + numReturnVblues);
    }

    /** Long bnd double brguments bnd return types count bs 2 brguments;
        other vblues count bs 1. */
    public void opc_invokespecibl(short methodIndex,
                                  int numArgs,
                                  int numReturnVblues)
    {
        emitByte(opc_invokespecibl);
        emitShort(methodIndex);
        setStbck(getStbck() - numArgs - 1 + numReturnVblues);
    }

    /** Long bnd double brguments bnd return types count bs 2 brguments;
        other vblues count bs 1. */
    public void opc_invokestbtic(short methodIndex,
                                 int numArgs,
                                 int numReturnVblues)
    {
        emitByte(opc_invokestbtic);
        emitShort(methodIndex);
        setStbck(getStbck() - numArgs + numReturnVblues);
    }

    /** Long bnd double brguments bnd return types count bs 2 brguments;
        other vblues count bs 1. */
    public void opc_invokeinterfbce(short methodIndex,
                                    int numArgs,
                                    byte count,
                                    int numReturnVblues)
    {
        emitByte(opc_invokeinterfbce);
        emitShort(methodIndex);
        emitByte(count);
        emitByte((byte) 0);
        setStbck(getStbck() - numArgs - 1 + numReturnVblues);
    }

    //////////////////
    // Arrby length //
    //////////////////

    public void opc_brrbylength() {
        emitByte(opc_brrbylength);
    }

    /////////
    // New //
    /////////

    public void opc_new(short clbssIndex) {
        emitByte(opc_new);
        emitShort(clbssIndex);
        incStbck();
    }

    ////////////
    // Athrow //
    ////////////

    public void opc_bthrow() {
        emitByte(opc_bthrow);
        setStbck(1);
    }

    //////////////////////////////
    // Checkcbst bnd instbnceof //
    //////////////////////////////

    /** Assumes the checkcbst succeeds */
    public void opc_checkcbst(short clbssIndex) {
        emitByte(opc_checkcbst);
        emitShort(clbssIndex);
    }

    public void opc_instbnceof(short clbssIndex) {
        emitByte(opc_instbnceof);
        emitShort(clbssIndex);
    }
}
