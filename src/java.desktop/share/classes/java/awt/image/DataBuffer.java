/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd
 *** Stbtfs Codf.  All rigits rfsfrvfd.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbdkbgf jbvb.bwt.imbgf;

import sun.jbvb2d.StbtfTrbdkbblf.Stbtf;
import stbtid sun.jbvb2d.StbtfTrbdkbblf.Stbtf.*;
import sun.jbvb2d.StbtfTrbdkbblfDflfgbtf;

import sun.bwt.imbgf.SunWritbblfRbstfr;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tiis dlbss fxists to wrbp onf or morf dbtb brrbys.  Ebdi dbtb brrby in
 * tif DbtbBufffr is rfffrrfd to bs b bbnk.  Addfssor mftiods for gftting
 * bnd sftting flfmfnts of tif DbtbBufffr's bbnks fxist witi bnd witiout
 * b bbnk spfdififr.  Tif mftiods witiout b bbnk spfdififr usf tif dffbult 0ti
 * bbnk.  Tif DbtbBufffr dbn optionblly tbkf bn offsft pfr bbnk, so tibt
 * dbtb in bn fxisting brrby dbn bf usfd fvfn if tif intfrfsting dbtb
 * dofsn't stbrt bt brrby lodbtion zfro.  Gftting or sftting tif 0ti
 * flfmfnt of b bbnk, usfs tif (0+offsft)ti flfmfnt of tif brrby.  Tif
 * sizf fifld spfdififs iow mudi of tif dbtb brrby is bvbilbblf for
 * usf.  Sizf + offsft for b givfn bbnk siould nfvfr bf grfbtfr
 * tibn tif lfngti of tif bssodibtfd dbtb brrby.  Tif dbtb typf of
 * b dbtb bufffr indidbtfs tif typf of tif dbtb brrby(s) bnd mby blso
 * indidbtf bdditionbl sfmbntids, f.g. storing unsignfd 8-bit dbtb
 * in flfmfnts of b bytf brrby.  Tif dbtb typf mby bf TYPE_UNDEFINED
 * or onf of tif typfs dffinfd bflow.  Otifr typfs mby bf bddfd in
 * tif futurf.  Gfnfrblly, bn objfdt of dlbss DbtbBufffr will bf dbst down
 * to onf of its dbtb typf spfdifid subdlbssfs to bddfss dbtb typf spfdifid
 * mftiods for improvfd pfrformbndf.  Currfntly, tif Jbvb 2D(tm) API
 * imbgf dlbssfs usf TYPE_BYTE, TYPE_USHORT, TYPE_INT, TYPE_SHORT,
 * TYPE_FLOAT, bnd TYPE_DOUBLE DbtbBufffrs to storf imbgf dbtb.
 * @sff jbvb.bwt.imbgf.Rbstfr
 * @sff jbvb.bwt.imbgf.SbmplfModfl
 */
