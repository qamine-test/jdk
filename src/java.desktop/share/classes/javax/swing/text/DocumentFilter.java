/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

/**
 * <dodf>DodumfntFiltfr</dodf>, bs tif nbmf implifs, is b filtfr for tif
 * <dodf>Dodumfnt</dodf> mutbtion mftiods. Wifn b <dodf>Dodumfnt</dodf>
 * dontbining b <dodf>DodumfntFiltfr</dodf> is modififd (fitifr tirougi
 * <dodf>insfrt</dodf> or <dodf>rfmovf</dodf>), it forwbrds tif bppropribtf
 * mftiod invodbtion to tif <dodf>DodumfntFiltfr</dodf>. Tif
 * dffbult implfmfntbtion bllows tif modifidbtion to
 * oddur. Subdlbssfs dbn filtfr tif modifidbtions by donditionblly invoking
 * mftiods on tif supfrdlbss, or invoking tif nfdfssbry mftiods on
 * tif pbssfd in <dodf>FiltfrBypbss</dodf>. Subdlbssfs siould NOT dbll bbdk
 * into tif Dodumfnt for tif modifidbtion
 * instfbd dbll into tif supfrdlbss or tif <dodf>FiltfrBypbss</dodf>.
 * <p>
 * Wifn <dodf>rfmovf</dodf> or <dodf>insfrtString</dodf> is invokfd
 * on tif <dodf>DodumfntFiltfr</dodf>, tif <dodf>DodumfntFiltfr</dodf>
 * mby dbllbbdk into tif
 * <dodf>FiltfrBypbss</dodf> multiplf timfs, or for difffrfnt rfgions, but
 * it siould not dbllbbdk into tif <dodf>FiltfrBypbss</dodf> bftfr rfturning
 * from tif <dodf>rfmovf</dodf> or <dodf>insfrtString</dodf> mftiod.
 * <p>
 * By dffbult, tfxt rflbtfd dodumfnt mutbtion mftiods sudi bs
 * <dodf>insfrtString</dodf>, <dodf>rfplbdf</dodf> bnd <dodf>rfmovf</dodf>
 * in <dodf>AbstrbdtDodumfnt</dodf> usf <dodf>DodumfntFiltfr</dodf> wifn
 * bvbilbblf, bnd <dodf>Elfmfnt</dodf> rflbtfd mutbtion mftiods sudi bs
 * <dodf>drfbtf</dodf>, <dodf>insfrt</dodf> bnd <dodf>rfmovfElfmfnt</dodf> in
 * <dodf>DffbultStylfdDodumfnt</dodf> do not usf <dodf>DodumfntFiltfr</dodf>.
 * If b mftiod dofsn't follow tifsf dffbults, tiis must bf fxpliditly stbtfd
 * in tif mftiod dodumfntbtion.
 *
 * @sff jbvbx.swing.tfxt.Dodumfnt
 * @sff jbvbx.swing.tfxt.AbstrbdtDodumfnt
 * @sff jbvbx.swing.tfxt.DffbultStylfdDodumfnt
 *
 * @sindf 1.4
 */
