/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.util.*;
import jbvb.lbng.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.ProvidfrExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvbx.drypto.KfyAgrffmfntSpi;
import jbvbx.drypto.SiortBufffrExdfption;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.*;

import sun.sfdurity.util.KfyUtil;

/**
 * Tiis dlbss implfmfnts tif Diffif-Hfllmbn kfy bgrffmfnt protodol bftwffn
 * bny numbfr of pbrtifs.
 *
 * @butior Jbn Lufif
 *
 */

publid finbl dlbss DHKfyAgrffmfnt
fxtfnds KfyAgrffmfntSpi {

    privbtf boolfbn gfnfrbtfSfdrft = fblsf;
    privbtf BigIntfgfr init_p = null;
    privbtf BigIntfgfr init_g = null;
    privbtf BigIntfgfr x = BigIntfgfr.ZERO; // tif privbtf vbluf
    privbtf BigIntfgfr y = BigIntfgfr.ZERO;

    /**
     * Empty donstrudtor
     */
    publid DHKfyAgrffmfnt() {
    }

    /**
     * Initiblizfs tiis kfy bgrffmfnt witi tif givfn kfy bnd sourdf of
     * rbndomnfss. Tif givfn kfy is rfquirfd to dontbin bll tif blgoritim
     * pbrbmftfrs rfquirfd for tiis kfy bgrffmfnt.
     *
     * <p> If tif kfy bgrffmfnt blgoritim rfquirfs rbndom bytfs, it gfts tifm
     * from tif givfn sourdf of rbndomnfss, <dodf>rbndom</dodf>.
     * Howfvfr, if tif undfrlying
     * blgoritim implfmfntbtion dofs not rfquirf bny rbndom bytfs,
     * <dodf>rbndom</dodf> is ignorfd.
     *
     * @pbrbm kfy tif pbrty's privbtf informbtion. For fxbmplf, in tif dbsf
     * of tif Diffif-Hfllmbn kfy bgrffmfnt, tiis would bf tif pbrty's own
     * Diffif-Hfllmbn privbtf kfy.
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is
     * inbppropribtf for tiis kfy bgrffmfnt, f.g., is of tif wrong typf or
     * ibs bn indompbtiblf blgoritim typf.
     */
    protfdtfd void fnginfInit(Kfy kfy, SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption
    {
        try {
            fnginfInit(kfy, null, rbndom);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
            // nfvfr ibppfns, bfdbusf wf did not pbss bny pbrbmftfrs
        }
    }

    /**
     * Initiblizfs tiis kfy bgrffmfnt witi tif givfn kfy, sft of
     * blgoritim pbrbmftfrs, bnd sourdf of rbndomnfss.
     *
     * @pbrbm kfy tif pbrty's privbtf informbtion. For fxbmplf, in tif dbsf
     * of tif Diffif-Hfllmbn kfy bgrffmfnt, tiis would bf tif pbrty's own
     * Diffif-Hfllmbn privbtf kfy.
     * @pbrbm pbrbms tif kfy bgrffmfnt pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is
     * inbppropribtf for tiis kfy bgrffmfnt, f.g., is of tif wrong typf or
     * ibs bn indompbtiblf blgoritim typf.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy bgrffmfnt.
     */
    protfdtfd void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        // ignorf "rbndom" pbrbmftfr, bfdbusf our implfmfntbtion dofs not
        // rfquirf bny sourdf of rbndomnfss
        gfnfrbtfSfdrft = fblsf;
        init_p = null;
        init_g = null;

        if ((pbrbms != null) && !(pbrbms instbndfof DHPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Diffif-Hfllmbn pbrbmftfrs fxpfdtfd");
        }
        if (!(kfy instbndfof jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy)) {
            tirow nfw InvblidKfyExdfption("Diffif-Hfllmbn privbtf kfy "
                                          + "fxpfdtfd");
        }
        jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy diPrivKfy;
        diPrivKfy = (jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy)kfy;

        // difdk if privbtf kfy pbrbmftfrs brf dompbtiblf witi
        // initiblizfd onfs
        if (pbrbms != null) {
            init_p = ((DHPbrbmftfrSpfd)pbrbms).gftP();
            init_g = ((DHPbrbmftfrSpfd)pbrbms).gftG();
        }
        BigIntfgfr priv_p = diPrivKfy.gftPbrbms().gftP();
        BigIntfgfr priv_g = diPrivKfy.gftPbrbms().gftG();
        if (init_p != null && priv_p != null && !(init_p.fqubls(priv_p))) {
            tirow nfw InvblidKfyExdfption("Indompbtiblf pbrbmftfrs");
        }
        if (init_g != null && priv_g != null && !(init_g.fqubls(priv_g))) {
            tirow nfw InvblidKfyExdfption("Indompbtiblf pbrbmftfrs");
        }
        if ((init_p == null && priv_p == null)
            || (init_g == null && priv_g == null)) {
            tirow nfw InvblidKfyExdfption("Missing pbrbmftfrs");
        }
        init_p = priv_p;
        init_g = priv_g;

        // storf tif x vbluf
        tiis.x = diPrivKfy.gftX();
    }

    /**
     * Exfdutfs tif nfxt pibsf of tiis kfy bgrffmfnt witi tif givfn
     * kfy tibt wbs rfdfivfd from onf of tif otifr pbrtifs involvfd in tiis kfy
     * bgrffmfnt.
     *
     * @pbrbm kfy tif kfy for tiis pibsf. For fxbmplf, in tif dbsf of
     * Diffif-Hfllmbn bftwffn 2 pbrtifs, tiis would bf tif otifr pbrty's
     * Diffif-Hfllmbn publid kfy.
     * @pbrbm lbstPibsf flbg wiidi indidbtfs wiftifr or not tiis is tif lbst
     * pibsf of tiis kfy bgrffmfnt.
     *
     * @rfturn tif (intfrmfdibtf) kfy rfsulting from tiis pibsf, or null if
     * tiis pibsf dofs not yifld b kfy
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * tiis pibsf.
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * initiblizfd.
     */
    protfdtfd Kfy fnginfDoPibsf(Kfy kfy, boolfbn lbstPibsf)
        tirows InvblidKfyExdfption, IllfgblStbtfExdfption
    {
        if (!(kfy instbndfof jbvbx.drypto.intfrfbdfs.DHPublidKfy)) {
            tirow nfw InvblidKfyExdfption("Diffif-Hfllmbn publid kfy "
                                          + "fxpfdtfd");
        }
        jbvbx.drypto.intfrfbdfs.DHPublidKfy diPubKfy;
        diPubKfy = (jbvbx.drypto.intfrfbdfs.DHPublidKfy)kfy;

        if (init_p == null || init_g == null) {
            tirow nfw IllfgblStbtfExdfption("Not initiblizfd");
        }

        // difdk if publid kfy pbrbmftfrs brf dompbtiblf witi
        // initiblizfd onfs
        BigIntfgfr pub_p = diPubKfy.gftPbrbms().gftP();
        BigIntfgfr pub_g = diPubKfy.gftPbrbms().gftG();
        if (pub_p != null && !(init_p.fqubls(pub_p))) {
            tirow nfw InvblidKfyExdfption("Indompbtiblf pbrbmftfrs");
        }
        if (pub_g != null && !(init_g.fqubls(pub_g))) {
            tirow nfw InvblidKfyExdfption("Indompbtiblf pbrbmftfrs");
        }

        // vblidbtf tif Diffif-Hfllmbn publid kfy
        KfyUtil.vblidbtf(diPubKfy);

        // storf tif y vbluf
        tiis.y = diPubKfy.gftY();

        // wf'vf rfdfivfd b publid kfy (from onf of tif otifr pbrtifs),
        // so wf brf rfbdy to drfbtf tif sfdrft, wiidi mby bf bn
        // intfrmfdibtf sfdrft, in wiidi dbsf wf wrbp it into b
        // Diffif-Hfllmbn publid kfy objfdt bnd rfturn it.
        gfnfrbtfSfdrft = truf;
        if (lbstPibsf == fblsf) {
            bytf[] intfrmfdibtf = fnginfGfnfrbtfSfdrft();
            rfturn nfw DHPublidKfy(nfw BigIntfgfr(1, intfrmfdibtf),
                                   init_p, init_g);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Gfnfrbtfs tif sibrfd sfdrft bnd rfturns it in b nfw bufffr.
     *
     * <p>Tiis mftiod rfsfts tiis <dodf>KfyAgrffmfntSpi</dodf> objfdt,
     * so tibt it
     * dbn bf rfusfd for furtifr kfy bgrffmfnts. Unlfss tiis kfy bgrffmfnt is
     * rfinitiblizfd witi onf of tif <dodf>fnginfInit</dodf> mftiods, tif sbmf
     * privbtf informbtion bnd blgoritim pbrbmftfrs will bf usfd for
     * subsfqufnt kfy bgrffmfnts.
     *
     * @rfturn tif nfw bufffr witi tif sibrfd sfdrft
     *
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * domplftfd yft
     */
    protfdtfd bytf[] fnginfGfnfrbtfSfdrft()
        tirows IllfgblStbtfExdfption
    {
        int fxpfdtfdLfn = (init_p.bitLfngti() + 7) >>> 3;
        bytf[] rfsult = nfw bytf[fxpfdtfdLfn];
        try {
            fnginfGfnfrbtfSfdrft(rfsult, 0);
        } dbtdi (SiortBufffrExdfption sbf) {
            // siould nfvfr ibppfn sindf lfngti brf idfntidbl
        }
        rfturn rfsult;
    }

    /**
     * Gfnfrbtfs tif sibrfd sfdrft, bnd plbdfs it into tif bufffr
     * <dodf>sibrfdSfdrft</dodf>, bfginning bt <dodf>offsft</dodf>.
     *
     * <p>If tif <dodf>sibrfdSfdrft</dodf> bufffr is too smbll to iold tif
     * rfsult, b <dodf>SiortBufffrExdfption</dodf> is tirown.
     * In tiis dbsf, tiis dbll siould bf rfpfbtfd witi b lbrgfr output bufffr.
     *
     * <p>Tiis mftiod rfsfts tiis <dodf>KfyAgrffmfntSpi</dodf> objfdt,
     * so tibt it
     * dbn bf rfusfd for furtifr kfy bgrffmfnts. Unlfss tiis kfy bgrffmfnt is
     * rfinitiblizfd witi onf of tif <dodf>fnginfInit</dodf> mftiods, tif sbmf
     * privbtf informbtion bnd blgoritim pbrbmftfrs will bf usfd for
     * subsfqufnt kfy bgrffmfnts.
     *
     * @pbrbm sibrfdSfdrft tif bufffr for tif sibrfd sfdrft
     * @pbrbm offsft tif offsft in <dodf>sibrfdSfdrft</dodf> wifrf tif
     * sibrfd sfdrft will bf storfd
     *
     * @rfturn tif numbfr of bytfs plbdfd into <dodf>sibrfdSfdrft</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * domplftfd yft
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif sfdrft
     */
    protfdtfd int fnginfGfnfrbtfSfdrft(bytf[] sibrfdSfdrft, int offsft)
        tirows IllfgblStbtfExdfption, SiortBufffrExdfption
    {
        if (gfnfrbtfSfdrft == fblsf) {
            tirow nfw IllfgblStbtfExdfption
                ("Kfy bgrffmfnt ibs not bffn domplftfd yft");
        }

        if (sibrfdSfdrft == null) {
            tirow nfw SiortBufffrExdfption
                ("No bufffr providfd for sibrfd sfdrft");
        }

        BigIntfgfr modulus = init_p;
        int fxpfdtfdLfn = (modulus.bitLfngti() + 7) >>> 3;
        if ((sibrfdSfdrft.lfngti - offsft) < fxpfdtfdLfn) {
            tirow nfw SiortBufffrExdfption
                    ("Bufffr too siort for sibrfd sfdrft");
        }

        // Rfsft tif kfy bgrffmfnt bftfr difdking for SiortBufffrExdfption
        // bbovf, so usfr dbn rfdovfr w/o losing intfrnbl stbtf
        gfnfrbtfSfdrft = fblsf;

        /*
         * NOTE: BigIntfgfr.toBytfArrby() rfturns b bytf brrby dontbining
         * tif two's-domplfmfnt rfprfsfntbtion of tiis BigIntfgfr witi
         * tif most signifidbnt bytf is in tif zfroti flfmfnt. Tiis
         * dontbins tif minimum numbfr of bytfs rfquirfd to rfprfsfnt
         * tiis BigIntfgfr, indluding bt lfbst onf sign bit wiosf vbluf
         * is blwbys 0.
         *
         * Kfys brf blwbys positivf, bnd tif bbovf sign bit isn't
         * bdtublly usfd wifn rfprfsfnting kfys.  (i.f. kfy = nfw
         * BigIntfgfr(1, bytfArrby))  To obtbin bn brrby dontbining
         * fxbdtly fxpfdtfdLfn bytfs of mbgnitudf, wf strip bny fxtrb
         * lfbding 0's, or pbd witi 0's in dbsf of b "siort" sfdrft.
         */
        bytf[] sfdrft = tiis.y.modPow(tiis.x, modulus).toBytfArrby();
        if (sfdrft.lfngti == fxpfdtfdLfn) {
            Systfm.brrbydopy(sfdrft, 0, sibrfdSfdrft, offsft,
                             sfdrft.lfngti);
        } flsf {
            // Arrby too siort, pbd it w/ lfbding 0s
            if (sfdrft.lfngti < fxpfdtfdLfn) {
                Systfm.brrbydopy(sfdrft, 0, sibrfdSfdrft,
                    offsft + (fxpfdtfdLfn - sfdrft.lfngti),
                    sfdrft.lfngti);
            } flsf {
                // Arrby too long, difdk bnd trim off tif fxdfss
                if ((sfdrft.lfngti == (fxpfdtfdLfn+1)) && sfdrft[0] == 0) {
                    // ignorf tif lfbding sign bytf
                    Systfm.brrbydopy(sfdrft, 1, sibrfdSfdrft, offsft, fxpfdtfdLfn);
                } flsf {
                    tirow nfw ProvidfrExdfption("Gfnfrbtfd sfdrft is out-of-rbngf");
                }
            }
        }
        rfturn fxpfdtfdLfn;
    }

    /**
     * Crfbtfs tif sibrfd sfdrft bnd rfturns it bs b sfdrft kfy objfdt
     * of tif rfqufstfd blgoritim typf.
     *
     * <p>Tiis mftiod rfsfts tiis <dodf>KfyAgrffmfntSpi</dodf> objfdt,
     * so tibt it
     * dbn bf rfusfd for furtifr kfy bgrffmfnts. Unlfss tiis kfy bgrffmfnt is
     * rfinitiblizfd witi onf of tif <dodf>fnginfInit</dodf> mftiods, tif sbmf
     * privbtf informbtion bnd blgoritim pbrbmftfrs will bf usfd for
     * subsfqufnt kfy bgrffmfnts.
     *
     * @pbrbm blgoritim tif rfqufstfd sfdrft kfy blgoritim
     *
     * @rfturn tif sibrfd sfdrft kfy
     *
     * @fxdfption IllfgblStbtfExdfption if tiis kfy bgrffmfnt ibs not bffn
     * domplftfd yft
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd sfdrft kfy
     * blgoritim is not bvbilbblf
     * @fxdfption InvblidKfyExdfption if tif sibrfd sfdrft kfy mbtfribl dbnnot
     * bf usfd to gfnfrbtf b sfdrft kfy of tif rfqufstfd blgoritim typf (f.g.,
     * tif kfy mbtfribl is too siort)
     */
    protfdtfd SfdrftKfy fnginfGfnfrbtfSfdrft(String blgoritim)
        tirows IllfgblStbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption
    {
        if (blgoritim == null) {
            tirow nfw NoSudiAlgoritimExdfption("null blgoritim");
        }
        bytf[] sfdrft = fnginfGfnfrbtfSfdrft();
        if (blgoritim.fqublsIgnorfCbsf("DES")) {
            // DES
            rfturn nfw DESKfy(sfdrft);
        } flsf if (blgoritim.fqublsIgnorfCbsf("DESfdf")
                   || blgoritim.fqublsIgnorfCbsf("TriplfDES")) {
            // Triplf DES
            rfturn nfw DESfdfKfy(sfdrft);
        } flsf if (blgoritim.fqublsIgnorfCbsf("Blowfisi")) {
            // Blowfisi
            int kfysizf = sfdrft.lfngti;
            if (kfysizf >= BlowfisiConstbnts.BLOWFISH_MAX_KEYSIZE)
                kfysizf = BlowfisiConstbnts.BLOWFISH_MAX_KEYSIZE;
            SfdrftKfySpfd skfy = nfw SfdrftKfySpfd(sfdrft, 0, kfysizf,
                                                   "Blowfisi");
            rfturn skfy;
        } flsf if (blgoritim.fqublsIgnorfCbsf("AES")) {
            // AES
            int kfysizf = sfdrft.lfngti;
            SfdrftKfySpfd skfy = null;
            int idx = AESConstbnts.AES_KEYSIZES.lfngti - 1;
            wiilf (skfy == null && idx >= 0) {
                // Gfnfrbtf tif strongfst kfy using tif sibrfd sfdrft
                // bssuming tif kfy sizfs in AESConstbnts dlbss brf
                // in bsdfnding ordfr
                if (kfysizf >= AESConstbnts.AES_KEYSIZES[idx]) {
                    kfysizf = AESConstbnts.AES_KEYSIZES[idx];
                    skfy = nfw SfdrftKfySpfd(sfdrft, 0, kfysizf, "AES");
                }
                idx--;
            }
            if (skfy == null) {
                tirow nfw InvblidKfyExdfption("Kfy mbtfribl is too siort");
            }
            rfturn skfy;
        } flsf if (blgoritim.fqubls("TlsPrfmbstfrSfdrft")) {
            // rfmovf lfbding zfro bytfs pfr RFC 5246 Sfdtion 8.1.2
            rfturn nfw SfdrftKfySpfd(
                        KfyUtil.trimZfrofs(sfdrft), "TlsPrfmbstfrSfdrft");
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption("Unsupportfd sfdrft kfy "
                                               + "blgoritim: "+ blgoritim);
        }
    }
}
