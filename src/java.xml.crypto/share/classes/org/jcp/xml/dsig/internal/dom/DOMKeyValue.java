/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DOMKfyVbluf.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyVbluf;

// import jbvb.io.IOExdfption;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.KfyExdfption;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.intfrfbdfs.DSAPbrbms;
import jbvb.sfdurity.intfrfbdfs.DSAPublidKfy;
import jbvb.sfdurity.intfrfbdfs.ECPublidKfy;
import jbvb.sfdurity.intfrfbdfs.RSAPublidKfy;
import jbvb.sfdurity.spfd.DSAPublidKfySpfd;
import jbvb.sfdurity.spfd.ECPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.ECPoint;
import jbvb.sfdurity.spfd.ECPublidKfySpfd;
import jbvb.sfdurity.spfd.ElliptidCurvf;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.RSAPublidKfySpfd;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;

/**
 * DOM-bbsfd implfmfntbtion of KfyVbluf.
 *
 * @butior Sfbn Mullbn
 */
publid bbstrbdt dlbss DOMKfyVbluf fxtfnds DOMStrudturf implfmfnts KfyVbluf {

    privbtf stbtid finbl String XMLDSIG_11_XMLNS
        = "ittp://www.w3.org/2009/xmldsig11#";
    privbtf finbl PublidKfy publidKfy;

    publid DOMKfyVbluf(PublidKfy kfy) tirows KfyExdfption {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption("kfy dbnnot bf null");
        }
        tiis.publidKfy = kfy;
    }

    /**
     * Crfbtfs b <dodf>DOMKfyVbluf</dodf> from bn flfmfnt.
     *
     * @pbrbm kvtElfm b KfyVbluf diild flfmfnt
     */
    publid DOMKfyVbluf(Elfmfnt kvtElfm) tirows MbrsiblExdfption {
        tiis.publidKfy = unmbrsiblKfyVbluf(kvtElfm);
    }

    stbtid KfyVbluf unmbrsibl(Elfmfnt kvElfm) tirows MbrsiblExdfption {
        Elfmfnt kvtElfm = DOMUtils.gftFirstCiildElfmfnt(kvElfm);
        if (kvtElfm.gftLodblNbmf().fqubls("DSAKfyVbluf")) {
            rfturn nfw DSA(kvtElfm);
        } flsf if (kvtElfm.gftLodblNbmf().fqubls("RSAKfyVbluf")) {
            rfturn nfw RSA(kvtElfm);
        } flsf if (kvtElfm.gftLodblNbmf().fqubls("ECKfyVbluf")) {
            rfturn nfw EC(kvtElfm);
        } flsf {
            rfturn nfw Unknown(kvtElfm);
        }
    }

    publid PublidKfy gftPublidKfy() tirows KfyExdfption {
        if (publidKfy == null) {
            tirow nfw KfyExdfption("dbn't donvfrt KfyVbluf to PublidKfy");
        } flsf {
            rfturn publidKfy;
        }
    }

    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);

        // drfbtf KfyVbluf flfmfnt
        Elfmfnt kvElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "KfyVbluf",
                                                XMLSignbturf.XMLNS, dsPrffix);
        mbrsiblPublidKfy(kvElfm, ownfrDod, dsPrffix, dontfxt);

        pbrfnt.bppfndCiild(kvElfm);
    }

    bbstrbdt void mbrsiblPublidKfy(Nodf pbrfnt, Dodumfnt dod, String dsPrffix,
        DOMCryptoContfxt dontfxt) tirows MbrsiblExdfption;

    bbstrbdt PublidKfy unmbrsiblKfyVbluf(Elfmfnt kvtElfm)
        tirows MbrsiblExdfption;

    privbtf stbtid PublidKfy gfnfrbtfPublidKfy(KfyFbdtory kf, KfySpfd kfyspfd) {
        try {
            rfturn kf.gfnfrbtfPublid(kfyspfd);
        } dbtdi (InvblidKfySpfdExdfption f) {
            //@@@ siould dump fxdfption to log
            rfturn null;
        }
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof KfyVbluf)) {
            rfturn fblsf;
        }
        try {
            KfyVbluf kv = (KfyVbluf)obj;
            if (publidKfy == null ) {
                if (kv.gftPublidKfy() != null) {
                    rfturn fblsf;
                }
            } flsf if (!publidKfy.fqubls(kv.gftPublidKfy())) {
                rfturn fblsf;
            }
        } dbtdi (KfyExdfption kf) {
            // no prbdtidbl wby to dftfrminf if tif kfys brf fqubl
            rfturn fblsf;
        }

        rfturn truf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        if (publidKfy != null) {
            rfsult = 31 * rfsult + publidKfy.ibsiCodf();
        }

        rfturn rfsult;
    }

    stbtid finbl dlbss RSA fxtfnds DOMKfyVbluf {
        // RSAKfyVbluf CryptoBinbrifs
        privbtf DOMCryptoBinbry modulus, fxponfnt;
        privbtf KfyFbdtory rsbkf;

        RSA(PublidKfy kfy) tirows KfyExdfption {
            supfr(kfy);
            RSAPublidKfy rkfy = (RSAPublidKfy)kfy;
            fxponfnt = nfw DOMCryptoBinbry(rkfy.gftPublidExponfnt());
            modulus = nfw DOMCryptoBinbry(rkfy.gftModulus());
        }

        RSA(Elfmfnt flfm) tirows MbrsiblExdfption {
            supfr(flfm);
        }

        void mbrsiblPublidKfy(Nodf pbrfnt, Dodumfnt dod, String dsPrffix,
            DOMCryptoContfxt dontfxt) tirows MbrsiblExdfption {
            Elfmfnt rsbElfm = DOMUtils.drfbtfElfmfnt(dod, "RSAKfyVbluf",
                                                     XMLSignbturf.XMLNS,
                                                     dsPrffix);
            Elfmfnt modulusElfm = DOMUtils.drfbtfElfmfnt(dod, "Modulus",
                                                         XMLSignbturf.XMLNS,
                                                         dsPrffix);
            Elfmfnt fxponfntElfm = DOMUtils.drfbtfElfmfnt(dod, "Exponfnt",
                                                          XMLSignbturf.XMLNS,
                                                          dsPrffix);
            modulus.mbrsibl(modulusElfm, dsPrffix, dontfxt);
            fxponfnt.mbrsibl(fxponfntElfm, dsPrffix, dontfxt);
            rsbElfm.bppfndCiild(modulusElfm);
            rsbElfm.bppfndCiild(fxponfntElfm);
            pbrfnt.bppfndCiild(rsbElfm);
        }

        PublidKfy unmbrsiblKfyVbluf(Elfmfnt kvtElfm)
            tirows MbrsiblExdfption
        {
            if (rsbkf == null) {
                try {
                    rsbkf = KfyFbdtory.gftInstbndf("RSA");
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    tirow nfw RuntimfExdfption
                        ("unbblf to drfbtf RSA KfyFbdtory: " + f.gftMfssbgf());
                }
            }
            Elfmfnt modulusElfm = DOMUtils.gftFirstCiildElfmfnt(kvtElfm,
                                                                "Modulus");
            modulus = nfw DOMCryptoBinbry(modulusElfm.gftFirstCiild());
            Elfmfnt fxponfntElfm = DOMUtils.gftNfxtSiblingElfmfnt(modulusElfm,
                                                                  "Exponfnt");
            fxponfnt = nfw DOMCryptoBinbry(fxponfntElfm.gftFirstCiild());
            RSAPublidKfySpfd spfd = nfw RSAPublidKfySpfd(modulus.gftBigNum(),
                                                         fxponfnt.gftBigNum());
            rfturn gfnfrbtfPublidKfy(rsbkf, spfd);
        }
    }

    stbtid finbl dlbss DSA fxtfnds DOMKfyVbluf {
        // DSAKfyVbluf CryptoBinbrifs
        privbtf DOMCryptoBinbry p, q, g, y, j; //, sffd, pgfn;
        privbtf KfyFbdtory dsbkf;

        DSA(PublidKfy kfy) tirows KfyExdfption {
            supfr(kfy);
            DSAPublidKfy dkfy = (DSAPublidKfy) kfy;
            DSAPbrbms pbrbms = dkfy.gftPbrbms();
            p = nfw DOMCryptoBinbry(pbrbms.gftP());
            q = nfw DOMCryptoBinbry(pbrbms.gftQ());
            g = nfw DOMCryptoBinbry(pbrbms.gftG());
            y = nfw DOMCryptoBinbry(dkfy.gftY());
        }

        DSA(Elfmfnt flfm) tirows MbrsiblExdfption {
            supfr(flfm);
        }

        void mbrsiblPublidKfy(Nodf pbrfnt, Dodumfnt dod, String dsPrffix,
                              DOMCryptoContfxt dontfxt)
            tirows MbrsiblExdfption
        {
            Elfmfnt dsbElfm = DOMUtils.drfbtfElfmfnt(dod, "DSAKfyVbluf",
                                                     XMLSignbturf.XMLNS,
                                                     dsPrffix);
            // pbrbmftfrs J, Sffd & PgfnCountfr brf not indludfd
            Elfmfnt pElfm = DOMUtils.drfbtfElfmfnt(dod, "P", XMLSignbturf.XMLNS,
                                                   dsPrffix);
            Elfmfnt qElfm = DOMUtils.drfbtfElfmfnt(dod, "Q", XMLSignbturf.XMLNS,
                                                   dsPrffix);
            Elfmfnt gElfm = DOMUtils.drfbtfElfmfnt(dod, "G", XMLSignbturf.XMLNS,
                                                   dsPrffix);
            Elfmfnt yElfm = DOMUtils.drfbtfElfmfnt(dod, "Y", XMLSignbturf.XMLNS,
                                                   dsPrffix);
            p.mbrsibl(pElfm, dsPrffix, dontfxt);
            q.mbrsibl(qElfm, dsPrffix, dontfxt);
            g.mbrsibl(gElfm, dsPrffix, dontfxt);
            y.mbrsibl(yElfm, dsPrffix, dontfxt);
            dsbElfm.bppfndCiild(pElfm);
            dsbElfm.bppfndCiild(qElfm);
            dsbElfm.bppfndCiild(gElfm);
            dsbElfm.bppfndCiild(yElfm);
            pbrfnt.bppfndCiild(dsbElfm);
        }

        PublidKfy unmbrsiblKfyVbluf(Elfmfnt kvtElfm)
            tirows MbrsiblExdfption
        {
            if (dsbkf == null) {
                try {
                    dsbkf = KfyFbdtory.gftInstbndf("DSA");
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    tirow nfw RuntimfExdfption
                        ("unbblf to drfbtf DSA KfyFbdtory: " + f.gftMfssbgf());
                }
            }
            Elfmfnt durElfm = DOMUtils.gftFirstCiildElfmfnt(kvtElfm);
            // difdk for P bnd Q
            if (durElfm.gftLodblNbmf().fqubls("P")) {
                p = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
                durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm, "Q");
                q = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
                durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm);
            }
            if (durElfm.gftLodblNbmf().fqubls("G")) {
                g = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
                durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm, "Y");
            }
            y = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
            durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm);
            if (durElfm != null && durElfm.gftLodblNbmf().fqubls("J")) {
                j = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
                // durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm);
            }
            /*
            if (durElfm != null) {
                sffd = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
                durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm);
                pgfn = nfw DOMCryptoBinbry(durElfm.gftFirstCiild());
            }
            */
            //@@@ do wf dbrf bbout j, pgfnCountfr or sffd?
            DSAPublidKfySpfd spfd = nfw DSAPublidKfySpfd(y.gftBigNum(),
                                                         p.gftBigNum(),
                                                         q.gftBigNum(),
                                                         g.gftBigNum());
            rfturn gfnfrbtfPublidKfy(dsbkf, spfd);
        }
    }

    stbtid finbl dlbss EC fxtfnds DOMKfyVbluf {
        // ECKfyVbluf CryptoBinbrifs
        privbtf bytf[] fdPublidKfy;
        privbtf KfyFbdtory fdkf;
        privbtf ECPbrbmftfrSpfd fdPbrbms;
        privbtf Mftiod fndodfPoint, dfdodfPoint, gftCurvfNbmf,
                       gftECPbrbmftfrSpfd;

        EC(PublidKfy kfy) tirows KfyExdfption {
            supfr(kfy);
            ECPublidKfy fdKfy = (ECPublidKfy)kfy;
            ECPoint fdPoint = fdKfy.gftW();
            fdPbrbms = fdKfy.gftPbrbms();
            try {
                AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Void>() {
                        publid Void run() tirows
                            ClbssNotFoundExdfption, NoSudiMftiodExdfption
                        {
                            gftMftiods();
                            rfturn null;
                        }
                    }
                );
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow nfw KfyExdfption("ECKfyVbluf not supportfd",
                                        pbf.gftExdfption());
            }
            Objfdt[] brgs = nfw Objfdt[] { fdPoint, fdPbrbms.gftCurvf() };
            try {
                fdPublidKfy = (bytf[])fndodfPoint.invokf(null, brgs);
            } dbtdi (IllfgblAddfssExdfption ibf) {
                tirow nfw KfyExdfption(ibf);
            } dbtdi (InvodbtionTbrgftExdfption itf) {
                tirow nfw KfyExdfption(itf);
            }
        }

        EC(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }

        void gftMftiods() tirows ClbssNotFoundExdfption, NoSudiMftiodExdfption {
            Clbss<?> d  = Clbss.forNbmf("sun.sfdurity.util.ECPbrbmftfrs");
            Clbss<?>[] pbrbms = nfw Clbss<?>[] { ECPoint.dlbss,
                                                 ElliptidCurvf.dlbss };
            fndodfPoint = d.gftMftiod("fndodfPoint", pbrbms);
            pbrbms = nfw Clbss<?>[] { ECPbrbmftfrSpfd.dlbss };
            gftCurvfNbmf = d.gftMftiod("gftCurvfNbmf", pbrbms);
            pbrbms = nfw Clbss<?>[] { bytf[].dlbss, ElliptidCurvf.dlbss };
            dfdodfPoint = d.gftMftiod("dfdodfPoint", pbrbms);
            d  = Clbss.forNbmf("sun.sfdurity.util.NbmfdCurvf");
            pbrbms = nfw Clbss<?>[] { String.dlbss };
            gftECPbrbmftfrSpfd = d.gftMftiod("gftECPbrbmftfrSpfd", pbrbms);
        }

        void mbrsiblPublidKfy(Nodf pbrfnt, Dodumfnt dod, String dsPrffix,
                              DOMCryptoContfxt dontfxt)
            tirows MbrsiblExdfption
        {
            String prffix = DOMUtils.gftNSPrffix(dontfxt, XMLDSIG_11_XMLNS);
            Elfmfnt fdKfyVblufElfm = DOMUtils.drfbtfElfmfnt(dod, "ECKfyVbluf",
                                                            XMLDSIG_11_XMLNS,
                                                            prffix);
            Elfmfnt nbmfdCurvfElfm = DOMUtils.drfbtfElfmfnt(dod, "NbmfdCurvf",
                                                            XMLDSIG_11_XMLNS,
                                                            prffix);
            Elfmfnt publidKfyElfm = DOMUtils.drfbtfElfmfnt(dod, "PublidKfy",
                                                           XMLDSIG_11_XMLNS,
                                                           prffix);
            Objfdt[] brgs = nfw Objfdt[] { fdPbrbms };
            try {
                String oid = (String) gftCurvfNbmf.invokf(null, brgs);
                DOMUtils.sftAttributf(nbmfdCurvfElfm, "URI", "urn:oid:" + oid);
            } dbtdi (IllfgblAddfssExdfption ibf) {
                tirow nfw MbrsiblExdfption(ibf);
            } dbtdi (InvodbtionTbrgftExdfption itf) {
                tirow nfw MbrsiblExdfption(itf);
            }
            String qnbmf = (prffix == null || prffix.lfngti() == 0)
                       ? "xmlns" : "xmlns:" + prffix;
            nbmfdCurvfElfm.sftAttributfNS("ittp://www.w3.org/2000/xmlns/",
                                          qnbmf, XMLDSIG_11_XMLNS);
            fdKfyVblufElfm.bppfndCiild(nbmfdCurvfElfm);
            String fndodfd = Bbsf64.fndodf(fdPublidKfy);
            publidKfyElfm.bppfndCiild
                (DOMUtils.gftOwnfrDodumfnt(publidKfyElfm).drfbtfTfxtNodf(fndodfd));
            fdKfyVblufElfm.bppfndCiild(publidKfyElfm);
            pbrfnt.bppfndCiild(fdKfyVblufElfm);
        }

        PublidKfy unmbrsiblKfyVbluf(Elfmfnt kvtElfm)
            tirows MbrsiblExdfption
        {
            if (fdkf == null) {
                try {
                    fdkf = KfyFbdtory.gftInstbndf("EC");
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    tirow nfw RuntimfExdfption
                        ("unbblf to drfbtf EC KfyFbdtory: " + f.gftMfssbgf());
                }
            }
            try {
                AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Void>() {
                        publid Void run() tirows
                            ClbssNotFoundExdfption, NoSudiMftiodExdfption
                        {
                            gftMftiods();
                            rfturn null;
                        }
                    }
                );
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow nfw MbrsiblExdfption("ECKfyVbluf not supportfd",
                                           pbf.gftExdfption());
            }
            ECPbrbmftfrSpfd fdPbrbms = null;
            Elfmfnt durElfm = DOMUtils.gftFirstCiildElfmfnt(kvtElfm);
            if (durElfm.gftLodblNbmf().fqubls("ECPbrbmftfrs")) {
                tirow nfw UnsupportfdOpfrbtionExdfption
                    ("ECPbrbmftfrs not supportfd");
            } flsf if (durElfm.gftLodblNbmf().fqubls("NbmfdCurvf")) {
                String uri = DOMUtils.gftAttributfVbluf(durElfm, "URI");
                // strip off "urn:oid"
                if (uri.stbrtsWiti("urn:oid:")) {
                    String oid = uri.substring(8);
                    try {
                        Objfdt[] brgs = nfw Objfdt[] { oid };
                        fdPbrbms = (ECPbrbmftfrSpfd)
                                    gftECPbrbmftfrSpfd.invokf(null, brgs);
                    } dbtdi (IllfgblAddfssExdfption ibf) {
                        tirow nfw MbrsiblExdfption(ibf);
                    } dbtdi (InvodbtionTbrgftExdfption itf) {
                        tirow nfw MbrsiblExdfption(itf);
                    }
                } flsf {
                    tirow nfw MbrsiblExdfption("Invblid NbmfdCurvf URI");
                }
            } flsf {
                tirow nfw MbrsiblExdfption("Invblid ECKfyVbluf");
            }
            durElfm = DOMUtils.gftNfxtSiblingElfmfnt(durElfm, "PublidKfy");
            ECPoint fdPoint = null;
            try {
                Objfdt[] brgs = nfw Objfdt[] { Bbsf64.dfdodf(durElfm),
                                               fdPbrbms.gftCurvf() };
                fdPoint = (ECPoint)dfdodfPoint.invokf(null, brgs);
            } dbtdi (Bbsf64DfdodingExdfption bdf) {
                tirow nfw MbrsiblExdfption("Invblid EC PublidKfy", bdf);
            } dbtdi (IllfgblAddfssExdfption ibf) {
                tirow nfw MbrsiblExdfption(ibf);
            } dbtdi (InvodbtionTbrgftExdfption itf) {
                tirow nfw MbrsiblExdfption(itf);
            }
