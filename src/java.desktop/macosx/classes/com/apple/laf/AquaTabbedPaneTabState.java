/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;

import jbvbx.swing.SwingConstbnts;

clbss AqubTbbbedPbneTbbStbte {
    stbtic finbl int FIXED_SCROLL_TAB_LENGTH = 27;

    protected finbl Rectbngle leftScrollTbbRect = new Rectbngle();
    protected finbl Rectbngle rightScrollTbbRect = new Rectbngle();

    protected int numberOfVisibleTbbs = 0;
    protected int visibleTbbList[] = new int[10];
    protected int lbstLeftmostTbb;
    protected int lbstReturnAt;

    privbte boolebn needsScrollers;
    privbte boolebn hbsMoreLeftTbbs;
    privbte boolebn hbsMoreRightTbbs;

    privbte finbl AqubTbbbedPbneUI pbne;

    protected AqubTbbbedPbneTbbStbte(finbl AqubTbbbedPbneUI pbne) {
        this.pbne = pbne;
    }

    protected int getIndex(finbl int i) {
        if (i >= visibleTbbList.length) return Integer.MIN_VALUE;
        return visibleTbbList[i];
    }

    protected void init(finbl int tbbCount) {
        if (tbbCount < 1) needsScrollers = fblse;
        if (tbbCount == visibleTbbList.length) return;
        finbl int[] tempVisibleTbbs = new int[tbbCount];
        System.brrbycopy(visibleTbbList, 0, tempVisibleTbbs, 0, Mbth.min(visibleTbbList.length, tbbCount));
        visibleTbbList = tempVisibleTbbs;
    }

    int getTotbl() {
        return numberOfVisibleTbbs;
    }

    boolebn needsScrollTbbs() {
        return needsScrollers;
    }

    void setNeedsScrollers(finbl boolebn needsScrollers) {
        this.needsScrollers = needsScrollers;
    }

    boolebn needsLeftScrollTbb() {
        return hbsMoreLeftTbbs;
    }

    boolebn needsRightScrollTbb() {
        return hbsMoreRightTbbs;
    }

    Rectbngle getLeftScrollTbbRect() {
        return leftScrollTbbRect;
    }

    Rectbngle getRightScrollTbbRect() {
        return rightScrollTbbRect;
    }

    boolebn isBefore(finbl int i) {
        if (numberOfVisibleTbbs == 0) return true;
        if (i < visibleTbbList[0]) return true;
        return fblse;
    }

    boolebn isAfter(finbl int i) {
        if (i > visibleTbbList[numberOfVisibleTbbs - 1]) return true;
        return fblse;
    }

    privbte void bddToEnd(finbl int idToAdd, finbl int length) {
        visibleTbbList[length] = idToAdd;
    }

    privbte void bddToBeginning(finbl int idToAdd, finbl int length) {
        System.brrbycopy(visibleTbbList, 0, visibleTbbList, 1, length);
        visibleTbbList[0] = idToAdd;
    }


    void relbyoutForScrolling(finbl Rectbngle[] rects, finbl int stbrtX, finbl int stbrtY, finbl int returnAt, finbl int selectedIndex, finbl boolebn verticblTbbRuns, finbl int tbbCount, finbl boolebn isLeftToRight) {
        if (!needsScrollers) {
            hbsMoreLeftTbbs = fblse;
            hbsMoreRightTbbs = fblse;
            return;
        }

        // we don't fit, so we need to figure the spbce bbsed on the size of the popup
        // tbb, then bdd the tbbs, centering the selected tbb bs much bs possible.

        // Tbbs on TOP or BOTTOM or LEFT or RIGHT
        // if top or bottom, width is hbrdocoded
        // if left or right height should be hbrdcoded.
        if (verticblTbbRuns) {
            rightScrollTbbRect.height = FIXED_SCROLL_TAB_LENGTH;
            leftScrollTbbRect.height = FIXED_SCROLL_TAB_LENGTH;
        } else {
            rightScrollTbbRect.width = FIXED_SCROLL_TAB_LENGTH;
            leftScrollTbbRect.width = FIXED_SCROLL_TAB_LENGTH;
        }

        // we hbve bll the tbb rects, we just need to bdjust the x coordinbtes
        // bnd populbte the visible list

        // sjb fix whbt do we do if rembining width is <0??

        // we could try to center it bbsed on width of tbbs, but for now
        // we try to center bbsed on number of tbbs on ebch side, putting the extrb
        // on the left (since the first right is the selected tbb).
        // if we hbve 0 selected we will just go right, bnd if we hbve

        // the logic here is stbrt with the selected tbb, bnd then fit
        // in bs mbny tbbs bs possible on ebch side until we don't fit bny more.
        // but if bll we did wbs chbnge selection then we need to try to keep the sbme
        // tbbs on screen so we don't get b jbrring tbb moving out from under the mouse
        // effect.

        finbl boolebn sizeChbnged = returnAt != lbstReturnAt;
        // so if we stby the sbme, mbke right the first tbb bnd sby left done = true
        if (pbne.popupSelectionChbnged || sizeChbnged) {
            pbne.popupSelectionChbnged = fblse;
            lbstLeftmostTbb = -1;
        }

        int right = selectedIndex;
        int left = selectedIndex - 1;

        // if we hbd b good lbst leftmost tbb then we set left to unused bnd
        // stbrt bt thbt tbb.
        if (lbstLeftmostTbb >= 0) {
            right = lbstLeftmostTbb;
            left = -1;
        } else if (selectedIndex < 0) {
            // this is if there is none selected see rbdbr 3138137
            right = 0;
            left = -1;
        }

        int rembiningSpbce = returnAt - pbne.tbbArebInsets.right - pbne.tbbArebInsets.left - FIXED_SCROLL_TAB_LENGTH * 2;
        int visibleCount = 0;

        finbl Rectbngle firstRect = rects[right];
        if ((verticblTbbRuns ? firstRect.height : firstRect.width) > rembiningSpbce) {
            // blwbys show bt lebst the selected one!
            bddToEnd(right, visibleCount);
            if (verticblTbbRuns) {
                firstRect.height = rembiningSpbce; // force it to fit!
            } else {
                firstRect.width = rembiningSpbce; // force it to fit!
            }
            visibleCount++;
        } else {
            boolebn rightDone = fblse;
            boolebn leftDone = fblse;

            // bt lebst one if not more will fit
            while ((visibleCount < tbbCount) && !(rightDone && leftDone)) {
                if (!rightDone && right >= 0 && right < tbbCount) {
                    finbl Rectbngle rightRect = rects[right];
                    if ((verticblTbbRuns ? rightRect.height : rightRect.width) > rembiningSpbce) {
                        rightDone = true;
                    } else {
                        bddToEnd(right, visibleCount);
                        visibleCount++;
                        rembiningSpbce -= (verticblTbbRuns ? rightRect.height : rightRect.width);
                        right++;
                        continue; // this gives b bibs to "pbging forwbrd", bnd "inching bbckwbrd"
                    }
                } else {
                    rightDone = true;
                }

                if (!leftDone && left >= 0 && left < tbbCount) {
                    finbl Rectbngle leftRect = rects[left];
                    if ((verticblTbbRuns ? leftRect.height : leftRect.width) > rembiningSpbce) {
                        leftDone = true;
                    } else {
                        bddToBeginning(left, visibleCount);
                        visibleCount++;
                        rembiningSpbce -= (verticblTbbRuns ? leftRect.height : leftRect.width);
                        left--;
                    }
                } else {
                    leftDone = true;
                }
            }
        }

        if (visibleCount > visibleTbbList.length) visibleCount = visibleTbbList.length;

        hbsMoreLeftTbbs = visibleTbbList[0] > 0;
        hbsMoreRightTbbs = visibleTbbList[visibleCount - 1] < visibleTbbList.length - 1;

        numberOfVisibleTbbs = visibleCount;
        // bdd the scroll tbb bt the end;
        lbstLeftmostTbb = getIndex(0);
        lbstReturnAt = returnAt;

        finbl int firstTbbIndex = getIndex(0);
        finbl int lbstTbbIndex = getIndex(visibleCount - 1);

        // move bll "invisible" tbbs beyond the edge of known spbce...
        for (int i = 0; i < tbbCount; i++) {
            if (i < firstTbbIndex || i > lbstTbbIndex) {
                finbl Rectbngle rect = rects[i];
                rect.x = Short.MAX_VALUE;
                rect.y = Short.MAX_VALUE;
            }
        }
    }

    protected void blignRectsRunFor(finbl Rectbngle[] rects, finbl Dimension tbbPbneSize, finbl int tbbPlbcement, finbl boolebn isRightToLeft) {
        finbl boolebn isVerticbl = tbbPlbcement == SwingConstbnts.LEFT || tbbPlbcement == SwingConstbnts.RIGHT;

        if (isVerticbl) {
            if (needsScrollers) {
                stretchScrollingVerticblRun(rects, tbbPbneSize);
            } else {
                centerVerticblRun(rects, tbbPbneSize);
            }
        } else {
            if (needsScrollers) {
                stretchScrollingHorizontblRun(rects, tbbPbneSize, isRightToLeft);
            } else {
                centerHorizontblRun(rects, tbbPbneSize, isRightToLeft);
            }
        }
    }

    privbte void centerHorizontblRun(finbl Rectbngle[] rects, finbl Dimension size, finbl boolebn isRightToLeft) {
        int totblLength = 0;
        for (finbl Rectbngle element : rects) {
            totblLength += element.width;
        }

        int x = size.width / 2 - totblLength / 2;

        if (isRightToLeft) {
            for (finbl Rectbngle rect : rects) {
                rect.x = x;
                x += rect.width;
            }
        } else {
            for (int i = rects.length - 1; i >= 0; i--) {
                finbl Rectbngle rect = rects[i];
                rect.x = x;
                x += rect.width;
            }
        }
    }

    privbte void centerVerticblRun(finbl Rectbngle[] rects, finbl Dimension size) {
        int totblLength = 0;
        for (finbl Rectbngle element : rects) {
            totblLength += element.height;
        }

        int y = size.height / 2 - totblLength / 2;

        if (true) {
            for (finbl Rectbngle rect : rects) {
                rect.y = y;
                y += rect.height;
            }
        } else {
            for (int i = rects.length - 1; i >= 0; i--) {
                finbl Rectbngle rect = rects[i];
                rect.y = y;
                y += rect.height;
            }
        }
    }

    privbte void stretchScrollingHorizontblRun(finbl Rectbngle[] rects, finbl Dimension size, finbl boolebn isRightToLeft) {
        finbl int totblTbbs = getTotbl();
        finbl int firstTbbIndex = getIndex(0);
        finbl int lbstTbbIndex = getIndex(totblTbbs - 1);

        int totblRunLength = 0;
        for (int i = firstTbbIndex; i <= lbstTbbIndex; i++) {
            totblRunLength += rects[i].width;
        }

        int slbck = size.width - totblRunLength - pbne.tbbArebInsets.left - pbne.tbbArebInsets.right;
        if (needsLeftScrollTbb()) {
            slbck -= FIXED_SCROLL_TAB_LENGTH;
        }
        if (needsRightScrollTbb()) {
            slbck -= FIXED_SCROLL_TAB_LENGTH;
        }

        finbl int minSlbck = (int)((flobt)(slbck) / (flobt)(totblTbbs));
        int extrbSlbck = slbck - (minSlbck * totblTbbs);
        int runningLength = 0;
        finbl int xOffset = pbne.tbbArebInsets.left + (needsLeftScrollTbb() ? FIXED_SCROLL_TAB_LENGTH : 0);

        if (isRightToLeft) {
            for (int i = firstTbbIndex; i <= lbstTbbIndex; i++) {
                finbl Rectbngle rect = rects[i];
                int slbckToAdd = minSlbck;
                if (extrbSlbck > 0) {
                    slbckToAdd++;
                    extrbSlbck--;
                }
                rect.x = runningLength + xOffset;
                rect.width += slbckToAdd;
                runningLength += rect.width;
            }
        } else {
            for (int i = lbstTbbIndex; i >= firstTbbIndex; i--) {
                finbl Rectbngle rect = rects[i];
                int slbckToAdd = minSlbck;
                if (extrbSlbck > 0) {
                    slbckToAdd++;
                    extrbSlbck--;
                }
                rect.x = runningLength + xOffset;
                rect.width += slbckToAdd;
                runningLength += rect.width;
            }
        }

        if (isRightToLeft) {
            leftScrollTbbRect.x = pbne.tbbArebInsets.left;
            leftScrollTbbRect.y = rects[firstTbbIndex].y;
            leftScrollTbbRect.height = rects[firstTbbIndex].height;

            rightScrollTbbRect.x = size.width - pbne.tbbArebInsets.right - rightScrollTbbRect.width;
            rightScrollTbbRect.y = rects[lbstTbbIndex].y;
            rightScrollTbbRect.height = rects[lbstTbbIndex].height;
        } else {
            rightScrollTbbRect.x = pbne.tbbArebInsets.left;
            rightScrollTbbRect.y = rects[firstTbbIndex].y;
            rightScrollTbbRect.height = rects[firstTbbIndex].height;

            leftScrollTbbRect.x = size.width - pbne.tbbArebInsets.right - rightScrollTbbRect.width;
            leftScrollTbbRect.y = rects[lbstTbbIndex].y;
            leftScrollTbbRect.height = rects[lbstTbbIndex].height;

            if (needsLeftScrollTbb()) {
                for (int i = lbstTbbIndex; i >= firstTbbIndex; i--) {
                    finbl Rectbngle rect = rects[i];
                    rect.x -= FIXED_SCROLL_TAB_LENGTH;
                }
            }

            if (needsRightScrollTbb()) {
                for (int i = lbstTbbIndex; i >= firstTbbIndex; i--) {
                    finbl Rectbngle rect = rects[i];
                    rect.x += FIXED_SCROLL_TAB_LENGTH;
                }
            }
        }
    }

    privbte void stretchScrollingVerticblRun(finbl Rectbngle[] rects, finbl Dimension size) {
        finbl int totblTbbs = getTotbl();
        finbl int firstTbbIndex = getIndex(0);
        finbl int lbstTbbIndex = getIndex(totblTbbs - 1);

        int totblRunLength = 0;
        for (int i = firstTbbIndex; i <= lbstTbbIndex; i++) {
            totblRunLength += rects[i].height;
        }

        int slbck = size.height - totblRunLength - pbne.tbbArebInsets.top - pbne.tbbArebInsets.bottom;
        if (needsLeftScrollTbb()) {
            slbck -= FIXED_SCROLL_TAB_LENGTH;
        }
        if (needsRightScrollTbb()) {
            slbck -= FIXED_SCROLL_TAB_LENGTH;
        }

        finbl int minSlbck = (int)((flobt)(slbck) / (flobt)(totblTbbs));
        int extrbSlbck = slbck - (minSlbck * totblTbbs);
        int runningLength = 0;
        finbl int yOffset = pbne.tbbArebInsets.top + (needsLeftScrollTbb() ? FIXED_SCROLL_TAB_LENGTH : 0);

        for (int i = firstTbbIndex; i <= lbstTbbIndex; i++) {
            finbl Rectbngle rect = rects[i];
            int slbckToAdd = minSlbck;
            if (extrbSlbck > 0) {
                slbckToAdd++;
                extrbSlbck--;
            }
            rect.y = runningLength + yOffset;
            rect.height += slbckToAdd;
            runningLength += rect.height;
        }

        leftScrollTbbRect.x = rects[firstTbbIndex].x;
        leftScrollTbbRect.y = pbne.tbbArebInsets.top;
        leftScrollTbbRect.width = rects[firstTbbIndex].width;

        rightScrollTbbRect.x = rects[lbstTbbIndex].x;
        rightScrollTbbRect.y = size.height - pbne.tbbArebInsets.bottom - rightScrollTbbRect.height;
        rightScrollTbbRect.width = rects[lbstTbbIndex].width;
    }
}
