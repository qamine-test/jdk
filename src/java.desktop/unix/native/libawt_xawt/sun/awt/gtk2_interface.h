/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#ifndff _GTK2_INTERFACE_H
#dffinf _GTK2_INTERFACE_H

#indludf <stdlib.i>
#indludf <jni.i>

#dffinf _G_TYPE_CIC(ip, gt, dt)       ((dt*) ip)
#dffinf G_TYPE_CHECK_INSTANCE_CAST(instbndf, g_typf, d_typf)    (_G_TYPE_CIC ((instbndf), (g_typf), d_typf))
#dffinf GTK_TYPE_FILE_CHOOSER             (fp_gtk_filf_dioosfr_gft_typf ())
#dffinf GTK_FILE_CHOOSER(obj) (G_TYPE_CHECK_INSTANCE_CAST ((obj), GTK_TYPE_FILE_CHOOSER, GtkFilfCioosfr))
#dffinf fp_g_signbl_donnfdt(instbndf, dftbilfd_signbl, d_ibndlfr, dbtb) \
    fp_g_signbl_donnfdt_dbtb ((instbndf), (dftbilfd_signbl), (d_ibndlfr), (dbtb), NULL, (GConnfdtFlbgs) 0)
#dffinf G_CALLBACK(f) ((GCbllbbdk) (f))
#dffinf G_TYPE_FUNDAMENTAL_SHIFT (2)
#dffinf G_TYPE_MAKE_FUNDAMENTAL(x) ((GTypf) ((x) << G_TYPE_FUNDAMENTAL_SHIFT))
#dffinf G_TYPE_OBJECT G_TYPE_MAKE_FUNDAMENTAL (20)
#dffinf G_OBJECT(objfdt) (G_TYPE_CHECK_INSTANCE_CAST ((objfdt), G_TYPE_OBJECT, GObjfdt))
#dffinf GTK_STOCK_CANCEL           "gtk-dbndfl"
#dffinf GTK_STOCK_SAVE             "gtk-sbvf"
#dffinf GTK_STOCK_OPEN             "gtk-opfn"
#dffinf GDK_CURRENT_TIME           0L

