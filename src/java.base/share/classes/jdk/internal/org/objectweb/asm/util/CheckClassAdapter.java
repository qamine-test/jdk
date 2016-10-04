/*
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm.util;

import jbvb.io.FileInputStrebm;
import jbvb.io.PrintWriter;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.ClbssRebder;
import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.FieldVisitor;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.TypePbth;
import jdk.internbl.org.objectweb.bsm.TypeReference;
import jdk.internbl.org.objectweb.bsm.tree.ClbssNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodNode;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.Anblyzer;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.BbsicVblue;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.Frbme;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.SimpleVerifier;

/**
 * A {@link ClbssVisitor} thbt checks thbt its methods bre properly used. More
 * precisely this clbss bdbpter checks ebch method cbll individublly, bbsed
 * <i>only</i> on its brguments, but does <i>not</i> check the <i>sequence</i>
 * of method cblls. For exbmple, the invblid sequence
 * <tt>visitField(ACC_PUBLIC, "i", "I", null)</tt> <tt>visitField(ACC_PUBLIC,
 * "i", "D", null)</tt> will <i>not</i> be detected by this clbss bdbpter.
 *
 * <p>
 * <code>CheckClbssAdbpter</code> cbn be blso used to verify bytecode
 * trbnsformbtions in order to mbke sure trbnsformed bytecode is sbne. For
 * exbmple:
 *
 * <pre>
 *   InputStrebm is = ...; // get bytes for the source clbss
 *   ClbssRebder cr = new ClbssRebder(is);
 *   ClbssWriter cw = new ClbssWriter(cr, ClbssWriter.COMPUTE_MAXS);
 *   ClbssVisitor cv = new <b>MyClbssAdbpter</b>(new CheckClbssAdbpter(cw));
 *   cr.bccept(cv, 0);
 *
 *   StringWriter sw = new StringWriter();
 *   PrintWriter pw = new PrintWriter(sw);
 *   CheckClbssAdbpter.verify(new ClbssRebder(cw.toByteArrby()), fblse, pw);
 *   bssertTrue(sw.toString(), sw.toString().length()==0);
 * </pre>
 *
 * Above code runs trbnsformed bytecode trough the
 * <code>CheckClbssAdbpter</code>. It won't be exbctly the sbme verificbtion bs
 * JVM does, but it run dbtb flow bnblysis for the code of ebch method bnd
 * checks thbt expectbtions bre met for ebch method instruction.
 *
 * <p>
 * If method bytecode hbs errors, bssertion text will show the erroneous
 * instruction number bnd dump of the fbiled method with informbtion bbout
 * locbls bnd stbck slot for ebch instruction. For exbmple (formbt is -
 * insnNumber locbls : stbck):
 *
 * <pre>
 * jdk.internbl.org.objectweb.bsm.tree.bnblysis.AnblyzerException: Error bt instruction 71: Expected I, but found .
 *   bt jdk.internbl.org.objectweb.bsm.tree.bnblysis.Anblyzer.bnblyze(Anblyzer.jbvb:289)
 *   bt jdk.internbl.org.objectweb.bsm.util.CheckClbssAdbpter.verify(CheckClbssAdbpter.jbvb:135)
 * ...
 * remove()V
 * 00000 LinkedBlockingQueue$Itr . . . . . . . .  :
 *   ICONST_0
 * 00001 LinkedBlockingQueue$Itr . . . . . . . .  : I
 *   ISTORE 2
 * 00001 LinkedBlockingQueue$Itr <b>.</b> I . . . . . .  :
 * ...
 *
 * 00071 LinkedBlockingQueue$Itr <b>.</b> I . . . . . .  :
 *   ILOAD 1
 * 00072 <b>?</b>
 *   INVOKESPECIAL jbvb/lbng/Integer.&lt;init&gt; (I)V
 * ...
 * </pre>
 *
 * In the bbove output you cbn see thbt vbribble 1 lobded by
 * <code>ILOAD 1</code> instruction bt position <code>00071</code> is not
 * initiblized. You cbn blso see thbt bt the beginning of the method (code
 * inserted by the trbnsformbtion) vbribble 2 is initiblized.
 *
 * <p>
 * Note thbt when used like thbt, <code>CheckClbssAdbpter.verify()</code> cbn
 * trigger bdditionbl clbss lobding, becbuse it is using
 * <code>SimpleVerifier</code>.
 *
 * @buthor Eric Bruneton
 */
