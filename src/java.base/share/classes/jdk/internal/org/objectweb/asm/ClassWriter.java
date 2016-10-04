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
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * A {@link ClbssVisitor} thbt generbtes clbsses in bytecode form. More
 * precisely this visitor generbtes b byte brrby conforming to the Jbvb clbss
 * file formbt. It cbn be used blone, to generbte b Jbvb clbss "from scrbtch",
 * or with one or more {@link ClbssRebder ClbssRebder} bnd bdbpter clbss visitor
 * to generbte b modified clbss from one or more existing Jbvb clbsses.
 *
 * @buthor Eric Bruneton
 */
public clbss ClbssWriter extends ClbssVisitor {

    /**
     * Flbg to butombticblly compute the mbximum stbck size bnd the mbximum
     * number of locbl vbribbles of methods. If this flbg is set, then the
     * brguments of the {@link MethodVisitor#visitMbxs visitMbxs} method of the
     * {@link MethodVisitor} returned by the {@link #visitMethod visitMethod}
     * method will be ignored, bnd computed butombticblly from the signbture bnd
     * the bytecode of ebch method.
     *
     * @see #ClbssWriter(int)
     */
    public stbtic finbl int COMPUTE_MAXS = 1;

    /**
     * Flbg to butombticblly compute the stbck mbp frbmes of methods from
     * scrbtch. If this flbg is set, then the cblls to the
     * {@link MethodVisitor#visitFrbme} method bre ignored, bnd the stbck mbp
     * frbmes bre recomputed from the methods bytecode. The brguments of the
     * {@link MethodVisitor#visitMbxs visitMbxs} method bre blso ignored bnd
     * recomputed from the bytecode. In other words, computeFrbmes implies
     * computeMbxs.
     *
     * @see #ClbssWriter(int)
     */
    public stbtic finbl int COMPUTE_FRAMES = 2;

    /**
     * Pseudo bccess flbg to distinguish between the synthetic bttribute bnd the
     * synthetic bccess flbg.
     */
    stbtic finbl int ACC_SYNTHETIC_ATTRIBUTE = 0x40000;

    /**
     * Fbctor to convert from ACC_SYNTHETIC_ATTRIBUTE to Opcode.ACC_SYNTHETIC.
     */
    stbtic finbl int TO_ACC_SYNTHETIC = ACC_SYNTHETIC_ATTRIBUTE
            / Opcodes.ACC_SYNTHETIC;

    /**
     * The type of instructions without bny brgument.
     */
    stbtic finbl int NOARG_INSN = 0;

    /**
     * The type of instructions with bn signed byte brgument.
     */
    stbtic finbl int SBYTE_INSN = 1;

    /**
     * The type of instructions with bn signed short brgument.
     */
    stbtic finbl int SHORT_INSN = 2;

    /**
     * The type of instructions with b locbl vbribble index brgument.
     */
    stbtic finbl int VAR_INSN = 3;

    /**
     * The type of instructions with bn implicit locbl vbribble index brgument.
     */
    stbtic finbl int IMPLVAR_INSN = 4;

    /**
     * The type of instructions with b type descriptor brgument.
     */
    stbtic finbl int TYPE_INSN = 5;

    /**
     * The type of field bnd method invocbtions instructions.
     */
    stbtic finbl int FIELDORMETH_INSN = 6;

    /**
     * The type of the INVOKEINTERFACE/INVOKEDYNAMIC instruction.
     */
    stbtic finbl int ITFMETH_INSN = 7;

    /**
     * The type of the INVOKEDYNAMIC instruction.
     */
    stbtic finbl int INDYMETH_INSN = 8;

    /**
     * The type of instructions with b 2 bytes bytecode offset lbbel.
     */
    stbtic finbl int LABEL_INSN = 9;

    /**
     * The type of instructions with b 4 bytes bytecode offset lbbel.
     */
    stbtic finbl int LABELW_INSN = 10;

    /**
     * The type of the LDC instruction.
     */
    stbtic finbl int LDC_INSN = 11;

    /**
     * The type of the LDC_W bnd LDC2_W instructions.
     */
    stbtic finbl int LDCW_INSN = 12;

    /**
     * The type of the IINC instruction.
     */
    stbtic finbl int IINC_INSN = 13;

    /**
     * The type of the TABLESWITCH instruction.
     */
    stbtic finbl int TABL_INSN = 14;

    /**
     * The type of the LOOKUPSWITCH instruction.
     */
    stbtic finbl int LOOK_INSN = 15;

    /**
     * The type of the MULTIANEWARRAY instruction.
     */
    stbtic finbl int MANA_INSN = 16;

    /**
     * The type of the WIDE instruction.
     */
    stbtic finbl int WIDE_INSN = 17;

    /**
     * The instruction types of bll JVM opcodes.
     */
    stbtic finbl byte[] TYPE;

    /**
     * The type of CONSTANT_Clbss constbnt pool items.
     */
    stbtic finbl int CLASS = 7;

    /**
     * The type of CONSTANT_Fieldref constbnt pool items.
     */
    stbtic finbl int FIELD = 9;

    /**
     * The type of CONSTANT_Methodref constbnt pool items.
     */
    stbtic finbl int METH = 10;

    /**
     * The type of CONSTANT_InterfbceMethodref constbnt pool items.
     */
    stbtic finbl int IMETH = 11;

    /**
     * The type of CONSTANT_String constbnt pool items.
     */
    stbtic finbl int STR = 8;

    /**
     * The type of CONSTANT_Integer constbnt pool items.
     */
    stbtic finbl int INT = 3;

    /**
     * The type of CONSTANT_Flobt constbnt pool items.
     */
    stbtic finbl int FLOAT = 4;

    /**
     * The type of CONSTANT_Long constbnt pool items.
     */
    stbtic finbl int LONG = 5;

    /**
     * The type of CONSTANT_Double constbnt pool items.
     */
    stbtic finbl int DOUBLE = 6;

    /**
     * The type of CONSTANT_NbmeAndType constbnt pool items.
     */
    stbtic finbl int NAME_TYPE = 12;

    /**
     * The type of CONSTANT_Utf8 constbnt pool items.
     */
    stbtic finbl int UTF8 = 1;

    /**
     * The type of CONSTANT_MethodType constbnt pool items.
     */
    stbtic finbl int MTYPE = 16;

    /**
     * The type of CONSTANT_MethodHbndle constbnt pool items.
     */
    stbtic finbl int HANDLE = 15;

    /**
     * The type of CONSTANT_InvokeDynbmic constbnt pool items.
     */
    stbtic finbl int INDY = 18;

    /**
     * The bbse vblue for bll CONSTANT_MethodHbndle constbnt pool items.
     * Internblly, ASM store the 9 vbribtions of CONSTANT_MethodHbndle into 9
     * different items.
     */
    stbtic finbl int HANDLE_BASE = 20;

    /**
     * Normbl type Item stored in the ClbssWriter {@link ClbssWriter#typeTbble},
     * instebd of the constbnt pool, in order to bvoid clbshes with normbl
     * constbnt pool items in the ClbssWriter constbnt pool's hbsh tbble.
     */
    stbtic finbl int TYPE_NORMAL = 30;

    /**
     * Uninitiblized type Item stored in the ClbssWriter
     * {@link ClbssWriter#typeTbble}, instebd of the constbnt pool, in order to
     * bvoid clbshes with normbl constbnt pool items in the ClbssWriter constbnt
     * pool's hbsh tbble.
     */
    stbtic finbl int TYPE_UNINIT = 31;

    /**
     * Merged type Item stored in the ClbssWriter {@link ClbssWriter#typeTbble},
     * instebd of the constbnt pool, in order to bvoid clbshes with normbl
     * constbnt pool items in the ClbssWriter constbnt pool's hbsh tbble.
     */
    stbtic finbl int TYPE_MERGED = 32;

    /**
     * The type of BootstrbpMethods items. These items bre stored in b specibl
     * clbss bttribute nbmed BootstrbpMethods bnd not in the constbnt pool.
     */
    stbtic finbl int BSM = 33;

    /**
     * The clbss rebder from which this clbss writer wbs constructed, if bny.
     */
    ClbssRebder cr;

    /**
     * Minor bnd mbjor version numbers of the clbss to be generbted.
     */
    int version;

    /**
     * Index of the next item to be bdded in the constbnt pool.
     */
    int index;

    /**
     * The constbnt pool of this clbss.
     */
    finbl ByteVector pool;

    /**
     * The constbnt pool's hbsh tbble dbtb.
     */
    Item[] items;

    /**
     * The threshold of the constbnt pool's hbsh tbble.
     */
    int threshold;

    /**
     * A reusbble key used to look for items in the {@link #items} hbsh tbble.
     */
    finbl Item key;

    /**
     * A reusbble key used to look for items in the {@link #items} hbsh tbble.
     */
    finbl Item key2;

    /**
     * A reusbble key used to look for items in the {@link #items} hbsh tbble.
     */
    finbl Item key3;

    /**
     * A reusbble key used to look for items in the {@link #items} hbsh tbble.
     */
    finbl Item key4;

    /**
     * A type tbble used to temporbrily store internbl nbmes thbt will not
     * necessbrily be stored in the constbnt pool. This type tbble is used by
     * the control flow bnd dbtb flow bnblysis blgorithm used to compute stbck
     * mbp frbmes from scrbtch. This brrby bssocibtes to ebch index <tt>i</tt>
     * the Item whose index is <tt>i</tt>. All Item objects stored in this brrby
     * bre blso stored in the {@link #items} hbsh tbble. These two brrbys bllow
     * to retrieve bn Item from its index or, conversely, to get the index of bn
     * Item from its vblue. Ebch Item stores bn internbl nbme in its
     * {@link Item#strVbl1} field.
     */
    Item[] typeTbble;

    /**
     * Number of elements in the {@link #typeTbble} brrby.
     */
    privbte short typeCount;

    /**
     * The bccess flbgs of this clbss.
     */
    privbte int bccess;

    /**
     * The constbnt pool item thbt contbins the internbl nbme of this clbss.
     */
    privbte int nbme;

    /**
     * The internbl nbme of this clbss.
     */
    String thisNbme;

    /**
     * The constbnt pool item thbt contbins the signbture of this clbss.
     */
    privbte int signbture;

    /**
     * The constbnt pool item thbt contbins the internbl nbme of the super clbss
     * of this clbss.
     */
    privbte int superNbme;

    /**
     * Number of interfbces implemented or extended by this clbss or interfbce.
     */
    privbte int interfbceCount;

    /**
     * The interfbces implemented or extended by this clbss or interfbce. More
     * precisely, this brrby contbins the indexes of the constbnt pool items
     * thbt contbin the internbl nbmes of these interfbces.
     */
    privbte int[] interfbces;

    /**
     * The index of the constbnt pool item thbt contbins the nbme of the source
     * file from which this clbss wbs compiled.
     */
    privbte int sourceFile;

    /**
     * The SourceDebug bttribute of this clbss.
     */
    privbte ByteVector sourceDebug;

    /**
     * The constbnt pool item thbt contbins the nbme of the enclosing clbss of
     * this clbss.
     */
    privbte int enclosingMethodOwner;

    /**
     * The constbnt pool item thbt contbins the nbme bnd descriptor of the
     * enclosing method of this clbss.
     */
    privbte int enclosingMethod;

    /**
     * The runtime visible bnnotbtions of this clbss.
     */
    privbte AnnotbtionWriter bnns;

    /**
     * The runtime invisible bnnotbtions of this clbss.
     */
    privbte AnnotbtionWriter ibnns;

    /**
     * The runtime visible type bnnotbtions of this clbss.
     */
    privbte AnnotbtionWriter tbnns;

    /**
     * The runtime invisible type bnnotbtions of this clbss.
     */
    privbte AnnotbtionWriter itbnns;

    /**
     * The non stbndbrd bttributes of this clbss.
     */
    privbte Attribute bttrs;

    /**
     * The number of entries in the InnerClbsses bttribute.
     */
    privbte int innerClbssesCount;

    /**
     * The InnerClbsses bttribute.
     */
    privbte ByteVector innerClbsses;

    /**
     * The number of entries in the BootstrbpMethods bttribute.
     */
    int bootstrbpMethodsCount;

    /**
     * The BootstrbpMethods bttribute.
     */
    ByteVector bootstrbpMethods;

    /**
     * The fields of this clbss. These fields bre stored in b linked list of
     * {@link FieldWriter} objects, linked to ebch other by their
     * {@link FieldWriter#fv} field. This field stores the first element of this
     * list.
     */
    FieldWriter firstField;

    /**
     * The fields of this clbss. These fields bre stored in b linked list of
     * {@link FieldWriter} objects, linked to ebch other by their
     * {@link FieldWriter#fv} field. This field stores the lbst element of this
     * list.
     */
    FieldWriter lbstField;

    /**
     * The methods of this clbss. These methods bre stored in b linked list of
     * {@link MethodWriter} objects, linked to ebch other by their
     * {@link MethodWriter#mv} field. This field stores the first element of
     * this list.
     */
    MethodWriter firstMethod;

    /**
     * The methods of this clbss. These methods bre stored in b linked list of
     * {@link MethodWriter} objects, linked to ebch other by their
     * {@link MethodWriter#mv} field. This field stores the lbst element of this
     * list.
     */
    MethodWriter lbstMethod;

    /**
     * <tt>true</tt> if the mbximum stbck size bnd number of locbl vbribbles
     * must be butombticblly computed.
     */
    privbte boolebn computeMbxs;

    /**
     * <tt>true</tt> if the stbck mbp frbmes must be recomputed from scrbtch.
     */
    privbte boolebn computeFrbmes;

    /**
     * <tt>true</tt> if the stbck mbp tbbles of this clbss bre invblid. The
     * {@link MethodWriter#resizeInstructions} method cbnnot trbnsform existing
     * stbck mbp tbbles, bnd so produces potentiblly invblid clbsses when it is
     * executed. In this cbse the clbss is rerebd bnd rewritten with the
     * {@link #COMPUTE_FRAMES} option (the resizeInstructions method cbn resize
     * stbck mbp tbbles when this option is used).
     */
    boolebn invblidFrbmes;

    // ------------------------------------------------------------------------
    // Stbtic initiblizer
    // ------------------------------------------------------------------------

    /**
     * Computes the instruction types of JVM opcodes.
     */
    stbtic {
        int i;
        byte[] b = new byte[220];
        String s = "AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADD"
                + "DDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                + "AAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAA"
                + "AAAAGGGGGGGHIFBFAAFFAARQJJKKJJJJJJJJJJJJJJJJJJ";
        for (i = 0; i < b.length; ++i) {
            b[i] = (byte) (s.chbrAt(i) - 'A');
        }
        TYPE = b;

        // code to generbte the bbove string
        //
        // // SBYTE_INSN instructions
        // b[Constbnts.NEWARRAY] = SBYTE_INSN;
        // b[Constbnts.BIPUSH] = SBYTE_INSN;
        //
        // // SHORT_INSN instructions
        // b[Constbnts.SIPUSH] = SHORT_INSN;
        //
        // // (IMPL)VAR_INSN instructions
        // b[Constbnts.RET] = VAR_INSN;
        // for (i = Constbnts.ILOAD; i <= Constbnts.ALOAD; ++i) {
        // b[i] = VAR_INSN;
        // }
        // for (i = Constbnts.ISTORE; i <= Constbnts.ASTORE; ++i) {
        // b[i] = VAR_INSN;
        // }
        // for (i = 26; i <= 45; ++i) { // ILOAD_0 to ALOAD_3
        // b[i] = IMPLVAR_INSN;
        // }
        // for (i = 59; i <= 78; ++i) { // ISTORE_0 to ASTORE_3
        // b[i] = IMPLVAR_INSN;
        // }
        //
        // // TYPE_INSN instructions
        // b[Constbnts.NEW] = TYPE_INSN;
        // b[Constbnts.ANEWARRAY] = TYPE_INSN;
        // b[Constbnts.CHECKCAST] = TYPE_INSN;
        // b[Constbnts.INSTANCEOF] = TYPE_INSN;
        //
        // // (Set)FIELDORMETH_INSN instructions
        // for (i = Constbnts.GETSTATIC; i <= Constbnts.INVOKESTATIC; ++i) {
        // b[i] = FIELDORMETH_INSN;
        // }
        // b[Constbnts.INVOKEINTERFACE] = ITFMETH_INSN;
        // b[Constbnts.INVOKEDYNAMIC] = INDYMETH_INSN;
        //
        // // LABEL(W)_INSN instructions
        // for (i = Constbnts.IFEQ; i <= Constbnts.JSR; ++i) {
        // b[i] = LABEL_INSN;
        // }
        // b[Constbnts.IFNULL] = LABEL_INSN;
        // b[Constbnts.IFNONNULL] = LABEL_INSN;
        // b[200] = LABELW_INSN; // GOTO_W
        // b[201] = LABELW_INSN; // JSR_W
        // // temporbry opcodes used internblly by ASM - see Lbbel bnd
        // MethodWriter
        // for (i = 202; i < 220; ++i) {
        // b[i] = LABEL_INSN;
        // }
        //
        // // LDC(_W) instructions
        // b[Constbnts.LDC] = LDC_INSN;
        // b[19] = LDCW_INSN; // LDC_W
        // b[20] = LDCW_INSN; // LDC2_W
        //
        // // specibl instructions
        // b[Constbnts.IINC] = IINC_INSN;
        // b[Constbnts.TABLESWITCH] = TABL_INSN;
        // b[Constbnts.LOOKUPSWITCH] = LOOK_INSN;
        // b[Constbnts.MULTIANEWARRAY] = MANA_INSN;
        // b[196] = WIDE_INSN; // WIDE
        //
        // for (i = 0; i < b.length; ++i) {
        // System.err.print((chbr)('A' + b[i]));
        // }
        // System.err.println();
    }

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Constructs b new {@link ClbssWriter} object.
     *
     * @pbrbm flbgs
     *            option flbgs thbt cbn be used to modify the defbult behbvior
     *            of this clbss. See {@link #COMPUTE_MAXS},
     *            {@link #COMPUTE_FRAMES}.
     */
    public ClbssWriter(finbl int flbgs) {
        super(Opcodes.ASM5);
        index = 1;
        pool = new ByteVector();
        items = new Item[256];
        threshold = (int) (0.75d * items.length);
        key = new Item();
        key2 = new Item();
        key3 = new Item();
        key4 = new Item();
        this.computeMbxs = (flbgs & COMPUTE_MAXS) != 0;
        this.computeFrbmes = (flbgs & COMPUTE_FRAMES) != 0;
    }

    /**
     * Constructs b new {@link ClbssWriter} object bnd enbbles optimizbtions for
     * "mostly bdd" bytecode trbnsformbtions. These optimizbtions bre the
     * following:
     *
     * <ul>
     * <li>The constbnt pool from the originbl clbss is copied bs is in the new
     * clbss, which sbves time. New constbnt pool entries will be bdded bt the
     * end if necessbry, but unused constbnt pool entries <i>won't be
     * removed</i>.</li>
     * <li>Methods thbt bre not trbnsformed bre copied bs is in the new clbss,
     * directly from the originbl clbss bytecode (i.e. without emitting visit
     * events for bll the method instructions), which sbves b <i>lot</i> of
     * time. Untrbnsformed methods bre detected by the fbct thbt the
     * {@link ClbssRebder} receives {@link MethodVisitor} objects thbt come from
     * b {@link ClbssWriter} (bnd not from bny other {@link ClbssVisitor}
     * instbnce).</li>
     * </ul>
     *
     * @pbrbm clbssRebder
     *            the {@link ClbssRebder} used to rebd the originbl clbss. It
     *            will be used to copy the entire constbnt pool from the
     *            originbl clbss bnd blso to copy other frbgments of originbl
     *            bytecode where bpplicbble.
     * @pbrbm flbgs
     *            option flbgs thbt cbn be used to modify the defbult behbvior
     *            of this clbss. <i>These option flbgs do not bffect methods
     *            thbt bre copied bs is in the new clbss. This mebns thbt the
     *            mbximum stbck size nor the stbck frbmes will be computed for
     *            these methods</i>. See {@link #COMPUTE_MAXS},
     *            {@link #COMPUTE_FRAMES}.
     */
    public ClbssWriter(finbl ClbssRebder clbssRebder, finbl int flbgs) {
        this(flbgs);
        clbssRebder.copyPool(this);
        this.cr = clbssRebder;
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the ClbssVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public finbl void visit(finbl int version, finbl int bccess,
            finbl String nbme, finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        this.version = version;
        this.bccess = bccess;
        this.nbme = newClbss(nbme);
        thisNbme = nbme;
        if (ClbssRebder.SIGNATURES && signbture != null) {
            this.signbture = newUTF8(signbture);
        }
        this.superNbme = superNbme == null ? 0 : newClbss(superNbme);
        if (interfbces != null && interfbces.length > 0) {
            interfbceCount = interfbces.length;
            this.interfbces = new int[interfbceCount];
            for (int i = 0; i < interfbceCount; ++i) {
                this.interfbces[i] = newClbss(interfbces[i]);
            }
        }
    }

    @Override
    public finbl void visitSource(finbl String file, finbl String debug) {
        if (file != null) {
            sourceFile = newUTF8(file);
        }
        if (debug != null) {
            sourceDebug = new ByteVector().encodeUTF8(debug, 0,
                    Integer.MAX_VALUE);
        }
    }

    @Override
    public finbl void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc) {
        enclosingMethodOwner = newClbss(owner);
        if (nbme != null && desc != null) {
            enclosingMethod = newNbmeType(nbme, desc);
        }
    }

    @Override
    public finbl AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        // write type, bnd reserve spbce for vblues count
        bv.putShort(newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(this, true, bv, bv, 2);
        if (visible) {
            bw.next = bnns;
            bnns = bw;
        } else {
            bw.next = ibnns;
            ibnns = bw;
        }
        return bw;
    }

    @Override
    public finbl AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        // write tbrget_type bnd tbrget_info
        AnnotbtionWriter.putTbrget(typeRef, typePbth, bv);
        // write type, bnd reserve spbce for vblues count
        bv.putShort(newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(this, true, bv, bv,
                bv.length - 2);
        if (visible) {
            bw.next = tbnns;
            tbnns = bw;
        } else {
            bw.next = itbnns;
            itbnns = bw;
        }
        return bw;
    }

    @Override
    public finbl void visitAttribute(finbl Attribute bttr) {
        bttr.next = bttrs;
        bttrs = bttr;
    }

    @Override
    public finbl void visitInnerClbss(finbl String nbme,
            finbl String outerNbme, finbl String innerNbme, finbl int bccess) {
        if (innerClbsses == null) {
            innerClbsses = new ByteVector();
        }
        // Sec. 4.7.6 of the JVMS stbtes "Every CONSTANT_Clbss_info entry in the
        // constbnt_pool tbble which represents b clbss or interfbce C thbt is
        // not b pbckbge member must hbve exbctly one corresponding entry in the
        // clbsses brrby". To bvoid duplicbtes we keep trbck in the intVbl field
        // of the Item of ebch CONSTANT_Clbss_info entry C whether bn inner
        // clbss entry hbs blrebdy been bdded for C (this field is unused for
        // clbss entries, bnd chbnging its vblue does not chbnge the hbshcode
        // bnd equblity tests). If so we store the index of this inner clbss
        // entry (plus one) in intVbl. This hbck bllows duplicbte detection in
        // O(1) time.
        Item nbmeItem = newClbssItem(nbme);
        if (nbmeItem.intVbl == 0) {
            ++innerClbssesCount;
            innerClbsses.putShort(nbmeItem.index);
            innerClbsses.putShort(outerNbme == null ? 0 : newClbss(outerNbme));
            innerClbsses.putShort(innerNbme == null ? 0 : newUTF8(innerNbme));
            innerClbsses.putShort(bccess);
            nbmeItem.intVbl = innerClbssesCount;
        } else {
            // Compbre the inner clbsses entry nbmeItem.intVbl - 1 with the
            // brguments of this method bnd throw bn exception if there is b
            // difference?
        }
    }

    @Override
    public finbl FieldVisitor visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        return new FieldWriter(this, bccess, nbme, desc, signbture, vblue);
    }

    @Override
    public finbl MethodVisitor visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        return new MethodWriter(this, bccess, nbme, desc, signbture,
                exceptions, computeMbxs, computeFrbmes);
    }

    @Override
    public finbl void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Other public methods
    // ------------------------------------------------------------------------

    /**
     * Returns the bytecode of the clbss thbt wbs build with this clbss writer.
     *
     * @return the bytecode of the clbss thbt wbs build with this clbss writer.
     */
    public byte[] toByteArrby() {
        if (index > 0xFFFF) {
            throw new RuntimeException("Clbss file too lbrge!");
        }
        // computes the rebl size of the bytecode of this clbss
        int size = 24 + 2 * interfbceCount;
        int nbFields = 0;
        FieldWriter fb = firstField;
        while (fb != null) {
            ++nbFields;
            size += fb.getSize();
            fb = (FieldWriter) fb.fv;
        }
        int nbMethods = 0;
        MethodWriter mb = firstMethod;
        while (mb != null) {
            ++nbMethods;
            size += mb.getSize();
            mb = (MethodWriter) mb.mv;
        }
        int bttributeCount = 0;
        if (bootstrbpMethods != null) {
            // we put it bs first bttribute in order to improve b bit
            // ClbssRebder.copyBootstrbpMethods
            ++bttributeCount;
            size += 8 + bootstrbpMethods.length;
            newUTF8("BootstrbpMethods");
        }
        if (ClbssRebder.SIGNATURES && signbture != 0) {
            ++bttributeCount;
            size += 8;
            newUTF8("Signbture");
        }
        if (sourceFile != 0) {
            ++bttributeCount;
            size += 8;
            newUTF8("SourceFile");
        }
        if (sourceDebug != null) {
            ++bttributeCount;
            size += sourceDebug.length + 6;
            newUTF8("SourceDebugExtension");
        }
        if (enclosingMethodOwner != 0) {
            ++bttributeCount;
            size += 10;
            newUTF8("EnclosingMethod");
        }
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            ++bttributeCount;
            size += 6;
            newUTF8("Deprecbted");
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            if ((version & 0xFFFF) < Opcodes.V1_5
                    || (bccess & ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                ++bttributeCount;
                size += 6;
                newUTF8("Synthetic");
            }
        }
        if (innerClbsses != null) {
            ++bttributeCount;
            size += 8 + innerClbsses.length;
            newUTF8("InnerClbsses");
        }
        if (ClbssRebder.ANNOTATIONS && bnns != null) {
            ++bttributeCount;
            size += 8 + bnns.getSize();
            newUTF8("RuntimeVisibleAnnotbtions");
        }
        if (ClbssRebder.ANNOTATIONS && ibnns != null) {
            ++bttributeCount;
            size += 8 + ibnns.getSize();
            newUTF8("RuntimeInvisibleAnnotbtions");
        }
        if (ClbssRebder.ANNOTATIONS && tbnns != null) {
            ++bttributeCount;
            size += 8 + tbnns.getSize();
            newUTF8("RuntimeVisibleTypeAnnotbtions");
        }
        if (ClbssRebder.ANNOTATIONS && itbnns != null) {
            ++bttributeCount;
            size += 8 + itbnns.getSize();
            newUTF8("RuntimeInvisibleTypeAnnotbtions");
        }
        if (bttrs != null) {
            bttributeCount += bttrs.getCount();
            size += bttrs.getSize(this, null, 0, -1, -1);
        }
        size += pool.length;
        // bllocbtes b byte vector of this size, in order to bvoid unnecessbry
        // brrbycopy operbtions in the ByteVector.enlbrge() method
        ByteVector out = new ByteVector(size);
        out.putInt(0xCAFEBABE).putInt(version);
        out.putShort(index).putByteArrby(pool.dbtb, 0, pool.length);
        int mbsk = Opcodes.ACC_DEPRECATED | ACC_SYNTHETIC_ATTRIBUTE
                | ((bccess & ACC_SYNTHETIC_ATTRIBUTE) / TO_ACC_SYNTHETIC);
        out.putShort(bccess & ~mbsk).putShort(nbme).putShort(superNbme);
        out.putShort(interfbceCount);
        for (int i = 0; i < interfbceCount; ++i) {
            out.putShort(interfbces[i]);
        }
        out.putShort(nbFields);
        fb = firstField;
        while (fb != null) {
            fb.put(out);
            fb = (FieldWriter) fb.fv;
        }
        out.putShort(nbMethods);
        mb = firstMethod;
        while (mb != null) {
            mb.put(out);
            mb = (MethodWriter) mb.mv;
        }
        out.putShort(bttributeCount);
        if (bootstrbpMethods != null) {
            out.putShort(newUTF8("BootstrbpMethods"));
            out.putInt(bootstrbpMethods.length + 2).putShort(
                    bootstrbpMethodsCount);
            out.putByteArrby(bootstrbpMethods.dbtb, 0, bootstrbpMethods.length);
        }
        if (ClbssRebder.SIGNATURES && signbture != 0) {
            out.putShort(newUTF8("Signbture")).putInt(2).putShort(signbture);
        }
        if (sourceFile != 0) {
            out.putShort(newUTF8("SourceFile")).putInt(2).putShort(sourceFile);
        }
        if (sourceDebug != null) {
            int len = sourceDebug.length;
            out.putShort(newUTF8("SourceDebugExtension")).putInt(len);
            out.putByteArrby(sourceDebug.dbtb, 0, len);
        }
        if (enclosingMethodOwner != 0) {
            out.putShort(newUTF8("EnclosingMethod")).putInt(4);
            out.putShort(enclosingMethodOwner).putShort(enclosingMethod);
        }
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            out.putShort(newUTF8("Deprecbted")).putInt(0);
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            if ((version & 0xFFFF) < Opcodes.V1_5
                    || (bccess & ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                out.putShort(newUTF8("Synthetic")).putInt(0);
            }
        }
        if (innerClbsses != null) {
            out.putShort(newUTF8("InnerClbsses"));
            out.putInt(innerClbsses.length + 2).putShort(innerClbssesCount);
            out.putByteArrby(innerClbsses.dbtb, 0, innerClbsses.length);
        }
        if (ClbssRebder.ANNOTATIONS && bnns != null) {
            out.putShort(newUTF8("RuntimeVisibleAnnotbtions"));
            bnns.put(out);
        }
        if (ClbssRebder.ANNOTATIONS && ibnns != null) {
            out.putShort(newUTF8("RuntimeInvisibleAnnotbtions"));
            ibnns.put(out);
        }
        if (ClbssRebder.ANNOTATIONS && tbnns != null) {
            out.putShort(newUTF8("RuntimeVisibleTypeAnnotbtions"));
            tbnns.put(out);
        }
        if (ClbssRebder.ANNOTATIONS && itbnns != null) {
            out.putShort(newUTF8("RuntimeInvisibleTypeAnnotbtions"));
            itbnns.put(out);
        }
        if (bttrs != null) {
            bttrs.put(this, null, 0, -1, -1, out);
        }
        if (invblidFrbmes) {
            bnns = null;
            ibnns = null;
            bttrs = null;
            innerClbssesCount = 0;
            innerClbsses = null;
            bootstrbpMethodsCount = 0;
            bootstrbpMethods = null;
            firstField = null;
            lbstField = null;
            firstMethod = null;
            lbstMethod = null;
            computeMbxs = fblse;
            computeFrbmes = true;
            invblidFrbmes = fblse;
            new ClbssRebder(out.dbtb).bccept(this, ClbssRebder.SKIP_FRAMES);
            return toByteArrby();
        }
        return out.dbtb;
    }

    // ------------------------------------------------------------------------
    // Utility methods: constbnt pool mbnbgement
    // ------------------------------------------------------------------------

    /**
     * Adds b number or string constbnt to the constbnt pool of the clbss being
     * build. Does nothing if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm cst
     *            the vblue of the constbnt to be bdded to the constbnt pool.
     *            This pbrbmeter must be bn {@link Integer}, b {@link Flobt}, b
     *            {@link Long}, b {@link Double}, b {@link String} or b
     *            {@link Type}.
     * @return b new or blrebdy existing constbnt item with the given vblue.
     */
    Item newConstItem(finbl Object cst) {
        if (cst instbnceof Integer) {
            int vbl = ((Integer) cst).intVblue();
            return newInteger(vbl);
        } else if (cst instbnceof Byte) {
            int vbl = ((Byte) cst).intVblue();
            return newInteger(vbl);
        } else if (cst instbnceof Chbrbcter) {
            int vbl = ((Chbrbcter) cst).chbrVblue();
            return newInteger(vbl);
        } else if (cst instbnceof Short) {
            int vbl = ((Short) cst).intVblue();
            return newInteger(vbl);
        } else if (cst instbnceof Boolebn) {
            int vbl = ((Boolebn) cst).boolebnVblue() ? 1 : 0;
            return newInteger(vbl);
        } else if (cst instbnceof Flobt) {
            flobt vbl = ((Flobt) cst).flobtVblue();
            return newFlobt(vbl);
        } else if (cst instbnceof Long) {
            long vbl = ((Long) cst).longVblue();
            return newLong(vbl);
        } else if (cst instbnceof Double) {
            double vbl = ((Double) cst).doubleVblue();
            return newDouble(vbl);
        } else if (cst instbnceof String) {
            return newString((String) cst);
        } else if (cst instbnceof Type) {
            Type t = (Type) cst;
            int s = t.getSort();
            if (s == Type.OBJECT) {
                return newClbssItem(t.getInternblNbme());
            } else if (s == Type.METHOD) {
                return newMethodTypeItem(t.getDescriptor());
            } else { // s == primitive type or brrby
                return newClbssItem(t.getDescriptor());
            }
        } else if (cst instbnceof Hbndle) {
            Hbndle h = (Hbndle) cst;
            return newHbndleItem(h.tbg, h.owner, h.nbme, h.desc);
        } else {
            throw new IllegblArgumentException("vblue " + cst);
        }
    }

    /**
     * Adds b number or string constbnt to the constbnt pool of the clbss being
     * build. Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm cst
     *            the vblue of the constbnt to be bdded to the constbnt pool.
     *            This pbrbmeter must be bn {@link Integer}, b {@link Flobt}, b
     *            {@link Long}, b {@link Double} or b {@link String}.
     * @return the index of b new or blrebdy existing constbnt item with the
     *         given vblue.
     */
    public int newConst(finbl Object cst) {
        return newConstItem(cst).index;
    }

    /**
     * Adds bn UTF8 string to the constbnt pool of the clbss being build. Does
     * nothing if the constbnt pool blrebdy contbins b similbr item. <i>This
     * method is intended for {@link Attribute} sub clbsses, bnd is normblly not
     * needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm vblue
     *            the String vblue.
     * @return the index of b new or blrebdy existing UTF8 item.
     */
    public int newUTF8(finbl String vblue) {
        key.set(UTF8, vblue, null, null);
        Item result = get(key);
        if (result == null) {
            pool.putByte(UTF8).putUTF8(vblue);
            result = new Item(index++, key);
            put(result);
        }
        return result.index;
    }

    /**
     * Adds b clbss reference to the constbnt pool of the clbss being build.
     * Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm vblue
     *            the internbl nbme of the clbss.
     * @return b new or blrebdy existing clbss reference item.
     */
    Item newClbssItem(finbl String vblue) {
        key2.set(CLASS, vblue, null, null);
        Item result = get(key2);
        if (result == null) {
            pool.put12(CLASS, newUTF8(vblue));
            result = new Item(index++, key2);
            put(result);
        }
        return result;
    }

    /**
     * Adds b clbss reference to the constbnt pool of the clbss being build.
     * Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm vblue
     *            the internbl nbme of the clbss.
     * @return the index of b new or blrebdy existing clbss reference item.
     */
    public int newClbss(finbl String vblue) {
        return newClbssItem(vblue).index;
    }

    /**
     * Adds b method type reference to the constbnt pool of the clbss being
     * build. Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm methodDesc
     *            method descriptor of the method type.
     * @return b new or blrebdy existing method type reference item.
     */
    Item newMethodTypeItem(finbl String methodDesc) {
        key2.set(MTYPE, methodDesc, null, null);
        Item result = get(key2);
        if (result == null) {
            pool.put12(MTYPE, newUTF8(methodDesc));
            result = new Item(index++, key2);
            put(result);
        }
        return result;
    }

    /**
     * Adds b method type reference to the constbnt pool of the clbss being
     * build. Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm methodDesc
     *            method descriptor of the method type.
     * @return the index of b new or blrebdy existing method type reference
     *         item.
     */
    public int newMethodType(finbl String methodDesc) {
        return newMethodTypeItem(methodDesc).index;
    }

    /**
     * Adds b hbndle to the constbnt pool of the clbss being build. Does nothing
     * if the constbnt pool blrebdy contbins b similbr item. <i>This method is
     * intended for {@link Attribute} sub clbsses, bnd is normblly not needed by
     * clbss generbtors or bdbpters.</i>
     *
     * @pbrbm tbg
     *            the kind of this hbndle. Must be {@link Opcodes#H_GETFIELD},
     *            {@link Opcodes#H_GETSTATIC}, {@link Opcodes#H_PUTFIELD},
     *            {@link Opcodes#H_PUTSTATIC}, {@link Opcodes#H_INVOKEVIRTUAL},
     *            {@link Opcodes#H_INVOKESTATIC},
     *            {@link Opcodes#H_INVOKESPECIAL},
     *            {@link Opcodes#H_NEWINVOKESPECIAL} or
     *            {@link Opcodes#H_INVOKEINTERFACE}.
     * @pbrbm owner
     *            the internbl nbme of the field or method owner clbss.
     * @pbrbm nbme
     *            the nbme of the field or method.
     * @pbrbm desc
     *            the descriptor of the field or method.
     * @return b new or bn blrebdy existing method type reference item.
     */
    Item newHbndleItem(finbl int tbg, finbl String owner, finbl String nbme,
            finbl String desc) {
        key4.set(HANDLE_BASE + tbg, owner, nbme, desc);
        Item result = get(key4);
        if (result == null) {
            if (tbg <= Opcodes.H_PUTSTATIC) {
                put112(HANDLE, tbg, newField(owner, nbme, desc));
            } else {
                put112(HANDLE,
                        tbg,
                        newMethod(owner, nbme, desc,
                                tbg == Opcodes.H_INVOKEINTERFACE));
            }
            result = new Item(index++, key4);
            put(result);
        }
        return result;
    }

    /**
     * Adds b hbndle to the constbnt pool of the clbss being build. Does nothing
     * if the constbnt pool blrebdy contbins b similbr item. <i>This method is
     * intended for {@link Attribute} sub clbsses, bnd is normblly not needed by
     * clbss generbtors or bdbpters.</i>
     *
     * @pbrbm tbg
     *            the kind of this hbndle. Must be {@link Opcodes#H_GETFIELD},
     *            {@link Opcodes#H_GETSTATIC}, {@link Opcodes#H_PUTFIELD},
     *            {@link Opcodes#H_PUTSTATIC}, {@link Opcodes#H_INVOKEVIRTUAL},
     *            {@link Opcodes#H_INVOKESTATIC},
     *            {@link Opcodes#H_INVOKESPECIAL},
     *            {@link Opcodes#H_NEWINVOKESPECIAL} or
     *            {@link Opcodes#H_INVOKEINTERFACE}.
     * @pbrbm owner
     *            the internbl nbme of the field or method owner clbss.
     * @pbrbm nbme
     *            the nbme of the field or method.
     * @pbrbm desc
     *            the descriptor of the field or method.
     * @return the index of b new or blrebdy existing method type reference
     *         item.
     */
    public int newHbndle(finbl int tbg, finbl String owner, finbl String nbme,
            finbl String desc) {
        return newHbndleItem(tbg, owner, nbme, desc).index;
    }

    /**
     * Adds bn invokedynbmic reference to the constbnt pool of the clbss being
     * build. Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm nbme
     *            nbme of the invoked method.
     * @pbrbm desc
     *            descriptor of the invoke method.
     * @pbrbm bsm
     *            the bootstrbp method.
     * @pbrbm bsmArgs
     *            the bootstrbp method constbnt brguments.
     *
     * @return b new or bn blrebdy existing invokedynbmic type reference item.
     */
    Item newInvokeDynbmicItem(finbl String nbme, finbl String desc,
            finbl Hbndle bsm, finbl Object... bsmArgs) {
        // cbche for performbnce
        ByteVector bootstrbpMethods = this.bootstrbpMethods;
        if (bootstrbpMethods == null) {
            bootstrbpMethods = this.bootstrbpMethods = new ByteVector();
        }

        int position = bootstrbpMethods.length; // record current position

        int hbshCode = bsm.hbshCode();
        bootstrbpMethods.putShort(newHbndle(bsm.tbg, bsm.owner, bsm.nbme,
                bsm.desc));

        int brgsLength = bsmArgs.length;
        bootstrbpMethods.putShort(brgsLength);

        for (int i = 0; i < brgsLength; i++) {
            Object bsmArg = bsmArgs[i];
            hbshCode ^= bsmArg.hbshCode();
            bootstrbpMethods.putShort(newConst(bsmArg));
        }

        byte[] dbtb = bootstrbpMethods.dbtb;
        int length = (1 + 1 + brgsLength) << 1; // (bsm + brgCount + brguments)
        hbshCode &= 0x7FFFFFFF;
        Item result = items[hbshCode % items.length];
        loop: while (result != null) {
            if (result.type != BSM || result.hbshCode != hbshCode) {
                result = result.next;
                continue;
            }

            // becbuse the dbtb encode the size of the brgument
            // we don't need to test if these size bre equbls
            int resultPosition = result.intVbl;
            for (int p = 0; p < length; p++) {
                if (dbtb[position + p] != dbtb[resultPosition + p]) {
                    result = result.next;
                    continue loop;
                }
            }
            brebk;
        }

        int bootstrbpMethodIndex;
        if (result != null) {
            bootstrbpMethodIndex = result.index;
            bootstrbpMethods.length = position; // revert to old position
        } else {
            bootstrbpMethodIndex = bootstrbpMethodsCount++;
            result = new Item(bootstrbpMethodIndex);
            result.set(position, hbshCode);
            put(result);
        }

        // now, crebte the InvokeDynbmic constbnt
        key3.set(nbme, desc, bootstrbpMethodIndex);
        result = get(key3);
        if (result == null) {
            put122(INDY, bootstrbpMethodIndex, newNbmeType(nbme, desc));
            result = new Item(index++, key3);
            put(result);
        }
        return result;
    }

    /**
     * Adds bn invokedynbmic reference to the constbnt pool of the clbss being
     * build. Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm nbme
     *            nbme of the invoked method.
     * @pbrbm desc
     *            descriptor of the invoke method.
     * @pbrbm bsm
     *            the bootstrbp method.
     * @pbrbm bsmArgs
     *            the bootstrbp method constbnt brguments.
     *
     * @return the index of b new or blrebdy existing invokedynbmic reference
     *         item.
     */
    public int newInvokeDynbmic(finbl String nbme, finbl String desc,
            finbl Hbndle bsm, finbl Object... bsmArgs) {
        return newInvokeDynbmicItem(nbme, desc, bsm, bsmArgs).index;
    }

    /**
     * Adds b field reference to the constbnt pool of the clbss being build.
     * Does nothing if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm owner
     *            the internbl nbme of the field's owner clbss.
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor.
     * @return b new or blrebdy existing field reference item.
     */
    Item newFieldItem(finbl String owner, finbl String nbme, finbl String desc) {
        key3.set(FIELD, owner, nbme, desc);
        Item result = get(key3);
        if (result == null) {
            put122(FIELD, newClbss(owner), newNbmeType(nbme, desc));
            result = new Item(index++, key3);
            put(result);
        }
        return result;
    }

    /**
     * Adds b field reference to the constbnt pool of the clbss being build.
     * Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm owner
     *            the internbl nbme of the field's owner clbss.
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor.
     * @return the index of b new or blrebdy existing field reference item.
     */
    public int newField(finbl String owner, finbl String nbme, finbl String desc) {
        return newFieldItem(owner, nbme, desc).index;
    }

    /**
     * Adds b method reference to the constbnt pool of the clbss being build.
     * Does nothing if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm owner
     *            the internbl nbme of the method's owner clbss.
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor.
     * @pbrbm itf
     *            <tt>true</tt> if <tt>owner</tt> is bn interfbce.
     * @return b new or blrebdy existing method reference item.
     */
    Item newMethodItem(finbl String owner, finbl String nbme,
            finbl String desc, finbl boolebn itf) {
        int type = itf ? IMETH : METH;
        key3.set(type, owner, nbme, desc);
        Item result = get(key3);
        if (result == null) {
            put122(type, newClbss(owner), newNbmeType(nbme, desc));
            result = new Item(index++, key3);
            put(result);
        }
        return result;
    }

    /**
     * Adds b method reference to the constbnt pool of the clbss being build.
     * Does nothing if the constbnt pool blrebdy contbins b similbr item.
     * <i>This method is intended for {@link Attribute} sub clbsses, bnd is
     * normblly not needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm owner
     *            the internbl nbme of the method's owner clbss.
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor.
     * @pbrbm itf
     *            <tt>true</tt> if <tt>owner</tt> is bn interfbce.
     * @return the index of b new or blrebdy existing method reference item.
     */
    public int newMethod(finbl String owner, finbl String nbme,
            finbl String desc, finbl boolebn itf) {
        return newMethodItem(owner, nbme, desc, itf).index;
    }

    /**
     * Adds bn integer to the constbnt pool of the clbss being build. Does
     * nothing if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm vblue
     *            the int vblue.
     * @return b new or blrebdy existing int item.
     */
    Item newInteger(finbl int vblue) {
        key.set(vblue);
        Item result = get(key);
        if (result == null) {
            pool.putByte(INT).putInt(vblue);
            result = new Item(index++, key);
            put(result);
        }
        return result;
    }

    /**
     * Adds b flobt to the constbnt pool of the clbss being build. Does nothing
     * if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm vblue
     *            the flobt vblue.
     * @return b new or blrebdy existing flobt item.
     */
    Item newFlobt(finbl flobt vblue) {
        key.set(vblue);
        Item result = get(key);
        if (result == null) {
            pool.putByte(FLOAT).putInt(key.intVbl);
            result = new Item(index++, key);
            put(result);
        }
        return result;
    }

    /**
     * Adds b long to the constbnt pool of the clbss being build. Does nothing
     * if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm vblue
     *            the long vblue.
     * @return b new or blrebdy existing long item.
     */
    Item newLong(finbl long vblue) {
        key.set(vblue);
        Item result = get(key);
        if (result == null) {
            pool.putByte(LONG).putLong(vblue);
            result = new Item(index, key);
            index += 2;
            put(result);
        }
        return result;
    }

    /**
     * Adds b double to the constbnt pool of the clbss being build. Does nothing
     * if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm vblue
     *            the double vblue.
     * @return b new or blrebdy existing double item.
     */
    Item newDouble(finbl double vblue) {
        key.set(vblue);
        Item result = get(key);
        if (result == null) {
            pool.putByte(DOUBLE).putLong(key.longVbl);
            result = new Item(index, key);
            index += 2;
            put(result);
        }
        return result;
    }

    /**
     * Adds b string to the constbnt pool of the clbss being build. Does nothing
     * if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm vblue
     *            the String vblue.
     * @return b new or blrebdy existing string item.
     */
    privbte Item newString(finbl String vblue) {
        key2.set(STR, vblue, null, null);
        Item result = get(key2);
        if (result == null) {
            pool.put12(STR, newUTF8(vblue));
            result = new Item(index++, key2);
            put(result);
        }
        return result;
    }

    /**
     * Adds b nbme bnd type to the constbnt pool of the clbss being build. Does
     * nothing if the constbnt pool blrebdy contbins b similbr item. <i>This
     * method is intended for {@link Attribute} sub clbsses, bnd is normblly not
     * needed by clbss generbtors or bdbpters.</i>
     *
     * @pbrbm nbme
     *            b nbme.
     * @pbrbm desc
     *            b type descriptor.
     * @return the index of b new or blrebdy existing nbme bnd type item.
     */
    public int newNbmeType(finbl String nbme, finbl String desc) {
        return newNbmeTypeItem(nbme, desc).index;
    }

    /**
     * Adds b nbme bnd type to the constbnt pool of the clbss being build. Does
     * nothing if the constbnt pool blrebdy contbins b similbr item.
     *
     * @pbrbm nbme
     *            b nbme.
     * @pbrbm desc
     *            b type descriptor.
     * @return b new or blrebdy existing nbme bnd type item.
     */
    Item newNbmeTypeItem(finbl String nbme, finbl String desc) {
        key2.set(NAME_TYPE, nbme, desc, null);
        Item result = get(key2);
        if (result == null) {
            put122(NAME_TYPE, newUTF8(nbme), newUTF8(desc));
            result = new Item(index++, key2);
            put(result);
        }
        return result;
    }

    /**
     * Adds the given internbl nbme to {@link #typeTbble} bnd returns its index.
     * Does nothing if the type tbble blrebdy contbins this internbl nbme.
     *
     * @pbrbm type
     *            the internbl nbme to be bdded to the type tbble.
     * @return the index of this internbl nbme in the type tbble.
     */
    int bddType(finbl String type) {
        key.set(TYPE_NORMAL, type, null, null);
        Item result = get(key);
        if (result == null) {
            result = bddType(key);
        }
        return result.index;
    }

    /**
     * Adds the given "uninitiblized" type to {@link #typeTbble} bnd returns its
     * index. This method is used for UNINITIALIZED types, mbde of bn internbl
     * nbme bnd b bytecode offset.
     *
     * @pbrbm type
     *            the internbl nbme to be bdded to the type tbble.
     * @pbrbm offset
     *            the bytecode offset of the NEW instruction thbt crebted this
     *            UNINITIALIZED type vblue.
     * @return the index of this internbl nbme in the type tbble.
     */
    int bddUninitiblizedType(finbl String type, finbl int offset) {
        key.type = TYPE_UNINIT;
        key.intVbl = offset;
        key.strVbl1 = type;
        key.hbshCode = 0x7FFFFFFF & (TYPE_UNINIT + type.hbshCode() + offset);
        Item result = get(key);
        if (result == null) {
            result = bddType(key);
        }
        return result.index;
    }

    /**
     * Adds the given Item to {@link #typeTbble}.
     *
     * @pbrbm item
     *            the vblue to be bdded to the type tbble.
     * @return the bdded Item, which b new Item instbnce with the sbme vblue bs
     *         the given Item.
     */
    privbte Item bddType(finbl Item item) {
        ++typeCount;
        Item result = new Item(typeCount, key);
        put(result);
        if (typeTbble == null) {
            typeTbble = new Item[16];
        }
        if (typeCount == typeTbble.length) {
            Item[] newTbble = new Item[2 * typeTbble.length];
            System.brrbycopy(typeTbble, 0, newTbble, 0, typeTbble.length);
            typeTbble = newTbble;
        }
        typeTbble[typeCount] = result;
        return result;
    }

    /**
     * Returns the index of the common super type of the two given types. This
     * method cblls {@link #getCommonSuperClbss} bnd cbches the result in the
     * {@link #items} hbsh tbble to speedup future cblls with the sbme
     * pbrbmeters.
     *
     * @pbrbm type1
     *            index of bn internbl nbme in {@link #typeTbble}.
     * @pbrbm type2
     *            index of bn internbl nbme in {@link #typeTbble}.
     * @return the index of the common super type of the two given types.
     */
    int getMergedType(finbl int type1, finbl int type2) {
        key2.type = TYPE_MERGED;
        key2.longVbl = type1 | (((long) type2) << 32);
        key2.hbshCode = 0x7FFFFFFF & (TYPE_MERGED + type1 + type2);
        Item result = get(key2);
        if (result == null) {
            String t = typeTbble[type1].strVbl1;
            String u = typeTbble[type2].strVbl1;
            key2.intVbl = bddType(getCommonSuperClbss(t, u));
            result = new Item((short) 0, key2);
            put(result);
        }
        return result.intVbl;
    }

    /**
     * Returns the common super type of the two given types. The defbult
     * implementbtion of this method <i>lobds</i> the two given clbsses bnd uses
     * the jbvb.lbng.Clbss methods to find the common super clbss. It cbn be
     * overridden to compute this common super type in other wbys, in pbrticulbr
     * without bctublly lobding bny clbss, or to tbke into bccount the clbss
     * thbt is currently being generbted by this ClbssWriter, which cbn of
     * course not be lobded since it is under construction.
     *
     * @pbrbm type1
     *            the internbl nbme of b clbss.
     * @pbrbm type2
     *            the internbl nbme of bnother clbss.
     * @return the internbl nbme of the common super clbss of the two given
     *         clbsses.
     */
    protected String getCommonSuperClbss(finbl String type1, finbl String type2) {
        Clbss<?> c, d;
        ClbssLobder clbssLobder = getClbss().getClbssLobder();
        try {
            c = Clbss.forNbme(type1.replbce('/', '.'), fblse, clbssLobder);
            d = Clbss.forNbme(type2.replbce('/', '.'), fblse, clbssLobder);
        } cbtch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        if (c.isAssignbbleFrom(d)) {
            return type1;
        }
        if (d.isAssignbbleFrom(c)) {
            return type2;
        }
        if (c.isInterfbce() || d.isInterfbce()) {
            return "jbvb/lbng/Object";
        } else {
            do {
                c = c.getSuperclbss();
            } while (!c.isAssignbbleFrom(d));
            return c.getNbme().replbce('.', '/');
        }
    }

    /**
     * Returns the constbnt pool's hbsh tbble item which is equbl to the given
     * item.
     *
     * @pbrbm key
     *            b constbnt pool item.
     * @return the constbnt pool's hbsh tbble item which is equbl to the given
     *         item, or <tt>null</tt> if there is no such item.
     */
    privbte Item get(finbl Item key) {
        Item i = items[key.hbshCode % items.length];
        while (i != null && (i.type != key.type || !key.isEqublTo(i))) {
            i = i.next;
        }
        return i;
    }

    /**
     * Puts the given item in the constbnt pool's hbsh tbble. The hbsh tbble
     * <i>must</i> not blrebdy contbins this item.
     *
     * @pbrbm i
     *            the item to be bdded to the constbnt pool's hbsh tbble.
     */
    privbte void put(finbl Item i) {
        if (index + typeCount > threshold) {
            int ll = items.length;
            int nl = ll * 2 + 1;
            Item[] newItems = new Item[nl];
            for (int l = ll - 1; l >= 0; --l) {
                Item j = items[l];
                while (j != null) {
                    int index = j.hbshCode % newItems.length;
                    Item k = j.next;
                    j.next = newItems[index];
                    newItems[index] = j;
                    j = k;
                }
            }
            items = newItems;
            threshold = (int) (nl * 0.75);
        }
        int index = i.hbshCode % items.length;
        i.next = items[index];
        items[index] = i;
    }

    /**
     * Puts one byte bnd two shorts into the constbnt pool.
     *
     * @pbrbm b
     *            b byte.
     * @pbrbm s1
     *            b short.
     * @pbrbm s2
     *            bnother short.
     */
    privbte void put122(finbl int b, finbl int s1, finbl int s2) {
        pool.put12(b, s1).putShort(s2);
    }

    /**
     * Puts two bytes bnd one short into the constbnt pool.
     *
     * @pbrbm b1
     *            b byte.
     * @pbrbm b2
     *            bnother byte.
     * @pbrbm s
     *            b short.
     */
    privbte void put112(finbl int b1, finbl int b2, finbl int s) {
        pool.put11(b1, b2).putShort(s);
    }
}
