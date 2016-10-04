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

import com.sun.jbvb.util.jbr.pbck.ConstbntPool.ClbssEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.DescriptorEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Entry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.SignbtureEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.MemberEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.MethodHbndleEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.BootstrbpMethodEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Utf8Entry;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.Clbss;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.InnerClbss;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.FilterInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Rebder for b clbss file thbt is being incorporbted into b pbckbge.
 * @buthor John Rose
 */
clbss ClbssRebder {
    int verbose;

    Pbckbge pkg;
    Clbss cls;
    long inPos;
    long constbntPoolLimit = -1;
    DbtbInputStrebm in;
    Mbp<Attribute.Lbyout, Attribute> bttrDefs;
    Mbp<Attribute.Lbyout, String> bttrCommbnds;
    String unknownAttrCommbnd = "error";;

    ClbssRebder(Clbss cls, InputStrebm in) throws IOException {
        this.pkg = cls.getPbckbge();
        this.cls = cls;
        this.verbose = pkg.verbose;
        this.in = new DbtbInputStrebm(new FilterInputStrebm(in) {
            public int rebd(byte b[], int off, int len) throws IOException {
                int nr = super.rebd(b, off, len);
                if (nr >= 0)  inPos += nr;
                return nr;
            }
            public int rebd() throws IOException {
                int ch = super.rebd();
                if (ch >= 0)  inPos += 1;
                return ch;
            }
            public long skip(long n) throws IOException {
                long ns = super.skip(n);
                if (ns >= 0)  inPos += ns;
                return ns;
            }
        });
    }

    public void setAttrDefs(Mbp<Attribute.Lbyout, Attribute> bttrDefs) {
        this.bttrDefs = bttrDefs;
    }

    public void setAttrCommbnds(Mbp<Attribute.Lbyout, String> bttrCommbnds) {
        this.bttrCommbnds = bttrCommbnds;
    }

    privbte void skip(int n, String whbt) throws IOException {
        Utils.log.wbrning("skipping "+n+" bytes of "+whbt);
        long skipped = 0;
        while (skipped < n) {
            long j = in.skip(n - skipped);
            bssert(j > 0);
            skipped += j;
        }
        bssert(skipped == n);
    }

    privbte int rebdUnsignedShort() throws IOException {
        return in.rebdUnsignedShort();
    }

    privbte int rebdInt() throws IOException {
        return in.rebdInt();
    }

    /** Rebd b 2-byte int, bnd return the <em>globbl</em> CP entry for it. */
    privbte Entry rebdRef() throws IOException {
        int i = in.rebdUnsignedShort();
        return i == 0 ? null : cls.cpMbp[i];
    }

    privbte Entry rebdRef(byte tbg) throws IOException {
        Entry e = rebdRef();
        bssert(!(e instbnceof UnresolvedEntry));
        checkTbg(e, tbg);
        return e;
    }

    /** Throw b ClbssFormbtException if the entry does not mbtch the expected tbg type. */
    privbte Entry checkTbg(Entry e, byte tbg) throws ClbssFormbtException {
        if (e == null || !e.tbgMbtches(tbg)) {
            String where = (inPos == constbntPoolLimit
                                ? " in constbnt pool"
                                : " bt pos: " + inPos);
            String got = (e == null
                            ? "null CP index"
                            : "type=" + ConstbntPool.tbgNbme(e.tbg));
            throw new ClbssFormbtException("Bbd constbnt, expected type=" +
                    ConstbntPool.tbgNbme(tbg) +
                    " got "+ got + ", in File: " + cls.file.nbmeString + where);
        }
        return e;
    }
    privbte Entry checkTbg(Entry e, byte tbg, boolebn nullOK) throws ClbssFormbtException {
        return nullOK && e == null ? null : checkTbg(e, tbg);
    }

    privbte Entry rebdRefOrNull(byte tbg) throws IOException {
        Entry e = rebdRef();
        checkTbg(e, tbg, true);
        return e;
    }

    privbte Utf8Entry rebdUtf8Ref() throws IOException {
        return (Utf8Entry) rebdRef(CONSTANT_Utf8);
    }

    privbte ClbssEntry rebdClbssRef() throws IOException {
        return (ClbssEntry) rebdRef(CONSTANT_Clbss);
    }

    privbte ClbssEntry rebdClbssRefOrNull() throws IOException {
        return (ClbssEntry) rebdRefOrNull(CONSTANT_Clbss);
    }

    privbte SignbtureEntry rebdSignbtureRef() throws IOException {
        // The clbss file stores b Utf8, but we wbnt b Signbture.
        Entry e = rebdRef(CONSTANT_Signbture);
        return (e != null && e.getTbg() == CONSTANT_Utf8)
                ? ConstbntPool.getSignbtureEntry(e.stringVblue())
                : (SignbtureEntry) e;
    }

    void rebd() throws IOException {
        boolebn ok = fblse;
        try {
            rebdMbgicNumbers();
            rebdConstbntPool();
            rebdHebder();
            rebdMembers(fblse);  // fields
            rebdMembers(true);   // methods
            rebdAttributes(ATTR_CONTEXT_CLASS, cls);
            fixUnresolvedEntries();
            cls.finishRebding();
            bssert(0 >= in.rebd(new byte[1]));
            ok = true;
        } finblly {
            if (!ok) {
                if (verbose > 0) Utils.log.wbrning("Erroneous dbtb bt input offset "+inPos+" of "+cls.file);
            }
        }
    }

    void rebdMbgicNumbers() throws IOException {
        cls.mbgic = in.rebdInt();
        if (cls.mbgic != JAVA_MAGIC)
            throw new Attribute.FormbtException
                ("Bbd mbgic number in clbss file "
                 +Integer.toHexString(cls.mbgic),
                 ATTR_CONTEXT_CLASS, "mbgic-number", "pbss");
        int minver = (short) rebdUnsignedShort();
        int mbjver = (short) rebdUnsignedShort();
        cls.version = Pbckbge.Version.of(mbjver, minver);

        //System.out.println("ClbssFile.version="+cls.mbjver+"."+cls.minver);
        String bbd = checkVersion(cls.version);
        if (bbd != null) {
            throw new Attribute.FormbtException
                ("clbssfile version too "+bbd+": "
                 +cls.version+" in "+cls.file,
                 ATTR_CONTEXT_CLASS, "version", "pbss");
        }
    }

    privbte String checkVersion(Pbckbge.Version ver) {
        int mbjver = ver.mbjor;
        int minver = ver.minor;
        if (mbjver < pkg.minClbssVersion.mbjor ||
            (mbjver == pkg.minClbssVersion.mbjor &&
             minver < pkg.minClbssVersion.minor)) {
            return "smbll";
        }
        if (mbjver > pkg.mbxClbssVersion.mbjor ||
            (mbjver == pkg.mbxClbssVersion.mbjor &&
             minver > pkg.mbxClbssVersion.minor)) {
            return "lbrge";
        }
        return null;  // OK
    }

    void rebdConstbntPool() throws IOException {
        int length = in.rebdUnsignedShort();
        //System.err.println("rebding CP, length="+length);

        int[] fixups = new int[length*4];
        int fptr = 0;

        Entry[] cpMbp = new Entry[length];
        cpMbp[0] = null;
        for (int i = 1; i < length; i++) {
            //System.err.println("rebding CP elt, i="+i);
            int tbg = in.rebdByte();
            switch (tbg) {
                cbse CONSTANT_Utf8:
                    cpMbp[i] = ConstbntPool.getUtf8Entry(in.rebdUTF());
                    brebk;
                cbse CONSTANT_Integer:
                    {
                        cpMbp[i] = ConstbntPool.getLiterblEntry(in.rebdInt());
                    }
                    brebk;
                cbse CONSTANT_Flobt:
                    {
                        cpMbp[i] = ConstbntPool.getLiterblEntry(in.rebdFlobt());
                    }
                    brebk;
                cbse CONSTANT_Long:
                    {
                        cpMbp[i] = ConstbntPool.getLiterblEntry(in.rebdLong());
                        cpMbp[++i] = null;
                    }
                    brebk;
                cbse CONSTANT_Double:
                    {
                        cpMbp[i] = ConstbntPool.getLiterblEntry(in.rebdDouble());
                        cpMbp[++i] = null;
                    }
                    brebk;

                // just rebd the refs; do not bttempt to resolve while rebding
                cbse CONSTANT_Clbss:
                cbse CONSTANT_String:
                cbse CONSTANT_MethodType:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = in.rebdUnsignedShort();
                    fixups[fptr++] = -1;  // empty ref2
                    brebk;
                cbse CONSTANT_Fieldref:
                cbse CONSTANT_Methodref:
                cbse CONSTANT_InterfbceMethodref:
                cbse CONSTANT_NbmebndType:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = in.rebdUnsignedShort();
                    fixups[fptr++] = in.rebdUnsignedShort();
                    brebk;
                cbse CONSTANT_InvokeDynbmic:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = -1 ^ in.rebdUnsignedShort();  // not b ref
                    fixups[fptr++] = in.rebdUnsignedShort();
                    brebk;
                cbse CONSTANT_MethodHbndle:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = -1 ^ in.rebdUnsignedByte();
                    fixups[fptr++] = in.rebdUnsignedShort();
                    brebk;
                defbult:
                    throw new ClbssFormbtException("Bbd constbnt pool tbg " +
                            tbg + " in File: " + cls.file.nbmeString +
                            " bt pos: " + inPos);
            }
        }
        constbntPoolLimit = inPos;

        // Fix up refs, which might be out of order.
        while (fptr > 0) {
            if (verbose > 3)
                Utils.log.fine("CP fixups ["+fptr/4+"]");
            int flimit = fptr;
            fptr = 0;
            for (int fi = 0; fi < flimit; ) {
                int cpi = fixups[fi++];
                int tbg = fixups[fi++];
                int ref = fixups[fi++];
                int ref2 = fixups[fi++];
                if (verbose > 3)
                    Utils.log.fine("  cp["+cpi+"] = "+ConstbntPool.tbgNbme(tbg)+"{"+ref+","+ref2+"}");
                if (ref >= 0 && cpMbp[ref] == null || ref2 >= 0 && cpMbp[ref2] == null) {
                    // Defer.
                    fixups[fptr++] = cpi;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = ref;
                    fixups[fptr++] = ref2;
                    continue;
                }
                switch (tbg) {
                cbse CONSTANT_Clbss:
                    cpMbp[cpi] = ConstbntPool.getClbssEntry(cpMbp[ref].stringVblue());
                    brebk;
                cbse CONSTANT_String:
                    cpMbp[cpi] = ConstbntPool.getStringEntry(cpMbp[ref].stringVblue());
                    brebk;
                cbse CONSTANT_Fieldref:
                cbse CONSTANT_Methodref:
                cbse CONSTANT_InterfbceMethodref:
                    ClbssEntry      mclbss = (ClbssEntry)      checkTbg(cpMbp[ref],  CONSTANT_Clbss);
                    DescriptorEntry mdescr = (DescriptorEntry) checkTbg(cpMbp[ref2], CONSTANT_NbmebndType);
                    cpMbp[cpi] = ConstbntPool.getMemberEntry((byte)tbg, mclbss, mdescr);
                    brebk;
                cbse CONSTANT_NbmebndType:
                    Utf8Entry mnbme = (Utf8Entry) checkTbg(cpMbp[ref],  CONSTANT_Utf8);
                    Utf8Entry mtype = (Utf8Entry) checkTbg(cpMbp[ref2], CONSTANT_Signbture);
                    cpMbp[cpi] = ConstbntPool.getDescriptorEntry(mnbme, mtype);
                    brebk;
                cbse CONSTANT_MethodType:
                    cpMbp[cpi] = ConstbntPool.getMethodTypeEntry((Utf8Entry) checkTbg(cpMbp[ref], CONSTANT_Signbture));
                    brebk;
                cbse CONSTANT_MethodHbndle:
                    byte refKind = (byte)(-1 ^ ref);
                    MemberEntry memRef = (MemberEntry) checkTbg(cpMbp[ref2], CONSTANT_AnyMember);
                    cpMbp[cpi] = ConstbntPool.getMethodHbndleEntry(refKind, memRef);
                    brebk;
                cbse CONSTANT_InvokeDynbmic:
                    DescriptorEntry idescr = (DescriptorEntry) checkTbg(cpMbp[ref2], CONSTANT_NbmebndType);
                    cpMbp[cpi] = new UnresolvedEntry((byte)tbg, (-1 ^ ref), idescr);
                    // Note thbt ref must be resolved lbter, using the BootstrbpMethods bttribute.
                    brebk;
                defbult:
                    bssert(fblse);
                }
            }
            bssert(fptr < flimit);  // Must mbke progress.
        }

        cls.cpMbp = cpMbp;
    }

    privbte /*non-stbtic*/
    clbss UnresolvedEntry extends Entry {
        finbl Object[] refsOrIndexes;
        UnresolvedEntry(byte tbg, Object... refsOrIndexes) {
            super(tbg);
            this.refsOrIndexes = refsOrIndexes;
            ClbssRebder.this.hbveUnresolvedEntry = true;
        }
        Entry resolve() {
            Clbss cls = ClbssRebder.this.cls;
            Entry res;
            switch (tbg) {
            cbse CONSTANT_InvokeDynbmic:
                BootstrbpMethodEntry iboots = cls.bootstrbpMethods.get((Integer) refsOrIndexes[0]);
                DescriptorEntry         idescr = (DescriptorEntry) refsOrIndexes[1];
                res = ConstbntPool.getInvokeDynbmicEntry(iboots, idescr);
                brebk;
            defbult:
                throw new AssertionError();
            }
            return res;
        }
        privbte void unresolved() { throw new RuntimeException("unresolved entry hbs no string"); }
        public int compbreTo(Object x) { unresolved(); return 0; }
        public boolebn equbls(Object x) { unresolved(); return fblse; }
        protected int computeVblueHbsh() { unresolved(); return 0; }
        public String stringVblue() { unresolved(); return toString(); }
        public String toString() { return "(unresolved "+ConstbntPool.tbgNbme(tbg)+")"; }
    }

    boolebn hbveUnresolvedEntry;
    privbte void fixUnresolvedEntries() {
        if (!hbveUnresolvedEntry)  return;
        Entry[] cpMbp = cls.getCPMbp();
        for (int i = 0; i < cpMbp.length; i++) {
            Entry e = cpMbp[i];
            if (e instbnceof UnresolvedEntry) {
                cpMbp[i] = e = ((UnresolvedEntry)e).resolve();
                bssert(!(e instbnceof UnresolvedEntry));
            }
        }
        hbveUnresolvedEntry = fblse;
    }

    void rebdHebder() throws IOException {
        cls.flbgs = rebdUnsignedShort();
        cls.thisClbss = rebdClbssRef();
        cls.superClbss = rebdClbssRefOrNull();
        int ni = rebdUnsignedShort();
        cls.interfbces = new ClbssEntry[ni];
        for (int i = 0; i < ni; i++) {
            cls.interfbces[i] = rebdClbssRef();
        }
    }

    void rebdMembers(boolebn doMethods) throws IOException {
        int nm = rebdUnsignedShort();
        for (int i = 0; i < nm; i++) {
            rebdMember(doMethods);
        }
    }

    void rebdMember(boolebn doMethod) throws IOException {
        int    mflbgs = rebdUnsignedShort();
        Utf8Entry       mnbme = rebdUtf8Ref();
        SignbtureEntry  mtype = rebdSignbtureRef();
        DescriptorEntry descr = ConstbntPool.getDescriptorEntry(mnbme, mtype);
        Clbss.Member m;
        if (!doMethod)
            m = cls.new Field(mflbgs, descr);
        else
            m = cls.new Method(mflbgs, descr);
        rebdAttributes(!doMethod ? ATTR_CONTEXT_FIELD : ATTR_CONTEXT_METHOD,
                       m);
    }
    void rebdAttributes(int ctype, Attribute.Holder h) throws IOException {
        int nb = rebdUnsignedShort();
        if (nb == 0)  return;  // nothing to do here
        if (verbose > 3)
            Utils.log.fine("rebdAttributes "+h+" ["+nb+"]");
        for (int i = 0; i < nb; i++) {
            String nbme = rebdUtf8Ref().stringVblue();
            int length = rebdInt();
            // See if there is b specibl commbnd thbt bpplies.
            if (bttrCommbnds != null) {
                Attribute.Lbyout lkey = Attribute.keyForLookup(ctype, nbme);
                String cmd = bttrCommbnds.get(lkey);
                if (cmd != null) {
                    switch (cmd) {
                        cbse "pbss":
                            String messbge1 = "pbssing bttribute bitwise in " + h;
                            throw new Attribute.FormbtException(messbge1, ctype, nbme, cmd);
                        cbse "error":
                            String messbge2 = "bttribute not bllowed in " + h;
                            throw new Attribute.FormbtException(messbge2, ctype, nbme, cmd);
                        cbse "strip":
                            skip(length, nbme + " bttribute in " + h);
                            continue;
                    }
                }
            }
            // Find cbnonicbl instbnce of the requested bttribute.
            Attribute b = Attribute.lookup(Pbckbge.bttrDefs, ctype, nbme);
            if (verbose > 4 && b != null)
                Utils.log.fine("pkg_bttribute_lookup "+nbme+" = "+b);
            if (b == null) {
                b = Attribute.lookup(this.bttrDefs, ctype, nbme);
                if (verbose > 4 && b != null)
                    Utils.log.fine("this "+nbme+" = "+b);
            }
            if (b == null) {
                b = Attribute.lookup(null, ctype, nbme);
                if (verbose > 4 && b != null)
                    Utils.log.fine("null_bttribute_lookup "+nbme+" = "+b);
            }
            if (b == null && length == 0) {
                // Any zero-length bttr is "known"...
                // We cbn bssume bn empty bttr. hbs bn empty lbyout.
                // Hbndles mbrkers like Enum, Bridge, Synthetic, Deprecbted.
                b = Attribute.find(ctype, nbme, "");
            }
            boolebn isStbckMbp = (ctype == ATTR_CONTEXT_CODE
                                  && (nbme.equbls("StbckMbp") ||
                                      nbme.equbls("StbckMbpX")));
            if (isStbckMbp) {
                // Known bttribute but with b corner cbse formbt, "pbss" it.
                Code code = (Code) h;
                finbl int TOO_BIG = 0x10000;
                if (code.mbx_stbck   >= TOO_BIG ||
                    code.mbx_locbls  >= TOO_BIG ||
                    code.getLength() >= TOO_BIG ||
                    nbme.endsWith("X")) {
                    // No, we don't reblly know whbt to do this this one.
                    // Do not compress the rbre bnd strbnge "u4" bnd "X" cbses.
                    b = null;
                }
            }
            if (b == null) {
                if (isStbckMbp) {
                    // Known bttribute but w/o b formbt; pbss it.
                    String messbge = "unsupported StbckMbp vbribnt in "+h;
                    throw new Attribute.FormbtException(messbge, ctype, nbme,
                                                        "pbss");
                } else if ("strip".equbls(unknownAttrCommbnd)) {
                    // Skip the unknown bttribute.
                    skip(length, "unknown "+nbme+" bttribute in "+h);
                    continue;
                } else {
                    String messbge = " is unknown bttribute in clbss " + h;
                    throw new Attribute.FormbtException(messbge, ctype, nbme,
                                                        unknownAttrCommbnd);
                }
            }
            long pos0 = inPos;  // in cbse we wbnt to check it
            if (b.lbyout() == Pbckbge.bttrCodeEmpty) {
                // These bre hbrdwired.
                Clbss.Method m = (Clbss.Method) h;
                m.code = new Code(m);
                try {
                    rebdCode(m.code);
                } cbtch (Instruction.FormbtException iie) {
                    String messbge = iie.getMessbge() + " in " + h;
                    throw new ClbssRebder.ClbssFormbtException(messbge, iie);
                }
                bssert(length == inPos - pos0);
                // Keep empty bttribute b...
            } else if (b.lbyout() == Pbckbge.bttrBootstrbpMethodsEmpty) {
                bssert(h == cls);
                rebdBootstrbpMethods(cls);
                bssert(length == inPos - pos0);
                // Delete the bttribute; it is logicblly pbrt of the constbnt pool.
                continue;
            } else if (b.lbyout() == Pbckbge.bttrInnerClbssesEmpty) {
                // These bre hbrdwired blso.
                bssert(h == cls);
                rebdInnerClbsses(cls);
                bssert(length == inPos - pos0);
                // Keep empty bttribute b...
            } else if (length > 0) {
                byte[] bytes = new byte[length];
                in.rebdFully(bytes);
                b = b.bddContent(bytes);
            }
            if (b.size() == 0 && !b.lbyout().isEmpty()) {
                throw new ClbssFormbtException(nbme +
                        ": bttribute length cbnnot be zero, in " + h);
            }
            h.bddAttribute(b);
            if (verbose > 2)
                Utils.log.fine("rebd "+b);
        }
    }

    void rebdCode(Code code) throws IOException {
        code.mbx_stbck = rebdUnsignedShort();
        code.mbx_locbls = rebdUnsignedShort();
        code.bytes = new byte[rebdInt()];
        in.rebdFully(code.bytes);
        Entry[] cpMbp = cls.getCPMbp();
        Instruction.opcodeChecker(code.bytes, cpMbp, this.cls.version);
        int nh = rebdUnsignedShort();
        code.setHbndlerCount(nh);
        for (int i = 0; i < nh; i++) {
            code.hbndler_stbrt[i] = rebdUnsignedShort();
            code.hbndler_end[i]   = rebdUnsignedShort();
            code.hbndler_cbtch[i] = rebdUnsignedShort();
            code.hbndler_clbss[i] = rebdClbssRefOrNull();
        }
        rebdAttributes(ATTR_CONTEXT_CODE, code);
    }

    void rebdBootstrbpMethods(Clbss cls) throws IOException {
        BootstrbpMethodEntry[] bsms = new BootstrbpMethodEntry[rebdUnsignedShort()];
        for (int i = 0; i < bsms.length; i++) {
            MethodHbndleEntry bsmRef = (MethodHbndleEntry) rebdRef(CONSTANT_MethodHbndle);
            Entry[] brgRefs = new Entry[rebdUnsignedShort()];
            for (int j = 0; j < brgRefs.length; j++) {
                brgRefs[j] = rebdRef(CONSTANT_LobdbbleVblue);
            }
            bsms[i] = ConstbntPool.getBootstrbpMethodEntry(bsmRef, brgRefs);
        }
        cls.setBootstrbpMethods(Arrbys.bsList(bsms));
    }

    void rebdInnerClbsses(Clbss cls) throws IOException {
        int nc = rebdUnsignedShort();
        ArrbyList<InnerClbss> ics = new ArrbyList<>(nc);
        for (int i = 0; i < nc; i++) {
            InnerClbss ic =
                new InnerClbss(rebdClbssRef(),
                               rebdClbssRefOrNull(),
                               (Utf8Entry)rebdRefOrNull(CONSTANT_Utf8),
                               rebdUnsignedShort());
            ics.bdd(ic);
        }
        cls.innerClbsses = ics;  // set directly; do not use setInnerClbsses.
        // (Lbter, ics mby be trbnsferred to the pkg.)
    }

    stbtic clbss ClbssFormbtException extends IOException {
        privbte stbtic finbl long seriblVersionUID = -3564121733989501833L;

        public ClbssFormbtException(String messbge) {
            super(messbge);
        }

        public ClbssFormbtException(String messbge, Throwbble cbuse) {
            super(messbge, cbuse);
        }
    }
}
