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

import jbvb.nft.URI;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.URISyntbx;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;

/**
 * Clbss PrintfrMorfInfoMbnufbdturfr is b printing bttributf dlbss, b URI,
 * tibt is usfd to obtbin morf informbtion bbout tiis typf of dfvidf.
 * Tif informbtion obtbinfd from tiis URI is intfndfd for fnd usfr
 * donsumption. Ffbturfs outsidf tif sdopf of tif Print Sfrvidf API
 * dbn bf bddfssfd from tiis URI (f.g.,
 * lbtfst firmwbrf, upgrbdfs, sfrvidf proxifs, optionbl ffbturfs bvbilbblf,
 * dftbils on dolor support). Tif informbtion is intfndfd to bf gfrmbnf to
 * tiis kind of printfr witiout rfgbrd to sitf spfdifid modifidbtions or
 * sfrvidfs.
 * <P>
 * In dontrbst, tif {@link PrintfrMorfInfo PrintfrMorfInfo} bttributf is usfd
 * to find out morf informbtion bbout tiis spfdifid printfr rbtifr tibn tiis
 * gfnfrbl kind of printfr.
 * <P>
 * <B>IPP Compbtibility:</B> Tif string form rfturnfd by
 * <CODE>toString()</CODE> givfs tif IPP uri vbluf.
 * Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE>
 * givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PrintfrMorfInfoMbnufbdturfr fxtfnds URISyntbx
        implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 3323271346485076608L;

    /**
     * Construdts b nfw printfr morf info mbnufbdturfr bttributf witi tif
     * spfdififd URI.
     *
     * @pbrbm  uri  URI.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>uri</CODE> is null.
     */
    publid PrintfrMorfInfoMbnufbdturfr(URI uri) {
        supfr (uri);
    }

    /**
     * Rfturns wiftifr tiis printfr morf info mbnufbdturfr bttributf is
     * fquivblfnt to tif pbssfd in objfdt. To bf fquivblfnt, bll of tif
     * following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss
     * PrintfrMorfInfoMbnufbdturfr.
     * <LI>
     * Tiis printfr morf info mbnufbdturfr bttributf's URI bnd
     * <CODE>objfdt</CODE>'s URI brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis printfr
     *          morf info mbnufbdturfr bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) &&
                objfdt instbndfof PrintfrMorfInfoMbnufbdturfr);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrMorfInfoMbnufbdturfr, tif dbtfgory is
     * dlbss PrintfrMorfInfoMbnufbdturfr itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrMorfInfoMbnufbdturfr.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrMorfInfoMbnufbdturfr, tif dbtfgory nbmf is
     * <CODE>"printfr-morf-info-mbnufbdturfr"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-morf-info-mbnufbdturfr";
    }

}
