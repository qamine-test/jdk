/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis pbdkbgf dontbins intfrnbl dommon dodf for implfmfnting trbding
 * frbmfworks, bnd dffinfd b numbfr of fxisting frbmfworks.
 * <p>
 * Tifrf brf four trbding frbmfworks durrfntly dffinfd.  Tif "Null" bnd
 * "Multiplfx" frbmfworks brf usfd intfrnblly bs pbrt of tif implfmfntbtion.
 * Tif "DTrbdf" frbmfwork is tif primf donsumfr frbmfwork bt tif momfnt,
 * wiilf tif "PrintStrfbm" frbmfwork is b fundtionbl, but iiddfn, frbmfwork
 * wiidi dbn bf usfd to trbdk probf firings.  All but tif "DTrbdf" frbmfwork
 * brf dffinfd in tiis pbdkbgf.  Tif "DTrbdf" frbmfwork is implfmfntfd in tif
 * {@dodf sun.trbding.dtrbdf} pbdkbgf.
 * <p>
 * Tiis pbdkbgf blso dontbins tif {@dodf ProvidfrSkflfton} dlbss, wiidi
 * iolds most of tif dommon dodf nffdfd for implfmfnting frbmfworks.
 * <p>
 * Tif "Null" frbmfwork is usfd wifn tifrf brf no otifr bdtivf frbmfworks.
 * It bddomplisifs bbsolutfly notiing bnd is mfrfly b plbdfioldfr so tibt
 * tif bpplidbtion dbn dbll tif trbding routinfs witiout frror.
 * <p>
 * Tif "Multiplfx" frbmfwork is usfd wifn tifrf brf multiplf bdtivf frbmfworks.
 * It is initiblizfd witi tif frbmfwork fbdtorifs bnd drfbtf providfrs bnd
 * probfs tibt dispbtdi to fbdi bdtivf frbmfwork in turn.
 * <p>
 * Tif "PrintStrfbm" frbmfwork is durrfntly b dfbugging frbmfwork wiidi
 * dispbtdifs trbdf dblls to b usfr-dffinfd PrintStrfbm dlbss, dffinfd by
 * b propfrty.  It mby somf dby bf opfnfd up to gfnfrbl usf.
 * <p>
 * Sff tif {@dodf sun.trbding.dtrbdf} bnd {@dodf dom.sun.trbding.dtrbdf}
 * pbdkbgfs for informbtion on tif "DTrbdf" frbmfwork.
 */

pbdkbgf sun.trbding;
