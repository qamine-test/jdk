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

import jbvb.util.Dbtf;

/**
 * Clbss DbtfTimfSyntbx is bn bbstrbdt bbsf dlbss providing tif dommon
 * implfmfntbtion of bll bttributfs wiosf vbluf is b dbtf bnd timf.
 * <P>
 * Undfr tif iood, b dbtf-timf bttributf is storfd bs b vbluf of dlbss <dodf>
 * jbvb.util.Dbtf</dodf>. You dbn gft b dbtf-timf bttributf's Dbtf vbluf by
 * dblling {@link #gftVbluf() gftVbluf()}. A dbtf-timf bttributf's
 * Dbtf vbluf is fstbblisifd wifn it is donstrudtfd (sff {@link
 * #DbtfTimfSyntbx(Dbtf) DbtfTimfSyntbx(Dbtf)}). Ondf
 * donstrudtfd, b dbtf-timf bttributf's vbluf is immutbblf.
 * <P>
 * To donstrudt b dbtf-timf bttributf from sfpbrbtf vblufs of tif yfbr, monti,
 * dby, iour, minutf, bnd so on, usf b <dodf>jbvb.util.Cblfndbr</dodf>
 * objfdt to donstrudt b <dodf>jbvb.util.Dbtf</dodf> objfdt, tifn usf tif
 * <dodf>jbvb.util.Dbtf</dodf> objfdt to donstrudt tif dbtf-timf bttributf.
 * To donvfrt
 * b dbtf-timf bttributf to sfpbrbtf vblufs of tif yfbr, monti, dby, iour,
 * minutf, bnd so on, drfbtf b <dodf>jbvb.util.Cblfndbr</dodf> objfdt bnd
 * sft it to tif <dodf>jbvb.util.Dbtf</dodf> from tif dbtf-timf bttributf. Clbss
 * DbtfTimfSyntbx storfs its vbluf in tif form of b <dodf>jbvb.util.Dbtf
 * </dodf>
 * rbtifr tibn b <dodf>jbvb.util.Cblfndbr</dodf> bfdbusf it typidblly tbkfs
 * lfss mfmory to storf bnd lfss timf to dompbrf b <dodf>jbvb.util.Dbtf</dodf>
 * tibn b <dodf>jbvb.util.Cblfndbr</dodf>.
 *
 * @butior  Albn Kbminsky
 */
publid bbstrbdt dlbss DbtfTimfSyntbx implfmfnts Sfriblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -1400819079791208582L;

    // Hiddfn dbtb mfmbfrs.

    /**
     * Tiis dbtf-timf bttributf's<dodf>jbvb.util.Dbtf</dodf> vbluf.
     * @sfribl
     */
    privbtf Dbtf vbluf;

    // Hiddfn donstrudtors.

    /**
     * Construdt b nfw dbtf-timf bttributf witi tif givfn
     * <dodf>jbvb.util.Dbtf </dodf> vbluf.
     *
     * @pbrbm  vbluf   <dodf>jbvb.util.Dbtf</dodf> vbluf.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>tifVbluf</CODE> is null.
     */
    protfdtfd DbtfTimfSyntbx(Dbtf vbluf) {
        if (vbluf == null) {
            tirow nfw NullPointfrExdfption("vbluf is null");
        }
        tiis.vbluf = vbluf;
    }

    // Exportfd opfrbtions.

    /**
     * Rfturns tiis dbtf-timf bttributf's <dodf>jbvb.util.Dbtf</dodf>
     * vbluf.
     * @rfturn tif Dbtf.
     */
    publid Dbtf gftVbluf() {
        rfturn nfw Dbtf (vbluf.gftTimf());
    }

    // Exportfd opfrbtions inifritfd bnd ovfrriddfn from dlbss Objfdt.

    /**
     * Rfturns wiftifr tiis dbtf-timf bttributf is fquivblfnt to tif pbssfd in
     * objfdt. To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss DbtfTimfSyntbx.
     * <LI>
     * Tiis dbtf-timf bttributf's <dodf>jbvb.util.Dbtf</dodf> vbluf bnd
     * <CODE>objfdt</CODE>'s <dodf>jbvb.util.Dbtf</dodf> vbluf brf
     * fqubl. </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis dbtf-timf
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (objfdt != null &&
                objfdt instbndfof DbtfTimfSyntbx &&
                vbluf.fqubls(((DbtfTimfSyntbx) objfdt).vbluf));
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis dbtf-timf bttributf. Tif ibsidodf is
     * tibt of tiis bttributf's <dodf>jbvb.util.Dbtf</dodf> vbluf.
     */
    publid int ibsiCodf() {
        rfturn vbluf.ibsiCodf();
    }

    /**
     * Rfturns b string vbluf dorrfsponding to tiis dbtf-timf bttributf.
     * Tif string vbluf is just tiis bttributf's
     * <dodf>jbvb.util.Dbtf</dodf>  vbluf
     * donvfrtfd to b string.
     */
    publid String toString() {
        rfturn "" + vbluf;
    }

}