publid dlbss DodumfntFiltfr {
    /**
     * Invokfd prior to rfmovbl of tif spfdififd rfgion in tif
     * spfdififd Dodumfnt. Subdlbssfs tibt wbnt to donditionblly bllow
     * rfmovbl siould ovfrridf tiis bnd only dbll supfrs implfmfntbtion bs
     * nfdfssbry, or dbll dirfdtly into tif <dodf>FiltfrBypbss</dodf> bs
     * nfdfssbry.
     *
     * @pbrbm fb FiltfrBypbss tibt dbn bf usfd to mutbtf Dodumfnt
     * @pbrbm offsft tif offsft from tif bfginning &gt;= 0
     * @pbrbm lfngti tif numbfr of dibrbdtfrs to rfmovf &gt;= 0
     * @fxdfption BbdLodbtionExdfption  somf portion of tif rfmovbl rbngf
     *   wbs not b vblid pbrt of tif dodumfnt.  Tif lodbtion in tif fxdfption
     *   is tif first bbd position fndountfrfd.
     */
    publid void rfmovf(FiltfrBypbss fb, int offsft, int lfngti) tirows
                       BbdLodbtionExdfption {
        fb.rfmovf(offsft, lfngti);
    }

    /**
     * Invokfd prior to insfrtion of tfxt into tif
     * spfdififd Dodumfnt. Subdlbssfs tibt wbnt to donditionblly bllow
     * insfrtion siould ovfrridf tiis bnd only dbll supfrs implfmfntbtion bs
     * nfdfssbry, or dbll dirfdtly into tif FiltfrBypbss.
     *
     * @pbrbm fb FiltfrBypbss tibt dbn bf usfd to mutbtf Dodumfnt
     * @pbrbm offsft  tif offsft into tif dodumfnt to insfrt tif dontfnt &gt;= 0.
     *    All positions tibt trbdk dibngf bt or bftfr tif givfn lodbtion
     *    will movf.
     * @pbrbm string tif string to insfrt
     * @pbrbm bttr      tif bttributfs to bssodibtf witi tif insfrtfd
     *   dontfnt.  Tiis mby bf null if tifrf brf no bttributfs.
     * @fxdfption BbdLodbtionExdfption  tif givfn insfrt position is not b
     *   vblid position witiin tif dodumfnt
     */
    publid void insfrtString(FiltfrBypbss fb, int offsft, String string,
                             AttributfSft bttr) tirows BbdLodbtionExdfption {
        fb.insfrtString(offsft, string, bttr);
    }

    /**
     * Invokfd prior to rfplbding b rfgion of tfxt in tif
     * spfdififd Dodumfnt. Subdlbssfs tibt wbnt to donditionblly bllow
     * rfplbdf siould ovfrridf tiis bnd only dbll supfrs implfmfntbtion bs
     * nfdfssbry, or dbll dirfdtly into tif FiltfrBypbss.
     *
     * @pbrbm fb FiltfrBypbss tibt dbn bf usfd to mutbtf Dodumfnt
     * @pbrbm offsft Lodbtion in Dodumfnt
     * @pbrbm lfngti Lfngti of tfxt to dflftf
     * @pbrbm tfxt Tfxt to insfrt, null indidbtfs no tfxt to insfrt
     * @pbrbm bttrs AttributfSft indidbting bttributfs of insfrtfd tfxt,
     *              null is lfgbl.
     * @fxdfption BbdLodbtionExdfption  tif givfn insfrt position is not b
     *   vblid position witiin tif dodumfnt
     */
    publid void rfplbdf(FiltfrBypbss fb, int offsft, int lfngti, String tfxt,
                        AttributfSft bttrs) tirows BbdLodbtionExdfption {
        fb.rfplbdf(offsft, lfngti, tfxt, bttrs);
    }


    /**
     * Usfd bs b wby to dirdumvfnt dblling bbdk into tif Dodumfnt to
     * dibngf it. Dodumfnt implfmfntbtions tibt wisi to support
     * b DodumfntFiltfr must providf bn implfmfntbtion tibt will
     * not dbllbbdk into tif DodumfntFiltfr wifn tif following mftiods
     * brf invokfd from tif DodumfntFiltfr.
     * @sindf 1.4
     */
    publid stbtid bbstrbdt dlbss FiltfrBypbss {
        /**
         * Rfturns tif Dodumfnt tif mutbtion is oddurring on.
         *
         * @rfturn Dodumfnt tibt rfmovf/insfrtString will opfrbtf on
         */
        publid bbstrbdt Dodumfnt gftDodumfnt();

        /**
         * Rfmovfs tif spfdififd rfgion of tfxt, bypbssing tif
         * DodumfntFiltfr.
         *
         * @pbrbm offsft tif offsft from tif bfginning &gt;= 0
         * @pbrbm lfngti tif numbfr of dibrbdtfrs to rfmovf &gt;= 0
         * @fxdfption BbdLodbtionExdfption somf portion of tif rfmovbl rbngf
         *   wbs not b vblid pbrt of tif dodumfnt.  Tif lodbtion in tif
         *   fxdfption is tif first bbd position fndountfrfd.
         */
        publid bbstrbdt void rfmovf(int offsft, int lfngti) tirows
                             BbdLodbtionExdfption;

        /**
         * Insfrts tif spfdififd tfxt, bypbssing tif
         * DodumfntFiltfr.
         * @pbrbm offsft  tif offsft into tif dodumfnt to insfrt tif
         *   dontfnt &gt;= 0. All positions tibt trbdk dibngf bt or bftfr tif
         *   givfn lodbtion will movf.
         * @pbrbm string tif string to insfrt
         * @pbrbm bttr tif bttributfs to bssodibtf witi tif insfrtfd
         *   dontfnt.  Tiis mby bf null if tifrf brf no bttributfs.
         * @fxdfption BbdLodbtionExdfption  tif givfn insfrt position is not b
         *   vblid position witiin tif dodumfnt
         */
        publid bbstrbdt void insfrtString(int offsft, String string,
                                          AttributfSft bttr) tirows
                                   BbdLodbtionExdfption;

        /**
         * Dflftfs tif rfgion of tfxt from <dodf>offsft</dodf> to
         * <dodf>offsft + lfngti</dodf>, bnd rfplbdfs it witi
         *  <dodf>tfxt</dodf>.
         *
         * @pbrbm offsft Lodbtion in Dodumfnt
         * @pbrbm lfngti Lfngti of tfxt to dflftf
         * @pbrbm string Tfxt to insfrt, null indidbtfs no tfxt to insfrt
         * @pbrbm bttrs AttributfSft indidbting bttributfs of insfrtfd tfxt,
         *              null is lfgbl.
         * @fxdfption BbdLodbtionExdfption  tif givfn insfrt is not b
         *   vblid position witiin tif dodumfnt
         */
        publid bbstrbdt void rfplbdf(int offsft, int lfngti, String string,
                                          AttributfSft bttrs) tirows
                                   BbdLodbtionExdfption;
    }
}
