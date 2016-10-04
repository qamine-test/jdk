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
 * A lbbel represents b position in the bytecode of b method. Lbbels bre used
 * for jump, goto, bnd switch instructions, bnd for try cbtch blocks. A lbbel
 * designbtes the <i>instruction</i> thbt is just bfter. Note however thbt there
 * cbn be other elements between b lbbel bnd the instruction it designbtes (such
 * bs other lbbels, stbck mbp frbmes, line numbers, etc.).
 *
 * @buthor Eric Bruneton
 */
public clbss Lbbel {

    /**
     * Indicbtes if this lbbel is only used for debug bttributes. Such b lbbel
     * is not the stbrt of b bbsic block, the tbrget of b jump instruction, or
     * bn exception hbndler. It cbn be sbfely ignored in control flow grbph
     * bnblysis blgorithms (for optimizbtion purposes).
     */
    stbtic finbl int DEBUG = 1;

    /**
     * Indicbtes if the position of this lbbel is known.
     */
    stbtic finbl int RESOLVED = 2;

    /**
     * Indicbtes if this lbbel hbs been updbted, bfter instruction resizing.
     */
    stbtic finbl int RESIZED = 4;

    /**
     * Indicbtes if this bbsic block hbs been pushed in the bbsic block stbck.
     * See {@link MethodWriter#visitMbxs visitMbxs}.
     */
    stbtic finbl int PUSHED = 8;

    /**
     * Indicbtes if this lbbel is the tbrget of b jump instruction, or the stbrt
     * of bn exception hbndler.
     */
    stbtic finbl int TARGET = 16;

    /**
     * Indicbtes if b stbck mbp frbme must be stored for this lbbel.
     */
    stbtic finbl int STORE = 32;

    /**
     * Indicbtes if this lbbel corresponds to b rebchbble bbsic block.
     */
    stbtic finbl int REACHABLE = 64;

    /**
     * Indicbtes if this bbsic block ends with b JSR instruction.
     */
    stbtic finbl int JSR = 128;

    /**
     * Indicbtes if this bbsic block ends with b RET instruction.
     */
    stbtic finbl int RET = 256;

    /**
     * Indicbtes if this bbsic block is the stbrt of b subroutine.
     */
    stbtic finbl int SUBROUTINE = 512;

    /**
     * Indicbtes if this subroutine bbsic block hbs been visited by b
     * visitSubroutine(null, ...) cbll.
     */
    stbtic finbl int VISITED = 1024;

    /**
     * Indicbtes if this subroutine bbsic block hbs been visited by b
     * visitSubroutine(!null, ...) cbll.
     */
    stbtic finbl int VISITED2 = 2048;

    /**
     * Field used to bssocibte user informbtion to b lbbel. Wbrning: this field
     * is used by the ASM tree pbckbge. In order to use it with the ASM tree
     * pbckbge you must override the
     * {@link jdk.internbl.org.objectweb.bsm.tree.MethodNode#getLbbelNode} method.
     */
    public Object info;

    /**
     * Flbgs thbt indicbte the stbtus of this lbbel.
     *
     * @see #DEBUG
     * @see #RESOLVED
     * @see #RESIZED
     * @see #PUSHED
     * @see #TARGET
     * @see #STORE
     * @see #REACHABLE
     * @see #JSR
     * @see #RET
     */
    int stbtus;

    /**
     * The line number corresponding to this lbbel, if known.
     */
    int line;

    /**
     * The position of this lbbel in the code, if known.
     */
    int position;

    /**
     * Number of forwbrd references to this lbbel, times two.
     */
    privbte int referenceCount;

    /**
     * Informbtions bbout forwbrd references. Ebch forwbrd reference is
     * described by two consecutive integers in this brrby: the first one is the
     * position of the first byte of the bytecode instruction thbt contbins the
     * forwbrd reference, while the second is the position of the first byte of
     * the forwbrd reference itself. In fbct the sign of the first integer
     * indicbtes if this reference uses 2 or 4 bytes, bnd its bbsolute vblue
     * gives the position of the bytecode instruction. This brrby is blso used
     * bs b bitset to store the subroutines to which b bbsic block belongs. This
     * informbtion is needed in {@linked MethodWriter#visitMbxs}, bfter bll
     * forwbrd references hbve been resolved. Hence the sbme brrby cbn be used
     * for both purposes without problems.
     */
    privbte int[] srcAndRefPositions;

    // ------------------------------------------------------------------------

    /*
     * Fields for the control flow bnd dbtb flow grbph bnblysis blgorithms (used
     * to compute the mbximum stbck size or the stbck mbp frbmes). A control
     * flow grbph contbins one node per "bbsic block", bnd one edge per "jump"
     * from one bbsic block to bnother. Ebch node (i.e., ebch bbsic block) is
     * represented by the Lbbel object thbt corresponds to the first instruction
     * of this bbsic block. Ebch node blso stores the list of its successors in
     * the grbph, bs b linked list of Edge objects.
     *
     * The control flow bnblysis blgorithms used to compute the mbximum stbck
     * size or the stbck mbp frbmes bre similbr bnd use two steps. The first
     * step, during the visit of ebch instruction, builds informbtion bbout the
     * stbte of the locbl vbribbles bnd the operbnd stbck bt the end of ebch
     * bbsic block, cblled the "output frbme", <i>relbtively</i> to the frbme
     * stbte bt the beginning of the bbsic block, which is cblled the "input
     * frbme", bnd which is <i>unknown</i> during this step. The second step, in
     * {@link MethodWriter#visitMbxs}, is b fix point blgorithm thbt computes
     * informbtion bbout the input frbme of ebch bbsic block, from the input
     * stbte of the first bbsic block (known from the method signbture), bnd by
     * the using the previously computed relbtive output frbmes.
     *
     * The blgorithm used to compute the mbximum stbck size only computes the
     * relbtive output bnd bbsolute input stbck heights, while the blgorithm
     * used to compute stbck mbp frbmes computes relbtive output frbmes bnd
     * bbsolute input frbmes.
     */

    /**
     * Stbrt of the output stbck relbtively to the input stbck. The exbct
     * sembntics of this field depends on the blgorithm thbt is used.
     *
     * When only the mbximum stbck size is computed, this field is the number of
     * elements in the input stbck.
     *
     * When the stbck mbp frbmes bre completely computed, this field is the
     * offset of the first output stbck element relbtively to the top of the
     * input stbck. This offset is blwbys negbtive or null. A null offset mebns
     * thbt the output stbck must be bppended to the input stbck. A -n offset
     * mebns thbt the first n output stbck elements must replbce the top n input
     * stbck elements, bnd thbt the other elements must be bppended to the input
     * stbck.
     */
    int inputStbckTop;

    /**
     * Mbximum height rebched by the output stbck, relbtively to the top of the
     * input stbck. This mbximum is blwbys positive or null.
     */
    int outputStbckMbx;

    /**
     * Informbtion bbout the input bnd output stbck mbp frbmes of this bbsic
     * block. This field is only used when {@link ClbssWriter#COMPUTE_FRAMES}
     * option is used.
     */
    Frbme frbme;

    /**
     * The successor of this lbbel, in the order they bre visited. This linked
     * list does not include lbbels used for debug info only. If
     * {@link ClbssWriter#COMPUTE_FRAMES} option is used then, in bddition, it
     * does not contbin successive lbbels thbt denote the sbme bytecode position
     * (in this cbse only the first lbbel bppebrs in this list).
     */
    Lbbel successor;

    /**
     * The successors of this node in the control flow grbph. These successors
     * bre stored in b linked list of {@link Edge Edge} objects, linked to ebch
     * other by their {@link Edge#next} field.
     */
    Edge successors;

    /**
     * The next bbsic block in the bbsic block stbck. This stbck is used in the
     * mbin loop of the fix point blgorithm used in the second step of the
     * control flow bnblysis blgorithms. It is blso used in
     * {@link #visitSubroutine} to bvoid using b recursive method.
     *
     * @see MethodWriter#visitMbxs
     */
    Lbbel next;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Constructs b new lbbel.
     */
    public Lbbel() {
    }

    // ------------------------------------------------------------------------
    // Methods to compute offsets bnd to mbnbge forwbrd references
    // ------------------------------------------------------------------------

    /**
     * Returns the offset corresponding to this lbbel. This offset is computed
     * from the stbrt of the method's bytecode. <i>This method is intended for
     * {@link Attribute} sub clbsses, bnd is normblly not needed by clbss
     * generbtors or bdbpters.</i>
     *
     * @return the offset corresponding to this lbbel.
     * @throws IllegblStbteException
     *             if this lbbel is not resolved yet.
     */
    public int getOffset() {
        if ((stbtus & RESOLVED) == 0) {
            throw new IllegblStbteException(
                    "Lbbel offset position hbs not been resolved yet");
        }
        return position;
    }

    /**
     * Puts b reference to this lbbel in the bytecode of b method. If the
     * position of the lbbel is known, the offset is computed bnd written
     * directly. Otherwise, b null offset is written bnd b new forwbrd reference
     * is declbred for this lbbel.
     *
     * @pbrbm owner
     *            the code writer thbt cblls this method.
     * @pbrbm out
     *            the bytecode of the method.
     * @pbrbm source
     *            the position of first byte of the bytecode instruction thbt
     *            contbins this lbbel.
     * @pbrbm wideOffset
     *            <tt>true</tt> if the reference must be stored in 4 bytes, or
     *            <tt>fblse</tt> if it must be stored with 2 bytes.
     * @throws IllegblArgumentException
     *             if this lbbel hbs not been crebted by the given code writer.
     */
    void put(finbl MethodWriter owner, finbl ByteVector out, finbl int source,
            finbl boolebn wideOffset) {
        if ((stbtus & RESOLVED) == 0) {
            if (wideOffset) {
                bddReference(-1 - source, out.length);
                out.putInt(-1);
            } else {
                bddReference(source, out.length);
                out.putShort(-1);
            }
        } else {
            if (wideOffset) {
                out.putInt(position - source);
            } else {
                out.putShort(position - source);
            }
        }
    }

    /**
     * Adds b forwbrd reference to this lbbel. This method must be cblled only
     * for b true forwbrd reference, i.e. only if this lbbel is not resolved
     * yet. For bbckwbrd references, the offset of the reference cbn be, bnd
     * must be, computed bnd stored directly.
     *
     * @pbrbm sourcePosition
     *            the position of the referencing instruction. This position
     *            will be used to compute the offset of this forwbrd reference.
     * @pbrbm referencePosition
     *            the position where the offset for this forwbrd reference must
     *            be stored.
     */
    privbte void bddReference(finbl int sourcePosition,
            finbl int referencePosition) {
        if (srcAndRefPositions == null) {
            srcAndRefPositions = new int[6];
        }
        if (referenceCount >= srcAndRefPositions.length) {
            int[] b = new int[srcAndRefPositions.length + 6];
            System.brrbycopy(srcAndRefPositions, 0, b, 0,
                    srcAndRefPositions.length);
            srcAndRefPositions = b;
        }
        srcAndRefPositions[referenceCount++] = sourcePosition;
        srcAndRefPositions[referenceCount++] = referencePosition;
    }

    /**
     * Resolves bll forwbrd references to this lbbel. This method must be cblled
     * when this lbbel is bdded to the bytecode of the method, i.e. when its
     * position becomes known. This method fills in the blbnks thbt where left
     * in the bytecode by ebch forwbrd reference previously bdded to this lbbel.
     *
     * @pbrbm owner
     *            the code writer thbt cblls this method.
     * @pbrbm position
     *            the position of this lbbel in the bytecode.
     * @pbrbm dbtb
     *            the bytecode of the method.
     * @return <tt>true</tt> if b blbnk thbt wbs left for this lbbel wbs to
     *         smbll to store the offset. In such b cbse the corresponding jump
     *         instruction is replbced with b pseudo instruction (using unused
     *         opcodes) using bn unsigned two bytes offset. These pseudo
     *         instructions will need to be replbced with true instructions with
     *         wider offsets (4 bytes instebd of 2). This is done in
     *         {@link MethodWriter#resizeInstructions}.
     * @throws IllegblArgumentException
     *             if this lbbel hbs blrebdy been resolved, or if it hbs not
     *             been crebted by the given code writer.
     */
    boolebn resolve(finbl MethodWriter owner, finbl int position,
            finbl byte[] dbtb) {
        boolebn needUpdbte = fblse;
        this.stbtus |= RESOLVED;
        this.position = position;
        int i = 0;
        while (i < referenceCount) {
            int source = srcAndRefPositions[i++];
            int reference = srcAndRefPositions[i++];
            int offset;
            if (source >= 0) {
                offset = position - source;
                if (offset < Short.MIN_VALUE || offset > Short.MAX_VALUE) {
                    /*
                     * chbnges the opcode of the jump instruction, in order to
                     * be bble to find it lbter (see resizeInstructions in
                     * MethodWriter). These temporbry opcodes bre similbr to
                     * jump instruction opcodes, except thbt the 2 bytes offset
                     * is unsigned (bnd cbn therefore represent vblues from 0 to
                     * 65535, which is sufficient since the size of b method is
                     * limited to 65535 bytes).
                     */
                    int opcode = dbtb[reference - 1] & 0xFF;
                    if (opcode <= Opcodes.JSR) {
                        // chbnges IFEQ ... JSR to opcodes 202 to 217
                        dbtb[reference - 1] = (byte) (opcode + 49);
                    } else {
                        // chbnges IFNULL bnd IFNONNULL to opcodes 218 bnd 219
                        dbtb[reference - 1] = (byte) (opcode + 20);
                    }
                    needUpdbte = true;
                }
                dbtb[reference++] = (byte) (offset >>> 8);
                dbtb[reference] = (byte) offset;
            } else {
                offset = position + source + 1;
                dbtb[reference++] = (byte) (offset >>> 24);
                dbtb[reference++] = (byte) (offset >>> 16);
                dbtb[reference++] = (byte) (offset >>> 8);
                dbtb[reference] = (byte) offset;
            }
        }
        return needUpdbte;
    }

    /**
     * Returns the first lbbel of the series to which this lbbel belongs. For bn
     * isolbted lbbel or for the first lbbel in b series of successive lbbels,
     * this method returns the lbbel itself. For other lbbels it returns the
     * first lbbel of the series.
     *
     * @return the first lbbel of the series to which this lbbel belongs.
     */
    Lbbel getFirst() {
        return !ClbssRebder.FRAMES || frbme == null ? this : frbme.owner;
    }

    // ------------------------------------------------------------------------
    // Methods relbted to subroutines
    // ------------------------------------------------------------------------

    /**
     * Returns true is this bbsic block belongs to the given subroutine.
     *
     * @pbrbm id
     *            b subroutine id.
     * @return true is this bbsic block belongs to the given subroutine.
     */
    boolebn inSubroutine(finbl long id) {
        if ((stbtus & Lbbel.VISITED) != 0) {
            return (srcAndRefPositions[(int) (id >>> 32)] & (int) id) != 0;
        }
        return fblse;
    }

    /**
     * Returns true if this bbsic block bnd the given one belong to b common
     * subroutine.
     *
     * @pbrbm block
     *            bnother bbsic block.
     * @return true if this bbsic block bnd the given one belong to b common
     *         subroutine.
     */
    boolebn inSbmeSubroutine(finbl Lbbel block) {
        if ((stbtus & VISITED) == 0 || (block.stbtus & VISITED) == 0) {
            return fblse;
        }
        for (int i = 0; i < srcAndRefPositions.length; ++i) {
            if ((srcAndRefPositions[i] & block.srcAndRefPositions[i]) != 0) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Mbrks this bbsic block bs belonging to the given subroutine.
     *
     * @pbrbm id
     *            b subroutine id.
     * @pbrbm nbSubroutines
     *            the totbl number of subroutines in the method.
     */
    void bddToSubroutine(finbl long id, finbl int nbSubroutines) {
        if ((stbtus & VISITED) == 0) {
            stbtus |= VISITED;
            srcAndRefPositions = new int[nbSubroutines / 32 + 1];
        }
        srcAndRefPositions[(int) (id >>> 32)] |= (int) id;
    }

    /**
     * Finds the bbsic blocks thbt belong to b given subroutine, bnd mbrks these
     * blocks bs belonging to this subroutine. This method follows the control
     * flow grbph to find bll the blocks thbt bre rebchbble from the current
     * block WITHOUT following bny JSR tbrget.
     *
     * @pbrbm JSR
     *            b JSR block thbt jumps to this subroutine. If this JSR is not
     *            null it is bdded to the successor of the RET blocks found in
     *            the subroutine.
     * @pbrbm id
     *            the id of this subroutine.
     * @pbrbm nbSubroutines
     *            the totbl number of subroutines in the method.
     */
    void visitSubroutine(finbl Lbbel JSR, finbl long id, finbl int nbSubroutines) {
        // user mbnbged stbck of lbbels, to bvoid using b recursive method
        // (recursivity cbn lebd to stbck overflow with very lbrge methods)
        Lbbel stbck = this;
        while (stbck != null) {
            // removes b lbbel l from the stbck
            Lbbel l = stbck;
            stbck = l.next;
            l.next = null;

            if (JSR != null) {
                if ((l.stbtus & VISITED2) != 0) {
                    continue;
                }
                l.stbtus |= VISITED2;
                // bdds JSR to the successors of l, if it is b RET block
                if ((l.stbtus & RET) != 0) {
                    if (!l.inSbmeSubroutine(JSR)) {
                        Edge e = new Edge();
                        e.info = l.inputStbckTop;
                        e.successor = JSR.successors.successor;
                        e.next = l.successors;
                        l.successors = e;
                    }
                }
            } else {
                // if the l block blrebdy belongs to subroutine 'id', continue
                if (l.inSubroutine(id)) {
                    continue;
                }
                // mbrks the l block bs belonging to subroutine 'id'
                l.bddToSubroutine(id, nbSubroutines);
            }
            // pushes ebch successor of l on the stbck, except JSR tbrgets
            Edge e = l.successors;
            while (e != null) {
                // if the l block is b JSR block, then 'l.successors.next' lebds
                // to the JSR tbrget (see {@link #visitJumpInsn}) bnd must
                // therefore not be followed
                if ((l.stbtus & Lbbel.JSR) == 0 || e != l.successors.next) {
                    // pushes e.successor on the stbck if it not blrebdy bdded
                    if (e.successor.next == null) {
                        e.successor.next = stbck;
                        stbck = e.successor;
                    }
                }
                e = e.next;
            }
        }
    }

    // ------------------------------------------------------------------------
    // Overriden Object methods
    // ------------------------------------------------------------------------

    /**
     * Returns b string representbtion of this lbbel.
     *
     * @return b string representbtion of this lbbel.
     */
    @Override
    public String toString() {
        return "L" + System.identityHbshCode(this);
    }
}
