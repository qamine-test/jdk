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

import jbvb.util.AbstrbdtMbp;
import jbvb.util.ArrbyList;
import jbvb.util.BitSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdList;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.AbstrbdtInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.InsnList;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.InsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.JumpInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.LbbflNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.LodblVbribblfNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.LookupSwitdiInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.MftiodNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.TbblfSwitdiInsnNodf;
import jdk.intfrnbl.org.objfdtwfb.bsm.trff.TryCbtdiBlodkNodf;

/**
 * A {@link jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor} tibt rfmovfs JSR instrudtions bnd
 * inlinfs tif rfffrfndfd subroutinfs.
 *
 * <b>Explbnbtion of iow it works</b> TODO
 *
 * @butior Niko Mbtsbkis
 */
publid dlbss JSRInlinfrAdbptfr fxtfnds MftiodNodf implfmfnts Opdodfs {

    privbtf stbtid finbl boolfbn LOGGING = fblsf;

    /**
     * For fbdi lbbfl tibt is jumpfd to by b JSR, wf drfbtf b BitSft instbndf.
     */
    privbtf finbl Mbp<LbbflNodf, BitSft> subroutinfHfbds = nfw HbsiMbp<LbbflNodf, BitSft>();

    /**
     * Tiis subroutinf instbndf dfnotfs tif linf of fxfdution tibt is not
     * dontbinfd witiin bny subroutinf; i.f., tif "subroutinf" tibt is fxfduting
     * wifn b mftiod first bfgins.
     */
    privbtf finbl BitSft mbinSubroutinf = nfw BitSft();

    /**
     * Tiis BitSft dontbins tif indfx of fvfry instrudtion tibt bflongs to morf
     * tibn onf subroutinf. Tiis siould not ibppfn oftfn.
     */
    finbl BitSft dublCitizfns = nfw BitSft();

    /**
     * Crfbtfs b nfw JSRInlinfr. <i>Subdlbssfs must not usf tiis
     * donstrudtor</i>. Instfbd, tify must usf tif
     * {@link #JSRInlinfrAdbptfr(int, MftiodVisitor, int, String, String, String, String[])}
     * vfrsion.
     *
     * @pbrbm mv
     *            tif <dodf>MftiodVisitor</dodf> to sfnd tif rfsulting inlinfd
     *            mftiod dodf to (usf <dodf>null</dodf> for nonf).
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
    publid JSRInlinfrAdbptfr(finbl MftiodVisitor mv, finbl int bddfss,
            finbl String nbmf, finbl String dfsd, finbl String signbturf,
            finbl String[] fxdfptions) {
        tiis(Opdodfs.ASM5, mv, bddfss, nbmf, dfsd, signbturf, fxdfptions);
        if (gftClbss() != JSRInlinfrAdbptfr.dlbss) {
            tirow nfw IllfgblStbtfExdfption();
        }
    }

    /**
     * Crfbtfs b nfw JSRInlinfr.
     *
     * @pbrbm bpi
     *            tif ASM API vfrsion implfmfntfd by tiis visitor. Must bf onf
     *            of {@link Opdodfs#ASM4} or {@link Opdodfs#ASM5}.
     * @pbrbm mv
     *            tif <dodf>MftiodVisitor</dodf> to sfnd tif rfsulting inlinfd
     *            mftiod dodf to (usf <dodf>null</dodf> for nonf).
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
    protfdtfd JSRInlinfrAdbptfr(finbl int bpi, finbl MftiodVisitor mv,
            finbl int bddfss, finbl String nbmf, finbl String dfsd,
            finbl String signbturf, finbl String[] fxdfptions) {
        supfr(bpi, bddfss, nbmf, dfsd, signbturf, fxdfptions);
        tiis.mv = mv;
    }

    /**
     * Dftfdts b JSR instrudtion bnd sfts b flbg to indidbtf wf will nffd to do
     * inlining.
     */
    @Ovfrridf
    publid void visitJumpInsn(finbl int opdodf, finbl Lbbfl lbl) {
        supfr.visitJumpInsn(opdodf, lbl);
        LbbflNodf ln = ((JumpInsnNodf) instrudtions.gftLbst()).lbbfl;
        if (opdodf == JSR && !subroutinfHfbds.dontbinsKfy(ln)) {
            subroutinfHfbds.put(ln, nfw BitSft());
        }
    }

    /**
     * If bny JSRs wfrf sffn, triggfrs tif inlining prodfss. Otifrwisf, forwbrds
     * tif bytf dodfs untoudifd.
     */
    @Ovfrridf
    publid void visitEnd() {
        if (!subroutinfHfbds.isEmpty()) {
            mbrkSubroutinfs();
            if (LOGGING) {
                log(mbinSubroutinf.toString());
                Itfrbtor<BitSft> it = subroutinfHfbds.vblufs().itfrbtor();
                wiilf (it.ibsNfxt()) {
                    BitSft sub = it.nfxt();
                    log(sub.toString());
                }
            }
            fmitCodf();
        }

        // Forwbrd tif trbnslbtf opdodfs on if bppropribtf:
        if (mv != null) {
            bddfpt(mv);
        }
    }

    /**
     * Wblks tif mftiod bnd dftfrminfs wiidi intfrnbl subroutinf(s), if bny,
     * fbdi instrudtion is b mftiod of.
     */
    privbtf void mbrkSubroutinfs() {
        BitSft bnyvisitfd = nfw BitSft();

        // First wblk tif mbin subroutinf bnd find bll tiosf instrudtions wiidi
        // dbn bf rfbdifd witiout invoking bny JSR bt bll
        mbrkSubroutinfWblk(mbinSubroutinf, 0, bnyvisitfd);

        // Go tirougi tif ifbd of fbdi subroutinf bnd find bny nodfs rfbdibblf
        // to tibt subroutinf witiout following bny JSR links.
        for (Itfrbtor<Mbp.Entry<LbbflNodf, BitSft>> it = subroutinfHfbds
                .fntrySft().itfrbtor(); it.ibsNfxt();) {
            Mbp.Entry<LbbflNodf, BitSft> fntry = it.nfxt();
            LbbflNodf lbb = fntry.gftKfy();
            BitSft sub = fntry.gftVbluf();
            int indfx = instrudtions.indfxOf(lbb);
            mbrkSubroutinfWblk(sub, indfx, bnyvisitfd);
        }
    }

    /**
     * Pfrforms b dfpti first sfbrdi wblking tif normbl bytf dodf pbti stbrting
     * bt <dodf>indfx</dodf>, bnd bdding fbdi instrudtion fndountfrfd into tif
     * subroutinf <dodf>sub</dodf>. Aftfr tiis wblk is domplftf, itfrbtfs ovfr
     * tif fxdfption ibndlfrs to fnsurf tibt wf blso indludf tiosf bytf dodfs
     * wiidi brf rfbdibblf tirougi bn fxdfption tibt mby bf tirown during tif
     * fxfdution of tif subroutinf. Invokfd from <dodf>mbrkSubroutinfs()</dodf>.
     *
     * @pbrbm sub
     *            tif subroutinf wiosf instrudtions must bf domputfd.
     * @pbrbm indfx
     *            bn instrudtion of tiis subroutinf.
     * @pbrbm bnyvisitfd
     *            indfxfs of tif blrfbdy visitfd instrudtions, i.f. mbrkfd bs
     *            pbrt of tiis subroutinf or bny prfviously domputfd subroutinf.
     */
    privbtf void mbrkSubroutinfWblk(finbl BitSft sub, finbl int indfx,
            finbl BitSft bnyvisitfd) {
        if (LOGGING) {
            log("mbrkSubroutinfWblk: sub=" + sub + " indfx=" + indfx);
        }

        // First find tiosf instrudtions rfbdibblf vib normbl fxfdution
        mbrkSubroutinfWblkDFS(sub, indfx, bnyvisitfd);

        // Now, mbkf surf wf blso indludf bny bpplidbblf fxdfption ibndlfrs
        boolfbn loop = truf;
        wiilf (loop) {
            loop = fblsf;
            for (Itfrbtor<TryCbtdiBlodkNodf> it = tryCbtdiBlodks.itfrbtor(); it
                    .ibsNfxt();) {
                TryCbtdiBlodkNodf trydbtdi = it.nfxt();

                if (LOGGING) {
                    // TODO usf of dffbult toString().
                    log("Sdbnning try/dbtdi " + trydbtdi);
                }

                // If tif ibndlfr ibs blrfbdy bffn prodfssfd, skip it.
                int ibndlfrindfx = instrudtions.indfxOf(trydbtdi.ibndlfr);
                if (sub.gft(ibndlfrindfx)) {
                    dontinuf;
                }

                int stbrtindfx = instrudtions.indfxOf(trydbtdi.stbrt);
                int fndindfx = instrudtions.indfxOf(trydbtdi.fnd);
                int nfxtbit = sub.nfxtSftBit(stbrtindfx);
                if (nfxtbit != -1 && nfxtbit < fndindfx) {
                    if (LOGGING) {
                        log("Adding fxdfption ibndlfr: " + stbrtindfx + '-'
                                + fndindfx + " duf to " + nfxtbit + " ibndlfr "
                                + ibndlfrindfx);
                    }
                    mbrkSubroutinfWblkDFS(sub, ibndlfrindfx, bnyvisitfd);
                    loop = truf;
                }
            }
        }
    }

    /**
     * Pfrforms b simplf DFS of tif instrudtions, bssigning fbdi to tif
     * subroutinf <dodf>sub</dodf>. Stbrts from <dodf>indfx</dodf>. Invokfd only
     * by <dodf>mbrkSubroutinfWblk()</dodf>.
     *
     * @pbrbm sub
     *            tif subroutinf wiosf instrudtions must bf domputfd.
     * @pbrbm indfx
     *            bn instrudtion of tiis subroutinf.
     * @pbrbm bnyvisitfd
     *            indfxfs of tif blrfbdy visitfd instrudtions, i.f. mbrkfd bs
     *            pbrt of tiis subroutinf or bny prfviously domputfd subroutinf.
     */
    privbtf void mbrkSubroutinfWblkDFS(finbl BitSft sub, int indfx,
            finbl BitSft bnyvisitfd) {
        wiilf (truf) {
            AbstrbdtInsnNodf nodf = instrudtions.gft(indfx);

            // don't visit b nodf twidf
            if (sub.gft(indfx)) {
                rfturn;
            }
            sub.sft(indfx);

            // difdk for tiosf nodfs blrfbdy visitfd by bnotifr subroutinf
            if (bnyvisitfd.gft(indfx)) {
                dublCitizfns.sft(indfx);
                if (LOGGING) {
                    log("Instrudtion #" + indfx + " is dubl ditizfn.");
                }
            }
            bnyvisitfd.sft(indfx);

            if (nodf.gftTypf() == AbstrbdtInsnNodf.JUMP_INSN
                    && nodf.gftOpdodf() != JSR) {
                // wf do not follow rfdursivfly dbllfd subroutinfs ifrf; but bny
                // otifr sort of brbndi wf do follow
                JumpInsnNodf jnodf = (JumpInsnNodf) nodf;
                int dfstidx = instrudtions.indfxOf(jnodf.lbbfl);
                mbrkSubroutinfWblkDFS(sub, dfstidx, bnyvisitfd);
            }
            if (nodf.gftTypf() == AbstrbdtInsnNodf.TABLESWITCH_INSN) {
                TbblfSwitdiInsnNodf tsnodf = (TbblfSwitdiInsnNodf) nodf;
                int dfstidx = instrudtions.indfxOf(tsnodf.dflt);
                mbrkSubroutinfWblkDFS(sub, dfstidx, bnyvisitfd);
                for (int i = tsnodf.lbbfls.sizf() - 1; i >= 0; --i) {
                    LbbflNodf l = tsnodf.lbbfls.gft(i);
                    dfstidx = instrudtions.indfxOf(l);
                    mbrkSubroutinfWblkDFS(sub, dfstidx, bnyvisitfd);
                }
            }
            if (nodf.gftTypf() == AbstrbdtInsnNodf.LOOKUPSWITCH_INSN) {
                LookupSwitdiInsnNodf lsnodf = (LookupSwitdiInsnNodf) nodf;
                int dfstidx = instrudtions.indfxOf(lsnodf.dflt);
                mbrkSubroutinfWblkDFS(sub, dfstidx, bnyvisitfd);
                for (int i = lsnodf.lbbfls.sizf() - 1; i >= 0; --i) {
                    LbbflNodf l = lsnodf.lbbfls.gft(i);
                    dfstidx = instrudtions.indfxOf(l);
                    mbrkSubroutinfWblkDFS(sub, dfstidx, bnyvisitfd);
                }
            }

            // difdk to sff if tiis opdodf fblls tirougi to tif nfxt instrudtion
            // or not; if not, rfturn.
            switdi (instrudtions.gft(indfx).gftOpdodf()) {
            dbsf GOTO:
            dbsf RET:
            dbsf TABLESWITCH:
            dbsf LOOKUPSWITCH:
            dbsf IRETURN:
            dbsf LRETURN:
            dbsf FRETURN:
            dbsf DRETURN:
            dbsf ARETURN:
            dbsf RETURN:
            dbsf ATHROW:
                /*
                 * notf: tiis fitifr rfturns from tiis subroutinf, or b pbrfnt
                 * subroutinf wiidi invokfd it
                 */
                rfturn;
            }

            // Usf tbil rfdursion ifrf in tif form of bn outfr wiilf loop to
            // bvoid our stbdk growing nffdlfssly:
            indfx++;

            // Wf impliditly bssumfd bbovf tibt fxfdution dbn blwbys fbll
            // tirougi to tif nfxt instrudtion bftfr b JSR. But b subroutinf mby
            // nfvfr rfturn, in wiidi dbsf tif dodf bftfr tif JSR is unrfbdibblf
            // bnd dbn bf bnytiing. In pbrtidulbr, it dbn sffm to fbll off tif
            // fnd of tif mftiod, so wf must ibndlf tiis dbsf ifrf (wf dould
            // instfbd dftfdt wiftifr fxfdution dbn rfturn or not from b JSR,
            // but tiis is morf domplidbtfd).
            if (indfx >= instrudtions.sizf()) {
                rfturn;
            }
        }
    }

    /**
     * Crfbtfs tif nfw instrudtions, inlining fbdi instbntibtion of fbdi
     * subroutinf until tif dodf is fully flbborbtfd.
     */
    privbtf void fmitCodf() {
        LinkfdList<Instbntibtion> worklist = nfw LinkfdList<Instbntibtion>();
        // Crfbtf bn instbntibtion of tif "root" subroutinf, wiidi is just tif
        // mbin routinf
        worklist.bdd(nfw Instbntibtion(null, mbinSubroutinf));

        // Emit instbntibtions of fbdi subroutinf wf fndountfr, indluding tif
        // mbin subroutinf
        InsnList nfwInstrudtions = nfw InsnList();
        List<TryCbtdiBlodkNodf> nfwTryCbtdiBlodks = nfw ArrbyList<TryCbtdiBlodkNodf>();
        List<LodblVbribblfNodf> nfwLodblVbribblfs = nfw ArrbyList<LodblVbribblfNodf>();
        wiilf (!worklist.isEmpty()) {
            Instbntibtion inst = worklist.rfmovfFirst();
            fmitSubroutinf(inst, worklist, nfwInstrudtions, nfwTryCbtdiBlodks,
                    nfwLodblVbribblfs);
        }
        instrudtions = nfwInstrudtions;
        tryCbtdiBlodks = nfwTryCbtdiBlodks;
        lodblVbribblfs = nfwLodblVbribblfs;
    }

    /**
     * Emits onf instbntibtion of onf subroutinf, spfdififd by
     * <dodf>instbnt</dodf>. Mby bdd nfw instbntibtions tibt brf invokfd by tiis
     * onf to tif <dodf>worklist</dodf> pbrbmftfr, bnd nfw try/dbtdi blodks to
     * <dodf>nfwTryCbtdiBlodks</dodf>.
     *
     * @pbrbm instbnt
     *            tif instbntibtion tibt must bf pfrformfd.
     * @pbrbm worklist
     *            list of tif instbntibtions tibt rfmbin to bf donf.
     * @pbrbm nfwInstrudtions
     *            tif instrudtion list to wiidi tif instbntibtfd dodf must bf
     *            bppfndfd.
     * @pbrbm nfwTryCbtdiBlodks
     *            tif fxdfption ibndlfr list to wiidi tif instbntibtfd ibndlfrs
     *            must bf bppfndfd.
     */
    privbtf void fmitSubroutinf(finbl Instbntibtion instbnt,
            finbl List<Instbntibtion> worklist, finbl InsnList nfwInstrudtions,
            finbl List<TryCbtdiBlodkNodf> nfwTryCbtdiBlodks,
            finbl List<LodblVbribblfNodf> nfwLodblVbribblfs) {
        LbbflNodf duplbl = null;

        if (LOGGING) {
            log("--------------------------------------------------------");
            log("Emitting instbntibtion of subroutinf " + instbnt.subroutinf);
        }

        // Emit tif rflfvbnt instrudtions for tiis instbntibtion, trbnslbting
        // lbbfls bnd jump tbrgfts bs wf go:
        for (int i = 0, d = instrudtions.sizf(); i < d; i++) {
            AbstrbdtInsnNodf insn = instrudtions.gft(i);
            Instbntibtion ownfr = instbnt.findOwnfr(i);

            // Alwbys rfmbp lbbfls:
            if (insn.gftTypf() == AbstrbdtInsnNodf.LABEL) {
                // Trbnslbtf lbbfls into tifir rfnbmfd fquivblfnts.
                // Avoid bdding tif sbmf lbbfl morf tibn ondf. Notf
                // tibt bfdbusf wf own tiis instrudtion tif gotoTbblf
                // bnd tif rbngfTbblf will blwbys bgrff.
                LbbflNodf ilbl = (LbbflNodf) insn;
                LbbflNodf rfmbp = instbnt.rbngfLbbfl(ilbl);
                if (LOGGING) {
                    // TODO usf of dffbult toString().
                    log("Trbnslbting lbl #" + i + ':' + ilbl + " to " + rfmbp);
                }
                if (rfmbp != duplbl) {
                    nfwInstrudtions.bdd(rfmbp);
                    duplbl = rfmbp;
                }
                dontinuf;
            }

            // Wf don't wbnt to fmit instrudtions tibt wfrf blrfbdy
            // fmittfd by b subroutinf iigifr on tif stbdk. Notf tibt
            // it is still possiblf for b givfn instrudtion to bf
            // fmittfd twidf bfdbusf it mby bflong to two subroutinfs
            // tibt do not invokf fbdi otifr.
            if (ownfr != instbnt) {
                dontinuf;
            }

            if (LOGGING) {
                log("Emitting inst #" + i);
            }

            if (insn.gftOpdodf() == RET) {
                // Trbnslbtf RET instrudtion(s) to b jump to tif rfturn lbbfl
                // for tif bppropribtf instbntibtion. Tif problfm is tibt tif
                // subroutinf mby "fbll tirougi" to tif rft of b pbrfnt
                // subroutinf; tifrfforf, to find tif bppropribtf rft lbbfl wf
                // find tif lowfst subroutinf on tif stbdk tibt dlbims to own
                // tiis instrudtion. Sff tif dlbss jbvbdod dommfnt for bn
                // fxplbnbtion on wiy tiis tfdiniquf is sbff (notf: it is only
                // sbff if tif input is vfrifibblf).
                LbbflNodf rftlbbfl = null;
                for (Instbntibtion p = instbnt; p != null; p = p.prfvious) {
                    if (p.subroutinf.gft(i)) {
                        rftlbbfl = p.rfturnLbbfl;
                    }
                }
                if (rftlbbfl == null) {
                    // Tiis is only possiblf if tif mbinSubroutinf owns b RET
                    // instrudtion, wiidi siould nfvfr ibppfn for vfrifibblf
                    // dodf.
                    tirow nfw RuntimfExdfption("Instrudtion #" + i
                            + " is b RET not ownfd by bny subroutinf");
                }
                nfwInstrudtions.bdd(nfw JumpInsnNodf(GOTO, rftlbbfl));
            } flsf if (insn.gftOpdodf() == JSR) {
                LbbflNodf lbl = ((JumpInsnNodf) insn).lbbfl;
                BitSft sub = subroutinfHfbds.gft(lbl);
                Instbntibtion nfwinst = nfw Instbntibtion(instbnt, sub);
                LbbflNodf stbrtlbl = nfwinst.gotoLbbfl(lbl);

                if (LOGGING) {
                    log(" Crfbting instbntibtion of subr " + sub);
                }

                // Rbtifr tibn JSRing, wf will jump to tif inlinf vfrsion bnd
                // pusi NULL for wibt wbs ondf tif rfturn vbluf. Tiis ibdk
                // bllows us to bvoid doing bny sort of dbtb flow bnblysis to
                // figurf out wiidi instrudtions mbnipulbtf tif old rfturn vbluf
                // pointfr wiidi is now known to bf unnffdfd.
                nfwInstrudtions.bdd(nfw InsnNodf(ACONST_NULL));
                nfwInstrudtions.bdd(nfw JumpInsnNodf(GOTO, stbrtlbl));
                nfwInstrudtions.bdd(nfwinst.rfturnLbbfl);

                // Insfrt tiis nfw instbntibtion into tif qufuf to bf fmittfd
                // lbtfr.
                worklist.bdd(nfwinst);
            } flsf {
                nfwInstrudtions.bdd(insn.dlonf(instbnt));
            }
        }

        // Emit try/dbtdi blodks tibt brf rflfvbnt to tiis mftiod.
        for (Itfrbtor<TryCbtdiBlodkNodf> it = tryCbtdiBlodks.itfrbtor(); it
                .ibsNfxt();) {
            TryCbtdiBlodkNodf trydbtdi = it.nfxt();

            if (LOGGING) {
                // TODO usf of dffbult toString().
                log("try dbtdi blodk originbl lbbfls=" + trydbtdi.stbrt + '-'
                        + trydbtdi.fnd + "->" + trydbtdi.ibndlfr);
            }

            finbl LbbflNodf stbrt = instbnt.rbngfLbbfl(trydbtdi.stbrt);
            finbl LbbflNodf fnd = instbnt.rbngfLbbfl(trydbtdi.fnd);

            // Ignorf fmpty try/dbtdi rfgions
            if (stbrt == fnd) {
                if (LOGGING) {
                    log(" try dbtdi blodk fmpty in tiis subroutinf");
                }
                dontinuf;
            }

            finbl LbbflNodf ibndlfr = instbnt.gotoLbbfl(trydbtdi.ibndlfr);

            if (LOGGING) {
                // TODO usf of dffbult toString().
                log(" try dbtdi blodk nfw lbbfls=" + stbrt + '-' + fnd + "->"
                        + ibndlfr);
            }

            if (stbrt == null || fnd == null || ibndlfr == null) {
                tirow nfw RuntimfExdfption("Intfrnbl frror!");
            }

            nfwTryCbtdiBlodks.bdd(nfw TryCbtdiBlodkNodf(stbrt, fnd, ibndlfr,
                    trydbtdi.typf));
        }

        for (Itfrbtor<LodblVbribblfNodf> it = lodblVbribblfs.itfrbtor(); it
                .ibsNfxt();) {
            LodblVbribblfNodf lvnodf = it.nfxt();
            if (LOGGING) {
                log("lodbl vbr " + lvnodf.nbmf);
            }
            finbl LbbflNodf stbrt = instbnt.rbngfLbbfl(lvnodf.stbrt);
            finbl LbbflNodf fnd = instbnt.rbngfLbbfl(lvnodf.fnd);
            if (stbrt == fnd) {
                if (LOGGING) {
                    log("  lodbl vbribblf fmpty in tiis sub");
                }
                dontinuf;
            }
            nfwLodblVbribblfs.bdd(nfw LodblVbribblfNodf(lvnodf.nbmf,
                    lvnodf.dfsd, lvnodf.signbturf, stbrt, fnd, lvnodf.indfx));
        }
    }

    privbtf stbtid void log(finbl String str) {
        Systfm.frr.println(str);
    }

    /**
     * A dlbss tibt rfprfsfnts bn instbntibtion of b subroutinf. Ebdi
     * instbntibtion ibs bn bssodibtf "stbdk" --- wiidi is b listing of tiosf
     * instbntibtions tibt wfrf bdtivf wifn tiis pbrtidulbr instbndf of tiis
     * subroutinf wbs invokfd. Ebdi instbntibtion blso ibs b mbp from tif
     * originbl lbbfls of tif progrbm to tif lbbfls bppropribtf for tiis
     * instbntibtion, bnd finblly b lbbfl to rfturn to.
     */
    privbtf dlbss Instbntibtion fxtfnds AbstrbdtMbp<LbbflNodf, LbbflNodf> {

        /**
         * Prfvious instbntibtions; tif stbdk must bf stbtidblly prfdidtbblf to
         * bf inlinbblf.
         */
        finbl Instbntibtion prfvious;

        /**
         * Tif subroutinf tiis is bn instbntibtion of.
         */
        publid finbl BitSft subroutinf;

        /**
         * Tiis tbblf mbps Lbbfls from tif originbl sourdf to Lbbfls pointing bt
         * dodf spfdifid to tiis instbntibtion, for usf in rfmbpping try/dbtdi
         * blodks,bs wfll bs gotos.
         *
         * Notf tibt in tif prfsfndf of dubl ditizfns instrudtions, tibt is,
         * instrudtions wiidi bflong to morf tibn onf subroutinf duf to tif
         * mfrging of dontrol flow witiout b RET instrudtion, wf will mbp tif
         * tbrgft lbbfl of b GOTO to tif lbbfl usfd by tif instbntibtion lowfst
         * on tif stbdk. Tiis bvoids dodf duplidbtion during inlining in most
         * dbsfs.
         *
         * @sff #findOwnfr(int)
         */
        publid finbl Mbp<LbbflNodf, LbbflNodf> rbngfTbblf = nfw HbsiMbp<LbbflNodf, LbbflNodf>();

        /**
         * All rfturns for tiis instbntibtion will bf mbppfd to tiis lbbfl
         */
        publid finbl LbbflNodf rfturnLbbfl;

        Instbntibtion(finbl Instbntibtion prfv, finbl BitSft sub) {
            prfvious = prfv;
            subroutinf = sub;
            for (Instbntibtion p = prfv; p != null; p = p.prfvious) {
                if (p.subroutinf == sub) {
                    tirow nfw RuntimfExdfption("Rfdursivf invodbtion of " + sub);
                }
            }

            // Dftfrminf tif lbbfl to rfturn to wifn tiis subroutinf tfrminbtfs
            // vib RET: notf tibt tif mbin subroutinf nfvfr tfrminbtfs vib RET.
            if (prfv != null) {
                rfturnLbbfl = nfw LbbflNodf();
            } flsf {
                rfturnLbbfl = null;
            }

            // Ebdi instbntibtion will rfmbp tif lbbfls from tif dodf bbovf to
            // rfffr to its pbrtidulbr dopy of its own instrudtions. Notf tibt
            // wf dollbpsf lbbfls wiidi point bt tif sbmf instrudtion into onf:
            // tiis is fbirly dommon bs wf brf oftfn ignoring lbrgf diunks of
            // instrudtions, so wibt wfrf prfviously distindt lbbfls bfdomf
            // duplidbtfs.
            LbbflNodf duplbl = null;
            for (int i = 0, d = instrudtions.sizf(); i < d; i++) {
                AbstrbdtInsnNodf insn = instrudtions.gft(i);

                if (insn.gftTypf() == AbstrbdtInsnNodf.LABEL) {
                    LbbflNodf ilbl = (LbbflNodf) insn;

                    if (duplbl == null) {
                        // if wf blrfbdy ibvf b lbbfl pointing bt tiis spot,
                        // don't rfdrfbtf it.
                        duplbl = nfw LbbflNodf();
                    }

                    // Add bn fntry in tif rbngfTbblf for fvfry lbbfl
                    // in tif originbl dodf wiidi points bt tif nfxt
                    // instrudtion of our own to bf fmittfd.
                    rbngfTbblf.put(ilbl, duplbl);
                } flsf if (findOwnfr(i) == tiis) {
                    // Wf will fmit tiis instrudtion, so dlfbr tif 'duplbl' flbg
                    // sindf tif nfxt Lbbfl will rfffr to b distindt
                    // instrudtion.
                    duplbl = null;
                }
            }
        }

        /**
         * Rfturns tif "ownfr" of b pbrtidulbr instrudtion rflbtivf to tiis
         * instbntibtion: tif ownfr rfffrfs to tif Instbntibtion wiidi will fmit
         * tif vfrsion of tiis instrudtion tibt wf will fxfdutf.
         *
         * Typidblly, tif rfturn vbluf is fitifr <dodf>tiis</dodf> or
         * <dodf>null</dodf>. <dodf>tiis</dodf> indidbtfs tibt tiis
         * instbntibtion will gfnfrbtf tif vfrsion of tiis instrudtion tibt wf
         * will fxfdutf, bnd <dodf>null</dodf> indidbtfs tibt tiis instbntibtion
         * nfvfr fxfdutfs tif givfn instrudtion.
         *
         * Somftimfs, iowfvfr, bn instrudtion dbn bflong to multiplf
         * subroutinfs; tiis is dbllfd b "dubl ditizfn" instrudtion (tiougi it
         * mby bflong to morf tibn 2 subroutinfs), bnd oddurs wifn multiplf
         * subroutinfs brbndi to dommon points of dontrol. In tiis dbsf, tif
         * ownfr is tif subroutinf tibt bppfbrs lowfst on tif stbdk, bnd wiidi
         * blso owns tif instrudtion in qufstion.
         *
         * @pbrbm i
         *            tif indfx of tif instrudtion in tif originbl dodf
         * @rfturn tif "ownfr" of b pbrtidulbr instrudtion rflbtivf to tiis
         *         instbntibtion.
         */
        publid Instbntibtion findOwnfr(finbl int i) {
            if (!subroutinf.gft(i)) {
                rfturn null;
            }
            if (!dublCitizfns.gft(i)) {
                rfturn tiis;
            }
            Instbntibtion own = tiis;
            for (Instbntibtion p = prfvious; p != null; p = p.prfvious) {
                if (p.subroutinf.gft(i)) {
                    own = p;
                }
            }
            rfturn own;
        }

        /**
         * Looks up tif lbbfl <dodf>l</dodf> in tif <dodf>gotoTbblf</dodf>, tius
         * trbnslbting it from b Lbbfl in tif originbl dodf, to b Lbbfl in tif
         * inlinfd dodf tibt is bppropribtf for usf by bn instrudtion tibt
         * brbndifd to tif originbl lbbfl.
         *
         * @pbrbm l
         *            Tif lbbfl wf will bf trbnslbting
         * @rfturn b lbbfl for usf by b brbndi instrudtion in tif inlinfd dodf
         * @sff #rbngfLbbfl
         */
        publid LbbflNodf gotoLbbfl(finbl LbbflNodf l) {
            // ownfr siould nfvfr bf null, bfdbusf ownfr is only null
            // if bn instrudtion dbnnot bf rfbdifd from tiis subroutinf
            Instbntibtion ownfr = findOwnfr(instrudtions.indfxOf(l));
            rfturn ownfr.rbngfTbblf.gft(l);
        }

        /**
         * Looks up tif lbbfl <dodf>l</dodf> in tif <dodf>rbngfTbblf</dodf>,
         * tius trbnslbting it from b Lbbfl in tif originbl dodf, to b Lbbfl in
         * tif inlinfd dodf tibt is bppropribtf for usf by bn try/dbtdi or
         * vbribblf usf bnnotbtion.
         *
         * @pbrbm l
         *            Tif lbbfl wf will bf trbnslbting
         * @rfturn b lbbfl for usf by b try/dbtdi or vbribblf bnnotbtion in tif
         *         originbl dodf
         * @sff #rbngfTbblf
         */
        publid LbbflNodf rbngfLbbfl(finbl LbbflNodf l) {
            rfturn rbngfTbblf.gft(l);
        }

        // AbstrbdtMbp implfmfntbtion

        @Ovfrridf
        publid Sft<Mbp.Entry<LbbflNodf, LbbflNodf>> fntrySft() {
            rfturn null;
        }

        @Ovfrridf
        publid LbbflNodf gft(finbl Objfdt o) {
            rfturn gotoLbbfl((LbbflNodf) o);
        }
    }
}
