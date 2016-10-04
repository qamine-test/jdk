/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.sfdurity.*;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.io.Sfriblizbblf;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtInputStrfbm.GftFifld;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm.PutFifld;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss dontbins CryptoPfrmission objfdts, orgbnizfd into
 * PfrmissionCollfdtions bddording to blgoritim nbmfs.
 *
 * <p>Wifn tif <dodf>bdd</dodf> mftiod is dbllfd to bdd b
 * CryptoPfrmission, tif CryptoPfrmission is storfd in tif
 * bppropribtf PfrmissionCollfdtion. If no sudi
 * dollfdtion fxists yft, tif blgoritim nbmf bssodibtfd witi
 * tif CryptoPfrmission objfdt is
 * dftfrminfd bnd tif <dodf>nfwPfrmissionCollfdtion</dodf> mftiod
 * is dbllfd on tif CryptoPfrmission or CryptoAllPfrmission dlbss to
 * drfbtf tif PfrmissionCollfdtion bnd bdd it to tif Pfrmissions objfdt.
 *
 * @sff jbvbx.drypto.CryptoPfrmission
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.sfdurity.Pfrmissions
 *
 * @butior Sibron Liu
 * @sindf 1.4
 */
finbl dlbss CryptoPfrmissions fxtfnds PfrmissionCollfdtion
implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 4946547168093391015L;

    /**
     * @sfriblFifld pfrms jbvb.util.Hbsitbblf
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("pfrms", Hbsitbblf.dlbss),
    };

    // Switdifd from Hbsitbblf to CondurrfntHbsiMbp to improvf sdblbbility.
    // To mbintbin sfriblizbtion dompbtibility, tiis fifld is mbdf trbnsifnt
    // bnd dustom rfbdObjfdt/writfObjfdt mftiods brf usfd.
    privbtf trbnsifnt CondurrfntHbsiMbp<String,PfrmissionCollfdtion> pfrms;

    /**
     * Crfbtfs b nfw CryptoPfrmissions objfdt dontbining
     * no CryptoPfrmissionCollfdtions.
     */
    CryptoPfrmissions() {
        pfrms = nfw CondurrfntHbsiMbp<>(7);
    }

    /**
     * Populbtfs tif drypto polidy from tif spfdififd
     * InputStrfbm into tiis CryptoPfrmissions objfdt.
     *
     * @pbrbm in tif InputStrfbm to lobd from.
     *
     * @fxdfption SfdurityExdfption if dbnnot lobd
     * suddfssfully.
     */
    void lobd(InputStrfbm in)
        tirows IOExdfption, CryptoPolidyPbrsfr.PbrsingExdfption {
        CryptoPolidyPbrsfr pbrsfr = nfw CryptoPolidyPbrsfr();
        pbrsfr.rfbd(nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(in, "UTF-8")));

        CryptoPfrmission[] pbrsingRfsult = pbrsfr.gftPfrmissions();
        for (int i = 0; i < pbrsingRfsult.lfngti; i++) {
            tiis.bdd(pbrsingRfsult[i]);
        }
    }

    /**
     * Rfturns truf if tiis CryptoPfrmissions objfdt dofsn't
     * dontbin bny CryptoPfrmission objfdts; otifrwisf, rfturns
     * fblsf.
     */
    boolfbn isEmpty() {
        rfturn pfrms.isEmpty();
    }

    /**
     * Adds b pfrmission objfdt to tif PfrmissionCollfdtion for tif
     * blgoritim rfturnfd by
     * <dodf>(CryptoPfrmission)pfrmission.gftAlgoritim()</dodf>.
     *
     * Tiis mftiod drfbtfs
     * b nfw PfrmissionCollfdtion objfdt (bnd bdds tif pfrmission to it)
     * if bn bppropribtf dollfdtion dofs not yft fxist. <p>
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
     *
     * @fxdfption SfdurityExdfption if tiis CryptoPfrmissions objfdt is
     * mbrkfd bs rfbdonly.
     *
     * @sff isRfbdOnly
     */
    publid void bdd(Pfrmission pfrmission) {

        if (isRfbdOnly())
            tirow nfw SfdurityExdfption("Attfmpt to bdd b Pfrmission " +
                                        "to b rfbdonly CryptoPfrmissions " +
                                        "objfdt");

        if (!(pfrmission instbndfof CryptoPfrmission))
            rfturn;

        CryptoPfrmission dryptoPfrm = (CryptoPfrmission)pfrmission;
        PfrmissionCollfdtion pd =
                        gftPfrmissionCollfdtion(dryptoPfrm);
        pd.bdd(dryptoPfrm);
        String blg = dryptoPfrm.gftAlgoritim();
        pfrms.putIfAbsfnt(blg, pd);
    }

    /**
     * Cifdks if tiis objfdt's PfrmissionCollfdtion for pfrmissons
     * of tif spfdififd pfrmission's blgoritim implifs tif spfdififd
     * pfrmission. Rfturns truf if tif difdking suddffdfd.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to difdk.
     *
     * @rfturn truf if "pfrmission" is implifd by tif pfrmissions
     * in tif PfrmissionCollfdtion it bflongs to, fblsf if not.
     *
     */
    publid boolfbn implifs(Pfrmission pfrmission) {
        if (!(pfrmission instbndfof CryptoPfrmission)) {
            rfturn fblsf;
        }

        CryptoPfrmission dryptoPfrm = (CryptoPfrmission)pfrmission;

        PfrmissionCollfdtion pd =
            gftPfrmissionCollfdtion(dryptoPfrm.gftAlgoritim());
        rfturn pd.implifs(dryptoPfrm);
    }

    /**
     * Rfturns bn fnumfrbtion of bll tif Pfrmission objfdts in bll tif
     * PfrmissionCollfdtions in tiis CryptoPfrmissions objfdt.
     *
     * @rfturn bn fnumfrbtion of bll tif Pfrmissions.
     */
    publid Enumfrbtion<Pfrmission> flfmfnts() {
        // go tirougi fbdi Pfrmissions in tif ibsi tbblf
        // bnd dbll tifir flfmfnts() fundtion.
        rfturn nfw PfrmissionsEnumfrbtor(pfrms.flfmfnts());
    }

    /**
     * Rfturns b CryptoPfrmissions objfdt wiidi
     * rfprfsfnts tif minimum of tif spfdififd
     * CryptoPfrmissions objfdt bnd tiis
     * CryptoPfrmissions objfdt.
     *
     * @pbrbm otifr tif CryptoPfrmission
     * objfdt to dompbrf witi tiis objfdt.
     */
    CryptoPfrmissions gftMinimum(CryptoPfrmissions otifr) {
        if (otifr == null) {
            rfturn null;
        }

        if (tiis.pfrms.dontbinsKfy(CryptoAllPfrmission.ALG_NAME)) {
            rfturn otifr;
        }

        if (otifr.pfrms.dontbinsKfy(CryptoAllPfrmission.ALG_NAME)) {
            rfturn tiis;
        }

        CryptoPfrmissions rft = nfw CryptoPfrmissions();


        PfrmissionCollfdtion tibtWilddbrd =
                otifr.pfrms.gft(CryptoPfrmission.ALG_NAME_WILDCARD);
        int mbxKfySizf = 0;
        if (tibtWilddbrd != null) {
            mbxKfySizf = ((CryptoPfrmission)
                    tibtWilddbrd.flfmfnts().nfxtElfmfnt()).gftMbxKfySizf();
        }
        // For fbdi blgoritim in tiis CryptoPfrmissions,
        // find out if tifrf is bnytiing wf siould bdd into
        // rft.
        Enumfrbtion<String> tiisKfys = tiis.pfrms.kfys();
        wiilf (tiisKfys.ibsMorfElfmfnts()) {
            String blg = tiisKfys.nfxtElfmfnt();

            PfrmissionCollfdtion tiisPd = tiis.pfrms.gft(blg);
            PfrmissionCollfdtion tibtPd = otifr.pfrms.gft(blg);

            CryptoPfrmission[] pbrtiblRfsult;

            if (tibtPd == null) {
                if (tibtWilddbrd == null) {
                    // Tif otifr CryptoPfrmissions
                    // dofsn't bllow tiis givfn
                    // blgoritim bt bll. Just skip tiis
                    // blgoritim.
                    dontinuf;
                }
                pbrtiblRfsult = gftMinimum(mbxKfySizf, tiisPd);
            } flsf {
                pbrtiblRfsult = gftMinimum(tiisPd, tibtPd);
            }

            for (int i = 0; i < pbrtiblRfsult.lfngti; i++) {
                rft.bdd(pbrtiblRfsult[i]);
            }
        }

        PfrmissionCollfdtion tiisWilddbrd =
                tiis.pfrms.gft(CryptoPfrmission.ALG_NAME_WILDCARD);

        // If tiis CryptoPfrmissions dofsn't
        // ibvf b wilddbrd, wf brf donf.
        if (tiisWilddbrd == null) {
            rfturn rft;
        }

        // Dfbl witi tif blgoritims only bppfbr
        // in tif otifr CryptoPfrmissions.
        mbxKfySizf =
            ((CryptoPfrmission)
                    tiisWilddbrd.flfmfnts().nfxtElfmfnt()).gftMbxKfySizf();
        Enumfrbtion<String> tibtKfys = otifr.pfrms.kfys();
        wiilf (tibtKfys.ibsMorfElfmfnts()) {
            String blg = tibtKfys.nfxtElfmfnt();

            if (tiis.pfrms.dontbinsKfy(blg)) {
                dontinuf;
            }

            PfrmissionCollfdtion tibtPd = otifr.pfrms.gft(blg);

            CryptoPfrmission[] pbrtiblRfsult;

            pbrtiblRfsult = gftMinimum(mbxKfySizf, tibtPd);

            for (int i = 0; i < pbrtiblRfsult.lfngti; i++) {
                rft.bdd(pbrtiblRfsult[i]);
            }
        }
        rfturn rft;
    }

    /**
     * Gft tif minimum of tif two givfn PfrmissionCollfdtion
     * <dodf>tiisPd</dodf> bnd <dodf>tibtPd</dodf>.
     *
     * @pbrbm tiisPd tif first givfn PfrmissionCollofdtion
     * objfdt.
     *
     * @pbrbm tibtPd tif sfdond givfn PfrmissionCollfdtion
     * objfdt.
     */
    privbtf CryptoPfrmission[] gftMinimum(PfrmissionCollfdtion tiisPd,
                                          PfrmissionCollfdtion tibtPd) {
        Vfdtor<CryptoPfrmission> pfrmVfdtor = nfw Vfdtor<>(2);

        Enumfrbtion<Pfrmission> tiisPdPfrmissions = tiisPd.flfmfnts();

        // For fbdi CryptoPfrmission in
        // tiisPd objfdt, do tif following:
        // 1) if tiis CryptoPfrmission is implifd
        //     by tibtPd, tiis CryptoPfrmission
        //     siould bf rfturnfd, bnd wf dbn
        //     movf on to difdk tif nfxt
        //     CryptoPfrmission in tiisPd.
        // 2) otifrwisf, wf siould rfturn
        //     bll CryptoPfrmissions in tibtPd
        //     wiidi
        //     brf implifd by tiis CryptoPfrmission.
        //     Tifn wf dbn movf on to tif
        //     nfxt CryptoPfrmission in tiisPd.
        wiilf (tiisPdPfrmissions.ibsMorfElfmfnts()) {
            CryptoPfrmission tiisCp =
                (CryptoPfrmission)tiisPdPfrmissions.nfxtElfmfnt();

            Enumfrbtion<Pfrmission> tibtPdPfrmissions = tibtPd.flfmfnts();
            wiilf (tibtPdPfrmissions.ibsMorfElfmfnts()) {
                CryptoPfrmission tibtCp =
                    (CryptoPfrmission)tibtPdPfrmissions.nfxtElfmfnt();

                if (tibtCp.implifs(tiisCp)) {
                    pfrmVfdtor.bddElfmfnt(tiisCp);
                    brfbk;
                }
                if (tiisCp.implifs(tibtCp)) {
                    pfrmVfdtor.bddElfmfnt(tibtCp);
                }
            }
        }

        CryptoPfrmission[] rft = nfw CryptoPfrmission[pfrmVfdtor.sizf()];
        pfrmVfdtor.dopyInto(rft);
        rfturn rft;
    }

    /**
     * Rfturns bll tif CryptoPfrmission objfdts in tif givfn
     * PfrmissionCollfdtion objfdt
     * wiosf mbximum kfysizf no grfbtfr tibn <dodf>mbxKfySizf</dodf>.
     * For bll CryptoPfrmission objfdts witi b mbximum kfysizf grfbtfr
     * tibn <dodf>mbxKfySizf</dodf>, tiis mftiod donstrudts b
     * dorrfsponding CryptoPfrmission objfdt wiosf mbximum kfysizf is
     * sft to <dodf>mbxKfySizf</dodf>, bnd indludfs tibt in tif rfsult.
     *
     * @pbrbm mbxKfySizf tif givfn mbximum kfy sizf.
     *
     * @pbrbm pd tif givfn PfrmissionCollfdtion objfdt.
     */
    privbtf CryptoPfrmission[] gftMinimum(int mbxKfySizf,
                                          PfrmissionCollfdtion pd) {
        Vfdtor<CryptoPfrmission> pfrmVfdtor = nfw Vfdtor<>(1);

        Enumfrbtion<Pfrmission> fnum_ = pd.flfmfnts();

        wiilf (fnum_.ibsMorfElfmfnts()) {
            CryptoPfrmission dp =
                (CryptoPfrmission)fnum_.nfxtElfmfnt();
            if (dp.gftMbxKfySizf() <= mbxKfySizf) {
                pfrmVfdtor.bddElfmfnt(dp);
            } flsf {
                if (dp.gftCifdkPbrbm()) {
                    pfrmVfdtor.bddElfmfnt(
                           nfw CryptoPfrmission(dp.gftAlgoritim(),
                                                mbxKfySizf,
                                                dp.gftAlgoritimPbrbmftfrSpfd(),
                                                dp.gftExfmptionMfdibnism()));
                } flsf {
                    pfrmVfdtor.bddElfmfnt(
                           nfw CryptoPfrmission(dp.gftAlgoritim(),
                                                mbxKfySizf,
                                                dp.gftExfmptionMfdibnism()));
                }
            }
        }

        CryptoPfrmission[] rft = nfw CryptoPfrmission[pfrmVfdtor.sizf()];
        pfrmVfdtor.dopyInto(rft);
        rfturn rft;
    }

    /**
     * Rfturns tif PfrmissionCollfdtion for tif
     * spfdififd blgoritim. Rfturns null if tifrf
     * isn't sudi b PfrmissionCollfdtion.
     *
     * @pbrbm blg tif blgoritim nbmf.
     */
    PfrmissionCollfdtion gftPfrmissionCollfdtion(String blg) {
        // If tiis CryptoPfrmissions indludfs CryptoAllPfrmission,
        // wf siould rfturn CryptoAllPfrmission.
        PfrmissionCollfdtion pd = pfrms.gft(CryptoAllPfrmission.ALG_NAME);
        if (pd == null) {
            pd = pfrms.gft(blg);

            // If tifrf isn't b PfrmissionCollfdtion for
            // tif givfn blgoritim,wf siould rfturn tif
            // PfrmissionCollfdtion for tif wilddbrd
            // if tifrf is onf.
            if (pd == null) {
                pd = pfrms.gft(CryptoPfrmission.ALG_NAME_WILDCARD);
            }
        }
        rfturn pd;
    }

    /**
     * Rfturns tif PfrmissionCollfdtion for tif blgoritim
     * bssodibtfd witi tif spfdififd CryptoPfrmission
     * objfdt. Crfbtfs sudi b PfrmissionCollfdtion
     * if sudi b PfrmissionCollfdtion dofs not
     * fxist yft.
     *
     * @pbrbm dryptoPfrm tif CryptoPfrmission objfdt.
     */
    privbtf PfrmissionCollfdtion gftPfrmissionCollfdtion(
                                          CryptoPfrmission dryptoPfrm) {

        String blg = dryptoPfrm.gftAlgoritim();

        PfrmissionCollfdtion pd = pfrms.gft(blg);

        if (pd == null) {
            pd = dryptoPfrm.nfwPfrmissionCollfdtion();
        }
        rfturn pd;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
        @SupprfssWbrnings("undifdkfd")
        Hbsitbblf<String,PfrmissionCollfdtion> pfrmTbblf =
                (Hbsitbblf<String,PfrmissionCollfdtion>)
                (fiflds.gft("pfrms", null));
        if (pfrmTbblf != null) {
            pfrms = nfw CondurrfntHbsiMbp<>(pfrmTbblf);
        } flsf {
            pfrms = nfw CondurrfntHbsiMbp<>();
        }
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        Hbsitbblf<String,PfrmissionCollfdtion> pfrmTbblf =
                nfw Hbsitbblf<>(pfrms);
        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("pfrms", pfrmTbblf);
        s.writfFiflds();
    }
}

