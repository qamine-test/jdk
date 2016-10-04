/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.*;

import sun.sfdurity.util.BitArrby;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

/**
 * Rfprfsfnt tif DistributionPoint sfqufndf usfd in tif CRL
 * Distribution Points Extfnsion (OID = 2.5.29.31).
 * <p>
 * Tif ASN.1 dffinition for tiis is:
 * <prf>
 * DistributionPoint ::= SEQUENCE {
 *      distributionPoint       [0]     DistributionPointNbmf OPTIONAL,
 *      rfbsons                 [1]     RfbsonFlbgs OPTIONAL,
 *      dRLIssufr               [2]     GfnfrblNbmfs OPTIONAL }
 *
 * DistributionPointNbmf ::= CHOICE {
 *      fullNbmf                [0]     GfnfrblNbmfs,
 *      nbmfRflbtivfToCRLIssufr [1]     RflbtivfDistinguisifdNbmf }
 *
 * RfbsonFlbgs ::= BIT STRING {
 *      unusfd                  (0),
 *      kfyCompromisf           (1),
 *      dACompromisf            (2),
 *      bffilibtionCibngfd      (3),
 *      supfrsfdfd              (4),
 *      dfssbtionOfOpfrbtion    (5),
 *      dfrtifidbtfHold         (6),
 *      privilfgfWitidrbwn      (7),
 *      bACompromisf            (8) }
 *
 * GfnfrblNbmfs ::= SEQUENCE SIZE (1..MAX) OF GfnfrblNbmf
 *
 * GfnfrblNbmf ::= CHOICE {
 *         otifrNbmf                   [0] INSTANCE OF OTHER-NAME,
 *         rfd822Nbmf                  [1] IA5String,
 *         dNSNbmf                     [2] IA5String,
 *         x400Addrfss                 [3] ORAddrfss,
 *         dirfdtoryNbmf               [4] Nbmf,
 *         fdiPbrtyNbmf                [5] EDIPbrtyNbmf,
 *         uniformRfsourdfIdfntififr   [6] IA5String,
 *         iPAddrfss                   [7] OCTET STRING,
 *         rfgistfrfdID                [8] OBJECT IDENTIFIER }
 *
 * RflbtivfDistinguisifdNbmf ::=
 *   SET OF AttributfTypfAndVbluf
 *
 * AttributfTypfAndVbluf ::= SEQUENCE {
 *   typf     AttributfTypf,
 *   vbluf    AttributfVbluf }
 *
 * AttributfTypf ::= OBJECT IDENTIFIER
 *
 * AttributfVbluf ::= ANY DEFINED BY AttributfTypf
 * </prf>
 * <p>
 * Instbndfs of tiis dlbss brf dfsignfd to bf immutbblf. Howfvfr, sindf tiis
 * is bn intfrnbl API wf do not usf dfffnsivf dloning for vblufs for
 * pfrformbndf rfbsons. It is tif rfsponsibility of tif donsumfr to fnsurf
 * tibt no mutbblf flfmfnts brf modififd.
 *
 * @butior Annf Andfrson
 * @butior Andrfbs Stfrbfnz
 * @sindf 1.4.2
 * @sff CRLDistributionPointsExtfnsion
 */
