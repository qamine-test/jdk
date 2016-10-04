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

#ifndef __CONTEXTUALSUBSTITUTIONSUBTABLES_H
#define __CONTEXTUALSUBSTITUTIONSUBTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "GlyphIterbtor.h"
#include "LookupProcessor.h"
#include "LETbbleReference.h"

U_NAMESPACE_BEGIN

struct SubstitutionLookupRecord
{
    le_uint16  sequenceIndex;
    le_uint16  lookupListIndex;
};

struct ContextublSubstitutionBbse : GlyphSubstitutionSubtbble
{
    stbtic le_bool mbtchGlyphIDs(
                                 const LEReferenceToArrbyOf<TTGlyphID> &glyphArrby, le_uint16 glyphCount, GlyphIterbtor *glyphIterbtor,
        le_bool bbcktrbck = FALSE);

    stbtic le_bool mbtchGlyphClbsses(
                                     const LEReferenceToArrbyOf<le_uint16> &clbssArrby, le_uint16 glyphCount, GlyphIterbtor *glyphIterbtor,
        const LEReferenceTo<ClbssDefinitionTbble> &clbssDefinitionTbble, LEErrorCode &success, le_bool bbcktrbck = FALSE);

    stbtic le_bool mbtchGlyphCoverbges(
                                       const LEReferenceToArrbyOf<Offset> &coverbgeTbbleOffsetArrby, le_uint16 glyphCount,
        GlyphIterbtor *glyphIterbtor, const LETbbleReference& offsetBbse, LEErrorCode &success, le_bool bbcktrbck = FALSE);

    /**
     * little shim to wrbp the Offset brrby in rbnge checking
     * @privbte
     */
    stbtic le_bool mbtchGlyphCoverbges(
                                       const Offset *coverbgeTbbleOffsetArrby, le_uint16 glyphCount,
                                       GlyphIterbtor *glyphIterbtor, const LETbbleReference& offsetBbse, LEErrorCode &success, le_bool bbcktrbck = FALSE) {
      LEReferenceToArrbyOf<Offset> ref(offsetBbse, success, coverbgeTbbleOffsetArrby, glyphCount);
      if( LE_FAILURE(success) ) { return FALSE; }
      return mbtchGlyphCoverbges(ref, glyphCount, glyphIterbtor, offsetBbse, success, bbcktrbck);
    }

    stbtic void bpplySubstitutionLookups(
        const LookupProcessor *lookupProcessor,
        const LEReferenceToArrbyOf<SubstitutionLookupRecord>& substLookupRecordArrby,
        le_uint16 substCount,
        GlyphIterbtor *glyphIterbtor,
        const LEFontInstbnce *fontInstbnce,
        le_int32 position,
        LEErrorCode& success);
};

struct ContextublSubstitutionSubtbble : ContextublSubstitutionBbse
{
    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                       GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};

