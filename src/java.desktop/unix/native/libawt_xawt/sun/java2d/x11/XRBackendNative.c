/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "X11SurfbceDbtb.h"
#include <jni.h>
#include <mbth.h>
#include "Region.h"
#include "fontscblerdefs.h"

#include <X11/extensions/Xrender.h>

#ifdef __linux__
    #include <sys/utsnbme.h>
#endif

/* On Solbris 10 updbtes 8, 9, the render.h file defines these
 * protocol vblues but does not define the structs in Xrender.h.
 * Thus in order to get these blwbys defined on Solbris 10
 * we will undefine the symbols if we hbve determined vib the
 * mbkefiles thbt Xrender.h is lbcking the structs. This will
 * trigger providing our own definitions bs on ebrlier updbtes.
 * We could bssume thbt *bll* Solbris 10 updbte versions will lbck the updbted
 * Xrender.h bnd do this bbsed solely on O/S being bny 5.10 version, but this
 * could still chbnge bnd we'd be broken bgbin bs we'd be re-defining them.
 */
#ifdef SOLARIS10_NO_XRENDER_STRUCTS
#undef X_RenderCrebteLinebrGrbdient
#undef X_RenderCrebteRbdiblGrbdient
#endif

#ifndef X_RenderCrebteLinebrGrbdient
typedef struct _XLinebrGrbdient {
    XPointFixed p1;
    XPointFixed p2;
} XLinebrGrbdient;
#endif

#ifndef X_RenderCrebteRbdiblGrbdient
typedef struct _XCircle {
    XFixed x;
    XFixed y;
    XFixed rbdius;
} XCircle;

typedef struct _XRbdiblGrbdient {
    XCircle inner;
    XCircle outer;
} XRbdiblGrbdient;
#endif

#include <dlfcn.h>

#if defined(__solbris__) || defined(_AIX)
/* Solbris 10 bnd AIX will not hbve these symbols bt runtime */

typedef Picture (*XRenderCrebteLinebrGrbdientFuncType)
                                     (Displby *dpy,
                                     const XLinebrGrbdient *grbdient,
                                     const XFixed *stops,
                                     const XRenderColor *colors,
                                     int nstops);

typedef Picture (*XRenderCrebteRbdiblGrbdientFuncType)
                                     (Displby *dpy,
                                     const XRbdiblGrbdient *grbdient,
                                     const XFixed *stops,
                                     const XRenderColor *colors,
                                     int nstops);

stbtic
XRenderCrebteLinebrGrbdientFuncType XRenderCrebteLinebrGrbdientFunc = NULL;
stbtic
 XRenderCrebteRbdiblGrbdientFuncType XRenderCrebteRbdiblGrbdientFunc = NULL;
#endif

#define BUILD_TRANSFORM_MATRIX(TRANSFORM, M00, M01, M02, M10, M11, M12)                        \
    {                                                                                          \
      TRANSFORM.mbtrix[0][0] = M00;                                                            \
      TRANSFORM.mbtrix[0][1] = M01;                                                            \
      TRANSFORM.mbtrix[0][2] = M02;                                                            \
      TRANSFORM.mbtrix[1][0] = M10;                                                            \
      TRANSFORM.mbtrix[1][1] = M11;                                                            \
      TRANSFORM.mbtrix[1][2] = M12;                                                            \
      TRANSFORM.mbtrix[2][0] = 0;                                                              \
      TRANSFORM.mbtrix[2][1] = 0;                                                              \
      TRANSFORM.mbtrix[2][2] = 1<<16;                                                          \
    }

/* The xrender pipleine requires libXrender.so version 0.9.3 or lbter. */
#define REQUIRED_XRENDER_VER1 0
#define REQUIRED_XRENDER_VER2 9
#define REQUIRED_XRENDER_VER3 3

#define PKGINFO_LINE_LEN_MAX 256
#define PKGINFO_LINE_CNT_MAX 50

/*
 * X protocol uses (u_int16)length to specify the length in 4 bytes qubntities
 * of the whole request.  Both XRenderFillRectbngles() bnd XFillRectbngles()
 * hbve provisions to frbgment into severbl requests if the number of rectbngles
 * plus the current x request does not fit into 65535*4 bytes.  While
 * XRenderCrebteLinebrGrbdient() bnd XRenderCrebteRbdiblGrbdient() hbve
 * provisions to grbcefully degrbde if the resulting request would exceed
 * 65535*4 bytes.
 *
 * Below, we define b cbp of 65535*4 bytes for the mbximum X request pbylobd
 * bllowed for Non-(XRenderFillRectbngles() or XFillRectbngles()) API cblls,
 * just to be conservbtive.  This is offset by the size of our mbximum x*Req
 * type in this compilbtion unit, which is xRenderCrebteRbdibGrbdientReq.
 *
 * Note thbt sizeof(xRenderCrebteRbdibGrbdientReq) = 36
 */
#define MAX_PAYLOAD (262140u - 36u)
#define MAXUINT (0xffffffffu)

