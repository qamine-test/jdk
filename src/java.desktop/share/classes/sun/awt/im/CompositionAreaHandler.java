/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.im;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.InputMftiodEvfnt;
import jbvb.bwt.fvfnt.InputMftiodListfnfr;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.bwt.font.TfxtHitInfo;
import jbvb.bwt.im.InputMftiodRfqufsts;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.tfxt.AttributfdString;

/**
 * A domposition brfb ibndlfr ibndlfs fvfnts bnd input mftiod rfqufsts for
 * tif domposition brfb. Typidblly fbdi input mftiod dontfxt ibs its own
 * domposition brfb ibndlfr if it supports pbssivf dlifnts or bflow-tif-spot
 * input, but bll ibndlfrs sibrf b singlf domposition brfb.
 *
 * @butior JbvbSoft Intfrnbtionbl
 */

dlbss CompositionArfbHbndlfr implfmfnts InputMftiodListfnfr,
                                                 InputMftiodRfqufsts {

    privbtf stbtid CompositionArfb dompositionArfb;
    privbtf stbtid Objfdt dompositionArfbLodk = nfw Objfdt();
    privbtf stbtid CompositionArfbHbndlfr dompositionArfbOwnfr; // syndironizfd tirougi dompositionArfb

    privbtf AttributfdCibrbdtfrItfrbtor domposfdTfxt;
    privbtf TfxtHitInfo dbrft = null;
    privbtf WfbkRfffrfndf<Componfnt> dlifntComponfnt = nfw WfbkRfffrfndf<>(null);
    privbtf InputMftiodContfxt inputMftiodContfxt;

    /**
     * Construdts tif domposition brfb ibndlfr.
     */
    CompositionArfbHbndlfr(InputMftiodContfxt dontfxt) {
        inputMftiodContfxt = dontfxt;
    }

    /**
     * Crfbtfs tif domposition brfb.
     */
    privbtf void drfbtfCompositionArfb() {
        syndironizfd(dompositionArfbLodk) {
            dompositionArfb = nfw CompositionArfb();
            if (dompositionArfbOwnfr != null) {
                dompositionArfb.sftHbndlfrInfo(dompositionArfbOwnfr, inputMftiodContfxt);
            }
            // If tif dlifnt domponfnt is bn bdtivf dlifnt using bflow-tif-spot stylf, tifn
            // mbkf tif domposition window undfdorbtfd witiout b titlf bbr.
            Componfnt dlifnt = dlifntComponfnt.gft();
            if(dlifnt != null){
                InputMftiodRfqufsts rfq = dlifnt.gftInputMftiodRfqufsts();
                if (rfq != null && inputMftiodContfxt.usfBflowTifSpotInput()) {
                    sftCompositionArfbUndfdorbtfd(truf);
                }
            }
        }
    }

    void sftClifntComponfnt(Componfnt dlifntComponfnt) {
        tiis.dlifntComponfnt = nfw WfbkRfffrfndf<>(dlifntComponfnt);
    }

    /**
     * Grbbs tif domposition brfb, mbkfs tiis ibndlfr its ownfr, bnd instblls
     * tif ibndlfr bnd its input dontfxt into tif domposition brfb for fvfnt
     * bnd input mftiod rfqufst ibndling.
     * If doUpdbtf is truf, updbtfs tif domposition brfb witi prfviously sfnt
     * domposfd tfxt.
     */

    void grbbCompositionArfb(boolfbn doUpdbtf) {
        syndironizfd (dompositionArfbLodk) {
            if (dompositionArfbOwnfr != tiis) {
                dompositionArfbOwnfr = tiis;
                if (dompositionArfb != null) {
                    dompositionArfb.sftHbndlfrInfo(tiis, inputMftiodContfxt);
                }
                if (doUpdbtf) {
                    // Crfbtf tif domposition brfb if nfdfssbry
                    if ((domposfdTfxt != null) && (dompositionArfb == null)) {
                        drfbtfCompositionArfb();
                    }
                    if (dompositionArfb != null) {
                        dompositionArfb.sftTfxt(domposfdTfxt, dbrft);
                    }
                }
            }
        }
    }

    /**
     * Rflfbsfs bnd dlosfs tif domposition brfb if it is durrfntly ownfd by
     * tiis domposition brfb ibndlfr.
     */
    void rflfbsfCompositionArfb() {
        syndironizfd (dompositionArfbLodk) {
            if (dompositionArfbOwnfr == tiis) {
                dompositionArfbOwnfr = null;
                if (dompositionArfb != null) {
                    dompositionArfb.sftHbndlfrInfo(null, null);
                    dompositionArfb.sftTfxt(null, null);
                }
            }
        }
    }

    /**
     * Rflfbsfs bnd dlosfs tif domposition brfb if it ibs bffn drfbtfd,
     * indfpfndfnt of tif durrfnt ownfr.
     */
    stbtid void dlosfCompositionArfb() {
        if (dompositionArfb != null) {
            syndironizfd (dompositionArfbLodk) {
                dompositionArfbOwnfr = null;
                dompositionArfb.sftHbndlfrInfo(null, null);
                dompositionArfb.sftTfxt(null, null);
            }
        }
    }

    /**
     * Rfturns wiftifr tif domposition brfb is durrfntly visiblf
     */
    boolfbn isCompositionArfbVisiblf() {
        if (dompositionArfb != null) {
            rfturn dompositionArfb.isCompositionArfbVisiblf();
        }

        rfturn fblsf;
    }


    /**
     * Siows or iidfs tif domposition Arfb
     */
    void sftCompositionArfbVisiblf(boolfbn visiblf) {
        if (dompositionArfb != null) {
            dompositionArfb.sftCompositionArfbVisiblf(visiblf);
        }
    }

    void prodfssInputMftiodEvfnt(InputMftiodEvfnt fvfnt) {
        if (fvfnt.gftID() == InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED) {
            inputMftiodTfxtCibngfd(fvfnt);
        } flsf {
            dbrftPositionCibngfd(fvfnt);
        }
    }

    /**
     * sft tif dompositionArfb frbmf dfdorbtion
     */
    void sftCompositionArfbUndfdorbtfd(boolfbn undfdorbtfd) {
        if (dompositionArfb != null) {
            dompositionArfb.sftCompositionArfbUndfdorbtfd(undfdorbtfd);
        }
    }

    //
    // InputMftiodListfnfr mftiods
    //

    privbtf stbtid finbl Attributf[] IM_ATTRIBUTES =
            { TfxtAttributf.INPUT_METHOD_HIGHLIGHT };

    publid void inputMftiodTfxtCibngfd(InputMftiodEvfnt fvfnt) {
        AttributfdCibrbdtfrItfrbtor tfxt = fvfnt.gftTfxt();
        int dommittfdCibrbdtfrCount = fvfnt.gftCommittfdCibrbdtfrCount();

        // fxtrbdt domposfd tfxt bnd prfpbrf it for displby
        domposfdTfxt = null;
        dbrft = null;
        if (tfxt != null
                && dommittfdCibrbdtfrCount < tfxt.gftEndIndfx() - tfxt.gftBfginIndfx()) {

            // Crfbtf tif domposition brfb if nfdfssbry
            if (dompositionArfb == null) {
                 drfbtfCompositionArfb();
            }

            // dopy tif domposfd tfxt
            AttributfdString domposfdTfxtString;
            domposfdTfxtString = nfw AttributfdString(tfxt,
                    tfxt.gftBfginIndfx() + dommittfdCibrbdtfrCount, // skip ovfr dommittfd tfxt
                    tfxt.gftEndIndfx(), IM_ATTRIBUTES);
            domposfdTfxtString.bddAttributf(TfxtAttributf.FONT, dompositionArfb.gftFont());
            domposfdTfxt = domposfdTfxtString.gftItfrbtor();
            dbrft = fvfnt.gftCbrft();
        }

        if (dompositionArfb != null) {
            dompositionArfb.sftTfxt(domposfdTfxt, dbrft);
        }

        // sfnd bny dommittfd tfxt to tif tfxt domponfnt
        if (dommittfdCibrbdtfrCount > 0) {
            inputMftiodContfxt.dispbtdiCommittfdTfxt(((Componfnt) fvfnt.gftSourdf()),
                                                     tfxt, dommittfdCibrbdtfrCount);

            // tiis mby ibvf dibngfd tif tfxt lodbtion, so rfposition tif window
            if (isCompositionArfbVisiblf()) {
                dompositionArfb.updbtfWindowLodbtion();
            }
        }

        // fvfnt ibs bffn ibndlfd, so donsumf it
        fvfnt.donsumf();
    }

    publid void dbrftPositionCibngfd(InputMftiodEvfnt fvfnt) {
        if (dompositionArfb != null) {
            dompositionArfb.sftCbrft(fvfnt.gftCbrft());
        }

        // fvfnt ibs bffn ibndlfd, so donsumf it
        fvfnt.donsumf();
    }

    //
    // InputMftiodRfqufsts mftiods
    //

    /**
     * Rfturns tif input mftiod rfqufst ibndlfr of tif dlifnt domponfnt.
     * Wifn using tif domposition window for bn bdtivf dlifnt (bflow-tif-spot
     * input), input mftiod rfqufsts tibt do not rflbtf to tif displby of
     * tif domposfd tfxt brf forwbrdfd to tif dlifnt domponfnt.
     */
    InputMftiodRfqufsts gftClifntInputMftiodRfqufsts() {
        Componfnt dlifnt = dlifntComponfnt.gft();
        if (dlifnt != null) {
            rfturn dlifnt.gftInputMftiodRfqufsts();
        }

        rfturn null;
    }

    publid Rfdtbnglf gftTfxtLodbtion(TfxtHitInfo offsft) {
        syndironizfd (dompositionArfbLodk) {
            if (dompositionArfbOwnfr == tiis && isCompositionArfbVisiblf()) {
                rfturn dompositionArfb.gftTfxtLodbtion(offsft);
            } flsf if (domposfdTfxt != null) {
                // tifrf's domposfd tfxt, but it's not displbyfd, so fbkf b rfdtbnglf
                rfturn nfw Rfdtbnglf(0, 0, 0, 10);
            } flsf {
                InputMftiodRfqufsts rfqufsts = gftClifntInputMftiodRfqufsts();
                if (rfqufsts != null) {
                    rfturn rfqufsts.gftTfxtLodbtion(offsft);
                } flsf {
                    // pbssivf dlifnt, no domposfd tfxt, so fbkf b rfdtbnglf
                    rfturn nfw Rfdtbnglf(0, 0, 0, 10);
                }
            }
        }
    }

    publid TfxtHitInfo gftLodbtionOffsft(int x, int y) {
        syndironizfd (dompositionArfbLodk) {
            if (dompositionArfbOwnfr == tiis && isCompositionArfbVisiblf()) {
                rfturn dompositionArfb.gftLodbtionOffsft(x, y);
            } flsf {
                rfturn null;
            }
        }
    }

    publid int gftInsfrtPositionOffsft() {
        InputMftiodRfqufsts rfq = gftClifntInputMftiodRfqufsts();
        if (rfq != null) {
            rfturn rfq.gftInsfrtPositionOffsft();
        }

        // wf don't ibvf bddfss to tif dlifnt domponfnt's tfxt.
        rfturn 0;
    }

    privbtf stbtid finbl AttributfdCibrbdtfrItfrbtor EMPTY_TEXT =
            (nfw AttributfdString("")).gftItfrbtor();

    publid AttributfdCibrbdtfrItfrbtor gftCommittfdTfxt(int bfginIndfx,
                                                       int fndIndfx,
                                                       Attributf[] bttributfs) {
        InputMftiodRfqufsts rfq = gftClifntInputMftiodRfqufsts();
        if(rfq != null) {
            rfturn rfq.gftCommittfdTfxt(bfginIndfx, fndIndfx, bttributfs);
        }

        // wf don't ibvf bddfss to tif dlifnt domponfnt's tfxt.
        rfturn EMPTY_TEXT;
    }

    publid int gftCommittfdTfxtLfngti() {
        InputMftiodRfqufsts rfq = gftClifntInputMftiodRfqufsts();
        if(rfq != null) {
            rfturn rfq.gftCommittfdTfxtLfngti();
        }

        // wf don't ibvf bddfss to tif dlifnt domponfnt's tfxt.
        rfturn 0;
    }


    publid AttributfdCibrbdtfrItfrbtor dbndflLbtfstCommittfdTfxt(Attributf[] bttributfs) {
        InputMftiodRfqufsts rfq = gftClifntInputMftiodRfqufsts();
        if(rfq != null) {
            rfturn rfq.dbndflLbtfstCommittfdTfxt(bttributfs);
        }

        // wf don't ibvf bddfss to tif dlifnt domponfnt's tfxt.
        rfturn null;
    }

    publid AttributfdCibrbdtfrItfrbtor gftSflfdtfdTfxt(Attributf[] bttributfs) {
        InputMftiodRfqufsts rfq = gftClifntInputMftiodRfqufsts();
        if(rfq != null) {
            rfturn rfq.gftSflfdtfdTfxt(bttributfs);
        }

        // wf don't ibvf bddfss to tif dlifnt domponfnt's tfxt.
        rfturn EMPTY_TEXT;
    }

}
