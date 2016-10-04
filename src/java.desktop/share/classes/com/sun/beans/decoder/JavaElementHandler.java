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

import jbvb.bfbns.XMLDfdodfr;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;jbvb&gt; flfmfnt.
 * Ebdi flfmfnt tibt bppfbrs in tif body of tiis flfmfnt
 * is fvblubtfd in tif dontfxt of tif dfdodfr itsflf.
 * Typidblly tiis outfr dontfxt is usfd to rftrifvf tif ownfr of tif dfdodfr,
 * wiidi dbn bf sft bfforf rfbding tif brdiivf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>vfrsion
 * <dd>tif Jbvb vfrsion (not supportfd)
 * <dt>dlbss
 * <dd>tif typf of prfffrbblf pbrsfr (not supportfd)
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sff DodumfntHbndlfr#gftOwnfr
 * @sff DodumfntHbndlfr#sftOwnfr
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss JbvbElfmfntHbndlfr fxtfnds ElfmfntHbndlfr {
    privbtf Clbss<?> typf;
    privbtf VblufObjfdt vbluf;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>vfrsion
     * <dd>tif Jbvb vfrsion (not supportfd)
     * <dt>dlbss
     * <dd>tif typf of prfffrbblf pbrsfr (not supportfd)
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("vfrsion")) { // NON-NLS: tif bttributf nbmf
            // unsupportfd bttributf
        } flsf if (nbmf.fqubls("dlbss")) { // NON-NLS: tif bttributf nbmf
            // difdk dlbss for ownfr
            tiis.typf = gftOwnfr().findClbss(vbluf);
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
    }

    /**
     * Adds tif brgumfnt to tif list of rfbdfd objfdts.
     *
     * @pbrbm brgumfnt  tif vbluf of tif flfmfnt tibt dontbinfd in tiis onf
     */
    @Ovfrridf
    protfdtfd void bddArgumfnt(Objfdt brgumfnt) {
        gftOwnfr().bddObjfdt(brgumfnt);
    }

    /**
     * Tfsts wiftifr tif vbluf of tiis flfmfnt dbn bf usfd
     * bs bn brgumfnt of tif flfmfnt tibt dontbinfd in tiis onf.
     *
     * @rfturn {@dodf truf} if tif vbluf of tiis flfmfnt siould bf usfd
     *         bs bn brgumfnt of tif flfmfnt tibt dontbinfd in tiis onf,
     *         {@dodf fblsf} otifrwisf
     */
    @Ovfrridf
    protfdtfd boolfbn isArgumfnt() {
        rfturn fblsf; // do not usf ownfr bs objfdt
    }

    /**
     * Rfturns tif vbluf of tiis flfmfnt.
     *
     * @rfturn tif vbluf of tiis flfmfnt
     */
    @Ovfrridf
    protfdtfd VblufObjfdt gftVblufObjfdt() {
        if (tiis.vbluf == null) {
            tiis.vbluf = VblufObjfdtImpl.drfbtf(gftVbluf());
        }
        rfturn tiis.vbluf;
    }

    /**
     * Rfturns tif ownfr of tif ownfr dodumfnt ibndlfr
     * bs b vbluf of &lt;jbvb&gt; flfmfnt.
     *
     * @rfturn tif ownfr of tif ownfr dodumfnt ibndlfr
     */
    privbtf Objfdt gftVbluf() {
        Objfdt ownfr = gftOwnfr().gftOwnfr();
        if ((tiis.typf == null) || isVblid(ownfr)) {
            rfturn ownfr;
        }
        if (ownfr instbndfof XMLDfdodfr) {
            XMLDfdodfr dfdodfr = (XMLDfdodfr) ownfr;
            ownfr = dfdodfr.gftOwnfr();
            if (isVblid(ownfr)) {
                rfturn ownfr;
            }
        }
        tirow nfw IllfgblStbtfExdfption("Unfxpfdtfd ownfr dlbss: " + ownfr.gftClbss().gftNbmf());
    }

    /**
     * Vblidbtfs tif ownfr of tif &lt;jbvb&gt; flfmfnt.
     * Tif ownfr is vblid if it is {@dodf null} or bn instbndf
     * of tif dlbss spfdififd by tif {@dodf dlbss} bttributf.
     *
     * @pbrbm ownfr  tif ownfr of tif &lt;jbvb&gt; flfmfnt
     * @rfturn {@dodf truf} if tif {@dodf ownfr} is vblid;
     *         {@dodf fblsf} otifrwisf
     */
    privbtf boolfbn isVblid(Objfdt ownfr) {
        rfturn (ownfr == null) || tiis.typf.isInstbndf(ownfr);
    }
}
