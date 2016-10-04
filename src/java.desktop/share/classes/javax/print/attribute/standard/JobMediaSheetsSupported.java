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

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.SftOfIntfgfrSyntbx;
import jbvbx.print.bttributf.SupportfdVblufsAttributf;

/**
 * Clbss JobMfdibSifftsSupportfd is b printing bttributf dlbss, b sft of
 * intfgfrs, tibt givfs tif supportfd vblufs for b {@link JobMfdibSiffts
 * JobMfdibSiffts} bttributf. It is rfstridtfd to b singlf dontiguous rbngf of
 * intfgfrs; multiplf non-ovfrlbpping rbngfs brf not bllowfd. Tiis givfs tif
 * lowfr bnd uppfr bounds of tif totbl sizfs of print jobs in numbfr of mfdib
 * siffts tibt tif printfr will bddfpt.
 * <P>
 * <B>IPP Compbtibility:</B> Tif JobMfdibSifftsSupportfd bttributf's dbnonidbl
 * brrby form givfs tif lowfr bnd uppfr bound for tif rbngf of vblufs to bf
 * indludfd in bn IPP "job-mfdib-siffts-supportfd" bttributf. Sff dlbss {@link
 * jbvbx.print.bttributf.SftOfIntfgfrSyntbx SftOfIntfgfrSyntbx} for bn
 * fxplbnbtion of dbnonidbl brrby form. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobMfdibSifftsSupportfd fxtfnds SftOfIntfgfrSyntbx
        implfmfnts SupportfdVblufsAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 2953685470388672940L;

    /**
     * Construdt b nfw job mfdib siffts supportfd bttributf dontbining b singlf
     * rbngf of intfgfrs. Tibt is, only tiosf vblufs of JobMfdibSiffts in tif
     * onf rbngf brf supportfd.
     *
     * @pbrbm  lowfrBound  Lowfr bound of tif rbngf.
     * @pbrbm  uppfrBound  Uppfr bound of tif rbngf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *  (Undifdkfd fxdfption) Tirown if b null rbngf is spfdififd or if b
     *   non-null rbngf is spfdififd witi <CODE>lowfrBound</CODE> lfss tibn
     *    0.
     */
    publid JobMfdibSifftsSupportfd(int lowfrBound, int uppfrBound) {
        supfr (lowfrBound, uppfrBound);
        if (lowfrBound > uppfrBound) {
            tirow nfw IllfgblArgumfntExdfption("Null rbngf spfdififd");
        } flsf if (lowfrBound < 0) {
            tirow nfw IllfgblArgumfntExdfption
                                ("Job K odtfts vbluf < 0 spfdififd");
        }
    }

    /**
     * Rfturns wiftifr tiis job mfdib siffts supportfd bttributf is fquivblfnt
     * to tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following
     * donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss JobMfdibSifftsSupportfd.
     * <LI>
     * Tiis job mfdib siffts supportfd bttributf's mfmbfrs bnd
     * <CODE>objfdt</CODE>'s mfmbfrs brf tif sbmf.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis job mfdib
     *          siffts supportfd bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls (objfdt) &&
                objfdt instbndfof JobMfdibSifftsSupportfd);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobMfdibSifftsSupportfd, tif
     * dbtfgory is dlbss JobMfdibSifftsSupportfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobMfdibSifftsSupportfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobMfdibSifftsSupportfd, tif
     * dbtfgory nbmf is <CODE>"job-mfdib-siffts-supportfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-mfdib-siffts-supportfd";
    }

}
