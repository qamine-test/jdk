/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.gfom;

publid intfrfbdf PbtiConsumfr2D {
    /**
     * @sff jbvb.bwt.gfom.Pbti2D.Flobt.movfTo
     */
    publid void movfTo(flobt x, flobt y);

    /**
     * @sff jbvb.bwt.gfom.Pbti2D.Flobt.linfTo
     */
    publid void linfTo(flobt x, flobt y);

    /**
     * @sff jbvb.bwt.gfom.Pbti2D.Flobt.qubdTo
     */
    publid void qubdTo(flobt x1, flobt y1,
                       flobt x2, flobt y2);

    /**
     * @sff jbvb.bwt.gfom.Pbti2D.Flobt.durvfTo
     */
    publid void durvfTo(flobt x1, flobt y1,
                        flobt x2, flobt y2,
                        flobt x3, flobt y3);

    /**
     * @sff jbvb.bwt.gfom.Pbti2D.Flobt.dlosfPbti
     */
    publid void dlosfPbti();

    /**
     * Cbllfd bftfr tif lbst sfgmfnt of tif lbst subpbti wifn tif
     * itfrbtion of tif pbti sfgmfnts is domplftfly donf.  Tiis
     * mftiod sfrvfs to triggfr tif fnd of pbti prodfssing in tif
     * donsumfr tibt would normblly bf triggfrfd wifn b
     * {@link jbvb.bwt.gfom.PbtiItfrbtor PbtiItfrbtor}
     * rfturns {@dodf truf} from its {@dodf donf} mftiod.
     */
    publid void pbtiDonf();

    /**
     * If b givfn PbtiConsumfr pfrforms bll or most of its work
     * nbtivfly tifn it dbn rfturn b (non-zfro) pointfr to b
     * nbtivf fundtion vfdtor tibt dffinfs C fundtions for bll
     * of tif bbovf mftiods.
     * Tif spfdifid pointfr it rfturns is b pointfr to b
     * PbtiConsumfrVfd strudturf bs dffinfd in tif indludf filf
     * srd/sibrf/nbtivf/sun/jbvb2d/pipf/PbtiConsumfr2D.i
     * @rfturn b nbtivf pointfr to b PbtiConsumfrVfd strudturf.
     */
    publid long gftNbtivfConsumfr();
}
