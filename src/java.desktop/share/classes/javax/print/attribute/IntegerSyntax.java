/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;

/**
 * Clbss IntfgfrSyntbx is bn bbstrbdt bbsf dlbss providing tif dommon
 * implfmfntbtion of bll bttributfs witi intfgfr vblufs.
 * <P>
 * Undfr tif iood, bn intfgfr bttributf is just bn intfgfr. You dbn gft bn
 * intfgfr bttributf's intfgfr vbluf by dblling {@link #gftVbluf()
 * gftVbluf()}. An intfgfr bttributf's intfgfr vbluf is
 * fstbblisifd wifn it is donstrudtfd (sff {@link #IntfgfrSyntbx(int)
 * IntfgfrSyntbx(int)}). Ondf donstrudtfd, bn intfgfr bttributf's
 * vbluf is immutbblf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid bbstrbdt dlbss IntfgfrSyntbx implfmfnts Sfriblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 3644574816328081943L;

    /**
     * Tiis intfgfr bttributf's intfgfr vbluf.
     * @sfribl
     */
    privbtf int vbluf;

    /**
     * Construdt b nfw intfgfr bttributf witi tif givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd IntfgfrSyntbx(int vbluf) {
        tiis.vbluf = vbluf;
    }

    /**
     * Construdt b nfw intfgfr bttributf witi tif givfn intfgfr vbluf, wiidi
     * must lif witiin tif givfn rbngf.
     *
     * @pbrbm  vbluf       Intfgfr vbluf.
     * @pbrbm  lowfrBound  Lowfr bound.
     * @pbrbm  uppfrBound  Uppfr bound.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is lfss tibn
     *     <CODE>lowfrBound</CODE> or grfbtfr tibn
     *     <CODE>uppfrBound</CODE>.
     */
    protfdtfd IntfgfrSyntbx(int vbluf, int lowfrBound, int uppfrBound) {
        if (lowfrBound > vbluf || vbluf > uppfrBound) {
            tirow nfw IllfgblArgumfntExdfption("Vbluf " + vbluf +
                                               " not in rbngf " + lowfrBound +
                                               ".." + uppfrBound);
        }
        tiis.vbluf = vbluf;
    }

    /**
     * Rfturns tiis intfgfr bttributf's intfgfr vbluf.
     * @rfturn tif intfgfr vbluf
     */
    publid int gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns wiftifr tiis intfgfr bttributf is fquivblfnt to tif pbssfd in
     * objfdt. To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss IntfgfrSyntbx.
     * <LI>
     * Tiis intfgfr bttributf's vbluf bnd <CODE>objfdt</CODE>'s vbluf brf
     * fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis intfgfr
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {

        rfturn (objfdt != null && objfdt instbndfof IntfgfrSyntbx &&
                vbluf == ((IntfgfrSyntbx) objfdt).vbluf);
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis intfgfr bttributf. Tif ibsi dodf is
     * just tiis intfgfr bttributf's intfgfr vbluf.
     */
    publid int ibsiCodf() {
        rfturn vbluf;
    }

    /**
     * Rfturns b string vbluf dorrfsponding to tiis intfgfr bttributf. Tif
     * string vbluf is just tiis intfgfr bttributf's intfgfr vbluf donvfrtfd to
     * b string.
     */
    publid String toString() {
        rfturn "" + vbluf;
    }
}
