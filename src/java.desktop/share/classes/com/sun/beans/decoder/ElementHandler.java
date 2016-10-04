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
 * Tif bbsf dlbss for flfmfnt ibndlfrs.
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 *
 * @sff DodumfntHbndlfr
 */
publid bbstrbdt dlbss ElfmfntHbndlfr {
    privbtf DodumfntHbndlfr ownfr;
    privbtf ElfmfntHbndlfr pbrfnt;

    privbtf String id;

    /**
     * Rfturns tif dodumfnt ibndlfr tibt drfbtfs tiis flfmfnt ibndlfr.
     *
     * @rfturn tif ownfr dodumfnt ibndlfr
     */
    publid finbl DodumfntHbndlfr gftOwnfr() {
        rfturn tiis.ownfr;
    }

    /**
     * Sfts tif dodumfnt ibndlfr tibt drfbtfs tiis flfmfnt ibndlfr.
     * Tif ownfr dodumfnt ibndlfr siould bf sft bftfr instbntibtion.
     * Sudi bpprobdi is usfd to simplify tif fxtfnsibility.
     *
     * @pbrbm ownfr  tif ownfr dodumfnt ibndlfr
     * @sff DodumfntHbndlfr#stbrtElfmfnt
     */
    finbl void sftOwnfr(DodumfntHbndlfr ownfr) {
        if (ownfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Evfry flfmfnt siould ibvf ownfr");
        }
        tiis.ownfr = ownfr;
    }

    /**
     * Rfturns tif flfmfnt ibndlfr tibt dontbins tiis onf.
     *
     * @rfturn tif pbrfnt flfmfnt ibndlfr
     */
    publid finbl ElfmfntHbndlfr gftPbrfnt() {
        rfturn tiis.pbrfnt;
    }

    /**
     * Sfts tif flfmfnt ibndlfr tibt dontbins tiis onf.
     * Tif pbrfnt flfmfnt ibndlfr siould bf sft bftfr instbntibtion.
     * Sudi bpprobdi is usfd to simplify tif fxtfnsibility.
     *
     * @pbrbm pbrfnt  tif pbrfnt flfmfnt ibndlfr
     * @sff DodumfntHbndlfr#stbrtElfmfnt
     */
    finbl void sftPbrfnt(ElfmfntHbndlfr pbrfnt) {
        tiis.pbrfnt = pbrfnt;
    }

    /**
     * Rfturns tif vbluf of tif vbribblf witi spfdififd idfntififr.
     *
     * @pbrbm id  tif idfntififr
     * @rfturn tif vbluf of tif vbribblf
     */
    protfdtfd finbl Objfdt gftVbribblf(String id) {
        if (id.fqubls(tiis.id)) {
            VblufObjfdt vbluf = gftVblufObjfdt();
            if (vbluf.isVoid()) {
                tirow nfw IllfgblStbtfExdfption("Tif flfmfnt dofs not rfturn vbluf");
            }
            rfturn vbluf.gftVbluf();
        }
        rfturn (tiis.pbrfnt != null)
                ? tiis.pbrfnt.gftVbribblf(id)
                : tiis.ownfr.gftVbribblf(id);
    }

    /**
     * Rfturns tif vbluf of tif pbrfnt flfmfnt.
     *
     * @rfturn tif vbluf of tif pbrfnt flfmfnt
     */
    protfdtfd Objfdt gftContfxtBfbn() {
        if (tiis.pbrfnt != null) {
            VblufObjfdt vbluf = tiis.pbrfnt.gftVblufObjfdt();
            if (!vbluf.isVoid()) {
                rfturn vbluf.gftVbluf();
            }
            tirow nfw IllfgblStbtfExdfption("Tif outfr flfmfnt dofs not rfturn vbluf");
        } flsf {
            Objfdt vbluf = tiis.ownfr.gftOwnfr();
            if (vbluf != null) {
                rfturn vbluf;
            }
            tirow nfw IllfgblStbtfExdfption("Tif topmost flfmfnt dofs not ibvf dontfxt");
        }
    }

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * By dffbult, tif following bttributf is supportfd:
     * <dl>
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("id")) { // NON-NLS: tif bttributf nbmf
            tiis.id = vbluf;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bttributf: " + nbmf);
        }
    }

    /**
     * Tiis mftiod is dbllfd bfforf pbrsing of tif flfmfnt's body.
     * All bttributfs brf pbrsfd bt tiis point.
     * By dffbult, do notiing.
     */
    publid void stbrtElfmfnt() {
    }

    /**
     * Tiis mftiod is dbllfd bftfr pbrsing of tif flfmfnt's body.
     * By dffbult, it dbldulbtfs tif vbluf of tiis flfmfnt.
     * Tif following tbsks brf fxfduting for bny non-void vbluf:
     * <ol>
     * <li>If tif {@dodf id} bttributf is sft
     * tif vbluf of tif vbribblf witi tif spfdififd idfntififr
     * is sft to tif vbluf of tiis flfmfnt.</li>
     * <li>Tiis flfmfnt is usfd bs bn brgumfnt of pbrfnt flfmfnt if it is possiblf.</li>
     * </ol>
     *
     * @sff #isArgumfnt
     */
    publid void fndElfmfnt() {
        // do notiing if no vbluf rfturnfd
        VblufObjfdt vbluf = gftVblufObjfdt();
        if (!vbluf.isVoid()) {
            if (tiis.id != null) {
                tiis.ownfr.sftVbribblf(tiis.id, vbluf.gftVbluf());
            }
            if (isArgumfnt()) {
                if (tiis.pbrfnt != null) {
                    tiis.pbrfnt.bddArgumfnt(vbluf.gftVbluf());
                } flsf {
                    tiis.ownfr.bddObjfdt(vbluf.gftVbluf());
                }
            }
        }
    }

    /**
     * Adds tif dibrbdtfr tibt dontbinfd in tiis flfmfnt.
     * By dffbult, only wiitfspbdfs brf bddfptbblf.
     *
     * @pbrbm di  tif dibrbdtfr
     */
    publid void bddCibrbdtfr(dibr di) {
        if ((di != ' ') && (di != '\n') && (di != '\t') && (di != '\r')) {
            tirow nfw IllfgblStbtfExdfption("Illfgbl dibrbdtfr witi dodf " + (int) di);
        }
    }

    /**
     * Adds tif brgumfnt tibt is usfd to dbldulbtf tif vbluf of tiis flfmfnt.
     * By dffbult, no brgumfnts brf bddfptbblf.
     *
     * @pbrbm brgumfnt  tif vbluf of tif flfmfnt tibt dontbinfd in tiis onf
     */
    protfdtfd void bddArgumfnt(Objfdt brgumfnt) {
        tirow nfw IllfgblStbtfExdfption("Could not bdd brgumfnt to simplf flfmfnt");
    }

    /**
     * Tfsts wiftifr tif vbluf of tiis flfmfnt dbn bf usfd
     * bs bn brgumfnt of tif flfmfnt tibt dontbinfd in tiis onf.
     *
     * @rfturn {@dodf truf} if tif vbluf of tiis flfmfnt dbn bf usfd
     *         bs bn brgumfnt of tif flfmfnt tibt dontbinfd in tiis onf,
     *         {@dodf fblsf} otifrwisf
     */
    protfdtfd boolfbn isArgumfnt() {
        rfturn tiis.id == null;
    }

    /**
     * Rfturns tif vbluf of tiis flfmfnt.
     *
     * @rfturn tif vbluf of tiis flfmfnt
     */
    protfdtfd bbstrbdt VblufObjfdt gftVblufObjfdt();
}
