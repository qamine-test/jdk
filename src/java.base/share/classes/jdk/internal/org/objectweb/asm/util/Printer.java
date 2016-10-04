/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.util;

import jbvb.io.PrintWritfr;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;

/**
 * An bbstrbdt donvfrtfr from visit fvfnts to tfxt.
 *
 * @butior Erid Brunfton
 */
publid bbstrbdt dlbss Printfr {

    /**
     * Tif nbmfs of tif Jbvb Virtubl Mbdiinf opdodfs.
     */
    publid stbtid finbl String[] OPCODES;

    /**
     * Tif nbmfs of tif for <dodf>opfrbnd</dodf> pbrbmftfr vblufs of tif
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitIntInsn} mftiod wifn
     * <dodf>opdodf</dodf> is <dodf>NEWARRAY</dodf>.
     */
    publid stbtid finbl String[] TYPES;

    /**
     * Tif nbmfs of tif <dodf>tbg</dodf> fifld vblufs for
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf}.
     */
    publid stbtid finbl String[] HANDLE_TAG;

    stbtid {
        String s = "NOP,ACONST_NULL,ICONST_M1,ICONST_0,ICONST_1,ICONST_2,"
                + "ICONST_3,ICONST_4,ICONST_5,LCONST_0,LCONST_1,FCONST_0,"
                + "FCONST_1,FCONST_2,DCONST_0,DCONST_1,BIPUSH,SIPUSH,LDC,,,"
                + "ILOAD,LLOAD,FLOAD,DLOAD,ALOAD,,,,,,,,,,,,,,,,,,,,,IALOAD,"
                + "LALOAD,FALOAD,DALOAD,AALOAD,BALOAD,CALOAD,SALOAD,ISTORE,"
                + "LSTORE,FSTORE,DSTORE,ASTORE,,,,,,,,,,,,,,,,,,,,,IASTORE,"
                + "LASTORE,FASTORE,DASTORE,AASTORE,BASTORE,CASTORE,SASTORE,POP,"
                + "POP2,DUP,DUP_X1,DUP_X2,DUP2,DUP2_X1,DUP2_X2,SWAP,IADD,LADD,"
                + "FADD,DADD,ISUB,LSUB,FSUB,DSUB,IMUL,LMUL,FMUL,DMUL,IDIV,LDIV,"
                + "FDIV,DDIV,IREM,LREM,FREM,DREM,INEG,LNEG,FNEG,DNEG,ISHL,LSHL,"
                + "ISHR,LSHR,IUSHR,LUSHR,IAND,LAND,IOR,LOR,IXOR,LXOR,IINC,I2L,"
                + "I2F,I2D,L2I,L2F,L2D,F2I,F2L,F2D,D2I,D2L,D2F,I2B,I2C,I2S,LCMP,"
                + "FCMPL,FCMPG,DCMPL,DCMPG,IFEQ,IFNE,IFLT,IFGE,IFGT,IFLE,"
                + "IF_ICMPEQ,IF_ICMPNE,IF_ICMPLT,IF_ICMPGE,IF_ICMPGT,IF_ICMPLE,"
                + "IF_ACMPEQ,IF_ACMPNE,GOTO,JSR,RET,TABLESWITCH,LOOKUPSWITCH,"
                + "IRETURN,LRETURN,FRETURN,DRETURN,ARETURN,RETURN,GETSTATIC,"
                + "PUTSTATIC,GETFIELD,PUTFIELD,INVOKEVIRTUAL,INVOKESPECIAL,"
                + "INVOKESTATIC,INVOKEINTERFACE,INVOKEDYNAMIC,NEW,NEWARRAY,"
                + "ANEWARRAY,ARRAYLENGTH,ATHROW,CHECKCAST,INSTANCEOF,"
                + "MONITORENTER,MONITOREXIT,,MULTIANEWARRAY,IFNULL,IFNONNULL,";
        OPCODES = nfw String[200];
        int i = 0;
        int j = 0;
        int l;
        wiilf ((l = s.indfxOf(',', j)) > 0) {
            OPCODES[i++] = j + 1 == l ? null : s.substring(j, l);
            j = l + 1;
        }

        s = "T_BOOLEAN,T_CHAR,T_FLOAT,T_DOUBLE,T_BYTE,T_SHORT,T_INT,T_LONG,";
        TYPES = nfw String[12];
        j = 0;
        i = 4;
        wiilf ((l = s.indfxOf(',', j)) > 0) {
            TYPES[i++] = s.substring(j, l);
            j = l + 1;
        }

        s = "H_GETFIELD,H_GETSTATIC,H_PUTFIELD,H_PUTSTATIC,"
                + "H_INVOKEVIRTUAL,H_INVOKESTATIC,H_INVOKESPECIAL,"
                + "H_NEWINVOKESPECIAL,H_INVOKEINTERFACE,";
        HANDLE_TAG = nfw String[10];
        j = 0;
        i = 1;
        wiilf ((l = s.indfxOf(',', j)) > 0) {
            HANDLE_TAG[i++] = s.substring(j, l);
            j = l + 1;
        }
    }

    /**
     * Tif ASM API vfrsion implfmfntfd by tiis dlbss. Tif vbluf of tiis fifld
     * must bf onf of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    protfdtfd finbl int bpi;

    /**
     * A bufffr tibt dbn bf usfd to drfbtf strings.
     */
    protfdtfd finbl StringBufffr buf;

    /**
     * Tif tfxt to bf printfd. Sindf tif dodf of mftiods is not nfdfssbrily
     * visitfd in sfqufntibl ordfr, onf mftiod bftfr tif otifr, but dbn bf
     * intfrlbdfd (somf instrudtions from mftiod onf, tifn somf instrudtions
     * from mftiod two, tifn somf instrudtions from mftiod onf bgbin...), it is
     * not possiblf to print tif visitfd instrudtions dirfdtly to b sfqufntibl
     * strfbm. A dlbss is tifrfforf printfd in b two stfps prodfss: b string
     * trff is donstrudtfd during tif visit, bnd printfd to b sfqufntibl strfbm
     * bt tif fnd of tif visit. Tiis string trff is storfd in tiis fifld, bs b
     * string list tibt dbn dontbin otifr string lists, wiidi dbn tifmsflvfs
     * dontbin otifr string lists, bnd so on.
     */
    publid finbl List<Objfdt> tfxt;

    /**
     * Construdts b nfw {@link Printfr}.
     */
    protfdtfd Printfr(finbl int bpi) {
        tiis.bpi = bpi;
        tiis.buf = nfw StringBufffr();
        tiis.tfxt = nfw ArrbyList<Objfdt>();
    }

    /**
     * Clbss ifbdfr. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visit}.
     */
    publid bbstrbdt void visit(finbl int vfrsion, finbl int bddfss,
            finbl String nbmf, finbl String signbturf, finbl String supfrNbmf,
            finbl String[] intfrfbdfs);

    /**
     * Clbss sourdf. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitSourdf}.
     */
    publid bbstrbdt void visitSourdf(finbl String filf, finbl String dfbug);

    /**
     * Clbss outfr dlbss. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitOutfrClbss}.
     */
    publid bbstrbdt void visitOutfrClbss(finbl String ownfr, finbl String nbmf,
            finbl String dfsd);

    /**
     * Clbss bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitAnnotbtion}.
     */
    publid bbstrbdt Printfr visitClbssAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf);

    /**
     * Clbss typf bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitTypfAnnotbtion}.
     */
    publid Printfr visitClbssTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Clbss bttributf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitAttributf}.
     */
    publid bbstrbdt void visitClbssAttributf(finbl Attributf bttr);

    /**
     * Clbss innfr nbmf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitInnfrClbss}.
     */
    publid bbstrbdt void visitInnfrClbss(finbl String nbmf,
            finbl String outfrNbmf, finbl String innfrNbmf, finbl int bddfss);

    /**
     * Clbss fifld. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitFifld}.
     */
    publid bbstrbdt Printfr visitFifld(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Objfdt vbluf);

    /**
     * Clbss mftiod. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitMftiod}.
     */
    publid bbstrbdt Printfr visitMftiod(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl String[] fxdfptions);

    /**
     * Clbss fnd. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor#visitEnd}.
     */
    publid bbstrbdt void visitClbssEnd();

    // ------------------------------------------------------------------------
    // Annotbtions
    // ------------------------------------------------------------------------

    /**
     * Annotbtion vbluf. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor#visit}.
     */
    publid bbstrbdt void visit(finbl String nbmf, finbl Objfdt vbluf);

    /**
     * Annotbtion fnum vbluf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor#visitEnum}.
     */
    publid bbstrbdt void visitEnum(finbl String nbmf, finbl String dfsd,
            finbl String vbluf);

    /**
     * Nfstfd bnnotbtion vbluf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor#visitAnnotbtion}.
     */
    publid bbstrbdt Printfr visitAnnotbtion(finbl String nbmf, finbl String dfsd);

    /**
     * Annotbtion brrby vbluf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor#visitArrby}.
     */
    publid bbstrbdt Printfr visitArrby(finbl String nbmf);

    /**
     * Annotbtion fnd. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor#visitEnd}.
     */
    publid bbstrbdt void visitAnnotbtionEnd();

    // ------------------------------------------------------------------------
    // Fiflds
    // ------------------------------------------------------------------------

    /**
     * Fifld bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor#visitAnnotbtion}.
     */
    publid bbstrbdt Printfr visitFifldAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf);

    /**
     * Fifld typf bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor#visitTypfAnnotbtion}.
     */
    publid Printfr visitFifldTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Fifld bttributf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor#visitAttributf}.
     */
    publid bbstrbdt void visitFifldAttributf(finbl Attributf bttr);

    /**
     * Fifld fnd. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor#visitEnd}.
     */
    publid bbstrbdt void visitFifldEnd();

    // ------------------------------------------------------------------------
    // Mftiods
    // ------------------------------------------------------------------------

    /**
     * Mftiod pbrbmftfr. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitPbrbmftfr(String, int)}.
     */
    publid void visitPbrbmftfr(String nbmf, int bddfss) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod dffbult bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitAnnotbtionDffbult}.
     */
    publid bbstrbdt Printfr visitAnnotbtionDffbult();

    /**
     * Mftiod bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitAnnotbtion}.
     */
    publid bbstrbdt Printfr visitMftiodAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf);

    /**
     * Mftiod typf bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitTypfAnnotbtion}.
     */
    publid Printfr visitMftiodTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod pbrbmftfr bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitPbrbmftfrAnnotbtion}.
     */
    publid bbstrbdt Printfr visitPbrbmftfrAnnotbtion(finbl int pbrbmftfr,
            finbl String dfsd, finbl boolfbn visiblf);

    /**
     * Mftiod bttributf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitAttributf}.
     */
    publid bbstrbdt void visitMftiodAttributf(finbl Attributf bttr);

    /**
     * Mftiod stbrt. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitCodf}.
     */
    publid bbstrbdt void visitCodf();

    /**
     * Mftiod stbdk frbmf. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitFrbmf}.
     */
    publid bbstrbdt void visitFrbmf(finbl int typf, finbl int nLodbl,
            finbl Objfdt[] lodbl, finbl int nStbdk, finbl Objfdt[] stbdk);

    /**
     * Mftiod instrudtion. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitInsn}
     * .
     */
    publid bbstrbdt void visitInsn(finbl int opdodf);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitIntInsn}.
     */
    publid bbstrbdt void visitIntInsn(finbl int opdodf, finbl int opfrbnd);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitVbrInsn}.
     */
    publid bbstrbdt void visitVbrInsn(finbl int opdodf, finbl int vbr);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitTypfInsn}.
     */
    publid bbstrbdt void visitTypfInsn(finbl int opdodf, finbl String typf);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitFifldInsn}.
     */
    publid bbstrbdt void visitFifldInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitMftiodInsn}.
     */
    @Dfprfdbtfd
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            boolfbn itf = opdodf == Opdodfs.INVOKEINTERFACE;
            visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitMftiodInsn}.
     */
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            if (itf != (opdodf == Opdodfs.INVOKEINTERFACE)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "INVOKESPECIAL/STATIC on intfrfbdfs rfquirf ASM 5");
            }
            visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitInvokfDynbmidInsn}.
     */
    publid bbstrbdt void visitInvokfDynbmidInsn(String nbmf, String dfsd,
            Hbndlf bsm, Objfdt... bsmArgs);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitJumpInsn}.
     */
    publid bbstrbdt void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbbfl);

    /**
     * Mftiod lbbfl. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitLbbfl}.
     */
    publid bbstrbdt void visitLbbfl(finbl Lbbfl lbbfl);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitLddInsn}.
     */
    publid bbstrbdt void visitLddInsn(finbl Objfdt dst);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitIindInsn}.
     */
    publid bbstrbdt void visitIindInsn(finbl int vbr, finbl int indrfmfnt);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitTbblfSwitdiInsn}.
     */
    publid bbstrbdt void visitTbblfSwitdiInsn(finbl int min, finbl int mbx,
            finbl Lbbfl dflt, finbl Lbbfl... lbbfls);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitLookupSwitdiInsn}.
     */
    publid bbstrbdt void visitLookupSwitdiInsn(finbl Lbbfl dflt,
            finbl int[] kfys, finbl Lbbfl[] lbbfls);

    /**
     * Mftiod instrudtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitMultiANfwArrbyInsn}.
     */
    publid bbstrbdt void visitMultiANfwArrbyInsn(finbl String dfsd,
            finbl int dims);

    /**
     * Instrudtion typf bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitInsnAnnotbtion}.
     */
    publid Printfr visitInsnAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod fxdfption ibndlfr. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitTryCbtdiBlodk}.
     */
    publid bbstrbdt void visitTryCbtdiBlodk(finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl Lbbfl ibndlfr, finbl String typf);

    /**
     * Try dbtdi blodk typf bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitTryCbtdiAnnotbtion}.
     */
    publid Printfr visitTryCbtdiAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod dfbug info. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitLodblVbribblf}.
     */
    publid bbstrbdt void visitLodblVbribblf(finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Lbbfl stbrt,
            finbl Lbbfl fnd, finbl int indfx);

    /**
     * Lodbl vbribblf typf bnnotbtion. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitTryCbtdiAnnotbtion}.
     */
    publid Printfr visitLodblVbribblfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl Lbbfl[] stbrt, finbl Lbbfl[] fnd,
            finbl int[] indfx, finbl String dfsd, finbl boolfbn visiblf) {
        tirow nfw RuntimfExdfption("Must bf ovfrridfn");
    }

    /**
     * Mftiod dfbug info. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitLinfNumbfr}.
     */
    publid bbstrbdt void visitLinfNumbfr(finbl int linf, finbl Lbbfl stbrt);

    /**
     * Mftiod mbx stbdk bnd mbx lodbls. Sff
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitMbxs}.
     */
    publid bbstrbdt void visitMbxs(finbl int mbxStbdk, finbl int mbxLodbls);

    /**
     * Mftiod fnd. Sff {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor#visitEnd}.
     */
    publid bbstrbdt void visitMftiodEnd();

    /**
     * Rfturns tif tfxt donstrudtfd by tiis visitor.
     *
     * @rfturn tif tfxt donstrudtfd by tiis visitor.
     */
    publid List<Objfdt> gftTfxt() {
        rfturn tfxt;
    }

    /**
     * Prints tif tfxt donstrudtfd by tiis visitor.
     *
     * @pbrbm pw
     *            tif print writfr to bf usfd.
     */
    publid void print(finbl PrintWritfr pw) {
        printList(pw, tfxt);
    }

    /**
     * Appfnds b quotfd string to b givfn bufffr.
     *
     * @pbrbm buf
     *            tif bufffr wifrf tif string must bf bddfd.
     * @pbrbm s
     *            tif string to bf bddfd.
     */
    publid stbtid void bppfndString(finbl StringBufffr buf, finbl String s) {
        buf.bppfnd('\"');
        for (int i = 0; i < s.lfngti(); ++i) {
            dibr d = s.dibrAt(i);
            if (d == '\n') {
                buf.bppfnd("\\n");
            } flsf if (d == '\r') {
                buf.bppfnd("\\r");
            } flsf if (d == '\\') {
                buf.bppfnd("\\\\");
            } flsf if (d == '"') {
                buf.bppfnd("\\\"");
            } flsf if (d < 0x20 || d > 0x7f) {
                buf.bppfnd("\\u");
                if (d < 0x10) {
                    buf.bppfnd("000");
                } flsf if (d < 0x100) {
                    buf.bppfnd("00");
                } flsf if (d < 0x1000) {
                    buf.bppfnd('0');
                }
                buf.bppfnd(Intfgfr.toString(d, 16));
            } flsf {
                buf.bppfnd(d);
            }
        }
        buf.bppfnd('\"');
    }

    /**
     * Prints tif givfn string trff.
     *
     * @pbrbm pw
     *            tif writfr to bf usfd to print tif trff.
     * @pbrbm l
     *            b string trff, i.f., b string list tibt dbn dontbin otifr
     *            string lists, bnd so on rfdursivfly.
     */
    stbtid void printList(finbl PrintWritfr pw, finbl List<?> l) {
        for (int i = 0; i < l.sizf(); ++i) {
            Objfdt o = l.gft(i);
            if (o instbndfof List) {
                printList(pw, (List<?>) o);
            } flsf {
                pw.print(o.toString());
            }
        }
    }
}
