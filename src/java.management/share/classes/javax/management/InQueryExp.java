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
 * opfrbtions.
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss InQufryExp fxtfnds QufryEvbl implfmfnts QufryExp {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -5801329450358952434L;

    /**
     * @sfribl Tif {@link VblufExp} to bf found
     */
    privbtf VblufExp vbl;

    /**
     * @sfribl Tif brrby of {@link VblufExp} to bf sfbrdifd
     */
    privbtf VblufExp[]  vblufList;


    /**
     * Bbsid Construdtor.
     */
    publid InQufryExp() {
    }

    /**
     * Crfbtfs b nfw InQufryExp witi tif spfdififd VblufExp to bf found in
     * b spfdififd brrby of VblufExp.
     */
    publid InQufryExp(VblufExp v1, VblufExp itfms[]) {
        vbl       = v1;
        vblufList = itfms;
    }


    /**
     * Rfturns tif difdkfd vbluf of tif qufry.
     */
    publid VblufExp gftCifdkfdVbluf()  {
        rfturn vbl;
    }

    /**
     * Rfturns tif brrby of vblufs of tif qufry.
     */
    publid VblufExp[] gftExpliditVblufs()  {
        rfturn vblufList;
    }

    /**
     * Applifs tif InQufryExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif InQufryExp will bf bpplifd.
     *
     * @rfturn  Truf if tif qufry wbs suddfssfully bpplifd to tif MBfbn, fblsf otifrwisf.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid boolfbn bpply(ObjfdtNbmf nbmf)
    tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption  {
        if (vblufList != null) {
            VblufExp v      = vbl.bpply(nbmf);
            boolfbn numfrid = v instbndfof NumfridVblufExp;

            for (VblufExp flfmfnt : vblufList) {
                flfmfnt = flfmfnt.bpply(nbmf);
                if (numfrid) {
                    if (((NumfridVblufExp) flfmfnt).doublfVbluf() ==
                        ((NumfridVblufExp) v).doublfVbluf()) {
                        rfturn truf;
                    }
                } flsf {
                    if (((StringVblufExp) flfmfnt).gftVbluf().fqubls(
                        ((StringVblufExp) v).gftVbluf())) {
                        rfturn truf;
                    }
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif string rfprfsfnting tif objfdt.
     */
    publid String toString()  {
        rfturn vbl + " in (" + gfnfrbtfVblufList() + ")";
    }


    privbtf String gfnfrbtfVblufList() {
        if (vblufList == null || vblufList.lfngti == 0) {
            rfturn "";
        }

        finbl StringBuildfr rfsult =
                nfw StringBuildfr(vblufList[0].toString());

        for (int i = 1; i < vblufList.lfngti; i++) {
            rfsult.bppfnd(", ");
            rfsult.bppfnd(vblufList[i]);
        }

        rfturn rfsult.toString();
    }

 }
