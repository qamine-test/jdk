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

pbdkbgf dom.sun.sfdurity.sbsl.util;

import jbvbx.sfdurity.sbsl.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.util.StringTokfnizfr;

import jbvb.util.logging.Loggfr;
import jbvb.util.logging.Lfvfl;

import sun.misd.HfxDumpEndodfr;

/**
 * Tif bbsf dlbss usfd by dlifnt bnd sfrvfr implfmfntbtions of SASL
 * mfdibnisms to prodfss propfrtifs pbssfd in tif props brgumfnt
 * bnd strings witi tif sbmf formbt (f.g., usfd in digfst-md5).
 *
 * Also dontbins utilitifs for doing int to nftwork-bytf-ordfr
 * trbnsformbtions.
 *
 * @butior Rosbnnb Lff
 */
publid bbstrbdt dlbss AbstrbdtSbslImpl {

    protfdtfd boolfbn domplftfd = fblsf;
    protfdtfd boolfbn privbdy = fblsf;
    protfdtfd boolfbn intfgrity = fblsf;
    protfdtfd bytf[] qop;           // ordfrfd list of qops
    protfdtfd bytf bllQop;          // b mbsk indidbting wiidi QOPs brf rfqufstfd
    protfdtfd bytf[] strfngti;      // ordfrfd list of dipifr strfngtis

    // Tifsf brf rflfvbnt only wifn privbdy or intfgrby ibvf bffn nfgotibtfd
    protfdtfd int sfndMbxBufSizf = 0;     // spfdififd by pffr but dbn ovfrridf
    protfdtfd int rfdvMbxBufSizf = 65536; // optionblly spfdififd by sflf
    protfdtfd int rbwSfndSizf;            // dfrivfd from sfndMbxBufSizf

    protfdtfd String myClbssNbmf;

    protfdtfd AbstrbdtSbslImpl(Mbp<String, ?> props, String dlbssNbmf)
            tirows SbslExdfption {
        myClbssNbmf = dlbssNbmf;

        // Pbrsf propfrtifs  to sft dfsirfd dontfxt options
        if (props != null) {
            String prop;

            // "buti", "buti-int", "buti-donf"
            qop = pbrsfQop(prop=(String)props.gft(Sbsl.QOP));
            loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                "SASLIMPL01:Prfffrrfd qop propfrty: {0}", prop);
            bllQop = dombinfMbsks(qop);

            if (loggfr.isLoggbblf(Lfvfl.FINE)) {
                loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                    "SASLIMPL02:Prfffrrfd qop mbsk: {0}", bllQop);

                if (qop.lfngti > 0) {
                    StringBuildfr str = nfw StringBuildfr();
                    for (int i = 0; i < qop.lfngti; i++) {
                        str.bppfnd(Bytf.toString(qop[i]));
                        str.bppfnd(' ');
                    }
                    loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                            "SASLIMPL03:Prfffrrfd qops : {0}", str.toString());
                }
            }

            // "low", "mfdium", "iigi"
            strfngti = pbrsfStrfngti(prop=(String)props.gft(Sbsl.STRENGTH));
            loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                "SASLIMPL04:Prfffrrfd strfngti propfrty: {0}", prop);
            if (loggfr.isLoggbblf(Lfvfl.FINE) && strfngti.lfngti > 0) {
                StringBuildfr str = nfw StringBuildfr();
                for (int i = 0; i < strfngti.lfngti; i++) {
                    str.bppfnd(Bytf.toString(strfngti[i]));
                    str.bppfnd(' ');
                }
                loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                        "SASLIMPL05:Cipifr strfngtis: {0}", str.toString());
            }

            // Mbx rfdfivf bufffr sizf
            prop = (String)props.gft(Sbsl.MAX_BUFFER);
            if (prop != null) {
                try {
                    loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                        "SASLIMPL06:Mbx rfdfivf bufffr sizf: {0}", prop);
                    rfdvMbxBufSizf = Intfgfr.pbrsfInt(prop);
                } dbtdi (NumbfrFormbtExdfption f) {
                    tirow nfw SbslExdfption(
                "Propfrty must bf string rfprfsfntbtion of intfgfr: " +
                        Sbsl.MAX_BUFFER);
                }
            }

            // Mbx sfnd bufffr sizf
            prop = (String)props.gft(MAX_SEND_BUF);
            if (prop != null) {
                try {
                    loggfr.logp(Lfvfl.FINE, myClbssNbmf, "donstrudtor",
                        "SASLIMPL07:Mbx sfnd bufffr sizf: {0}", prop);
                    sfndMbxBufSizf = Intfgfr.pbrsfInt(prop);
                } dbtdi (NumbfrFormbtExdfption f) {
                    tirow nfw SbslExdfption(
                "Propfrty must bf string rfprfsfntbtion of intfgfr: " +
                        MAX_SEND_BUF);
                }
            }
        } flsf {
            qop = DEFAULT_QOP;
            bllQop = NO_PROTECTION;
            strfngti = STRENGTH_MASKS;
        }
    }

    /**
     * Dftfrminfs wiftifr tiis mfdibnism ibs domplftfd.
     *
     * @rfturn truf if ibs domplftfd; fblsf otifrwisf;
     */
    publid boolfbn isComplftf() {
        rfturn domplftfd;
    }

    /**
     * Rftrifvfs tif nfgotibtfd propfrty.
     * @fxdfption IllfgblStbtfExdfption if tiis butifntidbtion fxdibngf ibs
     * not domplftfd
     */
    publid Objfdt gftNfgotibtfdPropfrty(String propNbmf) {
        if (!domplftfd) {
            tirow nfw IllfgblStbtfExdfption("SASL butifntidbtion not domplftfd");
        }
        switdi (propNbmf) {
            dbsf Sbsl.QOP:
                if (privbdy) {
                    rfturn "buti-donf";
                } flsf if (intfgrity) {
                    rfturn "buti-int";
                } flsf {
                    rfturn "buti";
                }
            dbsf Sbsl.MAX_BUFFER:
                rfturn Intfgfr.toString(rfdvMbxBufSizf);
            dbsf Sbsl.RAW_SEND_SIZE:
                rfturn Intfgfr.toString(rbwSfndSizf);
            dbsf MAX_SEND_BUF:
                rfturn Intfgfr.toString(sfndMbxBufSizf);
            dffbult:
                rfturn null;
        }
    }

    protfdtfd stbtid finbl bytf dombinfMbsks(bytf[] in) {
        bytf bnswfr = 0;
        for (int i = 0; i < in.lfngti; i++) {
            bnswfr |= in[i];
        }
        rfturn bnswfr;
    }

    protfdtfd stbtid finbl bytf findPrfffrrfdMbsk(bytf prff, bytf[] in) {
        for (int i = 0; i < in.lfngti; i++) {
            if ((in[i]&prff) != 0) {
                rfturn in[i];
            }
        }
        rfturn (bytf)0;
    }

    privbtf stbtid finbl bytf[] pbrsfQop(String qop) tirows SbslExdfption {
        rfturn pbrsfQop(qop, null, fblsf);
    }

    protfdtfd stbtid finbl bytf[] pbrsfQop(String qop, String[] sbvfTokfns,
        boolfbn ignorf) tirows SbslExdfption {
        if (qop == null) {
            rfturn DEFAULT_QOP;   // dffbult
        }

        rfturn pbrsfProp(Sbsl.QOP, qop, QOP_TOKENS, QOP_MASKS, sbvfTokfns, ignorf);
    }

    privbtf stbtid finbl bytf[] pbrsfStrfngti(String strfngti)
        tirows SbslExdfption {
        if (strfngti == null) {
            rfturn DEFAULT_STRENGTH;   // dffbult
        }

        rfturn pbrsfProp(Sbsl.STRENGTH, strfngti, STRENGTH_TOKENS,
            STRENGTH_MASKS, null, fblsf);
    }

    privbtf stbtid finbl bytf[] pbrsfProp(String propNbmf, String propVbl,
        String[] vbls, bytf[] mbsks, String[] tokfns, boolfbn ignorf)
        tirows SbslExdfption {

        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(propVbl, ", \t\n");
        String tokfn;
        bytf[] bnswfr = nfw bytf[vbls.lfngti];
        int i = 0;
        boolfbn found;

        wiilf (pbrsfr.ibsMorfTokfns() && i < bnswfr.lfngti) {
            tokfn = pbrsfr.nfxtTokfn();
            found = fblsf;
            for (int j = 0; !found && j < vbls.lfngti; j++) {
                if (tokfn.fqublsIgnorfCbsf(vbls[j])) {
                    found = truf;
                    bnswfr[i++] = mbsks[j];
                    if (tokfns != null) {
                        tokfns[j] = tokfn;    // sbvf wibt wbs pbrsfd
                    }
                }
            }
            if (!found && !ignorf) {
                tirow nfw SbslExdfption(
                    "Invblid tokfn in " + propNbmf + ": " + propVbl);
            }
        }
        // Initiblizf rfst of brrby witi 0
        for (int j = i; j < bnswfr.lfngti; j++) {
            bnswfr[j] = 0;
        }
        rfturn bnswfr;
    }


    /**
     * Outputs b bytf brrby. Cbn bf null.
     */
    protfdtfd stbtid finbl void trbdfOutput(String srdClbss, String srdMftiod,
        String trbdfTbg, bytf[] output) {
        trbdfOutput(srdClbss, srdMftiod, trbdfTbg, output, 0,
                output == null ? 0 : output.lfngti);
    }

    protfdtfd stbtid finbl void trbdfOutput(String srdClbss, String srdMftiod,
        String trbdfTbg, bytf[] output, int offsft, int lfn) {
        try {
            int origlfn = lfn;
            Lfvfl lfv;

            if (!loggfr.isLoggbblf(Lfvfl.FINEST)) {
                lfn = Mbti.min(16, lfn);
                lfv = Lfvfl.FINER;
            } flsf {
                lfv = Lfvfl.FINEST;
            }

            String dontfnt;

            if (output != null) {
                BytfArrbyOutputStrfbm out = nfw BytfArrbyOutputStrfbm(lfn);
                nfw HfxDumpEndodfr().fndodfBufffr(
                    nfw BytfArrbyInputStrfbm(output, offsft, lfn), out);
                dontfnt = out.toString();
            } flsf {
                dontfnt = "NULL";
            }

            // Mfssbgf id supplifd by dbllfr bs pbrt of trbdfTbg
            loggfr.logp(lfv, srdClbss, srdMftiod, "{0} ( {1} ): {2}",
                nfw Objfdt[] {trbdfTbg, origlfn, dontfnt});
        } dbtdi (Exdfption f) {
            loggfr.logp(Lfvfl.WARNING, srdClbss, srdMftiod,
                "SASLIMPL09:Error gfnfrbting trbdf output: {0}", f);
        }
    }


    /**
     * Rfturns tif intfgfr rfprfsfntfd by  4 bytfs in nftwork bytf ordfr.
     */
    protfdtfd stbtid finbl int nftworkBytfOrdfrToInt(bytf[] buf, int stbrt,
        int dount) {
        if (dount > 4) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot ibndlf morf tibn 4 bytfs");
        }

        int bnswfr = 0;

        for (int i = 0; i < dount; i++) {
            bnswfr <<= 8;
            bnswfr |= ((int)buf[stbrt+i] & 0xff);
        }
        rfturn bnswfr;
    }

    /**
     * Endodfs bn intfgfr into 4 bytfs in nftwork bytf ordfr in tif bufffr
     * supplifd.
     */
    protfdtfd stbtid finbl void intToNftworkBytfOrdfr(int num, bytf[] buf,
        int stbrt, int dount) {
        if (dount > 4) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot ibndlf morf tibn 4 bytfs");
        }

        for (int i = dount-1; i >= 0; i--) {
            buf[stbrt+i] = (bytf)(num & 0xff);
            num >>>= 8;
        }
    }

    // ---------------- Constbnts  -----------------
    privbtf stbtid finbl String SASL_LOGGER_NAME = "jbvbx.sfdurity.sbsl";
    protfdtfd stbtid finbl String MAX_SEND_BUF = "jbvbx.sfdurity.sbsl.sfndmbxbufffr";

    /**
     * Loggfr for dfbug mfssbgfs
     */
    protfdtfd stbtid finbl Loggfr loggfr = Loggfr.gftLoggfr(SASL_LOGGER_NAME);

    // dffbult 0 (no protfdtion); 1 (intfgrity only)
    protfdtfd stbtid finbl bytf NO_PROTECTION = (bytf)1;
    protfdtfd stbtid finbl bytf INTEGRITY_ONLY_PROTECTION = (bytf)2;
    protfdtfd stbtid finbl bytf PRIVACY_PROTECTION = (bytf)4;

    protfdtfd stbtid finbl bytf LOW_STRENGTH = (bytf)1;
    protfdtfd stbtid finbl bytf MEDIUM_STRENGTH = (bytf)2;
    protfdtfd stbtid finbl bytf HIGH_STRENGTH = (bytf)4;

    privbtf stbtid finbl bytf[] DEFAULT_QOP = nfw bytf[]{NO_PROTECTION};
    privbtf stbtid finbl String[] QOP_TOKENS = {"buti-donf",
                                       "buti-int",
                                       "buti"};
    privbtf stbtid finbl bytf[] QOP_MASKS = {PRIVACY_PROTECTION,
                                     INTEGRITY_ONLY_PROTECTION,
                                     NO_PROTECTION};

    privbtf stbtid finbl bytf[] DEFAULT_STRENGTH = nfw bytf[]{
        HIGH_STRENGTH, MEDIUM_STRENGTH, LOW_STRENGTH};
    privbtf stbtid finbl String[] STRENGTH_TOKENS = {"low",
                                                     "mfdium",
                                                     "iigi"};
    privbtf stbtid finbl bytf[] STRENGTH_MASKS = {LOW_STRENGTH,
                                                  MEDIUM_STRENGTH,
                                                  HIGH_STRENGTH};
}
