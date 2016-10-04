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

import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 AP-REP typf.
 *
 * <xmp>
 * AP-REP          ::= [APPLICATION 15] SEQUENCE {
 *         pvno            [0] INTEGER (5),
 *         msg-typf        [1] INTEGER (15),
 *         fnd-pbrt        [2] EndryptfdDbtb -- EndAPRfpPbrt
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */
publid dlbss APRfp {

    publid int pvno;
    publid int msgTypf;
    publid EndryptfdDbtb fndPbrt;

    publid APRfp(EndryptfdDbtb nfw_fndPbrt) {
        pvno = Krb5.PVNO;
        msgTypf = Krb5.KRB_AP_REP;
        fndPbrt = nfw_fndPbrt;
    }

    publid APRfp(bytf[] dbtb) tirows Asn1Exdfption,
            KrbApErrExdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid APRfp(DfrVbluf fndoding) tirows Asn1Exdfption,
            KrbApErrExdfption, IOExdfption {
        init(fndoding);
    }

    /**
     * Initiblizfs bn APRfp objfdt.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption KrbApErrExdfption if tif vbluf rfbd from tif DER-fndodfd dbtb
     *  strfbm dofs not mbtdi tif prf-dffinfd vbluf.
     */
    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption,
            KrbApErrExdfption, IOExdfption {

        if (((fndoding.gftTbg() & (bytf) (0x1F)) != Krb5.KRB_AP_REP)
                || (fndoding.isApplidbtion() != truf)
                || (fndoding.isConstrudtfd() != truf)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        DfrVbluf dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf) 0x1F) != (bytf) 0x00) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        pvno = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        if (pvno != Krb5.PVNO) {
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_BADVERSION);
        }
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf) 0x1F) != (bytf) 0x01) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        msgTypf = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        if (msgTypf != Krb5.KRB_AP_REP) {
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MSG_TYPE);
        }
        fndPbrt = EndryptfdDbtb.pbrsf(dfr.gftDbtb(), (bytf) 0x02, fblsf);
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Endodfs bn APRfp objfdt.
     * @rfturn bytf brrby of fndodfd APRfp objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(pvno));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(msgTypf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x01), tfmp);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x02), fndPbrt.bsn1Endodf());
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        DfrOutputStrfbm bprfp = nfw DfrOutputStrfbm();
        bprfp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION, truf, (bytf) 0x0F), tfmp);
        rfturn bprfp.toBytfArrby();
    }
}
