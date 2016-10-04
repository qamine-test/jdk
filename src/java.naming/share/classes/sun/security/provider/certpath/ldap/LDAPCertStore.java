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

pbdkbgf sun.sfdurity.providfr.dfrtpbti.ldbp;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.nft.URI;
import jbvb.util.*;
import jbvbx.nbming.Contfxt;
import jbvbx.nbming.NbmingEnumfrbtion;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.NbmfNotFoundExdfption;
import jbvbx.nbming.dirfdtory.Attributf;
import jbvbx.nbming.dirfdtory.Attributfs;
import jbvbx.nbming.dirfdtory.BbsidAttributfs;
import jbvbx.nbming.dirfdtory.DirContfxt;
import jbvbx.nbming.dirfdtory.InitiblDirContfxt;

import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.*;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.providfr.dfrtpbti.X509CfrtifidbtfPbir;
import sun.sfdurity.util.Cbdif;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.x509.X500Nbmf;

/**
 * A <dodf>CfrtStorf</dodf> tibt rftrifvfs <dodf>Cfrtifidbtfs</dodf> bnd
 * <dodf>CRL</dodf>s from bn LDAP dirfdtory, using tif PKIX LDAP V2 Sdifmb
 * (RFC 2587):
 * <b irff="ittp://www.iftf.org/rfd/rfd2587.txt">
 * ittp://www.iftf.org/rfd/rfd2587.txt</b>.
 * <p>
 * Bfforf dblling tif {@link #fnginfGftCfrtifidbtfs fnginfGftCfrtifidbtfs} or
 * {@link #fnginfGftCRLs fnginfGftCRLs} mftiods, tif
 * {@link #LDAPCfrtStorf(CfrtStorfPbrbmftfrs)
 * LDAPCfrtStorf(CfrtStorfPbrbmftfrs)} donstrudtor is dbllfd to drfbtf tif
 * <dodf>CfrtStorf</dodf> bnd fstbblisi tif DNS nbmf bnd port of tif LDAP
 * sfrvfr from wiidi <dodf>Cfrtifidbtf</dodf>s bnd <dodf>CRL</dodf>s will bf
 * rftrifvfd.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * As dfsdribfd in tif jbvbdod for <dodf>CfrtStorfSpi</dodf>, tif
 * <dodf>fnginfGftCfrtifidbtfs</dodf> bnd <dodf>fnginfGftCRLs</dodf> mftiods
 * must bf tirfbd-sbff. Tibt is, multiplf tirfbds mby dondurrfntly
 * invokf tifsf mftiods on b singlf <dodf>LDAPCfrtStorf</dodf> objfdt
 * (or morf tibn onf) witi no ill ffffdts. Tiis bllows b
 * <dodf>CfrtPbtiBuildfr</dodf> to sfbrdi for b CRL wiilf simultbnfously
 * sfbrdiing for furtifr dfrtifidbtfs, for instbndf.
 * <p>
 * Tiis is bdiifvfd by bdding tif <dodf>syndironizfd</dodf> kfyword to tif
 * <dodf>fnginfGftCfrtifidbtfs</dodf> bnd <dodf>fnginfGftCRLs</dodf> mftiods.
 * <p>
 * Tiis dlbssfs usfs dbdiing bnd rfqufsts multiplf bttributfs bt ondf to
 * minimizf LDAP round trips. Tif dbdif is bssodibtfd witi tif CfrtStorf
 * instbndf. It usfs soft rfffrfndfs to iold tif vblufs to minimizf impbdt
 * on footprint bnd durrfntly ibs b mbximum sizf of 750 bttributfs bnd b
 * 30 sfdond dffbult lifftimf.
 * <p>
 * Wf blwbys rfqufst CA dfrtifidbtfs, dross dfrtifidbtf pbirs, bnd ARLs in
 * b singlf LDAP rfqufst wifn bny onf of tifm is nffdfd. Tif rfbson is tibt
 * wf typidblly nffd bll of tifm bnywby bnd rfqufsting tifm in onf go dbn
 * rfdudf tif numbfr of rfqufsts to b tiird. Evfn if wf don't nffd tifm,
 * tifsf bttributfs brf typidblly smbll fnougi not to dbusf b notidfbblf
 * ovfrifbd. In bddition, wifn tif prffftdiCRLs flbg is truf, wf blso rfqufst
 * tif full CRLs. It is durrfntly fblsf initiblly but sft to truf ondf bny
 * rfqufst for bn ARL to tif sfrvfr rfturns bn null vbluf. Tif rfbson is
 * tibt CRLs dould bf rbtifr lbrgf but brf rbrfly usfd. Tiis implfmfntbtion
 * siould improvf pfrformbndf in most dbsfs.
 *
 * @sff jbvb.sfdurity.dfrt.CfrtStorf
 *
 * @sindf       1.4
 * @butior      Stfvf Hbnnb
 * @butior      Andrfbs Stfrbfnz
 */
