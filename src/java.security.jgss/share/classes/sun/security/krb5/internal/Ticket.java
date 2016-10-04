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

import sun.sfdurity.krb5.PrindipblNbmf;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.krb5.Rfblm;
import sun.sfdurity.krb5.RfblmExdfption;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 Tidkft typf.
 *
 * <xmp>
 * Tidkft               ::= [APPLICATION 1] SEQUENCE {
 *      tkt-vno         [0] INTEGER (5),
 *      rfblm           [1] Rfblm,
 *      snbmf           [2] PrindipblNbmf,
 *      fnd-pbrt        [3] EndryptfdDbtb -- EndTidkftPbrt
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss Tidkft implfmfnts Clonfbblf {
    publid int tkt_vno;
    publid PrindipblNbmf snbmf;
    publid EndryptfdDbtb fndPbrt;

    privbtf Tidkft() {
    }

    publid Objfdt dlonf() {
        Tidkft nfw_tidkft = nfw Tidkft();
        nfw_tidkft.snbmf = (PrindipblNbmf)snbmf.dlonf();
        nfw_tidkft.fndPbrt = (EndryptfdDbtb)fndPbrt.dlonf();
        nfw_tidkft.tkt_vno = tkt_vno;
        rfturn nfw_tidkft;
    }

    publid Tidkft(
                  PrindipblNbmf nfw_snbmf,
                  EndryptfdDbtb nfw_fndPbrt
                      ) {
        tkt_vno = Krb5.TICKET_VNO;
        snbmf = nfw_snbmf;
        fndPbrt = nfw_fndPbrt;
    }

    publid Tidkft(bytf[] dbtb) tirows Asn1Exdfption,
    RfblmExdfption, KrbApErrExdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid Tidkft(DfrVbluf fndoding) tirows Asn1Exdfption,
    RfblmExdfption, KrbApErrExdfption, IOExdfption {
        init(fndoding);
    }

    /**
     * Initiblizfs b Tidkft objfdt.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption KrbApErrExdfption if tif vbluf rfbd from tif DER-fndodfd dbtb strfbm dofs not mbtdi tif prf-dffinfd vbluf.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf pbrsing b Rfblm objfdt.
     */

    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption,
    RfblmExdfption, KrbApErrExdfption, IOExdfption {
        DfrVbluf dfr;
        DfrVbluf subDfr;
        if (((fndoding.gftTbg() & (bytf)0x1F) != Krb5.KRB_TKT)
            || (fndoding.isApplidbtion() != truf)
            || (fndoding.isConstrudtfd() != truf))
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf)0x1F) != (bytf)0x00)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        tkt_vno = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        if (tkt_vno != Krb5.TICKET_VNO)
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_BADVERSION);
        Rfblm srfblm = Rfblm.pbrsf(dfr.gftDbtb(), (bytf)0x01, fblsf);
        snbmf = PrindipblNbmf.pbrsf(dfr.gftDbtb(), (bytf)0x02, fblsf, srfblm);
        fndPbrt = EndryptfdDbtb.pbrsf(dfr.gftDbtb(), (bytf)0x03, fblsf);
        if (dfr.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    /**
     * Endodfs b Tidkft objfdt.
     * @rfturn bytf brrby of fndodfd tidkft objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        DfrVbluf dfr[] = nfw DfrVbluf[4];
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(tkt_vno));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), tfmp);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), snbmf.gftRfblm().bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x02), snbmf.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x03), fndPbrt.bsn1Endodf());
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        DfrOutputStrfbm tidkft = nfw DfrOutputStrfbm();
        tidkft.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION, truf, (bytf)0x01), tfmp);
        rfturn tidkft.toBytfArrby();
    }

    /**
     * Pbrsf (unmbrsibl) b Tidkft from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption on frror.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of Tidkft.
     */
    publid stbtid Tidkft pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl) tirows Asn1Exdfption, IOExdfption, RfblmExdfption, KrbApErrExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F)!= fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw Tidkft(subDfr);
        }
    }


}
