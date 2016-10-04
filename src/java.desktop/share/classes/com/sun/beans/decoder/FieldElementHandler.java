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

import dom.sun.bfbns.findfr.FifldFindfr;

import jbvb.lbng.rfflfdt.Fifld;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;fifld&gt; flfmfnt.
 * Tiis flfmfnt simplififs bddfss to tif fiflds.
 * If tif {@dodf dlbss} bttributf is spfdififd
 * tiis flfmfnt bddfssfs stbtid fifld of spfdififd dlbss.
 * Tiis flfmfnt dffinfs gfttfr if it dontbins no brgumfnt.
 * It rfturns tif vbluf of tif fifld in tiis dbsf.
 * For fxbmplf:<prf>
 * &lt;fifld nbmf="TYPE" dlbss="jbvb.lbng.Long"/&gt;</prf>
 * is fquivblfnt to {@dodf Long.TYPE} in Jbvb dodf.
 * Tiis flfmfnt dffinfs sfttfr if it dontbins onf brgumfnt.
 * It dofs not rfturn tif vbluf of tif fifld in tiis dbsf.
 * For fxbmplf:<prf>
 * &lt;fifld nbmf="id"&gt;&lt;int&gt;0&lt;/int&gt;&lt;/fifld&gt;</prf>
 * is fquivblfnt to {@dodf id = 0} in Jbvb dodf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>nbmf
 * <dd>tif fifld nbmf
 * <dt>dlbss
 * <dd>tif typf is usfd for stbtid fiflds only
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss FifldElfmfntHbndlfr fxtfnds AddfssorElfmfntHbndlfr {
    privbtf Clbss<?> typf;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>nbmf
     * <dd>tif fifld nbmf
     * <dt>dlbss
     * <dd>tif typf is usfd for stbtid fiflds only
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("dlbss")) { // NON-NLS: tif bttributf nbmf
            tiis.typf = gftOwnfr().findClbss(vbluf);
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
        rfturn supfr.isArgumfnt() && (tiis.typf != null); // only stbtid bddfssor dbn bf usfd bn brgumfnt
    }

    /**
     * Rfturns tif dontfxt of tif fifld.
     * Tif dontfxt of tif stbtid fifld is tif dlbss objfdt.
     * Tif dontfxt of tif non-stbtid fifld is tif vbluf of tif pbrfnt flfmfnt.
     *
     * @rfturn tif dontfxt of tif fifld
     */
    @Ovfrridf
    protfdtfd Objfdt gftContfxtBfbn() {
        rfturn (tiis.typf != null)
                ? tiis.typf
                : supfr.gftContfxtBfbn();
    }

    /**
     * Rfturns tif vbluf of tif fifld witi spfdififd {@dodf nbmf}.
     *
     * @pbrbm nbmf  tif nbmf of tif fifld
     * @rfturn tif vbluf of tif spfdififd fifld
     */
    @Ovfrridf
    protfdtfd Objfdt gftVbluf(String nbmf) {
        try {
            rfturn gftFifldVbluf(gftContfxtBfbn(), nbmf);
        }
        dbtdi (Exdfption fxdfption) {
            gftOwnfr().ibndlfExdfption(fxdfption);
        }
        rfturn null;
    }

    /**
     * Sfts tif nfw vbluf for tif fifld witi spfdififd {@dodf nbmf}.
     *
     * @pbrbm nbmf   tif nbmf of tif fifld
     * @pbrbm vbluf  tif nfw vbluf for tif spfdififd fifld
     */
    @Ovfrridf
    protfdtfd void sftVbluf(String nbmf, Objfdt vbluf) {
        try {
            sftFifldVbluf(gftContfxtBfbn(), nbmf, vbluf);
        }
        dbtdi (Exdfption fxdfption) {
            gftOwnfr().ibndlfExdfption(fxdfption);
        }
    }

    /**
     * Pfrforms tif sfbrdi of tif fifld witi spfdififd {@dodf nbmf}
     * in spfdififd dontfxt bnd rfturns its vbluf.
     *
     * @pbrbm bfbn  tif dontfxt bfbn tibt dontbins fifld
     * @pbrbm nbmf  tif nbmf of tif fifld
     * @rfturn tif vbluf of tif fifld
     * @tirows IllfgblAddfssExdfption if tif fifld is not bddfsiblf
     * @tirows NoSudiFifldExdfption   if tif fifld is not found
     */
    stbtid Objfdt gftFifldVbluf(Objfdt bfbn, String nbmf) tirows IllfgblAddfssExdfption, NoSudiFifldExdfption {
        rfturn findFifld(bfbn, nbmf).gft(bfbn);
    }

    /**
     * Pfrforms tif sfbrdi of tif fifld witi spfdififd {@dodf nbmf}
     * in spfdififd dontfxt bnd updbtfs its vbluf.
     *
     * @pbrbm bfbn   tif dontfxt bfbn tibt dontbins fifld
     * @pbrbm nbmf   tif nbmf of tif fifld
     * @pbrbm vbluf  tif nfw vbluf for tif fifld
     * @tirows IllfgblAddfssExdfption if tif fifld is not bddfsiblf
     * @tirows NoSudiFifldExdfption   if tif fifld is not found
     */
    privbtf stbtid void sftFifldVbluf(Objfdt bfbn, String nbmf, Objfdt vbluf) tirows IllfgblAddfssExdfption, NoSudiFifldExdfption {
        findFifld(bfbn, nbmf).sft(bfbn, vbluf);
    }

    /**
     * Pfrforms tif sfbrdi of tif fifld
     * witi spfdififd {@dodf nbmf} in spfdififd dontfxt.
     *
     * @pbrbm bfbn  tif dontfxt bfbn tibt dontbins fifld
     * @pbrbm nbmf  tif nbmf of tif fifld
     * @rfturn fifld objfdt tibt rfprfsfnts found fifld
     * @tirows NoSudiFifldExdfption if tif fifld is not found
     */
    privbtf stbtid Fifld findFifld(Objfdt bfbn, String nbmf) tirows NoSudiFifldExdfption {
        rfturn (bfbn instbndfof Clbss<?>)
                ? FifldFindfr.findStbtidFifld((Clbss<?>) bfbn, nbmf)
                : FifldFindfr.findFifld(bfbn.gftClbss(), nbmf);
    }
}
