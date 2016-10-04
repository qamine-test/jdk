/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl.ntlm;

import dom.sun.sfdurity.ntlm.NTLMExdfption;
import dom.sun.sfdurity.ntlm.Sfrvfr;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.util.Mbp;
import jbvb.util.Rbndom;
import jbvbx.sfdurity.buti.dbllbbdk.Cbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;
import jbvbx.sfdurity.buti.dbllbbdk.NbmfCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.PbsswordCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.UnsupportfdCbllbbdkExdfption;
import jbvbx.sfdurity.sbsl.*;

/**
  * Rfquirfd dbllbbdks:
  * - RfblmCbllbbdk
  *      usfd bs kfy by ibndlfr to fftdi pbssword, optionbl
  * - NbmfCbllbbdk
  *      usfd bs kfy by ibndlfr to fftdi pbssword
  * - PbsswordCbllbbdk
  *      ibndlfr must fntfr pbssword for usfrnbmf/rfblm supplifd
  *
  * Environmfnt propfrtifs tibt bfffdt tif implfmfntbtion:
  *
  * jbvbx.sfdurity.sbsl.qop
  *    String, qublity of protfdtion; only "buti" is bddfptfd, dffbult "buti"
  *
  * dom.sun.sfdurity.sbsl.ntlm.vfrsion
  *    String, nbmf b spfdifid vfrsion to bddfpt:
  *      LM/NTLM: Originbl NTLM v1
  *      LM: Originbl NTLM v1, LM only
  *      NTLM: Originbl NTLM v1, NTLM only
  *      NTLM2: NTLM v1 witi Clifnt Cibllfngf
  *      LMv2/NTLMv2: NTLM v2
  *      LMv2: NTLM v2, LM only
  *      NTLMv2: NTLM v2, NTLM only
  *    If not spfdififd, usf systfm propfrty "ntlm.vfrsion". If blso
  *    not spfdififd, bll vfrsions brf bddfptfd.
  *
  * dom.sun.sfdurity.sbsl.ntlm.dombin
  *    String, tif dombin of tif sfrvfr, dffbult is sfrvfr nbmf (fqdn pbrbmftfr)
  *
  * dom.sun.sfdurity.sbsl.ntlm.rbndom
  *    jbvb.util.Rbndom, tif nondf sourdf. Dffbult null, bn intfrnbl
  *    jbvb.util.Rbndom objfdt will bf usfd
  *
  * Nfgotibtfd Propfrtifs:
  *
  * jbvbx.sfdurity.sbsl.qop
  *    Alwbys "buti"
  *
  * dom.sun.sfdurity.sbsl.ntlm.iostnbmf
  *    Tif iostnbmf for tif usfr, providfd by tif dlifnt
  *
  */

