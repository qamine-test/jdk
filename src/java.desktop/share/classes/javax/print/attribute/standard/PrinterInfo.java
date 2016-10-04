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

import jbvb.util.Lodblf;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.TfxtSyntbx;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;

/**
 * Clbss PrintfrInfo is b printing bttributf dlbss, b tfxt bttributf, tibt
 * providfs dfsdriptivf informbtion bbout b printfr. Tiis dould indludf tiings
 * likf: <CODE>"Tiis printfr dbn bf usfd for printing dolor trbnspbrfndifs for
 * HR prfsfntbtions"</CODE>, or <CODE>"Out of dourtfsy for otifrs, plfbsf
 * print only smbll (1-5 pbgf) jobs bt tiis printfr"</CODE>, or fvfn \
 * <CODE>"Tiis printfr is going bwby on July 1, 1997, plfbsf find b nfw
 * printfr"</CODE>.
 * <P>
 * <B>IPP Compbtibility:</B> Tif string vbluf givfs tif IPP nbmf vbluf. Tif
 * lodblf givfs tif IPP nbturbl lbngubgf. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PrintfrInfo fxtfnds TfxtSyntbx
        implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7765280618777599727L;

    /**
     * Construdts b nfw printfr info bttributf witi tif givfn informbtion
     * string bnd lodblf.
     *
     * @pbrbm  info    Printfr informbtion string.
     * @pbrbm  lodblf  Nbturbl lbngubgf of tif tfxt string. null
     * is intfrprftfd to mfbn tif dffbult lodblf bs rfturnfd
     * by <dodf>Lodblf.gftDffbult()</dodf>
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>info</CODE> is null.
     */
    publid PrintfrInfo(String info, Lodblf lodblf) {
        supfr (info, lodblf);
    }

    /**
     * Rfturns wiftifr tiis printfr info bttributf is fquivblfnt to tif pbssfd
     * in objfdt. To bf fquivblfnt, bll of tif following donditions must bf
     * truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss PrintfrInfo.
     * <LI>
     * Tiis printfr info bttributf's undfrlying string bnd
     * <CODE>objfdt</CODE>'s undfrlying string brf fqubl.
     * <LI>
     * Tiis printfr info bttributf's lodblf bnd <CODE>objfdt</CODE>'s
     * lodblf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis printfr
     *          info bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) && objfdt instbndfof PrintfrInfo);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrInfo, tif dbtfgory is dlbss PrintfrInfo itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrInfo.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrInfo, tif dbtfgory nbmf is <CODE>"printfr-info"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-info";
    }

}
