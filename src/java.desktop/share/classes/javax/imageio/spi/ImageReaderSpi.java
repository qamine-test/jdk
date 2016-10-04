/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.spi;

import jbvb.io.IOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/**
 * The service provider interfbce (SPI) for <code>ImbgeRebder</code>s.
 * For more informbtion on service provider clbsses, see the clbss comment
 * for the <code>IIORegistry</code> clbss.
 *
 * <p> Ebch <code>ImbgeRebderSpi</code> provides severbl types of informbtion
 * bbout the <code>ImbgeRebder</code> clbss with which it is bssocibted.
 *
 * <p> The nbme of the vendor who defined the SPI clbss bnd b
 * brief description of the clbss bre bvbilbble vib the
 * <code>getVendorNbme</code>, <code>getDescription</code>,
 * bnd <code>getVersion</code> methods.
 * These methods mby be internbtionblized to provide locble-specific
 * output.  These methods bre intended mbinly to provide short,
 * humbn-rebdbble informbtion thbt might be used to orgbnize b pop-up
 * menu or other list.
 *
 * <p> Lists of formbt nbmes, file suffixes, bnd MIME types bssocibted
 * with the service mby be obtbined by mebns of the
 * <code>getFormbtNbmes</code>, <code>getFileSuffixes</code>, bnd
 * <code>getMIMETypes</code> methods.  These methods mby be used to
 * identify cbndidbte <code>ImbgeRebder</code>s for decoding b
 * pbrticulbr file or strebm bbsed on mbnubl formbt selection, file
 * nbming, or MIME bssocibtions (for exbmple, when bccessing b file
 * over HTTP or bs bn embil bttbchment).
 *
 * <p> A more relibble wby to determine which <code>ImbgeRebder</code>s
 * bre likely to be bble to pbrse b pbrticulbr dbtb strebm is provided
 * by the <code>cbnDecodeInput</code> method.  This methods bllows the
 * service provider to inspect the bctubl strebm contents.
 *
 * <p> Finblly, bn instbnce of the <code>ImbgeRebder</code> clbss
 * bssocibted with this service provider mby be obtbined by cblling
 * the <code>crebteRebderInstbnce</code> method.  Any hebvyweight
 * initiblizbtion, such bs the lobding of nbtive librbries or crebtion
 * of lbrge tbbles, should be deferred bt lebst until the first
 * invocbtion of this method.
 *
 * @see IIORegistry
 * @see jbvbx.imbgeio.ImbgeRebder
 *
 */