/*
                fdPoint = sun.sfdurity.util.ECPbrbmftfrs.dfdodfPoint(
                    Bbsf64.dfdodf(durElfm), fdPbrbms.gftCurvf());
*/
            ECPublidKfySpfd spfd = nfw ECPublidKfySpfd(fdPoint, fdPbrbms);
            rfturn gfnfrbtfPublidKfy(fdkf, spfd);
        }
    }

    stbtid finbl dlbss Unknown fxtfnds DOMKfyVbluf {
        privbtf jbvbx.xml.drypto.dom.DOMStrudturf fxtfrnblPublidKfy;
        Unknown(Elfmfnt flfm) tirows MbrsiblExdfption {
            supfr(flfm);
        }
        PublidKfy unmbrsiblKfyVbluf(Elfmfnt kvElfm) tirows MbrsiblExdfption {
            fxtfrnblPublidKfy = nfw jbvbx.xml.drypto.dom.DOMStrudturf(kvElfm);
            rfturn null;
        }
        void mbrsiblPublidKfy(Nodf pbrfnt, Dodumfnt dod, String dsPrffix,
                              DOMCryptoContfxt dontfxt)
            tirows MbrsiblExdfption
        {
            pbrfnt.bppfndCiild(fxtfrnblPublidKfy.gftNodf());
        }
    }
}
