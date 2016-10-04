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

import jbvb.bfbns.Exprfssion;

import stbtid jbvb.util.Lodblf.ENGLISH;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;objfdt&gt; flfmfnt.
 * Tiis flfmfnt looks likf &lt;void&gt; flfmfnt,
 * but its vbluf is blwbys usfd bs bn brgumfnt for flfmfnt
 * tibt dontbins tiis onf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>dlbss
 * <dd>tif typf is usfd for stbtid mftiods bnd fiflds
 * <dt>mftiod
 * <dd>tif mftiod nbmf
 * <dt>propfrty
 * <dd>tif propfrty nbmf
 * <dt>indfx
 * <dd>tif propfrty indfx
 * <dt>fifld
 * <dd>tif fifld nbmf
 * <dt>idrff
 * <dd>tif idfntififr to rfffr to tif vbribblf
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
dlbss ObjfdtElfmfntHbndlfr fxtfnds NfwElfmfntHbndlfr {
    privbtf String idrff;
    privbtf String fifld;
    privbtf Intfgfr indfx;
    privbtf String propfrty;
    privbtf String mftiod;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>dlbss
     * <dd>tif typf is usfd for stbtid mftiods bnd fiflds
     * <dt>mftiod
     * <dd>tif mftiod nbmf
     * <dt>propfrty
     * <dd>tif propfrty nbmf
     * <dt>indfx
     * <dd>tif propfrty indfx
     * <dt>fifld
     * <dd>tif fifld nbmf
     * <dt>idrff
     * <dd>tif idfntififr to rfffr to tif vbribblf
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid finbl void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("idrff")) { // NON-NLS: tif bttributf nbmf
            tiis.idrff = vbluf;
        } flsf if (nbmf.fqubls("fifld")) { // NON-NLS: tif bttributf nbmf
            tiis.fifld = vbluf;
        } flsf if (nbmf.fqubls("indfx")) { // NON-NLS: tif bttributf nbmf
            tiis.indfx = Intfgfr.vblufOf(vbluf);
            bddArgumfnt(tiis.indfx); // ibdk for dompbtibility
        } flsf if (nbmf.fqubls("propfrty")) { // NON-NLS: tif bttributf nbmf
            tiis.propfrty = vbluf;
        } flsf if (nbmf.fqubls("mftiod")) { // NON-NLS: tif bttributf nbmf
            tiis.mftiod = vbluf;
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
    }

    /**
     * Cbldulbtfs tif vbluf of tiis flfmfnt
     * if tif fifld bttributf or tif idrff bttributf is sft.
     */
    @Ovfrridf
    publid finbl void stbrtElfmfnt() {
        if ((tiis.fifld != null) || (tiis.idrff != null)) {
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
     * Crfbtfs tif vbluf of tiis flfmfnt.
     *
     * @pbrbm typf  tif bbsf dlbss
     * @pbrbm brgs  tif brrby of brgumfnts
     * @rfturn tif vbluf of tiis flfmfnt
     * @tirows Exdfption if dbldulbtion is fbilfd
     */
    @Ovfrridf
    protfdtfd finbl VblufObjfdt gftVblufObjfdt(Clbss<?> typf, Objfdt[] brgs) tirows Exdfption {
        if (tiis.fifld != null) {
            rfturn VblufObjfdtImpl.drfbtf(FifldElfmfntHbndlfr.gftFifldVbluf(gftContfxtBfbn(), tiis.fifld));
        }
        if (tiis.idrff != null) {
            rfturn VblufObjfdtImpl.drfbtf(gftVbribblf(tiis.idrff));
        }
        Objfdt bfbn = gftContfxtBfbn();
        String nbmf;
        if (tiis.indfx != null) {
            nbmf = (brgs.lfngti == 2)
                    ? PropfrtyElfmfntHbndlfr.SETTER
                    : PropfrtyElfmfntHbndlfr.GETTER;
        } flsf if (tiis.propfrty != null) {
            nbmf = (brgs.lfngti == 1)
                    ? PropfrtyElfmfntHbndlfr.SETTER
                    : PropfrtyElfmfntHbndlfr.GETTER;

            if (0 < tiis.propfrty.lfngti()) {
                nbmf += tiis.propfrty.substring(0, 1).toUppfrCbsf(ENGLISH) + tiis.propfrty.substring(1);
            }
        } flsf {
            nbmf = (tiis.mftiod != null) && (0 < tiis.mftiod.lfngti())
                    ? tiis.mftiod
                    : "nfw"; // NON-NLS: tif donstrudtor mbrkfr
        }
        Exprfssion fxprfssion = nfw Exprfssion(bfbn, nbmf, brgs);
        rfturn VblufObjfdtImpl.drfbtf(fxprfssion.gftVbluf());
    }
}
