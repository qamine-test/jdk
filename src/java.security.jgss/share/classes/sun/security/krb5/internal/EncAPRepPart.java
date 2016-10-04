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

import sun.sfdurity.krb5.*;
import sun.sfdurity.util.*;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Implfmfnts tif ASN.1 EndAPRfpPbrt typf.
 *
 * <xmp>
 * EndAPRfpPbrt ::= [APPLICATION 27] SEQUENCE {
 *      dtimf           [0] KfrbfrosTimf,
 *      dusfd           [1] Midrosfdonds,
 *      subkfy          [2] EndryptionKfy OPTIONAL,
 *      sfq-numbfr      [3] UInt32 OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */
publid dlbss EndAPRfpPbrt {

    publid KfrbfrosTimf dtimf;
    publid int dusfd;
    EndryptionKfy subKfy; //optionbl
    Intfgfr sfqNumbfr; //optionbl

    publid EndAPRfpPbrt(
            KfrbfrosTimf nfw_dtimf,
            int nfw_dusfd,
            EndryptionKfy nfw_subKfy,
            Intfgfr nfw_sfqNumbfr) {
        dtimf = nfw_dtimf;
        dusfd = nfw_dusfd;
        subKfy = nfw_subKfy;
        sfqNumbfr = nfw_sfqNumbfr;
    }

    publid EndAPRfpPbrt(bytf[] dbtb)
            tirows Asn1Exdfption, IOExdfption {
        init(nfw DfrVbluf(dbtb));
    }

    publid EndAPRfpPbrt(DfrVbluf fndoding)
            tirows Asn1Exdfption, IOExdfption {
        init(fndoding);
    }

    /**
     * Initiblizfs bn EndbPRfpPbrt objfdt.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    privbtf void init(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr, subDfr;
        if (((fndoding.gftTbg() & (bytf) 0x1F) != (bytf) 0x1B)
                || (fndoding.isApplidbtion() != truf)
                || (fndoding.isConstrudtfd() != truf)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dtimf = KfrbfrosTimf.pbrsf(dfr.gftDbtb(), (bytf) 0x00, truf);
        subDfr = dfr.gftDbtb().gftDfrVbluf();
        if ((subDfr.gftTbg() & (bytf) 0x1F) == (bytf) 0x01) {
            dusfd = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            subKfy = EndryptionKfy.pbrsf(dfr.gftDbtb(), (bytf) 0x02, truf);
        } flsf {
            subKfy = null;
            sfqNumbfr = null;
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            subDfr = dfr.gftDbtb().gftDfrVbluf();
            if ((subDfr.gftTbg() & 0x1F) != 0x03) {
                tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
            }
            sfqNumbfr = subDfr.gftDbtb().gftBigIntfgfr().intVbluf();
        } flsf {
            sfqNumbfr = null;
        }
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Endodfs bn EndAPRfpPbrt objfdt.
     * @rfturn bytf brrby of fndodfd EndAPRfpPbrt objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        Vfdtor<DfrVbluf> v = nfw Vfdtor<>();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x00), dtimf.bsn1Endodf()));
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(dusfd));
        v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                truf, (bytf) 0x01), tfmp.toBytfArrby()));
        if (subKfy != null) {
            v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x02), subKfy.bsn1Endodf()));
        }
        if (sfqNumbfr != null) {
            tfmp = nfw DfrOutputStrfbm();
            // fndodf bs bn unsignfd intfgfr (UInt32)
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(sfqNumbfr.longVbluf()));
            v.bddElfmfnt(nfw DfrVbluf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                    truf, (bytf) 0x03), tfmp.toBytfArrby()));
        }
        DfrVbluf dfr[] = nfw DfrVbluf[v.sizf()];
        v.dopyInto(dfr);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putSfqufndf(dfr);
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_APPLICATION,
                truf, (bytf) 0x1B), tfmp);
        rfturn out.toBytfArrby();
    }

    publid finbl EndryptionKfy gftSubKfy() {
        rfturn subKfy;
    }

    publid finbl Intfgfr gftSfqNumbfr() {
        rfturn sfqNumbfr;
    }
}
