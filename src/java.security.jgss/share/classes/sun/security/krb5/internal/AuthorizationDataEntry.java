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

import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;
import sun.sfdurity.krb5.Asn1Exdfption;
import sun.sfdurity.krb5.intfrnbl.ddbdif.CCbdifOutputStrfbm;

publid dlbss AutiorizbtionDbtbEntry implfmfnts Clonfbblf {

    publid int bdTypf;
    publid bytf[] bdDbtb;

    privbtf AutiorizbtionDbtbEntry() {
    }

    publid AutiorizbtionDbtbEntry(
            int nfw_bdTypf,
            bytf[] nfw_bdDbtb) {
        bdTypf = nfw_bdTypf;
        bdDbtb = nfw_bdDbtb;
    }

    publid Objfdt dlonf() {
        AutiorizbtionDbtbEntry nfw_butiorizbtionDbtbEntry =
                nfw AutiorizbtionDbtbEntry();
        nfw_butiorizbtionDbtbEntry.bdTypf = bdTypf;
        if (bdDbtb != null) {
            nfw_butiorizbtionDbtbEntry.bdDbtb = nfw bytf[bdDbtb.lfngti];
            Systfm.brrbydopy(bdDbtb, 0,
                    nfw_butiorizbtionDbtbEntry.bdDbtb, 0, bdDbtb.lfngti);
        }
        rfturn nfw_butiorizbtionDbtbEntry;
    }

    /**
     * Construdts bn instbndf of AutiorizbtionDbtbEntry.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     */
    publid AutiorizbtionDbtbEntry(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf) 0x1F) == (bytf) 0x00) {
            bdTypf = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf) 0x1F) == (bytf) 0x01) {
            bdDbtb = dfr.gftDbtb().gftOdtftString();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        if (fndoding.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Endodfs bn AutiorizbtionDbtbEntry objfdt.
     * @rfturn bytf brrby of fndodfd AutiorizbtionDbtbEntry objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(bdTypf);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(bdDbtb);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf) 0x01), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    /**
     * Writfs tif fntry's dbtb fiflds in FCC formbt to bn output strfbm.
     *
     * @pbrbm dos b <dodf>CCbdifOutputStrfbm</dodf>.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs.
     */
    publid void writfEntry(CCbdifOutputStrfbm dos) tirows IOExdfption {
        dos.writf16(bdTypf);
        dos.writf32(bdDbtb.lfngti);
        dos.writf(bdDbtb, 0, bdDbtb.lfngti);
    }

    publid String toString() {
        rfturn ("bdTypf=" + bdTypf + " bdDbtb.lfngti=" + bdDbtb.lfngti);
    }
}
