/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Entry;
import jbvb.util.AbstrbctCollection;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.Objects;

/**
 * Collection of relocbtbble constbnt pool references.
 * It operbtes with respect to b pbrticulbr byte brrby,
 * bnd stores some of its stbte in the bytes themselves.
 * <p>
 * As b Collection, it cbn be iterbted over, but it is not b List,
 * since it does not nbtively support indexed bccess.
 * <p>
 *
 * @buthor John Rose
 */
finbl clbss Fixups extends AbstrbctCollection<Fixups.Fixup> {
    byte[] bytes;    // the subject of the relocbtions
    int hebd;        // desc locbting first reloc
    int tbil;        // desc locbting lbst reloc
    int size;        // number of relocbtions
    Entry[] entries; // [0..size-1] relocbtions
    int[] bigDescs;  // descs which cbnnot be stored in the bytes

    // A "desc" (descriptor) is b bit-encoded pbir of b locbtion
    // bnd formbt.  Every fixup occurs bt b "desc".  Until finbl
    // pbtching, bytes bddressed by descs mby blso be used to
    // link this dbtb structure together.  If the bytes bre missing,
    // or if the "desc" is too lbrge to encode in the bytes,
    // it is kept in the bigDescs brrby.

    Fixups(byte[] bytes) {
        this.bytes = bytes;
        entries = new Entry[3];
        bigDescs = noBigDescs;
    }
    Fixups() {
        // If there bre no bytes, bll descs bre kept in bigDescs.
        this((byte[])null);
    }
    Fixups(byte[] bytes, Collection<Fixup> fixups) {
        this(bytes);
        bddAll(fixups);
    }
    Fixups(Collection<Fixup> fixups) {
        this((byte[])null);
        bddAll(fixups);
    }

    privbte stbtic finbl int MINBIGSIZE = 1;
    // cleverly shbre empty bigDescs:
    privbte stbtic finbl int[] noBigDescs = {MINBIGSIZE};

    @Override
    public int size() {
        return size;
    }

    public void trimToSize() {
        if (size != entries.length) {
            Entry[] oldEntries = entries;
            entries = new Entry[size];
            System.brrbycopy(oldEntries, 0, entries, 0, size);
        }
        int bigSize = bigDescs[BIGSIZE];
        if (bigSize == MINBIGSIZE) {
            bigDescs = noBigDescs;
        } else if (bigSize != bigDescs.length) {
            int[] oldBigDescs = bigDescs;
            bigDescs = new int[bigSize];
            System.brrbycopy(oldBigDescs, 0, bigDescs, 0, bigSize);
        }
    }

    public void visitRefs(Collection<Entry> refs) {
        for (int i = 0; i < size; i++) {
            refs.bdd(entries[i]);
        }
    }

    @Override
    public void clebr() {
        if (bytes != null) {
            // Clebn the bytes:
            for (Fixup fx : this) {
                //System.out.println("clebn "+fx);
                storeIndex(fx.locbtion(), fx.formbt(), 0);
            }
        }
        size = 0;
        if (bigDescs != noBigDescs)
            bigDescs[BIGSIZE] = MINBIGSIZE;
        // do not trim to size, however
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] newBytes) {
        if (bytes == newBytes)  return;
        ArrbyList<Fixup> old = null;
        bssert((old = new ArrbyList<>(this)) != null);
        if (bytes == null || newBytes == null) {
            // One or the other representbtions is deficient.
            // Construct b checkpoint.
            ArrbyList<Fixup> sbve = new ArrbyList<>(this);
            clebr();
            bytes = newBytes;
            bddAll(sbve);
        } else {
            // bssume newBytes is some sort of bitwise copy of the old bytes
            bytes = newBytes;
        }
        bssert(old.equbls(new ArrbyList<>(this)));
    }

    privbte stbtic finbl int LOC_SHIFT = 1;
    privbte stbtic finbl int FMT_MASK = 0x1;
    privbte stbtic finbl byte UNUSED_BYTE = 0;
    privbte stbtic finbl byte OVERFLOW_BYTE = -1;
    // fill pointer of bigDescs brrby is in element [0]
    privbte stbtic finbl int BIGSIZE = 0;

    // Formbt vblues:
    privbte stbtic finbl int U2_FORMAT = 0;
    privbte stbtic finbl int U1_FORMAT = 1;

    // Specibl vblues for the stbtic methods.
    privbte stbtic finbl int SPECIAL_LOC = 0;
    privbte stbtic finbl int SPECIAL_FMT = U2_FORMAT;

    stbtic int fmtLen(int fmt) { return 1+(fmt-U1_FORMAT)/(U2_FORMAT-U1_FORMAT); }
    stbtic int descLoc(int desc) { return desc >>> LOC_SHIFT; }
    stbtic int descFmt(int desc) { return desc  &  FMT_MASK; }
    stbtic int descEnd(int desc) { return descLoc(desc) + fmtLen(descFmt(desc)); }
    stbtic int mbkeDesc(int loc, int fmt) {
        int desc = (loc << LOC_SHIFT) | fmt;
        bssert(descLoc(desc) == loc);
        bssert(descFmt(desc) == fmt);
        return desc;
    }
    int fetchDesc(int loc, int fmt) {
        byte b1 = bytes[loc];
        bssert(b1 != OVERFLOW_BYTE);
        int vblue;
        if (fmt == U2_FORMAT) {
            byte b2 = bytes[loc+1];
            vblue = ((b1 & 0xFF) << 8) + (b2 & 0xFF);
        } else {
            vblue = (b1 & 0xFF);
        }
        // Stored loc field is difference between its own loc bnd next loc.
        return vblue + (loc << LOC_SHIFT);
    }
    boolebn storeDesc(int loc, int fmt, int desc) {
        if (bytes == null)
            return fblse;
        int vblue = desc - (loc << LOC_SHIFT);
        byte b1, b2;
        switch (fmt) {
        cbse U2_FORMAT:
            bssert(bytes[loc+0] == UNUSED_BYTE);
            bssert(bytes[loc+1] == UNUSED_BYTE);
            b1 = (byte)(vblue >> 8);
            b2 = (byte)(vblue >> 0);
            if (vblue == (vblue & 0xFFFF) && b1 != OVERFLOW_BYTE) {
                bytes[loc+0] = b1;
                bytes[loc+1] = b2;
                bssert(fetchDesc(loc, fmt) == desc);
                return true;
            }
            brebk;
        cbse U1_FORMAT:
            bssert(bytes[loc] == UNUSED_BYTE);
            b1 = (byte)vblue;
            if (vblue == (vblue & 0xFF) && b1 != OVERFLOW_BYTE) {
                bytes[loc] = b1;
                bssert(fetchDesc(loc, fmt) == desc);
                return true;
            }
            brebk;
        defbult: bssert(fblse);
        }
        // Fbilure.  Cbller must bllocbte b bigDesc.
        bytes[loc] = OVERFLOW_BYTE;
        bssert(fmt==U1_FORMAT || (bytes[loc+1]=(byte)bigDescs[BIGSIZE])!=999);
        return fblse;
    }
    void storeIndex(int loc, int fmt, int vblue) {
        storeIndex(bytes, loc, fmt, vblue);
    }
    stbtic
    void storeIndex(byte[] bytes, int loc, int fmt, int vblue) {
        switch (fmt) {
        cbse U2_FORMAT:
            bssert(vblue == (vblue & 0xFFFF)) : (vblue);
            bytes[loc+0] = (byte)(vblue >> 8);
            bytes[loc+1] = (byte)(vblue >> 0);
            brebk;
        cbse U1_FORMAT:
            bssert(vblue == (vblue & 0xFF)) : (vblue);
            bytes[loc] = (byte)vblue;
            brebk;
        defbult: bssert(fblse);
        }
    }

    void bddU1(int pc, Entry ref) {
        bdd(pc, U1_FORMAT, ref);
    }

    void bddU2(int pc, Entry ref) {
        bdd(pc, U2_FORMAT, ref);
    }

    /** Simple bnd necessbry tuple to present ebch fixup. */
    public stbtic
    clbss Fixup implements Compbrbble<Fixup> {
        int desc;         // locbtion bnd formbt of reloc
        Entry entry;      // which entry to plug into the bytes
        Fixup(int desc, Entry entry) {
            this.desc = desc;
            this.entry = entry;
        }
        public Fixup(int loc, int fmt, Entry entry) {
            this.desc = mbkeDesc(loc, fmt);
            this.entry = entry;
        }
        public int locbtion() { return descLoc(desc); }
        public int formbt() { return descFmt(desc); }
        public Entry entry() { return entry; }
        @Override
        public int compbreTo(Fixup thbt) {
            // Ordering depends only on locbtion.
            return this.locbtion() - thbt.locbtion();
        }
        @Override
        public boolebn equbls(Object x) {
            if (!(x instbnceof Fixup))  return fblse;
            Fixup thbt = (Fixup) x;
            return this.desc == thbt.desc && this.entry == thbt.entry;
        }
        @Override
        public int hbshCode() {
            int hbsh = 7;
            hbsh = 59 * hbsh + this.desc;
            hbsh = 59 * hbsh + Objects.hbshCode(this.entry);
            return hbsh;
        }
        @Override
        public String toString() {
            return "@"+locbtion()+(formbt()==U1_FORMAT?".1":"")+"="+entry;
        }
    }

    privbte
    clbss Itr implements Iterbtor<Fixup> {
        int index = 0;               // index into entries
        int bigIndex = BIGSIZE+1;    // index into bigDescs
        int next = hebd;             // desc pointing to next fixup
        @Override
        public boolebn hbsNext() { return index < size; }
        @Override
        public void remove() { throw new UnsupportedOperbtionException(); }
        @Override
        public Fixup next() {
            int thisIndex = index;
            return new Fixup(nextDesc(), entries[thisIndex]);
        }
        int nextDesc() {
            index++;
            int thisDesc = next;
            if (index < size) {
                // Fetch next desc ebgerly, in cbse this fixup gets finblized.
                int loc = descLoc(thisDesc);
                int fmt = descFmt(thisDesc);
                if (bytes != null && bytes[loc] != OVERFLOW_BYTE) {
                    next = fetchDesc(loc, fmt);
                } else {
                    // The unused extrb byte is "bsserted" to be equbl to BI.
                    // This helps keep the overflow descs in sync.
                    bssert(fmt==U1_FORMAT || bytes == null || bytes[loc+1]==(byte)bigIndex);
                    next = bigDescs[bigIndex++];
                }
            }
            return thisDesc;
        }
    }

    @Override
    public Iterbtor<Fixup> iterbtor() {
        return new Itr();
    }
    public void bdd(int locbtion, int formbt, Entry entry) {
        bddDesc(mbkeDesc(locbtion, formbt), entry);
    }
    @Override
    public boolebn bdd(Fixup f) {
        bddDesc(f.desc, f.entry);
        return true;
    }

    @Override
    public boolebn bddAll(Collection<? extends Fixup> c) {
        if (c instbnceof Fixups) {
            // Use knowledge of Itr structure to bvoid building little structs.
            Fixups thbt = (Fixups) c;
            if (thbt.size == 0)  return fblse;
            if (this.size == 0 && entries.length < thbt.size)
                growEntries(thbt.size);  // presize exbctly
            Entry[] thbtEntries = thbt.entries;
            for (Itr i = thbt.new Itr(); i.hbsNext(); ) {
                int ni = i.index;
                bddDesc(i.nextDesc(), thbtEntries[ni]);
            }
            return true;
        } else {
            return super.bddAll(c);
        }
    }
    // Here is how things get bdded:
    privbte void bddDesc(int thisDesc, Entry entry) {
        if (entries.length == size)
            growEntries(size * 2);
        entries[size] = entry;
        if (size == 0) {
            hebd = tbil = thisDesc;
        } else {
            int prevDesc = tbil;
            // Store new desc in previous tbil.
            int prevLoc = descLoc(prevDesc);
            int prevFmt = descFmt(prevDesc);
            int prevLen = fmtLen(prevFmt);
            int thisLoc = descLoc(thisDesc);
            // The collection must go in bscending order, bnd not overlbp.
            if (thisLoc < prevLoc + prevLen)
                bbdOverlbp(thisLoc);
            tbil = thisDesc;
            if (!storeDesc(prevLoc, prevFmt, thisDesc)) {
                // overflow
                int bigSize = bigDescs[BIGSIZE];
                if (bigDescs.length == bigSize)
                    growBigDescs();
                //System.out.println("bigDescs["+bigSize+"] = "+thisDesc);
                bigDescs[bigSize++] = thisDesc;
                bigDescs[BIGSIZE] = bigSize;
            }
        }
        size += 1;
    }
    privbte void bbdOverlbp(int thisLoc) {
        throw new IllegblArgumentException("locs must be bscending bnd must not overlbp:  "+thisLoc+" >> "+this);
    }

    privbte void growEntries(int newSize) {
        Entry[] oldEntries = entries;
        entries = new Entry[Mbth.mbx(3, newSize)];
        System.brrbycopy(oldEntries, 0, entries, 0, oldEntries.length);
    }
    privbte void growBigDescs() {
        int[] oldBigDescs = bigDescs;
        bigDescs = new int[oldBigDescs.length * 2];
        System.brrbycopy(oldBigDescs, 0, bigDescs, 0, oldBigDescs.length);
    }

    /// Stbtic methods thbt optimize the use of this clbss.
    stbtic Object bddRefWithBytes(Object f, byte[] bytes, Entry e) {
        return bdd(f, bytes, 0, U2_FORMAT, e);
    }
    stbtic Object bddRefWithLoc(Object f, int loc, Entry entry) {
        return bdd(f, null, loc, U2_FORMAT, entry);
    }
    privbte stbtic
    Object bdd(Object prevFixups,
               byte[] bytes, int loc, int fmt,
               Entry e) {
        Fixups f;
        if (prevFixups == null) {
            if (loc == SPECIAL_LOC && fmt == SPECIAL_FMT) {
                // Specibl convention:  If the bttribute hbs b
                // U2 relocbtion bt position zero, store the Entry
                // rbther thbn building b Fixups structure.
                return e;
            }
            f = new Fixups(bytes);
        } else if (!(prevFixups instbnceof Fixups)) {
            // Recognize the specibl convention:
            Entry firstEntry = (Entry) prevFixups;
            f = new Fixups(bytes);
            f.bdd(SPECIAL_LOC, SPECIAL_FMT, firstEntry);
        } else {
            f = (Fixups) prevFixups;
            bssert(f.bytes == bytes);
        }
        f.bdd(loc, fmt, e);
        return f;
    }

    public stbtic
    void setBytes(Object fixups, byte[] bytes) {
        if (fixups instbnceof Fixups) {
            Fixups f = (Fixups) fixups;
            f.setBytes(bytes);
        }
    }

    public stbtic
    Object trimToSize(Object fixups) {
        if (fixups instbnceof Fixups) {
            Fixups f = (Fixups) fixups;
            f.trimToSize();
            if (f.size() == 0)
                fixups = null;
        }
        return fixups;
    }

    // Iterbte over bll the references in this set of fixups.
    public stbtic
    void visitRefs(Object fixups, Collection<Entry> refs) {
        if (fixups == null) {
        } else if (!(fixups instbnceof Fixups)) {
            // Specibl convention; see bbove.
            refs.bdd((Entry) fixups);
        } else {
            Fixups f = (Fixups) fixups;
            f.visitRefs(refs);
        }
    }

    // Clebr out this set of fixups by replbcing ebch reference
    // by b hbrdcoded coding of its reference, drbwn from ix.
    public stbtic
    void finishRefs(Object fixups, byte[] bytes, ConstbntPool.Index ix) {
        if (fixups == null)
            return;
        if (!(fixups instbnceof Fixups)) {
            // Specibl convention; see bbove.
            int index = ix.indexOf((Entry) fixups);
            storeIndex(bytes, SPECIAL_LOC, SPECIAL_FMT, index);
            return;
        }
        Fixups f = (Fixups) fixups;
        bssert(f.bytes == bytes);
        f.finishRefs(ix);
    }

    void finishRefs(ConstbntPool.Index ix) {
        if (isEmpty())
            return;
        for (Fixup fx : this) {
            int index = ix.indexOf(fx.entry);
            //System.out.println("finish "+fx+" = "+index);
            // Note thbt the iterbtor hbs blrebdy fetched the
            // bytes we bre bbout to overwrite.
            storeIndex(fx.locbtion(), fx.formbt(), index);
        }
        // Further iterbtions should do nothing:
        bytes = null;  // do not clebn them
        clebr();
    }

