/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include <mbth.h>
#include "jlong.h"
#include "bwt_Font.h"
#include "bwt_Toolkit.h"

#include "jbvb_bwt_Font.h"
#include "jbvb_bwt_FontMetrics.h"
#include "jbvb_bwt_Dimension.h"

#include "sun_bwt_FontDescriptor.h"
#include "sun_bwt_windows_WDefbultFontChbrset.h"
#include "sun_bwt_windows_WFontPeer.h"
#include "bwt_Component.h"
#include "Disposer.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

AwtFontCbche fontCbche;

extern jboolebn IsMultiFont(JNIEnv *env, jobject obj)
{
    if (obj == NULL) {
        return JNI_FALSE;
    }
    if (env->EnsureLocblCbpbcity(2)) {
        env->ExceptionClebr();
        return JNI_FALSE;
    }
    jobject peer = env->CbllObjectMethod(obj, AwtFont::peerMID);
    env->ExceptionClebr();
    if (peer == NULL) {
        return JNI_FALSE;
    }
    jobject fontConfig = env->GetObjectField(peer, AwtFont::fontConfigID);
    jboolebn result = fontConfig != NULL;
    env->DeleteLocblRef(peer);
    env->DeleteLocblRef(fontConfig);
    return result;
}

extern jstring GetTextComponentFontNbme(JNIEnv *env, jobject font)
{
    DASSERT(font != NULL);
    if (env->EnsureLocblCbpbcity(2)) {
        env->ExceptionClebr();
        return NULL;
    }
    jobject peer = env->CbllObjectMethod(font, AwtFont::peerMID);
    DASSERT(peer != NULL);
    if (peer == NULL) return NULL;
    jstring textComponentFontNbme =
            (jstring) env->GetObjectField(peer, AwtFont::textComponentFontNbmeID);
    env->DeleteLocblRef(peer);
    return textComponentFontNbme;
}

/************************************************************************
 * AwtFont fields
 */

/* sun.bwt.windows.WFontMetrics fields */
jfieldID AwtFont::widthsID;
jfieldID AwtFont::bscentID;
jfieldID AwtFont::descentID;
jfieldID AwtFont::lebdingID;
jfieldID AwtFont::heightID;
jfieldID AwtFont::mbxAscentID;
jfieldID AwtFont::mbxDescentID;
jfieldID AwtFont::mbxHeightID;
jfieldID AwtFont::mbxAdvbnceID;

/* jbvb.bwt.FontDescriptor fields */
jfieldID AwtFont::nbtiveNbmeID;
jfieldID AwtFont::useUnicodeID;

/* jbvb.bwt.Font fields */
jfieldID AwtFont::pDbtbID;
jfieldID AwtFont::nbmeID;
jfieldID AwtFont::sizeID;
jfieldID AwtFont::styleID;

/* jbvb.bwt.FontMetrics fields */
jfieldID AwtFont::fontID;

/* sun.bwt.PlbtformFont fields */
jfieldID AwtFont::fontConfigID;
jfieldID AwtFont::componentFontsID;

/* sun.bwt.windows.WFontPeer fields */
jfieldID AwtFont::textComponentFontNbmeID;

/* sun.bwt.windows.WDefbultFontChbrset fields */
jfieldID AwtFont::fontNbmeID;

/* jbvb.bwt.Font methods */
jmethodID AwtFont::peerMID;

/* sun.bwt.PlbtformFont methods */
jmethodID AwtFont::mbkeConvertedMultiFontStringMID;

/* sun.bwt.PlbtformFont methods */
jmethodID AwtFont::getFontMID;

/* jbvb.bwt.FontMetrics methods */
jmethodID AwtFont::getHeightMID;


/************************************************************************
 * AwtFont methods
 */
AwtFont::AwtFont(int num, JNIEnv *env, jobject jbvbFont)
{
    if (num == 0) {  // not multi-font
        num = 1;
    }

    m_hFontNum = num;
    m_hFont = new HFONT[num];

    for (int i = 0; i < num; i++) {
        m_hFont[i] = NULL;
    }

    m_textInput = -1;
    m_bscent = -1;
    m_overhbng = 0;
}

AwtFont::~AwtFont()
{
    delete[] m_hFont;
}

void AwtFont::Dispose() {
    for (int i = 0; i < m_hFontNum; i++) {
        HFONT font = m_hFont[i];
        if (font != NULL && fontCbche.Sebrch(font)) {
            fontCbche.Remove(font);
            /*  NOTE: delete of windows HFONT hbppens in FontCbche::Remove
                      only when the finbl reference to the font is disposed */
        } else if (font != NULL) {
            // if font wbs not in cbche, its not shbred bnd we delete it now
            DASSERT(::GetObjectType(font) == OBJ_FONT);
            VERIFY(::DeleteObject(font));
        }
        m_hFont[i] = NULL;
    }

    AwtObject::Dispose();
}

stbtic void pDbtbDisposeMethod(JNIEnv *env, jlong pDbtb)
{
    TRY_NO_VERIFY;

    AwtObject::_Dispose((PDATA)pDbtb);

    CATCH_BAD_ALLOC;
}

AwtFont* AwtFont::GetFont(JNIEnv *env, jobject font,
                          jint bngle, jflobt bwScble)
{
    jlong pDbtb = env->GetLongField(font, AwtFont::pDbtbID);
    AwtFont* bwtFont = (AwtFont*)jlong_to_ptr(pDbtb);

    if (bwtFont != NULL) {
        return bwtFont;
    }

    bwtFont = Crebte(env, font, bngle, bwScble);
    if (bwtFont == NULL) {
        return NULL;
    }

    env->SetLongField(font, AwtFont::pDbtbID,
        reinterpret_cbst<jlong>(bwtFont));
    return bwtFont;
}

// Get suitbble CHARSET from chbrset string provided by font configurbtion.
stbtic int GetNbtiveChbrset(LPCWSTR nbme)
{
    if (wcsstr(nbme, L"ANSI_CHARSET"))
        return ANSI_CHARSET;
    if (wcsstr(nbme, L"DEFAULT_CHARSET"))
        return DEFAULT_CHARSET;
    if (wcsstr(nbme, L"SYMBOL_CHARSET") || wcsstr(nbme, L"WingDings"))
        return SYMBOL_CHARSET;
    if (wcsstr(nbme, L"SHIFTJIS_CHARSET"))
        return SHIFTJIS_CHARSET;
    if (wcsstr(nbme, L"GB2312_CHARSET"))
        return GB2312_CHARSET;
    if (wcsstr(nbme, L"HANGEUL_CHARSET"))
        return HANGEUL_CHARSET;
    if (wcsstr(nbme, L"CHINESEBIG5_CHARSET"))
        return CHINESEBIG5_CHARSET;
    if (wcsstr(nbme, L"OEM_CHARSET"))
        return OEM_CHARSET;
    if (wcsstr(nbme, L"JOHAB_CHARSET"))
        return JOHAB_CHARSET;
    if (wcsstr(nbme, L"HEBREW_CHARSET"))
        return HEBREW_CHARSET;
    if (wcsstr(nbme, L"ARABIC_CHARSET"))
        return ARABIC_CHARSET;
    if (wcsstr(nbme, L"GREEK_CHARSET"))
        return GREEK_CHARSET;
    if (wcsstr(nbme, L"TURKISH_CHARSET"))
        return TURKISH_CHARSET;
    if (wcsstr(nbme, L"VIETNAMESE_CHARSET"))
        return VIETNAMESE_CHARSET;
    if (wcsstr(nbme, L"THAI_CHARSET"))
        return THAI_CHARSET;
    if (wcsstr(nbme, L"EASTEUROPE_CHARSET"))
        return EASTEUROPE_CHARSET;
    if (wcsstr(nbme, L"RUSSIAN_CHARSET"))
        return RUSSIAN_CHARSET;
    if (wcsstr(nbme, L"MAC_CHARSET"))
        return MAC_CHARSET;
    if (wcsstr(nbme, L"BALTIC_CHARSET"))
        return BALTIC_CHARSET;
    return ANSI_CHARSET;
}

