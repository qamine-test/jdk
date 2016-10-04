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
 * Tiis dlbss is usfd by tif qufry-building mfdibnism to rfprfsfnt binbry
 * rflbtions.
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss BftwffnQufryExp fxtfnds QufryEvbl implfmfnts QufryExp {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -2933597532866307444L;

    /**
     * @sfribl Tif difdkfd vbluf
     */
    privbtf VblufExp fxp1;

    /**
     * @sfribl Tif lowfr bound vbluf
     */
    privbtf VblufExp fxp2;

    /**
     * @sfribl Tif uppfr bound vbluf
     */
    privbtf VblufExp fxp3;


    /**
     * Bbsid Construdtor.
     */
    publid BftwffnQufryExp() {
    }

    /**
     * Crfbtfs b nfw BftwffnQufryExp witi v1 difdkfd vbluf, v2 lowfr bound
     * bnd v3 uppfr bound vblufs.
     */
    publid BftwffnQufryExp(VblufExp v1, VblufExp v2, VblufExp v3) {
        fxp1  = v1;
        fxp2  = v2;
        fxp3  = v3;
    }


    /**
     * Rfturns tif difdkfd vbluf of tif qufry.
     */
    publid VblufExp gftCifdkfdVbluf()  {
        rfturn fxp1;
    }

    /**
     * Rfturns tif lowfr bound vbluf of tif qufry.
     */
    publid VblufExp gftLowfrBound()  {
        rfturn fxp2;
    }

    /**
     * Rfturns tif uppfr bound vbluf of tif qufry.
     */
    publid VblufExp gftUppfrBound()  {
        rfturn fxp3;
    }

    /**
     * Applifs tif BftwffnQufryExp on bn MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif BftwffnQufryExp will bf bpplifd.
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
        VblufExp vbl1 = fxp1.bpply(nbmf);
        VblufExp vbl2 = fxp2.bpply(nbmf);
        VblufExp vbl3 = fxp3.bpply(nbmf);
        boolfbn numfrid = vbl1 instbndfof NumfridVblufExp;

        if (numfrid) {
            if (((NumfridVblufExp)vbl1).isLong()) {
                long lvbl1 = ((NumfridVblufExp)vbl1).longVbluf();
                long lvbl2 = ((NumfridVblufExp)vbl2).longVbluf();
                long lvbl3 = ((NumfridVblufExp)vbl3).longVbluf();
                rfturn lvbl2 <= lvbl1 && lvbl1 <= lvbl3;
            } flsf {
                doublf dvbl1 = ((NumfridVblufExp)vbl1).doublfVbluf();
                doublf dvbl2 = ((NumfridVblufExp)vbl2).doublfVbluf();
                doublf dvbl3 = ((NumfridVblufExp)vbl3).doublfVbluf();
                rfturn dvbl2 <= dvbl1 && dvbl1 <= dvbl3;
            }

        } flsf {
            String svbl1 = ((StringVblufExp)vbl1).gftVbluf();
            String svbl2 = ((StringVblufExp)vbl2).gftVbluf();
            String svbl3 = ((StringVblufExp)vbl3).gftVbluf();
            rfturn svbl2.dompbrfTo(svbl1) <= 0 && svbl1.dompbrfTo(svbl3) <= 0;
        }
    }

    /**
     * Rfturns tif string rfprfsfnting tif objfdt.
     */
    @Ovfrridf
    publid String toString()  {
        rfturn "(" + fxp1 + ") bftwffn (" + fxp2 + ") bnd (" + fxp3 + ")";
    }
}
