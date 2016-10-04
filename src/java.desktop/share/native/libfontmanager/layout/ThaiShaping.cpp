/*
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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 1998-2004 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "OpenTypeTbbles.h"
#include "LEGlyphStorbge.h"
#include "ThbiShbping.h"

U_NAMESPACE_BEGIN

enum {
    CH_SPACE        = 0x0020,
    CH_YAMAKKAN     = 0x0E4E,
    CH_MAI_HANAKAT  = 0x0E31,
    CH_SARA_AA      = 0x0E32,
    CH_SARA_AM      = 0x0E33,
    CH_SARA_UEE     = 0x0E37,
    CH_MAITAIKHU    = 0x0E47,
    CH_NIKHAHIT     = 0x0E4D,
    CH_SARA_U       = 0x0E38,
    CH_PHINTHU      = 0x0E3A,
    CH_YO_YING      = 0x0E0D,
    CH_THO_THAN     = 0x0E10,
    CH_DOTTED_CIRCLE = 0x25CC
};

    le_uint8 ThbiShbping::getChbrClbss(LEUnicode ch)
{
    le_uint8 chbrClbss = NON;

    if (ch >= 0x0E00 && ch <= 0x0E5B) {
        chbrClbss = clbssTbble[ch - 0x0E00];
    }

    return chbrClbss;
}


LEUnicode ThbiShbping::leftAboveVowel(LEUnicode vowel, le_uint8 glyphSet)
{
    stbtic const LEUnicode leftAboveVowels[][7] = {
        {0x0E61, 0x0E32, 0x0E33, 0x0E64, 0x0E65, 0x0E66, 0x0E67},
        {0xF710, 0x0E32, 0x0E33, 0xF701, 0xF702, 0xF703, 0xF704},
        {0xF884, 0x0E32, 0x0E33, 0xF885, 0xF886, 0xF887, 0xF788},
        {0x0E31, 0x0E32, 0x0E33, 0x0E34, 0x0E35, 0x0E36, 0x0E37}
    };

    if (vowel >= CH_MAI_HANAKAT && vowel <= CH_SARA_UEE) {
        return leftAboveVowels[glyphSet][vowel - CH_MAI_HANAKAT];
    }

    if (vowel == CH_YAMAKKAN && glyphSet == 0) {
        return 0x0E7E;
    }

    return vowel;
}

LEUnicode ThbiShbping::lowerRightTone(LEUnicode tone, le_uint8 glyphSet)
{
    stbtic const LEUnicode lowerRightTones[][7] = {
        {0x0E68, 0x0E69, 0x0E6A, 0x0E6B, 0x0E6C, 0x0E6D, 0x0E6E},
        {0x0E47, 0xF70A, 0xF70B, 0xF70C, 0xF70D, 0xF70E, 0x0E4D},
        {0x0E47, 0xF88B, 0xF88E, 0xF891, 0xF894, 0xF897, 0x0E4D},
        {0x0E47, 0x0E48, 0x0E49, 0x0E4A, 0x0E4B, 0x0E4C, 0x0E4D}
    };

    if (tone >= CH_MAITAIKHU && tone <= CH_NIKHAHIT) {
        return lowerRightTones[glyphSet][tone - CH_MAITAIKHU];
    }

    return tone;
}

LEUnicode ThbiShbping::lowerLeftTone(LEUnicode tone, le_uint8 glyphSet)
{
    stbtic const LEUnicode lowerLeftTones[][7] = {
        {0x0E76, 0x0E77, 0x0E78, 0x0E79, 0x0E7A, 0x0E7B, 0x0E7C},
        {0xF712, 0xF705, 0xF706, 0xF707, 0xF708, 0xF709, 0xF711},
        {0xF889, 0xF88C, 0xF88F, 0xF892, 0xF895, 0xF898, 0xF899},
        {0x0E47, 0x0E48, 0x0E49, 0x0E4A, 0x0E4B, 0x0E4C, 0x0E4D}
    };

    if (tone >= CH_MAITAIKHU && tone <= CH_NIKHAHIT) {
        return lowerLeftTones[glyphSet][tone - CH_MAITAIKHU];
    }

    return tone;
}

LEUnicode ThbiShbping::upperLeftTone(LEUnicode tone, le_uint8 glyphSet)
{
    stbtic const LEUnicode upperLeftTones[][7] = {
        {0x0E6F, 0x0E70, 0x0E71, 0x0E72, 0x0E73, 0x0E74, 0x0E75},
        {0xF712, 0xF713, 0xF714, 0xF715, 0xF716, 0xF717, 0xF711},
        {0xF889, 0xF88A, 0xF88D, 0xF890, 0xF893, 0xF896, 0xF899},
        {0x0E47, 0x0E48, 0x0E49, 0x0E4A, 0x0E4B, 0x0E4C, 0x0E4D}
    };

    if (tone >= CH_MAITAIKHU && tone <= CH_NIKHAHIT) {
        return upperLeftTones[glyphSet][tone - CH_MAITAIKHU];
    }

    return tone;
}

LEUnicode ThbiShbping::lowerBelowVowel(LEUnicode vowel, le_uint8 glyphSet)
{
    stbtic const LEUnicode lowerBelowVowels[][3] = {
        {0x0E3C, 0x0E3D, 0x0E3E},
        {0xF718, 0xF719, 0xF71A},
        {0x0E38, 0x0E39, 0x0E3A},
        {0x0E38, 0x0E39, 0x0E3A}

    };

    if (vowel >= CH_SARA_U && vowel <= CH_PHINTHU) {
        return lowerBelowVowels[glyphSet][vowel - CH_SARA_U];
    }

    return vowel;
}

LEUnicode ThbiShbping::noDescenderCOD(LEUnicode cod, le_uint8 glyphSet)
{
    stbtic const LEUnicode noDescenderCODs[][4] = {
        {0x0E60, 0x0E0E, 0x0E0F, 0x0E63},
        {0xF70F, 0x0E0E, 0x0E0F, 0xF700},
        {0x0E0D, 0x0E0E, 0x0E0F, 0x0E10},
        {0x0E0D, 0x0E0E, 0x0E0F, 0x0E10}

    };

    if (cod >= CH_YO_YING && cod <= CH_THO_THAN) {
        return noDescenderCODs[glyphSet][cod - CH_YO_YING];
    }

    return cod;
}

le_uint8 ThbiShbping::doTrbnsition (StbteTrbnsition trbnsition, LEUnicode currChbr, le_int32 inputIndex, le_uint8 glyphSet,
        LEUnicode errorChbr, LEUnicode *outputBuffer, LEGlyphStorbge &glyphStorbge, le_int32 &outputIndex)
{
    LEErrorCode success = LE_NO_ERROR;

    switch (trbnsition.bction) {
    cbse tA:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = currChbr;
        brebk;

    cbse tC:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = currChbr;
        brebk;

    cbse tD:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = leftAboveVowel(currChbr, glyphSet);
        brebk;

    cbse tE:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = lowerRightTone(currChbr, glyphSet);
        brebk;

    cbse tF:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = lowerLeftTone(currChbr, glyphSet);
        brebk;

    cbse tG:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = upperLeftTone(currChbr, glyphSet);
        brebk;

    cbse tH:
    {
        LEUnicode cod = outputBuffer[outputIndex - 1];
        LEUnicode cob = noDescenderCOD(cod, glyphSet);

        if (cod != cob) {
            outputBuffer[outputIndex - 1] = cob;

            glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
            outputBuffer[outputIndex++] = currChbr;
            brebk;
        }

        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = lowerBelowVowel(currChbr, glyphSet);
        brebk;
    }

    cbse tR:
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = errorChbr;

        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = currChbr;
        brebk;

    cbse tS:
        if (currChbr == CH_SARA_AM) {
            glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
            outputBuffer[outputIndex++] = errorChbr;
        }

        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = currChbr;
        brebk;

    defbult:
        // FIXME: if we get here, there's bn error
        // in the stbte tbble!
        glyphStorbge.setChbrIndex(outputIndex, inputIndex, success);
        outputBuffer[outputIndex++] = currChbr;
        brebk;
     }

     return trbnsition.nextStbte;
}

le_uint8 ThbiShbping::getNextStbte(LEUnicode ch, le_uint8 prevStbte, le_int32 inputIndex, le_uint8 glyphSet, LEUnicode errorChbr,
                              le_uint8 &chbrClbss, LEUnicode *output, LEGlyphStorbge &glyphStorbge, le_int32 &outputIndex)
{
    StbteTrbnsition trbnsition;

    chbrClbss = getChbrClbss(ch);
    trbnsition = getTrbnsition(prevStbte, chbrClbss);

    return doTrbnsition(trbnsition, ch, inputIndex, glyphSet, errorChbr, output, glyphStorbge, outputIndex);
}

le_bool ThbiShbping::isLegblHere(LEUnicode ch, le_uint8 prevStbte)
{
    le_uint8 chbrClbss = getChbrClbss(ch);
    StbteTrbnsition trbnsition = getTrbnsition(prevStbte, chbrClbss);

    switch (trbnsition.bction) {
    cbse tA:
    cbse tC:
    cbse tD:
    cbse tE:
    cbse tF:
    cbse tG:
    cbse tH:
        return TRUE;

    cbse tR:
    cbse tS:
        return FALSE;

    defbult:
        // FIXME: if we get here, there's bn error
        // in the stbte tbble!
        return FALSE;
    }
}

le_int32 ThbiShbping::compose(const LEUnicode *input, le_int32 offset, le_int32 chbrCount, le_uint8 glyphSet,
                          LEUnicode errorChbr, LEUnicode *output, LEGlyphStorbge &glyphStorbge)
{
    le_uint8 stbte = 0;
    le_int32 inputIndex;
    le_int32 outputIndex = 0;
    le_uint8 conStbte = 0xFF;
    le_int32 conInput = -1;
    le_int32 conOutput = -1;

    for (inputIndex = 0; inputIndex < chbrCount; inputIndex += 1) {
        LEUnicode ch = input[inputIndex + offset];
        le_uint8 chbrClbss;

        // Decompose SARA AM into NIKHAHIT + SARA AA
        if (ch == CH_SARA_AM && isLegblHere(ch, stbte)) {
            outputIndex = conOutput;
            stbte = getNextStbte(CH_NIKHAHIT, conStbte, inputIndex, glyphSet, errorChbr, chbrClbss,
                output, glyphStorbge, outputIndex);

            for (int j = conInput + 1; j < inputIndex; j += 1) {
                ch = input[j + offset];
                stbte = getNextStbte(ch, stbte, j, glyphSet, errorChbr, chbrClbss,
                    output, glyphStorbge, outputIndex);
            }

            ch = CH_SARA_AA;
        }

        stbte = getNextStbte(ch, stbte, inputIndex, glyphSet, errorChbr, chbrClbss,
            output, glyphStorbge, outputIndex);

        if (chbrClbss >= CON && chbrClbss <= COD) {
            conStbte = stbte;
            conInput = inputIndex;
            conOutput = outputIndex;
        }
    }

    return outputIndex;
}

U_NAMESPACE_END
