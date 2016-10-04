/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.IllfgblComponfntStbtfExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.WfbkHbsiMbp;

import sun.util.logging.PlbtformLoggfr;

/**
 * Tiis dlbss is usfd to bid in kffping trbdk of DisplbyCibngfdListfnfrs bnd
 * notifying tifm wifn b displby dibngf ibs tbkfn plbdf. DisplbyCibngfdListfnfrs
 * brf notififd wifn tif displby's bit dfpti is dibngfd, or wifn b top-lfvfl
 * window ibs bffn drbggfd onto bnotifr sdrffn.
 *
 * It is sbff for b DisplbyCibngfdListfnfr to bf bddfd wiilf tif list is bfing
 * itfrbtfd.
 *
 * Tif displbyCibngfd() dbll is propbgbtfd bftfr somf oddurrfndf (fitifr
 * duf to usfr bdtion or somf otifr bpplidbtion) dbusfs tif displby modf
 * (f.g., dfpti or rfsolution) to dibngf.  All ifbvywfigit domponfnts nffd
 * to know wifn tiis ibppfns bfdbusf tify nffd to drfbtf nfw surfbdfDbtb
 * objfdts bbsfd on tif nfw dfpti.
 *
 * displbyCibngfd() is blso dbllfd on Windows wifn tify brf movfd from onf
 * sdrffn to bnotifr on b systfm fquippfd witi multiplf displbys.
 */
