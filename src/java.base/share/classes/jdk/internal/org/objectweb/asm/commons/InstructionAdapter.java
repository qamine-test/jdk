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

pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.dommons;

import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;

/**
 * A {@link MftiodVisitor} providing b morf dftbilfd API to gfnfrbtf bnd
 * trbnsform instrudtions.
 *
 * @butior Erid Brunfton
 */
publid dlbss InstrudtionAdbptfr fxtfnds MftiodVisitor {

    publid finbl stbtid Typf OBJECT_TYPE = Typf.gftTypf("Ljbvb/lbng/Objfdt;");

    /**
     * Crfbtfs b nfw {@link InstrudtionAdbptfr}. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #InstrudtionAdbptfr(int, MftiodVisitor)} vfrsion.
     *
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr dflfgbtfs dblls.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid InstrudtionAdbptfr(finbl MftiodVisitor mv) {
        tiis(Opdodfs.ASM5, mv);
        if (gftClbss() != InstrudtionAdbptfr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Crfbtfs b nfw {@link InstrudtionAdbptfr}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr dflfgbtfs dblls.
     */
    protfdtfd InstrudtionAdbptfr(finbl int bpi, finbl MftiodVisitor mv) {
        supfr(bpi, mv);
    }

    @Ovfrridf
    publid void visitInsn(finbl int opdodf) {
        switdi (opdodf) {
        dbsf Opdodfs.NOP:
            nop();
            brfbk;
        dbsf Opdodfs.ACONST_NULL:
            bdonst(null);
            brfbk;
        dbsf Opdodfs.ICONST_M1:
        dbsf Opdodfs.ICONST_0:
        dbsf Opdodfs.ICONST_1:
        dbsf Opdodfs.ICONST_2:
        dbsf Opdodfs.ICONST_3:
        dbsf Opdodfs.ICONST_4:
        dbsf Opdodfs.ICONST_5:
            idonst(opdodf - Opdodfs.ICONST_0);
            brfbk;
        dbsf Opdodfs.LCONST_0:
        dbsf Opdodfs.LCONST_1:
            ldonst(opdodf - Opdodfs.LCONST_0);
            brfbk;
        dbsf Opdodfs.FCONST_0:
        dbsf Opdodfs.FCONST_1:
        dbsf Opdodfs.FCONST_2:
            fdonst(opdodf - Opdodfs.FCONST_0);
            brfbk;
        dbsf Opdodfs.DCONST_0:
        dbsf Opdodfs.DCONST_1:
            ddonst(opdodf - Opdodfs.DCONST_0);
            brfbk;
        dbsf Opdodfs.IALOAD:
            blobd(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LALOAD:
            blobd(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FALOAD:
            blobd(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DALOAD:
            blobd(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.AALOAD:
            blobd(OBJECT_TYPE);
            brfbk;
        dbsf Opdodfs.BALOAD:
            blobd(Typf.BYTE_TYPE);
            brfbk;
        dbsf Opdodfs.CALOAD:
            blobd(Typf.CHAR_TYPE);
            brfbk;
        dbsf Opdodfs.SALOAD:
            blobd(Typf.SHORT_TYPE);
            brfbk;
        dbsf Opdodfs.IASTORE:
            bstorf(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LASTORE:
            bstorf(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FASTORE:
            bstorf(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DASTORE:
            bstorf(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.AASTORE:
            bstorf(OBJECT_TYPE);
            brfbk;
        dbsf Opdodfs.BASTORE:
            bstorf(Typf.BYTE_TYPE);
            brfbk;
        dbsf Opdodfs.CASTORE:
            bstorf(Typf.CHAR_TYPE);
            brfbk;
        dbsf Opdodfs.SASTORE:
            bstorf(Typf.SHORT_TYPE);
            brfbk;
        dbsf Opdodfs.POP:
            pop();
            brfbk;
        dbsf Opdodfs.POP2:
            pop2();
            brfbk;
        dbsf Opdodfs.DUP:
            dup();
            brfbk;
        dbsf Opdodfs.DUP_X1:
            dupX1();
            brfbk;
        dbsf Opdodfs.DUP_X2:
            dupX2();
            brfbk;
        dbsf Opdodfs.DUP2:
            dup2();
            brfbk;
        dbsf Opdodfs.DUP2_X1:
            dup2X1();
            brfbk;
        dbsf Opdodfs.DUP2_X2:
            dup2X2();
            brfbk;
        dbsf Opdodfs.SWAP:
            swbp();
            brfbk;
        dbsf Opdodfs.IADD:
            bdd(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LADD:
            bdd(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FADD:
            bdd(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DADD:
            bdd(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.ISUB:
            sub(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LSUB:
            sub(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FSUB:
            sub(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DSUB:
            sub(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.IMUL:
            mul(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LMUL:
            mul(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FMUL:
            mul(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DMUL:
            mul(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.IDIV:
            div(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LDIV:
            div(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FDIV:
            div(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DDIV:
            div(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.IREM:
            rfm(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LREM:
            rfm(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FREM:
            rfm(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DREM:
            rfm(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.INEG:
            nfg(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LNEG:
            nfg(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FNEG:
            nfg(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DNEG:
            nfg(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.ISHL:
            sil(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LSHL:
            sil(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.ISHR:
            sir(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LSHR:
            sir(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.IUSHR:
            usir(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LUSHR:
            usir(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.IAND:
            bnd(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LAND:
            bnd(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.IOR:
            or(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LOR:
            or(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.IXOR:
            xor(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LXOR:
            xor(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.I2L:
            dbst(Typf.INT_TYPE, Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.I2F:
            dbst(Typf.INT_TYPE, Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.I2D:
            dbst(Typf.INT_TYPE, Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.L2I:
            dbst(Typf.LONG_TYPE, Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.L2F:
            dbst(Typf.LONG_TYPE, Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.L2D:
            dbst(Typf.LONG_TYPE, Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.F2I:
            dbst(Typf.FLOAT_TYPE, Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.F2L:
            dbst(Typf.FLOAT_TYPE, Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.F2D:
            dbst(Typf.FLOAT_TYPE, Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.D2I:
            dbst(Typf.DOUBLE_TYPE, Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.D2L:
            dbst(Typf.DOUBLE_TYPE, Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.D2F:
            dbst(Typf.DOUBLE_TYPE, Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.I2B:
            dbst(Typf.INT_TYPE, Typf.BYTE_TYPE);
            brfbk;
        dbsf Opdodfs.I2C:
            dbst(Typf.INT_TYPE, Typf.CHAR_TYPE);
            brfbk;
        dbsf Opdodfs.I2S:
            dbst(Typf.INT_TYPE, Typf.SHORT_TYPE);
            brfbk;
        dbsf Opdodfs.LCMP:
            ldmp();
            brfbk;
        dbsf Opdodfs.FCMPL:
            dmpl(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.FCMPG:
            dmpg(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DCMPL:
            dmpl(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.DCMPG:
            dmpg(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.IRETURN:
            brfturn(Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LRETURN:
            brfturn(Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FRETURN:
            brfturn(Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DRETURN:
            brfturn(Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.ARETURN:
            brfturn(OBJECT_TYPE);
            brfbk;
        dbsf Opdodfs.RETURN:
            brfturn(Typf.VOID_TYPE);
            brfbk;
        dbsf Opdodfs.ARRAYLENGTH:
            brrbylfngti();
            brfbk;
        dbsf Opdodfs.ATHROW:
            btirow();
            brfbk;
        dbsf Opdodfs.MONITORENTER:
            monitorfntfr();
            brfbk;
        dbsf Opdodfs.MONITOREXIT:
            monitorfxit();
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitIntInsn(finbl int opdodf, finbl int opfrbnd) {
        switdi (opdodf) {
        dbsf Opdodfs.BIPUSH:
            idonst(opfrbnd);
            brfbk;
        dbsf Opdodfs.SIPUSH:
            idonst(opfrbnd);
            brfbk;
        dbsf Opdodfs.NEWARRAY:
            switdi (opfrbnd) {
            dbsf Opdodfs.T_BOOLEAN:
                nfwbrrby(Typf.BOOLEAN_TYPE);
                brfbk;
            dbsf Opdodfs.T_CHAR:
                nfwbrrby(Typf.CHAR_TYPE);
                brfbk;
            dbsf Opdodfs.T_BYTE:
                nfwbrrby(Typf.BYTE_TYPE);
                brfbk;
            dbsf Opdodfs.T_SHORT:
                nfwbrrby(Typf.SHORT_TYPE);
                brfbk;
            dbsf Opdodfs.T_INT:
                nfwbrrby(Typf.INT_TYPE);
                brfbk;
            dbsf Opdodfs.T_FLOAT:
                nfwbrrby(Typf.FLOAT_TYPE);
                brfbk;
            dbsf Opdodfs.T_LONG:
                nfwbrrby(Typf.LONG_TYPE);
                brfbk;
            dbsf Opdodfs.T_DOUBLE:
                nfwbrrby(Typf.DOUBLE_TYPE);
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption();
            }
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitVbrInsn(finbl int opdodf, finbl int vbr) {
        switdi (opdodf) {
        dbsf Opdodfs.ILOAD:
            lobd(vbr, Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LLOAD:
            lobd(vbr, Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FLOAD:
            lobd(vbr, Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DLOAD:
            lobd(vbr, Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.ALOAD:
            lobd(vbr, OBJECT_TYPE);
            brfbk;
        dbsf Opdodfs.ISTORE:
            storf(vbr, Typf.INT_TYPE);
            brfbk;
        dbsf Opdodfs.LSTORE:
            storf(vbr, Typf.LONG_TYPE);
            brfbk;
        dbsf Opdodfs.FSTORE:
            storf(vbr, Typf.FLOAT_TYPE);
            brfbk;
        dbsf Opdodfs.DSTORE:
            storf(vbr, Typf.DOUBLE_TYPE);
            brfbk;
        dbsf Opdodfs.ASTORE:
            storf(vbr, OBJECT_TYPE);
            brfbk;
        dbsf Opdodfs.RET:
            rft(vbr);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitTypfInsn(finbl int opdodf, finbl String typf) {
        Typf t = Typf.gftObjfdtTypf(typf);
        switdi (opdodf) {
        dbsf Opdodfs.NEW:
            bnfw(t);
            brfbk;
        dbsf Opdodfs.ANEWARRAY:
            nfwbrrby(t);
            brfbk;
        dbsf Opdodfs.CHECKCAST:
            difdkdbst(t);
            brfbk;
        dbsf Opdodfs.INSTANCEOF:
            instbndfOf(t);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitFifldInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        switdi (opdodf) {
        dbsf Opdodfs.GETSTATIC:
            gftstbtid(ownfr, nbmf, dfsd);
            brfbk;
        dbsf Opdodfs.PUTSTATIC:
            putstbtid(ownfr, nbmf, dfsd);
            brfbk;
        dbsf Opdodfs.GETFIELD:
            gftfifld(ownfr, nbmf, dfsd);
            brfbk;
        dbsf Opdodfs.PUTFIELD:
            putfifld(ownfr, nbmf, dfsd);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Dfprfdbtfd
    @Ovfrridf
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd,
                opdodf == Opdodfs.INVOKEINTERFACE);
    }

    @Ovfrridf
    publid void visitMftiodInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
    }

    privbtf void doVisitMftiodInsn(int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        switdi (opdodf) {
        dbsf Opdodfs.INVOKESPECIAL:
            invokfspfdibl(ownfr, nbmf, dfsd, itf);
            brfbk;
        dbsf Opdodfs.INVOKEVIRTUAL:
            invokfvirtubl(ownfr, nbmf, dfsd, itf);
            brfbk;
        dbsf Opdodfs.INVOKESTATIC:
            invokfstbtid(ownfr, nbmf, dfsd, itf);
            brfbk;
        dbsf Opdodfs.INVOKEINTERFACE:
            invokfintfrfbdf(ownfr, nbmf, dfsd);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        invokfdynbmid(nbmf, dfsd, bsm, bsmArgs);
    }

    @Ovfrridf
    publid void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbbfl) {
        switdi (opdodf) {
        dbsf Opdodfs.IFEQ:
            iffq(lbbfl);
            brfbk;
        dbsf Opdodfs.IFNE:
            ifnf(lbbfl);
            brfbk;
        dbsf Opdodfs.IFLT:
            iflt(lbbfl);
            brfbk;
        dbsf Opdodfs.IFGE:
            ifgf(lbbfl);
            brfbk;
        dbsf Opdodfs.IFGT:
            ifgt(lbbfl);
            brfbk;
        dbsf Opdodfs.IFLE:
            iflf(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ICMPEQ:
            ifidmpfq(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ICMPNE:
            ifidmpnf(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ICMPLT:
            ifidmplt(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ICMPGE:
            ifidmpgf(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ICMPGT:
            ifidmpgt(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ICMPLE:
            ifidmplf(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ACMPEQ:
            ifbdmpfq(lbbfl);
            brfbk;
        dbsf Opdodfs.IF_ACMPNE:
            ifbdmpnf(lbbfl);
            brfbk;
        dbsf Opdodfs.GOTO:
            goTo(lbbfl);
            brfbk;
        dbsf Opdodfs.JSR:
            jsr(lbbfl);
            brfbk;
        dbsf Opdodfs.IFNULL:
            ifnull(lbbfl);
            brfbk;
        dbsf Opdodfs.IFNONNULL:
            ifnonnull(lbbfl);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitLbbfl(finbl Lbbfl lbbfl) {
        mbrk(lbbfl);
    }

    @Ovfrridf
    publid void visitLddInsn(finbl Objfdt dst) {
        if (dst instbndfof Intfgfr) {
            int vbl = ((Intfgfr) dst).intVbluf();
            idonst(vbl);
        } flsf if (dst instbndfof Bytf) {
            int vbl = ((Bytf) dst).intVbluf();
            idonst(vbl);
        } flsf if (dst instbndfof Cibrbdtfr) {
            int vbl = ((Cibrbdtfr) dst).dibrVbluf();
            idonst(vbl);
        } flsf if (dst instbndfof Siort) {
            int vbl = ((Siort) dst).intVbluf();
            idonst(vbl);
        } flsf if (dst instbndfof Boolfbn) {
            int vbl = ((Boolfbn) dst).boolfbnVbluf() ? 1 : 0;
            idonst(vbl);
        } flsf if (dst instbndfof Flobt) {
            flobt vbl = ((Flobt) dst).flobtVbluf();
            fdonst(vbl);
        } flsf if (dst instbndfof Long) {
            long vbl = ((Long) dst).longVbluf();
            ldonst(vbl);
        } flsf if (dst instbndfof Doublf) {
            doublf vbl = ((Doublf) dst).doublfVbluf();
            ddonst(vbl);
        } flsf if (dst instbndfof String) {
            bdonst(dst);
        } flsf if (dst instbndfof Typf) {
            tdonst((Typf) dst);
        } flsf if (dst instbndfof Hbndlf) {
            idonst((Hbndlf) dst);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    @Ovfrridf
    publid void visitIindInsn(finbl int vbr, finbl int indrfmfnt) {
        iind(vbr, indrfmfnt);
    }

    @Ovfrridf
    publid void visitTbblfSwitdiInsn(finbl int min, finbl int mbx,
            finbl Lbbfl dflt, finbl Lbbfl... lbbfls) {
        tbblfswitdi(min, mbx, dflt, lbbfls);
    }

    @Ovfrridf
    publid void visitLookupSwitdiInsn(finbl Lbbfl dflt, finbl int[] kfys,
            finbl Lbbfl[] lbbfls) {
        lookupswitdi(dflt, kfys, lbbfls);
    }

    @Ovfrridf
    publid void visitMultiANfwArrbyInsn(finbl String dfsd, finbl int dims) {
        multibnfwbrrby(dfsd, dims);
    }

    // -----------------------------------------------------------------------

    publid void nop() {
        mv.visitInsn(Opdodfs.NOP);
    }

    publid void bdonst(finbl Objfdt dst) {
        if (dst == null) {
            mv.visitInsn(Opdodfs.ACONST_NULL);
        } flsf {
            mv.visitLddInsn(dst);
        }
    }

    publid void idonst(finbl int dst) {
        if (dst >= -1 && dst <= 5) {
            mv.visitInsn(Opdodfs.ICONST_0 + dst);
        } flsf if (dst >= Bytf.MIN_VALUE && dst <= Bytf.MAX_VALUE) {
            mv.visitIntInsn(Opdodfs.BIPUSH, dst);
        } flsf if (dst >= Siort.MIN_VALUE && dst <= Siort.MAX_VALUE) {
            mv.visitIntInsn(Opdodfs.SIPUSH, dst);
        } flsf {
            mv.visitLddInsn(nfw Intfgfr(dst));
        }
    }

    publid void ldonst(finbl long dst) {
        if (dst == 0L || dst == 1L) {
            mv.visitInsn(Opdodfs.LCONST_0 + (int) dst);
        } flsf {
            mv.visitLddInsn(nfw Long(dst));
        }
    }

    publid void fdonst(finbl flobt dst) {
        int bits = Flobt.flobtToIntBits(dst);
        if (bits == 0L || bits == 0x3f800000 || bits == 0x40000000) { // 0..2
            mv.visitInsn(Opdodfs.FCONST_0 + (int) dst);
        } flsf {
            mv.visitLddInsn(nfw Flobt(dst));
        }
    }

    publid void ddonst(finbl doublf dst) {
        long bits = Doublf.doublfToLongBits(dst);
        if (bits == 0L || bits == 0x3ff0000000000000L) { // +0.0d bnd 1.0d
            mv.visitInsn(Opdodfs.DCONST_0 + (int) dst);
        } flsf {
            mv.visitLddInsn(nfw Doublf(dst));
        }
    }

    publid void tdonst(finbl Typf typf) {
        mv.visitLddInsn(typf);
    }

    publid void idonst(finbl Hbndlf ibndlf) {
        mv.visitLddInsn(ibndlf);
    }

    publid void lobd(finbl int vbr, finbl Typf typf) {
        mv.visitVbrInsn(typf.gftOpdodf(Opdodfs.ILOAD), vbr);
    }

    publid void blobd(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IALOAD));
    }

    publid void storf(finbl int vbr, finbl Typf typf) {
        mv.visitVbrInsn(typf.gftOpdodf(Opdodfs.ISTORE), vbr);
    }

    publid void bstorf(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IASTORE));
    }

    publid void pop() {
        mv.visitInsn(Opdodfs.POP);
    }

    publid void pop2() {
        mv.visitInsn(Opdodfs.POP2);
    }

    publid void dup() {
        mv.visitInsn(Opdodfs.DUP);
    }

    publid void dup2() {
        mv.visitInsn(Opdodfs.DUP2);
    }

    publid void dupX1() {
        mv.visitInsn(Opdodfs.DUP_X1);
    }

    publid void dupX2() {
        mv.visitInsn(Opdodfs.DUP_X2);
    }

    publid void dup2X1() {
        mv.visitInsn(Opdodfs.DUP2_X1);
    }

    publid void dup2X2() {
        mv.visitInsn(Opdodfs.DUP2_X2);
    }

    publid void swbp() {
        mv.visitInsn(Opdodfs.SWAP);
    }

    publid void bdd(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IADD));
    }

    publid void sub(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.ISUB));
    }

    publid void mul(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IMUL));
    }

    publid void div(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IDIV));
    }

    publid void rfm(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IREM));
    }

    publid void nfg(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.INEG));
    }

    publid void sil(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.ISHL));
    }

    publid void sir(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.ISHR));
    }

    publid void usir(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IUSHR));
    }

    publid void bnd(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IAND));
    }

    publid void or(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IOR));
    }

    publid void xor(finbl Typf typf) {
        mv.visitInsn(typf.gftOpdodf(Opdodfs.IXOR));
    }

    publid void iind(finbl int vbr, finbl int indrfmfnt) {
        mv.visitIindInsn(vbr, indrfmfnt);
    }

    publid void dbst(finbl Typf from, finbl Typf to) {
        if (from != to) {
            if (from == Typf.DOUBLE_TYPE) {
                if (to == Typf.FLOAT_TYPE) {
                    mv.visitInsn(Opdodfs.D2F);
                } flsf if (to == Typf.LONG_TYPE) {
                    mv.visitInsn(Opdodfs.D2L);
                } flsf {
                    mv.visitInsn(Opdodfs.D2I);
                    dbst(Typf.INT_TYPE, to);
                }
            } flsf if (from == Typf.FLOAT_TYPE) {
                if (to == Typf.DOUBLE_TYPE) {
                    mv.visitInsn(Opdodfs.F2D);
                } flsf if (to == Typf.LONG_TYPE) {
                    mv.visitInsn(Opdodfs.F2L);
                } flsf {
                    mv.visitInsn(Opdodfs.F2I);
                    dbst(Typf.INT_TYPE, to);
                }
            } flsf if (from == Typf.LONG_TYPE) {
                if (to == Typf.DOUBLE_TYPE) {
                    mv.visitInsn(Opdodfs.L2D);
                } flsf if (to == Typf.FLOAT_TYPE) {
                    mv.visitInsn(Opdodfs.L2F);
                } flsf {
                    mv.visitInsn(Opdodfs.L2I);
                    dbst(Typf.INT_TYPE, to);
                }
            } flsf {
                if (to == Typf.BYTE_TYPE) {
                    mv.visitInsn(Opdodfs.I2B);
                } flsf if (to == Typf.CHAR_TYPE) {
                    mv.visitInsn(Opdodfs.I2C);
                } flsf if (to == Typf.DOUBLE_TYPE) {
                    mv.visitInsn(Opdodfs.I2D);
                } flsf if (to == Typf.FLOAT_TYPE) {
                    mv.visitInsn(Opdodfs.I2F);
                } flsf if (to == Typf.LONG_TYPE) {
                    mv.visitInsn(Opdodfs.I2L);
                } flsf if (to == Typf.SHORT_TYPE) {
                    mv.visitInsn(Opdodfs.I2S);
                }
            }
        }
    }

    publid void ldmp() {
        mv.visitInsn(Opdodfs.LCMP);
    }

    publid void dmpl(finbl Typf typf) {
        mv.visitInsn(typf == Typf.FLOAT_TYPE ? Opdodfs.FCMPL : Opdodfs.DCMPL);
    }

    publid void dmpg(finbl Typf typf) {
        mv.visitInsn(typf == Typf.FLOAT_TYPE ? Opdodfs.FCMPG : Opdodfs.DCMPG);
    }

    publid void iffq(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFEQ, lbbfl);
    }

    publid void ifnf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFNE, lbbfl);
    }

    publid void iflt(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFLT, lbbfl);
    }

    publid void ifgf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFGE, lbbfl);
    }

    publid void ifgt(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFGT, lbbfl);
    }

    publid void iflf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFLE, lbbfl);
    }

    publid void ifidmpfq(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ICMPEQ, lbbfl);
    }

    publid void ifidmpnf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ICMPNE, lbbfl);
    }

    publid void ifidmplt(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ICMPLT, lbbfl);
    }

    publid void ifidmpgf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ICMPGE, lbbfl);
    }

    publid void ifidmpgt(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ICMPGT, lbbfl);
    }

    publid void ifidmplf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ICMPLE, lbbfl);
    }

    publid void ifbdmpfq(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ACMPEQ, lbbfl);
    }

    publid void ifbdmpnf(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IF_ACMPNE, lbbfl);
    }

    publid void goTo(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.GOTO, lbbfl);
    }

    publid void jsr(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.JSR, lbbfl);
    }

    publid void rft(finbl int vbr) {
        mv.visitVbrInsn(Opdodfs.RET, vbr);
    }

    publid void tbblfswitdi(finbl int min, finbl int mbx, finbl Lbbfl dflt,
            finbl Lbbfl... lbbfls) {
        mv.visitTbblfSwitdiInsn(min, mbx, dflt, lbbfls);
    }

    publid void lookupswitdi(finbl Lbbfl dflt, finbl int[] kfys,
            finbl Lbbfl[] lbbfls) {
        mv.visitLookupSwitdiInsn(dflt, kfys, lbbfls);
    }

    publid void brfturn(finbl Typf t) {
        mv.visitInsn(t.gftOpdodf(Opdodfs.IRETURN));
    }

    publid void gftstbtid(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        mv.visitFifldInsn(Opdodfs.GETSTATIC, ownfr, nbmf, dfsd);
    }

    publid void putstbtid(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        mv.visitFifldInsn(Opdodfs.PUTSTATIC, ownfr, nbmf, dfsd);
    }

    publid void gftfifld(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        mv.visitFifldInsn(Opdodfs.GETFIELD, ownfr, nbmf, dfsd);
    }

    publid void putfifld(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        mv.visitFifldInsn(Opdodfs.PUTFIELD, ownfr, nbmf, dfsd);
    }

    @Dfprfdbtfd
    publid void invokfvirtubl(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            invokfvirtubl(ownfr, nbmf, dfsd, fblsf);
            rfturn;
        }
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, ownfr, nbmf, dfsd);
    }

    publid void invokfvirtubl(finbl String ownfr, finbl String nbmf,
            finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            if (itf) {
                tirow nfw IllfgblArgumfntExdfption(
                        "INVOKEVIRTUAL on intfrfbdfs rfquirf ASM 5");
            }
            invokfvirtubl(ownfr, nbmf, dfsd);
            rfturn;
        }
        mv.visitMftiodInsn(Opdodfs.INVOKEVIRTUAL, ownfr, nbmf, dfsd, itf);
    }

    @Dfprfdbtfd
    publid void invokfspfdibl(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            invokfspfdibl(ownfr, nbmf, dfsd, fblsf);
            rfturn;
        }
        mv.visitMftiodInsn(Opdodfs.INVOKESPECIAL, ownfr, nbmf, dfsd, fblsf);
    }

    publid void invokfspfdibl(finbl String ownfr, finbl String nbmf,
            finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            if (itf) {
                tirow nfw IllfgblArgumfntExdfption(
                        "INVOKESPECIAL on intfrfbdfs rfquirf ASM 5");
            }
            invokfspfdibl(ownfr, nbmf, dfsd);
            rfturn;
        }
        mv.visitMftiodInsn(Opdodfs.INVOKESPECIAL, ownfr, nbmf, dfsd, itf);
    }

    @Dfprfdbtfd
    publid void invokfstbtid(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            invokfstbtid(ownfr, nbmf, dfsd, fblsf);
            rfturn;
        }
        mv.visitMftiodInsn(Opdodfs.INVOKESTATIC, ownfr, nbmf, dfsd, fblsf);
    }

    publid void invokfstbtid(finbl String ownfr, finbl String nbmf,
            finbl String dfsd, finbl boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            if (itf) {
                tirow nfw IllfgblArgumfntExdfption(
                        "INVOKESTATIC on intfrfbdfs rfquirf ASM 5");
            }
            invokfstbtid(ownfr, nbmf, dfsd);
            rfturn;
        }
        mv.visitMftiodInsn(Opdodfs.INVOKESTATIC, ownfr, nbmf, dfsd, itf);
    }

    publid void invokfintfrfbdf(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        mv.visitMftiodInsn(Opdodfs.INVOKEINTERFACE, ownfr, nbmf, dfsd, truf);
    }

    publid void invokfdynbmid(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt[] bsmArgs) {
        mv.visitInvokfDynbmidInsn(nbmf, dfsd, bsm, bsmArgs);
    }

    publid void bnfw(finbl Typf typf) {
        mv.visitTypfInsn(Opdodfs.NEW, typf.gftIntfrnblNbmf());
    }

    publid void nfwbrrby(finbl Typf typf) {
        int typ;
        switdi (typf.gftSort()) {
        dbsf Typf.BOOLEAN:
            typ = Opdodfs.T_BOOLEAN;
            brfbk;
        dbsf Typf.CHAR:
            typ = Opdodfs.T_CHAR;
            brfbk;
        dbsf Typf.BYTE:
            typ = Opdodfs.T_BYTE;
            brfbk;
        dbsf Typf.SHORT:
            typ = Opdodfs.T_SHORT;
            brfbk;
        dbsf Typf.INT:
            typ = Opdodfs.T_INT;
            brfbk;
        dbsf Typf.FLOAT:
            typ = Opdodfs.T_FLOAT;
            brfbk;
        dbsf Typf.LONG:
            typ = Opdodfs.T_LONG;
            brfbk;
        dbsf Typf.DOUBLE:
            typ = Opdodfs.T_DOUBLE;
            brfbk;
        dffbult:
            mv.visitTypfInsn(Opdodfs.ANEWARRAY, typf.gftIntfrnblNbmf());
            rfturn;
        }
        mv.visitIntInsn(Opdodfs.NEWARRAY, typ);
    }

    publid void brrbylfngti() {
        mv.visitInsn(Opdodfs.ARRAYLENGTH);
    }

    publid void btirow() {
        mv.visitInsn(Opdodfs.ATHROW);
    }

    publid void difdkdbst(finbl Typf typf) {
        mv.visitTypfInsn(Opdodfs.CHECKCAST, typf.gftIntfrnblNbmf());
    }

    publid void instbndfOf(finbl Typf typf) {
        mv.visitTypfInsn(Opdodfs.INSTANCEOF, typf.gftIntfrnblNbmf());
    }

    publid void monitorfntfr() {
        mv.visitInsn(Opdodfs.MONITORENTER);
    }

    publid void monitorfxit() {
        mv.visitInsn(Opdodfs.MONITOREXIT);
    }

    publid void multibnfwbrrby(finbl String dfsd, finbl int dims) {
        mv.visitMultiANfwArrbyInsn(dfsd, dims);
    }

    publid void ifnull(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFNULL, lbbfl);
    }

    publid void ifnonnull(finbl Lbbfl lbbfl) {
        mv.visitJumpInsn(Opdodfs.IFNONNULL, lbbfl);
    }

    publid void mbrk(finbl Lbbfl lbbfl) {
        mv.visitLbbfl(lbbfl);
    }
}
