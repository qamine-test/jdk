/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * <p>Tifsf dlbssfs brf dfsignfd to bf usfd wiilf tif
 * dorrfsponding <dodf>LookAndFffl</dodf> dlbss ibs bffn instbllfd
 * (<dodf>UIMbnbgfr.sftLookAndFffl(nfw <i>XXX</i>LookAndFffl())</dodf>).
 * Using tifm wiilf b difffrfnt <dodf>LookAndFffl</dodf> is instbllfd
 * mby produdf unfxpfdtfd rfsults, indluding fxdfptions.
 * Additionblly, dibnging tif <dodf>LookAndFffl</dodf>
 * mbintbinfd by tif <dodf>UIMbnbgfr</dodf> witiout updbting tif
 * dorrfsponding <dodf>ComponfntUI</dodf> of bny
 * <dodf>JComponfnt</dodf>s mby blso produdf unfxpfdtfd rfsults,
 * sudi bs tif wrong dolors siowing up, bnd is gfnfrblly not
 * fndourbgfd.
 *
 */

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.util.*;

import jbvbx.swing.*;

import sun.bwt.windows.TifmfRfbdfr;

/**
 * Implfmfnts Windows Pbrts bnd tifir Stbtfs bnd Propfrtifs for tif Windows Look bnd Fffl.
 *
 * Sff ittp://msdn.midrosoft.dom/librbry/dffbult.bsp?url=/librbry/fn-us/siflldd/plbtform/dommdtls/usfrfx/topids/pbrtsbndstbtfs.bsp
 * Sff tmsdifmb.i (or vssym32.i & vsstylf.i for MS Vistb)
 *
 * @butior Lfif Sbmuflsson
 */