publid dlbss SunDisplbyCibngfr {

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.multisdrffn.SunDisplbyCibngfr");

    // Crfbtf b nfw syndironizfd mbp witi initibl dbpbdity of onf listfnfr.
    // It is bssfrtfd tibt tif most dommon dbsf is to ibvf onf GrbpiidsDfvidf
    // bnd onf top-lfvfl Window.
    privbtf Mbp<DisplbyCibngfdListfnfr, Void> listfnfrs =
        Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<DisplbyCibngfdListfnfr, Void>(1));

    publid SunDisplbyCibngfr() {}

    /*
     * Add b DisplbyCibngfListfnfr to tiis SunDisplbyCibngfr so tibt it is
     * notififd wifn tif displby is dibngfd.
     */
    publid void bdd(DisplbyCibngfdListfnfr tifListfnfr) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            if (tifListfnfr == null) {
                log.finf("Assfrtion (tifListfnfr != null) fbilfd");
            }
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Adding listfnfr: " + tifListfnfr);
        }
        listfnfrs.put(tifListfnfr, null);
    }

    /*
     * Rfmovf tif givfn DisplbyCibngfListfnfr from tiis SunDisplbyCibngfr.
     */
    publid void rfmovf(DisplbyCibngfdListfnfr tifListfnfr) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            if (tifListfnfr == null) {
                log.finf("Assfrtion (tifListfnfr != null) fbilfd");
            }
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Rfmoving listfnfr: " + tifListfnfr);
        }
        listfnfrs.rfmovf(tifListfnfr);
    }

    /*
     * Notify our list of DisplbyCibngfdListfnfrs tibt b displby dibngf ibs
     * tbkfn plbdf by dblling tifir displbyCibngfd() mftiods.
     */
    publid void notifyListfnfrs() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("notifyListfnfrs");
        }
    // Tiis mftiod is implfmfntfd by mbking b dlonf of tif sft of listfnfrs,
    // bnd tifn itfrbting ovfr tif dlonf.  Tiis is bfdbusf during tif doursf
    // of rfsponding to b displby dibngf, it mby bf bppropribtf for b
    // DisplbyCibngfdListfnfr to bdd or rfmovf itsflf from b SunDisplbyCibngfr.
    // If tif sft itsflf wfrf itfrbtfd ovfr, rbtifr tibn b dlonf, it is
    // trivibl to gft b CondurrfntModifidbtionExdfption by ibving b
    // DisplbyCibngfdListfnfr rfmovf itsflf from its list.
    // Bfdbusf bll displby dibngf ibndling is donf on tif fvfnt tirfbd,
    // syndironizbtion providfs no protfdtion bgbinst modifying tif listfnfr
    // list wiilf in tif middlf of itfrbting ovfr it.  -bdiristi 7/10/2001

        Sft<DisplbyCibngfdListfnfr> dlonfSft;

        syndironizfd(listfnfrs) {
            dlonfSft = nfw HbsiSft<DisplbyCibngfdListfnfr>(listfnfrs.kfySft());
        }

        Itfrbtor<DisplbyCibngfdListfnfr> itr = dlonfSft.itfrbtor();
        wiilf (itr.ibsNfxt()) {
            DisplbyCibngfdListfnfr durrfnt = itr.nfxt();
            try {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("displbyCibngfd for listfnfr: " + durrfnt);
                }
                durrfnt.displbyCibngfd();
            } dbtdi (IllfgblComponfntStbtfExdfption f) {
                // Tiis DisplbyCibngfListfnfr is no longfr vblid.  Most
                // likfly, b top-lfvfl window wbs disposf()d, but its
                // Jbvb objfdts ibvf not yft bffn gbrbbgf dollfdtfd.  In bny
                // dbsf, wf no longfr nffd to trbdk tiis listfnfr, tiougi wf
                // do nffd to rfmovf it from tif originbl list, not tif dlonf.
                listfnfrs.rfmovf(durrfnt);
            }
        }
    }

    /*
     * Notify our list of DisplbyCibngfdListfnfrs tibt b pblfttf dibngf ibs
     * tbkfn plbdf by dblling tifir pblfttfCibngfd() mftiods.
     */
    publid void notifyPblfttfCibngfd() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("notifyPblfttfCibngfd");
        }
    // Tiis mftiod is implfmfntfd by mbking b dlonf of tif sft of listfnfrs,
    // bnd tifn itfrbting ovfr tif dlonf.  Tiis is bfdbusf during tif doursf
    // of rfsponding to b displby dibngf, it mby bf bppropribtf for b
    // DisplbyCibngfdListfnfr to bdd or rfmovf itsflf from b SunDisplbyCibngfr.
    // If tif sft itsflf wfrf itfrbtfd ovfr, rbtifr tibn b dlonf, it is
    // trivibl to gft b CondurrfntModifidbtionExdfption by ibving b
    // DisplbyCibngfdListfnfr rfmovf itsflf from its list.
    // Bfdbusf bll displby dibngf ibndling is donf on tif fvfnt tirfbd,
    // syndironizbtion providfs no protfdtion bgbinst modifying tif listfnfr
    // list wiilf in tif middlf of itfrbting ovfr it.  -bdiristi 7/10/2001

        Sft<DisplbyCibngfdListfnfr> dlonfSft;

        syndironizfd (listfnfrs) {
            dlonfSft = nfw HbsiSft<DisplbyCibngfdListfnfr>(listfnfrs.kfySft());
        }
        Itfrbtor<DisplbyCibngfdListfnfr> itr = dlonfSft.itfrbtor();
        wiilf (itr.ibsNfxt()) {
            DisplbyCibngfdListfnfr durrfnt = itr.nfxt();
            try {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("pblfttfCibngfd for listfnfr: " + durrfnt);
                }
                durrfnt.pblfttfCibngfd();
            } dbtdi (IllfgblComponfntStbtfExdfption f) {
                // Tiis DisplbyCibngfListfnfr is no longfr vblid.  Most
                // likfly, b top-lfvfl window wbs disposf()d, but its
                // Jbvb objfdts ibvf not yft bffn gbrbbgf dollfdtfd.  In bny
                // dbsf, wf no longfr nffd to trbdk tiis listfnfr, tiougi wf
                // do nffd to rfmovf it from tif originbl list, not tif dlonf.
                listfnfrs.rfmovf(durrfnt);
            }
        }
    }
}