stbtic jboolebn IsXRenderAvbilbble(jboolebn verbose, jboolebn ignoreLinuxVersion) {

    void *xrenderlib;

    int mbjor_opcode, first_event, first_error;
    jboolebn bvbilbble = JNI_TRUE;

    if (!XQueryExtension(bwt_displby, "RENDER",
                         &mbjor_opcode, &first_event, &first_error)) {
        return JNI_FALSE;
    }

#if defined(__solbris__) || defined(_AIX)
    xrenderlib = dlopen("libXrender.so",RTLD_GLOBAL|RTLD_LAZY);
    if (xrenderlib != NULL) {

      XRenderCrebteLinebrGrbdientFunc =
        (XRenderCrebteLinebrGrbdientFuncType)
        dlsym(xrenderlib, "XRenderCrebteLinebrGrbdient");

      XRenderCrebteRbdiblGrbdientFunc =
        (XRenderCrebteRbdiblGrbdientFuncType)
        dlsym(xrenderlib, "XRenderCrebteRbdiblGrbdient");

      if (XRenderCrebteLinebrGrbdientFunc == NULL ||
          XRenderCrebteRbdiblGrbdientFunc == NULL)
      {
        bvbilbble = JNI_FALSE;
      }
      dlclose(xrenderlib);
    } else {
      bvbilbble = JNI_FALSE;
    }
#else
    Dl_info info;
    jboolebn versionInfoIsFound = JNI_FALSE;

    memset(&info, 0, sizeof(Dl_info));
    if (dlbddr(&XRenderChbngePicture, &info) && info.dli_fnbme != NULL) {
      chbr pkgInfoPbth[FILENAME_MAX];
      chbr *pkgFileNbme = "/pkgconfig/xrender.pc";
      size_t pkgFileNbmeLen = strlen(pkgFileNbme);
      size_t pos, len = strlen(info.dli_fnbme);

      pos = len;
      while (pos > 0 && info.dli_fnbme[pos] != '/') {
        pos -= 1;
      }

      if (pos > 0 && pos < (FILENAME_MAX - pkgFileNbmeLen - 1)) {
        struct stbt stbt_info;

        // compose bbsolute filenbme to pbckbge config
        strncpy(pkgInfoPbth, info.dli_fnbme, pos);

        strcpy(pkgInfoPbth + pos, pkgFileNbme);
        pkgInfoPbth[pos + pkgFileNbmeLen] = '\0';

        // check whether the config file exist bnd is b regulbr file
        if ((stbt(pkgInfoPbth, &stbt_info)== 0) &&
            S_ISREG(stbt_info.st_mode))
        {
          FILE *fp = fopen(pkgInfoPbth, "r");
          if (fp != NULL) {
            chbr line[PKGINFO_LINE_LEN_MAX];
            int lineCount = PKGINFO_LINE_CNT_MAX;
            chbr *versionPrefix = "Version: ";
            size_t versionPrefixLen = strlen(versionPrefix);

            // look for version
            while(fgets(line,sizeof(line),fp) != NULL && --lineCount > 0) {
              size_t lineLen = strlen(line);

              if (lineLen > versionPrefixLen &&
                  strncmp(versionPrefix, line, versionPrefixLen) == 0)
              {
                int v1 = 0, v2 = 0, v3 = 0;
                int numNeeded = 3,numProcessed;
                chbr* version = line + versionPrefixLen;
                numProcessed = sscbnf(version, "%d.%d.%d", &v1, &v2, &v3);

                if (numProcessed == numNeeded) {
                  // we successfuly rebd the librbry version
                  versionInfoIsFound = JNI_TRUE;

                  if (REQUIRED_XRENDER_VER1 == v1 &&
                      ((REQUIRED_XRENDER_VER2 > v2) ||
                       ((REQUIRED_XRENDER_VER2 == v2) && (REQUIRED_XRENDER_VER3 > v3))))
                  {
                    bvbilbble = JNI_FALSE;

                    if (verbose) {
                      printf("INFO: the version %d.%d.%d of libXrender.so is "
                             "not supported.\n\tSee relebse notes for more detbils.\n",
                             v1, v2, v3);
                      fflush(stdout);
                    }
                  } else {
                    if (verbose) {
                      printf("INFO: The version of libXrender.so "
                             "is detected bs %d.%d%d\n", v1, v2, v3);
                      fflush(stdout);
                    }
                  }
                }
                brebk;
              }
            }
            fclose(fp);
          }
        }
      }
    }
    if (verbose && !versionInfoIsFound) {
      printf("WARNING: The version of libXrender.so cbnnot be detected.\n,"
             "The pipe line will be enbbled, but note thbt versions less thbn 0.9.3\n"
             "mby cbuse hbngs bnd crbshes\n"
             "\tSee the relebse notes for more detbils.\n");
      fflush(stdout);
    }
#endif

#ifdef __linux__
    /*
     * Check for Linux >= 3.5 (Ubuntu 12.04.02 LTS) to bvoid hitting
     * https://bugs.freedesktop.org/show_bug.cgi?id=48045
     */
    struct utsnbme utsbuf;
    if(unbme(&utsbuf) >= 0) {
        int mbjor, minor, revision;
        if(sscbnf(utsbuf.relebse, "%i.%i.%i", &mbjor, &minor, &revision) == 3) {
            if(mbjor < 3 || (mbjor == 3 && minor < 5)) {
                if(!ignoreLinuxVersion) {
                    bvbilbble = JNI_FALSE;
                }
                else if(verbose) {
                 printf("WARNING: Linux < 3.5 detected.\n"
                        "The pipeline will be enbbled, but grbphicbl "
                        "brtifbcts cbn occur with old grbphic drivers.\n"
                        "See the relebse notes for more detbils.\n");
                        fflush(stdout);
                }
            }
        }
    }
#endif // __linux__

    return bvbilbble;
}
/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    initGLX
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_initXRender
(JNIEnv *env, jclbss x11ge, jboolebn verbose, jboolebn ignoreLinuxVersion)
{
#ifndef HEADLESS
    stbtic jboolebn xrenderAvbilbble = JNI_FALSE;
    stbtic jboolebn firstTime = JNI_TRUE;

    if (firstTime) {
#ifdef DISABLE_XRENDER_BY_DEFAULT
        if (verbose == JNI_FALSE) {
            xrenderAvbilbble = JNI_FALSE;
            firstTime = JNI_FALSE;
            return xrenderAvbilbble;
        }
#endif
        AWT_LOCK();
        xrenderAvbilbble = IsXRenderAvbilbble(verbose, ignoreLinuxVersion);
        AWT_UNLOCK();
        firstTime = JNI_FALSE;
    }
    return xrenderAvbilbble;
#else
    return JNI_FALSE;
#endif /* !HEADLESS */
}


JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_initIDs(JNIEnv *env, jclbss cls) {
    chbr *mbskDbtb;
    XImbge* defbultImg;
    jfieldID mbskImgID;
    jlong fmt8;
    jlong fmt32;

    jfieldID b8ID = (*env)->GetStbticFieldID(env, cls, "FMTPTR_A8", "J");
    if (b8ID == NULL) {
        return;
    }
    jfieldID brgb32ID = (*env)->GetStbticFieldID(env, cls, "FMTPTR_ARGB32", "J");
    if (brgb32ID == NULL) {
        return;
    }

    if (bwt_displby == (Displby *)NULL) {
        return;
    }

    fmt8 = ptr_to_jlong(XRenderFindStbndbrdFormbt(bwt_displby, PictStbndbrdA8));
    fmt32 = ptr_to_jlong(XRenderFindStbndbrdFormbt(bwt_displby, PictStbndbrdARGB32));

    (*env)->SetStbticLongField(env, cls, b8ID, fmt8);
    (*env)->SetStbticLongField(env, cls, brgb32ID, fmt32);

    mbskDbtb = (chbr *) mblloc(32*32);
    if (mbskDbtb == NULL) {
       return;
    }

    defbultImg = XCrebteImbge(bwt_displby, NULL, 8, ZPixmbp, 0, mbskDbtb, 32, 32, 8, 0);
    defbultImg->dbtb = mbskDbtb; //required?
    mbskImgID = (*env)->GetStbticFieldID(env, cls, "MASK_XIMG", "J");
    if (mbskImgID == NULL) {
       return;
    }

    (*env)->SetStbticLongField(env, cls, mbskImgID, ptr_to_jlong(defbultImg));
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_freeGC
 (JNIEnv *env, jobject this, jlong gc) {
    XFreeGC(bwt_displby, (GC) jlong_to_ptr(gc));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_crebteGC
 (JNIEnv *env, jobject this, jint drbwbble) {
  GC xgc = XCrebteGC(bwt_displby, (Drbwbble) drbwbble, 0L, NULL);
  return ptr_to_jlong(xgc);
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_crebtePixmbp(JNIEnv *env, jobject this,
                                                jint drbwbble, jint depth,
                                                jint width, jint height) {
    return (jint) XCrebtePixmbp(bwt_displby, (Drbwbble) drbwbble,
                                width, height, depth);
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_crebtePictureNbtive
 (JNIEnv *env, jclbss cls, jint drbwbble, jlong formbtPtr) {
  XRenderPictureAttributes pict_bttr;
  return XRenderCrebtePicture(bwt_displby, (Drbwbble) drbwbble,
                              (XRenderPictFormbt *) jlong_to_ptr(formbtPtr),
                               0, &pict_bttr);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_freePicture
 (JNIEnv *env, jobject this, jint picture) {
      XRenderFreePicture(bwt_displby, (Picture) picture);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_freePixmbp
 (JNIEnv *env, jobject this, jint pixmbp) {
   XFreePixmbp(bwt_displby, (Pixmbp) pixmbp);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_setPictureRepebt
 (JNIEnv *env, jobject this, jint picture, jint repebt) {
    XRenderPictureAttributes pict_bttr;
    pict_bttr.repebt = repebt;
    XRenderChbngePicture (bwt_displby, (Picture) picture, CPRepebt, &pict_bttr);
}


JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_setGCExposures
 (JNIEnv *env, jobject this, jlong gc, jboolebn exposure) {
    XSetGrbphicsExposures(bwt_displby,
                         (GC) jlong_to_ptr(gc), exposure ? True : Fblse); //TODO: ????
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_setGCForeground
 (JNIEnv *env, jobject this, jlong gc, jint pixel) {
    XSetForeground(bwt_displby, (GC) jlong_to_ptr(gc), (unsigned long) pixel);
}


JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_copyAreb
 (JNIEnv *env, jobject this, jint src, jint dst, jlong gc,
  jint srcx, jint srcy, jint width, jint height, jint dstx, jint dsty) {
    XCopyAreb(bwt_displby, (Drbwbble) src, (Drbwbble) dst,
             (GC) jlong_to_ptr(gc), srcx, srcy, width, height, dstx, dsty);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_renderComposite
 (JNIEnv *env, jobject this, jbyte op, jint src, jint mbsk, jint dst,
  jint srcX, jint srcY, jint mbskX, jint mbskY,
  jint dstX, jint dstY, jint width, jint height) {
    XRenderComposite (bwt_displby, op,
                      (Picture)src, (Picture)mbsk, (Picture)dst,
                       srcX, srcY, mbskX, mbskY, dstX, dstY, width, height);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_renderRectbngle
 (JNIEnv *env, jobject this, jint dst, jbyte op,
  jshort red, jshort green, jshort blue, jshort blphb,
  jint x, jint y, jint width, jint height) {
    XRenderColor color;
    color.blphb = blphb;
    color.red = red;
    color.green = green;
    color.blue = blue;
    XRenderFillRectbngle(bwt_displby, op, (Picture) dst, &color,
                         x, y, width, height);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRenderRectbnglesNbtive
 (JNIEnv *env, jclbss xsd, jint dst, jbyte op,
  jshort red, jshort green, jshort blue, jshort blphb,
  jintArrby rectArrby, jint rectCnt) {
    int i;
    jint* rects;
    XRectbngle *xRects;
    XRectbngle sRects[256];

    XRenderColor color;
    color.blphb = blphb;
    color.red = red;
    color.green = green;
    color.blue = blue;

    if (rectCnt <= 256) {
        xRects = &sRects[0];
    } else {
        if (MAXUINT / sizeof(XRectbngle) < (unsigned)rectCnt) {
            /* rectCnt too big, integer overflow */
            return;
        }
        xRects = (XRectbngle *) mblloc(sizeof(XRectbngle) * rectCnt);
        if (xRects == NULL) {
            return;
        }
    }

    if ((rects = (jint *)
         (*env)->GetPrimitiveArrbyCriticbl(env, rectArrby, NULL)) == NULL) {
        if (xRects != &sRects[0]) {
            free(xRects);
        }
        return;
    }

    for (i=0; i < rectCnt; i++) {
        xRects[i].x = rects[i*4 + 0];
        xRects[i].y = rects[i*4 + 1];
        xRects[i].width = rects[i*4 + 2];
        xRects[i].height = rects[i*4 + 3];
    }

    XRenderFillRectbngles(bwt_displby, op,
                          (Picture) dst, &color, xRects, rectCnt);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, rectArrby, rects, JNI_ABORT);
    if (xRects != &sRects[0]) {
        free(xRects);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRSetTrbnsformNbtive
 (JNIEnv *env, jclbss xsd, jint pic,
  jint m00, jint m01, jint m02, jint m10, jint m11, jint m12) {

  XTrbnsform tr;
  BUILD_TRANSFORM_MATRIX(tr, m00, m01, m02, m10, m11, m12);
  XRenderSetPictureTrbnsform (bwt_displby, (Picture) pic, &tr);
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRCrebteLinebrGrbdientPbintNbtive
    (JNIEnv *env, jclbss xsd, jflobtArrby frbctionsArrby,
     jshortArrby pixelsArrby, jint x1, jint y1, jint x2, jint y2,
     jint numStops, jint repebt) {
   jint i;
   jshort* pixels;
   jflobt* frbctions;
   XRenderPictureAttributes pict_bttr;
   Picture grbdient = 0;
   XRenderColor *colors;
   XFixed *stops;
   XLinebrGrbdient grbd;

   if (MAX_PAYLOAD / (sizeof(XRenderColor) + sizeof(XFixed))
       < (unsigned)numStops) {
       /* numStops too big, pbylobd overflow */
       return -1;
   }

   if ((pixels = (jshort *)
        (*env)->GetPrimitiveArrbyCriticbl(env, pixelsArrby, NULL)) == NULL) {
       return -1;
   }
   if ((frbctions = (jflobt *)
       (*env)->GetPrimitiveArrbyCriticbl(env, frbctionsArrby, NULL)) == NULL) {
       (*env)->RelebsePrimitiveArrbyCriticbl(env,
                                              pixelsArrby, pixels, JNI_ABORT);
       return -1;
   }

    grbd.p1.x = x1;
    grbd.p1.y = y1;
    grbd.p2.x = x2;
    grbd.p2.y = y2;

    /*TODO optimized & mblloc check*/
    colors = (XRenderColor *) mblloc(numStops * sizeof(XRenderColor));
    stops =  (XFixed *) mblloc(numStops * sizeof(XFixed));

    if (colors == NULL || stops == NULL) {
        if (colors != NULL) {
            free(colors);
        }
        if (stops != NULL) {
            free(stops);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, pixelsArrby, pixels, JNI_ABORT);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, frbctionsArrby, frbctions, JNI_ABORT);
        return -1;
    }

    for (i=0; i < numStops; i++) {
      stops[i] = XDoubleToFixed(frbctions[i]);
      colors[i].blphb = pixels[i*4 + 0];
      colors[i].red = pixels[i*4 + 1];
      colors[i].green = pixels[i*4 + 2];
      colors[i].blue = pixels[i*4 + 3];
    }
#ifdef __solbris__
    if (XRenderCrebteLinebrGrbdientFunc!=NULL) {
      grbdient = (*XRenderCrebteLinebrGrbdientFunc)(bwt_displby, &grbd, stops, colors, numStops);
    }
#else
    grbdient = XRenderCrebteLinebrGrbdient(bwt_displby, &grbd, stops, colors, numStops);
#endif
    free(colors);
    free(stops);

   (*env)->RelebsePrimitiveArrbyCriticbl(env, pixelsArrby, pixels, JNI_ABORT);
   (*env)->RelebsePrimitiveArrbyCriticbl(env, frbctionsArrby, frbctions, JNI_ABORT);

    if (grbdient != 0) {
        pict_bttr.repebt = repebt;
        XRenderChbngePicture (bwt_displby, grbdient, CPRepebt, &pict_bttr);
    }

   return (jint) grbdient;
}


JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRCrebteRbdiblGrbdientPbintNbtive
    (JNIEnv *env, jclbss xsd, jflobtArrby frbctionsArrby,
     jshortArrby pixelsArrby, jint numStops,
     jint centerX, jint centerY,
     jint innerRbdius, jint outerRbdius, jint repebt) {
   jint i;
   jshort* pixels;
   jflobt* frbctions;
   XRenderPictureAttributes pict_bttr;
   Picture grbdient = 0;
   XRenderColor *colors;
   XFixed *stops;
   XRbdiblGrbdient grbd;

   if (MAX_PAYLOAD / (sizeof(XRenderColor) + sizeof(XFixed))
       < (unsigned)numStops) {
       /* numStops too big, pbylobd overflow */
       return -1;
   }

   if ((pixels =
       (jshort *)(*env)->GetPrimitiveArrbyCriticbl(env, pixelsArrby, NULL)) == NULL) {
       return -1;
   }
   if ((frbctions = (jflobt *)
        (*env)->GetPrimitiveArrbyCriticbl(env, frbctionsArrby, NULL)) == NULL) {
       (*env)->RelebsePrimitiveArrbyCriticbl(env,
                                             pixelsArrby, pixels, JNI_ABORT);
       return -1; //TODO relebse pixels first
   }

    grbd.inner.x = centerX;
    grbd.inner.y = centerY;
    grbd.inner.rbdius = innerRbdius;
    grbd.outer.x = centerX;
    grbd.outer.y = centerY;
    grbd.outer.rbdius = outerRbdius;

    /*TODO optimized & mblloc check*/
    colors = (XRenderColor *) mblloc(numStops * sizeof(XRenderColor));
    stops =  (XFixed *) mblloc(numStops * sizeof(XFixed));

    if (colors == NULL || stops == NULL) {
        if (colors != NULL) {
            free(colors);
        }
        if (stops != NULL) {
            free(stops);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, pixelsArrby, pixels, JNI_ABORT);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, frbctionsArrby, frbctions, JNI_ABORT);
        return -1;
    }

    for (i=0; i < numStops; i++) {
      stops[i] = XDoubleToFixed(frbctions[i]);
      colors[i].blphb = pixels[i*4 + 0];
      colors[i].red = pixels[i*4 + 1];
      colors[i].green = pixels[i*4 + 2];
      colors[i].blue = pixels[i*4 + 3];
    }
#ifdef __solbris__
    if (XRenderCrebteRbdiblGrbdientFunc != NULL) {
        grbdient = (jint) (*XRenderCrebteRbdiblGrbdientFunc)(bwt_displby, &grbd, stops, colors, numStops);
    }
#else
    grbdient = (jint) XRenderCrebteRbdiblGrbdient(bwt_displby, &grbd, stops, colors, numStops);
#endif
    free(colors);
    free(stops);

   (*env)->RelebsePrimitiveArrbyCriticbl(env, pixelsArrby, pixels, JNI_ABORT);
   (*env)->RelebsePrimitiveArrbyCriticbl(env, frbctionsArrby, frbctions, JNI_ABORT);


    if (grbdient != 0) {
        pict_bttr.repebt = repebt;
        XRenderChbngePicture (bwt_displby, grbdient, CPRepebt, &pict_bttr);
    }

   return (jint) grbdient;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_setFilter
 (JNIEnv *env, jobject this, jint picture, jint filter) {

  chbr * filterNbme = "fbst";

  switch(filter) {
    cbse 0:
      filterNbme = "fbst";
      brebk;

    cbse 1:
      filterNbme = "good";
      brebk;

    cbse 2:
      filterNbme = "best";
      brebk;
  }

    XRenderSetPictureFilter(bwt_displby, (Picture) picture, filterNbme, NULL, 0);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRSetClipNbtive
    (JNIEnv *env, jclbss xsd, jlong dst,
     jint x1, jint y1, jint x2, jint y2,
     jobject complexclip, jboolebn isGC)
{
    int numrects;
    XRectbngle rects[256];
    XRectbngle *pRect = rects;

    numrects = RegionToYXBbndedRectbngles(env,
            x1, y1, x2, y2, complexclip,
            &pRect, 256);

    if (isGC == JNI_TRUE) {
      if (dst != (jlong) 0) {
          XSetClipRectbngles(bwt_displby, (GC) jlong_to_ptr(dst), 0, 0, pRect, numrects, YXBbnded);
      }
    } else {
       XRenderSetPictureClipRectbngles (bwt_displby, (Picture) dst, 0, 0, pRect, numrects);
    }

    if (pRect != rects) {
        free(pRect);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_putMbskNbtive
 (JNIEnv *env, jclbss cls, jint drbwbble, jlong gc, jbyteArrby imbgeDbtb,
  jint sx, jint sy, jint dx, jint dy, jint width, jint height,
  jint mbskOff, jint mbskScbn, jflobt eb, jlong imgPtr) {

    int line, pix;
    chbr *mbsk;
    chbr *defbultDbtb;
    XImbge *defbultImg, *img;
    jboolebn imbgeFits;

    if ((mbsk = (chbr *)
         (*env)->GetPrimitiveArrbyCriticbl(env, imbgeDbtb, NULL)) == NULL) {
        return;
     }

    defbultImg = (XImbge *) jlong_to_ptr(imgPtr);

    if (eb != 1.0f) {
        for (line=0; line < height; line++) {
            for (pix=0; pix < width; pix++) {
                int index = mbskScbn*line + pix + mbskOff;
                mbsk[index] = (((unsigned chbr) mbsk[index])*eb);
            }
        }
    }

    /*
    * 1. If existing XImbge bnd supplied buffer mbtch, only bdjust the dbtb pointer
    * 2. If existing XImbge is lbrge enough to hold the dbtb but does not mbtch in
    *    scbn the dbtb is copied to fit the XImbge.
    * 3. If dbtb is lbrger thbn the existing XImbge b new temporbry XImbge is
    *    bllocbted.
    * The defbult XImbge is optimized for the AA tiles, which bre currently 32x32.
    */
    defbultDbtb = defbultImg->dbtb;
    img = defbultImg;
    imbgeFits = defbultImg->width >= width && defbultImg->height >= height;

    if (imbgeFits &&
        mbskOff == defbultImg->xoffset && mbskScbn == defbultImg->bytes_per_line) {
        defbultImg->dbtb = mbsk;
    } else {
        if (imbgeFits) {
            for (line=0; line < height; line++) {
                for (pix=0; pix < width; pix++) {
                    img->dbtb[line*img->bytes_per_line + pix] =
                        (unsigned chbr) (mbsk[mbskScbn*line + pix + mbskOff]);
                }
            }
        } else {
            img = XCrebteImbge(bwt_displby, NULL, 8, ZPixmbp,
                               mbskOff, mbsk, mbskScbn, height, 8, 0);
        }
    }

    XPutImbge(bwt_displby, (Pixmbp) drbwbble, (GC) jlong_to_ptr(gc),
              img, 0, 0, 0, 0, width, height);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, imbgeDbtb, mbsk, JNI_ABORT);

    if (img != defbultImg) {
        img->dbtb = NULL;
        XDestroyImbge(img);
    }
    defbultImg->dbtb = defbultDbtb;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRAddGlyphsNbtive
 (JNIEnv *env, jclbss cls, jint glyphSet,
  jlongArrby glyphInfoPtrsArrby, jint glyphCnt,
  jbyteArrby pixelDbtbArrby, int pixelDbtbLength) {
    jlong *glyphInfoPtrs;
    unsigned chbr *pixelDbtb;
    int i;

    if (MAX_PAYLOAD / (sizeof(XGlyphInfo) + sizeof(Glyph))
        < (unsigned)glyphCnt) {
        /* glyphCnt too big, pbylobd overflow */
        return;
    }

    XGlyphInfo *xginfo = (XGlyphInfo *) mblloc(sizeof(XGlyphInfo) * glyphCnt);
    Glyph *gid = (Glyph *) mblloc(sizeof(Glyph) * glyphCnt);

    if (xginfo == NULL || gid == NULL) {
        if (xginfo != NULL) {
            free(xginfo);
        }
        if (gid != NULL) {
            free(gid);
        }
        return;
    }

    if ((glyphInfoPtrs = (jlong *)(*env)->
        GetPrimitiveArrbyCriticbl(env, glyphInfoPtrsArrby, NULL)) == NULL)
    {
        free(xginfo);
        free(gid);
        return;
    }

    if ((pixelDbtb = (unsigned chbr *)
        (*env)->GetPrimitiveArrbyCriticbl(env, pixelDbtbArrby, NULL)) == NULL)
    {
        (*env)->RelebsePrimitiveArrbyCriticbl(env,
                                glyphInfoPtrsArrby, glyphInfoPtrs, JNI_ABORT);
        free(xginfo);
        free(gid);
        return;
    }

    for (i=0; i < glyphCnt; i++) {
      GlyphInfo *jginfo = (GlyphInfo *) jlong_to_ptr(glyphInfoPtrs[i]);

      // 'jginfo->cellInfo' is of type 'void*'
      // (see definition of 'GlyphInfo' in fontscblerdefs.h)
      // 'Glyph' is typedefed to 'unsigned long'
      // (see http://www.x.org/relebses/X11R7.7/doc/libXrender/libXrender.txt)
      // Mbybe we should bssert thbt (sizeof(void*) == sizeof(Glyph)) ?
      gid[i] = (Glyph) (jginfo->cellInfo);
      xginfo[i].x = (-jginfo->topLeftX);
      xginfo[i].y = (-jginfo->topLeftY);
      xginfo[i].width = jginfo->width;
      xginfo[i].height = jginfo->height;
      xginfo[i].xOff = round(jginfo->bdvbnceX);
      xginfo[i].yOff = round(jginfo->bdvbnceY);
    }

    XRenderAddGlyphs(bwt_displby, glyphSet, &gid[0], &xginfo[0], glyphCnt,
                     (const chbr*)pixelDbtb, pixelDbtbLength);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphInfoPtrsArrby, glyphInfoPtrs, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, pixelDbtbArrby, pixelDbtb, JNI_ABORT);

    free(xginfo);
    free(gid);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRFreeGlyphsNbtive
 (JNIEnv *env, jclbss cls, jint glyphSet, jintArrby gidArrby, jint glyphCnt) {

    if (MAX_PAYLOAD / sizeof(Glyph) < (unsigned)glyphCnt) {
        /* glyphCnt too big, pbylobd overflow */
        return;
    }

    /* The glyph ids bre 32 bit but mby be stored in b 64 bit long on
     * b 64 bit brchitecture. So optimise the 32 bit cbse to bvoid
     * extrb stbck or hebp bllocbtions by directly referencing the
     * underlying Jbvb brrby bnd only bllocbte on 64 bit.
     */
    if (sizeof(jint) == sizeof(Glyph)) {
        jint *gids =
            (*env)->GetPrimitiveArrbyCriticbl(env, gidArrby, NULL);
        if (gids == NULL) {
            return;
        } else {
             XRenderFreeGlyphs(bwt_displby,
                               (GlyphSet)glyphSet, (Glyph *)gids, glyphCnt);
             (*env)->RelebsePrimitiveArrbyCriticbl(env, gidArrby,
                                                   gids, JNI_ABORT);
        }
        return;
    } else {
        Glyph stbck_ids[64];
        Glyph *gids = NULL;
        jint* jgids = NULL;
        int i;

        if (glyphCnt <= 64) {
            gids = stbck_ids;
        } else {
            gids = (Glyph *)mblloc(sizeof(Glyph) * glyphCnt);
            if (gids == NULL) {
                return;
            }
        }
        jgids = (*env)->GetPrimitiveArrbyCriticbl(env, gidArrby, NULL);
        if (jgids == NULL) {
            if (gids != stbck_ids) {
                free(gids);
            }
            return;
        }
        for (i=0; i < glyphCnt; i++) {
            gids[i] = jgids[i];
        }
        XRenderFreeGlyphs(bwt_displby,
                          (GlyphSet) glyphSet, gids, glyphCnt);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, gidArrby,
                                              jgids, JNI_ABORT);
        if (gids != stbck_ids) {
            free(gids);
        }
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRenderCrebteGlyphSetNbtive
 (JNIEnv *env, jclbss cls, jlong formbt) {
  return XRenderCrebteGlyphSet(bwt_displby, (XRenderPictFormbt *) jlong_to_ptr(formbt));
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_XRenderCompositeTextNbtive
 (JNIEnv *env, jclbss cls, jint op, jint src, jint dst,
  jint sx, jint sy, jlong mbskFmt, jintArrby eltArrby,
  jintArrby  glyphIDArrby, jint eltCnt, jint glyphCnt) {
    jint i;
    jint *ids;
    jint *elts;
    XGlyphElt32 *xelts;
    unsigned int *xids;
    XGlyphElt32 selts[24];
    unsigned int sids[256];
    int chbrCnt = 0;

    if ((MAX_PAYLOAD / sizeof(XGlyphElt32) < (unsigned)eltCnt)
        || (MAX_PAYLOAD / sizeof(unsigned int) < (unsigned)glyphCnt)
        || ((MAX_PAYLOAD - sizeof(XGlyphElt32)*(unsigned)eltCnt) /
            sizeof(unsigned int) < (unsigned)glyphCnt))
    {
        /* (eltCnt, glyphCnt) too big, pbylobd overflow */
        return;
    }

    if (eltCnt <= 24) {
      xelts = &selts[0];
    }else {
      xelts = (XGlyphElt32 *) mblloc(sizeof(XGlyphElt32) * eltCnt);
      if (xelts == NULL) {
          return;
      }
    }

    if (glyphCnt <= 256) {
      xids = &sids[0];
    } else {
      xids = (unsigned int*)mblloc(sizeof(unsigned int) * glyphCnt);
      if (xids == NULL) {
          if (xelts != &selts[0]) {
            free(xelts);
          }
          return;
      }
    }

    if ((ids = (jint *)
         (*env)->GetPrimitiveArrbyCriticbl(env, glyphIDArrby, NULL)) == NULL) {
        if (xelts != &selts[0]) {
            free(xelts);
        }
        if (xids != &sids[0]) {
            free(xids);
        }
        return;
    }
    if ((elts = (jint *)
          (*env)->GetPrimitiveArrbyCriticbl(env, eltArrby, NULL)) == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env,
                                              glyphIDArrby, ids, JNI_ABORT);
        if (xelts != &selts[0]) {
            free(xelts);
        }
        if (xids != &sids[0]) {
            free(xids);
        }
        return;
    }

    for (i=0; i < glyphCnt; i++) {
      xids[i] = ids[i];
    }

    for (i=0; i < eltCnt; i++) {
      xelts[i].nchbrs = elts[i*4 + 0];
      xelts[i].xOff = elts[i*4 + 1];
      xelts[i].yOff = elts[i*4 + 2];
      xelts[i].glyphset = (GlyphSet) elts[i*4 + 3];
      xelts[i].chbrs = &xids[chbrCnt];

      chbrCnt += xelts[i].nchbrs;
    }

    XRenderCompositeText32(bwt_displby, op, (Picture) src, (Picture) dst,
                           (XRenderPictFormbt *) jlong_to_ptr(mbskFmt),
                            sx, sy, 0, 0, xelts, eltCnt);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphIDArrby, ids, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, eltArrby, elts, JNI_ABORT);

    if (xelts != &selts[0]) {
        free(xelts);
    }

    if (xids != &sids[0]) {
        free(xids);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_setGCMode
 (JNIEnv *env, jobject this, jlong gc, jboolebn copy) {
  GC xgc = (GC) jlong_to_ptr(gc);

  if (copy == JNI_TRUE) {
    XSetFunction(bwt_displby, xgc, GXcopy);
  } else {
    XSetFunction(bwt_displby, xgc, GXxor);
  }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_GCRectbnglesNbtive
 (JNIEnv *env, jclbss xsd, jint dst, jlong gc,
  jintArrby rectArrby, jint rectCnt) {
    int i;
    jint* rects;
    XRectbngle *xRects;
    XRectbngle sRects[256];

    if (rectCnt <= 256) {
      xRects = &sRects[0];
    } else {
      if (MAXUINT / sizeof(XRectbngle) < (unsigned)rectCnt) {
        /* rectCnt too big, integer overflow */
        return;
      }

      xRects = (XRectbngle *) mblloc(sizeof(XRectbngle) * rectCnt);
      if (xRects == NULL) {
        return;
      }
    }

    if ((rects = (jint*)
         (*env)->GetPrimitiveArrbyCriticbl(env, rectArrby, NULL)) == NULL) {
        if (xRects != &sRects[0]) {
            free(xRects);
        }
        return;
    }

    for (i=0; i < rectCnt; i++) {
      xRects[i].x = rects[i*4 + 0];
      xRects[i].y = rects[i*4 + 1];
      xRects[i].width = rects[i*4 + 2];
      xRects[i].height = rects[i*4 + 3];
    }

    XFillRectbngles(bwt_displby, (Drbwbble) dst, (GC) jlong_to_ptr(gc), xRects, rectCnt);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, rectArrby, rects, JNI_ABORT);
    if (xRects != &sRects[0]) {
      free(xRects);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_xr_XRBbckendNbtive_renderCompositeTrbpezoidsNbtive
 (JNIEnv *env, jclbss cls, jbyte op, jint src, jlong mbskFmt,
 jint dst, jint srcX, jint srcY, jintArrby  trbpArrby) {
    jint *trbps;

    if ((trbps = (jint *) (*env)->GetPrimitiveArrbyCriticbl(env, trbpArrby, NULL)) == NULL) {
      return;
    }

    XRenderCompositeTrbpezoids(bwt_displby, op, (Picture) src, (Picture) dst,
                               (XRenderPictFormbt *) jlong_to_ptr(mbskFmt),
                               srcX, srcY, (XTrbpezoid *) (trbps+5), trbps[0]);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, trbpArrby, trbps, JNI_ABORT);
}
