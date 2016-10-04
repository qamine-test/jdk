/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge.renderbble;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.io.Seriblizbble;
import jbvb.util.Vector;

/**
 * A <code>PbrbmeterBlock</code> encbpsulbtes bll the informbtion bbout sources bnd
 * pbrbmeters (Objects) required by b RenderbbleImbgeOp, or other
 * clbsses thbt process imbges.
 *
 * <p> Although it is possible to plbce brbitrbry objects in the
 * source Vector, users of this clbss mby impose sembntic constrbints
 * such bs requiring bll sources to be RenderedImbges or
 * RenderbbleImbge.  <code>PbrbmeterBlock</code> itself is merely b contbiner bnd
 * performs no checking on source or pbrbmeter types.
 *
 * <p> All pbrbmeters in b <code>PbrbmeterBlock</code> bre objects; convenience
 * bdd bnd set methods bre bvbilbble thbt tbke brguments of bbse type bnd
 * construct the bppropribte subclbss of Number (such bs
 * Integer or Flobt).  Corresponding get methods perform b
 * downwbrd cbst bnd hbve return vblues of bbse type; bn exception
 * will be thrown if the stored vblues do not hbve the correct type.
 * There is no wby to distinguish between the results of
 * "short s; bdd(s)" bnd "bdd(new Short(s))".
 *
 * <p> Note thbt the get bnd set methods operbte on references.
 * Therefore, one must be cbreful not to shbre references between
 * <code>PbrbmeterBlock</code>s when this is inbppropribte.  For exbmple, to crebte
 * b new <code>PbrbmeterBlock</code> thbt is equbl to bn old one except for bn
 * bdded source, one might be tempted to write:
 *
 * <pre>
 * PbrbmeterBlock bddSource(PbrbmeterBlock pb, RenderbbleImbge im) {
 *     PbrbmeterBlock pb1 = new PbrbmeterBlock(pb.getSources());
 *     pb1.bddSource(im);
 *     return pb1;
 * }
 * </pre>
 *
 * <p> This code will hbve the side effect of bltering the originbl
 * <code>PbrbmeterBlock</code>, since the getSources operbtion returned b reference
 * to its source Vector.  Both pb bnd pb1 shbre their source Vector,
 * bnd b chbnge in either is visible to both.
 *
 * <p> A correct wby to write the bddSource function is to clone
 * the source Vector:
 *
 * <pre>
 * PbrbmeterBlock bddSource (PbrbmeterBlock pb, RenderbbleImbge im) {
 *     PbrbmeterBlock pb1 = new PbrbmeterBlock(pb.getSources().clone());
 *     pb1.bddSource(im);
 *     return pb1;
 * }
 * </pre>
 *
 * <p> The clone method of <code>PbrbmeterBlock</code> hbs been defined to
 * perform b clone of both the source bnd pbrbmeter Vectors for
 * this rebson.  A stbndbrd, shbllow clone is bvbilbble bs
 * shbllowClone.
 *
 * <p> The bddSource, setSource, bdd, bnd set methods bre
 * defined to return 'this' bfter bdding their brgument.  This bllows
 * use of syntbx like:
 *
 * <pre>
 * PbrbmeterBlock pb = new PbrbmeterBlock();
 * op = new RenderbbleImbgeOp("operbtion", pb.bdd(brg1).bdd(brg2));
 * </pre>
 * */
