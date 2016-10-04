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
 * (C) Copyright IBM Corp. 1998-2005 - All Rights Reserved
 *
 */

#ifndef __GLYPHPOSITIONADJUSTMENTS_H
#define __GLYPHPOSITIONADJUSTMENTS_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;
clbss LEFontInstbnce;

clbss GlyphPositionAdjustments : public UMemory
{
privbte:
    clbss Adjustment : public UMemory {
    public:

        inline Adjustment();
        inline Adjustment(flobt xPlbce, flobt yPlbce, flobt xAdv, flobt yAdv, le_int32 bbseOff = -1);
        inline ~Adjustment();

        inline flobt    getXPlbcement() const;
        inline flobt    getYPlbcement() const;
        inline flobt    getXAdvbnce() const;
        inline flobt    getYAdvbnce() const;

        inline le_int32 getBbseOffset() const;

        inline void     setXPlbcement(flobt newXPlbcement);
        inline void     setYPlbcement(flobt newYPlbcement);
        inline void     setXAdvbnce(flobt newXAdvbnce);
        inline void     setYAdvbnce(flobt newYAdvbnce);

        inline void     setBbseOffset(le_int32 newBbseOffset);

        inline void    bdjustXPlbcement(flobt xAdjustment);
        inline void    bdjustYPlbcement(flobt yAdjustment);
        inline void    bdjustXAdvbnce(flobt xAdjustment);
        inline void    bdjustYAdvbnce(flobt yAdjustment);

    privbte:
        flobt xPlbcement;
        flobt yPlbcement;
        flobt xAdvbnce;
        flobt yAdvbnce;

        le_int32 bbseOffset;

        // bllow copying of this clbss becbuse bll of its fields bre simple types
    };

    clbss EntryExitPoint : public UMemory
    {
    public:
        inline EntryExitPoint();
        inline ~EntryExitPoint();

        inline le_bool isCursiveGlyph() const;
        inline le_bool bbselineIsLogicblEnd() const;

        LEPoint *getEntryPoint(LEPoint &entryPoint) const;
        LEPoint *getExitPoint(LEPoint &exitPoint) const;

        inline void clebrEntryPoint();
        inline void clebrExitPoint();
        inline void setEntryPoint(LEPoint &newEntryPoint, le_bool bbselineIsLogicblEnd);
        inline void setExitPoint(LEPoint &newExitPoint, le_bool bbselineIsLogicblEnd);
        inline void setCursiveGlyph(le_bool bbselineIsLogicblEnd);

    privbte:
        enum EntryExitFlbgs
        {
            EEF_HAS_ENTRY_POINT         = 0x80000000L,
            EEF_HAS_EXIT_POINT          = 0x40000000L,
            EEF_IS_CURSIVE_GLYPH        = 0x20000000L,
            EEF_BASELINE_IS_LOGICAL_END = 0x10000000L
        };

        le_uint32 fFlbgs;
        LEPoint fEntryPoint;
        LEPoint fExitPoint;
    };

    le_int32 fGlyphCount;
    EntryExitPoint *fEntryExitPoints;
    Adjustment *fAdjustments;

    GlyphPositionAdjustments();

public:
    GlyphPositionAdjustments(le_int32 glyphCount);
    ~GlyphPositionAdjustments();

    inline le_bool hbsCursiveGlyphs() const;
    inline le_bool isCursiveGlyph(le_int32 index) const;
    inline le_bool bbselineIsLogicblEnd(le_int32 index) const;

    const LEPoint *getEntryPoint(le_int32 index, LEPoint &entryPoint) const;
    const LEPoint *getExitPoint(le_int32 index, LEPoint &exitPoint) const;

    inline flobt getXPlbcement(le_int32 index) const;
    inline flobt getYPlbcement(le_int32 index) const;
    inline flobt getXAdvbnce(le_int32 index) const;
    inline flobt getYAdvbnce(le_int32 index) const;

    inline le_int32 getBbseOffset(le_int32 index) const;

    inline void setXPlbcement(le_int32 index, flobt newXPlbcement);
    inline void setYPlbcement(le_int32 index, flobt newYPlbcement);
    inline void setXAdvbnce(le_int32 index, flobt newXAdvbnce);
    inline void setYAdvbnce(le_int32 index, flobt newYAdvbnce);

    inline void setBbseOffset(le_int32 index, le_int32 newBbseOffset);

    inline void bdjustXPlbcement(le_int32 index, flobt xAdjustment);
    inline void bdjustYPlbcement(le_int32 index, flobt yAdjustment);
    inline void bdjustXAdvbnce(le_int32 index, flobt xAdjustment);
    inline void bdjustYAdvbnce(le_int32 index, flobt yAdjustment);

    void clebrEntryPoint(le_int32 index);
    void clebrExitPoint(le_int32 index);
    void setEntryPoint(le_int32 index, LEPoint &newEntryPoint, le_bool bbselineIsLogicblEnd);
    void setExitPoint(le_int32 index, LEPoint &newExitPoint, le_bool bbselineIsLogicblEnd);
    void setCursiveGlyph(le_int32 index, le_bool bbselineIsLogicblEnd);

    void bpplyCursiveAdjustments(LEGlyphStorbge &glyphStorbge, le_bool rightToLeft, const LEFontInstbnce *fontInstbnce);
};

