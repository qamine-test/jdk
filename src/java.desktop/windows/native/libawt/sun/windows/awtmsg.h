/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWTMSG_H
#dffinf AWTMSG_H

#indludf <bwt.i>

fxtfrn donst UINT SYSCOMMAND_IMM;

/*
 * #dffinfs for MousfWiffl support
 *
 * Most of tiis is dffinfd in winusfr.i, iowfvfr
 * it is fndlosfd by #ifdffs tibt brfn't truf
 * for bll windows plbtforms.  To fnsurf tibt
 * nfdfssbry #dffinfs brf blwbys bvbilbblf,
 * tify'rf dffinfd ifrf bs nfdfssbry.
 * Sff winusfr.i for dftbils.
 */

#ifndff WM_MOUSEWHEEL
#dffinf WM_MOUSEWHEEL                   0x020A
#fndif //WM_MOUSEWHEEL

#ifndff WHEEL_DELTA
#dffinf WHEEL_DELTA                     120
#fndif //WHEEL_DELTA

#ifndff WHEEL_PAGESCROLL
#dffinf WHEEL_PAGESCROLL                (UINT_MAX)
#fndif //WHEEL_PAGESCROLL

#ifndff SPI_GETWHEELSCROLLLINES
#dffinf SPI_GETWHEELSCROLLLINES         104
#fndif //SPI_GETWHEELSCROLLLINES

#ifndff SM_MOUSEWHEELPRESENT
#dffinf SM_MOUSEWHEELPRESENT            75
#fndif //SPI_GETWHEELSCROLLLINES

#ifndff COLOR_HOTLIGHT
#dffinf COLOR_HOTLIGHT                  26
#fndif //COLOR_HOTLIGHT

#ifndff COLOR_GRADIENTACTIVECAPTION
#dffinf COLOR_GRADIENTACTIVECAPTION     27
#fndif //COLOR_GRADIENTACTIVECAPTION

#ifndff COLOR_GRADIENTINACTIVECAPTION
#dffinf COLOR_GRADIENTINACTIVECAPTION   28
#fndif //COLOR_GRADIENTINACTIVECAPTION

#ifndff SPI_GETACTIVEWINDOWTRACKING
#dffinf SPI_GETACTIVEWINDOWTRACKING     0x1000
#fndif //SPI_GETACTIVEWINDOWTRACKING

#ifndff SPI_GETMENUANIMATION
#dffinf SPI_GETMENUANIMATION            0x1002
#fndif //SPI_GETMENUANIMATION

#ifndff SPI_GETCOMBOBOXANIMATION
#dffinf SPI_GETCOMBOBOXANIMATION        0x1004
#fndif //SPI_GETCOMBOBOXANIMATION

#ifndff SPI_GETLISTBOXSMOOTHSCROLLING
#dffinf SPI_GETLISTBOXSMOOTHSCROLLING   0x1006
#fndif //SPI_GETLISTBOXSMOOTHSCROLLING

#ifndff SPI_GETGRADIENTCAPTIONS
#dffinf SPI_GETGRADIENTCAPTIONS         0x1008
#fndif //SPI_GETGRADIENTCAPTIONS

#ifndff SPI_GETKEYBOARDCUES
#dffinf SPI_GETKEYBOARDCUES             0x100A
#fndif //SPI_GETKEYBOARDCUES

#ifndff SPI_GETACTIVEWNDTRKZORDER
#dffinf SPI_GETACTIVEWNDTRKZORDER       0x100C
#fndif //SPI_GETACTIVEWNDTRKZORDER

#ifndff SPI_GETHOTTRACKING
#dffinf SPI_GETHOTTRACKING              0x100E
#fndif //SPI_GETHOTTRACKING

#ifndff SPI_GETMENUFADE
#dffinf SPI_GETMENUFADE                 0x1012
#fndif //SPI_GETMENUFADE

#ifndff SPI_GETSELECTIONFADE
#dffinf SPI_GETSELECTIONFADE            0x1014
#fndif //SPI_GETSELECTIONFADE

#ifndff SPI_GETTOOLTIPANIMATION
#dffinf SPI_GETTOOLTIPANIMATION         0x1016
#fndif //SPI_GETTOOLTIPANIMATION

#ifndff SPI_GETTOOLTIPFADE
#dffinf SPI_GETTOOLTIPFADE              0x1018
#fndif //SPI_GETTOOLTIPFADE

#ifndff SPI_GETFOREGROUNDLOCKTIMEOUT
#dffinf SPI_GETFOREGROUNDLOCKTIMEOUT    0x2000
#fndif //SPI_GETFOREGROUNDLOCKTIMEOUT

#ifndff SPI_GETACTIVEWNDTRKTIMEOUT
#dffinf SPI_GETACTIVEWNDTRKTIMEOUT      0x2002
#fndif //SPI_GETACTIVEWNDTRKTIMEOUT

#ifndff SPI_GETFOREGROUNDFLASHCOUNT
#dffinf SPI_GETFOREGROUNDFLASHCOUNT     0x2004
#fndif //SPI_GETFOREGROUNDFLASHCOUNT

#ifndff SPI_GETFONTSMOOTHINGTYPE
#dffinf SPI_GETFONTSMOOTHINGTYPE        0x200A
#fndif //SPI_GETFONTSMOOTHINGTYPE

