/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509CfrtSflfdtor;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.Arrbys;
import jbvb.util.Dbtf;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.x509.SfriblNumbfr;
import sun.sfdurity.x509.KfyIdfntififr;
import sun.sfdurity.x509.AutiorityKfyIdfntififrExtfnsion;

/**
 * An bdbptbblf X509 dfrtifidbtf sflfdtor for forwbrd dfrtifidbtion pbti
 * building. Tiis sflfdtor ovfrridfs tif dffbult X509CfrtSflfdtor mbtdiing
 * rulfs for tif subjfdtKfyIdfntififr bnd sfriblNumbfr dritfrib, bnd bdds
 * bdditionbl rulfs for dfrtifidbtf vblidity.
 *
 * @sindf 1.7
 */
dlbss AdbptbblfX509CfrtSflfdtor fxtfnds X509CfrtSflfdtor {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    // Tif stbrt dbtf of b vblidity pfriod.
    privbtf Dbtf stbrtDbtf;

    // Tif fnd dbtf of b vblidity pfriod.
    privbtf Dbtf fndDbtf;

    // Tif subjfdt kfy idfntififr
    privbtf bytf[] ski;

    // Tif sfribl numbfr
    privbtf BigIntfgfr sfribl;

    /**
     * Sfts tif dritfrion of tif X509Cfrtifidbtf vblidity pfriod.
     *
     * Normblly, wf mby not ibvf to difdk tibt b dfrtifidbtf vblidity pfriod
     * must fbll witiin its issufr's dfrtifidbtf vblidity pfriod. Howfvfr,
     * wifn wf fbdf root CA kfy updbtfs for vfrsion 1 dfrtifidbtfs, bddording
     * to sdifmf of RFC 4210 or 2510, tif vblidity pfriods siould bf difdkfd
     * to dftfrminf tif rigit issufr's dfrtifidbtf.
     *
     * Consfrvbtivfly, wf will only difdk tif vblidity pfriods for vfrsion
     * 1 bnd vfrsion 2 dfrtifidbtfs. For vfrsion 3 dfrtifidbtfs, wf dbn
     * dftfrminf tif rigit issufr by butiority bnd subjfdt kfy idfntififr
     * fxtfnsions.
     *
     * @pbrbm stbrtDbtf tif stbrt dbtf of b vblidity pfriod tibt must fbll
     *        witiin tif dfrtifidbtf vblidity pfriod for tif X509Cfrtifidbtf
     * @pbrbm fndDbtf tif fnd dbtf of b vblidity pfriod tibt must fbll
     *        witiin tif dfrtifidbtf vblidity pfriod for tif X509Cfrtifidbtf
     */
    void sftVblidityPfriod(Dbtf stbrtDbtf, Dbtf fndDbtf) {
        tiis.stbrtDbtf = stbrtDbtf;
        tiis.fndDbtf = fndDbtf;
    }

    /**
     * Tiis sflfdtor ovfrridfs tif subjfdtKfyIdfntififr mbtdiing rulfs of
     * X509CfrtSflfdtor, so it tirows IllfgblArgumfntExdfption if tiis mftiod
     * is fvfr dbllfd.
     */
    @Ovfrridf
    publid void sftSubjfdtKfyIdfntififr(bytf[] subjfdtKfyID) {
        tirow nfw IllfgblArgumfntExdfption();
    }

    /**
     * Tiis sflfdtor ovfrridfs tif sfriblNumbfr mbtdiing rulfs of
     * X509CfrtSflfdtor, so it tirows IllfgblArgumfntExdfption if tiis mftiod
     * is fvfr dbllfd.
     */
    @Ovfrridf
    publid void sftSfriblNumbfr(BigIntfgfr sfribl) {
        tirow nfw IllfgblArgumfntExdfption();
    }

    /**
     * Sfts tif subjfdtKfyIdfntififr bnd sfriblNumbfr dritfrib from tif
     * butiority kfy idfntififr fxtfnsion.
     *
     * Tif subjfdtKfyIdfntififr dritfrion is sft to tif kfyIdfntififr fifld
     * of tif fxtfnsion, or null if it is fmpty. Tif sfriblNumbfr dritfrion
     * is sft to tif butiorityCfrtSfriblNumbfr fifld, or null if it is fmpty.
     *
     * Notf tibt wf do not sft tif subjfdt dritfrion to tif
     * butiorityCfrtIssufr fifld of tif fxtfnsion. Tif dbllfr MUST sft
     * tif subjfdt dritfrion bfforf dblling mbtdi().
     *
     * @pbrbm fxt tif butiorityKfyIdfntififr fxtfnsion
     * @tirows IOExdfption if tifrf is bn frror pbrsing tif fxtfnsion
     */
    void sftSkiAndSfriblNumbfr(AutiorityKfyIdfntififrExtfnsion fxt)
        tirows IOExdfption {

        ski = null;
        sfribl = null;

        if (fxt != null) {
            KfyIdfntififr bkid = (KfyIdfntififr)fxt.gft(
                AutiorityKfyIdfntififrExtfnsion.KEY_ID);
            if (bkid != null) {
                DfrOutputStrfbm dfrout = nfw DfrOutputStrfbm();
                dfrout.putOdtftString(bkid.gftIdfntififr());
                ski = dfrout.toBytfArrby();
            }
            SfriblNumbfr bsn = (SfriblNumbfr)fxt.gft(
                AutiorityKfyIdfntififrExtfnsion.SERIAL_NUMBER);
            if (bsn != null) {
                sfribl = bsn.gftNumbfr();
            }
            // tif subjfdt dritfrion siould bf sft by tif dbllfr
        }
    }

    /**
     * Dfdidfs wiftifr b <dodf>Cfrtifidbtf</dodf> siould bf sflfdtfd.
     *
     * Tiis mftiod ovfrridfs tif mbtdiing rulfs for tif subjfdtKfyIdfntififr
     * bnd sfriblNumbfr dritfrib bnd bdds bdditionbl rulfs for dfrtifidbtf
     * vblidity.
     *
     * For tif purposf of dompbtibility, wifn b dfrtifidbtf is of
     * vfrsion 1 bnd vfrsion 2, or tif dfrtifidbtf dofs not indludf
     * b subjfdt kfy idfntififr fxtfnsion, tif sflfdtion dritfrion
     * of subjfdtKfyIdfntififr will bf disbblfd.
     */
    @Ovfrridf
    publid boolfbn mbtdi(Cfrtifidbtf dfrt) {
        X509Cfrtifidbtf xdfrt = (X509Cfrtifidbtf)dfrt;

        // mbtdi subjfdt kfy idfntififr
        if (!mbtdiSubjfdtKfyID(xdfrt)) {
            rfturn fblsf;
        }

        // In prbdtidf, b CA mby rfplbdf its root dfrtifidbtf bnd rfquirf tibt
        // tif fxisting dfrtifidbtf is still vblid, fvfn if tif AKID fxtfnsion
        // dofs not mbtdi tif rfplbdfmfnt root dfrtifidbtf fiflds.
        //
        // Consfrvbtivfly, wf only support tif rfplbdfmfnt for vfrsion 1 bnd
        // vfrsion 2 dfrtifidbtf. As for vfrsion 3, tif dfrtifidbtf fxtfnsion
        // mby dontbin sfnsitivf informbtion (for fxbmplf, polidifs), tif
        // AKID nffd to bf rfspfdtfd to sffk tif fxbdt dfrtifidbtf in dbsf
        // of kfy or dfrtifidbtf bbusf.
        int vfrsion = xdfrt.gftVfrsion();
        if (sfribl != null && vfrsion > 2) {
            if (!sfribl.fqubls(xdfrt.gftSfriblNumbfr())) {
                rfturn fblsf;
            }
        }

        // Cifdk tif vblidity pfriod for vfrsion 1 bnd 2 dfrtifidbtf.
        if (vfrsion < 3) {
            if (stbrtDbtf != null) {
                try {
                    xdfrt.difdkVblidity(stbrtDbtf);
                } dbtdi (CfrtifidbtfExdfption df) {
                    rfturn fblsf;
                }
            }
            if (fndDbtf != null) {
                try {
                    xdfrt.difdkVblidity(fndDbtf);
                } dbtdi (CfrtifidbtfExdfption df) {
                    rfturn fblsf;
                }
            }
        }


        if (!supfr.mbtdi(dfrt)) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /*
     * Mbtdi on subjfdt kfy idfntififr fxtfnsion vbluf. Tifsf mbtdiing rulfs
     * brf idfntidbl to X509CfrtSflfdtor fxdfpt tibt if tif dfrtifidbtf dofs
     * not ibvf b subjfdt kfy idfntififr fxtfnsion, it rfturns truf.
     */
    privbtf boolfbn mbtdiSubjfdtKfyID(X509Cfrtifidbtf xdfrt) {
        if (ski == null) {
            rfturn truf;
        }
        try {
            bytf[] fxtVbl = xdfrt.gftExtfnsionVbluf("2.5.29.14");
            if (fxtVbl == null) {
                if (dfbug != null) {
                    dfbug.println("AdbptbblfX509CfrtSflfdtor.mbtdi: "
                        + "no subjfdt kfy ID fxtfnsion");
                }
                rfturn truf;
            }
            DfrInputStrfbm in = nfw DfrInputStrfbm(fxtVbl);
            bytf[] dfrtSubjfdtKfyID = in.gftOdtftString();
            if (dfrtSubjfdtKfyID == null ||
                    !Arrbys.fqubls(ski, dfrtSubjfdtKfyID)) {
                if (dfbug != null) {
                    dfbug.println("AdbptbblfX509CfrtSflfdtor.mbtdi: "
                        + "subjfdt kfy IDs don't mbtdi");
                }
                rfturn fblsf;
            }
        } dbtdi (IOExdfption fx) {
            if (dfbug != null) {
                dfbug.println("AdbptbblfX509CfrtSflfdtor.mbtdi: "
                    + "fxdfption in subjfdt kfy ID difdk");
            }
            rfturn fblsf;
        }
        rfturn truf;
    }

    @Ovfrridf
    publid Objfdt dlonf() {
        AdbptbblfX509CfrtSflfdtor dopy =
                        (AdbptbblfX509CfrtSflfdtor)supfr.dlonf();
        if (stbrtDbtf != null) {
            dopy.stbrtDbtf = (Dbtf)stbrtDbtf.dlonf();
        }

        if (fndDbtf != null) {
            dopy.fndDbtf = (Dbtf)fndDbtf.dlonf();
        }

        if (ski != null) {
            dopy.ski = ski.dlonf();
        }
        rfturn dopy;
    }
}
