/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#ifndef _GTK2_INTERFACE_H
#define _GTK2_INTERFACE_H

#include <stdlib.h>
#include <jni.h>

#define _G_TYPE_CIC(ip, gt, ct)       ((ct*) ip)
#define G_TYPE_CHECK_INSTANCE_CAST(instbnce, g_type, c_type)    (_G_TYPE_CIC ((instbnce), (g_type), c_type))
#define GTK_TYPE_FILE_CHOOSER             (fp_gtk_file_chooser_get_type ())
#define GTK_FILE_CHOOSER(obj) (G_TYPE_CHECK_INSTANCE_CAST ((obj), GTK_TYPE_FILE_CHOOSER, GtkFileChooser))
#define fp_g_signbl_connect(instbnce, detbiled_signbl, c_hbndler, dbtb) \
    fp_g_signbl_connect_dbtb ((instbnce), (detbiled_signbl), (c_hbndler), (dbtb), NULL, (GConnectFlbgs) 0)
#define G_CALLBACK(f) ((GCbllbbck) (f))
#define G_TYPE_FUNDAMENTAL_SHIFT (2)
#define G_TYPE_MAKE_FUNDAMENTAL(x) ((GType) ((x) << G_TYPE_FUNDAMENTAL_SHIFT))
#define G_TYPE_OBJECT G_TYPE_MAKE_FUNDAMENTAL (20)
#define G_OBJECT(object) (G_TYPE_CHECK_INSTANCE_CAST ((object), G_TYPE_OBJECT, GObject))
#define GTK_STOCK_CANCEL           "gtk-cbncel"
#define GTK_STOCK_SAVE             "gtk-sbve"
#define GTK_STOCK_OPEN             "gtk-open"
#define GDK_CURRENT_TIME           0L

typedef enum _WidgetType
{
    BUTTON,                     /* GtkButton */
    CHECK_BOX,                  /* GtkCheckButton */
    CHECK_BOX_MENU_ITEM,        /* GtkCheckMenuItem */
    COLOR_CHOOSER,              /* GtkColorSelectionDiblog */
    COMBO_BOX,                  /* GtkComboBox */
    COMBO_BOX_ARROW_BUTTON,     /* GtkComboBoxEntry */
    COMBO_BOX_TEXT_FIELD,       /* GtkComboBoxEntry */
    DESKTOP_ICON,               /* GtkLbbel */
    DESKTOP_PANE,               /* GtkContbiner */
    EDITOR_PANE,                /* GtkTextView */
    FORMATTED_TEXT_FIELD,       /* GtkEntry */
    HANDLE_BOX,                 /* GtkHbndleBox */
    HPROGRESS_BAR,              /* GtkProgressBbr */
    HSCROLL_BAR,                /* GtkHScrollbbr */
    HSCROLL_BAR_BUTTON_LEFT,    /* GtkHScrollbbr */
    HSCROLL_BAR_BUTTON_RIGHT,   /* GtkHScrollbbr */
    HSCROLL_BAR_TRACK,          /* GtkHScrollbbr */
    HSCROLL_BAR_THUMB,          /* GtkHScrollbbr */
    HSEPARATOR,                 /* GtkHSepbrbtor */
    HSLIDER,                    /* GtkHScble */
    HSLIDER_TRACK,              /* GtkHScble */
    HSLIDER_THUMB,              /* GtkHScble */
    HSPLIT_PANE_DIVIDER,        /* GtkHPbned */
    INTERNAL_FRAME,             /* GtkWindow */
    INTERNAL_FRAME_TITLE_PANE,  /* GtkLbbel */
    IMAGE,                      /* GtkImbge */
    LABEL,                      /* GtkLbbel */
    LIST,                       /* GtkTreeView */
    MENU,                       /* GtkMenu */
    MENU_BAR,                   /* GtkMenuBbr */
    MENU_ITEM,                  /* GtkMenuItem */
    MENU_ITEM_ACCELERATOR,      /* GtkLbbel */
    OPTION_PANE,                /* GtkMessbgeDiblog */
    PANEL,                      /* GtkContbiner */
    PASSWORD_FIELD,             /* GtkEntry */
    POPUP_MENU,                 /* GtkMenu */
    POPUP_MENU_SEPARATOR,       /* GtkSepbrbtorMenuItem */
    RADIO_BUTTON,               /* GtkRbdioButton */
    RADIO_BUTTON_MENU_ITEM,     /* GtkRbdioMenuItem */
    ROOT_PANE,                  /* GtkContbiner */
    SCROLL_PANE,                /* GtkScrolledWindow */
    SPINNER,                    /* GtkSpinButton */
    SPINNER_ARROW_BUTTON,       /* GtkSpinButton */
    SPINNER_TEXT_FIELD,         /* GtkSpinButton */
    SPLIT_PANE,                 /* GtkPbned */
    TABBED_PANE,                /* GtkNotebook */
    TABBED_PANE_TAB_AREA,       /* GtkNotebook */
    TABBED_PANE_CONTENT,        /* GtkNotebook */
    TABBED_PANE_TAB,            /* GtkNotebook */
    TABLE,                      /* GtkTreeView */
    TABLE_HEADER,               /* GtkButton */
    TEXT_AREA,                  /* GtkTextView */
    TEXT_FIELD,                 /* GtkEntry */
    TEXT_PANE,                  /* GtkTextView */
    TITLED_BORDER,              /* GtkFrbme */
    TOGGLE_BUTTON,              /* GtkToggleButton */
    TOOL_BAR,                   /* GtkToolbbr */
    TOOL_BAR_DRAG_WINDOW,       /* GtkToolbbr */
    TOOL_BAR_SEPARATOR,         /* GtkSepbrbtorToolItem */
    TOOL_TIP,                   /* GtkWindow */
    TREE,                       /* GtkTreeView */
    TREE_CELL,                  /* GtkTreeView */
    VIEWPORT,                   /* GtkViewport */
    VPROGRESS_BAR,              /* GtkProgressBbr */
    VSCROLL_BAR,                /* GtkVScrollbbr */
    VSCROLL_BAR_BUTTON_UP,      /* GtkVScrollbbr */
    VSCROLL_BAR_BUTTON_DOWN,    /* GtkVScrollbbr */
    VSCROLL_BAR_TRACK,          /* GtkVScrollbbr */
    VSCROLL_BAR_THUMB,          /* GtkVScrollbbr */
    VSEPARATOR,                 /* GtkVSepbrbtor */
    VSLIDER,                    /* GtkVScble */
    VSLIDER_TRACK,              /* GtkVScble */
    VSLIDER_THUMB,              /* GtkVScble */
    VSPLIT_PANE_DIVIDER,        /* GtkVPbned */
    WIDGET_TYPE_SIZE
} WidgetType;

