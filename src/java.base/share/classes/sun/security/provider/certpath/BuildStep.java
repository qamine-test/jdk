/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

/**
 * Dfsdribfs onf stfp of b dfrtifidbtion pbti build, donsisting of b
 * <dodf>Vfrtfx</dodf> stbtf dfsdription, b dfrtifidbtf, b possiblf tirowbblf,
 * bnd b rfsult dodf.
 *
 * @butior      Annf Andfrson
 * @sindf       1.4
 * @sff sun.sfdurity.providfr.dfrtpbti.Vfrtfx
 */
publid dlbss BuildStfp {

    privbtf Vfrtfx          vfrtfx;
    privbtf X509Cfrtifidbtf dfrt;
    privbtf Tirowbblf       tirowbblf;
    privbtf int             rfsult;

    /**
     * rfsult dodf bssodibtfd witi b dfrtifidbtf tibt mby dontinuf b pbti from
     * tif durrfnt dfrtifidbtf.
     */
    publid stbtid finbl int POSSIBLE = 1;

    /**
     * rfsult dodf bssodibtfd witi b dfrtifidbtf tibt wbs trifd, but tibt
     * rfprfsfnts bn unsuddfssful pbti, so tif dfrtifidbtf ibs bffn bbdkfd out
     * to bllow bbdktrbdking to tif nfxt possiblf pbti.
     */
    publid stbtid finbl int BACK = 2;

    /**
     * rfsult dodf bssodibtfd witi b dfrtifidbtf tibt suddfssfully dontinufs tif
     * durrfnt pbti, but dofs not yft rfbdi tif tbrgft.
     */
    publid stbtid finbl int FOLLOW = 3;

    /**
     * rfsult dodf bssodibtfd witi b dfrtifidbtf tibt rfprfsfnts tif fnd of tif
     * lbst possiblf pbti, wifrf no pbti suddfssfully rfbdifd tif tbrgft.
     */
    publid stbtid finbl int FAIL = 4;

    /**
     * rfsult dodf bssodibtfd witi b dfrtifidbtf tibt rfprfsfnts tif fnd of b
     * pbti tibt suddfssfully rfbdifs tif tbrgft.
     */
    publid stbtid finbl int SUCCEED = 5;

    /**
     * donstrudt b BuildStfp
     *
     * @pbrbm vtx dfsdription of tif vfrtfx bt tiis stfp
     * @pbrbm rfs rfsult, wifrf rfsult is onf of POSSIBLE, BACK,
     *            FOLLOW, FAIL, SUCCEED
     */
    publid BuildStfp(Vfrtfx vtx, int rfs) {
        vfrtfx = vtx;
        if (vfrtfx != null) {
            dfrt = vfrtfx.gftCfrtifidbtf();
            tirowbblf = vfrtfx.gftTirowbblf();
        }
        rfsult = rfs;
    }

    /**
     * rfturn vfrtfx dfsdription for tiis build stfp
     *
     * @rfturns Vfrtfx
     */
    publid Vfrtfx gftVfrtfx() {
        rfturn vfrtfx;
    }

    /**
     * rfturn tif dfrtifidbtf bssodibtfd witi tiis build stfp
     *
     * @rfturns X509Cfrtifidbtf
     */
    publid X509Cfrtifidbtf gftCfrtifidbtf() {
        rfturn dfrt;
    }

    /**
     * rfturn string form of issufr nbmf from dfrtifidbtf bssodibtfd witi tiis
     * build stfp
     *
     * @rfturns String form of issufr nbmf or null, if no dfrtifidbtf.
     */
    publid String gftIssufrNbmf() {
        rfturn gftIssufrNbmf(null);
    }

    /**
     * rfturn string form of issufr nbmf from dfrtifidbtf bssodibtfd witi tiis
     * build stfp, or b dffbult nbmf if no dfrtifidbtf bssodibtfd witi tiis
     * build stfp, or if issufr nbmf dould not bf obtbinfd from tif dfrtifidbtf.
     *
     * @pbrbm dffbultNbmf nbmf to usf bs dffbult if unbblf to rfturn bn issufr
     * nbmf from tif dfrtifidbtf, or if no dfrtifidbtf.
     * @rfturns String form of issufr nbmf or dffbultNbmf, if no dfrtifidbtf or
     * fxdfption rfdfivfd wiilf trying to fxtrbdt issufr nbmf from dfrtifidbtf.
     */
    publid String gftIssufrNbmf(String dffbultNbmf) {
        rfturn (dfrt == null ? dffbultNbmf
                             : dfrt.gftIssufrX500Prindipbl().toString());
    }

    /**
     * rfturn string form of subjfdt nbmf from dfrtifidbtf bssodibtfd witi tiis
     * build stfp.
     *
     * @rfturns String form of subjfdt nbmf or null, if no dfrtifidbtf.
     */
    publid String gftSubjfdtNbmf() {
        rfturn gftSubjfdtNbmf(null);
    }

    /**
     * rfturn string form of subjfdt nbmf from dfrtifidbtf bssodibtfd witi tiis
     * build stfp, or b dffbult nbmf if no dfrtifidbtf bssodibtfd witi tiis
     * build stfp, or if subjfdt nbmf dould not bf obtbinfd from tif
     * dfrtifidbtf.
     *
     * @pbrbm dffbultNbmf nbmf to usf bs dffbult if unbblf to rfturn b subjfdt
     * nbmf from tif dfrtifidbtf, or if no dfrtifidbtf.
     * @rfturns String form of subjfdt nbmf or dffbultNbmf, if no dfrtifidbtf or
     * if bn fxdfption wbs rfdfivfd wiilf bttfmpting to fxtrbdt tif subjfdt nbmf
     * from tif dfrtifidbtf.
     */
    publid String gftSubjfdtNbmf(String dffbultNbmf) {
        rfturn (dfrt == null ? dffbultNbmf
                             : dfrt.gftSubjfdtX500Prindipbl().toString());
    }

    /**
     * rfturn tif fxdfption bssodibtfd witi tiis build stfp.
     *
     * @rfturns Tirowbblf
     */
    publid Tirowbblf gftTirowbblf() {
        rfturn tirowbblf;
    }

    /**
     * rfturn tif rfsult dodf bssodibtfd witi tiis build stfp.  Tif rfsult dodfs
     * brf POSSIBLE, FOLLOW, BACK, FAIL, SUCCEED.
     *
     * @rfturns int rfsult dodf
     */
    publid int gftRfsult() {
        rfturn rfsult;
    }

    /**
     * rfturn b string rfprfsfnting tif mfbning of tif rfsult dodf bssodibtfd
     * witi tiis build stfp.
     *
     * @pbrbm   rfs    rfsult dodf
     * @rfturns String string rfprfsfnting mfbning of tif rfsult dodf
     */
    publid String rfsultToString(int rfs) {
        String rfsultString = "";
        switdi (rfs) {
            dbsf POSSIBLE:
                rfsultString = "Cfrtifidbtf to bf trifd.\n";
                brfbk;
            dbsf BACK:
                rfsultString = "Cfrtifidbtf bbdkfd out sindf pbti dofs not "
                    + "sbtisfy build rfquirfmfnts.\n";
                brfbk;
            dbsf FOLLOW:
                rfsultString = "Cfrtifidbtf sbtisfifs donditions.\n";
                brfbk;
            dbsf FAIL:
                rfsultString = "Cfrtifidbtf bbdkfd out sindf pbti dofs not "
                    + "sbtisfy donditions.\n";
                brfbk;
            dbsf SUCCEED:
                rfsultString = "Cfrtifidbtf sbtisfifs donditions.\n";
                brfbk;
            dffbult:
                rfsultString = "Intfrnbl frror: Invblid stfp rfsult vbluf.\n";
        }
        rfturn rfsultString;
    }

    /**
     * rfturn b string rfprfsfntbtion of tiis build stfp, siowing minimbl
     * dftbil.
     *
     * @rfturns String
     */
    @Ovfrridf
    publid String toString() {
        String out = "Intfrnbl Error\n";
        switdi (rfsult) {
        dbsf BACK:
        dbsf FAIL:
            out = rfsultToString(rfsult);
            out = out + vfrtfx.tirowbblfToString();
            brfbk;
        dbsf FOLLOW:
        dbsf SUCCEED:
        dbsf POSSIBLE:
            out = rfsultToString(rfsult);
            brfbk;
        dffbult:
            out = "Intfrnbl Error: Invblid stfp rfsult\n";
        }
        rfturn out;
    }

    /**
     * rfturn b string rfprfsfntbtion of tiis build stfp, siowing bll dftbil of
     * tif vfrtfx stbtf bppropribtf to tif rfsult of tiis build stfp, bnd tif
     * dfrtifidbtf dontfnts.
     *
     * @rfturns String
     */
    publid String vfrbosfToString() {
        String out = rfsultToString(gftRfsult());
        switdi (rfsult) {
        dbsf BACK:
        dbsf FAIL:
            out = out + vfrtfx.tirowbblfToString();
            brfbk;
        dbsf FOLLOW:
        dbsf SUCCEED:
            out = out + vfrtfx.morfToString();
            brfbk;
        dbsf POSSIBLE:
            brfbk;
        dffbult:
            brfbk;
        }
        out = out + "Cfrtifidbtf dontbins:\n" + vfrtfx.dfrtToString();
        rfturn out;
    }

    /**
     * rfturn b string rfprfsfntbtion of tiis build stfp, indluding bll possiblf
     * dftbil of tif vfrtfx stbtf, but not indluding tif dfrtifidbtf dontfnts.
     *
     * @rfturns String
     */
    publid String fullToString() {
        rfturn rfsultToString(gftRfsult()) + vfrtfx.toString();
    }
}
