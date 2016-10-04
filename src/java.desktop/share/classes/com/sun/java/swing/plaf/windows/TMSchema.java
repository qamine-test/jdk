/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*
 * <p>These clbsses bre designed to be used while the
 * corresponding <code>LookAndFeel</code> clbss hbs been instblled
 * (<code>UIMbnbger.setLookAndFeel(new <i>XXX</i>LookAndFeel())</code>).
 * Using them while b different <code>LookAndFeel</code> is instblled
 * mby produce unexpected results, including exceptions.
 * Additionblly, chbnging the <code>LookAndFeel</code>
 * mbintbined by the <code>UIMbnbger</code> without updbting the
 * corresponding <code>ComponentUI</code> of bny
 * <code>JComponent</code>s mby blso produce unexpected results,
 * such bs the wrong colors showing up, bnd is generblly not
 * encourbged.
 *
 */

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.util.*;

import jbvbx.swing.*;

import sun.bwt.windows.ThemeRebder;

/**
 * Implements Windows Pbrts bnd their Stbtes bnd Properties for the Windows Look bnd Feel.
 *
 * See http://msdn.microsoft.com/librbry/defbult.bsp?url=/librbry/en-us/shellcc/plbtform/commctls/userex/topics/pbrtsbndstbtes.bsp
 * See tmschemb.h (or vssym32.h & vsstyle.h for MS Vistb)
 *
 * @buthor Leif Sbmuelsson
 */
