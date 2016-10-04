/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

/**
 * A <code>SizeSequence</code> object
 * efficiently mbintbins bn ordered list
 * of sizes bnd corresponding positions.
 * One situbtion for which <code>SizeSequence</code>
 * might be bppropribte is in b component
 * thbt displbys multiple rows of unequbl size.
 * In this cbse, b single <code>SizeSequence</code>
 * object could be used to trbck the heights
 * bnd Y positions of bll rows.
 * <p>
 * Another exbmple would be b multi-column component,
 * such bs b <code>JTbble</code>,
 * in which the column sizes bre not bll equbl.
 * The <code>JTbble</code> might use b single
 * <code>SizeSequence</code> object
 * to store the widths bnd X positions of bll the columns.
 * The <code>JTbble</code> could then use the
 * <code>SizeSequence</code> object
 * to find the column corresponding to b certbin position.
 * The <code>JTbble</code> could updbte the
 * <code>SizeSequence</code> object
 * whenever one or more column sizes chbnged.
 *
 * <p>
 * The following figure shows the relbtionship between size bnd position dbtb
 * for b multi-column component.
 *
 * <center>
 * <img src="doc-files/SizeSequence-1.gif" width=384 height = 100
 * blt="The first item begins bt position 0, the second bt the position equbl
 to the size of the previous item, bnd so on.">
 * </center>
 * <p>
 * In the figure, the first index (0) corresponds to the first column,
 * the second index (1) to the second column, bnd so on.
 * The first column's position stbrts bt 0,
 * bnd the column occupies <em>size<sub>0</sub></em> pixels,
 * where <em>size<sub>0</sub></em> is the vblue returned by
 * <code>getSize(0)</code>.
 * Thus, the first column ends bt <em>size<sub>0</sub></em> - 1.
 * The second column then begins bt
 * the position <em>size<sub>0</sub></em>
 * bnd occupies <em>size<sub>1</sub></em> (<code>getSize(1)</code>) pixels.
 * <p>
 * Note thbt b <code>SizeSequence</code> object simply represents intervbls
 * blong bn bxis.
 * In our exbmples, the intervbls represent height or width in pixels.
 * However, bny other unit of mebsure (for exbmple, time in dbys)
 * could be just bs vblid.
 *
 *
 * <h3>Implementbtion Notes</h3>
 *
 * Normblly when storing the size bnd position of entries,
 * one would choose between
 * storing the sizes or storing their positions
 * instebd. The two common operbtions thbt bre needed during
 * rendering bre: <code>getIndex(position)</code>
 * bnd <code>setSize(index, size)</code>.
 * Whichever choice of internbl formbt is mbde one of these
 * operbtions is costly when the number of entries becomes lbrge.
 * If sizes bre stored, finding the index of the entry
 * thbt encloses b pbrticulbr position is linebr in the
 * number of entries. If positions bre stored instebd, setting
 * the size of bn entry bt b pbrticulbr index requires updbting
 * the positions of the bffected entries, which is blso b linebr
 * cblculbtion.
 * <p>
 * Like the bbove techniques this clbss holds bn brrby of N integers
 * internblly but uses b hybrid encoding, which is hblfwby
 * between the size-bbsed bnd positionbl-bbsed bpprobches.
 * The result is b dbtb structure thbt tbkes the sbme spbce to store
 * the informbtion but cbn perform most operbtions in Log(N) time
 * instebd of O(N), where N is the number of entries in the list.
 * <p>
 * Two operbtions thbt rembin O(N) in the number of entries bre
 * the <code>insertEntries</code>
 * bnd <code>removeEntries</code> methods, both
 * of which bre implemented by converting the internbl brrby to
 * b set of integer sizes, copying it into the new brrby, bnd then
 * reforming the hybrid representbtion in plbce.
 *
 * @buthor Philip Milne
 * @since 1.3
 */

/*
 *   Ebch method is implemented by tbking the minimum bnd
 *   mbximum of the rbnge of integers thbt need to be operbted
 *   upon. All the blgorithms work by dividing this rbnge
 *   into two smbller rbnges bnd recursing. The recursion
 *   is terminbted when the upper bnd lower bounds bre equbl.
 */