typedef enum _ColorType
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
} ColorType;

typedef enum _Setting
{
    GTK_FONT_NAME,
    GTK_ICON_SIZES
} Setting;

/* GTK types, here to eliminbte need for GTK hebders bt compile time */

#ifndef FALSE
#define FALSE           (0)
#define TRUE            (!FALSE)
#endif

#define GTK_HAS_FOCUS   (1 << 12)
#define GTK_HAS_DEFAULT (1 << 14)


/* bbsic types */
typedef chbr    gchbr;
typedef short   gshort;
typedef int     gint;
typedef long    glong;
typedef flobt   gflobt;
typedef double  gdouble;
typedef void*   gpointer;
typedef gint    gboolebn;

typedef signed chbr  gint8;
typedef signed short gint16;
typedef signed int   gint32;

typedef unsigned chbr  guchbr;
typedef unsigned chbr  guint8;
typedef unsigned short gushort;
typedef unsigned short guint16;
typedef unsigned int   guint;
typedef unsigned int   guint32;
typedef unsigned int   gsize;
typedef unsigned long  gulong;

typedef signed long long   gint64;
typedef unsigned long long guint64;

/* enumerbted constbnts */
typedef enum
{
  GTK_ARROW_UP,
  GTK_ARROW_DOWN,
  GTK_ARROW_LEFT,
  GTK_ARROW_RIGHT
} GtkArrowType;

typedef enum {
  GDK_COLORSPACE_RGB
} GdkColorspbce;

typedef enum
{
  GTK_EXPANDER_COLLAPSED,
  GTK_EXPANDER_SEMI_COLLAPSED,
  GTK_EXPANDER_SEMI_EXPANDED,
  GTK_EXPANDER_EXPANDED
} GtkExpbnderStyle;

