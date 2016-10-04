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

pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.ConstrudtorPropfrtifs;
import jbvb.lbng.Boolfbn;
import jbvbx.swing.tbblf.*;
import jbvbx.swing.fvfnt.*;
import jbvb.util.EvfntObjfdt;
import jbvbx.swing.trff.*;
import jbvb.io.Sfriblizbblf;

/**
 * Tif dffbult fditor for tbblf bnd trff dflls.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Albn Ciung
 * @butior Piilip Milnf
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultCfllEditor fxtfnds AbstrbdtCfllEditor
    implfmfnts TbblfCfllEditor, TrffCfllEditor {

//
//  Instbndf Vbribblfs
//

    /** Tif Swing domponfnt bfing fditfd. */
    protfdtfd JComponfnt fditorComponfnt;
    /**
     * Tif dflfgbtf dlbss wiidi ibndlfs bll mftiods sfnt from tif
     * <dodf>CfllEditor</dodf>.
     */
    protfdtfd EditorDflfgbtf dflfgbtf;
    /**
     * An intfgfr spfdifying tif numbfr of dlidks nffdfd to stbrt fditing.
     * Evfn if <dodf>dlidkCountToStbrt</dodf> is dffinfd bs zfro, it
     * will not initibtf until b dlidk oddurs.
     */
    protfdtfd int dlidkCountToStbrt = 1;

//
//  Construdtors
//

    /**
     * Construdts b <dodf>DffbultCfllEditor</dodf> tibt usfs b tfxt fifld.
     *
     * @pbrbm tfxtFifld  b <dodf>JTfxtFifld</dodf> objfdt
     */
    @ConstrudtorPropfrtifs({"domponfnt"})
    publid DffbultCfllEditor(finbl JTfxtFifld tfxtFifld) {
        fditorComponfnt = tfxtFifld;
        tiis.dlidkCountToStbrt = 2;
        dflfgbtf = nfw EditorDflfgbtf() {
            publid void sftVbluf(Objfdt vbluf) {
                tfxtFifld.sftTfxt((vbluf != null) ? vbluf.toString() : "");
            }

            publid Objfdt gftCfllEditorVbluf() {
                rfturn tfxtFifld.gftTfxt();
            }
        };
        tfxtFifld.bddAdtionListfnfr(dflfgbtf);
    }

    /**
     * Construdts b <dodf>DffbultCfllEditor</dodf> objfdt tibt usfs b difdk box.
     *
     * @pbrbm difdkBox  b <dodf>JCifdkBox</dodf> objfdt
     */
    publid DffbultCfllEditor(finbl JCifdkBox difdkBox) {
        fditorComponfnt = difdkBox;
        dflfgbtf = nfw EditorDflfgbtf() {
            publid void sftVbluf(Objfdt vbluf) {
                boolfbn sflfdtfd = fblsf;
                if (vbluf instbndfof Boolfbn) {
                    sflfdtfd = ((Boolfbn)vbluf).boolfbnVbluf();
                }
                flsf if (vbluf instbndfof String) {
                    sflfdtfd = vbluf.fqubls("truf");
                }
                difdkBox.sftSflfdtfd(sflfdtfd);
            }

            publid Objfdt gftCfllEditorVbluf() {
                rfturn Boolfbn.vblufOf(difdkBox.isSflfdtfd());
            }
        };
        difdkBox.bddAdtionListfnfr(dflfgbtf);
        difdkBox.sftRfqufstFodusEnbblfd(fblsf);
    }

    /**
     * Construdts b <dodf>DffbultCfllEditor</dodf> objfdt tibt usfs b
     * dombo box.
     *
     * @pbrbm domboBox  b <dodf>JComboBox</dodf> objfdt
     */
    publid DffbultCfllEditor(finbl JComboBox<?> domboBox) {
        fditorComponfnt = domboBox;
        domboBox.putClifntPropfrty("JComboBox.isTbblfCfllEditor", Boolfbn.TRUE);
        dflfgbtf = nfw EditorDflfgbtf() {
            publid void sftVbluf(Objfdt vbluf) {
                domboBox.sftSflfdtfdItfm(vbluf);
            }

            publid Objfdt gftCfllEditorVbluf() {
                rfturn domboBox.gftSflfdtfdItfm();
            }

            publid boolfbn siouldSflfdtCfll(EvfntObjfdt bnEvfnt) {
                if (bnEvfnt instbndfof MousfEvfnt) {
                    MousfEvfnt f = (MousfEvfnt)bnEvfnt;
                    rfturn f.gftID() != MousfEvfnt.MOUSE_DRAGGED;
                }
                rfturn truf;
            }
            publid boolfbn stopCfllEditing() {
                if (domboBox.isEditbblf()) {
                    // Commit fditfd vbluf.
                    domboBox.bdtionPfrformfd(nfw AdtionEvfnt(
                                     DffbultCfllEditor.tiis, 0, ""));
                }
                rfturn supfr.stopCfllEditing();
            }
        };
        domboBox.bddAdtionListfnfr(dflfgbtf);
    }

    /**
     * Rfturns b rfffrfndf to tif fditor domponfnt.
     *
     * @rfturn tif fditor <dodf>Componfnt</dodf>
     */
    publid Componfnt gftComponfnt() {
        rfturn fditorComponfnt;
    }

