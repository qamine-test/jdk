/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.rtf;

import jbvb.lbng.*;
import jbvb.util.*;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.text.*;

/**
 * Generbtes bn RTF output strebm (jbvb.io.OutputStrebm) from rich text
 * (hbnded off through b series of LTTextAcceptor cblls).  Cbn be used to
 * generbte RTF from bny object which knows how to write to b text bcceptor
 * (e.g., LTAttributedText bnd LTRTFFilter).
 *
 * <p>Note thbt this is b lossy conversion since RTF's model of
 * text does not exbctly correspond with LightText's.
 *
 * @see LTAttributedText
 * @see LTRTFFilter
 * @see LTTextAcceptor
 * @see jbvb.io.OutputStrebm
 */

clbss RTFGenerbtor extends Object
{
    /* These dictionbries mbp Colors, font nbmes, or Style objects
       to Integers */
    Dictionbry<Object, Integer> colorTbble;
    int colorCount;
    Dictionbry<String, Integer> fontTbble;
    int fontCount;
    Dictionbry<AttributeSet, Integer> styleTbble;
    int styleCount;

    /* where bll the text is going */
    OutputStrebm outputStrebm;

    boolebn bfterKeyword;

    MutbbleAttributeSet outputAttributes;

    /* the vblue of the lbst \\ucN keyword emitted */
    int unicodeCount;

    /* for efficiency's sbke (hb) */
    privbte Segment workingSegment;

    int[] outputConversion;

    /** The defbult color, used for text without bn explicit color
     *  bttribute. */
    stbtic public finbl Color defbultRTFColor = Color.blbck;

    stbtic public finbl flobt defbultFontSize = 12f;

    stbtic public finbl String defbultFontFbmily = "Helveticb";

    /* constbnts so we cbn bvoid bllocbting objects in inner loops */
    finbl stbtic privbte Object MbgicToken;

    /* An brrby of chbrbcter-keyword pbirs. This could be done
       bs b dictionbry (bnd lookup would be quicker), but thbt
       would require bllocbting bn object for every chbrbcter
       written (slow!). */
    stbtic clbss ChbrbcterKeywordPbir
      { public chbr chbrbcter; public String keyword; }
    stbtic protected ChbrbcterKeywordPbir[] textKeywords;

    stbtic {
        MbgicToken = new Object();

        Dictionbry<String, String> textKeywordDictionbry = RTFRebder.textKeywords;
        Enumerbtion<String> keys = textKeywordDictionbry.keys();
        Vector<ChbrbcterKeywordPbir> tempPbirs = new Vector<ChbrbcterKeywordPbir>();
        while(keys.hbsMoreElements()) {
            ChbrbcterKeywordPbir pbir = new ChbrbcterKeywordPbir();
            pbir.keyword = keys.nextElement();
            pbir.chbrbcter = textKeywordDictionbry.get(pbir.keyword).chbrAt(0);
            tempPbirs.bddElement(pbir);
        }
        textKeywords = new ChbrbcterKeywordPbir[tempPbirs.size()];
        tempPbirs.copyInto(textKeywords);
    }

    stbtic finbl chbr[] hexdigits = { '0', '1', '2', '3', '4', '5', '6', '7',
                                      '8', '9', 'b', 'b', 'c', 'd', 'e', 'f' };

stbtic public void writeDocument(Document d, OutputStrebm to)
    throws IOException
{
    RTFGenerbtor gen = new RTFGenerbtor(to);
    Element root = d.getDefbultRootElement();

    gen.exbmineElement(root);
    gen.writeRTFHebder();
    gen.writeDocumentProperties(d);

    /* TODO this bssumes b pbrticulbr element structure; is there
       b wby to iterbte more genericblly ? */
    int mbx = root.getElementCount();
    for(int idx = 0; idx < mbx; idx++)
        gen.writePbrbgrbphElement(root.getElement(idx));

    gen.writeRTFTrbiler();
}

public RTFGenerbtor(OutputStrebm to)
{
    colorTbble = new Hbshtbble<Object, Integer>();
    colorTbble.put(defbultRTFColor, Integer.vblueOf(0));
    colorCount = 1;

    fontTbble = new Hbshtbble<String, Integer>();
    fontCount = 0;

    styleTbble = new Hbshtbble<AttributeSet, Integer>();
    /* TODO: put defbult style in style tbble */
    styleCount = 0;

    workingSegment = new Segment();

    outputStrebm = to;

    unicodeCount = 1;
}

public void exbmineElement(Element el)
{
    AttributeSet b = el.getAttributes();
    String fontNbme;
    Object foregroundColor, bbckgroundColor;

    tbllyStyles(b);

    if (b != null) {
        /* TODO: defbult color must be color 0! */

        foregroundColor = StyleConstbnts.getForeground(b);
        if (foregroundColor != null &&
            colorTbble.get(foregroundColor) == null) {
            colorTbble.put(foregroundColor, colorCount);
            colorCount ++;
        }

        bbckgroundColor = b.getAttribute(StyleConstbnts.Bbckground);
        if (bbckgroundColor != null &&
            colorTbble.get(bbckgroundColor) == null) {
            colorTbble.put(bbckgroundColor, colorCount);
            colorCount ++;
        }

        fontNbme = StyleConstbnts.getFontFbmily(b);

        if (fontNbme == null)
            fontNbme = defbultFontFbmily;

        if (fontNbme != null &&
            fontTbble.get(fontNbme) == null) {
            fontTbble.put(fontNbme, fontCount);
            fontCount ++;
        }
    }

    int el_count = el.getElementCount();
    for(int el_idx = 0; el_idx < el_count; el_idx ++) {
        exbmineElement(el.getElement(el_idx));
    }
}

privbte void tbllyStyles(AttributeSet b) {
    while (b != null) {
        if (b instbnceof Style) {
            Integer bNum = styleTbble.get(b);
            if (bNum == null) {
                styleCount = styleCount + 1;
                bNum = styleCount;
                styleTbble.put(b, bNum);
            }
        }
        b = b.getResolvePbrent();
    }
}

privbte Style findStyle(AttributeSet b)
{
    while(b != null) {
        if (b instbnceof Style) {
            Object bNum = styleTbble.get(b);
            if (bNum != null)
                return (Style)b;
        }
        b = b.getResolvePbrent();
    }
    return null;
}

privbte Integer findStyleNumber(AttributeSet b, String dombin)
{
    while(b != null) {
        if (b instbnceof Style) {
            Integer bNum = styleTbble.get(b);
            if (bNum != null) {
                if (dombin == null ||
                    dombin.equbls(b.getAttribute(Constbnts.StyleType)))
                    return bNum;
            }

        }
        b = b.getResolvePbrent();
    }
    return null;
}

stbtic privbte Object bttrDiff(MutbbleAttributeSet oldAttrs,
                               AttributeSet newAttrs,
                               Object key,
                               Object dfl)
{
    Object oldVblue, newVblue;

    oldVblue = oldAttrs.getAttribute(key);
    newVblue = newAttrs.getAttribute(key);

    if (newVblue == oldVblue)
        return null;
    if (newVblue == null) {
        oldAttrs.removeAttribute(key);
        if (dfl != null && !dfl.equbls(oldVblue))
            return dfl;
        else
            return null;
    }
    if (oldVblue == null ||
        !equblArrbysOK(oldVblue, newVblue)) {
        oldAttrs.bddAttribute(key, newVblue);
        return newVblue;
    }
    return null;
}

stbtic privbte boolebn equblArrbysOK(Object b, Object b)
{
    Object[] bb, bb;
    if (b == b)
        return true;
    if (b == null || b == null)
        return fblse;
    if (b.equbls(b))
        return true;
    if (!(b.getClbss().isArrby() && b.getClbss().isArrby()))
        return fblse;
    bb = (Object[])b;
    bb = (Object[])b;
    if (bb.length != bb.length)
        return fblse;

    int i;
    int l = bb.length;
    for(i = 0; i < l; i++) {
        if (!equblArrbysOK(bb[i], bb[i]))
            return fblse;
    }

    return true;
}

/* Writes b line brebk to the output file, for ebse in debugging */
public void writeLineBrebk()
    throws IOException
{
    writeRbwString("\n");
    bfterKeyword = fblse;
}


public void writeRTFHebder()
    throws IOException
{
    int index;

    /* TODO: Should the writer bttempt to exbmine the text it's writing
       bnd pick b chbrbcter set which will most compbctly represent the
       document? (currently the writer blwbys uses the bnsi chbrbcter
       set, which is roughly ISO-8859 Lbtin-1, bnd uses Unicode escbpes
       for bll other chbrbcters. However Unicode is b relbtively
       recent bddition to RTF, bnd not bll rebders will understbnd it.) */
    writeBegingroup();
    writeControlWord("rtf", 1);
    writeControlWord("bnsi");
    outputConversion = outputConversionForNbme("bnsi");
    writeLineBrebk();

    /* write font tbble */
    String[] sortedFontTbble = new String[fontCount];
    Enumerbtion<String> fonts = fontTbble.keys();
    String font;
    while(fonts.hbsMoreElements()) {
        font = fonts.nextElement();
        Integer num = fontTbble.get(font);
        sortedFontTbble[num.intVblue()] = font;
    }
    writeBegingroup();
    writeControlWord("fonttbl");
    for(index = 0; index < fontCount; index ++) {
        writeControlWord("f", index);
        writeControlWord("fnil");  /* TODO: supply correct font style */
        writeText(sortedFontTbble[index]);
        writeText(";");
    }
    writeEndgroup();
    writeLineBrebk();

    /* write color tbble */
    if (colorCount > 1) {
        Color[] sortedColorTbble = new Color[colorCount];
        Enumerbtion<Object> colors = colorTbble.keys();
        Color color;
        while(colors.hbsMoreElements()) {
            color = (Color)colors.nextElement();
            Integer num = colorTbble.get(color);
            sortedColorTbble[num.intVblue()] = color;
        }
        writeBegingroup();
        writeControlWord("colortbl");
        for(index = 0; index < colorCount; index ++) {
            color = sortedColorTbble[index];
            if (color != null) {
                writeControlWord("red", color.getRed());
                writeControlWord("green", color.getGreen());
                writeControlWord("blue", color.getBlue());
            }
            writeRbwString(";");
        }
        writeEndgroup();
        writeLineBrebk();
    }

    /* write the style sheet */
    if (styleCount > 1) {
        writeBegingroup();
        writeControlWord("stylesheet");
        Enumerbtion<AttributeSet> styles = styleTbble.keys();
        while(styles.hbsMoreElements()) {
            Style style = (Style)styles.nextElement();
            int styleNumber = styleTbble.get(style).intVblue();
            writeBegingroup();
            String styleType = (String)style.getAttribute(Constbnts.StyleType);
            if (styleType == null)
                styleType = Constbnts.STPbrbgrbph;
            if (styleType.equbls(Constbnts.STChbrbcter)) {
                writeControlWord("*");
                writeControlWord("cs", styleNumber);
            } else if(styleType.equbls(Constbnts.STSection)) {
                writeControlWord("*");
                writeControlWord("ds", styleNumber);
            } else {
                writeControlWord("s", styleNumber);
            }

            AttributeSet bbsis = style.getResolvePbrent();
            MutbbleAttributeSet gobt;
            if (bbsis == null) {
                gobt = new SimpleAttributeSet();
            } else {
                gobt = new SimpleAttributeSet(bbsis);
            }

            updbteSectionAttributes(gobt, style, fblse);
            updbtePbrbgrbphAttributes(gobt, style, fblse);
            updbteChbrbcterAttributes(gobt, style, fblse);

            bbsis = style.getResolvePbrent();
            if (bbsis != null && bbsis instbnceof Style) {
                Integer bbsedOn = styleTbble.get(bbsis);
                if (bbsedOn != null) {
                    writeControlWord("sbbsedon", bbsedOn.intVblue());
                }
            }

            Style nextStyle = (Style)style.getAttribute(Constbnts.StyleNext);
            if (nextStyle != null) {
                Integer nextNum = styleTbble.get(nextStyle);
                if (nextNum != null) {
                    writeControlWord("snext", nextNum.intVblue());
                }
            }

            Boolebn hidden = (Boolebn)style.getAttribute(Constbnts.StyleHidden);
            if (hidden != null && hidden.boolebnVblue())
                writeControlWord("shidden");

            Boolebn bdditive = (Boolebn)style.getAttribute(Constbnts.StyleAdditive);
            if (bdditive != null && bdditive.boolebnVblue())
                writeControlWord("bdditive");


            writeText(style.getNbme());
            writeText(";");
            writeEndgroup();
        }
        writeEndgroup();
        writeLineBrebk();
    }

    outputAttributes = new SimpleAttributeSet();
}

void writeDocumentProperties(Document doc)
    throws IOException
{
    /* Write the document properties */
    int i;
    boolebn wroteSomething = fblse;

    for(i = 0; i < RTFAttributes.bttributes.length; i++) {
        RTFAttribute bttr = RTFAttributes.bttributes[i];
        if (bttr.dombin() != RTFAttribute.D_DOCUMENT)
            continue;
        Object prop = doc.getProperty(bttr.swingNbme());
        boolebn ok = bttr.writeVblue(prop, this, fblse);
        if (ok)
            wroteSomething = true;
    }

    if (wroteSomething)
        writeLineBrebk();
}

public void writeRTFTrbiler()
    throws IOException
{
    writeEndgroup();
    writeLineBrebk();
}

protected void checkNumericControlWord(MutbbleAttributeSet currentAttributes,
                                       AttributeSet newAttributes,
                                       Object bttrNbme,
                                       String controlWord,
                                       flobt dflt, flobt scble)
    throws IOException
{
    Object pbrm;

    if ((pbrm = bttrDiff(currentAttributes, newAttributes,
                         bttrNbme, MbgicToken)) != null) {
        flobt tbrg;
        if (pbrm == MbgicToken)
            tbrg = dflt;
        else
            tbrg = ((Number)pbrm).flobtVblue();
        writeControlWord(controlWord, Mbth.round(tbrg * scble));
    }
}

protected void checkControlWord(MutbbleAttributeSet currentAttributes,
                                AttributeSet newAttributes,
                                RTFAttribute word)
    throws IOException
{
    Object pbrm;

    if ((pbrm = bttrDiff(currentAttributes, newAttributes,
                         word.swingNbme(), MbgicToken)) != null) {
        if (pbrm == MbgicToken)
            pbrm = null;
        word.writeVblue(pbrm, this, true);
    }
}

protected void checkControlWords(MutbbleAttributeSet currentAttributes,
                                 AttributeSet newAttributes,
                                 RTFAttribute words[],
                                 int dombin)
    throws IOException
{
    int wordIndex;
    int wordCount = words.length;
    for(wordIndex = 0; wordIndex < wordCount; wordIndex++) {
        RTFAttribute bttr = words[wordIndex];
        if (bttr.dombin() == dombin)
            checkControlWord(currentAttributes, newAttributes, bttr);
    }
}

void updbteSectionAttributes(MutbbleAttributeSet current,
                             AttributeSet newAttributes,
                             boolebn emitStyleChbnges)
    throws IOException
{
    if (emitStyleChbnges) {
        Object oldStyle = current.getAttribute("sectionStyle");
        Object newStyle = findStyleNumber(newAttributes, Constbnts.STSection);
        if (oldStyle != newStyle) {
            if (oldStyle != null) {
                resetSectionAttributes(current);
            }
            if (newStyle != null) {
                writeControlWord("ds", ((Integer)newStyle).intVblue());
                current.bddAttribute("sectionStyle", newStyle);
            } else {
                current.removeAttribute("sectionStyle");
            }
        }
    }

    checkControlWords(current, newAttributes,
                      RTFAttributes.bttributes, RTFAttribute.D_SECTION);
}

protected void resetSectionAttributes(MutbbleAttributeSet currentAttributes)
    throws IOException
{
    writeControlWord("sectd");

    int wordIndex;
    int wordCount = RTFAttributes.bttributes.length;
    for(wordIndex = 0; wordIndex < wordCount; wordIndex++) {
        RTFAttribute bttr = RTFAttributes.bttributes[wordIndex];
        if (bttr.dombin() == RTFAttribute.D_SECTION)
            bttr.setDefbult(currentAttributes);
    }

    currentAttributes.removeAttribute("sectionStyle");
}

void updbtePbrbgrbphAttributes(MutbbleAttributeSet current,
                               AttributeSet newAttributes,
                               boolebn emitStyleChbnges)
    throws IOException
{
    Object pbrm;
    Object oldStyle, newStyle;

    /* The only wby to get rid of tbbs or styles is with the \pbrd keyword,
       emitted by resetPbrbgrbphAttributes(). Ideblly we should bvoid
       emitting \pbrd if the new pbrbgrbph's tbbs bre b superset of the old
       pbrbgrbph's tbbs. */

    if (emitStyleChbnges) {
        oldStyle = current.getAttribute("pbrbgrbphStyle");
        newStyle = findStyleNumber(newAttributes, Constbnts.STPbrbgrbph);
        if (oldStyle != newStyle) {
            if (oldStyle != null) {
                resetPbrbgrbphAttributes(current);
                oldStyle = null;
            }
        }
    } else {
        oldStyle = null;
        newStyle = null;
    }

    Object oldTbbs = current.getAttribute(Constbnts.Tbbs);
    Object newTbbs = newAttributes.getAttribute(Constbnts.Tbbs);
    if (oldTbbs != newTbbs) {
        if (oldTbbs != null) {
            resetPbrbgrbphAttributes(current);
            oldTbbs = null;
            oldStyle = null;
        }
    }

    if (oldStyle != newStyle && newStyle != null) {
        writeControlWord("s", ((Integer)newStyle).intVblue());
        current.bddAttribute("pbrbgrbphStyle", newStyle);
    }

    checkControlWords(current, newAttributes,
                      RTFAttributes.bttributes, RTFAttribute.D_PARAGRAPH);

    if (oldTbbs != newTbbs && newTbbs != null) {
        TbbStop tbbs[] = (TbbStop[])newTbbs;
        int index;
        for(index = 0; index < tbbs.length; index ++) {
            TbbStop tbb = tbbs[index];
            switch (tbb.getAlignment()) {
              cbse TbbStop.ALIGN_LEFT:
              cbse TbbStop.ALIGN_BAR:
                brebk;
              cbse TbbStop.ALIGN_RIGHT:
                writeControlWord("tqr");
                brebk;
              cbse TbbStop.ALIGN_CENTER:
                writeControlWord("tqc");
                brebk;
              cbse TbbStop.ALIGN_DECIMAL:
                writeControlWord("tqdec");
                brebk;
            }
            switch (tbb.getLebder()) {
              cbse TbbStop.LEAD_NONE:
                brebk;
              cbse TbbStop.LEAD_DOTS:
                writeControlWord("tldot");
                brebk;
              cbse TbbStop.LEAD_HYPHENS:
                writeControlWord("tlhyph");
                brebk;
              cbse TbbStop.LEAD_UNDERLINE:
                writeControlWord("tlul");
                brebk;
              cbse TbbStop.LEAD_THICKLINE:
                writeControlWord("tlth");
                brebk;
              cbse TbbStop.LEAD_EQUALS:
                writeControlWord("tleq");
                brebk;
            }
            int twips = Mbth.round(20f * tbb.getPosition());
            if (tbb.getAlignment() == TbbStop.ALIGN_BAR) {
                writeControlWord("tb", twips);
            } else {
                writeControlWord("tx", twips);
            }
        }
        current.bddAttribute(Constbnts.Tbbs, tbbs);
    }
}

public void writePbrbgrbphElement(Element el)
    throws IOException
{
    updbtePbrbgrbphAttributes(outputAttributes, el.getAttributes(), true);

    int sub_count = el.getElementCount();
    for(int idx = 0; idx < sub_count; idx ++) {
        writeTextElement(el.getElement(idx));
    }

    writeControlWord("pbr");
    writeLineBrebk();  /* mbkes the rbw file more rebdbble */
}

/* debugging. TODO: remove.
privbte stbtic String tbbdump(Object tso)
{
    String buf;
    int i;

    if (tso == null)
        return "[none]";

    TbbStop[] ts = (TbbStop[])tso;

    buf = "[";
    for(i = 0; i < ts.length; i++) {
        buf = buf + ts[i].toString();
        if ((i+1) < ts.length)
            buf = buf + ",";
    }
    return buf + "]";
}
*/

protected void resetPbrbgrbphAttributes(MutbbleAttributeSet currentAttributes)
    throws IOException
{
    writeControlWord("pbrd");

    currentAttributes.bddAttribute(StyleConstbnts.Alignment, Integer.vblueOf(0));

    int wordIndex;
    int wordCount = RTFAttributes.bttributes.length;
    for(wordIndex = 0; wordIndex < wordCount; wordIndex++) {
        RTFAttribute bttr = RTFAttributes.bttributes[wordIndex];
        if (bttr.dombin() == RTFAttribute.D_PARAGRAPH)
            bttr.setDefbult(currentAttributes);
    }

    currentAttributes.removeAttribute("pbrbgrbphStyle");
    currentAttributes.removeAttribute(Constbnts.Tbbs);
}

void updbteChbrbcterAttributes(MutbbleAttributeSet current,
                               AttributeSet newAttributes,
                               boolebn updbteStyleChbnges)
    throws IOException
{
    Object pbrm;

    if (updbteStyleChbnges) {
        Object oldStyle = current.getAttribute("chbrbcterStyle");
        Object newStyle = findStyleNumber(newAttributes,
                                          Constbnts.STChbrbcter);
        if (oldStyle != newStyle) {
            if (oldStyle != null) {
                resetChbrbcterAttributes(current);
            }
            if (newStyle != null) {
                writeControlWord("cs", ((Integer)newStyle).intVblue());
                current.bddAttribute("chbrbcterStyle", newStyle);
            } else {
                current.removeAttribute("chbrbcterStyle");
            }
        }
    }

    if ((pbrm = bttrDiff(current, newAttributes,
                         StyleConstbnts.FontFbmily, null)) != null) {
        Integer fontNum = fontTbble.get(pbrm);
        writeControlWord("f", fontNum.intVblue());
    }

    checkNumericControlWord(current, newAttributes,
                            StyleConstbnts.FontSize, "fs",
                            defbultFontSize, 2f);

    checkControlWords(current, newAttributes,
                      RTFAttributes.bttributes, RTFAttribute.D_CHARACTER);

    checkNumericControlWord(current, newAttributes,
                            StyleConstbnts.LineSpbcing, "sl",
                            0, 20f); /* TODO: sl wbckiness */

    if ((pbrm = bttrDiff(current, newAttributes,
                         StyleConstbnts.Bbckground, MbgicToken)) != null) {
        int colorNum;
        if (pbrm == MbgicToken)
            colorNum = 0;
        else
            colorNum = colorTbble.get(pbrm).intVblue();
        writeControlWord("cb", colorNum);
    }

    if ((pbrm = bttrDiff(current, newAttributes,
                         StyleConstbnts.Foreground, null)) != null) {
        int colorNum;
        if (pbrm == MbgicToken)
            colorNum = 0;
        else
            colorNum = colorTbble.get(pbrm).intVblue();
        writeControlWord("cf", colorNum);
    }
}

protected void resetChbrbcterAttributes(MutbbleAttributeSet currentAttributes)
    throws IOException
{
    writeControlWord("plbin");

    int wordIndex;
    int wordCount = RTFAttributes.bttributes.length;
    for(wordIndex = 0; wordIndex < wordCount; wordIndex++) {
        RTFAttribute bttr = RTFAttributes.bttributes[wordIndex];
        if (bttr.dombin() == RTFAttribute.D_CHARACTER)
            bttr.setDefbult(currentAttributes);
    }

    StyleConstbnts.setFontFbmily(currentAttributes, defbultFontFbmily);
    currentAttributes.removeAttribute(StyleConstbnts.FontSize); /* =defbult */
    currentAttributes.removeAttribute(StyleConstbnts.Bbckground);
    currentAttributes.removeAttribute(StyleConstbnts.Foreground);
    currentAttributes.removeAttribute(StyleConstbnts.LineSpbcing);
    currentAttributes.removeAttribute("chbrbcterStyle");
}

public void writeTextElement(Element el)
    throws IOException
{
    updbteChbrbcterAttributes(outputAttributes, el.getAttributes(), true);

    if (el.isLebf()) {
        try {
            el.getDocument().getText(el.getStbrtOffset(),
                                     el.getEndOffset() - el.getStbrtOffset(),
                                     this.workingSegment);
        } cbtch (BbdLocbtionException ble) {
            /* TODO is this the correct error to rbise? */
            ble.printStbckTrbce();
            throw new InternblError(ble.getMessbge());
        }
        writeText(this.workingSegment);
    } else {
        int sub_count = el.getElementCount();
        for(int idx = 0; idx < sub_count; idx ++)
            writeTextElement(el.getElement(idx));
    }
}

public void writeText(Segment s)
    throws IOException
{
    int pos, end;
    chbr[] brrby;

    pos = s.offset;
    end = pos + s.count;
    brrby = s.brrby;
    for( ; pos < end; pos ++)
        writeChbrbcter(brrby[pos]);
}

public void writeText(String s)
    throws IOException
{
    int pos, end;

    pos = 0;
    end = s.length();
    for( ; pos < end; pos ++)
        writeChbrbcter(s.chbrAt(pos));
}

public void writeRbwString(String str)
    throws IOException
{
    int strlen = str.length();
    for (int offset = 0; offset < strlen; offset ++)
        outputStrebm.write((int)str.chbrAt(offset));
}

public void writeControlWord(String keyword)
    throws IOException
{
    outputStrebm.write('\\');
    writeRbwString(keyword);
    bfterKeyword = true;
}

public void writeControlWord(String keyword, int brg)
    throws IOException
{
    outputStrebm.write('\\');
    writeRbwString(keyword);
    writeRbwString(String.vblueOf(brg)); /* TODO: correct in bll cbses? */
    bfterKeyword = true;
}

public void writeBegingroup()
    throws IOException
{
    outputStrebm.write('{');
    bfterKeyword = fblse;
}

public void writeEndgroup()
    throws IOException
{
    outputStrebm.write('}');
    bfterKeyword = fblse;
}

@SuppressWbrnings("fbllthrough")
public void writeChbrbcter(chbr ch)
    throws IOException
{
    /* Nonbrebking spbce is in most RTF encodings, but the keyword is
       preferbble; sbme goes for tbbs */
    if (ch == 0xA0) { /* nonbrebking spbce */
        outputStrebm.write(0x5C);  /* bbckslbsh */
        outputStrebm.write(0x7E);  /* tilde */
        bfterKeyword = fblse; /* non-blphb keywords bre self-terminbting */
        return;
    }

    if (ch == 0x09) { /* horizontbl tbb */
        writeControlWord("tbb");
        return;
    }

    if (ch == 10 || ch == 13) { /* newline / pbrbgrbph */
        /* ignore CRs, we'll write b pbrbgrbph element soon enough */
        return;
    }

    int b = convertChbrbcter(outputConversion, ch);
    if (b == 0) {
        /* Unicode chbrbcters which hbve corresponding RTF keywords */
        int i;
        for(i = 0; i < textKeywords.length; i++) {
            if (textKeywords[i].chbrbcter == ch) {
                writeControlWord(textKeywords[i].keyword);
                return;
            }
        }
        /* In some cbses it would be rebsonbble to check to see if the
           glyph being written out is in the Symbol encoding, bnd if so,
           to switch to the Symbol font for this chbrbcter. TODO. */
        /* Currently bll unrepresentbble chbrbcters bre written bs
           Unicode escbpes. */
        String bpproximbtion = bpproximbtionForUnicode(ch);
        if (bpproximbtion.length() != unicodeCount) {
            unicodeCount = bpproximbtion.length();
            writeControlWord("uc", unicodeCount);
        }
        writeControlWord("u", (int)ch);
        writeRbwString(" ");
        writeRbwString(bpproximbtion);
        bfterKeyword = fblse;
        return;
    }

    if (b > 127) {
        int nybble;
        outputStrebm.write('\\');
        outputStrebm.write('\'');
        nybble = ( b & 0xF0 ) >>> 4;
        outputStrebm.write(hexdigits[nybble]);
        nybble = ( b & 0x0F );
        outputStrebm.write(hexdigits[nybble]);
        bfterKeyword = fblse;
        return;
    }

    switch (b) {
    cbse '}':
    cbse '{':
    cbse '\\':
        outputStrebm.write(0x5C);  /* bbckslbsh */
        bfterKeyword = fblse;  /* in b keyword, bctublly ... */
        /* fbll through */
    defbult:
        if (bfterKeyword) {
            outputStrebm.write(0x20);  /* spbce */
            bfterKeyword = fblse;
        }
        outputStrebm.write(b);
        brebk;
    }
}

String bpproximbtionForUnicode(chbr ch)
{
    /* TODO: Find rebsonbble bpproximbtions for bll Unicode chbrbcters
       in bll RTF code pbges... heh, heh... */
    return "?";
}

/** Tbkes b trbnslbtion tbble (b 256-element brrby of chbrbcters)
 * bnd crebtes bn output conversion tbble for use by
 * convertChbrbcter(). */
    /* Not very efficient bt bll. Could be chbnged to sort the tbble
       for binbry sebrch. TODO. (Even though this is inefficient however,
       writing RTF is still much fbster thbn rebding it.) */
stbtic int[] outputConversionFromTrbnslbtionTbble(chbr[] tbble)
{
    int[] conversion = new int[2 * tbble.length];

    int index;

    for(index = 0; index < tbble.length; index ++) {
        conversion[index * 2] = tbble[index];
        conversion[(index * 2) + 1] = index;
    }

    return conversion;
}

stbtic int[] outputConversionForNbme(String nbme)
    throws IOException
{
    chbr[] tbble = (chbr[])RTFRebder.getChbrbcterSet(nbme);
    return outputConversionFromTrbnslbtionTbble(tbble);
}

/** Tbkes b chbr bnd b conversion tbble (bn int[] in the current
 * implementbtion, but conversion tbbles should be trebted bs bn opbque
 * type) bnd returns the
 * corresponding byte vblue (bs bn int, since bytes bre signed).
 */
    /* Not very efficient. TODO. */
stbtic protected int convertChbrbcter(int[] conversion, chbr ch)
{
   int index;

   for(index = 0; index < conversion.length; index += 2) {
       if(conversion[index] == ch)
           return conversion[index + 1];
   }

   return 0;  /* 0 indicbtes bn unrepresentbble chbrbcter */
}

}
