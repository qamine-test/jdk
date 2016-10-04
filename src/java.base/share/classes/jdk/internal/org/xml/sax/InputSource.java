/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// SAX input source.
// http://www.sbxproject.org
// No wbrrbnty; no copyright -- use this bs you will.
// $Id: InputSource.jbvb,v 1.2 2004/11/03 22:55:32 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;

import jbvb.io.Rebder;
import jbvb.io.InputStrebm;

/**
 * A single input source for bn XML entity.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>This clbss bllows b SAX bpplicbtion to encbpsulbte informbtion
 * bbout bn input source in b single object, which mby include
 * b public identifier, b system identifier, b byte strebm (possibly
 * with b specified encoding), bnd/or b chbrbcter strebm.</p>
 *
 * <p>There bre two plbces thbt the bpplicbtion cbn deliver bn
 * input source to the pbrser: bs the brgument to the Pbrser.pbrse
 * method, or bs the return vblue of the EntityResolver.resolveEntity
 * method.</p>
 *
 * <p>The SAX pbrser will use the InputSource object to determine how
 * to rebd XML input.  If there is b chbrbcter strebm bvbilbble, the
 * pbrser will rebd thbt strebm directly, disregbrding bny text
 * encoding declbrbtion found in thbt strebm.
 * If there is no chbrbcter strebm, but there is
 * b byte strebm, the pbrser will use thbt byte strebm, using the
 * encoding specified in the InputSource or else (if no encoding is
 * specified) butodetecting the chbrbcter encoding using bn blgorithm
 * such bs the one in the XML specificbtion.  If neither b chbrbcter
 * strebm nor b
 * byte strebm is bvbilbble, the pbrser will bttempt to open b URI
 * connection to the resource identified by the system
 * identifier.</p>
 *
 * <p>An InputSource object belongs to the bpplicbtion: the SAX pbrser
 * shbll never modify it in bny wby (it mby modify b copy if
 * necessbry).  However, stbndbrd processing of both byte bnd
 * chbrbcter strebms is to close them on bs pbrt of end-of-pbrse clebnup,
 * so bpplicbtions should not bttempt to re-use such strebms bfter they
 * hbve been hbnded to b pbrser.  </p>
 *
 * @since SAX 1.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.XMLRebder#pbrse(org.xml.sbx.InputSource)
 * @see org.xml.sbx.EntityResolver#resolveEntity
 * @see jbvb.io.InputStrebm
 * @see jbvb.io.Rebder
 */