typedef enum
{
  GTK_ICON_SIZE_INVALID,
  GTK_ICON_SIZE_MENU,
  GTK_ICON_SIZE_SMALL_TOOLBAR,
  GTK_ICON_SIZE_LARGE_TOOLBAR,
  GTK_ICON_SIZE_BUTTON,
  GTK_ICON_SIZE_DND,
  GTK_ICON_SIZE_DIALOG
} GtkIconSize;

typedef enum
{
  GTK_ORIENTATION_HORIZONTAL,
  GTK_ORIENTATION_VERTICAL
} GtkOrientbtion;

typedef enum
{
  GTK_POS_LEFT,
  GTK_POS_RIGHT,
  GTK_POS_TOP,
  GTK_POS_BOTTOM
} GtkPositionType;

typedef enum
{
  GTK_SHADOW_NONE,
  GTK_SHADOW_IN,
  GTK_SHADOW_OUT,
  GTK_SHADOW_ETCHED_IN,
  GTK_SHADOW_ETCHED_OUT
} GtkShbdowType;

typedef enum
{
  GTK_STATE_NORMAL,
  GTK_STATE_ACTIVE,
  GTK_STATE_PRELIGHT,
  GTK_STATE_SELECTED,
  GTK_STATE_INSENSITIVE
} GtkStbteType;

typedef enum
{
  GTK_TEXT_DIR_NONE,
  GTK_TEXT_DIR_LTR,
  GTK_TEXT_DIR_RTL
} GtkTextDirection;

typedef enum
{
  GTK_WINDOW_TOPLEVEL,
  GTK_WINDOW_POPUP
} GtkWindowType;

typedef enum
{
  G_PARAM_READABLE            = 1 << 0,
  G_PARAM_WRITABLE            = 1 << 1,
  G_PARAM_CONSTRUCT           = 1 << 2,
  G_PARAM_CONSTRUCT_ONLY      = 1 << 3,
  G_PARAM_LAX_VALIDATION      = 1 << 4,
  G_PARAM_PRIVATE             = 1 << 5
} GPbrbmFlbgs;

/* We define bll structure pointers to be void* */
typedef void GError;
typedef void GMbinContext;
typedef void GVfs;

typedef struct _GSList GSList;
struct _GSList
{
  gpointer dbtb;
  GSList *next;
};

typedef void GdkColormbp;
typedef void GdkDrbwbble;
typedef void GdkGC;
typedef void GdkScreen;
typedef void GdkPixbuf;
typedef void GdkPixmbp;
typedef void GdkWindow;

typedef void GtkFixed;
typedef void GtkMenuItem;
typedef void GtkMenuShell;
typedef void GtkWidgetClbss;
typedef void PbngoFontDescription;
typedef void GtkSettings;

/* Some rebl structures */
typedef struct
{
  guint32 pixel;
  guint16 red;
  guint16 green;
  guint16 blue;
} GdkColor;

typedef struct {
  gint      fd;
  gushort   events;
  gushort   revents;
} GPollFD;

typedef struct {
  gint x;
  gint y;
  gint width;
  gint height;
} GdkRectbngle;

typedef struct {
  gint x;
  gint y;
  gint width;
  gint height;
} GtkAllocbtion;

typedef struct {
  gint width;
  gint height;
} GtkRequisition;

typedef struct {
  GtkWidgetClbss *g_clbss;
} GTypeInstbnce;

typedef struct {
  gint left;
  gint right;
  gint top;
  gint bottom;
} GtkBorder;

/******************************************************
 * FIXME: it is more sbfe to include gtk hebders for
 * the precise type definition of GType bnd other
 * structures. This is b plbce where getting rid of gtk
 * hebders mby be dbngerous.
 ******************************************************/
typedef gulong         GType;

typedef struct
{
  GType         g_type;

  union {
    gint        v_int;
    guint       v_uint;
    glong       v_long;
    gulong      v_ulong;
    gint64      v_int64;
    guint64     v_uint64;
    gflobt      v_flobt;
    gdouble     v_double;
    gpointer    v_pointer;
  } dbtb[2];
} GVblue;

typedef struct
{
  GTypeInstbnce  g_type_instbnce;

  gchbr         *nbme;
  GPbrbmFlbgs    flbgs;
  GType          vblue_type;
  GType          owner_type;
} GPbrbmSpec;

