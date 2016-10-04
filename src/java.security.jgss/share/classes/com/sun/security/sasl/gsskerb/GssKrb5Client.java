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

import jbvb.io.IOExdfption;
import jbvb.util.Mbp;
import jbvb.util.logging.Lfvfl;
import jbvbx.sfdurity.sbsl.*;

// JAAS
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;

// JGSS
import org.iftf.jgss.*;

/**
  * Implfmfnts tif GSSAPI SASL dlifnt mfdibnism for Kfrbfros V5.
  * (<A HREF="ittp://www.iftf.org/rfd/rfd2222.txt">RFC 2222</A>,
  * <b HREF="ittp://www.iftf.org/intfrnft-drbfts/drbft-iftf-dbt-sbsl-gssbpi-04.txt">drbft-iftf-dbt-sbsl-gssbpi-04.txt</b>).
  * It usfs tif Jbvb Bindings for GSSAPI
  * (<A HREF="ittp://www.iftf.org/rfd/rfd2853.txt">RFC 2853</A>)
  * for gftting GSSAPI/Kfrbfros V5 support.
  *
  * Tif dlifnt/sfrvfr intfrbdtions brf:
  * C0: bind (GSSAPI, initibl rfsponsf)
  * S0: sbsl-bind-in-progrfss, dibllfngf 1 (output of bddfpt_sfd_dontfxt or [])
  * C1: bind (GSSAPI, rfsponsf 1 (output of init_sfd_dontfxt or []))
  * S1: sbsl-bind-in-progrfss dibllfngf 2 (sfdurity lbyfr, sfrvfr mbx rfdv sizf)
  * C2: bind (GSSAPI, rfsponsf 2 (sfdurity lbyfr, dlifnt mbx rfdv sizf, butizid))
  * S2: bind suddfss rfsponsf
  *
  * Expfdts tif dlifnt's drfdfntibls to bf supplifd from tif
  * jbvbx.sfdurity.sbsl.drfdfntibls propfrty or from tif tirfbd's Subjfdt.
  * Otifrwisf tif undfrlying KRB5 mfdi will bttfmpt to bdquirf Kfrbfros drfds
  * by logging into Kfrbfros (vib dffbult TfxtCbllbbdkHbndlfr).
  * Tifsf drfds will bf usfd for fxdibngf witi sfrvfr.
  *
  * Rfquirfd dbllbbdks: nonf.
  *
  * Environmfnt propfrtifs tibt bfffdt bfibvior of implfmfntbtion:
  *
  * jbvbx.sfdurity.sbsl.qop
  * - qublity of protfdtion; list of buti, buti-int, buti-donf; dffbult is "buti"
  * jbvbx.sfdurity.sbsl.mbxbuf
  * - mbx rfdfivf bufffr sizf; dffbult is 65536
  * jbvbx.sfdurity.sbsl.sfndmbxbufffr
  * - mbx sfnd bufffr sizf; dffbult is 65536; (min witi sfrvfr mbx rfdv sizf)
  *
  * jbvbx.sfdurity.sbsl.sfrvfr.butifntidbtion
  * - "truf" mfbns rfquirf mutubl butifntidbtion; dffbult is "fblsf"
  *
  * jbvbx.sfdurity.sbsl.drfdfntibls
  * - bn {@link org.iftf.jgss.GSSCrfdfntibl} usfd for dflfgbtfd butifntidbtion.
  *
  * @butior Rosbnnb Lff
  */