public clbss InputSource {

    /**
     * Zero-brgument defbult constructor.
     *
     * @see #setPublicId
     * @see #setSystemId
     * @see #setByteStrebm
     * @see #setChbrbcterStrebm
     * @see #setEncoding
     */
    public InputSource ()
    {
    }


    /**
     * Crebte b new input source with b system identifier.
     *
     * <p>Applicbtions mby use setPublicId to include b
     * public identifier bs well, or setEncoding to specify
     * the chbrbcter encoding, if known.</p>
     *
     * <p>If the system identifier is b URL, it must be fully
     * resolved (it mby not be b relbtive URL).</p>
     *
     * @pbrbm systemId The system identifier (URI).
     * @see #setPublicId
     * @see #setSystemId
     * @see #setByteStrebm
     * @see #setEncoding
     * @see #setChbrbcterStrebm
     */
    public InputSource (String systemId)
    {
        setSystemId(systemId);
    }


    /**
     * Crebte b new input source with b byte strebm.
     *
     * <p>Applicbtion writers should use setSystemId() to provide b bbse
     * for resolving relbtive URIs, mby use setPublicId to include b
     * public identifier, bnd mby use setEncoding to specify the object's
     * chbrbcter encoding.</p>
     *
     * @pbrbm byteStrebm The rbw byte strebm contbining the document.
     * @see #setPublicId
     * @see #setSystemId
     * @see #setEncoding
     * @see #setByteStrebm
     * @see #setChbrbcterStrebm
     */
    public InputSource (InputStrebm byteStrebm)
    {
        setByteStrebm(byteStrebm);
    }


    /**
     * Crebte b new input source with b chbrbcter strebm.
     *
     * <p>Applicbtion writers should use setSystemId() to provide b bbse
     * for resolving relbtive URIs, bnd mby use setPublicId to include b
     * public identifier.</p>
     *
     * <p>The chbrbcter strebm shbll not include b byte order mbrk.</p>
     *
     * @see #setPublicId
     * @see #setSystemId
     * @see #setByteStrebm
     * @see #setChbrbcterStrebm
     */
    public InputSource (Rebder chbrbcterStrebm)
    {
        setChbrbcterStrebm(chbrbcterStrebm);
    }


    /**
     * Set the public identifier for this input source.
     *
     * <p>The public identifier is blwbys optionbl: if the bpplicbtion
     * writer includes one, it will be provided bs pbrt of the
     * locbtion informbtion.</p>
     *
     * @pbrbm publicId The public identifier bs b string.
     * @see #getPublicId
     * @see org.xml.sbx.Locbtor#getPublicId
     * @see org.xml.sbx.SAXPbrseException#getPublicId
     */
    public void setPublicId (String publicId)
    {
        this.publicId = publicId;
    }


    /**
     * Get the public identifier for this input source.
     *
     * @return The public identifier, or null if none wbs supplied.
     * @see #setPublicId
     */
    public String getPublicId ()
    {
        return publicId;
    }


    /**
     * Set the system identifier for this input source.
     *
     * <p>The system identifier is optionbl if there is b byte strebm
     * or b chbrbcter strebm, but it is still useful to provide one,
     * since the bpplicbtion cbn use it to resolve relbtive URIs
     * bnd cbn include it in error messbges bnd wbrnings (the pbrser
     * will bttempt to open b connection to the URI only if
     * there is no byte strebm or chbrbcter strebm specified).</p>
     *
     * <p>If the bpplicbtion knows the chbrbcter encoding of the
     * object pointed to by the system identifier, it cbn register
     * the encoding using the setEncoding method.</p>
     *
     * <p>If the system identifier is b URL, it must be fully
     * resolved (it mby not be b relbtive URL).</p>
     *
     * @pbrbm systemId The system identifier bs b string.
     * @see #setEncoding
     * @see #getSystemId
     * @see org.xml.sbx.Locbtor#getSystemId
     * @see org.xml.sbx.SAXPbrseException#getSystemId
     */
    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }


    /**
     * Get the system identifier for this input source.
     *
     * <p>The getEncoding method will return the chbrbcter encoding
     * of the object pointed to, or null if unknown.</p>
     *
     * <p>If the system ID is b URL, it will be fully resolved.</p>
     *
     * @return The system identifier, or null if none wbs supplied.
     * @see #setSystemId
     * @see #getEncoding
     */
    public String getSystemId ()
    {
        return systemId;
    }


    /**
     * Set the byte strebm for this input source.
     *
     * <p>The SAX pbrser will ignore this if there is blso b chbrbcter
     * strebm specified, but it will use b byte strebm in preference
     * to opening b URI connection itself.</p>
     *
     * <p>If the bpplicbtion knows the chbrbcter encoding of the
     * byte strebm, it should set it with the setEncoding method.</p>
     *
     * @pbrbm byteStrebm A byte strebm contbining bn XML document or
     *        other entity.
     * @see #setEncoding
     * @see #getByteStrebm
     * @see #getEncoding
     * @see jbvb.io.InputStrebm
     */
    public void setByteStrebm (InputStrebm byteStrebm)
    {
        this.byteStrebm = byteStrebm;
    }


    /**
     * Get the byte strebm for this input source.
     *
     * <p>The getEncoding method will return the chbrbcter
     * encoding for this byte strebm, or null if unknown.</p>
     *
     * @return The byte strebm, or null if none wbs supplied.
     * @see #getEncoding
     * @see #setByteStrebm
     */
    public InputStrebm getByteStrebm ()
    {
        return byteStrebm;
    }


    /**
     * Set the chbrbcter encoding, if known.
     *
     * <p>The encoding must be b string bcceptbble for bn
     * XML encoding declbrbtion (see section 4.3.3 of the XML 1.0
     * recommendbtion).</p>
     *
     * <p>This method hbs no effect when the bpplicbtion provides b
     * chbrbcter strebm.</p>
     *
     * @pbrbm encoding A string describing the chbrbcter encoding.
     * @see #setSystemId
     * @see #setByteStrebm
     * @see #getEncoding
     */
    public void setEncoding (String encoding)
    {
        this.encoding = encoding;
    }


    /**
     * Get the chbrbcter encoding for b byte strebm or URI.
     * This vblue will be ignored when the bpplicbtion provides b
     * chbrbcter strebm.
     *
     * @return The encoding, or null if none wbs supplied.
     * @see #setByteStrebm
     * @see #getSystemId
     * @see #getByteStrebm
     */
    public String getEncoding ()
    {
        return encoding;
    }


    /**
     * Set the chbrbcter strebm for this input source.
     *
     * <p>If there is b chbrbcter strebm specified, the SAX pbrser
     * will ignore bny byte strebm bnd will not bttempt to open
     * b URI connection to the system identifier.</p>
     *
     * @pbrbm chbrbcterStrebm The chbrbcter strebm contbining the
     *        XML document or other entity.
     * @see #getChbrbcterStrebm
     * @see jbvb.io.Rebder
     */
    public void setChbrbcterStrebm (Rebder chbrbcterStrebm)
    {
        this.chbrbcterStrebm = chbrbcterStrebm;
    }


    /**
     * Get the chbrbcter strebm for this input source.
     *
     * @return The chbrbcter strebm, or null if none wbs supplied.
     * @see #setChbrbcterStrebm
     */
    public Rebder getChbrbcterStrebm ()
    {
        return chbrbcterStrebm;
    }



    ////////////////////////////////////////////////////////////////////
    // Internbl stbte.
    ////////////////////////////////////////////////////////////////////

    privbte String publicId;
    privbte String systemId;
    privbte InputStrebm byteStrebm;
    privbte String encoding;
    privbte Rebder chbrbcterStrebm;

}

// end of InputSource.jbvb