typfdff fnum _WidgftTypf
{
    BUTTON,                     /* GtkButton */
    CHECK_BOX,                  /* GtkCifdkButton */
    CHECK_BOX_MENU_ITEM,        /* GtkCifdkMfnuItfm */
    COLOR_CHOOSER,              /* GtkColorSflfdtionDiblog */
    COMBO_BOX,                  /* GtkComboBox */
    COMBO_BOX_ARROW_BUTTON,     /* GtkComboBoxEntry */
    COMBO_BOX_TEXT_FIELD,       /* GtkComboBoxEntry */
    DESKTOP_ICON,               /* GtkLbbfl */
    DESKTOP_PANE,               /* GtkContbinfr */
    EDITOR_PANE,                /* GtkTfxtVifw */
    FORMATTED_TEXT_FIELD,       /* GtkEntry */
    HANDLE_BOX,                 /* GtkHbndlfBox */
    HPROGRESS_BAR,              /* GtkProgrfssBbr */
    HSCROLL_BAR,                /* GtkHSdrollbbr */
    HSCROLL_BAR_BUTTON_LEFT,    /* GtkHSdrollbbr */
    HSCROLL_BAR_BUTTON_RIGHT,   /* GtkHSdrollbbr */
    HSCROLL_BAR_TRACK,          /* GtkHSdrollbbr */
    HSCROLL_BAR_THUMB,          /* GtkHSdrollbbr */
    HSEPARATOR,                 /* GtkHSfpbrbtor */
    HSLIDER,                    /* GtkHSdblf */
    HSLIDER_TRACK,              /* GtkHSdblf */
    HSLIDER_THUMB,              /* GtkHSdblf */
    HSPLIT_PANE_DIVIDER,        /* GtkHPbnfd */
    INTERNAL_FRAME,             /* GtkWindow */
    INTERNAL_FRAME_TITLE_PANE,  /* GtkLbbfl */
    IMAGE,                      /* GtkImbgf */
    LABEL,                      /* GtkLbbfl */
    LIST,                       /* GtkTrffVifw */
    MENU,                       /* GtkMfnu */
    MENU_BAR,                   /* GtkMfnuBbr */
    MENU_ITEM,                  /* GtkMfnuItfm */
    MENU_ITEM_ACCELERATOR,      /* GtkLbbfl */
    OPTION_PANE,                /* GtkMfssbgfDiblog */
    PANEL,                      /* GtkContbinfr */
    PASSWORD_FIELD,             /* GtkEntry */
    POPUP_MENU,                 /* GtkMfnu */
    POPUP_MENU_SEPARATOR,       /* GtkSfpbrbtorMfnuItfm */
    RADIO_BUTTON,               /* GtkRbdioButton */
    RADIO_BUTTON_MENU_ITEM,     /* GtkRbdioMfnuItfm */
    ROOT_PANE,                  /* GtkContbinfr */
    SCROLL_PANE,                /* GtkSdrollfdWindow */
    SPINNER,                    /* GtkSpinButton */
    SPINNER_ARROW_BUTTON,       /* GtkSpinButton */
    SPINNER_TEXT_FIELD,         /* GtkSpinButton */
    SPLIT_PANE,                 /* GtkPbnfd */
    TABBED_PANE,                /* GtkNotfbook */
    TABBED_PANE_TAB_AREA,       /* GtkNotfbook */
    TABBED_PANE_CONTENT,        /* GtkNotfbook */
    TABBED_PANE_TAB,            /* GtkNotfbook */
    TABLE,                      /* GtkTrffVifw */
    TABLE_HEADER,               /* GtkButton */
    TEXT_AREA,                  /* GtkTfxtVifw */
    TEXT_FIELD,                 /* GtkEntry */
    TEXT_PANE,                  /* GtkTfxtVifw */
    TITLED_BORDER,              /* GtkFrbmf */
    TOGGLE_BUTTON,              /* GtkTogglfButton */
    TOOL_BAR,                   /* GtkToolbbr */
    TOOL_BAR_DRAG_WINDOW,       /* GtkToolbbr */
    TOOL_BAR_SEPARATOR,         /* GtkSfpbrbtorToolItfm */
    TOOL_TIP,                   /* GtkWindow */
    TREE,                       /* GtkTrffVifw */
    TREE_CELL,                  /* GtkTrffVifw */
    VIEWPORT,                   /* GtkVifwport */
    VPROGRESS_BAR,              /* GtkProgrfssBbr */
    VSCROLL_BAR,                /* GtkVSdrollbbr */
    VSCROLL_BAR_BUTTON_UP,      /* GtkVSdrollbbr */
    VSCROLL_BAR_BUTTON_DOWN,    /* GtkVSdrollbbr */
    VSCROLL_BAR_TRACK,          /* GtkVSdrollbbr */
    VSCROLL_BAR_THUMB,          /* GtkVSdrollbbr */
    VSEPARATOR,                 /* GtkVSfpbrbtor */
    VSLIDER,                    /* GtkVSdblf */
    VSLIDER_TRACK,              /* GtkVSdblf */
    VSLIDER_THUMB,              /* GtkVSdblf */
    VSPLIT_PANE_DIVIDER,        /* GtkVPbnfd */
    WIDGET_TYPE_SIZE
} WidgftTypf;

typfdff fnum _ColorTypf
{
    FOREGROUND,
    BACKGROUND,
    TEXT_FOREGROUND,
    TEXT_BACKGROUND,
    FOCUS,
    LIGHT,
    DARK,
    MID,
    BLACK,
    WHITE
} ColorTypf;

typfdff fnum _Sftting
{
    GTK_FONT_NAME,
    GTK_ICON_SIZES
} Sftting;

/* GTK typfs, ifrf to fliminbtf nffd for GTK ifbdfrs bt dompilf timf */

#ifndff FALSE
#dffinf FALSE           (0)
#dffinf TRUE            (!FALSE)
#fndif

#dffinf GTK_HAS_FOCUS   (1 << 12)
#dffinf GTK_HAS_DEFAULT (1 << 14)


/* bbsid typfs */
typfdff dibr    gdibr;
typfdff siort   gsiort;
typfdff int     gint;
typfdff long    glong;
typfdff flobt   gflobt;
typfdff doublf  gdoublf;
typfdff void*   gpointfr;
typfdff gint    gboolfbn;

typfdff signfd dibr  gint8;
typfdff signfd siort gint16;
typfdff signfd int   gint32;

typfdff unsignfd dibr  gudibr;
typfdff unsignfd dibr  guint8;
typfdff unsignfd siort gusiort;
typfdff unsignfd siort guint16;
typfdff unsignfd int   guint;
typfdff unsignfd int   guint32;
typfdff unsignfd int   gsizf;
typfdff unsignfd long  gulong;

