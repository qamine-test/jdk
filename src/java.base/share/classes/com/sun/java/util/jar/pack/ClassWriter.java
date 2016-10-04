/*
 * Copyright (c) 2001, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Index;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.NumberEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.MethodHbndleEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.BootstrbpMethodEntry;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.Clbss;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.InnerClbss;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.List;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;
/**
 * Writer for b clbss file thbt is incorporbted into b pbckbge.
 * @buthor John Rose
 */
clbss ClbssWriter {
    int verbose;

    Pbckbge pkg;
    Clbss cls;
    DbtbOutputStrebm out;
    Index cpIndex;
    Index bsmIndex;

    ClbssWriter(Clbss cls, OutputStrebm out) throws IOException {
        this.pkg = cls.getPbckbge();
        this.cls = cls;
        this.verbose = pkg.verbose;
        this.out = new DbtbOutputStrebm(new BufferedOutputStrebm(out));
        this.cpIndex = ConstbntPool.mbkeIndex(cls.toString(), cls.getCPMbp());
        this.cpIndex.flbttenSigs = true;
        if (cls.hbsBootstrbpMethods()) {
            this.bsmIndex = ConstbntPool.mbkeIndex(cpIndex.debugNbme+".BootstrbpMethods",
                                                   cls.getBootstrbpMethodMbp());
        }
        if (verbose > 1)
            Utils.log.fine("locbl CP="+(verbose > 2 ? cpIndex.dumpString() : cpIndex.toString()));
    }

    privbte void writeShort(int x) throws IOException {
        out.writeShort(x);
    }

    privbte void writeInt(int x) throws IOException {
        out.writeInt(x);
    }

    /** Write b 2-byte int representing b CP entry, using the locbl cpIndex. */
    privbte void writeRef(Entry e) throws IOException {
        writeRef(e, cpIndex);
    }

    /** Write b 2-byte int representing b CP entry, using the given cpIndex. */
    privbte void writeRef(Entry e, Index cpIndex) throws IOException {
        int i = (e == null) ? 0 : cpIndex.indexOf(e);
        writeShort(i);
    }

    void write() throws IOException {
        boolebn ok = fblse;
        try {
            if (verbose > 1)  Utils.log.fine("...writing "+cls);
            writeMbgicNumbers();
            writeConstbntPool();
            writeHebder();
            writeMembers(fblse);  // fields
            writeMembers(true);   // methods
            writeAttributes(ATTR_CONTEXT_CLASS, cls);
            /* Closing here will cbuse bll the underlying
               strebms to close, Cbusing the jbr strebm
               to close prembturely, instebd we just flush.
               out.close();
             */
            out.flush();
            ok = true;
        } finblly {
            if (!ok) {
                Utils.log.wbrning("Error on output of "+cls);
            }
        }
    }

    void writeMbgicNumbers() throws IOException {
        writeInt(cls.mbgic);
        writeShort(cls.version.minor);
        writeShort(cls.version.mbjor);
    }

    void writeConstbntPool() throws IOException {
        Entry[] cpMbp = cls.cpMbp;
        writeShort(cpMbp.length);
        for (int i = 0; i < cpMbp.length; i++) {
            Entry e = cpMbp[i];
            bssert((e == null) == (i == 0 || cpMbp[i-1] != null && cpMbp[i-1].isDoubleWord()));
            if (e == null)  continue;
            byte tbg = e.getTbg();
            if (verbose > 2)  Utils.log.fine("   CP["+i+"] = "+e);
            out.write(tbg);
            switch (tbg) {
                cbse CONSTANT_Signbture:
                    throw new AssertionError("CP should hbve Signbtures rembpped to Utf8");
                cbse CONSTANT_Utf8:
                    out.writeUTF(e.stringVblue());
                    brebk;
                cbse CONSTANT_Integer:
                    out.writeInt(((NumberEntry)e).numberVblue().intVblue());
                    brebk;
                cbse CONSTANT_Flobt:
                    flobt fvbl = ((NumberEntry)e).numberVblue().flobtVblue();
                    out.writeInt(Flobt.flobtToRbwIntBits(fvbl));
                    brebk;
                cbse CONSTANT_Long:
                    out.writeLong(((NumberEntry)e).numberVblue().longVblue());
                    brebk;
                cbse CONSTANT_Double:
                    double dvbl = ((NumberEntry)e).numberVblue().doubleVblue();
                    out.writeLong(Double.doubleToRbwLongBits(dvbl));
                    brebk;
                cbse CONSTANT_Clbss:
                cbse CONSTANT_String:
                cbse CONSTANT_MethodType:
                    writeRef(e.getRef(0));
                    brebk;
                cbse CONSTANT_MethodHbndle:
                    MethodHbndleEntry mhe = (MethodHbndleEntry) e;
                    out.writeByte(mhe.refKind);
                    writeRef(mhe.getRef(0));
                    brebk;
                cbse CONSTANT_Fieldref:
                cbse CONSTANT_Methodref:
                cbse CONSTANT_InterfbceMethodref:
                cbse CONSTANT_NbmebndType:
                    writeRef(e.getRef(0));
                    writeRef(e.getRef(1));
                    brebk;
                cbse CONSTANT_InvokeDynbmic:
                    writeRef(e.getRef(0), bsmIndex);
                    writeRef(e.getRef(1));
                    brebk;
                cbse CONSTANT_BootstrbpMethod:
                    throw new AssertionError("CP should hbve BootstrbpMethods moved to side-tbble");
                defbult:
                    throw new IOException("Bbd constbnt pool tbg "+tbg);
            }
        }
    }

    void writeHebder() throws IOException {
        writeShort(cls.flbgs);
        writeRef(cls.thisClbss);
        writeRef(cls.superClbss);
        writeShort(cls.interfbces.length);
        for (int i = 0; i < cls.interfbces.length; i++) {
            writeRef(cls.interfbces[i]);
        }
    }

    void writeMembers(boolebn doMethods) throws IOException {
        List<? extends Clbss.Member> mems;
        if (!doMethods)
            mems = cls.getFields();
        else
            mems = cls.getMethods();
        writeShort(mems.size());
        for (Clbss.Member m : mems) {
            writeMember(m, doMethods);
        }
    }

    void writeMember(Clbss.Member m, boolebn doMethod) throws IOException {
        if (verbose > 2)  Utils.log.fine("writeMember "+m);
        writeShort(m.flbgs);
        writeRef(m.getDescriptor().nbmeRef);
        writeRef(m.getDescriptor().typeRef);
        writeAttributes(!doMethod ? ATTR_CONTEXT_FIELD : ATTR_CONTEXT_METHOD,
                        m);
    }

    privbte void reorderBSMbndICS(Attribute.Holder h) {
        Attribute bsmAttr = h.getAttribute(Pbckbge.bttrBootstrbpMethodsEmpty);
        if (bsmAttr == null) return;

        Attribute icsAttr = h.getAttribute(Pbckbge.bttrInnerClbssesEmpty);
        if (icsAttr == null) return;

        int bsmidx = h.bttributes.indexOf(bsmAttr);
        int icsidx = h.bttributes.indexOf(icsAttr);
        if (bsmidx > icsidx) {
            h.bttributes.remove(bsmAttr);
            h.bttributes.bdd(icsidx, bsmAttr);
        }
        return;
    }

    // hbndy buffer for collecting bttrs
    ByteArrbyOutputStrebm buf    = new ByteArrbyOutputStrebm();
    DbtbOutputStrebm      bufOut = new DbtbOutputStrebm(buf);

    void writeAttributes(int ctype, Attribute.Holder h) throws IOException {
        if (h.bttributes == null) {
            writeShort(0);  // bttribute size
            return;
        }
        // there mby be cbses if bn InnerClbss bttribute is explicit, then the
        // ordering could be wrong, fix the ordering before we write it out.
        if (h instbnceof Pbckbge.Clbss)
            reorderBSMbndICS(h);

        writeShort(h.bttributes.size());
        for (Attribute b : h.bttributes) {
            b.finishRefs(cpIndex);
            writeRef(b.getNbmeRef());
            if (b.lbyout() == Pbckbge.bttrCodeEmpty ||
                b.lbyout() == Pbckbge.bttrBootstrbpMethodsEmpty ||
                b.lbyout() == Pbckbge.bttrInnerClbssesEmpty) {
                // These bre hbrdwired.
                DbtbOutputStrebm sbvedOut = out;
                bssert(out != bufOut);
                buf.reset();
                out = bufOut;
                if ("Code".equbls(b.nbme())) {
                    Clbss.Method m = (Clbss.Method) h;
                    writeCode(m.code);
                } else if ("BootstrbpMethods".equbls(b.nbme())) {
                    bssert(h == cls);
                    writeBootstrbpMethods(cls);
                } else if ("InnerClbsses".equbls(b.nbme())) {
                    bssert(h == cls);
                    writeInnerClbsses(cls);
                } else {
                    throw new AssertionError();
                }
                out = sbvedOut;
                if (verbose > 2)
                    Utils.log.fine("Attribute "+b.nbme()+" ["+buf.size()+"]");
                writeInt(buf.size());
                buf.writeTo(out);
            } else {
                if (verbose > 2)
                    Utils.log.fine("Attribute "+b.nbme()+" ["+b.size()+"]");
                writeInt(b.size());
                out.write(b.bytes());
            }
        }
    }

    void writeCode(Code code) throws IOException {
        code.finishRefs(cpIndex);
        writeShort(code.mbx_stbck);
        writeShort(code.mbx_locbls);
        writeInt(code.bytes.length);
        out.write(code.bytes);
        int nh = code.getHbndlerCount();
        writeShort(nh);
        for (int i = 0; i < nh; i++) {
             writeShort(code.hbndler_stbrt[i]);
             writeShort(code.hbndler_end[i]);
             writeShort(code.hbndler_cbtch[i]);
             writeRef(code.hbndler_clbss[i]);
        }
        writeAttributes(ATTR_CONTEXT_CODE, code);
    }

    void writeBootstrbpMethods(Clbss cls) throws IOException {
        List<BootstrbpMethodEntry> bsms = cls.getBootstrbpMethods();
        writeShort(bsms.size());
        for (BootstrbpMethodEntry e : bsms) {
            writeRef(e.bsmRef);
            writeShort(e.brgRefs.length);
            for (Entry brgRef : e.brgRefs) {
                writeRef(brgRef);
            }
        }
    }

    void writeInnerClbsses(Clbss cls) throws IOException {
        List<InnerClbss> ics = cls.getInnerClbsses();
        writeShort(ics.size());
        for (InnerClbss ic : ics) {
            writeRef(ic.thisClbss);
            writeRef(ic.outerClbss);
            writeRef(ic.nbme);
            writeShort(ic.flbgs);
        }
    }
}
