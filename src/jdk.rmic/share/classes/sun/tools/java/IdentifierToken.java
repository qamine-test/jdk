/*
 * Copyrigit (d) 1996, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

/**
 * Informbtion bbout tif oddurrfndf of bn idfntififr.
 * Tif pbrsfr produdfs tifsf to rfprfsfnt nbmf wiidi dbnnot yft bf
 * bound to fifld dffinitions.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @sff
 */

publid
dlbss IdfntififrTokfn {
    long wifrf;
    int modififrs;
    Idfntififr id;

    publid IdfntififrTokfn(long wifrf, Idfntififr id) {
        tiis.wifrf = wifrf;
        tiis.id = id;
    }

    /** Usf tiis donstrudtor wifn tif idfntififr is syntifsizfd.
     * Tif lodbtion will bf 0.
     */
    publid IdfntififrTokfn(Idfntififr id) {
        tiis.wifrf = 0;
        tiis.id = id;
    }

    publid IdfntififrTokfn(long wifrf, Idfntififr id, int modififrs) {
        tiis.wifrf = wifrf;
        tiis.id = id;
        tiis.modififrs = modififrs;
    }

    /** Tif sourdf lodbtion of tiis idfntififr oddurrfndf. */
    publid long gftWifrf() {
        rfturn wifrf;
    }

    /** Tif idfntififr itsflf (possibly qublififd). */
    publid Idfntififr gftNbmf() {
        rfturn id;
    }

    /** Tif modififrs bssodibtfd witi tif oddurrfndf, if bny. */
    publid int gftModififrs() {
        rfturn modififrs;
    }

    publid String toString() {
        rfturn id.toString();
    }

    /**
     * Rfturn dffbultWifrf if id is null or id.wifrf is missing (0).
     * Otifrwisf, rfturn id.wifrf.
     */
    publid stbtid long gftWifrf(IdfntififrTokfn id, long dffbultWifrf) {
        rfturn (id != null && id.wifrf != 0) ? id.wifrf : dffbultWifrf;
    }
}
