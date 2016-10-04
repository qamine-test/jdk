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

import jbvb.bwt.imbge.RenderedImbge;
import jbvb.io.IOException;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

/**
 * The service provider interfbce (SPI) for <code>ImbgeWriter</code>s.
 * For more informbtion on service provider clbsses, see the clbss comment
 * for the <code>IIORegistry</code> clbss.
 *
 * <p> Ebch <code>ImbgeWriterSpi</code> provides severbl types of informbtion
 * bbout the <code>ImbgeWriter</code> clbss with which it is bssocibted.
 *
 * <p> The nbme of the vendor who defined the SPI clbss bnd b
 * brief description of the clbss bre bvbilbble vib the
 * <code>getVendorNbme</code>, <code>getDescription</code>,
 * bnd <code>getVersion</code> methods.
 * These methods mby be internbtionblized to provide locble-specific
 * output.  These methods bre intended mbinly to provide short,
 * humbn-writbble informbtion thbt might be used to orgbnize b pop-up
 * menu or other list.
 *
 * <p> Lists of formbt nbmes, file suffixes, bnd MIME types bssocibted
 * with the service mby be obtbined by mebns of the
 * <code>getFormbtNbmes</code>, <code>getFileSuffixes</code>, bnd
 * <code>getMIMEType</code> methods.  These methods mby be used to
 * identify cbndidbte <code>ImbgeWriter</code>s for writing b
 * pbrticulbr file or strebm bbsed on mbnubl formbt selection, file
 * nbming, or MIME bssocibtions.
 *
 * <p> A more relibble wby to determine which <code>ImbgeWriter</code>s
 * bre likely to be bble to pbrse b pbrticulbr dbtb strebm is provided
 * by the <code>cbnEncodeImbge</code> method.  This methods bllows the
 * service provider to inspect the bctubl imbge contents.
 *
 * <p> Finblly, bn instbnce of the <code>ImbgeWriter</code> clbss
 * bssocibted with this service provider mby be obtbined by cblling
 * the <code>crebteWriterInstbnce</code> method.  Any hebvyweight
 * initiblizbtion, such bs the lobding of nbtive librbries or crebtion
 * of lbrge tbbles, should be deferred bt lebst until the first
 * invocbtion of this method.
 *
 * @see IIORegistry
 * @see jbvbx.imbgeio.ImbgeTypeSpecifier
 * @see jbvbx.imbgeio.ImbgeWriter
 *
 */
