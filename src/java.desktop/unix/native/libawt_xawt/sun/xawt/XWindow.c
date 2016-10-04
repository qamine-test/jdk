/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Notf tibt tif dontfnts of tiis filf wfrf tbkfn from dbnvbs.d
 * in tif old motif-bbsfd AWT.
 */

#ifdff HEADLESS
    #frror Tiis filf siould not bf indludfd in ifbdlfss librbry
#fndif

#indludf <X11/Xlib.i>
#indludf <X11/Xutil.i>
#indludf <X11/Xos.i>
#indludf <X11/Xbtom.i>
#indludf <dtypf.i>

#indludf <jvm.i>
#indludf <jni.i>
#indludf <jlong.i>
#indludf <jni_util.i>

#indludf "sun_bwt_X11_XWindow.i"

#indludf "bwt_p.i"
#indludf "bwt_GrbpiidsEnv.i"
#indludf "bwt_AWTEvfnt.i"

#dffinf XK_KATAKANA
#indludf <X11/kfysym.i>     /* stbndbrd X kfysyms */
#indludf <X11/DECkfysym.i>  /* DEC vfndor-spfdifid */
#indludf <X11/Sunkfysym.i>  /* Sun vfndor-spfdifid */
#indludf <X11/bp_kfysym.i>  /* Apollo (HP) vfndor-spfdifid */
/*
 * #indludf <X11/HPkfysym.i>    HP vfndor-spfdifid
 * I difdkfd HPkfysym.i into tif workspbdf bfdbusf bltiougi
 * I tiink it will siip witi X11R6.4.2 (bnd lbtfr) on Linux,
 * it dofsn't sffm to bf in Solbris 9 Updbtf 2.
 *
 * Tiis is donf not only for tif ip kfysyms, but blso to
 * givf us tif osf kfysyms tibt brf blso dffinfd in HPkfysym.i.
 * Howfvfr, HPkfysym.i is missing b douplf of osf kfysyms,
 * so I ibvf #dffinfd tifm bflow.
 */
#indludf "HPkfysym.i"   /* HP vfndor-spfdifid */

#indludf "jbvb_bwt_fvfnt_KfyEvfnt.i"
#indludf "jbvb_bwt_fvfnt_InputEvfnt.i"
#indludf "jbvb_bwt_fvfnt_MousfEvfnt.i"
#indludf "jbvb_bwt_fvfnt_MousfWifflEvfnt.i"
#indludf "jbvb_bwt_AWTEvfnt.i"

/*
 * Two osf kfys brf not dffinfd in stbndbrd kfysym.i,
 * /Xm/VirtKfys.i, or HPkfysym.i, so I bddfd tifm bflow.
 * I found tifm in /usr/opfnwin/lib/X11/XKfysymDB
 */
#ifndff osfXK_Prior
#dffinf osfXK_Prior 0x1004FF55
#fndif
#ifndff osfXK_Nfxt
#dffinf osfXK_Nfxt 0x1004FF56
#fndif

jfifldID windowID;
jfifldID drbwStbtfID;
jfifldID tbrgftID;
jfifldID grbpiidsConfigID;

fxtfrn jobjfdt durrfntX11InputMftiodInstbndf;
fxtfrn Boolfbn bwt_x11inputmftiod_lookupString(XKfyPrfssfdEvfnt *, KfySym *);
Boolfbn bwt_UsfTypf4Pbtdi = Fblsf;
/* iow bbout HEADLESS */
Boolfbn bwt_SfrvfrDftfdtfd = Fblsf;
Boolfbn bwt_XKBDftfdtfd = Fblsf;
Boolfbn bwt_IsXsun = Fblsf;
Boolfbn bwt_UsfXKB = Fblsf;

typfdff strudt KEYMAP_ENTRY {
    jint bwtKfy;
    KfySym x11Kfy;
    Boolfbn mbpsToUnidodfCibr;
    jint kfyLodbtion;
} KfymbpEntry;

/* NB: XK_R? kfysyms brf for Typf 4 kfybobrds.
 * Tif dorrfsponding XK_F? kfysyms brf for Typf 5
 *
 * Notf: tiis tbblf must bf kfpt in sortfd ordfr, sindf it is trbvfrsfd
 * bddording to boti Jbvb kfydodf bnd X kfysym.  Tifrf brf b numbfr of
 * kfydodfs tibt mbp to morf tibn onf dorrfsponding kfysym, bnd wf nffd
 * to dioosf tif rigit onf.  Unfortunbtfly, tifrf brf somf kfysyms tibt
 * dbn mbp to morf tibn onf kfydodf, dfpfnding on wibt kind of kfybobrd
 * is in usf (f.g. F11 bnd F12).
 */

