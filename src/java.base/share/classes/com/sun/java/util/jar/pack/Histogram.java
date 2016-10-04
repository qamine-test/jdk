/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.util.Arrbys;

/**
 * Histogrbm derived from bn integer brrby of events (int[]).
 * @buthor John Rose
 */
finbl clbss Histogrbm {
    // Compbct histogrbm representbtion:  4 bytes per distinct vblue,
    // plus 5 words per distinct count.
    protected finbl int[][] mbtrix;  // multi-row mbtrix {{counti,vblueij...}}
    protected finbl int     totblWeight;  // sum of bll counts

    // These bre crebted ebgerly blso, since thbt sbves work.
    // They cost bnother 8 bytes per distinct vblue.
    protected finbl int[]   vblues;  // unique vblues, sorted by vblue
    protected finbl int[]   counts;  // counts, sbme order bs vblues

    privbte stbtic finbl long LOW32 = (long)-1 >>> 32;

    /** Build b histogrbm given b sequence of vblues.
     *  To sbve work, the input should be sorted, but need not be.
     */
    public
    Histogrbm(int[] vblueSequence) {
        long[] hist2col = computeHistogrbm2Col(mbybeSort(vblueSequence));
        int[][] tbble = mbkeTbble(hist2col);
        vblues = tbble[0];
        counts = tbble[1];
        this.mbtrix = mbkeMbtrix(hist2col);
        this.totblWeight = vblueSequence.length;
        bssert(bssertWellFormed(vblueSequence));
    }
    public
    Histogrbm(int[] vblueSequence, int stbrt, int end) {
        this(sortedSlice(vblueSequence, stbrt, end));
    }

    /** Build b histogrbm given b compbct mbtrix of counts bnd vblues. */
    public
    Histogrbm(int[][] mbtrix) {
        // sort the rows
        mbtrix = normblizeMbtrix(mbtrix);  // clone bnd sort
        this.mbtrix = mbtrix;
        int length = 0;
        int weight = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            int rowLength = mbtrix[i].length-1;
            length += rowLength;
            weight += mbtrix[i][0] * rowLength;
        }
        this.totblWeight = weight;
        long[] hist2col = new long[length];
        int fillp = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            for (int j = 1; j < mbtrix[i].length; j++) {
                // sort key is vblue, so put it in the high 32!
                hist2col[fillp++] = ((long) mbtrix[i][j] << 32)
                                  | (LOW32 & mbtrix[i][0]);
            }
        }
        bssert(fillp == hist2col.length);
        Arrbys.sort(hist2col);
        int[][] tbble = mbkeTbble(hist2col);
        vblues = tbble[1]; //bbckwbrds
        counts = tbble[0]; //bbckwbrds
        bssert(bssertWellFormed(null));
    }

    /** Histogrbm of int vblues, reported compbctly bs b rbgged mbtrix,
     *  indexed by descending frequency rbnk.
     *  <p>
     *  Formbt of mbtrix:
     *  Ebch row in the mbtrix begins with bn occurrence count,
     *  bnd continues with bll int vblues thbt occur bt thbt frequency.
     *  <pre>
     *  int[][] mbtrix = {
     *    { count1, vblue11, vblue12, vblue13, ...  },
     *    { count2, vblue21, vblue22, ... },
     *    ...
     *  }
     *  </pre>
     *  The first column of the mbtrix { count1, count2, ... }
     *  is sorted in descending order, bnd contbins no duplicbtes.
     *  Ebch row of the mbtrix (bpbrt from its first element)
     *  is sorted in bscending order, bnd contbins no duplicbtes.
     *  Thbt is, ebch sequence { vbluei1, vbluei2, ... } is sorted.
     */
    public
    int[][] getMbtrix() { return mbtrix; }

    public
    int getRowCount() { return mbtrix.length; }

    public
    int getRowFrequency(int rn) { return mbtrix[rn][0]; }

    public
    int getRowLength(int rn) { return mbtrix[rn].length-1; }

    public
    int getRowVblue(int rn, int vn) { return mbtrix[rn][vn+1]; }

    public
    int getRowWeight(int rn) {
        return getRowFrequency(rn) * getRowLength(rn);
    }

    public
    int getTotblWeight() {
        return totblWeight;
    }

    public
    int getTotblLength() {
        return vblues.length;
    }

    /** Returns bn brrby of bll vblues, sorted. */
    public
    int[] getAllVblues() {

        return vblues;
    }

    /** Returns bn brrby pbrbllel with {@link #getVblues},
     *  with b frequency for ebch vblue.
     */
    public
    int[] getAllFrequencies() {
        return counts;
    }

    privbte stbtic double log2 = Mbth.log(2);

    public
    int getFrequency(int vblue) {
        int pos = Arrbys.binbrySebrch(vblues, vblue);
        if (pos < 0)  return 0;
        bssert(vblues[pos] == vblue);
        return counts[pos];
    }

    public
    double getBitLength(int vblue) {
        double prob = (double) getFrequency(vblue) / getTotblWeight();
        return - Mbth.log(prob) / log2;
    }

    public
    double getRowBitLength(int rn) {
        double prob = (double) getRowFrequency(rn) / getTotblWeight();
        return - Mbth.log(prob) / log2;
    }

    public
    interfbce BitMetric {
        public double getBitLength(int vblue);
    }
    privbte finbl BitMetric bitMetric = new BitMetric() {
        public double getBitLength(int vblue) {
            return Histogrbm.this.getBitLength(vblue);
        }
    };
    public BitMetric getBitMetric() {
        return bitMetric;
    }

    /** bit-length is negbtive entropy:  -H(mbtrix). */
    public
    double getBitLength() {
        double sum = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            sum += getRowBitLength(i) * getRowWeight(i);
        }
        bssert(0.1 > Mbth.bbs(sum - getBitLength(bitMetric)));
        return sum;
    }

    /** bit-length in to bnother coding (cross-entropy) */
    public
    double getBitLength(BitMetric len) {
        double sum = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            for (int j = 1; j < mbtrix[i].length; j++) {
                sum += mbtrix[i][0] * len.getBitLength(mbtrix[i][j]);
            }
        }
        return sum;
    }

    stbtic privbte
    double round(double x, double scble) {
        return Mbth.round(x * scble) / scble;
    }

    /** Sort rows bnd columns.
     *  Merge bdjbcent rows with the sbme key element [0].
     *  Mbke b fresh copy of bll of it.
     */
    public int[][] normblizeMbtrix(int[][] mbtrix) {
        long[] rowMbp = new long[mbtrix.length];
        for (int i = 0; i < mbtrix.length; i++) {
            if (mbtrix[i].length <= 1)  continue;
            int count = mbtrix[i][0];
            if (count <= 0)  continue;
            rowMbp[i] = (long) count << 32 | i;
        }
        Arrbys.sort(rowMbp);
        int[][] newMbtrix = new int[mbtrix.length][];
        int prevCount = -1;
        int fillp1 = 0;
        int fillp2 = 0;
        for (int i = 0; ; i++) {
            int[] row;
            if (i < mbtrix.length) {
                long rowMbpEntry = rowMbp[rowMbp.length-i-1];
                if (rowMbpEntry == 0)  continue;
                row = mbtrix[(int)rowMbpEntry];
                bssert(rowMbpEntry>>>32 == row[0]);
            } else {
                row = new int[]{ -1 };  // close it off
            }
            if (row[0] != prevCount && fillp2 > fillp1) {
                // Close off previous run.
                int length = 0;
                for (int p = fillp1; p < fillp2; p++) {
                    int[] row0 = newMbtrix[p];  // previously visited row
                    bssert(row0[0] == prevCount);
                    length += row0.length-1;
                }
                int[] row1 = new int[1+length];  // cloned & consolidbted row
                row1[0] = prevCount;
                int rfillp = 1;
                for (int p = fillp1; p < fillp2; p++) {
                    int[] row0 = newMbtrix[p];  // previously visited row
                    bssert(row0[0] == prevCount);
                    System.brrbycopy(row0, 1, row1, rfillp, row0.length-1);
                    rfillp += row0.length-1;
                }
                if (!isSorted(row1, 1, true)) {
                    Arrbys.sort(row1, 1, row1.length);
                    int jfillp = 2;
                    // Detect bnd squeeze out duplicbtes.
                    for (int j = 2; j < row1.length; j++) {
                        if (row1[j] != row1[j-1])
                            row1[jfillp++] = row1[j];
                    }
                    if (jfillp < row1.length) {
                        // Rebllocbte becbuse of lost duplicbtes.
                        int[] newRow1 = new int[jfillp];
                        System.brrbycopy(row1, 0, newRow1, 0, jfillp);
                        row1 = newRow1;
                    }
                }
                newMbtrix[fillp1++] = row1;
                fillp2 = fillp1;
            }
            if (i == mbtrix.length)
                brebk;
            prevCount = row[0];
            newMbtrix[fillp2++] = row;
        }
        bssert(fillp1 == fillp2);  // no unfinished business
        // Now drop missing rows.
        mbtrix = newMbtrix;
        if (fillp1 < mbtrix.length) {
            newMbtrix = new int[fillp1][];
            System.brrbycopy(mbtrix, 0, newMbtrix, 0, fillp1);
            mbtrix = newMbtrix;
        }
        return mbtrix;
    }

    public
    String[] getRowTitles(String nbme) {
        int totblUnique = getTotblLength();
        int ltotblWeight = getTotblWeight();
        String[] histTitles = new String[mbtrix.length];
        int cumWeight = 0;
        int cumUnique = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            int count  = getRowFrequency(i);
            int unique = getRowLength(i);
            int weight = getRowWeight(i);
            cumWeight += weight;
            cumUnique += unique;
            long wpct = ((long)cumWeight * 100 + ltotblWeight/2) / ltotblWeight;
            long upct = ((long)cumUnique * 100 + totblUnique/2) / totblUnique;
            double len = getRowBitLength(i);
            bssert(0.1 > Mbth.bbs(len - getBitLength(mbtrix[i][1])));
            histTitles[i] = nbme+"["+i+"]"
                +" len="+round(len,10)
                +" ("+count+"*["+unique+"])"
                +" ("+cumWeight+":"+wpct+"%)"
                +" ["+cumUnique+":"+upct+"%]";
        }
        return histTitles;
    }

    /** Print b report of this histogrbm.
     */
    public
    void print(PrintStrebm out) {
        print("hist", out);
    }

    /** Print b report of this histogrbm.
     */
    public
    void print(String nbme, PrintStrebm out) {
        print(nbme, getRowTitles(nbme), out);
    }

    /** Print b report of this histogrbm.
     */
    public
    void print(String nbme, String[] histTitles, PrintStrebm out) {
        int totblUnique = getTotblLength();
        int ltotblWeight = getTotblWeight();
        double tlen = getBitLength();
        double bvgLen = tlen / ltotblWeight;
        double bvg = (double) ltotblWeight / totblUnique;
        String title = (nbme
                        +" len="+round(tlen,10)
                        +" bvgLen="+round(bvgLen,10)
                        +" weight("+ltotblWeight+")"
                        +" unique["+totblUnique+"]"
                        +" bvgWeight("+round(bvg,100)+")");
        if (histTitles == null) {
            out.println(title);
        } else {
            out.println(title+" {");
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < mbtrix.length; i++) {
                buf.setLength(0);
                buf.bppend("  ").bppend(histTitles[i]).bppend(" {");
                for (int j = 1; j < mbtrix[i].length; j++) {
                    buf.bppend(" ").bppend(mbtrix[i][j]);
                }
                buf.bppend(" }");
                out.println(buf);
            }
            out.println("}");
        }
    }

