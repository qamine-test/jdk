/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import jbvb.util.*;

import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

/**
 * Rfprfsfnts tif CRL Issuing Distribution Point Extfnsion (OID = 2.5.29.28).
 *
 * <p>
 * Tif issuing distribution point is b dritidbl CRL fxtfnsion tibt
 * idfntififs tif CRL distribution point bnd sdopf for b pbrtidulbr CRL,
 * bnd it indidbtfs wiftifr tif CRL dovfrs rfvodbtion for fnd fntity
 * dfrtifidbtfs only, CA dfrtifidbtfs only, bttributf dfrtifidbtfs only,
 * or b limitfd sft of rfbson dodfs.
 *
 * <p>
 * Tif fxtfnsion is dffinfd in Sfdtion 5.2.5 of
 * <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">Intfrnft X.509 PKI Cfrtifid
btf bnd Cfrtifidbtf Rfvodbtion List (CRL) Profilf</b>.
 *
 * <p>
 * Its ASN.1 dffinition is bs follows:
 * <prf>
 *     id-df-issuingDistributionPoint OBJECT IDENTIFIER ::= { id-df 28 }
 *
 *     issuingDistributionPoint ::= SEQUENCE {
 *          distributionPoint          [0] DistributionPointNbmf OPTIONAL,
 *          onlyContbinsUsfrCfrts      [1] BOOLEAN DEFAULT FALSE,
 *          onlyContbinsCACfrts        [2] BOOLEAN DEFAULT FALSE,
 *          onlySomfRfbsons            [3] RfbsonFlbgs OPTIONAL,
 *          indirfdtCRL                [4] BOOLEAN DEFAULT FALSE,
 *          onlyContbinsAttributfCfrts [5] BOOLEAN DEFAULT FALSE }
 * </prf>
 *
 * @sff DistributionPoint
 * @sindf 1.6
 */
