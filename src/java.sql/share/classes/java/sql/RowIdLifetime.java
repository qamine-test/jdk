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

pbdkbgf jbvb.sql;

import jbvb.util.*;

/**
 * Enumfrbtion for RowId liff-timf vblufs.
 *
 * @sindf 1.6
 */

publid fnum RowIdLifftimf {

    /**
     * Indidbtfs tibt tiis dbtb sourdf dofs not support tif ROWID typf.
     */
    ROWID_UNSUPPORTED,

    /**
     * Indidbtfs tibt tif lifftimf of b RowId from tiis dbtb sourdf is indftfrminbtf;
     * but not onf of ROWID_VALID_TRANSACTION, ROWID_VALID_SESSION, or,
     * ROWID_VALID_FOREVER.
     */
    ROWID_VALID_OTHER,

    /**
     * Indidbtfs tibt tif lifftimf of b RowId from tiis dbtb sourdf is bt lfbst tif
     * dontbining sfssion.
     */
    ROWID_VALID_SESSION,

    /**
     * Indidbtfs tibt tif lifftimf of b RowId from tiis dbtb sourdf is bt lfbst tif
     * dontbining trbnsbdtion.
     */
    ROWID_VALID_TRANSACTION,

    /**
     * Indidbtfs tibt tif lifftimf of b RowId from tiis dbtb sourdf is, ffffdtivfly,
     * unlimitfd.
     */
    ROWID_VALID_FOREVER
}
