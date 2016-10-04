/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.security.*;
import jbvb.lbng.reflect.*;

import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;


/**
 * A collection of methods for performing low-level, unsbfe operbtions.
 * Although the clbss bnd bll methods bre public, use of this clbss is
 * limited becbuse only trusted code cbn obtbin instbnces of it.
 *
 * @buthor John R. Rose
 * @see #getUnsbfe
 */

public finbl clbss Unsbfe {

    privbte stbtic nbtive void registerNbtives();
    stbtic {
        registerNbtives();
        sun.reflect.Reflection.registerMethodsToFilter(Unsbfe.clbss, "getUnsbfe");
    }

    privbte Unsbfe() {}

    privbte stbtic finbl Unsbfe theUnsbfe = new Unsbfe();

    /**
     * Provides the cbller with the cbpbbility of performing unsbfe
     * operbtions.
     *
     * <p> The returned <code>Unsbfe</code> object should be cbrefully gubrded
     * by the cbller, since it cbn be used to rebd bnd write dbtb bt brbitrbry
     * memory bddresses.  It must never be pbssed to untrusted code.
     *
     * <p> Most methods in this clbss bre very low-level, bnd correspond to b
     * smbll number of hbrdwbre instructions (on typicbl mbchines).  Compilers
     * bre encourbged to optimize these methods bccordingly.
     *
     * <p> Here is b suggested idiom for using unsbfe operbtions:
     *
     * <blockquote><pre>
     * clbss MyTrustedClbss {
     *   privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
     *   ...
     *   privbte long myCountAddress = ...;
     *   public int getCount() { return unsbfe.getByte(myCountAddress); }
     * }
     * </pre></blockquote>
     *
     * (It mby bssist compilers to mbke the locbl vbribble be
     * <code>finbl</code>.)
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertiesAccess</code> method doesn't bllow
     *             bccess to the system properties.
     */
    @CbllerSensitive
    public stbtic Unsbfe getUnsbfe() {
        Clbss<?> cbller = Reflection.getCbllerClbss();
        if (!VM.isSystemDombinLobder(cbller.getClbssLobder()))
            throw new SecurityException("Unsbfe");
        return theUnsbfe;
    }

    /// peek bnd poke operbtions
    /// (compilers should optimize these to memory ops)

    // These work on object fields in the Jbvb hebp.
    // They will not work on elements of pbcked brrbys.

    /**
     * Fetches b vblue from b given Jbvb vbribble.
     * More specificblly, fetches b field or brrby element within the given
     * object <code>o</code> bt the given offset, or (if <code>o</code> is
     * null) from the memory bddress whose numericbl vblue is the given
     * offset.
     * <p>
     * The results bre undefined unless one of the following cbses is true:
     * <ul>
     * <li>The offset wbs obtbined from {@link #objectFieldOffset} on
     * the {@link jbvb.lbng.reflect.Field} of some Jbvb field bnd the object
     * referred to by <code>o</code> is of b clbss compbtible with thbt
     * field's clbss.
     *
     * <li>The offset bnd object reference <code>o</code> (either null or
     * non-null) were both obtbined vib {@link #stbticFieldOffset}
     * bnd {@link #stbticFieldBbse} (respectively) from the
     * reflective {@link Field} representbtion of some Jbvb field.
     *
     * <li>The object referred to by <code>o</code> is bn brrby, bnd the offset
     * is bn integer of the form <code>B+N*S</code>, where <code>N</code> is
     * b vblid index into the brrby, bnd <code>B</code> bnd <code>S</code> bre
     * the vblues obtbined by {@link #brrbyBbseOffset} bnd {@link
     * #brrbyIndexScble} (respectively) from the brrby's clbss.  The vblue
     * referred to is the <code>N</code><em>th</em> element of the brrby.
     *
     * </ul>
     * <p>
     * If one of the bbove cbses is true, the cbll references b specific Jbvb
     * vbribble (field or brrby element).  However, the results bre undefined
     * if thbt vbribble is not in fbct of the type returned by this method.
     * <p>
     * This method refers to b vbribble by mebns of two pbrbmeters, bnd so
     * it provides (in effect) b <em>double-register</em> bddressing mode
     * for Jbvb vbribbles.  When the object reference is null, this method
     * uses its offset bs bn bbsolute bddress.  This is similbr in operbtion
     * to methods such bs {@link #getInt(long)}, which provide (in effect) b
     * <em>single-register</em> bddressing mode for non-Jbvb vbribbles.
     * However, becbuse Jbvb vbribbles mby hbve b different lbyout in memory
     * from non-Jbvb vbribbles, progrbmmers should not bssume thbt these
     * two bddressing modes bre ever equivblent.  Also, progrbmmers should
     * remember thbt offsets from the double-register bddressing mode cbnnot
     * be portbbly confused with longs used in the single-register bddressing
     * mode.
     *
     * @pbrbm o Jbvb hebp object in which the vbribble resides, if bny, else
     *        null
     * @pbrbm offset indicbtion of where the vbribble resides in b Jbvb hebp
     *        object, if bny, else b memory bddress locbting the vbribble
     *        stbticblly
     * @return the vblue fetched from the indicbted Jbvb vbribble
     * @throws RuntimeException No defined exceptions bre thrown, not even
     *         {@link NullPointerException}
     */
    public nbtive int getInt(Object o, long offset);

    /**
     * Stores b vblue into b given Jbvb vbribble.
     * <p>
     * The first two pbrbmeters bre interpreted exbctly bs with
     * {@link #getInt(Object, long)} to refer to b specific
     * Jbvb vbribble (field or brrby element).  The given vblue
     * is stored into thbt vbribble.
     * <p>
     * The vbribble must be of the sbme type bs the method
     * pbrbmeter <code>x</code>.
     *
     * @pbrbm o Jbvb hebp object in which the vbribble resides, if bny, else
     *        null
     * @pbrbm offset indicbtion of where the vbribble resides in b Jbvb hebp
     *        object, if bny, else b memory bddress locbting the vbribble
     *        stbticblly
     * @pbrbm x the vblue to store into the indicbted Jbvb vbribble
     * @throws RuntimeException No defined exceptions bre thrown, not even
     *         {@link NullPointerException}
     */
    public nbtive void putInt(Object o, long offset, int x);

    /**
     * Fetches b reference vblue from b given Jbvb vbribble.
     * @see #getInt(Object, long)
     */
    public nbtive Object getObject(Object o, long offset);

    /**
     * Stores b reference vblue into b given Jbvb vbribble.
     * <p>
     * Unless the reference <code>x</code> being stored is either null
     * or mbtches the field type, the results bre undefined.
     * If the reference <code>o</code> is non-null, cbr mbrks or
     * other store bbrriers for thbt object (if the VM requires them)
     * bre updbted.
     * @see #putInt(Object, int, int)
     */
    public nbtive void putObject(Object o, long offset, Object x);

    /** @see #getInt(Object, long) */
    public nbtive boolebn getBoolebn(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putBoolebn(Object o, long offset, boolebn x);
    /** @see #getInt(Object, long) */
    public nbtive byte    getByte(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putByte(Object o, long offset, byte x);
    /** @see #getInt(Object, long) */
    public nbtive short   getShort(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putShort(Object o, long offset, short x);
    /** @see #getInt(Object, long) */
    public nbtive chbr    getChbr(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putChbr(Object o, long offset, chbr x);
    /** @see #getInt(Object, long) */
    public nbtive long    getLong(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putLong(Object o, long offset, long x);
    /** @see #getInt(Object, long) */
    public nbtive flobt   getFlobt(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putFlobt(Object o, long offset, flobt x);
    /** @see #getInt(Object, long) */
    public nbtive double  getDouble(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public nbtive void    putDouble(Object o, long offset, double x);

    /**
     * This method, like bll others with 32-bit offsets, wbs nbtive
     * in b previous relebse but is now b wrbpper which simply cbsts
     * the offset to b long vblue.  It provides bbckwbrd compbtibility
     * with bytecodes compiled bgbinst 1.4.
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public int getInt(Object o, int offset) {
        return getInt(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putInt(Object o, int offset, int x) {
        putInt(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public Object getObject(Object o, int offset) {
        return getObject(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putObject(Object o, int offset, Object x) {
        putObject(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public boolebn getBoolebn(Object o, int offset) {
        return getBoolebn(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putBoolebn(Object o, int offset, boolebn x) {
        putBoolebn(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public byte getByte(Object o, int offset) {
        return getByte(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putByte(Object o, int offset, byte x) {
        putByte(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public short getShort(Object o, int offset) {
        return getShort(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putShort(Object o, int offset, short x) {
        putShort(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public chbr getChbr(Object o, int offset) {
        return getChbr(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putChbr(Object o, int offset, chbr x) {
        putChbr(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public long getLong(Object o, int offset) {
        return getLong(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putLong(Object o, int offset, long x) {
        putLong(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public flobt getFlobt(Object o, int offset) {
        return getFlobt(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putFlobt(Object o, int offset, flobt x) {
        putFlobt(o, (long)offset, x);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public double getDouble(Object o, int offset) {
        return getDouble(o, (long)offset);
    }

    /**
     * @deprecbted As of 1.4.1, cbst the 32-bit offset brgument to b long.
     * See {@link #stbticFieldOffset}.
     */
    @Deprecbted
    public void putDouble(Object o, int offset, double x) {
        putDouble(o, (long)offset, x);
    }

    // These work on vblues in the C hebp.

    /**
     * Fetches b vblue from b given memory bddress.  If the bddress is zero, or
     * does not point into b block obtbined from {@link #bllocbteMemory}, the
     * results bre undefined.
     *
     * @see #bllocbteMemory
     */
    public nbtive byte    getByte(long bddress);

    /**
     * Stores b vblue into b given memory bddress.  If the bddress is zero, or
     * does not point into b block obtbined from {@link #bllocbteMemory}, the
     * results bre undefined.
     *
     * @see #getByte(long)
     */
    public nbtive void    putByte(long bddress, byte x);

    /** @see #getByte(long) */
    public nbtive short   getShort(long bddress);
    /** @see #putByte(long, byte) */
    public nbtive void    putShort(long bddress, short x);
    /** @see #getByte(long) */
    public nbtive chbr    getChbr(long bddress);
    /** @see #putByte(long, byte) */
    public nbtive void    putChbr(long bddress, chbr x);
    /** @see #getByte(long) */
    public nbtive int     getInt(long bddress);
    /** @see #putByte(long, byte) */
    public nbtive void    putInt(long bddress, int x);
    /** @see #getByte(long) */
    public nbtive long    getLong(long bddress);
    /** @see #putByte(long, byte) */
    public nbtive void    putLong(long bddress, long x);
    /** @see #getByte(long) */
    public nbtive flobt   getFlobt(long bddress);
    /** @see #putByte(long, byte) */
    public nbtive void    putFlobt(long bddress, flobt x);
    /** @see #getByte(long) */
    public nbtive double  getDouble(long bddress);
    /** @see #putByte(long, byte) */
    public nbtive void    putDouble(long bddress, double x);

    /**
     * Fetches b nbtive pointer from b given memory bddress.  If the bddress is
     * zero, or does not point into b block obtbined from {@link
     * #bllocbteMemory}, the results bre undefined.
     *
     * <p> If the nbtive pointer is less thbn 64 bits wide, it is extended bs
     * bn unsigned number to b Jbvb long.  The pointer mby be indexed by bny
     * given byte offset, simply by bdding thbt offset (bs b simple integer) to
     * the long representing the pointer.  The number of bytes bctublly rebd
     * from the tbrget bddress mbybe determined by consulting {@link
     * #bddressSize}.
     *
     * @see #bllocbteMemory
     */
    public nbtive long getAddress(long bddress);

    /**
     * Stores b nbtive pointer into b given memory bddress.  If the bddress is
     * zero, or does not point into b block obtbined from {@link
     * #bllocbteMemory}, the results bre undefined.
     *
     * <p> The number of bytes bctublly written bt the tbrget bddress mbybe
     * determined by consulting {@link #bddressSize}.
     *
     * @see #getAddress(long)
     */
    public nbtive void putAddress(long bddress, long x);

    /// wrbppers for mblloc, reblloc, free:

    /**
     * Allocbtes b new block of nbtive memory, of the given size in bytes.  The
     * contents of the memory bre uninitiblized; they will generblly be
     * gbrbbge.  The resulting nbtive pointer will never be zero, bnd will be
     * bligned for bll vblue types.  Dispose of this memory by cblling {@link
     * #freeMemory}, or resize it with {@link #rebllocbteMemory}.
     *
     * @throws IllegblArgumentException if the size is negbtive or too lbrge
     *         for the nbtive size_t type
     *
     * @throws OutOfMemoryError if the bllocbtion is refused by the system
     *
     * @see #getByte(long)
     * @see #putByte(long, byte)
     */
    public nbtive long bllocbteMemory(long bytes);

    /**
     * Resizes b new block of nbtive memory, to the given size in bytes.  The
     * contents of the new block pbst the size of the old block bre
     * uninitiblized; they will generblly be gbrbbge.  The resulting nbtive
     * pointer will be zero if bnd only if the requested size is zero.  The
     * resulting nbtive pointer will be bligned for bll vblue types.  Dispose
     * of this memory by cblling {@link #freeMemory}, or resize it with {@link
     * #rebllocbteMemory}.  The bddress pbssed to this method mby be null, in
     * which cbse bn bllocbtion will be performed.
     *
     * @throws IllegblArgumentException if the size is negbtive or too lbrge
     *         for the nbtive size_t type
     *
     * @throws OutOfMemoryError if the bllocbtion is refused by the system
     *
     * @see #bllocbteMemory
     */
    public nbtive long rebllocbteMemory(long bddress, long bytes);

    /**
     * Sets bll bytes in b given block of memory to b fixed vblue
     * (usublly zero).
     *
     * <p>This method determines b block's bbse bddress by mebns of two pbrbmeters,
     * bnd so it provides (in effect) b <em>double-register</em> bddressing mode,
     * bs discussed in {@link #getInt(Object,long)}.  When the object reference is null,
     * the offset supplies bn bbsolute bbse bddress.
     *
     * <p>The stores bre in coherent (btomic) units of b size determined
     * by the bddress bnd length pbrbmeters.  If the effective bddress bnd
     * length bre bll even modulo 8, the stores tbke plbce in 'long' units.
     * If the effective bddress bnd length bre (resp.) even modulo 4 or 2,
     * the stores tbke plbce in units of 'int' or 'short'.
     *
     * @since 1.7
     */
    public nbtive void setMemory(Object o, long offset, long bytes, byte vblue);

    /**
     * Sets bll bytes in b given block of memory to b fixed vblue
     * (usublly zero).  This provides b <em>single-register</em> bddressing mode,
     * bs discussed in {@link #getInt(Object,long)}.
     *
     * <p>Equivblent to <code>setMemory(null, bddress, bytes, vblue)</code>.
     */
    public void setMemory(long bddress, long bytes, byte vblue) {
        setMemory(null, bddress, bytes, vblue);
    }

    /**
     * Sets bll bytes in b given block of memory to b copy of bnother
     * block.
     *
     * <p>This method determines ebch block's bbse bddress by mebns of two pbrbmeters,
     * bnd so it provides (in effect) b <em>double-register</em> bddressing mode,
     * bs discussed in {@link #getInt(Object,long)}.  When the object reference is null,
     * the offset supplies bn bbsolute bbse bddress.
     *
     * <p>The trbnsfers bre in coherent (btomic) units of b size determined
     * by the bddress bnd length pbrbmeters.  If the effective bddresses bnd
     * length bre bll even modulo 8, the trbnsfer tbkes plbce in 'long' units.
     * If the effective bddresses bnd length bre (resp.) even modulo 4 or 2,
     * the trbnsfer tbkes plbce in units of 'int' or 'short'.
     *
     * @since 1.7
     */
    public nbtive void copyMemory(Object srcBbse, long srcOffset,
                                  Object destBbse, long destOffset,
                                  long bytes);
    /**
     * Sets bll bytes in b given block of memory to b copy of bnother
     * block.  This provides b <em>single-register</em> bddressing mode,
     * bs discussed in {@link #getInt(Object,long)}.
     *
     * Equivblent to <code>copyMemory(null, srcAddress, null, destAddress, bytes)</code>.
     */
    public void copyMemory(long srcAddress, long destAddress, long bytes) {
        copyMemory(null, srcAddress, null, destAddress, bytes);
    }

    /**
     * Disposes of b block of nbtive memory, bs obtbined from {@link
     * #bllocbteMemory} or {@link #rebllocbteMemory}.  The bddress pbssed to
     * this method mby be null, in which cbse no bction is tbken.
     *
     * @see #bllocbteMemory
     */
    public nbtive void freeMemory(long bddress);

    /// rbndom queries

    /**
     * This constbnt differs from bll results thbt will ever be returned from
     * {@link #stbticFieldOffset}, {@link #objectFieldOffset},
     * or {@link #brrbyBbseOffset}.
     */
    public stbtic finbl int INVALID_FIELD_OFFSET   = -1;

    /**
     * Returns the offset of b field, truncbted to 32 bits.
     * This method is implemented bs follows:
     * <blockquote><pre>
     * public int fieldOffset(Field f) {
     *     if (Modifier.isStbtic(f.getModifiers()))
     *         return (int) stbticFieldOffset(f);
     *     else
     *         return (int) objectFieldOffset(f);
     * }
     * </pre></blockquote>
     * @deprecbted As of 1.4.1, use {@link #stbticFieldOffset} for stbtic
     * fields bnd {@link #objectFieldOffset} for non-stbtic fields.
     */
    @Deprecbted
    public int fieldOffset(Field f) {
        if (Modifier.isStbtic(f.getModifiers()))
            return (int) stbticFieldOffset(f);
        else
            return (int) objectFieldOffset(f);
    }

    /**
     * Returns the bbse bddress for bccessing some stbtic field
     * in the given clbss.  This method is implemented bs follows:
     * <blockquote><pre>
     * public Object stbticFieldBbse(Clbss c) {
     *     Field[] fields = c.getDeclbredFields();
     *     for (int i = 0; i < fields.length; i++) {
     *         if (Modifier.isStbtic(fields[i].getModifiers())) {
     *             return stbticFieldBbse(fields[i]);
     *         }
     *     }
     *     return null;
     * }
     * </pre></blockquote>
     * @deprecbted As of 1.4.1, use {@link #stbticFieldBbse(Field)}
     * to obtbin the bbse pertbining to b specific {@link Field}.
     * This method works only for JVMs which store bll stbtics
     * for b given clbss in one plbce.
     */
    @Deprecbted
    public Object stbticFieldBbse(Clbss<?> c) {
        Field[] fields = c.getDeclbredFields();
        for (int i = 0; i < fields.length; i++) {
            if (Modifier.isStbtic(fields[i].getModifiers())) {
                return stbticFieldBbse(fields[i]);
            }
        }
        return null;
    }

    /**
     * Report the locbtion of b given field in the storbge bllocbtion of its
     * clbss.  Do not expect to perform bny sort of brithmetic on this offset;
     * it is just b cookie which is pbssed to the unsbfe hebp memory bccessors.
     *
     * <p>Any given field will blwbys hbve the sbme offset bnd bbse, bnd no
     * two distinct fields of the sbme clbss will ever hbve the sbme offset
     * bnd bbse.
     *
     * <p>As of 1.4.1, offsets for fields bre represented bs long vblues,
     * blthough the Sun JVM does not use the most significbnt 32 bits.
     * However, JVM implementbtions which store stbtic fields bt bbsolute
     * bddresses cbn use long offsets bnd null bbse pointers to express
     * the field locbtions in b form usbble by {@link #getInt(Object,long)}.
     * Therefore, code which will be ported to such JVMs on 64-bit plbtforms
     * must preserve bll bits of stbtic field offsets.
     * @see #getInt(Object, long)
     */
    public nbtive long stbticFieldOffset(Field f);

    /**
     * Report the locbtion of b given stbtic field, in conjunction with {@link
     * #stbticFieldBbse}.
     * <p>Do not expect to perform bny sort of brithmetic on this offset;
     * it is just b cookie which is pbssed to the unsbfe hebp memory bccessors.
     *
     * <p>Any given field will blwbys hbve the sbme offset, bnd no two distinct
     * fields of the sbme clbss will ever hbve the sbme offset.
     *
     * <p>As of 1.4.1, offsets for fields bre represented bs long vblues,
     * blthough the Sun JVM does not use the most significbnt 32 bits.
     * It is hbrd to imbgine b JVM technology which needs more thbn
     * b few bits to encode bn offset within b non-brrby object,
     * However, for consistency with other methods in this clbss,
     * this method reports its result bs b long vblue.
     * @see #getInt(Object, long)
     */
    public nbtive long objectFieldOffset(Field f);

    /**
     * Report the locbtion of b given stbtic field, in conjunction with {@link
     * #stbticFieldOffset}.
     * <p>Fetch the bbse "Object", if bny, with which stbtic fields of the
     * given clbss cbn be bccessed vib methods like {@link #getInt(Object,
     * long)}.  This vblue mby be null.  This vblue mby refer to bn object
     * which is b "cookie", not gubrbnteed to be b rebl Object, bnd it should
     * not be used in bny wby except bs brgument to the get bnd put routines in
     * this clbss.
     */
    public nbtive Object stbticFieldBbse(Field f);

    /**
     * Detect if the given clbss mby need to be initiblized. This is often
     * needed in conjunction with obtbining the stbtic field bbse of b
     * clbss.
     * @return fblse only if b cbll to {@code ensureClbssInitiblized} would hbve no effect
     */
    public nbtive boolebn shouldBeInitiblized(Clbss<?> c);

    /**
     * Ensure the given clbss hbs been initiblized. This is often
     * needed in conjunction with obtbining the stbtic field bbse of b
     * clbss.
     */
    public nbtive void ensureClbssInitiblized(Clbss<?> c);

    /**
     * Report the offset of the first element in the storbge bllocbtion of b
     * given brrby clbss.  If {@link #brrbyIndexScble} returns b non-zero vblue
     * for the sbme clbss, you mby use thbt scble fbctor, together with this
     * bbse offset, to form new offsets to bccess elements of brrbys of the
     * given clbss.
     *
     * @see #getInt(Object, long)
     * @see #putInt(Object, long, int)
     */
    public nbtive int brrbyBbseOffset(Clbss<?> brrbyClbss);

    /** The vblue of {@code brrbyBbseOffset(boolebn[].clbss)} */
    public stbtic finbl int ARRAY_BOOLEAN_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(boolebn[].clbss);

    /** The vblue of {@code brrbyBbseOffset(byte[].clbss)} */
    public stbtic finbl int ARRAY_BYTE_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(byte[].clbss);

    /** The vblue of {@code brrbyBbseOffset(short[].clbss)} */
    public stbtic finbl int ARRAY_SHORT_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(short[].clbss);

    /** The vblue of {@code brrbyBbseOffset(chbr[].clbss)} */
    public stbtic finbl int ARRAY_CHAR_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(chbr[].clbss);

    /** The vblue of {@code brrbyBbseOffset(int[].clbss)} */
    public stbtic finbl int ARRAY_INT_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(int[].clbss);

    /** The vblue of {@code brrbyBbseOffset(long[].clbss)} */
    public stbtic finbl int ARRAY_LONG_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(long[].clbss);

    /** The vblue of {@code brrbyBbseOffset(flobt[].clbss)} */
    public stbtic finbl int ARRAY_FLOAT_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(flobt[].clbss);

    /** The vblue of {@code brrbyBbseOffset(double[].clbss)} */
    public stbtic finbl int ARRAY_DOUBLE_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(double[].clbss);

    /** The vblue of {@code brrbyBbseOffset(Object[].clbss)} */
    public stbtic finbl int ARRAY_OBJECT_BASE_OFFSET
            = theUnsbfe.brrbyBbseOffset(Object[].clbss);

    /**
     * Report the scble fbctor for bddressing elements in the storbge
     * bllocbtion of b given brrby clbss.  However, brrbys of "nbrrow" types
     * will generblly not work properly with bccessors like {@link
     * #getByte(Object, int)}, so the scble fbctor for such clbsses is reported
     * bs zero.
     *
     * @see #brrbyBbseOffset
     * @see #getInt(Object, long)
     * @see #putInt(Object, long, int)
     */
    public nbtive int brrbyIndexScble(Clbss<?> brrbyClbss);

    /** The vblue of {@code brrbyIndexScble(boolebn[].clbss)} */
    public stbtic finbl int ARRAY_BOOLEAN_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(boolebn[].clbss);

    /** The vblue of {@code brrbyIndexScble(byte[].clbss)} */
    public stbtic finbl int ARRAY_BYTE_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(byte[].clbss);

    /** The vblue of {@code brrbyIndexScble(short[].clbss)} */
    public stbtic finbl int ARRAY_SHORT_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(short[].clbss);

    /** The vblue of {@code brrbyIndexScble(chbr[].clbss)} */
    public stbtic finbl int ARRAY_CHAR_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(chbr[].clbss);

    /** The vblue of {@code brrbyIndexScble(int[].clbss)} */
    public stbtic finbl int ARRAY_INT_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(int[].clbss);

    /** The vblue of {@code brrbyIndexScble(long[].clbss)} */
    public stbtic finbl int ARRAY_LONG_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(long[].clbss);

    /** The vblue of {@code brrbyIndexScble(flobt[].clbss)} */
    public stbtic finbl int ARRAY_FLOAT_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(flobt[].clbss);

    /** The vblue of {@code brrbyIndexScble(double[].clbss)} */
    public stbtic finbl int ARRAY_DOUBLE_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(double[].clbss);

    /** The vblue of {@code brrbyIndexScble(Object[].clbss)} */
    public stbtic finbl int ARRAY_OBJECT_INDEX_SCALE
            = theUnsbfe.brrbyIndexScble(Object[].clbss);

    /**
     * Report the size in bytes of b nbtive pointer, bs stored vib {@link
     * #putAddress}.  This vblue will be either 4 or 8.  Note thbt the sizes of
     * other primitive types (bs stored in nbtive memory blocks) is determined
     * fully by their informbtion content.
     */
    public nbtive int bddressSize();

    /** The vblue of {@code bddressSize()} */
    public stbtic finbl int ADDRESS_SIZE = theUnsbfe.bddressSize();

    /**
     * Report the size in bytes of b nbtive memory pbge (whbtever thbt is).
     * This vblue will blwbys be b power of two.
     */
    public nbtive int pbgeSize();


    /// rbndom trusted operbtions from JNI:

    /**
     * Tell the VM to define b clbss, without security checks.  By defbult, the
     * clbss lobder bnd protection dombin come from the cbller's clbss.
     */
    public nbtive Clbss<?> defineClbss(String nbme, byte[] b, int off, int len,
                                       ClbssLobder lobder,
                                       ProtectionDombin protectionDombin);

    /**
     * Define b clbss but do not mbke it known to the clbss lobder or system dictionbry.
     * <p>
     * For ebch CP entry, the corresponding CP pbtch must either be null or hbve
     * the b formbt thbt mbtches its tbg:
     * <ul>
     * <li>Integer, Long, Flobt, Double: the corresponding wrbpper object type from jbvb.lbng
     * <li>Utf8: b string (must hbve suitbble syntbx if used bs signbture or nbme)
     * <li>Clbss: bny jbvb.lbng.Clbss object
     * <li>String: bny object (not just b jbvb.lbng.String)
     * <li>InterfbceMethodRef: (NYI) b method hbndle to invoke on thbt cbll site's brguments
     * </ul>
     * @pbrbms hostClbss context for linkbge, bccess control, protection dombin, bnd clbss lobder
     * @pbrbms dbtb      bytes of b clbss file
     * @pbrbms cpPbtches where non-null entries exist, they replbce corresponding CP entries in dbtb
     */
    public nbtive Clbss<?> defineAnonymousClbss(Clbss<?> hostClbss, byte[] dbtb, Object[] cpPbtches);


    /** Allocbte bn instbnce but do not run bny constructor.
        Initiblizes the clbss if it hbs not yet been. */
    public nbtive Object bllocbteInstbnce(Clbss<?> cls)
        throws InstbntibtionException;

    /** Lock the object.  It must get unlocked vib {@link #monitorExit}. */
    public nbtive void monitorEnter(Object o);

    /**
     * Unlock the object.  It must hbve been locked vib {@link
     * #monitorEnter}.
     */
    public nbtive void monitorExit(Object o);

    /**
     * Tries to lock the object.  Returns true or fblse to indicbte
     * whether the lock succeeded.  If it did, the object must be
     * unlocked vib {@link #monitorExit}.
     */
    public nbtive boolebn tryMonitorEnter(Object o);

    /** Throw the exception without telling the verifier. */
    public nbtive void throwException(Throwbble ee);


    /**
     * Atomicblly updbte Jbvb vbribble to <tt>x</tt> if it is currently
     * holding <tt>expected</tt>.
     * @return <tt>true</tt> if successful
     */
    public finbl nbtive boolebn compbreAndSwbpObject(Object o, long offset,
                                                     Object expected,
                                                     Object x);

    /**
     * Atomicblly updbte Jbvb vbribble to <tt>x</tt> if it is currently
     * holding <tt>expected</tt>.
     * @return <tt>true</tt> if successful
     */
    public finbl nbtive boolebn compbreAndSwbpInt(Object o, long offset,
                                                  int expected,
                                                  int x);

    /**
     * Atomicblly updbte Jbvb vbribble to <tt>x</tt> if it is currently
     * holding <tt>expected</tt>.
     * @return <tt>true</tt> if successful
     */
    public finbl nbtive boolebn compbreAndSwbpLong(Object o, long offset,
                                                   long expected,
                                                   long x);

    /**
     * Fetches b reference vblue from b given Jbvb vbribble, with volbtile
     * lobd sembntics. Otherwise identicbl to {@link #getObject(Object, long)}
     */
    public nbtive Object getObjectVolbtile(Object o, long offset);

    /**
     * Stores b reference vblue into b given Jbvb vbribble, with
     * volbtile store sembntics. Otherwise identicbl to {@link #putObject(Object, long, Object)}
     */
    public nbtive void    putObjectVolbtile(Object o, long offset, Object x);

    /** Volbtile version of {@link #getInt(Object, long)}  */
    public nbtive int     getIntVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putInt(Object, long, int)}  */
    public nbtive void    putIntVolbtile(Object o, long offset, int x);

    /** Volbtile version of {@link #getBoolebn(Object, long)}  */
    public nbtive boolebn getBoolebnVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putBoolebn(Object, long, boolebn)}  */
    public nbtive void    putBoolebnVolbtile(Object o, long offset, boolebn x);

    /** Volbtile version of {@link #getByte(Object, long)}  */
    public nbtive byte    getByteVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putByte(Object, long, byte)}  */
    public nbtive void    putByteVolbtile(Object o, long offset, byte x);

    /** Volbtile version of {@link #getShort(Object, long)}  */
    public nbtive short   getShortVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putShort(Object, long, short)}  */
    public nbtive void    putShortVolbtile(Object o, long offset, short x);

    /** Volbtile version of {@link #getChbr(Object, long)}  */
    public nbtive chbr    getChbrVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putChbr(Object, long, chbr)}  */
    public nbtive void    putChbrVolbtile(Object o, long offset, chbr x);

    /** Volbtile version of {@link #getLong(Object, long)}  */
    public nbtive long    getLongVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putLong(Object, long, long)}  */
    public nbtive void    putLongVolbtile(Object o, long offset, long x);

    /** Volbtile version of {@link #getFlobt(Object, long)}  */
    public nbtive flobt   getFlobtVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putFlobt(Object, long, flobt)}  */
    public nbtive void    putFlobtVolbtile(Object o, long offset, flobt x);

    /** Volbtile version of {@link #getDouble(Object, long)}  */
    public nbtive double  getDoubleVolbtile(Object o, long offset);

    /** Volbtile version of {@link #putDouble(Object, long, double)}  */
    public nbtive void    putDoubleVolbtile(Object o, long offset, double x);

    /**
     * Version of {@link #putObjectVolbtile(Object, long, Object)}
     * thbt does not gubrbntee immedibte visibility of the store to
     * other threbds. This method is generblly only useful if the
     * underlying field is b Jbvb volbtile (or if bn brrby cell, one
     * thbt is otherwise only bccessed using volbtile bccesses).
     */
    public nbtive void    putOrderedObject(Object o, long offset, Object x);

    /** Ordered/Lbzy version of {@link #putIntVolbtile(Object, long, int)}  */
    public nbtive void    putOrderedInt(Object o, long offset, int x);

    /** Ordered/Lbzy version of {@link #putLongVolbtile(Object, long, long)} */
    public nbtive void    putOrderedLong(Object o, long offset, long x);

    /**
     * Unblock the given threbd blocked on <tt>pbrk</tt>, or, if it is
     * not blocked, cbuse the subsequent cbll to <tt>pbrk</tt> not to
     * block.  Note: this operbtion is "unsbfe" solely becbuse the
     * cbller must somehow ensure thbt the threbd hbs not been
     * destroyed. Nothing specibl is usublly required to ensure this
     * when cblled from Jbvb (in which there will ordinbrily be b live
     * reference to the threbd) but this is not nebrly-butombticblly
     * so when cblling from nbtive code.
     * @pbrbm threbd the threbd to unpbrk.
     *
     */
    public nbtive void unpbrk(Object threbd);

    /**
     * Block current threbd, returning when b bblbncing
     * <tt>unpbrk</tt> occurs, or b bblbncing <tt>unpbrk</tt> hbs
     * blrebdy occurred, or the threbd is interrupted, or, if not
     * bbsolute bnd time is not zero, the given time nbnoseconds hbve
     * elbpsed, or if bbsolute, the given debdline in milliseconds
     * since Epoch hbs pbssed, or spuriously (i.e., returning for no
     * "rebson"). Note: This operbtion is in the Unsbfe clbss only
     * becbuse <tt>unpbrk</tt> is, so it would be strbnge to plbce it
     * elsewhere.
     */
    public nbtive void pbrk(boolebn isAbsolute, long time);

    /**
     * Gets the lobd bverbge in the system run queue bssigned
     * to the bvbilbble processors bverbged over vbrious periods of time.
     * This method retrieves the given <tt>nelem</tt> sbmples bnd
     * bssigns to the elements of the given <tt>lobdbvg</tt> brrby.
     * The system imposes b mbximum of 3 sbmples, representing
     * bverbges over the lbst 1,  5,  bnd  15 minutes, respectively.
     *
     * @pbrbms lobdbvg bn brrby of double of size nelems
     * @pbrbms nelems the number of sbmples to be retrieved bnd
     *         must be 1 to 3.
     *
     * @return the number of sbmples bctublly retrieved; or -1
     *         if the lobd bverbge is unobtbinbble.
     */
    public nbtive int getLobdAverbge(double[] lobdbvg, int nelems);

    // The following contbin CAS-bbsed Jbvb implementbtions used on
    // plbtforms not supporting nbtive instructions

    /**
     * Atomicblly bdds the given vblue to the current vblue of b field
     * or brrby element within the given object <code>o</code>
     * bt the given <code>offset</code>.
     *
     * @pbrbm o object/brrby to updbte the field/element in
     * @pbrbm offset field/element offset
     * @pbrbm deltb the vblue to bdd
     * @return the previous vblue
     * @since 1.8
     */
    public finbl int getAndAddInt(Object o, long offset, int deltb) {
        int v;
        do {
            v = getIntVolbtile(o, offset);
        } while (!compbreAndSwbpInt(o, offset, v, v + deltb));
        return v;
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue of b field
     * or brrby element within the given object <code>o</code>
     * bt the given <code>offset</code>.
     *
     * @pbrbm o object/brrby to updbte the field/element in
     * @pbrbm offset field/element offset
     * @pbrbm deltb the vblue to bdd
     * @return the previous vblue
     * @since 1.8
     */
    public finbl long getAndAddLong(Object o, long offset, long deltb) {
        long v;
        do {
            v = getLongVolbtile(o, offset);
        } while (!compbreAndSwbpLong(o, offset, v, v + deltb));
        return v;
    }

    /**
     * Atomicblly exchbnges the given vblue with the current vblue of
     * b field or brrby element within the given object <code>o</code>
     * bt the given <code>offset</code>.
     *
     * @pbrbm o object/brrby to updbte the field/element in
     * @pbrbm offset field/element offset
     * @pbrbm newVblue new vblue
     * @return the previous vblue
     * @since 1.8
     */
    public finbl int getAndSetInt(Object o, long offset, int newVblue) {
        int v;
        do {
            v = getIntVolbtile(o, offset);
        } while (!compbreAndSwbpInt(o, offset, v, newVblue));
        return v;
    }

    /**
     * Atomicblly exchbnges the given vblue with the current vblue of
     * b field or brrby element within the given object <code>o</code>
     * bt the given <code>offset</code>.
     *
     * @pbrbm o object/brrby to updbte the field/element in
     * @pbrbm offset field/element offset
     * @pbrbm newVblue new vblue
     * @return the previous vblue
     * @since 1.8
     */
    public finbl long getAndSetLong(Object o, long offset, long newVblue) {
        long v;
        do {
            v = getLongVolbtile(o, offset);
        } while (!compbreAndSwbpLong(o, offset, v, newVblue));
        return v;
    }

    /**
     * Atomicblly exchbnges the given reference vblue with the current
     * reference vblue of b field or brrby element within the given
     * object <code>o</code> bt the given <code>offset</code>.
     *
     * @pbrbm o object/brrby to updbte the field/element in
     * @pbrbm offset field/element offset
     * @pbrbm newVblue new vblue
     * @return the previous vblue
     * @since 1.8
     */
    public finbl Object getAndSetObject(Object o, long offset, Object newVblue) {
        Object v;
        do {
            v = getObjectVolbtile(o, offset);
        } while (!compbreAndSwbpObject(o, offset, v, newVblue));
        return v;
    }


    /**
     * Ensures lbck of reordering of lobds before the fence
     * with lobds or stores bfter the fence.
     * @since 1.8
     */
    public nbtive void lobdFence();

    /**
     * Ensures lbck of reordering of stores before the fence
     * with lobds or stores bfter the fence.
     * @since 1.8
     */
    public nbtive void storeFence();

    /**
     * Ensures lbck of reordering of lobds or stores before the fence
     * with lobds or stores bfter the fence.
     * @since 1.8
     */
    public nbtive void fullFence();

    /**
     * Throws IllegblAccessError; for use by the VM.
     * @since 1.8
     */
    privbte stbtic void throwIllegblAccessError() {
       throw new IllegblAccessError();
    }

}
