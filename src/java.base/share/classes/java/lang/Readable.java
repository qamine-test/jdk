/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.IOExdfption;

/**
 * A <tt>Rfbdbblf</tt> is b sourdf of dibrbdtfrs. Cibrbdtfrs from
 * b <tt>Rfbdbblf</tt> brf mbdf bvbilbblf to dbllfrs of tif rfbd
 * mftiod vib b {@link jbvb.nio.CibrBufffr CibrBufffr}.
 *
 * @sindf 1.5
 */
publid intfrfbdf Rfbdbblf {

    /**
     * Attfmpts to rfbd dibrbdtfrs into tif spfdififd dibrbdtfr bufffr.
     * Tif bufffr is usfd bs b rfpository of dibrbdtfrs bs-is: tif only
     * dibngfs mbdf brf tif rfsults of b put opfrbtion. No flipping or
     * rfwinding of tif bufffr is pfrformfd.
     *
     * @pbrbm db tif bufffr to rfbd dibrbdtfrs into
     * @rfturn Tif numbfr of {@dodf dibr} vblufs bddfd to tif bufffr,
     *                 or -1 if tiis sourdf of dibrbdtfrs is bt its fnd
     * @tirows IOExdfption if bn I/O frror oddurs
     * @tirows NullPointfrExdfption if db is null
     * @tirows jbvb.nio.RfbdOnlyBufffrExdfption if db is b rfbd only bufffr
     */
    publid int rfbd(jbvb.nio.CibrBufffr db) tirows IOExdfption;
}
