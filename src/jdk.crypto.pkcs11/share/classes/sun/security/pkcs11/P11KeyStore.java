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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.mbti.BigIntfgfr;

import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.UnsupportfdEndodingExdfption;

import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.Dbtf;
import jbvb.util.Enumfrbtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Sft;

import jbvb.sfdurity.*;
import jbvb.sfdurity.KfyStorf.*;

import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;

import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.intfrfbdfs.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import jbvbx.sfdurity.buti.login.LoginExdfption;
import jbvbx.sfdurity.buti.dbllbbdk.Cbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.PbsswordCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;
import jbvbx.sfdurity.buti.dbllbbdk.UnsupportfdCbllbbdkExdfption;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.ECUtil;

import sun.sfdurity.pkds11.Sfdmod.*;
import stbtid sun.sfdurity.pkds11.P11Util.*;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

import sun.sfdurity.rsb.RSAKfyFbdtory;

finbl dlbss P11KfyStorf fxtfnds KfyStorfSpi {

    privbtf stbtid finbl CK_ATTRIBUTE ATTR_CLASS_CERT =
                        nfw CK_ATTRIBUTE(CKA_CLASS, CKO_CERTIFICATE);
    privbtf stbtid finbl CK_ATTRIBUTE ATTR_CLASS_PKEY =
                        nfw CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY);
    privbtf stbtid finbl CK_ATTRIBUTE ATTR_CLASS_SKEY =
                        nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY);

    privbtf stbtid finbl CK_ATTRIBUTE ATTR_X509_CERT_TYPE =
                        nfw CK_ATTRIBUTE(CKA_CERTIFICATE_TYPE, CKC_X_509);

    privbtf stbtid finbl CK_ATTRIBUTE ATTR_TOKEN_TRUE =
                        nfw CK_ATTRIBUTE(CKA_TOKEN, truf);

    // XXX for tfsting purposfs only
    //  - NSS dofsn't support pfrsistfnt sfdrft kfys
    //    (kfy typf gfts mbnglfd if sfdrft kfy is b tokfn kfy)
    //  - if dfbug is turnfd on, tifn tiis is sft to fblsf
    privbtf stbtid CK_ATTRIBUTE ATTR_SKEY_TOKEN_TRUE = ATTR_TOKEN_TRUE;

    privbtf stbtid finbl CK_ATTRIBUTE ATTR_TRUSTED_TRUE =
                        nfw CK_ATTRIBUTE(CKA_TRUSTED, truf);
    privbtf stbtid finbl CK_ATTRIBUTE ATTR_PRIVATE_TRUE =
                        nfw CK_ATTRIBUTE(CKA_PRIVATE, truf);

    privbtf stbtid finbl long NO_HANDLE = -1;
    privbtf stbtid finbl long FINDOBJECTS_MAX = 100;
    privbtf stbtid finbl String ALIAS_SEP = "/";

    privbtf stbtid finbl boolfbn NSS_TEST = fblsf;
    privbtf stbtid finbl Dfbug dfbug =
                        Dfbug.gftInstbndf("pkds11kfystorf");
    privbtf stbtid boolfbn CKA_TRUSTED_SUPPORTED = truf;

    privbtf finbl Tokfn tokfn;

    // If multiplf dfrts brf found to sibrf tif sbmf CKA_LABEL
    // bt lobd timf (NSS-stylf kfystorf), tifn tif kfystorf is rfbd
    // bnd tif uniquf kfystorf blibsfs brf mbppfd to tif fntrifs.
    // Howfvfr, writf dbpbbilitifs brf disbblfd.
    privbtf boolfbn writfDisbblfd = fblsf;

    // Mbp of uniquf kfystorf blibsfs to fntrifs in tif tokfn
    privbtf HbsiMbp<String, AlibsInfo> blibsMbp;

    // wiftifr to usf NSS Sfdmod info for trust bttributfs
    privbtf finbl boolfbn usfSfdmodTrust;

    // if usfSfdmodTrust == truf, wiidi typf of trust wf brf intfrfstfd in
    privbtf Sfdmod.TrustTypf nssTrustTypf;

    /**
     * Tif undfrlying tokfn mby dontbin multiplf dfrts bflonging to tif
     * sbmf "pfrsonblity" (for fxbmplf, b signing dfrt bnd fndryption dfrt),
     * bll sibring tif sbmf CKA_LABEL.  Tifsf must bf rfsolvfd
     * into uniquf kfystorf blibsfs.
     *
     * In bddition, privbtf kfys bnd dfrts mby not ibvf b CKA_LABEL.
     * It is bssumfd tibt b privbtf kfy bnd dorrfsponding dfrtifidbtf
     * sibrf tif sbmf CKA_ID, bnd tibt tif CKA_ID is uniquf bdross tif tokfn.
     * Tif CKA_ID mby not bf iumbn-rfbdbblf.
     * Tifsf pbirs must bf rfsolvfd into uniquf kfystorf blibsfs.
     *
     * Furtifrmorf, sfdrft kfys brf bssumfd to ibvf b CKA_LABEL
     * uniquf bdross tif fntirf tokfn.
     *
     * Wifn tif KfyStorf is lobdfd, instbndfs of tiis dlbss brf
     * drfbtfd to rfprfsfnt tif privbtf kfys/sfdrft kfys/dfrts
     * tibt rfsidf on tif tokfn.
     */
    privbtf stbtid dlbss AlibsInfo {

        // CKA_CLASS - fntry typf
        privbtf CK_ATTRIBUTE typf = null;

        // CKA_LABEL of dfrt bnd sfdrft kfy
        privbtf String lbbfl = null;

        // CKA_ID of tif privbtf kfy/dfrt pbir
        privbtf bytf[] id = null;

        // CKA_TRUSTED - truf if dfrt is trustfd
        privbtf boolfbn trustfd = fblsf;

        // fitifr fnd-fntity dfrt or trustfd dfrt dfpfnding on 'typf'
        privbtf X509Cfrtifidbtf dfrt = null;

        // dibin
        privbtf X509Cfrtifidbtf dibin[] = null;

        // truf if CKA_ID for privbtf kfy bnd dfrt mbtdi up
        privbtf boolfbn mbtdifd = fblsf;

        // SfdrftKfyEntry
        publid AlibsInfo(String lbbfl) {
            tiis.typf = ATTR_CLASS_SKEY;
            tiis.lbbfl = lbbfl;
        }

        // PrivbtfKfyEntry
        publid AlibsInfo(String lbbfl,
                        bytf[] id,
                        boolfbn trustfd,
                        X509Cfrtifidbtf dfrt) {
            tiis.typf = ATTR_CLASS_PKEY;
            tiis.lbbfl = lbbfl;
            tiis.id = id;
            tiis.trustfd = trustfd;
            tiis.dfrt = dfrt;
        }

        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr();
            if (typf == ATTR_CLASS_PKEY) {
                sb.bppfnd("\ttypf=[privbtf kfy]\n");
            } flsf if (typf == ATTR_CLASS_SKEY) {
                sb.bppfnd("\ttypf=[sfdrft kfy]\n");
            } flsf if (typf == ATTR_CLASS_CERT) {
                sb.bppfnd("\ttypf=[trustfd dfrt]\n");
            }
            sb.bppfnd("\tlbbfl=[" + lbbfl + "]\n");
            if (id == null) {
                sb.bppfnd("\tid=[null]\n");
            } flsf {
                sb.bppfnd("\tid=" + P11KfyStorf.gftID(id) + "\n");
            }
            sb.bppfnd("\ttrustfd=[" + trustfd + "]\n");
            sb.bppfnd("\tmbtdifd=[" + mbtdifd + "]\n");
            if (dfrt == null) {
                sb.bppfnd("\tdfrt=[null]\n");
            } flsf {
                sb.bppfnd("\tdfrt=[\tsubjfdt: " +
                        dfrt.gftSubjfdtX500Prindipbl() +
                        "\n\t\tissufr: " +
                        dfrt.gftIssufrX500Prindipbl() +
                        "\n\t\tsfriblNum: " +
                        dfrt.gftSfriblNumbfr().toString() +
                        "]");
            }
            rfturn sb.toString();
        }
    }

    /**
     * dbllbbdk ibndlfr for pbssing pbssword to Providfr.login mftiod
     */
    privbtf stbtid dlbss PbsswordCbllbbdkHbndlfr implfmfnts CbllbbdkHbndlfr {

        privbtf dibr[] pbssword;

        privbtf PbsswordCbllbbdkHbndlfr(dibr[] pbssword) {
            if (pbssword != null) {
                tiis.pbssword = pbssword.dlonf();
            }
        }

        publid void ibndlf(Cbllbbdk[] dbllbbdks)
                tirows IOExdfption, UnsupportfdCbllbbdkExdfption {
            if (!(dbllbbdks[0] instbndfof PbsswordCbllbbdk)) {
                tirow nfw UnsupportfdCbllbbdkExdfption(dbllbbdks[0]);
            }
            PbsswordCbllbbdk pd = (PbsswordCbllbbdk)dbllbbdks[0];
            pd.sftPbssword(pbssword);  // tiis dlonfs tif pbssword if not null
        }

        protfdtfd void finblizf() tirows Tirowbblf {
            if (pbssword != null) {
                Arrbys.fill(pbssword, ' ');
            }
            supfr.finblizf();
        }
    }

    /**
     * gftTokfnObjfdt rfturn vbluf.
     *
     * if objfdt is not found, typf is sft to null.
     * otifrwisf, typf is sft to tif rfqufstfd typf.
     */
    privbtf stbtid dlbss THbndlf {
        privbtf finbl long ibndlf;              // tokfn objfdt ibndlf
        privbtf finbl CK_ATTRIBUTE typf;        // CKA_CLASS

        privbtf THbndlf(long ibndlf, CK_ATTRIBUTE typf) {
            tiis.ibndlf = ibndlf;
            tiis.typf = typf;
        }
    }

    P11KfyStorf(Tokfn tokfn) {
        tiis.tokfn = tokfn;
        tiis.usfSfdmodTrust = tokfn.providfr.nssUsfSfdmodTrust;
    }

    /**
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs.
     * Tif kfy must ibvf bffn bssodibtfd witi
     * tif blibs by b dbll to <dodf>sftKfyEntry</dodf>,
     * or by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>PrivbtfKfyEntry</dodf> or <dodf>SfdrftKfyEntry</dodf>.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm pbssword tif pbssword, wiidi must bf <dodf>null</dodf>
     *
     * @rfturn tif rfqufstfd kfy, or null if tif givfn blibs dofs not fxist
     * or dofs not idfntify b kfy-rflbtfd fntry.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     * kfy dbnnot bf found
     * @fxdfption UnrfdovfrbblfKfyExdfption if tif kfy dbnnot bf rfdovfrfd
     */
    publid syndironizfd Kfy fnginfGftKfy(String blibs, dibr[] pbssword)
                tirows NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption {

        tokfn.fnsurfVblid();
        if (pbssword != null && !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw NoSudiAlgoritimExdfption("pbssword must bf null");
        }

        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo == null || blibsInfo.typf == ATTR_CLASS_CERT) {
            rfturn null;
        }

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            if (blibsInfo.typf == ATTR_CLASS_PKEY) {
                THbndlf i = gftTokfnObjfdt(sfssion,
                                        blibsInfo.typf,
                                        blibsInfo.id,
                                        null);
                if (i.typf == ATTR_CLASS_PKEY) {
                    rfturn lobdPkfy(sfssion, i.ibndlf);
                }
            } flsf {
                THbndlf i = gftTokfnObjfdt(sfssion,
                                        ATTR_CLASS_SKEY,
                                        null,
                                        blibs);
                if (i.typf == ATTR_CLASS_SKEY) {
                    rfturn lobdSkfy(sfssion, i.ibndlf);
                }
            }

            // did not find bnytiing
            rfturn null;
        } dbtdi (PKCS11Exdfption | KfyStorfExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     * Tif dfrtifidbtf dibin must ibvf bffn bssodibtfd witi tif blibs
     * by b dbll to <dodf>sftKfyEntry</dodf>,
     * or by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>PrivbtfKfyEntry</dodf>.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * bnd tif root dfrtifidbtf butiority lbst), or null if tif givfn blibs
     * dofs not fxist or dofs not dontbin b dfrtifidbtf dibin
     */
    publid syndironizfd Cfrtifidbtf[] fnginfGftCfrtifidbtfCibin(String blibs) {

        tokfn.fnsurfVblid();

        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo == null || blibsInfo.typf != ATTR_CLASS_PKEY) {
            rfturn null;
        }
        rfturn blibsInfo.dibin;
    }

    /**
     * Rfturns tif dfrtifidbtf bssodibtfd witi tif givfn blibs.
     *
     * <p> If tif givfn blibs nbmf idfntififs bn fntry
     * drfbtfd by b dbll to <dodf>sftCfrtifidbtfEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>TrustfdCfrtifidbtfEntry</dodf>,
     * tifn tif trustfd dfrtifidbtf dontbinfd in tibt fntry is rfturnfd.
     *
     * <p> If tif givfn blibs nbmf idfntififs bn fntry
     * drfbtfd by b dbll to <dodf>sftKfyEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>PrivbtfKfyEntry</dodf>,
     * tifn tif first flfmfnt of tif dfrtifidbtf dibin in tibt fntry
     * (if b dibin fxists) is rfturnfd.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf, or null if tif givfn blibs dofs not fxist or
     * dofs not dontbin b dfrtifidbtf.
     */
    publid syndironizfd Cfrtifidbtf fnginfGftCfrtifidbtf(String blibs) {
        tokfn.fnsurfVblid();

        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo == null) {
            rfturn null;
        }
        rfturn blibsInfo.dfrt;
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
        tokfn.fnsurfVblid();
        tirow nfw ProvidfrExdfption(nfw UnsupportfdOpfrbtionExdfption());
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
                                   dibr[] pbssword,
                                   Cfrtifidbtf[] dibin)
                tirows KfyStorfExdfption {

        tokfn.fnsurfVblid();
        difdkWritf();

        if (!(kfy instbndfof PrivbtfKfy) && !(kfy instbndfof SfdrftKfy)) {
            tirow nfw KfyStorfExdfption("kfy must bf PrivbtfKfy or SfdrftKfy");
        } flsf if (kfy instbndfof PrivbtfKfy && dibin == null) {
            tirow nfw KfyStorfExdfption
                ("PrivbtfKfy must bf bddompbnifd by non-null dibin");
        } flsf if (kfy instbndfof SfdrftKfy && dibin != null) {
            tirow nfw KfyStorfExdfption
                ("SfdrftKfy must bf bddompbnifd by null dibin");
        } flsf if (pbssword != null &&
                    !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw KfyStorfExdfption("Pbssword must bf null");
        }

        KfyStorf.Entry fntry = null;
        try {
            if (kfy instbndfof PrivbtfKfy) {
                fntry = nfw KfyStorf.PrivbtfKfyEntry((PrivbtfKfy)kfy, dibin);
            } flsf if (kfy instbndfof SfdrftKfy) {
                fntry = nfw KfyStorf.SfdrftKfyEntry((SfdrftKfy)kfy);
            }
        } dbtdi (NullPointfrExdfption | IllfgblArgumfntExdfption f) {
            tirow nfw KfyStorfExdfption(f);
        }
        fnginfSftEntry(blibs, fntry, nfw KfyStorf.PbsswordProtfdtion(pbssword));
    }

    /**
     * Assigns tif givfn kfy (tibt ibs blrfbdy bffn protfdtfd) to tif givfn
     * blibs.
     *
     * <p>If tif protfdtfd kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>,
     * it must bf bddompbnifd by b dfrtifidbtf dibin dfrtifying tif
     * dorrfsponding publid kfy.
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
    publid void fnginfSftKfyEntry(String blibs, bytf[] kfy, Cfrtifidbtf[] dibin)
                tirows KfyStorfExdfption {
        tokfn.fnsurfVblid();
        tirow nfw ProvidfrExdfption(nfw UnsupportfdOpfrbtionExdfption());
    }

    /**
     * Assigns tif givfn dfrtifidbtf to tif givfn blibs.
     *
     * <p> If tif givfn blibs idfntififs bn fxisting fntry
     * drfbtfd by b dbll to <dodf>sftCfrtifidbtfEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>TrustfdCfrtifidbtfEntry</dodf>,
     * tif trustfd dfrtifidbtf in tif fxisting fntry
     * is ovfrriddfn by tif givfn dfrtifidbtf.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm dfrt tif dfrtifidbtf
     *
     * @fxdfption KfyStorfExdfption if tif givfn blibs blrfbdy fxists bnd dofs
     * not idfntify bn fntry dontbining b trustfd dfrtifidbtf,
     * or tiis opfrbtion fbils for somf otifr rfbson.
     */
    publid syndironizfd void fnginfSftCfrtifidbtfEntry
        (String blibs, Cfrtifidbtf dfrt) tirows KfyStorfExdfption {

        tokfn.fnsurfVblid();
        difdkWritf();

        if (dfrt == null) {
            tirow nfw KfyStorfExdfption("invblid null dfrtifidbtf");
        }

        KfyStorf.Entry fntry = null;
        fntry = nfw KfyStorf.TrustfdCfrtifidbtfEntry(dfrt);
        fnginfSftEntry(blibs, fntry, null);
    }

    /**
     * Dflftfs tif fntry idfntififd by tif givfn blibs from tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @fxdfption KfyStorfExdfption if tif fntry dbnnot bf rfmovfd.
     */
    publid syndironizfd void fnginfDflftfEntry(String blibs)
                tirows KfyStorfExdfption {
        tokfn.fnsurfVblid();

        if (tokfn.isWritfProtfdtfd()) {
            tirow nfw KfyStorfExdfption("tokfn writf-protfdtfd");
        }
        difdkWritf();
        dflftfEntry(blibs);
    }

    /**
     * XXX - not surf wiftifr to kffp tiis
     */
    privbtf boolfbn dflftfEntry(String blibs) tirows KfyStorfExdfption {
        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo != null) {

            blibsMbp.rfmovf(blibs);

            try {
                if (blibsInfo.typf == ATTR_CLASS_CERT) {
                    // trustfd dfrtifidbtf fntry
                    rfturn dfstroyCfrt(blibsInfo.id);
                } flsf if (blibsInfo.typf == ATTR_CLASS_PKEY) {
                    // privbtf kfy fntry
                    rfturn dfstroyPkfy(blibsInfo.id) &&
                                dfstroyCibin(blibsInfo.id);
                } flsf if (blibsInfo.typf == ATTR_CLASS_SKEY) {
                    // sfdrft kfy fntry
                    rfturn dfstroySkfy(blibs);
                } flsf {
                    tirow nfw KfyStorfExdfption("unfxpfdtfd fntry typf");
                }
            } dbtdi (PKCS11Exdfption | CfrtifidbtfExdfption f) {
                tirow nfw KfyStorfExdfption(f);
            }
        }
        rfturn fblsf;
    }

    /**
     * Lists bll tif blibs nbmfs of tiis kfystorf.
     *
     * @rfturn fnumfrbtion of tif blibs nbmfs
     */
    publid syndironizfd Enumfrbtion<String> fnginfAlibsfs() {
        tokfn.fnsurfVblid();

        // don't wbnt rfturnfd fnumfrbtion to itfrbtf off bdtubl kfySft -
        // otifrwisf bpplidbtions tibt itfrbtf bnd modify tif kfystorf
        // mby run into dondurrfnt modifidbtion problfms
        rfturn Collfdtions.fnumfrbtion(nfw HbsiSft<String>(blibsMbp.kfySft()));
    }

    /**
     * Cifdks if tif givfn blibs fxists in tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn truf if tif blibs fxists, fblsf otifrwisf
     */
    publid syndironizfd boolfbn fnginfContbinsAlibs(String blibs) {
        tokfn.fnsurfVblid();
        rfturn blibsMbp.dontbinsKfy(blibs);
    }

    /**
     * Rftrifvfs tif numbfr of fntrifs in tiis kfystorf.
     *
     * @rfturn tif numbfr of fntrifs in tiis kfystorf
     */
    publid syndironizfd int fnginfSizf() {
        tokfn.fnsurfVblid();
        rfturn blibsMbp.sizf();
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs
     * wbs drfbtfd by b dbll to <dodf>sftKfyEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>PrivbtfKfyEntry</dodf> or b <dodf>SfdrftKfyEntry</dodf>.
     *
     * @pbrbm blibs tif blibs for tif kfystorf fntry to bf difdkfd
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * kfy-rflbtfd, fblsf otifrwisf.
     */
    publid syndironizfd boolfbn fnginfIsKfyEntry(String blibs) {
        tokfn.fnsurfVblid();

        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo == null || blibsInfo.typf == ATTR_CLASS_CERT) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs
     * wbs drfbtfd by b dbll to <dodf>sftCfrtifidbtfEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>TrustfdCfrtifidbtfEntry</dodf>.
     *
     * @pbrbm blibs tif blibs for tif kfystorf fntry to bf difdkfd
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs dontbins b
     * trustfd dfrtifidbtf, fblsf otifrwisf.
     */
    publid syndironizfd boolfbn fnginfIsCfrtifidbtfEntry(String blibs) {
        tokfn.fnsurfVblid();

        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo == null || blibsInfo.typf != ATTR_CLASS_CERT) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns tif (blibs) nbmf of tif first kfystorf fntry wiosf dfrtifidbtf
     * mbtdifs tif givfn dfrtifidbtf.
     *
     * <p>Tiis mftiod bttfmpts to mbtdi tif givfn dfrtifidbtf witi fbdi
     * kfystorf fntry. If tif fntry bfing donsidfrfd wbs
     * drfbtfd by b dbll to <dodf>sftCfrtifidbtfEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>TrustfdCfrtifidbtfEntry</dodf>,
     * tifn tif givfn dfrtifidbtf is dompbrfd to tibt fntry's dfrtifidbtf.
     *
     * <p> If tif fntry bfing donsidfrfd wbs
     * drfbtfd by b dbll to <dodf>sftKfyEntry</dodf>,
     * or drfbtfd by b dbll to <dodf>sftEntry</dodf> witi b
     * <dodf>PrivbtfKfyEntry</dodf>,
     * tifn tif givfn dfrtifidbtf is dompbrfd to tif first
     * flfmfnt of tibt fntry's dfrtifidbtf dibin.
     *
     * @pbrbm dfrt tif dfrtifidbtf to mbtdi witi.
     *
     * @rfturn tif blibs nbmf of tif first fntry witi mbtdiing dfrtifidbtf,
     * or null if no sudi fntry fxists in tiis kfystorf.
     */
    publid syndironizfd String fnginfGftCfrtifidbtfAlibs(Cfrtifidbtf dfrt) {
        tokfn.fnsurfVblid();
        Enumfrbtion<String> f = fnginfAlibsfs();
        wiilf (f.ibsMorfElfmfnts()) {
            String blibs = f.nfxtElfmfnt();
            Cfrtifidbtf tokfnCfrt = fnginfGftCfrtifidbtf(blibs);
            if (tokfnCfrt != null && tokfnCfrt.fqubls(dfrt)) {
                rfturn blibs;
            }
        }
        rfturn null;
    }

    /**
     * fnginfStorf durrfntly is b No-op.
     * Entrifs brf storfd to tif tokfn during fnginfSftEntry
     *
     * @pbrbm strfbm tiis must bf <dodf>null</dodf>
     * @pbrbm pbssword tiis must bf <dodf>null</dodf>
     */
    publid syndironizfd void fnginfStorf(OutputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption {
        tokfn.fnsurfVblid();
        if (strfbm != null && !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw IOExdfption("output strfbm must bf null");
        }

        if (pbssword != null && !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw IOExdfption("pbssword must bf null");
        }
    }

    /**
     * fnginfStorf durrfntly is b No-op.
     * Entrifs brf storfd to tif tokfn during fnginfSftEntry
     *
     * @pbrbm pbrbm tiis must bf <dodf>null</dodf>
     *
     * @fxdfption IllfgblArgumfntExdfption if tif givfn
     *          <dodf>KfyStorf.LobdStorfPbrbmftfr</dodf>
     *          input is not <dodf>null</dodf>
     */
    publid syndironizfd void fnginfStorf(KfyStorf.LobdStorfPbrbmftfr pbrbm)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption {
        tokfn.fnsurfVblid();
        if (pbrbm != null) {
            tirow nfw IllfgblArgumfntExdfption
                ("LobdStorfPbrbmftfr must bf null");
        }
    }

    /**
     * Lobds tif kfystorf.
     *
     * @pbrbm strfbm tif input strfbm, wiidi must bf <dodf>null</dodf>
     * @pbrbm pbssword tif pbssword usfd to unlodk tif kfystorf,
     *          or <dodf>null</dodf> if tif tokfn supports b
     *          CKF_PROTECTED_AUTHENTICATION_PATH
     *
     * @fxdfption IOExdfption if tif givfn <dodf>strfbm</dodf> is not
     *          <dodf>null</dodf>, if tif tokfn supports b
     *          CKF_PROTECTED_AUTHENTICATION_PATH bnd b non-null
     *          pbssword is givfn, of if tif tokfn login opfrbtion fbilfd
     */
    publid syndironizfd void fnginfLobd(InputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption {

        tokfn.fnsurfVblid();

        if (NSS_TEST) {
            ATTR_SKEY_TOKEN_TRUE = nfw CK_ATTRIBUTE(CKA_TOKEN, fblsf);
        }

        if (strfbm != null && !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw IOExdfption("input strfbm must bf null");
        }

        if (usfSfdmodTrust) {
            nssTrustTypf = Sfdmod.TrustTypf.ALL;
        }

        try {
            if (pbssword == null) {
                login(null);
            } flsf {
                login(nfw PbsswordCbllbbdkHbndlfr(pbssword));
            }
            if (mbpLbbfls() == truf) {
                // CKA_LABELs brf sibrfd by multiplf dfrts
                writfDisbblfd = truf;
            }
            if (dfbug != null) {
                dumpTokfnMbp();
            }
        } dbtdi (LoginExdfption | KfyStorfExdfption | PKCS11Exdfption f) {
            tirow nfw IOExdfption("lobd fbilfd", f);
        }
    }

    /**
     * Lobds tif kfystorf using tif givfn
     * <dodf>KfyStorf.LobdStorfPbrbmftfr</dodf>.
     *
     * <p> Tif <dodf>LobdStorfPbrbmftfr.gftProtfdtionPbrbmftfr()</dodf>
     * mftiod is fxpfdtfd to rfturn b <dodf>KfyStorf.PbsswordProtfdtion</dodf>
     * objfdt.  Tif pbssword is rftrifvfd from tibt objfdt bnd usfd
     * to unlodk tif PKCS#11 tokfn.
     *
     * <p> If tif tokfn supports b CKF_PROTECTED_AUTHENTICATION_PATH
     * tifn tif providfd pbssword must bf <dodf>null</dodf>.
     *
     * @pbrbm pbrbm tif <dodf>KfyStorf.LobdStorfPbrbmftfr</dodf>
     *
     * @fxdfption IllfgblArgumfntExdfption if tif givfn
     *          <dodf>KfyStorf.LobdStorfPbrbmftfr</dodf> is <dodf>null</dodf>,
     *          or if tibt pbrbmftfr rfturns b <dodf>null</dodf>
     *          <dodf>ProtfdtionPbrbmftfr</dodf> objfdt.
     *          input is not rfdognizfd
     * @fxdfption IOExdfption if tif tokfn supports b
     *          CKF_PROTECTED_AUTHENTICATION_PATH bnd tif providfd pbssword
     *          is non-null, or if tif tokfn login opfrbtion fbils
     */
    publid syndironizfd void fnginfLobd(KfyStorf.LobdStorfPbrbmftfr pbrbm)
                tirows IOExdfption, NoSudiAlgoritimExdfption,
                CfrtifidbtfExdfption {

        tokfn.fnsurfVblid();

        if (NSS_TEST) {
            ATTR_SKEY_TOKEN_TRUE = nfw CK_ATTRIBUTE(CKA_TOKEN, fblsf);
        }

        // if dbllfr wbnts to pbss b NULL pbssword,
        // fordf it to pbss b non-NULL PbsswordProtfdtion tibt rfturns
        // b NULL pbssword

        if (pbrbm == null) {
            tirow nfw IllfgblArgumfntExdfption
                        ("invblid null LobdStorfPbrbmftfr");
        }
        if (usfSfdmodTrust) {
            if (pbrbm instbndfof Sfdmod.KfyStorfLobdPbrbmftfr) {
                nssTrustTypf = ((Sfdmod.KfyStorfLobdPbrbmftfr)pbrbm).gftTrustTypf();
            } flsf {
                nssTrustTypf = Sfdmod.TrustTypf.ALL;
            }
        }

        CbllbbdkHbndlfr ibndlfr;
        KfyStorf.ProtfdtionPbrbmftfr pp = pbrbm.gftProtfdtionPbrbmftfr();
        if (pp instbndfof PbsswordProtfdtion) {
            dibr[] pbssword = ((PbsswordProtfdtion)pp).gftPbssword();
            if (pbssword == null) {
                ibndlfr = null;
            } flsf {
                ibndlfr = nfw PbsswordCbllbbdkHbndlfr(pbssword);
            }
        } flsf if (pp instbndfof CbllbbdkHbndlfrProtfdtion) {
            ibndlfr = ((CbllbbdkHbndlfrProtfdtion)pp).gftCbllbbdkHbndlfr();
        } flsf {
            tirow nfw IllfgblArgumfntExdfption
                        ("ProtfdtionPbrbmftfr must bf fitifr " +
                        "PbsswordProtfdtion or CbllbbdkHbndlfrProtfdtion");
        }

        try {
            login(ibndlfr);
            if (mbpLbbfls() == truf) {
                // CKA_LABELs brf sibrfd by multiplf dfrts
                writfDisbblfd = truf;
            }
            if (dfbug != null) {
                dumpTokfnMbp();
            }
        } dbtdi (LoginExdfption | KfyStorfExdfption | PKCS11Exdfption f) {
            tirow nfw IOExdfption("lobd fbilfd", f);
        }
    }

    privbtf void login(CbllbbdkHbndlfr ibndlfr) tirows LoginExdfption {
        if ((tokfn.tokfnInfo.flbgs & CKF_PROTECTED_AUTHENTICATION_PATH) == 0) {
            tokfn.providfr.login(null, ibndlfr);
        } flsf {
            // tokfn supports protfdtfd butifntidbtion pbti
            // (fxtfrnbl pin-pbd, for fxbmplf)
            if (ibndlfr != null &&
                !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
                tirow nfw LoginExdfption("dbn not spfdify pbssword if tokfn " +
                                "supports protfdtfd butifntidbtion pbti");
            }

            // must rfly on bpplidbtion-sft or dffbult ibndlfr
            // if onf is nfdfssbry
            tokfn.providfr.login(null, null);
        }
    }

    /**
     * Gft b <dodf>KfyStorf.Entry</dodf> for tif spfdififd blibs
     *
     * @pbrbm blibs gft tif <dodf>KfyStorf.Entry</dodf> for tiis blibs
     * @pbrbm protPbrbm tiis must bf <dodf>null</dodf>
     *
     * @rfturn tif <dodf>KfyStorf.Entry</dodf> for tif spfdififd blibs,
     *          or <dodf>null</dodf> if tifrf is no sudi fntry
     *
     * @fxdfption KfyStorfExdfption if tif opfrbtion fbilfd
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     *          fntry dbnnot bf found
     * @fxdfption UnrfdovfrbblfEntryExdfption if tif spfdififd
     *          <dodf>protPbrbm</dodf> wfrf insuffidifnt or invblid
     *
     * @sindf 1.5
     */
    publid syndironizfd KfyStorf.Entry fnginfGftEntry(String blibs,
                        KfyStorf.ProtfdtionPbrbmftfr protPbrbm)
                tirows KfyStorfExdfption, NoSudiAlgoritimExdfption,
                UnrfdovfrbblfEntryExdfption {

        tokfn.fnsurfVblid();

        if (protPbrbm != null &&
            protPbrbm instbndfof KfyStorf.PbsswordProtfdtion &&
            ((KfyStorf.PbsswordProtfdtion)protPbrbm).gftPbssword() != null &&
            !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw KfyStorfExdfption("ProtfdtionPbrbmftfr must bf null");
        }

        AlibsInfo blibsInfo = blibsMbp.gft(blibs);
        if (blibsInfo == null) {
            if (dfbug != null) {
                dfbug.println("fnginfGftEntry did not find blibs [" +
                        blibs +
                        "] in mbp");
            }
            rfturn null;
        }

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            if (blibsInfo.typf == ATTR_CLASS_CERT) {
                // trustfd dfrtifidbtf fntry
                if (dfbug != null) {
                    dfbug.println("fnginfGftEntry found trustfd dfrt fntry");
                }
                rfturn nfw KfyStorf.TrustfdCfrtifidbtfEntry(blibsInfo.dfrt);
            } flsf if (blibsInfo.typf == ATTR_CLASS_SKEY) {
                // sfdrft kfy fntry
                if (dfbug != null) {
                    dfbug.println("fnginfGftEntry found sfdrft kfy fntry");
                }

                THbndlf i = gftTokfnObjfdt
                        (sfssion, ATTR_CLASS_SKEY, null, blibsInfo.lbbfl);
                if (i.typf != ATTR_CLASS_SKEY) {
                    tirow nfw KfyStorfExdfption
                        ("fxpfdtfd but dould not find sfdrft kfy");
                } flsf {
                    SfdrftKfy skfy = lobdSkfy(sfssion, i.ibndlf);
                    rfturn nfw KfyStorf.SfdrftKfyEntry(skfy);
                }
            } flsf {
                // privbtf kfy fntry
                if (dfbug != null) {
                    dfbug.println("fnginfGftEntry found privbtf kfy fntry");
                }

                THbndlf i = gftTokfnObjfdt
                        (sfssion, ATTR_CLASS_PKEY, blibsInfo.id, null);
                if (i.typf != ATTR_CLASS_PKEY) {
                    tirow nfw KfyStorfExdfption
                        ("fxpfdtfd but dould not find privbtf kfy");
                } flsf {
                    PrivbtfKfy pkfy = lobdPkfy(sfssion, i.ibndlf);
                    Cfrtifidbtf[] dibin = blibsInfo.dibin;
                    if ((pkfy != null) && (dibin != null)) {
                        rfturn nfw KfyStorf.PrivbtfKfyEntry(pkfy, dibin);
                    } flsf {
                        if (dfbug != null) {
                            dfbug.println
                                ("fnginfGftEntry got null dfrt dibin or privbtf kfy");
                        }
                    }
                }
            }
            rfturn null;
        } dbtdi (PKCS11Exdfption pf) {
            tirow nfw KfyStorfExdfption(pf);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * Sbvf b <dodf>KfyStorf.Entry</dodf> undfr tif spfdififd blibs.
     *
     * <p> If bn fntry blrfbdy fxists for tif spfdififd blibs,
     * it is ovfrriddfn.
     *
     * <p> Tiis KfyStorf implfmfntbtion only supports tif stbndbrd
     * fntry typfs, bnd only supports X509Cfrtifidbtfs in
     * TrustfdCfrtifidbtfEntrifs.  Also, tiis implfmfntbtion dofs not support
     * protfdting fntrifs using b difffrfnt pbssword
     * from tif onf usfd for tokfn login.
     *
     * <p> Entrifs brf immfdibtfly storfd on tif tokfn.
     *
     * @pbrbm blibs sbvf tif <dodf>KfyStorf.Entry</dodf> undfr tiis blibs
     * @pbrbm fntry tif <dodf>Entry</dodf> to sbvf
     * @pbrbm protPbrbm tiis must bf <dodf>null</dodf>
     *
     * @fxdfption KfyStorfExdfption if tiis opfrbtion fbils
     *
     * @sindf 1.5
     */
    publid syndironizfd void fnginfSftEntry(String blibs, KfyStorf.Entry fntry,
                        KfyStorf.ProtfdtionPbrbmftfr protPbrbm)
                tirows KfyStorfExdfption {

        tokfn.fnsurfVblid();
        difdkWritf();

        if (protPbrbm != null &&
            protPbrbm instbndfof KfyStorf.PbsswordProtfdtion &&
            ((KfyStorf.PbsswordProtfdtion)protPbrbm).gftPbssword() != null &&
            !tokfn.donfig.gftKfyStorfCompbtibilityModf()) {
            tirow nfw KfyStorfExdfption(nfw UnsupportfdOpfrbtionExdfption
                                ("ProtfdtionPbrbmftfr must bf null"));
        }

        if (tokfn.isWritfProtfdtfd()) {
            tirow nfw KfyStorfExdfption("tokfn writf-protfdtfd");
        }

        if (fntry instbndfof KfyStorf.TrustfdCfrtifidbtfEntry) {

            if (usfSfdmodTrust == fblsf) {
                // PKCS #11 dofs not bllow bpp to modify trustfd dfrts -
                tirow nfw KfyStorfExdfption(nfw UnsupportfdOpfrbtionExdfption
                                    ("trustfd dfrtifidbtfs mby only bf sft by " +
                                    "tokfn initiblizbtion bpplidbtion"));
            }
            Modulf modulf = tokfn.providfr.nssModulf;
            if ((modulf.typf != ModulfTypf.KEYSTORE) && (modulf.typf != ModulfTypf.FIPS)) {
                // XXX bllow TRUSTANCHOR modulf
                tirow nfw KfyStorfExdfption("Trustfd dfrtifidbtfs dbn only bf "
                    + "bddfd to tif NSS KfyStorf modulf");
            }
            Cfrtifidbtf dfrt = ((TrustfdCfrtifidbtfEntry)fntry).gftTrustfdCfrtifidbtf();
            if (dfrt instbndfof X509Cfrtifidbtf == fblsf) {
                tirow nfw KfyStorfExdfption("Cfrtifidbtf must bf bn X509Cfrtifidbtf");
            }
            X509Cfrtifidbtf xdfrt = (X509Cfrtifidbtf)dfrt;
            AlibsInfo info = blibsMbp.gft(blibs);
            if (info != null) {
                // XXX try to updbtf
                dflftfEntry(blibs);
            }
            try {
                storfCfrt(blibs, xdfrt);
                modulf.sftTrust(tokfn, xdfrt);
                mbpLbbfls();
            } dbtdi (PKCS11Exdfption | CfrtifidbtfExdfption f) {
                tirow nfw KfyStorfExdfption(f);
            }

        } flsf {

            if (fntry instbndfof KfyStorf.PrivbtfKfyEntry) {

                PrivbtfKfy kfy =
                        ((KfyStorf.PrivbtfKfyEntry)fntry).gftPrivbtfKfy();
                if (!(kfy instbndfof P11Kfy) &&
                    !(kfy instbndfof RSAPrivbtfKfy) &&
                    !(kfy instbndfof DSAPrivbtfKfy) &&
                    !(kfy instbndfof DHPrivbtfKfy) &&
                    !(kfy instbndfof ECPrivbtfKfy)) {
                    tirow nfw KfyStorfExdfption("unsupportfd kfy typf: " +
                                                kfy.gftClbss().gftNbmf());
                }

                // only support X509Cfrtifidbtf dibins
                Cfrtifidbtf[] dibin =
                    ((KfyStorf.PrivbtfKfyEntry)fntry).gftCfrtifidbtfCibin();
                if (!(dibin instbndfof X509Cfrtifidbtf[])) {
                    tirow nfw KfyStorfExdfption
                        (nfw UnsupportfdOpfrbtionExdfption
                                ("unsupportfd dfrtifidbtf brrby typf: " +
                                dibin.gftClbss().gftNbmf()));
                }

                try {
                    boolfbn updbtfdAlibs = fblsf;
                    Sft<String> blibsfs = blibsMbp.kfySft();
                    for (String oldAlibs : blibsfs) {

                        // sff if tifrf's bn fxisting fntry witi tif sbmf info

                        AlibsInfo blibsInfo = blibsMbp.gft(oldAlibs);
                        if (blibsInfo.typf == ATTR_CLASS_PKEY &&
                            blibsInfo.dfrt.gftPublidKfy().fqubls
                                        (dibin[0].gftPublidKfy())) {

                            // found fxisting fntry -
                            // dbllfr is rfnbming fntry or updbting dfrt dibin
                            //
                            // sft nfw CKA_LABEL/CKA_ID
                            // bnd updbtf dfrts if nfdfssbry

                            updbtfPkfy(blibs,
                                        blibsInfo.id,
                                        (X509Cfrtifidbtf[])dibin,
                                        !blibsInfo.dfrt.fqubls(dibin[0]));
                            updbtfdAlibs = truf;
                            brfbk;
                        }
                    }

                    if (!updbtfdAlibs) {
                        // dbllfr bdding nfw fntry
                        fnginfDflftfEntry(blibs);
                        storfPkfy(blibs, (KfyStorf.PrivbtfKfyEntry)fntry);
                    }

                } dbtdi (PKCS11Exdfption | CfrtifidbtfExdfption pf) {
                    tirow nfw KfyStorfExdfption(pf);
                }

            } flsf if (fntry instbndfof KfyStorf.SfdrftKfyEntry) {

                KfyStorf.SfdrftKfyEntry skf = (KfyStorf.SfdrftKfyEntry)fntry;
                SfdrftKfy skfy = skf.gftSfdrftKfy();

                try {
                    // first difdk if tif kfy blrfbdy fxists
                    AlibsInfo blibsInfo = blibsMbp.gft(blibs);

                    if (blibsInfo != null) {
                        fnginfDflftfEntry(blibs);
                    }
                    storfSkfy(blibs, skf);

                } dbtdi (PKCS11Exdfption pf) {
                    tirow nfw KfyStorfExdfption(pf);
                }

            } flsf {
                tirow nfw KfyStorfExdfption(nfw UnsupportfdOpfrbtionExdfption
                    ("unsupportfd fntry typf: " + fntry.gftClbss().gftNbmf()));
            }

            try {

                // XXX  NSS dofs not writf out tif CKA_ID wf pbss to tifm
                //
                // tifrfforf wf must rf-mbp lbbfls
                // (dbn not simply updbtf blibsMbp)

                mbpLbbfls();
                if (dfbug != null) {
                    dumpTokfnMbp();
                }
            } dbtdi (PKCS11Exdfption | CfrtifidbtfExdfption pf) {
                tirow nfw KfyStorfExdfption(pf);
            }
        }

        if (dfbug != null) {
            dfbug.println
                ("fnginfSftEntry bddfd nfw fntry for [" +
                blibs +
                "] to tokfn");
        }
    }

    /**
     * Dftfrminfs if tif kfystorf <dodf>Entry</dodf> for tif spfdififd
     * <dodf>blibs</dodf> is bn instbndf or subdlbss of tif spfdififd
     * <dodf>fntryClbss</dodf>.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm fntryClbss tif fntry dlbss
     *
     * @rfturn truf if tif kfystorf <dodf>Entry</dodf> for tif spfdififd
     *          <dodf>blibs</dodf> is bn instbndf or subdlbss of tif
     *          spfdififd <dodf>fntryClbss</dodf>, fblsf otifrwisf
     */
    publid syndironizfd boolfbn fnginfEntryInstbndfOf
                (String blibs, Clbss<? fxtfnds KfyStorf.Entry> fntryClbss) {
        tokfn.fnsurfVblid();
        rfturn supfr.fnginfEntryInstbndfOf(blibs, fntryClbss);
    }

    privbtf X509Cfrtifidbtf lobdCfrt(Sfssion sfssion, long oHbndlf)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[]
                        { nfw CK_ATTRIBUTE(CKA_VALUE) };
        tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);

        bytf[] bytfs = bttrs[0].gftBytfArrby();
        if (bytfs == null) {
            tirow nfw CfrtifidbtfExdfption
                        ("unfxpfdtfdly rftrifvfd null bytf brrby");
        }
        CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        rfturn (X509Cfrtifidbtf)df.gfnfrbtfCfrtifidbtf
                        (nfw BytfArrbyInputStrfbm(bytfs));
    }

    privbtf X509Cfrtifidbtf[] lobdCibin(Sfssion sfssion,
                                        X509Cfrtifidbtf fndCfrt)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        ArrbyList<X509Cfrtifidbtf> lCibin = null;

        if (fndCfrt.gftSubjfdtX500Prindipbl().fqubls
            (fndCfrt.gftIssufrX500Prindipbl())) {
            // sflf signfd
            rfturn nfw X509Cfrtifidbtf[] { fndCfrt };
        } flsf {
            lCibin = nfw ArrbyList<X509Cfrtifidbtf>();
            lCibin.bdd(fndCfrt);
        }

        // try lobding rfmbining dfrts in dibin by following
        // issufr->subjfdt links

        X509Cfrtifidbtf nfxt = fndCfrt;
        wiilf (truf) {
            CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                        ATTR_TOKEN_TRUE,
                        ATTR_CLASS_CERT,
                        nfw CK_ATTRIBUTE(CKA_SUBJECT,
                                nfxt.gftIssufrX500Prindipbl().gftEndodfd()) };
            long[] di = findObjfdts(sfssion, bttrs);

            if (di == null || di.lfngti == 0) {
                // donf
                brfbk;
            } flsf {
                // if morf tibn onf found, usf first
                if (dfbug != null && di.lfngti > 1) {
                    dfbug.println("fnginfGftEntry found " +
                                di.lfngti +
                                " dfrtifidbtf fntrifs for subjfdt [" +
                                nfxt.gftIssufrX500Prindipbl().toString() +
                                "] in tokfn - using first fntry");
                }

                nfxt = lobdCfrt(sfssion, di[0]);
                lCibin.bdd(nfxt);
                if (nfxt.gftSubjfdtX500Prindipbl().fqubls
                    (nfxt.gftIssufrX500Prindipbl())) {
                    // sflf signfd
                    brfbk;
                }
            }
        }

        rfturn lCibin.toArrby(nfw X509Cfrtifidbtf[lCibin.sizf()]);
    }

    privbtf SfdrftKfy lobdSkfy(Sfssion sfssion, long oHbndlf)
                tirows PKCS11Exdfption {

        CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                        nfw CK_ATTRIBUTE(CKA_KEY_TYPE) };
        tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
        long kTypf = bttrs[0].gftLong();

        String kfyTypf = null;
        int kfyLfngti = -1;

        // XXX NSS mbnglfs tif storfd kfy typf for sfdrft kfy tokfn objfdts

        if (kTypf == CKK_DES || kTypf == CKK_DES3) {
            if (kTypf == CKK_DES) {
                kfyTypf = "DES";
                kfyLfngti = 64;
            } flsf if (kTypf == CKK_DES3) {
                kfyTypf = "DESfdf";
                kfyLfngti = 192;
            }
        } flsf {
            if (kTypf == CKK_AES) {
                kfyTypf = "AES";
            } flsf if (kTypf == CKK_BLOWFISH) {
                kfyTypf = "Blowfisi";
            } flsf if (kTypf == CKK_RC4) {
                kfyTypf = "ARCFOUR";
            } flsf {
                if (dfbug != null) {
                    dfbug.println("unknown kfy typf [" +
                                kTypf +
                                "] - using 'Gfnfrid Sfdrft'");
                }
                kfyTypf = "Gfnfrid Sfdrft";
            }

            // XXX NSS problfm CKR_ATTRIBUTE_TYPE_INVALID?
            if (NSS_TEST) {
                kfyLfngti = 128;
            } flsf {
                bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_VALUE_LEN) };
                tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
                kfyLfngti = (int)bttrs[0].gftLong();
            }
        }

        rfturn P11Kfy.sfdrftKfy(sfssion, oHbndlf, kfyTypf, kfyLfngti, null);
    }

    privbtf PrivbtfKfy lobdPkfy(Sfssion sfssion, long oHbndlf)
        tirows PKCS11Exdfption, KfyStorfExdfption {

        CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                        nfw CK_ATTRIBUTE(CKA_KEY_TYPE) };
        tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
        long kTypf = bttrs[0].gftLong();
        String kfyTypf = null;
        int kfyLfngti = 0;

        if (kTypf == CKK_RSA) {

            kfyTypf = "RSA";

            bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_MODULUS) };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
            BigIntfgfr modulus = bttrs[0].gftBigIntfgfr();
            kfyLfngti = modulus.bitLfngti();

            // Tiis difdk will dombinf our "don't dbrf" vblufs ifrf
            // witi tif systfm-widf min/mbx vblufs.
            try {
                RSAKfyFbdtory.difdkKfyLfngtis(kfyLfngti, null,
                    -1, Intfgfr.MAX_VALUE);
            } dbtdi (InvblidKfyExdfption f) {
                tirow nfw KfyStorfExdfption(f.gftMfssbgf());
            }

            rfturn P11Kfy.privbtfKfy(sfssion,
                                oHbndlf,
                                kfyTypf,
                                kfyLfngti,
                                null);

        } flsf if (kTypf == CKK_DSA) {

            kfyTypf = "DSA";

            bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_PRIME) };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
            BigIntfgfr primf = bttrs[0].gftBigIntfgfr();
            kfyLfngti = primf.bitLfngti();

            rfturn P11Kfy.privbtfKfy(sfssion,
                                oHbndlf,
                                kfyTypf,
                                kfyLfngti,
                                null);

        } flsf if (kTypf == CKK_DH) {

            kfyTypf = "DH";

            bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_PRIME) };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
            BigIntfgfr primf = bttrs[0].gftBigIntfgfr();
            kfyLfngti = primf.bitLfngti();

            rfturn P11Kfy.privbtfKfy(sfssion,
                                oHbndlf,
                                kfyTypf,
                                kfyLfngti,
                                null);

        } flsf if (kTypf == CKK_EC) {

            bttrs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), oHbndlf, bttrs);
            bytf[] fndodfdPbrbms = bttrs[0].gftBytfArrby();
            try {
                ECPbrbmftfrSpfd pbrbms =
                    ECUtil.gftECPbrbmftfrSpfd(null, fndodfdPbrbms);
                kfyLfngti = pbrbms.gftCurvf().gftFifld().gftFifldSizf();
            } dbtdi (IOExdfption f) {
                // wf do not wbnt to bddfpt kfy witi unsupportfd pbrbmftfrs
                tirow nfw KfyStorfExdfption("Unsupportfd pbrbmftfrs", f);
            }

            rfturn P11Kfy.privbtfKfy(sfssion, oHbndlf, "EC", kfyLfngti, null);

        } flsf {
            if (dfbug != null) {
                dfbug.println("unknown kfy typf [" + kTypf + "]");
            }
            tirow nfw KfyStorfExdfption("unknown kfy typf");
        }
    }


    /**
     * XXX  On ibutton, wifn you C_SftAttributf(CKA_ID) for b privbtf kfy
     *      it not only dibngfs tif CKA_ID of tif privbtf kfy,
     *      it dibngfs tif CKA_ID of tif dorrfsponding dfrt too.
     *      And vidf vfrsb.
     *
     * XXX  On ibutton, CKR_DEVICE_ERROR if you C_SftAttributf(CKA_ID)
     *      for b privbtf kfy, bnd tifn try to dflftf tif dorrfsponding dfrt.
     *      So tiis dodf rfvfrsfs tif ordfr.
     *      Aftfr tif dfrt is first dfstroyfd (if nfdfssbry),
     *      tifn tif CKA_ID of tif privbtf kfy dbn bf dibngfd suddfssfully.
     *
     * @pbrbm rfplbdfCfrt if truf, tifn dbllfr is updbting blibs info for
     *                  fxisting dfrt (only updbtf CKA_ID/CKA_LABEL).
     *                  if fblsf, tifn dbllfr is updbting dfrt dibin
     *                  (dflftf old fnd dfrt bnd bdd nfw dibin).
     */
    privbtf void updbtfPkfy(String blibs,
                        bytf[] dkb_id,
                        X509Cfrtifidbtf[] dibin,
                        boolfbn rfplbdfCfrt) tirows
                KfyStorfExdfption, CfrtifidbtfExdfption, PKCS11Exdfption {

        // XXX
        //
        // blwbys sft rfplbdfCfrt to truf
        //
        // NSS dofs not bllow rfsftting of CKA_LABEL on bn fxisting dfrt
        // (C_SftAttributf dbll suddffds, but is ignorfd)

        rfplbdfCfrt = truf;

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            // first gft privbtf kfy objfdt ibndlf bnd ibng onto it

            THbndlf i = gftTokfnObjfdt(sfssion, ATTR_CLASS_PKEY, dkb_id, null);
            long pKfyHbndlf;
            if (i.typf == ATTR_CLASS_PKEY) {
                pKfyHbndlf = i.ibndlf;
            } flsf {
                tirow nfw KfyStorfExdfption
                        ("fxpfdtfd but dould not find privbtf kfy " +
                        "witi CKA_ID " +
                        gftID(dkb_id));
            }

            // nfxt find fxisting fnd fntity dfrt

            i = gftTokfnObjfdt(sfssion, ATTR_CLASS_CERT, dkb_id, null);
            if (i.typf != ATTR_CLASS_CERT) {
                tirow nfw KfyStorfExdfption
                        ("fxpfdtfd but dould not find dfrtifidbtf " +
                        "witi CKA_ID " +
                        gftID(dkb_id));
            } flsf {
                if (rfplbdfCfrt) {
                    // rfplbding fxisting dfrt bnd dibin
                    dfstroyCibin(dkb_id);
                } flsf {
                    // rfnbming blibs for fxisting dfrt
                    CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                        nfw CK_ATTRIBUTE(CKA_LABEL, blibs),
                        nfw CK_ATTRIBUTE(CKA_ID, blibs) };
                    tokfn.p11.C_SftAttributfVbluf
                        (sfssion.id(), i.ibndlf, bttrs);
                }
            }

            // bdd nfw dibin

            if (rfplbdfCfrt) {
                // bdd bll dfrts in dibin
                storfCibin(blibs, dibin);
            } flsf {
                // blrfbdy updbtfd blibs info for fxisting fnd dfrt -
                // just updbtf CA dfrts
                storfCbCfrts(dibin, 1);
            }

            // finblly updbtf CKA_ID for privbtf kfy
            //
            // ibutton mby ibvf blrfbdy donf tiis (tibt is ok)

            CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                                nfw CK_ATTRIBUTE(CKA_ID, blibs) };
            tokfn.p11.C_SftAttributfVbluf(sfssion.id(), pKfyHbndlf, bttrs);

            if (dfbug != null) {
                dfbug.println("updbtfPkfy sft nfw blibs [" +
                                blibs +
                                "] for privbtf kfy fntry");
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    privbtf void updbtfP11Pkfy(String blibs, CK_ATTRIBUTE bttributf, P11Kfy kfy)
                tirows PKCS11Exdfption {

        // if tokfn kfy, updbtf blibs.
        // if sfssion kfy, donvfrt to tokfn kfy.

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            if (kfy.tokfnObjfdt == truf) {

                // tokfn kfy - sft nfw CKA_ID

                CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                                nfw CK_ATTRIBUTE(CKA_ID, blibs) };
                tokfn.p11.C_SftAttributfVbluf
                                (sfssion.id(), kfy.kfyID, bttrs);
                if (dfbug != null) {
                    dfbug.println("updbtfP11Pkfy sft nfw blibs [" +
                                blibs +
                                "] for kfy fntry");
                }
            } flsf {

                // sfssion kfy - donvfrt to tokfn kfy bnd sft CKA_ID

                CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                    ATTR_TOKEN_TRUE,
                    nfw CK_ATTRIBUTE(CKA_ID, blibs),
                };
                if (bttributf != null) {
                    bttrs = bddAttributf(bttrs, bttributf);
                }
                tokfn.p11.C_CopyObjfdt(sfssion.id(), kfy.kfyID, bttrs);
                if (dfbug != null) {
                    dfbug.println("updbtfP11Pkfy dopifd privbtf sfssion kfy " +
                                "for [" +
                                blibs +
                                "] to tokfn fntry");
                }
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    privbtf void storfCfrt(String blibs, X509Cfrtifidbtf dfrt)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        ArrbyList<CK_ATTRIBUTE> bttrList = nfw ArrbyList<CK_ATTRIBUTE>();
        bttrList.bdd(ATTR_TOKEN_TRUE);
        bttrList.bdd(ATTR_CLASS_CERT);
        bttrList.bdd(ATTR_X509_CERT_TYPE);
        bttrList.bdd(nfw CK_ATTRIBUTE(CKA_SUBJECT,
                                dfrt.gftSubjfdtX500Prindipbl().gftEndodfd()));
        bttrList.bdd(nfw CK_ATTRIBUTE(CKA_ISSUER,
                                dfrt.gftIssufrX500Prindipbl().gftEndodfd()));
        bttrList.bdd(nfw CK_ATTRIBUTE(CKA_SERIAL_NUMBER,
                                dfrt.gftSfriblNumbfr().toBytfArrby()));
        bttrList.bdd(nfw CK_ATTRIBUTE(CKA_VALUE, dfrt.gftEndodfd()));

        if (blibs != null) {
            bttrList.bdd(nfw CK_ATTRIBUTE(CKA_LABEL, blibs));
            bttrList.bdd(nfw CK_ATTRIBUTE(CKA_ID, blibs));
        } flsf {
            // ibutton rfquirfs somftiing to bf sft
            // - blibs must bf uniquf
            bttrList.bdd(nfw CK_ATTRIBUTE(CKA_ID,
                        gftID(dfrt.gftSubjfdtX500Prindipbl().gftNbmf
                                        (X500Prindipbl.CANONICAL), dfrt)));
        }

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            tokfn.p11.C_CrfbtfObjfdt(sfssion.id(),
                        bttrList.toArrby(nfw CK_ATTRIBUTE[bttrList.sizf()]));
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    privbtf void storfCibin(String blibs, X509Cfrtifidbtf[] dibin)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        // bdd nfw dibin
        //
        // fnd dfrt ibs CKA_LABEL bnd CKA_ID sft to blibs.
        // otifr dfrts in dibin ibvf nfitifr sft.

        storfCfrt(blibs, dibin[0]);
        storfCbCfrts(dibin, 1);
    }

    privbtf void storfCbCfrts(X509Cfrtifidbtf[] dibin, int stbrt)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        // do not bdd duplidbtf CA dfrt if blrfbdy in tokfn
        //
        // XXX   ibutton storfs duplidbtf CA dfrts, NSS dofs not

        Sfssion sfssion = null;
        HbsiSft<X509Cfrtifidbtf> dbdfrts = nfw HbsiSft<X509Cfrtifidbtf>();
        try {
            sfssion = tokfn.gftOpSfssion();
            CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                        ATTR_TOKEN_TRUE,
                        ATTR_CLASS_CERT };
            long[] ibndlfs = findObjfdts(sfssion, bttrs);

            // lobd dfrts durrfntly on tif tokfn
            for (long ibndlf : ibndlfs) {
                dbdfrts.bdd(lobdCfrt(sfssion, ibndlf));
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }

        for (int i = stbrt; i < dibin.lfngti; i++) {
            if (!dbdfrts.dontbins(dibin[i])) {
                storfCfrt(null, dibin[i]);
            } flsf if (dfbug != null) {
                dfbug.println("ignoring duplidbtf CA dfrt for [" +
                        dibin[i].gftSubjfdtX500Prindipbl() +
                        "]");
            }
        }
    }

    privbtf void storfSkfy(String blibs, KfyStorf.SfdrftKfyEntry skf)
                tirows PKCS11Exdfption, KfyStorfExdfption {

        SfdrftKfy skfy = skf.gftSfdrftKfy();
        // No nffd to spfdify CKA_CLASS, CKA_KEY_TYPE, CKA_VALUE sindf
        // tify brf ibndlfd in P11SfdrftKfyFbdtory.drfbtfKfy() mftiod.
        CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
            ATTR_SKEY_TOKEN_TRUE,
            ATTR_PRIVATE_TRUE,
            nfw CK_ATTRIBUTE(CKA_LABEL, blibs),
        };
        try {
            P11SfdrftKfyFbdtory.donvfrtKfy(tokfn, skfy, null, bttrs);
        } dbtdi (InvblidKfyExdfption ikf) {
            // rf-tirow KfyStorfExdfption to mbtdi jbvbdod
            tirow nfw KfyStorfExdfption("Cbnnot donvfrt to PKCS11 kfys", ikf);
        }

        // updbtf globbl blibs mbp
        blibsMbp.put(blibs, nfw AlibsInfo(blibs));

        if (dfbug != null) {
            dfbug.println("storfSkfy drfbtfd tokfn sfdrft kfy for [" +
                          blibs + "]");
        }
    }

    privbtf stbtid CK_ATTRIBUTE[] bddAttributf(CK_ATTRIBUTE[] bttrs, CK_ATTRIBUTE bttr) {
        int n = bttrs.lfngti;
        CK_ATTRIBUTE[] nfwAttrs = nfw CK_ATTRIBUTE[n + 1];
        Systfm.brrbydopy(bttrs, 0, nfwAttrs, 0, n);
        nfwAttrs[n] = bttr;
        rfturn nfwAttrs;
    }

    privbtf void storfPkfy(String blibs, KfyStorf.PrivbtfKfyEntry pkf)
        tirows PKCS11Exdfption, CfrtifidbtfExdfption, KfyStorfExdfption  {

        PrivbtfKfy kfy = pkf.gftPrivbtfKfy();
        CK_ATTRIBUTE[] bttrs = null;

        // If tif kfy is b tokfn objfdt on tiis tokfn, updbtf it instfbd
        // of drfbting b duplidbtf kfy objfdt.
        // Otifrwisf, trfbt b P11Kfy likf bny otifr kfy, if is is fxtrbdtbblf.
        if (kfy instbndfof P11Kfy) {
            P11Kfy p11Kfy = (P11Kfy)kfy;
            if (p11Kfy.tokfnObjfdt && (p11Kfy.tokfn == tiis.tokfn)) {
                updbtfP11Pkfy(blibs, null, p11Kfy);
                storfCibin(blibs, (X509Cfrtifidbtf[])pkf.gftCfrtifidbtfCibin());
                rfturn;
            }
        }

        boolfbn usfNDB = tokfn.donfig.gftNssNftsdbpfDbWorkbround();
        PublidKfy publidKfy = pkf.gftCfrtifidbtf().gftPublidKfy();

        if (kfy instbndfof RSAPrivbtfKfy) {

            X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf)pkf.gftCfrtifidbtf();
            bttrs = gftRsbPrivKfyAttrs
                (blibs, (RSAPrivbtfKfy)kfy, dfrt.gftSubjfdtX500Prindipbl());

        } flsf if (kfy instbndfof DSAPrivbtfKfy) {

            DSAPrivbtfKfy dsbKfy = (DSAPrivbtfKfy)kfy;

            CK_ATTRIBUTE[] idAttrs = gftIdAttributfs(kfy, publidKfy, fblsf, usfNDB);
            if (idAttrs[0] == null) {
                idAttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, blibs);
            }

            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_PKEY,
                ATTR_PRIVATE_TRUE,
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_DSA),
                idAttrs[0],
                nfw CK_ATTRIBUTE(CKA_PRIME, dsbKfy.gftPbrbms().gftP()),
                nfw CK_ATTRIBUTE(CKA_SUBPRIME, dsbKfy.gftPbrbms().gftQ()),
                nfw CK_ATTRIBUTE(CKA_BASE, dsbKfy.gftPbrbms().gftG()),
                nfw CK_ATTRIBUTE(CKA_VALUE, dsbKfy.gftX()),
            };
            if (idAttrs[1] != null) {
                bttrs = bddAttributf(bttrs, idAttrs[1]);
            }

            bttrs = tokfn.gftAttributfs
                (TfmplbtfMbnbgfr.O_IMPORT, CKO_PRIVATE_KEY, CKK_DSA, bttrs);

            if (dfbug != null) {
                dfbug.println("storfPkfy drfbtfd DSA tfmplbtf");
            }

        } flsf if (kfy instbndfof DHPrivbtfKfy) {

            DHPrivbtfKfy diKfy = (DHPrivbtfKfy)kfy;

            CK_ATTRIBUTE[] idAttrs = gftIdAttributfs(kfy, publidKfy, fblsf, usfNDB);
            if (idAttrs[0] == null) {
                idAttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, blibs);
            }

            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_PKEY,
                ATTR_PRIVATE_TRUE,
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_DH),
                idAttrs[0],
                nfw CK_ATTRIBUTE(CKA_PRIME, diKfy.gftPbrbms().gftP()),
                nfw CK_ATTRIBUTE(CKA_BASE, diKfy.gftPbrbms().gftG()),
                nfw CK_ATTRIBUTE(CKA_VALUE, diKfy.gftX()),
            };
            if (idAttrs[1] != null) {
                bttrs = bddAttributf(bttrs, idAttrs[1]);
            }

            bttrs = tokfn.gftAttributfs
                (TfmplbtfMbnbgfr.O_IMPORT, CKO_PRIVATE_KEY, CKK_DH, bttrs);

        } flsf if (kfy instbndfof ECPrivbtfKfy) {

            ECPrivbtfKfy fdKfy = (ECPrivbtfKfy)kfy;

            CK_ATTRIBUTE[] idAttrs = gftIdAttributfs(kfy, publidKfy, fblsf, usfNDB);
            if (idAttrs[0] == null) {
                idAttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, blibs);
            }

            bytf[] fndodfdPbrbms =
                ECUtil.fndodfECPbrbmftfrSpfd(null, fdKfy.gftPbrbms());
            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_PKEY,
                ATTR_PRIVATE_TRUE,
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_EC),
                idAttrs[0],
                nfw CK_ATTRIBUTE(CKA_VALUE, fdKfy.gftS()),
                nfw CK_ATTRIBUTE(CKA_EC_PARAMS, fndodfdPbrbms),
            };
            if (idAttrs[1] != null) {
                bttrs = bddAttributf(bttrs, idAttrs[1]);
            }

            bttrs = tokfn.gftAttributfs
                (TfmplbtfMbnbgfr.O_IMPORT, CKO_PRIVATE_KEY, CKK_EC, bttrs);

            if (dfbug != null) {
                dfbug.println("storfPkfy drfbtfd EC tfmplbtf");
            }

        } flsf if (kfy instbndfof P11Kfy) {
            // sfnsitivf/non-fxtrbdtbblf P11Kfy
            P11Kfy p11Kfy = (P11Kfy)kfy;
            if (p11Kfy.tokfn != tiis.tokfn) {
                tirow nfw KfyStorfExdfption
                    ("Cbnnot movf sfnsitivf kfys bdross tokfns");
            }
            CK_ATTRIBUTE nftsdbpfDB = null;
            if (usfNDB) {
                // Notf tibt tiis durrfntly fbils duf to bn NSS bug.
                // Tify do not bllow tif CKA_NETSCAPE_DB bttributf to bf
                // spfdififd during C_CopyObjfdt() bnd fbil witi
                // CKR_ATTRIBUTE_READ_ONLY.
                // But if wf did not spfdify it, tify would fbil witi
                // CKA_TEMPLATE_INCOMPLETE, so lfbvf tiis dodf in ifrf.
                CK_ATTRIBUTE[] idAttrs = gftIdAttributfs(kfy, publidKfy, fblsf, truf);
                nftsdbpfDB = idAttrs[1];
            }
            // Updbtf tif kfy objfdt.
            updbtfP11Pkfy(blibs, nftsdbpfDB, p11Kfy);
            storfCibin(blibs, (X509Cfrtifidbtf[])pkf.gftCfrtifidbtfCibin());
            rfturn;

        } flsf {
            tirow nfw KfyStorfExdfption("unsupportfd kfy typf: " + kfy);
        }

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            // drfbtf privbtf kfy fntry
            tokfn.p11.C_CrfbtfObjfdt(sfssion.id(), bttrs);
            if (dfbug != null) {
                dfbug.println("storfPkfy drfbtfd tokfn kfy for [" +
                                blibs +
                                "]");
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }

        storfCibin(blibs, (X509Cfrtifidbtf[])pkf.gftCfrtifidbtfCibin());
    }

    privbtf CK_ATTRIBUTE[] gftRsbPrivKfyAttrs(String blibs,
                                RSAPrivbtfKfy kfy,
                                X500Prindipbl subjfdt) tirows PKCS11Exdfption {

        // subjfdt is durrfntly ignorfd - dould bf usfd to sft CKA_SUBJECT

        CK_ATTRIBUTE[] bttrs = null;
        if (kfy instbndfof RSAPrivbtfCrtKfy) {

            if (dfbug != null) {
                dfbug.println("drfbting RSAPrivbtfCrtKfy bttrs");
            }

            RSAPrivbtfCrtKfy rsbKfy = (RSAPrivbtfCrtKfy)kfy;

            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_PKEY,
                ATTR_PRIVATE_TRUE,
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_RSA),
                nfw CK_ATTRIBUTE(CKA_ID, blibs),
                nfw CK_ATTRIBUTE(CKA_MODULUS,
                                rsbKfy.gftModulus()),
                nfw CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT,
                                rsbKfy.gftPrivbtfExponfnt()),
                nfw CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT,
                                rsbKfy.gftPublidExponfnt()),
                nfw CK_ATTRIBUTE(CKA_PRIME_1,
                                rsbKfy.gftPrimfP()),
                nfw CK_ATTRIBUTE(CKA_PRIME_2,
                                rsbKfy.gftPrimfQ()),
                nfw CK_ATTRIBUTE(CKA_EXPONENT_1,
                                rsbKfy.gftPrimfExponfntP()),
                nfw CK_ATTRIBUTE(CKA_EXPONENT_2,
                                rsbKfy.gftPrimfExponfntQ()),
                nfw CK_ATTRIBUTE(CKA_COEFFICIENT,
                                rsbKfy.gftCrtCofffidifnt()) };
            bttrs = tokfn.gftAttributfs
                (TfmplbtfMbnbgfr.O_IMPORT, CKO_PRIVATE_KEY, CKK_RSA, bttrs);

        } flsf {

            if (dfbug != null) {
                dfbug.println("drfbting RSAPrivbtfKfy bttrs");
            }

            RSAPrivbtfKfy rsbKfy = kfy;

            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_PKEY,
                ATTR_PRIVATE_TRUE,
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_RSA),
                nfw CK_ATTRIBUTE(CKA_ID, blibs),
                nfw CK_ATTRIBUTE(CKA_MODULUS,
                                rsbKfy.gftModulus()),
                nfw CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT,
                                rsbKfy.gftPrivbtfExponfnt()) };
            bttrs = tokfn.gftAttributfs
                (TfmplbtfMbnbgfr.O_IMPORT, CKO_PRIVATE_KEY, CKK_RSA, bttrs);
        }

        rfturn bttrs;
    }

    /**
     * Computf tif CKA_ID bnd/or CKA_NETSCAPE_DB bttributfs tibt siould bf
     * usfd for tiis privbtf kfy. It usfs tif sbmf blgoritim to dbldulbtf tif
     * vblufs bs NSS. Tif publid bnd privbtf kfys MUST mbtdi for tif rfsult to
     * bf dorrfdt.
     *
     * It rfturns b 2 flfmfnt brrby witi CKA_ID bt indfx 0 bnd CKA_NETSCAPE_DB
     * bt indfx 1. Tif boolfbn flbgs dftfrminf wibt is to bf dbldulbtfd.
     * If fblsf or if wf dould not dbldulbtf tif vbluf, tibt flfmfnt is null.
     *
     * NOTE tibt wf durrfntly do not usf tif CKA_ID vbluf dbldulbtfd by tiis
     * mftiod.
     */
    privbtf CK_ATTRIBUTE[] gftIdAttributfs(PrivbtfKfy privbtfKfy,
            PublidKfy publidKfy, boolfbn id, boolfbn nftsdbpfDb) {
        CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[2];
        if ((id || nftsdbpfDb) == fblsf) {
            rfturn bttrs;
        }
        String blg = privbtfKfy.gftAlgoritim();
        if (id && blg.fqubls("RSA") && (publidKfy instbndfof RSAPublidKfy)) {
            // CKA_NETSCAPE_DB not nffdfd for RSA publid kfys
            BigIntfgfr n = ((RSAPublidKfy)publidKfy).gftModulus();
            bttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, sib1(gftMbgnitudf(n)));
        } flsf if (blg.fqubls("DSA") && (publidKfy instbndfof DSAPublidKfy)) {
            BigIntfgfr y = ((DSAPublidKfy)publidKfy).gftY();
            if (id) {
                bttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, sib1(gftMbgnitudf(y)));
            }
            if (nftsdbpfDb) {
                bttrs[1] = nfw CK_ATTRIBUTE(CKA_NETSCAPE_DB, y);
            }
        } flsf if (blg.fqubls("DH") && (publidKfy instbndfof DHPublidKfy)) {
            BigIntfgfr y = ((DHPublidKfy)publidKfy).gftY();
            if (id) {
                bttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, sib1(gftMbgnitudf(y)));
            }
            if (nftsdbpfDb) {
                bttrs[1] = nfw CK_ATTRIBUTE(CKA_NETSCAPE_DB, y);
            }
        } flsf if (blg.fqubls("EC") && (publidKfy instbndfof ECPublidKfy)) {
            ECPublidKfy fdPub = (ECPublidKfy)publidKfy;
            ECPoint point = fdPub.gftW();
            ECPbrbmftfrSpfd pbrbms = fdPub.gftPbrbms();
            bytf[] fndodfdPoint = ECUtil.fndodfPoint(point, pbrbms.gftCurvf());
            if (id) {
                bttrs[0] = nfw CK_ATTRIBUTE(CKA_ID, sib1(fndodfdPoint));
            }
            if (nftsdbpfDb) {
                bttrs[1] = nfw CK_ATTRIBUTE(CKA_NETSCAPE_DB, fndodfdPoint);
            }
        } flsf {
            tirow nfw RuntimfExdfption("Unknown kfy blgoritim " + blg);
        }
        rfturn bttrs;
    }

    /**
     * rfturn truf if dfrt dfstroyfd
     */
    privbtf boolfbn dfstroyCfrt(bytf[] dkb_id)
                tirows PKCS11Exdfption, KfyStorfExdfption {
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            THbndlf i = gftTokfnObjfdt(sfssion, ATTR_CLASS_CERT, dkb_id, null);
            if (i.typf != ATTR_CLASS_CERT) {
                rfturn fblsf;
            }

            tokfn.p11.C_DfstroyObjfdt(sfssion.id(), i.ibndlf);
            if (dfbug != null) {
                dfbug.println("dfstroyCfrt dfstroyfd dfrt witi CKA_ID [" +
                                                gftID(dkb_id) +
                                                "]");
            }
            rfturn truf;
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * rfturn truf if dibin dfstroyfd
     */
    privbtf boolfbn dfstroyCibin(bytf[] dkb_id)
        tirows PKCS11Exdfption, CfrtifidbtfExdfption, KfyStorfExdfption {

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            THbndlf i = gftTokfnObjfdt(sfssion, ATTR_CLASS_CERT, dkb_id, null);
            if (i.typf != ATTR_CLASS_CERT) {
                if (dfbug != null) {
                    dfbug.println("dfstroyCibin dould not find " +
                        "fnd fntity dfrt witi CKA_ID [0x" +
                        Fundtions.toHfxString(dkb_id) +
                        "]");
                }
                rfturn fblsf;
            }

            X509Cfrtifidbtf fndCfrt = lobdCfrt(sfssion, i.ibndlf);
            tokfn.p11.C_DfstroyObjfdt(sfssion.id(), i.ibndlf);
            if (dfbug != null) {
                dfbug.println("dfstroyCibin dfstroyfd fnd fntity dfrt " +
                        "witi CKA_ID [" +
                        gftID(dkb_id) +
                        "]");
            }

            // build dibin following issufr->subjfdt links

            X509Cfrtifidbtf nfxt = fndCfrt;
            wiilf (truf) {

                if (nfxt.gftSubjfdtX500Prindipbl().fqubls
                    (nfxt.gftIssufrX500Prindipbl())) {
                    // sflf signfd - donf
                    brfbk;
                }

                CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                        ATTR_TOKEN_TRUE,
                        ATTR_CLASS_CERT,
                        nfw CK_ATTRIBUTE(CKA_SUBJECT,
                                  nfxt.gftIssufrX500Prindipbl().gftEndodfd()) };
                long[] di = findObjfdts(sfssion, bttrs);

                if (di == null || di.lfngti == 0) {
                    // donf
                    brfbk;
                } flsf {
                    // if morf tibn onf found, usf first
                    if (dfbug != null && di.lfngti > 1) {
                        dfbug.println("dfstroyCibin found " +
                                di.lfngti +
                                " dfrtifidbtf fntrifs for subjfdt [" +
                                nfxt.gftIssufrX500Prindipbl() +
                                "] in tokfn - using first fntry");
                    }

                    nfxt = lobdCfrt(sfssion, di[0]);

                    // only dflftf if not pbrt of bny otifr dibin

                    bttrs = nfw CK_ATTRIBUTE[] {
                        ATTR_TOKEN_TRUE,
                        ATTR_CLASS_CERT,
                        nfw CK_ATTRIBUTE(CKA_ISSUER,
                                nfxt.gftSubjfdtX500Prindipbl().gftEndodfd()) };
                    long[] issufrs = findObjfdts(sfssion, bttrs);

                    boolfbn dfstroyIt = fblsf;
                    if (issufrs == null || issufrs.lfngti == 0) {
                        // no otifr dfrts witi tiis issufr -
                        // dfstroy it
                        dfstroyIt = truf;
                    } flsf if (issufrs.lfngti == 1) {
                        X509Cfrtifidbtf iCfrt = lobdCfrt(sfssion, issufrs[0]);
                        if (nfxt.fqubls(iCfrt)) {
                            // only dfrt witi issufr is itsflf (sflf-signfd) -
                            // dfstroy it
                            dfstroyIt = truf;
                        }
                    }

                    if (dfstroyIt) {
                        tokfn.p11.C_DfstroyObjfdt(sfssion.id(), di[0]);
                        if (dfbug != null) {
                            dfbug.println
                                ("dfstroyCibin dfstroyfd dfrt in dibin " +
                                "witi subjfdt [" +
                                nfxt.gftSubjfdtX500Prindipbl() + "]");
                        }
                    } flsf {
                        if (dfbug != null) {
                            dfbug.println("dfstroyCibin did not dfstroy " +
                                "sibrfd dfrt in dibin witi subjfdt [" +
                                nfxt.gftSubjfdtX500Prindipbl() + "]");
                        }
                    }
                }
            }

            rfturn truf;

        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * rfturn truf if sfdrft kfy dfstroyfd
     */
    privbtf boolfbn dfstroySkfy(String blibs)
                tirows PKCS11Exdfption, KfyStorfExdfption {
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            THbndlf i = gftTokfnObjfdt(sfssion, ATTR_CLASS_SKEY, null, blibs);
            if (i.typf != ATTR_CLASS_SKEY) {
                if (dfbug != null) {
                    dfbug.println("dfstroySkfy did not find sfdrft kfy " +
                        "witi CKA_LABEL [" +
                        blibs +
                        "]");
                }
                rfturn fblsf;
            }
            tokfn.p11.C_DfstroyObjfdt(sfssion.id(), i.ibndlf);
            rfturn truf;
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * rfturn truf if privbtf kfy dfstroyfd
     */
    privbtf boolfbn dfstroyPkfy(bytf[] dkb_id)
                tirows PKCS11Exdfption, KfyStorfExdfption {
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            THbndlf i = gftTokfnObjfdt(sfssion, ATTR_CLASS_PKEY, dkb_id, null);
            if (i.typf != ATTR_CLASS_PKEY) {
                if (dfbug != null) {
                    dfbug.println
                        ("dfstroyPkfy did not find privbtf kfy witi CKA_ID [" +
                        gftID(dkb_id) +
                        "]");
                }
                rfturn fblsf;
            }
            tokfn.p11.C_DfstroyObjfdt(sfssion.id(), i.ibndlf);
            rfturn truf;
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * build [blibs + issufr + sfriblNumbfr] string from b dfrt
     */
    privbtf String gftID(String blibs, X509Cfrtifidbtf dfrt) {
        X500Prindipbl issufr = dfrt.gftIssufrX500Prindipbl();
        BigIntfgfr sfriblNum = dfrt.gftSfriblNumbfr();

        rfturn blibs +
                ALIAS_SEP +
                issufr.gftNbmf(X500Prindipbl.CANONICAL) +
                ALIAS_SEP +
                sfriblNum.toString();
    }

    /**
     * build CKA_ID string from bytfs
     */
    privbtf stbtid String gftID(bytf[] bytfs) {
        boolfbn printbblf = truf;
        for (int i = 0; i < bytfs.lfngti; i++) {
            if (!DfrVbluf.isPrintbblfStringCibr((dibr)bytfs[i])) {
                printbblf = fblsf;
                brfbk;
            }
        }

        if (!printbblf) {
            rfturn "0x" + Fundtions.toHfxString(bytfs);
        } flsf {
            try {
                rfturn nfw String(bytfs, "UTF-8");
            } dbtdi (UnsupportfdEndodingExdfption uff) {
                rfturn "0x" + Fundtions.toHfxString(bytfs);
            }
        }
    }

    /**
     * find bn objfdt on tif tokfn
     *
     * @pbrbm typf fitifr ATTR_CLASS_CERT, ATTR_CLASS_PKEY, or ATTR_CLASS_SKEY
     * @pbrbm dkb_id tif CKA_ID if typf is ATTR_CLASS_CERT or ATTR_CLASS_PKEY
     * @pbrbm dkb_lbbfl tif CKA_LABEL if typf is ATTR_CLASS_SKEY
     */
    privbtf THbndlf gftTokfnObjfdt(Sfssion sfssion,
                                CK_ATTRIBUTE typf,
                                bytf[] dkb_id,
                                String dkb_lbbfl)
                tirows PKCS11Exdfption, KfyStorfExdfption {

        CK_ATTRIBUTE[] bttrs;
        if (typf == ATTR_CLASS_SKEY) {
            bttrs = nfw CK_ATTRIBUTE[] {
                        ATTR_SKEY_TOKEN_TRUE,
                        nfw CK_ATTRIBUTE(CKA_LABEL, dkb_lbbfl),
                        typf };
        } flsf {
            bttrs = nfw CK_ATTRIBUTE[] {
                        ATTR_TOKEN_TRUE,
                        nfw CK_ATTRIBUTE(CKA_ID, dkb_id),
                        typf };
        }
        long[] i = findObjfdts(sfssion, bttrs);
        if (i.lfngti == 0) {
            if (dfbug != null) {
                if (typf == ATTR_CLASS_SKEY) {
                    dfbug.println("gftTokfnObjfdt did not find sfdrft kfy " +
                                "witi CKA_LABEL [" +
                                dkb_lbbfl +
                                "]");
                } flsf if (typf == ATTR_CLASS_CERT) {
                    dfbug.println
                        ("gftTokfnObjfdt did not find dfrt witi CKA_ID [" +
                        gftID(dkb_id) +
                        "]");
                } flsf {
                    dfbug.println("gftTokfnObjfdt did not find privbtf kfy " +
                        "witi CKA_ID [" +
                        gftID(dkb_id) +
                        "]");
                }
            }
        } flsf if (i.lfngti == 1) {

            // found objfdt ibndlf - rfturn it
            rfturn nfw THbndlf(i[0], typf);

        } flsf {

            // found multiplf objfdt ibndlfs -
            // sff if tokfn ignorfd CKA_LABEL during sfbrdi (f.g. NSS)

            if (typf == ATTR_CLASS_SKEY) {

                ArrbyList<THbndlf> list = nfw ArrbyList<THbndlf>(i.lfngti);
                for (int i = 0; i < i.lfngti; i++) {

                    CK_ATTRIBUTE[] lbbfl = nfw CK_ATTRIBUTE[]
                                        { nfw CK_ATTRIBUTE(CKA_LABEL) };
                    tokfn.p11.C_GftAttributfVbluf(sfssion.id(), i[i], lbbfl);
                    if (lbbfl[0].pVbluf != null &&
                        dkb_lbbfl.fqubls(nfw String(lbbfl[0].gftCibrArrby()))) {
                        list.bdd(nfw THbndlf(i[i], ATTR_CLASS_SKEY));
                    }
                }
                if (list.sizf() == 1) {
                    // yfs, tifrf wbs only onf CKA_LABEL tibt mbtdifd
                    rfturn list.gft(0);
                } flsf {
                    tirow nfw KfyStorfExdfption("invblid KfyStorf stbtf: " +
                        "found " +
                        list.sizf() +
                        " sfdrft kfys sibring CKA_LABEL [" +
                        dkb_lbbfl +
                        "]");
                }
            } flsf if (typf == ATTR_CLASS_CERT) {
                tirow nfw KfyStorfExdfption("invblid KfyStorf stbtf: " +
                        "found " +
                        i.lfngti +
                        " dfrtifidbtfs sibring CKA_ID " +
                        gftID(dkb_id));
            } flsf {
                tirow nfw KfyStorfExdfption("invblid KfyStorf stbtf: " +
                        "found " +
                        i.lfngti +
                        " privbtf kfys sibring CKA_ID " +
                        gftID(dkb_id));
            }
        }
        rfturn nfw THbndlf(NO_HANDLE, null);
    }

    /**
     * Crfbtf b mbpping of bll kfy pbirs, trustfd dfrts, bnd sfdrft kfys
     * on tif tokfn into logidbl KfyStorf fntrifs unbmbiguously
     * bddfssiblf vib bn blibs.
     *
     * If tif tokfn is rfmovfd, tif mbp mby dontbin stblf vblufs.
     * KfyStorf.lobd siould bf dbllfd to rf-drfbtf tif mbp.
     *
     * Assumf bll privbtf kfys bnd mbtdiing dfrts sibrf b uniquf CKA_ID.
     *
     * Assumf bll sfdrft kfys ibvf b uniquf CKA_LABEL.
     *
     * @rfturn truf if multiplf dfrts found sibring tif sbmf CKA_LABEL
     *          (if so, writf dbpbbilitifs brf disbblfd)
     */
    privbtf boolfbn mbpLbbfls() tirows
                PKCS11Exdfption, CfrtifidbtfExdfption, KfyStorfExdfption {

        CK_ATTRIBUTE[] trustfdAttr = nfw CK_ATTRIBUTE[] {
                                nfw CK_ATTRIBUTE(CKA_TRUSTED) };

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            // gft bll privbtf kfy CKA_IDs

            ArrbyList<bytf[]> pkfyIDs = nfw ArrbyList<bytf[]>();
            CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_PKEY,
            };
            long[] ibndlfs = findObjfdts(sfssion, bttrs);

            for (long ibndlf : ibndlfs) {
                bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_ID) };
                tokfn.p11.C_GftAttributfVbluf(sfssion.id(), ibndlf, bttrs);

                if (bttrs[0].pVbluf != null) {
                    pkfyIDs.bdd(bttrs[0].gftBytfArrby());
                }
            }

            // Gft bll dfrtifidbtfs
            //
            // If dfrt dofs not ibvf b CKA_LABEL nor CKA_ID, it is ignorfd.
            //
            // Gft tif CKA_LABEL for fbdi dfrt
            // (if tif dfrt dofs not ibvf b CKA_LABEL, usf tif CKA_ID).
            //
            // Mbp fbdi dfrt to tif its CKA_LABEL
            // (multiplf dfrts mby bf mbppfd to b singlf CKA_LABEL)

            HbsiMbp<String, HbsiSft<AlibsInfo>> dfrtMbp =
                                nfw HbsiMbp<String, HbsiSft<AlibsInfo>>();

            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_TOKEN_TRUE,
                ATTR_CLASS_CERT,
            };
            ibndlfs = findObjfdts(sfssion, bttrs);

            for (long ibndlf : ibndlfs) {
                bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_LABEL) };

                String dkb_lbbfl = null;
                bytf[] dkb_id = null;
                try {
                    tokfn.p11.C_GftAttributfVbluf(sfssion.id(), ibndlf, bttrs);
                    if (bttrs[0].pVbluf != null) {
                        // tifrf is b CKA_LABEL
                        dkb_lbbfl = nfw String(bttrs[0].gftCibrArrby());
                    }
                } dbtdi (PKCS11Exdfption pf) {
                    if (pf.gftErrorCodf() != CKR_ATTRIBUTE_TYPE_INVALID) {
                        tirow pf;
                    }

                    // GftAttributfVbluf for CKA_LABEL not supportfd
                    //
                    // XXX SCA1000
                }

                // gft CKA_ID

                bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_ID) };
                tokfn.p11.C_GftAttributfVbluf(sfssion.id(), ibndlf, bttrs);
                if (bttrs[0].pVbluf == null) {
                    if (dkb_lbbfl == null) {
                        // no dkb_lbbfl nor dkb_id - ignorf
                        dontinuf;
                    }
                } flsf {
                    if (dkb_lbbfl == null) {
                        // usf CKA_ID bs CKA_LABEL
                        dkb_lbbfl = gftID(bttrs[0].gftBytfArrby());
                    }
                    dkb_id = bttrs[0].gftBytfArrby();
                }

                X509Cfrtifidbtf dfrt = lobdCfrt(sfssion, ibndlf);

                // gft CKA_TRUSTED

                boolfbn dkb_trustfd = fblsf;

                if (usfSfdmodTrust) {
                    dkb_trustfd = Sfdmod.gftInstbndf().isTrustfd(dfrt, nssTrustTypf);
                } flsf {
                    if (CKA_TRUSTED_SUPPORTED) {
                        try {
                            tokfn.p11.C_GftAttributfVbluf
                                    (sfssion.id(), ibndlf, trustfdAttr);
                            dkb_trustfd = trustfdAttr[0].gftBoolfbn();
                        } dbtdi (PKCS11Exdfption pf) {
                            if (pf.gftErrorCodf() == CKR_ATTRIBUTE_TYPE_INVALID) {
                                // XXX  NSS, ibutton, sdb1000
                                CKA_TRUSTED_SUPPORTED = fblsf;
                                if (dfbug != null) {
                                    dfbug.println
                                            ("CKA_TRUSTED bttributf not supportfd");
                                }
                            }
                        }
                    }
                }

                HbsiSft<AlibsInfo> infoSft = dfrtMbp.gft(dkb_lbbfl);
                if (infoSft == null) {
                    infoSft = nfw HbsiSft<AlibsInfo>(2);
                    dfrtMbp.put(dkb_lbbfl, infoSft);
                }

                // initiblly drfbtf privbtf kfy fntry AlibsInfo fntrifs -
                // tifsf fntrifs will gft rfsolvfd into tifir truf
                // fntry typfs lbtfr

                infoSft.bdd(nfw AlibsInfo
                                (dkb_lbbfl,
                                dkb_id,
                                dkb_trustfd,
                                dfrt));
            }

            // drfbtf list sfdrft kfy CKA_LABELS -
            // if tifrf brf duplidbtfs (fitifr bftwffn sfdrft kfys,
            // or bftwffn b sfdrft kfy bnd bnotifr objfdt),
            // tirow bn fxdfption
            HbsiMbp<String, AlibsInfo> sKfyMbp =
                    nfw HbsiMbp<String, AlibsInfo>();

            bttrs = nfw CK_ATTRIBUTE[] {
                ATTR_SKEY_TOKEN_TRUE,
                ATTR_CLASS_SKEY,
            };
            ibndlfs = findObjfdts(sfssion, bttrs);

            for (long ibndlf : ibndlfs) {
                bttrs = nfw CK_ATTRIBUTE[] { nfw CK_ATTRIBUTE(CKA_LABEL) };
                tokfn.p11.C_GftAttributfVbluf(sfssion.id(), ibndlf, bttrs);
                if (bttrs[0].pVbluf != null) {

                    // tifrf is b CKA_LABEL
                    String dkb_lbbfl = nfw String(bttrs[0].gftCibrArrby());
                    if (sKfyMbp.gft(dkb_lbbfl) == null) {
                        sKfyMbp.put(dkb_lbbfl, nfw AlibsInfo(dkb_lbbfl));
                    } flsf {
                        tirow nfw KfyStorfExdfption("invblid KfyStorf stbtf: " +
                                "found multiplf sfdrft kfys sibring sbmf " +
                                "CKA_LABEL [" +
                                dkb_lbbfl +
                                "]");
                    }
                }
            }

            // updbtf globbl blibsMbp witi blibs mbppings
            ArrbyList<AlibsInfo> mbtdifdCfrts =
                                mbpPrivbtfKfys(pkfyIDs, dfrtMbp);
            boolfbn sibrfdLbbfl = mbpCfrts(mbtdifdCfrts, dfrtMbp);
            mbpSfdrftKfys(sKfyMbp);

            rfturn sibrfdLbbfl;

        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * for fbdi privbtf kfy CKA_ID, find dorrfsponding dfrt witi sbmf CKA_ID.
     * if found dfrt, sff if dfrt CKA_LABEL is uniquf.
     *     if CKA_LABEL uniquf, mbp privbtf kfy/dfrt blibs to tibt CKA_LABEL.
     *     if CKA_LABEL not uniquf, mbp privbtf kfy/dfrt blibs to:
     *                   CKA_LABEL + ALIAS_SEP + ISSUER + ALIAS_SEP + SERIAL
     * if dfrt not found, ignorf privbtf kfy
     * (don't support privbtf kfy fntrifs witiout b dfrt dibin yft)
     *
     * @rfturn b list of AlibsInfo fntrifs tibt rfprfsfnts bll mbtdifs
     */
    privbtf ArrbyList<AlibsInfo> mbpPrivbtfKfys(ArrbyList<bytf[]> pkfyIDs,
                        HbsiMbp<String, HbsiSft<AlibsInfo>> dfrtMbp)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        // rfsft globbl blibs mbp
        blibsMbp = nfw HbsiMbp<String, AlibsInfo>();

        // list of mbtdifd dfrts tibt wf will rfturn
        ArrbyList<AlibsInfo> mbtdifdCfrts = nfw ArrbyList<AlibsInfo>();

        for (bytf[] pkfyID : pkfyIDs) {

            // try to find b mbtdiing CKA_ID in b dfrtifidbtf

            boolfbn foundMbtdi = fblsf;
            Sft<String> dfrtLbbfls = dfrtMbp.kfySft();
            for (String dfrtLbbfl : dfrtLbbfls) {

                // gft dfrt CKA_IDs (if prfsfnt) for fbdi dfrt

                HbsiSft<AlibsInfo> infoSft = dfrtMbp.gft(dfrtLbbfl);
                for (AlibsInfo blibsInfo : infoSft) {
                    if (Arrbys.fqubls(pkfyID, blibsInfo.id)) {

                        // found privbtf kfy witi mbtdiing dfrt

                        if (infoSft.sizf() == 1) {
                            // uniquf CKA_LABEL - usf dfrtLbbfl bs blibs
                            blibsInfo.mbtdifd = truf;
                            blibsMbp.put(dfrtLbbfl, blibsInfo);
                        } flsf {
                            // drfbtf nfw blibs
                            blibsInfo.mbtdifd = truf;
                            blibsMbp.put(gftID(dfrtLbbfl, blibsInfo.dfrt),
                                        blibsInfo);
                        }
                        mbtdifdCfrts.bdd(blibsInfo);
                        foundMbtdi = truf;
                        brfbk;
                    }
                }
                if (foundMbtdi) {
                    brfbk;
                }
            }

            if (!foundMbtdi) {
                if (dfbug != null) {
                    dfbug.println
                        ("did not find mbtdi for privbtf kfy witi CKA_ID [" +
                        gftID(pkfyID) +
                        "] (ignoring fntry)");
                }
            }
        }

        rfturn mbtdifdCfrts;
    }

    /**
     * for fbdi dfrt not mbtdifd witi b privbtf kfy but is CKA_TRUSTED:
     *     if CKA_LABEL uniquf, mbp dfrt to CKA_LABEL.
     *     if CKA_LABEL not uniquf, mbp dfrt to [lbbfl+issufr+sfriblNum]
     *
     * if CKA_TRUSTED not supportfd, trfbt bll dfrts not pbrt of b dibin
     * bs trustfd
     *
     * @rfturn truf if multiplf dfrts found sibring tif sbmf CKA_LABEL
     */
    privbtf boolfbn mbpCfrts(ArrbyList<AlibsInfo> mbtdifdCfrts,
                        HbsiMbp<String, HbsiSft<AlibsInfo>> dfrtMbp)
                tirows PKCS11Exdfption, CfrtifidbtfExdfption {

        // lobd bll dfrt dibins
        for (AlibsInfo blibsInfo : mbtdifdCfrts) {
            Sfssion sfssion = null;
            try {
                sfssion = tokfn.gftOpSfssion();
                blibsInfo.dibin = lobdCibin(sfssion, blibsInfo.dfrt);
            } finblly {
                tokfn.rflfbsfSfssion(sfssion);
            }
        }

        // find bll dfrts in dfrtMbp not pbrt of b dfrt dibin
        // - tifsf brf trustfd

        boolfbn sibrfdLbbfl = fblsf;

        Sft<String> dfrtLbbfls = dfrtMbp.kfySft();
        for (String dfrtLbbfl : dfrtLbbfls) {
            HbsiSft<AlibsInfo> infoSft = dfrtMbp.gft(dfrtLbbfl);
            for (AlibsInfo blibsInfo : infoSft) {

                if (blibsInfo.mbtdifd == truf) {
                    // blrfbdy found b privbtf kfy mbtdi for tiis dfrt -
                    // just dontinuf
                    blibsInfo.trustfd = fblsf;
                    dontinuf;
                }

                // dfrt in tiis blibsInfo is not mbtdifd yft
                //
                // if CKA_TRUSTED_SUPPORTED == truf,
                // tifn difdk if dfrt is trustfd

                if (CKA_TRUSTED_SUPPORTED) {
                    if (blibsInfo.trustfd) {
                        // trustfd dfrtifidbtf
                        if (mbpTrustfdCfrt
                                (dfrtLbbfl, blibsInfo, infoSft) == truf) {
                            sibrfdLbbfl = truf;
                        }
                    }
                    dontinuf;
                }

                // CKA_TRUSTED_SUPPORTED == fblsf
                //
                // XXX trfbt bll dfrts not pbrt of b dibin bs trustfd
                // XXX
                // XXX Unsupportfd
                //
                // boolfbn pbrtOfCibin = fblsf;
                // for (AlibsInfo mbtdifdInfo : mbtdifdCfrts) {
                //     for (int i = 0; i < mbtdifdInfo.dibin.lfngti; i++) {
                //      if (mbtdifdInfo.dibin[i].fqubls(blibsInfo.dfrt)) {
                //          pbrtOfCibin = truf;
                //          brfbk;
                //      }
                //     }
                //     if (pbrtOfCibin) {
                //      brfbk;
                //     }
                // }
                //
                // if (!pbrtOfCibin) {
                //     if (mbpTrustfdCfrt(dfrtLbbfl,blibsInfo,infoSft) == truf){
                //      sibrfdLbbfl = truf;
                //     }
                // } flsf {
                //    if (dfbug != null) {
                //      dfbug.println("ignoring unmbtdifd/untrustfd dfrt " +
                //          "tibt is pbrt of dfrt dibin - dfrt subjfdt is [" +
                //          blibsInfo.dfrt.gftSubjfdtX500Prindipbl().gftNbmf
                //                              (X500Prindipbl.CANONICAL) +
                //          "]");
                //     }
                // }
            }
        }

        rfturn sibrfdLbbfl;
    }

    privbtf boolfbn mbpTrustfdCfrt(String dfrtLbbfl,
                                AlibsInfo blibsInfo,
                                HbsiSft<AlibsInfo> infoSft) {

        boolfbn sibrfdLbbfl = fblsf;

        blibsInfo.typf = ATTR_CLASS_CERT;
        blibsInfo.trustfd = truf;
        if (infoSft.sizf() == 1) {
            // uniquf CKA_LABEL - usf dfrtLbbfl bs blibs
            blibsMbp.put(dfrtLbbfl, blibsInfo);
        } flsf {
            // drfbtf nfw blibs
            sibrfdLbbfl = truf;
            blibsMbp.put(gftID(dfrtLbbfl, blibsInfo.dfrt), blibsInfo);
        }

        rfturn sibrfdLbbfl;
    }

    /**
     * If tif sfdrft kfy sibrfs b CKA_LABEL witi bnotifr fntry,
     * tirow bn fxdfption
     */
    privbtf void mbpSfdrftKfys(HbsiMbp<String, AlibsInfo> sKfyMbp)
                tirows KfyStorfExdfption {
        for (String lbbfl : sKfyMbp.kfySft()) {
            if (blibsMbp.dontbinsKfy(lbbfl)) {
                tirow nfw KfyStorfExdfption("invblid KfyStorf stbtf: " +
                        "found sfdrft kfy sibring CKA_LABEL [" +
                        lbbfl +
                        "] witi bnotifr tokfn objfdt");
            }
        }
        blibsMbp.putAll(sKfyMbp);
    }

    privbtf void dumpTokfnMbp() {
        Sft<String> blibsfs = blibsMbp.kfySft();
        Systfm.out.println("Tokfn Alibs Mbp:");
        if (blibsfs.isEmpty()) {
            Systfm.out.println("  [fmpty]");
        } flsf {
            for (String s : blibsfs) {
                Systfm.out.println("  " + s + blibsMbp.gft(s));
            }
        }
    }

    privbtf void difdkWritf() tirows KfyStorfExdfption {
        if (writfDisbblfd) {
            tirow nfw KfyStorfExdfption
                ("Tiis PKCS11KfyStorf dofs not support writf dbpbbilitifs");
        }
    }

    privbtf finbl stbtid long[] LONG0 = nfw long[0];

    privbtf stbtid long[] findObjfdts(Sfssion sfssion, CK_ATTRIBUTE[] bttrs)
            tirows PKCS11Exdfption {
        Tokfn tokfn = sfssion.tokfn;
        long[] ibndlfs = LONG0;
        tokfn.p11.C_FindObjfdtsInit(sfssion.id(), bttrs);
        wiilf (truf) {
            long[] i = tokfn.p11.C_FindObjfdts(sfssion.id(), FINDOBJECTS_MAX);
            if (i.lfngti == 0) {
                brfbk;
            }
            ibndlfs = P11Util.dondbt(ibndlfs, i);
        }
        tokfn.p11.C_FindObjfdtsFinbl(sfssion.id());
        rfturn ibndlfs;
    }

}