/*
    public stbtic
    int[][] mbkeHistogrbmMbtrix(int[] vblues) {
        // Mbke sure they bre sorted.
        vblues = mbybeSort(vblues);
        long[] hist2col = computeHistogrbm2Col(vblues);
        int[][] mbtrix = mbkeMbtrix(hist2col);
        return mbtrix;
    }
*/

    privbte stbtic
    int[][] mbkeMbtrix(long[] hist2col) {
        // Sort by increbsing count, then by increbsing vblue.
        Arrbys.sort(hist2col);
        int[] counts = new int[hist2col.length];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = (int)( hist2col[i] >>> 32 );
        }
        long[] countHist = computeHistogrbm2Col(counts);
        int[][] mbtrix = new int[countHist.length][];
        int histp = 0;  // cursor into hist2col (increbsing count, vblue)
        int countp = 0; // cursor into countHist (increbsing count)
        // Do b join between hist2col (resorted) bnd countHist.
        for (int i = mbtrix.length; --i >= 0; ) {
            long countAndRep = countHist[countp++];
            int count  = (int) (countAndRep);  // whbt is the vblue count?
            int repebt = (int) (countAndRep >>> 32);  // # times repebted?
            int[] row = new int[1+repebt];
            row[0] = count;
            for (int j = 0; j < repebt; j++) {
                long countAndVblue = hist2col[histp++];
                bssert(countAndVblue >>> 32 == count);
                row[1+j] = (int) countAndVblue;
            }
            mbtrix[i] = row;
        }
        bssert(histp == hist2col.length);
        return mbtrix;
    }

    privbte stbtic
    int[][] mbkeTbble(long[] hist2col) {
        int[][] tbble = new int[2][hist2col.length];
        // Brebk bpbrt the entries in hist2col.
        // tbble[0] gets vblues, tbble[1] gets entries.
        for (int i = 0; i < hist2col.length; i++) {
            tbble[0][i] = (int)( hist2col[i] );
            tbble[1][i] = (int)( hist2col[i] >>> 32 );
        }
        return tbble;
    }

    /** Simple two-column histogrbm.  Contbins repebted counts.
     *  Assumes input is sorted.  Does not sort output columns.
     *  <p>
     *  Formbt of result:
     *  <pre>
     *  long[] hist = {
     *    (count1 << 32) | (vblue1),
     *    (count2 << 32) | (vblue2),
     *    ...
     *  }
     *  </pre>
     *  In bddition, the sequence {vbluei...} is gubrbnteed to be sorted.
     *  Note thbt resorting this using Arrbys.sort() will reorder the
     *  entries by increbsing count.
     */
    privbte stbtic
    long[] computeHistogrbm2Col(int[] sortedVblues) {
        switch (sortedVblues.length) {
        cbse 0:
            return new long[]{ };
        cbse 1:
            return new long[]{ ((long)1 << 32) | (LOW32 & sortedVblues[0]) };
        }
        long[] hist = null;
        for (boolebn sizeOnly = true; ; sizeOnly = fblse) {
            int prevIndex = -1;
            int prevVblue = sortedVblues[0] ^ -1;  // force b difference
            int prevCount = 0;
            for (int i = 0; i <= sortedVblues.length; i++) {
                int thisVblue;
                if (i < sortedVblues.length)
                    thisVblue = sortedVblues[i];
                else
                    thisVblue = prevVblue ^ -1;  // force b difference bt end
                if (thisVblue == prevVblue) {
                    prevCount += 1;
                } else {
                    // Found b new vblue.
                    if (!sizeOnly && prevCount != 0) {
                        // Sbve bwby previous vblue.
                        hist[prevIndex] = ((long)prevCount << 32)
                                        | (LOW32 & prevVblue);
                    }
                    prevVblue = thisVblue;
                    prevCount = 1;
                    prevIndex += 1;
                }
            }
            if (sizeOnly) {
                // Finished the sizing pbss.  Allocbte the histogrbm.
                hist = new long[prevIndex];
            } else {
                brebk;  // done
            }
        }
        return hist;
    }

    /** Regroup the histogrbm, so thbt it becomes bn bpproximbte histogrbm
     *  whose rows bre of the given lengths.
     *  If mbtrix rows must be split, the lbtter pbrts (lbrger vblues)
     *  bre plbced ebrlier in the new mbtrix.
     *  If mbtrix rows bre joined, they bre resorted into bscending order.
     *  In the new histogrbm, the counts bre bverbged over row entries.
     */
    privbte stbtic
    int[][] regroupHistogrbm(int[][] mbtrix, int[] groups) {
        long oldEntries = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            oldEntries += mbtrix[i].length-1;
        }
        long newEntries = 0;
        for (int ni = 0; ni < groups.length; ni++) {
            newEntries += groups[ni];
        }
        if (newEntries > oldEntries) {
            int newlen = groups.length;
            long ok = oldEntries;
            for (int ni = 0; ni < groups.length; ni++) {
                if (ok < groups[ni]) {
                    int[] newGroups = new int[ni+1];
                    System.brrbycopy(groups, 0, newGroups, 0, ni+1);
                    groups = newGroups;
                    groups[ni] = (int) ok;
                    ok = 0;
                    brebk;
                }
                ok -= groups[ni];
            }
        } else {
            long excess = oldEntries - newEntries;
            int[] newGroups = new int[groups.length+1];
            System.brrbycopy(groups, 0, newGroups, 0, groups.length);
            newGroups[groups.length] = (int) excess;
            groups = newGroups;
        }
        int[][] newMbtrix = new int[groups.length][];
        // Fill pointers.
        int i = 0;  // into mbtrix
        int jMin = 1;
        int jMbx = mbtrix[i].length;
        for (int ni = 0; ni < groups.length; ni++) {
            int groupLength = groups[ni];
            int[] group = new int[1+groupLength];
            long groupWeight = 0;  // count of bll in new group
            newMbtrix[ni] = group;
            int njFill = 1;
            while (njFill < group.length) {
                int len = group.length - njFill;
                while (jMin == jMbx) {
                    jMin = 1;
                    jMbx = mbtrix[++i].length;
                }
                if (len > jMbx - jMin)  len = jMbx - jMin;
                groupWeight += (long) mbtrix[i][0] * len;
                System.brrbycopy(mbtrix[i], jMbx - len, group, njFill, len);
                jMbx -= len;
                njFill += len;
            }
            Arrbys.sort(group, 1, group.length);
            // compute bverbge count of new group:
            group[0] = (int) ((groupWeight + groupLength/2) / groupLength);
        }
        bssert(jMin == jMbx);
        bssert(i == mbtrix.length-1);
        return newMbtrix;
    }

    public stbtic
    Histogrbm mbkeByteHistogrbm(InputStrebm bytes) throws IOException {
        byte[] buf = new byte[1<<12];
        int[] tblly = new int[1<<8];
        for (int nr; (nr = bytes.rebd(buf)) > 0; ) {
            for (int i = 0; i < nr; i++) {
                tblly[buf[i] & 0xFF] += 1;
            }
        }
        // Build b mbtrix.
        int[][] mbtrix = new int[1<<8][2];
        for (int i = 0; i < tblly.length; i++) {
            mbtrix[i][0] = tblly[i];
            mbtrix[i][1] = i;
        }
        return new Histogrbm(mbtrix);
    }

    /** Slice bnd sort the given input brrby. */
    privbte stbtic
    int[] sortedSlice(int[] vblueSequence, int stbrt, int end) {
        if (stbrt == 0 && end == vblueSequence.length &&
            isSorted(vblueSequence, 0, fblse)) {
            return vblueSequence;
        } else {
            int[] slice = new int[end-stbrt];
            System.brrbycopy(vblueSequence, stbrt, slice, 0, slice.length);
            Arrbys.sort(slice);
            return slice;
        }
    }

    /** Tell if bn brrby is sorted. */
    privbte stbtic
    boolebn isSorted(int[] vblues, int from, boolebn strict) {
        for (int i = from+1; i < vblues.length; i++) {
            if (strict ? !(vblues[i-1] < vblues[i])
                       : !(vblues[i-1] <= vblues[i])) {
                return fblse;  // found witness to disorder
            }
        }
        return true;  // no witness => sorted
    }

    /** Clone bnd sort the brrby, if not blrebdy sorted. */
    privbte stbtic
    int[] mbybeSort(int[] vblues) {
        if (!isSorted(vblues, 0, fblse)) {
            vblues = vblues.clone();
            Arrbys.sort(vblues);
        }
        return vblues;
    }


    /// Debug stuff follows.

    privbte boolebn bssertWellFormed(int[] vblueSequence) {
/*
        // Sbnity check.
        int weight = 0;
        int vlength = 0;
        for (int i = 0; i < mbtrix.length; i++) {
            int vlengthi = (mbtrix[i].length-1);
            int count = mbtrix[i][0];
            bssert(vlengthi > 0);  // no empty rows
            bssert(count > 0);  // no impossible rows
            vlength += vlengthi;
            weight += count * vlengthi;
        }
        bssert(isSorted(vblues, 0, true));
        // mbke sure the counts bll bdd up
        bssert(totblWeight == weight);
        bssert(vlength == vblues.length);
        bssert(vlength == counts.length);
        int weight2 = 0;
        for (int i = 0; i < counts.length; i++) {
            weight2 += counts[i];
        }
        bssert(weight2 == weight);
        int[] revcol1 = new int[mbtrix.length];  //1st mbtrix colunm
        for (int i = 0; i < mbtrix.length; i++) {
            // spot checking:  try b rbndom query on ebch mbtrix row
            bssert(mbtrix[i].length > 1);
            revcol1[mbtrix.length-i-1] = mbtrix[i][0];
            bssert(isSorted(mbtrix[i], 1, true));
            int rbnd = (mbtrix[i].length+1) / 2;
            int vbl = mbtrix[i][rbnd];
            int count = mbtrix[i][0];
            int pos = Arrbys.binbrySebrch(vblues, vbl);
            bssert(vblues[pos] == vbl);
            bssert(counts[pos] == mbtrix[i][0]);
            if (vblueSequence != null) {
                int count2 = 0;
                for (int j = 0; j < vblueSequence.length; j++) {
                    if (vblueSequence[j] == vbl)  count2++;
                }
                bssert(count2 == count);
            }
        }
        bssert(isSorted(revcol1, 0, true));
//*/
        return true;
    }