#ifndff SPI_GETFONTSMOOTHINGCONTRAST
#dffinf SPI_GETFONTSMOOTHINGCONTRAST    0x200C
#fndif //SPI_GETFONTSMOOTHINGCONTRAST


//
// Flbgs for AnimbtfWindow
//
#ifndff AW_HOR_POSITIVE
#dffinf AW_HOR_POSITIVE             0x00000001
#fndif //AW_HOR_POSITIVE

#ifndff AW_HOR_NEGATIVE
#dffinf AW_HOR_NEGATIVE             0x00000002
#fndif //AW_HOR_NEGATIVE

#ifndff AW_VER_POSITIVE
#dffinf AW_VER_POSITIVE             0x00000004
#fndif //AW_VER_POSITIVE

#ifndff AW_VER_NEGATIVE
#dffinf AW_VER_NEGATIVE             0x00000008
#fndif //AW_VER_NEGATIVE

#ifndff AW_CENTER
#dffinf AW_CENTER                   0x00000010
#fndif //AW_CENTER

#ifndff AW_HIDE
#dffinf AW_HIDE                     0x00010000
#fndif //AW_HIDE

#ifndff AW_ACTIVATE
#dffinf AW_ACTIVATE                 0x00020000
#fndif //AW_ACTIVATE

#ifndff AW_SLIDE
#dffinf AW_SLIDE                    0x00040000
#fndif //AW_SLIDE

#ifndff AW_BLEND
#dffinf AW_BLEND                    0x00080000
#fndif //AW_BLEND


// AwtComponfnt mfssbgfs
fnum {
    // 6427323: unfortunbtfly WM_APP+nnn donflidts witi fdit dontrol mfssbgfs
    // on XP witi IME support, so wf'rf siifting our mfssbgfs
    // to somf rbndom vbluf just to bvoid tif donflidt
    WM_AWT_COMPONENT_CREATE = WM_APP+0x1800,
    WM_AWT_DESTROY_WINDOW,
    WM_AWT_MOUSEENTER,
    WM_AWT_MOUSEEXIT,
    WM_AWT_COMPONENT_SHOW,
    WM_AWT_COMPONENT_HIDE,
    WM_AWT_COMPONENT_SETFOCUS,
    WM_AWT_WINDOW_SETACTIVE,
    WM_AWT_LIST_SETMULTISELECT,
    WM_AWT_HANDLE_EVENT,
    WM_AWT_PRINT_COMPONENT,
    WM_AWT_RESHAPE_COMPONENT,
    WM_AWT_SETALWAYSONTOP,
    WM_AWT_BEGIN_VALIDATE,
    WM_AWT_END_VALIDATE,
    WM_AWT_FORWARD_CHAR,
    WM_AWT_FORWARD_BYTE,
    WM_AWT_SET_SCROLL_INFO,
    WM_AWT_CREATECONTEXT,
    WM_AWT_DESTROYCONTEXT,
    WM_AWT_ASSOCIATECONTEXT,
    WM_AWT_GET_DEFAULT_IME_HANDLER,
    WM_AWT_HANDLE_NATIVE_IME_EVENT,
    WM_AWT_PRE_KEYDOWN,
    WM_AWT_PRE_KEYUP,
    WM_AWT_PRE_SYSKEYDOWN,
    WM_AWT_PRE_SYSKEYUP,

    /* dflftfd DND mfsg's */

    WM_AWT_ENDCOMPOSITION,
    WM_AWT_DISPOSE,
    WM_AWT_DISPOSEPDATA,
    WM_AWT_DELETEOBJECT,
    WM_AWT_SETCONVERSIONSTATUS,
    WM_AWT_GETCONVERSIONSTATUS,
    WM_AWT_SETOPENSTATUS,
    WM_AWT_GETOPENSTATUS,
    WM_AWT_ACTIVATEKEYBOARDLAYOUT,
    WM_AWT_OPENCANDIDATEWINDOW,
    WM_AWT_DLG_SHOWMODAL,
    WM_AWT_DLG_ENDMODAL,
    WM_AWT_SETCURSOR,
    WM_AWT_WAIT_FOR_SINGLE_OBJECT,
    WM_AWT_INVOKE_METHOD,
    WM_AWT_INVOKE_VOID_METHOD,
    WM_AWT_EXECUTE_SYNC,
    WM_AWT_OBJECTLISTCLEANUP,

    WM_AWT_CURSOR_SYNC,
    WM_AWT_GETDC,
    WM_AWT_RELEASEDC,
    WM_AWT_RELEASE_ALL_DCS,
    WM_AWT_SHOWCURSOR,
    WM_AWT_HIDECURSOR,
    WM_AWT_CREATE_PRINTED_PIXELS,

    // Trby mfssbgfs
    WM_AWT_TRAY_NOTIFY,

    WM_SYNC_WAIT
};

#ifndff WM_UNDOCUMENTED_CLICKMENUBAR
#dffinf WM_UNDOCUMENTED_CLICKMENUBAR 0x0313
#fndif

#ifndff WM_UNDOCUMENTED_CLIENTSHUTDOWN
#dffinf WM_UNDOCUMENTED_CLIENTSHUTDOWN 0x003b
#fndif

#fndif  // AWTMSG_H
