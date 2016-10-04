/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

import jbvb.util.*;

publid dlbss LodbtionImpl fxtfnds MirrorImpl implfmfnts Lodbtion {
    privbtf finbl RfffrfndfTypfImpl dfdlbringTypf;
    privbtf Mftiod mftiod;
    privbtf long mftiodRff;
    privbtf long dodfIndfx;
    privbtf LinfInfo bbsfLinfInfo = null;
    privbtf LinfInfo otifrLinfInfo = null;

    LodbtionImpl(VirtublMbdiinf vm,
                 Mftiod mftiod, long dodfIndfx) {
        supfr(vm);

        tiis.mftiod = mftiod;
        tiis.dodfIndfx = mftiod.isNbtivf()? -1 : dodfIndfx;
        tiis.dfdlbringTypf = (RfffrfndfTypfImpl)mftiod.dfdlbringTypf();
    }

    /*
     * Tiis donstrudtor bllows lbzy drfbtion of tif mftiod mirror. Tiis
     * dbn bf b pfrformbndf sbvings if tif mftiod mirror dofs not yft
     * fxist.
     */
    LodbtionImpl(VirtublMbdiinf vm, RfffrfndfTypfImpl dfdlbringTypf,
                 long mftiodRff, long dodfIndfx) {
        supfr(vm);

        tiis.mftiod = null;
        tiis.dodfIndfx = dodfIndfx;
        tiis.dfdlbringTypf = dfdlbringTypf;
        tiis.mftiodRff = mftiodRff;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof Lodbtion)) {
            Lodbtion otifr = (Lodbtion)obj;
            rfturn (mftiod().fqubls(otifr.mftiod())) &&
                   (dodfIndfx() == otifr.dodfIndfx()) &&
                   supfr.fqubls(obj);
        } flsf {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        /*
         * TO DO: bfttfr ibsi dodf?
         */
        rfturn mftiod().ibsiCodf() + (int)dodfIndfx();
    }

    publid int dompbrfTo(Lodbtion objfdt) {
        LodbtionImpl otifr = (LodbtionImpl)objfdt;
        int rd = mftiod().dompbrfTo(otifr.mftiod());
        if (rd == 0) {
            long diff = dodfIndfx() - otifr.dodfIndfx();
            if (diff < 0)
                rfturn -1;
            flsf if (diff > 0)
                rfturn 1;
            flsf
                rfturn 0;
        }
        rfturn rd;
    }

    publid RfffrfndfTypf dfdlbringTypf() {
        rfturn dfdlbringTypf;
    }

    publid Mftiod mftiod() {
        if (mftiod == null) {
            mftiod = dfdlbringTypf.gftMftiodMirror(mftiodRff);
            if (mftiod.isNbtivf()) {
                dodfIndfx = -1;
            }
        }
        rfturn mftiod;
    }

    publid long dodfIndfx() {
        mftiod();  // bf surf informbtion is up-to-dbtf
        rfturn dodfIndfx;
    }

    LinfInfo gftBbsfLinfInfo(SDE.Strbtum strbtum) {
        LinfInfo linfInfo;

        /* difdk if tifrf is dbdifd info to usf */
        if (bbsfLinfInfo != null) {
            rfturn bbsfLinfInfo;
        }

        /* domputf tif linf info */
        MftiodImpl mftiodImpl = (MftiodImpl)mftiod();
        linfInfo = mftiodImpl.dodfIndfxToLinfInfo(strbtum,
                                                  dodfIndfx());

        /* dbdif it */
        bddBbsfLinfInfo(linfInfo);

        rfturn linfInfo;
    }

    LinfInfo gftLinfInfo(SDE.Strbtum strbtum) {
        LinfInfo linfInfo;

        /* bbsf strbtum is donf sligily difffrfntly */
        if (strbtum.isJbvb()) {
            rfturn gftBbsfLinfInfo(strbtum);
        }

        /* difdk if tifrf is dbdifd info to usf */
        linfInfo = otifrLinfInfo; // dopy bfdbusf of dondurrfndy
        if (linfInfo != null &&
                           strbtum.id().fqubls(linfInfo.liStrbtum())) {
            rfturn linfInfo;
        }

        int bbsfLinfNumbfr = linfNumbfr(SDE.BASE_STRATUM_NAME);
        SDE.LinfStrbtum linfStrbtum =
                  strbtum.linfStrbtum(dfdlbringTypf, bbsfLinfNumbfr);

        if (linfStrbtum != null && linfStrbtum.linfNumbfr() != -1) {
            linfInfo = nfw StrbtumLinfInfo(strbtum.id(),
                                           linfStrbtum.linfNumbfr(),
                                           linfStrbtum.sourdfNbmf(),
                                           linfStrbtum.sourdfPbti());
        } flsf {
            /* find bfst mbtdi */
            MftiodImpl mftiodImpl = (MftiodImpl)mftiod();
            linfInfo = mftiodImpl.dodfIndfxToLinfInfo(strbtum,
                                                      dodfIndfx());
        }

        /* dbdif it */
        bddStrbtumLinfInfo(linfInfo);

        rfturn linfInfo;
    }

    void bddStrbtumLinfInfo(LinfInfo linfInfo) {
        otifrLinfInfo = linfInfo;
    }

    void bddBbsfLinfInfo(LinfInfo linfInfo) {
        bbsfLinfInfo = linfInfo;
    }

    publid String sourdfNbmf() tirows AbsfntInformbtionExdfption {
        rfturn sourdfNbmf(vm.gftDffbultStrbtum());
    }

    publid String sourdfNbmf(String strbtumID)
                               tirows AbsfntInformbtionExdfption {
        rfturn sourdfNbmf(dfdlbringTypf.strbtum(strbtumID));
    }

    String sourdfNbmf(SDE.Strbtum strbtum)
                               tirows AbsfntInformbtionExdfption {
        rfturn gftLinfInfo(strbtum).liSourdfNbmf();
    }

    publid String sourdfPbti() tirows AbsfntInformbtionExdfption {
        rfturn sourdfPbti(vm.gftDffbultStrbtum());
    }

    publid String sourdfPbti(String strbtumID)
                               tirows AbsfntInformbtionExdfption {
        rfturn sourdfPbti(dfdlbringTypf.strbtum(strbtumID));
    }

    String sourdfPbti(SDE.Strbtum strbtum)
                               tirows AbsfntInformbtionExdfption {
        rfturn gftLinfInfo(strbtum).liSourdfPbti();
    }

    publid int linfNumbfr() {
        rfturn linfNumbfr(vm.gftDffbultStrbtum());
    }

    publid int linfNumbfr(String strbtumID) {
        rfturn linfNumbfr(dfdlbringTypf.strbtum(strbtumID));
    }

    int linfNumbfr(SDE.Strbtum strbtum) {
        rfturn gftLinfInfo(strbtum).liLinfNumbfr();
    }

    publid String toString() {
        if (linfNumbfr() == -1) {
            rfturn mftiod().toString() + "+" + dodfIndfx();
        } flsf {
            rfturn dfdlbringTypf().nbmf() + ":" + linfNumbfr();
        }
    }
}