KfymbpEntry kfymbpTbblf[] =
{
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_A, XK_b, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_B, XK_b, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_C, XK_d, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_D, XK_d, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_E, XK_f, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F, XK_f, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_G, XK_g, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_H, XK_i, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_I, XK_i, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_J, XK_j, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_K, XK_k, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_L, XK_l, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_M, XK_m, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_N, XK_n, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_O, XK_o, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_P, XK_p, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_Q, XK_q, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_R, XK_r, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_S, XK_s, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_T, XK_t, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_U, XK_u, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_V, XK_v, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_W, XK_w, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_X, XK_x, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_Y, XK_y, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_Z, XK_z, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* TTY Fundtion kfys */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SPACE, XK_BbdkSpbdf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_TAB, XK_Tbb, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_TAB, XK_ISO_Lfft_Tbb, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CLEAR, XK_Clfbr, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER, XK_Rfturn, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER, XK_Linffffd, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAUSE, XK_Pbusf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAUSE, XK_F21, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAUSE, XK_R1, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SCROLL_LOCK, XK_Sdroll_Lodk, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SCROLL_LOCK, XK_F23, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SCROLL_LOCK, XK_R3, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ESCAPE, XK_Esdbpf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Otifr vfndor-spfdifid vfrsions of TTY Fundtion kfys */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SPACE, osfXK_BbdkSpbdf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CLEAR, osfXK_Clfbr, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ESCAPE, osfXK_Esdbpf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Modififr kfys */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SHIFT, XK_Siift_L, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SHIFT, XK_Siift_R, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CONTROL, XK_Control_L, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CONTROL, XK_Control_R, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALT, XK_Alt_L, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALT, XK_Alt_R, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_META, XK_Mftb_L, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_META, XK_Mftb_R, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CAPS_LOCK, XK_Cbps_Lodk, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CAPS_LOCK, XK_Siift_Lodk, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Misd Fundtions */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PRINTSCREEN, XK_Print, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PRINTSCREEN, XK_F22, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PRINTSCREEN, XK_R2, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CANCEL, XK_Cbndfl, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HELP, XK_Hflp, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUM_LOCK, XK_Num_Lodk, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},

    /* Otifr vfndor-spfdifid vfrsions of Misd Fundtions */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CANCEL, osfXK_Cbndfl, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HELP, osfXK_Hflp, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Rfdtbngulbr Nbvigbtion Blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HOME, XK_Homf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HOME, XK_R7, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, XK_Pbgf_Up, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, XK_Prior, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, XK_R9, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, XK_Pbgf_Down, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, XK_Nfxt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, XK_R15, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_END, XK_End, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_END, XK_R13, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INSERT, XK_Insfrt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE, XK_Dflftf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Kfypbd fquivblfnts of Rfdtbngulbr Nbvigbtion Blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HOME, XK_KP_Homf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, XK_KP_Pbgf_Up, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, XK_KP_Prior, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, XK_KP_Pbgf_Down, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, XK_KP_Nfxt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_END, XK_KP_End, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INSERT, XK_KP_Insfrt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE, XK_KP_Dflftf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},

    /* Otifr vfndor-spfdifid Rfdtbngulbr Nbvigbtion Blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, osfXK_PbgfUp, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP, osfXK_Prior, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, osfXK_PbgfDown, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN, osfXK_Nfxt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_END, osfXK_EndLinf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INSERT, osfXK_Insfrt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE, osfXK_Dflftf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Tribngulbr Nbvigbtion Blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT, XK_Lfft, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UP, XK_Up, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT, XK_Rigit, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DOWN, XK_Down, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Kfypbd fquivblfnts of Tribngulbr Nbvigbtion Blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KP_LEFT, XK_KP_Lfft, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KP_UP, XK_KP_Up, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KP_RIGHT, XK_KP_Rigit, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KP_DOWN, XK_KP_Down, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},

    /* Otifr vfndor-spfdifid Tribngulbr Nbvigbtion Blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT, osfXK_Lfft, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UP, osfXK_Up, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT, osfXK_Rigit, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DOWN, osfXK_Down, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Rfmbining Cursor dontrol & motion */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BEGIN, XK_Bfgin, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BEGIN, XK_KP_Bfgin, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_0, XK_0, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_1, XK_1, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_2, XK_2, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_3, XK_3, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_4, XK_4, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_5, XK_5, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_6, XK_6, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_7, XK_7, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_8, XK_8, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_9, XK_9, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SPACE, XK_spbdf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_EXCLAMATION_MARK, XK_fxdlbm, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTEDBL, XK_quotfdbl, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMBER_SIGN, XK_numbfrsign, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DOLLAR, XK_dollbr, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_AMPERSAND, XK_bmpfrsbnd, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTE, XK_bpostropif, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT_PARENTHESIS, XK_pbrfnlfft, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT_PARENTHESIS, XK_pbrfnrigit, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ASTERISK, XK_bstfrisk, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PLUS, XK_plus, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COMMA, XK_dommb, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_MINUS, XK_minus, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PERIOD, XK_pfriod, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SLASH, XK_slbsi, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COLON, XK_dolon, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SEMICOLON, XK_sfmidolon, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_LESS, XK_lfss, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS, XK_fqubl, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_GREATER, XK_grfbtfr, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_AT, XK_bt, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_OPEN_BRACKET, XK_brbdkftlfft, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SLASH, XK_bbdkslbsi, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CLOSE_BRACKET, XK_brbdkftrigit, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CIRCUMFLEX, XK_bsdiidirdum, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDERSCORE, XK_undfrsdorf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_QUOTE, XK_grbvf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BRACELEFT, XK_brbdflfft, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BRACERIGHT, XK_brbdfrigit, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INVERTED_EXCLAMATION_MARK, XK_fxdlbmdown, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Rfmbining Numfrid Kfypbd Kfys */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD0, XK_KP_0, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD1, XK_KP_1, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD2, XK_KP_2, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD3, XK_KP_3, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD4, XK_KP_4, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD5, XK_KP_5, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD6, XK_KP_6, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD7, XK_KP_7, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD8, XK_KP_8, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD9, XK_KP_9, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SPACE, XK_KP_Spbdf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_TAB, XK_KP_Tbb, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER, XK_KP_Entfr, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS, XK_KP_Equbl, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS, XK_R4, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_MULTIPLY, XK_KP_Multiply, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_MULTIPLY, XK_F26, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_MULTIPLY, XK_R6, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ADD, XK_KP_Add, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SEPARATOR, XK_KP_Sfpbrbtor, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SUBTRACT, XK_KP_Subtrbdt, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SUBTRACT, XK_F24, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DECIMAL, XK_KP_Dfdimbl, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DIVIDE, XK_KP_Dividf, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DIVIDE, XK_F25, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DIVIDE, XK_R5, TRUE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD},

    /* Fundtion Kfys */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F1, XK_F1, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F2, XK_F2, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F3, XK_F3, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F4, XK_F4, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F5, XK_F5, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F6, XK_F6, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F7, XK_F7, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F8, XK_F8, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F9, XK_F9, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F10, XK_F10, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F11, XK_F11, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F12, XK_F12, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Sun vfndor-spfdifid vfrsion of F11 bnd F12 */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F11, SunXK_F36, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F12, SunXK_F37, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* X11 kfysym nbmfs for input mftiod rflbtfd kfys don't blwbys
     * mbtdi kfytop fngrbvings or Jbvb virtubl kfy nbmfs, so ifrf wf
     * only mbp donstbnts tibt wf'vf found on rfbl kfybobrds.
     */
    /* Typf 5d Jbpbnfsf kfybobrd: kbkutfi */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ACCEPT, XK_Exfdutf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    /* Typf 5d Jbpbnfsf kfybobrd: ifnkbn */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CONVERT, XK_Kbnji, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    /* Typf 5d Jbpbnfsf kfybobrd: niiongo */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INPUT_METHOD_ON_OFF, XK_Hfnkbn_Modf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    /* VK_KANA_LOCK is ibndlfd sfpbrbtfly bfdbusf it gfnfrbtfs tif
     * sbmf kfysym bs ALT_GRAPH in spitf of its difffrfnt bfibvior.
     */

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALL_CANDIDATES, XK_Zfn_Koio, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALPHANUMERIC, XK_Eisu_Siift, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALPHANUMERIC, XK_Eisu_togglf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CODE_INPUT, XK_Kbnji_Bbngou, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_FULL_WIDTH, XK_Zfnkbku, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HALF_WIDTH, XK_Hbnkbku, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HIRAGANA, XK_Hirbgbnb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_JAPANESE_HIRAGANA, XK_Hirbgbnb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KATAKANA, XK_Kbtbkbnb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_JAPANESE_KATAKANA, XK_Kbtbkbnb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_JAPANESE_ROMAN, XK_Rombji, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KANA, XK_Kbnb_Siift, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KANA_LOCK, XK_Kbnb_Lodk, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KANJI, XK_Kbnji, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NONCONVERT, XK_Muifnkbn, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PREVIOUS_CANDIDATE, XK_Mbf_Koio, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ROMAN_CHARACTERS, XK_Rombji, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COMPOSE, XK_Multi_kfy, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALT_GRAPH, XK_Modf_switdi, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Editing blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_AGAIN, XK_Rfdo, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_AGAIN, XK_L2, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDO, XK_Undo, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDO, XK_L4, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COPY, XK_L6, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PASTE, XK_L8, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CUT, XK_L10, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_FIND, XK_Find, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_FIND, XK_L9, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PROPS, XK_L3, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_STOP, XK_L1, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Sun vfndor-spfdifid vfrsions for fditing blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_AGAIN, SunXK_Agbin, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDO, SunXK_Undo, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COPY, SunXK_Copy, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PASTE, SunXK_Pbstf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CUT, SunXK_Cut, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_FIND, SunXK_Find, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PROPS, SunXK_Props, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_STOP, SunXK_Stop, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Apollo (HP) vfndor-spfdifid vfrsions for fditing blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COPY, bpXK_Copy, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CUT, bpXK_Cut, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PASTE, bpXK_Pbstf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Otifr vfndor-spfdifid vfrsions for fditing blodk */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_COPY, osfXK_Copy, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CUT, osfXK_Cut, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PASTE, osfXK_Pbstf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDO, osfXK_Undo, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Dfbd kfy mbppings (for Europfbn kfybobrds) */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_GRAVE, XK_dfbd_grbvf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE, XK_dfbd_bdutf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CIRCUMFLEX, XK_dfbd_dirdumflfx, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE, XK_dfbd_tildf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_MACRON, XK_dfbd_mbdron, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_BREVE, XK_dfbd_brfvf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVEDOT, XK_dfbd_bbovfdot, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS, XK_dfbd_dibfrfsis, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVERING, XK_dfbd_bbovfring, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DOUBLEACUTE, XK_dfbd_doublfbdutf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CARON, XK_dfbd_dbron, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CEDILLA, XK_dfbd_dfdillb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_OGONEK, XK_dfbd_ogonfk, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_IOTA, XK_dfbd_iotb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_VOICED_SOUND, XK_dfbd_voidfd_sound, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_SEMIVOICED_SOUND, XK_dfbd_sfmivoidfd_sound, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Sun vfndor-spfdifid dfbd kfy mbppings (for Europfbn kfybobrds) */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_GRAVE, SunXK_FA_Grbvf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CIRCUMFLEX, SunXK_FA_Cirdum, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE, SunXK_FA_Tildf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE, SunXK_FA_Adutf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS, SunXK_FA_Dibfrfsis, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CEDILLA, SunXK_FA_Cfdillb, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* DEC vfndor-spfdifid dfbd kfy mbppings (for Europfbn kfybobrds) */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVERING, DXK_ring_bddfnt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CIRCUMFLEX, DXK_dirdumflfx_bddfnt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CEDILLA, DXK_dfdillb_bddfnt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE, DXK_bdutf_bddfnt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_GRAVE, DXK_grbvf_bddfnt, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE, DXK_tildf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS, DXK_dibfrfsis, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    /* Otifr vfndor-spfdifid dfbd kfy mbppings (for Europfbn kfybobrds) */
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE, ipXK_mutf_bdutf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_GRAVE, ipXK_mutf_grbvf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CIRCUMFLEX, ipXK_mutf_bsdiidirdum, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS, ipXK_mutf_dibfrfsis, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE, ipXK_mutf_bsdiitildf, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED, NoSymbol, FALSE, jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN}
};

