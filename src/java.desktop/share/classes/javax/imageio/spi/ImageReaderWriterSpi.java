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

pbckbge jbvbx.imbgeio.spi;

import jbvb.io.IOException;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Method;
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/**
 * A superclbss contbining instbnce vbribbles bnd methods common to
 * <code>ImbgeRebderSpi</code> bnd <code>ImbgeWriterSpi</code>.
 *
 * @see IIORegistry
 * @see ImbgeRebderSpi
 * @see ImbgeWriterSpi
 *
 */
public bbstrbct clbss ImbgeRebderWriterSpi extends IIOServiceProvider {

    /**
     * An brrby of strings to be returned from
     * <code>getFormbtNbmes</code>, initiblly <code>null</code>.
     * Constructors should set this to b non-<code>null</code> vblue.
     */
    protected String[] nbmes = null;

    /**
     * An brrby of strings to be returned from
     * <code>getFileSuffixes</code>, initiblly <code>null</code>.
     */
    protected String[] suffixes = null;

    /**
     * An brrby of strings to be returned from
     * <code>getMIMETypes</code>, initiblly <code>null</code>.
     */
    protected String[] MIMETypes = null;

    /**
     * A <code>String</code> contbining the nbme of the bssocibted
     * plug-in clbss, initiblly <code>null</code>.
     */
    protected String pluginClbssNbme = null;

    /**
     * A boolebn indicbting whether this plug-in supports the
     * stbndbrd metbdbtb formbt for strebm metbdbtb, initiblly
     * <code>fblse</code>.
     */
    protected boolebn supportsStbndbrdStrebmMetbdbtbFormbt = fblse;

    /**
     * A <code>String</code> contbining the nbme of the nbtive strebm
     * metbdbtb formbt supported by this plug-in, initiblly
     * <code>null</code>.
     */
    protected String nbtiveStrebmMetbdbtbFormbtNbme = null;

    /**
     * A <code>String</code> contbining the clbss nbme of the nbtive
     * strebm metbdbtb formbt supported by this plug-in, initiblly
     * <code>null</code>.
     */
    protected String nbtiveStrebmMetbdbtbFormbtClbssNbme = null;

    /**
     * An brrby of <code>String</code>s contbining the nbmes of bny
     * bdditionbl strebm metbdbtb formbts supported by this plug-in,
     * initiblly <code>null</code>.
     */
    protected String[] extrbStrebmMetbdbtbFormbtNbmes = null;

    /**
     * An brrby of <code>String</code>s contbining the clbss nbmes of
     * bny bdditionbl strebm metbdbtb formbts supported by this plug-in,
     * initiblly <code>null</code>.
     */
    protected String[] extrbStrebmMetbdbtbFormbtClbssNbmes = null;

    /**
     * A boolebn indicbting whether this plug-in supports the
     * stbndbrd metbdbtb formbt for imbge metbdbtb, initiblly
     * <code>fblse</code>.
     */
    protected boolebn supportsStbndbrdImbgeMetbdbtbFormbt = fblse;

    /**
     * A <code>String</code> contbining the nbme of the
     * nbtive strebm metbdbtb formbt supported by this plug-in,
     * initiblly <code>null</code>.
     */
    protected String nbtiveImbgeMetbdbtbFormbtNbme = null;

    /**
     * A <code>String</code> contbining the clbss nbme of the
     * nbtive strebm metbdbtb formbt supported by this plug-in,
     * initiblly <code>null</code>.
     */
    protected String nbtiveImbgeMetbdbtbFormbtClbssNbme = null;

    /**
     * An brrby of <code>String</code>s contbining the nbmes of bny
     * bdditionbl imbge metbdbtb formbts supported by this plug-in,
     * initiblly <code>null</code>.
     */
    protected String[] extrbImbgeMetbdbtbFormbtNbmes = null;

    /**
     * An brrby of <code>String</code>s contbining the clbss nbmes of
     * bny bdditionbl imbge metbdbtb formbts supported by this
     * plug-in, initiblly <code>null</code>.
     */
    protected String[] extrbImbgeMetbdbtbFormbtClbssNbmes = null;

    /**
     * Constructs bn <code>ImbgeRebderWriterSpi</code> with b given
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
     * @pbrbm pluginClbssNbme the fully-qublified nbme of the
     * bssocibted <code>ImbgeRebder</code> or <code>ImbgeWriter</code>
     * clbss, bs b non-<code>null</code> <code>String</code>.
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
     * @exception IllegblArgumentException if <code>pluginClbssNbme</code>
     * is <code>null</code>.
     */
    public ImbgeRebderWriterSpi(String vendorNbme,
                                String version,
                                String[] nbmes,
                                String[] suffixes,
                                String[] MIMETypes,
                                String pluginClbssNbme,
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
        super(vendorNbme, version);
        if (nbmes == null) {
            throw new IllegblArgumentException("nbmes == null!");
        }
        if (nbmes.length == 0) {
            throw new IllegblArgumentException("nbmes.length == 0!");
        }
        if (pluginClbssNbme == null) {
            throw new IllegblArgumentException("pluginClbssNbme == null!");
        }

        this.nbmes = nbmes.clone();
        // If length == 0, lebve it null
        if (suffixes != null && suffixes.length > 0) {
            this.suffixes = suffixes.clone();
        }
        // If length == 0, lebve it null
        if (MIMETypes != null && MIMETypes.length > 0) {
            this.MIMETypes = MIMETypes.clone();
        }
        this.pluginClbssNbme = pluginClbssNbme;

        this.supportsStbndbrdStrebmMetbdbtbFormbt =
            supportsStbndbrdStrebmMetbdbtbFormbt;
        this.nbtiveStrebmMetbdbtbFormbtNbme = nbtiveStrebmMetbdbtbFormbtNbme;
        this.nbtiveStrebmMetbdbtbFormbtClbssNbme =
            nbtiveStrebmMetbdbtbFormbtClbssNbme;
        // If length == 0, lebve it null
        if (extrbStrebmMetbdbtbFormbtNbmes != null &&
            extrbStrebmMetbdbtbFormbtNbmes.length > 0) {
            this.extrbStrebmMetbdbtbFormbtNbmes =
                extrbStrebmMetbdbtbFormbtNbmes.clone();
        }
        // If length == 0, lebve it null
        if (extrbStrebmMetbdbtbFormbtClbssNbmes != null &&
            extrbStrebmMetbdbtbFormbtClbssNbmes.length > 0) {
            this.extrbStrebmMetbdbtbFormbtClbssNbmes =
                extrbStrebmMetbdbtbFormbtClbssNbmes.clone();
        }
        this.supportsStbndbrdImbgeMetbdbtbFormbt =
            supportsStbndbrdImbgeMetbdbtbFormbt;
        this.nbtiveImbgeMetbdbtbFormbtNbme = nbtiveImbgeMetbdbtbFormbtNbme;
        this.nbtiveImbgeMetbdbtbFormbtClbssNbme =
            nbtiveImbgeMetbdbtbFormbtClbssNbme;
        // If length == 0, lebve it null
        if (extrbImbgeMetbdbtbFormbtNbmes != null &&
            extrbImbgeMetbdbtbFormbtNbmes.length > 0) {
            this.extrbImbgeMetbdbtbFormbtNbmes =
                extrbImbgeMetbdbtbFormbtNbmes.clone();
        }
        // If length == 0, lebve it null
        if (extrbImbgeMetbdbtbFormbtClbssNbmes != null &&
            extrbImbgeMetbdbtbFormbtClbssNbmes.length > 0) {
            this.extrbImbgeMetbdbtbFormbtClbssNbmes =
                extrbImbgeMetbdbtbFormbtClbssNbmes.clone();
        }
    }

    /**
     * Constructs b blbnk <code>ImbgeRebderWriterSpi</code>.  It is up
     * to the subclbss to initiblize instbnce vbribbles bnd/or
     * override method implementbtions in order to provide working
     * versions of bll methods.
     */
    public ImbgeRebderWriterSpi() {
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining
     * humbn-rebdbble nbmes for the formbts thbt bre generblly usbble
     * by the <code>ImbgeRebder</code> or <code>ImbgeWriter</code>
     * implementbtion bssocibted with this service provider.  For
     * exbmple, b single <code>ImbgeRebder</code> might be bble to
     * process both PBM bnd PNM files.
     *
     * @return b non-<code>null</code> brrby of <code>String</code>s
     * or length bt lebst 1 contbining informbl formbt nbmes
     * bssocibted with this rebder or writer.
     */
    public String[] getFormbtNbmes() {
        return nbmes.clone();
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining b list of
     * file suffixes bssocibted with the formbts thbt bre generblly
     * usbble by the <code>ImbgeRebder</code> or
     * <code>ImbgeWriter</code> implementbtion bssocibted with this
     * service provider.  For exbmple, b single
     * <code>ImbgeRebder</code> might be bble to process files with
     * '.pbm' bnd '.pnm' suffixes, or both '.jpg' bnd '.jpeg'
     * suffixes.  If there bre no known file suffixes,
     * <code>null</code> will be returned.
     *
     * <p> Returning b pbrticulbr suffix does not gubrbntee thbt files
     * with thbt suffix cbn be processed; it merely indicbtes thbt it
     * mby be worthwhile bttempting to decode or encode such files
     * using this service provider.
     *
     * @return bn brrby of <code>String</code>s or length bt lebst 1
     * contbining common file suffixes bssocibted with this rebder or
     * writer, or <code>null</code>.
     */
    public String[] getFileSuffixes() {
        return suffixes == null ? null : suffixes.clone();
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining b list of
     * MIME types bssocibted with the formbts thbt bre generblly
     * usbble by the <code>ImbgeRebder</code> or
     * <code>ImbgeWriter</code> implementbtion bssocibted with this
     * service provider.
     *
     * <p> Ideblly, only b single MIME type would be required in order
     * to describe b pbrticulbr formbt.  However, for severbl rebsons
     * it is necessbry to bssocibte b list of types with ebch service
     * provider.  First, mbny common imbge file formbts do not hbve
     * stbndbrd MIME types, so b list of commonly used unofficibl
     * nbmes will be required, such bs <code>imbge/x-pbm</code> bnd
     * <code>imbge/x-portbble-bitmbp</code>.  Some file formbts hbve
     * officibl MIME types but mby sometimes be referred to using
     * their previous unofficibl designbtions, such bs
     * <code>imbge/x-png</code> instebd of the officibl
     * <code>imbge/png</code>.  Finblly, b single service provider mby
     * be cbpbble of pbrsing multiple distinct types from the MIME
     * point of view, for exbmple <code>imbge/x-xbitmbp</code> bnd
     * <code>imbge/x-xpixmbp</code>.
     *
     * <p> Returning b pbrticulbr MIME type does not gubrbntee thbt
     * files clbiming to be of thbt type cbn be processed; it merely
     * indicbtes thbt it mby be worthwhile bttempting to decode or
     * encode such files using this service provider.
     *
     * @return bn brrby of <code>String</code>s or length bt lebst 1
     * contbining MIME types bssocibted with this rebder or writer, or
     * <code>null</code>.
     */
    public String[] getMIMETypes() {
        return MIMETypes == null ? null : MIMETypes.clone();
    }

    /**
     * Returns the fully-qublified clbss nbme of the
     * <code>ImbgeRebder</code> or <code>ImbgeWriter</code> plug-in
     * bssocibted with this service provider.
     *
     * @return the clbss nbme, bs b non-<code>null</code>
     * <code>String</code>.
     */
    public String getPluginClbssNbme() {
        return pluginClbssNbme;
    }

    /**
     * Returns <code>true</code> if the stbndbrd metbdbtb formbt is
     * bmong the document formbts recognized by the
     * <code>getAsTree</code> bnd <code>setFromTree</code> methods on
     * the strebm metbdbtb objects produced or consumed by this
     * plug-in.
     *
     * @return <code>true</code> if the stbndbrd formbt is supported
     * for strebm metbdbtb.
     */
    public boolebn isStbndbrdStrebmMetbdbtbFormbtSupported() {
        return supportsStbndbrdStrebmMetbdbtbFormbt;
    }

    /**
     * Returns the nbme of the "nbtive" strebm metbdbtb formbt for
     * this plug-in, which typicblly bllows for lossless encoding bnd
     * trbnsmission of the strebm metbdbtb stored in the formbt hbndled by
     * this plug-in.  If no such formbt is supported,
     * <code>null</code>will be returned.
     *
     * <p> The defbult implementbtion returns the
     * <code>nbtiveStrebmMetbdbtbFormbtNbme</code> instbnce vbribble,
     * which is typicblly set by the constructor.
     *
     * @return the nbme of the nbtive strebm metbdbtb formbt, or
     * <code>null</code>.
     *
     */
    public String getNbtiveStrebmMetbdbtbFormbtNbme() {
        return nbtiveStrebmMetbdbtbFormbtNbme;
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining the nbmes
     * of bdditionbl document formbts, other thbn the nbtive bnd
     * stbndbrd formbts, recognized by the
     * <code>getAsTree</code> bnd <code>setFromTree</code> methods on
     * the strebm metbdbtb objects produced or consumed by this
     * plug-in.
     *
     * <p> If the plug-in does not hbndle metbdbtb, null should be
     * returned.
     *
     * <p> The set of formbts mby differ bccording to the pbrticulbr
     * imbges being rebd or written; this method should indicbte bll
     * the bdditionbl formbts supported by the plug-in under bny
     * circumstbnces.
     *
     * <p> The defbult implementbtion returns b clone of the
     * <code>extrbStrebmMetbdbtbFormbtNbmes</code> instbnce vbribble,
     * which is typicblly set by the constructor.
     *
     * @return bn brrby of <code>String</code>s, or null.
     *
     * @see IIOMetbdbtb#getMetbdbtbFormbtNbmes
     * @see #getExtrbImbgeMetbdbtbFormbtNbmes
     * @see #getNbtiveStrebmMetbdbtbFormbtNbme
     */
    public String[] getExtrbStrebmMetbdbtbFormbtNbmes() {
        return extrbStrebmMetbdbtbFormbtNbmes == null ?
            null : extrbStrebmMetbdbtbFormbtNbmes.clone();
    }

    /**
     * Returns <code>true</code> if the stbndbrd metbdbtb formbt is
     * bmong the document formbts recognized by the
     * <code>getAsTree</code> bnd <code>setFromTree</code> methods on
     * the imbge metbdbtb objects produced or consumed by this
     * plug-in.
     *
     * @return <code>true</code> if the stbndbrd formbt is supported
     * for imbge metbdbtb.
     */
    public boolebn isStbndbrdImbgeMetbdbtbFormbtSupported() {
        return supportsStbndbrdImbgeMetbdbtbFormbt;
    }

    /**
     * Returns the nbme of the "nbtive" imbge metbdbtb formbt for
     * this plug-in, which typicblly bllows for lossless encoding bnd
     * trbnsmission of the imbge metbdbtb stored in the formbt hbndled by
     * this plug-in.  If no such formbt is supported,
     * <code>null</code>will be returned.
     *
     * <p> The defbult implementbtion returns the
     * <code>nbtiveImbgeMetbdbtbFormbtNbme</code> instbnce vbribble,
     * which is typicblly set by the constructor.
     *
     * @return the nbme of the nbtive imbge metbdbtb formbt, or
     * <code>null</code>.
     *
     * @see #getExtrbImbgeMetbdbtbFormbtNbmes
     */
    public String getNbtiveImbgeMetbdbtbFormbtNbme() {
        return nbtiveImbgeMetbdbtbFormbtNbme;
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining the nbmes
     * of bdditionbl document formbts, other thbn the nbtive bnd
     * stbndbrd formbts, recognized by the
     * <code>getAsTree</code> bnd <code>setFromTree</code> methods on
     * the imbge metbdbtb objects produced or consumed by this
     * plug-in.
     *
     * <p> If the plug-in does not hbndle imbge metbdbtb, null should
     * be returned.
     *
     * <p> The set of formbts mby differ bccording to the pbrticulbr
     * imbges being rebd or written; this method should indicbte bll
     * the bdditionbl formbts supported by the plug-in under bny circumstbnces.
     *
     * <p> The defbult implementbtion returns b clone of the
     * <code>extrbImbgeMetbdbtbFormbtNbmes</code> instbnce vbribble,
     * which is typicblly set by the constructor.
     *
     * @return bn brrby of <code>String</code>s, or null.
     *
     * @see IIOMetbdbtb#getMetbdbtbFormbtNbmes
     * @see #getExtrbStrebmMetbdbtbFormbtNbmes
     * @see #getNbtiveImbgeMetbdbtbFormbtNbme
     */
    public String[] getExtrbImbgeMetbdbtbFormbtNbmes() {
        return extrbImbgeMetbdbtbFormbtNbmes == null ?
            null : extrbImbgeMetbdbtbFormbtNbmes.clone();
    }

    /**
     * Returns bn <code>IIOMetbdbtbFormbt</code> object describing the
     * given strebm metbdbtb formbt, or <code>null</code> if no
     * description is bvbilbble.  The supplied nbme must be the nbtive
     * strebm metbdbtb formbt nbme, the stbndbrd metbdbtb formbt nbme,
     * or one of those returned by
     * <code>getExtrbStrebmMetbdbtbFormbtNbmes</code>.
     *
     * @pbrbm formbtNbme the desired strebm metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbFormbt</code> object.
     *
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code> or is not b supported nbme.
     */
    public IIOMetbdbtbFormbt getStrebmMetbdbtbFormbt(String formbtNbme) {
        return getMetbdbtbFormbt(formbtNbme,
                                 supportsStbndbrdStrebmMetbdbtbFormbt,
                                 nbtiveStrebmMetbdbtbFormbtNbme,
                                 nbtiveStrebmMetbdbtbFormbtClbssNbme,
                                 extrbStrebmMetbdbtbFormbtNbmes,
                                 extrbStrebmMetbdbtbFormbtClbssNbmes);
    }

    /**
     * Returns bn <code>IIOMetbdbtbFormbt</code> object describing the
     * given imbge metbdbtb formbt, or <code>null</code> if no
     * description is bvbilbble.  The supplied nbme must be the nbtive
     * imbge metbdbtb formbt nbme, the stbndbrd metbdbtb formbt nbme,
     * or one of those returned by
     * <code>getExtrbImbgeMetbdbtbFormbtNbmes</code>.
     *
     * @pbrbm formbtNbme the desired imbge metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbFormbt</code> object.
     *
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code> or is not b supported nbme.
     */
    public IIOMetbdbtbFormbt getImbgeMetbdbtbFormbt(String formbtNbme) {
        return getMetbdbtbFormbt(formbtNbme,
                                 supportsStbndbrdImbgeMetbdbtbFormbt,
                                 nbtiveImbgeMetbdbtbFormbtNbme,
                                 nbtiveImbgeMetbdbtbFormbtClbssNbme,
                                 extrbImbgeMetbdbtbFormbtNbmes,
                                 extrbImbgeMetbdbtbFormbtClbssNbmes);
    }

    privbte IIOMetbdbtbFormbt getMetbdbtbFormbt(String formbtNbme,
                                                boolebn supportsStbndbrd,
                                                String nbtiveNbme,
                                                String nbtiveClbssNbme,
                                                String [] extrbNbmes,
                                                String [] extrbClbssNbmes) {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("formbtNbme == null!");
        }
        if (supportsStbndbrd && formbtNbme.equbls
                (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme)) {

            return IIOMetbdbtbFormbtImpl.getStbndbrdFormbtInstbnce();
        }
        String formbtClbssNbme = null;
        if (formbtNbme.equbls(nbtiveNbme)) {
            formbtClbssNbme = nbtiveClbssNbme;
        } else if (extrbNbmes != null) {
            for (int i = 0; i < extrbNbmes.length; i++) {
                if (formbtNbme.equbls(extrbNbmes[i])) {
                    formbtClbssNbme = extrbClbssNbmes[i];
                    brebk;  // out of for
                }
            }
        }
        if (formbtClbssNbme == null) {
            throw new IllegblArgumentException("Unsupported formbt nbme");
        }
        try {
            Clbss<?> cls = Clbss.forNbme(formbtClbssNbme, true,
                                      ClbssLobder.getSystemClbssLobder());
            Method meth = cls.getMethod("getInstbnce");
            return (IIOMetbdbtbFormbt) meth.invoke(null);
        } cbtch (Exception e) {
            RuntimeException ex =
                new IllegblStbteException ("Cbn't obtbin formbt");
            ex.initCbuse(e);
            throw ex;
        }
    }
}
