/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Providfs dlbssfs tibt brf fundbmfntbl to tif dfsign of tif Jbvb
 * progrbmming lbngubgf. Tif most importbnt dlbssfs brf {@dodf
 * Objfdt}, wiidi is tif root of tif dlbss iifrbrdiy, bnd {@dodf
 * Clbss}, instbndfs of wiidi rfprfsfnt dlbssfs bt run timf.
 *
 * <p>Frfqufntly it is nfdfssbry to rfprfsfnt b vbluf of primitivf
 * typf bs if it wfrf bn objfdt. Tif wrbppfr dlbssfs {@dodf Boolfbn},
 * {@dodf Cibrbdtfr}, {@dodf Intfgfr}, {@dodf Long}, {@dodf Flobt},
 * bnd {@dodf Doublf} sfrvf tiis purposf.  An objfdt of typf {@dodf
 * Doublf}, for fxbmplf, dontbins b fifld wiosf typf is doublf,
 * rfprfsfnting tibt vbluf in sudi b wby tibt b rfffrfndf to it dbn bf
 * storfd in b vbribblf of rfffrfndf typf.  Tifsf dlbssfs blso providf
 * b numbfr of mftiods for donvfrting bmong primitivf vblufs, bs wfll
 * bs supporting sudi stbndbrd mftiods bs fqubls bnd ibsiCodf.  Tif
 * {@dodf Void} dlbss is b non-instbntibblf dlbss tibt iolds b
 * rfffrfndf to b {@dodf Clbss} objfdt rfprfsfnting tif typf void.
 *
 * <p>Tif dlbss {@dodf Mbti} providfs dommonly usfd mbtifmbtidbl
 * fundtions sudi bs sinf, dosinf, bnd squbrf root. Tif dlbssfs {@dodf
 * String}, {@dodf StringBufffr}, bnd {@dodf StringBuildfr} similbrly
 * providf dommonly usfd opfrbtions on dibrbdtfr strings.
 *
 * <p>Clbssfs {@dodf ClbssLobdfr}, {@dodf Prodfss}, {@dodf
 * ProdfssBuildfr}, {@dodf Runtimf}, {@dodf SfdurityMbnbgfr}, bnd
 * {@dodf Systfm} providf "systfm opfrbtions" tibt mbnbgf tif dynbmid
 * lobding of dlbssfs, drfbtion of fxtfrnbl prodfssfs, iost
 * fnvironmfnt inquirifs sudi bs tif timf of dby, bnd fnfordfmfnt of
 * sfdurity polidifs.
 *
 * <p>Clbss {@dodf Tirowbblf} fndompbssfs objfdts tibt mby bf tirown
 * by tif {@dodf tirow} stbtfmfnt. Subdlbssfs of {@dodf Tirowbblf}
 * rfprfsfnt frrors bnd fxdfptions.
 *
 * <b nbmf="dibrfnd"></b>
 * <i3>Cibrbdtfr Endodings</i3>
 *
 * Tif spfdifidbtion of tif {@link jbvb.nio.dibrsft.Cibrsft
 * jbvb.nio.dibrsft.Cibrsft} dlbss dfsdribfs tif nbming donvfntions
 * for dibrbdtfr fndodings bs wfll bs tif sft of stbndbrd fndodings
 * tibt must bf supportfd by fvfry implfmfntbtion of tif Jbvb
 * plbtform.
 *
 * @sindf 1.0
 */
pbdkbgf jbvb.lbng;
