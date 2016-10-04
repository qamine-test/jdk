/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.io.*;
import sun.sfdurity.x509.AlgoritimId;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss implfmfnts tif <dodf>EndryptfdPrivbtfKfyInfo</dodf> typf,
 * wiidi is dffinfd in PKCS #8 bs follows:
 *
 * <prf>
 * EndryptfdPrivbtfKfyInfo ::=  SEQUENCE {
 *     fndryptionAlgoritim   AlgoritimIdfntififr,
 *     fndryptfdDbtb   OCTET STRING }
 * </prf>
 *
 * @butior Jbn Lufif
 */
finbl dlbss EndryptfdPrivbtfKfyInfo {

    // tif "fndryptionAlgoritim" fifld
    privbtf AlgoritimId blgid;

    // tif "fndryptfdDbtb" fifld
    privbtf bytf[] fndryptfdDbtb;

    // tif ASN.1 fndodfd dontfnts of tiis dlbss
    privbtf bytf[] fndodfd;

    /**
     * Construdts (i.f., pbrsfs) bn <dodf>EndryptfdPrivbtfKfyInfo</dodf> from
     * its fndoding.
     */
    EndryptfdPrivbtfKfyInfo(bytf[] fndodfd) tirows IOExdfption {
        DfrVbluf vbl = nfw DfrVbluf(fndodfd);

        DfrVbluf[] sfq = nfw DfrVbluf[2];

        sfq[0] = vbl.dbtb.gftDfrVbluf();
        sfq[1] = vbl.dbtb.gftDfrVbluf();

        if (vbl.dbtb.bvbilbblf() != 0) {
            tirow nfw IOExdfption("ovfrrun, bytfs = " + vbl.dbtb.bvbilbblf());
        }

        tiis.blgid = AlgoritimId.pbrsf(sfq[0]);
        if (sfq[0].dbtb.bvbilbblf() != 0) {
            tirow nfw IOExdfption("fndryptionAlgoritim fifld ovfrrun");
        }

        tiis.fndryptfdDbtb = sfq[1].gftOdtftString();
        if (sfq[1].dbtb.bvbilbblf() != 0)
            tirow nfw IOExdfption("fndryptfdDbtb fifld ovfrrun");

        tiis.fndodfd = fndodfd.dlonf();
    }

    /**
     * Construdts bn <dodf>EndryptfdPrivbtfKfyInfo</dodf> from tif
     * fndryption blgoritim bnd tif fndryptfd dbtb.
     */
    EndryptfdPrivbtfKfyInfo(AlgoritimId blgid, bytf[] fndryptfdDbtb) {
        tiis.blgid = blgid;
        tiis.fndryptfdDbtb = fndryptfdDbtb.dlonf();
        tiis.fndodfd = null; // lbzy gfnfrbtion of fndoding
    }

    /**
     * Rfturns tif fndryption blgoritim.
     */
    AlgoritimId gftAlgoritim() {
        rfturn tiis.blgid;
    }

    /**
     * Rfturns tif fndryptfd dbtb.
     */
    bytf[] gftEndryptfdDbtb() {
        rfturn tiis.fndryptfdDbtb.dlonf();
    }

    /**
     * Rfturns tif ASN.1 fndoding of tiis dlbss.
     */
    bytf[] gftEndodfd()
        tirows IOExdfption
    {
        if (tiis.fndodfd != null) rfturn tiis.fndodfd.dlonf();

        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

        // fndodf fndryption blgoritim
        blgid.fndodf(tmp);

        // fndodf fndryptfd dbtb
        tmp.putOdtftString(fndryptfdDbtb);

        // wrbp fvfrytiing into b SEQUENCE
        out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
        tiis.fndodfd = out.toBytfArrby();

        rfturn tiis.fndodfd.dlonf();
    }
}
