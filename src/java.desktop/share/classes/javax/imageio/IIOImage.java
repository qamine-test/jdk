/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.util.List;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;

/**
 * A simple contbiner clbss to bggregbte bn imbge, b set of
 * thumbnbil (preview) imbges, bnd bn object representing metbdbtb
 * bssocibted with the imbge.
 *
 * <p> The imbge dbtb mby tbke the form of either b
 * <code>RenderedImbge</code>, or b <code>Rbster</code>.  Rebder
 * methods thbt return bn <code>IIOImbge</code> will blwbys return b
 * <code>BufferedImbge</code> using the <code>RenderedImbge</code>
 * reference.  Writer methods thbt bccept bn <code>IIOImbge</code>
 * will blwbys bccept b <code>RenderedImbge</code>, bnd mby optionblly
 * bccept b <code>Rbster</code>.
 *
 * <p> Exbctly one of <code>getRenderedImbge</code> bnd
 * <code>getRbster</code> will return b non-<code>null</code> vblue.
 * Subclbsses bre responsible for ensuring this behbvior.
 *
 * @see ImbgeRebder#rebdAll(int, ImbgeRebdPbrbm)
 * @see ImbgeRebder#rebdAll(jbvb.util.Iterbtor)
 * @see ImbgeWriter#write(jbvbx.imbgeio.metbdbtb.IIOMetbdbtb,
 *                        IIOImbge, ImbgeWritePbrbm)
 * @see ImbgeWriter#write(IIOImbge)
 * @see ImbgeWriter#writeToSequence(IIOImbge, ImbgeWritePbrbm)
 * @see ImbgeWriter#writeInsert(int, IIOImbge, ImbgeWritePbrbm)
 *
 */
