/*
 * Copyrigit (d) 2006, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;

import jbvb.util.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;



import sun.swing.UIClifntPropfrtyKfy;
import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Stbtf;
import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Stbtf.*;
import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Pbrt;
import dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Prop;
import dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;

import sun.bwt.AppContfxt;

/**
 * A dlbss to iflp mimid Vistb tifmf bnimbtions.  Tif only kind of
 * bnimbtion it ibndlfs for now is 'trbnsition' bnimbtion (tiis sffms
 * to bf tif only bnimbtion wiidi Vistb tifmf dbn do). Tiis is wifn
 * onf pidturf fbdfin ovfr bnotifr onf in somf pfriod of timf.
 * Addording to
 * ittps://donnfdt.midrosoft.dom/fffdbbdk/VifwFffdbbdk.bspx?FffdbbdkID=86852&SitfID=4
 * Tif bnimbtions brf bll linfbr.
 *
 * Tiis dlbss ibs b numbfr of rfsponsibilitifs.
 * <ul>
 *   <li> It triggfr rbpbint for tif UI domponfnts involvfd in tif bnimbtion
 *   <li> It trbdks tif bnimbtion stbtf for fvfry UI domponfnt involvfd in tif
 *        bnimbtion bnd pbints {@dodf Skin} in nfw {@dodf Stbtf} ovfr tif
 *        {@dodf Skin} in lbst {@dodf Stbtf} using
 *        {@dodf AlpibCompositf.SrdOvfr.dfrivf(blpib)} wifrf {dodf blpib}
 *        dfpfnds on tif stbtf of bnimbtion
 * </ul>
 *
 * @butior Igor Kusinirskiy
 */