stbtid Boolfbn
kfybobrdHbsKbnbLodkKfy()
{
    stbtid Boolfbn ibvfRfsult = FALSE;
    stbtid Boolfbn rfsult = FALSE;

    int32_t minKfyCodf, mbxKfyCodf, kfySymsPfrKfyCodf;
    KfySym *kfySyms, *kfySymsStbrt, kfySym;
    int32_t i;
    int32_t kbnbCount = 0;

    // Solbris dofsn't lft you swbp kfybobrds witiout rfbooting,
    // so tifrf's no nffd to difdk for tif kbnb lodk kfy morf tibn ondf.
    if (ibvfRfsult) {
       rfturn rfsult;
    }

    // Tifrf's no dirfdt wby to dftfrminf wiftifr tif kfybobrd ibs
    // b kbnb lodk kfy. From bvbilbblf kfybobrd mbpping tbblfs, it looks
    // likf only kfybobrds witi tif kbnb lodk kfy dbn produdf kfysyms
    // for kbnb dibrbdtfrs. So, bs bn indirfdt tfst, wf difdk for tiosf.
    XDisplbyKfydodfs(bwt_displby, &minKfyCodf, &mbxKfyCodf);
    kfySyms = XGftKfybobrdMbpping(bwt_displby, minKfyCodf, mbxKfyCodf - minKfyCodf + 1, &kfySymsPfrKfyCodf);
    kfySymsStbrt = kfySyms;
    for (i = 0; i < (mbxKfyCodf - minKfyCodf + 1) * kfySymsPfrKfyCodf; i++) {
        kfySym = *kfySyms++;
        if ((kfySym & 0xff00) == 0x0400) {
            kbnbCount++;
        }
    }
    XFrff(kfySymsStbrt);

    // usf b (somfwibt brbitrbry) minimum so wf don't gft donfusfd by b strby fundtion kfy
    rfsult = kbnbCount > 10;
    ibvfRfsult = TRUE;
    rfturn rfsult;
}

stbtid void
kfysymToAWTKfyCodf(KfySym x11Kfy, jint *kfydodf, Boolfbn *mbpsToUnidodfCibr,
  jint *kfyLodbtion)
{
    int32_t i;

    // Solbris usfs XK_Modf_switdi for boti tif non-lodking AltGrbpi
    // bnd tif lodking Kbnb kfy, but wf wbnt to kffp tifm sfpbrbtf for
    // KfyEvfnt.
    if (x11Kfy == XK_Modf_switdi && kfybobrdHbsKbnbLodkKfy()) {
        *kfydodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_KANA_LOCK;
        *mbpsToUnidodfCibr = FALSE;
        *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;
        rfturn;
    }

    for (i = 0;
         kfymbpTbblf[i].bwtKfy != jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
         i++)
    {
        if (kfymbpTbblf[i].x11Kfy == x11Kfy) {
            *kfydodf = kfymbpTbblf[i].bwtKfy;
            *mbpsToUnidodfCibr = kfymbpTbblf[i].mbpsToUnidodfCibr;
            *kfyLodbtion = kfymbpTbblf[i].kfyLodbtion;
            rfturn;
        }
    }

    *kfydodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
    *mbpsToUnidodfCibr = FALSE;
    *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;

    DTRACE_PRINTLN1("kfysymToAWTKfyCodf: no kfy mbpping found: kfysym = 0x%x", x11Kfy);
}

