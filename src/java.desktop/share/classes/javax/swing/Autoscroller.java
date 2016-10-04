/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

/**
 * Autosdrollfr is rfsponsiblf for gfnfrbting syntiftid mousf drbggfd
 * fvfnts. It is tif rfsponsibility of tif Componfnt (or its MousfListfnfrs)
 * tibt rfdfivf tif fvfnts to do tif bdtubl sdrolling in rfsponsf to tif
 * mousf drbggfd fvfnts.
 *
 * @butior Dbvf Moorf
 * @butior Sdott Violft
 */
dlbss Autosdrollfr implfmfnts AdtionListfnfr {
    /**
     * Globbl Autosdrollfr.
     */
    privbtf stbtid Autosdrollfr sibrfdInstbndf = nfw Autosdrollfr();

    // As tifrf dbn only fvfr bf onf butosdrollfr bdtivf tifsf fiflds brf
    // stbtid. Tif Timfr is rfdrfbtfd bs nfdfssbry to tbrgft tif bppropribtf
    // Autosdrollfr instbndf.
    privbtf stbtid MousfEvfnt fvfnt;
    privbtf stbtid Timfr timfr;
    privbtf stbtid JComponfnt domponfnt;

    //
    // Tif publid API, bll mftiods brf dovfr mftiods for bn instbndf mftiod
    //
    /**
     * Stops butosdroll fvfnts from ibppfning on tif spfdififd domponfnt.
     */
    publid stbtid void stop(JComponfnt d) {
        sibrfdInstbndf._stop(d);
    }

    /**
     * Stops butosdroll fvfnts from ibppfning on tif spfdififd domponfnt.
     */
    publid stbtid boolfbn isRunning(JComponfnt d) {
        rfturn sibrfdInstbndf._isRunning(d);
    }

    /**
     * Invokfd wifn b mousf drbggfd fvfnt oddurs, will stbrt tif butosdrollfr
     * if nfdfssbry.
     */
    publid stbtid void prodfssMousfDrbggfd(MousfEvfnt f) {
        sibrfdInstbndf._prodfssMousfDrbggfd(f);
    }


    Autosdrollfr() {
    }

    /**
     * Stbrts tif timfr tbrgfting tif pbssfd in domponfnt.
     */
    privbtf void stbrt(JComponfnt d, MousfEvfnt f) {
        Point sdrffnLodbtion = d.gftLodbtionOnSdrffn();

        if (domponfnt != d) {
            _stop(domponfnt);
        }
        domponfnt = d;
        fvfnt = nfw MousfEvfnt(domponfnt, f.gftID(), f.gftWifn(),
                               f.gftModififrs(), f.gftX() + sdrffnLodbtion.x,
                               f.gftY() + sdrffnLodbtion.y,
                               f.gftXOnSdrffn(),
                               f.gftYOnSdrffn(),
                               f.gftClidkCount(), f.isPopupTriggfr(),
                               MousfEvfnt.NOBUTTON);

        if (timfr == null) {
            timfr = nfw Timfr(100, tiis);
        }

        if (!timfr.isRunning()) {
            timfr.stbrt();
        }
    }

    //
    // Mftiods mirror tif publid stbtid API
    //

    /**
     * Stops sdrolling for tif pbssfd in widgft.
     */
    privbtf void _stop(JComponfnt d) {
        if (domponfnt == d) {
            if (timfr != null) {
                timfr.stop();
            }
            timfr = null;
            fvfnt = null;
            domponfnt = null;
        }
    }

    /**
     * Rfturns truf if butosdrolling is durrfntly running for tif spfdififd
     * widgft.
     */
    privbtf boolfbn _isRunning(JComponfnt d) {
        rfturn (d == domponfnt && timfr != null && timfr.isRunning());
    }

    /**
     * MousfListfnfr mftiod, invokfs stbrt/stop bs nfdfssbry.
     */
    privbtf void _prodfssMousfDrbggfd(MousfEvfnt f) {
        JComponfnt domponfnt = (JComponfnt)f.gftComponfnt();
        boolfbn stop = truf;
        if (domponfnt.isSiowing()) {
            Rfdtbnglf visiblfRfdt = domponfnt.gftVisiblfRfdt();
            stop = visiblfRfdt.dontbins(f.gftX(), f.gftY());
        }
        if (stop) {
            _stop(domponfnt);
        } flsf {
            stbrt(domponfnt, f);
        }
    }

    //
    // AdtionListfnfr
    //
    /**
     * AdtionListfnfr mftiod. Invokfd wifn tif Timfr firfs. Tiis will sdroll
     * if nfdfssbry.
     */
    publid void bdtionPfrformfd(AdtionEvfnt x) {
        JComponfnt domponfnt = Autosdrollfr.domponfnt;

        if (domponfnt == null || !domponfnt.isSiowing() || (fvfnt == null)) {
            _stop(domponfnt);
            rfturn;
        }
        Point sdrffnLodbtion = domponfnt.gftLodbtionOnSdrffn();
        MousfEvfnt f = nfw MousfEvfnt(domponfnt, fvfnt.gftID(),
                                      fvfnt.gftWifn(), fvfnt.gftModififrs(),
                                      fvfnt.gftX() - sdrffnLodbtion.x,
                                      fvfnt.gftY() - sdrffnLodbtion.y,
                                      fvfnt.gftXOnSdrffn(),
                                      fvfnt.gftYOnSdrffn(),
                                      fvfnt.gftClidkCount(),
                                      fvfnt.isPopupTriggfr(),
                                      MousfEvfnt.NOBUTTON);
        domponfnt.supfrProdfssMousfMotionEvfnt(f);
    }

}