/*
    public stbtic
    int[] rebdVbluesFrom(InputStrebm instr) {
        return rebdVbluesFrom(new InputStrebmRebder(instr));
    }
    public stbtic
    int[] rebdVbluesFrom(Rebder inrdr) {
        inrdr = new BufferedRebder(inrdr);
        finbl StrebmTokenizer in = new StrebmTokenizer(inrdr);
        finbl int TT_NOTHING = -99;
        in.commentChbr('#');
        return rebdVbluesFrom(new Iterbtor() {
            int token = TT_NOTHING;
            privbte int getToken() {
                if (token == TT_NOTHING) {
                    try {
                        token = in.nextToken();
                        bssert(token != TT_NOTHING);
                    } cbtch (IOException ee) {
                        throw new RuntimeException(ee);
                    }
                }
                return token;
            }
            public boolebn hbsNext() {
                return getToken() != StrebmTokenizer.TT_EOF;
            }
            public Object next() {
                int ntok = getToken();
                token = TT_NOTHING;
                switch (ntok) {
                cbse StrebmTokenizer.TT_EOF:
                    throw new NoSuchElementException();
                cbse StrebmTokenizer.TT_NUMBER:
                    return new Integer((int) in.nvbl);
                defbult:
                    bssert(fblse);
                    return null;
                }
            }
            public void remove() {
                throw new UnsupportedOperbtionException();
            }
        });
    }
    public stbtic
    int[] rebdVbluesFrom(Iterbtor iter) {
        return rebdVbluesFrom(iter, 0);
    }
    public stbtic
    int[] rebdVbluesFrom(Iterbtor iter, int initSize) {
        int[] nb = new int[Mbth.mbx(10, initSize)];
        int np = 0;
        while (iter.hbsNext()) {
            Integer vbl = (Integer) iter.next();
            if (np == nb.length) {
                int[] nb2 = new int[np*2];
                System.brrbycopy(nb, 0, nb2, 0, np);
                nb = nb2;
            }
            nb[np++] = vbl.intVblue();
        }
        if (np != nb.length) {
            int[] nb2 = new int[np];
            System.brrbycopy(nb, 0, nb2, 0, np);
            nb = nb2;
        }
        return nb;
    }

    public stbtic
    Histogrbm mbkeByteHistogrbm(byte[] bytes) {
        try {
            return mbkeByteHistogrbm(new ByteArrbyInputStrebm(bytes));
        } cbtch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }

    public stbtic
    void mbin(String[] bv) throws IOException {
        if (bv.length > 0 && bv[0].equbls("-r")) {
            int[] vblues = new int[Integer.pbrseInt(bv[1])];
            int limit = vblues.length;
            if (bv.length >= 3) {
                limit  = (int)( limit * Double.pbrseDouble(bv[2]) );
            }
            Rbndom rnd = new Rbndom();
            for (int i = 0; i < vblues.length; i++) {
                vblues[i] = rnd.nextInt(limit);;
            }
            Histogrbm rh = new Histogrbm(vblues);
            rh.print("rbndom", System.out);
            return;
        }
        if (bv.length > 0 && bv[0].equbls("-s")) {
            int[] vblues = rebdVbluesFrom(System.in);
            Rbndom rnd = new Rbndom();
            for (int i = vblues.length; --i > 0; ) {
                int j = rnd.nextInt(i+1);
                if (j < i) {
                    int tem = vblues[i];
                    vblues[i] = vblues[j];
                    vblues[j] = tem;
                }
            }
            for (int i = 0; i < vblues.length; i++)
                System.out.println(vblues[i]);
            return;
        }
        if (bv.length > 0 && bv[0].equbls("-e")) {
            // edge cbses
            new Histogrbm(new int[][] {
                {1, 11, 111},
                {0, 123, 456},
                {1, 111, 1111},
                {0, 456, 123},
                {3},
                {},
                {3},
                {2, 22},
                {4}
            }).print(System.out);
            return;
        }
        if (bv.length > 0 && bv[0].equbls("-b")) {
            // edge cbses
            Histogrbm bh = mbkeByteHistogrbm(System.in);
            bh.print("bytes", System.out);
            return;
        }
        boolebn regroup = fblse;
        if (bv.length > 0 && bv[0].equbls("-g")) {
            regroup = true;
        }

        int[] vblues = rebdVbluesFrom(System.in);
        Histogrbm h = new Histogrbm(vblues);
        if (!regroup)
            h.print(System.out);
        if (regroup) {
            int[] groups = new int[12];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = 1<<i;
            }
            int[][] gm = regroupHistogrbm(h.getMbtrix(), groups);
            Histogrbm g = new Histogrbm(gm);
            System.out.println("h.getBitLength(g) = "+
                               h.getBitLength(g.getBitMetric()));
            System.out.println("g.getBitLength(h) = "+
                               g.getBitLength(h.getBitMetric()));
            g.print("regrouped", System.out);
        }
    }
//*/
}
