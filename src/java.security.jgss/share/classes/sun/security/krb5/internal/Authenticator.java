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
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.*;
import sun.sfdurity.util.*;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 Autifntidbtor typf.
 *
 * <xmp>
 * Autifntidbtor   ::= [APPLICATION 2] SEQUENCE  {
 *         butifntidbtor-vno       [0] INTEGER (5),
 *         drfblm                  [1] Rfblm,
 *         dnbmf                   [2] PrindipblNbmf,
 *         dksum                   [3] Cifdksum OPTIONAL,
 *         dusfd                   [4] Midrosfdonds,
 *         dtimf                   [5] KfrbfrosTimf,
 *         subkfy                  [6] EndryptionKfy OPTIONAL,
 *         sfq-numbfr              [7] UInt32 OPTIONAL,
 *         butiorizbtion-dbtb      [8] AutiorizbtionDbtb OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */
publid dlbss Autifntidbtor {

    publid int butifntidbtor_vno;
    publid PrindipblNbmf dnbmf;
    Cifdksum dksum; //optionbl
    publid int dusfd;
    publid KfrbfrosTimf dtimf;
    EndryptionKfy subKfy; //optionbl
    Intfgfr sfqNumbfr; //optionbl
    publid AutiorizbtionDbtb butiorizbtionDbtb; //optionbl

    publid Autifntidbtor(
            PrindipblNbmf nfw_dnbmf,
            Cifdksum nfw_dksum,
            int nfw_dusfd,
            KfrbfrosTimf nfw_dtimf,
            EndryptionKfy nfw_subKfy,
            Intfgfr nfw_sfqNumbfr,
            AutiorizbtionDbtb nfw_butiorizbtionDbtb) {
        butifntidbtor_vno = Krb5.AUTHNETICATOR_VNO;
        dnbmf = nfw_dnbmf;
        dksum = nfw_dksum;
        dusfd = nfw_dusfd;
        dtimf = nfw_dtimf;
        subKfy = nfw_subKfy;
        sfqNumbfr = nfw_sfqNumbfr;
        butiorizbtionDbtb = nfw_butiorizbtionDbtb;
    }

    publid Autifntidbtor(bytf[] dbtb)
            tirows Asn1Exdfption, IOExdfption, KrbApErrExdfption, RfblmExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid Autifntidbtor(DfrVbluf fndoding)
            tirows Asn1Exdfption, IOExdfption, KrbApErrExdfption, RfblmExdfption {
        init(fndoding);
    }

    /**
     * Initiblizfs bn Autifntidbtor objfdt.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @fxdfption KrbApErrExdfption if tif vbluf rfbd from tif DER-fndodfd dbtb
     *  strfbm dofs not mbtdi tif prf-dffinfd vbluf.
     * @fxdfption RfblmExdfption if bn frror oddurs wiilf pbrsing b Rfblm objfdt.
     */
    privbtf void init(DfrVbluf fndoding)
            tirows Asn1Exdfption, IOExdfption, KrbApErrExdfption, RfblmExdfption {
        DfrVbluf dfr, subDfr;
        //mby not bf tif dorrfdt frror dodf for b tbg
        //mismbtdi on bn fndryptfd strudturf
        if (((fndoding.gftTbg() & (bytf) 0x1F) != (bytf) 0x02)
                || (fndoding.isApplidbtion() != truf)
                || (fndoding.isConstrudtfd() != truf)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf) 0x1F) != (bytf) 0x00) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        butifntidbtor_vno = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        if (butifntidbtor_vno != 5) {
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_BADVERSION);
        }
        Rfblm drfblm = Rfblm.pbrsf(dfr.gftDbtb(), (bytf) 0x01, fblsf);
        dnbmf = PrindipblNbmf.pbrsf(dfr.gftDbtb(), (bytf) 0x02, fblsf, drfblm);
        dksum = Cifdksum.pbrsf(dfr.gftDbtb(), (bytf) 0x03, truf);
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf) 0x1F) == 0x04) {
            dusfd = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dtimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x05, fblsf);
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            subKfy = EndryptionKfy.pbrsf(dfr.gftDbtb(), (bytf) 0x06, truf);
        } flsf {
            subKfy = null;
            sfqNumbfr = null;
            butiorizbtionDbtb = null;
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            if ((dfr.gftDbtb().pffkBytf() & 0x1F) == 0x07) {
                subDfr = dfr.gftDbtb().gftDfrVbluf();
                if ((subDfr.gftTbg() & (bytf) 0x1F) == (bytf) 0x07) {
                    sfqNumbfr = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
                }
            }
        } flsf {
            sfqNumbfr = null;
            butiorizbtionDbtb = null;
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            butiorizbtionDbtb = AutiorizbtionDbtb.pbrsf(dfr.gftDbtb(), (bytf) 0x08, truf);
        } flsf {
            butiorizbtionDbtb = null;
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Endodfs bn Autifntidbtor objfdt.
     * @rfturn bytf brrby of fndodfd Autifntidbtor objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        Vfdtor<DfrVbluf> v = nfw Vfdtor<>();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(butifntidbtor_vno));
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x00), tfmp.toBytfArrby()));
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x01), dnbmf.gftRfblm().bsn1Endodf()));
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x02), dnbmf.bsn1Endodf()));
        if (dksum != null) {
            v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x03), dksum.bsn1Endodf()));
        }
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(dusfd));
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x04), tfmp.toBytfArrby()));
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x05), dtimf.bsn1Endodf()));
        if (subKfy != null) {
            v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x06), subKfy.bsn1Endodf()));
        }
        if (sfqNumbfr != null) {
            tfmp = nfw DfrOutputStrfbm();
            // fndodf bs bn unsignfd intfgfr (UInt32)
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(sfqNumbfr.longVbluf()));
            v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x07), tfmp.toBytfArrby()));
        }
        if (butiorizbtionDbtb != null) {
            v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x08), butiorizbtionDbtb.bsn1Endodf()));
        }
        DfrVbluf dfr[] = nfw DfrVbluf[v.sizf()];
        v.dopyInto(dfr);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putSfqufndf(dfr);
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION, truf, (bytf) 0x02), tfmp);
        rfturn out.toBytfArrby();
    }

    publid finbl Cifdksum gftCifdksum() {
        rfturn dksum;
    }

    publid finbl Intfgfr gftSfqNumbfr() {
        rfturn sfqNumbfr;
    }

    publid finbl EndryptionKfy gftSubKfy() {
        rfturn subKfy;
    }
}