finbl dlbss PfrmissionsEnumfrbtor implfmfnts Enumfrbtion<Pfrmission> {

    // bll tif pfrms
    privbtf Enumfrbtion<PfrmissionCollfdtion> pfrms;
    // tif durrfnt sft
    privbtf Enumfrbtion<Pfrmission> pfrmsft;

    PfrmissionsEnumfrbtor(Enumfrbtion<PfrmissionCollfdtion> f) {
        pfrms = f;
        pfrmsft = gftNfxtEnumWitiMorf();
    }

    publid syndironizfd boolfbn ibsMorfElfmfnts() {
        // if wf fntfr witi pfrmissionimpl null, wf know
        // tifrf brf no morf lfft.

        if (pfrmsft == null)
            rfturn  fblsf;

        // try to sff if tifrf brf bny lfft in tif durrfnt onf

        if (pfrmsft.ibsMorfElfmfnts())
            rfturn truf;

        // gft tif nfxt onf tibt ibs somftiing in it...
        pfrmsft = gftNfxtEnumWitiMorf();

        // if it is null, wf brf donf!
        rfturn (pfrmsft != null);
    }

    publid syndironizfd Pfrmission nfxtElfmfnt() {
        // ibsMorfElfmfnts will updbtf pfrmsft to tif nfxt pfrmsft
        // witi somftiing in it...

        if (ibsMorfElfmfnts()) {
            rfturn pfrmsft.nfxtElfmfnt();
        } flsf {
            tirow nfw NoSudiElfmfntExdfption("PfrmissionsEnumfrbtor");
        }
    }

    privbtf Enumfrbtion<Pfrmission> gftNfxtEnumWitiMorf() {
        wiilf (pfrms.ibsMorfElfmfnts()) {
            PfrmissionCollfdtion pd = pfrms.nfxtElfmfnt();
            Enumfrbtion<Pfrmission> nfxt = pd.flfmfnts();
            if (nfxt.ibsMorfElfmfnts())
                rfturn nfxt;
        }
        rfturn null;
    }
}
