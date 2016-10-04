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
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.ClbssRebder;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.TypePbth;
import jdk.internbl.org.objectweb.bsm.TypeReference;
import jdk.internbl.org.objectweb.bsm.signbture.SignbtureRebder;

/**
 * A {@link Printer} thbt prints b disbssembled view of the clbsses it visits.
 *
 * @buthor Eric Bruneton
 */
public clbss Textifier extends Printer {

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for internbl
     * type nbmes in bytecode notbtion.
     */
    public stbtic finbl int INTERNAL_NAME = 0;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for field
     * descriptors, formbtted in bytecode notbtion
     */
    public stbtic finbl int FIELD_DESCRIPTOR = 1;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for field
     * signbtures, formbtted in bytecode notbtion
     */
    public stbtic finbl int FIELD_SIGNATURE = 2;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for method
     * descriptors, formbtted in bytecode notbtion
     */
    public stbtic finbl int METHOD_DESCRIPTOR = 3;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for method
     * signbtures, formbtted in bytecode notbtion
     */
    public stbtic finbl int METHOD_SIGNATURE = 4;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for clbss
     * signbtures, formbtted in bytecode notbtion
     */
    public stbtic finbl int CLASS_SIGNATURE = 5;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for field or
     * method return vblue signbtures, formbtted in defbult Jbvb notbtion
     * (non-bytecode)
     */
    public stbtic finbl int TYPE_DECLARATION = 6;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for clbss
     * signbtures, formbtted in defbult Jbvb notbtion (non-bytecode)
     */
    public stbtic finbl int CLASS_DECLARATION = 7;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for method
     * pbrbmeter signbtures, formbtted in defbult Jbvb notbtion (non-bytecode)
     */
    public stbtic finbl int PARAMETERS_DECLARATION = 8;

    /**
     * Constbnt used in {@link #bppendDescriptor bppendDescriptor} for hbndle
     * descriptors, formbtted in bytecode notbtion
     */
    public stbtic finbl int HANDLE_DESCRIPTOR = 9;

    /**
     * Tbb for clbss members.
     */
    protected String tbb = "  ";

    /**
     * Tbb for bytecode instructions.
     */
    protected String tbb2 = "    ";

    /**
     * Tbb for tbble bnd lookup switch instructions.
     */
    protected String tbb3 = "      ";

    /**
     * Tbb for lbbels.
     */
    protected String ltbb = "   ";

    /**
     * The lbbel nbmes. This mbp bssocibte String vblues to Lbbel keys.
     */
    protected Mbp<Lbbel, String> lbbelNbmes;

    /**
     * Clbss bccess flbgs
     */
    privbte int bccess;

    privbte int vblueNumber = 0;

    /**
     * Constructs b new {@link Textifier}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the {@link #Textifier(int)}
     * version.
     *
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public Textifier() {
        this(Opcodes.ASM5);
        if (getClbss() != Textifier.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link Textifier}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected Textifier(finbl int bpi) {
        super(bpi);
    }

    /**
     * Prints b disbssembled view of the given clbss to the stbndbrd output.
     * <p>
     * Usbge: Textifier [-debug] &lt;binbry clbss nbme or clbss file nbme &gt;
     *
     * @pbrbm brgs
     *            the commbnd line brguments.
     *
     * @throws Exception
     *             if the clbss cbnnot be found, or if bn IO exception occurs.
     */
    public stbtic void mbin(finbl String[] brgs) throws Exception {
        int i = 0;
        int flbgs = ClbssRebder.SKIP_DEBUG;

        boolebn ok = true;
        if (brgs.length < 1 || brgs.length > 2) {
            ok = fblse;
        }
        if (ok && "-debug".equbls(brgs[0])) {
            i = 1;
            flbgs = 0;
            if (brgs.length != 2) {
                ok = fblse;
            }
        }
        if (!ok) {
            System.err
                    .println("Prints b disbssembled view of the given clbss.");
            System.err.println("Usbge: Textifier [-debug] "
                    + "<fully qublified clbss nbme or clbss file nbme>");
            return;
        }
        ClbssRebder cr;
        if (brgs[i].endsWith(".clbss") || brgs[i].indexOf('\\') > -1
                || brgs[i].indexOf('/') > -1) {
            cr = new ClbssRebder(new FileInputStrebm(brgs[i]));
        } else {
            cr = new ClbssRebder(brgs[i]);
        }
        cr.bccept(new TrbceClbssVisitor(new PrintWriter(System.out)), flbgs);
    }

    // ------------------------------------------------------------------------
    // Clbsses
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl int version, finbl int bccess, finbl String nbme,
            finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        this.bccess = bccess;
        int mbjor = version & 0xFFFF;
        int minor = version >>> 16;
        buf.setLength(0);
        buf.bppend("// clbss version ").bppend(mbjor).bppend('.').bppend(minor)
                .bppend(" (").bppend(version).bppend(")\n");
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            buf.bppend("// DEPRECATED\n");
        }
        buf.bppend("// bccess flbgs 0x")
                .bppend(Integer.toHexString(bccess).toUpperCbse()).bppend('\n');

        bppendDescriptor(CLASS_SIGNATURE, signbture);
        if (signbture != null) {
            TrbceSignbtureVisitor sv = new TrbceSignbtureVisitor(bccess);
            SignbtureRebder r = new SignbtureRebder(signbture);
            r.bccept(sv);
            buf.bppend("// declbrbtion: ").bppend(nbme)
                    .bppend(sv.getDeclbrbtion()).bppend('\n');
        }

        bppendAccess(bccess & ~Opcodes.ACC_SUPER);
        if ((bccess & Opcodes.ACC_ANNOTATION) != 0) {
            buf.bppend("@interfbce ");
        } else if ((bccess & Opcodes.ACC_INTERFACE) != 0) {
            buf.bppend("interfbce ");
        } else if ((bccess & Opcodes.ACC_ENUM) == 0) {
            buf.bppend("clbss ");
        }
        bppendDescriptor(INTERNAL_NAME, nbme);

        if (superNbme != null && !"jbvb/lbng/Object".equbls(superNbme)) {
            buf.bppend(" extends ");
            bppendDescriptor(INTERNAL_NAME, superNbme);
            buf.bppend(' ');
        }
        if (interfbces != null && interfbces.length > 0) {
            buf.bppend(" implements ");
            for (int i = 0; i < interfbces.length; ++i) {
                bppendDescriptor(INTERNAL_NAME, interfbces[i]);
                buf.bppend(' ');
            }
        }
        buf.bppend(" {\n\n");

        text.bdd(buf.toString());
    }

    @Override
    public void visitSource(finbl String file, finbl String debug) {
        buf.setLength(0);
        if (file != null) {
            buf.bppend(tbb).bppend("// compiled from: ").bppend(file)
                    .bppend('\n');
        }
        if (debug != null) {
            buf.bppend(tbb).bppend("// debug info: ").bppend(debug)
                    .bppend('\n');
        }
        if (buf.length() > 0) {
            text.bdd(buf.toString());
        }
    }

    @Override
    public void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc) {
        buf.setLength(0);
        buf.bppend(tbb).bppend("OUTERCLASS ");
        bppendDescriptor(INTERNAL_NAME, owner);
        buf.bppend(' ');
        if (nbme != null) {
            buf.bppend(nbme).bppend(' ');
        }
        bppendDescriptor(METHOD_DESCRIPTOR, desc);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public Textifier visitClbssAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        text.bdd("\n");
        return visitAnnotbtion(desc, visible);
    }

    @Override
    public Printer visitClbssTypeAnnotbtion(int typeRef, TypePbth typePbth,
            String desc, boolebn visible) {
        text.bdd("\n");
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public void visitClbssAttribute(finbl Attribute bttr) {
        text.bdd("\n");
        visitAttribute(bttr);
    }

    @Override
    public void visitInnerClbss(finbl String nbme, finbl String outerNbme,
            finbl String innerNbme, finbl int bccess) {
        buf.setLength(0);
        buf.bppend(tbb).bppend("// bccess flbgs 0x");
        buf.bppend(
                Integer.toHexString(bccess & ~Opcodes.ACC_SUPER).toUpperCbse())
                .bppend('\n');
        buf.bppend(tbb);
        bppendAccess(bccess);
        buf.bppend("INNERCLASS ");
        bppendDescriptor(INTERNAL_NAME, nbme);
        buf.bppend(' ');
        bppendDescriptor(INTERNAL_NAME, outerNbme);
        buf.bppend(' ');
        bppendDescriptor(INTERNAL_NAME, innerNbme);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public Textifier visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        buf.setLength(0);
        buf.bppend('\n');
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            buf.bppend(tbb).bppend("// DEPRECATED\n");
        }
        buf.bppend(tbb).bppend("// bccess flbgs 0x")
                .bppend(Integer.toHexString(bccess).toUpperCbse()).bppend('\n');
        if (signbture != null) {
            buf.bppend(tbb);
            bppendDescriptor(FIELD_SIGNATURE, signbture);

            TrbceSignbtureVisitor sv = new TrbceSignbtureVisitor(0);
            SignbtureRebder r = new SignbtureRebder(signbture);
            r.bcceptType(sv);
            buf.bppend(tbb).bppend("// declbrbtion: ")
                    .bppend(sv.getDeclbrbtion()).bppend('\n');
        }

        buf.bppend(tbb);
        bppendAccess(bccess);

        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend(' ').bppend(nbme);
        if (vblue != null) {
            buf.bppend(" = ");
            if (vblue instbnceof String) {
                buf.bppend('\"').bppend(vblue).bppend('\"');
            } else {
                buf.bppend(vblue);
            }
        }

        buf.bppend('\n');
        text.bdd(buf.toString());

        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        return t;
    }

    @Override
    public Textifier visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        buf.setLength(0);
        buf.bppend('\n');
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            buf.bppend(tbb).bppend("// DEPRECATED\n");
        }
        buf.bppend(tbb).bppend("// bccess flbgs 0x")
                .bppend(Integer.toHexString(bccess).toUpperCbse()).bppend('\n');

        if (signbture != null) {
            buf.bppend(tbb);
            bppendDescriptor(METHOD_SIGNATURE, signbture);

            TrbceSignbtureVisitor v = new TrbceSignbtureVisitor(0);
            SignbtureRebder r = new SignbtureRebder(signbture);
            r.bccept(v);
            String genericDecl = v.getDeclbrbtion();
            String genericReturn = v.getReturnType();
            String genericExceptions = v.getExceptions();

            buf.bppend(tbb).bppend("// declbrbtion: ").bppend(genericReturn)
                    .bppend(' ').bppend(nbme).bppend(genericDecl);
            if (genericExceptions != null) {
                buf.bppend(" throws ").bppend(genericExceptions);
            }
            buf.bppend('\n');
        }

        buf.bppend(tbb);
        bppendAccess(bccess & ~Opcodes.ACC_VOLATILE);
        if ((bccess & Opcodes.ACC_NATIVE) != 0) {
            buf.bppend("nbtive ");
        }
        if ((bccess & Opcodes.ACC_VARARGS) != 0) {
            buf.bppend("vbrbrgs ");
        }
        if ((bccess & Opcodes.ACC_BRIDGE) != 0) {
            buf.bppend("bridge ");
        }
        if ((this.bccess & Opcodes.ACC_INTERFACE) != 0
                && (bccess & Opcodes.ACC_ABSTRACT) == 0
                && (bccess & Opcodes.ACC_STATIC) == 0) {
            buf.bppend("defbult ");
        }

        buf.bppend(nbme);
        bppendDescriptor(METHOD_DESCRIPTOR, desc);
        if (exceptions != null && exceptions.length > 0) {
            buf.bppend(" throws ");
            for (int i = 0; i < exceptions.length; ++i) {
                bppendDescriptor(INTERNAL_NAME, exceptions[i]);
                buf.bppend(' ');
            }
        }

        buf.bppend('\n');
        text.bdd(buf.toString());

        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        return t;
    }

    @Override
    public void visitClbssEnd() {
        text.bdd("}\n");
    }

    // ------------------------------------------------------------------------
    // Annotbtions
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl String nbme, finbl Object vblue) {
        buf.setLength(0);
        bppendComb(vblueNumber++);

        if (nbme != null) {
            buf.bppend(nbme).bppend('=');
        }

        if (vblue instbnceof String) {
            visitString((String) vblue);
        } else if (vblue instbnceof Type) {
            visitType((Type) vblue);
        } else if (vblue instbnceof Byte) {
            visitByte(((Byte) vblue).byteVblue());
        } else if (vblue instbnceof Boolebn) {
            visitBoolebn(((Boolebn) vblue).boolebnVblue());
        } else if (vblue instbnceof Short) {
            visitShort(((Short) vblue).shortVblue());
        } else if (vblue instbnceof Chbrbcter) {
            visitChbr(((Chbrbcter) vblue).chbrVblue());
        } else if (vblue instbnceof Integer) {
            visitInt(((Integer) vblue).intVblue());
        } else if (vblue instbnceof Flobt) {
            visitFlobt(((Flobt) vblue).flobtVblue());
        } else if (vblue instbnceof Long) {
            visitLong(((Long) vblue).longVblue());
        } else if (vblue instbnceof Double) {
            visitDouble(((Double) vblue).doubleVblue());
        } else if (vblue.getClbss().isArrby()) {
            buf.bppend('{');
            if (vblue instbnceof byte[]) {
                byte[] v = (byte[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitByte(v[i]);
                }
            } else if (vblue instbnceof boolebn[]) {
                boolebn[] v = (boolebn[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitBoolebn(v[i]);
                }
            } else if (vblue instbnceof short[]) {
                short[] v = (short[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitShort(v[i]);
                }
            } else if (vblue instbnceof chbr[]) {
                chbr[] v = (chbr[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitChbr(v[i]);
                }
            } else if (vblue instbnceof int[]) {
                int[] v = (int[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitInt(v[i]);
                }
            } else if (vblue instbnceof long[]) {
                long[] v = (long[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitLong(v[i]);
                }
            } else if (vblue instbnceof flobt[]) {
                flobt[] v = (flobt[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitFlobt(v[i]);
                }
            } else if (vblue instbnceof double[]) {
                double[] v = (double[]) vblue;
                for (int i = 0; i < v.length; i++) {
                    bppendComb(i);
                    visitDouble(v[i]);
                }
            }
            buf.bppend('}');
        }

        text.bdd(buf.toString());
    }

    privbte void visitInt(finbl int vblue) {
        buf.bppend(vblue);
    }

    privbte void visitLong(finbl long vblue) {
        buf.bppend(vblue).bppend('L');
    }

    privbte void visitFlobt(finbl flobt vblue) {
        buf.bppend(vblue).bppend('F');
    }

    privbte void visitDouble(finbl double vblue) {
        buf.bppend(vblue).bppend('D');
    }

    privbte void visitChbr(finbl chbr vblue) {
        buf.bppend("(chbr)").bppend((int) vblue);
    }

    privbte void visitShort(finbl short vblue) {
        buf.bppend("(short)").bppend(vblue);
    }

    privbte void visitByte(finbl byte vblue) {
        buf.bppend("(byte)").bppend(vblue);
    }

    privbte void visitBoolebn(finbl boolebn vblue) {
        buf.bppend(vblue);
    }

    privbte void visitString(finbl String vblue) {
        bppendString(buf, vblue);
    }

    privbte void visitType(finbl Type vblue) {
        buf.bppend(vblue.getClbssNbme()).bppend(".clbss");
    }

    @Override
    public void visitEnum(finbl String nbme, finbl String desc,
            finbl String vblue) {
        buf.setLength(0);
        bppendComb(vblueNumber++);
        if (nbme != null) {
            buf.bppend(nbme).bppend('=');
        }
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('.').bppend(vblue);
        text.bdd(buf.toString());
    }

    @Override
    public Textifier visitAnnotbtion(finbl String nbme, finbl String desc) {
        buf.setLength(0);
        bppendComb(vblueNumber++);
        if (nbme != null) {
            buf.bppend(nbme).bppend('=');
        }
        buf.bppend('@');
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('(');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        text.bdd(")");
        return t;
    }

    @Override
    public Textifier visitArrby(finbl String nbme) {
        buf.setLength(0);
        bppendComb(vblueNumber++);
        if (nbme != null) {
            buf.bppend(nbme).bppend('=');
        }
        buf.bppend('{');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        text.bdd("}");
        return t;
    }

    @Override
    public void visitAnnotbtionEnd() {
    }

    // ------------------------------------------------------------------------
    // Fields
    // ------------------------------------------------------------------------

    @Override
    public Textifier visitFieldAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        return visitAnnotbtion(desc, visible);
    }

    @Override
    public Printer visitFieldTypeAnnotbtion(int typeRef, TypePbth typePbth,
            String desc, boolebn visible) {
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public void visitFieldAttribute(finbl Attribute bttr) {
        visitAttribute(bttr);
    }

    @Override
    public void visitFieldEnd() {
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    @Override
    public void visitPbrbmeter(finbl String nbme, finbl int bccess) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("// pbrbmeter ");
        bppendAccess(bccess);
        buf.bppend(' ').bppend((nbme == null) ? "<no nbme>" : nbme)
                .bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public Textifier visitAnnotbtionDefbult() {
        text.bdd(tbb2 + "defbult=");
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        text.bdd("\n");
        return t;
    }

    @Override
    public Textifier visitMethodAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        return visitAnnotbtion(desc, visible);
    }

    @Override
    public Printer visitMethodTypeAnnotbtion(int typeRef, TypePbth typePbth,
            String desc, boolebn visible) {
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public Textifier visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend('@');
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('(');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        text.bdd(visible ? ") // pbrbmeter " : ") // invisible, pbrbmeter ");
        text.bdd(new Integer(pbrbmeter));
        text.bdd("\n");
        return t;
    }

    @Override
    public void visitMethodAttribute(finbl Attribute bttr) {
        buf.setLength(0);
        buf.bppend(tbb).bppend("ATTRIBUTE ");
        bppendDescriptor(-1, bttr.type);

        if (bttr instbnceof Textifibble) {
            ((Textifibble) bttr).textify(buf, lbbelNbmes);
        } else {
            buf.bppend(" : unknown\n");
        }

        text.bdd(buf.toString());
    }

    @Override
    public void visitCode() {
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        buf.setLength(0);
        buf.bppend(ltbb);
        buf.bppend("FRAME ");
        switch (type) {
        cbse Opcodes.F_NEW:
        cbse Opcodes.F_FULL:
            buf.bppend("FULL [");
            bppendFrbmeTypes(nLocbl, locbl);
            buf.bppend("] [");
            bppendFrbmeTypes(nStbck, stbck);
            buf.bppend(']');
            brebk;
        cbse Opcodes.F_APPEND:
            buf.bppend("APPEND [");
            bppendFrbmeTypes(nLocbl, locbl);
            buf.bppend(']');
            brebk;
        cbse Opcodes.F_CHOP:
            buf.bppend("CHOP ").bppend(nLocbl);
            brebk;
        cbse Opcodes.F_SAME:
            buf.bppend("SAME");
            brebk;
        cbse Opcodes.F_SAME1:
            buf.bppend("SAME1 ");
            bppendFrbmeTypes(1, stbck);
            brebk;
        }
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitInsn(finbl int opcode) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend(OPCODES[opcode]).bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        buf.setLength(0);
        buf.bppend(tbb2)
                .bppend(OPCODES[opcode])
                .bppend(' ')
                .bppend(opcode == Opcodes.NEWARRAY ? TYPES[operbnd] : Integer
                        .toString(operbnd)).bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend(OPCODES[opcode]).bppend(' ').bppend(vbr)
                .bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend(OPCODES[opcode]).bppend(' ');
        bppendDescriptor(INTERNAL_NAME, type);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend(OPCODES[opcode]).bppend(' ');
        bppendDescriptor(INTERNAL_NAME, owner);
        buf.bppend('.').bppend(nbme).bppend(" : ");
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Deprecbted
    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc,
                opcode == Opcodes.INVOKEINTERFACE);
    }

    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc, itf);
    }

    privbte void doVisitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend(OPCODES[opcode]).bppend(' ');
        bppendDescriptor(INTERNAL_NAME, owner);
        buf.bppend('.').bppend(nbme).bppend(' ');
        bppendDescriptor(METHOD_DESCRIPTOR, desc);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("INVOKEDYNAMIC").bppend(' ');
        buf.bppend(nbme);
        bppendDescriptor(METHOD_DESCRIPTOR, desc);
        buf.bppend(" [");
        buf.bppend('\n');
        buf.bppend(tbb3);
        bppendHbndle(bsm);
        buf.bppend('\n');
        buf.bppend(tbb3).bppend("// brguments:");
        if (bsmArgs.length == 0) {
            buf.bppend(" none");
        } else {
            buf.bppend('\n');
            for (int i = 0; i < bsmArgs.length; i++) {
                buf.bppend(tbb3);
                Object cst = bsmArgs[i];
                if (cst instbnceof String) {
                    Printer.bppendString(buf, (String) cst);
                } else if (cst instbnceof Type) {
                    Type type = (Type) cst;
                    if(type.getSort() == Type.METHOD){
                        bppendDescriptor(METHOD_DESCRIPTOR, type.getDescriptor());
                    } else {
                        buf.bppend(type.getDescriptor()).bppend(".clbss");
                    }
                } else if (cst instbnceof Hbndle) {
                    bppendHbndle((Hbndle) cst);
                } else {
                    buf.bppend(cst);
                }
                buf.bppend(", \n");
            }
            buf.setLength(buf.length() - 3);
        }
        buf.bppend('\n');
        buf.bppend(tbb2).bppend("]\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend(OPCODES[opcode]).bppend(' ');
        bppendLbbel(lbbel);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        buf.setLength(0);
        buf.bppend(ltbb);
        bppendLbbel(lbbel);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("LDC ");
        if (cst instbnceof String) {
            Printer.bppendString(buf, (String) cst);
        } else if (cst instbnceof Type) {
            buf.bppend(((Type) cst).getDescriptor()).bppend(".clbss");
        } else {
            buf.bppend(cst);
        }
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("IINC ").bppend(vbr).bppend(' ')
                .bppend(increment).bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("TABLESWITCH\n");
        for (int i = 0; i < lbbels.length; ++i) {
            buf.bppend(tbb3).bppend(min + i).bppend(": ");
            bppendLbbel(lbbels[i]);
            buf.bppend('\n');
        }
        buf.bppend(tbb3).bppend("defbult: ");
        bppendLbbel(dflt);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("LOOKUPSWITCH\n");
        for (int i = 0; i < lbbels.length; ++i) {
            buf.bppend(tbb3).bppend(keys[i]).bppend(": ");
            bppendLbbel(lbbels[i]);
            buf.bppend('\n');
        }
        buf.bppend(tbb3).bppend("defbult: ");
        bppendLbbel(dflt);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("MULTIANEWARRAY ");
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend(' ').bppend(dims).bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public Printer visitInsnAnnotbtion(int typeRef, TypePbth typePbth,
            String desc, boolebn visible) {
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("TRYCATCHBLOCK ");
        bppendLbbel(stbrt);
        buf.bppend(' ');
        bppendLbbel(end);
        buf.bppend(' ');
        bppendLbbel(hbndler);
        buf.bppend(' ');
        bppendDescriptor(INTERNAL_NAME, type);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public Printer visitTryCbtchAnnotbtion(int typeRef, TypePbth typePbth,
            String desc, boolebn visible) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("TRYCATCHBLOCK @");
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('(');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        buf.setLength(0);
        buf.bppend(") : ");
        bppendTypeReference(typeRef);
        buf.bppend(", ").bppend(typePbth);
        buf.bppend(visible ? "\n" : " // invisible\n");
        text.bdd(buf.toString());
        return t;
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("LOCALVARIABLE ").bppend(nbme).bppend(' ');
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend(' ');
        bppendLbbel(stbrt);
        buf.bppend(' ');
        bppendLbbel(end);
        buf.bppend(' ').bppend(index).bppend('\n');

        if (signbture != null) {
            buf.bppend(tbb2);
            bppendDescriptor(FIELD_SIGNATURE, signbture);

            TrbceSignbtureVisitor sv = new TrbceSignbtureVisitor(0);
            SignbtureRebder r = new SignbtureRebder(signbture);
            r.bcceptType(sv);
            buf.bppend(tbb2).bppend("// declbrbtion: ")
                    .bppend(sv.getDeclbrbtion()).bppend('\n');
        }
        text.bdd(buf.toString());
    }

    @Override
    public Printer visitLocblVbribbleAnnotbtion(int typeRef, TypePbth typePbth,
            Lbbel[] stbrt, Lbbel[] end, int[] index, String desc,
            boolebn visible) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("LOCALVARIABLE @");
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('(');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        buf.setLength(0);
        buf.bppend(") : ");
        bppendTypeReference(typeRef);
        buf.bppend(", ").bppend(typePbth);
        for (int i = 0; i < stbrt.length; ++i) {
            buf.bppend(" [ ");
            bppendLbbel(stbrt[i]);
            buf.bppend(" - ");
            bppendLbbel(end[i]);
            buf.bppend(" - ").bppend(index[i]).bppend(" ]");
        }
        buf.bppend(visible ? "\n" : " // invisible\n");
        text.bdd(buf.toString());
        return t;
    }

    @Override
    public void visitLineNumber(finbl int line, finbl Lbbel stbrt) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("LINENUMBER ").bppend(line).bppend(' ');
        bppendLbbel(stbrt);
        buf.bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        buf.setLength(0);
        buf.bppend(tbb2).bppend("MAXSTACK = ").bppend(mbxStbck).bppend('\n');
        text.bdd(buf.toString());

        buf.setLength(0);
        buf.bppend(tbb2).bppend("MAXLOCALS = ").bppend(mbxLocbls).bppend('\n');
        text.bdd(buf.toString());
    }

    @Override
    public void visitMethodEnd() {
    }

    // ------------------------------------------------------------------------
    // Common methods
    // ------------------------------------------------------------------------

    /**
     * Prints b disbssembled view of the given bnnotbtion.
     *
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues.
     */
    public Textifier visitAnnotbtion(finbl String desc, finbl boolebn visible) {
        buf.setLength(0);
        buf.bppend(tbb).bppend('@');
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('(');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        text.bdd(visible ? ")\n" : ") // invisible\n");
        return t;
    }

    /**
     * Prints b disbssembled view of the given type bnnotbtion.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues.
     */
    public Textifier visitTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        buf.setLength(0);
        buf.bppend(tbb).bppend('@');
        bppendDescriptor(FIELD_DESCRIPTOR, desc);
        buf.bppend('(');
        text.bdd(buf.toString());
        Textifier t = crebteTextifier();
        text.bdd(t.getText());
        buf.setLength(0);
        buf.bppend(") : ");
        bppendTypeReference(typeRef);
        buf.bppend(", ").bppend(typePbth);
        buf.bppend(visible ? "\n" : " // invisible\n");
        text.bdd(buf.toString());
        return t;
    }

    /**
     * Prints b disbssembled view of the given bttribute.
     *
     * @pbrbm bttr
     *            bn bttribute.
     */
    public void visitAttribute(finbl Attribute bttr) {
        buf.setLength(0);
        buf.bppend(tbb).bppend("ATTRIBUTE ");
        bppendDescriptor(-1, bttr.type);

        if (bttr instbnceof Textifibble) {
            ((Textifibble) bttr).textify(buf, null);
        } else {
            buf.bppend(" : unknown\n");
        }

        text.bdd(buf.toString());
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    /**
     * Crebtes b new TrbceVisitor instbnce.
     *
     * @return b new TrbceVisitor.
     */
    protected Textifier crebteTextifier() {
        return new Textifier();
    }

    /**
     * Appends bn internbl nbme, b type descriptor or b type signbture to
     * {@link #buf buf}.
     *
     * @pbrbm type
     *            indicbtes if desc is bn internbl nbme, b field descriptor, b
     *            method descriptor, b clbss signbture, ...
     * @pbrbm desc
     *            bn internbl nbme, type descriptor, or type signbture. Mby be
     *            <tt>null</tt>.
     */
    protected void bppendDescriptor(finbl int type, finbl String desc) {
        if (type == CLASS_SIGNATURE || type == FIELD_SIGNATURE
                || type == METHOD_SIGNATURE) {
            if (desc != null) {
                buf.bppend("// signbture ").bppend(desc).bppend('\n');
            }
        } else {
            buf.bppend(desc);
        }
    }

    /**
     * Appends the nbme of the given lbbel to {@link #buf buf}. Crebtes b new
     * lbbel nbme if the given lbbel does not yet hbve one.
     *
     * @pbrbm l
     *            b lbbel.
     */
    protected void bppendLbbel(finbl Lbbel l) {
        if (lbbelNbmes == null) {
            lbbelNbmes = new HbshMbp<Lbbel, String>();
        }
        String nbme = lbbelNbmes.get(l);
        if (nbme == null) {
            nbme = "L" + lbbelNbmes.size();
            lbbelNbmes.put(l, nbme);
        }
        buf.bppend(nbme);
    }

    /**
     * Appends the informbtion bbout the given hbndle to {@link #buf buf}.
     *
     * @pbrbm h
     *            b hbndle, non null.
     */
    protected void bppendHbndle(finbl Hbndle h) {
        int tbg = h.getTbg();
        buf.bppend("// hbndle kind 0x").bppend(Integer.toHexString(tbg))
                .bppend(" : ");
        boolebn isMethodHbndle = fblse;
        switch (tbg) {
        cbse Opcodes.H_GETFIELD:
            buf.bppend("GETFIELD");
            brebk;
        cbse Opcodes.H_GETSTATIC:
            buf.bppend("GETSTATIC");
            brebk;
        cbse Opcodes.H_PUTFIELD:
            buf.bppend("PUTFIELD");
            brebk;
        cbse Opcodes.H_PUTSTATIC:
            buf.bppend("PUTSTATIC");
            brebk;
        cbse Opcodes.H_INVOKEINTERFACE:
            buf.bppend("INVOKEINTERFACE");
            isMethodHbndle = true;
            brebk;
        cbse Opcodes.H_INVOKESPECIAL:
            buf.bppend("INVOKESPECIAL");
            isMethodHbndle = true;
            brebk;
        cbse Opcodes.H_INVOKESTATIC:
            buf.bppend("INVOKESTATIC");
            isMethodHbndle = true;
            brebk;
        cbse Opcodes.H_INVOKEVIRTUAL:
            buf.bppend("INVOKEVIRTUAL");
            isMethodHbndle = true;
            brebk;
        cbse Opcodes.H_NEWINVOKESPECIAL:
            buf.bppend("NEWINVOKESPECIAL");
            isMethodHbndle = true;
            brebk;
        }
        buf.bppend('\n');
        buf.bppend(tbb3);
        bppendDescriptor(INTERNAL_NAME, h.getOwner());
        buf.bppend('.');
        buf.bppend(h.getNbme());
        if(!isMethodHbndle){
            buf.bppend('(');
        }
        bppendDescriptor(HANDLE_DESCRIPTOR, h.getDesc());
        if(!isMethodHbndle){
            buf.bppend(')');
        }
    }

    /**
     * Appends b string representbtion of the given bccess modifiers to
     * {@link #buf buf}.
     *
     * @pbrbm bccess
     *            some bccess modifiers.
     */
    privbte void bppendAccess(finbl int bccess) {
        if ((bccess & Opcodes.ACC_PUBLIC) != 0) {
            buf.bppend("public ");
        }
        if ((bccess & Opcodes.ACC_PRIVATE) != 0) {
            buf.bppend("privbte ");
        }
        if ((bccess & Opcodes.ACC_PROTECTED) != 0) {
            buf.bppend("protected ");
        }
        if ((bccess & Opcodes.ACC_FINAL) != 0) {
            buf.bppend("finbl ");
        }
        if ((bccess & Opcodes.ACC_STATIC) != 0) {
            buf.bppend("stbtic ");
        }
        if ((bccess & Opcodes.ACC_SYNCHRONIZED) != 0) {
            buf.bppend("synchronized ");
        }
        if ((bccess & Opcodes.ACC_VOLATILE) != 0) {
            buf.bppend("volbtile ");
        }
        if ((bccess & Opcodes.ACC_TRANSIENT) != 0) {
            buf.bppend("trbnsient ");
        }
        if ((bccess & Opcodes.ACC_ABSTRACT) != 0) {
            buf.bppend("bbstrbct ");
        }
        if ((bccess & Opcodes.ACC_STRICT) != 0) {
            buf.bppend("strictfp ");
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            buf.bppend("synthetic ");
        }
        if ((bccess & Opcodes.ACC_MANDATED) != 0) {
            buf.bppend("mbndbted ");
        }
        if ((bccess & Opcodes.ACC_ENUM) != 0) {
            buf.bppend("enum ");
        }
    }

    privbte void bppendComb(finbl int i) {
        if (i != 0) {
            buf.bppend(", ");
        }
    }

    privbte void bppendTypeReference(finbl int typeRef) {
        TypeReference ref = new TypeReference(typeRef);
        switch (ref.getSort()) {
        cbse TypeReference.CLASS_TYPE_PARAMETER:
            buf.bppend("CLASS_TYPE_PARAMETER ").bppend(
                    ref.getTypePbrbmeterIndex());
            brebk;
        cbse TypeReference.METHOD_TYPE_PARAMETER:
            buf.bppend("METHOD_TYPE_PARAMETER ").bppend(
                    ref.getTypePbrbmeterIndex());
            brebk;
        cbse TypeReference.CLASS_EXTENDS:
            buf.bppend("CLASS_EXTENDS ").bppend(ref.getSuperTypeIndex());
            brebk;
        cbse TypeReference.CLASS_TYPE_PARAMETER_BOUND:
            buf.bppend("CLASS_TYPE_PARAMETER_BOUND ")
                    .bppend(ref.getTypePbrbmeterIndex()).bppend(", ")
                    .bppend(ref.getTypePbrbmeterBoundIndex());
            brebk;
        cbse TypeReference.METHOD_TYPE_PARAMETER_BOUND:
            buf.bppend("METHOD_TYPE_PARAMETER_BOUND ")
                    .bppend(ref.getTypePbrbmeterIndex()).bppend(", ")
                    .bppend(ref.getTypePbrbmeterBoundIndex());
            brebk;
        cbse TypeReference.FIELD:
            buf.bppend("FIELD");
            brebk;
        cbse TypeReference.METHOD_RETURN:
            buf.bppend("METHOD_RETURN");
            brebk;
        cbse TypeReference.METHOD_RECEIVER:
            buf.bppend("METHOD_RECEIVER");
            brebk;
        cbse TypeReference.METHOD_FORMAL_PARAMETER:
            buf.bppend("METHOD_FORMAL_PARAMETER ").bppend(
                    ref.getFormblPbrbmeterIndex());
            brebk;
        cbse TypeReference.THROWS:
            buf.bppend("THROWS ").bppend(ref.getExceptionIndex());
            brebk;
        cbse TypeReference.LOCAL_VARIABLE:
            buf.bppend("LOCAL_VARIABLE");
            brebk;
        cbse TypeReference.RESOURCE_VARIABLE:
            buf.bppend("RESOURCE_VARIABLE");
            brebk;
        cbse TypeReference.EXCEPTION_PARAMETER:
            buf.bppend("EXCEPTION_PARAMETER ").bppend(
                    ref.getTryCbtchBlockIndex());
            brebk;
        cbse TypeReference.INSTANCEOF:
            buf.bppend("INSTANCEOF");
            brebk;
        cbse TypeReference.NEW:
            buf.bppend("NEW");
            brebk;
        cbse TypeReference.CONSTRUCTOR_REFERENCE:
            buf.bppend("CONSTRUCTOR_REFERENCE");
            brebk;
        cbse TypeReference.METHOD_REFERENCE:
            buf.bppend("METHOD_REFERENCE");
            brebk;
        cbse TypeReference.CAST:
            buf.bppend("CAST ").bppend(ref.getTypeArgumentIndex());
            brebk;
        cbse TypeReference.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
            buf.bppend("CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT ").bppend(
                    ref.getTypeArgumentIndex());
            brebk;
        cbse TypeReference.METHOD_INVOCATION_TYPE_ARGUMENT:
            buf.bppend("METHOD_INVOCATION_TYPE_ARGUMENT ").bppend(
                    ref.getTypeArgumentIndex());
            brebk;
        cbse TypeReference.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
            buf.bppend("CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT ").bppend(
                    ref.getTypeArgumentIndex());
            brebk;
        cbse TypeReference.METHOD_REFERENCE_TYPE_ARGUMENT:
            buf.bppend("METHOD_REFERENCE_TYPE_ARGUMENT ").bppend(
                    ref.getTypeArgumentIndex());
            brebk;
        }
    }

    privbte void bppendFrbmeTypes(finbl int n, finbl Object[] o) {
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                buf.bppend(' ');
            }
            if (o[i] instbnceof String) {
                String desc = (String) o[i];
                if (desc.stbrtsWith("[")) {
                    bppendDescriptor(FIELD_DESCRIPTOR, desc);
                } else {
                    bppendDescriptor(INTERNAL_NAME, desc);
                }
            } else if (o[i] instbnceof Integer) {
                switch (((Integer) o[i]).intVblue()) {
                cbse 0:
                    bppendDescriptor(FIELD_DESCRIPTOR, "T");
                    brebk;
                cbse 1:
                    bppendDescriptor(FIELD_DESCRIPTOR, "I");
                    brebk;
                cbse 2:
                    bppendDescriptor(FIELD_DESCRIPTOR, "F");
                    brebk;
                cbse 3:
                    bppendDescriptor(FIELD_DESCRIPTOR, "D");
                    brebk;
                cbse 4:
                    bppendDescriptor(FIELD_DESCRIPTOR, "J");
                    brebk;
                cbse 5:
                    bppendDescriptor(FIELD_DESCRIPTOR, "N");
                    brebk;
                cbse 6:
                    bppendDescriptor(FIELD_DESCRIPTOR, "U");
                    brebk;
                }
            } else {
                bppendLbbel((Lbbel) o[i]);
            }
        }
    }
}
