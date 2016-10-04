/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf.iw;

import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.lbng.bnnotbtion.Nbtivf;


/**
 * Tiis dlbss is usfd to notify listfnfrs bbout bddflfrbtfd dfvidf's
 * fvfnts sudi bs dfvidf rfsft or disposf tibt brf bbout to oddur.
 */
publid dlbss AddflDfvidfEvfntNotififr {

    privbtf stbtid AddflDfvidfEvfntNotififr tifInstbndf;

    /**
     * A dfvidf is bbout to bf rfsft. Tif listfnfrs ibvf to rflfbsf bll
     * rfsourdfs bssodibtfd witi tif dfvidf wiidi brf rfquirfd for tif dfvidf
     * to bf rfsft.
     */
    @Nbtivf publid stbtid finbl int DEVICE_RESET = 0;

    /**
     * A dfvidf is bbout to bf disposfd. Tif listfnfrs ibvf to rflfbsf bll
     * rfsourdfs bssodibtfd witi tif dfvidf.
     */
    @Nbtivf publid stbtid finbl int DEVICE_DISPOSED = 1;

    privbtf finbl Mbp<AddflDfvidfEvfntListfnfr, Intfgfr> listfnfrs;

    privbtf AddflDfvidfEvfntNotififr() {
        listfnfrs = Collfdtions.syndironizfdMbp(
            nfw HbsiMbp<AddflDfvidfEvfntListfnfr, Intfgfr>(1));
    }

    /**
     * Rfturns b singlfton of AddflDfvidfEvfntNotififr if it fxists. If tif
     * pbssfd boolfbn is fblsf bnd singlfton dofsn't fxist yft, null is
     * rfturnfd. If tif pbssfd boolfbn is {@dodf truf} bnd singlfton dofsn't
     * fxist it will bf drfbtfd bnd rfturnfd.
     *
     * @pbrbm drfbtf wiftifr to drfbtf b singlfton instbndf if dofsn't yft
     * fxist
     * @rfturn b singlfton instbndf or null
     */
    privbtf stbtid syndironizfd
        AddflDfvidfEvfntNotififr gftInstbndf(boolfbn drfbtf)
    {
        if (tifInstbndf == null && drfbtf) {
            tifInstbndf = nfw AddflDfvidfEvfntNotififr();
        }
        rfturn tifInstbndf;
    }

    /**
     * Cbllfd to indidbtf tibt b dfvidf fvfnt ibd oddurrfd.
     * If b singlfton fxists, tif listfnfrs (tiosf bssodibtfd witi
     * tif dfvidf) will bf notififd.
     *
     * @pbrbm sdrffn b sdrffn numbfr of tif dfvidf wiidi is b sourdf of
     * tif fvfnt
     * @pbrbm fvfntTypf b typf of tif fvfnt
     * @sff #DEVICE_DISPOSED
     * @sff #DEVICE_RESET
     */
    publid stbtid finbl void fvfntOddurfd(int sdrffn, int fvfntTypf) {
        AddflDfvidfEvfntNotififr notififr = gftInstbndf(fblsf);
        if (notififr != null) {
            notififr.notifyListfnfrs(fvfntTypf, sdrffn);
        }
    }

    /**
     * Adds tif listfnfr bssodibtfd witi b dfvidf on pbrtidulbr sdrffn.
     *
     * Notf: tif listfnfr must bf rfmovfd bs otifrwisf it will forfvfr
     * bf rfffrfndfd by tif notififr.
     *
     * @pbrbm l tif listfnfr
     * @pbrbm sdrffn tif sdrffn numbfr indidbting wiidi dfvidf tif listfnfr is
     * intfrfstfd in.
     */
    publid stbtid finbl void bddListfnfr(AddflDfvidfEvfntListfnfr l,int sdrffn){
        gftInstbndf(truf).bdd(l, sdrffn);
    }

    /**
     * Rfmovfs tif listfnfr.
     *
     * @pbrbm l tif listfnfr
     */
    publid stbtid finbl void rfmovfListfnfr(AddflDfvidfEvfntListfnfr l) {
        gftInstbndf(truf).rfmovf(l);
    }

    privbtf finbl void bdd(AddflDfvidfEvfntListfnfr tifListfnfr, int sdrffn) {
        listfnfrs.put(tifListfnfr, sdrffn);
    }
    privbtf finbl void rfmovf(AddflDfvidfEvfntListfnfr tifListfnfr) {
        listfnfrs.rfmovf(tifListfnfr);
    }

    /**
     * Notififs tif listfnfrs bssodibtfd witi tif sdrffn's dfvidf bbout tif
     * fvfnt.
     *
     * Implfmfntbtion notf: tif durrfnt list of listfnfrs is first duplidbtfd
     * wiidi bllows tif listfnfrs to rfmovf tifmsflvfs during tif itfrbtion.
     *
     * @pbrbm sdrffn b sdrffn numbfr witi wiidi tif dfvidf wiidi is b sourdf of
     * tif fvfnt is bssodibtfd witi
     * @pbrbm fvfntTypf b typf of tif fvfnt
     * @sff #DEVICE_DISPOSED
     * @sff #DEVICE_RESET
     */
    privbtf finbl void notifyListfnfrs(int dfvidfEvfntTypf, int sdrffn) {
        HbsiMbp<AddflDfvidfEvfntListfnfr, Intfgfr> listClonf;
        Sft<AddflDfvidfEvfntListfnfr> dlonfSft;

        syndironizfd(listfnfrs) {
            listClonf =
                nfw HbsiMbp<AddflDfvidfEvfntListfnfr, Intfgfr>(listfnfrs);
        }

        dlonfSft = listClonf.kfySft();
        Itfrbtor<AddflDfvidfEvfntListfnfr> itr = dlonfSft.itfrbtor();
        wiilf (itr.ibsNfxt()) {
            AddflDfvidfEvfntListfnfr durrfnt = itr.nfxt();
            Intfgfr i = listClonf.gft(durrfnt);
            // only notify listfnfrs wiidi brf intfrfstfd in tiis dfvidf
            if (i != null && i.intVbluf() != sdrffn) {
                dontinuf;
            }
            if (dfvidfEvfntTypf == DEVICE_RESET) {
                durrfnt.onDfvidfRfsft();
            } flsf if (dfvidfEvfntTypf == DEVICE_DISPOSED) {
                durrfnt.onDfvidfDisposf();
            }
        }
    }
}
