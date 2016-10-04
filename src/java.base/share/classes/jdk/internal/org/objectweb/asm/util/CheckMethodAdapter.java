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
import jbvb.io.StringWritfr;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfRfffrfndf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.MftiodNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.Anblyzfr;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.BbsidVbluf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.BbsidVfrififr;

/**
 * A {@link MftiodVisitor} tibt difdks tibt its mftiods brf propfrly usfd. Morf
 * prfdisfly tiis mftiod bdbptfr difdks fbdi instrudtion individublly, i.f.,
 * fbdi visit mftiod difdks somf prfdonditions bbsfd <i>only</i> on its
 * brgumfnts - sudi bs tif fbdt tibt tif givfn opdodf is dorrfdt for b givfn
 * visit mftiod. Tiis bdbptfr dbn blso pfrform somf bbsid dbtb flow difdks (morf
 * prfdisfly tiosf tibt dbn bf pfrformfd witiout tif full dlbss iifrbrdiy - sff
 * {@link jdk.intfrnbl.org.objfdtwfb.bsm.trff.bnblysis.BbsidVfrififr}). For instbndf in b
 * mftiod wiosf signbturf is <tt>void m ()</tt>, tif invblid instrudtion
 * IRETURN, or tif invblid sfqufndf IADD L2I will bf dftfdtfd if tif dbtb flow
 * difdks brf fnbblfd. Tifsf difdks brf fnbblfd by using tif
 * {@link #CifdkMftiodAdbptfr(int,String,String,MftiodVisitor,Mbp)} donstrudtor.
 * Tify brf not pfrformfd if bny otifr donstrudtor is usfd.
 *
 * @butior Erid Brunfton
 */
