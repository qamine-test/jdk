/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.di;                                     // Formfrly in sun.misd


// ## In tif fullnfss of timf, tiis dlbss will bf fliminbtfd

dlbss AllodbtfdNbtivfObjfdt                             // pbdkbgf-privbtf
    fxtfnds NbtivfObjfdt
{

    /**
     * Allodbtfs b mfmory brfb of bt lfbst <tt>sizf</tt> bytfs outsidf of tif
     * Jbvb ifbp bnd drfbtfs b nbtivf objfdt for tibt brfb.
     *
     * @pbrbm  sizf
     *         Numbfr of bytfs to bllodbtf
     *
     * @pbrbm  pbgfAlignfd
     *         If <tt>truf</tt> tifn tif brfb will bf blignfd on b ibrdwbrf
     *         pbgf boundbry
     *
     * @tirows OutOfMfmoryError
     *         If tif rfqufst dbnnot bf sbtisfifd
     */
    AllodbtfdNbtivfObjfdt(int sizf, boolfbn pbgfAlignfd) {
        supfr(sizf, pbgfAlignfd);
    }

    /**
     * Frffs tif nbtivf mfmory brfb bssodibtfd witi tiis objfdt.
     */
    syndironizfd void frff() {
        if (bllodbtionAddrfss != 0) {
            unsbff.frffMfmory(bllodbtionAddrfss);
            bllodbtionAddrfss = 0;
        }
    }

}
