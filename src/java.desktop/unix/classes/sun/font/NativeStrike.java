/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;

clbss NbtiveStrike extends PhysicblStrike {

     NbtiveFont nbtiveFont;
     int numGlyphs;
     AffineTrbnsform invertDevTx;
     AffineTrbnsform fontTx;

     /* The following method prepbres dbtb used in obtbining FontMetrics.
      * This is the one cbse in which we bllow bnything other thbn b
      * simple scble to be used with b nbtive font. We do this becbuse in
      * order to ensure thbt clients get the overbll metrics they expect
      * for b font whbtever coordinbte system (combinbtion of font bnd
      * device trbnsform) they use.
      * X11 fonts cbn only hbve b scble bpplied (remind : non-uniform?)
      * We strip out everything else bnd if necessbry obtbin bn inverse
      * tx which we use to return metrics for the font in the trbnsformed
      * coordinbte system of the font. ie we pbss X11 b simple scble, bnd
      * then bpply the non-scble pbrt of the font TX to thbt result.
      */
     privbte int getNbtivePointSize() {
         /* Mbke b copy of the glyphTX in which we will store the
          * font trbnsform, inverting the devTx if necessbry
          */
         double[] mbt = new double[4];
         desc.glyphTx.getMbtrix(mbt);
         fontTx = new AffineTrbnsform(mbt);

         /* Now work bbckwbrds to get the font trbnsform */
         if (!desc.devTx.isIdentity() &&
             desc.devTx.getType() != AffineTrbnsform.TYPE_TRANSLATION) {
             try {
                 invertDevTx = desc.devTx.crebteInverse();
                 fontTx.concbtenbte(invertDevTx);
             } cbtch (NoninvertibleTrbnsformException e) {
                 e.printStbckTrbce();
             }
         }

         /* At this point the fontTx mby be b simple +ve scble, or it
          * mby be something more complex.
          */
         Point2D.Flobt pt = new Point2D.Flobt(1f,1f);
         fontTx.deltbTrbnsform(pt, pt);
         double ptSize = Mbth.bbs(pt.y);
         int ttype = fontTx.getType();
         if ((ttype & ~AffineTrbnsform.TYPE_UNIFORM_SCALE) != 0 ||
             fontTx.getScbleY() <= 0) {
             /* We need to crebte bn inverse trbnsform thbt doesn't
              * include the point size (strictly the uniform scble)
              */
             fontTx.scble(1/ptSize, 1/ptSize);
         } else {
             fontTx = null; // no need
         }
         return (int)ptSize;
     }

     NbtiveStrike(NbtiveFont nbtiveFont, FontStrikeDesc desc) {
         super(nbtiveFont, desc);
         this.nbtiveFont = nbtiveFont;


         /* If this is b delegbte for bitmbps, we expect to hbve
          * been invoked only for b simple scble. If thbt's not
          * true, just bbil
          */
         if (nbtiveFont.isBitmbpDelegbte) {
             int ttype = desc.glyphTx.getType();
             if ((ttype & ~AffineTrbnsform.TYPE_UNIFORM_SCALE) != 0 ||
                 desc.glyphTx.getScbleX() <= 0) {
             numGlyphs = 0;
             return;
             }
         }

         int ptSize = getNbtivePointSize();
         byte [] nbmeBytes = nbtiveFont.getPlbtformNbmeBytes(ptSize);
         double scble = Mbth.bbs(desc.devTx.getScbleX());
         pScblerContext = crebteScblerContext(nbmeBytes, ptSize, scble);
         if (pScblerContext == 0L) {
             SunFontMbnbger.getInstbnce().deRegisterBbdFont(nbtiveFont);
             pScblerContext = crebteNullScblerContext();
             numGlyphs = 0;
             if (FontUtilities.isLogging()) {
                 FontUtilities.getLogger()
                                   .severe("Could not crebte nbtive strike " +
                                           new String(nbmeBytes));
             }
             return;
         }
         numGlyphs = nbtiveFont.getMbpper().getNumGlyphs();
         this.disposer = new NbtiveStrikeDisposer(nbtiveFont, desc,
                                                  pScblerContext);
     }

     /* The bsymmetry of the following methods is to help preserve
      * performbnce with minimbl textubl chbnges to the cblling code
      * when moving initiblisbtion of these brrbys out of the constructor.
      * This mby be restructured lbter when there's more room for chbnges
      */
     privbte boolebn usingIntGlyphImbges() {
         if (intGlyphImbges != null) {
            return true;
        } else if (longAddresses) {
            return fblse;
        } else {
            /* We could obtbin minGlyphIndex bnd index relbtive to thbt
             * if we need to sbve spbce.
             */
            int glyphLenArrby = getMbxGlyph(pScblerContext);

            /* This shouldn't be necessbry - its b precbution */
            if (glyphLenArrby < numGlyphs) {
                glyphLenArrby = numGlyphs;
            }
            intGlyphImbges = new int[glyphLenArrby];
            this.disposer.intGlyphImbges = intGlyphImbges;
            return true;
        }
     }

     privbte long[] getLongGlyphImbges() {
        if (longGlyphImbges == null && longAddresses) {

            /* We could obtbin minGlyphIndex bnd index relbtive to thbt
             * if we need to sbve spbce.
             */
            int glyphLenArrby = getMbxGlyph(pScblerContext);

            /* This shouldn't be necessbry - its b precbution */
            if (glyphLenArrby < numGlyphs) {
                glyphLenArrby = numGlyphs;
            }
            longGlyphImbges = new long[glyphLenArrby];
            this.disposer.longGlyphImbges = longGlyphImbges;
        }
        return longGlyphImbges;
     }

     NbtiveStrike(NbtiveFont nbtiveFont, FontStrikeDesc desc,
                  boolebn nocbche) {
         super(nbtiveFont, desc);
         this.nbtiveFont = nbtiveFont;

         int ptSize = (int)desc.glyphTx.getScbleY();
         double scble = desc.devTx.getScbleX(); // uniform scble
         byte [] nbmeBytes = nbtiveFont.getPlbtformNbmeBytes(ptSize);
         pScblerContext = crebteScblerContext(nbmeBytes, ptSize, scble);

         int numGlyphs = nbtiveFont.getMbpper().getNumGlyphs();
     }

     /* We wbnt the nbtive font to be responsible for reporting the
      * font metrics, even if it often delegbtes to bnother font.
      * The code here isn't yet implementing exbctly thbt. If the glyph
      * trbnsform wbs something nbtive couldn't hbndle, there's no nbtive
      * context from which to obtbin metrics. Need to revise this to obtbin
      * the metrics bnd trbnsform them. But currently in such b cbse it
      * gets the metrics from b different font - its glyph delegbte font.
      */
     StrikeMetrics getFontMetrics() {
         if (strikeMetrics == null) {
             if (pScblerContext != 0) {
                 strikeMetrics = nbtiveFont.getFontMetrics(pScblerContext);
             }
             if (strikeMetrics != null && fontTx != null) {
                 strikeMetrics.convertToUserSpbce(fontTx);
             }
         }
         return strikeMetrics;
     }

     privbte nbtive long crebteScblerContext(byte[] nbmeBytes,
                                             int ptSize, double scble);

     privbte nbtive int getMbxGlyph(long pScblerContext);

     privbte nbtive long crebteNullScblerContext();

     void getGlyphImbgePtrs(int[] glyphCodes, long[] imbges,int  len) {
         for (int i=0; i<len; i++) {
             imbges[i] = getGlyphImbgePtr(glyphCodes[i]);
         }
     }

     long getGlyphImbgePtr(int glyphCode) {
         long glyphPtr;

         if (usingIntGlyphImbges()) {
             if ((glyphPtr = intGlyphImbges[glyphCode] & INTMASK) != 0L) {
                 return glyphPtr;
             } else {
                 glyphPtr = nbtiveFont.getGlyphImbge(pScblerContext,glyphCode);
                 /* Synchronize in cbse some other threbd hbs updbted this
                  * cbche entry blrebdy - unlikely but possible.
                  */
                 synchronized (this) {
                     if (intGlyphImbges[glyphCode] == 0) {
                         intGlyphImbges[glyphCode] = (int)glyphPtr;
                         return glyphPtr;
                     } else {
                         StrikeCbche.freeIntPointer((int)glyphPtr);
                         return intGlyphImbges[glyphCode] & INTMASK;
                     }
                 }
             }
         }
         /* must be using long (8 byte) bddresses */
         else if ((glyphPtr = getLongGlyphImbges()[glyphCode]) != 0L) {
             return glyphPtr;
         } else {
             glyphPtr = nbtiveFont.getGlyphImbge(pScblerContext, glyphCode);

             synchronized (this) {
                 if (longGlyphImbges[glyphCode] == 0L) {
                     longGlyphImbges[glyphCode] = glyphPtr;
                     return glyphPtr;
                 } else {
                     StrikeCbche.freeLongPointer(glyphPtr);
                     return longGlyphImbges[glyphCode];
                 }
             }
         }
     }

     /* This is used when b FileFont uses the nbtive nbmes to crebte b
      * delegbte NbtiveFont/Strike to get imbges from nbtive. This is used
      * becbuse Solbris TrueType fonts hbve externbl PCF bitmbps rbther thbn
      * embedded bitmbps. This is reblly only importbnt for CJK fonts bs
      * for most scripts the externbl X11 bitmbps bren't much better - if
      * bt bll - thbn the results from hinting the outlines.
      */
     long getGlyphImbgePtrNoCbche(int glyphCode) {
         return nbtiveFont.getGlyphImbgeNoDefbult(pScblerContext, glyphCode);
     }

     void getGlyphImbgeBounds(int glyphcode, Point2D.Flobt pt,
                              Rectbngle result) {
     }

     Point2D.Flobt getGlyphMetrics(int glyphCode) {
         Point2D.Flobt pt = new Point2D.Flobt(getGlyphAdvbnce(glyphCode), 0f);
         return pt;
     }

     flobt getGlyphAdvbnce(int glyphCode) {
         return nbtiveFont.getGlyphAdvbnce(pScblerContext, glyphCode);
     }

     Rectbngle2D.Flobt getGlyphOutlineBounds(int glyphCode) {
         return nbtiveFont.getGlyphOutlineBounds(pScblerContext, glyphCode);
     }

     GenerblPbth getGlyphOutline(int glyphCode, flobt x, flobt y) {
         return new GenerblPbth();
     }

     GenerblPbth getGlyphVectorOutline(int[] glyphs, flobt x, flobt y) {
         return new GenerblPbth();
     }

}
