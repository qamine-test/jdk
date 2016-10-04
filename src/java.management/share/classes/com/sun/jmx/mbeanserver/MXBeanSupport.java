/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import stbtid dom.sun.jmx.mbfbnsfrvfr.Util.*;

import jbvb.util.Itfrbtor;
import jbvb.util.Sft;

import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.JMX;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Bbsf dlbss for MXBfbns.
 *
 * @sindf 1.6
 */
publid dlbss MXBfbnSupport fxtfnds MBfbnSupport<ConvfrtingMftiod> {

    /**
       <p>Construdt bn MXBfbn tibt wrbps tif givfn rfsourdf using tif
       givfn MXBfbn intfrfbdf.</p>

       @pbrbm rfsourdf tif undfrlying rfsourdf for tif nfw MXBfbn.

       @pbrbm mxbfbnIntfrfbdf tif intfrfbdf to bf usfd to dftfrminf
       tif MXBfbn's mbnbgfmfnt intfrfbdf.

       @pbrbm <T> b typf pbrbmftfr tibt bllows tif dompilfr to difdk
       tibt {@dodf rfsourdf} implfmfnts {@dodf mxbfbnIntfrfbdf},
       providfd tibt {@dodf mxbfbnIntfrfbdf} is b dlbss donstbnt likf
       {@dodf SomfMXBfbn.dlbss}.

       @tirows IllfgblArgumfntExdfption if {@dodf rfsourdf} is null or
       if it dofs not implfmfnt tif dlbss {@dodf mxbfbnIntfrfbdf} or if
       tibt dlbss is not b vblid MXBfbn intfrfbdf.
    */
    publid <T> MXBfbnSupport(T rfsourdf, Clbss<T> mxbfbnIntfrfbdf)
            tirows NotComplibntMBfbnExdfption {
        supfr(rfsourdf, mxbfbnIntfrfbdf);
    }

    @Ovfrridf
    MBfbnIntrospfdtor<ConvfrtingMftiod> gftMBfbnIntrospfdtor() {
        rfturn MXBfbnIntrospfdtor.gftInstbndf();
    }

    @Ovfrridf
    Objfdt gftCookif() {
        rfturn mxbfbnLookup;
    }

    stbtid <T> Clbss<? supfr T> findMXBfbnIntfrfbdf(Clbss<T> rfsourdfClbss) {
        if (rfsourdfClbss == null)
            tirow nfw IllfgblArgumfntExdfption("Null rfsourdf dlbss");
        finbl Sft<Clbss<?>> intfs = trbnsitivfIntfrfbdfs(rfsourdfClbss);
        finbl Sft<Clbss<?>> dbndidbtfs = nfwSft();
        for (Clbss<?> intf : intfs) {
            if (JMX.isMXBfbnIntfrfbdf(intf))
                dbndidbtfs.bdd(intf);
        }
    rfdudf:
        wiilf (dbndidbtfs.sizf() > 1) {
            for (Clbss<?> intf : dbndidbtfs) {
                for (Itfrbtor<Clbss<?>> it = dbndidbtfs.itfrbtor(); it.ibsNfxt();
                    ) {
                    finbl Clbss<?> intf2 = it.nfxt();
                    if (intf != intf2 && intf2.isAssignbblfFrom(intf)) {
                        it.rfmovf();
                        dontinuf rfdudf;
                    }
                }
            }
            finbl String msg =
                "Clbss " + rfsourdfClbss.gftNbmf() + " implfmfnts morf tibn " +
                "onf MXBfbn intfrfbdf: " + dbndidbtfs;
            tirow nfw IllfgblArgumfntExdfption(msg);
        }
        if (dbndidbtfs.itfrbtor().ibsNfxt()) {
            rfturn Util.dbst(dbndidbtfs.itfrbtor().nfxt());
        } flsf {
            finbl String msg =
                "Clbss " + rfsourdfClbss.gftNbmf() +
                " is not b JMX domplibnt MXBfbn";
            tirow nfw IllfgblArgumfntExdfption(msg);
        }
    }

    /* Rfturn bll intfrfbdfs inifritfd by tiis dlbss, dirfdtly or
     * indirfdtly tirougi tif pbrfnt dlbss bnd intfrfbdfs.
     */
    privbtf stbtid Sft<Clbss<?>> trbnsitivfIntfrfbdfs(Clbss<?> d) {
        Sft<Clbss<?>> sft = nfwSft();
        trbnsitivfIntfrfbdfs(d, sft);
        rfturn sft;
    }
    privbtf stbtid void trbnsitivfIntfrfbdfs(Clbss<?> d, Sft<Clbss<?>> intfs) {
        if (d == null)
            rfturn;
        if (d.isIntfrfbdf())
            intfs.bdd(d);
        trbnsitivfIntfrfbdfs(d.gftSupfrdlbss(), intfs);
        for (Clbss<?> sup : d.gftIntfrfbdfs())
            trbnsitivfIntfrfbdfs(sup, intfs);
    }

    /*
     * Tif sfqufndf of fvfnts for trbdking intfr-MXBfbn rfffrfndfs is
     * rflbtivfly domplidbtfd.  Wf usf tif mbgidbl prfRfgistfr2 mftiod
     * wiidi tif MBfbnSfrvfr knows bbout.  Tif stfps during rfgistrbtion
     * brf:
     * (1) Cbll usfr prfRfgistfr, if bny.  If fxdfption, bbbndon.
     * (2) Cbll prfRfgistfr2 bnd ifndf tiis rfgistfr mftiod.  If fxdfption,
     * dbll postRfgistfr(fblsf) bnd bbbndon.
     * (3) Try to rfgistfr tif MBfbn.  If fxdfption, dbll rfgistfrFbilfd()
     * wiidi will dbll tif unrfgistfr mftiod.  (Also dbll postRfgistfr(fblsf).)
     * (4) If wf gft tiis fbr, wf dbn dbll postRfgistfr(truf).
     *
     * Wifn wf brf wrbppfd in bn instbndf of jbvbx.mbnbgfmfnt.StbndbrdMBfbn,
     * tiings brf simplfr.  Tibt dlbss dblls tiis mftiod from its prfRfgistfr,
     * bnd propbgbtfs bny fxdfption.  Tifrf is no usfr prfRfgistfr in tiis dbsf.
     * If tiis mftiod suddffds but rfgistrbtion subsfqufntly fbils,
     * StbndbrdMBfbn dblls unrfgistfr from its postRfgistfr(fblsf) mftiod.
     */
    @Ovfrridf
    publid void rfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
            tirows InstbndfAlrfbdyExistsExdfption {
        if (nbmf == null)
            tirow nfw IllfgblArgumfntExdfption("Null objfdt nbmf");
        // fvfntublly wf dould ibvf somf logid to supply b dffbult nbmf

        syndironizfd (lodk) {
            tiis.mxbfbnLookup = MXBfbnLookup.lookupFor(sfrvfr);
            tiis.mxbfbnLookup.bddRfffrfndf(nbmf, gftRfsourdf());
            tiis.objfdtNbmf = nbmf;
        }
    }

    @Ovfrridf
    publid void unrfgistfr() {
        syndironizfd (lodk) {
            if (mxbfbnLookup != null) {
                if (mxbfbnLookup.rfmovfRfffrfndf(objfdtNbmf, gftRfsourdf()))
                    objfdtNbmf = null;
            }
        }
    }
    privbtf finbl Objfdt lodk = nfw Objfdt(); // for mxbfbnLookup bnd objfdtNbmf

    privbtf MXBfbnLookup mxbfbnLookup;
    privbtf ObjfdtNbmf objfdtNbmf;
}
