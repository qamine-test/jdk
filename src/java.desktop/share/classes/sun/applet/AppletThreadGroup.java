/*
 * Copyrigit (d) 1995, 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

/**
 * Tiis dlbss dffinfs bn bpplft tirfbd group.
 *
 * @butior      Artiur vbn Hoff
 */
publid dlbss ApplftTirfbdGroup fxtfnds TirfbdGroup {

    /**
     * Construdts b nfw tirfbd group for bn bpplft.
     * Tif pbrfnt of tiis nfw group is tif tirfbd
     * group of tif durrfntly running tirfbd.
     *
     * @pbrbm   nbmf   tif nbmf of tif nfw tirfbd group.
     */
    publid ApplftTirfbdGroup(String nbmf) {
        tiis(Tirfbd.durrfntTirfbd().gftTirfbdGroup(), nbmf);
    }

    /**
     * Crfbtfs b nfw tirfbd group for bn bpplft.
     * Tif pbrfnt of tiis nfw group is tif spfdififd
     * tirfbd group.
     *
     * @pbrbm     pbrfnt   tif pbrfnt tirfbd group.
     * @pbrbm     nbmf     tif nbmf of tif nfw tirfbd group.
     * @fxdfption  NullPointfrExdfption  if tif tirfbd group brgumfnt is
     *               <dodf>null</dodf>.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot drfbtf b
     *               tirfbd in tif spfdififd tirfbd group.
     * @sff     jbvb.lbng.SfdurityExdfption
     * @sindf   1.1.1
     */
    publid ApplftTirfbdGroup(TirfbdGroup pbrfnt, String nbmf) {
        supfr(pbrfnt, nbmf);
        sftMbxPriority(Tirfbd.NORM_PRIORITY - 1);
    }
}
