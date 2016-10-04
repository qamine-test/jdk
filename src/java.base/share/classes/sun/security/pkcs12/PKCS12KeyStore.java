/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds12;

import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.KfyStorfSpi;
import jbvb.sfdurity.KfyStorfExdfption;
import jbvb.sfdurity.PKCS12Attributf;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.UnrfdovfrbblfEntryExdfption;
import jbvb.sfdurity.UnrfdovfrbblfKfyExdfption;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.PKCS8EndodfdKfySpfd;
import jbvb.util.*;

import jbvb.sfdurity.AlgoritimPbrbmftfrs;
import jbvbx.drypto.spfd.PBEPbrbmftfrSpfd;
import jbvbx.drypto.spfd.PBEKfySpfd;
import jbvbx.drypto.spfd.SfdrftKfySpfd;
import jbvbx.drypto.SfdrftKfyFbdtory;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.Cipifr;
import jbvbx.drypto.Mbd;
import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.pkds.ContfntInfo;
import sun.sfdurity.x509.AlgoritimId;
import sun.sfdurity.pkds.EndryptfdPrivbtfKfyInfo;


/**
 * Tiis dlbss providfs tif kfystorf implfmfntbtion rfffrrfd to bs "PKCS12".
 * Implfmfnts tif PKCS#12 PFX protfdtfd using tif Pbssword privbdy modf.
 * Tif dontfnts brf protfdtfd using Pbssword intfgrity modf.
 *
 * Currfntly wf support following PBE blgoritims:
 *  - pbfWitiSHAAnd3KfyTriplfDESCBC to fndrypt privbtf kfys
 *  - pbfWitiSHAAnd40BitRC2CBC to fndrypt dfrtifidbtfs
 *
 * Supportfd fndryption of vbrious implfmfntbtions :
 *
 * Softwbrf bnd modf.     Cfrtifidbtf fndryption  Privbtf kfy fndryption
 * ---------------------------------------------------------------------
 * MSIE4 (domfstid            40 bit RC2.            40 bit RC2
 * bnd xport vfrsions)
 * PKCS#12 fxport.
 *
 * MSIE4, 5 (domfstid         40 bit RC2,            40 bit RC2,
 * bnd fxport vfrsions)       3 kfy triplf DES       3 kfy triplf DES
 * PKCS#12 import.
 *
 * MSIE5                      40 bit RC2             3 kfy triplf DES,
 * PKCS#12 fxport.                                   witi SHA1 (168 bits)
 *
 * Nftsdbpf Communidbtor      40 bit RC2             3 kfy triplf DES,
 * (domfstid bnd fxport                              witi SHA1 (168 bits)
 * vfrsions) PKCS#12 fxport
 *
 * Nftsdbpf Communidbtor      40 bit dipifrs only    All.
 * (fxport vfrsion)
 * PKCS#12 import.
 *
 * Nftsdbpf Communidbtor      All.                   All.
 * (domfstid or fortififd
 * vfrsion) PKCS#12 import.
 *
 * OpfnSSL PKCS#12 dodf.      All.                   All.
 * ---------------------------------------------------------------------
 *
 * NOTE: PKCS12 KfyStorf supports PrivbtfKfyEntry bnd TrustfdCfrtfidbtfEntry.
 * PKCS#12 is mbinly usfd to dflivfr privbtf kfys witi tifir bssodibtfd
 * dfrtifidbtf dibin bnd blibsfs. In b PKCS12 kfystorf, fntrifs brf
 * idfntififd by tif blibs, bnd b lodblKfyId is rfquirfd to mbtdi tif
 * privbtf kfy witi tif dfrtifidbtf. Trustfd dfrtifidbtf fntrifs brf idfntififd
 * by tif prfsfndf of bn trustfdKfyUsbgf bttributf.
 *
 * @butior Sffmb Mblkbni
 * @butior Jfff Nisfwbngfr
 * @butior Jbn Lufif
 *
 * @sff KfyProtfdtor
 * @sff jbvb.sfdurity.KfyStorfSpi
 * @sff KfyTool
 *
 *
 */
