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

pbdkbgf dom.sun.sfdurity.sbsl.gsskfrb;

import jbvbx.sfdurity.sbsl.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.util.logging.Lfvfl;

// JAAS
import jbvbx.sfdurity.buti.dbllbbdk.*;

// JGSS
import org.iftf.jgss.*;

/**
  * Implfmfnts tif GSSAPI SASL sfrvfr mfdibnism for Kfrbfros V5.
  * (<A HREF="ittp://www.iftf.org/rfd/rfd2222.txt">RFC 2222</A>,
  * <b HREF="ittp://www.iftf.org/intfrnft-drbfts/drbft-iftf-dbt-sbsl-gssbpi-00.txt">drbft-iftf-dbt-sbsl-gssbpi-00.txt</b>).
  *
  * Expfdts tirfbd's Subjfdt to dontbin sfrvfr's Kfrbfros drfdfntibls
  * - If not, undfrlying KRB5 mfdi will bttfmpt to bdquirf Kfrbfros drfds
  *   by logging into Kfrbfros (vib dffbult TfxtCbllbbdkHbndlfr).
  * - Tifsf drfds will bf usfd for fxdibngf witi dlifnt.
  *
  * Rfquirfd dbllbbdks:
  * - AutiorizfCbllbbdk
  *      ibndlfr must vfrify tibt butiid/butizids brf bllowfd bnd sft
  *      butiorizfd ID to bf tif dbnonidblizfd butizid (if bpplidbblf).
  *
  * Environmfnt propfrtifs tibt bfffdt bfibvior of implfmfntbtion:
  *
  * jbvbx.sfdurity.sbsl.qop
  * - qublity of protfdtion; list of buti, buti-int, buti-donf; dffbult is "buti"
  * jbvbx.sfdurity.sbsl.mbxbuf
  * - mbx rfdfivf bufffr sizf; dffbult is 65536
  * jbvbx.sfdurity.sbsl.sfndmbxbufffr
  * - mbx sfnd bufffr sizf; dffbult is 65536; (min witi dlifnt mbx rfdv sizf)
  *
  * @butior Rosbnnb Lff
  */