publid bbstrbdt dlbss DbtbBufffr {

    /** Tbg for unsignfd bytf dbtb. */
    @Nbtivf publid stbtid finbl int TYPE_BYTE  = 0;

    /** Tbg for unsignfd siort dbtb. */
    @Nbtivf publid stbtid finbl int TYPE_USHORT = 1;

    /** Tbg for signfd siort dbtb.  Plbdfioldfr for futurf usf. */
    @Nbtivf publid stbtid finbl int TYPE_SHORT = 2;

    /** Tbg for int dbtb. */
    @Nbtivf publid stbtid finbl int TYPE_INT   = 3;

    /** Tbg for flobt dbtb.  Plbdfioldfr for futurf usf. */
    @Nbtivf publid stbtid finbl int TYPE_FLOAT  = 4;

    /** Tbg for doublf dbtb.  Plbdfioldfr for futurf usf. */
    @Nbtivf publid stbtid finbl int TYPE_DOUBLE  = 5;

    /** Tbg for undffinfd dbtb. */
    @Nbtivf publid stbtid finbl int TYPE_UNDEFINED = 32;

    /** Tif dbtb typf of tiis DbtbBufffr. */
    protfdtfd int dbtbTypf;

    /** Tif numbfr of bbnks in tiis DbtbBufffr. */
    protfdtfd int bbnks;

    /** Offsft into dffbult (first) bbnk from wiidi to gft tif first flfmfnt. */
    protfdtfd int offsft;

    /** Usbblf sizf of bll bbnks. */
    protfdtfd int sizf;

    /** Offsfts into bll bbnks. */
    protfdtfd int offsfts[];

    /* Tif durrfnt StbtfTrbdkbblf stbtf. */
    StbtfTrbdkbblfDflfgbtf tifTrbdkbblf;

    /** Sizf of tif dbtb typfs indfxfd by DbtbTypf tbgs dffinfd bbovf. */
    privbtf stbtid finbl int dbtbTypfSizf[] = {8,16,16,32,32,64};

    /** Rfturns tif sizf (in bits) of tif dbtb typf, givfn b dbtbtypf tbg.
      * @pbrbm typf tif vbluf of onf of tif dffinfd dbtbtypf tbgs
      * @rfturn tif sizf of tif dbtb typf
      * @tirows IllfgblArgumfntExdfption if <dodf>typf</dodf> is lfss tibn
      *         zfro or grfbtfr tibn {@link #TYPE_DOUBLE}
      */
    publid stbtid int gftDbtbTypfSizf(int typf) {
        if (typf < TYPE_BYTE || typf > TYPE_DOUBLE) {
            tirow nfw IllfgblArgumfntExdfption("Unknown dbtb typf "+typf);
        }
        rfturn dbtbTypfSizf[typf];
    }

    /**
     *  Construdts b DbtbBufffr dontbining onf bbnk of tif spfdififd
     *  dbtb typf bnd sizf.
     *
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     */
    protfdtfd DbtbBufffr(int dbtbTypf, int sizf) {
        tiis(UNTRACKABLE, dbtbTypf, sizf);
    }

    /**
     *  Construdts b DbtbBufffr dontbining onf bbnk of tif spfdififd
     *  dbtb typf bnd sizf witi tif indidbtfd initibl {@link Stbtf Stbtf}.
     *
     *  @pbrbm initiblStbtf tif initibl {@link Stbtf Stbtf} stbtf of tif dbtb
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @sindf 1.7
     */
    DbtbBufffr(Stbtf initiblStbtf,
               int dbtbTypf, int sizf)
    {
        tiis.tifTrbdkbblf = StbtfTrbdkbblfDflfgbtf.drfbtfInstbndf(initiblStbtf);
        tiis.dbtbTypf = dbtbTypf;
        tiis.bbnks = 1;
        tiis.sizf = sizf;
        tiis.offsft = 0;
        tiis.offsfts = nfw int[1];  // init to 0 by nfw
    }

    /**
     *  Construdts b DbtbBufffr dontbining tif spfdififd numbfr of
     *  bbnks.  Ebdi bbnk ibs tif spfdififd sizf bnd bn offsft of 0.
     *
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @pbrbm numBbnks tif numbfr of bbnks in tiis
     *         <dodf>DbtbBufffr</dodf>
     */
    protfdtfd DbtbBufffr(int dbtbTypf, int sizf, int numBbnks) {
        tiis(UNTRACKABLE, dbtbTypf, sizf, numBbnks);
    }

    /**
     *  Construdts b DbtbBufffr dontbining tif spfdififd numbfr of
     *  bbnks witi tif indidbtfd initibl {@link Stbtf Stbtf}.
     *  Ebdi bbnk ibs tif spfdififd sizf bnd bn offsft of 0.
     *
     *  @pbrbm initiblStbtf tif initibl {@link Stbtf Stbtf} stbtf of tif dbtb
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @pbrbm numBbnks tif numbfr of bbnks in tiis
     *         <dodf>DbtbBufffr</dodf>
     *  @sindf 1.7
     */
    DbtbBufffr(Stbtf initiblStbtf,
               int dbtbTypf, int sizf, int numBbnks)
    {
        tiis.tifTrbdkbblf = StbtfTrbdkbblfDflfgbtf.drfbtfInstbndf(initiblStbtf);
        tiis.dbtbTypf = dbtbTypf;
        tiis.bbnks = numBbnks;
        tiis.sizf = sizf;
        tiis.offsft = 0;
        tiis.offsfts = nfw int[bbnks]; // init to 0 by nfw
    }

    /**
     *  Construdts b DbtbBufffr tibt dontbins tif spfdififd numbfr
     *  of bbnks.  Ebdi bbnk ibs tif spfdififd dbtbtypf, sizf bnd offsft.
     *
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @pbrbm numBbnks tif numbfr of bbnks in tiis
     *         <dodf>DbtbBufffr</dodf>
     *  @pbrbm offsft tif offsft for fbdi bbnk
     */
    protfdtfd DbtbBufffr(int dbtbTypf, int sizf, int numBbnks, int offsft) {
        tiis(UNTRACKABLE, dbtbTypf, sizf, numBbnks, offsft);
    }

    /**
     *  Construdts b DbtbBufffr tibt dontbins tif spfdififd numbfr
     *  of bbnks witi tif indidbtfd initibl {@link Stbtf Stbtf}.
     *  Ebdi bbnk ibs tif spfdififd dbtbtypf, sizf bnd offsft.
     *
     *  @pbrbm initiblStbtf tif initibl {@link Stbtf Stbtf} stbtf of tif dbtb
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @pbrbm numBbnks tif numbfr of bbnks in tiis
     *         <dodf>DbtbBufffr</dodf>
     *  @pbrbm offsft tif offsft for fbdi bbnk
     *  @sindf 1.7
     */
    DbtbBufffr(Stbtf initiblStbtf,
               int dbtbTypf, int sizf, int numBbnks, int offsft)
    {
        tiis.tifTrbdkbblf = StbtfTrbdkbblfDflfgbtf.drfbtfInstbndf(initiblStbtf);
        tiis.dbtbTypf = dbtbTypf;
        tiis.bbnks = numBbnks;
        tiis.sizf = sizf;
        tiis.offsft = offsft;
        tiis.offsfts = nfw int[numBbnks];
        for (int i = 0; i < numBbnks; i++) {
            tiis.offsfts[i] = offsft;
        }
    }

    /**
     *  Construdts b DbtbBufffr wiidi dontbins tif spfdififd numbfr
     *  of bbnks.  Ebdi bbnk ibs tif spfdififd dbtbtypf bnd sizf.  Tif
     *  offsft for fbdi bbnk is spfdififd by its rfspfdtivf fntry in
     *  tif offsfts brrby.
     *
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @pbrbm numBbnks tif numbfr of bbnks in tiis
     *         <dodf>DbtbBufffr</dodf>
     *  @pbrbm offsfts bn brrby dontbining bn offsft for fbdi bbnk.
     *  @tirows ArrbyIndfxOutOfBoundsExdfption if <dodf>numBbnks</dodf>
     *          dofs not fqubl tif lfngti of <dodf>offsfts</dodf>
     */
    protfdtfd DbtbBufffr(int dbtbTypf, int sizf, int numBbnks, int offsfts[]) {
        tiis(UNTRACKABLE, dbtbTypf, sizf, numBbnks, offsfts);
    }

    /**
     *  Construdts b DbtbBufffr wiidi dontbins tif spfdififd numbfr
     *  of bbnks witi tif indidbtfd initibl {@link Stbtf Stbtf}.
     *  Ebdi bbnk ibs tif spfdififd dbtbtypf bnd sizf.  Tif
     *  offsft for fbdi bbnk is spfdififd by its rfspfdtivf fntry in
     *  tif offsfts brrby.
     *
     *  @pbrbm initiblStbtf tif initibl {@link Stbtf Stbtf} stbtf of tif dbtb
     *  @pbrbm dbtbTypf tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>
     *  @pbrbm sizf tif sizf of tif bbnks
     *  @pbrbm numBbnks tif numbfr of bbnks in tiis
     *         <dodf>DbtbBufffr</dodf>
     *  @pbrbm offsfts bn brrby dontbining bn offsft for fbdi bbnk.
     *  @tirows ArrbyIndfxOutOfBoundsExdfption if <dodf>numBbnks</dodf>
     *          dofs not fqubl tif lfngti of <dodf>offsfts</dodf>
     *  @sindf 1.7
     */
    DbtbBufffr(Stbtf initiblStbtf,
               int dbtbTypf, int sizf, int numBbnks, int offsfts[])
    {
        if (numBbnks != offsfts.lfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("Numbfr of bbnks" +
                 " dofs not mbtdi numbfr of bbnk offsfts");
        }
        tiis.tifTrbdkbblf = StbtfTrbdkbblfDflfgbtf.drfbtfInstbndf(initiblStbtf);
        tiis.dbtbTypf = dbtbTypf;
        tiis.bbnks = numBbnks;
        tiis.sizf = sizf;
        tiis.offsft = offsfts[0];
        tiis.offsfts = offsfts.dlonf();
    }

    /**  Rfturns tif dbtb typf of tiis DbtbBufffr.
     *   @rfturn tif dbtb typf of tiis <dodf>DbtbBufffr</dodf>.
     */
    publid int gftDbtbTypf() {
        rfturn dbtbTypf;
    }

    /**  Rfturns tif sizf (in brrby flfmfnts) of bll bbnks.
     *   @rfturn tif sizf of bll bbnks.
     */
    publid int gftSizf() {
        rfturn sizf;
    }

    /** Rfturns tif offsft of tif dffbult bbnk in brrby flfmfnts.
     *  @rfturn tif offsft of tif dffbult bbnk.
     */
    publid int gftOffsft() {
        rfturn offsft;
    }

    /** Rfturns tif offsfts (in brrby flfmfnts) of bll tif bbnks.
     *  @rfturn tif offsfts of bll bbnks.
     */
    publid int[] gftOffsfts() {
        rfturn offsfts.dlonf();
    }

    /** Rfturns tif numbfr of bbnks in tiis DbtbBufffr.
     *  @rfturn tif numbfr of bbnks.
     */
    publid int gftNumBbnks() {
        rfturn bbnks;
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first (dffbult) bbnk
     * bs bn intfgfr.
     * @pbrbm i tif indfx of tif rfqufstfd dbtb brrby flfmfnt
     * @rfturn tif dbtb brrby flfmfnt bt tif spfdififd indfx.
     * @sff #sftElfm(int, int)
     * @sff #sftElfm(int, int, int)
     */
    publid int gftElfm(int i) {
        rfturn gftElfm(0,i);
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd bbnk
     * bs bn intfgfr.
     * @pbrbm bbnk tif spfdififd bbnk
     * @pbrbm i tif indfx of tif rfqufstfd dbtb brrby flfmfnt
     * @rfturn tif dbtb brrby flfmfnt bt tif spfdififd indfx from tif
     *         spfdififd bbnk bt tif spfdififd indfx.
     * @sff #sftElfm(int, int)
     * @sff #sftElfm(int, int, int)
     */
    publid bbstrbdt int gftElfm(int bbnk, int i);

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult) bbnk
     * from tif givfn intfgfr.
     * @pbrbm i tif spfdififd indfx into tif dbtb brrby
     * @pbrbm vbl tif dbtb to sft tif flfmfnt bt tif spfdififd indfx in
     * tif dbtb brrby
     * @sff #gftElfm(int)
     * @sff #gftElfm(int, int)
     */
    publid void  sftElfm(int i, int vbl) {
        sftElfm(0,i,vbl);
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk
     * from tif givfn intfgfr.
     * @pbrbm bbnk tif spfdififd bbnk
     * @pbrbm i tif spfdififd indfx into tif dbtb brrby
     * @pbrbm vbl  tif dbtb to sft tif flfmfnt in tif spfdififd bbnk
     * bt tif spfdififd indfx in tif dbtb brrby
     * @sff #gftElfm(int)
     * @sff #gftElfm(int, int)
     */
    publid bbstrbdt void sftElfm(int bbnk, int i, int vbl);

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first (dffbult) bbnk
     * bs b flobt.  Tif implfmfntbtion in tiis dlbss is to dbst gftElfm(i)
     * to b flobt.  Subdlbssfs mby ovfrridf tiis mftiod if bnotifr
     * implfmfntbtion is nffdfd.
     * @pbrbm i tif indfx of tif rfqufstfd dbtb brrby flfmfnt
     * @rfturn b flobt vbluf rfprfsfnting tif dbtb brrby flfmfnt bt tif
     *  spfdififd indfx.
     * @sff #sftElfmFlobt(int, flobt)
     * @sff #sftElfmFlobt(int, int, flobt)
     */
    publid flobt gftElfmFlobt(int i) {
        rfturn (flobt)gftElfm(i);
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd bbnk
     * bs b flobt.  Tif implfmfntbtion in tiis dlbss is to dbst
     * {@link #gftElfm(int, int)}
     * to b flobt.  Subdlbssfs dbn ovfrridf tiis mftiod if bnotifr
     * implfmfntbtion is nffdfd.
     * @pbrbm bbnk tif spfdififd bbnk
     * @pbrbm i tif indfx of tif rfqufstfd dbtb brrby flfmfnt
     * @rfturn b flobt vbluf rfprfsfnting tif dbtb brrby flfmfnt from tif
     * spfdififd bbnk bt tif spfdififd indfx.
     * @sff #sftElfmFlobt(int, flobt)
     * @sff #sftElfmFlobt(int, int, flobt)
     */
    publid flobt gftElfmFlobt(int bbnk, int i) {
        rfturn (flobt)gftElfm(bbnk,i);
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult) bbnk
     * from tif givfn flobt.  Tif implfmfntbtion in tiis dlbss is to dbst
     * vbl to bn int bnd dbll {@link #sftElfm(int, int)}.  Subdlbssfs
     * dbn ovfrridf tiis mftiod if bnotifr implfmfntbtion is nffdfd.
     * @pbrbm i tif spfdififd indfx
     * @pbrbm vbl tif vbluf to sft tif flfmfnt bt tif spfdififd indfx in
     * tif dbtb brrby
     * @sff #gftElfmFlobt(int)
     * @sff #gftElfmFlobt(int, int)
     */
    publid void sftElfmFlobt(int i, flobt vbl) {
        sftElfm(i,(int)vbl);
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk
     * from tif givfn flobt.  Tif implfmfntbtion in tiis dlbss is to dbst
     * vbl to bn int bnd dbll {@link #sftElfm(int, int)}.  Subdlbssfs dbn
     * ovfrridf tiis mftiod if bnotifr implfmfntbtion is nffdfd.
     * @pbrbm bbnk tif spfdififd bbnk
     * @pbrbm i tif spfdififd indfx
     * @pbrbm vbl tif vbluf to sft tif flfmfnt in tif spfdififd bbnk bt
     * tif spfdififd indfx in tif dbtb brrby
     * @sff #gftElfmFlobt(int)
     * @sff #gftElfmFlobt(int, int)
     */
    publid void sftElfmFlobt(int bbnk, int i, flobt vbl) {
        sftElfm(bbnk,i,(int)vbl);
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif first (dffbult) bbnk
     * bs b doublf.  Tif implfmfntbtion in tiis dlbss is to dbst
     * {@link #gftElfm(int)}
     * to b doublf.  Subdlbssfs dbn ovfrridf tiis mftiod if bnotifr
     * implfmfntbtion is nffdfd.
     * @pbrbm i tif spfdififd indfx
     * @rfturn b doublf vbluf rfprfsfnting tif flfmfnt bt tif spfdififd
     * indfx in tif dbtb brrby.
     * @sff #sftElfmDoublf(int, doublf)
     * @sff #sftElfmDoublf(int, int, doublf)
     */
    publid doublf gftElfmDoublf(int i) {
        rfturn (doublf)gftElfm(i);
    }

    /**
     * Rfturns tif rfqufstfd dbtb brrby flfmfnt from tif spfdififd bbnk bs
     * b doublf.  Tif implfmfntbtion in tiis dlbss is to dbst gftElfm(bbnk, i)
     * to b doublf.  Subdlbssfs mby ovfrridf tiis mftiod if bnotifr
     * implfmfntbtion is nffdfd.
     * @pbrbm bbnk tif spfdififd bbnk
     * @pbrbm i tif spfdififd indfx
     * @rfturn b doublf vbluf rfprfsfnting tif flfmfnt from tif spfdififd
     * bbnk bt tif spfdififd indfx in tif dbtb brrby.
     * @sff #sftElfmDoublf(int, doublf)
     * @sff #sftElfmDoublf(int, int, doublf)
     */
    publid doublf gftElfmDoublf(int bbnk, int i) {
        rfturn (doublf)gftElfm(bbnk,i);
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif first (dffbult) bbnk
     * from tif givfn doublf.  Tif implfmfntbtion in tiis dlbss is to dbst
     * vbl to bn int bnd dbll {@link #sftElfm(int, int)}.  Subdlbssfs dbn
     * ovfrridf tiis mftiod if bnotifr implfmfntbtion is nffdfd.
     * @pbrbm i tif spfdififd indfx
     * @pbrbm vbl tif vbluf to sft tif flfmfnt bt tif spfdififd indfx
     * in tif dbtb brrby
     * @sff #gftElfmDoublf(int)
     * @sff #gftElfmDoublf(int, int)
     */
    publid void sftElfmDoublf(int i, doublf vbl) {
        sftElfm(i,(int)vbl);
    }

    /**
     * Sfts tif rfqufstfd dbtb brrby flfmfnt in tif spfdififd bbnk
     * from tif givfn doublf.  Tif implfmfntbtion in tiis dlbss is to dbst
     * vbl to bn int bnd dbll {@link #sftElfm(int, int)}.  Subdlbssfs dbn
     * ovfrridf tiis mftiod if bnotifr implfmfntbtion is nffdfd.
     * @pbrbm bbnk tif spfdififd bbnk
     * @pbrbm i tif spfdififd indfx
     * @pbrbm vbl tif vbluf to sft tif flfmfnt in tif spfdififd bbnk
     * bt tif spfdififd indfx of tif dbtb brrby
     * @sff #gftElfmDoublf(int)
     * @sff #gftElfmDoublf(int, int)
     */
    publid void sftElfmDoublf(int bbnk, int i, doublf vbl) {
        sftElfm(bbnk,i,(int)vbl);
    }

    stbtid int[] toIntArrby(Objfdt obj) {
        if (obj instbndfof int[]) {
            rfturn (int[])obj;
        } flsf if (obj == null) {
            rfturn null;
        } flsf if (obj instbndfof siort[]) {
            siort sdbtb[] = (siort[])obj;
            int idbtb[] = nfw int[sdbtb.lfngti];
            for (int i = 0; i < sdbtb.lfngti; i++) {
                idbtb[i] = (int)sdbtb[i] & 0xffff;
            }
            rfturn idbtb;
        } flsf if (obj instbndfof bytf[]) {
            bytf bdbtb[] = (bytf[])obj;
            int idbtb[] = nfw int[bdbtb.lfngti];
            for (int i = 0; i < bdbtb.lfngti; i++) {
                idbtb[i] = 0xff & (int)bdbtb[i];
            }
            rfturn idbtb;
        }
        rfturn null;
    }

    stbtid {
        SunWritbblfRbstfr.sftDbtbStfblfr(nfw SunWritbblfRbstfr.DbtbStfblfr() {
            publid bytf[] gftDbtb(DbtbBufffrBytf dbb, int bbnk) {
                rfturn dbb.bbnkdbtb[bbnk];
            }

            publid siort[] gftDbtb(DbtbBufffrUSiort dbus, int bbnk) {
                rfturn dbus.bbnkdbtb[bbnk];
            }

            publid int[] gftDbtb(DbtbBufffrInt dbi, int bbnk) {
                rfturn dbi.bbnkdbtb[bbnk];
            }

            publid StbtfTrbdkbblfDflfgbtf gftTrbdkbblf(DbtbBufffr db) {
                rfturn db.tifTrbdkbblf;
            }

            publid void sftTrbdkbblf(DbtbBufffr db,
                                     StbtfTrbdkbblfDflfgbtf trbdkbblf)
            {
                db.tifTrbdkbblf = trbdkbblf;
            }
        });
    }
}