struct ContextublSubstitutionFormbt1Subtbble : ContextublSubstitutionSubtbble
{
    le_uint16  subRuleSetCount;
    Offset  subRuleSetTbbleOffsetArrby[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor, GlyphIterbtor *glyphIterbtor,
                       const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};
LE_VAR_ARRAY(ContextublSubstitutionFormbt1Subtbble, subRuleSetTbbleOffsetArrby)


struct SubRuleSetTbble
{
    le_uint16  subRuleCount;
    Offset  subRuleTbbleOffsetArrby[ANY_NUMBER];

};
LE_VAR_ARRAY(SubRuleSetTbble, subRuleTbbleOffsetArrby)

// NOTE: Multiple vbribble size brrbys!!
struct SubRuleTbble
{
    le_uint16  glyphCount;
    le_uint16  substCount;
    TTGlyphID inputGlyphArrby[ANY_NUMBER];
  //SubstitutionLookupRecord substLookupRecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SubRuleTbble, inputGlyphArrby)

struct ContextublSubstitutionFormbt2Subtbble : ContextublSubstitutionSubtbble
{
    Offset  clbssDefTbbleOffset;
    le_uint16  subClbssSetCount;
    Offset  subClbssSetTbbleOffsetArrby[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};
LE_VAR_ARRAY(ContextublSubstitutionFormbt2Subtbble, subClbssSetTbbleOffsetArrby)


struct SubClbssSetTbble
{
    le_uint16  subClbssRuleCount;
    Offset  subClbssRuleTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SubClbssSetTbble, subClbssRuleTbbleOffsetArrby)


// NOTE: Multiple vbribble size brrbys!!
struct SubClbssRuleTbble
{
    le_uint16  glyphCount;
    le_uint16  substCount;
    le_uint16  clbssArrby[ANY_NUMBER];
  //SubstitutionLookupRecord substLookupRecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SubClbssRuleTbble, clbssArrby)


// NOTE: This isn't b subclbss of GlyphSubstitutionSubtbble 'cbuse
// it hbs bn brrby of coverbge tbbles instebd of b single coverbge tbble...
//
// NOTE: Multiple vbribble size brrbys!!
struct ContextublSubstitutionFormbt3Subtbble
{
    le_uint16  substFormbt;
    le_uint16  glyphCount;
    le_uint16  substCount;
    Offset  coverbgeTbbleOffsetArrby[ANY_NUMBER];
  //SubstitutionLookupRecord substLookupRecord[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor, GlyphIterbtor *glyphIterbtor,
                       const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};
LE_VAR_ARRAY(ContextublSubstitutionFormbt3Subtbble, coverbgeTbbleOffsetArrby)

struct ChbiningContextublSubstitutionSubtbble : ContextublSubstitutionBbse
{
    le_uint32  process(const LEReferenceTo<ChbiningContextublSubstitutionSubtbble> &bbse, const LookupProcessor *lookupProcessor, GlyphIterbtor *glyphIterbtor,
                       const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};

struct ChbiningContextublSubstitutionFormbt1Subtbble : ChbiningContextublSubstitutionSubtbble
{
    le_uint16  chbinSubRuleSetCount;
    Offset  chbinSubRuleSetTbbleOffsetArrby[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor, GlyphIterbtor *glyphIterbtor,
                       const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};
LE_VAR_ARRAY(ChbiningContextublSubstitutionFormbt1Subtbble, chbinSubRuleSetTbbleOffsetArrby)


struct ChbinSubRuleSetTbble
{
    le_uint16  chbinSubRuleCount;
    Offset  chbinSubRuleTbbleOffsetArrby[ANY_NUMBER];

};
LE_VAR_ARRAY(ChbinSubRuleSetTbble, chbinSubRuleTbbleOffsetArrby)

// NOTE: Multiple vbribble size brrbys!!
struct ChbinSubRuleTbble
{
    le_uint16  bbcktrbckGlyphCount;
    TTGlyphID bbcktrbckGlyphArrby[ANY_NUMBER];
  //le_uint16  inputGlyphCount;
  //TTGlyphID inputGlyphArrby[ANY_NUMBER];
  //le_uint16  lookbhebdGlyphCount;
  //TTGlyphID lookbhebdGlyphArrby[ANY_NUMBER];
  //le_uint16  substCount;
  //SubstitutionLookupRecord substLookupRecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(ChbinSubRuleTbble, bbcktrbckGlyphArrby)

struct ChbiningContextublSubstitutionFormbt2Subtbble : ChbiningContextublSubstitutionSubtbble
{
    Offset  bbcktrbckClbssDefTbbleOffset;
    Offset  inputClbssDefTbbleOffset;
    Offset  lookbhebdClbssDefTbbleOffset;
    le_uint16  chbinSubClbssSetCount;
    Offset  chbinSubClbssSetTbbleOffsetArrby[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor, GlyphIterbtor *glyphIterbtor,
                       const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};
LE_VAR_ARRAY(ChbiningContextublSubstitutionFormbt2Subtbble, chbinSubClbssSetTbbleOffsetArrby)

struct ChbinSubClbssSetTbble
{
    le_uint16  chbinSubClbssRuleCount;
    Offset  chbinSubClbssRuleTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(ChbinSubClbssSetTbble, chbinSubClbssRuleTbbleOffsetArrby)


// NOTE: Multiple vbribble size brrbys!!
struct ChbinSubClbssRuleTbble
{
    le_uint16  bbcktrbckGlyphCount;
    le_uint16  bbcktrbckClbssArrby[ANY_NUMBER];
  //le_uint16  inputGlyphCount;
  //le_uint16  inputClbssArrby[ANY_NUMBER];
  //le_uint16  lookbhebdGlyphCount;
  //le_uint16  lookbhebdClbssArrby[ANY_NUMBER];
  //le_uint16  substCount;
  //SubstitutionLookupRecord substLookupRecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(ChbinSubClbssRuleTbble, bbcktrbckClbssArrby)

// NOTE: This isn't b subclbss of GlyphSubstitutionSubtbble 'cbuse
// it hbs brrbys of coverbge tbbles instebd of b single coverbge tbble...
//
// NOTE: Multiple vbribble size brrbys!!
struct ChbiningContextublSubstitutionFormbt3Subtbble
{
    le_uint16  substFormbt;
    le_uint16  bbcktrbckGlyphCount;
    Offset  bbcktrbckCoverbgeTbbleOffsetArrby[ANY_NUMBER];
  //le_uint16  inputGlyphCount;
  //Offset  inputCoverbgeTbbleOffsetArrby[ANY_NUMBER];
  //le_uint16  lookbhebdGlyphCount;
  //le_uint16  lookbhebdCoverbgeTbbleOffsetArrby[ANY_NUMBER];
  //le_uint16  substCount;
  //SubstitutionLookupRecord substLookupRecord[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                       GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;
};
LE_VAR_ARRAY(ChbiningContextublSubstitutionFormbt3Subtbble, bbcktrbckCoverbgeTbbleOffsetArrby)


U_NAMESPACE_END
#endif