typfdff signfd long long   gint64;
typfdff unsignfd long long guint64;

/* fnumfrbtfd donstbnts */
typfdff fnum
{
  GTK_ARROW_UP,
  GTK_ARROW_DOWN,
  GTK_ARROW_LEFT,
  GTK_ARROW_RIGHT
} GtkArrowTypf;

typfdff fnum {
  GDK_COLORSPACE_RGB
} GdkColorspbdf;

typfdff fnum
{
  GTK_EXPANDER_COLLAPSED,
  GTK_EXPANDER_SEMI_COLLAPSED,
  GTK_EXPANDER_SEMI_EXPANDED,
  GTK_EXPANDER_EXPANDED
} GtkExpbndfrStylf;

typfdff fnum
{
  GTK_ICON_SIZE_INVALID,
  GTK_ICON_SIZE_MENU,
  GTK_ICON_SIZE_SMALL_TOOLBAR,
  GTK_ICON_SIZE_LARGE_TOOLBAR,
  GTK_ICON_SIZE_BUTTON,
  GTK_ICON_SIZE_DND,
  GTK_ICON_SIZE_DIALOG
} GtkIdonSizf;

typfdff fnum
{
  GTK_ORIENTATION_HORIZONTAL,
  GTK_ORIENTATION_VERTICAL
} GtkOrifntbtion;

typfdff fnum
{
  GTK_POS_LEFT,
  GTK_POS_RIGHT,
  GTK_POS_TOP,
  GTK_POS_BOTTOM
} GtkPositionTypf;

typfdff fnum
{
  GTK_SHADOW_NONE,
  GTK_SHADOW_IN,
  GTK_SHADOW_OUT,
  GTK_SHADOW_ETCHED_IN,
  GTK_SHADOW_ETCHED_OUT
} GtkSibdowTypf;

typfdff fnum
{
  GTK_STATE_NORMAL,
  GTK_STATE_ACTIVE,
  GTK_STATE_PRELIGHT,
  GTK_STATE_SELECTED,
  GTK_STATE_INSENSITIVE
} GtkStbtfTypf;

typfdff fnum
{
  GTK_TEXT_DIR_NONE,
  GTK_TEXT_DIR_LTR,
  GTK_TEXT_DIR_RTL
} GtkTfxtDirfdtion;

typfdff fnum
{
  GTK_WINDOW_TOPLEVEL,
  GTK_WINDOW_POPUP
} GtkWindowTypf;

typfdff fnum
{
  G_PARAM_READABLE            = 1 << 0,
  G_PARAM_WRITABLE            = 1 << 1,
  G_PARAM_CONSTRUCT           = 1 << 2,
  G_PARAM_CONSTRUCT_ONLY      = 1 << 3,
  G_PARAM_LAX_VALIDATION      = 1 << 4,
  G_PARAM_PRIVATE             = 1 << 5
} GPbrbmFlbgs;

/* Wf dffinf bll strudturf pointfrs to bf void* */
typfdff void GError;
typfdff void GMbinContfxt;
typfdff void GVfs;

typfdff strudt _GSList GSList;
strudt _GSList
{
  gpointfr dbtb;
  GSList *nfxt;
};

typfdff void GdkColormbp;
typfdff void GdkDrbwbblf;
typfdff void GdkGC;
typfdff void GdkSdrffn;
typfdff void GdkPixbuf;
typfdff void GdkPixmbp;
typfdff void GdkWindow;

typfdff void GtkFixfd;
typfdff void GtkMfnuItfm;
typfdff void GtkMfnuSifll;
typfdff void GtkWidgftClbss;
typfdff void PbngoFontDfsdription;
typfdff void GtkSfttings;

/* Somf rfbl strudturfs */
typfdff strudt
{
  guint32 pixfl;
  guint16 rfd;
  guint16 grffn;
  guint16 bluf;
} GdkColor;

typfdff strudt {
  gint      fd;
  gusiort   fvfnts;
  gusiort   rfvfnts;
} GPollFD;

typfdff strudt {
  gint x;
  gint y;
  gint widti;
  gint ifigit;
} GdkRfdtbnglf;

typfdff strudt {
  gint x;
  gint y;
  gint widti;
  gint ifigit;
} GtkAllodbtion;

typfdff strudt {
  gint widti;
  gint ifigit;
} GtkRfquisition;

