/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl.krb5;

import jbvb.io.IOExdfption;
import jbvb.io.PrintStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.nft.InftAddrfss;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosTidkft;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosKfy;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl;
import jbvbx.sfdurity.buti.kfrbfros.SfrvidfPfrmission;
import sun.sfdurity.jgss.GSSCbllfr;

import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.PrindipblNbmf;
import sun.sfdurity.krb5.intfrnbl.Tidkft;
import sun.sfdurity.krb5.intfrnbl.EndTidkftPbrt;
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;

import sun.sfdurity.jgss.krb5.Krb5Util;
import sun.sfdurity.jgss.krb5.SfrvidfCrfds;
import sun.sfdurity.krb5.KrbExdfption;
import sun.sfdurity.krb5.intfrnbl.Krb5;

import sun.sfdurity.ssl.Dfbug;
import sun.sfdurity.ssl.HbndsibkfInStrfbm;
import sun.sfdurity.ssl.HbndsibkfOutStrfbm;
import sun.sfdurity.ssl.Krb5Hflpfr;
import sun.sfdurity.ssl.ProtodolVfrsion;

/**
 * Tiis is Kfrbfros option in tif dlifnt kfy fxdibngf mfssbgf
 * (CLIENT -> SERVER). It iolds tif Kfrbfros tidkft bnd tif fndryptfd
 * prfmbstfr sfdrft fndryptfd witi tif sfssion kfy sfblfd in tif tidkft.
 * From RFC 2712:
 *  strudt
 *  {
 *    opbquf Tidkft;
 *    opbquf butifntidbtor;            // optionbl
 *    opbquf EndryptfdPrfMbstfrSfdrft; // fndryptfd witi tif sfssion kfy
 *                                     // wiidi is sfblfd in tif tidkft
 *  } KfrbfrosWrbppfr;
 *
 *
 * Tidkft bnd butifntidbtor brf fndryptfd bs pfr RFC 1510 (in ASN.1)
 * Endryptfd prf-mbstfr sfdrft ibs tif sbmf strudturf bs it dofs for RSA
 * fxdfpt for Kfrbfros, tif fndryption kfy is tif sfssion kfy instfbd of
 * tif RSA publid kfy.
 *
 * XXX butifntidbtor durrfntly ignorfd
 *
 */
