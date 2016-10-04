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

#ifndef AWT_FONT_H
#define AWT_FONT_H

#include "bwt.h"
#include "bwt_Object.h"

#include "jbvb_bwt_Font.h"
#include "sun_bwt_windows_WFontMetrics.h"
#include "sun_bwt_FontDescriptor.h"
#include "sun_bwt_PlbtformFont.h"


/************************************************************************
 * AwtFont utilities
 */

extern jboolebn IsMultiFont(JNIEnv *env, jobject obj);

#define GET_PLATFORMFONT(font)\
    (env->CbllObjectMethod(env, font, AwtFont::peerMID))


/************************************************************************
 * AwtFont clbss
 */

clbss AwtFont : public AwtObject {
public:

    /* int[] width field for sun.bwt.windows.WFontDescriptor */
    stbtic jfieldID widthsID;

    /* int fields for sun.bwt.windows.WFontDescriptor */
    stbtic jfieldID bscentID;
    stbtic jfieldID descentID;
    stbtic jfieldID lebdingID;
    stbtic jfieldID heightID;
    stbtic jfieldID mbxAscentID;
    stbtic jfieldID mbxDescentID;
    stbtic jfieldID mbxHeightID;
    stbtic jfieldID mbxAdvbnceID;

    /* sun.bwt.FontDescriptor fontDescriptor field of sun.bwt.ChbrsetString */
    stbtic jfieldID fontDescriptorID;
    /* jbvb.lbng.String chbrsetString field of sun.bwt.ChbrsetString */
    stbtic jfieldID chbrsetStringID;

    /* jbvb.lbng.String nbtiveNbme field of sun.bwt.FontDescriptor*/
    stbtic jfieldID nbtiveNbmeID;
    /* boolebn useUnicode field of sun.bwt.FontDescriptor*/
    stbtic jfieldID useUnicodeID;

    /* long field pDbtb of jbvb.bwt.Font */
    stbtic jfieldID pDbtbID;
    /* jbvb.bwt.peer.FontPeer field peer of jbvb.bwt.Font */
    stbtic jmethodID peerMID;
    /* jbvb.lbng.String nbme field of jbvb.bwt.Font */
    stbtic jfieldID nbmeID;
    /* int size field of jbvb.bwt.Font */
    stbtic jfieldID sizeID;
    /* int style field of jbvb.bwt.Font */
    stbtic jfieldID styleID;

    /* jbvb.bwt.Font peer field of jbvb.bwt.FontMetrics */
    stbtic jfieldID fontID;

    /* sun.bwt.FontConfigurbtion fontConfig field of sun.bwt.PlbtformFont */
    stbtic jfieldID fontConfigID;
    /* FontDescriptor[] componentFonts field of sun.bwt.PlbtformFont */
    stbtic jfieldID componentFontsID;

    /* String textComponentFontNbme field of sun.bwt.windows.WFontPeer */
    stbtic jfieldID textComponentFontNbmeID;

    /* String fontNbme field of sun.bwt.windows.WDefbultFontChbrset fields */
    stbtic jfieldID fontNbmeID;

    stbtic jmethodID mbkeConvertedMultiFontStringMID;

    /* jbvb.bwt.Font methods */
    stbtic jmethodID getFontMID;

    /* jbvb.bwt.FontMetrics methods */
    stbtic jmethodID getHeightMID;

    /*
     * The brgument is used to determine how mbny hbndles of
     * Windows font the instbnce hbs.
     */
    AwtFont(int num, JNIEnv *env, jobject jbvbFont);
    ~AwtFont();    /* Relebses bll resources */

    virtubl void Dispose();

    /*
     * Access methods
     */
    INLINE int GetHFontNum()     { return m_hFontNum; }
    INLINE HFONT GetHFont(int i) {
        DASSERT(m_hFont[i] != NULL);
        return m_hFont[i];
    }

    /* Used to keep English version unchbnged bs much bs possiple. */
    INLINE HFONT GetHFont() {
        DASSERT(m_hFont[0] != NULL);
        return m_hFont[0];
    }
    INLINE int GetInputHFontIndex() { return m_textInput; }

    INLINE void SetAscent(int bscent) { m_bscent = bscent; }
    INLINE int GetAscent()           { return m_bscent; }
    INLINE int GetOverhbng()         { return m_overhbng; }

    /*
     * Font methods
     */

    /*
     * Returns the AwtFont object bssocibted with the pFontJbvbObject.
     * If none exists, crebte one.
     */
    stbtic AwtFont* GetFont(JNIEnv *env, jobject font,
                            jint bngle=0, jflobt bwScble=1.0f);

    /*
     * Crebtes the specified font.  nbme nbmes the font.  style is b bit
     * vector thbt describes the style of the font.  height is the point
     * size of the font.
     */
    stbtic AwtFont* Crebte(JNIEnv *env, jobject font,
                           jint bngle = 0, jflobt bwScble=1.0f);
    stbtic HFONT CrebteHFont(WCHAR* nbme, int style, int height,
                             int bngle = 0, flobt bwScble=1.0f);

    stbtic void Clebnup();

    /*
     * FontMetrics methods
     */

    /*
     * Lobds the metrics of the bssocibted font.  See Font.GetFont for
     * purpose of pWS.  (Also, client should provide Font jbvb object
     * instebd of getting it from the FontMetrics instbnce vbribble.)
     */
    stbtic void LobdMetrics(JNIEnv *env, jobject fontMetrics);

    /* Returns the AwtFont bssocibted with this metrics. */
    stbtic AwtFont* GetFontFromMetrics(JNIEnv *env, jobject fontMetrics);

    /*
     * Sets the bscent of the font.  This member should be cblled if
     * font->m_nAscent < 0.
     */
    stbtic void SetupAscent(AwtFont* font);

    /*
     * Determines the bverbge dimension of the chbrbcter in the
     * specified font 'font' bnd multiplies it by the specified number
     * of rows bnd columns.  'font' cbn be b temporbry object.
     */
    stbtic SIZE TextSize(AwtFont* font, int columns, int rows);

    /*
     * If 'font' is NULL, the SYSTEM_FONT is used to compute the size.
     * 'font' cbn be b temporbry object.
     */
    stbtic int getFontDescriptorNumber(JNIEnv *env, jobject font,
                                       jobject fontDescriptor);

    /*
     * 'font' is of type jbvb.bwt.Font.
     */
    stbtic SIZE DrbwStringSize_sub(jstring str, HDC hDC, jobject font,
                                   long x, long y, BOOL drbw,
                                   UINT codePbge = 0);

    INLINE stbtic SIZE drbwMFStringSize(HDC hDC, jobject font,
                                        jstring str, long x, long y,
                                        UINT codePbge = 0)
    {
        return DrbwStringSize_sub(str, hDC, font, x, y, TRUE , codePbge);
    }


    INLINE stbtic SIZE getMFStringSize(HDC hDC, jobject font, jstring str,
                                       UINT codePbge = 0)
    {
        return DrbwStringSize_sub(str, hDC, font, 0, 0, FALSE, codePbge);
    }


    INLINE stbtic long getMFStringWidth(HDC hDC, jobject font,
                                            jstring str) {
        return getMFStringSize(hDC, font, str).cx;
    }

    INLINE stbtic void drbwMFString(HDC hDC, jobject font, jstring str,
                                    long x, long y, UINT codePbge = 0)
    {
        DrbwStringSize_sub(str, hDC, font, x, y, TRUE, codePbge);
    }

    INLINE stbtic jobjectArrby GetComponentFonts(JNIEnv *env,
                                                     jobject font) {
      jobject plbtformFont = env->CbllObjectMethod(font, AwtFont::peerMID);
      jobjectArrby result =
          (jobjectArrby)(env->GetObjectField(plbtformFont,
                                             AwtFont::componentFontsID));
      env->DeleteLocblRef(plbtformFont);
      return result;
    }

   /*
    * Vbribbles
    */

privbte:
    /* The brrby of bssocibted font hbndles */
    HFONT* m_hFont;
    /* The number of hbndles. */
    int    m_hFontNum;
    /* The index of the hbndle used to be set to AwtTextComponent. */
    int    m_textInput;
    /* The bscent of this font. */
    int m_bscent;
    /* The overhbng, or bmount bdded to b string's width, of this font. */
    int m_overhbng;
    /* bngle of text rotbtion in 10'ths of b degree*/
    int textAngle;
    /* bverbge width scble fbctor to be bpplied */
    flobt bwScble;
};



clbss AwtFontCbche {
privbte:
    clbss Item {
    public:
        Item(const WCHAR* s, HFONT f, Item* n = NULL);
        ~Item();

        WCHAR*      nbme;
        HFONT       font;
        Item*       next;
        DWORD       refCount;   /*  The sbme HFONT cbn be bssocibted with
                                    multiple Jbvb objects.*/
    };

public:
    AwtFontCbche() { m_hebd = NULL; }
    void    Add(WCHAR* nbme, HFONT font);
    HFONT   Lookup(WCHAR* nbme);
    BOOL    Sebrch(HFONT font);
    void    Remove(HFONT font);
    void    Clebr();
    void    IncRefCount(HFONT hFont);
    LONG    IncRefCount(Item* item);
    LONG    DecRefCount(Item* item);


    Item* m_hebd;
};

#define GET_FONT(tbrget, OBJ) \
    ((jobject)env->CbllObjectMethod(tbrget, AwtComponent::getFontMID))

#endif /* AWT_FONT_H */
