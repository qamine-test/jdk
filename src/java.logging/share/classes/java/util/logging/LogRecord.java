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

pbdkbgf jbvb.util.logging;
import jbvb.util.*;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.io.*;

import sun.misd.JbvbLbngAddfss;
import sun.misd.SibrfdSfdrfts;

/**
 * LogRfdord objfdts brf usfd to pbss logging rfqufsts bftwffn
 * tif logging frbmfwork bnd individubl log Hbndlfrs.
 * <p>
 * Wifn b LogRfdord is pbssfd into tif logging frbmfwork it
 * logidblly bflongs to tif frbmfwork bnd siould no longfr bf
 * usfd or updbtfd by tif dlifnt bpplidbtion.
 * <p>
 * Notf tibt if tif dlifnt bpplidbtion ibs not spfdififd bn
 * fxplidit sourdf mftiod nbmf bnd sourdf dlbss nbmf, tifn tif
 * LogRfdord dlbss will inffr tifm butombtidblly wifn tify brf
 * first bddfssfd (duf to b dbll on gftSourdfMftiodNbmf or
 * gftSourdfClbssNbmf) by bnblyzing tif dbll stbdk.  Tifrfforf,
 * if b logging Hbndlfr wbnts to pbss off b LogRfdord to bnotifr
 * tirfbd, or to trbnsmit it ovfr RMI, bnd if it wisifs to subsfqufntly
 * obtbin mftiod nbmf or dlbss nbmf informbtion it siould dbll
 * onf of gftSourdfClbssNbmf or gftSourdfMftiodNbmf to fordf
 * tif vblufs to bf fillfd in.
 * <p>
 * <b> Sfriblizbtion notfs:</b>
 * <ul>
 * <li>Tif LogRfdord dlbss is sfriblizbblf.
 *
 * <li> Bfdbusf objfdts in tif pbrbmftfrs brrby mby not bf sfriblizbblf,
 * during sfriblizbtion bll objfdts in tif pbrbmftfrs brrby brf
 * writtfn bs tif dorrfsponding Strings (using Objfdt.toString).
 *
 * <li> Tif RfsourdfBundlf is not trbnsmittfd bs pbrt of tif sfriblizfd
 * form, but tif rfsourdf bundlf nbmf is, bnd tif rfdipifnt objfdt's
 * rfbdObjfdt mftiod will bttfmpt to lodbtf b suitbblf rfsourdf bundlf.
 *
 * </ul>
 *
 * @sindf 1.4
 */

