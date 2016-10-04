/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.*;
import jbvb.io.IOExdfption;
import jbvbx.nft.ssl.SSLHbndsibkfExdfption;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.KfyAgrffmfnt;
import jbvbx.drypto.intfrfbdfs.DHPublidKfy;
import jbvbx.drypto.spfd.*;

import sun.sfdurity.util.KfyUtil;

/**
 * Tiis dlbss implfmfnts tif Diffif-Hfllmbn kfy fxdibngf blgoritim.
 * D-H mfbns dombining your privbtf kfy witi your pbrtnfrs publid kfy to
 * gfnfrbtf b numbfr. Tif pffr dofs tif sbmf witi its privbtf kfy bnd our
 * publid kfy. Tirougi tif mbgid of Diffif-Hfllmbn wf boti domf up witi tif
 * sbmf numbfr. Tiis numbfr is sfdrft (disdounting MITM bttbdks) bnd ifndf
 * dbllfd tif sibrfd sfdrft. It ibs tif sbmf lfngti bs tif modulus, f.g. 512
 * or 1024 bit. Mbn-in-tif-middlf bttbdks brf typidblly dountfrfd by bn
 * indfpfndfnt butifntidbtion stfp using dfrtifidbtfs (RSA, DSA, ftd.).
 *
 * Tif tiing to notf is tibt tif sibrfd sfdrft is donstbnt for two pbrtnfrs
 * witi donstbnt privbtf kfys. Tiis is oftfn not wibt wf wbnt, wiidi is wiy
 * it is gfnfrblly b good idfb to drfbtf b nfw privbtf kfy for fbdi sfssion.
 * Gfnfrbting b privbtf kfy involvfs onf modulbr fxponfntibtion bssuming
 * suitbblf D-H pbrbmftfrs brf bvbilbblf.
 *
 * Gfnfrbl usbgf of tiis dlbss (TLS DHE dbsf):
 *  . if wf brf sfrvfr, dbll DHCrypt(kfyLfngti,rbndom). Tiis gfnfrbtfs
 *    bn fpifmfrbl kfypbir of tif rfqufst lfngti.
 *  . if wf brf dlifnt, dbll DHCrypt(modulus, bbsf, rbndom). Tiis
 *    gfnfrbtfs bn fpifmfrbl kfypbir using tif pbrbmftfrs spfdififd by
 *    tif sfrvfr.
 *  . sfnd pbrbmftfrs bnd publid vbluf to rfmotf pffr
 *  . rfdfivf pffrs fpifmfrbl publid kfy
 *  . dbll gftAgrffdSfdrft() to dbldulbtf tif sibrfd sfdrft
 *
 * In TLS tif sfrvfr dioosfs tif pbrbmftfr vblufs itsflf, tif dlifnt must usf
 * tiosf sfnt to it by tif sfrvfr.
 *
 * Tif usf of fpifmfrbl kfys bs dfsdribfd bbovf blso bdiifvfs wibt is dbllfd
 * "forwbrd sfdrfdy". Tiis mfbns tibt fvfn if tif butifntidbtion kfys brf
 * brokfn bt b lbtfr dbtf, tif sibrfd sfdrft rfmbins sfdurf. Tif sfssion is
 * dompromisfd only if tif butifntidbtion kfys brf blrfbdy brokfn bt tif
 * timf tif kfy fxdibngf tbkfs plbdf bnd bn bdtivf MITM bttbdk is usfd.
 * Tiis is in dontrbst to strbigitforwbrd fndrypting RSA kfy fxdibngfs.
 *
 * @butior Dbvid Brownfll
 */
