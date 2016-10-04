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

import sun.sfdurity.util.*;
import sun.sfdurity.krb5.Asn1Exdfption;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 TrbnsitfdEndoding typf.
 *
 * <xmp>
 *  TrbnsitfdEndoding      ::= SEQUENCE {
 *         tr-typf         [0] Int32 -- must bf rfgistfrfd --,
 *         dontfnts        [1] OCTET STRING
 *  }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss TrbnsitfdEndoding {
    publid int trTypf;
    publid bytf[] dontfnts;

    publid TrbnsitfdEndoding(int typf, bytf[] dont) {
        trTypf = typf;
        dontfnts = dont;
    }

    /**
     * Construdts b TrbnsitfdEndoding objfdt.
     * @pbrbm fndoding b Dfr-fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */

    publid TrbnsitfdEndoding(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        DfrVbluf dfr;
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x00) {
            trTypf = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        dfr = fndoding.gftDbtb().gftDfrVbluf();

        if ((dfr.gftTbg() & 0x1F) == 0x01) {
            dontfnts = dfr.gftDbtb().gftOdtftString();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        if (dfr.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    /**
     * Endodfs b TrbnsitfdEndoding objfdt.
     * @rfturn tif bytf brrby of tif fndodfd TrbnsitfdEndoding objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(trTypf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(dontfnts);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    /**
     * Pbrsf (unmbrsibl) b TrbnsitfdEndoding objfdt from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of TrbnsitfdEndoding.
     *
     */
    publid stbtid TrbnsitfdEndoding pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl) tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw TrbnsitfdEndoding(subDfr);
        }
    }
}