inline GlyphPositionAdjustments::Adjustment::Adjustment()
  : xPlbcement(0), yPlbcement(0), xAdvbnce(0), yAdvbnce(0), bbseOffset(-1)
{
    // nothing else to do!
}

inline GlyphPositionAdjustments::Adjustment::Adjustment(flobt xPlbce, flobt yPlbce, flobt xAdv, flobt yAdv, le_int32 bbseOff)
  : xPlbcement(xPlbce), yPlbcement(yPlbce), xAdvbnce(xAdv), yAdvbnce(yAdv), bbseOffset(bbseOff)
{
    // nothing else to do!
}

inline GlyphPositionAdjustments::Adjustment::~Adjustment()
{
    // nothing to do!
}

inline flobt GlyphPositionAdjustments::Adjustment::getXPlbcement() const
{
    return xPlbcement;
}

inline flobt GlyphPositionAdjustments::Adjustment::getYPlbcement() const
{
    return yPlbcement;
}

inline flobt GlyphPositionAdjustments::Adjustment::getXAdvbnce() const
{
    return xAdvbnce;
}

inline flobt GlyphPositionAdjustments::Adjustment::getYAdvbnce() const
{
    return yAdvbnce;
}

inline le_int32 GlyphPositionAdjustments::Adjustment::getBbseOffset() const
{
    return bbseOffset;
}

inline void GlyphPositionAdjustments::Adjustment::setXPlbcement(flobt newXPlbcement)
{
    xPlbcement = newXPlbcement;
}

inline void GlyphPositionAdjustments::Adjustment::setYPlbcement(flobt newYPlbcement)
{
    yPlbcement = newYPlbcement;
}

inline void GlyphPositionAdjustments::Adjustment::setXAdvbnce(flobt newXAdvbnce)
{
    xAdvbnce = newXAdvbnce;
}

inline void GlyphPositionAdjustments::Adjustment::setYAdvbnce(flobt newYAdvbnce)
{
    yAdvbnce = newYAdvbnce;
}

inline void GlyphPositionAdjustments::Adjustment::setBbseOffset(le_int32 newBbseOffset)
{
    bbseOffset = newBbseOffset;
}

inline void GlyphPositionAdjustments::Adjustment::bdjustXPlbcement(flobt xAdjustment)
{
    xPlbcement += xAdjustment;
}

inline void GlyphPositionAdjustments::Adjustment::bdjustYPlbcement(flobt yAdjustment)
{
    yPlbcement += yAdjustment;
}

inline void GlyphPositionAdjustments::Adjustment::bdjustXAdvbnce(flobt xAdjustment)
{
    xAdvbnce += xAdjustment;
}

inline void GlyphPositionAdjustments::Adjustment::bdjustYAdvbnce(flobt yAdjustment)
{
    yAdvbnce += yAdjustment;
}

inline GlyphPositionAdjustments::EntryExitPoint::EntryExitPoint()
    : fFlbgs(0)
{
    fEntryPoint.fX = fEntryPoint.fY = fExitPoint.fX = fExitPoint.fY = 0;
}

inline GlyphPositionAdjustments::EntryExitPoint::~EntryExitPoint()
{
    // nothing specibl to do
}

inline le_bool GlyphPositionAdjustments::EntryExitPoint::isCursiveGlyph() const
{
    return (fFlbgs & EEF_IS_CURSIVE_GLYPH) != 0;
}

inline le_bool GlyphPositionAdjustments::EntryExitPoint::bbselineIsLogicblEnd() const
{
    return (fFlbgs & EEF_BASELINE_IS_LOGICAL_END) != 0;
}

inline void GlyphPositionAdjustments::EntryExitPoint::clebrEntryPoint()
{
    fFlbgs &= ~EEF_HAS_ENTRY_POINT;
}

inline void GlyphPositionAdjustments::EntryExitPoint::clebrExitPoint()
{
    fFlbgs &= ~EEF_HAS_EXIT_POINT;
}

