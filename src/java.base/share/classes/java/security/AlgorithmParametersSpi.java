/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code AlgorithmPbrbmeters} clbss, which is used to mbnbge
 * blgorithm pbrbmeters.
 *
 * <p> All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply pbrbmeter mbnbgement
 * for b pbrticulbr blgorithm.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see AlgorithmPbrbmeters
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 * @see jbvb.security.spec.DSAPbrbmeterSpec
 *
 * @since 1.2
 */

public bbstrbct clbss AlgorithmPbrbmetersSpi {

    /**
     * Initiblizes this pbrbmeters object using the pbrbmeters
     * specified in {@code pbrbmSpec}.
     *
     * @pbrbm pbrbmSpec the pbrbmeter specificbtion.
     *
     * @exception InvblidPbrbmeterSpecException if the given pbrbmeter
     * specificbtion is inbppropribte for the initiblizbtion of this pbrbmeter
     * object.
     */
    protected bbstrbct void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException;

    /**
     * Imports the specified pbrbmeters bnd decodes them
     * bccording to the primbry decoding formbt for pbrbmeters.
     * The primbry decoding formbt for pbrbmeters is ASN.1, if bn ASN.1
     * specificbtion for this type of pbrbmeters exists.
     *
     * @pbrbm pbrbms the encoded pbrbmeters.
     *
     * @exception IOException on decoding errors
     */
    protected bbstrbct void engineInit(byte[] pbrbms)
        throws IOException;

    /**
     * Imports the pbrbmeters from {@code pbrbms} bnd
     * decodes them bccording to the specified decoding formbt.
     * If {@code formbt} is null, the
     * primbry decoding formbt for pbrbmeters is used. The primbry decoding
     * formbt is ASN.1, if bn ASN.1 specificbtion for these pbrbmeters
     * exists.
     *
     * @pbrbm pbrbms the encoded pbrbmeters.
     *
     * @pbrbm formbt the nbme of the decoding formbt.
     *
     * @exception IOException on decoding errors
     */
    protected bbstrbct void engineInit(byte[] pbrbms, String formbt)
        throws IOException;

    /**
     * Returns b (trbnspbrent) specificbtion of this pbrbmeters
     * object.
     * {@code pbrbmSpec} identifies the specificbtion clbss in which
     * the pbrbmeters should be returned. It could, for exbmple, be
     * {@code DSAPbrbmeterSpec.clbss}, to indicbte thbt the
     * pbrbmeters should be returned in bn instbnce of the
     * {@code DSAPbrbmeterSpec} clbss.
     *
     * @pbrbm <T> the type of the pbrbmeter specificbtion to be returned
     *
     * @pbrbm pbrbmSpec the specificbtion clbss in which
     * the pbrbmeters should be returned.
     *
     * @return the pbrbmeter specificbtion.
     *
     * @exception InvblidPbrbmeterSpecException if the requested pbrbmeter
     * specificbtion is inbppropribte for this pbrbmeter object.
     */
    protected bbstrbct
        <T extends AlgorithmPbrbmeterSpec>
        T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException;

    /**
     * Returns the pbrbmeters in their primbry encoding formbt.
     * The primbry encoding formbt for pbrbmeters is ASN.1, if bn ASN.1
     * specificbtion for this type of pbrbmeters exists.
     *
     * @return the pbrbmeters encoded using their primbry encoding formbt.
     *
     * @exception IOException on encoding errors.
     */
    protected bbstrbct byte[] engineGetEncoded() throws IOException;

    /**
     * Returns the pbrbmeters encoded in the specified formbt.
     * If {@code formbt} is null, the
     * primbry encoding formbt for pbrbmeters is used. The primbry encoding
     * formbt is ASN.1, if bn ASN.1 specificbtion for these pbrbmeters
     * exists.
     *
     * @pbrbm formbt the nbme of the encoding formbt.
     *
     * @return the pbrbmeters encoded using the specified encoding scheme.
     *
     * @exception IOException on encoding errors.
     */
    protected bbstrbct byte[] engineGetEncoded(String formbt)
        throws IOException;

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters.
     */
    protected bbstrbct String engineToString();
}
