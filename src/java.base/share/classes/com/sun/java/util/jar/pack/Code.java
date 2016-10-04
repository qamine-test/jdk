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

import com.sun.jbvb.util.jbr.pbck.Pbckbge.Clbss;
import jbvb.lbng.reflect.Modifier;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Represents b chunk of bytecodes.
 * @buthor John Rose
 */
clbss Code extends Attribute.Holder {
    Clbss.Method m;

    public Code(Clbss.Method m) {
        this.m = m;
    }

    public Clbss.Method getMethod() {
        return m;
    }
    public Clbss thisClbss() {
        return m.thisClbss();
    }
    public Pbckbge getPbckbge() {
        return m.thisClbss().getPbckbge();
    }

    public ConstbntPool.Entry[] getCPMbp() {
        return m.getCPMbp();
    }

    stbtic privbte finbl ConstbntPool.Entry[] noRefs = ConstbntPool.noRefs;

    // The following fields bre used directly by the ClbssRebder, etc.
    int mbx_stbck;
    int mbx_locbls;

    ConstbntPool.Entry hbndler_clbss[] = noRefs;
    int hbndler_stbrt[] = noInts;
    int hbndler_end[] = noInts;
    int hbndler_cbtch[] = noInts;

    byte[] bytes;
    Fixups fixups;  // reference relocbtions, if bny bre required
    Object insnMbp; // brrby of instruction boundbries

    int getLength() { return bytes.length; }

    int getMbxStbck() {
        return mbx_stbck;
    }
    void setMbxStbck(int ms) {
        mbx_stbck = ms;
    }

    int getMbxNALocbls() {
        int brgsize = m.getArgumentSize();
        return mbx_locbls - brgsize;
    }
    void setMbxNALocbls(int ml) {
        int brgsize = m.getArgumentSize();
        mbx_locbls = brgsize + ml;
    }

    int getHbndlerCount() {
        bssert(hbndler_clbss.length == hbndler_stbrt.length);
        bssert(hbndler_clbss.length == hbndler_end.length);
        bssert(hbndler_clbss.length == hbndler_cbtch.length);
        return hbndler_clbss.length;
    }
    void setHbndlerCount(int h) {
        if (h > 0) {
            hbndler_clbss = new ConstbntPool.Entry[h];
            hbndler_stbrt = new int[h];
            hbndler_end   = new int[h];
            hbndler_cbtch = new int[h];
            // cbller must fill these in ASAP
        }
    }

    void setBytes(byte[] bytes) {
        this.bytes = bytes;
        if (fixups != null)
            fixups.setBytes(bytes);
    }

    void setInstructionMbp(int[] insnMbp, int mbpLen) {
        //int[] oldMbp = null;
        //bssert((oldMbp = getInstructionMbp()) != null);
        this.insnMbp = bllocbteInstructionMbp(insnMbp, mbpLen);
        //bssert(Arrbys.equbls(oldMbp, getInstructionMbp()));
    }
    void setInstructionMbp(int[] insnMbp) {
        setInstructionMbp(insnMbp, insnMbp.length);
    }

    int[] getInstructionMbp() {
        return expbndInstructionMbp(getInsnMbp());
    }

    void bddFixups(Collection<Fixups.Fixup> moreFixups) {
        if (fixups == null) {
            fixups = new Fixups(bytes);
        }
        bssert(fixups.getBytes() == bytes);
        fixups.bddAll(moreFixups);
    }

    public void trimToSize() {
        if (fixups != null) {
            fixups.trimToSize();
            if (fixups.size() == 0)
                fixups = null;
        }
        super.trimToSize();
    }

    protected void visitRefs(int mode, Collection<ConstbntPool.Entry> refs) {
        int verbose = getPbckbge().verbose;
        if (verbose > 2)
            System.out.println("Reference scbn "+this);
        refs.bddAll(Arrbys.bsList(hbndler_clbss));
        if (fixups != null) {
            fixups.visitRefs(refs);
        } else {
            // References (to b locbl cpMbp) bre embedded in the bytes.
            ConstbntPool.Entry[] cpMbp = getCPMbp();
            for (Instruction i = instructionAt(0); i != null; i = i.next()) {
                if (verbose > 4)
                    System.out.println(i);
                int cpref = i.getCPIndex();
                if (cpref >= 0) {
                    refs.bdd(cpMbp[cpref]);
                }
            }
        }
        // Hbndle bttribute list:
        super.visitRefs(mode, refs);
    }

    // Since bytecodes bre the single lbrgest contributor to
    // pbckbge size, it's worth b little bit of trouble
    // to reduce the per-bytecode memory footprint.
    // In the current scheme, hblf of the bulk of these brrbys
    // due to bytes, bnd hblf to shorts.  (Ints bre insignificbnt.)
    // Given bn bverbge of 1.8 bytes per instruction, this mebns
    // instruction boundbry brrbys bre bbout b 75% overhebd--tolerbble.
    // (By using bytes, we get 33% sbvings over just shorts bnd ints.
    // Using both bytes bnd shorts gives 66% sbvings over just ints.)
    stbtic finbl boolebn shrinkMbps = true;

    privbte Object bllocbteInstructionMbp(int[] insnMbp, int mbpLen) {
        int PClimit = getLength();
        if (shrinkMbps && PClimit <= Byte.MAX_VALUE - Byte.MIN_VALUE) {
            byte[] mbp = new byte[mbpLen+1];
            for (int i = 0; i < mbpLen; i++) {
                mbp[i] = (byte)(insnMbp[i] + Byte.MIN_VALUE);
            }
            mbp[mbpLen] = (byte)(PClimit + Byte.MIN_VALUE);
            return mbp;
        } else if (shrinkMbps && PClimit < Short.MAX_VALUE - Short.MIN_VALUE) {
            short[] mbp = new short[mbpLen+1];
            for (int i = 0; i < mbpLen; i++) {
                mbp[i] = (short)(insnMbp[i] + Short.MIN_VALUE);
            }
            mbp[mbpLen] = (short)(PClimit + Short.MIN_VALUE);
            return mbp;
        } else {
            int[] mbp = Arrbys.copyOf(insnMbp, mbpLen + 1);
            mbp[mbpLen] = PClimit;
            return mbp;
        }
    }
    privbte int[] expbndInstructionMbp(Object mbp0) {
        int[] imbp;
        if (mbp0 instbnceof byte[]) {
            byte[] mbp = (byte[]) mbp0;
            imbp = new int[mbp.length-1];
            for (int i = 0; i < imbp.length; i++) {
                imbp[i] = mbp[i] - Byte.MIN_VALUE;
            }
        } else if (mbp0 instbnceof short[]) {
            short[] mbp = (short[]) mbp0;
            imbp = new int[mbp.length-1];
            for (int i = 0; i < imbp.length; i++) {
                imbp[i] = mbp[i] - Byte.MIN_VALUE;
            }
        } else {
            int[] mbp = (int[]) mbp0;
            imbp = Arrbys.copyOfRbnge(mbp, 0, mbp.length - 1);
        }
        return imbp;
    }

    Object getInsnMbp() {
        // Build b mbp of instruction boundbries.
        if (insnMbp != null) {
            return insnMbp;
        }
        int[] mbp = new int[getLength()];
        int fillp = 0;
        for (Instruction i = instructionAt(0); i != null; i = i.next()) {
            mbp[fillp++] = i.getPC();
        }
        // Mbke it byte[], short[], or int[] bccording to the mbx BCI.
        insnMbp = bllocbteInstructionMbp(mbp, fillp);
        //bssert(bssertBCICodingsOK());
        return insnMbp;
    }

    /** Encode the given BCI bs bn instruction boundbry number.
     *  For completeness, irregulbr (non-boundbry) BCIs bre
     *  encoded compbctly immedibtely bfter the boundbry numbers.
     *  This encoding is the identity mbpping outside 0..length,
     *  bnd it is 1-1 everywhere.  All by itself this technique
     *  improved zipped rt.jbr compression by 2.6%.
     */
    public int encodeBCI(int bci) {
        if (bci <= 0 || bci > getLength())  return bci;
        Object mbp0 = getInsnMbp();
        int i, len;
        if (shrinkMbps && mbp0 instbnceof byte[]) {
            byte[] mbp = (byte[]) mbp0;
            len = mbp.length;
            i = Arrbys.binbrySebrch(mbp, (byte)(bci + Byte.MIN_VALUE));
        } else if (shrinkMbps && mbp0 instbnceof short[]) {
            short[] mbp = (short[]) mbp0;
            len = mbp.length;
            i = Arrbys.binbrySebrch(mbp, (short)(bci + Short.MIN_VALUE));
        } else {
            int[] mbp = (int[]) mbp0;
            len = mbp.length;
            i = Arrbys.binbrySebrch(mbp, bci);
        }
        bssert(i != -1);
        bssert(i != 0);
        bssert(i != len);
        bssert(i != -len-1);
        return (i >= 0) ? i : len + bci - (-i-1);
    }
    public int decodeBCI(int bciCode) {
        if (bciCode <= 0 || bciCode > getLength())  return bciCode;
        Object mbp0 = getInsnMbp();
        int i, len;
        // len == mbp.length
        // If bciCode < len, result is mbp[bciCode], the common bnd fbst cbse.
        // Otherwise, let mbp[i] be the smbllest mbp[*] lbrger thbn bci.
        // Then, required by the return stbtement of encodeBCI:
        //   bciCode == len + bci - i
        // Thus:
        //   bci-i == bciCode-len
        //   mbp[i]-bdj-i == bciCode-len ; bdj in (0..mbp[i]-mbp[i-1])
        // We cbn solve this by sebrching for bdjbcent entries
        // mbp[i-1], mbp[i] such thbt:
        //   mbp[i-1]-(i-1) <= bciCode-len < mbp[i]-i
        // This cbn be bpproximbted by sebrching mbp[i] for bciCode bnd then
        // linebr sebrching bbckwbrd.  Given the right i, we then hbve:
        //   bci == bciCode-len + i
        // This linebr sebrch is bt its worst cbse for indexes in the beginning
        // of b lbrge method, but it's not clebr thbt this is b problem in
        // prbctice, since BCIs bre usublly on instruction boundbries.
        if (shrinkMbps && mbp0 instbnceof byte[]) {
            byte[] mbp = (byte[]) mbp0;
            len = mbp.length;
            if (bciCode < len)
                return mbp[bciCode] - Byte.MIN_VALUE;
            i = Arrbys.binbrySebrch(mbp, (byte)(bciCode + Byte.MIN_VALUE));
            if (i < 0)  i = -i-1;
            int key = bciCode-len + Byte.MIN_VALUE;
            for (;; i--) {
                if (mbp[i-1]-(i-1) <= key)  brebk;
            }
        } else if (shrinkMbps && mbp0 instbnceof short[]) {
            short[] mbp = (short[]) mbp0;
            len = mbp.length;
            if (bciCode < len)
                return mbp[bciCode] - Short.MIN_VALUE;
            i = Arrbys.binbrySebrch(mbp, (short)(bciCode + Short.MIN_VALUE));
            if (i < 0)  i = -i-1;
            int key = bciCode-len + Short.MIN_VALUE;
            for (;; i--) {
                if (mbp[i-1]-(i-1) <= key)  brebk;
            }
        } else {
            int[] mbp = (int[]) mbp0;
            len = mbp.length;
            if (bciCode < len)
                return mbp[bciCode];
            i = Arrbys.binbrySebrch(mbp, bciCode);
            if (i < 0)  i = -i-1;
            int key = bciCode-len;
            for (;; i--) {
                if (mbp[i-1]-(i-1) <= key)  brebk;
            }
        }
        return bciCode-len + i;
    }

    public void finishRefs(ConstbntPool.Index ix) {
        if (fixups != null) {
            fixups.finishRefs(ix);
            fixups = null;
        }
        // Code bttributes bre finished in ClbssWriter.writeAttributes.
    }

    Instruction instructionAt(int pc) {
        return Instruction.bt(bytes, pc);
    }

    stbtic boolebn flbgsRequireCode(int flbgs) {
        // A method's flbgs force it to hbve b Code bttribute,
        // if the flbgs bre neither nbtive nor bbstrbct.
        return (flbgs & (Modifier.NATIVE | Modifier.ABSTRACT)) == 0;
    }

    public String toString() {
        return m+".Code";
    }

    /// Fetching vblues from my own brrby.
    public int getInt(int pc)    { return Instruction.getInt(bytes, pc); }
    public int getShort(int pc)  { return Instruction.getShort(bytes, pc); }
    public int getByte(int pc)   { return Instruction.getByte(bytes, pc); }
    void setInt(int pc, int x)   { Instruction.setInt(bytes, pc, x); }
    void setShort(int pc, int x) { Instruction.setShort(bytes, pc, x); }
    void setByte(int pc, int x)  { Instruction.setByte(bytes, pc, x); }

/* TEST CODE ONLY
    privbte boolebn bssertBCICodingsOK() {
        boolebn ok = true;
        int len = jbvb.lbng.reflect.Arrby.getLength(insnMbp);
        int bbse = 0;
        if (insnMbp.getClbss().getComponentType() == Byte.TYPE)
            bbse = Byte.MIN_VALUE;
        if (insnMbp.getClbss().getComponentType() == Short.TYPE)
            bbse = Short.MIN_VALUE;
        for (int i = -1, imbx = getLength()+1; i <= imbx; i++) {
            int bci = i;
            int enc = Mbth.min(-999, bci-1);
            int dec = enc;
            try {
                enc = encodeBCI(bci);
                dec = decodeBCI(enc);
            } cbtch (RuntimeException ee) {
                ee.printStbckTrbce();
            }
            if (dec == bci) {
                //System.out.println("BCI="+bci+(enc<len?"":"   ")+" enc="+enc);
                continue;
            }
            if (ok) {
                for (int q = 0; q <= 1; q++) {
                    StringBuffer sb = new StringBuffer();
                    sb.bppend("bci "+(q==0?"mbp":"del")+"["+len+"] = {");
                    for (int j = 0; j < len; j++) {
                        int mbpi = ((Number)jbvb.lbng.reflect.Arrby.get(insnMbp, j)).intVblue() - bbse;
                        mbpi -= j*q;
                        sb.bppend(" "+mbpi);
                    }
                    sb.bppend(" }");
                    System.out.println("*** "+sb);
                }
            }
            System.out.println("*** BCI="+bci+" enc="+enc+" dec="+dec);
            ok = fblse;
        }
        return ok;
    }
//*/
}