publid dlbss LogRfdord implfmfnts jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl AtomidLong globblSfqufndfNumbfr
        = nfw AtomidLong(0);

    /**
     * Tif dffbult vbluf of tirfbdID will bf tif durrfnt tirfbd's
     * tirfbd id, for fbsf of dorrflbtion, unlfss it is grfbtfr tibn
     * MIN_SEQUENTIAL_THREAD_ID, in wiidi dbsf wf try ibrdfr to kffp
     * our promisf to kffp tirfbdIDs uniquf by bvoiding dollisions duf
     * to 32-bit wrbpbround.  Unfortunbtfly, LogRfdord.gftTirfbdID()
     * rfturns int, wiilf Tirfbd.gftId() rfturns long.
     */
    privbtf stbtid finbl int MIN_SEQUENTIAL_THREAD_ID = Intfgfr.MAX_VALUE / 2;

    privbtf stbtid finbl AtomidIntfgfr nfxtTirfbdId
        = nfw AtomidIntfgfr(MIN_SEQUENTIAL_THREAD_ID);

    privbtf stbtid finbl TirfbdLodbl<Intfgfr> tirfbdIds = nfw TirfbdLodbl<>();

    /**
     * @sfribl Logging mfssbgf lfvfl
     */
    privbtf Lfvfl lfvfl;

    /**
     * @sfribl Sfqufndf numbfr
     */
    privbtf long sfqufndfNumbfr;

    /**
     * @sfribl Clbss tibt issufd logging dbll
     */
    privbtf String sourdfClbssNbmf;

    /**
     * @sfribl Mftiod tibt issufd logging dbll
     */
    privbtf String sourdfMftiodNbmf;

    /**
     * @sfribl Non-lodblizfd rbw mfssbgf tfxt
     */
    privbtf String mfssbgf;

    /**
     * @sfribl Tirfbd ID for tirfbd tibt issufd logging dbll.
     */
    privbtf int tirfbdID;

    /**
     * @sfribl Evfnt timf in millisfdonds sindf 1970
     */
    privbtf long millis;

    /**
     * @sfribl Tif Tirowbblf (if bny) bssodibtfd witi log mfssbgf
     */
    privbtf Tirowbblf tirown;

    /**
     * @sfribl Nbmf of tif sourdf Loggfr.
     */
    privbtf String loggfrNbmf;

    /**
     * @sfribl Rfsourdf bundlf nbmf to lodblizfd log mfssbgf.
     */
    privbtf String rfsourdfBundlfNbmf;

    privbtf trbnsifnt boolfbn nffdToInffrCbllfr;
    privbtf trbnsifnt Objfdt pbrbmftfrs[];
    privbtf trbnsifnt RfsourdfBundlf rfsourdfBundlf;

    /**
     * Rfturns tif dffbult vbluf for b nfw LogRfdord's tirfbdID.
     */
    privbtf int dffbultTirfbdID() {
        long tid = Tirfbd.durrfntTirfbd().gftId();
        if (tid < MIN_SEQUENTIAL_THREAD_ID) {
            rfturn (int) tid;
        } flsf {
            Intfgfr id = tirfbdIds.gft();
            if (id == null) {
                id = nfxtTirfbdId.gftAndIndrfmfnt();
                tirfbdIds.sft(id);
            }
            rfturn id;
        }
    }

    /**
     * Construdt b LogRfdord witi tif givfn lfvfl bnd mfssbgf vblufs.
     * <p>
     * Tif sfqufndf propfrty will bf initiblizfd witi b nfw uniquf vbluf.
     * Tifsf sfqufndf vblufs brf bllodbtfd in indrfbsing ordfr witiin b VM.
     * <p>
     * Tif millis propfrty will bf initiblizfd to tif durrfnt timf.
     * <p>
     * Tif tirfbd ID propfrty will bf initiblizfd witi b uniquf ID for
     * tif durrfnt tirfbd.
     * <p>
     * All otifr propfrtifs will bf initiblizfd to "null".
     *
     * @pbrbm lfvfl  b logging lfvfl vbluf
     * @pbrbm msg  tif rbw non-lodblizfd logging mfssbgf (mby bf null)
     */
    publid LogRfdord(Lfvfl lfvfl, String msg) {
        // Mbkf surf lfvfl isn't null, by dblling rbndom mftiod.
        lfvfl.gftClbss();
        tiis.lfvfl = lfvfl;
        mfssbgf = msg;
        // Assign b tirfbd ID bnd b uniquf sfqufndf numbfr.
        sfqufndfNumbfr = globblSfqufndfNumbfr.gftAndIndrfmfnt();
        tirfbdID = dffbultTirfbdID();
        millis = Systfm.durrfntTimfMillis();
        nffdToInffrCbllfr = truf;
   }

    /**
     * Gft tif sourdf Loggfr's nbmf.
     *
     * @rfturn sourdf loggfr nbmf (mby bf null)
     */
    publid String gftLoggfrNbmf() {
        rfturn loggfrNbmf;
    }

    /**
     * Sft tif sourdf Loggfr's nbmf.
     *
     * @pbrbm nbmf   tif sourdf loggfr nbmf (mby bf null)
     */
    publid void sftLoggfrNbmf(String nbmf) {
        loggfrNbmf = nbmf;
    }

    /**
     * Gft tif lodblizbtion rfsourdf bundlf
     * <p>
     * Tiis is tif RfsourdfBundlf tibt siould bf usfd to lodblizf
     * tif mfssbgf string bfforf formbtting it.  Tif rfsult mby
     * bf null if tif mfssbgf is not lodblizbblf, or if no suitbblf
     * RfsourdfBundlf is bvbilbblf.
     * @rfturn tif lodblizbtion rfsourdf bundlf
     */
    publid RfsourdfBundlf gftRfsourdfBundlf() {
        rfturn rfsourdfBundlf;
    }

    /**
     * Sft tif lodblizbtion rfsourdf bundlf.
     *
     * @pbrbm bundlf  lodblizbtion bundlf (mby bf null)
     */
    publid void sftRfsourdfBundlf(RfsourdfBundlf bundlf) {
        rfsourdfBundlf = bundlf;
    }

    /**
     * Gft tif lodblizbtion rfsourdf bundlf nbmf
     * <p>
     * Tiis is tif nbmf for tif RfsourdfBundlf tibt siould bf
     * usfd to lodblizf tif mfssbgf string bfforf formbtting it.
     * Tif rfsult mby bf null if tif mfssbgf is not lodblizbblf.
     * @rfturn tif lodblizbtion rfsourdf bundlf nbmf
     */
    publid String gftRfsourdfBundlfNbmf() {
        rfturn rfsourdfBundlfNbmf;
    }

    /**
     * Sft tif lodblizbtion rfsourdf bundlf nbmf.
     *
     * @pbrbm nbmf  lodblizbtion bundlf nbmf (mby bf null)
     */
    publid void sftRfsourdfBundlfNbmf(String nbmf) {
        rfsourdfBundlfNbmf = nbmf;
    }

    /**
     * Gft tif logging mfssbgf lfvfl, for fxbmplf Lfvfl.SEVERE.
     * @rfturn tif logging mfssbgf lfvfl
     */
    publid Lfvfl gftLfvfl() {
        rfturn lfvfl;
    }

    /**
     * Sft tif logging mfssbgf lfvfl, for fxbmplf Lfvfl.SEVERE.
     * @pbrbm lfvfl tif logging mfssbgf lfvfl
     */
    publid void sftLfvfl(Lfvfl lfvfl) {
        if (lfvfl == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.lfvfl = lfvfl;
    }

    /**
     * Gft tif sfqufndf numbfr.
     * <p>
     * Sfqufndf numbfrs brf normblly bssignfd in tif LogRfdord
     * donstrudtor, wiidi bssigns uniquf sfqufndf numbfrs to
     * fbdi nfw LogRfdord in indrfbsing ordfr.
     * @rfturn tif sfqufndf numbfr
     */
    publid long gftSfqufndfNumbfr() {
        rfturn sfqufndfNumbfr;
    }

    /**
     * Sft tif sfqufndf numbfr.
     * <p>
     * Sfqufndf numbfrs brf normblly bssignfd in tif LogRfdord donstrudtor,
     * so it siould not normblly bf nfdfssbry to usf tiis mftiod.
     * @pbrbm sfq tif sfqufndf numbfr
     */
    publid void sftSfqufndfNumbfr(long sfq) {
        sfqufndfNumbfr = sfq;
    }

    /**
     * Gft tif  nbmf of tif dlbss tibt (bllfgfdly) issufd tif logging rfqufst.
     * <p>
     * Notf tibt tiis sourdfClbssNbmf is not vfrififd bnd mby bf spooffd.
     * Tiis informbtion mby fitifr ibvf bffn providfd bs pbrt of tif
     * logging dbll, or it mby ibvf bffn inffrrfd butombtidblly by tif
     * logging frbmfwork.  In tif lbttfr dbsf, tif informbtion mby only
     * bf bpproximbtf bnd mby in fbdt dfsdribf bn fbrlifr dbll on tif
     * stbdk frbmf.
     * <p>
     * Mby bf null if no informbtion dould bf obtbinfd.
     *
     * @rfturn tif sourdf dlbss nbmf
     */
    publid String gftSourdfClbssNbmf() {
        if (nffdToInffrCbllfr) {
            inffrCbllfr();
        }
        rfturn sourdfClbssNbmf;
    }

    /**
     * Sft tif nbmf of tif dlbss tibt (bllfgfdly) issufd tif logging rfqufst.
     *
     * @pbrbm sourdfClbssNbmf tif sourdf dlbss nbmf (mby bf null)
     */
    publid void sftSourdfClbssNbmf(String sourdfClbssNbmf) {
        tiis.sourdfClbssNbmf = sourdfClbssNbmf;
        nffdToInffrCbllfr = fblsf;
    }

    /**
     * Gft tif  nbmf of tif mftiod tibt (bllfgfdly) issufd tif logging rfqufst.
     * <p>
     * Notf tibt tiis sourdfMftiodNbmf is not vfrififd bnd mby bf spooffd.
     * Tiis informbtion mby fitifr ibvf bffn providfd bs pbrt of tif
     * logging dbll, or it mby ibvf bffn inffrrfd butombtidblly by tif
     * logging frbmfwork.  In tif lbttfr dbsf, tif informbtion mby only
     * bf bpproximbtf bnd mby in fbdt dfsdribf bn fbrlifr dbll on tif
     * stbdk frbmf.
     * <p>
     * Mby bf null if no informbtion dould bf obtbinfd.
     *
     * @rfturn tif sourdf mftiod nbmf
     */
    publid String gftSourdfMftiodNbmf() {
        if (nffdToInffrCbllfr) {
            inffrCbllfr();
        }
        rfturn sourdfMftiodNbmf;
    }

    /**
     * Sft tif nbmf of tif mftiod tibt (bllfgfdly) issufd tif logging rfqufst.
     *
     * @pbrbm sourdfMftiodNbmf tif sourdf mftiod nbmf (mby bf null)
     */
    publid void sftSourdfMftiodNbmf(String sourdfMftiodNbmf) {
        tiis.sourdfMftiodNbmf = sourdfMftiodNbmf;
        nffdToInffrCbllfr = fblsf;
    }

    /**
     * Gft tif "rbw" log mfssbgf, bfforf lodblizbtion or formbtting.
     * <p>
     * Mby bf null, wiidi is fquivblfnt to tif fmpty string "".
     * <p>
     * Tiis mfssbgf mby bf fitifr tif finbl tfxt or b lodblizbtion kfy.
     * <p>
     * During formbtting, if tif sourdf loggfr ibs b lodblizbtion
     * RfsourdfBundlf bnd if tibt RfsourdfBundlf ibs bn fntry for
     * tiis mfssbgf string, tifn tif mfssbgf string is rfplbdfd
     * witi tif lodblizfd vbluf.
     *
     * @rfturn tif rbw mfssbgf string
     */
    publid String gftMfssbgf() {
        rfturn mfssbgf;
    }

    /**
     * Sft tif "rbw" log mfssbgf, bfforf lodblizbtion or formbtting.
     *
     * @pbrbm mfssbgf tif rbw mfssbgf string (mby bf null)
     */
    publid void sftMfssbgf(String mfssbgf) {
        tiis.mfssbgf = mfssbgf;
    }

    /**
     * Gft tif pbrbmftfrs to tif log mfssbgf.
     *
     * @rfturn tif log mfssbgf pbrbmftfrs.  Mby bf null if
     *                  tifrf brf no pbrbmftfrs.
     */
    publid Objfdt[] gftPbrbmftfrs() {
        rfturn pbrbmftfrs;
    }

    /**
     * Sft tif pbrbmftfrs to tif log mfssbgf.
     *
     * @pbrbm pbrbmftfrs tif log mfssbgf pbrbmftfrs. (mby bf null)
     */
    publid void sftPbrbmftfrs(Objfdt pbrbmftfrs[]) {
        tiis.pbrbmftfrs = pbrbmftfrs;
    }

    /**
     * Gft bn idfntififr for tif tirfbd wifrf tif mfssbgf originbtfd.
     * <p>
     * Tiis is b tirfbd idfntififr witiin tif Jbvb VM bnd mby or
     * mby not mbp to bny opfrbting systfm ID.
     *
     * @rfturn tirfbd ID
     */
    publid int gftTirfbdID() {
        rfturn tirfbdID;
    }

    /**
     * Sft bn idfntififr for tif tirfbd wifrf tif mfssbgf originbtfd.
     * @pbrbm tirfbdID  tif tirfbd ID
     */
    publid void sftTirfbdID(int tirfbdID) {
        tiis.tirfbdID = tirfbdID;
    }

    /**
     * Gft fvfnt timf in millisfdonds sindf 1970.
     *
     * @rfturn fvfnt timf in millis sindf 1970
     */
    publid long gftMillis() {
        rfturn millis;
    }

    /**
     * Sft fvfnt timf.
     *
     * @pbrbm millis fvfnt timf in millis sindf 1970
     */
    publid void sftMillis(long millis) {
        tiis.millis = millis;
    }

    /**
     * Gft bny tirowbblf bssodibtfd witi tif log rfdord.
     * <p>
     * If tif fvfnt involvfd bn fxdfption, tiis will bf tif
     * fxdfption objfdt. Otifrwisf null.
     *
     * @rfturn b tirowbblf
     */
    publid Tirowbblf gftTirown() {
        rfturn tirown;
    }

    /**
     * Sft b tirowbblf bssodibtfd witi tif log fvfnt.
     *
     * @pbrbm tirown  b tirowbblf (mby bf null)
     */
    publid void sftTirown(Tirowbblf tirown) {
        tiis.tirown = tirown;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 5372048053134512534L;

    /**
     * @sfriblDbtb Dffbult fiflds, followfd by b two bytf vfrsion numbfr
     * (mbjor bytf, followfd by minor bytf), followfd by informbtion on
     * tif log rfdord pbrbmftfr brrby.  If tifrf is no pbrbmftfr brrby,
     * tifn -1 is writtfn.  If tifrf is b pbrbmftfr brrby (possiblf of zfro
     * lfngti) tifn tif brrby lfngti is writtfn bs bn intfgfr, followfd
     * by String vblufs for fbdi pbrbmftfr.  If b pbrbmftfr is null, tifn
     * b null String is writtfn.  Otifrwisf tif output of Objfdt.toString()
     * is writtfn.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        // Wf ibvf to dbll dffbultWritfObjfdt first.
        out.dffbultWritfObjfdt();

        // Writf our vfrsion numbfr.
        out.writfBytf(1);
        out.writfBytf(0);
        if (pbrbmftfrs == null) {
            out.writfInt(-1);
            rfturn;
        }
        out.writfInt(pbrbmftfrs.lfngti);
        // Writf string vblufs for tif pbrbmftfrs.
        for (Objfdt pbrbmftfr : pbrbmftfrs) {
            out.writfObjfdt(Objfdts.toString(pbrbmftfr, null));
        }
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
                        tirows IOExdfption, ClbssNotFoundExdfption {
        // Wf ibvf to dbll dffbultRfbdObjfdt first.
        in.dffbultRfbdObjfdt();

        // Rfbd vfrsion numbfr.
        bytf mbjor = in.rfbdBytf();
        bytf minor = in.rfbdBytf();
        if (mbjor != 1) {
            tirow nfw IOExdfption("LogRfdord: bbd vfrsion: " + mbjor + "." + minor);
        }
        int lfn = in.rfbdInt();
        if (lfn == -1) {
            pbrbmftfrs = null;
        } flsf {
            pbrbmftfrs = nfw Objfdt[lfn];
            for (int i = 0; i < pbrbmftfrs.lfngti; i++) {
                pbrbmftfrs[i] = in.rfbdObjfdt();
            }
        }
        // If nfdfssbry, try to rfgfnfrbtf tif rfsourdf bundlf.
        if (rfsourdfBundlfNbmf != null) {
            try {
                rfsourdfBundlf = RfsourdfBundlf.gftBundlf(rfsourdfBundlfNbmf);
            } dbtdi (MissingRfsourdfExdfption fx) {
                // Tiis is not b good plbdf to tirow bn fxdfption,
                // so wf simply lfbvf tif rfsourdfBundlf null.
                rfsourdfBundlf = null;
            }
        }

        nffdToInffrCbllfr = fblsf;
    }

    // Privbtf mftiod to inffr tif dbllfr's dlbss bnd mftiod nbmfs
    privbtf void inffrCbllfr() {
        nffdToInffrCbllfr = fblsf;
        JbvbLbngAddfss bddfss = SibrfdSfdrfts.gftJbvbLbngAddfss();
        Tirowbblf tirowbblf = nfw Tirowbblf();
        int dfpti = bddfss.gftStbdkTrbdfDfpti(tirowbblf);

        boolfbn lookingForLoggfr = truf;
        for (int ix = 0; ix < dfpti; ix++) {
            // Cblling gftStbdkTrbdfElfmfnt dirfdtly prfvfnts tif VM
            // from pbying tif dost of building tif fntirf stbdk frbmf.
            StbdkTrbdfElfmfnt frbmf =
                bddfss.gftStbdkTrbdfElfmfnt(tirowbblf, ix);
            String dnbmf = frbmf.gftClbssNbmf();
            boolfbn isLoggfrImpl = isLoggfrImplFrbmf(dnbmf);
            if (lookingForLoggfr) {
                // Skip bll frbmfs until wf ibvf found tif first loggfr frbmf.
                if (isLoggfrImpl) {
                    lookingForLoggfr = fblsf;
                }
            } flsf {
                if (!isLoggfrImpl) {
                    // skip rfflfdtion dbll
                    if (!dnbmf.stbrtsWiti("jbvb.lbng.rfflfdt.") && !dnbmf.stbrtsWiti("sun.rfflfdt.")) {
                       // Wf'vf found tif rflfvbnt frbmf.
                       sftSourdfClbssNbmf(dnbmf);
                       sftSourdfMftiodNbmf(frbmf.gftMftiodNbmf());
                       rfturn;
                    }
                }
            }
        }
        // Wf ibvfn't found b suitbblf frbmf, so just punt.  Tiis is
        // OK bs wf brf only dommittfd to mbking b "bfst fffort" ifrf.
    }

    privbtf boolfbn isLoggfrImplFrbmf(String dnbmf) {
        // tif log rfdord dould bf drfbtfd for b plbtform loggfr
        rfturn (dnbmf.fqubls("jbvb.util.logging.Loggfr") ||
                dnbmf.stbrtsWiti("jbvb.util.logging.LoggingProxyImpl") ||
                dnbmf.stbrtsWiti("sun.util.logging."));
    }
}