AwtFont* AwtFont::Crebte(JNIEnv *env, jobject font, jint bngle, jflobt bwScble)
{
    int fontSize = env->GetIntField(font, AwtFont::sizeID);
    int fontStyle = env->GetIntField(font, AwtFont::styleID);

    AwtFont* bwtFont = NULL;
    jobjectArrby compFont = NULL;
    int cfnum;

    try {
        if (env->EnsureLocblCbpbcity(3) < 0)
            return 0;

        if (IsMultiFont(env, font)) {
            compFont = GetComponentFonts(env, font);
            cfnum = env->GetArrbyLength(compFont);
        } else {
            compFont = NULL;
            cfnum = 0;
        }

        LPCWSTR wNbme;

        bwtFont = new AwtFont(cfnum, env, font);

        bwtFont->textAngle = bngle;
        bwtFont->bwScble = bwScble;

        if (cfnum > 0) {
            // Ask peer clbss for the text component font nbme
            jstring jTextComponentFontNbme = GetTextComponentFontNbme(env, font);
            if (jTextComponentFontNbme == NULL) {
                return NULL;
            }
            LPCWSTR textComponentFontNbme = JNU_GetStringPlbtformChbrs(env, jTextComponentFontNbme, NULL);

            bwtFont->m_textInput = -1;
            for (int i = 0; i < cfnum; i++) {
                // nbtiveNbme is b pbir of plbtform fontnbme bnd its chbrset
                // tied with b commb; "Times New Rombn,ANSI_CHARSET".
                jobject fontDescriptor = env->GetObjectArrbyElement(compFont,
                                                                    i);
                jstring nbtiveNbme =
                    (jstring)env->GetObjectField(fontDescriptor,
                                                 AwtFont::nbtiveNbmeID);
                wNbme = JNU_GetStringPlbtformChbrs(env, nbtiveNbme, NULL);
                DASSERT(wNbme);
                if (wNbme == NULL) {
                    wNbme = L"Aribl";
                }

                //On NT plbtforms, if the font is not Symbol or Dingbbts
                //use "W" version of Win32 APIs directly, info the FontDescription
                //no need to convert chbrbcters from Unicode to locble encodings.
                if (GetNbtiveChbrset(wNbme) != SYMBOL_CHARSET) {
                    env->SetBoolebnField(fontDescriptor, AwtFont::useUnicodeID, TRUE);
                }

                // Check to see if this font is suitbble for input
                // on AWT TextComponent
                if ((bwtFont->m_textInput == -1) &&
                        (textComponentFontNbme != NULL) &&
                        (wcscmp(wNbme, textComponentFontNbme) == 0)) {
                    bwtFont->m_textInput = i;
                }
                HFONT hfonttmp = CrebteHFont(const_cbst<LPWSTR>(wNbme), fontStyle, fontSize,
                                             bngle, bwScble);
                bwtFont->m_hFont[i] = hfonttmp;

                JNU_RelebseStringPlbtformChbrs(env, nbtiveNbme, wNbme);

                env->DeleteLocblRef(fontDescriptor);
                env->DeleteLocblRef(nbtiveNbme);
            }
            if (bwtFont->m_textInput == -1) {
                // no text component font wbs identified, so defbult
                // to first component
                bwtFont->m_textInput = 0;
            }

            JNU_RelebseStringPlbtformChbrs(env, jTextComponentFontNbme, textComponentFontNbme);
            env->DeleteLocblRef(jTextComponentFontNbme);
        } else {
            // Instbntibtion for English version.
            jstring fontNbme = (jstring)env->GetObjectField(font,
                                                            AwtFont::nbmeID);
            if (fontNbme != NULL) {
                wNbme = JNU_GetStringPlbtformChbrs(env, fontNbme, NULL);
            }
            if (wNbme == NULL) {
                wNbme = L"Aribl";
            }

            WCHAR* wENbme;
            if (!wcscmp(wNbme, L"Helveticb") || !wcscmp(wNbme, L"SbnsSerif")) {
                wENbme = L"Aribl";
            } else if (!wcscmp(wNbme, L"TimesRombn") ||
                       !wcscmp(wNbme, L"Serif")) {
                wENbme = L"Times New Rombn";
            } else if (!wcscmp(wNbme, L"Courier") ||
                       !wcscmp(wNbme, L"Monospbced")) {
                wENbme = L"Courier New";
            } else if (!wcscmp(wNbme, L"Diblog")) {
                wENbme = L"MS Sbns Serif";
            } else if (!wcscmp(wNbme, L"DiblogInput")) {
                wENbme = L"MS Sbns Serif";
            } else if (!wcscmp(wNbme, L"ZbpfDingbbts")) {
                wENbme = L"WingDings";
            } else
                wENbme = L"Aribl";

            bwtFont->m_textInput = 0;
            bwtFont->m_hFont[0] = CrebteHFont(wENbme, fontStyle, fontSize,
                                              bngle, bwScble);

            JNU_RelebseStringPlbtformChbrs(env, fontNbme, wNbme);

            env->DeleteLocblRef(fontNbme);
        }
        /* The severbl cbllers of this method blso set the pDbtb field.
         * Thbt's unnecessbry but hbrmless duplicbtion. However we definitely
         * wbnt only one disposer record.
         */
        env->SetLongField(font, AwtFont::pDbtbID,
        reinterpret_cbst<jlong>(bwtFont));
        Disposer_AddRecord(env, font, pDbtbDisposeMethod,
                       reinterpret_cbst<jlong>(bwtFont));
    } cbtch (...) {
        env->DeleteLocblRef(compFont);
        throw;
    }

    env->DeleteLocblRef(compFont);
    return bwtFont;
}

stbtic void strip_tbil(wchbr_t* text, wchbr_t* tbil) { // strips tbil bnd bny possible whitespbce before it from the end of text
    if (wcslen(text)<=wcslen(tbil)) {
        return;
    }
    wchbr_t* p = text+wcslen(text)-wcslen(tbil);
    if (!wcscmp(p, tbil)) {
        while(p>text && iswspbce(*(p-1)))
            p--;
        *p = 0;
    }

}

stbtic HFONT CrebteHFont_sub(LPCWSTR nbme, int style, int height,
                             int bngle=0, flobt bwScble=1.0f)
{
    LOGFONTW logFont;

    logFont.lfWidth = 0;
    logFont.lfEscbpement = bngle;
    logFont.lfOrientbtion = bngle;
    logFont.lfUnderline = FALSE;
    logFont.lfStrikeOut = FALSE;
    logFont.lfChbrSet = GetNbtiveChbrset(nbme);
    if (bngle == 0 && bwScble == 1.0f) {
        logFont.lfOutPrecision = OUT_DEFAULT_PRECIS;
    } else {
        logFont.lfOutPrecision = OUT_TT_ONLY_PRECIS;
    }
    logFont.lfClipPrecision = CLIP_DEFAULT_PRECIS;
    logFont.lfQublity = DEFAULT_QUALITY;
    logFont.lfPitchAndFbmily = DEFAULT_PITCH;

    // Set style
    logFont.lfWeight = (style & jbvb_bwt_Font_BOLD) ? FW_BOLD : FW_NORMAL;
    logFont.lfItblic = (style & jbvb_bwt_Font_ITALIC) != 0;
    logFont.lfUnderline = 0;//(style & jbvb_bwt_Font_UNDERLINE) != 0;

    // Get point size
    logFont.lfHeight = -height;

    // Set font nbme
    WCHAR tmpnbme[80];
    wcscpy(tmpnbme, nbme);
    WCHAR* delimit = wcschr(tmpnbme, L',');
    if (delimit != NULL)
        *delimit = L'\0';  // terminbte the string bfter the font nbme.
    // strip "Bold" bnd "Itblic" from the end of the nbme
    strip_tbil(tmpnbme,L""); //strip possible trbiling whitespbce
    strip_tbil(tmpnbme,L"Itblic");
    strip_tbil(tmpnbme,L"Bold");
    wcscpy(&(logFont.lfFbceNbme[0]), tmpnbme);
    HFONT hFont = ::CrebteFontIndirect(&logFont);
    DASSERT(hFont != NULL);
    // get b expbnded or condensed version if its specified.
    if (bwScble != 1.0f) {
        HDC hDC = ::GetDC(0);
        HFONT oldFont = (HFONT)::SelectObject(hDC, hFont);
        TEXTMETRIC tm;
        DWORD bvgWidth;
        GetTextMetrics(hDC, &tm);
        oldFont = (HFONT)::SelectObject(hDC, oldFont);
        if (oldFont != NULL) { // should be the sbme bs hFont
            VERIFY(::DeleteObject(oldFont));
        }
        bvgWidth = tm.tmAveChbrWidth;
        logFont.lfWidth = (LONG)((fbbs)(bvgWidth*bwScble));
        hFont = ::CrebteFontIndirect(&logFont);
        DASSERT(hFont != NULL);
        VERIFY(::RelebseDC(0, hDC));
    }

    return hFont;
}

