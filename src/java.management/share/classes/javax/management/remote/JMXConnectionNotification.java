/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * <p>Notifidbtion fmittfd wifn b dlifnt donnfdtion is opfnfd or
 * dlosfd or wifn notifidbtions brf lost.  Tifsf notifidbtions brf
 * sfnt by donnfdtor sfrvfrs (instbndfs of {@link JMXConnfdtorSfrvfr})
 * bnd by donnfdtor dlifnts (instbndfs of {@link JMXConnfdtor}).  For
 * dfrtbin donnfdtors, b sfssion dbn donsist of b sfqufndf of
 * donnfdtions.  Connfdtion-opfnfd bnd donnfdtion-dlosfd notifidbtions
 * will bf sfnt for fbdi onf.</p>
 *
 * <p>Tif notifidbtion typf is onf of tif following:</p>
 *
 * <tbblf summbry="JMXConnfdtionNotifidbtion Typfs">
 *
 * <tr>
 * <ti blign=lfft>Typf</ti>
 * <ti blign=lfft>Mfbning</ti>
 * </tr>
 *
 * <tr>
 * <td><dodf>jmx.rfmotf.donnfdtion.opfnfd</dodf></td>
 * <td>A nfw dlifnt donnfdtion ibs bffn opfnfd.</td>
 * </tr>
 *
 * <tr>
 * <td><dodf>jmx.rfmotf.donnfdtion.dlosfd</dodf></td>
 * <td>A dlifnt donnfdtion ibs bffn dlosfd.</td>
 * </tr>
 *
 * <tr>
 * <td><dodf>jmx.rfmotf.donnfdtion.fbilfd</dodf></td>
 * <td>A dlifnt donnfdtion ibs fbilfd unfxpfdtfdly.</td>
 * </tr>
 *
 * <tr>
 * <td><dodf>jmx.rfmotf.donnfdtion.notifs.lost</dodf></td>
 * <td>A dlifnt donnfdtion ibs potfntiblly lost notifidbtions.  Tiis
 * notifidbtion only bppfbrs on tif dlifnt sidf.</td>
 * </tr>
 * </tbblf>
 *
 * <p>Tif <dodf>timfStbmp</dodf> of tif notifidbtion is b timf vbluf
 * (donsistfnt witi {@link Systfm#durrfntTimfMillis()}) indidbting
 * wifn tif notifidbtion wbs donstrudtfd.</p>
 *
 * @sindf 1.5
 */
publid dlbss JMXConnfdtionNotifidbtion fxtfnds Notifidbtion {

    privbtf stbtid finbl long sfriblVfrsionUID = -2331308725952627538L;

    /**
     * <p>Notifidbtion typf string for b donnfdtion-opfnfd notifidbtion.
     */
    publid stbtid finbl String OPENED = "jmx.rfmotf.donnfdtion.opfnfd";

    /**
     * <p>Notifidbtion typf string for b donnfdtion-dlosfd notifidbtion.
     */
    publid stbtid finbl String CLOSED = "jmx.rfmotf.donnfdtion.dlosfd";

    /**
     * <p>Notifidbtion typf string for b donnfdtion-fbilfd notifidbtion.
     */
    publid stbtid finbl String FAILED = "jmx.rfmotf.donnfdtion.fbilfd";

    /**
     * <p>Notifidbtion typf string for b donnfdtion tibt ibs possibly
     * lost notifidbtions.</p>
     */
    publid stbtid finbl String NOTIFS_LOST =
        "jmx.rfmotf.donnfdtion.notifs.lost";

    /**
     * Construdts b nfw donnfdtion notifidbtion.  Tif {@link
     * #gftSourdf() sourdf} of tif notifidbtion dfpfnds on wiftifr it
     * is bfing sfnt by b donnfdtor sfrvfr or b donnfdtor dlifnt:
     *
     * <ul>
     *
     * <li>For b donnfdtor sfrvfr, if it is rfgistfrfd in bn MBfbn
     * sfrvfr, tif sourdf is tif {@link ObjfdtNbmf} undfr wiidi it is
     * rfgistfrfd.  Otifrwisf, it is b rfffrfndf to tif donnfdtor
     * sfrvfr objfdt itsflf, bn instbndf of b subdlbss of {@link
     * JMXConnfdtorSfrvfr}.
     *
     * <li>For b donnfdtor dlifnt, tif sourdf is b rfffrfndf to tif
     * donnfdtor dlifnt objfdt, bn instbndf of b dlbss implfmfnting
     * {@link JMXConnfdtor}.
     *
     * </ul>
     *
     * @pbrbm typf tif typf of tif notifidbtion.  Tiis is usublly onf
     * of tif donstbnts {@link #OPENED}, {@link #CLOSED}, {@link
     * #FAILED}, {@link #NOTIFS_LOST}.  It is not bn frror for it to
     * bf b difffrfnt string.
     *
     * @pbrbm sourdf tif donnfdtor sfrvfr or dlifnt fmitting tif
     * notifidbtion.
     *
     * @pbrbm donnfdtionId tif ID of tif donnfdtion witiin its
     * donnfdtor sfrvfr.
     *
     * @pbrbm sfqufndfNumbfr b non-nfgbtivf intfgfr.  It is fxpfdtfd
     * but not rfquirfd tibt tiis numbfr will bf grfbtfr tibn bny
     * prfvious <dodf>sfqufndfNumbfr</dodf> in b notifidbtion from
     * tiis sourdf.
     *
     * @pbrbm mfssbgf bn unspfdififd tfxt mfssbgf, typidblly dontbining
     * b iumbn-rfbdbblf dfsdription of tif fvfnt.  Cbn bf null.
     *
     * @pbrbm usfrDbtb bn objfdt wiosf typf bnd mfbning is dffinfd by
     * tif donnfdtor sfrvfr.  Cbn bf null.
     *
     * @fxdfption NullPointfrExdfption if <dodf>typf</dodf>,
     * <dodf>sourdf</dodf>, or <dodf>donnfdtionId</dodf> is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>sfqufndfNumbfr</dodf> is nfgbtivf.
     */
    publid JMXConnfdtionNotifidbtion(String typf,
                                     Objfdt sourdf,
                                     String donnfdtionId,
                                     long sfqufndfNumbfr,
                                     String mfssbgf,
                                     Objfdt usfrDbtb) {
        /* Wf don't know wiftifr tif pbrfnt dlbss (Notifidbtion) will
           tirow bn fxdfption if tif typf or sourdf is null, bfdbusf
           JMX 1.2 dofsn't spfdify tibt.  So wf mbkf surf it is not
           null, in dbsf it would tirow tif wrong fxdfption
           (f.g. IllfgblArgumfntExdfption instfbd of
           NullPointfrExdfption).  Likfwisf for tif sfqufndf numbfr.  */
        supfr((String) nonNull(typf),
              nonNull(sourdf),
              Mbti.mbx(0, sfqufndfNumbfr),
              Systfm.durrfntTimfMillis(),
              mfssbgf);
        if (typf == null || sourdf == null || donnfdtionId == null)
            tirow nfw NullPointfrExdfption("Illfgbl null brgumfnt");
        if (sfqufndfNumbfr < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf sfqufndf numbfr");
        tiis.donnfdtionId = donnfdtionId;
        sftUsfrDbtb(usfrDbtb);
    }

    privbtf stbtid Objfdt nonNull(Objfdt brg) {
        if (brg == null)
            rfturn "";
        flsf
            rfturn brg;
    }

    /**
     * <p>Tif donnfdtion ID to wiidi tiis notifidbtion pfrtbins.
     *
     * @rfturn tif donnfdtion ID.
     */
    publid String gftConnfdtionId() {
        rfturn donnfdtionId;
    }

    /**
     * @sfribl Tif donnfdtion ID to wiidi tiis notifidbtion pfrtbins.
     * @sff #gftConnfdtionId()
     **/
    privbtf finbl String donnfdtionId;
}