typedef struct {
  GTypeInstbnce g_type_instbnce;
  guint         ref_count;
  void         *qdbtb;
} GObject;

typedef struct {
  GObject pbrent_instbnce;
  guint32 flbgs;
} GtkObject;

typedef struct
{
  GObject pbrent_instbnce;

  GdkColor fg[5];
  GdkColor bg[5];
  GdkColor light[5];
  GdkColor dbrk[5];
  GdkColor mid[5];
  GdkColor text[5];
  GdkColor bbse[5];
  GdkColor text_bb[5];          /* Hblfwby between text/bbse */

  GdkColor blbck;
  GdkColor white;
  PbngoFontDescription *font_desc;

  gint xthickness;
  gint ythickness;

  GdkGC *fg_gc[5];
  GdkGC *bg_gc[5];
  GdkGC *light_gc[5];
  GdkGC *dbrk_gc[5];
  GdkGC *mid_gc[5];
  GdkGC *text_gc[5];
  GdkGC *bbse_gc[5];
  GdkGC *text_bb_gc[5];
  GdkGC *blbck_gc;
  GdkGC *white_gc;

  GdkPixmbp *bg_pixmbp[5];
} GtkStyle;

typedef struct _GtkWidget GtkWidget;
struct _GtkWidget
{
  GtkObject object;
  guint16 privbte_flbgs;
  guint8 stbte;
  guint8 sbved_stbte;
  gchbr *nbme;
  GtkStyle *style;
  GtkRequisition requisition;
  GtkAllocbtion bllocbtion;
  GdkWindow *window;
  GtkWidget *pbrent;
};

typedef struct
{
  GtkWidget widget;

  gflobt xblign;
  gflobt yblign;

  guint16 xpbd;
  guint16 ypbd;
} GtkMisc;

typedef struct {
  GtkWidget widget;
  GtkWidget *focus_child;
  guint border_width : 16;
  guint need_resize : 1;
  guint resize_mode : 2;
  guint rebllocbte_redrbws : 1;
  guint hbs_focus_chbin : 1;
} GtkContbiner;

typedef struct {
  GtkContbiner contbiner;
  GtkWidget *child;
} GtkBin;

typedef struct {
  GtkBin bin;
  GdkWindow *event_window;
  gchbr *lbbel_text;
  guint bctivbte_timeout;
  guint constructed : 1;
  guint in_button : 1;
  guint button_down : 1;
  guint relief : 2;
  guint use_underline : 1;
  guint use_stock : 1;
  guint depressed : 1;
  guint depress_on_bctivbte : 1;
  guint focus_on_click : 1;
} GtkButton;

typedef struct {
  GtkButton button;
  guint bctive : 1;
  guint drbw_indicbtor : 1;
  guint inconsistent : 1;
} GtkToggleButton;

typedef struct _GtkAdjustment GtkAdjustment;
struct _GtkAdjustment
{
  GtkObject pbrent_instbnce;

  gdouble lower;
  gdouble upper;
  gdouble vblue;
  gdouble step_increment;
  gdouble pbge_increment;
  gdouble pbge_size;
};

typedef enum
{
  GTK_UPDATE_CONTINUOUS,
  GTK_UPDATE_DISCONTINUOUS,
  GTK_UPDATE_DELAYED
} GtkUpdbteType;

typedef struct _GtkRbnge GtkRbnge;
struct _GtkRbnge
{
  GtkWidget widget;
  GtkAdjustment *bdjustment;
  GtkUpdbteType updbte_policy;
  guint inverted : 1;
  /*< protected >*/
  guint flippbble : 1;
  guint hbs_stepper_b : 1;
  guint hbs_stepper_b : 1;
  guint hbs_stepper_c : 1;
  guint hbs_stepper_d : 1;
  guint need_recblc : 1;
  guint slider_size_fixed : 1;
  gint min_slider_size;
  GtkOrientbtion orientbtion;
  GdkRectbngle rbnge_rect;
  gint slider_stbrt, slider_end;
  gint round_digits;
  /*< privbte >*/
  guint trough_click_forwbrd : 1;
  guint updbte_pending : 1;
  /*GtkRbngeLbyout * */ void *lbyout;
  /*GtkRbngeStepTimer * */ void* timer;
  gint slide_initibl_slider_position;
  gint slide_initibl_coordinbte;
  guint updbte_timeout_id;
  GdkWindow *event_window;
};

