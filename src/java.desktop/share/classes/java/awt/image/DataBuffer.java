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

import sun.jbvb2d.StbteTrbckbble.Stbte;
import stbtic sun.jbvb2d.StbteTrbckbble.Stbte.*;
import sun.jbvb2d.StbteTrbckbbleDelegbte;

import sun.bwt.imbge.SunWritbbleRbster;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * This clbss exists to wrbp one or more dbtb brrbys.  Ebch dbtb brrby in
 * the DbtbBuffer is referred to bs b bbnk.  Accessor methods for getting
 * bnd setting elements of the DbtbBuffer's bbnks exist with bnd without
 * b bbnk specifier.  The methods without b bbnk specifier use the defbult 0th
 * bbnk.  The DbtbBuffer cbn optionblly tbke bn offset per bbnk, so thbt
 * dbtb in bn existing brrby cbn be used even if the interesting dbtb
 * doesn't stbrt bt brrby locbtion zero.  Getting or setting the 0th
 * element of b bbnk, uses the (0+offset)th element of the brrby.  The
 * size field specifies how much of the dbtb brrby is bvbilbble for
 * use.  Size + offset for b given bbnk should never be grebter
 * thbn the length of the bssocibted dbtb brrby.  The dbtb type of
 * b dbtb buffer indicbtes the type of the dbtb brrby(s) bnd mby blso
 * indicbte bdditionbl sembntics, e.g. storing unsigned 8-bit dbtb
 * in elements of b byte brrby.  The dbtb type mby be TYPE_UNDEFINED
 * or one of the types defined below.  Other types mby be bdded in
 * the future.  Generblly, bn object of clbss DbtbBuffer will be cbst down
 * to one of its dbtb type specific subclbsses to bccess dbtb type specific
 * methods for improved performbnce.  Currently, the Jbvb 2D(tm) API
 * imbge clbsses use TYPE_BYTE, TYPE_USHORT, TYPE_INT, TYPE_SHORT,
 * TYPE_FLOAT, bnd TYPE_DOUBLE DbtbBuffers to store imbge dbtb.
 * @see jbvb.bwt.imbge.Rbster
 * @see jbvb.bwt.imbge.SbmpleModel
 */