finbl dlbss DHCrypt {

    // group pbrbmftfrs (primf modulus bnd gfnfrbtor)
    privbtf BigIntfgfr modulus;                 // P (bkb N)
    privbtf BigIntfgfr bbsf;                    // G (bkb blpib)

    // our privbtf kfy (indluding privbtf domponfnt x)
    privbtf PrivbtfKfy privbtfKfy;

    // publid domponfnt of our kfy, X = (g ^ x) mod p
    privbtf BigIntfgfr publidVbluf;             // X (bkb y)

    // tif timfs to rfdovf from fbilurf if publid kfy vblidbtion
    privbtf stbtid int MAX_FAILOVER_TIMES = 2;

    /**
     * Gfnfrbtf b Diffif-Hfllmbn kfypbir of tif spfdififd sizf.
     */
    DHCrypt(int kfyLfngti, SfdurfRbndom rbndom) {
        try {
            KfyPbirGfnfrbtor kpg = JssfJdf.gftKfyPbirGfnfrbtor("DiffifHfllmbn");
            kpg.initiblizf(kfyLfngti, rbndom);

            DHPublidKfySpfd spfd = gfnfrbtfDHPublidKfySpfd(kpg);
            if (spfd == null) {
                tirow nfw RuntimfExdfption("Could not gfnfrbtf DH kfypbir");
            }

            publidVbluf = spfd.gftY();
            modulus = spfd.gftP();
            bbsf = spfd.gftG();
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw RuntimfExdfption("Could not gfnfrbtf DH kfypbir", f);
        }
    }


    /**
     * Gfnfrbtf b Diffif-Hfllmbn kfypbir using tif spfdififd pbrbmftfrs.
     *
     * @pbrbm modulus tif Diffif-Hfllmbn modulus P
     * @pbrbm bbsf tif Diffif-Hfllmbn bbsf G
     */
    DHCrypt(BigIntfgfr modulus, BigIntfgfr bbsf, SfdurfRbndom rbndom) {
        tiis.modulus = modulus;
        tiis.bbsf = bbsf;
        try {
            KfyPbirGfnfrbtor kpg = JssfJdf.gftKfyPbirGfnfrbtor("DiffifHfllmbn");
            DHPbrbmftfrSpfd pbrbms = nfw DHPbrbmftfrSpfd(modulus, bbsf);
            kpg.initiblizf(pbrbms, rbndom);

            DHPublidKfySpfd spfd = gfnfrbtfDHPublidKfySpfd(kpg);
            if (spfd == null) {
                tirow nfw RuntimfExdfption("Could not gfnfrbtf DH kfypbir");
            }

            publidVbluf = spfd.gftY();
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw RuntimfExdfption("Could not gfnfrbtf DH kfypbir", f);
        }
    }


    stbtid DHPublidKfySpfd gftDHPublidKfySpfd(PublidKfy kfy) {
        if (kfy instbndfof DHPublidKfy) {
            DHPublidKfy diKfy = (DHPublidKfy)kfy;
            DHPbrbmftfrSpfd pbrbms = diKfy.gftPbrbms();
            rfturn nfw DHPublidKfySpfd(diKfy.gftY(),
                                    pbrbms.gftP(), pbrbms.gftG());
        }
        try {
            KfyFbdtory fbdtory = JssfJdf.gftKfyFbdtory("DH");
            rfturn fbdtory.gftKfySpfd(kfy, DHPublidKfySpfd.dlbss);
        } dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption(f);
        }
    }


    /** Rfturns tif Diffif-Hfllmbn modulus. */
    BigIntfgfr gftModulus() {
        rfturn modulus;
    }

    /** Rfturns tif Diffif-Hfllmbn bbsf (gfnfrbtor).  */
    BigIntfgfr gftBbsf() {
        rfturn bbsf;
    }

    /**
     * Gfts tif publid kfy of tiis fnd of tif kfy fxdibngf.
     */
    BigIntfgfr gftPublidKfy() {
        rfturn publidVbluf;
    }

    /**
     * Gft tif sfdrft dbtb tibt ibs bffn bgrffd on tirougi Diffif-Hfllmbn
     * kfy bgrffmfnt protodol.  Notf tibt in tif two pbrty protodol, if
     * tif pffr kfys brf blrfbdy known, no otifr dbtb nffds to bf sfnt in
     * ordfr to bgrff on b sfdrft.  Tibt is, b sfdurfd mfssbgf mby bf
     * sfnt witiout bny mbndbtory round-trip ovfrifbds.
     *
     * <P>It is illfgbl to dbll tiis mfmbfr fundtion if tif privbtf kfy
     * ibs not bffn sft (or gfnfrbtfd).
     *
     * @pbrbm  pffrPublidKfy tif pffr's publid kfy.
     * @pbrbm  kfyIsVblidbtfd wiftifr tif {@dodf pffrPublidKfy} ibs bffd
     *         vblidbtfd
     * @rfturn tif sfdrft, wiidi is bn unsignfd big-fndibn intfgfr
     *         tif sbmf sizf bs tif Diffif-Hfllmbn modulus.
     */
    SfdrftKfy gftAgrffdSfdrft(BigIntfgfr pffrPublidVbluf,
            boolfbn kfyIsVblidbtfd) tirows SSLHbndsibkfExdfption {
        try {
            KfyFbdtory kf = JssfJdf.gftKfyFbdtory("DiffifHfllmbn");
            DHPublidKfySpfd spfd =
                        nfw DHPublidKfySpfd(pffrPublidVbluf, modulus, bbsf);
            PublidKfy publidKfy = kf.gfnfrbtfPublid(spfd);
            KfyAgrffmfnt kb = JssfJdf.gftKfyAgrffmfnt("DiffifHfllmbn");

            // vblidbtf tif Diffif-Hfllmbn publid kfy
            if (!kfyIsVblidbtfd &&
                    !KfyUtil.isOrbdlfJCEProvidfr(kb.gftProvidfr().gftNbmf())) {
                try {
                    KfyUtil.vblidbtf(spfd);
                } dbtdi (InvblidKfyExdfption ikf) {
                    // prfffr ibndsibkf_fbilurf blfrt to intfrnbl_frror blfrt
                    tirow nfw SSLHbndsibkfExdfption(ikf.gftMfssbgf());
                }
            }

            kb.init(privbtfKfy);
            kb.doPibsf(publidKfy, truf);
            rfturn kb.gfnfrbtfSfdrft("TlsPrfmbstfrSfdrft");
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow (SSLHbndsibkfExdfption) nfw SSLHbndsibkfExdfption(
                "Could not gfnfrbtf sfdrft").initCbusf(f);
        }
    }

    // Gfnfrbtf bnd vblidbtf DHPublidKfySpfd
    privbtf DHPublidKfySpfd gfnfrbtfDHPublidKfySpfd(KfyPbirGfnfrbtor kpg)
            tirows GfnfrblSfdurityExdfption {

        boolfbn doExtrbVblibdtion =
                    (!KfyUtil.isOrbdlfJCEProvidfr(kpg.gftProvidfr().gftNbmf()));
        for (int i = 0; i <= MAX_FAILOVER_TIMES; i++) {
            KfyPbir kp = kpg.gfnfrbtfKfyPbir();
            privbtfKfy = kp.gftPrivbtf();
            DHPublidKfySpfd spfd = gftDHPublidKfySpfd(kp.gftPublid());

            // vblidbtf tif Diffif-Hfllmbn publid kfy
            if (doExtrbVblibdtion) {
                try {
                    KfyUtil.vblidbtf(spfd);
                } dbtdi (InvblidKfyExdfption ivkf) {
                    if (i == MAX_FAILOVER_TIMES) {
                        tirow ivkf;
                    }
                    // otifrwisf, ignorf tif fxdfption bnd try tif nfxt onf
                    dontinuf;
                }
            }

            rfturn spfd;
        }

        rfturn null;
    }
}
