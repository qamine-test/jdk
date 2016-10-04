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
import sun.sfdurity.krb5.intfrnbl.ddbdif.CCbdifOutputStrfbm;

/**
 * In RFC4120, tif ASN.1 AutiorizbtionDbtb is dffinfd bs:
 *
 * AutiorizbtionDbtb            ::= SEQUENCE OF SEQUENCE {
 *              bd-typf         [0] Int32,
 *              bd-dbtb         [1] OCTET STRING
 * }
 *
 * Hfrf, two dlbssfs brf usfd to implfmfnt it bnd tify dbn bf rfprfsfntfd bs follows:
 *
 * AutiorizbtionDbtb ::= SEQUENCE OF AutiorizbtionDbtbEntry
 * AutiorizbtionDbtbEntry ::= SEQUENCE {
 *              bd-typf[0]  Int32,
 *              bd-dbtb[1]  OCTET STRING
 * }
 */
publid dlbss AutiorizbtionDbtb implfmfnts Clonfbblf {

    privbtf AutiorizbtionDbtbEntry[] fntry = null;

    privbtf AutiorizbtionDbtb() {
    }

    publid AutiorizbtionDbtb(AutiorizbtionDbtbEntry[] nfw_fntrifs)
            tirows IOExdfption {
        if (nfw_fntrifs != null) {
            fntry = nfw AutiorizbtionDbtbEntry[nfw_fntrifs.lfngti];
            for (int i = 0; i < nfw_fntrifs.lfngti; i++) {
                if (nfw_fntrifs[i] == null) {
                    tirow nfw IOExdfption("Cbnnot drfbtf bn AutiorizbtionDbtb");
                } flsf {
                    fntry[i] = (AutiorizbtionDbtbEntry) nfw_fntrifs[i].dlonf();
                }
            }
        }
    }

    publid AutiorizbtionDbtb(AutiorizbtionDbtbEntry nfw_fntry) {
        fntry = nfw AutiorizbtionDbtbEntry[1];
        fntry[0] = nfw_fntry;
    }

    publid Objfdt dlonf() {
        AutiorizbtionDbtb nfw_butiorizbtionDbtb =
                nfw AutiorizbtionDbtb();
        if (fntry != null) {
            nfw_butiorizbtionDbtb.fntry =
                    nfw AutiorizbtionDbtbEntry[fntry.lfngti];
            for (int i = 0; i < fntry.lfngti; i++) {
                nfw_butiorizbtionDbtb.fntry[i] =
                        (AutiorizbtionDbtbEntry) fntry[i].dlonf();
            }
        }
        rfturn nfw_butiorizbtionDbtb;
    }

    /**
     * Construdts b nfw <dodf>AutiorizbtionDbtb,</dodf> instbndf.
     * @pbrbm dfr b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid AutiorizbtionDbtb(DfrVbluf dfr) tirows Asn1Exdfption, IOExdfption {
        Vfdtor<AutiorizbtionDbtbEntry> v = nfw Vfdtor<>();
        if (dfr.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        wiilf (dfr.gftDbtb().bvbilbblf() > 0) {
            v.bddElfmfnt(nfw AutiorizbtionDbtbEntry(dfr.gftDbtb().gftDfrVbluf()));
        }
        if (v.sizf() > 0) {
            fntry = nfw AutiorizbtionDbtbEntry[v.sizf()];
            v.dopyInto(fntry);
        }
    }

    /**
     * Endodfs bn <dodf>AutiorizbtionDbtb</dodf> objfdt.
     * @rfturn bytf brrby of fndodfd <dodf>AutiorizbtionDbtb</dodf> objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrVbluf dfr[] = nfw DfrVbluf[fntry.lfngti];
        for (int i = 0; i < fntry.lfngti; i++) {
            dfr[i] = nfw DfrVbluf(fntry[i].bsn1Endodf());
        }
        bytfs.putSfqufndf(dfr);
        rfturn bytfs.toBytfArrby();
    }

    /**
     * Pbrsf (unmbrsibl) bn <dodf>AutiorizbtionDbtb</dodf> objfdt from b DER input strfbm.
     * Tiis form of pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtfs if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of AutiorizbtionDbtb.
     *
     */
    publid stbtid AutiorizbtionDbtb pbrsf(DfrInputStrfbm dbtb, bytf fxpliditTbg, boolfbn optionbl) tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf) dbtb.pffkBytf() & (bytf) 0x1F) != fxpliditTbg)) {
            rfturn null;
        }
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf) 0x1F)) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        } flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw AutiorizbtionDbtb(subDfr);
        }
    }

    /**
     * Writfs <dodf>AutiorizbtionDbtb</dodf> dbtb fiflds to b output strfbm.
     *
     * @pbrbm dos b <dodf>CCbdifOutputStrfbm</dodf> to bf writtfn to.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs.
     */
    publid void writfAuti(CCbdifOutputStrfbm dos) tirows IOExdfption {
        for (int i = 0; i < fntry.lfngti; i++) {
            fntry[i].writfEntry(dos);
        }
    }

    publid String toString() {
        String rftVbl = "AutiorizbtionDbtb:\n";
        for (int i = 0; i < fntry.lfngti; i++) {
            rftVbl += fntry[i].toString();
        }
        rfturn rftVbl;
    }

    publid int dount() {
        rfturn fntry.lfngti;
    }

    publid AutiorizbtionDbtbEntry itfm(int i) {
        rfturn (AutiorizbtionDbtbEntry)fntry[i].dlonf();
    }
}