public bbstrbct clbss DbtbBuffer {

    /** Tbg for unsigned byte dbtb. */
    @Nbtive public stbtic finbl int TYPE_BYTE  = 0;

    /** Tbg for unsigned short dbtb. */
    @Nbtive public stbtic finbl int TYPE_USHORT = 1;

    /** Tbg for signed short dbtb.  Plbceholder for future use. */
    @Nbtive public stbtic finbl int TYPE_SHORT = 2;

    /** Tbg for int dbtb. */
    @Nbtive public stbtic finbl int TYPE_INT   = 3;

    /** Tbg for flobt dbtb.  Plbceholder for future use. */
    @Nbtive public stbtic finbl int TYPE_FLOAT  = 4;

    /** Tbg for double dbtb.  Plbceholder for future use. */
    @Nbtive public stbtic finbl int TYPE_DOUBLE  = 5;

    /** Tbg for undefined dbtb. */
    @Nbtive public stbtic finbl int TYPE_UNDEFINED = 32;

    /** The dbtb type of this DbtbBuffer. */
    protected int dbtbType;

    /** The number of bbnks in this DbtbBuffer. */
    protected int bbnks;

    /** Offset into defbult (first) bbnk from which to get the first element. */
    protected int offset;

    /** Usbble size of bll bbnks. */
    protected int size;

    /** Offsets into bll bbnks. */
    protected int offsets[];

    /* The current StbteTrbckbble stbte. */
    StbteTrbckbbleDelegbte theTrbckbble;

    /** Size of the dbtb types indexed by DbtbType tbgs defined bbove. */
    privbte stbtic finbl int dbtbTypeSize[] = {8,16,16,32,32,64};

    /** Returns the size (in bits) of the dbtb type, given b dbtbtype tbg.
      * @pbrbm type the vblue of one of the defined dbtbtype tbgs
      * @return the size of the dbtb type
      * @throws IllegblArgumentException if <code>type</code> is less thbn
      *         zero or grebter thbn {@link #TYPE_DOUBLE}
      */
    public stbtic int getDbtbTypeSize(int type) {
        if (type < TYPE_BYTE || type > TYPE_DOUBLE) {
            throw new IllegblArgumentException("Unknown dbtb type "+type);
        }
        return dbtbTypeSize[type];
    }

    /**
     *  Constructs b DbtbBuffer contbining one bbnk of the specified
     *  dbtb type bnd size.
     *
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     */
    protected DbtbBuffer(int dbtbType, int size) {
        this(UNTRACKABLE, dbtbType, size);
    }

    /**
     *  Constructs b DbtbBuffer contbining one bbnk of the specified
     *  dbtb type bnd size with the indicbted initibl {@link Stbte Stbte}.
     *
     *  @pbrbm initiblStbte the initibl {@link Stbte Stbte} stbte of the dbtb
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @since 1.7
     */
    DbtbBuffer(Stbte initiblStbte,
               int dbtbType, int size)
    {
        this.theTrbckbble = StbteTrbckbbleDelegbte.crebteInstbnce(initiblStbte);
        this.dbtbType = dbtbType;
        this.bbnks = 1;
        this.size = size;
        this.offset = 0;
        this.offsets = new int[1];  // init to 0 by new
    }

    /**
     *  Constructs b DbtbBuffer contbining the specified number of
     *  bbnks.  Ebch bbnk hbs the specified size bnd bn offset of 0.
     *
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @pbrbm numBbnks the number of bbnks in this
     *         <code>DbtbBuffer</code>
     */
    protected DbtbBuffer(int dbtbType, int size, int numBbnks) {
        this(UNTRACKABLE, dbtbType, size, numBbnks);
    }

    /**
     *  Constructs b DbtbBuffer contbining the specified number of
     *  bbnks with the indicbted initibl {@link Stbte Stbte}.
     *  Ebch bbnk hbs the specified size bnd bn offset of 0.
     *
     *  @pbrbm initiblStbte the initibl {@link Stbte Stbte} stbte of the dbtb
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @pbrbm numBbnks the number of bbnks in this
     *         <code>DbtbBuffer</code>
     *  @since 1.7
     */
    DbtbBuffer(Stbte initiblStbte,
               int dbtbType, int size, int numBbnks)
    {
        this.theTrbckbble = StbteTrbckbbleDelegbte.crebteInstbnce(initiblStbte);
        this.dbtbType = dbtbType;
        this.bbnks = numBbnks;
        this.size = size;
        this.offset = 0;
        this.offsets = new int[bbnks]; // init to 0 by new
    }

    /**
     *  Constructs b DbtbBuffer thbt contbins the specified number
     *  of bbnks.  Ebch bbnk hbs the specified dbtbtype, size bnd offset.
     *
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @pbrbm numBbnks the number of bbnks in this
     *         <code>DbtbBuffer</code>
     *  @pbrbm offset the offset for ebch bbnk
     */
    protected DbtbBuffer(int dbtbType, int size, int numBbnks, int offset) {
        this(UNTRACKABLE, dbtbType, size, numBbnks, offset);
    }

    /**
     *  Constructs b DbtbBuffer thbt contbins the specified number
     *  of bbnks with the indicbted initibl {@link Stbte Stbte}.
     *  Ebch bbnk hbs the specified dbtbtype, size bnd offset.
     *
     *  @pbrbm initiblStbte the initibl {@link Stbte Stbte} stbte of the dbtb
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @pbrbm numBbnks the number of bbnks in this
     *         <code>DbtbBuffer</code>
     *  @pbrbm offset the offset for ebch bbnk
     *  @since 1.7
     */
    DbtbBuffer(Stbte initiblStbte,
               int dbtbType, int size, int numBbnks, int offset)
    {
        this.theTrbckbble = StbteTrbckbbleDelegbte.crebteInstbnce(initiblStbte);
        this.dbtbType = dbtbType;
        this.bbnks = numBbnks;
        this.size = size;
        this.offset = offset;
        this.offsets = new int[numBbnks];
        for (int i = 0; i < numBbnks; i++) {
            this.offsets[i] = offset;
        }
    }

    /**
     *  Constructs b DbtbBuffer which contbins the specified number
     *  of bbnks.  Ebch bbnk hbs the specified dbtbtype bnd size.  The
     *  offset for ebch bbnk is specified by its respective entry in
     *  the offsets brrby.
     *
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @pbrbm numBbnks the number of bbnks in this
     *         <code>DbtbBuffer</code>
     *  @pbrbm offsets bn brrby contbining bn offset for ebch bbnk.
     *  @throws ArrbyIndexOutOfBoundsException if <code>numBbnks</code>
     *          does not equbl the length of <code>offsets</code>
     */
    protected DbtbBuffer(int dbtbType, int size, int numBbnks, int offsets[]) {
        this(UNTRACKABLE, dbtbType, size, numBbnks, offsets);
    }

    /**
     *  Constructs b DbtbBuffer which contbins the specified number
     *  of bbnks with the indicbted initibl {@link Stbte Stbte}.
     *  Ebch bbnk hbs the specified dbtbtype bnd size.  The
     *  offset for ebch bbnk is specified by its respective entry in
     *  the offsets brrby.
     *
     *  @pbrbm initiblStbte the initibl {@link Stbte Stbte} stbte of the dbtb
     *  @pbrbm dbtbType the dbtb type of this <code>DbtbBuffer</code>
     *  @pbrbm size the size of the bbnks
     *  @pbrbm numBbnks the number of bbnks in this
     *         <code>DbtbBuffer</code>
     *  @pbrbm offsets bn brrby contbining bn offset for ebch bbnk.
     *  @throws ArrbyIndexOutOfBoundsException if <code>numBbnks</code>
     *          does not equbl the length of <code>offsets</code>
     *  @since 1.7
     */
    DbtbBuffer(Stbte initiblStbte,
               int dbtbType, int size, int numBbnks, int offsets[])
    {
        if (numBbnks != offsets.length) {
            throw new ArrbyIndexOutOfBoundsException("Number of bbnks" +
                 " does not mbtch number of bbnk offsets");
        }
        this.theTrbckbble = StbteTrbckbbleDelegbte.crebteInstbnce(initiblStbte);
        this.dbtbType = dbtbType;
        this.bbnks = numBbnks;
        this.size = size;
        this.offset = offsets[0];
        this.offsets = offsets.clone();
    }

    /**  Returns the dbtb type of this DbtbBuffer.
     *   @return the dbtb type of this <code>DbtbBuffer</code>.
     */
    public int getDbtbType() {
        return dbtbType;
    }

    /**  Returns the size (in brrby elements) of bll bbnks.
     *   @return the size of bll bbnks.
     */
    public int getSize() {
        return size;
    }

    /** Returns the offset of the defbult bbnk in brrby elements.
     *  @return the offset of the defbult bbnk.
     */
    public int getOffset() {
        return offset;
    }

    /** Returns the offsets (in brrby elements) of bll the bbnks.
     *  @return the offsets of bll bbnks.
     */
    public int[] getOffsets() {
        return offsets.clone();
    }

    /** Returns the number of bbnks in this DbtbBuffer.
     *  @return the number of bbnks.
     */
    public int getNumBbnks() {
        return bbnks;
    }

    /**
     * Returns the requested dbtb brrby element from the first (defbult) bbnk
     * bs bn integer.
     * @pbrbm i the index of the requested dbtb brrby element
     * @return the dbtb brrby element bt the specified index.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int i) {
        return getElem(0,i);
    }

    /**
     * Returns the requested dbtb brrby element from the specified bbnk
     * bs bn integer.
     * @pbrbm bbnk the specified bbnk
     * @pbrbm i the index of the requested dbtb brrby element
     * @return the dbtb brrby element bt the specified index from the
     *         specified bbnk bt the specified index.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public bbstrbct int getElem(int bbnk, int i);

    /**
     * Sets the requested dbtb brrby element in the first (defbult) bbnk
     * from the given integer.
     * @pbrbm i the specified index into the dbtb brrby
     * @pbrbm vbl the dbtb to set the element bt the specified index in
     * the dbtb brrby
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void  setElem(int i, int vbl) {
        setElem(0,i,vbl);
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk
     * from the given integer.
     * @pbrbm bbnk the specified bbnk
     * @pbrbm i the specified index into the dbtb brrby
     * @pbrbm vbl  the dbtb to set the element in the specified bbnk
     * bt the specified index in the dbtb brrby
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public bbstrbct void setElem(int bbnk, int i, int vbl);

    /**
     * Returns the requested dbtb brrby element from the first (defbult) bbnk
     * bs b flobt.  The implementbtion in this clbss is to cbst getElem(i)
     * to b flobt.  Subclbsses mby override this method if bnother
     * implementbtion is needed.
     * @pbrbm i the index of the requested dbtb brrby element
     * @return b flobt vblue representing the dbtb brrby element bt the
     *  specified index.
     * @see #setElemFlobt(int, flobt)
     * @see #setElemFlobt(int, int, flobt)
     */
    public flobt getElemFlobt(int i) {
        return (flobt)getElem(i);
    }

    /**
     * Returns the requested dbtb brrby element from the specified bbnk
     * bs b flobt.  The implementbtion in this clbss is to cbst
     * {@link #getElem(int, int)}
     * to b flobt.  Subclbsses cbn override this method if bnother
     * implementbtion is needed.
     * @pbrbm bbnk the specified bbnk
     * @pbrbm i the index of the requested dbtb brrby element
     * @return b flobt vblue representing the dbtb brrby element from the
     * specified bbnk bt the specified index.
     * @see #setElemFlobt(int, flobt)
     * @see #setElemFlobt(int, int, flobt)
     */
    public flobt getElemFlobt(int bbnk, int i) {
        return (flobt)getElem(bbnk,i);
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult) bbnk
     * from the given flobt.  The implementbtion in this clbss is to cbst
     * vbl to bn int bnd cbll {@link #setElem(int, int)}.  Subclbsses
     * cbn override this method if bnother implementbtion is needed.
     * @pbrbm i the specified index
     * @pbrbm vbl the vblue to set the element bt the specified index in
     * the dbtb brrby
     * @see #getElemFlobt(int)
     * @see #getElemFlobt(int, int)
     */
    public void setElemFlobt(int i, flobt vbl) {
        setElem(i,(int)vbl);
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk
     * from the given flobt.  The implementbtion in this clbss is to cbst
     * vbl to bn int bnd cbll {@link #setElem(int, int)}.  Subclbsses cbn
     * override this method if bnother implementbtion is needed.
     * @pbrbm bbnk the specified bbnk
     * @pbrbm i the specified index
     * @pbrbm vbl the vblue to set the element in the specified bbnk bt
     * the specified index in the dbtb brrby
     * @see #getElemFlobt(int)
     * @see #getElemFlobt(int, int)
     */
    public void setElemFlobt(int bbnk, int i, flobt vbl) {
        setElem(bbnk,i,(int)vbl);
    }

    /**
     * Returns the requested dbtb brrby element from the first (defbult) bbnk
     * bs b double.  The implementbtion in this clbss is to cbst
     * {@link #getElem(int)}
     * to b double.  Subclbsses cbn override this method if bnother
     * implementbtion is needed.
     * @pbrbm i the specified index
     * @return b double vblue representing the element bt the specified
     * index in the dbtb brrby.
     * @see #setElemDouble(int, double)
     * @see #setElemDouble(int, int, double)
     */
    public double getElemDouble(int i) {
        return (double)getElem(i);
    }

    /**
     * Returns the requested dbtb brrby element from the specified bbnk bs
     * b double.  The implementbtion in this clbss is to cbst getElem(bbnk, i)
     * to b double.  Subclbsses mby override this method if bnother
     * implementbtion is needed.
     * @pbrbm bbnk the specified bbnk
     * @pbrbm i the specified index
     * @return b double vblue representing the element from the specified
     * bbnk bt the specified index in the dbtb brrby.
     * @see #setElemDouble(int, double)
     * @see #setElemDouble(int, int, double)
     */
    public double getElemDouble(int bbnk, int i) {
        return (double)getElem(bbnk,i);
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult) bbnk
     * from the given double.  The implementbtion in this clbss is to cbst
     * vbl to bn int bnd cbll {@link #setElem(int, int)}.  Subclbsses cbn
     * override this method if bnother implementbtion is needed.
     * @pbrbm i the specified index
     * @pbrbm vbl the vblue to set the element bt the specified index
     * in the dbtb brrby
     * @see #getElemDouble(int)
     * @see #getElemDouble(int, int)
     */
    public void setElemDouble(int i, double vbl) {
        setElem(i,(int)vbl);
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk
     * from the given double.  The implementbtion in this clbss is to cbst
     * vbl to bn int bnd cbll {@link #setElem(int, int)}.  Subclbsses cbn
     * override this method if bnother implementbtion is needed.
     * @pbrbm bbnk the specified bbnk
     * @pbrbm i the specified index
     * @pbrbm vbl the vblue to set the element in the specified bbnk
     * bt the specified index of the dbtb brrby
     * @see #getElemDouble(int)
     * @see #getElemDouble(int, int)
     */
    public void setElemDouble(int bbnk, int i, double vbl) {
        setElem(bbnk,i,(int)vbl);
    }

    stbtic int[] toIntArrby(Object obj) {
        if (obj instbnceof int[]) {
            return (int[])obj;
        } else if (obj == null) {
            return null;
        } else if (obj instbnceof short[]) {
            short sdbtb[] = (short[])obj;
            int idbtb[] = new int[sdbtb.length];
            for (int i = 0; i < sdbtb.length; i++) {
                idbtb[i] = (int)sdbtb[i] & 0xffff;
            }
            return idbtb;
        } else if (obj instbnceof byte[]) {
            byte bdbtb[] = (byte[])obj;
            int idbtb[] = new int[bdbtb.length];
            for (int i = 0; i < bdbtb.length; i++) {
                idbtb[i] = 0xff & (int)bdbtb[i];
            }
            return idbtb;
        }
        return null;
    }

    stbtic {
        SunWritbbleRbster.setDbtbStebler(new SunWritbbleRbster.DbtbStebler() {
            public byte[] getDbtb(DbtbBufferByte dbb, int bbnk) {
                return dbb.bbnkdbtb[bbnk];
            }

            public short[] getDbtb(DbtbBufferUShort dbus, int bbnk) {
                return dbus.bbnkdbtb[bbnk];
            }

            public int[] getDbtb(DbtbBufferInt dbi, int bbnk) {
                return dbi.bbnkdbtb[bbnk];
            }

            public StbteTrbckbbleDelegbte getTrbckbble(DbtbBuffer db) {
                return db.theTrbckbble;
            }

            public void setTrbckbble(DbtbBuffer db,
                                     StbteTrbckbbleDelegbte trbckbble)
            {
                db.theTrbckbble = trbckbble;
            }
        });
    }
}
