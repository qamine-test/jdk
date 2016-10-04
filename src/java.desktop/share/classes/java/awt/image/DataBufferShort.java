/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublished  work pursubnt to Title 17 of the United
 *** Stbtes Code.  All rights reserved.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbckbge jbvb.bwt.imbge;

import stbtic sun.jbvb2d.StbteTrbckbble.Stbte.*;

/**
 * This clbss extends <CODE>DbtbBuffer</CODE> bnd stores dbtb internblly bs shorts.
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
 */
public finbl clbss DbtbBufferShort extends DbtbBuffer
{
    /** The defbult dbtb bbnk. */
    short dbtb[];

    /** All dbtb bbnks */
    short bbnkdbtb[][];

    /**
     * Constructs b short-bbsed <CODE>DbtbBuffer</CODE> with b single bbnk bnd the
     * specified size.
     *
     * @pbrbm size The size of the <CODE>DbtbBuffer</CODE>.
     */
    public DbtbBufferShort(int size) {
        super(STABLE, TYPE_SHORT,size);
        dbtb = new short[size];
        bbnkdbtb = new short[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Constructs b short-bbsed <CODE>DbtbBuffer</CODE> with the specified number of
     * bbnks bll of which bre the specified size.
     *
     * @pbrbm size The size of the bbnks in the <CODE>DbtbBuffer</CODE>.
     * @pbrbm numBbnks The number of bbnks in the b<CODE>DbtbBuffer</CODE>.
     */
    public DbtbBufferShort(int size, int numBbnks) {
        super(STABLE, TYPE_SHORT,size,numBbnks);
        bbnkdbtb = new short[numBbnks][];
        for (int i= 0; i < numBbnks; i++) {
            bbnkdbtb[i] = new short[size];
        }
        dbtb = bbnkdbtb[0];
    }

    /**
     * Constructs b short-bbsed <CODE>DbtbBuffer</CODE> with b single bbnk using the
     * specified brrby.
     * Only the first <CODE>size</CODE> elements should be used by bccessors of
     * this <CODE>DbtbBuffer</CODE>.  <CODE>dbtbArrby</CODE> must be lbrge enough to
     * hold <CODE>size</CODE> elements.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby The short brrby for the <CODE>DbtbBuffer</CODE>.
     * @pbrbm size The size of the <CODE>DbtbBuffer</CODE> bbnk.
     */
    public DbtbBufferShort(short dbtbArrby[], int size) {
        super(UNTRACKABLE, TYPE_SHORT, size);
        dbtb = dbtbArrby;
        bbnkdbtb = new short[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Constructs b short-bbsed <CODE>DbtbBuffer</CODE> with b single bbnk using the
     * specified brrby, size, bnd offset.  <CODE>dbtbArrby</CODE> must hbve bt lebst
     * <CODE>offset</CODE> + <CODE>size</CODE> elements.  Only elements <CODE>offset</CODE>
     * through <CODE>offset</CODE> + <CODE>size</CODE> - 1
     * should be used by bccessors of this <CODE>DbtbBuffer</CODE>.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby The short brrby for the <CODE>DbtbBuffer</CODE>.
     * @pbrbm size The size of the <CODE>DbtbBuffer</CODE> bbnk.
     * @pbrbm offset The offset into the <CODE>dbtbArrby</CODE>.
     */
    public DbtbBufferShort(short dbtbArrby[], int size, int offset) {
        super(UNTRACKABLE, TYPE_SHORT, size, 1, offset);
        dbtb = dbtbArrby;
        bbnkdbtb = new short[1][];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Constructs b short-bbsed <CODE>DbtbBuffer</CODE> with the specified brrbys.
     * The number of bbnks will be equbl to <CODE>dbtbArrby.length</CODE>.
     * Only the first <CODE>size</CODE> elements of ebch brrby should be used by
     * bccessors of this <CODE>DbtbBuffer</CODE>.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby The short brrbys for the <CODE>DbtbBuffer</CODE>.
     * @pbrbm size The size of the bbnks in the <CODE>DbtbBuffer</CODE>.
     */
    public DbtbBufferShort(short dbtbArrby[][], int size) {
        super(UNTRACKABLE, TYPE_SHORT, size, dbtbArrby.length);
        bbnkdbtb = dbtbArrby.clone();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Constructs b short-bbsed <CODE>DbtbBuffer</CODE> with the specified brrbys, size,
     * bnd offsets.
     * The number of bbnks is equbl to <CODE>dbtbArrby.length</CODE>.  Ebch brrby must
     * be bt lebst bs lbrge bs <CODE>size</CODE> + the corresponding offset.   There must
     * be bn entry in the offset brrby for ebch <CODE>dbtbArrby</CODE> entry.  For ebch
     * bbnk, only elements <CODE>offset</CODE> through
     * <CODE>offset</CODE> + <CODE>size</CODE> - 1 should be
     * used by bccessors of this <CODE>DbtbBuffer</CODE>.
     * <p>
     * Note thbt {@code DbtbBuffer} objects crebted by this constructor
     * mby be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @pbrbm dbtbArrby The short brrbys for the <CODE>DbtbBuffer</CODE>.
     * @pbrbm size The size of the bbnks in the <CODE>DbtbBuffer</CODE>.
     * @pbrbm offsets The offsets into ebch brrby.
     */
    public DbtbBufferShort(short dbtbArrby[][], int size, int offsets[]) {
        super(UNTRACKABLE, TYPE_SHORT, size, dbtbArrby.length, offsets);
        bbnkdbtb = dbtbArrby.clone();
        dbtb = bbnkdbtb[0];
    }

    /**
     * Returns the defbult (first) byte dbtb brrby.
     * <p>
     * Note thbt cblling this method mby cbuse this {@code DbtbBuffer}
     * object to be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @return The first short dbtb brrby.
     */
    public short[] getDbtb() {
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
     * @pbrbm bbnk The bbnk whose dbtb brrby you wbnt to get.
     * @return The dbtb brrby for the specified bbnk.
     */
    public short[] getDbtb(int bbnk) {
        theTrbckbble.setUntrbckbble();
        return bbnkdbtb[bbnk];
    }

    /**
     * Returns the dbtb brrbys for bll bbnks.
     * <p>
     * Note thbt cblling this method mby cbuse this {@code DbtbBuffer}
     * object to be incompbtible with <b href="#optimizbtions">performbnce
     * optimizbtions</b> used by some implementbtions (such bs cbching
     * bn bssocibted imbge in video memory).
     *
     * @return All of the dbtb brrbys.
     */
    public short[][] getBbnkDbtb() {
        theTrbckbble.setUntrbckbble();
        return bbnkdbtb.clone();
    }

    /**
     * Returns the requested dbtb brrby element from the first (defbult) bbnk.
     *
     * @pbrbm i The dbtb brrby element you wbnt to get.
     * @return The requested dbtb brrby element bs bn integer.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int i) {
        return (int)(dbtb[i+offset]);
    }

    /**
     * Returns the requested dbtb brrby element from the specified bbnk.
     *
     * @pbrbm bbnk The bbnk from which you wbnt to get b dbtb brrby element.
     * @pbrbm i The dbtb brrby element you wbnt to get.
     * @return The requested dbtb brrby element bs bn integer.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int bbnk, int i) {
        return (int)(bbnkdbtb[bbnk][i+offsets[bbnk]]);
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult) bbnk
     * to the specified vblue.
     *
     * @pbrbm i The dbtb brrby element you wbnt to set.
     * @pbrbm vbl The integer vblue to which you wbnt to set the dbtb brrby element.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int i, int vbl) {
        dbtb[i+offset] = (short)vbl;
        theTrbckbble.mbrkDirty();
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk
     * from the given integer.
     * @pbrbm bbnk The bbnk in which you wbnt to set the dbtb brrby element.
     * @pbrbm i The dbtb brrby element you wbnt to set.
     * @pbrbm vbl The integer vblue to which you wbnt to set the specified dbtb brrby element.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int bbnk, int i, int vbl) {
        bbnkdbtb[bbnk][i+offsets[bbnk]] = (short)vbl;
        theTrbckbble.mbrkDirty();
    }
}
