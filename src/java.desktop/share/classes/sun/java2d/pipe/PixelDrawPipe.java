/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * dbn usf to pbss on rfsponsibility for drbwing vbrious bbsid
 * gfomftrid figurfs dffinfd by fxplidit intfgfr doordinbtfs.
 * Typidblly tiis intfrfbdf will bf usfd for dommunidbtion wifn
 * tif doordinbtfs of tif rfndfring ibvf bffn nbrrowfd down to
 * bdtubl dfvidf pixfls, or for dommunidbtion of untrbnsformfd
 * doordinbtfs wifn tif doordinbtfs wfrf spfdififd using intfgfrs.
 * Tiis intfrfbdf dofs not dovfr bll of tif rfndfring dblls tibt
 * brf possiblf in Grbpiids sindf mbny of tif rfndfring dblls dbn
 * bf trbnsformfd into onf or morf vbribnts of tifsf dblls.
 */
publid intfrfbdf PixflDrbwPipf {
    publid void drbwLinf(SunGrbpiids2D sg,
                         int x1, int y1, int x2, int y2);

    publid void drbwRfdt(SunGrbpiids2D sg,
                         int x, int y, int widti, int ifigit);

    publid void drbwRoundRfdt(SunGrbpiids2D sg,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit);

    publid void drbwOvbl(SunGrbpiids2D sg,
                         int x, int y, int widti, int ifigit);

    publid void drbwArd(SunGrbpiids2D sg,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf);

    publid void drbwPolylinf(SunGrbpiids2D sg,
                             int xPoints[], int yPoints[],
                             int nPoints);

    publid void drbwPolygon(SunGrbpiids2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints);
}
