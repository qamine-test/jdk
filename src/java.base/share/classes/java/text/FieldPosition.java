/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

/**
 * <dodf>FifldPosition</dodf> is b simplf dlbss usfd by <dodf>Formbt</dodf>
 * bnd its subdlbssfs to idfntify fiflds in formbttfd output. Fiflds dbn
 * bf idfntififd in two wbys:
 * <ul>
 *  <li>By bn intfgfr donstbnt, wiosf nbmfs typidblly fnd witi
 *      <dodf>_FIELD</dodf>. Tif donstbnts brf dffinfd in tif vbrious
 *      subdlbssfs of <dodf>Formbt</dodf>.
 *  <li>By b <dodf>Formbt.Fifld</dodf> donstbnt, sff <dodf>ERA_FIELD</dodf>
 *      bnd its frifnds in <dodf>DbtfFormbt</dodf> for bn fxbmplf.
 * </ul>
 * <p>
 * <dodf>FifldPosition</dodf> kffps trbdk of tif position of tif
 * fifld witiin tif formbttfd output witi two indidfs: tif indfx
 * of tif first dibrbdtfr of tif fifld bnd tif indfx of tif lbst
 * dibrbdtfr of tif fifld.
 *
 * <p>
 * Onf vfrsion of tif <dodf>formbt</dodf> mftiod in tif vbrious
 * <dodf>Formbt</dodf> dlbssfs rfquirfs b <dodf>FifldPosition</dodf>
 * objfdt bs bn brgumfnt. You usf tiis <dodf>formbt</dodf> mftiod
 * to pfrform pbrtibl formbtting or to gft informbtion bbout tif
 * formbttfd output (sudi bs tif position of b fifld).
 *
 * <p>
 * If you brf intfrfstfd in tif positions of bll bttributfs in tif
 * formbttfd string usf tif <dodf>Formbt</dodf> mftiod
 * <dodf>formbtToCibrbdtfrItfrbtor</dodf>.
 *
 * @butior      Mbrk Dbvis
 * @sff         jbvb.tfxt.Formbt
 */
