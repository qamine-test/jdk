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
import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.FifldVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs;
import jdk.intfrnbl.org.objfdtwfb.bsm.TypfPbti;

/**
 * A {@link ClbssVisitor} for typf rfmbpping.
 *
 * @butior Eugfnf Kulfsiov
 */
publid dlbss RfmbppingClbssAdbptfr fxtfnds ClbssVisitor {

    protfdtfd finbl Rfmbppfr rfmbppfr;

    protfdtfd String dlbssNbmf;

    publid RfmbppingClbssAdbptfr(finbl ClbssVisitor dv, finbl Rfmbppfr rfmbppfr) {
        tiis(Opdodfs.ASM5, dv, rfmbppfr);
    }

    protfdtfd RfmbppingClbssAdbptfr(finbl int bpi, finbl ClbssVisitor dv,
            finbl Rfmbppfr rfmbppfr) {
        supfr(bpi, dv);
        tiis.rfmbppfr = rfmbppfr;
    }

    @Ovfrridf
    publid void visit(int vfrsion, int bddfss, String nbmf, String signbturf,
            String supfrNbmf, String[] intfrfbdfs) {
        tiis.dlbssNbmf = nbmf;
        supfr.visit(vfrsion, bddfss, rfmbppfr.mbpTypf(nbmf), rfmbppfr
                .mbpSignbturf(signbturf, fblsf), rfmbppfr.mbpTypf(supfrNbmf),
                intfrfbdfs == null ? null : rfmbppfr.mbpTypfs(intfrfbdfs));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitAnnotbtion(rfmbppfr.mbpDfsd(dfsd),
                visiblf);
        rfturn bv == null ? null : drfbtfRfmbppingAnnotbtionAdbptfr(bv);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(int typfRff,
            TypfPbti typfPbti, String dfsd, boolfbn visiblf) {
        AnnotbtionVisitor bv = supfr.visitTypfAnnotbtion(typfRff, typfPbti,
                rfmbppfr.mbpDfsd(dfsd), visiblf);
        rfturn bv == null ? null : drfbtfRfmbppingAnnotbtionAdbptfr(bv);
    }

    @Ovfrridf
    publid FifldVisitor visitFifld(int bddfss, String nbmf, String dfsd,
            String signbturf, Objfdt vbluf) {
        FifldVisitor fv = supfr.visitFifld(bddfss,
                rfmbppfr.mbpFifldNbmf(dlbssNbmf, nbmf, dfsd),
                rfmbppfr.mbpDfsd(dfsd), rfmbppfr.mbpSignbturf(signbturf, truf),
                rfmbppfr.mbpVbluf(vbluf));
        rfturn fv == null ? null : drfbtfRfmbppingFifldAdbptfr(fv);
    }

    @Ovfrridf
    publid MftiodVisitor visitMftiod(int bddfss, String nbmf, String dfsd,
            String signbturf, String[] fxdfptions) {
        String nfwDfsd = rfmbppfr.mbpMftiodDfsd(dfsd);
        MftiodVisitor mv = supfr.visitMftiod(bddfss, rfmbppfr.mbpMftiodNbmf(
                dlbssNbmf, nbmf, dfsd), nfwDfsd, rfmbppfr.mbpSignbturf(
                signbturf, fblsf),
                fxdfptions == null ? null : rfmbppfr.mbpTypfs(fxdfptions));
        rfturn mv == null ? null : drfbtfRfmbppingMftiodAdbptfr(bddfss,
                nfwDfsd, mv);
    }

    @Ovfrridf
    publid void visitInnfrClbss(String nbmf, String outfrNbmf,
            String innfrNbmf, int bddfss) {
        // TODO siould innfrNbmf bf dibngfd?
        supfr.visitInnfrClbss(rfmbppfr.mbpTypf(nbmf), outfrNbmf == null ? null
                : rfmbppfr.mbpTypf(outfrNbmf), innfrNbmf, bddfss);
    }

    @Ovfrridf
    publid void visitOutfrClbss(String ownfr, String nbmf, String dfsd) {
        supfr.visitOutfrClbss(rfmbppfr.mbpTypf(ownfr), nbmf == null ? null
                : rfmbppfr.mbpMftiodNbmf(ownfr, nbmf, dfsd),
                dfsd == null ? null : rfmbppfr.mbpMftiodDfsd(dfsd));
    }

    protfdtfd FifldVisitor drfbtfRfmbppingFifldAdbptfr(FifldVisitor fv) {
        rfturn nfw RfmbppingFifldAdbptfr(fv, rfmbppfr);
    }

    protfdtfd MftiodVisitor drfbtfRfmbppingMftiodAdbptfr(int bddfss,
            String nfwDfsd, MftiodVisitor mv) {
        rfturn nfw RfmbppingMftiodAdbptfr(bddfss, nfwDfsd, mv, rfmbppfr);
    }

    protfdtfd AnnotbtionVisitor drfbtfRfmbppingAnnotbtionAdbptfr(
            AnnotbtionVisitor bv) {
        rfturn nfw RfmbppingAnnotbtionAdbptfr(bv, rfmbppfr);
    }
}
