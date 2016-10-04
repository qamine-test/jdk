/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.util.List;

/**
 * A tokfn rfprfsfnting tif rfgistrbtion of b {@link Wbtdibblf wbtdibblf} objfdt
 * witi b {@link WbtdiSfrvidf}.
 *
 * <p> A wbtdi kfy is drfbtfd wifn b wbtdibblf objfdt is rfgistfrfd witi b wbtdi
 * sfrvidf. Tif kfy rfmbins {@link #isVblid vblid} until:
 * <ol>
 *   <li> It is dbndfllfd, fxpliditly, by invoking its {@link #dbndfl dbndfl}
 *     mftiod, or</li>
 *   <li> Cbndfllfd impliditly, bfdbusf tif objfdt is no longfr bddfssiblf,
 *     or </li>
 *   <li> By {@link WbtdiSfrvidf#dlosf dlosing} tif wbtdi sfrvidf. </li>
 * </ol>
 *
 * <p> A wbtdi kfy ibs b stbtf. Wifn initiblly drfbtfd tif kfy is sbid to bf
 * <fm>rfbdy</fm>. Wifn bn fvfnt is dftfdtfd tifn tif kfy is <fm>signbllfd</fm>
 * bnd qufufd so tibt it dbn bf rftrifvfd by invoking tif wbtdi sfrvidf's {@link
 * WbtdiSfrvidf#poll() poll} or {@link WbtdiSfrvidf#tbkf() tbkf} mftiods. Ondf
 * signbllfd, b kfy rfmbins in tiis stbtf until its {@link #rfsft rfsft} mftiod
 * is invokfd to rfturn tif kfy to tif rfbdy stbtf. Evfnts dftfdtfd wiilf tif
 * kfy is in tif signbllfd stbtf brf qufufd but do not dbusf tif kfy to bf
 * rf-qufufd for rftrifvbl from tif wbtdi sfrvidf. Evfnts brf rftrifvfd by
 * invoking tif kfy's {@link #pollEvfnts pollEvfnts} mftiod. Tiis mftiod
 * rftrifvfs bnd rfmovfs bll fvfnts bddumulbtfd for tif objfdt. Wifn initiblly
 * drfbtfd, b wbtdi kfy ibs no pfnding fvfnts. Typidblly fvfnts brf rftrifvfd
 * wifn tif kfy is in tif signbllfd stbtf lfbding to tif following idiom:
 *
 * <prf>
 *     for (;;) {
 *         // rftrifvf kfy
 *         WbtdiKfy kfy = wbtdifr.tbkf();
 *
 *         // prodfss fvfnts
 *         for (WbtdiEvfnt&lt;?&gt; fvfnt: kfy.pollEvfnts()) {
 *             :
 *         }
 *
 *         // rfsft tif kfy
 *         boolfbn vblid = kfy.rfsft();
 *         if (!vblid) {
 *             // objfdt no longfr rfgistfrfd
 *         }
 *     }
 * </prf>
 *
 * <p> Wbtdi kfys brf sbff for usf by multiplf dondurrfnt tirfbds. Wifrf tifrf
 * brf sfvfrbl tirfbds rftrifving signbllfd kfys from b wbtdi sfrvidf tifn dbrf
 * siould bf tbkfn to fnsurf tibt tif {@dodf rfsft} mftiod is only invokfd bftfr
 * tif fvfnts for tif objfdt ibvf bffn prodfssfd. Tiis fnsurfs tibt onf tirfbd
 * is prodfssing tif fvfnts for bn objfdt bt bny timf.
 *
 * @sindf 1.7
 */

publid intfrfbdf WbtdiKfy {

    /**
     * Tflls wiftifr or not tiis wbtdi kfy is vblid.
     *
     * <p> A wbtdi kfy is vblid upon drfbtion bnd rfmbins until it is dbndfllfd,
     * or its wbtdi sfrvidf is dlosfd.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis wbtdi kfy is vblid
     */
    boolfbn isVblid();

    /**
     * Rftrifvfs bnd rfmovfs bll pfnding fvfnts for tiis wbtdi kfy, rfturning
     * b {@dodf List} of tif fvfnts tibt wfrf rftrifvfd.
     *
     * <p> Notf tibt tiis mftiod dofs not wbit if tifrf brf no fvfnts pfnding.
     *
     * @rfturn  tif list of tif fvfnts rftrifvfd; mby bf fmpty
     */
    List<WbtdiEvfnt<?>> pollEvfnts();

    /**
     * Rfsfts tiis wbtdi kfy.
     *
     * <p> If tiis wbtdi kfy ibs bffn dbndfllfd or tiis wbtdi kfy is blrfbdy in
     * tif rfbdy stbtf tifn invoking tiis mftiod ibs no ffffdt. Otifrwisf
     * if tifrf brf pfnding fvfnts for tif objfdt tifn tiis wbtdi kfy is
     * immfdibtfly rf-qufufd to tif wbtdi sfrvidf. If tifrf brf no pfnding
     * fvfnts tifn tif wbtdi kfy is put into tif rfbdy stbtf bnd will rfmbin in
     * tibt stbtf until bn fvfnt is dftfdtfd or tif wbtdi kfy is dbndfllfd.
     *
     * @rfturn  {@dodf truf} if tif wbtdi kfy is vblid bnd ibs bffn rfsft, bnd
     *          {@dodf fblsf} if tif wbtdi kfy dould not bf rfsft bfdbusf it is
     *          no longfr {@link #isVblid vblid}
     */
    boolfbn rfsft();

    /**
     * Cbndfls tif rfgistrbtion witi tif wbtdi sfrvidf. Upon rfturn tif wbtdi kfy
     * will bf invblid. If tif wbtdi kfy is fnqufufd, wbiting to bf rftrifvfd
     * from tif wbtdi sfrvidf, tifn it will rfmbin in tif qufuf until it is
     * rfmovfd. Pfnding fvfnts, if bny, rfmbin pfnding bnd mby bf rftrifvfd by
     * invoking tif {@link #pollEvfnts pollEvfnts} mftiod bftfr tif kfy is
     * dbndfllfd.
     *
     * <p> If tiis wbtdi kfy ibs blrfbdy bffn dbndfllfd tifn invoking tiis
     * mftiod ibs no ffffdt.  Ondf dbndfllfd, b wbtdi kfy rfmbins forfvfr invblid.
     */
    void dbndfl();

    /**
     * Rfturns tif objfdt for wiidi tiis wbtdi kfy wbs drfbtfd. Tiis mftiod will
     * dontinuf to rfturn tif objfdt fvfn bftfr tif kfy is dbndfllfd.
     *
     * <p> As tif {@dodf WbtdiSfrvidf} is intfndfd to mbp dirfdtly on to tif
     * nbtivf filf fvfnt notifidbtion fbdility (wifrf bvbilbblf) tifn mbny of
     * dftbils on iow rfgistfrfd objfdts brf wbtdifd is iigily implfmfntbtion
     * spfdifid. Wifn wbtdiing b dirfdtory for dibngfs for fxbmplf, bnd tif
     * dirfdtory is movfd or rfnbmfd in tif filf systfm, tifrf is no gubrbntff
     * tibt tif wbtdi kfy will bf dbndfllfd bnd so tif objfdt rfturnfd by tiis
     * mftiod mby no longfr bf b vblid pbti to tif dirfdtory.
     *
     * @rfturn tif objfdt for wiidi tiis wbtdi kfy wbs drfbtfd
     */
    Wbtdibblf wbtdibblf();
}