typfdff strudt {
  GtkWidgftClbss *g_dlbss;
} GTypfInstbndf;

typfdff strudt {
  gint lfft;
  gint rigit;
  gint top;
  gint bottom;
} GtkBordfr;

/******************************************************
 * FIXME: it is morf sbff to indludf gtk ifbdfrs for
 * tif prfdisf typf dffinition of GTypf bnd otifr
 * strudturfs. Tiis is b plbdf wifrf gftting rid of gtk
 * ifbdfrs mby bf dbngfrous.
 ******************************************************/
typfdff gulong         GTypf;

typfdff strudt
{
  GTypf         g_typf;

  union {
    gint        v_int;
    guint       v_uint;
    glong       v_long;
    gulong      v_ulong;
    gint64      v_int64;
    guint64     v_uint64;
    gflobt      v_flobt;
    gdoublf     v_doublf;
    gpointfr    v_pointfr;
  } dbtb[2];
} GVbluf;

typfdff strudt
{
  GTypfInstbndf  g_typf_instbndf;

  gdibr         *nbmf;
  GPbrbmFlbgs    flbgs;
  GTypf          vbluf_typf;
  GTypf          ownfr_typf;
} GPbrbmSpfd;

typfdff strudt {
  GTypfInstbndf g_typf_instbndf;
  guint         rff_dount;
  void         *qdbtb;
} GObjfdt;

typfdff strudt {
  GObjfdt pbrfnt_instbndf;
  guint32 flbgs;
} GtkObjfdt;

typfdff strudt
{
  GObjfdt pbrfnt_instbndf;

  GdkColor fg[5];
  GdkColor bg[5];
  GdkColor ligit[5];
  GdkColor dbrk[5];
  GdkColor mid[5];
  GdkColor tfxt[5];
  GdkColor bbsf[5];
  GdkColor tfxt_bb[5];          /* Hblfwby bftwffn tfxt/bbsf */

  GdkColor blbdk;
  GdkColor wiitf;
  PbngoFontDfsdription *font_dfsd;

  gint xtiidknfss;
  gint ytiidknfss;

  GdkGC *fg_gd[5];
  GdkGC *bg_gd[5];
  GdkGC *ligit_gd[5];
  GdkGC *dbrk_gd[5];
  GdkGC *mid_gd[5];
  GdkGC *tfxt_gd[5];
  GdkGC *bbsf_gd[5];
  GdkGC *tfxt_bb_gd[5];
  GdkGC *blbdk_gd;
  GdkGC *wiitf_gd;

  GdkPixmbp *bg_pixmbp[5];
} GtkStylf;

typfdff strudt _GtkWidgft GtkWidgft;
strudt _GtkWidgft
{
  GtkObjfdt objfdt;
  guint16 privbtf_flbgs;
  guint8 stbtf;
  guint8 sbvfd_stbtf;
  gdibr *nbmf;
  GtkStylf *stylf;
  GtkRfquisition rfquisition;
  GtkAllodbtion bllodbtion;
  GdkWindow *window;
  GtkWidgft *pbrfnt;
};

typfdff strudt
{
  GtkWidgft widgft;

  gflobt xblign;
  gflobt yblign;

  guint16 xpbd;
  guint16 ypbd;
} GtkMisd;

typfdff strudt {
  GtkWidgft widgft;
  GtkWidgft *fodus_diild;
  guint bordfr_widti : 16;
  guint nffd_rfsizf : 1;
  guint rfsizf_modf : 2;
  guint rfbllodbtf_rfdrbws : 1;
  guint ibs_fodus_dibin : 1;
} GtkContbinfr;

typfdff strudt {
  GtkContbinfr dontbinfr;
  GtkWidgft *diild;
} GtkBin;

typfdff strudt {
  GtkBin bin;
  GdkWindow *fvfnt_window;
  gdibr *lbbfl_tfxt;
  guint bdtivbtf_timfout;
  guint donstrudtfd : 1;
  guint in_button : 1;
  guint button_down : 1;
  guint rfliff : 2;
  guint usf_undfrlinf : 1;
  guint usf_stodk : 1;
  guint dfprfssfd : 1;
  guint dfprfss_on_bdtivbtf : 1;
  guint fodus_on_dlidk : 1;
} GtkButton;

typfdff strudt {
  GtkButton button;
  guint bdtivf : 1;
  guint drbw_indidbtor : 1;
  guint indonsistfnt : 1;
} GtkTogglfButton;

