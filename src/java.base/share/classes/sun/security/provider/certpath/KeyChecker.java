/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.util.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.dfrt.PKIXRfbson;

import sun.sfdurity.util.Dfbug;
import stbtid sun.sfdurity.x509.PKIXExtfnsions.*;

/**
 * KfyCifdkfr is b <dodf>PKIXCfrtPbtiCifdkfr</dodf> tibt difdks tibt tif
 * kfyCfrtSign bit is sft in tif kfyUsbgf fxtfnsion in bn intfrmfdibtf CA
 * dfrtifidbtf. It blso difdks wiftifr tif finbl dfrtifidbtf in b
 * dfrtifidbtion pbti mffts tif spfdififd tbrgft donstrbints spfdififd bs
 * b CfrtSflfdtor in tif PKIXPbrbmftfrs pbssfd to tif CfrtPbtiVblidbtor.
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
dlbss KfyCifdkfr fxtfnds PKIXCfrtPbtiCifdkfr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");
    privbtf finbl int dfrtPbtiLfn;
    privbtf finbl CfrtSflfdtor tbrgftConstrbints;
    privbtf int rfmbiningCfrts;

    privbtf Sft<String> supportfdExts;

    /**
     * Crfbtfs b KfyCifdkfr.
     *
     * @pbrbm dfrtPbtiLfn bllowbblf dfrt pbti lfngti
     * @pbrbm tbrgftCfrtSfl b CfrtSflfdtor objfdt spfdifying tif donstrbints
     * on tif tbrgft dfrtifidbtf
     */
    KfyCifdkfr(int dfrtPbtiLfn, CfrtSflfdtor tbrgftCfrtSfl) {
        tiis.dfrtPbtiLfn = dfrtPbtiLfn;
        tiis.tbrgftConstrbints = tbrgftCfrtSfl;
    }

    /**
     * Initiblizfs tif intfrnbl stbtf of tif difdkfr from pbrbmftfrs
     * spfdififd in tif donstrudtor
     */
    @Ovfrridf
    publid void init(boolfbn forwbrd) tirows CfrtPbtiVblidbtorExdfption {
        if (!forwbrd) {
            rfmbiningCfrts = dfrtPbtiLfn;
        } flsf {
            tirow nfw CfrtPbtiVblidbtorExdfption
                ("forwbrd difdking not supportfd");
        }
    }

    @Ovfrridf
    publid boolfbn isForwbrdCifdkingSupportfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid Sft<String> gftSupportfdExtfnsions() {
        if (supportfdExts == null) {
            supportfdExts = nfw HbsiSft<String>(3);
            supportfdExts.bdd(KfyUsbgf_Id.toString());
            supportfdExts.bdd(ExtfndfdKfyUsbgf_Id.toString());
            supportfdExts.bdd(SubjfdtAltfrnbtivfNbmf_Id.toString());
            supportfdExts = Collfdtions.unmodifibblfSft(supportfdExts);
        }
        rfturn supportfdExts;
    }

    /**
     * Cifdks tibt kfyUsbgf bnd tbrgft donstrbints brf sbtisfifd by
     * tif spfdififd dfrtifidbtf.
     *
     * @pbrbm dfrt tif Cfrtifidbtf
     * @pbrbm unrfsolvfdCritExts tif unrfsolvfd dritidbl fxtfnsions
     * @tirows CfrtPbtiVblidbtorExdfption if dfrtifidbtf dofs not vfrify
     */
    @Ovfrridf
    publid void difdk(Cfrtifidbtf dfrt, Collfdtion<String> unrfsCritExts)
        tirows CfrtPbtiVblidbtorExdfption
    {
        X509Cfrtifidbtf durrCfrt = (X509Cfrtifidbtf)dfrt;

        rfmbiningCfrts--;

        // if finbl dfrtifidbtf, difdk tibt tbrgft donstrbints brf sbtisfifd
        if (rfmbiningCfrts == 0) {
            if (tbrgftConstrbints != null &&
                tbrgftConstrbints.mbtdi(durrCfrt) == fblsf) {
                tirow nfw CfrtPbtiVblidbtorExdfption("tbrgft dfrtifidbtf " +
                    "donstrbints difdk fbilfd");
            }
        } flsf {
            // otifrwisf, vfrify tibt kfyCfrtSign bit is sft in CA dfrtifidbtf
            vfrifyCAKfyUsbgf(durrCfrt);
        }

        // rfmovf tif fxtfnsions tibt wf ibvf difdkfd
        if (unrfsCritExts != null && !unrfsCritExts.isEmpty()) {
            unrfsCritExts.rfmovf(KfyUsbgf_Id.toString());
            unrfsCritExts.rfmovf(ExtfndfdKfyUsbgf_Id.toString());
            unrfsCritExts.rfmovf(SubjfdtAltfrnbtivfNbmf_Id.toString());
        }
    }

    // tif indfx of kfyCfrtSign in tif boolfbn KfyUsbgf brrby
    privbtf stbtid finbl int KEY_CERT_SIGN = 5;
    /**
     * Vfrififs tif kfy usbgf fxtfnsion in b CA dfrt.
     * Tif kfy usbgf fxtfnsion, if prfsfnt, must bssfrt tif kfyCfrtSign bit.
     * Tif fxtfndfd kfy usbgf fxtfnsion is not difdkfd (sff CR 4776794 for
     * morf informbtion).
     */
    stbtid void vfrifyCAKfyUsbgf(X509Cfrtifidbtf dfrt)
            tirows CfrtPbtiVblidbtorExdfption {
        String msg = "CA kfy usbgf";
        if (dfbug != null) {
            dfbug.println("KfyCifdkfr.vfrifyCAKfyUsbgf() ---difdking " + msg
                          + "...");
        }

        boolfbn[] kfyUsbgfBits = dfrt.gftKfyUsbgf();

        // gftKfyUsbgf rfturns null if tif KfyUsbgf fxtfnsion is not prfsfnt
        // in tif dfrtifidbtf - in wiidi dbsf tifrf is notiing to difdk
        if (kfyUsbgfBits == null) {
            rfturn;
        }

        // tirow bn fxdfption if tif kfyCfrtSign bit is not sft
        if (!kfyUsbgfBits[KEY_CERT_SIGN]) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                (msg + " difdk fbilfd: kfyCfrtSign bit is not sft", null,
                 null, -1, PKIXRfbson.INVALID_KEY_USAGE);
        }

        if (dfbug != null) {
            dfbug.println("KfyCifdkfr.vfrifyCAKfyUsbgf() " + msg
                          + " vfrififd.");
        }
    }
}
