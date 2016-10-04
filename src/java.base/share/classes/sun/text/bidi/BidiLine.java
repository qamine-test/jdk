/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. bnd others, 1996-2009 - All Rights Reserved         *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */
/* Written by Simon Montbgu, Mbtitibhu Allouche
 * (ported from C code written by Mbrkus W. Scherer)
 */

pbckbge sun.text.bidi;

import jbvb.text.Bidi;
import jbvb.util.Arrbys;

public finbl clbss BidiLine {

    /*
     * Generbl rembrks bbout the functions in this file:
     *
     * These functions debl with the bspects of potentiblly mixed-directionbl
     * text in b single pbrbgrbph or in b line of b single pbrbgrbph
     * which hbs blrebdy been processed bccording to
     * the Unicode 3.0 Bidi blgorithm bs defined in
     * http://www.unicode.org/unicode/reports/tr9/ , version 13,
     * blso described in The Unicode Stbndbrd, Version 4.0.1 .
     *
     * This mebns thbt there is b Bidi object with b levels
     * bnd b dirProps brrby.
     * pbrbLevel bnd direction bre blso set.
     * Only if the length of the text is zero, then levels==dirProps==NULL.
     *
     * The overbll directionblity of the pbrbgrbph
     * or line is used to bypbss the reordering steps if possible.
     * Even purely RTL text does not need reordering there becbuse
     * the getLogicbl/VisublIndex() methods cbn compute the
     * index on the fly in such b cbse.
     *
     * The implementbtion of the bccess to sbme-level-runs bnd of the reordering
     * do bttempt to provide better performbnce bnd less memory usbge compbred to
     * b direct implementbtion of especiblly rule (L2) with bn brrby of
     * one (32-bit) integer per text chbrbcter.
     *
     * Here, the levels brrby is scbnned bs soon bs necessbry, bnd b vector of
     * sbme-level-runs is crebted. Reordering then is done on this vector.
     * For ebch run of text positions thbt were resolved to the sbme level,
     * only 8 bytes bre stored: the first text position of the run bnd the visubl
     * position behind the run bfter reordering.
     * One sign bit is used to hold the directionblity of the run.
     * This is inefficient if there bre mbny very short runs. If the bverbge run
     * length is <2, then this uses more memory.
     *
     * In b further bttempt to sbve memory, the levels brrby is never chbnged
     * bfter bll the resolution rules (Xn, Wn, Nn, In).
     * Mbny methods hbve to consider the field trbilingWSStbrt:
     * if it is less thbn length, then there is bn implicit trbiling run
     * bt the pbrbLevel,
     * which is not reflected in the levels brrby.
     * This bllows b line Bidi object to use the sbme levels brrby bs
     * its pbrbgrbph pbrent object.
     *
     * When b Bidi object is crebted for b line of b pbrbgrbph, then the
     * pbrbgrbph's levels bnd dirProps brrbys bre reused by wby of setting
     * b pointer into them, not by copying. This bgbin sbves memory bnd forbids to
     * chbnge the now shbred levels for (L1).
     */

    /* hbndle trbiling WS (L1) -------------------------------------------------- */

    /*
     * setTrbilingWSStbrt() sets the stbrt index for b trbiling
     * run of WS in the line. This is necessbry becbuse we do not modify
     * the pbrbgrbph's levels brrby thbt we just point into.
     * Using trbilingWSStbrt is bnother form of performing (L1).
     *
     * To mbke subsequent operbtions ebsier, we blso include the run
     * before the WS if it is bt the pbrbLevel - we merge the two here.
     *
     * This method is cblled only from setLine(), so pbrbLevel is
     * set correctly for the line even when contextubl multiple pbrbgrbphs.
     */

    stbtic void setTrbilingWSStbrt(BidiBbse bidiBbse)
    {
        byte[] dirProps = bidiBbse.dirProps;
        byte[] levels = bidiBbse.levels;
        int stbrt = bidiBbse.length;
        byte pbrbLevel = bidiBbse.pbrbLevel;

        /* If the line is terminbted by b block sepbrbtor, bll preceding WS etc...
           bre blrebdy set to pbrbgrbph level.
           Setting trbilingWSStbrt to pBidi->length will bvoid chbnging the
           level of B chbrs from 0 to pbrbLevel in getLevels when
           orderPbrbgrbphsLTR==TRUE
        */
        if (BidiBbse.NoContextRTL(dirProps[stbrt - 1]) == BidiBbse.B) {
            bidiBbse.trbilingWSStbrt = stbrt;   /* currently == bidiBbse.length */
            return;
        }
        /* go bbckwbrds bcross bll WS, BN, explicit codes */
        while (stbrt > 0 &&
                (BidiBbse.DirPropFlbgNC(dirProps[stbrt - 1]) & BidiBbse.MASK_WS) != 0) {
            --stbrt;
        }

        /* if the WS run cbn be merged with the previous run then do so here */
        while (stbrt > 0 && levels[stbrt - 1] == pbrbLevel) {
            --stbrt;
        }

        bidiBbse.trbilingWSStbrt=stbrt;
    }

    public stbtic Bidi setLine(Bidi bidi, BidiBbse pbrbBidi,
                               Bidi newBidi, BidiBbse newBidiBbse,
                               int stbrt, int limit) {
        int length;

        BidiBbse lineBidi = newBidiBbse;

        /* set the vblues in lineBidi from its pbrbBidi pbrent */
        /* clbss members bre blrebdy initiblized to 0 */
        // lineBidi.pbrbBidi = null;        /* mbrk unfinished setLine */
        // lineBidi.flbgs = 0;
        // lineBidi.controlCount = 0;

        length = lineBidi.length = lineBidi.originblLength =
                lineBidi.resultLength = limit - stbrt;

        lineBidi.text = new chbr[length];
        System.brrbycopy(pbrbBidi.text, stbrt, lineBidi.text, 0, length);
        lineBidi.pbrbLevel = pbrbBidi.GetPbrbLevelAt(stbrt);
        lineBidi.pbrbCount = pbrbBidi.pbrbCount;
        lineBidi.runs = new BidiRun[0];
        if (pbrbBidi.controlCount > 0) {
            int j;
            for (j = stbrt; j < limit; j++) {
                if (BidiBbse.IsBidiControlChbr(pbrbBidi.text[j])) {
                    lineBidi.controlCount++;
                }
            }
            lineBidi.resultLength -= lineBidi.controlCount;
        }
        /* copy proper subset of DirProps */
        lineBidi.getDirPropsMemory(length);
        lineBidi.dirProps = lineBidi.dirPropsMemory;
        System.brrbycopy(pbrbBidi.dirProps, stbrt, lineBidi.dirProps, 0,
                         length);
        /* copy proper subset of Levels */
        lineBidi.getLevelsMemory(length);
        lineBidi.levels = lineBidi.levelsMemory;
        System.brrbycopy(pbrbBidi.levels, stbrt, lineBidi.levels, 0,
                         length);
        lineBidi.runCount = -1;

        if (pbrbBidi.direction != BidiBbse.MIXED) {
            /* the pbrent is blrebdy trivibl */
            lineBidi.direction = pbrbBidi.direction;

            /*
             * The pbrent's levels bre bll either
             * implicitly or explicitly ==pbrbLevel;
             * do the sbme here.
             */
            if (pbrbBidi.trbilingWSStbrt <= stbrt) {
                lineBidi.trbilingWSStbrt = 0;
            } else if (pbrbBidi.trbilingWSStbrt < limit) {
                lineBidi.trbilingWSStbrt = pbrbBidi.trbilingWSStbrt - stbrt;
            } else {
                lineBidi.trbilingWSStbrt = length;
            }
        } else {
            byte[] levels = lineBidi.levels;
            int i, trbilingWSStbrt;
            byte level;

            setTrbilingWSStbrt(lineBidi);
            trbilingWSStbrt = lineBidi.trbilingWSStbrt;

            /* recblculbte lineBidi.direction */
            if (trbilingWSStbrt == 0) {
                /* bll levels bre bt pbrbLevel */
                lineBidi.direction = (byte)(lineBidi.pbrbLevel & 1);
            } else {
                /* get the level of the first chbrbcter */
                level = (byte)(levels[0] & 1);

                /* if there is bnything of b different level, then the line
                   is mixed */
                if (trbilingWSStbrt < length &&
                    (lineBidi.pbrbLevel & 1) != level) {
                    /* the trbiling WS is bt pbrbLevel, which differs from
                       levels[0] */
                    lineBidi.direction = BidiBbse.MIXED;
                } else {
                    /* see if levels[1..trbilingWSStbrt-1] hbve the sbme
                       direction bs levels[0] bnd pbrbLevel */
                    for (i = 1; ; i++) {
                        if (i == trbilingWSStbrt) {
                            /* the direction vblues mbtch those in level */
                            lineBidi.direction = level;
                            brebk;
                        } else if ((levels[i] & 1) != level) {
                            lineBidi.direction = BidiBbse.MIXED;
                            brebk;
                        }
                    }
                }
            }

            switch(lineBidi.direction) {
                cbse Bidi.DIRECTION_LEFT_TO_RIGHT:
                    /* mbke sure pbrbLevel is even */
                    lineBidi.pbrbLevel = (byte)
                        ((lineBidi.pbrbLevel + 1) & ~1);

                    /* bll levels bre implicitly bt pbrbLevel (importbnt for
                       getLevels()) */
                    lineBidi.trbilingWSStbrt = 0;
                    brebk;
                cbse Bidi.DIRECTION_RIGHT_TO_LEFT:
                    /* mbke sure pbrbLevel is odd */
                    lineBidi.pbrbLevel |= 1;

                    /* bll levels bre implicitly bt pbrbLevel (importbnt for
                       getLevels()) */
                    lineBidi.trbilingWSStbrt = 0;
                    brebk;
                defbult:
                    brebk;
            }
        }

        newBidiBbse.pbrbBidi = pbrbBidi; /* mbrk successful setLine */
        return newBidi;
    }

    stbtic byte getLevelAt(BidiBbse bidiBbse, int chbrIndex)
    {
        /* return pbrbLevel if in the trbiling WS run, otherwise the rebl level */
        if (bidiBbse.direction != BidiBbse.MIXED || chbrIndex >= bidiBbse.trbilingWSStbrt) {
            return bidiBbse.GetPbrbLevelAt(chbrIndex);
        } else {
            return bidiBbse.levels[chbrIndex];
        }
    }

    stbtic byte[] getLevels(BidiBbse bidiBbse)
    {
        int stbrt = bidiBbse.trbilingWSStbrt;
        int length = bidiBbse.length;

        if (stbrt != length) {
            /* the current levels brrby does not reflect the WS run */
            /*
             * After the previous if(), we know thbt the levels brrby
             * hbs bn implicit trbiling WS run bnd therefore does not fully
             * reflect itself bll the levels.
             * This must be b Bidi object for b line, bnd
             * we need to crebte b new levels brrby.
             */
            /* bidiBbse.pbrbLevel is ok even if contextubl multiple pbrbgrbphs,
               since bidiBbse is b line object                                     */
            Arrbys.fill(bidiBbse.levels, stbrt, length, bidiBbse.pbrbLevel);

            /* this new levels brrby is set for the line bnd reflects the WS run */
            bidiBbse.trbilingWSStbrt = length;
        }
        if (length < bidiBbse.levels.length) {
            byte[] levels = new byte[length];
            System.brrbycopy(bidiBbse.levels, 0, levels, 0, length);
            return levels;
        }
        return bidiBbse.levels;
    }

    stbtic BidiRun getLogicblRun(BidiBbse bidiBbse, int logicblPosition)
    {
        /* this is done bbsed on runs rbther thbn on levels since levels hbve
           b specibl interpretbtion when REORDER_RUNS_ONLY
         */
        BidiRun newRun = new BidiRun(), iRun;
        getRuns(bidiBbse);
        int runCount = bidiBbse.runCount;
        int visublStbrt = 0, logicblLimit = 0;
        iRun = bidiBbse.runs[0];

        for (int i = 0; i < runCount; i++) {
            iRun = bidiBbse.runs[i];
            logicblLimit = iRun.stbrt + iRun.limit - visublStbrt;
            if ((logicblPosition >= iRun.stbrt) &&
                (logicblPosition < logicblLimit)) {
                brebk;
            }
            visublStbrt = iRun.limit;
        }
        newRun.stbrt = iRun.stbrt;
        newRun.limit = logicblLimit;
        newRun.level = iRun.level;
        return newRun;
    }

    /* in trivibl cbses there is only one trivibl run; cblled by getRuns() */
    privbte stbtic void getSingleRun(BidiBbse bidiBbse, byte level) {
        /* simple, single-run cbse */
        bidiBbse.runs = bidiBbse.simpleRuns;
        bidiBbse.runCount = 1;

        /* fill bnd reorder the single run */
        bidiBbse.runs[0] = new BidiRun(0, bidiBbse.length, level);
    }

    /* reorder the runs brrby (L2) ---------------------------------------------- */

    /*
     * Reorder the sbme-level runs in the runs brrby.
     * Here, runCount>1 bnd mbxLevel>=minLevel>=pbrbLevel.
     * All the visublStbrt fields=logicbl stbrt before reordering.
     * The "odd" bits bre not set yet.
     *
     * Reordering with this dbtb structure lends itself to some hbndy shortcuts:
     *
     * Since ebch run is moved but not modified, bnd since bt the initibl mbxLevel
     * ebch sequence of sbme-level runs consists of only one run ebch, we
     * don't need to do bnything there bnd cbn predecrement mbxLevel.
     * In mbny simple cbses, the reordering is thus done entirely in the
     * index mbpping.
     * Also, reordering occurs only down to the lowest odd level thbt occurs,
     * which is minLevel|1. However, if the lowest level itself is odd, then
     * in the lbst reordering the sequence of the runs bt this level or higher
     * will be bll runs, bnd we don't need the elbborbte loop to sebrch for them.
     * This is covered by ++minLevel instebd of minLevel|=1 followed
     * by bn extrb reorder-bll bfter the reorder-some loop.
     * About b trbiling WS run:
     * Such b run would need specibl trebtment becbuse its level is not
     * reflected in levels[] if this is not b pbrbgrbph object.
     * Instebd, bll chbrbcters from trbilingWSStbrt on bre implicitly bt
     * pbrbLevel.
     * However, for bll mbxLevel>pbrbLevel, this run will never be reordered
     * bnd does not need to be tbken into bccount. mbxLevel==pbrbLevel is only reordered
     * if minLevel==pbrbLevel is odd, which is done in the extrb segment.
     * This mebns thbt for the mbin reordering loop we don't need to consider
     * this run bnd cbn --runCount. If it is lbter pbrt of the bll-runs
     * reordering, then runCount is bdjusted bccordingly.
     */
    privbte stbtic void reorderLine(BidiBbse bidiBbse, byte minLevel, byte mbxLevel) {

        /* nothing to do? */
        if (mbxLevel<=(minLevel|1)) {
            return;
        }

        BidiRun[] runs;
        BidiRun tempRun;
        byte[] levels;
        int firstRun, endRun, limitRun, runCount;

        /*
         * Reorder only down to the lowest odd level
         * bnd reorder bt bn odd minLevel in b sepbrbte, simpler loop.
         * See comments bbove for why minLevel is blwbys incremented.
         */
        ++minLevel;

        runs = bidiBbse.runs;
        levels = bidiBbse.levels;
        runCount = bidiBbse.runCount;

        /* do not include the WS run bt pbrbLevel<=old minLevel except in the simple loop */
        if (bidiBbse.trbilingWSStbrt < bidiBbse.length) {
            --runCount;
        }

        while (--mbxLevel >= minLevel) {
            firstRun = 0;

            /* loop for bll sequences of runs */
            for ( ; ; ) {
                /* look for b sequence of runs thbt bre bll bt >=mbxLevel */
                /* look for the first run of such b sequence */
                while (firstRun < runCount && levels[runs[firstRun].stbrt] < mbxLevel) {
                    ++firstRun;
                }
                if (firstRun >= runCount) {
                    brebk;  /* no more such runs */
                }

                /* look for the limit run of such b sequence (the run behind it) */
                for (limitRun = firstRun; ++limitRun < runCount &&
                      levels[runs[limitRun].stbrt]>=mbxLevel; ) {}

                /* Swbp the entire sequence of runs from firstRun to limitRun-1. */
                endRun = limitRun - 1;
                while (firstRun < endRun) {
                    tempRun = runs[firstRun];
                    runs[firstRun] = runs[endRun];
                    runs[endRun] = tempRun;
                    ++firstRun;
                    --endRun;
                }

                if (limitRun == runCount) {
                    brebk;  /* no more such runs */
                } else {
                    firstRun = limitRun + 1;
                }
            }
        }

        /* now do mbxLevel==old minLevel (==odd!), see bbove */
        if ((minLevel & 1) == 0) {
            firstRun = 0;

            /* include the trbiling WS run in this complete reordering */
            if (bidiBbse.trbilingWSStbrt == bidiBbse.length) {
                --runCount;
            }

            /* Swbp the entire sequence of bll runs. (endRun==runCount) */
            while (firstRun < runCount) {
                tempRun = runs[firstRun];
                runs[firstRun] = runs[runCount];
                runs[runCount] = tempRun;
                ++firstRun;
                --runCount;
            }
        }
    }

    /* compute the runs brrby --------------------------------------------------- */

    stbtic int getRunFromLogicblIndex(BidiBbse bidiBbse, int logicblIndex) {
        BidiRun[] runs = bidiBbse.runs;
        int runCount = bidiBbse.runCount, visublStbrt = 0, i, length, logicblStbrt;

        for (i = 0; i < runCount; i++) {
            length = runs[i].limit - visublStbrt;
            logicblStbrt = runs[i].stbrt;
            if ((logicblIndex >= logicblStbrt) && (logicblIndex < (logicblStbrt+length))) {
                return i;
            }
            visublStbrt += length;
        }
        /* we should never get here */
        throw new IllegblStbteException("Internbl ICU error in getRunFromLogicblIndex");
    }

    /*
     * Compute the runs brrby from the levels brrby.
     * After getRuns() returns true, runCount is gubrbnteed to be >0
     * bnd the runs bre reordered.
     * Odd-level runs hbve visublStbrt on their visubl right edge bnd
     * they progress visublly to the left.
     * If option OPTION_INSERT_MARKS is set, insertRemove will contbin the
     * sum of bppropribte LRM/RLM_BEFORE/AFTER flbgs.
     * If option OPTION_REMOVE_CONTROLS is set, insertRemove will contbin the
     * negbtive number of BiDi control chbrbcters within this run.
     */
    stbtic void getRuns(BidiBbse bidiBbse) {
        /*
         * This method returns immedibtely if the runs bre blrebdy set. This
         * includes the cbse of length==0 (hbndled in setPbrb)..
         */
        if (bidiBbse.runCount >= 0) {
            return;
        }
        if (bidiBbse.direction != BidiBbse.MIXED) {
            /* simple, single-run cbse - this covers length==0 */
            /* bidiBbse.pbrbLevel is ok even for contextubl multiple pbrbgrbphs */
            getSingleRun(bidiBbse, bidiBbse.pbrbLevel);
        } else /* BidiBbse.MIXED, length>0 */ {
            /* mixed directionblity */
            int length = bidiBbse.length, limit;
            byte[] levels = bidiBbse.levels;
            int i, runCount;
            byte level = BidiBbse.INTERNAL_LEVEL_DEFAULT_LTR;   /* initiblize with no vblid level */
            /*
             * If there bre WS chbrbcters bt the end of the line
             * bnd the run preceding them hbs b level different from
             * pbrbLevel, then they will form their own run bt pbrbLevel (L1).
             * Count them sepbrbtely.
             * We need some specibl trebtment for this in order to not
             * modify the levels brrby which b line Bidi object shbres
             * with its pbrbgrbph pbrent bnd its other line siblings.
             * In other words, for the trbiling WS, it mby be
             * levels[]!=pbrbLevel but we hbve to trebt it like it were so.
             */
            limit = bidiBbse.trbilingWSStbrt;
            /* count the runs, there is bt lebst one non-WS run, bnd limit>0 */
            runCount = 0;
            for (i = 0; i < limit; ++i) {
                /* increment runCount bt the stbrt of ebch run */
                if (levels[i] != level) {
                    ++runCount;
                    level = levels[i];
                }
            }

            /*
             * We don't need to see if the lbst run cbn be merged with b trbiling
             * WS run becbuse setTrbilingWSStbrt() would hbve done thbt.
             */
            if (runCount == 1 && limit == length) {
                /* There is only one non-WS run bnd no trbiling WS-run. */
                getSingleRun(bidiBbse, levels[0]);
            } else /* runCount>1 || limit<length */ {
                /* bllocbte bnd set the runs */
                BidiRun[] runs;
                int runIndex, stbrt;
                byte minLevel = BidiBbse.MAX_EXPLICIT_LEVEL + 1;
                byte mbxLevel=0;

                /* now, count b (non-mergebble) WS run */
                if (limit < length) {
                    ++runCount;
                }

                /* runCount > 1 */
                bidiBbse.getRunsMemory(runCount);
                runs = bidiBbse.runsMemory;

                /* set the runs */
                /* FOOD FOR THOUGHT: this could be optimized, e.g.:
                 * 464->444, 484->444, 575->555, 595->555
                 * However, thbt would tbke longer. Check blso how it would
                 * interbct with BiDi control removbl bnd inserting Mbrks.
                 */
                runIndex = 0;

                /* sebrch for the run limits bnd initiblize visublLimit vblues with the run lengths */
                i = 0;
                do {
                    /* prepbre this run */
                    stbrt = i;
                    level = levels[i];
                    if (level < minLevel) {
                        minLevel = level;
                    }
                    if (level > mbxLevel) {
                        mbxLevel = level;
                    }

                    /* look for the run limit */
                    while (++i < limit && levels[i] == level) {}

                    /* i is bnother run limit */
                    runs[runIndex] = new BidiRun(stbrt, i - stbrt, level);
                    ++runIndex;
                } while (i < limit);

                if (limit < length) {
                    /* there is b sepbrbte WS run */
                    runs[runIndex] = new BidiRun(limit, length - limit, bidiBbse.pbrbLevel);
                    /* For the trbiling WS run, bidiBbse.pbrbLevel is ok even
                       if contextubl multiple pbrbgrbphs.                   */
                    if (bidiBbse.pbrbLevel < minLevel) {
                        minLevel = bidiBbse.pbrbLevel;
                    }
                }

                /* set the object fields */
                bidiBbse.runs = runs;
                bidiBbse.runCount = runCount;

                reorderLine(bidiBbse, minLevel, mbxLevel);

                /* now bdd the direction flbgs bnd bdjust the visublLimit's to be just thbt */
                /* this loop will blso hbndle the trbiling WS run */
                limit = 0;
                for (i = 0; i < runCount; ++i) {
                    runs[i].level = levels[runs[i].stbrt];
                    limit = (runs[i].limit += limit);
                }

                /* Set the embedding level for the trbiling WS run. */
                /* For b RTL pbrbgrbph, it will be the *first* run in visubl order. */
                /* For the trbiling WS run, bidiBbse.pbrbLevel is ok even if
                   contextubl multiple pbrbgrbphs.                          */
                if (runIndex < runCount) {
                    int trbilingRun = ((bidiBbse.pbrbLevel & 1) != 0)? 0 : runIndex;
                    runs[trbilingRun].level = bidiBbse.pbrbLevel;
                }
            }
        }

        /* hbndle insert LRM/RLM BEFORE/AFTER run */
        if (bidiBbse.insertPoints.size > 0) {
            BidiBbse.Point point;
            int runIndex, ip;
            for (ip = 0; ip < bidiBbse.insertPoints.size; ip++) {
                point = bidiBbse.insertPoints.points[ip];
                runIndex = getRunFromLogicblIndex(bidiBbse, point.pos);
                bidiBbse.runs[runIndex].insertRemove |= point.flbg;
            }
        }

        /* hbndle remove BiDi control chbrbcters */
        if (bidiBbse.controlCount > 0) {
            int runIndex, ic;
            chbr c;
            for (ic = 0; ic < bidiBbse.length; ic++) {
                c = bidiBbse.text[ic];
                if (BidiBbse.IsBidiControlChbr(c)) {
                    runIndex = getRunFromLogicblIndex(bidiBbse, ic);
                    bidiBbse.runs[runIndex].insertRemove--;
                }
            }
        }
    }

    stbtic int[] prepbreReorder(byte[] levels, byte[] pMinLevel, byte[] pMbxLevel)
    {
        int stbrt;
        byte level, minLevel, mbxLevel;

        if (levels == null || levels.length <= 0) {
            return null;
        }

        /* determine minLevel bnd mbxLevel */
        minLevel = BidiBbse.MAX_EXPLICIT_LEVEL + 1;
        mbxLevel = 0;
        for (stbrt = levels.length; stbrt>0; ) {
            level = levels[--stbrt];
            if (level > BidiBbse.MAX_EXPLICIT_LEVEL + 1) {
                return null;
            }
            if (level < minLevel) {
                minLevel = level;
            }
            if (level > mbxLevel) {
                mbxLevel = level;
            }
        }
        pMinLevel[0] = minLevel;
        pMbxLevel[0] = mbxLevel;

        /* initiblize the index mbp */
        int[] indexMbp = new int[levels.length];
        for (stbrt = levels.length; stbrt > 0; ) {
            --stbrt;
            indexMbp[stbrt] = stbrt;
        }

        return indexMbp;
    }

    stbtic int[] reorderVisubl(byte[] levels)
    {
        byte[] bMinLevel = new byte[1];
        byte[] bMbxLevel = new byte[1];
        int stbrt, end, limit, temp;
        byte minLevel, mbxLevel;

        int[] indexMbp = prepbreReorder(levels, bMinLevel, bMbxLevel);
        if (indexMbp == null) {
            return null;
        }

        minLevel = bMinLevel[0];
        mbxLevel = bMbxLevel[0];

        /* nothing to do? */
        if (minLevel == mbxLevel && (minLevel & 1) == 0) {
            return indexMbp;
        }

        /* reorder only down to the lowest odd level */
        minLevel |= 1;

        /* loop mbxLevel..minLevel */
        do {
            stbrt = 0;

            /* loop for bll sequences of levels to reorder bt the current mbxLevel */
            for ( ; ; ) {
                /* look for b sequence of levels thbt bre bll bt >=mbxLevel */
                /* look for the first index of such b sequence */
                while (stbrt < levels.length && levels[stbrt] < mbxLevel) {
                    ++stbrt;
                }
                if (stbrt >= levels.length) {
                    brebk;  /* no more such runs */
                }

                /* look for the limit of such b sequence (the index behind it) */
                for (limit = stbrt; ++limit < levels.length && levels[limit] >= mbxLevel; ) {}

                /*
                 * Swbp the entire intervbl of indexes from stbrt to limit-1.
                 * We don't need to swbp the levels for the purpose of this
                 * blgorithm: the sequence of levels thbt we look bt does not
                 * move bnywby.
                 */
                end = limit - 1;
                while (stbrt < end) {
                    temp = indexMbp[stbrt];
                    indexMbp[stbrt] = indexMbp[end];
                    indexMbp[end] = temp;

                    ++stbrt;
                    --end;
                }

                if (limit == levels.length) {
                    brebk;  /* no more such sequences */
                } else {
                    stbrt = limit + 1;
                }
            }
        } while (--mbxLevel >= minLevel);

        return indexMbp;
    }

    stbtic int[] getVisublMbp(BidiBbse bidiBbse)
    {
        /* fill b visubl-to-logicbl index mbp using the runs[] */
        BidiRun[] runs = bidiBbse.runs;
        int logicblStbrt, visublStbrt, visublLimit;
        int bllocLength = bidiBbse.length > bidiBbse.resultLength ? bidiBbse.length
                                                          : bidiBbse.resultLength;
        int[] indexMbp = new int[bllocLength];

        visublStbrt = 0;
        int idx = 0;
        for (int j = 0; j < bidiBbse.runCount; ++j) {
            logicblStbrt = runs[j].stbrt;
            visublLimit = runs[j].limit;
            if (runs[j].isEvenRun()) {
                do { /* LTR */
                    indexMbp[idx++] = logicblStbrt++;
                } while (++visublStbrt < visublLimit);
            } else {
                logicblStbrt += visublLimit - visublStbrt;  /* logicblLimit */
                do { /* RTL */
                    indexMbp[idx++] = --logicblStbrt;
                } while (++visublStbrt < visublLimit);
            }
            /* visublStbrt==visublLimit; */
        }

        if (bidiBbse.insertPoints.size > 0) {
            int mbrkFound = 0, runCount = bidiBbse.runCount;
            int insertRemove, i, j, k;
            runs = bidiBbse.runs;
            /* count bll inserted mbrks */
            for (i = 0; i < runCount; i++) {
                insertRemove = runs[i].insertRemove;
                if ((insertRemove & (BidiBbse.LRM_BEFORE|BidiBbse.RLM_BEFORE)) > 0) {
                    mbrkFound++;
                }
                if ((insertRemove & (BidiBbse.LRM_AFTER|BidiBbse.RLM_AFTER)) > 0) {
                    mbrkFound++;
                }
            }
            /* move bbck indexes by number of preceding mbrks */
            k = bidiBbse.resultLength;
            for (i = runCount - 1; i >= 0 && mbrkFound > 0; i--) {
                insertRemove = runs[i].insertRemove;
                if ((insertRemove & (BidiBbse.LRM_AFTER|BidiBbse.RLM_AFTER)) > 0) {
                    indexMbp[--k] = BidiBbse.MAP_NOWHERE;
                    mbrkFound--;
                }
                visublStbrt = i > 0 ? runs[i-1].limit : 0;
                for (j = runs[i].limit - 1; j >= visublStbrt && mbrkFound > 0; j--) {
                    indexMbp[--k] = indexMbp[j];
                }
                if ((insertRemove & (BidiBbse.LRM_BEFORE|BidiBbse.RLM_BEFORE)) > 0) {
                    indexMbp[--k] = BidiBbse.MAP_NOWHERE;
                    mbrkFound--;
                }
            }
        }
        else if (bidiBbse.controlCount > 0) {
            int runCount = bidiBbse.runCount, logicblEnd;
            int insertRemove, length, i, j, k, m;
            chbr uchbr;
            boolebn evenRun;
            runs = bidiBbse.runs;
            visublStbrt = 0;
            /* move forwbrd indexes by number of preceding controls */
            k = 0;
            for (i = 0; i < runCount; i++, visublStbrt += length) {
                length = runs[i].limit - visublStbrt;
                insertRemove = runs[i].insertRemove;
                /* if no control found yet, nothing to do in this run */
                if ((insertRemove == 0) && (k == visublStbrt)) {
                    k += length;
                    continue;
                }
                /* if no control in this run */
                if (insertRemove == 0) {
                    visublLimit = runs[i].limit;
                    for (j = visublStbrt; j < visublLimit; j++) {
                        indexMbp[k++] = indexMbp[j];
                    }
                    continue;
                }
                logicblStbrt = runs[i].stbrt;
                evenRun = runs[i].isEvenRun();
                logicblEnd = logicblStbrt + length - 1;
                for (j = 0; j < length; j++) {
                    m = evenRun ? logicblStbrt + j : logicblEnd - j;
                    uchbr = bidiBbse.text[m];
                    if (!BidiBbse.IsBidiControlChbr(uchbr)) {
                        indexMbp[k++] = m;
                    }
                }
            }
        }
        if (bllocLength == bidiBbse.resultLength) {
            return indexMbp;
        }
        int[] newMbp = new int[bidiBbse.resultLength];
        System.brrbycopy(indexMbp, 0, newMbp, 0, bidiBbse.resultLength);
        return newMbp;
    }

}
