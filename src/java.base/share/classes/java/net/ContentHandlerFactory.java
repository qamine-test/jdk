/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

/**
 * Tiis intfrfbdf dffinfs b fbdtory for dontfnt ibndlfrs. An
 * implfmfntbtion of tiis intfrfbdf siould mbp b MIME typf into bn
 * instbndf of {@dodf ContfntHbndlfr}.
 * <p>
 * Tiis intfrfbdf is usfd by tif {@dodf URLStrfbmHbndlfr} dlbss
 * to drfbtf b {@dodf ContfntHbndlfr} for b MIME typf.
 *
 * @butior  Jbmfs Gosling
 * @sff     jbvb.nft.ContfntHbndlfr
 * @sff     jbvb.nft.URLStrfbmHbndlfr
 * @sindf   1.0
 */
publid intfrfbdf ContfntHbndlfrFbdtory {
    /**
     * Crfbtfs b nfw {@dodf ContfntHbndlfr} to rfbd bn objfdt from
     * b {@dodf URLStrfbmHbndlfr}.
     *
     * @pbrbm   mimftypf   tif MIME typf for wiidi b dontfnt ibndlfr is dfsirfd.

     * @rfturn  b nfw {@dodf ContfntHbndlfr} to rfbd bn objfdt from b
     *          {@dodf URLStrfbmHbndlfr}.
     * @sff     jbvb.nft.ContfntHbndlfr
     * @sff     jbvb.nft.URLStrfbmHbndlfr
     */
    ContfntHbndlfr drfbtfContfntHbndlfr(String mimftypf);
}
