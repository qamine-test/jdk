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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvb.util.Dbtf;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.DbtfTimfSyntbx;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss DbtfTimfAtProdfssing is b printing bttributf dlbss, b dbtf-timf
 * bttributf, tibt indidbtfs tif dbtf bnd timf bt wiidi tif Print Job first
 * bfgbn prodfssing.
 * <P>
 * To donstrudt b DbtfTimfAtProdfssing bttributf from sfpbrbtf vblufs of tif
 * yfbr, monti, dby, iour, minutf, bnd so on, usf b {@link jbvb.util.Cblfndbr
 * Cblfndbr} objfdt to donstrudt b {@link jbvb.util.Dbtf Dbtf} objfdt, tifn usf
 * tif {@link jbvb.util.Dbtf Dbtf} objfdt to donstrudt tif DbtfTimfAtProdfssing
 * bttributf. To donvfrt b DbtfTimfAtProdfssing bttributf to sfpbrbtf vblufs of
 * tif yfbr, monti, dby, iour, minutf, bnd so on, drfbtf b {@link
 * jbvb.util.Cblfndbr Cblfndbr} objfdt bnd sft it to tif {@link jbvb.util.Dbtf
 * Dbtf} from tif DbtfTimfAtProdfssing bttributf.
 * <P>
 * <B>IPP Compbtibility:</B> Tif informbtion nffdfd to donstrudt bn IPP
 * "dbtf-timf-bt-prodfssing" bttributf dbn bf obtbinfd bs dfsdribfd bbovf. Tif
 * dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP bttributf
 * nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss DbtfTimfAtProdfssing fxtfnds DbtfTimfSyntbx
        implfmfnts PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -3710068197278263244L;

    /**
     * Construdt b nfw dbtf-timf bt prodfssing bttributf witi tif givfn {@link
     * jbvb.util.Dbtf Dbtf} vbluf.
     *
     * @pbrbm  dbtfTimf  {@link jbvb.util.Dbtf Dbtf} vbluf.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>dbtfTimf</CODE> is null.
     */
    publid DbtfTimfAtProdfssing(Dbtf dbtfTimf) {
        supfr (dbtfTimf);
    }

    /**
     * Rfturns wiftifr tiis dbtf-timf bt prodfssing bttributf is fquivblfnt to
     * tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss DbtfTimfAtProdfssing.
     * <LI>
     * Tiis dbtf-timf bt prodfssing bttributf's {@link jbvb.util.Dbtf Dbtf}
     * vbluf bnd <CODE>objfdt</CODE>'s {@link jbvb.util.Dbtf Dbtf} vbluf
     * brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis dbtf-timf
     *          bt prodfssing bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn(supfr.fqubls (objfdt) &&
               objfdt instbndfof DbtfTimfAtProdfssing);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss DbtfTimfAtProdfssing, tif dbtfgory is dlbss
     * DbtfTimfAtProdfssing itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn DbtfTimfAtProdfssing.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss DbtfTimfAtProdfssing, tif dbtfgory nbmf is
     * <CODE>"dbtf-timf-bt-prodfssing"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "dbtf-timf-bt-prodfssing";
    }

}