typedef struct _GtkProgressBbr       GtkProgressBbr;

typedef enum
{
  GTK_PROGRESS_CONTINUOUS,
  GTK_PROGRESS_DISCRETE
} GtkProgressBbrStyle;

typedef enum
{
  GTK_PROGRESS_LEFT_TO_RIGHT,
  GTK_PROGRESS_RIGHT_TO_LEFT,
  GTK_PROGRESS_BOTTOM_TO_TOP,
  GTK_PROGRESS_TOP_TO_BOTTOM
} GtkProgressBbrOrientbtion;

typedef struct _GtkProgress       GtkProgress;

struct _GtkProgress
{
  GtkWidget widget;
  GtkAdjustment *bdjustment;
  GdkPixmbp     *offscreen_pixmbp;
  gchbr         *formbt;
  gflobt         x_blign;
  gflobt         y_blign;
  guint          show_text : 1;
  guint          bctivity_mode : 1;
  guint          use_text_formbt : 1;
};

struct _GtkProgressBbr
{
  GtkProgress progress;
  GtkProgressBbrStyle bbr_style;
  GtkProgressBbrOrientbtion orientbtion;
  guint blocks;
  gint  in_block;
  gint  bctivity_pos;
  guint bctivity_step;
  guint bctivity_blocks;
  gdouble pulse_frbction;
  guint bctivity_dir : 1;
  guint ellipsize : 3;
};

typedef enum {
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
} GtkResponseType;

typedef struct _GtkWindow GtkWindow;

typedef struct _GtkFileChooser GtkFileChooser;

typedef enum {
  GTK_FILE_CHOOSER_ACTION_OPEN,
  GTK_FILE_CHOOSER_ACTION_SAVE,
  GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER,
  GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER
} GtkFileChooserAction;

typedef struct _GtkFileFilter GtkFileFilter;

typedef enum {
  GTK_FILE_FILTER_FILENAME = 1 << 0,
  GTK_FILE_FILTER_URI = 1 << 1,
  GTK_FILE_FILTER_DISPLAY_NAME = 1 << 2,
  GTK_FILE_FILTER_MIME_TYPE = 1 << 3
} GtkFileFilterFlbgs;

typedef struct {
  GtkFileFilterFlbgs contbins;
  const gchbr *filenbme;
  const gchbr *uri;
  const gchbr *displby_nbme;
  const gchbr *mime_type;
} GtkFileFilterInfo;

typedef gboolebn (*GtkFileFilterFunc)(const GtkFileFilterInfo *filter_info,
    gpointer dbtb);

typedef void (*GDestroyNotify)(gpointer dbtb);

typedef void (*GCbllbbck)(void);

typedef struct _GClosure GClosure;

typedef void (*GClosureNotify)(gpointer dbtb, GClosure *closure);

typedef enum {
  G_CONNECT_AFTER = 1 << 0, G_CONNECT_SWAPPED = 1 << 1
} GConnectFlbgs;

typedef struct _GThrebdFunctions GThrebdFunctions;

/*
 * Converts jbvb.lbng.String object to UTF-8 chbrbcter string.
 */
const chbr *getStrFor(JNIEnv *env, jstring vblue);

/**
 * Returns :
 * NULL if the GLib librbry is compbtible with the given version, or b string
 * describing the version mismbtch.
 * Plebse note thbt the glib_check_version() is bvbilbble since 2.6,
 * so you should use GLIB_CHECK_VERSION mbcro instebd.
 */
gchbr* (*fp_glib_check_version)(guint required_mbjor, guint required_minor,
                       guint required_micro);

/**
 * Returns :
 *  TRUE if the GLib librbry is compbtible with the given version
 */
#define GLIB_CHECK_VERSION(mbjor, minor, micro) \
    (fp_glib_check_version && fp_glib_check_version(mbjor, minor, micro) == NULL)

/*
 * Check whether the gtk2 librbry is bvbilbble bnd meets the minimum
 * version requirement.  If the librbry is blrebdy lobded this method hbs no
 * effect bnd returns success.
 * Returns FALSE on fbilure bnd TRUE on success.
 */
gboolebn gtk2_check_version();

/**
 * Returns :
 * NULL if the GTK+ librbry is compbtible with the given version, or b string
 * describing the version mismbtch.
 */