publid dlbss CifdkMftiodAdbptfr fxtfnds MftiodVisitor {

    /**
     * Tif dlbss vfrsion numbfr.
     */
    publid int vfrsion;

    /**
     * Tif bddfss flbgs of tif mftiod.
     */
    privbtf int bddfss;

    /**
     * <tt>truf</tt> if tif visitCodf mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn stbrtCodf;

    /**
     * <tt>truf</tt> if tif visitMbxs mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn fndCodf;

    /**
     * <tt>truf</tt> if tif visitEnd mftiod ibs bffn dbllfd.
     */
    privbtf boolfbn fndMftiod;

    /**
     * Numbfr of visitfd instrudtions.
     */
    privbtf int insnCount;

    /**
     * Tif blrfbdy visitfd lbbfls. Tiis mbp bssodibtf Intfgfr vblufs to psfudo
     * dodf offsfts.
     */
    privbtf finbl Mbp<Lbbfl, Intfgfr> lbbfls;

    /**
     * Tif lbbfls usfd in tiis mftiod. Evfry usfd lbbfl must bf visitfd witi
     * visitLbbfl bfforf tif fnd of tif mftiod (i.f. siould bf in #lbbfls).
     */
    privbtf Sft<Lbbfl> usfdLbbfls;

    /**
     * Numbfr of visitfd frbmfs in fxpbndfd form.
     */
    privbtf int fxpbndfdFrbmfs;

    /**
     * Numbfr of visitfd frbmfs in domprfssfd form.
     */
    privbtf int domprfssfdFrbmfs;

    /**
     * Numbfr of instrudtions bfforf tif lbst visitfd frbmf.
     */
    privbtf int lbstFrbmf = -1;

    /**
     * Tif fxdfption ibndlfr rbngfs. Ebdi pbir of list flfmfnt dontbins tif
     * stbrt bnd fnd lbbfls of bn fxdfption ibndlfr blodk.
     */
    privbtf List<Lbbfl> ibndlfrs;

    /**
     * Codf of tif visit mftiod to bf usfd for fbdi opdodf.
     */
    privbtf stbtid finbl int[] TYPE;

    /**
     * Tif Lbbfl.stbtus fifld.
     */
    privbtf stbtid Fifld lbbflStbtusFifld;

    stbtid {
        String s = "BBBBBBBBBBBBBBBBCCIAADDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBDD"
                + "DDDAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                + "BBBBBBBBBBBBBBBBBBBJBBBBBBBBBBBBBBBBBBBBHHHHHHHHHHHHHHHHD"
                + "KLBBBBBBFFFFGGGGAECEBBEEBBAMHHAA";
        TYPE = nfw int[s.lfngti()];
        for (int i = 0; i < TYPE.lfngti; ++i) {
            TYPE[i] = s.dibrAt(i) - 'A' - 1;
        }
    }

    // dodf to gfnfrbtf tif bbovf string
    // publid stbtid void mbin (String[] brgs) {
    // int[] TYPE = nfw int[] {
    // 0, //NOP
    // 0, //ACONST_NULL
    // 0, //ICONST_M1
    // 0, //ICONST_0
    // 0, //ICONST_1
    // 0, //ICONST_2
    // 0, //ICONST_3
    // 0, //ICONST_4
    // 0, //ICONST_5
    // 0, //LCONST_0
    // 0, //LCONST_1
    // 0, //FCONST_0
    // 0, //FCONST_1
    // 0, //FCONST_2
    // 0, //DCONST_0
    // 0, //DCONST_1
    // 1, //BIPUSH
    // 1, //SIPUSH
    // 7, //LDC
    // -1, //LDC_W
    // -1, //LDC2_W
    // 2, //ILOAD
    // 2, //LLOAD
    // 2, //FLOAD
    // 2, //DLOAD
    // 2, //ALOAD
    // -1, //ILOAD_0
    // -1, //ILOAD_1
    // -1, //ILOAD_2
    // -1, //ILOAD_3
    // -1, //LLOAD_0
    // -1, //LLOAD_1
    // -1, //LLOAD_2
    // -1, //LLOAD_3
    // -1, //FLOAD_0
    // -1, //FLOAD_1
    // -1, //FLOAD_2
    // -1, //FLOAD_3
    // -1, //DLOAD_0
    // -1, //DLOAD_1
    // -1, //DLOAD_2
    // -1, //DLOAD_3
    // -1, //ALOAD_0
    // -1, //ALOAD_1
    // -1, //ALOAD_2
    // -1, //ALOAD_3
    // 0, //IALOAD
    // 0, //LALOAD
    // 0, //FALOAD
    // 0, //DALOAD
    // 0, //AALOAD
    // 0, //BALOAD
    // 0, //CALOAD
    // 0, //SALOAD
    // 2, //ISTORE
    // 2, //LSTORE
    // 2, //FSTORE
    // 2, //DSTORE
    // 2, //ASTORE
    // -1, //ISTORE_0
    // -1, //ISTORE_1
    // -1, //ISTORE_2
    // -1, //ISTORE_3
    // -1, //LSTORE_0
    // -1, //LSTORE_1
    // -1, //LSTORE_2
    // -1, //LSTORE_3
    // -1, //FSTORE_0
    // -1, //FSTORE_1
    // -1, //FSTORE_2
    // -1, //FSTORE_3
    // -1, //DSTORE_0
    // -1, //DSTORE_1
    // -1, //DSTORE_2
    // -1, //DSTORE_3
    // -1, //ASTORE_0
    // -1, //ASTORE_1
    // -1, //ASTORE_2
    // -1, //ASTORE_3
    // 0, //IASTORE
    // 0, //LASTORE
    // 0, //FASTORE
    // 0, //DASTORE
    // 0, //AASTORE
    // 0, //BASTORE
    // 0, //CASTORE
    // 0, //SASTORE
    // 0, //POP
    // 0, //POP2
    // 0, //DUP
    // 0, //DUP_X1
    // 0, //DUP_X2
    // 0, //DUP2
    // 0, //DUP2_X1
    // 0, //DUP2_X2
    // 0, //SWAP
    // 0, //IADD
    // 0, //LADD
    // 0, //FADD
    // 0, //DADD
    // 0, //ISUB
    // 0, //LSUB
    // 0, //FSUB
    // 0, //DSUB
    // 0, //IMUL
    // 0, //LMUL
    // 0, //FMUL
    // 0, //DMUL
    // 0, //IDIV
    // 0, //LDIV
    // 0, //FDIV
    // 0, //DDIV
    // 0, //IREM
    // 0, //LREM
    // 0, //FREM
    // 0, //DREM
    // 0, //INEG
    // 0, //LNEG
    // 0, //FNEG
    // 0, //DNEG
    // 0, //ISHL
    // 0, //LSHL
    // 0, //ISHR
    // 0, //LSHR
    // 0, //IUSHR
    // 0, //LUSHR
    // 0, //IAND
    // 0, //LAND
    // 0, //IOR
    // 0, //LOR
    // 0, //IXOR
    // 0, //LXOR
    // 8, //IINC
    // 0, //I2L
    // 0, //I2F
    // 0, //I2D
    // 0, //L2I
    // 0, //L2F
    // 0, //L2D
    // 0, //F2I
    // 0, //F2L
    // 0, //F2D
    // 0, //D2I
    // 0, //D2L
    // 0, //D2F
    // 0, //I2B
    // 0, //I2C
    // 0, //I2S
    // 0, //LCMP
    // 0, //FCMPL
    // 0, //FCMPG
    // 0, //DCMPL
    // 0, //DCMPG
    // 6, //IFEQ
    // 6, //IFNE
    // 6, //IFLT
    // 6, //IFGE
    // 6, //IFGT
    // 6, //IFLE
    // 6, //IF_ICMPEQ
    // 6, //IF_ICMPNE
    // 6, //IF_ICMPLT
    // 6, //IF_ICMPGE
    // 6, //IF_ICMPGT
    // 6, //IF_ICMPLE
    // 6, //IF_ACMPEQ
    // 6, //IF_ACMPNE
    // 6, //GOTO
    // 6, //JSR
    // 2, //RET
    // 9, //TABLESWITCH
    // 10, //LOOKUPSWITCH
    // 0, //IRETURN
    // 0, //LRETURN
    // 0, //FRETURN
    // 0, //DRETURN
    // 0, //ARETURN
    // 0, //RETURN
    // 4, //GETSTATIC
    // 4, //PUTSTATIC
    // 4, //GETFIELD
    // 4, //PUTFIELD
    // 5, //INVOKEVIRTUAL
    // 5, //INVOKESPECIAL
    // 5, //INVOKESTATIC
    // 5, //INVOKEINTERFACE
    // -1, //INVOKEDYNAMIC
    // 3, //NEW
    // 1, //NEWARRAY
    // 3, //ANEWARRAY
    // 0, //ARRAYLENGTH
    // 0, //ATHROW
    // 3, //CHECKCAST
    // 3, //INSTANCEOF
    // 0, //MONITORENTER
    // 0, //MONITOREXIT
    // -1, //WIDE
    // 11, //MULTIANEWARRAY
    // 6, //IFNULL
    // 6, //IFNONNULL
    // -1, //GOTO_W
    // -1 //JSR_W
    // };
    // for (int i = 0; i < TYPE.lfngti; ++i) {
    // Systfm.out.print((dibr)(TYPE[i] + 1 + 'A'));
    // }
    // Systfm.out.println();
    // }

    /**
     * Construdts b nfw {@link CifdkMftiodAdbptfr} objfdt. Tiis mftiod bdbptfr
     * will not pfrform bny dbtb flow difdk (sff
     * {@link #CifdkMftiodAdbptfr(int,String,String,MftiodVisitor,Mbp)}).
     * <i>Subdlbssfs must not usf tiis donstrudtor</i>. Instfbd, tify must usf
     * tif {@link #CifdkMftiodAdbptfr(int, MftiodVisitor, Mbp)} vfrsion.
     *
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     */
    publid CifdkMftiodAdbptfr(finbl MftiodVisitor mv) {
        tiis(mv, nfw HbsiMbp<Lbbfl, Intfgfr>());
    }

    /**
     * Construdts b nfw {@link CifdkMftiodAdbptfr} objfdt. Tiis mftiod bdbptfr
     * will not pfrform bny dbtb flow difdk (sff
     * {@link #CifdkMftiodAdbptfr(int,String,String,MftiodVisitor,Mbp)}).
     * <i>Subdlbssfs must not usf tiis donstrudtor</i>. Instfbd, tify must usf
     * tif {@link #CifdkMftiodAdbptfr(int, MftiodVisitor, Mbp)} vfrsion.
     *
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     * @pbrbm lbbfls
     *            b mbp of blrfbdy visitfd lbbfls (in otifr mftiods).
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid CifdkMftiodAdbptfr(finbl MftiodVisitor mv,
            finbl Mbp<Lbbfl, Intfgfr> lbbfls) {
        tiis(Opdodfs.ASM5, mv, lbbfls);
        if (gftClbss() != CifdkMftiodAdbptfr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link CifdkMftiodAdbptfr} objfdt. Tiis mftiod bdbptfr
     * will not pfrform bny dbtb flow difdk (sff
     * {@link #CifdkMftiodAdbptfr(int,String,String,MftiodVisitor,Mbp)}).
     *
     * @pbrbm mv
     *            tif mftiod visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     * @pbrbm lbbfls
     *            b mbp of blrfbdy visitfd lbbfls (in otifr mftiods).
     */
    protfdtfd CifdkMftiodAdbptfr(finbl int bpi, finbl MftiodVisitor mv,
            finbl Mbp<Lbbfl, Intfgfr> lbbfls) {
        supfr(bpi, mv);
        tiis.lbbfls = lbbfls;
        tiis.usfdLbbfls = nfw HbsiSft<Lbbfl>();
        tiis.ibndlfrs = nfw ArrbyList<Lbbfl>();
    }

    /**
     * Construdts b nfw {@link CifdkMftiodAdbptfr} objfdt. Tiis mftiod bdbptfr
     * will pfrform bbsid dbtb flow difdks. For instbndf in b mftiod wiosf
     * signbturf is <tt>void m ()</tt>, tif invblid instrudtion IRETURN, or tif
     * invblid sfqufndf IADD L2I will bf dftfdtfd.
     *
     * @pbrbm bddfss
     *            tif mftiod's bddfss flbgs.
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf Typf}).
     * @pbrbm dmv
     *            tif mftiod visitor to wiidi tiis bdbptfr must dflfgbtf dblls.
     * @pbrbm lbbfls
     *            b mbp of blrfbdy visitfd lbbfls (in otifr mftiods).
     */
    publid CifdkMftiodAdbptfr(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl MftiodVisitor dmv,
            finbl Mbp<Lbbfl, Intfgfr> lbbfls) {
        tiis(nfw MftiodNodf(Opdodfs.ASM5, bddfss, nbmf, dfsd, null, null) {
            @Ovfrridf
            publid void visitEnd() {
                Anblyzfr<BbsidVbluf> b = nfw Anblyzfr<BbsidVbluf>(
                        nfw BbsidVfrififr());
                try {
                    b.bnblyzf("dummy", tiis);
                } dbtdi (Exdfption f) {
                    if (f instbndfof IndfxOutOfBoundsExdfption
                            && mbxLodbls == 0 && mbxStbdk == 0) {
                        tirow nfw RuntimfExdfption(
                                "Dbtb flow difdking option rfquirfs vblid, non zfro mbxLodbls bnd mbxStbdk vblufs.");
                    }
                    f.printStbdkTrbdf();
                    StringWritfr sw = nfw StringWritfr();
                    PrintWritfr pw = nfw PrintWritfr(sw, truf);
                    CifdkClbssAdbptfr.printAnblyzfrRfsult(tiis, b, pw);
                    pw.dlosf();
                    tirow nfw RuntimfExdfption(f.gftMfssbgf() + ' '
                            + sw.toString());
                }
                bddfpt(dmv);
            }
        }, lbbfls);
        tiis.bddfss = bddfss;
    }

    @Ovfrridf
    publid void visitPbrbmftfr(String nbmf, int bddfss) {
        if (nbmf != null) {
            difdkUnqublififdNbmf(vfrsion, nbmf, "nbmf");
        }
        CifdkClbssAdbptfr.difdkAddfss(bddfss, Opdodfs.ACC_FINAL
                + Opdodfs.ACC_MANDATED + Opdodfs.ACC_SYNTHETIC);
        supfr.visitPbrbmftfr(nbmf, bddfss);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        difdkEndMftiod();
        difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitAnnotbtion(dfsd, visiblf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        difdkEndMftiod();
        int sort = typfRff >>> 24;
        if (sort != TypfRfffrfndf.METHOD_TYPE_PARAMETER
                && sort != TypfRfffrfndf.METHOD_TYPE_PARAMETER_BOUND
                && sort != TypfRfffrfndf.METHOD_RETURN
                && sort != TypfRfffrfndf.METHOD_RECEIVER
                && sort != TypfRfffrfndf.METHOD_FORMAL_PARAMETER
                && sort != TypfRfffrfndf.THROWS) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(sort));
        }
        CifdkClbssAdbptfr.difdkTypfRffAndPbti(typfRff, typfPbti);
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitTypfAnnotbtion(typfRff,
                typfPbti, dfsd, visiblf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtionDffbult() {
        difdkEndMftiod();
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitAnnotbtionDffbult(), fblsf);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitPbrbmftfrAnnotbtion(finbl int pbrbmftfr,
            finbl String dfsd, finbl boolfbn visiblf) {
        difdkEndMftiod();
        difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitPbrbmftfrAnnotbtion(
                pbrbmftfr, dfsd, visiblf));
    }

    @Ovfrridf
    publid void visitAttributf(finbl Attributf bttr) {
        difdkEndMftiod();
        if (bttr == null) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid bttributf (must not bf null)");
        }
        supfr.visitAttributf(bttr);
    }

    @Ovfrridf
    publid void visitCodf() {
        if ((bddfss & Opdodfs.ACC_ABSTRACT) != 0) {
            tirow nfw RuntimfExdfption("Abstrbdt mftiods dbnnot ibvf dodf");
        }
        stbrtCodf = truf;
        supfr.visitCodf();
    }

    @Ovfrridf
    publid void visitFrbmf(finbl int typf, finbl int nLodbl,
            finbl Objfdt[] lodbl, finbl int nStbdk, finbl Objfdt[] stbdk) {
        if (insnCount == lbstFrbmf) {
            tirow nfw IllfgblStbtfExdfption(
                    "At most onf frbmf dbn bf visitfd bt b givfn dodf lodbtion.");
        }
        lbstFrbmf = insnCount;
        int mLodbl;
        int mStbdk;
        switdi (typf) {
        dbsf Opdodfs.F_NEW:
        dbsf Opdodfs.F_FULL:
            mLodbl = Intfgfr.MAX_VALUE;
            mStbdk = Intfgfr.MAX_VALUE;
            brfbk;

        dbsf Opdodfs.F_SAME:
            mLodbl = 0;
            mStbdk = 0;
            brfbk;

        dbsf Opdodfs.F_SAME1:
            mLodbl = 0;
            mStbdk = 1;
            brfbk;

        dbsf Opdodfs.F_APPEND:
        dbsf Opdodfs.F_CHOP:
            mLodbl = 3;
            mStbdk = 0;
            brfbk;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid frbmf typf " + typf);
        }

        if (nLodbl > mLodbl) {
            tirow nfw IllfgblArgumfntExdfption("Invblid nLodbl=" + nLodbl
                    + " for frbmf typf " + typf);
        }
        if (nStbdk > mStbdk) {
            tirow nfw IllfgblArgumfntExdfption("Invblid nStbdk=" + nStbdk
                    + " for frbmf typf " + typf);
        }

        if (typf != Opdodfs.F_CHOP) {
            if (nLodbl > 0 && (lodbl == null || lodbl.lfngti < nLodbl)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Arrby lodbl[] is siortfr tibn nLodbl");
            }
            for (int i = 0; i < nLodbl; ++i) {
                difdkFrbmfVbluf(lodbl[i]);
            }
        }
        if (nStbdk > 0 && (stbdk == null || stbdk.lfngti < nStbdk)) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Arrby stbdk[] is siortfr tibn nStbdk");
        }
        for (int i = 0; i < nStbdk; ++i) {
            difdkFrbmfVbluf(stbdk[i]);
        }
        if (typf == Opdodfs.F_NEW) {
            ++fxpbndfdFrbmfs;
        } flsf {
            ++domprfssfdFrbmfs;
        }
        if (fxpbndfdFrbmfs > 0 && domprfssfdFrbmfs > 0) {
            tirow nfw RuntimfExdfption(
                    "Expbndfd bnd domprfssfd frbmfs must not bf mixfd.");
        }
        supfr.visitFrbmf(typf, nLodbl, lodbl, nStbdk, stbdk);
    }

    @Ovfrridf
    publid void visitInsn(finbl int opdodf) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 0);
        supfr.visitInsn(opdodf);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitIntInsn(finbl int opdodf, finbl int opfrbnd) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 1);
        switdi (opdodf) {
        dbsf Opdodfs.BIPUSH:
            difdkSignfdBytf(opfrbnd, "Invblid opfrbnd");
            brfbk;
        dbsf Opdodfs.SIPUSH:
            difdkSignfdSiort(opfrbnd, "Invblid opfrbnd");
            brfbk;
        // dbsf Constbnts.NEWARRAY:
        dffbult:
            if (opfrbnd < Opdodfs.T_BOOLEAN || opfrbnd > Opdodfs.T_LONG) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Invblid opfrbnd (must bf bn brrby typf dodf T_...): "
                                + opfrbnd);
            }
        }
        supfr.visitIntInsn(opdodf, opfrbnd);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitVbrInsn(finbl int opdodf, finbl int vbr) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 2);
        difdkUnsignfdSiort(vbr, "Invblid vbribblf indfx");
        supfr.visitVbrInsn(opdodf, vbr);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitTypfInsn(finbl int opdodf, finbl String typf) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 3);
        difdkIntfrnblNbmf(typf, "typf");
        if (opdodf == Opdodfs.NEW && typf.dibrAt(0) == '[') {
            tirow nfw IllfgblArgumfntExdfption(
                    "NEW dbnnot bf usfd to drfbtf brrbys: " + typf);
        }
        supfr.visitTypfInsn(opdodf, typf);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitFifldInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 4);
        difdkIntfrnblNbmf(ownfr, "ownfr");
        difdkUnqublififdNbmf(vfrsion, nbmf, "nbmf");
        difdkDfsd(dfsd, fblsf);
        supfr.visitFifldInsn(opdodf, ownfr, nbmf, dfsd);
        ++insnCount;
    }

    @Dfprfdbtfd
    @Ovfrridf
    publid void visitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd,
                opdodf == Opdodfs.INVOKEINTERFACE);
    }

    @Ovfrridf
    publid void visitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd, boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        doVisitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
    }

    privbtf void doVisitMftiodInsn(int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd, finbl boolfbn itf) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 5);
        if (opdodf != Opdodfs.INVOKESPECIAL || !"<init>".fqubls(nbmf)) {
            difdkMftiodIdfntififr(vfrsion, nbmf, "nbmf");
        }
        difdkIntfrnblNbmf(ownfr, "ownfr");
        difdkMftiodDfsd(dfsd);
        if (opdodf == Opdodfs.INVOKEVIRTUAL && itf) {
            tirow nfw IllfgblArgumfntExdfption(
                    "INVOKEVIRTUAL dbn't bf usfd witi intfrfbdfs");
        }
        if (opdodf == Opdodfs.INVOKEINTERFACE && !itf) {
            tirow nfw IllfgblArgumfntExdfption(
                    "INVOKEINTERFACE dbn't bf usfd witi dlbssfs");
        }
        // Cblling supfr.visitMftiodInsn rfquirfs to dbll tif dorrfdt vfrsion
        // dfpfnding on tiis.bpi (otifrwisf infinitf loops dbn oddur). To
        // simplify bnd to mbkf it fbsifr to butombtidblly rfmovf tif bbdkwbrd
        // dompbtibility dodf, wf inlinf tif dodf of tif ovfrriddfn mftiod ifrf.
        if (mv != null) {
            mv.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
        }
        ++insnCount;
    }

    @Ovfrridf
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkMftiodIdfntififr(vfrsion, nbmf, "nbmf");
        difdkMftiodDfsd(dfsd);
        if (bsm.gftTbg() != Opdodfs.H_INVOKESTATIC
                && bsm.gftTbg() != Opdodfs.H_NEWINVOKESPECIAL) {
            tirow nfw IllfgblArgumfntExdfption("invblid ibndlf tbg "
                    + bsm.gftTbg());
        }
        for (int i = 0; i < bsmArgs.lfngti; i++) {
            difdkLDCConstbnt(bsmArgs[i]);
        }
        supfr.visitInvokfDynbmidInsn(nbmf, dfsd, bsm, bsmArgs);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbbfl) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkOpdodf(opdodf, 6);
        difdkLbbfl(lbbfl, fblsf, "lbbfl");
        difdkNonDfbugLbbfl(lbbfl);
        supfr.visitJumpInsn(opdodf, lbbfl);
        usfdLbbfls.bdd(lbbfl);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitLbbfl(finbl Lbbfl lbbfl) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkLbbfl(lbbfl, fblsf, "lbbfl");
        if (lbbfls.gft(lbbfl) != null) {
            tirow nfw IllfgblArgumfntExdfption("Alrfbdy visitfd lbbfl");
        }
        lbbfls.put(lbbfl, nfw Intfgfr(insnCount));
        supfr.visitLbbfl(lbbfl);
    }

    @Ovfrridf
    publid void visitLddInsn(finbl Objfdt dst) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkLDCConstbnt(dst);
        supfr.visitLddInsn(dst);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitIindInsn(finbl int vbr, finbl int indrfmfnt) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkUnsignfdSiort(vbr, "Invblid vbribblf indfx");
        difdkSignfdSiort(indrfmfnt, "Invblid indrfmfnt");
        supfr.visitIindInsn(vbr, indrfmfnt);
        ++insnCount;
    }

    @Ovfrridf
    publid void visitTbblfSwitdiInsn(finbl int min, finbl int mbx,
            finbl Lbbfl dflt, finbl Lbbfl... lbbfls) {
        difdkStbrtCodf();
        difdkEndCodf();
        if (mbx < min) {
            tirow nfw IllfgblArgumfntExdfption("Mbx = " + mbx
                    + " must bf grfbtfr tibn or fqubl to min = " + min);
        }
        difdkLbbfl(dflt, fblsf, "dffbult lbbfl");
        difdkNonDfbugLbbfl(dflt);
        if (lbbfls == null || lbbfls.lfngti != mbx - min + 1) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Tifrf must bf mbx - min + 1 lbbfls");
        }
        for (int i = 0; i < lbbfls.lfngti; ++i) {
            difdkLbbfl(lbbfls[i], fblsf, "lbbfl bt indfx " + i);
            difdkNonDfbugLbbfl(lbbfls[i]);
        }
        supfr.visitTbblfSwitdiInsn(min, mbx, dflt, lbbfls);
        for (int i = 0; i < lbbfls.lfngti; ++i) {
            usfdLbbfls.bdd(lbbfls[i]);
        }
        ++insnCount;
    }

    @Ovfrridf
    publid void visitLookupSwitdiInsn(finbl Lbbfl dflt, finbl int[] kfys,
            finbl Lbbfl[] lbbfls) {
        difdkEndCodf();
        difdkStbrtCodf();
        difdkLbbfl(dflt, fblsf, "dffbult lbbfl");
        difdkNonDfbugLbbfl(dflt);
        if (kfys == null || lbbfls == null || kfys.lfngti != lbbfls.lfngti) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Tifrf must bf tif sbmf numbfr of kfys bnd lbbfls");
        }
        for (int i = 0; i < lbbfls.lfngti; ++i) {
            difdkLbbfl(lbbfls[i], fblsf, "lbbfl bt indfx " + i);
            difdkNonDfbugLbbfl(lbbfls[i]);
        }
        supfr.visitLookupSwitdiInsn(dflt, kfys, lbbfls);
        usfdLbbfls.bdd(dflt);
        for (int i = 0; i < lbbfls.lfngti; ++i) {
            usfdLbbfls.bdd(lbbfls[i]);
        }
        ++insnCount;
    }

    @Ovfrridf
    publid void visitMultiANfwArrbyInsn(finbl String dfsd, finbl int dims) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkDfsd(dfsd, fblsf);
        if (dfsd.dibrAt(0) != '[') {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid dfsdriptor (must bf bn brrby typf dfsdriptor): "
                            + dfsd);
        }
        if (dims < 1) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid dimfnsions (must bf grfbtfr tibn 0): " + dims);
        }
        if (dims > dfsd.lbstIndfxOf('[') + 1) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid dimfnsions (must not bf grfbtfr tibn dims(dfsd)): "
                            + dims);
        }
        supfr.visitMultiANfwArrbyInsn(dfsd, dims);
        ++insnCount;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitInsnAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        difdkStbrtCodf();
        difdkEndCodf();
        int sort = typfRff >>> 24;
        if (sort != TypfRfffrfndf.INSTANCEOF && sort != TypfRfffrfndf.NEW
                && sort != TypfRfffrfndf.CONSTRUCTOR_REFERENCE
                && sort != TypfRfffrfndf.METHOD_REFERENCE
                && sort != TypfRfffrfndf.CAST
                && sort != TypfRfffrfndf.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
                && sort != TypfRfffrfndf.METHOD_INVOCATION_TYPE_ARGUMENT
                && sort != TypfRfffrfndf.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
                && sort != TypfRfffrfndf.METHOD_REFERENCE_TYPE_ARGUMENT) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(sort));
        }
        CifdkClbssAdbptfr.difdkTypfRffAndPbti(typfRff, typfPbti);
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitInsnAnnotbtion(typfRff,
                typfPbti, dfsd, visiblf));
    }

    @Ovfrridf
    publid void visitTryCbtdiBlodk(finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl Lbbfl ibndlfr, finbl String typf) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkLbbfl(stbrt, fblsf, "stbrt lbbfl");
        difdkLbbfl(fnd, fblsf, "fnd lbbfl");
        difdkLbbfl(ibndlfr, fblsf, "ibndlfr lbbfl");
        difdkNonDfbugLbbfl(stbrt);
        difdkNonDfbugLbbfl(fnd);
        difdkNonDfbugLbbfl(ibndlfr);
        if (lbbfls.gft(stbrt) != null || lbbfls.gft(fnd) != null
                || lbbfls.gft(ibndlfr) != null) {
            tirow nfw IllfgblStbtfExdfption(
                    "Try dbtdi blodks must bf visitfd bfforf tifir lbbfls");
        }
        if (typf != null) {
            difdkIntfrnblNbmf(typf, "typf");
        }
        supfr.visitTryCbtdiBlodk(stbrt, fnd, ibndlfr, typf);
        ibndlfrs.bdd(stbrt);
        ibndlfrs.bdd(fnd);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTryCbtdiAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        difdkStbrtCodf();
        difdkEndCodf();
        int sort = typfRff >>> 24;
        if (sort != TypfRfffrfndf.EXCEPTION_PARAMETER) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(sort));
        }
        CifdkClbssAdbptfr.difdkTypfRffAndPbti(typfRff, typfPbti);
        CifdkMftiodAdbptfr.difdkDfsd(dfsd, fblsf);
        rfturn nfw CifdkAnnotbtionAdbptfr(supfr.visitTryCbtdiAnnotbtion(
                typfRff, typfPbti, dfsd, visiblf));
    }

    @Ovfrridf
    publid void visitLodblVbribblf(finbl String nbmf, finbl String dfsd,
            finbl String signbturf, finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl int indfx) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkUnqublififdNbmf(vfrsion, nbmf, "nbmf");
        difdkDfsd(dfsd, fblsf);
        difdkLbbfl(stbrt, truf, "stbrt lbbfl");
        difdkLbbfl(fnd, truf, "fnd lbbfl");
        difdkUnsignfdSiort(indfx, "Invblid vbribblf indfx");
        int s = lbbfls.gft(stbrt).intVbluf();
        int f = lbbfls.gft(fnd).intVbluf();
        if (f < s) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid stbrt bnd fnd lbbfls (fnd must bf grfbtfr tibn stbrt)");
        }
        supfr.visitLodblVbribblf(nbmf, dfsd, signbturf, stbrt, fnd, indfx);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitLodblVbribblfAnnotbtion(int typfRff,
            TypfPbti typfPbti, Lbbfl[] stbrt, Lbbfl[] fnd, int[] indfx,
            String dfsd, boolfbn visiblf) {
        difdkStbrtCodf();
        difdkEndCodf();
        int sort = typfRff >>> 24;
        if (sort != TypfRfffrfndf.LOCAL_VARIABLE
                && sort != TypfRfffrfndf.RESOURCE_VARIABLE) {
            tirow nfw IllfgblArgumfntExdfption("Invblid typf rfffrfndf sort 0x"
                    + Intfgfr.toHfxString(sort));
        }
        CifdkClbssAdbptfr.difdkTypfRffAndPbti(typfRff, typfPbti);
        difdkDfsd(dfsd, fblsf);
        if (stbrt == null || fnd == null || indfx == null
                || fnd.lfngti != stbrt.lfngti || indfx.lfngti != stbrt.lfngti) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid stbrt, fnd bnd indfx brrbys (must bf non null bnd of idfntidbl lfngti");
        }
        for (int i = 0; i < stbrt.lfngti; ++i) {
            difdkLbbfl(stbrt[i], truf, "stbrt lbbfl");
            difdkLbbfl(fnd[i], truf, "fnd lbbfl");
            difdkUnsignfdSiort(indfx[i], "Invblid vbribblf indfx");
            int s = lbbfls.gft(stbrt[i]).intVbluf();
            int f = lbbfls.gft(fnd[i]).intVbluf();
            if (f < s) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Invblid stbrt bnd fnd lbbfls (fnd must bf grfbtfr tibn stbrt)");
            }
        }
        rfturn supfr.visitLodblVbribblfAnnotbtion(typfRff, typfPbti, stbrt,
                fnd, indfx, dfsd, visiblf);
    }

    @Ovfrridf
    publid void visitLinfNumbfr(finbl int linf, finbl Lbbfl stbrt) {
        difdkStbrtCodf();
        difdkEndCodf();
        difdkUnsignfdSiort(linf, "Invblid linf numbfr");
        difdkLbbfl(stbrt, truf, "stbrt lbbfl");
        supfr.visitLinfNumbfr(linf, stbrt);
    }

    @Ovfrridf
    publid void visitMbxs(finbl int mbxStbdk, finbl int mbxLodbls) {
        difdkStbrtCodf();
        difdkEndCodf();
        fndCodf = truf;
        for (Lbbfl l : usfdLbbfls) {
            if (lbbfls.gft(l) == null) {
                tirow nfw IllfgblStbtfExdfption("Undffinfd lbbfl usfd");
            }
        }
        for (int i = 0; i < ibndlfrs.sizf();) {
            Intfgfr stbrt = lbbfls.gft(ibndlfrs.gft(i++));
            Intfgfr fnd = lbbfls.gft(ibndlfrs.gft(i++));
            if (stbrt == null || fnd == null) {
                tirow nfw IllfgblStbtfExdfption(
                        "Undffinfd try dbtdi blodk lbbfls");
            }
            if (fnd.intVbluf() <= stbrt.intVbluf()) {
                tirow nfw IllfgblStbtfExdfption(
                        "Emty try dbtdi blodk ibndlfr rbngf");
            }
        }
        difdkUnsignfdSiort(mbxStbdk, "Invblid mbx stbdk");
        difdkUnsignfdSiort(mbxLodbls, "Invblid mbx lodbls");
        supfr.visitMbxs(mbxStbdk, mbxLodbls);
    }

    @Ovfrridf
    publid void visitEnd() {
        difdkEndMftiod();
        fndMftiod = truf;
        supfr.visitEnd();
    }

    // -------------------------------------------------------------------------

    /**
     * Cifdks tibt tif visitCodf mftiod ibs bffn dbllfd.
     */
    void difdkStbrtCodf() {
        if (!stbrtCodf) {
            tirow nfw IllfgblStbtfExdfption(
                    "Cbnnot visit instrudtions bfforf visitCodf ibs bffn dbllfd.");
        }
    }

    /**
     * Cifdks tibt tif visitMbxs mftiod ibs not bffn dbllfd.
     */
    void difdkEndCodf() {
        if (fndCodf) {
            tirow nfw IllfgblStbtfExdfption(
                    "Cbnnot visit instrudtions bftfr visitMbxs ibs bffn dbllfd.");
        }
    }

    /**
     * Cifdks tibt tif visitEnd mftiod ibs not bffn dbllfd.
     */
    void difdkEndMftiod() {
        if (fndMftiod) {
            tirow nfw IllfgblStbtfExdfption(
                    "Cbnnot visit flfmfnts bftfr visitEnd ibs bffn dbllfd.");
        }
    }

    /**
     * Cifdks b stbdk frbmf vbluf.
     *
     * @pbrbm vbluf
     *            tif vbluf to bf difdkfd.
     */
    void difdkFrbmfVbluf(finbl Objfdt vbluf) {
        if (vbluf == Opdodfs.TOP || vbluf == Opdodfs.INTEGER
                || vbluf == Opdodfs.FLOAT || vbluf == Opdodfs.LONG
                || vbluf == Opdodfs.DOUBLE || vbluf == Opdodfs.NULL
                || vbluf == Opdodfs.UNINITIALIZED_THIS) {
            rfturn;
        }
        if (vbluf instbndfof String) {
            difdkIntfrnblNbmf((String) vbluf, "Invblid stbdk frbmf vbluf");
            rfturn;
        }
        if (!(vbluf instbndfof Lbbfl)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid stbdk frbmf vbluf: "
                    + vbluf);
        } flsf {
            usfdLbbfls.bdd((Lbbfl) vbluf);
        }
    }

    /**
     * Cifdks tibt tif typf of tif givfn opdodf is fqubl to tif givfn typf.
     *
     * @pbrbm opdodf
     *            tif opdodf to bf difdkfd.
     * @pbrbm typf
     *            tif fxpfdtfd opdodf typf.
     */
    stbtid void difdkOpdodf(finbl int opdodf, finbl int typf) {
        if (opdodf < 0 || opdodf > 199 || TYPE[opdodf] != typf) {
            tirow nfw IllfgblArgumfntExdfption("Invblid opdodf: " + opdodf);
        }
    }

    /**
     * Cifdks tibt tif givfn vbluf is b signfd bytf.
     *
     * @pbrbm vbluf
     *            tif vbluf to bf difdkfd.
     * @pbrbm msg
     *            bn mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkSignfdBytf(finbl int vbluf, finbl String msg) {
        if (vbluf < Bytf.MIN_VALUE || vbluf > Bytf.MAX_VALUE) {
            tirow nfw IllfgblArgumfntExdfption(msg
                    + " (must bf b signfd bytf): " + vbluf);
        }
    }

    /**
     * Cifdks tibt tif givfn vbluf is b signfd siort.
     *
     * @pbrbm vbluf
     *            tif vbluf to bf difdkfd.
     * @pbrbm msg
     *            bn mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkSignfdSiort(finbl int vbluf, finbl String msg) {
        if (vbluf < Siort.MIN_VALUE || vbluf > Siort.MAX_VALUE) {
            tirow nfw IllfgblArgumfntExdfption(msg
                    + " (must bf b signfd siort): " + vbluf);
        }
    }

    /**
     * Cifdks tibt tif givfn vbluf is bn unsignfd siort.
     *
     * @pbrbm vbluf
     *            tif vbluf to bf difdkfd.
     * @pbrbm msg
     *            bn mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkUnsignfdSiort(finbl int vbluf, finbl String msg) {
        if (vbluf < 0 || vbluf > 65535) {
            tirow nfw IllfgblArgumfntExdfption(msg
                    + " (must bf bn unsignfd siort): " + vbluf);
        }
    }

    /**
     * Cifdks tibt tif givfn vbluf is bn {@link Intfgfr}, b{@link Flobt}, b
     * {@link Long}, b {@link Doublf} or b {@link String}.
     *
     * @pbrbm dst
     *            tif vbluf to bf difdkfd.
     */
    stbtid void difdkConstbnt(finbl Objfdt dst) {
        if (!(dst instbndfof Intfgfr) && !(dst instbndfof Flobt)
                && !(dst instbndfof Long) && !(dst instbndfof Doublf)
                && !(dst instbndfof String)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid donstbnt: " + dst);
        }
    }

    void difdkLDCConstbnt(finbl Objfdt dst) {
        if (dst instbndfof Typf) {
            int s = ((Typf) dst).gftSort();
            if (s != Typf.OBJECT && s != Typf.ARRAY && s != Typf.METHOD) {
                tirow nfw IllfgblArgumfntExdfption("Illfgbl LDC donstbnt vbluf");
            }
            if (s != Typf.METHOD && (vfrsion & 0xFFFF) < Opdodfs.V1_5) {
                tirow nfw IllfgblArgumfntExdfption(
                        "ldd of b donstbnt dlbss rfquirfs bt lfbst vfrsion 1.5");
            }
            if (s == Typf.METHOD && (vfrsion & 0xFFFF) < Opdodfs.V1_7) {
                tirow nfw IllfgblArgumfntExdfption(
                        "ldd of b mftiod typf rfquirfs bt lfbst vfrsion 1.7");
            }
        } flsf if (dst instbndfof Hbndlf) {
            if ((vfrsion & 0xFFFF) < Opdodfs.V1_7) {
                tirow nfw IllfgblArgumfntExdfption(
                        "ldd of b ibndlf rfquirfs bt lfbst vfrsion 1.7");
            }
            int tbg = ((Hbndlf) dst).gftTbg();
            if (tbg < Opdodfs.H_GETFIELD || tbg > Opdodfs.H_INVOKEINTERFACE) {
                tirow nfw IllfgblArgumfntExdfption("invblid ibndlf tbg " + tbg);
            }
        } flsf {
            difdkConstbnt(dst);
        }
    }

    /**
     * Cifdks tibt tif givfn string is b vblid unqublififd nbmf.
     *
     * @pbrbm vfrsion
     *            tif dlbss vfrsion.
     * @pbrbm nbmf
     *            tif string to bf difdkfd.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkUnqublififdNbmf(int vfrsion, finbl String nbmf,
            finbl String msg) {
        if ((vfrsion & 0xFFFF) < Opdodfs.V1_5) {
            difdkIdfntififr(nbmf, msg);
        } flsf {
            for (int i = 0; i < nbmf.lfngti(); ++i) {
                if (".;[/".indfxOf(nbmf.dibrAt(i)) != -1) {
                    tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                            + " (must bf b vblid unqublififd nbmf): " + nbmf);
                }
            }
        }
    }

    /**
     * Cifdks tibt tif givfn string is b vblid Jbvb idfntififr.
     *
     * @pbrbm nbmf
     *            tif string to bf difdkfd.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkIdfntififr(finbl String nbmf, finbl String msg) {
        difdkIdfntififr(nbmf, 0, -1, msg);
    }

    /**
     * Cifdks tibt tif givfn substring is b vblid Jbvb idfntififr.
     *
     * @pbrbm nbmf
     *            tif string to bf difdkfd.
     * @pbrbm stbrt
     *            indfx of tif first dibrbdtfr of tif idfntififr (indlusivf).
     * @pbrbm fnd
     *            indfx of tif lbst dibrbdtfr of tif idfntififr (fxdlusivf). -1
     *            is fquivblfnt to <tt>nbmf.lfngti()</tt> if nbmf is not
     *            <tt>null</tt>.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkIdfntififr(finbl String nbmf, finbl int stbrt,
            finbl int fnd, finbl String msg) {
        if (nbmf == null || (fnd == -1 ? nbmf.lfngti() <= stbrt : fnd <= stbrt)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                    + " (must not bf null or fmpty)");
        }
        if (!Cibrbdtfr.isJbvbIdfntififrStbrt(nbmf.dibrAt(stbrt))) {
            tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                    + " (must bf b vblid Jbvb idfntififr): " + nbmf);
        }
        int mbx = fnd == -1 ? nbmf.lfngti() : fnd;
        for (int i = stbrt + 1; i < mbx; ++i) {
            if (!Cibrbdtfr.isJbvbIdfntififrPbrt(nbmf.dibrAt(i))) {
                tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                        + " (must bf b vblid Jbvb idfntififr): " + nbmf);
            }
        }
    }

    /**
     * Cifdks tibt tif givfn string is b vblid Jbvb idfntififr.
     *
     * @pbrbm vfrsion
     *            tif dlbss vfrsion.
     * @pbrbm nbmf
     *            tif string to bf difdkfd.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkMftiodIdfntififr(int vfrsion, finbl String nbmf,
            finbl String msg) {
        if (nbmf == null || nbmf.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                    + " (must not bf null or fmpty)");
        }
        if ((vfrsion & 0xFFFF) >= Opdodfs.V1_5) {
            for (int i = 0; i < nbmf.lfngti(); ++i) {
                if (".;[/<>".indfxOf(nbmf.dibrAt(i)) != -1) {
                    tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                            + " (must bf b vblid unqublififd nbmf): " + nbmf);
                }
            }
            rfturn;
        }
        if (!Cibrbdtfr.isJbvbIdfntififrStbrt(nbmf.dibrAt(0))) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid "
                            + msg
                            + " (must bf b '<init>', '<dlinit>' or b vblid Jbvb idfntififr): "
                            + nbmf);
        }
        for (int i = 1; i < nbmf.lfngti(); ++i) {
            if (!Cibrbdtfr.isJbvbIdfntififrPbrt(nbmf.dibrAt(i))) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Invblid "
                                + msg
                                + " (must bf '<init>' or '<dlinit>' or b vblid Jbvb idfntififr): "
                                + nbmf);
            }
        }
    }

    /**
     * Cifdks tibt tif givfn string is b vblid intfrnbl dlbss nbmf.
     *
     * @pbrbm nbmf
     *            tif string to bf difdkfd.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkIntfrnblNbmf(finbl String nbmf, finbl String msg) {
        if (nbmf == null || nbmf.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                    + " (must not bf null or fmpty)");
        }
        if (nbmf.dibrAt(0) == '[') {
            difdkDfsd(nbmf, fblsf);
        } flsf {
            difdkIntfrnblNbmf(nbmf, 0, -1, msg);
        }
    }

    /**
     * Cifdks tibt tif givfn substring is b vblid intfrnbl dlbss nbmf.
     *
     * @pbrbm nbmf
     *            tif string to bf difdkfd.
     * @pbrbm stbrt
     *            indfx of tif first dibrbdtfr of tif idfntififr (indlusivf).
     * @pbrbm fnd
     *            indfx of tif lbst dibrbdtfr of tif idfntififr (fxdlusivf). -1
     *            is fquivblfnt to <tt>nbmf.lfngti()</tt> if nbmf is not
     *            <tt>null</tt>.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    stbtid void difdkIntfrnblNbmf(finbl String nbmf, finbl int stbrt,
            finbl int fnd, finbl String msg) {
        int mbx = fnd == -1 ? nbmf.lfngti() : fnd;
        try {
            int bfgin = stbrt;
            int slbsi;
            do {
                slbsi = nbmf.indfxOf('/', bfgin + 1);
                if (slbsi == -1 || slbsi > mbx) {
                    slbsi = mbx;
                }
                difdkIdfntififr(nbmf, bfgin, slbsi, null);
                bfgin = slbsi + 1;
            } wiilf (slbsi != mbx);
        } dbtdi (IllfgblArgumfntExdfption unusfd) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid "
                            + msg
                            + " (must bf b fully qublififd dlbss nbmf in intfrnbl form): "
                            + nbmf);
        }
    }

    /**
     * Cifdks tibt tif givfn string is b vblid typf dfsdriptor.
     *
     * @pbrbm dfsd
     *            tif string to bf difdkfd.
     * @pbrbm dbnBfVoid
     *            <tt>truf</tt> if <tt>V</tt> dbn bf donsidfrfd vblid.
     */
    stbtid void difdkDfsd(finbl String dfsd, finbl boolfbn dbnBfVoid) {
        int fnd = difdkDfsd(dfsd, 0, dbnBfVoid);
        if (fnd != dfsd.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: " + dfsd);
        }
    }

    /**
     * Cifdks tibt b tif givfn substring is b vblid typf dfsdriptor.
     *
     * @pbrbm dfsd
     *            tif string to bf difdkfd.
     * @pbrbm stbrt
     *            indfx of tif first dibrbdtfr of tif idfntififr (indlusivf).
     * @pbrbm dbnBfVoid
     *            <tt>truf</tt> if <tt>V</tt> dbn bf donsidfrfd vblid.
     * @rfturn tif indfx of tif lbst dibrbdtfr of tif typf dfdriptor, plus onf.
     */
    stbtid int difdkDfsd(finbl String dfsd, finbl int stbrt,
            finbl boolfbn dbnBfVoid) {
        if (dfsd == null || stbrt >= dfsd.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid typf dfsdriptor (must not bf null or fmpty)");
        }
        int indfx;
        switdi (dfsd.dibrAt(stbrt)) {
        dbsf 'V':
            if (dbnBfVoid) {
                rfturn stbrt + 1;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: "
                        + dfsd);
            }
        dbsf 'Z':
        dbsf 'C':
        dbsf 'B':
        dbsf 'S':
        dbsf 'I':
        dbsf 'F':
        dbsf 'J':
        dbsf 'D':
            rfturn stbrt + 1;
        dbsf '[':
            indfx = stbrt + 1;
            wiilf (indfx < dfsd.lfngti() && dfsd.dibrAt(indfx) == '[') {
                ++indfx;
            }
            if (indfx < dfsd.lfngti()) {
                rfturn difdkDfsd(dfsd, indfx, fblsf);
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: "
                        + dfsd);
            }
        dbsf 'L':
            indfx = dfsd.indfxOf(';', stbrt);
            if (indfx == -1 || indfx - stbrt < 2) {
                tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: "
                        + dfsd);
            }
            try {
                difdkIntfrnblNbmf(dfsd, stbrt + 1, indfx, null);
            } dbtdi (IllfgblArgumfntExdfption unusfd) {
                tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: "
                        + dfsd);
            }
            rfturn indfx + 1;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: " + dfsd);
        }
    }

    /**
     * Cifdks tibt tif givfn string is b vblid mftiod dfsdriptor.
     *
     * @pbrbm dfsd
     *            tif string to bf difdkfd.
     */
    stbtid void difdkMftiodDfsd(finbl String dfsd) {
        if (dfsd == null || dfsd.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Invblid mftiod dfsdriptor (must not bf null or fmpty)");
        }
        if (dfsd.dibrAt(0) != '(' || dfsd.lfngti() < 3) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: " + dfsd);
        }
        int stbrt = 1;
        if (dfsd.dibrAt(stbrt) != ')') {
            do {
                if (dfsd.dibrAt(stbrt) == 'V') {
                    tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: "
                            + dfsd);
                }
                stbrt = difdkDfsd(dfsd, stbrt, fblsf);
            } wiilf (stbrt < dfsd.lfngti() && dfsd.dibrAt(stbrt) != ')');
        }
        stbrt = difdkDfsd(dfsd, stbrt + 1, truf);
        if (stbrt != dfsd.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dfsdriptor: " + dfsd);
        }
    }

    /**
     * Cifdks tibt tif givfn lbbfl is not null. Tiis mftiod dbn blso difdk tibt
     * tif lbbfl ibs bffn visitfd.
     *
     * @pbrbm lbbfl
     *            tif lbbfl to bf difdkfd.
     * @pbrbm difdkVisitfd
     *            <tt>truf</tt> to difdk tibt tif lbbfl ibs bffn visitfd.
     * @pbrbm msg
     *            b mfssbgf to bf usfd in dbsf of frror.
     */
    void difdkLbbfl(finbl Lbbfl lbbfl, finbl boolfbn difdkVisitfd,
            finbl String msg) {
        if (lbbfl == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                    + " (must not bf null)");
        }
        if (difdkVisitfd && lbbfls.gft(lbbfl) == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid " + msg
                    + " (must bf visitfd first)");
        }
    }

    /**
     * Cifdks tibt tif givfn lbbfl is not b lbbfl usfd only for dfbug purposfs.
     *
     * @pbrbm lbbfl
     *            tif lbbfl to bf difdkfd.
     */
    privbtf stbtid void difdkNonDfbugLbbfl(finbl Lbbfl lbbfl) {
        Fifld f = gftLbbflStbtusFifld();
        int stbtus = 0;
        try {
            stbtus = f == null ? 0 : ((Intfgfr) f.gft(lbbfl)).intVbluf();
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw Error("Intfrnbl frror");
        }
        if ((stbtus & 0x01) != 0) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Lbbfls usfd for dfbug info dbnnot bf rfusfd for dontrol flow");
        }
    }

    /**
     * Rfturns tif Fifld objfdt dorrfsponding to tif Lbbfl.stbtus fifld.
     *
     * @rfturn tif Fifld objfdt dorrfsponding to tif Lbbfl.stbtus fifld.
     */
    privbtf stbtid Fifld gftLbbflStbtusFifld() {
        if (lbbflStbtusFifld == null) {
            lbbflStbtusFifld = gftLbbflFifld("b");
            if (lbbflStbtusFifld == null) {
                lbbflStbtusFifld = gftLbbflFifld("stbtus");
            }
        }
        rfturn lbbflStbtusFifld;
    }

    /**
     * Rfturns tif fifld of tif Lbbfl dlbss wiosf nbmf is givfn.
     *
     * @pbrbm nbmf
     *            b fifld nbmf.
     * @rfturn tif fifld of tif Lbbfl dlbss wiosf nbmf is givfn, or null.
     */
    privbtf stbtid Fifld gftLbbflFifld(finbl String nbmf) {
        try {
            Fifld f = Lbbfl.dlbss.gftDfdlbrfdFifld(nbmf);
            f.sftAddfssiblf(truf);
            rfturn f;
        } dbtdi (NoSudiFifldExdfption f) {
            rfturn null;
        }
    }
}
