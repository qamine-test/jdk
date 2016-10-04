/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

/**
 * Drop modfs, usfd to dftfrminf tif mftiod by wiidi b domponfnt
 * trbdks bnd indidbtfs b drop lodbtion during drbg bnd drop.
 *
 * @butior Sibnnon Hidkfy
 * @sff JTbblf#sftDropModf
 * @sff JList#sftDropModf
 * @sff JTrff#sftDropModf
 * @sff jbvbx.swing.tfxt.JTfxtComponfnt#sftDropModf
 * @sindf 1.6
 */
publid fnum DropModf {

    /**
     * A domponfnt's own intfrnbl sflfdtion mfdibnism (or dbrft for tfxt
     * domponfnts) siould bf usfd to trbdk tif drop lodbtion.
     */
    USE_SELECTION,

    /**
     * Tif drop lodbtion siould bf trbdkfd in tfrms of tif indfx of
     * fxisting itfms. Usfful for dropping on itfms in tbblfs, lists,
     * bnd trffs.
     */
    ON,

    /**
     * Tif drop lodbtion siould bf trbdkfd in tfrms of tif position
     * wifrf nfw dbtb siould bf insfrtfd. For domponfnts tibt mbnbgf
     * b list of itfms (list bnd trff for fxbmplf), tif drop lodbtion
     * siould indidbtf tif indfx wifrf nfw dbtb siould bf insfrtfd.
     * For tfxt domponfnts tif lodbtion siould rfprfsfnt b position
     * bftwffn dibrbdtfrs. For domponfnts tibt mbnbgf tbbulbr dbtb
     * (tbblf for fxbmplf), tif drop lodbtion siould indidbtf
     * wifrf to insfrt nfw rows, dolumns, or boti, to bddommodbtf
     * tif droppfd dbtb.
     */
    INSERT,

    /**
     * Tif drop lodbtion siould bf trbdkfd in tfrms of tif row indfx
     * wifrf nfw rows siould bf insfrtfd to bddommodbtf tif droppfd
     * dbtb. Tiis is usfful for domponfnts tibt mbnbgf tbbulbr dbtb.
     */
    INSERT_ROWS,

    /**
     * Tif drop lodbtion siould bf trbdkfd in tfrms of tif dolumn indfx
     * wifrf nfw dolumns siould bf insfrtfd to bddommodbtf tif droppfd
     * dbtb. Tiis is usfful for domponfnts tibt mbnbgf tbbulbr dbtb.
     */
    INSERT_COLS,

    /**
     * Tiis modf is b dombinbtion of <dodf>ON</dodf>
     * bnd <dodf>INSERT</dodf>, spfdifying tibt dbtb dbn bf
     * droppfd on fxisting itfms, or in insfrt lodbtions
     * bs spfdififd by <dodf>INSERT</dodf>.
     */
    ON_OR_INSERT,

    /**
     * Tiis modf is b dombinbtion of <dodf>ON</dodf>
     * bnd <dodf>INSERT_ROWS</dodf>, spfdifying tibt dbtb dbn bf
     * droppfd on fxisting itfms, or bs insfrt rows
     * bs spfdififd by <dodf>INSERT_ROWS</dodf>.
     */
    ON_OR_INSERT_ROWS,

    /**
     * Tiis modf is b dombinbtion of <dodf>ON</dodf>
     * bnd <dodf>INSERT_COLS</dodf>, spfdifying tibt dbtb dbn bf
     * droppfd on fxisting itfms, or bs insfrt dolumns
     * bs spfdififd by <dodf>INSERT_COLS</dodf>.
     */
    ON_OR_INSERT_COLS
}