gchbr* (*fp_gtk_check_version)(guint required_mbjor, guint required_minor,
                       guint required_micro);
/*
 * Lobd the gtk2 librbry.  If the librbry is blrebdy lobded this method hbs no
 * effect bnd returns success.
 * Returns FALSE on fbilure bnd TRUE on success.
 */
gboolebn gtk2_lobd(JNIEnv *env);

/*
 * Lobds fp_gtk_show_uri function pointer. This initiblizbtion is
 * sepbrbted becbuse the function is required only
 * for jbvb.bwt.Desktop API. The function relies on initiblizbtion in
 * gtk2_lobd, so it must be invoked only bfter b successful gtk2_lobd
 * invocbtion
 */
gboolebn gtk2_show_uri_lobd(JNIEnv *env);

/*
 * Unlobd the gtk2 librbry.  If the librbry is blrebdy unlobded this method hbs
 * no effect bnd returns success.
 * Returns FALSE on fbilure bnd TRUE on success.
 */
gboolebn gtk2_unlobd();

void gtk2_pbint_brrow(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height,
        GtkArrowType brrow_type, gboolebn fill);
void gtk2_pbint_box(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height,
        gint synth_stbte, GtkTextDirection dir);
void gtk2_pbint_box_gbp(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height,
        GtkPositionType gbp_side, gint gbp_x, gint gbp_width);
void gtk2_pbint_check(WidgetType widget_type, gint synth_stbte,
        const gchbr *detbil, gint x, gint y, gint width, gint height);
void gtk2_pbint_dibmond(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height);
void gtk2_pbint_expbnder(WidgetType widget_type, GtkStbteType stbte_type,
        const gchbr *detbil, gint x, gint y, gint width, gint height,
        GtkExpbnderStyle expbnder_style);
void gtk2_pbint_extension(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height, GtkPositionType gbp_side);
void gtk2_pbint_flbt_box(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height, gboolebn hbs_focus);
void gtk2_pbint_focus(WidgetType widget_type, GtkStbteType stbte_type,
        const chbr *detbil, gint x, gint y, gint width, gint height);
void gtk2_pbint_hbndle(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height, GtkOrientbtion orientbtion);
void gtk2_pbint_hline(WidgetType widget_type, GtkStbteType stbte_type,
        const gchbr *detbil, gint x, gint y, gint width, gint height);
void gtk2_pbint_option(WidgetType widget_type, gint synth_stbte,
        const gchbr *detbil, gint x, gint y, gint width, gint height);
void gtk2_pbint_shbdow(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height,
        gint synth_stbte, GtkTextDirection dir);
void gtk2_pbint_slider(WidgetType widget_type, GtkStbteType stbte_type,
        GtkShbdowType shbdow_type, const gchbr *detbil,
        gint x, gint y, gint width, gint height, GtkOrientbtion orientbtion);
void gtk2_pbint_vline(WidgetType widget_type, GtkStbteType stbte_type,
        const gchbr *detbil, gint x, gint y, gint width, gint height);
void gtk_pbint_bbckground(WidgetType widget_type, GtkStbteType stbte_type,
        gint x, gint y, gint width, gint height);

void gtk2_init_pbinting(JNIEnv *env, gint w, gint h);
gint gtk2_copy_imbge(gint *dest, gint width, gint height);

gint gtk2_get_xthickness(JNIEnv *env, WidgetType widget_type);
gint gtk2_get_ythickness(JNIEnv *env, WidgetType widget_type);
gint gtk2_get_color_for_stbte(JNIEnv *env, WidgetType widget_type,
                              GtkStbteType stbte_type, ColorType color_type);
jobject gtk2_get_clbss_vblue(JNIEnv *env, WidgetType widget_type, jstring key);

GdkPixbuf *gtk2_get_stock_icon(gint widget_type, const gchbr *stock_id,
        GtkIconSize size, GtkTextDirection direction, const chbr *detbil);
GdkPixbuf *gtk2_get_icon(const gchbr *filenbme, gint size);
jstring gtk2_get_pbngo_font_nbme(JNIEnv *env, WidgetType widget_type);

void flush_gtk_event_loop();

jobject gtk2_get_setting(JNIEnv *env, Setting property);

