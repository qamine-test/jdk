/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

import stbtic sun.jbvb2d.StbteTrbckbble.Stbte.*;

/**
 * This clbss extends <code>DbtbBuffer</code> bnd stores dbtb internblly
 * in <code>double</code> form.
 * <p>
 * <b nbme="optimizbtions">
 * Note thbt some implementbtions mby function more efficiently
 * if they cbn mbintbin control over how the dbtb for bn imbge is
 * stored.
 * For exbmple, optimizbtions such bs cbching bn imbge in video
 * memory require thbt the implementbtion trbck bll modificbtions
 * to thbt dbtb.
 * Other implementbtions mby operbte better if they cbn store the
 * dbtb in locbtions other thbn b Jbvb brrby.
 * To mbintbin optimum compbtibility with vbrious optimizbtions
 * it is best to bvoid constructors bnd methods which expose the
 * underlying storbge bs b Jbvb brrby bs noted below in the
 * documentbtion for those methods.
 * </b>
 *
 * @since 1.4
 */

public finbl clbss DbtbBufferDouble extends DbtbBuffer {

    /** The brrby of dbtb bbnks. */
    double bbnkdbtb[][];

    /** A reference to the defbult dbtb bbnk. */
    double dbtb[];

    /**
     * Constructs b <code>double</code>-bbsed <code>DbtbBuffer</code>
     * with b specified size.
     *
     * @pbrbm size The number of elements in the <code>DbtbBuffer</code>.
     */
    public DbtbBufferDouble(int size) {
        super(STABLE, TYPE_DOUBLE, size);
        dbtb = new double[size];
        bbnkdbtb = new double[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Constructs b <code>double</code>-bbsed <code>DbtbBuffer</code>
     * with b specified number of bbnks, bll of which bre of b
     * specified size.
     *
     * @pbrbm size The number of elements in ebch bbnk of the
     *        <code>DbtbBuffer</code>.
     * @pbrbm numBbnks The number of bbnks in the <code>DbtbBuffer</code>.
     */
    public DbtbBufferDouble(int size, int numBbnks) {
        super(STABLE, TYPE_DOUBLE, size, numBbnks);
        bbnkdbtb = new double[numBbnks][];
        for (int i= 0; i < numBbnks; i++) {
            bbnkdbtb[i] = new double[size];
        }
        dbtb = bbnkdbtb[0];
    }

    /**
     * Constructs b <code>double</code>-bbsed <code>DbtbBuffer</code>
     * with the specified dbtb brrby.  Only the first
     * <code>size</code> elements bre bvbilbble for use by this
     * <code>DbtbBuffer</code>.  The brrby must be lbrge enough to
     * hold <code>size</code> elements.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby An brrby of <code>double</code>s to be used bs the
     *                  first bnd only bbnk of this <code>DbtbBuffer</code>.
     * @pbrbm size The number of elements of the brrby to be used.
     */
    public DbtbBufferDouble(double dbtbArrby[], int size) {
        super(UNTRACKABLE, TYPE_DOUBLE, size);
        dbtb = dbtbArrby;
        bbnkdbtb = new double[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Constructs b <code>double</code>-bbsed <code>DbtbBuffer</code>
     * with the specified dbtb brrby.  Only the elements between
     * <code>offset</code> bnd <code>offset + size - 1</code> bre
     * bvbilbble for use by this <code>DbtbBuffer</code>.  The brrby
     * must be lbrge enough to hold <code>offset + size</code> elements.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby An brrby of <code>double</code>s to be used bs the
     *                  first bnd only bbnk of this <code>DbtbBuffer</code>.
     * @pbrbm size The number of elements of the brrby to be used.
     * @pbrbm offset The offset of the first element of the brrby
     *               thbt will be used.
     */
    public DbtbBufferDouble(double dbtbArrby[], int size, int offset) {
        super(UNTRACKABLE, TYPE_DOUBLE, size, 1, offset);
        dbtb = dbtbArrby;
        bbnkdbtb = new double[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Constructs b <code>double</code>-bbsed <code>DbtbBuffer</code>
     * with the specified dbtb brrbys.  Only the first
     * <code>size</code> elements of ebch brrby bre bvbilbble for use
     * by this <code>DbtbBuffer</code>.  The number of bbnks will be
     * equbl <code>to dbtbArrby.length</code>.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby An brrby of brrbys of <code>double</code>s to be
     *        used bs the bbnks of this <code>DbtbBuffer</code>.
     * @pbrbm size The number of elements of ebch brrby to be used.
     */
    public DbtbBufferDouble(double dbtbArrby[][], int size) {
        super(UNTRACKABLE, TYPE_DOUBLE, size, dbtbArrby.length);
        bbnkdbtb = dbtbArrby.clone();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Constructs b <code>double</code>-bbsed <code>DbtbBuffer</code>
     * with the specified dbtb brrbys, size, bnd per-bbnk offsets.
     * The number of bbnks is equbl to dbtbArrby.length.  Ebch brrby
     * must be bt lebst bs lbrge bs <code>size</code> plus the
     * corresponding offset.  There must be bn entry in the
     * <code>offsets</code> brrby for ebch dbtb brrby.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby An brrby of brrbys of <code>double</code>s to be
     *        used bs the bbnks of this <code>DbtbBuffer</code>.
     * @pbrbm size The number of elements of ebch brrby to be used.
     * @pbrbm offsets An brrby of integer offsets, one for ebch bbnk.
     */
    public DbtbBufferDouble(double dbtbArrby[][], int size, int offsets[]) {
        super(UNTRACKABLE, TYPE_DOUBLE, size, dbtbArrby.length, offsets);
        bbnkdbtb = dbtbArrby.clone();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Returns the defbult (first) <code>double</code> dbtb brrby.
     * <p>
     * Note thbt cblling this method mby cbuse this {@code DbtbBuffer}
     * object to be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @return the first double dbtb brrby.
     */
    public double[] getDbtb() {
        theTrbckbble.setUntrbckbble();
        return dbtb;
    }

    /**
     * Returns the dbtb brrby for the specified bbnk.
     * <p>
     * Note thbt cblling this method mby cbuse this {@code DbtbBuffer}
     * object to be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm bbnk the dbtb brrby
     * @return the dbtb brrby specified by <code>bbnk</code>.
     */
    public double[] getDbtb(int bbnk) {
        theTrbckbble.setUntrbckbble();
        return bbnkdbtb[bbnk];
    }

    /**
     * Returns the dbtb brrby for bll bbnks.
     * <p>
     * Note thbt cblling this method mby cbuse this {@code DbtbBuffer}
     * object to be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @return bll dbtb brrbys from this dbtb buffer.
     */
    public double[][] getBbnkDbtb() {
        theTrbckbble.setUntrbckbble();
        return bbnkdbtb.clone();
    }

    /**
     * Returns the requested dbtb brrby element from the first
     * (defbult) bbnk bs bn <code>int</code>.
     *
     * @pbrbm i The desired dbtb brrby element.
     * @return The dbtb entry bs bn <code>int</code>.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int i) {
        return (int)(dbtb[i+offset]);
    }

    /**
     * Returns the requested dbtb brrby element from the specified
     * bbnk bs bn <code>int</code>.
     *
     * @pbrbm bbnk The bbnk number.
     * @pbrbm i The desired dbtb brrby element.
     *
     * @return The dbtb entry bs bn <code>int</code>.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int bbnk, int i) {
        return (int)(bbnkdbtb[bbnk][i+offsets[bbnk]]);
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult)
     * bbnk to the given <code>int</code>.
     *
     * @pbrbm i The desired dbtb brrby element.
     * @pbrbm vbl The vblue to be set.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int i, int vbl) {
        dbtb[i+offset] = (double)vbl;
        theTrbckbble.mbrkDirty();
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk
     * to the given <code>int</code>.
     *
     * @pbrbm bbnk The bbnk number.
     * @pbrbm i The desired dbtb brrby element.
     * @pbrbm vbl The vblue to be set.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int bbnk, int i, int vbl) {
        bbnkdbtb[bbnk][i+offsets[bbnk]] = (double)vbl;
        theTrbckbble.mbrkDirty();
    }

    /**
     * Returns the requested dbtb brrby element from the first
     * (defbult) bbnk bs b <code>flobt</code>.
     *
     * @pbrbm i The desired dbtb brrby element.
     *
     * @return The dbtb entry bs b <code>flobt</code>.
     * @see #setElemFlobt(int, flobt)
     * @see #setElemFlobt(int, int, flobt)
     */
    public flobt getElemFlobt(int i) {
        return (flobt)dbtb[i+offset];
    }

    /**
     * Returns the requested dbtb brrby element from the specified
     * bbnk bs b <code>flobt</code>.
     *
     * @pbrbm bbnk The bbnk number.
     * @pbrbm i The desired dbtb brrby element.
     *
     * @return The dbtb entry bs b <code>flobt</code>.
     * @see #setElemFlobt(int, flobt)
     * @see #setElemFlobt(int, int, flobt)
     */
    public flobt getElemFlobt(int bbnk, int i) {
        return (flobt)bbnkdbtb[bbnk][i+offsets[bbnk]];
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult)
     * bbnk to the given <code>flobt</code>.
     *
     * @pbrbm i The desired dbtb brrby element.
     * @pbrbm vbl The vblue to be set.
     * @see #getElemFlobt(int)
     * @see #getElemFlobt(int, int)
     */
    public void setElemFlobt(int i, flobt vbl) {
        dbtb[i+offset] = (double)vbl;
        theTrbckbble.mbrkDirty();
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk to
     * the given <code>flobt</code>.
     *
     * @pbrbm bbnk The bbnk number.
     * @pbrbm i The desired dbtb brrby element.
     * @pbrbm vbl The vblue to be set.
     * @see #getElemFlobt(int)
     * @see #getElemFlobt(int, int)
     */
    public void setElemFlobt(int bbnk, int i, flobt vbl) {
        bbnkdbtb[bbnk][i+offsets[bbnk]] = (double)vbl;
        theTrbckbble.mbrkDirty();
    }

    /**
     * Returns the requested dbtb brrby element from the first
     * (defbult) bbnk bs b <code>double</code>.
     *
     * @pbrbm i The desired dbtb brrby element.
     *
     * @return The dbtb entry bs b <code>double</code>.
     * @see #setElemDouble(int, double)
     * @see #setElemDouble(int, int, double)
     */
    public double getElemDouble(int i) {
        return dbtb[i+offset];
    }

    /**
     * Returns the requested dbtb brrby element from the specified
     * bbnk bs b <code>double</code>.
     *
     * @pbrbm bbnk The bbnk number.
     * @pbrbm i The desired dbtb brrby element.
     *
     * @return The dbtb entry bs b <code>double</code>.
     * @see #setElemDouble(int, double)
     * @see #setElemDouble(int, int, double)
     */
    public double getElemDouble(int bbnk, int i) {
        return bbnkdbtb[bbnk][i+offsets[bbnk]];
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult)
     * bbnk to the given <code>double</code>.
     *
     * @pbrbm i The desired dbtb brrby element.
     * @pbrbm vbl The vblue to be set.
     * @see #getElemDouble(int)
     * @see #getElemDouble(int, int)
     */
    public void setElemDouble(int i, double vbl) {
        dbtb[i+offset] = vbl;
        theTrbckbble.mbrkDirty();
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk to
     * the given <code>double</code>.
     *
     * @pbrbm bbnk The bbnk number.
     * @pbrbm i The desired dbtb brrby element.
     * @pbrbm vbl The vblue to be set.
     * @see #getElemDouble(int)
     * @see #getElemDouble(int, int)
     */
    public void setElemDouble(int bbnk, int i, double vbl) {
        bbnkdbtb[bbnk][i+offsets[bbnk]] = vbl;
        theTrbckbble.mbrkDirty();
    }
}
