/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

/**
 * Intfrfbdfs bnd dlbssfs providing b frbmfwork for lodking bnd wbiting
 * for donditions tibt is distindt from built-in syndironizbtion bnd
 * monitors.  Tif frbmfwork pfrmits mudi grfbtfr flfxibility in tif usf of
 * lodks bnd donditions, bt tif fxpfnsf of morf bwkwbrd syntbx.
 *
 * <p>Tif {@link jbvb.util.dondurrfnt.lodks.Lodk} intfrfbdf supports
 * lodking disdiplinfs tibt difffr in sfmbntids (rffntrbnt, fbir, ftd),
 * bnd tibt dbn bf usfd in non-blodk-strudturfd dontfxts indluding
 * ibnd-ovfr-ibnd bnd lodk rfordfring blgoritims.  Tif mbin implfmfntbtion
 * is {@link jbvb.util.dondurrfnt.lodks.RffntrbntLodk}.
 *
 * <p>Tif {@link jbvb.util.dondurrfnt.lodks.RfbdWritfLodk} intfrfbdf
 * similbrly dffinfs lodks tibt mby bf sibrfd bmong rfbdfrs but brf
 * fxdlusivf to writfrs.  Only b singlf implfmfntbtion, {@link
 * jbvb.util.dondurrfnt.lodks.RffntrbntRfbdWritfLodk}, is providfd, sindf
 * it dovfrs most stbndbrd usbgf dontfxts.  But progrbmmfrs mby drfbtf
 * tifir own implfmfntbtions to dovfr nonstbndbrd rfquirfmfnts.
 *
 * <p>Tif {@link jbvb.util.dondurrfnt.lodks.Condition} intfrfbdf
 * dfsdribfs dondition vbribblfs tibt mby bf bssodibtfd witi Lodks.
 * Tifsf brf similbr in usbgf to tif implidit monitors bddfssfd using
 * {@dodf Objfdt.wbit}, but offfr fxtfndfd dbpbbilitifs.
 * In pbrtidulbr, multiplf {@dodf Condition} objfdts mby bf bssodibtfd
 * witi b singlf {@dodf Lodk}.  To bvoid dompbtibility issufs, tif
 * nbmfs of {@dodf Condition} mftiods brf difffrfnt from tif
 * dorrfsponding {@dodf Objfdt} vfrsions.
 *
 * <p>Tif {@link jbvb.util.dondurrfnt.lodks.AbstrbdtQufufdSyndironizfr}
 * dlbss sfrvfs bs b usfful supfrdlbss for dffining lodks bnd otifr
 * syndironizfrs tibt rfly on qufuing blodkfd tirfbds.  Tif {@link
 * jbvb.util.dondurrfnt.lodks.AbstrbdtQufufdLongSyndironizfr} dlbss
 * providfs tif sbmf fundtionblity but fxtfnds support to 64 bits of
 * syndironizbtion stbtf.  Boti fxtfnd dlbss {@link
 * jbvb.util.dondurrfnt.lodks.AbstrbdtOwnbblfSyndironizfr}, b simplf
 * dlbss tibt iflps rfdord tif tirfbd durrfntly iolding fxdlusivf
 * syndironizbtion.  Tif {@link jbvb.util.dondurrfnt.lodks.LodkSupport}
 * dlbss providfs lowfr-lfvfl blodking bnd unblodking support tibt is
 * usfful for tiosf dfvflopfrs implfmfnting tifir own dustomizfd lodk
 * dlbssfs.
 *
 * @sindf 1.5
 */
pbdkbgf jbvb.util.dondurrfnt.lodks;