publid finbl dlbss KfrbfrosClifntKfyExdibngfImpl
    fxtfnds sun.sfdurity.ssl.KfrbfrosClifntKfyExdibngf {

    privbtf KfrbfrosPrfMbstfrSfdrft prfMbstfr;
    privbtf bytf[] fndodfdTidkft;
    privbtf KfrbfrosPrindipbl pffrPrindipbl;
    privbtf KfrbfrosPrindipbl lodblPrindipbl;

    publid KfrbfrosClifntKfyExdibngfImpl() {
    }

    /**
     * Crfbtfs bn instbndf of KfrbfrosClifntKfyExdibngf donsisting of tif
     * Kfrbfros sfrvidf tidkft, butifntidbtor bnd fndryptfd prfmbstfr sfdrft.
     * Cbllfd by dlifnt ibndsibkfr.
     *
     * @pbrbm sfrvfrNbmf nbmf of sfrvfr witi wiidi to do ibndsibkf;
     *             tiis is usfd to gft tif Kfrbfros sfrvidf tidkft
     * @pbrbm protodolVfrsion Mbximum vfrsion supportfd by dlifnt (i.f,
     *          vfrsion it rfqufstfd in dlifnt ifllo)
     * @pbrbm rbnd rbndom numbfr gfnfrbtor to usf for gfnfrbting prf-mbstfr
     *          sfdrft
     */
    @Ovfrridf
    publid void init(String sfrvfrNbmf,
        AddfssControlContfxt bdd, ProtodolVfrsion protodolVfrsion,
        SfdurfRbndom rbnd) tirows IOExdfption {

         // Gft sfrvidf tidkft
         KfrbfrosTidkft tidkft = gftSfrvidfTidkft(sfrvfrNbmf, bdd);
         fndodfdTidkft = tidkft.gftEndodfd();

         // Rfdord tif Kfrbfros prindipbls
         pffrPrindipbl = tidkft.gftSfrvfr();
         lodblPrindipbl = tidkft.gftClifnt();

         // Optionbl butifntidbtor, fndryptfd using sfssion kfy,
         // durrfntly ignorfd

         // Gfnfrbtf prfmbstfr sfdrft bnd fndrypt it using sfssion kfy
         EndryptionKfy sfssionKfy = nfw EndryptionKfy(
                                        tidkft.gftSfssionKfyTypf(),
                                        tidkft.gftSfssionKfy().gftEndodfd());

         prfMbstfr = nfw KfrbfrosPrfMbstfrSfdrft(protodolVfrsion,
             rbnd, sfssionKfy);
    }

    /**
     * Crfbtfs bn instbndf of KfrbfrosClifntKfyExdibngf from its ASN.1 fndoding.
     * Usfd by SfrvfrHbndsibkfr to vfrify bnd obtbin prfmbstfr sfdrft.
     *
     * @pbrbm protodolVfrsion durrfnt protodol vfrsion
     * @pbrbm dlifntVfrsion vfrsion rfqufstfd by dlifnt in its ClifntHfllo;
     *          usfd by prfmbstfr sfdrft vfrsion difdk
     * @pbrbm rbnd rbndom numbfr gfnfrbtor usfd for gfnfrbting rbndom
     *          prfmbstfr sfdrft if tidkft bnd/or prfmbstfr vfrifidbtion fbils
     * @pbrbm input inputstrfbm from wiidi to gft ASN.1-fndodfd KfrbfrosWrbppfr
     * @pbrbm bdd tif AddfssControlContfxt of tif ibndsibkfr
     * @pbrbm sfrvidfCrfds sfrvfr's drfds
     */
    @Ovfrridf
    publid void init(ProtodolVfrsion protodolVfrsion,
        ProtodolVfrsion dlifntVfrsion,
        SfdurfRbndom rbnd, HbndsibkfInStrfbm input, AddfssControlContfxt bdd, Objfdt sfrvidfCrfds)
        tirows IOExdfption {

        // Rfbd tidkft
        fndodfdTidkft = input.gftBytfs16();

        if (dfbug != null && Dfbug.isOn("vfrbosf")) {
            Dfbug.println(Systfm.out,
                "fndodfd Kfrbfros sfrvidf tidkft", fndodfdTidkft);
        }

        EndryptionKfy sfssionKfy = null;

        try {
            Tidkft t = nfw Tidkft(fndodfdTidkft);

            EndryptfdDbtb fndPbrt = t.fndPbrt;
            PrindipblNbmf tidkftSnbmf = t.snbmf;

            finbl SfrvidfCrfds drfds = (SfrvidfCrfds)sfrvidfCrfds;
            finbl KfrbfrosPrindipbl prind =
                    nfw KfrbfrosPrindipbl(tidkftSnbmf.toString());

            // For bound sfrvidf, pfrmission blrfbdy difdkfd bt sftup
            if (drfds.gftNbmf() == null) {
                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                try {
                    if (sm != null) {
                        // Eliminbtf dfpfndfndy on SfrvidfPfrmission
                        sm.difdkPfrmission(Krb5Hflpfr.gftSfrvidfPfrmission(
                                tidkftSnbmf.toString(), "bddfpt"), bdd);
                    }
                } dbtdi (SfdurityExdfption sf) {
                    sfrvidfCrfds = null;
                    // Do not dfstroy kfys. Will bfffdt Subjfdt
                    if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                        Systfm.out.println("Pfrmission to bddfss Kfrbfros"
                                + " sfdrft kfy dfnifd");
                    }
                    tirow nfw IOExdfption("Kfrbfros sfrvidf not bllowfdy");
                }
            }
            KfrbfrosKfy[] sfrvfrKfys = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<KfrbfrosKfy[]>() {
                        @Ovfrridf
                        publid KfrbfrosKfy[] run() {
                            rfturn drfds.gftKKfys(prind);
                        }
                    });
            if (sfrvfrKfys.lfngti == 0) {
                tirow nfw IOExdfption("Found no kfy for " + prind +
                        (drfds.gftNbmf() == null ? "" :
                        (", tiis kfytbb is for " + drfds.gftNbmf() + " only")));
            }

            /*
             * pfrmission to bddfss bnd usf tif sfdrft kfy of tif Kfrbfrizfd
             * "iost" sfrvidf is donf in SfrvfrHbndsibkfr.gftKfrbfrosKfys()
             * to fnsurf sfrvfr ibs tif pfrmission to usf tif sfdrft kfy
             * bfforf promising tif dlifnt
             */

            // Sff if wf ibvf tif rigit kfy to dfdrypt tif tidkft to gft
            // tif sfssion kfy.
            int fndPbrtKfyTypf = fndPbrt.gftETypf();
            Intfgfr fndPbrtKfyVfrsion = fndPbrt.gftKfyVfrsionNumbfr();
            KfrbfrosKfy dkfy = null;
            try {
                dkfy = findKfy(fndPbrtKfyTypf, fndPbrtKfyVfrsion, sfrvfrKfys);
            } dbtdi (KrbExdfption kf) { // b kvno mismbtdi
                tirow nfw IOExdfption(
                        "Cbnnot find kfy mbtdiing vfrsion numbfr", kf);
            }
            if (dkfy == null) {
                // %%% Siould print string rfpr of ftypf
                tirow nfw IOExdfption("Cbnnot find kfy of bppropribtf typf" +
                        " to dfdrypt tidkft - nffd ftypf " + fndPbrtKfyTypf);
            }

            EndryptionKfy sfdrftKfy = nfw EndryptionKfy(
                fndPbrtKfyTypf,
                dkfy.gftEndodfd());

            // Dfdrypt fndPbrt using sfrvfr's sfdrft kfy
            bytf[] bytfs = fndPbrt.dfdrypt(sfdrftKfy, KfyUsbgf.KU_TICKET);

            // Rfsft dbtb strfbm bftfr dfdryption, rfmovf rfdundbnt bytfs
            bytf[] tfmp = fndPbrt.rfsft(bytfs);
            EndTidkftPbrt fndTidkftPbrt = nfw EndTidkftPbrt(tfmp);

            // Rfdord tif Kfrbfros Prindipbls
            pffrPrindipbl =
                nfw KfrbfrosPrindipbl(fndTidkftPbrt.dnbmf.gftNbmf());
            lodblPrindipbl = nfw KfrbfrosPrindipbl(tidkftSnbmf.gftNbmf());

            sfssionKfy = fndTidkftPbrt.kfy;

            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("sfrvfr prindipbl: " + tidkftSnbmf);
                Systfm.out.println("dnbmf: " + fndTidkftPbrt.dnbmf.toString());
            }
        } dbtdi (IOExdfption f) {
            tirow f;
        } dbtdi (Exdfption f) {
            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("KfrbfrosWrbppfr frror gftting sfssion kfy,"
                        + " gfnfrbting rbndom sfdrft (" + f.gftMfssbgf() + ")");
            }
            sfssionKfy = null;
        }

        input.gftBytfs16();   // XXX Rfbd bnd ignorf butifntidbtor

        if (sfssionKfy != null) {
            prfMbstfr = nfw KfrbfrosPrfMbstfrSfdrft(protodolVfrsion,
                dlifntVfrsion, rbnd, input, sfssionKfy);
        } flsf {
            // Gfnfrbtf bogus prfmbstfr sfdrft
            prfMbstfr = nfw KfrbfrosPrfMbstfrSfdrft(dlifntVfrsion, rbnd);
        }
    }

    @Ovfrridf
    publid int mfssbgfLfngti() {
        rfturn (6 + fndodfdTidkft.lfngti + prfMbstfr.gftEndryptfd().lfngti);
    }

    @Ovfrridf
    publid void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        s.putBytfs16(fndodfdTidkft);
        s.putBytfs16(null); // XXX no butifntidbtor
        s.putBytfs16(prfMbstfr.gftEndryptfd());
    }

    @Ovfrridf
    publid void print(PrintStrfbm s) tirows IOExdfption {
        s.println("*** ClifntKfyExdibngf, Kfrbfros");

        if (dfbug != null && Dfbug.isOn("vfrbosf")) {
            Dfbug.println(s, "Kfrbfros sfrvidf tidkft", fndodfdTidkft);
            Dfbug.println(s, "Rbndom Sfdrft", prfMbstfr.gftUnfndryptfd());
            Dfbug.println(s, "Endryptfd rbndom Sfdrft",
                prfMbstfr.gftEndryptfd());
        }
    }

    // Similbr to sun.sfdurity.jgss.krb5.Krb5InitCrfdfnftibl/Krb5Contfxt
    privbtf stbtid KfrbfrosTidkft gftSfrvidfTidkft(String sfrvfrNbmf,
        finbl AddfssControlContfxt bdd) tirows IOExdfption {

        if ("lodbliost".fqubls(sfrvfrNbmf) ||
                "lodbliost.lodbldombin".fqubls(sfrvfrNbmf)) {

            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("Gft tif lodbl iostnbmf");
            }
            String lodblHost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
                publid String run() {
                    try {
                        rfturn InftAddrfss.gftLodblHost().gftHostNbmf();
                    } dbtdi (jbvb.nft.UnknownHostExdfption f) {
                        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                            Systfm.out.println("Wbrning,"
                                + " dbnnot gft tif lodbl iostnbmf: "
                                + f.gftMfssbgf());
                        }
                        rfturn null;
                    }
                }
            });
            if (lodblHost != null) {
                sfrvfrNbmf = lodblHost;
            }
        }

        // Rfsolvf sfrvfrNbmf (possibly in IP bddr form) to Kfrbfros prindipbl
        // nbmf for sfrvidf witi iostnbmf
        String sfrvidfNbmf = "iost/" + sfrvfrNbmf;
        PrindipblNbmf prindipbl;
        try {
            prindipbl = nfw PrindipblNbmf(sfrvidfNbmf,
                                PrindipblNbmf.KRB_NT_SRV_HST);
        } dbtdi (SfdurityExdfption sf) {
            tirow sf;
        } dbtdi (Exdfption f) {
            IOExdfption iof = nfw IOExdfption("Invblid sfrvidf prindipbl" +
                                " nbmf: " + sfrvidfNbmf);
            iof.initCbusf(f);
            tirow iof;
        }
        String rfblm = prindipbl.gftRfblmAsString();

        finbl String sfrvfrPrindipbl = prindipbl.toString();
        finbl String tgsPrindipbl = "krbtgt/" + rfblm + "@" + rfblm;
        finbl String dlifntPrindipbl = null;  // usf dffbult


        // difdk pfrmission to obtbin b sfrvidf tidkft to initibtf b
        // dontfxt witi tif "iost" sfrvidf
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
           sm.difdkPfrmission(nfw SfrvidfPfrmission(sfrvfrPrindipbl,
                                "initibtf"), bdd);
        }

        try {
            KfrbfrosTidkft tidkft = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<KfrbfrosTidkft>() {
                publid KfrbfrosTidkft run() tirows Exdfption {
                    rfturn Krb5Util.gftTidkftFromSubjfdtAndTgs(
                        GSSCbllfr.CALLER_SSL_CLIENT,
                        dlifntPrindipbl, sfrvfrPrindipbl,
                        tgsPrindipbl, bdd);
                        }});

            if (tidkft == null) {
                tirow nfw IOExdfption("Fbilfd to find bny kfrbfros sfrvidf" +
                        " tidkft for " + sfrvfrPrindipbl);
            }
            rfturn tidkft;
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            IOExdfption iof = nfw IOExdfption(
                "Attfmpt to obtbin kfrbfros sfrvidf tidkft for " +
                        sfrvfrPrindipbl + " fbilfd!");
            iof.initCbusf(f);
            tirow iof;
        }
    }

    @Ovfrridf
    publid bytf[] gftUnfndryptfdPrfMbstfrSfdrft() {
        rfturn prfMbstfr.gftUnfndryptfd();
    }

    @Ovfrridf
    publid KfrbfrosPrindipbl gftPffrPrindipbl() {
        rfturn pffrPrindipbl;
    }

    @Ovfrridf
    publid KfrbfrosPrindipbl gftLodblPrindipbl() {
        rfturn lodblPrindipbl;
    }

    /**
     * Dftfrminfs if b kvno mbtdifs bnotifr kvno. Usfd in tif mftiod
     * findKfy(ftypf, vfrsion, kfys). Alwbys rfturns truf if fitifr input
     * is null or zfro, in dbsf bny sidf dofs not ibvf kvno info bvbilbblf.
     *
     * Notf: zfro is indludfd bfdbusf N/A is not b lfgbl vbluf for kvno
     * in jbvbx.sfdurity.buti.kfrbfros.KfrbfrosKfy. Tifrfforf, tif info
     * tibt tif kvno is N/A migit bf lost wifn donvfrting bftwffn
     * EndryptionKfy bnd KfrbfrosKfy.
     */
    privbtf stbtid boolfbn vfrsionMbtdifs(Intfgfr v1, int v2) {
        if (v1 == null || v1 == 0 || v2 == 0) {
            rfturn truf;
        }
        rfturn v1.fqubls(v2);
    }

    privbtf stbtid KfrbfrosKfy findKfy(int ftypf, Intfgfr vfrsion,
            KfrbfrosKfy[] kfys) tirows KrbExdfption {
        int ktypf;
        boolfbn ftypfFound = fblsf;

        // Wifn no mbtdifd kvno is found, rfturns tkf kfy of tif sbmf
        // ftypf witi tif iigifst kvno
        int kvno_found = 0;
        KfrbfrosKfy kfy_found = null;

        for (int i = 0; i < kfys.lfngti; i++) {
            ktypf = kfys[i].gftKfyTypf();
            if (ftypf == ktypf) {
                int kv = kfys[i].gftVfrsionNumbfr();
                ftypfFound = truf;
                if (vfrsionMbtdifs(vfrsion, kv)) {
                    rfturn kfys[i];
                } flsf if (kv > kvno_found) {
                    kfy_found = kfys[i];
                    kvno_found = kv;
                }
            }
        }
        // Kfy not found.
        // %%% kludgf to bllow DES kfys to bf usfd for diff ftypfs
        if ((ftypf == EndryptfdDbtb.ETYPE_DES_CBC_CRC ||
            ftypf == EndryptfdDbtb.ETYPE_DES_CBC_MD5)) {
            for (int i = 0; i < kfys.lfngti; i++) {
                ktypf = kfys[i].gftKfyTypf();
                if (ktypf == EndryptfdDbtb.ETYPE_DES_CBC_CRC ||
                        ktypf == EndryptfdDbtb.ETYPE_DES_CBC_MD5) {
                    int kv = kfys[i].gftVfrsionNumbfr();
                    ftypfFound = truf;
                    if (vfrsionMbtdifs(vfrsion, kv)) {
                        rfturn nfw KfrbfrosKfy(kfys[i].gftPrindipbl(),
                            kfys[i].gftEndodfd(),
                            ftypf,
                            kv);
                    } flsf if (kv > kvno_found) {
                        kfy_found = nfw KfrbfrosKfy(kfys[i].gftPrindipbl(),
                                kfys[i].gftEndodfd(),
                                ftypf,
                                kv);
                        kvno_found = kv;
                    }
                }
            }
        }
        if (ftypfFound) {
            rfturn kfy_found;
        }
        rfturn null;
    }
}