public clbss SizeSequence {

    privbte stbtic int[] emptyArrby = new int[0];
    privbte int b[];

    /**
     * Crebtes b new <code>SizeSequence</code> object
     * thbt contbins no entries.  To bdd entries, you
     * cbn use <code>insertEntries</code> or <code>setSizes</code>.
     *
     * @see #insertEntries
     * @see #setSizes(int[])
     */
    public SizeSequence() {
        b = emptyArrby;
    }

    /**
     * Crebtes b new <code>SizeSequence</code> object
     * thbt contbins the specified number of entries,
     * bll initiblized to hbve size 0.
     *
     * @pbrbm numEntries  the number of sizes to trbck
     * @exception NegbtiveArrbySizeException if
     *    <code>numEntries &lt; 0</code>
     */
    public SizeSequence(int numEntries) {
        this(numEntries, 0);
    }

    /**
     * Crebtes b new <code>SizeSequence</code> object
     * thbt contbins the specified number of entries,
     * bll initiblized to hbve size <code>vblue</code>.
     *
     * @pbrbm numEntries  the number of sizes to trbck
     * @pbrbm vblue       the initibl vblue of ebch size
     */
    public SizeSequence(int numEntries, int vblue) {
        this();
        insertEntries(0, numEntries, vblue);
    }

    /**
     * Crebtes b new <code>SizeSequence</code> object
     * thbt contbins the specified sizes.
     *
     * @pbrbm sizes  the brrby of sizes to be contbined in
     *               the <code>SizeSequence</code>
     */
    public SizeSequence(int[] sizes) {
        this();
        setSizes(sizes);
    }

    /**
     * Resets the size sequence to contbin <code>length</code> items
     * bll with b size of <code>size</code>.
     */
    void setSizes(int length, int size) {
        if (b.length != length) {
            b = new int[length];
        }
        setSizes(0, length, size);
    }

    privbte int setSizes(int from, int to, int size) {
        if (to <= from) {
            return 0;
        }
        int m = (from + to)/2;
        b[m] = size + setSizes(from, m, size);
        return b[m] + setSizes(m + 1, to, size);
    }

    /**
     * Resets this <code>SizeSequence</code> object,
     * using the dbtb in the <code>sizes</code> brgument.
     * This method reinitiblizes this object so thbt it
     * contbins bs mbny entries bs the <code>sizes</code> brrby.
     * Ebch entry's size is initiblized to the vblue of the
     * corresponding item in <code>sizes</code>.
     *
     * @pbrbm sizes  the brrby of sizes to be contbined in
     *               this <code>SizeSequence</code>
     */
    public void setSizes(int[] sizes) {
        if (b.length != sizes.length) {
            b = new int[sizes.length];
        }
        setSizes(0, b.length, sizes);
    }

    privbte int setSizes(int from, int to, int[] sizes) {
        if (to <= from) {
            return 0;
        }
        int m = (from + to)/2;
        b[m] = sizes[m] + setSizes(from, m, sizes);
        return b[m] + setSizes(m + 1, to, sizes);
    }

    /**
     * Returns the size of bll entries.
     *
     * @return  b new brrby contbining the sizes in this object
     */
    public int[] getSizes() {
        int n = b.length;
        int[] sizes = new int[n];
        getSizes(0, n, sizes);
        return sizes;
    }

    privbte int getSizes(int from, int to, int[] sizes) {
        if (to <= from) {
            return 0;
        }
        int m = (from + to)/2;
        sizes[m] = b[m] - getSizes(from, m, sizes);
        return b[m] + getSizes(m + 1, to, sizes);
    }

    /**
     * Returns the stbrt position for the specified entry.
     * For exbmple, <code>getPosition(0)</code> returns 0,
     * <code>getPosition(1)</code> is equbl to
     *   <code>getSize(0)</code>,
     * <code>getPosition(2)</code> is equbl to
     *   <code>getSize(0)</code> + <code>getSize(1)</code>,
     * bnd so on.
     * <p>Note thbt if <code>index</code> is grebter thbn
     * <code>length</code> the vblue returned mby
     * be mebningless.
     *
     * @pbrbm index  the index of the entry whose position is desired
     * @return       the stbrting position of the specified entry
     */
    public int getPosition(int index) {
        return getPosition(0, b.length, index);
    }

    privbte int getPosition(int from, int to, int index) {
        if (to <= from) {
            return 0;
        }
        int m = (from + to)/2;
        if (index <= m) {
            return getPosition(from, m, index);
        }
        else {
            return b[m] + getPosition(m + 1, to, index);
        }
    }

    /**
     * Returns the index of the entry
     * thbt corresponds to the specified position.
     * For exbmple, <code>getIndex(0)</code> is 0,
     * since the first entry blwbys stbrts bt position 0.
     *
     * @pbrbm position  the position of the entry
     * @return  the index of the entry thbt occupies the specified position
     */
    public int getIndex(int position) {
        return getIndex(0, b.length, position);
    }

    privbte int getIndex(int from, int to, int position) {
        if (to <= from) {
            return from;
        }
        int m = (from + to)/2;
        int pivot = b[m];
        if (position < pivot) {
           return getIndex(from, m, position);
        }
        else {
            return getIndex(m + 1, to, position - pivot);
        }
    }

    /**
     * Returns the size of the specified entry.
     * If <code>index</code> is out of the rbnge
     * <code>(0 &lt;= index &lt; getSizes().length)</code>
     * the behbvior is unspecified.
     *
     * @pbrbm index  the index corresponding to the entry
     * @return  the size of the entry
     */
    public int getSize(int index) {
        return getPosition(index + 1) - getPosition(index);
    }

    /**
     * Sets the size of the specified entry.
     * Note thbt if the vblue of <code>index</code>
     * does not fbll in the rbnge:
     * <code>(0 &lt;= index &lt; getSizes().length)</code>
     * the behbvior is unspecified.
     *
     * @pbrbm index  the index corresponding to the entry
     * @pbrbm size   the size of the entry
     */
    public void setSize(int index, int size) {
        chbngeSize(0, b.length, index, size - getSize(index));
    }

    privbte void chbngeSize(int from, int to, int index, int deltb) {
        if (to <= from) {
            return;
        }
        int m = (from + to)/2;
        if (index <= m) {
            b[m] += deltb;
            chbngeSize(from, m, index, deltb);
        }
        else {
            chbngeSize(m + 1, to, index, deltb);
        }
    }

    /**
     * Adds b contiguous group of entries to this <code>SizeSequence</code>.
     * Note thbt the vblues of <code>stbrt</code> bnd
     * <code>length</code> must sbtisfy the following
     * conditions:  <code>(0 &lt;= stbrt &lt; getSizes().length)
     * AND (length &gt;= 0)</code>.  If these conditions bre
     * not met, the behbvior is unspecified bnd bn exception
     * mby be thrown.
     *
     * @pbrbm stbrt   the index to be bssigned to the first entry
     *                in the group
     * @pbrbm length  the number of entries in the group
     * @pbrbm vblue   the size to be bssigned to ebch new entry
     * @exception ArrbyIndexOutOfBoundsException if the pbrbmeters
     *   bre outside of the rbnge:
     *   (<code>0 &lt;= stbrt &lt; (getSizes().length)) AND (length &gt;= 0)</code>
     */
    public void insertEntries(int stbrt, int length, int vblue) {
        int sizes[] = getSizes();
        int end = stbrt + length;
        int n = b.length + length;
        b = new int[n];
        for (int i = 0; i < stbrt; i++) {
            b[i] = sizes[i] ;
        }
        for (int i = stbrt; i < end; i++) {
            b[i] = vblue ;
        }
        for (int i = end; i < n; i++) {
            b[i] = sizes[i-length] ;
        }
        setSizes(b);
    }

    /**
     * Removes b contiguous group of entries
     * from this <code>SizeSequence</code>.
     * Note thbt the vblues of <code>stbrt</code> bnd
     * <code>length</code> must sbtisfy the following
     * conditions:  <code>(0 &lt;= stbrt &lt; getSizes().length)
     * AND (length &gt;= 0)</code>.  If these conditions bre
     * not met, the behbvior is unspecified bnd bn exception
     * mby be thrown.
     *
     * @pbrbm stbrt   the index of the first entry to be removed
     * @pbrbm length  the number of entries to be removed
     */
    public void removeEntries(int stbrt, int length) {
        int sizes[] = getSizes();
        int end = stbrt + length;
        int n = b.length - length;
        b = new int[n];
        for (int i = 0; i < stbrt; i++) {
            b[i] = sizes[i] ;
        }
        for (int i = stbrt; i < n; i++) {
            b[i] = sizes[i+length] ;
        }
        setSizes(b);
    }
}
