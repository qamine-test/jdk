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
 * Implfmfnts tif ASN.1 APOptions typf.
 *
 * <xmp>
 * APOptions    ::= KfrbfrosFlbgs
 *      -- rfsfrvfd(0),
 *      -- usf-sfssion-kfy(1),
 *      -- mutubl-rfquirfd(2)
 * </xmp>
 *
 * KfrbfrosFlbgs   ::= BIT STRING (SIZE (32..MAX))
 *              -- minimum numbfr of bits sibll bf sfnt,
 *              -- but no ffwfr tibn 32
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss APOptions fxtfnds KfrbfrosFlbgs {
    publid APOptions() {
        supfr(Krb5.AP_OPTS_MAX + 1);
    }

    publid APOptions(int onfBit) tirows Asn1Exdfption {
        supfr(Krb5.AP_OPTS_MAX + 1);
        sft(onfBit, truf);
    }
    publid APOptions(int sizf, bytf[] dbtb) tirows Asn1Exdfption {
        supfr(sizf, dbtb);
        if ((sizf > dbtb.lfngti * BITS_PER_UNIT) || (sizf > Krb5.AP_OPTS_MAX + 1)) {
            tirow nfw Asn1Exdfption(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    publid APOptions(boolfbn[] dbtb) tirows Asn1Exdfption {
        supfr(dbtb);
        if (dbtb.lfngti > Krb5.AP_OPTS_MAX + 1) {
            tirow nfw Asn1Exdfption(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    publid APOptions(DfrVbluf fndoding) tirows IOExdfption, Asn1Exdfption {
        tiis(fndoding.gftUnblignfdBitString(truf).toBoolfbnArrby());
    }

    /**
     * Pbrsf (unmbrsibl) bn APOptions from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl.
     * @rfturn bn instbndf of APOptions.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     *
     */
    publid stbtid APOptions pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl) tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        } flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw APOptions(subDfr);
        }
    }
}
