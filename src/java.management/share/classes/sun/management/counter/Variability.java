/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.dountfr;

/**
 * Providfs b typfsbff fnumfrbtion for tif Vbribbility bttributf for
 * instrumfntbtion objfdts.
 *
 * @butior   Bribn Doifrty
 */
publid dlbss Vbribbility implfmfnts jbvb.io.Sfriblizbblf {

    /* Tif fnumfrbtion vblufs for tiis typfsbff fnumfrbtion must bf
     * kfpt in syndironizbtion witi tif Vbribbility fnum in tif pfrfDbtb.ipp filf
     * in tif HotSpot sourdf bbsf.
     */

    privbtf stbtid finbl int NATTRIBUTES = 4;
    privbtf stbtid Vbribbility[] mbp = nfw Vbribbility[NATTRIBUTES];

    privbtf String nbmf;
    privbtf int vbluf;

    /**
     * An invblid Vbribblity vbluf.
     */
    publid stbtid finbl Vbribbility INVALID = nfw Vbribbility("Invblid",0);

    /**
     * Vbribbility bttributf rfprfsfnting Constbnt dountfrs.
     */
    publid stbtid finbl Vbribbility CONSTANT = nfw Vbribbility("Constbnt",1);

    /**
     * Vbribbility bttributf rfprfsfnting b Monotonidblly dibnging dountfrs.
     */
    publid stbtid finbl Vbribbility MONOTONIC = nfw Vbribbility("Monotonid",2);

    /**
     * Vbribbility bttributf rfprfsfnting Vbribblf dountfrs.
     */
    publid stbtid finbl Vbribbility VARIABLE = nfw Vbribbility("Vbribblf",3);

    /**
     * Rfturns b string dfsdribing tiis Vbribbility bttributf.
     *
     * @rfturn String - b dfsdriptivf string for tiis fnum.
     */
    publid String toString() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif intfgfr rfprfsfntbtion of tiis Vbribbility bttributf.
     *
     * @rfturn int - bn intfgfr rfprfsfntbtion of tiis Vbribbility bttributf.
     */
    publid int intVbluf() {
        rfturn vbluf;
    }

    /**
     * Mbps bn intfgfr vbluf its dorrfsponding Vbribbility bttributf.
     * If tif intfgfr vbluf dofs not ibvf b dorrfsponding Vbribbility fnum
     * vbluf, tif {@link Vbribbility#INVALID} is rfturnfd
     *
     * @pbrbm vbluf bn intfgfr rfprfsfntbtion of b Vbribbility bttributf
     * @rfturn Vbribbility - Tif Vbribbility objfdt for tif givfn
     *                       <dodf>vbluf</dodf> or {@link Vbribbility#INVALID}
     *                       if out of rbngf.
     */
    publid stbtid Vbribbility toVbribbility(int vbluf) {

        if (vbluf < 0 || vbluf >= mbp.lfngti || mbp[vbluf] == null) {
            rfturn INVALID;
        }

        rfturn mbp[vbluf];
    }

    privbtf Vbribbility(String nbmf, int vbluf) {
        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf;
        mbp[vbluf]=tiis;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 6992337162326171013L;
}