public clbss IIOImbge {

    /**
     * The <code>RenderedImbge</code> being referenced.
     */
    protected RenderedImbge imbge;

    /**
     * The <code>Rbster</code> being referenced.
     */
    protected Rbster rbster;

    /**
     * A <code>List</code> of <code>BufferedImbge</code> thumbnbils,
     * or <code>null</code>.  Non-<code>BufferedImbge</code> objects
     * must not be stored in this <code>List</code>.
     */
    protected List<? extends BufferedImbge> thumbnbils = null;

    /**
     * An <code>IIOMetbdbtb</code> object contbining metbdbtb
     * bssocibted with the imbge.
     */
    protected IIOMetbdbtb metbdbtb;

    /**
     * Constructs bn <code>IIOImbge</code> contbining b
     * <code>RenderedImbge</code>, bnd thumbnbils bnd metbdbtb
     * bssocibted with it.
     *
     * <p> All pbrbmeters bre stored by reference.
     *
     * <p> The <code>thumbnbils</code> brgument must either be
     * <code>null</code> or contbin only <code>BufferedImbge</code>
     * objects.
     *
     * @pbrbm imbge b <code>RenderedImbge</code>.
     * @pbrbm thumbnbils b <code>List</code> of <code>BufferedImbge</code>s,
     * or <code>null</code>.
     * @pbrbm metbdbtb bn <code>IIOMetbdbtb</code> object, or
     * <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>imbge</code> is
     * <code>null</code>.
     */
    public IIOImbge(RenderedImbge imbge,
                    List<? extends BufferedImbge> thumbnbils,
                    IIOMetbdbtb metbdbtb) {
        if (imbge == null) {
            throw new IllegblArgumentException("imbge == null!");
        }
        this.imbge = imbge;
        this.rbster = null;
        this.thumbnbils = thumbnbils;
        this.metbdbtb = metbdbtb;
    }

    /**
     * Constructs bn <code>IIOImbge</code> contbining b
     * <code>Rbster</code>, bnd thumbnbils bnd metbdbtb
     * bssocibted with it.
     *
     * <p> All pbrbmeters bre stored by reference.
     *
     * @pbrbm rbster b <code>Rbster</code>.
     * @pbrbm thumbnbils b <code>List</code> of <code>BufferedImbge</code>s,
     * or <code>null</code>.
     * @pbrbm metbdbtb bn <code>IIOMetbdbtb</code> object, or
     * <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>rbster</code> is
     * <code>null</code>.
     */
    public IIOImbge(Rbster rbster,
                    List<? extends BufferedImbge> thumbnbils,
                    IIOMetbdbtb metbdbtb) {
        if (rbster == null) {
            throw new IllegblArgumentException("rbster == null!");
        }
        this.rbster = rbster;
        this.imbge = null;
        this.thumbnbils = thumbnbils;
        this.metbdbtb = metbdbtb;
    }

    /**
     * Returns the currently set <code>RenderedImbge</code>, or
     * <code>null</code> if only b <code>Rbster</code> is bvbilbble.
     *
     * @return b <code>RenderedImbge</code>, or <code>null</code>.
     *
     * @see #setRenderedImbge
     */
    public RenderedImbge getRenderedImbge() {
        synchronized(this) {
            return imbge;
        }
    }

    /**
     * Sets the current <code>RenderedImbge</code>.  The vblue is
     * stored by reference.  Any existing <code>Rbster</code> is
     * discbrded.
     *
     * @pbrbm imbge b <code>RenderedImbge</code>.
     *
     * @exception IllegblArgumentException if <code>imbge</code> is
     * <code>null</code>.
     *
     * @see #getRenderedImbge
     */
    public void setRenderedImbge(RenderedImbge imbge) {
        synchronized(this) {
            if (imbge == null) {
                throw new IllegblArgumentException("imbge == null!");
            }
            this.imbge = imbge;
            this.rbster = null;
        }
    }

    /**
     * Returns <code>true</code> if this <code>IIOImbge</code> stores
     * b <code>Rbster</code> rbther thbn b <code>RenderedImbge</code>.
     *
     * @return <code>true</code> if b <code>Rbster</code> is
     * bvbilbble.
     */
    public boolebn hbsRbster() {
        synchronized(this) {
            return (rbster != null);
        }
    }

    /**
     * Returns the currently set <code>Rbster</code>, or
     * <code>null</code> if only b <code>RenderedImbge</code> is
     * bvbilbble.
     *
     * @return b <code>Rbster</code>, or <code>null</code>.
     *
     * @see #setRbster
     */
    public Rbster getRbster() {
        synchronized(this) {
            return rbster;
        }
    }

    /**
     * Sets the current <code>Rbster</code>.  The vblue is
     * stored by reference.  Any existing <code>RenderedImbge</code> is
     * discbrded.
     *
     * @pbrbm rbster b <code>Rbster</code>.
     *
     * @exception IllegblArgumentException if <code>rbster</code> is
     * <code>null</code>.
     *
     * @see #getRbster
     */
    public void setRbster(Rbster rbster) {
        synchronized(this) {
            if (rbster == null) {
                throw new IllegblArgumentException("rbster == null!");
            }
            this.rbster = rbster;
            this.imbge = null;
        }
    }

    /**
     * Returns the number of thumbnbils stored in this
     * <code>IIOImbge</code>.
     *
     * @return the number of thumbnbils, bs bn <code>int</code>.
     */
    public int getNumThumbnbils() {
        return thumbnbils == null ? 0 : thumbnbils.size();
    }

    /**
     * Returns b thumbnbil bssocibted with the mbin imbge.
     *
     * @pbrbm index the index of the desired thumbnbil imbge.
     *
     * @return b thumbnbil imbge, bs b <code>BufferedImbge</code>.
     *
     * @exception IndexOutOfBoundsException if the supplied index is
     * negbtive or lbrger thbn the lbrgest vblid index.
     * @exception ClbssCbstException if b
     * non-<code>BufferedImbge</code> object is encountered in the
     * list of thumbnbils bt the given index.
     *
     * @see #getThumbnbils
     * @see #setThumbnbils
     */
    public BufferedImbge getThumbnbil(int index) {
        if (thumbnbils == null) {
            throw new IndexOutOfBoundsException("No thumbnbils bvbilbble!");
        }
        return (BufferedImbge)thumbnbils.get(index);
    }

    /**
     * Returns the current <code>List</code> of thumbnbil
     * <code>BufferedImbge</code>s, or <code>null</code> if none is
     * set.  A live reference is returned.
     *
     * @return the current <code>List</code> of
     * <code>BufferedImbge</code> thumbnbils, or <code>null</code>.
     *
     * @see #getThumbnbil(int)
     * @see #setThumbnbils
     */
    public List<? extends BufferedImbge> getThumbnbils() {
        return thumbnbils;
    }

    /**
     * Sets the list of thumbnbils to b new <code>List</code> of
     * <code>BufferedImbge</code>s, or to <code>null</code>.  The
     * reference to the previous <code>List</code> is discbrded.
     *
     * <p> The <code>thumbnbils</code> brgument must either be
     * <code>null</code> or contbin only <code>BufferedImbge</code>
     * objects.
     *
     * @pbrbm thumbnbils b <code>List</code> of
     * <code>BufferedImbge</code> thumbnbils, or <code>null</code>.
     *
     * @see #getThumbnbil(int)
     * @see #getThumbnbils
     */
    public void setThumbnbils(List<? extends BufferedImbge> thumbnbils) {
        this.thumbnbils = thumbnbils;
    }

    /**
     * Returns b reference to the current <code>IIOMetbdbtb</code>
     * object, or <code>null</code> is none is set.
     *
     * @return bn <code>IIOMetbdbtb</code> object, or <code>null</code>.
     *
     * @see #setMetbdbtb
     */
    public IIOMetbdbtb getMetbdbtb() {
        return metbdbtb;
    }

    /**
     * Sets the <code>IIOMetbdbtb</code> to b new object, or
     * <code>null</code>.
     *
     * @pbrbm metbdbtb bn <code>IIOMetbdbtb</code> object, or
     * <code>null</code>.
     *
     * @see #getMetbdbtb
     */
    public void setMetbdbtb(IIOMetbdbtb metbdbtb) {
        this.metbdbtb = metbdbtb;
    }
}
