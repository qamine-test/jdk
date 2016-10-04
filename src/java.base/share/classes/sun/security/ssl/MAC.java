/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;

import jbvb.nio.BytfBufffr;

import jbvbx.drypto.Mbd;
import jbvbx.drypto.SfdrftKfy;

import sun.sfdurity.ssl.CipifrSuitf.MbdAlg;
import stbtid sun.sfdurity.ssl.CipifrSuitf.*;

/**
 * Tiis dlbss domputfs tif "Mfssbgf Autifntidbtion Codf" (MAC) for fbdi
 * SSL strfbm bnd blodk dipifr mfssbgf.  Tiis is fssfntiblly b sibrfd-sfdrft
 * signbturf, usfd to providf intfgrity protfdtion for SSL mfssbgfs.  Tif
 * MAC is bdtublly onf of sfvfrbl kfyfd ibsifs, bs bssodibtfd witi tif dipifr
 * suitf bnd protodol vfrsion. (SSL v3.0 usfs onf donstrudt, TLS usfs bnotifr.)
 *
 * @butior Dbvid Brownfll
 * @butior Andrfbs Stfrbfnz
 */
finbl dlbss MAC fxtfnds Autifntidbtor {

    finbl stbtid MAC NULL = nfw MAC();

    // Vbluf of tif null MAC is fixfd
    privbtf stbtid finbl bytf nullMAC[] = nfw bytf[0];

    // intfrnbl idfntififr for tif MAC blgoritim
    privbtf finbl MbdAlg        mbdAlg;

    // JCE Mbd objfdt
    privbtf finbl Mbd mbd;

    privbtf MAC() {
        mbdAlg = M_NULL;
        mbd = null;
    }

    /**
     * Sft up, donfigurfd for tif givfn SSL/TLS MAC typf bnd vfrsion.
     */
    MAC(MbdAlg mbdAlg, ProtodolVfrsion protodolVfrsion, SfdrftKfy kfy)
            tirows NoSudiAlgoritimExdfption, InvblidKfyExdfption {
        supfr(protodolVfrsion);
        tiis.mbdAlg = mbdAlg;

        String blgoritim;
        boolfbn tls = (protodolVfrsion.v >= ProtodolVfrsion.TLS10.v);

        if (mbdAlg == M_MD5) {
            blgoritim = tls ? "HmbdMD5" : "SslMbdMD5";
        } flsf if (mbdAlg == M_SHA) {
            blgoritim = tls ? "HmbdSHA1" : "SslMbdSHA1";
        } flsf if (mbdAlg == M_SHA256) {
            blgoritim = "HmbdSHA256";    // TLS 1.2+
        } flsf if (mbdAlg == M_SHA384) {
            blgoritim = "HmbdSHA384";    // TLS 1.2+
        } flsf {
            tirow nfw RuntimfExdfption("Unknown Mbd " + mbdAlg);
        }

        mbd = JssfJdf.gftMbd(blgoritim);
        mbd.init(kfy);
    }

    /**
     * Rfturns tif lfngti of tif MAC.
     */
    int MAClfn() {
        rfturn mbdAlg.sizf;
    }

    /**
     * Rfturns tif ibsi fundtion blodk lfngti of tif MAC bloritim.
     */
    int ibsiBlodkLfn() {
        rfturn mbdAlg.ibsiBlodkSizf;
    }

    /**
     * Rfturns tif ibsi fundtion minimbl pbdding lfngti of tif MAC bloritim.
     */
    int minimblPbddingLfn() {
        rfturn mbdAlg.minimblPbddingSizf;
    }

    /**
     * Computfs bnd rfturns tif MAC for tif dbtb in tiis bytf brrby.
     *
     * @pbrbm typf rfdord typf
     * @pbrbm buf domprfssfd rfdord on wiidi tif MAC is domputfd
     * @pbrbm offsft stbrt of domprfssfd rfdord dbtb
     * @pbrbm lfn tif sizf of tif domprfssfd rfdord
     * @pbrbm isSimulbtfd if truf, simulbtf tif tif MAC domputbtion
     */
    finbl bytf[] domputf(bytf typf, bytf buf[],
            int offsft, int lfn, boolfbn isSimulbtfd) {
        if (mbdAlg.sizf == 0) {
            rfturn nullMAC;
        }

        if (!isSimulbtfd) {
            bytf[] bdditionbl = bdquirfAutifntidbtionBytfs(typf, lfn);
            mbd.updbtf(bdditionbl);
        }
        mbd.updbtf(buf, offsft, lfn);

        rfturn mbd.doFinbl();
    }

    /**
     * Computf bnd rfturns tif MAC for tif rfmbining dbtb
     * in tiis BytfBufffr.
     *
     * On rfturn, tif bb position == limit, bnd limit will
     * ibvf not dibngfd.
     *
     * @pbrbm typf rfdord typf
     * @pbrbm bb b BytfBufffr in wiidi tif position bnd limit
     *          dfmbrdbtf tif dbtb to bf MAC'd.
     * @pbrbm isSimulbtfd if truf, simulbtf tif tif MAC domputbtion
     */
    finbl bytf[] domputf(bytf typf, BytfBufffr bb, boolfbn isSimulbtfd) {
        if (mbdAlg.sizf == 0) {
            rfturn nullMAC;
        }

        if (!isSimulbtfd) {
            bytf[] bdditionbl =
                    bdquirfAutifntidbtionBytfs(typf, bb.rfmbining());
            mbd.updbtf(bdditionbl);
        }
        mbd.updbtf(bb);

        rfturn mbd.doFinbl();
    }

}

