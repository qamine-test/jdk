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
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss JobMfssbgfFromOpfrbtor is b printing bttributf dlbss, b tfxt bttributf,
 * tibt providfs b mfssbgf from bn opfrbtor, systfm bdministrbtor, or
 * "intflligfnt" prodfss to indidbtf to tif fnd usfr tif rfbsons for
 * modifidbtion or otifr mbnbgfmfnt bdtion tbkfn on b job.
 * <P>
 * A Print Job's bttributf sft indludfs zfro instbndfs or onf instbndf of b
 * JobMfssbgfFromOpfrbtor bttributf, not morf tibn onf instbndf. A nfw
 * JobMfssbgfFromOpfrbtor bttributf rfplbdfs bn fxisting JobMfssbgfFromOpfrbtor
 * bttributf, if bny. In otifr words, JobMfssbgfFromOpfrbtor is not intfndfd to
 * bf b iistory log. If it wisifs, tif dlifnt dbn dftfdt dibngfs to b Print
 * Job's JobMfssbgfFromOpfrbtor bttributf bnd mbintbin tif dlifnt's own iistory
 * log of tif JobMfssbgfFromOpfrbtor bttributf vblufs.
 * <P>
 * <B>IPP Compbtibility:</B> Tif string vbluf givfs tif IPP nbmf vbluf. Tif
 * lodblf givfs tif IPP nbturbl lbngubgf. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobMfssbgfFromOpfrbtor fxtfnds TfxtSyntbx
        implfmfnts PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -4620751846003142047L;

    /**
     * Construdts b nfw job mfssbgf from opfrbtor bttributf witi tif givfn
     * mfssbgf bnd lodblf.
     *
     * @pbrbm  mfssbgf  Mfssbgf.
     * @pbrbm  lodblf   Nbturbl lbngubgf of tif tfxt string. null
     * is intfrprftfd to mfbn tif dffbult lodblf bs rfturnfd
     * by <dodf>Lodblf.gftDffbult()</dodf>
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>mfssbgf</CODE> is null.
     */
    publid JobMfssbgfFromOpfrbtor(String mfssbgf, Lodblf lodblf) {
        supfr (mfssbgf, lodblf);
    }

    /**
     * Rfturns wiftifr tiis job mfssbgf from opfrbtor bttributf is fquivblfnt to
     * tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss JobMfssbgfFromOpfrbtor.
     * <LI>
     * Tiis job mfssbgf from opfrbtor bttributf's undfrlying string bnd
     * <CODE>objfdt</CODE>'s undfrlying string brf fqubl.
     * <LI>
     * Tiis job mfssbgf from opfrbtor bttributf's lodblf bnd
     * <CODE>objfdt</CODE>'s lodblf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis job
     *          mfssbgf from opfrbtor bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls (objfdt) &&
                objfdt instbndfof JobMfssbgfFromOpfrbtor);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobMfssbgfFromOpfrbtor, tif
     * dbtfgory is dlbss JobMfssbgfFromOpfrbtor itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobMfssbgfFromOpfrbtor.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobMfssbgfFromOpfrbtor, tif
     * dbtfgory nbmf is <CODE>"job-mfssbgf-from-opfrbtor"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-mfssbgf-from-opfrbtor";
    }

}
