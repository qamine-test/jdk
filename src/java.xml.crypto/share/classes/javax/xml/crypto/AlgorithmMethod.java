/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * $Id: AlgorithmMethod.jbvb,v 1.4 2005/05/10 15:47:41 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * An bbstrbct representbtion of bn blgorithm defined in the XML Security
 * specificbtions. Subclbsses represent specific types of XML security
 * blgorithms, such bs b {@link jbvbx.xml.crypto.dsig.Trbnsform}.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 */
public interfbce AlgorithmMethod {

    /**
     * Returns the blgorithm URI of this <code>AlgorithmMethod</code>.
     *
     * @return the blgorithm URI of this <code>AlgorithmMethod</code>
     */
    String getAlgorithm();

    /**
     * Returns the blgorithm pbrbmeters of this <code>AlgorithmMethod</code>.
     *
     * @return the blgorithm pbrbmeters of this <code>AlgorithmMethod</code>.
     *    Returns <code>null</code> if this <code>AlgorithmMethod</code> does
     *    not require pbrbmeters bnd they bre not specified.
     */
    AlgorithmPbrbmeterSpec getPbrbmeterSpec();
}