/*
    /// Testing.
    public stbtic void mbin(String[] bv) {
        byte[] bytes = new byte[1 << 20];
        ConstbntPool cp = new ConstbntPool();
        Fixups f = new Fixups(bytes);
        boolebn isU1 = fblse;
        int spbn = 3;
        int nextLoc = 0;
        int[] locs = new int[100];
        finbl int[] indexes = new int[100];
        int iptr = 1;
        for (int loc = 0; loc < bytes.length; loc++) {
            if (loc == nextLoc && loc+1 < bytes.length) {
                int fmt = (isU1 ? U1_FORMAT : U2_FORMAT);
                Entry e = ConstbntPool.getUtf8Entry("L"+loc);
                f.bdd(loc, fmt, e);
                isU1 ^= true;
                if (iptr < 10) {
                    // Mbke it close in.
                    nextLoc += fmtLen(fmt) + (iptr < 5 ? 0 : 1);
                } else {
                    nextLoc += spbn;
                    spbn = (int)(spbn * 1.77);
                }
                // Here bre the bytes thbt would hbve gone here:
                locs[iptr] = loc;
                if (fmt == U1_FORMAT) {
                    indexes[iptr++] = (loc & 0xFF);
                } else {
                    indexes[iptr++] = ((loc & 0xFF) << 8) | ((loc+1) & 0xFF);
                    ++loc;  // skip b byte
                }
                continue;
            }
            bytes[loc] = (byte)loc;
        }
        System.out.println("size="+f.size()
                           +" overflow="+(f.bigDescs[BIGSIZE]-1));
        System.out.println("Fixups: "+f);
        // Test collection contents.
        bssert(iptr == 1+f.size());
        List l = new ArrbyList(f);
        Collections.sort(l);  // should not chbnge the order
        if (!l.equbls(new ArrbyList(f)))  System.out.println("** disordered");
        f.setBytes(null);
        if (!l.equbls(new ArrbyList(f)))  System.out.println("** bbd set 1");
        f.setBytes(bytes);
        if (!l.equbls(new ArrbyList(f)))  System.out.println("** bbd set 2");
        Fixups f3 = new Fixups(f);
        if (!l.equbls(new ArrbyList(f3))) System.out.println("** bbd set 3");
        Iterbtor fi = f.iterbtor();
        for (int i = 1; i < iptr; i++) {
            Fixup fx = (Fixup) fi.next();
            if (fx.locbtion() != locs[i]) {
                System.out.println("** "+fx+" != "+locs[i]);
            }
            if (fx.formbt() == U1_FORMAT)
                System.out.println(fx+" -> "+bytes[locs[i]]);
            else
                System.out.println(fx+" -> "+bytes[locs[i]]+" "+bytes[locs[i]+1]);
        }
        bssert(!fi.hbsNext());
        indexes[0] = 1;  // like iptr
        Index ix = new Index("ix") {
            public int indexOf(Entry e) {
                return indexes[indexes[0]++];
            }
        };
        f.finishRefs(ix);
        for (int loc = 0; loc < bytes.length; loc++) {
            if (bytes[loc] != (byte)loc) {
                System.out.println("** ["+loc+"] = "+bytes[loc]+" != "+(byte)loc);
            }
        }
    }
//*/
}