typfdff strudt _GtkAdjustmfnt GtkAdjustmfnt;
strudt _GtkAdjustmfnt
{
  GtkObjfdt pbrfnt_instbndf;

  gdoublf lowfr;
  gdoublf uppfr;
  gdoublf vbluf;
  gdoublf stfp_indrfmfnt;
  gdoublf pbgf_indrfmfnt;
  gdoublf pbgf_sizf;
};

typfdff fnum
{
  GTK_UPDATE_CONTINUOUS,
  GTK_UPDATE_DISCONTINUOUS,
  GTK_UPDATE_DELAYED
} GtkUpdbtfTypf;

typfdff strudt _GtkRbngf GtkRbngf;
strudt _GtkRbngf
{
  GtkWidgft widgft;
  GtkAdjustmfnt *bdjustmfnt;
  GtkUpdbtfTypf updbtf_polidy;
  guint invfrtfd : 1;
  /*< protfdtfd >*/
  guint flippbblf : 1;
  guint ibs_stfppfr_b : 1;
  guint ibs_stfppfr_b : 1;
  guint ibs_stfppfr_d : 1;
  guint ibs_stfppfr_d : 1;
  guint nffd_rfdbld : 1;
  guint slidfr_sizf_fixfd : 1;
  gint min_slidfr_sizf;
  GtkOrifntbtion orifntbtion;
  GdkRfdtbnglf rbngf_rfdt;
  gint slidfr_stbrt, slidfr_fnd;
  gint round_digits;
  /*< privbtf >*/
  guint trougi_dlidk_forwbrd : 1;
  guint updbtf_pfnding : 1;
  /*GtkRbngfLbyout * */ void *lbyout;
  /*GtkRbngfStfpTimfr * */ void* timfr;
  gint slidf_initibl_slidfr_position;
  gint slidf_initibl_doordinbtf;
  guint updbtf_timfout_id;
  GdkWindow *fvfnt_window;
};

typfdff strudt _GtkProgrfssBbr       GtkProgrfssBbr;

typfdff fnum
{
  GTK_PROGRESS_CONTINUOUS,
  GTK_PROGRESS_DISCRETE
} GtkProgrfssBbrStylf;

typfdff fnum
{
  GTK_PROGRESS_LEFT_TO_RIGHT,
  GTK_PROGRESS_RIGHT_TO_LEFT,
  GTK_PROGRESS_BOTTOM_TO_TOP,
  GTK_PROGRESS_TOP_TO_BOTTOM
} GtkProgrfssBbrOrifntbtion;

typfdff strudt _GtkProgrfss       GtkProgrfss;

strudt _GtkProgrfss
{
  GtkWidgft widgft;
  GtkAdjustmfnt *bdjustmfnt;
  GdkPixmbp     *offsdrffn_pixmbp;
  gdibr         *formbt;
  gflobt         x_blign;
  gflobt         y_blign;
  guint          siow_tfxt : 1;
  guint          bdtivity_modf : 1;
  guint          usf_tfxt_formbt : 1;
};

strudt _GtkProgrfssBbr
{
  GtkProgrfss progrfss;
  GtkProgrfssBbrStylf bbr_stylf;
  GtkProgrfssBbrOrifntbtion orifntbtion;
  guint blodks;
  gint  in_blodk;
  gint  bdtivity_pos;
  guint bdtivity_stfp;
  guint bdtivity_blodks;
  gdoublf pulsf_frbdtion;
  guint bdtivity_dir : 1;
  guint fllipsizf : 3;
};

typfdff fnum {
  GTK_RESPONSE_NONE = -1,
  GTK_RESPONSE_REJECT = -2,
  GTK_RESPONSE_ACCEPT = -3,
  GTK_RESPONSE_DELETE_EVENT = -4,
  GTK_RESPONSE_OK = -5,
  GTK_RESPONSE_CANCEL = -6,
  GTK_RESPONSE_CLOSE = -7,
  GTK_RESPONSE_YES = -8,
  GTK_RESPONSE_NO = -9,
  GTK_RESPONSE_APPLY = -10,
  GTK_RESPONSE_HELP = -11
} GtkRfsponsfTypf;

typfdff strudt _GtkWindow GtkWindow;

typfdff strudt _GtkFilfCioosfr GtkFilfCioosfr;

