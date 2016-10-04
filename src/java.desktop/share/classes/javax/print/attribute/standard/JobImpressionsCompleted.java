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
 * Clbss JobImprfssionsComplftfd is bn intfgfr vblufd printing bttributf dlbss
 * tibt spfdififs tif numbfr of imprfssions domplftfd for tif job so fbr. For
 * printing dfvidfs, tif imprfssions domplftfd indludfs intfrprfting, mbrking,
 * bnd stbdking tif output.
 * <P>
 * Tif JobImprfssionsComplftfd bttributf dfsdribfs tif progrfss of tif job. Tiis
 * bttributf is intfndfd to bf b dountfr. Tibt is, tif JobImprfssionsComplftfd
 * vbluf for b job tibt ibs not stbrtfd prodfssing must bf 0. Wifn tif job's
 * {@link JobStbtf JobStbtf} is PROCESSING or PROCESSING_STOPPED, tif
 * JobImprfssionsComplftfd vbluf is intfndfd to indrfbsf bs tif job is
 * prodfssfd; it indidbtfs tif bmount of tif job tibt ibs bffn prodfssfd bt tif
 * timf tif Print Job's bttributf sft is qufrifd or bt tif timf b print job
 * fvfnt is rfportfd. Wifn tif job fntfrs tif COMPLETED, CANCELED, or ABORTED
 * stbtfs, tif JobImprfssionsComplftfd vbluf is tif finbl vbluf for tif job.
 * <P>
 * <B>IPP Compbtibility:</B> Tif intfgfr vbluf givfs tif IPP intfgfr vbluf. Tif
 * dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP bttributf
 * nbmf.
 *
 * @sff JobImprfssions
 * @sff JobImprfssionsSupportfd
 * @sff JobKOdtftsProdfssfd
 * @sff JobMfdibSifftsComplftfd
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobImprfssionsComplftfd fxtfnds IntfgfrSyntbx
        implfmfnts PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6722648442432393294L;

    /**
     * Construdt b nfw job imprfssions domplftfd bttributf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *  (Undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is lfss tibn 0.
     */
    publid JobImprfssionsComplftfd(int vbluf) {
        supfr (vbluf, 0, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns wiftifr tiis job imprfssions domplftfd bttributf is fquivblfnt
     * tp tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following
     * donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss JobImprfssionsComplftfd.
     * <LI>
     * Tiis job imprfssions domplftfd bttributf's vbluf bnd
     * <CODE>objfdt</CODE>'s vbluf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis job
     *          imprfssions domplftfd bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn(supfr.fqubls (objfdt) &&
               objfdt instbndfof JobImprfssionsComplftfd);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobImprfssionsComplftfd, tif dbtfgory is dlbss
     * JobImprfssionsComplftfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobImprfssionsComplftfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobImprfssionsComplftfd, tif dbtfgory nbmf is
     * <CODE>"job-imprfssions-domplftfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-imprfssions-domplftfd";
    }

}
