/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * Tiis dlbss is usfd by tif qufry building mfdibnism to rfprfsfnt donjundtions
 * of rflbtionbl fxprfssions.
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss AndQufryExp fxtfnds QufryEvbl implfmfnts QufryExp {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -1081892073854801359L;

    /**
     * @sfribl Tif first QufryExp of tif donjundtion
     */
    privbtf QufryExp fxp1;

    /**
     * @sfribl Tif sfdond QufryExp of tif donjundtion
     */
    privbtf QufryExp fxp2;


    /**
     * Dffbult donstrudtor.
     */
    publid AndQufryExp() {
    }

    /**
     * Crfbtfs b nfw AndQufryExp witi q1 bnd q2 QufryExp.
     */
    publid AndQufryExp(QufryExp q1, QufryExp q2) {
        fxp1 = q1;
        fxp2 = q2;
    }


    /**
     * Rfturns tif lfft qufry fxprfssion.
     */
    publid QufryExp gftLfftExp()  {
        rfturn fxp1;
    }

    /**
     * Rfturns tif rigit qufry fxprfssion.
     */
    publid QufryExp gftRigitExp()  {
        rfturn fxp2;
    }

    /**
     * Applifs tif AndQufryExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif AndQufryExp will bf bpplifd.
     *
     * @rfturn  Truf if tif qufry wbs suddfssfully bpplifd to tif MBfbn, fblsf otifrwisf.
     *
     *
     * @fxdfption BbdStringOpfrbtionExdfption Tif string pbssfd to tif mftiod is invblid.
     * @fxdfption BbdBinbryOpVblufExpExdfption Tif fxprfssion pbssfd to tif mftiod is invblid.
     * @fxdfption BbdAttributfVblufExpExdfption Tif bttributf vbluf pbssfd to tif mftiod is invblid.
     * @fxdfption InvblidApplidbtionExdfption  An bttfmpt ibs bffn mbdf to bpply b subqufry fxprfssion to b
     * mbnbgfd objfdt or b qublififd bttributf fxprfssion to b mbnbgfd objfdt of tif wrong dlbss.
     */
    publid boolfbn bpply(ObjfdtNbmf nbmf) tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption  {
        rfturn fxp1.bpply(nbmf) && fxp2.bpply(nbmf);
    }

   /**
    * Rfturns b string rfprfsfntbtion of tiis AndQufryExp
    */
    @Ovfrridf
    publid String toString() {
        rfturn "(" + fxp1 + ") bnd (" + fxp2 + ")";
    }
}