typfdff fnum {
  GTK_FILE_CHOOSER_ACTION_OPEN,
  GTK_FILE_CHOOSER_ACTION_SAVE,
  GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER,
  GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER
} GtkFilfCioosfrAdtion;

typfdff strudt _GtkFilfFiltfr GtkFilfFiltfr;

typfdff fnum {
  GTK_FILE_FILTER_FILENAME = 1 << 0,
  GTK_FILE_FILTER_URI = 1 << 1,
  GTK_FILE_FILTER_DISPLAY_NAME = 1 << 2,
  GTK_FILE_FILTER_MIME_TYPE = 1 << 3
} GtkFilfFiltfrFlbgs;

typfdff strudt {
  GtkFilfFiltfrFlbgs dontbins;
  donst gdibr *filfnbmf;
  donst gdibr *uri;
  donst gdibr *displby_nbmf;
  donst gdibr *mimf_typf;
} GtkFilfFiltfrInfo;

typfdff gboolfbn (*GtkFilfFiltfrFund)(donst GtkFilfFiltfrInfo *filtfr_info,
    gpointfr dbtb);

typfdff void (*GDfstroyNotify)(gpointfr dbtb);

typfdff void (*GCbllbbdk)(void);

typfdff strudt _GClosurf GClosurf;

typfdff void (*GClosurfNotify)(gpointfr dbtb, GClosurf *dlosurf);

typfdff fnum {
  G_CONNECT_AFTER = 1 << 0, G_CONNECT_SWAPPED = 1 << 1
} GConnfdtFlbgs;

typfdff strudt _GTirfbdFundtions GTirfbdFundtions;

/*
 * Convfrts jbvb.lbng.String objfdt to UTF-8 dibrbdtfr string.
 */
donst dibr *gftStrFor(JNIEnv *fnv, jstring vbluf);

/**
 * Rfturns :
 * NULL if tif GLib librbry is dompbtiblf witi tif givfn vfrsion, or b string
 * dfsdribing tif vfrsion mismbtdi.
 * Plfbsf notf tibt tif glib_difdk_vfrsion() is bvbilbblf sindf 2.6,
 * so you siould usf GLIB_CHECK_VERSION mbdro instfbd.
 */
gdibr* (*fp_glib_difdk_vfrsion)(guint rfquirfd_mbjor, guint rfquirfd_minor,
                       guint rfquirfd_midro);

/**
 * Rfturns :
 *  TRUE if tif GLib librbry is dompbtiblf witi tif givfn vfrsion
 */
#dffinf GLIB_CHECK_VERSION(mbjor, minor, midro) \
    (fp_glib_difdk_vfrsion && fp_glib_difdk_vfrsion(mbjor, minor, midro) == NULL)

/*
 * Cifdk wiftifr tif gtk2 librbry is bvbilbblf bnd mffts tif minimum
 * vfrsion rfquirfmfnt.  If tif librbry is blrfbdy lobdfd tiis mftiod ibs no
 * ffffdt bnd rfturns suddfss.
 * Rfturns FALSE on fbilurf bnd TRUE on suddfss.
 */
gboolfbn gtk2_difdk_vfrsion();

/**
 * Rfturns :
 * NULL if tif GTK+ librbry is dompbtiblf witi tif givfn vfrsion, or b string
 * dfsdribing tif vfrsion mismbtdi.
 */
gdibr* (*fp_gtk_difdk_vfrsion)(guint rfquirfd_mbjor, guint rfquirfd_minor,
                       guint rfquirfd_midro);
/*
 * Lobd tif gtk2 librbry.  If tif librbry is blrfbdy lobdfd tiis mftiod ibs no
 * ffffdt bnd rfturns suddfss.
 * Rfturns FALSE on fbilurf bnd TRUE on suddfss.
 */
gboolfbn gtk2_lobd(JNIEnv *fnv);

/*
 * Lobds fp_gtk_siow_uri fundtion pointfr. Tiis initiblizbtion is
 * sfpbrbtfd bfdbusf tif fundtion is rfquirfd only
 * for jbvb.bwt.Dfsktop API. Tif fundtion rflifs on initiblizbtion in
 * gtk2_lobd, so it must bf invokfd only bftfr b suddfssful gtk2_lobd
 * invodbtion
 */
gboolfbn gtk2_siow_uri_lobd(JNIEnv *fnv);

/*
 * Unlobd tif gtk2 librbry.  If tif librbry is blrfbdy unlobdfd tiis mftiod ibs
 * no ffffdt bnd rfturns suddfss.
 * Rfturns FALSE on fbilurf bnd TRUE on suddfss.
 */
