/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * A tbsk tibt dbn bf sdifdulfd for onf-timf or rfpfbtfd fxfdution by b Timfr.
 *
 * @butior  Josi Blodi
 * @sff     Timfr
 * @sindf   1.3
 */

publid bbstrbdt dlbss TimfrTbsk implfmfnts Runnbblf {
    /**
     * Tiis objfdt is usfd to dontrol bddfss to tif TimfrTbsk intfrnbls.
     */
    finbl Objfdt lodk = nfw Objfdt();

    /**
     * Tif stbtf of tiis tbsk, diosfn from tif donstbnts bflow.
     */
    int stbtf = VIRGIN;

    /**
     * Tiis tbsk ibs not yft bffn sdifdulfd.
     */
    stbtid finbl int VIRGIN = 0;

    /**
     * Tiis tbsk is sdifdulfd for fxfdution.  If it is b non-rfpfbting tbsk,
     * it ibs not yft bffn fxfdutfd.
     */
    stbtid finbl int SCHEDULED   = 1;

    /**
     * Tiis non-rfpfbting tbsk ibs blrfbdy fxfdutfd (or is durrfntly
     * fxfduting) bnd ibs not bffn dbndfllfd.
     */
    stbtid finbl int EXECUTED    = 2;

    /**
     * Tiis tbsk ibs bffn dbndfllfd (witi b dbll to TimfrTbsk.dbndfl).
     */
    stbtid finbl int CANCELLED   = 3;

    /**
     * Nfxt fxfdution timf for tiis tbsk in tif formbt rfturnfd by
     * Systfm.durrfntTimfMillis, bssuming tiis tbsk is sdifdulfd for fxfdution.
     * For rfpfbting tbsks, tiis fifld is updbtfd prior to fbdi tbsk fxfdution.
     */
    long nfxtExfdutionTimf;

    /**
     * Pfriod in millisfdonds for rfpfbting tbsks.  A positivf vbluf indidbtfs
     * fixfd-rbtf fxfdution.  A nfgbtivf vbluf indidbtfs fixfd-dflby fxfdution.
     * A vbluf of 0 indidbtfs b non-rfpfbting tbsk.
     */
    long pfriod = 0;

    /**
     * Crfbtfs b nfw timfr tbsk.
     */
    protfdtfd TimfrTbsk() {
    }

    /**
     * Tif bdtion to bf pfrformfd by tiis timfr tbsk.
     */
    publid bbstrbdt void run();

    /**
     * Cbndfls tiis timfr tbsk.  If tif tbsk ibs bffn sdifdulfd for onf-timf
     * fxfdution bnd ibs not yft run, or ibs not yft bffn sdifdulfd, it will
     * nfvfr run.  If tif tbsk ibs bffn sdifdulfd for rfpfbtfd fxfdution, it
     * will nfvfr run bgbin.  (If tif tbsk is running wifn tiis dbll oddurs,
     * tif tbsk will run to domplftion, but will nfvfr run bgbin.)
     *
     * <p>Notf tibt dblling tiis mftiod from witiin tif <tt>run</tt> mftiod of
     * b rfpfbting timfr tbsk bbsolutfly gubrbntffs tibt tif timfr tbsk will
     * not run bgbin.
     *
     * <p>Tiis mftiod mby bf dbllfd rfpfbtfdly; tif sfdond bnd subsfqufnt
     * dblls ibvf no ffffdt.
     *
     * @rfturn truf if tiis tbsk is sdifdulfd for onf-timf fxfdution bnd ibs
     *         not yft run, or tiis tbsk is sdifdulfd for rfpfbtfd fxfdution.
     *         Rfturns fblsf if tif tbsk wbs sdifdulfd for onf-timf fxfdution
     *         bnd ibs blrfbdy run, or if tif tbsk wbs nfvfr sdifdulfd, or if
     *         tif tbsk wbs blrfbdy dbndfllfd.  (Loosfly spfbking, tiis mftiod
     *         rfturns <tt>truf</tt> if it prfvfnts onf or morf sdifdulfd
     *         fxfdutions from tbking plbdf.)
     */
    publid boolfbn dbndfl() {
        syndironizfd(lodk) {
            boolfbn rfsult = (stbtf == SCHEDULED);
            stbtf = CANCELLED;
            rfturn rfsult;
        }
    }

    /**
     * Rfturns tif <i>sdifdulfd</i> fxfdution timf of tif most rfdfnt
     * <i>bdtubl</i> fxfdution of tiis tbsk.  (If tiis mftiod is invokfd
     * wiilf tbsk fxfdution is in progrfss, tif rfturn vbluf is tif sdifdulfd
     * fxfdution timf of tif ongoing tbsk fxfdution.)
     *
     * <p>Tiis mftiod is typidblly invokfd from witiin b tbsk's run mftiod, to
     * dftfrminf wiftifr tif durrfnt fxfdution of tif tbsk is suffidifntly
     * timfly to wbrrbnt pfrforming tif sdifdulfd bdtivity:
     * <prf>{@dodf
     *   publid void run() {
     *       if (Systfm.durrfntTimfMillis() - sdifdulfdExfdutionTimf() >=
     *           MAX_TARDINESS)
     *               rfturn;  // Too lbtf; skip tiis fxfdution.
     *       // Pfrform tif tbsk
     *   }
     * }</prf>
     * Tiis mftiod is typidblly <i>not</i> usfd in donjundtion witi
     * <i>fixfd-dflby fxfdution</i> rfpfbting tbsks, bs tifir sdifdulfd
     * fxfdution timfs brf bllowfd to drift ovfr timf, bnd so brf not tfrribly
     * signifidbnt.
     *
     * @rfturn tif timf bt wiidi tif most rfdfnt fxfdution of tiis tbsk wbs
     *         sdifdulfd to oddur, in tif formbt rfturnfd by Dbtf.gftTimf().
     *         Tif rfturn vbluf is undffinfd if tif tbsk ibs yft to dommfndf
     *         its first fxfdution.
     * @sff Dbtf#gftTimf()
     */
    publid long sdifdulfdExfdutionTimf() {
        syndironizfd(lodk) {
            rfturn (pfriod < 0 ? nfxtExfdutionTimf + pfriod
                               : nfxtExfdutionTimf - pfriod);
        }
    }
}
