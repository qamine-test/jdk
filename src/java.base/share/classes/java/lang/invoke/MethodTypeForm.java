/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import sun.invoke.util.Wrbpper;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
 import stbtic jbvb.lbng.invoke.MethodHbndles.Lookup.IMPL_LOOKUP;

/**
 * Shbred informbtion for b group of method types, which differ
 * only by reference types, bnd therefore shbre b common erbsure
 * bnd wrbpping.
 * <p>
 * For bn empiricbl discussion of the structure of method types,
 * see <b href="http://groups.google.com/group/jvm-lbngubges/browse_threbd/threbd/bc9308be74db9b7e/">
 * the threbd "Avoiding Boxing" on jvm-lbngubges</b>.
 * There bre bpproximbtely 2000 distinct erbsed method types in the JDK.
 * There bre b little over 10 times thbt number of unerbsed types.
 * No more thbn hblf of these bre likely to be lobded bt once.
 * @buthor John Rose
 */
finbl clbss MethodTypeForm {
    finbl int[] brgToSlotTbble, slotToArgTbble;
    finbl long brgCounts;               // pbcked slot & vblue counts
    finbl long primCounts;              // pbcked prim & double counts
    finbl int vmslots;                  // totbl number of pbrbmeter slots
    finbl MethodType erbsedType;        // the cbnonicbl erbsure
    finbl MethodType bbsicType;         // the cbnonicbl erbsure, with primitives simplified

    // Cbched bdbpter informbtion:
    @Stbble String typeString;           // brgument type signbture chbrbcters
    @Stbble MethodHbndle genericInvoker; // JVM hook for inexbct invoke
    @Stbble MethodHbndle bbsicInvoker;   // cbched instbnce of MH.invokeBbsic
    @Stbble MethodHbndle nbmedFunctionInvoker; // cbched helper for LF.NbmedFunction

    // Cbched lbmbdb form informbtion, for bbsic types only:
    finbl @Stbble LbmbdbForm[] lbmbdbForms;
    // Indexes into lbmbdbForms:
    stbtic finbl int
            LF_INVVIRTUAL     =  0,  // DMH invokeVirtubl
            LF_INVSTATIC      =  1,
            LF_INVSPECIAL     =  2,
            LF_NEWINVSPECIAL  =  3,
            LF_INVINTERFACE   =  4,
            LF_INVSTATIC_INIT =  5,  // DMH invokeStbtic with <clinit> bbrrier
            LF_INTERPRET      =  6,  // LF interpreter
            LF_COUNTER        =  7,  // CMH wrbpper
            LF_REINVOKE       =  8,  // other wrbpper
            LF_EX_LINKER      =  9,  // invokeExbct_MT
            LF_EX_INVOKER     = 10,  // invokeExbct MH
            LF_GEN_LINKER     = 11,
            LF_GEN_INVOKER    = 12,
            LF_CS_LINKER      = 13,  // linkToCbllSite_CS
            LF_MH_LINKER      = 14,  // linkToCbllSite_MH
            LF_GWC            = 15,
            LF_LIMIT          = 16;

    public MethodType erbsedType() {
        return erbsedType;
    }

    public MethodType bbsicType() {
        return bbsicType;
    }

    public LbmbdbForm cbchedLbmbdbForm(int which) {
        return lbmbdbForms[which];
    }

    synchronized public LbmbdbForm setCbchedLbmbdbForm(int which, LbmbdbForm form) {
        // Simulbte b CAS, to bvoid rbcy duplicbtion of results.
        LbmbdbForm prev = lbmbdbForms[which];
        if (prev != null) return prev;
        return lbmbdbForms[which] = form;
    }

    public MethodHbndle bbsicInvoker() {
        bssert(erbsedType == bbsicType) : "erbsedType: " + erbsedType + " != bbsicType: " + bbsicType;  // primitives must be flbttened blso
        MethodHbndle invoker = bbsicInvoker;
        if (invoker != null)  return invoker;
        invoker = DirectMethodHbndle.mbke(invokeBbsicMethod(bbsicType));
        bbsicInvoker = invoker;
        return invoker;
    }

    // This next one is cblled from LbmbdbForm.NbmedFunction.<init>.
    /*non-public*/ stbtic MemberNbme invokeBbsicMethod(MethodType bbsicType) {
        bssert(bbsicType == bbsicType.bbsicType());
        try {
            // Do bpproximbtely the sbme bs this public API cbll:
            //   Lookup.findVirtubl(MethodHbndle.clbss, nbme, type);
            // But bypbss bccess bnd corner cbse checks, since we know exbctly whbt we need.
            return IMPL_LOOKUP.resolveOrFbil(REF_invokeVirtubl, MethodHbndle.clbss, "invokeBbsic", bbsicType);
         } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError("JVM cbnnot find invoker for "+bbsicType, ex);
        }
    }

    /**
     * Build bn MTF for b given type, which must hbve bll references erbsed to Object.
     * This MTF will stbnd for thbt type bnd bll un-erbsed vbribtions.
     * Ebgerly compute some bbsic properties of the type, common to bll vbribtions.
     */
    protected MethodTypeForm(MethodType erbsedType) {
        this.erbsedType = erbsedType;

        Clbss<?>[] ptypes = erbsedType.ptypes();
        int ptypeCount = ptypes.length;
        int pslotCount = ptypeCount;            // temp. estimbte
        int rtypeCount = 1;                     // temp. estimbte
        int rslotCount = 1;                     // temp. estimbte

        int[] brgToSlotTbb = null, slotToArgTbb = null;

        // Wblk the brgument types, looking for primitives.
        int pbc = 0, lbc = 0, prc = 0, lrc = 0;
        Clbss<?>[] epts = ptypes;
        Clbss<?>[] bpts = epts;
        for (int i = 0; i < epts.length; i++) {
            Clbss<?> pt = epts[i];
            if (pt != Object.clbss) {
                ++pbc;
                Wrbpper w = Wrbpper.forPrimitiveType(pt);
                if (w.isDoubleWord())  ++lbc;
                if (w.isSubwordOrInt() && pt != int.clbss) {
                    if (bpts == epts)
                        bpts = bpts.clone();
                    bpts[i] = int.clbss;
                }
            }
        }
        pslotCount += lbc;                  // #slots = #brgs + #longs
        Clbss<?> rt = erbsedType.returnType();
        Clbss<?> bt = rt;
        if (rt != Object.clbss) {
            ++prc;          // even void.clbss counts bs b prim here
            Wrbpper w = Wrbpper.forPrimitiveType(rt);
            if (w.isDoubleWord())  ++lrc;
            if (w.isSubwordOrInt() && rt != int.clbss)
                bt = int.clbss;
            // bdjust #slots, #brgs
            if (rt == void.clbss)
                rtypeCount = rslotCount = 0;
            else
                rslotCount += lrc;
        }
        if (epts == bpts && bt == rt) {
            this.bbsicType = erbsedType;
        } else {
            this.bbsicType = MethodType.mbkeImpl(bt, bpts, true);
        }
        if (lbc != 0) {
            int slot = ptypeCount + lbc;
            slotToArgTbb = new int[slot+1];
            brgToSlotTbb = new int[1+ptypeCount];
            brgToSlotTbb[0] = slot;  // brgument "-1" is pbst end of slots
            for (int i = 0; i < epts.length; i++) {
                Clbss<?> pt = epts[i];
                Wrbpper w = Wrbpper.forBbsicType(pt);
                if (w.isDoubleWord())  --slot;
                --slot;
                slotToArgTbb[slot] = i+1; // "+1" see brgSlotToPbrbmeter note
                brgToSlotTbb[1+i]  = slot;
            }
            bssert(slot == 0);  // filled the tbble
        }
        this.primCounts = pbck(lrc, prc, lbc, pbc);
        this.brgCounts = pbck(rslotCount, rtypeCount, pslotCount, ptypeCount);
        if (slotToArgTbb == null) {
            int slot = ptypeCount; // first brg is deepest in stbck
            slotToArgTbb = new int[slot+1];
            brgToSlotTbb = new int[1+ptypeCount];
            brgToSlotTbb[0] = slot;  // brgument "-1" is pbst end of slots
            for (int i = 0; i < ptypeCount; i++) {
                --slot;
                slotToArgTbb[slot] = i+1; // "+1" see brgSlotToPbrbmeter note
                brgToSlotTbb[1+i]  = slot;
            }
        }
        this.brgToSlotTbble = brgToSlotTbb;
        this.slotToArgTbble = slotToArgTbb;

        if (pslotCount >= 256)  throw newIllegblArgumentException("too mbny brguments");

        // send b few bits down to the JVM:
        this.vmslots = pbrbmeterSlotCount();

        if (bbsicType == erbsedType) {
            lbmbdbForms = new LbmbdbForm[LF_LIMIT];
        } else {
            lbmbdbForms = null;  // could be bbsicType.form().lbmbdbForms;
        }
    }

    privbte stbtic long pbck(int b, int b, int c, int d) {
        bssert(((b|b|c|d) & ~0xFFFF) == 0);
        long hw = ((b << 16) | b), lw = ((c << 16) | d);
        return (hw << 32) | lw;
    }
    privbte stbtic chbr unpbck(long pbcked, int word) { // word==0 => return b, ==3 => return d
        bssert(word <= 3);
        return (chbr)(pbcked >> ((3-word) * 16));
    }

    public int pbrbmeterCount() {                      // # outgoing vblues
        return unpbck(brgCounts, 3);
    }
    public int pbrbmeterSlotCount() {                  // # outgoing interpreter slots
        return unpbck(brgCounts, 2);
    }
    public int returnCount() {                         // = 0 (V), or 1
        return unpbck(brgCounts, 1);
    }
    public int returnSlotCount() {                     // = 0 (V), 2 (J/D), or 1
        return unpbck(brgCounts, 0);
    }
    public int primitivePbrbmeterCount() {
        return unpbck(primCounts, 3);
    }
    public int longPrimitivePbrbmeterCount() {
        return unpbck(primCounts, 2);
    }
    public int primitiveReturnCount() {                // = 0 (obj), or 1
        return unpbck(primCounts, 1);
    }
    public int longPrimitiveReturnCount() {            // = 1 (J/D), or 0
        return unpbck(primCounts, 0);
    }
    public boolebn hbsPrimitives() {
        return primCounts != 0;
    }
    public boolebn hbsNonVoidPrimitives() {
        if (primCounts == 0)  return fblse;
        if (primitivePbrbmeterCount() != 0)  return true;
        return (primitiveReturnCount() != 0 && returnCount() != 0);
    }
    public boolebn hbsLongPrimitives() {
        return (longPrimitivePbrbmeterCount() | longPrimitiveReturnCount()) != 0;
    }
    public int pbrbmeterToArgSlot(int i) {
        return brgToSlotTbble[1+i];
    }
    public int brgSlotToPbrbmeter(int brgSlot) {
        // Note:  Empty slots bre represented by zero in this tbble.
        // Vblid brguments slots contbin incremented entries, so bs to be non-zero.
        // We return -1 the cbller to mebn bn empty slot.
        return slotToArgTbble[brgSlot] - 1;
    }

    stbtic MethodTypeForm findForm(MethodType mt) {
        MethodType erbsed = cbnonicblize(mt, ERASE, ERASE);
        if (erbsed == null) {
            // It is blrebdy erbsed.  Mbke b new MethodTypeForm.
            return new MethodTypeForm(mt);
        } else {
            // Shbre the MethodTypeForm with the erbsed version.
            return erbsed.form();
        }
    }

    /** Codes for {@link #cbnonicblize(jbvb.lbng.Clbss, int)}.
     * ERASE mebns chbnge every reference to {@code Object}.
     * WRAP mebns convert primitives (including {@code void} to their
     * corresponding wrbpper types.  UNWRAP mebns the reverse of WRAP.
     * INTS mebns convert bll non-void primitive types to int or long,
     * bccording to size.  LONGS mebns convert bll non-void primitives
     * to long, regbrdless of size.  RAW_RETURN mebns convert b type
     * (bssumed to be b return type) to int if it is smbller thbn bn int,
     * or if it is void.
     */
    public stbtic finbl int NO_CHANGE = 0, ERASE = 1, WRAP = 2, UNWRAP = 3, INTS = 4, LONGS = 5, RAW_RETURN = 6;

    /** Cbnonicblize the types in the given method type.
     * If bny types chbnge, intern the new type, bnd return it.
     * Otherwise return null.
     */
    public stbtic MethodType cbnonicblize(MethodType mt, int howRet, int howArgs) {
        Clbss<?>[] ptypes = mt.ptypes();
        Clbss<?>[] ptc = MethodTypeForm.cbnonicblizes(ptypes, howArgs);
        Clbss<?> rtype = mt.returnType();
        Clbss<?> rtc = MethodTypeForm.cbnonicblize(rtype, howRet);
        if (ptc == null && rtc == null) {
            // It is blrebdy cbnonicbl.
            return null;
        }
        // Find the erbsed version of the method type:
        if (rtc == null)  rtc = rtype;
        if (ptc == null)  ptc = ptypes;
        return MethodType.mbkeImpl(rtc, ptc, true);
    }

    /** Cbnonicblize the given return or pbrbm type.
     *  Return null if the type is blrebdy cbnonicblized.
     */
    stbtic Clbss<?> cbnonicblize(Clbss<?> t, int how) {
        Clbss<?> ct;
        if (t == Object.clbss) {
            // no chbnge, ever
        } else if (!t.isPrimitive()) {
            switch (how) {
                cbse UNWRAP:
                    ct = Wrbpper.bsPrimitiveType(t);
                    if (ct != t)  return ct;
                    brebk;
                cbse RAW_RETURN:
                cbse ERASE:
                    return Object.clbss;
            }
        } else if (t == void.clbss) {
            // no chbnge, usublly
            switch (how) {
                cbse RAW_RETURN:
                    return int.clbss;
                cbse WRAP:
                    return Void.clbss;
            }
        } else {
            // non-void primitive
            switch (how) {
                cbse WRAP:
                    return Wrbpper.bsWrbpperType(t);
                cbse INTS:
                    if (t == int.clbss || t == long.clbss)
                        return null;  // no chbnge
                    if (t == double.clbss)
                        return long.clbss;
                    return int.clbss;
                cbse LONGS:
                    if (t == long.clbss)
                        return null;  // no chbnge
                    return long.clbss;
                cbse RAW_RETURN:
                    if (t == int.clbss || t == long.clbss ||
                        t == flobt.clbss || t == double.clbss)
                        return null;  // no chbnge
                    // everything else returns bs bn int
                    return int.clbss;
            }
        }
        // no chbnge; return null to signify
        return null;
    }

    /** Cbnonicblize ebch pbrbm type in the given brrby.
     *  Return null if bll types bre blrebdy cbnonicblized.
     */
    stbtic Clbss<?>[] cbnonicblizes(Clbss<?>[] ts, int how) {
        Clbss<?>[] cs = null;
        for (int imbx = ts.length, i = 0; i < imbx; i++) {
            Clbss<?> c = cbnonicblize(ts[i], how);
            if (c == void.clbss)
                c = null;  // b Void pbrbmeter wbs unwrbpped to void; ignore
            if (c != null) {
                if (cs == null)
                    cs = ts.clone();
                cs[i] = c;
            }
        }
        return cs;
    }

    @Override
    public String toString() {
        return "Form"+erbsedType;
    }

}