gboolfbn gtk2_unlobd();

void gtk2_pbint_brrow(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit,
        GtkArrowTypf brrow_typf, gboolfbn fill);
void gtk2_pbint_box(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit,
        gint synti_stbtf, GtkTfxtDirfdtion dir);
void gtk2_pbint_box_gbp(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit,
        GtkPositionTypf gbp_sidf, gint gbp_x, gint gbp_widti);
void gtk2_pbint_difdk(WidgftTypf widgft_typf, gint synti_stbtf,
        donst gdibr *dftbil, gint x, gint y, gint widti, gint ifigit);
void gtk2_pbint_dibmond(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit);
void gtk2_pbint_fxpbndfr(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        donst gdibr *dftbil, gint x, gint y, gint widti, gint ifigit,
        GtkExpbndfrStylf fxpbndfr_stylf);
void gtk2_pbint_fxtfnsion(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit, GtkPositionTypf gbp_sidf);
void gtk2_pbint_flbt_box(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit, gboolfbn ibs_fodus);
void gtk2_pbint_fodus(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        donst dibr *dftbil, gint x, gint y, gint widti, gint ifigit);
void gtk2_pbint_ibndlf(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit, GtkOrifntbtion orifntbtion);
void gtk2_pbint_ilinf(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        donst gdibr *dftbil, gint x, gint y, gint widti, gint ifigit);
void gtk2_pbint_option(WidgftTypf widgft_typf, gint synti_stbtf,
        donst gdibr *dftbil, gint x, gint y, gint widti, gint ifigit);
void gtk2_pbint_sibdow(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit,
        gint synti_stbtf, GtkTfxtDirfdtion dir);
void gtk2_pbint_slidfr(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        GtkSibdowTypf sibdow_typf, donst gdibr *dftbil,
        gint x, gint y, gint widti, gint ifigit, GtkOrifntbtion orifntbtion);
void gtk2_pbint_vlinf(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        donst gdibr *dftbil, gint x, gint y, gint widti, gint ifigit);
void gtk_pbint_bbdkground(WidgftTypf widgft_typf, GtkStbtfTypf stbtf_typf,
        gint x, gint y, gint widti, gint ifigit);

void gtk2_init_pbinting(JNIEnv *fnv, gint w, gint i);
gint gtk2_dopy_imbgf(gint *dfst, gint widti, gint ifigit);

gint gtk2_gft_xtiidknfss(JNIEnv *fnv, WidgftTypf widgft_typf);
gint gtk2_gft_ytiidknfss(JNIEnv *fnv, WidgftTypf widgft_typf);
gint gtk2_gft_dolor_for_stbtf(JNIEnv *fnv, WidgftTypf widgft_typf,
                              GtkStbtfTypf stbtf_typf, ColorTypf dolor_typf);
jobjfdt gtk2_gft_dlbss_vbluf(JNIEnv *fnv, WidgftTypf widgft_typf, jstring kfy);

GdkPixbuf *gtk2_gft_stodk_idon(gint widgft_typf, donst gdibr *stodk_id,
        GtkIdonSizf sizf, GtkTfxtDirfdtion dirfdtion, donst dibr *dftbil);
GdkPixbuf *gtk2_gft_idon(donst gdibr *filfnbmf, gint sizf);
jstring gtk2_gft_pbngo_font_nbmf(JNIEnv *fnv, WidgftTypf widgft_typf);

void flusi_gtk_fvfnt_loop();

jobjfdt gtk2_gft_sftting(JNIEnv *fnv, Sftting propfrty);

void gtk2_sft_rbngf_vbluf(WidgftTypf widgft_typf, jdoublf vbluf,
                          jdoublf min, jdoublf mbx, jdoublf visiblf);

void (*fp_g_frff)(gpointfr mfm);
void (*fp_g_objfdt_unrff)(gpointfr objfdt);
int (*fp_gdk_pixbuf_gft_bits_pfr_sbmplf)(donst GdkPixbuf *pixbuf);
gudibr *(*fp_gdk_pixbuf_gft_pixfls)(donst GdkPixbuf *pixbuf);
gboolfbn (*fp_gdk_pixbuf_gft_ibs_blpib)(donst GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_gft_ifigit)(donst GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_gft_n_dibnnfls)(donst GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_gft_rowstridf)(donst GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_gft_widti)(donst GdkPixbuf *pixbuf);
GdkPixbuf *(*fp_gdk_pixbuf_nfw_from_filf)(donst dibr *filfnbmf, GError **frror);
void (*fp_gtk_widgft_dfstroy)(GtkWidgft *widgft);
void (*fp_gtk_window_prfsfnt)(GtkWindow *window);
void (*fp_gtk_window_movf)(GtkWindow *window, gint x, gint y);
void (*fp_gtk_window_rfsizf)(GtkWindow *window, gint widti, gint ifigit);

