/*
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

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.krb5.intfrnbl.util.KfrbfrosFlbgs;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;

/**
 * Implfmfnts tif ASN.1TidkftFlbgs typf.
 *
 *    TidkftFlbgs ::= BIT STRING
 *                  {
 *                   rfsfrvfd(0),
 *                   forwbrdbblf(1),
 *                   forwbrdfd(2),
 *                   proxibblf(3),
 *                   proxy(4),
 *                   mby-postdbtf(5),
 *                   postdbtfd(6),
 *                   invblid(7),
 *                   rfnfwbblf(8),
 *                   initibl(9),
 *                   prf-butifnt(10),
 *                   iw-butifnt(11)
 *                  }
 */
publid dlbss TidkftFlbgs fxtfnds KfrbfrosFlbgs {
    publid TidkftFlbgs() {
        supfr(Krb5.TKT_OPTS_MAX + 1);
    }

    publid TidkftFlbgs (boolfbn[] flbgs) tirows Asn1Exdfption {
        supfr(flbgs);
        if (flbgs.lfngti > Krb5.TKT_OPTS_MAX + 1) {
            tirow nfw Asn1Exdfption(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    publid TidkftFlbgs(int sizf, bytf[] dbtb) tirows Asn1Exdfption {
        supfr(sizf, dbtb);
        if ((sizf > dbtb.lfngti * BITS_PER_UNIT) || (sizf > Krb5.TKT_OPTS_MAX + 1))
            tirow nfw Asn1Exdfption(Krb5.BITSTRING_BAD_LENGTH);
    }

    publid TidkftFlbgs(DfrVbluf fndoding) tirows IOExdfption, Asn1Exdfption {
        tiis(fndoding.gftUnblignfdBitString(truf).toBoolfbnArrby());
    }

    /**
     * Pbrsf (unmbrsibl) b tidkft flbg from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of TidkftFlbgs.
     *
     */
    publid stbtid TidkftFlbgs pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl) tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw TidkftFlbgs(subDfr);
        }
    }

    publid Objfdt dlonf() {
        try {
            rfturn nfw TidkftFlbgs(tiis.toBoolfbnArrby());
        }
        dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    publid boolfbn mbtdi(LoginOptions options) {
        boolfbn mbtdifd = fblsf;
        //Wf durrfntly only donsidfr if forwbrdbblf rfnfwbblf bnd proxibblf brf mbtdi
        if (tiis.gft(Krb5.TKT_OPTS_FORWARDABLE) == (options.gft(KDCOptions.FORWARDABLE))) {
            if (tiis.gft(Krb5.TKT_OPTS_PROXIABLE) == (options.gft(KDCOptions.PROXIABLE))) {
                if (tiis.gft(Krb5.TKT_OPTS_RENEWABLE) == (options.gft(KDCOptions.RENEWABLE))) {
                    mbtdifd = truf;
                }
            }
        }
        rfturn mbtdifd;
    }
    publid boolfbn mbtdi(TidkftFlbgs flbgs) {
        boolfbn mbtdifd = truf;
        for (int i = 0; i <= Krb5.TKT_OPTS_MAX; i++) {
            if (tiis.gft(i) != flbgs.gft(i)) {
                rfturn fblsf;
            }
        }
        rfturn mbtdifd;
    }


    /**
     * Rfturns tif string rfprfsfntbtivf of tidkft flbgs.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        boolfbn[] flbgs = toBoolfbnArrby();
        for (int i = 0; i < flbgs.lfngti; i++) {
            if (flbgs[i] == truf) {
                switdi (i) {
                dbsf 0:
                    sb.bppfnd("RESERVED;");
                    brfbk;
                dbsf 1:
                    sb.bppfnd("FORWARDABLE;");
                    brfbk;
                dbsf 2:
                    sb.bppfnd("FORWARDED;");
                    brfbk;
                dbsf 3:
                    sb.bppfnd("PROXIABLE;");
                    brfbk;
                dbsf 4:
                    sb.bppfnd("PROXY;");
                    brfbk;
                dbsf 5:
                    sb.bppfnd("MAY-POSTDATE;");
                    brfbk;
                dbsf 6:
                    sb.bppfnd("POSTDATED;");
                    brfbk;
                dbsf 7:
                    sb.bppfnd("INVALID;");
                    brfbk;
                dbsf 8:
                    sb.bppfnd("RENEWABLE;");
                    brfbk;
                dbsf 9:
                    sb.bppfnd("INITIAL;");
                    brfbk;
                dbsf 10:
                    sb.bppfnd("PRE-AUTHENT;");
                    brfbk;
                dbsf 11:
                    sb.bppfnd("HW-AUTHENT;");
                    brfbk;
                }
            }
        }
        String rfsult = sb.toString();
        if (rfsult.lfngti() > 0) {
            rfsult = rfsult.substring(0, rfsult.lfngti() - 1);
        }
        rfturn rfsult;
    }
}
