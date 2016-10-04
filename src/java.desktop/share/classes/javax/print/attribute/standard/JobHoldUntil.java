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
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss JobHoldUntil is b printing bttributf dlbss, b dbtf-timf bttributf, tibt
 * spfdififs tif fxbdt dbtf bnd timf bt wiidi tif job must bfdomf b dbndidbtf
 * for printing.
 * <P>
 * If tif vbluf of tiis bttributf spfdififs b dbtf-timf tibt is in tif futurf,
 * tif printfr siould bdd tif {@link JobStbtfRfbson JobStbtfRfbson} vbluf of
 * JOB_HOLD_UNTIL_SPECIFIED to tif job's {@link JobStbtfRfbsons JobStbtfRfbsons}
 * bttributf, must movf tif job to tif PENDING_HELD stbtf, bnd must not sdifdulf
 * tif job for printing until tif spfdififd dbtf-timf brrivfs.
 * <P>
 * Wifn tif spfdififd dbtf-timf brrivfs, tif printfr must rfmovf tif {@link
 * JobStbtfRfbson JobStbtfRfbson} vbluf of JOB_HOLD_UNTIL_SPECIFIED from tif
 * job's {@link JobStbtfRfbsons JobStbtfRfbsons} bttributf, if prfsfnt. If tifrf
 * brf no otifr job stbtf rfbsons tibt kffp tif job in tif PENDING_HELD stbtf,
 * tif printfr must donsidfr tif job bs b dbndidbtf for prodfssing by moving tif
 * job to tif PENDING stbtf.
 * <P>
 * If tif spfdififd dbtf-timf ibs blrfbdy pbssfd, tif job must bf b dbndidbtf
 * for prodfssing immfdibtfly. Tius, onf wby to mbkf tif job immfdibtfly bfdomf
 * b dbndidbtf for prodfssing is to spfdify b JobHoldUntil bttributf donstrudtfd
 * likf tiis (dfnoting b dbtf-timf of Jbnubry 1, 1970, 00:00:00 GMT):
 * <PRE>
 *     JobHoldUntil immfdibtfly = nfw JobHoldUntil (nfw Dbtf (0L));
 * </PRE>
 * <P>
 * If tif dlifnt dofs not supply tiis bttributf in b Print Rfqufst bnd tif
 * printfr supports tiis bttributf, tif printfr must usf its
 * (implfmfntbtion-dfpfndfnt) dffbult JobHoldUntil vbluf bt job submission timf
 * (unlikf most job tfmplbtf bttributfs tibt brf usfd if nfdfssbry bt job
 * prodfssing timf).
 * <P>
 * To donstrudt b JobHoldUntil bttributf from sfpbrbtf vblufs of tif yfbr,
 * monti, dby, iour, minutf, bnd so on, usf b {@link jbvb.util.Cblfndbr
 * Cblfndbr} objfdt to donstrudt b {@link jbvb.util.Dbtf Dbtf} objfdt, tifn usf
 * tif {@link jbvb.util.Dbtf Dbtf} objfdt to donstrudt tif JobHoldUntil
 * bttributf. To donvfrt b JobHoldUntil bttributf to sfpbrbtf vblufs of tif
 * yfbr, monti, dby, iour, minutf, bnd so on, drfbtf b {@link jbvb.util.Cblfndbr
 * Cblfndbr} objfdt bnd sft it to tif {@link jbvb.util.Dbtf Dbtf} from tif
 * JobHoldUntil bttributf.
 * <P>
 * <B>IPP Compbtibility:</B> Altiougi IPP supports b "job-iold-until" bttributf
 * spfdififd bs b kfyword, IPP dofs not bt tiis timf support b "job-iold-until"
 * bttributf spfdififd bs b dbtf bnd timf. Howfvfr, tif dbtf bnd timf dbn bf
 * donvfrtfd to onf of tif stbndbrd IPP kfywords witi somf loss of prfdision;
 * for fxbmplf, b JobHoldUntil vbluf witi todby's dbtf bnd 9:00pm lodbl timf
 * migit bf donvfrtfd to tif stbndbrd IPP kfyword "nigit". Tif dbtfgory nbmf
 * rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss JobHoldUntil fxtfnds DbtfTimfSyntbx
        implfmfnts PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -1664471048860415024L;


    /**
     * Construdt b nfw job iold until dbtf-timf bttributf witi tif givfn
     * {@link jbvb.util.Dbtf Dbtf} vbluf.
     *
     * @pbrbm  dbtfTimf  {@link jbvb.util.Dbtf Dbtf} vbluf.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>dbtfTimf</CODE> is null.
     */
    publid JobHoldUntil(Dbtf dbtfTimf) {
        supfr (dbtfTimf);
    }

    /**
     * Rfturns wiftifr tiis job iold until bttributf is fquivblfnt to tif
     * pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss JobHoldUntil.
     * <LI>
     * Tiis job iold until bttributf's {@link jbvb.util.Dbtf Dbtf} vbluf bnd
     * <CODE>objfdt</CODE>'s {@link jbvb.util.Dbtf Dbtf} vbluf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis job iold
     *          until bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) && objfdt instbndfof JobHoldUntil);
    }


    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobHoldUntil, tif dbtfgory is dlbss JobHoldUntil itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobHoldUntil.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobHoldUntil, tif dbtfgory nbmf is <CODE>"job-iold-until"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-iold-until";
    }

}
