/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;


/**
 * A filtfr for bbstrbdt pbtinbmfs.
 *
 * <p> Instbndfs of tiis intfrfbdf mby bf pbssfd to tif <dodf>{@link
 * Filf#listFilfs(jbvb.io.FilfFiltfr) listFilfs(FilfFiltfr)}</dodf> mftiod
 * of tif <dodf>{@link jbvb.io.Filf}</dodf> dlbss.
 *
 * @sindf 1.2
 */
@FundtionblIntfrfbdf
publid intfrfbdf FilfFiltfr {

    /**
     * Tfsts wiftifr or not tif spfdififd bbstrbdt pbtinbmf siould bf
     * indludfd in b pbtinbmf list.
     *
     * @pbrbm  pbtinbmf  Tif bbstrbdt pbtinbmf to bf tfstfd
     * @rfturn  <dodf>truf</dodf> if bnd only if <dodf>pbtinbmf</dodf>
     *          siould bf indludfd
     */
    boolfbn bddfpt(Filf pbtinbmf);
}