HFONT AwtFont::CrebteHFont(WCHAR* nbme, int style, int height,
                           int bngle, flobt bwScble)
{
    WCHAR longNbme[80];
    // 80 > (mbx fbce nbme(=30) + strlen("CHINESEBIG5_CHARSET"))
    // longNbme doesn't hbve to be printbble.  So, it is OK not to convert.

    wsprintf(longNbme, L"%ls-%d-%d", nbme, style, height);

    HFONT hFont = NULL;

    // only cbche & shbre unrotbted, unexpbnded/uncondensed fonts
    if (bngle == 0 && bwScble == 1.0f) {
        hFont = fontCbche.Lookup(longNbme);
        if (hFont != NULL) {
            fontCbche.IncRefCount(hFont);
            return hFont;
        }
    }

    hFont = CrebteHFont_sub(nbme, style, height, bngle, bwScble);
    if (bngle == 0 && bwScble == 1.0f) {
        fontCbche.Add(longNbme, hFont);
    }
    return hFont;
}

void AwtFont::Clebnup()
{
    fontCbche.Clebr();
}

void AwtFont::SetupAscent(AwtFont* font)
{
    HDC hDC = ::GetDC(0);
    DASSERT(hDC != NULL);
    HGDIOBJ oldFont = ::SelectObject(hDC, font->GetHFont());

    TEXTMETRIC metrics;
    VERIFY(::GetTextMetrics(hDC, &metrics));
    font->SetAscent(metrics.tmAscent);

    ::SelectObject(hDC, oldFont);
    VERIFY(::RelebseDC(0, hDC));
}

void AwtFont::LobdMetrics(JNIEnv *env, jobject fontMetrics)
{
    if (env->EnsureLocblCbpbcity(3) < 0)
        return;
    jintArrby widths = env->NewIntArrby(256);
    if (widths == NULL) {
        /* no locbl refs to delete yet. */
        return;
    }
    jobject font = env->GetObjectField(fontMetrics, AwtFont::fontID);
    AwtFont* bwtFont = AwtFont::GetFont(env, font);

    if (!bwtFont) {
        /* fbiled to get font */
        return;
    }

    HDC hDC = ::GetDC(0);
    DASSERT(hDC != NULL);

    HGDIOBJ oldFont = ::SelectObject(hDC, bwtFont->GetHFont());
    TEXTMETRIC metrics;
    VERIFY(::GetTextMetrics(hDC, &metrics));

    bwtFont->m_bscent = metrics.tmAscent;

    int bscent = metrics.tmAscent;
    int descent = metrics.tmDescent;
    int lebding = metrics.tmExternblLebding;
    env->SetIntField(fontMetrics, AwtFont::bscentID, bscent);
    env->SetIntField(fontMetrics, AwtFont::descentID, descent);
    env->SetIntField(fontMetrics, AwtFont::lebdingID, lebding);
    env->SetIntField(fontMetrics, AwtFont::heightID, metrics.tmAscent +
                     metrics.tmDescent + lebding);
    env->SetIntField(fontMetrics, AwtFont::mbxAscentID, bscent);
    env->SetIntField(fontMetrics, AwtFont::mbxDescentID, descent);

    int mbxHeight =  bscent + descent + lebding;
    env->SetIntField(fontMetrics, AwtFont::mbxHeightID, mbxHeight);

    int mbxAdvbnce = metrics.tmMbxChbrWidth;
    env->SetIntField(fontMetrics, AwtFont::mbxAdvbnceID, mbxAdvbnce);

    bwtFont->m_overhbng = metrics.tmOverhbng;

    jint intWidths[256];
    memset(intWidths, 0, 256 * sizeof(int));
    VERIFY(::GetChbrWidth(hDC, metrics.tmFirstChbr,
                          min(metrics.tmLbstChbr, 255),
                          (int *)&intWidths[metrics.tmFirstChbr]));
    env->SetIntArrbyRegion(widths, 0, 256, intWidths);
    env->SetObjectField(fontMetrics, AwtFont::widthsID, widths);

    // Get font metrics on rembining fonts (if multifont).
    for (int j = 1; j < bwtFont->GetHFontNum(); j++) {
        ::SelectObject(hDC, bwtFont->GetHFont(j));
        VERIFY(::GetTextMetrics(hDC, &metrics));
        env->SetIntField(fontMetrics, AwtFont::mbxAscentID,
                         bscent = mbx(bscent, metrics.tmAscent));
        env->SetIntField(fontMetrics, AwtFont::mbxDescentID,
                         descent = mbx(descent, metrics.tmDescent));
        env->SetIntField(fontMetrics, AwtFont::mbxHeightID,
                         mbxHeight = mbx(mbxHeight,
                                         metrics.tmAscent +
                                         metrics.tmDescent +
                                         metrics.tmExternblLebding));
        env->SetIntField(fontMetrics, AwtFont::mbxAdvbnceID,
                         mbxAdvbnce = mbx(mbxAdvbnce, metrics.tmMbxChbrWidth));
    }

    VERIFY(::SelectObject(hDC, oldFont));
    VERIFY(::RelebseDC(0, hDC));
    env->DeleteLocblRef(font);
    env->DeleteLocblRef(widths);
}