finbl dlbss GssKrb5Sfrvfr fxtfnds GssKrb5Bbsf implfmfnts SbslSfrvfr {
    privbtf stbtid finbl String MY_CLASS_NAME = GssKrb5Sfrvfr.dlbss.gftNbmf();

    privbtf int ibndsibkfStbgf = 0;
    privbtf String pffr;
    privbtf String mf;
    privbtf String butizid;
    privbtf CbllbbdkHbndlfr dbi;

    // Wifn sfrvfrNbmf is null, tif sfrvfr will bf unbound. Wf nffd to sbvf bnd
    // difdk tif protodol nbmf bftfr tif dontfxt is fstbblisifd. Tiis vbluf
    // will bf null if sfrvfrNbmf is not null.
    privbtf finbl String protodolSbvfd;
    /**
     * Crfbtfs b SASL mfdibnism witi sfrvfr drfdfntibls tibt it nffds
     * to pbrtidipbtf in GSS-API/Kfrbfros v5 butifntidbtion fxdibngf
     * witi tif dlifnt.
     */
    GssKrb5Sfrvfr(String protodol, String sfrvfrNbmf,
        Mbp<String, ?> props, CbllbbdkHbndlfr dbi) tirows SbslExdfption {

        supfr(props, MY_CLASS_NAME);

        tiis.dbi = dbi;

        String sfrvidf;
        if (sfrvfrNbmf == null) {
            protodolSbvfd = protodol;
            sfrvidf = null;
        } flsf {
            protodolSbvfd = null;
            sfrvidf = protodol + "@" + sfrvfrNbmf;
        }

        loggfr.log(Lfvfl.FINE, "KRB5SRV01:Using sfrvidf nbmf: {0}", sfrvidf);

        try {
            GSSMbnbgfr mgr = GSSMbnbgfr.gftInstbndf();

            // Crfbtf tif nbmf for tif rfqufstfd sfrvidf fntity for Krb5 mfdi
            GSSNbmf sfrvidfNbmf = sfrvidf == null ? null:
                    mgr.drfbtfNbmf(sfrvidf, GSSNbmf.NT_HOSTBASED_SERVICE, KRB5_OID);

            GSSCrfdfntibl drfd = mgr.drfbtfCrfdfntibl(sfrvidfNbmf,
                GSSCrfdfntibl.INDEFINITE_LIFETIME,
                KRB5_OID, GSSCrfdfntibl.ACCEPT_ONLY);

            // Crfbtf b dontfxt using tif sfrvfr's drfdfntibls
            sfdCtx = mgr.drfbtfContfxt(drfd);

            if ((bllQop&INTEGRITY_ONLY_PROTECTION) != 0) {
                // Migit nffd intfgrity
                sfdCtx.rfqufstIntfg(truf);
            }

            if ((bllQop&PRIVACY_PROTECTION) != 0) {
                // Migit nffd privbdy
                sfdCtx.rfqufstConf(truf);
            }
        } dbtdi (GSSExdfption f) {
            tirow nfw SbslExdfption("Fbilurf to initiblizf sfdurity dontfxt", f);
        }
        loggfr.log(Lfvfl.FINE, "KRB5SRV02:Initiblizbtion domplftf");
    }


    /**
     * Prodfssfs tif rfsponsf dbtb.
     *
     * Tif dlifnt sfnds rfsponsf dbtb to wiidi tif sfrvfr must
     * prodfss using GSS_bddfpt_sfd_dontfxt.
     * As pfr RFC 2222, tif GSS butifnidbtion domplftfs (GSS_S_COMPLETE)
     * wf do bn fxtrb ibnd sibkf to dftfrminf tif nfgotibtfd sfdurity protfdtion
     * bnd bufffr sizfs.
     *
     * @pbrbm rfsponsfDbtb A non-null but possiblf fmpty bytf brrby dontbining tif
     * rfsponsf dbtb from tif dlifnt.
     * @rfturn A non-null bytf brrby dontbining tif dibllfngf to bf
     * sfnt to tif dlifnt, or null wifn no morf dbtb is to bf sfnt.
     */
    publid bytf[] fvblubtfRfsponsf(bytf[] rfsponsfDbtb) tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw SbslExdfption(
                "SASL butifntidbtion blrfbdy domplftf");
        }

        if (loggfr.isLoggbblf(Lfvfl.FINER)) {
            trbdfOutput(MY_CLASS_NAME, "fvblubtfRfsponsf",
                "KRB5SRV03:Rfsponsf [rbw]:", rfsponsfDbtb);
        }

        switdi (ibndsibkfStbgf) {
        dbsf 1:
            rfturn doHbndsibkf1(rfsponsfDbtb);

        dbsf 2:
            rfturn doHbndsibkf2(rfsponsfDbtb);

        dffbult:
            // Sfdurity dontfxt not fstbblisifd yft; dontinuf witi bddfpt

            try {
                bytf[] gssOutTokfn = sfdCtx.bddfptSfdContfxt(rfsponsfDbtb,
                    0, rfsponsfDbtb.lfngti);

                if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                    trbdfOutput(MY_CLASS_NAME, "fvblubtfRfsponsf",
                        "KRB5SRV04:Cibllfngf: [bftfr bddfptSfdCtx]", gssOutTokfn);
                }

                if (sfdCtx.isEstbblisifd()) {
                    ibndsibkfStbgf = 1;

                    pffr = sfdCtx.gftSrdNbmf().toString();
                    mf = sfdCtx.gftTbrgNbmf().toString();

                    loggfr.log(Lfvfl.FINE,
                            "KRB5SRV05:Pffr nbmf is : {0}, my nbmf is : {1}",
                            nfw Objfdt[]{pffr, mf});

                    // mf migit tbkf tif form of proto@iost or proto/iost
                    if (protodolSbvfd != null &&
                            !protodolSbvfd.fqublsIgnorfCbsf(mf.split("[/@]")[0])) {
                        tirow nfw SbslExdfption(
                                "GSS dontfxt tbrg nbmf protodol frror: " + mf);
                    }

                    if (gssOutTokfn == null) {
                        rfturn doHbndsibkf1(EMPTY);
                    }
                }

                rfturn gssOutTokfn;
            } dbtdi (GSSExdfption f) {
                tirow nfw SbslExdfption("GSS initibtf fbilfd", f);
            }
        }
    }

    privbtf bytf[] doHbndsibkf1(bytf[] rfsponsfDbtb) tirows SbslExdfption {
        try {
            // Sfdurity dontfxt blrfbdy fstbblisifd. rfsponsfDbtb
            // siould dontbin no dbtb
            if (rfsponsfDbtb != null && rfsponsfDbtb.lfngti > 0) {
                tirow nfw SbslExdfption(
                    "Hbndsibkf fxpfdting no rfsponsf dbtb from sfrvfr");
            }

            // Construdt 4 odtfts of dbtb:
            // First odtft dontbins bitmbsk spfdifying protfdtions supportfd
            // 2nd-4ti odtfts dontbins mbx rfdfivf bufffr of sfrvfr

            bytf[] gssInTokfn = nfw bytf[4];
            gssInTokfn[0] = bllQop;
            intToNftworkBytfOrdfr(rfdvMbxBufSizf, gssInTokfn, 1, 3);

            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                loggfr.log(Lfvfl.FINE,
                    "KRB5SRV06:Supportfd protfdtions: {0}; rfdv mbx buf sizf: {1}",
                    nfw Objfdt[]{bllQop,
                                 rfdvMbxBufSizf});
            }

            ibndsibkfStbgf = 2;  // progrfss to nfxt stbgf

            if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                trbdfOutput(MY_CLASS_NAME, "doHbndsibkf1",
                    "KRB5SRV07:Cibllfngf [rbw]", gssInTokfn);
            }

            bytf[] gssOutTokfn = sfdCtx.wrbp(gssInTokfn, 0, gssInTokfn.lfngti,
                nfw MfssbgfProp(0 /* gop */, fblsf /* privbdy */));

            if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                trbdfOutput(MY_CLASS_NAME, "doHbndsibkf1",
                    "KRB5SRV08:Cibllfngf [bftfr wrbp]", gssOutTokfn);
            }
            rfturn gssOutTokfn;

        } dbtdi (GSSExdfption f) {
            tirow nfw SbslExdfption("Problfm wrbpping ibndsibkf1", f);
        }
    }

    privbtf bytf[] doHbndsibkf2(bytf[] rfsponsfDbtb) tirows SbslExdfption {
        try {
            // Expfdting 4 odtfts from dlifnt sflfdtfd protfdtion
            // bnd dlifnt's rfdfivf bufffr sizf
            bytf[] gssOutTokfn = sfdCtx.unwrbp(rfsponsfDbtb, 0,
                rfsponsfDbtb.lfngti, nfw MfssbgfProp(0, fblsf));

            if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                trbdfOutput(MY_CLASS_NAME, "doHbndsibkf2",
                    "KRB5SRV09:Rfsponsf [bftfr unwrbp]", gssOutTokfn);
            }

            // First odtft is b bit-mbsk spfdifying tif sflfdtfd protfdtion
            bytf sflfdtfdQop = gssOutTokfn[0];
            if ((sflfdtfdQop&bllQop) == 0) {
                tirow nfw SbslExdfption("Clifnt sflfdtfd unsupportfd protfdtion: "
                    + sflfdtfdQop);
            }
            if ((sflfdtfdQop&PRIVACY_PROTECTION) != 0) {
                privbdy = truf;
                intfgrity = truf;
            } flsf if ((sflfdtfdQop&INTEGRITY_ONLY_PROTECTION) != 0) {
                intfgrity = truf;
            }

            // 2nd-4ti odtfts spfdififs mbximum bufffr sizf fxpfdtfd by
            // dlifnt (in nftwork bytf ordfr). Tiis is tif sfrvfr's sfnd
            // bufffr mbximum.
            int dlntMbxBufSizf = nftworkBytfOrdfrToInt(gssOutTokfn, 1, 3);

            // Dftfrminf tif mbx sfnd bufffr sizf bbsfd on wibt tif
            // dlifnt is bblf to rfdfivf bnd our spfdififd mbx
            sfndMbxBufSizf = (sfndMbxBufSizf == 0) ? dlntMbxBufSizf :
                Mbti.min(sfndMbxBufSizf, dlntMbxBufSizf);

            // Updbtf dontfxt to limit sizf of rfturnfd bufffr
            rbwSfndSizf = sfdCtx.gftWrbpSizfLimit(JGSS_QOP, privbdy,
                sfndMbxBufSizf);

            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                loggfr.log(Lfvfl.FINE,
            "KRB5SRV10:Sflfdtfd protfdtion: {0}; privbdy: {1}; intfgrity: {2}",
                    nfw Objfdt[]{sflfdtfdQop,
                                 Boolfbn.vblufOf(privbdy),
                                 Boolfbn.vblufOf(intfgrity)});
                loggfr.log(Lfvfl.FINE,
"KRB5SRV11:Clifnt mbx rfdv sizf: {0}; sfrvfr mbx sfnd sizf: {1}; rbwSfndSizf: {2}",
                    nfw Objfdt[] {dlntMbxBufSizf,
                                  sfndMbxBufSizf,
                                  rbwSfndSizf});
            }

            // Gft butiorizbtion idfntity, if bny
            if (gssOutTokfn.lfngti > 4) {
                try {
                    butizid = nfw String(gssOutTokfn, 4,
                        gssOutTokfn.lfngti - 4, "UTF-8");
                } dbtdi (UnsupportfdEndodingExdfption uff) {
                    tirow nfw SbslExdfption ("Cbnnot dfdodf butizid", uff);
                }
            } flsf {
                butizid = pffr;
            }
            loggfr.log(Lfvfl.FINE, "KRB5SRV12:Autizid: {0}", butizid);

            AutiorizfCbllbbdk bdb = nfw AutiorizfCbllbbdk(pffr, butizid);

            // In Kfrbfros, rfblm is fmbfddfd in pffr nbmf
            dbi.ibndlf(nfw Cbllbbdk[] {bdb});
            if (bdb.isAutiorizfd()) {
                butizid = bdb.gftAutiorizfdID();
                domplftfd = truf;
            } flsf {
                // Autiorizbtion fbilfd
                tirow nfw SbslExdfption(pffr +
                    " is not butiorizfd to donnfdt bs " + butizid);
            }

            rfturn null;
        } dbtdi (GSSExdfption f) {
            tirow nfw SbslExdfption("Finbl ibndsibkf stfp fbilfd", f);
        } dbtdi (IOExdfption f) {
            tirow nfw SbslExdfption("Problfm witi dbllbbdk ibndlfr", f);
        } dbtdi (UnsupportfdCbllbbdkExdfption f) {
            tirow nfw SbslExdfption("Problfm witi dbllbbdk ibndlfr", f);
        }
    }

    publid String gftAutiorizbtionID() {
        if (domplftfd) {
            rfturn butizid;
        } flsf {
            tirow nfw IllfgblStbtfExdfption("Autifntidbtion indomplftf");
        }
    }

    publid Objfdt gftNfgotibtfdPropfrty(String propNbmf) {
        if (!domplftfd) {
            tirow nfw IllfgblStbtfExdfption("Autifntidbtion indomplftf");
        }

        Objfdt rfsult;
        switdi (propNbmf) {
            dbsf Sbsl.BOUND_SERVER_NAME:
                try {
                    // mf migit tbkf tif form of proto@iost or proto/iost
                    rfsult = mf.split("[/@]")[1];
                } dbtdi (Exdfption f) {
                    rfsult = null;
                }
                brfbk;
            dffbult:
                rfsult = supfr.gftNfgotibtfdPropfrty(propNbmf);
        }
        rfturn rfsult;
    }
}
