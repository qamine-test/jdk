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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.trff;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Attributf;
import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;

/**
 * A nodf tibt rfprfsfnts b mftiod.
 *
 * @butior Erid Brunfton
 */
publid dlbss MftiodNodf fxtfnds MftiodVisitor {

    /**
     * Tif mftiod's bddfss flbgs (sff {@link Opdodfs}). Tiis fifld blso
     * indidbtfs if tif mftiod is syntiftid bnd/or dfprfdbtfd.
     */
    publid int bddfss;

    /**
     * Tif mftiod's nbmf.
     */
    publid String nbmf;

    /**
     * Tif mftiod's dfsdriptor (sff {@link Typf}).
     */
    publid String dfsd;

    /**
     * Tif mftiod's signbturf. Mby bf <tt>null</tt>.
     */
    publid String signbturf;

    /**
     * Tif intfrnbl nbmfs of tif mftiod's fxdfption dlbssfs (sff
     * {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). Tiis list is b list of
     * {@link String} objfdts.
     */
    publid List<String> fxdfptions;

    /**
     * Tif mftiod pbrbmftfr info (bddfss flbgs bnd nbmf)
     */
    publid List<PbrbmftfrNodf> pbrbmftfrs;

    /**
     * Tif runtimf visiblf bnnotbtions of tiis mftiod. Tiis list is b list of
     * {@link AnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AnnotbtionNodf
     * @lbbfl visiblf
     */
    publid List<AnnotbtionNodf> visiblfAnnotbtions;

    /**
     * Tif runtimf invisiblf bnnotbtions of tiis mftiod. Tiis list is b list of
     * {@link AnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AnnotbtionNodf
     * @lbbfl invisiblf
     */
    publid List<AnnotbtionNodf> invisiblfAnnotbtions;

    /**
     * Tif runtimf visiblf typf bnnotbtions of tiis mftiod. Tiis list is b list
     * of {@link TypfAnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl visiblf
     */
    publid List<TypfAnnotbtionNodf> visiblfTypfAnnotbtions;

    /**
     * Tif runtimf invisiblf typf bnnotbtions of tiis mftiod. Tiis list is b
     * list of {@link TypfAnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl invisiblf
     */
    publid List<TypfAnnotbtionNodf> invisiblfTypfAnnotbtions;

    /**
     * Tif non stbndbrd bttributfs of tiis mftiod. Tiis list is b list of
     * {@link Attributf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.Attributf
     */
    publid List<Attributf> bttrs;

    /**
     * Tif dffbult vbluf of tiis bnnotbtion intfrfbdf mftiod. Tiis fifld must bf
     * b {@link Bytf}, {@link Boolfbn}, {@link Cibrbdtfr}, {@link Siort},
     * {@link Intfgfr}, {@link Long}, {@link Flobt}, {@link Doublf},
     * {@link String} or {@link Typf}, or bn two flfmfnts String brrby (for
     * fnumfrbtion vblufs), b {@link AnnotbtionNodf}, or b {@link List} of
     * vblufs of onf of tif prfdfding typfs. Mby bf <tt>null</tt>.
     */
    publid Objfdt bnnotbtionDffbult;

    /**
     * Tif runtimf visiblf pbrbmftfr bnnotbtions of tiis mftiod. Tifsf lists brf
     * lists of {@link AnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AnnotbtionNodf
     * @lbbfl invisiblf pbrbmftfrs
     */
    publid List<AnnotbtionNodf>[] visiblfPbrbmftfrAnnotbtions;

    /**
     * Tif runtimf invisiblf pbrbmftfr bnnotbtions of tiis mftiod. Tifsf lists
     * brf lists of {@link AnnotbtionNodf} objfdts. Mby bf <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AnnotbtionNodf
     * @lbbfl visiblf pbrbmftfrs
     */
    publid List<AnnotbtionNodf>[] invisiblfPbrbmftfrAnnotbtions;

    /**
     * Tif instrudtions of tiis mftiod. Tiis list is b list of
     * {@link AbstrbdtInsnNodf} objfdts.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.AbstrbdtInsnNodf
     * @lbbfl instrudtions
     */
    publid InsnList instrudtions;

    /**
     * Tif try dbtdi blodks of tiis mftiod. Tiis list is b list of
     * {@link TryCbtdiBlodkNodf} objfdts.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TryCbtdiBlodkNodf
     */
    publid List<TryCbtdiBlodkNodf> tryCbtdiBlodks;

    /**
     * Tif mbximum stbdk sizf of tiis mftiod.
     */
    publid int mbxStbdk;

    /**
     * Tif mbximum numbfr of lodbl vbribblfs of tiis mftiod.
     */
    publid int mbxLodbls;

    /**
     * Tif lodbl vbribblfs of tiis mftiod. Tiis list is b list of
     * {@link LodblVbribblfNodf} objfdts. Mby bf <tt>null</tt>
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.LodblVbribblfNodf
     */
    publid List<LodblVbribblfNodf> lodblVbribblfs;

    /**
     * Tif visiblf lodbl vbribblf bnnotbtions of tiis mftiod. Tiis list is b
     * list of {@link LodblVbribblfAnnotbtionNodf} objfdts. Mby bf <tt>null</tt>
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.LodblVbribblfAnnotbtionNodf
     */
    publid List<LodblVbribblfAnnotbtionNodf> visiblfLodblVbribblfAnnotbtions;

    /**
     * Tif invisiblf lodbl vbribblf bnnotbtions of tiis mftiod. Tiis list is b
     * list of {@link LodblVbribblfAnnotbtionNodf} objfdts. Mby bf <tt>null</tt>
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.LodblVbribblfAnnotbtionNodf
     */
    publid List<LodblVbribblfAnnotbtionNodf> invisiblfLodblVbribblfAnnotbtions;

    /**
     * If tif bddfpt mftiod ibs bffn dbllfd on tiis objfdt.
     */
    privbtf boolfbn visitfd;

    /**
     * Construdts bn uninitiblizfd {@link MftiodNodf}. <i>Subdlbssfs must not
     * usf tiis donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #MftiodNodf(int)} vfrsion.
     *
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid MftiodNodf() {
        tiis(Opdodfs.ASM5);
        if (gftClbss() != MftiodNodf.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts bn uninitiblizfd {@link MftiodNodf}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     */
    publid MftiodNodf(finbl int bpi) {
        supfr(bpi);
        tiis.instrudtions = nfw InsnList();
    }

    /**
     * Construdts b nfw {@link MftiodNodf}. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #MftiodNodf(int, int, String, String, String, String[])} vfrsion.
     *
     * @pbrbm bddfss
     *            tif mftiod's bddfss flbgs (sff {@link Opdodfs}). Tiis
     *            pbrbmftfr blso indidbtfs if tif mftiod is syntiftid bnd/or
     *            dfprfdbtfd.
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf}).
     * @pbrbm signbturf
     *            tif mftiod's signbturf. Mby bf <tt>null</tt>.
     * @pbrbm fxdfptions
     *            tif intfrnbl nbmfs of tif mftiod's fxdfption dlbssfs (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). Mby bf
     *            <tt>null</tt>.
     * @tirows IllfgblStbtfExdfption
     *             If b subdlbss dblls tiis donstrudtor.
     */
    publid MftiodNodf(finbl int bddfss, finbl String nbmf, finbl String dfsd,
            finbl String signbturf, finbl String[] fxdfptions) {
        tiis(Opdodfs.ASM5, bddfss, nbmf, dfsd, signbturf, fxdfptions);
        if (gftClbss() != MftiodNodf.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Construdts b nfw {@link MftiodNodf}.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm bddfss
     *            tif mftiod's bddfss flbgs (sff {@link Opdodfs}). Tiis
     *            pbrbmftfr blso indidbtfs if tif mftiod is syntiftid bnd/or
     *            dfprfdbtfd.
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor (sff {@link Typf}).
     * @pbrbm signbturf
     *            tif mftiod's signbturf. Mby bf <tt>null</tt>.
     * @pbrbm fxdfptions
     *            tif intfrnbl nbmfs of tif mftiod's fxdfption dlbssfs (sff
     *            {@link Typf#gftIntfrnblNbmf() gftIntfrnblNbmf}). Mby bf
     *            <tt>null</tt>.
     */
    publid MftiodNodf(finbl int bpi, finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl String[] fxdfptions) {
        supfr(bpi);
        tiis.bddfss = bddfss;
        tiis.nbmf = nbmf;
        tiis.dfsd = dfsd;
        tiis.signbturf = signbturf;
        tiis.fxdfptions = nfw ArrbyList<String>(fxdfptions == null ? 0
                : fxdfptions.lfngti);
        boolfbn isAbstrbdt = (bddfss & Opdodfs.ACC_ABSTRACT) != 0;
        if (!isAbstrbdt) {
            tiis.lodblVbribblfs = nfw ArrbyList<LodblVbribblfNodf>(5);
        }
        tiis.tryCbtdiBlodks = nfw ArrbyList<TryCbtdiBlodkNodf>();
        if (fxdfptions != null) {
            tiis.fxdfptions.bddAll(Arrbys.bsList(fxdfptions));
        }
        tiis.instrudtions = nfw InsnList();
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif MftiodVisitor bbstrbdt dlbss
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visitPbrbmftfr(String nbmf, int bddfss) {
        if (pbrbmftfrs == null) {
            pbrbmftfrs = nfw ArrbyList<PbrbmftfrNodf>(5);
        }
        pbrbmftfrs.bdd(nfw PbrbmftfrNodf(nbmf, bddfss));
    }

    @Ovfrridf
    @SupprfssWbrnings("sfribl")
    publid AnnotbtionVisitor visitAnnotbtionDffbult() {
        rfturn nfw AnnotbtionNodf(nfw ArrbyList<Objfdt>(0) {
            @Ovfrridf
            publid boolfbn bdd(finbl Objfdt o) {
                bnnotbtionDffbult = o;
                rfturn supfr.bdd(o);
            }
        });
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        AnnotbtionNodf bn = nfw AnnotbtionNodf(dfsd);
        if (visiblf) {
            if (visiblfAnnotbtions == null) {
                visiblfAnnotbtions = nfw ArrbyList<AnnotbtionNodf>(1);
            }
            visiblfAnnotbtions.bdd(bn);
        } flsf {
            if (invisiblfAnnotbtions == null) {
                invisiblfAnnotbtions = nfw ArrbyList<AnnotbtionNodf>(1);
            }
            invisiblfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        TypfAnnotbtionNodf bn = nfw TypfAnnotbtionNodf(typfRff, typfPbti, dfsd);
        if (visiblf) {
            if (visiblfTypfAnnotbtions == null) {
                visiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(1);
            }
            visiblfTypfAnnotbtions.bdd(bn);
        } flsf {
            if (invisiblfTypfAnnotbtions == null) {
                invisiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(1);
            }
            invisiblfTypfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid AnnotbtionVisitor visitPbrbmftfrAnnotbtion(finbl int pbrbmftfr,
            finbl String dfsd, finbl boolfbn visiblf) {
        AnnotbtionNodf bn = nfw AnnotbtionNodf(dfsd);
        if (visiblf) {
            if (visiblfPbrbmftfrAnnotbtions == null) {
                int pbrbms = Typf.gftArgumfntTypfs(tiis.dfsd).lfngti;
                visiblfPbrbmftfrAnnotbtions = (List<AnnotbtionNodf>[]) nfw List<?>[pbrbms];
            }
            if (visiblfPbrbmftfrAnnotbtions[pbrbmftfr] == null) {
                visiblfPbrbmftfrAnnotbtions[pbrbmftfr] = nfw ArrbyList<AnnotbtionNodf>(
                        1);
            }
            visiblfPbrbmftfrAnnotbtions[pbrbmftfr].bdd(bn);
        } flsf {
            if (invisiblfPbrbmftfrAnnotbtions == null) {
                int pbrbms = Typf.gftArgumfntTypfs(tiis.dfsd).lfngti;
                invisiblfPbrbmftfrAnnotbtions = (List<AnnotbtionNodf>[]) nfw List<?>[pbrbms];
            }
            if (invisiblfPbrbmftfrAnnotbtions[pbrbmftfr] == null) {
                invisiblfPbrbmftfrAnnotbtions[pbrbmftfr] = nfw ArrbyList<AnnotbtionNodf>(
                        1);
            }
            invisiblfPbrbmftfrAnnotbtions[pbrbmftfr].bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid void visitAttributf(finbl Attributf bttr) {
        if (bttrs == null) {
            bttrs = nfw ArrbyList<Attributf>(1);
        }
        bttrs.bdd(bttr);
    }

    @Ovfrridf
    publid void visitCodf() {
    }

    @Ovfrridf
    publid void visitFrbmf(finbl int typf, finbl int nLodbl,
            finbl Objfdt[] lodbl, finbl int nStbdk, finbl Objfdt[] stbdk) {
        instrudtions.bdd(nfw FrbmfNodf(typf, nLodbl, lodbl == null ? null
                : gftLbbflNodfs(lodbl), nStbdk, stbdk == null ? null
                : gftLbbflNodfs(stbdk)));
    }

    @Ovfrridf
    publid void visitInsn(finbl int opdodf) {
        instrudtions.bdd(nfw InsnNodf(opdodf));
    }

    @Ovfrridf
    publid void visitIntInsn(finbl int opdodf, finbl int opfrbnd) {
        instrudtions.bdd(nfw IntInsnNodf(opdodf, opfrbnd));
    }

    @Ovfrridf
    publid void visitVbrInsn(finbl int opdodf, finbl int vbr) {
        instrudtions.bdd(nfw VbrInsnNodf(opdodf, vbr));
    }

    @Ovfrridf
    publid void visitTypfInsn(finbl int opdodf, finbl String typf) {
        instrudtions.bdd(nfw TypfInsnNodf(opdodf, typf));
    }

    @Ovfrridf
    publid void visitFifldInsn(finbl int opdodf, finbl String ownfr,
            finbl String nbmf, finbl String dfsd) {
        instrudtions.bdd(nfw FifldInsnNodf(opdodf, ownfr, nbmf, dfsd));
    }

    @Dfprfdbtfd
    @Ovfrridf
    publid void visitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd) {
        if (bpi >= Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd);
            rfturn;
        }
        instrudtions.bdd(nfw MftiodInsnNodf(opdodf, ownfr, nbmf, dfsd));
    }

    @Ovfrridf
    publid void visitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd, boolfbn itf) {
        if (bpi < Opdodfs.ASM5) {
            supfr.visitMftiodInsn(opdodf, ownfr, nbmf, dfsd, itf);
            rfturn;
        }
        instrudtions.bdd(nfw MftiodInsnNodf(opdodf, ownfr, nbmf, dfsd, itf));
    }

    @Ovfrridf
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        instrudtions.bdd(nfw InvokfDynbmidInsnNodf(nbmf, dfsd, bsm, bsmArgs));
    }

    @Ovfrridf
    publid void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbbfl) {
        instrudtions.bdd(nfw JumpInsnNodf(opdodf, gftLbbflNodf(lbbfl)));
    }

    @Ovfrridf
    publid void visitLbbfl(finbl Lbbfl lbbfl) {
        instrudtions.bdd(gftLbbflNodf(lbbfl));
    }

    @Ovfrridf
    publid void visitLddInsn(finbl Objfdt dst) {
        instrudtions.bdd(nfw LddInsnNodf(dst));
    }

    @Ovfrridf
    publid void visitIindInsn(finbl int vbr, finbl int indrfmfnt) {
        instrudtions.bdd(nfw IindInsnNodf(vbr, indrfmfnt));
    }

    @Ovfrridf
    publid void visitTbblfSwitdiInsn(finbl int min, finbl int mbx,
            finbl Lbbfl dflt, finbl Lbbfl... lbbfls) {
        instrudtions.bdd(nfw TbblfSwitdiInsnNodf(min, mbx, gftLbbflNodf(dflt),
                gftLbbflNodfs(lbbfls)));
    }

    @Ovfrridf
    publid void visitLookupSwitdiInsn(finbl Lbbfl dflt, finbl int[] kfys,
            finbl Lbbfl[] lbbfls) {
        instrudtions.bdd(nfw LookupSwitdiInsnNodf(gftLbbflNodf(dflt), kfys,
                gftLbbflNodfs(lbbfls)));
    }

    @Ovfrridf
    publid void visitMultiANfwArrbyInsn(finbl String dfsd, finbl int dims) {
        instrudtions.bdd(nfw MultiANfwArrbyInsnNodf(dfsd, dims));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitInsnAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        // Finds tif lbst rfbl instrudtion, i.f. tif instrudtion tbrgftfd by
        // tiis bnnotbtion.
        AbstrbdtInsnNodf insn = instrudtions.gftLbst();
        wiilf (insn.gftOpdodf() == -1) {
            insn = insn.gftPrfvious();
        }
        // Adds tif bnnotbtion to tiis instrudtion.
        TypfAnnotbtionNodf bn = nfw TypfAnnotbtionNodf(typfRff, typfPbti, dfsd);
        if (visiblf) {
            if (insn.visiblfTypfAnnotbtions == null) {
                insn.visiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(
                        1);
            }
            insn.visiblfTypfAnnotbtions.bdd(bn);
        } flsf {
            if (insn.invisiblfTypfAnnotbtions == null) {
                insn.invisiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(
                        1);
            }
            insn.invisiblfTypfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid void visitTryCbtdiBlodk(finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl Lbbfl ibndlfr, finbl String typf) {
        tryCbtdiBlodks.bdd(nfw TryCbtdiBlodkNodf(gftLbbflNodf(stbrt),
                gftLbbflNodf(fnd), gftLbbflNodf(ibndlfr), typf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTryCbtdiAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        TryCbtdiBlodkNodf tdb = tryCbtdiBlodks.gft((typfRff & 0x00FFFF00) >> 8);
        TypfAnnotbtionNodf bn = nfw TypfAnnotbtionNodf(typfRff, typfPbti, dfsd);
        if (visiblf) {
            if (tdb.visiblfTypfAnnotbtions == null) {
                tdb.visiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(
                        1);
            }
            tdb.visiblfTypfAnnotbtions.bdd(bn);
        } flsf {
            if (tdb.invisiblfTypfAnnotbtions == null) {
                tdb.invisiblfTypfAnnotbtions = nfw ArrbyList<TypfAnnotbtionNodf>(
                        1);
            }
            tdb.invisiblfTypfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid void visitLodblVbribblf(finbl String nbmf, finbl String dfsd,
            finbl String signbturf, finbl Lbbfl stbrt, finbl Lbbfl fnd,
            finbl int indfx) {
        lodblVbribblfs.bdd(nfw LodblVbribblfNodf(nbmf, dfsd, signbturf,
                gftLbbflNodf(stbrt), gftLbbflNodf(fnd), indfx));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitLodblVbribblfAnnotbtion(int typfRff,
            TypfPbti typfPbti, Lbbfl[] stbrt, Lbbfl[] fnd, int[] indfx,
            String dfsd, boolfbn visiblf) {
        LodblVbribblfAnnotbtionNodf bn = nfw LodblVbribblfAnnotbtionNodf(
                typfRff, typfPbti, gftLbbflNodfs(stbrt), gftLbbflNodfs(fnd),
                indfx, dfsd);
        if (visiblf) {
            if (visiblfLodblVbribblfAnnotbtions == null) {
                visiblfLodblVbribblfAnnotbtions = nfw ArrbyList<LodblVbribblfAnnotbtionNodf>(
                        1);
            }
            visiblfLodblVbribblfAnnotbtions.bdd(bn);
        } flsf {
            if (invisiblfLodblVbribblfAnnotbtions == null) {
                invisiblfLodblVbribblfAnnotbtions = nfw ArrbyList<LodblVbribblfAnnotbtionNodf>(
                        1);
            }
            invisiblfLodblVbribblfAnnotbtions.bdd(bn);
        }
        rfturn bn;
    }

    @Ovfrridf
    publid void visitLinfNumbfr(finbl int linf, finbl Lbbfl stbrt) {
        instrudtions.bdd(nfw LinfNumbfrNodf(linf, gftLbbflNodf(stbrt)));
    }

    @Ovfrridf
    publid void visitMbxs(finbl int mbxStbdk, finbl int mbxLodbls) {
        tiis.mbxStbdk = mbxStbdk;
        tiis.mbxLodbls = mbxLodbls;
    }

    @Ovfrridf
    publid void visitEnd() {
    }

    /**
     * Rfturns tif LbbflNodf dorrfsponding to tif givfn Lbbfl. Crfbtfs b nfw
     * LbbflNodf if nfdfssbry. Tif dffbult implfmfntbtion of tiis mftiod usfs
     * tif {@link Lbbfl#info} fifld to storf bssodibtions bftwffn lbbfls bnd
     * lbbfl nodfs.
     *
     * @pbrbm l
     *            b Lbbfl.
     * @rfturn tif LbbflNodf dorrfsponding to l.
     */
    protfdtfd LbbflNodf gftLbbflNodf(finbl Lbbfl l) {
        if (!(l.info instbndfof LbbflNodf)) {
            l.info = nfw LbbflNodf();
        }
        rfturn (LbbflNodf) l.info;
    }

    privbtf LbbflNodf[] gftLbbflNodfs(finbl Lbbfl[] l) {
        LbbflNodf[] nodfs = nfw LbbflNodf[l.lfngti];
        for (int i = 0; i < l.lfngti; ++i) {
            nodfs[i] = gftLbbflNodf(l[i]);
        }
        rfturn nodfs;
    }

    privbtf Objfdt[] gftLbbflNodfs(finbl Objfdt[] objs) {
        Objfdt[] nodfs = nfw Objfdt[objs.lfngti];
        for (int i = 0; i < objs.lfngti; ++i) {
            Objfdt o = objs[i];
            if (o instbndfof Lbbfl) {
                o = gftLbbflNodf((Lbbfl) o);
            }
            nodfs[i] = o;
        }
        rfturn nodfs;
    }

    // ------------------------------------------------------------------------
    // Addfpt mftiod
    // ------------------------------------------------------------------------

    /**
     * Cifdks tibt tiis mftiod nodf is dompbtiblf witi tif givfn ASM API
     * vfrsion. Tiis mftiods difdks tibt tiis nodf, bnd bll its nodfs
     * rfdursivfly, do not dontbin flfmfnts tibt wfrf introdudfd in morf rfdfnt
     * vfrsions of tif ASM API tibn tif givfn vfrsion.
     *
     * @pbrbm bpi
     *            bn ASM API vfrsion. Must bf onf of {@link Opdodfs#ASM4} or
     *            {@link Opdodfs#ASM5}.
     */
    publid void difdk(finbl int bpi) {
        if (bpi == Opdodfs.ASM4) {
            if (visiblfTypfAnnotbtions != null
                    && visiblfTypfAnnotbtions.sizf() > 0) {
                tirow nfw RuntimfExdfption();
            }
            if (invisiblfTypfAnnotbtions != null
                    && invisiblfTypfAnnotbtions.sizf() > 0) {
                tirow nfw RuntimfExdfption();
            }
            int n = tryCbtdiBlodks == null ? 0 : tryCbtdiBlodks.sizf();
            for (int i = 0; i < n; ++i) {
                TryCbtdiBlodkNodf tdb = tryCbtdiBlodks.gft(i);
                if (tdb.visiblfTypfAnnotbtions != null
                        && tdb.visiblfTypfAnnotbtions.sizf() > 0) {
                    tirow nfw RuntimfExdfption();
                }
                if (tdb.invisiblfTypfAnnotbtions != null
                        && tdb.invisiblfTypfAnnotbtions.sizf() > 0) {
                    tirow nfw RuntimfExdfption();
                }
            }
            for (int i = 0; i < instrudtions.sizf(); ++i) {
                AbstrbdtInsnNodf insn = instrudtions.gft(i);
                if (insn.visiblfTypfAnnotbtions != null
                        && insn.visiblfTypfAnnotbtions.sizf() > 0) {
                    tirow nfw RuntimfExdfption();
                }
                if (insn.invisiblfTypfAnnotbtions != null
                        && insn.invisiblfTypfAnnotbtions.sizf() > 0) {
                    tirow nfw RuntimfExdfption();
                }
                if (insn instbndfof MftiodInsnNodf) {
                    boolfbn itf = ((MftiodInsnNodf) insn).itf;
                    if (itf != (insn.opdodf == Opdodfs.INVOKEINTERFACE)) {
                        tirow nfw RuntimfExdfption();
                    }
                }
            }
            if (visiblfLodblVbribblfAnnotbtions != null
                    && visiblfLodblVbribblfAnnotbtions.sizf() > 0) {
                tirow nfw RuntimfExdfption();
            }
            if (invisiblfLodblVbribblfAnnotbtions != null
                    && invisiblfLodblVbribblfAnnotbtions.sizf() > 0) {
                tirow nfw RuntimfExdfption();
            }
        }
    }

    /**
     * Mbkfs tif givfn dlbss visitor visit tiis mftiod.
     *
     * @pbrbm dv
     *            b dlbss visitor.
     */
    publid void bddfpt(finbl ClbssVisitor dv) {
        String[] fxdfptions = nfw String[tiis.fxdfptions.sizf()];
        tiis.fxdfptions.toArrby(fxdfptions);
        MftiodVisitor mv = dv.visitMftiod(bddfss, nbmf, dfsd, signbturf,
                fxdfptions);
        if (mv != null) {
            bddfpt(mv);
        }
    }

    /**
     * Mbkfs tif givfn mftiod visitor visit tiis mftiod.
     *
     * @pbrbm mv
     *            b mftiod visitor.
     */
    publid void bddfpt(finbl MftiodVisitor mv) {
        // visits tif mftiod pbrbmftfrs
        int i, j, n;
        n = pbrbmftfrs == null ? 0 : pbrbmftfrs.sizf();
        for (i = 0; i < n; i++) {
            PbrbmftfrNodf pbrbmftfr = pbrbmftfrs.gft(i);
            mv.visitPbrbmftfr(pbrbmftfr.nbmf, pbrbmftfr.bddfss);
        }
        // visits tif mftiod bttributfs
        if (bnnotbtionDffbult != null) {
            AnnotbtionVisitor bv = mv.visitAnnotbtionDffbult();
            AnnotbtionNodf.bddfpt(bv, null, bnnotbtionDffbult);
            if (bv != null) {
                bv.visitEnd();
            }
        }
        n = visiblfAnnotbtions == null ? 0 : visiblfAnnotbtions.sizf();
        for (i = 0; i < n; ++i) {
            AnnotbtionNodf bn = visiblfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitAnnotbtion(bn.dfsd, truf));
        }
        n = invisiblfAnnotbtions == null ? 0 : invisiblfAnnotbtions.sizf();
        for (i = 0; i < n; ++i) {
            AnnotbtionNodf bn = invisiblfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitAnnotbtion(bn.dfsd, fblsf));
        }
        n = visiblfTypfAnnotbtions == null ? 0 : visiblfTypfAnnotbtions.sizf();
        for (i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = visiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitTypfAnnotbtion(bn.typfRff, bn.typfPbti, bn.dfsd,
                    truf));
        }
        n = invisiblfTypfAnnotbtions == null ? 0 : invisiblfTypfAnnotbtions
                .sizf();
        for (i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = invisiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitTypfAnnotbtion(bn.typfRff, bn.typfPbti, bn.dfsd,
                    fblsf));
        }
        n = visiblfPbrbmftfrAnnotbtions == null ? 0
                : visiblfPbrbmftfrAnnotbtions.lfngti;
        for (i = 0; i < n; ++i) {
            List<?> l = visiblfPbrbmftfrAnnotbtions[i];
            if (l == null) {
                dontinuf;
            }
            for (j = 0; j < l.sizf(); ++j) {
                AnnotbtionNodf bn = (AnnotbtionNodf) l.gft(j);
                bn.bddfpt(mv.visitPbrbmftfrAnnotbtion(i, bn.dfsd, truf));
            }
        }
        n = invisiblfPbrbmftfrAnnotbtions == null ? 0
                : invisiblfPbrbmftfrAnnotbtions.lfngti;
        for (i = 0; i < n; ++i) {
            List<?> l = invisiblfPbrbmftfrAnnotbtions[i];
            if (l == null) {
                dontinuf;
            }
            for (j = 0; j < l.sizf(); ++j) {
                AnnotbtionNodf bn = (AnnotbtionNodf) l.gft(j);
                bn.bddfpt(mv.visitPbrbmftfrAnnotbtion(i, bn.dfsd, fblsf));
            }
        }
        if (visitfd) {
            instrudtions.rfsftLbbfls();
        }
        n = bttrs == null ? 0 : bttrs.sizf();
        for (i = 0; i < n; ++i) {
            mv.visitAttributf(bttrs.gft(i));
        }
        // visits tif mftiod's dodf
        if (instrudtions.sizf() > 0) {
            mv.visitCodf();
            // visits try dbtdi blodks
            n = tryCbtdiBlodks == null ? 0 : tryCbtdiBlodks.sizf();
            for (i = 0; i < n; ++i) {
                tryCbtdiBlodks.gft(i).updbtfIndfx(i);
                tryCbtdiBlodks.gft(i).bddfpt(mv);
            }
            // visits instrudtions
            instrudtions.bddfpt(mv);
            // visits lodbl vbribblfs
            n = lodblVbribblfs == null ? 0 : lodblVbribblfs.sizf();
            for (i = 0; i < n; ++i) {
                lodblVbribblfs.gft(i).bddfpt(mv);
            }
            // visits lodbl vbribblf bnnotbtions
            n = visiblfLodblVbribblfAnnotbtions == null ? 0
                    : visiblfLodblVbribblfAnnotbtions.sizf();
            for (i = 0; i < n; ++i) {
                visiblfLodblVbribblfAnnotbtions.gft(i).bddfpt(mv, truf);
            }
            n = invisiblfLodblVbribblfAnnotbtions == null ? 0
                    : invisiblfLodblVbribblfAnnotbtions.sizf();
            for (i = 0; i < n; ++i) {
                invisiblfLodblVbribblfAnnotbtions.gft(i).bddfpt(mv, fblsf);
            }
            // visits mbxs
            mv.visitMbxs(mbxStbdk, mbxLodbls);
            visitfd = truf;
        }
        mv.visitEnd();
    }
}