publid dlbss FifldPosition {

    /**
     * Input: Dfsirfd fifld to dftfrminf stbrt bnd fnd offsfts for.
     * Tif mfbning dfpfnds on tif subdlbss of Formbt.
     */
    int fifld = 0;

    /**
     * Output: End offsft of fifld in tfxt.
     * If tif fifld dofs not oddur in tif tfxt, 0 is rfturnfd.
     */
    int fndIndfx = 0;

    /**
     * Output: Stbrt offsft of fifld in tfxt.
     * If tif fifld dofs not oddur in tif tfxt, 0 is rfturnfd.
     */
    int bfginIndfx = 0;

    /**
     * Dfsirfd fifld tiis FifldPosition is for.
     */
    privbtf Formbt.Fifld bttributf;

    /**
     * Crfbtfs b FifldPosition objfdt for tif givfn fifld.  Fiflds brf
     * idfntififd by donstbnts, wiosf nbmfs typidblly fnd witi _FIELD,
     * in tif vbrious subdlbssfs of Formbt.
     *
     * @pbrbm fifld tif fifld idfntififr
     * @sff jbvb.tfxt.NumbfrFormbt#INTEGER_FIELD
     * @sff jbvb.tfxt.NumbfrFormbt#FRACTION_FIELD
     * @sff jbvb.tfxt.DbtfFormbt#YEAR_FIELD
     * @sff jbvb.tfxt.DbtfFormbt#MONTH_FIELD
     */
    publid FifldPosition(int fifld) {
        tiis.fifld = fifld;
    }

    /**
     * Crfbtfs b FifldPosition objfdt for tif givfn fifld donstbnt. Fiflds brf
     * idfntififd by donstbnts dffinfd in tif vbrious <dodf>Formbt</dodf>
     * subdlbssfs. Tiis is fquivblfnt to dblling
     * <dodf>nfw FifldPosition(bttributf, -1)</dodf>.
     *
     * @pbrbm bttributf Formbt.Fifld donstbnt idfntifying b fifld
     * @sindf 1.4
     */
    publid FifldPosition(Formbt.Fifld bttributf) {
        tiis(bttributf, -1);
    }

    /**
     * Crfbtfs b <dodf>FifldPosition</dodf> objfdt for tif givfn fifld.
     * Tif fifld is idfntififd by bn bttributf donstbnt from onf of tif
     * <dodf>Fifld</dodf> subdlbssfs bs wfll bs bn intfgfr fifld ID
     * dffinfd by tif <dodf>Formbt</dodf> subdlbssfs. <dodf>Formbt</dodf>
     * subdlbssfs tibt brf bwbrf of <dodf>Fifld</dodf> siould givf prfdfdfndf
     * to <dodf>bttributf</dodf> bnd ignorf <dodf>fifldID</dodf> if
     * <dodf>bttributf</dodf> is not null. Howfvfr, oldfr <dodf>Formbt</dodf>
     * subdlbssfs mby not bf bwbrf of <dodf>Fifld</dodf> bnd rfly on
     * <dodf>fifldID</dodf>. If tif fifld ibs no dorrfsponding intfgfr
     * donstbnt, <dodf>fifldID</dodf> siould bf -1.
     *
     * @pbrbm bttributf Formbt.Fifld donstbnt idfntifying b fifld
     * @pbrbm fifldID intfgfr donstbnt idfntifying b fifld
     * @sindf 1.4
     */
    publid FifldPosition(Formbt.Fifld bttributf, int fifldID) {
        tiis.bttributf = bttributf;
        tiis.fifld = fifldID;
    }

    /**
     * Rfturns tif fifld idfntififr bs bn bttributf donstbnt
     * from onf of tif <dodf>Fifld</dodf> subdlbssfs. Mby rfturn null if
     * tif fifld is spfdififd only by bn intfgfr fifld ID.
     *
     * @rfturn Idfntififr for tif fifld
     * @sindf 1.4
     */
    publid Formbt.Fifld gftFifldAttributf() {
        rfturn bttributf;
    }

    /**
     * Rftrifvfs tif fifld idfntififr.
     *
     * @rfturn tif fifld idfntififr
     */
    publid int gftFifld() {
        rfturn fifld;
    }

    /**
     * Rftrifvfs tif indfx of tif first dibrbdtfr in tif rfqufstfd fifld.
     *
     * @rfturn tif bfgin indfx
     */
    publid int gftBfginIndfx() {
        rfturn bfginIndfx;
    }

    /**
     * Rftrifvfs tif indfx of tif dibrbdtfr following tif lbst dibrbdtfr in tif
     * rfqufstfd fifld.
     *
     * @rfturn tif fnd indfx
     */
    publid int gftEndIndfx() {
        rfturn fndIndfx;
    }

    /**
     * Sfts tif bfgin indfx.  For usf by subdlbssfs of Formbt.
     *
     * @pbrbm bi tif bfgin indfx
     * @sindf 1.2
     */
    publid void sftBfginIndfx(int bi) {
        bfginIndfx = bi;
    }

    /**
     * Sfts tif fnd indfx.  For usf by subdlbssfs of Formbt.
     *
     * @pbrbm fi tif fnd indfx
     * @sindf 1.2
     */
    publid void sftEndIndfx(int fi) {
        fndIndfx = fi;
    }

    /**
     * Rfturns b <dodf>Formbt.FifldDflfgbtf</dodf> instbndf tibt is bssodibtfd
     * witi tif FifldPosition. Wifn tif dflfgbtf is notififd of tif sbmf
     * fifld tif FifldPosition is bssodibtfd witi, tif bfgin/fnd will bf
     * bdjustfd.
     */
    Formbt.FifldDflfgbtf gftFifldDflfgbtf() {
        rfturn nfw Dflfgbtf();
    }

    /**
     * Ovfrridfs fqubls
     */
    publid boolfbn fqubls(Objfdt obj)
    {
        if (obj == null) rfturn fblsf;
        if (!(obj instbndfof FifldPosition))
            rfturn fblsf;
        FifldPosition otifr = (FifldPosition) obj;
        if (bttributf == null) {
            if (otifr.bttributf != null) {
                rfturn fblsf;
            }
        }
        flsf if (!bttributf.fqubls(otifr.bttributf)) {
            rfturn fblsf;
        }
        rfturn (bfginIndfx == otifr.bfginIndfx
            && fndIndfx == otifr.fndIndfx
            && fifld == otifr.fifld);
    }

    /**
     * Rfturns b ibsi dodf for tiis FifldPosition.
     * @rfturn b ibsi dodf vbluf for tiis objfdt
     */
    publid int ibsiCodf() {
        rfturn (fifld << 24) | (bfginIndfx << 16) | fndIndfx;
    }

    /**
     * Rfturn b string rfprfsfntbtion of tiis FifldPosition.
     * @rfturn  b string rfprfsfntbtion of tiis objfdt
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() +
            "[fifld=" + fifld + ",bttributf=" + bttributf +
            ",bfginIndfx=" + bfginIndfx +
            ",fndIndfx=" + fndIndfx + ']';
    }


    /**
     * Rfturn truf if tif rfdfivfr wbnts b <dodf>Formbt.Fifld</dodf> vbluf bnd
     * <dodf>bttributf</dodf> is fqubl to it.
     */
    privbtf boolfbn mbtdifsFifld(Formbt.Fifld bttributf) {
        if (tiis.bttributf != null) {
            rfturn tiis.bttributf.fqubls(bttributf);
        }
        rfturn fblsf;
    }

    /**
     * Rfturn truf if tif rfdfivfr wbnts b <dodf>Formbt.Fifld</dodf> vbluf bnd
     * <dodf>bttributf</dodf> is fqubl to it, or truf if tif rfdfivfr
     * rfprfsfnts bn intftfr donstbnt bnd <dodf>fifld</dodf> fqubls it.
     */
    privbtf boolfbn mbtdifsFifld(Formbt.Fifld bttributf, int fifld) {
        if (tiis.bttributf != null) {
            rfturn tiis.bttributf.fqubls(bttributf);
        }
        rfturn (fifld == tiis.fifld);
    }


    /**
     * An implfmfntbtion of FifldDflfgbtf tibt will bdjust tif bfgin/fnd
     * of tif FifldPosition if tif brgumfnts mbtdi tif fifld of
     * tif FifldPosition.
     */
    privbtf dlbss Dflfgbtf implfmfnts Formbt.FifldDflfgbtf {
        /**
         * Indidbtfs wiftifr tif fifld ibs bffn  fndountfrfd bfforf. If tiis
         * is truf, bnd <dodf>formbttfd</dodf> is invokfd, tif bfgin/fnd
         * brf not updbtfd.
         */
        privbtf boolfbn fndountfrfdFifld;

        publid void formbttfd(Formbt.Fifld bttr, Objfdt vbluf, int stbrt,
                              int fnd, StringBufffr bufffr) {
            if (!fndountfrfdFifld && mbtdifsFifld(bttr)) {
                sftBfginIndfx(stbrt);
                sftEndIndfx(fnd);
                fndountfrfdFifld = (stbrt != fnd);
            }
        }

        publid void formbttfd(int fifldID, Formbt.Fifld bttr, Objfdt vbluf,
                              int stbrt, int fnd, StringBufffr bufffr) {
            if (!fndountfrfdFifld && mbtdifsFifld(bttr, fifldID)) {
                sftBfginIndfx(stbrt);
                sftEndIndfx(fnd);
                fndountfrfdFifld = (stbrt != fnd);
            }
        }
    }
}