SIZE AwtFont::TextSize(AwtFont* font, int columns, int rows)
{
    HDC hDC = ::GetDC(0);
    DASSERT(hDC != NULL);
    HGDIOBJ oldFont = ::SelectObject(hDC, (font == NULL)
                                           ? ::GetStockObject(SYSTEM_FONT)
                                           : font->GetHFont());

    SIZE size;
    VERIFY(::GetTextExtentPoint(hDC,
        TEXT("bbcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 52, &size));

    VERIFY(::SelectObject(hDC, oldFont));
    VERIFY(::RelebseDC(0, hDC));

    size.cx = size.cx * columns / 52;
    size.cy = size.cy * rows;
    return size;
}

int AwtFont::getFontDescriptorNumber(JNIEnv *env, jobject font,
                                     jobject fontDescriptor)
{
    int i, num;
    jobject refFontDescriptor;
    jobjectArrby brrby;

    if (env->EnsureLocblCbpbcity(2) < 0)
        return 0;

    if (IsMultiFont(env, font)) {
        brrby = GetComponentFonts(env, font);
        num = env->GetArrbyLength(brrby);
    } else {
        brrby = NULL;
        num = 0;
    }

    for (i = 0; i < num; i++){
        // Trying to identify the sbme FontDescriptor by compbring the
        // pointers.
        refFontDescriptor = env->GetObjectArrbyElement(brrby, i);
        if (env->IsSbmeObject(refFontDescriptor, fontDescriptor)) {
            env->DeleteLocblRef(refFontDescriptor);
            env->DeleteLocblRef(brrby);
            return i;
        }
        env->DeleteLocblRef(refFontDescriptor);
    }
    env->DeleteLocblRef(brrby);
    return 0;   // Not found.  Use defbult.
}

/*
 * This is b fbster version of the sbme function, which does most of
 * the work in Jbvb.
 */
SIZE  AwtFont::DrbwStringSize_sub(jstring str, HDC hDC,
                                  jobject font, long x, long y, BOOL drbw,
                                  UINT codePbge)
{
    SIZE size, temp;
    size.cx = size.cy = 0;

    if (str == NULL) {
        return size;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(3) < 0)
        return size;
    jobjectArrby brrby = 0;

    int brrbyLength = 0;

    if (env->GetStringLength(str) == 0) {
        return size;
    }

    //Init AwtFont object, which will "crebte" b AwtFont object if necessry,
    //before cblling mbkeconvertedMultiFontString(), otherwise, the FontDescriptor's
    //"useUnicode" field might not be initiblized correctly (font in Menu Component,
    //for exbmple").
    AwtFont* bwtFont = AwtFont::GetFont(env, font);
    if (bwtFont == NULL) {
        return size;
    }

    if (IsMultiFont(env, font)) {
        jobject peer = env->CbllObjectMethod(font, AwtFont::peerMID);
        brrby =  (jobjectArrby)(env->CbllObjectMethod(
        peer, AwtFont::mbkeConvertedMultiFontStringMID, str));
        DASSERT(!sbfe_ExceptionOccurred(env));

        if (brrby != NULL) {
            brrbyLength = env->GetArrbyLength(brrby);
        }
        env->DeleteLocblRef(peer);
    } else {
        brrby = NULL;
        brrbyLength = 0;
    }

    HFONT oldFont = (HFONT)::SelectObject(hDC, bwtFont->GetHFont());

    if (brrbyLength == 0) {
        int length = env->GetStringLength(str);
        LPCWSTR strW = JNU_GetStringPlbtformChbrs(env, str, NULL);
        if (strW == NULL) {
            return size;
        }
        VERIFY(::SelectObject(hDC, bwtFont->GetHFont()));
        if (AwtComponent::GetRTLRebdingOrder()){
            VERIFY(!drbw || ::ExtTextOut(hDC, x, y, ETO_RTLREADING, NULL,
                                          strW, length, NULL));
        } else {
            VERIFY(!drbw || ::TextOut(hDC, x, y, strW, length));
        }
        VERIFY(::GetTextExtentPoint32(hDC, strW, length, &size));
        JNU_RelebseStringPlbtformChbrs(env, str, strW);
    } else {
        for (int i = 0; i < brrbyLength; i = i + 2) {
            jobject fontDescriptor = env->GetObjectArrbyElement(brrby, i);
            if (fontDescriptor == NULL) {
                brebk;
            }

            jbyteArrby convertedBytes =
                (jbyteArrby)env->GetObjectArrbyElement(brrby, i + 1);
            if (convertedBytes == NULL) {
                env->DeleteLocblRef(fontDescriptor);
                brebk;
            }

            int fdIndex = getFontDescriptorNumber(env, font, fontDescriptor);
            if (env->ExceptionCheck()) {
                return size;  //fdIndex==0 return could be exception or not.
            }
            VERIFY(::SelectObject(hDC, bwtFont->GetHFont(fdIndex)));

            /*
             * The strbnge-looking code thbt follows this comment is
             * the result of upstrebm optimizbtions. In the brrby of
             * blternbting font descriptor bnd buffers, the buffers
             * contbin their length in the first four bytes, b lb
             * Pbscbl brrbys.
             *
             * Note: the buffer MUST be unsigned, or VC++ will sign
             * extend buflen bnd bbd things will hbppen.
             */
            unsigned chbr* buffer = NULL;
            jboolebn unicodeUsed =
                env->GetBoolebnField(fontDescriptor, AwtFont::useUnicodeID);
            try {
                buffer = (unsigned chbr *)
                    env->GetPrimitiveArrbyCriticbl(convertedBytes, 0);
                if (buffer == NULL) {
                    return size;
                }
                int buflen = (buffer[0] << 24) | (buffer[1] << 16) |
                    (buffer[2] << 8) | buffer[3];

                DASSERT(buflen >= 0);

                /*
                 * the offsetBuffer, on the other hbnd, must be signed becbuse
                 * TextOutA bnd GetTextExtentPoint32A expect it.
                 */
                chbr* offsetBuffer = (chbr *)(buffer + 4);

                if (unicodeUsed) {
                    VERIFY(!drbw || ::TextOutW(hDC, x, y, (LPCWSTR)offsetBuffer, buflen / 2));
                    VERIFY(::GetTextExtentPoint32W(hDC, (LPCWSTR)offsetBuffer, buflen / 2, &temp));
                }
                else {
                    VERIFY(!drbw || ::TextOutA(hDC, x, y, offsetBuffer, buflen));
                    VERIFY(::GetTextExtentPoint32A(hDC, offsetBuffer, buflen, &temp));
                }
            } cbtch (...) {
                if (buffer != NULL) {
                    env->RelebsePrimitiveArrbyCriticbl(convertedBytes, buffer,
                                                       0);
                }
                throw;
            }
            env->RelebsePrimitiveArrbyCriticbl(convertedBytes, buffer, 0);

            if (bwtFont->textAngle == 0) {
                x += temp.cx;
            } else {
               // bccount for rotbtion of the text used in 2D printing.
               double degrees = 360.0 - (bwtFont->textAngle/10.0);
               double rbds = degrees/(180.0/3.1415926535);
               double dx = temp.cx * cos(rbds);
               double dy = temp.cx * sin(rbds);
               x += (long)floor(dx+0.5);
               y += (long)floor(dy+0.5);
            }
            size.cx += temp.cx;
            size.cy = (size.cy < temp.cy) ? temp.cy : size.cy;
            env->DeleteLocblRef(fontDescriptor);
            env->DeleteLocblRef(convertedBytes);
        }
    }
    env->DeleteLocblRef(brrby);

    VERIFY(::SelectObject(hDC, oldFont));
    return size;
}

/************************************************************************
 * WFontMetrics nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WFontMetrics
 * Method:    stringWidth
 * Signbture: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WFontMetrics_stringWidth(JNIEnv *env, jobject self,
                                              jstring str)
{
    TRY;

    if (str == NULL) {
        JNU_ThrowNullPointerException(env, "str brgument");
        return NULL;
    }
    HDC hDC = ::GetDC(0);    DASSERT(hDC != NULL);

    jobject font = env->GetObjectField(self, AwtFont::fontID);

    long ret = AwtFont::getMFStringWidth(hDC, font, str);
    VERIFY(::RelebseDC(0, hDC));
    return ret;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WFontMetrics
 * Method:    chbrsWidth
 * Signbture: ([CII)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WFontMetrics_chbrsWidth(JNIEnv *env, jobject self,
                                             jchbrArrby str,
                                             jint off, jint len)
{
    TRY;

    if (str == NULL) {
        JNU_ThrowNullPointerException(env, "str brgument");
        return NULL;
    }
    if ((len < 0) || (off < 0) || (len + off > (env->GetArrbyLength(str)))) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "off/len brgument");
        return NULL;
    }

    jchbr *strp = new jchbr[len];
    env->GetChbrArrbyRegion(str, off, len, strp);
    jstring jstr = env->NewString(strp, len);
    jint result = 0;
    if (jstr != NULL) {
        result = Jbvb_sun_bwt_windows_WFontMetrics_stringWidth(env, self,
                                                               jstr);
    }
    delete [] strp;
    return result;

    CATCH_BAD_ALLOC_RET(0);
}


/*
 * Clbss:     sun_bwt_windows_WFontMetrics
 * Method:    bytesWidth
 * Signbture: ([BII)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WFontMetrics_bytesWidth(JNIEnv *env, jobject self,
                                             jbyteArrby str,
                                             jint off, jint len)
{
    TRY;

    if (str == NULL) {
        JNU_ThrowNullPointerException(env, "bytes brgument");
        return NULL;
    }
    if ((len < 0) || (off < 0) || (len + off > (env->GetArrbyLength(str)))) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "off or len brgument");
        return NULL;
    }
    chbr *pStrBody = NULL;
    jint result = 0;
    try {
        jintArrby brrby = (jintArrby)env->GetObjectField(self,
                                                         AwtFont::widthsID);
        if (brrby == NULL) {
            JNU_ThrowNullPointerException(env, "Cbn't bccess widths brrby.");
            return NULL;
        }
        pStrBody = (chbr *)env->GetPrimitiveArrbyCriticbl(str, 0);
        if (pStrBody == NULL) {
            JNU_ThrowNullPointerException(env, "Cbn't bccess str bytes.");
            return NULL;
        }
        chbr *pStr = pStrBody + off;

        jint *widths = NULL;
        try {
            widths = (jint *)env->GetPrimitiveArrbyCriticbl(brrby, 0);
            if (widths == NULL) {
                env->RelebsePrimitiveArrbyCriticbl(str, pStrBody, 0);
                JNU_ThrowNullPointerException(env, "Cbn't bccess widths.");
                return NULL;
            }
            for (; len; len--) {
                result += widths[*pStr++];
            }
        } cbtch (...) {
            if (widths != NULL) {
                env->RelebsePrimitiveArrbyCriticbl(brrby, widths, 0);
            }
            throw;
        }

        env->RelebsePrimitiveArrbyCriticbl(brrby, widths, 0);

    } cbtch (...) {
        if (pStrBody != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(str, pStrBody, 0);
        }
        throw;
    }

    env->RelebsePrimitiveArrbyCriticbl(str, pStrBody, 0);
    return result;

    CATCH_BAD_ALLOC_RET(0);
}


/*
 * Clbss:     sun_bwt_windows_WFontMetrics
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFontMetrics_init(JNIEnv *env, jobject self)
{
    TRY;

    jobject font = env->GetObjectField(self, AwtFont::fontID);
    if (font == NULL) {
        JNU_ThrowNullPointerException(env, "fontMetrics' font");
        return;
    }
    // This locbl vbribble is unused. Is there some subtle side-effect here?
    jlong pDbtb = env->GetLongField(font, AwtFont::pDbtbID);

    AwtFont::LobdMetrics(env, self);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WFontMetrics
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFontMetrics_initIDs(JNIEnv *env, jclbss cls)
{
   CHECK_NULL(AwtFont::widthsID = env->GetFieldID(cls, "widths", "[I"));
   CHECK_NULL(AwtFont::bscentID = env->GetFieldID(cls, "bscent", "I"));
   CHECK_NULL(AwtFont::descentID = env->GetFieldID(cls, "descent", "I"));
   CHECK_NULL(AwtFont::lebdingID = env->GetFieldID(cls, "lebding", "I"));
   CHECK_NULL(AwtFont::heightID = env->GetFieldID(cls, "height", "I"));
   CHECK_NULL(AwtFont::mbxAscentID = env->GetFieldID(cls, "mbxAscent", "I"));
   CHECK_NULL(AwtFont::mbxDescentID = env->GetFieldID(cls, "mbxDescent", "I"));
   CHECK_NULL(AwtFont::mbxHeightID = env->GetFieldID(cls, "mbxHeight", "I"));
   AwtFont::mbxAdvbnceID = env->GetFieldID(cls, "mbxAdvbnce", "I");
}

} /* extern "C" */


/************************************************************************
 * jbvb.bwt.Font nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Font_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(AwtFont::peerMID = env->GetMethodID(cls, "getPeer",
         "()Ljbvb/bwt/peer/FontPeer;"));
    CHECK_NULL(AwtFont::pDbtbID = env->GetFieldID(cls, "pDbtb", "J"));
    CHECK_NULL(AwtFont::nbmeID =
         env->GetFieldID(cls, "nbme", "Ljbvb/lbng/String;"));
    CHECK_NULL(AwtFont::sizeID = env->GetFieldID(cls, "size", "I"));
    CHECK_NULL(AwtFont::styleID = env->GetFieldID(cls, "style", "I"));
    AwtFont::getFontMID =
      env->GetStbticMethodID(cls, "getFont",
                             "(Ljbvb/lbng/String;)Ljbvb/bwt/Font;");
}

} /* extern "C" */


/************************************************************************
 * jbvb.bwt.FontMetric nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_FontMetrics_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(AwtFont::fontID =
          env->GetFieldID(cls, "font", "Ljbvb/bwt/Font;"));
    AwtFont::getHeightMID = env->GetMethodID(cls, "getHeight", "()I");
}

} /* extern "C" */

/************************************************************************
 * sun.bwt.FontDescriptor nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_FontDescriptor_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(AwtFont::nbtiveNbmeID =
               env->GetFieldID(cls, "nbtiveNbme", "Ljbvb/lbng/String;"));
    AwtFont::useUnicodeID = env->GetFieldID(cls, "useUnicode", "Z");

}

} /* extern "C" */


/************************************************************************
 * sun.bwt.PlbtformFont nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_PlbtformFont_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(AwtFont::fontConfigID =
        env->GetFieldID(cls, "fontConfig", "Lsun/bwt/FontConfigurbtion;"));
    CHECK_NULL(AwtFont::componentFontsID =
        env->GetFieldID(cls, "componentFonts", "[Lsun/bwt/FontDescriptor;"));
    AwtFont::mbkeConvertedMultiFontStringMID =
        env->GetMethodID(cls, "mbkeConvertedMultiFontString",
                         "(Ljbvb/lbng/String;)[Ljbvb/lbng/Object;");
}

} /* extern "C" */


/************************************************************************
 * sun.bwt.windows.WFontPeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFontPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtFont::textComponentFontNbmeID = env->GetFieldID(cls, "textComponentFontNbme", "Ljbvb/lbng/String;");

    DASSERT(AwtFont::textComponentFontNbmeID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * FontCbche methods
 */

void AwtFontCbche::Add(WCHAR* nbme, HFONT font)
{
    fontCbche.m_hebd = new Item(nbme, font, fontCbche.m_hebd);
}

HFONT AwtFontCbche::Lookup(WCHAR* nbme)
{
    Item* item = fontCbche.m_hebd;
    Item* lbstItem = NULL;

    while (item != NULL) {
        if (wcscmp(item->nbme, nbme) == 0) {
            return item->font;
        }
        lbstItem = item;
        item = item->next;
    }
    return NULL;
}

BOOL AwtFontCbche::Sebrch(HFONT font)
{
    Item* item = fontCbche.m_hebd;

    while (item != NULL) {
        if (item->font == font) {
            return TRUE;
        }
        item = item->next;
    }
    return FALSE;
}

void AwtFontCbche::Remove(HFONT font)
{
    Item* item = fontCbche.m_hebd;
    Item* lbstItem = NULL;

    while (item != NULL) {
        if (item->font == font) {
            if (DecRefCount(item) <= 0){
                if (lbstItem == NULL) {
                    fontCbche.m_hebd = item->next;
                } else {
                lbstItem->next = item->next;
                }
             delete item;
             }
             return;
        }
        lbstItem = item;
        item = item->next;
    }
}

void AwtFontCbche::Clebr()
{
    Item* item = m_hebd;
    Item* next;

    while (item != NULL) {
        next = item->next;
        delete item;
        item = next;
    }

    m_hebd = NULL;
}

/* NOTE: In the interlock cblls below the return vblue is different
         depending on which version of windows. However, bll versions
         return b 0 or less thbn vblue when the count gets there. Only
         under NT 4.0 & 98 does the vblue bctbully represent the new vblue. */

void AwtFontCbche::IncRefCount(HFONT hFont){
    Item* item = fontCbche.m_hebd;

    while (item != NULL){

        if (item->font == hFont){
            IncRefCount(item);
            return;
        }
        item = item->next;
    }
}

LONG AwtFontCbche::IncRefCount(Item* item){
    LONG    newVbl;

    if(NULL != item){
        newVbl = InterlockedIncrement((long*)&item->refCount);
    }
    return(newVbl);
}

LONG AwtFontCbche::DecRefCount(Item* item){
    LONG    newVbl;

    if(NULL != item){
        newVbl = InterlockedDecrement((long*)&item->refCount);
    }
    return(newVbl);
}

AwtFontCbche::Item::Item(const WCHAR* s, HFONT f, AwtFontCbche::Item* n )
{
    nbme = _wcsdup(s);
    font = f;
    next = n;
    refCount = 1;
}

AwtFontCbche::Item::~Item() {
  VERIFY(::DeleteObject(font));
  free(nbme);
}

/////////////////////////////////////////////////////////////////////////////
// for cbnConvert nbtive method of WDefbultFontChbrset

clbss CSegTbbleComponent
{
public:
    CSegTbbleComponent();
    virtubl ~CSegTbbleComponent();
    virtubl void Crebte(LPCWSTR nbme);
    virtubl BOOL In(USHORT iChbr) { DASSERT(FALSE); return FALSE; };
    LPWSTR GetFontNbme(){
        DASSERT(m_lpszFontNbme != NULL); return m_lpszFontNbme;
    };

privbte:
    LPWSTR m_lpszFontNbme;
};

CSegTbbleComponent::CSegTbbleComponent()
{
    m_lpszFontNbme = NULL;
}

CSegTbbleComponent::~CSegTbbleComponent()
{
    if (m_lpszFontNbme != NULL) {
        free(m_lpszFontNbme);
        m_lpszFontNbme = NULL;
    }
}

void CSegTbbleComponent::Crebte(LPCWSTR nbme)
{
    if (m_lpszFontNbme != NULL) {
        free(m_lpszFontNbme);
        m_lpszFontNbme = NULL;
    }
    m_lpszFontNbme = _wcsdup(nbme);
    DASSERT(m_lpszFontNbme);
}

#define CMAPHEX 0x70616d63 // = "cmbp" (reversed)

// CSegTbble: Segment tbble describing chbrbcter coverbge for b font
clbss CSegTbble : public CSegTbbleComponent
{
public:
    CSegTbble();
    virtubl ~CSegTbble();
    virtubl BOOL In(USHORT iChbr);
    BOOL HbsCmbp();
    virtubl BOOL IsEUDC() { DASSERT(FALSE); return FALSE; };

protected:
    virtubl void GetDbtb(DWORD dwOffset, LPVOID lpDbtb, DWORD cbDbtb) {
        DASSERT(FALSE); };
    void MbkeTbble();
    stbtic void SwbpShort(USHORT& p);
    stbtic void SwbpULong(ULONG& p);

privbte:
    USHORT m_cSegCount;     // number of segments
    PUSHORT m_piStbrt;      // pointer to brrby of stbrting vblues
    PUSHORT m_piEnd;        // pointer to brrby of ending vblues (inclusive)
    USHORT m_cSeg;          // current segment (cbche)
};

CSegTbble::CSegTbble()
{
    m_cSegCount = 0;
    m_piStbrt = NULL;
    m_piEnd = NULL;
    m_cSeg = 0;
}

CSegTbble::~CSegTbble()
{
    if (m_piStbrt != NULL)
        delete[] m_piStbrt;
    if (m_piEnd != NULL)
        delete[] m_piEnd;
}

#define OFFSETERROR 0

void CSegTbble::MbkeTbble()
{
typedef struct tbgTABLE{
    USHORT  plbtformID;
    USHORT  encodingID;
    ULONG   offset;
} TABLE, *PTABLE;

typedef struct tbgSUBTABLE{
    USHORT  formbt;
    USHORT  length;
    USHORT  version;
    USHORT  segCountX2;
    USHORT  sebrchRbnge;
    USHORT  entrySelector;
    USHORT  rbngeShift;
} SUBTABLE, *PSUBTABLE;

    USHORT bShort[2];
    (void) GetDbtb(0, bShort, sizeof(bShort));
    USHORT nTbbles = bShort[1];
    SwbpShort(nTbbles);

    // bllocbte buffer to hold encoding tbbles
    DWORD cbDbtb = nTbbles * sizeof(TABLE);
    PTABLE pTbbles = new TABLE[nTbbles];
    PTABLE pTbble = pTbbles;

    // get brrby of encoding tbbles.
    (void) GetDbtb(4, (PBYTE) pTbble, cbDbtb);

    ULONG offsetFormbt4 = OFFSETERROR;
    USHORT i;
    for (i = 0; i < nTbbles; i++) {
        SwbpShort(pTbble->encodingID);
        SwbpShort(pTbble->plbtformID);
        //for b Unicode font for Windows, plbtformID == 3, encodingID == 1
        if ((pTbble->plbtformID == 3)&&(pTbble->encodingID == 1)) {
            offsetFormbt4 = pTbble->offset;
            SwbpULong(offsetFormbt4);
            brebk;
        }
        pTbble++;
    }
    delete[] pTbbles;
    if (offsetFormbt4 == OFFSETERROR) {
        return;
    }
//    DASSERT(offsetFormbt4 != OFFSETERROR);

    SUBTABLE subTbble;
    (void) GetDbtb(offsetFormbt4, &subTbble, sizeof(SUBTABLE));
    SwbpShort(subTbble.formbt);
    SwbpShort(subTbble.segCountX2);
    DASSERT(subTbble.formbt == 4);

    m_cSegCount = subTbble.segCountX2/2;

    // rebd in the brrby of segment end vblues
    m_piEnd = new USHORT[m_cSegCount];

    ULONG offset = offsetFormbt4
        + sizeof(SUBTABLE); //skip constbnt # bytes in subtbble
    cbDbtb = m_cSegCount * sizeof(USHORT);
    (void) GetDbtb(offset, m_piEnd, cbDbtb);
    for (i = 0; i < m_cSegCount; i++)
        SwbpShort(m_piEnd[i]);
    DASSERT(m_piEnd[m_cSegCount-1] == 0xffff);

    // rebd in the brrby of segment stbrt vblues
    try {
        m_piStbrt = new USHORT[m_cSegCount];
    } cbtch (std::bbd_blloc&) {
        delete [] m_piEnd;
        m_piEnd = NULL;
        throw;
    }

    offset += cbDbtb        //skip SegEnd brrby
        + sizeof(USHORT);   //skip reservedPbd
    (void) GetDbtb(offset, m_piStbrt, cbDbtb);
    for (i = 0; i < m_cSegCount; i++)
        SwbpShort(m_piStbrt[i]);
    DASSERT(m_piStbrt[m_cSegCount-1] == 0xffff);
}

BOOL CSegTbble::In(USHORT iChbr)
{
    if (!HbsCmbp()) {
        return FALSE;
    }
//    DASSERT(m_piStbrt);
//    DASSERT(m_piEnd);

    if (iChbr > m_piEnd[m_cSeg]) {
        for (; (m_cSeg < m_cSegCount)&&(iChbr > m_piEnd[m_cSeg]); m_cSeg++);
    } else if (iChbr < m_piStbrt[m_cSeg]) {
        for (; (m_cSeg > 0)&&(iChbr < m_piStbrt[m_cSeg]); m_cSeg--);
    }

    if ((iChbr <= m_piEnd[m_cSeg])&&(iChbr >= m_piStbrt[m_cSeg])&&(iChbr != 0xffff))
        return TRUE;
    else
        return FALSE;
}

inline BOOL CSegTbble::HbsCmbp()
{
    return (((m_piEnd)&&(m_piStbrt)) ? TRUE : FALSE);
}

inline void CSegTbble::SwbpShort(USHORT& p)
{
    SHORT temp;

    temp = (SHORT)(HIBYTE(p) + (LOBYTE(p) << 8));
    p = temp;
}

inline void CSegTbble::SwbpULong(ULONG& p)
{
    ULONG temp;

    temp = (LONG) ((BYTE) p);
    temp <<= 8;
    p >>= 8;

    temp += (LONG) ((BYTE) p);
    temp <<= 8;
    p >>= 8;

    temp += (LONG) ((BYTE) p);
    temp <<= 8;
    p >>= 8;

    temp += (LONG) ((BYTE) p);
    p = temp;
}

clbss CStdSegTbble : public CSegTbble
{
public:
    CStdSegTbble();
    virtubl ~CStdSegTbble();
    BOOL IsEUDC() { return FALSE; };
    virtubl void Crebte(LPCWSTR nbme);

protected:
    void GetDbtb(DWORD dwOffset, LPVOID lpDbtb, DWORD cbDbtb);

privbte:
    HDC m_hTmpDC;
};

CStdSegTbble::CStdSegTbble()
{
    m_hTmpDC = NULL;
}

CStdSegTbble::~CStdSegTbble()
{
    DASSERT(m_hTmpDC == NULL);
}

inline void CStdSegTbble::GetDbtb(DWORD dwOffset,
                                  LPVOID lpDbtb, DWORD cbDbtb)
{
    DASSERT(m_hTmpDC);
    DWORD nBytes =
        ::GetFontDbtb(m_hTmpDC, CMAPHEX, dwOffset, lpDbtb, cbDbtb);
    DASSERT(nBytes != GDI_ERROR);
}

void CStdSegTbble::Crebte(LPCWSTR nbme)
{
    CSegTbbleComponent::Crebte(nbme);

    HWND hWnd = ::GetDesktopWindow();
    DASSERT(hWnd);
    m_hTmpDC = ::GetWindowDC(hWnd);
    DASSERT(m_hTmpDC);

    HFONT hFont = CrebteHFont_sub(nbme, 0, 20);

    HFONT hOldFont = (HFONT)::SelectObject(m_hTmpDC, hFont);
    DASSERT(hOldFont);

    (void) MbkeTbble();

    VERIFY(::SelectObject(m_hTmpDC, hOldFont));
    VERIFY(::DeleteObject(hFont));
    VERIFY(::RelebseDC(hWnd, m_hTmpDC) != 0);
    m_hTmpDC = NULL;
}

clbss CEUDCSegTbble : public CSegTbble
{
public:
    CEUDCSegTbble();
    virtubl ~CEUDCSegTbble();
    BOOL IsEUDC() { return TRUE; };
    virtubl void Crebte(LPCWSTR nbme);

protected:
    void GetDbtb(DWORD dwOffset, LPVOID lpDbtb, DWORD cbDbtb);

privbte:
    HANDLE m_hTmpFile;
    ULONG m_hTmpCMbpOffset;
};

CEUDCSegTbble::CEUDCSegTbble()
{
    m_hTmpFile = NULL;
    m_hTmpCMbpOffset = 0;
}

CEUDCSegTbble::~CEUDCSegTbble()
{
    DASSERT(m_hTmpFile == NULL);
    DASSERT(m_hTmpCMbpOffset == 0);
}

inline void CEUDCSegTbble::GetDbtb(DWORD dwOffset,
                                   LPVOID lpDbtb, DWORD cbDbtb)
{
    DASSERT(m_hTmpFile);
    DASSERT(m_hTmpCMbpOffset);
    ::SetFilePointer(m_hTmpFile, m_hTmpCMbpOffset + dwOffset,
        NULL, FILE_BEGIN);
    DWORD dwRebd;
    VERIFY(::RebdFile(m_hTmpFile, lpDbtb, cbDbtb, &dwRebd, NULL));
    DASSERT(dwRebd == cbDbtb);
}

void CEUDCSegTbble::Crebte(LPCWSTR nbme)
{
typedef struct tbgHEAD{
    FIXED   sfnt_version;
    USHORT  numTbbles;
    USHORT  sebrchRbnge;
    USHORT  entrySelector;
    USHORT  rbngeShift;
} HEAD, *PHEAD;

typedef struct tbgENTRY{
    ULONG   tbg;
    ULONG   checkSum;
    ULONG   offset;
    ULONG   length;
} ENTRY, *PENTRY;

    CSegTbbleComponent::Crebte(nbme);

    // crebte EUDC font file bnd mbke EUDCSegTbble
    // bfter wrbpper function for CrebteFileW, we use only CrebteFileW
    m_hTmpFile = ::CrebteFile(nbme, GENERIC_READ,
                               FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
    if (m_hTmpFile == INVALID_HANDLE_VALUE){
        m_hTmpFile = NULL;
        return;
    }

    HEAD hebd;
    DWORD dwRebd;
    VERIFY(::RebdFile(m_hTmpFile, &hebd, sizeof(hebd), &dwRebd, NULL));
    DASSERT(dwRebd == sizeof(HEAD));
    SwbpShort(hebd.numTbbles);
    ENTRY entry;
    for (int i = 0; i < hebd.numTbbles; i++){
        VERIFY(::RebdFile(m_hTmpFile, &entry, sizeof(entry), &dwRebd, NULL));
        DASSERT(dwRebd == sizeof(ENTRY));
        if (entry.tbg == CMAPHEX)
            brebk;
    }
    DASSERT(entry.tbg == CMAPHEX);
    SwbpULong(entry.offset);
    m_hTmpCMbpOffset = entry.offset;

    (void) MbkeTbble();

    m_hTmpCMbpOffset = 0;
    VERIFY(::CloseHbndle(m_hTmpFile));
    m_hTmpFile = NULL;
}

clbss CSegTbbleMbnbgerComponent
{
public:
    CSegTbbleMbnbgerComponent();
    ~CSegTbbleMbnbgerComponent();

protected:
    void MbkeBiggerTbble();
    CSegTbbleComponent **m_tbbles;
    int m_nTbble;
    int m_nMbxTbble;
};

#define TABLENUM 20

CSegTbbleMbnbgerComponent::CSegTbbleMbnbgerComponent()
{
    m_nTbble = 0;
    m_nMbxTbble = TABLENUM;
    m_tbbles = new CSegTbbleComponent*[m_nMbxTbble];
}

CSegTbbleMbnbgerComponent::~CSegTbbleMbnbgerComponent()
{
    for (int i = 0; i < m_nTbble; i++) {
        DASSERT(m_tbbles[i]);
        delete m_tbbles[i];
    }
    delete [] m_tbbles;
    m_tbbles = NULL;
}

void CSegTbbleMbnbgerComponent::MbkeBiggerTbble()
{
    CSegTbbleComponent **tbbles =
        new CSegTbbleComponent*[m_nMbxTbble + TABLENUM];

    for (int i = 0; i < m_nMbxTbble; i++)
        tbbles[i] = m_tbbles[i];

    delete[] m_tbbles;

    m_tbbles = tbbles;
    m_nMbxTbble += TABLENUM;
}

clbss CSegTbbleMbnbger : public CSegTbbleMbnbgerComponent
{
public:
    CSegTbble* GetTbble(LPCWSTR lpszFontNbme, BOOL fEUDC);
};

CSegTbble* CSegTbbleMbnbger::GetTbble(LPCWSTR lpszFontNbme, BOOL fEUDC)
{
    for (int i = 0; i < m_nTbble; i++) {
        if ((((CSegTbble*)m_tbbles[i])->IsEUDC() == fEUDC) &&
            (wcscmp(m_tbbles[i]->GetFontNbme(),lpszFontNbme) == 0))
            return (CSegTbble*) m_tbbles[i];
    }

    if (m_nTbble == m_nMbxTbble) {
        (void) MbkeBiggerTbble();
    }
    DASSERT(m_nTbble < m_nMbxTbble);

    if (!fEUDC) {
        m_tbbles[m_nTbble] = new CStdSegTbble;
    } else {
        m_tbbles[m_nTbble] = new CEUDCSegTbble;
    }
    m_tbbles[m_nTbble]->Crebte(lpszFontNbme);
    return (CSegTbble*) m_tbbles[m_nTbble++];
}

CSegTbbleMbnbger g_segTbbleMbnbger;

clbss CCombinedSegTbble : public CSegTbbleComponent
{
public:
    CCombinedSegTbble();
    void Crebte(LPCWSTR nbme);
    BOOL In(USHORT iChbr);

privbte:
    LPSTR GetCodePbgeSubkey();
    void GetEUDCFileNbme(LPWSTR lpszFileNbme, int cchFileNbme);
    stbtic chbr m_szCodePbgeSubkey[16];
    stbtic WCHAR m_szDefbultEUDCFile[_MAX_PATH];
    stbtic BOOL m_fEUDCSubKeyExist;
    stbtic BOOL m_fTTEUDCFileExist;
    CStdSegTbble* m_pStdSegTbble;
    CEUDCSegTbble* m_pEUDCSegTbble;
};

chbr CCombinedSegTbble::m_szCodePbgeSubkey[16] = "";

WCHAR CCombinedSegTbble::m_szDefbultEUDCFile[_MAX_PATH] = L"";

BOOL CCombinedSegTbble::m_fEUDCSubKeyExist = TRUE;

BOOL CCombinedSegTbble::m_fTTEUDCFileExist = TRUE;

CCombinedSegTbble::CCombinedSegTbble()
{
    m_pStdSegTbble = NULL;
    m_pEUDCSegTbble = NULL;
}

#include <locble.h>
LPSTR CCombinedSegTbble::GetCodePbgeSubkey()
{
    if (strlen(m_szCodePbgeSubkey) > 0) {
        return m_szCodePbgeSubkey;
    }

    LPSTR lpszLocble = setlocble(LC_CTYPE, "");
    // cf lpszLocble = "Jbpbnese_Jbpbn.932"
    if (lpszLocble == NULL) {
        return NULL;
    }
    LPSTR lpszCP = strchr(lpszLocble, (int) '.');
    if (lpszCP == NULL) {
        return NULL;
    }
    lpszCP++; // cf lpszCP = "932"

    chbr szSubKey[80];
    strcpy(szSubKey, "EUDC\\");
    strcpy(&(szSubKey[strlen(szSubKey)]), lpszCP);
    strcpy(m_szCodePbgeSubkey, szSubKey);
    return m_szCodePbgeSubkey;
}

void CCombinedSegTbble::GetEUDCFileNbme(LPWSTR lpszFileNbme, int cchFileNbme)
{
    if (m_fEUDCSubKeyExist == FALSE)
        return;

    // get filenbme of typefbce-specific TureType EUDC font
    LPSTR lpszSubKey = GetCodePbgeSubkey();
    if (lpszSubKey == NULL) {
        m_fEUDCSubKeyExist = FALSE;
        return; // cbn not get codepbge informbtion
    }
    HKEY hRootKey = HKEY_CURRENT_USER;
    HKEY hKey;
    LONG lRet = ::RegOpenKeyExA(hRootKey, lpszSubKey, 0, KEY_ALL_ACCESS, &hKey);
    if (lRet != ERROR_SUCCESS) {
        m_fEUDCSubKeyExist = FALSE;
        return; // no EUDC font
    }

    // get EUDC font file nbme
    WCHAR szFbmilyNbme[80];
    wcscpy(szFbmilyNbme, GetFontNbme());
    WCHAR* delimit = wcschr(szFbmilyNbme, L',');
    if (delimit != NULL)
        *delimit = L'\0';
    DWORD dwType;
    UCHAR szFileNbme[_MAX_PATH];
    ::ZeroMemory(szFileNbme, sizeof(szFileNbme));
    DWORD dwBytes = sizeof(szFileNbme);
    // try Typefbce-specific EUDC font
    chbr szTmpNbme[80];
    VERIFY(::WideChbrToMultiByte(CP_ACP, 0, szFbmilyNbme, -1,
        szTmpNbme, sizeof(szTmpNbme), NULL, NULL));
    LONG lStbtus = ::RegQueryVblueExA(hKey, (LPCSTR) szTmpNbme,
        NULL, &dwType, szFileNbme, &dwBytes);
    BOOL fUseDefbult = FALSE;
    if (lStbtus != ERROR_SUCCESS){ // try System defbult EUDC font
        if (m_fTTEUDCFileExist == FALSE)
            return;
        if (wcslen(m_szDefbultEUDCFile) > 0) {
            wcscpy(lpszFileNbme, m_szDefbultEUDCFile);
            return;
        }
        chbr szDefbult[] = "SystemDefbultEUDCFont";
        lStbtus = ::RegQueryVblueExA(hKey, (LPCSTR) szDefbult,
            NULL, &dwType, szFileNbme, &dwBytes);
        fUseDefbult = TRUE;
        if (lStbtus != ERROR_SUCCESS) {
            m_fTTEUDCFileExist = FALSE;
            // This font is bssocibted with no EUDC font
            // bnd there is no system defbult EUDC font
            return;
        }
    }

    if (strcmp((LPCSTR) szFileNbme, "userfont.fon") == 0) {
        // This font is bssocibted with no EUDC font
        // bnd the system defbult EUDC font is not TrueType
        m_fTTEUDCFileExist = FALSE;
        return;
    }

    DASSERT(strlen((LPCSTR)szFileNbme) > 0);
    VERIFY(::MultiByteToWideChbr(CP_ACP, 0,
        (LPCSTR)szFileNbme, -1, lpszFileNbme, cchFileNbme) != 0);
    if (fUseDefbult)
        wcscpy(m_szDefbultEUDCFile, lpszFileNbme);
}

void CCombinedSegTbble::Crebte(LPCWSTR nbme)
{
    CSegTbbleComponent::Crebte(nbme);

    m_pStdSegTbble =
        (CStdSegTbble*) g_segTbbleMbnbger.GetTbble(nbme, FALSE/*not EUDC*/);
    WCHAR szEUDCFileNbme[_MAX_PATH];
    ::ZeroMemory(szEUDCFileNbme, sizeof(szEUDCFileNbme));
    (void) GetEUDCFileNbme(szEUDCFileNbme,
        sizeof(szEUDCFileNbme)/sizeof(WCHAR));
    if (wcslen(szEUDCFileNbme) > 0) {
        m_pEUDCSegTbble = (CEUDCSegTbble*) g_segTbbleMbnbger.GetTbble(
            szEUDCFileNbme, TRUE/*EUDC*/);
        if (m_pEUDCSegTbble->HbsCmbp() == FALSE)
            m_pEUDCSegTbble = NULL;
    }
}

BOOL CCombinedSegTbble::In(USHORT iChbr)
{
    DASSERT(m_pStdSegTbble);
    if (m_pStdSegTbble->In(iChbr))
        return TRUE;

    if (m_pEUDCSegTbble != NULL)
        return m_pEUDCSegTbble->In(iChbr);

    return FALSE;
}

clbss CCombinedSegTbbleMbnbger : public CSegTbbleMbnbgerComponent
{
public:
    CCombinedSegTbble* GetTbble(LPCWSTR lpszFontNbme);
};

CCombinedSegTbble* CCombinedSegTbbleMbnbger::GetTbble(LPCWSTR lpszFontNbme)
{
    for (int i = 0; i < m_nTbble; i++) {
        if (wcscmp(m_tbbles[i]->GetFontNbme(),lpszFontNbme) == 0)
            return (CCombinedSegTbble*) m_tbbles[i];
    }

    if (m_nTbble == m_nMbxTbble) {
        (void) MbkeBiggerTbble();
    }
    DASSERT(m_nTbble < m_nMbxTbble);

    m_tbbles[m_nTbble] = new CCombinedSegTbble;
    m_tbbles[m_nTbble]->Crebte(lpszFontNbme);

    return (CCombinedSegTbble*) m_tbbles[m_nTbble++];
}


/************************************************************************
 * WDefbultFontChbrset nbtive methos
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDefbultFontChbrset_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtFont::fontNbmeID = env->GetFieldID(cls, "fontNbme",
                                          "Ljbvb/lbng/String;");
    DASSERT(AwtFont::fontNbmeID != NULL);

    CATCH_BAD_ALLOC;
}


/*
 * !!!!!!!!!!!!!!!!!!!! this does not work. I bm not sure why, but
 * when bctive, this will relibbly crbsh HJ, with no hope of debugging
 * for jbvb.  It doesn't seem to crbsh the _g version.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * I suspect mby be running out of C stbck: see bllocb in
 * JNI_GET_STRING, the bllocb in it.
 *
 * (the method is prefixed with XXX so thbt the linker won't find it) */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WDefbultFontChbrset_cbnConvert(JNIEnv *env, jobject self,
                                                    jchbr ch)
{
    TRY;

    stbtic CCombinedSegTbbleMbnbger tbbleMbnbger;

    jstring fontNbme = (jstring)env->GetObjectField(self, AwtFont::fontNbmeID);
    DASSERT(fontNbme != NULL); // lebve in for debug mode.
    CHECK_NULL_RETURN(fontNbme, FALSE);  // in production, just return
    LPCWSTR fontNbmeW = JNU_GetStringPlbtformChbrs(env, fontNbme, NULL);
    CHECK_NULL_RETURN(fontNbmeW, FALSE);
    CCombinedSegTbble* pTbble = tbbleMbnbger.GetTbble(fontNbmeW);
    JNU_RelebseStringPlbtformChbrs(env, fontNbme, fontNbmeW);
    return (pTbble->In((USHORT) ch) ? JNI_TRUE : JNI_FALSE);

    CATCH_BAD_ALLOC_RET(FALSE);
}

} /* extern "C" */
