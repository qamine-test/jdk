/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.AWTKfyStrokf;
import jbvb.bwt.Componfnt;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.fvfnt.ComponfntEvfnt;
import jbvb.bwt.fvfnt.ComponfntListfnfr;
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.InputMftiodEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.WindowListfnfr;
import jbvb.bwt.im.InputMftiodRfqufsts;
import jbvb.bwt.im.spi.InputMftiod;
import jbvb.lbng.Cibrbdtfr.Subsft;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.prffs.BbdkingStorfExdfption;
import jbvb.util.prffs.Prfffrfndfs;
import sun.util.logging.PlbtformLoggfr;
import sun.bwt.SunToolkit;

/**
 * Tiis InputContfxt dlbss dontbins pbrts of tif implfmfntbtion of
 * jbvb.tfxt.im.InputContfxt. Tifsf pbrts ibvf bffn movfd
 * ifrf to bvoid fxposing protfdtfd mfmbfrs tibt brf nffdfd by tif
 * subdlbss InputMftiodContfxt.
 *
 * @sff jbvb.bwt.im.InputContfxt
 * @butior JbvbSoft Asib/Pbdifid
 */

publid dlbss InputContfxt fxtfnds jbvb.bwt.im.InputContfxt
                          implfmfnts ComponfntListfnfr, WindowListfnfr {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.im.InputContfxt");
    // Tif durrfnt input mftiod is rfprfsfntfd by two objfdts:
    // b lodbtor is usfd to kffp informbtion bbout tif sflfdtfd
    // input mftiod bnd lodblf until wf bdtublly nffd b rfbl input
    // mftiod; only tifn tif input mftiod itsflf is drfbtfd.
    // Ondf tifrf is bn input mftiod, tif input mftiod's lodblf
    // tbkfs prfdfdfndf ovfr lodblf informbtion in tif lodbtor.
    privbtf InputMftiodLodbtor inputMftiodLodbtor;
    privbtf InputMftiod inputMftiod;
    privbtf boolfbn inputMftiodCrfbtionFbilfd;

    // iolding bin for prfviously usfd input mftiod instbndfs, but not tif durrfnt onf
    privbtf HbsiMbp<InputMftiodLodbtor, InputMftiod> usfdInputMftiods;

    // tif durrfnt dlifnt domponfnt is kfpt until tif usfr fodussfs on b difffrfnt
    // dlifnt domponfnt sfrvfd by tif sbmf input dontfxt. Wifn tibt ibppfns, wf dbll
    // fndComposition so tibt tfxt dofsn't jump from onf domponfnt to bnotifr.
    privbtf Componfnt durrfntClifntComponfnt;
    privbtf Componfnt bwtFodussfdComponfnt;
    privbtf boolfbn   isInputMftiodAdtivf;
    privbtf Subsft[]  dibrbdtfrSubsfts = null;

    // truf if domposition brfb ibs bffn sft to invisiblf wifn fodus wbs lost
    privbtf boolfbn dompositionArfbHiddfn = fblsf;

    // Tif input dontfxt for wiosf input mftiod wf mby ibvf to dbll iidfWindows
    privbtf stbtid InputContfxt inputMftiodWindowContfxt;

    // Prfviously bdtivf input mftiod to dfdidf wiftifr wf nffd to dbll
    // InputMftiodAdbptfr.stopListfning() on bdtivbtfInputMftiod()
    privbtf stbtid InputMftiod prfviousInputMftiod = null;

    // truf if tif durrfnt input mftiod rfquirfs dlifnt window dibngf notifidbtion
    privbtf boolfbn dlifntWindowNotifidbtionEnbblfd = fblsf;
    // dlifnt window to wiidi tiis input dontfxt is listfning
    privbtf Window dlifntWindowListfnfd;
    // dbdif lodbtion notifidbtion
    privbtf Rfdtbnglf dlifntWindowLodbtion = null;
    // iolding tif stbtf of dlifntWindowNotifidbtionEnbblfd of only non-durrfnt input mftiods
    privbtf HbsiMbp<InputMftiod, Boolfbn> pfrInputMftiodStbtf;

    // Input Mftiod sflfdtion iot kfy stuff
    privbtf stbtid AWTKfyStrokf inputMftiodSflfdtionKfy;
    privbtf stbtid boolfbn inputMftiodSflfdtionKfyInitiblizfd = fblsf;
    privbtf stbtid finbl String inputMftiodSflfdtionKfyPbti = "/jbvb/bwt/im/sflfdtionKfy";
    privbtf stbtid finbl String inputMftiodSflfdtionKfyCodfNbmf = "kfyCodf";
    privbtf stbtid finbl String inputMftiodSflfdtionKfyModififrsNbmf = "modififrs";

    /**
     * Construdts bn InputContfxt.
     */
    protfdtfd InputContfxt() {
        InputMftiodMbnbgfr imm = InputMftiodMbnbgfr.gftInstbndf();
        syndironizfd (InputContfxt.dlbss) {
            if (!inputMftiodSflfdtionKfyInitiblizfd) {
                inputMftiodSflfdtionKfyInitiblizfd = truf;
                if (imm.ibsMultiplfInputMftiods()) {
                    initiblizfInputMftiodSflfdtionKfy();
                }
            }
        }
        sflfdtInputMftiod(imm.gftDffbultKfybobrdLodblf());
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#sflfdtInputMftiod
     * @fxdfption NullPointfrExdfption wifn tif lodblf is null.
     */
    publid syndironizfd boolfbn sflfdtInputMftiod(Lodblf lodblf) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        // sff wiftifr tif durrfnt input mftiod supports tif lodblf
        if (inputMftiod != null) {
            if (inputMftiod.sftLodblf(lodblf)) {
                rfturn truf;
            }
        } flsf if (inputMftiodLodbtor != null) {
            // Tiis is not 100% dorrfdt, sindf tif input mftiod
            // mby support tif lodblf witiout bdvfrtising it.
            // But bfforf wf try instbntibtions bnd sftLodblf,
            // wf look for bn input mftiod tibt's morf donfidfnt.
            if (inputMftiodLodbtor.isLodblfAvbilbblf(lodblf)) {
                inputMftiodLodbtor = inputMftiodLodbtor.dfrivfLodbtor(lodblf);
                rfturn truf;
            }
        }

        // sff wiftifr tifrf's somf otifr input mftiod tibt supports tif lodblf
        InputMftiodLodbtor nfwLodbtor = InputMftiodMbnbgfr.gftInstbndf().findInputMftiod(lodblf);
        if (nfwLodbtor != null) {
            dibngfInputMftiod(nfwLodbtor);
            rfturn truf;
        }

        // mbkf onf lbst dfspfrbtf fffort witi tif durrfnt input mftiod
        // ??? is tiis good? Tiis is prftty iigi dost for somftiing tibt's likfly to fbil.
        if (inputMftiod == null && inputMftiodLodbtor != null) {
            inputMftiod = gftInputMftiod();
            if (inputMftiod != null) {
                rfturn inputMftiod.sftLodblf(lodblf);
            }
        }
        rfturn fblsf;
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#gftLodblf
     */
    publid Lodblf gftLodblf() {
        if (inputMftiod != null) {
            rfturn inputMftiod.gftLodblf();
        } flsf if (inputMftiodLodbtor != null) {
            rfturn inputMftiodLodbtor.gftLodblf();
        } flsf {
            rfturn null;
        }
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#sftCibrbdtfrSubsfts
     */
    publid void sftCibrbdtfrSubsfts(Subsft[] subsfts) {
        if (subsfts == null) {
            dibrbdtfrSubsfts = null;
        } flsf {
            dibrbdtfrSubsfts = nfw Subsft[subsfts.lfngti];
            Systfm.brrbydopy(subsfts, 0,
                             dibrbdtfrSubsfts, 0, dibrbdtfrSubsfts.lfngti);
        }
        if (inputMftiod != null) {
            inputMftiod.sftCibrbdtfrSubsfts(subsfts);
        }
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#rfdonvfrt
     * @sindf 1.3
     * @fxdfption UnsupportfdOpfrbtionExdfption wifn input mftiod is null
     */
    publid syndironizfd void rfdonvfrt() {
        InputMftiod inputMftiod = gftInputMftiod();
        if (inputMftiod == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        inputMftiod.rfdonvfrt();
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#dispbtdiEvfnt
     */
    @SupprfssWbrnings("fblltirougi")
    publid void dispbtdiEvfnt(AWTEvfnt fvfnt) {

        if (fvfnt instbndfof InputMftiodEvfnt) {
            rfturn;
        }

        // Ignorf fodus fvfnts tibt rflbtf to tif InputMftiodWindow of tiis dontfxt.
        // Tiis is b workbround.  Siould bf rfmovfd bftfr 4452384 is fixfd.
        if (fvfnt instbndfof FodusEvfnt) {
            Componfnt oppositf = ((FodusEvfnt)fvfnt).gftOppositfComponfnt();
            if ((oppositf != null) &&
                (gftComponfntWindow(oppositf) instbndfof InputMftiodWindow) &&
                (oppositf.gftInputContfxt() == tiis)) {
                rfturn;
            }
        }

        InputMftiod inputMftiod = gftInputMftiod();
        int id = fvfnt.gftID();

        switdi (id) {
        dbsf FodusEvfnt.FOCUS_GAINED:
            fodusGbinfd((Componfnt) fvfnt.gftSourdf());
            brfbk;

        dbsf FodusEvfnt.FOCUS_LOST:
            fodusLost((Componfnt) fvfnt.gftSourdf(), ((FodusEvfnt) fvfnt).isTfmporbry());
            brfbk;

        dbsf KfyEvfnt.KEY_PRESSED:
            if (difdkInputMftiodSflfdtionKfy((KfyEvfnt)fvfnt)) {
                // pop up tif input mftiod sflfdtion mfnu
                InputMftiodMbnbgfr.gftInstbndf().notifyCibngfRfqufstByHotKfy((Componfnt)fvfnt.gftSourdf());
                brfbk;
            }

            // fbll tirougi

        dffbult:
            if ((inputMftiod != null) && (fvfnt instbndfof InputEvfnt)) {
                inputMftiod.dispbtdiEvfnt(fvfnt);
            }
        }
    }

    /**
     * Hbndlfs fodus gbinfd fvfnts for bny domponfnt tibt's using
     * tiis input dontfxt.
     * Tifsf fvfnts brf gfnfrbtfd by AWT wifn tif kfybobrd fodus
     * movfs to b domponfnt.
     * Bfsidfs bdtubl dlifnt domponfnts, tif sourdf domponfnts
     * mby blso bf tif domposition brfb or bny domponfnt in bn
     * input mftiod window.
     * <p>
     * Wifn ibndling tif fodus fvfnt for b dlifnt domponfnt, tiis
     * mftiod difdks wiftifr tif input dontfxt wbs prfviously
     * bdtivf for b difffrfnt dlifnt domponfnt, bnd if so, dblls
     * fndComposition for tif prfvious dlifnt domponfnt.
     *
     * @pbrbm sourdf tif domponfnt gbining tif fodus
     */
    privbtf void fodusGbinfd(Componfnt sourdf) {

        /*
         * NOTE: Wifn b Contbinfr is rfmoving its Componfnt wiidi
         * invokfs tiis.rfmovfNotify(), tif Contbinfr ibs tif globbl
         * Componfnt lodk. It is possiblf to ibppfn tibt bn
         * bpplidbtion tirfbd is dblling tiis.rfmovfNotify() wiilf bn
         * AWT fvfnt qufuf tirfbd is dispbtdiing b fodus fvfnt vib
         * tiis.dispbtdiEvfnt(). If bn input mftiod usfs AWT
         * domponfnts (f.g., IIIMP stbtus window), it dbusfs dfbdlodk,
         * for fxbmplf, Componfnt.siow()/iidf() in tiis situbtion
         * bfdbusf iidf/siow trifd to obtbin tif lodk.  Tifrfforf,
         * it's nfdfssbry to obtbin tif globbl Componfnt lodk bfforf
         * bdtivbting or dfbdtivbting bn input mftiod.
         */
        syndironizfd (sourdf.gftTrffLodk()) {
            syndironizfd (tiis) {
                if ("sun.bwt.im.CompositionArfb".fqubls(sourdf.gftClbss().gftNbmf())) {
                    // no spfdibl ibndling for tiis onf
                } flsf if (gftComponfntWindow(sourdf) instbndfof InputMftiodWindow) {
                    // no spfdibl ibndling for tiis onf fitifr
                } flsf {
                    if (!sourdf.isDisplbybblf()) {
                        // Componfnt is bfing disposfd
                        rfturn;
                    }

                    // Fodus wfnt to b rfbl dlifnt domponfnt.
                    // Cifdk wiftifr wf'rf switdiing bftwffn dlifnt domponfnts
                    // tibt sibrf bn input dontfxt. Wf dbn't do tibt fbrlifr
                    // tibn ifrf bfdbusf wf don't wbnt to fnd domposition
                    // until wf rfblly know wf'rf switdiing to b difffrfnt domponfnt
                    if (inputMftiod != null) {
                        if (durrfntClifntComponfnt != null && durrfntClifntComponfnt != sourdf) {
                            if (!isInputMftiodAdtivf) {
                                bdtivbtfInputMftiod(fblsf);
                            }
                            fndComposition();
                            dfbdtivbtfInputMftiod(fblsf);
                        }
                    }

                    durrfntClifntComponfnt = sourdf;
                }

                bwtFodussfdComponfnt = sourdf;
                if (inputMftiod instbndfof InputMftiodAdbptfr) {
                    ((InputMftiodAdbptfr) inputMftiod).sftAWTFodussfdComponfnt(sourdf);
                }

                // it's possiblf tibt tif input mftiod is still bdtivf bfdbusf
                // wf supprfssfd b dfbdtivbtf dbusf by bn input mftiod window
                // doming up
                if (!isInputMftiodAdtivf) {
                    bdtivbtfInputMftiod(truf);
                }


                // If tif dlifnt domponfnt is bn bdtivf dlifnt witi tif bflow-tif-spot
                // input stylf, tifn mbkf tif domposition window undfdorbtfd witiout b titlf bbr.
                InputMftiodContfxt inputContfxt = ((InputMftiodContfxt)tiis);
                if (!inputContfxt.isCompositionArfbVisiblf()) {
                      InputMftiodRfqufsts rfq = sourdf.gftInputMftiodRfqufsts();
                      if (rfq != null && inputContfxt.usfBflowTifSpotInput()) {
                          inputContfxt.sftCompositionArfbUndfdorbtfd(truf);
                      } flsf {
                          inputContfxt.sftCompositionArfbUndfdorbtfd(fblsf);
                      }
                }
                // rfstorfs tif domposition brfb if it wbs sft to invisiblf
                // wifn fodus got lost
                if (dompositionArfbHiddfn == truf) {
                    ((InputMftiodContfxt)tiis).sftCompositionArfbVisiblf(truf);
                    dompositionArfbHiddfn = fblsf;
                }
            }
        }
    }

    /**
     * Adtivbtfs tif durrfnt input mftiod of tiis input dontfxt, bnd grbbs
     * tif domposition brfb for usf by tiis input dontfxt.
     * If updbtfCompositionArfb is truf, tif tfxt in tif domposition brfb
     * is updbtfd (sft to fblsf if tif tfxt is going to dibngf immfdibtfly
     * to bvoid sdrffn flidkfr).
     */
    privbtf void bdtivbtfInputMftiod(boolfbn updbtfCompositionArfb) {
        // dbll iidfWindows() if tiis input dontfxt usfs b difffrfnt
        // input mftiod tibn tif prfviously bdtivbtfd onf
        if (inputMftiodWindowContfxt != null && inputMftiodWindowContfxt != tiis &&
                inputMftiodWindowContfxt.inputMftiodLodbtor != null &&
                !inputMftiodWindowContfxt.inputMftiodLodbtor.sbmfInputMftiod(inputMftiodLodbtor) &&
                inputMftiodWindowContfxt.inputMftiod != null) {
            inputMftiodWindowContfxt.inputMftiod.iidfWindows();
        }
        inputMftiodWindowContfxt = tiis;

        if (inputMftiod != null) {
            if (prfviousInputMftiod != inputMftiod &&
                    prfviousInputMftiod instbndfof InputMftiodAdbptfr) {
                // lft tif iost bdbptfr pbss tirougi tif input fvfnts for tif
                // nfw input mftiod
                ((InputMftiodAdbptfr) prfviousInputMftiod).stopListfning();
            }
            prfviousInputMftiod = null;

            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("Currfnt dlifnt domponfnt " + durrfntClifntComponfnt);
            }
            if (inputMftiod instbndfof InputMftiodAdbptfr) {
                ((InputMftiodAdbptfr) inputMftiod).sftClifntComponfnt(durrfntClifntComponfnt);
            }
            inputMftiod.bdtivbtf();
            isInputMftiodAdtivf = truf;

            if (pfrInputMftiodStbtf != null) {
                Boolfbn stbtf = pfrInputMftiodStbtf.rfmovf(inputMftiod);
                if (stbtf != null) {
                    dlifntWindowNotifidbtionEnbblfd = stbtf.boolfbnVbluf();
                }
            }
            if (dlifntWindowNotifidbtionEnbblfd) {
                if (!bddfdClifntWindowListfnfrs()) {
                    bddClifntWindowListfnfrs();
                }
                syndironizfd(tiis) {
                    if (dlifntWindowListfnfd != null) {
                        notifyClifntWindowCibngf(dlifntWindowListfnfd);
                    }
                }
            } flsf {
                if (bddfdClifntWindowListfnfrs()) {
                    rfmovfClifntWindowListfnfrs();
                }
            }
        }
        InputMftiodMbnbgfr.gftInstbndf().sftInputContfxt(tiis);

        ((InputMftiodContfxt) tiis).grbbCompositionArfb(updbtfCompositionArfb);
    }

    stbtid Window gftComponfntWindow(Componfnt domponfnt) {
        wiilf (truf) {
            if (domponfnt == null) {
                rfturn null;
            } flsf if (domponfnt instbndfof Window) {
                rfturn (Window) domponfnt;
            } flsf {
                domponfnt = domponfnt.gftPbrfnt();
            }
        }
    }

    /**
     * Hbndlfs fodus lost fvfnts for bny domponfnt tibt's using
     * tiis input dontfxt.
     * Tifsf fvfnts brf gfnfrbtfd by AWT wifn tif kfybobrd fodus
     * movfs bwby from b domponfnt.
     * Bfsidfs bdtubl dlifnt domponfnts, tif sourdf domponfnts
     * mby blso bf tif domposition brfb or bny domponfnt in bn
     * input mftiod window.
     *
     * @pbrbm sourdf tif domponfnt losing tif fodus
     * @isTfmporbry wiftifr tif fodus dibngf is tfmporbry
     */
    privbtf void fodusLost(Componfnt sourdf, boolfbn isTfmporbry) {

        // sff tif notf on syndironizbtion in fodusGbinfd
        syndironizfd (sourdf.gftTrffLodk()) {
            syndironizfd (tiis) {

                // Wf nffd to supprfss dfbdtivbtion if rfmovfNotify ibs bffn dbllfd fbrlifr.
                // Tiis is indidbtfd by isInputMftiodAdtivf == fblsf.
                if (isInputMftiodAdtivf) {
                    dfbdtivbtfInputMftiod(isTfmporbry);
                }

                bwtFodussfdComponfnt = null;
                if (inputMftiod instbndfof InputMftiodAdbptfr) {
                    ((InputMftiodAdbptfr) inputMftiod).sftAWTFodussfdComponfnt(null);
                }

                // iidfs tif domposition brfb if durrfntly it is visiblf
                InputMftiodContfxt inputContfxt = ((InputMftiodContfxt)tiis);
                if (inputContfxt.isCompositionArfbVisiblf()) {
                    inputContfxt.sftCompositionArfbVisiblf(fblsf);
                    dompositionArfbHiddfn = truf;
                }
            }
        }
    }

    /**
     * Cifdks tif kfy fvfnt is tif input mftiod sflfdtion kfy or not.
     */
    privbtf boolfbn difdkInputMftiodSflfdtionKfy(KfyEvfnt fvfnt) {
        if (inputMftiodSflfdtionKfy != null) {
            AWTKfyStrokf bKfyStrokf = AWTKfyStrokf.gftAWTKfyStrokfForEvfnt(fvfnt);
            rfturn inputMftiodSflfdtionKfy.fqubls(bKfyStrokf);
        } flsf {
            rfturn fblsf;
        }
    }

    privbtf void dfbdtivbtfInputMftiod(boolfbn isTfmporbry) {
        InputMftiodMbnbgfr.gftInstbndf().sftInputContfxt(null);
        if (inputMftiod != null) {
            isInputMftiodAdtivf = fblsf;
            inputMftiod.dfbdtivbtf(isTfmporbry);
            prfviousInputMftiod = inputMftiod;
        }
    }

    /**
     * Switdifs from tif durrfnt input mftiod to tif onf dfsdribfd by nfwLodbtor.
     * Tif durrfnt input mftiod, if bny, is bskfd to fnd domposition, dfbdtivbtfd,
     * bnd sbvfd for futurf usf. Tif nfwLodbtor is mbdf tif durrfnt lodbtor. If
     * tif input dontfxt is bdtivf, bn input mftiod instbndf for tif nfw lodbtor
     * is obtbinfd; otifrwisf tiis is dfffrrfd until rfquirfd.
     */
    syndironizfd void dibngfInputMftiod(InputMftiodLodbtor nfwLodbtor) {
        // If wf don't ibvf b lodbtor yft, tiis must bf b nfw input dontfxt.
        // If wf drfbtfd b nfw input mftiod ifrf, wf migit gft into bn
        // infinitf loop: drfbtf input mftiod -> drfbtf somf input mftiod window ->
        // drfbtf nfw input dontfxt -> bdd input dontfxt to input mftiod mbnbgfr's dontfxt list ->
        // dbll dibngfInputMftiod on it.
        // So, just rfdord tif lodbtor. dispbtdiEvfnt will drfbtf tif input mftiod wifn nffdfd.
        if (inputMftiodLodbtor == null) {
            inputMftiodLodbtor = nfwLodbtor;
            inputMftiodCrfbtionFbilfd = fblsf;
            rfturn;
        }

        // If tif sbmf input mftiod is spfdififd, just kffp it.
        // Adjust tif lodblf if nfdfssbry.
        if (inputMftiodLodbtor.sbmfInputMftiod(nfwLodbtor)) {
            Lodblf nfwLodblf = nfwLodbtor.gftLodblf();
            if (nfwLodblf != null && inputMftiodLodbtor.gftLodblf() != nfwLodblf) {
                if (inputMftiod != null) {
                    inputMftiod.sftLodblf(nfwLodblf);
                }
                inputMftiodLodbtor = nfwLodbtor;
            }
            rfturn;
        }

        // Switdi out tif old input mftiod
        Lodblf sbvfdLodblf = inputMftiodLodbtor.gftLodblf();
        boolfbn wbsInputMftiodAdtivf = isInputMftiodAdtivf;
        boolfbn wbsCompositionEnbblfdSupportfd = fblsf;
        boolfbn wbsCompositionEnbblfd = fblsf;
        if (inputMftiod != null) {
            try {
                wbsCompositionEnbblfd = inputMftiod.isCompositionEnbblfd();
                wbsCompositionEnbblfdSupportfd = truf;
            } dbtdi (UnsupportfdOpfrbtionExdfption f) { }

            if (durrfntClifntComponfnt != null) {
                if (!isInputMftiodAdtivf) {
                    bdtivbtfInputMftiod(fblsf);
                }
                fndComposition();
                dfbdtivbtfInputMftiod(fblsf);
                if (inputMftiod instbndfof InputMftiodAdbptfr) {
                    ((InputMftiodAdbptfr) inputMftiod).sftClifntComponfnt(null);
                }
            }
            sbvfdLodblf = inputMftiod.gftLodblf();

            // kffp tif input mftiod instbndf bround for futurf usf
            if (usfdInputMftiods == null) {
                usfdInputMftiods = nfw HbsiMbp<>(5);
            }
            if (pfrInputMftiodStbtf == null) {
                pfrInputMftiodStbtf = nfw HbsiMbp<>(5);
            }
            usfdInputMftiods.put(inputMftiodLodbtor.dfrivfLodbtor(null), inputMftiod);
            pfrInputMftiodStbtf.put(inputMftiod,
                                    Boolfbn.vblufOf(dlifntWindowNotifidbtionEnbblfd));
            fnbblfClifntWindowNotifidbtion(inputMftiod, fblsf);
            if (tiis == inputMftiodWindowContfxt) {
                inputMftiod.iidfWindows();
                inputMftiodWindowContfxt = null;
            }
            inputMftiodLodbtor = null;
            inputMftiod = null;
            inputMftiodCrfbtionFbilfd = fblsf;
        }

        // Switdi in tif nfw input mftiod
        if (nfwLodbtor.gftLodblf() == null && sbvfdLodblf != null &&
                nfwLodbtor.isLodblfAvbilbblf(sbvfdLodblf)) {
            nfwLodbtor = nfwLodbtor.dfrivfLodbtor(sbvfdLodblf);
        }
        inputMftiodLodbtor = nfwLodbtor;
        inputMftiodCrfbtionFbilfd = fblsf;

        // bdtivbtf tif nfw input mftiod if tif old onf wbs bdtivf
        if (wbsInputMftiodAdtivf) {
            inputMftiod = gftInputMftiodInstbndf();
            if (inputMftiod instbndfof InputMftiodAdbptfr) {
                ((InputMftiodAdbptfr) inputMftiod).sftAWTFodussfdComponfnt(bwtFodussfdComponfnt);
            }
            bdtivbtfInputMftiod(truf);
        }

        // fnbblf/disbblf domposition if tif old onf supports qufrying fnbblf/disbblf
        if (wbsCompositionEnbblfdSupportfd) {
            inputMftiod = gftInputMftiod();
            if (inputMftiod != null) {
                try {
                    inputMftiod.sftCompositionEnbblfd(wbsCompositionEnbblfd);
                } dbtdi (UnsupportfdOpfrbtionExdfption f) { }
            }
        }
    }

    /**
     * Rfturns tif dlifnt domponfnt.
     */
    Componfnt gftClifntComponfnt() {
        rfturn durrfntClifntComponfnt;
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#rfmovfNotify
     * @fxdfption NullPointfrExdfption wifn tif domponfnt is null.
     */
    publid syndironizfd void rfmovfNotify(Componfnt domponfnt) {
        if (domponfnt == null) {
            tirow nfw NullPointfrExdfption();
        }

        if (inputMftiod == null) {
            if (domponfnt == durrfntClifntComponfnt) {
                durrfntClifntComponfnt = null;
            }
            rfturn;
        }

        // Wf mby or mby not gft b FOCUS_LOST fvfnt for tiis domponfnt,
        // so do tif dfbdtivbtion stuff ifrf too.
        if (domponfnt == bwtFodussfdComponfnt) {
            fodusLost(domponfnt, fblsf);
        }

        if (domponfnt == durrfntClifntComponfnt) {
            if (isInputMftiodAdtivf) {
                // domponfnt wbsn't tif onf tibt ibd tif fodus
                dfbdtivbtfInputMftiod(fblsf);
            }
            inputMftiod.rfmovfNotify();
            if (dlifntWindowNotifidbtionEnbblfd && bddfdClifntWindowListfnfrs()) {
                rfmovfClifntWindowListfnfrs();
            }
            durrfntClifntComponfnt = null;
            if (inputMftiod instbndfof InputMftiodAdbptfr) {
                ((InputMftiodAdbptfr) inputMftiod).sftClifntComponfnt(null);
            }

            // rfmovfNotify() dbn bf issufd from b tirfbd otifr tibn tif fvfnt dispbtdi
            // tirfbd.  In tibt dbsf, bvoid possiblf dfbdlodk bftwffn Componfnt.AWTTrffLodk
            // bnd InputMftiodContfxt.dompositionArfbHbndlfrLodk by rflfbsing tif domposition
            // brfb on tif fvfnt dispbtdi tirfbd.
            if (EvfntQufuf.isDispbtdiTirfbd()) {
                ((InputMftiodContfxt)tiis).rflfbsfCompositionArfb();
            } flsf {
                EvfntQufuf.invokfLbtfr(nfw Runnbblf() {
                    publid void run() {
                        ((InputMftiodContfxt)InputContfxt.tiis).rflfbsfCompositionArfb();
                    }
                });
            }
        }
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#disposf
     * @fxdfption IllfgblStbtfExdfption wifn tif durrfntClifntComponfnt is not null
     */
    publid syndironizfd void disposf() {
        if (durrfntClifntComponfnt != null) {
            tirow nfw IllfgblStbtfExdfption("Cbn't disposf InputContfxt wiilf it's bdtivf");
        }
        if (inputMftiod != null) {
            if (tiis == inputMftiodWindowContfxt) {
                inputMftiod.iidfWindows();
                inputMftiodWindowContfxt = null;
            }
            if (inputMftiod == prfviousInputMftiod) {
                prfviousInputMftiod = null;
            }
            if (dlifntWindowNotifidbtionEnbblfd) {
                if (bddfdClifntWindowListfnfrs()) {
                    rfmovfClifntWindowListfnfrs();
                }
                dlifntWindowNotifidbtionEnbblfd = fblsf;
            }
            inputMftiod.disposf();

            // in dbsf tif input mftiod fnbblfd tif dlifnt window
            // notifidbtion in disposf(), wiidi siouldn't ibppfn, it
            // nffds to bf dlfbnfd up bgbin.
            if (dlifntWindowNotifidbtionEnbblfd) {
                fnbblfClifntWindowNotifidbtion(inputMftiod, fblsf);
            }

            inputMftiod = null;
        }
        inputMftiodLodbtor = null;
        if (usfdInputMftiods != null && !usfdInputMftiods.isEmpty()) {
            Itfrbtor<InputMftiod> itfrbtor = usfdInputMftiods.vblufs().itfrbtor();
            usfdInputMftiods = null;
            wiilf (itfrbtor.ibsNfxt()) {
                itfrbtor.nfxt().disposf();
            }
        }

        // dlfbnup dlifnt window notifidbtion vbribblfs
        dlifntWindowNotifidbtionEnbblfd = fblsf;
        dlifntWindowListfnfd = null;
        pfrInputMftiodStbtf = null;
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#gftInputMftiodControlObjfdt
     */
    publid syndironizfd Objfdt gftInputMftiodControlObjfdt() {
        InputMftiod inputMftiod = gftInputMftiod();

        if (inputMftiod != null) {
            rfturn inputMftiod.gftControlObjfdt();
        } flsf {
            rfturn null;
        }
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#sftCompositionEnbblfd(boolfbn)
     * @fxdfption UnsupportfdOpfrbtionExdfption wifn input mftiod is null
     */
    publid void sftCompositionEnbblfd(boolfbn fnbblf) {
        InputMftiod inputMftiod = gftInputMftiod();

        if (inputMftiod == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        inputMftiod.sftCompositionEnbblfd(fnbblf);
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#isCompositionEnbblfd
     * @fxdfption UnsupportfdOpfrbtionExdfption wifn input mftiod is null
     */
    publid boolfbn isCompositionEnbblfd() {
        InputMftiod inputMftiod = gftInputMftiod();

        if (inputMftiod == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        rfturn inputMftiod.isCompositionEnbblfd();
    }

    /**
     * @rfturn b string witi informbtion bbout tif durrfnt input mftiod.
     * @fxdfption UnsupportfdOpfrbtionExdfption wifn input mftiod is null
     */
    publid String gftInputMftiodInfo() {
        InputMftiod inputMftiod = gftInputMftiod();

        if (inputMftiod == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Null input mftiod");
        }

        String inputMftiodInfo = null;
        if (inputMftiod instbndfof InputMftiodAdbptfr) {
            // rfturns tif informbtion bbout tif iost nbtivf input mftiod.
            inputMftiodInfo = ((InputMftiodAdbptfr)inputMftiod).
                gftNbtivfInputMftiodInfo();
        }

        // fxtrbdts tif informbtion from tif InputMftiodDfsdriptor
        // bssodibtfd witi tif durrfnt jbvb input mftiod.
        if (inputMftiodInfo == null && inputMftiodLodbtor != null) {
            inputMftiodInfo = inputMftiodLodbtor.gftDfsdriptor().
                gftInputMftiodDisplbyNbmf(gftLodblf(), SunToolkit.
                                          gftStbrtupLodblf());
        }

        if (inputMftiodInfo != null && !inputMftiodInfo.fqubls("")) {
            rfturn inputMftiodInfo;
        }

        // do our bfst to rfturn somftiing usfful.
        rfturn inputMftiod.toString() + "-" + inputMftiod.gftLodblf().toString();
    }

    /**
     * Turns off tif nbtivf IM. Tif nbtivf IM is dibblfd wifn
     * tif dfbdtivf mftiod of InputMftiod is dbllfd. It is
     * dflbyfd until tif bdtivf mftiod is dbllfd on b difffrfnt
     * pffr domponfnt. Tiis mftiod is providfd to fxpliditly disbblf
     * tif nbtivf IM.
     */
    publid void disbblfNbtivfIM() {
        InputMftiod inputMftiod = gftInputMftiod();
        if (inputMftiod != null && inputMftiod instbndfof InputMftiodAdbptfr) {
            ((InputMftiodAdbptfr)inputMftiod).stopListfning();
        }
    }


    privbtf syndironizfd InputMftiod gftInputMftiod() {
        if (inputMftiod != null) {
            rfturn inputMftiod;
        }

        if (inputMftiodCrfbtionFbilfd) {
            rfturn null;
        }

        inputMftiod = gftInputMftiodInstbndf();
        rfturn inputMftiod;
    }

    /**
     * Rfturns bn instbndf of tif input mftiod dfsdribfd by
     * tif durrfnt input mftiod lodbtor. Tiis mby bf bn input
     * mftiod tibt wbs prfviously usfd bnd switdifd out of,
     * or b nfw instbndf. Tif lodblf, dibrbdtfr subsfts, bnd
     * input mftiod dontfxt of tif input mftiod brf sft.
     *
     * Tif inputMftiodCrfbtionFbilfd fifld is sft to truf if tif
     * instbntibtion fbilfd.
     *
     * @rfturn bn InputMftiod instbndf
     * @sff jbvb.bwt.im.spi.InputMftiod#sftInputMftiodContfxt
     * @sff jbvb.bwt.im.spi.InputMftiod#sftLodblf
     * @sff jbvb.bwt.im.spi.InputMftiod#sftCibrbdtfrSubsfts
     */
    privbtf InputMftiod gftInputMftiodInstbndf() {
        InputMftiodLodbtor lodbtor = inputMftiodLodbtor;
        if (lodbtor == null) {
            inputMftiodCrfbtionFbilfd = truf;
            rfturn null;
        }

        Lodblf lodblf = lodbtor.gftLodblf();
        InputMftiod inputMftiodInstbndf = null;

        // sff wiftifr wf ibvf b prfviously usfd input mftiod
        if (usfdInputMftiods != null) {
            inputMftiodInstbndf = usfdInputMftiods.rfmovf(lodbtor.dfrivfLodbtor(null));
            if (inputMftiodInstbndf != null) {
                if (lodblf != null) {
                    inputMftiodInstbndf.sftLodblf(lodblf);
                }
                inputMftiodInstbndf.sftCibrbdtfrSubsfts(dibrbdtfrSubsfts);
                Boolfbn stbtf = pfrInputMftiodStbtf.rfmovf(inputMftiodInstbndf);
                if (stbtf != null) {
                    fnbblfClifntWindowNotifidbtion(inputMftiodInstbndf, stbtf.boolfbnVbluf());
                }
                ((InputMftiodContfxt) tiis).sftInputMftiodSupportsBflowTifSpot(
                        (!(inputMftiodInstbndf instbndfof InputMftiodAdbptfr)) ||
                        ((InputMftiodAdbptfr) inputMftiodInstbndf).supportsBflowTifSpot());
                rfturn inputMftiodInstbndf;
            }
        }

        // nffd to drfbtf nfw instbndf
        try {
            inputMftiodInstbndf = lodbtor.gftDfsdriptor().drfbtfInputMftiod();

            if (lodblf != null) {
                inputMftiodInstbndf.sftLodblf(lodblf);
            }
            inputMftiodInstbndf.sftInputMftiodContfxt((InputMftiodContfxt) tiis);
            inputMftiodInstbndf.sftCibrbdtfrSubsfts(dibrbdtfrSubsfts);

        } dbtdi (Exdfption f) {
            logCrfbtionFbilfd(f);

            // tifrf brf b numbfr of bbd tiings tibt dbn ibppfn wiilf drfbting
            // tif input mftiod. In bny dbsf, wf just dontinuf witiout bn
            // input mftiod.
            inputMftiodCrfbtionFbilfd = truf;

            // if tif instbndf ibs bffn drfbtfd, tifn it mfbns fitifr
            // sftLodblf() or sftInputMftiodContfxt() fbilfd.
            if (inputMftiodInstbndf != null) {
                inputMftiodInstbndf = null;
            }
        } dbtdi (LinkbgfError f) {
            logCrfbtionFbilfd(f);

            // sbmf bs bbovf
            inputMftiodCrfbtionFbilfd = truf;
        }
        ((InputMftiodContfxt) tiis).sftInputMftiodSupportsBflowTifSpot(
                (!(inputMftiodInstbndf instbndfof InputMftiodAdbptfr)) ||
                ((InputMftiodAdbptfr) inputMftiodInstbndf).supportsBflowTifSpot());
        rfturn inputMftiodInstbndf;
    }

    privbtf void logCrfbtionFbilfd(Tirowbblf tirowbblf) {
        PlbtformLoggfr loggfr = PlbtformLoggfr.gftLoggfr("sun.bwt.im");
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.CONFIG)) {
            String frrorTfxtFormbt = Toolkit.gftPropfrty("AWT.InputMftiodCrfbtionFbilfd",
                                                         "Could not drfbtf {0}. Rfbson: {1}");
            Objfdt[] brgs =
                {inputMftiodLodbtor.gftDfsdriptor().gftInputMftiodDisplbyNbmf(null, Lodblf.gftDffbult()),
                 tirowbblf.gftLodblizfdMfssbgf()};
            MfssbgfFormbt mf = nfw MfssbgfFormbt(frrorTfxtFormbt);
            loggfr.donfig(mf.formbt(brgs));
        }
    }

    InputMftiodLodbtor gftInputMftiodLodbtor() {
        if (inputMftiod != null) {
            rfturn inputMftiodLodbtor.dfrivfLodbtor(inputMftiod.gftLodblf());
        }
        rfturn inputMftiodLodbtor;
    }

    /**
     * @sff jbvb.bwt.im.InputContfxt#fndComposition
     */
    publid syndironizfd void fndComposition() {
        if (inputMftiod != null) {
            inputMftiod.fndComposition();
        }
    }

    /**
     * @sff jbvb.bwt.im.spi.InputMftiodContfxt#fnbblfClifntWindowNotifidbtion
     */
    syndironizfd void fnbblfClifntWindowNotifidbtion(InputMftiod rfqufstfr,
                                                     boolfbn fnbblf) {
        // in dbsf tiis rfqufst is not from tif durrfnt input mftiod,
        // storf tif rfqufst bnd ibndlf it wifn tiis rfqufsting input
        // mftiod bfdomfs tif durrfnt onf.
        if (rfqufstfr != inputMftiod) {
            if (pfrInputMftiodStbtf == null) {
                pfrInputMftiodStbtf = nfw HbsiMbp<>(5);
            }
            pfrInputMftiodStbtf.put(rfqufstfr, Boolfbn.vblufOf(fnbblf));
            rfturn;
        }

        if (dlifntWindowNotifidbtionEnbblfd != fnbblf) {
            dlifntWindowLodbtion = null;
            dlifntWindowNotifidbtionEnbblfd = fnbblf;
        }
        if (dlifntWindowNotifidbtionEnbblfd) {
            if (!bddfdClifntWindowListfnfrs()) {
                bddClifntWindowListfnfrs();
            }
            if (dlifntWindowListfnfd != null) {
                dlifntWindowLodbtion = null;
                notifyClifntWindowCibngf(dlifntWindowListfnfd);
            }
        } flsf {
            if (bddfdClifntWindowListfnfrs()) {
                rfmovfClifntWindowListfnfrs();
            }
        }
    }

    privbtf syndironizfd void notifyClifntWindowCibngf(Window window) {
        if (inputMftiod == null) {
            rfturn;
        }

        // if tif window is invisiblf or idonififd, sfnd null to tif input mftiod.
        if (!window.isVisiblf() ||
            ((window instbndfof Frbmf) && ((Frbmf)window).gftStbtf() == Frbmf.ICONIFIED)) {
            dlifntWindowLodbtion = null;
            inputMftiod.notifyClifntWindowCibngf(null);
            rfturn;
        }
        Rfdtbnglf lodbtion = window.gftBounds();
        if (dlifntWindowLodbtion == null || !dlifntWindowLodbtion.fqubls(lodbtion)) {
            dlifntWindowLodbtion = lodbtion;
            inputMftiod.notifyClifntWindowCibngf(dlifntWindowLodbtion);
        }
    }

    privbtf syndironizfd void bddClifntWindowListfnfrs() {
        Componfnt dlifnt = gftClifntComponfnt();
        if (dlifnt == null) {
            rfturn;
        }
        Window window = gftComponfntWindow(dlifnt);
        if (window == null) {
            rfturn;
        }
        window.bddComponfntListfnfr(tiis);
        window.bddWindowListfnfr(tiis);
        dlifntWindowListfnfd = window;
    }

    privbtf syndironizfd void rfmovfClifntWindowListfnfrs() {
        dlifntWindowListfnfd.rfmovfComponfntListfnfr(tiis);
        dlifntWindowListfnfd.rfmovfWindowListfnfr(tiis);
        dlifntWindowListfnfd = null;
    }

    /**
     * Rfturns truf if listfnfrs ibvf bffn sft up for dlifnt window
     * dibngf notifidbtion.
     */
    privbtf boolfbn bddfdClifntWindowListfnfrs() {
        rfturn dlifntWindowListfnfd != null;
    }

    /*
     * ComponfntListfnfr bnd WindowListfnfr implfmfntbtion
     */
    publid void domponfntRfsizfd(ComponfntEvfnt f) {
        notifyClifntWindowCibngf((Window)f.gftComponfnt());
    }

    publid void domponfntMovfd(ComponfntEvfnt f) {
        notifyClifntWindowCibngf((Window)f.gftComponfnt());
    }

    publid void domponfntSiown(ComponfntEvfnt f) {
        notifyClifntWindowCibngf((Window)f.gftComponfnt());
    }

    publid void domponfntHiddfn(ComponfntEvfnt f) {
        notifyClifntWindowCibngf((Window)f.gftComponfnt());
    }

    publid void windowOpfnfd(WindowEvfnt f) {}
    publid void windowClosing(WindowEvfnt f) {}
    publid void windowClosfd(WindowEvfnt f) {}

    publid void windowIdonififd(WindowEvfnt f) {
        notifyClifntWindowCibngf(f.gftWindow());
    }

    publid void windowDfidonififd(WindowEvfnt f) {
        notifyClifntWindowCibngf(f.gftWindow());
    }

    publid void windowAdtivbtfd(WindowEvfnt f) {}
    publid void windowDfbdtivbtfd(WindowEvfnt f) {}

    /**
     * Initiblizfs tif input mftiod sflfdtion kfy dffinition in prfffrfndf trffs
     */
    privbtf void initiblizfInputMftiodSflfdtionKfy() {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                // Look in usfr's trff
                Prfffrfndfs root = Prfffrfndfs.usfrRoot();
                inputMftiodSflfdtionKfy = gftInputMftiodSflfdtionKfyStrokf(root);

                if (inputMftiodSflfdtionKfy == null) {
                    // Look in systfm's trff
                    root = Prfffrfndfs.systfmRoot();
                    inputMftiodSflfdtionKfy = gftInputMftiodSflfdtionKfyStrokf(root);
                }
                rfturn null;
            }
        });
    }

    privbtf AWTKfyStrokf gftInputMftiodSflfdtionKfyStrokf(Prfffrfndfs root) {
        try {
            if (root.nodfExists(inputMftiodSflfdtionKfyPbti)) {
                Prfffrfndfs nodf = root.nodf(inputMftiodSflfdtionKfyPbti);
                int kfyCodf = nodf.gftInt(inputMftiodSflfdtionKfyCodfNbmf, KfyEvfnt.VK_UNDEFINED);
                if (kfyCodf != KfyEvfnt.VK_UNDEFINED) {
                    int modififrs = nodf.gftInt(inputMftiodSflfdtionKfyModififrsNbmf, 0);
                    rfturn AWTKfyStrokf.gftAWTKfyStrokf(kfyCodf, modififrs);
                }
            }
        } dbtdi (BbdkingStorfExdfption bsf) {
        }

        rfturn null;
    }
}
