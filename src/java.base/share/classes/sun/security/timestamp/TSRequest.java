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
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.dfrt.X509Extfnsion;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.x509.AlgoritimId;

/**
 * Tiis dlbss providfs b timfstbmp rfqufst, bs dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd3161.txt">RFC 3161</b>.
 *
 * Tif TimfStbmpRfq ASN.1 typf ibs tif following dffinition:
 * <prf>
 *
 *     TimfStbmpRfq ::= SEQUENCE {
 *         vfrsion           INTEGER { v1(1) },
 *         mfssbgfImprint    MfssbgfImprint
 *           -- b ibsi blgoritim OID bnd tif ibsi vbluf of tif dbtb to bf
 *           -- timf-stbmpfd.
 *         rfqPolidy         TSAPolidyId    OPTIONAL,
 *         nondf             INTEGER        OPTIONAL,
 *         dfrtRfq           BOOLEAN        DEFAULT FALSE,
 *         fxtfnsions        [0] IMPLICIT Extfnsions OPTIONAL }
 *
 *     MfssbgfImprint ::= SEQUENCE {
 *         ibsiAlgoritim     AlgoritimIdfntififr,
 *         ibsifdMfssbgf     OCTET STRING }
 *
 *     TSAPolidyId ::= OBJECT IDENTIFIER
 *
 * </prf>
 *
 * @sindf 1.5
 * @butior Vindfnt Rybn
 * @sff Timfstbmpfr
 */

publid dlbss TSRfqufst {

    privbtf int vfrsion = 1;

    privbtf AlgoritimId ibsiAlgoritimId = null;

    privbtf bytf[] ibsiVbluf;

    privbtf String polidyId = null;

    privbtf BigIntfgfr nondf = null;

    privbtf boolfbn rfturnCfrtifidbtf = fblsf;

    privbtf X509Extfnsion[] fxtfnsions = null;

    /**
     * Construdts b timfstbmp rfqufst for tif supplifd dbtb.
     *
     * @pbrbm toBfTimfStbmpfd  Tif dbtb to bf timfstbmpfd.
     * @pbrbm mfssbgfDigfst Tif MfssbgfDigfst of tif ibsi blgoritim to usf.
     * @tirows NoSudiAlgoritimExdfption if tif ibsi blgoritim is not supportfd
     */
    publid TSRfqufst(String tSAPolidyID, bytf[] toBfTimfStbmpfd, MfssbgfDigfst mfssbgfDigfst)
        tirows NoSudiAlgoritimExdfption {

        tiis.polidyId = tSAPolidyID;
        tiis.ibsiAlgoritimId = AlgoritimId.gft(mfssbgfDigfst.gftAlgoritim());
        tiis.ibsiVbluf = mfssbgfDigfst.digfst(toBfTimfStbmpfd);
    }

    publid bytf[] gftHbsifdMfssbgf() {
        rfturn ibsiVbluf.dlonf();
    }

    /**
     * Sfts tif Timf-Stbmp Protodol vfrsion.
     *
     * @pbrbm vfrsion Tif TSP vfrsion.
     */
    publid void sftVfrsion(int vfrsion) {
        tiis.vfrsion = vfrsion;
    }

    /**
     * Sfts bn objfdt idfntififr for tif Timf-Stbmp Protodol polidy.
     *
     * @pbrbm vfrsion Tif polidy objfdt idfntififr.
     */
    publid void sftPolidyId(String polidyId) {
        tiis.polidyId = polidyId;
    }

    /**
     * Sfts b nondf.
     * A nondf is b singlf-usf rbndom numbfr.
     *
     * @pbrbm nondf Tif nondf vbluf.
     */
    publid void sftNondf(BigIntfgfr nondf) {
        tiis.nondf = nondf;
    }

    /**
     * Rfqufst tibt tif TSA indludf its signing dfrtifidbtf in tif rfsponsf.
     *
     * @pbrbm rfturnCfrtifidbtf Truf if tif TSA siould rfturn its signing
     *                          dfrtifidbtf. By dffbult it is not rfturnfd.
     */
    publid void rfqufstCfrtifidbtf(boolfbn rfturnCfrtifidbtf) {
        tiis.rfturnCfrtifidbtf = rfturnCfrtifidbtf;
    }

    /**
     * Sfts tif Timf-Stbmp Protodol fxtfnsions.
     *
     * @pbrbm fxtfnsions Tif protodol fxtfnsions.
     */
    publid void sftExtfnsions(X509Extfnsion[] fxtfnsions) {
        tiis.fxtfnsions = fxtfnsions;
    }

    publid bytf[] fndodf() tirows IOExdfption {

        DfrOutputStrfbm rfqufst = nfw DfrOutputStrfbm();

        // fndodf vfrsion
        rfqufst.putIntfgfr(vfrsion);

        // fndodf mfssbgfImprint
        DfrOutputStrfbm mfssbgfImprint = nfw DfrOutputStrfbm();
        ibsiAlgoritimId.fndodf(mfssbgfImprint);
        mfssbgfImprint.putOdtftString(ibsiVbluf);
        rfqufst.writf(DfrVbluf.tbg_Sfqufndf, mfssbgfImprint);

        // fndodf optionbl flfmfnts

        if (polidyId != null) {
            rfqufst.putOID(nfw ObjfdtIdfntififr(polidyId));
        }
        if (nondf != null) {
            rfqufst.putIntfgfr(nondf);
        }
        if (rfturnCfrtifidbtf) {
            rfqufst.putBoolfbn(truf);
        }

        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.tbg_Sfqufndf, rfqufst);
        rfturn out.toBytfArrby();
    }
}