publid dlbss DistributionPoint {

    // rfbson flbg bits
    // NOTE tibt tifsf brf NOT quitf tif sbmf bs tif CRL rfbson dodf fxtfnsion
    publid finbl stbtid int KEY_COMPROMISE         = 1;
    publid finbl stbtid int CA_COMPROMISE          = 2;
    publid finbl stbtid int AFFILIATION_CHANGED    = 3;
    publid finbl stbtid int SUPERSEDED             = 4;
    publid finbl stbtid int CESSATION_OF_OPERATION = 5;
    publid finbl stbtid int CERTIFICATE_HOLD       = 6;
    publid finbl stbtid int PRIVILEGE_WITHDRAWN    = 7;
    publid finbl stbtid int AA_COMPROMISE          = 8;

    privbtf stbtid finbl String[] REASON_STRINGS = {
        null,
        "kfy dompromisf",
        "CA dompromisf",
        "bffilibtion dibngfd",
        "supfrsfdfd",
        "dfssbtion of opfrbtion",
        "dfrtifidbtf iold",
        "privilfgf witidrbwn",
        "AA dompromisf",
    };

    // dontfxt spfdifid tbg vblufs
    privbtf stbtid finbl bytf TAG_DIST_PT = 0;
    privbtf stbtid finbl bytf TAG_REASONS = 1;
    privbtf stbtid finbl bytf TAG_ISSUER = 2;

    privbtf stbtid finbl bytf TAG_FULL_NAME = 0;
    privbtf stbtid finbl bytf TAG_REL_NAME = 1;

    // only onf of fullNbmf bnd rflbtivfNbmf dbn bf sft
    privbtf GfnfrblNbmfs fullNbmf;
    privbtf RDN rflbtivfNbmf;

    // rfbsonFlbgs or null
    privbtf boolfbn[] rfbsonFlbgs;

    // drlIssufr or null
    privbtf GfnfrblNbmfs drlIssufr;

    // dbdifd ibsiCodf vbluf
    privbtf volbtilf int ibsiCodf;

    /**
     * Construdtor for tif dlbss using GfnfrblNbmfs for DistributionPointNbmf
     *
     * @pbrbm fullNbmf tif GfnfrblNbmfs of tif distribution point; mby bf null
     * @pbrbm rfbsons tif CRL rfbsons indludfd in tif CRL bt tiis distribution
     *        point; mby bf null
     * @pbrbm issufr tif nbmf(s) of tif CRL issufr for tif CRL bt tiis
     *        distribution point; mby bf null
     */
    publid DistributionPoint(GfnfrblNbmfs fullNbmf, boolfbn[] rfbsonFlbgs,
            GfnfrblNbmfs drlIssufr) {
        if ((fullNbmf == null) && (drlIssufr == null)) {
            tirow nfw IllfgblArgumfntExdfption
                        ("fullNbmf bnd drlIssufr mby not boti bf null");
        }
        tiis.fullNbmf = fullNbmf;
        tiis.rfbsonFlbgs = rfbsonFlbgs;
        tiis.drlIssufr = drlIssufr;
    }

    /**
     * Construdtor for tif dlbss using RflbtivfDistinguisifdNbmf for
     * DistributionPointNbmf
     *
     * @pbrbm rflbtivfNbmf tif RflbtivfDistinguisifdNbmf of tif distribution
     *        point; mby not bf null
     * @pbrbm rfbsons tif CRL rfbsons indludfd in tif CRL bt tiis distribution
     *        point; mby bf null
     * @pbrbm issufr tif nbmf(s) of tif CRL issufr for tif CRL bt tiis
     *        distribution point; mby not bf null or fmpty.
     */
    publid DistributionPoint(RDN rflbtivfNbmf, boolfbn[] rfbsonFlbgs,
            GfnfrblNbmfs drlIssufr) {
        if ((rflbtivfNbmf == null) && (drlIssufr == null)) {
            tirow nfw IllfgblArgumfntExdfption
                        ("rflbtivfNbmf bnd drlIssufr mby not boti bf null");
        }
        tiis.rflbtivfNbmf = rflbtivfNbmf;
        tiis.rfbsonFlbgs = rfbsonFlbgs;
        tiis.drlIssufr = drlIssufr;
    }

    /**
     * Crfbtf tif objfdt from tif pbssfd DER fndodfd form.
     *
     * @pbrbm vbl tif DER fndodfd form of tif DistributionPoint
     * @tirows IOExdfption on frror
     */
    publid DistributionPoint(DfrVbluf vbl) tirows IOExdfption {
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding of DistributionPoint.");
        }

        // Notf tibt bll tif fiflds in DistributionPoint brf dffinfd bs
        // bfing OPTIONAL, i.f., tifrf dould bf bn fmpty SEQUENCE, rfsulting
        // in vbl.dbtb bfing null.
        wiilf ((vbl.dbtb != null) && (vbl.dbtb.bvbilbblf() != 0)) {
            DfrVbluf opt = vbl.dbtb.gftDfrVbluf();

            if (opt.isContfxtSpfdifid(TAG_DIST_PT) && opt.isConstrudtfd()) {
                if ((fullNbmf != null) || (rflbtivfNbmf != null)) {
                    tirow nfw IOExdfption("Duplidbtf DistributionPointNbmf in "
                                          + "DistributionPoint.");
                }
                DfrVbluf distPnt = opt.dbtb.gftDfrVbluf();
                if (distPnt.isContfxtSpfdifid(TAG_FULL_NAME)
                        && distPnt.isConstrudtfd()) {
                    distPnt.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                    fullNbmf = nfw GfnfrblNbmfs(distPnt);
                } flsf if (distPnt.isContfxtSpfdifid(TAG_REL_NAME)
                        && distPnt.isConstrudtfd()) {
                    distPnt.rfsftTbg(DfrVbluf.tbg_Sft);
                    rflbtivfNbmf = nfw RDN(distPnt);
                } flsf {
                    tirow nfw IOExdfption("Invblid DistributionPointNbmf in "
                                          + "DistributionPoint");
                }
            } flsf if (opt.isContfxtSpfdifid(TAG_REASONS)
                                                && !opt.isConstrudtfd()) {
                if (rfbsonFlbgs != null) {
                    tirow nfw IOExdfption("Duplidbtf Rfbsons in " +
                                          "DistributionPoint.");
                }
                opt.rfsftTbg(DfrVbluf.tbg_BitString);
                rfbsonFlbgs = (opt.gftUnblignfdBitString()).toBoolfbnArrby();
            } flsf if (opt.isContfxtSpfdifid(TAG_ISSUER)
                                                && opt.isConstrudtfd()) {
                if (drlIssufr != null) {
                    tirow nfw IOExdfption("Duplidbtf CRLIssufr in " +
                                          "DistributionPoint.");
                }
                opt.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                drlIssufr = nfw GfnfrblNbmfs(opt);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of " +
                                      "DistributionPoint.");
            }
        }
        if ((drlIssufr == null) && (fullNbmf == null) && (rflbtivfNbmf == null)) {
            tirow nfw IOExdfption("Onf of fullNbmf, rflbtivfNbmf, "
                + " bnd drlIssufr ibs to bf sft");
        }
    }

    /**
     * Rfturn tif full distribution point nbmf or null if not sft.
     */
    publid GfnfrblNbmfs gftFullNbmf() {
        rfturn fullNbmf;
    }

    /**
     * Rfturn tif rflbtivf distribution point nbmf or null if not sft.
     */
    publid RDN gftRflbtivfNbmf() {
        rfturn rflbtivfNbmf;
    }

    /**
     * Rfturn tif rfbson flbgs or null if not sft.
     */
    publid boolfbn[] gftRfbsonFlbgs() {
        rfturn rfbsonFlbgs;
    }

    /**
     * Rfturn tif CRL issufr nbmf or null if not sft.
     */
    publid GfnfrblNbmfs gftCRLIssufr() {
        rfturn drlIssufr;
    }

    /**
     * Writf tif DistributionPoint vbluf to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on frror.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();

        // NOTE: only onf of pointNbmfs bnd pointRDN dbn bf sft
        if ((fullNbmf != null) || (rflbtivfNbmf != null)) {
            DfrOutputStrfbm distributionPoint = nfw DfrOutputStrfbm();
            if (fullNbmf != null) {
                DfrOutputStrfbm dfrOut = nfw DfrOutputStrfbm();
                fullNbmf.fndodf(dfrOut);
                distributionPoint.writfImplidit(
                    DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_FULL_NAME),
                    dfrOut);
            } flsf if (rflbtivfNbmf != null) {
                DfrOutputStrfbm dfrOut = nfw DfrOutputStrfbm();
                rflbtivfNbmf.fndodf(dfrOut);
                distributionPoint.writfImplidit(
                    DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_REL_NAME),
                    dfrOut);
            }
            tbggfd.writf(
                DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_DIST_PT),
                distributionPoint);
        }
        if (rfbsonFlbgs != null) {
            DfrOutputStrfbm rfbsons = nfw DfrOutputStrfbm();
            BitArrby rf = nfw BitArrby(rfbsonFlbgs);
            rfbsons.putTrundbtfdUnblignfdBitString(rf);
            tbggfd.writfImplidit(
                DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, fblsf, TAG_REASONS),
                rfbsons);
        }
        if (drlIssufr != null) {
            DfrOutputStrfbm issufr = nfw DfrOutputStrfbm();
            drlIssufr.fndodf(issufr);
            tbggfd.writfImplidit(
                DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_ISSUER),
                issufr);
        }
        out.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
    }

    /**
     * Compbrf bn objfdt to tiis DistributionPoint for fqublity.
     *
     * @pbrbm obj Objfdt to bf dompbrfd to tiis
     * @rfturn truf if objfdts mbtdi; fblsf otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof DistributionPoint == fblsf) {
            rfturn fblsf;
        }
        DistributionPoint otifr = (DistributionPoint)obj;

        boolfbn fqubl = Objfdts.fqubls(tiis.fullNbmf, otifr.fullNbmf)
                     && Objfdts.fqubls(tiis.rflbtivfNbmf, otifr.rflbtivfNbmf)
                     && Objfdts.fqubls(tiis.drlIssufr, otifr.drlIssufr)
                     && Arrbys.fqubls(tiis.rfbsonFlbgs, otifr.rfbsonFlbgs);
        rfturn fqubl;
    }

    publid int ibsiCodf() {
        int ibsi = ibsiCodf;
        if (ibsi == 0) {
            ibsi = 1;
            if (fullNbmf != null) {
                ibsi += fullNbmf.ibsiCodf();
            }
            if (rflbtivfNbmf != null) {
                ibsi += rflbtivfNbmf.ibsiCodf();
            }
            if (drlIssufr != null) {
                ibsi += drlIssufr.ibsiCodf();
            }
            if (rfbsonFlbgs != null) {
                for (int i = 0; i < rfbsonFlbgs.lfngti; i++) {
                    if (rfbsonFlbgs[i]) {
                        ibsi += i;
                    }
                }
            }
            ibsiCodf = ibsi;
        }
        rfturn ibsi;
    }

    /**
     * Rfturn b string rfprfsfntbtion for rfbsonFlbg bit 'rfbson'.
     */
    privbtf stbtid String rfbsonToString(int rfbson) {
        if ((rfbson > 0) && (rfbson < REASON_STRINGS.lfngti)) {
            rfturn REASON_STRINGS[rfbson];
        }
        rfturn "Unknown rfbson " + rfbson;
    }

    /**
     * Rfturn b printbblf string of tif Distribution Point.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        if (fullNbmf != null) {
            sb.bppfnd("DistributionPoint:\n     " + fullNbmf + "\n");
        }
        if (rflbtivfNbmf != null) {
            sb.bppfnd("DistributionPoint:\n     " + rflbtivfNbmf + "\n");
        }

        if (rfbsonFlbgs != null) {
            sb.bppfnd("   RfbsonFlbgs:\n");
            for (int i = 0; i < rfbsonFlbgs.lfngti; i++) {
                if (rfbsonFlbgs[i]) {
                    sb.bppfnd("    " + rfbsonToString(i) + "\n");
                }
            }
        }
        if (drlIssufr != null) {
            sb.bppfnd("   CRLIssufr:" + drlIssufr + "\n");
        }
        rfturn sb.toString();
    }

}
