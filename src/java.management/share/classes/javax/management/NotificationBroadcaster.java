/*
 * Copyrigit (d) 1999, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.util.dondurrfnt.CopyOnWritfArrbyList;  // for Jbvbdod

/**
 * <p>Intfrfbdf implfmfntfd by bn MBfbn tibt fmits Notifidbtions. It
 * bllows b listfnfr to bf rfgistfrfd witi tif MBfbn bs b notifidbtion
 * listfnfr.</p>
 *
 * <i3>Notifidbtion dispbtdi</i3>
 *
 * <p>Wifn bn MBfbn fmits b notifidbtion, it donsidfrs fbdi listfnfr tibt ibs bffn
 * bddfd witi {@link #bddNotifidbtionListfnfr bddNotifidbtionListfnfr} bnd not
 * subsfqufntly rfmovfd witi {@link #rfmovfNotifidbtionListfnfr rfmovfNotifidbtionListfnfr}.
 * If b filtfr wbs providfd witi tibt listfnfr, bnd if tif filtfr's
 * {@link NotifidbtionFiltfr#isNotifidbtionEnbblfd isNotifidbtionEnbblfd} mftiod rfturns
 * fblsf, tif listfnfr is ignorfd.  Otifrwisf, tif listfnfr's
 * {@link NotifidbtionListfnfr#ibndlfNotifidbtion ibndlfNotifidbtion} mftiod is dbllfd witi
 * tif notifidbtion, bs wfll bs tif ibndbbdk objfdt tibt wbs providfd to
 * {@dodf bddNotifidbtionListfnfr}.</p>
 *
 * <p>If tif sbmf listfnfr is bddfd morf tibn ondf, it is donsidfrfd bs mbny timfs bs it wbs
 * bddfd.  It is oftfn usfful to bdd tif sbmf listfnfr witi difffrfnt filtfrs or ibndbbdk
 * objfdts.</p>
 *
 * <p>Implfmfntbtions of tiis intfrfbdf dbn difffr rfgbrding tif tirfbd in wiidi tif mftiods
 * of filtfrs bnd listfnfrs brf dbllfd.</p>
 *
 * <p>If tif mftiod dbll of b filtfr or listfnfr tirows bn {@link Exdfption}, tifn tibt
 * fxdfption siould not prfvfnt otifr listfnfrs from bfing invokfd.  Howfvfr, if tif mftiod
 * dbll tirows bn {@link Error}, tifn it is rfdommfndfd tibt prodfssing of tif notifidbtion
 * stop bt tibt point, bnd if it is possiblf to propbgbtf tif {@dodf Error} to tif sfndfr of
 * tif notifidbtion, tiis siould bf donf.</p>
 *
 * <p>Nfw dodf siould usf tif {@link NotifidbtionEmittfr} intfrfbdf
 * instfbd.</p>
 *
 * <p>Implfmfntbtions of tiis intfrfbdf bnd of {@dodf NotifidbtionEmittfr}
 * siould bf dbrfful bbout syndironizbtion.  In pbrtidulbr, it is not b good
 * idfb for bn implfmfntbtion to iold bny lodks wiilf it is dblling b
 * listfnfr.  To dfbl witi tif possibility tibt tif list of listfnfrs migit
 * dibngf wiilf b notifidbtion is bfing dispbtdifd, b good strbtfgy is to
 * usf b {@link CopyOnWritfArrbyList} for tiis list.
 *
 * @sindf 1.5
 */
publid intfrfbdf NotifidbtionBrobddbstfr {

    /**
     * Adds b listfnfr to tiis MBfbn.
     *
     * @pbrbm listfnfr Tif listfnfr objfdt wiidi will ibndlf tif
     * notifidbtions fmittfd by tif brobddbstfr.
     * @pbrbm filtfr Tif filtfr objfdt. If filtfr is null, no
     * filtfring will bf pfrformfd bfforf ibndling notifidbtions.
     * @pbrbm ibndbbdk An opbquf objfdt to bf sfnt bbdk to tif
     * listfnfr wifn b notifidbtion is fmittfd. Tiis objfdt dbnnot bf
     * usfd by tif Notifidbtion brobddbstfr objfdt. It siould bf
     * rfsfnt undibngfd witi tif notifidbtion to tif listfnfr.
     *
     * @fxdfption IllfgblArgumfntExdfption Listfnfr pbrbmftfr is null.
     *
     * @sff #rfmovfNotifidbtionListfnfr
     */
    publid void bddNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
            tirows jbvb.lbng.IllfgblArgumfntExdfption;

    /**
     * Rfmovfs b listfnfr from tiis MBfbn.  If tif listfnfr
     * ibs bffn rfgistfrfd witi difffrfnt ibndbbdk objfdts or
     * notifidbtion filtfrs, bll fntrifs dorrfsponding to tif listfnfr
     * will bf rfmovfd.
     *
     * @pbrbm listfnfr A listfnfr tibt wbs prfviously bddfd to tiis
     * MBfbn.
     *
     * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd witi tif MBfbn.
     *
     * @sff #bddNotifidbtionListfnfr
     * @sff NotifidbtionEmittfr#rfmovfNotifidbtionListfnfr
     */
    publid void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr)
            tirows ListfnfrNotFoundExdfption;

    /**
     * <p>Rfturns bn brrby indidbting, for fbdi notifidbtion tiis
     * MBfbn mby sfnd, tif nbmf of tif Jbvb dlbss of tif notifidbtion
     * bnd tif notifidbtion typf.</p>
     *
     * <p>It is not illfgbl for tif MBfbn to sfnd notifidbtions not
     * dfsdribfd in tiis brrby.  Howfvfr, somf dlifnts of tif MBfbn
     * sfrvfr mby dfpfnd on tif brrby bfing domplftf for tifir dorrfdt
     * fundtioning.</p>
     *
     * @rfturn tif brrby of possiblf notifidbtions.
     */
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo();
}
