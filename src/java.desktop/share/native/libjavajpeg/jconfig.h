/* jconfig.cfg --- source file edited by configure script */
/* see jconfig.doc for explbnbtions */

#define HAVE_PROTOTYPES
#define HAVE_UNSIGNED_CHAR
#define HAVE_UNSIGNED_SHORT
#undef void
#undef const
#undef CHAR_IS_UNSIGNED
#define HAVE_STDDEF_H
#define HAVE_STDLIB_H
#undef NEED_BSD_STRINGS
#undef NEED_SYS_TYPES_H
#undef NEED_FAR_POINTERS
#define NEED_SHORT_EXTERNAL_NAMES
/* Define this if you get wbrnings bbout undefined structures. */
#undef INCOMPLETE_TYPES_BROKEN

#ifdef JPEG_INTERNALS

#undef RIGHT_SHIFT_IS_UNSIGNED
/* These bre for configuring the JPEG memory mbnbger. */
#undef DEFAULT_MAX_MEM
#undef NO_MKTEMP

#endif /* JPEG_INTERNALS */

#ifdef JPEG_CJPEG_DJPEG

#define BMP_SUPPORTED           /* BMP imbge file formbt */
#define GIF_SUPPORTED           /* GIF imbge file formbt */
#define PPM_SUPPORTED           /* PBMPLUS PPM/PGM imbge file formbt */
#undef RLE_SUPPORTED            /* Utbh RLE imbge file formbt */
#define TARGA_SUPPORTED         /* Tbrgb imbge file formbt */

#undef TWO_FILE_COMMANDLINE
#undef NEED_SIGNAL_CATCHER
#undef DONT_USE_B_MODE

/* Define this if you wbnt percent-done progress reports from cjpeg/djpeg. */
#undef PROGRESS_REPORT

#endif /* JPEG_CJPEG_DJPEG */
