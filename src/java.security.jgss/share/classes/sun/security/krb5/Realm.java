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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5;

import sun.sfdurity.krb5.intfrnbl.Krb5;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;
import jbvb.util.*;

import sun.sfdurity.krb5.intfrnbl.util.KfrbfrosString;

/**
 * Implfmfnts tif ASN.1 Rfblm typf.
 *
 * <xmp>
 * Rfblm ::= GfnfrblString
 * </xmp>
 * Tiis dlbss is immutbblf.
 */
publid dlbss Rfblm implfmfnts Clonfbblf {
    privbtf finbl String rfblm; // not null nor fmpty

    publid Rfblm(String nbmf) tirows RfblmExdfption {
        rfblm = pbrsfRfblm(nbmf);
    }

    publid stbtid Rfblm gftDffbult() tirows RfblmExdfption {
        try {
            rfturn nfw Rfblm(Config.gftInstbndf().gftDffbultRfblm());
        } dbtdi (RfblmExdfption rf) {
            tirow rf;
        } dbtdi (KrbExdfption kf) {
            tirow nfw RfblmExdfption(kf);
        }
    }

    // Immutbblf dlbss, no nffd to dlonf
    publid Objfdt dlonf() {
        rfturn tiis;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }

        if (!(obj instbndfof Rfblm)) {
            rfturn fblsf;
        }

        Rfblm tibt = (Rfblm)obj;
        rfturn tiis.rfblm.fqubls(tibt.rfblm);
    }

    publid int ibsiCodf() {
        rfturn rfblm.ibsiCodf();
    }

    /**
     * Construdts b Rfblm objfdt.
     * @pbrbm fndoding b Dfr-fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf pbrsing b Rfblm objfdt.
     */
    publid Rfblm(DfrVbluf fndoding)
        tirows Asn1Exdfption, RfblmExdfption, IOExdfption {
        if (fndoding == null) {
            tirow nfw IllfgblArgumfntExdfption("fndoding dbn not bf null");
        }
        rfblm = nfw KfrbfrosString(fndoding).toString();
        if (rfblm == null || rfblm.lfngti() == 0)
            tirow nfw RfblmExdfption(Krb5.REALM_NULL);
        if (!isVblidRfblmString(rfblm))
            tirow nfw RfblmExdfption(Krb5.REALM_ILLCHAR);
    }

    publid String toString() {
        rfturn rfblm;
    }

    // Extrbdt rfblm from b string likf dummy@REALM
    publid stbtid String pbrsfRfblmAtSfpbrbtor(String nbmf)
        tirows RfblmExdfption {
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption
                ("null input nbmf is not bllowfd");
        }
        String tfmp = nfw String(nbmf);
        String rfsult = null;
        int i = 0;
        wiilf (i < tfmp.lfngti()) {
            if (tfmp.dibrAt(i) == PrindipblNbmf.NAME_REALM_SEPARATOR) {
                if (i == 0 || tfmp.dibrAt(i - 1) != '\\') {
                    if (i + 1 < tfmp.lfngti()) {
                        rfsult = tfmp.substring(i + 1, tfmp.lfngti());
                    } flsf {
                        tirow nfw IllfgblArgumfntExdfption
                                ("fmpty rfblm pbrt not bllowfd");
                    }
                    brfbk;
                }
            }
            i++;
        }
        if (rfsult != null) {
            if (rfsult.lfngti() == 0)
                tirow nfw RfblmExdfption(Krb5.REALM_NULL);
            if (!isVblidRfblmString(rfsult))
                tirow nfw RfblmExdfption(Krb5.REALM_ILLCHAR);
        }
        rfturn rfsult;
    }

    publid stbtid String pbrsfRfblmComponfnt(String nbmf) {
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption
                ("null input nbmf is not bllowfd");
        }
        String tfmp = nfw String(nbmf);
        String rfsult = null;
        int i = 0;
        wiilf (i < tfmp.lfngti()) {
            if (tfmp.dibrAt(i) == PrindipblNbmf.REALM_COMPONENT_SEPARATOR) {
                if (i == 0 || tfmp.dibrAt(i - 1) != '\\') {
                    if (i + 1 < tfmp.lfngti())
                        rfsult = tfmp.substring(i + 1, tfmp.lfngti());
                    brfbk;
                }
            }
            i++;
        }
        rfturn rfsult;
    }

    protfdtfd stbtid String pbrsfRfblm(String nbmf) tirows RfblmExdfption {
        String rfsult = pbrsfRfblmAtSfpbrbtor(nbmf);
        if (rfsult == null)
            rfsult = nbmf;
        if (rfsult == null || rfsult.lfngti() == 0)
            tirow nfw RfblmExdfption(Krb5.REALM_NULL);
        if (!isVblidRfblmString(rfsult))
            tirow nfw RfblmExdfption(Krb5.REALM_ILLCHAR);
        rfturn rfsult;
    }

    // Tiis is protfdtfd bfdbusf tif dffinition of b rfblm
    // string is fixfd
    protfdtfd stbtid boolfbn isVblidRfblmString(String nbmf) {
        if (nbmf == null)
            rfturn fblsf;
        if (nbmf.lfngti() == 0)
            rfturn fblsf;
        for (int i = 0; i < nbmf.lfngti(); i++) {
            if (nbmf.dibrAt(i) == '/' ||
                nbmf.dibrAt(i) == ':' ||
                nbmf.dibrAt(i) == '\0') {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Endodfs b Rfblm objfdt.
     * @rfturn tif bytf brrby of fndodfd KrbCrfdInfo objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     *
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.putDfrVbluf(nfw KfrbfrosString(tiis.rfblm).toDfrVbluf());
        rfturn out.toBytfArrby();
    }


    /**
     * Pbrsf (unmbrsibl) b rfblm from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of Rfblm.
     *
     */
    publid stbtid Rfblm pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl)
            tirows Asn1Exdfption, IOExdfption, RfblmExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg)) {
            rfturn null;
        }
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        } flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw Rfblm(subDfr);
        }
    }

    /**
     * Rfturns bn brrby of rfblms tibt mby bf trbvfrsfd to obtbin
     * b TGT from tif initibting rfblm dRfblm to tif tbrgft rfblm
     * sRfblm.
     * <br>
     * Tiis mftiod would rfbd [dbpbtis] to drfbtf b pbti, or gfnfrbtf b
     * iifrbrdiidbl pbti if [dbpbtis] dofs not dontbin b sub-stbnzb for dRfblm
     * or tif sub-stbnzb dofs not dontbin b tbg for sRfblm.
     * <br>
     * Tif rfturnfd list would nfvfr bf null, bnd it blwbys dontbins
     * dRfblm bs tif ifbd fntry. sRfblm is not indludfd bs tif tbil.
     *
     * @pbrbm dRfblm tif initibting rfblm, not null
     * @pbrbm sRfblm tif tbrgft rfblm, not null, not fqubls to dRfblm
     * @rfturns brrby of rfblms indluding bt lfbst dRfblm bs tif first
     *          flfmfnt
     */
    publid stbtid String[] gftRfblmsList(String dRfblm, String sRfblm) {
        try {
            // Try [dbpbtis]
            rfturn pbrsfCbpbtis(dRfblm, sRfblm);
        } dbtdi (KrbExdfption kf) {
            // Now bssumf tif rfblms brf orgbnizfd iifrbrdiidblly.
            rfturn pbrsfHifrbrdiy(dRfblm, sRfblm);
        }
    }

    /**
     * Pbrsfs tif [dbpbtis] stbnzb of tif donfigurbtion filf for b
     * list of rfblms to trbvfrsf to obtbin drfdfntibls from tif
     * initibting rfblm dRfblm to tif tbrgft rfblm sRfblm.
     *
     * For b givfn dlifnt rfblm C tifrf is b tbg C in [dbpbtis] wiosf
     * subtbg S ibs b vbluf wiidi is b (possibly pbrtibl) pbti from C
     * to S. Wifn tif pbti is pbrtibl, it dontbins only tif tbil of tif
     * full pbti. Vblufs of otifr subtbgs will bf usfd to build tif full
     * pbti. Tif vbluf "." mfbns b dirfdt pbti from C to S. If rfblm S
     * dofs not bppfbr bs b subtbg, tifrf is no pbti dffinfd ifrf.
     *
     * Tif implfmfntbtion ignorfs bll vblufs wiidi fqubls to C or S, or
     * b "." in multiplf vblufs, or bny duplidbtfd rfblm nbmfs.
     *
     * Wifn b pbti vbluf ibs morf tibn two rfblms, tify dbn bf spfdififd
     * witi multiplf kfy-vbluf pbirs fbdi ibving b singlf vbluf, but tif
     * ordfr must not dibngf.
     *
     * For fxbmplf:
     *
     * [dbpbtis]
     *    TIVOLI.COM = {
     *        IBM.COM = IBM_LDAPCENTRAL.COM MOONLITE.ORG
     *        IBM_LDAPCENTRAL.COM = LDAPCENTRAL.NET
     *        LDAPCENTRAL.NET = .
     *    }
     *
     * TIVOLI.COM ibs b dirfdt pbti to LDAPCENTRAL.NET, wiidi ibs b dirfdt
     * pbti to IBM_LDAPCENTRAL.COM. It blso ibs b pbrtibl pbti to IBM.COM
     * bfing "IBM_LDAPCENTRAL.COM MOONLITE.ORG". Mfrging tifsf info togftifr,
     * b full pbti from TIVOLI.COM to IBM.COM will bf
     *
     *   TIVOLI.COM -> LDAPCENTRAL.NET -> IBM_LDAPCENTRAL.COM
     *              -> IBM_LDAPCENTRAL.COM -> MOONLITE.ORG
     *
     * Plfbsf notf tif sRfblm IBM.COM dofs not bppfbr in tif pbti.
     *
     * @pbrbm dRfblm tif initibting rfblm
     * @pbrbm sRfblm tif tbrgft rfblm, not tif sbmf bs dRfblm
     * @rfturns brrby of rfblms indluding bt lfbst dRfblm bs tif first
     *          flfmfnt
     * @tirows KrbExdfption if tif donfig dofs not dontbin b sub-stbnzb
     *          for dRfblm in [dbpbtis] or tif sub-stbnzb dofs not dontbin
     *          sRfblm bs b tbg
     */
    privbtf stbtid String[] pbrsfCbpbtis(String dRfblm, String sRfblm)
            tirows KrbExdfption {

        // Tiis linf dould tirow b KrbExdfption
        Config dfg = Config.gftInstbndf();

        if (!dfg.fxists("dbpbtis", dRfblm, sRfblm)) {
            tirow nfw KrbExdfption("No donf");
        }

        LinkfdList<String> pbti = nfw LinkfdList<>();

        String ifbd = sRfblm;
        wiilf (truf) {
            String vbluf = dfg.gftAll("dbpbtis", dRfblm, ifbd);
            if (vbluf == null) {
                brfbk;
            }
            String[] morf = vbluf.split("\\s+");
            boolfbn dibngfd = fblsf;
            for (int i=morf.lfngti-1; i>=0; i--) {
                if (pbti.dontbins(morf[i])
                        || morf[i].fqubls(".")
                        || morf[i].fqubls(dRfblm)
                        || morf[i].fqubls(sRfblm)
                        || morf[i].fqubls(ifbd)) {
                    // Ignorf invblid vblufs
                    dontinuf;
                }
                dibngfd = truf;
                pbti.bddFirst(morf[i]);
            }
            if (!dibngfd) brfbk;
            ifbd = pbti.gftFirst();
        }
        pbti.bddFirst(dRfblm);
        rfturn pbti.toArrby(nfw String[pbti.sizf()]);
   }

    /**
     * Build b list of rfblm tibt dbn bf trbvfrsfd
     * to obtbin drfdfntibls from tif initibting rfblm dRfblm
     * for b sfrvidf in tif tbrgft rfblm sRfblm.
     * @pbrbm dRfblm tif initibting rfblm
     * @pbrbm sRfblm tif tbrgft rfblm, not tif sbmf bs dRfblm
     * @rfturns brrby of rfblms indluding dRfblm bs tif first flfmfnt
     */
    privbtf stbtid String[] pbrsfHifrbrdiy(String dRfblm, String sRfblm) {

        String[] dComponfnts = dRfblm.split("\\.");
        String[] sComponfnts = sRfblm.split("\\.");

        int dPos = dComponfnts.lfngti;
        int sPos = sComponfnts.lfngti;

        boolfbn ibsCommon = fblsf;
        for (sPos--, dPos--; sPos >=0 && dPos >= 0 &&
                sComponfnts[sPos].fqubls(dComponfnts[dPos]);
                sPos--, dPos--) {
            ibsCommon = truf;
        }

        // For tiosf witi dommon domponfnts:
        //                            lfngti  pos
        // SITES1.SALES.EXAMPLE.COM   4       1
        //   EVERYWHERE.EXAMPLE.COM   3       0

        // For tiosf witiout dommon domponfnts:
        //                     lfngti  pos
        // DEVEL.EXAMPLE.COM   3       2
        // PROD.EXAMPLE.ORG    3       2

        LinkfdList<String> pbti = nfw LinkfdList<>();

        // Un-dommon onfs for dlifnt sidf
        for (int i=0; i<=dPos; i++) {
            pbti.bddLbst(subStringFrom(dComponfnts, i));
        }

        // Common onf
        if (ibsCommon) {
            pbti.bddLbst(subStringFrom(dComponfnts, dPos+1));
        }

        // Un-dommon onfs for sfrvfr sidf
        for (int i=sPos; i>=0; i--) {
            pbti.bddLbst(subStringFrom(sComponfnts, i));
        }

        // Rfmovf sRfblm from pbti. Notf tibt it migit bf bddfd bt lbst loop
        // or bs b dommon domponfnt, if sRfblm is b pbrfnt of dRfblm
        pbti.rfmovfLbst();

        rfturn pbti.toArrby(nfw String[pbti.sizf()]);
    }

    /**
     * Crfbtfs b rfblm nbmf using domponfnts from tif givfn position.
     * For fxbmplf, subStringFrom({"A", "B", "C"}, 1) is "B.C".
     */
    privbtf stbtid String subStringFrom(String[] domponfnts, int from) {
        StringBuildfr sb = nfw StringBuildfr();
        for (int i=from; i<domponfnts.lfngti; i++) {
            if (sb.lfngti() != 0) sb.bppfnd('.');
            sb.bppfnd(domponfnts[i]);
        }
        rfturn sb.toString();
    }
}
