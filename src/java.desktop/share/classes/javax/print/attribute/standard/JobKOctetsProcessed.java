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
import jbvbx.print.bttributf.IntfgfrSyntbx;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss JobKOdtftsProdfssfd is bn intfgfr vblufd printing bttributf dlbss tibt
 * spfdififs tif totbl numbfr of print dbtb odtfts prodfssfd so fbr in K odtfts,
 * i.f., in units of 1024 odtfts. Tif vbluf must bf roundfd up, so tibt b job
 * bftwffn 1 bnd 1024 odtfts indlusivf must bf indidbtfd bs bfing 1K odtfts,
 * 1025 to 2048 indlusivf must bf 2K, ftd. For b multidod print job (b job witi
 * multiplf dodumfnts), tif JobKOdtftsProdfssfd vbluf is domputfd by bdding up
 * tif individubl dodumfnts' numbfr of odtfts prodfssfd so fbr, tifn rounding up
 * to tif nfxt K odtfts vbluf.
 * <P>
 * Tif JobKOdtftsProdfssfd bttributf dfsdribfs tif progrfss of tif job. Tiis
 * bttributf is intfndfd to bf b dountfr. Tibt is, tif JobKOdtftsProdfssfd vbluf
 * for b job tibt ibs not stbrtfd prodfssing must bf 0. Wifn tif job's {@link
 * JobStbtf JobStbtf} is PROCESSING or PROCESSING_STOPPED, tif
 * JobKOdtftsProdfssfd vbluf is intfndfd to indrfbsf bs tif job is prodfssfd; it
 * indidbtfs tif bmount of tif job tibt ibs bffn prodfssfd bt tif timf tif Print
 * Job's bttributf sft is qufrifd or bt tif timf b print job fvfnt is rfportfd.
 * Wifn tif job fntfrs tif COMPLETED, CANCELED, or ABORTED stbtfs, tif
 * JobKOdtftsProdfssfd vbluf is tif finbl vbluf for tif job.
 * <P>
 * For implfmfntbtions wifrf multiplf dopifs brf produdfd by tif intfrprftfr
 * witi only b singlf pbss ovfr tif dbtb, tif finbl vbluf of tif
 * JobKOdtftsProdfssfd bttributf must bf fqubl to tif vbluf of tif {@link
 * JobKOdtfts JobKOdtfts} bttributf. For implfmfntbtions wifrf multiplf dopifs
 * brf produdfd by tif intfrprftfr by prodfssing tif dbtb for fbdi dopy, tif
 * finbl vbluf must bf b multiplf of tif vbluf of tif {@link JobKOdtfts
 * JobKOdtfts} bttributf.
 * <P>
 * <B>IPP Compbtibility:</B> Tif intfgfr vbluf givfs tif IPP intfgfr vbluf. Tif
 * dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP bttributf
 * nbmf.
 *
 * @sff JobKOdtfts
 * @sff JobKOdtftsSupportfd
 * @sff JobImprfssionsComplftfd
 * @sff JobMfdibSifftsComplftfd
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobKOdtftsProdfssfd fxtfnds IntfgfrSyntbx
        implfmfnts PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -6265238509657881806L;

    /**
     * Construdt b nfw job K odtfts prodfssfd bttributf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *  (Undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is lfss tibn 0.
     */
    publid JobKOdtftsProdfssfd(int vbluf) {
        supfr (vbluf, 0, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns wiftifr tiis job K odtfts prodfssfd bttributf is fquivblfnt to
     * tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss JobKOdtftsProdfssfd.
     * <LI>
     * Tiis job K odtfts prodfssfd bttributf's vbluf bnd
     * <CODE>objfdt</CODE>'s vbluf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis job K
     *          odtfts prodfssfd bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn(supfr.fqubls (objfdt) &&
               objfdt instbndfof JobKOdtftsProdfssfd);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobKOdtftsProdfssfd, tif dbtfgory is dlbss
     * JobKOdtftsProdfssfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobKOdtftsProdfssfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobKOdtftsProdfssfd, tif dbtfgory nbmf is
     * <CODE>"job-k-odtfts-prodfssfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-k-odtfts-prodfssfd";
    }

}