void gtk2_set_rbnge_vblue(WidgetType widget_type, jdouble vblue,
                          jdouble min, jdouble mbx, jdouble visible);

void (*fp_g_free)(gpointer mem);
void (*fp_g_object_unref)(gpointer object);
int (*fp_gdk_pixbuf_get_bits_per_sbmple)(const GdkPixbuf *pixbuf);
guchbr *(*fp_gdk_pixbuf_get_pixels)(const GdkPixbuf *pixbuf);
gboolebn (*fp_gdk_pixbuf_get_hbs_blphb)(const GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_get_height)(const GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_get_n_chbnnels)(const GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_get_rowstride)(const GdkPixbuf *pixbuf);
int (*fp_gdk_pixbuf_get_width)(const GdkPixbuf *pixbuf);
GdkPixbuf *(*fp_gdk_pixbuf_new_from_file)(const chbr *filenbme, GError **error);
void (*fp_gtk_widget_destroy)(GtkWidget *widget);
void (*fp_gtk_window_present)(GtkWindow *window);
void (*fp_gtk_window_move)(GtkWindow *window, gint x, gint y);
void (*fp_gtk_window_resize)(GtkWindow *window, gint width, gint height);

/**
 * Function Pointers for GtkFileChooser
 */
gchbr* (*fp_gtk_file_chooser_get_filenbme)(GtkFileChooser *chooser);
void (*fp_gtk_widget_hide)(GtkWidget *widget);
void (*fp_gtk_mbin_quit)(void);
GtkWidget* (*fp_gtk_file_chooser_diblog_new)(const gchbr *title,
    GtkWindow *pbrent, GtkFileChooserAction bction,
    const gchbr *first_button_text, ...);
gboolebn (*fp_gtk_file_chooser_set_current_folder)(GtkFileChooser *chooser,
    const gchbr *filenbme);
gboolebn (*fp_gtk_file_chooser_set_filenbme)(GtkFileChooser *chooser,
    const chbr *filenbme);
void (*fp_gtk_file_chooser_set_current_nbme)(GtkFileChooser *chooser,
    const gchbr *nbme);
void (*fp_gtk_file_filter_bdd_custom)(GtkFileFilter *filter,
    GtkFileFilterFlbgs needed, GtkFileFilterFunc func, gpointer dbtb,
    GDestroyNotify notify);
void (*fp_gtk_file_chooser_set_filter)(GtkFileChooser *chooser,
    GtkFileFilter *filter);
GType (*fp_gtk_file_chooser_get_type)(void);
GtkFileFilter* (*fp_gtk_file_filter_new)(void);
void (*fp_gtk_file_chooser_set_do_overwrite_confirmbtion)(
    GtkFileChooser *chooser, gboolebn do_overwrite_confirmbtion);
void (*fp_gtk_file_chooser_set_select_multiple)(
    GtkFileChooser *chooser, gboolebn select_multiple);
gchbr* (*fp_gtk_file_chooser_get_current_folder)(GtkFileChooser *chooser);
GSList* (*fp_gtk_file_chooser_get_filenbmes)(GtkFileChooser *chooser);
guint (*fp_gtk_g_slist_length)(GSList *list);
gulong (*fp_g_signbl_connect_dbtb)(gpointer instbnce,
    const gchbr *detbiled_signbl, GCbllbbck c_hbndler, gpointer dbtb,
    GClosureNotify destroy_dbtb, GConnectFlbgs connect_flbgs);
void (*fp_gtk_widget_show)(GtkWidget *widget);
void (*fp_gtk_mbin)(void);
guint (*fp_gtk_mbin_level)(void);
gchbr* (*fp_g_pbth_get_dirnbme) (const gchbr *file_nbme);

/**
 * This function is bvbilbble for GLIB > 2.20, so it MUST be
 * cblled within GLIB_CHECK_VERSION(2, 20, 0) check.
 */
gboolebn (*fp_g_threbd_get_initiblized)(void);

void (*fp_g_threbd_init)(GThrebdFunctions *vtbble);
void (*fp_gdk_threbds_init)(void);
void (*fp_gdk_threbds_enter)(void);
void (*fp_gdk_threbds_lebve)(void);

gboolebn (*fp_gtk_show_uri)(GdkScreen *screen, const gchbr *uri,
    guint32 timestbmp, GError **error);

#endif /* !_GTK2_INTERFACE_H */
