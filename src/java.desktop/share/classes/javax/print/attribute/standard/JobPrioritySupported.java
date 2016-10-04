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
import jbvbx.print.bttributf.SupportfdVblufsAttributf;

/**
 * Clbss JobPrioritySupportfd is bn intfgfr vblufd printing bttributf dlbss
 * tibt spfdififs wiftifr b Print Sfrvidf instbndf supports tif {@link
 * JobPriority JobPriority} bttributf bnd tif numbfr of difffrfnt job priority
 * lfvfls supportfd.
 * <P>
 * Tif dlifnt dbn blwbys spfdify bny {@link JobPriority JobPriority} vbluf
 * from 1 to 100 for b job. Howfvfr, tif Print Sfrvidf instbndf mby support
 * ffwfr tibn 100 difffrfnt job priority lfvfls. If tiis is tif dbsf, tif
 * Print Sfrvidf instbndf butombtidblly mbps tif dlifnt-spfdififd job priority
 * vbluf to onf of tif supportfd job priority lfvfls, dividing tif 100 job
 * priority vblufs fqublly bmong tif bvbilbblf job priority lfvfls.
 * <P>
 * <B>IPP Compbtibility:</B> Tif intfgfr vbluf givfs tif IPP intfgfr vbluf.
 * Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP
 * bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobPrioritySupportfd fxtfnds IntfgfrSyntbx
    implfmfnts SupportfdVblufsAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 2564840378013555894L;


    /**
     * Construdt b nfw job priority supportfd bttributf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Numbfr of difffrfnt job priority lfvfls supportfd.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is lfss tibn 1
     *     or grfbtfr tibn 100.
     */
    publid JobPrioritySupportfd(int vbluf) {
        supfr (vbluf, 1, 100);
    }

    /**
     * Rfturns wiftifr tiis job priority supportfd bttributf is fquivblfnt to
     * tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss JobPrioritySupportfd.
     * <LI>
     * Tiis job priority supportfd bttributf's vbluf bnd
     * <CODE>objfdt</CODE>'s vbluf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis job
     *          priority supportfd bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls (Objfdt objfdt) {

        rfturn (supfr.fqubls(objfdt) &&
               objfdt instbndfof JobPrioritySupportfd);
    }


    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobPrioritySupportfd, tif
     * dbtfgory is dlbss JobPrioritySupportfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobPrioritySupportfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobPrioritySupportfd, tif
     * dbtfgory nbmf is <CODE>"job-priority-supportfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-priority-supportfd";
    }

}