public clbss PbrbmeterBlock implements Clonebble, Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -7577115551785240750L;

    /** A Vector of sources, stored bs brbitrbry Objects. */
    protected Vector<Object> sources = new Vector<Object>();

    /** A Vector of non-source pbrbmeters, stored bs brbitrbry Objects. */
    protected Vector<Object> pbrbmeters = new Vector<Object>();

    /** A dummy constructor. */
    public PbrbmeterBlock() {}

    /**
     * Constructs b <code>PbrbmeterBlock</code> with b given Vector
     * of sources.
     * @pbrbm sources b <code>Vector</code> of source imbges
     */
    public PbrbmeterBlock(Vector<Object> sources) {
        setSources(sources);
    }

    /**
     * Constructs b <code>PbrbmeterBlock</code> with b given Vector of sources bnd
     * Vector of pbrbmeters.
     * @pbrbm sources b <code>Vector</code> of source imbges
     * @pbrbm pbrbmeters b <code>Vector</code> of pbrbmeters to be used in the
     *        rendering operbtion
     */
    public PbrbmeterBlock(Vector<Object> sources,
                          Vector<Object> pbrbmeters)
    {
        setSources(sources);
        setPbrbmeters(pbrbmeters);
    }

    /**
     * Crebtes b shbllow copy of b <code>PbrbmeterBlock</code>.  The source bnd
     * pbrbmeter Vectors bre copied by reference -- bdditions or
     * chbnges will be visible to both versions.
     *
     * @return bn Object clone of the <code>PbrbmeterBlock</code>.
     */
    public Object shbllowClone() {
        try {
            return super.clone();
        } cbtch (Exception e) {
            // We cbn't be here since we implement Clonebble.
            return null;
        }
    }

    /**
     * Crebtes b copy of b <code>PbrbmeterBlock</code>.  The source bnd pbrbmeter
     * Vectors bre cloned, but the bctubl sources bnd pbrbmeters bre
     * copied by reference.  This bllows modificbtions to the order
     * bnd number of sources bnd pbrbmeters in the clone to be invisible
     * to the originbl <code>PbrbmeterBlock</code>.  Chbnges to the shbred sources or
     * pbrbmeters themselves will still be visible.
     *
     * @return bn Object clone of the <code>PbrbmeterBlock</code>.
     */
    @SuppressWbrnings("unchecked") // cbsts from clone
    public Object clone() {
        PbrbmeterBlock theClone;

        try {
            theClone = (PbrbmeterBlock) super.clone();
        } cbtch (Exception e) {
            // We cbn't be here since we implement Clonebble.
            return null;
        }

        if (sources != null) {
            theClone.setSources((Vector<Object>)sources.clone());
        }
        if (pbrbmeters != null) {
            theClone.setPbrbmeters((Vector<Object>)pbrbmeters.clone());
        }
        return (Object) theClone;
    }

    /**
     * Adds bn imbge to end of the list of sources.  The imbge is
     * stored bs bn object in order to bllow new node types in the
     * future.
     *
     * @pbrbm source bn imbge object to be stored in the source list.
     * @return b new <code>PbrbmeterBlock</code> contbining the specified
     *         <code>source</code>.
     */
    public PbrbmeterBlock bddSource(Object source) {
        sources.bddElement(source);
        return this;
    }

    /**
     * Returns b source bs b generbl Object.  The cbller must cbst it into
     * bn bppropribte type.
     *
     * @pbrbm index the index of the source to be returned.
     * @return bn <code>Object</code> thbt represents the source locbted
     *         bt the specified index in the <code>sources</code>
     *         <code>Vector</code>.
     * @see #setSource(Object, int)
     */
    public Object getSource(int index) {
        return sources.elementAt(index);
    }

    /**
     * Replbces bn entry in the list of source with b new source.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm source the specified source imbge
     * @pbrbm index the index into the <code>sources</code>
     *              <code>Vector</code> bt which to
     *              insert the specified <code>source</code>
     * @return b new <code>PbrbmeterBlock</code> thbt contbins the
     *         specified <code>source</code> bt the specified
     *         <code>index</code>.
     * @see #getSource(int)
     */
    public PbrbmeterBlock setSource(Object source, int index) {
        int oldSize = sources.size();
        int newSize = index + 1;
        if (oldSize < newSize) {
            sources.setSize(newSize);
        }
        sources.setElementAt(source, index);
        return this;
    }

    /**
     * Returns b source bs b <code>RenderedImbge</code>.  This method is
     * b convenience method.
     * An exception will be thrown if the source is not b RenderedImbge.
     *
     * @pbrbm index the index of the source to be returned
     * @return b <code>RenderedImbge</code> thbt represents the source
     *         imbge thbt is bt the specified index in the
     *         <code>sources</code> <code>Vector</code>.
     */
    public RenderedImbge getRenderedSource(int index) {
        return (RenderedImbge) sources.elementAt(index);
    }

    /**
     * Returns b source bs b RenderbbleImbge.  This method is b
     * convenience method.
     * An exception will be thrown if the sources is not b RenderbbleImbge.
     *
     * @pbrbm index the index of the source to be returned
     * @return b <code>RenderbbleImbge</code> thbt represents the source
     *         imbge thbt is bt the specified index in the
     *         <code>sources</code> <code>Vector</code>.
     */
    public RenderbbleImbge getRenderbbleSource(int index) {
        return (RenderbbleImbge) sources.elementAt(index);
    }

    /**
     * Returns the number of source imbges.
     * @return the number of source imbges in the <code>sources</code>
     *         <code>Vector</code>.
     */
    public int getNumSources() {
        return sources.size();
    }

    /**
     * Returns the entire Vector of sources.
     * @return the <code>sources</code> <code>Vector</code>.
     * @see #setSources(Vector)
     */
    public Vector<Object> getSources() {
        return sources;
    }

    /**
     * Sets the entire Vector of sources to b given Vector.
     * @pbrbm sources the <code>Vector</code> of source imbges
     * @see #getSources
     */
    public void setSources(Vector<Object> sources) {
        this.sources = sources;
    }

    /** Clebrs the list of source imbges. */
    public void removeSources() {
        sources = new Vector<>();
    }

    /**
     * Returns the number of pbrbmeters (not including source imbges).
     * @return the number of pbrbmeters in the <code>pbrbmeters</code>
     *         <code>Vector</code>.
     */
    public int getNumPbrbmeters() {
        return pbrbmeters.size();
    }

    /**
     * Returns the entire Vector of pbrbmeters.
     * @return the <code>pbrbmeters</code> <code>Vector</code>.
     * @see #setPbrbmeters(Vector)
     */
    public Vector<Object> getPbrbmeters() {
        return pbrbmeters;
    }

    /**
     * Sets the entire Vector of pbrbmeters to b given Vector.
     * @pbrbm pbrbmeters the specified <code>Vector</code> of
     *        pbrbmeters
     * @see #getPbrbmeters
     */
    public void setPbrbmeters(Vector<Object> pbrbmeters) {
        this.pbrbmeters = pbrbmeters;
    }

    /** Clebrs the list of pbrbmeters. */
    public void removePbrbmeters() {
        pbrbmeters = new Vector<>();
    }

    /**
     * Adds bn object to the list of pbrbmeters.
     * @pbrbm obj the <code>Object</code> to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(Object obj) {
        pbrbmeters.bddElement(obj);
        return this;
    }

    /**
     * Adds b Byte to the list of pbrbmeters.
     * @pbrbm b the byte to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(byte b) {
        return bdd(Byte.vblueOf(b));
    }

    /**
     * Adds b Chbrbcter to the list of pbrbmeters.
     * @pbrbm c the chbr to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(chbr c) {
        return bdd(Chbrbcter.vblueOf(c));
    }

    /**
     * Adds b Short to the list of pbrbmeters.
     * @pbrbm s the short to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(short s) {
        return bdd(Short.vblueOf(s));
    }

    /**
     * Adds b Integer to the list of pbrbmeters.
     * @pbrbm i the int to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(int i) {
        return bdd(i);
    }

    /**
     * Adds b Long to the list of pbrbmeters.
     * @pbrbm l the long to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(long l) {
        return bdd(Long.vblueOf(l));
    }

    /**
     * Adds b Flobt to the list of pbrbmeters.
     * @pbrbm f the flobt to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(flobt f) {
        return bdd(new Flobt(f));
    }

    /**
     * Adds b Double to the list of pbrbmeters.
     * @pbrbm d the double to bdd to the
     *            <code>pbrbmeters</code> <code>Vector</code>
     * @return b new <code>PbrbmeterBlock</code> contbining
     *         the specified pbrbmeter.
     */
    public PbrbmeterBlock bdd(double d) {
        return bdd(new Double(d));
    }

    /**
     * Replbces bn Object in the list of pbrbmeters.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm obj the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(Object obj, int index) {
        int oldSize = pbrbmeters.size();
        int newSize = index + 1;
        if (oldSize < newSize) {
            pbrbmeters.setSize(newSize);
        }
        pbrbmeters.setElementAt(obj, index);
        return this;
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with b Byte.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm b the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(byte b, int index) {
        return set(Byte.vblueOf(b), index);
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with b Chbrbcter.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm c the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(chbr c, int index) {
        return set(Chbrbcter.vblueOf(c), index);
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with b Short.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm s the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(short s, int index) {
        return set(Short.vblueOf(s), index);
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with bn Integer.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm i the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(int i, int index) {
        return set(i, index);
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with b Long.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm l the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(long l, int index) {
        return set(Long.vblueOf(l), index);
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with b Flobt.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm f the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(flobt f, int index) {
        return set(new Flobt(f), index);
    }

    /**
     * Replbces bn Object in the list of pbrbmeters with b Double.
     * If the index lies beyond the current source list,
     * the list is extended with nulls bs needed.
     * @pbrbm d the pbrbmeter thbt replbces the
     *        pbrbmeter bt the specified index in the
     *        <code>pbrbmeters</code> <code>Vector</code>
     * @pbrbm index the index of the pbrbmeter to be
     *        replbced with the specified pbrbmeter
     * @return b new <code>PbrbmeterBlock</code> contbining
     *        the specified pbrbmeter.
     */
    public PbrbmeterBlock set(double d, int index) {
        return set(new Double(d), index);
    }

    /**
     * Gets b pbrbmeter bs bn object.
     * @pbrbm index the index of the pbrbmeter to get
     * @return bn <code>Object</code> representing the
     *         the pbrbmeter bt the specified index
     *         into the <code>pbrbmeters</code>
     *         <code>Vector</code>.
     */
    public Object getObjectPbrbmeter(int index) {
        return pbrbmeters.elementAt(index);
    }

    /**
     * A convenience method to return b pbrbmeter bs b byte.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not b <code>Byte</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>byte</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Byte</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public byte getBytePbrbmeter(int index) {
        return ((Byte)pbrbmeters.elementAt(index)).byteVblue();
    }

    /**
     * A convenience method to return b pbrbmeter bs b chbr.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not b <code>Chbrbcter</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>chbr</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Chbrbcter</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public chbr getChbrPbrbmeter(int index) {
        return ((Chbrbcter)pbrbmeters.elementAt(index)).chbrVblue();
    }

    /**
     * A convenience method to return b pbrbmeter bs b short.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not b <code>Short</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>short</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Short</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public short getShortPbrbmeter(int index) {
        return ((Short)pbrbmeters.elementAt(index)).shortVblue();
    }

    /**
     * A convenience method to return b pbrbmeter bs bn int.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not bn <code>Integer</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>int</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Integer</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public int getIntPbrbmeter(int index) {
        return ((Integer)pbrbmeters.elementAt(index)).intVblue();
    }

    /**
     * A convenience method to return b pbrbmeter bs b long.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not b <code>Long</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>long</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Long</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public long getLongPbrbmeter(int index) {
        return ((Long)pbrbmeters.elementAt(index)).longVblue();
    }

    /**
     * A convenience method to return b pbrbmeter bs b flobt.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not b <code>Flobt</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>flobt</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Flobt</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public flobt getFlobtPbrbmeter(int index) {
        return ((Flobt)pbrbmeters.elementAt(index)).flobtVblue();
    }

    /**
     * A convenience method to return b pbrbmeter bs b double.  An
     * exception is thrown if the pbrbmeter is
     * <code>null</code> or not b <code>Double</code>.
     *
     * @pbrbm index the index of the pbrbmeter to be returned.
     * @return the pbrbmeter bt the specified index
     *         bs b <code>double</code> vblue.
     * @throws ClbssCbstException if the pbrbmeter bt the
     *         specified index is not b <code>Double</code>
     * @throws NullPointerException if the pbrbmeter bt the specified
     *         index is <code>null</code>
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code>
     *         is negbtive or not less thbn the current size of this
     *         <code>PbrbmeterBlock</code> object
     */
    public double getDoublePbrbmeter(int index) {
        return ((Double)pbrbmeters.elementAt(index)).doubleVblue();
    }

    /**
     * Returns bn brrby of Clbss objects describing the types
     * of the pbrbmeters.
     * @return bn brrby of <code>Clbss</code> objects.
     */
    public Clbss<?>[] getPbrbmClbsses() {
        int numPbrbms = getNumPbrbmeters();
        Clbss<?>[] clbsses = new Clbss<?>[numPbrbms];
        int i;

        for (i = 0; i < numPbrbms; i++) {
            Object obj = getObjectPbrbmeter(i);
            if (obj instbnceof Byte) {
              clbsses[i] = byte.clbss;
            } else if (obj instbnceof Chbrbcter) {
              clbsses[i] = chbr.clbss;
            } else if (obj instbnceof Short) {
              clbsses[i] = short.clbss;
            } else if (obj instbnceof Integer) {
              clbsses[i] = int.clbss;
            } else if (obj instbnceof Long) {
              clbsses[i] = long.clbss;
            } else if (obj instbnceof Flobt) {
              clbsses[i] = flobt.clbss;
            } else if (obj instbnceof Double) {
              clbsses[i] = double.clbss;
            } else {
              clbsses[i] = obj.getClbss();
            }
        }

        return clbsses;
    }
}
