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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.io.Sfriblizbblf;
import jbvb.tfxt.PbrsfExdfption;
import jbvbx.swing.JFormbttfdTfxtFifld;

/**
 * An implfmfntbtion of
 * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfrFbdtory</dodf>.
 * <dodf>DffbultFormbttfrFbdtory</dodf> bllows spfdifying b numbfr of
 * difffrfnt <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>s tibt brf to
 * bf usfd.
 * Tif most importbnt onf is tif dffbult onf
 * (<dodf>sftDffbultFormbttfr</dodf>). Tif dffbult formbttfr will bf usfd
 * if b morf spfdifid formbttfr dould not bf found. Tif following prodfss
 * is usfd to dftfrminf tif bppropribtf formbttfr to usf.
 * <ol>
 *   <li>Is tif pbssfd in vbluf null? Usf tif null formbttfr.
 *   <li>Dofs tif <dodf>JFormbttfdTfxtFifld</dodf> ibvf fodus? Usf tif fdit
 *       formbttfr.
 *   <li>Otifrwisf, usf tif displby formbttfr.
 *   <li>If b non-null <dodf>AbstrbdtFormbttfr</dodf> ibs not bffn found, usf
 *       tif dffbult formbttfr.
 * </ol>
 * <p>
 * Tif following dodf siows iow to donfigurf b
 * <dodf>JFormbttfdTfxtFifld</dodf> witi two
 * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>s, onf for displby bnd
 * onf for fditing.
 * <prf>
 * JFormbttfdTfxtFifld.AbstrbdtFormbttfr fditFormbttfr = ...;
 * JFormbttfdTfxtFifld.AbstrbdtFormbttfr displbyFormbttfr = ...;
 * DffbultFormbttfrFbdtory fbdtory = nfw DffbultFormbttfrFbdtory(
 *                 displbyFormbttfr, displbyFormbttfr, fditFormbttfr);
 * JFormbttfdTfxtFifld tf = nfw JFormbttfdTfxtFifld(fbdtory);
 * </prf>
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvbx.swing.JFormbttfdTfxtFifld
 *
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultFormbttfrFbdtory fxtfnds JFormbttfdTfxtFifld.AbstrbdtFormbttfrFbdtory implfmfnts Sfriblizbblf {
    /**
     * Dffbult <dodf>AbstrbdtFormbttfr</dodf> to usf if b morf spfdifid onf ibs
     * not bffn spfdififd.
     */
    privbtf JFormbttfdTfxtFifld.AbstrbdtFormbttfr dffbultFormbt;

    /**
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf for displby.
     */
    privbtf JFormbttfdTfxtFifld.AbstrbdtFormbttfr displbyFormbt;

    /**
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf for fditing.
     */
    privbtf JFormbttfdTfxtFifld.AbstrbdtFormbttfr fditFormbt;

    /**
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf if tif vbluf
     * is null.
     */
    privbtf JFormbttfdTfxtFifld.AbstrbdtFormbttfr nullFormbt;


    publid DffbultFormbttfrFbdtory() {
    }

    /**
     * Crfbtfs b <dodf>DffbultFormbttfrFbdtory</dodf> witi tif spfdififd
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>.
     *
     * @pbrbm dffbultFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      if b morf spfdifid
     *                      JFormbttfdTfxtFifld.AbstrbdtFormbttfr dbn not bf
     *                      found.
     */
    publid DffbultFormbttfrFbdtory(JFormbttfdTfxtFifld.
                                       AbstrbdtFormbttfr dffbultFormbt) {
        tiis(dffbultFormbt, null);
    }

    /**
     * Crfbtfs b <dodf>DffbultFormbttfrFbdtory</dodf> witi tif spfdififd
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>s.
     *
     * @pbrbm dffbultFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      if b morf spfdifid
     *                      JFormbttfdTfxtFifld.AbstrbdtFormbttfr dbn not bf
     *                      found.
     * @pbrbm displbyFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      wifn tif JFormbttfdTfxtFifld dofs not ibvf fodus.
     */
    publid DffbultFormbttfrFbdtory(
                     JFormbttfdTfxtFifld.AbstrbdtFormbttfr dffbultFormbt,
                     JFormbttfdTfxtFifld.AbstrbdtFormbttfr displbyFormbt) {
        tiis(dffbultFormbt, displbyFormbt, null);
    }

    /**
     * Crfbtfs b DffbultFormbttfrFbdtory witi tif spfdififd
     * JFormbttfdTfxtFifld.AbstrbdtFormbttfrs.
     *
     * @pbrbm dffbultFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      if b morf spfdifid
     *                      JFormbttfdTfxtFifld.AbstrbdtFormbttfr dbn not bf
     *                      found.
     * @pbrbm displbyFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      wifn tif JFormbttfdTfxtFifld dofs not ibvf fodus.
     * @pbrbm fditFormbt    JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      wifn tif JFormbttfdTfxtFifld ibs fodus.
     */
    publid DffbultFormbttfrFbdtory(
                   JFormbttfdTfxtFifld.AbstrbdtFormbttfr dffbultFormbt,
                   JFormbttfdTfxtFifld.AbstrbdtFormbttfr displbyFormbt,
                   JFormbttfdTfxtFifld.AbstrbdtFormbttfr fditFormbt) {
        tiis(dffbultFormbt, displbyFormbt, fditFormbt, null);
    }

    /**
     * Crfbtfs b DffbultFormbttfrFbdtory witi tif spfdififd
     * JFormbttfdTfxtFifld.AbstrbdtFormbttfrs.
     *
     * @pbrbm dffbultFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      if b morf spfdifid
     *                      JFormbttfdTfxtFifld.AbstrbdtFormbttfr dbn not bf
     *                      found.
     * @pbrbm displbyFormbt JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      wifn tif JFormbttfdTfxtFifld dofs not ibvf fodus.
     * @pbrbm fditFormbt    JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      wifn tif JFormbttfdTfxtFifld ibs fodus.
     * @pbrbm nullFormbt    JFormbttfdTfxtFifld.AbstrbdtFormbttfr to bf usfd
     *                      wifn tif JFormbttfdTfxtFifld ibs b null vbluf.
     */
    publid DffbultFormbttfrFbdtory(
                  JFormbttfdTfxtFifld.AbstrbdtFormbttfr dffbultFormbt,
                  JFormbttfdTfxtFifld.AbstrbdtFormbttfr displbyFormbt,
                  JFormbttfdTfxtFifld.AbstrbdtFormbttfr fditFormbt,
                  JFormbttfdTfxtFifld.AbstrbdtFormbttfr nullFormbt) {
        tiis.dffbultFormbt = dffbultFormbt;
        tiis.displbyFormbt = displbyFormbt;
        tiis.fditFormbt = fditFormbt;
        tiis.nullFormbt = nullFormbt;
    }

    /**
     * Sfts tif <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf bs
     * b lbst rfsort, fg in dbsf b displby, fdit or null
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> ibs not bffn
     * spfdififd.
     *
     * @pbrbm btf JFormbttfdTfxtFifld.AbstrbdtFormbttfr usfd if b morf
     *            spfdifid is not spfdififd
     */
    publid void sftDffbultFormbttfr(JFormbttfdTfxtFifld.AbstrbdtFormbttfr btf){
        dffbultFormbt = btf;
    }

    /**
     * Rfturns tif <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf
     * bs b lbst rfsort, fg in dbsf b displby, fdit or null
     * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>
     * ibs not bffn spfdififd.
     *
     * @rfturn JFormbttfdTfxtFifld.AbstrbdtFormbttfr usfd if b morf spfdifid
     *         onf is not spfdififd.
     */
    publid JFormbttfdTfxtFifld.AbstrbdtFormbttfr gftDffbultFormbttfr() {
        rfturn dffbultFormbt;
    }

    /**
     * Sfts tif <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf if
     * tif <dodf>JFormbttfdTfxtFifld</dodf> is not bfing fditfd bnd fitifr
     * tif vbluf is not-null, or tif vbluf is null bnd b null formbttfr ibs
     * ibs not bffn spfdififd.
     *
     * @pbrbm btf JFormbttfdTfxtFifld.AbstrbdtFormbttfr to usf wifn tif
     *            JFormbttfdTfxtFifld dofs not ibvf fodus
     */
    publid void sftDisplbyFormbttfr(JFormbttfdTfxtFifld.AbstrbdtFormbttfr btf){
        displbyFormbt = btf;
    }

    /**
     * Rfturns tif <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf
     * if tif <dodf>JFormbttfdTfxtFifld</dodf> is not bfing fditfd bnd fitifr
     * tif vbluf is not-null, or tif vbluf is null bnd b null formbttfr ibs
     * ibs not bffn spfdififd.
     *
     * @rfturn JFormbttfdTfxtFifld.AbstrbdtFormbttfr to usf wifn tif
     *         JFormbttfdTfxtFifld dofs not ibvf fodus
     */
    publid JFormbttfdTfxtFifld.AbstrbdtFormbttfr gftDisplbyFormbttfr() {
        rfturn displbyFormbt;
    }

    /**
     * Sfts tif <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf if
     * tif <dodf>JFormbttfdTfxtFifld</dodf> is bfing fditfd bnd fitifr
     * tif vbluf is not-null, or tif vbluf is null bnd b null formbttfr ibs
     * ibs not bffn spfdififd.
     *
     * @pbrbm btf JFormbttfdTfxtFifld.AbstrbdtFormbttfr to usf wifn tif
     *            domponfnt ibs fodus
     */
    publid void sftEditFormbttfr(JFormbttfdTfxtFifld.AbstrbdtFormbttfr btf) {
        fditFormbt = btf;
    }

    /**
     * Rfturns tif <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> to usf
     * if tif <dodf>JFormbttfdTfxtFifld</dodf> is bfing fditfd bnd fitifr
     * tif vbluf is not-null, or tif vbluf is null bnd b null formbttfr ibs
     * ibs not bffn spfdififd.
     *
     * @rfturn JFormbttfdTfxtFifld.AbstrbdtFormbttfr to usf wifn tif
     *         domponfnt ibs fodus
     */
    publid JFormbttfdTfxtFifld.AbstrbdtFormbttfr gftEditFormbttfr() {
        rfturn fditFormbt;
    }

    /**
     * Sfts tif formbttfr to usf if tif vbluf of tif JFormbttfdTfxtFifld is
     * null.
     *
     * @pbrbm btf JFormbttfdTfxtFifld.AbstrbdtFormbttfr to usf wifn
     * tif vbluf of tif JFormbttfdTfxtFifld is null.
     */
    publid void sftNullFormbttfr(JFormbttfdTfxtFifld.AbstrbdtFormbttfr btf) {
        nullFormbt = btf;
    }

    /**
     * Rfturns tif formbttfr to usf if tif vbluf is null.
     *
     * @rfturn JFormbttfdTfxtFifld.AbstrbdtFormbttfr to usf wifn tif vbluf is
     *         null
     */
    publid JFormbttfdTfxtFifld.AbstrbdtFormbttfr gftNullFormbttfr() {
        rfturn nullFormbt;
    }

    /**
     * Rfturns fitifr tif dffbult formbttfr, displby formbttfr, fditor
     * formbttfr or null formbttfr bbsfd on tif stbtf of tif
     * JFormbttfdTfxtFifld.
     *
     * @pbrbm sourdf JFormbttfdTfxtFifld rfqufsting
     *               JFormbttfdTfxtFifld.AbstrbdtFormbttfr
     * @rfturn JFormbttfdTfxtFifld.AbstrbdtFormbttfr to ibndlf
     *         formbtting dutifs.
     */
    publid JFormbttfdTfxtFifld.AbstrbdtFormbttfr gftFormbttfr(
                     JFormbttfdTfxtFifld sourdf) {
        JFormbttfdTfxtFifld.AbstrbdtFormbttfr formbt = null;

        if (sourdf == null) {
            rfturn null;
        }
        Objfdt vbluf = sourdf.gftVbluf();

        if (vbluf == null) {
            formbt = gftNullFormbttfr();
        }
        if (formbt == null) {
            if (sourdf.ibsFodus()) {
                formbt = gftEditFormbttfr();
            }
            flsf {
                formbt = gftDisplbyFormbttfr();
            }
            if (formbt == null) {
                formbt = gftDffbultFormbttfr();
            }
        }
        rfturn formbt;
    }
}
