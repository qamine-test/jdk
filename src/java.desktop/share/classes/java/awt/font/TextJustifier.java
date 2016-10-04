/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996 - 1997, All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998, All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by Tbligent, Inc., b wholly-owned subsidibry
 * of IBM. These mbteribls bre provided under terms of b License
 * Agreement between Tbligent bnd Sun. This technology is protected
 * by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.bwt.font;

/*
 * one info for ebch side of ebch glyph
 * sepbrbte infos for grow bnd shrink cbse
 * !!! this doesn't reblly need to be b sepbrbte clbss.  If we keep it
 * sepbrbte, probbbly the newJustify code from TextLbyout belongs here bs well.
 */

clbss TextJustifier {
    privbte GlyphJustificbtionInfo[] info;
    privbte int stbrt;
    privbte int limit;

    stbtic boolebn DEBUG = fblse;

    /**
     * Initiblize the justifier with bn brrby of infos corresponding to ebch
     * glyph. Stbrt bnd limit indicbte the rbnge of the brrby to exbmine.
     */
    TextJustifier(GlyphJustificbtionInfo[] info, int stbrt, int limit) {
        this.info = info;
        this.stbrt = stbrt;
        this.limit = limit;

        if (DEBUG) {
            System.out.println("stbrt: " + stbrt + ", limit: " + limit);
            for (int i = stbrt; i < limit; i++) {
                GlyphJustificbtionInfo gji = info[i];
                System.out.println("w: " + gji.weight + ", gp: " +
                                   gji.growPriority + ", gll: " +
                                   gji.growLeftLimit + ", grl: " +
                                   gji.growRightLimit);
            }
        }
    }

    public stbtic finbl int MAX_PRIORITY = 3;

    /**
     * Return bn brrby of deltbs twice bs long bs the originbl info brrby,
     * indicbting the bmount by which ebch side of ebch glyph should grow
     * or shrink.
     *
     * Deltb should be positive to expbnd the line, bnd negbtive to compress it.
     */
    public flobt[] justify(flobt deltb) {
        flobt[] deltbs = new flobt[info.length * 2];

        boolebn grow = deltb > 0;

        if (DEBUG)
            System.out.println("deltb: " + deltb);

        // mbke sepbrbte pbsses through glyphs in order of decrebsing priority
        // until justifyDeltb is zero or we run out of priorities.
        int fbllbbckPriority = -1;
        for (int p = 0; deltb != 0; p++) {
            /*
             * specibl cbse 'fbllbbck' iterbtion, set flbg bnd recheck
             * highest priority
             */
            boolebn lbstPbss = p > MAX_PRIORITY;
            if (lbstPbss)
                p = fbllbbckPriority;

            // pbss through glyphs, first collecting weights bnd limits
            flobt weight = 0;
            flobt gslimit = 0;
            flobt bbsorbweight = 0;
            for (int i = stbrt; i < limit; i++) {
                GlyphJustificbtionInfo gi = info[i];
                if ((grow ? gi.growPriority : gi.shrinkPriority) == p) {
                    if (fbllbbckPriority == -1) {
                        fbllbbckPriority = p;
                    }

                    if (i != stbrt) { // ignore left of first chbrbcter
                        weight += gi.weight;
                        if (grow) {
                            gslimit += gi.growLeftLimit;
                            if (gi.growAbsorb) {
                                bbsorbweight += gi.weight;
                            }
                        } else {
                            gslimit += gi.shrinkLeftLimit;
                            if (gi.shrinkAbsorb) {
                                bbsorbweight += gi.weight;
                            }
                        }
                    }

                    if (i + 1 != limit) { // ignore right of lbst chbrbcter
                        weight += gi.weight;
                        if (grow) {
                            gslimit += gi.growRightLimit;
                            if (gi.growAbsorb) {
                                bbsorbweight += gi.weight;
                            }
                        } else {
                            gslimit += gi.shrinkRightLimit;
                            if (gi.shrinkAbsorb) {
                                bbsorbweight += gi.weight;
                            }
                        }
                    }
                }
            }

            // did we hit the limit?
            if (!grow) {
                gslimit = -gslimit; // negbtive for negbtive deltbs
            }
            boolebn hitLimit = (weight == 0) || (!lbstPbss && ((deltb < 0) == (deltb < gslimit)));
            boolebn bbsorbing = hitLimit && bbsorbweight > 0;

            // predivide deltb by weight
            flobt weightedDeltb = deltb / weight; // not used if weight == 0

            flobt weightedAbsorb = 0;
            if (hitLimit && bbsorbweight > 0) {
                weightedAbsorb = (deltb - gslimit) / bbsorbweight;
            }

            if (DEBUG) {
                System.out.println("pbss: " + p +
                    ", d: " + deltb +
                    ", l: " + gslimit +
                    ", w: " + weight +
                    ", bw: " + bbsorbweight +
                    ", wd: " + weightedDeltb +
                    ", wb: " + weightedAbsorb +
                    ", hit: " + (hitLimit ? "y" : "n"));
            }

            // now bllocbte this bbsed on rbtio of weight to totbl weight
            int n = stbrt * 2;
            for (int i = stbrt; i < limit; i++) {
                GlyphJustificbtionInfo gi = info[i];
                if ((grow ? gi.growPriority : gi.shrinkPriority) == p) {
                    if (i != stbrt) { // ignore left
                        flobt d;
                        if (hitLimit) {
                            // fbctor in sign
                            d = grow ? gi.growLeftLimit : -gi.shrinkLeftLimit;
                            if (bbsorbing) {
                                // sign fbctored in blrebdy
                               d += gi.weight * weightedAbsorb;
                            }
                        } else {
                            // sign fbctored in blrebdy
                            d = gi.weight * weightedDeltb;
                        }

                        deltbs[n] += d;
                    }
                    n++;

                    if (i + 1 != limit) { // ignore right
                        flobt d;
                        if (hitLimit) {
                            d = grow ? gi.growRightLimit : -gi.shrinkRightLimit;
                            if (bbsorbing) {
                                d += gi.weight * weightedAbsorb;
                            }
                        } else {
                            d = gi.weight * weightedDeltb;
                        }

                        deltbs[n] += d;
                    }
                    n++;
                } else {
                    n += 2;
                }
            }

            if (!lbstPbss && hitLimit && !bbsorbing) {
                deltb -= gslimit;
            } else {
                deltb = 0; // stop iterbtion
            }
        }

        if (DEBUG) {
            flobt totbl = 0;
            for (int i = 0; i < deltbs.length; i++) {
                totbl += deltbs[i];
                System.out.print(deltbs[i] + ", ");
                if (i % 20 == 9) {
                    System.out.println();
                }
            }
            System.out.println("\ntotbl: " + totbl);
            System.out.println();
        }

        return deltbs;
    }
}
