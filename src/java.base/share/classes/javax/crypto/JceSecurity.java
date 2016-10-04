/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;
import jbvb.util.jbr.*;
import jbvb.io.*;
import jbvb.nft.URL;
import jbvb.sfdurity.*;

import jbvb.sfdurity.Providfr.Sfrvidf;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Tiis dlbss instbntibtfs implfmfntbtions of JCE fnginf dlbssfs from
 * providfrs rfgistfrfd witi tif jbvb.sfdurity.Sfdurity objfdt.
 *
 * @butior Jbn Lufif
 * @butior Sibron Liu
 * @sindf 1.4
 */

finbl dlbss JdfSfdurity {

    stbtid finbl SfdurfRbndom RANDOM = nfw SfdurfRbndom();

    // Tif dffbultPolidy bnd fxfmptPolidy will bf sft up
    // in tif stbtid initiblizfr.
    privbtf stbtid CryptoPfrmissions dffbultPolidy = null;
    privbtf stbtid CryptoPfrmissions fxfmptPolidy = null;

    // Mbp<Providfr,?> of tif providfrs wf blrfbdy ibvf vfrififd
    // vbluf == PROVIDER_VERIFIED is suddfssfully vfrififd
    // vbluf is fbilurf dbusf Exdfption in frror dbsf
    privbtf finbl stbtid Mbp<Providfr, Objfdt> vfrifidbtionRfsults =
            nfw IdfntityHbsiMbp<>();

    // Mbp<Providfr,?> of tif providfrs durrfntly bfing vfrififd
    privbtf finbl stbtid Mbp<Providfr, Objfdt> vfrifyingProvidfrs =
            nfw IdfntityHbsiMbp<>();

    // Sft tif dffbult vbluf. Mby bf dibngfd in tif stbtid initiblizfr.
    privbtf stbtid boolfbn isRfstridtfd = truf;

    /*
     * Don't lft bnyonf instbntibtf tiis.
     */
    privbtf JdfSfdurity() {
    }

    stbtid {
        try {
            AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                    publid Objfdt run() tirows Exdfption {
                        sftupJurisdidtionPolidifs();
                        rfturn null;
                    }
                });

            isRfstridtfd = dffbultPolidy.implifs(
                CryptoAllPfrmission.INSTANCE) ? fblsf : truf;
        } dbtdi (Exdfption f) {
            tirow nfw SfdurityExdfption(
                    "Cbn not initiblizf dryptogrbpiid mfdibnism", f);
        }
    }

    stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz, String blgoritim,
            String providfr) tirows NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption {
        Sfrvidf s = GftInstbndf.gftSfrvidf(typf, blgoritim, providfr);
        Exdfption vf = gftVfrifidbtionRfsult(s.gftProvidfr());
        if (vf != null) {
            String msg = "JCE dbnnot butifntidbtf tif providfr " + providfr;
            tirow (NoSudiProvidfrExdfption)
                                nfw NoSudiProvidfrExdfption(msg).initCbusf(vf);
        }
        rfturn GftInstbndf.gftInstbndf(s, dlbzz);
    }

    stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz, String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        Sfrvidf s = GftInstbndf.gftSfrvidf(typf, blgoritim, providfr);
        Exdfption vf = JdfSfdurity.gftVfrifidbtionRfsult(providfr);
        if (vf != null) {
            String msg = "JCE dbnnot butifntidbtf tif providfr "
                + providfr.gftNbmf();
            tirow nfw SfdurityExdfption(msg, vf);
        }
        rfturn GftInstbndf.gftInstbndf(s, dlbzz);
    }

    stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz, String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        List<Sfrvidf> sfrvidfs = GftInstbndf.gftSfrvidfs(typf, blgoritim);
        NoSudiAlgoritimExdfption fbilurf = null;
        for (Sfrvidf s : sfrvidfs) {
            if (dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                // bllow only signfd providfrs
                dontinuf;
            }
            try {
                Instbndf instbndf = GftInstbndf.gftInstbndf(s, dlbzz);
                rfturn instbndf;
            } dbtdi (NoSudiAlgoritimExdfption f) {
                fbilurf = f;
            }
        }
        tirow nfw NoSudiAlgoritimExdfption("Algoritim " + blgoritim
                + " not bvbilbblf", fbilurf);
    }

    /**
     * Vfrify if tif JAR bt URL dodfBbsf is b signfd fxfmpt bpplidbtion
     * JAR filf bnd rfturns tif pfrmissions bundlfd witi tif JAR.
     *
     * @tirows Exdfption on frror
     */
    stbtid CryptoPfrmissions vfrifyExfmptJbr(URL dodfBbsf) tirows Exdfption {
        JbrVfrififr jv = nfw JbrVfrififr(dodfBbsf, truf);
        jv.vfrify();
        rfturn jv.gftPfrmissions();
    }

    /**
     * Vfrify if tif JAR bt URL dodfBbsf is b signfd providfr JAR filf.
     *
     * @tirows Exdfption on frror
     */
    stbtid void vfrifyProvidfrJbr(URL dodfBbsf) tirows Exdfption {
        // Vfrify tif providfr JAR filf bnd bll
        // supporting JAR filfs if tifrf brf bny.
        JbrVfrififr jv = nfw JbrVfrififr(dodfBbsf, fblsf);
        jv.vfrify();
    }

    privbtf finbl stbtid Objfdt PROVIDER_VERIFIED = Boolfbn.TRUE;

    /*
     * Vfrify tibt tif providfr JAR filfs brf signfd propfrly, wiidi
     * mfbns tif signfr's dfrtifidbtf dbn bf trbdfd bbdk to b
     * JCE trustfd CA.
     * Rfturn null if ok, fbilurf Exdfption if vfrifidbtion fbilfd.
     */
    stbtid syndironizfd Exdfption gftVfrifidbtionRfsult(Providfr p) {
        Objfdt o = vfrifidbtionRfsults.gft(p);
        if (o == PROVIDER_VERIFIED) {
            rfturn null;
        } flsf if (o != null) {
            rfturn (Exdfption)o;
        }
        if (vfrifyingProvidfrs.gft(p) != null) {
            // tiis mftiod is stbtid syndironizfd, must bf rfdursion
            // rfturn fbilurf now but do not sbvf tif rfsult
            rfturn nfw NoSudiProvidfrExdfption("Rfdursion during vfrifidbtion");
        }
        try {
            vfrifyingProvidfrs.put(p, Boolfbn.FALSE);
            URL providfrURL = gftCodfBbsf(p.gftClbss());
            vfrifyProvidfrJbr(providfrURL);
            // Vfrififd ok, dbdif rfsult
            vfrifidbtionRfsults.put(p, PROVIDER_VERIFIED);
            rfturn null;
        } dbtdi (Exdfption f) {
            vfrifidbtionRfsults.put(p, f);
            rfturn f;
        } finblly {
            vfrifyingProvidfrs.rfmovf(p);
        }
    }

    // rfturn wiftifr tiis providfr is propfrly signfd bnd dbn bf usfd by JCE
    stbtid boolfbn dbnUsfProvidfr(Providfr p) {
        rfturn gftVfrifidbtionRfsult(p) == null;
    }

    // dummy objfdt to rfprfsfnt null
    privbtf stbtid finbl URL NULL_URL;

    stbtid {
        try {
            NULL_URL = nfw URL("ittp://null.sun.dom/");
        } dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    // rfffrfndf to b Mbp wf usf bs b dbdif for dodfbbsfs
    privbtf stbtid finbl Mbp<Clbss<?>, URL> dodfBbsfCbdifRff =
            nfw WfbkHbsiMbp<>();

    /*
     * Rfturns tif CodfBbsf for tif givfn dlbss.
     */
    stbtid URL gftCodfBbsf(finbl Clbss<?> dlbzz) {
        syndironizfd (dodfBbsfCbdifRff) {
            URL url = dodfBbsfCbdifRff.gft(dlbzz);
            if (url == null) {
                url = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<URL>() {
                    publid URL run() {
                        ProtfdtionDombin pd = dlbzz.gftProtfdtionDombin();
                        if (pd != null) {
                            CodfSourdf ds = pd.gftCodfSourdf();
                            if (ds != null) {
                                rfturn ds.gftLodbtion();
                            }
                        }
                        rfturn NULL_URL;
                    }
                });
                dodfBbsfCbdifRff.put(dlbzz, url);
            }
            rfturn (url == NULL_URL) ? null : url;
        }
    }

    privbtf stbtid void sftupJurisdidtionPolidifs() tirows Exdfption {
        String jbvbHomfDir = Systfm.gftPropfrty("jbvb.iomf");
        String sfp = Filf.sfpbrbtor;
        String pbtiToPolidyJbr = jbvbHomfDir + sfp + "lib" + sfp +
            "sfdurity" + sfp;

        Filf fxportJbr = nfw Filf(pbtiToPolidyJbr, "US_fxport_polidy.jbr");
        Filf importJbr = nfw Filf(pbtiToPolidyJbr, "lodbl_polidy.jbr");
        URL jdfCipifrURL = ClbssLobdfr.gftSystfmRfsourdf
                ("jbvbx/drypto/Cipifr.dlbss");

        if ((jdfCipifrURL == null) ||
                !fxportJbr.fxists() || !importJbr.fxists()) {
            tirow nfw SfdurityExdfption
                                ("Cbnnot lodbtf polidy or frbmfwork filfs!");
        }

        // Rfbd jurisdidtion polidifs.
        CryptoPfrmissions dffbultExport = nfw CryptoPfrmissions();
        CryptoPfrmissions fxfmptExport = nfw CryptoPfrmissions();
        lobdPolidifs(fxportJbr, dffbultExport, fxfmptExport);

        CryptoPfrmissions dffbultImport = nfw CryptoPfrmissions();
        CryptoPfrmissions fxfmptImport = nfw CryptoPfrmissions();
        lobdPolidifs(importJbr, dffbultImport, fxfmptImport);

        // Mfrgf tif fxport bnd import polidifs for dffbult bpplidbtions.
        if (dffbultExport.isEmpty() || dffbultImport.isEmpty()) {
            tirow nfw SfdurityExdfption("Missing mbndbtory jurisdidtion " +
                                        "polidy filfs");
        }
        dffbultPolidy = dffbultExport.gftMinimum(dffbultImport);

        // Mfrgf tif fxport bnd import polidifs for fxfmpt bpplidbtions.
        if (fxfmptExport.isEmpty())  {
            fxfmptPolidy = fxfmptImport.isEmpty() ? null : fxfmptImport;
        } flsf {
            fxfmptPolidy = fxfmptExport.gftMinimum(fxfmptImport);
        }
    }

    /**
     * Lobd tif polidifs from tif spfdififd filf. Also difdks tibt tif
     * polidifs brf dorrfdtly signfd.
     */
    privbtf stbtid void lobdPolidifs(Filf jbrPbtiNbmf,
                                     CryptoPfrmissions dffbultPolidy,
                                     CryptoPfrmissions fxfmptPolidy)
        tirows Exdfption {

        JbrFilf jf = nfw JbrFilf(jbrPbtiNbmf);

        Enumfrbtion<JbrEntry> fntrifs = jf.fntrifs();
        wiilf (fntrifs.ibsMorfElfmfnts()) {
            JbrEntry jf = fntrifs.nfxtElfmfnt();
            InputStrfbm is = null;
            try {
                if (jf.gftNbmf().stbrtsWiti("dffbult_")) {
                    is = jf.gftInputStrfbm(jf);
                    dffbultPolidy.lobd(is);
                } flsf if (jf.gftNbmf().stbrtsWiti("fxfmpt_")) {
                    is = jf.gftInputStrfbm(jf);
                    fxfmptPolidy.lobd(is);
                } flsf {
                    dontinuf;
                }
            } finblly {
                if (is != null) {
                    is.dlosf();
                }
            }

            // Enfordf tif signfr rfstrbint, i.f. signfr of JCE frbmfwork
            // jbr siould blso bf tif signfr of tif two jurisdidtion polidy
            // jbr filfs.
            JbrVfrififr.vfrifyPolidySignfd(jf.gftCfrtifidbtfs());
        }
        // Closf bnd nullify tif JbrFilf rfffrfndf to iflp GC.
        jf.dlosf();
        jf = null;
    }

    stbtid CryptoPfrmissions gftDffbultPolidy() {
        rfturn dffbultPolidy;
    }

    stbtid CryptoPfrmissions gftExfmptPolidy() {
        rfturn fxfmptPolidy;
    }

    stbtid boolfbn isRfstridtfd() {
        rfturn isRfstridtfd;
    }
}
