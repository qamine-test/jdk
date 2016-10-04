/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: XMLSignContext.jbvb,v 1.8 2005/05/10 16:03:48 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.KeySelector;
import jbvbx.xml.crypto.XMLCryptoContext;

/**
 * Contbins context informbtion for generbting XML Signbtures. This interfbce
 * is primbrily intended for type-sbfety.
 *
 * <p>Note thbt <code>XMLSignContext</code> instbnces cbn contbin
 * informbtion bnd stbte specific to the XML signbture structure it is
 * used with. The results bre unpredictbble if bn
 * <code>XMLSignContext</code> is used with different signbture structures
 * (for exbmple, you should not use the sbme <code>XMLSignContext</code>
 * instbnce to sign two different {@link XMLSignbture} objects).
 * <p>
 * <b><b nbme="SupportedProperties"></b>Supported Properties</b>
 * <p>The following properties cbn be set using the
 * {@link #setProperty setProperty} method.
 * <ul>
 *   <li><code>jbvbx.xml.crypto.dsig.cbcheReference</code>: vblue must be b
 *      {@link Boolebn}. This property controls whether or not the digested
 *      {@link Reference} objects will cbche the dereferenced content bnd
 *      pre-digested input for subsequent retrievbl vib the
 *      {@link Reference#getDereferencedDbtb Reference.getDereferencedDbtb} bnd
 *      {@link Reference#getDigestInputStrebm Reference.getDigestInputStrebm}
 *      methods. The defbult vblue if not specified is
 *      <code>Boolebn.FALSE</code>.
 * </ul>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbture#sign(XMLSignContext)
 */
public interfbce XMLSignContext extends XMLCryptoContext {}