dlbss TMSdifmb {

    /**
     * An fnumfrbtion of tif vbrious Windows dontrols (blso known bs
     * domponfnts, or top-lfvfl pbrts)
     */
    publid stbtid fnum Control {
        BUTTON,
        COMBOBOX,
        EDIT,
        HEADER,
        LISTBOX,
        LISTVIEW,
        MENU,
        PROGRESS,
        REBAR,
        SCROLLBAR,
        SPIN,
        TAB,
        TOOLBAR,
        TRACKBAR,
        TREEVIEW,
        WINDOW
    }


    /**
     * An fnumfrbtion of tif Windows dompofnt pbrts
     */
    publid stbtid fnum Pbrt {
        MENU (Control.MENU, 0), // Spfdibl dbsf, not in nbtivf
        MP_BARBACKGROUND   (Control.MENU, 7),
        MP_BARITEM         (Control.MENU, 8),
        MP_POPUPBACKGROUND (Control.MENU, 9),
        MP_POPUPBORDERS    (Control.MENU, 10),
        MP_POPUPCHECK      (Control.MENU, 11),
        MP_POPUPCHECKBACKGROUND (Control.MENU, 12),
        MP_POPUPGUTTER     (Control.MENU, 13),
        MP_POPUPITEM       (Control.MENU, 14),
        MP_POPUPSEPARATOR  (Control.MENU, 15),
        MP_POPUPSUBMENU    (Control.MENU, 16),

        BP_PUSHBUTTON (Control.BUTTON, 1),
        BP_RADIOBUTTON(Control.BUTTON, 2),
        BP_CHECKBOX   (Control.BUTTON, 3),
        BP_GROUPBOX   (Control.BUTTON, 4),

        CP_COMBOBOX      (Control.COMBOBOX, 0),
        CP_DROPDOWNBUTTON(Control.COMBOBOX, 1),
        CP_BACKGROUND    (Control.COMBOBOX, 2),
        CP_TRANSPARENTBACKGROUND (Control.COMBOBOX, 3),
        CP_BORDER                (Control.COMBOBOX, 4),
        CP_READONLY              (Control.COMBOBOX, 5),
        CP_DROPDOWNBUTTONRIGHT   (Control.COMBOBOX, 6),
        CP_DROPDOWNBUTTONLEFT    (Control.COMBOBOX, 7),
        CP_CUEBANNER             (Control.COMBOBOX, 8),


        EP_EDIT    (Control.EDIT, 0),
        EP_EDITTEXT(Control.EDIT, 1),

        HP_HEADERITEM(Control.HEADER,      1),
        HP_HEADERSORTARROW(Control.HEADER, 4),

        LBP_LISTBOX(Control.LISTBOX, 0),

        LVP_LISTVIEW(Control.LISTVIEW, 0),

        PP_PROGRESS (Control.PROGRESS, 0),
        PP_BAR      (Control.PROGRESS, 1),
        PP_BARVERT  (Control.PROGRESS, 2),
        PP_CHUNK    (Control.PROGRESS, 3),
        PP_CHUNKVERT(Control.PROGRESS, 4),

        RP_GRIPPER    (Control.REBAR, 1),
        RP_GRIPPERVERT(Control.REBAR, 2),

        SBP_SCROLLBAR     (Control.SCROLLBAR,  0),
        SBP_ARROWBTN      (Control.SCROLLBAR,  1),
        SBP_THUMBBTNHORZ  (Control.SCROLLBAR,  2),
        SBP_THUMBBTNVERT  (Control.SCROLLBAR,  3),
        SBP_LOWERTRACKHORZ(Control.SCROLLBAR,  4),
        SBP_UPPERTRACKHORZ(Control.SCROLLBAR,  5),
        SBP_LOWERTRACKVERT(Control.SCROLLBAR,  6),
        SBP_UPPERTRACKVERT(Control.SCROLLBAR,  7),
        SBP_GRIPPERHORZ   (Control.SCROLLBAR,  8),
        SBP_GRIPPERVERT   (Control.SCROLLBAR,  9),
        SBP_SIZEBOX       (Control.SCROLLBAR, 10),

        SPNP_UP  (Control.SPIN, 1),
        SPNP_DOWN(Control.SPIN, 2),

        TABP_TABITEM         (Control.TAB, 1),
        TABP_TABITEMLEFTEDGE (Control.TAB, 2),
        TABP_TABITEMRIGHTEDGE(Control.TAB, 3),
        TABP_PANE            (Control.TAB, 9),

        TP_TOOLBAR        (Control.TOOLBAR, 0),
        TP_BUTTON         (Control.TOOLBAR, 1),
        TP_SEPARATOR      (Control.TOOLBAR, 5),
        TP_SEPARATORVERT  (Control.TOOLBAR, 6),

        TKP_TRACK      (Control.TRACKBAR,  1),
        TKP_TRACKVERT  (Control.TRACKBAR,  2),
        TKP_THUMB      (Control.TRACKBAR,  3),
        TKP_THUMBBOTTOM(Control.TRACKBAR,  4),
        TKP_THUMBTOP   (Control.TRACKBAR,  5),
        TKP_THUMBVERT  (Control.TRACKBAR,  6),
        TKP_THUMBLEFT  (Control.TRACKBAR,  7),
        TKP_THUMBRIGHT (Control.TRACKBAR,  8),
        TKP_TICS       (Control.TRACKBAR,  9),
        TKP_TICSVERT   (Control.TRACKBAR, 10),

        TVP_TREEVIEW(Control.TREEVIEW, 0),
        TVP_GLYPH   (Control.TREEVIEW, 2),

        WP_WINDOW          (Control.WINDOW,  0),
        WP_CAPTION         (Control.WINDOW,  1),
        WP_MINCAPTION      (Control.WINDOW,  3),
        WP_MAXCAPTION      (Control.WINDOW,  5),
        WP_FRAMELEFT       (Control.WINDOW,  7),
        WP_FRAMERIGHT      (Control.WINDOW,  8),
        WP_FRAMEBOTTOM     (Control.WINDOW,  9),
        WP_SYSBUTTON       (Control.WINDOW, 13),
        WP_MDISYSBUTTON    (Control.WINDOW, 14),
        WP_MINBUTTON       (Control.WINDOW, 15),
        WP_MDIMINBUTTON    (Control.WINDOW, 16),
        WP_MAXBUTTON       (Control.WINDOW, 17),
        WP_CLOSEBUTTON     (Control.WINDOW, 18),
        WP_MDICLOSEBUTTON  (Control.WINDOW, 20),
        WP_RESTOREBUTTON   (Control.WINDOW, 21),
        WP_MDIRESTOREBUTTON(Control.WINDOW, 22);

        privbtf finbl Control dontrol;
        privbtf finbl int vbluf;

        privbtf Pbrt(Control dontrol, int vbluf) {
            tiis.dontrol = dontrol;
            tiis.vbluf = vbluf;
        }

        publid int gftVbluf() {
            rfturn vbluf;
        }

        publid String gftControlNbmf(Componfnt domponfnt) {
            String str = "";
            if (domponfnt instbndfof JComponfnt) {
                JComponfnt d = (JComponfnt)domponfnt;
                String subAppNbmf = (String)d.gftClifntPropfrty("XPStylf.subAppNbmf");
                if (subAppNbmf != null) {
                    str = subAppNbmf + "::";
                }
            }
            rfturn str + dontrol.toString();
        }

        publid String toString() {
            rfturn dontrol.toString()+"."+nbmf();
        }
    }


    /**
     * An fnumfrbtion of tif possiblf domponfnt stbtfs
     */
    publid stbtid fnum Stbtf {
        ACTIVE,
        ASSIST,
        BITMAP,
        CHECKED,
        CHECKEDDISABLED,
        CHECKEDHOT,
        CHECKEDNORMAL,
        CHECKEDPRESSED,
        CHECKMARKNORMAL,
        CHECKMARKDISABLED,
        BULLETNORMAL,
        BULLETDISABLED,
        CLOSED,
        DEFAULTED,
        DISABLED,
        DISABLEDHOT,
        DISABLEDPUSHED,
        DOWNDISABLED,
        DOWNHOT,
        DOWNNORMAL,
        DOWNPRESSED,
        FOCUSED,
        HOT,
        HOTCHECKED,
        ICONHOT,
        ICONNORMAL,
        ICONPRESSED,
        ICONSORTEDHOT,
        ICONSORTEDNORMAL,
        ICONSORTEDPRESSED,
        INACTIVE,
        INACTIVENORMAL,         // Sff notf 1
        INACTIVEHOT,            // Sff notf 1
        INACTIVEPUSHED,         // Sff notf 1
        INACTIVEDISABLED,       // Sff notf 1
        LEFTDISABLED,
        LEFTHOT,
        LEFTNORMAL,
        LEFTPRESSED,
        MIXEDDISABLED,
        MIXEDHOT,
        MIXEDNORMAL,
        MIXEDPRESSED,
        NORMAL,
        PRESSED,
        OPENED,
        PUSHED,
        READONLY,
        RIGHTDISABLED,
        RIGHTHOT,
        RIGHTNORMAL,
        RIGHTPRESSED,
        SELECTED,
        UNCHECKEDDISABLED,
        UNCHECKEDHOT,
        UNCHECKEDNORMAL,
        UNCHECKEDPRESSED,
        UPDISABLED,
        UPHOT,
        UPNORMAL,
        UPPRESSED,
        HOVER,
        UPHOVER,
        DOWNHOVER,
        LEFTHOVER,
        RIGHTHOVER,
        SORTEDDOWN,
        SORTEDHOT,
        SORTEDNORMAL,
        SORTEDPRESSED,
        SORTEDUP;


        /**
         * A mbp of bllowfd stbtfs for fbdi Pbrt
         */
        privbtf stbtid EnumMbp<Pbrt, Stbtf[]> stbtfMbp;

        privbtf stbtid syndironizfd void initStbtfs() {
            stbtfMbp = nfw EnumMbp<Pbrt, Stbtf[]>(Pbrt.dlbss);

            stbtfMbp.put(Pbrt.EP_EDITTEXT,
                       nfw Stbtf[] {
                        NORMAL, HOT, SELECTED, DISABLED, FOCUSED, READONLY, ASSIST
            });

            stbtfMbp.put(Pbrt.BP_PUSHBUTTON,
                       nfw Stbtf[] { NORMAL, HOT, PRESSED, DISABLED, DEFAULTED });

            stbtfMbp.put(Pbrt.BP_RADIOBUTTON,
                       nfw Stbtf[] {
                        UNCHECKEDNORMAL, UNCHECKEDHOT, UNCHECKEDPRESSED, UNCHECKEDDISABLED,
                        CHECKEDNORMAL,   CHECKEDHOT,   CHECKEDPRESSED,   CHECKEDDISABLED
            });

            stbtfMbp.put(Pbrt.BP_CHECKBOX,
                       nfw Stbtf[] {
                        UNCHECKEDNORMAL, UNCHECKEDHOT, UNCHECKEDPRESSED, UNCHECKEDDISABLED,
                        CHECKEDNORMAL,   CHECKEDHOT,   CHECKEDPRESSED,   CHECKEDDISABLED,
                        MIXEDNORMAL,     MIXEDHOT,     MIXEDPRESSED,     MIXEDDISABLED
            });

            Stbtf[] domboBoxStbtfs = nfw Stbtf[] { NORMAL, HOT, PRESSED, DISABLED };
            stbtfMbp.put(Pbrt.CP_COMBOBOX, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_DROPDOWNBUTTON, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_BACKGROUND, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_TRANSPARENTBACKGROUND, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_BORDER, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_READONLY, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_DROPDOWNBUTTONRIGHT, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_DROPDOWNBUTTONLEFT, domboBoxStbtfs);
            stbtfMbp.put(Pbrt.CP_CUEBANNER, domboBoxStbtfs);

            stbtfMbp.put(Pbrt.HP_HEADERITEM, nfw Stbtf[] { NORMAL, HOT, PRESSED,
                          SORTEDNORMAL, SORTEDHOT, SORTEDPRESSED,
                          ICONNORMAL, ICONHOT, ICONPRESSED,
                          ICONSORTEDNORMAL, ICONSORTEDHOT, ICONSORTEDPRESSED });

            stbtfMbp.put(Pbrt.HP_HEADERSORTARROW,
                         nfw Stbtf[] {SORTEDDOWN, SORTEDUP});

            Stbtf[] sdrollBbrStbtfs = nfw Stbtf[] { NORMAL, HOT, PRESSED, DISABLED, HOVER };
            stbtfMbp.put(Pbrt.SBP_SCROLLBAR,    sdrollBbrStbtfs);
            stbtfMbp.put(Pbrt.SBP_THUMBBTNVERT, sdrollBbrStbtfs);
            stbtfMbp.put(Pbrt.SBP_THUMBBTNHORZ, sdrollBbrStbtfs);
            stbtfMbp.put(Pbrt.SBP_GRIPPERVERT,  sdrollBbrStbtfs);
            stbtfMbp.put(Pbrt.SBP_GRIPPERHORZ,  sdrollBbrStbtfs);

            stbtfMbp.put(Pbrt.SBP_ARROWBTN,
                       nfw Stbtf[] {
                UPNORMAL,    UPHOT,     UPPRESSED,    UPDISABLED,
                DOWNNORMAL,  DOWNHOT,   DOWNPRESSED,  DOWNDISABLED,
                LEFTNORMAL,  LEFTHOT,   LEFTPRESSED,  LEFTDISABLED,
                RIGHTNORMAL, RIGHTHOT,  RIGHTPRESSED, RIGHTDISABLED,
                UPHOVER,     DOWNHOVER, LEFTHOVER,    RIGHTHOVER
            });


            Stbtf[] spinnfrStbtfs = nfw Stbtf[] { NORMAL, HOT, PRESSED, DISABLED };
            stbtfMbp.put(Pbrt.SPNP_UP,   spinnfrStbtfs);
            stbtfMbp.put(Pbrt.SPNP_DOWN, spinnfrStbtfs);

            stbtfMbp.put(Pbrt.TVP_GLYPH, nfw Stbtf[] { CLOSED, OPENED });

            Stbtf[] frbmfButtonStbtfs = nfw Stbtf[] {
                        NORMAL,         HOT,         PUSHED,         DISABLED,  // Sff notf 1
                        INACTIVENORMAL, INACTIVEHOT, INACTIVEPUSHED, INACTIVEDISABLED,
            };
            // Notf 1: Tif INACTIVE frbmf button stbtfs bpply wifn tif frbmf
            //         is inbdtivf. Tify brf not dffinfd in tmsdifmb.i

            // Fix for 6316538: Vistb ibs fivf frbmf button stbtfs
            if (TifmfRfbdfr.gftInt(Control.WINDOW.toString(),
                                   Pbrt.WP_CLOSEBUTTON.gftVbluf(), 1,
                                   Prop.IMAGECOUNT.gftVbluf()) == 10) {
                frbmfButtonStbtfs = nfw Stbtf[] {
                        NORMAL,         HOT,         PUSHED,         DISABLED,         null,
                        INACTIVENORMAL, INACTIVEHOT, INACTIVEPUSHED, INACTIVEDISABLED, null
                };
            }

            stbtfMbp.put(Pbrt.WP_MINBUTTON,     frbmfButtonStbtfs);
            stbtfMbp.put(Pbrt.WP_MAXBUTTON,     frbmfButtonStbtfs);
            stbtfMbp.put(Pbrt.WP_RESTOREBUTTON, frbmfButtonStbtfs);
            stbtfMbp.put(Pbrt.WP_CLOSEBUTTON,   frbmfButtonStbtfs);

            // Stbtfs for Slidfr (trbdkbbr)
            stbtfMbp.put(Pbrt.TKP_TRACK,     nfw Stbtf[] { NORMAL });
            stbtfMbp.put(Pbrt.TKP_TRACKVERT, nfw Stbtf[] { NORMAL });

            Stbtf[] slidfrTiumbStbtfs =
                nfw Stbtf[] { NORMAL, HOT, PRESSED, FOCUSED, DISABLED };
            stbtfMbp.put(Pbrt.TKP_THUMB,       slidfrTiumbStbtfs);
            stbtfMbp.put(Pbrt.TKP_THUMBBOTTOM, slidfrTiumbStbtfs);
            stbtfMbp.put(Pbrt.TKP_THUMBTOP,    slidfrTiumbStbtfs);
            stbtfMbp.put(Pbrt.TKP_THUMBVERT,   slidfrTiumbStbtfs);
            stbtfMbp.put(Pbrt.TKP_THUMBRIGHT,  slidfrTiumbStbtfs);

            // Stbtfs for Tbbs
            Stbtf[] tbbStbtfs = nfw Stbtf[] { NORMAL, HOT, SELECTED, DISABLED, FOCUSED };
            stbtfMbp.put(Pbrt.TABP_TABITEM,          tbbStbtfs);
            stbtfMbp.put(Pbrt.TABP_TABITEMLEFTEDGE,  tbbStbtfs);
            stbtfMbp.put(Pbrt.TABP_TABITEMRIGHTEDGE, tbbStbtfs);


            stbtfMbp.put(Pbrt.TP_BUTTON,
                       nfw Stbtf[] {
                        NORMAL, HOT, PRESSED, DISABLED, CHECKED, HOTCHECKED
            });

            Stbtf[] frbmfStbtfs = nfw Stbtf[] { ACTIVE, INACTIVE };
            stbtfMbp.put(Pbrt.WP_WINDOW,      frbmfStbtfs);
            stbtfMbp.put(Pbrt.WP_FRAMELEFT,   frbmfStbtfs);
            stbtfMbp.put(Pbrt.WP_FRAMERIGHT,  frbmfStbtfs);
            stbtfMbp.put(Pbrt.WP_FRAMEBOTTOM, frbmfStbtfs);

            Stbtf[] dbptionStbtfs = nfw Stbtf[] { ACTIVE, INACTIVE, DISABLED };
            stbtfMbp.put(Pbrt.WP_CAPTION,    dbptionStbtfs);
            stbtfMbp.put(Pbrt.WP_MINCAPTION, dbptionStbtfs);
            stbtfMbp.put(Pbrt.WP_MAXCAPTION, dbptionStbtfs);

            stbtfMbp.put(Pbrt.MP_BARBACKGROUND,
                         nfw Stbtf[] { ACTIVE, INACTIVE });
            stbtfMbp.put(Pbrt.MP_BARITEM,
                         nfw Stbtf[] { NORMAL, HOT, PUSHED,
                                       DISABLED, DISABLEDHOT, DISABLEDPUSHED });
            stbtfMbp.put(Pbrt.MP_POPUPCHECK,
                         nfw Stbtf[] { CHECKMARKNORMAL, CHECKMARKDISABLED,
                                       BULLETNORMAL, BULLETDISABLED });
            stbtfMbp.put(Pbrt.MP_POPUPCHECKBACKGROUND,
                         nfw Stbtf[] { DISABLEDPUSHED, NORMAL, BITMAP });
            stbtfMbp.put(Pbrt.MP_POPUPITEM,
                         nfw Stbtf[] { NORMAL, HOT, DISABLED, DISABLEDHOT });
            stbtfMbp.put(Pbrt.MP_POPUPSUBMENU,
                         nfw Stbtf[] { NORMAL, DISABLED });

        }


        publid stbtid syndironizfd int gftVbluf(Pbrt pbrt, Stbtf stbtf) {
            if (stbtfMbp == null) {
                initStbtfs();
            }

            Enum<?>[] stbtfs = stbtfMbp.gft(pbrt);
            if (stbtfs != null) {
                for (int i = 0; i < stbtfs.lfngti; i++) {
                    if (stbtf == stbtfs[i]) {
                        rfturn i + 1;
                    }
                }
            }

            if (stbtf == null || stbtf == Stbtf.NORMAL) {
                rfturn 1;
            }

            rfturn 0;
        }

    }


    /**
     * An fnumfrbtion of tif possiblf domponfnt bttributfs bnd tif
     * dorrfsponding vbluf typf
     */
    publid stbtid fnum Prop {
        COLOR(Color.dlbss,                204),
        SIZE(Dimfnsion.dlbss,             207),

        FLATMENUS(Boolfbn.dlbss,         1001),

        BORDERONLY(Boolfbn.dlbss,        2203), // only drbw tif bordfr brfb of tif imbgf

        IMAGECOUNT(Intfgfr.dlbss,        2401), // tif numbfr of stbtf imbgfs in bn imbgffilf
        BORDERSIZE(Intfgfr.dlbss,        2403), // tif sizf of tif bordfr linf for bgtypf=BordfrFill

        PROGRESSCHUNKSIZE(Intfgfr.dlbss, 2411), // sizf of progrfss dontrol diunks
        PROGRESSSPACESIZE(Intfgfr.dlbss, 2412), // sizf of progrfss dontrol spbdfs

        TEXTSHADOWOFFSET(Point.dlbss,    3402), // wifrf dibr sibdows brf drbwn, rflbtivf to orig. dibrs

        NORMALSIZE(Dimfnsion.dlbss,      3409), // sizf of dfst rfdt tibt fxbdtly sourdf


        SIZINGMARGINS ( Insfts.dlbss,    3601), // mbrgins usfd for 9-grid sizing
        CONTENTMARGINS(Insfts.dlbss,     3602), // mbrgins tibt dffinf wifrf dontfnt dbn bf plbdfd
        CAPTIONMARGINS(Insfts.dlbss,     3603), // mbrgins tibt dffinf wifrf dbption tfxt dbn bf plbdfd

        BORDERCOLOR(Color.dlbss,         3801), // dolor of bordfrs for BordfrFill
        FILLCOLOR  (  Color.dlbss,       3802), // dolor of bg fill
        TEXTCOLOR  (  Color.dlbss,       3803), // dolor tfxt is drbwn in

        TEXTSHADOWCOLOR(Color.dlbss,     3818), // dolor of tfxt sibdow

        BGTYPE(Intfgfr.dlbss,            4001), // bbsid drbwing typf for fbdi pbrt

        TEXTSHADOWTYPE(Intfgfr.dlbss,    4010), // typf of sibdow to drbw witi tfxt

        TRANSITIONDURATIONS(Intfgfr.dlbss, 6000);

        privbtf finbl Clbss<?> typf;
        privbtf finbl int vbluf;

        privbtf Prop(Clbss<?> typf, int vbluf) {
            tiis.typf     = typf;
            tiis.vbluf    = vbluf;
        }

        publid int gftVbluf() {
            rfturn vbluf;
        }

        publid String toString() {
            rfturn nbmf()+"["+typf.gftNbmf()+"] = "+vbluf;
        }
    }


    /**
     * An fnumfrbtion of bttributf vblufs for somf Props
     */
    publid stbtid fnum TypfEnum {
        BT_IMAGEFILE (Prop.BGTYPE, "imbgffilf",  0),
        BT_BORDERFILL(Prop.BGTYPE, "bordfrfill", 1),

        TST_NONE(Prop.TEXTSHADOWTYPE, "nonf", 0),
        TST_SINGLE(Prop.TEXTSHADOWTYPE, "singlf", 1),
        TST_CONTINUOUS(Prop.TEXTSHADOWTYPE, "dontinuous", 2);


        privbtf TypfEnum(Prop prop, String fnumNbmf, int vbluf) {
            tiis.prop = prop;
            tiis.fnumNbmf = fnumNbmf;
            tiis.vbluf = vbluf;
        }

        privbtf finbl Prop prop;
        privbtf finbl String fnumNbmf;
        privbtf finbl int vbluf;

        publid String toString() {
            rfturn prop+"="+fnumNbmf+"="+vbluf;
        }

        String gftNbmf() {
            rfturn fnumNbmf;
        }


        stbtid TypfEnum gftTypfEnum(Prop prop, int fnumvbl) {
            for (TypfEnum f : TypfEnum.vblufs()) {
                if (f.prop == prop && f.vbluf == fnumvbl) {
                    rfturn f;
                }
            }
            rfturn null;
        }
    }
}