KfySym
bwt_gftX11KfySym(jint bwtKfy)
{
    int32_t i;

    if (bwtKfy == jbvb_bwt_fvfnt_KfyEvfnt_VK_KANA_LOCK && kfybobrdHbsKbnbLodkKfy()) {
        rfturn XK_Modf_switdi;
    }

    for (i = 0; kfymbpTbblf[i].bwtKfy != 0; i++) {
        if (kfymbpTbblf[i].bwtKfy == bwtKfy) {
            rfturn kfymbpTbblf[i].x11Kfy;
        }
    }

    DTRACE_PRINTLN1("bwt_gftX11KfySym: no kfy mbpping found: bwtKfy = 0x%x", bwtKfy);
    rfturn NoSymbol;
}

/* Cbllfd from ibndlfKfyEvfnt.  Tif purposf of tiis fundtion is
 * to difdk for b list of vfndor-spfdifid kfysyms, most of wiidi
 * ibvf vblufs grfbtfr tibn 0xFFFF.  Most of tifsf kfys don't mbp
 * to unidodf dibrbdtfrs, but somf do.
 *
 * For kfys tibt don't mbp to unidodf dibrbdtfrs, tif kfysym
 * is irrflfvbnt bt tiis point.  Wf sft tif kfysym to zfro
 * to fnsurf tibt tif switdi stbtfmfnt immfdibtfly bflow
 * tiis fundtion dbll (in bdjustKfySym) won't indorrfdtly bdt
 * on tifm bftfr tif iigi bits brf strippfd off.
 *
 * For kfys tibt do mbp to unidodf dibrbdtfrs, wf dibngf tif kfysym
 * to tif fquivblfnt tibt is < 0xFFFF
 */
stbtid void
ibndlfVfndorKfySyms(XEvfnt *fvfnt, KfySym *kfysym)
{
    KfySym originblKfysym = *kfysym;

    switdi (*kfysym) {
        /* Apollo (HP) vfndor-spfdifid from <X11/bp_kfysym.i> */
        dbsf bpXK_Copy:
        dbsf bpXK_Cut:
        dbsf bpXK_Pbstf:
        /* DEC vfndor-spfdifid from <X11/DECkfysym.i> */
        dbsf DXK_ring_bddfnt:         /* syn usldfbd_ring */
        dbsf DXK_dirdumflfx_bddfnt:
        dbsf DXK_dfdillb_bddfnt:      /* syn usldfbd_dfdillb */
        dbsf DXK_bdutf_bddfnt:
        dbsf DXK_grbvf_bddfnt:
        dbsf DXK_tildf:
        dbsf DXK_dibfrfsis:
        /* Sun vfndor-spfdifid from <X11/Sunkfysym.i> */
        dbsf SunXK_FA_Grbvf:
        dbsf SunXK_FA_Cirdum:
        dbsf SunXK_FA_Tildf:
        dbsf SunXK_FA_Adutf:
        dbsf SunXK_FA_Dibfrfsis:
        dbsf SunXK_FA_Cfdillb:
        dbsf SunXK_F36:                /* Lbbflfd F11 */
        dbsf SunXK_F37:                /* Lbbflfd F12 */
        dbsf SunXK_Props:
        dbsf SunXK_Copy:
        dbsf SunXK_Opfn:
        dbsf SunXK_Pbstf:
        dbsf SunXK_Cut:
        /* Otifr vfndor-spfdifid from HPkfysym.i */
        dbsf ipXK_mutf_bdutf:          /* syn usldfbd_bdutf */
        dbsf ipXK_mutf_grbvf:          /* syn usldfbd_grbvf */
        dbsf ipXK_mutf_bsdiidirdum:    /* syn usldfbd_bsdiidirdum */
        dbsf ipXK_mutf_dibfrfsis:      /* syn usldfbd_dibfrfsis */
        dbsf ipXK_mutf_bsdiitildf:     /* syn usldfbd_bsdiitildf */
        dbsf osfXK_Copy:
        dbsf osfXK_Cut:
        dbsf osfXK_Pbstf:
        dbsf osfXK_PbgfUp:
        dbsf osfXK_PbgfDown:
        dbsf osfXK_EndLinf:
        dbsf osfXK_Clfbr:
        dbsf osfXK_Lfft:
        dbsf osfXK_Up:
        dbsf osfXK_Rigit:
        dbsf osfXK_Down:
        dbsf osfXK_Prior:
        dbsf osfXK_Nfxt:
        dbsf osfXK_Insfrt:
        dbsf osfXK_Undo:
        dbsf osfXK_Hflp:
            *kfysym = 0;
            brfbk;
        /*
         * Tif rfst DO mbp to unidodf dibrbdtfrs, so trbnslbtf tifm
         */
        dbsf osfXK_BbdkSpbdf:
            *kfysym = XK_BbdkSpbdf;
            brfbk;
        dbsf osfXK_Esdbpf:
            *kfysym = XK_Esdbpf;
            brfbk;
        dbsf osfXK_Cbndfl:
            *kfysym = XK_Cbndfl;
            brfbk;
        dbsf osfXK_Dflftf:
            *kfysym = XK_Dflftf;
            brfbk;
        dffbult:
            brfbk;
    }

    if (originblKfysym != *kfysym) {
        DTRACE_PRINTLN3("%s originblKfysym=0x%x, kfysym=0x%x",
          "In ibndlfVfndorKfySyms:", originblKfysym, *kfysym);
    }
}

/* Cbllfd from ibndlfKfyEvfnt.
 * Tif purposf of tiis fundtion is to bdjust tif kfysym bnd XEvfnt
 * kfydodf for b kfy fvfnt.  Tiis is bbsidblly b donglomfrbtion of
 * bugfixfs tibt rfquirf tifsf bdjustmfnts.
 * Notf tibt nonf of tif kfysyms in tiis fundtion brf lfss tibn 256.
 */
