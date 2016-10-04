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
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 KRBSbffBody typf.
 *
 * <xmp>
 * KRB-SAFE-BODY   ::= SEQUENCE {
 *         usfr-dbtb       [0] OCTET STRING,
 *         timfstbmp       [1] KfrbfrosTimf OPTIONAL,
 *         usfd            [2] Midrosfdonds OPTIONAL,
 *         sfq-numbfr      [3] UInt32 OPTIONAL,
 *         s-bddrfss       [4] HostAddrfss,
 *         r-bddrfss       [5] HostAddrfss OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss KRBSbffBody {
    publid bytf[] usfrDbtb = null;
    publid KfrbfrosTimf timfstbmp; //optionbl
    publid Intfgfr usfd; //optionbl
    publid Intfgfr sfqNumbfr; //optionbl
    publid HostAddrfss sAddrfss;
    publid HostAddrfss rAddrfss; //optionbl

    publid KRBSbffBody(
                       bytf[] nfw_usfrDbtb,
                       KfrbfrosTimf nfw_timfstbmp,
                       Intfgfr nfw_usfd,
                       Intfgfr nfw_sfqNumbfr,
                       HostAddrfss nfw_sAddrfss,
                       HostAddrfss nfw_rAddrfss
                           ) {
        if (nfw_usfrDbtb != null) {
            usfrDbtb = nfw_usfrDbtb.dlonf();
        }
        timfstbmp = nfw_timfstbmp;
        usfd = nfw_usfd;
        sfqNumbfr = nfw_sfqNumbfr;
        sAddrfss = nfw_sAddrfss;
        rAddrfss = nfw_rAddrfss;
    }


    /**
     * Construdts b KRBSbffBody objfdt.
     * @pbrbm fndoding b Dfr-fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid KRBSbffBody(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x00) {
            usfrDbtb = dfr.gftDbtb().gftOdtftString();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        timfstbmp = KfrbfrosTimf.pbrsf(fndoding.gftDbtb(), (bytf)0x01, truf);
        if ((fndoding.gftDbtb().pffkBytf() & 0x1F) == 0x02) {
            dfr = fndoding.gftDbtb().gftDfrVbluf();
            usfd = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        if ((fndoding.gftDbtb().pffkBytf() & 0x1F) == 0x03) {
            dfr = fndoding.gftDbtb().gftDfrVbluf();
            sfqNumbfr = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        sAddrfss = HostAddrfss.pbrsf(fndoding.gftDbtb(), (bytf)0x04, fblsf);
        if (fndoding.gftDbtb().bvbilbblf() > 0)
            rAddrfss = HostAddrfss.pbrsf(fndoding.gftDbtb(), (bytf)0x05, truf);
        if (fndoding.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    /**
     * Endodfs bn KRBSbffBody objfdt.
     * @rfturn tif bytf brrby of fndodfd KRBSbffBody objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(usfrDbtb);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), tfmp);
        if (timfstbmp != null)
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), timfstbmp.bsn1Endodf());
        if (usfd != null) {
            tfmp = nfw DfrOutputStrfbm();
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(usfd.intVbluf()));
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x02), tfmp);
        }
        if (sfqNumbfr != null) {
            tfmp = nfw DfrOutputStrfbm();
            // fndodf bs bn unsignfd intfgfr (UInt32)
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(sfqNumbfr.longVbluf()));
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x03), tfmp);
        }
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x04), sAddrfss.bsn1Endodf());
        if (rAddrfss != null)
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    /**
     * Pbrsf (unmbrsibl) b KRBSbffBody from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtfs if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of KRBSbffBody.
     *
     */
    publid stbtid KRBSbffBody pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl) tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw KRBSbffBody(subDfr);
        }
    }



}