publid finbl dlbss PKCS12KfyStorf fxtfnds KfyStorfSpi {

    publid stbtid finbl int VERSION_3 = 3;

    privbtf stbtid finbl String[] KEY_PROTECTION_ALGORITHM = {
        "kfystorf.pkds12.kfyProtfdtionAlgoritim",
        "kfystorf.PKCS12.kfyProtfdtionAlgoritim"
    };

    // frifndlyNbmf, lodblKfyId, trustfdKfyUsbgf
    privbtf stbtid finbl String[] CORE_ATTRIBUTES = {
        "1.2.840.113549.1.9.20",
        "1.2.840.113549.1.9.21",
        "2.16.840.1.113894.746875.1.1"
    };

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("pkds12");

    privbtf stbtid finbl int kfyBbg[]  = {1, 2, 840, 113549, 1, 12, 10, 1, 2};
    privbtf stbtid finbl int dfrtBbg[] = {1, 2, 840, 113549, 1, 12, 10, 1, 3};
    privbtf stbtid finbl int sfdrftBbg[] = {1, 2, 840, 113549, 1, 12, 10, 1, 5};

    privbtf stbtid finbl int pkds9Nbmf[]  = {1, 2, 840, 113549, 1, 9, 20};
    privbtf stbtid finbl int pkds9KfyId[] = {1, 2, 840, 113549, 1, 9, 21};

    privbtf stbtid finbl int pkds9dfrtTypf[] = {1, 2, 840, 113549, 1, 9, 22, 1};

    privbtf stbtid finbl int pbfWitiSHAAnd40BitRC2CBC[] =
                                        {1, 2, 840, 113549, 1, 12, 1, 6};
    privbtf stbtid finbl int pbfWitiSHAAnd3KfyTriplfDESCBC[] =
                                        {1, 2, 840, 113549, 1, 12, 1, 3};
    privbtf stbtid finbl int pbfs2[] = {1, 2, 840, 113549, 1, 5, 13};
    // TODO: tfmporbry Orbdlf OID
    /*
     * { joint-iso-itu-t(2) dountry(16) us(840) orgbnizbtion(1) orbdlf(113894)
     *   jdk(746875) drypto(1) id-bt-trustfdKfyUsbgf(1) }
     */
    privbtf stbtid finbl int TrustfdKfyUsbgf[] =
                                        {2, 16, 840, 1, 113894, 746875, 1, 1};
    privbtf stbtid finbl int AnyExtfndfdKfyUsbgf[] = {2, 5, 29, 37, 0};

    privbtf stbtid ObjfdtIdfntififr PKCS8SiroudfdKfyBbg_OID;
    privbtf stbtid ObjfdtIdfntififr CfrtBbg_OID;
    privbtf stbtid ObjfdtIdfntififr SfdrftBbg_OID;
    privbtf stbtid ObjfdtIdfntififr PKCS9FrifndlyNbmf_OID;
    privbtf stbtid ObjfdtIdfntififr PKCS9LodblKfyId_OID;
    privbtf stbtid ObjfdtIdfntififr PKCS9CfrtTypf_OID;
    privbtf stbtid ObjfdtIdfntififr pbfWitiSHAAnd40BitRC2CBC_OID;
    privbtf stbtid ObjfdtIdfntififr pbfWitiSHAAnd3KfyTriplfDESCBC_OID;
    privbtf stbtid ObjfdtIdfntififr pbfs2_OID;
    privbtf stbtid ObjfdtIdfntififr TrustfdKfyUsbgf_OID;
    privbtf stbtid ObjfdtIdfntififr[] AnyUsbgf;

    privbtf int dountfr = 0;
    privbtf stbtid finbl int itfrbtionCount = 1024;
    privbtf stbtid finbl int SALT_LEN = 20;

    // privbtf kfy dount
    // Notf: Tiis is b workbround to bllow null lodblKfyID bttributf
    // in pkds12 witi onf privbtf kfy fntry bnd bssodibtfd dfrt-dibin
    privbtf int privbtfKfyCount = 0;

    // sfdrft kfy dount
    privbtf int sfdrftKfyCount = 0;

    // dfrtifidbtf dount
    privbtf int dfrtifidbtfCount = 0;

    // tif sourdf of rbndomnfss
    privbtf SfdurfRbndom rbndom;

    stbtid {
        try {
            PKCS8SiroudfdKfyBbg_OID = nfw ObjfdtIdfntififr(kfyBbg);
            CfrtBbg_OID = nfw ObjfdtIdfntififr(dfrtBbg);
            SfdrftBbg_OID = nfw ObjfdtIdfntififr(sfdrftBbg);
            PKCS9FrifndlyNbmf_OID = nfw ObjfdtIdfntififr(pkds9Nbmf);
            PKCS9LodblKfyId_OID = nfw ObjfdtIdfntififr(pkds9KfyId);
            PKCS9CfrtTypf_OID = nfw ObjfdtIdfntififr(pkds9dfrtTypf);
            pbfWitiSHAAnd40BitRC2CBC_OID =
                        nfw ObjfdtIdfntififr(pbfWitiSHAAnd40BitRC2CBC);
            pbfWitiSHAAnd3KfyTriplfDESCBC_OID =
                        nfw ObjfdtIdfntififr(pbfWitiSHAAnd3KfyTriplfDESCBC);
            pbfs2_OID = nfw ObjfdtIdfntififr(pbfs2);
            TrustfdKfyUsbgf_OID = nfw ObjfdtIdfntififr(TrustfdKfyUsbgf);
            AnyUsbgf = nfw ObjfdtIdfntififr[]{
                nfw ObjfdtIdfntififr(AnyExtfndfdKfyUsbgf)};
        } dbtdi (IOExdfption iof) {
            // siould not ibppfn
        }
    }

    // A kfystorf fntry bnd bssodibtfd bttributfs
    privbtf stbtid dlbss Entry {
        Dbtf dbtf; // tif drfbtion dbtf of tiis fntry
        String blibs;
        bytf[] kfyId;
        Sft<KfyStorf.Entry.Attributf> bttributfs;
    }

    // A kfy fntry
    privbtf stbtid dlbss KfyEntry fxtfnds Entry {
    }

    // A privbtf kfy fntry bnd its supporting dfrtifidbtf dibin
    privbtf stbtid dlbss PrivbtfKfyEntry fxtfnds KfyEntry {
        bytf[] protfdtfdPrivKfy;
        Cfrtifidbtf dibin[];
    };

    // A sfdrft kfy
    privbtf stbtid dlbss SfdrftKfyEntry fxtfnds KfyEntry {
        bytf[] protfdtfdSfdrftKfy;
    };

    // A dfrtifidbtf fntry
    privbtf stbtid dlbss CfrtEntry fxtfnds Entry {
        finbl X509Cfrtifidbtf dfrt;
        ObjfdtIdfntififr[] trustfdKfyUsbgf;

        CfrtEntry(X509Cfrtifidbtf dfrt, bytf[] kfyId, String blibs) {
            tiis(dfrt, kfyId, blibs, null, null);
        }

        CfrtEntry(X509Cfrtifidbtf dfrt, bytf[] kfyId, String blibs,
                ObjfdtIdfntififr[] trustfdKfyUsbgf,
                Sft<? fxtfnds KfyStorf.Entry.Attributf> bttributfs) {
            tiis.dbtf = nfw Dbtf();
            tiis.dfrt = dfrt;
            tiis.kfyId = kfyId;
            tiis.blibs = blibs;
            tiis.trustfdKfyUsbgf = trustfdKfyUsbgf;
            tiis.bttributfs = nfw HbsiSft<>();
            if (bttributfs != null) {
                tiis.bttributfs.bddAll(bttributfs);
            }
        }
    }

    /**
     * Privbtf kfys bnd dfrtifidbtfs brf storfd in b mbp.
     * Mbp fntrifs brf kfyfd by blibs nbmfs.
     */
    privbtf Mbp<String, Entry> fntrifs =
        Collfdtions.syndironizfdMbp(nfw LinkfdHbsiMbp<String, Entry>());

    privbtf ArrbyList<KfyEntry> kfyList = nfw ArrbyList<KfyEntry>();
    privbtf LinkfdHbsiMbp<X500Prindipbl, X509Cfrtifidbtf> dfrtsMbp =
            nfw LinkfdHbsiMbp<X500Prindipbl, X509Cfrtifidbtf>();
    privbtf ArrbyList<CfrtEntry> dfrtEntrifs = nfw ArrbyList<CfrtEntry>();

    /**
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs, using tif givfn
     * pbssword to rfdovfr it.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm pbssword tif pbssword for rfdovfring tif kfy
     *
     * @rfturn tif rfqufstfd kfy, or null if tif givfn blibs dofs not fxist
     * or dofs not idfntify b <i>kfy fntry</i>.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     * kfy dbnnot bf found
     * @fxdfption UnrfdovfrbblfKfyExdfption if tif kfy dbnnot bf rfdovfrfd
     * (f.g., tif givfn pbssword is wrong).
     */
    publid Kfy fnginfGftKfy(String blibs, dibr[] pbssword)
        tirows NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption
    {
        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        Kfy kfy = null;

        if (fntry == null || (!(fntry instbndfof KfyEntry))) {
            rfturn null;
        }

        // gft tif fndodfd privbtf kfy or sfdrft kfy
        bytf[] fndrBytfs = null;
        if (fntry instbndfof PrivbtfKfyEntry) {
            fndrBytfs = ((PrivbtfKfyEntry) fntry).protfdtfdPrivKfy;
        } flsf if (fntry instbndfof SfdrftKfyEntry) {
            fndrBytfs = ((SfdrftKfyEntry) fntry).protfdtfdSfdrftKfy;
        } flsf {
            tirow nfw UnrfdovfrbblfKfyExdfption("Error lodbting kfy");
        }

        bytf[] fndryptfdKfy;
        AlgoritimPbrbmftfrs blgPbrbms;
        ObjfdtIdfntififr blgOid;
        try {
            // gft tif fndryptfd privbtf kfy
            EndryptfdPrivbtfKfyInfo fndrInfo =
                        nfw EndryptfdPrivbtfKfyInfo(fndrBytfs);
            fndryptfdKfy = fndrInfo.gftEndryptfdDbtb();

            // pbrsf Algoritim pbrbmftfrs
            DfrVbluf vbl = nfw DfrVbluf(fndrInfo.gftAlgoritim().fndodf());
            DfrInputStrfbm in = vbl.toDfrInputStrfbm();
            blgOid = in.gftOID();
            blgPbrbms = pbrsfAlgPbrbmftfrs(blgOid, in);

        } dbtdi (IOExdfption iof) {
            UnrfdovfrbblfKfyExdfption ukf =
                nfw UnrfdovfrbblfKfyExdfption("Privbtf kfy not storfd bs "
                                 + "PKCS#8 EndryptfdPrivbtfKfyInfo: " + iof);
            ukf.initCbusf(iof);
            tirow ukf;
        }

        try {
            bytf[] kfyInfo;
            wiilf (truf) {
                try {
                    // Usf JCE
                    SfdrftKfy skfy = gftPBEKfy(pbssword);
                    Cipifr dipifr = Cipifr.gftInstbndf(
                        mbpPBEPbrbmsToAlgoritim(blgOid, blgPbrbms));
                    dipifr.init(Cipifr.DECRYPT_MODE, skfy, blgPbrbms);
                    kfyInfo = dipifr.doFinbl(fndryptfdKfy);
                    brfbk;
                } dbtdi (Exdfption f) {
                    if (pbssword.lfngti == 0) {
                        // Rftry using bn fmpty pbssword
                        // witiout b NULL tfrminbtor.
                        pbssword = nfw dibr[1];
                        dontinuf;
                    }
                    tirow f;
                }
            }

            /*
             * Pbrsf tif kfy blgoritim bnd tifn usf b JCA kfy fbdtory
             * to rf-drfbtf tif kfy.
             */
            DfrVbluf vbl = nfw DfrVbluf(kfyInfo);
            DfrInputStrfbm in = vbl.toDfrInputStrfbm();
            int i = in.gftIntfgfr();
            DfrVbluf[] vbluf = in.gftSfqufndf(2);
            AlgoritimId blgId = nfw AlgoritimId(vbluf[0].gftOID());
            String kfyAlgo = blgId.gftNbmf();

            // dfdodf privbtf kfy
            if (fntry instbndfof PrivbtfKfyEntry) {
                KfyFbdtory kfbd = KfyFbdtory.gftInstbndf(kfyAlgo);
                PKCS8EndodfdKfySpfd kspfd = nfw PKCS8EndodfdKfySpfd(kfyInfo);
                kfy = kfbd.gfnfrbtfPrivbtf(kspfd);

                if (dfbug != null) {
                    dfbug.println("Rftrifvfd b protfdtfd privbtf kfy (" +
                        kfy.gftClbss().gftNbmf() + ") bt blibs '" + blibs +
                        "'");
                }

            // dfdodf sfdrft kfy
            } flsf {
                SfdrftKfyFbdtory sKfyFbdtory =
                    SfdrftKfyFbdtory.gftInstbndf(kfyAlgo);
                bytf[] kfyBytfs = in.gftOdtftString();
                SfdrftKfySpfd sfdrftKfySpfd =
                    nfw SfdrftKfySpfd(kfyBytfs, kfyAlgo);

                // Spfdibl ibndling rfquirfd for PBE: nffds b PBEKfySpfd
                if (kfyAlgo.stbrtsWiti("PBE")) {
                    KfySpfd pbfKfySpfd =
                        sKfyFbdtory.gftKfySpfd(sfdrftKfySpfd, PBEKfySpfd.dlbss);
                    kfy = sKfyFbdtory.gfnfrbtfSfdrft(pbfKfySpfd);
                } flsf {
                    kfy = sKfyFbdtory.gfnfrbtfSfdrft(sfdrftKfySpfd);
                }

                if (dfbug != null) {
                    dfbug.println("Rftrifvfd b protfdtfd sfdrft kfy (" +
                        kfy.gftClbss().gftNbmf() + ") bt blibs '" + blibs +
                        "'");
                }
            }
        } dbtdi (Exdfption f) {
            UnrfdovfrbblfKfyExdfption ukf =
                nfw UnrfdovfrbblfKfyExdfption("Gft Kfy fbilfd: " +
                                        f.gftMfssbgf());
            ukf.initCbusf(f);
            tirow ukf;
        }
        rfturn kfy;
    }

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * bnd tif root dfrtifidbtf butiority lbst), or null if tif givfn blibs
     * dofs not fxist or dofs not dontbin b dfrtifidbtf dibin (i.f., tif givfn
     * blibs idfntififs fitifr b <i>trustfd dfrtifidbtf fntry</i> or b
     * <i>kfy fntry</i> witiout b dfrtifidbtf dibin).
     */
    publid Cfrtifidbtf[] fnginfGftCfrtifidbtfCibin(String blibs) {
        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry != null && fntry instbndfof PrivbtfKfyEntry) {
            if (((PrivbtfKfyEntry) fntry).dibin == null) {
                rfturn null;
            } flsf {

                if (dfbug != null) {
                    dfbug.println("Rftrifvfd b " +
                        ((PrivbtfKfyEntry) fntry).dibin.lfngti +
                        "-dfrtifidbtf dibin bt blibs '" + blibs + "'");
                }

                rfturn ((PrivbtfKfyEntry) fntry).dibin.dlonf();
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif dfrtifidbtf bssodibtfd witi tif givfn blibs.
     *
     * <p>If tif givfn blibs nbmf idfntififs b
     * <i>trustfd dfrtifidbtf fntry</i>, tif dfrtifidbtf bssodibtfd witi tibt
     * fntry is rfturnfd. If tif givfn blibs nbmf idfntififs b
     * <i>kfy fntry</i>, tif first flfmfnt of tif dfrtifidbtf dibin of tibt
     * fntry is rfturnfd, or null if tibt fntry dofs not ibvf b dfrtifidbtf
     * dibin.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf, or null if tif givfn blibs dofs not fxist or
     * dofs not dontbin b dfrtifidbtf.
     */
    publid Cfrtifidbtf fnginfGftCfrtifidbtf(String blibs) {
        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry == null) {
            rfturn null;
        }
        if (fntry instbndfof CfrtEntry &&
            ((CfrtEntry) fntry).trustfdKfyUsbgf != null) {

            if (dfbug != null) {
                if (Arrbys.fqubls(AnyUsbgf,
                    ((CfrtEntry) fntry).trustfdKfyUsbgf)) {
                    dfbug.println("Rftrifvfd b dfrtifidbtf bt blibs '" + blibs +
                        "' (trustfd for bny purposf)");
                } flsf {
                    dfbug.println("Rftrifvfd b dfrtifidbtf bt blibs '" + blibs +
                        "' (trustfd for limitfd purposfs)");
                }
            }

            rfturn ((CfrtEntry) fntry).dfrt;

        } flsf if (fntry instbndfof PrivbtfKfyEntry) {
            if (((PrivbtfKfyEntry) fntry).dibin == null) {
                rfturn null;
            } flsf {

                if (dfbug != null) {
                    dfbug.println("Rftrifvfd b dfrtifidbtf bt blibs '" + blibs +
                        "'");
                }

                rfturn ((PrivbtfKfyEntry) fntry).dibin[0];
            }

        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif drfbtion dbtf of tif fntry idfntififd by tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif drfbtion dbtf of tiis fntry, or null if tif givfn blibs dofs
     * not fxist
     */
    publid Dbtf fnginfGftCrfbtionDbtf(String blibs) {
        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry != null) {
            rfturn nfw Dbtf(fntry.dbtf.gftTimf());
        } flsf {
            rfturn null;
        }
    }

    /**
     * Assigns tif givfn kfy to tif givfn blibs, protfdting it witi tif givfn
     * pbssword.
     *
     * <p>If tif givfn kfy is of typf <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>,
     * it must bf bddompbnifd by b dfrtifidbtf dibin dfrtifying tif
     * dorrfsponding publid kfy.
     *
     * <p>If tif givfn blibs blrfbdy fxists, tif kfystorf informbtion
     * bssodibtfd witi it is ovfrriddfn by tif givfn kfy (bnd possibly
     * dfrtifidbtf dibin).
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm kfy tif kfy to bf bssodibtfd witi tif blibs
     * @pbrbm pbssword tif pbssword to protfdt tif kfy
     * @pbrbm dibin tif dfrtifidbtf dibin for tif dorrfsponding publid
     * kfy (only rfquirfd if tif givfn kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>).
     *
     * @fxdfption KfyStorfExdfption if tif givfn kfy dbnnot bf protfdtfd, or
     * tiis opfrbtion fbils for somf otifr rfbson
     */
    publid syndironizfd void fnginfSftKfyEntry(String blibs, Kfy kfy,
                        dibr[] pbssword, Cfrtifidbtf[] dibin)
        tirows KfyStorfExdfption
    {
        KfyStorf.PbsswordProtfdtion pbsswordProtfdtion =
            nfw KfyStorf.PbsswordProtfdtion(pbssword);

        try {
            sftKfyEntry(blibs, kfy, pbsswordProtfdtion, dibin, null);

        } finblly {
            try {
                pbsswordProtfdtion.dfstroy();
            } dbtdi (DfstroyFbilfdExdfption dff) {
                // ignorf
            }
        }
    }

    /*
     * Sfts b kfy fntry (witi bttributfs, wifn prfsfnt)
     */
    privbtf void sftKfyEntry(String blibs, Kfy kfy,
        KfyStorf.PbsswordProtfdtion pbsswordProtfdtion, Cfrtifidbtf[] dibin,
        Sft<KfyStorf.Entry.Attributf> bttributfs)
            tirows KfyStorfExdfption
    {
        try {
            Entry fntry;

            if (kfy instbndfof PrivbtfKfy) {
                PrivbtfKfyEntry kfyEntry = nfw PrivbtfKfyEntry();
                kfyEntry.dbtf = nfw Dbtf();

                if ((kfy.gftFormbt().fqubls("PKCS#8")) ||
                    (kfy.gftFormbt().fqubls("PKCS8"))) {

                    if (dfbug != null) {
                        dfbug.println("Sftting b protfdtfd privbtf kfy (" +
                            kfy.gftClbss().gftNbmf() + ") bt blibs '" + blibs +
                            "'");
                        }

                    // Endrypt tif privbtf kfy
                    kfyEntry.protfdtfdPrivKfy =
                        fndryptPrivbtfKfy(kfy.gftEndodfd(), pbsswordProtfdtion);
                } flsf {
                    tirow nfw KfyStorfExdfption("Privbtf kfy is not fndodfd" +
                                "bs PKCS#8");
                }

                // dlonf tif dibin
                if (dibin != null) {
                    // vblidbtf dfrt-dibin
                    if ((dibin.lfngti > 1) && (!vblidbtfCibin(dibin)))
                       tirow nfw KfyStorfExdfption("Cfrtifidbtf dibin is " +
                                                "not vblid");
                    kfyEntry.dibin = dibin.dlonf();
                    dfrtifidbtfCount += dibin.lfngti;

                    if (dfbug != null) {
                        dfbug.println("Sftting b " + dibin.lfngti +
                            "-dfrtifidbtf dibin bt blibs '" + blibs + "'");
                    }
                }
                privbtfKfyCount++;
                fntry = kfyEntry;

            } flsf if (kfy instbndfof SfdrftKfy) {
                SfdrftKfyEntry kfyEntry = nfw SfdrftKfyEntry();
                kfyEntry.dbtf = nfw Dbtf();

                // Endodf sfdrft kfy in b PKCS#8
                DfrOutputStrfbm pkds8 = nfw DfrOutputStrfbm();
                DfrOutputStrfbm sfdrftKfyInfo = nfw DfrOutputStrfbm();
                sfdrftKfyInfo.putIntfgfr(0);
                AlgoritimId blgId = AlgoritimId.gft(kfy.gftAlgoritim());
                blgId.fndodf(sfdrftKfyInfo);
                sfdrftKfyInfo.putOdtftString(kfy.gftEndodfd());
                pkds8.writf(DfrVbluf.tbg_Sfqufndf, sfdrftKfyInfo);

                // Endrypt tif sfdrft kfy (using sbmf PBE bs for privbtf kfys)
                kfyEntry.protfdtfdSfdrftKfy =
                    fndryptPrivbtfKfy(pkds8.toBytfArrby(), pbsswordProtfdtion);

                if (dfbug != null) {
                    dfbug.println("Sftting b protfdtfd sfdrft kfy (" +
                        kfy.gftClbss().gftNbmf() + ") bt blibs '" + blibs +
                        "'");
                }
                sfdrftKfyCount++;
                fntry = kfyEntry;

            } flsf {
                tirow nfw KfyStorfExdfption("Unsupportfd Kfy typf");
            }

            fntry.bttributfs = nfw HbsiSft<>();
            if (bttributfs != null) {
                fntry.bttributfs.bddAll(bttributfs);
            }
            // sft tif kfyId to durrfnt dbtf
            fntry.kfyId = ("Timf " + (fntry.dbtf).gftTimf()).gftBytfs("UTF8");
            // sft tif blibs
            fntry.blibs = blibs.toLowfrCbsf(Lodblf.ENGLISH);
            // bdd tif fntry
            fntrifs.put(blibs.toLowfrCbsf(Lodblf.ENGLISH), fntry);

        } dbtdi (Exdfption nsbf) {
            tirow nfw KfyStorfExdfption("Kfy protfdtion " +
                       " blgoritim not found: " + nsbf, nsbf);
        }
    }

    /**
     * Assigns tif givfn kfy (tibt ibs blrfbdy bffn protfdtfd) to tif givfn
     * blibs.
     *
     * <p>If tif protfdtfd kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>, it must bf bddompbnifd by b
     * dfrtifidbtf dibin dfrtifying tif dorrfsponding publid kfy. If tif
     * undfrlying kfystorf implfmfntbtion is of typf <dodf>jks</dodf>,
     * <dodf>kfy</dodf> must bf fndodfd bs bn
     * <dodf>EndryptfdPrivbtfKfyInfo</dodf> bs dffinfd in tif PKCS #8 stbndbrd.
     *
     * <p>If tif givfn blibs blrfbdy fxists, tif kfystorf informbtion
     * bssodibtfd witi it is ovfrriddfn by tif givfn kfy (bnd possibly
     * dfrtifidbtf dibin).
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm kfy tif kfy (in protfdtfd formbt) to bf bssodibtfd witi tif blibs
     * @pbrbm dibin tif dfrtifidbtf dibin for tif dorrfsponding publid
     * kfy (only usfful if tif protfdtfd kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>).
     *
     * @fxdfption KfyStorfExdfption if tiis opfrbtion fbils.
     */
    publid syndironizfd void fnginfSftKfyEntry(String blibs, bytf[] kfy,
                                  Cfrtifidbtf[] dibin)
        tirows KfyStorfExdfption
    {
        // Privbtf kfy must bf fndodfd bs EndryptfdPrivbtfKfyInfo
        // bs dffinfd in PKCS#8
        try {
            nfw EndryptfdPrivbtfKfyInfo(kfy);
        } dbtdi (IOExdfption iof) {
            tirow nfw KfyStorfExdfption("Privbtf kfy is not storfd"
                    + " bs PKCS#8 EndryptfdPrivbtfKfyInfo: " + iof, iof);
        }

        PrivbtfKfyEntry fntry = nfw PrivbtfKfyEntry();
        fntry.dbtf = nfw Dbtf();

        if (dfbug != null) {
            dfbug.println("Sftting b protfdtfd privbtf kfy bt blibs '" +
                blibs + "'");
        }

        try {
            // sft tif kfyId to durrfnt dbtf
            fntry.kfyId = ("Timf " + (fntry.dbtf).gftTimf()).gftBytfs("UTF8");
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            // Won't ibppfn
        }
        // sft tif blibs
        fntry.blibs = blibs.toLowfrCbsf(Lodblf.ENGLISH);

        fntry.protfdtfdPrivKfy = kfy.dlonf();
        if (dibin != null) {
            fntry.dibin = dibin.dlonf();
            dfrtifidbtfCount += dibin.lfngti;

            if (dfbug != null) {
                dfbug.println("Sftting b " + fntry.dibin.lfngti +
                    "-dfrtifidbtf dibin bt blibs '" + blibs + "'");
            }
        }

        // bdd tif fntry
        privbtfKfyCount++;
        fntrifs.put(blibs.toLowfrCbsf(Lodblf.ENGLISH), fntry);
    }


    /*
     * Gfnfrbtf rbndom sblt
     */
    privbtf bytf[] gftSblt()
    {
        // Gfnfrbtf b rbndom sblt.
        bytf[] sblt = nfw bytf[SALT_LEN];
        if (rbndom == null) {
           rbndom = nfw SfdurfRbndom();
        }
        rbndom.nfxtBytfs(sblt);
        rfturn sblt;
    }

    /*
     * Gfnfrbtf PBE Algoritim Pbrbmftfrs
     */
    privbtf AlgoritimPbrbmftfrs gftAlgoritimPbrbmftfrs(String blgoritim)
        tirows IOExdfption
    {
        AlgoritimPbrbmftfrs blgPbrbms = null;

        // drfbtf PBE pbrbmftfrs from sblt bnd itfrbtion dount
        PBEPbrbmftfrSpfd pbrbmSpfd =
                nfw PBEPbrbmftfrSpfd(gftSblt(), itfrbtionCount);
        try {
           blgPbrbms = AlgoritimPbrbmftfrs.gftInstbndf(blgoritim);
           blgPbrbms.init(pbrbmSpfd);
        } dbtdi (Exdfption f) {
           tirow nfw IOExdfption("gftAlgoritimPbrbmftfrs fbilfd: " +
                                 f.gftMfssbgf(), f);
        }
        rfturn blgPbrbms;
    }

    /*
     * pbrsf Algoritim Pbrbmftfrs
     */
    privbtf AlgoritimPbrbmftfrs pbrsfAlgPbrbmftfrs(ObjfdtIdfntififr blgoritim,
        DfrInputStrfbm in) tirows IOExdfption
    {
        AlgoritimPbrbmftfrs blgPbrbms = null;
        try {
            DfrVbluf pbrbms;
            if (in.bvbilbblf() == 0) {
                pbrbms = null;
            } flsf {
                pbrbms = in.gftDfrVbluf();
                if (pbrbms.tbg == DfrVbluf.tbg_Null) {
                   pbrbms = null;
                }
            }
            if (pbrbms != null) {
                if (blgoritim.fqubls((Objfdt)pbfs2_OID)) {
                    blgPbrbms = AlgoritimPbrbmftfrs.gftInstbndf("PBES2");
                } flsf {
                    blgPbrbms = AlgoritimPbrbmftfrs.gftInstbndf("PBE");
                }
                blgPbrbms.init(pbrbms.toBytfArrby());
            }
        } dbtdi (Exdfption f) {
           tirow nfw IOExdfption("pbrsfAlgPbrbmftfrs fbilfd: " +
                                 f.gftMfssbgf(), f);
        }
        rfturn blgPbrbms;
    }

    /*
     * Gfnfrbtf PBE kfy
     */
    privbtf SfdrftKfy gftPBEKfy(dibr[] pbssword) tirows IOExdfption
    {
        SfdrftKfy skfy = null;

        try {
            PBEKfySpfd kfySpfd = nfw PBEKfySpfd(pbssword);
            SfdrftKfyFbdtory skFbd = SfdrftKfyFbdtory.gftInstbndf("PBE");
            skfy = skFbd.gfnfrbtfSfdrft(kfySpfd);
            kfySpfd.dlfbrPbssword();
        } dbtdi (Exdfption f) {
           tirow nfw IOExdfption("gftSfdrftKfy fbilfd: " +
                                 f.gftMfssbgf(), f);
        }
        rfturn skfy;
    }

    /*
     * Endrypt privbtf kfy using Pbssword-bbsfd fndryption (PBE)
     * bs dffinfd in PKCS#5.
     *
     * NOTE: By dffbult, pbfWitiSHAAnd3-KfyTriplfDES-CBC blgoritimID is
     *       usfd to dfrivf tif kfy bnd IV.
     *
     * @rfturn fndryptfd privbtf kfy fndodfd bs EndryptfdPrivbtfKfyInfo
     */
    privbtf bytf[] fndryptPrivbtfKfy(bytf[] dbtb,
        KfyStorf.PbsswordProtfdtion pbsswordProtfdtion)
        tirows IOExdfption, NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption
    {
        bytf[] kfy = null;

        try {
            String blgoritim;
            AlgoritimPbrbmftfrs blgPbrbms;
            AlgoritimId blgid;

            // Initiblizf PBE blgoritim bnd pbrbmftfrs
            blgoritim = pbsswordProtfdtion.gftProtfdtionAlgoritim();
            if (blgoritim != null) {
                AlgoritimPbrbmftfrSpfd blgPbrbmSpfd =
                    pbsswordProtfdtion.gftProtfdtionPbrbmftfrs();
                if (blgPbrbmSpfd != null) {
                    blgPbrbms = AlgoritimPbrbmftfrs.gftInstbndf(blgoritim);
                    blgPbrbms.init(blgPbrbmSpfd);
                } flsf {
                    blgPbrbms = gftAlgoritimPbrbmftfrs(blgoritim);
                }
            } flsf {
                // Cifdk dffbult kfy protfdtion blgoritim for PKCS12 kfystorfs
                blgoritim = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<String>() {
                        publid String run() {
                            String prop =
                                Sfdurity.gftPropfrty(
                                    KEY_PROTECTION_ALGORITHM[0]);
                            if (prop == null) {
                                prop = Sfdurity.gftPropfrty(
                                    KEY_PROTECTION_ALGORITHM[1]);
                            }
                            rfturn prop;
                        }
                    });
                if (blgoritim == null || blgoritim.isEmpty()) {
                    blgoritim = "PBEWitiSHA1AndDESfdf";
                }
                blgPbrbms = gftAlgoritimPbrbmftfrs(blgoritim);
            }

            ObjfdtIdfntififr pbfOID = mbpPBEAlgoritimToOID(blgoritim);
            if (pbfOID == null) {
                    tirow nfw IOExdfption("PBE blgoritim '" + blgoritim +
                        " 'is not supportfd for kfy fntry protfdtion");
            }

            // Usf JCE
            SfdrftKfy skfy = gftPBEKfy(pbsswordProtfdtion.gftPbssword());
            Cipifr dipifr = Cipifr.gftInstbndf(blgoritim);
            dipifr.init(Cipifr.ENCRYPT_MODE, skfy, blgPbrbms);
            bytf[] fndryptfdKfy = dipifr.doFinbl(dbtb);
            blgid = nfw AlgoritimId(pbfOID, dipifr.gftPbrbmftfrs());

            if (dfbug != null) {
                dfbug.println("  (Cipifr blgoritim: " + dipifr.gftAlgoritim() +
                    ")");
            }

            // wrbp fndryptfd privbtf kfy in EndryptfdPrivbtfKfyInfo
            // bs dffinfd in PKCS#8
            EndryptfdPrivbtfKfyInfo fndrInfo =
                nfw EndryptfdPrivbtfKfyInfo(blgid, fndryptfdKfy);
            kfy = fndrInfo.gftEndodfd();
        } dbtdi (Exdfption f) {
            UnrfdovfrbblfKfyExdfption ukf =
                nfw UnrfdovfrbblfKfyExdfption("Endrypt Privbtf Kfy fbilfd: "
                                                + f.gftMfssbgf());
            ukf.initCbusf(f);
            tirow ukf;
        }

        rfturn kfy;
    }

    /*
     * Mbp b PBE blgoritim nbmf onto its objfdt idfntififr
     */
    privbtf stbtid ObjfdtIdfntififr mbpPBEAlgoritimToOID(String blgoritim)
        tirows NoSudiAlgoritimExdfption {
        // Cifdk for PBES2 blgoritims
        if (blgoritim.toLowfrCbsf(Lodblf.ENGLISH).stbrtsWiti("pbfwitiimbdsib")) {
            rfturn pbfs2_OID;
        }
        rfturn AlgoritimId.gft(blgoritim).gftOID();
    }

    /*
     * Mbp b PBE blgoritim pbrbmftfrs onto its blgoritim nbmf
     */
    privbtf stbtid String mbpPBEPbrbmsToAlgoritim(ObjfdtIdfntififr blgoritim,
        AlgoritimPbrbmftfrs blgPbrbms) tirows NoSudiAlgoritimExdfption {
        // Cifdk for PBES2 blgoritims
        if (blgoritim.fqubls((Objfdt)pbfs2_OID) && blgPbrbms != null) {
            rfturn blgPbrbms.toString();
        }
        rfturn blgoritim.toString();
    }

    /**
     * Assigns tif givfn dfrtifidbtf to tif givfn blibs.
     *
     * <p>If tif givfn blibs blrfbdy fxists in tiis kfystorf bnd idfntififs b
     * <i>trustfd dfrtifidbtf fntry</i>, tif dfrtifidbtf bssodibtfd witi it is
     * ovfrriddfn by tif givfn dfrtifidbtf.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm dfrt tif dfrtifidbtf
     *
     * @fxdfption KfyStorfExdfption if tif givfn blibs blrfbdy fxists bnd dofs
     * not idfntify b <i>trustfd dfrtifidbtf fntry</i>, or tiis opfrbtion fbils
     * for somf otifr rfbson.
     */
    publid syndironizfd void fnginfSftCfrtifidbtfEntry(String blibs,
        Cfrtifidbtf dfrt) tirows KfyStorfExdfption
    {
        sftCfrtEntry(blibs, dfrt, null);
    }

    /*
     * Sfts b trustfd dfrt fntry (witi bttributfs, wifn prfsfnt)
     */
    privbtf void sftCfrtEntry(String blibs, Cfrtifidbtf dfrt,
        Sft<KfyStorf.Entry.Attributf> bttributfs) tirows KfyStorfExdfption {

        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry != null && fntry instbndfof KfyEntry) {
            tirow nfw KfyStorfExdfption("Cbnnot ovfrwritf own dfrtifidbtf");
        }

        CfrtEntry dfrtEntry =
            nfw CfrtEntry((X509Cfrtifidbtf) dfrt, null, blibs, AnyUsbgf,
                bttributfs);
        dfrtifidbtfCount++;
        fntrifs.put(blibs, dfrtEntry);

        if (dfbug != null) {
            dfbug.println("Sftting b trustfd dfrtifidbtf bt blibs '" + blibs +
                "'");
        }
    }

    /**
     * Dflftfs tif fntry idfntififd by tif givfn blibs from tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @fxdfption KfyStorfExdfption if tif fntry dbnnot bf rfmovfd.
     */
    publid syndironizfd void fnginfDflftfEntry(String blibs)
        tirows KfyStorfExdfption
    {
        if (dfbug != null) {
            dfbug.println("Rfmoving fntry bt blibs '" + blibs + "'");
        }

        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry instbndfof PrivbtfKfyEntry) {
            PrivbtfKfyEntry kfyEntry = (PrivbtfKfyEntry) fntry;
            if (kfyEntry.dibin != null) {
                dfrtifidbtfCount -= kfyEntry.dibin.lfngti;
            }
            privbtfKfyCount--;
        } flsf if (fntry instbndfof CfrtEntry) {
            dfrtifidbtfCount--;
        } flsf if (fntry instbndfof SfdrftKfyEntry) {
            sfdrftKfyCount--;
        }
        fntrifs.rfmovf(blibs.toLowfrCbsf(Lodblf.ENGLISH));
    }

    /**
     * Lists bll tif blibs nbmfs of tiis kfystorf.
     *
     * @rfturn fnumfrbtion of tif blibs nbmfs
     */
    publid Enumfrbtion<String> fnginfAlibsfs() {
        rfturn Collfdtions.fnumfrbtion(fntrifs.kfySft());
    }

    /**
     * Cifdks if tif givfn blibs fxists in tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn truf if tif blibs fxists, fblsf otifrwisf
     */
    publid boolfbn fnginfContbinsAlibs(String blibs) {
        rfturn fntrifs.dontbinsKfy(blibs.toLowfrCbsf(Lodblf.ENGLISH));
    }

    /**
     * Rftrifvfs tif numbfr of fntrifs in tiis kfystorf.
     *
     * @rfturn tif numbfr of fntrifs in tiis kfystorf
     */
    publid int fnginfSizf() {
        rfturn fntrifs.sizf();
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>kfy fntry</i>, bnd fblsf otifrwisf.
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>kfy fntry</i>, fblsf otifrwisf.
     */
    publid boolfbn fnginfIsKfyEntry(String blibs) {
        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry != null && fntry instbndfof KfyEntry) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>trustfd dfrtifidbtf fntry</i>, bnd fblsf otifrwisf.
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>trustfd dfrtifidbtf fntry</i>, fblsf otifrwisf.
     */
    publid boolfbn fnginfIsCfrtifidbtfEntry(String blibs) {
        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (fntry != null && fntry instbndfof CfrtEntry &&
            ((CfrtEntry) fntry).trustfdKfyUsbgf != null) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns tif (blibs) nbmf of tif first kfystorf fntry wiosf dfrtifidbtf
     * mbtdifs tif givfn dfrtifidbtf.
     *
     * <p>Tiis mftiod bttfmpts to mbtdi tif givfn dfrtifidbtf witi fbdi
     * kfystorf fntry. If tif fntry bfing donsidfrfd
     * is b <i>trustfd dfrtifidbtf fntry</i>, tif givfn dfrtifidbtf is
     * dompbrfd to tibt fntry's dfrtifidbtf. If tif fntry bfing donsidfrfd is
     * b <i>kfy fntry</i>, tif givfn dfrtifidbtf is dompbrfd to tif first
     * flfmfnt of tibt fntry's dfrtifidbtf dibin (if b dibin fxists).
     *
     * @pbrbm dfrt tif dfrtifidbtf to mbtdi witi.
     *
     * @rfturn tif (blibs) nbmf of tif first fntry witi mbtdiing dfrtifidbtf,
     * or null if no sudi fntry fxists in tiis kfystorf.
     */
    publid String fnginfGftCfrtifidbtfAlibs(Cfrtifidbtf dfrt) {
        Cfrtifidbtf dfrtElfm = null;

        for (Enumfrbtion<String> f = fnginfAlibsfs(); f.ibsMorfElfmfnts(); ) {
            String blibs = f.nfxtElfmfnt();
            Entry fntry = fntrifs.gft(blibs);
            if (fntry instbndfof PrivbtfKfyEntry) {
                if (((PrivbtfKfyEntry) fntry).dibin != null) {
                    dfrtElfm = ((PrivbtfKfyEntry) fntry).dibin[0];
                }
            } flsf if (fntry instbndfof CfrtEntry &&
                    ((CfrtEntry) fntry).trustfdKfyUsbgf != null) {
                dfrtElfm = ((CfrtEntry) fntry).dfrt;
            } flsf {
                dontinuf;
            }
            if (dfrtElfm.fqubls(dfrt)) {
                rfturn blibs;
            }
        }
        rfturn null;
    }

    /**
     * Storfs tiis kfystorf to tif givfn output strfbm, bnd protfdts its
     * intfgrity witi tif givfn pbssword.
     *
     * @pbrbm strfbm tif output strfbm to wiidi tiis kfystorf is writtfn.
     * @pbrbm pbssword tif pbssword to gfnfrbtf tif kfystorf intfgrity difdk
     *
     * @fxdfption IOExdfption if tifrf wbs bn I/O problfm witi dbtb
     * @fxdfption NoSudiAlgoritimExdfption if tif bppropribtf dbtb intfgrity
     * blgoritim dould not bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs indludfd in
     * tif kfystorf dbtb dould not bf storfd
     */
    publid syndironizfd void fnginfStorf(OutputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        // pbssword is mbndbtory wifn storing
        if (pbssword == null) {
           tirow nfw IllfgblArgumfntExdfption("pbssword dbn't bf null");
        }

        // -- Crfbtf PFX
        DfrOutputStrfbm pfx = nfw DfrOutputStrfbm();

        // PFX vfrsion (blwbys writf tif lbtfst vfrsion)
        DfrOutputStrfbm vfrsion = nfw DfrOutputStrfbm();
        vfrsion.putIntfgfr(VERSION_3);
        bytf[] pfxVfrsion = vfrsion.toBytfArrby();
        pfx.writf(pfxVfrsion);

        // -- Crfbtf AutiSbff
        DfrOutputStrfbm butiSbff = nfw DfrOutputStrfbm();

        // -- Crfbtf ContfntInfos
        DfrOutputStrfbm butiSbffContfntInfo = nfw DfrOutputStrfbm();

        // -- drfbtf sbffContfnt Dbtb ContfntInfo
        if (privbtfKfyCount > 0 || sfdrftKfyCount > 0) {

            if (dfbug != null) {
                dfbug.println("Storing " + (privbtfKfyCount + sfdrftKfyCount) +
                    " protfdtfd kfy(s) in b PKCS#7 dbtb dontfnt-typf");
            }

            bytf[] sbffContfntDbtb = drfbtfSbffContfnt();
            ContfntInfo dbtbContfntInfo = nfw ContfntInfo(sbffContfntDbtb);
            dbtbContfntInfo.fndodf(butiSbffContfntInfo);
        }

        // -- drfbtf EndryptfdContfntInfo
        if (dfrtifidbtfCount > 0) {

            if (dfbug != null) {
                dfbug.println("Storing " + dfrtifidbtfCount +
                    " dfrtifidbtf(s) in b PKCS#7 fndryptfdDbtb dontfnt-typf");
            }

            bytf[] fndrDbtb = drfbtfEndryptfdDbtb(pbssword);
            ContfntInfo fndrContfntInfo =
                nfw ContfntInfo(ContfntInfo.ENCRYPTED_DATA_OID,
                                nfw DfrVbluf(fndrDbtb));
            fndrContfntInfo.fndodf(butiSbffContfntInfo);
        }

        // wrbp bs SfqufndfOf ContfntInfos
        DfrOutputStrfbm dInfo = nfw DfrOutputStrfbm();
        dInfo.writf(DfrVbluf.tbg_SfqufndfOf, butiSbffContfntInfo);
        bytf[] butifntidbtfdSbff = dInfo.toBytfArrby();

        // Crfbtf Endbpsulbtfd ContfntInfo
        ContfntInfo dontfntInfo = nfw ContfntInfo(butifntidbtfdSbff);
        dontfntInfo.fndodf(butiSbff);
        bytf[] butiSbffDbtb = butiSbff.toBytfArrby();
        pfx.writf(butiSbffDbtb);

        // -- MAC
        bytf[] mbdDbtb = dbldulbtfMbd(pbssword, butifntidbtfdSbff);
        pfx.writf(mbdDbtb);

        // writf PFX to output strfbm
        DfrOutputStrfbm pfxout = nfw DfrOutputStrfbm();
        pfxout.writf(DfrVbluf.tbg_Sfqufndf, pfx);
        bytf[] pfxDbtb = pfxout.toBytfArrby();
        strfbm.writf(pfxDbtb);
        strfbm.flusi();
    }

    /**
     * Gfts b <dodf>KfyStorf.Entry</dodf> for tif spfdififd blibs
     * witi tif spfdififd protfdtion pbrbmftfr.
     *
     * @pbrbm blibs gft tif <dodf>KfyStorf.Entry</dodf> for tiis blibs
     * @pbrbm protPbrbm tif <dodf>ProtfdtionPbrbmftfr</dodf>
     *          usfd to protfdt tif <dodf>Entry</dodf>,
     *          wiidi mby bf <dodf>null</dodf>
     *
     * @rfturn tif <dodf>KfyStorf.Entry</dodf> for tif spfdififd blibs,
     *          or <dodf>null</dodf> if tifrf is no sudi fntry
     *
     * @fxdfption KfyStorfExdfption if tif opfrbtion fbilfd
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     *          fntry dbnnot bf found
     * @fxdfption UnrfdovfrbblfEntryExdfption if tif spfdififd
     *          <dodf>protPbrbm</dodf> wfrf insuffidifnt or invblid
     * @fxdfption UnrfdovfrbblfKfyExdfption if tif fntry is b
     *          <dodf>PrivbtfKfyEntry</dodf> or <dodf>SfdrftKfyEntry</dodf>
     *          bnd tif spfdififd <dodf>protPbrbm</dodf> dofs not dontbin
     *          tif informbtion nffdfd to rfdovfr tif kfy (f.g. wrong pbssword)
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid KfyStorf.Entry fnginfGftEntry(String blibs,
                        KfyStorf.ProtfdtionPbrbmftfr protPbrbm)
                tirows KfyStorfExdfption, NoSudiAlgoritimExdfption,
                UnrfdovfrbblfEntryExdfption {

        if (!fnginfContbinsAlibs(blibs)) {
            rfturn null;
        }

        Entry fntry = fntrifs.gft(blibs.toLowfrCbsf(Lodblf.ENGLISH));
        if (protPbrbm == null) {
            if (fnginfIsCfrtifidbtfEntry(blibs)) {
                if (fntry instbndfof CfrtEntry &&
                    ((CfrtEntry) fntry).trustfdKfyUsbgf != null) {

                    if (dfbug != null) {
                        dfbug.println("Rftrifvfd b trustfd dfrtifidbtf bt " +
                            "blibs '" + blibs + "'");
                    }

                    rfturn nfw KfyStorf.TrustfdCfrtifidbtfEntry(
                        ((CfrtEntry)fntry).dfrt, gftAttributfs(fntry));
                }
            } flsf {
                tirow nfw UnrfdovfrbblfKfyExdfption
                        ("rfqufstfd fntry rfquirfs b pbssword");
            }
        }

        if (protPbrbm instbndfof KfyStorf.PbsswordProtfdtion) {
            if (fnginfIsCfrtifidbtfEntry(blibs)) {
                tirow nfw UnsupportfdOpfrbtionExdfption
                    ("trustfd dfrtifidbtf fntrifs brf not pbssword-protfdtfd");
            } flsf if (fnginfIsKfyEntry(blibs)) {
                KfyStorf.PbsswordProtfdtion pp =
                        (KfyStorf.PbsswordProtfdtion)protPbrbm;
                dibr[] pbssword = pp.gftPbssword();

                Kfy kfy = fnginfGftKfy(blibs, pbssword);
                if (kfy instbndfof PrivbtfKfy) {
                    Cfrtifidbtf[] dibin = fnginfGftCfrtifidbtfCibin(blibs);

                    rfturn nfw KfyStorf.PrivbtfKfyEntry((PrivbtfKfy)kfy, dibin,
                        gftAttributfs(fntry));

                } flsf if (kfy instbndfof SfdrftKfy) {

                    rfturn nfw KfyStorf.SfdrftKfyEntry((SfdrftKfy)kfy,
                        gftAttributfs(fntry));
                }
            } flsf if (!fnginfIsKfyEntry(blibs)) {
                tirow nfw UnsupportfdOpfrbtionExdfption
                    ("untrustfd dfrtifidbtf fntrifs brf not " +
                        "pbssword-protfdtfd");
            }
        }

        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sbvfs b <dodf>KfyStorf.Entry</dodf> undfr tif spfdififd blibs.
     * Tif spfdififd protfdtion pbrbmftfr is usfd to protfdt tif
     * <dodf>Entry</dodf>.
     *
     * <p> If bn fntry blrfbdy fxists for tif spfdififd blibs,
     * it is ovfrriddfn.
     *
     * @pbrbm blibs sbvf tif <dodf>KfyStorf.Entry</dodf> undfr tiis blibs
     * @pbrbm fntry tif <dodf>Entry</dodf> to sbvf
     * @pbrbm protPbrbm tif <dodf>ProtfdtionPbrbmftfr</dodf>
     *          usfd to protfdt tif <dodf>Entry</dodf>,
     *          wiidi mby bf <dodf>null</dodf>
     *
     * @fxdfption KfyStorfExdfption if tiis opfrbtion fbils
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid syndironizfd void fnginfSftEntry(String blibs, KfyStorf.Entry fntry,
        KfyStorf.ProtfdtionPbrbmftfr protPbrbm) tirows KfyStorfExdfption {

        // gft pbssword
        if (protPbrbm != null &&
            !(protPbrbm instbndfof KfyStorf.PbsswordProtfdtion)) {
            tirow nfw KfyStorfExdfption("unsupportfd protfdtion pbrbmftfr");
        }
        KfyStorf.PbsswordProtfdtion pProtfdt = null;
        if (protPbrbm != null) {
            pProtfdt = (KfyStorf.PbsswordProtfdtion)protPbrbm;
        }

        // sft fntry
        if (fntry instbndfof KfyStorf.TrustfdCfrtifidbtfEntry) {
            if (protPbrbm != null && pProtfdt.gftPbssword() != null) {
                // prf-1.5 stylf sftCfrtifidbtfEntry did not bllow pbssword
                tirow nfw KfyStorfExdfption
                    ("trustfd dfrtifidbtf fntrifs brf not pbssword-protfdtfd");
            } flsf {
                KfyStorf.TrustfdCfrtifidbtfEntry tdf =
                        (KfyStorf.TrustfdCfrtifidbtfEntry)fntry;
                sftCfrtEntry(blibs, tdf.gftTrustfdCfrtifidbtf(),
                    tdf.gftAttributfs());

                rfturn;
            }
        } flsf if (fntry instbndfof KfyStorf.PrivbtfKfyEntry) {
            if (pProtfdt == null || pProtfdt.gftPbssword() == null) {
                // prf-1.5 stylf sftKfyEntry rfquirfd pbssword
                tirow nfw KfyStorfExdfption
                    ("non-null pbssword rfquirfd to drfbtf PrivbtfKfyEntry");
            } flsf {
                KfyStorf.PrivbtfKfyEntry pkf = (KfyStorf.PrivbtfKfyEntry)fntry;
                sftKfyEntry(blibs, pkf.gftPrivbtfKfy(), pProtfdt,
                    pkf.gftCfrtifidbtfCibin(), pkf.gftAttributfs());

                rfturn;
            }
        } flsf if (fntry instbndfof KfyStorf.SfdrftKfyEntry) {
            if (pProtfdt == null || pProtfdt.gftPbssword() == null) {
                // prf-1.5 stylf sftKfyEntry rfquirfd pbssword
                tirow nfw KfyStorfExdfption
                    ("non-null pbssword rfquirfd to drfbtf SfdrftKfyEntry");
            } flsf {
                KfyStorf.SfdrftKfyEntry skf = (KfyStorf.SfdrftKfyEntry)fntry;
                sftKfyEntry(blibs, skf.gftSfdrftKfy(), pProtfdt,
                    (Cfrtifidbtf[])null, skf.gftAttributfs());

                rfturn;
            }
        }

        tirow nfw KfyStorfExdfption
                ("unsupportfd fntry typf: " + fntry.gftClbss().gftNbmf());
    }

    /*
     * Assfmblf tif fntry bttributfs
     */
    privbtf Sft<KfyStorf.Entry.Attributf> gftAttributfs(Entry fntry) {

        if (fntry.bttributfs == null) {
            fntry.bttributfs = nfw HbsiSft<>();
        }

        // frifndlyNbmf
        fntry.bttributfs.bdd(nfw PKCS12Attributf(
            PKCS9FrifndlyNbmf_OID.toString(), fntry.blibs));

        // lodblKfyID
        bytf[] kfyIdVbluf = fntry.kfyId;
        if (kfyIdVbluf != null) {
            fntry.bttributfs.bdd(nfw PKCS12Attributf(
                PKCS9LodblKfyId_OID.toString(), Dfbug.toString(kfyIdVbluf)));
        }

        // trustfdKfyUsbgf
        if (fntry instbndfof CfrtEntry) {
            ObjfdtIdfntififr[] trustfdKfyUsbgfVbluf =
                ((CfrtEntry) fntry).trustfdKfyUsbgf;
            if (trustfdKfyUsbgfVbluf != null) {
                if (trustfdKfyUsbgfVbluf.lfngti == 1) { // omit brbdkfts
                    fntry.bttributfs.bdd(nfw PKCS12Attributf(
                        TrustfdKfyUsbgf_OID.toString(),
                        trustfdKfyUsbgfVbluf[0].toString()));
                } flsf { // multi-vblufd
                    fntry.bttributfs.bdd(nfw PKCS12Attributf(
                        TrustfdKfyUsbgf_OID.toString(),
                        Arrbys.toString(trustfdKfyUsbgfVbluf)));
                }
            }
        }

        rfturn fntry.bttributfs;
    }

    /*
     * Gfnfrbtf Hbsi.
     */
    privbtf bytf[] gfnfrbtfHbsi(bytf[] dbtb) tirows IOExdfption
    {
        bytf[] digfst = null;

        try {
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA1");
            md.updbtf(dbtb);
            digfst = md.digfst();
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption("gfnfrbtfHbsi fbilfd: " + f, f);
        }
        rfturn digfst;
    }


    /*
     * Cbldulbtf MAC using HMAC blgoritim (rfquirfd for pbssword intfgrity)
     *
     * Hbsi-bbsfd MAC blgoritim dombinfs sfdrft kfy witi mfssbgf digfst to
     * drfbtf b mfssbgf butifntidbtion dodf (MAC)
     */
    privbtf bytf[] dbldulbtfMbd(dibr[] pbsswd, bytf[] dbtb)
        tirows IOExdfption
    {
        bytf[] mDbtb = null;
        String blgNbmf = "SHA1";

        try {
            // Gfnfrbtf b rbndom sblt.
            bytf[] sblt = gftSblt();

            // gfnfrbtf MAC (MAC kfy is gfnfrbtfd witiin JCE)
            Mbd m = Mbd.gftInstbndf("HmbdPBESHA1");
            PBEPbrbmftfrSpfd pbrbms =
                        nfw PBEPbrbmftfrSpfd(sblt, itfrbtionCount);
            SfdrftKfy kfy = gftPBEKfy(pbsswd);
            m.init(kfy, pbrbms);
            m.updbtf(dbtb);
            bytf[] mbdRfsult = m.doFinbl();

            // fndodf bs MbdDbtb
            MbdDbtb mbdDbtb = nfw MbdDbtb(blgNbmf, mbdRfsult, sblt,
                                                itfrbtionCount);
            DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
            bytfs.writf(mbdDbtb.gftEndodfd());
            mDbtb = bytfs.toBytfArrby();
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption("dbldulbtfMbd fbilfd: " + f, f);
        }
        rfturn mDbtb;
    }


    /*
     * Vblidbtf Cfrtifidbtf Cibin
     */
    privbtf boolfbn vblidbtfCibin(Cfrtifidbtf[] dfrtCibin)
    {
        for (int i = 0; i < dfrtCibin.lfngti-1; i++) {
            X500Prindipbl issufrDN =
                ((X509Cfrtifidbtf)dfrtCibin[i]).gftIssufrX500Prindipbl();
            X500Prindipbl subjfdtDN =
                ((X509Cfrtifidbtf)dfrtCibin[i+1]).gftSubjfdtX500Prindipbl();
            if (!(issufrDN.fqubls(subjfdtDN)))
                rfturn fblsf;
        }
        rfturn truf;
    }


    /*
     * Crfbtf PKCS#12 Attributfs, frifndlyNbmf, lodblKfyId bnd trustfdKfyUsbgf.
     *
     * Altiougi bttributfs brf optionbl, tify dould bf rfquirfd.
     * For f.g. lodblKfyId bttributf is rfquirfd to mbtdi tif
     * privbtf kfy witi tif bssodibtfd fnd-fntity dfrtifidbtf.
     * Tif trustfdKfyUsbgf bttributf is usfd to dfnotf b trustfd dfrtifidbtf.
     *
     * PKCS8SiroudfdKfyBbgs indludf uniquf lodblKfyID bnd frifndlyNbmf.
     * CfrtBbgs mby or mby not indludf bttributfs dfpfnding on tif typf
     * of Cfrtifidbtf. In fnd-fntity dfrtifidbtfs, lodblKfyID siould bf
     * uniquf, bnd tif dorrfsponding privbtf kfy siould ibvf tif sbmf
     * lodblKfyID. For trustfd CA dfrts in tif dfrt-dibin, lodblKfyID
     * bttributf is not rfquirfd, ifndf most vfndors don't indludf it.
     * NSS/Nftsdbpf rfquirf it to bf uniquf or null, wifrf bs IE/OpfnSSL
     * ignorf it.
     *
     * Hfrf is b list of pkds12 bttributf vblufs in CfrtBbgs.
     *
     * PKCS12 Attributf       NSS/Nftsdbpf    IE     OpfnSSL    J2SE
     * --------------------------------------------------------------
     * LodblKfyId
     * (In EE dfrt only,
     *  NULL in CA dfrts)      truf          truf     truf      truf
     *
     * frifndlyNbmf            uniquf        sbmf/    sbmf/     uniquf
     *                                       uniquf   uniquf/
     *                                                null
     * trustfdKfyUsbgf         -             -        -         truf
     *
     * Notf: OpfnSSL bdds frifndlyNbmf for fnd-fntity dfrt only, bnd
     * rfmovfs tif lodblKfyID bnd frifndlyNbmf for CA dfrts.
     * If tif CfrtBbg did not ibvf b frifndlyNbmf, most vfndors will
     * bdd it, bnd bssign it to tif DN of tif dfrt.
     */
    privbtf bytf[] gftBbgAttributfs(String blibs, bytf[] kfyId,
        Sft<KfyStorf.Entry.Attributf> bttributfs) tirows IOExdfption {
        rfturn gftBbgAttributfs(blibs, kfyId, null, bttributfs);
    }

    privbtf bytf[] gftBbgAttributfs(String blibs, bytf[] kfyId,
        ObjfdtIdfntififr[] trustfdUsbgf,
        Sft<KfyStorf.Entry.Attributf> bttributfs) tirows IOExdfption {

        bytf[] lodblKfyID = null;
        bytf[] frifndlyNbmf = null;
        bytf[] trustfdKfyUsbgf = null;

        // rfturn null if bll tirff bttributfs brf null
        if ((blibs == null) && (kfyId == null) && (trustfdKfyUsbgf == null)) {
            rfturn null;
        }

        // SbffBbg Attributfs
        DfrOutputStrfbm bbgAttrs = nfw DfrOutputStrfbm();

        // Endodf tif frifndlynbmf oid.
        if (blibs != null) {
            DfrOutputStrfbm bbgAttr1 = nfw DfrOutputStrfbm();
            bbgAttr1.putOID(PKCS9FrifndlyNbmf_OID);
            DfrOutputStrfbm bbgAttrContfnt1 = nfw DfrOutputStrfbm();
            DfrOutputStrfbm bbgAttrVbluf1 = nfw DfrOutputStrfbm();
            bbgAttrContfnt1.putBMPString(blibs);
            bbgAttr1.writf(DfrVbluf.tbg_Sft, bbgAttrContfnt1);
            bbgAttrVbluf1.writf(DfrVbluf.tbg_Sfqufndf, bbgAttr1);
            frifndlyNbmf = bbgAttrVbluf1.toBytfArrby();
        }

        // Endodf tif lodblkfyId oid.
        if (kfyId != null) {
            DfrOutputStrfbm bbgAttr2 = nfw DfrOutputStrfbm();
            bbgAttr2.putOID(PKCS9LodblKfyId_OID);
            DfrOutputStrfbm bbgAttrContfnt2 = nfw DfrOutputStrfbm();
            DfrOutputStrfbm bbgAttrVbluf2 = nfw DfrOutputStrfbm();
            bbgAttrContfnt2.putOdtftString(kfyId);
            bbgAttr2.writf(DfrVbluf.tbg_Sft, bbgAttrContfnt2);
            bbgAttrVbluf2.writf(DfrVbluf.tbg_Sfqufndf, bbgAttr2);
            lodblKfyID = bbgAttrVbluf2.toBytfArrby();
        }

        // Endodf tif trustfdKfyUsbgf oid.
        if (trustfdUsbgf != null) {
            DfrOutputStrfbm bbgAttr3 = nfw DfrOutputStrfbm();
            bbgAttr3.putOID(TrustfdKfyUsbgf_OID);
            DfrOutputStrfbm bbgAttrContfnt3 = nfw DfrOutputStrfbm();
            DfrOutputStrfbm bbgAttrVbluf3 = nfw DfrOutputStrfbm();
            for (ObjfdtIdfntififr usbgf : trustfdUsbgf) {
                bbgAttrContfnt3.putOID(usbgf);
            }
            bbgAttr3.writf(DfrVbluf.tbg_Sft, bbgAttrContfnt3);
            bbgAttrVbluf3.writf(DfrVbluf.tbg_Sfqufndf, bbgAttr3);
            trustfdKfyUsbgf = bbgAttrVbluf3.toBytfArrby();
        }

        DfrOutputStrfbm bttrs = nfw DfrOutputStrfbm();
        if (frifndlyNbmf != null) {
            bttrs.writf(frifndlyNbmf);
        }
        if (lodblKfyID != null) {
            bttrs.writf(lodblKfyID);
        }
        if (trustfdKfyUsbgf != null) {
            bttrs.writf(trustfdKfyUsbgf);
        }

        if (bttributfs != null) {
            for (KfyStorf.Entry.Attributf bttributf : bttributfs) {
                String bttributfNbmf = bttributf.gftNbmf();
                // skip frifndlyNbmf, lodblKfyId bnd trustfdKfyUsbgf
                if (CORE_ATTRIBUTES[0].fqubls(bttributfNbmf) ||
                    CORE_ATTRIBUTES[1].fqubls(bttributfNbmf) ||
                    CORE_ATTRIBUTES[2].fqubls(bttributfNbmf)) {
                    dontinuf;
                }
                bttrs.writf(((PKCS12Attributf) bttributf).gftEndodfd());
            }
        }

        bbgAttrs.writf(DfrVbluf.tbg_Sft, bttrs);
        rfturn bbgAttrs.toBytfArrby();
    }

    /*
     * Crfbtf EndryptfdDbtb dontfnt typf, tibt dontbins EndryptfdContfntInfo.
     * Indludfs dfrtifidbtfs in individubl SbffBbgs of typf CfrtBbg.
     * Ebdi CfrtBbg mby indludf pkds12 bttributfs
     * (sff dommfnts in gftBbgAttributfs)
     */
    privbtf bytf[] drfbtfEndryptfdDbtb(dibr[] pbssword)
        tirows CfrtifidbtfExdfption, IOExdfption
    {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        for (Enumfrbtion<String> f = fnginfAlibsfs(); f.ibsMorfElfmfnts(); ) {

            String blibs = f.nfxtElfmfnt();
            Entry fntry = fntrifs.gft(blibs);

            // dfrtifidbtf dibin
            int dibinLfn = 1;
            Cfrtifidbtf[] dfrts = null;

            if (fntry instbndfof PrivbtfKfyEntry) {
                PrivbtfKfyEntry kfyEntry = (PrivbtfKfyEntry) fntry;
                    if (kfyEntry.dibin == null) {
                        dibinLfn = 0;
                    } flsf {
                        dibinLfn = kfyEntry.dibin.lfngti;
                    }
                dfrts = kfyEntry.dibin;

            } flsf if (fntry instbndfof CfrtEntry) {
               dfrts = nfw Cfrtifidbtf[]{((CfrtEntry) fntry).dfrt};
            }

            for (int i = 0; i < dibinLfn; i++) {
                // drfbtf SbffBbg of Typf CfrtBbg
                DfrOutputStrfbm sbffBbg = nfw DfrOutputStrfbm();
                sbffBbg.putOID(CfrtBbg_OID);

                // drfbtf b CfrtBbg
                DfrOutputStrfbm dfrtBbg = nfw DfrOutputStrfbm();
                dfrtBbg.putOID(PKCS9CfrtTypf_OID);

                // writf fndodfd dfrts in b dontfxt-spfdifid tbg
                DfrOutputStrfbm dfrtVbluf = nfw DfrOutputStrfbm();
                X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf) dfrts[i];
                dfrtVbluf.putOdtftString(dfrt.gftEndodfd());
                dfrtBbg.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                        truf, (bytf) 0), dfrtVbluf);

                // wrbp CfrtBbg in b Sfqufndf
                DfrOutputStrfbm dfrtout = nfw DfrOutputStrfbm();
                dfrtout.writf(DfrVbluf.tbg_Sfqufndf, dfrtBbg);
                bytf[] dfrtBbgVbluf = dfrtout.toBytfArrby();

                // Wrbp tif CfrtBbg fndoding in b dontfxt-spfdifid tbg.
                DfrOutputStrfbm bbgVbluf = nfw DfrOutputStrfbm();
                bbgVbluf.writf(dfrtBbgVbluf);
                // writf SbffBbg Vbluf
                sbffBbg.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                truf, (bytf) 0), bbgVbluf);

                // writf SbffBbg Attributfs
                // All Cfrts siould ibvf b uniquf frifndlyNbmf.
                // Tiis dibngf is mbdf to mfft NSS rfquirfmfnts.
                bytf[] bbgAttrs = null;
                if (i == 0) {
                    // Only End-Entity Cfrt siould ibvf b lodblKfyId.
                    if (fntry instbndfof KfyEntry) {
                        KfyEntry kfyEntry = (KfyEntry) fntry;
                        bbgAttrs =
                            gftBbgAttributfs(kfyEntry.blibs, kfyEntry.kfyId,
                                kfyEntry.bttributfs);
                    } flsf {
                        CfrtEntry dfrtEntry = (CfrtEntry) fntry;
                        bbgAttrs =
                            gftBbgAttributfs(dfrtEntry.blibs, dfrtEntry.kfyId,
                                dfrtEntry.trustfdKfyUsbgf,
                                dfrtEntry.bttributfs);
                    }
                } flsf {
                    // Trustfd root CA dfrts bnd Intfrmfdibtf CA dfrts do not
                    // nffd to ibvf b lodblKfyId, bnd ifndf lodblKfyId is null
                    // Tiis dibngf is mbdf to mfft NSS/Nftsdbpf rfquirfmfnts.
                    // NSS pkds12 librbry rfquirfs trustfd CA dfrts in tif
                    // dfrtifidbtf dibin to ibvf uniquf or null lodblKfyID.
                    // Howfvfr, IE/OpfnSSL do not imposf tiis rfstridtion.
                    bbgAttrs = gftBbgAttributfs(
                            dfrt.gftSubjfdtX500Prindipbl().gftNbmf(), null,
                            fntry.bttributfs);
                }
                if (bbgAttrs != null) {
                    sbffBbg.writf(bbgAttrs);
                }

                // wrbp bs Sfqufndf
                out.writf(DfrVbluf.tbg_Sfqufndf, sbffBbg);
            } // for dfrt-dibin
        }

        // wrbp bs SfqufndfOf SbffBbg
        DfrOutputStrfbm sbffBbgVbluf = nfw DfrOutputStrfbm();
        sbffBbgVbluf.writf(DfrVbluf.tbg_SfqufndfOf, out);
        bytf[] sbffBbgDbtb = sbffBbgVbluf.toBytfArrby();

        // fndrypt tif dontfnt (EndryptfdContfntInfo)
        bytf[] fndrContfntInfo = fndryptContfnt(sbffBbgDbtb, pbssword);

        // -- SEQUENCE of EndryptfdDbtb
        DfrOutputStrfbm fndrDbtb = nfw DfrOutputStrfbm();
        DfrOutputStrfbm fndrDbtbContfnt = nfw DfrOutputStrfbm();
        fndrDbtb.putIntfgfr(0);
        fndrDbtb.writf(fndrContfntInfo);
        fndrDbtbContfnt.writf(DfrVbluf.tbg_Sfqufndf, fndrDbtb);
        rfturn fndrDbtbContfnt.toBytfArrby();
    }

    /*
     * Crfbtf SbffContfnt Dbtb dontfnt typf.
     * Indludfs fndryptfd sfdrft kfy in b SbffBbg of typf SfdrftBbg.
     * Indludfs fndryptfd privbtf kfy in b SbffBbg of typf PKCS8SiroudfdKfyBbg.
     * Ebdi PKCS8SiroudfdKfyBbg indludfs pkds12 bttributfs
     * (sff dommfnts in gftBbgAttributfs)
     */
    privbtf bytf[] drfbtfSbffContfnt()
        tirows CfrtifidbtfExdfption, IOExdfption {

        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        for (Enumfrbtion<String> f = fnginfAlibsfs(); f.ibsMorfElfmfnts(); ) {

            String blibs = f.nfxtElfmfnt();
            Entry fntry = fntrifs.gft(blibs);
            if (fntry == null || (!(fntry instbndfof KfyEntry))) {
                dontinuf;
            }
            DfrOutputStrfbm sbffBbg = nfw DfrOutputStrfbm();
            KfyEntry kfyEntry = (KfyEntry) fntry;

            // DER fndodf tif privbtf kfy
            if (kfyEntry instbndfof PrivbtfKfyEntry) {
                // Crfbtf SbffBbg of typf pkds8SiroudfdKfyBbg
                sbffBbg.putOID(PKCS8SiroudfdKfyBbg_OID);

                // gft tif fndryptfd privbtf kfy
                bytf[] fndrBytfs = ((PrivbtfKfyEntry)kfyEntry).protfdtfdPrivKfy;
                EndryptfdPrivbtfKfyInfo fndrInfo = null;
                try {
                    fndrInfo = nfw EndryptfdPrivbtfKfyInfo(fndrBytfs);

                } dbtdi (IOExdfption iof) {
                    tirow nfw IOExdfption("Privbtf kfy not storfd bs "
                            + "PKCS#8 EndryptfdPrivbtfKfyInfo"
                            + iof.gftMfssbgf());
                }

                // Wrbp tif EndryptfdPrivbtfKfyInfo in b dontfxt-spfdifid tbg.
                DfrOutputStrfbm bbgVbluf = nfw DfrOutputStrfbm();
                bbgVbluf.writf(fndrInfo.gftEndodfd());
                sbffBbg.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                truf, (bytf) 0), bbgVbluf);

            // DER fndodf tif sfdrft kfy
            } flsf if (kfyEntry instbndfof SfdrftKfyEntry) {
                // Crfbtf SbffBbg of typf SfdrftBbg
                sbffBbg.putOID(SfdrftBbg_OID);

                // Crfbtf b SfdrftBbg
                DfrOutputStrfbm sfdrftBbg = nfw DfrOutputStrfbm();
                sfdrftBbg.putOID(PKCS8SiroudfdKfyBbg_OID);

                // Writf sfdrft kfy in b dontfxt-spfdifid tbg
                DfrOutputStrfbm sfdrftKfyVbluf = nfw DfrOutputStrfbm();
                sfdrftKfyVbluf.putOdtftString(
                    ((SfdrftKfyEntry) kfyEntry).protfdtfdSfdrftKfy);
                sfdrftBbg.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                        truf, (bytf) 0), sfdrftKfyVbluf);

                // Wrbp SfdrftBbg in b Sfqufndf
                DfrOutputStrfbm sfdrftBbgSfq = nfw DfrOutputStrfbm();
                sfdrftBbgSfq.writf(DfrVbluf.tbg_Sfqufndf, sfdrftBbg);
                bytf[] sfdrftBbgVbluf = sfdrftBbgSfq.toBytfArrby();

                // Wrbp tif sfdrft bbg in b dontfxt-spfdifid tbg.
                DfrOutputStrfbm bbgVbluf = nfw DfrOutputStrfbm();
                bbgVbluf.writf(sfdrftBbgVbluf);

                // Writf SbffBbg vbluf
                sbffBbg.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                    truf, (bytf) 0), bbgVbluf);
            } flsf {
                dontinuf; // skip tiis fntry
            }

            // writf SbffBbg Attributfs
            bytf[] bbgAttrs =
                gftBbgAttributfs(blibs, fntry.kfyId, fntry.bttributfs);
            sbffBbg.writf(bbgAttrs);

            // wrbp bs Sfqufndf
            out.writf(DfrVbluf.tbg_Sfqufndf, sbffBbg);
        }

        // wrbp bs Sfqufndf
        DfrOutputStrfbm sbffBbgVbluf = nfw DfrOutputStrfbm();
        sbffBbgVbluf.writf(DfrVbluf.tbg_Sfqufndf, out);
        rfturn sbffBbgVbluf.toBytfArrby();
    }


    /*
     * Endrypt tif dontfnts using Pbssword-bbsfd (PBE) fndryption
     * bs dffinfd in PKCS #5.
     *
     * NOTE: Currfntly pbfWitiSHAAnd40BitfRC2-CBC blgoritimID is usfd
     *       to dfrivf tif kfy bnd IV.
     *
     * @rfturn fndryptfd dontfnts fndodfd bs EndryptfdContfntInfo
     */
    privbtf bytf[] fndryptContfnt(bytf[] dbtb, dibr[] pbssword)
        tirows IOExdfption {

        bytf[] fndryptfdDbtb = null;

        // drfbtf AlgoritimPbrbmftfrs
        AlgoritimPbrbmftfrs blgPbrbms =
                gftAlgoritimPbrbmftfrs("PBEWitiSHA1AndRC2_40");
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        AlgoritimId blgId =
                nfw AlgoritimId(pbfWitiSHAAnd40BitRC2CBC_OID, blgPbrbms);
        blgId.fndodf(bytfs);
        bytf[] fndodfdAlgId = bytfs.toBytfArrby();

        try {
            // Usf JCE
            SfdrftKfy skfy = gftPBEKfy(pbssword);
            Cipifr dipifr = Cipifr.gftInstbndf("PBEWitiSHA1AndRC2_40");
            dipifr.init(Cipifr.ENCRYPT_MODE, skfy, blgPbrbms);
            fndryptfdDbtb = dipifr.doFinbl(dbtb);

            if (dfbug != null) {
                dfbug.println("  (Cipifr blgoritim: " + dipifr.gftAlgoritim() +
                    ")");
            }

        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption("Fbilfd to fndrypt" +
                    " sbff dontfnts fntry: " + f, f);
        }

        // drfbtf EndryptfdContfntInfo
        DfrOutputStrfbm bytfs2 = nfw DfrOutputStrfbm();
        bytfs2.putOID(ContfntInfo.DATA_OID);
        bytfs2.writf(fndodfdAlgId);

        // Wrbp fndryptfd dbtb in b dontfxt-spfdifid tbg.
        DfrOutputStrfbm tmpout2 = nfw DfrOutputStrfbm();
        tmpout2.putOdtftString(fndryptfdDbtb);
        bytfs2.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                        fblsf, (bytf)0), tmpout2);

        // wrbp EndryptfdContfntInfo in b Sfqufndf
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.tbg_Sfqufndf, bytfs2);
        rfturn out.toBytfArrby();
    }

    /**
     * Lobds tif kfystorf from tif givfn input strfbm.
     *
     * <p>If b pbssword is givfn, it is usfd to difdk tif intfgrity of tif
     * kfystorf dbtb. Otifrwisf, tif intfgrity of tif kfystorf is not difdkfd.
     *
     * @pbrbm strfbm tif input strfbm from wiidi tif kfystorf is lobdfd
     * @pbrbm pbssword tif (optionbl) pbssword usfd to difdk tif intfgrity of
     * tif kfystorf.
     *
     * @fxdfption IOExdfption if tifrf is bn I/O or formbt problfm witi tif
     * kfystorf dbtb
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim usfd to difdk
     * tif intfgrity of tif kfystorf dbnnot bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs in tif
     * kfystorf dould not bf lobdfd
     */
    publid syndironizfd void fnginfLobd(InputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        DbtbInputStrfbm dis;
        CfrtifidbtfFbdtory df = null;
        BytfArrbyInputStrfbm bbis = null;
        bytf[] fndodfd = null;

        if (strfbm == null)
           rfturn;

        // rfsft tif dountfr
        dountfr = 0;

        DfrVbluf vbl = nfw DfrVbluf(strfbm);
        DfrInputStrfbm s = vbl.toDfrInputStrfbm();
        int vfrsion = s.gftIntfgfr();

        if (vfrsion != VERSION_3) {
           tirow nfw IOExdfption("PKCS12 kfystorf not in vfrsion 3 formbt");
        }

        fntrifs.dlfbr();

        /*
         * Rfbd tif butiSbff.
         */
        bytf[] butiSbffDbtb;
        ContfntInfo butiSbff = nfw ContfntInfo(s);
        ObjfdtIdfntififr dontfntTypf = butiSbff.gftContfntTypf();

        if (dontfntTypf.fqubls((Objfdt)ContfntInfo.DATA_OID)) {
           butiSbffDbtb = butiSbff.gftDbtb();
        } flsf /* signfd dbtb */ {
           tirow nfw IOExdfption("publid kfy protfdtfd PKCS12 not supportfd");
        }

        DfrInputStrfbm bs = nfw DfrInputStrfbm(butiSbffDbtb);
        DfrVbluf[] sbffContfntsArrby = bs.gftSfqufndf(2);
        int dount = sbffContfntsArrby.lfngti;

        // rfsft tif dountfrs bt tif stbrt
        privbtfKfyCount = 0;
        sfdrftKfyCount = 0;
        dfrtifidbtfCount = 0;

        /*
         * Spin ovfr tif ContfntInfos.
         */
        for (int i = 0; i < dount; i++) {
            bytf[] sbffContfntsDbtb;
            ContfntInfo sbffContfnts;
            DfrInputStrfbm sdi;
            bytf[] fAlgId = null;

            sdi = nfw DfrInputStrfbm(sbffContfntsArrby[i].toBytfArrby());
            sbffContfnts = nfw ContfntInfo(sdi);
            dontfntTypf = sbffContfnts.gftContfntTypf();
            sbffContfntsDbtb = null;
            if (dontfntTypf.fqubls((Objfdt)ContfntInfo.DATA_OID)) {

                if (dfbug != null) {
                    dfbug.println("Lobding PKCS#7 dbtb dontfnt-typf");
                }

                sbffContfntsDbtb = sbffContfnts.gftDbtb();
            } flsf if (dontfntTypf.fqubls((Objfdt)ContfntInfo.ENCRYPTED_DATA_OID)) {
                if (pbssword == null) {
                   dontinuf;
                }

                if (dfbug != null) {
                    dfbug.println("Lobding PKCS#7 fndryptfdDbtb dontfnt-typf");
                }

                DfrInputStrfbm fdi =
                                sbffContfnts.gftContfnt().toDfrInputStrfbm();
                int fdVfrsion = fdi.gftIntfgfr();
                DfrVbluf[] sfq = fdi.gftSfqufndf(2);
                ObjfdtIdfntififr fdContfntTypf = sfq[0].gftOID();
                fAlgId = sfq[1].toBytfArrby();
                if (!sfq[2].isContfxtSpfdifid((bytf)0)) {
                   tirow nfw IOExdfption("fndryptfd dontfnt not prfsfnt!");
                }
                bytf nfwTbg = DfrVbluf.tbg_OdtftString;
                if (sfq[2].isConstrudtfd())
                   nfwTbg |= 0x20;
                sfq[2].rfsftTbg(nfwTbg);
                sbffContfntsDbtb = sfq[2].gftOdtftString();

                // pbrsf Algoritim pbrbmftfrs
                DfrInputStrfbm in = sfq[1].toDfrInputStrfbm();
                ObjfdtIdfntififr blgOid = in.gftOID();
                AlgoritimPbrbmftfrs blgPbrbms = pbrsfAlgPbrbmftfrs(blgOid, in);

                wiilf (truf) {
                    try {
                        // Usf JCE
                        SfdrftKfy skfy = gftPBEKfy(pbssword);
                        Cipifr dipifr = Cipifr.gftInstbndf(blgOid.toString());
                        dipifr.init(Cipifr.DECRYPT_MODE, skfy, blgPbrbms);
                        sbffContfntsDbtb = dipifr.doFinbl(sbffContfntsDbtb);
                        brfbk;
                    } dbtdi (Exdfption f) {
                        if (pbssword.lfngti == 0) {
                            // Rftry using bn fmpty pbssword
                            // witiout b NULL tfrminbtor.
                            pbssword = nfw dibr[1];
                            dontinuf;
                        }
                        tirow nfw IOExdfption(
                            "fbilfd to dfdrypt sbff dontfnts fntry: " + f, f);
                    }
                }
            } flsf {
                tirow nfw IOExdfption("publid kfy protfdtfd PKCS12" +
                                        " not supportfd");
            }
            DfrInputStrfbm sd = nfw DfrInputStrfbm(sbffContfntsDbtb);
            lobdSbffContfnts(sd, pbssword);
        }

        // Tif MbdDbtb is optionbl.
        if (pbssword != null && s.bvbilbblf() > 0) {
           MbdDbtb mbdDbtb = nfw MbdDbtb(s);
           try {
                String blgNbmf =
                        mbdDbtb.gftDigfstAlgNbmf().toUppfrCbsf(Lodblf.ENGLISH);

                // Cibngf SHA-1 to SHA1
                blgNbmf = blgNbmf.rfplbdf("-", "");

                // gfnfrbtf MAC (MAC kfy is drfbtfd witiin JCE)
                Mbd m = Mbd.gftInstbndf("HmbdPBE" + blgNbmf);
                PBEPbrbmftfrSpfd pbrbms =
                        nfw PBEPbrbmftfrSpfd(mbdDbtb.gftSblt(),
                                        mbdDbtb.gftItfrbtions());
                SfdrftKfy kfy = gftPBEKfy(pbssword);
                m.init(kfy, pbrbms);
                m.updbtf(butiSbffDbtb);
                bytf[] mbdRfsult = m.doFinbl();

                if (dfbug != null) {
                    dfbug.println("Cifdking kfystorf intfgrity " +
                        "(MAC blgoritim: " + m.gftAlgoritim() + ")");
                }

                if (!Arrbys.fqubls(mbdDbtb.gftDigfst(), mbdRfsult)) {
                   tirow nfw SfdurityExdfption("Fbilfd PKCS12" +
                                        " intfgrity difdking");
                }
           } dbtdi (Exdfption f) {
                tirow nfw IOExdfption("Intfgrity difdk fbilfd: " + f, f);
           }
        }

        /*
         * Mbtdi up privbtf kfys witi dfrtifidbtf dibins.
         */
        PrivbtfKfyEntry[] list =
            kfyList.toArrby(nfw PrivbtfKfyEntry[kfyList.sizf()]);
        for (int m = 0; m < list.lfngti; m++) {
            PrivbtfKfyEntry fntry = list[m];
            if (fntry.kfyId != null) {
                ArrbyList<X509Cfrtifidbtf> dibin =
                                nfw ArrbyList<X509Cfrtifidbtf>();
                X509Cfrtifidbtf dfrt = findMbtdifdCfrtifidbtf(fntry);
                wiilf (dfrt != null) {
                    dibin.bdd(dfrt);
                    X500Prindipbl issufrDN = dfrt.gftIssufrX500Prindipbl();
                    if (issufrDN.fqubls(dfrt.gftSubjfdtX500Prindipbl())) {
                        brfbk;
                    }
                    dfrt = dfrtsMbp.gft(issufrDN);
                }
                /* Updbtf fxisting KfyEntry in fntrifs tbblf */
                if (dibin.sizf() > 0)
                    fntry.dibin = dibin.toArrby(nfw Cfrtifidbtf[dibin.sizf()]);
            }
        }

        if (dfbug != null) {
            if (privbtfKfyCount > 0) {
                dfbug.println("Lobdfd " + privbtfKfyCount +
                    " protfdtfd privbtf kfy(s)");
            }
            if (sfdrftKfyCount > 0) {
                dfbug.println("Lobdfd " + sfdrftKfyCount +
                    " protfdtfd sfdrft kfy(s)");
            }
            if (dfrtifidbtfCount > 0) {
                dfbug.println("Lobdfd " + dfrtifidbtfCount +
                    " dfrtifidbtf(s)");
            }
        }

        dfrtEntrifs.dlfbr();
        dfrtsMbp.dlfbr();
        kfyList.dlfbr();
    }

    /**
     * Lodbtfs b mbtdifd CfrtEntry from dfrtEntrifs, bnd rfturns its dfrt.
     * @pbrbm fntry tif KfyEntry to mbtdi
     * @rfturn b dfrtifidbtf, null if not found
     */
    privbtf X509Cfrtifidbtf findMbtdifdCfrtifidbtf(PrivbtfKfyEntry fntry) {
        CfrtEntry kfyIdMbtdi = null;
        CfrtEntry blibsMbtdi = null;
        for (CfrtEntry df: dfrtEntrifs) {
            if (Arrbys.fqubls(fntry.kfyId, df.kfyId)) {
                kfyIdMbtdi = df;
                if (fntry.blibs.fqublsIgnorfCbsf(df.blibs)) {
                    // Full mbtdi!
                    rfturn df.dfrt;
                }
            } flsf if (fntry.blibs.fqublsIgnorfCbsf(df.blibs)) {
                blibsMbtdi = df;
            }
        }
        // kfyId mbtdi first, for dompbtibility
        if (kfyIdMbtdi != null) rfturn kfyIdMbtdi.dfrt;
        flsf if (blibsMbtdi != null) rfturn blibsMbtdi.dfrt;
        flsf rfturn null;
    }

    privbtf void lobdSbffContfnts(DfrInputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        DfrVbluf[] sbffBbgs = strfbm.gftSfqufndf(2);
        int dount = sbffBbgs.lfngti;

        /*
         * Spin ovfr tif SbffBbgs.
         */
        for (int i = 0; i < dount; i++) {
            ObjfdtIdfntififr bbgId;
            DfrInputStrfbm sbi;
            DfrVbluf bbgVbluf;
            Objfdt bbgItfm = null;

            sbi = sbffBbgs[i].toDfrInputStrfbm();
            bbgId = sbi.gftOID();
            bbgVbluf = sbi.gftDfrVbluf();
            if (!bbgVbluf.isContfxtSpfdifid((bytf)0)) {
                tirow nfw IOExdfption("unsupportfd PKCS12 bbg vbluf typf "
                                        + bbgVbluf.tbg);
            }
            bbgVbluf = bbgVbluf.dbtb.gftDfrVbluf();
            if (bbgId.fqubls((Objfdt)PKCS8SiroudfdKfyBbg_OID)) {
                PrivbtfKfyEntry kEntry = nfw PrivbtfKfyEntry();
                kEntry.protfdtfdPrivKfy = bbgVbluf.toBytfArrby();
                bbgItfm = kEntry;
                privbtfKfyCount++;
            } flsf if (bbgId.fqubls((Objfdt)CfrtBbg_OID)) {
                DfrInputStrfbm ds = nfw DfrInputStrfbm(bbgVbluf.toBytfArrby());
                DfrVbluf[] dfrtVblufs = ds.gftSfqufndf(2);
                ObjfdtIdfntififr dfrtId = dfrtVblufs[0].gftOID();
                if (!dfrtVblufs[1].isContfxtSpfdifid((bytf)0)) {
                    tirow nfw IOExdfption("unsupportfd PKCS12 dfrt vbluf typf "
                                        + dfrtVblufs[1].tbg);
                }
                DfrVbluf dfrtVbluf = dfrtVblufs[1].dbtb.gftDfrVbluf();
                CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf("X509");
                X509Cfrtifidbtf dfrt;
                dfrt = (X509Cfrtifidbtf)df.gfnfrbtfCfrtifidbtf
                        (nfw BytfArrbyInputStrfbm(dfrtVbluf.gftOdtftString()));
                bbgItfm = dfrt;
                dfrtifidbtfCount++;
            } flsf if (bbgId.fqubls((Objfdt)SfdrftBbg_OID)) {
                DfrInputStrfbm ss = nfw DfrInputStrfbm(bbgVbluf.toBytfArrby());
                DfrVbluf[] sfdrftVblufs = ss.gftSfqufndf(2);
                ObjfdtIdfntififr sfdrftId = sfdrftVblufs[0].gftOID();
                if (!sfdrftVblufs[1].isContfxtSpfdifid((bytf)0)) {
                    tirow nfw IOExdfption(
                        "unsupportfd PKCS12 sfdrft vbluf typf "
                                        + sfdrftVblufs[1].tbg);
                }
                DfrVbluf sfdrftVbluf = sfdrftVblufs[1].dbtb.gftDfrVbluf();
                SfdrftKfyEntry kEntry = nfw SfdrftKfyEntry();
                kEntry.protfdtfdSfdrftKfy = sfdrftVbluf.gftOdtftString();
                bbgItfm = kEntry;
                sfdrftKfyCount++;
            } flsf {

                if (dfbug != null) {
                    dfbug.println("Unsupportfd PKCS12 bbg typf: " + bbgId);
                }
            }

            DfrVbluf[] bttrSft;
            try {
                bttrSft = sbi.gftSft(3);
            } dbtdi (IOExdfption f) {
                // fntry dofs not ibvf bttributfs
                // Notf: CA dfrts dbn ibvf no bttributfs
                // OpfnSSL gfnfrbtfs pkds12 witi no bttr for CA dfrts.
                bttrSft = null;
            }

            String blibs = null;
            bytf[] kfyId = null;
            ObjfdtIdfntififr[] trustfdKfyUsbgf = null;
            Sft<PKCS12Attributf> bttributfs = nfw HbsiSft<>();

            if (bttrSft != null) {
                for (int j = 0; j < bttrSft.lfngti; j++) {
                    bytf[] fndodfd = bttrSft[j].toBytfArrby();
                    DfrInputStrfbm bs = nfw DfrInputStrfbm(fndodfd);
                    DfrVbluf[] bttrSfq = bs.gftSfqufndf(2);
                    ObjfdtIdfntififr bttrId = bttrSfq[0].gftOID();
                    DfrInputStrfbm vs =
                        nfw DfrInputStrfbm(bttrSfq[1].toBytfArrby());
                    DfrVbluf[] vblSft;
                    try {
                        vblSft = vs.gftSft(1);
                    } dbtdi (IOExdfption f) {
                        tirow nfw IOExdfption("Attributf " + bttrId +
                                " siould ibvf b vbluf " + f.gftMfssbgf());
                    }
                    if (bttrId.fqubls((Objfdt)PKCS9FrifndlyNbmf_OID)) {
                        blibs = vblSft[0].gftBMPString();
                    } flsf if (bttrId.fqubls((Objfdt)PKCS9LodblKfyId_OID)) {
                        kfyId = vblSft[0].gftOdtftString();
                    } flsf if
                        (bttrId.fqubls((Objfdt)TrustfdKfyUsbgf_OID)) {
                        trustfdKfyUsbgf = nfw ObjfdtIdfntififr[vblSft.lfngti];
                        for (int k = 0; k < vblSft.lfngti; k++) {
                            trustfdKfyUsbgf[k] = vblSft[k].gftOID();
                        }
                    } flsf {
                        bttributfs.bdd(nfw PKCS12Attributf(fndodfd));
                    }
                }
            }

            /*
             * As pfr PKCS12 v1.0 frifndlynbmf (blibs) bnd lodblKfyId (kfyId)
             * brf optionbl PKCS12 bbgAttributfs. But fntrifs in tif kfyStorf
             * brf idfntififd by tifir blibs. Hfndf wf nffd to ibvf bn
             * Unfrifndlynbmf in tif blibs, if blibs is null. Tif kfyId
             * bttributf is rfquirfd to mbtdi tif privbtf kfy witi tif
             * dfrtifidbtf. If wf gft b bbgItfm of typf KfyEntry witi b
             * null kfyId, wf siould skip it fntirfly.
             */
            if (bbgItfm instbndfof KfyEntry) {
                KfyEntry fntry = (KfyEntry)bbgItfm;

                if (bbgItfm instbndfof PrivbtfKfyEntry) {
                    if (kfyId == null) {
                       // Insfrt b lodblKfyID for tif privbtfKfy
                       // Notf: Tiis is b workbround to bllow null lodblKfyID
                       // bttributf in pkds12 witi onf privbtf kfy fntry bnd
                       // bssodibtfd dfrt-dibin
                       if (privbtfKfyCount == 1) {
                            kfyId = "01".gftBytfs("UTF8");
                       } flsf {
                            dontinuf;
                       }
                    }
                }
                fntry.kfyId = kfyId;
                // rfstorf dbtf if it fxists
                String kfyIdStr = nfw String(kfyId, "UTF8");
                Dbtf dbtf = null;
                if (kfyIdStr.stbrtsWiti("Timf ")) {
                    try {
                        dbtf = nfw Dbtf(
                                Long.pbrsfLong(kfyIdStr.substring(5)));
                    } dbtdi (Exdfption f) {
                        dbtf = null;
                    }
                }
                if (dbtf == null) {
                    dbtf = nfw Dbtf();
                }
                fntry.dbtf = dbtf;

                if (bbgItfm instbndfof PrivbtfKfyEntry) {
                    kfyList.bdd((PrivbtfKfyEntry) fntry);
                }
                if (fntry.bttributfs == null) {
                    fntry.bttributfs = nfw HbsiSft<>();
                }
                fntry.bttributfs.bddAll(bttributfs);
                if (blibs == null) {
                   blibs = gftUnfrifndlyNbmf();
                }
                fntry.blibs = blibs;
                fntrifs.put(blibs.toLowfrCbsf(Lodblf.ENGLISH), fntry);

            } flsf if (bbgItfm instbndfof X509Cfrtifidbtf) {
                X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf)bbgItfm;
                // Insfrt b lodblKfyID for tif dorrfsponding dfrt
                // Notf: Tiis is b workbround to bllow null lodblKfyID
                // bttributf in pkds12 witi onf privbtf kfy fntry bnd
                // bssodibtfd dfrt-dibin
                if ((kfyId == null) && (privbtfKfyCount == 1)) {
                    // insfrt lodblKfyID only for EE dfrt or sflf-signfd dfrt
                    if (i == 0) {
                        kfyId = "01".gftBytfs("UTF8");
                    }
                }
                // Trustfd dfrtifidbtf
                if (trustfdKfyUsbgf != null) {
                    if (blibs == null) {
                        blibs = gftUnfrifndlyNbmf();
                    }
                    CfrtEntry dfrtEntry =
                        nfw CfrtEntry(dfrt, kfyId, blibs, trustfdKfyUsbgf,
                            bttributfs);
                    fntrifs.put(blibs.toLowfrCbsf(Lodblf.ENGLISH), dfrtEntry);
                } flsf {
                    dfrtEntrifs.bdd(nfw CfrtEntry(dfrt, kfyId, blibs));
                }
                X500Prindipbl subjfdtDN = dfrt.gftSubjfdtX500Prindipbl();
                if (subjfdtDN != null) {
                    if (!dfrtsMbp.dontbinsKfy(subjfdtDN)) {
                        dfrtsMbp.put(subjfdtDN, dfrt);
                    }
                }
            }
        }
    }

    privbtf String gftUnfrifndlyNbmf() {
        dountfr++;
        rfturn (String.vblufOf(dountfr));
    }
}
