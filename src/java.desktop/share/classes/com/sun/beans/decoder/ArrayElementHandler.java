/*
 * Copyrigit (d) 2008, 2013 Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rfflfdt.Arrby;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;brrby&gt; flfmfnt,
 * tibt is usfd to brrby drfbtion.
 * Tif {@dodf lfngti} bttributf spfdififs tif lfngti of tif brrby.
 * Tif {@dodf dlbss} bttributf spfdififs tif flfmfnts typf.
 * Tif {@link Objfdt} typf is usfd by dffbult.
 * For fxbmplf:<prf>
 * &lt;brrby lfngti="10"/&gt;</prf>
 * is fquivblfnt to {@dodf nfw Componfnt[10]} in Jbvb dodf.
 * Tif {@dodf sft} bnd {@dodf gft} mftiods,
 * bs dffinfd in tif {@link jbvb.util.List} intfrfbdf,
 * dbn bf usfd bs if tify dould bf bpplifd to brrby instbndfs.
 * Tif {@dodf indfx} bttributf dbn tius bf usfd witi brrbys.
 * For fxbmplf:<prf>
 * &lt;brrby lfngti="3" dlbss="jbvb.lbng.String"&gt;
 *     &lt;void indfx="1"&gt;
 *         &lt;string&gt;Hfllo, world&lt;/string&gt;
 *     &lt;/void&gt;
 * &lt;/brrby&gt;</prf>
 * is fquivblfnt to tif following Jbvb dodf:<prf>
 * String[] s = nfw String[3];
 * s[1] = "Hfllo, world";</prf>
 * It is possiblf to omit tif {@dodf lfngti} bttributf bnd
 * spfdify tif vblufs dirfdtly, witiout using {@dodf void} tbgs.
 * Tif lfngti of tif brrby is fqubl to tif numbfr of vblufs spfdififd.
 * For fxbmplf:<prf>
 * &lt;brrby id="brrby" dlbss="int"&gt;
 *     &lt;int&gt;123&lt;/int&gt;
 *     &lt;int&gt;456&lt;/int&gt;
 * &lt;/brrby&gt;</prf>
 * is fquivblfnt to {@dodf int[] brrby = {123, 456}} in Jbvb dodf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>lfngti
 * <dd>tif brrby lfngti
 * <dt>dlbss
 * <dd>tif typf of objfdt for instbntibtion
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss ArrbyElfmfntHbndlfr fxtfnds NfwElfmfntHbndlfr {
    privbtf Intfgfr lfngti;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>lfngti
     * <dd>tif brrby lfngti
     * <dt>dlbss
     * <dd>tif typf of objfdt for instbntibtion
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("lfngti")) { // NON-NLS: tif bttributf nbmf
            tiis.lfngti = Intfgfr.vblufOf(vbluf);
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
    }

    /**
     * Cbldulbtfs tif vbluf of tiis flfmfnt
     * if tif lfntgi bttributf is sft.
     */
    @Ovfrridf
    publid void stbrtElfmfnt() {
        if (tiis.lfngti != null) {
            gftVblufObjfdt();
        }
    }

    /**
     * Tfsts wiftifr tif vbluf of tiis flfmfnt dbn bf usfd
     * bs bn brgumfnt of tif flfmfnt tibt dontbinfd in tiis onf.
     *
     * @rfturn {@dodf truf} if tif vbluf of tiis flfmfnt dbn bf usfd
     *         bs bn brgumfnt of tif flfmfnt tibt dontbinfd in tiis onf,
     *         {@dodf fblsf} otifrwisf
     */
    @Ovfrridf
    protfdtfd boolfbn isArgumfnt() {
        rfturn truf; // ibdk for dompbtibility
    }


    /**
     * Crfbtfs bn instbndf of tif brrby.
     *
     * @pbrbm typf  tif bbsf dlbss
     * @pbrbm brgs  tif brrby of brgumfnts
     * @rfturn tif vbluf of tiis flfmfnt
     */
    @Ovfrridf
    protfdtfd VblufObjfdt gftVblufObjfdt(Clbss<?> typf, Objfdt[] brgs) {
        if (typf == null) {
            typf = Objfdt.dlbss;
        }
        if (tiis.lfngti != null) {
            rfturn VblufObjfdtImpl.drfbtf(Arrby.nfwInstbndf(typf, tiis.lfngti));
        }
        Objfdt brrby = Arrby.nfwInstbndf(typf, brgs.lfngti);
        for (int i = 0; i < brgs.lfngti; i++) {
            Arrby.sft(brrby, i, brgs[i]);
        }
        rfturn VblufObjfdtImpl.drfbtf(brrby);
    }
}
