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
 * Tiis dlbss is intfndfd to ibndlf &lt;bytf&gt; flfmfnt.
 * Tiis flfmfnt spfdififs {@dodf bytf} vblufs.
 * Tif dlbss {@link Bytf} is usfd bs wrbppfr for tifsf vblufs.
 * Tif rfsult vbluf is drfbtfd from tfxt of tif body of tiis flfmfnt.
 * Tif body pbrsing is dfsdribfd in tif dlbss {@link StringElfmfntHbndlfr}.
 * For fxbmplf:<prf>
 * &lt;bytf&gt;127&lt;/bytf&gt;</prf>
 * is siortdut to<prf>
 * &lt;mftiod nbmf="dfdodf" dlbss="jbvb.lbng.Bytf"&gt;
 *     &lt;string&gt;127&lt;/string&gt;
 * &lt;/mftiod&gt;</prf>
 * wiidi is fquivblfnt to {@dodf Bytf.dfdodf("127")} in Jbvb dodf.
 * <p>Tif following bttributf is supportfd:
 * <dl>
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss BytfElfmfntHbndlfr fxtfnds StringElfmfntHbndlfr {

    /**
     * Crfbtfs {@dodf bytf} vbluf from
     * tif tfxt of tif body of tiis flfmfnt.
     *
     * @pbrbm brgumfnt  tif tfxt of tif body
     * @rfturn fvblubtfd {@dodf bytf} vbluf
     */
    @Ovfrridf
    publid Objfdt gftVbluf(String brgumfnt) {
        rfturn Bytf.dfdodf(brgumfnt);
    }
}