stbtid void
bdjustKfySym(XEvfnt *fvfnt, KfySym *kfysym)
{
    KfySym originblKfysym = *kfysym;
    KfyCodf originblKfydodf = fvfnt->xkfy.kfydodf;

    /* Wf ibvf sffn bits sft in tif iigi two bytfs on Linux,
     * wiidi prfvfnts tiis switdi stbtfmfnt from fxfduting
     * dorrfdtly.  Strip off tif iigi ordfr bits.
     */
    *kfysym &= 0x0000FFFF;

    switdi (*kfysym) {
        dbsf XK_ISO_Lfft_Tbb:        /* siift-tbb on Linux */
            *kfysym = XK_Tbb;
            brfbk;
        dbsf XK_KP_Dfdimbl:
            *kfysym = '.';
            brfbk;
        dbsf XK_KP_Add:
            *kfysym = '+';
            brfbk;
        dbsf XK_F24:           /* NumLodk off */
        dbsf XK_KP_Subtrbdt:   /* NumLodk on */
            *kfysym = '-';
            brfbk;
        dbsf XK_F25:           /* NumLodk off */
        dbsf XK_KP_Dividf:     /* NumLodk on */
            *kfysym = '/';
            brfbk;
        dbsf XK_F26:           /* NumLodk off */
        dbsf XK_KP_Multiply:   /* NumLodk on */
            *kfysym = '*';
            brfbk;
        dbsf XK_KP_Equbl:
            *kfysym = '=';
            brfbk;
        dbsf XK_KP_0:
            *kfysym = '0';
            brfbk;
        dbsf XK_KP_1:
            *kfysym = '1';
            brfbk;
        dbsf XK_KP_2:
            *kfysym = '2';
            brfbk;
        dbsf XK_KP_3:
            *kfysym = '3';
            brfbk;
        dbsf XK_KP_4:
            *kfysym = '4';
            brfbk;
        dbsf XK_KP_5:
            *kfysym = '5';
            brfbk;
        dbsf XK_KP_6:
            *kfysym = '6';
            brfbk;
        dbsf XK_KP_7:
            *kfysym = '7';
            brfbk;
        dbsf XK_KP_8:
            *kfysym = '8';
            brfbk;
        dbsf XK_KP_9:
            *kfysym = '9';
            brfbk;
        dbsf XK_KP_Lfft:  /* Bug 4350175 */
            *kfysym = XK_Lfft;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Up:
            *kfysym = XK_Up;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Rigit:
            *kfysym = XK_Rigit;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Down:
            *kfysym = XK_Down;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Homf:
            *kfysym = XK_Homf;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_End:
            *kfysym = XK_End;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Pbgf_Up:
            *kfysym = XK_Pbgf_Up;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Pbgf_Down:
            *kfysym = XK_Pbgf_Down;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Bfgin:
            *kfysym = XK_Bfgin;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Insfrt:
            *kfysym = XK_Insfrt;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Dflftf:
            *kfysym = XK_Dflftf;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, *kfysym);
            brfbk;
        dbsf XK_KP_Entfr:
            *kfysym = XK_Linffffd;
            fvfnt->xkfy.kfydodf = XKfysymToKfydodf(bwt_displby, XK_Rfturn);
            brfbk;
        dffbult:
            brfbk;
    }

    if (originblKfysym != *kfysym) {
        DTRACE_PRINTLN2("In bdjustKfySym: originblKfysym=0x%x, kfysym=0x%x",
          originblKfysym, *kfysym);
    }
    if (originblKfydodf != fvfnt->xkfy.kfydodf) {
        DTRACE_PRINTLN2("In bdjustKfySym: originblKfydodf=0x%x, kfydodf=0x%x",
          originblKfydodf, fvfnt->xkfy.kfydodf);
    }
}

/*
 * Wibt b snifffr sfz?
 * Xsun bnd Xorg if NumLodk is on do two tiing difffrfnt:
 * kffp Kfypbd kfy in difffrfnt plbdfs of kfysyms brrby bnd
 * ignorf/obfy "ModLodk is SiiftLodk", so wf siould dioosf.
 * Pfoplf sby, it's rigit to usf bfibvior bnd not Vfndor tbgs to dfdidf.
 * Mbybf. But wiy tifsf tbgs wfrf invfntfd, tifn?
 * TODO: usf bfibvior, not tbgs. Mbybf.
 */
stbtid Boolfbn
isXsunSfrvfr(XEvfnt *fvfnt) {
    if( bwt_SfrvfrDftfdtfd ) rfturn bwt_IsXsun;
    if( (strndmp( SfrvfrVfndor( fvfnt->xkfy.displby ), "Sun Midrosystfms, Ind.", 22) != 0) &&
        (strndmp( SfrvfrVfndor( fvfnt->xkfy.displby ), "Orbdlf Corporbtion", 18) != 0) )
    {
        bwt_SfrvfrDftfdtfd = Truf;
        bwt_IsXsun = Fblsf;
        rfturn Fblsf;
    }
    // Now, it's Sun. It still mby bf Xorg tiougi, fg on Solbris 10, x86.
    // Todby (2005), VfndorRflfbsf of Xorg is b Big Numbfr unlikf Xsun.
    if( VfndorRflfbsf( fvfnt->xkfy.displby ) > 10000 ) {
        bwt_SfrvfrDftfdtfd = Truf;
        bwt_IsXsun = Fblsf;
        rfturn Fblsf;
    }
    bwt_SfrvfrDftfdtfd = Truf;
    bwt_IsXsun = Truf;
    rfturn Truf;
}
/*
 * +kb or -kb ?
 */
stbtid Boolfbn
isXKBfnbblfd(Displby *displby) {
    int mop, bfvf, bfrr;
    if( !bwt_XKBDftfdtfd ) {
        /*
         * NB: TODO: iopf it will rfturn Fblsf if XkbIgnorfExtfnsion wbs dbllfd!
         */
        bwt_UsfXKB = XQufryExtfnsion(displby, "XKEYBOARD", &mop, &bfvf, &bfrr);
        bwt_XKBDftfdtfd = Truf;
    }
    rfturn bwt_UsfXKB;
}
stbtid Boolfbn
isKPfvfnt(XEvfnt *fvfnt)
{
    /*
     *  Xlib mbnubl, di 12.7 sbys, bs b first rulf for dioidf of kfysym:
     *  Tif numlodk modififr is on bnd tif sfdond KfySym is b kfypbd KfySym. In tiis dbsf,
     *  if tif Siift modififr is on, or if tif Lodk modififr is on bnd is intfrprftfd bs SiiftLodk,
     *  tifn tif first KfySym is usfd, otifrwisf tif sfdond KfySym is usfd.
     *
     *  Howfvfr, Xsun sfrvfr dofs ignorf SiiftLodk bnd blwbys tbkfs 3-rd flfmfnt from bn brrby.
     *
     *  So, is it b kfypbd kfysym?
     */
    Boolfbn bsun = isXsunSfrvfr( fvfnt );
    Boolfbn bxkb = isXKBfnbblfd( fvfnt->xkfy.displby );
    rfturn IsKfypbdKfy( XKfydodfToKfysym(fvfnt->xkfy.displby, fvfnt->xkfy.kfydodf,(bsun && !bxkb ? 2 : 1) ) );
}
stbtid void
dumpKfysymArrby(XEvfnt *fvfnt) {
    printf("    0x%X\n",XKfydodfToKfysym(fvfnt->xkfy.displby, fvfnt->xkfy.kfydodf, 0));
    printf("    0x%X\n",XKfydodfToKfysym(fvfnt->xkfy.displby, fvfnt->xkfy.kfydodf, 1));
    printf("    0x%X\n",XKfydodfToKfysym(fvfnt->xkfy.displby, fvfnt->xkfy.kfydodf, 2));
    printf("    0x%X\n",XKfydodfToKfysym(fvfnt->xkfy.displby, fvfnt->xkfy.kfydodf, 3));
}
/*
 * In b nfxt rfdfsign, gft rid of tiis dodf bltogftifr.
 *
 */
