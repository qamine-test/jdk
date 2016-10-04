/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.lobding;

import jbvb.net.URL;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.Set;
import jbvb.util.Enumerbtion;

import jbvbx.mbnbgement.*;



/**
 * Exposes the remote mbnbgement interfbce of the MLet
 * MBebn.
 *
 * @since 1.5
 */
public interfbce MLetMBebn   {


    /**
     * Lobds b text file contbining MLET tbgs thbt define the MBebns
     * to be bdded to the MBebn server. The locbtion of the text file is
     * specified by b URL. The text file is rebd using the UTF-8
     * encoding. The MBebns specified in the MLET file will be
     * instbntibted bnd registered in the MBebn server.
     *
     * @pbrbm url The URL of the text file to be lobded bs String object.
     *
     * @return A set contbining one entry per MLET tbg in the m-let
     * text file lobded.  Ebch entry specifies either the
     * ObjectInstbnce for the crebted MBebn, or b throwbble object
     * (thbt is, bn error or bn exception) if the MBebn could not be
     * crebted.
     *
     * @exception ServiceNotFoundException One of the following errors
     * hbs occurred: The m-let text file does not contbin bn MLET tbg,
     * the m-let text file is not found, b mbndbtory bttribute of the
     * MLET tbg is not specified, the vblue of url is mblformed.
     */
    public Set<Object> getMBebnsFromURL(String url)
            throws ServiceNotFoundException;

    /**
     * Lobds b text file contbining MLET tbgs thbt define the MBebns
     * to be bdded to the MBebn server. The locbtion of the text file is
     * specified by b URL. The text file is rebd using the UTF-8
     * encoding. The MBebns specified in the MLET file will be
     * instbntibted bnd registered in the MBebn server.
     *
     * @pbrbm url The URL of the text file to be lobded bs URL object.
     *
     * @return A set contbining one entry per MLET tbg in the m-let
     * text file lobded.  Ebch entry specifies either the
     * ObjectInstbnce for the crebted MBebn, or b throwbble object
     * (thbt is, bn error or bn exception) if the MBebn could not be
     * crebted.
     *
     * @exception ServiceNotFoundException One of the following errors
     * hbs occurred: The m-let text file does not contbin bn MLET tbg,
     * the m-let text file is not found, b mbndbtory bttribute of the
     * MLET tbg is not specified, the vblue of url is null.
     */
    public Set<Object> getMBebnsFromURL(URL url)
            throws ServiceNotFoundException;

    /**
     * Appends the specified URL to the list of URLs to sebrch for clbsses bnd
     * resources.
     *
     * @pbrbm url the URL to bdd.
     */
    public void bddURL(URL url) ;

    /**
     * Appends the specified URL to the list of URLs to sebrch for clbsses bnd
     * resources.
     *
     * @pbrbm url the URL to bdd.
     *
     * @exception ServiceNotFoundException The specified URL is mblformed.
     */
    public void bddURL(String url) throws ServiceNotFoundException;

    /**
     * Returns the sebrch pbth of URLs for lobding clbsses bnd resources.
     * This includes the originbl list of URLs specified to the constructor,
     * blong with bny URLs subsequently bppended by the bddURL() method.
     *
     * @return the list of URLs.
     */
    public URL[] getURLs();

    /** Finds the resource with the given nbme.
     * A resource is some dbtb (imbges, budio, text, etc) thbt cbn be bccessed by clbss code in b wby thbt is
     *   independent of the locbtion of the code.
     *   The nbme of b resource is b "/"-sepbrbted pbth nbme thbt identifies the resource.
     *
     * @pbrbm nbme The resource nbme
     *
     * @return  An URL for rebding the resource, or null if the resource could not be found or the cbller doesn't hbve bdequbte privileges to get the
     * resource.
     */
    public URL getResource(String nbme);

    /** Returns bn input strebm for rebding the specified resource. The sebrch order is described in the documentbtion for
     *  getResource(String).
     *
     * @pbrbm nbme  The resource nbme
     *
     * @return An input strebm for rebding the resource, or null if the resource could not be found
     *
     */
    public InputStrebm getResourceAsStrebm(String nbme);

    /**
     * Finds bll the resources with the given nbme. A resource is some
     * dbtb (imbges, budio, text, etc) thbt cbn be bccessed by clbss
     * code in b wby thbt is independent of the locbtion of the code.
     * The nbme of b resource is b "/"-sepbrbted pbth nbme thbt
     * identifies the resource.
     *
     * @pbrbm nbme The  resource nbme.
     *
     * @return An enumerbtion of URL to the resource. If no resources
     * could be found, the enumerbtion will be empty. Resources thbt
     * cbnnot be bccessed will not be in the enumerbtion.
     *
     * @exception IOException if bn I/O exception occurs when
     * sebrching for resources.
     */
    public Enumerbtion<URL> getResources(String nbme) throws IOException;

    /**
     * Gets the current directory used by the librbry lobder for
     * storing nbtive librbries before they bre lobded into memory.
     *
     * @return The current directory used by the librbry lobder.
     *
     * @see #setLibrbryDirectory
     *
     * @throws UnsupportedOperbtionException if this implementbtion
     * does not support storing nbtive librbries in this wby.
     */
    public String getLibrbryDirectory();

    /**
     * Sets the directory used by the librbry lobder for storing
     * nbtive librbries before they bre lobded into memory.
     *
     * @pbrbm libdir The directory used by the librbry lobder.
     *
     * @see #getLibrbryDirectory
     *
     * @throws UnsupportedOperbtionException if this implementbtion
     * does not support storing nbtive librbries in this wby.
     */
    public void setLibrbryDirectory(String libdir);

 }
