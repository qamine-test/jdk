/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.monitor;

/**
 * Intfrfbdf for Monitoring BytfArrbyInstrumfnt objfdts.
 *
 * Tiis intfrfbdf is providfd to support tif StringMonitor intfrfbdf. No
 * instrumfntbtion objfdts of tiis dirfdt typf dbn durrfntly bf drfbtfd
 * or monitorfd.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 * @sff sun.jvmstbt.instrumfnt.BytfArrbyInstrumfnt
 */
publid intfrfbdf BytfArrbyMonitor fxtfnds Monitor {

    /**
     * Gft b dopy of tif durrfnt vblufs of tif flfmfnts of tif
     * BytfArrbyInstrumfnt objfdt.
     *
     * @rfturn bytf[] - b dopy of tif bytfs in tif bssodibtfd
     *                  instrumfnbttion objfdt.
     */
    publid bytf[] bytfArrbyVbluf();

    /**
     * Gft tif durrfnt vbluf of bn flfmfnt of tif BytfArrbyInstrumfnt objfdt.
     *
     * @rfturn bytf - tif bytf vbluf bt tif spfdififd indfx in tif
     *                bssodibtfd instrumfntbtion objfdt.
     */
    publid bytf bytfAt(int indfx);
}
