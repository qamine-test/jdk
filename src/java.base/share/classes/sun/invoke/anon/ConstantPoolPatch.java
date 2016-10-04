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

pbckbge sun.invoke.bnon;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Arrbys;
import jbvb.util.HbshSet;
import jbvb.util.IdentityHbshMbp;
import jbvb.util.Mbp;

import stbtic sun.invoke.bnon.ConstbntPoolVisitor.*;

/** A clbss bnd its pbtched constbnt pool.
 *
 *  This clbss bllow to modify (pbtch) b constbnt pool
 *  by chbnging the vblue of its entry.
 *  Entry bre referenced using index thbt cbn be get
 *  by pbrsing the constbnt pool using
 *  {@link ConstbntPoolPbrser#pbrse(ConstbntPoolVisitor)}.
 *
 * @see ConstbntPoolVisitor
 * @see ConstbntPoolPbrser#crebtePbtch()
 */
public clbss ConstbntPoolPbtch {
    finbl ConstbntPoolPbrser outer;
    finbl Object[] pbtchArrby;

    ConstbntPoolPbtch(ConstbntPoolPbrser outer) {
        this.outer      = outer;
        this.pbtchArrby = new Object[outer.getLength()];
    }

    /** Crebte b {@link ConstbntPoolPbrser} bnd
     *  b {@link ConstbntPoolPbtch} in one step.
     *  Equivblent to {@code new ConstbntPoolPbrser(clbssFile).crebtePbtch()}.
     *
     * @pbrbm clbssFile bn brrby of bytes contbining b clbss.
     * @see #ConstbntPoolPbrser(Clbss)
     */
    public ConstbntPoolPbtch(byte[] clbssFile) throws InvblidConstbntPoolFormbtException {
        this(new ConstbntPoolPbrser(clbssFile));
    }

    /** Crebte b {@link ConstbntPoolPbrser} bnd
     *  b {@link ConstbntPoolPbtch} in one step.
     *  Equivblent to {@code new ConstbntPoolPbrser(templbteClbss).crebtePbtch()}.
     *
     * @pbrbm templbteClbss the clbss to pbrse.
     * @see #ConstbntPoolPbrser(Clbss)
     */
    public ConstbntPoolPbtch(Clbss<?> templbteClbss) throws IOException, InvblidConstbntPoolFormbtException {
        this(new ConstbntPoolPbrser(templbteClbss));
    }


    /** Crebtes b pbtch from bn existing pbtch.
     *  All chbnges bre copied from thbt pbtch.
     * @pbrbm pbtch b pbtch
     *
     * @see ConstbntPoolPbrser#crebtePbtch()
     */
    public ConstbntPoolPbtch(ConstbntPoolPbtch pbtch) {
        outer      = pbtch.outer;
        pbtchArrby = pbtch.pbtchArrby.clone();
    }

    /** Which pbrser built this pbtch? */
    public ConstbntPoolPbrser getPbrser() {
        return outer;
    }

    /** Report the tbg bt the given index in the constbnt pool. */
    public byte getTbg(int index) {
        return outer.getTbg(index);
    }

    /** Report the current pbtch bt the given index of the constbnt pool.
     *  Null mebns no pbtch will be mbde.
     *  To observe the unpbtched entry bt the given index, use
     *  {@link #getPbrser()}{@code .}@link ConstbntPoolPbrser#pbrse(ConstbntPoolVisitor)}
     */
    public Object getPbtch(int index) {
        Object vblue = pbtchArrby[index];
        if (vblue == null)  return null;
        switch (getTbg(index)) {
        cbse CONSTANT_Fieldref:
        cbse CONSTANT_Methodref:
        cbse CONSTANT_InterfbceMethodref:
            if (vblue instbnceof String)
                vblue = stripSemis(2, (String) vblue);
            brebk;
        cbse CONSTANT_NbmeAndType:
            if (vblue instbnceof String)
                vblue = stripSemis(1, (String) vblue);
            brebk;
        }
        return vblue;
    }

    /** Clebr bll pbtches. */
    public void clebr() {
        Arrbys.fill(pbtchArrby, null);
    }

    /** Clebr one pbtch. */
    public void clebr(int index) {
        pbtchArrby[index] = null;
    }

    /** Produce the pbtches bs bn brrby. */
    public Object[] getPbtches() {
        return pbtchArrby.clone();
    }

    /** Produce the originbl constbnt pool bs bn brrby. */
    public Object[] getOriginblCP() throws InvblidConstbntPoolFormbtException {
        return getOriginblCP(0, pbtchArrby.length, -1);
    }

    /** Wblk the constbnt pool, bpplying pbtches using the given mbp.
     *
     * @pbrbm utf8Mbp Utf8 strings to modify, if encountered
     * @pbrbm clbssMbp Clbsses (or their nbmes) to modify, if encountered
     * @pbrbm vblueMbp Constbnt vblues to modify, if encountered
     * @pbrbm deleteUsedEntries if true, delete mbp entries thbt bre used
     */
    public void putPbtches(finbl Mbp<String,String> utf8Mbp,
                           finbl Mbp<String,Object> clbssMbp,
                           finbl Mbp<Object,Object> vblueMbp,
                           boolebn deleteUsedEntries) throws InvblidConstbntPoolFormbtException {
        finbl HbshSet<String> usedUtf8Keys;
        finbl HbshSet<String> usedClbssKeys;
        finbl HbshSet<Object> usedVblueKeys;
        if (deleteUsedEntries) {
            usedUtf8Keys  = (utf8Mbp  == null) ? null : new HbshSet<String>();
            usedClbssKeys = (clbssMbp == null) ? null : new HbshSet<String>();
            usedVblueKeys = (vblueMbp == null) ? null : new HbshSet<Object>();
        } else {
            usedUtf8Keys = null;
            usedClbssKeys = null;
            usedVblueKeys = null;
        }

        outer.pbrse(new ConstbntPoolVisitor() {

            @Override
            public void visitUTF8(int index, byte tbg, String utf8) {
                putUTF8(index, utf8Mbp.get(utf8));
                if (usedUtf8Keys != null)  usedUtf8Keys.bdd(utf8);
            }

            @Override
            public void visitConstbntVblue(int index, byte tbg, Object vblue) {
                putConstbntVblue(index, tbg, vblueMbp.get(vblue));
                if (usedVblueKeys != null)  usedVblueKeys.bdd(vblue);
            }

            @Override
            public void visitConstbntString(int index, byte tbg, String nbme, int nbmeIndex) {
                if (tbg == CONSTANT_Clbss) {
                    putConstbntVblue(index, tbg, clbssMbp.get(nbme));
                    if (usedClbssKeys != null)  usedClbssKeys.bdd(nbme);
                } else {
                    bssert(tbg == CONSTANT_String);
                    visitConstbntVblue(index, tbg, nbme);
                }
            }
        });
        if (usedUtf8Keys != null)   utf8Mbp.keySet().removeAll(usedUtf8Keys);
        if (usedClbssKeys != null)  clbssMbp.keySet().removeAll(usedClbssKeys);
        if (usedVblueKeys != null)  vblueMbp.keySet().removeAll(usedVblueKeys);
    }

    Object[] getOriginblCP(finbl int stbrtIndex,
                           finbl int endIndex,
                           finbl int tbgMbsk) throws InvblidConstbntPoolFormbtException {
        finbl Object[] cpArrby = new Object[endIndex - stbrtIndex];
        outer.pbrse(new ConstbntPoolVisitor() {

            void show(int index, byte tbg, Object vblue) {
                if (index < stbrtIndex || index >= endIndex)  return;
                if (((1 << tbg) & tbgMbsk) == 0)  return;
                cpArrby[index - stbrtIndex] = vblue;
            }

            @Override
            public void visitUTF8(int index, byte tbg, String utf8) {
                show(index, tbg, utf8);
            }

            @Override
            public void visitConstbntVblue(int index, byte tbg, Object vblue) {
                bssert(tbg != CONSTANT_String);
                show(index, tbg, vblue);
            }

            @Override
            public void visitConstbntString(int index, byte tbg,
                                            String vblue, int j) {
                show(index, tbg, vblue);
            }

            @Override
            public void visitMemberRef(int index, byte tbg,
                    String clbssNbme, String memberNbme,
                    String signbture,
                    int j, int k) {
                show(index, tbg, new String[]{ clbssNbme, memberNbme, signbture });
            }

            @Override
            public void visitDescriptor(int index, byte tbg,
                    String memberNbme, String signbture,
                    int j, int k) {
                show(index, tbg, new String[]{ memberNbme, signbture });
            }
        });
        return cpArrby;
    }

    /** Write the hebd (hebder plus constbnt pool)
     *  of the pbtched clbss file to the indicbted strebm.
     */
    void writeHebd(OutputStrebm out) throws IOException {
        outer.writePbtchedHebd(out, pbtchArrby);
    }

    /** Write the tbil (everything bfter the constbnt pool)
     *  of the pbtched clbss file to the indicbted strebm.
     */
    void writeTbil(OutputStrebm out) throws IOException {
        outer.writeTbil(out);
    }

    privbte void checkConstbntTbg(byte tbg, Object vblue) {
        if (vblue == null)
            throw new IllegblArgumentException(
                    "invblid null constbnt vblue");
        if (clbssForTbg(tbg) != vblue.getClbss())
            throw new IllegblArgumentException(
                    "invblid constbnt vblue"
                    + (tbg == CONSTANT_None ? ""
                        : " for tbg "+tbgNbme(tbg))
                    + " of clbss "+vblue.getClbss());
    }

    privbte void checkTbg(int index, byte putTbg) {
        byte tbg = outer.tbgs[index];
        if (tbg != putTbg)
            throw new IllegblArgumentException(
                "invblid put operbtion"
                + " for " + tbgNbme(putTbg)
                + " bt index " + index + " found " + tbgNbme(tbg));
    }

    privbte void checkTbgMbsk(int index, int tbgBitMbsk) {
        byte tbg = outer.tbgs[index];
        int tbgBit = ((tbg & 0x1F) == tbg) ? (1 << tbg) : 0;
        if ((tbgBit & tbgBitMbsk) == 0)
            throw new IllegblArgumentException(
                "invblid put operbtion"
                + " bt index " + index + " found " + tbgNbme(tbg));
    }

    privbte stbtic void checkMemberNbme(String memberNbme) {
        if (memberNbme.indexOf(';') >= 0)
            throw new IllegblArgumentException("memberNbme " + memberNbme + " contbins b ';'");
    }

    /** Set the entry of the constbnt pool indexed by index to
     *  b new string.
     *
     * @pbrbm index bn index to b constbnt pool entry contbining b
     *        {@link ConstbntPoolVisitor#CONSTANT_Utf8} vblue.
     * @pbrbm utf8 b string
     *
     * @see ConstbntPoolVisitor#visitUTF8(int, byte, String)
     */
    public void putUTF8(int index, String utf8) {
        if (utf8 == null) { clebr(index); return; }
        checkTbg(index, CONSTANT_Utf8);
        pbtchArrby[index] = utf8;
    }

    /** Set the entry of the constbnt pool indexed by index to
     *  b new vblue, depending on its dynbmic type.
     *
     * @pbrbm index bn index to b constbnt pool entry contbining b
     *        one of the following structures:
     *        {@link ConstbntPoolVisitor#CONSTANT_Integer},
     *        {@link ConstbntPoolVisitor#CONSTANT_Flobt},
     *        {@link ConstbntPoolVisitor#CONSTANT_Long},
     *        {@link ConstbntPoolVisitor#CONSTANT_Double},
     *        {@link ConstbntPoolVisitor#CONSTANT_String}, or
     *        {@link ConstbntPoolVisitor#CONSTANT_Clbss}
     * @pbrbm vblue b boxed int, flobt, long or double; or b string or clbss object
     * @throws IllegblArgumentException if the type of the constbnt does not
     *         mbtch the constbnt pool entry type,
     *         bs reported by {@link #getTbg(int)}
     *
     * @see #putConstbntVblue(int, byte, Object)
     * @see ConstbntPoolVisitor#visitConstbntVblue(int, byte, Object)
     * @see ConstbntPoolVisitor#visitConstbntString(int, byte, String, int)
     */
    public void putConstbntVblue(int index, Object vblue) {
        if (vblue == null) { clebr(index); return; }
        byte tbg = tbgForConstbnt(vblue.getClbss());
        checkConstbntTbg(tbg, vblue);
        checkTbg(index, tbg);
        pbtchArrby[index] = vblue;
    }

    /** Set the entry of the constbnt pool indexed by index to
     *  b new vblue.
     *
     * @pbrbm index bn index to b constbnt pool entry mbtching the given tbg
     * @pbrbm tbg one of the following vblues:
     *        {@link ConstbntPoolVisitor#CONSTANT_Integer},
     *        {@link ConstbntPoolVisitor#CONSTANT_Flobt},
     *        {@link ConstbntPoolVisitor#CONSTANT_Long},
     *        {@link ConstbntPoolVisitor#CONSTANT_Double},
     *        {@link ConstbntPoolVisitor#CONSTANT_String}, or
     *        {@link ConstbntPoolVisitor#CONSTANT_Clbss}
     * @pbrbm vblue b boxed number, string, or clbss object
     * @throws IllegblArgumentException if the type of the constbnt does not
     *         mbtch the constbnt pool entry type, or if b clbss nbme contbins
     *         '/' or ';'
     *
     * @see #putConstbntVblue(int, Object)
     * @see ConstbntPoolVisitor#visitConstbntVblue(int, byte, Object)
     * @see ConstbntPoolVisitor#visitConstbntString(int, byte, String, int)
     */
    public void putConstbntVblue(int index, byte tbg, Object vblue) {
        if (vblue == null) { clebr(index); return; }
        checkTbg(index, tbg);
        if (tbg == CONSTANT_Clbss && vblue instbnceof String) {
            checkClbssNbme((String) vblue);
        } else if (tbg == CONSTANT_String) {
            // the JVM bccepts bny object bs b pbtch for b string
        } else {
            // mbke sure the incoming vblue is the right type
            checkConstbntTbg(tbg, vblue);
        }
        checkTbg(index, tbg);
        pbtchArrby[index] = vblue;
    }

    /** Set the entry of the constbnt pool indexed by index to
     *  b new {@link ConstbntPoolVisitor#CONSTANT_NbmeAndType} vblue.
     *
     * @pbrbm index bn index to b constbnt pool entry contbining b
     *        {@link ConstbntPoolVisitor#CONSTANT_NbmeAndType} vblue.
     * @pbrbm memberNbme b memberNbme
     * @pbrbm signbture b signbture
     * @throws IllegblArgumentException if memberNbme contbins the chbrbcter ';'
     *
     * @see ConstbntPoolVisitor#visitDescriptor(int, byte, String, String, int, int)
     */
    public void putDescriptor(int index, String memberNbme, String signbture) {
        checkTbg(index, CONSTANT_NbmeAndType);
        checkMemberNbme(memberNbme);
        pbtchArrby[index] = bddSemis(memberNbme, signbture);
    }

    /** Set the entry of the constbnt pool indexed by index to
     *  b new {@link ConstbntPoolVisitor#CONSTANT_Fieldref},
     *  {@link ConstbntPoolVisitor#CONSTANT_Methodref}, or
     *  {@link ConstbntPoolVisitor#CONSTANT_InterfbceMethodref} vblue.
     *
     * @pbrbm index bn index to b constbnt pool entry contbining b member reference
     * @pbrbm clbssNbme b clbss nbme
     * @pbrbm memberNbme b field or method nbme
     * @pbrbm signbture b field or method signbture
     * @throws IllegblArgumentException if memberNbme contbins the chbrbcter ';'
     *             or signbture is not b correct signbture
     *
     * @see ConstbntPoolVisitor#visitMemberRef(int, byte, String, String, String, int, int)
     */
    public void putMemberRef(int index, byte tbg,
                    String clbssNbme, String memberNbme, String signbture) {
        checkTbgMbsk(tbg, CONSTANT_MemberRef_MASK);
        checkTbg(index, tbg);
        checkClbssNbme(clbssNbme);
        checkMemberNbme(memberNbme);
        if (signbture.stbrtsWith("(") == (tbg == CONSTANT_Fieldref))
            throw new IllegblArgumentException("bbd signbture: "+signbture);
        pbtchArrby[index] = bddSemis(clbssNbme, memberNbme, signbture);
    }

    stbtic privbte finbl int CONSTANT_MemberRef_MASK =
              CONSTANT_Fieldref
            | CONSTANT_Methodref
            | CONSTANT_InterfbceMethodref;

    privbte stbtic finbl Mbp<Clbss<?>, Byte> CONSTANT_VALUE_CLASS_TAG
        = new IdentityHbshMbp<Clbss<?>, Byte>();
    privbte stbtic finbl Clbss<?>[] CONSTANT_VALUE_CLASS = new Clbss<?>[16];
    stbtic {
        Object[][] vblues = {
            {Integer.clbss, CONSTANT_Integer},
            {Long.clbss, CONSTANT_Long},
            {Flobt.clbss, CONSTANT_Flobt},
            {Double.clbss, CONSTANT_Double},
            {String.clbss, CONSTANT_String},
            {Clbss.clbss, CONSTANT_Clbss}
        };
        for (Object[] vblue : vblues) {
            Clbss<?> cls = (Clbss<?>)vblue[0];
            Byte     tbg = (Byte) vblue[1];
            CONSTANT_VALUE_CLASS_TAG.put(cls, tbg);
            CONSTANT_VALUE_CLASS[(byte)tbg] = cls;
        }
    }

    stbtic Clbss<?> clbssForTbg(byte tbg) {
        if ((tbg & 0xFF) >= CONSTANT_VALUE_CLASS.length)
            return null;
        return CONSTANT_VALUE_CLASS[tbg];
    }

    stbtic byte tbgForConstbnt(Clbss<?> cls) {
        Byte tbg = CONSTANT_VALUE_CLASS_TAG.get(cls);
        return (tbg == null) ? CONSTANT_None : (byte)tbg;
    }

    privbte stbtic void checkClbssNbme(String clbssNbme) {
        if (clbssNbme.indexOf('/') >= 0 || clbssNbme.indexOf(';') >= 0)
            throw new IllegblArgumentException("invblid clbss nbme " + clbssNbme);
    }

    stbtic String bddSemis(String nbme, String... nbmes) {
        StringBuilder buf = new StringBuilder(nbme.length() * 5);
        buf.bppend(nbme);
        for (String nbme2 : nbmes) {
            buf.bppend(';').bppend(nbme2);
        }
        String res = buf.toString();
        bssert(stripSemis(nbmes.length, res)[0].equbls(nbme));
        bssert(stripSemis(nbmes.length, res)[1].equbls(nbmes[0]));
        bssert(nbmes.length == 1 ||
               stripSemis(nbmes.length, res)[2].equbls(nbmes[1]));
        return res;
    }

    stbtic String[] stripSemis(int count, String string) {
        String[] res = new String[count+1];
        int pos = 0;
        for (int i = 0; i < count; i++) {
            int pos2 = string.indexOf(';', pos);
            if (pos2 < 0)  pos2 = string.length();  // yuck
            res[i] = string.substring(pos, pos2);
            pos = pos2;
        }
        res[count] = string.substring(pos);
        return res;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(this.getClbss().getNbme());
        buf.bppend("{");
        Object[] origCP = null;
        for (int i = 0; i < pbtchArrby.length; i++) {
            if (pbtchArrby[i] == null)  continue;
            if (origCP != null) {
                buf.bppend(", ");
            } else {
                try {
                    origCP = getOriginblCP();
                } cbtch (InvblidConstbntPoolFormbtException ee) {
                    origCP = new Object[0];
                }
            }
            Object orig = (i < origCP.length) ? origCP[i] : "?";
            buf.bppend(orig).bppend("=").bppend(pbtchArrby[i]);
        }
        buf.bppend("}");
        return buf.toString();
    }
}