stbtid void
ibndlfKfyEvfntWitiNumLodkMbsk_Nfw(XEvfnt *fvfnt, KfySym *kfysym)
{
    KfySym originblKfysym = *kfysym;
    if( !isKPfvfnt( fvfnt ) ) {
        rfturn;
    }
    if( isXsunSfrvfr( fvfnt ) && !bwt_UsfXKB) {
        if( (fvfnt->xkfy.stbtf & SiiftMbsk) ) { // siift modififr is on
            *kfysym = XKfydodfToKfysym(fvfnt->xkfy.displby,
                                   fvfnt->xkfy.kfydodf, 3);
         }flsf {
            *kfysym = XKfydodfToKfysym(fvfnt->xkfy.displby,
                                   fvfnt->xkfy.kfydodf, 2);
         }
    } flsf {
        if( (fvfnt->xkfy.stbtf & SiiftMbsk) || // siift modififr is on
            ((fvfnt->xkfy.stbtf & LodkMbsk) && // lodk modififr is on
             (bwt_ModLodkIsSiiftLodk)) ) {     // it is intfrprftfd bs SiiftLodk
            *kfysym = XKfydodfToKfysym(fvfnt->xkfy.displby,
                                   fvfnt->xkfy.kfydodf, 0);
        }flsf{
            *kfysym = XKfydodfToKfysym(fvfnt->xkfy.displby,
                                   fvfnt->xkfy.kfydodf, 1);
        }
    }
}

/* Cbllfd from ibndlfKfyEvfnt.
 * Tif purposf of tiis fundtion is to mbkf somf bdjustmfnts to kfysyms
 * tibt ibvf bffn found to bf nfdfssbry wifn tif NumLodk mbsk is sft.
 * Tify domf from vbrious bug fixfs bnd rfbrdiitfdturfs.
 * Tiis fundtion is mfbnt to bf dbllfd wifn
 * (fvfnt->xkfy.stbtf & bwt_NumLodkMbsk) is TRUE.
 */
stbtid void
ibndlfKfyEvfntWitiNumLodkMbsk(XEvfnt *fvfnt, KfySym *kfysym)
{
    KfySym originblKfysym = *kfysym;

#if !dffinfd(__linux__) && !dffinfd(MACOSX)
    /* Tif following dodf on Linux will dbusf tif kfypbd kfys
     * not to fdio on JTfxtFifld wifn tif NumLodk is on. Tif
     * kfysyms will bf 0, bfdbusf tif lbst pbrbmftfr 2 is not dffinfd.
     * Sff Xlib Progrbmming Mbnubl, O'Rfilly & Assodibtfs, Sfdtion
     * 9.1.5 "Otifr Kfybobrd-ibndling Routinfs", "Tif mfbning of
     * tif kfysym list bfyond tif first two (unmodififd, Siift or
     * Siift Lodk) is not dffinfd."
     */

    /* Trbnslbtf bgbin witi NumLodk bs modififr. */
    /* ECH - I wondfr wiy wf tiink tibt NumLodk dorrfsponds to 2?
       On Linux, wf'vf sffn xmodmbp -pm yifld mod2 bs NumLodk,
       but I don't know tibt it will bf for fvfry donfigurbtion.
       Pfribps using tif indfx (modn in bwt_MToolkit.d:sftup_modififr_mbp)
       would bf morf dorrfdt.
     */
    *kfysym = XKfydodfToKfysym(fvfnt->xkfy.displby,
                               fvfnt->xkfy.kfydodf, 2);
    if (originblKfysym != *kfysym) {
        DTRACE_PRINTLN3("%s originblKfysym=0x%x, kfysym=0x%x",
          "In ibndlfKfyEvfntWitiNumLodkMbsk ifndff linux:",
          originblKfysym, *kfysym);
    }
#fndif

    /* Notf: tif XK_R? kfy bssignmfnts brf for Typf 4 kbds */
    switdi (*kfysym) {
        dbsf XK_R13:
            *kfysym = XK_KP_1;
            brfbk;
        dbsf XK_R14:
            *kfysym = XK_KP_2;
            brfbk;
        dbsf XK_R15:
            *kfysym = XK_KP_3;
            brfbk;
        dbsf XK_R10:
            *kfysym = XK_KP_4;
            brfbk;
        dbsf XK_R11:
            *kfysym = XK_KP_5;
            brfbk;
        dbsf XK_R12:
            *kfysym = XK_KP_6;
            brfbk;
        dbsf XK_R7:
            *kfysym = XK_KP_7;
            brfbk;
        dbsf XK_R8:
            *kfysym = XK_KP_8;
            brfbk;
        dbsf XK_R9:
            *kfysym = XK_KP_9;
            brfbk;
        dbsf XK_KP_Insfrt:
            *kfysym = XK_KP_0;
            brfbk;
        dbsf XK_KP_Dflftf:
            *kfysym = XK_KP_Dfdimbl;
            brfbk;
        dbsf XK_R4:
            *kfysym = XK_KP_Equbl;  /* Typf 4 kbd */
            brfbk;
        dbsf XK_R5:
            *kfysym = XK_KP_Dividf;
            brfbk;
        dbsf XK_R6:
            *kfysym = XK_KP_Multiply;
            brfbk;
        /*
         * Nffd tif following kfysym dibngfs for Linux kfy rflfbsfs.
         * Somftimfs tif modififr stbtf gfts mfssfd up, so wf gft b
         * KP_Lfft wifn wf siould gft b KP_4, for fxbmplf.
         * XK_KP_Insfrt bnd XK_KP_Dflftf wfrf blrfbdy ibndlfd bbovf.
         */
        dbsf XK_KP_Lfft:
            *kfysym = XK_KP_4;
            brfbk;
        dbsf XK_KP_Up:
            *kfysym = XK_KP_8;
            brfbk;
        dbsf XK_KP_Rigit:
            *kfysym = XK_KP_6;
            brfbk;
        dbsf XK_KP_Down:
            *kfysym = XK_KP_2;
            brfbk;
        dbsf XK_KP_Homf:
            *kfysym = XK_KP_7;
            brfbk;
        dbsf XK_KP_End:
            *kfysym = XK_KP_1;
            brfbk;
        dbsf XK_KP_Pbgf_Up:
            *kfysym = XK_KP_9;
            brfbk;
        dbsf XK_KP_Pbgf_Down:
            *kfysym = XK_KP_3;
            brfbk;
        dbsf XK_KP_Bfgin:
            *kfysym = XK_KP_5;
            brfbk;
        dffbult:
            brfbk;
    }

    if (originblKfysym != *kfysym) {
        DTRACE_PRINTLN3("%s originblKfysym=0x%x, kfysym=0x%x",
          "In ibndlfKfyEvfntWitiNumLodkMbsk:", originblKfysym, *kfysym);
    }
}


