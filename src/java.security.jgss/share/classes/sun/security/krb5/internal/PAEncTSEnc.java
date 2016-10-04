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
 * Implfmfnts tif ASN.1 PAEndTSEnd typf.
 *
 * <xmp>
 * PA-ENC-TS-ENC                ::= SEQUENCE {
 *      pbtimfstbmp     [0] KfrbfrosTimf -- dlifnt's timf --,
 *      pbusfd          [1] Midrosfdonds OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
 * spfdifidbtion bvbilbblf bt
 * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
 * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
 */

publid dlbss PAEndTSEnd {
    publid KfrbfrosTimf pATimfStbmp;
    publid Intfgfr pAUSfd; //optionbl

    publid PAEndTSEnd(
                      KfrbfrosTimf nfw_pATimfStbmp,
                      Intfgfr nfw_pAUSfd
                          ) {
        pATimfStbmp = nfw_pATimfStbmp;
        pAUSfd = nfw_pAUSfd;
    }

    publid PAEndTSEnd() {
        KfrbfrosTimf now = KfrbfrosTimf.now();
        pATimfStbmp = now;
        pAUSfd = now.gftMidroSfdonds();
    }

    /**
     * Construdts b PAEndTSEnd objfdt.
     * @pbrbm fndoding b Dfr-fndodfd dbtb.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid PAEndTSEnd(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        pATimfStbmp = KfrbfrosTimf.pbrsf(fndoding.gftDbtb(), (bytf)0x00, fblsf);
        if (fndoding.gftDbtb().bvbilbblf() > 0) {
            dfr = fndoding.gftDbtb().gftDfrVbluf();
            if ((dfr.gftTbg() & 0x1F) == 0x01) {
                pAUSfd = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
            }
            flsf tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        if (fndoding.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }


    /**
     * Endodfs b PAEndTSEnd objfdt.
     * @rfturn tif bytf brrby of fndodfd PAEndTSEnd objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), pATimfStbmp.bsn1Endodf());
        if (pAUSfd != null) {
            tfmp = nfw DfrOutputStrfbm();
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(pAUSfd.intVbluf()));
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), tfmp);
        }
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }
}
