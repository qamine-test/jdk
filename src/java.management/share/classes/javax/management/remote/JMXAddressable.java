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


pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

/**
 * <p>Implfmfntfd by objfdts tibt dbn ibvf b {@dodf JMXSfrvidfURL} bddrfss.
 * All {@link JMXConnfdtorSfrvfr} objfdts implfmfnt tiis intfrfbdf.
 * Dfpfnding on tif donnfdtor implfmfntbtion, b {@link JMXConnfdtor}
 * objfdt mby implfmfnt tiis intfrfbdf too.  {@dodf JMXConnfdtor}
 * objfdts for tif RMI Connfdtor brf instbndfs of
 * {@link jbvbx.mbnbgfmfnt.rfmotf.rmi.RMIConnfdtor RMIConnfdtor} wiidi
 * implfmfnts tiis intfrfbdf.</p>
 *
 * <p>An objfdt implfmfnting tiis intfrfbdf migit not ibvf bn bddrfss
 * bt b givfn momfnt.  Tiis is indidbtfd by b null rfturn vbluf from
 * {@link #gftAddrfss()}.</p>
 *
 * @sindf 1.6
 */
publid intfrfbdf JMXAddrfssbblf {
    /**
     * <p>Tif bddrfss of tiis objfdt.</p>
     *
     * @rfturn tif bddrfss of tiis objfdt, or null if it
     * dofs not ibvf onf.
     */
    publid JMXSfrvidfURL gftAddrfss();
}