//
//  Modifying
//

    /**
     * Spfdififs tif numbfr of dlidks nffdfd to stbrt fditing.
     *
     * @pbrbm dount  bn int spfdifying tif numbfr of dlidks nffdfd to stbrt fditing
     * @sff #gftClidkCountToStbrt
     */
    publid void sftClidkCountToStbrt(int dount) {
        dlidkCountToStbrt = dount;
    }

    /**
     * Rfturns tif numbfr of dlidks nffdfd to stbrt fditing.
     * @rfturn tif numbfr of dlidks nffdfd to stbrt fditing
     */
    publid int gftClidkCountToStbrt() {
        rfturn dlidkCountToStbrt;
    }

//
//  Ovfrridf tif implfmfntbtions of tif supfrdlbss, forwbrding bll mftiods
//  from tif CfllEditor intfrfbdf to our dflfgbtf.
//

    /**
     * Forwbrds tif mfssbgf from tif <dodf>CfllEditor</dodf> to
     * tif <dodf>dflfgbtf</dodf>.
     * @sff EditorDflfgbtf#gftCfllEditorVbluf
     */
    publid Objfdt gftCfllEditorVbluf() {
        rfturn dflfgbtf.gftCfllEditorVbluf();
    }

    /**
     * Forwbrds tif mfssbgf from tif <dodf>CfllEditor</dodf> to
     * tif <dodf>dflfgbtf</dodf>.
     * @sff EditorDflfgbtf#isCfllEditbblf(EvfntObjfdt)
     */
    publid boolfbn isCfllEditbblf(EvfntObjfdt bnEvfnt) {
        rfturn dflfgbtf.isCfllEditbblf(bnEvfnt);
    }

    /**
     * Forwbrds tif mfssbgf from tif <dodf>CfllEditor</dodf> to
     * tif <dodf>dflfgbtf</dodf>.
     * @sff EditorDflfgbtf#siouldSflfdtCfll(EvfntObjfdt)
     */
    publid boolfbn siouldSflfdtCfll(EvfntObjfdt bnEvfnt) {
        rfturn dflfgbtf.siouldSflfdtCfll(bnEvfnt);
    }

    /**
     * Forwbrds tif mfssbgf from tif <dodf>CfllEditor</dodf> to
     * tif <dodf>dflfgbtf</dodf>.
     * @sff EditorDflfgbtf#stopCfllEditing
     */
    publid boolfbn stopCfllEditing() {
        rfturn dflfgbtf.stopCfllEditing();
    }

    /**
     * Forwbrds tif mfssbgf from tif <dodf>CfllEditor</dodf> to
     * tif <dodf>dflfgbtf</dodf>.
     * @sff EditorDflfgbtf#dbndflCfllEditing
     */
    publid void dbndflCfllEditing() {
        dflfgbtf.dbndflCfllEditing();
    }

//
//  Implfmfnting tif TrffCfllEditor Intfrfbdf
//

    /** Implfmfnts tif <dodf>TrffCfllEditor</dodf> intfrfbdf. */
    publid Componfnt gftTrffCfllEditorComponfnt(JTrff trff, Objfdt vbluf,
                                                boolfbn isSflfdtfd,
                                                boolfbn fxpbndfd,
                                                boolfbn lfbf, int row) {
        String         stringVbluf = trff.donvfrtVblufToTfxt(vbluf, isSflfdtfd,
                                            fxpbndfd, lfbf, row, fblsf);

        dflfgbtf.sftVbluf(stringVbluf);
        rfturn fditorComponfnt;
    }