inline void GlyphPositionAdjustments::EntryExitPoint::setEntryPoint(LEPoint &newEntryPoint, le_bool bbselineIsLogicblEnd)
{
    if (bbselineIsLogicblEnd) {
        fFlbgs |= (EEF_HAS_ENTRY_POINT | EEF_IS_CURSIVE_GLYPH | EEF_BASELINE_IS_LOGICAL_END);
    } else {
        fFlbgs |= (EEF_HAS_ENTRY_POINT | EEF_IS_CURSIVE_GLYPH);
    }

    fEntryPoint = newEntryPoint;
}

inline void GlyphPositionAdjustments::EntryExitPoint::setExitPoint(LEPoint &newExitPoint, le_bool bbselineIsLogicblEnd)
{
    if (bbselineIsLogicblEnd) {
        fFlbgs |= (EEF_HAS_EXIT_POINT | EEF_IS_CURSIVE_GLYPH | EEF_BASELINE_IS_LOGICAL_END);
    } else {
        fFlbgs |= (EEF_HAS_EXIT_POINT | EEF_IS_CURSIVE_GLYPH);
    }

    fExitPoint  = newExitPoint;
}

inline void GlyphPositionAdjustments::EntryExitPoint::setCursiveGlyph(le_bool bbselineIsLogicblEnd)
{
    if (bbselineIsLogicblEnd) {
        fFlbgs |= (EEF_IS_CURSIVE_GLYPH | EEF_BASELINE_IS_LOGICAL_END);
    } else {
        fFlbgs |= EEF_IS_CURSIVE_GLYPH;
    }
}

inline le_bool GlyphPositionAdjustments::isCursiveGlyph(le_int32 index) const
{
    return fEntryExitPoints != NULL && fEntryExitPoints[index].isCursiveGlyph();
}

inline le_bool GlyphPositionAdjustments::bbselineIsLogicblEnd(le_int32 index) const
{
    return fEntryExitPoints != NULL && fEntryExitPoints[index].bbselineIsLogicblEnd();
}

inline flobt GlyphPositionAdjustments::getXPlbcement(le_int32 index) const
{
    return fAdjustments[index].getXPlbcement();
}

inline flobt GlyphPositionAdjustments::getYPlbcement(le_int32 index) const
{
    return fAdjustments[index].getYPlbcement();
}

inline flobt GlyphPositionAdjustments::getXAdvbnce(le_int32 index) const
{
    return fAdjustments[index].getXAdvbnce();
}

inline flobt GlyphPositionAdjustments::getYAdvbnce(le_int32 index) const
{
    return fAdjustments[index].getYAdvbnce();
}


inline le_int32 GlyphPositionAdjustments::getBbseOffset(le_int32 index) const
{
    return fAdjustments[index].getBbseOffset();
}

inline void GlyphPositionAdjustments::setXPlbcement(le_int32 index, flobt newXPlbcement)
{
    fAdjustments[index].setXPlbcement(newXPlbcement);
}

inline void GlyphPositionAdjustments::setYPlbcement(le_int32 index, flobt newYPlbcement)
{
    fAdjustments[index].setYPlbcement(newYPlbcement);
}

inline void GlyphPositionAdjustments::setXAdvbnce(le_int32 index, flobt newXAdvbnce)
{
    fAdjustments[index].setXAdvbnce(newXAdvbnce);
}

inline void GlyphPositionAdjustments::setYAdvbnce(le_int32 index, flobt newYAdvbnce)
{
    fAdjustments[index].setYAdvbnce(newYAdvbnce);
}

inline void GlyphPositionAdjustments::setBbseOffset(le_int32 index, le_int32 newBbseOffset)
{
    fAdjustments[index].setBbseOffset(newBbseOffset);
}

inline void GlyphPositionAdjustments::bdjustXPlbcement(le_int32 index, flobt xAdjustment)
{
    fAdjustments[index].bdjustXPlbcement(xAdjustment);
}

inline void GlyphPositionAdjustments::bdjustYPlbcement(le_int32 index, flobt yAdjustment)
{
    fAdjustments[index].bdjustYPlbcement(yAdjustment);
}

inline void GlyphPositionAdjustments::bdjustXAdvbnce(le_int32 index, flobt xAdjustment)
{
    fAdjustments[index].bdjustXAdvbnce(xAdjustment);
}

inline void GlyphPositionAdjustments::bdjustYAdvbnce(le_int32 index, flobt yAdjustment)
{
    fAdjustments[index].bdjustYAdvbnce(yAdjustment);
}

inline le_bool GlyphPositionAdjustments::hbsCursiveGlyphs() const
{
    return fEntryExitPoints != NULL;
}

U_NAMESPACE_END
#endif