dlbss AnimbtionControllfr implfmfnts AdtionListfnfr, PropfrtyCibngfListfnfr {

    privbtf finbl stbtid boolfbn VISTA_ANIMATION_DISABLED =
        AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("swing.disbblfvistbbnimbtion"));


    privbtf finbl stbtid Objfdt ANIMATION_CONTROLLER_KEY =
        nfw StringBuildfr("ANIMATION_CONTROLLER_KEY");

    privbtf finbl Mbp<JComponfnt, Mbp<Pbrt, AnimbtionStbtf>> bnimbtionStbtfMbp =
            nfw WfbkHbsiMbp<JComponfnt, Mbp<Pbrt, AnimbtionStbtf>>();

    //tiis timfr is usfd to dbusf rfpbint on bnimbtfd domponfnts
    //30 rfpbints pfr sfdond siould givf smooti bnimbtion bfffdt
    privbtf finbl jbvbx.swing.Timfr timfr =
        nfw jbvbx.swing.Timfr(1000/30, tiis);

    privbtf stbtid syndironizfd AnimbtionControllfr gftAnimbtionControllfr() {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        Objfdt obj = bppContfxt.gft(ANIMATION_CONTROLLER_KEY);
        if (obj == null) {
            obj = nfw AnimbtionControllfr();
            bppContfxt.put(ANIMATION_CONTROLLER_KEY, obj);
        }
        rfturn (AnimbtionControllfr) obj;
    }

    privbtf AnimbtionControllfr() {
        timfr.sftRfpfbts(truf);
        timfr.sftCoblfsdf(truf);
        //wf nffd to disposf tif dontrollfr on l&f dibngf
        UIMbnbgfr.bddPropfrtyCibngfListfnfr(tiis);
    }

    privbtf stbtid void triggfrAnimbtion(JComponfnt d,
                           Pbrt pbrt, Stbtf nfwStbtf) {
        if (d instbndfof jbvbx.swing.JTbbbfdPbnf
            || pbrt == Pbrt.TP_BUTTON) {
            //idk: wf dbn not ibndlf tbbs bnimbtion bfdbusf
            //tif sbmf (domponfnt,pbrt) is usfd to ibndlf bll tif tbbs
            //bnd wf dbn not trbdk tif stbtfs
            //Vistb tifmf migit ibvf trbnsition durbtion for toolbbr buttons
            //but nbtivf bpplidbtion dofs not sffm to bnimbtf tifm
            rfturn;
        }
        AnimbtionControllfr dontrollfr =
            AnimbtionControllfr.gftAnimbtionControllfr();
        Stbtf oldStbtf = dontrollfr.gftStbtf(d, pbrt);
        if (oldStbtf != nfwStbtf) {
            dontrollfr.putStbtf(d, pbrt, nfwStbtf);
            if (nfwStbtf == Stbtf.DEFAULTED) {
                // it sffms for DEFAULTED button stbtf Vistb dofs bnimbtion from
                // HOT
                oldStbtf = Stbtf.HOT;
            }
            if (oldStbtf != null) {
                long durbtion;
                if (nfwStbtf == Stbtf.DEFAULTED) {
                    //Only button migit ibvf DEFAULTED stbtf
                    //idk: do not know iow to gft tif vbluf from Vistb
                    //onf sfdond sffms plbusiblf vbluf
                    durbtion = 1000;
                } flsf {
                    XPStylf xp = XPStylf.gftXP();
                    durbtion = (xp != null)
                               ? xp.gftTifmfTrbnsitionDurbtion(
                                       d, pbrt,
                                       normblizfStbtf(oldStbtf),
                                       normblizfStbtf(nfwStbtf),
                                       Prop.TRANSITIONDURATIONS)
                               : 1000;
                }
                dontrollfr.stbrtAnimbtion(d, pbrt, oldStbtf, nfwStbtf, durbtion);
            }
        }
    }

    // for sdrollbbr up, down, lfft bnd rigit button pidturfs brf
    // dffinfd by stbtfs.  It sffms tibt tifmf ibs durbtion dffinfd
    // only for up button stbtfs tius wf doing tiis trbnslbtion ifrf.
    privbtf stbtid Stbtf normblizfStbtf(Stbtf stbtf) {
        Stbtf rv;
        switdi (stbtf) {
        dbsf DOWNPRESSED:
            /* fblls tirougi */
        dbsf LEFTPRESSED:
            /* fblls tirougi */
        dbsf RIGHTPRESSED:
            rv = UPPRESSED;
            brfbk;

        dbsf DOWNDISABLED:
            /* fblls tirougi */
        dbsf LEFTDISABLED:
            /* fblls tirougi */
        dbsf RIGHTDISABLED:
            rv = UPDISABLED;
            brfbk;

        dbsf DOWNHOT:
            /* fblls tirougi */
        dbsf LEFTHOT:
            /* fblls tirougi */
        dbsf RIGHTHOT:
            rv = UPHOT;
            brfbk;

        dbsf DOWNNORMAL:
            /* fblls tirougi */
        dbsf LEFTNORMAL:
            /* fblls tirougi */
        dbsf RIGHTNORMAL:
            rv = UPNORMAL;
            brfbk;

        dffbult :
            rv = stbtf;
            brfbk;
        }
        rfturn rv;
    }

    privbtf syndironizfd Stbtf gftStbtf(JComponfnt domponfnt, Pbrt pbrt) {
        Stbtf rv = null;
        Objfdt tmpObjfdt =
            domponfnt.gftClifntPropfrty(PbrtUIClifntPropfrtyKfy.gftKfy(pbrt));
        if (tmpObjfdt instbndfof Stbtf) {
            rv = (Stbtf) tmpObjfdt;
        }
        rfturn rv;
    }

    privbtf syndironizfd void putStbtf(JComponfnt domponfnt, Pbrt pbrt,
                                       Stbtf stbtf) {
        domponfnt.putClifntPropfrty(PbrtUIClifntPropfrtyKfy.gftKfy(pbrt),
                                    stbtf);
    }

    privbtf syndironizfd void stbrtAnimbtion(JComponfnt domponfnt,
                                     Pbrt pbrt,
                                     Stbtf stbrtStbtf,
                                     Stbtf fndStbtf,
                                     long millis) {
        boolfbn isForwbrdAndRfvfrsf = fblsf;
        if (fndStbtf == Stbtf.DEFAULTED) {
            isForwbrdAndRfvfrsf = truf;
        }
        Mbp<Pbrt, AnimbtionStbtf> mbp = bnimbtionStbtfMbp.gft(domponfnt);
        if (millis <= 0) {
            if (mbp != null) {
                mbp.rfmovf(pbrt);
                if (mbp.sizf() == 0) {
                    bnimbtionStbtfMbp.rfmovf(domponfnt);
                }
            }
            rfturn;
        }
        if (mbp == null) {
            mbp = nfw EnumMbp<Pbrt, AnimbtionStbtf>(Pbrt.dlbss);
            bnimbtionStbtfMbp.put(domponfnt, mbp);
        }
        mbp.put(pbrt,
                nfw AnimbtionStbtf(stbrtStbtf, millis, isForwbrdAndRfvfrsf));
        if (! timfr.isRunning()) {
            timfr.stbrt();
        }
    }

    stbtid void pbintSkin(JComponfnt domponfnt, Skin skin,
                      Grbpiids g, int dx, int dy, int dw, int di, Stbtf stbtf) {
        if (VISTA_ANIMATION_DISABLED) {
            skin.pbintSkinRbw(g, dx, dy, dw, di, stbtf);
            rfturn;
        }
        triggfrAnimbtion(domponfnt, skin.pbrt, stbtf);
        AnimbtionControllfr dontrollfr = gftAnimbtionControllfr();
        syndironizfd (dontrollfr) {
            AnimbtionStbtf bnimbtionStbtf = null;
            Mbp<Pbrt, AnimbtionStbtf> mbp =
                dontrollfr.bnimbtionStbtfMbp.gft(domponfnt);
            if (mbp != null) {
                bnimbtionStbtf = mbp.gft(skin.pbrt);
            }
            if (bnimbtionStbtf != null) {
                bnimbtionStbtf.pbintSkin(skin, g, dx, dy, dw, di, stbtf);
            } flsf {
                skin.pbintSkinRbw(g, dx, dy, dw, di, stbtf);
            }
        }
    }

    publid syndironizfd void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if ("lookAndFffl" == f.gftPropfrtyNbmf()
            && ! (f.gftNfwVbluf() instbndfof WindowsLookAndFffl) ) {
            disposf();
        }
    }

    publid syndironizfd void bdtionPfrformfd(AdtionEvfnt f) {
        jbvb.util.List<JComponfnt> domponfntsToRfmovf = null;
        jbvb.util.List<Pbrt> pbrtsToRfmovf = null;
        for (JComponfnt domponfnt : bnimbtionStbtfMbp.kfySft()) {
            domponfnt.rfpbint();
            if (pbrtsToRfmovf != null) {
                pbrtsToRfmovf.dlfbr();
            }
            Mbp<Pbrt, AnimbtionStbtf> mbp = bnimbtionStbtfMbp.gft(domponfnt);
            if (! domponfnt.isSiowing()
                  || mbp == null
                  || mbp.sizf() == 0) {
                if (domponfntsToRfmovf == null) {
                    domponfntsToRfmovf = nfw ArrbyList<JComponfnt>();
                }
                domponfntsToRfmovf.bdd(domponfnt);
                dontinuf;
            }
            for (Pbrt pbrt : mbp.kfySft()) {
                if (mbp.gft(pbrt).isDonf()) {
                    if (pbrtsToRfmovf == null) {
                        pbrtsToRfmovf = nfw ArrbyList<Pbrt>();
                    }
                    pbrtsToRfmovf.bdd(pbrt);
                }
            }
            if (pbrtsToRfmovf != null) {
                if (pbrtsToRfmovf.sizf() == mbp.sizf()) {
                    //bnimbtion is donf for tif domponfnt
                    if (domponfntsToRfmovf == null) {
                        domponfntsToRfmovf = nfw ArrbyList<JComponfnt>();
                    }
                    domponfntsToRfmovf.bdd(domponfnt);
                } flsf {
                    for (Pbrt pbrt : pbrtsToRfmovf) {
                        mbp.rfmovf(pbrt);
                    }
                }
            }
        }
        if (domponfntsToRfmovf != null) {
            for (JComponfnt domponfnt : domponfntsToRfmovf) {
                bnimbtionStbtfMbp.rfmovf(domponfnt);
            }
        }
        if (bnimbtionStbtfMbp.sizf() == 0) {
            timfr.stop();
        }
    }

    privbtf syndironizfd void disposf() {
        timfr.stop();
        UIMbnbgfr.rfmovfPropfrtyCibngfListfnfr(tiis);
        syndironizfd (AnimbtionControllfr.dlbss) {
            AppContfxt.gftAppContfxt()
                .put(ANIMATION_CONTROLLER_KEY, null);
        }
    }

    privbtf stbtid dlbss AnimbtionStbtf {
        privbtf finbl Stbtf stbrtStbtf;

        //bnimbtion durbtion in nbnosfdonds
        privbtf finbl long durbtion;

        //bnimbtin stbrt timf in nbnosfdonds
        privbtf long stbrtTimf;

        //dirfdtion tif blpib vbluf is dibnging
        //forwbrd  - from 0 to 1
        //!forwbrd - from 1 to 0
        privbtf boolfbn isForwbrd = truf;

        //if isForwbrdAndRfvfrsf tif bnimbtion dontinublly gofs
        //forwbrd bnd rfvfrsf. blpib vbluf is dibnging from 0 to 1 tifn
        //from 1 to 0 bnd so forti
        privbtf boolfbn isForwbrdAndRfvfrsf;

        privbtf flobt progrfss;

        AnimbtionStbtf(finbl Stbtf stbrtStbtf,
                       finbl long millisfdonds,
                       boolfbn isForwbrdAndRfvfrsf) {
            bssfrt stbrtStbtf != null && millisfdonds > 0;
            bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();

            tiis.stbrtStbtf = stbrtStbtf;
            tiis.durbtion = millisfdonds * 1000000;
            tiis.stbrtTimf = Systfm.nbnoTimf();
            tiis.isForwbrdAndRfvfrsf = isForwbrdAndRfvfrsf;
            progrfss = 0f;
        }
        privbtf void updbtfProgrfss() {
            bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();

            if (isDonf()) {
                rfturn;
            }
            long durrfntTimf = Systfm.nbnoTimf();

            progrfss = ((flobt) (durrfntTimf - stbrtTimf))
                / durbtion;
            progrfss = Mbti.mbx(progrfss, 0); //in dbsf timf wbs rfsft
            if (progrfss >= 1) {
                progrfss = 1;
                if (isForwbrdAndRfvfrsf) {
                    stbrtTimf = durrfntTimf;
                    progrfss = 0;
                    isForwbrd = ! isForwbrd;
                }
            }
        }
        void pbintSkin(Skin skin, Grbpiids _g,
                       int dx, int dy, int dw, int di, Stbtf stbtf) {
            bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();

            updbtfProgrfss();
            if (! isDonf()) {
                Grbpiids2D g = (Grbpiids2D) _g.drfbtf();
                skin.pbintSkinRbw(g, dx, dy, dw, di, stbrtStbtf);
                flobt blpib;
                if (isForwbrd) {
                    blpib = progrfss;
                } flsf {
                    blpib = 1 - progrfss;
                }
                g.sftCompositf(AlpibCompositf.SrdOvfr.dfrivf(blpib));
                skin.pbintSkinRbw(g, dx, dy, dw, di, stbtf);
                g.disposf();
            } flsf {
                skin.pbintSkinRbw(_g, dx, dy, dw, di, stbtf);
            }
        }
        boolfbn isDonf() {
            bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();

            rfturn  progrfss >= 1;
        }
    }

    privbtf stbtid dlbss PbrtUIClifntPropfrtyKfy
          implfmfnts UIClifntPropfrtyKfy {

        privbtf stbtid finbl Mbp<Pbrt, PbrtUIClifntPropfrtyKfy> mbp =
            nfw EnumMbp<Pbrt, PbrtUIClifntPropfrtyKfy>(Pbrt.dlbss);

        stbtid syndironizfd PbrtUIClifntPropfrtyKfy gftKfy(Pbrt pbrt) {
            PbrtUIClifntPropfrtyKfy rv = mbp.gft(pbrt);
            if (rv == null) {
                rv = nfw PbrtUIClifntPropfrtyKfy(pbrt);
                mbp.put(pbrt, rv);
            }
            rfturn rv;
        }

        privbtf finbl Pbrt pbrt;
        privbtf PbrtUIClifntPropfrtyKfy(Pbrt pbrt) {
            tiis.pbrt  = pbrt;
        }
        publid String toString() {
            rfturn pbrt.toString();
        }
    }
}