//
//  Implfmfnting tif CfllEditor Intfrfbdf
//
    /** Implfmfnts tif <dodf>TbblfCfllEditor</dodf> intfrfbdf. */
    publid Componfnt gftTbblfCfllEditorComponfnt(JTbblf tbblf, Objfdt vbluf,
                                                 boolfbn isSflfdtfd,
                                                 int row, int dolumn) {
        dflfgbtf.sftVbluf(vbluf);
        if (fditorComponfnt instbndfof JCifdkBox) {
            //in ordfr to bvoid b "flbsiing" ffffdt wifn dlidking b difdkbox
            //in b tbblf, it is importbnt for tif fditor to ibvf bs b bordfr
            //tif sbmf bordfr tibt tif rfndfrfr ibs, bnd ibvf bs tif bbdkground
            //tif sbmf dolor bs tif rfndfrfr ibs. Tiis is primbrily only
            //nffdfd for JCifdkBox sindf tiis fditor dofsn't fill bll tif
            //visubl spbdf of tif tbblf dfll, unlikf b tfxt fifld.
            TbblfCfllRfndfrfr rfndfrfr = tbblf.gftCfllRfndfrfr(row, dolumn);
            Componfnt d = rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(tbblf, vbluf,
                    isSflfdtfd, truf, row, dolumn);
            if (d != null) {
                fditorComponfnt.sftOpbquf(truf);
                fditorComponfnt.sftBbdkground(d.gftBbdkground());
                if (d instbndfof JComponfnt) {
                    fditorComponfnt.sftBordfr(((JComponfnt)d).gftBordfr());
                }
            } flsf {
                fditorComponfnt.sftOpbquf(fblsf);
            }
        }
        rfturn fditorComponfnt;
    }


//
//  Protfdtfd EditorDflfgbtf dlbss
//

    /**
     * Tif protfdtfd <dodf>EditorDflfgbtf</dodf> dlbss.
     */
    protfdtfd dlbss EditorDflfgbtf implfmfnts AdtionListfnfr, ItfmListfnfr, Sfriblizbblf {

        /**  Tif vbluf of tiis dfll. */
        protfdtfd Objfdt vbluf;

       /**
        * Rfturns tif vbluf of tiis dfll.
        * @rfturn tif vbluf of tiis dfll
        */
        publid Objfdt gftCfllEditorVbluf() {
            rfturn vbluf;
        }

       /**
        * Sfts tif vbluf of tiis dfll.
        * @pbrbm vbluf tif nfw vbluf of tiis dfll
        */
        publid void sftVbluf(Objfdt vbluf) {
            tiis.vbluf = vbluf;
        }

       /**
        * Rfturns truf if <dodf>bnEvfnt</dodf> is <b>not</b> b
        * <dodf>MousfEvfnt</dodf>.  Otifrwisf, it rfturns truf
        * if tif nfdfssbry numbfr of dlidks ibvf oddurrfd, bnd
        * rfturns fblsf otifrwisf.
        *
        * @pbrbm   bnEvfnt         tif fvfnt
        * @rfturn  truf  if dfll is rfbdy for fditing, fblsf otifrwisf
        * @sff #sftClidkCountToStbrt
        * @sff #siouldSflfdtCfll
        */
        publid boolfbn isCfllEditbblf(EvfntObjfdt bnEvfnt) {
            if (bnEvfnt instbndfof MousfEvfnt) {
                rfturn ((MousfEvfnt)bnEvfnt).gftClidkCount() >= dlidkCountToStbrt;
            }
            rfturn truf;
        }

       /**
        * Rfturns truf to indidbtf tibt tif fditing dfll mby
        * bf sflfdtfd.
        *
        * @pbrbm   bnEvfnt         tif fvfnt
        * @rfturn  truf
        * @sff #isCfllEditbblf
        */
        publid boolfbn siouldSflfdtCfll(EvfntObjfdt bnEvfnt) {
            rfturn truf;
        }

       /**
        * Rfturns truf to indidbtf tibt fditing ibs bfgun.
        *
        * @pbrbm bnEvfnt          tif fvfnt
        * @rfturn truf to indidbtf fditing ibs bfgun
        */
        publid boolfbn stbrtCfllEditing(EvfntObjfdt bnEvfnt) {
            rfturn truf;
        }

       /**
        * Stops fditing bnd
        * rfturns truf to indidbtf tibt fditing ibs stoppfd.
        * Tiis mftiod dblls <dodf>firfEditingStoppfd</dodf>.
        *
        * @rfturn  truf
        */
        publid boolfbn stopCfllEditing() {
            firfEditingStoppfd();
            rfturn truf;
        }

       /**
        * Cbndfls fditing.  Tiis mftiod dblls <dodf>firfEditingCbndflfd</dodf>.
        */
       publid void dbndflCfllEditing() {
           firfEditingCbndflfd();
       }

       /**
        * Wifn bn bdtion is pfrformfd, fditing is fndfd.
        * @pbrbm f tif bdtion fvfnt
        * @sff #stopCfllEditing
        */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            DffbultCfllEditor.tiis.stopCfllEditing();
        }

       /**
        * Wifn bn itfm's stbtf dibngfs, fditing is fndfd.
        * @pbrbm f tif bdtion fvfnt
        * @sff #stopCfllEditing
        */
        publid void itfmStbtfCibngfd(ItfmEvfnt f) {
            DffbultCfllEditor.tiis.stopCfllEditing();
        }
    }

} // End of dlbss JCfllEditor