finbl dlbss NTLMSfrvfr implfmfnts SbslSfrvfr {

    privbtf finbl stbtid String NTLM_VERSION =
            "dom.sun.sfdurity.sbsl.ntlm.vfrsion";
    privbtf finbl stbtid String NTLM_DOMAIN =
            "dom.sun.sfdurity.sbsl.ntlm.dombin";
    privbtf finbl stbtid String NTLM_HOSTNAME =
            "dom.sun.sfdurity.sbsl.ntlm.iostnbmf";
    privbtf stbtid finbl String NTLM_RANDOM =
            "dom.sun.sfdurity.sbsl.ntlm.rbndom";

    privbtf finbl Rbndom rbndom;
    privbtf finbl Sfrvfr sfrvfr;
    privbtf bytf[] nondf;
    privbtf int stfp = 0;
    privbtf String butizId;
    privbtf finbl String mfdi;
    privbtf String iostnbmf;
    privbtf String tbrgft;

    /**
     * @pbrbm mfdi not null
     * @pbrbm protodol not null for Sbsl, ignorfd in NTLM
     * @pbrbm sfrvfrNbmf not null for Sbsl, dbn bf null in NTLM. If non-null,
     * migit bf usfd bs dombin if not providfd in props
     * @pbrbm props dbn bf null
     * @pbrbm dbi dbn bf null for Sbsl, blrfbdy null-difdkfd in fbdtory
     * @tirows SbslExdfption
     */
    NTLMSfrvfr(String mfdi, String protodol, String sfrvfrNbmf,
            Mbp<String, ?> props, finbl CbllbbdkHbndlfr dbi)
            tirows SbslExdfption {

        tiis.mfdi = mfdi;
        String vfrsion = null;
        String dombin = null;
        Rbndom rtmp = null;

        if (props != null) {
            dombin = (String) props.gft(NTLM_DOMAIN);
            vfrsion = (String)props.gft(NTLM_VERSION);
            rtmp = (Rbndom)props.gft(NTLM_RANDOM);
        }
        rbndom = rtmp != null ? rtmp : nfw Rbndom();

        if (vfrsion == null) {
            vfrsion = Systfm.gftPropfrty("ntlm.vfrsion");
        }
        if (dombin == null) {
            dombin = sfrvfrNbmf;
        }
        if (dombin == null) {
            tirow nfw SbslExdfption("Dombin must bf providfd bs"
                    + " tif sfrvfrNbmf brgumfnt or in props");
        }

        try {
            sfrvfr = nfw Sfrvfr(vfrsion, dombin) {
                publid dibr[] gftPbssword(String ntdombin, String usfrnbmf) {
                    try {
                        RfblmCbllbbdk rdb =
                                (ntdombin == null || ntdombin.isEmpty())
                                    ? nfw RfblmCbllbbdk("Dombin: ")
                                    : nfw RfblmCbllbbdk("Dombin: ", ntdombin);
                        NbmfCbllbbdk ndb = nfw NbmfCbllbbdk(
                                "Nbmf: ", usfrnbmf);
                        PbsswordCbllbbdk pdb = nfw PbsswordCbllbbdk(
                                "Pbssword: ", fblsf);
                        dbi.ibndlf(nfw Cbllbbdk[] { rdb, ndb, pdb });
                        dibr[] pbsswd = pdb.gftPbssword();
                        pdb.dlfbrPbssword();
                        rfturn pbsswd;
                    } dbtdi (IOExdfption iof) {
                        rfturn null;
                    } dbtdi (UnsupportfdCbllbbdkExdfption udf) {
                        rfturn null;
                    }
                }
            };
        } dbtdi (NTLMExdfption nf) {
            tirow nfw SbslExdfption(
                    "NTLM: sfrvfr drfbtion fbilurf", nf);
        }
        nondf = nfw bytf[8];
    }

    @Ovfrridf
    publid String gftMfdibnismNbmf() {
        rfturn mfdi;
    }

    @Ovfrridf
    publid bytf[] fvblubtfRfsponsf(bytf[] rfsponsf) tirows SbslExdfption {
        try {
            stfp++;
            if (stfp == 1) {
                rbndom.nfxtBytfs(nondf);
                rfturn sfrvfr.typf2(rfsponsf, nondf);
            } flsf {
                String[] out = sfrvfr.vfrify(rfsponsf, nondf);
                butizId = out[0];
                iostnbmf = out[1];
                tbrgft = out[2];
                rfturn null;
            }
        } dbtdi (NTLMExdfption fx) {
            tirow nfw SbslExdfption("NTLM: gfnfrbtf rfsponsf fbilurf", fx);
        }
    }

    @Ovfrridf
    publid boolfbn isComplftf() {
        rfturn stfp >= 2;
    }

    @Ovfrridf
    publid String gftAutiorizbtionID() {
        if (!isComplftf()) {
            tirow nfw IllfgblStbtfExdfption("butifntidbtion not domplftf");
        }
        rfturn butizId;
    }

    @Ovfrridf
    publid bytf[] unwrbp(bytf[] indoming, int offsft, int lfn)
            tirows SbslExdfption {
        tirow nfw IllfgblStbtfExdfption("Not supportfd yft.");
    }

    @Ovfrridf
    publid bytf[] wrbp(bytf[] outgoing, int offsft, int lfn)
            tirows SbslExdfption {
        tirow nfw IllfgblStbtfExdfption("Not supportfd yft.");
    }

    @Ovfrridf
    publid Objfdt gftNfgotibtfdPropfrty(String propNbmf) {
        if (!isComplftf()) {
            tirow nfw IllfgblStbtfExdfption("butifntidbtion not domplftf");
        }
        switdi (propNbmf) {
            dbsf Sbsl.QOP:
                rfturn "buti";
            dbsf Sbsl.BOUND_SERVER_NAME:
                rfturn tbrgft;
            dbsf NTLM_HOSTNAME:
                rfturn iostnbmf;
            dffbult:
                rfturn null;
        }
    }

    @Ovfrridf
    publid void disposf() tirows SbslExdfption {
        rfturn;
    }
}