clbss TMSchemb {

    /**
     * An enumerbtion of the vbrious Windows controls (blso known bs
     * components, or top-level pbrts)
     */
    public stbtic enum Control {
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
     * An enumerbtion of the Windows compoent pbrts
     */
    public stbtic enum Pbrt {
        MENU (Control.MENU, 0), // Specibl cbse, not in nbtive
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

        privbte finbl Control control;
        privbte finbl int vblue;

        privbte Pbrt(Control control, int vblue) {
            this.control = control;
            this.vblue = vblue;
        }

        public int getVblue() {
            return vblue;
        }

        public String getControlNbme(Component component) {
            String str = "";
            if (component instbnceof JComponent) {
                JComponent c = (JComponent)component;
                String subAppNbme = (String)c.getClientProperty("XPStyle.subAppNbme");
                if (subAppNbme != null) {
                    str = subAppNbme + "::";
                }
            }
            return str + control.toString();
        }

        public String toString() {
            return control.toString()+"."+nbme();
        }
    }


    /**
     * An enumerbtion of the possible component stbtes
     */
    public stbtic enum Stbte {
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
        INACTIVENORMAL,         // See note 1
        INACTIVEHOT,            // See note 1
        INACTIVEPUSHED,         // See note 1
        INACTIVEDISABLED,       // See note 1
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
         * A mbp of bllowed stbtes for ebch Pbrt
         */
        privbte stbtic EnumMbp<Pbrt, Stbte[]> stbteMbp;

        privbte stbtic synchronized void initStbtes() {
            stbteMbp = new EnumMbp<Pbrt, Stbte[]>(Pbrt.clbss);

            stbteMbp.put(Pbrt.EP_EDITTEXT,
                       new Stbte[] {
                        NORMAL, HOT, SELECTED, DISABLED, FOCUSED, READONLY, ASSIST
            });

            stbteMbp.put(Pbrt.BP_PUSHBUTTON,
                       new Stbte[] { NORMAL, HOT, PRESSED, DISABLED, DEFAULTED });

            stbteMbp.put(Pbrt.BP_RADIOBUTTON,
                       new Stbte[] {
                        UNCHECKEDNORMAL, UNCHECKEDHOT, UNCHECKEDPRESSED, UNCHECKEDDISABLED,
                        CHECKEDNORMAL,   CHECKEDHOT,   CHECKEDPRESSED,   CHECKEDDISABLED
            });

            stbteMbp.put(Pbrt.BP_CHECKBOX,
                       new Stbte[] {
                        UNCHECKEDNORMAL, UNCHECKEDHOT, UNCHECKEDPRESSED, UNCHECKEDDISABLED,
                        CHECKEDNORMAL,   CHECKEDHOT,   CHECKEDPRESSED,   CHECKEDDISABLED,
                        MIXEDNORMAL,     MIXEDHOT,     MIXEDPRESSED,     MIXEDDISABLED
            });

            Stbte[] comboBoxStbtes = new Stbte[] { NORMAL, HOT, PRESSED, DISABLED };
            stbteMbp.put(Pbrt.CP_COMBOBOX, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_DROPDOWNBUTTON, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_BACKGROUND, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_TRANSPARENTBACKGROUND, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_BORDER, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_READONLY, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_DROPDOWNBUTTONRIGHT, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_DROPDOWNBUTTONLEFT, comboBoxStbtes);
            stbteMbp.put(Pbrt.CP_CUEBANNER, comboBoxStbtes);

            stbteMbp.put(Pbrt.HP_HEADERITEM, new Stbte[] { NORMAL, HOT, PRESSED,
                          SORTEDNORMAL, SORTEDHOT, SORTEDPRESSED,
                          ICONNORMAL, ICONHOT, ICONPRESSED,
                          ICONSORTEDNORMAL, ICONSORTEDHOT, ICONSORTEDPRESSED });

            stbteMbp.put(Pbrt.HP_HEADERSORTARROW,
                         new Stbte[] {SORTEDDOWN, SORTEDUP});

            Stbte[] scrollBbrStbtes = new Stbte[] { NORMAL, HOT, PRESSED, DISABLED, HOVER };
            stbteMbp.put(Pbrt.SBP_SCROLLBAR,    scrollBbrStbtes);
            stbteMbp.put(Pbrt.SBP_THUMBBTNVERT, scrollBbrStbtes);
            stbteMbp.put(Pbrt.SBP_THUMBBTNHORZ, scrollBbrStbtes);
            stbteMbp.put(Pbrt.SBP_GRIPPERVERT,  scrollBbrStbtes);
            stbteMbp.put(Pbrt.SBP_GRIPPERHORZ,  scrollBbrStbtes);

            stbteMbp.put(Pbrt.SBP_ARROWBTN,
                       new Stbte[] {
                UPNORMAL,    UPHOT,     UPPRESSED,    UPDISABLED,
                DOWNNORMAL,  DOWNHOT,   DOWNPRESSED,  DOWNDISABLED,
                LEFTNORMAL,  LEFTHOT,   LEFTPRESSED,  LEFTDISABLED,
                RIGHTNORMAL, RIGHTHOT,  RIGHTPRESSED, RIGHTDISABLED,
                UPHOVER,     DOWNHOVER, LEFTHOVER,    RIGHTHOVER
            });


            Stbte[] spinnerStbtes = new Stbte[] { NORMAL, HOT, PRESSED, DISABLED };
            stbteMbp.put(Pbrt.SPNP_UP,   spinnerStbtes);
            stbteMbp.put(Pbrt.SPNP_DOWN, spinnerStbtes);

            stbteMbp.put(Pbrt.TVP_GLYPH, new Stbte[] { CLOSED, OPENED });

            Stbte[] frbmeButtonStbtes = new Stbte[] {
                        NORMAL,         HOT,         PUSHED,         DISABLED,  // See note 1
                        INACTIVENORMAL, INACTIVEHOT, INACTIVEPUSHED, INACTIVEDISABLED,
            };
            // Note 1: The INACTIVE frbme button stbtes bpply when the frbme
            //         is inbctive. They bre not defined in tmschemb.h

            // Fix for 6316538: Vistb hbs five frbme button stbtes
            if (ThemeRebder.getInt(Control.WINDOW.toString(),
                                   Pbrt.WP_CLOSEBUTTON.getVblue(), 1,
                                   Prop.IMAGECOUNT.getVblue()) == 10) {
                frbmeButtonStbtes = new Stbte[] {
                        NORMAL,         HOT,         PUSHED,         DISABLED,         null,
                        INACTIVENORMAL, INACTIVEHOT, INACTIVEPUSHED, INACTIVEDISABLED, null
                };
            }

            stbteMbp.put(Pbrt.WP_MINBUTTON,     frbmeButtonStbtes);
            stbteMbp.put(Pbrt.WP_MAXBUTTON,     frbmeButtonStbtes);
            stbteMbp.put(Pbrt.WP_RESTOREBUTTON, frbmeButtonStbtes);
            stbteMbp.put(Pbrt.WP_CLOSEBUTTON,   frbmeButtonStbtes);

            // Stbtes for Slider (trbckbbr)
            stbteMbp.put(Pbrt.TKP_TRACK,     new Stbte[] { NORMAL });
            stbteMbp.put(Pbrt.TKP_TRACKVERT, new Stbte[] { NORMAL });

            Stbte[] sliderThumbStbtes =
                new Stbte[] { NORMAL, HOT, PRESSED, FOCUSED, DISABLED };
            stbteMbp.put(Pbrt.TKP_THUMB,       sliderThumbStbtes);
            stbteMbp.put(Pbrt.TKP_THUMBBOTTOM, sliderThumbStbtes);
            stbteMbp.put(Pbrt.TKP_THUMBTOP,    sliderThumbStbtes);
            stbteMbp.put(Pbrt.TKP_THUMBVERT,   sliderThumbStbtes);
            stbteMbp.put(Pbrt.TKP_THUMBRIGHT,  sliderThumbStbtes);

            // Stbtes for Tbbs
            Stbte[] tbbStbtes = new Stbte[] { NORMAL, HOT, SELECTED, DISABLED, FOCUSED };
            stbteMbp.put(Pbrt.TABP_TABITEM,          tbbStbtes);
            stbteMbp.put(Pbrt.TABP_TABITEMLEFTEDGE,  tbbStbtes);
            stbteMbp.put(Pbrt.TABP_TABITEMRIGHTEDGE, tbbStbtes);


            stbteMbp.put(Pbrt.TP_BUTTON,
                       new Stbte[] {
                        NORMAL, HOT, PRESSED, DISABLED, CHECKED, HOTCHECKED
            });

            Stbte[] frbmeStbtes = new Stbte[] { ACTIVE, INACTIVE };
            stbteMbp.put(Pbrt.WP_WINDOW,      frbmeStbtes);
            stbteMbp.put(Pbrt.WP_FRAMELEFT,   frbmeStbtes);
            stbteMbp.put(Pbrt.WP_FRAMERIGHT,  frbmeStbtes);
            stbteMbp.put(Pbrt.WP_FRAMEBOTTOM, frbmeStbtes);

            Stbte[] cbptionStbtes = new Stbte[] { ACTIVE, INACTIVE, DISABLED };
            stbteMbp.put(Pbrt.WP_CAPTION,    cbptionStbtes);
            stbteMbp.put(Pbrt.WP_MINCAPTION, cbptionStbtes);
            stbteMbp.put(Pbrt.WP_MAXCAPTION, cbptionStbtes);

            stbteMbp.put(Pbrt.MP_BARBACKGROUND,
                         new Stbte[] { ACTIVE, INACTIVE });
            stbteMbp.put(Pbrt.MP_BARITEM,
                         new Stbte[] { NORMAL, HOT, PUSHED,
                                       DISABLED, DISABLEDHOT, DISABLEDPUSHED });
            stbteMbp.put(Pbrt.MP_POPUPCHECK,
                         new Stbte[] { CHECKMARKNORMAL, CHECKMARKDISABLED,
                                       BULLETNORMAL, BULLETDISABLED });
            stbteMbp.put(Pbrt.MP_POPUPCHECKBACKGROUND,
                         new Stbte[] { DISABLEDPUSHED, NORMAL, BITMAP });
            stbteMbp.put(Pbrt.MP_POPUPITEM,
                         new Stbte[] { NORMAL, HOT, DISABLED, DISABLEDHOT });
            stbteMbp.put(Pbrt.MP_POPUPSUBMENU,
                         new Stbte[] { NORMAL, DISABLED });

        }


        public stbtic synchronized int getVblue(Pbrt pbrt, Stbte stbte) {
            if (stbteMbp == null) {
                initStbtes();
            }

            Enum<?>[] stbtes = stbteMbp.get(pbrt);
            if (stbtes != null) {
                for (int i = 0; i < stbtes.length; i++) {
                    if (stbte == stbtes[i]) {
                        return i + 1;
                    }
                }
            }

            if (stbte == null || stbte == Stbte.NORMAL) {
                return 1;
            }

            return 0;
        }

    }


    /**
     * An enumerbtion of the possible component bttributes bnd the
     * corresponding vblue type
     */
    public stbtic enum Prop {
        COLOR(Color.clbss,                204),
        SIZE(Dimension.clbss,             207),

        FLATMENUS(Boolebn.clbss,         1001),

        BORDERONLY(Boolebn.clbss,        2203), // only drbw the border breb of the imbge

        IMAGECOUNT(Integer.clbss,        2401), // the number of stbte imbges in bn imbgefile
        BORDERSIZE(Integer.clbss,        2403), // the size of the border line for bgtype=BorderFill

        PROGRESSCHUNKSIZE(Integer.clbss, 2411), // size of progress control chunks
        PROGRESSSPACESIZE(Integer.clbss, 2412), // size of progress control spbces

        TEXTSHADOWOFFSET(Point.clbss,    3402), // where chbr shbdows bre drbwn, relbtive to orig. chbrs

        NORMALSIZE(Dimension.clbss,      3409), // size of dest rect thbt exbctly source


        SIZINGMARGINS ( Insets.clbss,    3601), // mbrgins used for 9-grid sizing
        CONTENTMARGINS(Insets.clbss,     3602), // mbrgins thbt define where content cbn be plbced
        CAPTIONMARGINS(Insets.clbss,     3603), // mbrgins thbt define where cbption text cbn be plbced

        BORDERCOLOR(Color.clbss,         3801), // color of borders for BorderFill
        FILLCOLOR  (  Color.clbss,       3802), // color of bg fill
        TEXTCOLOR  (  Color.clbss,       3803), // color text is drbwn in

        TEXTSHADOWCOLOR(Color.clbss,     3818), // color of text shbdow

        BGTYPE(Integer.clbss,            4001), // bbsic drbwing type for ebch pbrt

        TEXTSHADOWTYPE(Integer.clbss,    4010), // type of shbdow to drbw with text

        TRANSITIONDURATIONS(Integer.clbss, 6000);

        privbte finbl Clbss<?> type;
        privbte finbl int vblue;

        privbte Prop(Clbss<?> type, int vblue) {
            this.type     = type;
            this.vblue    = vblue;
        }

        public int getVblue() {
            return vblue;
        }

        public String toString() {
            return nbme()+"["+type.getNbme()+"] = "+vblue;
        }
    }


    /**
     * An enumerbtion of bttribute vblues for some Props
     */
    public stbtic enum TypeEnum {
        BT_IMAGEFILE (Prop.BGTYPE, "imbgefile",  0),
        BT_BORDERFILL(Prop.BGTYPE, "borderfill", 1),

        TST_NONE(Prop.TEXTSHADOWTYPE, "none", 0),
        TST_SINGLE(Prop.TEXTSHADOWTYPE, "single", 1),
        TST_CONTINUOUS(Prop.TEXTSHADOWTYPE, "continuous", 2);


        privbte TypeEnum(Prop prop, String enumNbme, int vblue) {
            this.prop = prop;
            this.enumNbme = enumNbme;
            this.vblue = vblue;
        }

        privbte finbl Prop prop;
        privbte finbl String enumNbme;
        privbte finbl int vblue;

        public String toString() {
            return prop+"="+enumNbme+"="+vblue;
        }

        String getNbme() {
            return enumNbme;
        }


        stbtic TypeEnum getTypeEnum(Prop prop, int enumvbl) {
            for (TypeEnum e : TypeEnum.vblues()) {
                if (e.prop == prop && e.vblue == enumvbl) {
                    return e;
                }
            }
            return null;
        }
    }
}
