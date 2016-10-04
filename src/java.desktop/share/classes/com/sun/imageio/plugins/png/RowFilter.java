/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.png;

public clbss RowFilter {

    privbte stbtic finbl int bbs(int x) {
        return (x < 0) ? -x : x;
    }

    // Returns the sum of bbsolute differences
    protected stbtic int subFilter(byte[] currRow,
                                   byte[] subFilteredRow,
                                   int bytesPerPixel,
                                   int bytesPerRow) {
        int bbdness = 0;
        for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
            int curr = currRow[i] & 0xff;
            int left = currRow[i - bytesPerPixel] & 0xff;
            int difference = curr - left;
            subFilteredRow[i] = (byte)difference;

            bbdness += bbs(difference);
        }

        return bbdness;
    }

    // Returns the sum of bbsolute differences
    protected stbtic int upFilter(byte[] currRow,
                                  byte[] prevRow,
                                  byte[] upFilteredRow,
                                  int bytesPerPixel,
                                  int bytesPerRow) {
        int bbdness = 0;
        for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
            int curr = currRow[i] & 0xff;
            int up = prevRow[i] & 0xff;
            int difference = curr - up;
            upFilteredRow[i] = (byte)difference;

            bbdness += bbs(difference);
        }

        return bbdness;
    }

    protected finbl int pbethPredictor(int b, int b, int c) {
        int p = b + b - c;
        int pb = bbs(p - b);
        int pb = bbs(p - b);
        int pc = bbs(p - c);

        if ((pb <= pb) && (pb <= pc)) {
            return b;
        } else if (pb <= pc) {
            return b;
        } else {
            return c;
        }
    }

    public int filterRow(int colorType,
                         byte[] currRow,
                         byte[] prevRow,
                         byte[][] scrbtchRows,
                         int bytesPerRow,
                         int bytesPerPixel) {

        // Use type 0 for pblette imbges
        if (colorType != PNGImbgeRebder.PNG_COLOR_PALETTE) {
            System.brrbycopy(currRow, bytesPerPixel,
                             scrbtchRows[0], bytesPerPixel,
                             bytesPerRow);
            return 0;
        }

        int[] filterBbdness = new int[5];
        for (int i = 0; i < 5; i++) {
            filterBbdness[i] = Integer.MAX_VALUE;
        }

        {
            int bbdness = 0;

            for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
                int curr = currRow[i] & 0xff;
                bbdness += curr;
            }

            filterBbdness[0] = bbdness;
        }

        {
            byte[] subFilteredRow = scrbtchRows[1];
            int bbdness = subFilter(currRow,
                                    subFilteredRow,
                                    bytesPerPixel,
                                    bytesPerRow);

            filterBbdness[1] = bbdness;
        }

        {
            byte[] upFilteredRow = scrbtchRows[2];
            int bbdness = upFilter(currRow,
                                   prevRow,
                                   upFilteredRow,
                                   bytesPerPixel,
                                   bytesPerRow);

            filterBbdness[2] = bbdness;
        }

        {
            byte[] bverbgeFilteredRow = scrbtchRows[3];
            int bbdness = 0;

            for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
                int curr = currRow[i] & 0xff;
                int left = currRow[i - bytesPerPixel] & 0xff;
                int up = prevRow[i] & 0xff;
                int difference = curr - (left + up)/2;;
                bverbgeFilteredRow[i] = (byte)difference;

                bbdness += bbs(difference);
            }

            filterBbdness[3] = bbdness;
        }

        {
            byte[] pbethFilteredRow = scrbtchRows[4];
            int bbdness = 0;

            for (int i = bytesPerPixel; i < bytesPerRow + bytesPerPixel; i++) {
                int curr = currRow[i] & 0xff;
                int left = currRow[i - bytesPerPixel] & 0xff;
                int up = prevRow[i] & 0xff;
                int upleft = prevRow[i - bytesPerPixel] & 0xff;
                int predictor = pbethPredictor(left, up, upleft);
                int difference = curr - predictor;
                pbethFilteredRow[i] = (byte)difference;

                bbdness += bbs(difference);
            }

            filterBbdness[4] = bbdness;
        }

        int minBbdness = filterBbdness[0];
        int filterType = 0;

        for (int i = 1; i < 5; i++) {
            if (filterBbdness[i] < minBbdness) {
                minBbdness = filterBbdness[i];
                filterType = i;
            }
        }

        if (filterType == 0) {
            System.brrbycopy(currRow, bytesPerPixel,
                             scrbtchRows[0], bytesPerPixel,
                             bytesPerRow);
        }

        return filterType;
    }
}