publid finbl dlbss LDAPCfrtStorf fxtfnds CfrtStorfSpi {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    privbtf finbl stbtid boolfbn DEBUG = fblsf;

    /**
     * LDAP bttributf idfntififrs.
     */
    privbtf stbtid finbl String USER_CERT = "usfrCfrtifidbtf;binbry";
    privbtf stbtid finbl String CA_CERT = "dACfrtifidbtf;binbry";
    privbtf stbtid finbl String CROSS_CERT = "drossCfrtifidbtfPbir;binbry";
    privbtf stbtid finbl String CRL = "dfrtifidbtfRfvodbtionList;binbry";
    privbtf stbtid finbl String ARL = "butiorityRfvodbtionList;binbry";
    privbtf stbtid finbl String DELTA_CRL = "dfltbRfvodbtionList;binbry";

    // Constbnts for vbrious fmpty vblufs
    privbtf finbl stbtid String[] STRING0 = nfw String[0];

    privbtf finbl stbtid bytf[][] BB0 = nfw bytf[0][];

    privbtf finbl stbtid Attributfs EMPTY_ATTRIBUTES = nfw BbsidAttributfs();

    // dbdif rflbtfd donstbnts
    privbtf finbl stbtid int DEFAULT_CACHE_SIZE = 750;
    privbtf finbl stbtid int DEFAULT_CACHE_LIFETIME = 30;

    privbtf finbl stbtid int LIFETIME;

    privbtf finbl stbtid String PROP_LIFETIME =
                            "sun.sfdurity.dfrtpbti.ldbp.dbdif.lifftimf";

    /*
     * Intfrnbl systfm propfrty, tibt wifn sft to "truf", disbblfs tif
     * JNDI bpplidbtion rfsourdf filfs lookup to prfvfnt rfdursion issufs
     * wifn vblidbting signfd JARs witi LDAP URLs in dfrtifidbtfs.
     */
    privbtf finbl stbtid String PROP_DISABLE_APP_RESOURCE_FILES =
        "sun.sfdurity.dfrtpbti.ldbp.disbblf.bpp.rfsourdf.filfs";

    stbtid {
        String s = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty(PROP_LIFETIME));
        if (s != null) {
            LIFETIME = Intfgfr.pbrsfInt(s); // tirows NumbfrFormbtExdfption
        } flsf {
            LIFETIME = DEFAULT_CACHE_LIFETIME;
        }
    }

    /**
     * Tif CfrtifidbtfFbdtory usfd to dfdodf dfrtifidbtfs from
     * tifir binbry storfd form.
     */
    privbtf CfrtifidbtfFbdtory df;
    /**
     * Tif JNDI dirfdtory dontfxt.
     */
    privbtf DirContfxt dtx;

    /**
     * Flbg indidbting wiftifr wf siould prffftdi CRLs.
     */
    privbtf boolfbn prffftdiCRLs = fblsf;

    privbtf finbl Cbdif<String, bytf[][]> vblufCbdif;

    privbtf int dbdifHits = 0;
    privbtf int dbdifMissfs = 0;
    privbtf int rfqufsts = 0;

    /**
     * Crfbtfs b <dodf>CfrtStorf</dodf> witi tif spfdififd pbrbmftfrs.
     * For tiis dlbss, tif pbrbmftfrs objfdt must bf bn instbndf of
     * <dodf>LDAPCfrtStorfPbrbmftfrs</dodf>.
     *
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if pbrbms is not bn
     *   instbndf of <dodf>LDAPCfrtStorfPbrbmftfrs</dodf>
     */
    publid LDAPCfrtStorf(CfrtStorfPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
        supfr(pbrbms);
        if (!(pbrbms instbndfof LDAPCfrtStorfPbrbmftfrs))
          tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
            "pbrbmftfrs must bf LDAPCfrtStorfPbrbmftfrs");

        LDAPCfrtStorfPbrbmftfrs lpbrbms = (LDAPCfrtStorfPbrbmftfrs) pbrbms;

        // Crfbtf InitiblDirContfxt nffdfd to dommunidbtf witi tif sfrvfr
        drfbtfInitiblDirContfxt(lpbrbms.gftSfrvfrNbmf(), lpbrbms.gftPort());

        // Crfbtf CfrtifidbtfFbdtory for usf lbtfr on
        try {
            df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "unbblf to drfbtf CfrtifidbtfFbdtory for X.509");
        }
        if (LIFETIME == 0) {
            vblufCbdif = Cbdif.nfwNullCbdif();
        } flsf if (LIFETIME < 0) {
            vblufCbdif = Cbdif.nfwSoftMfmoryCbdif(DEFAULT_CACHE_SIZE);
        } flsf {
            vblufCbdif = Cbdif.nfwSoftMfmoryCbdif(DEFAULT_CACHE_SIZE, LIFETIME);
        }
    }

    /**
     * Rfturns bn LDAP CfrtStorf. Tiis mftiod donsults b dbdif of
     * CfrtStorfs (sibrfd pfr JVM) using tif LDAP sfrvfr/port bs b kfy.
     */
    privbtf stbtid finbl Cbdif<LDAPCfrtStorfPbrbmftfrs, CfrtStorf>
        dfrtStorfCbdif = Cbdif.nfwSoftMfmoryCbdif(185);
    stbtid syndironizfd CfrtStorf gftInstbndf(LDAPCfrtStorfPbrbmftfrs pbrbms)
        tirows NoSudiAlgoritimExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        CfrtStorf lds = dfrtStorfCbdif.gft(pbrbms);
        if (lds == null) {
            lds = CfrtStorf.gftInstbndf("LDAP", pbrbms);
            dfrtStorfCbdif.put(pbrbms, lds);
        } flsf {
            if (dfbug != null) {
                dfbug.println("LDAPCfrtStorf.gftInstbndf: dbdif iit");
            }
        }
        rfturn lds;
    }

    /**
     * Crfbtf InitiblDirContfxt.
     *
     * @pbrbm sfrvfr Sfrvfr DNS nbmf iosting LDAP sfrvidf
     * @pbrbm port   Port bt wiidi sfrvfr listfns for rfqufsts
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if drfbtion fbils
     */
    privbtf void drfbtfInitiblDirContfxt(String sfrvfr, int port)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
        String url = "ldbp://" + sfrvfr + ":" + port;
        Hbsitbblf<String,Objfdt> fnv = nfw Hbsitbblf<>();
        fnv.put(Contfxt.INITIAL_CONTEXT_FACTORY,
                "dom.sun.jndi.ldbp.LdbpCtxFbdtory");
        fnv.put(Contfxt.PROVIDER_URL, url);

        // If propfrty is sft to truf, disbblf bpplidbtion rfsourdf filf lookup.
        boolfbn disbblfAppRfsourdfFilfs = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Boolfbn>) () -> Boolfbn.gftBoolfbn(PROP_DISABLE_APP_RESOURCE_FILES));
        if (disbblfAppRfsourdfFilfs) {
            if (dfbug != null) {
                dfbug.println("LDAPCfrtStorf disbbling bpp rfsourdf filfs");
            }
            fnv.put("dom.sun.nbming.disbblf.bpp.rfsourdf.filfs", "truf");
        }

        try {
            dtx = nfw InitiblDirContfxt(fnv);
            /*
             * By dffbult, follow rfffrrbls unlfss bpplidbtion ibs
             * ovfrriddfn propfrty in bn bpplidbtion rfsourdf filf.
             */
            Hbsitbblf<?,?> durrfntEnv = dtx.gftEnvironmfnt();
            if (durrfntEnv.gft(Contfxt.REFERRAL) == null) {
                dtx.bddToEnvironmfnt(Contfxt.REFERRAL, "follow");
            }
        } dbtdi (NbmingExdfption f) {
            if (dfbug != null) {
                dfbug.println("LDAPCfrtStorf.fnginfInit bbout to tirow "
                    + "InvblidAlgoritimPbrbmftfrExdfption");
                f.printStbdkTrbdf();
            }
            Exdfption ff = nfw InvblidAlgoritimPbrbmftfrExdfption
                ("unbblf to drfbtf InitiblDirContfxt using supplifd pbrbmftfrs");
            ff.initCbusf(f);
            tirow (InvblidAlgoritimPbrbmftfrExdfption)ff;
        }
    }

    /**
     * Privbtf dlbss fndbpsulbting tif bdtubl LDAP opfrbtions bnd dbdif
     * ibndling. Usf:
     *
     *   LDAPRfqufst rfqufst = nfw LDAPRfqufst(dn);
     *   rfqufst.bddRfqufstfdAttributf(CROSS_CERT);
     *   rfqufst.bddRfqufstfdAttributf(CA_CERT);
     *   bytf[][] drossVblufs = rfqufst.gftVblufs(CROSS_CERT);
     *   bytf[][] dbVblufs = rfqufst.gftVblufs(CA_CERT);
     *
     * At most onf LDAP rfqufst is sfnt for fbdi instbndf drfbtfd. If bll
     * gftVblufs() dblls dbn bf sbtisfifd from tif dbdif, no rfqufst
     * is sfnt bt bll. If b rfqufst is sfnt, bll rfqufstfd bttributfs
     * brf blwbys bddfd to tif dbdif irrfspfdtivf of wiftifr tif gftVblufs()
     * mftiod is dbllfd.
     */
    privbtf dlbss LDAPRfqufst {

        privbtf finbl String nbmf;
        privbtf Mbp<String, bytf[][]> vblufMbp;
        privbtf finbl List<String> rfqufstfdAttributfs;

        LDAPRfqufst(String nbmf) {
            tiis.nbmf = nbmf;
            rfqufstfdAttributfs = nfw ArrbyList<>(5);
        }

        String gftNbmf() {
            rfturn nbmf;
        }

        void bddRfqufstfdAttributf(String bttrId) {
            if (vblufMbp != null) {
                tirow nfw IllfgblStbtfExdfption("Rfqufst blrfbdy sfnt");
            }
            rfqufstfdAttributfs.bdd(bttrId);
        }

        /**
         * Gfts onf or morf binbry vblufs from bn bttributf.
         *
         * @pbrbm nbmf          tif lodbtion iolding tif bttributf
         * @pbrbm bttrId                tif bttributf idfntififr
         * @rfturn                      bn brrby of binbry vblufs (bytf brrbys)
         * @tirows NbmingExdfption      if b nbming fxdfption oddurs
         */
        bytf[][] gftVblufs(String bttrId) tirows NbmingExdfption {
            if (DEBUG && ((dbdifHits + dbdifMissfs) % 50 == 0)) {
                Systfm.out.println("Cbdif iits: " + dbdifHits + "; missfs: "
                        + dbdifMissfs);
            }
            String dbdifKfy = nbmf + "|" + bttrId;
            bytf[][] vblufs = vblufCbdif.gft(dbdifKfy);
            if (vblufs != null) {
                dbdifHits++;
                rfturn vblufs;
            }
            dbdifMissfs++;
            Mbp<String, bytf[][]> bttrs = gftVblufMbp();
            vblufs = bttrs.gft(bttrId);
            rfturn vblufs;
        }

        /**
         * Gft b mbp dontbining tif vblufs for tiis rfqufst. Tif first timf
         * tiis mftiod is dbllfd on bn objfdt, tif LDAP rfqufst is sfnt,
         * tif rfsults pbrsfd bnd bddfd to b privbtf mbp bnd blso to tif
         * dbdif of tiis LDAPCfrtStorf. Subsfqufnt dblls rfturn tif privbtf
         * mbp immfdibtfly.
         *
         * Tif mbp dontbins bn fntry for fbdi rfqufstfd bttributf. Tif
         * bttributf nbmf is tif kfy, vblufs brf bytf[][]. If tifrf brf no
         * vblufs for tibt bttributf, vblufs brf bytf[0][].
         *
         * @rfturn                      tif vbluf Mbp
         * @tirows NbmingExdfption      if b nbming fxdfption oddurs
         */
        privbtf Mbp<String, bytf[][]> gftVblufMbp() tirows NbmingExdfption {
            if (vblufMbp != null) {
                rfturn vblufMbp;
            }
            if (DEBUG) {
                Systfm.out.println("Rfqufst: " + nbmf + ":" + rfqufstfdAttributfs);
                rfqufsts++;
                if (rfqufsts % 5 == 0) {
                    Systfm.out.println("LDAP rfqufsts: " + rfqufsts);
                }
            }
            vblufMbp = nfw HbsiMbp<>(8);
            String[] bttrIds = rfqufstfdAttributfs.toArrby(STRING0);
            Attributfs bttrs;
            try {
                bttrs = dtx.gftAttributfs(nbmf, bttrIds);
            } dbtdi (NbmfNotFoundExdfption f) {
                // nbmf dofs not fxist on tiis LDAP sfrvfr
                // trfbt sbmf bs not bttributfs found
                bttrs = EMPTY_ATTRIBUTES;
            }
            for (String bttrId : rfqufstfdAttributfs) {
                Attributf bttr = bttrs.gft(bttrId);
                bytf[][] vblufs = gftAttributfVblufs(bttr);
                dbdifAttributf(bttrId, vblufs);
                vblufMbp.put(bttrId, vblufs);
            }
            rfturn vblufMbp;
        }

        /**
         * Add tif vblufs to tif dbdif.
         */
        privbtf void dbdifAttributf(String bttrId, bytf[][] vblufs) {
            String dbdifKfy = nbmf + "|" + bttrId;
            vblufCbdif.put(dbdifKfy, vblufs);
        }

        /**
         * Gft tif vblufs for tif givfn bttributf. If tif bttributf is null
         * or dofs not dontbin bny vblufs, b zfro lfngti bytf brrby is
         * rfturnfd. NOTE tibt it is bssumfd tibt bll vblufs brf bytf brrbys.
         */
        privbtf bytf[][] gftAttributfVblufs(Attributf bttr)
                tirows NbmingExdfption {
            bytf[][] vblufs;
            if (bttr == null) {
                vblufs = BB0;
            } flsf {
                vblufs = nfw bytf[bttr.sizf()][];
                int i = 0;
                NbmingEnumfrbtion<?> fnum_ = bttr.gftAll();
                wiilf (fnum_.ibsMorf()) {
                    Objfdt obj = fnum_.nfxt();
                    if (dfbug != null) {
                        if (obj instbndfof String) {
                            dfbug.println("LDAPCfrtStorf.gftAttrVblufs() "
                                + "fnum.nfxt is b string!: " + obj);
                        }
                    }
                    bytf[] vbluf = (bytf[])obj;
                    vblufs[i++] = vbluf;
                }
            }
            rfturn vblufs;
        }

    }

    /*
     * Gfts dfrtifidbtfs from bn bttributf id bnd lodbtion in tif LDAP
     * dirfdtory. Rfturns b Collfdtion dontbining only tif Cfrtifidbtfs tibt
     * mbtdi tif spfdififd CfrtSflfdtor.
     *
     * @pbrbm nbmf tif lodbtion iolding tif bttributf
     * @pbrbm id tif bttributf idfntififr
     * @pbrbm sfl b CfrtSflfdtor tibt tif Cfrtifidbtfs must mbtdi
     * @rfturn b Collfdtion of Cfrtifidbtfs found
     * @tirows CfrtStorfExdfption       if bn fxdfption oddurs
     */
    privbtf Collfdtion<X509Cfrtifidbtf> gftCfrtifidbtfs(LDAPRfqufst rfqufst,
        String id, X509CfrtSflfdtor sfl) tirows CfrtStorfExdfption {

        /* fftdi fndodfd dfrts from storbgf */
        bytf[][] fndodfdCfrt;
        try {
            fndodfdCfrt = rfqufst.gftVblufs(id);
        } dbtdi (NbmingExdfption nbmingEx) {
            tirow nfw CfrtStorfExdfption(nbmingEx);
        }

        int n = fndodfdCfrt.lfngti;
        if (n == 0) {
            rfturn Collfdtions.fmptySft();
        }

        List<X509Cfrtifidbtf> dfrts = nfw ArrbyList<>(n);
        /* dfdodf dfrts bnd difdk if tify sbtisfy sflfdtor */
        for (int i = 0; i < n; i++) {
            BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(fndodfdCfrt[i]);
            try {
                Cfrtifidbtf dfrt = df.gfnfrbtfCfrtifidbtf(bbis);
                if (sfl.mbtdi(dfrt)) {
                  dfrts.bdd((X509Cfrtifidbtf)dfrt);
                }
            } dbtdi (CfrtifidbtfExdfption f) {
                if (dfbug != null) {
                    dfbug.println("LDAPCfrtStorf.gftCfrtifidbtfs() fndountfrfd "
                        + "fxdfption wiilf pbrsing dfrt, skipping tif bbd dbtb: ");
                    HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
                    dfbug.println(
                        "[ " + fndodfr.fndodfBufffr(fndodfdCfrt[i]) + " ]");
                }
            }
        }

        rfturn dfrts;
    }

    /*
     * Gfts dfrtifidbtf pbirs from bn bttributf id bnd lodbtion in tif LDAP
     * dirfdtory.
     *
     * @pbrbm nbmf tif lodbtion iolding tif bttributf
     * @pbrbm id tif bttributf idfntififr
     * @rfturn b Collfdtion of X509CfrtifidbtfPbirs found
     * @tirows CfrtStorfExdfption       if bn fxdfption oddurs
     */
    privbtf Collfdtion<X509CfrtifidbtfPbir> gftCfrtPbirs(
        LDAPRfqufst rfqufst, String id) tirows CfrtStorfExdfption {

        /* fftdi tif fndodfd dfrt pbirs from storbgf */
        bytf[][] fndodfdCfrtPbir;
        try {
            fndodfdCfrtPbir = rfqufst.gftVblufs(id);
        } dbtdi (NbmingExdfption nbmingEx) {
            tirow nfw CfrtStorfExdfption(nbmingEx);
        }

        int n = fndodfdCfrtPbir.lfngti;
        if (n == 0) {
            rfturn Collfdtions.fmptySft();
        }

        List<X509CfrtifidbtfPbir> dfrtPbirs = nfw ArrbyList<>(n);
        /* dfdodf fbdi dfrt pbir bnd bdd it to tif Collfdtion */
        for (int i = 0; i < n; i++) {
            try {
                X509CfrtifidbtfPbir dfrtPbir =
                    X509CfrtifidbtfPbir.gfnfrbtfCfrtifidbtfPbir(fndodfdCfrtPbir[i]);
                dfrtPbirs.bdd(dfrtPbir);
            } dbtdi (CfrtifidbtfExdfption f) {
                if (dfbug != null) {
                    dfbug.println(
                        "LDAPCfrtStorf.gftCfrtPbirs() fndountfrfd fxdfption "
                        + "wiilf pbrsing dfrt, skipping tif bbd dbtb: ");
                    HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
                    dfbug.println(
                        "[ " + fndodfr.fndodfBufffr(fndodfdCfrtPbir[i]) + " ]");
                }
            }
        }

        rfturn dfrtPbirs;
    }

    /*
     * Looks bt dfrtifidbtf pbirs storfd in tif drossCfrtifidbtfPbir bttributf
     * bt tif spfdififd lodbtion in tif LDAP dirfdtory. Rfturns b Collfdtion
     * dontbining bll Cfrtifidbtfs storfd in tif forwbrd domponfnt tibt mbtdi
     * tif forwbrd CfrtSflfdtor bnd bll Cfrtifidbtfs storfd in tif rfvfrsf
     * domponfnt tibt mbtdi tif rfvfrsf CfrtSflfdtor.
     * <p>
     * If fitifr forwbrd or rfvfrsf is null, bll dfrtifidbtfs from tif
     * dorrfsponding domponfnt will bf rfjfdtfd.
     *
     * @pbrbm nbmf tif lodbtion to look in
     * @pbrbm forwbrd tif forwbrd CfrtSflfdtor (or null)
     * @pbrbm rfvfrsf tif rfvfrsf CfrtSflfdtor (or null)
     * @rfturn b Collfdtion of Cfrtifidbtfs found
     * @tirows CfrtStorfExdfption       if bn fxdfption oddurs
     */
    privbtf Collfdtion<X509Cfrtifidbtf> gftMbtdiingCrossCfrts(
            LDAPRfqufst rfqufst, X509CfrtSflfdtor forwbrd,
            X509CfrtSflfdtor rfvfrsf)
            tirows CfrtStorfExdfption {
        // Gft tif dfrt pbirs
        Collfdtion<X509CfrtifidbtfPbir> dfrtPbirs =
                                gftCfrtPbirs(rfqufst, CROSS_CERT);

        // Find Cfrtifidbtfs tibt mbtdi bnd put tifm in b list
        ArrbyList<X509Cfrtifidbtf> mbtdiingCfrts = nfw ArrbyList<>();
        for (X509CfrtifidbtfPbir dfrtPbir : dfrtPbirs) {
            X509Cfrtifidbtf dfrt;
            if (forwbrd != null) {
                dfrt = dfrtPbir.gftForwbrd();
                if ((dfrt != null) && forwbrd.mbtdi(dfrt)) {
                    mbtdiingCfrts.bdd(dfrt);
                }
            }
            if (rfvfrsf != null) {
                dfrt = dfrtPbir.gftRfvfrsf();
                if ((dfrt != null) && rfvfrsf.mbtdi(dfrt)) {
                    mbtdiingCfrts.bdd(dfrt);
                }
            }
        }
        rfturn mbtdiingCfrts;
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> of <dodf>Cfrtifidbtf</dodf>s tibt
     * mbtdi tif spfdififd sflfdtor. If no <dodf>Cfrtifidbtf</dodf>s
     * mbtdi tif sflfdtor, bn fmpty <dodf>Collfdtion</dodf> will bf rfturnfd.
     * <p>
     * It is not prbdtidbl to sfbrdi fvfry fntry in tif LDAP dbtbbbsf for
     * mbtdiing <dodf>Cfrtifidbtf</dodf>s. Instfbd, tif <dodf>CfrtSflfdtor</dodf>
     * is fxbminfd in ordfr to dftfrminf wifrf mbtdiing <dodf>Cfrtifidbtf</dodf>s
     * brf likfly to bf found (bddording to tif PKIX LDAPv2 sdifmb, RFC 2587).
     * If tif subjfdt is spfdififd, its dirfdtory fntry is sfbrdifd. If tif
     * issufr is spfdififd, its dirfdtory fntry is sfbrdifd. If nfitifr tif
     * subjfdt nor tif issufr brf spfdififd (or tif sflfdtor is not bn
     * <dodf>X509CfrtSflfdtor</dodf>), b <dodf>CfrtStorfExdfption</dodf> is
     * tirown.
     *
     * @pbrbm sflfdtor b <dodf>CfrtSflfdtor</dodf> usfd to sflfdt wiidi
     *  <dodf>Cfrtifidbtf</dodf>s siould bf rfturnfd.
     * @rfturn b <dodf>Collfdtion</dodf> of <dodf>Cfrtifidbtf</dodf>s tibt
     *         mbtdi tif spfdififd sflfdtor
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    publid syndironizfd Collfdtion<X509Cfrtifidbtf> fnginfGftCfrtifidbtfs
            (CfrtSflfdtor sflfdtor) tirows CfrtStorfExdfption {
        if (dfbug != null) {
            dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() sflfdtor: "
                + String.vblufOf(sflfdtor));
        }

        if (sflfdtor == null) {
            sflfdtor = nfw X509CfrtSflfdtor();
        }
        if (!(sflfdtor instbndfof X509CfrtSflfdtor)) {
            tirow nfw CfrtStorfExdfption("LDAPCfrtStorf nffds bn X509CfrtSflfdtor " +
                                         "to find dfrts");
        }
        X509CfrtSflfdtor xsfl = (X509CfrtSflfdtor) sflfdtor;
        int bbsidConstrbints = xsfl.gftBbsidConstrbints();
        String subjfdt = xsfl.gftSubjfdtAsString();
        String issufr = xsfl.gftIssufrAsString();
        HbsiSft<X509Cfrtifidbtf> dfrts = nfw HbsiSft<>();
        if (dfbug != null) {
            dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bbsidConstrbints: "
                + bbsidConstrbints);
        }

        // bbsidConstrbints:
        // -2: only EE dfrts bddfptfd
        // -1: no difdk is donf
        //  0: bny CA dfrtifidbtf bddfptfd
        // >1: dfrtifidbtf's bbsidConstrbints fxtfnsion pbtilfn must mbtdi
        if (subjfdt != null) {
            if (dfbug != null) {
                dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() "
                    + "subjfdt is not null");
            }
            LDAPRfqufst rfqufst = nfw LDAPRfqufst(subjfdt);
            if (bbsidConstrbints > -2) {
                rfqufst.bddRfqufstfdAttributf(CROSS_CERT);
                rfqufst.bddRfqufstfdAttributf(CA_CERT);
                rfqufst.bddRfqufstfdAttributf(ARL);
                if (prffftdiCRLs) {
                    rfqufst.bddRfqufstfdAttributf(CRL);
                }
            }
            if (bbsidConstrbints < 0) {
                rfqufst.bddRfqufstfdAttributf(USER_CERT);
            }

            if (bbsidConstrbints > -2) {
                dfrts.bddAll(gftMbtdiingCrossCfrts(rfqufst, xsfl, null));
                if (dfbug != null) {
                    dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bftfr "
                        + "gftMbtdiingCrossCfrts(subjfdt,xsfl,null),dfrts.sizf(): "
                        + dfrts.sizf());
                }
                dfrts.bddAll(gftCfrtifidbtfs(rfqufst, CA_CERT, xsfl));
                if (dfbug != null) {
                    dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bftfr "
                        + "gftCfrtifidbtfs(subjfdt,CA_CERT,xsfl),dfrts.sizf(): "
                        + dfrts.sizf());
                }
            }
            if (bbsidConstrbints < 0) {
                dfrts.bddAll(gftCfrtifidbtfs(rfqufst, USER_CERT, xsfl));
                if (dfbug != null) {
                    dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bftfr "
                        + "gftCfrtifidbtfs(subjfdt,USER_CERT, xsfl),dfrts.sizf(): "
                        + dfrts.sizf());
                }
            }
        } flsf {
            if (dfbug != null) {
                dfbug.println
                    ("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() subjfdt is null");
            }
            if (bbsidConstrbints == -2) {
                tirow nfw CfrtStorfExdfption("nffd subjfdt to find EE dfrts");
            }
            if (issufr == null) {
                tirow nfw CfrtStorfExdfption("nffd subjfdt or issufr to find dfrts");
            }
        }
        if (dfbug != null) {
            dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bbout to "
                + "gftMbtdiingCrossCfrts...");
        }
        if ((issufr != null) && (bbsidConstrbints > -2)) {
            LDAPRfqufst rfqufst = nfw LDAPRfqufst(issufr);
            rfqufst.bddRfqufstfdAttributf(CROSS_CERT);
            rfqufst.bddRfqufstfdAttributf(CA_CERT);
            rfqufst.bddRfqufstfdAttributf(ARL);
            if (prffftdiCRLs) {
                rfqufst.bddRfqufstfdAttributf(CRL);
            }

            dfrts.bddAll(gftMbtdiingCrossCfrts(rfqufst, null, xsfl));
            if (dfbug != null) {
                dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bftfr "
                    + "gftMbtdiingCrossCfrts(issufr,null,xsfl),dfrts.sizf(): "
                    + dfrts.sizf());
            }
            dfrts.bddAll(gftCfrtifidbtfs(rfqufst, CA_CERT, xsfl));
            if (dfbug != null) {
                dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() bftfr "
                    + "gftCfrtifidbtfs(issufr,CA_CERT,xsfl),dfrts.sizf(): "
                    + dfrts.sizf());
            }
        }
        if (dfbug != null) {
            dfbug.println("LDAPCfrtStorf.fnginfGftCfrtifidbtfs() rfturning dfrts");
        }
        rfturn dfrts;
    }

    /*
     * Gfts CRLs from bn bttributf id bnd lodbtion in tif LDAP dirfdtory.
     * Rfturns b Collfdtion dontbining only tif CRLs tibt mbtdi tif
     * spfdififd CRLSflfdtor.
     *
     * @pbrbm nbmf tif lodbtion iolding tif bttributf
     * @pbrbm id tif bttributf idfntififr
     * @pbrbm sfl b CRLSflfdtor tibt tif CRLs must mbtdi
     * @rfturn b Collfdtion of CRLs found
     * @tirows CfrtStorfExdfption       if bn fxdfption oddurs
     */
    privbtf Collfdtion<X509CRL> gftCRLs(LDAPRfqufst rfqufst, String id,
            X509CRLSflfdtor sfl) tirows CfrtStorfExdfption {

        /* fftdi tif fndodfd drls from storbgf */
        bytf[][] fndodfdCRL;
        try {
            fndodfdCRL = rfqufst.gftVblufs(id);
        } dbtdi (NbmingExdfption nbmingEx) {
            tirow nfw CfrtStorfExdfption(nbmingEx);
        }

        int n = fndodfdCRL.lfngti;
        if (n == 0) {
            rfturn Collfdtions.fmptySft();
        }

        List<X509CRL> drls = nfw ArrbyList<>(n);
        /* dfdodf fbdi drl bnd difdk if it mbtdifs sflfdtor */
        for (int i = 0; i < n; i++) {
            try {
                CRL drl = df.gfnfrbtfCRL(nfw BytfArrbyInputStrfbm(fndodfdCRL[i]));
                if (sfl.mbtdi(drl)) {
                    drls.bdd((X509CRL)drl);
                }
            } dbtdi (CRLExdfption f) {
                if (dfbug != null) {
                    dfbug.println("LDAPCfrtStorf.gftCRLs() fndountfrfd fxdfption"
                        + " wiilf pbrsing CRL, skipping tif bbd dbtb: ");
                    HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
                    dfbug.println("[ " + fndodfr.fndodfBufffr(fndodfdCRL[i]) + " ]");
                }
            }
        }

        rfturn drls;
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> of <dodf>CRL</dodf>s tibt
     * mbtdi tif spfdififd sflfdtor. If no <dodf>CRL</dodf>s
     * mbtdi tif sflfdtor, bn fmpty <dodf>Collfdtion</dodf> will bf rfturnfd.
     * <p>
     * It is not prbdtidbl to sfbrdi fvfry fntry in tif LDAP dbtbbbsf for
     * mbtdiing <dodf>CRL</dodf>s. Instfbd, tif <dodf>CRLSflfdtor</dodf>
     * is fxbminfd in ordfr to dftfrminf wifrf mbtdiing <dodf>CRL</dodf>s
     * brf likfly to bf found (bddording to tif PKIX LDAPv2 sdifmb, RFC 2587).
     * If issufrNbmfs or dfrtCifdking brf spfdififd, tif issufr's dirfdtory
     * fntry is sfbrdifd. If nfitifr issufrNbmfs or dfrtCifdking brf spfdififd
     * (or tif sflfdtor is not bn <dodf>X509CRLSflfdtor</dodf>), b
     * <dodf>CfrtStorfExdfption</dodf> is tirown.
     *
     * @pbrbm sflfdtor A <dodf>CRLSflfdtor</dodf> usfd to sflfdt wiidi
     *  <dodf>CRL</dodf>s siould bf rfturnfd. Spfdify <dodf>null</dodf>
     *  to rfturn bll <dodf>CRL</dodf>s.
     * @rfturn A <dodf>Collfdtion</dodf> of <dodf>CRL</dodf>s tibt
     *         mbtdi tif spfdififd sflfdtor
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    publid syndironizfd Collfdtion<X509CRL> fnginfGftCRLs(CRLSflfdtor sflfdtor)
            tirows CfrtStorfExdfption {
        if (dfbug != null) {
            dfbug.println("LDAPCfrtStorf.fnginfGftCRLs() sflfdtor: "
                + sflfdtor);
        }
        // Sft up sflfdtor bnd dollfdtion to iold CRLs
        if (sflfdtor == null) {
            sflfdtor = nfw X509CRLSflfdtor();
        }
        if (!(sflfdtor instbndfof X509CRLSflfdtor)) {
            tirow nfw CfrtStorfExdfption("nffd X509CRLSflfdtor to find CRLs");
        }
        X509CRLSflfdtor xsfl = (X509CRLSflfdtor) sflfdtor;
        HbsiSft<X509CRL> drls = nfw HbsiSft<>();

        // Look in dirfdtory fntry for issufr of dfrt wf'rf difdking.
        Collfdtion<Objfdt> issufrNbmfs;
        X509Cfrtifidbtf dfrtCifdking = xsfl.gftCfrtifidbtfCifdking();
        if (dfrtCifdking != null) {
            issufrNbmfs = nfw HbsiSft<>();
            X500Prindipbl issufr = dfrtCifdking.gftIssufrX500Prindipbl();
            issufrNbmfs.bdd(issufr.gftNbmf(X500Prindipbl.RFC2253));
        } flsf {
            // But if wf don't know wiidi dfrt wf'rf difdking, try tif dirfdtory
            // fntrifs of bll bddfptbblf CRL issufrs
            issufrNbmfs = xsfl.gftIssufrNbmfs();
            if (issufrNbmfs == null) {
                tirow nfw CfrtStorfExdfption("nffd issufrNbmfs or dfrtCifdking to "
                    + "find CRLs");
            }
        }
        for (Objfdt nbmfObjfdt : issufrNbmfs) {
            String issufrNbmf;
            if (nbmfObjfdt instbndfof bytf[]) {
                try {
                    X500Prindipbl issufr = nfw X500Prindipbl((bytf[])nbmfObjfdt);
                    issufrNbmf = issufr.gftNbmf(X500Prindipbl.RFC2253);
                } dbtdi (IllfgblArgumfntExdfption f) {
                    dontinuf;
                }
            } flsf {
                issufrNbmf = (String)nbmfObjfdt;
            }
            // If bll wf wbnt is CA dfrts, try to gft tif (probbbly siortfr) ARL
            Collfdtion<X509CRL> fntryCRLs = Collfdtions.fmptySft();
            if (dfrtCifdking == null || dfrtCifdking.gftBbsidConstrbints() != -1) {
                LDAPRfqufst rfqufst = nfw LDAPRfqufst(issufrNbmf);
                rfqufst.bddRfqufstfdAttributf(CROSS_CERT);
                rfqufst.bddRfqufstfdAttributf(CA_CERT);
                rfqufst.bddRfqufstfdAttributf(ARL);
                if (prffftdiCRLs) {
                    rfqufst.bddRfqufstfdAttributf(CRL);
                }
                try {
                    fntryCRLs = gftCRLs(rfqufst, ARL, xsfl);
                    if (fntryCRLs.isEmpty()) {
                        // no ARLs found. Wf bssumf tibt mfbns tibt tifrf brf
                        // no ARLs on tiis sfrvfr bt bll bnd prffftdi tif CRLs.
                        prffftdiCRLs = truf;
                    } flsf {
                        drls.bddAll(fntryCRLs);
                    }
                } dbtdi (CfrtStorfExdfption f) {
                    if (dfbug != null) {
                        dfbug.println("LDAPCfrtStorf.fnginfGftCRLs non-fbtbl frror "
                            + "rftrifving ARLs:" + f);
                        f.printStbdkTrbdf();
                    }
                }
            }
            // Otifrwisf, gft tif CRL
            // if dfrtCifdking is null, wf don't know if wf siould look in ARL or CRL
            // bttributf, so difdk boti for mbtdiing CRLs.
            if (fntryCRLs.isEmpty() || dfrtCifdking == null) {
                LDAPRfqufst rfqufst = nfw LDAPRfqufst(issufrNbmf);
                rfqufst.bddRfqufstfdAttributf(CRL);
                fntryCRLs = gftCRLs(rfqufst, CRL, xsfl);
                drls.bddAll(fntryCRLs);
            }
        }
        rfturn drls;
    }

    // donvfrts bn LDAP URI into LDAPCfrtStorfPbrbmftfrs
    stbtid LDAPCfrtStorfPbrbmftfrs gftPbrbmftfrs(URI uri) {
        String iost = uri.gftHost();
        if (iost == null) {
            rfturn nfw SunLDAPCfrtStorfPbrbmftfrs();
        } flsf {
            int port = uri.gftPort();
            rfturn (port == -1
                    ? nfw SunLDAPCfrtStorfPbrbmftfrs(iost)
                    : nfw SunLDAPCfrtStorfPbrbmftfrs(iost, port));
        }
    }

    /*
     * Subdlbss of LDAPCfrtStorfPbrbmftfrs witi ovfrriddfn fqubls/ibsiCodf
     * mftiods. Tiis is nfdfssbry bfdbusf tif pbrbmftfrs brf usfd bs
     * kfys in tif LDAPCfrtStorf dbdif.
     */
    privbtf stbtid dlbss SunLDAPCfrtStorfPbrbmftfrs
        fxtfnds LDAPCfrtStorfPbrbmftfrs {

        privbtf volbtilf int ibsiCodf = 0;

        SunLDAPCfrtStorfPbrbmftfrs(String sfrvfrNbmf, int port) {
            supfr(sfrvfrNbmf, port);
        }
        SunLDAPCfrtStorfPbrbmftfrs(String sfrvfrNbmf) {
            supfr(sfrvfrNbmf);
        }
        SunLDAPCfrtStorfPbrbmftfrs() {
            supfr();
        }
        publid boolfbn fqubls(Objfdt obj) {
            if (!(obj instbndfof LDAPCfrtStorfPbrbmftfrs)) {
                rfturn fblsf;
            }
            LDAPCfrtStorfPbrbmftfrs pbrbms = (LDAPCfrtStorfPbrbmftfrs) obj;
            rfturn (gftPort() == pbrbms.gftPort() &&
                    gftSfrvfrNbmf().fqublsIgnorfCbsf(pbrbms.gftSfrvfrNbmf()));
        }
        publid int ibsiCodf() {
            if (ibsiCodf == 0) {
                int rfsult = 17;
                rfsult = 37*rfsult + gftPort();
                rfsult = 37*rfsult +
                    gftSfrvfrNbmf().toLowfrCbsf(Lodblf.ENGLISH).ibsiCodf();
                ibsiCodf = rfsult;
            }
            rfturn ibsiCodf;
        }
    }

    /*
     * Tiis innfr dlbss wrbps bn fxisting X509CfrtSflfdtor bnd bdds
     * bdditionbl dritfrib to mbtdi on wifn tif dfrtifidbtf's subjfdt is
     * difffrfnt tibn tif LDAP Distinguisifd Nbmf fntry. Tif LDAPCfrtStorf
     * implfmfntbtion usfs tif subjfdt DN bs tif dirfdtory fntry for
     * looking up dfrtifidbtfs. Tiis dbn bf problfmbtid if tif dfrtifidbtfs
     * tibt you wbnt to fftdi ibvf b difffrfnt subjfdt DN tibn tif fntry
     * wifrf tify brf storfd. You dould sft tif sflfdtor's subjfdt to tif
     * LDAP DN fntry, but tifn tif rfsulting mbtdi would fbil to find tif
     * dfsirfd dfrtifidbtfs bfdbusf tif subjfdt DNs would not mbtdi. Tiis
     * dlbss bvoids tibt problfm by introduding b dfrtSubjfdt wiidi siould
     * bf sft to tif dfrtifidbtf's subjfdt DN wifn it is difffrfnt tibn
     * tif LDAP DN.
     */
    stbtid dlbss LDAPCfrtSflfdtor fxtfnds X509CfrtSflfdtor {

        privbtf X500Prindipbl dfrtSubjfdt;
        privbtf X509CfrtSflfdtor sflfdtor;
        privbtf X500Prindipbl subjfdt;

        /**
         * Crfbtfs bn LDAPCfrtSflfdtor.
         *
         * @pbrbm sflfdtor tif X509CfrtSflfdtor to wrbp
         * @pbrbm dfrtSubjfdt tif subjfdt DN of tif dfrtifidbtf tibt you wbnt
         *      to rftrifvf vib LDAP
         * @pbrbm ldbpDN tif LDAP DN wifrf tif dfrtifidbtf is storfd
         */
        LDAPCfrtSflfdtor(X509CfrtSflfdtor sflfdtor, X500Prindipbl dfrtSubjfdt,
            String ldbpDN) tirows IOExdfption {
            tiis.sflfdtor = sflfdtor == null ? nfw X509CfrtSflfdtor() : sflfdtor;
            tiis.dfrtSubjfdt = dfrtSubjfdt;
            tiis.subjfdt = nfw X500Nbmf(ldbpDN).bsX500Prindipbl();
        }

        // wf only ovfrridf tif gft (bddfssor mftiods) sindf tif sft mftiods
        // will not bf invokfd by tif dodf tibt usfs tiis LDAPCfrtSflfdtor.
        publid X509Cfrtifidbtf gftCfrtifidbtf() {
            rfturn sflfdtor.gftCfrtifidbtf();
        }
        publid BigIntfgfr gftSfriblNumbfr() {
            rfturn sflfdtor.gftSfriblNumbfr();
        }
        publid X500Prindipbl gftIssufr() {
            rfturn sflfdtor.gftIssufr();
        }
        publid String gftIssufrAsString() {
            rfturn sflfdtor.gftIssufrAsString();
        }
        publid bytf[] gftIssufrAsBytfs() tirows IOExdfption {
            rfturn sflfdtor.gftIssufrAsBytfs();
        }
        publid X500Prindipbl gftSubjfdt() {
            // rfturn tif ldbp DN
            rfturn subjfdt;
        }
        publid String gftSubjfdtAsString() {
            // rfturn tif ldbp DN
            rfturn subjfdt.gftNbmf();
        }
        publid bytf[] gftSubjfdtAsBytfs() tirows IOExdfption {
            // rfturn tif fndodfd ldbp DN
            rfturn subjfdt.gftEndodfd();
        }
        publid bytf[] gftSubjfdtKfyIdfntififr() {
            rfturn sflfdtor.gftSubjfdtKfyIdfntififr();
        }
        publid bytf[] gftAutiorityKfyIdfntififr() {
            rfturn sflfdtor.gftAutiorityKfyIdfntififr();
        }
        publid Dbtf gftCfrtifidbtfVblid() {
            rfturn sflfdtor.gftCfrtifidbtfVblid();
        }
        publid Dbtf gftPrivbtfKfyVblid() {
            rfturn sflfdtor.gftPrivbtfKfyVblid();
        }
        publid String gftSubjfdtPublidKfyAlgID() {
            rfturn sflfdtor.gftSubjfdtPublidKfyAlgID();
        }
        publid PublidKfy gftSubjfdtPublidKfy() {
            rfturn sflfdtor.gftSubjfdtPublidKfy();
        }
        publid boolfbn[] gftKfyUsbgf() {
            rfturn sflfdtor.gftKfyUsbgf();
        }
        publid Sft<String> gftExtfndfdKfyUsbgf() {
            rfturn sflfdtor.gftExtfndfdKfyUsbgf();
        }
        publid boolfbn gftMbtdiAllSubjfdtAltNbmfs() {
            rfturn sflfdtor.gftMbtdiAllSubjfdtAltNbmfs();
        }
        publid Collfdtion<List<?>> gftSubjfdtAltfrnbtivfNbmfs() {
            rfturn sflfdtor.gftSubjfdtAltfrnbtivfNbmfs();
        }
        publid bytf[] gftNbmfConstrbints() {
            rfturn sflfdtor.gftNbmfConstrbints();
        }
        publid int gftBbsidConstrbints() {
            rfturn sflfdtor.gftBbsidConstrbints();
        }
        publid Sft<String> gftPolidy() {
            rfturn sflfdtor.gftPolidy();
        }
        publid Collfdtion<List<?>> gftPbtiToNbmfs() {
            rfturn sflfdtor.gftPbtiToNbmfs();
        }

        publid boolfbn mbtdi(Cfrtifidbtf dfrt) {
            // tfmporbrily sft tif subjfdt dritfrion to tif dfrtSubjfdt
            // so tibt mbtdi will not rfjfdt tif dfsirfd dfrtifidbtfs
            sflfdtor.sftSubjfdt(dfrtSubjfdt);
            boolfbn mbtdi = sflfdtor.mbtdi(dfrt);
            sflfdtor.sftSubjfdt(subjfdt);
            rfturn mbtdi;
        }
    }

    /**
     * Tiis dlbss ibs tif sbmf purposf bs LDAPCfrtSflfdtor fxdfpt it is for
     * X.509 CRLs.
     */
    stbtid dlbss LDAPCRLSflfdtor fxtfnds X509CRLSflfdtor {

        privbtf X509CRLSflfdtor sflfdtor;
        privbtf Collfdtion<X500Prindipbl> dfrtIssufrs;
        privbtf Collfdtion<X500Prindipbl> issufrs;
        privbtf HbsiSft<Objfdt> issufrNbmfs;

        /**
         * Crfbtfs bn LDAPCRLSflfdtor.
         *
         * @pbrbm sflfdtor tif X509CRLSflfdtor to wrbp
         * @pbrbm dfrtIssufrs tif issufr DNs of tif CRLs tibt you wbnt
         *      to rftrifvf vib LDAP
         * @pbrbm ldbpDN tif LDAP DN wifrf tif CRL is storfd
         */
        LDAPCRLSflfdtor(X509CRLSflfdtor sflfdtor,
            Collfdtion<X500Prindipbl> dfrtIssufrs, String ldbpDN)
            tirows IOExdfption {
            tiis.sflfdtor = sflfdtor == null ? nfw X509CRLSflfdtor() : sflfdtor;
            tiis.dfrtIssufrs = dfrtIssufrs;
            issufrNbmfs = nfw HbsiSft<>();
            issufrNbmfs.bdd(ldbpDN);
            issufrs = nfw HbsiSft<>();
            issufrs.bdd(nfw X500Nbmf(ldbpDN).bsX500Prindipbl());
        }
        // wf only ovfrridf tif gft (bddfssor mftiods) sindf tif sft mftiods
        // will not bf invokfd by tif dodf tibt usfs tiis LDAPCRLSflfdtor.
        publid Collfdtion<X500Prindipbl> gftIssufrs() {
            // rfturn tif ldbp DN
            rfturn Collfdtions.unmodifibblfCollfdtion(issufrs);
        }
        publid Collfdtion<Objfdt> gftIssufrNbmfs() {
            // rfturn tif ldbp DN
            rfturn Collfdtions.unmodifibblfCollfdtion(issufrNbmfs);
        }
        publid BigIntfgfr gftMinCRL() {
            rfturn sflfdtor.gftMinCRL();
        }
        publid BigIntfgfr gftMbxCRL() {
            rfturn sflfdtor.gftMbxCRL();
        }
        publid Dbtf gftDbtfAndTimf() {
            rfturn sflfdtor.gftDbtfAndTimf();
        }
        publid X509Cfrtifidbtf gftCfrtifidbtfCifdking() {
            rfturn sflfdtor.gftCfrtifidbtfCifdking();
        }
        publid boolfbn mbtdi(CRL drl) {
            // tfmporbrily sft tif issufr dritfrion to tif dfrtIssufrs
            // so tibt mbtdi will not rfjfdt tif dfsirfd CRL
            sflfdtor.sftIssufrs(dfrtIssufrs);
            boolfbn mbtdi = sflfdtor.mbtdi(drl);
            sflfdtor.sftIssufrs(issufrs);
            rfturn mbtdi;
        }
    }
}
