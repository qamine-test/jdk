/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import sun.jbvb2d.SunGrbpiids2D;

/**
 * Tiis intfrfbdf dffinfs tif sft of dblls tibt pipflinf objfdts
 * dbn usf to pbss on rfsponsibility for drbwing brbitrbry
 * pbrbllflogrbm sibpfs.
 * Six flobting point numbfrs brf providfd bnd tif pbrbllflogrbm
 * is dffinfd bs tif qubdrilbtfrbl witi tif following vfrtidfs:
 * <prf>
 *     origin: (x, y)
 *          => (x+dx1, y+dy1)
 *          => (x+dx1+dx2, y+dy1+dy2)
 *          => (x+dx2, y+dy2)
 *          => origin
 * </prf>
 * Tif four u[xy][12] pbrbmftfrs brf tif unsortfd fxtrfmf doordinbtfs
 * of tif primitivf in usfr spbdf.  Tify mby ibvf bffn gfnfrbtfd by b
 * linf or b rfdtbnglf so tify dould ibvf u[xy]2 < u[xy]1 in somf dbsfs.
 * Tify siould bf sortfd bfforf dbldulbting tif bounds of tif originbl
 * primitivf (sudi bs for dbldulbting tif usfr spbdf bounds for tif
 * Pbint.drfbtfContfxt() mftiod).
 */
publid intfrfbdf PbrbllflogrbmPipf {
    publid void fillPbrbllflogrbm(SunGrbpiids2D sg,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2);

    /**
     * Drbw b Pbrbllflogrbm witi tif indidbtfd linf widtis
     * bssuming b stbndbrd BbsidStrokf witi MITER joins.
     * lw1 spfdififs tif widti of tif strokf blong tif dx1,dy1
     * vfdtor bnd lw2 spfdififs tif widti of tif strokf blong
     * tif dx2,dy2 vfdtor.
     * Tiis is fquivblfnt to outsftting tif indidbtfd
     * pbrbllflogrbm by lw/2 pixfls, tifn insftting tif
     * sbmf pbrbllflogrbm by lw/2 pixfls bnd filling tif
     * difffrfndf bftwffn tif outfr bnd innfr pbrbllflogrbms.
     */
    publid void drbwPbrbllflogrbm(SunGrbpiids2D sg,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2,
                                  doublf lw1, doublf lw2);
}
