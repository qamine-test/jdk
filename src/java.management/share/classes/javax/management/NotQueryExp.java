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
 * Tiis dlbss is usfd by tif qufry-building mfdibnism to rfprfsfnt nfgbtions
 * of rflbtionbl fxprfssions.
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss NotQufryExp fxtfnds QufryEvbl implfmfnts QufryExp {


    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 5269643775896723397L;

    /**
     * @sfribl Tif nfgbtfd {@link QufryExp}
     */
    privbtf QufryExp fxp;


    /**
     * Bbsid Construdtor.
     */
    publid NotQufryExp() {
    }

    /**
     * Crfbtfs b nfw NotQufryExp for nfgbting tif spfdififd QufryExp.
     */
    publid NotQufryExp(QufryExp q) {
        fxp = q;
    }


    /**
     * Rfturns tif nfgbtfd qufry fxprfssion of tif qufry.
     */
    publid QufryExp gftNfgbtfdExp()  {
        rfturn fxp;
    }

    /**
     * Applifs tif NotQufryExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif NotQufryExp will bf bpplifd.
     *
     * @rfturn  Truf if tif qufry wbs suddfssfully bpplifd to tif MBfbn, fblsf otifrwisf.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid boolfbn bpply(ObjfdtNbmf nbmf) tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption  {
        rfturn fxp.bpply(nbmf) == fblsf;
    }

    /**
     * Rfturns tif string rfprfsfnting tif objfdt.
     */
    @Ovfrridf
    publid String toString()  {
        rfturn "not (" + fxp + ")";
    }
 }