public bbstrbct clbss ImbgeWriterSpi extends ImbgeRebderWriterSpi {

    /**
     * A single-element brrby, initiblly contbining
     * <code>ImbgeOutputStrebm.clbss</code>, to be returned from
     * <code>getOutputTypes</code>.
     * @deprecbted Instebd of using this field, directly crebte
     * the equivblent brrby <code>{ ImbgeOutputStrebm.clbss }</code>.
     */
    @Deprecbted
    public stbtic finbl Clbss<?>[] STANDARD_OUTPUT_TYPE =
        { ImbgeOutputStrebm.clbss };

    /**
     * An brrby of <code>Clbss</code> objects to be returned from
     * <code>getOutputTypes</code>, initiblly <code>null</code>.
     */
    protected Clbss<?>[] outputTypes = null;

    /**
     * An brrby of strings to be returned from
     * <code>getImbgeRebderSpiNbmes</code>, initiblly
     * <code>null</code>.
     */
    protected String[] rebderSpiNbmes = null;

    /**
     * The <code>Clbss</code> of the writer, initiblly
     * <code>null</code>.
     */
    privbte Clbss<?> writerClbss = null;

    /**
     * Constructs b blbnk <code>ImbgeWriterSpi</code>.  It is up to
     * the subclbss to initiblize instbnce vbribbles bnd/or override
     * method implementbtions in order to provide working versions of
     * bll methods.
     */
    protected ImbgeWriterSpi() {
    }

    /**
     * Constructs bn <code>ImbgeWriterSpi</code> with b given
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
     * the formbt's MIME types.  If no suffixes bre defined,
     * <code>null</code> should be supplied.  An brrby of length 0
     * will be normblized to <code>null</code>.
     * @pbrbm writerClbssNbme the fully-qublified nbme of the
     * bssocibted <code>ImbgeWriterSpi</code> clbss, bs b
     * non-<code>null</code> <code>String</code>.
     * @pbrbm outputTypes bn brrby of <code>Clbss</code> objects of
     * length bt lebst 1 indicbting the legbl output types.
     * @pbrbm rebderSpiNbmes bn brrby <code>String</code>s of length
     * bt lebst 1 nbming the clbsses of bll bssocibted
     * <code>ImbgeRebder</code>s, or <code>null</code>.  An brrby of
     * length 0 is normblized to <code>null</code>.
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
     * @exception IllegblArgumentException if <code>writerClbssNbme</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>outputTypes</code>
     * is <code>null</code> or hbs length 0.
     */
    public ImbgeWriterSpi(String vendorNbme,
                          String version,
                          String[] nbmes,
                          String[] suffixes,
                          String[] MIMETypes,
                          String writerClbssNbme,
                          Clbss<?>[] outputTypes,
                          String[] rebderSpiNbmes,
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
              nbmes, suffixes, MIMETypes, writerClbssNbme,
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

        if (outputTypes == null) {
            throw new IllegblArgumentException
                ("outputTypes == null!");
        }
        if (outputTypes.length == 0) {
            throw new IllegblArgumentException
                ("outputTypes.length == 0!");
        }

        this.outputTypes = (outputTypes == STANDARD_OUTPUT_TYPE) ?
            new Clbss<?>[] { ImbgeOutputStrebm.clbss } :
            outputTypes.clone();

        // If length == 0, lebve it null
        if (rebderSpiNbmes != null && rebderSpiNbmes.length > 0) {
            this.rebderSpiNbmes = rebderSpiNbmes.clone();
        }
    }

    /**
     * Returns <code>true</code> if the formbt thbt this writer
     * outputs preserves pixel dbtb bit-bccurbtely.  The defbult
     * implementbtion returns <code>true</code>.
     *
     * @return <code>true</code> if the formbt preserves full pixel
     * bccurbcy.
     */
    public boolebn isFormbtLossless() {
        return true;
    }

    /**
     * Returns bn brrby of <code>Clbss</code> objects indicbting whbt
     * types of objects mby be used bs brguments to the writer's
     * <code>setOutput</code> method.
     *
     * <p> For most writers, which only output to bn
     * <code>ImbgeOutputStrebm</code>, b single-element brrby
     * contbining <code>ImbgeOutputStrebm.clbss</code> should be
     * returned.
     *
     * @return b non-<code>null</code> brrby of
     * <code>Clbss</code> objects of length bt lebst 1.
     */
    public Clbss<?>[] getOutputTypes() {
        return outputTypes.clone();
    }

    /**
     * Returns <code>true</code> if the <code>ImbgeWriter</code>
     * implementbtion bssocibted with this service provider is bble to
     * encode bn imbge with the given lbyout.  The lbyout
     * (<i>i.e.</i>, the imbge's <code>SbmpleModel</code> bnd
     * <code>ColorModel</code>) is described by bn
     * <code>ImbgeTypeSpecifier</code> object.
     *
     * <p> A return vblue of <code>true</code> is not bn bbsolute
     * gubrbntee of successful encoding; the encoding process mby still
     * produce errors due to fbctors such bs I/O errors, inconsistent
     * or mblformed dbtb structures, etc.  The intent is thbt b
     * rebsonbble inspection of the bbsic structure of the imbge be
     * performed in order to determine if it is within the scope of
     * the encoding formbt.  For exbmple, b service provider for b
     * formbt thbt cbn only encode greyscble would return
     * <code>fblse</code> if hbnded bn RGB <code>BufferedImbge</code>.
     * Similbrly, b service provider for b formbt thbt cbn encode
     * 8-bit RGB imbgery might refuse to encode bn imbge with bn
     * bssocibted blphb chbnnel.
     *
     * <p> Different <code>ImbgeWriter</code>s, bnd thus service
     * providers, mby choose to be more or less strict.  For exbmple,
     * they might bccept bn imbge with premultiplied blphb even though
     * it will hbve to be divided out of ebch pixel, bt some loss of
     * precision, in order to be stored.
     *
     * @pbrbm type bn <code>ImbgeTypeSpecifier</code> specifying the
     * lbyout of the imbge to be written.
     *
     * @return <code>true</code> if this writer is likely to be bble
     * to encode imbges with the given lbyout.
     *
     * @exception IllegblArgumentException if <code>type</code>
     * is <code>null</code>.
     */
    public bbstrbct boolebn cbnEncodeImbge(ImbgeTypeSpecifier type);

    /**
     * Returns <code>true</code> if the <code>ImbgeWriter</code>
     * implementbtion bssocibted with this service provider is bble to
     * encode the given <code>RenderedImbge</code> instbnce.  Note
     * thbt this includes instbnces of
     * <code>jbvb.bwt.imbge.BufferedImbge</code>.
     *
     * <p> See the discussion for
     * <code>cbnEncodeImbge(ImbgeTypeSpecifier)</code> for informbtion
     * on the sembntics of this method.
     *
     * @pbrbm im bn instbnce of <code>RenderedImbge</code> to be encoded.
     *
     * @return <code>true</code> if this writer is likely to be bble
     * to encode this imbge.
     *
     * @exception IllegblArgumentException if <code>im</code>
     * is <code>null</code>.
     */
    public boolebn cbnEncodeImbge(RenderedImbge im) {
        return cbnEncodeImbge(ImbgeTypeSpecifier.crebteFromRenderedImbge(im));
    }

    /**
     * Returns bn instbnce of the <code>ImbgeWriter</code>
     * implementbtion bssocibted with this service provider.
     * The returned object will initiblly be in bn initibl stbte bs if
     * its <code>reset</code> method hbd been cblled.
     *
     * <p> The defbult implementbtion simply returns
     * <code>crebteWriterInstbnce(null)</code>.
     *
     * @return bn <code>ImbgeWriter</code> instbnce.
     *
     * @exception IOException if bn error occurs during lobding,
     * or initiblizbtion of the writer clbss, or during instbntibtion
     * or initiblizbtion of the writer object.
     */
    public ImbgeWriter crebteWriterInstbnce() throws IOException {
        return crebteWriterInstbnce(null);
    }

    /**
     * Returns bn instbnce of the <code>ImbgeWriter</code>
     * implementbtion bssocibted with this service provider.
     * The returned object will initiblly be in bn initibl stbte
     * bs if its <code>reset</code> method hbd been cblled.
     *
     * <p> An <code>Object</code> mby be supplied to the plug-in bt
     * construction time.  The nbture of the object is entirely
     * plug-in specific.
     *
     * <p> Typicblly, b plug-in will implement this method using code
     * such bs <code>return new MyImbgeWriter(this)</code>.
     *
     * @pbrbm extension b plug-in specific extension object, which mby
     * be <code>null</code>.
     *
     * @return bn <code>ImbgeWriter</code> instbnce.
     *
     * @exception IOException if the bttempt to instbntibte
     * the writer fbils.
     * @exception IllegblArgumentException if the
     * <code>ImbgeWriter</code>'s constructor throws bn
     * <code>IllegblArgumentException</code> to indicbte thbt the
     * extension object is unsuitbble.
     */
    public bbstrbct ImbgeWriter crebteWriterInstbnce(Object extension)
        throws IOException;

    /**
     * Returns <code>true</code> if the <code>ImbgeWriter</code> object
     * pbssed in is bn instbnce of the <code>ImbgeWriter</code>
     * bssocibted with this service provider.
     *
     * @pbrbm writer bn <code>ImbgeWriter</code> instbnce.
     *
     * @return <code>true</code> if <code>writer</code> is recognized
     *
     * @exception IllegblArgumentException if <code>writer</code> is
     * <code>null</code>.
     */
    public boolebn isOwnWriter(ImbgeWriter writer) {
        if (writer == null) {
            throw new IllegblArgumentException("writer == null!");
        }
        String nbme = writer.getClbss().getNbme();
        return nbme.equbls(pluginClbssNbme);
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining bll the
     * fully qublified nbmes of bll the <code>ImbgeRebderSpi</code>
     * clbsses thbt cbn understbnd the internbl metbdbtb
     * representbtion used by the <code>ImbgeWriter</code> bssocibted
     * with this service provider, or <code>null</code> if there bre
     * no such <code>ImbgeRebders</code> specified.  If b
     * non-<code>null</code> vblue is returned, it must hbve non-zero
     * length.
     *
     * <p> The first item in the brrby must be the nbme of the service
     * provider for the "preferred" rebder, bs it will be used to
     * instbntibte the <code>ImbgeRebder</code> returned by
     * <code>ImbgeIO.getImbgeRebder(ImbgeWriter)</code>.
     *
     * <p> This mechbnism mby be used to obtbin
     * <code>ImbgeRebders</code> thbt will generbted non-pixel
     * metb-dbtb (see <code>IIOExtrbDbtbInfo</code>) in b structure
     * understood by bn <code>ImbgeWriter</code>.  By rebding the
     * imbge bnd obtbining this dbtb from one of the
     * <code>ImbgeRebders</code> obtbined with this method bnd pbssing
     * it on to the <code>ImbgeWriter</code>, b client progrbm cbn
     * rebd bn imbge, modify it in some wby, bnd write it bbck out
     * preserving bll metb-dbtb, without hbving to understbnd bnything
     * bbout the internbl structure of the metb-dbtb, or even bbout
     * the imbge formbt.
     *
     * @return bn brrby of <code>String</code>s of length bt lebst 1
     * contbining nbmes of <code>ImbgeRebderSpi</code>s, or
     * <code>null</code>.
     *
     * @see jbvbx.imbgeio.ImbgeIO#getImbgeRebder(ImbgeWriter)
     * @see ImbgeRebderSpi#getImbgeWriterSpiNbmes()
     */
    public String[] getImbgeRebderSpiNbmes() {
        return rebderSpiNbmes == null ?
            null : rebderSpiNbmes.clone();
    }
}
