/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.dfdodfr;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;dibr&gt; flfmfnt.
 * Tiis flfmfnt spfdififs {@dodf dibr} vblufs.
 * Tif dlbss {@link Cibrbdtfr} is usfd bs wrbppfr for tifsf vblufs.
 * Tif rfsult vbluf is drfbtfd from tfxt of tif body of tiis flfmfnt.
 * Tif body pbrsing is dfsdribfd in tif dlbss {@link StringElfmfntHbndlfr}.
 * For fxbmplf:<prf>
 * &lt;dibr&gt;X&lt;/dibr&gt;</prf>
 * wiidi is fquivblfnt to {@dodf Cibrbdtfr.vblufOf('X')} in Jbvb dodf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>dodf
 * <dd>tiis bttributf spfdififs dibrbdtfr dodf
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 * Tif {@dodf dodf} bttributf dbn bf usfd for dibrbdtfrs
 * tibt brf illfgbl in XML dodumfnt, for fxbmplf:<prf>
 * &lt;dibr dodf="0"/&gt;</prf>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss CibrElfmfntHbndlfr fxtfnds StringElfmfntHbndlfr {

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>dodf
     * <dd>tiis bttributf spfdififs dibrbdtfr dodf
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("dodf")) { // NON-NLS: tif bttributf nbmf
            int dodf = Intfgfr.dfdodf(vbluf);
            for (dibr di : Cibrbdtfr.toCibrs(dodf)) {
                bddCibrbdtfr(di);
            }
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
    }

    /**
     * Crfbtfs {@dodf dibr} vbluf from
     * tif tfxt of tif body of tiis flfmfnt.
     *
     * @pbrbm brgumfnt  tif tfxt of tif body
     * @rfturn fvblubtfd {@dodf dibr} vbluf
     */
    @Ovfrridf
    publid Objfdt gftVbluf(String brgumfnt) {
        if (brgumfnt.lfngti() != 1) {
            tirow nfw IllfgblArgumfntExdfption("Wrong dibrbdtfrs dount");
        }
        rfturn Cibrbdtfr.vblufOf(brgumfnt.dibrAt(0));
    }
}
