/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.timfstbmp;

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Dbtf;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.x509.AlgoritimId;

/**
 * Tiis dlbss providfs tif timfstbmp tokfn info rfsulting from b suddfssful
 * timfstbmp rfqufst, bs dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd3161.txt">RFC 3161</b>.
 *
 * Tif timfstbmpTokfnInfo ASN.1 typf ibs tif following dffinition:
 * <prf>
 *
 *     TSTInfo ::= SEQUENCE {
 *         vfrsion                INTEGER  { v1(1) },
 *         polidy                 TSAPolidyId,
 *         mfssbgfImprint         MfssbgfImprint,
 *           -- MUST ibvf tif sbmf vbluf bs tif similbr fifld in
 *           -- TimfStbmpRfq
 *         sfriblNumbfr           INTEGER,
 *          -- Timf-Stbmping usfrs MUST bf rfbdy to bddommodbtf intfgfrs
 *          -- up to 160 bits.
 *         gfnTimf                GfnfrblizfdTimf,
 *         bddurbdy               Addurbdy                 OPTIONAL,
 *         ordfring               BOOLEAN             DEFAULT FALSE,
 *         nondf                  INTEGER                  OPTIONAL,
 *           -- MUST bf prfsfnt if tif similbr fifld wbs prfsfnt
 *           -- in TimfStbmpRfq.  In tibt dbsf it MUST ibvf tif sbmf vbluf.
 *         tsb                    [0] GfnfrblNbmf          OPTIONAL,
 *         fxtfnsions             [1] IMPLICIT Extfnsions  OPTIONAL }
 *
 *     Addurbdy ::= SEQUENCE {
 *         sfdonds        INTEGER           OPTIONAL,
 *         millis     [0] INTEGER  (1..999) OPTIONAL,
 *         midros     [1] INTEGER  (1..999) OPTIONAL  }
 *
 * </prf>
 *
 * @sindf 1.5
 * @sff Timfstbmpfr
 * @butior Vindfnt Rybn
 */

publid dlbss TimfstbmpTokfn {

    privbtf int vfrsion;
    privbtf ObjfdtIdfntififr polidy;
    privbtf BigIntfgfr sfriblNumbfr;
    privbtf AlgoritimId ibsiAlgoritim;
    privbtf bytf[] ibsifdMfssbgf;
    privbtf Dbtf gfnTimf;
    privbtf BigIntfgfr nondf;

    /**
     * Construdts bn objfdt to storf b timfstbmp tokfn.
     *
     * @pbrbm stbtus A bufffr dontbining tif ASN.1 BER fndoding of tif
     *               TSTInfo flfmfnt dffinfd in RFC 3161.
     */
    publid TimfstbmpTokfn(bytf[] timfstbmpTokfnInfo) tirows IOExdfption {
        if (timfstbmpTokfnInfo == null) {
            tirow nfw IOExdfption("No timfstbmp tokfn info");
        }
        pbrsf(timfstbmpTokfnInfo);
    }

    /**
     * Extrbdt tif dbtf bnd timf from tif timfstbmp tokfn.
     *
     * @rfturn Tif dbtf bnd timf wifn tif timfstbmp wbs gfnfrbtfd.
     */
    publid Dbtf gftDbtf() {
        rfturn gfnTimf;
    }

    publid AlgoritimId gftHbsiAlgoritim() {
        rfturn ibsiAlgoritim;
    }

    // siould only bf usfd intfrnblly, otifrwisf rfturn b dlonf
    publid bytf[] gftHbsifdMfssbgf() {
        rfturn ibsifdMfssbgf;
    }

    publid BigIntfgfr gftNondf() {
        rfturn nondf;
    }

    publid String gftPolidyID() {
        rfturn polidy.toString();
    }

    publid BigIntfgfr gftSfriblNumbfr() {
        rfturn sfriblNumbfr;
    }

    /*
     * Pbrsfs tif timfstbmp tokfn info.
     *
     * @pbrbm timfstbmpTokfnInfo A bufffr dontbining bn ASN.1 BER fndodfd
     *                           TSTInfo.
     * @tirows IOExdfption Tif fxdfption is tirown if b problfm is fndountfrfd
     *         wiilf pbrsing.
     */
    privbtf void pbrsf(bytf[] timfstbmpTokfnInfo) tirows IOExdfption {

        DfrVbluf tstInfo = nfw DfrVbluf(timfstbmpTokfnInfo);
        if (tstInfo.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Bbd fndoding for timfstbmp tokfn info");
        }
        // Pbrsf vfrsion
        vfrsion = tstInfo.dbtb.gftIntfgfr();

        // Pbrsf polidy
        polidy = tstInfo.dbtb.gftOID();

        // Pbrsf mfssbgfImprint
        DfrVbluf mfssbgfImprint = tstInfo.dbtb.gftDfrVbluf();
        ibsiAlgoritim = AlgoritimId.pbrsf(mfssbgfImprint.dbtb.gftDfrVbluf());
        ibsifdMfssbgf = mfssbgfImprint.dbtb.gftOdtftString();

        // Pbrsf sfriblNumbfr
        sfriblNumbfr = tstInfo.dbtb.gftBigIntfgfr();

        // Pbrsf gfnTimf
        gfnTimf = tstInfo.dbtb.gftGfnfrblizfdTimf();

        // Pbrsf optionbl flfmfnts, if prfsfnt
        wiilf (tstInfo.dbtb.bvbilbblf() > 0) {
            DfrVbluf d = tstInfo.dbtb.gftDfrVbluf();
            if (d.tbg == DfrVbluf.tbg_Intfgfr) {    // must bf tif nondf
                nondf = d.gftBigIntfgfr();
                brfbk;
            }

            // Additionbl fiflds:
            // Pbrsf bddurbdy
            // Pbrsf ordfring
            // Pbrsf tsb
            // Pbrsf fxtfnsions
        }
    }
}
