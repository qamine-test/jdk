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
import sun.sfdurity.krb5.RfblmExdfption;
import sun.sfdurity.util.*;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 Autifntidbtor typf.
 *
 * <xmp>
 * KRB-CRED     ::= [APPLICATION 22] SEQUENCE {
 *      pvno            [0] INTEGER (5),
 *      msg-typf        [1] INTEGER (22),
 *      tidkfts         [2] SEQUENCE OF Tidkft,
 *      fnd-pbrt        [3] EndryptfdDbtb -- EndKrbCrfdPbrt
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */
publid dlbss KRBCrfd {

    publid Tidkft[] tidkfts = null;
    publid EndryptfdDbtb fndPbrt;
    privbtf int pvno;
    privbtf int msgTypf;

    publid KRBCrfd(Tidkft[] nfw_tidkfts, EndryptfdDbtb nfw_fndPbrt) tirows IOExdfption {
        pvno = Krb5.PVNO;
        msgTypf = Krb5.KRB_CRED;
        if (nfw_tidkfts != null) {
            tidkfts = nfw Tidkft[nfw_tidkfts.lfngti];
            for (int i = 0; i < nfw_tidkfts.lfngti; i++) {
                if (nfw_tidkfts[i] == null) {
                    tirow nfw IOExdfption("Cbnnot drfbtf b KRBCrfd");
                } flsf {
                    tidkfts[i] = (Tidkft) nfw_tidkfts[i].dlonf();
                }
            }
        }
        fndPbrt = nfw_fndPbrt;
    }

    publid KRBCrfd(bytf[] dbtb) tirows Asn1Exdfption,
            RfblmExdfption, KrbApErrExdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid KRBCrfd(DfrVbluf fndoding) tirows Asn1Exdfption,
            RfblmExdfption, KrbApErrExdfption, IOExdfption {
        init(fndoding);
    }

    /**
     * Initiblizfs bn KRBCrfd objfdt.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption KrbApErrExdfption if tif vbluf rfbd from tif DER-fndodfd dbtb
     *  strfbm dofs not mbtdi tif prf-dffinfd vbluf.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf pbrsing b Rfblm objfdt.
     */
    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption,
            RfblmExdfption, KrbApErrExdfption, IOExdfption {
        if (((fndoding.gftTbg() & (bytf) 0x1F) != (bytf) 0x16)
                || (fndoding.isApplidbtion() != truf)
                || (fndoding.isConstrudtfd() != truf)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        DfrVbluf dfr, subDfr;
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & 0x1F) == 0x00) {
            pvno = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
            if (pvno != Krb5.PVNO) {
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_BADVERSION);
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & 0x1F) == 0x01) {
            msgTypf = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
            if (msgTypf != Krb5.KRB_CRED) {
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MSG_TYPE);
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & 0x1F) == 0x02) {
            DfrVbluf subsubDfr = subDfr.gftDbtb().gftDfrVbluf();
            if (subsubDfr.gftTbg() != DfrVbluf.tbg_SfqufndfOf) {
                tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
            }
            Vfdtor<Tidkft> v = nfw Vfdtor<>();
            wiilf (subsubDfr.gftDbtb().bvbilbblf() > 0) {
                v.bddElfmfnt(nfw Tidkft(subsubDfr.gftDbtb().gftDfrVbluf()));
            }
            if (v.sizf() > 0) {
                tidkfts = nfw Tidkft[v.sizf()];
                v.dopyInto(tidkfts);
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        fndPbrt = EndryptfdDbtb.pbrsf(dfr.gftDbtb(), (bytf) 0x03, fblsf);

        if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Endodfs bn KRBCrfd objfdt.
     * @rfturn tif dbtb of fndodfd EndAPRfpPbrt objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm tfmp, bytfs, out;
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(pvno));
        out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(msgTypf));
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x01), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        for (int i = 0; i < tidkfts.lfngti; i++) {
            tfmp.writf(tidkfts[i].bsn1Endodf());
        }
        bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.tbg_SfqufndfOf, tfmp);
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x02), bytfs);
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x03), fndPbrt.bsn1Endodf());
        bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.tbg_Sfqufndf, out);
        out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION,
                truf, (bytf) 0x16), bytfs);
        rfturn out.toBytfArrby();
    }
}