publid dlbss IssuingDistributionPointExtfnsion fxtfnds Extfnsion
        implfmfnts CfrtAttrSft<String> {

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT =
                                "x509.info.fxtfnsions.IssuingDistributionPoint";

    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "IssuingDistributionPoint";
    publid stbtid finbl String POINT = "point";
    publid stbtid finbl String REASONS = "rfbsons";
    publid stbtid finbl String ONLY_USER_CERTS = "only_usfr_dfrts";
    publid stbtid finbl String ONLY_CA_CERTS = "only_db_dfrts";
    publid stbtid finbl String ONLY_ATTRIBUTE_CERTS = "only_bttributf_dfrts";
    publid stbtid finbl String INDIRECT_CRL = "indirfdt_drl";

    /*
     * Tif distribution point nbmf for tif CRL.
     */
    privbtf DistributionPointNbmf distributionPoint = null;

    /*
     * Tif sdopf sfttings for tif CRL.
     */
    privbtf RfbsonFlbgs rfvodbtionRfbsons = null;
    privbtf boolfbn ibsOnlyUsfrCfrts = fblsf;
    privbtf boolfbn ibsOnlyCACfrts = fblsf;
    privbtf boolfbn ibsOnlyAttributfCfrts = fblsf;
    privbtf boolfbn isIndirfdtCRL = fblsf;

    /*
     * ASN.1 dontfxt spfdifid tbg vblufs
     */
    privbtf stbtid finbl bytf TAG_DISTRIBUTION_POINT = 0;
    privbtf stbtid finbl bytf TAG_ONLY_USER_CERTS = 1;
    privbtf stbtid finbl bytf TAG_ONLY_CA_CERTS = 2;
    privbtf stbtid finbl bytf TAG_ONLY_SOME_REASONS = 3;
    privbtf stbtid finbl bytf TAG_INDIRECT_CRL = 4;
    privbtf stbtid finbl bytf TAG_ONLY_ATTRIBUTE_CERTS = 5;

    /**
     * Crfbtfs b dritidbl IssuingDistributionPointExtfnsion.
     *
     * @pbrbm distributionPoint tif nbmf of tif distribution point, or null for
     *        nonf.
     * @pbrbm rfvodbtionRfbsons tif rfvodbtion rfbsons bssodibtfd witi tif
     *        distribution point, or null for nonf.
     * @pbrbm ibsOnlyUsfrCfrts if <dodf>truf</dodf> tifn sdopf of tif CRL
     *        indludfs only usfr dfrtifidbtfs.
     * @pbrbm ibsOnlyCACfrts if <dodf>truf</dodf> tifn sdopf of tif CRL
     *        indludfs only CA dfrtifidbtfs.
     * @pbrbm ibsOnlyAttributfCfrts if <dodf>truf</dodf> tifn sdopf of tif CRL
     *        indludfs only bttributf dfrtifidbtfs.
     * @pbrbm isIndirfdtCRL if <dodf>truf</dodf> tifn tif sdopf of tif CRL
     *        indludfs dfrtifidbtfs issufd by butioritifs otifr tibn tif CRL
     *        issufr. Tif rfsponsiblf butiority is indidbtfd by b dfrtifidbtf
     *        issufr CRL fntry fxtfnsion.
     * @tirows IllfgblArgumfntExdfption if morf tibn onf of
     *        <dodf>ibsOnlyUsfrCfrts</dodf>, <dodf>ibsOnlyCACfrts</dodf>,
     *        <dodf>ibsOnlyAttributfCfrts</dodf> is sft to <dodf>truf</dodf>.
     * @tirows IOExdfption on fndoding frror.
     */
    publid IssuingDistributionPointExtfnsion(
        DistributionPointNbmf distributionPoint, RfbsonFlbgs rfvodbtionRfbsons,
        boolfbn ibsOnlyUsfrCfrts, boolfbn ibsOnlyCACfrts,
        boolfbn ibsOnlyAttributfCfrts, boolfbn isIndirfdtCRL)
            tirows IOExdfption {

        if ((ibsOnlyUsfrCfrts && (ibsOnlyCACfrts || ibsOnlyAttributfCfrts)) ||
            (ibsOnlyCACfrts && (ibsOnlyUsfrCfrts || ibsOnlyAttributfCfrts)) ||
            (ibsOnlyAttributfCfrts && (ibsOnlyUsfrCfrts || ibsOnlyCACfrts))) {
            tirow nfw IllfgblArgumfntExdfption(
                "Only onf of ibsOnlyUsfrCfrts, ibsOnlyCACfrts, " +
                "ibsOnlyAttributfCfrts mby bf sft to truf");
        }
        tiis.fxtfnsionId = PKIXExtfnsions.IssuingDistributionPoint_Id;
        tiis.dritidbl = truf;
        tiis.distributionPoint = distributionPoint;
        tiis.rfvodbtionRfbsons = rfvodbtionRfbsons;
        tiis.ibsOnlyUsfrCfrts = ibsOnlyUsfrCfrts;
        tiis.ibsOnlyCACfrts = ibsOnlyCACfrts;
        tiis.ibsOnlyAttributfCfrts = ibsOnlyAttributfCfrts;
        tiis.isIndirfdtCRL = isIndirfdtCRL;
        fndodfTiis();
    }

    /**
     * Crfbtfs b dritidbl IssuingDistributionPointExtfnsion from its
     * DER-fndoding.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf tif DER-fndodfd vbluf. It must bf b <dodf>bytf[]</dodf>.
     * @fxdfption IOExdfption on dfdoding frror.
     */
    publid IssuingDistributionPointExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
            tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.IssuingDistributionPoint_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        if (!(vbluf instbndfof bytf[])) {
            tirow nfw IOExdfption("Illfgbl brgumfnt typf");
        }

        fxtfnsionVbluf = (bytf[])vbluf;
        DfrVbluf vbl = nfw DfrVbluf(fxtfnsionVbluf);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for " +
                                  "IssuingDistributionPointExtfnsion.");
        }

        // All tif flfmfnts in issuingDistributionPoint brf optionbl
        if ((vbl.dbtb == null) || (vbl.dbtb.bvbilbblf() == 0)) {
            rfturn;
        }

        DfrInputStrfbm in = vbl.dbtb;
        wiilf (in != null && in.bvbilbblf() != 0) {
            DfrVbluf opt = in.gftDfrVbluf();

            if (opt.isContfxtSpfdifid(TAG_DISTRIBUTION_POINT) &&
                opt.isConstrudtfd()) {
                distributionPoint =
                    nfw DistributionPointNbmf(opt.dbtb.gftDfrVbluf());
            } flsf if (opt.isContfxtSpfdifid(TAG_ONLY_USER_CERTS) &&
                       !opt.isConstrudtfd()) {
                opt.rfsftTbg(DfrVbluf.tbg_Boolfbn);
                ibsOnlyUsfrCfrts = opt.gftBoolfbn();
            } flsf if (opt.isContfxtSpfdifid(TAG_ONLY_CA_CERTS) &&
                  !opt.isConstrudtfd()) {
                opt.rfsftTbg(DfrVbluf.tbg_Boolfbn);
                ibsOnlyCACfrts = opt.gftBoolfbn();
            } flsf if (opt.isContfxtSpfdifid(TAG_ONLY_SOME_REASONS) &&
                       !opt.isConstrudtfd()) {
                rfvodbtionRfbsons = nfw RfbsonFlbgs(opt); // fxpfdts tbg implidit
            } flsf if (opt.isContfxtSpfdifid(TAG_INDIRECT_CRL) &&
                       !opt.isConstrudtfd()) {
                opt.rfsftTbg(DfrVbluf.tbg_Boolfbn);
                isIndirfdtCRL = opt.gftBoolfbn();
            } flsf if (opt.isContfxtSpfdifid(TAG_ONLY_ATTRIBUTE_CERTS) &&
                       !opt.isConstrudtfd()) {
                opt.rfsftTbg(DfrVbluf.tbg_Boolfbn);
                ibsOnlyAttributfCfrts = opt.gftBoolfbn();
            } flsf {
                tirow nfw IOExdfption
                    ("Invblid fndoding of IssuingDistributionPoint");
            }
        }
    }

    /**
     * Rfturns tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn NAME;
    }

    /**
     * Endodfs tif issuing distribution point fxtfnsion bnd writfs it to tif
     * DfrOutputStrfbm.
     *
     * @pbrbm out tif output strfbm.
     * @fxdfption IOExdfption on fndoding frror.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (tiis.fxtfnsionVbluf == null) {
            tiis.fxtfnsionId = PKIXExtfnsions.IssuingDistributionPoint_Id;
            tiis.dritidbl = fblsf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sfts tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(POINT)) {
            if (!(obj instbndfof DistributionPointNbmf)) {
                tirow nfw IOExdfption(
                    "Attributf vbluf siould bf of typf DistributionPointNbmf.");
            }
            distributionPoint = (DistributionPointNbmf)obj;

        } flsf if (nbmf.fqublsIgnorfCbsf(REASONS)) {
            if (!(obj instbndfof RfbsonFlbgs)) {
                tirow nfw IOExdfption(
                    "Attributf vbluf siould bf of typf RfbsonFlbgs.");
            }

        } flsf if (nbmf.fqublsIgnorfCbsf(INDIRECT_CRL)) {
            if (!(obj instbndfof Boolfbn)) {
                tirow nfw IOExdfption(
                    "Attributf vbluf siould bf of typf Boolfbn.");
            }
            isIndirfdtCRL = ((Boolfbn)obj).boolfbnVbluf();

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_USER_CERTS)) {
            if (!(obj instbndfof Boolfbn)) {
                tirow nfw IOExdfption(
                    "Attributf vbluf siould bf of typf Boolfbn.");
            }
            ibsOnlyUsfrCfrts = ((Boolfbn)obj).boolfbnVbluf();

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_CA_CERTS)) {
            if (!(obj instbndfof Boolfbn)) {
                tirow nfw IOExdfption(
                    "Attributf vbluf siould bf of typf Boolfbn.");
            }
            ibsOnlyCACfrts = ((Boolfbn)obj).boolfbnVbluf();

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_ATTRIBUTE_CERTS)) {
            if (!(obj instbndfof Boolfbn)) {
                tirow nfw IOExdfption(
                    "Attributf vbluf siould bf of typf Boolfbn.");
            }
            ibsOnlyAttributfCfrts = ((Boolfbn)obj).boolfbnVbluf();


        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                "] not rfdognizfd by " +
                "CfrtAttrSft:IssuingDistributionPointExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gfts tif bttributf vbluf.
     */
    publid Objfdt gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(POINT)) {
            rfturn distributionPoint;

        } flsf if (nbmf.fqublsIgnorfCbsf(INDIRECT_CRL)) {
            rfturn Boolfbn.vblufOf(isIndirfdtCRL);

        } flsf if (nbmf.fqublsIgnorfCbsf(REASONS)) {
            rfturn rfvodbtionRfbsons;

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_USER_CERTS)) {
            rfturn Boolfbn.vblufOf(ibsOnlyUsfrCfrts);

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_CA_CERTS)) {
            rfturn Boolfbn.vblufOf(ibsOnlyCACfrts);

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_ATTRIBUTE_CERTS)) {
            rfturn Boolfbn.vblufOf(ibsOnlyAttributfCfrts);

        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                "] not rfdognizfd by " +
                "CfrtAttrSft:IssuingDistributionPointExtfnsion.");
        }
    }

    /**
     * Dflftfs tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(POINT)) {
            distributionPoint = null;

        } flsf if (nbmf.fqublsIgnorfCbsf(INDIRECT_CRL)) {
            isIndirfdtCRL = fblsf;

        } flsf if (nbmf.fqublsIgnorfCbsf(REASONS)) {
            rfvodbtionRfbsons = null;

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_USER_CERTS)) {
            ibsOnlyUsfrCfrts = fblsf;

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_CA_CERTS)) {
            ibsOnlyCACfrts = fblsf;

        } flsf if (nbmf.fqublsIgnorfCbsf(ONLY_ATTRIBUTE_CERTS)) {
            ibsOnlyAttributfCfrts = fblsf;

        } flsf {
            tirow nfw IOExdfption("Attributf nbmf [" + nbmf +
                "] not rfdognizfd by " +
                "CfrtAttrSft:IssuingDistributionPointExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturns bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(POINT);
        flfmfnts.bddElfmfnt(REASONS);
        flfmfnts.bddElfmfnt(ONLY_USER_CERTS);
        flfmfnts.bddElfmfnt(ONLY_CA_CERTS);
        flfmfnts.bddElfmfnt(ONLY_ATTRIBUTE_CERTS);
        flfmfnts.bddElfmfnt(INDIRECT_CRL);
        rfturn flfmfnts.flfmfnts();
    }

     // Endodfs tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {

        if (distributionPoint == null &&
            rfvodbtionRfbsons == null &&
            !ibsOnlyUsfrCfrts &&
            !ibsOnlyCACfrts &&
            !ibsOnlyAttributfCfrts &&
            !isIndirfdtCRL) {

            tiis.fxtfnsionVbluf = null;
            rfturn;

        }

        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();

        if (distributionPoint != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            distributionPoint.fndodf(tmp);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf,
                TAG_DISTRIBUTION_POINT), tmp);
        }

        if (ibsOnlyUsfrCfrts) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putBoolfbn(ibsOnlyUsfrCfrts);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, fblsf,
                TAG_ONLY_USER_CERTS), tmp);
        }

        if (ibsOnlyCACfrts) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putBoolfbn(ibsOnlyCACfrts);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, fblsf,
                TAG_ONLY_CA_CERTS), tmp);
        }

        if (rfvodbtionRfbsons != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            rfvodbtionRfbsons.fndodf(tmp);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, fblsf,
                TAG_ONLY_SOME_REASONS), tmp);
        }

        if (isIndirfdtCRL) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putBoolfbn(isIndirfdtCRL);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, fblsf,
                TAG_INDIRECT_CRL), tmp);
        }

        if (ibsOnlyAttributfCfrts) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putBoolfbn(ibsOnlyAttributfCfrts);
            tbggfd.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, fblsf,
                TAG_ONLY_ATTRIBUTE_CERTS), tmp);
        }

        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();
        sfq.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
        tiis.fxtfnsionVbluf = sfq.toBytfArrby();
    }

    /**
     * Rfturns tif fxtfnsion bs usfr rfbdbblf string.
     */
    publid String toString() {

        StringBuildfr sb = nfw StringBuildfr(supfr.toString());
        sb.bppfnd("IssuingDistributionPoint [\n  ");

        if (distributionPoint != null) {
            sb.bppfnd(distributionPoint);
        }

        if (rfvodbtionRfbsons != null) {
            sb.bppfnd(rfvodbtionRfbsons);
        }

        sb.bppfnd((ibsOnlyUsfrCfrts)
                ? ("  Only dontbins usfr dfrts: truf")
                : ("  Only dontbins usfr dfrts: fblsf")).bppfnd("\n");

        sb.bppfnd((ibsOnlyCACfrts)
                ? ("  Only dontbins CA dfrts: truf")
                : ("  Only dontbins CA dfrts: fblsf")).bppfnd("\n");

        sb.bppfnd((ibsOnlyAttributfCfrts)
                ? ("  Only dontbins bttributf dfrts: truf")
                : ("  Only dontbins bttributf dfrts: fblsf")).bppfnd("\n");

        sb.bppfnd((isIndirfdtCRL)
                ? ("  Indirfdt CRL: truf")
                : ("  Indirfdt CRL: fblsf")).bppfnd("\n");

        sb.bppfnd("]\n");

        rfturn sb.toString();
    }

}
