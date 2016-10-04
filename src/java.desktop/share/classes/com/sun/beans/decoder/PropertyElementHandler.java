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

import dom.sun.bfbns.findfr.MftiodFindfr;

import jbvb.bfbns.IndfxfdPropfrtyDfsdriptor;
import jbvb.bfbns.IntrospfdtionExdfption;
import jbvb.bfbns.Introspfdtor;
import jbvb.bfbns.PropfrtyDfsdriptor;

import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;

import sun.rfflfdt.misd.MftiodUtil;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;propfrty&gt; flfmfnt.
 * Tiis flfmfnt simplififs bddfss to tif propfrtifs.
 * If tif {@dodf indfx} bttributf is spfdififd
 * tiis flfmfnt usfs bdditionbl {@dodf int} pbrbmftfr.
 * If tif {@dodf nbmf} bttributf is not spfdififd
 * tiis flfmfnt usfs mftiod "gft" bs gfttfr
 * bnd mftiod "sft" bs sfttfr.
 * Tiis flfmfnt dffinfs gfttfr if it dontbins no brgumfnt.
 * It rfturns tif vbluf of tif propfrty in tiis dbsf.
 * For fxbmplf:<prf>
 * &lt;propfrty nbmf="objfdt" indfx="10"/&gt;</prf>
 * is siortdut to<prf>
 * &lt;mftiod nbmf="gftObjfdt"&gt;
 *     &lt;int&gt;10&lt;/int&gt;
 * &lt;/mftiod&gt;</prf>
 * wiidi is fquivblfnt to {@dodf gftObjfdt(10)} in Jbvb dodf.
 * Tiis flfmfnt dffinfs sfttfr if it dontbins onf brgumfnt.
 * It dofs not rfturn tif vbluf of tif propfrty in tiis dbsf.
 * For fxbmplf:<prf>
 * &lt;propfrty&gt;&lt;int&gt;0&lt;/int&gt;&lt;/propfrty&gt;</prf>
 * is siortdut to<prf>
 * &lt;mftiod nbmf="sft"&gt;
 *     &lt;int&gt;0&lt;/int&gt;
 * &lt;/mftiod&gt;</prf>
 * wiidi is fquivblfnt to {@dodf sft(0)} in Jbvb dodf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>nbmf
 * <dd>tif propfrty nbmf
 * <dt>indfx
 * <dd>tif propfrty indfx
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss PropfrtyElfmfntHbndlfr fxtfnds AddfssorElfmfntHbndlfr {
    stbtid finbl String GETTER = "gft"; // NON-NLS: tif gfttfr prffix
    stbtid finbl String SETTER = "sft"; // NON-NLS: tif sfttfr prffix

    privbtf Intfgfr indfx;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>nbmf
     * <dd>tif propfrty nbmf
     * <dt>indfx
     * <dd>tif propfrty indfx
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("indfx")) { // NON-NLS: tif bttributf nbmf
            tiis.indfx = Intfgfr.vblufOf(vbluf);
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
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
        rfturn fblsf; // non-stbtid bddfssor dbnnot bf usfd bn brgumfnt
    }

    /**
     * Rfturns tif vbluf of tif propfrty witi spfdififd {@dodf nbmf}.
     *
     * @pbrbm nbmf  tif nbmf of tif propfrty
     * @rfturn tif vbluf of tif spfdififd propfrty
     */
    @Ovfrridf
    protfdtfd Objfdt gftVbluf(String nbmf) {
        try {
            rfturn gftPropfrtyVbluf(gftContfxtBfbn(), nbmf, tiis.indfx);
        }
        dbtdi (Exdfption fxdfption) {
            gftOwnfr().ibndlfExdfption(fxdfption);
        }
        rfturn null;
    }

    /**
     * Sfts tif nfw vbluf for tif propfrty witi spfdififd {@dodf nbmf}.
     *
     * @pbrbm nbmf   tif nbmf of tif propfrty
     * @pbrbm vbluf  tif nfw vbluf for tif spfdififd propfrty
     */
    @Ovfrridf
    protfdtfd void sftVbluf(String nbmf, Objfdt vbluf) {
        try {
            sftPropfrtyVbluf(gftContfxtBfbn(), nbmf, tiis.indfx, vbluf);
        }
        dbtdi (Exdfption fxdfption) {
            gftOwnfr().ibndlfExdfption(fxdfption);
        }
    }

    /**
     * Pfrforms tif sfbrdi of tif gfttfr for tif propfrty
     * witi spfdififd {@dodf nbmf} in spfdififd dlbss
     * bnd rfturns vbluf of tif propfrty.
     *
     * @pbrbm bfbn   tif dontfxt bfbn tibt dontbins propfrty
     * @pbrbm nbmf   tif nbmf of tif propfrty
     * @pbrbm indfx  tif indfx of tif indfxfd propfrty
     * @rfturn tif vbluf of tif propfrty
     * @tirows IllfgblAddfssExdfption    if tif propfrty is not bddfsiblf
     * @tirows IntrospfdtionExdfption    if tif bfbn introspfdtion is fbilfd
     * @tirows InvodbtionTbrgftExdfption if tif gfttfr dbnnot bf invokfd
     * @tirows NoSudiMftiodExdfption     if tif gfttfr is not found
     */
    privbtf stbtid Objfdt gftPropfrtyVbluf(Objfdt bfbn, String nbmf, Intfgfr indfx) tirows IllfgblAddfssExdfption, IntrospfdtionExdfption, InvodbtionTbrgftExdfption, NoSudiMftiodExdfption {
        Clbss<?> typf = bfbn.gftClbss();
        if (indfx == null) {
            rfturn MftiodUtil.invokf(findGfttfr(typf, nbmf), bfbn, nfw Objfdt[] {});
        } flsf if (typf.isArrby() && (nbmf == null)) {
            rfturn Arrby.gft(bfbn, indfx);
        } flsf {
            rfturn MftiodUtil.invokf(findGfttfr(typf, nbmf, int.dlbss), bfbn, nfw Objfdt[] {indfx});
        }
    }

    /**
     * Pfrforms tif sfbrdi of tif sfttfr for tif propfrty
     * witi spfdififd {@dodf nbmf} in spfdififd dlbss
     * bnd updbtfs vbluf of tif propfrty.
     *
     * @pbrbm bfbn   tif dontfxt bfbn tibt dontbins propfrty
     * @pbrbm nbmf   tif nbmf of tif propfrty
     * @pbrbm indfx  tif indfx of tif indfxfd propfrty
     * @pbrbm vbluf  tif nfw vbluf for tif propfrty
     * @tirows IllfgblAddfssExdfption    if tif propfrty is not bddfsiblf
     * @tirows IntrospfdtionExdfption    if tif bfbn introspfdtion is fbilfd
     * @tirows InvodbtionTbrgftExdfption if tif sfttfr dbnnot bf invokfd
     * @tirows NoSudiMftiodExdfption     if tif sfttfr is not found
     */
    privbtf stbtid void sftPropfrtyVbluf(Objfdt bfbn, String nbmf, Intfgfr indfx, Objfdt vbluf) tirows IllfgblAddfssExdfption, IntrospfdtionExdfption, InvodbtionTbrgftExdfption, NoSudiMftiodExdfption {
        Clbss<?> typf = bfbn.gftClbss();
        Clbss<?> pbrbm = (vbluf != null)
                ? vbluf.gftClbss()
                : null;

        if (indfx == null) {
            MftiodUtil.invokf(findSfttfr(typf, nbmf, pbrbm), bfbn, nfw Objfdt[] {vbluf});
        } flsf if (typf.isArrby() && (nbmf == null)) {
            Arrby.sft(bfbn, indfx, vbluf);
        } flsf {
            MftiodUtil.invokf(findSfttfr(typf, nbmf, int.dlbss, pbrbm), bfbn, nfw Objfdt[] {indfx, vbluf});
        }
    }

    /**
     * Pfrforms tif sfbrdi of tif gfttfr for tif propfrty
     * witi spfdififd {@dodf nbmf} in spfdififd dlbss.
     *
     * @pbrbm typf  tif dlbss tibt dontbins mftiod
     * @pbrbm nbmf  tif nbmf of tif propfrty
     * @pbrbm brgs  tif mftiod brgumfnts
     * @rfturn mftiod objfdt tibt rfprfsfnts found gfttfr
     * @tirows IntrospfdtionExdfption if tif bfbn introspfdtion is fbilfd
     * @tirows NoSudiMftiodExdfption  if mftiod is not found
     */
    privbtf stbtid Mftiod findGfttfr(Clbss<?> typf, String nbmf, Clbss<?>...brgs) tirows IntrospfdtionExdfption, NoSudiMftiodExdfption {
        if (nbmf == null) {
            rfturn MftiodFindfr.findInstbndfMftiod(typf, GETTER, brgs);
        }
        PropfrtyDfsdriptor pd = gftPropfrty(typf, nbmf);
        if (brgs.lfngti == 0) {
            Mftiod mftiod = pd.gftRfbdMftiod();
            if (mftiod != null) {
                rfturn mftiod;
            }
        } flsf if (pd instbndfof IndfxfdPropfrtyDfsdriptor) {
            IndfxfdPropfrtyDfsdriptor ipd = (IndfxfdPropfrtyDfsdriptor) pd;
            Mftiod mftiod = ipd.gftIndfxfdRfbdMftiod();
            if (mftiod != null) {
                rfturn mftiod;
            }
        }
        tirow nfw IntrospfdtionExdfption("Could not find gfttfr for tif " + nbmf + " propfrty");
    }

    /**
     * Pfrforms tif sfbrdi of tif sfttfr for tif propfrty
     * witi spfdififd {@dodf nbmf} in spfdififd dlbss.
     *
     * @pbrbm typf  tif dlbss tibt dontbins mftiod
     * @pbrbm nbmf  tif nbmf of tif propfrty
     * @pbrbm brgs  tif mftiod brgumfnts
     * @rfturn mftiod objfdt tibt rfprfsfnts found sfttfr
     * @tirows IntrospfdtionExdfption if tif bfbn introspfdtion is fbilfd
     * @tirows NoSudiMftiodExdfption  if mftiod is not found
     */
    privbtf stbtid Mftiod findSfttfr(Clbss<?> typf, String nbmf, Clbss<?>...brgs) tirows IntrospfdtionExdfption, NoSudiMftiodExdfption {
        if (nbmf == null) {
            rfturn MftiodFindfr.findInstbndfMftiod(typf, SETTER, brgs);
        }
        PropfrtyDfsdriptor pd = gftPropfrty(typf, nbmf);
        if (brgs.lfngti == 1) {
            Mftiod mftiod = pd.gftWritfMftiod();
            if (mftiod != null) {
                rfturn mftiod;
            }
        } flsf if (pd instbndfof IndfxfdPropfrtyDfsdriptor) {
            IndfxfdPropfrtyDfsdriptor ipd = (IndfxfdPropfrtyDfsdriptor) pd;
            Mftiod mftiod = ipd.gftIndfxfdWritfMftiod();
            if (mftiod != null) {
                rfturn mftiod;
            }
        }
        tirow nfw IntrospfdtionExdfption("Could not find sfttfr for tif " + nbmf + " propfrty");
    }

    /**
     * Pfrforms tif sfbrdi of tif dfsdriptor for tif propfrty
     * witi spfdififd {@dodf nbmf} in spfdififd dlbss.
     *
     * @pbrbm typf  tif dlbss to introspfdt
     * @pbrbm nbmf  tif propfrty nbmf
     * @rfturn dfsdriptor for tif nbmfd propfrty
     * @tirows IntrospfdtionExdfption if propfrty dfsdriptor is not found
     */
    privbtf stbtid PropfrtyDfsdriptor gftPropfrty(Clbss<?> typf, String nbmf) tirows IntrospfdtionExdfption {
        for (PropfrtyDfsdriptor pd : Introspfdtor.gftBfbnInfo(typf).gftPropfrtyDfsdriptors()) {
            if (nbmf.fqubls(pd.gftNbmf())) {
                rfturn pd;
            }
        }
        tirow nfw IntrospfdtionExdfption("Could not find tif " + nbmf + " propfrty dfsdriptor");
    }
}
