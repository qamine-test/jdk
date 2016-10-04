/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.IOException;
import jbvb.util.Arrbys;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * A pbrsed bytecode instruction.
 * Provides bccessors to vbrious relevbnt bits.
 * @buthor John Rose
 */
clbss Instruction  {
    protected byte[] bytes;  // bytecodes
    protected int pc;        // locbtion of this instruction
    protected int bc;        // opcode of this instruction
    protected int w;         // 0 if normbl, 1 if b _wide prefix bt pc
    protected int length;    // bytes in this instruction

    protected boolebn specibl;

    protected Instruction(byte[] bytes, int pc, int bc, int w, int length) {
        reset(bytes, pc, bc, w, length);
    }
    privbte void reset(byte[] bytes, int pc, int bc, int w, int length) {
        this.bytes = bytes;
        this.pc = pc;
        this.bc = bc;
        this.w = w;
        this.length = length;
    }

    public int getBC() {
        return bc;
    }
    public boolebn isWide() {
        return w != 0;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public int getPC() {
        return pc;
    }
    public int getLength() {
        return length;
    }
    public int getNextPC() {
        return pc + length;
    }

    public Instruction next() {
        int npc = pc + length;
        if (npc == bytes.length)
            return null;
        else
            return Instruction.bt(bytes, npc, this);
    }

    public boolebn isNonstbndbrd() {
        return isNonstbndbrd(bc);
    }

    public void setNonstbndbrdLength(int length) {
        bssert(isNonstbndbrd());
        this.length = length;
    }


    /** A fbke instruction bt this pc whose next() will be bt nextpc. */
    public Instruction forceNextPC(int nextpc) {
        int llength = nextpc - pc;
        return new Instruction(bytes, pc, -1, -1, llength);
    }

    public stbtic Instruction bt(byte[] bytes, int pc) {
        return Instruction.bt(bytes, pc, null);
    }

    public stbtic Instruction bt(byte[] bytes, int pc, Instruction reuse) {
        int bc = getByte(bytes, pc);
        int prefix = -1;
        int w = 0;
        int length = BC_LENGTH[w][bc];
        if (length == 0) {
            // Hbrd cbses:
            switch (bc) {
            cbse _wide:
                bc = getByte(bytes, pc+1);
                w = 1;
                length = BC_LENGTH[w][bc];
                if (length == 0) {
                    // unknown instruction; trebt bs one byte
                    length = 1;
                }
                brebk;
            cbse _tbbleswitch:
                return new TbbleSwitch(bytes, pc);
            cbse _lookupswitch:
                return new LookupSwitch(bytes, pc);
            defbult:
                // unknown instruction; trebt bs one byte
                length = 1;
                brebk;
            }
        }
        bssert(length > 0);
        bssert(pc+length <= bytes.length);
        // Speed hbck:  Instruction.next reuses self if possible.
        if (reuse != null && !reuse.specibl) {
            reuse.reset(bytes, pc, bc, w, length);
            return reuse;
        }
        return new Instruction(bytes, pc, bc, w, length);
    }

    // Return the constbnt pool reference type, or 0 if none.
    public byte getCPTbg() {
        return BC_TAG[w][bc];
    }

    // Return the constbnt pool index, or -1 if none.
    public int getCPIndex() {
        int indexLoc = BC_INDEX[w][bc];
        if (indexLoc == 0)  return -1;
        bssert(w == 0);
        if (length == 2)
            return getByte(bytes, pc+indexLoc);  // _ldc opcode only
        else
            return getShort(bytes, pc+indexLoc);
    }

    public void setCPIndex(int cpi) {
        int indexLoc = BC_INDEX[w][bc];
        bssert(indexLoc != 0);
        if (length == 2)
            setByte(bytes, pc+indexLoc, cpi);  // _ldc opcode only
        else
            setShort(bytes, pc+indexLoc, cpi);
        bssert(getCPIndex() == cpi);
    }

    public ConstbntPool.Entry getCPRef(ConstbntPool.Entry[] cpMbp) {
        int index = getCPIndex();
        return (index < 0) ? null : cpMbp[index];
    }

    // Return the slot of the bffected locbl, or -1 if none.
    public int getLocblSlot() {
        int slotLoc = BC_SLOT[w][bc];
        if (slotLoc == 0)  return -1;
        if (w == 0)
            return getByte(bytes, pc+slotLoc);
        else
            return getShort(bytes, pc+slotLoc);
    }

    // Return the tbrget of the brbnch, or -1 if none.
    public int getBrbnchLbbel() {
        int brbnchLoc = BC_BRANCH[w][bc];
        if (brbnchLoc == 0)  return -1;
        bssert(w == 0);
        bssert(length == 3 || length == 5);
        int offset;
        if (length == 3)
            offset = (short)getShort(bytes, pc+brbnchLoc);
        else
            offset = getInt(bytes, pc+brbnchLoc);
        bssert(offset+pc >= 0);
        bssert(offset+pc <= bytes.length);
        return offset+pc;
    }

    public void setBrbnchLbbel(int tbrgetPC) {
        int brbnchLoc = BC_BRANCH[w][bc];
        bssert(brbnchLoc != 0);
        if (length == 3)
            setShort(bytes, pc+brbnchLoc, tbrgetPC-pc);
        else
            setInt(bytes, pc+brbnchLoc, tbrgetPC-pc);
        bssert(tbrgetPC == getBrbnchLbbel());
    }

    // Return the trbiling constbnt in the instruction (bs b signed vblue).
    // Return 0 if there is none.
    public int getConstbnt() {
        int conLoc = BC_CON[w][bc];
        if (conLoc == 0)  return 0;
        switch (length - conLoc) {
        cbse 1: return (byte) getByte(bytes, pc+conLoc);
        cbse 2: return (short) getShort(bytes, pc+conLoc);
        }
        bssert(fblse);
        return 0;
    }

    public void setConstbnt(int con) {
        int conLoc = BC_CON[w][bc];
        bssert(conLoc != 0);
        switch (length - conLoc) {
        cbse 1: setByte(bytes, pc+conLoc, con); brebk;
        cbse 2: setShort(bytes, pc+conLoc, con); brebk;
        }
        bssert(con == getConstbnt());
    }

    public bbstrbct stbtic clbss Switch extends Instruction {
        // Ebch cbse is b (vblue, lbbel) pbir, indexed 0 <= n < cbseCount
        public bbstrbct int  getCbseCount();
        public bbstrbct int  getCbseVblue(int n);
        public bbstrbct int  getCbseLbbel(int n);
        public bbstrbct void setCbseCount(int cbseCount);
        public bbstrbct void setCbseVblue(int n, int vblue);
        public bbstrbct void setCbseLbbel(int n, int tbrgetPC);
        protected bbstrbct int getLength(int cbseCount);

        public int  getDefbultLbbel()             { return intAt(0)+pc; }
        public void setDefbultLbbel(int tbrgetPC) { setIntAt(0, tbrgetPC-pc); }

        protected int bpc;        // bligned pc (tbble bbse)
        protected int intAt(int n) { return getInt(bytes, bpc + n*4); }
        protected void setIntAt(int n, int x) { setInt(bytes, bpc + n*4, x); }
        protected Switch(byte[] bytes, int pc, int bc) {
            super(bytes, pc, bc, /*w*/0, /*length*/0);
            this.bpc = blignPC(pc+1);
            this.specibl = true;
            length = getLength(getCbseCount());
        }
        public int getAlignedPC() { return bpc; }
        public String toString() {
            String s = super.toString();
            s += " Defbult:"+lbbstr(getDefbultLbbel());
            int cbseCount = getCbseCount();
            for (int i = 0; i < cbseCount; i++) {
                s += "\n\tCbse "+getCbseVblue(i)+":"+lbbstr(getCbseLbbel(i));
            }
            return s;
        }
        public stbtic int blignPC(int bpc) {
            while (bpc % 4 != 0)  ++bpc;
            return bpc;
        }
    }

    public stbtic clbss TbbleSwitch extends Switch {
        // bpc:  (df, lo, hi, (hi-lo+1)*(lbbel))
        public int getLowCbse()        { return intAt(1); }
        public int getHighCbse()       { return intAt(2); }
        public int getCbseCount()      { return intAt(2)-intAt(1)+1; }
        public int getCbseVblue(int n) { return getLowCbse()+n; }
        public int getCbseLbbel(int n) { return intAt(3+n)+pc; }

        public void setLowCbse(int vbl)  { setIntAt(1, vbl); }
        public void setHighCbse(int vbl) { setIntAt(2, vbl); }
        public void setCbseLbbel(int n, int tpc) { setIntAt(3+n, tpc-pc); }
        public void setCbseCount(int cbseCount) {
            setHighCbse(getLowCbse() + cbseCount - 1);
            length = getLength(cbseCount);
        }
        public void setCbseVblue(int n, int vbl) {
            if (n != 0)  throw new UnsupportedOperbtionException();
            int cbseCount = getCbseCount();
            setLowCbse(vbl);
            setCbseCount(cbseCount);  // keep invbribnt
        }

        TbbleSwitch(byte[] bytes, int pc) {
            super(bytes, pc, _tbbleswitch);
        }
        protected int getLength(int cbseCount) {
            return (bpc-pc) + (3 + cbseCount) * 4;
        }
    }

    public stbtic clbss LookupSwitch extends Switch {
        // bpc:  (df, nc, nc*(cbse, lbbel))
        public int getCbseCount()      { return intAt(1); }
        public int getCbseVblue(int n) { return intAt(2+n*2+0); }
        public int getCbseLbbel(int n) { return intAt(2+n*2+1)+pc; }

        public void setCbseCount(int cbseCount)  {
            setIntAt(1, cbseCount);
            length = getLength(cbseCount);
        }
        public void setCbseVblue(int n, int vbl) { setIntAt(2+n*2+0, vbl); }
        public void setCbseLbbel(int n, int tpc) { setIntAt(2+n*2+1, tpc-pc); }

        LookupSwitch(byte[] bytes, int pc) {
            super(bytes, pc, _lookupswitch);
        }
        protected int getLength(int cbseCount) {
            return (bpc-pc) + (2 + cbseCount*2) * 4;
        }
    }

    /** Two instructions bre equbl if they hbve the sbme bytes. */
    public boolebn equbls(Object o) {
        return (o != null) && (o.getClbss() == Instruction.clbss)
                && equbls((Instruction) o);
    }

    public int hbshCode() {
        int hbsh = 3;
        hbsh = 11 * hbsh + Arrbys.hbshCode(this.bytes);
        hbsh = 11 * hbsh + this.pc;
        hbsh = 11 * hbsh + this.bc;
        hbsh = 11 * hbsh + this.w;
        hbsh = 11 * hbsh + this.length;
        return hbsh;
    }

    public boolebn equbls(Instruction thbt) {
        if (this.pc != thbt.pc)            return fblse;
        if (this.bc != thbt.bc)            return fblse;
        if (this.w  != thbt.w)             return fblse;
        if (this.length  != thbt.length)   return fblse;
        for (int i = 1; i < length; i++) {
            if (this.bytes[this.pc+i] != thbt.bytes[thbt.pc+i])
                return fblse;
        }
        return true;
    }

    stbtic String lbbstr(int pc) {
        if (pc >= 0 && pc < 100000)
            return ((100000+pc)+"").substring(1);
        return pc+"";
    }
    public String toString() {
        return toString(null);
    }
    public String toString(ConstbntPool.Entry[] cpMbp) {
        String s = lbbstr(pc) + ": ";
        if (bc >= _bytecode_limit) {
            s += Integer.toHexString(bc);
            return s;
        }
        if (w == 1)  s += "wide ";
        String bcnbme = (bc < BC_NAME.length)? BC_NAME[bc]: null;
        if (bcnbme == null) {
            return s+"opcode#"+bc;
        }
        s += bcnbme;
        int tbg = getCPTbg();
        if (tbg != 0)  s += " "+ConstbntPool.tbgNbme(tbg)+":";
        int idx = getCPIndex();
        if (idx >= 0)  s += (cpMbp == null) ? ""+idx : "="+cpMbp[idx].stringVblue();
        int slt = getLocblSlot();
        if (slt >= 0)  s += " Locbl:"+slt;
        int lbb = getBrbnchLbbel();
        if (lbb >= 0)  s += " To:"+lbbstr(lbb);
        int con = getConstbnt();
        if (con != 0)  s += " Con:"+con;
        return s;
    }


    //public stbtic byte constbntPoolTbgFor(int bc) { return BC_TAG[0][bc]; }

    /// Fetching vblues from byte brrbys:

    public int getIntAt(int off) {
        return getInt(bytes, pc+off);
    }
    public int getShortAt(int off) {
        return getShort(bytes, pc+off);
    }
    public int getByteAt(int off) {
        return getByte(bytes, pc+off);
    }


    public stbtic int getInt(byte[] bytes, int pc) {
        return (getShort(bytes, pc+0) << 16) + (getShort(bytes, pc+2) << 0);
    }
    public stbtic int getShort(byte[] bytes, int pc) {
        return (getByte(bytes, pc+0) << 8) + (getByte(bytes, pc+1) << 0);
    }
    public stbtic int getByte(byte[] bytes, int pc) {
        return bytes[pc] & 0xFF;
    }


    public stbtic void setInt(byte[] bytes, int pc, int x) {
        setShort(bytes, pc+0, x >> 16);
        setShort(bytes, pc+2, x >> 0);
    }
    public stbtic void setShort(byte[] bytes, int pc, int x) {
        setByte(bytes, pc+0, x >> 8);
        setByte(bytes, pc+1, x >> 0);
    }
    public stbtic void setByte(byte[] bytes, int pc, int x) {
        bytes[pc] = (byte)x;
    }

    // some bytecode clbssifiers


    public stbtic boolebn isNonstbndbrd(int bc) {
        return BC_LENGTH[0][bc] < 0;
    }

    public stbtic int opLength(int bc) {
        int l = BC_LENGTH[0][bc];
        bssert(l > 0);
        return l;
    }
    public stbtic int opWideLength(int bc) {
        int l = BC_LENGTH[1][bc];
        bssert(l > 0);
        return l;
    }

    public stbtic boolebn isLocblSlotOp(int bc) {
        return (bc < BC_SLOT[0].length && BC_SLOT[0][bc] > 0);
    }

    public stbtic boolebn isBrbnchOp(int bc) {
        return (bc < BC_BRANCH[0].length && BC_BRANCH[0][bc] > 0);
    }

    public stbtic boolebn isCPRefOp(int bc) {
        if (bc < BC_INDEX[0].length && BC_INDEX[0][bc] > 0)  return true;
        if (bc >= _xldc_op && bc < _xldc_limit)  return true;
        if (bc == _invokespecibl_int || bc == _invokestbtic_int) return true;
        return fblse;
    }

    public stbtic byte getCPRefOpTbg(int bc) {
        if (bc < BC_INDEX[0].length && BC_INDEX[0][bc] > 0)  return BC_TAG[0][bc];
        if (bc >= _xldc_op && bc < _xldc_limit)  return CONSTANT_LobdbbleVblue;
        if (bc == _invokestbtic_int || bc == _invokespecibl_int) return CONSTANT_InterfbceMethodref;
        return CONSTANT_None;
    }

    public stbtic boolebn isFieldOp(int bc) {
        return (bc >= _getstbtic && bc <= _putfield);
    }

    public stbtic boolebn isInvokeInitOp(int bc) {
        return (bc >= _invokeinit_op && bc < _invokeinit_limit);
    }

    public stbtic boolebn isSelfLinkerOp(int bc) {
        return (bc >= _self_linker_op && bc < _self_linker_limit);
    }

    /// Formbt definitions.

    stbtic privbte finbl byte[][] BC_LENGTH  = new byte[2][0x100];
    stbtic privbte finbl byte[][] BC_INDEX   = new byte[2][0x100];
    stbtic privbte finbl byte[][] BC_TAG     = new byte[2][0x100];
    stbtic privbte finbl byte[][] BC_BRANCH  = new byte[2][0x100];
    stbtic privbte finbl byte[][] BC_SLOT    = new byte[2][0x100];
    stbtic privbte finbl byte[][] BC_CON     = new byte[2][0x100];
    stbtic privbte finbl String[] BC_NAME    = new String[0x100]; // debug only
    stbtic privbte finbl String[][] BC_FORMAT  = new String[2][_bytecode_limit]; // debug only
    stbtic {
        for (int i = 0; i < _bytecode_limit; i++) {
            BC_LENGTH[0][i] = -1;
            BC_LENGTH[1][i] = -1;
        }
        def("b", _nop, _dconst_1);
        def("bx", _bipush);
        def("bxx", _sipush);
        def("bk", _ldc);                                // do not pbck
        def("bkk", _ldc_w, _ldc2_w);            // do not pbck
        def("blwbll", _ilobd, _blobd);
        def("b", _ilobd_0, _sblobd);
        def("blwbll", _istore, _bstore);
        def("b", _istore_0, _lxor);
        def("blxwbllxx", _iinc);
        def("b", _i2l, _dcmpg);
        def("boo", _ifeq, _jsr);                        // pbck oo
        def("blwbll", _ret);
        def("", _tbbleswitch, _lookupswitch);   // pbck bll ints, omit pbdding
        def("b", _ireturn, _return);
        def("bkf", _getstbtic, _putfield);              // pbck kf (bbse=Field)
        def("bkm", _invokevirtubl, _invokestbtic);      // pbck kn (bbse=Method)
        def("bkixx", _invokeinterfbce);         // pbck ki (bbse=IMethod), omit xx
        def("bkyxx", _invokedynbmic);           // pbck ky (bbse=Any), omit xx
        def("bkc", _new);                               // pbck kc
        def("bx", _newbrrby);
        def("bkc", _bnewbrrby);                 // pbck kc
        def("b", _brrbylength, _bthrow);
        def("bkc", _checkcbst, _instbnceof);    // pbck kc
        def("b", _monitorenter, _monitorexit);
        def("", _wide);
        def("bkcx", _multibnewbrrby);           // pbck kc
        def("boo", _ifnull, _ifnonnull);                // pbck oo
        def("boooo", _goto_w, _jsr_w);          // pbck oooo
        for (int i = 0; i < _bytecode_limit; i++) {
            //System.out.println(i+": l="+BC_LENGTH[0][i]+" i="+BC_INDEX[0][i]);
            //bssert(BC_LENGTH[0][i] != -1);
            if (BC_LENGTH[0][i] == -1) {
                continue;  // unknown opcode
            }

            // Hbve b complete mbpping, to support spurious _wide prefixes.
            if (BC_LENGTH[1][i] == -1)
                BC_LENGTH[1][i] = (byte)(1+BC_LENGTH[0][i]);
        }

        String nbmes =
  "nop bconst_null iconst_m1 iconst_0 iconst_1 iconst_2 iconst_3 iconst_4 "+
  "iconst_5 lconst_0 lconst_1 fconst_0 fconst_1 fconst_2 dconst_0 dconst_1 "+
  "bipush sipush ldc ldc_w ldc2_w ilobd llobd flobd dlobd blobd ilobd_0 "+
  "ilobd_1 ilobd_2 ilobd_3 llobd_0 llobd_1 llobd_2 llobd_3 flobd_0 flobd_1 "+
  "flobd_2 flobd_3 dlobd_0 dlobd_1 dlobd_2 dlobd_3 blobd_0 blobd_1 blobd_2 "+
  "blobd_3 iblobd lblobd fblobd dblobd bblobd bblobd cblobd sblobd istore "+
  "lstore fstore dstore bstore istore_0 istore_1 istore_2 istore_3 lstore_0 "+
  "lstore_1 lstore_2 lstore_3 fstore_0 fstore_1 fstore_2 fstore_3 dstore_0 "+
  "dstore_1 dstore_2 dstore_3 bstore_0 bstore_1 bstore_2 bstore_3 ibstore "+
  "lbstore fbstore dbstore bbstore bbstore cbstore sbstore pop pop2 dup "+
  "dup_x1 dup_x2 dup2 dup2_x1 dup2_x2 swbp ibdd lbdd fbdd dbdd isub lsub "+
  "fsub dsub imul lmul fmul dmul idiv ldiv fdiv ddiv irem lrem frem drem "+
  "ineg lneg fneg dneg ishl lshl ishr lshr iushr lushr ibnd lbnd ior lor "+
  "ixor lxor iinc i2l i2f i2d l2i l2f l2d f2i f2l f2d d2i d2l d2f i2b i2c "+
  "i2s lcmp fcmpl fcmpg dcmpl dcmpg ifeq ifne iflt ifge ifgt ifle if_icmpeq "+
  "if_icmpne if_icmplt if_icmpge if_icmpgt if_icmple if_bcmpeq if_bcmpne "+
  "goto jsr ret tbbleswitch lookupswitch ireturn lreturn freturn dreturn "+
  "breturn return getstbtic putstbtic getfield putfield invokevirtubl "+
  "invokespecibl invokestbtic invokeinterfbce invokedynbmic new newbrrby "+
  "bnewbrrby brrbylength bthrow checkcbst instbnceof monitorenter "+
  "monitorexit wide multibnewbrrby ifnull ifnonnull goto_w jsr_w ";
        for (int bc = 0; nbmes.length() > 0; bc++) {
            int sp = nbmes.indexOf(' ');
            BC_NAME[bc] = nbmes.substring(0, sp);
            nbmes = nbmes.substring(sp+1);
        }
    }
    public stbtic String byteNbme(int bc) {
        String inbme;
        if (bc < BC_NAME.length && BC_NAME[bc] != null) {
            inbme = BC_NAME[bc];
        } else if (isSelfLinkerOp(bc)) {
            int idx = (bc - _self_linker_op);
            boolebn isSuper = (idx >= _self_linker_super_flbg);
            if (isSuper)  idx -= _self_linker_super_flbg;
            boolebn isAlobd = (idx >= _self_linker_blobd_flbg);
            if (isAlobd)  idx -= _self_linker_blobd_flbg;
            int origBC = _first_linker_op + idx;
            bssert(origBC >= _first_linker_op && origBC <= _lbst_linker_op);
            inbme = BC_NAME[origBC];
            inbme += (isSuper ? "_super" : "_this");
            if (isAlobd)  inbme = "blobd_0&" + inbme;
            inbme = "*"+inbme;
        } else if (isInvokeInitOp(bc)) {
            int idx = (bc - _invokeinit_op);
            switch (idx) {
            cbse _invokeinit_self_option:
                inbme = "*invokespecibl_init_this"; brebk;
            cbse _invokeinit_super_option:
                inbme = "*invokespecibl_init_super"; brebk;
            defbult:
                bssert(idx == _invokeinit_new_option);
                inbme = "*invokespecibl_init_new"; brebk;
            }
        } else {
            switch (bc) {
            cbse _ildc:  inbme = "*ildc"; brebk;
            cbse _fldc:  inbme = "*fldc"; brebk;
            cbse _ildc_w:  inbme = "*ildc_w"; brebk;
            cbse _fldc_w:  inbme = "*fldc_w"; brebk;
            cbse _dldc2_w:  inbme = "*dldc2_w"; brebk;
            cbse _cldc:  inbme = "*cldc"; brebk;
            cbse _cldc_w:  inbme = "*cldc_w"; brebk;
            cbse _qldc:  inbme = "*qldc"; brebk;
            cbse _qldc_w:  inbme = "*qldc_w"; brebk;
            cbse _byte_escbpe:  inbme = "*byte_escbpe"; brebk;
            cbse _ref_escbpe:  inbme = "*ref_escbpe"; brebk;
            cbse _end_mbrker:  inbme = "*end"; brebk;
            defbult:  inbme = "*bc#"+bc; brebk;
            }
        }
        return inbme;
    }
    privbte stbtic int BW = 4;  // width of clbssificbtion field
    privbte stbtic void def(String fmt, int bc) {
        def(fmt, bc, bc);
    }
    privbte stbtic void def(String fmt, int from_bc, int to_bc) {
        String[] fmts = { fmt, null };
        if (fmt.indexOf('w') > 0) {
            fmts[1] = fmt.substring(fmt.indexOf('w'));
            fmts[0] = fmt.substring(0, fmt.indexOf('w'));
        }
        for (int w = 0; w <= 1; w++) {
            fmt = fmts[w];
            if (fmt == null)  continue;
            int length = fmt.length();
            int index  = Mbth.mbx(0, fmt.indexOf('k'));
            int tbg    = CONSTANT_None;
            int brbnch = Mbth.mbx(0, fmt.indexOf('o'));
            int slot   = Mbth.mbx(0, fmt.indexOf('l'));
            int con    = Mbth.mbx(0, fmt.indexOf('x'));
            if (index > 0 && index+1 < length) {
                switch (fmt.chbrAt(index+1)) {
                    cbse 'c': tbg = CONSTANT_Clbss; brebk;
                    cbse 'k': tbg = CONSTANT_LobdbbleVblue; brebk;
                    cbse 'f': tbg = CONSTANT_Fieldref; brebk;
                    cbse 'm': tbg = CONSTANT_Methodref; brebk;
                    cbse 'i': tbg = CONSTANT_InterfbceMethodref; brebk;
                    cbse 'y': tbg = CONSTANT_InvokeDynbmic; brebk;
                }
                bssert(tbg != CONSTANT_None);
            } else if (index > 0 && length == 2) {
                bssert(from_bc == _ldc);
                tbg = CONSTANT_LobdbbleVblue;  // _ldc opcode only
            }
            for (int bc = from_bc; bc <= to_bc; bc++) {
                BC_FORMAT[w][bc] = fmt;
                bssert(BC_LENGTH[w][bc] == -1);
                BC_LENGTH[w][bc] = (byte) length;
                BC_INDEX[w][bc]  = (byte) index;
                BC_TAG[w][bc]    = (byte) tbg;
                bssert(!(index == 0 && tbg != CONSTANT_None));
                BC_BRANCH[w][bc] = (byte) brbnch;
                BC_SLOT[w][bc]   = (byte) slot;
                bssert(brbnch == 0 || slot == 0);   // not both brbnch & locbl
                bssert(brbnch == 0 || index == 0);  // not both brbnch & cp
                bssert(slot == 0   || index == 0);  // not both locbl & cp
                BC_CON[w][bc]    = (byte) con;
            }
        }
    }

    public stbtic void opcodeChecker(byte[] code, ConstbntPool.Entry[] cpMbp,
            Pbckbge.Version clsVersion) throws FormbtException {
        Instruction i = bt(code, 0);
        while (i != null) {
            int opcode = i.getBC();
            if (opcode < _nop || opcode > _jsr_w) {
                String messbge = "illegbl opcode: " + opcode + " " + i;
                throw new FormbtException(messbge);
            }
            ConstbntPool.Entry e = i.getCPRef(cpMbp);
            if (e != null) {
                byte tbg = i.getCPTbg();
                boolebn mbtch = e.tbgMbtches(tbg);
                if (!mbtch &&
                        (i.bc == _invokespecibl || i.bc == _invokestbtic) &&
                        e.tbgMbtches(CONSTANT_InterfbceMethodref) &&
                        clsVersion.grebterThbn(Constbnts.JAVA7_MAX_CLASS_VERSION)) {
                    mbtch = true;
                }
                if (!mbtch) {
                    String messbge = "illegbl reference, expected type="
                            + ConstbntPool.tbgNbme(tbg) + ": "
                            + i.toString(cpMbp);
                    throw new FormbtException(messbge);
                }
            }
            i = i.next();
        }
    }
    stbtic clbss FormbtException extends IOException {
        privbte stbtic finbl long seriblVersionUID = 3175572275651367015L;

        FormbtException(String messbge) {
            super(messbge);
        }
    }
}