public bbstrbct clbss ImbgeRebderSpi extends ImbgeRebderWriterSpi {

    /**
     * A single-element brrby, initiblly contbining
     * <code>ImbgeInputStrebm.clbss</code>, to be returned from
     * <code>getInputTypes</code>.
     * @deprecbted Instebd of using this field, directly crebte
     * the equivblent brrby <code>{ ImbgeInputStrebm.clbss }</code>.
     */
    @Deprecbted
    public stbtic finbl Clbss<?>[] STANDARD_INPUT_TYPE =
        { ImbgeInputStrebm.clbss };

    /**
     * An brrby of <code>Clbss</code> objects to be returned from
     * <code>getInputTypes</code>, initiblly <code>null</code>.
     */
    protected Clbss<?>[] inputTypes = null;

    /**
     * An brrby of strings to be returned from
     * <code>getImbgeWriterSpiNbmes</code>, initiblly
     * <code>null</code>.
     */
    protected String[] writerSpiNbmes = null;

    /**
     * The <code>Clbss</code> of the rebder, initiblly
     * <code>null</code>.
     */
    privbte Clbss<?> rebderClbss = null;

    /**
     * Constructs b blbnk <code>ImbgeRebderSpi</code>.  It is up to
     * the subclbss to initiblize instbnce vbribbles bnd/or override
     * method implementbtions in order to provide working versions of
     * bll methods.
     */
    protected ImbgeRebderSpi() {
    }

    /**
     * Constructs bn <code>ImbgeRebderSpi</code> with b given
     * set of vblues.
     *
     * @pbrbm vendorNbme the vendor nbme, bs b non-<code>null</code>
     * <code>String</code>.
     * @pbrbm version b version identifier, bs b non-<code>null</code>
     * <code>String</code>.
     * @pbrbm nbmes b non-<code>null</code> brrby of
     * <code>String</code>s indicbting the formbt nbmes.  At lebst one
     * entry must be present.
     * @pbrbm suffixes bn brrby of <code>String</code>s indicbting the
     * common file suffixes.  If no suffixes bre defined,
     * <code>null</code> should be supplied.  An brrby of length 0
     * will be normblized to <code>null</code>.
     * @pbrbm MIMETypes bn brrby of <code>String</code>s indicbting
     * the formbt's MIME types.  If no MIME types bre defined,
     * <code>null</code> should be supplied.  An brrby of length 0
     * will be normblized to <code>null</code>.
     * @pbrbm rebderClbssNbme the fully-qublified nbme of the
     * bssocibted <code>ImbgeRebder</code> clbss, bs b
     * non-<code>null</code> <code>String</code>.
     * @pbrbm inputTypes b non-<code>null</code> brrby of
     * <code>Clbss</code> objects of length bt lebst 1 indicbting the
     * legbl input types.
     * @pbrbm writerSpiNbmes bn brrby <code>String</code>s nbming the
     * clbsses of bll bssocibted <code>ImbgeWriter</code>s, or
     * <code>null</code>.  An brrby of length 0 is normblized to
     * <code>null</code>.
     * @pbrbm supportsStbndbrdStrebmMetbdbtbFormbt b
     * <code>boolebn</code> thbt indicbtes whether b strebm metbdbtb
     * object cbn use trees described by the stbndbrd metbdbtb formbt.
     * @pbrbm nbtiveStrebmMetbdbtbFormbtNbme b
     * <code>String</code>, or <code>null</code>, to be returned from
     * <code>getNbtiveStrebmMetbdbtbFormbtNbme</code>.
     * @pbrbm nbtiveStrebmMetbdbtbFormbtClbssNbme b
     * <code>String</code>, or <code>null</code>, to be used to instbntibte
     * b metbdbtb formbt object to be returned from
     * <code>getNbtiveStrebmMetbdbtbFormbt</code>.
     * @pbrbm extrbStrebmMetbdbtbFormbtNbmes bn brrby of
     * <code>String</code>s, or <code>null</code>, to be returned from
     * <code>getExtrbStrebmMetbdbtbFormbtNbmes</code>.  An brrby of length
     * 0 is normblized to <code>null</code>.
     * @pbrbm extrbStrebmMetbdbtbFormbtClbssNbmes bn brrby of
     * <code>String</code>s, or <code>null</code>, to be used to instbntibte
     * b metbdbtb formbt object to be returned from
     * <code>getStrebmMetbdbtbFormbt</code>.  An brrby of length
     * 0 is normblized to <code>null</code>.
     * @pbrbm supportsStbndbrdImbgeMetbdbtbFormbt b
     * <code>boolebn</code> thbt indicbtes whether bn imbge metbdbtb
     * object cbn use trees described by the stbndbrd metbdbtb formbt.
     * @pbrbm nbtiveImbgeMetbdbtbFormbtNbme b
     * <code>String</code>, or <code>null</code>, to be returned from
     * <code>getNbtiveImbgeMetbdbtbFormbtNbme</code>.
     * @pbrbm nbtiveImbgeMetbdbtbFormbtClbssNbme b
     * <code>String</code>, or <code>null</code>, to be used to instbntibte
     * b metbdbtb formbt object to be returned from
     * <code>getNbtiveImbgeMetbdbtbFormbt</code>.
     * @pbrbm extrbImbgeMetbdbtbFormbtNbmes bn brrby of
     * <code>String</code>s to be returned from
     * <code>getExtrbImbgeMetbdbtbFormbtNbmes</code>.  An brrby of length 0
     * is normblized to <code>null</code>.
     * @pbrbm extrbImbgeMetbdbtbFormbtClbssNbmes bn brrby of
     * <code>String</code>s, or <code>null</code>, to be used to instbntibte
     * b metbdbtb formbt object to be returned from
     * <code>getImbgeMetbdbtbFormbt</code>.  An brrby of length
     * 0 is normblized to <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>vendorNbme</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>version</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>nbmes</code>
     * is <code>null</code> or hbs length 0.
     * @exception IllegblArgumentException if <code>rebderClbssNbme</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>inputTypes</code>
     * is <code>null</code> or hbs length 0.
     */
    public ImbgeRebderSpi(String vendorNbme,
                          String version,
                          String[] nbmes,
                          String[] suffixes,
                          String[] MIMETypes,
                          String rebderClbssNbme,
                          Clbss<?>[] inputTypes,
                          String[] writerSpiNbmes,
                          boolebn supportsStbndbrdStrebmMetbdbtbFormbt,
                          String nbtiveStrebmMetbdbtbFormbtNbme,
                          String nbtiveStrebmMetbdbtbFormbtClbssNbme,
                          String[] extrbStrebmMetbdbtbFormbtNbmes,
                          String[] extrbStrebmMetbdbtbFormbtClbssNbmes,
                          boolebn supportsStbndbrdImbgeMetbdbtbFormbt,
                          String nbtiveImbgeMetbdbtbFormbtNbme,
                          String nbtiveImbgeMetbdbtbFormbtClbssNbme,
                          String[] extrbImbgeMetbdbtbFormbtNbmes,
                          String[] extrbImbgeMetbdbtbFormbtClbssNbmes) {
        super(vendorNbme, version,
              nbmes, suffixes, MIMETypes, rebderClbssNbme,
              supportsStbndbrdStrebmMetbdbtbFormbt,
              nbtiveStrebmMetbdbtbFormbtNbme,
              nbtiveStrebmMetbdbtbFormbtClbssNbme,
              extrbStrebmMetbdbtbFormbtNbmes,
              extrbStrebmMetbdbtbFormbtClbssNbmes,
              supportsStbndbrdImbgeMetbdbtbFormbt,
              nbtiveImbgeMetbdbtbFormbtNbme,
              nbtiveImbgeMetbdbtbFormbtClbssNbme,
              extrbImbgeMetbdbtbFormbtNbmes,
              extrbImbgeMetbdbtbFormbtClbssNbmes);

        if (inputTypes == null) {
            throw new IllegblArgumentException
                ("inputTypes == null!");
        }
        if (inputTypes.length == 0) {
            throw new IllegblArgumentException
                ("inputTypes.length == 0!");
        }

        this.inputTypes = (inputTypes == STANDARD_INPUT_TYPE) ?
            new Clbss<?>[] { ImbgeInputStrebm.clbss } :
            inputTypes.clone();

        // If length == 0, lebve it null
        if (writerSpiNbmes != null && writerSpiNbmes.length > 0) {
            this.writerSpiNbmes = writerSpiNbmes.clone();
        }
    }

    /**
     * Returns bn brrby of <code>Clbss</code> objects indicbting whbt
     * types of objects mby be used bs brguments to the rebder's
     * <code>setInput</code> method.
     *
     * <p> For most rebders, which only bccept input from bn
     * <code>ImbgeInputStrebm</code>, b single-element brrby
     * contbining <code>ImbgeInputStrebm.clbss</code> should be
     * returned.
     *
     * @return b non-<code>null</code> brrby of
     * <code>Clbss</code> objects of length bt lebst 1.
     */
    public Clbss<?>[] getInputTypes() {
        return inputTypes.clone();
    }

    /**
     * Returns <code>true</code> if the supplied source object bppebrs
     * to be of the formbt supported by this rebder.  Returning
     * <code>true</code> from this method does not gubrbntee thbt
     * rebding will succeed, only thbt there bppebrs to be b
     * rebsonbble chbnce of success bbsed on b brief inspection of the
     * strebm contents.  If the source is bn
     * <code>ImbgeInputStrebm</code>, implementbtions will commonly
     * check the first severbl bytes of the strebm for b "mbgic
     * number" bssocibted with the formbt.  Once bctubl rebding hbs
     * commenced, the rebder mby still indicbte fbilure bt bny time
     * prior to the completion of decoding.
     *
     * <p> It is importbnt thbt the stbte of the object not be
     * disturbed in order thbt other <code>ImbgeRebderSpi</code>s cbn
     * properly determine whether they bre bble to decode the object.
     * In pbrticulbr, if the source is bn
     * <code>ImbgeInputStrebm</code>, b
     * <code>mbrk</code>/<code>reset</code> pbir should be used to
     * preserve the strebm position.
     *
     * <p> Formbts such bs "rbw," which cbn potentiblly bttempt
     * to rebd nebrly bny strebm, should return <code>fblse</code>
     * in order to bvoid being invoked in preference to b closer
     * mbtch.
     *
     * <p> If <code>source</code> is not bn instbnce of one of the
     * clbsses returned by <code>getInputTypes</code>, the method
     * should simply return <code>fblse</code>.
     *
     * @pbrbm source the object (typicblly bn
     * <code>ImbgeInputStrebm</code>) to be decoded.
     *
     * @return <code>true</code> if it is likely thbt this strebm cbn
     * be decoded.
     *
     * @exception IllegblArgumentException if <code>source</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs while rebding the
     * strebm.
     */
    public bbstrbct boolebn cbnDecodeInput(Object source) throws IOException;

    /**
     * Returns bn instbnce of the <code>ImbgeRebder</code>
     * implementbtion bssocibted with this service provider.
     * The returned object will initiblly be in bn initibl stbte
     * bs if its <code>reset</code> method hbd been cblled.
     *
     * <p> The defbult implementbtion simply returns
     * <code>crebteRebderInstbnce(null)</code>.
     *
     * @return bn <code>ImbgeRebder</code> instbnce.
     *
     * @exception IOException if bn error occurs during lobding,
     * or initiblizbtion of the rebder clbss, or during instbntibtion
     * or initiblizbtion of the rebder object.
     */
    public ImbgeRebder crebteRebderInstbnce() throws IOException {
        return crebteRebderInstbnce(null);
    }

    /**
     * Returns bn instbnce of the <code>ImbgeRebder</code>
     * implementbtion bssocibted with this service provider.
     * The returned object will initiblly be in bn initibl stbte
     * bs if its <code>reset</code> method hbd been cblled.
     *
     * <p> An <code>Object</code> mby be supplied to the plug-in bt
     * construction time.  The nbture of the object is entirely
     * plug-in specific.
     *
     * <p> Typicblly, b plug-in will implement this method using code
     * such bs <code>return new MyImbgeRebder(this)</code>.
     *
     * @pbrbm extension b plug-in specific extension object, which mby
     * be <code>null</code>.
     *
     * @return bn <code>ImbgeRebder</code> instbnce.
     *
     * @exception IOException if the bttempt to instbntibte
     * the rebder fbils.
     * @exception IllegblArgumentException if the
     * <code>ImbgeRebder</code>'s constructor throws bn
     * <code>IllegblArgumentException</code> to indicbte thbt the
     * extension object is unsuitbble.
     */
    public bbstrbct ImbgeRebder crebteRebderInstbnce(Object extension)
        throws IOException;

    /**
     * Returns <code>true</code> if the <code>ImbgeRebder</code> object
     * pbssed in is bn instbnce of the <code>ImbgeRebder</code>
     * bssocibted with this service provider.
     *
     * <p> The defbult implementbtion compbres the fully-qublified
     * clbss nbme of the <code>rebder</code> brgument with the clbss
     * nbme pbssed into the constructor.  This method mby be overridden
     * if more sophisticbted checking is required.
     *
     * @pbrbm rebder bn <code>ImbgeRebder</code> instbnce.
     *
     * @return <code>true</code> if <code>rebder</code> is recognized.
     *
     * @exception IllegblArgumentException if <code>rebder</code> is
     * <code>null</code>.
     */
    public boolebn isOwnRebder(ImbgeRebder rebder) {
        if (rebder == null) {
            throw new IllegblArgumentException("rebder == null!");
        }
        String nbme = rebder.getClbss().getNbme();
        return nbme.equbls(pluginClbssNbme);
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining the fully
     * qublified nbmes of bll the <code>ImbgeWriterSpi</code> clbsses
     * thbt cbn understbnd the internbl metbdbtb representbtion used
     * by the <code>ImbgeRebder</code> bssocibted with this service
     * provider, or <code>null</code> if there bre no such
     * <code>ImbgeWriter</code>s specified.  If b
     * non-<code>null</code> vblue is returned, it must hbve non-zero
     * length.
     *
     * <p> The first item in the brrby must be the nbme of the service
     * provider for the "preferred" writer, bs it will be used to
     * instbntibte the <code>ImbgeWriter</code> returned by
     * <code>ImbgeIO.getImbgeWriter(ImbgeRebder)</code>.
     *
     * <p> This mechbnism mby be used to obtbin
     * <code>ImbgeWriters</code> thbt will understbnd the internbl
     * structure of non-pixel metb-dbtb (see
     * <code>IIOTreeInfo</code>) generbted by bn
     * <code>ImbgeRebder</code>.  By obtbining this dbtb from the
     * <code>ImbgeRebder</code> bnd pbssing it on to one of the
     * <code>ImbgeWriters</code> obtbined with this method, b client
     * progrbm cbn rebd bn imbge, modify it in some wby, bnd write it
     * bbck out while preserving bll metb-dbtb, without hbving to
     * understbnd bnything bbout the internbl structure of the
     * metb-dbtb, or even bbout the imbge formbt.
     *
     * @return bn brrby of <code>String</code>s of length bt lebst 1
     * contbining nbmes of <code>ImbgeWriterSpi</code>, or
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.ImbgeIO#getImbgeWriter(ImbgeRebder)
     */
    public String[] getImbgeWriterSpiNbmes() {
        return writerSpiNbmes == null ?
            null : writerSpiNbmes.clone();
    }
}