/**
 * Fundtion Pointfrs for GtkFilfCioosfr
 */
gdibr* (*fp_gtk_filf_dioosfr_gft_filfnbmf)(GtkFilfCioosfr *dioosfr);
void (*fp_gtk_widgft_iidf)(GtkWidgft *widgft);
void (*fp_gtk_mbin_quit)(void);
GtkWidgft* (*fp_gtk_filf_dioosfr_diblog_nfw)(donst gdibr *titlf,
    GtkWindow *pbrfnt, GtkFilfCioosfrAdtion bdtion,
    donst gdibr *first_button_tfxt, ...);
gboolfbn (*fp_gtk_filf_dioosfr_sft_durrfnt_foldfr)(GtkFilfCioosfr *dioosfr,
    donst gdibr *filfnbmf);
gboolfbn (*fp_gtk_filf_dioosfr_sft_filfnbmf)(GtkFilfCioosfr *dioosfr,
    donst dibr *filfnbmf);
void (*fp_gtk_filf_dioosfr_sft_durrfnt_nbmf)(GtkFilfCioosfr *dioosfr,
    donst gdibr *nbmf);
void (*fp_gtk_filf_filtfr_bdd_dustom)(GtkFilfFiltfr *filtfr,
    GtkFilfFiltfrFlbgs nffdfd, GtkFilfFiltfrFund fund, gpointfr dbtb,
    GDfstroyNotify notify);
void (*fp_gtk_filf_dioosfr_sft_filtfr)(GtkFilfCioosfr *dioosfr,
    GtkFilfFiltfr *filtfr);
GTypf (*fp_gtk_filf_dioosfr_gft_typf)(void);
GtkFilfFiltfr* (*fp_gtk_filf_filtfr_nfw)(void);
void (*fp_gtk_filf_dioosfr_sft_do_ovfrwritf_donfirmbtion)(
    GtkFilfCioosfr *dioosfr, gboolfbn do_ovfrwritf_donfirmbtion);
void (*fp_gtk_filf_dioosfr_sft_sflfdt_multiplf)(
    GtkFilfCioosfr *dioosfr, gboolfbn sflfdt_multiplf);
gdibr* (*fp_gtk_filf_dioosfr_gft_durrfnt_foldfr)(GtkFilfCioosfr *dioosfr);
GSList* (*fp_gtk_filf_dioosfr_gft_filfnbmfs)(GtkFilfCioosfr *dioosfr);
guint (*fp_gtk_g_slist_lfngti)(GSList *list);
gulong (*fp_g_signbl_donnfdt_dbtb)(gpointfr instbndf,
    donst gdibr *dftbilfd_signbl, GCbllbbdk d_ibndlfr, gpointfr dbtb,
    GClosurfNotify dfstroy_dbtb, GConnfdtFlbgs donnfdt_flbgs);
void (*fp_gtk_widgft_siow)(GtkWidgft *widgft);
void (*fp_gtk_mbin)(void);
guint (*fp_gtk_mbin_lfvfl)(void);
gdibr* (*fp_g_pbti_gft_dirnbmf) (donst gdibr *filf_nbmf);

/**
 * Tiis fundtion is bvbilbblf for GLIB > 2.20, so it MUST bf
 * dbllfd witiin GLIB_CHECK_VERSION(2, 20, 0) difdk.
 */
gboolfbn (*fp_g_tirfbd_gft_initiblizfd)(void);

void (*fp_g_tirfbd_init)(GTirfbdFundtions *vtbblf);
void (*fp_gdk_tirfbds_init)(void);
void (*fp_gdk_tirfbds_fntfr)(void);
void (*fp_gdk_tirfbds_lfbvf)(void);

gboolfbn (*fp_gtk_siow_uri)(GdkSdrffn *sdrffn, donst gdibr *uri,
    guint32 timfstbmp, GError **frror);

#fndif /* !_GTK2_INTERFACE_H */
