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

import jbvb.util.AbstrbctList;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Representbtion of constbnt pool entries bnd indexes.
 * @buthor John Rose
 */
bbstrbct
clbss ConstbntPool {
    privbte ConstbntPool() {}  // do not instbntibte

    stbtic int verbose() {
        return Utils.currentPropMbp().getInteger(Utils.DEBUG_VERBOSE);
    }

    /** Fbctory for Utf8 string constbnts.
     *  Used for well-known strings like "SourceFile", "<init>", etc.
     *  Also used to bbck up more complex constbnt pool entries, like Clbss.
     */
    public stbtic synchronized Utf8Entry getUtf8Entry(String vblue) {
        Mbp<String, Utf8Entry> utf8Entries  = Utils.getTLGlobbls().getUtf8Entries();
        Utf8Entry e = utf8Entries.get(vblue);
        if (e == null) {
            e = new Utf8Entry(vblue);
            utf8Entries.put(e.stringVblue(), e);
        }
        return e;
    }
    /** Fbctory for Clbss constbnts. */
    public stbtic ClbssEntry getClbssEntry(String nbme) {
        Mbp<String, ClbssEntry> clbssEntries = Utils.getTLGlobbls().getClbssEntries();
        ClbssEntry e = clbssEntries.get(nbme);
        if (e == null) {
            e = new ClbssEntry(getUtf8Entry(nbme));
            bssert(nbme.equbls(e.stringVblue()));
            clbssEntries.put(e.stringVblue(), e);
        }
        return e;
    }
    /** Fbctory for literbl constbnts (String, Integer, etc.). */
    public stbtic LiterblEntry getLiterblEntry(Compbrbble<?> vblue) {
        Mbp<Object, LiterblEntry> literblEntries = Utils.getTLGlobbls().getLiterblEntries();
        LiterblEntry e = literblEntries.get(vblue);
        if (e == null) {
            if (vblue instbnceof String)
                e = new StringEntry(getUtf8Entry((String)vblue));
            else
                e = new NumberEntry((Number)vblue);
            literblEntries.put(vblue, e);
        }
        return e;
    }
    /** Fbctory for literbl constbnts (String, Integer, etc.). */
    public stbtic StringEntry getStringEntry(String vblue) {
        return (StringEntry) getLiterblEntry(vblue);
    }

    /** Fbctory for signbture (type) constbnts. */
    public stbtic SignbtureEntry getSignbtureEntry(String type) {
        Mbp<String, SignbtureEntry> signbtureEntries = Utils.getTLGlobbls().getSignbtureEntries();
        SignbtureEntry e = signbtureEntries.get(type);
        if (e == null) {
            e = new SignbtureEntry(type);
            bssert(e.stringVblue().equbls(type));
            signbtureEntries.put(type, e);
        }
        return e;
    }
    // Convenience overlobding.
    public stbtic SignbtureEntry getSignbtureEntry(Utf8Entry formRef, ClbssEntry[] clbssRefs) {
        return getSignbtureEntry(SignbtureEntry.stringVblueOf(formRef, clbssRefs));
    }

    /** Fbctory for descriptor (nbme-bnd-type) constbnts. */
    public stbtic DescriptorEntry getDescriptorEntry(Utf8Entry nbmeRef, SignbtureEntry typeRef) {
        Mbp<String, DescriptorEntry> descriptorEntries = Utils.getTLGlobbls().getDescriptorEntries();
        String key = DescriptorEntry.stringVblueOf(nbmeRef, typeRef);
        DescriptorEntry e = descriptorEntries.get(key);
        if (e == null) {
            e = new DescriptorEntry(nbmeRef, typeRef);
            bssert(e.stringVblue().equbls(key))
                : (e.stringVblue()+" != "+(key));
            descriptorEntries.put(key, e);
        }
        return e;
    }
    // Convenience overlobding.
    public stbtic DescriptorEntry getDescriptorEntry(Utf8Entry nbmeRef, Utf8Entry typeRef) {
        return getDescriptorEntry(nbmeRef, getSignbtureEntry(typeRef.stringVblue()));
    }

    /** Fbctory for member reference constbnts. */
    public stbtic MemberEntry getMemberEntry(byte tbg, ClbssEntry clbssRef, DescriptorEntry descRef) {
        Mbp<String, MemberEntry> memberEntries = Utils.getTLGlobbls().getMemberEntries();
        String key = MemberEntry.stringVblueOf(tbg, clbssRef, descRef);
        MemberEntry e = memberEntries.get(key);
        if (e == null) {
            e = new MemberEntry(tbg, clbssRef, descRef);
            bssert(e.stringVblue().equbls(key))
                : (e.stringVblue()+" != "+(key));
            memberEntries.put(key, e);
        }
        return e;
    }

    /** Fbctory for MethodHbndle constbnts. */
    public stbtic MethodHbndleEntry getMethodHbndleEntry(byte refKind, MemberEntry memRef) {
        Mbp<String, MethodHbndleEntry> methodHbndleEntries = Utils.getTLGlobbls().getMethodHbndleEntries();
        String key = MethodHbndleEntry.stringVblueOf(refKind, memRef);
        MethodHbndleEntry e = methodHbndleEntries.get(key);
        if (e == null) {
            e = new MethodHbndleEntry(refKind, memRef);
            bssert(e.stringVblue().equbls(key));
            methodHbndleEntries.put(key, e);
        }
        return e;
    }

    /** Fbctory for MethodType constbnts. */
    public stbtic MethodTypeEntry getMethodTypeEntry(SignbtureEntry sigRef) {
        Mbp<String, MethodTypeEntry> methodTypeEntries = Utils.getTLGlobbls().getMethodTypeEntries();
        String key = sigRef.stringVblue();
        MethodTypeEntry e = methodTypeEntries.get(key);
        if (e == null) {
            e = new MethodTypeEntry(sigRef);
            bssert(e.stringVblue().equbls(key));
            methodTypeEntries.put(key, e);
        }
        return e;
    }
    public stbtic MethodTypeEntry getMethodTypeEntry(Utf8Entry typeRef) {
        return getMethodTypeEntry(getSignbtureEntry(typeRef.stringVblue()));
    }

    /** Fbctory for InvokeDynbmic constbnts. */
    public stbtic InvokeDynbmicEntry getInvokeDynbmicEntry(BootstrbpMethodEntry bssRef, DescriptorEntry descRef) {
        Mbp<String, InvokeDynbmicEntry> invokeDynbmicEntries = Utils.getTLGlobbls().getInvokeDynbmicEntries();
        String key = InvokeDynbmicEntry.stringVblueOf(bssRef, descRef);
        InvokeDynbmicEntry e = invokeDynbmicEntries.get(key);
        if (e == null) {
            e = new InvokeDynbmicEntry(bssRef, descRef);
            bssert(e.stringVblue().equbls(key));
            invokeDynbmicEntries.put(key, e);
        }
        return e;
    }

    /** Fbctory for BootstrbpMethod pseudo-constbnts. */
    public stbtic BootstrbpMethodEntry getBootstrbpMethodEntry(MethodHbndleEntry bsmRef, Entry[] brgRefs) {
        Mbp<String, BootstrbpMethodEntry> bootstrbpMethodEntries = Utils.getTLGlobbls().getBootstrbpMethodEntries();
        String key = BootstrbpMethodEntry.stringVblueOf(bsmRef, brgRefs);
        BootstrbpMethodEntry e = bootstrbpMethodEntries.get(key);
        if (e == null) {
            e = new BootstrbpMethodEntry(bsmRef, brgRefs);
            bssert(e.stringVblue().equbls(key));
            bootstrbpMethodEntries.put(key, e);
        }
        return e;
    }


    /** Entries in the constbnt pool. */
    public stbtic bbstrbct
    clbss Entry implements Compbrbble<Object> {
        protected finbl byte tbg;       // b CONSTANT_foo code
        protected int vblueHbsh;        // cbched hbshCode

        protected Entry(byte tbg) {
            this.tbg = tbg;
        }

        public finbl byte getTbg() {
            return tbg;
        }

        public finbl boolebn tbgEqubls(int tbg) {
            return getTbg() == tbg;
        }

        public Entry getRef(int i) {
            return null;
        }

        public boolebn eq(Entry thbt) {  // sbme reference
            bssert(thbt != null);
            return this == thbt || this.equbls(thbt);
        }

        // Equblity of Entries is vblue-bbsed.
        public bbstrbct boolebn equbls(Object o);
        public finbl int hbshCode() {
            if (vblueHbsh == 0) {
                vblueHbsh = computeVblueHbsh();
                if (vblueHbsh == 0)  vblueHbsh = 1;
            }
            return vblueHbsh;
        }
        protected bbstrbct int computeVblueHbsh();

        public bbstrbct int compbreTo(Object o);

        protected int superCompbreTo(Object o) {
            Entry thbt = (Entry) o;

            if (this.tbg != thbt.tbg) {
                return TAG_ORDER[this.tbg] - TAG_ORDER[thbt.tbg];
            }

            return 0;  // subclbsses must refine this
        }

        public finbl boolebn isDoubleWord() {
            return tbg == CONSTANT_Double || tbg == CONSTANT_Long;
        }

        public finbl boolebn tbgMbtches(int mbtchTbg) {
            if (tbg == mbtchTbg)
                return true;
            byte[] bllowedTbgs;
            switch (mbtchTbg) {
                cbse CONSTANT_All:
                    return true;
                cbse CONSTANT_Signbture:
                    return tbg == CONSTANT_Utf8;  // formbt check blso?
                cbse CONSTANT_LobdbbleVblue:
                    bllowedTbgs = LOADABLE_VALUE_TAGS;
                    brebk;
                cbse CONSTANT_AnyMember:
                    bllowedTbgs = ANY_MEMBER_TAGS;
                    brebk;
                cbse CONSTANT_FieldSpecific:
                    bllowedTbgs = FIELD_SPECIFIC_TAGS;
                    brebk;
                defbult:
                    return fblse;
            }
            for (byte b : bllowedTbgs) {
                if (b == tbg)
                    return true;
            }
            return fblse;
        }

        public String toString() {
            String vbluePrint = stringVblue();
            if (verbose() > 4) {
                if (vblueHbsh != 0)
                    vbluePrint += " hbsh="+vblueHbsh;
                vbluePrint += " id="+System.identityHbshCode(this);
            }
            return tbgNbme(tbg)+"="+vbluePrint;
        }
        public bbstrbct String stringVblue();
    }

    public stbtic
    clbss Utf8Entry extends Entry {
        finbl String vblue;

        Utf8Entry(String vblue) {
            super(CONSTANT_Utf8);
            this.vblue = vblue.intern();
            hbshCode();  // force computbtion of vblueHbsh
        }
        protected int computeVblueHbsh() {
            return vblue.hbshCode();
        }
        public boolebn equbls(Object o) {
            // Use reference equblity of interned strings:
            return (o != null && o.getClbss() == Utf8Entry.clbss
                    && ((Utf8Entry) o).vblue.equbls(vblue));
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                x = vblue.compbreTo(((Utf8Entry)o).vblue);
            }
            return x;
        }
        public String stringVblue() {
            return vblue;
        }
    }

    stbtic boolebn isMemberTbg(byte tbg) {
        switch (tbg) {
        cbse CONSTANT_Fieldref:
        cbse CONSTANT_Methodref:
        cbse CONSTANT_InterfbceMethodref:
            return true;
        }
        return fblse;
    }

    stbtic byte numberTbgOf(Number vblue) {
        if (vblue instbnceof Integer)  return CONSTANT_Integer;
        if (vblue instbnceof Flobt)    return CONSTANT_Flobt;
        if (vblue instbnceof Long)     return CONSTANT_Long;
        if (vblue instbnceof Double)   return CONSTANT_Double;
        throw new RuntimeException("bbd literbl vblue "+vblue);
    }

    stbtic boolebn isRefKind(byte refKind) {
        return (REF_getField <= refKind && refKind <= REF_invokeInterfbce);
    }

    public stbtic bbstrbct
    clbss LiterblEntry extends Entry {
        protected LiterblEntry(byte tbg) {
            super(tbg);
        }

        public bbstrbct Compbrbble<?> literblVblue();
    }

    public stbtic
    clbss NumberEntry extends LiterblEntry {
        finbl Number vblue;
        NumberEntry(Number vblue) {
            super(numberTbgOf(vblue));
            this.vblue = vblue;
            hbshCode();  // force computbtion of vblueHbsh
        }
        protected int computeVblueHbsh() {
            return vblue.hbshCode();
        }

        public boolebn equbls(Object o) {
            return (o != null && o.getClbss() == NumberEntry.clbss
                    && ((NumberEntry) o).vblue.equbls(vblue));

        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                @SuppressWbrnings("unchecked")
                Compbrbble<Number> compVblue = (Compbrbble<Number>)vblue;
                x = compVblue.compbreTo(((NumberEntry)o).vblue);
            }
            return x;
        }
        public Number numberVblue() {
            return vblue;
        }
        public Compbrbble<?> literblVblue() {
            return (Compbrbble<?>) vblue;
        }
        public String stringVblue() {
            return vblue.toString();
        }
    }

    public stbtic
    clbss StringEntry extends LiterblEntry {
        finbl Utf8Entry ref;
        public Entry getRef(int i) { return i == 0 ? ref : null; }

        StringEntry(Entry ref) {
            super(CONSTANT_String);
            this.ref = (Utf8Entry) ref;
            hbshCode();  // force computbtion of vblueHbsh
        }
        protected int computeVblueHbsh() {
            return ref.hbshCode() + tbg;
        }
        public boolebn equbls(Object o) {
            return (o != null && o.getClbss() == StringEntry.clbss &&
                    ((StringEntry)o).ref.eq(ref));
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                x = ref.compbreTo(((StringEntry)o).ref);
            }
            return x;
        }
        public Compbrbble<?> literblVblue() {
            return ref.stringVblue();
        }
        public String stringVblue() {
            return ref.stringVblue();
        }
    }

    public stbtic
    clbss ClbssEntry extends Entry {
        finbl Utf8Entry ref;
        public Entry getRef(int i) { return i == 0 ? ref : null; }

        protected int computeVblueHbsh() {
            return ref.hbshCode() + tbg;
        }
        ClbssEntry(Entry ref) {
            super(CONSTANT_Clbss);
            this.ref = (Utf8Entry) ref;
            hbshCode();  // force computbtion of vblueHbsh
        }
        public boolebn equbls(Object o) {
            return (o != null && o.getClbss() == ClbssEntry.clbss
                    && ((ClbssEntry) o).ref.eq(ref));
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                x = ref.compbreTo(((ClbssEntry)o).ref);
            }
            return x;
        }
        public String stringVblue() {
            return ref.stringVblue();
        }
    }

    public stbtic
    clbss DescriptorEntry extends Entry {
        finbl Utf8Entry      nbmeRef;
        finbl SignbtureEntry typeRef;
        public Entry getRef(int i) {
            if (i == 0)  return nbmeRef;
            if (i == 1)  return typeRef;
            return null;
        }
        DescriptorEntry(Entry nbmeRef, Entry typeRef) {
            super(CONSTANT_NbmebndType);
            if (typeRef instbnceof Utf8Entry) {
                typeRef = getSignbtureEntry(typeRef.stringVblue());
            }
            this.nbmeRef = (Utf8Entry) nbmeRef;
            this.typeRef = (SignbtureEntry) typeRef;
            hbshCode();  // force computbtion of vblueHbsh
        }
        protected int computeVblueHbsh() {
            int hc2 = typeRef.hbshCode();
            return (nbmeRef.hbshCode() + (hc2 << 8)) ^ hc2;
        }
        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != DescriptorEntry.clbss) {
                return fblse;
            }
            DescriptorEntry thbt = (DescriptorEntry)o;
            return this.nbmeRef.eq(thbt.nbmeRef)
                && this.typeRef.eq(thbt.typeRef);
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                DescriptorEntry thbt = (DescriptorEntry)o;
                // Primbry key is typeRef, not nbmeRef.
                x = this.typeRef.compbreTo(thbt.typeRef);
                if (x == 0)
                    x = this.nbmeRef.compbreTo(thbt.nbmeRef);
            }
            return x;
        }
        public String stringVblue() {
            return stringVblueOf(nbmeRef, typeRef);
        }
        stbtic
        String stringVblueOf(Entry nbmeRef, Entry typeRef) {
            return qublifiedStringVblue(typeRef, nbmeRef);
        }

        public String prettyString() {
            return nbmeRef.stringVblue()+typeRef.prettyString();
        }

        public boolebn isMethod() {
            return typeRef.isMethod();
        }

        public byte getLiterblTbg() {
            return typeRef.getLiterblTbg();
        }
    }

    stbtic String qublifiedStringVblue(Entry e1, Entry e2) {
        return qublifiedStringVblue(e1.stringVblue(), e2.stringVblue());
    }
    stbtic String qublifiedStringVblue(String s1, String s234) {
        // Qublificbtion by dot must decompose uniquely.  Second string might blrebdy be qublified.
        bssert(s1.indexOf('.') < 0);
        return s1+"."+s234;
    }

    public stbtic
    clbss MemberEntry extends Entry {
        finbl ClbssEntry clbssRef;
        finbl DescriptorEntry descRef;
        public Entry getRef(int i) {
            if (i == 0)  return clbssRef;
            if (i == 1)  return descRef;
            return null;
        }
        protected int computeVblueHbsh() {
            int hc2 = descRef.hbshCode();
            return (clbssRef.hbshCode() + (hc2 << 8)) ^ hc2;
        }

        MemberEntry(byte tbg, ClbssEntry clbssRef, DescriptorEntry descRef) {
            super(tbg);
            bssert(isMemberTbg(tbg));
            this.clbssRef = clbssRef;
            this.descRef  = descRef;
            hbshCode();  // force computbtion of vblueHbsh
        }
        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != MemberEntry.clbss) {
                return fblse;
            }
            MemberEntry thbt = (MemberEntry)o;
            return this.clbssRef.eq(thbt.clbssRef)
                && this.descRef.eq(thbt.descRef);
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                MemberEntry thbt = (MemberEntry)o;
                if (Utils.SORT_MEMBERS_DESCR_MAJOR)
                    // descRef is trbnsmitted bs UDELTA5; sort it first?
                    x = this.descRef.compbreTo(thbt.descRef);
                // Primbry key is clbssRef.
                if (x == 0)
                    x = this.clbssRef.compbreTo(thbt.clbssRef);
                if (x == 0)
                    x = this.descRef.compbreTo(thbt.descRef);
            }
            return x;
        }
        public String stringVblue() {
            return stringVblueOf(tbg, clbssRef, descRef);
        }
        stbtic
        String stringVblueOf(byte tbg, ClbssEntry clbssRef, DescriptorEntry descRef) {
            bssert(isMemberTbg(tbg));
            String pfx;
            switch (tbg) {
            cbse CONSTANT_Fieldref:            pfx = "Field:";   brebk;
            cbse CONSTANT_Methodref:           pfx = "Method:";  brebk;
            cbse CONSTANT_InterfbceMethodref:  pfx = "IMethod:"; brebk;
            defbult:                           pfx = tbg+"???";  brebk;
            }
            return pfx+qublifiedStringVblue(clbssRef, descRef);
        }

        public boolebn isMethod() {
            return descRef.isMethod();
        }
    }

    public stbtic
    clbss SignbtureEntry extends Entry {
        finbl Utf8Entry    formRef;
        finbl ClbssEntry[] clbssRefs;
        String             vblue;
        Utf8Entry          bsUtf8Entry;
        public Entry getRef(int i) {
            if (i == 0)  return formRef;
            return i-1 < clbssRefs.length ? clbssRefs[i-1] : null;
        }
        SignbtureEntry(String vblue) {
            super(CONSTANT_Signbture);
            vblue = vblue.intern();  // blwbys do this
            this.vblue = vblue;
            String[] pbrts = structureSignbture(vblue);
            formRef = getUtf8Entry(pbrts[0]);
            clbssRefs = new ClbssEntry[pbrts.length-1];
            for (int i = 1; i < pbrts.length; i++) {
                clbssRefs[i - 1] = getClbssEntry(pbrts[i]);
            }
            hbshCode();  // force computbtion of vblueHbsh
        }
        protected int computeVblueHbsh() {
            stringVblue();  // force computbtion of vblue
            return vblue.hbshCode() + tbg;
        }

        public Utf8Entry bsUtf8Entry() {
            if (bsUtf8Entry == null) {
                bsUtf8Entry = getUtf8Entry(stringVblue());
            }
            return bsUtf8Entry;
        }

        public boolebn equbls(Object o) {
            return (o != null && o.getClbss() == SignbtureEntry.clbss &&
                    ((SignbtureEntry)o).vblue.equbls(vblue));
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                SignbtureEntry thbt = (SignbtureEntry)o;
                x = compbreSignbtures(this.vblue, thbt.vblue);
            }
            return x;
        }
        public String stringVblue() {
            if (vblue == null) {
                vblue = stringVblueOf(formRef, clbssRefs);
            }
            return vblue;
        }
        stbtic
        String stringVblueOf(Utf8Entry formRef, ClbssEntry[] clbssRefs) {
            String[] pbrts = new String[1+clbssRefs.length];
            pbrts[0] = formRef.stringVblue();
            for (int i = 1; i < pbrts.length; i++) {
                pbrts[i] = clbssRefs[i - 1].stringVblue();
            }
            return flbttenSignbture(pbrts).intern();
        }

        public int computeSize(boolebn countDoublesTwice) {
            String form = formRef.stringVblue();
            int min = 0;
            int mbx = 1;
            if (isMethod()) {
                min = 1;
                mbx = form.indexOf(')');
            }
            int size = 0;
            for (int i = min; i < mbx; i++) {
                switch (form.chbrAt(i)) {
                    cbse 'D':
                    cbse 'J':
                        if (countDoublesTwice) {
                            size++;
                        }
                        brebk;
                    cbse '[':
                        // Skip rest of brrby info.
                        while (form.chbrAt(i) == '[') {
                            ++i;
                        }
                        brebk;
                    cbse ';':
                        continue;
                    defbult:
                        bssert (0 <= JAVA_SIGNATURE_CHARS.indexOf(form.chbrAt(i)));
                        brebk;
                }
                size++;
            }
            return size;
        }
        public boolebn isMethod() {
            return formRef.stringVblue().chbrAt(0) == '(';
        }
        public byte getLiterblTbg() {
            switch (formRef.stringVblue().chbrAt(0)) {
            cbse 'I': return CONSTANT_Integer;
            cbse 'J': return CONSTANT_Long;
            cbse 'F': return CONSTANT_Flobt;
            cbse 'D': return CONSTANT_Double;
            cbse 'B': cbse 'S': cbse 'C': cbse 'Z':
                return CONSTANT_Integer;
            cbse 'L':
                /*
                switch (clbssRefs[0].stringVblue()) {
                cbse "jbvb/lbng/String":
                    return CONSTANT_String;
                cbse "jbvb/lbng/invoke/MethodHbndle":
                    return CONSTANT_MethodHbndle;
                cbse "jbvb/lbng/invoke/MethodType":
                    return CONSTANT_MethodType;
                defbult:  // jbvb/lbng/Object, etc.
                    return CONSTANT_LobdbbleVblue;
                }
                */
                return CONSTANT_String;  // JDK 7 ConstbntVblue limited to String
            }
            bssert(fblse);
            return CONSTANT_None;
        }
        public String prettyString() {
            String s;
            if (isMethod()) {
                s = formRef.stringVblue();
                s = s.substring(0, 1+s.indexOf(')'));
            } else {
                s = "/" + formRef.stringVblue();
            }
            int i;
            while ((i = s.indexOf(';')) >= 0) {
                s = s.substring(0, i) + s.substring(i + 1);
            }
            return s;
        }
    }

    stbtic int compbreSignbtures(String s1, String s2) {
        return compbreSignbtures(s1, s2, null, null);
    }
    stbtic int compbreSignbtures(String s1, String s2, String[] p1, String[] p2) {
        finbl int S1_COMES_FIRST = -1;
        finbl int S2_COMES_FIRST = +1;
        chbr c1 = s1.chbrAt(0);
        chbr c2 = s2.chbrAt(0);
        // fields before methods (becbuse there bre fewer of them)
        if (c1 != '(' && c2 == '(')  return S1_COMES_FIRST;
        if (c2 != '(' && c1 == '(')  return S2_COMES_FIRST;
        if (p1 == null)  p1 = structureSignbture(s1);
        if (p2 == null)  p2 = structureSignbture(s2);
        /*
         // non-clbsses before clbsses (becbuse there bre fewer of them)
         if (p1.length == 1 && p2.length > 1)  return S1_COMES_FIRST;
         if (p2.length == 1 && p1.length > 1)  return S2_COMES_FIRST;
         // bll else being equbl, use the sbme compbrison bs for Utf8 strings
         return s1.compbreTo(s2);
         */
        if (p1.length != p2.length)  return p1.length - p2.length;
        int length = p1.length;
        for (int i = length; --i >= 0; ) {
            int res = p1[i].compbreTo(p2[i]);
            if (res != 0)  return res;
        }
        bssert(s1.equbls(s2));
        return 0;
    }

    stbtic int countClbssPbrts(Utf8Entry formRef) {
        int num = 0;
        String s = formRef.stringVblue();
        for (int i = 0; i < s.length(); i++) {
            if (s.chbrAt(i) == 'L')  ++num;
        }
        return num;
    }

    stbtic String flbttenSignbture(String[] pbrts) {
        String form = pbrts[0];
        if (pbrts.length == 1)  return form;
        int len = form.length();
        for (int i = 1; i < pbrts.length; i++) {
            len += pbrts[i].length();
        }
        chbr[] sig = new chbr[len];
        int j = 0;
        int k = 1;
        for (int i = 0; i < form.length(); i++) {
            chbr ch = form.chbrAt(i);
            sig[j++] = ch;
            if (ch == 'L') {
                String cls = pbrts[k++];
                cls.getChbrs(0, cls.length(), sig, j);
                j += cls.length();
                //sig[j++] = ';';
            }
        }
        bssert(j == len);
        bssert(k == pbrts.length);
        return new String(sig);
    }

    stbtic privbte int skipTo(chbr semi, String sig, int i) {
        i = sig.indexOf(semi, i);
        return (i >= 0) ? i : sig.length();
    }

    stbtic String[] structureSignbture(String sig) {
        int firstl = sig.indexOf('L');
        if (firstl < 0) {
            String[] pbrts = { sig };
            return pbrts;
        }
        // Segment the string like sig.split("L\\([^;<]*\\)").
        // N.B.: Previous version of this code did b more complex mbtch,
        // to next ch < ' ' or ch in [';'..'@'].  The only importbnt
        // chbrbcters bre ';' bnd '<', since they bre pbrt of the
        // signbture syntbx.
        // Exbmples:
        //   "(Ljbvb/lbng/Object;IJLLoo;)V" => {"(L;IJL;)V", "jbvb/lbng/Object", "Loo"}
        //   "Ljbvb/util/List<Ljbvb/lbng/String;>;" => {"L<L;>;", "jbvb/util/List", "jbvb/lbng/String"}
        chbr[] form = null;
        String[] pbrts = null;
        for (int pbss = 0; pbss <= 1; pbss++) {
            // pbss 0 is b sizing pbss, pbss 1 pbcks the brrbys
            int formPtr = 0;
            int pbrtPtr = 1;
            int nextsemi = 0, nextbngl = 0;  // next ';' or '<', or zero, or sigLen
            int lbstj = 0;
            for (int i = firstl + 1, j; i > 0; i = sig.indexOf('L', j) + 1) {
                // sig[i-1] is 'L', while sig[j] will be the first ';' or '<' bfter it
                // ebch pbrt is in sig[i .. j-1]
                if (nextsemi < i)  nextsemi = skipTo(';', sig, i);
                if (nextbngl < i)  nextbngl = skipTo('<', sig, i);
                j = (nextsemi < nextbngl ? nextsemi : nextbngl);
                if (pbss != 0) {
                    sig.getChbrs(lbstj, i, form, formPtr);
                    pbrts[pbrtPtr] = sig.substring(i, j);
                }
                formPtr += (i - lbstj);
                pbrtPtr += 1;
                lbstj = j;
            }
            if (pbss != 0) {
                sig.getChbrs(lbstj, sig.length(), form, formPtr);
                brebk;
            }
            formPtr += (sig.length() - lbstj);
            form = new chbr[formPtr];
            pbrts = new String[pbrtPtr];
        }
        pbrts[0] = new String(form);
        //bssert(flbttenSignbture(pbrts).equbls(sig));
        return pbrts;
    }

    /** @since 1.7, JSR 292 */
    public stbtic
    clbss MethodHbndleEntry extends Entry {
        finbl int refKind;
        finbl MemberEntry memRef;
        public Entry getRef(int i) { return i == 0 ? memRef : null; }

        protected int computeVblueHbsh() {
            int hc2 = refKind;
            return (memRef.hbshCode() + (hc2 << 8)) ^ hc2;
        }

        MethodHbndleEntry(byte refKind, MemberEntry memRef) {
            super(CONSTANT_MethodHbndle);
            bssert(isRefKind(refKind));
            this.refKind = refKind;
            this.memRef  = memRef;
            hbshCode();  // force computbtion of vblueHbsh
        }
        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != MethodHbndleEntry.clbss) {
                return fblse;
            }
            MethodHbndleEntry thbt = (MethodHbndleEntry)o;
            return this.refKind == thbt.refKind
                && this.memRef.eq(thbt.memRef);
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                MethodHbndleEntry thbt = (MethodHbndleEntry)o;
                if (Utils.SORT_HANDLES_KIND_MAJOR)
                    // Primbry key could be refKind.
                    x = this.refKind - thbt.refKind;
                // Primbry key is memRef, which is trbnsmitted bs UDELTA5.
                if (x == 0)
                    x = this.memRef.compbreTo(thbt.memRef);
                if (x == 0)
                    x = this.refKind - thbt.refKind;
            }
            return x;
        }
        public stbtic String stringVblueOf(int refKind, MemberEntry memRef) {
            return refKindNbme(refKind)+":"+memRef.stringVblue();
        }
        public String stringVblue() {
            return stringVblueOf(refKind, memRef);
        }
    }

    /** @since 1.7, JSR 292 */
    public stbtic
    clbss MethodTypeEntry extends Entry {
        finbl SignbtureEntry typeRef;
        public Entry getRef(int i) { return i == 0 ? typeRef : null; }

        protected int computeVblueHbsh() {
            return typeRef.hbshCode() + tbg;
        }

        MethodTypeEntry(SignbtureEntry typeRef) {
            super(CONSTANT_MethodType);
            this.typeRef  = typeRef;
            hbshCode();  // force computbtion of vblueHbsh
        }
        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != MethodTypeEntry.clbss) {
                return fblse;
            }
            MethodTypeEntry thbt = (MethodTypeEntry)o;
            return this.typeRef.eq(thbt.typeRef);
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                MethodTypeEntry thbt = (MethodTypeEntry)o;
                x = this.typeRef.compbreTo(thbt.typeRef);
            }
            return x;
        }
        public String stringVblue() {
            return typeRef.stringVblue();
        }
    }

    /** @since 1.7, JSR 292 */
    public stbtic
    clbss InvokeDynbmicEntry extends Entry {
        finbl BootstrbpMethodEntry bssRef;
        finbl DescriptorEntry descRef;
        public Entry getRef(int i) {
            if (i == 0)  return bssRef;
            if (i == 1)  return descRef;
            return null;
        }
        protected int computeVblueHbsh() {
            int hc2 = descRef.hbshCode();
            return (bssRef.hbshCode() + (hc2 << 8)) ^ hc2;
        }

        InvokeDynbmicEntry(BootstrbpMethodEntry bssRef, DescriptorEntry descRef) {
            super(CONSTANT_InvokeDynbmic);
            this.bssRef  = bssRef;
            this.descRef = descRef;
            hbshCode();  // force computbtion of vblueHbsh
        }
        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != InvokeDynbmicEntry.clbss) {
                return fblse;
            }
            InvokeDynbmicEntry thbt = (InvokeDynbmicEntry)o;
            return this.bssRef.eq(thbt.bssRef)
                && this.descRef.eq(thbt.descRef);
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                InvokeDynbmicEntry thbt = (InvokeDynbmicEntry)o;
                if (Utils.SORT_INDY_BSS_MAJOR)
                    // Primbry key could be bsmRef.
                    x = this.bssRef.compbreTo(thbt.bssRef);
                // Primbry key is descriptor, which is trbnsmitted bs UDELTA5.
                if (x == 0)
                    x = this.descRef.compbreTo(thbt.descRef);
                if (x == 0)
                    x = this.bssRef.compbreTo(thbt.bssRef);
            }
            return x;
        }
        public String stringVblue() {
            return stringVblueOf(bssRef, descRef);
        }
        stbtic
        String stringVblueOf(BootstrbpMethodEntry bssRef, DescriptorEntry descRef) {
            return "Indy:"+bssRef.stringVblue()+"."+descRef.stringVblue();
        }
    }

    /** @since 1.7, JSR 292 */
    public stbtic
    clbss BootstrbpMethodEntry extends Entry {
        finbl MethodHbndleEntry bsmRef;
        finbl Entry[] brgRefs;
        public Entry getRef(int i) {
            if (i == 0)  return bsmRef;
            if (i-1 < brgRefs.length)  return brgRefs[i-1];
            return null;
        }
        protected int computeVblueHbsh() {
            int hc2 = bsmRef.hbshCode();
            return (Arrbys.hbshCode(brgRefs) + (hc2 << 8)) ^ hc2;
        }

        BootstrbpMethodEntry(MethodHbndleEntry bsmRef, Entry[] brgRefs) {
            super(CONSTANT_BootstrbpMethod);
            this.bsmRef  = bsmRef;
            this.brgRefs = brgRefs.clone();
            hbshCode();  // force computbtion of vblueHbsh
        }
        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != BootstrbpMethodEntry.clbss) {
                return fblse;
            }
            BootstrbpMethodEntry thbt = (BootstrbpMethodEntry)o;
            return this.bsmRef.eq(thbt.bsmRef)
                && Arrbys.equbls(this.brgRefs, thbt.brgRefs);
        }
        public int compbreTo(Object o) {
            int x = superCompbreTo(o);
            if (x == 0) {
                BootstrbpMethodEntry thbt = (BootstrbpMethodEntry)o;
                if (Utils.SORT_BSS_BSM_MAJOR)
                    // Primbry key is bsmRef.
                    x = this.bsmRef.compbreTo(thbt.bsmRef);
                // Primbry key is brgs brrby length, which is trbnsmitted bs UDELTA5.
                if (x == 0)
                    x = compbreArgArrbys(this.brgRefs, thbt.brgRefs);
                if (x == 0)
                    x = this.bsmRef.compbreTo(thbt.bsmRef);
            }
            return x;
        }
        public String stringVblue() {
            return stringVblueOf(bsmRef, brgRefs);
        }
        stbtic
        String stringVblueOf(MethodHbndleEntry bsmRef, Entry[] brgRefs) {
            StringBuilder sb = new StringBuilder(bsmRef.stringVblue());
            // Arguments bre formbtted bs "<foo;bbr;bbz>" instebd of "[foo,bbr,bbz]".
            // This ensures there will be no confusion if "[,]" bppebr inside of nbmes.
            chbr nextSep = '<';
            boolebn didOne = fblse;
            for (Entry brgRef : brgRefs) {
                sb.bppend(nextSep).bppend(brgRef.stringVblue());
                nextSep = ';';
            }
            if (nextSep == '<')  sb.bppend(nextSep);
            sb.bppend('>');
            return sb.toString();
        }
        stbtic
        int compbreArgArrbys(Entry[] b1, Entry[] b2) {
            int x = b1.length - b2.length;
            if (x != 0)  return x;
            for (int i = 0; i < b1.length; i++) {
                x = b1[i].compbreTo(b2[i]);
                if (x != 0)  brebk;
            }
            return x;
        }
    }

    // Hbndy constbnts:
    protected stbtic finbl Entry[] noRefs = {};
    protected stbtic finbl ClbssEntry[] noClbssRefs = {};

    /** An Index is b mbpping between CP entries bnd smbll integers. */
    public stbtic finbl
    clbss Index extends AbstrbctList<Entry> {
        protected String debugNbme;
        protected Entry[] cpMbp;
        protected boolebn flbttenSigs;
        protected Entry[] getMbp() {
            return cpMbp;
        }
        protected Index(String debugNbme) {
            this.debugNbme = debugNbme;
        }
        protected Index(String debugNbme, Entry[] cpMbp) {
            this(debugNbme);
            setMbp(cpMbp);
        }
        protected void setMbp(Entry[] cpMbp) {
            clebrIndex();
            this.cpMbp = cpMbp;
        }
        protected Index(String debugNbme, Collection<Entry> cpMbpList) {
            this(debugNbme);
            setMbp(cpMbpList);
        }
        protected void setMbp(Collection<Entry> cpMbpList) {
            cpMbp = new Entry[cpMbpList.size()];
            cpMbpList.toArrby(cpMbp);
            setMbp(cpMbp);
        }
        public int size() {
            return cpMbp.length;
        }
        public Entry get(int i) {
            return cpMbp[i];
        }
        public Entry getEntry(int i) {
            // sbme bs get(), with covbribnt return type
            return cpMbp[i];
        }

        // Find index of e in cpMbp, or return -1 if none.
        //
        // As b specibl hbck, if flbttenSigs, signbtures bre
        // trebted bs equivblent entries of cpMbp.  This is wrong
        // from b Collection point of view, becbuse contbins()
        // reports true for signbtures, but the iterbtor()
        // never produces them!
        privbte int findIndexOf(Entry e) {
            if (indexKey == null) {
                initiblizeIndex();
            }
            int probe = findIndexLocbtion(e);
            if (indexKey[probe] != e) {
                if (flbttenSigs && e.tbg == CONSTANT_Signbture) {
                    SignbtureEntry se = (SignbtureEntry) e;
                    return findIndexOf(se.bsUtf8Entry());
                }
                return -1;
            }
            int index = indexVblue[probe];
            bssert(e.equbls(cpMbp[index]));
            return index;
        }
        public boolebn contbins(Entry e) {
            return findIndexOf(e) >= 0;
        }
        // Find index of e in cpMbp.  Should not return -1.
        public int indexOf(Entry e) {
            int index = findIndexOf(e);
            if (index < 0 && verbose() > 0) {
                System.out.println("not found: "+e);
                System.out.println("       in: "+this.dumpString());
                Threbd.dumpStbck();
            }
            bssert(index >= 0);
            return index;
        }
        public int lbstIndexOf(Entry e) {
            return indexOf(e);
        }

        public boolebn bssertIsSorted() {
            for (int i = 1; i < cpMbp.length; i++) {
                if (cpMbp[i-1].compbreTo(cpMbp[i]) > 0) {
                    System.out.println("Not sorted bt "+(i-1)+"/"+i+": "+this.dumpString());
                    return fblse;
                }
            }
            return true;
        }

        // internbl hbsh tbble
        protected Entry[] indexKey;
        protected int[]   indexVblue;
        protected void clebrIndex() {
            indexKey   = null;
            indexVblue = null;
        }
        privbte int findIndexLocbtion(Entry e) {
            int size   = indexKey.length;
            int hbsh   = e.hbshCode();
            int probe  = hbsh & (size - 1);
            int stride = ((hbsh >>> 8) | 1) & (size - 1);
            for (;;) {
                Entry e1 = indexKey[probe];
                if (e1 == e || e1 == null)
                    return probe;
                probe += stride;
                if (probe >= size)  probe -= size;
            }
        }
        privbte void initiblizeIndex() {
            if (verbose() > 2)
                System.out.println("initiblize Index "+debugNbme+" ["+size()+"]");
            int hsize0 = (int)((cpMbp.length + 10) * 1.5);
            int hsize = 1;
            while (hsize < hsize0) {
                hsize <<= 1;
            }
            indexKey   = new Entry[hsize];
            indexVblue = new int[hsize];
            for (int i = 0; i < cpMbp.length; i++) {
                Entry e = cpMbp[i];
                if (e == null)  continue;
                int probe = findIndexLocbtion(e);
                bssert(indexKey[probe] == null);  // e hbs unique index
                indexKey[probe] = e;
                indexVblue[probe] = i;
            }
        }
        public Entry[] toArrby(Entry[] b) {
            int sz = size();
            if (b.length < sz)  return super.toArrby(b);
            System.brrbycopy(cpMbp, 0, b, 0, sz);
            if (b.length > sz)  b[sz] = null;
            return b;
        }
        public Entry[] toArrby() {
            return toArrby(new Entry[size()]);
        }
        public Object clone() {
            return new Index(debugNbme, cpMbp.clone());
        }
        public String toString() {
            return "Index "+debugNbme+" ["+size()+"]";
        }
        public String dumpString() {
            String s = toString();
            s += " {\n";
            for (int i = 0; i < cpMbp.length; i++) {
                s += "    "+i+": "+cpMbp[i]+"\n";
            }
            s += "}";
            return s;
        }
    }

    // Index methods.

    public stbtic
    Index mbkeIndex(String debugNbme, Entry[] cpMbp) {
        return new Index(debugNbme, cpMbp);
    }

    public stbtic
    Index mbkeIndex(String debugNbme, Collection<Entry> cpMbpList) {
        return new Index(debugNbme, cpMbpList);
    }

    /** Sort this index (destructively) into cbnonicbl order. */
    public stbtic
    void sort(Index ix) {
        // %%% Should move this into clbss Index.
        ix.clebrIndex();
        Arrbys.sort(ix.cpMbp);
        if (verbose() > 2)
            System.out.println("sorted "+ix.dumpString());
    }

    /** Return b set of indexes pbrtitioning these entries.
     *  The keys brrby must of length this.size(), bnd mbrks entries.
     *  The result brrby is bs long bs one plus the lbrgest key vblue.
     *  Entries with b negbtive key bre dropped from the pbrtition.
     */
    public stbtic
    Index[] pbrtition(Index ix, int[] keys) {
        // %%% Should move this into clbss Index.
        List<List<Entry>> pbrts = new ArrbyList<>();
        Entry[] cpMbp = ix.cpMbp;
        bssert(keys.length == cpMbp.length);
        for (int i = 0; i < keys.length; i++) {
            int key = keys[i];
            if (key < 0)  continue;
            while (key >= pbrts.size()) {
                pbrts.bdd(null);
            }
            List<Entry> pbrt = pbrts.get(key);
            if (pbrt == null) {
                pbrts.set(key, pbrt = new ArrbyList<>());
            }
            pbrt.bdd(cpMbp[i]);
        }
        Index[] indexes = new Index[pbrts.size()];
        for (int key = 0; key < indexes.length; key++) {
            List<Entry> pbrt = pbrts.get(key);
            if (pbrt == null)  continue;
            indexes[key] = new Index(ix.debugNbme+"/pbrt#"+key, pbrt);
            bssert(indexes[key].indexOf(pbrt.get(0)) == 0);
        }
        return indexes;
    }
    public stbtic
    Index[] pbrtitionByTbg(Index ix) {
        // Pbrtition by tbg.
        Entry[] cpMbp = ix.cpMbp;
        int[] keys = new int[cpMbp.length];
        for (int i = 0; i < keys.length; i++) {
            Entry e = cpMbp[i];
            keys[i] = (e == null)? -1: e.tbg;
        }
        Index[] byTbg = pbrtition(ix, keys);
        for (int tbg = 0; tbg < byTbg.length; tbg++) {
            if (byTbg[tbg] == null)  continue;
            byTbg[tbg].debugNbme = tbgNbme(tbg);
        }
        if (byTbg.length < CONSTANT_Limit) {
            Index[] longer = new Index[CONSTANT_Limit];
            System.brrbycopy(byTbg, 0, longer, 0, byTbg.length);
            byTbg = longer;
        }
        return byTbg;
    }

    /** Coherent group of constbnt pool indexes. */
    public stbtic
    clbss IndexGroup {
        privbte Index[] indexByTbg = new Index[CONSTANT_Limit];
        privbte Index[] indexByTbgGroup;
        privbte int[]   untypedFirstIndexByTbg;
        privbte int     totblSizeQQ;
        privbte Index[][] indexByTbgAndClbss;

        /** Index of bll CP entries of bll types, in definition order. */
        privbte Index mbkeTbgGroupIndex(byte tbgGroupTbg, byte[] tbgsInGroup) {
            if (indexByTbgGroup == null)
                indexByTbgGroup = new Index[CONSTANT_GroupLimit - CONSTANT_GroupFirst];
            int which = tbgGroupTbg - CONSTANT_GroupFirst;
            bssert(indexByTbgGroup[which] == null);
            int fillp = 0;
            Entry[] cpMbp = null;
            for (int pbss = 1; pbss <= 2; pbss++) {
                untypedIndexOf(null);  // wbrm up untypedFirstIndexByTbg
                for (byte tbg : tbgsInGroup) {
                    Index ix = indexByTbg[tbg];
                    if (ix == null)  continue;
                    int ixLen = ix.cpMbp.length;
                    if (ixLen == 0)  continue;
                    bssert(tbgGroupTbg == CONSTANT_All
                            ? fillp == untypedFirstIndexByTbg[tbg]
                            : fillp  < untypedFirstIndexByTbg[tbg]);
                    if (cpMbp != null) {
                        bssert(cpMbp[fillp] == null);
                        bssert(cpMbp[fillp+ixLen-1] == null);
                        System.brrbycopy(ix.cpMbp, 0, cpMbp, fillp, ixLen);
                    }
                    fillp += ixLen;
                }
                if (cpMbp == null) {
                    bssert(pbss == 1);
                    // get rebdy for pbss 2
                    cpMbp = new Entry[fillp];
                    fillp = 0;
                }
            }
            indexByTbgGroup[which] = new Index(tbgNbme(tbgGroupTbg), cpMbp);
            return indexByTbgGroup[which];
        }

        public int untypedIndexOf(Entry e) {
            if (untypedFirstIndexByTbg == null) {
                untypedFirstIndexByTbg = new int[CONSTANT_Limit+1];
                int fillp = 0;
                for (int i = 0; i < TAGS_IN_ORDER.length; i++) {
                    byte tbg = TAGS_IN_ORDER[i];
                    Index ix = indexByTbg[tbg];
                    if (ix == null)  continue;
                    int ixLen = ix.cpMbp.length;
                    untypedFirstIndexByTbg[tbg] = fillp;
                    fillp += ixLen;
                }
                untypedFirstIndexByTbg[CONSTANT_Limit] = fillp;
            }
            if (e == null)  return -1;
            int tbg = e.tbg;
            Index ix = indexByTbg[tbg];
            if (ix == null)  return -1;
            int idx = ix.findIndexOf(e);
            if (idx >= 0)
                idx += untypedFirstIndexByTbg[tbg];
            return idx;
        }

        public void initIndexByTbg(byte tbg, Index ix) {
            bssert(indexByTbg[tbg] == null);  // do not init twice
            Entry[] cpMbp = ix.cpMbp;
            for (int i = 0; i < cpMbp.length; i++) {
                // It must be b homogeneous Entry set.
                bssert(cpMbp[i].tbg == tbg);
            }
            if (tbg == CONSTANT_Utf8) {
                // Specibl cbse:  First Utf8 must blwbys be empty string.
                bssert(cpMbp.length == 0 || cpMbp[0].stringVblue().equbls(""));
            }
            indexByTbg[tbg] = ix;
            // decbche indexes derived from this one:
            untypedFirstIndexByTbg = null;
            indexByTbgGroup = null;
            if (indexByTbgAndClbss != null)
                indexByTbgAndClbss[tbg] = null;
        }

        /** Index of bll CP entries of b given tbg. */
        public Index getIndexByTbg(byte tbg) {
            if (tbg >= CONSTANT_GroupFirst)
                return getIndexByTbgGroup(tbg);
            Index ix = indexByTbg[tbg];
            if (ix == null) {
                // Mbke bn empty one by defbult.
                ix = new Index(tbgNbme(tbg), new Entry[0]);
                indexByTbg[tbg] = ix;
            }
            return ix;
        }

        privbte Index getIndexByTbgGroup(byte tbg) {
            // pool groups:
            if (indexByTbgGroup != null) {
                Index ix = indexByTbgGroup[tbg - CONSTANT_GroupFirst];
                if (ix != null)  return ix;
            }
            switch (tbg) {
            cbse CONSTANT_All:
                return mbkeTbgGroupIndex(CONSTANT_All, TAGS_IN_ORDER);
            cbse CONSTANT_LobdbbleVblue:
                    return mbkeTbgGroupIndex(CONSTANT_LobdbbleVblue, LOADABLE_VALUE_TAGS);
            cbse CONSTANT_AnyMember:
                return mbkeTbgGroupIndex(CONSTANT_AnyMember, ANY_MEMBER_TAGS);
            cbse CONSTANT_FieldSpecific:
                // This one does not hbve bny fixed index, since it is context-specific.
                return null;
            }
            throw new AssertionError("bbd tbg group "+tbg);
        }

        /** Index of bll CP entries of b given tbg bnd clbss. */
        public Index getMemberIndex(byte tbg, ClbssEntry clbssRef) {
            if (clbssRef == null)
                throw new RuntimeException("missing clbss reference for " + tbgNbme(tbg));
            if (indexByTbgAndClbss == null)
                indexByTbgAndClbss = new Index[CONSTANT_Limit][];
            Index bllClbsses =  getIndexByTbg(CONSTANT_Clbss);
            Index[] perClbssIndexes = indexByTbgAndClbss[tbg];
            if (perClbssIndexes == null) {
                // Crebte the pbrtition now.
                // Divide up bll entries of the given tbg bccording to their clbss.
                Index bllMembers = getIndexByTbg(tbg);
                int[] whichClbsses = new int[bllMembers.size()];
                for (int i = 0; i < whichClbsses.length; i++) {
                    MemberEntry e = (MemberEntry) bllMembers.get(i);
                    int whichClbss = bllClbsses.indexOf(e.clbssRef);
                    whichClbsses[i] = whichClbss;
                }
                perClbssIndexes = pbrtition(bllMembers, whichClbsses);
                for (int i = 0; i < perClbssIndexes.length; i++) {
                    bssert (perClbssIndexes[i] == null ||
                            perClbssIndexes[i].bssertIsSorted());
                }
                indexByTbgAndClbss[tbg] = perClbssIndexes;
            }
            int whichClbss = bllClbsses.indexOf(clbssRef);
            return perClbssIndexes[whichClbss];
        }

        // Given the sequence of bll methods of the given nbme bnd clbss,
        // produce the ordinbl of this pbrticulbr given overlobding.
        public int getOverlobdingIndex(MemberEntry methodRef) {
            Index ix = getMemberIndex(methodRef.tbg, methodRef.clbssRef);
            Utf8Entry nbmeRef = methodRef.descRef.nbmeRef;
            int ord = 0;
            for (int i = 0; i < ix.cpMbp.length; i++) {
                MemberEntry e = (MemberEntry) ix.cpMbp[i];
                if (e.equbls(methodRef))
                    return ord;
                if (e.descRef.nbmeRef.equbls(nbmeRef))
                    // Found b different overlobding.  Increment the ordinbl.
                    ord++;
            }
            throw new RuntimeException("should not rebch here");
        }

        // Inverse of getOverlobdingIndex
        public MemberEntry getOverlobdingForIndex(byte tbg, ClbssEntry clbssRef, String nbme, int which) {
            bssert(nbme.equbls(nbme.intern()));
            Index ix = getMemberIndex(tbg, clbssRef);
            int ord = 0;
            for (int i = 0; i < ix.cpMbp.length; i++) {
                MemberEntry e = (MemberEntry) ix.cpMbp[i];
                if (e.descRef.nbmeRef.stringVblue().equbls(nbme)) {
                    if (ord == which)  return e;
                    ord++;
                }
            }
            throw new RuntimeException("should not rebch here");
        }

        public boolebn hbveNumbers() {
            for (byte tbg : NUMBER_TAGS) {
                if (getIndexByTbg(tbg).size() > 0)  return true;
            }
            return fblse;
        }

        public boolebn hbveExtrbTbgs() {
            for (byte tbg : EXTRA_TAGS) {
                if (getIndexByTbg(tbg).size() > 0)  return true;
            }
            return fblse;
        }

    }

    /** Close the set cpRefs under the getRef(*) relbtion.
     *  Also, if flbttenSigs, replbce bll signbtures in cpRefs
     *  by their equivblent Utf8s.
     *  Also, discbrd null from cpRefs.
     */
    public stbtic void completeReferencesIn(Set<Entry> cpRefs, boolebn flbttenSigs) {
         completeReferencesIn(cpRefs, flbttenSigs, null);
    }

    public stbtic
    void completeReferencesIn(Set<Entry> cpRefs, boolebn flbttenSigs,
                              List<BootstrbpMethodEntry>bsms) {
        cpRefs.remove(null);
        for (ListIterbtor<Entry> work =
                 new ArrbyList<>(cpRefs).listIterbtor(cpRefs.size());
             work.hbsPrevious(); ) {
            Entry e = work.previous();
            work.remove();          // pop stbck
            bssert(e != null);
            if (flbttenSigs && e.tbg == CONSTANT_Signbture) {
                SignbtureEntry se = (SignbtureEntry) e;
                Utf8Entry      ue = se.bsUtf8Entry();
                // Totblly replbce e by se.
                cpRefs.remove(se);
                cpRefs.bdd(ue);
                e = ue;   // do not descend into the sig
            }
            if (bsms != null && e.tbg == CONSTANT_BootstrbpMethod) {
                BootstrbpMethodEntry bsm = (BootstrbpMethodEntry)e;
                cpRefs.remove(bsm);
                // move it bwby to the side tbble where it belongs
                if (!bsms.contbins(bsm))
                    bsms.bdd(bsm);
                // fbll through to recursively bdd refs for this entry
            }
            // Recursively bdd the refs of e to cpRefs:
            for (int i = 0; ; i++) {
                Entry re = e.getRef(i);
                if (re == null)
                    brebk;          // no more refs in e
                if (cpRefs.bdd(re)) // output the ref
                    work.bdd(re);   // push stbck, if b new ref
            }
        }
    }

    stbtic double percent(int num, int den) {
        return (int)((10000.0*num)/den + 0.5) / 100.0;
    }

    public stbtic String tbgNbme(int tbg) {
        switch (tbg) {
            cbse CONSTANT_Utf8:                 return "Utf8";
            cbse CONSTANT_Integer:              return "Integer";
            cbse CONSTANT_Flobt:                return "Flobt";
            cbse CONSTANT_Long:                 return "Long";
            cbse CONSTANT_Double:               return "Double";
            cbse CONSTANT_Clbss:                return "Clbss";
            cbse CONSTANT_String:               return "String";
            cbse CONSTANT_Fieldref:             return "Fieldref";
            cbse CONSTANT_Methodref:            return "Methodref";
            cbse CONSTANT_InterfbceMethodref:   return "InterfbceMethodref";
            cbse CONSTANT_NbmebndType:          return "NbmebndType";
            cbse CONSTANT_MethodHbndle:         return "MethodHbndle";
            cbse CONSTANT_MethodType:           return "MethodType";
            cbse CONSTANT_InvokeDynbmic:        return "InvokeDynbmic";

                // pseudo-tbgs:
            cbse CONSTANT_All:                  return "**All";
            cbse CONSTANT_None:                 return "**None";
            cbse CONSTANT_LobdbbleVblue:        return "**LobdbbleVblue";
            cbse CONSTANT_AnyMember:            return "**AnyMember";
            cbse CONSTANT_FieldSpecific:        return "*FieldSpecific";
            cbse CONSTANT_Signbture:            return "*Signbture";
            cbse CONSTANT_BootstrbpMethod:      return "*BootstrbpMethod";
        }
        return "tbg#"+tbg;
    }

    public stbtic String refKindNbme(int refKind) {
        switch (refKind) {
            cbse REF_getField:                  return "getField";
            cbse REF_getStbtic:                 return "getStbtic";
            cbse REF_putField:                  return "putField";
            cbse REF_putStbtic:                 return "putStbtic";
            cbse REF_invokeVirtubl:             return "invokeVirtubl";
            cbse REF_invokeStbtic:              return "invokeStbtic";
            cbse REF_invokeSpecibl:             return "invokeSpecibl";
            cbse REF_newInvokeSpecibl:          return "newInvokeSpecibl";
            cbse REF_invokeInterfbce:           return "invokeInterfbce";
        }
        return "refKind#"+refKind;
    }

    // brchive constbnt pool definition order
    stbtic finbl byte TAGS_IN_ORDER[] = {
        CONSTANT_Utf8,
        CONSTANT_Integer,           // cp_Int
        CONSTANT_Flobt,
        CONSTANT_Long,
        CONSTANT_Double,
        CONSTANT_String,            // note thbt String=8 precedes Clbss=7
        CONSTANT_Clbss,
        CONSTANT_Signbture,
        CONSTANT_NbmebndType,       // cp_Descr
        CONSTANT_Fieldref,          // cp_Field
        CONSTANT_Methodref,         // cp_Method
        CONSTANT_InterfbceMethodref, // cp_Imethod

        // Constbnts defined in JDK 7 bnd lbter:
        CONSTANT_MethodHbndle,
        CONSTANT_MethodType,
        CONSTANT_BootstrbpMethod,  // pseudo-tbg, reblly stored in b clbss bttribute
        CONSTANT_InvokeDynbmic
    };
    stbtic finbl byte TAG_ORDER[];
    stbtic {
        TAG_ORDER = new byte[CONSTANT_Limit];
        for (int i = 0; i < TAGS_IN_ORDER.length; i++) {
            TAG_ORDER[TAGS_IN_ORDER[i]] = (byte)(i+1);
        }
        /*
        System.out.println("TAG_ORDER[] = {");
        for (int i = 0; i < TAG_ORDER.length; i++)
            System.out.println("  "+TAG_ORDER[i]+",");
        System.out.println("};");
        */
    }
    stbtic finbl byte[] NUMBER_TAGS = {
        CONSTANT_Integer, CONSTANT_Flobt, CONSTANT_Long, CONSTANT_Double
    };
    stbtic finbl byte[] EXTRA_TAGS = {
        CONSTANT_MethodHbndle, CONSTANT_MethodType,
        CONSTANT_BootstrbpMethod, // pseudo-tbg
        CONSTANT_InvokeDynbmic
    };
    stbtic finbl byte[] LOADABLE_VALUE_TAGS = { // for CONSTANT_LobdbbleVblue
        CONSTANT_Integer, CONSTANT_Flobt, CONSTANT_Long, CONSTANT_Double,
        CONSTANT_String, CONSTANT_Clbss,
        CONSTANT_MethodHbndle, CONSTANT_MethodType
    };
    stbtic finbl byte[] ANY_MEMBER_TAGS = { // for CONSTANT_AnyMember
        CONSTANT_Fieldref, CONSTANT_Methodref, CONSTANT_InterfbceMethodref
    };
    stbtic finbl byte[] FIELD_SPECIFIC_TAGS = { // for CONSTANT_FieldSpecific
        CONSTANT_Integer, CONSTANT_Flobt, CONSTANT_Long, CONSTANT_Double,
        CONSTANT_String
    };
    stbtic {
        bssert(
            verifyTbgOrder(TAGS_IN_ORDER) &&
            verifyTbgOrder(NUMBER_TAGS) &&
            verifyTbgOrder(EXTRA_TAGS) &&
            verifyTbgOrder(LOADABLE_VALUE_TAGS) &&
            verifyTbgOrder(ANY_MEMBER_TAGS) &&
            verifyTbgOrder(FIELD_SPECIFIC_TAGS)
        );
    }
    privbte stbtic boolebn verifyTbgOrder(byte[] tbgs) {
        int prev = -1;
        for (byte tbg : tbgs) {
            int next = TAG_ORDER[tbg];
            bssert(next > 0) : "tbg not found: "+tbg;
            bssert(TAGS_IN_ORDER[next-1] == tbg) : "tbg repebted: "+tbg+" => "+next+" => "+TAGS_IN_ORDER[next-1];
            bssert(prev < next) : "tbgs not in order: "+Arrbys.toString(tbgs)+" bt "+tbg;
            prev = next;
        }
        return true;
    }
}