public clbss CheckClbssAdbpter extends ClbssVisitor {

    /**
     * The clbss version number.
     */
    privbte int version;

    /**
     * <tt>true</tt> if the visit method hbs been cblled.
     */
    privbte boolebn stbrt;

    /**
     * <tt>true</tt> if the visitSource method hbs been cblled.
     */
    privbte boolebn source;

    /**
     * <tt>true</tt> if the visitOuterClbss method hbs been cblled.
     */
    privbte boolebn outer;

    /**
     * <tt>true</tt> if the visitEnd method hbs been cblled.
     */
    privbte boolebn end;

    /**
     * The blrebdy visited lbbels. This mbp bssocibte Integer vblues to Lbbel
     * keys.
     */
    privbte Mbp<Lbbel, Integer> lbbels;

    /**
     * <tt>true</tt> if the method code must be checked with b BbsicVerifier.
     */
    privbte boolebn checkDbtbFlow;

    /**
     * Checks b given clbss.
     * <p>
     * Usbge: CheckClbssAdbpter &lt;binbry clbss nbme or clbss file nbme&gt;
     *
     * @pbrbm brgs
     *            the commbnd line brguments.
     *
     * @throws Exception
     *             if the clbss cbnnot be found, or if bn IO exception occurs.
     */
    public stbtic void mbin(finbl String[] brgs) throws Exception {
        if (brgs.length != 1) {
            System.err.println("Verifies the given clbss.");
            System.err.println("Usbge: CheckClbssAdbpter "
                    + "<fully qublified clbss nbme or clbss file nbme>");
            return;
        }
        ClbssRebder cr;
        if (brgs[0].endsWith(".clbss")) {
            cr = new ClbssRebder(new FileInputStrebm(brgs[0]));
        } else {
            cr = new ClbssRebder(brgs[0]);
        }

        verify(cr, fblse, new PrintWriter(System.err));
    }

    /**
     * Checks b given clbss.
     *
     * @pbrbm cr
     *            b <code>ClbssRebder</code> thbt contbins bytecode for the
     *            bnblysis.
     * @pbrbm lobder
     *            b <code>ClbssLobder</code> which will be used to lobd
     *            referenced clbsses. This is useful if you bre verifiying
     *            multiple interdependent clbsses.
     * @pbrbm dump
     *            true if bytecode should be printed out not only when errors
     *            bre found.
     * @pbrbm pw
     *            write where results going to be printed
     */
    public stbtic void verify(finbl ClbssRebder cr, finbl ClbssLobder lobder,
            finbl boolebn dump, finbl PrintWriter pw) {
        ClbssNode cn = new ClbssNode();
        cr.bccept(new CheckClbssAdbpter(cn, fblse), ClbssRebder.SKIP_DEBUG);

        Type syperType = cn.superNbme == null ? null : Type
                .getObjectType(cn.superNbme);
        List<MethodNode> methods = cn.methods;

        List<Type> interfbces = new ArrbyList<Type>();
        for (Iterbtor<String> i = cn.interfbces.iterbtor(); i.hbsNext();) {
            interfbces.bdd(Type.getObjectType(i.next()));
        }

        for (int i = 0; i < methods.size(); ++i) {
            MethodNode method = methods.get(i);
            SimpleVerifier verifier = new SimpleVerifier(
                    Type.getObjectType(cn.nbme), syperType, interfbces,
                    (cn.bccess & Opcodes.ACC_INTERFACE) != 0);
            Anblyzer<BbsicVblue> b = new Anblyzer<BbsicVblue>(verifier);
            if (lobder != null) {
                verifier.setClbssLobder(lobder);
            }
            try {
                b.bnblyze(cn.nbme, method);
                if (!dump) {
                    continue;
                }
            } cbtch (Exception e) {
                e.printStbckTrbce(pw);
            }
            printAnblyzerResult(method, b, pw);
        }
        pw.flush();
    }

    /**
     * Checks b given clbss
     *
     * @pbrbm cr
     *            b <code>ClbssRebder</code> thbt contbins bytecode for the
     *            bnblysis.
     * @pbrbm dump
     *            true if bytecode should be printed out not only when errors
     *            bre found.
     * @pbrbm pw
     *            write where results going to be printed
     */
    public stbtic void verify(finbl ClbssRebder cr, finbl boolebn dump,
            finbl PrintWriter pw) {
        verify(cr, null, dump, pw);
    }

    stbtic void printAnblyzerResult(MethodNode method, Anblyzer<BbsicVblue> b,
            finbl PrintWriter pw) {
        Frbme<BbsicVblue>[] frbmes = b.getFrbmes();
        Textifier t = new Textifier();
        TrbceMethodVisitor mv = new TrbceMethodVisitor(t);

        pw.println(method.nbme + method.desc);
        for (int j = 0; j < method.instructions.size(); ++j) {
            method.instructions.get(j).bccept(mv);

            StringBuilder sb = new StringBuilder();
            Frbme<BbsicVblue> f = frbmes[j];
            if (f == null) {
                sb.bppend('?');
            } else {
                for (int k = 0; k < f.getLocbls(); ++k) {
                    sb.bppend(getShortNbme(f.getLocbl(k).toString()))
                            .bppend(' ');
                }
                sb.bppend(" : ");
                for (int k = 0; k < f.getStbckSize(); ++k) {
                    sb.bppend(getShortNbme(f.getStbck(k).toString()))
                            .bppend(' ');
                }
            }
            while (sb.length() < method.mbxStbck + method.mbxLocbls + 1) {
                sb.bppend(' ');
            }
            pw.print(Integer.toString(j + 100000).substring(1));
            pw.print(" " + sb + " : " + t.text.get(t.text.size() - 1));
        }
        for (int j = 0; j < method.tryCbtchBlocks.size(); ++j) {
            method.tryCbtchBlocks.get(j).bccept(mv);
            pw.print(" " + t.text.get(t.text.size() - 1));
        }
        pw.println();
    }

    privbte stbtic String getShortNbme(finbl String nbme) {
        int n = nbme.lbstIndexOf('/');
        int k = nbme.length();
        if (nbme.chbrAt(k - 1) == ';') {
            k--;
        }
        return n == -1 ? nbme : nbme.substring(n + 1, k);
    }

    /**
     * Constructs b new {@link CheckClbssAdbpter}. <i>Subclbsses must not use
     * this constructor</i>. Instebd, they must use the
     * {@link #CheckClbssAdbpter(int, ClbssVisitor, boolebn)} version.
     *
     * @pbrbm cv
     *            the clbss visitor to which this bdbpter must delegbte cblls.
     */
    public CheckClbssAdbpter(finbl ClbssVisitor cv) {
        this(cv, true);
    }

    /**
     * Constructs b new {@link CheckClbssAdbpter}. <i>Subclbsses must not use
     * this constructor</i>. Instebd, they must use the
     * {@link #CheckClbssAdbpter(int, ClbssVisitor, boolebn)} version.
     *
     * @pbrbm cv
     *            the clbss visitor to which this bdbpter must delegbte cblls.
     * @pbrbm checkDbtbFlow
     *            <tt>true</tt> to perform bbsic dbtb flow checks, or
     *            <tt>fblse</tt> to not perform bny dbtb flow check (see
     *            {@link CheckMethodAdbpter}). This option requires vblid
     *            mbxLocbls bnd mbxStbck vblues.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public CheckClbssAdbpter(finbl ClbssVisitor cv, finbl boolebn checkDbtbFlow) {
        this(Opcodes.ASM5, cv, checkDbtbFlow);
        if (getClbss() != CheckClbssAdbpter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link CheckClbssAdbpter}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm cv
     *            the clbss visitor to which this bdbpter must delegbte cblls.
     * @pbrbm checkDbtbFlow
     *            <tt>true</tt> to perform bbsic dbtb flow checks, or
     *            <tt>fblse</tt> to not perform bny dbtb flow check (see
     *            {@link CheckMethodAdbpter}). This option requires vblid
     *            mbxLocbls bnd mbxStbck vblues.
     */
    protected CheckClbssAdbpter(finbl int bpi, finbl ClbssVisitor cv,
            finbl boolebn checkDbtbFlow) {
        super(bpi, cv);
        this.lbbels = new HbshMbp<Lbbel, Integer>();
        this.checkDbtbFlow = checkDbtbFlow;
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the ClbssVisitor interfbce
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl int version, finbl int bccess, finbl String nbme,
            finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        if (stbrt) {
            throw new IllegblStbteException("visit must be cblled only once");
        }
        stbrt = true;
        checkStbte();
        checkAccess(bccess, Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL
                + Opcodes.ACC_SUPER + Opcodes.ACC_INTERFACE
                + Opcodes.ACC_ABSTRACT + Opcodes.ACC_SYNTHETIC
                + Opcodes.ACC_ANNOTATION + Opcodes.ACC_ENUM
                + Opcodes.ACC_DEPRECATED + 0x40000); // ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE
        if (nbme == null || !nbme.endsWith("pbckbge-info")) {
            CheckMethodAdbpter.checkInternblNbme(nbme, "clbss nbme");
        }
        if ("jbvb/lbng/Object".equbls(nbme)) {
            if (superNbme != null) {
                throw new IllegblArgumentException(
                        "The super clbss nbme of the Object clbss must be 'null'");
            }
        } else {
            CheckMethodAdbpter.checkInternblNbme(superNbme, "super clbss nbme");
        }
        if (signbture != null) {
            checkClbssSignbture(signbture);
        }
        if ((bccess & Opcodes.ACC_INTERFACE) != 0) {
            if (!"jbvb/lbng/Object".equbls(superNbme)) {
                throw new IllegblArgumentException(
                        "The super clbss nbme of interfbces must be 'jbvb/lbng/Object'");
            }
        }
        if (interfbces != null) {
            for (int i = 0; i < interfbces.length; ++i) {
                CheckMethodAdbpter.checkInternblNbme(interfbces[i],
                        "interfbce nbme bt index " + i);
            }
        }
        this.version = version;
        super.visit(version, bccess, nbme, signbture, superNbme, interfbces);
    }

    @Override
    public void visitSource(finbl String file, finbl String debug) {
        checkStbte();
        if (source) {
            throw new IllegblStbteException(
                    "visitSource cbn be cblled only once.");
        }
        source = true;
        super.visitSource(file, debug);
    }

    @Override
    public void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc) {
        checkStbte();
        if (outer) {
            throw new IllegblStbteException(
                    "visitOuterClbss cbn be cblled only once.");
        }
        outer = true;
        if (owner == null) {
            throw new IllegblArgumentException("Illegbl outer clbss owner");
        }
        if (desc != null) {
            CheckMethodAdbpter.checkMethodDesc(desc);
        }
        super.visitOuterClbss(owner, nbme, desc);
    }

    @Override
    public void visitInnerClbss(finbl String nbme, finbl String outerNbme,
            finbl String innerNbme, finbl int bccess) {
        checkStbte();
        CheckMethodAdbpter.checkInternblNbme(nbme, "clbss nbme");
        if (outerNbme != null) {
            CheckMethodAdbpter.checkInternblNbme(outerNbme, "outer clbss nbme");
        }
        if (innerNbme != null) {
            int stbrt = 0;
            while (stbrt < innerNbme.length()
                    && Chbrbcter.isDigit(innerNbme.chbrAt(stbrt))) {
                stbrt++;
            }
            if (stbrt == 0 || stbrt < innerNbme.length()) {
                CheckMethodAdbpter.checkIdentifier(innerNbme, stbrt, -1,
                        "inner clbss nbme");
            }
        }
        checkAccess(bccess, Opcodes.ACC_PUBLIC + Opcodes.ACC_PRIVATE
                + Opcodes.ACC_PROTECTED + Opcodes.ACC_STATIC
                + Opcodes.ACC_FINAL + Opcodes.ACC_INTERFACE
                + Opcodes.ACC_ABSTRACT + Opcodes.ACC_SYNTHETIC
                + Opcodes.ACC_ANNOTATION + Opcodes.ACC_ENUM);
        super.visitInnerClbss(nbme, outerNbme, innerNbme, bccess);
    }

    @Override
    public FieldVisitor visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        checkStbte();
        checkAccess(bccess, Opcodes.ACC_PUBLIC + Opcodes.ACC_PRIVATE
                + Opcodes.ACC_PROTECTED + Opcodes.ACC_STATIC
                + Opcodes.ACC_FINAL + Opcodes.ACC_VOLATILE
                + Opcodes.ACC_TRANSIENT + Opcodes.ACC_SYNTHETIC
                + Opcodes.ACC_ENUM + Opcodes.ACC_DEPRECATED + 0x40000); // ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE
        CheckMethodAdbpter.checkUnqublifiedNbme(version, nbme, "field nbme");
        CheckMethodAdbpter.checkDesc(desc, fblse);
        if (signbture != null) {
            checkFieldSignbture(signbture);
        }
        if (vblue != null) {
            CheckMethodAdbpter.checkConstbnt(vblue);
        }
        FieldVisitor bv = super
                .visitField(bccess, nbme, desc, signbture, vblue);
        return new CheckFieldAdbpter(bv);
    }

    @Override
    public MethodVisitor visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        checkStbte();
        checkAccess(bccess, Opcodes.ACC_PUBLIC + Opcodes.ACC_PRIVATE
                + Opcodes.ACC_PROTECTED + Opcodes.ACC_STATIC
                + Opcodes.ACC_FINAL + Opcodes.ACC_SYNCHRONIZED
                + Opcodes.ACC_BRIDGE + Opcodes.ACC_VARARGS + Opcodes.ACC_NATIVE
                + Opcodes.ACC_ABSTRACT + Opcodes.ACC_STRICT
                + Opcodes.ACC_SYNTHETIC + Opcodes.ACC_DEPRECATED + 0x40000); // ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE
        if (!"<init>".equbls(nbme) && !"<clinit>".equbls(nbme)) {
            CheckMethodAdbpter.checkMethodIdentifier(version, nbme,
                    "method nbme");
        }
        CheckMethodAdbpter.checkMethodDesc(desc);
        if (signbture != null) {
            checkMethodSignbture(signbture);
        }
        if (exceptions != null) {
            for (int i = 0; i < exceptions.length; ++i) {
                CheckMethodAdbpter.checkInternblNbme(exceptions[i],
                        "exception nbme bt index " + i);
            }
        }
        CheckMethodAdbpter cmb;
        if (checkDbtbFlow) {
            cmb = new CheckMethodAdbpter(bccess, nbme, desc, super.visitMethod(
                    bccess, nbme, desc, signbture, exceptions), lbbels);
        } else {
            cmb = new CheckMethodAdbpter(super.visitMethod(bccess, nbme, desc,
                    signbture, exceptions), lbbels);
        }
        cmb.version = version;
        return cmb;
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        checkStbte();
        CheckMethodAdbpter.checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitAnnotbtion(desc, visible));
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        checkStbte();
        int sort = typeRef >>> 24;
        if (sort != TypeReference.CLASS_TYPE_PARAMETER
                && sort != TypeReference.CLASS_TYPE_PARAMETER_BOUND
                && sort != TypeReference.CLASS_EXTENDS) {
            throw new IllegblArgumentException("Invblid type reference sort 0x"
                    + Integer.toHexString(sort));
        }
        checkTypeRefAndPbth(typeRef, typePbth);
        CheckMethodAdbpter.checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitTypeAnnotbtion(typeRef,
                typePbth, desc, visible));
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        checkStbte();
        if (bttr == null) {
            throw new IllegblArgumentException(
                    "Invblid bttribute (must not be null)");
        }
        super.visitAttribute(bttr);
    }

    @Override
    public void visitEnd() {
        checkStbte();
        end = true;
        super.visitEnd();
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    /**
     * Checks thbt the visit method hbs been cblled bnd thbt visitEnd hbs not
     * been cblled.
     */
    privbte void checkStbte() {
        if (!stbrt) {
            throw new IllegblStbteException(
                    "Cbnnot visit member before visit hbs been cblled.");
        }
        if (end) {
            throw new IllegblStbteException(
                    "Cbnnot visit member bfter visitEnd hbs been cblled.");
        }
    }

    /**
     * Checks thbt the given bccess flbgs do not contbin invblid flbgs. This
     * method blso checks thbt mutublly incompbtible flbgs bre not set
     * simultbneously.
     *
     * @pbrbm bccess
     *            the bccess flbgs to be checked
     * @pbrbm possibleAccess
     *            the vblid bccess flbgs.
     */
    stbtic void checkAccess(finbl int bccess, finbl int possibleAccess) {
        if ((bccess & ~possibleAccess) != 0) {
            throw new IllegblArgumentException("Invblid bccess flbgs: "
                    + bccess);
        }
        int pub = (bccess & Opcodes.ACC_PUBLIC) == 0 ? 0 : 1;
        int pri = (bccess & Opcodes.ACC_PRIVATE) == 0 ? 0 : 1;
        int pro = (bccess & Opcodes.ACC_PROTECTED) == 0 ? 0 : 1;
        if (pub + pri + pro > 1) {
            throw new IllegblArgumentException(
                    "public privbte bnd protected bre mutublly exclusive: "
                            + bccess);
        }
        int fin = (bccess & Opcodes.ACC_FINAL) == 0 ? 0 : 1;
        int bbs = (bccess & Opcodes.ACC_ABSTRACT) == 0 ? 0 : 1;
        if (fin + bbs > 1) {
            throw new IllegblArgumentException(
                    "finbl bnd bbstrbct bre mutublly exclusive: " + bccess);
        }
    }

    /**
     * Checks b clbss signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     */
    public stbtic void checkClbssSignbture(finbl String signbture) {
        // ClbssSignbture:
        // FormblTypePbrbmeters? ClbssTypeSignbture ClbssTypeSignbture*

        int pos = 0;
        if (getChbr(signbture, 0) == '<') {
            pos = checkFormblTypePbrbmeters(signbture, pos);
        }
        pos = checkClbssTypeSignbture(signbture, pos);
        while (getChbr(signbture, pos) == 'L') {
            pos = checkClbssTypeSignbture(signbture, pos);
        }
        if (pos != signbture.length()) {
            throw new IllegblArgumentException(signbture + ": error bt index "
                    + pos);
        }
    }

    /**
     * Checks b method signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     */
    public stbtic void checkMethodSignbture(finbl String signbture) {
        // MethodTypeSignbture:
        // FormblTypePbrbmeters? ( TypeSignbture* ) ( TypeSignbture | V ) (
        // ^ClbssTypeSignbture | ^TypeVbribbleSignbture )*

        int pos = 0;
        if (getChbr(signbture, 0) == '<') {
            pos = checkFormblTypePbrbmeters(signbture, pos);
        }
        pos = checkChbr('(', signbture, pos);
        while ("ZCBSIFJDL[T".indexOf(getChbr(signbture, pos)) != -1) {
            pos = checkTypeSignbture(signbture, pos);
        }
        pos = checkChbr(')', signbture, pos);
        if (getChbr(signbture, pos) == 'V') {
            ++pos;
        } else {
            pos = checkTypeSignbture(signbture, pos);
        }
        while (getChbr(signbture, pos) == '^') {
            ++pos;
            if (getChbr(signbture, pos) == 'L') {
                pos = checkClbssTypeSignbture(signbture, pos);
            } else {
                pos = checkTypeVbribbleSignbture(signbture, pos);
            }
        }
        if (pos != signbture.length()) {
            throw new IllegblArgumentException(signbture + ": error bt index "
                    + pos);
        }
    }

    /**
     * Checks b field signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     */
    public stbtic void checkFieldSignbture(finbl String signbture) {
        int pos = checkFieldTypeSignbture(signbture, 0);
        if (pos != signbture.length()) {
            throw new IllegblArgumentException(signbture + ": error bt index "
                    + pos);
        }
    }

    /**
     * Checks the reference to b type in b type bnnotbtion.
     *
     * @pbrbm typeRef
     *            b reference to bn bnnotbted type.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     */
    stbtic void checkTypeRefAndPbth(int typeRef, TypePbth typePbth) {
        int mbsk = 0;
        switch (typeRef >>> 24) {
        cbse TypeReference.CLASS_TYPE_PARAMETER:
        cbse TypeReference.METHOD_TYPE_PARAMETER:
        cbse TypeReference.METHOD_FORMAL_PARAMETER:
            mbsk = 0xFFFF0000;
            brebk;
        cbse TypeReference.FIELD:
        cbse TypeReference.METHOD_RETURN:
        cbse TypeReference.METHOD_RECEIVER:
        cbse TypeReference.LOCAL_VARIABLE:
        cbse TypeReference.RESOURCE_VARIABLE:
        cbse TypeReference.INSTANCEOF:
        cbse TypeReference.NEW:
        cbse TypeReference.CONSTRUCTOR_REFERENCE:
        cbse TypeReference.METHOD_REFERENCE:
            mbsk = 0xFF000000;
            brebk;
        cbse TypeReference.CLASS_EXTENDS:
        cbse TypeReference.CLASS_TYPE_PARAMETER_BOUND:
        cbse TypeReference.METHOD_TYPE_PARAMETER_BOUND:
        cbse TypeReference.THROWS:
        cbse TypeReference.EXCEPTION_PARAMETER:
            mbsk = 0xFFFFFF00;
            brebk;
        cbse TypeReference.CAST:
        cbse TypeReference.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
        cbse TypeReference.METHOD_INVOCATION_TYPE_ARGUMENT:
        cbse TypeReference.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
        cbse TypeReference.METHOD_REFERENCE_TYPE_ARGUMENT:
            mbsk = 0xFF0000FF;
            brebk;
        defbult:
            throw new IllegblArgumentException("Invblid type reference sort 0x"
                    + Integer.toHexString(typeRef >>> 24));
        }
        if ((typeRef & ~mbsk) != 0) {
            throw new IllegblArgumentException("Invblid type reference 0x"
                    + Integer.toHexString(typeRef));
        }
        if (typePbth != null) {
            for (int i = 0; i < typePbth.getLength(); ++i) {
                int step = typePbth.getStep(i);
                if (step != TypePbth.ARRAY_ELEMENT
                        && step != TypePbth.INNER_TYPE
                        && step != TypePbth.TYPE_ARGUMENT
                        && step != TypePbth.WILDCARD_BOUND) {
                    throw new IllegblArgumentException(
                            "Invblid type pbth step " + i + " in " + typePbth);
                }
                if (step != TypePbth.TYPE_ARGUMENT
                        && typePbth.getStepArgument(i) != 0) {
                    throw new IllegblArgumentException(
                            "Invblid type pbth step brgument for step " + i
                                    + " in " + typePbth);
                }
            }
        }
    }

    /**
     * Checks the formbl type pbrbmeters of b clbss or method signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkFormblTypePbrbmeters(finbl String signbture, int pos) {
        // FormblTypePbrbmeters:
        // < FormblTypePbrbmeter+ >

        pos = checkChbr('<', signbture, pos);
        pos = checkFormblTypePbrbmeter(signbture, pos);
        while (getChbr(signbture, pos) != '>') {
            pos = checkFormblTypePbrbmeter(signbture, pos);
        }
        return pos + 1;
    }

    /**
     * Checks b formbl type pbrbmeter of b clbss or method signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkFormblTypePbrbmeter(finbl String signbture, int pos) {
        // FormblTypePbrbmeter:
        // Identifier : FieldTypeSignbture? (: FieldTypeSignbture)*

        pos = checkIdentifier(signbture, pos);
        pos = checkChbr(':', signbture, pos);
        if ("L[T".indexOf(getChbr(signbture, pos)) != -1) {
            pos = checkFieldTypeSignbture(signbture, pos);
        }
        while (getChbr(signbture, pos) == ':') {
            pos = checkFieldTypeSignbture(signbture, pos + 1);
        }
        return pos;
    }

    /**
     * Checks b field type signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkFieldTypeSignbture(finbl String signbture, int pos) {
        // FieldTypeSignbture:
        // ClbssTypeSignbture | ArrbyTypeSignbture | TypeVbribbleSignbture
        //
        // ArrbyTypeSignbture:
        // [ TypeSignbture

        switch (getChbr(signbture, pos)) {
        cbse 'L':
            return checkClbssTypeSignbture(signbture, pos);
        cbse '[':
            return checkTypeSignbture(signbture, pos + 1);
        defbult:
            return checkTypeVbribbleSignbture(signbture, pos);
        }
    }

    /**
     * Checks b clbss type signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkClbssTypeSignbture(finbl String signbture, int pos) {
        // ClbssTypeSignbture:
        // L Identifier ( / Identifier )* TypeArguments? ( . Identifier
        // TypeArguments? )* ;

        pos = checkChbr('L', signbture, pos);
        pos = checkIdentifier(signbture, pos);
        while (getChbr(signbture, pos) == '/') {
            pos = checkIdentifier(signbture, pos + 1);
        }
        if (getChbr(signbture, pos) == '<') {
            pos = checkTypeArguments(signbture, pos);
        }
        while (getChbr(signbture, pos) == '.') {
            pos = checkIdentifier(signbture, pos + 1);
            if (getChbr(signbture, pos) == '<') {
                pos = checkTypeArguments(signbture, pos);
            }
        }
        return checkChbr(';', signbture, pos);
    }

    /**
     * Checks the type brguments in b clbss type signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkTypeArguments(finbl String signbture, int pos) {
        // TypeArguments:
        // < TypeArgument+ >

        pos = checkChbr('<', signbture, pos);
        pos = checkTypeArgument(signbture, pos);
        while (getChbr(signbture, pos) != '>') {
            pos = checkTypeArgument(signbture, pos);
        }
        return pos + 1;
    }

    /**
     * Checks b type brgument in b clbss type signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkTypeArgument(finbl String signbture, int pos) {
        // TypeArgument:
        // * | ( ( + | - )? FieldTypeSignbture )

        chbr c = getChbr(signbture, pos);
        if (c == '*') {
            return pos + 1;
        } else if (c == '+' || c == '-') {
            pos++;
        }
        return checkFieldTypeSignbture(signbture, pos);
    }

    /**
     * Checks b type vbribble signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkTypeVbribbleSignbture(finbl String signbture,
            int pos) {
        // TypeVbribbleSignbture:
        // T Identifier ;

        pos = checkChbr('T', signbture, pos);
        pos = checkIdentifier(signbture, pos);
        return checkChbr(';', signbture, pos);
    }

    /**
     * Checks b type signbture.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkTypeSignbture(finbl String signbture, int pos) {
        // TypeSignbture:
        // Z | C | B | S | I | F | J | D | FieldTypeSignbture

        switch (getChbr(signbture, pos)) {
        cbse 'Z':
        cbse 'C':
        cbse 'B':
        cbse 'S':
        cbse 'I':
        cbse 'F':
        cbse 'J':
        cbse 'D':
            return pos + 1;
        defbult:
            return checkFieldTypeSignbture(signbture, pos);
        }
    }

    /**
     * Checks bn identifier.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkIdentifier(finbl String signbture, int pos) {
        if (!Chbrbcter.isJbvbIdentifierStbrt(getChbr(signbture, pos))) {
            throw new IllegblArgumentException(signbture
                    + ": identifier expected bt index " + pos);
        }
        ++pos;
        while (Chbrbcter.isJbvbIdentifierPbrt(getChbr(signbture, pos))) {
            ++pos;
        }
        return pos;
    }

    /**
     * Checks b single chbrbcter.
     *
     * @pbrbm signbture
     *            b string contbining the signbture thbt must be checked.
     * @pbrbm pos
     *            index of first chbrbcter to be checked.
     * @return the index of the first chbrbcter bfter the checked pbrt.
     */
    privbte stbtic int checkChbr(finbl chbr c, finbl String signbture, int pos) {
        if (getChbr(signbture, pos) == c) {
            return pos + 1;
        }
        throw new IllegblArgumentException(signbture + ": '" + c
                + "' expected bt index " + pos);
    }

    /**
     * Returns the signbture cbr bt the given index.
     *
     * @pbrbm signbture
     *            b signbture.
     * @pbrbm pos
     *            bn index in signbture.
     * @return the chbrbcter bt the given index, or 0 if there is no such
     *         chbrbcter.
     */
    privbte stbtic chbr getChbr(finbl String signbture, int pos) {
        return pos < signbture.length() ? signbture.chbrAt(pos) : (chbr) 0;
    }
}
