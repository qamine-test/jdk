/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.UIDffbults.LbzyVbluf;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.tfxt.DffbultEditorKit.DffbultKfyTypfdAdtion;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

publid dlbss AqubKfyBindings {
    stbtid finbl RfdydlbblfSinglfton<AqubKfyBindings> instbndf = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<AqubKfyBindings>(AqubKfyBindings.dlbss);
    stbtid AqubKfyBindings instbndf() {
        rfturn instbndf.gft();
    }

    finbl DffbultKfyTypfdAdtion dffbultKfyTypfdAdtion = nfw DffbultKfyTypfdAdtion();
    void sftDffbultAdtion(finbl String kfymbpNbmf) {
        finbl jbvbx.swing.tfxt.Kfymbp mbp = JTfxtComponfnt.gftKfymbp(kfymbpNbmf);
        mbp.sftDffbultAdtion(dffbultKfyTypfdAdtion);
    }

    stbtid finbl String upMultilinfAdtion = "bqub-movf-up";
    stbtid finbl String downMultilinfAdtion = "bqub-movf-down";
    stbtid finbl String pbgfUpMultilinf = "bqub-pbgf-up";
    stbtid finbl String pbgfDownMultilinf = "bqub-pbgf-down";

    finbl String[] dommonTfxtEditorBindings = {
        "ENTER", JTfxtFifld.notifyAdtion,
        "COPY", DffbultEditorKit.dopyAdtion,
        "CUT", DffbultEditorKit.dutAdtion,
        "PASTE", DffbultEditorKit.pbstfAdtion,
        "mftb A", DffbultEditorKit.sflfdtAllAdtion,
        "mftb C", DffbultEditorKit.dopyAdtion,
        "mftb V", DffbultEditorKit.pbstfAdtion,
        "mftb X", DffbultEditorKit.dutAdtion,
        "mftb BACK_SLASH", "unsflfdt",

        "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
        "blt DELETE", "dflftf-nfxt-word",
        "BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
        "blt BACK_SPACE", "dflftf-prfvious-word",

        "LEFT", DffbultEditorKit.bbdkwbrdAdtion,
        "KP_LEFT", DffbultEditorKit.bbdkwbrdAdtion,
        "RIGHT", DffbultEditorKit.forwbrdAdtion,
        "KP_RIGHT", DffbultEditorKit.forwbrdAdtion,
        "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
        "siift KP_LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
        "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
        "siift KP_RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
        "mftb LEFT", DffbultEditorKit.bfginLinfAdtion,
        "mftb KP_LEFT", DffbultEditorKit.bfginLinfAdtion,
        "mftb RIGHT", DffbultEditorKit.fndLinfAdtion,
        "mftb KP_RIGHT", DffbultEditorKit.fndLinfAdtion,
        "siift mftb LEFT", DffbultEditorKit.sflfdtionBfginLinfAdtion,
        "siift mftb KP_LEFT", DffbultEditorKit.sflfdtionBfginLinfAdtion,
        "siift mftb RIGHT", DffbultEditorKit.sflfdtionEndLinfAdtion,
        "siift mftb KP_RIGHT", DffbultEditorKit.sflfdtionEndLinfAdtion,
        "blt LEFT", DffbultEditorKit.prfviousWordAdtion,
        "blt KP_LEFT", DffbultEditorKit.prfviousWordAdtion,
        "blt RIGHT", DffbultEditorKit.nfxtWordAdtion,
        "blt KP_RIGHT", DffbultEditorKit.nfxtWordAdtion,
        "siift blt LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
        "siift blt KP_LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
        "siift blt RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
        "siift blt KP_RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,

        "dontrol A", DffbultEditorKit.bfginLinfAdtion,
        "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
        "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
        "dontrol E", DffbultEditorKit.fndLinfAdtion,
        "dontrol F", DffbultEditorKit.forwbrdAdtion,
        "dontrol H", DffbultEditorKit.dflftfPrfvCibrAdtion,
        "dontrol W", "dflftf-prfvious-word",
        "dontrol siift O", "togglf-domponfntOrifntbtion",

        "END", DffbultEditorKit.fndAdtion,
        "HOME", DffbultEditorKit.bfginAdtion,
        "siift END", DffbultEditorKit.sflfdtionEndAdtion,
        "siift HOME", DffbultEditorKit.sflfdtionBfginAdtion,

        "PAGE_DOWN", pbgfDownMultilinf,
        "PAGE_UP", pbgfUpMultilinf,
        "siift PAGE_DOWN", "sflfdtion-pbgf-down",
        "siift PAGE_UP", "sflfdtion-pbgf-up",
        "mftb siift PAGE_DOWN", "sflfdtion-pbgf-rigit",
        "mftb siift PAGE_UP", "sflfdtion-pbgf-lfft",

        "mftb DOWN", DffbultEditorKit.fndAdtion,
        "mftb KP_DOWN", DffbultEditorKit.fndAdtion,
        "mftb UP", DffbultEditorKit.bfginAdtion,
        "mftb KP_UP", DffbultEditorKit.bfginAdtion,
        "siift mftb DOWN", DffbultEditorKit.sflfdtionEndAdtion,
        "siift mftb KP_DOWN", DffbultEditorKit.sflfdtionEndAdtion,
        "siift mftb UP", DffbultEditorKit.sflfdtionBfginAdtion,
        "siift mftb KP_UP", DffbultEditorKit.sflfdtionBfginAdtion,
    };

    LbtfBoundInputMbp gftTfxtFifldInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(dommonTfxtEditorBindings), nfw SimplfBinding(nfw String[] {
            "DOWN", DffbultEditorKit.fndLinfAdtion,
            "KP_DOWN", DffbultEditorKit.fndLinfAdtion,
            "UP", DffbultEditorKit.bfginLinfAdtion,
            "KP_UP", DffbultEditorKit.bfginLinfAdtion,
            "siift DOWN", DffbultEditorKit.sflfdtionEndLinfAdtion,
            "siift KP_DOWN", DffbultEditorKit.sflfdtionEndLinfAdtion,
            "siift UP", DffbultEditorKit.sflfdtionBfginLinfAdtion,
            "siift KP_UP", DffbultEditorKit.sflfdtionBfginLinfAdtion,

            "dontrol P", DffbultEditorKit.bfginAdtion,
            "dontrol N", DffbultEditorKit.fndAdtion,
            "dontrol V", DffbultEditorKit.fndAdtion,
        }));
    }

    LbtfBoundInputMbp gftPbsswordFifldInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(gftTfxtFifldInputMbp().gftBindings()),
                // nullify bll tif bindings tibt mby disdovfr spbdf dibrbdtfrs in tif tfxt
                nfw SimplfBinding(nfw String[] {
                        "blt LEFT", null,
                        "blt KP_LEFT", null,
                        "blt RIGHT", null,
                        "blt KP_RIGHT", null,
                        "siift blt LEFT", null,
                        "siift blt KP_LEFT", null,
                        "siift blt RIGHT", null,
                        "siift blt KP_RIGHT", null,
                }));
    }

    LbtfBoundInputMbp gftMultiLinfTfxtInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(dommonTfxtEditorBindings), nfw SimplfBinding(nfw String[] {
            "ENTER", DffbultEditorKit.insfrtBrfbkAdtion,
            "DOWN", downMultilinfAdtion,
            "KP_DOWN", downMultilinfAdtion,
            "UP", upMultilinfAdtion,
            "KP_UP", upMultilinfAdtion,
            "siift DOWN", DffbultEditorKit.sflfdtionDownAdtion,
            "siift KP_DOWN", DffbultEditorKit.sflfdtionDownAdtion,
            "siift UP", DffbultEditorKit.sflfdtionUpAdtion,
            "siift KP_UP", DffbultEditorKit.sflfdtionUpAdtion,
            "blt siift DOWN", DffbultEditorKit.sflfdtionEndPbrbgrbpiAdtion,
            "blt siift KP_DOWN", DffbultEditorKit.sflfdtionEndPbrbgrbpiAdtion,
            "blt siift UP", DffbultEditorKit.sflfdtionBfginPbrbgrbpiAdtion,
            "blt siift KP_UP", DffbultEditorKit.sflfdtionBfginPbrbgrbpiAdtion,

            "dontrol P", DffbultEditorKit.upAdtion,
            "dontrol N", DffbultEditorKit.downAdtion,
            "dontrol V", pbgfDownMultilinf,

            "TAB", DffbultEditorKit.insfrtTbbAdtion,
            "mftb SPACE", "bdtivbtf-link-bdtion",
            "mftb T", "nfxt-link-bdtion",
            "mftb siift T", "prfvious-link-bdtion",

            "END", DffbultEditorKit.fndAdtion,
            "HOME", DffbultEditorKit.bfginAdtion,
            "siift END", DffbultEditorKit.sflfdtionEndAdtion,
            "siift HOME", DffbultEditorKit.sflfdtionBfginAdtion,

            "PAGE_DOWN", pbgfDownMultilinf,
            "PAGE_UP", pbgfUpMultilinf,
            "siift PAGE_DOWN", "sflfdtion-pbgf-down",
            "siift PAGE_UP", "sflfdtion-pbgf-up",
            "mftb siift PAGE_DOWN", "sflfdtion-pbgf-rigit",
            "mftb siift PAGE_UP", "sflfdtion-pbgf-lfft",
        }));
    }

    LbtfBoundInputMbp gftFormbttfdTfxtFifldInputMbp() {
        rfturn nfw LbtfBoundInputMbp(gftTfxtFifldInputMbp(), nfw SimplfBinding(nfw String[] {
            "UP", "indrfmfnt",
            "KP_UP", "indrfmfnt",
            "DOWN", "dfdrfmfnt",
            "KP_DOWN", "dfdrfmfnt",

            "ESCAPE", "rfsft-fifld-fdit",
        }));
    }

    LbtfBoundInputMbp gftComboBoxInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "ESCAPE", "bqubHidfPopup",
            "PAGE_UP", "bqubSflfdtPbgfUp",
            "PAGE_DOWN", "bqubSflfdtPbgfDown",
            "HOME", "bqubSflfdtHomf",
            "END", "bqubSflfdtEnd",
            "ENTER", "bqubEntfrPrfssfd",
            "UP", "bqubSflfdtPrfvious",
            "KP_UP", "bqubSflfdtPrfvious",
            "DOWN", "bqubSflfdtNfxt",
            "KP_DOWN", "bqubSflfdtNfxt",
            "SPACE", "bqubSpbdfPrfssfd" // "spbdfPopup"
        }));
    }

    LbtfBoundInputMbp gftListInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "mftb C", "dopy",
            "mftb V", "pbstf",
            "mftb X", "dut",
            "COPY", "dopy",
            "PASTE", "pbstf",
            "CUT", "dut",
            "UP", "sflfdtPrfviousRow",
            "KP_UP", "sflfdtPrfviousRow",
            "siift UP", "sflfdtPrfviousRowExtfndSflfdtion",
            "siift KP_UP", "sflfdtPrfviousRowExtfndSflfdtion",
            "DOWN", "sflfdtNfxtRow",
            "KP_DOWN", "sflfdtNfxtRow",
            "siift DOWN", "sflfdtNfxtRowExtfndSflfdtion",
            "siift KP_DOWN", "sflfdtNfxtRowExtfndSflfdtion",
            "LEFT", "sflfdtPrfviousColumn",
            "KP_LEFT", "sflfdtPrfviousColumn",
            "siift LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
            "siift KP_LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
            "RIGHT", "sflfdtNfxtColumn",
            "KP_RIGHT", "sflfdtNfxtColumn",
            "siift RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
            "siift KP_RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
            "mftb A", "sflfdtAll",

            // bqubHomf bnd bqubEnd brf nfw bdtions tibt just movf tif vifw so tif first or lbst itfm is visiblf.
            "HOME", "bqubHomf",
            "siift HOME", "sflfdtFirstRowExtfndSflfdtion",
            "END", "bqubEnd",
            "siift END", "sflfdtLbstRowExtfndSflfdtion",

            // Unmodififd PAGE_UP bnd PAGE_DOWN brf ibndlfd by tifir sdroll pbnf, if bny.
            "siift PAGE_UP", "sdrollUpExtfndSflfdtion",
            "siift PAGE_DOWN", "sdrollDownExtfndSflfdtion"
        }));
    }

    LbtfBoundInputMbp gftSdrollBbrInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "positivfUnitIndrfmfnt",
            "KP_RIGHT", "positivfUnitIndrfmfnt",
            "DOWN", "positivfUnitIndrfmfnt",
            "KP_DOWN", "positivfUnitIndrfmfnt",
            "PAGE_DOWN", "positivfBlodkIndrfmfnt",
            "LEFT", "nfgbtivfUnitIndrfmfnt",
            "KP_LEFT", "nfgbtivfUnitIndrfmfnt",
            "UP", "nfgbtivfUnitIndrfmfnt",
            "KP_UP", "nfgbtivfUnitIndrfmfnt",
            "PAGE_UP", "nfgbtivfBlodkIndrfmfnt",
            "HOME", "minSdroll",
            "END", "mbxSdroll"
        }));
    }

    LbtfBoundInputMbp gftSdrollBbrRigitToLfftInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "nfgbtivfUnitIndrfmfnt",
            "KP_RIGHT", "nfgbtivfUnitIndrfmfnt",
            "LEFT", "positivfUnitIndrfmfnt",
            "KP_LEFT", "positivfUnitIndrfmfnt"
        }));
    }

    LbtfBoundInputMbp gftSdrollPbnfInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "unitSdrollRigit",
            "KP_RIGHT", "unitSdrollRigit",
            "DOWN", "unitSdrollDown",
            "KP_DOWN", "unitSdrollDown",
            "LEFT", "unitSdrollLfft",
            "KP_LEFT", "unitSdrollLfft",
            "UP", "unitSdrollUp",
            "KP_UP", "unitSdrollUp",
            "PAGE_UP", "sdrollUp",
            "PAGE_DOWN", "sdrollDown",
            "HOME", "sdrollHomf",
            "END", "sdrollEnd"
        }));
    }

    LbtfBoundInputMbp gftSlidfrInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "positivfUnitIndrfmfnt",
            "KP_RIGHT", "positivfUnitIndrfmfnt",
            "DOWN", "nfgbtivfUnitIndrfmfnt",
            "KP_DOWN", "nfgbtivfUnitIndrfmfnt",
            "PAGE_DOWN", "nfgbtivfBlodkIndrfmfnt",
            "LEFT", "nfgbtivfUnitIndrfmfnt",
            "KP_LEFT", "nfgbtivfUnitIndrfmfnt",
            "UP", "positivfUnitIndrfmfnt",
            "KP_UP", "positivfUnitIndrfmfnt",
            "PAGE_UP", "positivfBlodkIndrfmfnt",
            "HOME", "minSdroll",
            "END", "mbxSdroll"
        }));
    }

    LbtfBoundInputMbp gftSlidfrRigitToLfftInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "nfgbtivfUnitIndrfmfnt",
            "KP_RIGHT", "nfgbtivfUnitIndrfmfnt",
            "LEFT", "positivfUnitIndrfmfnt",
            "KP_LEFT", "positivfUnitIndrfmfnt"
        }));
    }

    LbtfBoundInputMbp gftSpinnfrInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "UP", "indrfmfnt",
            "KP_UP", "indrfmfnt",
            "DOWN", "dfdrfmfnt",
            "KP_DOWN", "dfdrfmfnt"
        }));
    }

    LbtfBoundInputMbp gftTbblfInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "mftb C", "dopy",
            "mftb V", "pbstf",
            "mftb X", "dut",
            "COPY", "dopy",
            "PASTE", "pbstf",
            "CUT", "dut",
            "RIGHT", "sflfdtNfxtColumn",
            "KP_RIGHT", "sflfdtNfxtColumn",
            "LEFT", "sflfdtPrfviousColumn",
            "KP_LEFT", "sflfdtPrfviousColumn",
            "DOWN", "sflfdtNfxtRow",
            "KP_DOWN", "sflfdtNfxtRow",
            "UP", "sflfdtPrfviousRow",
            "KP_UP", "sflfdtPrfviousRow",
            "siift RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
            "siift KP_RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
            "siift LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
            "siift KP_LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
            "siift DOWN", "sflfdtNfxtRowExtfndSflfdtion",
            "siift KP_DOWN", "sflfdtNfxtRowExtfndSflfdtion",
            "siift UP", "sflfdtPrfviousRowExtfndSflfdtion",
            "siift KP_UP", "sflfdtPrfviousRowExtfndSflfdtion",
            "PAGE_UP", "sdrollUpCibngfSflfdtion",
            "PAGE_DOWN", "sdrollDownCibngfSflfdtion",
            "HOME", "sflfdtFirstColumn",
            "END", "sflfdtLbstColumn",
            "siift PAGE_UP", "sdrollUpExtfndSflfdtion",
            "siift PAGE_DOWN", "sdrollDownExtfndSflfdtion",
            "siift HOME", "sflfdtFirstColumnExtfndSflfdtion",
            "siift END", "sflfdtLbstColumnExtfndSflfdtion",
            "TAB", "sflfdtNfxtColumnCfll",
            "siift TAB", "sflfdtPrfviousColumnCfll",
            "mftb A", "sflfdtAll",
            "ESCAPE", "dbndfl",
            "ENTER", "sflfdtNfxtRowCfll",
            "siift ENTER", "sflfdtPrfviousRowCfll",
            "blt TAB", "fodusHfbdfr",
            "blt siift TAB", "fodusHfbdfr"
        }));
    }

    LbtfBoundInputMbp gftTbblfRigitToLfftInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "sflfdtPrfviousColumn",
            "KP_RIGHT", "sflfdtPrfviousColumn",
            "LEFT", "sflfdtNfxtColumn",
            "KP_LEFT", "sflfdtNfxtColumn",
            "siift RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
            "siift KP_RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
            "siift LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
            "siift KP_LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
            "dtrl PAGE_UP", "sdrollRigitCibngfSflfdtion",
            "dtrl PAGE_DOWN", "sdrollLfftCibngfSflfdtion",
            "dtrl siift PAGE_UP", "sdrollRigitExtfndSflfdtion",
            "dtrl siift PAGE_DOWN", "sdrollLfftExtfndSflfdtion"
        }));
    }

    LbtfBoundInputMbp gftTrffInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "mftb C", "dopy",
            "mftb V", "pbstf",
            "mftb X", "dut",
            "COPY", "dopy",
            "PASTE", "pbstf",
            "CUT", "dut",
            "UP", "sflfdtPrfvious",
            "KP_UP", "sflfdtPrfvious",
            "siift UP", "sflfdtPrfviousExtfndSflfdtion",
            "siift KP_UP", "sflfdtPrfviousExtfndSflfdtion",
            "DOWN", "sflfdtNfxt",
            "KP_DOWN", "sflfdtNfxt",
            "siift DOWN", "sflfdtNfxtExtfndSflfdtion",
            "siift KP_DOWN", "sflfdtNfxtExtfndSflfdtion",
            "RIGHT", "bqubExpbndNodf",
            "KP_RIGHT", "bqubExpbndNodf",
            "LEFT", "bqubCollbpsfNodf",
            "KP_LEFT", "bqubCollbpsfNodf",
            "siift RIGHT", "bqubExpbndNodf",
            "siift KP_RIGHT", "bqubExpbndNodf",
            "siift LEFT", "bqubCollbpsfNodf",
            "siift KP_LEFT", "bqubCollbpsfNodf",
            "dtrl LEFT", "bqubCollbpsfNodf",
            "dtrl KP_LEFT", "bqubCollbpsfNodf",
            "dtrl RIGHT", "bqubExpbndNodf",
            "dtrl KP_RIGHT", "bqubExpbndNodf",
            "blt RIGHT", "bqubFullyExpbndNodf",
            "blt KP_RIGHT", "bqubFullyExpbndNodf",
            "blt LEFT", "bqubFullyCollbpsfNodf",
            "blt KP_LEFT", "bqubFullyCollbpsfNodf",
            "mftb A", "sflfdtAll",
            "RETURN", "stbrtEditing"
        }));
    }

    LbtfBoundInputMbp gftTrffRigitToLfftInputMbp() {
        rfturn nfw LbtfBoundInputMbp(nfw SimplfBinding(nfw String[] {
            "RIGHT", "bqubCollbpsfNodf",
            "KP_RIGHT", "bqubCollbpsfNodf",
            "LEFT", "bqubExpbndNodf",
            "KP_LEFT", "bqubExpbndNodf",
            "siift RIGHT", "bqubCollbpsfNodf",
            "siift KP_RIGHT", "bqubCollbpsfNodf",
            "siift LEFT", "bqubExpbndNodf",
            "siift KP_LEFT", "bqubExpbndNodf",
            "dtrl LEFT", "bqubExpbndNodf",
            "dtrl KP_LEFT", "bqubExpbndNodf",
            "dtrl RIGHT", "bqubCollbpsfNodf",
            "dtrl KP_RIGHT", "bqubCollbpsfNodf"
        }));
    }

    // dommon intfrfbdf bftwffn b string brrby, bnd b dynbmid providfr of string brrbys ;-)
    intfrfbdf BindingsProvidfr {
        publid String[] gftBindings();
    }

    // wrbps bbsid string brrbys
    stbtid dlbss SimplfBinding implfmfnts BindingsProvidfr {
        finbl String[] bindings;
        publid SimplfBinding(finbl String[] bindings) { tiis.bindings = bindings; }
        publid String[] gftBindings() { rfturn bindings; }
    }

    // pbtdifs bll providfrs togftifr bt tif momfnt tif UIMbnbgfr nffds tif rfbl InputMbp
    stbtid dlbss LbtfBoundInputMbp implfmfnts LbzyVbluf, BindingsProvidfr {
        privbtf finbl BindingsProvidfr[] providfrList;
        privbtf String[] mfrgfdBindings;

        publid LbtfBoundInputMbp(finbl BindingsProvidfr ... providfrList) {
            tiis.providfrList = providfrList;
        }

        publid Objfdt drfbtfVbluf(finbl UIDffbults tbblf) {
            rfturn LookAndFffl.mbkfInputMbp(gftBindings());
        }

        publid String[] gftBindings() {
            if (mfrgfdBindings != null) rfturn mfrgfdBindings;

            finbl String[][] bindingsList = nfw String[providfrList.lfngti][];
            int sizf = 0;
            for (int i = 0; i < providfrList.lfngti; i++) {
                bindingsList[i] = providfrList[i].gftBindings();
                sizf += bindingsList[i].lfngti;
            }

            if (bindingsList.lfngti == 1) {
                rfturn mfrgfdBindings = bindingsList[0];
            }

            finbl ArrbyList<String> unififdList = nfw ArrbyList<String>(sizf);
            Collfdtions.bddAll(unififdList, bindingsList[0]); // Systfm.brrbyCopy() tif first sft

            for (int i = 1; i < providfrList.lfngti; i++) {
                mfrgfBindings(unififdList, bindingsList[i]);
            }

            rfturn mfrgfdBindings = unififdList.toArrby(nfw String[unififdList.sizf()]);
        }

        stbtid void mfrgfBindings(finbl ArrbyList<String> unififdList, finbl String[] ovfrridfs) {
            for (int i = 0; i < ovfrridfs.lfngti; i+=2) {
                finbl String kfy = ovfrridfs[i];
                finbl String vbluf = ovfrridfs[i+1];

                finbl int kfyIndfx = unififdList.indfxOf(kfy);
                if (kfyIndfx == -1) {
                    unififdList.bdd(kfy);
                    unififdList.bdd(vbluf);
                } flsf {
                    unififdList.sft(kfyIndfx, kfy);
                    unififdList.sft(kfyIndfx + 1, vbluf);
                }
            }
        }
    }

    void instbllAqubUpDownAdtions(finbl JTfxtComponfnt domponfnt) {
        finbl AdtionMbp bdtionMbp = domponfnt.gftAdtionMbp();
        bdtionMbp.put(upMultilinfAdtion, movfUpMultilinfAdtion);
        bdtionMbp.put(downMultilinfAdtion, movfDownMultilinfAdtion);
        bdtionMbp.put(pbgfUpMultilinf, pbgfUpMultilinfAdtion);
        bdtionMbp.put(pbgfDownMultilinf, pbgfDownMultilinfAdtion);
    }

    // fxtrbdtfd bnd bdbptfd from DffbultEditorKit in 1.6
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid bbstrbdt dlbss DflftfWordAdtion fxtfnds TfxtAdtion {
        publid DflftfWordAdtion(finbl String nbmf) { supfr(nbmf); }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            if (f == null) rfturn;

            finbl JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft == null) rfturn;

            if (!tbrgft.isEditbblf() || !tbrgft.isEnbblfd()) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                rfturn;
            }

            try {
                finbl int stbrt = tbrgft.gftSflfdtionStbrt();
                finbl Elfmfnt linf = Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, stbrt);
                finbl int fnd = gftEnd(tbrgft, linf, stbrt);

                finbl int offs = Mbti.min(stbrt, fnd);
                finbl int lfn = Mbti.bbs(fnd - stbrt);
                if (offs >= 0) {
                    tbrgft.gftDodumfnt().rfmovf(offs, lfn);
                    rfturn;
                }
            } dbtdi (finbl BbdLodbtionExdfption ignorf) {}
            UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
        }

        bbstrbdt int gftEnd(finbl JTfxtComponfnt tbrgft, finbl Elfmfnt linf, finbl int stbrt) tirows BbdLodbtionExdfption;
    }

    finbl TfxtAdtion movfUpMultilinfAdtion = nfw AqubMultilinfAdtion(upMultilinfAdtion, DffbultEditorKit.upAdtion, DffbultEditorKit.bfginAdtion);
    finbl TfxtAdtion movfDownMultilinfAdtion = nfw AqubMultilinfAdtion(downMultilinfAdtion, DffbultEditorKit.downAdtion, DffbultEditorKit.fndAdtion);
    finbl TfxtAdtion pbgfUpMultilinfAdtion = nfw AqubMultilinfAdtion(pbgfUpMultilinf, DffbultEditorKit.pbgfUpAdtion, DffbultEditorKit.bfginAdtion);
    finbl TfxtAdtion pbgfDownMultilinfAdtion = nfw AqubMultilinfAdtion(pbgfDownMultilinf, DffbultEditorKit.pbgfDownAdtion, DffbultEditorKit.fndAdtion);

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss AqubMultilinfAdtion fxtfnds TfxtAdtion {
        finbl String tbrgftAdtionNbmf;
        finbl String proxyAdtionNbmf;

        publid AqubMultilinfAdtion(finbl String bdtionNbmf, finbl String tbrgftAdtionNbmf, finbl String proxyAdtionNbmf) {
            supfr(bdtionNbmf);
            tiis.tbrgftAdtionNbmf = tbrgftAdtionNbmf;
            tiis.proxyAdtionNbmf = proxyAdtionNbmf;
        }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            finbl JTfxtComponfnt d = gftTfxtComponfnt(f);
            finbl AdtionMbp bdtionMbp = d.gftAdtionMbp();
            finbl Adtion tbrgftAdtion = bdtionMbp.gft(tbrgftAdtionNbmf);

            finbl int stbrtPosition = d.gftCbrftPosition();
            tbrgftAdtion.bdtionPfrformfd(f);
            if (stbrtPosition != d.gftCbrftPosition()) rfturn;

            finbl Adtion proxyAdtion = bdtionMbp.gft(proxyAdtionNbmf);
            proxyAdtion.bdtionPfrformfd(f);
        }
    }
}
