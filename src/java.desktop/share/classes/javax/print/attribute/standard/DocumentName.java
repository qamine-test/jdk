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
import jbvbx.print.bttributf.DodAttributf;

/**
 * Clbss DodumfntNbmf is b printing bttributf dlbss, b tfxt bttributf, tibt
 * spfdififs tif nbmf of b dodumfnt. DodumfntNbmf is bn bttributf of tif print
 * dbtb (tif dod), not of tif Print Job. A dodumfnt's nbmf is bn brbitrbry
 * string dffinfd by tif dlifnt.
 * Howfvfr if b JobNbmf is not spfdififd, tif DodumfntNbmf siould bf usfd
 * instfbd, wiidi implifs tibt supporting spfdifidbtion of DodumfntNbmf
 * rfquirfs rfporting of JobNbmf bnd vidf vfrsb.
 * Sff {@link JobNbmf JobNbmf} for morf informbtion.
 * <P>
 * <B>IPP Compbtibility:</B> Tif string vbluf givfs tif IPP nbmf vbluf. Tif
 * lodblf givfs tif IPP nbturbl lbngubgf. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss DodumfntNbmf fxtfnds TfxtSyntbx implfmfnts DodAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7883105848533280430L;

    /**
     * Construdts b nfw dodumfnt nbmf bttributf witi tif givfn dodumfnt nbmf
     * bnd lodblf.
     *
     * @pbrbm  dodumfntNbmf  Dodumfnt nbmf.
     * @pbrbm  lodblf        Nbturbl lbngubgf of tif tfxt string. null
     * is intfrprftfd to mfbn tif dffbult lodblf bs rfturnfd
     * by <dodf>Lodblf.gftDffbult()</dodf>
     *
     * @fxdfption  NullPointfrExdfption
     *   (undifdkfd fxdfption) Tirown if <CODE>dodumfntNbmf</CODE> is null.
     */
    publid DodumfntNbmf(String dodumfntNbmf, Lodblf lodblf) {
        supfr (dodumfntNbmf, lodblf);
    }

    /**
     * Rfturns wiftifr tiis dodumfnt nbmf bttributf is fquivblfnt to tif
     * pbssfd in objfdt.
     * To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss DodumfntNbmf.
     * <LI>
     * Tiis dodumfnt nbmf bttributf's undfrlying string bnd
     * <CODE>objfdt</CODE>'s undfrlying string brf fqubl.
     * <LI>
     * Tiis dodumfnt nbmf bttributf's lodblf bnd <CODE>objfdt</CODE>'s lodblf
     * brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis dodumfnt
     *          nbmf bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls (objfdt) && objfdt instbndfof DodumfntNbmf);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss DodumfntNbmf, tif dbtfgory is dlbss DodumfntNbmf itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn DodumfntNbmf.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss DodumfntNbmf, tif dbtfgory nbmf is <CODE>"dodumfnt-nbmf"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "dodumfnt-nbmf";
    }

}
