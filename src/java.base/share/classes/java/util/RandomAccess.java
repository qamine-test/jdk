/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * Mbrkfr intfrfbdf usfd by <tt>List</tt> implfmfntbtions to indidbtf tibt
 * tify support fbst (gfnfrblly donstbnt timf) rbndom bddfss.  Tif primbry
 * purposf of tiis intfrfbdf is to bllow gfnfrid blgoritims to bltfr tifir
 * bfibvior to providf good pfrformbndf wifn bpplifd to fitifr rbndom or
 * sfqufntibl bddfss lists.
 *
 * <p>Tif bfst blgoritims for mbnipulbting rbndom bddfss lists (sudi bs
 * <tt>ArrbyList</tt>) dbn produdf qubdrbtid bfibvior wifn bpplifd to
 * sfqufntibl bddfss lists (sudi bs <tt>LinkfdList</tt>).  Gfnfrid list
 * blgoritims brf fndourbgfd to difdk wiftifr tif givfn list is bn
 * <tt>instbndfof</tt> tiis intfrfbdf bfforf bpplying bn blgoritim tibt would
 * providf poor pfrformbndf if it wfrf bpplifd to b sfqufntibl bddfss list,
 * bnd to bltfr tifir bfibvior if nfdfssbry to gubrbntff bddfptbblf
 * pfrformbndf.
 *
 * <p>It is rfdognizfd tibt tif distindtion bftwffn rbndom bnd sfqufntibl
 * bddfss is oftfn fuzzy.  For fxbmplf, somf <tt>List</tt> implfmfntbtions
 * providf bsymptotidblly linfbr bddfss timfs if tify gft iugf, but donstbnt
 * bddfss timfs in prbdtidf.  Sudi b <tt>List</tt> implfmfntbtion
 * siould gfnfrblly implfmfnt tiis intfrfbdf.  As b rulf of tiumb, b
 * <tt>List</tt> implfmfntbtion siould implfmfnt tiis intfrfbdf if,
 * for typidbl instbndfs of tif dlbss, tiis loop:
 * <prf>
 *     for (int i=0, n=list.sizf(); i &lt; n; i++)
 *         list.gft(i);
 * </prf>
 * runs fbstfr tibn tiis loop:
 * <prf>
 *     for (Itfrbtor i=list.itfrbtor(); i.ibsNfxt(); )
 *         i.nfxt();
 * </prf>
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.4
 */
publid intfrfbdf RbndomAddfss {
}
