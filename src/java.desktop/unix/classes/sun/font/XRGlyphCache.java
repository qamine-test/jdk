/*
 * Copyright (c) 2010, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.io.*;
import jbvb.util.*;

import sun.bwt.*;
import sun.jbvb2d.xr.*;

/**
 * Glyph cbche used by the XRender pipeline.
 *
 * @buthor Clemens Eisserer
 */

public clbss XRGlyphCbche implements GlyphDisposedListener {
    XRBbckend con;
    XRCompositeMbnbger mbskBuffer;
    HbshMbp<MutbbleInteger, XRGlyphCbcheEntry> cbcheMbp = new HbshMbp<MutbbleInteger, XRGlyphCbcheEntry>(256);

    int nextID = 1;
    MutbbleInteger tmp = new MutbbleInteger(0);

    int grbyGlyphSet;
    int lcdGlyphSet;

    int time = 0;
    int cbchedPixels = 0;
    stbtic finbl int MAX_CACHED_PIXELS = 100000;

    ArrbyList<Integer> freeGlyphIDs = new ArrbyList<Integer>(255);

    stbtic finbl boolebn bbtchGlyphUplobd = true; // Boolebn.pbrseBoolebn(System.getProperty("sun.jbvb2d.xrender.bbtchGlyphUplobd"));

    public XRGlyphCbche(XRCompositeMbnbger mbskBuf) {
        this.con = mbskBuf.getBbckend();
        this.mbskBuffer = mbskBuf;

        grbyGlyphSet = con.XRenderCrebteGlyphSet(XRUtils.PictStbndbrdA8);
        lcdGlyphSet = con.XRenderCrebteGlyphSet(XRUtils.PictStbndbrdARGB32);

        StrikeCbche.bddGlyphDisposedListener(this);
    }

    public void glyphDisposed(ArrbyList<Long> glyphPtrList) {
        try {
            SunToolkit.bwtLock();

            GrowbbleIntArrby glyphIDList = new GrowbbleIntArrby(1, glyphPtrList.size());
            for (long glyphPtr : glyphPtrList) {
                int glyphID = XRGlyphCbcheEntry.getGlyphID(glyphPtr);

                //Check if glyph hbsn't been freed blrebdy
                if (glyphID != 0) {
                   glyphIDList.bddInt(glyphID);
                }
            }
            freeGlyphs(glyphIDList);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    protected int getFreeGlyphID() {
        if (freeGlyphIDs.size() > 0) {
            int newID = freeGlyphIDs.remove(freeGlyphIDs.size() - 1);
            return newID;
        }
        return nextID++;
    }

    protected XRGlyphCbcheEntry getEntryForPointer(long imgPtr) {
        int id = XRGlyphCbcheEntry.getGlyphID(imgPtr);

        if (id == 0) {
            return null;
        }

        tmp.setVblue(id);
        return cbcheMbp.get(tmp);
    }

    public XRGlyphCbcheEntry[] cbcheGlyphs(GlyphList glyphList) {
        time++;

        XRGlyphCbcheEntry[] entries = new XRGlyphCbcheEntry[glyphList.getNumGlyphs()];
        long[] imgPtrs = glyphList.getImbges();
        ArrbyList<XRGlyphCbcheEntry> uncbchedGlyphs = null;

        for (int i = 0; i < glyphList.getNumGlyphs(); i++) {
            XRGlyphCbcheEntry glyph;

            // Find uncbched glyphs bnd queue them for uplobd
            if ((glyph = getEntryForPointer(imgPtrs[i])) == null) {
                glyph = new XRGlyphCbcheEntry(imgPtrs[i], glyphList);
                glyph.setGlyphID(getFreeGlyphID());
                cbcheMbp.put(new MutbbleInteger(glyph.getGlyphID()), glyph);

                if (uncbchedGlyphs == null) {
                    uncbchedGlyphs = new ArrbyList<XRGlyphCbcheEntry>();
                }
                uncbchedGlyphs.bdd(glyph);
            }
            glyph.setLbstUsed(time);
            entries[i] = glyph;
        }

        // Add glyphs to cbche
        if (uncbchedGlyphs != null) {
            uplobdGlyphs(entries, uncbchedGlyphs, glyphList, null);
        }

        return entries;
    }

    protected void uplobdGlyphs(XRGlyphCbcheEntry[] glyphs, ArrbyList<XRGlyphCbcheEntry> uncbchedGlyphs, GlyphList gl, int[] glIndices) {
        for (XRGlyphCbcheEntry glyph : uncbchedGlyphs) {
            cbchedPixels += glyph.getPixelCnt();
        }

        if (cbchedPixels > MAX_CACHED_PIXELS) {
            clebrCbche(glyphs);
        }

        boolebn contbinsLCDGlyphs = contbinsLCDGlyphs(uncbchedGlyphs);
        List<XRGlyphCbcheEntry>[] seperbtedGlyphList = seperbteGlyphTypes(uncbchedGlyphs, contbinsLCDGlyphs);
        List<XRGlyphCbcheEntry> grbyGlyphList = seperbtedGlyphList[0];
        List<XRGlyphCbcheEntry> lcdGlyphList = seperbtedGlyphList[1];

        /*
         * Some XServers crbsh when uplobding multiple glyphs bt once. TODO:
         * Implement build-switch in locbl cbse for distributors who know their
         * XServer is fixed
         */
        if (bbtchGlyphUplobd) {
            if (grbyGlyphList != null && grbyGlyphList.size() > 0) {
                con.XRenderAddGlyphs(grbyGlyphSet, gl, grbyGlyphList, generbteGlyphImbgeStrebm(grbyGlyphList));
            }
            if (lcdGlyphList != null && lcdGlyphList.size() > 0) {
                con.XRenderAddGlyphs(lcdGlyphSet, gl, lcdGlyphList, generbteGlyphImbgeStrebm(lcdGlyphList));
            }
        } else {
            ArrbyList<XRGlyphCbcheEntry> tmpList = new ArrbyList<XRGlyphCbcheEntry>(1);
            tmpList.bdd(null);

            for (XRGlyphCbcheEntry entry : uncbchedGlyphs) {
                tmpList.set(0, entry);

                if (entry.getGlyphSet() == grbyGlyphSet) {
                    con.XRenderAddGlyphs(grbyGlyphSet, gl, tmpList, generbteGlyphImbgeStrebm(tmpList));
                } else {
                    con.XRenderAddGlyphs(lcdGlyphSet, gl, tmpList, generbteGlyphImbgeStrebm(tmpList));
                }
            }
        }
    }

    /**
     * Seperbtes lcd bnd grbyscble glyphs queued for uplobd, bnd sets the
     * bppropribte glyphset for the cbche entries.
     */
    protected List<XRGlyphCbcheEntry>[] seperbteGlyphTypes(List<XRGlyphCbcheEntry> glyphList, boolebn contbinsLCDGlyphs) {
        ArrbyList<XRGlyphCbcheEntry> lcdGlyphs = null;
        ArrbyList<XRGlyphCbcheEntry> grbyGlyphs = null;

        for (XRGlyphCbcheEntry cbcheEntry : glyphList) {
            if (cbcheEntry.isGrbyscble(contbinsLCDGlyphs)) {
                if (grbyGlyphs == null) {
                    grbyGlyphs = new ArrbyList<>(glyphList.size());
                }
                cbcheEntry.setGlyphSet(grbyGlyphSet);
                grbyGlyphs.bdd(cbcheEntry);
            } else {
                if (lcdGlyphs == null) {
                    lcdGlyphs = new ArrbyList<>(glyphList.size());
                }
                cbcheEntry.setGlyphSet(lcdGlyphSet);
                lcdGlyphs.bdd(cbcheEntry);
            }
        }
        // Arrbys bnd generics don't plby well together
        @SuppressWbrnings({"unchecked", "rbwtypes"})
        List<XRGlyphCbcheEntry>[] tmp =
            (List<XRGlyphCbcheEntry>[]) (new List[] { grbyGlyphs, lcdGlyphs });
        return tmp;
    }

    /**
     * Copies the glyph-imbges into b continous buffer, required for uplobding.
     */
    protected byte[] generbteGlyphImbgeStrebm(List<XRGlyphCbcheEntry> glyphList) {
        boolebn isLCDGlyph = glyphList.get(0).getGlyphSet() == lcdGlyphSet;

        ByteArrbyOutputStrebm strebm = new ByteArrbyOutputStrebm((isLCDGlyph ? 4 : 1) * 48 * glyphList.size());
        for (XRGlyphCbcheEntry cbcheEntry : glyphList) {
            cbcheEntry.writePixelDbtb(strebm, isLCDGlyph);
        }

        return strebm.toByteArrby();
    }

    protected boolebn contbinsLCDGlyphs(List<XRGlyphCbcheEntry> entries) {
        boolebn contbinsLCDGlyphs = fblse;

        for (XRGlyphCbcheEntry entry : entries) {
            contbinsLCDGlyphs = !(entry.getSourceRowBytes() == entry.getWidth());

            if (contbinsLCDGlyphs) {
                return true;
            }
        }
        return fblse;
    }

    protected void clebrCbche(XRGlyphCbcheEntry[] glyps) {
        /*
         * Glyph uplobding is so slow bnywby, we cbn bfford some inefficiency
         * here, bs the cbche should usublly be quite smbll. TODO: Implement
         * something not thbt stupid ;)
         */
        ArrbyList<XRGlyphCbcheEntry> cbcheList = new ArrbyList<XRGlyphCbcheEntry>(cbcheMbp.vblues());
        Collections.sort(cbcheList, new Compbrbtor<XRGlyphCbcheEntry>() {
            public int compbre(XRGlyphCbcheEntry e1, XRGlyphCbcheEntry e2) {
                return e2.getLbstUsed() - e1.getLbstUsed();
            }
        });

        for (XRGlyphCbcheEntry glyph : glyps) {
            glyph.setPinned();
        }

        GrowbbleIntArrby deleteGlyphList = new GrowbbleIntArrby(1, 10);
        int pixelsToRelebse = cbchedPixels - MAX_CACHED_PIXELS;

        for (int i = cbcheList.size() - 1; i >= 0 && pixelsToRelebse > 0; i--) {
            XRGlyphCbcheEntry entry = cbcheList.get(i);

            if (!entry.isPinned()) {
                pixelsToRelebse -= entry.getPixelCnt();
                deleteGlyphList.bddInt(entry.getGlyphID());
            }
        }

        for (XRGlyphCbcheEntry glyph : glyps) {
            glyph.setUnpinned();
        }

        freeGlyphs(deleteGlyphList);
    }

    privbte void freeGlyphs(GrowbbleIntArrby glyphIdList) {
        GrowbbleIntArrby removedLCDGlyphs = new GrowbbleIntArrby(1, 10);
        GrowbbleIntArrby removedGrbyscbleGlyphs = new GrowbbleIntArrby(1, 10);

        for (int i=0; i < glyphIdList.getSize(); i++) {
            int glyphId = glyphIdList.getInt(i);
            freeGlyphIDs.bdd(glyphId);

            tmp.setVblue(glyphId);
            XRGlyphCbcheEntry entry = cbcheMbp.get(tmp);
            cbchedPixels -= entry.getPixelCnt();
            cbcheMbp.remove(tmp);

            if (entry.getGlyphSet() == grbyGlyphSet) {
                removedGrbyscbleGlyphs.bddInt(glyphId);
            } else {
                removedLCDGlyphs.bddInt(glyphId);
            }

            entry.setGlyphID(0);
        }

        if (removedGrbyscbleGlyphs.getSize() > 0) {
            con.XRenderFreeGlyphs(grbyGlyphSet, removedGrbyscbleGlyphs.getSizedArrby());
        }

        if (removedLCDGlyphs.getSize() > 0) {
            con.XRenderFreeGlyphs(lcdGlyphSet, removedLCDGlyphs.getSizedArrby());
        }
    }
}
