/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvb.util.Collfdtion;
import jbvb.util.HbsiSft;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss JobStbtfRfbsons is b printing bttributf dlbss, b sft of fnumfrbtion
 * vblufs, tibt providfs bdditionbl informbtion bbout tif job's durrfnt stbtf,
 * i.f., informbtion tibt bugmfnts tif vbluf of tif job's {@link JobStbtf
 * JobStbtf} bttributf.
 * <P>
 * Instbndfs of {@link JobStbtfRfbson JobStbtfRfbson} do not bppfbr in b Print
 * Job's bttributf sft dirfdtly. Rbtifr, b JobStbtfRfbsons bttributf bppfbrs in
 * tif Print Job's bttributf sft. Tif JobStbtfRfbsons bttributf dontbins zfro,
 * onf, or morf tibn onf {@link JobStbtfRfbson JobStbtfRfbson} objfdts wiidi
 * pfrtbin to tif Print Job's stbtus. Tif printfr bdds b {@link JobStbtfRfbson
 * JobStbtfRfbson} objfdt to tif Print Job's JobStbtfRfbsons bttributf wifn tif
 * dorrfsponding dondition bfdomfs truf of tif Print Job, bnd tif printfr
 * rfmovfs tif {@link JobStbtfRfbson JobStbtfRfbson} objfdt bgbin wifn tif
 * dorrfsponding dondition bfdomfs fblsf, rfgbrdlfss of wiftifr tif Print Job's
 * ovfrbll {@link JobStbtf JobStbtf} blso dibngfd.
 * <P>
 * Clbss JobStbtfRfbsons inifrits its implfmfntbtion from dlbss {@link
 * jbvb.util.HbsiSft jbvb.util.HbsiSft}. Unlikf most printing bttributfs wiidi
 * brf immutbblf ondf donstrudtfd, dlbss JobStbtfRfbsons is dfsignfd to bf
 * mutbblf; you dbn bdd {@link JobStbtfRfbson JobStbtfRfbson} objfdts to bn
 * fxisting JobStbtfRfbsons objfdt bnd rfmovf tifm bgbin. Howfvfr, likf dlbss
 * {@link jbvb.util.HbsiSft jbvb.util.HbsiSft}, dlbss JobStbtfRfbsons is not
 * multiplf tirfbd sbff. If b JobStbtfRfbsons objfdt will bf usfd by multiplf
 * tirfbds, bf surf to syndironizf its opfrbtions (f.g., using b syndironizfd
 * sft vifw obtbinfd from dlbss {@link jbvb.util.Collfdtions
 * jbvb.util.Collfdtions}).
 * <P>
 * <B>IPP Compbtibility:</B> Tif string vbluf rfturnfd by fbdi individubl {@link
 * JobStbtfRfbson JobStbtfRfbson} objfdt's <CODE>toString()</CODE> mftiod givfs
 * tif IPP kfyword vbluf. Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE>
 * givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobStbtfRfbsons
    fxtfnds HbsiSft<JobStbtfRfbson> implfmfnts PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 8849088261264331812L;

    /**
     * Construdt b nfw, fmpty job stbtf rfbsons bttributf; tif undfrlying ibsi
     * sft ibs tif dffbult initibl dbpbdity bnd lobd fbdtor.
     */
    publid JobStbtfRfbsons() {
        supfr();
    }

    /**
     * Construdt b nfw, fmpty job stbtf rfbsons bttributf; tif undfrlying ibsi
     * sft ibs tif givfn initibl dbpbdity bnd tif dffbult lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity  Initibl dbpbdity.
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *     tibn zfro.
     */
    publid JobStbtfRfbsons(int initiblCbpbdity) {
        supfr (initiblCbpbdity);
    }

    /**
     * Construdt b nfw, fmpty job stbtf rfbsons bttributf; tif undfrlying ibsi
     * sft ibs tif givfn initibl dbpbdity bnd lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity  Initibl dbpbdity.
     * @pbrbm  lobdFbdtor       Lobd fbdtor.
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is lfss
     *     tibn zfro.
     */
    publid JobStbtfRfbsons(int initiblCbpbdity, flobt lobdFbdtor) {
        supfr (initiblCbpbdity, lobdFbdtor);
    }

    /**
     * Construdt b nfw job stbtf rfbsons bttributf tibt dontbins tif sbmf
     * {@link JobStbtfRfbson JobStbtfRfbson} objfdts bs tif givfn dollfdtion.
     * Tif undfrlying ibsi sft's initibl dbpbdity bnd lobd fbdtor brf bs
     * spfdififd in tif supfrdlbss donstrudtor {@link
     * jbvb.util.HbsiSft#HbsiSft(jbvb.util.Collfdtion)
     * HbsiSft(Collfdtion)}.
     *
     * @pbrbm  dollfdtion  Collfdtion to dopy.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>dollfdtion</CODE> is null or
     *     if bny flfmfnt in <CODE>dollfdtion</CODE> is null.
     * @tirows  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt in
     *     <CODE>dollfdtion</CODE> is not bn instbndf of dlbss {@link
     *     JobStbtfRfbson JobStbtfRfbson}.
     */
   publid JobStbtfRfbsons(Collfdtion<JobStbtfRfbson> dollfdtion) {
       supfr (dollfdtion);
   }

    /**
     * Adds tif spfdififd flfmfnt to tiis job stbtf rfbsons bttributf if it is
     * not blrfbdy prfsfnt. Tif flfmfnt to bf bddfd must bf bn instbndf of dlbss
     * {@link JobStbtfRfbson JobStbtfRfbson}. If tiis job stbtf rfbsons
     * bttributf blrfbdy dontbins tif spfdififd flfmfnt, tif dbll lfbvfs tiis
     * job stbtf rfbsons bttributf undibngfd bnd rfturns <tt>fblsf</tt>.
     *
     * @pbrbm  o  Elfmfnt to bf bddfd to tiis job stbtf rfbsons bttributf.
     *
     * @rfturn  <tt>truf</tt> if tiis job stbtf rfbsons bttributf did not
     *          blrfbdy dontbin tif spfdififd flfmfnt.
     *
     * @tirows  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if tif spfdififd flfmfnt is null.
     * @tirows  ClbssCbstExdfption
     *     (undifdkfd fxdfption) Tirown if tif spfdififd flfmfnt is not bn
     *     instbndf of dlbss {@link JobStbtfRfbson JobStbtfRfbson}.
     * @sindf 1.5
     */
    publid boolfbn bdd(JobStbtfRfbson o) {
        if (o == null) {
            tirow nfw NullPointfrExdfption();
        }
        rfturn supfr.bdd(o);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobStbtfRfbsons, tif dbtfgory is dlbss JobStbtfRfbsons itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobStbtfRfbsons.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobStbtfRfbsons, tif dbtfgory
     * nbmf is <CODE>"job-stbtf-rfbsons"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-stbtf-rfbsons";
    }

}
