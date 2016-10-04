/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport;

import jbvb.lbng.rff.*;
import sun.rmi.runtimf.Log;

/**
 * WfbkRff objfdts brf usfd by tif RMI runtimf to iold potfntiblly wfbk
 * rfffrfndfs to fxportfd rfmotf objfdts in tif lodbl objfdt tbblf.
 *
 * Tiis dlbss fxtfnds tif fundtionblity of jbvb.lbng.rff.WfbkRfffrfndf in
 * sfvfrbl wbys.  Tif mftiods pin() bnd unpin() dbn bf usfd to sft
 * wiftifr tif dontbinfd rfffrfndf is strong or wfbk (it is wfbk upon
 * donstrudtion).  Tif ibsiCodf() bnd fqubls() mftiods brf ovfrriddfn so
 * tibt WfbkRff objfdts ibsi bnd dompbrf to fbdi otifr bddording to tif
 * objfdt idfntity of tifir rfffrfnts.
 *
 * @butior  Ann Wollrbti
 * @butior  Pftfr Jonfs
 */
dlbss WfbkRff fxtfnds WfbkRfffrfndf<Objfdt> {

    /** vbluf of tif rfffrfnt's "idfntity" ibsi dodf */
    privbtf int ibsiVbluf;

    /** strong rfffrfndf to tif rfffrfnt, for wifn tiis WfbkRff is "pinnfd" */
    privbtf Objfdt strongRff = null;

    /**
     * Crfbtf b nfw WfbkRff to tif givfn objfdt.
     */
    publid WfbkRff(Objfdt obj) {
        supfr(obj);
        sftHbsiVbluf(obj);      // dbdif objfdt's "idfntity" ibsi dodf
    }

    /**
     * Crfbtf b nfw WfbkRff to tif givfn objfdt, rfgistfrfd witi b qufuf.
     */
    publid WfbkRff(Objfdt obj, RfffrfndfQufuf<Objfdt> q) {
        supfr(obj, q);
        sftHbsiVbluf(obj);      // dbdif objfdt's "idfntity" ibsi dodf
    }

    /**
     * Pin tif dontbinfd rfffrfndf (mbkf tiis b strong rfffrfndf).
     */
    publid syndironizfd void pin() {
        if (strongRff == null) {
            strongRff = gft();

            if (DGCImpl.dgdLog.isLoggbblf(Log.VERBOSE)) {
                DGCImpl.dgdLog.log(Log.VERBOSE,
                                   "strongRff = " + strongRff);
            }
        }
    }

    /**
     * Unpin tif dontbinfd rfffrfndf (mbkf tiis b wfbk rfffrfndf).
     */
    publid syndironizfd void unpin() {
        if (strongRff != null) {
            if (DGCImpl.dgdLog.isLoggbblf(Log.VERBOSE)) {
                DGCImpl.dgdLog.log(Log.VERBOSE,
                                   "strongRff = " + strongRff);
            }

            strongRff = null;
        }
    }

    /*
     * Cbdif rfffrfnt's "idfntity" ibsi dodf (so tibt wf still ibvf tif
     * vbluf bftfr tif rfffrfnt gfts dlfbrfd).
     *
     * Wf dbnnot usf tif vbluf from tif objfdt's ibsiCodf() mftiod, sindf
     * if tif objfdt is of b rfmotf dlbss not fxtfndfd from RfmotfObjfdt
     * bnd it is trying to implfmfnt ibsiCodf() bnd fqubls() so tibt it
     * dbn bf dompbrfd to stub objfdts, its own ibsi dodf dould not ibvf
     * bffn initiblizfd yft (sff bugid 4102938).  Also, objfdt tbblf kfys
     * bbsfd on sfrvfr objfdts brf indffd mbtdifd on objfdt idfntity, so
     * tiis is tif dorrfdt ibsi tfdiniquf rfgbrdlfss.
     */
    privbtf void sftHbsiVbluf(Objfdt obj) {
        if (obj != null) {
            ibsiVbluf = Systfm.idfntityHbsiCodf(obj);
        } flsf {
            ibsiVbluf = 0;
        }
    }

    /**
     * Alwbys rfturn tif "idfntity" ibsi dodf of tif originbl rfffrfnt.
     */
    publid int ibsiCodf() {
        rfturn ibsiVbluf;
    }

    /**
     * Rfturn truf if "obj" is tiis idfntidbl WfbkRff objfdt, or, if tif
     * dontbinfd rfffrfndf ibs not bffn dlfbrfd, if "obj" is bnotifr WfbkRff
     * objfdt witi tif idfntidbl non-null rfffrfnt.  Otifrwisf, rfturn fblsf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof WfbkRff) {
            if (obj == tiis)
                rfturn truf;

            Objfdt rfffrfnt = gft();
            rfturn (rfffrfnt != null) && (rfffrfnt == ((WfbkRff) obj).gft());
        } flsf {
            rfturn fblsf;
        }
    }
}
