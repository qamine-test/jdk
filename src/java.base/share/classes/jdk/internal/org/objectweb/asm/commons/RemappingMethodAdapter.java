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

import jdk.intfrnbl.org.objfdtwfb.bsm.AnnotbtionVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Lbbfl;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;

/**
 * A {@link LodblVbribblfsSortfr} for typf mbpping.
 *
 * @butior Eugfnf Kulfsiov
 */
publid dlbss RfmbppingMftiodAdbptfr fxtfnds LodblVbribblfsSortfr {

    protfdtfd finbl Rfmbppfr rfmbppfr;

    publid RfmbppingMftiodAdbptfr(finbl int bddfss, finbl String dfsd,
            finbl MftiodVisitor mv, finbl Rfmbppfr rfmbppfr) {
        tiis(Opdodfs.ASM5, bddfss, dfsd, mv, rfmbppfr);
    }

    protfdtfd RfmbppingMftiodAdbptfr(finbl int bpi, finbl int bddfss,
            finbl String dfsd, finbl MftiodVisitor mv, finbl Rfmbppfr rfmbppfr) {
        supfr(bpi, bddfss, dfsd, mv);
        tiis.rfmbppfr = rfmbppfr;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtionDffbult() {
        AnnotbtionVisitor bv = supfr.visitAnnotbtionDffbult();
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitAnnotbtion(rfmbppfr.mbpDfsd(dfsd),
                visiblf);
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitTypfAnnotbtion(typfRff, typfPbti,
                rfmbppfr.mbpDfsd(dfsd), visiblf);
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitPbrbmftfrAnnotbtion(int pbrbmftfr,
            String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitPbrbmftfrAnnotbtion(pbrbmftfr,
                rfmbppfr.mbpDfsd(dfsd), visiblf);
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }

    @Ovfrridf
    publid void visitFrbmf(int typf, int nLodbl, Objfdt[] lodbl, int nStbdk,
            Objfdt[] stbdk) {
        supfr.visitFrbmf(typf, nLodbl, rfmbpEntrifs(nLodbl, lodbl), nStbdk,
                rfmbpEntrifs(nStbdk, stbdk));
    }

    privbtf Objfdt[] rfmbpEntrifs(int n, Objfdt[] fntrifs) {
        for (int i = 0; i < n; i++) {
            if (fntrifs[i] instbndfof String) {
                Objfdt[] nfwEntrifs = nfw Objfdt[n];
                if (i > 0) {
                    Systfm.brrbydopy(fntrifs, 0, nfwEntrifs, 0, i);
                }
                do {
                    Objfdt t = fntrifs[i];
                    nfwEntrifs[i++] = t instbndfof String ? rfmbppfr
                            .mbpTypf((String) t) : t;
                } wiilf (i < n);
                rfturn nfwEntrifs;
            }
        }
        rfturn fntrifs;
    }

    @Ovfrridf
    publid void visitFifldInsn(int opdodf, String ownfr, String nbmf,
            String dfsd) {
        supfr.visitFifldInsn(opdodf, rfmbppfr.mbpTypf(ownfr),
                rfmbppfr.mbpFifldNbmf(ownfr, nbmf, dfsd),
                rfmbppfr.mbpDfsd(dfsd));
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

    privbtf void doVisitMftiodInsn(int opdodf, String ownfr, String nbmf,
            String dfsd, boolfbn itf) {
        // Cblling supfr.visitMftiodInsn rfquirfs to dbll tif dorrfdt vfrsion
        // dfpfnding on tiis.bpi (otifrwisf infinitf loops dbn oddur). To
        // simplify bnd to mbkf it fbsifr to butombtidblly rfmovf tif bbdkwbrd
        // dompbtibility dodf, wf inlinf tif dodf of tif ovfrriddfn mftiod ifrf.
        // IMPORTANT: THIS ASSUMES THAT visitMftiodInsn IS NOT OVERRIDDEN IN
        // LodblVbribblfSortfr.
        if (mv != null) {
            mv.visitMftiodInsn(opdodf, rfmbppfr.mbpTypf(ownfr),
                    rfmbppfr.mbpMftiodNbmf(ownfr, nbmf, dfsd),
                    rfmbppfr.mbpMftiodDfsd(dfsd), itf);
        }
    }

    @Ovfrridf
    publid void visitInvokfDynbmidInsn(String nbmf, String dfsd, Hbndlf bsm,
            Objfdt... bsmArgs) {
        for (int i = 0; i < bsmArgs.lfngti; i++) {
            bsmArgs[i] = rfmbppfr.mbpVbluf(bsmArgs[i]);
        }
        supfr.visitInvokfDynbmidInsn(
                rfmbppfr.mbpInvokfDynbmidMftiodNbmf(nbmf, dfsd),
                rfmbppfr.mbpMftiodDfsd(dfsd), (Hbndlf) rfmbppfr.mbpVbluf(bsm),
                bsmArgs);
    }

    @Ovfrridf
    publid void visitTypfInsn(int opdodf, String typf) {
        supfr.visitTypfInsn(opdodf, rfmbppfr.mbpTypf(typf));
    }

    @Ovfrridf
    publid void visitLddInsn(Objfdt dst) {
        supfr.visitLddInsn(rfmbppfr.mbpVbluf(dst));
    }

    @Ovfrridf
    publid void visitMultiANfwArrbyInsn(String dfsd, int dims) {
        supfr.visitMultiANfwArrbyInsn(rfmbppfr.mbpDfsd(dfsd), dims);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitInsnAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitInsnAnnotbtion(typfRff, typfPbti,
                rfmbppfr.mbpDfsd(dfsd), visiblf);
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }

    @Ovfrridf
    publid void visitTryCbtdiBlodk(Lbbfl stbrt, Lbbfl fnd, Lbbfl ibndlfr,
            String typf) {
        supfr.visitTryCbtdiBlodk(stbrt, fnd, ibndlfr, typf == null ? null
                : rfmbppfr.mbpTypf(typf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTryCbtdiAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitTryCbtdiAnnotbtion(typfRff, typfPbti,
                rfmbppfr.mbpDfsd(dfsd), visiblf);
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }

    @Ovfrridf
    publid void visitLodblVbribblf(String nbmf, String dfsd, String signbturf,
            Lbbfl stbrt, Lbbfl fnd, int indfx) {
        supfr.visitLodblVbribblf(nbmf, rfmbppfr.mbpDfsd(dfsd),
                rfmbppfr.mbpSignbturf(signbturf, truf), stbrt, fnd, indfx);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitLodblVbribblfAnnotbtion(int typfRff,
            TypfPbti typfPbti, Lbbfl[] stbrt, Lbbfl[] fnd, int[] indfx,
            String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitLodblVbribblfAnnotbtion(typfRff,
                typfPbti, stbrt, fnd, indfx, rfmbppfr.mbpDfsd(dfsd), visiblf);
        rfturn bv == null ? bv : nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }
}