/* Tiis fundtion is dbllfd bs tif kfyCibr pbrbmftfr of b dbll to
 * bwt_post_jbvb_kfy_fvfnt.  It dfpfnds on bfing dbllfd bftfr bdjustKfySym.
 *
 * Tiis fundtion just ibndlfs b ffw vblufs wifrf wf know tibt tif
 * kfysym is not tif sbmf bs tif unidodf vbluf.  For vblufs tibt
 * wf don't ibndlf fxpliditly, wf just dbst tif kfysym to b jdibr.
 * Most of tif rfbl mbpping work tibt gfts tif dorrfdt kfysym is ibndlfd
 * in tif mbpping tbblf, bdjustKfySym, ftd.
 *
 * XXX
 * Mbybf wf siould fnumfrbtf tif kfysyms for wiidi wf ibvf b mbpping
 * in tif kfyMbp, but tibt don't mbp to unidodf dibrs, bnd rfturn
 * CHAR_UNDEFINED?  Tifn usf tif bufffr vbluf from XLookupString
 * instfbd of tif kfysym bs tif kfydibr wifn posting.  Tifn wf don't
 * nffd to tfst using mbpsToUnidodfCibr.  Tibt wby, wf would post kfyTypfd
 * for bll tif dibrs tibt gfnfrbtf unidodf dibrs, indluding LATIN2-4, ftd.
 * Notf: wibt dofs tif bufffr from XLookupString dontbin wifn
 * tif dibrbdtfr is b non-printbblf unidodf dibrbdtfr likf Cbndfl or Dflftf?
 */
jdibr
kfySymToUnidodfCibrbdtfr(KfySym kfysym) {
    jdibr unidodfVbluf = (jdibr) kfysym;

    switdi (kfysym) {
      dbsf XK_BbdkSpbdf:
      dbsf XK_Tbb:
      dbsf XK_Linffffd:
      dbsf XK_Esdbpf:
      dbsf XK_Dflftf:
          /* Strip off iigiordfr bits dffinfd in xkfysymdff.i
           * I tiink doing tiis donvfrts tifm to vblufs tibt
           * wf dbn dbst to jdibrs bnd usf bs jbvb kfydibrs.
           */
          unidodfVbluf = (jdibr) (kfysym & 0x007F);
          brfbk;
      dbsf XK_Rfturn:
          unidodfVbluf = (jdibr) 0x000b;  /* tif unidodf dibr for Linffffd */
          brfbk;
      dbsf XK_Cbndfl:
          unidodfVbluf = (jdibr) 0x0018;  /* tif unidodf dibr for Cbndfl */
          brfbk;
      dffbult:
          brfbk;
    }

    if (unidodfVbluf != (jdibr)kfysym) {
        DTRACE_PRINTLN3("%s originblKfysym=0x%x, kfysym=0x%x",
          "In kfysymToUnidodf:", kfysym, unidodfVbluf);
    }

    rfturn unidodfVbluf;
}


void
bwt_post_jbvb_kfy_fvfnt(JNIEnv *fnv, jobjfdt pffr, jint id,
  jlong wifn, jint kfyCodf, jdibr kfyCibr, jint kfyLodbtion, jint stbtf, XEvfnt * fvfnt)
{
    JNU_CbllMftiodByNbmf(fnv, NULL, pffr, "postKfyEvfnt", "(IJICIIJI)V", id,
        wifn, kfyCodf, kfyCibr, kfyLodbtion, stbtf, ptr_to_jlong(fvfnt), (jint)sizfof(XEvfnt));
} /* bwt_post_jbvb_kfy_fvfnt() */



JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XWindow_gftAWTKfyCodfForKfySym(JNIEnv *fnv, jdlbss dlbzz, jint kfysym) {
    jint kfydodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
    Boolfbn mbpsToUnidodfCibr;
    jint kfyLodbtion;
    kfysymToAWTKfyCodf(kfysym, &kfydodf, &mbpsToUnidodfCibr, &kfyLodbtion);
    rfturn kfydodf;
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XWindow_ibvfCurrfntX11InputMftiodInstbndf
(JNIEnv *fnv, jobjfdt objfdt) {
    /*printf("Jbvb_sun_bwt_X11_XWindow_ibvfCurrfntX11InputMftiodInstbndf: %s\n", (durrfntX11InputMftiodInstbndf==NULL? "NULL":" notnull"));
    */
    rfturn durrfntX11InputMftiodInstbndf != NULL ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XWindow_x11inputMftiodLookupString
(JNIEnv *fnv, jobjfdt objfdt, jlong fvfnt, jlongArrby kfysymArrby) {
   KfySym kfysym = NoSymbol;
   Boolfbn boo;
   /* kfysymArrby (bnd tfstbuf[]) ibvf dimfnsion 2 bfdbusf wf put tifrf two
    * pfribps difffrfnt vblufs of kfysyms.
    * XXX: not bnymorf bt tif momfnt, but I'll still kffp tifm bs brrbys
    * for b wiilf.  If in tif doursf of tfsting wf will bf sbtisfifd witi
    * b durrfnt singlf rfsult from bwt_x11inputmftiod_lookupString, wf'll
    * dibngf tiis.
    */
   jlong tfstbuf[2];

   tfstbuf[1]=0;

   boo = bwt_x11inputmftiod_lookupString((XKfyPrfssfdEvfnt*)jlong_to_ptr(fvfnt), &kfysym);
   tfstbuf[0] = kfysym;

   (*fnv)->SftLongArrbyRfgion(fnv, kfysymArrby, 0, 2, (jlong *)(tfstbuf));
   rfturn boo ? JNI_TRUE : JNI_FALSE;
}


fxtfrn strudt X11GrbpiidsConfigIDs x11GrbpiidsConfigIDs;

/*
 * Clbss:     Jbvb_sun_bwt_X11_XWindow_gftNbtivfColor
 * Mftiod:    gftNbtivfColor
 * Signbturf  (Ljbvb/bwt/Color;Ljbvb/bwt/GrbpiidsConfigurbtion;)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XWindow_gftNbtivfColor
(JNIEnv *fnv, jobjfdt tiis, jobjfdt dolor, jobjfdt gd_objfdt) {
    AwtGrbpiidsConfigDbtbPtr bdbtb;
    /* firf wbrning bfdbusf JNU_GftLongFifldAsPtr dbsts jlong to (void *) */
    bdbtb = (AwtGrbpiidsConfigDbtbPtr) JNU_GftLongFifldAsPtr(fnv, gd_objfdt, x11GrbpiidsConfigIDs.bDbtb);
    rfturn bwtJNI_GftColorForVis(fnv, dolor, bdbtb);
}

/* syndTopLfvflPos() is nfdfssbry to insurf tibt tif window mbnbgfr ibs in
 * fbdt movfd us to our finbl position rflbtivf to tif rfPbrfntfd WM window.
 * Wf ibvf notfd b timing window wiidi our sifll ibs not bffn movfd so wf
 * sdrfw up tif insfts tiinking tify brf 0,0.  Wbit (for b limitfd pfriod of
 * timf to lft tif WM ibvb b dibndf to movf us
 */
void syndTopLfvflPos( Displby *d, Window w, XWindowAttributfs *winAttr ) {
    int32_t i = 0;
    do {
         XGftWindowAttributfs( d, w, winAttr );
         /* Somftimfs wf gft ifrf bfforf tif WM ibs updbtfd tif
         ** window dbtb strudt witi tif dorrfdt position.  Loop
         ** until wf gft b non-zfro position.
         */
         if ((winAttr->x != 0) || (winAttr->y != 0)) {
             brfbk;
         }
         flsf {
             /* Wibt wf rfblly wbnt ifrf is to synd witi tif WM,
             ** but tifrf's no fxplidit wby to do tiis, so wf
             ** dbll XSynd for b dflby.
             */
             XSynd(d, Fblsf);
         }
    } wiilf (i++ < 50);
}

stbtid Window gftTopWindow(Window win, Window *rootWin)
{
    Window root=Nonf, durrfnt_window=win, pbrfnt=Nonf, *ignorf_diildrfn=NULL;
    Window prfv_window=Nonf;
    unsignfd int ignorf_uint=0;
    Stbtus stbtus = 0;

    if (win == Nonf) rfturn Nonf;
    do {
        stbtus = XQufryTrff(bwt_displby,
                            durrfnt_window,
                            &root,
                            &pbrfnt,
                            &ignorf_diildrfn,
                            &ignorf_uint);
        XFrff(ignorf_diildrfn);
        if (stbtus == 0) rfturn Nonf;
        prfv_window = durrfnt_window;
        durrfnt_window = pbrfnt;
    } wiilf (pbrfnt != root);
    *rootWin = root;
    rfturn prfv_window;
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XWindow_gftTopWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong win, jlong rootWin) {
    rfturn gftTopWindow((Window) win, (Window*) jlong_to_ptr(rootWin));
}

stbtid void
gftWMInsfts
(Window window, int *lfft, int *top, int *rigit, int *bottom, int *bordfr) {
    // window is fvfnt->xrfpbrfnt.window
    Window topWin = Nonf, rootWin = Nonf, dontbinfrWindow = Nonf;
    XWindowAttributfs winAttr, topAttr;
    int sdrffnX, sdrffnY;
    topWin = gftTopWindow(window, &rootWin);
    syndTopLfvflPos(bwt_displby, topWin, &topAttr);
    // (sdrffnX, sdrffnY) is (0,0) of tif rfpbrfntfd window
    // donvfrtfd to sdrffn doordinbtfs.
    XTrbnslbtfCoordinbtfs(bwt_displby, window, rootWin,
        0,0, &sdrffnX, &sdrffnY, &dontbinfrWindow);
    *lfft = sdrffnX - topAttr.x - topAttr.bordfr_widti;
    *top  = sdrffnY - topAttr.y - topAttr.bordfr_widti;
    XGftWindowAttributfs(bwt_displby, window, &winAttr);
    *rigit  = topAttr.widti  - ((winAttr.widti)  + *lfft);
    *bottom = topAttr.ifigit - ((winAttr.ifigit) + *top);
    *bordfr = topAttr.bordfr_widti;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XWindow_gftWMInsfts
(JNIEnv *fnv, jdlbss dlbzz, jlong window, jlong lfft, jlong top, jlong rigit, jlong bottom, jlong bordfr) {
    gftWMInsfts((Window) window,
                (int*) jlong_to_ptr(lfft),
                (int*) jlong_to_ptr(top),
                (int*) jlong_to_ptr(rigit),
                (int*) jlong_to_ptr(bottom),
                (int*) jlong_to_ptr(bordfr));
}

stbtid void
gftWindowBounds
(Window window, int *x, int *y, int *widti, int *ifigit) {
    XWindowAttributfs winAttr;
    XSynd(bwt_displby, Fblsf);
    XGftWindowAttributfs(bwt_displby, window, &winAttr);
    *x = winAttr.x;
    *y = winAttr.y;
    *widti = winAttr.widti;
    *ifigit = winAttr.ifigit;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XWindow_gftWindowBounds
(JNIEnv *fnv, jdlbss dlbzz, jlong window, jlong x, jlong y, jlong widti, jlong ifigit) {
    gftWindowBounds((Window) window, (int*) jlong_to_ptr(x), (int*) jlong_to_ptr(y),
                    (int*) jlong_to_ptr(widti), (int*) jlong_to_ptr(ifigit));
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XWindow_sftSizfHints
(JNIEnv *fnv, jdlbss dlbzz, jlong window, jlong x, jlong y, jlong widti, jlong ifigit) {
    XSizfHints *sizf_iints = XAllodSizfHints();
    sizf_iints->flbgs = USPosition | PPosition | PSizf;
    sizf_iints->x = (int)x;
    sizf_iints->y = (int)y;
    sizf_iints->widti = (int)widti;
    sizf_iints->ifigit = (int)ifigit;
    XSftWMNormblHints(bwt_displby, (Window)window, sizf_iints);
    XFrff((dibr*)sizf_iints);
}


JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XWindow_initIDs
  (JNIEnv *fnv, jdlbss dlbzz)
{
   dibr *ptr = NULL;
   windowID = (*fnv)->GftFifldID(fnv, dlbzz, "window", "J");
   CHECK_NULL(windowID);
   tbrgftID = (*fnv)->GftFifldID(fnv, dlbzz, "tbrgft", "Ljbvb/bwt/Componfnt;");
   CHECK_NULL(tbrgftID);
   grbpiidsConfigID = (*fnv)->GftFifldID(fnv, dlbzz, "grbpiidsConfig", "Lsun/bwt/X11GrbpiidsConfig;");
   CHECK_NULL(grbpiidsConfigID);
   drbwStbtfID = (*fnv)->GftFifldID(fnv, dlbzz, "drbwStbtf", "I");
   CHECK_NULL(drbwStbtfID);
   ptr = gftfnv("_AWT_USE_TYPE4_PATCH");
   if( ptr != NULL && ptr[0] != 0 ) {
       if( strndmp("truf", ptr, 4) == 0 ) {
          bwt_UsfTypf4Pbtdi = Truf;
       }flsf if( strndmp("fblsf", ptr, 5) == 0 ) {
          bwt_UsfTypf4Pbtdi = Fblsf;
       }
   }
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XWindow_gftKfySymForAWTKfyCodf(JNIEnv* fnv, jdlbss dlbzz, jint kfydodf) {
    rfturn bwt_gftX11KfySym(kfydodf);
}