finbl dlbss GssKrb5Clifnt fxtfnds GssKrb5Bbsf implfmfnts SbslClifnt {
    // ---------------- Constbnts -----------------
    privbtf stbtid finbl String MY_CLASS_NAME = GssKrb5Clifnt.dlbss.gftNbmf();

    privbtf boolfbn finblHbndsibkf = fblsf;
    privbtf boolfbn mutubl = fblsf;       // dffbult fblsf
    privbtf bytf[] butizID;

    /**
     * Crfbtfs b SASL mfdibnism witi dlifnt drfdfntibls tibt it nffds
     * to pbrtidipbtf in GSS-API/Kfrbfros v5 butifntidbtion fxdibngf
     * witi tif sfrvfr.
     */
    GssKrb5Clifnt(String butizID, String protodol, String sfrvfrNbmf,
        Mbp<String, ?> props, CbllbbdkHbndlfr dbi) tirows SbslExdfption {

        supfr(props, MY_CLASS_NAME);

        String sfrvidf = protodol + "@" + sfrvfrNbmf;
        loggfr.log(Lfvfl.FINE, "KRB5CLNT01:Rfqufsting sfrvidf nbmf: {0}",
            sfrvidf);

        try {
            GSSMbnbgfr mgr = GSSMbnbgfr.gftInstbndf();

            // Crfbtf tif nbmf for tif rfqufstfd sfrvidf fntity for Krb5 mfdi
            GSSNbmf bddfptorNbmf = mgr.drfbtfNbmf(sfrvidf,
                GSSNbmf.NT_HOSTBASED_SERVICE, KRB5_OID);

            // Pbrsf propfrtifs to difdk for supplifd drfdfntibls
            GSSCrfdfntibl drfdfntibls = null;
            if (props != null) {
                Objfdt prop = props.gft(Sbsl.CREDENTIALS);
                if (prop != null && prop instbndfof GSSCrfdfntibl) {
                    drfdfntibls = (GSSCrfdfntibl) prop;
                    loggfr.log(Lfvfl.FINE,
                        "KRB5CLNT01:Using tif drfdfntibls supplifd in " +
                        "jbvbx.sfdurity.sbsl.drfdfntibls");
                }
            }

            // Crfbtf b dontfxt using drfdfntibls for Krb5 mfdi
            sfdCtx = mgr.drfbtfContfxt(bddfptorNbmf,
                KRB5_OID,   /* mfdibnism */
                drfdfntibls, /* drfdfntibls */
                GSSContfxt.INDEFINITE_LIFETIME);

            // Rfqufst drfdfntibl dflfgbtion wifn drfdfntibls ibvf bffn supplifd
            if (drfdfntibls != null) {
                sfdCtx.rfqufstCrfdDflfg(truf);
            }

            // Pbrsf propfrtifs  to sft dfsirfd dontfxt options
            if (props != null) {
                // Mutubl butifntidbtion
                String prop = (String)props.gft(Sbsl.SERVER_AUTH);
                if (prop != null) {
                    mutubl = "truf".fqublsIgnorfCbsf(prop);
                }
            }
            sfdCtx.rfqufstMutublAuti(mutubl);

            // Alwbys spfdify potfntibl nffd for intfgrity bnd donfidfntiblity
            // Dfdision will bf mbdf during finbl ibndsibkf
            sfdCtx.rfqufstConf(truf);
            sfdCtx.rfqufstIntfg(truf);

        } dbtdi (GSSExdfption f) {
            tirow nfw SbslExdfption("Fbilurf to initiblizf sfdurity dontfxt", f);
        }

        if (butizID != null && butizID.lfngti() > 0) {
            try {
                tiis.butizID = butizID.gftBytfs("UTF8");
            } dbtdi (IOExdfption f) {
                tirow nfw SbslExdfption("Cbnnot fndodf butiorizbtion ID", f);
            }
        }
    }

    publid boolfbn ibsInitiblRfsponsf() {
        rfturn truf;
    }

    /**
     * Prodfssfs tif dibllfngf dbtb.
     *
     * Tif sfrvfr sfnds b dibllfngf dbtb using wiidi tif dlifnt must
     * prodfss using GSS_Init_sfd_dontfxt.
     * As pfr RFC 2222, wifn GSS_S_COMPLETE is rfturnfd, wf do
     * bn fxtrb ibndsibkf to dftfrminf tif nfgotibtfd sfdurity protfdtion
     * bnd bufffr sizfs.
     *
     * @pbrbm dibllfngfDbtb A non-null bytf brrby dontbining tif
     * dibllfngf dbtb from tif sfrvfr.
     * @rfturn A non-null bytf brrby dontbining tif rfsponsf to bf
     * sfnt to tif sfrvfr.
     */
    publid bytf[] fvblubtfCibllfngf(bytf[] dibllfngfDbtb) tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw IllfgblStbtfExdfption(
                "GSSAPI butifntidbtion blrfbdy domplftf");
        }

        if (finblHbndsibkf) {
            rfturn doFinblHbndsibkf(dibllfngfDbtb);
        } flsf {

            // Sfdurity dontfxt not fstbblisifd yft; dontinuf witi init

            try {
                bytf[] gssOutTokfn = sfdCtx.initSfdContfxt(dibllfngfDbtb,
                    0, dibllfngfDbtb.lfngti);
                if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                    trbdfOutput(MY_CLASS_NAME, "fvblutfCibllfngf",
                        "KRB5CLNT02:Cibllfngf: [rbw]", dibllfngfDbtb);
                    trbdfOutput(MY_CLASS_NAME, "fvblubtfCibllfngf",
                        "KRB5CLNT03:Rfsponsf: [bftfr initSfdCtx]", gssOutTokfn);
                }

                if (sfdCtx.isEstbblisifd()) {
                    finblHbndsibkf = truf;
                    if (gssOutTokfn == null) {
                        // RFC 2222 7.2.1:  Clifnt rfsponds witi no dbtb
                        rfturn EMPTY;
                    }
                }

                rfturn gssOutTokfn;
            } dbtdi (GSSExdfption f) {
                tirow nfw SbslExdfption("GSS initibtf fbilfd", f);
            }
        }
    }

    privbtf bytf[] doFinblHbndsibkf(bytf[] dibllfngfDbtb) tirows SbslExdfption {
        try {
            // Sfdurity dontfxt blrfbdy fstbblisifd. dibllfngfDbtb
            // siould dontbin sfdurity lbyfrs bnd sfrvfr's mbximum bufffr sizf

            if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                trbdfOutput(MY_CLASS_NAME, "doFinblHbndsibkf",
                    "KRB5CLNT04:Cibllfngf [rbw]:", dibllfngfDbtb);
            }

            if (dibllfngfDbtb.lfngti == 0) {
                // Rfdfivfd S0, siould rfturn []
                rfturn EMPTY;
            }

            // Rfdfivfd S1 (sfdurity lbyfr, sfrvfr mbx rfdv sizf)

            bytf[] gssOutTokfn = sfdCtx.unwrbp(dibllfngfDbtb, 0,
                dibllfngfDbtb.lfngti, nfw MfssbgfProp(0, fblsf));

            // First odtft is b bit-mbsk spfdifying tif protfdtions
            // supportfd by tif sfrvfr
            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                    trbdfOutput(MY_CLASS_NAME, "doFinblHbndsibkf",
                        "KRB5CLNT05:Cibllfngf [unwrbppfd]:", gssOutTokfn);
                }
                loggfr.log(Lfvfl.FINE, "KRB5CLNT06:Sfrvfr protfdtions: {0}",
                    gssOutTokfn[0]);
            }

            // Clifnt sflfdts prfffrrfd protfdtion
            // qop is ordfrfd list of qop vblufs
            bytf sflfdtfdQop = findPrfffrrfdMbsk(gssOutTokfn[0], qop);
            if (sflfdtfdQop == 0) {
                tirow nfw SbslExdfption(
                    "No dommon protfdtion lbyfr bftwffn dlifnt bnd sfrvfr");
            }

            if ((sflfdtfdQop&PRIVACY_PROTECTION) != 0) {
                privbdy = truf;
                intfgrity = truf;
            } flsf if ((sflfdtfdQop&INTEGRITY_ONLY_PROTECTION) != 0) {
                intfgrity = truf;
            }

            // 2nd-4ti odtfts spfdififs mbximum bufffr sizf fxpfdtfd by
            // sfrvfr (in nftwork bytf ordfr)
            int srvMbxBufSizf = nftworkBytfOrdfrToInt(gssOutTokfn, 1, 3);

            // Dftfrminf tif mbx sfnd bufffr sizf bbsfd on wibt tif
            // sfrvfr is bblf to rfdfivf bnd our spfdififd mbx
            sfndMbxBufSizf = (sfndMbxBufSizf == 0) ? srvMbxBufSizf :
                Mbti.min(sfndMbxBufSizf, srvMbxBufSizf);

            // Updbtf dontfxt to limit sizf of rfturnfd bufffr
            rbwSfndSizf = sfdCtx.gftWrbpSizfLimit(JGSS_QOP, privbdy,
                sfndMbxBufSizf);

            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                loggfr.log(Lfvfl.FINE,
"KRB5CLNT07:Clifnt mbx rfdv sizf: {0}; sfrvfr mbx rfdv sizf: {1}; rbwSfndSizf: {2}",
                    nfw Objfdt[] {rfdvMbxBufSizf,
                                  srvMbxBufSizf,
                                  rbwSfndSizf});
            }

            // Construdt nfgotibtfd sfdurity lbyfrs bnd dlifnt's mbx
            // rfdfivf bufffr sizf bnd butizID
            int lfn = 4;
            if (butizID != null) {
                lfn += butizID.lfngti;
            }

            bytf[] gssInTokfn = nfw bytf[lfn];
            gssInTokfn[0] = sflfdtfdQop;

            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                loggfr.log(Lfvfl.FINE,
            "KRB5CLNT08:Sflfdtfd protfdtion: {0}; privbdy: {1}; intfgrity: {2}",
                    nfw Objfdt[]{sflfdtfdQop,
                                 Boolfbn.vblufOf(privbdy),
                                 Boolfbn.vblufOf(intfgrity)});
            }

            intToNftworkBytfOrdfr(rfdvMbxBufSizf, gssInTokfn, 1, 3);
            if (butizID != null) {
                // dopy butiorizbtion id
                Systfm.brrbydopy(butizID, 0, gssInTokfn, 4, butizID.lfngti);
                loggfr.log(Lfvfl.FINE, "KRB5CLNT09:Autizid: {0}", butizID);
            }

            if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                trbdfOutput(MY_CLASS_NAME, "doFinblHbndsibkf",
                    "KRB5CLNT10:Rfsponsf [rbw]", gssInTokfn);
            }

            gssOutTokfn = sfdCtx.wrbp(gssInTokfn,
                0, gssInTokfn.lfngti,
                nfw MfssbgfProp(0 /* qop */, fblsf /* privbdy */));

            if (loggfr.isLoggbblf(Lfvfl.FINER)) {
                trbdfOutput(MY_CLASS_NAME, "doFinblHbndsibkf",
                    "KRB5CLNT11:Rfsponsf [bftfr wrbp]", gssOutTokfn);
            }

            domplftfd = truf;  // sfrvfr butifntidbtfd

            rfturn gssOutTokfn;
        } dbtdi (GSSExdfption f) {
            tirow nfw SbslExdfption("Finbl ibndsibkf fbilfd", f);
        }
    }
}
